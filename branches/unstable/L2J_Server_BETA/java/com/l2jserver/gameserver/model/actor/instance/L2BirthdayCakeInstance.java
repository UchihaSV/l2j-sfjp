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
package com.l2jserver.gameserver.model.actor.instance;

import java.util.concurrent.ScheduledFuture;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.L2Party;
import com.l2jserver.gameserver.model.L2Skill;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.zone.type.L2PeaceZone;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;

/**
 * @author Nyaran
 */
public class L2BirthdayCakeInstance extends L2Npc implements IhaveOwner
{
	private static final int BIRTHDAY_CAKE_24 = 106;
	private static final int BIRTHDAY_CAKE = 139;
	private L2Skill _skill;
	private ScheduledFuture<?> _aiTask;
	private int _masterId;
	
	public L2BirthdayCakeInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}
	
	@Override
	public final L2PcInstance getOwner()	//[JOJO]
	{
		return L2World.getInstance().getPlayer(_masterId);
	}
	@Override
	public void setOwner(L2PcInstance newOwner)	//[JOJO]
	{
		_masterId = newOwner.getObjectId();
	}
	
	private class BuffTask implements Runnable
	{
		@Override
		public void run()
		{
			L2BirthdayCakeInstance npc = L2BirthdayCakeInstance.this;
			switch (getNpcId())
			{
				case BIRTHDAY_CAKE_24:
					for (L2PcInstance player : getKnownList().getKnownPlayersInRadius(_skill.getSkillRadius()))
					{
						_skill.getEffects(npc, player);
					}
					break;
					
				case BIRTHDAY_CAKE:
					L2PcInstance player = L2World.getInstance().getPlayer(_masterId);
					if (player == null)
						return;
					
					L2Party party = player.getParty();
					if (party == null)
					{
						if (player.isInsideRadius(npc, _skill.getSkillRadius(), true, true))
							_skill.getEffects(npc, player);
					}
					else
					{
						for (L2PcInstance member : party.getPartyMembers())
						{
							if (member != null && member.isInsideRadius(npc, _skill.getSkillRadius(), true, true))
								_skill.getEffects(npc, member);
						}
					}
					break;
			}
		}
	}
	
	@Override
	public void onSpawn()
	{
		if (ZoneManager.getInstance().getZone(this, L2PeaceZone.class) == null)
		{
			switch (getNpcId())
			{
				case BIRTHDAY_CAKE_24:
					_skill = SkillTable.getInstance().getInfo(22035, 1);
					break;
				case BIRTHDAY_CAKE:
					_skill = SkillTable.getInstance().getInfo(22250, 1);
					break;
				default:
					throw new RuntimeException();
			}
			
			_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new BuffTask(), 3000, 3000);
		}
	}
	
	@Override
	public void deleteMe()
	{
		if (_aiTask != null)
		{
			_aiTask.cancel(true);
			_aiTask = null;
		}
		super.deleteMe();
	}
	
	/* (non-Javadoc)
	 * @see com.l2jserver.gameserver.model.L2Object#isAttackable()
	 */
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return false;
	}
}
