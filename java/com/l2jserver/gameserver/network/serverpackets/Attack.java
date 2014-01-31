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
package com.l2jserver.gameserver.network.serverpackets;

import java.util.ArrayList;

import com.l2jserver.gameserver.model.Hit;
import com.l2jserver.gameserver.model.actor.L2Character;

public class Attack extends L2GameServerPacket
{
	private final int _attackerObjId;
	private final boolean _soulshot;
	private final int _ssGrade;
	private final int _x;
	private final int _y;
	private final int _z;
	private final int _tx;
	private final int _ty;
	private final int _tz;
	private ArrayList<Hit> _hits;
	
	/**
	 * @param attacker
	 * @param target
	 * @param useShots
	 * @param ssGrade
	 */
	public Attack(L2Character attacker, L2Character target, boolean useShots, int ssGrade)
	{
		_attackerObjId = attacker.getObjectId();
		_soulshot = useShots;
		_ssGrade = ssGrade;
		_x = attacker.getX();
		_y = attacker.getY();
		_z = attacker.getZ();
		_tx = target.getX();
		_ty = target.getY();
		_tz = target.getZ();
	}
	
	/**
	 * Adds hit to the attack (Attacks such as dual dagger/sword/fist has two hits)
	 * @param target
	 * @param damage
	 * @param miss
	 * @param crit
	 * @param shld
	 */
	public void addHit(L2Character target, int damage, boolean miss, boolean crit, byte shld)
	{
		if (_hits == null)
			_hits = new ArrayList<>();
		_hits.add(new Hit(target, damage, miss, crit, shld, _soulshot, _ssGrade));
	}
	
	/**
	 * @return {@code true} if current attack contains at least 1 hit.
	 */
	public boolean hasHits()
	{
		return _hits != null;
	}
	
	/**
	 * @return {@code true} if attack has soul shot charged.
	 */
	public boolean hasSoulshot()
	{
		return _soulshot;
	}
	
	/**
	 * Writes current hit
	 * @param hit
	 */
	private void writeHit(Hit hit)
	{
		writeD(hit.getTargetId());
		writeD(hit.getDamage());
		writeC(hit.getFlags());
	}
	
	@Override
	protected final void writeImpl()
	{
		writeC(0x33);
		
		writeD(_attackerObjId);
		writeHit(_hits.get(0));
		writeD(_x);
		writeD(_y);
		writeD(_z);
		
		writeH(_hits.size() - 1);
		for (int i = 1; i < _hits.size(); i++)
		{
			writeHit(_hits.get(i));
		}
		
		writeD(_tx);
		writeD(_ty);
		writeD(_tz);
	}
}
