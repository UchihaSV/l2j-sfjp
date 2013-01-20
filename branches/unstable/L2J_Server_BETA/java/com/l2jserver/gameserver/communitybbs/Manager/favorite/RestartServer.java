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

import com.l2jserver.Config;
import com.l2jserver.gameserver.Announcements;
import com.l2jserver.gameserver.GameServer;
import com.l2jserver.gameserver.Shutdown;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.instancemanager.CastleManager;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.entity.Castle;
import com.l2jserver.gameserver.network.L2GameClient;
import com.l2jserver.gameserver.util.TextReplacer;

/**
 * @author  TEN / nanasisaso / TSL / JOJO
 */
public class RestartServer extends BaseFavoriteManager
{
	private static RestartServer _instance = new RestartServer();

	//集計
	private static boolean _autorestart = false;
	private int _total;		// サーバー内全員
	private int _score;		// リスタ希望者の人数
	private int _rate;		// 割合

	/**
	 * @return
	 */
	public static RestartServer getInstance()
	{
		return _instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		// _bbsgetfav;restart_srv;_menu
		// _bbsgetfav;restart_srv;do

		String arg = getArgs(command);
		if (arg.equals("_menu"))
			showRestartServerMenu(activeChar);
		else if (arg.equals("do"))
			doRestartServer(activeChar);
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
	}

	// サーバ再起動申請者数集計
	private void countRestartServer()
	{
		int total = 0, score = 0;
		for (L2PcInstance pc : L2World.getInstance().getAllPlayersArray()) {
			L2GameClient c = pc.getClient();
			if (c == null || c.isDetached()) continue;	// 不在モードを除外
			++total;
			if (pc.voteServerRestart) ++score;
		}
		_total = total;
		_score = score;
		_rate = total == 0 ? 0 : score * 100 / total;
	}

	// 申請画面表示部分
	private void showRestartServerMenu(L2PcInstance activeChar)
	{
		TextReplacer html = new TextReplacer(HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/restart_srv/restartServer.htm"));

		String restartServer;
		if (_autorestart)
		{
			// 再起動処理中ならば
			restartServer = "<font color=\"LEVEL\">既に再起動が可決されています。</font><br1>"
				+ "<font color=\"LEVEL\">安全な場所でログアウトしてください。</font><br1>";
		}
		else
		{
			restartServer = "<button value=\"申請する\" action=\"bypass _bbsgetfav;restart_srv;do\" width=80 height=22 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\"><br>"
				+ "＜注意事項＞<br1>"
				+ "サーバ起動後 " + com.l2jserver.util.Util.strMillTime(Config.CUSTOM_SERVER_RESTART_INTERVAL) + " 経過していないと申請できません。<br1>"
				+ "また、同一アカウントからの申請は１回のみで、ログアウトした場合は無効となります。<br1>";
		}

		countRestartServer();

		html = html
			.replace("%SERVER_RESTART_RATE%", Config.CUSTOM_SERVER_RESTART_RATE)
			.replace("%total%", _total)
			.replace("%score%", _score)
			.replace("%rate%", _rate)
			.replace("%restartServer%", restartServer);
		separateAndSend(html.toString(), activeChar);
	}

	// 状態確認、判別部分
	private void doRestartServer(L2PcInstance activeChar)
	{
		// 再起動処理中ならば下記処理
		if (_autorestart) {
			showMessage(activeChar, "既にサーバー再起動処理に移行しています。");
			return;
		}

		// コマンド実行者が申請済かどうかチェック
		if (activeChar.voteServerRestart) {
			showMessage(activeChar, "既にサーバー再起動申請済です。");
			return;
		}

		// 条件付け
		if (activeChar.getLevel() < 10) {
			showMessage(activeChar, "レベル１０以上でなければ申請できません。");
			return;
		}

		// サーバ起動後、指定時間が経過しているかチェック
		if (System.currentTimeMillis() < GameServer.gameServer.serverLoadEnd + Config.CUSTOM_SERVER_RESTART_INTERVAL) {
			showMessage(activeChar, "サーバ起動後、規定時間が経過していません。");
			return;
		}

		// 攻城戦中かどうかチェック
		for (Castle castle : CastleManager.getInstance().getCastles()) {
			if (castle.getSiege().getIsInProgress()) {
				showMessage(activeChar, "攻城戦中はサーバー再起動を申請することができません。");
				return;
			}
		}

		activeChar.voteServerRestart = true;
//		showMessage(activeChar, "サーバー再起動申請を受付ました。");
		countRestartServer();

		if (_rate < Config.CUSTOM_SERVER_RESTART_RATE) {
			showMessage(activeChar, "サーバー再起動申請を受付ました。<br>"
					+ "希望者数が総ログイン数の " + Config.CUSTOM_SERVER_RESTART_RATE + "％ に達するまでログアウトせずにお待ち下さい。");
			return;
		}
		if (_score < Config.CUSTOM_SERVER_RESTART_MINIMUM) {
			showMessage(activeChar, "サーバー再起動申請を受付ました。<br>"
					+ Config.CUSTOM_SERVER_RESTART_MINIMUM + " 名以上の同意がなければサーバー再起動できません。");
			return;
		}

		_autorestart = true;
		showMessage(activeChar, "サーバー再起動が可決されました。");
		Announcements.getInstance().announceToAll("【サーバー再起動のお知らせ】");
		Announcements.getInstance().announceToAll("投票システムにより、サーバー再起動が可決されました。");
		Announcements.getInstance().announceToAll("これより当サーバーは再起動処理に移行します。");
		Announcements.getInstance().announceToAll("安全な場所でログアウトするようにしてください。");
		Shutdown.getInstance().startShutdown(null, Config.CUSTOM_SERVER_RESTART_TIME, true);
//		FavoriteMain.getInstance().showFavorite(activeChar);
	}
}