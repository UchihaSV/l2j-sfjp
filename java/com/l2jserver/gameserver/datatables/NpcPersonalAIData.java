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
package com.l2jserver.gameserver.datatables;

import java.util.HashMap;
import java.util.Map;

import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;

/**
 * This class holds parameter, specific to certain NPCs.<br>
 * It can be either general parameters overridden for certain NPC instance instead of template parameters(aggro range, for example), or some optional parameters, handled by datapack scripts.<br>
 * @author GKR
 */
public class NpcPersonalAIData
{
	private final Map<String, Map<String, Integer>> _AIData = new HashMap<>();
	private long randomName;
	
	/**
	 * Instantiates a new table.
	 */
	protected NpcPersonalAIData()
	{
	}
	
	/**
	 * Stores data for given spawn.
	 * @param spawnDat spawn to process
	 * @param data Map of AI values
	 */
	public void storeData(L2Spawn spawnDat, Map<String, Integer> data)
	{
		if (data != null && !data.isEmpty())
		{
			// check for spawn name. Since spawn name is key for AI Data, generate random name, if spawn name isn't specified
			if (spawnDat.getName() == null)
			{
				String spawnName;
				do
					spawnName = Long.toString(++randomName);
				while (_AIData.containsKey(spawnName));
				spawnDat.setName(spawnName);
			}
			
			_AIData.put(spawnDat.getName(), data);
		}
	}
	
	/**
	 * Gets AI value with given spawnName and paramName
	 * @param spawnName spawn name to check
	 * @param paramName parameter to check
	 * @return value of given parameter for given spawn name
	 */
	public int getAIValue(String spawnName, String paramName)
	{
		return getAIValue(spawnName, paramName, -1);
	}
	public int getAIValue(String spawnName, String paramName, int defaultValue)
	{
		if (spawnName == null) return defaultValue;
		final Map<String, Integer> map = _AIData.get(spawnName);
		if (map == null) return defaultValue;
		final Integer val = map.get(paramName);
		if (val == null) return defaultValue;
		return val;
	}
	
	/**
	 * Verifies if there is AI value with given spawnName and paramName
	 * @param spawnName spawn name to check
	 * @param paramName parameter name to check
	 * @return {@code true} if parameter paramName is set for spawn spawnName, {@code false} otherwise
	 */
	public boolean hasAIValue(String spawnName, String paramName)
	{
		final Map<String, Integer> map;
		return spawnName != null && (map = _AIData.get(spawnName)) != null && map.get(paramName) != null;
	}
	
	/**
	 * Initializes npc parameters by specified values.
	 * @param npc NPC to process
	 * @param spawn link to NPC's spawn
	 * @param spawnName name of spawn
	 */
	public void initializeNpcParameters(L2Npc npc, L2Spawn spawn, String spawnName)
	{
		final Map<String, Integer> map;
		if ((map = _AIData.get(spawnName)) != null)
		{
			try
			{
				for (String key : map.keySet())
				{
					final int val;
					switch (key)
					{
						case "disableRandomAnimation":
							val = map.get(key);
							npc.setRandomAnimationEnabled(val == 0);
							break;
						case "disableRandomWalk":
							val = map.get(key);
							npc.setIsNoRndWalk(val == 1);
							spawn.setIsNoRndWalk(val == 1);
							break;
					}
				}
			}
			catch (Exception e)
			{
				// Do nothing
			}
		}
	}
	
	/**
	 * Gets the single instance of NpcTable.
	 * @return single instance of NpcTable
	 */
	public static NpcPersonalAIData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final NpcPersonalAIData _instance = new NpcPersonalAIData();
	}
}