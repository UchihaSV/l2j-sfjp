/*
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

import java.util.List;
import java.util.StringTokenizer;

import javolution.util.FastList;

import com.l2jserver.gameserver.communitybbs.BB.CustomComment;
import com.l2jserver.gameserver.communitybbs.BB.CustomMsg;
import com.l2jserver.gameserver.communitybbs.BB.CustomMsg.MsgType;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jserver.gameserver.network.serverpackets.ShowBoard;

/**
 * @author  TSL
 * @editor  JOJO
 */
public class CustomBBSManager extends BaseBBSManager
{
	private static CustomBBSManager _instance = new CustomBBSManager();

	private static final int MESSAGE_LIST_LIMIT = 12;
	private static final int COMMENT_LIST_LIMIT = 10;
	private static final int MEDIUM_LENGTH = 1000;	// 自動省略表示の文字数
	private static final int MINI_LENGTH = 170;		// 最小省略表示の文字数
	private static final int MINI_VIEW = 1, MEDIUM_VIEW = 2, FULL_VIEW = 3;	/*enum*/
	private static final int HIDE_EDIT = 0, SINGLE_EDIT = 1, MULTI_EDIT = 2;	/*enum*/

	/**
	 * @return
	 */
	public static CustomBBSManager getInstance()
	{
		return _instance;
	}

	/* (non-Javadoc)
	 * @see com.l2jserver.gameserver.communitybbs.Manager.BaseBBSManager#parsecmd(java.lang.String, com.l2jserver.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (activeChar == null) return;

		if (command.startsWith("_bbsmemo"))
		{
			command = "_bbscustom;msglist;memo";
		}
		else if (command.startsWith("_maillist"))
		{
			command = "_bbscustom;msglist;private";
		}

//		activeChar.TRACE("__BASENAME__:__LINE__: parsecmd("+command+")");
		if (command.startsWith("_bbscustom;"))
		{
			// "_bbscustom;[subcmd];[MsgType];..."
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();
			String subcmd = st.nextToken();
			MsgType msgType = MsgType.enumOf(st.nextToken());

			if (msgType.isClanmemberOnly && activeChar.getClan() == null) { authError(activeChar); return; }

			if (subcmd.equals("msglist"))
			{
				//_bbscustom;msglist;[MsgType]				// <= ClanBBSManager, html/CommunityBoad/index.html
				//_bbscustom;msglist;[MsgType];[message page]
				int msgPage = 1; if (st.hasMoreTokens()) msgPage = Integer.parseInt(st.nextToken());
				showMessageList(activeChar, msgType, msgPage);
			}
			else if (subcmd.equals("msgnew"))
			{
				//_bbscustom;msgnew;[MsgType]
				//_bbscustom;msgnew;private;[to_name]		// <= ClanBBSManager, FriendListManager
				if (msgType.isMail) {
					String toName = ""; if (st.hasMoreTokens()) toName = st.nextToken();
					String title = ""; if (st.hasMoreTokens()) title = st.nextToken();
					showMailNew(activeChar, msgType, toName, title, "");
				} else {
					showMessageNew(activeChar, msgType);
				}
			}
			else if (subcmd.equals("msgreply"))
			{
				//_bbscustom;msgreply;[MsgType];[msg_id]
				int msgId = Integer.parseInt(st.nextToken());
				showMailReply(activeChar, msgType, msgId);
			}
			else if (subcmd.equals("msgedit"))
			{
				//_bbscustom;msgedit;[MsgType];[msg_id]
				int msgId = Integer.parseInt(st.nextToken());
				showMessageEdit(activeChar, msgType, msgId);
			}
			else if (subcmd.equals("msgview"))
			{
				//_bbscustom;msgview;[MsgType];[msg_id];0;[message page];[comment page]
				//_bbscustom;msgview;[MsgType];[msg_id];0;[message page];[comment page];[view comment flag]
				//_bbscustom;msgview;[MsgType];[msg_id];0;[message page];[comment page];[view comment flag];[multi edit flag]
				int msgId = Integer.parseInt(st.nextToken());
				int comId = 0; st.nextToken(); /*comId*//*dummy*/
				int msgPage = Integer.parseInt(st.nextToken());
				int comPage = Integer.parseInt(st.nextToken());
				char zoom = '-'; if (st.hasMoreTokens()) zoom = st.nextToken().charAt(0);
				char multi = '-'; if (st.hasMoreTokens()) multi = st.nextToken().charAt(0);
				showMessageView(activeChar, msgType, msgId, comId, msgPage, comPage, zoom, multi);
			}
			else if (subcmd.equals("comview"))
			{
				//_bbscustom;comview;[MsgType];[msg_id];[com_id];[message page];[comment page]
				int msgId = Integer.parseInt(st.nextToken());
				int comId = Integer.parseInt(st.nextToken());
				int msgPage = Integer.parseInt(st.nextToken());
				int comPage = Integer.parseInt(st.nextToken());
				showCommentView(activeChar, msgType, msgId, comId, msgPage, comPage);
			}
			else if (subcmd.equals("msgdel"))
			{
				//_bbscustom;msgdel;[MsgType];[msg_id];0;[message page];0
				int msgId = Integer.parseInt(st.nextToken());
				st.nextToken(); /*int comId = Integer.parseInt(st.nextToken());*//*dummy*/
				int msgPage = Integer.parseInt(st.nextToken());
				st.nextToken(); /*int comPage = Integer.parseInt(st.nextToken());*//*dummy*/
				if (doMessageDelete(activeChar, msgType, msgId))
					showMessageList(activeChar, msgType, msgPage);
			}
			else if (subcmd.equals("comdel"))
			{
				//_bbscustom;comdel;[MsgType];[msg_id];[com_id];[message page];[comment page]
				int msgId = Integer.parseInt(st.nextToken());
				int comId = Integer.parseInt(st.nextToken());
				int msgPage = Integer.parseInt(st.nextToken());
				int comPage = Integer.parseInt(st.nextToken());
				if (doCommentDelete(activeChar, msgType, msgId, comId))
					showMessageView(activeChar, msgType, msgId, 0, msgPage, comPage);
			}
			else
			{
				separateAndSend("<html><body><br><br><center> the command: "+command+" is not implemented yet</center><br><br></body></html>", activeChar);
			}
		}
		else
		{
			separateAndSend("<html><body><br><br><center> the command: "+command+" is not implemented yet</center><br><br></body></html>", activeChar);
		}
	}

	/* (non-Javadoc)
	 * @see com.l2jserver.gameserver.communitybbs.Manager.BaseBBSManager#parsewrite(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.l2jserver.gameserver.model.actor.instance.L2PcInstance)
	 */
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar == null) return;
//		activeChar.TRACE("__BASENAME__:__LINE__: parsewrite(" + ar1 + "," + ar2 + "," + ar3 + "," + ar4 + "," + ar5 + ")");

		//ar2 = quoteString(ar2);
		ar3 = quoteString(ar3);
		ar4 = quoteString(ar4);
		ar5 = quoteString(ar5);

		StringTokenizer st = new StringTokenizer(ar2, ";");
		MsgType msgType = MsgType.enumOf(st.nextToken());

		if (ar1.equals("create"))
		{
			//action="Write Custom create [ar2]     [ar3]     [ar4]  "
			//action="Write Custom create [MsgType] [Content] [Title]"
//			//
//			// msgType ⇒ ar2 ⇒ msgType ⇒ msg_type
//			// Content ⇒ ar3 ⇒ message ⇒ message
//			// Title   ⇒ ar4 ⇒ title   ⇒ title
			if (! checkTitleAndContent(activeChar, ar4, ar3)) return;
			if (! CustomMsg.insert(msgType, "", ar4, ar3, activeChar))
			{
				errorDialog(activeChar, "CREATE ERROR!");
				return;
			}
			showMessageList(activeChar, msgType);
		}
		else if (ar1.equals("mail"))
		{
			//action="Write Custom mail [ar2]     [ar3]     [ar4]   [ar5]    "
			//action="Write Custom mail [MsgType] [Content] [Title] [Address]"	-- PRIVATEの新規と返信
//			//
//			// msgType ⇒ ar2 ⇒ msgType ⇒ msg_type
//			// Content ⇒ ar3 ⇒ message ⇒ message
//			// Title   ⇒ ar4 ⇒ title   ⇒ title
//			// Address ⇒ ar5 ⇒ toName  ⇒ to_name
			if (ar5.charAt(0) == ':') ar5 = ar5.substring(1);
			if (! checkTitleAndContent(activeChar, ar4, ar3)) return;
			if (! CustomMsg.insert(MsgType.PRIVATE, ar5, ar4, ar3, activeChar))
			{
				errorDialog(activeChar, "MAIL ERROR!");
				activeChar.sendPacket(SystemMessageId.MESSAGE_NOT_SENT);
				return;
			}
			activeChar.sendPacket(SystemMessageId.SENT_MAIL);
			L2PcInstance receiver = L2World.getInstance().getPlayer(ar5);
			if (receiver != null) receiver.sendPacket(SystemMessageId.NEW_MAIL);
			showMessageList(activeChar, msgType);
		}
		else if (ar1.equals("edit"))
		{
			//action="Write Custom edit [ar2]              [ar3]     [ar4]"
			//action="Write Custom edit [MsgType];[msg_id] [Content] [Title]"
			if (! checkTitleAndContent(activeChar, ar4, ar3)) return;
			int msgId = Integer.parseInt(st.nextToken());
			if (! CustomMsg.update(msgId, ar4, ar3, activeChar))
			{
				errorDialog(activeChar, "UPDATE ERROR!");
				return;
			}
			showMessageList(activeChar, msgType);
		}
		else if (ar1.equals("comment"))
		{
			//action="Write Custom comment [ar2]              [ar3]     [ar4]"
			//action="Write Custom comment [MsgType];[msg_id] [Content] [Content]"	//最後の Content はダミー
			if (ar3.length() > 256) { activeChar.sendPacket(SystemMessageId.ONE_LINE_RESPONSE_NOT_EXCEED_128_CHARACTERS); return; }
			int msgId = Integer.parseInt(st.nextToken());
			if (! CustomComment.insert(msgId, ar3, activeChar))
			{
				errorDialog(activeChar, "COMMENT ERROR!");
				return;
			}
			showMessageView(activeChar, msgType, msgId);
		}
		else
		{
			separateAndSend("<html><body><br><br><center>the command: " + ar1 + " is not implemented yet</center><br><br></body></html>", activeChar);
		}
	}

	private boolean checkTitleAndContent(L2PcInstance activeChar, String title, String content)
	{
		if (title.length() >  128) {
			activeChar.sendPacket(SystemMessageId.PLEASE_INPUT_TITLE_LESS_128_CHARACTERS);
			return false;
		}
		if (content.length() > 3000) {
			activeChar.sendPacket(SystemMessageId.PLEASE_INPUT_CONTENT_LESS_3000_CHARACTERS);
			return false;
		}
		return true;
	}

	/**
	 *  → 中身は CustomMsg に引越し
	 * @param activeChar
	 * @return 未読メールの件数
	 */
	public int getPrivateMsgCnt(L2PcInstance activeChar)
	{
		return CustomMsg.getPrivateMsgCnt(activeChar);
	}

	/**
	 * @param msgType
	 * @param activeChar
	 * @param page
	 */
	private void showMessageList(L2PcInstance activeChar, MsgType msgType)
	{
		showMessageList(activeChar, msgType, 1);
	}
	private void showMessageList(L2PcInstance activeChar, MsgType msgType, int msgPage)
	{
		if (msgPage < 1) msgPage = 1;

		List<CustomMsg> msgTable = new FastList<CustomMsg>();
		int msgTotal = CustomMsg.list(msgTable, msgType, activeChar, (msgPage-1)*MESSAGE_LIST_LIMIT, MESSAGE_LIST_LIMIT);
		if (msgTotal < 0) {
			separateAndSend("<html><body><br><br><center><font color=FF0000>BBS ERROR!</font></center></body></html>", activeChar);
			return;
		}
		int nbp = 1 + (msgTotal-1) / MESSAGE_LIST_LIMIT;
		if (msgPage > nbp) msgPage = nbp;

		StringBuilder html = new StringBuilder(8192);
		html.append("<html><body>");
		appendTopTag(html, msgType);
		html.append("<center>");

		//[416番号][413題目][417作成者][418作成日][423コメント]
		html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=888888>");
		html.append("<tr>");
			html.append("<td WIDTH=60 align=right>&$416;</td>");
			html.append("<td WIDTH=435 align=center>&$413;</td>");
			html.append("<td WIDTH=110 align=center>&$417;</td>");
			html.append("<td WIDTH=72 align=center>&$418;</td>");
			if (msgType.canComment)
				html.append("<td WIDTH=60 align=right>&$423;</td>");
			else if (msgType.isMail)
				html.append("<td WIDTH=60 align=right></td>");
			else
				html.append("<td WIDTH=28 align=right></td>");
		html.append("</tr>");
		html.append("</table>");

		int cnt = 0;
		for (CustomMsg tmpMsg : msgTable)
		{
			//   番号                                題目                                          作成者          作成日  コメント
			//|99999999 ###################################################################### &&&&&&&&&&&&&&&& 99/99/99 99:99 9999
			//|--- 8---|----------------------------- 70--------------------------------------|------ 16-------|------ 14-----|- 4-| 文字数
			//|---60---|-----------------------------435--------------------------------------|------110-------|------100-----|-32-| ピクセル計737
			if (cnt % 2 == 0)
				html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=333333>");
			else
				html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=444444>");
			html.append("<tr>");
			html.append("<td FIXWIDTH=60 align=right>" + tmpMsg.getMsgId() + "</td>");
			html.append("<td FIXWIDTH=435 align=left>"
					+ "<a action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + tmpMsg.getMsgId() + ";0;" + msgPage + ";0\">"
					+ htmlescape(cut(tmpMsg.getTitle(), 70)) + "</a></td>");
			html.append("<td FIXWIDTH=110 align=right>" + tmpMsg.getFromName() + "</td>");	//キャラクター名＝max 16
//|			html.append("<td FIXWIDTH=110 align=right>" + cut(tmpMsg.getFromName(), xx) + "</td>");	//キャラクター名＝max 16
			html.append("<td FIXWIDTH=100 align=left>" + tmpMsg.getDate() + "</td>");
			if (msgType.canComment)
				html.append("<td FIXWIDTH=32 align=right>" + tmpMsg.getComCount() + "</td>");
			else if (msgType.isMail)
				html.append("<td FIXWIDTH=32 align=center>")
					.append(tmpMsg.getReadFlg() ? "\u2713" : "<font color=FF0000>new!</font>")
					.append("</td>");
			html.append("</tr>");
			html.append("</table>");
			cnt++;
		}
		for (; cnt < MESSAGE_LIST_LIMIT /*+2*/; cnt++) {
			if (cnt % 2 == 0)
				html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=333333>");
			else
				html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=444444>");
			if (msgType.canComment || msgType.isMail)
				html.append("<tr><td WIDTH=737>\u3000</td></tr></table>");	//60+435+110+100+32 = 737
			else
				html.append("<tr><td WIDTH=705>\u3000</td></tr></table>");	//60+435+110+100 = 705
		}
//if (false) {{
//	/*XXX:レイアウト テスト用*/
//			if (cnt % 2 == 0)
//				html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=333333>");
//			else
//				html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=444444>");
//			html.append("<tr>");
//			html.append("<td WIDTH=60 align=right>99999999</td>");
//			html.append("<td WIDTH=435 align=left><a action=\"bypass \">" + htmlescape(cut("タイトル######################################################################", 70)) + "</a></td>");
//			html.append("<td WIDTH=110 align=right>うんこちゃん７８</td>");
//			html.append("<td WIDTH=100 align=left>99/99/99 99:99</td>");
//			if (msgType.canComment)
//				html.append("<td WIDTH=32 align=right>9999</td>");
//			else if (msgType.isMail)
//				html.append("<td WIDTH=32 align=center>\u2713</td>");
//			html.append("</tr>");
//			html.append("</table>");
//}}

		// | [422リストへ]    << 1 2 3 4 5 6 7 8 9 10 >>    [421新規作成] |
		// |-|-whdth=65--|------------whdth=600-------------|--whdth=65-|-|
		html.append("<br>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=730>");
		html.append("<tr>");
			html.append("<td>");
			html.append("<button value=\"&$422;\" action=\"bypass _bbscustom;msglist;"+ msgType.toString() + "\""
						+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df>");
			html.append("</td>");

			html.append("<td width=600 align=center>");
			html.append("<table border=0 cellspacing=6 cellpadding=0><tr>");
				int startIdx = 1 + (msgPage-1) / 10 * 10;
				int nextIdx = startIdx + 10;
		
				html.append("<td><button");
				 if (startIdx - 1 >= 1) html.append(" action=\"bypass _bbscustom;msglist;" + msgType.toString() + ";" + (startIdx - 1) + "\"");
				html.append(" width=16 height=16 back=l2ui_ch3.prev1_down fore=l2ui_ch3.prev1></td>");
		
				html.append("<td><button");
				 if (msgPage - 1 >= 1) html.append(" action=\"bypass _bbscustom;msglist;" + msgType.toString() + ";" + (msgPage - 1) + "\"");
				html.append(" width=16 height=16 back=l2ui_ch3.prev1_down fore=l2ui_ch3.prev1></td>");
		
				for (int i = startIdx; i <= nbp && i < nextIdx; i++)
				{
					html.append("<td>");
					if (i == msgPage)
						html.append(i);
					else
						html.append("<a action=\"bypass _bbscustom;msglist;" + msgType.toString() + ";" + i + "\">" + i + "</a>");
					html.append("</td>");
				}
		
				html.append("<td><button");
				 if (msgPage + 1 <= nbp) html.append(" action=\"bypass _bbscustom;msglist;" + msgType.toString() + ";" + (msgPage + 1) + "\"");
				html.append(" width=16 height=16 back=l2ui_ch3.next1_down fore=l2ui_ch3.next1></td>");
		
				html.append("<td><button");
				 if (nextIdx <= nbp)  html.append(" action=\"bypass _bbscustom;msglist;" + msgType.toString() + ";" + nextIdx + "\"");
				html.append(" width=16 height=16 back=l2ui_ch3.next1_down fore=l2ui_ch3.next1></td>");
			html.append("</tr></table>");
			html.append("</td>");

			html.append("<td width=65 align=right>");
			if (authNew(activeChar, msgType))
				html.append("<button value=\"&$421;\" action=\"bypass _bbscustom;msgnew;"+ msgType.toString() + "\""
							+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df>");
			html.append("</td>");
		html.append("</tr>");
		html.append("</table>");

 //if (false) {{
 //		/** 未実装 **/
 //		//[420検索][  Search  ][913送信]
 //		html.append("<img src=L2UI.squareblank width=1 height=5>"
 //				+ "<img src=L2UI.squaregray width=610 height=1>"
 //				+ "<img src=L2UI.squareblank width=1 height=5>");
 //		html.append("<table border=0 cellspacing=0><tr>");
 //			html.append("<td FIXWIDTH=65 align=right>&$420;</td>");
 //			html.append("<td><edit var=\"Search\" width=150></td>");
 //			html.append("<td><button value=\"&$913;\" action=\"Write Custom search " + msgType.toString() + " Search Search\""
 //					+ " width=65 height=20 L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
 //		html.append("</tr></table>");
 //}}

		html.append("</center>");
		appendBottomTag(html);
		html.append("</body>");
		html.append("</html>");

		separateAndSend(html.toString(), activeChar);
	}

	private void showMessageNew(L2PcInstance activeChar, MsgType msgType)
	{
		if (! authNew(activeChar, msgType)) { authError(activeChar); return; }
		if (msgType.isMail) {
			throw new java.lang.IllegalArgumentException("showMessageNew("+activeChar.getName()+","+msgType.toString()+")");
		}

		StringBuilder html = new StringBuilder(8192);
		html.append("<html><body>");
		appendTopTag(html, msgType);
		html.append("<center>");

		html.append("<table border=0 cellspacing=0 cellpadding=5>");
		//[909宛先]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center>&$909;</td>");
			switch (msgType)
			{
			case CLAN:
				html.append("<td FIXWIDTH=540>"+activeChar.getClan().getName()+"</td>");
				break;
			default:
				html.append("<td FIXWIDTH=540>"+msgType.getCaption()+"</td>");
			}
		html.append("</tr>");
		//[413題目][Title]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center>&$413;</td>");
			html.append("<td><edit var=\"Title\" width=540></td>");
		html.append("</tr>");
		//[427本文][Content]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center valign=top>&$427;</td>");
			html.append("<td><MultiEdit var=\"Content\" width=540 height=300></td>");
		html.append("</tr>");
		html.append("</table>");

		//[140;OK][141キャンセル]
		html.append("<img src=L2UI.squareblank width=1 height=5>"
				+ "<img src=L2UI.squaregray width=610 height=1>"
				+ "<img src=L2UI.squareblank width=1 height=5>");
		html.append("<table border=0 cellspacing=0 cellpadding=5><tr>");
			html.append("<td><button value=\"&$140;\"");
				html.append(" action=\"Write Custom create " + msgType.toString() + " Content Title\"");
			html.append(" width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
			html.append("<td>"
					+ "<button value=\"&$141;\" action=\"bypass _bbscustom;msglist;"+ msgType.toString() + "\""
					+ " width=75 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
			html.append("<td WIDTH=350></td>");
		html.append("</tr></table>");

		html.append("</center>");
		appendBottomTag(html);
		html.append("</body>");
		html.append("</html>");

		separateAndSend(html.toString(),activeChar);
	}

	private void showMailReply(L2PcInstance activeChar, MsgType msgType, int msgId)
	{
		CustomMsg curMsg = new CustomMsg().load(msgId);
		if (! curMsg.isSuccess())
			return;
		if (msgType == MsgType.MEMO)
		{
			// memo ==> mail
			String title = curMsg.getTitle();
			String message = curMsg.getMessage();
			curMsg = null;
			showMailNew(activeChar, msgType, ":", title, message);
		}
		else
		{
			// mail reply, topic reply.
			String toName = curMsg.getFromName();
			String title = curMsg.getTitle();
			curMsg = null;
			if (! title.toLowerCase().startsWith("re:")) title = "Re:" + title;
			showMailNew(activeChar, msgType, toName, title, "");
		}
	}
	private void showMailNew(L2PcInstance activeChar, MsgType msgType, String address, String title, String content)
	{
		if (! authReply(activeChar, msgType)) { authError(activeChar); return; }

		StringBuilder html = new StringBuilder(8192);
		html.append("<html><body>");
		appendTopTag(html, msgType);
		html.append("<center>");

		html.append("<table border=0 cellspacing=0 cellpadding=5>");
		//[909宛先][Address]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center>&$909;</td>");
			html.append("<td><edit var=\"Address\" width=540></td>");
		html.append("</tr>");
		//[413題目][Title]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center>&$413;</td>");
			html.append("<td><edit var=\"Title\" width=540></td>");
		html.append("</tr>");
		//[427本文][Content]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center valign=top>&$427;</td>");
			html.append("<td><MultiEdit var=\"Content\" width=540 height=300></td>");
		html.append("</tr>");
		html.append("</table>");

		//[140;OK][141キャンセル]
		html.append("<img src=L2UI.squareblank width=1 height=5>"
				+ "<img src=L2UI.squaregray width=610 height=1>"
				+ "<img src=L2UI.squareblank width=1 height=5>");
		html.append("<table border=0 cellspacing=0 cellpadding=5><tr>");
			html.append("<td><button value=\"&$140;\"");
				html.append(" action=\"Write Custom mail " + msgType.toString() + " Content Title Address\"");
			html.append(" width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
			html.append("<td>"
					+ "<button value=\"&$141;\" action=\"bypass _bbscustom;msglist;"+ msgType.toString() + "\""
					+ " width=75 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
			html.append("<td WIDTH=350></td>");
		html.append("</tr></table>");

		html.append("</center>");
		appendBottomTag(html);
		html.append("</body>");
		html.append("</html>");

		if (html.length() >= 8180) {
			separateAndSend("<html><body><br><br><center><font color=FF0000>PACKET TOO LARGE!</font></center></body></html>", activeChar);
			return;
		}
		send1001(html.toString(), activeChar);
		send1002(activeChar, content, title, address);
//|		send1002(activeChar, "", title, address);
	}

	/**
	 * @param msgType
	 * @param activeChar
	 * @param msgId
	 */
	private void showMessageEdit(L2PcInstance activeChar, MsgType msgType, int msgId)
	{
		CustomMsg curMsg = new CustomMsg().load(msgId);
		if (! authEdit(activeChar, msgType, curMsg)) { authError(activeChar); return; }

		StringBuilder html = new StringBuilder(8192);
		html.append("<html><body>");
		appendTopTag(html, msgType);
		html.append("<center>");

		html.append("<table border=0 cellspacing=0 cellpadding=5>");
		//[909宛先]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center>&$909;</td>");
			switch (msgType)
			{
			case PRIVATE:
				html.append("<td FIXWIDTH=540>"+curMsg.getToName()+"</td>");
				break;
			case CLAN:
				html.append("<td FIXWIDTH=540>"+activeChar.getClan().getName()+"</td>");
				break;
			default:
				html.append("<td FIXWIDTH=540>"+msgType.getCaption()+"</td>");
			}
		html.append("</tr>");

		//[413題目]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center>&$413;</td>");
			html.append("<td><edit var=\"Title\" width=540></td>");
		html.append("</tr>");
		//[427本文]
		html.append("<tr>");
			html.append("<td WIDTH=60 align=center valign=top>&$427;</td>");
			html.append("<td><MultiEdit var=\"Content\" width=540 height=300></td>");
		html.append("</tr>");
		html.append("</table>");

		//[140;OK][141キャンセル]
		html.append("<img src=L2UI.squareblank width=1 height=5>"
				+ "<img src=L2UI.squaregray width=610 height=1>"
				+ "<img src=L2UI.squareblank width=1 height=5>");
		html.append("<table border=0 cellspacing=0 cellpadding=5><tr>");
			html.append("<td>"
					+"<button value=\"&$140;\" action=\"Write Custom edit " + msgType.toString() + ";" + curMsg.getMsgId() + " Content Title\""
					+ " width=65 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
			html.append("<td>"
					+ "<button value=\"&$141;\" action=\"bypass _bbscustom;msglist;"+ msgType.toString() + "\""
					+ " width=75 height=20 back=\"L2UI_ct1.button_df\" fore=\"L2UI_ct1.button_df\"></td>");
			html.append("<td WIDTH=350></td>");
		html.append("</tr></table>");

		html.append("</center>");
		appendBottomTag(html);
		html.append("</body>");
		html.append("</html>");

		if (html.length() >= 8180) {
			separateAndSend("<html><body><br><br><center><font color=FF0000>PACKET TOO LARGE!</font></center></body></html>", activeChar);
			return;
		}
		send1001(html.toString(), activeChar);
		send1002(activeChar, curMsg.getMessage(), "", curMsg.getTitle());
	}

	private void showMessageView(L2PcInstance activeChar, MsgType msgType, int msgId)
	{
		showMessageView(activeChar, msgType, msgId, 0, 1, 1, '-', '-');
	}
	private void showMessageView(L2PcInstance activeChar, MsgType msgType, int msgId, int dummy1, int msgPage, int comPage)
	{
		showMessageView(activeChar, msgType, msgId, dummy1, msgPage, comPage, '-', '-');
	}
	/**
	 * @param activeChar
	 * @param msgType
	 * @param msgId
	 * @param comId
	 * @param msgPage
	 * @param comPage
	 * @param fullMsg true = View full message, don't show comments. false = Auto, show comments.
	 * @param multiCom true = Input multi line comment. false = 1 line comment.
	 */
	private void showMessageView(L2PcInstance activeChar, MsgType msgType, int msgId, int dummy1, int msgPage, int comPage, char fullMsg, char multiCom)
	{
//		activeChar.TRACE("__BASENAME__:__LINE__: showMessageView("+activeChar.getName()+","+msgType+","+msgId+","+dummy1+","+msgPage+","+comPage+","+fullMsg+","+multiCom+")");
		if (msgPage < 1) msgPage = 1;
		if (comPage < 1) comPage = 1;

		int msgViewStyle;	// 本文を全部表示するか、省略表示するか
			if (fullMsg == 'F') msgViewStyle = FULL_VIEW;
			else if (comPage == 1) msgViewStyle = MEDIUM_VIEW;
			else msgViewStyle = MINI_VIEW;
		boolean showComList = msgType.canComment;	// コメントリストを表示するか
			if (msgViewStyle == FULL_VIEW) showComList = false;

		int comInputStyle;	// コメント入力 0=なし、1=あり
			if (! msgType.canComment) comInputStyle = HIDE_EDIT;
			else if (! authNew(activeChar, msgType)) comInputStyle = HIDE_EDIT;
			else if (multiCom == 'M') comInputStyle = MULTI_EDIT;
			else comInputStyle = SINGLE_EDIT;
			
		CustomMsg curMsg = new CustomMsg().load(msgId);

		List<CustomComment> comTable = null;
		int comTotal = -1, nbp = 0;
		if (showComList) {
			//「一言コメント」
			comTable = new FastList<CustomComment>();
			comTotal = CustomComment.list(comTable, msgId, (comPage-1)*COMMENT_LIST_LIMIT, COMMENT_LIST_LIMIT);
			if (comTotal < 0) {
				separateAndSend("<html><body><br><br><center><font color=FF0000>COMMENT ERROR!</font></center></body></html>", activeChar);
				return;
			}
			nbp = 1 + (comTotal-1) / COMMENT_LIST_LIMIT;
			if (comPage > nbp) comPage = nbp;
			if (comTotal == 0) msgViewStyle = FULL_VIEW;
		}
//		activeChar.TRACE("    └ msgViewStyle=" + msgViewStyle);
//		activeChar.TRACE("    └ comInputStyle=" + comInputStyle);

		StringBuilder html = new StringBuilder(8192);
		html.append("<html><body>");
		appendTopTag(html, msgType);
		html.append("<center>");

		//[413題目]
		html.append("<table border=0 cellspacing=0 cellpadding=5 width=570 bgcolor=444444>");
			html.append("<tr>");
				html.append("<td WIDTH=60 align=right>&$413; : </td>");
				html.append("<td FIXWIDTH=510>" + htmlescape(curMsg.getTitle()) + "</td>");
			html.append("</tr>");
		html.append("</table>");
		//[417作成者]  [418作成日]
		html.append("<table border=0 cellspacing=0 cellpadding=5 width=570 bgcolor=444444>");
			html.append("<tr>");
				html.append("<td WIDTH=60 align=right>&$417; : </td>");
				html.append("<td FIXWIDTH=350>"+curMsg.getFromName()+"</td>");
				html.append("<td WIDTH=60 align=right>&$418; : </td>");
				html.append("<td WIDTH=100>"+curMsg.getDate()+"</td>");
			html.append("</tr>");
		html.append("</table>");
		//(本文)
		html.append("<table border=0 cellspacing=0 cellpadding=5 width=570 bgcolor=333333>");
			html.append("<tr>");
				html.append("<td FIXWIDTH=600 height=66>");
				if (msgViewStyle == MINI_VIEW && curMsg.getMessage().length() > MINI_LENGTH)
				{
					//_bbscustom;msgview;[MsgType];[msg_id];0;[message page][comment page];F
					//ex. <td>The message<a action="bypass _bbscustom;msgview;default;1234567;0;3;2;F">...</a></td>
					String mes = curMsg.getMessage().substring(0, MINI_LENGTH).replace("\r", "").replaceAll("\n+", " ");
					html.append(htmlescape(mes));
					html.append("<a action=\"bypass _bbscustom;msgview;"+msgType.toString()+";"+msgId+";0;"+msgPage+";"+comPage+";F\">\u2026</a>");
				}
				else if (msgViewStyle == MEDIUM_VIEW && curMsg.getMessage().length() > MEDIUM_LENGTH + 60)
				{
					//_bbscustom;msgview;[MsgType];[msg_id];0;[message page][comment page];F
					//ex. <td>The message<a action="bypass _bbscustom;msgview;default;1234567;0;3;2;F">...</a></td>
					//Overhead: "<a action >...</a>".length() == about 60 characters
					String mes = curMsg.getMessage().substring(0, MEDIUM_LENGTH);
					html.append(htmlescape(mes));
					html.append("<a action=\"bypass _bbscustom;msgview;"+msgType.toString()+";"+msgId+";0;"+msgPage+";"+comPage+";F\">\u2026</a>");
				}
				else
				{
					html.append(htmlescape(curMsg.getMessage()));
				}
				html.append("</td>");
			html.append("</tr>");
		html.append("</table>");

		//[422リストへ][161戻る]    [424編集][425削除][426返信][421新規作成]

		html.append("<table border=0 cellspacing=0 cellpadding=0 width=730><tr>");
			html.append("<td>");
			if (fullMsg == 'F')
				html.append("<button value=\"&$161;\" action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + comPage + "\""
						+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df>");
			else
				html.append("<button value=\"&$422;\" action=\"bypass _bbscustom;msglist;"+ msgType.toString() + ";" + msgPage + "\""
						+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df>");
			html.append("</td>");
			html.append("<td width=665 align=right><table border=0 cellspacing=0 cellpadding=0><tr>");
				if (authReply(activeChar, msgType))
					html.append("<td> <button value=\""+(msgType.isMail?"&$426;":"&$905;")+"\" action=\"bypass _bbscustom;msgreply;" + msgType.toString() + ";" + msgId + "\""
							+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
				if (authEdit(activeChar, msgType, curMsg))
					html.append("<td> <button value=\"&$424;\" action=\"bypass _bbscustom;msgedit;"  + msgType.toString() + ";" + msgId + "\""
							+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
				if (authDelete(activeChar, msgType, curMsg))
					html.append("<td> <button value=\"&$425;\" action=\"bypass _bbscustom;msgdel;"   + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";0\""
							+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
				if (authNew(activeChar, msgType))
					html.append("<td> <button value=\"&$421;\" action=\"bypass _bbscustom;msgnew;"   + msgType.toString() + "\""
							+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
			html.append("</tr></table></td>");
		html.append("</tr></table>");

		if (comInputStyle != HIDE_EDIT) {
			/**
			 * 「一言コメント」入力
			 */
				//[423コメント]▽[  Content  ][913送信]
				html.append("<img src=L2UI.squareblank width=1 height=5>"
						+ "<img src=L2UI.squaregray width=750 height=1>"
						+ "<img src=L2UI.squareblank width=1 height=5>");
				html.append("<table border=0 cellspacing=0 cellpadding=4><tr>");
				if (comInputStyle == SINGLE_EDIT) {
					html.append("<td WIDTH=70 align=right valign=top>"
							+ "<a action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + comPage + ";" + fullMsg + ";M\">\u25BD</a>"
							+ "&$423;</td>");
					html.append("<td><edit var=\"Content\" width=540></td>");
				} else /*MULTI_EDIT*/ {
					html.append("<td WIDTH=70 align=right valign=top>"
							+ "<a action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + comPage + ";" + fullMsg + ";-\">\u25B3</a>"
							+ "&$423;</td>");
					html.append("<td><MultiEdit var=\"Content\" width=540 height=80></td>");
				}
				html.append("<td valign=top>"
						+ "<button value=\"&$913;\" action=\"Write Custom comment " + msgType.toString() + ";" + msgId + " Content Content\""
						+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df></td>");
				html.append("</tr></table>");
		}
		if (showComList) {
			/**
			 * 「一言コメント」一覧
			 */
			if (comTotal == 0)
			{
				html.append("<img src=L2UI.squareblank width=1 height=20>");
			}
			else /*if (comTotal >= 1)*/
			{
				// [<][<] [423コメント] [>][>]
				html.append("<table border=0 cellspacing=0 cellpadding=4>");
				html.append("<tr>");
				if (nbp >= 3)
				{
					html.append("<td WIDTH=16><button");
					 if (comPage > 1) html.append(" action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + (1) + "\"");
					html.append(" width=16 height=16 back=l2ui_ch3.prev1_down fore=l2ui_ch3.prev1></td>");
				}
				if (nbp >= 2)
				{
					html.append("<td WIDTH=16><button");
					 if (comPage > 1) html.append(" action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + (comPage - 1) + "\"");
					html.append(" width=16 height=16 back=l2ui_ch3.prev1_down fore=l2ui_ch3.prev1></td>");
				}
				html.append("<td WIDTH=70 align=center>&$423;</td>");
				if (nbp >= 2)
				{
					html.append("<td WIDTH=16><button");
					 if (comPage < nbp) html.append(" action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + (comPage + 1) + "\"");
					html.append(" width=16 height=16 back=l2ui_ch3.next1_down fore=l2ui_ch3.next1></td>");
				}
				if (nbp >= 3)
				{
					html.append("<td WIDTH=16><button");
					 if (comPage < nbp) html.append(" action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + (nbp) + "\"");
					html.append(" width=16 height=16 back=l2ui_ch3.next1_down fore=l2ui_ch3.next1></td>");
				}
				html.append("</tr>");
				html.append("</table>");

				int cnt = 0;
				for (CustomComment tmpCom : comTable) {
					if (cnt % 2 == 0)
						html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=444444>");
					else
						html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=333333>");
					html.append("<tr>");
					html.append("<td FIXWIDTH=40 align=right valign=top>" + tmpCom.getComId() + "</td>");
					html.append("<td FIXWIDTH=435 align=left valign=top>");
					String m = tmpCom.getMessage();
					if (m.length() > MINI_LENGTH + 60) {
						//_bbscustom;comview;[MsgType];[msg_id];[com_id];[message page];[comment page]
						//ex. <td>The comment<a action="bypass _bbscustom;comview;default;1234567;1;1;1">...</a></td>
						html.append(htmlescape(m.substring(0, MINI_LENGTH)));
						html.append("<a action=\"bypass _bbscustom;comview;"+msgType.toString()+";"+msgId+";"+tmpCom.getComId()+";"+msgPage+";"+comPage+"\">\u2026</a>");	//約60文字
					}
					else
					{
						html.append(htmlescape(m));
					}
					m = null;
					html.append("</td>");

					html.append("<td FIXWIDTH=110 align=right valign=bottom>");
						if (tmpCom.isOwn(activeChar))
							html.append("<font color=00FF00>" + tmpCom.getFromName() + "</font>");
						else if (tmpCom.getFromName().equals(curMsg.getFromName()))
							html.append("<font color=00FFFF>" + tmpCom.getFromName() + "</font>");
						else
							html.append(tmpCom.getFromName());
					html.append("</td>");
					html.append("<td FIXWIDTH=92 align=left valign=bottom>" + tmpCom.getDate() + "</td>");
					html.append("<td FIXWIDTH=24 align=center valign=bottom>"); /**/ {
						String caption;
						if (tmpCom.isOwn(activeChar)) caption = "<font color=FF0000>\u2715</font>";		//owner delete
						else if (activeChar.isGM()) caption = "<font color=FFFF00>\u2717</font>";		//GM delete
						else caption = null;
						if (caption != null)
							html.append("<a action=\"bypass _bbscustom;comdel;"+msgType.toString()+";"+msgId+";"+tmpCom.getComId()+";"+msgPage+";"+comPage+"\">"
									+caption+"</a>");
					}
		 			html.append("</td>");
					html.append("</tr>");
					html.append("</table>");
					cnt++;
				}
//if (false) {{
///*XXX:レイアウト テスト用*/
//				if (cnt % 2 == 0)
//					html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=444444>");
//				else
//					html.append("<table border=0 cellspacing=0 cellpadding=4 bgcolor=333333>");
//				html.append("<tr>");
//				html.append("<td FIXWIDTH=40 align=right valign=top>9999</td>");
//				html.append("<td FIXWIDTH=435 align=left valign=top>＃＃＃＃＃こめんと文＃＃＃＃＃</td>");
//				html.append("<td FIXWIDTH=110 align=right valign=bottom>１２３４５６７８９０１２３４</td>");
//				html.append("<td FIXWIDTH=100 align=left valign=bottom>99/99/99 99:99</td>");
//				html.append("<td FIXWIDTH=24 align=center valign=bottom><font color=FF0000>\u2717</font></td>");	//XXX:
//				html.append("</tr>");
//				html.append("</table>");
//}}
			}
		}

		html.append("</center>");
		appendBottomTag(html);
		html.append("</body>");
		html.append("</html>");

		separateAndSend(html.toString(),activeChar);

		/*-----------------------------------------*/
		if (msgType.isMail) curMsg.setReadFlg(true);
		/*-----------------------------------------*/
	}

	private void showCommentView(L2PcInstance activeChar, MsgType msgType, int msgId, int comId, int msgPage, int comPage)
	{
		CustomComment curCom = new CustomComment().load(msgId, comId);
		if (curCom == null) {
			separateAndSend("<html><body><br><br><center><font color=FF0000>COMMENT ERROR!</font></center></body></html>", activeChar);
			return;
		}

		StringBuilder html = new StringBuilder(8192);
		html.append("<html><body>");
		appendTopTag(html, msgType);
		html.append("<center>");

		//[413題目]
		html.append("<table border=0 cellspacing=0 cellpadding=5 width=570 bgcolor=444444>");
			html.append("<tr>");
				html.append("<td FIXWIDTH=60 align=right>&$413; : </td>");
				html.append("<td FIXWIDTH=510>Re:" + htmlescape(curCom.getTitle()) + "  #" + comId + "</td>");
			html.append("</tr>");
		html.append("</table>");
		//[417作成者]  [418作成日]
		html.append("<table border=0 cellspacing=0 cellpadding=5 width=570 bgcolor=444444>");
			html.append("<tr>");
				html.append("<td FIXWIDTH=60 align=right>&$417; : </td>");
				html.append("<td FIXWIDTH=350>"+curCom.getFromName()+"</td>");
				html.append("<td FIXWIDTH=60 align=right>&$418; : </td>");
				html.append("<td FIXWIDTH=100>"+curCom.getDate()+"</td>");
			html.append("</tr>");
		html.append("</table>");
		//(本文)
		html.append("<table border=0 cellspacing=0 cellpadding=5 width=570 bgcolor=333333>");
			html.append("<tr>");
				html.append("<td FIXWIDTH=600 height=66>");
					html.append(curCom.getMessage());
				html.append("</td>");
			html.append("</tr>");
		html.append("</table>");

		//[422リストへ][161戻る]    [424編集][425削除][426返信][421新規作成]
		html.append("<img src=L2UI.squareblank width=1 height=5>"
				+ "<img src=L2UI.squaregray width=750 height=1>"
				+ "<img src=L2UI.squareblank width=1 height=5>");
		html.append("<table border=0 cellspacing=0 cellpadding=0 width=730><tr>");
			html.append("<td>");
			html.append("<button value=\"&$161;\" action=\"bypass _bbscustom;msgview;" + msgType.toString() + ";" + msgId + ";0;" + msgPage + ";" + comPage + "\""
					+ " width=65 height=20 back=L2UI_ct1.button_df fore=L2UI_ct1.button_df>");
			html.append("</td>");
			html.append("<td width=665 align=right><table border=0 cellspacing=0 cellpadding=0><tr>");
			html.append("</tr></table></td>");
		html.append("</tr></table>");

		html.append("</center>");
		appendBottomTag(html);
		html.append("</body>");
		html.append("</html>");

		separateAndSend(html.toString(),activeChar);
	}

	private boolean doMessageDelete(L2PcInstance activeChar, MsgType msgType, int msgId)
	{
//		activeChar.TRACE("__BASENAME__:__LINE__: doMessageDelete("+activeChar.getName()+","+msgType+","+msgId+")");
		CustomMsg delMsg = new CustomMsg().load(msgId);
		int delflag = 0;
			if (delMsg.isOwn(activeChar)) delflag = CustomMsg.DELETE_OWNER;
			else if (activeChar.isGM()) delflag = CustomMsg.DELETE_ADMIN;
			else { authError(activeChar); return false; }
		delMsg = null;
		if (! CustomMsg.delete(msgId, delflag))
		{
			errorDialog(activeChar, "DELETE ERROR!");
			return false;
		}
		return true;
	}

	private boolean doCommentDelete(L2PcInstance activeChar, MsgType msgType, int msgId, int comId)
	{
//		activeChar.TRACE("__BASENAME__:__LINE__: doCommentDelete("+activeChar.getName()+","+msgType+","+msgId+","+comId+")");
		CustomComment delCom = new CustomComment().load(msgId, comId);
		int delflag = 0;
			if (delCom.isOwn(activeChar)) delflag = CustomMsg.DELETE_OWNER;
			else if (activeChar.isGM()) delflag = CustomMsg.DELETE_ADMIN;
			else { authError(activeChar); return false; }
		delCom = null;
		if (! CustomComment.delete(msgId, comId, delflag))
		{
			errorDialog(activeChar, "DELETE ERROR!");
			return false;
		}
		return true;
	}

	private void appendTopTag(StringBuilder html, MsgType msgType)
	{
		html.append("<table border=0 cellspacing=10 cellpadding=0><tr>");
		html.append("<td fixwidth=400>").append(getHeaderTree(msgType)).append("</td>");
		html.append("<td fixwidth=400 align=right>").append(getSubMenu(msgType)).append("</td>");
		html.append("</tr></table>");
	}
	private void appendBottomTag(StringBuilder html/*, MsgType msgType*/)
	{
		html.append("<img src=L2UI.squareblank width=1 height=20>");
	}
	private String getHeaderTree(MsgType msgType)
	{
		/*
		 * メモ：<a action="bypass _bbshome"> &$377; </a> - タブメニューと食い違いが出るので使用中止。
		 * 　（FriendListManager,FavoriteManager,ClanBBSManager も）
		 */
		String html = null;
		switch (msgType)
		{
		case CLAN:
			//[377ホーム]>[809血盟コミュニティー]>[802血盟HOME]>[382血盟][387掲示板]
			html = "<a action=\"\"> &$377; </a>&nbsp;>&nbsp;" +
					"<a action=\"bypass _bbsclan_clanlist\"> &$809; </a>&nbsp;>&nbsp;" +
					"<a action=\"bypass _bbsclan_clanhome\"> &$802; </a>&nbsp;>&nbsp;" +
					"<a action=\"bypass _bbscustom;msglist;"+msgType.toString()+"\"> "+msgType.getCaption()+" </a>";
			break;
		default:
			html = "<a action=\"\"> &$377; </a>&nbsp;>&nbsp;" +
					"<a action=\"bypass _bbscustom;msglist;"+msgType.toString()+"\"> "+msgType.getCaption()+" </a>";
		}
		return html;
	}

	private String getSubMenu(MsgType msgType)
	{
		/*
		 * メモ：<td align=right> 指定しているので、なぜか左右逆に表示される。
		 */
		if (msgType.canComment)
			return
				  "|<a action=\"bypass _bbscustom;msglist;announce\">"+"お知らせ" +"</a>"
				+ "|<a action=\"bypass _bbscustom;msglist;recruit\">" +"血盟募集" +"</a>"
				+ "|<a action=\"bypass _bbscustom;msglist;trade\">"   +"トレード" +"</a>"
				+ "|<a action=\"bypass _bbscustom;msglist;event\">"   +"イベント" +"</a>"
				+ "|<a action=\"bypass _bbscustom;msglist;qa\">"      +"Q&A"      +"</a>"
				+ "|<a action=\"bypass _bbscustom;msglist;hello\">"   +"あいさつ" +"</a>"
				+ "|<a action=\"bypass _bbscustom;msglist;default\">" +"雑談"     +"</a>"
				+ "|";
		else
			return "";
	}

	private void errorDialog(L2PcInstance activeChar, String msg)
	{
		activeChar.sendPacket(new NpcHtmlMessage(0, "<html><title>ERROR</title><body><br><br><center><font color=FF0000>"+msg+"</font></center></body></html>"));
	}

	/**
	 * Shortcut to SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT
	 */
	private void authError(L2PcInstance activeChar)
	{
		activeChar.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
	}

	private boolean isShutout(L2PcInstance activeChar)
	{
		//if (activeChar.getPkKills() > 5) return true;
		//if (activeChar.getLevel() < 10) return true;
		return false;
	}
	private boolean isTuEEE(L2PcInstance activeChar)
	{
		//return activeChar.isGM();
		//return activeChar.getName().equals("ぜんまいじかけ");
		return false;
	}

	private boolean authReply(L2PcInstance activeChar, MsgType msgType)
	{
		return msgType.canReply;
	}
	
	private boolean authEdit(L2PcInstance activeChar, MsgType msgType, CustomMsg curMsg)
	{
		if (isShutout(activeChar)) return false;
		return msgType.canEdit && curMsg.isOwn(activeChar) || isTuEEE(activeChar);
	}
	
	private boolean authDelete(L2PcInstance activeChar, MsgType msgType, CustomMsg curMsg)
	{
		if (isShutout(activeChar)) return false;
		return curMsg.isOwn(activeChar) || activeChar.isGM() || isTuEEE(activeChar);
	}
	
	private boolean authNew(L2PcInstance activeChar, MsgType msgType)
	{
		if (isShutout(activeChar)) return false;
		return (! msgType.canPostAdminOnly) || msgType.isMail || activeChar.isGM() || isTuEEE(activeChar);
	}

//	BaseBBSManager に移動
//	/**
//	 * html に使えない文字をエスケープする。
//	 * （ほんとは BaseBBSManager に書いたほうがいいかも）
//	 * @param  text - plain text
//	 * @return - html text
//	 */
//	private String htmlescape(CharSequence text)
//	{
//		CharSequence html = text;
//		StringBuilder buf = new StringBuilder(text.length()*2);
//		for (int index = 0; index < text.length(); ++index) {
//			char ch = text.charAt(index);
//			switch (ch) {
//			case '&': html = buf.append("&amp;"); break;
//			case '<': html = buf.append("&lt;"); break;
//		//	case '>': html = buf.append("&gt;"); break;
//			case ' ': html = buf.append("&nbsp;"); break;
//			case '"': html = buf.append("&quot;"); break;
//			case '\'': html = buf.append("&apos;"); break;
//			case '\n': html = buf.append("<br1>"); break;
//			default: buf.append(ch); break;
//			}
//		}
//		return html.toString();
//	}

	/**
	 * 文字列を所定の文字幅に切り詰める。
	 * 漢字＝幅２、英数字半角カナ＝幅１として計算。
	 * 文字幅を超えるものは … を付け加える。
	 * 半角 : u0020-u007E, uFF61-uFFDC, uFFE8-uFFEE
	 */
	private CharSequence cut(CharSequence s, int max)
	{
		int length = s.length();
		if (length * 2 <= max) return s;

		int pos, index;
		for (pos = index = 0; index < length; ++index) {
			char c = s.charAt(index);
			int w = c < 0x007F || c > 0xFF60 ? 1 : 2;	//かなり手抜き
			if (pos + w > max) break;
			pos += w;
		}
		if (index >= length) return s;
		max -= 2;	// "…"
		for (pos = index = 0; index < length; ++index) {
			char c = s.charAt(index);
			int w = c < 0x007F || c > 0xFF60 ? 1 : 2;	//かなり手抜き
			if (pos + w > max) break;
			pos += w;
		}
		return s.subSequence(0, index) + "\u2026"; //"…";
	}

	@Override // BaseBBSManager
	protected void separateAndSend(String html, L2PcInstance acha)
	{
		if (html.length() < 12270)
			super.separateAndSend(html, acha);
		else
			super.separateAndSend("<html><body><br><br><center><font color=FF0000>PACKET TOO LARGE!</font></center></body></html>", acha);
	}

	/**
	 * ３番目の文字列の不具合？を修正。
	 * イマイチ動きがわからないが、とりあえず動いている。
	 */
	@Override // BaseBBSManager
	protected void send1002(L2PcInstance activeChar, String string1, String string2,String string3)
	{
//		activeChar.TRACE("__BASENAME__:__LINE__: send1002(activeChar,["+string1+"],["+string2+"],["+string3+"])");
		List<String> _arg = new FastList<String>();
		_arg.add("0");
		_arg.add("0");
		_arg.add("0");
		_arg.add("0");
		_arg.add("0");
		_arg.add("0");
		_arg.add(activeChar.getName());
		_arg.add(Integer.toString(activeChar.getObjectId()));
		_arg.add(activeChar.getAccountName());
		_arg.add("9");
		_arg.add(string3/*string2*/);
		_arg.add(string2);
		_arg.add(string1);
		_arg.add("0"/*string3*/);
		_arg.add("0"/*string3*/);
		_arg.add("0");
		_arg.add("0");
		activeChar.sendPacket(new ShowBoard(_arg));
	}

	/**
	 * ・クライアントで [Enter] を押すと改行コードが "\r\n" と入力される。
	 * 　しかし '\r' と '\n' の２文字分として扱われるので
	 * 　'\r' だけ消したり '\n' だけ消したりできてしまう。
	 *   → '\r' はすべて取り除く。
	 * 　→ '\n' が連続するときは１つに減らす。
	 * ・連続空白は１つに減らす。
	 * ・"&$数字;" や "&#数字;" の実体参照は禁止。
	 */
//	@Override // BaseBBSManager
	protected String quoteString(String input)
	{
		if (input == null || input.isEmpty()) return input;
		CharSequence result = input;
		StringBuilder buf = new StringBuilder(input.length());
		for (int index = 0; index < input.length(); ) {
			char ch = input.charAt(index), cl;
			switch (ch) {
			case '\r':
				result = buf; //\r は捨てる
				index += 1;
				break;
			case '\n':
			case ' ':
				result = buf.append(ch);
				do ++index; while (index < input.length() && ((cl = input.charAt(index)) == ch || cl == '\r'));
				break;
			case '&':
				if (index+1 < input.length() && ((cl = input.charAt(index+1)) == '$' || cl == '#')) {
					result = buf;
					ch = '?';
				}
				/* not 'break'. goto default:*/
			default:
				buf.append(ch);
				index += 1;
				break;
			}
		}
		return result.toString();
	}

//	private void DUMP(String data)
//	{
//		String dummy = data.replace('\r', '!').replace('\n', '!');
//		for (int dx=0; dx < data.length(); dx += 8) {
//			System.out.print("\t");
//			for (int cx=0; cx < 8; ++cx) {
//				if (dx+cx < data.length())
//					System.out.printf("%04X ", data.charAt(dx+cx) & 0xFFFF);
//				else
//					System.out.print("     ");
//			}
//			if (dx+8 <= dummy.length())
//				System.out.println(": " + dummy.substring(dx, dx+8));
//			else
//				System.out.println(": " + dummy.substring(dx));
//		}
//	}
}
// 日本呼び　　略称　英語 　CustomBBSManager
// 掲示板　　　板　　forum　MsgType
// スレッド　　スレ　topic　messagez
// レスポンス　レス　post 　commentz
