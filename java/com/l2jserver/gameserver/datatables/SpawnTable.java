/*
 * Copyright (C) 2004-2013 L2J Server
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
package com.l2jserver.gameserver.datatables;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastSet;
import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.instancemanager.DayNightSpawnManager;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.interfaces.IL2Procedure;
import com.l2jserver.util.Util;

/**
 * Spawn data retriever.
 * @author Zoey76
 */
public final class SpawnTable
{
	private static final Logger _log = Logger.getLogger(SpawnTable.class.getName());
	// SQL
	private static final String SELECT_SPAWNS = "SELECT count, npc_templateid, locx, locy, locz, heading, respawn_delay, respawn_random, loc_id, periodOfDay FROM spawnlist";
	private static final String SELECT_CUSTOM_SPAWNS = "SELECT count, npc_templateid, locx, locy, locz, heading, respawn_delay, respawn_random, loc_id, periodOfDay FROM custom_spawnlist";
	
	private static final FastIntObjectMap< FastSet<L2Spawn>> _spawnTable = new FastIntObjectMap<FastSet<L2Spawn>>().shared();
	
	protected SpawnTable()
	{
		load();
	}
	
	/**
	 * Wrapper to load all spawns.
	 */
	public void load()
	{
		if (!Config.ALT_DEV_NO_SPAWNS)
		{
			fillSpawnTable(false);
			final int spawnCount = _spawnTable.size();
			_log.info(getClass().getSimpleName() + ": Loaded " + spawnCount + " npc spawns.");
			if (Config.CUSTOM_SPAWNLIST_TABLE)
			{
				fillSpawnTable(true);
				_log.info(getClass().getSimpleName() + ": Loaded " + (_spawnTable.size() - spawnCount) + " custom npc spawns.");
			}
		}
	}
	
	/**
	 * Retrieves spawn data from database.
	 * @param isCustom if {@code true} the spawns are loaded as custom from custom spawn table
	 * @return the spawn count
	 */
	private int fillSpawnTable(boolean isCustom)
	{
		int npcSpawnCount = 0;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(isCustom ? SELECT_CUSTOM_SPAWNS : SELECT_SPAWNS))
		{
			L2Spawn spawn;
			L2NpcTemplate npcTemplate;
			int npcId;
			while (rs.next())
			{
				npcId = rs.getInt("npc_templateid");
				npcTemplate = NpcTable.getInstance().getTemplate(npcId);
				if (npcTemplate == null)
				{
					_log.warning(getClass().getSimpleName() + ": Data missing in NPC table for ID: " + npcId + ".");
					continue;
				}
				
				if (npcTemplate.isType("L2SiegeGuard") || npcTemplate.isType("L2RaidBoss") || (!Config.ALLOW_CLASS_MASTERS && npcTemplate.isType("L2ClassMaster")))
				{
					// Don't spawn
					continue;
				}
				
				spawn = new L2Spawn(npcTemplate);
				spawn.setAmount(rs.getInt("count"));
				spawn.setLocx(rs.getInt("locx"));
				spawn.setLocy(rs.getInt("locy"));
				spawn.setLocz(rs.getInt("locz"));
				spawn.setHeading(rs.getInt("heading"));
				spawn.setRespawnDelay(rs.getInt("respawn_delay"), rs.getInt("respawn_random"));
				spawn.setCustom(isCustom);
				int loc_id = rs.getInt("loc_id");
				spawn.setLocation(loc_id);
				
				switch (rs.getInt("periodOfDay"))
				{
					case 0: // default
						npcSpawnCount += spawn.init();
						break;
					case 1: // Day
						DayNightSpawnManager.getInstance().addDayCreature(spawn);
						npcSpawnCount++;
						break;
					case 2: // Night
						DayNightSpawnManager.getInstance().addNightCreature(spawn);
						npcSpawnCount++;
						break;
				}
				
				addSpawn(spawn);
			}
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Spawn could not be initialized: " + e.getMessage(), e);
		}
		return npcSpawnCount;
	}
	
	public FastIntObjectMap<FastSet<L2Spawn>> getSpawnTable()
	{
		return _spawnTable;
	}
	
	/**
	 * Get the spawns for the NPC Id.
	 * @param npcId the NPC Id
	 * @return the spawn set for the given npcId
	 */
	public Set<L2Spawn> getSpawns(int npcId)
	{
		final FastSet<L2Spawn> t;
		return (t = _spawnTable.get(npcId)) != null ? t : Collections.<L2Spawn> emptySet();
	}
	
	/**
	 * Get the first NPC spawn.
	 * @param npcId the NPC Id to search
	 * @return the first not null spawn, if any
	 */
	public L2Spawn getFirstSpawn(int npcId)
	{
		FastSet<L2Spawn> t;
		if ((t = _spawnTable.get(npcId)) != null)
		{
			for (L2Spawn spawn : t)
			{
				if (spawn != null)
				{
					return spawn;
				}
			}
		}
		return null;
	}
	
	/**
	 * Add a new spawn to the spawn table.
	 * @param spawn the spawn to add
	 * @param storeInDb if {@code true} it'll be saved in the database
	 */
	public void addNewSpawn(L2Spawn spawn, boolean storeInDb)
	{
		addSpawn(spawn);
		
		if (storeInDb)
		{
			final String spawnTable = spawn.isCustom() && Config.CUSTOM_SPAWNLIST_TABLE ? "custom_spawnlist" : "spawnlist";
			try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement insert = con.prepareStatement("INSERT INTO " + spawnTable + "(count,npc_templateid,locx,locy,locz,heading,respawn_delay,respawn_random,loc_id) values(?,?,?,?,?,?,?,?,?)"))
			{
				insert.setInt(1, spawn.getAmount());
				insert.setInt(2, spawn.getNpcid());
				insert.setInt(3, spawn.getLocx());
				insert.setInt(4, spawn.getLocy());
				insert.setInt(5, spawn.getLocz());
				insert.setInt(6, spawn.getHeading());
				insert.setInt(7, spawn.getRespawnDelay() / 1000);
				insert.setInt(8, spawn.getRespawnMaxDelay() - spawn.getRespawnMinDelay());
				insert.setInt(9, spawn.getLocation());
				insert.execute();
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, getClass().getSimpleName() + ": Could not store spawn in the DB:" + e.getMessage(), e);
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
	
	/**
	 * Delete an spawn from the spawn table.
	 * @param spawn the spawn to delete
	 * @param updateDb if {@code true} database will be updated
	 */
	public void deleteSpawn(L2Spawn spawn, boolean updateDb)
	{
		if (!removeSpawn(spawn))
		{
			return;
		}
		
		if (updateDb)
		{
			try (Connection con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement delete = con.prepareStatement("DELETE FROM " + (spawn.isCustom() ? "custom_spawnlist" : "spawnlist") + " WHERE locx=? AND locy=? AND locz=? AND npc_templateid=? AND heading=?"))
			{
				delete.setInt(1, spawn.getLocx());
				delete.setInt(2, spawn.getLocy());
				delete.setInt(3, spawn.getLocz());
				delete.setInt(4, spawn.getNpcid());
				delete.setInt(5, spawn.getHeading());
				delete.execute();
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, getClass().getSimpleName() + ": Spawn " + spawn + " could not be removed from DB: " + e.getMessage(), e);
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
	
	/**
	 * Add a spawn to the spawn set if present, otherwise add a spawn set and add the spawn to the newly created spawn set.
	 * @param spawn the NPC spawn to add
	 */
	private void addSpawn(L2Spawn spawn)
	{
		FastSet<L2Spawn> test;
//TODO:	synchronized (_spawnTable)
//TODO:	{
			if ((test = _spawnTable.get(spawn.getNpcid())) == null)
			{
				test = new FastSet<L2Spawn>().shared();
				_spawnTable.put(spawn.getNpcid(), test);
			}
//TODO:	}
		test.add(spawn);
	}
	
	/**
	 * Remove a spawn from the spawn set, if the spawn set is empty, remove it as well.
	 * @param spawn the NPC spawn to remove
	 * @return {@code true} if the spawn was successfully removed, {@code false} otherwise
	 */
	private boolean removeSpawn(L2Spawn spawn)
	{
		FastSet<L2Spawn> set;
		if ((set = _spawnTable.get(spawn.getNpcid())) != null)
		{
//TODO:	synchronized (_spawnTable)
//TODO:	{
			boolean removed = set.remove(spawn);
			if (set.isEmpty())
			{
				_spawnTable.remove(spawn.getNpcid());
			}
			return removed;
//TODO:	}
		}
		return false;
	}
	
	/**
	 * Execute a procedure over all spawns.<br>
	 * <font size="4" color="red">Do not use it!</font>
	 * @param procedure the procedure to execute
	 * @return {@code true} if all procedures were executed, {@code false} otherwise
	 */
	public boolean forEachSpawn(IL2Procedure<L2Spawn> procedure)
	{
		for (Set<L2Spawn> set : _spawnTable.values())
		{
			for (L2Spawn spawn : set)
			{
				if (!procedure.execute(spawn))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void addNewSpawnOne(L2Npc npc)	//+[JOJO] Orfen, Zaken
	{
		Set<L2Spawn> t;
		if ((t = _spawnTable.get(npc.getNpcId())) != null) t.clear();
		addNewSpawn(npc.getSpawn(), false);
	}
	public void deleteSpawnOne(L2Npc npc)	//+[JOJO]
	{
		_spawnTable.remove(npc.getSpawn().getNpcid());
	}
	public L2Spawn getSpawnOne(int npcId)	//+[JOJO]
	{
		Set<L2Spawn> t;
		if ((t = _spawnTable.get(npcId)) != null && t.size() > 0)
		{
			return t.iterator().next();
		}
		return null;
	}
	
    /**
     * Write to "log/_spawnlist.sql" the SQL.
     */
    private static void addSqlLog(String sql)	//+[JOJO]
    {
	    System.out.print(sql);
	    try {
			BufferedWriter save = Util.utf8BufferedWriter("log/_spawnlist.sql", true); // [JOJO] UTF-8
			save.write(sql);
			save.flush();
			save.close();
	    } catch (java.io.IOException e) {
	    }
    }
	
	public static SpawnTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final SpawnTable _instance = new SpawnTable();
	}
}
