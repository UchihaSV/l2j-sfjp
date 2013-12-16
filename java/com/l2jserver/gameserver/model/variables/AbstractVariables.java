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
package com.l2jserver.gameserver.model.variables;

import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.interfaces.IRestorable;
import com.l2jserver.gameserver.model.interfaces.IStorable;

/**
 * @author UnAfraid
 */
public abstract class AbstractVariables extends StatsSet implements IRestorable, IStorable
{
	private volatile boolean _changes = false;	//[JOJO] AtomicBoolean --> volatile boolean
	
	/**
	 * Overriding following methods to prevent from doing useless database operations if there is no changes since player's login.
	 */
	
	@Override
	public final void set(String name, boolean value)
	{
		super.set(name, value);
		_changes = true;
	}
	
	@Override
	public final void set(String name, double value)
	{
		super.set(name, value);
		_changes = true;
	}
	
	@Override
	public final void set(String name, Enum<?> value)
	{
		super.set(name, value);
		_changes = true;
	}
	
	@Override
	public final void set(String name, int value)
	{
		super.set(name, value);
		_changes = true;
	}
	
	@Override
	public final void set(String name, long value)
	{
		super.set(name, value);
		_changes = true;
	}
	
	@Override
	public final void set(String name, String value)
	{
		super.set(name, value);
		_changes = true;
	}
	
	/**
	 * Return true if there exists a record for the variable name.
	 * @param name
	 * @return
	 */
	public boolean hasVariable(String name)
	{
		return getSet().containsKey(name);
	}
	
	/**
	 * @return {@code true} if changes are made since last load/save.
	 */
	public final boolean getAndResetChanges()
	{
		boolean changes = _changes;
		if (changes) _changes = false;
		return changes;
	}
	
	/**
	 * Removes variable
	 * @param name
	 */
	public final void remove(String name)
	{
		_hasChanges.compareAndSet(false, true);
		getSet().remove(name);
	}
}
