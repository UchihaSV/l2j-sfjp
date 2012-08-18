/* This program is free software; you can redistribute it and/or modify
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
package com.l2jserver.gameserver.instancemanager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.datatables.ClanTable;
import com.l2jserver.gameserver.model.entity.ClanHall;
import com.l2jserver.gameserver.network.L2GameClient;

/**
 * @author  TSL
 */
public class CleanUpManager
{
	private static final boolean CLEANUP_CHARACTER = false;
	private static final boolean CLEANUP_CLAN = false;
	private static final boolean CLEANUP_CLANHALL = false;
	
	private static Logger _log = Logger.getLogger(CleanUpManager.class.getName());
	
	private static CleanUpManager _instance = new CleanUpManager();
	
	public static CleanUpManager getInstance()
	{
		return _instance;
	}
	
	private CleanUpManager()
	{
	}
	
	public void execute()	//[JOJO]
	{
//		System.out.println("Execute CleanUpManager");
//		doCleanupCharacter();
//		doCleanupClan();
//		doCleanupClanHall();
	}
	
	public void doCleanupCharacter()
	{
if (CLEANUP_CHARACTER) {
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT charId, account_name, char_name, lastaccess"
				+ " FROM characters"
				+ " WHERE lastaccess < (UNIX_TIMESTAMP()*1000 - 86400000*90)"
				+ " AND clanid = 0 AND accesslevel <= 0"
				+ " ORDER BY account_name, lastaccess") )
		{
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				_log.info("[CHECK CHARADATA] DELETE CHARACTER(ID:" + result.getString("account_name") + ", NAME:" + result.getString("char_name") + ")");
				L2GameClient.deleteCharByObjId(result.getInt("charId"));	//+[JOJO]
//				deleteCharByObjId(result.getInt("charId"));					//-[JOJO]
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "could not cleanup character:", e);
		}
}
	}
	
	public void doCleanupClan()
	{
		/* 盟主が２ヶ月ログインしていないクランを強制解散させるサンプルコード */
if (CLEANUP_CLAN) {
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT clan_data.clan_id, clan_name, char_name, lastaccess"
											+ " FROM clan_data, characters"
											+ " WHERE clan_data.leader_id = characters.CharId"
											+ " AND characters.lastaccess < (UNIX_TIMESTAMP()*1000 - 86400000*60)"
											+ " ORDER BY clan_data.clan_id") )
		{
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				_log.info("[CHECK CLANDATA] DELETE CLAN(NAME:" + result.getString("clan_name") + ", LEADER:" + result.getString("char_name") + ")!");
				ClanTable.getInstance().destroyClan(Integer.parseInt(result.getString("clan_id")));
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "could not cleanup clan:", e);
		}
}
	}
	
	public void doCleanupClanHall()
	{
		/* 盟主が２週間ログインしていないアジトを強制退去させるサンプルコード */
if (CLEANUP_CLANHALL) {
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT clanhall.id, clan_data.clan_name"
											+ " FROM clanhall, clan_data, characters"
											+ " WHERE clanhall.ownerid = clan_data.clan_id"
											+ " AND clan_data.leader_id = characters.charId"
											+ " AND characters.lastaccess < (UNIX_TIMESTAMP()*1000 - 86400000*14)"
											+ " ORDER BY clanhall.id") )
		{
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				ClanHall clanhall = ClanHallManager.getInstance().getClanHallById(result.getInt("id"));
				_log.info("[CHECK CLANHALL] " + result.getString("clan_name") + " part with ClanHall(" + clanhall.getName() + ")!");
				ClanHallManager.getInstance().setFree(result.getInt("id"));
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "could not cleanup clanhall:", e);
		}
}
	}
}
