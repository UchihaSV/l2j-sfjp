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

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.Config;
import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.L2ArmorSet;
import com.l2jserver.gameserver.model.holders.SkillHolder;

/**
 * @author godson, Luno, UnAfraid
 */
public final class ArmorSetsTable extends DocumentParser
{
	private final Map<Integer, L2ArmorSet> _armorSets = new HashMap<>();
	
	private ArmorSetsTable()
	{
		load();
	}
	
	private void load()
	{
		_armorSets.clear();
		parseDirectory(new File(Config.DATAPACK_ROOT, "data/stats/armorsets"));
		_log.log(Level.INFO, getClass().getSimpleName() + ": Loaded " + _armorSets.size() + " Armor sets.");
	}
	
	@Override
	protected void parseDocument(Document doc)
	{
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("set".equalsIgnoreCase(d.getNodeName()))
					{
						L2ArmorSet set = new L2ArmorSet();
						for (Node a = d.getFirstChild(); a != null; a = a.getNextSibling())
						{
							NamedNodeMap attrs = a.getAttributes();
							switch (a.getNodeName())
							{
								case "chest":
								{
									set.addChest(parseInt(attrs, "id"));
									break;
								}
								case "feet":
								{
									set.addFeet(parseInt(attrs, "id"));
									break;
								}
								case "gloves":
								{
									set.addGloves(parseInt(attrs, "id"));
									break;
								}
								case "head":
								{
									set.addHead(parseInt(attrs, "id"));
									break;
								}
								case "legs":
								{
									set.addLegs(parseInt(attrs, "id"));
									break;
								}
								case "shield":
								{
									set.addShield(parseInt(attrs, "id"));
									break;
								}
								case "skill":
								{
									int skillId = parseInt(attrs, "id");
									int skillLevel = parseInt(attrs, "level");
									set.addSkill(new SkillHolder(skillId, skillLevel));
									break;
								}
								case "shield_skill":
								{
									int skillId = parseInt(attrs, "id");
									int skillLevel = parseInt(attrs, "level");
									set.addShieldSkill(new SkillHolder(skillId, skillLevel));
									break;
								}
								case "enchant6skill":
								{
									int skillId = parseInt(attrs, "id");
									int skillLevel = parseInt(attrs, "level");
									set.addEnchant6Skill(new SkillHolder(skillId, skillLevel));
									break;
								}
								case "con":
								{
									// TODO: Implement me
									break;
								}
								case "dex":
								{
									// TODO: Implement me
									break;
								}
								case "str":
								{
									// TODO: Implement me
									break;
								}
								case "men":
								{
									// TODO: Implement me
									break;
								}
								case "wit":
								{
									// TODO: Implement me
									break;
								}
								case "int":
								{
									// TODO: Implement me
									break;
								}
							}
						}
						_armorSets.put(set.getChestId(), set);
					}
				}
			}
		}
	}
	
	/**
	 * @param chestId the chest Id to verify.
	 * @return {@code true} if the chest Id belongs to a registered armor set, {@code false} otherwise.
	 */
	public boolean isArmorSet(int chestId)
	{
		return _armorSets.containsKey(chestId);
	}
	
	/**
	 * @param chestId the chest Id identifying the armor set.
	 * @return the armor set associated to the give chest Id.
	 */
	public L2ArmorSet getSet(int chestId)
	{
		return _armorSets.get(chestId);
	}
	
	public static ArmorSetsTable getInstance()
	{
		return SingletonHolder._instance;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final ArmorSetsTable _instance = new ArmorSetsTable();
	}
}
