/**
 * [JOJO] This file is copy of:
 *   L2J_DataPack_BETA/dist/game/data/scripts/handlers/admincommandhandlers/AdminShutdown.java
 *   sync revesion 10172, timestamp 2014/02/11 21:26
 *   sync revesion 9922, timestamp 2013/08/25 08:08
 *   sync revesion 9040, timestamp 2012/08/01 21:48
 *   sync revesion 7150, timestamp 2010/03/08 21:16
 *   sync revesion 5901, timestamp 2009/04/03 19:05
 **/
/*
 * Copyright (C) 2004-2013 L2J DataPack
 * 
 * This file is part of L2J DataPack.
 * 
 * L2J DataPack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J DataPack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.handlers.admincommandhandlers;		//[JOJO]
//package handlers.admincommandhandlers;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.l2jserver.gameserver.GameTimeController;
import com.l2jserver.gameserver.Shutdown;
import com.l2jserver.gameserver.handler.IAdminCommandHandler;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 * This class handles following admin commands: - server_shutdown [sec] = shows menu or shuts down server in sec seconds
 * @version $Revision: 1.5.2.1.2.4 $ $Date: 2005/04/11 10:06:06 $
 */
public class AdminShutdown implements IAdminCommandHandler
{
	// private static Logger _log = Logger.getLogger(AdminShutdown.class.getName());
	
	private static final String[] ADMIN_COMMANDS =
	{
		"admin_server_shutdown",
		"admin_server_restart",
		"admin_server_abort"
	};
	
	@Override
	public boolean useAdminCommand(String command, L2PcInstance activeChar)
	{
		if (command.startsWith("admin_server_shutdown"))
		{
			try
			{
				int val = Integer.parseInt(command.substring(22));
				serverShutdown(activeChar, val, false);
			}
			catch (StringIndexOutOfBoundsException | NumberFormatException e)
			{
				activeChar.sendMessage("Usage: //server_shutdown <seconds>");
				sendHtmlForm(activeChar);
			}
		}
		else if (command.startsWith("admin_server_restart"))
		{
			try
			{
				int val = Integer.parseInt(command.substring(21));
				serverShutdown(activeChar, val, true);
			}
			catch (StringIndexOutOfBoundsException | NumberFormatException e)
			{
				activeChar.sendMessage("Usage: //server_restart <seconds>");
				sendHtmlForm(activeChar);
			}
		}
		else if (command.startsWith("admin_server_abort"))
		{
			serverAbort(activeChar);
		}
		
		return true;
	}
	
	@Override
	public String[] getAdminCommandList()
	{
		return ADMIN_COMMANDS;
	}
	
	private void sendHtmlForm(L2PcInstance activeChar)
	{
		final NpcHtmlMessage adminReply = new NpcHtmlMessage();
		int t = GameTimeController.getInstance().getGameTime();
		int h = t / 60;
		int m = t % 60;
		SimpleDateFormat format = new SimpleDateFormat("h:mm a");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, h);
		cal.set(Calendar.MINUTE, m);
		adminReply.setFile(activeChar.getHtmlPrefix(), "data/html/admin/shutdown.htm");
		adminReply.replace("%count%", L2World.getInstance().getAllPlayersCount());
		adminReply.replace("%used%", String.format("%,3d", Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) );
		adminReply.replace("%time%", format.format(cal.getTime()));
		activeChar.sendPacket(adminReply);
	}
	
	private void serverShutdown(L2PcInstance activeChar, int seconds, boolean restart)
	{
		Shutdown.getInstance().startShutdown(activeChar, seconds, restart);
	}
	
	private void serverAbort(L2PcInstance activeChar)
	{
		Shutdown.getInstance().abort(activeChar);
	}
	
}
