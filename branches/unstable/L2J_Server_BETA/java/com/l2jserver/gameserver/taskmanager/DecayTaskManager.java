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
package com.l2jserver.gameserver.taskmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.model.actor.L2Attackable;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;

/**
 * @author la2 Lets drink to code!
 */
public class DecayTaskManager
{
	protected static final Logger _log = Logger.getLogger(DecayTaskManager.class.getName());
	
	protected final Map<L2Character, Long> _decayTasks = new FastMap<L2Character, Long>().shared();
	
	private static final boolean GRAND_BOSS_DECAY_TIME_L2J_JP = true;
	
	private DecayTaskManager()
	{
		ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new DecayScheduler(), 10000, Config.DECAY_TIME_TASK);
	}
	
	public static DecayTaskManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public void addDecayTask(L2Character actor)
	{
		addDecayTask(actor, 0);
	}
	
	public void addDecayTask(L2Character actor, int interval)
	{
		_decayTasks.put(actor, System.currentTimeMillis() + interval);
	}
	
	public void cancelDecayTask(L2Character actor)
	{
		_decayTasks.remove(actor);
	}
	
	private class DecayScheduler implements Runnable
	{
		protected DecayScheduler()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			final long current = System.currentTimeMillis();
			try
			{
				final Iterator<Entry<L2Character, Long>> it = _decayTasks.entrySet().iterator();
				Entry<L2Character, Long> e;
				L2Character actor;
				Long next;
				int delay;
				while (it.hasNext())
				{
					e = it.next();
					actor = e.getKey();
					next = e.getValue();
					if ((actor == null) || (next == null))
					{
						continue;
					}
					if (actor.isRaid() && !actor.isRaidMinion())
					{
						delay = Config.RAID_BOSS_DECAY_TIME;
					}
					else if ((actor instanceof L2Attackable) && (((L2Attackable) actor).isSpoil() || ((L2Attackable) actor).isSeeded()))
					{
						delay = Config.SPOILED_DECAY_TIME;
					}
					else
					{
						delay = Config.NPC_DECAY_TIME;
					}
					if (GRAND_BOSS_DECAY_TIME_L2J_JP)	//TODO:[JOJO] 〓チェック中〓
					{
						// [L2J_JP ADD SANDMAN][MODIFY JOJO]
						// [L2J_JP ADD START]
						if (actor instanceof L2Npc)
						{
							L2Npc npc = (L2Npc) actor;
							switch (npc.getNpcId())
							{
//								case 29028: // Varakas
//									delay = 18000;
//									break;
//								case 29019: // Antharas
//								case 29066: // Antharas
//								case 29067: // Antharas
//								case 29068: // Antharas
//									delay = 12000;
//									break;
//								case 29045: // frintezza
//									delay = 15000;
//									break;
								case 29014: // Orfen
									delay = 150000;
									break;
								case 29001: // Queen Ant
									delay = 150000;
								case 29006: // Core
									delay = 15000;
									break;
							}
						}
						// [L2J_JP ADD END]
					}
					if ((current - next) > delay)
					{
						actor.onDecay();
						it.remove();
					}
				}
			}
			catch (Exception e)
			{
				// TODO: Find out the reason for exception. Unless caught here, mob decay would stop.
				_log.log(Level.WARNING, "Error in DecayScheduler: " + e.getMessage(), e);
			}
		}
	}
	
	@Override
	public String toString()
	{
		StringBuilder ret = new StringBuilder(256);
		ret.append("============= DecayTask Manager Report ============\r\n"
		   + "Tasks count: ")
		   .append(_decayTasks.size())
		   .append("\r\n"
		   + "Tasks dump:\r\n");
		
		Long current = System.currentTimeMillis();
		for (L2Character actor : _decayTasks.keySet())
		{
			ret.append("Class/Name: ")
			   .append(actor.getClass().getSimpleName())
			   .append('/')
			   .append(actor.getName())
			   .append(" decay timer: ")
			   .append(current - _decayTasks.get(actor))
			   .append("\r\n");
		}
		
		return ret.toString();
	}
	
	/**
	 * <u><b><font color="FF0000">Read only</font></b></u>
	 * @return
	 */
	public Map<L2Character, Long> getTasks()
	{
		return _decayTasks;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final DecayTaskManager _instance = new DecayTaskManager();
	}
}
