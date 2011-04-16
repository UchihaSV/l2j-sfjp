package com.l2jserver.gameserver.skills.effects;

import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.model.L2Effect;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.skills.AbnormalEffect;
import com.l2jserver.gameserver.skills.Env;
import com.l2jserver.gameserver.templates.effects.EffectTemplate;
import com.l2jserver.gameserver.templates.skills.L2EffectType;

/**
 * @author JOJO Copy from EffectDanceStun.java
 *
 */
public class EffectFloatStun extends L2Effect
{
	public EffectFloatStun(Env env, EffectTemplate template)
	{
		super(env, template);
	}

	@Override
	public L2EffectType getEffectType()
	{
		return L2EffectType.STUN;
	}

	/** Notify started */
	@Override
	public boolean onStart()
	{
		L2Character effected = getEffected();
		if (effected.isStunned() || effected.isImmobilized())
			return false;
		int e = effected.getAbnormalEffects();
		if ((e & AbnormalEffect.FLOATING_ROOT.getMask()) != 0
		  ||(e & AbnormalEffect.DANCE_STUNNED.getMask()) != 0)
			return false;

		effected.setTarget(null);
		effected.getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		effected.abortAttack();
		effected.abortCast();

		effected.startAbnormalEffect(AbnormalEffect.FLOATING_ROOT);
		effected.setIsImmobilized(true);
		effected.disableAllSkills();
		return true;
	}

	/** Notify exited */
	@Override
	public void onExit()
	{
		L2Character effected = getEffected();
		effected.stopAbnormalEffect(AbnormalEffect.FLOATING_ROOT);
		effected.setIsImmobilized(false);
		effected.enableAllSkills();
	}

	@Override
	public boolean onActionTime()
	{
		// just stop this effect
		return false;
	}
}