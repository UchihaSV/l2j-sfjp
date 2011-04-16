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
package com.l2jserver.gameserver.util;

import com.l2jserver.gameserver.GameTimeController;

/**
 * @author  JOJO
 */
public class GameTimeFunction
{
	public static final long DAYMILI = 14400*1000;	// ゲーム内時間の１日の長さを実時間であらわした[ミリ秒]
	public static final long TIMEMILI = 600*1000;	// ゲーム内時間の１時間の長さを実時間であらわした[ミリ秒]
	public static final long MINUTEMILI = 10*1000;	// ゲーム内時間の１分の長さを実時間であらわした[ミリ秒]

	/**
	 * @param hh,mm,ss ゲーム内時間の時分秒
	 * @return この次の時分秒まで、あと何ミリ秒か。実時間でのミリ秒で返す。
	 */
	public static long timeLeftMili(int hh, int mm, int ss)
	{
		int now = GameTimeController.getGameTicks() * 60 / 100;	//ゲーム内時間[秒]
		int dd = (hh * 3600 + mm * 60 + ss) - (now % 86400);
		if (dd < 0) dd += 86400;

		return (long)dd * 1000 / 6;	//リアル時間に変換
	}
}
