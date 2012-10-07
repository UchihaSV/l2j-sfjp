/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.instance.L2RaidBossInstance;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.util.Broadcast;
import com.l2jserver.util.Rnd;

/**
 * @author godson
 */
public class RaidBossSpawnManager
{
	private static Logger _log = Logger.getLogger(RaidBossSpawnManager.class.getName());
	
	protected static Map<Integer, L2RaidBossInstance> _bosses;
	protected static Map<Integer, L2Spawn> _spawns;
	protected static Map<Integer, StatsSet> _storedInfo;
	protected static Map<Integer, ScheduledFuture<?>> _schedules;
	
	public static enum StatusEnum
	{
		ALIVE,
		DEAD,
		UNDEFINED
	}
	
	protected RaidBossSpawnManager()
	{
		init();
	}
	
	public static RaidBossSpawnManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private void init()
	{
		_bosses = new FastMap<>();
		_schedules = new FastMap<>();
		_storedInfo = new FastMap<>();
		_spawns = new FastMap<>();
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM raidboss_spawnlist ORDER BY boss_id");
			ResultSet rset = statement.executeQuery())
		{
			L2Spawn spawnDat;
			L2NpcTemplate template;
			long respawnTime;
			while (rset.next())
			{
				template = getValidTemplate(rset.getInt("boss_id"));
				if (template != null)
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setLocx(rset.getInt("loc_x"));
					spawnDat.setLocy(rset.getInt("loc_y"));
					spawnDat.setLocz(rset.getInt("loc_z"));
					spawnDat.setAmount(rset.getInt("amount"));
					spawnDat.setHeading(rset.getInt("heading"));
					spawnDat.setRespawnMinDelay(rset.getInt("respawn_min_delay"));
					spawnDat.setRespawnMaxDelay(rset.getInt("respawn_max_delay"));
					respawnTime = rset.getLong("respawn_time");
					
					addNewSpawn(spawnDat, respawnTime, rset.getDouble("currentHP"), rset.getDouble("currentMP"), false);
				}
				else
				{
					_log.warning(getClass().getSimpleName() + ": Could not load raidboss #" + rset.getInt("boss_id") + " from DB");
				}
			}
			
			_log.info(getClass().getSimpleName() + ": Loaded " + _bosses.size() + " Instances");
			_log.info(getClass().getSimpleName() + ": Scheduled " + _schedules.size() + " Instances");
		}
		catch (SQLException e)
		{
			_log.warning(getClass().getSimpleName() + ": Couldnt load raidboss_spawnlist table");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Error while initializing RaidBossSpawnManager: " + e.getMessage(), e);
		}
	}
	
	private static class SpawnSchedule implements Runnable
	{
		private static final Logger _log = Logger.getLogger(SpawnSchedule.class.getName());
		
		private final int bossId;
		
		public SpawnSchedule(int npcId)
		{
			bossId = npcId;
		}
		
		@Override
		public void run()
		{
			L2RaidBossInstance raidboss = null;
			
			if (bossId == 25328)
			{
				raidboss = DayNightSpawnManager.getInstance().handleBoss(_spawns.get(bossId));
			}
			else
			{
				raidboss = (L2RaidBossInstance) _spawns.get(bossId).doSpawn();
			}
			
			if (raidboss != null)
			{
				raidboss.setRaidStatus(StatusEnum.ALIVE);
				
				StatsSet info = new StatsSet();
				info.set("currentHP", raidboss.getCurrentHp());
				info.set("currentMP", raidboss.getCurrentMp());
				info.set("respawnTime", 0L);
				
				_storedInfo.put(bossId, info);
				
				_log.info(getClass().getSimpleName() + ": Spawning Raid Boss " + raidboss.getName());
				
				_bosses.put(bossId, raidboss);
			}
			
			_schedules.remove(bossId);
		}
	}
	
	public void updateStatus(L2RaidBossInstance boss)	//[JOJO]
	{
		StatsSet info = _storedInfo.get(boss.getNpcId());
		if (info == null)
		{
			return;
		}
		
		boss.setRaidStatus(StatusEnum.DEAD);
		
		final int respawnMinDelay = boss.getSpawn().getRespawnMinDelay();
		final int respawnMaxDelay = boss.getSpawn().getRespawnMaxDelay();
		final long respawnDelay = Rnd.get((int) (respawnMinDelay * 1000 * Config.RAID_MIN_RESPAWN_MULTIPLIER), (int) (respawnMaxDelay * 1000 * Config.RAID_MAX_RESPAWN_MULTIPLIER));
		final long respawnTime = System.currentTimeMillis() + respawnDelay;
		
		info.set("currentHP", boss.getMaxHp());
		info.set("currentMP", boss.getMaxMp());
		info.set("respawnTime", respawnTime);
		
		if (!_schedules.containsKey(boss.getNpcId()) && respawnMinDelay < respawnMaxDelay)					//+[JOJO]
	//	if (!_schedules.containsKey(boss.getNpcId()) && ((respawnMinDelay > 0) && (respawnMaxDelay > 0)))	//-[JOJO]
		{
			String time = com.l2jserver.util.Util.dateFormat(respawnTime);	//[JOJO]
			Broadcast.announceToOnlinePlayers(boss.getName() + " �����S���܂����B���̏o���� " + time + " ����ł��B");	//[JOJO]
			_log.info(getClass().getSimpleName() + ": Updated " + boss.getName() + " respawn time to " + /*JOJO*/time);
		//	Calendar time = Calendar.getInstance();
		//	time.setTimeInMillis(respawnTime);
		//	_log.info(getClass().getSimpleName() + ": Updated " + boss.getName() + " respawn time to " + time.getTime());
			
			ScheduledFuture<?> futureSpawn;
			futureSpawn = ThreadPoolManager.getInstance().scheduleGeneral(new SpawnSchedule(boss.getNpcId()), respawnDelay);
			
			_schedules.put(boss.getNpcId(), futureSpawn);
			updateDb(boss.getNpcId());
		}
		else
		{
			Broadcast.announceToOnlinePlayers(boss.getName() + " �����S���܂����B");	//[JOJO]
			//  SELECT npc.id, npc.name,raidboss_spawnlist.loc_x,raidboss_spawnlist.loc_y,raidboss_spawnlist.loc_z,raidboss_spawnlist.respawn_min_delay,raidboss_spawnlist.respawn_max_delay,raidboss_spawnlist.respawn_time FROM raidboss_spawnlist LEFT JOIN npc ON (npc.id=raidboss_spawnlist.boss_id) WHERE respawn_min_delay=0 OR respawn_max_delay=0;
			//  id    name                      loc_x  loc_y  loc_z respawn_min_delay respawn_max_delay respawn_time
			//- 25680 �W���C�A���g �}���p�i�b�N 193902 54135  -4184 0                 21600             0
			//- 25681 �S���S���X                186210 61479  -4000 0                 21600             0
			//- 25684 �Ō�̉����l ���e�k�X     186919 56297  -4480 0                 21600             0
			//+ 29054 �x�m��                    11882  -49216 -3008 0                 0                 0
		}
	}
	
	public void addNewSpawn(L2Spawn spawnDat, long respawnTime, double currentHP, double currentMP, boolean storeInDb)
	{
		if (spawnDat == null)
		{
			return;
		}
		if (_spawns.containsKey(spawnDat.getNpcid()))
		{
			return;
		}
		
		int bossId = spawnDat.getNpcid();
		long time = Calendar.getInstance().getTimeInMillis();
		
		SpawnTable.getInstance().addNewSpawn(spawnDat, false);
		
		if ((respawnTime == 0L) || (time > respawnTime))
		{
			L2RaidBossInstance raidboss = null;
			
			if (bossId == 25328)
			{
				raidboss = DayNightSpawnManager.getInstance().handleBoss(spawnDat);
			}
			else
			{
				raidboss = (L2RaidBossInstance) spawnDat.doSpawn();
			}
			
			if (raidboss != null)
			{
				raidboss.setCurrentHp(currentHP);
				raidboss.setCurrentMp(currentMP);
				raidboss.setRaidStatus(StatusEnum.ALIVE);
				
				_bosses.put(bossId, raidboss);
				
				StatsSet info = new StatsSet();
				info.set("currentHP", currentHP);
				info.set("currentMP", currentMP);
				info.set("respawnTime", 0L);
				
				_storedInfo.put(bossId, info);
			}
		}
		else
		{
			ScheduledFuture<?> futureSpawn;
			long spawnTime = respawnTime - Calendar.getInstance().getTimeInMillis();
			
			futureSpawn = ThreadPoolManager.getInstance().scheduleGeneral(new SpawnSchedule(bossId), spawnTime);
			
			_schedules.put(bossId, futureSpawn);
		}
		
		_spawns.put(bossId, spawnDat);
		
		if (storeInDb)
		{
			try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO raidboss_spawnlist (boss_id,amount,loc_x,loc_y,loc_z,heading,respawn_time,currentHp,currentMp) VALUES(?,?,?,?,?,?,?,?,?)"))
			{
				statement.setInt(1, spawnDat.getNpcid());
				statement.setInt(2, spawnDat.getAmount());
				statement.setInt(3, spawnDat.getLocx());
				statement.setInt(4, spawnDat.getLocy());
				statement.setInt(5, spawnDat.getLocz());
				statement.setInt(6, spawnDat.getHeading());
				statement.setLong(7, respawnTime);
				statement.setDouble(8, currentHP);
				statement.setDouble(9, currentMP);
				statement.execute();
			}
			catch (Exception e)
			{
				// problem with storing spawn
				_log.log(Level.WARNING, getClass().getSimpleName() + ": Could not store raidboss #" + bossId + " in the DB:" + e.getMessage(), e);
			}
		}
	}
	
	public void deleteSpawn(L2Spawn spawnDat, boolean updateDb)
	{
		if (spawnDat == null)
		{
			return;
		}
		
		int bossId = spawnDat.getNpcid();
		
		if (!_spawns.containsKey(bossId))
		{
			return;
		}
		
		SpawnTable.getInstance().deleteSpawn(spawnDat, false);
		_spawns.remove(bossId);
		
		if (_bosses.containsKey(bossId))
		{
			_bosses.remove(bossId);
		}
		
		if (_schedules.containsKey(bossId))
		{
			ScheduledFuture<?> f = _schedules.get(bossId);
			f.cancel(true);
			_schedules.remove(bossId);
		}
		
		if (_storedInfo.containsKey(bossId))
		{
			_storedInfo.remove(bossId);
		}
		
		if (updateDb)
		{
			try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("DELETE FROM raidboss_spawnlist WHERE boss_id=?"))
			{
				statement.setInt(1, bossId);
				statement.execute();
			}
			catch (Exception e)
			{
				// problem with deleting spawn
				_log.log(Level.WARNING, getClass().getSimpleName() + ": Could not remove raidboss #" + bossId + " from DB: " + e.getMessage(), e);
			}
		}
	}
	
	//[JOJO]-------------------------------------------------
	private void updateDb(int bossId)
	{
		ArrayList<Integer> e = new ArrayList<>(1);
		e.add(bossId);
		updateDb(e);
	}
	//-------------------------------------------------------
	private void updateDb(Collection<Integer> bossIdList)	//[JOJO]
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE raidboss_spawnlist SET respawn_time = ?, currentHP = ?, currentMP = ? WHERE boss_id = ?"))
		{
			for (Integer bossId : bossIdList)	//[JOJO]
			{
				if (bossId == null)
				{
					continue;
				}
				
				L2RaidBossInstance boss = _bosses.get(bossId);
				
				if (boss == null)
				{
					continue;
				}
				
				StatsSet info = _storedInfo.get(bossId);
				
				if (info == null)
				{
					continue;
				}
				
				//[JOJO]-------------------------------------------------
				if (boss.getRaidStatus() == StatusEnum.ALIVE)
				{
					info.set("currentHP", boss.getCurrentHp());
					info.set("currentMP", boss.getCurrentMp());
					info.set("respawnTime", 0L);
				}
				//-------------------------------------------------------
				
				try
				{
					statement.setLong(1, info.getLong("respawnTime"));
					statement.setDouble(2, info.getDouble("currentHP"));
					statement.setDouble(3, info.getDouble("currentMP"));
					statement.setInt(4, bossId);
					statement.executeUpdate();
					statement.clearParameters();
				}
				catch (SQLException e)
				{
					_log.log(Level.WARNING, getClass().getSimpleName() + ": Couldnt update raidboss_spawnlist table " + e.getMessage(), e);
				}
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": SQL error while updating RaidBoss spawn to database: " + e.getMessage(), e);
		}
	}
	
	public String[] getAllRaidBossStatus()
	{
		String[] msg = new String[_bosses == null ? 0 : _bosses.size()];
		
		if (_bosses == null)
		{
			msg[0] = "None";
			return msg;
		}
		
		int index = 0;
		
		for (int i : _bosses.keySet())
		{
			L2RaidBossInstance boss = _bosses.get(i);
			
			msg[index++] = boss.getName() + ": " + boss.getRaidStatus().name();
		}
		
		return msg;
	}
	
	public String getRaidBossStatus(int bossId)
	{
		String msg = "RaidBoss Status....\n";
		
		if (_bosses == null)
		{
			msg += "None";
			return msg;
		}
		
		if (_bosses.containsKey(bossId))
		{
			L2RaidBossInstance boss = _bosses.get(bossId);
			
			msg += boss.getName() + ": " + boss.getRaidStatus().name();
		}
		
		return msg;
	}
	
	public StatusEnum getRaidBossStatusId(int bossId)
	{
		if (_bosses.containsKey(bossId))
		{
			return _bosses.get(bossId).getRaidStatus();
		}
		else if (_schedules.containsKey(bossId))
		{
			return StatusEnum.DEAD;
		}
		else
		{
			return StatusEnum.UNDEFINED;
		}
	}
	
	public L2NpcTemplate getValidTemplate(int bossId)
	{
		L2NpcTemplate template = NpcTable.getInstance().getTemplate(bossId);
		if (template == null)
		{
			return null;
		}
		if (!template.isType("L2RaidBoss"))
		{
			return null;
		}
		return template;
	}
	
	public void notifySpawnNightBoss(L2RaidBossInstance raidboss)
	{
		StatsSet info = new StatsSet();
		info.set("currentHP", raidboss.getCurrentHp());
		info.set("currentMP", raidboss.getCurrentMp());
		info.set("respawnTime", 0L);
		
		raidboss.setRaidStatus(StatusEnum.ALIVE);
		
		_storedInfo.put(raidboss.getNpcId(), info);
		
		_log.info(getClass().getSimpleName() + ": Spawning Night Raid Boss " + raidboss.getName());
		
		_bosses.put(raidboss.getNpcId(), raidboss);
	}
	
	public boolean isDefined(int bossId)
	{
		return _spawns.containsKey(bossId);
	}
	
	public Map<Integer, L2RaidBossInstance> getBosses()
	{
		return _bosses;
	}
	
	public Map<Integer, L2Spawn> getSpawns()
	{
		return _spawns;
	}
	
	public void reloadBosses()
	{
		init();
	}
	
	/**
	 * Saves and clears the raidbosses status, including all schedules.
	 */
	public void cleanUp()
	{
		//[JOJO]-------------------------------------------------
		if (_schedules != null)
		{
			for (ScheduledFuture<?> f : _schedules.values())
			{
				if (f != null)
				{
					f.cancel(true);
				}
			}
		}
		
		updateDb(_storedInfo.keySet());
		//-------------------------------------------------------

		_bosses.clear();
		
		if (_schedules != null)
		{
			_schedules.clear();
		}
		_storedInfo.clear();
		_spawns.clear();
	}
	
	private static class SingletonHolder
	{
		protected static final RaidBossSpawnManager _instance = new RaidBossSpawnManager();
	}
}
