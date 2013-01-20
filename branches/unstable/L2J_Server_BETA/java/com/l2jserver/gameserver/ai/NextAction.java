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
package com.l2jserver.gameserver.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for AI action after some event.<br>
 * Has 2 array list for "work" and "break".
 * @author Yaroslav
 * mod: JOJO
 */
public class NextAction
{
	public interface NextActionCallback
	{
		public void doWork();
	}
	
	protected void doWork()	//+[JOJO] You can override.
	{
	}
	
	private ArrayList<CtrlEvent> _events;
	private ArrayList<CtrlIntention> _intentions;
	private NextActionCallback _callback;
	
	/**
	 * Main constructor.
	 * @param events
	 * @param intentions
	 * @param callback
	 */
	public NextAction(ArrayList<CtrlEvent> events, ArrayList<CtrlIntention> intentions, NextActionCallback callback)
	{
		_events = events;
		_intentions = intentions;
		setCallback(callback);
	}
	
	public NextAction(ArrayList<CtrlEvent> events, ArrayList<CtrlIntention> intentions)	//+[JOJO]
	{
		_events = events;
		_intentions = intentions;
	}
	
	/**
	 * Single constructor.
	 * @param event
	 * @param intention
	 * @param callback
	 */
	public NextAction(CtrlEvent event, CtrlIntention intention, NextActionCallback callback)
	{
		if (event != null)
		{
			_events = new ArrayList<>(1);
			_events.add(event);
		}
		
		if (intention != null)
		{
			_intentions = new ArrayList<>(1);
			_intentions.add(intention);
		}
		
		setCallback(callback);
	}
	
	/**
	 * Do action.
	 */
	public void doAction()
	{
		if (_callback != null)
		{
			_callback.doWork();
		}
		else
		{
			/*this.*/doWork();	//+[JOJO]
		}
	}
	
	/**
	 * @return the _event
	 */
	public List<CtrlEvent> getEvents()	//[JOJO] ArrayList --> List
	{
		if (_events == null)
		{
			return Collections.emptyList();
		}
		else
		{
			return _events;
		}
	}
	
	/**
	 * @param event the event to set.
	 */
	public void setEvents(ArrayList<CtrlEvent> event)
	{
		_events = event;
	}
	
	/**
	 * @param event
	 */
	public void addEvent(CtrlEvent event)
	{
		if (event != null)
		{
			if (_events == null)
			{
				_events = new ArrayList<>();
			}
			_events.add(event);
		}
	}
	
	/**
	 * @param event
	 */
	public void removeEvent(CtrlEvent event)
	{
		if (_events == null)
		{
			return;
		}
		_events.remove(event);
	}
	
	/**
	 * @return the _callback
	 */
	public NextActionCallback getCallback()
	{
		return _callback;
	}
	
	/**
	 * @param callback the callback to set.
	 */
	public void setCallback(NextActionCallback callback)
	{
		_callback = callback;
	}
	
	/**
	 * @return the _intentions
	 */
	public List<CtrlIntention> getIntentions()	//[JOJO] ArrayList --> List
	{
		// If null return empty list.
		if (_intentions == null)
		{
			return Collections.emptyList();
		}
		else
		{
			return _intentions;
		}
	}
	
	/**
	 * @param intentions the intention to set.
	 */
	public void setIntentions(ArrayList<CtrlIntention> intentions)
	{
		_intentions = intentions;
	}
	
	/**
	 * @param intention
	 */
	public void addIntention(CtrlIntention intention)
	{
		if (intention != null)
		{
			if (_intentions == null)
			{
				_intentions = new ArrayList<>();
			}
			_intentions.add(intention);
		}
	}
	
	/**
	 * @param intention
	 */
	public void removeIntention(CtrlIntention intention)
	{
		if (_intentions == null)
		{
			return;
		}
		_intentions.remove(intention);
	}
}