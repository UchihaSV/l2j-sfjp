/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.model.skills.l2skills;

import static javolution.lang.MathLib.TWO_PI;

import java.util.logging.Level;

import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.util.Rnd;

public class L2SkillSpawn extends L2Skill
{
	private final int _npcId;
	private final int _despawnDelay;
	private final boolean _summonSpawn;
	private final boolean _randomOffset;
	private final boolean _showOwnerName;	//+[JOJO]
	
	public L2SkillSpawn(StatsSet set)
	{
		super(set);
		_npcId = set.getInteger("npcId", 0);
		_despawnDelay = set.getInteger("despawnDelay", 0);
		_summonSpawn = set.getBool("isSummonSpawn", false);
		_randomOffset = set.getBool("randomOffset", true);
		_showOwnerName = set.getBool("showOwnerName", true);
	}
	
	@Override
	public void useSkill(L2Character caster, L2Object[] targets)
	{
		if (caster.isAlikeDead())
		{
			return;
		}
		
		if (_npcId == 0)
		{
			_log.warning("NPC ID not defined for skill ID:" + getId());
			return;
		}
		
		final L2NpcTemplate template = NpcTable.getInstance().getTemplate(_npcId);
		if (template == null)
		{
			_log.warning("Spawn of the nonexisting NPC ID:" + _npcId + ", skill ID:" + getId());
			return;
		}
		
		final L2Spawn spawn;
		try
		{
			spawn = new L2Spawn(template);
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Exception in L2SkillSpawn. NPC ID: " + _npcId + ", skill ID: " + getId() + ", exception: " + e.getMessage(), e);
			return;
		}
		
		int x = caster.getX();
		int y = caster.getY();
		if (_randomOffset)
		{
			double radius = 20.0 + 30.0 * Rnd.nextDouble();
			double angle = TWO_PI * Rnd.nextDouble();
			x += (int)(radius * Math.cos(angle));
			y += (int)(radius * Math.sin(angle));
		}
		
		spawn.setLocx(x);
		spawn.setLocy(y);
		spawn.setLocz(caster.getZ() + com.l2jserver.Config.NPC_SPAWN_Z_MARGIN);
		spawn.setHeading(caster.getHeading());
		spawn.stopRespawn();
		spawn.setInstanceId(caster.getInstanceId());	//+[JOJO]
		
		final L2Npc npc = spawn.doSpawn(_summonSpawn);
		//npc.setName(template.getName());	//-[JOJO]
		if (_showOwnerName)
		{
			npc.setTitle(caster.getName());
		}
		npc.setSummoner(caster);
		if (_despawnDelay > 0)
		{
			npc.scheduleDespawn(_despawnDelay);
		}
		npc.setIsRunning(false); // Broadcast info
	}
}
