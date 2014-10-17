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
package com.l2jserver.gameserver.datatables;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.engines.DocumentEngine;
import com.l2jserver.gameserver.model.skills.Skill;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.TIntByteHashMap;

/**
 * Skill data.
 */
public final class SkillData
{
	private static Logger _log = Logger.getLogger(SkillData.class.getName());
	
	private FastIntObjectMap<Skill> _skills;	//[JOJO] -HashMap
	private TIntByteHashMap _skillMaxLevel;		//[JOJO] -HashMap
	private int[] _enchantable;					//[JOJO] -HashSet
	
	protected SkillData()
	{
		load();
	}
	
	public void reload()
	{
		load();
		// Reload Skill Tree as well.
		SkillTreesData.getInstance().load();
	}
	
	private void load()
	{
		//[JOJO]-------------------------------------------------
		final FastIntObjectMap<Skill> skills = new FastIntObjectMap<>();
		final TIntByteHashMap skillMaxLevel = new TIntByteHashMap();
		final TIntArrayList enchantable = new TIntArrayList();
		//-------------------------------------------------------
		
		DocumentEngine.getInstance().loadAllSkills(skills);
		
		for (Skill skill : skills.values())
		{
			final int skillId = skill.getId();
			final int skillLvl = skill.getLevel();
			if (skillLvl > 99)
			{
				if (!enchantable.contains(skillId))
				{
					enchantable.add(skillId);
				}
				continue;
			}
			
			// only non-enchanted skills
			final int maxLvl = skillMaxLevel.get(skillId);
			if (skillLvl > maxLvl)
			{
				skillMaxLevel.put(skillId, (byte) skillLvl);
			}
		}
		
		//[JOJO]-------------------------------------------------
		skillMaxLevel.trimToSize();
		enchantable.sort();	// Sorting for binary-search.
		_skills = skills;
		_skillMaxLevel = skillMaxLevel;
		_enchantable = enchantable.toArray();
		//-------------------------------------------------------
	}
	
	/**
	 * Provides the skill hash
	 * @param skill The L2Skill to be hashed
	 * @return getSkillHashCode(skill.getId(), skill.getLevel())
	 */
	public static int getSkillHashCode(Skill skill)
	{
		return getSkillHashCode(skill.getId(), skill.getLevel());
	}
	
	/**
	 * Centralized method for easier change of the hashing sys
	 * @param skillId The Skill Id
	 * @param skillLevel The Skill Level
	 * @return The Skill hash number
	 */
	public static int getSkillHashCode(int skillId, int skillLevel)
	{
		return (skillId * PRIME) + skillLevel;
	}
	
	//[JOJO]-------------------------------------------------
	/**
	 * @param skillHashCode The Skill hash number
	 * @return The Skill Id
	 */
	public static int getSkillId(int skillHashCode)
	{
		return skillHashCode / PRIME;
	}
	
	/**
	 * @param skillHashCode The Skill hash number
	 * @return The Skill Level
	 */
	public static int getSkillLevel(int skillHashCode)
	{
		return skillHashCode % PRIME;
	}
	
	public static final int PRIME = 1021;
	
	/**
	 * @param skillHashCode The Skill hash number
	 * @return The Skill
	 */
	public static Skill getSkill(int skillHashCode)
	{
		return SkillData.getInstance()._skills.get(skillHashCode);
	}
	
	@Deprecated
	public final Skill getInfo(final int skillHashCode)
	{
		return _skills.get(skillHashCode);
	}
	
	@Deprecated
	public final Skill getInfo(int skillId, int level)
	{
		return getSkill(skillId, level);
	}
	//-------------------------------------------------------
	
	public Skill getSkill(int skillId, int level)
	{
		final Skill result = _skills.get(getSkillHashCode(skillId, level));
		if (result != null)
		{
			return result;
		}
		
		// skill/level not found, fix for transformation scripts
		final int maxLvl = getMaxLevel(skillId);
		// requested level too high
		if ((maxLvl > 0) && (level > maxLvl))
		{
			if (Config.DEBUG)
			{
				_log.log(Level.WARNING, getClass().getSimpleName() + ": call to unexisting skill level id: " + skillId + " requested level: " + level + " max level: " + maxLvl, new Throwable());
			}
			return _skills.get(getSkillHashCode(skillId, maxLvl));
		}
		
		_log.warning(getClass().getSimpleName() + ": No skill info found for skill id " + skillId + " and skill level " + level + ".");
		return null;
	}
	
	public int getMaxLevel(int skillId)
	{
		return _skillMaxLevel.get(skillId);	// return 0 if not exist.
	}
	
	/**
	 * Verifies if the given skill ID correspond to an enchantable skill.
	 * @param skillId the skill ID
	 * @return {@code true} if the skill is enchantable, {@code false} otherwise
	 */
	public boolean isEnchantable(int skillId)
	{
		return Arrays.binarySearch(_enchantable, skillId) >= 0;
	}
	
	/**
	 * @param addNoble
	 * @param hasCastle
	 * @return an array with siege skills. If addNoble == true, will add also Advanced headquarters.
	 */
	public Skill[] getSiegeSkills(boolean addNoble, boolean hasCastle)
	{
		Skill[] temp = new Skill[2 + (addNoble ? 1 : 0) + (hasCastle ? 2 : 0)];
		int i = 0;
		temp[i++] = _skills.get(SkillData.getSkillHashCode(246, 1));
		temp[i++] = _skills.get(SkillData.getSkillHashCode(247, 1));
		
		if (addNoble)
		{
			temp[i++] = _skills.get(SkillData.getSkillHashCode(326, 1));
		}
		if (hasCastle)
		{
			temp[i++] = _skills.get(SkillData.getSkillHashCode(844, 1));
			temp[i++] = _skills.get(SkillData.getSkillHashCode(845, 1));
		}
		return temp;
	}
	
	
	public static SkillData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final SkillData _instance = new SkillData();
	}
}