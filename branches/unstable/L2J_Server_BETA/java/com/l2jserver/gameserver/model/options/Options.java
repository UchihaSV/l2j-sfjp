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
package com.l2jserver.gameserver.model.options;

import static com.l2jserver.gameserver.datatables.SkillTable.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.skills.funcs.Func;
import com.l2jserver.gameserver.model.skills.funcs.FuncTemplate;
import com.l2jserver.gameserver.model.stats.Env;
import com.l2jserver.gameserver.network.serverpackets.SkillCoolTime;
import com.l2jserver.gameserver.util.Util;

/**
 * @author UnAfraid
 */
public class Options
{
	private final int _id;
	private static final FuncTemplate[] EMPTY_FUNC_TEMPLATES = {};
	private FuncTemplate[] _funcs = EMPTY_FUNC_TEMPLATES;
	
	private int _activeSkill = 0;
	private int _passiveSkill = 0;
	
	private static final OptionsSkillHolder[] EMPTY_OPTIONS_SKILL_HOLDERS = {};
	private OptionsSkillHolder[] _activationSkills = EMPTY_OPTIONS_SKILL_HOLDERS;
	
	/**
	 * @param id
	 */
	public Options(int id)
	{
		_id = id;
	}
	
	public final int getId()
	{
		return _id;
	}
	
	public boolean hasFuncs()
	{
		return _funcs.length > 0;
	}
	
	public List<Func> getStatFuncs(L2ItemInstance item, L2Character player)
	{
		if (_funcs.length == 0)
		{
			return Collections.<Func> emptyList();
		}
		
		final List<Func> funcs = new ArrayList<>(_funcs.length);
		final Env env = new Env();
		env.setCharacter(player);
		env.setTarget(player);
		env.setItem(item);
		for (FuncTemplate t : _funcs)
		{
			Func f = t.getFunc(env, this);
			if (f != null)
			{
				funcs.add(f);
			}
			player.sendDebugMessage("Adding stats: " + t.stat + " val: " + t.lambda.calc(env));
		}
		return funcs;
	}
	
	public void addFunc(FuncTemplate template)
	{
		_funcs = Util.append(_funcs, template);
	}
	
	public boolean hasActiveSkill()
	{
		return _activeSkill != 0;
	}
	
	public L2Skill getActiveSkill()
	{
		return getSkill(_activeSkill);
	}
	
	public void setActiveSkill(int id, int level)
	{
		_activeSkill = getSkillHashCode(id, level);
	}
	
	public boolean hasPassiveSkill()
	{
		return _passiveSkill != 0;
	}
	
	public L2Skill getPassiveSkill()
	{
		return getSkill(_passiveSkill);
	}
	
	public void setPassiveSkill(int id, int level)
	{
		_passiveSkill = getSkillHashCode(id, level);
	}
	
	public boolean hasActivationSkills()
	{
		return _activationSkills.length > 0;
	}
	
	public boolean hasActivationSkills(OptionsSkillType type)
	{
		for (OptionsSkillHolder holder : _activationSkills)
		{
			if (holder.getSkillType() == type)
			{
				return true;
			}
		}
		return false;
	}
	
	@Deprecated public OptionsSkillHolder[] getActivationsSkills()
	{
		return _activationSkills;
	}
	
	public List<OptionsSkillHolder> getActivationsSkills(OptionsSkillType type)
	{
		List<OptionsSkillHolder> temp = new ArrayList<>();
		for (OptionsSkillHolder holder : _activationSkills)
		{
			if (holder.getSkillType() == type)
			{
				temp.add(holder);
			}
		}
		return temp;
	}
	
	public void addActivationSkill(OptionsSkillHolder holder)
	{
		_activationSkills = Util.append(_activationSkills, holder);
	}
	
	public void apply(L2PcInstance player)
	{
		player.sendDebugMessage("Activating option id: " + _id);
		if (hasFuncs())
		{
			player.addStatFuncs(getStatFuncs(null, player));
		}
		if (hasActiveSkill())
		{
			addSkill(player, getActiveSkill());
			player.sendDebugMessage("Adding active skill: " + getActiveSkill());
		}
		if (hasPassiveSkill())
		{
			addSkill(player, getPassiveSkill());
			player.sendDebugMessage("Adding passive skill: " + getPassiveSkill());
		}
		if (hasActivationSkills())
		{
			for (OptionsSkillHolder holder : _activationSkills)
			{
				player.addTriggerSkill(holder);
				player.sendDebugMessage("Adding trigger skill: " + holder);
			}
		}
		
		player.sendSkillList();
	}
	
	public void remove(L2PcInstance player)
	{
		player.sendDebugMessage("Deactivating option id: " + _id);
		if (hasFuncs())
		{
			player.removeStatsOwner(this);
		}
		if (hasActiveSkill())
		{
			player.removeSkill(getActiveSkill(), false, false);
			player.sendDebugMessage("Removing active skill: " + getActiveSkill());
		}
		if (hasPassiveSkill())
		{
			player.removeSkill(getPassiveSkill(), false, true);
			player.sendDebugMessage("Removing passive skill: " + getPassiveSkill());
		}
		if (hasActivationSkills())
		{
			for (OptionsSkillHolder holder : _activationSkills)
			{
				player.removeTriggerSkill(holder);
				player.sendDebugMessage("Removing trigger skill: " + holder);
			}
		}
		player.sendSkillList();
	}
	
	private final void addSkill(L2PcInstance player, L2Skill skill)
	{
		boolean updateTimeStamp = false;
		
		player.addSkill(skill, false);
		
		if (skill.isActive())
		{
			final long remainingTime = player.getSkillRemainingReuseTime(skill.getReuseHashCode());
			if (remainingTime > 0)
			{
				player.addTimeStamp(skill, remainingTime);
				player.disableSkill(skill, remainingTime);
			}
			updateTimeStamp = true;
		}
		if (updateTimeStamp)
		{
			player.sendPacket(new SkillCoolTime(player));
		}
	}
}
