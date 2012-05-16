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

import gnu.trove.map.hash.TIntObjectHashMap;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.base.ClassId;
import com.l2jserver.gameserver.model.base.ClassInfo;
import com.l2jserver.gameserver.model.base.PlayerClass;

/**
 * This class holds the list of classes and it's info.<br>
 * It's in <i>beta</i> state, so it's expected to change over time.
 * @author Zoey76
 */
public final class ClassListData extends DocumentParser
{
	private static final TIntObjectHashMap<ClassInfo> _classData = new TIntObjectHashMap<ClassInfo>();
	
	protected ClassListData()
	{
		load();
	}
	
	@Override
	public void load()
	{
		_classData.clear();
		parseDatapackFile("data/stats/chars/classList.xml");
		_log.info(getClass().getSimpleName() + ": Loaded " + _classData.size() + " Class data.");
	}
	
	@Override
	protected void parseDocument(Document doc)
	{
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equals(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					NamedNodeMap attrs = d.getAttributes();
					if ("class".equals(d.getNodeName()))
					{
						Node attr;
						attr = attrs.getNamedItem("classId");
						ClassId classId = ClassId.getClassId(parseInt(attr));
						attr = attrs.getNamedItem("name");
						String className = attr.getNodeValue();
						attr = attrs.getNamedItem("serverName");
						String classServName = attr.getNodeValue();
						attr = attrs.getNamedItem("parentClassId");
						ClassId parentClassId = (attr != null) ? ClassId.getClassId(parseInt(attr)) : null;
						_classData.put(classId.getId(), new ClassInfo(classId, className, classServName, parentClassId));
					}
				}
			}
		}
	}
	
	/**
	 * @param classId the class Id.
	 * @return the class info related to the given {@code classId}.
	 */
	public ClassInfo getClass(final ClassId classId)
	{
		return _classData.get(classId.getId());
	}
	
	public ClassInfo getClass(final PlayerClass playerClass)	//[JOJO]
	{
		return _classData.get(playerClass.getId());
	}
	
	/**
	 * @param classId the class Id as integer.
	 * @return the class info related to the given {@code classId}.
	 */
	public ClassInfo getClass(final int classId)
	{
		return _classData.get(classId);
	}
	
	public static ClassListData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final ClassListData _instance = new ClassListData();
	}
}