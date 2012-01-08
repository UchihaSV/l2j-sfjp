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
 * NPC          143     �̗͂̃g�[�e��
 * |�A�C�e��    21899   �̗͂̃g�[�e��
 * |�����X�L��  22294-1 �̗͂̃g�[�e��
 * |BUF�X�L��   23308-1 �g�[�e���̃G�i�W�[�F�̗� - �_��I�ȃg�[�e���̗͂�HP�񕜃{�[�i�X��30%���サ�܂��B�n�`����󂯂�_���[�W��80%�������܂��B�g�[�e���͈̔͂��O���ƌ��ʂ͏����܂��B
 * NPC          144     ���_�̃g�[�e��
 * |�A�C�e��    21900   ���_�̃g�[�e��
 * |�����X�L��  22295-1 ���_�̃g�[�e��
 * |BUF�X�L��   23309-1 �g�[�e���̃G�i�W�[�F���_ - �_��I�ȃg�[�e���̗͂�MP�񕜃{�[�i�X��30%���サ�܂��B�g�[�e���͈̔͂��O���ƌ��ʂ͏����܂��B
 * NPC          145     �E�C�̃g�[�e��
 * |�A�C�e��    21901   �E�C�̃g�[�e��
 * |�����X�L��  22296-1 �E�C�̃g�[�e��
 * |BUF�X�L��   23310-1 �g�[�e���̃G�i�W�[�F�E�C - �_��I�ȃg�[�e���̗͂Ŗh��͂�15%���サ�܂��B�g�[�e���͈̔͂��O���ƌ��ʂ͏����܂��B
 * NPC          146     �s���̃g�[�e��
 * |�A�C�e��    21902   �s���̃g�[�e��
 * |�����X�L��  22297-1 �s���̃g�[�e��
 * |BUF�X�L��   23311-1 �g�[�e���̃G�i�W�[�F�s�� - �_��I�ȃg�[�e���̗͂Ŗ��@��R�͂�25%���サ�܂��B�g�[�e���͈̔͂��O���ƌ��ʂ͏����܂��B
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
