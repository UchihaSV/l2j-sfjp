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
package com.l2jserver.gameserver.communitybbs.Manager.favorite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;

/**
 * @author  TEN / nanasisaso / TSL / JOJO
 */
public class DropList extends BaseFavoriteManager
{
	private static Logger _log = Logger.getLogger(DropList.class.getName());
	private static DropList _instance = new DropList();

	/**
	 * @return
	 */
	public static DropList getInstance()
	{
		return _instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		// _bbsgetfav;bbs_droplist

//		String arg = getArgs(command);
		searchWindow(activeChar);
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar == null) return;

		// action="Write Favorite droplist search {type} {level} {word}"
		if (ar2.equals("search"))
			showDropList(activeChar, ar3, ar4, ar5);
	}

	// ドロップリストを表示する。
	private void showDropList(L2PcInstance activeChar, String optionType, String optionLevel, String optionWord)
	{
		String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/droplist/dropList.htm");
		
		StringBuilder where_str = new StringBuilder();
		if (optionType.equals("アイテム名"))
			where_str.append(" AND itemname_ja.name Like ?");
		//	where_str = " AND itemname_ja.name Like '%" + quoteString(optionWord) + "%'";
		else /*if (optionType.equals("モンスター名"))*/
			where_str.append(" AND npc.name Like ?");
		//	where_str = " AND npc.name Like '%" + quoteString(optionWord) + "%'";
		if (!optionLevel.equals("全体"))
		{
			String v[] = optionLevel.split("-");
			int minlevel = Integer.parseInt(v[0]);
			int maxlevel = Integer.parseInt(v[1]);
			where_str.append(" AND level BETWEEN " + minlevel + " AND " + maxlevel);
		}
		_log.info("UserName: " + activeChar.getName() + " SearchWord : " + where_str);
		StringBuilder dropList = new StringBuilder();
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT level, npc.name as mobname, itemname_ja.name as itemname, category"
				+ " FROM droplist,npc,itemname_ja"
				+ " WHERE droplist.mobId = npc.id"
				+ " AND droplist.itemId = itemname_ja.id"
				+ " AND droplist.itemId != 57"
				+ where_str
				+ " ORDER BY level, npc.name, itemname_ja.name LIMIT 21") )
		{
			statement.setString(1, "%" + optionWord + "%");
			ResultSet result = statement.executeQuery();
			int count = 0;
			while (result.next())
			{
				if (++count >= 21)
				{
					dropList.append("<font color=FF00FF>...省略されました。</font><br>");
					break;
				}
				dropList.append("<table width=610><tr>");
				dropList.append("<td width=40 align=center>" + result.getInt("level") + "</td>");
				dropList.append("<td width=220>" + result.getString("mobname") + "</td>");
				dropList.append("<td width=300>" + result.getString("itemname") + "</td>");
				dropList.append("<td width=50 align=center>" + (result.getInt("category") == -1 ? "＊" : "－") + "</td>");
				dropList.append("</tr></table>");
			}
			
			html = html.replace("%dropList%", dropList);
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "ドロップリスト作成中にエラーが発生しました。", e);
			html = "ドロップリスト作成中にエラーが発生しました。";
		}
		separateAndSend(html, activeChar);
	}

	// 検索条件入力用ウィンドウの表示
	private void searchWindow(L2PcInstance activeChar)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/droplist/searchWindow.htm");
		activeChar.sendPacket(html);
	}
}