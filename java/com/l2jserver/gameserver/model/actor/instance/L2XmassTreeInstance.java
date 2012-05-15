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
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.skills.L2SkillType;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jserver.gameserver.network.serverpackets.PlaySound;
import com.l2jserver.util.Rnd;

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
	static final int[][] _buffs = {
		 { 4262, 2 }	// �N���X�}�X �E�B���h �E�H�[�N�^��莞�ԁA�ړ����x�����サ����ԁB����2�B
		,{ 4263, 1 }	// �N���X�}�X �w�C�X�g�^�ꎞ�I�ɍU�����x�����コ����B����1�B
		,{ 4264, 1 }	// �N���X�}�X �G���p���[�^�ꎞ�I�ɖ��͂����サ����ԁB����1�B
		,{ 4265, 3 }	// �N���X�}�X �}�C�g�^�ꎞ�I�ɍU���͂����サ����ԁB����3�B
		,{ 4266, 3 }	// �N���X�}�X �V�[���h�^�ꎞ�I�ɖh��͂����サ����ԁB����3�B
	};
	
	private static final String[] XMASS_SONGS = {
		"CC_01", "CC_02", "CC_03", "CC_04", "CC_05", "CC_06"
	};
	
	protected ScheduledFuture<?> _aiTask;

	protected class XmassAI implements Runnable
	{
		protected final L2XmassTreeInstance _caster;
		protected final L2Skill _skill;
		private int _buffIndex = 0;
		
		protected XmassAI(L2XmassTreeInstance caster, L2Skill skill)
		{
			_caster = caster;
			_skill = skill;
			if (_skill.getSkillType() == L2SkillType.NOTDONE) throw new RuntimeException();
		}
		
		@Override
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
					handleBuff(player, b[0], b[1]);
				}
			}
			_buffIndex = (_buffIndex + 1) % _buffs.length;
		}
		
		protected void handleEffect(L2PcInstance player)
		{
			final L2Skill skill = _skill;
			if (! doesStack(player, skill))
				skill.getEffects(player, player);
		}
		
		protected void handleBuff(L2PcInstance player, int skillId, int skillLevel)
		{
			final L2Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
			if (! doesStack(player, skill))
			{
				skill.getEffects(_caster, player);
				player.sendPacket(new MagicSkillUse(_caster, player, skillId, skillLevel, skill.getHitTime(), 0));
			}
		}
		
		private boolean doesStack(L2PcInstance player, L2Skill checkSkill)
		{
			String stackType = checkSkill.getEffectTemplates()[0].abnormalType;
			for (L2Effect e : player.getAllEffects())
				if (stackType.equals(e.getAbnormalType()))
					return true;
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
	//	setShowSummonAnimation(false);
		
		if (getNpcId() == 13007
				&& !isInsideZone(ZONE_PEACE))
		{
			_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new XmassAI(this,SkillTable.getInstance().getInfo(2139, 1)), 3000, 3000);
			broadcastPacket(new PlaySound(1, XMASS_SONGS[Rnd.get(XMASS_SONGS.length)], 0, 0, 0, 0, 0));
		}
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
}
