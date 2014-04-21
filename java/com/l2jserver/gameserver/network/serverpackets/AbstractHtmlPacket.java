/*
 * Copyright (C) 2004-2014 L2J Server
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
package com.l2jserver.gameserver.network.serverpackets;

import java.util.logging.Level;

import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.enums.HtmlActionScope;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.util.Util;

/**
 * @author FBIagent
 */
public abstract class AbstractHtmlPacket extends L2GameServerPacket
{
	public static final char VAR_PARAM_START_CHAR = '$';
	
	private final int _npcObjId;
	private String _html = null;
	private boolean _disabledValidation = false;
	
	protected AbstractHtmlPacket()
	{
		_npcObjId = 0;
	}
	
	protected AbstractHtmlPacket(int npcObjId)
	{
		if (npcObjId < 0)
		{
			throw new IllegalArgumentException();
		}
		
		_npcObjId = npcObjId;
	}
	
	protected AbstractHtmlPacket(String html)
	{
		_npcObjId = 0;
		setHtml(html);
	}
	
	protected AbstractHtmlPacket(int npcObjId, String html)
	{
		if (npcObjId < 0)
		{
			throw new IllegalArgumentException();
		}
		
		_npcObjId = npcObjId;
		setHtml(html);
	}
	
	public final void disableValidation()
	{
		_disabledValidation = true;
	}
	
	public final void setHtml(String html)
	{
		if (html.length() > 17200)
		{
			_log.log(Level.WARNING, "Html is too long! this will crash the client!", new Throwable());
			_html = html.substring(0, 17200);
		}
		
		if (!html.contains("<html"))
		{
			html = "<html><body>" + html + "</body></html>";
		}
		
		_html = html;
	}
	
	public boolean setFile(L2PcInstance pc, String path)	//[JOJO]
	{
		return setFile(pc.getHtmlPrefix(), path);
	}
	
	public final boolean setFile(String prefix, String path)
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
	public final int replace(CharSequence pattern, CharSequence value)	//[JOJO]
	{
		if (value == null) value = "null";
		int result = 0;
		if (_ca == null) {
			_count = _html.length();
			_ca = new char[_count * 2];
			_html.getChars(0, _count, _ca, 0);
		}
		final char first = pattern.charAt(0);
		final int token_length = pattern.length();
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
							if (_ca[end++] != pattern.charAt(i++))
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
	
	@Override
	public String toString()
	{
		return getHtml();	//[JOJO]
	}
	
	public final int replace(CharSequence pattern, boolean val)
	{
		return replace(pattern, Boolean.toString(val));
	}
	
	public final int replace(CharSequence pattern, int val)
	{
		return replace(pattern, Integer.toString(val));
	}
	
	public final int replace(CharSequence pattern, long val)
	{
		return replace(pattern, Long.toString(val));
	}
	
	public final int replace(CharSequence pattern, float val)
	{
		return replace(pattern, Float.toString(val));
	}
	
	public final int replace(CharSequence pattern, double val)
	{
		return replace(pattern, Double.toString(val));
	}
	
	public final AbstractHtmlPacket replaceAll(String regex, String replacement)	//[JOJO]
	{
		_html = getHtml().replaceAll(regex, replacement);
		return this;
	}
	
	public final AbstractHtmlPacket replaceFirst(String regex, String replacement)	//[JOJO]
	{
		_html = getHtml().replaceFirst(regex, replacement);
		return this;
	}
	
	@Override
	public final void runImpl()
	{
		L2PcInstance player = getClient().getActiveChar();
		player.clearHtmlActions(getScope());
		
		if (_disabledValidation)
		{
			return;
		}
		
		Util.buildHtmlActionCache(player, getScope(), _npcObjId, getHtml());	//[JOJO] _html --> getHtml()
	}
	
	public final int getNpcObjId()
	{
		return _npcObjId;
	}
	
	public final String getHtml()
	{
		if (_ca != null) { _html = new String(_ca, 0, _count); _ca = null; }	//[JOJO]
		return _html;
	}
	
	public abstract HtmlActionScope getScope();
}
