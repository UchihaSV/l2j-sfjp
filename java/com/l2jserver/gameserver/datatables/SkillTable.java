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
package com.l2jserver.gameserver.datatables;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.gameserver.engines.DocumentEngine;
import com.l2jserver.gameserver.model.holders.SkillHolder;
import com.l2jserver.gameserver.model.skills.L2Skill;

import gnu.trove.list.array.TIntArrayList;
import gnu.trove.map.hash.TIntIntHashMap;

/**
 * 
 */
public class SkillTable
{
	private static Logger _log = Logger.getLogger(SkillTable.class.getName());
	
	private final Map<Integer, L2Skill> _skills = new HashMap<>();
	private final TIntIntHashMap _skillMaxLevel = new TIntIntHashMap();
	private final TIntArrayList _enchantable = new TIntArrayList();
	
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
		_skills.clear();
		DocumentEngine.getInstance().loadAllSkills(_skills);
		
		_skillMaxLevel.clear();
		for (final L2Skill skill : _skills.values())
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
		return (skillId * 1021) + skillLevel;
	}
	
	//[JOJO]-------------------------------------------------
	public final L2Skill getInfo(final int skillHashCode)
	{
		return _skills.get(skillHashCode);
	}
	//-------------------------------------------------------
	
	public final L2Skill getInfo(final int skillId, final int level)
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
	
	public final int getMaxLevel(final int skillId)
	{
		return _skillMaxLevel.get(skillId);
	}
	
	public final boolean isEnchantable(final int skillId)
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
		FAKE_PETRIFICATION(4616, 1),
		FIREWORK(5965, 1),
		LARGE_FIREWORK(2025, 1),
		BLESSING_OF_PROTECTION(5182, 1),
		ARENA_CP_RECOVERY(4380, 1),
		VOID_BURST(3630, 1),
		VOID_FLOW(3631, 1),
		THE_VICTOR_OF_WAR(5074, 1),
		THE_VANQUISHED_OF_WAR(5075, 1),
		SPECIAL_TREE_RECOVERY_BONUS(2139, 1),
		WEAPON_GRADE_PENALTY(6209, 1),
		ARMOR_GRADE_PENALTY(6213, 1);
		
		private final SkillHolder _holder;
		
		private FrequentSkill(int id, int level)
		{
			_holder = new SkillHolder(id, level);
		}
		
		public int getId()
		{
			return _holder.getSkillId();
		}
		
		public int getLevel()
		{
			return _holder.getSkillLvl();
		}
		
		public L2Skill getSkill()
		{
			return _holder.getSkill();
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