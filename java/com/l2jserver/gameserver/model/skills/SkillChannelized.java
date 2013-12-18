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
package com.l2jserver.gameserver.model.skills;

import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.gameserver.model.actor.L2Character;

/**
 * @author UnAfraid
 */
public final class SkillChannelized
{
	private final FastIntObjectMap<FastIntObjectMap<L2Character>> _channelizers = new FastIntObjectMap<FastIntObjectMap<L2Character>>().shared();
	
	public void addChannelizer(int skillId, L2Character channelizer)
	{
		FastIntObjectMap<L2Character> c;
		if ((c = _channelizers.get(skillId)) == null)
			_channelizers.put(skillId, c = new FastIntObjectMap<L2Character>().shared());
		c.put(channelizer.getObjectId(), channelizer);
	}
	
	public void removeChannelizer(int skillId, L2Character channelizer)
	{
		FastIntObjectMap<L2Character> c;
		if ((c = _channelizers.get(skillId)) != null)
			c.remove(channelizer.getObjectId());
	}
	
	public int getChannerlizersSize(int skillId)
	{
		FastIntObjectMap<L2Character> c;
		return (c = _channelizers.get(skillId)) != null ? c.size() : 0;
	}
	
	public FastIntObjectMap<L2Character> getChannelizers(int skillId)
	{
		return _channelizers.get(skillId);
	}
	
	public void abortChannelization()
	{
		for (FastIntObjectMap<L2Character> map : _channelizers.values())
		{
			for (L2Character channelizer : map.values())
			{
				channelizer.abortCast();
			}
		}
		_channelizers.clear();
	}
	
	public boolean isChannelized()
	{
		for (FastIntObjectMap<L2Character> map : _channelizers.values())
		{
			if (map != null && !map.isEmpty())
			{
				return true;
			}
		}
		return false;
	}
}
