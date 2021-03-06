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
package com.l2jserver.gameserver.engines;

import static com.l2jserver.util.Util.*;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;
import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.datatables.SkillData;
import com.l2jserver.gameserver.datatables.StringIntern;
import com.l2jserver.gameserver.engines.items.DocumentItem;
import com.l2jserver.gameserver.engines.skills.DocumentSkill;
import com.l2jserver.gameserver.model.items.L2Item;
import com.l2jserver.gameserver.model.skills.Skill;
import com.l2jserver.util.file.filter.XMLFilter;

/**
 * @author mkizub
 */
public class DocumentEngine
{
	private static final Logger _log = Logger.getLogger(DocumentEngine.class.getName());
	
	public static DocumentEngine getInstance()
	{
		return SingletonHolder._instance;
	}
	
	protected DocumentEngine()
	{
	}
	
	private void hashFiles(String dirname, List<File> hash)
	{
		File dir = new File(Config.DATAPACK_ROOT, dirname);
		if (!dir.exists())
		{
			_log.warning("Dir " + dir.getAbsolutePath() + " not exists");
			return;
		}
		for (File f : dir.listFiles(new XMLFilter()))
		{
			hash.add(f);
		}
	}
	
	private/*public*/ List<Skill> loadSkills(File file)
	{
		if (file == null)
		{
			_log.warning("Skill file not found.");
			return null;
		}
		DocumentSkill doc = new DocumentSkill(file);
		doc.parse();
		return doc.getSkills();
	}
	
	public void loadAllSkills(final FastIntObjectMap<Skill> allSkills)
	{
		long started = System.currentTimeMillis();
		int count = 0;
		List<File> files = new FastList<>();
		hashFiles("data/stats/skills", files);
		if (Config.CUSTOM_SKILLS_LOAD)
			hashFiles("data/stats/skills/custom", files);
		
		StringIntern.begin(SkillData.class.getSimpleName());
		StringIntern.intern("icon.skill0000");
		for (File file : files)
		{
			List<Skill> s = loadSkills(file);
			if (s == null)
			{
				continue;
			}
			for (Skill skill : s)
			{
				//[JOJO]-------------------------------------------------
				if (allSkills.put(SkillData.getSkillHashCode(skill), skill) != null)
					_log.log(Level.INFO, "<!>" + SkillData.class.getSimpleName() + ": file '" + file.getPath() + "' override the skill id=" + skill.getId() + " name=\"" + skill.getName() + "\" targetType=" + skill.getTargetType().name() + " abnormalType=" + skill.getAbnormalType().name());	//[JOJO]
				//-------------------------------------------------------
			//	allSkills.put(SkillData.getSkillHashCode(skill), skill);
				count++;
			}
		}
		StringIntern.end();
		_log.info("SkillData"/*getClass().getSimpleName()*/ + ": Loaded " + count + " Skill templates from XML files. (" + strMillTime(System.currentTimeMillis() - started) + ")");
	}
	
	/**
	 * Return created items
	 * @return List of {@link L2Item}
	 */
	public List<L2Item> loadItems()
	{
		FastIntObjectMap<L2Item> list = new FastIntObjectMap<>();	//[JOJO] FastList --> FastIntObjectMap
		List<File> files = new FastList<>();
		hashFiles("data/stats/items", files);
		if (Config.CUSTOM_ITEMS_LOAD)
			hashFiles("data/stats/items/custom", files);
		
		StringIntern.begin(ItemTable.class.getSimpleName());
		for (File f : files)
		{
			DocumentItem document = new DocumentItem(f);
			document.parse();
			//[JOJO]-------------------------------------------------
			for (L2Item item : document.getItemList())
				if (list.put(item.getId(), item) != null)
					_log.log(Level.INFO, "<!>" + ItemTable.class.getSimpleName() + ": file '" + f.getPath() + "' override the item " + item.getItemType() + " " + item.getId() + " " + item.getName());	//[JOJO]
			//-------------------------------------------------------
		//	list.addAll(document.getItemList());
		}
		StringIntern.end();
		return new FastList<>(list.values());
	}
	
	private static class SingletonHolder
	{
		protected static final DocumentEngine _instance = new DocumentEngine();
	}
}
