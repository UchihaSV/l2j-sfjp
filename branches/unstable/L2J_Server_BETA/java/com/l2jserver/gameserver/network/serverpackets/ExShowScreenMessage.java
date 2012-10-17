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

import java.util.ArrayList;
import java.util.List;

import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.SystemMessageId;

/**
 * @author Kerberos
 */
public class ExShowScreenMessage extends L2GameServerPacket
{
	private final int _type;
	private final int _sysMessageId;
	private final int _position;
	private final int _unk1;
	private final int _size;
	private final int _unk2;
	private final int _unk3;
	private final boolean _effect;
	private final int _time;
	private final boolean _fade;
	private final String _text;
	private final int _npcString;
	private List<String> _parameters;
	
	public ExShowScreenMessage(String text, int time)
	{
		this(text, 2, time);
	}
	
	public ExShowScreenMessage(int npcString, int position, int time) // For npcstring
	{
		this(2, -1, position, 0, 0, 0, 0, false, time, false, null, npcString);
	}
	
	public ExShowScreenMessage(NpcStringId npcString, int position, int time) // For npcstring
	{
		this(2, -1, position, 0, 0, 0, 0, false, time, false, null, npcString.getId());
	}
	
	public ExShowScreenMessage(SystemMessageId systemMsg, int position, int time) // For SystemMessage
	{
		this(2, systemMsg.getId(), position, 0, 0, 0, 0, false, time, false, null, -1);
	}
	
	public ExShowScreenMessage(String text, int position, int time)
	{
		this(1, -1, position, 0, 0, 0, 0, false, time, false, text, -1);
	}
	
	/**
	 * String parameter for argument S1,S2,.. in npcstring-e.dat
	 * @param text
	 */
	public ExShowScreenMessage addStringParameter(String text)
	{
		if (_parameters == null)
			_parameters = new ArrayList<>();
		_parameters.add(text);
		return this;
	}
	public ExShowScreenMessage addString(String text)
	{
		return addStringParameter(text);
	}
	public ExShowScreenMessage addPcName(L2PcInstance pc)
	{
		return addStringParameter(pc.getName());
	}
	public ExShowScreenMessage addNpcName(L2Npc npc)
	{
		return addStringParameter(npc.getName());
	}
	public ExShowScreenMessage addNumber(int number)
	{
		return addStringParameter(String.valueOf(number));
	}
	
	public ExShowScreenMessage(int type, int messageId, int position, int unk1, int size, int unk2, int unk3,boolean showEffect, int time,boolean fade, String text, int npcString)
	{
		_type = type;
		_sysMessageId = messageId;
		_unk1 = unk1;
		_unk2 = unk2;
		_unk3 = unk3;
		_fade = fade;
		_position = position;
		_text = text;
		_time = time;
		_size = size;
		_effect = showEffect;
		_npcString = npcString;
	}
	
	/*TODO:@Deprecated */public ExShowScreenMessage(int type, int messageId, int position, int unk1, int size, int unk2, int unk3,boolean showEffect, int time,boolean fade, String text, NpcStringId npcString)
	{
		this(type, messageId, position, unk1, size, unk2, unk3, showEffect, time, fade, text, npcString.getId());
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0x39);
		writeD(_type); // 0 - system messages, 1 - your defined text, 2 - npcstring
		writeD(_sysMessageId); // system message id (_type must be 0 otherwise no effect)
		writeD(_position); // message position
		writeD(_unk1); // ?
		writeD(_size); // font size 0 - normal, 1 - small
		writeD(_unk2); // ?
		writeD(_unk3); // ?
		writeD(_effect ? 1 : 0); // upper effect (0 - disabled, 1 enabled) - _position must be 2 (center) otherwise no effect
		writeD(_time); // time
		writeD(_fade ? 1 : 0); // fade effect (0 - disabled, 1 enabled)
		writeD(_npcString); // npcString
		if (_npcString == -1)
		{
			writeS(_text); // your text (_type must be 1, otherwise no effect)
		}
		else
		{
			if (_parameters != null)
			{
				for (String s : _parameters)
				{
					writeS(s);
				}
			}
		}
	}
}
