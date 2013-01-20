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

import java.util.StringTokenizer;

import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author  TEN / nanasisaso / TSL / JOJO
 */
public class FavoriteManager extends BaseFavoriteManager
{
	private static FavoriteManager _instance = new FavoriteManager();

	/**
	 * @return
	 */
	public static FavoriteManager getInstance()
	{
		return _instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (activeChar == null) return;

		if (command.equals("_bbsgetfav"))
		{
			showFavorite(activeChar);
		}
		else if (command.startsWith("_bbsgetfav;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();	// skip "_bbsgetfav"
			String subcmd = st.nextToken();
			//ドロップリスト検索
			if (subcmd.equals("bbs_droplist"))
				DropList.getInstance().parsecmd(command, activeChar);
			// サーバ再起動申請
			else if (subcmd.equals("restart_srv"))
				RestartServer.getInstance().parsecmd(command, activeChar);
			// キャラクター復旧申請
			else if (subcmd.equals("repair_char"))
				RepairChar.getInstance().parsecmd(command, activeChar);
			// Grand Boss Respawn Info
			else if (subcmd.equals("grand_boss_respawn"))
//				if (activeChar.isGM())
					GrandBossRespawnTEST.getInstance().parsecmd(command, activeChar);
//				else
//					GrandBossRespawn.getInstance().parsecmd(command, activeChar);
			else
				separateAndSend("<html><body><br><center> the command: " + command + " is not implemented yet</center><br></body></html>", activeChar);
		}
		else
		{
			separateAndSend("<html><body><br><center> the command: " + command + " is not implemented yet</center><br></body></html>", activeChar);
			return;
		}
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar == null) return;

	/*	if (ar1.equals("restart_srv"))
			RestartServer.getInstance().parsewrite(null, ar2, ar3, ar4, ar5, activeChar);
		else if (ar1.equals("repair_char"))
			RepairChar.getInstance().parsewrite(null, ar2, ar3, ar4, ar5, activeChar);
		else if (ar1.equals("party_match"))
			PartyMatch.getInstance().parsewrite(null, ar2, ar3, ar4, ar5, activeChar);
		else */
		if (ar1.equals("droplist"))
			DropList.getInstance().parsewrite(null, ar2, ar3, ar4, ar5, activeChar);
		else
		{
			separateAndSend("<html><body><br><center> the command: " + ar1 + " is not implemented yet</center><br></body></html>", activeChar);
		}
	}

	/**
	 * @param activeChar
	 */
	void showFavorite(L2PcInstance activeChar)
	{
		separateAndSend(HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/favalite.htm")
				, activeChar);
	}
}