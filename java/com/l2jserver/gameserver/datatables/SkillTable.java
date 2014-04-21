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

import java.util.logging.Level;
import java.util.logging.Logger;

import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.engines.DocumentEngine;
import com.l2jserver.gameserver.model.skills.L2Skill;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.TIntIntHashMap;

/**
 * Skill table.
 */
public final class SkillTable
{
	private static Logger _log = Logger.getLogger(SkillTable.class.getName());
	
	private FastIntObjectMap<L2Skill> _skills;
	private TIntIntHashMap _skillMaxLevel;
	private TIntArrayList _enchantable;	//TODO: int[] _enchantable;
	
	protected SkillTable()
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
		final FastIntObjectMap<L2Skill> _skills = new FastIntObjectMap<>();
		final TIntIntHashMap _skillMaxLevel = new TIntIntHashMap();
		final TIntArrayList _enchantable = new TIntArrayList();
		//-------------------------------------------------------
		
		_skills.clear();
		DocumentEngine.getInstance().loadAllSkills(_skills);
		
		_skillMaxLevel.clear();
		for (L2Skill skill : _skills.values())
		{
			final int skillId = skill.getId();
			final int skillLvl = skill.getLevel();
			if (skillLvl > 99)
			{
				if (!_enchantable.contains(skillId))
				{
					_enchantable.add(skillId);
				}
				continue;
			}
			
			// only non-enchanted skills
			final int maxLvl = _skillMaxLevel.get(skillId);
			if (skillLvl > maxLvl)
			{
				_skillMaxLevel.put(skillId, skillLvl);
			}
		}
		
		// Sorting for binarySearch
		_enchantable.sort();
		
		//[JOJO]-------------------------------------------------
		_skillMaxLevel.trimToSize();
		_enchantable.trimToSize();
		this._skills = _skills; 
		this._skillMaxLevel = _skillMaxLevel; 
		this._enchantable = _enchantable; 
		//-------------------------------------------------------
	}
	
	/**
	 * Provides the skill hash
	 * @param skill The L2Skill to be hashed
	 * @return getSkillHashCode(skill.getId(), skill.getLevel())
	 */
	public static int getSkillHashCode(L2Skill skill)
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
	public static L2Skill getSkill(int skillHashCode)
	{
		return SkillTable.getInstance()._skills.get(skillHashCode);
	}
	
	@Deprecated
	public final L2Skill getInfo(final int skillHashCode)
	{
		return _skills.get(skillHashCode);
	}
	//-------------------------------------------------------
	
	public L2Skill getInfo(int skillId, int level)
	{
		final L2Skill result = _skills.get(getSkillHashCode(skillId, level));
		if (result != null)
		{
			return result;
		}
		
		// skill/level not found, fix for transformation scripts
		final int maxLvl = _skillMaxLevel.get(skillId);
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
		return _skillMaxLevel.get(skillId);
	}
	
	public boolean isEnchantable(int skillId)
	{
		return _enchantable.binarySearch(skillId) >= 0;
	}
	
	/**
	 * @param addNoble
	 * @param hasCastle
	 * @return an array with siege skills. If addNoble == true, will add also Advanced headquarters.
	 */
	public L2Skill[] getSiegeSkills(boolean addNoble, boolean hasCastle)
	{
		L2Skill[] temp = new L2Skill[2 + (addNoble ? 1 : 0) + (hasCastle ? 2 : 0)];
		int i = 0;
		temp[i++] = _skills.get(SkillTable.getSkillHashCode(246, 1));
		temp[i++] = _skills.get(SkillTable.getSkillHashCode(247, 1));
		
		if (addNoble)
		{
			temp[i++] = _skills.get(SkillTable.getSkillHashCode(326, 1));
		}
		if (hasCastle)
		{
			temp[i++] = _skills.get(SkillTable.getSkillHashCode(844, 1));
			temp[i++] = _skills.get(SkillTable.getSkillHashCode(845, 1));
		}
		return temp;
	}
	
	/**
	 * Enum to hold some important references to frequently used (hardcoded) skills in core
	 * @author DrHouse
	 */
	public static enum FrequentSkill
	{
		RAID_CURSE(4215, 1),
		RAID_CURSE2(4515, 1),
		SEAL_OF_RULER(246, 1),
		BUILD_HEADQUARTERS(247, 1),
		WYVERN_BREATH(4289, 1),
		STRIDER_SIEGE_ASSAULT(325, 1),
		FIREWORK(5965, 1),
		LARGE_FIREWORK(2025, 1),
		BLESSING_OF_PROTECTION(5182, 1),
		VOID_BURST(3630, 1),
		VOID_FLOW(3631, 1),
		THE_VICTOR_OF_WAR(5074, 1),
		THE_VANQUISHED_OF_WAR(5075, 1),
		SPECIAL_TREE_RECOVERY_BONUS(2139, 1),
		WEAPON_GRADE_PENALTY(6209, 1),
		ARMOR_GRADE_PENALTY(6213, 1);
		
		private final int _skillHashCode;
		
		private FrequentSkill(int id, int level)
		{
			_skillHashCode = SkillTable.getSkillHashCode(id, level);
		}
		
		public int getId()
		{
			return SkillTable.getSkillId(_skillHashCode);
		}
		
		public int getLevel()
		{
			return SkillTable.getSkillLevel(_skillHashCode);
		}
		
		public L2Skill getSkill()
		{
			return SkillTable.getSkill(_skillHashCode);
		}
	}
	
	public static SkillTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final SkillTable _instance = new SkillTable();
	}
}