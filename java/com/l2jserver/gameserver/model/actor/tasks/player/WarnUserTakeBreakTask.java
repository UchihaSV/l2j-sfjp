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
package com.l2jserver.gameserver.model.actor.tasks.player;

import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;

/**
 * Task dedicated to warn user to take a break.
 * @author UnAfraid
 */
public class WarnUserTakeBreakTask implements Runnable
{
	private final L2PcInstance _player;
	
	public WarnUserTakeBreakTask(L2PcInstance player)
	{
		_player = player;
	}
	
	@Override
	public void run()
	{
		if (_player != null)
		{
			if (_player.isOnline())
			{
				SystemMessageId.PLAYING_FOR_LONG_TIME.setParamCount(1);	//[JOJO] en=0, ja,ta=1
				SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.PLAYING_FOR_LONG_TIME);
				// [L2J_JP_ADD][JOJO] ���p���Ԃ̌v�Z�E�\��
				msg.addNumber((int)(_player.getUptime() / 3600000));
				_player.sendPacket(msg);
			}
			else
			{
				_player.stopWarnUserTakeBreak();
			}
		}
	}
}
