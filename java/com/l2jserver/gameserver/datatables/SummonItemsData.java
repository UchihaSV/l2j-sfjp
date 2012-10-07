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

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.gameserver.model.L2SummonItem;

/**
 * @author FBIagent
 */
public class SummonItemsData
{
	protected static final Logger _log = Logger.getLogger(SummonItemsData.class.getName());
	private final Map<Integer, L2SummonItem> _summonitems = new HashMap<>();
	
	public static SummonItemsData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected SummonItemsData()
	{
		try (Scanner s = new Scanner(new File(Config.DATAPACK_ROOT + "/data/summon_items.csv")))
		{
			int lineCount = 0;
			
			while (s.hasNextLine())
			{
				lineCount++;
				
				String line = s.nextLine();
				
				if (line.startsWith("#"))
				{
					continue;
				}
				else if (line.isEmpty())
				{
					continue;
				}
				
				String[] lineSplit = line.split(";");
				boolean ok = true;
				int itemID = 0, npcID = 0;
				byte summonType = 0;
				int despawn = -1;
				
				try
				{
					itemID = Integer.parseInt(lineSplit[0]);
					npcID = Integer.parseInt(lineSplit[1]);
					summonType = Byte.parseByte(lineSplit[2]);
					if (summonType == 0)
					{
						despawn = Integer.parseInt(lineSplit[3]);
					}
				}
				catch (Exception e)
				{
					_log.warning(getClass().getSimpleName() + ": Error in line " + lineCount + " -> incomplete/invalid data or wrong seperator!");
					_log.warning("		" + line);
					ok = false;
				}
				
				if (!ok)
				{
					continue;
				}
				
				L2SummonItem summonitem = new L2SummonItem(itemID, npcID, summonType, despawn);
				_summonitems.put(itemID, summonitem);
			}
			
			_log.info(getClass().getSimpleName() + ": Loaded " + _summonitems.size() + " summon items.");
		}
		catch (Exception e)
		{
			_log.warning(getClass().getSimpleName() + ": Can not find '" + Config.DATAPACK_ROOT + "/data/summon_items.csv'");
			return;
		}
	}
	
	public L2SummonItem getSummonItem(int itemId)
	{
		return _summonitems.get(itemId);
	}
	
	public int[] itemIDs()
	{
		int size = _summonitems.size();
		int[] result = new int[size];
		int i = 0;
		for (L2SummonItem si : _summonitems.values())
		{
			result[i++] = si.getItemId();
		}
		return result;
	}
	
	private static class SingletonHolder
	{
		protected static final SummonItemsData _instance = new SummonItemsData();
	}
}
