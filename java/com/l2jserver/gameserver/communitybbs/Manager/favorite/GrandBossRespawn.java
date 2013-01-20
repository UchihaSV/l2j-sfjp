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
import com.l2jserver.gameserver.instancemanager.GrandBossManager;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * GrandBoss respawn info
 * implement: JOJO
 * original: http://www.l2jserver.com/forum/viewtopic.php?f=73&t=18022
 */
public class GrandBossRespawn extends BaseFavoriteManager
{
	public static GrandBossRespawn getInstance()
	{
		return new GrandBossRespawn();
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		// _bbsgetfav;grand_boss_respawn

		String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/grand_boss_respawn/grandBossRespawnList.htm");

		StringBuilder tb = new StringBuilder();

		int[] BOSSES = {
			29001,	// クイーン アント
			29006,	// コア
			29014,	// オルフェン
			29019,	// アンタラス *
			29020,	// バイウム
			29022,	// ザケン
			29028,	// ヴァラカス
			29045,	// フリンテッサ
			29062,	// アンドレアス ヴァン ホルター
			29065,	// サイレン
		};

		for (int bossId : BOSSES)
		{
			if (GrandBossManager.getInstance().getStatsSet(bossId) == null)
				continue;
			tb.append("<tr><td width=300 align=right><font color=\"00C3FF\">").append(NpcTable.getInstance().getTemplate(bossId).getName()).append("</font></td>"
					+ "<td width=300>");
			final long respawnTime;
			if (bossId == 29019)
				respawnTime = getAntharasRespawnTime();
			else
				respawnTime = GrandBossManager.getInstance().getStatsSet(bossId).getLong("respawn_time");
			if (respawnTime <= System.currentTimeMillis())
				tb.append("<font color=\"32C332\">Alive");
			else
				tb.append("<font color=\"9CC300\">").append(com.l2jserver.util.Util.dateFormat(respawnTime));
			tb.append("</font></td></tr>");
		}

		html = html.replace("%bossList%", tb.toString());
		separateAndSend(html, activeChar);
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
	}

	private long getAntharasRespawnTime()
	{
		int[] ANTHARAS = {
			29066, // アンタラス 弱
			29067, // アンタラス 中
			29068, // アンタラス 強
			29019, // アンタラス 旧
		};

		for (int antharasId : ANTHARAS)
		{
			if (GrandBossManager.getInstance().getBossStatus(antharasId) != 0)
				return GrandBossManager.getInstance().getStatsSet(antharasId).getLong("respawn_time");
		}
		return 0;
	}
}