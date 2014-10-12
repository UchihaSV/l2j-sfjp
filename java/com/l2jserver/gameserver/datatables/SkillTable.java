package com.l2jserver.gameserver.datatables;

import com.l2jserver.gameserver.model.skills.Skill;

//[JOJO]-------------------------------------------------
@Deprecated public class SkillTable
{
	public static final int getSkillHashCode(Skill skill)
	{
		return SkillData.getSkillHashCode(skill);
	}
	
	public static final int getSkillHashCode(int skillId, int skillLevel)
	{
		return SkillData.getSkillHashCode(skillId, skillLevel);
	}
	
	public static final int getSkillId(int skillHashCode)
	{
		return SkillData.getSkillId(skillHashCode);
	}
	
	public static final int getSkillLevel(int skillHashCode)
	{
		return SkillData.getSkillLevel(skillHashCode);
	}
	
	public static final Skill getSkill(int skillHashCode)
	{
		return SkillData.getSkill(skillHashCode);
	}
}
//-------------------------------------------------------
