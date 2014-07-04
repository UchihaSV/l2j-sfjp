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

import com.l2jserver.gameserver.datatables.ManorData;
import com.l2jserver.gameserver.instancemanager.CastleManager;
import com.l2jserver.gameserver.instancemanager.CastleManorManager;
import com.l2jserver.gameserver.model.SeedProduction;
import com.l2jserver.gameserver.model.entity.Castle;

/**
 * @author l3x
 */
public class ExShowSeedSetting extends L2GameServerPacket
{
	private final int _manorId;
	private final int _count;
	private final long[] _seedData; // data to send, size:_count*12
	
	public ExShowSeedSetting(int manorId)
	{
		_manorId = manorId;
		Castle c = CastleManager.getInstance().getCastleById(_manorId);
		int[] seeds = ManorData.getInstance().getSeedsForCastle(_manorId);
		_count = seeds.length;
		_seedData = new long[_count * 12];
		for (int i = 0; i < _count; ++i)
		{
			int s = seeds[i];
			final int dx = i * 12;
			_seedData[dx + 0] = s;
			_seedData[dx + 1] = ManorData.getInstance().getSeedLevel(s);
			_seedData[dx + 2] = ManorData.getInstance().getRewardItemBySeed(s, 1);
			_seedData[dx + 3] = ManorData.getInstance().getRewardItemBySeed(s, 2);
			_seedData[dx + 4] = ManorData.getInstance().getSeedSaleLimit(s);
			_seedData[dx + 5] = ManorData.getInstance().getSeedBuyPrice(s);
			_seedData[dx + 6] = ManorData.getInstance().getSeedBasicPrice(s) * 60 / 100;
			_seedData[dx + 7] = ManorData.getInstance().getSeedBasicPrice(s) * 10;
			SeedProduction seedPr = c.getSeed(s, CastleManorManager.PERIOD_CURRENT);
			if (seedPr != null)
			{
				_seedData[dx + 8] = seedPr.getStartProduce();
				_seedData[dx + 9] = seedPr.getPrice();
			}
		//	else
		//	{
		//		_seedData[dx + 8] = 0;
		//		_seedData[dx + 9] = 0;
		//	}
			seedPr = c.getSeed(s, CastleManorManager.PERIOD_NEXT);
			if (seedPr != null)
			{
				_seedData[dx + 10] = seedPr.getStartProduce();
				_seedData[dx + 11] = seedPr.getPrice();
			}
		//	else
		//	{
		//		_seedData[dx + 10] = 0;
		//		_seedData[dx + 11] = 0;
		//	}
		}
	}
	
	@Override
	public void writeImpl()
	{
		writeC(0xFE); // Id
		writeH(0x26); // SubId
		
		writeD(_manorId); // manor id
		writeD(_count); // size
		
		for (int i = 0; i < _count; i++)
		{
			final int dx = i * 12;
			writeD((int) _seedData[dx + 0]); // seed id
			writeD((int) _seedData[dx + 1]); // level
			writeC(1);
			writeD((int) _seedData[dx + 2]); // reward 1 id
			writeC(1);
			writeD((int) _seedData[dx + 3]); // reward 2 id
			
			writeD((int) _seedData[dx + 4]); // next sale limit
			writeD((int) _seedData[dx + 5]); // price for castle to produce 1
			writeD((int) _seedData[dx + 6]); // min seed price
			writeD((int) _seedData[dx + 7]); // max seed price
			
			writeQ(_seedData[dx + 8]); // today sales
			writeQ(_seedData[dx + 9]); // today price
			writeQ(_seedData[dx + 10]); // next sales
			writeQ(_seedData[dx + 11]); // next price
		}
	}
}
