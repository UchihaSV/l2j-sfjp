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
package com.l2jserver.gameserver.network.serverpackets;

import java.util.Collection;

import com.l2jserver.Config;
import com.l2jserver.gameserver.model.L2TradeList;
import com.l2jserver.gameserver.model.L2TradeList.L2TradeItem;

public final class BuyList extends L2GameServerPacket
{
	private final int _listId;
	private final Collection<L2TradeItem> _list;
	private final long _money;
	private double _taxRate = 0;
	
	public BuyList(L2TradeList list, long currentMoney, double taxRate)
	{
		_listId = list.getListId();
		_list = list.getItems();
		_money = currentMoney;
		_taxRate = taxRate;
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0xFE);
		writeH(0xB7);
		writeD(0x00);
		writeQ(_money); // current money
		writeD(_listId);
		
		writeH(_list.size());
		
		for (L2TradeItem item : _list)
		{
			final long item_getCurrentCount = item.getCurrentCount();	//[JOJO]
			if ((item_getCurrentCount > 0) || !item.hasLimitedStock())
			{
				writeD(item.getItemId());
				writeD(item.getItemId());
				writeD(0);
				writeQ(item_getCurrentCount < 0 ? 0 : item_getCurrentCount);
				writeH(item.getTemplate().getType2());
				writeH(item.getTemplate().getType1()); // Custom Type 1
				writeH(0x00); // isEquipped
				writeD(item.getTemplate().getBodyPart()); // Body Part
				writeH(0x00); // Enchant
				writeH(0x00); // Custom Type
				writeD(0x00); // Augment
				writeD(-1); // Mana
				writeD(-9999); // Time
				writeH(0x00); // Element Type
				writeH(0x00); // Element Power
				for (byte i = 0; i < 6; i++)
				{
					writeH(0x00);
				}
				// Enchant Effects
				writeH(0x00);
				writeH(0x00);
				writeH(0x00);
				
				//[L2J-JP EDIT START] —b•º”z’u}E‚‹‰—b•º”z’u}Eêt–¾‚Ì—b•º”z’u}E‚‹‰ŒP—û•º”z’u}EŒP—û•º”z’u}EƒlƒtƒBƒŠƒ€”z’u}
				final int item_getItemId = item.getItemId();	//[JOJO]
				if (item_getItemId >= 3960 && item_getItemId <= 4026//Config.RATE_SIEGE_GUARDS_PRICE-//'
				 || item_getItemId >= 5205 && item_getItemId <= 5219
				 || item_getItemId >= 6038 && item_getItemId <= 6306
				 || item_getItemId >= 6779 && item_getItemId <= 6833
				 || item_getItemId >= 7918 && item_getItemId <= 8029)
				//[L2J-JP EDIT END]
				{
					writeQ((long) (item.getPrice() * Config.RATE_SIEGE_GUARDS_PRICE * (1 + _taxRate)));
				}
				else
				{
					writeQ((long) (item.getPrice() * (1 + _taxRate)));
				}
			}
		}
	}
}
