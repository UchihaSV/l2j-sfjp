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
package com.l2jserver.gameserver.instancemanager.tasks;

import static com.l2jserver.gameserver.instancemanager.FourSepulchersManager.*;

import java.util.Calendar;

import com.l2jserver.Config;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.instancemanager.FourSepulchersManager;

/**
 * Four Sepulchers change attack time task.
 * @author xban1x, sandman
 */
public final class FourSepulchersChangeAttackTimeTask implements Runnable
{
	@Override
	public void run()
	{
		final FourSepulchersManager manager = FourSepulchersManager.getInstance();
		manager.setAttackTime();
		
		manager.locationShadowSpawns();
		
		manager.spawnMysteriousBox(31921);
		manager.spawnMysteriousBox(31922);
		manager.spawnMysteriousBox(31923);
		manager.spawnMysteriousBox(31924);
		
		manager.setAttackTimeStart(System.currentTimeMillis());
		
		long interval = Config.FS_TIME_ATTACK * 60000L;
		manager.setChangePeriodTask(ThreadPoolManager.getInstance().scheduleGeneral(new FourSepulchersChangeCoolDownTimeTask(), interval + OUST_PLAYER_MARGIN_TIME));
		ThreadPoolManager.getInstance().scheduleGeneral(new FourSepulchersManagerSayTask(), ATTACK_ELASPED_INTERVAL * 60000 + 500);
	}
	
	static class FourSepulchersManagerSayTask implements Runnable
	{
		@Override
		public void run()
		{
			final FourSepulchersManager manager = FourSepulchersManager.getInstance();
			if (manager.isAttackTime())
			{
				final Calendar tmp = Calendar.getInstance();
				tmp.setTimeInMillis(tmp.getTimeInMillis() - manager.getAttackTimeStart());
				int min = tmp.get(Calendar.MINUTE);
				manager.managerSay(min);
				
				if (min + ATTACK_ELASPED_INTERVAL <= Config.FS_TIME_ATTACK)
				{
					ThreadPoolManager.getInstance().scheduleGeneral(this/*new FourSepulchersManagerSay()*/, ATTACK_ELASPED_INTERVAL * 60000);
				}
			}
		}
	}
}
