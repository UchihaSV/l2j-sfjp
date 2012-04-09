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
package com.l2jserver.gameserver.communitybbs.Manager;

import gnu.trove.iterator.TIntObjectIterator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javolution.util.FastList;

import com.l2jserver.Config;
import com.l2jserver.gameserver.GameServer;
import com.l2jserver.gameserver.GameTimeController;
import com.l2jserver.gameserver.datatables.ClassListData;
import com.l2jserver.gameserver.datatables.ExperienceTable;
import com.l2jserver.gameserver.model.BlockList;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.itemcontainer.PcInventory;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.CreatureSay;
import com.l2jserver.gameserver.network.serverpackets.ShowBoard;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.util.StringUtil;

public class RegionBBSManager extends BaseBBSManager
{
	private static Logger _logChat = Logger.getLogger("chat");
	
	private static final Comparator<L2PcInstance> playerNameComparator = new Comparator<L2PcInstance>()
	{
		@Override
		public int compare(L2PcInstance p1, L2PcInstance p2)
		{
			return p1.getName().compareToIgnoreCase(p2.getName());
		}
	};
	
	private RegionBBSManager()
	{
	}
	
	/**
	 * 
	 * @see com.l2jserver.gameserver.communitybbs.Manager.BaseBBSManager#parsecmd(java.lang.String, com.l2jserver.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (command.equals("_bbsloc"))
		{
			showOldCommunity(activeChar, 1);
		}
		else if (command.startsWith("_bbsloc;page;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();
			st.nextToken();
			int page = 0;
			try
			{
				page = Integer.parseInt(st.nextToken());
			}
			catch (NumberFormatException nfe)
			{
			}
			
			showOldCommunity(activeChar, page);
		}
		else if (command.startsWith("_bbsloc;playerinfo;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();
			st.nextToken();
			String name = st.nextToken();
			
			showOldCommunityPI(activeChar, name);
		}
		else
		{
			if (Config.COMMUNITY_TYPE == 1)
			{
				showOldCommunity(activeChar, 1);
			}
			else
			{
				ShowBoard sb = new ShowBoard("<html><body><br><br><center>コマンド： " + command
						+ " は未実装です。</center><br><br></body></html>", "101");
				activeChar.sendPacket(sb);
				activeChar.sendPacket(new ShowBoard(null, "102"));
				activeChar.sendPacket(new ShowBoard(null, "103"));
			}
		}
	}
	
	/**
	 * @param activeChar
	 * @param name
	 */
	private void showOldCommunityPI(L2PcInstance activeChar, String name)
	{
		final StringBuilder htmlCode = StringUtil.startAppend(1000, "<html><body><br>"
				+ "<table border=0><tr><td FIXWIDTH=15></td><td align=center>L2J Community Board<img src=\"sek.cbui355\" width=610 height=1></td></tr><tr><td FIXWIDTH=15></td><td>");
		L2PcInstance player = L2World.getInstance().getPlayer(name);
		
		if (player != null)
		{
			String sex = "男性";
			if (player.getAppearance().getSex())
			{
				sex = "女性";
			}
			String levelApprox = "低い";
			if (player.getLevel() >= 60)
				levelApprox = "非常に高い";
			else if (player.getLevel() >= 40)
				levelApprox = "高い";
			else if (player.getLevel() >= 20)
				levelApprox = "中くらい";
			
			StringUtil.append(htmlCode, "<table border=0><tr><td>", player.getName(), " (", sex, " ", ClassListData.getInstance().getClass(player.getClassId()).getClientCode(), "):</td></tr>"
					+ "<tr><td>レベル: ", levelApprox, "</td></tr>" + "<tr><td><br></td></tr>");
			
			if (activeChar != null
					&& (activeChar.isGM() || player.getObjectId() == activeChar.getObjectId() || Config.SHOW_LEVEL_COMMUNITYBOARD))
			{
				int level = player.getLevel();
				long currentExp = player.getExp() - ExperienceTable.getInstance().getExpForLevel(level);
				long nextLevelExp = ExperienceTable.getInstance().getExpForLevel(level + 1) - ExperienceTable.getInstance().getExpForLevel(level);
				long nextLevelExpNeeded = nextLevelExp - currentExp;
				double perExp = (double)currentExp / nextLevelExp;
				DecimalFormat dfExp = new DecimalFormat("0.00%");
				dfExp.setRoundingMode(RoundingMode.DOWN);
				StringUtil.append(htmlCode, "<tr><td>現在のレベル: ", String.valueOf(level), "</td></tr>" + "<tr><td>現在の経験値: ", String.valueOf(currentExp), "/", String.valueOf(nextLevelExp), " (", dfExp.format(new BigDecimal(perExp)), ")</td></tr>"
						+ "<tr><td>次のLvUPに必要な経験値: ", String.valueOf(nextLevelExpNeeded), "</td></tr>"
						+ "<tr><td><br></td></tr>");
			}
			
			StringUtil.append(htmlCode, "<tr><td>プレイ時間： ", com.l2jserver.util.Util.strTime((int)(player.getUptime() / 1000)), "</td></tr>"
					+ "<tr><td><br></td></tr>");
			
			if (player.getClan() != null)
			{
				StringUtil.append(htmlCode, "<tr><td>所属血盟: ", player.getClan().getName(), "</td></tr>" + "<tr><td><br></td></tr>");
			}
			
			StringUtil.append(htmlCode, "<tr><td><multiedit var=\"pm\" width=240 height=40><button value=\"メッセージ送信\" action=\"Write Region PM " , player.getName() , " pm pm pm\" width=110 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr>");
			if (activeChar.isGM())
			{
				StringUtil.append(htmlCode, "<tr><td><br><button value=\"強制呼出\" action=\"bypass -h admin_recall ", player.getName()
						, "\" width=80 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr>");
				if (player.getParty() != null)
					StringUtil.append(htmlCode, "<tr><td><br><button value=\"ＰＴ呼出\" action=\"bypass -h admin_recall_pt ", player.getName(), "\" width=80 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr>");
				StringUtil.append(htmlCode, "<tr><td><br><button value=\"自己転送\" action=\"bypass -h admin_teleportto ", player.getName()
						, "\" width=80 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr>");
			}
			StringUtil.append(htmlCode, "<tr><td><br><button value=\"戻る\" action=\"bypass _bbsloc\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td></tr></table>"
					+ "</td></tr></table>" + "</body></html>");
			separateAndSend(htmlCode.toString(), activeChar);
		}
		else
		{
			ShowBoard sb = new ShowBoard(StringUtil.concat("<html><body><br><br><center>プレイヤー: ", name, "はオフラインです。</center><br><br></body></html>"), "101");
			activeChar.sendPacket(sb);
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}
	}
	
	/**
	 * @param activeChar
	 * @param page 
	 */
	private void showOldCommunity(L2PcInstance activeChar, int page)
	{
		separateAndSend(getCommunityPage(page, activeChar.isGM() ? RegionBBSManager.FOR_GM/*"gm"*/ : RegionBBSManager.FOR_PLAYER/*"pl"*/), activeChar);
	}
	
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar == null)
			return;
		
		if (ar1.equals("PM"))
		{
			final StringBuilder htmlCode = StringUtil.startAppend(500, "<html><body><br>"
					+ "<table border=0><tr><td FIXWIDTH=15></td><td align=center>L2J コミュニティ<img src=\"sek.cbui355\" width=610 height=1></td></tr><tr><td FIXWIDTH=15></td><td>");
			
			try
			{
				
				L2PcInstance receiver = L2World.getInstance().getPlayer(ar2);
				if (receiver == null)
				{
					StringUtil.append(htmlCode, "相手が見つかりませんでした。<br><button value=\"戻る\" action=\"bypass _bbsloc;playerinfo;", ar2, "\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">"
							+ "</td></tr></table></body></html>");
					separateAndSend(htmlCode.toString(), activeChar);
					return;
				}
				if (Config.JAIL_DISABLE_CHAT && receiver.isInJail())
				{
					activeChar.sendMessage("相手はGM相談所に居ます。");
					return;
				}
				if (receiver.isChatBanned())
				{
					activeChar.sendPacket(SystemMessageId.TARGET_IS_CHAT_BANNED);
					return;
				}
				if (activeChar.isInJail() && Config.JAIL_DISABLE_CHAT)
				{
					activeChar.sendMessage("GM相談所ではチャットできません。");
					return;
				}
				if (activeChar.isChatBanned())
				{
					activeChar.sendPacket(SystemMessageId.CHATTING_IS_CURRENTLY_PROHIBITED);
					return;
				}
				
				if (Config.LOG_CHAT)
				{
					LogRecord record = new LogRecord(Level.INFO, ar3);
					record.setLoggerName("chat");
					record.setParameters(new Object[] { "TELL", "[" + activeChar.getName() + " to " + receiver.getName() + "]" });
					_logChat.log(record);
				}
				CreatureSay cs = new CreatureSay(activeChar.getObjectId(), Say2.TELL, activeChar.getName(), ar3);
				if (!receiver.isSilenceMode(activeChar.getObjectId()) && !BlockList.isBlocked(receiver, activeChar) )
				{
					receiver.sendPacket(cs);
					activeChar.sendPacket(new CreatureSay(activeChar.getObjectId(), Say2.TELL, "->" + receiver.getName(), ar3));
					StringUtil.append(htmlCode, "メッセージを送信しました。<br><button value=\"戻る\" action=\"bypass _bbsloc;playerinfo;", receiver.getName(), "\" width=40 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\">"
							+ "</td></tr></table></body></html>");
					separateAndSend(htmlCode.toString(), activeChar);
				}
				else
				{
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE);
					activeChar.sendPacket(sm);
					parsecmd("_bbsloc;playerinfo;" + receiver.getName(), activeChar);
				}
			}
			catch (StringIndexOutOfBoundsException e)
			{
				// ignore
			}
		}
		else
		{
			ShowBoard sb = new ShowBoard(StringUtil.concat("<html><body><br><br><center>コマンド： ", ar1, " は未実装です。</center><br><br></body></html>"), "101");
			activeChar.sendPacket(sb);
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}
		
	}
	private static final int _onlineCounts[] = new int[2];
	private static final int FOR_PLAYER = 0, FOR_GM = 1;
	@SuppressWarnings("unchecked")
	private static final FastList<String> _communityPages[] = new FastList[2];
		static {
			_communityPages[FOR_PLAYER] = new FastList<String>();
			_communityPages[FOR_GM] = new FastList<String>();
		}

	private static final String DATEFORMAT = "yyyy/MM/dd HH:mm";
	private static final String SHORTFORMAT = "H時 mm分";

	/**
	 * @return
	 */
	public static RegionBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private boolean _shutdown = false;
	public void shutdown()
	{
if (com.l2jserver.Config.FIX_DEADLOCK_ON_SHUTDOWN) {{
		_shutdown = true;
}}
	}
	
	public void changeCommunityBoard()
	{
if (com.l2jserver.Config.FIX_DEADLOCK_ON_SHUTDOWN) {{
		if (_shutdown) return;
}}
		final FastList<L2PcInstance> sortedPlayers = new FastList<L2PcInstance>();
		final TIntObjectIterator<L2PcInstance> it = L2World.getInstance().getAllPlayers().iterator();
		while (it.hasNext())
		{
			it.advance();
			if (it.value() != null)
			{
				sortedPlayers.add(it.value());
			}
		}
		Collections.sort(sortedPlayers, playerNameComparator);
		
		_onlineCounts[FOR_PLAYER] = 0;
		_onlineCounts[FOR_GM] = 0;
		
		for (L2PcInstance player : sortedPlayers)
		{
			addOnlinePlayer(player);
		}
		
		_communityPages[FOR_PLAYER].clear();
		_communityPages[FOR_GM].clear();

		writeCommunityPages(sortedPlayers);
	}

	private void addOnlinePlayer(L2PcInstance player)
	{
		++_onlineCounts[FOR_GM];
		if (! player.getAppearance().getInvisible())
			++_onlineCounts[FOR_PLAYER];
	}
	
	private void writeCommunityPages(FastList<L2PcInstance> sortedPlayers)
	{
		final String trOpen = "<tr>";
		final String trClose = "</tr>";
		final String tdOpen = "<td align=left valign=top>";
		final String colSpacer = "<td FIXWIDTH=15></td>";
		final String tdClose = "</td>";

		StringBuilder htmlCode;

		for (int type = 0; type <= 1; ++type)
		{
			//-------------
			// BBS HEADER
			//-------------
			htmlCode = new StringBuilder(2000);
			StringUtil.append(htmlCode, "<html><body><br>"
					+ "<center><table width=80><tr><td width=80><img src=l2ui.bbs_lineage2 height=16 width=80></td></tr><tr><td height=12></td></tr></table><center>"
					+ "<img src=\"sek.cbui355\" width=\"610\" height=\"1\"><br>"
			
					+ "<table width=630>"
			
					+ trOpen
					+ tdOpen + "ゲーム内時刻: %gametime%" + tdClose
					+ tdOpen + "サーバー実時刻: %realtime%" + tdClose
					+ tdOpen + "サーバー起動日時: ", new SimpleDateFormat(DATEFORMAT).format(GameServer.gameServer.serverLoadEnd) , tdClose
					+ trClose
			
					+ "</table>"
			
					+ "<table width=630>"
			
					+ trOpen
					+ tdOpen + "取得経験値倍率: ", String.valueOf(Config.RATE_XP), tdClose
					+ colSpacer
					+ tdOpen, "PT経験値ボーナス倍率: ", String.valueOf(Config.RATE_XP * Config.RATE_PARTY_XP), tdClose
					+ colSpacer
					+ tdOpen, "Lv差経験値ボーナス倍率: ", String.valueOf(Config.ALT_GAME_EXPONENT_XP), tdClose
					+ trClose
			
					+ trOpen
					+ tdOpen, "取得SP倍率: ", String.valueOf(Config.RATE_SP), tdClose
					+ colSpacer
					+ tdOpen, "PT SPボーナス倍率: ", String.valueOf(Config.RATE_SP * Config.RATE_PARTY_SP), tdClose
					+ colSpacer
					+ tdOpen, "Lv差SPボーナス倍率: ", String.valueOf(Config.ALT_GAME_EXPONENT_SP), tdClose
					+ trClose
			
					+ trOpen
					+ tdOpen, "ドロップ倍率: ", String.valueOf(Config.RATE_DROP_ITEMS), tdClose
					+ colSpacer
					+ tdOpen, "スポイル倍率: ", String.valueOf(Config.RATE_DROP_SPOIL), tdClose
					+ colSpacer
					+ tdOpen, "アデナ倍率: ", String.valueOf(Config.RATE_DROP_ITEMS_ID.get(PcInventory.ADENA_ID)), tdClose
					+ trClose
			
					+ "</table>"
			
					+ "<br><img src=\"sek.cbui355\" width=610 height=1><br>");
			
			if (type == FOR_GM)
				StringUtil.append(htmlCode, String.valueOf(L2World.getInstance().getAllVisibleObjectsCount()), " 体のNPCが存在しています。<br1>");
			StringUtil.append(htmlCode,  String.valueOf(_onlineCounts[type]), " 名のプレイヤーがオンラインです。<br1>");
			if (Config.BBS_SHOW_PLAYERLIST)
				StringUtil.append(htmlCode, "（GMプレイヤー名は黄色で表記）<br1>");
			
			StringBuilder htmlHeader = new StringBuilder(htmlCode);
			htmlCode = null;
			
			//-----------------
			// BBS PLAYER LIST
			//-----------------
			FastList<StringBuilder> pages = new FastList<StringBuilder>();
			
			if (Config.BBS_SHOW_PLAYERLIST)
			{
				int cells[] = new int[sortedPlayers.size() / Config.NAME_PAGE_SIZE_COMMUNITYBOARD + 3];
				int page = 0, cell = 0;
			
				final String tableOpen_1 = "<table border=0>";
				final String trOpen_2 = "<tr>";
				final String colSpacer_2 = colSpacer;
				final String trClose_2 = "</tr>";
				final String tableClose_1 = "</table><hr>";

				for (L2PcInstance player : sortedPlayers)
				{
					if (type == FOR_PLAYER && player.getAppearance().getInvisible())
						continue;	 // Go to next
					
					++cell;
					if (cell == 1)
					{
						++page;
						htmlCode = new StringBuilder(htmlHeader.length() + 2000)
								.append(htmlHeader)
								.append(tableOpen_1);
					}
					if (cell % Config.NAME_PER_ROW_COMMUNITYBOARD == 1) htmlCode.append(trOpen_2);
					
					StringUtil.append(htmlCode, "<td align=left valign=top FIXWIDTH=110><a action=\"bypass _bbsloc;playerinfo;", player.getName(), "\">");
					
					if (player.isGM())
					{
						StringUtil.append(htmlCode, "<font color=\"LEVEL\">", player.getName(), "</font>");
					}
					else if (Config.OFFLINE_SET_NAME_COLOR && (player.getClient() == null || player.getClient().isDetached()))
					{
						StringUtil.append(htmlCode, "<font color=\"", String.valueOf(Config.OFFLINE_NAME_COLOR), "\">", player.getName(), "</font>");
					}
					else
					{
						htmlCode.append(player.getName());
					}
					
					htmlCode.append("</a></td>");
					
					if (cell % Config.NAME_PER_ROW_COMMUNITYBOARD == 0)
						htmlCode.append(trClose_2);
					else
						htmlCode.append(colSpacer_2);
					
					if (cell == Config.NAME_PAGE_SIZE_COMMUNITYBOARD)
					{
						htmlCode.append(tableClose_1);
						pages.add(htmlCode);
						htmlCode = null;
						cells[page] = cell;
						cell = 0;
					}
				}
				
				if (cell % Config.NAME_PER_ROW_COMMUNITYBOARD != 0)
					htmlCode.append(trClose_2);
				
				if (cell > 0)
				{
					htmlCode.append(tableClose_1);
					pages.add(htmlCode);
					htmlCode = null;
					cells[page] = cell;
					cell = 0;
				}
				
				if (pages.size() >= 2)
				{
					page = 0;
					for (StringBuilder html : pages)
					{
						htmlCode = html; ++page;
						htmlCode.append("<br1><table border=0 width=600>"
							+ "<tr>");
						if (page == 1)
							htmlCode.append("<td align=right width=190></td>");
						else
							StringUtil.append(htmlCode, "<td align=right width=190><button value=\"前へ\" action=\"bypass _bbsloc;page;", String.valueOf(page - 1)
									, "\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
						htmlCode.append("<td FIXWIDTH=10></td>");
						int n1 = ((page - 1) * Config.NAME_PAGE_SIZE_COMMUNITYBOARD) + 1;
						int n2 = ((page - 1) * Config.NAME_PAGE_SIZE_COMMUNITYBOARD) + cells[page];
						StringUtil.append(htmlCode, "<td align=center valign=top width=200>", String.valueOf(n1), " - ", String.valueOf(n2), " のプレイヤーを表示中</td>"
							+ "<td FIXWIDTH=10></td>");
						if (page == pages.size())
							htmlCode.append("<td width=190></td>");
						else
							StringUtil.append(htmlCode, "<td width=190><button value=\"次へ\" action=\"bypass _bbsloc;page;", String.valueOf(page + 1)
									, "\" width=50 height=15 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
						htmlCode.append("</tr>"
							+ "</table>");
					}
				}
			}

			if (pages.size() == 0)
			{
				pages.add(htmlHeader);
			}

			//--------------------------
			// BBS HEADER + PLAYER LIST
			//--------------------------
			for (StringBuilder html : pages)
			{
				htmlCode = html;
				htmlCode.append("</body></html>");
				_communityPages[type].add(new String(htmlCode));
			}
			
		} /*for type*/
	}
	
	private String getCommunityPage(int page, int type)
	{
		FastList<String> pages = _communityPages[type];
		if (page < 1)
			page = 1;
		if (page > pages.size())
			page = pages.size();

		Calendar realtime = Calendar.getInstance();
		Calendar gametime = Calendar.getInstance();
		int t = GameTimeController.getInstance().getGameTime();
		gametime.set(Calendar.HOUR_OF_DAY, t / 60);
		gametime.set(Calendar.MINUTE, t % 60);

		return pages.get(page-1)
					.replaceFirst("%gametime%", new SimpleDateFormat(SHORTFORMAT).format(gametime.getTime()))
					.replaceFirst("%realtime%", new SimpleDateFormat(DATEFORMAT).format(realtime.getTime()))
					;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final RegionBBSManager _instance = new RegionBBSManager();
	}
}