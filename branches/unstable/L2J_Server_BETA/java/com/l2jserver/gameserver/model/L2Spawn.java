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
package com.l2jserver.gameserver.model;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;
import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.GeoData;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.NpcPersonalAIData;
import com.l2jserver.gameserver.datatables.TerritoryTable;
import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.idfactory.IdFactory;
import com.l2jserver.gameserver.model.actor.L2Attackable;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.interfaces.IIdentifiable;
import com.l2jserver.gameserver.model.interfaces.INamable;
import com.l2jserver.gameserver.model.stats.MoveType;
import com.l2jserver.gameserver.model.zone.type.NpcSpawnTerritory;
import com.l2jserver.util.Rnd;

/**
 * This class manages the spawn and respawn of a group of L2NpcInstance that are in the same are and have the same type.<br>
 * <B><U>Concept</U>:</B><br>
 * L2NpcInstance can be spawned either in a random position into a location area (if Lox=0 and Locy=0), either at an exact position.<br>
 * The heading of the L2NpcInstance can be a random heading if not defined (value= -1) or an exact heading (ex : merchant...).
 * @author Nightmare
 */
public class L2Spawn extends Location implements IIdentifiable, INamable	//[JOJO] -IPositionable +Location
{
	protected static final Logger _log = Logger.getLogger(L2Spawn.class.getName());
	
	/** String identifier of this spawn */
	private String _name;
	/** The link on the L2NpcTemplate object containing generic and static properties of this spawn (ex : RewardExp, RewardSP, AggroRange...) */
	private L2NpcTemplate _template;
	/** The maximum number of L2NpcInstance that can manage this L2Spawn */
	private int _maximumCount;
	/** The current number of L2NpcInstance managed by this L2Spawn */
	private int _currentCount;
	/** The current number of SpawnTask in progress or stand by of this L2Spawn */
	protected int _scheduledCount;
	/** The identifier of the location area where L2NpcInstance can be spwaned */
	private int _locationId;
 //	/** The Location of this NPC spawn. */
 //	private Location _location = new Location(0, 0, 0, 0, 0);	//[JOJO] --> super
	/** Link to NPC spawn territory */
	private NpcSpawnTerritory _spawnTerritory = null;
	/** Minimum respawn delay */
	private int _respawnMinDelay;
	/** Maximum respawn delay */
	private int _respawnMaxDelay;
	/** The generic constructor of L2NpcInstance managed by this L2Spawn */
	private Constructor<? extends L2Npc> _constructor;
	/** If True a L2NpcInstance is respawned each time that another is killed */
	private boolean _doRespawn;
	/** If true then spawn is custom */
	private boolean _customSpawn;
	private static List<SpawnListener> _spawnListeners = new FastList<>();
	private LinkedList<L2Npc> _spawnedNpcs;	//[JOJO] -final FastList
	private FastIntObjectMap<Location> _lastSpawnPoints;	//[JOJO] -ConcurrentHashMap
	private boolean _isNoRndWalk = false; // Is no random walk
	
	/** The task launching the function doSpawn() */
	class SpawnTask implements Runnable
	{
		private final L2Npc _oldNpc;
		
		public SpawnTask(L2Npc pOldNpc)
		{
			_oldNpc = pOldNpc;
		}
		
		@Override
		public void run()
		{
			try
			{
				// doSpawn();
				respawnNpc(_oldNpc);
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, "", e);
			}
			
			_scheduledCount--;
		}
	}
	
	/**
	 * Constructor of L2Spawn.<br>
	 * <B><U>Concept</U>:</B><br>
	 * Each L2Spawn owns generic and static properties (ex : RewardExp, RewardSP, AggroRange...).<br>
	 * All of those properties are stored in a different L2NpcTemplate for each type of L2Spawn. Each template is loaded once in the server cache memory (reduce memory use).<br>
	 * When a new instance of L2Spawn is created, server just create a link between the instance and the template.<br>
	 * This link is stored in <B>_template</B> Each L2NpcInstance is linked to a L2Spawn that manages its spawn and respawn (delay, location...).<br>
	 * This link is stored in <B>_spawn</B> of the L2NpcInstance.<br>
	 * <B><U> Actions</U>:</B><br>
	 * <ul>
	 * <li>Set the _template of the L2Spawn</li>
	 * <li>Calculate the implementationName used to generate the generic constructor of L2NpcInstance managed by this L2Spawn</li>
	 * <li>Create the generic constructor of L2NpcInstance managed by this L2Spawn</li>
	 * </ul>
	 * @param template The L2NpcTemplate to link to this L2Spawn
	 * @throws SecurityException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws ClassCastException when template type is not subclass of L2Npc
	 */
	public L2Spawn(L2NpcTemplate template) throws SecurityException, ClassNotFoundException, NoSuchMethodException, ClassCastException
	{
		super(0, 0, 0, 0, -1);	//[JOJO]
		// Set the _template of the L2Spawn
		_template = template;
		
		if (_template == null)
		{
			return;
		}
		
		String className = "com.l2jserver.gameserver.model.actor.instance." + _template.getType() + "Instance";
		
		// Create the generic constructor of L2Npc managed by this L2Spawn
		_constructor = Class.forName(className).asSubclass(L2Npc.class).getConstructor(int.class, L2NpcTemplate.class);
		
if (com.l2jserver.Config.NEVER_RandomWalk_IF_CORPSE) {{
		if (InstanceType.isType(template.getType(), InstanceType.L2Attackable)) {
			if (L2NpcTemplate.isNoRndWalk(template)) {
				if (com.l2jserver.Config.NEVER_RandomWalk_IF_CORPSE_LOG) System.out.println("__BASENAME__:__LINE__:(NonRandomWalkMonsters) canMove=" + template.canMove() + " baseWalkSpd=" + template.getBaseMoveSpeed(MoveType.WALK) + " baseRunSpd=" + template.getBaseMoveSpeed(MoveType.RUN) + " " + template.getRace().name() + " " + template.getAIType().name() + " " + template.getType() + " " + template.getNpcId() + " " + com.l2jserver.util.Util.concat_ws(" ", template.getTitle(), template.getName()));
				_isNoRndWalk = true;
			}
		}
		else {
			_isNoRndWalk = true;
		}
}}
		if (Arrays.binarySearch(Config.NON_RANDOM_WALK_MONSTERS, getNpcid()) >= 0)
			_isNoRndWalk = true;	//+[JOJO]
	}
	
	/**
	 * @return the maximum number of L2NpcInstance that this L2Spawn can manage.
	 */
	public int getAmount()
	{
		return _maximumCount;
	}
	
	/**
	 * @return the String Identifier of this spawn.
	 */
	@Override
	public String getName()
	{
		return _name;
	}
	
	/**
	 * Set the String Identifier of this spawn.
	 * @param name
	 */
	public void setName(String name)
	{
		_name = name;
	}
	
	/**
	 * @return the Identifier of the location area where L2NpcInstance can be spwaned.
	 */
	public int getLocationId()
	{
		return _locationId;
	}
	
	/**
	 * The Location of this NPC spawn.
	 */
	@Override
	public Location getLocation()
	{
		return this;
	}
	
	public Location getLocation(L2Object obj)
	{
		final Location p;
		return _lastSpawnPoints != null && obj != null && (p = _lastSpawnPoints.get(obj.getObjectId())) != null ? p : this;
	}
	
	/**
	 * @param obj object to check
	 * @return the X position of the last spawn point of given NPC.
	 */
	public int getX(L2Object obj)
	{
		return getLocation(obj).getX();
	}
	
	/**
	 * @param obj object to check
	 * @return the Y position of the last spawn point of given NPC.
	 */
	public int getY(L2Object obj)
	{
		return getLocation(obj).getY();
	}
	
	/**
	 * @param obj object to check
	 * @return the Z position of the last spawn point of given NPC.
	 */
	public int getZ(L2Object obj)
	{
		return getLocation(obj).getZ();
	}
	
	/**
	 * Gets the NPC ID.
	 * @return the NPC ID
	 */
	@Override
	public int getId()
	{
		return _template.getId();
	}
	
	public int getNpcid()
	{
		return getId();
	}
	
	/**
	 * @return min respawn delay.
	 */
	public int getRespawnMinDelay()
	{
		return _respawnMinDelay;
	}
	
	/**
	 * @return max respawn delay.
	 */
	public int getRespawnMaxDelay()
	{
		return _respawnMaxDelay;
	}
	
	/**
	 * Set the maximum number of L2NpcInstance that this L2Spawn can manage.
	 * @param amount
	 */
	public void setAmount(int amount)
	{
		if (amount <= 0) { _log.log(Level.WARNING, "NPC "+_template.getId()+_template.getName()+" setAmount("+amount+")"); Thread.dumpStack(); }	//[JOJO]
	//	if (amount < 1) amount = 1;	// [L2J_JP ADD SANDMAN]
		_maximumCount = amount;
	}
	
	/**
	 * Set the Identifier of the location area where L2NpcInstance can be spawned.
	 * @param id
	 */
	public void setLocationId(int id)
	{
		_locationId = id;
	}
	
	/**
	 * Set Minimum Respawn Delay.
	 * @param date
	 */
	public void setRespawnMinDelay(int date)
	{
		_respawnMinDelay = date;
	}
	
	/**
	 * Set Maximum Respawn Delay.
	 * @param date
	 */
	public void setRespawnMaxDelay(int date)
	{
		_respawnMaxDelay = date;
	}
	
	/**
	 * Set the spawn as custom.<BR>
	 * @param custom
	 */
	public void setCustom(boolean custom)
	{
		_customSpawn = custom;
	}
	
	/**
	 * @return type of spawn.
	 */
	public boolean isCustom()
	{
		return _customSpawn;
	}
	
	/**
	 * Decrease the current number of L2NpcInstance of this L2Spawn and if necessary create a SpawnTask to launch after the respawn Delay. <B><U> Actions</U> :</B> <li>Decrease the current number of L2NpcInstance of this L2Spawn</li> <li>Check if respawn is possible to prevent multiple respawning
	 * caused by lag</li> <li>Update the current number of SpawnTask in progress or stand by of this L2Spawn</li> <li>Create a new SpawnTask to launch after the respawn Delay</li> <FONT COLOR=#FF0000><B> <U>Caution</U> : A respawn is possible ONLY if _doRespawn=True and _scheduledCount +
	 * _currentCount < _maximumCount</B></FONT>
	 * @param oldNpc
	 */
	public void decreaseCount(L2Npc oldNpc)
	{
		// sanity check
		if (_currentCount <= 0)
		{
			return;
		}
		
		// Decrease the current number of L2NpcInstance of this L2Spawn
		_currentCount--;
		
		// Remove this NPC from list of spawned
		if (_spawnedNpcs != null) _spawnedNpcs.remove(oldNpc);
		
		// Remove spawn point for old NPC
		if (_lastSpawnPoints != null)
		{
			_lastSpawnPoints.remove(oldNpc.getObjectId());
		}
		
		// Check if respawn is possible to prevent multiple respawning caused by lag
		if (_doRespawn && ((_scheduledCount + _currentCount) < _maximumCount))
		{
			// Update the current number of SpawnTask in progress or stand by of this L2Spawn
			_scheduledCount++;
			
			// Create a new SpawnTask to launch after the respawn Delay
			// ClientScheduler.getInstance().scheduleLow(new SpawnTask(npcId), _respawnDelay);
			ThreadPoolManager.getInstance().scheduleGeneral(new SpawnTask(oldNpc), hasRespawnRandom() ? Rnd.get(_respawnMinDelay, _respawnMaxDelay) : _respawnMinDelay);
		}
	}
	
	/**
	 * Create the initial spawning and set _doRespawn to False, if respawn time set to 0, or set it to True otherwise.
	 * @return The number of L2NpcInstance that were spawned
	 */
	public int init()
	{
		while (_currentCount < _maximumCount)
		{
			doSpawn();
		}
		_doRespawn = _respawnMinDelay != 0;
		
		return _currentCount;
	}
	
	/**
	 * Create a L2NpcInstance in this L2Spawn.
	 * @param val
	 * @return
	 */
	public L2Npc spawnOne(boolean val)
	{
		return doSpawn(val);
	}
	
	/**
	 * @return true if respawn enabled
	 */
	public boolean isRespawnEnabled()
	{
		return _doRespawn;
	}
	
	/**
	 * Set _doRespawn to False to stop respawn in this L2Spawn.
	 */
	public void stopRespawn()
	{
		_doRespawn = false;
	}
	
	/**
	 * Set _doRespawn to True to start or restart respawn in this L2Spawn.
	 */
	public void startRespawn()
	{
		_doRespawn = true;
	}
	
	public L2Npc doSpawn()
	{
		return doSpawn(false);
	}
	
	/**
	 * Create the L2NpcInstance, add it to the world and lauch its OnSpawn action.<br>
	 * <B><U>Concept</U>:</B><br>
	 * L2NpcInstance can be spawned either in a random position into a location area (if Lox=0 and Locy=0), either at an exact position.<br>
	 * The heading of the L2NpcInstance can be a random heading if not defined (value= -1) or an exact heading (ex : merchant...).<br>
	 * <B><U>Actions for an random spawn into location area</U>:<I> (if Locx=0 and Locy=0)</I></B>
	 * <ul>
	 * <li>Get L2NpcInstance Init parameters and its generate an Identifier</li>
	 * <li>Call the constructor of the L2NpcInstance</li>
	 * <li>Calculate the random position in the location area (if Locx=0 and Locy=0) or get its exact position from the L2Spawn</li>
	 * <li>Set the position of the L2NpcInstance</li>
	 * <li>Set the HP and MP of the L2NpcInstance to the max</li>
	 * <li>Set the heading of the L2NpcInstance (random heading if not defined : value=-1)</li>
	 * <li>Link the L2NpcInstance to this L2Spawn</li>
	 * <li>Init other values of the L2NpcInstance (ex : from its L2CharTemplate for INT, STR, DEX...) and add it in the world</li>
	 * <li>Launch the action OnSpawn fo the L2NpcInstance</li>
	 * <li>Increase the current number of L2NpcInstance managed by this L2Spawn</li>
	 * </ul>
	 * @param isSummonSpawn
	 * @return
	 */
	public L2Npc doSpawn(boolean isSummonSpawn)
	{
		try
		{
			// Check if the L2Spawn is not a L2Pet or L2Minion or L2Decoy spawn
			if (_template.isType("L2Pet") || _template.isType("L2Decoy") || _template.isType("L2Trap"))
			{
				_currentCount++;
				
				return null;
			}
			
			// Call the constructor of the L2Npc
			L2Npc npc = _constructor.newInstance(IdFactory.getInstance().getNextId(), _template);
			npc.setInstanceId(getInstanceId()); // Must be done before object is spawned into visible world
			if (isSummonSpawn)
			{
				npc.setShowSummonAnimation(isSummonSpawn);
			}
			
			// Check for certain AI data, overriden in spawnlist
			if (_name != null)
			{
				NpcPersonalAIData.getInstance().initializeNpcParameters(npc, this, _name);
			}
			
			return initializeNpcInstance(npc);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "NPC "+_template.getId()+" "+_template.getName()+" '"+_template.getType()+"' class not found", e);	//[JOJO]
			_maximumCount = 0;	//+[JOJO]
		}
		return null;
	}
	
	/**
	 * @param mob
	 * @return
	 */
	private L2Npc initializeNpcInstance(L2Npc mob)
	{
		int newlocx = 0;
		int newlocy = 0;
		int newlocz = 0;
		
		// If Locx and Locy are not defined, the L2NpcInstance must be spawned in an area defined by location or spawn territory
		// New method
		if (isTerritoryBased())
		{
			int[] p = _spawnTerritory.getRandomPoint();
			newlocx = p[0];
			newlocy = p[1];
			newlocz = p[2];
		}
		// Old method (for backward compatibility)
		else if ((getX() == 0) && (getY() == 0))
		{
			if (getLocationId() == 0)
			{
				return mob;
			}
			
			// Calculate the random position in the location area
			final Location location = TerritoryTable.getInstance().getRandomPoint(getLocationId());
			
			// Set the calculated position of the L2NpcInstance
			if (location != null)
			{
				newlocx = location.getX();
				newlocy = location.getY();
				newlocz = location.getZ();
			}
		}
		else
		{
			// The L2NpcInstance is spawned at the exact position (Lox, Locy, Locz)
			newlocx = getX();
			newlocy = getY();
			newlocz = getZ();
		}
		
		// don't correct z of flying npc's
		if (!mob.isFlying())
		{
			newlocz = GeoData.getInstance().getSpawnHeight(newlocx, newlocy, newlocz);
		}
		
		mob.stopAllEffects();
		
		mob.setIsDead(false);
		// Reset decay info
		mob.setDecayed(false);
		// Set the HP and MP of the L2NpcInstance to the max
		mob.setCurrentHpMp(mob.getMaxHp(), mob.getMaxMp());
		// Clear script variables
		if (mob.hasVariables())
		{
			mob.getVariables().getSet().clear();
		}
		// Set is not random walk default value
		mob.setIsNoRndWalk(isNoRndWalk());
		
		// Set the heading of the L2NpcInstance (random heading if not defined)
		if (getHeading() == -1)
		{
			mob.setHeading(Rnd.nextInt(61794));
		}
		else
		{
			mob.setHeading(getHeading());
		}
		
		if (mob instanceof L2Attackable)
		{
			((L2Attackable) mob).setChampion(false);
		}
		
		if (Config.L2JMOD_CHAMPION_ENABLE)
		{
			// Set champion on next spawn
			if (mob.isMonster() && !getTemplate().isUndying() && !mob.isRaid() && !mob.isRaidMinion() && (Config.L2JMOD_CHAMPION_FREQUENCY > 0) && (mob.getLevel() >= Config.L2JMOD_CHAMP_MIN_LVL) && (mob.getLevel() <= Config.L2JMOD_CHAMP_MAX_LVL) && (Config.L2JMOD_CHAMPION_ENABLE_IN_INSTANCES || (getInstanceId() == 0)))
			{
				if (Rnd.get(100) < Config.L2JMOD_CHAMPION_FREQUENCY)
				{
					((L2Attackable) mob).setChampion(true);
				}
			}
		}
		
		// Link the L2NpcInstance to this L2Spawn
		mob.setSpawn(this);
		
		// Init other values of the L2NpcInstance (ex : from its L2CharTemplate for INT, STR, DEX...) and add it in the world as a visible object
		mob.spawnMe(newlocx, newlocy, newlocz);
		
		L2Spawn.notifyNpcSpawned(mob);
		
		if (_spawnedNpcs == null) _spawnedNpcs = new LinkedList<>(); _spawnedNpcs.add(mob);
		if (_lastSpawnPoints != null)
		{
			_lastSpawnPoints.put(mob.getObjectId(), new Location(newlocx, newlocy, newlocz));
		}
		
		if (Config.DEBUG)
		{
			_log.finest("Spawned Mob Id: " + _template.getId() + " , at: X: " + mob.getX() + " Y: " + mob.getY() + " Z: " + mob.getZ());
		}
		// Increase the current number of L2NpcInstance managed by this L2Spawn
		_currentCount++;
		return mob;
	}
	
	public static void addSpawnListener(SpawnListener listener)
	{
		synchronized (_spawnListeners)
		{
if (com.l2jserver.Config.FIX_onSpawn_for_SpawnTable) {{
			for (L2Object o :  L2World.getInstance().getVisibleObjects())
				if (o instanceof L2Npc)
					listener.npcSpawned((L2Npc) o);
}}

			_spawnListeners.add(listener);
		}
	}
	
	public static void removeSpawnListener(SpawnListener listener)
	{
		synchronized (_spawnListeners)
		{
			_spawnListeners.remove(listener);
		}
	}
	
	public static void notifyNpcSpawned(L2Npc npc)
	{
		synchronized (_spawnListeners)
		{
			for (SpawnListener listener : _spawnListeners)
			{
				listener.npcSpawned(npc);
			}
		}
	}
	
	/**
	 * Set bounds for random calculation and delay for respawn
	 * @param delay delay in seconds
	 * @param randomInterval random interval in seconds
	 */
	public void setRespawnDelay(int delay, int randomInterval)
	{
		if (delay != 0)
		{
			if (delay < 0)
			{
				_log.warning("respawn delay is negative for spawn:" + this);
			}
			
			int minDelay = delay - randomInterval;
			int maxDelay = delay + randomInterval;
			
			_respawnMinDelay = Math.max(10, minDelay) * 1000;
			_respawnMaxDelay = Math.max(10, maxDelay) * 1000;
		}
		
		else
		{
			_respawnMinDelay = 0;
			_respawnMaxDelay = 0;
		}
	}
	
	public void setRespawnDelay(int delay)
	{
		setRespawnDelay(delay, 0);
	}
	
	public int getRespawnDelay()
	{
		return (_respawnMinDelay + _respawnMaxDelay) / 2;
	}
	
	public boolean hasRespawnRandom()
	{
		return _respawnMinDelay != _respawnMaxDelay;
	}
	
	public void setSpawnTerritory(NpcSpawnTerritory territory)
	{
		_spawnTerritory = territory;
		_lastSpawnPoints = new FastIntObjectMap<Location>().shared();
	}
	
	public NpcSpawnTerritory getSpawnTerritory()
	{
		return _spawnTerritory;
	}
	
	public boolean isTerritoryBased()
	{
		return (_spawnTerritory != null) && (getX() == 0) && (getY() == 0);
	}
	
	public L2Npc getLastSpawn()
	{
		if (_spawnedNpcs != null && !_spawnedNpcs.isEmpty())
		{
			return _spawnedNpcs.getLast();
		}
		
		return null;
	}
	
 //	public final List<L2Npc> getSpawnedNpcs()
 //	{
 //		return _spawnedNpcs != null ? _spawnedNpcs : Collections.<L2Npc>emptyList();
 //	}
	
	/**
	 * @param oldNpc
	 */
	public void respawnNpc(L2Npc oldNpc)
	{
		if (_doRespawn)
		{
			oldNpc.refreshID();
			initializeNpcInstance(oldNpc);
		}
	}
	
	public L2NpcTemplate getTemplate()
	{
		return _template;
	}
	
	@Override
	public String toString()
	{
		return "L2Spawn ID: " + getId() + " " + getLocation();
	}
	
	public final boolean isNoRndWalk()
	{
		return _isNoRndWalk;
	}
	
	public final void setIsNoRndWalk(boolean value)
	{
		_isNoRndWalk = value;
	}
}
