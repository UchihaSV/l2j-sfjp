/*
 * Copyright (C) 2004-2014 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.model.actor.instance;

import static com.l2jserver.gameserver.instancemanager.HandysBlockChecker.*;

import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.instancemanager.HandysBlockCheckerManager;
import com.l2jserver.gameserver.model.ArenaParticipantsHolder;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.entity.BlockCheckerEngine;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.AbstractNpcInfo;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.network.serverpackets.ExCubeGameChangePoints;
import com.l2jserver.gameserver.network.serverpackets.ExCubeGameExtendedChangePoints;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.util.Rnd;

/**
 * @author BiggBoss
 */
public class L2BlockInstance extends L2MonsterInstance
{
	private int _colorEffect;
	
	/**
	 * @param objectId
	 * @param template
	 */
	public L2BlockInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}
	
	/**
	 * Will change the color of the block and update the appearance in the known players clients
	 * @param attacker
	 * @param holder
	 * @param team
	 */
	private final void changeColor(L2PcInstance attacker, ArenaParticipantsHolder holder, int team, int color)
	{
		// Do not update color while sending old info
		synchronized (this)
		{
			if (_colorEffect != color)
			{
				// Change color
				_colorEffect = color;
				// BroadCast to all known players
				this.broadcastPacket(new AbstractNpcInfo.NpcInfo(this, attacker));
				increaseTeamPointsAndSend(attacker, team, holder.getEvent());
			}
			// 30% chance to drop the event items
			int random = Rnd.get(100);
			// Bond
			if ((random -= 15)  < 0)	// 15%
			{
				dropItem(13787, holder.getEvent(), attacker);	// �ڒ���
			}
			else if ((random -= 15)  < 0)	// 15%
			{
				dropItem(13788, holder.getEvent(), attacker);	// �n��
			}
		}
	}
	
	/*
	 * �X�L�� 5852-1 �u���b�N�Ԃ�	(�u���b�N���Ђ�����Ԃ��B)
	 * �X�L�� 5853-1 �u���b�N�Ԃ�	(�u���b�N���Ђ�����Ԃ��B)
	 * �A�C�e�� 13787 �ڒ��� �� �X�L�� 2616-1 �ڒ��Œ� abnormalType=INVINCIBILITY ... setIsInvul(true)
	 * �A�C�e�� 13788 �n��   �� �X�L�� 2617-1 �n������ ���� �X�L�� 5848-1 �u���b�N �g���K�[ �X���[ �� 5849-1 �ړ����x�̒ቺ
	 *                                                 ���� �X�L�� 5850-1 �u���b�N �g���K�[�X�^��  �� 5851-1 �V���b�N
	 */
	public final void blockCheckerSkill(L2PcInstance caster, L2Skill skill)	//+[JOJO] Dummy.java#useBlockCheckerSkill() ������z��
	{
		final int arena = caster.getBlockCheckerArena();
		if (arena == ARENA_NONE)
		{
			return;
		}
		final ArenaParticipantsHolder holder = HandysBlockCheckerManager.getInstance().getHolder(arena);
		if (holder == null)
		{
			return;
		}
		if (isInvul())	// �X�L�� 2616-1 �ڒ��Œ�
		{
			caster.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.ATTACK_WAS_BLOCKED));
			return;
		}
		
		switch (skill.getId())
		{
			case 5852:	// �u���b�N�Ԃ�
			case 5853:	// �u���b�N�Ԃ�
				final int team = holder.getPlayerTeam(caster);
				if (team == TEAM_RED && _colorEffect != COLOR_EFFECT_RED)
				{
					changeColor(caster, holder, team, COLOR_EFFECT_RED);
				}
				else if (team == TEAM_BLUE && _colorEffect != COLOR_EFFECT_BLUE)
				{
					changeColor(caster, holder, team, COLOR_EFFECT_BLUE);
				}
				
				if (getKnownSkill(5848) != null)	// �u���b�N �g���K�[ �X���[
				{
					SkillTable.getInstance().getInfo(5849, 1).applyEffects(this, caster);	// �ړ����x�̒ቺ
				}
				if (getKnownSkill(5850) != null)	// �u���b�N �g���K�[�X�^��
				{
					SkillTable.getInstance().getInfo(5851, 1).applyEffects(this, caster);	// �V���b�N
				}
				break;
				
			case 2616:	// �ڒ��Œ�
				skill.applyEffects(caster, this);
			//	if (getEffectList().getBuffInfoBySkillId(skill.getId()) == null)
			//		caster.sendPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_RESISTED_YOUR_S2)
			//			.addCharName(this)
			//			.addSkillName(skill));
			//	else
			//		caster.sendMessage(getName() + "��" + skill.getName() + "�ɂ�����܂����B");	//��
				break;
				
			case 2617:	// �n������
				if (!(getKnownSkill(5848) != null || getKnownSkill(5850) != null))
				{
					L2Skill s = SkillTable.getInstance().getInfo(Rnd.nextBoolean() ? 5848 : 5850, 1);	// ? �u���b�N �g���K�[ �X���[ : �u���b�N �g���K�[�X�^��
					addSkill(s);
			//		caster.sendMessage(getName() + "��" + skill.getName() + "�ɂ�����܂����B");	//��
				}
				break;
		}
	}
	
	/**
	 * Sets if the block is red or blue. Mainly used in block spawn
	 * @param isRed
	 */
	public void setRed()
	{
		_colorEffect = COLOR_EFFECT_RED;
	}
	public void setBlue()
	{
		_colorEffect = COLOR_EFFECT_BLUE;
	}
	
	/**
	 * @return {@code true} if the block is red at this moment, {@code false} otherwise
	 */
	@Override
	public int getColorEffect()
	{
		return _colorEffect;
	}
	
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		if (attacker instanceof L2PcInstance)
		{
			return (attacker.getActingPlayer() != null) && (attacker.getActingPlayer().getBlockCheckerArena() != ARENA_NONE);
		}
		return true;
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		return false;
	}
	
	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		if (!canTarget(player))
		{
			return;
		}
		
		player.setLastFolkNPC(this);
		
		if (player.getTarget() != this)
		{
			player.setTarget(this);
			getAI(); // wake up ai
		}
		else if (interact)
		{
			player.sendPacket(ActionFailed.STATIC_PACKET);
		}
	}
	
	private void increaseTeamPointsAndSend(L2PcInstance player, int team, BlockCheckerEngine eng)
	{
		eng.increasePlayerPoints(player, team);
		
		int timeLeft = (int) ((eng.getStarterTime() - System.currentTimeMillis()) / 1000);
		
		ExCubeGameChangePoints changePoints = new ExCubeGameChangePoints(timeLeft, eng.getBluePoints(), eng.getRedPoints());
		ExCubeGameExtendedChangePoints secretPoints = new ExCubeGameExtendedChangePoints(timeLeft, eng.getBluePoints(), eng.getRedPoints(), team, player, eng.getPlayerPoints(player, team));
		
		eng.getHolder().broadCastPacketToTeam(changePoints);
		eng.getHolder().broadCastPacketToTeam(secretPoints);
	}
	
	private void dropItem(int id, BlockCheckerEngine eng, L2PcInstance player)
	{
		L2ItemInstance drop = ItemTable.getInstance().createItem("Loot", id, 1, player, this);
		int x = getX() + Rnd.get(50);
		int y = getY() + Rnd.get(50);
		int z = getZ();
		
		drop.dropMe(this, x, y, z);
		
		eng.addNewDrop(drop);
	}
}
