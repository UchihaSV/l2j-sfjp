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

import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.model.skills.L2Skill;

/**
 * @author JOJO
 */
@Deprecated
public class SkillHashCode
{
	public static int valueOf(int skillId, int skillLevel)
	{
		return SkillTable.getSkillHashCode(skillId, skillLevel);
	}
	
	public static int valueOf(L2Skill skill)
	{
		return skill.getReuseHashCode();
	}
	
	public static final int getId(int skillHashCode)
	{
		return SkillTable.getSkillId(skillHashCode);
	}
	
	public static final int getLevel(int skillHashCode)
	{
		return SkillTable.getSkillLevel(skillHashCode);
	}
	
	public static final L2Skill getSkill(int skillHashCode)
	{
		return SkillTable.getSkill(skillHashCode);
	}
}