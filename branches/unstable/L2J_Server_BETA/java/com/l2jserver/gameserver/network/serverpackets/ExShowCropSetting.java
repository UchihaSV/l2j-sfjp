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
import com.l2jserver.gameserver.model.CropProcure;
import com.l2jserver.gameserver.model.entity.Castle;

/**
 * @author l3x
 */
public class ExShowCropSetting extends L2GameServerPacket
{
	private final int _manorId;
	private final int _count;
	private final long[] _cropData; // data to send, size:_count*14
	
	public ExShowCropSetting(int manorId)
	{
		_manorId = manorId;
		Castle c = CastleManager.getInstance().getCastleById(_manorId);
		int[] crops = ManorData.getInstance().getCropsForCastle(_manorId);
		_count = crops.length;
		_cropData = new long[_count * 14];
		for (int i = 0; i < _count; ++i)
		{
			final int cr = crops[i];
			final int dx = i * 14;
			_cropData[dx + 0] = cr;
			_cropData[dx + 1] = ManorData.getInstance().getSeedLevelByCrop(cr);
			_cropData[dx + 2] = ManorData.getInstance().getRewardItem(cr, 1);
			_cropData[dx + 3] = ManorData.getInstance().getRewardItem(cr, 2);
			_cropData[dx + 4] = ManorData.getInstance().getCropPuchaseLimit(cr);
			_cropData[dx + 5] = 0; // Looks like not used
			_cropData[dx + 6] = ManorData.getInstance().getCropBasicPrice(cr) * 60 / 100;
			_cropData[dx + 7] = ManorData.getInstance().getCropBasicPrice(cr) * 10;
			CropProcure cropPr = c.getCrop(cr, CastleManorManager.PERIOD_CURRENT);
			if (cropPr != null)
			{
				_cropData[dx + 8] = cropPr.getStartAmount();
				_cropData[dx + 9] = cropPr.getPrice();
				_cropData[dx + 10] = cropPr.getReward();
			}
		//	else
		//	{
		//		_cropData[dx + 8] = 0;
		//		_cropData[dx + 9] = 0;
		//		_cropData[dx + 10] = 0;
		//	}
			cropPr = c.getCrop(cr, CastleManorManager.PERIOD_NEXT);
			if (cropPr != null)
			{
				_cropData[dx + 11] = cropPr.getStartAmount();
				_cropData[dx + 12] = cropPr.getPrice();
				_cropData[dx + 13] = cropPr.getReward();
			}
		//	else
		//	{
		//		_cropData[dx + 11] = 0;
		//		_cropData[dx + 12] = 0;
		//		_cropData[dx + 13] = 0;
		//	}
		}
	}
	
	@Override
	public void writeImpl()
	{
		writeC(0xFE); // Id
		writeH(0x2b); // SubId
		
		writeD(_manorId); // manor id
		writeD(_count); // size
		
		for (int i = 0; i < _count; i++)
		{
			final int dx = i * 14;
			writeD((int) _cropData[dx + 0]); // crop id
			writeD((int) _cropData[dx + 1]); // seed level
			writeC(1);
			writeD((int) _cropData[dx + 2]); // reward 1 id
			writeC(1);
			writeD((int) _cropData[dx + 3]); // reward 2 id
			
			writeD((int) _cropData[dx + 4]); // next sale limit
			writeD((int) _cropData[dx + 5]); // ???
			writeD((int) _cropData[dx + 6]); // min crop price
			writeD((int) _cropData[dx + 7]); // max crop price
			
			writeQ(_cropData[dx + 8]); // today buy
			writeQ(_cropData[dx + 9]); // today price
			writeC((int) _cropData[dx + 10]); // today reward
			
			writeQ(_cropData[dx + 11]); // next buy
			writeQ(_cropData[dx + 12]); // next price
			writeC((int) _cropData[dx + 13]); // next reward
		}
	}
}
