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
package com.l2jserver.gameserver.model.event;

import static com.l2jserver.util.Util.dateFormat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.Announcements;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.EventDroplist;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.model.L2DropData;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.CreatureSay;
import com.l2jserver.gameserver.script.DateRange;
import com.l2jserver.gameserver.script.EventDrop;
import com.l2jserver.gameserver.util.Broadcast;
import com.l2jserver.gameserver.util.EventNpcAligner;
/**
 * Parent class for long time events.<br>
 * Maintains config reading, spawn of NPC's, adding of event's drop.
 * @author GKR
 */
public class LongTimeEvent extends Quest
{
	protected final static Logger _log = Logger.getLogger(LongTimeEvent.class.getName());
	
	static final String messageSplitPattern = "(?:\r\n|\n|\r|\\\\n)";
	
 //	private String _eventName;
	
	// Messages
	private String _onEnterMsg = "Event is in process";
	private String _onEndMsg = "Event ends!";
	
	private DateRange _eventPeriod = null;
	private DateRange _dropPeriod;
	
	// NPC's to spawm and their spawn points
	private ArrayList<NpcSpawn> _spawnList;
	
	// Drop data for event
	private ArrayList<EventDrop> _dropList;
	
	private class NpcSpawn
	{
		protected final int npc;
		protected final int x, y, z, heading;
		protected NpcSpawn(int pNpcId, int pX, int pY, int pZ, int pHeading)
		{
			npc = pNpcId;
			x = pX;
			y = pY;
			z = pZ;
			heading = pHeading;
		}
	}
	
	public LongTimeEvent(int questId, String name, String descr)
	{
		super(questId, name, descr);
		
		loadConfig();
		
		if (_eventPeriod != null)
		{
			Date now = new Date();
			
			if (now.before(_eventPeriod.getEndDate()))
			{
				long delay = _eventPeriod.getStartDate().getTime() - now.getTime();
				if (delay <= 0)
					_log.info("Event name: (" + getName() + ") active till " + dateFormat(_eventPeriod.getEndDate()));
				else
					_log.info("Event name: (" + getName() + ") Date: " + dateFormat(_eventPeriod.getStartDate()) + " is not active yet... Scheduled.");
				ThreadPoolManager.getInstance().scheduleGeneral(new Runnable() {
					@Override
					public void run()
					{
						startEvent();
					}
				}, 	Math.max(99999, delay));
			}
			else
			{
				_log.info("Event name: (" + getName() + ") Date: " + dateFormat(_eventPeriod.getEndDate()) + " has passed... Ignored.");
			}
		}
	}
	
	/**
	 * Load event configuration file
	 */
	private void loadConfig()
	{
		File configFile = new File("data/scripts/events/" + getScriptName() + "/config.xml");
		_log.info("LongTimeEvent: " + configFile.getPath());
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(configFile);
			if (!doc.getDocumentElement().getNodeName().equalsIgnoreCase("event"))
			{
				throw new NullPointerException("WARNING!!! " + getScriptName() + " event: bad config file!");
			}
		//	_eventName = doc.getDocumentElement().getAttributes().getNamedItem("name").getNodeValue();
			String period = doc.getDocumentElement().getAttributes().getNamedItem("active").getNodeValue();
			_eventPeriod = DateRange.parse(period, new SimpleDateFormat("dd MM yyyy", Locale.US));
			if (!_eventPeriod.isValid())
			{
				_eventPeriod = null;
				throw new NullPointerException("WARNING!!! " + getScriptName() + " event: illegal event period");
			}
			
			if (doc.getDocumentElement().getAttributes().getNamedItem("dropPeriod") != null)
			{
				String dropPeriod = doc.getDocumentElement().getAttributes().getNamedItem("dropPeriod").getNodeValue();
				_dropPeriod = DateRange.parse(dropPeriod, new SimpleDateFormat("dd MM yyyy", Locale.US));
				// Check if drop period is within range of event period
				if (!_dropPeriod.isValid() || !_eventPeriod.isWithinRange(_dropPeriod.getStartDate()) || !_eventPeriod.isWithinRange(_dropPeriod.getEndDate()))
				{
					_dropPeriod = _eventPeriod;
					_log.warning(getScriptName() + " event: illegal drop period");
				}
			}
			else
			{
				_dropPeriod = _eventPeriod; // Drop period, if not specified, assumes all event period.
			}
			
			Date now = new Date();
			
			if (now.before(_eventPeriod.getEndDate()))
			{
				Node first = doc.getDocumentElement().getFirstChild();
				for (Node n = first; n != null; n = n.getNextSibling())
				{
					// Loading droplist
					if (n.getNodeName().equalsIgnoreCase("droplist"))
					{
						for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						{
							if (d.getNodeName().equalsIgnoreCase("add"))
							{
								try
								{
									String itemId = d.getAttributes().getNamedItem("item").getNodeValue();
									int[] items = com.l2jserver.gameserver.script.IntList.parse(itemId);
									int minCount = Integer.parseInt(d.getAttributes().getNamedItem("min").getNodeValue());
									int maxCount = Integer.parseInt(d.getAttributes().getNamedItem("max").getNodeValue());
									String chance = d.getAttributes().getNamedItem("chance").getNodeValue();
									int finalChance = 0;
									
									if (!chance.isEmpty() && chance.endsWith("%"))
									{
										finalChance = (int) (com.l2jserver.gameserver.script.faenor.FaenorParser.getPercent(chance) * L2DropData.MAX_CHANCE);
									}
									
									boolean err = false;
									
									for (int item : items)
									{
										if (ItemTable.getInstance().getTemplate(item) == null)
										{
											_log.warning(getScriptName() + " event: " + itemId + " is wrong item id, item was not added in droplist");
											err = true;
										}
									}
									
									if (minCount > maxCount)
									{
										_log.warning(getScriptName() + " event: item " + itemId + " - min greater than max, item was not added in droplist");
										err = true;
									}
									
									if ((finalChance < 10000) || (finalChance > L2DropData.MAX_CHANCE))
									{
										_log.warning(getScriptName() + " event: item " + itemId + " - incorrect drop chance, item was not added in droplist");
										err = true;
									}
									
									if (err) continue;
									
									if (_dropList == null)
										_dropList = new ArrayList<>();
									_dropList.add(new EventDrop(items, minCount, maxCount, finalChance));
								}
								catch (NumberFormatException nfe)
								{
									_log.warning("Wrong number format in config.xml droplist block for " + getScriptName() + " event");
								}
							}
						}
					}
					
					else if (n.getNodeName().equalsIgnoreCase("spawnlist"))
					{
						// Loading spawnlist
						for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						{
							if (d.getNodeName().equalsIgnoreCase("add"))
							{
								try
								{
									int npcId = Integer.parseInt(d.getAttributes().getNamedItem("npc").getNodeValue());
									if (NpcTable.getInstance().getTemplate(npcId) == null)
									{
										_log.warning(getScriptName() + " event: " + npcId + " is wrong NPC id, NPC was not added in spawnlist");
										continue;
									}
									
									Node nx = d.getAttributes().getNamedItem("x");
									Node ny = d.getAttributes().getNamedItem("y");
									Node nz = d.getAttributes().getNamedItem("z");
									Node nh = d.getAttributes().getNamedItem("heading");
									
									int xPos = nx == null ? 0 : Integer.parseInt(nx.getNodeValue());
									int yPos = ny == null ? 0 : Integer.parseInt(ny.getNodeValue());
									int zPos = nz == null ? 0 : Integer.parseInt(nz.getNodeValue());
									int heading = nh == null ? 0 : Integer.parseInt(nh.getNodeValue());
									
									if (_spawnList == null)
										_spawnList = new ArrayList<>();
									_spawnList.add(new NpcSpawn(npcId, xPos, yPos, zPos, heading));
								}
								catch (NumberFormatException nfe)
								{
									_log.warning("Wrong number format in config.xml spawnlist block for " + getScriptName() + " event");
									continue;
								}
							}
						}
					}
					
					else if (n.getNodeName().equalsIgnoreCase("messages"))
					{
						// Loading Messages
						for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
						{
							if (d.getNodeName().equalsIgnoreCase("add"))
							{
								String msgType = d.getAttributes().getNamedItem("type").getNodeValue();
								String msgText = d.getAttributes().getNamedItem("text").getNodeValue();
								if ((msgType != null) && (msgText != null))
								{
									if (msgType.equalsIgnoreCase("onEnd"))
									{
										_onEndMsg = msgText;
									}
									else if (msgType.equalsIgnoreCase("onEnter"))
									{
										_onEnterMsg = msgText;
									}
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, getScriptName() + " event: error reading " + configFile.getAbsolutePath() + " ! " + e.getMessage(), e);
		}
	}
	
	/**
	 * Maintenance event start - adds global drop, spawns event NPC's, shows start announcement.
	 */
	protected void startEvent()
	{
		// Add drop
		if (_dropList != null)
		{
			for (EventDrop drop : _dropList)
			{
				EventDroplist.getInstance().addGlobalDrop(_dropPeriod, drop);
			}
		}
		
		// Add spawns
		long millisToEventEnd = _eventPeriod.getEndDate().getTime() - System.currentTimeMillis();
		if (_spawnList != null)
		{
			for (NpcSpawn spawn : _spawnList)
			{
				if (spawn.x == 0 && spawn.y == 0 && spawn.z == 0 && spawn.heading == 0)
				{
					for (Location location : EventNpcAligner.getSpawns())
						EventNpcAligner.align(addSpawn(spawn.npc, location, false, millisToEventEnd));
				}
				else
				{
					addSpawn(spawn.npc, spawn.x, spawn.y, spawn.z, spawn.heading, false, millisToEventEnd, false, 0);
				}
			}
		}
		
		String[] enterMsgs = _onEnterMsg.split(messageSplitPattern);
		
		// Send message on begin
		announceToAll(enterMsgs);

		// Add announce for entering players
		Announcements.getInstance().addEventAnnouncement(_eventPeriod, enterMsgs);
		
		// Schedule event end (now only for message sending)
		ThreadPoolManager.getInstance().scheduleGeneral(new Runnable() {
			@Override
			public void run()
			{
				endEvent();
			}
		}, millisToEventEnd);
	}
	
	/**
	 * End of event.
	 */
	protected void endEvent()
	{
		// Send message on end
		String[] endMsgs = _onEndMsg.split(messageSplitPattern);
		announceToAll(endMsgs);
	}
	
	public void announceToAll(String[] messageList)
	{
		for (String msg : messageList)
			Broadcast.toAllOnlinePlayers(new CreatureSay(0, Say2.ANNOUNCEMENT, "", msg));
	}
	
	/**
	 * @return event period
	 */
	public DateRange getEventPeriod()
	{
		return _eventPeriod;
	}
	
	/**
	 * @return {@code true} if now is event period
	 */
	public boolean isEventPeriod()
	{
		return _eventPeriod.isWithinRange(new Date());
	}
	
	/**
	 * @return {@code true} if now is drop period
	 */
	public boolean isDropPeriod()
	{
		return _dropPeriod.isWithinRange(new Date());
	}
}
