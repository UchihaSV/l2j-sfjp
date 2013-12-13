/*
 * Copyright (C) 2004-2013 L2J Server
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
package com.l2jserver.gameserver.communitybbs.Manager;

import java.util.ArrayList;

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.serverpackets.ShowBoard;

public abstract class BaseBBSManager
{
	public abstract void parsecmd(String command, L2PcInstance activeChar);
	
	public abstract void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar);
	
	protected void separateAndSend(String html, L2PcInstance acha)
	{
		if (html == null)
		{
			return;
		}
		if (html.length() < 4096)
		{
			acha.sendPacket(new ShowBoard(html, "101"));
			acha.sendPacket(new ShowBoard(null, "102"));
			acha.sendPacket(new ShowBoard(null, "103"));
			
		}
		else if (html.length() < 8192)
		{
			acha.sendPacket(new ShowBoard(html.substring(0, 4096), "101"));
			acha.sendPacket(new ShowBoard(html.substring(4096), "102"));
			acha.sendPacket(new ShowBoard(null, "103"));
			
		}
		else if (html.length() < 16384)
		{
			acha.sendPacket(new ShowBoard(html.substring(0, 4096), "101"));
			acha.sendPacket(new ShowBoard(html.substring(4096, 8192), "102"));
			acha.sendPacket(new ShowBoard(html.substring(8192), "103"));
		}
	}
	
	/**
	 * @param html
	 * @param acha
	 */
	protected void send1001(String html, L2PcInstance acha)
	{
		if (html.length() < 8192)
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
	protected void send1002(L2PcInstance activeChar, String string1, String string2, String string3)
	{
//		TRACE("__BASENAME__:__LINE__: send1002(activeChar,["+string1+"],["+string2+"],["+string3+"])");
		ArrayList<String> arg = new ArrayList<>(17);
		arg.add("0");
		arg.add("0");
		arg.add("0");
		arg.add("0");
		arg.add("0");
		arg.add("0");
		arg.add(activeChar.getName());
		arg.add(Integer.toString(activeChar.getObjectId()));
		arg.add(activeChar.getAccountName());
		arg.add("9");
		arg.add(string3 == null || string3.length() == 0 ? " " : string3);
		arg.add(string2 == null || string2.length() == 0 ? " " : string2);
		arg.add(string1 == null || string1.length() == 0 ? " " : string1);
		arg.add("0");
		arg.add("0");
		arg.add("0");
		arg.add("0");
		activeChar.sendPacket(new ShowBoard(arg));
	}
//	// [L2J_JP ADD - TSL]
//	protected String quoteString(String html)
//	{
//		html = html.replaceAll("<", "＜");
//		html = html.replaceAll(">", "＞");
//		html = html.replaceAll("\"", "”");
//		html = html.replaceAll("&", "＆");
//		html = html.replaceAll("～", "");
//		return html;
//	}
	
	/**
	 * ・クライアントで [Enter] を押すと改行コードが "\r\n" と入力される。
	 * 　しかし '\r' と '\n' の２文字分として扱われるので
	 * 　'\r' だけ消したり '\n' だけ消したりできてしまう。
	 *   → '\r' はすべて取り除く。
	 * 　→ '\n' が連続するときは１つに減らす。
	 * ・連続空白は１つに減らす。
	 * ・"&$数字;" や "&#数字;" の実体参照は禁止。
	 */
	public static String quoteString(String input)
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
				if (index + 1 < input.length() && "!#$%&*+-/=?@^`|~".indexOf(input.charAt(index + 1)) >= 0
				 && index + 2 < input.length() && "0123456789".indexOf(input.charAt(index + 2)) >= 0) {
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
	
	/**
	 * html に使えない文字をエスケープする。[JOJO]
	 * @param  text - plain text
	 * @return - html text
	 */
	public static String htmlescape(CharSequence text)
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
	
	/**
	 * 文字列を所定の文字幅に切り詰める。
	 * 漢字＝幅２、英数字半角カナ＝幅１として計算。
	 * 文字幅を超えるものは … を付け加える。
	 * 半角 : u0020-u007E, uFF61-uFFDC, uFFE8-uFFEE
	 */
	public static CharSequence cut(CharSequence s, int max)
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
}
