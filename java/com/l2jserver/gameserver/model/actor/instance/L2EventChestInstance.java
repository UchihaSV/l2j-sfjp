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

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.skills.CommonSkill;
import com.l2jserver.gameserver.model.skills.Skill;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.AbstractNpcInfo;
import com.l2jserver.gameserver.network.serverpackets.L2GameServerPacket;
import com.l2jserver.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.util.Rnd;

/**
 * @author Gnacik
 */
public final class L2EventChestInstance extends L2EventMonsterInstance
{
	//[JOJO]-------------------------------------------------
	private static final int TRANSFORMATION_ID = 105;	//skill 2428 "Scroll of Transformation - Rabbit"
	private static final int BONUS_RATE = 20;
	boolean _bonus;
	
	Runnable _hideTask = null;
	
	/** Time of last social packet broadcast*/
	long _lastSocialBroadcast = 0;
	/** Minimum interval between social packets*/
	private static final int _minimalSocialInterval = 5000;
	
	static final int[] TRIGGERE_SAY = 
	{
		1600008,	//Did you see that Firecracker explode?	私を選んで！他のはクズよ！
		1600009,	//I am nothing.	いやいやっ！私にさわらないで！
	};
	
	private static final int[] RANDAM_SAY =
	{
		1600007,	//A relaxing feeling is moving through my stomach.	私の中のブツがウズいてるぅ。
		1600010,	//I am telling the truth.	私、見た目よりすごいんです！
		1600022,	//You will regret this.	後悔しますよ。
	};
	
	static final int[] HIDE_SAY =
	{
		1600004,	//Boo-hoo... I hate...	くすん．．．嫌い．．．
		1600005,	//See you later.	またね～っ！
	};
	
	void autoChat(int[] stringId, int type)
	{
		autoChat(L2EventChestInstance.this, stringId, type);
	}
	
	public static void autoChat(L2Npc npc, int[] stringId, int type)
	{
		NpcSay packet = new NpcSay(npc.getObjectId(), type, npc.getTemplate().getDisplayId(), stringId[Rnd.get(stringId.length)]);
		for (L2PcInstance player : npc.getKnownList().getKnownPlayers().values())
			if (player != null && player.getTransformationId() == TRANSFORMATION_ID)
				player.sendPacket(packet);
	}
	
	public boolean isTriggered() { return !isInvisible(); }
	protected void setTriggerd(boolean value) { setInvisible(!value); }
	public boolean hasBonus() { return _bonus; }
	//-------------------------------------------------------
	
	public L2EventChestInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		
		setTriggerd(false);
	//	setIsNoRndWalk(true);	//-[JOJO] --> onSpawn()
		disableCoreAI(true);
		
		eventSetDropOnGround(true);
		eventSetBlockOffensiveSkills(true);
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		getSpawn().setIsNoRndWalk(true);	//+[JOJO]
		setIsNoRndWalk(true);	//+[JOJO]
	}
	
	public void trigger()
	{
		if (isTriggered()) return;	//+[JOJO]
		setTriggerd(true);
		broadcastPacket(new AbstractNpcInfo.NpcInfo(this, null));
		//[JOJO]-------------------------------------------------
		_hideTask = new Runnable(){
			private int _sequenceId = 0;
			@Override
			public void run()
			{
				if (_hideTask != this)
					return;
				if (!isTriggered() || isDecayed() || isDead())
				{
					_hideTask = null;
					return;
				}
				switch (++_sequenceId)
				{
				case 1:
					_bonus = Rnd.get(100) <= BONUS_RATE;
					if (_bonus)
					{
						L2EventChestInstance me = L2EventChestInstance.this;
						Skill firework = CommonSkill.FIREWORK.getSkill();
						broadcastPacket(new MagicSkillUse(me, me, firework.getId(), firework.getLevel(), firework.getHitTime(), firework.getReuseDelay()));
					}
					autoChat(TRIGGERE_SAY, Say2.ALL);
					_lastSocialBroadcast = System.currentTimeMillis();
					startRandomAnimationTimer();
					ThreadPoolManager.getInstance().scheduleGeneral(this, Rnd.get(15000, 20000)); // 15sec.
					return;
				case 2:
					stopRandomAnimationTimer();
					autoChat(HIDE_SAY, Say2.ALL);
					ThreadPoolManager.getInstance().scheduleGeneral(this, 1000);
					return;
				case 3:
					decayMe();
					setTriggerd(false);
					spawnMe();
					_hideTask = null;
					return;
				}
			}
		};
		ThreadPoolManager.getInstance().executeGeneral(_hideTask);
		//-------------------------------------------------------
	}
	
	//[JOJO]-------------------------------------------------
	@Override
	public void onRandomAnimation(int animationId)
	{
		if (!isTriggered() || isDecayed() || isDead())
			stopRandomAnimationTimer();
		else
		{
			// Send a packet SocialAction to all L2PcInstance in the _KnownPlayers of the L2NpcInstance
			long now = System.currentTimeMillis();
			if (now - _lastSocialBroadcast > _minimalSocialInterval)
			{
				_lastSocialBroadcast = now;
				autoChat(RANDAM_SAY, Say2.ALL);
			}
		}
	}
	
	@Override
	public boolean hasRandomAnimation()
	{
		return isTriggered();
	}
	
	public void stopRandomAnimationTimer()
	{
		_rAniTask = null;
	}
	//-------------------------------------------------------
	
	@Override
	public void sendInfo(L2PcInstance activeChar)
	{
		if (isTriggered())
		{
			activeChar.sendPacket(new AbstractNpcInfo.NpcInfo(this, activeChar));
		}
	}
	
	@Override
	public void broadcastPacket(L2GameServerPacket mov)
	{
		if (isTriggered())
		{
			super.broadcastPacket(mov);
		}
	}
	
	@Override
	public void broadcastPacket(L2GameServerPacket mov, int radiusInKnownlist)
	{
		if (isTriggered())
		{
			super.broadcastPacket(mov, radiusInKnownlist);
		}
	}
	
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return false;
	}
	
	@Override
	public void reduceHate(L2Character target, int amount)
	{
		// Do nothing.
	}
	
	@Override
	public boolean isAttackable()
	{
		return false;
	}
}