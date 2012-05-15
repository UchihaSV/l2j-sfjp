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
package com.l2jserver.gameserver.datatables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.io.UTF8StreamWriter;
import javolution.util.FastSet;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.instancemanager.DayNightSpawnManager;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.util.Util;

/**
 * This class ...
 *
 * @author Nightmare
 * @version $Revision: 1.5.2.6.2.7 $ $Date: 2005/03/27 15:29:18 $
 */
public class SpawnTable
{
	private static Logger _log = Logger.getLogger(SpawnTable.class.getName());
	
	private FastSet<L2Spawn> _spawntable = new FastSet<L2Spawn>().shared();
	private int _npcSpawnCount;
	private int _customSpawnCount;
	
	public static SpawnTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected SpawnTable()
	{
		if (!Config.ALT_DEV_NO_SPAWNS)
			fillSpawnTable();
	}
	
	public FastSet<L2Spawn> getSpawnTable()
	{
		return _spawntable;
	}
	
	private void fillSpawnTable()
	{
		Connection con = null;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT count, npc_templateid, locx, locy, locz, heading, respawn_delay, loc_id, periodOfDay FROM spawnlist");
			ResultSet rset = statement.executeQuery();
			
			L2Spawn spawnDat;
			L2NpcTemplate template1;
			
			while (rset.next())
			{
				template1 = NpcTable.getInstance().getTemplate(rset.getInt("npc_templateid"));
				if (template1 != null)
				{
					if (template1.isType("L2SiegeGuard"))
					{
						// Don't spawn
					}
					else if (template1.isType("L2RaidBoss"))
					{
						// Don't spawn raidboss
					}
					else if (!Config.ALLOW_CLASS_MASTERS && template1.isType("L2ClassMaster"))
					{
						// Dont' spawn class masters
					}
					else
					{
						spawnDat = new L2Spawn(template1);
						spawnDat.setAmount(rset.getInt("count"));
						spawnDat.setLocx(rset.getInt("locx"));
						spawnDat.setLocy(rset.getInt("locy"));
						spawnDat.setLocz(rset.getInt("locz"));
						spawnDat.setHeading(rset.getInt("heading"));
						spawnDat.setRespawnDelay(rset.getInt("respawn_delay"));
						int loc_id = rset.getInt("loc_id");
						spawnDat.setLocation(loc_id);
						
						switch (rset.getInt("periodOfDay"))
						{
							case 0: // default
								_npcSpawnCount += spawnDat.init();
								break;
							case 1: // Day
								DayNightSpawnManager.getInstance().addDayCreature(spawnDat);
								_npcSpawnCount++;
								break;
							case 2: // Night
								DayNightSpawnManager.getInstance().addNightCreature(spawnDat);
								_npcSpawnCount++;
								break;
						}
						
						_spawntable.add(spawnDat);
					}
				}
				else
				{
					_log.warning("SpawnTable: Data missing in NPC table for ID: " + rset.getInt("npc_templateid") + ".");
				}
			}
			rset.close();
			statement.close();
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "SpawnTable: Spawn could not be initialized: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		
		_log.info("SpawnTable: Loaded " + _spawntable.size() + " Npc Spawn Locations.");
		
		if (Config.CUSTOM_SPAWNLIST_TABLE)
		{
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("SELECT count, npc_templateid, locx, locy, locz, heading, respawn_delay, loc_id, periodOfDay FROM custom_spawnlist");
				ResultSet rset = statement.executeQuery();
				
				L2Spawn spawnDat;
				L2NpcTemplate template1;
				
				while (rset.next())
				{
					template1 = NpcTable.getInstance().getTemplate(rset.getInt("npc_templateid"));
					if (template1 != null)
					{
						if (template1.isType("L2SiegeGuard"))
						{
							// Don't spawn
						}
						else if (template1.isType("L2RaidBoss"))
						{
							// Don't spawn raidboss
						}
						else if (!Config.ALLOW_CLASS_MASTERS && template1.isType("L2ClassMaster"))
						{
							// Dont' spawn class masters
						}
						else
						{
							spawnDat = new L2Spawn(template1);
							spawnDat.setAmount(rset.getInt("count"));
							spawnDat.setLocx(rset.getInt("locx"));
							spawnDat.setLocy(rset.getInt("locy"));
							spawnDat.setLocz(rset.getInt("locz"));
							spawnDat.setHeading(rset.getInt("heading"));
							spawnDat.setRespawnDelay(rset.getInt("respawn_delay"));
							spawnDat.setCustom(true);
							int loc_id = rset.getInt("loc_id");
							spawnDat.setLocation(loc_id);
							
							switch (rset.getInt("periodOfDay"))
							{
								case 0: // default
									_customSpawnCount += spawnDat.init();
									break;
								case 1: // Day
									DayNightSpawnManager.getInstance().addDayCreature(spawnDat);
									_customSpawnCount++;
									break;
								case 2: // Night
									DayNightSpawnManager.getInstance().addNightCreature(spawnDat);
									_customSpawnCount++;
									break;
							}
							
							_spawntable.add(spawnDat);
						}
					}
					else
					{
						_log.warning("CustomSpawnTable: Data missing in NPC table for ID: " + rset.getInt("npc_templateid") + ".");
					}
				}
				rset.close();
				statement.close();
			}
			catch (Exception e)
			{
				// problem with initializing spawn, go to next one
				_log.log(Level.WARNING, "CustomSpawnTable: Spawn could not be initialized: " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			_log.info("CustomSpawnTable: Loaded " + _customSpawnCount + " Npc Spawn Locations.");
			
		}
		
		if (Config.DEBUG)
			_log.fine("SpawnTable: Spawning completed, total number of NPCs in the world: " + (_npcSpawnCount + _customSpawnCount));
		
	}
	
	public void addNewSpawn(L2Spawn spawn, boolean storeInDb)
	{
		_spawntable.add(spawn);
		
		if (storeInDb)
		{
			String spawnTable;
			if (spawn.isCustom() && Config.CUSTOM_SPAWNLIST_TABLE)
				spawnTable = "custom_spawnlist";
			else
				spawnTable = "spawnlist";
			
			Connection con = null;
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO " + spawnTable
						+ "(count,npc_templateid,locx,locy,locz,heading,respawn_delay,loc_id) values(?,?,?,?,?,?,?,?)");
				statement.setInt(1, spawn.getAmount());
				statement.setInt(2, spawn.getNpcid());
				statement.setInt(3, spawn.getLocx());
				statement.setInt(4, spawn.getLocy());
				statement.setInt(5, spawn.getLocz());
				statement.setInt(6, spawn.getHeading());
				statement.setInt(7, spawn.getRespawnDelay() / 1000);
				statement.setInt(8, spawn.getLocation());
				statement.execute();
				statement.close();
			}
			catch (Exception e)
			{
				// problem with storing spawn
				_log.log(Level.WARNING, "SpawnTable: Could not store spawn in the DB:" + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			
			//[JOJO]
			String zoneName = MapRegionManager.getInstance().getClosestTownName(spawn.getLocx(),spawn.getLocy());
			addSqlLog("-- " + Util.dateFormat() + " " + spawn.getTemplate().getTitle() + " " + spawn.getTemplate().getName() + " - " + zoneName + "\r\n"
					+ "INSERT INTO " + spawnTable + " SET location='//spawn." + zoneName + "'"
					+ ", count=" + spawn.getAmount()
					+ ", npc_templateid=" + spawn.getNpcid()
					+ ", locx=" + spawn.getLocx()
					+ ", locy=" + spawn.getLocy()
					+ ", locz=" + spawn.getLocz()
					+ ", heading=" + spawn.getHeading()
					+ ", respawn_delay=" + (spawn.getRespawnDelay() / 1000)
					+ ", loc_id=" + spawn.getLocation()
					+ ";\r\n");
			//[JOJO]
		}
	}
	
	public void deleteSpawn(L2Spawn spawn, boolean updateDb)
	{
		
		if (!_spawntable.remove(spawn))
			return;
		
		if (updateDb)
		{
			Connection con = null;
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("DELETE FROM "
						+ (spawn.isCustom() ? "custom_spawnlist" : "spawnlist") + " WHERE locx=? AND locy=? AND locz=? AND npc_templateid=? AND heading=?");
				statement.setInt(1, spawn.getLocx());
				statement.setInt(2, spawn.getLocy());
				statement.setInt(3, spawn.getLocz());
				statement.setInt(4, spawn.getNpcid());
				statement.setInt(5, spawn.getHeading());
				statement.execute();
				statement.close();
			}
			catch (Exception e)
			{
				// problem with deleting spawn
				_log.log(Level.WARNING, "SpawnTable: Spawn " + spawn + " could not be removed from DB: " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			
			//[JOJO]
			String zoneName = MapRegionManager.getInstance().getClosestTownName(spawn.getLocx(),spawn.getLocy());
			addSqlLog("-- " + Util.dateFormat() + " " + spawn.getTemplate().getTitle() + " " + spawn.getTemplate().getName() + " - " + zoneName + "\r\n"
					+ "DELETE FROM "
					+ (spawn.isCustom() ? "custom_spawnlist" : "spawnlist")
					+ " WHERE locx=" + spawn.getLocx()
					+ " AND locy=" + spawn.getLocy()
					+ " AND locz=" + spawn.getLocz()
					+ " AND npc_templateid=" + spawn.getNpcid()
					+ " AND heading=" + spawn.getHeading()
					+ ";\r\n");
			//[JOJO]
		}
	}
	
	//just wrapper
	public void reloadAll()
	{
		fillSpawnTable();
	}
	
	/**
	 * Get all the spawn of a NPC<BR><BR>
	 * @param activeChar
	 * @param npcId
	 * @param teleportIndex
	 * @param showposition
	 */
	public void findNPCInstances(L2PcInstance activeChar, int npcId, int teleportIndex, boolean showposition)
	{
		int index = 0;
		for (L2Spawn spawn : _spawntable)
		{
			
			if (npcId == spawn.getNpcid())
			{
				index++;
				L2Npc _npc = spawn.getLastSpawn();
				if (teleportIndex > -1)
				{
					if (teleportIndex == index)
					{
						if(showposition && _npc != null)
							meet(activeChar, spawn);
				//			activeChar.teleToLocation(_npc.getX(), _npc.getY(), _npc.getZ(), true);
						else
							activeChar.teleToLocation(spawn.getLocx(), spawn.getLocy(), spawn.getLocz(), true);
					}
				}
				else
				{
					if (index == 1)
						activeChar.sendMessage(spawn.getNpcid() + " " + spawn.getTemplate().getName());
					if(showposition && _npc != null)
						activeChar.sendMessage("- " + index + ". " + _npc.getX() + " "+ _npc.getY() + " " + _npc.getZ());
				//		activeChar.sendMessage(index + " - " + spawn.getTemplate().getName() + " (" + spawn + "): " + _npc.getX() + " "+ _npc.getY() + " " + _npc.getZ());
					else
						activeChar.sendMessage("- " + index + ". " + spawn.getLocx() + " "+ spawn.getLocy() + " " + spawn.getLocz());
				//		activeChar.sendMessage(index + " - " + spawn.getTemplate().getName() + " (" + spawn + "): " + spawn.getLocx() + " "+ spawn.getLocy() + " " + spawn.getLocz());
				}
			}
		}
		
		if (index == 0)
			activeChar.sendMessage("No current spawns found.");
	}
	
	private void meet(L2PcInstance activeChar, L2Spawn spawn)	//+[JOJO]
	{
		int x, y, z/*, h*/;
		L2Npc npc;
		if ((npc = spawn.getLastSpawn()) != null && npc.isVisible())
		{
			double a = npc.getHeading() * (Math.PI / 32768.0); //convert heading to radian
			double r = Math.max(npc.getCollisionRadius(), npc.getCollisionHeight())
			         + activeChar.getCollisionRadius()
			         + 60;
			x = npc.getX() + (int)Math.ceil(r * Math.cos(a));
			y = npc.getY() + (int)Math.ceil(r * Math.sin(a));
			z = npc.getZ();
//			h = (npc.getHeading() + 32768) % 65536;
		}
		else
		{
			x = spawn.getLocx();
			y = spawn.getLocy();
			z = spawn.getLocz();
//			h = spawn.getHeading();
		}
		activeChar.teleToLocation(x, y, z, false);
//		activeChar.setHeading(h);								//TODO:
//		activeChar.getPosition().setHeading(h);					//TODO:
	}

	public void addNewSpawnOne(L2Npc npc)	//+[JOJO] Orfen, Zaken
	{
		int npcId = npc.getNpcId();
		for (Iterator<L2Spawn> it = _spawntable.iterator(); it.hasNext(); )
		{
			L2Spawn spawn = it.next();
			if (spawn.getNpcid() == npcId)
				it.remove();
		}
		addNewSpawn(npc.getSpawn(), false);
	}
	public void deleteSpawnOne(L2Npc npc)	//+[JOJO]
	{
		_spawntable.remove(npc.getSpawn());
	}
	public L2Spawn getSpawnOne(int npcId)	//+[JOJO]
	{
		for (L2Spawn spawn : _spawntable)
			if (spawn.getNpcid() == npcId)
				return spawn;
		return null;
	}

    /**
     * Write to "log/_spawnlist.sql" the SQL.
     */
    private static void addSqlLog(String sql)	//+[JOJO]
    {
	    System.out.print(sql);
	    try {
			UTF8StreamWriter save = Util.utf8StreamWriter("log/_spawnlist.sql", true); // [JOJO] UTF-8
			save.write(sql);
			save.flush();
			save.close();
	    } catch (java.io.IOException e) {
	    }
    }
	
	private static class SingletonHolder
	{
		protected static final SpawnTable _instance = new SpawnTable();
	}
}
