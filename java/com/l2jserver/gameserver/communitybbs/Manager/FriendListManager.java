/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l2jserver.gameserver.communitybbs.Manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author  TSL
 */
public class FriendListManager extends BaseBBSManager
{
	private static Logger _log = Logger.getLogger(FriendListManager.class.getName());
	private static FriendListManager _instance = new FriendListManager();
	
	/**
	 * @return
	 */
	public static FriendListManager getInstance()
	{
		return _instance;
	}
	
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (activeChar == null) return;
		
		if(command.startsWith("_friendlist"))
		{
			showFriendList(activeChar);
		}
		else
		{
			separateAndSend("<html><body><br><br><center> the command: " + command + " is not implemented yet</center><br><br></body></html>", activeChar);
		}
	}
	
	/**
	 * @param activeChar
	 */
	private void showFriendList(L2PcInstance activeChar)
	{
		StringBuilder html = new StringBuilder(256).append("<html><body><br><br>"
			+ "<TABLE border=0 width=610><TR><TD width=10></TD><TD width=600 align=left>"
			+ "<a action=\"bypass _bbshome\"> &$377; </a>&nbsp;>&nbsp;<A action=\"bypass _friendlist\">&$904;</A>"
			+ "</TD></TR>"
			+ "</TABLE>"
			+ "<CENTER>"
			+ "<IMG src=\"L2UI.squareblank\" width=\"1\" height=\"10\">"
			+ "&$997;"
			+ "<IMG src=\"L2UI.squaregray\" width=\"610\" height=\"1\"><BR>"
			+ "<TABLE border=0 cellspacing=0 cellpadding=0 width=610>"
			+ "<TR>"
			+ "<TD fixWIDTH=5></td>"
			+ "<TD fixWIDTH=600>");
		
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT characters.char_name AS friend_name FROM character_friends LEFT JOIN characters ON (character_friends.friendId=characters.charId) WHERE character_friends.charId=? ORDER BY friend_name");
			statement.setInt(1, activeChar.getObjectId());
			ResultSet rset = statement.executeQuery();
			while (rset.next())
			{
				String friendName = rset.getString("friend_name");
				L2PcInstance friend = L2World.getInstance().getPlayer(friendName);
				html.append("<A action=\"bypass _bbscustom;msgnew;private;").append(friendName).append("\">").append(friendName).append("</A>"
					+ " (").append(friend != null && friend.isOnline() ? "&$1006;":"&$1007;").append(") &nbsp;");
			}
			rset.close();
			statement.close();
		}
		catch (Exception e) {
			_log.warning("Error in friendlist : " + e);
		}
		finally	{
			L2DatabaseFactory.close(con);
		}
		
		html.append("</TD>"
			+ "<TD fixWIDTH=5></td>"
			+ "</TR>"
			+ "</TABLE>"
			+ "</CENTER>"
			+ "</body>"
			+ "</html>");
		separateAndSend(html.toString(), activeChar);
	}
	
	/* (non-Javadoc)
	 * @see com.l2jserver.gameserver.communitybbs.Manager.BaseBBSManager#parsewrite(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.l2jserver.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		
	}
}