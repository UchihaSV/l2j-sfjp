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

import com.l2jserver.gameserver.model.interfaces.IPositionable;

/**
 * @author JOJO
 */
public class L2Position implements IPositionable
{
	// This class is read only.
	public final int x, y, z;
	
	public L2Position(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
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
	
	@Deprecated @Override public Location getLocation() { throw new UnsupportedOperationException(); }
	@Deprecated @Override public int getHeading() { throw new UnsupportedOperationException(); }
	@Deprecated @Override public int getInstanceId() { throw new UnsupportedOperationException(); }
	@Deprecated @Override public void setX(int x) { throw new UnsupportedOperationException(); }
	@Deprecated @Override public void setY(int y) { throw new UnsupportedOperationException(); }
	@Deprecated @Override public void setZ(int z) { throw new UnsupportedOperationException(); }
	@Deprecated @Override public void setHeading(int heading) { throw new UnsupportedOperationException(); }
	@Deprecated @Override public void setInstanceId(int instanceId) { throw new UnsupportedOperationException(); }
	@Deprecated @Override public void setLocation(Location loc) { throw new UnsupportedOperationException(); }
}
