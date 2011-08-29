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
package com.l2jserver.gameserver.model.actor.instance;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Skill;
import com.l2jserver.gameserver.model.zone.type.L2PeaceZone;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;

/**
 * @author JOJO
 * 
 * NPC          28      こたつ (type: L2Kotatsu) T25 Freya
 * NPC          127     こたつ (type: L2Kotatsu) T26 H5
 * |アイテム    20868   こたつ
 * |召喚スキル  22127-1 温かなこたつ召喚
 * |BUFスキル   22118-1 こたつのぬくもり - こたつのぬくもりが感じられます。HP回復力が40%、CP回復力が30%、MP回復力が20%増加している状態。
 */
public class L2KotatsuInstance extends L2XmassTreeInstance /*extends L2Npc*/
{
	private class KotatsuAI extends XmassAI implements Runnable
	{
		protected KotatsuAI(L2KotatsuInstance caster, L2Skill skill)
		{
			super(caster, skill);
		}
		
		@Override
		public void run()
		{
			for (L2PcInstance player : getKnownList().getKnownPlayersInRadius(_skill.getSkillRadius()))
			{
				if (player.isInvul())
					continue;
				if (player.isMovementDisabled())
					continue;
				if (player.getPkKills() > 5)
					continue;
				if (player.getCurrentHp() < player.getMaxHp()
				 || player.getCurrentCp() < player.getMaxCp()
				 || player.getCurrentMp() < player.getMaxMp())
				{
					handleEffect(player);
				}
			}
		}
	}
	
	public L2KotatsuInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
	}
	
	@Override
	public void onSpawn()
	{
		super.onSpawn();
		
		if (getNpcId() == 127 /*T26 H5*/
				&& ZoneManager.getInstance().getZone(this, L2PeaceZone.class) == null)
			_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new KotatsuAI(this, SkillTable.getInstance().getInfo(22118, 1)), 3000, 3000);
	}
	
	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		return 90; //guess
	}
}
