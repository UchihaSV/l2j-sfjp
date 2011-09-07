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

import static com.l2jserver.gameserver.ai.CtrlIntention.*;

import com.l2jserver.gameserver.ai.L2AttackableAI;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;

/**
 * @author JOJO
 * �|���i �C�x���g�A�X�C�J �C�x���g�p�����X�^�[�B
 * �E���҂����l��ێ�����B
 * �E�U���ł��邪�A�������Ă��Ȃ��B
 */
public class L2ChronoMonsterInstance extends L2MonsterInstance implements IhaveOwner
{
	private L2PcInstance _owner;
	
	public L2ChronoMonsterInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setAI(new L2ChronoAI(new AIAccessor()));
	}
	
	@Override
	public L2PcInstance getOwner()
	{
		return _owner;
	}
	@Override
	public void setOwner(L2PcInstance newOwner)
	{
		_owner = newOwner;
	}

	public class AIAccessor extends L2Character.AIAccessor
	{
		@Override
		public void detachAI()
		{}
	}
	
	@Override
	public L2Character getMostHated()
	{
		return null;
	}

	/* package com.l2jserver.gameserver.ai; */
	class L2ChronoAI extends L2AttackableAI implements Runnable
	{
		public L2ChronoAI(L2Character.AIAccessor accessor)
		{
			super(accessor);
		}
		
		/**
		 * Manage AI thinking actions of a L2Attackable.<BR><BR>
		 */
		@Override
		protected void onEvtThink()
		{
			// Check if the actor can't use skills and if a thinking action isn't already in progress
			if (/*_thinking ||*/ _actor.isAllSkillsDisabled())
				return;
			
			// Manage AI thinks of a L2Attackable
		/**	if (getIntention() == AI_INTENTION_ACTIVE)
				thinkActive();
			else **/ if (getIntention() == AI_INTENTION_ATTACK)
				setIntention(AI_INTENTION_ACTIVE);
			//	thinkAttack();
		}
	}
}