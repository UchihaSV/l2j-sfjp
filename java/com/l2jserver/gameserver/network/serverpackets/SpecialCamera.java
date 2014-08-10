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
package com.l2jserver.gameserver.network.serverpackets;

import com.l2jserver.gameserver.model.actor.L2Character;

/**
 * Special Camera server packet implementation.
 * @author Zoey76
 */
public class SpecialCamera extends L2GameServerPacket
{
	private final int _id;
	private final int _force;
	private final int _angle1;
	private final int _angle2;
	private final int _time;
	private final int _duration;
	private final int _relYaw;
	private final int _relPitch;
	private final int _isWide;
	private final int _relAngle;
	private final int _unk;
	
	/**
	 * Special Camera 3 packet constructor.
	 * @param creature the creature
	 * @param force
	 * @param angle1
	 * @param angle2
	 * @param time
	 * @param range
	 * @param duration
	 * @param relYaw
	 * @param relPitch
	 * @param isWide
	 * @param relAngle
	 * @param unk unknown post-C4 parameter
	 */
	public SpecialCamera(L2Character creature, int force, int angle1, int angle2, int time, int range, int duration, int relYaw, int relPitch, int isWide, int relAngle, int unk)
	{
		_id = creature.getObjectId();
		_force = force;
		_angle1 = angle1;
		_angle2 = angle2;
		_time = time;
		_duration = duration;
		_relYaw = relYaw;
		_relPitch = relPitch;
		_isWide = isWide;
		_relAngle = relAngle;
		_unk = unk;
	}
	
	//[JOJO]-------------------------------------------------
	/**
	 * SpecialCamera revision 3696-6314
	 * @param id object Id
	 * @param dist the distance to the object
	 * @param yaw North = 90, South = 270, East = 0, West = 180
	 * @param pitch > 0: looks up, pitch < 0: looks down (angle)
	 * @param time faster if it's smaller
	 * @param duration animation time
	 */
	public SpecialCamera(int id, int dist, int yaw, int pitch, int time, int duration)
	{
		this(id, dist, yaw, pitch, time, duration, 0, 0, 0, 0, 0);
	}
	
	/**
	 * SpecialCamera revision 3964-6314
	 * @param id object Id
	 * @param dist the distance to the object
	 * @param yaw North = 90, South = 270, East = 0, West = 180
	 * @param pitch > 0: looks up, pitch < 0: looks down (angle)
	 * @param time faster if it's smaller
	 * @param duration animation time
	 * @param turn
	 * @param rise
	 * @param widescreen
	 * @param heading the first person point of view
	 */
	public SpecialCamera(int id, int dist, int yaw, int pitch, int time, int duration, int turn, int rise, int widescreen, int heading)
	{
		this(id, dist, yaw, pitch, time, duration, turn, rise, widescreen, heading, 0);
	}
	
	/**
	 * SpecialCamera revision 6315-
	 * @param id object Id
	 * @param dist the distance to the object
	 * @param yaw North = 90, South = 270, East = 0, West = 180
	 * @param pitch > 0: looks up, pitch < 0: looks down (angle)
	 * @param time faster if it's smaller
	 * @param duration animation time
	 * @param turn
	 * @param rise
	 * @param widescreen
	 * @param heading the first person point of view
	 * @param unkonown
	 */
	public SpecialCamera(int id, int dist, int yaw, int pitch, int time, int duration, int turn, int rise, int widescreen, int heading, int unkonown)
	{
		_id = id;
		_force = dist;
		_angle1 = yaw;
		_angle2 = pitch;
		_time = time;
		_duration = duration;
		_relYaw = turn;
		_relPitch = rise;
		_isWide = widescreen;
		_relAngle = heading;
		_unk = unkonown;
	}
	//-------------------------------------------------------
	
	@Override
	public void writeImpl()
	{
		writeC(0xD6);
		writeD(_id);
		writeD(_force);
		writeD(_angle1);
		writeD(_angle2);
		writeD(_time);
		writeD(_duration);
		writeD(_relYaw);
		writeD(_relPitch);
		writeD(_isWide);
		writeD(_relAngle);
		writeD(_unk);
	}
}
