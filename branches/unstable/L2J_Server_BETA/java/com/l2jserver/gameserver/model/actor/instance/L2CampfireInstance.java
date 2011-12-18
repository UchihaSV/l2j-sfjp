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
import com.l2jserver.gameserver.model.L2Effect;
import com.l2jserver.gameserver.model.L2Skill;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.zone.type.L2TownZone;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;
import com.l2jserver.gameserver.templates.skills.L2SkillType;

/**
 * @author JOJO
 * 
 * NPC          148     ������ (type: L2Campfire)
 * |�A�C�e��    20868   �����Ηp�̉ΑŐ� - ��30����������ԂŎg���ƕ����΂��������܂��B�������苗���ȏ㗣�ꂽ�ꏊ�ŕ����΂��������Ɖ񕜃{�[�i�X���ʂ���������܂��B�퓬�n��ł͉񕜗͂��オ��܂��B�g���[�h�A�h���b�v�A�j��͂ł��܂���B�l�q�ɂւ̕ۊǂ͂ł��܂��B
 * |            1864    ��(30)
 * |�����X�L��  22305-1 �����Ώ���
 * |BUF�X�L��   22304-1 �����΂̉����� - �����΂̉����肪�������܂��BHP�񕜗͂�80%�ACP�񕜗͂�60%�AMP�񕜗͂�40���������܂��B
 */
public class L2CampfireInstance extends L2Npc implements Runnable
{
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
	
	public L2CampfireInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	//	setInstanceType(InstanceType.L2CampfireInstance);
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		setShowSummonAnimation(false);
		
		if (ZoneManager.getInstance().getZone(this, L2TownZone.class) == null
	  /* && ZoneManager.getInstance().getZone(this, L2****Zone.class) != null*/) //TODO: "�퓬�n��ł͉񕜗͂��オ��܂��B"
		{
			_skill = SkillTable.getInstance().getInfo(22304, 1);
			if (_skill.getSkillType() == L2SkillType.NOTDONE) throw new RuntimeException();
			
			_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(this, 3000, 3000);
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
		_skill = null;
		
		super.deleteMe();
	}
}
