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
package com.l2jserver.gameserver.model.actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.CtrlEvent;
import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.ai.L2AttackableAI;
import com.l2jserver.gameserver.ai.L2CharacterAI;
import com.l2jserver.gameserver.ai.L2FortSiegeGuardAI;
import com.l2jserver.gameserver.ai.L2SiegeGuardAI;
import com.l2jserver.gameserver.datatables.EventDroplist;
import com.l2jserver.gameserver.datatables.EventDroplist.DateDrop;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.datatables.ManorData;
import com.l2jserver.gameserver.enums.AIType;
import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.enums.QuestEventType;
import com.l2jserver.gameserver.instancemanager.CursedWeaponsManager;
import com.l2jserver.gameserver.instancemanager.WalkingManager;
import com.l2jserver.gameserver.model.AbsorberInfo;
import com.l2jserver.gameserver.model.AggroInfo;
import com.l2jserver.gameserver.model.DamageDoneInfo;
import com.l2jserver.gameserver.model.L2CommandChannel;
import com.l2jserver.gameserver.model.L2DropData;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Party;
import com.l2jserver.gameserver.model.actor.events.AttackableEvents;
import com.l2jserver.gameserver.model.actor.instance.L2GrandBossInstance;
import com.l2jserver.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.instance.L2ServitorInstance;
import com.l2jserver.gameserver.model.actor.knownlist.AttackableKnownList;
import com.l2jserver.gameserver.model.actor.status.AttackableStatus;
import com.l2jserver.gameserver.model.actor.tasks.attackable.CommandChannelTimer;
import com.l2jserver.gameserver.model.actor.tasks.attackable.OnKillNotifyTask;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.drops.DropListScope;
import com.l2jserver.gameserver.model.holders.ItemHolder;
import com.l2jserver.gameserver.model.itemcontainer.Inventory;
import com.l2jserver.gameserver.model.items.L2Item;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.stats.MoveType;
import com.l2jserver.gameserver.model.stats.Stats;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.CreatureSay;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.gameserver.taskmanager.DecayTaskManager;
import com.l2jserver.gameserver.util.Util;
import com.l2jserver.util.Rnd;

public class L2Attackable extends L2Npc
{
	private boolean _isRaid = false;
	private boolean _isRaidMinion = false;
	private boolean _champion = false;
	private final Map<L2Character, AggroInfo> _aggroList = new ConcurrentHashMap<>();
	private boolean _isReturningToSpawnPoint = false;
	private boolean _canReturnToSpawnPoint = true;
	private boolean _seeThroughSilentMove = false;
	private ItemHolder[] _sweepItems;
	private ItemHolder _harvestItem;	//[JOJO] -[]
	private boolean _seeded;
	private int _seedType = 0;
	private int _seederObjId = 0;
	
	private boolean _overhit;
	
	private double _overhitDamage;
	
	private L2Character _overhitAttacker;
	
	private volatile L2CommandChannel _firstCommandChannelAttacked = null;
	private CommandChannelTimer _commandChannelTimer = null;
	private long _commandChannelLastAttack = 0;
	
	private boolean _absorbed;
	
	private final Map<Integer, AbsorberInfo> _absorbersList = new ConcurrentHashMap<>();
	
	private boolean _mustGiveExpSp;
	
	/** True if a Dwarf has used Spoil on this L2NpcInstance */
	private boolean _isSpoil = false;
	
	private int _isSpoiledBy = 0;
	
	protected int _onKillDelay = 5000;
	
	/**
	 * Constructor of L2Attackable (use L2Character and L2NpcInstance constructor).<br>
	 * Actions:<br>
	 * Call the L2Character constructor to set the _template of the L2Attackable (copy skills from template to object and link _calculators to NPC_STD_CALCULATOR)<br>
	 * Set the name of the L2Attackable<br>
	 * Create a RandomAnimation Task that will be launched after the calculated delay if the server allow it.
	 * @param objectId identifier of the object initialized.
	 * @param template the template to apply to the NPC.
	 */
	public L2Attackable(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2Attackable);
		setIsInvul(false);
		_mustGiveExpSp = true;
if (com.l2jserver.Config.NEVER_RandomWalk_IF_CORPSE) {{
		if (!template.canMove()
		 || template.getAIType() == AIType.CORPSE
		 || template.getBaseMoveSpeed(MoveType.WALK) <= 1f) {
			if (com.l2jserver.Config.NEVER_RandomWalk_IF_CORPSE_LOG) if (!isNoRndWalk()) System.out.println("__BASENAME__:__LINE__:(NonRandomWalkMonsters) canMove=" + template.canMove() + " baseWalkSpd=" + template.getBaseMoveSpeed(MoveType.WALK) + " baseRunSpd=" + template.getBaseMoveSpeed(MoveType.RUN) + " " + template.getRace().name() + " " + template.getAIType().name() + " " + template.getType() + " " + template.getNpcId() + " " + com.l2jserver.util.Util.concat_ws(" ", template.getTitle(), template.getName()));
			setIsNoRndWalk(true);
		}
}}
	}
	
	@Override
	public AttackableKnownList getKnownList()
	{
		return (AttackableKnownList) super.getKnownList();
	}
	
	@Override
	public void initKnownList()
	{
		setKnownList(new AttackableKnownList(this));
	}
	
	@Override
	public AttackableStatus getStatus()
	{
		return (AttackableStatus) super.getStatus();
	}
	
	@Override
	public void initCharStatus()
	{
		setStatus(new AttackableStatus(this));
	}
	
	@Override
	public void initCharEvents()
	{
		setCharEvents(new AttackableEvents(this));
	}
	
	@Override
	public AttackableEvents getEvents()
	{
		return (AttackableEvents) super.getEvents();
	}
	
	/**
	 * Return the L2Character AI of the L2Attackable and if its null create a new one.
	 */
	@Override
	public L2CharacterAI getAI()
	{
		L2CharacterAI ai = _ai; // copy handle
		if (ai == null)
		{
			synchronized (this)
			{
				if (_ai == null)
				{
					_ai = new L2AttackableAI(new AIAccessor());
				}
				return _ai;
			}
		}
		return ai;
	}
	
	public final Map<L2Character, AggroInfo> getAggroList()
	{
		return _aggroList;
	}
	
	public final boolean isReturningToSpawnPoint()
	{
		return _isReturningToSpawnPoint;
	}
	
	public final void setisReturningToSpawnPoint(boolean value)
	{
		_isReturningToSpawnPoint = value;
	}
	
	public final boolean canReturnToSpawnPoint()
	{
		return _canReturnToSpawnPoint;
	}
	
	public final void setCanReturnToSpawnPoint(boolean value)
	{
		_canReturnToSpawnPoint = value;
	}
	
	public boolean canSeeThroughSilentMove()
	{
		return _seeThroughSilentMove;
	}
	
	public void setSeeThroughSilentMove(boolean val)
	{
		_seeThroughSilentMove = val;
	}
	
	/**
	 * Use the skill if minimum checks are pass.
	 * @param skill the skill
	 */
	public void useMagic(L2Skill skill)
	{
		if ((skill == null) || isAlikeDead() || skill.isPassive() || isCastingNow() || isSkillDisabled(skill))
		{
			return;
		}
		
		if ((getCurrentMp() < (getStat().getMpConsume(skill) + getStat().getMpInitialConsume(skill))) || (getCurrentHp() <= skill.getHpConsume()))
		{
			return;
		}
		
		if (!skill.isStatic())
		{
			if (skill.isMagic())
			{
				if (isMuted())
				{
					return;
				}
			}
			else
			{
				if (isPhysicalMuted())
				{
					return;
				}
			}
		}
		
		final L2Object target = skill.getFirstOfTargetList(this);
		if (target != null)
		{
			getAI().setIntention(CtrlIntention.AI_INTENTION_CAST, skill, target);
		}
	}
	
	/**
	 * Reduce the current HP of the L2Attackable.
	 * @param damage The HP decrease value
	 * @param attacker The L2Character who attacks
	 */
	@Override
	public void reduceCurrentHp(double damage, L2Character attacker, L2Skill skill)
	{
		reduceCurrentHp(damage, attacker, true, false, skill);
	}
	
	/**
	 * Reduce the current HP of the L2Attackable, update its _aggroList and launch the doDie Task if necessary.
	 * @param damage The HP decrease value
	 * @param attacker The L2Character who attacks
	 * @param awake The awake state (If True : stop sleeping)
	 * @param isDOT
	 * @param skill
	 */
	@Override
	public void reduceCurrentHp(double damage, L2Character attacker, boolean awake, boolean isDOT, L2Skill skill)
	{
		if (isRaid() && !isMinion() && (attacker != null) && (attacker.getParty() != null) && attacker.getParty().isInCommandChannel() && attacker.getParty().getCommandChannel().meetRaidWarCondition(this))
		{
			if (_firstCommandChannelAttacked == null) // looting right isn't set
			{
				synchronized (this)
				{
					if (_firstCommandChannelAttacked == null)
					{
						_firstCommandChannelAttacked = attacker.getParty().getCommandChannel();
						if (_firstCommandChannelAttacked != null)
						{
							_commandChannelTimer = new CommandChannelTimer(this);
							_commandChannelLastAttack = System.currentTimeMillis();
							ThreadPoolManager.getInstance().scheduleGeneral(_commandChannelTimer, 10000); // check for last attack
							_firstCommandChannelAttacked.broadcastPacket(new CreatureSay(0, Say2.PARTYROOM_ALL, "", "You have looting rights!")); // TODO: retail msg
						}
					}
				}
			}
			else if (attacker.getParty().getCommandChannel().equals(_firstCommandChannelAttacked)) // is in same channel
			{
				_commandChannelLastAttack = System.currentTimeMillis(); // update last attack time
			}
		}
		
		if (isEventMob())
		{
			return;
		}
		
		// Add damage and hate to the attacker AggroInfo of the L2Attackable _aggroList
		if (attacker != null)
		{
			addDamage(attacker, (int) damage, skill);
		}
		
		// If this L2Attackable is a L2MonsterInstance and it has spawned minions, call its minions to battle
		if (this instanceof L2MonsterInstance)
		{
			L2MonsterInstance master = (L2MonsterInstance) this;
			
			if (master.hasMinions())
			{
				master.getMinionList().onAssist(this, attacker);
			}
			
			master = master.getLeader();
			if ((master != null) && master.hasMinions())
			{
				master.getMinionList().onAssist(this, attacker);
			}
		}
		// Reduce the current HP of the L2Attackable and launch the doDie Task if necessary
		super.reduceCurrentHp(damage, attacker, awake, isDOT, skill);
	}
	
	public synchronized void setMustRewardExpSp(boolean value)
	{
		_mustGiveExpSp = value;
	}
	
	public synchronized boolean getMustRewardExpSP()
	{
		return _mustGiveExpSp;
	}
	
	/**
	 * Kill the L2Attackable (the corpse disappeared after 7 seconds), distribute rewards (EXP, SP, Drops...) and notify Quest Engine.<br>
	 * Actions:<br>
	 * Distribute Exp and SP rewards to L2PcInstance (including Summon owner) that hit the L2Attackable and to their Party members<br>
	 * Notify the Quest Engine of the L2Attackable death if necessary.<br>
	 * Kill the L2NpcInstance (the corpse disappeared after 7 seconds)<br>
	 * Caution: This method DOESN'T GIVE rewards to L2PetInstance.
	 * @param killer The L2Character that has killed the L2Attackable
	 */
	@Override
	public boolean doDie(L2Character killer)
	{
		// Kill the L2NpcInstance (the corpse disappeared after 7 seconds)
		if (!super.doDie(killer))
		{
			return false;
		}
		
		// Notify the Quest Engine of the L2Attackable death if necessary
		try
		{
			L2PcInstance player = null;
			
			if (killer != null)
			{
				player = killer.getActingPlayer();
			}
			
			if (player != null)
			{
				List<Quest> eventQuests;
				if ((eventQuests = getTemplate().getEventQuests(QuestEventType.ON_KILL)) != null)
				{
if (com.l2jserver.Config.FIX_OnKillNotifyTask_THREAD) {{
					// TODO: L2QuestGuardInstance にもON_KILL処理があるが...
					ThreadPoolManager.getInstance().scheduleEffect(new OnKillNotifyTask(this, null, player, killer instanceof L2Summon), _onKillDelay);
}} else {{
					for (Quest quest : eventQuests)
					{
						ThreadPoolManager.getInstance().scheduleEffect(new OnKillNotifyTask(this, quest, player, (killer != null) && killer.isSummon()), _onKillDelay);
					}
}}
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "", e);
		}
		return true;
	}
	
	/**
	 * Distribute Exp and SP rewards to L2PcInstance (including Summon owner) that hit the L2Attackable and to their Party members.<br>
	 * Actions:<br>
	 * Get the L2PcInstance owner of the L2ServitorInstance (if necessary) and L2Party in progress.<br>
	 * Calculate the Experience and SP rewards in function of the level difference.<br>
	 * Add Exp and SP rewards to L2PcInstance (including Summon penalty) and to Party members in the known area of the last attacker.<br>
	 * Caution : This method DOESN'T GIVE rewards to L2PetInstance.
	 * @param lastAttacker The L2Character that has killed the L2Attackable
	 */
	@Override
	protected void calculateRewards(L2Character lastAttacker)
	{
		try
		{
			if (getAggroList().isEmpty())
			{
				return;
			}
			
			// Creates an empty list of rewards
			final FastMap<L2Character, DamageDoneInfo> rewards = new FastMap<>();	//[JOJO]
			// L2J本家 変更履歴
			// 6073  UnAfraid  2013/06/20  HashMap --> ConcurrentHashMap<>()
			// 6070  UnAfraid  2013/06/20  ConcurrentHashMap --> HashMap<>()
			// 5964  Zoey76    2013/05/04  FastMap --> ConcurrentHashMap<>()
			// 4057  JIV       2010/04/10  FastMap<L2PcInstance, RewardInfo>().shared()
			// 4     kadarl2j  2006/09/13  FastMap<L2PcInstance, RewardInfo>().setShared(true)
			
			L2PcInstance maxDealer = null;
			int maxDamage = 0;
			long totalDamage = 0;
			// While Iterating over This Map Removing Object is Not Allowed
			// Go through the _aggroList of the L2Attackable
			for (AggroInfo info : getAggroList().values())
			{
				if (info == null)
				{
					continue;
				}
				
				// Get the L2Character corresponding to this attacker
				final L2PcInstance attacker = info.getAttacker().getActingPlayer();
				if (attacker != null)
				{
					// Get damages done by this attacker
					final int damage = info.getDamage();
					
					// Prevent unwanted behavior
					if (damage > 1)
					{
						// Check if damage dealer isn't too far from this (killed monster)
						if (!Util.checkIfInRange(Config.ALT_PARTY_RANGE, this, attacker, true))
						{
							continue;
						}
						
						totalDamage += damage;
						
						// Calculate real damages (Summoners should get own damage plus summon's damage)
						DamageDoneInfo reward = rewards.get(attacker);
						if (reward == null)
						{
							reward = new DamageDoneInfo(attacker, damage);
							rewards.put(attacker, reward);
						}
						else
						{
							reward.addDamage(damage);
						}
						
						if (reward.getDamage() > maxDamage)
						{
							maxDealer = attacker;
							maxDamage = reward.getDamage();
						}
					}
				}
			}
			
			// Manage Base, Quests and Sweep drops of the L2Attackable
			doItemDrop((maxDealer != null) && maxDealer.isOnline() ? maxDealer : lastAttacker);
			
			// Manage drop of Special Events created by GM for a defined period
			doEventDrop(lastAttacker);
			
			if (!getMustRewardExpSP())
			{
				return;
			}
			
			if (!rewards.isEmpty())
			{
				for (DamageDoneInfo reward : rewards.values())
				{
					if (reward == null)
					{
						continue;
					}
					
					// Attacker to be rewarded
					final L2PcInstance attacker = reward.getAttacker();
					
					// Total amount of damage done
					final int damage = reward.getDamage();
					
					// Get party
					final L2Party attackerParty = attacker.getParty();
					
					// Penalty applied to the attacker's XP
					// If this attacker have servitor, get Exp Penalty applied for the servitor.
					final float penalty = attacker.hasServitor() ? ((L2ServitorInstance) attacker.getSummon()).getExpPenalty() : 0;
					
					// If there's NO party in progress
					if (attackerParty == null)
					{
						// Calculate Exp and SP rewards
						if (attacker.getKnownList().knowsObject(this))
						{
							// Calculate the difference of level between this attacker (player or servitor owner) and the L2Attackable
							// mob = 24, atk = 10, diff = -14 (full xp)
							// mob = 24, atk = 28, diff = 4 (some xp)
							// mob = 24, atk = 50, diff = 26 (no xp)
							final int levelDiff = attacker.getLevel() - getLevel();
							
							final int[] expSp = calculateExpAndSp(levelDiff, damage, totalDamage);
							long exp = expSp[0];
							int sp = expSp[1];
							
							if (Config.L2JMOD_CHAMPION_ENABLE && isChampion())
							{
								exp *= Config.L2JMOD_CHAMPION_REWARDS;
								sp *= Config.L2JMOD_CHAMPION_REWARDS;
							}
							
							exp *= 1 - penalty;
							
							// Check for an over-hit enabled strike
							L2Character overhitAttacker;
							if (isOverhit() && (overhitAttacker = getOverhitAttacker()) != null && overhitAttacker.getActingPlayer() == attacker)
							{
								attacker.sendPacket(SystemMessageId.OVER_HIT);
								exp += calculateOverhitExp(exp);
							}
							
							// Distribute the Exp and SP between the L2PcInstance and its L2Summon
							if (!attacker.isDead())
							{
								final long addexp = Math.round(attacker.calcStat(Stats.EXPSP_RATE, exp, null, null));
								final int addsp = (int) attacker.calcStat(Stats.EXPSP_RATE, sp, null, null);
								
								attacker.addExpAndSp(addexp, addsp, useVitalityRate());
								if (addexp > 0)
								{
									attacker.updateVitalityPoints(getVitalityPoints(damage), true, false);
								}
							}
						}
					}
					else
					{
						// share with party members
						int partyDmg = 0;
						float partyMul = 1;
						int partyLvl = 0;
						
						// Get all L2Character that can be rewarded in the party
						final List<L2PcInstance> rewardedMembers = new ArrayList<>();
						// Go through all L2PcInstance in the party
						final List<L2PcInstance> groupMembers = attackerParty.isInCommandChannel() ? attackerParty.getCommandChannel().getMembers() : attackerParty.getMembers();
						for (L2PcInstance partyPlayer : groupMembers)
						{
							if ((partyPlayer == null) || partyPlayer.isDead())
							{
								continue;
							}
							
							// Get the RewardInfo of this L2PcInstance from L2Attackable rewards
							// Delete the RewardInfo from the L2Attackable rewards
							final DamageDoneInfo reward2 = rewards.put(partyPlayer, null);
							
							if (Util.checkIfInRange(Config.ALT_PARTY_RANGE, this, partyPlayer, true))
							{
								// If the L2PcInstance is in the L2Attackable rewards add its damages to party damages
								if (reward2 != null)
									partyDmg += reward2.getDamage(); // Add L2PcInstance damages to party damages
								rewardedMembers.add(partyPlayer);
								
								if (partyPlayer.getLevel() > partyLvl)
								{
									if (attackerParty.isInCommandChannel())
									{
										partyLvl = attackerParty.getCommandChannel().getLevel();
									}
									else
									{
										partyLvl = partyPlayer.getLevel();
									}
								}
							}
						}
						
						// If the party didn't killed this L2Attackable alone
						if (partyDmg < totalDamage)
						{
							partyMul = ((float) partyDmg / totalDamage);
						}
						
						// Calculate the level difference between Party and L2Attackable
						final int levelDiff = partyLvl - getLevel();
						
						// Calculate Exp and SP rewards
						final int[] expSp = calculateExpAndSp(levelDiff, partyDmg, totalDamage);
						long exp = expSp[0];
						int sp = expSp[1];
						
						if (Config.L2JMOD_CHAMPION_ENABLE && isChampion())
						{
							exp *= Config.L2JMOD_CHAMPION_REWARDS;
							sp *= Config.L2JMOD_CHAMPION_REWARDS;
						}
						
						exp *= partyMul;
						sp *= partyMul;
						
						// Check for an over-hit enabled strike
						// (When in party, the over-hit exp bonus is given to the whole party and splitted proportionally through the party members)
						L2Character overhitAttacker;
						if (isOverhit() && (overhitAttacker = getOverhitAttacker()) != null && overhitAttacker.getActingPlayer() == attacker)
						{
							attacker.sendPacket(SystemMessageId.OVER_HIT);
							exp += calculateOverhitExp(exp);
						}
						
						// Distribute Experience and SP rewards to L2PcInstance Party members in the known area of the last attacker
						if (partyDmg > 0)
						{
							attackerParty.distributeXpAndSp(exp, sp, rewardedMembers, partyLvl, partyDmg, this);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, "", e);
		}
	}
	
	@Override
	public void addAttackerToAttackByList(L2Character player)
	{
		if ((player == null) || (player == this) || getAttackByList().contains(player))
		{
			return;
		}
		getAttackByList().add(player);
	}
	
	/**
	 * Add damage and hate to the attacker AggroInfo of the L2Attackable _aggroList.
	 * @param attacker The L2Character that gave damages to this L2Attackable
	 * @param damage The number of damages given by the attacker L2Character
	 * @param skill
	 */
	public void addDamage(L2Character attacker, int damage, L2Skill skill)
	{
		if (attacker == null)
		{
			return;
		}
		
		// Notify the L2Attackable AI with EVT_ATTACKED
		if (!isDead())
		{
			try
			{
				// If monster is on walk - stop it
				if (isWalker() && !isCoreAIDisabled() && WalkingManager.getInstance().isOnWalk(this))
				{
					WalkingManager.getInstance().stopMoving(this, false, true);
				}
				
				L2PcInstance player = attacker.getActingPlayer();
				if (player != null)
				{
					List<Quest> eventQuests;
					if ((eventQuests = getTemplate().getEventQuests(QuestEventType.ON_ATTACK)) != null)
					{
						for (Quest quest : eventQuests)
						{
							quest.notifyAttack(this, player, damage, attacker.isSummon(), skill);
						}
					}
				}
				// for now hard code damage hate caused by an L2Attackable
				else
				{
					getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, attacker);
					addDamageHate(attacker, damage, (damage * 100) / (getLevel() + 7));
				}
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "", e);
			}
		}
	}
	
	/**
	 * Add damage and hate to the attacker AggroInfo of the L2Attackable _aggroList.
	 * @param attacker The L2Character that gave damages to this L2Attackable
	 * @param damage The number of damages given by the attacker L2Character
	 * @param aggro The hate (=damage) given by the attacker L2Character
	 */
	public void addDamageHate(L2Character attacker, int damage, int aggro)
	{
		if (attacker == null)
		{
			return;
		}
		
		final L2PcInstance targetPlayer = attacker.getActingPlayer();
		// Get the AggroInfo of the attacker L2Character from the _aggroList of the L2Attackable
		AggroInfo ai = getAggroList().get(attacker);
		if (ai == null)
		{
			ai = new AggroInfo(attacker);
			getAggroList().put(attacker, ai);
		}
		ai.addDamage(damage);
		// traps does not cause aggro
		// making this hack because not possible to determine if damage made by trap
		// so just check for triggered trap here
		if ((targetPlayer == null) || (targetPlayer.getTrap() == null) || !targetPlayer.getTrap().isTriggered())
		{
			ai.addHate(aggro);
		}
		
		if ((targetPlayer != null) && (aggro == 0))
		{
			List<Quest> eventQuests;
			if ((eventQuests = getTemplate().getEventQuests(QuestEventType.ON_AGGRO_RANGE_ENTER)) != null)
			{
				for (Quest quest : eventQuests)
				{
					quest.notifyAggroRangeEnter(this, targetPlayer, attacker.isSummon());
				}
			}
		}
		else if ((targetPlayer == null) && (aggro == 0))
		{
			aggro = 1;
			ai.addHate(1);
		}
		
		// Set the intention to the L2Attackable to AI_INTENTION_ACTIVE
		if ((aggro != 0) && (getAI().getIntention() == CtrlIntention.AI_INTENTION_IDLE))
		{
			getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		}
	}
	
	public void reduceHate(L2Character target, int amount)
	{
		if ((getAI() instanceof L2SiegeGuardAI) || (getAI() instanceof L2FortSiegeGuardAI))
		{
			// TODO: this just prevents error until siege guards are handled properly
			stopHating(target);
			setTarget(null);
			getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
			return;
		}
		
		if (target == null) // whole aggrolist
		{
			L2Character mostHated = getMostHated();
			if (mostHated == null) // makes target passive for a moment more
			{
				((L2AttackableAI) getAI()).setGlobalAggro(-25);
				return;
			}
			
			for (AggroInfo ai : getAggroList().values())
			{
				if (ai == null)
				{
					return;
				}
				ai.addHate(amount);
			}
			
			amount = getHating(mostHated);
			if (amount >= 0)
			{
				((L2AttackableAI) getAI()).setGlobalAggro(-25);
				clearAggroList();
				getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
				setWalking();
			}
			return;
		}
		
		AggroInfo ai = getAggroList().get(target);
		if (ai == null)
		{
			_log.info("target " + target + " not present in aggro list of " + this);
			return;
		}
		
		ai.addHate(amount);
		if ((ai.getHate() >= 0) && (getMostHated() == null))
		{
			((L2AttackableAI) getAI()).setGlobalAggro(-25);
			clearAggroList();
			getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
			setWalking();
		}
	}
	
	/**
	 * Clears _aggroList hate of the L2Character without removing from the list.
	 * @param target
	 */
	public void stopHating(L2Character target)
	{
		if (target == null)
		{
			return;
		}
		AggroInfo ai = getAggroList().get(target);
		if (ai != null)
		{
			ai.stopHate();
		}
	}
	
	/**
	 * @return the most hated L2Character of the L2Attackable _aggroList.
	 */
	public L2Character getMostHated()
	{
		if (getAggroList().isEmpty() || isAlikeDead())
		{
			return null;
		}
		
		L2Character mostHated = null;
		int maxHate = 0;
		
		// While Interacting over This Map Removing Object is Not Allowed
		// Go through the aggroList of the L2Attackable
		for (AggroInfo ai : getAggroList().values())
		{
			if (ai == null)
			{
				continue;
			}
			
			if (ai.checkHate(this) > maxHate)
			{
				mostHated = ai.getAttacker();
				maxHate = ai.getHate();
			}
		}
		
		return mostHated;
	}
	
	/**
	 * @return the 2 most hated L2Character of the L2Attackable _aggroList.
	 */
	public List<L2Character> get2MostHated()
	{
		if (getAggroList().isEmpty() || isAlikeDead())
		{
			return null;
		}
		
		L2Character mostHated = null;
		L2Character secondMostHated = null;
		int maxHate = 0;
		List<L2Character> result = new ArrayList<>();
		
		// While iterating over this map removing objects is not allowed
		// Go through the aggroList of the L2Attackable
		for (AggroInfo ai : getAggroList().values())
		{
			if (ai == null)
			{
				continue;
			}
			
			if (ai.checkHate(this) > maxHate)
			{
				secondMostHated = mostHated;
				mostHated = ai.getAttacker();
				maxHate = ai.getHate();
			}
		}
		
		result.add(mostHated);
		
		if (getAttackByList().contains(secondMostHated))
		{
			result.add(secondMostHated);
		}
		else
		{
			result.add(null);
		}
		return result;
	}
	
	public List<L2Character> getHateList()
	{
		if (getAggroList().isEmpty() || isAlikeDead())
		{
			return null;
		}
		
		List<L2Character> result = new ArrayList<>();
		for (AggroInfo ai : getAggroList().values())
		{
			if (ai == null)
			{
				continue;
			}
			ai.checkHate(this);
			
			result.add(ai.getAttacker());
		}
		return result;
	}
	
	/**
	 * @param target The L2Character whose hate level must be returned
	 * @return the hate level of the L2Attackable against this L2Character contained in _aggroList.
	 */
	public int getHating(final L2Character target)
	{
		if (getAggroList().isEmpty() || (target == null))
		{
			return 0;
		}
		
		AggroInfo ai = getAggroList().get(target);
		
		if (ai == null)
		{
			return 0;
		}
		
		if (ai.getAttacker() instanceof L2PcInstance)
		{
			L2PcInstance act = (L2PcInstance) ai.getAttacker();
			if (act.getAppearance().getInvisible() || ai.getAttacker().isInvul() || act.isSpawnProtected())
			{
				// Remove Object Should Use This Method and Can be Blocked While Interacting
				getAggroList().remove(target);
				return 0;
			}
		}
		
		if (!ai.getAttacker().isVisible())
		{
			getAggroList().remove(target);
			return 0;
		}
		
		if (ai.getAttacker().isAlikeDead())
		{
			ai.stopHate();
			return 0;
		}
		return ai.getHate();
	}
	
	public void doItemDrop(L2Character mainDamageDealer)
	{
		doItemDrop(getTemplate(), mainDamageDealer);
	}
	
	/**
	 * Manage Base, Quests and Special Events drops of L2Attackable (called by calculateRewards).<br>
	 * Concept:<br>
	 * During a Special Event all L2Attackable can drop extra Items.<br>
	 * Those extra Items are defined in the table allNpcDateDrops of the EventDroplist.<br>
	 * Each Special Event has a start and end date to stop to drop extra Items automatically.<br>
	 * Actions:<br>
	 * Manage drop of Special Events created by GM for a defined period.<br>
	 * Get all possible drops of this L2Attackable from L2NpcTemplate and add it Quest drops.<br>
	 * For each possible drops (base + quests), calculate which one must be dropped (random).<br>
	 * Get each Item quantity dropped (random).<br>
	 * Create this or these L2ItemInstance corresponding to each Item Identifier dropped.<br>
	 * If the autoLoot mode is actif and if the L2Character that has killed the L2Attackable is a L2PcInstance, Give the item(s) to the L2PcInstance that has killed the L2Attackable.<br>
	 * If the autoLoot mode isn't actif or if the L2Character that has killed the L2Attackable is not a L2PcInstance, add this or these item(s) in the world as a visible object at the position where mob was last.
	 * @param npcTemplate
	 * @param mainDamageDealer
	 */
	public void doItemDrop(L2NpcTemplate npcTemplate, L2Character mainDamageDealer)
	{
		if (mainDamageDealer == null)
		{
			return;
		}
		
		L2PcInstance player = mainDamageDealer.getActingPlayer();
		
		// Don't drop anything if the last attacker or owner isn't L2PcInstance
		if (player == null)
		{
			return;
		}
		
		CursedWeaponsManager.getInstance().checkDrop(this, player);
		
		if (isSpoil())
		{
			List<ItemHolder> sweepItems = npcTemplate.calculateDrops(DropListScope.CORPSE, this, player);
			if ((sweepItems != null) && !sweepItems.isEmpty())
			{
				_sweepItems = sweepItems.toArray(new ItemHolder[sweepItems.size()]);
			}
		}
		
		List<ItemHolder> deathItems = npcTemplate.calculateDrops(DropListScope.DEATH, this, player);
		if (deathItems != null)
		{
			for (ItemHolder drop : deathItems)
			{
				L2Item item = ItemTable.getInstance().getTemplate(drop.getId());
				// Check if the autoLoot mode is active
				if (isFlying() || (!isRaid() && Config.AUTO_LOOT) || (isRaid() && Config.AUTO_LOOT_RAIDS) || (item.hasExImmediateEffect() && Config.AUTO_LOOT_HERBS))
				{
					player.doAutoLoot(this, drop); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				}
				else
				{
					dropItem(player, drop); // drop the item on the ground
				}
				
				// Broadcast message if RaidBoss was defeated
				if (isRaid() && !isRaidMinion())
				{
						if (item.getId() == Inventory.ADENA_ID)	//[JOJO]
						{
							broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_DIED_DROPPED_S2_ADENA)
								.addCharName(this)
								.addItemNumber(drop.getCount()));
						}
						else
						{
							broadcastPacket(SystemMessage.getSystemMessage(SystemMessageId.C1_DIED_DROPPED_S3_S2)
								.addCharName(this)
								.addItemName(item)
								.addItemNumber(drop.getCount()));
						}
				}
			}
		}
		
		// Apply Special Item drop with random(rnd) quantity(qty) for champions.
		if (Config.L2JMOD_CHAMPION_ENABLE && isChampion() && ((Config.L2JMOD_CHAMPION_REWARD_LOWER_LVL_ITEM_CHANCE > 0) || (Config.L2JMOD_CHAMPION_REWARD_HIGHER_LVL_ITEM_CHANCE > 0)))
		{
			int champqty = Rnd.get(Config.L2JMOD_CHAMPION_REWARD_QTY);
			ItemHolder item = new ItemHolder(Config.L2JMOD_CHAMPION_REWARD_ID, ++champqty);
			
			if ((player.getLevel() <= getLevel()) && (Rnd.get(100) < Config.L2JMOD_CHAMPION_REWARD_LOWER_LVL_ITEM_CHANCE))
			{
				if (Config.AUTO_LOOT || isFlying())
				{
					player.addItem("ChampionLoot", item.getId(), item.getCount(), this, true); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				}
				else
				{
					dropItem(player, item);
				}
			}
			else if ((player.getLevel() > getLevel()) && (Rnd.get(100) < Config.L2JMOD_CHAMPION_REWARD_HIGHER_LVL_ITEM_CHANCE))
			{
				if (Config.AUTO_LOOT || isFlying())
				{
					player.addItem("ChampionLoot", item.getId(), item.getCount(), this, true); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				}
				else
				{
					dropItem(player, item);
				}
			}
		}
	}
	
	/**
	 * Manage Special Events drops created by GM for a defined period.<br>
	 * Concept:<br>
	 * During a Special Event all L2Attackable can drop extra Items.<br>
	 * Those extra Items are defined in the table allNpcDateDrops of the EventDroplist.<br>
	 * Each Special Event has a start and end date to stop to drop extra Items automatically.<br>
	 * Actions: <I>If an extra drop must be generated</I><br>
	 * Get an Item Identifier (random) from the DateDrop Item table of this Event.<br>
	 * Get the Item quantity dropped (random).<br>
	 * Create this or these L2ItemInstance corresponding to this Item Identifier.<br>
	 * If the autoLoot mode is actif and if the L2Character that has killed the L2Attackable is a L2PcInstance, Give the item(s) to the L2PcInstance that has killed the L2Attackable<br>
	 * If the autoLoot mode isn't actif or if the L2Character that has killed the L2Attackable is not a L2PcInstance, add this or these item(s) in the world as a visible object at the position where mob was last
	 * @param lastAttacker The L2Character that has killed the L2Attackable
	 */
	public void doEventDrop(L2Character lastAttacker)
	{
		if (lastAttacker == null)
		{
			return;
		}
		
		L2PcInstance player = lastAttacker.getActingPlayer();
		
		// Don't drop anything if the last attacker or owner isn't L2PcInstance
		if (player == null)
		{
			return;
		}
		
		if ((player.getLevel() - getLevel()) > 9)
		{
			return;
		}
		
		// Go through DateDrop of EventDroplist allNpcDateDrops within the date range
		for (DateDrop drop : EventDroplist.getInstance().getAllDrops())
		{
			if (Rnd.get(L2DropData.MAX_CHANCE) < drop.getEventDrop().getDropChance())
			{
				final int itemId = drop.getEventDrop().getItemIdList()[Rnd.get(drop.getEventDrop().getItemIdList().length)];
				final long itemCount = Rnd.get(drop.getEventDrop().getMinCount(), drop.getEventDrop().getMaxCount());
				if (Config.AUTO_LOOT || isFlying())
				{
					player.doAutoLoot(this, itemId, itemCount); // Give the item(s) to the L2PcInstance that has killed the L2Attackable
				}
				else
				{
					dropItem(player, itemId, itemCount); // drop the item on the ground
				}
			}
		}
	}
	
	/**
	 * @return the active weapon of this L2Attackable (= null).
	 */
	public L2ItemInstance getActiveWeapon()
	{
		return null;
	}
	
	/**
	 * @param player The L2Character searched in the _aggroList of the L2Attackable
	 * @return True if the _aggroList of this L2Attackable contains the L2Character.
	 */
	public boolean containsTarget(L2Character player)
	{
		return getAggroList().containsKey(player);
	}
	
	/**
	 * Clear the _aggroList of the L2Attackable.
	 */
	public void clearAggroList()
	{
		getAggroList().clear();
		
		// clear overhit values
		_overhit = false;
		_overhitDamage = 0;
		_overhitAttacker = null;
	}
	
	/**
	 * @return {@code true} if there is a loot to sweep, {@code false} otherwise.
	 */
	@Override
	public boolean isSweepActive()
	{
		return _sweepItems != null;
	}
	
	/**
	 * @return a copy of dummy items for the spoil loot.
	 */
	public List<L2Item> getSpoilLootItems()
	{
		final List<L2Item> lootItems = new ArrayList<>();
		if (isSweepActive())
		{
			for (ItemHolder item : _sweepItems)
			{
				lootItems.add(ItemTable.getInstance().createDummyItem(item.getId()).getItem());
			}
		}
		return lootItems;
	}
	
	/**
	 * @return table containing all L2ItemInstance that can be spoiled.
	 */
	public synchronized ItemHolder[] takeSweep()
	{
		ItemHolder[] sweep = _sweepItems;
		_sweepItems = null;
		return sweep;
	}
	
	/**
	 * @return table containing all L2ItemInstance that can be harvested.
	 */
	public synchronized ItemHolder takeHarvest()
	{
		ItemHolder harvest = _harvestItem;
		_harvestItem = null;
		return harvest;
	}
	
	/**
	 * Check if the corpse is too old.
	 * @param attacker the player to validate
	 * @param time the time to check
	 * @param sendMessage if {@code true} will send a message of corpse too old
	 * @return {@code true} if the corpse is too old
	 */
	public boolean isOldCorpse(L2PcInstance attacker, int time, boolean sendMessage)
	{
		if (DecayTaskManager.getInstance().getTasks().containsKey(this) && ((System.currentTimeMillis() - DecayTaskManager.getInstance().getTasks().get(this)) > time))
		{
			if (sendMessage && (attacker != null))
			{
				attacker.sendPacket(SystemMessageId.CORPSE_TOO_OLD_SKILL_NOT_USED);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * @param sweeper the player to validate.
	 * @param sendMessage sendMessage if {@code true} will send a message of sweep not allowed.
	 * @return {@code true} if is the spoiler or is in the spoiler party.
	 */
	public boolean checkSpoilOwner(L2PcInstance sweeper, boolean sendMessage)
	{
		if ((sweeper.getObjectId() != getIsSpoiledBy()) && !sweeper.isInLooterParty(getIsSpoiledBy()))
		{
			if (sendMessage)
			{
				sweeper.sendPacket(SystemMessageId.SWEEP_NOT_ALLOWED);
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Set the over-hit flag on the L2Attackable.
	 * @param status The status of the over-hit flag
	 */
	public void overhitEnabled(boolean status)
	{
		_overhit = status;
	}
	
	/**
	 * Set the over-hit values like the attacker who did the strike and the amount of damage done by the skill.
	 * @param attacker The L2Character who hit on the L2Attackable using the over-hit enabled skill
	 * @param damage The amount of damage done by the over-hit enabled skill on the L2Attackable
	 */
	public void setOverhitValues(L2Character attacker, double damage)
	{
		// Calculate the over-hit damage
		// Ex: mob had 10 HP left, over-hit skill did 50 damage total, over-hit damage is 40
		double overhitDmg = damage - getCurrentHp();
		if (overhitDmg < 0)
		{
			// we didn't killed the mob with the over-hit strike. (it wasn't really an over-hit strike)
			// let's just clear all the over-hit related values
			overhitEnabled(false);
			_overhitDamage = 0;
			_overhitAttacker = null;
			return;
		}
		overhitEnabled(true);
		_overhitDamage = overhitDmg;
		_overhitAttacker = attacker;
	}
	
	/**
	 * Return the L2Character who hit on the L2Attackable using an over-hit enabled skill.
	 * @return L2Character attacker
	 */
	public L2Character getOverhitAttacker()
	{
		return _overhitAttacker;
	}
	
	/**
	 * Return the amount of damage done on the L2Attackable using an over-hit enabled skill.
	 * @return double damage
	 */
	public double getOverhitDamage()
	{
		return _overhitDamage;
	}
	
	/**
	 * @return True if the L2Attackable was hit by an over-hit enabled skill.
	 */
	public boolean isOverhit()
	{
		return _overhit;
	}
	
	/**
	 * Activate the absorbed soul condition on the L2Attackable.
	 */
	public void absorbSoul()
	{
		_absorbed = true;
	}
	
	/**
	 * @return True if the L2Attackable had his soul absorbed.
	 */
	public boolean isAbsorbed()
	{
		return _absorbed;
	}
	
	/**
	 * Adds an attacker that successfully absorbed the soul of this L2Attackable into the _absorbersList.
	 * @param attacker
	 */
	public void addAbsorber(L2PcInstance attacker)
	{
		// If we have no _absorbersList initiated, do it
		final AbsorberInfo ai = _absorbersList.get(attacker.getObjectId());
		
		// If the L2Character attacker isn't already in the _absorbersList of this L2Attackable, add it
		if (ai == null)
		{
			_absorbersList.put(attacker.getObjectId(), new AbsorberInfo(attacker.getObjectId(), getCurrentHp()));
		}
		else
		{
			ai.setAbsorbedHp(getCurrentHp());
		}
		
		// Set this L2Attackable as absorbed
		absorbSoul();
	}
	
	public void resetAbsorbList()
	{
		_absorbed = false;
		_absorbersList.clear();
	}
	
	public Map<Integer, AbsorberInfo> getAbsorbersList()
	{
		return _absorbersList;
	}
	
	/**
	 * Calculate the Experience and SP to distribute to attacker (L2PcInstance, L2ServitorInstance or L2Party) of the L2Attackable.
	 * @param diff The difference of level between attacker (L2PcInstance, L2ServitorInstance or L2Party) and the L2Attackable
	 * @param damage The damages given by the attacker (L2PcInstance, L2ServitorInstance or L2Party)
	 * @param totalDamage The total damage done
	 * @return
	 */
	private int[] calculateExpAndSp(int diff, int damage, long totalDamage)
	{
		double xp;
		double sp;
		
		if (diff < -5)
		{
			diff = -5; // makes possible to use ALT_GAME_EXPONENT configuration
		}
		
		xp = ((double) getExpReward() * damage) / totalDamage;
		if (Config.ALT_GAME_EXPONENT_XP != 0)
		{
			xp *= Math.pow(2., -diff / Config.ALT_GAME_EXPONENT_XP);
		}
		
		sp = ((double) getSpReward() * damage) / totalDamage;
		if (Config.ALT_GAME_EXPONENT_SP != 0)
		{
			sp *= Math.pow(2., -diff / Config.ALT_GAME_EXPONENT_SP);
		}
		
		if ((Config.ALT_GAME_EXPONENT_XP == 0) && (Config.ALT_GAME_EXPONENT_SP == 0))
		{
			if (diff > 5) // formula revised May 07
			{
				double pow = Math.pow((double) 5 / 6, diff - 5);
				xp = xp * pow;
				sp = sp * pow;
			}
			
			if (xp <= 0)
			{
				xp = 0;
				sp = 0;
			}
			else if (sp <= 0)
			{
				sp = 0;
			}
		}
		int[] tmp =
		{
			(int) xp,
			(int) sp
		};
		return tmp;
	}
	
	public long calculateOverhitExp(long normalExp)
	{
		// Get the percentage based on the total of extra (over-hit) damage done relative to the total (maximum) ammount of HP on the L2Attackable
		double overhitPercentage = ((getOverhitDamage() * 100) / getMaxHp());
		
		// Over-hit damage percentages are limited to 25% max
		if (overhitPercentage > 25)
		{
			overhitPercentage = 25;
		}
		
		// Get the overhit exp bonus according to the above over-hit damage percentage
		// (1/1 basis - 13% of over-hit damage, 13% of extra exp is given, and so on...)
		double overhitExp = ((overhitPercentage / 100) * normalExp);
		
		// Return the rounded ammount of exp points to be added to the player's normal exp reward
		long bonusOverhit = Math.round(overhitExp);
		return bonusOverhit;
	}
	
	/**
	 * Return True.
	 */
	@Override
	public boolean canBeAttacked()
	{
		return true;
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		// Clear mob spoil, seed
		setSpoil(false);
		// Clear all aggro char from list
		clearAggroList();
		// Clear Harvester Rewrard List
		_harvestItem = null;
		// Clear mod Seeded stat
		_seeded = false;
		_seedType = 0;
		_seederObjId = 0;
		// Clear overhit value
		overhitEnabled(false);
		
		_sweepItems = null;
		resetAbsorbList();
		
		setWalking();
		
		// check the region where this mob is, do not activate the AI if region is inactive.
		if (!isInActiveRegion())
		{
			if (hasAI())
			{
				getAI().stopAITask();
			}
		}
	}
	
	/**
	 * @return True if this L2NpcInstance has drops that can be sweeped.
	 */
	public boolean isSpoil()
	{
		return _isSpoil;
	}
	
	/**
	 * Set the spoil state of this L2NpcInstance.
	 * @param isSpoil
	 */
	public void setSpoil(boolean isSpoil)
	{
		_isSpoil = isSpoil;
	}
	
	public final int getIsSpoiledBy()
	{
		return _isSpoiledBy;
	}
	
	public final void setIsSpoiledBy(int value)
	{
		_isSpoiledBy = value;
	}
	
	/**
	 * Sets state of the mob to seeded. Paramets needed to be set before.
	 * @param seeder
	 */
	public void setSeeded(L2PcInstance seeder)
	{
		if ((_seedType != 0) && (_seederObjId == seeder.getObjectId()))
		{
			setSeeded(_seedType, seeder.getLevel());
		}
	}
	
	/**
	 * Sets the seed parameters, but not the seed state
	 * @param id - id of the seed
	 * @param seeder - player who is sowind the seed
	 */
	public void setSeeded(int id, L2PcInstance seeder)
	{
		if (!_seeded)
		{
			_seedType = id;
			_seederObjId = seeder.getObjectId();
		}
	}
	
	private void setSeeded(int id, int seederLvl)
	{
		_seeded = true;
		_seedType = id;
		int count = 1;
		
		for_skiils:
		for (L2Skill skill : getTemplate().getSkills().values())
		{
				switch (skill.getId())
				{
					case 4303: // Strong type x2
						count *= 2;
						break for_skiils;
					case 4304: // Strong type x3
						count *= 3;
						break for_skiils;
					case 4305: // Strong type x4
						count *= 4;
						break for_skiils;
					case 4306: // Strong type x5
						count *= 5;
						break for_skiils;
					case 4307: // Strong type x6
						count *= 6;
						break for_skiils;
					case 4308: // Strong type x7
						count *= 7;
						break for_skiils;
					case 4309: // Strong type x8
						count *= 8;
						break for_skiils;
					case 4310: // Strong type x9
						count *= 9;
						break for_skiils;
					//[JOJO]-------------------------------------------------
					case 4408:
						switch (skill.getLevel())
						{
						//	case  2:	// HPやや高め
						//		count *= TODO;
						//		break for_skiils;
						//	case  3:	// HPやや高め
						//		count *= TODO;
						//		break for_skiils;
						//	case  4:	// HP高め
						//		count *= TODO;
						//		break for_skiils;
						//	case  5:	// HP高め
						//		count *= TODO;
						//		break for_skiils;
						//	case  6:	// HPかなり高め
						//		count *= TODO;
						//		break for_skiils;
						//	case  7:	// HPかなり高め
						//		count *= TODO;
						//		break for_skiils;
							case 10:	// x2
								count *= 2;
								break for_skiils;
							case 11:	// x3
								count *= 3;
								break for_skiils;
							case 12:	// x4
								count *= 4;
								break for_skiils;
							case 13:	// x5
								count *= 5;
								break for_skiils;
							case 14:	// x6
								count *= 6;
								break for_skiils;
							case 15:	// x7
								count *= 7;
								break for_skiils;
							case 16:	// x8
								count *= 8;
								break for_skiils;
							case 17:	// x9
								count *= 9;
								break for_skiils;
							case 18:	// x10
								count *= 10;
								break for_skiils;
							case 19:	// x11
								count *= 11;
								break for_skiils;
							case 20:	// x12
								count *= 12;
								break for_skiils;
						}
						break for_skiils;
					//-------------------------------------------------------
				}
		}
		
		int diff = (getLevel() - (ManorData.getInstance().getSeedLevel(_seedType) - 5));
		
		// hi-lvl mobs bonus
		if (diff > 0)
		{
			count += diff;
		}
		
		_harvestItem = new ItemHolder(ManorData.getInstance().getCropType(_seedType), count * Config.RATE_DROP_MANOR);
	}
	
	public int getSeederId()
	{
		return _seederObjId;
	}
	
	public int getSeedType()
	{
		return _seedType;
	}
	
	public boolean isSeeded()
	{
		return _seeded;
	}
	
	/**
	 * Set delay for onKill() call, in ms Default: 5000 ms
	 * @param delay
	 */
	public final void setOnKillDelay(int delay)
	{
		_onKillDelay = delay;
	}
	
	public final int getOnKillDelay()
	{
		return _onKillDelay;
	}
	
	/**
	 * Check if the server allows Random Animation.
	 */
	// This is located here because L2Monster and L2FriendlyMob both extend this class. The other non-pc instances extend either L2NpcInstance or L2MonsterInstance.
	@Override
	public boolean hasRandomAnimation()
	{
		return ((Config.MAX_MONSTER_ANIMATION > 0) && isRandomAnimationEnabled() && !(this instanceof L2GrandBossInstance));
	}
	
	@Override
	public boolean isMob()
	{
		return true; // This means we use MAX_MONSTER_ANIMATION instead of MAX_NPC_ANIMATION
	}
	
	public void setCommandChannelTimer(CommandChannelTimer commandChannelTimer)
	{
		_commandChannelTimer = commandChannelTimer;
	}
	
	public CommandChannelTimer getCommandChannelTimer()
	{
		return _commandChannelTimer;
	}
	
	public L2CommandChannel getFirstCommandChannelAttacked()
	{
		return _firstCommandChannelAttacked;
	}
	
	public void setFirstCommandChannelAttacked(L2CommandChannel firstCommandChannelAttacked)
	{
		_firstCommandChannelAttacked = firstCommandChannelAttacked;
	}
	
	/**
	 * @return the _commandChannelLastAttack
	 */
	public long getCommandChannelLastAttack()
	{
		return _commandChannelLastAttack;
	}
	
	/**
	 * @param channelLastAttack the _commandChannelLastAttack to set
	 */
	public void setCommandChannelLastAttack(long channelLastAttack)
	{
		_commandChannelLastAttack = channelLastAttack;
	}
	
	public void returnHome()
	{
		clearAggroList();
		
		if (hasAI() && (getSpawn() != null))
		{
			getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, getSpawn().getLocation(this));
		}
	}
	
	/*
	 * Return vitality points decrease (if positive) or increase (if negative) based on damage. Maximum for damage = maxHp.
	 */
	public float getVitalityPoints(int damage)
	{
		// sanity check
		if (damage <= 0)
		{
			return 0;
		}
		
		final float divider = (getLevel() > 0) && (getExpReward() > 0) ? (getTemplate().getBaseHpMax() * 9 * getLevel() * getLevel()) / (100 * getExpReward()) : 0;
		if (divider == 0)
		{
			return 0;
		}
		
		// negative value - vitality will be consumed
		return -Math.min(damage, getMaxHp()) / divider;
	}
	
	/*
	 * True if vitality rate for exp and sp should be applied
	 */
	public boolean useVitalityRate()
	{
		if (isChampion() && !Config.L2JMOD_CHAMPION_ENABLE_VITALITY)
		{
			return false;
		}
		
		return true;
	}
	
	/** Return True if the L2Character is RaidBoss or his minion. */
	@Override
	public boolean isRaid()
	{
		return _isRaid;
	}
	
	/**
	 * Set this Npc as a Raid instance.
	 * @param isRaid
	 */
	public void setIsRaid(boolean isRaid)
	{
		_isRaid = isRaid;
	}
	
	/**
	 * Set this Npc as a Minion instance.
	 * @param val
	 */
	public void setIsRaidMinion(boolean val)
	{
		_isRaid = val;
		_isRaidMinion = val;
	}
	
	@Override
	public boolean isRaidMinion()
	{
		return _isRaidMinion;
	}
	
	@Override
	public boolean isMinion()
	{
		return getLeader() != null;
	}
	
	/**
	 * @return leader of this minion or null.
	 */
	public L2Attackable getLeader()
	{
		return null;
	}
	
	public void setChampion(boolean champ)
	{
		_champion = champ;
	}
	
	@Override
	public boolean isChampion()
	{
		return _champion;
	}
	
	@Override
	public boolean isAttackable()
	{
		return true;
	}
}
