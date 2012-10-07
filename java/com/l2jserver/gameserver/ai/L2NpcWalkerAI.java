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
package com.l2jserver.gameserver.ai;

import java.util.List;

import com.l2jserver.Config;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.NpcWalkerRoutesData;
import com.l2jserver.gameserver.model.L2CharPosition;
import com.l2jserver.gameserver.model.L2NpcWalkerNode;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.instance.L2NpcWalkerInstance;
import com.l2jserver.gameserver.network.NpcStringId;

public class L2NpcWalkerAI extends L2CharacterAI implements Runnable
{
	private static final int DEFAULT_MOVE_DELAY = 0;
	
	private boolean _walkingToNextPoint = false;
	
	/**
	 * route of the current npc
	 */
	private List<L2NpcWalkerNode> _route;
	
	/**
	 * current node
	 */
	private int _currentPos;
	
	/**
	 * Constructor of L2CharacterAI.<BR><BR>
	 *
	 * @param accessor The AI accessor of the L2Character
	 */
	public L2NpcWalkerAI(L2Character.AIAccessor accessor)
	{
		super(accessor);
		
		if (!Config.ALLOW_NPC_WALKERS)
			return;
		
		_route = NpcWalkerRoutesData.getInstance().getRouteForNpc(getActor().getNpcId());
		if (_route != null)
			ThreadPoolManager.getInstance().scheduleAi(this, com.l2jserver.util.Rnd.get(300000, 36000));
		else
			_log.warning(getClass().getSimpleName()+": Missing route data! Npc: "+_actor);
	}
	
	@Override
	public void run()
	{
		/**
		 * Walk to Location.
		 */
		assert ! isWalkingToNextPoint();
		_currentPos = (_currentPos + 1) % _route.size();
		
		final L2NpcWalkerNode pos = _route.get(_currentPos);
		
		/**
		 * false - walking
		 * true - Running
		 */
		if (pos.getRunning())
			getActor().setRunning();
		else
			getActor().setWalking();
		
		//notify AI of MOVE_TO
		setWalkingToNextPoint(true);
		
		setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, new L2CharPosition(pos.getMoveX(), pos.getMoveY(), pos.getMoveZ(), 0));
	}
	
	@Override
	protected void onEvtArrived()
	{
		super.onEvtArrived();
		
		if (!Config.ALLOW_NPC_WALKERS)
			return;
		
		if (isWalkingToNextPoint())
		{
			/**
			 * Check arrived.
			 */
			final L2NpcWalkerNode pos = _route.get(_currentPos);
			NpcStringId npcString;
			String chat;
			
			if ((npcString = pos.getNpcString()) != null)
				getActor().broadcastChat(npcString);
			else if ((chat = pos.getChatText()) != null && chat.length() > 0)
				getActor().broadcastChat(chat);
			
			//time in millis
			long delay = pos.getDelay();
			
			//sleeps between each move
			if (delay < 0)
			{
				delay = DEFAULT_MOVE_DELAY;
				if (Config.DEVELOPER)
					_log.warning(getClass().getSimpleName() + ": Wrong Delay Set in Npc Walker Functions = " + delay + " secs, using default delay: " + DEFAULT_MOVE_DELAY + " secs instead.");
			}
			
			setWalkingToNextPoint(false);
			ThreadPoolManager.getInstance().scheduleAi(this, delay * 1000);
		}
	}
	
	/**
	 * If npc can't walk to it's target then just teleport to next point
	 * @param blocked_at_pos ignoring it
	 */
	@Override
	protected void onEvtArrivedBlocked(L2CharPosition blocked_at_pos)
	{
		_log.warning(getClass().getSimpleName() + ": " + getActor().getNpcId() + ": Blocked at rote position [" + _currentPos + "], coords: " + blocked_at_pos.x + ", " + blocked_at_pos.y + ", " + blocked_at_pos.z + ". Teleporting to next point");
		
		final L2NpcWalkerNode pos = _route.get(_currentPos);
		getActor().teleToLocation(pos.getMoveX(), pos.getMoveY(), pos.getMoveZ(), false);
		super.onEvtArrivedBlocked(blocked_at_pos);
	}
	
	@Override
	public L2NpcWalkerInstance getActor()
	{
		return (L2NpcWalkerInstance) super.getActor();
	}
	
	private boolean isWalkingToNextPoint()
	{
		return _walkingToNextPoint;
	}
	
	private void setWalkingToNextPoint(boolean value)
	{
		_walkingToNextPoint = value;
	}
}
