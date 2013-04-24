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
package com.l2jserver.gameserver.instancemanager;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastSet;

import com.l2jserver.gameserver.GameTimeController;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2RaidBossInstance;

/**
 * @author godson
 */
public class DayNightSpawnManager
{
	
	private static Logger _log = Logger.getLogger(DayNightSpawnManager.class.getName());
	
	private final ArrayList<L2Spawn> _dayCreatures;
	private final ArrayList<L2Spawn> _nightCreatures;
	private final FastSet<L2Spawn> _nightBosses;
	
	public static DayNightSpawnManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected DayNightSpawnManager()
	{
		_dayCreatures = new ArrayList<>();
		_nightCreatures = new ArrayList<>();
		_nightBosses = new FastSet<>();
	}
	
	public void addDayCreature(L2Spawn spawnDat)
	{
		_dayCreatures.add(spawnDat);
	}
	
	public void addNightCreature(L2Spawn spawnDat)
	{
		_nightCreatures.add(spawnDat);
	}
	
	/**
	 * Spawn Day Creatures, and Unspawn Night Creatures
	 */
	public void spawnDayCreatures()
	{
		spawnCreatures(_nightCreatures, _dayCreatures, "night", "day");
	}
	
	/**
	 * Spawn Night Creatures, and Unspawn Day Creatures
	 */
	public void spawnNightCreatures()
	{
		spawnCreatures(_dayCreatures, _nightCreatures, "day", "night");
	}
	
	/**
	 * Manage Spawn/Respawn
	 * @param unSpawnCreatures List with spawns must be unspawned
	 * @param spawnCreatures List with spawns must be spawned
	 * @param UnspawnLogInfo String for log info for unspawned L2NpcInstance
	 * @param SpawnLogInfo String for log info for spawned L2NpcInstance
	 */
	private void spawnCreatures(List<L2Spawn> unSpawnCreatures, List<L2Spawn> spawnCreatures, String UnspawnLogInfo, String SpawnLogInfo)
	{
		try
		{
			if (!unSpawnCreatures.isEmpty())
			{
				int i = 0;
				for (L2Spawn spawn : unSpawnCreatures)
				{
					if (spawn == null)
					{
						continue;
					}
					
					spawn.stopRespawn();
					L2Npc last = spawn.getLastSpawn();
					if (last != null)
					{
						last.deleteMe();
						i++;
					}
				}
				_log.info("DayNightSpawnManager: Removed " + i + " " + UnspawnLogInfo + " creatures");
			}
			
			int i = 0;
			for (L2Spawn spawnDat : spawnCreatures)
			{
				if (spawnDat == null)
				{
					continue;
				}
				spawnDat.startRespawn();
				spawnDat.doSpawn();
				i++;
			}
			
			_log.info("DayNightSpawnManager: Spawned " + i + " " + SpawnLogInfo + " creatures");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Error while spawning creatures: " + e.getMessage(), e);
		}
	}
	
	private void changeMode(boolean isNight)
	{
		if (_nightCreatures.isEmpty() && _dayCreatures.isEmpty())
		{
			return;
		}
		
		if (isNight)
			spawnNightCreatures();
		else
			spawnDayCreatures();
	}
	
	public DayNightSpawnManager trim()
	{
		_nightCreatures.trimToSize();
		_dayCreatures.trimToSize();
		return this;
	}
	
	public void notifyChangeMode()
	{
		try
		{
			boolean isNight = GameTimeController.getInstance().isNight();
			changeMode(isNight);
			changeBoss(isNight);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Error while notifyChangeMode(): " + e.getMessage(), e);
		}
	}
	
	public void cleanUp()
	{
		_nightCreatures.clear();
		_dayCreatures.clear();
		_nightBosses.clear();
	}
	
	private void changeBoss(boolean isNight)
	{
		try
		{
			for (L2Spawn spawn : _nightBosses)
			{
				L2RaidBossInstance raidboss = (L2RaidBossInstance) spawn.getLastSpawn();
				
				if (isNight)
				{
					if (raidboss != null && raidboss.isVisible())
						continue;
					if (RaidBossSpawnManager.getInstance().getRaidBossStatusId(spawn.getNpcid()) == RaidBossSpawnManager.StatusEnum.DEAD)
						continue;
					raidboss = (L2RaidBossInstance) spawn.doSpawn();
					RaidBossSpawnManager.getInstance().notifySpawnNightBoss(raidboss);
				}
				else
				{
					if (raidboss == null)
						continue;
					raidboss.deleteMe();
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Error while changeNightBoss(): " + e.getMessage(), e);
		}
	}
	
	public L2RaidBossInstance handleBoss(L2Spawn spawnDat)
	{
		_nightBosses.add(spawnDat);
		
		L2RaidBossInstance raidboss = (L2RaidBossInstance) spawnDat.getLastSpawn();
		if (raidboss != null && raidboss.isVisible())
			return raidboss;
		
		if (GameTimeController.getInstance().isNight())
			raidboss = (L2RaidBossInstance) spawnDat.doSpawn();
		else
			raidboss = null;
		return raidboss;
	}
	
	private static class SingletonHolder
	{
		protected static final DayNightSpawnManager _instance = new DayNightSpawnManager();
	}
}
