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
package com.l2jserver.gameserver.network.serverpackets;

import java.util.logging.Level;

import com.l2jserver.Config;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * <pre>
 * the HTML parser in the client knowns these standard and non-standard tags and attributes
 * VOLUMN
 * UNKNOWN
 * UL
 * U
 * TT
 * TR
 * TITLE
 * TEXTCODE
 * TEXTAREA
 * TD
 * TABLE
 * SUP
 * SUB
 * STRIKE
 * SPIN
 * SELECT
 * RIGHT
 * PRE
 * P
 * OPTION
 * OL
 * MULTIEDIT
 * LI
 * LEFT
 * INPUT
 * IMG
 * I
 * HTML
 * H7
 * H6
 * H5
 * H4
 * H3
 * H2
 * H1
 * FONT
 * EXTEND
 * EDIT
 * COMMENT
 * COMBOBOX
 * CENTER
 * BUTTON
 * BR
 * BR1
 * BODY
 * BAR
 * ADDRESS
 * A
 * SEL
 * LIST
 * VAR
 * FORE
 * READONL
 * ROWS
 * VALIGN
 * FIXWIDTH
 * BORDERCOLORLI
 * BORDERCOLORDA
 * BORDERCOLOR
 * BORDER
 * BGCOLOR
 * BACKGROUND
 * ALIGN
 * VALU
 * READONLY
 * MULTIPLE
 * SELECTED
 * TYP
 * TYPE
 * MAXLENGTH
 * CHECKED
 * SRC
 * Y
 * X
 * QUERYDELAY
 * NOSCROLLBAR
 * IMGSRC
 * B
 * FG
 * SIZE
 * FACE
 * COLOR
 * DEFFON
 * DEFFIXEDFONT
 * WIDTH
 * VALUE
 * TOOLTIP
 * NAME
 * MIN
 * MAX
 * HEIGHT
 * DISABLED
 * ALIGN
 * MSG
 * LINK
 * HREF
 * ACTION
 * </pre>
 */
public final class NpcHtmlMessage extends L2GameServerPacket
{
	private final int _npcObjId;
	private String _html;
	private int _itemId = 0;
	private boolean _validate = true;
	
	/**
	 * @param npcObjId
	 * @param itemId
	 */
	public NpcHtmlMessage(int npcObjId, int itemId)
	{
		_npcObjId = npcObjId;
		_itemId = itemId;
	}
	
	/**
	 * @param npcObjId
	 * @param text
	 */
	public NpcHtmlMessage(int npcObjId, String text)
	{
		_npcObjId = npcObjId;
		setHtml(text);
	}
	
	public NpcHtmlMessage(int npcObjId)
	{
		_npcObjId = npcObjId;
	}
	
	/**
	 * disable building bypass validation cache for this packet
	 */
	public void disableValidation()
	{
		_validate = false;
	}
	
	@Override
	public void runImpl()
	{
		if (Config.BYPASS_VALIDATION && _validate)
			buildBypassCache(getClient().getActiveChar());
	}
	
	public void setHtml(String text)
	{
		if (text.length() > 17200)
		{
			_log.log(Level.WARNING, "Html is too long! this will crash the client!", new Throwable());
			_html = text.substring(0, 17200);
		}
		if (!text.contains("<html>"))
			text = "<html><body>" + text + "</body></html>";
		
		_html = text;
	}
	
	public boolean setFile(L2PcInstance pc, String path)	//[JOJO]
	{
		return setFile(pc.getHtmlPrefix(), path);
	}
	
	public boolean setFile(String prefix, String path)
	{
		String content = HtmCache.getInstance().getHtm(prefix, path);
		
		if (content == null)
		{
			setHtml("<html><body>My Text is missing:<br>" + path + "</body></html>");
			_log.warning("missing html page " + path);
			return false;
		}
		
		setHtml(content);
		return true;
	}
	
	private char[] _ca = null; private int _count;				//[JOJO]
	public int replace(CharSequence token, CharSequence value)	//[JOJO]
	{
		int result = 0;
		if (_ca == null) {
			_count = _html.length();
			_ca = new char[_count * 2];
			_html.getChars(0, _count, _ca, 0);
		}
		final char first = token.charAt(0);
		final int token_length = token.length();
		final int value_length = value.length();
		for (int start = 0, end; ; start += value_length) {
			indexOf: {
				int max = _count - token_length;
				unko:
				for (; start < max; start++) {
					if (_ca[start] == first) {
						end = start + 1;
						int i = 1;
						while (i < token_length)
							if (_ca[end++] != token.charAt(i++))
								continue unko;
						/*assert(end == start + token_length);*/
						break indexOf;
					}
				}
				return result;
			}
			int newLen = _count - token_length + value_length;
			if (newLen > _ca.length) {
				char[] copy = new char[newLen * 2];
				System.arraycopy(_ca, 0, copy, 0, _count);
				_ca = copy;
			}
			int d = 0 - token_length + value_length;
			if (d < 0)
				{ for (int i = end; i <= _count-1; i++) _ca[i+d] = _ca[i]; }
			else if (d > 0)
				{ for (int i = _count-1; i >= end; i--) _ca[i+d] = _ca[i]; }
			_count = newLen;
		//	value.getChars(0, value_length, _ca, start);
			for (int i = 0; i < value_length; i++) //CharSequence value
				_ca[start+i] = value.charAt(i);
			++result;
		}
	}
	public int replace(CharSequence token, boolean value)
	{
		return replace(token, Boolean.toString(value));
	}
	public int replace(CharSequence token, int value)
	{
		return replace(token, Integer.toString(value));
	}
	public int replace(CharSequence token, long value)
	{
		return replace(token, Long.toString(value));
	}
	public int replace(CharSequence token, float value)
	{
		return replace(token, Float.toString(value));
	}
	public int replace(CharSequence token, double value)
	{
		return replace(token, Double.toString(value));
	}
	@Override
	public String toString()
	{
		return _ca != null ? new String(_ca, 0, _count) : _html;
	}
	
	/*
		<a action="bypass -h Quest 100_SagaOfTheMaestro 0-1">"I'll do it."</a></body></html>
				   |         |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
				   start.....start                         finish
	 */
	private final void buildBypassCache(L2PcInstance activeChar)
	{
		if (activeChar == null)
			return;
		
		if (_ca != null) { _html = new String(_ca, 0, _count); _ca = null; }	//[JOJO]
		
		activeChar.clearBypass();
		int len = _html.length();
		for (int i = 0; i < len; i++)
		{
			int start = _html.indexOf("\"bypass ", i);
			int finish = _html.indexOf("\"", start + 1);
			if (start < 0 || finish < 0)
				break;
			
			if (_html.substring(start + 8, start + 10).equals("-h"))
				start += 11;
			else
				start += 8;
			
			i = finish;
			int finish2 = _html.indexOf("$", start);
			if (finish2 < finish && finish2 > 0)
				activeChar.addBypass2(_html.substring(start, finish2).trim());
			else
				activeChar.addBypass(_html.substring(start, finish).trim());
		}
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x19);
		
		writeD(_npcObjId);
		if (_ca != null) {
			for (int i = 0; i < _count; i++) _buf.putChar(_ca[i]);
			_ca = null;
		} else
			writeS(_html);
		if (_npcObjId != 0)
			writeD(_itemId);
	}
}
