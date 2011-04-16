/* This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l2jserver.gameserver.instancemanager;

import com.l2jserver.gameserver.GameTimeController;
import com.l2jserver.gameserver.model.L2Object;

/**
 * L2J_JP r1057 CustomZoneManager の残骸。
 */
public class CustomZoneManager
{
	private static CustomZoneManager _instance = new CustomZoneManager();
	public static final CustomZoneManager getInstance()
	{
		return _instance;
	}

	/**
	 * ザケン - ３階南東の日光の当たる部屋 scripts/ai/individual/Zaken.java で使用。
	 */
	public boolean isZakenWeakZone(L2Object npc)
	{
		if ((GameTimeController.getInstance().getGameTime() / 60) % 24 < 5) return false;
		return CustomZoneManager.getInstance().isInsideSunlightRoomZone(npc);
	}

	public boolean isInsideSunlightRoomZone(L2Object obj)
	{
		int x = obj.getX();
		int y = obj.getY();
		int z = obj.getZ();
		return 55779 <= x && x <=  56789
		   && 219619 <= y && y <= 220636
		   &&  -2970 <= z && z <=  -2690;
	}
}