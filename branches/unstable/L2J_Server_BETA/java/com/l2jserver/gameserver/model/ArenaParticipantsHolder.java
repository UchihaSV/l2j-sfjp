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

import static com.l2jserver.gameserver.instancemanager.HandysBlockChecker.*;

import java.util.ArrayList;
import java.util.List;

import com.l2jserver.gameserver.instancemanager.HandysBlockCheckerManager;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.entity.BlockCheckerEngine;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.L2GameServerPacket;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;

/**
 * @author xban1x, BiggBoss
 */
public final class ArenaParticipantsHolder
{
	private final int _arena;
	private final ArrayList<L2PcInstance> _redPlayers;
	private final ArrayList<L2PcInstance> _bluePlayers;
	private final BlockCheckerEngine _engine;
	
	public ArenaParticipantsHolder(int arena)
	{
		_arena = arena;
		_redPlayers = new ArrayList<>(6);
		_bluePlayers = new ArrayList<>(6);
		_engine = new BlockCheckerEngine(this, _arena);
	}
	
	public List<L2PcInstance> getRedPlayers()
	{
		return _redPlayers;
	}
	
	public List<L2PcInstance> getBluePlayers()
	{
		return _bluePlayers;
	}
	
	public List<L2PcInstance> getAllPlayers()
	{
		ArrayList<L2PcInstance> all = new ArrayList<>(12);
		all.addAll(_redPlayers);
		all.addAll(_bluePlayers);
		return all;
	}
	
	public void addPlayer(L2PcInstance player, int team)
	{
		if (team == TEAM_RED)
		{
			_redPlayers.add(player);
		}
		else
		{
			_bluePlayers.add(player);
		}
	}
	
	public void removePlayer(L2PcInstance player, int team)
	{
		if (team == TEAM_RED)
		{
			_redPlayers.remove(player);
		}
		else
		{
			_bluePlayers.remove(player);
		}
	}
	
	public int getPlayerTeam(L2PcInstance player)
	{
		if (_redPlayers.contains(player))
		{
			return TEAM_RED;
		}
		else if (_bluePlayers.contains(player))
		{
			return TEAM_BLUE;
		}
		else
		{
			return TEAM_NON;
		}
	}
	
	public int getRedTeamSize()
	{
		return _redPlayers.size();
	}
	
	public int getBlueTeamSize()
	{
		return _bluePlayers.size();
	}
	
	public int getAllPlayerSize()
	{
		return _redPlayers.size() + _bluePlayers.size();
	}
	
	public boolean contains(L2PcInstance player)
	{
		return _redPlayers.contains(player)
		    || _bluePlayers.contains(player);
	}
	
	public void broadCastPacketToTeam(L2GameServerPacket packet)
	{
		for (L2PcInstance p : _redPlayers)
		{
			p.sendPacket(packet);
		}
		for (L2PcInstance p : _bluePlayers)
		{
			p.sendPacket(packet);
		}
	}
	
	public void clearPlayers()
	{
		_redPlayers.clear();
		_bluePlayers.clear();
	}
	
	public BlockCheckerEngine getEvent()
	{
		return _engine;
	}
	
	public void updateEvent()
	{
		_engine.updatePlayersOnStart(this);
	}
	
	public void checkAndShuffle()
	{
		boolean changed = false;
		while (_redPlayers.size() - _bluePlayers.size() >= 2) {
			HandysBlockCheckerManager.getInstance().changePlayerToTeam(_redPlayers.get(0), _arena, TEAM_BLUE);
			changed = true;
		}
		while (_bluePlayers.size() - _redPlayers.size() >= 2) {
			HandysBlockCheckerManager.getInstance().changePlayerToTeam(_bluePlayers.get(0), _arena, TEAM_RED);
			changed = true;
		}
		if (changed)
			broadCastPacketToTeam(SystemMessage.getSystemMessage(SystemMessageId.TEAM_ADJUSTED_BECAUSE_WRONG_POPULATION_RATIO));
	}
}
