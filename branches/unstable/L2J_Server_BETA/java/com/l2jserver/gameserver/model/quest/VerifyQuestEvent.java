package com.l2jserver.gameserver.model.quest;

import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.L2Trap;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.items.L2Item;
import com.l2jserver.gameserver.model.quest.Quest.TrapAction;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.zone.L2ZoneType;

/**
 * @author JOJO
 */
public class VerifyQuestEvent
{
	public static boolean hasMethod(Quest.QuestEventType EventType, Quest q)
	{
		switch (EventType) {
			case ON_FIRST_TALK:
				return hasOnFirstTalk(q);
		//	case QUEST_START:
		//		return hasOnTalk(q);
			case ON_TALK:
				return hasOnTalk(q);
			case ON_ATTACK:
				return hasOnAttack(q);
			case ON_KILL:
				return hasOnKill(q);
			case ON_SPAWN:
				return hasOnSpawn(q);
			case ON_SKILL_SEE:
				return hasOnSkillSee(q);
			case ON_FACTION_CALL:
				return hasOnFactionCall(q);
			case ON_AGGRO_RANGE_ENTER:
				return hasOnAggroRangeEnter(q);
			case ON_SPELL_FINISHED:
				return hasOnSpellFinished(q);
		//	case ON_SKILL_LEARN:
		//		return hasOnAcquireSkill(q);
			case ON_ENTER_ZONE:
				return hasOnEnterZone(q);
			case ON_EXIT_ZONE:
				return hasOnExitZone(q);
			case ON_TRAP_ACTION:
				return hasOnTrapAction(q);
			case ON_ITEM_USE:
				return hasOnItemUse(q);
			default:
				return true;
		//		throw new RuntimeException();
		}
	}
	
	public static String checkMethod(Quest.QuestEventType EventType, Quest q)
	{
		String result = null;
		switch (EventType) {
			case ON_FIRST_TALK:
				if (!hasOnFirstTalk(q))
					result = "onFirstTalk()";
				break;
		//	case QUEST_START:
		//		if (!hasOnTalk(q))
		//			result = "onTalk()";
		//		break;
			case ON_TALK:
				if (!hasOnTalk(q))
					result = "onTalk()";
				break;
			case ON_ATTACK:
				if (!hasOnAttack(q))
					result = "onAttack()";
				break;
			case ON_KILL:
				if (!hasOnKill(q))
					result = "onKill()";
				break;
			case ON_SPAWN:
				if (!hasOnSpawn(q))
					result = "onSpawn()";
				break;
			case ON_SKILL_SEE:
				if (!hasOnSkillSee(q))
					result = "onSkillSee()";
				break;
			case ON_FACTION_CALL:
				if (!hasOnFactionCall(q))
					result = "onFactionCall()";
				break;
			case ON_AGGRO_RANGE_ENTER:
				if (!hasOnAggroRangeEnter(q))
					result = "onAggroRangeEnter()";
				break;
			case ON_SPELL_FINISHED:
				if (!hasOnSpellFinished(q))
					result = "onSpellFinished()";
				break;
		//	case ON_SKILL_LEARN:
		//		if (!hasOnAcquireSkill(q))
		//			result = "onAcquireSkill()";
		//		break;
			case ON_ENTER_ZONE:
				if (!hasOnEnterZone(q))
					result = "onEnterZone()";
				break;
			case ON_EXIT_ZONE:
				if (!hasOnExitZone(q))
					result = "onExitZone()";
				break;
			case ON_TRAP_ACTION:
				if (!hasOnTrapAction(q))
					result = "onTrapAction()";
				break;
			case ON_ITEM_USE:
				if (!hasOnItemUse(q))
					result = "onItemUse()";
				break;
			default:
		//		throw new RuntimeException();
		}
		return result;
	}
	
	public static boolean hasOnFirstTalk(Quest q)
	{
		try {
			// onFirstTalk(L2Npc npc, L2PcInstance player)
			q.getClass().getDeclaredMethod("onFirstTalk", L2Npc.class, L2PcInstance.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnTalk(Quest q)
	{
		try {
			// onTalk(L2Npc npc, L2PcInstance talker)
			q.getClass().getDeclaredMethod("onTalk", L2Npc.class, L2PcInstance.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnAttack(Quest q)
	{
		try {
			// onAttack(L2Npc npc, L2PcInstance attacker, int damage, boolean isPet)
			q.getClass().getDeclaredMethod("onAttack", L2Npc.class, L2PcInstance.class, int.class, boolean.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnKill(Quest q)
	{
		try {
			// onKill(L2Npc npc, L2PcInstance killer, boolean isPet)
			q.getClass().getDeclaredMethod("onKill", L2Npc.class, L2PcInstance.class, boolean.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnSpawn(Quest q)
	{
		try {
			// onSpawn(L2Npc npc)
			q.getClass().getDeclaredMethod("onSpawn", L2Npc.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnSkillSee(Quest q)
	{
		try {
			// onSkillSee(L2Npc npc, L2PcInstance caster, L2Skill skill, L2Object[] targets, boolean isPet)
			q.getClass().getDeclaredMethod("onSkillSee", L2Npc.class, L2PcInstance.class, L2Skill.class, L2Object[].class, boolean.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnFactionCall(Quest q)
	{
		try {
			// onFactionCall(L2Npc npc, L2Npc caller, L2PcInstance attacker, boolean isPet)
			q.getClass().getDeclaredMethod("onFactionCall", L2Npc.class, L2Npc.class, L2PcInstance.class, boolean.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnAggroRangeEnter(Quest q)
	{
		try {
			// onAggroRangeEnter(L2Npc npc, L2PcInstance player, boolean isPet)
			q.getClass().getDeclaredMethod("onAggroRangeEnter", L2Npc.class, L2PcInstance.class, boolean.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnSpellFinished(Quest q)
	{
		try {
			// onSpellFinished(L2Npc npc, L2PcInstance player, L2Skill skill)
			q.getClass().getDeclaredMethod("onSpellFinished", L2Npc.class, L2PcInstance.class, L2Skill.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnEnterZone(Quest q)
	{
		try {
			// onEnterZone(L2Character character, L2ZoneType zone)
			q.getClass().getDeclaredMethod("onEnterZone", L2Character.class, L2ZoneType.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnExitZone(Quest q)
	{
		try {
			// onExitZone(L2Character character, L2ZoneType zone)
			q.getClass().getDeclaredMethod("onExitZone", L2Character.class, L2ZoneType.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnTrapAction(Quest q)
	{
		try {
			// onTrapAction(L2Trap trap, L2Character trigger, TrapAction action)
			q.getClass().getDeclaredMethod("onTrapAction", L2Trap.class, L2Character.class, TrapAction.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
	
	public static boolean hasOnItemUse(Quest q)
	{
		try {
			// onItemUse(L2Item item, L2PcInstance player)
			q.getClass().getDeclaredMethod("onItemUse", L2Item.class, L2PcInstance.class);
			return true;
		}
		catch (NoSuchMethodException | SecurityException e) {
			return false;
		}
	}
}