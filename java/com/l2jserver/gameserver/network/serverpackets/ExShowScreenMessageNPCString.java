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
package com.l2jserver.gameserver.network.serverpackets;


/**
 * extends ExShowScreenMessage, NpcSay
 * @author JOJO
 *
 */
public class ExShowScreenMessageNPCString extends ExShowScreenMessage
{
	public static final int	// jurchiks
		TOP_LEFT = 1, TOP_CENTER = 2, TOP_RIGHT = 3, MIDDLE_LEFT = 4, MIDDLE_CENTER = 5, MIDDLE_RIGHT = 6, BOTTOM_CENTER = 7, BOTTOM_RIGHT = 8;
	public static final int
		SIZE_LARGE = 0, SIZE_SMALL = 1;
	
	public ExShowScreenMessageNPCString(int npcStringId, int time)
	{
		super(1, 0, TOP_CENTER, 0, SIZE_LARGE, 0, 0, false, time, false, null, npcStringId);
	}
}
