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

import com.l2jserver.gameserver.model.L2Clan;

public final class PledgeStatusChanged extends L2GameServerPacket
{
	private final L2Clan _clan;
	
	public PledgeStatusChanged(L2Clan clan)
	{
		_clan = clan;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xCD);
		writeD(_clan.getLeaderId());
		writeD(_clan.getId());
		writeD(_clan.getCrestId());
		writeD(_clan.getAllyId());
		writeD(_clan.getAllyCrestId());
		writeD(0x00);
		writeD(0x00);
	}
}
