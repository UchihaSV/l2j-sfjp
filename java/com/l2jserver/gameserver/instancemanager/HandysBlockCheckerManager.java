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

import static com.l2jserver.gameserver.instancemanager.HandysBlockChecker.*;

import java.util.Arrays;

import com.l2jserver.Config;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.enums.Team;
import com.l2jserver.gameserver.instancemanager.tasks.PenaltyRemoveTask;
import com.l2jserver.gameserver.model.ArenaParticipantsHolder;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.itemcontainer.PcInventory;
import com.l2jserver.gameserver.model.olympiad.OlympiadManager;
import com.l2jserver.gameserver.model.zone.ZoneId;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.ExCubeGameAddPlayer;
import com.l2jserver.gameserver.network.serverpackets.ExCubeGameChangeTeam;
import com.l2jserver.gameserver.network.serverpackets.ExCubeGameRemovePlayer;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.gameserver.util.FastIntSet;

/**
 * This class manage the player add/remove, team change and event arena status,<br>
 * as the clearance of the participants list or liberate the arena.
 * @author BiggBoss
 */
public final class HandysBlockCheckerManager
{
	
	// All the participants and their team classified by arena
	private static final ArenaParticipantsHolder[] _arenaPlayers = new ArenaParticipantsHolder[4];	// 0..3
	
	// Arena votes to start the game
	private static final int[] _arenaVotes = new int[4];	// 0..3	[JOJO] -HashMap
	
	// Arena Status, True = is being used, otherwise, False
	private static final boolean[] _arenaStatus = new boolean[4];	// 0..3	[JOJO] -HashMap
	
	// Registration request penalty (10 seconds)
	protected static FastIntSet _registrationPenalty = new FastIntSet().shared();	//[JOJO] -Collections.synchronizedSet(new HashSet<Integer>())
	
	/**
	 * Return the number of event-start votes for the specified arena id
	 * @param arenaId
	 * @return int (number of votes)
	 */
	public synchronized int getArenaVotes(int arenaId)
	{
		return _arenaVotes[arenaId];
	}
	
	/**
	 * Add a new vote to start the event for the specified arena id
	 * @param arena
	 */
	public synchronized void increaseArenaVotes(int arena)
	{
		int newVotes = _arenaVotes[arena] + 1;
		ArenaParticipantsHolder holder = _arenaPlayers[arena];
		
		if (newVotes > holder.getAllPlayerSize() / 2 && !holder.getEvent().isStarted())
		{
			clearArenaVotes(arena);
			if (holder.getBlueTeamSize() == 0 || holder.getRedTeamSize() == 0)
			{
				return;
			}
			if (Config.HBCE_FAIR_PLAY)
			{
				holder.checkAndShuffle();
			}
			ThreadPoolManager.getInstance().executeTask(holder.getEvent().new StartEvent());
		}
		else
		{
			_arenaVotes[arena] = newVotes;
		}
	}
	
	/**
	 * Will clear the votes queue (of event start) for the specified arena id
	 * @param arena
	 */
	public synchronized void clearArenaVotes(int arena)
	{
		_arenaVotes[arena] = 0;
	}
	
	protected HandysBlockCheckerManager()
	{
		// Initialize arena status
		Arrays.fill(_arenaStatus, false);
		
		// Initialize arena votes
		Arrays.fill(_arenaVotes, 0);
	}
	
	/**
	 * Returns the players holder
	 * @param arena
	 * @return ArenaParticipantsHolder
	 */
	public ArenaParticipantsHolder getHolder(int arena)
	{
		return _arenaPlayers[arena];
	}
	
	/**
	 * Initializes the participants holder
	 */
	public void startUpParticipantsQueue()
	{
		for (int i = 0; i < 4; ++i)
		{
			_arenaPlayers[i] = new ArenaParticipantsHolder(i);
		}
	}
	
	/**
	 * Add the player to the specified arena (through the specified arena manager) and send the needed server -> client packets
	 * @param player
	 * @param arenaId
	 * @return
	 */
	public boolean addPlayerToArena(L2PcInstance player, int arena)
	{
		ArenaParticipantsHolder holder = _arenaPlayers[arena];
		
		synchronized (holder)
		{
			for (int i = 0; i < 4; i++)
			{
				if (_arenaPlayers[i].contains(player))
				{
					SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST);
					msg.addCharName(player);
					player.sendPacket(msg);
					return false;
				}
			}
			
			if (player.isCursedWeaponEquipped())
			{
				player.sendPacket(SystemMessageId.CANNOT_REGISTER_PROCESSING_CURSED_WEAPON);
				return false;
			}
			
			if (player.isOnEvent() || player.isInOlympiadMode())
			{
				player.sendMessage("Couldnt register you due other event participation");
				return false;
			}
			
			if (OlympiadManager.getInstance().isRegistered(player))
			{
				OlympiadManager.getInstance().unRegisterNoble(player);
				player.sendPacket(SystemMessageId.COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE);
			}
			
			// if(UnderGroundColiseum.getInstance().isRegisteredPlayer(player))
			// {
			// UngerGroundColiseum.getInstance().removeParticipant(player);
			// player.sendPacket(SystemMessageId.COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE));
			// }
			// if(KrateiCubeManager.getInstance().isRegisteredPlayer(player))
			// {
			// KrateiCubeManager.getInstance().removeParticipant(player);
			// player.sendPacket(SystemMessageId.COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE));
			// }
			
			if (_registrationPenalty.contains(player.getObjectId()))
			{
				player.sendPacket(SystemMessageId.CANNOT_REQUEST_REGISTRATION_10_SECS_AFTER);
				return false;
			}
			
			final int team;
			if (holder.getBlueTeamSize() < holder.getRedTeamSize())
			{
				team = TEAM_BLUE;
			}
			else
			{
				team = TEAM_RED;
			}
			holder.addPlayer(player, team);
			holder.broadCastPacketToTeam(new ExCubeGameAddPlayer(player, team));
			return true;
		}
	}
	
	/**
	 * Will remove the specified player from the specified team and arena and will send the needed packet to all his team mates / enemy team mates
	 * @param player
	 * @param arenaId
	 * @param team
	 */
	public void removePlayer(L2PcInstance player, int arena, int team)
	{
		ArenaParticipantsHolder holder = _arenaPlayers[arena];
		synchronized (holder)
		{
			holder.removePlayer(player, team);
			holder.broadCastPacketToTeam(new ExCubeGameRemovePlayer(player, team));
			
			// End event if theres an empty team
			int teamSize = team == TEAM_RED ? holder.getRedTeamSize() : holder.getBlueTeamSize();
			if (teamSize == 0)
			{
				holder.getEvent().endEventAbnormally();
			}
			
			_registrationPenalty.add(player.getObjectId());
			schedulePenaltyRemoval(player.getObjectId());
		}
	}
	
	/**
	 * Will change the player from one team to other (if possible) and will send the needed packets
	 * @param player
	 * @param arena
	 * @param team
	 */
	public void changePlayerToTeam(L2PcInstance player, int arena, int team)
	{
		ArenaParticipantsHolder holder = _arenaPlayers[arena];
		
		synchronized (holder)
		{
			final int fromTeam;
			final int toTeam;
			
			if (holder.getRedPlayers().contains(player))
			{
				fromTeam = TEAM_RED;
				toTeam = TEAM_BLUE;
				if (holder.getBlueTeamSize() == 6)
				{
					player.sendPacket(SystemMessageId.CANNOT_REGISTER_CAUSE_QUEUE_FULL);
				//	player.sendMessage("The team is full");
					return;
				}
			}
			else if (holder.getBluePlayers().contains(player))
			{
				fromTeam = TEAM_BLUE;
				toTeam = TEAM_RED;
				if (holder.getRedTeamSize() == 6)
				{
					player.sendPacket(SystemMessageId.CANNOT_REGISTER_CAUSE_QUEUE_FULL);
				//	player.sendMessage("The team is full");
					return;
				}
			}
			else
			{
				return;
			}
			
			holder.addPlayer(player, toTeam);
			holder.removePlayer(player, fromTeam);
			
			holder.broadCastPacketToTeam(new ExCubeGameChangeTeam(player, fromTeam, toTeam));
		}
	}
	
	/**
	 * Will erase all participants from the specified holder
	 * @param arenaId
	 */
	public synchronized void clearPaticipantQueueByArenaId(int arena)
	{
		_arenaPlayers[arena].clearPlayers();
	}
	
	/**
	 * Returns true if arena is holding an event at this momment
	 * @param arenaId
	 * @return boolean
	 */
	public boolean arenaIsBeingUsed(int arena)
	{
	//	if (arena < 0 || arena > 3)
	//	{
	//		return false;
	//	}
		return _arenaStatus[arena];
	}
	
	/**
	 * Set the specified arena as being used
	 * @param arenaId
	 */
	public void setArenaBeingUsed(int arena)
	{
		_arenaStatus[arena] = true;
	}
	
	/**
	 * Set as free the specified arena for future events
	 * @param arenaId
	 */
	public void setArenaFree(int arena)
	{
		_arenaStatus[arena] = false;
	}
	
	/**
	 * Called when played logs out while participating in Block Checker Event
	 * @param player
	 */
	public void onDisconnect(L2PcInstance player)
	{
		int arena = player.getBlockCheckerArena();
		int team = getHolder(arena).getPlayerTeam(player);
		removePlayer(player, arena, team);
		if (player.getTeam() != Team.NONE)
		{
			player.stopAllEffects();
			// Remove team aura
			player.setTeam(Team.NONE);
			
			// Remove the event items
			PcInventory inv = player.getInventory();
			
			if (inv.getItemByItemId(13787) != null)
			{
				long count = inv.getInventoryItemCount(13787, 0);
				inv.destroyItemByItemId("Handys Block Checker", 13787, count, player, player);
			}
			if (inv.getItemByItemId(13788) != null)
			{
				long count = inv.getInventoryItemCount(13788, 0);
				inv.destroyItemByItemId("Handys Block Checker", 13788, count, player, player);
			}
			player.setInsideZone(ZoneId.PVP, false);
			// Teleport Back
			player.teleToLocation(-57478 + com.l2jserver.util.Rnd.get(-200, 200), -60367 + com.l2jserver.util.Rnd.get(-200, 200), -2370);	//[JOJO]
		}
	}
	
	public void onPlayerEnter(L2PcInstance player)
	{
		player.stopSkillEffects(true, 6034);
		player.stopSkillEffects(true, 6035);
		player.stopSkillEffects(true, 6036);
		// Remove team aura
		player.setTeam(Team.NONE);
		
		// Remove the event items
		PcInventory inv = player.getInventory();
		
		if (inv.getItemByItemId(13787) != null)
		{
			long count = inv.getInventoryItemCount(13787, 0);
			inv.destroyItemByItemId("Handys Block Checker", 13787, count, player, player);
		}
		if (inv.getItemByItemId(13788) != null)
		{
			long count = inv.getInventoryItemCount(13788, 0);
			inv.destroyItemByItemId("Handys Block Checker", 13788, count, player, player);
		}
	}
	
	public void removePenalty(int objectId)
	{
		_registrationPenalty.remove(objectId);
	}
	
	private void schedulePenaltyRemoval(int objId)
	{
		ThreadPoolManager.getInstance().scheduleGeneral(new PenaltyRemoveTask(objId), 10000);
	}
	
	/**
	 * Gets the single instance of {@code HandysBlockCheckerManager}.
	 * @return single instance of {@code HandysBlockCheckerManager}
	 */
	public static HandysBlockCheckerManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final HandysBlockCheckerManager _instance = new HandysBlockCheckerManager();
	}
}