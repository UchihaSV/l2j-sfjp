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
package com.l2jserver.gameserver.templates;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * This class represents a Buff Template
 * 
 * Author: JOJO
 *
 * This code was based on...
 * l2jfree.com L2BuffTemplate.java Revision:2917
 * Author: G1ta0
 */

@Deprecated
public class L2BuffTemplate
{
	/** Id of buff template */
	private int		_templateId;

	/** Name of the buff template */
	private String	_templateName;

	/** Identifier of the skill (buff) to cast */
	private int		_skillId;

	/** Order of the skill in template */
	private int		_skillOrder;

	private L2Skill	_skill;

	/** Level of the skill (buff) to cast */
	private int		_skillLevel;

	/** Force cast, even if same effect present */
	private boolean	_forceCast;

	/** Condition that player must have to obtain this buff */
	/** Min player level */
	private int		_minLevel;

	/** Max player level */
	private int		_maxLevel;

	/** Player's faction */
	private int		_faction;

	/** Players's race */
	private int		_race;

	/** Magus/Fighter class of the player */
	private int		_class;

	/** Adena price */
	private int		_adena;

	/** Faction points price */
	private int		_points;

	/**
	 * Constructor of L2BuffTemplat.<BR>
	 * <BR>
	 */
//	public L2BuffTemplate(StatsSet set)
//	{
//		_templateId = set.getInteger("id");
//		_templateName = set.getString("name");
//		_skillId = set.getInteger("skillId");
//		_skillLevel = set.getInteger("skillLevel");
//		_skillOrder = set.getInteger("skillOrder");
//
//		if (_skillLevel == 0)
//			_skillLevel = SkillTable.getInstance().getMaxLevel(_skillId, _skillLevel);
//
//		_skill = SkillTable.getInstance().getInfo(_skillId, _skillLevel);
//
//		_forceCast = (set.getInteger("forceCast") == 1);
//		_minLevel = set.getInteger("minLevel");
//		_maxLevel = set.getInteger("maxLevel");
//		_race = set.getInteger("race");
//		_class = set.getInteger("class");
//		_faction = set.getInteger("faction");
//		_adena = set.getInteger("adena");
//		_points = set.getInteger("points");
//	}
	public L2BuffTemplate(ResultSet set/*npc_buffer_list.sql*/) throws SQLException
	{
		_templateId = set.getInt("id");
		_templateName = set.getString("name");
		_skillId = set.getInt("skill_id");
		_skillLevel = set.getInt("skill_level");
		_skillOrder = set.getInt("skill_order");

//		if (_skillLevel == 0)
//			_skillLevel = SkillTable.getInstance().getMaxLevel(_skillId, _skillLevel);
		_skillLevel = getMaxLevel(_skillId, _skillLevel);	//XXX:[JOJO]

		_skill = SkillTable.getInstance().getInfo(_skillId, _skillLevel);

		_forceCast = (set.getInt("skill_force") == 1);
		_minLevel = set.getInt("char_min_level");
		_maxLevel = set.getInt("char_max_level");
		_race = set.getInt("char_race");
		_class = set.getInt("char_class");
		_faction = set.getInt("char_faction");
		_adena = set.getInt("price_adena");
		_points = set.getInt("price_points");
		if (_skillLevel != set.getInt("skill_level")) {
			try
			{
				FileWriter f = new FileWriter("__npcbuf__.sql", true);
				f.write("-- "+_skillId+" "+set.getInt("skill_level")+" "+SkillTable.getInstance().getInfo(_skillId, _skillLevel).getName()+"\n");
				f.write("UPDATE npc_buffer_list SET skill_level="+_skillLevel+" WHERE id="+_templateId+"\n");
				f.close();
            }
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	int getMaxLevel(int magicId, int level)
	{
		return SkillTable.getInstance().getInfo(magicId, 130) != null? 130 : level;
//		SkillTable skillTable = SkillTable.getInstance();
//
//		int max = level;
//		while (++level <= 130)
//		{
//			if (skillTable.getInfo(magicId, level) != null)
//				max = level;
//		}
//		return max;
	}

	/**
	 * @return Returns the Id of the buff template
	 */
	public int getId()
	{
		return _templateId;
	}

	/**
	 * @return Returns the Name of the buff template
	 */
	public String getName()
	{
		return _templateName;
	}

	/**
	 * @return Returns the Id of the buff that the L2PcInstance will receive
	 */
	public int getSkillId()
	{
		return _skillId;
	}

	/**
	 * @return Returns the Id of the buff that the L2PcInstance will receive
	 */
	public int getSkillOrder()
	{
		return _skillOrder;
	}

	/**
	 * @return Returns the Level of the buff that the L2PcInstance will receive
	 */
	public int getSkillLevel()
	{
		return _skillLevel;
	}

	/**
	 * @return Returns the Skill that the L2PcInstance will receive
	 */
	public L2Skill getSkill()
	{
		return _skill;
	}

	/**
	 * @return Returns the L2PcInstance minimum level to receive buff
	 */
	public int getMinLevel()
	{
		return _minLevel;
	}

	/**
	 * @return Returns the L2PcInstance maximum level to receive buff
	 */
	public int getMaxLevel()
	{
		return _maxLevel;
	}

	/**
	 * @return Returns the requirement faction to receive buff
	 */
	public int getFaction()
	{
		return _faction;
	}

	/**
	 * @return Returns the price for buff in Adena
	 */
	public int getAdenaPrice()
	{
		return _adena;
	}

	/**
	 * @return Returns the price for buff in Faction Points
	 */
	public int getPointsPrice()
	{
		return _points;
	}

	/**
	 * @return Is cast animation will be shown
	 */
	public boolean forceCast()
	{
		return _forceCast;
	}

	/**
	 * @return Returns the result of level check
	 */
	public boolean checkLevel(L2PcInstance player)
	{
		return (_minLevel == 0 || player.getLevel() >= _minLevel) && (_maxLevel == 0 || player.getLevel() <= _maxLevel);
	}

	/**
	 * @return Returns the result of race check
	 */
	public boolean checkRace(L2PcInstance player)
	{
		if (_race == 0 || _race == 0x3F) return true;
		switch (player.getRace()) {
			case Kamael:	return (_race & 0x20) != 0;
			case Human:		return (_race & 0x10) != 0;
			case Elf:		return (_race & 0x08) != 0;
			case DarkElf:	return (_race & 0x04) != 0;
			case Orc:		return (_race & 0x02) != 0;
			case Dwarf:		return (_race & 0x01) != 0;
		}
		return false;
	}

	/**
	 * @return Returns the result of Magus/Fighter class check
	 */
	public boolean checkClass(L2PcInstance player)
	{
		return _class == 0 || _class == 3 || (_class == 1 && !player.isMageClass()) || (_class == 2 && player.isMageClass());
	}

	/**
	 * @return Returns the result of faction check
	 */
	public boolean checkFaction(L2PcInstance player)
	{
		return true;
		// return ((_faction == 0 ||player.getFaction = _faction)
	}

//	/**
//	 * @return Returns the result of price check
//	 */
//	public boolean checkPrice(L2PcInstance player)
//	{
//		return ((_adena == 0 || player.getInventory().getAdena() >= _adena));
//		// return ((_adena == 0 || player.getInventory().getAdena()>=_adena) &&
//		// (_points == 0 || (player.getFactionPoints>=_points)));
//	}

	/**
	 * @return Returns the result of all player related conditions check
	 */
	public boolean checkPlayer(L2PcInstance player)
	{
		return checkLevel(player) && checkRace(player) && checkClass(player) && checkFaction(player);
	}
}
