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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.EnchantItem;
import com.l2jserver.gameserver.model.EnchantScroll;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;

/**
 * @author UnAfraid
 */
public class EnchantItemData extends DocumentParser
{
	public final Map<Integer, EnchantScroll> _scrolls;
	public final Map<Integer, EnchantItem> _supports;
	
	public EnchantItemData()
	{
		_scrolls = new HashMap<>();
		_supports = new HashMap<>();
		load();
	}
	
	public void load()
	{
		_scrolls.clear();
		_supports.clear();
		parseDatapackFile("data/enchantData.xml");
		_log.info(getClass().getSimpleName() + ": Loaded " + _scrolls.size() + " Enchant Scrolls");
		_log.info(getClass().getSimpleName() + ": Loaded " + _supports.size() + " Support Items");
	}
	
	@Override
	protected void parseDocument(Document doc)
	{
		StatsSet set;
		Node att;
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("enchant".equalsIgnoreCase(d.getNodeName()))
					{
						NamedNodeMap attrs = d.getAttributes();
						set = new StatsSet();
						for (int i = 0; i < attrs.getLength(); i++)
						{
							att = attrs.item(i);
							set.set(att.getNodeName(), att.getNodeValue());
						}
						
						List<Integer> items = new ArrayList<Integer>();
						
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
						{
							if ("item".equalsIgnoreCase(cd.getNodeName()))
							{
								items.add(parseInt(cd.getAttributes(), "id"));
							}
						}
						EnchantScroll item = new EnchantScroll(set, items);
						_scrolls.put(item.getScrollId(), item);
					}
					else if ("support".equalsIgnoreCase(d.getNodeName()))
					{
						NamedNodeMap attrs = d.getAttributes();
						
						set = new StatsSet();
						for (int i = 0; i < attrs.getLength(); i++)
						{
							att = attrs.item(i);
							set.set(att.getNodeName(), att.getNodeValue());
						}
						
						List<Integer> items = new ArrayList<Integer>();
						
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
						{
							if ("item".equalsIgnoreCase(cd.getNodeName()))
							{
								items.add(parseInt(cd.getAttributes(), "id"));
							}
						}
						EnchantItem item = new EnchantItem(set, items);
						_supports.put(item.getScrollId(), item);
					}
				}
			}
		}
	}
	
	/**
	 * @param scroll
	 * @return enchant template for scroll
	 */
	public final EnchantScroll getEnchantScroll(L2ItemInstance scroll)
	{
		return _scrolls.get(scroll.getItemId());
	}
	
	/**
	 * @param item
	 * @return enchant template for support item
	 */
	public final EnchantItem getSupportItem(L2ItemInstance item)
	{
		return _supports.get(item.getItemId());
	}
	
	public static final EnchantItemData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final EnchantItemData _instance = new EnchantItemData();
	}
}
