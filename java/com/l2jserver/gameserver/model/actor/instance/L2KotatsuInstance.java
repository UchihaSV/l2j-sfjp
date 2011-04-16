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

import java.util.Collection;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.templates.chars.L2NpcTemplate;

/**
 * @author JOJO
 */
public class L2KotatsuInstance extends L2XmassTreeInstance /*extends L2Npc*/
{

	private class KotatsuAI extends XmassAI implements Runnable
	{
		protected KotatsuAI(L2KotatsuInstance caster/*, L2Skill skill*/)
		{
			super(caster/*, L2Skill skill*/);
		}

		@Override public void run()
		{
			Collection<L2PcInstance> plrs = getKnownList().getKnownPlayersInRadius(getDistanceToWatchObject(null));
			for (L2PcInstance player : plrs)
			{
				if (player.isMovementDisabled())
					continue;
				if (player.getPkKills() > 5)
					continue;
				if (player.getCurrentHp() < player.getMaxHp() * 9 / 10)
					player.setCurrentHp(player.getCurrentHp() + player.getMaxHp() / player.getLevel());
				if (player.getCurrentCp() < player.getMaxCp() * 9 / 10)
					player.setCurrentCp(player.getCurrentHp() + player.getMaxCp() / player.getLevel());
				if (player.getCurrentMp() < player.getMaxMp() * 9 / 10)
					player.setCurrentMp(player.getCurrentMp() + player.getMaxMp() / player.getLevel());


			}
		}
	}

	public L2KotatsuInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		_aiTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new KotatsuAI(this/*,SkillTable.getInstance().getInfo(22118, 1)*/), 3000, 3000);
	}

	@Override
	public int getDistanceToWatchObject(L2Object object)
	{
		return 90;	//guess
	}
}
