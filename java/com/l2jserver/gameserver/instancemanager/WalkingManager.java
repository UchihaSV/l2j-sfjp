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
package com.l2jserver.gameserver.instancemanager;

import static com.l2jserver.gameserver.ai.CtrlIntention.*;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

import jp.sf.l2j.troja.FastIntObjectMap;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.Config;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.datatables.NpcData;
import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.enums.QuestEventType;
import com.l2jserver.gameserver.idfactory.IdFactory;
import com.l2jserver.gameserver.instancemanager.tasks.StartMovingTask;
import com.l2jserver.gameserver.model.L2NpcWalkerNode;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.L2WalkRoute;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.L2WorldRegion;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.WalkInfo;
import com.l2jserver.gameserver.model.actor.L2Attackable;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.tasks.npc.walker.ArrivedTask;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.holders.NpcRoutesHolder;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.AbstractNpcInfo;
import com.l2jserver.gameserver.network.serverpackets.CreatureSay;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.gameserver.util.Broadcast;

/**
 * This class manages walking monsters.
 * @author GKR
 */
public final class WalkingManager extends DocumentParser
{
	// Repeat style:
	// 0 - go back
	// 1 - go to first point (circle style)
	// 2 - teleport to first point (conveyor style)
	// 3 - random walking between points.
	public static final byte REPEAT_GO_BACK = 0;
	public static final byte REPEAT_GO_FIRST = 1;
	public static final byte REPEAT_TELE_FIRST = 2;
	public static final byte REPEAT_RANDOM = 3;
	
	private final HashMap<String, L2WalkRoute> _routes = new HashMap<>(); // all available routes
	private final FastIntObjectMap<WalkInfo> _activeRoutes = new FastIntObjectMap<>(); // each record represents NPC, moving by predefined route from _routes, and moving progress
	private final FastIntObjectMap<NpcRoutesHolder> _routesToAttach = new FastIntObjectMap<>(); // each record represents NPC and all available routes for it
	
	protected WalkingManager()
	{
		load();
	}
	
	@Override
	public final void load()
	{
if (com.l2jserver.Config.CUSTOM_ROUTES_LOAD) {{
		final Pattern pattern = Pattern.compile("Routes.*\\.xml");	// Routes*.xml
		File[] walkerRoutesFiles = new File(Config.DATAPACK_ROOT, "data").listFiles(new FileFilter(){
			@Override public boolean accept(File file) { return file.isFile() && pattern.matcher(file.getName()).matches(); }
		});
		for (File file : walkerRoutesFiles)
		{
			_log.info(getClass().getSimpleName() + ": Loading " + file.getName());
			parseFile(file);
		}
}} else {{
		parseDatapackFile("data/Routes.xml");
}}
		_log.info(getClass().getSimpleName() + ": Loaded " + _routes.size() + " walking routes.");
	//	dump();
	}
	
	@Override
	protected void parseDocument()
	{
		Node n = getCurrentDocument().getFirstChild();
		for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
		{
			if (d.getNodeName().equals("route"))
			{
				final String routeName = parseString(d.getAttributes(), "name");
				boolean repeat = parseBoolean(d.getAttributes(), "repeat");
				String repeatStyle = d.getAttributes().getNamedItem("repeatStyle").getNodeValue();
				byte repeatType;
				if (repeatStyle.equalsIgnoreCase("back"))
				{
					repeatType = REPEAT_GO_BACK;
				}
				else if (repeatStyle.equalsIgnoreCase("cycle"))
				{
					repeatType = REPEAT_GO_FIRST;
				}
				else if (repeatStyle.equalsIgnoreCase("conveyor"))
				{
					repeatType = REPEAT_TELE_FIRST;
				}
				else if (repeatStyle.equalsIgnoreCase("random"))
				{
					repeatType = REPEAT_RANDOM;
				}
				else
				{
					repeatType = -1;
				}
				
				final List<L2NpcWalkerNode> list = new ArrayList<>();
				for (Node r = d.getFirstChild(); r != null; r = r.getNextSibling())
				{
					if (r.getNodeName().equals("point"))
					{
						NamedNodeMap attrs = r.getAttributes();
						int x = parseInteger(attrs, "X");
						int y = parseInteger(attrs, "Y");
						int z = parseInteger(attrs, "Z");
						int delay = parseInteger(attrs, "delay");
						boolean run = parseBoolean(attrs, "run");
						NpcStringId npcString = null;
						String chatString = null;
						
						Node node = attrs.getNamedItem("string");
						if (node != null)
						{
							chatString = node.getNodeValue();
						}
						else
						{
							node = attrs.getNamedItem("npcString");
							if (node != null)
							{
								npcString = NpcStringId.getNpcStringId(node.getNodeValue());
								if (npcString == null)
								{
									_log.warning(getClass().getSimpleName() + ": Unknown npcString '" + node.getNodeValue() + "' for route '" + routeName + "'");
									continue;
								}
							}
							else
							{
								node = attrs.getNamedItem("npcStringId");
								if (node != null)
								{
									npcString = NpcStringId.getNpcStringId(Integer.parseInt(node.getNodeValue()));
									if (npcString == null)
									{
										_log.warning(getClass().getSimpleName() + ": Unknown npcString '" + node.getNodeValue() + "' for route '" + routeName + "'");
										continue;
									}
								}
							}
						}
						list.add(new L2NpcWalkerNode(x, y, z, delay, run, npcString, chatString));
					}
					
					else if (r.getNodeName().equals("target"))
					{
						NamedNodeMap attrs = r.getAttributes();
						try
						{
							int npcId = Integer.parseInt(attrs.getNamedItem("id").getNodeValue());
							int x = Integer.parseInt(attrs.getNamedItem("spawnX").getNodeValue());
							int y = Integer.parseInt(attrs.getNamedItem("spawnY").getNodeValue());
							int z = Integer.parseInt(attrs.getNamedItem("spawnZ").getNodeValue());
							
							NpcRoutesHolder holder = _routesToAttach.get(npcId); if (holder == null) holder = new NpcRoutesHolder();
							holder.addRoute(routeName, new Location(x, y, z));
							_routesToAttach.put(npcId, holder);
							
							final L2NpcTemplate t = NpcData.getInstance().getTemplate(npcId);
							if (t == null)
								_log.log(Level.WARNING, "__BASENAME__: Unknown npc. " + getCurrentFile().getName() + " <target id=" + npcId + " spawnX=" + x + " spawnY=" + y + " spawnZ=" + z + " />");	//+[JOJO]
							else if (!t.canMove())
								_log.log(Level.WARNING, "__BASENAME__: Not canMove npc. " + getCurrentFile().getName() + " <target id=" + npcId + " spawnX=" + x + " spawnY=" + y + " spawnZ=" + z + " />");	//+[JOJO]
						}
						catch (Exception e)
						{
							_log.warning(getClass().getSimpleName() + ": Error in target definition for route '" + routeName + "'");
						}
					}
				}
				_routes.put(routeName, new L2WalkRoute(routeName, list, repeat, false, repeatType));
			}
		}
	}
	
	/**
	 * @param npc NPC to check
	 * @return {@code true} if given NPC, or its leader is controlled by Walking Manager and moves currently.
	 */
	public boolean isOnWalk(L2Npc npc)
	{
		L2MonsterInstance monster = null;
		
		if (npc.isMonster())
		{
			if (((L2MonsterInstance) npc).getLeader() == null)
			{
				monster = (L2MonsterInstance) npc;
			}
			else
			{
				monster = ((L2MonsterInstance) npc).getLeader();
			}
		}
		
		if (((monster != null) && !isRegistered(monster)) || !isRegistered(npc))
		{
			return false;
		}
		
		final WalkInfo walk = monster != null ? _activeRoutes.get(monster.getObjectId()) : _activeRoutes.get(npc.getObjectId());
		if (walk == null) return false;	//[JOJO] setWalker()
		if (walk.isStoppedByAttack() || walk.isSuspended())
		{
			return false;
		}
		return true;
	}
	
	public L2WalkRoute getRoute(String route)
	{
		return _routes.get(route);
	}
	
	/**
	 * @param npc NPC to check
	 * @return {@code true} if given NPC controlled by Walking Manager.
	 */
	public boolean isRegistered(L2Npc npc)
	{
		return _activeRoutes.containsKey(npc.getObjectId());
	}
	
	/**
	 * @param npc
	 * @return name of route
	 */
	public String getRouteName(L2Npc npc)
	{
		final WalkInfo walk;
		return (walk = _activeRoutes.get(npc.getObjectId())) != null ? walk.getRoute().getName() : "";
	}
	
	//[JOJO]-------------------------------------------------
	// L2Npc#isWalkerAAttackableKnownList#removeKnownObject ‘Îô—p.
	public void setWalker(L2Npc npc, boolean set)
	{
		if (set)
			_activeRoutes.put(npc.getObjectId(), null);
		else
			_activeRoutes.remove(npc.getObjectId());
	}
	//-------------------------------------------------------
	
	/**
	 * Start to move given NPC by given route
	 * @param npc NPC to move
	 * @param routeName name of route to move by
	 */
	public void startMoving(final L2Npc npc, final String routeName)
	{
		if (_routes.containsKey(routeName) && (npc != null) && !npc.isDead()) // check, if these route and NPC present
		{
			WalkInfo walk;
			if ((walk = _activeRoutes.get(npc.getObjectId())) == null) // new walk task
			{
				// only if not already moved / not engaged in battle... should not happens if called on spawn
				final CtrlIntention intention;
				if ((intention = npc.getAI().getIntention()) == CtrlIntention.AI_INTENTION_ACTIVE || intention == CtrlIntention.AI_INTENTION_IDLE)
				{
					walk = new WalkInfo(routeName);
					
					if (npc.isDebug())
					{
						walk.setLastAction(System.currentTimeMillis());
					}
					
					L2NpcWalkerNode node = walk.getCurrentNode();
					
					// adjust next waypoint, if NPC spawns at first waypoint
					if ((npc.getX() == node.getX()) && (npc.getY() == node.getY()))
					{
						walk.calculateNextNode(npc);
						node = walk.getCurrentNode();
						npc.sendDebugMessage("Route '" + routeName + "': spawn point is same with first waypoint, adjusted to next");
					}
					
					if (!npc.isInsideRadius(node, 3000, true, false))
					{
						final String message = "Route '" + routeName + "': NPC (id=" + npc.getId() + ", x=" + npc.getX() + ", y=" + npc.getY() + ", z=" + npc.getZ() + ") is too far from starting point (node x=" + node.getX() + ", y=" + node.getY() + ", z=" + node.getZ() + ", range=" + npc.calculateDistance(node, true, true) + "), walking will not start";
						_log.warning(getClass().getSimpleName() + ": " + message);
						npc.sendDebugMessage(message);
						return;
					}
					
					npc.sendDebugMessage("Starting to move at route '" + routeName + "'");
					npc.setIsRunning(node.runToLocation());
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, node);
					walk.setWalkCheckTask(ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new StartMovingTask(npc, routeName), 60000, 60000)); // start walk check task, for resuming walk after fight
					
					npc.getKnownList().startTrackingTask();
					
					_activeRoutes.put(npc.getObjectId(), walk); // register route
if (com.l2jserver.Config.NEVER_WALKER_RndWalk | com.l2jserver.Config.NEVER_WALKER_ReturnToSpawnPoint | com.l2jserver.Config.NEVER_WALKER_RandomAnimation) {{
					if (npc instanceof L2Attackable)
					{
						L2Attackable mob = (L2Attackable) npc;
						if (com.l2jserver.Config.NEVER_WALKER_RndWalk) mob.setIsNoRndWalk(true);
						if (com.l2jserver.Config.NEVER_WALKER_ReturnToSpawnPoint) mob.setCanReturnToSpawnPoint(false);
						if (com.l2jserver.Config.NEVER_WALKER_RandomAnimation) mob.setRandomAnimationEnabled(false);
					}
}}
				}
				else
				{
					npc.sendDebugMessage("Failed to start moving along route '" + routeName + "', scheduled");
					ThreadPoolManager.getInstance().scheduleGeneral(new StartMovingTask(npc, routeName), 60000);
				}
			}
			else
			// walk was stopped due to some reason (arrived to node, script action, fight or something else), resume it
			{
				final CtrlIntention intention;
				if ((intention = npc.getAI().getIntention()) == CtrlIntention.AI_INTENTION_ACTIVE || intention == CtrlIntention.AI_INTENTION_IDLE)
				{
					// Prevent call simultaneously from scheduled task and onArrived() or temporarily stop walking for resuming in future
					if (walk.isBlocked() || walk.isSuspended())
					{
						npc.sendDebugMessage("Failed to continue moving along route '" + routeName + "' (operation is blocked)");
						return;
					}
					
					walk.setBlocked(true);
					final L2NpcWalkerNode node = walk.getCurrentNode();
					npc.sendDebugMessage("Route '" + routeName + "', continuing to node " + walk.getCurrentNodeId());
					npc.setIsRunning(node.runToLocation());
					npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, node);
					walk.setBlocked(false);
					walk.setStoppedByAttack(false);
				}
				else
				{
					npc.sendDebugMessage("Failed to continue moving along route '" + routeName + "' (wrong AI state - " + npc.getAI().getIntention() + ")");
				}
			}
		}
	}
	
	/**
	 * Cancel NPC moving permanently
	 * @param npc NPC to cancel
	 */
	public synchronized void cancelMoving(L2Npc npc)
	{
		final WalkInfo walk;
		if ((walk = _activeRoutes.remove(npc.getObjectId())) != null)
		{
			walk.getWalkCheckTask().cancel(true);
			npc.getKnownList().stopTrackingTask();
		}
	}
	
	/**
	 * Resumes previously stopped moving
	 * @param npc NPC to resume
	 */
	public void resumeMoving(final L2Npc npc)
	{
		final WalkInfo walk;
		if ((walk = _activeRoutes.get(npc.getObjectId())) == null)
		{
			return;
		}
		
		walk.setSuspended(false);
		walk.setStoppedByAttack(false);
		startMoving(npc, walk.getRoute().getName());
	}
	
	/**
	 * Pause NPC moving until it will be resumed
	 * @param npc NPC to pause moving
	 * @param suspend {@code true} if moving was temporarily suspended for some reasons of AI-controlling script
	 * @param stoppedByAttack {@code true} if moving was suspended because of NPC was attacked or desired to attack
	 */
	public void stopMoving(L2Npc npc, boolean suspend, boolean stoppedByAttack)
	{
		L2MonsterInstance monster = null;
		
		if (npc.isMonster())
		{
			if (((L2MonsterInstance) npc).getLeader() == null)
			{
				monster = (L2MonsterInstance) npc;
			}
			else
			{
				monster = ((L2MonsterInstance) npc).getLeader();
			}
		}
		
		if (((monster != null) && !isRegistered(monster)) || !isRegistered(npc))
		{
			return;
		}
		
		final WalkInfo walk = monster != null ? _activeRoutes.get(monster.getObjectId()) : _activeRoutes.get(npc.getObjectId());
		
		walk.setSuspended(suspend);
		walk.setStoppedByAttack(stoppedByAttack);
		
		if (monster != null)
		{
if (com.l2jserver.Config.FIX_WALKER_ATTACK) {{
			if (monster.getAI().getIntention() != CtrlIntention.AI_INTENTION_MOVE_TO)
				return;
}}
			monster.stopMove(null);
			monster.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		}
		else
		{
if (com.l2jserver.Config.FIX_WALKER_ATTACK) {{
			if (npc.getAI().getIntention() != CtrlIntention.AI_INTENTION_MOVE_TO)
				return;
}}
			npc.stopMove(null);
			npc.getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE);
		}
	}
	
	/**
	 * Manage "node arriving"-related tasks: schedule move to next node; send ON_NODE_ARRIVED event to Quest script
	 * @param npc NPC to manage
	 * @return the true if overridden AI_INTENTION //[JOJO] --> L2CharacterAI#onEvtArrived()
	 */
	public /*[JOJO]*/boolean onArrived(final L2Npc npc)
	{
		final WalkInfo walk;
		if ((walk = _activeRoutes.get(npc.getObjectId())) != null)
		{
			// Notify quest
			List<Quest> eventQuests;
			if ((eventQuests = npc.getTemplate().getEventQuests(QuestEventType.ON_NODE_ARRIVED)) != null)
			{
				for (Quest quest : eventQuests)
				{
					quest.notifyNodeArrived(npc);
				}
			}
			
			// Opposite should not happen... but happens sometime
			if ((walk.getCurrentNodeId() >= 0) && (walk.getCurrentNodeId() < walk.getRoute().getNodesCount()))
			{
				final L2NpcWalkerNode node = walk.getRoute().getNodeList().get(walk.getCurrentNodeId());
				if (npc.isInsideRadius(node, 10, false, false))
				{
					npc.sendDebugMessage("Route '" + walk.getRoute().getName() + "', arrived to node " + walk.getCurrentNodeId());
					npc.sendDebugMessage("Done in " + ((System.currentTimeMillis() - walk.getLastAction()) / 1000) + " s");
					walk.calculateNextNode(npc);
					walk.setBlocked(true); // prevents to be ran from walk check task, if there is delay in this node.
					
					NpcStringId npcString;
					String chatText;
					if ((npcString = node.getNpcString()) != null)
					{
						Broadcast.toKnownPlayers(npc, new NpcSay(npc, Say2.NPC_ALL, npcString));
					}
					else if ((chatText = node.getChatText()) != null && !chatText.isEmpty())
					{
						Broadcast.toKnownPlayers(npc, npc.getTemplate().isUsingServerSideName()
							? new CreatureSay(npc, Say2.NPC_ALL, chatText)
							: new NpcSay(npc, Say2.NPC_ALL, chatText));
					}
					
					if (npc.isDebug())
					{
						walk.setLastAction(System.currentTimeMillis());
					}
					
if (com.l2jserver.Config.NEVER_WALKER_AI_onArrived) {{
					if (node.getDelay() == 0)
					{
					//	if (walk.isSuspended()) return false;	//TODO:Check
						final L2NpcWalkerNode nextNode = walk.getCurrentNode();
						npc.setIsRunning(nextNode.runToLocation());
						npc.getAI().setIntention(CtrlIntention.AI_INTENTION_MOVE_TO, nextNode);
						walk.setBlocked(false);
						return true;
					}
					/*else...*/
}}
					npc.getAI().setIntention(AI_INTENTION_ACTIVE);
					ThreadPoolManager.getInstance().scheduleGeneral(new ArrivedTask(npc, walk), node.getDelay() * 1000L);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Manage "on death"-related tasks: permanently cancel moving of died NPC
	 * @param npc NPC to manage
	 */
	public void onDeath(L2Npc npc)
	{
		cancelMoving(npc);
	}
	
	/**
	 * Manage "on spawn"-related tasks: start NPC moving, if there is route attached to its spawn point
	 * @param npc NPC to manage
	 */
	public void onSpawn(L2Npc npc)
	{
		final NpcRoutesHolder root;
		if ((root = _routesToAttach.get(npc.getId())) != null)
		{
			final String routeName = root.getRouteName(npc);
			if (!routeName.isEmpty())
			{
				startMoving(npc, routeName);
			}
		}
	}
	
	public static final WalkingManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final WalkingManager _instance = new WalkingManager();
	}
	
	//[JOJO]-------------------------------------------------
	private static final int MARKER_NPC = 1032467;
	private static final int MARKER_ITEM = 57;
	
	public void visualize(String routeName, L2PcInstance player)
	{
		visualize(_routes.get(routeName), player);
	}
	
	public void visualize(L2Npc walker, L2PcInstance player)
	{
		visualize(_activeRoutes.get(walker.getObjectId()).getRoute(), player);
	}
	
	public void visualize(L2WalkRoute route, L2PcInstance player)
	{
		try {
			final String name = route.getName();
			final List<L2NpcWalkerNode> WALKS = route.getNodeList();
			assert WALKS instanceof ArrayList;
			final L2NpcTemplate markerNpcTemplate = NpcData.getInstance().getTemplate(MARKER_NPC);
			int length = WALKS.size();
			for (int index = 0; index < length; ++index) {
				L2NpcWalkerNode node = WALKS.get(index);
				L2NpcWalkerNode next = WALKS.get((index + 1) % length);
				
				final int x1 = node.getX(), y1 = node.getY(), z1 = node.getZ();
				final int x2 = next.getX(), y2 = next.getY(), z2 = next.getZ();
				final double dx = x2 - x1, dy = y2 - y1, dz = z2 - z1;
				final double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
				final double pitch = 50;
				
				// npc marker
				if (markerNpcTemplate != null) {
					L2Spawn spawn = new L2Spawn(markerNpcTemplate);
					spawn.setX(x1);
					spawn.setY(y1);
					spawn.setZ(z1 + com.l2jserver.Config.NPC_SPAWN_Z_MARGIN);
					spawn.setHeading(com.l2jserver.gameserver.util.Util.calculateHeadingFrom(x1, y1, x2, y2));
					spawn.stopRespawn();
					L2Npc n = spawn.spawnOne(false);
					n.setTitle("#" + index);
					n.setName(name);
					n.broadcastPacket(new AbstractNpcInfo.NpcInfo(n, null));
				}
				
				if (route.getRepeatType() != REPEAT_GO_FIRST && index + 1 == length) break;
				
				// adena line
				for (double p = pitch; p < distance; p += pitch) {
					double a = p / distance;
					int x = x1 + (int)Math.round(dx * a);
					int y = y1 + (int)Math.round(dy * a);
					int z = z1 + (int)Math.round(dz * a) + 20;
					L2ItemInstance i = new L2ItemInstance(IdFactory.getInstance().getNextId(), MARKER_ITEM);
					if (player != null) i.setOwnerId(player.getObjectId());
					i.new ItemDropTask(i, null, x, y, z).run();
					i.setProtected(false);
					L2World.getInstance().storeObject(i);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dump()
	{
		for (L2WalkRoute route : _routes.values()) {
			final String repeatStyle;
			switch (route.getRepeatType()) {
				case REPEAT_GO_BACK: repeatStyle = "back"; break;
				case REPEAT_GO_FIRST: repeatStyle = "cycle"; break;
				case REPEAT_TELE_FIRST: repeatStyle = "conveyor"; break;
				case REPEAT_RANDOM: repeatStyle = "random"; break;
				default: repeatStyle = "?";
			}
			System.out.println("<route name=\"" + route.getName() + "\" repeat=" + route.repeatWalk() + " repeatStyle=" + repeatStyle + ">");
			for (jp.sf.l2j.troja.IntObjectMap.Entry<NpcRoutesHolder> a : _routesToAttach.entrySet()) {
				final NpcRoutesHolder nr = a.getValue();
				for (java.util.Map.Entry<String, String> c : nr.getCorrespondences().entrySet()) {
					final String uniqueKey = c.getKey();
					final String routeName = c.getValue();
					if (routeName.equals(route.getName())) {
						String[] spawn = uniqueKey.split(",");
						final int npcId = a.getKey();
						final L2NpcTemplate t = NpcData.getInstance().getTemplate(npcId);
						System.out.println("\t<target id=" + npcId + " spawnX=" + spawn[0] + " spawnY=" + spawn[1] + " spawnZ=" + spawn[2] + " /> <!-- " + (t.getTitle() + " " + t.getName()).trim() + " -->");
					}
				}
			}
			L2WorldRegion nodeWorldRegion0 = null;
			for (L2NpcWalkerNode node : route.getNodeList()) {
				L2WorldRegion nodeWorldRegion1 = L2World.getInstance().getRegion(node.getX(), node.getY());
				final String region = nodeWorldRegion0 == null             ? "<!-- Grid" + nodeWorldRegion1.getName() + " -->"
				                    : nodeWorldRegion0 != nodeWorldRegion1 ? "<!-- Grid" + nodeWorldRegion1.getName() + "#-->"
				                    : "";
				nodeWorldRegion0 = nodeWorldRegion1;
				if (node.getNpcString() != null)
					System.out.println("\t<point npcStringId=" + node.getNpcString().getId() + " X=" + node.getX() + " Y=" + node.getY() + " Z=" + node.getZ() + " delay=" + node.getDelay() + " run=" + node.runToLocation() + " />" + region);
				else if (node.getChatText() != null && node.getChatText().length() > 0)
					System.out.println("\t<point string=\"" + node.getChatText() + "\" X=" + node.getX() + " Y=" + node.getY() + " Z=" + node.getZ() + " delay=" + node.getDelay() + " run=" + node.runToLocation() + " />" + region);
				else
					System.out.println("\t<point X=" + node.getX() + " Y=" + node.getY() + " Z=" + node.getZ() + " delay=" + node.getDelay() + " run=" + node.runToLocation() + " />" + region);
			}
			System.out.println("</route>");
		}
	}
	//-------------------------------------------------------
}