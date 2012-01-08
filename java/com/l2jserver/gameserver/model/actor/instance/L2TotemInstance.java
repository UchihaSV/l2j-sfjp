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
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Skill;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;

/**
 * @author UnAfraid
 * 
 * @author JOJO
 * 
 * NPC          143     体力のトーテム
 * |アイテム    21899   体力のトーテム
 * |召喚スキル  22294-1 体力のトーテム
 * |BUFスキル   23308-1 トーテムのエナジー：体力 - 神秘的なトーテムの力でHP回復ボーナスが30%向上します。地形から受けるダメージが80%減少します。トーテムの範囲を外れると効果は消えます。
 * NPC          144     精神のトーテム
 * |アイテム    21900   精神のトーテム
 * |召喚スキル  22295-1 精神のトーテム
 * |BUFスキル   23309-1 トーテムのエナジー：精神 - 神秘的なトーテムの力でMP回復ボーナスが30%向上します。トーテムの範囲を外れると効果は消えます。
 * NPC          145     勇気のトーテム
 * |アイテム    21901   勇気のトーテム
 * |召喚スキル  22296-1 勇気のトーテム
 * |BUFスキル   23310-1 トーテムのエナジー：勇気 - 神秘的なトーテムの力で防御力が15%向上します。トーテムの範囲を外れると効果は消えます。
 * NPC          146     不屈のトーテム
 * |アイテム    21902   不屈のトーテム
 * |召喚スキル  22297-1 不屈のトーテム
 * |BUFスキル   23311-1 トーテムのエナジー：不屈 - 神秘的なトーテムの力で魔法抵抗力が25%向上します。トーテムの範囲を外れると効果は消えます。
 */
public class L2TotemInstance extends L2Npc
{
	private ScheduledFuture<?> _aiTask;
	
	private L2Skill _skill;
	
	private class TotemAI implements Runnable
	{
		private final L2TotemInstance _caster;
		
		protected TotemAI(L2TotemInstance caster)
		{
			_caster = caster;
		}
		
		@Override
		public void run()
		{
			if (_skill == null)
			{
				if (_caster._aiTask != null)
				{
					_caster._aiTask.cancel(false);
					_caster._aiTask = null;
				}
				return;
			}
			
			for (L2PcInstance player : getKnownList().getKnownPlayersInRadius(_skill.getSkillRadius()))
			{
				if (player.getFirstEffect(_skill.getId()) == null)
				{
					_skill.getEffects(player, player);
				}
			}
		}
	}
	
	public L2TotemInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2TotemInstance);
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
	
	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		return 900;
	}
	
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return false;
	}
	
	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void setAITask()
	{
		if (_aiTask == null)
		{
			_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new TotemAI(this), 3000, 3000);
		}
	}
	
	/**
	 * @param skillId the _skill to set
	 */
	public void setSkill(int skillId)
	{
		_skill = SkillTable.getInstance().getInfo(skillId, 1);
	}
}
