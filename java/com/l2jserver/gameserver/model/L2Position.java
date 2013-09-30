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
package com.l2jserver.gameserver.model;

/**
 * This class permit to pass (npcId, x, y, z, heading) position data to method.
 * @author JOJO
 */
public class L2Position
{
	public final int x, y, z;
	
	/**
	 * Constructor of L2Position.
	 * @param pX
	 * @param pY
	 * @param pZ
	 */
	public L2Position(int pX, int pY, int pZ)
	{
		x = pX;
		y = pY;
		z = pZ;
	}
}