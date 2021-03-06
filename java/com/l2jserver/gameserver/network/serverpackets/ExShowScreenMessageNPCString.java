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
package com.l2jserver.gameserver.network.serverpackets;


/**
 * extends ExShowScreenMessage, NpcSay
 * @author JOJO
 *
 */
public class ExShowScreenMessageNPCString extends ExShowScreenMessage
{
	public ExShowScreenMessageNPCString(int npcStringId, int time)
	{
		super(1, 0, TOP_CENTER, 0, SIZE_LARGE, 0, 0, false, time, false, null, npcStringId);
	}
}
