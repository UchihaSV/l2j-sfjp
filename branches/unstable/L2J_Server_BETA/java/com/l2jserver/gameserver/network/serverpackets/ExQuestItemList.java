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

/**
 * @author JIV
 */
public class ExQuestItemList extends AbstractItemPacket
{
	private final L2PcInstance _activeChar;
	private final L2ItemInstance[] _items;
	private final int _size;
	
	public ExQuestItemList(L2PcInstance activeChar)
	{
		_activeChar = activeChar;
		
		_items = activeChar.getInventory().getItems();
		int index = 0;
		for (int i = 0, length = _items.length; i < length; ++i)
		{
			L2ItemInstance item = _items[i];
			if (item.isQuestItem())
				_items[index++] = item;
		}
		_size = index;
	}
	
	@Override
	protected void writeImpl()
	{
		writeC(0xFE);
		writeH(0xC6);
		writeH(_size);
		for (int index = 0; index < _size; ++index)
		{
			writeItem(_items[index]);
		}
		writeInventoryBlock(_activeChar.getInventory());
	}
}
