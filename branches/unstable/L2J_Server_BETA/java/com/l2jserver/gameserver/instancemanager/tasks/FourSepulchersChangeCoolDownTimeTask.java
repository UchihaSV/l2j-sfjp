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
package com.l2jserver.gameserver.instancemanager.tasks;

import static com.l2jserver.gameserver.instancemanager.FourSepulchersManager.*;

import java.util.Calendar;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.instancemanager.FourSepulchersManager;

/**
 * Four Sepulchers change cool down time task.
 * @author xban1x, sandman
 */
public final class FourSepulchersChangeCoolDownTimeTask implements Runnable
{
	@Override
	public void run()
	{
		final FourSepulchersManager manager = FourSepulchersManager.getInstance();
		manager.setCoolDownTime();
		
		manager.clean();
		
		long interval;
		final long now = System.currentTimeMillis();
		final Calendar time = Calendar.getInstance();
		time.set(Calendar.MINUTE, NEW_CYCLE_MINUTE);
		time.set(Calendar.SECOND, 0);
		time.set(Calendar.MILLISECOND, 0);
		while ((interval = time.getTimeInMillis() - now) < 0)
			time.add(Calendar.HOUR, 1);
		
		manager.setChangePeriodTask(ThreadPoolManager.getInstance().scheduleGeneral(new FourSepulchersChangeEntryTimeTask(), interval));
	}
}
