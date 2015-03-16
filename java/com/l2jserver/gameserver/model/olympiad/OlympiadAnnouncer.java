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
package com.l2jserver.gameserver.model.olympiad;

import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;

/**
 * @author DS
 */
final class OlympiadAnnouncer
{
	private static final int OLY_MANAGER = 31688;
	
	public static void announce(AbstractOlympiadGame game)
	{
		NpcStringId npcString;
		final String arenaId = String.valueOf(game.getStadiumId() + 1);
		switch (game.getType())
		{
			case NON_CLASSED:
				npcString = NpcStringId.OLYMPIAD_CLASS_FREE_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;
				break;
			case CLASSED:
				npcString = NpcStringId.OLYMPIAD_CLASS_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;
				break;
			case TEAMS:
				npcString = NpcStringId.OLYMPIAD_CLASS_FREE_TEAM_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;
				break;
			default:
				return;
		}
		
		for (L2Spawn spawn : SpawnTable.getInstance().getSpawns(OLY_MANAGER))
		{
			L2Npc manager = spawn.getLastSpawn();
			if (manager != null)
			{
				manager.broadcastPacket( new NpcSay(manager.getObjectId(), Say2.NPC_SHOUT, manager.getId(), npcString)
				.addStringParameter(arenaId) );
			}
		}
	}
}
