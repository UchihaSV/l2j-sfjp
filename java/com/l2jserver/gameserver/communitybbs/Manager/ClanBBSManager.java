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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

import com.l2jserver.Config;
import com.l2jserver.gameserver.datatables.ClanTable;
import com.l2jserver.gameserver.model.L2Clan;
import com.l2jserver.gameserver.model.L2ClanMember;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.ShowBoard;
import com.l2jserver.util.StringUtil;
import com.l2jserver.util.Util;

/**
 * @editor  TSL
 */
public class ClanBBSManager extends BaseBBSManager
{
	public static ClanBBSManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	/**
	 * @param command
	 * @param activeChar
	 */
	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		if (command.equals("_bbsclan"))
		{
			if ((activeChar.getClan() == null) || (activeChar.getClan().getLevel() < 2))
			{
				clanlist(activeChar, 1);
			}
			else
			{
				clanhome(activeChar);
			}
		}
		else if (command.startsWith("_bbsclan_clanlist"))
		{
			if (command.equals("_bbsclan_clanlist"))
			{
				clanlist(activeChar, 1);
			}
			else if (command.startsWith("_bbsclan_clanlist;"))
			{
				StringTokenizer st = new StringTokenizer(command, ";");
				st.nextToken();
				int index = Integer.parseInt(st.nextToken());
				clanlist(activeChar, index);
			}
		}
		else if (command.startsWith("_bbsclan_clanhome"))
		{
			if (command.equals("_bbsclan_clanhome"))
			{
				clanhome(activeChar);
			}
			else if (command.startsWith("_bbsclan_clanhome;"))
			{
				StringTokenizer st = new StringTokenizer(command, ";");
				st.nextToken();
				int index = Integer.parseInt(st.nextToken());
				clanhome(activeChar, index);
			}
		}
		
		else if (command.startsWith("_bbsclan_clanadmin;"))
		{
			StringTokenizer st = new StringTokenizer(command, ";");
			st.nextToken();
			String subcmd = st.nextToken();
			String msgType = "";
			int clanId = Integer.parseInt(st.nextToken());
			if (activeChar.getClan() == null || activeChar.getClan().getClanId() != clanId
			        || activeChar.getClan().getLeaderId() != activeChar.getObjectId())
			{
				activeChar.sendPacket(SystemMessageId.YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT);
				parsecmd("_bbsclan_clanlist", activeChar);
				return;
			}
			
			if (subcmd.startsWith("new"))
			{
				if (subcmd.equals("newcomm"))
					msgType = "comment";
				else if (subcmd.equals("newanno"))
					msgType = "announce";
				showMsgEdit(activeChar, msgType, clanId);
			}
			else if (subcmd.startsWith("del"))
			{
				if (subcmd.equals("delcomm"))
					msgType = "comment";
				else if (subcmd.equals("delanno"))
					msgType = "announce";
				File file = new File(Config.DATAPACK_ROOT + "/data/clans/" + msgType + "_" + clanId
						+ ".txt");
				file.delete();
				clanhome(activeChar);
			}
		}
		
		else if (command.startsWith("_bbsclan_clannotice_edit;"))
		{
			clanNotice(activeChar, activeChar.getClanId());
		}
		else if (command.startsWith("_bbsclan_clannotice_enable"))
		{
			if (activeChar.getClan() != null)
			{
				activeChar.getClan().setNoticeEnabled(true);
			}
			clanNotice(activeChar, activeChar.getClanId());
		}
		else if (command.startsWith("_bbsclan_clannotice_disable"))
		{
			if (activeChar.getClan() != null)
			{
				activeChar.getClan().setNoticeEnabled(false);
			}
			clanNotice(activeChar, activeChar.getClanId());
		}
		else
		{
			separateAndSend("<html><body><br><br><center>Command : " + command + " needs core development</center><br><br></body></html>", activeChar);
		}
	}
	
	private void clanNotice(L2PcInstance activeChar, int clanId)
	{
		final L2Clan cl = ClanTable.getInstance().getClan(clanId);
		if (cl != null)
		{
			if (cl.getLevel() < 2)
			{
				activeChar.sendPacket(SystemMessageId.NO_CB_IN_MY_CLAN);
				parsecmd("_bbsclan_clanlist", activeChar);
			}
			else
			{
				final StringBuilder html = StringUtil.startAppend(2000, "<html><body><br><br><table border=0 width=610><tr><td width=10></td><td width=600 align=left><a action=\"bypass _bbshome\">HOME</a> &gt; <a action=\"bypass _bbsclan_clanlist\"> CLAN COMMUNITY </a>  &gt; <a action=\"bypass _bbsclan_clanhome;", String.valueOf(clanId), "\"> &amp;$802; </a></td></tr></table>");
				if (activeChar.isClanLeader())
				{
					StringUtil.append(html, "<br><br><center><table width=610 border=0 cellspacing=0 cellpadding=0><tr><td fixwidth=610><font color=\"AAAAAA\">The Clan Notice function allows the clan leader to send messages through a pop-up window to clan members at login.</font> </td></tr><tr><td height=20></td></tr>");
					
					if (activeChar.getClan().isNoticeEnabled())
					{
						StringUtil.append(html, "<tr><td fixwidth=610> Clan Notice Function:&nbsp;&nbsp;&nbsp;on&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<a action=\"bypass _bbsclan_clannotice_disable\">off</a>");
					}
					else
					{
						StringUtil.append(html, "<tr><td fixwidth=610> Clan Notice Function:&nbsp;&nbsp;&nbsp;<a action=\"bypass _bbsclan_clannotice_enable\">on</a>&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;off");
					}
					
					StringUtil.append(html, "</td></tr></table><img src=\"L2UI.Squaregray\" width=\"610\" height=\"1\"><br> <br><table width=610 border=0 cellspacing=2 cellpadding=0><tr><td>Edit Notice: </td></tr><tr><td height=5></td></tr><tr><td><MultiEdit var =\"Content\" width=610 height=100></td></tr></table><br><table width=610 border=0 cellspacing=0 cellpadding=0><tr><td height=5></td></tr><tr><td align=center FIXWIDTH=65><button value=\"&$140;\" action=\"Write Notice Set _ Content Content Content\" back=\"l2ui_ch3.smallbutton2_down\" width=65 height=20 fore=\"l2ui_ch3.smallbutton2\" ></td><td align=center FIXWIDTH=45></td><td align=center FIXWIDTH=500></td></tr></table></center></body></html>");
					send1001(html.toString(), activeChar);
					send1002(activeChar, activeChar.getClan().getNotice(), " ", "0");
				}
				else
				{
					StringUtil.append(html, "<img src=\"L2UI.squareblank\" width=\"1\" height=\"10\"><center><table border=0 cellspacing=0 cellpadding=0><tr><td>You are not your clan's leader, and therefore cannot change the clan notice</td></tr></table>");
					if (activeChar.getClan().isNoticeEnabled())
					{
						StringUtil.append(html, "<table border=0 cellspacing=0 cellpadding=0><tr><td>The current clan notice:</td></tr><tr><td fixwidth=5></td><td FIXWIDTH=600 align=left>", activeChar.getClan().getNotice(), "</td><td fixqqwidth=5></td></tr></table>");
					}
					StringUtil.append(html, "</center></body></html>");
					separateAndSend(html.toString(), activeChar);
				}
			}
		}
	}
	
	/**
	 * @param activeChar
	 * @param index
	 */
	private void clanlist(L2PcInstance activeChar, int index)
	{
		if (index < 1)
		{
			index = 1;
		}
		
		//header
		final StringBuilder html = StringUtil.startAppend(2000, "<html><body><br><br><center>"
				+ "<table border=0 cellspacing=0 cellpadding=0><tr><td FIXWIDTH=15>&nbsp;</td>"
				+ "<td width=610 height=30 align=left><a action=\"bypass _bbshome\"> &$377; </a>&nbsp;>&nbsp;<a action=\"bypass _bbsclan_clanlist\"> &$809; </a>"	// CLAN COMMUNITY
		        + "</td></tr></table><table border=0 cellspacing=0 cellpadding=0 width=610 bgcolor=434343>"
				+ "<tr><td height=10></td></tr><tr><td fixWIDTH=5></td><td fixWIDTH=600>"
				+ "<a action=\"bypass _bbsclan_clanhome;", String.valueOf((activeChar.getClan() != null) ? activeChar.getClan().getClanId() : 0), "\">[&$802;]</a>&nbsp;&nbsp;"	// [GO TO MY CLAN]
		        + "</td>"
				+ "<td fixWIDTH=5></td>"
				+ "</tr>"
				+ "<tr><td height=10></td></tr>"
				+ "</table>"
		//body
				+ "<br>"
				+ "<table border=0 cellspacing=0 cellpadding=2 bgcolor=5A5A5A width=610>"
				+ "<tr>"
				+ "<td FIXWIDTH=5></td>"
				+ "<td FIXWIDTH=200 align=center>&$580;</td>"	// CLAN NAME
				+ "<td FIXWIDTH=200 align=center>&$342;</td>"	// CLAN LEADER
				+ "<td FIXWIDTH=100 align=center>&$392;</td>"	// CLAN LEVEL
				+ "<td FIXWIDTH=100 align=center>&$1036;</td>"	// CLAN MEMBERS
				+ "<td FIXWIDTH=5></td></tr></table><img src=\"L2UI.Squareblank\" width=\"1\" height=\"5\">");
		
		L2Clan[] clanList = ClanTable.getInstance().getClans();
		for (int i = (index - 1) * 12, end = Math.min(i + 12, clanList.length); i < end; i++)
		{
			L2Clan cl = clanList[i];
			if (cl == null)
				continue;
			html.append("<table border=0 cellspacing=0 cellpadding=5 width=610><tr> "
					+ "<td FIXWIDTH=5></td>"
					+ "<td FIXWIDTH=200 align=center><a action=\"bypass _bbsclan_clanhome;").append(cl.getClanId()).append("\">").append(cl.getName()).append("</a></td>"
					+ "<td FIXWIDTH=200 align=center>").append(cl.getLeaderName()).append("</td>"
					+ "<td FIXWIDTH=100 align=center>").append(cl.getLevel()).append("</td>"
					+ "<td FIXWIDTH=100 align=center>").append(cl.getMembersCount()).append("</td>"
					+ "<td FIXWIDTH=5></td></tr></table>"
					+ "<img src=\"L2UI.SquareGray\" width=\"610\" height=\"1\">");
		}
		html.append("<br><table cellpadding=0 cellspacing=2 border=0><tr>");
		
		int nbp;
		nbp = (clanList.length - 1) / 12 + 1;
		int startIdx = (index - 1) / 10 * 10 + 1;
		if (startIdx > 1)
		{
			html.append("<td><button action=\"bypass _bbsclan_clanlist;").append(startIdx - 1).append("\" back=\"l2ui_ch3.prev1_down\" fore=\"l2ui_ch3.prev1\" width=16 height=16 ></td>");
		}
		else
		{
			html.append("<td><button action=\"\" back=\"l2ui_ch3.prev1_down\" fore=\"l2ui_ch3.prev1\" width=16 height=16 ></td>");
		}
		if (index > 1)
		{
			html.append("<td><button action=\"bypass _bbsclan_clanlist;").append(index - 1).append("\" back=\"l2ui_ch3.prev1_down\" fore=\"l2ui_ch3.prev1\" width=16 height=16 ></td>");
		}
		else
		{
			html.append("<td><button action=\"\" back=\"l2ui_ch3.prev1_down\" fore=\"l2ui_ch3.prev1\" width=16 height=16 ></td>");
		}
		for (int i = startIdx; i <= nbp && i < startIdx + 10; i++)
		{
			if (i == index)
			{
				html.append("<td> ").append(i).append(" </td>");
			}
			else
			{
				html.append("<td><a action=\"bypass _bbsclan_clanlist;").append(i).append("\"> ").append(i).append(" </a></td>");
			}
			
		}
		if (index + 1 <= nbp)
		{
			html.append("<td><button action=\"bypass _bbsclan_clanlist;").append(index + 1).append("\" back=\"l2ui_ch3.next1_down\" fore=\"l2ui_ch3.next1\" width=16 height=16 ></td>");
		}
		else
		{
			html.append("<td><button action=\"\" back=\"l2ui_ch3.next1_down\" fore=\"l2ui_ch3.next1\" width=16 height=16 ></td>");
		}
		if (startIdx + 10 <= nbp)
		{
			html.append("<td><button action=\"bypass _bbsclan_clanlist;").append(startIdx + 10).append("\" back=\"l2ui_ch3.next1_down\" fore=\"l2ui_ch3.next1\" width=16 height=16 ></td>");
		}
		else
		{
			html.append("<td><button action=\"\" back=\"l2ui_ch3.next1_down\" fore=\"l2ui_ch3.next1\" width=16 height=16 ></td>");
		}
		html.append("</tr></table></center></body></html>");
	//	html.append("</tr></table><table border=0 cellspacing=0 cellpadding=0><tr><td width=610><img src=\"sek.cbui141\" width=\"610\" height=\"1\"></td></tr></table><table border=0><tr><td><combobox width=65 var=keyword list=\"Name;Ruler\"></td><td><edit var = \"Search\" width=130 height=11 length=\"16\"></td>" +
	//	// TODO: search (Write in BBS)
	//	"<td><button value=\"&$420;\" action=\"Write 5 -1 0 Search keyword keyword\" back=\"l2ui_ch3.smallbutton2_down\" width=65 height=20 fore=\"l2ui_ch3.smallbutton2\"> </td> </tr></table><br><br></center></body></html>");
		separateAndSend(html.toString(), activeChar);
	}
	
	/**
	 * @param activeChar
	 */
	private void clanhome(L2PcInstance activeChar)
	{
		clanhome(activeChar, activeChar.getClan().getClanId());
	}
	
	private static class SingletonHolder
	{
		protected static final ClanBBSManager _instance = new ClanBBSManager();
	}
	
	/**
	 * @param activeChar
	 * @param clanId
	 */
	private void clanhome(L2PcInstance activeChar, int clanId)
	{
		L2Clan cl = ClanTable.getInstance().getClan(clanId);
		if (cl != null)
		{
			if (activeChar.getClan() != null && activeChar.getClan().getClanId() == clanId
			        && cl.getLevel() < 2)
			{
				activeChar.sendPacket(SystemMessageId.NO_CB_IN_MY_CLAN);
				parsecmd("_bbsclan_clanlist", activeChar);
			}
			else
			{
				final StringBuilder html = StringUtil.startAppend(2000, "<html><body><center><br><br>"
						+ "<table border=0 cellspacing=0 cellpadding=0>"
						+ "<tr><td FIXWIDTH=15>&nbsp;</td>"
						+ "<td width=610 height=30 align=left>"
						+ "<a action=\"bypass _bbshome\"> &$377; </a> &gt; <a action=\"bypass _bbsclan_clanlist\"> &$809; </a>  &gt; <a action=\"bypass _bbsclan_clanhome;" , String.valueOf(clanId), "\"> &amp;$802; </a>"	// HOME, CLAN, COMMUNITY
						+ "</td></tr></table>"
						+ "<table border=0 cellspacing=0 cellpadding=0 width=610 bgcolor=434343>"
						+ "<tr><td height=10></td></tr>"
						+ "<tr>"
						+ "<td fixWIDTH=5></td>"
						+ "<td fixwidth=600>"
						+ "<a action=\"bypass _bbsclan_clanadmin;newcomm;", String.valueOf(clanId), "\">[&$423;&$424;]</a>&nbsp;"	// コメント編集
						+ "<a action=\"bypass _bbsclan_clanadmin;newanno;", String.valueOf(clanId), "\">[&$328;&$424;]</a>&nbsp;"	// お知らせ編集
						+ "<a action=\"bypass _bbscustom;msglist;clan\">[&$382;&$387;]</a>&nbsp;"									// 血盟掲示板
//						+ "<a action=\"bypass _bbscustom;msgnew;clan\">[&$905;&$424;]</a>&nbsp;"									//メール編集 //-[JOJO]
					//	+ "<a action=\"bypass _bbsclan_clanhome;", String.valueOf(clanId), ";announce\">[CLAN ANNOUNCEMENT]</a> <a action=\"bypass _bbsclan_clanhome;", String.valueOf(clanId), ";cbb\">[CLAN BULLETIN BOARD]</a>"
					//	+ "<a action=\"bypass _bbsclan_clanhome;", String.valueOf(clanId), ";cmail\">[CLAN MAIL]</a>&nbsp;&nbsp;"
					//	+ "<a action=\"bypass _bbsclan_clannotice_edit;", String.valueOf(clanId), ";cnotice\">[CLAN NOTICE]</a>&nbsp;&nbsp;"
						+ "</td>"
						+ "<td fixWIDTH=5></td>"
						+ "</tr>"
						+ "<tr><td height=10></td></tr>"
						+ "</table>"
						+ "<table border=0 cellspacing=0 cellpadding=0 width=610>"
						+ "<tr><td height=10></td></tr>"
						+ "<tr><td fixWIDTH=5></td>"
						+ "<td fixwidth=290 valign=top>" , getMsgData("comment", clanId), "</td>"
						+ "<td fixWIDTH=5></td>"
						+ "<td fixWIDTH=5 align=center valign=top><img src=\"l2ui.squaregray\" width=2  height=128></td>"
						+ "<td fixWIDTH=5></td>"
						+ "<td fixwidth=295>"
						+ "<table border=0 cellspacing=0 cellpadding=0 width=295>"
						+ "<tr>"
						+ "<td fixWIDTH=100 align=left>&$580;</td><td fixWIDTH=195 align=left>", cl.getName(), "</td></tr>"	// CLAN NAME
						+ "<tr><td height=7></td></tr><tr><td fixWIDTH=100 align=left>&$392;</td>"	// CLAN LEVEL
						+ "<td fixWIDTH=195 align=left height=16>", String.valueOf(cl.getLevel()), "</td></tr>"
						+ "<tr><td height=7></td></tr><tr><td fixWIDTH=100 align=left>&$1036;</td>"	// CLAN MEMBERS
						+ "<td fixWIDTH=195 align=left height=16>", String.valueOf(cl.getMembersCount()), "</td></tr>"
						+ "<tr><td height=7></td></tr><tr><td fixWIDTH=100 align=left>&$342;</td>"	// CLAN LEADER
						+ "<td fixWIDTH=195 align=left height=16>", cl.getLeaderName(), "</td></tr><tr><td height=7></td></tr>"
						+
						//ADMINISTRATOR ??
						/*html.append("<tr>");
						 html.append("<td fixWIDTH=100 align=left>ADMINISTRATOR</td>");
						 html.append("<td fixWIDTH=195 align=left height=16>"+cl.getLeaderName()+"</td>");
						 html.append("</tr>");*/
						"<tr><td height=7></td></tr><tr><td fixWIDTH=100 align=left>&$571;</td>"	// ALLIANCE
						+ "<td fixWIDTH=195 align=left height=16>", (cl.getAllyName() != null) ? cl.getAllyName() : "", "</td></tr>"
						+ "</table></td><td fixWIDTH=5></td>"
						+ "</tr>"
						+ "<tr><td height=10></td></tr>"
						+ "</table>"
						+
						//TODO: the BB for clan :)
						//html.append("<table border=0 cellspacing=0 cellpadding=0 width=610  bgcolor=333333>");
						"<img src=\"L2UI.squareblank\" width=\"1\" height=\"5\">"
						+ "<img src=\"L2UI.squaregray\" width=\"610\" height=\"1\">"
				        // [L2J_JP] //////////////////////////////
				        + "<img src=\"L2UI.squareblank\" width=\"1\" height=\"5\">"
						+ "<BR> &$1322;"
						+ "<TABLE border=0 cellspacing=0 cellpadding=0 width=610>"
						+ "<TR>"
						+ "<TD fixWIDTH=5></td><TD fixWIDTH=600>");
						for (L2ClanMember member : cl.getMembers())
						{
							html.append("<A action=\"bypass _bbscustom;msgnew;private;").append(member.getName()).append("\">").append(member.getName()).append("</A> (").append((L2World.getInstance().getPlayer(member.getName()) != null) ? "&$1006;" : "&$1007;").append(") &nbsp;");
						}
						html.append("</TD><TD fixWIDTH=5></td></TR></TABLE>"
						// ////////////////////////////////////////
						+ "</center><br> <br></body>"
						+ "</html>");
				separateAndSend(html.toString(), activeChar);
			}
		}
	}
	
	/**
	 * @param activeChar
	 * @param msgType
	 * @param clanId
	 */
	private void showMsgEdit(L2PcInstance activeChar, String msgType, int clanId)
	{
		StringBuilder html = new StringBuilder(2000).append("<html><body><br><br>"
				+ "<table border=0 width=610><tr><td width=10></td><td width=600 align=left>").append(getHeaderTree(msgType, clanId)).append("</td></tr>"
				+ "</table>"
				+ "<img src=\"L2UI.squareblank\" width=\"1\" height=\"10\">"
				+ "<center>"
				+ "<table border=0 cellspacing=0 cellpadding=0>"
				+ "<tr><td width=610><img src=\"sek.cbui355\" width=\"610\" height=\"1\"></td></tr>"
				+ "</table>"
				+ "<table fixwidth=610 border=0 cellspacing=0 cellpadding=0>"
				+ "<tr><td><img src=\"l2ui.mini_logo\" width=5 height=10></td></tr>"
				+ "<tr>"
				+ "<td><img src=\"l2ui.mini_logo\" width=5 height=1></td>"
				+ "<td align=center FIXWIDTH=60 height=29 valign=top>&$427;</td>"
				+ "<td align=center FIXWIDTH=540><MultiEdit var=\"Content\" width=540 height=300></td>"
				+ "<td><img src=\"l2ui.mini_logo\" width=5 height=1></td>"
				+ "</tr>"
				+ "<tr><td><img src=\"l2ui.mini_logo\" width=5 height=10></td></tr>"
				+ "</table>"
				+ "<img src=\"L2UI.squareblank\" width=\"1\" height=\"5\">"
				+ "<img src=\"L2UI.squaregray\" width=\"610\" height=\"1\">"
				+ "<img src=\"L2UI.squareblank\" width=\"1\" height=\"5\">"
				+ "<table fixwidth=610 border=0 cellspacing=0 cellpadding=0>"
				+ "<tr><td><img src=\"l2ui.mini_logo\" width=5 height=10></td></tr>"
				+ "<tr>"
				+ "<td><img src=\"l2ui.mini_logo\" width=5 height=1></td>"
				+ "<td align=center FIXWIDTH=60 height=29>&nbsp;</td>"
				+ "<td align=center FIXWIDTH=70><button value=\"&$424;\" action=\"Write Clan ").append(msgType).append(' ').append(clanId).append(" Content Content Content\" back=\"L2UI_CT1.BUTTON_DF_DOWN\" width=65 height=20 fore=\"L2UI_CT1.BUTTON_DF\"></td>"
				+ "<td align=center FIXWIDTH=70><button value=\"&$425;\" action=\"bypass _bbsclan_clanadmin;").append(msgType.equals("comment") ? "delcomm" : "delanno").append(';').append(clanId).append("\" back=\"L2UI_CT1.BUTTON_DF_DOWN\" width=65 height=20 fore=\"L2UI_CT1.BUTTON_DF\" ></td>&nbsp;"
				+ "<td align=center FIXWIDTH=70><button value=\"&$141;\" action=\"bypass _bbsclan_clanhome\" back=\"L2UI_CT1.BUTTON_DF_DOWN\" width=65 height=20 fore=\"L2UI_CT1.BUTTON_DF\"></td>"
				+ "<td align=center FIXWIDTH=330>&nbsp;</td>"
				+ "<td><img src=\"l2ui.mini_logo\" width=5 height=1></td>"
				+ "</tr></table>"
				+ "</center></body></html>");
		separateAndSend(html.toString(), activeChar);
	}
	
	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
		if (activeChar == null)
			return;
		if (ar1.equals("Set"))
		{
			activeChar.getClan().setNotice(ar4);
			parsecmd("_bbsclan_clanhome;" + activeChar.getClan().getClanId(), activeChar);
		}
		if (ar1.equals("comment") || ar1.equals("announce"))
		{
			setMsgData(ar1, Integer.parseInt(ar2), ar3);
			parsecmd("_bbsclan_clanhome", activeChar);
		}
		else
		{
			ShowBoard sb = new ShowBoard("<html><body><br><br><center>the command: " + ar1 + " is not implemented yet</center><br><br></body></html>", "101");
			activeChar.sendPacket(sb);
			activeChar.sendPacket(new ShowBoard(null, "102"));
			activeChar.sendPacket(new ShowBoard(null, "103"));
		}
	}
	/**
	 * @param msgType
	 * @param clanId
	 */
	public String getMsgData(String msgType, int clanId)
	{
		File file = new File(Config.DATAPACK_ROOT + "/data/clans/" + msgType + "_" + clanId + ".txt");
		BufferedReader lnr = null; // [JOJO]
		StringBuilder MsgData = new StringBuilder();
		try
		{
			String line = null;
			lnr = Util.utf8BufferedReader(file); // [JOJO] UTF-8
			while ((line = lnr.readLine()) != null)
			{
				MsgData.append(line);
			}
		}
		catch (FileNotFoundException e)
		{
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return MsgData.toString();
	}
	
	/**
	 * @param msgType
	 * @param clanId
	 * @param MsgData
	 */
	private void setMsgData(String msgType, int clanId, String MsgData)
	{
		File file = new File(Config.DATAPACK_ROOT + "/data/clans/" + msgType + "_" + clanId + ".txt");
		try
		{
			file.createNewFile();
			BufferedWriter save = Util.utf8BufferedWriter(file); // [JOJO] UTF-8
			MsgData = MsgData.replace("\r\n", "<br1>\r\n");
			save.write(MsgData);
			save.close();
		}
		catch (FileNotFoundException e)
		{
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
	}
	
	/**
	 * @param msgType
	 * @param clanId
	 */
	private String getHeaderTree(String msgType, int clanId)
	{
		String html = "";
		if (msgType.equals("comment"))
		{
			html = "<a action=\"bypass _bbshome\"> &$377; </a>&nbsp;>&nbsp;"
					+ "<a action=\"bypass _bbsclan_clanlist\"> &$809; </a>&nbsp;>&nbsp;"
					+ "<a action=\"bypass _bbsclan_clanhome;" + clanId
					+ "\"> &$802; </a>&nbsp;>&nbsp;"
					+ "<a action=\"bypass _bbsclan_clanadmin;newcomm;" + clanId
					+ "\"> &$423;&$424;";
		}
		else if (msgType.equals("announce"))
		{
			html = "<a action=\"bypass _bbshome\"> &$377; </a>&nbsp;>&nbsp;"
					+ "<a action=\"bypass _bbsclan_clanlist\"> &$809; </a>&nbsp;>&nbsp;"
					+ "<a action=\"bypass _bbsclan_clanhome;" + clanId
					+ "\"> &$802; </a>&nbsp;>&nbsp;"
					+ "<a action=\"bypass _bbsclan_clanadmin;newanno;" + clanId
					+ "\"> &$328;&$424;";
		}
		return html;
	}
}