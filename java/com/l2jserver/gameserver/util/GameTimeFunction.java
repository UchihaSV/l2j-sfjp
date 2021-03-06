/*
 * Copyright (C) 2005-2008 L2J_JP / 2008-2013 L2J-SFJP
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
package com.l2jserver.gameserver.util;

import com.l2jserver.gameserver.GameTimeController;

/**
 * @author  JOJO
 */
public class GameTimeFunction
{
	/**
	 * @param hh,mm,ss ゲーム内時間の時分秒
	 * @return この次の時分秒まで、あと何ミリ秒か。実時間でのミリ秒で返す。
	 */
	public static long timeLeftMilli(int hh, int mm, int ss)
	{
		return timeLeftMilli(hh, mm, ss, 0);
	}
	
	public static long timeLeftMilli(int hh, int mm, int ss, int cycle)
	{
		// ゲーム内時間で計算
		final long gameTimeMillis = GameTimeController.getInstance().getGameTimeMillis() % 86400_000;
		if (cycle != 0)
		{
			int gameHour = (int) (gameTimeMillis / 3600_000L % 24);
		//	int gameMinute = (int) (gameTimeMillis / 60_000L % 60);
			int n = hh % cycle;
			hh = ((gameHour - n + cycle) / cycle * cycle + n) % 24;
		}
		long dd = ((long)hh * 3600_000 + mm * 60_000 + ss * 1_000) - gameTimeMillis;
		while (dd < 0)
			dd += 86400_000;
		
		// リアル時間に変換
		return (dd + 3) / 6;
	}
	
	/**
	 * @param hh ゲーム内時間の「時」
	 * @return 実時間でのミリ秒 例:ゲーム内の1時間⇒実時間の600ミリ秒(10分)
	 */
	public static long gameTimeHourToRealMilli(int hh)
	{
		 return (long) hh * GameTimeController.MILLIS_PER_IG_HOUR;
	}
	
	/**
	 * @return 経過秒数
	 */
	public static int currentTime()
	{
		return (int)((System.currentTimeMillis() - com.l2jserver.gameserver.GameServer.gameServer.serverLoadEnd) / 1000);
	}
}
