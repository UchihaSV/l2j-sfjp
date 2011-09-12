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

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Skill;
import com.l2jserver.gameserver.model.zone.type.L2PeaceZone;
import com.l2jserver.gameserver.network.serverpackets.SocialAction;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;
import com.l2jserver.util.Rnd;

/**
 * @author JOJO
 * 
 * NPC          28      ������ (type: L2Kotatsu) T25 Freya
 * NPC          127     ������ (type: L2Kotatsu) T26 H5
 * |�A�C�e��    20868   ������
 * |�����X�L��  22127-1 �����Ȃ�������
 * |BUF�X�L��   22118-1 �����̂ʂ����� - �����̂ʂ����肪�������܂��BHP�񕜗͂�40%�ACP�񕜗͂�30%�AMP�񕜗͂�20%�������Ă����ԁB
 */
public class L2KotatsuInstance extends L2XmassTreeInstance /*extends L2Npc*/
{
	private static final int MIN_NPC_ANIMATION = 40000;
	private static final int MAX_NPC_ANIMATION = 120000;
	/** Time of last social packet broadcast*/
	private long _lastSocialBroadcast = 0;
	/** Minimum interval between social packets*/
	private int _minimalSocialInterval = MIN_NPC_ANIMATION;
	
	private class KotatsuAI extends XmassAI implements Runnable
	{
		protected KotatsuAI(L2KotatsuInstance caster, L2Skill skill)
		{
			super(caster, skill);
		}
		
		@Override
		public void run()
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
			if (getKnownList().getKnownPlayers().size() > 0)
				randomAnimation(Rnd.get(1, 3));
		}
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
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		setShowSummonAnimation(false);
		
		switch (getNpcId())
		{
		case 28:	/*T25 Freya*/
		case 127:	/*T26 H5*/
			if (ZoneManager.getInstance().getZone(this, L2PeaceZone.class) == null)
				_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new KotatsuAI(this, SkillTable.getInstance().getInfo(22118, 1)), 3000, 3000);
			break;
		}
	}
	
	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		return 90; //guess
	}
}
