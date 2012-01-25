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
import com.l2jserver.gameserver.model.L2Party;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.network.serverpackets.PlaySound;

/**
 * @author Nyaran
 * 
 * update JOJO
 * 
 * NPC          106     �o�[�X�f�C�P�[�L (type: L2BirthdayCake)
 * |�A�C�e��    20314   �o�[�X�f�C �P�[�L �p�b�N - �o�C�^���e�B (capsuled_items)
 * |�A�C�e��    20320   �o�[�X�f�C �P�[�L - �o�C�^���e�B�F24���Ԍ��� TODO:24���Ԍo���Ă������܂���
 * |�����X�L��  22034-1 �o�[�X�f�C �P�[�L
 * |BUF�X�L��   22035-1 �o�[�X�f�C �P�[�L���� - �o�[�X�f�C �P�[�L�̃o�C�^���e�B���ʁB �o���l���l�����āA�o�C�^���e�B���񕜂��Ă����ԁB
 * NPC          139     �o�[�X�f�C�P�[�L (type: L2BirthdayCake)
 * |�A�C�e��    21169   ��т̃o�[�X�f�C �M�t�g �{�b�N�X (capsuled_items)
 * |�A�C�e��    21595   �o�[�X�f�C �P�[�L
 * |�����X�L��  22249-1 �o�[�X�f�C �P�[�L
 * |BUF�X�L��   22250-1 �o�[�X�f�C �P�[�L���� - �o�[�X�f�C �P�[�L�̃o�C�^���e�B���ʁB5���ԁA�o�C�^���e�B���ۂ���Ă����ԁB
 */
public class L2BirthdayCakeInstance extends L2Npc
{
	private static final int BIRTHDAY_CAKE_24 = 106;
	private static final int BIRTHDAY_CAKE = 139;
	private L2Skill _skill;
	private ScheduledFuture<?> _aiTask;
	
	public L2BirthdayCakeInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2BirthdayCakeInstance);
	}
	
	private class BuffTask implements Runnable
	{
		@Override
		public void run()
		{
			final L2BirthdayCakeInstance cake = L2BirthdayCakeInstance.this;
			final L2Skill skill = _skill;
			switch (cake.getNpcId())
			{
				case BIRTHDAY_CAKE_24:
					for (L2PcInstance player : cake.getKnownList().getKnownPlayersInRadius(skill.getSkillRadius()))
					{
						if (player.getFirstEffect(skill) == null)
						{
							skill.getEffects(cake, player);
						}
					}
					break;
					
				case BIRTHDAY_CAKE:
					final L2PcInstance player = (L2PcInstance) cake.getSummoner();
					if (player == null)
					{
						return;
					}
					
					final L2Party party = player.getParty();
					if (party == null)
					{
						if (player.isInsideRadius(cake, skill.getSkillRadius(), true, true)
						 && player.getFirstEffect(skill) == null)
						{
							skill.getEffects(cake, player);
						}
					}
					else
					{
						for (L2PcInstance member : party.getPartyMembers())
						{
							if ((member != null) && member.isInsideRadius(cake, skill.getSkillRadius(), true, true)
							 && member.getFirstEffect(skill) == null)
							{
								skill.getEffects(cake, member);
							}
						}
					}
					break;
			}
		}
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		setShowSummonAnimation(false);
		
		if (!isInsideZone(ZONE_PEACE))
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
			
			broadcastPacket(new PlaySound(1, "HB01", 0, 0, 0, 0, 0));	//[JOJO]
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
	
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return false;
	}
}
