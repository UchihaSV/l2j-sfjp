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
/*package custom.BossRespawn;*/

import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.handler.AdminCommandHandler;
import com.l2jserver.gameserver.instancemanager.GrandBossManager;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * GrandBoss respawn info
 * implement: JOJO
 * original: http://www.l2jserver.com/forum/viewtopic.php?f=73&t=18022
 */
public class GrandBossRespawnTEST extends BaseFavoriteManager
{
	public static GrandBossRespawnTEST getInstance()
	{
		return new GrandBossRespawnTEST();
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		// _bbsgetfav;grand_boss_respawn
		// _bbsgetfav;grand_boss_respawn;admin_grandboss

		String[] args = command.split(";", 3);
		String arg = args.length < 3 ? "" : args[2];
		switch (arg)
		{
			case "":
			{
				String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/grand_boss_respawn/grandBossRespawnList.htm");
				
				StringBuilder tb = new StringBuilder();
				boolean isGM = activeChar.isGM();
				add(tb, isGM, 29001, new String[]{ "ALIVE", "DEAD"});							//QueenAnt.java
				add(tb, isGM, 29006, new String[]{ "ALIVE", "DEAD"});							//Core.java
				add(tb, isGM, 29014, new String[]{ "ALIVE", "DEAD"});							//Orfen.java
				add(tb, isGM, 29068, new String[]{ "ALIVE", "WAITING", "IN_FIGHT", "DEAD"});	//Antharas.java
				add(tb, isGM, 29020, new String[]{ "ASLEEP", "AWAKE", "DEAD"});					//Baium.java
				add(tb, isGM, 29022, new String[]{ "ALIVE", "DEAD"});							//Zaken.java
				add(tb, isGM, 29028, new String[]{ "DORMANT", "WAITING", "FIGHTING", "DEAD"});	//Valakas.java
				add(tb, isGM, 29045, new String[]{ "DORMANT", "WAITING", "FIGHTING", "DEAD"});	//Frintezza.java
				add(tb, isGM, 29062, new String[]{ "DORMANT", "FIGHTING", "DEAD", "INTERVAL"});	//Vanhalter.java
				add(tb, isGM, 29065, new String[]{ "DORMANT", "WAITING", "FIGHTING", "DEAD"});	//Sailren.java
				
				html = html.replace("%bossList%", tb.toString());
				
				html = html.replaceFirst("(?=<table><tr><td height=60></td><tr></table>)",
					isGM ? "<table><tr><td height=20></td><tr></table>\n<button value=\"GrandBoss admin menu\" action=\"bypass _bbsgetfav;grand_boss_respawn;admin_grandboss\" width=160 height=21 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df>\n"
						: "");
				separateAndSend(html, activeChar);
				break;
			}
			case "admin_grandboss":
			{
				AdminCommandHandler.getInstance().getHandler(arg).useAdminCommand(arg, activeChar);
				break;
			}
		}
	}
	private void add(StringBuilder tb, boolean isGM, int bossId, String[] statusString)
	{
		if (GrandBossManager.getInstance().getStatsSet(bossId) == null)
			return;
		tb.append("<tr><td width=270 align=right><font color=\"00C3FF\">")
		  .append(NpcTable.getInstance().getTemplate(bossId).getName())
		  .append("</font></td>");
		if (isGM)
			tb.append("<td><font color=\"F9F999\">")
			  .append(statusString[GrandBossManager.getInstance().getBossStatus(bossId)])
			  .append("</font></td>");
		final long respawnTime = GrandBossManager.getInstance().getStatsSet(bossId).getLong("respawn_time");
		tb.append("<td width=270>");
		if (System.currentTimeMillis() < respawnTime)
			tb.append("<font color=\"9CC300\">")
			  .append(com.l2jserver.util.Util.dateFormat(respawnTime))
			  .append("</font>");
		else
			tb.append("<font color=\"32C332\">Alive</font>");
		tb.append("</td></tr>");
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
	}
}