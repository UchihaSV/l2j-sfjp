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

import java.util.Collection;
import java.util.concurrent.ScheduledFuture;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.L2Effect;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Skill;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.zone.type.L2TownZone;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;
import com.l2jserver.gameserver.templates.skills.L2SkillType;

/**
 * @author Drunkard Zabb0x
 * Lets drink2code!
 * 
 * @author JOJO
 * 
 * NPC          13006   �N���X�}�X �c���[  (type: L2Npc)
 * |�A�C�e��    5560    �N���X�}�X �c���[
 * |�����X�L��  2137-1  �c���[����
 * 
 * NPC          13007   �����N���X�}�X �c���[  (type: L2XmassTree)
 * |�A�C�e��    5561    �����N���X�}�X �c���[
 * |�����X�L��  2138-1  �����c���[����
 * |BUF�X�L��   2139-1  �����c���[�̉񕜃{�[�i�X - �N���X�}�X �C�x���g�̍����c���[���ʁBHP�񕜃{�[�i�X�AMP�񕜃{�[�i�X�����サ����ԁB
 */
public class L2XmassTreeInstance extends L2Npc
{
	private static final int[][] _buffs = {
		 { 4262, 2 }	// �N���X�}�X �E�B���h �E�H�[�N�^��莞�ԁA�ړ����x�����サ����ԁB����2�B
		,{ 4263, 1 }	// �N���X�}�X �w�C�X�g�^�ꎞ�I�ɍU�����x�����コ����B����1�B
		,{ 4264, 1 }	// �N���X�}�X �G���p���[�^�ꎞ�I�ɖ��͂����サ����ԁB����1�B
		,{ 4265, 3 }	// �N���X�}�X �}�C�g�^�ꎞ�I�ɍU���͂����サ����ԁB����3�B
		,{ 4266, 3 }	// �N���X�}�X �V�[���h�^�ꎞ�I�ɖh��͂����サ����ԁB����3�B
	};

	protected/*private*/ ScheduledFuture<?> _aiTask;

	protected class XmassAI implements Runnable
	{
		protected final L2XmassTreeInstance _caster;
		protected final L2Skill _skill;
		protected final String _abnormalType;
		private int _buffIndex = 0;
		
		protected XmassAI(L2XmassTreeInstance caster, L2Skill skill)
		{
			_caster = caster;
			_skill = skill;
			if (_skill.getSkillType() == L2SkillType.NOTDONE) throw new RuntimeException();
			_abnormalType = _skill.getEffectTemplates()[0].abnormalType;
		}
		
		public void run()
		{
			Collection<L2PcInstance> plrs = getKnownList().getKnownPlayersInRadius(_skill.getSkillRadius());
			for (L2PcInstance player : plrs)
			{
				if (player.isInvul())
					continue;
				if (player.isMovementDisabled())
					continue;
				if (player.getPkKills() > 5)
					continue;
				if (player.getCurrentHp() < player.getMaxHp()
				 || player.getCurrentMp() < player.getMaxMp())
				{
					handleEffect(player);
				}
				else
				{
					int[] b = _buffs[_buffIndex];
					handleCast(player, b[0], b[1]);
				}
			}
			_buffIndex = (_buffIndex + 1) % _buffs.length;
		}
		
		protected void handleEffect(L2PcInstance player)
		{
			for (L2Effect e : player.getAllEffects())
				if (_abnormalType.equals(e.getAbnormalType()))
					return;
			_skill.getEffects(player, player);
		}
		
		protected boolean handleCast(L2PcInstance player, int skillId, int skillLevel)
		{
			L2Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);

			if (player.getFirstEffect(skill) == null)
			{
				setTarget(player);
				doCast(skill);

				MagicSkillUse msu = new MagicSkillUse(_caster, player, skillId, skillLevel, skill.getHitTime(), 0);
				broadcastPacket(msu);

				return true;
			}

			return false;
		}

	}
	
	public L2XmassTreeInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2XmassTreeInstance);
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		
		if (getNpcId() == 13007
				&& ZoneManager.getInstance().getZone(this, L2TownZone.class) == null)
			_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new XmassAI(this,SkillTable.getInstance().getInfo(2139, 1)), 3000, 3000);
	}
	
	@Override
	public void deleteMe()
	{
		if (_aiTask != null) _aiTask.cancel(true);
		_aiTask = null;
		
		super.deleteMe();
	}
	
	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		return 900;
	}
	
	/* (non-Javadoc)
	 * @see com.l2jserver.gameserver.model.L2Object#isAttackable()
	 */
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return false;
	}
	
	/**
	 * @see com.l2jserver.gameserver.model.actor.L2Npc#onAction(com.l2jserver.gameserver.model.actor.instance.L2PcInstance, boolean)
	 */
	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
}
