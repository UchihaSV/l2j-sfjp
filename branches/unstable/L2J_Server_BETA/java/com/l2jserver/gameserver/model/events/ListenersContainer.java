/*
 * Copyright (C) 2004-2014 L2J Server
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
package com.l2jserver.gameserver.model.events;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

import com.l2jserver.gameserver.model.events.listeners.AbstractEventListener;
import com.l2jserver.util.EmptyQueue;

/**
 * @author UnAfraid
 */
public class ListenersContainer
{
	private volatile Map<EventType, Queue<AbstractEventListener>> _listeners = null;
	
	/**
	 * Registers listener for a callback when specified event is executed.
	 * @param listener
	 * @return
	 */
	public AbstractEventListener addListener(AbstractEventListener listener)
	{
		if ((listener == null))
		{
			throw new NullPointerException("Listener cannot be null!");
		}
		getListeners().computeIfAbsent(listener.getType(), k -> new PriorityBlockingQueue<>()).add(listener);
		return listener;
	}
	
	/**
	 * Unregisters listener for a callback when specified event is executed.
	 * @param listener
	 * @return
	 */
	public AbstractEventListener removeListener(AbstractEventListener listener)
	{
		Queue<AbstractEventListener> eventQuests;
		if ((listener == null))
		{
			throw new NullPointerException("Listener cannot be null!");
		}
		else if (_listeners == null)
		{
			throw new NullPointerException("Listeners container is not initialized!");
		}
		else if ((eventQuests = _listeners.get(listener.getType())) == null)
		{
			throw new IllegalAccessError("Listeners container doesn't had " + listener.getType() + " event type added!");
		}
		
		eventQuests.remove(listener);
		return listener;
	}
	
	/**
	 * @param type
	 * @return {@code List} of {@link AbstractEventListener} by the specified type
	 */
	public Queue<AbstractEventListener> getListeners(EventType type)
	{
		Queue<AbstractEventListener> eventQuests;
		return (_listeners != null) && (eventQuests = _listeners.get(type)) != null ? eventQuests : EmptyQueue.emptyQueue();
	}
	
	public boolean hasListener(EventType type)
	{
		return !getListeners(type).isEmpty();
	}
	
	/**
	 * Creates the listeners container map if doesn't exists.
	 * @return the listeners container map.
	 */
	private Map<EventType, Queue<AbstractEventListener>> getListeners()
	{
		if (_listeners == null)
		{
			synchronized (this)
			{
				if (_listeners == null)
				{
					return _listeners = new ConcurrentHashMap<>();
				}
			}
		}
		return _listeners;
	}
}
