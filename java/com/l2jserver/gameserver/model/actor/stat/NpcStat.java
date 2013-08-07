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
package com.l2jserver.gameserver.model.actor.stat;

import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.stats.MoveType;
import com.l2jserver.gameserver.model.stats.Stats;

public class NpcStat extends CharStat
{
	public NpcStat(L2Npc activeChar)
	{
		super(activeChar);
	}
	
	@Override
	public byte getLevel()
	{
		return getActiveChar().getTemplate().getLevel();
	}
	
	@Override
	public L2Npc getActiveChar()
	{
		return (L2Npc) super.getActiveChar();
	}
	
	@Override
	public int getWalkSpeed()
	{
		return (int) calcStat(Stats.MOVE_SPEED, getActiveChar().getTemplate().getBaseMoveSpd(MoveType.WALK), null, null);
	}
	
	@Override
	public float getMovementSpeedMultiplier()
	{
    	//[JOJO]-------------------------------------------------
		L2Npc ac = getActiveChar();
		if (ac == null)
		{
			return 1f;
		}

		int baseMoveSpd = ac.getTemplate().getBaseMoveSpd(ac.isRunning() ? MoveType.RUN : MoveType.WALK);
		if (baseMoveSpd == 0)
		{
			return 1f;
		}
		
		return (float) getWalkSpeed() / baseMoveSpd;
	//	//-------------------------------------------------------
	//	if (getActiveChar() == null)
	//	{
	//		return 1;
	//	}
	//
	//	return (getWalkSpeed() * 1f) / getActiveChar().getTemplate().getBaseMoveSpd(getActiveChar().isRunning() ? MoveType.RUN : MoveType.WALK);
	}
}
