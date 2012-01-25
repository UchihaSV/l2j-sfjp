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
package com.l2jserver.gameserver.model.actor.stat;

import com.l2jserver.gameserver.model.actor.L2Npc;
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
	public L2Npc getActiveChar() { return (L2Npc)super.getActiveChar(); }
	
	@Override
	public int getWalkSpeed(){ return (int) calcStat(Stats.WALK_SPEED, getActiveChar().getTemplate().getBaseWalkSpd(), null, null);}
	
	@Override
	public float getMovementSpeedMultiplier()
	{
    	//[JOJO]-------------------------------------------------
    	L2Npc ac = getActiveChar();
		if (ac == null)
			return 1f;
		if (ac.isRunning()) {
			int baseRunSpd = ac.getTemplate().getBaseRunSpd();
			if (baseRunSpd == 0)
				return 1f;
			return (float)getRunSpeed() / baseRunSpd;
		} else {
			int baseWalkSpd = ac.getTemplate().getBaseWalkSpd();
			if (baseWalkSpd == 0)
				return 1f;
			return (float)getWalkSpeed() / baseWalkSpd;
		}
	//	if (getActiveChar().isRunning())
	//		return getRunSpeed() * 1f / getActiveChar().getTemplate().getBaseRunSpd();
	//	return getWalkSpeed() * 1f / getActiveChar().getTemplate().getBaseWalkSpd();
		//-------------------------------------------------------
	}
}
