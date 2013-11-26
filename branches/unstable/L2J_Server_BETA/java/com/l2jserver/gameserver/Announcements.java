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
package com.l2jserver.gameserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;

import com.l2jserver.Config;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.CreatureSay;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.gameserver.script.DateRange;
import com.l2jserver.gameserver.util.Broadcast;
import com.l2jserver.util.StringUtil;

public class Announcements
{
	private static Logger _log = Logger.getLogger(Announcements.class.getName());
	
	private final List<String> _announcements = new FastList<>();
	private final List<String> _critAnnouncements = new FastList<>();
	private final List<EventAnnounce> _eventAnnouncements = new FastList<>();
	
	protected Announcements()
	{
		loadAnnouncements();
	}
	
	public static Announcements getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public void loadAnnouncements()
	{
		_announcements.clear();
		_critAnnouncements.clear();
		readFromDisk("data/announcements.txt", _announcements);
		readFromDisk("data/critannouncements.txt", _critAnnouncements);
		
		if (Config.DEBUG)
		{
			_log.info("Announcements: Loaded " + (_announcements.size() + _critAnnouncements.size()) + " announcements.");
		}
	}
	
	//[JOJO]-------------------------------------------------
	private static class EventAnnounce
	{
		DateRange validDateRange;
		String[] messages;
		EventAnnounce(DateRange validDateRange, String[] messages)
		{
			this.validDateRange = validDateRange;
			this.messages = messages;
		}
	}
	//-------------------------------------------------------
	
	public void showAnnouncements(L2PcInstance activeChar)
	{
		for (String announce : _announcements)
		{
			if (announce.indexOf('%') >= 0)
			{
				announce = announce.replaceFirst("%server_name%", LoginServerThread.getInstance().getServerName()); // [JOJO]
			}
			CreatureSay cs = new CreatureSay(0, Say2.ANNOUNCEMENT, activeChar.getName(), announce);
			activeChar.sendPacket(cs);
		}
		
		for (String critAnnounce : _critAnnouncements)
		{
			CreatureSay cs = new CreatureSay(0, Say2.CRITICAL_ANNOUNCE, activeChar.getName(), critAnnounce);
			activeChar.sendPacket(cs);
		}
		
		final Date currentDate = new Date();
		for (EventAnnounce eventAnnounce : _eventAnnouncements)
		{
			DateRange validDateRange = eventAnnounce.validDateRange;
			String[] messages = eventAnnounce.messages;
			
			if (validDateRange.isValid() && validDateRange.isWithinRange(currentDate)) // [JOJO]
			{
				for (String s : messages)
				{
					activeChar.sendMessage(s);/* ÉeÉXÉgçœ */
				}
			}
			
		}
	}
	
	public void addEventAnnouncement(DateRange validDateRange, String... msg)
	{
		_eventAnnouncements.add(new EventAnnounce(validDateRange, msg));
	}
	
	public void listAnnouncements(L2PcInstance activeChar)
	{
		String content = HtmCache.getInstance().getHtmForce(activeChar.getHtmlPrefix(), "data/html/admin/announce.htm");
		final NpcHtmlMessage adminReply = new NpcHtmlMessage();
		adminReply.setHtml(content);
		final StringBuilder replyMSG = StringUtil.startAppend(500, "<br>");
		for (int i = 0; i < _announcements.size(); i++)
		{
			String line = String.valueOf(i);
			StringUtil.append(replyMSG, "<table width=275 border=0 cellspacing=0 cellpadding=0 bgcolor=", i % 2 == 0 ? "333333" : "444444" , "><tr>"
				+ "<td VALIGN=TOP fixwidth=14><button action=\"bypass -h admin_del_announcement "  , line, " -1 \" width=14 height=14 back=L2UI_CH3.Shortcut_Nextv_Down fore=L2UI_CH3.Shortcut_Nextv></td>"
				+ "<td VALIGN=TOP fixwidth=14><button action=\"bypass -h admin_del_announcement ", line, " +1 \" width=14 height=14 back=L2UI_CH3.Shortcut_Prevv_Down fore=L2UI_CH3.Shortcut_Prevv></td>"
				+ "<td VALIGN=TOP fixwidth=230>", _announcements.get(i), "</td>"
				+ "<td VALIGN=TOP fixwidth=12><button action=\"bypass -h admin_del_announcement " , line, "\" width=12 height=12 back=L2UI.bbs_delete_Down fore=L2UI.bbs_delete></td>"
				+ "</tr></table>");
		}
		adminReply.replace("%announces%", replyMSG.toString());
		activeChar.sendPacket(adminReply);
	}
	
	public void listCritAnnouncements(L2PcInstance activeChar)
	{
		String content = HtmCache.getInstance().getHtmForce(activeChar.getHtmlPrefix(), "data/html/admin/critannounce.htm");
		final NpcHtmlMessage adminReply = new NpcHtmlMessage();
		adminReply.setHtml(content);
		final StringBuilder replyMSG = StringUtil.startAppend(500, "<br>");
		for (int i = 0; i < _critAnnouncements.size(); i++)
		{
			String line = String.valueOf(i);
			StringUtil.append(replyMSG, "<table width=275 border=0 cellspacing=0 cellpadding=0 bgcolor=", i % 2 == 0 ? "333333" : "444444" , "><tr>"
				+ "<td VALIGN=TOP fixwidth=14><button action=\"bypass -h admin_del_critannouncement "  , line, " -1 \" width=14 height=14 back=L2UI_CH3.Shortcut_Nextv_Down fore=L2UI_CH3.Shortcut_Nextv></td>"
				+ "<td VALIGN=TOP fixwidth=14><button action=\"bypass -h admin_del_critannouncement ", line, " +1 \" width=14 height=14 back=L2UI_CH3.Shortcut_Prevv_Down fore=L2UI_CH3.Shortcut_Prevv></td>"
				+ "<td VALIGN=TOP fixwidth=230>", _critAnnouncements.get(i), "</td>"
				+ "<td VALIGN=TOP fixwidth=12><button action=\"bypass -h admin_del_critannouncement " , line, "\" width=12 height=12 back=L2UI.bbs_delete_Down fore=L2UI.bbs_delete></td>"
				+ "</tr></table>");
		}
		adminReply.replace("%critannounces%", replyMSG.toString());
		activeChar.sendPacket(adminReply);
	}
	
	public void addAnnouncement(String text)
	{
		_announcements.add(text);
		saveToDisk(false);
	}
	
	/**
	 * @param updown -1:up +1:down 0:remove
	 */
	public void delAnnouncement(int line, int updown)
	{
		moveLine(line, updown, false);
	}
	
	public void addCritAnnouncement(String text)
	{
		_critAnnouncements.add(text);
		saveToDisk(true);
	}
	
	/**
	 * @param updown -1:up +1:down 0:remove
	 */
	public void delCritAnnouncement(int line, int updown)
	{
		moveLine(line, updown, true);
	}
	
	private void moveLine(int line, int updown, boolean isCritical)
	{
		List<String> list = isCritical ? _critAnnouncements : _announcements;
		int to;
		if (updown == 0)
			list.remove(line);
		else if ((to = line + updown) >= 0 && to < list.size())
			list.add(to, list.remove(line));
		else
			return;
		saveToDisk(isCritical);
	}
	
	private void readFromDisk(String path, List<String> list)
	{
		final File file = new File(Config.DATAPACK_ROOT, path);
		try (BufferedReader lnr = com.l2jserver.util.Util.utf8BufferedReader(file))	//[JOJO]
		{
			//[JOJO] UTF-8
			String line;
			while ((line = lnr.readLine()) != null)
			{
				list.add(line);
			}
			//[JOJO]
		}
		catch (IOException e1)
		{
			_log.log(Level.SEVERE, "Error reading announcements: ", e1);
		}
	}
	
	private void saveToDisk(boolean isCritical)
	{
		String path;
		List<String> list;
		
		if (isCritical)
		{
			path = "data/critannouncements.txt";
			list = _critAnnouncements;
		}
		else
		{
			path = "data/announcements.txt";
			list = _announcements;
		}
		
		final String FILE_EOL = detectEOL(path);	//[JOJO]
		try (BufferedWriter save = com.l2jserver.util.Util.utf8BufferedWriter(path))	//[JOJO] UTF-8
		{
			for (String announce : list)
			{
				save.write(announce);
				save.write(FILE_EOL);
			}
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, "Saving to the announcements file has failed: ", e);
		}
	}
	
	//[JOJO]-------------------------------------------------
	private String detectEOL(String file)
	{
		try (FileInputStream in = new FileInputStream(file))
		{
			int ch1, ch2;
			while ((ch1 = in.read()) != -1)
			{
				if (ch1 == '\r')
				{
					ch2 = in.read();
					if (ch2 == '\n') return "\r\n";
					else return "\r";
				}
				else if (ch1 == '\n')
				{
					return "\n";
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return System.lineSeparator();
	}
	//-------------------------------------------------------
	
	public void announceToAll(String text)
	{
		announceToAll(text, false);
	}
	
	public void announceToAll(String text, boolean isCritical)
	{
		Broadcast.announceToOnlinePlayers(text, isCritical);
	}
	
	public void announceToAll(SystemMessage sm)
	{
		Broadcast.toAllOnlinePlayers(sm);
	}
	
	public void announceToInstance(SystemMessage sm, int instanceId)
	{
		Broadcast.toPlayersInInstance(sm, instanceId);
	}
	
	/**
	 * Method for handling announcements from admin
	 * @param command
	 * @param lengthToTrim
	 * @param isCritical
	 */
	public void handleAnnounce(String command, int lengthToTrim, boolean isCritical)
	{
		try
		{
			// Announce string to everyone on server
			String text = command.substring(lengthToTrim);
			SingletonHolder._instance.announceToAll(text, isCritical);
		}
		// No body cares!
		catch (StringIndexOutOfBoundsException e)
		{
			// empty message.. ignore
		}
	}
	
	private static class SingletonHolder
	{
		protected static final Announcements _instance = new Announcements();
	}
}
