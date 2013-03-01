/*
 * Copyright (C) 2005-2008 L2J_JP / 2008-2013 L2J-SFJP
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
package com.l2jserver.gameserver.model.holders;

import static com.l2jserver.gameserver.datatables.SkillTable.PRIME;

import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.model.skills.L2Skill;

/**
 * @author JOJO
 */
public class SkillHashCode
{
	public static int valueOf(int skillId, int skillLevel)
	{
		//assert skillId * PRIME + skillLevel == SkillTable.getSkillHashCode(skillId, skillLevel);
		return skillId * PRIME + skillLevel;
	}
	
	public static int valueOf(L2Skill skill)
	{
		return valueOf(skill.getId(), skill.getLevel());
	}
	
	public static final int getId(int skillHashCode)
	{
		return skillHashCode / PRIME;
	}
	
	public static final int getLevel(int skillHashCode)
	{
		return skillHashCode % PRIME;
	}
	
	public static final L2Skill getSkill(int skillHashCode)
	{
		return SkillTable.getInstance().getInfo(skillHashCode);
	}
}