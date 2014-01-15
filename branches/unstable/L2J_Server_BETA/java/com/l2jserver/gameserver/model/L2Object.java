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
package com.l2jserver.gameserver.model;

import java.util.Map;

import javolution.util.FastMap;

import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.enums.ShotType;
import com.l2jserver.gameserver.handler.ActionHandler;
import com.l2jserver.gameserver.handler.ActionShiftHandler;
import com.l2jserver.gameserver.handler.IActionHandler;
import com.l2jserver.gameserver.idfactory.IdFactory;
import com.l2jserver.gameserver.instancemanager.InstanceManager;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.knownlist.ObjectKnownList;
import com.l2jserver.gameserver.model.actor.poly.ObjectPoly;
import com.l2jserver.gameserver.model.entity.Instance;
import com.l2jserver.gameserver.model.interfaces.IDecayable;
import com.l2jserver.gameserver.model.interfaces.IIdentifiable;
import com.l2jserver.gameserver.model.interfaces.ILocational;
import com.l2jserver.gameserver.model.interfaces.INamable;
import com.l2jserver.gameserver.model.interfaces.IPositionable;
import com.l2jserver.gameserver.model.interfaces.ISpawnable;
import com.l2jserver.gameserver.model.interfaces.IUniqueId;
import com.l2jserver.gameserver.model.zone.ZoneId;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.network.serverpackets.ExSendUIEvent;
import com.l2jserver.gameserver.network.serverpackets.L2GameServerPacket;

/**
 * Base class for all interactive objects.
 * @author unknown
 */
public abstract class L2Object implements IIdentifiable, INamable, ISpawnable, IUniqueId, IDecayable, IPositionable
{
	private boolean _isVisible;
	private ObjectKnownList _knownList;
	private String _name;
	private int _objectId;
	private L2WorldRegion _worldRegion;
	private InstanceType _instanceType = null;
	private volatile Map<String, Object> _scripts;
	
	private int _x;
	private int _y;
	private int _z;
	private int _heading;
	private int _instanceId;
	
	public L2Object(int objectId)
	{
		setInstanceType(InstanceType.L2Object);
		_objectId = objectId;
		initKnownList();
	}
	
	protected final void setInstanceType(InstanceType i)
	{
		_instanceType = i;
	}
	
	public final InstanceType getInstanceType()
	{
		return _instanceType;
	}
	
	public final boolean isInstanceType(InstanceType i)
	{
		return _instanceType.isType(i);
	}
	
	public final boolean isInstanceTypes(InstanceType... i)
	{
		return _instanceType.isTypes(i);
	}
	
	public final void onAction(L2PcInstance player)
	{
		onAction(player, true);
	}
	
	public void onAction(L2PcInstance player, boolean interact)
	{
		IActionHandler handler = ActionHandler.getInstance().getHandler(getInstanceType());
		if (handler != null)
		{
			handler.action(player, this, interact);
		}
		
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void onActionShift(L2PcInstance player)
	{
		IActionHandler handler = ActionShiftHandler.getInstance().getHandler(getInstanceType());
		if (handler != null)
		{
			handler.action(player, this, true);
		}
		
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void onForcedAttack(L2PcInstance player)
	{
		player.sendPacket(ActionFailed.STATIC_PACKET);
	}
	
	public void onSpawn()
	{
	}
	
	/**
	 * UnAfraid: TODO: Add listener here.
	 * @param instanceId The id of the instance zone the object is in - id 0 is global
	 */
	@Override
	public void setInstanceId(int instanceId)
	{
		if ((instanceId < 0) || (getInstanceId() == instanceId))
		{
			return;
		}
		
		Instance oldI = InstanceManager.getInstance().getInstance(getInstanceId());
		Instance newI = InstanceManager.getInstance().getInstance(instanceId);
		if (newI == null)
		{
			return;
		}
		
		if (isPlayer())
		{
			final L2PcInstance player = getActingPlayer();
			if ((getInstanceId() > 0) && (oldI != null))
			{
				oldI.removePlayer(getObjectId());
				if (oldI.isShowTimer())
				{
					hideInstanceTimer();	//[JOJO]
				}
			}
			if (instanceId > 0)
			{
				newI.addPlayer(getObjectId());
				if (newI.isShowTimer())
				{
					showInstanceTimer(newI);	//[JOJO]
				}
			}
			if (player.hasSummon())
			{
				player.getSummon().setInstanceId(instanceId);
			}
		}
		else if (isNpc())
		{
			final L2Npc npc = (L2Npc) this;
			if ((getInstanceId() > 0) && (oldI != null))
			{
				oldI.removeNpc(npc);
			}
			if (instanceId > 0)
			{
				newI.addNpc(npc);
			}
		}
		
		_instanceId = instanceId;
		if (_isVisible && (_knownList != null))
		{
			// We don't want some ugly looking disappear/appear effects, so don't update
			// the knownlist here, but players usually enter instancezones through teleporting
			// and the teleport will do the revalidation for us.
			if (!isPlayer())
			{
				decayMe();
				spawnMe();
			}
		}
	}
	
	//[JOJO]-------------------------------------------------
	public void showInstanceTimer(Instance instance)
	{
		final int npcString;
		String timerText = instance.getTimerText();
		if (timerText != null && timerText.startsWith("#"))	// ex: <showTimer val="true" increase="true" text="#1911119" />
		{
			npcString = 
				  timerText.equals("#1911119") ? 1911119	// "経過時間："
				: timerText.equals("#1911120") ? 1911120	// "残り時間："
				: Integer.parseInt(timerText.substring(1));
			timerText = null;
		}
		else
		{
			npcString = -1;
		}
		final int start, end;
		if (instance.isTimerIncrease()) // count up
		{
			start = (int) ((System.currentTimeMillis() - instance.getInstanceStartTime()) / 1000);
			end = (int) ((instance.getInstanceEndTime() - instance.getInstanceStartTime()) / 1000);
			assert start <= end;
			assert start >= 0;
			assert end >= 0;
		}
		else // count down
		{
			end = 0;
			start = (int) ((instance.getInstanceEndTime() - System.currentTimeMillis()) / 1000);
			assert end <= start;
			assert start >= 0;
		}
		sendPacket(new ExSendUIEvent(getActingPlayer(), false, instance.isTimerIncrease(), start, end, timerText, npcString, null));
	}
	
	public void hideInstanceTimer()
	{
		sendPacket(new ExSendUIEvent(getActingPlayer(), true, true, 0, 0, -1));
	}
	//-------------------------------------------------------
	
	@Override
	public boolean decayMe()
	{
		assert getWorldRegion() != null;
		
		L2WorldRegion reg = getWorldRegion();
		
		synchronized (this)
		{
			_isVisible = false;
			setWorldRegion(null);
		}
		
		// this can synchronize on others instances, so it's out of
		// synchronized, to avoid deadlocks
		// Remove the L2Object from the world
		L2World.getInstance().removeVisibleObject(this, reg);
		L2World.getInstance().removeObject(this);
		
		return true;
	}
	
	public void refreshID()
	{
		L2World.getInstance().removeObject(this);
		IdFactory.getInstance().releaseId(getObjectId());
		_objectId = IdFactory.getInstance().getNextId();
	}
	
	@Override
	public final boolean spawnMe()
	{
		assert (getWorldRegion() == null) && (getPosition().getX() != 0) && (getPosition().getY() != 0) && (getPosition().getZ() != 0);
		
		synchronized (this)
		{
			// Set the x,y,z position of the L2Object spawn and update its _worldregion
			_isVisible = true;
			setWorldRegion(L2World.getInstance().getRegion(getPosition()));
			
			// Add the L2Object spawn in the _allobjects of L2World
			L2World.getInstance().storeObject(this);
			
			// Add the L2Object spawn to _visibleObjects and if necessary to _allplayers of its L2WorldRegion
			getWorldRegion().addVisibleObject(this);
		}
		
		// this can synchronize on others instances, so it's out of synchronized, to avoid deadlocks
		// Add the L2Object spawn in the world as a visible object
		L2World.getInstance().addVisibleObject(this, getWorldRegion());
		
		onSpawn();
		
		return true;
	}
	
	public final void spawnMe(int x, int y, int z)
	{
		assert getWorldRegion() == null;
		
		synchronized (this)
		{
			// Set the x,y,z position of the L2Object spawn and update its _worldregion
			_isVisible = true;
			
			if (x > L2World.MAP_MAX_X)
			{
				x = L2World.MAP_MAX_X - 5000;
			}
			if (x < L2World.MAP_MIN_X)
			{
				x = L2World.MAP_MIN_X + 5000;
			}
			if (y > L2World.MAP_MAX_Y)
			{
				y = L2World.MAP_MAX_Y - 5000;
			}
			if (y < L2World.MAP_MIN_Y)
			{
				y = L2World.MAP_MIN_Y + 5000;
			}
			
			setXYZ(x, y, z);
			setWorldRegion(L2World.getInstance().getRegion(getPosition()));
			
			// Add the L2Object spawn in the _allobjects of L2World
		}
		
		L2World.getInstance().storeObject(this);
		
		// these can synchronize on others instances, so they're out of
		// synchronized, to avoid deadlocks
		
		// Add the L2Object spawn to _visibleObjects and if necessary to _allplayers of its L2WorldRegion
		getWorldRegion().addVisibleObject(this);
		
		// Add the L2Object spawn in the world as a visible object
		L2World.getInstance().addVisibleObject(this, getWorldRegion());
		
		onSpawn();
	}
	
	public boolean isAttackable()
	{
		return false;
	}
	
	public abstract boolean isAutoAttackable(L2Character attacker);
	
	public final boolean isVisible()
	{
		return getWorldRegion() != null;
	}
	
	public final void setIsVisible(boolean value)
	{
		_isVisible = value;
		if (!_isVisible)
		{
			setWorldRegion(null);
		}
	}
	
	public void toggleVisible()
	{
		if (isVisible())
		{
			decayMe();
		}
		else
		{
			spawnMe();
		}
	}
	
	public ObjectKnownList getKnownList()
	{
		return _knownList;
	}
	
	public void initKnownList()
	{
		_knownList = new ObjectKnownList(this);
	}
	
	public final void setKnownList(ObjectKnownList value)
	{
		_knownList = value;
	}
	
	@Override
	public final String getName()
	{
		return _name;
	}
	
	public void setName(String value)
	{
		_name = value;
	}
	
	@Override
	public final int getObjectId()
	{
		return _objectId;
	}
	
	public final ObjectPoly getPoly()
	{
		final ObjectPoly poly = getScript(ObjectPoly.class);
		return (poly == null) ? addScript(new ObjectPoly(this)) : poly;
	}
	
	public abstract void sendInfo(L2PcInstance activeChar);
	
	public void sendPacket(L2GameServerPacket mov)
	{
	}
	
	public void sendPacket(SystemMessageId id)
	{
	}
	
	public L2PcInstance getActingPlayer()
	{
		return null;
	}
	
	/**
	 * @return {@code true} if object is instance of L2PcInstance
	 */
	public boolean isPlayer()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2Playable
	 */
	public boolean isPlayable()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2Summon
	 */
	public boolean isSummon()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2PetInstance
	 */
	public boolean isPet()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2ServitorInstance
	 */
	public boolean isServitor()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2Attackable
	 */
	public boolean isCharacter()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2DoorInstance
	 */
	public boolean isDoor()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2Npc
	 */
	public boolean isNpc()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2Attackable
	 */
	public boolean isL2Attackable()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2MonsterInstance
	 */
	public boolean isMonster()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2TrapInstance
	 */
	public boolean isTrap()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object is instance of L2ItemInstance
	 */
	public boolean isItem()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object Npc Walker or Vehicle
	 */
	public boolean isWalker()
	{
		return false;
	}
	
	/**
	 * @return {@code true} if object Can be targeted
	 */
	public boolean isTargetable()
	{
		return true;
	}
	
	/**
	 * Check if the object is in the given zone Id.
	 * @param zone the zone Id to check
	 * @return {@code true} if the object is in that zone Id
	 */
	public boolean isInsideZone(ZoneId zone)
	{
		return false;
	}
	
	/**
	 * Check if current object has charged shot.
	 * @param type of the shot to be checked.
	 * @return {@code true} if the object has charged shot
	 */
	public boolean isChargedShot(ShotType type)
	{
		return false;
	}
	
	/**
	 * Charging shot into the current object.
	 * @param type of the shot to be charged.
	 * @param charged
	 */
	public void setChargedShot(ShotType type, boolean charged)
	{
	}
	
	/**
	 * Try to recharge a shot.
	 * @param physical skill are using Soul shots.
	 * @param magical skill are using Spirit shots.
	 */
	public void rechargeShots(boolean physical, boolean magical)
	{
	}
	
	/**
	 * @param <T>
	 * @param script
	 * @return
	 */
	public final <T> T addScript(T script)
	{
		if (_scripts == null)
		{
			// Double-checked locking
			synchronized (this)
			{
				if (_scripts == null)
				{
					_scripts = new FastMap<String, Object>().shared();
				}
			}
		}
		_scripts.put(script.getClass().getName(), script);
		return script;
	}
	
	/**
	 * @param <T>
	 * @param script
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <T> T removeScript(Class<T> script)
	{
		if (_scripts == null)
		{
			return null;
		}
		return (T) _scripts.remove(script.getName());
	}
	
	/**
	 * @param <T>
	 * @param script
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public final <T> T getScript(Class<T> script)
	{
		if (_scripts == null)
		{
			return null;
		}
		return (T) _scripts.get(script.getName());
	}
	
	public void removeStatusListener(L2Character object)
	{
		
	}
	
	@Override
	public final void setXYZ(int x, int y, int z)
	{
		assert getWorldRegion() != null;
		
		setX(x);
		setY(y);
		setZ(z);
		
		try
		{
			if (L2World.getInstance().getRegion(getPosition()) != getWorldRegion())
			{
				updateWorldRegion();
			}
		}
		catch (Exception e)
		{
			badCoords();
		}
	}
	
	protected void badCoords()
	{
		if (isCharacter())
		{
			this.decayMe();
		}
		else if (isPlayer())
		{
			((L2Character) this).teleToLocation(0, 0, 0, false);
			((L2Character) this).sendMessage("Error with your coords, Please ask a GM for help!");
		}
	}
	
	public final void setXYZInvisible(int x, int y, int z)
	{
		assert getWorldRegion() == null;
		if (x > L2World.MAP_MAX_X)
		{
			x = L2World.MAP_MAX_X - 5000;
		}
		if (x < L2World.MAP_MIN_X)
		{
			x = L2World.MAP_MIN_X + 5000;
		}
		if (y > L2World.MAP_MAX_Y)
		{
			y = L2World.MAP_MAX_Y - 5000;
		}
		if (y < L2World.MAP_MIN_Y)
		{
			y = L2World.MAP_MIN_Y + 5000;
		}
		
		setXYZ(x, y, z);
		this.setIsVisible(false);
	}
	
	public final void setLocationInvisible(ILocational loc)
	{
		setXYZInvisible(loc.getX(), loc.getY(), loc.getZ());
	}
	
	public void updateWorldRegion()
	{
		if (!this.isVisible())
		{
			return;
		}
		
		L2WorldRegion newRegion = L2World.getInstance().getRegion(getPosition());
		if (newRegion != getWorldRegion())
		{
			getWorldRegion().removeVisibleObject(this);
			
			setWorldRegion(newRegion);
			
			// Add the L2Oject spawn to _visibleObjects and if necessary to _allplayers of its L2WorldRegion
			getWorldRegion().addVisibleObject(this);
		}
	}
	
	public final L2WorldRegion getWorldRegion()
	{
		return _worldRegion;
	}
	
	public void setWorldRegion(L2WorldRegion value)
	{
		if ((getWorldRegion() != null) && isCharacter()) // confirm revalidation of old region's zones
		{
			if (value != null)
			{
				getWorldRegion().revalidateZones((L2Character) this); // at world region change
			}
			else
			{
				getWorldRegion().removeFromZones((L2Character) this); // at world region change
			}
		}
		
		_worldRegion = value;
	}
	
	/**
	 * Calculates distance between this L2Object and given x, y , z.
	 * @param x - X coordinate.
	 * @param y - Y coordinate.
	 * @param z - Z coordinate.
	 * @param includeZAxis - If set to true, Z coordinate will be included.
	 * @param squared - If set to true, distance returned will be squared.
	 * @return {@code double} - Distance between object and given x, y , z.
	 */
	public double calculateDistance(int x, int y, int z, boolean includeZAxis, boolean squared)
	{
		final double distance = Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2) + (includeZAxis ? Math.pow(z - getZ(), 2) : 0);
		return (squared) ? distance : Math.sqrt(distance);
	}
	
	/**
	 * Calculates distance between this L2Object and given location.
	 * @param loc - Location on map.
	 * @param includeZAxis - If set to true, Z coordinate will be included.
	 * @param squared - If set to true, distance returned will be squared.
	 * @return {@code double} - Distance between object and given location.
	 */
	public double calculateDistance(ILocational loc, boolean includeZAxis, boolean squared)
	{
		return calculateDistance(loc.getX(), loc.getY(), loc.getZ(), includeZAxis, squared);
	}
	
	@Override
	public int getX()
	{
		return _x;
	}
	
	@Override
	public int getY()
	{
		return _y;
	}
	
	@Override
	public int getZ()
	{
		return _z;
	}
	
	@Override
	public int getHeading()
	{
		return _heading;
	}
	
	@Override
	public int getInstanceId()
	{
		return _instanceId;
	}
	
	@Override
	public Location getLocation()
	{
		return new Location(getX(), getY(), getZ(), getHeading(), getInstanceId());
	}
	
	//[JOJO]-------------------------------------------------
	public final ILocational getPosition()
	{
if (com.l2jserver.Config.FIX_GETLOCATION) {{
		return this;
}} else {{
		return getLocation();
}}
	}
	
	public final void setPosition(ILocational loc)
	{
		_x = loc.getX();
		_y = loc.getY();
		_z = loc.getZ();
		_heading = loc.getHeading();
		_instanceId = loc.getInstanceId();
	}
	//-------------------------------------------------------
	
	@Override
	public void setX(int x)
	{
		_x = x;
	}
	
	@Override
	public void setY(int y)
	{
		_y = y;
	}
	
	@Override
	public void setZ(int z)
	{
		_z = z;
	}
	
	public void setXYZ(ILocational loc)
	{
		_x = loc.getX();
		_y = loc.getY();
		_z = loc.getZ();
	}
	
	@Override
	public void setHeading(int heading)
	{
		_heading = heading;
	}
	
	@Override
	public void setLocation(Location loc)
	{
		_x = loc.getX();
		_y = loc.getY();
		_z = loc.getZ();
		_heading = loc.getHeading();
		_instanceId = loc.getInstanceId();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		return ((obj instanceof L2Object) && (((L2Object) obj).getObjectId() == getObjectId()));
	}
	
	@Override
	public String toString()
	{
		return (getClass().getSimpleName() + ":" + getName() + "[" + getObjectId() + "]");
	}
}