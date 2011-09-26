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

import java.util.List;

import javolution.util.FastList;

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.serverpackets.ShowBoard;

public abstract class BaseBBSManager
{
	public abstract void parsecmd(String command, L2PcInstance activeChar);
	
	public abstract void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar);
	
	protected void separateAndSend(String html, L2PcInstance acha)
	{
		if (html == null)
			return;
		if (html.length() < 4090)
		{
			acha.sendPacket(new ShowBoard(html, "101"));
			acha.sendPacket(new ShowBoard(null, "102"));
			acha.sendPacket(new ShowBoard(null, "103"));
			
		}
		else if (html.length() < 8180)
		{
			acha.sendPacket(new ShowBoard(html.substring(0, 4090), "101"));
			acha.sendPacket(new ShowBoard(html.substring(4090, html.length()), "102"));
			acha.sendPacket(new ShowBoard(null, "103"));
			
		}
		else if (html.length() < 12270)
		{
			acha.sendPacket(new ShowBoard(html.substring(0, 4090), "101"));
			acha.sendPacket(new ShowBoard(html.substring(4090, 8180), "102"));
			acha.sendPacket(new ShowBoard(html.substring(8180, html.length()), "103"));
			
		}
	}
	
	/**
	 * @param html
	 * @param acha 
	 */
	protected void send1001(String html, L2PcInstance acha)
	{
		if (html.length() < 8180)
		{
			acha.sendPacket(new ShowBoard(html, "1001"));
		}
	}
	
	/**
	 * @param acha 
	 */
	protected void send1002(L2PcInstance acha)
	{
		send1002(acha, " ", " ", "0");
	}
	
	/**
	 * @param activeChar
	 * @param string
	 * @param string2
	 * @param string3 
	 */
	protected void send1002(L2PcInstance activeChar, String string, String string2, String string3)
	{
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
		_arg.add(string2);
		_arg.add(string2);
		_arg.add(string);
		_arg.add(string3);
		_arg.add(string3);
		_arg.add("0");
		_arg.add("0");
		activeChar.sendPacket(new ShowBoard(_arg));
	}
//	// [L2J_JP ADD - TSL]
//	protected String quoteString(String html)
//	{
//		html = html.replaceAll("<", "��");
//		html = html.replaceAll(">", "��");
//		html = html.replaceAll("\"", "�h");
//		html = html.replaceAll("&", "��");
//		html = html.replaceAll("�`", "");
//		return html;
//	}

	/**
	 * html �Ɏg���Ȃ��������G�X�P�[�v����B[JOJO]
	 * @param  text - plain text
	 * @return - html text
	 */
	protected String htmlescape(CharSequence text)
	{
		CharSequence html = text;
		StringBuilder buf = new StringBuilder(text.length()*2);
		for (int index = 0; index < text.length(); ++index) {
			char ch = text.charAt(index);
			switch (ch) {
			case '&': html = buf.append("&amp;"); break;
			case '<': html = buf.append("&lt;"); break;
		//	case '>': html = buf.append("&gt;"); break;
			case ' ': html = buf.append("&nbsp;"); break;
			case '"': html = buf.append("&quot;"); break;
			case '\'': html = buf.append("&apos;"); break;
			case '\n': html = buf.append("<br1>"); break;
			case '\r': break;
			default: buf.append(ch); break;
			}
		}
		return html.toString();
	}
}
