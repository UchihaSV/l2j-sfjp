/*
 * Copyright (C) 2004-2013 L2J Server
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

import java.util.Collection;
import java.util.concurrent.Future;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.instancemanager.FourSepulchersManager;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.quest.QuestState;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.network.serverpackets.AbstractNpcInfo.NpcInfo;
import com.l2jserver.gameserver.network.serverpackets.MagicSkillUse;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.util.Rnd;

/**
 * @author sandman
 */
public class L2SepulcherMonsterInstance extends L2MonsterInstance
{
	@Deprecated static final boolean DEBUG = false;
	static final long TIME_VICTIM_SPAWN_KEYBOX = 300000;
	public int mysteriousBoxId = 0;
	boolean victimKeyBoxSpawned;
	L2Spawn _spawn2;
	
	protected Future<?> _victimSpawnKeyBoxTask = null;
	protected Future<?> _victimShout = null;
	protected Future<?> _changeImmortalTask = null;
	protected Future<?> _onDeadEventTask = null;
	
	public L2SepulcherMonsterInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2SepulcherMonsterInstance);
		setShowSummonAnimation(true);
		switch (template.getId())
		{
			case 25339:
			case 25342:
			case 25346:
			case 25349:
				setIsRaid(true);
		}
	}
	
	@Override
	public void onSpawn()
	{
		setShowSummonAnimation(false);
		if (DEBUG) switch (getId())
		{
			case 18120:
			case 18121:
			case 18122:
			case 18123:
			case 18124:
			case 18125:
			case 18126:
			case 18127:
			case 18128:
			case 18129:
			case 18130:
			case 18131:
			case 18149:
			case 18158:
			case 18159:
			case 18160:
			case 18161:
			case 18162:
			case 18163:
			case 18164:
			case 18165:
			case 18183:
			case 18184:
			case 18212:
			case 18213:
			case 18214:
			case 18215:
			case 18216:
			case 18217:
			case 18218:
			case 18219:
				setTitle("����");  broadcastPacket(new NpcInfo(this, null));
		}
		switch (getId())
		{
			case 18150:
			case 18151:
			case 18152:
			case 18153:
			case 18154:
			case 18155:
			case 18156:
			case 18157:
				setRunning();
				if (_victimSpawnKeyBoxTask != null)
				{
					_victimSpawnKeyBoxTask.cancel(true);
				}
				_victimSpawnKeyBoxTask = ThreadPoolManager.getInstance().scheduleEffect(new VictimSpawnKeyBox(), TIME_VICTIM_SPAWN_KEYBOX);
				if (_victimShout != null)
				{
					_victimShout.cancel(true);
				}
				_victimShout = ThreadPoolManager.getInstance().scheduleEffect(new VictimShout(System.currentTimeMillis() + TIME_VICTIM_SPAWN_KEYBOX), 5000);
				break;
			case 18196:
			case 18197:
			case 18198:
			case 18199:
			case 18200:
			case 18201:
			case 18202:
			case 18203:
			case 18204:
			case 18205:
			case 18206:
			case 18207:
			case 18208:
			case 18209:
			case 18210:
			case 18211:
				break;
			
			case 18231:
			case 18232:
			case 18233:
			case 18234:
			case 18235:
			case 18236:
			case 18237:
			case 18238:
			case 18239:
			case 18240:
			case 18241:
			case 18242:
			case 18243:
				if (_changeImmortalTask != null)
				{
					_changeImmortalTask.cancel(true);
				}
				_changeImmortalTask = ThreadPoolManager.getInstance().scheduleEffect(new ChangeImmortal(this), 1600);
				
				break;
			case 18256:
				break;
			case 25339:
			case 25342:
			case 25346:
			case 25349:
				setIsRaid(true);
				break;
		}
		super.onSpawn();
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		
		switch (getId())
		{
			case 18120:
			case 18121:
			case 18122:
			case 18123:
			case 18124:
			case 18125:
			case 18126:
			case 18127:
			case 18128:
			case 18129:
			case 18130:
			case 18131:
			case 18149:
			case 18158:
			case 18159:
			case 18160:
			case 18161:
			case 18162:
			case 18163:
			case 18164:
			case 18165:
			case 18183:
			case 18184:
			case 18212:
			case 18213:
			case 18214:
			case 18215:
			case 18216:
			case 18217:
			case 18218:
			case 18219:
				if (_onDeadEventTask != null)
				{
					_onDeadEventTask.cancel(true);
				}
				_onDeadEventTask = ThreadPoolManager.getInstance().scheduleEffect(new OnDeadEvent(this), 3500);
				break;
			
			case 18150:
			case 18151:
			case 18152:
			case 18153:
			case 18154:
			case 18155:
			case 18156:
			case 18157:
				if (_victimSpawnKeyBoxTask != null)
				{
					_victimSpawnKeyBoxTask.cancel(true);
					_victimSpawnKeyBoxTask = null;
				}
				if (_victimShout != null)
				{
					_victimShout.cancel(true);
					_victimShout = null;
				}
				if (_onDeadEventTask != null)
				{
					_onDeadEventTask.cancel(true);
				}
				_onDeadEventTask = ThreadPoolManager.getInstance().scheduleEffect(new OnDeadEvent(this), 3500);
				break;
			
			case 18141:
			case 18142:
			case 18143:
			case 18144:
			case 18145:
			case 18146:
			case 18147:
			case 18148:
				if (FourSepulchersManager.getInstance().isViscountMobsAnnihilated(mysteriousBoxId))
				{
					if (_onDeadEventTask != null)
					{
						_onDeadEventTask.cancel(true);
					}
					_onDeadEventTask = ThreadPoolManager.getInstance().scheduleEffect(new OnDeadEvent(this), 3500);
				}
				break;
			
			case 18220:
			case 18221:
			case 18222:
			case 18223:
			case 18224:
			case 18225:
			case 18226:
			case 18227:
			case 18228:
			case 18229:
			case 18230:
			case 18231:
			case 18232:
			case 18233:
			case 18234:
			case 18235:
			case 18236:
			case 18237:
			case 18238:
			case 18239:
			case 18240:
				if (FourSepulchersManager.getInstance().isDukeMobsAnnihilated(mysteriousBoxId))
				{
					if (_onDeadEventTask != null)
					{
						_onDeadEventTask.cancel(true);
					}
					_onDeadEventTask = ThreadPoolManager.getInstance().scheduleEffect(new OnDeadEvent(this), 3500);
				}
				break;
			
			case 25339:
			case 25342:
			case 25346:
			case 25349:
				giveCup(killer);
				if (_onDeadEventTask != null)
				{
					_onDeadEventTask.cancel(true);
				}
				_onDeadEventTask = ThreadPoolManager.getInstance().scheduleEffect(new OnDeadEvent(this), 8500);
				break;
		}
		return true;
	}
	
	@Override
	public void deleteMe()
	{
		if (_victimSpawnKeyBoxTask != null)
		{
			_victimSpawnKeyBoxTask.cancel(true);
			_victimSpawnKeyBoxTask = null;
		}
		if (_onDeadEventTask != null)
		{
			_onDeadEventTask.cancel(true);
			_onDeadEventTask = null;
		}
		
		super.deleteMe();
	}
	
	private void giveCup(L2Character killer)
	{
		String questId = "620_FourGoblets";
		int cupId = 0;
		int oldBrooch = 7262;
		
		switch (getId())
		{
			case 25339:
				cupId = 7256;
				break;
			case 25342:
				cupId = 7257;
				break;
			case 25346:
				cupId = 7258;
				break;
			case 25349:
				cupId = 7259;
				break;
		}
		
		L2PcInstance player = killer.getActingPlayer();
		
		if (player == null)
		{
			return;
		}
		
		if (player.getParty() != null)
		{
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				QuestState qs = mem.getQuestState(questId);
				if ((qs != null) && (qs.isStarted() || qs.isCompleted()) && (mem.getInventory().getItemByItemId(oldBrooch) == null))
				{
					mem.addItem("Quest", cupId, 1, mem, true);
				}
			}
		}
		else
		{
			QuestState qs = player.getQuestState(questId);
			if ((qs != null) && (qs.isStarted() || qs.isCompleted()) && (player.getInventory().getItemByItemId(oldBrooch) == null))
			{
				player.addItem("Quest", cupId, 1, player, true);
			}
		}
	}
	
	private class VictimShout implements Runnable
	{
		long _victimSpawnKeyBoxTime;
		long _nextShoutTime;
		
		public VictimShout(long victimSpawnKeyBoxTime)
		{
			//assert "victim".equals(getFactionId());
			_victimSpawnKeyBoxTime = victimSpawnKeyBoxTime;
		}
		
		@Override
		public void run()
		{
		 try {
			if (isDead())
			{
				return;
			}
			
			if (!isVisible())
			{
				return;
			}
			
			long timeLeft = _victimSpawnKeyBoxTime - System.currentTimeMillis();
			if (timeLeft <= 0)
				return;
			
			if (getCurrentHp() - 777 > (double) getMaxHp() * timeLeft / TIME_VICTIM_SPAWN_KEYBOX)
			{
				L2SepulcherMonsterInstance attacker;
				if ((attacker = getClosestMonster()) != null)
					attacker.doAttack(L2SepulcherMonsterInstance.this);
			}
			
			if (_nextShoutTime < System.currentTimeMillis())
			{
				_nextShoutTime = System.currentTimeMillis() + Rnd.get(15000, 45000);
				L2PcInstance player;
				if ((player = getRandomPlayer(false)) != null)
				{
					broadcastPacket(new NpcSay(getObjectId(), 0, getId(), 1010483/*"$s1! Help me!!"*/).addPcName(player));
					getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, player);
					setCanReturnToSpawnPoint(false);
					setIsNoRndWalk(true);
					ThreadPoolManager.getInstance().scheduleEffect(new Runnable() {
						@Override
						public void run()
						{
							if (getAI().getIntention() == CtrlIntention.AI_INTENTION_INTERACT)
								getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
						}
					}, 5000);
				}
				else if ((player = getRandomPlayer(true)) != null)
				{
					broadcastPacket(new NpcSay(getObjectId(), 0, getId(), 1010483/*"$s1! Help me!!"*/).addPcName(player));
				}
				else
				{
					broadcastPacket(new NpcSay(getObjectId(), 0, getId(), 1010484/*"forgive me!!"*/));
				}
			}

			_victimShout = ThreadPoolManager.getInstance().scheduleEffect(this, Rnd.get(500, 5000));
		 } catch (Exception e) {e.printStackTrace();}
		}
	}
	
	L2PcInstance getRandomPlayer(boolean isBusy)	//[JOJO]
	{
		Collection<L2PcInstance> knowns = getKnownList().getKnownPlayers().values();
		L2PcInstance[] knownPlayers = new L2PcInstance[knowns.size()];
		int count = 0;
		for (L2PcInstance player : knowns)
		{
			if (player == null || player.isDead())
				continue;
			if (player.isInCombat() == isBusy)
				knownPlayers[count++] = player;
		}
		if (count > 0)
			return knownPlayers[Rnd.get(count)];
		else
			return null;
	}
	
	L2SepulcherMonsterInstance getClosestMonster()	//[JOJO]
	{
		L2SepulcherMonsterInstance attacker = null;
		long distanceSq = Long.MAX_VALUE;
		for (L2Object o : getKnownList().getKnownObjects().values())
		{
			if (o instanceof L2SepulcherMonsterInstance)
			{
				L2SepulcherMonsterInstance a = (L2SepulcherMonsterInstance) o;
				if (a.isAttackingDisabled()) continue;
				long d;
				if ((d = (long)getPlanDistanceSq(a)) < distanceSq)
				{
					distanceSq = d;
					attacker = a;
				}
			}
		}
		return attacker;
	}
	
	@Override protected void doAttack(L2Character target)	//[JOJO]
	{
		super.doAttack(target);
	}
	
	@Override
	public void addDamageHate(L2Character attacker, int damage, int aggro)	//[JOJO]
	{
		switch (getId())
		{
		case 18150:
		case 18151:
		case 18152:
		case 18153:
		case 18154:
		case 18155:
		case 18156:
		case 18157:
			// Don't add hate.
//��		if (attacker instanceof L2Attackable)
				return;
		}
		super.addDamageHate(attacker, damage, aggro);
	}
	
	private class VictimSpawnKeyBox implements Runnable
	{
		private long _effectPeriod;
		
		public VictimSpawnKeyBox()
		{
		}
		
		@Override
		public void run()
		{
			final L2SepulcherMonsterInstance activeChar = L2SepulcherMonsterInstance.this;
			if (_victimShout != null)
			{
				_victimShout.cancel(true);
				_victimShout = null;
			}
			if (isDead())
			{
				return;
			}
			
			if (!isVisible())
			{
				return;
			}
			
			if (! victimKeyBoxSpawned)
			{
				victimKeyBoxSpawned = true;
				FourSepulchersManager.getInstance().spawnKeyBox(activeChar);
				broadcastPacket(new NpcSay(getObjectId(), 0, getId(), 1000503/*"Many thanks for rescue me."*/));
				setWalking();
			}
			
			if (System.currentTimeMillis() > _effectPeriod)
			{
				_effectPeriod = System.currentTimeMillis() + 110000;
				for (L2PcInstance player : getKnownList().getKnownPlayers().values())
				{
					if (player == null || player.isDead()) continue;
					int skillId = Rnd.get(4384, 4387), skillLevel = 1;
					L2Skill skill = SkillTable.getInstance().getInfo(skillId, skillLevel);
					setTarget(player);
					doCast(skill);
					broadcastPacket(new MagicSkillUse(activeChar, player, skillId, skillLevel, 0, 0));
				}
			}
			
			long delay = isMoving() ? 10000 : 30000;
			L2PcInstance player;
			switch (getAI().getIntention())
			{
				case AI_INTENTION_ACTIVE:
					if ((player = getRandomPlayer(false)) != null) {
						setRunning();
						getAI().setIntention(CtrlIntention.AI_INTENTION_INTERACT, player);
						delay = 10000;
					}
					break;
				case AI_INTENTION_INTERACT:
					if ((player = getRandomPlayer(false)) != null) {
						setRunning();
						getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, player);
						delay = 10000;
					}
					break;
				case AI_INTENTION_FOLLOW:
					if (!isMoving() || getTarget() instanceof L2PcInstance && ((L2PcInstance) getTarget()).isInCombat())
					{
						getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
						delay = 60000;
					}
					break;
			}
			_victimSpawnKeyBoxTask = ThreadPoolManager.getInstance().scheduleEffect(this, delay);
		}
	}
	
	private static class OnDeadEvent implements Runnable
	{
		L2SepulcherMonsterInstance _activeChar;
		
		public OnDeadEvent(L2SepulcherMonsterInstance activeChar)
		{
			_activeChar = activeChar;
		}
		
		@Override
		public void run()
		{
			switch (_activeChar.getId())
			{
				case 18120:
				case 18121:
				case 18122:
				case 18123:
				case 18124:
				case 18125:
				case 18126:
				case 18127:
				case 18128:
				case 18129:
				case 18130:
				case 18131:
				case 18149:
				case 18158:
				case 18159:
				case 18160:
				case 18161:
				case 18162:
				case 18163:
				case 18164:
				case 18165:
				case 18183:
				case 18184:
				case 18212:
				case 18213:
				case 18214:
				case 18215:
				case 18216:
				case 18217:
				case 18218:
				case 18219:
					FourSepulchersManager.getInstance().spawnKeyBox(_activeChar);
					break;
				
				case 18150:
				case 18151:
				case 18152:
				case 18153:
				case 18154:
				case 18155:
				case 18156:
				case 18157:
					if (! _activeChar.victimKeyBoxSpawned)
					FourSepulchersManager.getInstance().spawnExecutionerOfHalisha(_activeChar);
					break;
				
				case 18141:
				case 18142:
				case 18143:
				case 18144:
				case 18145:
				case 18146:
				case 18147:
				case 18148:
					FourSepulchersManager.getInstance().spawnMonster(_activeChar.mysteriousBoxId);
					break;
				
				case 18220:
				case 18221:
				case 18222:
				case 18223:
				case 18224:
				case 18225:
				case 18226:
				case 18227:
				case 18228:
				case 18229:
				case 18230:
				case 18231:
				case 18232:
				case 18233:
				case 18234:
				case 18235:
				case 18236:
				case 18237:
				case 18238:
				case 18239:
				case 18240:
					FourSepulchersManager.getInstance().spawnArchonOfHalisha(_activeChar.mysteriousBoxId);
					break;
				
				case 25339:
				case 25342:
				case 25346:
				case 25349:
					FourSepulchersManager.getInstance().spawnEmperorsGraveNpc(_activeChar.mysteriousBoxId);
					break;
			}
		}
	}
	
	private static class ChangeImmortal implements Runnable
	{
		L2SepulcherMonsterInstance activeChar;
		
		public ChangeImmortal(L2SepulcherMonsterInstance mob)
		{
			activeChar = mob;
		}
		
		@Override
		public void run()
		{
			L2Skill fp = SkillTable.FrequentSkill.FAKE_PETRIFICATION.getSkill(); // Invulnerable by petrification
			fp.getEffects(activeChar, activeChar);
		}
	}
	
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return true;
	}
}
