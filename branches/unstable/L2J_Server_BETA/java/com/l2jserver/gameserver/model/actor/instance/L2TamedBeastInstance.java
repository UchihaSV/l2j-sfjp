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

import static com.l2jserver.gameserver.ai.CtrlIntention.*;
import static com.l2jserver.gameserver.util.Util.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.ai.L2AttackableAI;
import com.l2jserver.gameserver.ai.L2CharacterAI;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.effects.L2EffectType;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.AbstractNpcInfo;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.gameserver.network.serverpackets.SocialAction;
import com.l2jserver.gameserver.network.serverpackets.ValidateLocation;
import com.l2jserver.util.Rnd;

// While a tamed beast behaves a lot like a pet (ingame) and does have
// an owner, in all other aspects, it acts like a mob.
// In addition, it can be fed in order to increase its duration.
// This class handles the running tasks, AI, and feed of the mob.
// The (mostly optional) AI on feeding the spawn is handled by the datapack ai script
public final class L2TamedBeastInstance extends L2FeedableBeastInstance
{
	// Config
	private static final boolean AUTO_CHAT = true;
	private static final boolean AUTO_BUFF = true;
	private static final boolean AUTO_DEBUFF = true;
	private static final boolean AUTO_HEAL = true;
	
	private int _foodSkillId;
	private static final int MAX_DISTANCE_FROM_OWNER = 2000;
	private static final int MAX_DURATION = 1200000; // 20 minutes
	private static final int DURATION_CHECK_INTERVAL = 60000; // 1 minute
	private static final int DURATION_INCREASE_INTERVAL = 20000; // 20 secs (gained upon feeding)
	private static final int BUFF_INTERVAL = 5000; // 5 seconds
	private int _remainingTime = MAX_DURATION;
	protected L2PcInstance _owner;
	public int nameId = -1;	//+[JOJO] npcstring
	public String nameParam = null;	//+[JOJO]
	private Future<?> _buffTask = null;
	private Future<?> _durationCheckTask = null;
	protected boolean _isFreyaBeast;
	protected boolean _hasDeBuffSkill, _hasHealSkill;	//+[JOJO]
	
	//[JOJO]-------------------------------------------------
	protected long _chatTime;
	protected int _chatIndex;
	protected static int[] AUTO_CHAT_TEXT =
	{
		2029,	// がんばれ！よいしょ！
		2030,	// 食いしん坊でごめんね。もぐもぐ。
		2031,	// あなたと息を合わせるのが楽になってきたよ。
		2032,	// 手伝ってやろう！
		2033,	// 天気がいいな。ピクニックでも行こうか。
		2034,	// これといってあなたが気に入ったわけじゃなく．．．あの、その．．．
		2035,	// ここから離れるな。力を貸してやれなくなるぞ。
		2036,	// 役に立ってるのかなぁ、私。
		2037,	// 食べること以外にもできるんだってば！
		2038,	// えいっ、えいっ、えいっ、えーいっ！
	};
	//-------------------------------------------------------
	
	public L2TamedBeastInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2TamedBeastInstance);
if (com.l2jserver.Config.TAMED_BEAST_ALLIVE_SORT) {{
		setAI(new L2TamedBeastAI(new AIAccessor()));
}}
	}
	
	public L2TamedBeastInstance(int objectId, L2NpcTemplate template, L2PcInstance owner, int foodSkillId, int x, int y, int z) // FeedableBeasts
	{
		super(objectId, template);
		_isFreyaBeast = false;
		setInstanceType(InstanceType.L2TamedBeastInstance);
		setCurrentHp(getMaxHp());
		setCurrentMp(getMaxMp());
		setFoodType(foodSkillId);
		spawnMe(x, y, z);
if (com.l2jserver.Config.TAMED_BEAST_ALLIVE_SORT) {{
		setAI(new L2TamedBeastAI(new AIAccessor()));
}}
		setOwner(owner);
		startOwnerBuffs();
	}
	
	public L2TamedBeastInstance(int objectId, L2NpcTemplate template, L2PcInstance owner, int food, int x, int y, int z, boolean isFreyaBeast) // BeastFarm
	{
		super(objectId, template);
		_isFreyaBeast = isFreyaBeast;
		setInstanceType(InstanceType.L2TamedBeastInstance);
		setCurrentHp(getMaxHp());
		setCurrentMp(getMaxMp());
		setFoodType(food);
		spawnMe(x, y, z);
if (com.l2jserver.Config.TAMED_BEAST_ALLIVE_SORT) {{
		setAI(new L2TamedBeastAI(new AIAccessor()));
}}
		setOwner(owner);
		if (isFreyaBeast)
		{
			getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, _owner);
		}
	}
	
	public void onReceiveFood()
	{
		// Eating food extends the duration by 20secs, to a max of 20minutes
		_remainingTime = _remainingTime + DURATION_INCREASE_INTERVAL;
		if (_remainingTime > MAX_DURATION)
		{
			_remainingTime = MAX_DURATION;
		}
	}
	
	public int getRemainingTime()
	{
		return _remainingTime;
	}
	
	public void setRemainingTime(int duration)
	{
		_remainingTime = duration;
	}
	
	public int getFoodType()
	{
		return _foodSkillId;
	}
	
	public void setFoodType(int foodItemId)
	{
		if (foodItemId > 0)
		{
			_foodSkillId = foodItemId;
			
			// start the duration checks
			// start the buff tasks
			if (_durationCheckTask != null)
			{
				_durationCheckTask.cancel(true);
			}
			_durationCheckTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new CheckDuration(), DURATION_CHECK_INTERVAL, DURATION_CHECK_INTERVAL);
		}
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		
		getAI().stopFollow();
		if (_buffTask != null)
		{
			_buffTask.cancel(true);
		}
		if (_durationCheckTask != null)
		{
			_durationCheckTask.cancel(true);
		}
		
		// clean up variables
		List<L2TamedBeastInstance> trainedBeasts;
		if (_owner != null && (trainedBeasts = _owner.getTrainedBeasts()) != null)
		{
			trainedBeasts.remove(this);
		}
		_buffTask = null;
		_durationCheckTask = null;
		_owner = null;
		_foodSkillId = 0;
		_remainingTime = 0;
		return true;
	}
	
	@Override
	public boolean isAutoAttackable(L2Character attacker)
	{
		return !_isFreyaBeast;
	}
	
	//[JOJO]-------------------------------------------------
	private boolean canAutoBuff()
	{
		if (AUTO_BUFF)
			return true;
		else
			return !_isFreyaBeast;
	}
	
	private boolean canAutoDeBuff()
	{
		if (AUTO_DEBUFF)
			return true;
		else
			return !_isFreyaBeast;
	}
	
	private boolean canAutoHeal()
	{
		if (AUTO_HEAL)
			return true;
		else
			return !_isFreyaBeast;
	}
	
	private boolean hasDeBuffSkill()
	{
		if (canAutoDeBuff())
		{
			for (L2Skill skill : getSkills().values())
				if (isDeBuffSkill(skill))
					return true;
		}
		return false;
	}
	
	private boolean hasHealSkill()
	{
		if (canAutoHeal())
		{
			for (L2Skill skill : getSkills().values())
				if (isHealSkill(skill))
					return true;
		}
		return false;
	}
	
	protected static boolean isBuffSkill(L2Skill skill)
	{
		return skill.isBuff();
	}
	
	protected static boolean isDeBuffSkill(L2Skill skill)
	{
		return skill.isDebuff();
	}
	
	protected static boolean isHealSkill(L2Skill skill)
	{
		return skill.hasEffectType(L2EffectType.CPHEAL,
			L2EffectType.HEAL,
			L2EffectType.HEAL_PERCENT,
			L2EffectType.MANAHEAL_BY_LEVEL,
			L2EffectType.MANAHEAL_PERCENT);
	}
	
	@Override
	public L2Skill addSkill(L2Skill newSkill)
	{
		L2Skill oldSkill = super.addSkill(newSkill);
		_hasDeBuffSkill = hasDeBuffSkill();
		_hasHealSkill = hasHealSkill();
		return oldSkill;
	}
	
	public void removeBuffSkills()
	{
		for (Iterator<Map.Entry<Integer, L2Skill>> it = getSkills().entrySet().iterator(); it.hasNext(); )
		{
			Map.Entry<Integer, L2Skill> e = it.next();
			L2Skill skill = e.getValue();
			if (isBuffSkill(skill))
				it.remove();
		}
	}
	
	//-------------------------------------------------------
	
	public void castBeastSkills()
	{
		if (_owner == null)
		{
			return;
		}
		int delay = 100;
		boolean done = false;
		for (L2Skill skill : getSkills().values())
		{
			if (isBuffSkill(skill) && !isSkillDisabled(skill))
			{
				final L2Skill _skill = skill;
				ThreadPoolManager.getInstance().scheduleGeneral(new Runnable(){
					@Override public void run()
					{
						sitCastAndFollow(_skill, _owner);
					}
				}, delay);
				delay += (100 + skill.getHitTime());
				done = true;
			}
		}
		ThreadPoolManager.getInstance().scheduleGeneral(new Runnable(){
			@Override public void run()
			{
				getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, _owner);
			}
		}, delay);
		if (!done)
			_owner.sendMessage("条件が合わないため、能力は使用できません。");
	}
	
	public L2PcInstance getOwner()
	{
		return _owner;
	}
	
	private void setOwner(L2PcInstance owner)
	{
		if (owner != null)
		{
			_owner = owner;
			setTitle(owner.getName());
			// broadcast the new title
			broadcastPacket(new AbstractNpcInfo.NpcInfo(this, owner));
			
			owner.addTrainedBeast(this);
			
			// always and automatically follow the owner.
			getAI().startFollow(_owner, 100);
			
			_hasDeBuffSkill = hasDeBuffSkill();
			_hasHealSkill = hasHealSkill();
		}
		else
		{
			deleteMe(); // despawn if no owner
		}
	}
	
	public void startOwnerBuffs()
	{
		if (canAutoBuff())
		{
			// start the buff tasks
			if (_buffTask != null)
			{
				_buffTask.cancel(true);
			}
			_buffTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new CheckOwnerBuffs(), 30000/*BUFF_INTERVAL*/, BUFF_INTERVAL);
		}
	}
	
	@Override
	public boolean deleteMe()
	{
		if (_buffTask != null)
		{
			_buffTask.cancel(true);
		}
		_durationCheckTask.cancel(true);
		stopHpMpRegeneration();
		
		// clean up variables
		if ((_owner != null) && (_owner.getTrainedBeasts() != null))
		{
			_owner.getTrainedBeasts().remove(this);
		}
		setTarget(null);
		_buffTask = null;
		_durationCheckTask = null;
		_owner = null;
		_foodSkillId = 0;
		_remainingTime = 0;
		
		// remove the spawn
		return super.deleteMe();
	}
	
	// notification triggered by the owner when the owner is attacked.
	// tamed mobs will heal/recharge or debuff the enemy according to their skills
	public void onOwnerGotAttacked(L2Character attacker)
	{
		// check if the owner is no longer around...if so, despawn
		if ((_owner == null) || !_owner.isOnline())
		{
			deleteMe();
			return;
		}
		// if the owner is too far away, stop anything else and immediately run towards the owner.
		if (!_owner.isInsideRadius(this, MAX_DISTANCE_FROM_OWNER, true, true))
		{
			if (!isMoving())
				getAI().startFollow(_owner);
			return;
		}
		if (!(_hasDeBuffSkill || _hasHealSkill))
		{
			return;
		}
		// if the owner is dead, do nothing...
		if (_owner.isDead())
		{
			return;
		}
		
		// if the tamed beast is currently in the middle of casting, let it complete its skill...
		if (isCastingNow())
		{
			return;
		}
		
		double HPRatio = _owner.getCurrentHp() / _owner.getMaxHp();
		
		// if the owner has a lot of HP, then debuff the enemy with a random debuff among the available skills
		// use of more than one debuff at this moment is acceptable
		if (HPRatio >= 0.8)
		{
		  if (attacker != null && getCurrentHp() == getMaxHp())
			for (L2Skill skill : /*getTemplate().*/getSkills().values())
			{
				// if the skill is a debuff, check if the attacker has it already [ attacker.getEffect(L2Skill skill) ]
				if (isDeBuffSkill(skill) && !isSkillDisabled(skill) && Rnd.get(3) < 1 && !attacker.isAffectedBySkill(skill.getId()))
				{
					sitCastAndFollow(skill, attacker);
					break;	// isCastingNow
				}
			}
		}
		// for HP levels between 80% and 50%, do not react to attack events (so that MP can regenerate a bit)
		// for lower HP ranges, heal or recharge the owner with 1 skill use per attack.
		else if (HPRatio < 0.5)
		{
			int chance = 1;
			if (HPRatio < 0.25)
			{
				chance = 2;
			}
			
			// if the owner has a lot of HP, then debuff the enemy with a random debuff among the available skills
			for (L2Skill skill : /*getTemplate().*/getSkills().values())
			{
				// if the skill is a buff, check if the owner has it already [ owner.getEffect(L2Skill skill) ]
				if (Rnd.get(5) < chance && isHealSkill(skill) && !isSkillDisabled(skill))
				{
					sitCastAndFollow(skill, _owner);
					break;	// isCastingNow
				}
			}
		}
	}
	
	/**
	 * Prepare and cast a skill:<br>
	 * First smoothly prepare the beast for casting, by abandoning other actions.<br>
	 * Next, call super.doCast(skill) in order to actually cast the spell.<br>
	 * Finally, return to auto-following the owner.
	 * @param skill
	 * @param target
	 */
	protected void sitCastAndFollow(L2Skill skill, L2Character target)
	{
		stopMove(null);
 //		broadcastPacket(new StopMove(this));	//-[JOJO]
		getAI().setIntention(AI_INTENTION_IDLE);
		
		setTarget(target);
		doCast(skill);
		getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, _owner);
	}
	
	protected class CheckDuration implements Runnable
	{
		@Override
		public void run()
		{
			final L2TamedBeastInstance _tamedBeast = L2TamedBeastInstance.this;
			int foodTypeSkillId = _tamedBeast.getFoodType();
			L2PcInstance owner = _tamedBeast.getOwner();
			
			L2ItemInstance item = null;
			if (_tamedBeast._isFreyaBeast)
			{
				item = owner.getInventory().getItemByItemId(foodTypeSkillId);
				if ((item != null) && (item.getCount() >= 1))
				{
					owner.destroyItem("BeastMob", item, 1, _tamedBeast, true);
					_tamedBeast.broadcastPacket(new SocialAction(_tamedBeast.getObjectId(), 3));
				}
				else
				{
					_tamedBeast.deleteMe();
				}
			}
			else
			{
				_tamedBeast.setRemainingTime(_tamedBeast.getRemainingTime() - DURATION_CHECK_INTERVAL);
				// I tried to avoid this as much as possible...but it seems I can't avoid hardcoding
				// ids further, except by carrying an additional variable just for these two lines...
				// Find which food item needs to be consumed.
				if (foodTypeSkillId == 2188)
				{
					item = owner.getInventory().getItemByItemId(6643);
				}
				else if (foodTypeSkillId == 2189)
				{
					item = owner.getInventory().getItemByItemId(6644);
				}
				
				// if the owner has enough food, call the item handler (use the food and triffer all necessary actions)
				if ((item != null) && (item.getCount() >= 1))
				{
					L2Object oldTarget = owner.getTarget();
					owner.setTarget(_tamedBeast);
					L2Object[] targets =
					{
						_tamedBeast
					};
					
					// emulate a call to the owner using food, but bypass all checks for range, etc
					// this also causes a call to the AI tasks handling feeding, which may call onReceiveFood as required.
					owner.callSkill(SkillTable.getInstance().getInfo(foodTypeSkillId, 1), targets);
					owner.setTarget(oldTarget);
				}
				else
				{
					// if the owner has no food, the beast immediately despawns, except when it was only
					// newly spawned. Newly spawned beasts can last up to 5 minutes
					if (_tamedBeast.getRemainingTime() < (MAX_DURATION - 300000))
					{
						_tamedBeast.setRemainingTime(-1);
					}
				}
				// There are too many conflicting reports about whether distance from home should be taken into consideration. Disabled for now.
				// if (_tamedBeast.isTooFarFromHome())
				// _tamedBeast.setRemainingTime(-1);
				
				if (_tamedBeast.getRemainingTime() <= 0)
				{
					_tamedBeast.deleteMe();
				}
			}
			//[JOJO]-------------------------------------------------
			if (AUTO_CHAT)
			{
				long now;
				if ((now = System.currentTimeMillis()) > _chatTime)
				{
					_chatTime = now + Rnd.get(30_000, 120_000) * _tamedBeast.getOwner().getTrainedBeasts().size();
					int chatIndex;
					while ((chatIndex = Rnd.get(AUTO_CHAT_TEXT.length)) == _chatIndex) {}
					_chatIndex = chatIndex;
					_tamedBeast.broadcastPacket(new NpcSay(_tamedBeast, Say2.ALL, AUTO_CHAT_TEXT[chatIndex]));
				}
			}
			//-------------------------------------------------------
		}
	}
	
	protected class CheckOwnerBuffs implements Runnable
	{
		private final int _numBuffs, _limitBuffs;
		
		protected CheckOwnerBuffs()
		{
			// instead of calculating this value each time, let's get this now and pass it on
			int totalBuffsAvailable = 0;
			for (L2Skill skill : getSkills().values())
			{
				// if the skill is a buff, check if the owner has it already [ owner.getEffect(L2Skill skill) ]
				if (isBuffSkill(skill))
					++totalBuffsAvailable;
			}
			int limitBuffs = totalBuffsAvailable >= 3 ? totalBuffsAvailable * 2 / 3 : totalBuffsAvailable;
			_numBuffs = totalBuffsAvailable;
			_limitBuffs = limitBuffs;
		}
		
		@Override
		public void run()
		{
			L2PcInstance owner = getOwner();
			
			// check if the owner is no longer around...if so, despawn
			if (owner == null || !owner.isOnline())
			{
				deleteMe();
				return;
			}
			// if the owner is too far away, stop anything else and immediately run towards the owner.
			if (!isInsideRadius(owner, MAX_DISTANCE_FROM_OWNER, true, true))
			{
				getAI().startFollow(owner);
				return;
			}
			// if the owner is dead, do nothing...
			if (owner.isDead())
			{
				return;
			}
			// if the tamed beast is currently casting a spell, do not interfere (do not attempt to cast anything new yet).
			if (isCastingNow())
			{
				return;
			}
			
			int totalBuffsOnOwner = 0;
			int i = 0;
			int rand = Rnd.get(_numBuffs);
			L2Skill buffToGive = null;
			
			// get this npc's skills: getSkills()
			for (L2Skill skill : getSkills().values())
			{
				// if the skill is a buff, check if the owner has it already [ owner.getEffect(L2Skill skill) ]
				if (isBuffSkill(skill))
				{
					if (owner.isAffectedBySkill(skill.getId()))
					{
						++totalBuffsOnOwner;
					}
					else if (i == rand && !isSkillDisabled(skill))
					{
						buffToGive = skill;
					}
					++i;
				}
			}
			// if the owner has less than 60% of this beast's available buff, cast a random buff
			if (buffToGive != null && totalBuffsOnOwner < _limitBuffs)
				sitCastAndFollow(buffToGive, owner);
			else
				getAI().setIntention(CtrlIntention.AI_INTENTION_FOLLOW, getOwner());
		}
	}
	
	@Override
	public void onAction(L2PcInstance player, boolean interact)
	{
		if ((player == null) || !canTarget(player))
		{
			return;
		}
		
		// Check if the L2PcInstance already target the L2NpcInstance
		if (this != player.getTarget())
		{
			// Set the target of the L2PcInstance player
			player.setTarget(this);
		}
		else if (interact)
		{
			if (isAutoAttackable(player) && (Math.abs(player.getZ() - getZ()) < 100))
			{
				player.getAI().setIntention(CtrlIntention.AI_INTENTION_ATTACK, this);
			}
			else
			{
				// Send a Server->Client ActionFailed to the L2PcInstance in order to avoid that the client wait another packet
				player.sendPacket(ActionFailed.STATIC_PACKET);
			}
		}
	}
	
	//[JOJO]-------------------------------------------------
	@Override
	public L2CharacterAI getAI()
	{
if (com.l2jserver.Config.TAMED_BEAST_ALLIVE_SORT) {{
		L2CharacterAI ai = _ai; // copy handle
		if (ai == null)
		{
			synchronized (this)
			{
				if (_ai == null)
				{
					_ai = new L2TamedBeastAI(new AIAccessor());
				}
				return _ai;
			}
		}
		return ai;
}} else {{
		return super.getAI();
}}
	}
	
	protected class L2TamedBeastAI extends L2AttackableAI/*L2CharacterAI*/
	{
		public L2TamedBeastAI(L2Character.AIAccessor accessor)
		{
			super(accessor);
		}
		
		@Override
		protected void onEvtArrived()
		{
			super.onEvtArrived();
if (com.l2jserver.Config.TAMED_BEAST_ALLIVE_SORT) {{
			final L2PcInstance owner;
			if (getIntention() == CtrlIntention.AI_INTENTION_FOLLOW  && (owner = getOwner()) == getFollowTarget() && owner.getAI().getIntention() == AI_INTENTION_IDLE)
			{
				/*private static*/final double AVOID_ANGLE = Math.PI / 6;
				final List<L2TamedBeastInstance> trainedBeasts = owner.getTrainedBeasts();
				final int size = trainedBeasts.size();
				if (size >= 2)
				{
					final L2TamedBeastInstance beast = (L2TamedBeastInstance) getActor();
					final double distance = Math.max(64.0, owner.calculateDistance(beast, false, false));
					final int index = trainedBeasts.indexOf(beast);
					final double angle = convertHeadingToRadian(owner.getHeading())
						+ Math.PI
						- AVOID_ANGLE * size / 2
						+ AVOID_ANGLE * index;
					int x = owner.getX() + (int)(distance * Math.cos(angle));
					int y = owner.getY() + (int)(distance * Math.sin(angle));
					int z = owner.getZ();
					if (x != beast.getX() && y != beast.getY() /*&& z != beast.getZ()*/)
					{
						beast.setXYZ(x, y, z);
						owner.sendPacket(new ValidateLocation(beast));
					}
				}
			}
}}
		}
	}
	//-------------------------------------------------------
}
