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
package com.l2jserver.gameserver.model.entity;
/*package com.l2jfree.gameserver.model.entity;*/

import com.l2jserver.gameserver.instancemanager.GrandBossManager;
import com.l2jserver.gameserver.model.StatsSet;

/**
 * l2j-free ⇒ l2jserver.com/l2j_jp/l2j-sfjp ボス移植用ラッパ
 *
 * @author  JOJO Sync: l2jfree-core rev6697, timestamp 2009/07/23 08:37:48
 */

public class GrandBossState
{
	public static class StateEnum
	{
		public static final int NOTSPAWN = 0;
		public static final int ALIVE    = 1;
		public static final int DEAD     = 2;
		public static final int INTERVAL = 3;
	}

	static final String[] _stateName = new String[4];
	static {
		_stateName[StateEnum.NOTSPAWN] = "NOTSPAWN";
		_stateName[StateEnum.ALIVE]    = "ALIVE";
		_stateName[StateEnum.DEAD]     = "DEAD";
		_stateName[StateEnum.INTERVAL] = "INTERVAL";
	}

	private int					_bossId;
 //	private long				_respawnDate;
	private StatsSet			_stateSet;

 //	private static final Logger	_log	= Logger.getLogger(GrandBossState.class.getName());

	public int getBossId()
	{
		return _bossId;
	}

	public void setBossId(int newId)
	{
		_bossId = newId;
	}

	public int getState()
	{
		return GrandBossManager.getInstance().getBossStatus(_bossId);
	}

	public String getStateName()
	{
		return _stateName[GrandBossManager.getInstance().getBossStatus(_bossId)];
	}

	public void setState(int status)
	{
		GrandBossManager.getInstance().setBossStatus(_bossId, status);
	}

 //	public long getRespawnDate()
 //	{
 //		return ...
 //	}

	public void setRespawnDate(long interval)
	{
		_stateSet.set("respawn_time", System.currentTimeMillis() + interval);
	}

 //	public GrandBossState()
 //	{
 //	}

	public GrandBossState(int bossId)
	{
		_bossId = bossId;
		_stateSet = GrandBossManager.getInstance().getStatsSet(_bossId);
	}

 //	public GrandBossState(int bossId, boolean isDoLoad)
 //	{
 //		...
 //	}

 // public void load()
 //	{
 //		...
 //	}

 //	public void save()
 //	{
 //		...
 //	}

	public void update()
	{
		GrandBossManager.getInstance().setStatsSet(_bossId, _stateSet);
	}

	public void setNextRespawnDate(long newRespawnDate)
	{
		_stateSet.set("respawn_time", newRespawnDate);
	}

	public long getInterval()
	{
		long interval = _stateSet.getLong("respawn_time") - System.currentTimeMillis();

		if (interval < 0)
			return 0;

		return interval;
	}

	public String respawnTimeFormat()	//+[JOJO]
	{
		return GrandBossManager.respawnTimeFormat(_stateSet);
	}
}