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
package com.l2jserver.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import jp.sf.l2j.troja.FastIntObjectMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.entity.Instance;
import com.l2jserver.gameserver.model.instancezone.InstanceWorld;

/**
 * @author evill33t, GodKratos
 */
public final class InstanceManager extends DocumentParser
{
	private static final FastIntObjectMap<Instance> _instanceList = new FastIntObjectMap<>();
	private final FastIntObjectMap<InstanceWorld> _instanceWorlds = new FastIntObjectMap<>();
	private int _dynamic = 300000;
	// InstanceId Names
	private static String[] _instanceIdNames;
	private final FastIntObjectMap<FastIntObjectMap<Long>> _playerInstanceTimes = new FastIntObjectMap<>();
	// SQL Queries
	private static final String ADD_INSTANCE_TIME = "INSERT INTO character_instance_time (charId,instanceId,time) values (?,?,?) ON DUPLICATE KEY UPDATE time=?";
	private static final String RESTORE_INSTANCE_TIMES = "SELECT instanceId,time FROM character_instance_time WHERE charId=?";
	private static final String DELETE_INSTANCE_TIME = "DELETE FROM character_instance_time WHERE charId=? AND instanceId=?";
	
	protected InstanceManager()
	{
		// Creates the multiverse.
		_instanceList.put(-1, new Instance(-1, "multiverse"));
		_log.info(getClass().getSimpleName() + ": Multiverse Instance created.");
		// Creates the universe.
		_instanceList.put(0, new Instance(0, "universe"));
		_log.info(getClass().getSimpleName() + ": Universe Instance created.");
		load();
	}
	
	@Override
	public void load()
	{
		_instanceIdNames = new String[0];
		parseDatapackFile("data/instancenames.xml");
		_log.info(getClass().getSimpleName() + ": Loaded " + _instanceIdNames.length + " instance names.");
	}
	
	/**
	 * @param playerObjId
	 * @param id
	 * @return
	 */
	public long getInstanceTime(int playerObjId, int id)
	{
		restoreInstanceTimes(playerObjId);
		Long time = _playerInstanceTimes.get(playerObjId).get(id);
		if (time != null)
			return time;
		else
			return -1;
	}
	
	/**
	 * @param playerObjId
	 * @return
	 */
	public FastIntObjectMap<Long> getAllInstanceTimes(int playerObjId)
	{
		restoreInstanceTimes(playerObjId);
		return _playerInstanceTimes.get(playerObjId);
	}
	
	/**
	 * @param playerObjId
	 * @param id
	 * @param time
	 */
	public void setInstanceTime(int playerObjId, int id, long time)
	{
		restoreInstanceTimes(playerObjId);
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(ADD_INSTANCE_TIME))
		{
			ps.setInt(1, playerObjId);
			ps.setInt(2, id);
			ps.setLong(3, time);
			ps.setLong(4, time);
			ps.execute();
			_playerInstanceTimes.get(playerObjId).put(id, time);
		}
		catch (Exception e)
		{
			_log.warning(getClass().getSimpleName() + ": Could not insert character instance time data: " + e.getMessage());
		}
	}
	
	/**
	 * @param playerObjId
	 * @param id
	 */
	public void deleteInstanceTime(int playerObjId, int id)
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(DELETE_INSTANCE_TIME))
		{
			ps.setInt(1, playerObjId);
			ps.setInt(2, id);
			ps.execute();
			_playerInstanceTimes.get(playerObjId).remove(id);
		}
		catch (Exception e)
		{
			_log.warning(getClass().getSimpleName() + ": Could not delete character instance time data: " + e.getMessage());
		}
	}
	
	/**
	 * @param playerObjId
	 */
	public void restoreInstanceTimes(int playerObjId)
	{
		if (_playerInstanceTimes.containsKey(playerObjId))
		{
			return; // already restored
		}
		final FastIntObjectMap<Long> times = new FastIntObjectMap<>();
		_playerInstanceTimes.put(playerObjId, times);
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement(RESTORE_INSTANCE_TIMES))
		{
			ps.setInt(1, playerObjId);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					int id = rs.getInt("instanceId");
					long time = rs.getLong("time");
					if (time < System.currentTimeMillis())
					{
						deleteInstanceTime(playerObjId, id);
					}
					else
					{
						times.put(id, time);
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.warning(getClass().getSimpleName() + ": Could not delete character instance time data: " + e.getMessage());
		}
	}
	
	/**
	 * @param id
	 * @return
	 */
	public String getInstanceIdName(int id)
	{
		String name;
		return  id < 0 || id >= _instanceIdNames.length || (name = _instanceIdNames[id]) == null ? "UnknownInstance" : name;
	}
	
	@Override
	protected void parseDocument()
	{
		for (Node n = getCurrentDocument().getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equals(n.getNodeName()))
			{
				NamedNodeMap attrs;
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("instance".equals(d.getNodeName()))
					{
						attrs = d.getAttributes();
						final int id = parseInteger(attrs, "id");
						final String name = attrs.getNamedItem("name").getNodeValue();
						if (_instanceIdNames.length <= id)
							_instanceIdNames = Arrays.copyOf(_instanceIdNames, id + 1);
						_instanceIdNames[id] = name;
					}
				}
			}
		}
	}
	
	/**
	 * @param world
	 */
	public void addWorld(InstanceWorld world)
	{
		_instanceWorlds.put(world.getInstanceId(), world);
	}
	
	/**
	 * @param instanceId
	 * @return
	 */
	public InstanceWorld getWorld(int instanceId)
	{
		return _instanceWorlds.get(instanceId);
	}
	
	/**
	 * Check if the player have a World Instance where it's allowed to enter.
	 * @param player the player to check
	 * @return the instance world
	 */
	public InstanceWorld getPlayerWorld(L2PcInstance player)
	{
		for (InstanceWorld temp : _instanceWorlds.values())
		{
			if ((temp != null) && (temp.isAllowed(player.getObjectId())))
			{
				return temp;
			}
		}
		return null;
	}
	
	/**
	 * @param instanceid
	 */
	public void destroyInstance(int instanceid)
	{
		if (instanceid <= 0)
		{
			return;
		}
		_instanceWorlds.remove(instanceid);
		final Instance temp = _instanceList.remove(instanceid);
		if (temp != null)
		{
			temp.cancelQuestTimers();
			temp.removeNpcs();
			temp.removePlayers();
			temp.removeDoors();
			temp.cancelTimer();
		}
	}
	
	/**
	 * @param instanceid
	 * @return
	 */
	public Instance getInstance(int instanceid)
	{
		return _instanceList.get(instanceid);
	}
	
	/**
	 * @return
	 */
	public FastIntObjectMap<Instance> getInstances()
	{
		return _instanceList;
	}
	
	/**
	 * @param objectId
	 * @return
	 */
	public int getPlayerInstance(int objectId)
	{
		for (Instance temp : _instanceList.values())
		{
			if (temp == null)
			{
				continue;
			}
			// check if the player is in any active instance
			if (temp.containsPlayer(objectId))
			{
				return temp.getId();
			}
		}
		// 0 is default instance aka the world
		return 0;
	}
	
	/**
	 * @param id
	 * @return
	 */
	public boolean createInstance(int id)
	{
		if (getInstance(id) != null)
		{
			return false;
		}
		
		final Instance instance = new Instance(id);
		_instanceList.put(id, instance);
		return true;
	}
	
	/**
	 * @param id
	 * @param template
	 * @return
	 */
	public boolean createInstanceFromTemplate(int id, String template)
	{
		if (getInstance(id) != null)
		{
			return false;
		}
		
		final Instance instance = new Instance(id);
		_instanceList.put(id, instance);
		instance.loadInstanceTemplate(template);
		return true;
	}
	
	/**
	 * Create a new instance with a dynamic instance id based on a template (or null)
	 * @param template xml file
	 * @return
	 */
	public int createDynamicInstance(String template)
	{
		while (getInstance(_dynamic) != null)
		{
			_dynamic++;
			if (_dynamic == Integer.MAX_VALUE)
			{
				_log.warning(getClass().getSimpleName() + ": More then " + (Integer.MAX_VALUE - 300000) + " instances created");
				_dynamic = 300000;
			}
		}
		final Instance instance = new Instance(_dynamic);
		_instanceList.put(_dynamic, instance);
		if (template != null)
		{
			instance.loadInstanceTemplate(template);
		}
		return _dynamic++;	//[JOJO]
	//	return _dynamic;
	}
	
	/**
	 * Gets the single instance of {@code InstanceManager}.
	 * @return single instance of {@code InstanceManager}
	 */
	public static final InstanceManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final InstanceManager _instance = new InstanceManager();
	}
}
