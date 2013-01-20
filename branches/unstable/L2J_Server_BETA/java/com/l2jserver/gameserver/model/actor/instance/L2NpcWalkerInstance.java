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
package com.l2jserver.gameserver.model.actor.instance;

import com.l2jserver.gameserver.ai.L2CharacterAI;
import com.l2jserver.gameserver.ai.L2NpcWalkerAI;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.CreatureSay;
import com.l2jserver.gameserver.network.serverpackets.L2GameServerPacket;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;

/**
 * This class manages some npcs can walk in the city.<br>
 * It inherits all methods from L2NpcInstance.
 * @author Rayan
 */
public class L2NpcWalkerInstance extends L2Npc
{
	/**
	 * Constructor of L2NpcWalkerInstance (use L2Character and L2NpcInstance constructor).
	 * @param objectId given object id
	 * @param template L2NpcTemplateForThisAi
	 */
	public L2NpcWalkerInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2NpcWalkerInstance);
		setAI(new L2NpcWalkerAI(new L2NpcWalkerAIAccessor()));
	}
	
	/**
	 * AI can't be detached, npc must move always with the same AI instance.
	 * @param newAI AI to set for this L2NpcWalkerInstance
	 */
	@Override
	public void setAI(L2CharacterAI newAI)
	{
		if (!(_ai instanceof L2NpcWalkerAI))
		{
			_ai = newAI;
		}
	}
	
 //	@Override
 //	public void onSpawn()
 //	{
 //		getAI().setHomeX(getX());
 //		getAI().setHomeY(getY());
 //		getAI().setHomeZ(getZ());
 //	}
	
	/**
	 * Sends a chat to all _knowObjects
	 * @param npcString
	 */
	public void broadcastChat(NpcStringId npcString)
	{
		final L2GameServerPacket cs;
		if (getTemplate().isServerSideName())
		{
			throw new UnsupportedOperationException(); //custom_npc cannot say NpcStringId.
		}
		else
		{
			cs = new NpcSay(getObjectId(), Say2.NPC_ALL, getTemplate().getIdTemplate(), npcString);
		}
		broadcastPacket(cs);
	}
	/**
	 * Sends a chat to all _knowObjects
	 * @param chat message to say
	 */
	public void broadcastChat(String chat)
	{
		final L2GameServerPacket cs;
		if (getTemplate().isServerSideName())
		{
			cs = new CreatureSay(getObjectId(), Say2.NPC_ALL, getName(), chat);
		}
		else
		{
			cs = new NpcSay(getObjectId(), Say2.NPC_ALL, getTemplate().getIdTemplate(), chat);
		}
		broadcastPacket(cs);
	}
	
	/**
	 * NPCs are immortal
	 * @param i ignore it
	 * @param attacker ignore it
	 * @param awake ignore it
	 */
	@Override
	public void reduceCurrentHp(double i, L2Character attacker, boolean awake, boolean isDOT, L2Skill skill)
	{
	}
	
	/**
	 * NPCs are immortal
	 * @param killer ignore it
	 * @return false
	 */
	@Override
	public boolean doDie(L2Character killer)
	{
		return false;
	}
	
	@Override
	public L2NpcWalkerAI getAI()
	{
		return (L2NpcWalkerAI) _ai;
	}
	
	protected class L2NpcWalkerAIAccessor extends L2Character.AIAccessor
	{
		/**
		 * AI can't be detached.
		 */
		@Override
		public void detachAI()
		{
		}
	}
	
	@Override
	public boolean isWalker()
	{
		return true;
	}
}
