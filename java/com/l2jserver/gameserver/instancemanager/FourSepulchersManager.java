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
package com.l2jserver.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;
import jp.sf.l2j.arrayMaps.SortedIntBooleanArrayMap;
import jp.sf.l2j.arrayMaps.SortedIntIntArrayMap;
import jp.sf.l2j.arrayMaps.SortedIntObjectArrayMap;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.DoorTable;
import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.instancemanager.tasks.FourSepulchersChangeCoolDownTimeTask;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.instance.L2SepulcherMonsterInstance;
import com.l2jserver.gameserver.model.actor.instance.L2SepulcherNpcInstance;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.quest.QuestState;
import com.l2jserver.gameserver.model.zone.type.L2BossZone;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jserver.gameserver.util.Util;
import com.l2jserver.util.Rnd;

/**
 * Zoey76: TODO: Use Location DTO instead of array of int.
 * @author sandman
 */
public final class FourSepulchersManager
{
	private static final Logger _log = Logger.getLogger(FourSepulchersManager.class.getName());
	
	private static final int QUEST_ID = 620;
	
	private static final int ENTRANCE_PASS = 7075;
	private static final int USED_PASS = 7261;
	private static final int CHAPEL_KEY = 7260;
	private static final int ANTIQUE_BROOCH = 7262;
	
	private static enum FourSepulchersPeriod
	{
		COOL_DOWN,	// **:**:**~00:55:00
		ENTRY,		// 00:55:00~00:58:00
		WARM_UP,	// 00:58:00~01:00:00
		ATTACK		// 01:00:00~01:50:00
	}
	
	private FourSepulchersPeriod _currentPeriod;
	private ScheduledFuture<?> _changePeriodTask;
	
	// @formatter:off
	private Location[] START_HALL_SPAWN =
	{
		new Location(181632, -85587, -7218, 0),
		new Location(179963, -88978, -7218, 0),
		new Location(173217, -86132, -7218, 0),
		new Location(175608, -82296, -7218, 0),
	};
	
	private final int[] DUKES_HALL_GATEKEEPER =
	{
		31929,
		31934,
		31939,
		31944
	};
	
	private final int[] SHADOW_OF_HALISHA =
	{
		25339,
		25349,
		25346,
		25342,
	};
	
	private final Location[] SHADOW_SPAWN =
	{
		new Location(191231, -85574, -7216, 33380),
		new Location(189534, -88969, -7216, 32768),
		new Location(173195, -76560, -7215, 49277),
		new Location(175591, -72744, -7215, 49317),
	};
	// @formatter:on
	
	protected SortedIntBooleanArrayMap _archonSpawned = new SortedIntBooleanArrayMap();
	protected SortedIntBooleanArrayMap _hallInUse = new SortedIntBooleanArrayMap();
//	protected FastMap<Integer, L2PcInstance> _challengers = new FastMap<>();
	protected SortedIntObjectArrayMap<Location> _startHallSpawns = new SortedIntObjectArrayMap<>();
	protected SortedIntIntArrayMap _hallGateKeepers = new SortedIntIntArrayMap();
	protected SortedIntIntArrayMap _keyBoxNpc = new SortedIntIntArrayMap();
	protected SortedIntIntArrayMap _victim = new SortedIntIntArrayMap();
 //	protected SortedIntObjectArrayMap<L2Spawn> _executionerSpawns = new SortedIntObjectArrayMap<>();	//-[JOJO]
 //	protected SortedIntObjectArrayMap<L2Spawn> _keyBoxSpawns = new SortedIntObjectArrayMap<>();		//-[JOJO]
	protected SortedIntObjectArrayMap<L2Spawn> _mysteriousBoxSpawns = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<L2Spawn> _shadowSpawns = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<FastList<L2Spawn>> _dukeFinalMobs = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<FastList<L2SepulcherMonsterInstance>> _dukeMobs = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<FastList<L2Spawn>> _emperorsGraveNpcs = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<FastList<L2Spawn>> _magicalMonsters = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<FastList<L2Spawn>> _physicalMonsters = new SortedIntObjectArrayMap<>();
	protected SortedIntObjectArrayMap<FastList<L2SepulcherMonsterInstance>> _viscountMobs = new SortedIntObjectArrayMap<>();
	
 //	protected FastList<L2Spawn> _physicalSpawns;
 //	protected FastList<L2Spawn> _magicalSpawns;
	protected FastList<L2Spawn> _managers;
 //	protected FastList<L2Spawn> _dukeFinalSpawns;
 //	protected FastList<L2Spawn> _emperorsGraveSpawns;
	protected FastList<L2Npc> _allMobs = new FastList<>();
	
	public static final int NEW_CYCLE_MINUTE = 55;	// min
	public static final int OUST_PLAYER_MARGIN_TIME = 60000;	// sec
	public static final int ATTACK_ELASPED_INTERVAL = 5;	// min
	
	private long _attackTimeStart;
	
	public void init()
	{
		if (_changePeriodTask != null)
		{
			_changePeriodTask.cancel(false);
			_changePeriodTask = null;
		}
		
		_currentPeriod = null;
		
	//	_firstTimeRun = true;
		initFixedInfo();
		loadMysteriousBox();
		initKeyBoxSpawns();
		loadPhysicalMonsters();
		loadMagicalMonsters();
		initLocationShadowSpawns();
		initExecutionerSpawns();
		loadDukeMonsters();
		loadEmperorsGraveMonsters();
		spawnManagers();
	//	timeSelector();
		_changePeriodTask = ThreadPoolManager.getInstance().scheduleGeneral(new FourSepulchersChangeCoolDownTimeTask(), 0);	//+[JOJO]
		_log.info(getClass().getSimpleName() + ": Beginning in Cooldown time");	//+[JOJO]
	}
	
	public void clean()
	{
		for (Location location : _startHallSpawns.values())
		{
			L2BossZone zone;
			if ((zone = GrandBossManager.getInstance().getZone(location.getX(), location.getY(), location.getZ())) != null)
				zone.oustAllPlayers();
			else if (GrandBossManager.getInstance().getZones().size() > 0)
				throw new RuntimeException();
		}
		
		deleteAllMobs();
		
		closeAllDoors();
		
		_hallInUse.fill(false);
		
		_archonSpawned.fill(false);
	}
	
	protected void spawnManagers() //[modify JOJO]
	{
		_managers = new FastList<>();
		
		for (int i = 31921; i <= 31924; i++)
		{
			L2Spawn spawnDat = SpawnTable.getInstance().getSpawnOne(i);
			_managers.add(spawnDat);
			_log.info(getClass().getSimpleName() + ": spawned " + spawnDat.getTemplate().getName());
		}
	}
	
	protected void initFixedInfo()
	{
		_startHallSpawns.put(31921, START_HALL_SPAWN[0]);
		_startHallSpawns.put(31922, START_HALL_SPAWN[1]);
		_startHallSpawns.put(31923, START_HALL_SPAWN[2]);
		_startHallSpawns.put(31924, START_HALL_SPAWN[3]);
		
		_hallInUse.put(31921, false);
		_hallInUse.put(31922, false);
		_hallInUse.put(31923, false);
		_hallInUse.put(31924, false);
		
		_hallGateKeepers.put(31925, 25150012);
		_hallGateKeepers.put(31926, 25150013);
		_hallGateKeepers.put(31927, 25150014);
		_hallGateKeepers.put(31928, 25150015);
		_hallGateKeepers.put(31929, 25150016);
		_hallGateKeepers.put(31930, 25150002);
		_hallGateKeepers.put(31931, 25150003);
		_hallGateKeepers.put(31932, 25150004);
		_hallGateKeepers.put(31933, 25150005);
		_hallGateKeepers.put(31934, 25150006);
		_hallGateKeepers.put(31935, 25150032);
		_hallGateKeepers.put(31936, 25150033);
		_hallGateKeepers.put(31937, 25150034);
		_hallGateKeepers.put(31938, 25150035);
		_hallGateKeepers.put(31939, 25150036);
		_hallGateKeepers.put(31940, 25150022);
		_hallGateKeepers.put(31941, 25150023);
		_hallGateKeepers.put(31942, 25150024);
		_hallGateKeepers.put(31943, 25150025);
		_hallGateKeepers.put(31944, 25150026);
		
		_keyBoxNpc.put(18120, 31455);
		_keyBoxNpc.put(18121, 31455);
		_keyBoxNpc.put(18122, 31455);
		_keyBoxNpc.put(18123, 31455);
		_keyBoxNpc.put(18124, 31456);
		_keyBoxNpc.put(18125, 31456);
		_keyBoxNpc.put(18126, 31456);
		_keyBoxNpc.put(18127, 31456);
		_keyBoxNpc.put(18128, 31457);
		_keyBoxNpc.put(18129, 31457);
		_keyBoxNpc.put(18130, 31457);
		_keyBoxNpc.put(18131, 31457);
		_keyBoxNpc.put(18149, 31458);
		_keyBoxNpc.put(18150, 31459);
		_keyBoxNpc.put(18151, 31459);
		_keyBoxNpc.put(18152, 31459);
		_keyBoxNpc.put(18153, 31459);
		_keyBoxNpc.put(18154, 31460);
		_keyBoxNpc.put(18155, 31460);
		_keyBoxNpc.put(18156, 31460);
		_keyBoxNpc.put(18157, 31460);
		_keyBoxNpc.put(18158, 31461);
		_keyBoxNpc.put(18159, 31461);
		_keyBoxNpc.put(18160, 31461);
		_keyBoxNpc.put(18161, 31461);
		_keyBoxNpc.put(18162, 31462);
		_keyBoxNpc.put(18163, 31462);
		_keyBoxNpc.put(18164, 31462);
		_keyBoxNpc.put(18165, 31462);
		_keyBoxNpc.put(18183, 31463);
		_keyBoxNpc.put(18184, 31464);
		_keyBoxNpc.put(18212, 31465);
		_keyBoxNpc.put(18213, 31465);
		_keyBoxNpc.put(18214, 31465);
		_keyBoxNpc.put(18215, 31465);
		_keyBoxNpc.put(18216, 31466);
		_keyBoxNpc.put(18217, 31466);
		_keyBoxNpc.put(18218, 31466);
		_keyBoxNpc.put(18219, 31466);
		
		_victim.put(18150, 18158);
		_victim.put(18151, 18159);
		_victim.put(18152, 18160);
		_victim.put(18153, 18161);
		_victim.put(18154, 18162);
		_victim.put(18155, 18163);
		_victim.put(18156, 18164);
		_victim.put(18157, 18165);
	}
	
	private void loadMysteriousBox()
	{
		_mysteriousBoxSpawns.clear();
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY id"))
		{
			ps.setInt(1, 0);
			try (ResultSet rs = ps.executeQuery())
			{
				while (rs.next())
				{
					L2NpcTemplate template1 = NpcTable.getInstance().getTemplate(rs.getInt("npc_templateid"));
					if (template1 != null)
					{
						L2Spawn spawnDat = new L2Spawn(template1);
						spawnDat.setAmount(rs.getInt("count"));
						spawnDat.setX(rs.getInt("locx"));
						spawnDat.setY(rs.getInt("locy"));
						spawnDat.setZ(rs.getInt("locz"));
						spawnDat.setHeading(rs.getInt("heading"));
						spawnDat.setRespawnDelay(rs.getInt("respawn_delay"));
					//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] ínóãìPãé
						int keyNpcId = rs.getInt("key_npc_id");
						_mysteriousBoxSpawns.put(keyNpcId, spawnDat);
					}
					else
					{
						_log.warning("FourSepulchersManager.LoadMysteriousBox: Data missing in NPC table for ID: " + rs.getInt("npc_templateid") + ".");
					}
				}
			}
			_log.info(getClass().getSimpleName() + ": loaded " + _mysteriousBoxSpawns.size() + " Mysterious-Box spawns.");
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "FourSepulchersManager.LoadMysteriousBox: Spawn could not be initialized: " + e.getMessage(), e);
		}
	}
	
	private void initKeyBoxSpawns() //[modify JOJO]
	{
		// Zoey76: TODO: Replace iteration over keys with iteration over entries to avoid calling get method inside the loop.
		for (int spawnNpcId : _keyBoxNpc.values())
		{
			try
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(spawnNpcId);
				if (template == null) throw new AssertionError();
				new L2Spawn(template); /*Test only.*/
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, "FourSepulchersManager.InitKeyBoxSpawns: Spawn could not be initialized: " + e.getMessage(), e);
			}
		}
	}
	
	private void loadPhysicalMonsters()
	{
		_physicalMonsters.clear();
		
		int loaded = 0;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id"))
		{
			ps1.setInt(1, 1);
			try (ResultSet rs1 = ps1.executeQuery();
				PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist Where key_npc_id = ? and spawntype = ? ORDER BY id"))
			{
				while (rs1.next())
				{
					FastList<L2Spawn> physicalSpawns = new FastList<>();
					int keyNpcId = rs1.getInt("key_npc_id");
					
					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 1);
					try (ResultSet rs2 = ps2.executeQuery())
					{
						while (rs2.next())
						{
							L2NpcTemplate template1 = NpcTable.getInstance().getTemplate(rs2.getInt("npc_templateid"));
							if (template1 != null)
							{
								L2Spawn spawnDat = new L2Spawn(template1);
								spawnDat.setAmount(rs2.getInt("count"));
								spawnDat.setX(rs2.getInt("locx"));
								spawnDat.setY(rs2.getInt("locy"));
								spawnDat.setZ(rs2.getInt("locz"));
								spawnDat.setHeading(rs2.getInt("heading"));
								spawnDat.setRespawnDelay(rs2.getInt("respawn_delay"));
							//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] ínóãìPãé
								physicalSpawns.add(spawnDat);
								loaded++;
							}
							else
							{
								_log.warning("FourSepulchersManager.LoadPhysicalMonsters: Data missing in NPC table for ID: " + rs2.getInt("npc_templateid") + ".");
							}
						}
					}
					ps2.clearParameters();
					_physicalMonsters.put(keyNpcId, physicalSpawns);
				}
			}
			_log.info(getClass().getSimpleName() + ": loaded " + loaded + " Physical type monsters spawns.");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "FourSepulchersManager.LoadPhysicalMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
	}
	
	private void loadMagicalMonsters()
	{
		_magicalMonsters.clear();
		
		int loaded = 0;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id"))
		{
			ps1.setInt(1, 2);
			try (ResultSet rs1 = ps1.executeQuery();
				PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? AND spawntype = ? ORDER BY id"))
			{
				while (rs1.next())
				{
					FastList<L2Spawn> magicalSpawns = new FastList<>();
					int keyNpcId = rs1.getInt("key_npc_id");
					
					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 2);
					try (ResultSet rset2 = ps2.executeQuery())
					{
						while (rset2.next())
						{
							L2NpcTemplate template1 = NpcTable.getInstance().getTemplate(rset2.getInt("npc_templateid"));
							if (template1 != null)
							{
								L2Spawn spawnDat = new L2Spawn(template1);
								spawnDat.setAmount(rset2.getInt("count"));
								spawnDat.setX(rset2.getInt("locx"));
								spawnDat.setY(rset2.getInt("locy"));
								spawnDat.setZ(rset2.getInt("locz"));
								spawnDat.setHeading(rset2.getInt("heading"));
								spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
							//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] ínóãìPãé
								magicalSpawns.add(spawnDat);
								loaded++;
							}
							else
							{
								_log.warning("FourSepulchersManager.LoadMagicalMonsters: Data missing in NPC table for ID: " + rset2.getInt("npc_templateid") + ".");
							}
						}
					}
					ps2.clearParameters();
					_magicalMonsters.put(keyNpcId, magicalSpawns);
				}
			}
			_log.info(getClass().getSimpleName() + ": loaded " + loaded + " Magical type monsters spawns.");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "FourSepulchersManager.LoadMagicalMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
	}
	
	private void loadDukeMonsters()
	{
		_dukeFinalMobs.clear();
		_archonSpawned.clear();
		
		int loaded = 0;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id"))
		{
			ps1.setInt(1, 5);
			try (ResultSet rs1 = ps1.executeQuery();
				PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? AND spawntype = ? ORDER BY id"))
			{
				while (rs1.next())
				{
					FastList<L2Spawn> dukeFinalSpawns = new FastList<>();
					int keyNpcId = rs1.getInt("key_npc_id");
					
					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 5);
					try (ResultSet rset2 = ps2.executeQuery())
					{
						ps2.clearParameters();
						
						while (rset2.next())
						{
							L2NpcTemplate template1 = NpcTable.getInstance().getTemplate(rset2.getInt("npc_templateid"));
							if (template1 != null)
							{
								L2Spawn spawnDat = new L2Spawn(template1);
								spawnDat.setAmount(rset2.getInt("count"));
								spawnDat.setX(rset2.getInt("locx"));
								spawnDat.setY(rset2.getInt("locy"));
								spawnDat.setZ(rset2.getInt("locz"));
								spawnDat.setHeading(rset2.getInt("heading"));
								spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
							//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] ínóãìPãé
								dukeFinalSpawns.add(spawnDat);
								loaded++;
							}
							else
							{
								_log.warning("FourSepulchersManager.LoadDukeMonsters: Data missing in NPC table for ID: " + rset2.getInt("npc_templateid") + ".");
							}
						}
					}
					ps2.clearParameters();
					_dukeFinalMobs.put(keyNpcId, dukeFinalSpawns);
					_archonSpawned.put(keyNpcId, false);
				}
			}
			_log.info(getClass().getSimpleName() + ": loaded " + loaded + " Church of duke monsters spawns.");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "FourSepulchersManager.LoadDukeMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
	}
	
	private void loadEmperorsGraveMonsters()
	{
		_emperorsGraveNpcs.clear();
		
		int loaded = 0;
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id"))
		{
			ps1.setInt(1, 6);
			try (ResultSet rs1 = ps1.executeQuery();
				PreparedStatement ps2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? and spawntype = ? ORDER BY id"))
			{
				while (rs1.next())
				{
					FastList<L2Spawn> _emperorsGraveSpawns = new FastList<>();
					int keyNpcId = rs1.getInt("key_npc_id");
					
					ps2.setInt(1, keyNpcId);
					ps2.setInt(2, 6);
					try (ResultSet rs2 = ps2.executeQuery())
					{
						while (rs2.next())
						{
							L2NpcTemplate template1 = NpcTable.getInstance().getTemplate(rs2.getInt("npc_templateid"));
							if (template1 != null)
							{
								L2Spawn spawnDat = new L2Spawn(template1);
								spawnDat.setAmount(rs2.getInt("count"));
								spawnDat.setX(rs2.getInt("locx"));
								spawnDat.setY(rs2.getInt("locy"));
								spawnDat.setZ(rs2.getInt("locz"));
								spawnDat.setHeading(rs2.getInt("heading"));
								spawnDat.setRespawnDelay(rs2.getInt("respawn_delay"));
							//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] ínóãìPãé
								_emperorsGraveSpawns.add(spawnDat);
								loaded++;
							}
							else
							{
								_log.warning("FourSepulchersManager.LoadEmperorsGraveMonsters: Data missing in NPC table for ID: " + rs2.getInt("npc_templateid") + ".");
							}
						}
					}
					ps2.clearParameters();
					_emperorsGraveNpcs.put(keyNpcId, _emperorsGraveSpawns);
				}
			}
			_log.info(getClass().getSimpleName() + ": loaded " + loaded + " Emperor's grave NPC spawns.");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "FourSepulchersManager.LoadEmperorsGraveMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
	}
	
	private static void shuffle(int[] array)
	{
		for (int i = array.length; --i > 0;)
		{
			int j = Rnd.get(i);
			int swap = array[i];
			array[i] = array[j];
			array[j] = swap;
		}
	}
	
	protected void initLocationShadowSpawns()
	{
		_shadowSpawns.clear();
		
		shuffle(SHADOW_OF_HALISHA);
		
		for (int i = 0; i < 4; i++)
		{
			int keyNpcId = DUKES_HALL_GATEKEEPER[i];
			int spawnNpcId = SHADOW_OF_HALISHA[i];
			Location loc = SHADOW_SPAWN[i];
			L2NpcTemplate template = NpcTable.getInstance().getTemplate(spawnNpcId);
			if (template != null)
			{
				try
				{
					L2Spawn spawnDat;
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setXYZ(loc.getX(), loc.getY(), loc.getZ());
					spawnDat.setHeading(loc.getHeading());
				//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] ínóãìPãé
					_shadowSpawns.put(keyNpcId, spawnDat);
				}
				catch (SecurityException | ClassNotFoundException | NoSuchMethodException e)
				{
					_log.log(Level.SEVERE, "Error on InitLocationShadowSpawns", e);
				}
			}
			else
			{
				_log.warning("FourSepulchersManager.InitLocationShadowSpawns: Data missing in NPC table for ID: " + spawnNpcId + ".");
			}
		}
	}
	
	protected void initExecutionerSpawns() //[modify JOJO]
	{
		for (int spawnNpcId : _victim.values())
		{
			try
			{
				L2NpcTemplate template = NpcTable.getInstance().getTemplate(spawnNpcId);
				if (template == null) throw new AssertionError();
				new L2Spawn(template); /*Test only.*/
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, "FourSepulchersManager.InitExecutionerSpawns: Spawn could not be initialized: " + e.getMessage(), e);
			}
		}
	}
	
	public void setChangePeriodTask(ScheduledFuture<?> task)
	{
		_changePeriodTask = task;
	}
	
	public void setAttackTimeStart(long attackTimeStart)
	{
		_attackTimeStart = attackTimeStart;
	}
	
	public long getAttackTimeStart()
	{
		return _attackTimeStart;
	}
	
	public boolean isAttackTime()
	{
		return _currentPeriod == FourSepulchersPeriod.ATTACK;
	}
	
	public void setAttackTime()
	{
		_currentPeriod = FourSepulchersPeriod.ATTACK;
	}
	
	public boolean isCoolDownTime()
	{
		return _currentPeriod == FourSepulchersPeriod.COOL_DOWN;
	}
	
	public void setCoolDownTime()
	{
		_currentPeriod = FourSepulchersPeriod.COOL_DOWN;
	}
	
	public boolean isEntryTime()
	{
		return _currentPeriod == FourSepulchersPeriod.ENTRY;
	}
	
	public void setEntryTime()
	{
		_currentPeriod = FourSepulchersPeriod.ENTRY;
	}
	
	public boolean isWarmUpTime()
	{
		return _currentPeriod == FourSepulchersPeriod.WARM_UP;
	}
	
	public void setWarmUpTime()
	{
		_currentPeriod = FourSepulchersPeriod.WARM_UP;
	}
	
	// quests/620_FourGoblets/__init__.py --> onAdvEvent(...) --> if event == "Enter" --> here
	public synchronized void tryEntry(L2Npc npc, L2PcInstance player)
	{
		Quest hostQuest = QuestManager.getInstance().getQuest(QUEST_ID);
		if (hostQuest == null)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't find quest: " + QUEST_ID);
			return;
		}
		int npcId = npc.getNpcId();
		switch (npcId)
		{
		// ID ok
			case 31921:
			case 31922:
			case 31923:
			case 31924:
				break;
			// ID not ok
			default:
				if (!player.isGM())
				{
					_log.warning("Player " + player.getName() + "(" + player.getObjectId() + ") tried to cheat in four sepulchers.");
					Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName() + " tried to enter four sepulchers with invalid npc id.", Config.DEFAULT_PUNISH);
				}
				return;
		}
		
		if (_hallInUse.get(npcId))
		{
			showHtmlFile(player, npcId + "-FULL.htm", npc, null);
			return;
		}
		
		if (Config.FS_PARTY_MEMBER_COUNT > 1)
		{
			if (!player.isInParty() || (player.getParty().getMemberCount() < Config.FS_PARTY_MEMBER_COUNT))
			{
				showHtmlFile(player, npcId + "-SP.htm", npc, null);
				return;
			}
			
			if (!player.getParty().isLeader(player))
			{
				showHtmlFile(player, npcId + "-NL.htm", npc, null);
				return;
			}
			
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				QuestState qs = mem.getQuestState(hostQuest.getName());
				if ((qs == null) || (!qs.isStarted() && !qs.isCompleted()))
				{
					showHtmlFile(player, npcId + "-NS.htm", npc, mem);
					return;
				}
				if (mem.getInventory().getItemByItemId(ENTRANCE_PASS) == null)
				{
					showHtmlFile(player, npcId + "-SE.htm", npc, mem);
					return;
				}
				
				if (player.getWeightPenalty() >= 3)
				{
					mem.sendPacket(SystemMessageId.INVENTORY_LESS_THAN_80_PERCENT);
					return;
				}
			}
		}
		else if ((Config.FS_PARTY_MEMBER_COUNT <= 1) && player.isInParty())
		{
			if (!player.getParty().isLeader(player))
			{
				showHtmlFile(player, npcId + "-NL.htm", npc, null);
				return;
			}
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				QuestState qs = mem.getQuestState(hostQuest.getName());
				if ((qs == null) || (!qs.isStarted() && !qs.isCompleted()))
				{
					showHtmlFile(player, npcId + "-NS.htm", npc, mem);
					return;
				}
				if (mem.getInventory().getItemByItemId(ENTRANCE_PASS) == null)
				{
					showHtmlFile(player, npcId + "-SE.htm", npc, mem);
					return;
				}
				
				if (player.getWeightPenalty() >= 3)
				{
					mem.sendPacket(SystemMessageId.INVENTORY_LESS_THAN_80_PERCENT);
					return;
				}
			}
		}
		else
		{
			QuestState qs = player.getQuestState(hostQuest.getName());
			if ((qs == null) || (!qs.isStarted() && !qs.isCompleted()))
			{
				showHtmlFile(player, npcId + "-NS.htm", npc, player);
				return;
			}
			if (player.getInventory().getItemByItemId(ENTRANCE_PASS) == null)
			{
				showHtmlFile(player, npcId + "-SE.htm", npc, player);
				return;
			}
			
			if (player.getWeightPenalty() >= 3)
			{
				player.sendPacket(SystemMessageId.INVENTORY_LESS_THAN_80_PERCENT);
				return;
			}
		}
		
		if (!isEntryTime())
		{
			showHtmlFile(player, npcId + "-NE.htm", npc, null);
			return;
		}
		
		showHtmlFile(player, npcId + "-OK.htm", npc, null);
		
		entry(npcId, player);
	}
	
	private void entry(int npcId, L2PcInstance player)
	{
		Location location = _startHallSpawns.get(npcId);
		
		if (Config.FS_PARTY_MEMBER_COUNT > 1)
		{
			List<L2PcInstance> members = new FastList<>();
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				if (!mem.isDead() && Util.checkIfInRange(700, player, mem, true))
				{
					members.add(mem);
				}
			}
			
			for (L2PcInstance mem : members)
			{
				GrandBossManager.getInstance().getZone(location.getX(), location.getY(), location.getZ()).allowPlayerEntry(mem, 30);
				int driftx = Rnd.get(-80, 80);
				int drifty = Rnd.get(-80, 80);
				mem.teleToLocation(location.getX() + driftx, location.getY() + drifty, location.getZ());
				mem.destroyItemByItemId("Quest", ENTRANCE_PASS, 1, mem, true);
				if (mem.getInventory().getItemByItemId(ANTIQUE_BROOCH) == null)
				{
					mem.addItem("Quest", USED_PASS, 1, mem, true);
				}
				
				L2ItemInstance hallsKey = mem.getInventory().getItemByItemId(CHAPEL_KEY);
				if (hallsKey != null)
				{
					mem.destroyItemByItemId("Quest", CHAPEL_KEY, hallsKey.getCount(), mem, true);
				}
			}
			
		//	_challengers.remove(npcId);
		//	_challengers.put(npcId, player);
			
		//	_hallInUse.remove(npcId);
			_hallInUse.put(npcId, true);
		}
		if ((Config.FS_PARTY_MEMBER_COUNT <= 1) && player.isInParty())
		{
			List<L2PcInstance> members = new FastList<>();
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				if (!mem.isDead() && Util.checkIfInRange(700, player, mem, true))
				{
					members.add(mem);
				}
			}
			
			for (L2PcInstance mem : members)
			{
				GrandBossManager.getInstance().getZone(location.getX(), location.getY(), location.getZ()).allowPlayerEntry(mem, 30);
				int driftx = Rnd.get(-80, 80);
				int drifty = Rnd.get(-80, 80);
				mem.teleToLocation(location.getX() + driftx, location.getY() + drifty, location.getZ());
				mem.destroyItemByItemId("Quest", ENTRANCE_PASS, 1, mem, true);
				if (mem.getInventory().getItemByItemId(ANTIQUE_BROOCH) == null)
				{
					mem.addItem("Quest", USED_PASS, 1, mem, true);
				}
				
				L2ItemInstance hallsKey = mem.getInventory().getItemByItemId(CHAPEL_KEY);
				if (hallsKey != null)
				{
					mem.destroyItemByItemId("Quest", CHAPEL_KEY, hallsKey.getCount(), mem, true);
				}
			}
			
		//	_challengers.remove(npcId);
		//	_challengers.put(npcId, player);
			
		//	_hallInUse.remove(npcId);
			_hallInUse.put(npcId, true);
		}
		else
		{
			GrandBossManager.getInstance().getZone(location.getX(), location.getY(), location.getZ()).allowPlayerEntry(player, 30);
			int driftx = Rnd.get(-80, 80);
			int drifty = Rnd.get(-80, 80);
			player.teleToLocation(location.getX() + driftx, location.getY() + drifty, location.getZ());
			player.destroyItemByItemId("Quest", ENTRANCE_PASS, 1, player, true);
			if (player.getInventory().getItemByItemId(ANTIQUE_BROOCH) == null)
			{
				player.addItem("Quest", USED_PASS, 1, player, true);
			}
			
			L2ItemInstance hallsKey = player.getInventory().getItemByItemId(CHAPEL_KEY);
			if (hallsKey != null)
			{
				player.destroyItemByItemId("Quest", CHAPEL_KEY, hallsKey.getCount(), player, true);
			}
			
		//	_challengers.remove(npcId);
		//	_challengers.put(npcId, player);
			
		//	_hallInUse.remove(npcId);
			_hallInUse.put(npcId, true);
		}
	}
	
	public void spawnMysteriousBox(int npcId)
	{
		if (!isAttackTime())
		{
			return;
		}
		
		L2Spawn spawnDat = _mysteriousBoxSpawns.get(npcId);
		if (spawnDat != null)
		{
			_allMobs.add(spawnDat.doSpawn());
			spawnDat.stopRespawn();
		}
	}
	
	public void spawnMonster(int npcId)
	{
		if (!isAttackTime())
		{
			return;
		}
		
		FastList<L2Spawn> monsterList;
		FastList<L2SepulcherMonsterInstance> mobs = new FastList<>();
		
		if (Rnd.get(2) == 0)
		{
			monsterList = _physicalMonsters.get(npcId);
		}
		else
		{
			monsterList = _magicalMonsters.get(npcId);
		}
		
		if (monsterList != null)
		{
			boolean spawnKeyBoxMob = false;
			boolean spawnedKeyBoxMob = false;
			
			for (L2Spawn spawnDat : monsterList)
			{
				if (spawnedKeyBoxMob)
				{
					spawnKeyBoxMob = false;
				}
				else
				{
					switch (npcId)
					{
						case 31469:
						case 31474:
						case 31479:
						case 31484:
							if (Rnd.get(48) == 0)
							{
								spawnKeyBoxMob = true;
								// _log.info("FourSepulchersManager.SpawnMonster:
								// Set to spawn Church of Viscount Key Mob.");
							}
							break;
						default:
							spawnKeyBoxMob = false;
					}
				}
				
				L2SepulcherMonsterInstance mob = null;
				
				if (spawnKeyBoxMob)
				{
					try
					{
						L2NpcTemplate template = NpcTable.getInstance().getTemplate(18149);
						if (template != null)
						{
							L2Spawn keyBoxMobSpawn = new L2Spawn(template);
							keyBoxMobSpawn.setAmount(1);
							keyBoxMobSpawn.setLocation(spawnDat.getLocation());
							keyBoxMobSpawn.setRespawnDelay(3600);
						//	SpawnTable.getInstance().addNewSpawn(keyBoxMobSpawn, false);	//[JOJO] ínóãìPãé
							mob = (L2SepulcherMonsterInstance) keyBoxMobSpawn.doSpawn();
							keyBoxMobSpawn.stopRespawn();
						}
						else
						{
							_log.warning("FourSepulchersManager.SpawnMonster: Data missing in NPC table for ID: 18149");
						}
					}
					catch (Exception e)
					{
						_log.log(Level.WARNING, "FourSepulchersManager.SpawnMonster: Spawn could not be initialized: " + e.getMessage(), e);
					}
					
					spawnedKeyBoxMob = true;
				}
				else
				{
					mob = (L2SepulcherMonsterInstance) spawnDat.doSpawn();
					spawnDat.stopRespawn();
				}
				
				if (mob != null)
				{
					mob.mysteriousBoxId = npcId;
					switch (npcId)
					{
						case 31469:
						case 31474:
						case 31479:
						case 31484:
						case 31472:
						case 31477:
						case 31482:
						case 31487:
							mobs.add(mob);
					}
					_allMobs.add(mob);
				}
			}
			
			switch (npcId)
			{
				case 31469:
				case 31474:
				case 31479:
				case 31484:
					_viscountMobs.put(npcId, mobs);
					break;
				
				case 31472:
				case 31477:
				case 31482:
				case 31487:
					_dukeMobs.put(npcId, mobs);
					break;
			}
		}
	}
	
	public synchronized boolean isViscountMobsAnnihilated(int npcId)
	{
		FastList<L2SepulcherMonsterInstance> mobs = _viscountMobs.get(npcId);
		
		if (mobs == null)
		{
			return true;
		}
		
		for (L2SepulcherMonsterInstance mob : mobs)
		{
			if (!mob.isDead())
			{
				return false;
			}
		}
		
		return true;
	}
	
	public synchronized boolean isDukeMobsAnnihilated(int npcId)
	{
		FastList<L2SepulcherMonsterInstance> mobs = _dukeMobs.get(npcId);
		
		if (mobs == null)
		{
			return true;
		}
		
		for (L2SepulcherMonsterInstance mob : mobs)
		{
			if (!mob.isDead())
			{
				return false;
			}
		}
		
		return true;
	}
	
	public void spawnKeyBox(L2Npc activeChar) //[modify JOJO]
	{
		if (!isAttackTime())
		{
			return;
		}
		
		int keyNpcId = activeChar.getNpcId();
		int spawnNpcId = _keyBoxNpc.get(keyNpcId);
		try
		{
			L2NpcTemplate template = NpcTable.getInstance().getTemplate(spawnNpcId);
			if (template == null) throw new AssertionError();

			L2Spawn spawnDat = new L2Spawn(template);
			spawnDat.setAmount(1);
			spawnDat.setXYZ(activeChar.getX(), activeChar.getY(), activeChar.getZ());
			spawnDat.setHeading(activeChar.getHeading());
			spawnDat.setRespawnDelay(3600);
			_allMobs.add(spawnDat.doSpawn());
			spawnDat.stopRespawn();
		}
		catch (Exception e)
		{
			_log.warning("FourSepulchersManager.spawnKeyBox(" + keyNpcId + "): Data missing in NPC table for ID: " + spawnNpcId + ".");
		}
	}
	
	public void spawnExecutionerOfHalisha(L2Npc activeChar) //[modify JOJO]
	{
		if (!isAttackTime())
		{
			return;
		}
		
		int keyNpcId = activeChar.getNpcId();
		int spawnNpcId = _victim.get(keyNpcId);
		try
		{
			L2NpcTemplate template = NpcTable.getInstance().getTemplate(spawnNpcId);
			if (template == null) throw new AssertionError();
			
			L2Spawn spawnDat = new L2Spawn(template);
			spawnDat.setAmount(1);
			spawnDat.setXYZ(activeChar.getX(), activeChar.getY(), activeChar.getZ());
			spawnDat.setHeading(activeChar.getHeading());
			spawnDat.setRespawnDelay(3600);
			_allMobs.add(spawnDat.doSpawn());
			spawnDat.stopRespawn();
		}
		catch (Exception e)
		{
			_log.warning("FourSepulchersManager.spawnExecutionerOfHalisha(" + keyNpcId + "): Data missing in NPC table for ID: " + spawnNpcId + ".");
		}
	}
	
	public void spawnArchonOfHalisha(int npcId)
	{
		if (!isAttackTime())
		{
			return;
		}
		
		if (_archonSpawned.get(npcId))
		{
			return;
		}
		
		FastList<L2Spawn> monsterList = _dukeFinalMobs.get(npcId);
		
		if (monsterList != null)
		{
			for (L2Spawn spawnDat : monsterList)
			{
				L2SepulcherMonsterInstance mob = (L2SepulcherMonsterInstance) spawnDat.doSpawn();
				spawnDat.stopRespawn();
				
				if (mob != null)
				{
					mob.mysteriousBoxId = npcId;
					_allMobs.add(mob);
				}
			}
			_archonSpawned.put(npcId, true);
		}
	}
	
	public void spawnEmperorsGraveNpc(int npcId)
	{
		if (!isAttackTime())
		{
			return;
		}
		
		FastList<L2Spawn> monsterList = _emperorsGraveNpcs.get(npcId);
		
		if (monsterList != null)
		{
			for (L2Spawn spawnDat : monsterList)
			{
				_allMobs.add(spawnDat.doSpawn());
				spawnDat.stopRespawn();
			}
		}
	}
	
	public void locationShadowSpawns()
	{
		initLocationShadowSpawns();
	}
	
	public void spawnShadow(int npcId)
	{
		if (!isAttackTime())
		{
			return;
		}
		
		L2Spawn spawnDat = _shadowSpawns.get(npcId);
		if (spawnDat != null)
		{
			L2SepulcherMonsterInstance mob = (L2SepulcherMonsterInstance) spawnDat.doSpawn();
			spawnDat.stopRespawn();
			
			if (mob != null)
			{
				mob.mysteriousBoxId = npcId;
				_allMobs.add(mob);
			}
		}
	}
	
	public void deleteAllMobs()
	{
		for (L2Npc mob : _allMobs)
		{
			if (mob == null)
			{
				continue;
			}
			
			try
			{
				if (mob.getSpawn() != null)
				{
					mob.getSpawn().stopRespawn();
				}
				mob.deleteMe();
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, getClass().getSimpleName() + ": Failed deleting mob.", e);
			}
		}
		_allMobs.clear();
	}
	
	protected void closeAllDoors()
	{
		for (int doorId : _hallGateKeepers.values())
		{
			try
			{
				L2DoorInstance door = DoorTable.getInstance().getDoor(doorId);
				if (door != null)
				{
					door.closeMe();
				}
				else
				{
					_log.warning(getClass().getSimpleName() + ": Attempted to close undefined door. doorId: " + doorId);
				}
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, getClass().getSimpleName() + ": Failed closing door", e);
			}
		}
	}
	
	public void managerSay(int min)
	{
		// for attack phase, sending message every 5 minutes
		if (isAttackTime())
		{
			final String msg;
			
			if (min < ATTACK_ELASPED_INTERVAL)
			{
				return; // do not shout when < 5 minutes
			}
			min = (min + ATTACK_ELASPED_INTERVAL / 2) / ATTACK_ELASPED_INTERVAL * ATTACK_ELASPED_INTERVAL;
			if (min >= Config.FS_TIME_ATTACK)
			{
				msg = /*TODO:1000457*/"ÉQÅ[ÉÄÇ™èIóπÇµÇ‹ÇµÇΩÅBä‘Ç‡Ç»Ç≠é©ìÆÉeÉåÉ|Å[ÉgÇµÇ‹Ç∑ÅB";
			//	msg = NpcStringId.GAME_OVER_THE_TELEPORT_WILL_APPEAR_MOMENTARILY;
			}
			else
			{
				msg = /*TODO:1000455*/"åªç›" + min + /*TODO:1000456*/"ï™Ç™åoâﬂÇµÇ‹ÇµÇΩ";
			//	msg = NpcStringId.MINUTES_HAVE_PASSED;
			}
			
			for (L2Spawn temp : _managers)
			{
				if (temp == null)
				{
					_log.warning(getClass().getSimpleName() + ": managerSay(): manager is null");
					continue;
				}
				if (!(temp.getLastSpawn() instanceof L2SepulcherNpcInstance))
				{
					_log.warning(getClass().getSimpleName() + ": managerSay(): manager is not Sepulcher instance");
					continue;
				}
				// hall not used right now, so its manager will not tell you
				// anything :)
				// if you don't need this - delete next two lines.
				if (!_hallInUse.get(temp.getNpcid()))
				{
					continue;
				}
				
				((L2SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg);
			}
		}
		
		else if (isEntryTime())
		{
			final NpcStringId msg1 = NpcStringId.YOU_MAY_NOW_ENTER_THE_SEPULCHER;
			final NpcStringId msg2 = NpcStringId.IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER;
			for (L2Spawn temp : _managers)
			{
				if (temp == null)
				{
					_log.warning(getClass().getSimpleName() + ": Something goes wrong in managerSay()...");
					continue;
				}
				if (!(temp.getLastSpawn() instanceof L2SepulcherNpcInstance))
				{
					_log.warning(getClass().getSimpleName() + ": Something goes wrong in managerSay()...");
					continue;
				}
				((L2SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg1);
				((L2SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg2);
			}
		}
	}
	
	public SortedIntIntArrayMap getHallGateKeepers()
	{
		return _hallGateKeepers;
	}
	
	public void showHtmlFile(L2PcInstance player, String file, L2Npc npc, L2PcInstance member)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
		html.setFile(player.getHtmlPrefix(), "data/html/SepulcherNpc/" + file);
		if (member != null)
		{
			html.replace("%member%", member.getName());
		}
		player.sendPacket(html);
	}
	
	public static final FourSepulchersManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final FourSepulchersManager _instance = new FourSepulchersManager();
	}
}
