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
package com.l2jserver.gameserver.datatables;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.enums.CategoryType;

/**
 * This class holds different categories containing class ids or npc ids.
 * @author Nos, xban1x
 */
public final class CategoryData extends DocumentParser
{
	private static final Logger _log = Logger.getLogger(CategoryData.class.getName());
	
	private final HashMap<CategoryType, int[]> _categories = new HashMap<>();
	
	protected CategoryData()
	{
		load();
	}
	
	@Override
	public void load()
	{
		parseDatapackFile("data/categoryData.xml");
		_log.info(getClass().getSimpleName() + ": Loaded " + _categories.size() + " Categories.");
	}
	
	@Override
	protected void parseDocument()
	{
		for (Node node = getCurrentDocument().getFirstChild(); node != null; node = node.getNextSibling())
		{
			if ("list".equalsIgnoreCase(node.getNodeName()))
			{
				for (Node list_node = node.getFirstChild(); list_node != null; list_node = list_node.getNextSibling())
				{
					if ("category".equalsIgnoreCase(list_node.getNodeName()))
					{
						final NamedNodeMap attrs = list_node.getAttributes();
						final CategoryType categoryType = CategoryType.findByName(attrs.getNamedItem("name").getNodeValue());
						if (categoryType == null)
						{
							_log.log(Level.WARNING, getClass().getSimpleName() + ": Can't find category by name :" + attrs.getNamedItem("name").getNodeValue());
							continue;
						}
						
						final HashSet<Integer> ids = new HashSet<>();
						for (Node category_node = list_node.getFirstChild(); category_node != null; category_node = category_node.getNextSibling())
						{
							if ("id".equalsIgnoreCase(category_node.getNodeName()))
							{
								ids.add(Integer.parseInt(category_node.getTextContent()));
							}
						}
						
						int[] ida = new int[ids.size()];
						int index = 0;
						for (Integer id : ids)
							ida[index++] = id;
						Arrays.sort(ida);
						_categories.put(categoryType, ida);
					}
				}
			}
		}
	}
	
	/**
	 * Checks if id is in category.
	 * @param type The category type.
	 * @param id The id to be checked.
	 * @return {@code true} if id is in category, {@code false} if id is not in category or category was not found.
	 */
	public boolean isInCategory(CategoryType type, int id)
	{
		final int[] category = _categories.get(type);
		if (category == null)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Can't find category type :" + type);
			return false;
		}
		return Arrays.binarySearch(category, id) >= 0;
	}
	
	public static CategoryData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected final static CategoryData _instance = new CategoryData();
	}
}
