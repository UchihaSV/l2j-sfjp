/*
 * Copyright (C) 2004-2014 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;
import jp.sf.l2j.troja.FastIntObjectMap;
import jp.sf.l2j.troja.IntObjectMap;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.NpcData;
import com.l2jserver.gameserver.instancemanager.tasks.GrandBossManagerStoreTask;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.instance.L2GrandBossInstance;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.interfaces.IStorable;
import com.l2jserver.gameserver.model.zone.type.L2BossZone;

import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

/**
 * @author DaRkRaGe Revised by Emperorc
 */
public final class GrandBossManager implements IStorable
{
	/* =========================================================
	 * This class handles all Grand Bosses:
	 * <ul>
	 * <li>25333-25338  Anakazel</li>
	 * <li>29001        Queen Ant</li>
	 * <li>29006        Core</li>
	 * <li>29014        Orfen</li>
	 * <li>29019        Antharas</li>
	 * <li>29020        Baium</li>
	 * <li>29022        Zaken</li>
	 * <li>29028        Valakas</li>
	 * <li>29045        Frintezza</li>
	 * <li><DEL>29046-29047  Scarlet van Halisha</DEL></li>
	 * <li>29054        Benom</li>
	 * <li>29062        High Priestess van Halter</li>
	 * <li>29065        Sailren</li>
	 * <li>29066        Antharas Weak</li>
	 * <li>29067        Antharas Normal</li>
	 * <li>29068        Antharas Strong</li>
	 * <li><DEL>29099        Baylor</DEL></li>
	 * </ul>
	 *
	 * It handles the saving of hp, mp, location, and status
	 * of all Grand Bosses. It also manages the zones associated
	 * with the Grand Bosses.
	 * NOTE: The current version does NOT spawn the Grand Bosses,
	 * it just stores and retrieves the values on reboot/startup,
	 * for AI scripts to utilize as needed.
	*/
	/* useful query:
SELECT npc.name, grandboss_data.*, IF(grandboss_data.respawn_time > 0, FROM_UNIXTIME(grandboss_data.respawn_time / 1000, '%Y-%m-%d %h:%m (%a)' ), '-' ) AS respawn_time
 FROM grandboss_data LEFT JOIN npc ON (grandboss_data.boss_id = npc.id);
	 */
	
	// SQL queries
	private static final String DELETE_GRAND_BOSS_LIST = "TRUNCATE TABLE grandboss_list";	//[JOJO] -DELETE FROM
	private static final String INSERT_GRAND_BOSS_LIST = "INSERT INTO grandboss_list (player_id,zone) VALUES (?,?)";
	private static final String UPDATE_GRAND_BOSS_DATA = "UPDATE grandboss_data set loc_x = ?, loc_y = ?, loc_z = ?, heading = ?, respawn_time = ?, currentHP = ?, currentMP = ?, status = ? where boss_id = ?";
	private static final String UPDATE_GRAND_BOSS_DATA2 = "UPDATE grandboss_data set status = ? where boss_id = ?";
	private static final String UPDATE_GRAND_BOSS_DATA3 = "UPDATE grandboss_data set respawn_time = ?, status = ? where boss_id = ?";	//+[JOJO]
	
	protected static Logger _log = Logger.getLogger(GrandBossManager.class.getName());
	
	protected static FastIntObjectMap<L2GrandBossInstance> _bosses;
	
	protected static TIntObjectHashMap<StatsSet> _storedInfo;
	
	private TIntIntHashMap _bossStatus;
	
	private FastList/*L2FastList*/<L2BossZone> _zones;
	
	protected GrandBossManager()
	{
		init();
	}
	
	private void init()
	{
		_zones = new FastList/*L2FastList*/<>();
		
		_bosses = new FastIntObjectMap<>();
		_storedInfo = new TIntObjectHashMap<>();
		_bossStatus = new TIntIntHashMap();
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from grandboss_data ORDER BY boss_id"))
		{
			while (rs.next())
			{
				// Read all info from DB, and store it for AI to read and decide what to do
				// faster than accessing DB in real time
				StatsSet info = new StatsSet();
				int bossId = rs.getInt("boss_id");
				info.set("loc_x", rs.getInt("loc_x"));
				info.set("loc_y", rs.getInt("loc_y"));
				info.set("loc_z", rs.getInt("loc_z"));
				info.set("heading", rs.getInt("heading"));
				info.set("respawn_time", rs.getLong("respawn_time"));
				double HP = rs.getDouble("currentHP"); // jython doesn't recognize doubles
				int true_HP = (int) HP; // so use java's ability to type cast
				info.set("currentHP", true_HP); // to convert double to int
				double MP = rs.getDouble("currentMP");
				int true_MP = (int) MP;
				info.set("currentMP", true_MP);
				int status = rs.getInt("status");
				_bossStatus.put(bossId, status);
				_storedInfo.put(bossId, info);
				_log.info(getClass().getSimpleName() + ": " + NpcData.getInstance().getTemplate(bossId).getName() + "(" + bossId + ") status is " + status + ".");
				if (status > 0)
				{
					_log.info(getClass().getSimpleName() + ": Next spawn date of " + NpcData.getInstance().getTemplate(bossId).getName() + " is " + respawnTimeFormat(info) + ".");
				}
			}
			_log.info(getClass().getSimpleName() + ": Loaded " + _storedInfo.size() + " Instances");
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Could not load grandboss_data table: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Error while initializing GrandBossManager: " + e.getMessage(), e);
		}
		ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new GrandBossManagerStoreTask(), 5 * 60 * 1000, 5 * 60 * 1000);
	}
	
	/**
	 * Zone Functions
	 */
	public void initZones()
	{
		FastIntObjectMap<FastIntObjectMap<Object>/*L2FastList*/> zones = new FastIntObjectMap<>();
		
		if (_zones == null)
		{
			_log.warning(getClass().getSimpleName() + ": Could not read Grand Boss zone data");
			return;
		}
		
		for (L2BossZone zone : _zones)
		{
			if (zone == null)
			{
				continue;
			}
			zones.put(zone.getId(), new FastIntObjectMap<>/*L2FastList*/());
		}
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT * from grandboss_list"))	//[JOJO] -ORDER BY player_id
		{
			while (rs.next())
			{
				int id = rs.getInt("player_id");
				int zone_id = rs.getInt("zone");
				zones.get(zone_id).put(id, Boolean.TRUE);
			}
			_log.info(getClass().getSimpleName() + ": Initialized " + _zones.size() + " Grand Boss Zones");
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Could not load grandboss_list table: " + e.getMessage(), e);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Error while initializing GrandBoss zones: " + e.getMessage(), e);
		}
		
		for (L2BossZone zone : _zones)
		{
			if (zone == null)
			{
				continue;
			}
			zone.setAllowedPlayers(zones.get(zone.getId()));
		}
		
		zones.clear();
	}
	
	public void addZone(L2BossZone zone)
	{
		if (_zones != null)
		{
			_zones.add(zone);
		}
	}
	
	public final L2BossZone getZone(int zoneId)
	{
		if (_zones != null)
		{
			for (L2BossZone temp : _zones)
			{
				if (temp.getId() == zoneId)
				{
					return temp;
				}
			}
		}
		return null;
	}
	
	public final L2BossZone getZone(L2Character character)
	{
		if (_zones != null)
		{
			for (L2BossZone temp : _zones)
			{
				if (temp.isCharacterInZone(character))
				{
					return temp;
				}
			}
		}
		return null;
	}
	
	public final L2BossZone getZone(Location loc)
	{
		return getZone(loc.getX(), loc.getY(), loc.getZ());
	}
	
	public final L2BossZone getZone(int x, int y, int z)
	{
		if (_zones != null)
		{
			for (L2BossZone temp : _zones)
			{
				if (temp.isInsideZone(x, y, z))
				{
					return temp;
				}
			}
		}
		return null;
	}
	
	@Deprecated
	public boolean checkIfInZone(String zoneType, L2Object obj)
	{
		L2BossZone temp = getZone(obj.getX(), obj.getY(), obj.getZ());
		if (temp == null)
		{
			return false;
		}
		
		return temp.getName().equalsIgnoreCase(zoneType);
	}
	
	public boolean checkIfInZone(L2PcInstance player)
	{
		if (player == null)
		{
			return false;
		}
		L2BossZone temp = getZone(player.getX(), player.getY(), player.getZ());
		if (temp == null)
		{
			return false;
		}
		
		return true;
	}
	
	public int getBossStatus(int bossId)
	{
		return _bossStatus.get(bossId);
	}
	
	public void setBossStatus(int bossId, int status)
	{
		_bossStatus.put(bossId, status);
		_log.info(getClass().getSimpleName() + ": Updated " + NpcData.getInstance().getTemplate(bossId).getName() + "(" + bossId + ") status to " + status);
		updateDb(bossId, true);
	}
	
	/**
	 * Adds a L2GrandBossInstance to the list of bosses.
	 * @param boss
	 */
	public void addBoss(L2GrandBossInstance boss) //Note: AI script.
	{
		if (boss != null)
		{
			_bosses.put(boss.getId(), boss);
		}
	}
	
	public L2GrandBossInstance getBoss(int bossId)
	{
		return _bosses.get(bossId);
	}
	
	public StatsSet getStatsSet(int bossId)
	{
		return _storedInfo.get(bossId);
	}
	
	public void setStatsSet(int bossId, StatsSet info)
	{
		_storedInfo.put(bossId, info);
		updateDb(bossId, false);
	}
	
	@Override
	public boolean storeMe()
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			try (PreparedStatement delete = con.prepareStatement(DELETE_GRAND_BOSS_LIST))
			{
				delete.executeUpdate();
			}
			
			try (PreparedStatement insert = con.prepareStatement(INSERT_GRAND_BOSS_LIST))
			{
				for (L2BossZone zone : _zones)
				{
					if (zone == null)
					{
						continue;
					}
					int id = zone.getId();
					FastIntObjectMap<Object> list = zone.getAllowedPlayers();
					if ((list == null) || list.isEmpty())
					{
						continue;
					}
					for (IntObjectMap.Entry<Object> e : list.entrySet())
					{
						insert.setInt(1, e.getKey());	// player object ID
						insert.setInt(2, id);			// zone ID
						insert.executeUpdate();
						insert.clearParameters();
					}
				}
			}
			try (PreparedStatement update_grand_boss_data2 = con.prepareStatement(UPDATE_GRAND_BOSS_DATA2);
			     PreparedStatement update_grand_boss_data3 = con.prepareStatement(UPDATE_GRAND_BOSS_DATA3);
			     PreparedStatement update_grand_boss_data1 = con.prepareStatement(UPDATE_GRAND_BOSS_DATA) )
			{
				for (int bossId : _storedInfo.keys())
				{
					final L2GrandBossInstance boss = _bosses.get(bossId);
					StatsSet info = _storedInfo.get(bossId);
					if ( boss == null  &&  info == null )
				//	if ((boss == null) || (info == null))
					{
						PreparedStatement update = update_grand_boss_data2;
						update.setInt(1, _bossStatus.get(bossId));
						update.setInt(2, bossId);
						update.executeUpdate();
						update.clearParameters();
					}
					else if (boss == null && info != null)
					{
						PreparedStatement update = update_grand_boss_data3;
						update.setLong(1, info.getLong("respawn_time"));
						update.setInt(2, _bossStatus.get(bossId));
						update.setInt(3, bossId);
						update.executeUpdate();
						update.clearParameters();
					}
					else if (boss != null && info != null)
					{
						PreparedStatement update = update_grand_boss_data1;
						update.setInt(1, boss.getX());
						update.setInt(2, boss.getY());
						update.setInt(3, boss.getZ());
						update.setInt(4, boss.getHeading());
						update.setLong(5, info.getLong("respawn_time"));
						double hp = boss.getCurrentHp();
						double mp = boss.getCurrentMp();
						if (boss.isDead())
						{
							hp = boss.getMaxHp();
							mp = boss.getMaxMp();
						}
						update.setDouble(6, hp);
						update.setDouble(7, mp);
						update.setInt(8, _bossStatus.get(bossId));
						update.setInt(9, bossId);
						update.executeUpdate();
						update.clearParameters();
					}
					else /*if (boss != null && info == null)*/
					{
						throw new RuntimeException();
					}
				}
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't store grandbosses to database:" + e.getMessage(), e);
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private void updateDb(int bossId, boolean statusOnly)
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			L2GrandBossInstance boss = _bosses.get(bossId);
			StatsSet info = _storedInfo.get(bossId);
			
			if (statusOnly || (boss == null && info == null))
		//	if (statusOnly || (boss == null) || (info == null))
			{
				try (PreparedStatement ps = con.prepareStatement(UPDATE_GRAND_BOSS_DATA2))
				{
					ps.setInt(1, _bossStatus.get(bossId));
					ps.setInt(2, bossId);
					ps.executeUpdate();
				}
			}
			else if (boss == null && info != null)
			{
				try (PreparedStatement statement = con.prepareStatement(UPDATE_GRAND_BOSS_DATA3))
				{
					statement.setLong(1, info.getLong("respawn_time"));
					statement.setInt(2, _bossStatus.get(bossId));
					statement.setInt(3, bossId);
					statement.executeUpdate();
				}
			}
			else if (boss != null && info != null)
			{
				try (PreparedStatement ps = con.prepareStatement(UPDATE_GRAND_BOSS_DATA))
				{
					ps.setInt(1, boss.getX());
					ps.setInt(2, boss.getY());
					ps.setInt(3, boss.getZ());
					ps.setInt(4, boss.getHeading());
					ps.setLong(5, info.getLong("respawn_time"));
					double hp = boss.getCurrentHp();
					double mp = boss.getCurrentMp();
					if (boss.isDead())
					{
						hp = boss.getMaxHp();
						mp = boss.getMaxMp();
					}
					ps.setDouble(6, hp);
					ps.setDouble(7, mp);
					ps.setInt(8, _bossStatus.get(bossId));
					ps.setInt(9, bossId);
					ps.executeUpdate();
				}
			}
			else /*if (boss != null && info == null)*/
			{
				throw new RuntimeException();
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't update grandbosses to database:" + e.getMessage(), e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Saves all Grand Boss info and then clears all info from memory, including all schedules.
	 */
	public void cleanUp()
	{
		storeMe();
		
		_bosses.clear();
		_storedInfo.clear();
		_bossStatus.clear();
		_zones.clear();
	}
	
	public FastList/*L2FastList*/<L2BossZone> getZones()
	{
		return _zones;
	}

	public static String respawnTimeFormat(StatsSet info)	//+[JOJO]
	{
		return com.l2jserver.util.Util.dateFormat(info.getLong("respawn_time"));
	}
	
	/**
	 * Gets the single instance of {@code GrandBossManager}.
	 * @return single instance of {@code GrandBossManager}
	 */
	public static GrandBossManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final GrandBossManager _instance = new GrandBossManager();
	}
}
