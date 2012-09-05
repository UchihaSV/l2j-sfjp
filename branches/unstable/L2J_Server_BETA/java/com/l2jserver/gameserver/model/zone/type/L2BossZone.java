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
package com.l2jserver.gameserver.model.zone.type;

import java.util.Map;

import javolution.util.FastMap;

import com.l2jserver.gameserver.GameServer;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.instancemanager.GrandBossManager;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.actor.L2Attackable;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.L2Summon;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.zone.AbstractZoneSettings;
import com.l2jserver.gameserver.model.zone.L2ZoneType;
import com.l2jserver.util.L2FastList;

/**
 * @author DaRkRaGe
 */
public class L2BossZone extends L2ZoneType
{
	private int _timeServerDown = 0;	//+[JOJO]
	private int _timeClientDown = 0;	//+[JOJO]
 //	private int _timeInvade;			//-[JOJO]
	private boolean _enabled = true; // default value, unless overridden by xml...
	
	private int[] _oustLoc =
	{
		0,
		0,
		0
	};
	
	private final class Settings extends AbstractZoneSettings
	{
		// track the times that players got disconnected. Players are allowed
		// to log back into the zone as long as their log-out was within _timeInvade time...
		// <player objectId, expiration time in milliseconds>
		private final FastMap<Integer, Long> _playerAllowedReEntryTimes;
		
		// track the players admitted to the zone who should be allowed back in
		// after reboot/server downtime (outside of their control), within 30 of server restart
		private final L2FastList<Integer> _playersAllowed;
		
		private final L2FastList<L2Character> _raidList;
		
		public Settings()
		{
			_playerAllowedReEntryTimes = new FastMap<>();
			_playersAllowed = new L2FastList<>();
			_raidList = new L2FastList<>();
		}
		
		public FastMap<Integer, Long> getPlayerAllowedReEntryTimes()
		{
			return _playerAllowedReEntryTimes;
		}
		
		public L2FastList<Integer> getPlayersAllowed()
		{
			return _playersAllowed;
		}
		
		public L2FastList<L2Character> getRaidList()
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
		_oustLoc = new int[3];
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
		else if (name.equals("default_enabled"))
		{
			_enabled = Boolean.parseBoolean(value);
		}
		else if (name.equals("oustX"))
		{
			_oustLoc[0] = Integer.parseInt(value);
		}
		else if (name.equals("oustY"))
		{
			_oustLoc[1] = Integer.parseInt(value);
		}
		else if (name.equals("oustZ"))
		{
			_oustLoc[2] = Integer.parseInt(value);
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
		if (_enabled)
		{
			if (character.isPlayer())
			{
				final L2PcInstance player = character.getActingPlayer();
				if (player.isGM())
					return;
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
					getSettings().getPlayersAllowed().remove(getSettings().getPlayersAllowed().indexOf(player.getObjectId()));
				}
				// teleport out all players who attempt "illegal" (re-)entry
				if (_oustLoc[0] != 0 && _oustLoc[1] != 0 && _oustLoc[2] != 0)
				{
					player.teleToLocation(_oustLoc[0], _oustLoc[1], _oustLoc[2]);
				}
				else
				{
					player.teleToLocation(MapRegionManager.TeleportWhereType.Town);
				}
			}
			else if (character.isSummon())
			{
				final L2PcInstance player = character.getActingPlayer();
				if (player != null)
				{
					if (getSettings().getPlayersAllowed().contains(player.getObjectId()) || player.isGM())
						return;
					
					// remove summon and teleport out owner
					// who attempt "illegal" (re-)entry
					if (_oustLoc[0] != 0 && _oustLoc[1] != 0 && _oustLoc[2] != 0)
					{
						player.teleToLocation(_oustLoc[0], _oustLoc[1], _oustLoc[2]);
					}
					else
					{
						player.teleToLocation(MapRegionManager.TeleportWhereType.Town);
					}
				}
				((L2Summon) character).unSummon(player);
			}
		}
	}
	
	@Override
	protected void onExit(L2Character character)
	{
		if (_enabled)
		{
			if (character.isPlayer())
			{
				final L2PcInstance player = character.getActingPlayer();
				if (player.isGM())
					return;
				
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
					if (getSettings().getPlayersAllowed().contains(player.getObjectId()))
					{
						getSettings().getPlayersAllowed().remove(getSettings().getPlayersAllowed().indexOf(player.getObjectId()));
					}
					getSettings().getPlayerAllowedReEntryTimes().remove(player.getObjectId());
				}
			}
			if (character.isPlayable())
			{
				if (getCharactersInside() != null && !getCharactersInside().isEmpty())
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
						else if (obj.isL2Attackable() && obj.isRaid())
						{
							getSettings().getRaidList().add(obj);
						}
					}
					// if inside zone isnt any player, force all boss instance return to its spawn points
					if (count == 0 && !getSettings().getRaidList().isEmpty())
					{
						for (int i = 0; i < getSettings().getRaidList().size(); i++)
						{
							L2Attackable raid = (L2Attackable) getSettings().getRaidList().get(i);
							if (raid == null || raid.getSpawn() == null || raid.isDead())
								continue;
							if (!raid.isInsideRadius(raid.getSpawn().getLocx(), raid.getSpawn().getLocy(), 150, false))
								raid.returnHome();
						}
					}
				}
			}
		}
		if (character.isL2Attackable() && character.isRaid() && !character.isDead())
		{
			((L2Attackable) character).returnHome();
		}
	}
	
	@Deprecated public boolean isZoneEnabled() {return _enabled;}	//������
	public void setZoneDisable() { _enabled = false; }	//[JOJO]
	public void setZoneEnable() { _enabled = true; }	//[JOJO]
	public void setZoneEnabled(boolean flag)
	{
		if (_enabled != flag)
			oustAllPlayers();
		
		_enabled = flag;
	}
	
	public void setAllowedPlayers(L2FastList<Integer> players)
	{
		if (players != null)
		{
			getSettings().getPlayersAllowed().clear();
			getSettings().getPlayersAllowed().addAll(players);
		}
	}
	
	public L2FastList<Integer> getAllowedPlayers()
	{
		return getSettings().getPlayersAllowed();
	}
	
	public boolean isPlayerAllowed(L2PcInstance player)
	{
		if (player.isGM())
		{
			return true;
		}
		else if (getSettings().getPlayersAllowed().contains(player.getObjectId()))
		{
			return true;
		}
		else
		{
			if (_oustLoc[0] != 0 && _oustLoc[1] != 0 && _oustLoc[2] != 0)
			{
				player.teleToLocation(_oustLoc[0], _oustLoc[1], _oustLoc[2]);
			}
			else
			{
				player.teleToLocation(MapRegionManager.TeleportWhereType.Town);
			}
			return false;
		}
	}
	
	/**
	 * Some GrandBosses send all players in zone to a specific part of the zone, rather than just removing them all. If this is the case, this command should be used. If this is no the case, then use oustAllPlayers().
	 * @param x
	 * @param y
	 * @param z
	 */
	public void movePlayersTo(int x, int y, int z)
	{
		if (_characterList.isEmpty())
			return;
		
		for (L2Character character : getCharactersInside())
		{
			if (character != null && character.isPlayer())
			{
				L2PcInstance player = character.getActingPlayer();
				if (player.isOnline())
				{
					player.teleToLocation(x, y, z);
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
			return;
		
		for (L2Character character : getCharactersInside())
		{
			if (character != null && character.isPlayer())
			{
				L2PcInstance player = character.getActingPlayer();
				if (player.isOnline())
				{
					if (_oustLoc[0] != 0 && _oustLoc[1] != 0 && _oustLoc[2] != 0)
					{
						player.teleToLocation(_oustLoc[0], _oustLoc[1], _oustLoc[2]);
					}
					else
					{
						player.teleToLocation(MapRegionManager.TeleportWhereType.Town);
					}
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
		if (!player.isGM())
		{
			if (!getSettings().getPlayersAllowed().contains(player.getObjectId()))
			{
				getSettings().getPlayersAllowed().add(player.getObjectId());
			}
			getSettings().getPlayerAllowedReEntryTimes().put(player.getObjectId(), System.currentTimeMillis() + durationInSec * 1000);
		}
	}
	
	public void removePlayer(L2PcInstance player)
	{
		if (!player.isGM())
		{
			getSettings().getPlayersAllowed().remove(Integer.valueOf(player.getObjectId()));
			getSettings().getPlayerAllowedReEntryTimes().remove(player.getObjectId());
		}
	}
	
	@Override
	public void onDieInside(L2Character character)
	{
	}
	
	@Override
	public void onReviveInside(L2Character character)
	{
	}
	
	public void updateKnownList(L2Npc npc)
	{
		if (_characterList == null || _characterList.isEmpty())
			return;
		
		Map<Integer, L2PcInstance> npcKnownPlayers = npc.getKnownList().getKnownPlayers();
		for (L2Character character : getCharactersInside())
		{
			if (character != null && character.isPlayer())
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
		if (! _enabled)
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

	@Deprecated protected void TRACE(String message) {TRACE("", message);}
	@Deprecated protected void TRACE(String title, String message) { com.l2jserver.gameserver.debug.TRACE(title, message); }
	@Deprecated protected String STR(com.l2jserver.gameserver.model.actor.L2Npc npc) { return com.l2jserver.gameserver.debug.STR(npc); }
	@Deprecated protected String STR(com.l2jserver.gameserver.model.L2Object obj) { return com.l2jserver.gameserver.debug.STR(obj); }
	@Deprecated protected String STR(com.l2jserver.gameserver.model.skills.L2Skill skill) { return com.l2jserver.gameserver.debug.STR(skill); }
	@Deprecated protected String STR(java.util.concurrent.ScheduledFuture<?> task) { return com.l2jserver.gameserver.debug.STR(task); }
	
}
