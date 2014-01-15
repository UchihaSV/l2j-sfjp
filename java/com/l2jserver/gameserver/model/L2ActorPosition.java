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

import com.l2jserver.gameserver.model.interfaces.ILocational;

/**
 * @author JOJO
 */
public class L2ActorPosition implements ILocational
{
	// This class is read only.
	public final int x, y, z, heading;
	
	public L2ActorPosition(int x, int y, int z, int heading)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.heading = heading;
	}
	
	@Override
	public int getX()
	{
		return x;
	}
	
	@Override
	public int getY()
	{
		return y;
	}
	
	@Override
	public int getZ()
	{
		return z;
	}
	
	@Override
	public int getHeading()
	{
		return heading;
	}
	
	@Deprecated @Override public Location getLocation() { throw new UnsupportedOperationException(); }
	@Deprecated @Override public int getInstanceId() { throw new UnsupportedOperationException(); }
}
