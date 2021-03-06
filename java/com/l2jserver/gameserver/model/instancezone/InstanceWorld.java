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
package com.l2jserver.gameserver.model.instancezone;

import java.util.List;

import javolution.util.FastList;

import com.l2jserver.gameserver.instancemanager.InstanceManager;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.entity.Instance;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;

/**
 * Basic instance zone data transfer object.
 * @author Zoey76
 */
public class InstanceWorld
{
	public int instanceId;
	public int templateId = -1;
	public final List<Integer> allowed = new FastList<>();
	public volatile int status = 0;	//[JOJO] -AtomicInteger
	
	public List<Integer> getAllowed()
	{
		return allowed;
	}
	
	public Integer removeAllowed(int id)
	{
		return allowed.remove(allowed.indexOf(Integer.valueOf(id)));
	}
	
	public Integer removeAllowed(L2PcInstance player)
	{
		return removeAllowed(player.getObjectId());
	}
	
	public boolean addAllowed(int id)
	{
		return allowed.add(id);
	}
	
	public boolean addAllowed(L2PcInstance player)
	{
		return addAllowed(player.getObjectId());
	}
	
	public boolean isAllowed(int id)
	{
		return allowed.contains(id);
	}
	
	public boolean isAllowed(L2PcInstance player)
	{
		return isAllowed(player.getObjectId());
	}
	
	/**
	 * Gets the dynamically generated instance ID.
	 * @return the instance ID
	 */
	public int getInstanceId()
	{
		return instanceId;
	}
	
	/**
	 * Sets the instance ID.
	 * @param instanceId the instance ID
	 */
	public void setInstanceId(int instanceId)
	{
		this.instanceId = instanceId;
	}
	
	/**
	 * Gets the client's template instance ID.
	 * @return the template ID
	 */
	public int getTemplateId()
	{
		return templateId;
	}
	
	/**
	 * Sets the template ID.
	 * @param templateId the template ID
	 */
	public void setTemplateId(int templateId)
	{
		this.templateId = templateId;
	}
	
	public int getStatus()
	{
		return this.status;
	}
	
	public boolean isStatus(int status)
	{
		return this.status == status;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	/*@Deprecated*/ public synchronized void incStatus()
	{
		++this.status;
	}
	
	/**
	 * @param killer
	 * @param victim
	 */
	public void onDeath(L2Character killer, L2Character victim)
	{
		if ((victim != null) && victim.isPlayer())
		{
			final Instance instance = InstanceManager.getInstance().getInstance(getInstanceId());
			if (instance != null)
			{
				final SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOU_WILL_BE_EXPELLED_IN_S1);
				sm.addInt((instance.getEjectTime() + 59999) / 60000);
				victim.getActingPlayer().sendPacket(sm);
				instance.addEjectDeadTask(victim.getActingPlayer());
			}
		}
	}
}
