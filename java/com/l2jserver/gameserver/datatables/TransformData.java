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
package com.l2jserver.gameserver.datatables;

import static com.l2jserver.util.Util.*;

import java.util.logging.Level;

import jp.sf.l2j.arrayMaps.SortedIntObjectArrayMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.transform.Transform;
import com.l2jserver.gameserver.model.actor.transform.TransformLevelData;
import com.l2jserver.gameserver.model.actor.transform.TransformTemplate;
import com.l2jserver.gameserver.model.holders.AdditionalItemHolder;
import com.l2jserver.gameserver.model.holders.AdditionalSkillHolder;
import com.l2jserver.gameserver.model.holders.SkillHolder;
import com.l2jserver.gameserver.network.serverpackets.ExBasicActionList;

/**
 * @author UnAfraid
 */
public final class TransformData extends DocumentParser
{
	private final SortedIntObjectArrayMap<Transform> _transformData = new SortedIntObjectArrayMap<>();	//[JOJO] -HashMap
	
	protected TransformData()
	{
		load();
	}
	
	@Override
	public synchronized void load()
	{
		long started = System.currentTimeMillis();
		_transformData.clear();
		parseDatapackDirectory("data/stats/transformations", false);
		_log.log(Level.INFO, getClass().getSimpleName() + ": Loaded: " + _transformData.size() + " transform templates. (" + strMillTime(System.currentTimeMillis() - started) + ")");
	}
	
	@Override
	protected void parseDocument()
	{
		NamedNodeMap attrs;
		Node att;
		StatsSet set;
		for (Node n = getCurrentDocument().getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("transform".equalsIgnoreCase(d.getNodeName()))
					{
						attrs = d.getAttributes();
						set = new StatsSet();
						for (int i = 0; i < attrs.getLength(); i++)
						{
							att = attrs.item(i);
							set.set(att.getNodeName(), att.getNodeValue());
						}
						final Transform transform = new Transform(set);
						for (Node cd = d.getFirstChild(); cd != null; cd = cd.getNextSibling())
						{
							final String nodeName = cd.getNodeName();
							if ("Male".equalsIgnoreCase(nodeName) || "Female".equalsIgnoreCase(nodeName) || "Both".equalsIgnoreCase(nodeName))
							{
								final int isMale = "Male".equalsIgnoreCase(nodeName) ? 1 : "Female".equalsIgnoreCase(nodeName) ? 2 : 0;
								TransformTemplate templateData = null;
								for (Node z = cd.getFirstChild(); z != null; z = z.getNextSibling())
								{
									switch (z.getNodeName())
									{
										case "common":
										{
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												switch (s.getNodeName())
												{
													case "base":
													case "stats":
													case "defense":
													case "magicDefense":
													case "collision":
													case "moving":
													{
														attrs = s.getAttributes();
														for (int i = 0; i < attrs.getLength(); i++)
														{
															att = attrs.item(i);
															set.set(att.getNodeName(), att.getNodeValue());
														}
														break;
													}
												}
											}
											templateData = new TransformTemplate(set);
											transform.setTemplate(isMale, templateData);
											break;
										}
										case "skills":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("skill".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													int skillId = parseInt(attrs, "id");
													int skillLevel = parseInt(attrs, "level");
													templateData.addSkill(new SkillHolder(skillId, skillLevel));
												}
											}
											break;
										}
										case "actions":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											set.set("actions", z.getTextContent());
											final int[] actions = set.getIntArray("actions", " ");
											templateData.setBasicActionList(new ExBasicActionList(actions));
											break;
										}
										case "additionalSkills":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("skill".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													int skillId = parseInt(attrs, "id");
													int skillLevel = parseInt(attrs, "level");
													int minLevel = parseInt(attrs, "minLevel");
													templateData.addAdditionalSkill(new AdditionalSkillHolder(skillId, skillLevel, minLevel));
												}
											}
											break;
										}
										case "items":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("item".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													int itemId = parseInt(attrs, "id");
													boolean allowed = parseBool(attrs, "allowed");
													templateData.addAdditionalItem(new AdditionalItemHolder(itemId, allowed));
												}
											}
											break;
										}
										case "levels":
										{
											if (templateData == null)
											{
												templateData = new TransformTemplate(set);
												transform.setTemplate(isMale, templateData);
											}
											
											final StatsSet levelsSet = new StatsSet();
											for (Node s = z.getFirstChild(); s != null; s = s.getNextSibling())
											{
												if ("level".equals(s.getNodeName()))
												{
													attrs = s.getAttributes();
													for (int i = 0; i < attrs.getLength(); i++)
													{
														att = attrs.item(i);
														levelsSet.set(att.getNodeName(), att.getNodeValue());
													}
												}
											}
											templateData.addLevelData(new TransformLevelData(levelsSet));
											break;
										}
									}
								}
							}
						}
						_transformData.put(transform.getId(), transform);
					}
				}
			}
		}
	}
	
	public Transform getTransform(int id)
	{
		return _transformData.get(id);
	}
	
	public boolean transformPlayer(int id, L2PcInstance player)
	{
		final Transform transform = getTransform(id);
		if (transform != null)
		{
			player.transform(transform);
		}
		return transform != null;
	}
	
	public static TransformData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final TransformData _instance = new TransformData();
	}
}
