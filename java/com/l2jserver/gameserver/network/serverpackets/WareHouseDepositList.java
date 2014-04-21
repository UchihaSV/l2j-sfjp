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

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;

public final class WareHouseDepositList extends AbstractItemPacket
{
	public static final int PRIVATE = 1;
	public static final int CLAN = 4;
	public static final int CASTLE = 3; // not sure
	public static final int FREIGHT = 1;
	private final long _playerAdena;
	private final L2ItemInstance[] _items;
	private final int _size;
	/**
	 * <ul>
	 * <li>0x01-Private Warehouse</li>
	 * <li>0x02-Clan Warehouse</li>
	 * <li>0x03-Castle Warehouse</li>
	 * <li>0x04-Warehouse</li>
	 * </ul>
	 */
	private final int _whType;
	
	public WareHouseDepositList(L2PcInstance player, int type)
	{
		_whType = type;
		_playerAdena = player.getAdena();
		
		final boolean isPrivate = _whType == PRIVATE;
		
		_items = player.getInventory().getAvailableItems(true, isPrivate, false);
		int index = 0;
		for (int i = 0, length = _items.length; i < length; ++i)
		{
			L2ItemInstance temp = _items[i];
			if (temp.isDepositable(isPrivate))
				_items[index++] = temp;
		}
		_size = index;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x41);
		writeH(_whType);
		writeQ(_playerAdena);
		writeH(_size);
		for (int index = 0; index < _size; ++index)
		{
			L2ItemInstance item = _items[index];
			writeItem(item);
			writeD(item.getObjectId());
		}
	}
}
