/*
 * Copyright (C) 2005-2008 L2J_JP / 2008-2013 L2J-SFJP
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
package com.l2jserver.gameserver.communitybbs.Manager.favorite;

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author JOJO
 *
 */
abstract class BaseFavoriteManager extends com.l2jserver.gameserver.communitybbs.Manager.BaseBBSManager
{
	void showMessage(L2PcInstance pc, String message)
	{
		separateAndSend("<html><body><br><table border=0 width=610>"
				+ "<tr><td align=left><a action=\"bypass _bbshome\">&$377;</a>&nbsp;&gt;&nbsp;<A action=\"bypass _bbsgetfav\">&$379;</A></td><tr>"
				+ "<tr><td height=100></td></tr>"
				+ "</table>"
				+ "<center>" + message + "</center></body></html>", pc);
	}

	String getArgs(String command)
	{
		return command.split(";", 3)[2];
	}
}
