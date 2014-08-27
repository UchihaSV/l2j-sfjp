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
package com.l2jserver.gameserver.model.zone.type;

import javolution.util.FastList;
import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.gameserver.GameServer;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.instancemanager.GrandBossManager;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.PcCondOverride;
import com.l2jserver.gameserver.model.TeleportWhereType;
import com.l2jserver.gameserver.model.actor.L2Attackable;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.L2Summon;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.zone.AbstractZoneSettings;
import com.l2jserver.gameserver.model.zone.L2ZoneType;
import com.l2jserver.gameserver.util.FastIntSet;

/**
 * @author DaRkRaGe
 */
public class L2BossZone extends L2ZoneType
{
	private int _timeServerDown = 0;	//+[JOJO]
	private int _timeClientDown = 0;	//+[JOJO]
 //	private int _timeInvade;			//-[JOJO]
	
	//[JOJO]-------------------------------------------------
	final Location _oustLoc;
	void oustPlayer(L2PcInstance player)
	{
		if (_oustLoc.getX() != 0 && _oustLoc.getY() != 0 && _oustLoc.getZ() != 0)
			player.teleToLocation(_oustLoc);
		else
			player.teleToLocation(TeleportWhereType.TOWN);
	}
	//-------------------------------------------------------
	
	private final class Settings extends AbstractZoneSettings
	{
		// track the times that players got disconnected. Players are allowed
		// to log back into the zone as long as their log-out was within _timeInvade time...
		// <player objectId, expiration time in milliseconds>
		private final FastIntObjectMap<Long> _playerAllowedReEntryTimes;	//[JOJO] -FastMap
		
		// track the players admitted to the zone who should be allowed back in
		// after reboot/server downtime (outside of their control), within 30 of server restart
		private final FastIntSet _playersAllowed;		//[JOJO] -L2FastList
		
		private final FastList<L2Character> _raidList;	//[JOJO] -L2FastList
		
		public Settings()
		{
			_playerAllowedReEntryTimes = new FastIntObjectMap<>();	//[JOJO] -FastMap
			_playersAllowed = new FastIntSet();	//[JOJO] -L2FastList
			_raidList = new FastList<>();	//[JOJO] -L2FastList
		}
		
		public FastIntObjectMap<Long> getPlayerAllowedReEntryTimes()	//[JOJO] -FastMap
		{
			return _playerAllowedReEntryTimes;
		}
		
		public FastIntSet getPlayersAllowed()	//[JOJO] -L2FastList
		{
			return _playersAllowed;
		}
		
		public FastList<L2Character> getRaidList()	//[JOJO] -L2FastList
		{
			return _raidList;
		}
		
		@Override
		public void clear()
		{
			_playerAllowedReEntryTimes.clear();
			_playersAllowed.clear();
			_raidList.clear();
		}
	}
	
	public L2BossZone(int id)
	{
		super(id);
		_oustLoc = new Location(0, 0, 0, 0, -1);
		AbstractZoneSettings settings = ZoneManager.getSettings(getName());
		if (settings == null)
		{
			settings = new Settings();
		}
		setSettings(settings);
		GrandBossManager.getInstance().addZone(this);
	}
	
	@Override
	public Settings getSettings()
	{
		return (Settings) super.getSettings();
	}
	
	@Override
	public void setParameter(String name, String value)
	{
		if (name.equals("serverDownTime"))	//+[JOJO]
		{
			_timeServerDown = Integer.parseInt(value);
		}
		else if (name.equals("clientDownTime"))	//+[JOJO]
		{
			_timeClientDown = Integer.parseInt(value);
		}
		else if (name.equals("InvadeTime"))
		{
			//[JOJO]-------------------------------------------------
			if (_timeServerDown == 0 && _timeClientDown == 0)
				_timeServerDown = Integer.parseInt(value);
			//-------------------------------------------------------
		}
		else if (name.equals("oustX"))
		{
			_oustLoc.setX(Integer.parseInt(value));
		}
		else if (name.equals("oustY"))
		{
			_oustLoc.setY(Integer.parseInt(value));
		}
		else if (name.equals("oustZ"))
		{
			_oustLoc.setZ(Integer.parseInt(value));
		}
		else
		{
			super.setParameter(name, value);
		}
	}
	
	/**
	 * Boss zones have special behaviors for player characters.<br>
	 * Players are automatically teleported out when the attempt to enter these zones, except if the time at which they enter the zone is prior to the entry expiration time set for that player.<br>
	 * Entry expiration times are set by any one of the following:<br>
	 * 1) A player logs out while in a zone (Expiration gets set to logoutTime + _timeInvade)<br>
	 * 2) An external source (such as a quest or AI of NPC) set up the player for entry.<br>
	 * There exists one more case in which the player will be allowed to enter.<br>
	 * That is if the server recently rebooted (boot-up time more recent than currentTime - _timeInvade) AND the player was in the zone prior to reboot.
	 */
	@Override
	protected void onEnter(L2Character character)
	{
		if (isEnabled())
		{
			if (character.isPlayer())
			{
				final L2PcInstance player = character.getActingPlayer();
				if (player.canOverrideCond(PcCondOverride.ZONE_CONDITIONS))
				{
					return;
				}
				// if player has been (previously) cleared by npc/ai for entry and the zone is
				// set to receive players (aka not waiting for boss to respawn)
				if (getSettings().getPlayersAllowed().contains(player.getObjectId()))
				{
					// Get the information about this player's last logout-exit from
					// this zone.
					final Long expirationTime = getSettings().getPlayerAllowedReEntryTimes().get(player.getObjectId());
					
					// with legal entries, do nothing.
					if (expirationTime == null) // legal null expirationTime entries
					{
						long serverStartTime = GameServer.gameServer.serverLoadEnd;
						if ((serverStartTime > (System.currentTimeMillis() - _timeServerDown/*_timeInvade*/)))
						{
							return;
						}
					}
					else
					{
						// legal non-null logoutTime entries
						getSettings().getPlayerAllowedReEntryTimes().remove(player.getObjectId());
						if (expirationTime.longValue() > System.currentTimeMillis())
						{
							return;
						}
					}
					getSettings().getPlayersAllowed().remove(player.getObjectId());
				}
				// teleport out all players who attempt "illegal" (re-)entry
				oustPlayer(player);
			}
			else if (character.isSummon())
			{
				final L2PcInstance player = character.getActingPlayer();
				if (player != null)
				{
					if (getSettings().getPlayersAllowed().contains(player.getObjectId()) || player.canOverrideCond(PcCondOverride.ZONE_CONDITIONS))
					{
						return;
					}
					
					// remove summon and teleport out owner
					// who attempt "illegal" (re-)entry
					oustPlayer(player);
				}
				((L2Summon) character).unSummon(player);
			}
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (isEnabled())
		{
			if (character.isPlayer())
			{
				final L2PcInstance player = character.getActingPlayer();
				if (player.canOverrideCond(PcCondOverride.ZONE_CONDITIONS))
				{
					return;
				}
				
				// if the player just got disconnected/logged out, store the dc
				// time so that
				// decisions can be made later about allowing or not the player
				// to log into the zone
				if (!player.isOnline() && getSettings().getPlayersAllowed().contains(player.getObjectId()))
				{
					// mark the time that the player left the zone
					getSettings().getPlayerAllowedReEntryTimes().put(player.getObjectId(), System.currentTimeMillis() + _timeClientDown/*_timeInvade*/);
				}
				else
				{
					getSettings().getPlayersAllowed().remove(player.getObjectId());
					getSettings().getPlayerAllowedReEntryTimes().remove(player.getObjectId());
				}
			}
			if (character.isPlayable())
			{
				if ((getCharactersInside() != null) && !getCharactersInside().isEmpty())
				{
					getSettings().getRaidList().clear();
					int count = 0;
					for (L2Character obj : getCharactersInside())
					{
						if (obj == null)
						{
							continue;
						}
						if (obj.isPlayable())
						{
							count++;
						}
						else if (obj.isAttackable() && obj.isRaid())
						{
							getSettings().getRaidList().add(obj);
						}
					}
					// if inside zone isnt any player, force all boss instance return to its spawn points
					if ((count == 0) && !getSettings().getRaidList().isEmpty())
					{
						for (int i = 0; i < getSettings().getRaidList().size(); i++)
						{
							L2Attackable raid = (L2Attackable) getSettings().getRaidList().get(i);
							if ((raid == null) || (raid.getSpawn() == null) || raid.isDead())
							{
								continue;
							}
							if (!raid.isInsideRadius(raid.getSpawn(), 150, false, false))
							{
								raid.returnHome();
							}
						}
					}
				}
			}
		}
		if (character.isAttackable() && character.isRaid() && !character.isDead())
		{
			((L2Attackable) character).returnHome();
		}
	}
	
	@Deprecated public boolean isZoneEnabled() { return isEnabled(); }	//¬‰¼¬
	public void setZoneDisable() { super.setEnabled(false); }	//[JOJO]
	public void setZoneEnable() { super.setEnabled(true); }	//[JOJO]
	@Override
	public void setEnabled(boolean flag)
	{
		if (isEnabled() != flag)
		{
			oustAllPlayers();
		}
		super.setEnabled(flag);
	}
	
	public void setAllowedPlayers(FastIntSet players)
	{
		if (players != null)
		{
			getSettings().getPlayersAllowed().clear();
			getSettings().getPlayersAllowed().addAll(players.iterator());
		}
	}
	
	public FastIntSet getAllowedPlayers()	//[JOJO] -L2FastList
	{
		return getSettings().getPlayersAllowed();
	}
	
	public boolean isPlayerAllowed(L2PcInstance player)
	{
		if (player.canOverrideCond(PcCondOverride.ZONE_CONDITIONS))
		{
			return true;
		}
		else if (getSettings().getPlayersAllowed().contains(player.getObjectId()))
		{
			return true;
		}
		else
		{
			oustPlayer(player);
			return false;
		}
	}
	
	/**
	 * Some GrandBosses send all players in zone to a specific part of the zone, rather than just removing them all. If this is the case, this command should be used. If this is no the case, then use oustAllPlayers().
	 * @param loc
	 */
	public void movePlayersTo(Location loc)
	{
		if (_characterList.isEmpty())
		{
			return;
		}
		
		for (L2Character character : getCharactersInside())
		{
			if ((character != null) && character.isPlayer())
			{
				L2PcInstance player = character.getActingPlayer();
				if (player.isOnline())
				{
					player.teleToLocation(loc);
				}
			}
		}
	}
	
	/**
	 * Occasionally, all players need to be sent out of the zone (for example, if the players are just running around without fighting for too long, or if all players die, etc). This call sends all online players to town and marks offline players to be teleported (by clearing their relog expiration
	 * times) when they log back in (no real need for off-line teleport).
	 */
	public void oustAllPlayers()
	{
		if (_characterList.isEmpty())
		{
			return;
		}
		
		for (L2Character character : getCharactersInside())
		{
			if ((character != null) && character.isPlayer())
			{
				L2PcInstance player = character.getActingPlayer();
				if (player.isOnline())
				{
					oustPlayer(player);
				}
			}
		}
		getSettings().getPlayerAllowedReEntryTimes().clear();
		getSettings().getPlayersAllowed().clear();
	}
	
	/**
	 * This function is to be used by external sources, such as quests and AI in order to allow a player for entry into the zone for some time. Naturally if the player does not enter within the allowed time, he/she will be teleported out again...
	 * @param player reference to the player we wish to allow
	 * @param durationInSec amount of time in seconds during which entry is valid.
	 */
	public void allowPlayerEntry(L2PcInstance player, int durationInSec)
	{
		if (!player.canOverrideCond(PcCondOverride.ZONE_CONDITIONS))
		{
			int id = player.getObjectId();
			getSettings().getPlayersAllowed().add(id);
			getSettings().getPlayerAllowedReEntryTimes().put(id, System.currentTimeMillis() + durationInSec * 1000);
		}
	}
	
	public void removePlayer(L2PcInstance player)
	{
		if (!player.canOverrideCond(PcCondOverride.ZONE_CONDITIONS))
		{
			int id = player.getObjectId();
			getSettings().getPlayersAllowed().remove(id);
			getSettings().getPlayerAllowedReEntryTimes().remove(id);
		}
	}
	
	public void updateKnownList(L2Npc npc)
	{
		if ((_characterList == null) || _characterList.isEmpty())
		{
			return;
		}
		
		FastIntObjectMap<L2PcInstance> npcKnownPlayers = npc.getKnownList().getKnownPlayers();
		for (L2Character character : getCharactersInside())
		{
			if ((character != null) && character.isPlayer())
			{
				L2PcInstance player = character.getActingPlayer();
				if (player.isOnline())
				{
					npcKnownPlayers.put(player.getObjectId(), player);
				}
			}
		}
	}
	//[JOJO]-------------------------------------------------
	// When the player has been annihilated, the player is banished from the lair.
	public void checkAnnihilated(L2PcInstance pc/*:dummy*/)	//<<== L2PcInstance#doDie
	{
		if (! isEnabled())
			return;
		if (isPlayersAnnihilated())
		{
			ThreadPoolManager.getInstance().scheduleGeneral(new Runnable() {
				@Override
				public void run()
				{
					oustAllPlayers();
				}
			}, 10000);
		}
	}
	private synchronized boolean isPlayersAnnihilated()
	{
		if (_characterList == null)
			return false;
		for (L2Character character : getCharactersInside())
		{
			if (character instanceof L2PcInstance)
			{
				L2PcInstance player = (L2PcInstance)character;
				if (! player.isDead() && player.isOnline())
					return false;
			}
		}
		return true;
	}
	//-------------------------------------------------------
	
}
