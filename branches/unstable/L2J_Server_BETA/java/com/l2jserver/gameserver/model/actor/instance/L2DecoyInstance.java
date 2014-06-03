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
package com.l2jserver.gameserver.model.actor.instance;

import java.util.concurrent.Future;
import java.util.logging.Level;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Decoy;
import com.l2jserver.gameserver.model.actor.knownlist.DecoyKnownList;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.taskmanager.DecayTaskManager;

public class L2DecoyInstance extends L2Decoy
{
	protected L2Skill _hateSpamSkill;
	private Future<?> _despawnTask;
	private Future<?> _hateSpamTask;
	
	public L2DecoyInstance(int objectId, L2NpcTemplate template, L2PcInstance owner, int despawnDelay)
	{
		super(objectId, template, owner);
		setInstanceType(InstanceType.L2DecoyInstance);
		int skillLevel = getTemplate().getDisplayId() - 13070;
		_hateSpamSkill = SkillTable.getInstance().getInfo(5272, skillLevel);
		_despawnTask = ThreadPoolManager.getInstance().scheduleGeneral(new DespawnTask(), despawnDelay);
		_hateSpamTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new HateSpam(), 2000, 5000);
	}
	
	protected void stopAllTasks()
	{
		if (_hateSpamTask != null)
		{
			_hateSpamTask.cancel(true);
			_hateSpamTask = null;
		}
		if (_despawnTask != null)
		{
			_despawnTask.cancel(false);
			_despawnTask = null;
		}
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		stopAllTasks();
		if (!super.doDie(killer))
		{
			return false;
		}
		DecayTaskManager.getInstance().addDecayTask(this);
		return true;
	}
	
	@Override
	public DecoyKnownList getKnownList()
	{
		return (DecoyKnownList) super.getKnownList();
	}
	
	@Override
	public void initKnownList()
	{
		setKnownList(new DecoyKnownList(this));
	}
	
	protected class DespawnTask implements Runnable
	{
		@Override
		public void run()
		{
			unSummon(getOwner());
		}
	}
	
	protected class HateSpam implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				// skill 5272 "Decoy Provocation" - targetType: AURA, effect name: TargetMe
				final L2DecoyInstance me = L2DecoyInstance.this;
				setTarget(me);
				doCast(_hateSpamSkill);
			}
			catch (Throwable e)
			{
				_log.log(Level.SEVERE, "Decoy Error: ", e);
			}
		}
	}
	
	@Override
	public void unSummon(L2PcInstance owner)
	{
		stopAllTasks();
		super.unSummon(owner);
	}
}
