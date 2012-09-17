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
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.skills.L2SkillType;
import com.l2jserver.gameserver.model.zone.type.L2TownZone;
import com.l2jserver.gameserver.network.serverpackets.SocialAction;
import com.l2jserver.util.Rnd;

/**
 * @author JOJO
 * 
 * NPC          28      ������ (type: L2Kotatsu) T25 Freya
 * NPC          127     ������ (type: L2Kotatsu) T26 H5
 * |�A�C�e��    20868   ������ - �������苗���ȏ㗣�ꂽ�Ƃ���ŏ�������ƁAHP�EMP�̉񕜌��ʂ���������܂��B�g���[�h�A�h���b�v�͂ł��܂���B
 * |�����X�L��  22127-1 �����Ȃ�������
 * |BUF�X�L��   22118-1 �����̂ʂ����� - �����̂ʂ����肪�������܂��BHP�񕜗͂�40%�ACP�񕜗͂�30%�AMP�񕜗͂�20%�������Ă����ԁB
 */
public class L2KotatsuInstance extends L2Npc implements Runnable
{
	private static final int MIN_NPC_ANIMATION = 40000;
	private static final int MAX_NPC_ANIMATION = 120000;
	/** Time of last social packet broadcast*/
	private long _lastSocialBroadcast = 0;
	/** Minimum interval between social packets*/
	private int _minimalSocialInterval = MIN_NPC_ANIMATION;
	
	private L2Skill _skill;
	private ScheduledFuture<?> _aiTask;
	
	@Override
	public void run()
	{
		if (getKnownList().getKnownPlayers().size() > 0)
		{
			if (_skill != null)
			{
				for (L2PcInstance player : getKnownList().getKnownPlayersInRadius(_skill.getSkillRadius()))
				{
					if (player.isInvul())
						continue;
					if (player.isMovementDisabled())
						continue;
					if (player.getPkKills() > 5)
						continue;
					if (player.getCurrentHp() < player.getMaxHp()
					 || player.getCurrentCp() < player.getMaxCp()
					 || player.getCurrentMp() < player.getMaxMp())
					{
						handleEffect(player);
					}
				}
			}
			randomAnimation(Rnd.get(1, 3));
		}
	}
	
	private void handleEffect(L2PcInstance player)
	{
		if (! doesStack(player, _skill))
			_skill.getEffects(player, player);
	}
	
	private boolean doesStack(L2PcInstance player, L2Skill checkSkill)
	{
		String stackType = checkSkill.getEffectTemplates()[0].abnormalType;
		for (L2Effect e : player.getAllEffects())
			if (stackType.equals(e.getAbnormalType()))
				return true;
		return false;
	}
	
	/**
	 * Send a packet SocialAction to all L2PcInstance in the _KnownPlayers of the L2NpcInstance.<BR><BR>
	 */
	private void randomAnimation(int animationId)
	{
		// Send a packet SocialAction to all L2PcInstance in the _KnownPlayers of the L2NpcInstance
		long now = System.currentTimeMillis();
		if (now - _lastSocialBroadcast > _minimalSocialInterval)
		{
			_lastSocialBroadcast = now;
			_minimalSocialInterval = Rnd.get(MIN_NPC_ANIMATION, MAX_NPC_ANIMATION);
			broadcastPacket(new SocialAction(L2KotatsuInstance.this, animationId));
		}
	}
	
	public L2KotatsuInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	//	setInstanceType(InstanceType.L2KotatsuInstance);
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		setShowSummonAnimation(false);
		
		if (ZoneManager.getInstance().getZone(this, L2TownZone.class) == null)
		{
			_skill = SkillTable.getInstance().getInfo(22118, 1);
			if (_skill.getSkillType() == L2SkillType.NOTDONE) throw new RuntimeException();
		}
		_lastSocialBroadcast = System.currentTimeMillis();
		_aiTask = ThreadPoolManager.getInstance().scheduleGeneralWithFixedDelay(this, 3000, 3000);
	}
	
	@Override
	public void deleteMe()
	{
		if (_aiTask != null)
		{
			_aiTask.cancel(true);
			_aiTask = null;
		}
		_skill = null;
		
		super.deleteMe();
	}
}
