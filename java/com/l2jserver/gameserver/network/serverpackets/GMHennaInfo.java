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

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.items.L2Henna;

/**
 * @author KenM, Zoey76
 */
public final class GMHennaInfo extends L2GameServerPacket
{
	private static final String _S__F0_GMHENNAINFO = "[S] F0 GMHennaInfo";
	
	private final L2PcInstance _activeChar;
	private final L2Henna[] _hennas = new L2Henna[3];
	private final int _count;
	
	public GMHennaInfo(L2PcInstance player)
	{
		_activeChar = player;
		int j = 0;
		for (L2Henna henna : _activeChar.getHennaList())
		{
			if (henna != null)
			{
				_hennas[j++] = henna;
			}
		}
		_count = j;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xF0);
		writeC(_activeChar.getHennaStatINT()); // equip INT
		writeC(_activeChar.getHennaStatSTR()); // equip STR
		writeC(_activeChar.getHennaStatCON()); // equip CON
		writeC(_activeChar.getHennaStatMEN()); // equip MEN
		writeC(_activeChar.getHennaStatDEX()); // equip DEX
		writeC(_activeChar.getHennaStatWIT()); // equip WIT
		writeD(3); // Slots
		writeD(_count); // Size
		for (int i = 0; i < _count; i++)
		{
			writeD(_hennas[i].getDyeId());
			writeD(0x01);
		}
	}
	
	@Override
	public String getType()
	{
		return _S__F0_GMHENNAINFO;
	}
}
