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
package com.l2jserver.gameserver.instancemanager;

import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;
import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.DoorTable;
import com.l2jserver.gameserver.datatables.NpcTable;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2DoorInstance;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.actor.instance.L2SepulcherMonsterInstance;
import com.l2jserver.gameserver.model.actor.instance.L2SepulcherNpcInstance;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.quest.QuestState;
import com.l2jserver.gameserver.model.zone.type.L2BossZone;
import com.l2jserver.gameserver.network.NpcStringId;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jserver.gameserver.util.Util;
import com.l2jserver.util.Rnd;

/**
 * @author sandman
 * 
 */
public class FourSepulchersManager
{
	protected static final Logger _log = Logger.getLogger(FourSepulchersManager.class.getName());
	
	private static final String QUEST_ID = "620_FourGoblets";
	
	private static final int ENTRANCE_PASS = 7075;
	private static final int USED_PASS = 7261;
	private static final int CHAPEL_KEY = 7260;
	private static final int ANTIQUE_BROOCH = 7262;
	
 //	protected boolean _firstTimeRun;	//-[JOJO]
	protected boolean _inEntryTime = false;
	protected boolean _inWarmUpTime = false;
	protected boolean _inAttackTime = false;
	protected boolean _inCoolDownTime = false;
	
	protected ScheduledFuture<?> _changeCoolDownTimeTask = null;
	protected ScheduledFuture<?> _changeEntryTimeTask = null;
	protected ScheduledFuture<?> _changeWarmUpTimeTask = null;
	protected ScheduledFuture<?> _changeAttackTimeTask = null;
	protected ScheduledFuture<?> _onPartyAnnihilatedTask = null;
	
	private int[][] _startHallSpawn = {
			{ 181632, -85587, -7218 },
			{ 179963, -88978, -7218 },
			{ 173217, -86132, -7218 },
			{ 175608, -82296, -7218 }
	};
	
	private int[][][] _shadowSpawnLoc = {
			{
				{ 25339, 191231, -85574, -7216, 33380 },
				{ 25349, 189534, -88969, -7216, 32768 },
				{ 25346, 173195, -76560, -7215, 49277 },
				{ 25342, 175591, -72744, -7215, 49317 }
			},
			{
				{ 25342, 191231, -85574, -7216, 33380 },
				{ 25339, 189534, -88969, -7216, 32768 },
				{ 25349, 173195, -76560, -7215, 49277 },
				{ 25346, 175591, -72744, -7215, 49317 }
			},
			{
				{ 25346, 191231, -85574, -7216, 33380 },
				{ 25342, 189534, -88969, -7216, 32768 },
				{ 25339, 173195, -76560, -7215, 49277 },
				{ 25349, 175591, -72744, -7215, 49317 }
			},
			{
				{ 25349, 191231, -85574, -7216, 33380 },
				{ 25346, 189534, -88969, -7216, 32768 },
				{ 25342, 173195, -76560, -7215, 49277 },
				{ 25339, 175591, -72744, -7215, 49317 }
			},
	};
	protected FastMap<Integer, Boolean> _archonSpawned = new FastMap<Integer, Boolean>();
	protected FastMap<Integer, Boolean> _hallInUse = new FastMap<Integer, Boolean>();
	protected FastMap<Integer, L2PcInstance> _challengers = new FastMap<Integer, L2PcInstance>();
	protected TIntObjectHashMap<int []> _startHallSpawns = new TIntObjectHashMap<int []>();
	protected TIntIntHashMap _hallGateKeepers = new TIntIntHashMap();
	protected TIntIntHashMap _keyBoxNpc = new TIntIntHashMap();
	protected TIntIntHashMap _victim = new TIntIntHashMap();
 //	protected TIntObjectHashMap<L2Spawn> _executionerSpawns = new TIntObjectHashMap<L2Spawn>();	//-[JOJO]
 //	protected TIntObjectHashMap<L2Spawn> _keyBoxSpawns = new TIntObjectHashMap<L2Spawn>();		//-[JOJO]
	protected TIntObjectHashMap<L2Spawn> _mysteriousBoxSpawns = new TIntObjectHashMap<L2Spawn>();
	protected TIntObjectHashMap<L2Spawn> _shadowSpawns = new TIntObjectHashMap<L2Spawn>();
	protected TIntObjectHashMap<FastList<L2Spawn>> _dukeFinalMobs = new TIntObjectHashMap<FastList<L2Spawn>>();
	protected TIntObjectHashMap<FastList<L2SepulcherMonsterInstance>> _dukeMobs = new TIntObjectHashMap<FastList<L2SepulcherMonsterInstance>>();
	protected TIntObjectHashMap<FastList<L2Spawn>> _emperorsGraveNpcs = new TIntObjectHashMap<FastList<L2Spawn>>();
	protected TIntObjectHashMap<FastList<L2Spawn>> _magicalMonsters = new TIntObjectHashMap<FastList<L2Spawn>>();
	protected TIntObjectHashMap<FastList<L2Spawn>> _physicalMonsters = new TIntObjectHashMap<FastList<L2Spawn>>();
	protected TIntObjectHashMap<FastList<L2SepulcherMonsterInstance>> _viscountMobs = new TIntObjectHashMap<FastList<L2SepulcherMonsterInstance>>();
	
	protected FastList<L2Spawn> _physicalSpawns;
	protected FastList<L2Spawn> _magicalSpawns;
	protected FastList<L2Spawn> _managers;
	protected FastList<L2Spawn> _dukeFinalSpawns;
	protected FastList<L2Spawn> _emperorsGraveSpawns;
	protected FastList<L2Npc> _allMobs = new FastList<L2Npc>();
	
 //	protected long _attackTimeEnd = 0;	//-[JOJO]
 //	protected long _coolDownTimeEnd = 0;	//-[JOJO]
 //	protected long _entryTimeEnd = 0;	//-[JOJO]
 //	protected long _warmUpTimeEnd = 0;	//-[JOJO]
	long _attackTimeStart;	//+[JOJO]
	
	private static final int NEW_CYCLE_MINUTE = 55;
	private static final int OUST_PLAYER_MARGIN_TIME = 60000;
	
	public static final FourSepulchersManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public void init()
	{
		if (_changeCoolDownTimeTask != null)
			_changeCoolDownTimeTask.cancel(true);
		if (_changeEntryTimeTask != null)
			_changeEntryTimeTask.cancel(true);
		if (_changeWarmUpTimeTask != null)
			_changeWarmUpTimeTask.cancel(true);
		if (_changeAttackTimeTask != null)
			_changeAttackTimeTask.cancel(true);
		
		_changeCoolDownTimeTask = null;
		_changeEntryTimeTask = null;
		_changeWarmUpTimeTask = null;
		_changeAttackTimeTask = null;
		
		_inEntryTime = false;
		_inWarmUpTime = false;
		_inAttackTime = false;
		_inCoolDownTime = false;
		
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
		_changeCoolDownTimeTask = ThreadPoolManager.getInstance().scheduleGeneral(new ChangeCoolDownTime(), 0);	//+[JOJO]
		_log.info("FourSepulchersManager: Beginning in Cooldown time");	//+[JOJO]
	}
	
	public void clean()
	{
		for (int i = 31921; i <= 31924; i++)
		{
			int[] location = _startHallSpawns.get(i);
			L2BossZone zone;
			if ((zone = GrandBossManager.getInstance().getZone(location[0],location[1],location[2])) != null)	//+[JOJO]
				zone.oustAllPlayers();
			else if (GrandBossManager.getInstance().getZones().size() > 0)
				throw new RuntimeException();
		//	GrandBossManager.getInstance().getZone(location[0],location[1],location[2]).oustAllPlayers();	//-[JOJO]
		}
		
		deleteAllMobs();
		
		closeAllDoors();
		
		_hallInUse.clear();
		_hallInUse.put(31921, false);
		_hallInUse.put(31922, false);
		_hallInUse.put(31923, false);
		_hallInUse.put(31924, false);
		
		if (_archonSpawned.size() != 0)
		{
			Set<Integer> npcIdSet = _archonSpawned.keySet();
			for (int npcId : npcIdSet)
			{
				_archonSpawned.put(npcId, false);
			}
		}
	}
	
	protected void spawnManagers() //[modify JOJO]
	{
		_managers = new FastList<L2Spawn>();
		
		for (int i = 31921; i <= 31924; i++)
		{
			L2Spawn spawnDat = SpawnTable.getInstance().getSpawnOne(i);
			_managers.add(spawnDat);
			_log.info("FourSepulchersManager: spawned " + spawnDat.getTemplate().getName());
		}
	}
	
	protected void initFixedInfo()
	{
		_startHallSpawns.put(31921, _startHallSpawn[0]);
		_startHallSpawns.put(31922, _startHallSpawn[1]);
		_startHallSpawns.put(31923, _startHallSpawn[2]);
		_startHallSpawns.put(31924, _startHallSpawn[3]);
		
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
		Connection con = null;
		
		_mysteriousBoxSpawns.clear();
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY id");
			statement.setInt(1, 0);
			ResultSet rset = statement.executeQuery();
			
			L2Spawn spawnDat;
			L2NpcTemplate template1;
			
			while (rset.next())
			{
				template1 = NpcTable.getInstance().getTemplate(rset.getInt("npc_templateid"));
				if (template1 != null)
				{
					spawnDat = new L2Spawn(template1);
					spawnDat.setAmount(rset.getInt("count"));
					spawnDat.setLocx(rset.getInt("locx"));
					spawnDat.setLocy(rset.getInt("locy"));
					spawnDat.setLocz(rset.getInt("locz"));
					spawnDat.setHeading(rset.getInt("heading"));
					spawnDat.setRespawnDelay(rset.getInt("respawn_delay"));
				//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] 地雷撤去
					int keyNpcId = rset.getInt("key_npc_id");
					_mysteriousBoxSpawns.put(keyNpcId, spawnDat);
				}
				else
				{
					_log.warning("FourSepulchersManager.LoadMysteriousBox: Data missing in NPC table for ID: "
							+ rset.getInt("npc_templateid") + ".");
				}
			}
			
			rset.close();
			statement.close();
			_log.info("FourSepulchersManager: loaded " + _mysteriousBoxSpawns.size() + " Mysterious-Box spawns.");
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "FourSepulchersManager.LoadMysteriousBox: Spawn could not be initialized: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	private void initKeyBoxSpawns() //[modify JOJO]
	{
		for (int keyNpcId : _keyBoxNpc.keys())
		{
			int spawnNpcId = _keyBoxNpc.get(keyNpcId);
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
		Connection con = null;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id");
			statement1.setInt(1, 1);
			ResultSet rset1 = statement1.executeQuery();
			
			PreparedStatement statement2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist Where key_npc_id = ? and spawntype = ? ORDER BY id");
			while (rset1.next())
			{
				int keyNpcId = rset1.getInt("key_npc_id");
				
				statement2.setInt(1, keyNpcId);
				statement2.setInt(2, 1);
				ResultSet rset2 = statement2.executeQuery();
				statement2.clearParameters();
				
				L2Spawn spawnDat;
				L2NpcTemplate template1;
				
				_physicalSpawns = new FastList<L2Spawn>();
				
				while (rset2.next())
				{
					template1 = NpcTable.getInstance().getTemplate(rset2.getInt("npc_templateid"));
					if (template1 != null)
					{
						spawnDat = new L2Spawn(template1);
						spawnDat.setAmount(rset2.getInt("count"));
						spawnDat.setLocx(rset2.getInt("locx"));
						spawnDat.setLocy(rset2.getInt("locy"));
						spawnDat.setLocz(rset2.getInt("locz"));
						spawnDat.setHeading(rset2.getInt("heading"));
						spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
					//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] 地雷撤去
						_physicalSpawns.add(spawnDat);
						loaded++;
					}
					else
					{
						_log.warning("FourSepulchersManager.LoadPhysicalMonsters: Data missing in NPC table for ID: " + rset2.getInt("npc_templateid") + ".");
					}
				}
				
				rset2.close();
				_physicalMonsters.put(keyNpcId, _physicalSpawns);
			}
			
			rset1.close();
			statement1.close();
			statement2.close();
			
			_log.info("FourSepulchersManager: loaded " + loaded + " Physical type monsters spawns.");
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "FourSepulchersManager.LoadPhysicalMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	private void loadMagicalMonsters()
	{
		
		_magicalMonsters.clear();
		
		int loaded = 0;
		Connection con = null;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id");
			statement1.setInt(1, 2);
			ResultSet rset1 = statement1.executeQuery();
			
			PreparedStatement statement2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? AND spawntype = ? ORDER BY id");
			while (rset1.next())
			{
				int keyNpcId = rset1.getInt("key_npc_id");
				
				statement2.setInt(1, keyNpcId);
				statement2.setInt(2, 2);
				ResultSet rset2 = statement2.executeQuery();
				statement2.clearParameters();
				
				L2Spawn spawnDat;
				L2NpcTemplate template1;
				
				_magicalSpawns = new FastList<L2Spawn>();
				
				while (rset2.next())
				{
					template1 = NpcTable.getInstance().getTemplate(rset2.getInt("npc_templateid"));
					if (template1 != null)
					{
						spawnDat = new L2Spawn(template1);
						spawnDat.setAmount(rset2.getInt("count"));
						spawnDat.setLocx(rset2.getInt("locx"));
						spawnDat.setLocy(rset2.getInt("locy"));
						spawnDat.setLocz(rset2.getInt("locz"));
						spawnDat.setHeading(rset2.getInt("heading"));
						spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
					//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] 地雷撤去
						_magicalSpawns.add(spawnDat);
						loaded++;
					}
					else
					{
						_log.warning("FourSepulchersManager.LoadMagicalMonsters: Data missing in NPC table for ID: " + rset2.getInt("npc_templateid") + ".");
					}
				}
				
				rset2.close();
				_magicalMonsters.put(keyNpcId, _magicalSpawns);
			}
			
			rset1.close();
			statement1.close();
			statement2.close();
			
			_log.info("FourSepulchersManager: loaded " + loaded + " Magical type monsters spawns.");
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "FourSepulchersManager.LoadMagicalMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	private void loadDukeMonsters()
	{
		_dukeFinalMobs.clear();
		_archonSpawned.clear();
		
		int loaded = 0;
		Connection con = null;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id");
			statement1.setInt(1, 5);
			ResultSet rset1 = statement1.executeQuery();
			
			PreparedStatement statement2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? AND spawntype = ? ORDER BY id");
			while (rset1.next())
			{
				int keyNpcId = rset1.getInt("key_npc_id");
				
				statement2.setInt(1, keyNpcId);
				statement2.setInt(2, 5);
				ResultSet rset2 = statement2.executeQuery();
				statement2.clearParameters();
				
				L2Spawn spawnDat;
				L2NpcTemplate template1;
				
				_dukeFinalSpawns = new FastList<L2Spawn>();
				
				while (rset2.next())
				{
					template1 = NpcTable.getInstance().getTemplate(rset2.getInt("npc_templateid"));
					if (template1 != null)
					{
						spawnDat = new L2Spawn(template1);
						spawnDat.setAmount(rset2.getInt("count"));
						spawnDat.setLocx(rset2.getInt("locx"));
						spawnDat.setLocy(rset2.getInt("locy"));
						spawnDat.setLocz(rset2.getInt("locz"));
						spawnDat.setHeading(rset2.getInt("heading"));
						spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
					//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] 地雷撤去
						_dukeFinalSpawns.add(spawnDat);
						loaded++;
					}
					else
					{
						_log.warning("FourSepulchersManager.LoadDukeMonsters: Data missing in NPC table for ID: " + rset2.getInt("npc_templateid") + ".");
					}
				}
				
				rset2.close();
				_dukeFinalMobs.put(keyNpcId, _dukeFinalSpawns);
				_archonSpawned.put(keyNpcId, false);
			}
			
			rset1.close();
			statement1.close();
			statement2.close();
			
			_log.info("FourSepulchersManager: loaded " + loaded + " Church of duke monsters spawns.");
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "FourSepulchersManager.LoadDukeMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	private void loadEmperorsGraveMonsters()
	{
		
		_emperorsGraveNpcs.clear();
		
		int loaded = 0;
		Connection con = null;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			
			PreparedStatement statement1 = con.prepareStatement("SELECT Distinct key_npc_id FROM four_sepulchers_spawnlist Where spawntype = ? ORDER BY key_npc_id");
			statement1.setInt(1, 6);
			ResultSet rset1 = statement1.executeQuery();
			
			PreparedStatement statement2 = con.prepareStatement("SELECT id, count, npc_templateid, locx, locy, locz, heading, respawn_delay, key_npc_id FROM four_sepulchers_spawnlist WHERE key_npc_id = ? and spawntype = ? ORDER BY id");
			while (rset1.next())
			{
				int keyNpcId = rset1.getInt("key_npc_id");
				
				statement2.setInt(1, keyNpcId);
				statement2.setInt(2, 6);
				ResultSet rset2 = statement2.executeQuery();
				statement2.clearParameters();
				
				L2Spawn spawnDat;
				L2NpcTemplate template1;
				
				_emperorsGraveSpawns = new FastList<L2Spawn>();
				
				while (rset2.next())
				{
					template1 = NpcTable.getInstance().getTemplate(rset2.getInt("npc_templateid"));
					if (template1 != null)
					{
						spawnDat = new L2Spawn(template1);
						spawnDat.setAmount(rset2.getInt("count"));
						spawnDat.setLocx(rset2.getInt("locx"));
						spawnDat.setLocy(rset2.getInt("locy"));
						spawnDat.setLocz(rset2.getInt("locz"));
						spawnDat.setHeading(rset2.getInt("heading"));
						spawnDat.setRespawnDelay(rset2.getInt("respawn_delay"));
					//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] 地雷撤去
						_emperorsGraveSpawns.add(spawnDat);
						loaded++;
					}
					else
					{
						_log.warning("FourSepulchersManager.LoadEmperorsGraveMonsters: Data missing in NPC table for ID: " + rset2.getInt("npc_templateid") + ".");
					}
				}
				
				rset2.close();
				_emperorsGraveNpcs.put(keyNpcId, _emperorsGraveSpawns);
			}
			
			rset1.close();
			statement1.close();
			statement2.close();
			
			_log.info("FourSepulchersManager: loaded " + loaded + " Emperor's grave NPC spawns.");
		}
		catch (Exception e)
		{
			// problem with initializing spawn, go to next one
			_log.log(Level.WARNING, "FourSepulchersManager.LoadEmperorsGraveMonsters: Spawn could not be initialized: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	protected void initLocationShadowSpawns()
	{
		int locNo = Rnd.get(4);
		final int[] gateKeeper = { 31929, 31934, 31939, 31944 };
		
		L2Spawn spawnDat;
		L2NpcTemplate template;
		
		_shadowSpawns.clear();
		
		for (int i = 0; i <= 3; i++)
		{
			int[] loc = _shadowSpawnLoc[locNo][i];
			template = NpcTable.getInstance().getTemplate(loc[0]);
			if (template != null)
			{
				try
				{
					spawnDat = new L2Spawn(template);
					spawnDat.setAmount(1);
					spawnDat.setXYZ(loc[1],loc[2],loc[3]);
					spawnDat.setHeading(loc[4]);
				//	SpawnTable.getInstance().addNewSpawn(spawnDat, false);	//[JOJO] 地雷撤去
					int keyNpcId = gateKeeper[i];
					_shadowSpawns.put(keyNpcId, spawnDat);
				}
				catch (Exception e)
				{
					_log.log(Level.SEVERE, "Error on InitLocationShadowSpawns", e);
				}
			}
			else
			{
				_log.warning("FourSepulchersManager.InitLocationShadowSpawns: Data missing in NPC table for ID: "
						+ loc[0] + ".");
			}
		}
	}
	
	protected void initExecutionerSpawns() //[modify JOJO]
	{
		for (int keyNpcId : _victim.keys())
		{
			int spawnNpcId = _victim.get(keyNpcId);
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
	
	public boolean isEntryTime()
	{
		return _inEntryTime;
	}
	
	public boolean isAttackTime()
	{
		return _inAttackTime;
	}
	
	// quests/620_FourGoblets/__init__.py --> onAdvEvent(...) --> if event == "Enter" --> here
	public synchronized void tryEntry(L2Npc npc, L2PcInstance player)
	{
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
					Util.handleIllegalPlayerAction(player, "Warning!! Character " + player.getName()
							+ " tried to enter four sepulchers with invalid npc id.", Config.DEFAULT_PUNISH);
				}
				return;
		}
		
		if (_hallInUse.get(npcId).booleanValue())
		{
			showHtmlFile(player, npcId + "-FULL.htm", npc, null);
			return;
		}
		
		if (Config.FS_PARTY_MEMBER_COUNT > 1)
		{
			if (!player.isInParty() || player.getParty().getMemberCount() < Config.FS_PARTY_MEMBER_COUNT)
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
				QuestState qs = mem.getQuestState(QUEST_ID);
				if (qs == null || (!qs.isStarted() && !qs.isCompleted()))
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
		else if (Config.FS_PARTY_MEMBER_COUNT <= 1 && player.isInParty())
		{
			if (!player.getParty().isLeader(player))
			{
				showHtmlFile(player, npcId + "-NL.htm", npc, null);
				return;
			}
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				QuestState qs = mem.getQuestState(QUEST_ID);
				if (qs == null || (!qs.isStarted() && !qs.isCompleted()))
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
			QuestState qs = player.getQuestState(QUEST_ID);
			if (qs == null || (!qs.isStarted() && !qs.isCompleted()))
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
		int[] Location = _startHallSpawns.get(npcId);
		int driftx;
		int drifty;
		
		if (Config.FS_PARTY_MEMBER_COUNT > 1)
		{
			List<L2PcInstance> members = new FastList<L2PcInstance>();
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				if (!mem.isDead() && Util.checkIfInRange(700, player, mem, true))
				{
					members.add(mem);
				}
			}
			
			for (L2PcInstance mem : members)
			{
				GrandBossManager.getInstance().getZone(Location[0], Location[1], Location[2]).allowPlayerEntry(mem, 30);
				driftx = Rnd.get(-80, 80);
				drifty = Rnd.get(-80, 80);
				mem.teleToLocation(Location[0] + driftx, Location[1] + drifty, Location[2]);
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
			
			_challengers.remove(npcId);
			_challengers.put(npcId, player);
			
			_hallInUse.remove(npcId);
			_hallInUse.put(npcId, true);
		}
		if (Config.FS_PARTY_MEMBER_COUNT <= 1 && player.isInParty())
		{
			List<L2PcInstance> members = new FastList<L2PcInstance>();
			for (L2PcInstance mem : player.getParty().getMembers())
			{
				if (!mem.isDead() && Util.checkIfInRange(700, player, mem, true))
				{
					members.add(mem);
				}
			}
			
			for (L2PcInstance mem : members)
			{
				GrandBossManager.getInstance().getZone(Location[0], Location[1], Location[2]).allowPlayerEntry(mem, 30);
				driftx = Rnd.get(-80, 80);
				drifty = Rnd.get(-80, 80);
				mem.teleToLocation(Location[0] + driftx, Location[1] + drifty, Location[2]);
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
			
			_challengers.remove(npcId);
			_challengers.put(npcId, player);
			
			_hallInUse.remove(npcId);
			_hallInUse.put(npcId, true);
		}
		else
		{
			GrandBossManager.getInstance().getZone(Location[0], Location[1], Location[2]).allowPlayerEntry(player, 30);
			driftx = Rnd.get(-80, 80);
			drifty = Rnd.get(-80, 80);
			player.teleToLocation(Location[0] + driftx, Location[1] + drifty, Location[2]);
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
			
			_challengers.remove(npcId);
			_challengers.put(npcId, player);
			
			_hallInUse.remove(npcId);
			_hallInUse.put(npcId, true);
		}
	}
	
	public void spawnMysteriousBox(int npcId)
	{
		if (!isAttackTime())
			return;
		
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
			return;
		
		FastList<L2Spawn> monsterList;
		FastList<L2SepulcherMonsterInstance> mobs = new FastList<L2SepulcherMonsterInstance>();
		L2Spawn keyBoxMobSpawn;
		
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
							keyBoxMobSpawn = new L2Spawn(template);
							keyBoxMobSpawn.setAmount(1);
							keyBoxMobSpawn.setLocx(spawnDat.getLocx());
							keyBoxMobSpawn.setLocy(spawnDat.getLocy());
							keyBoxMobSpawn.setLocz(spawnDat.getLocz());
							keyBoxMobSpawn.setHeading(spawnDat.getHeading());
							keyBoxMobSpawn.setRespawnDelay(3600);
						//	SpawnTable.getInstance().addNewSpawn(keyBoxMobSpawn, false);	//[JOJO] 地雷撤去
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
			return true;
		
		for (L2SepulcherMonsterInstance mob : mobs)
		{
			if (!mob.isDead())
				return false;
		}
		
		return true;
	}
	
	public synchronized boolean isDukeMobsAnnihilated(int npcId)
	{
		FastList<L2SepulcherMonsterInstance> mobs = _dukeMobs.get(npcId);
		
		if (mobs == null)
			return true;
		
		for (L2SepulcherMonsterInstance mob : mobs)
		{
			if (!mob.isDead())
				return false;
		}
		
		return true;
	}
	
	public void spawnKeyBox(L2Npc activeChar) //[modify JOJO]
	{
		if (!isAttackTime())
			return;
		
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
			return;
		
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
			return;
		
		if (_archonSpawned.get(npcId))
			return;
		
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
			return;
		
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
	
	protected void locationShadowSpawns()
	{
		int locNo = Rnd.get(4);
		// _log.info("FourSepulchersManager.LocationShadowSpawns: Location index
		// is " + locNo + ".");
		final int[] gateKeeper = { 31929, 31934, 31939, 31944 };
		
		L2Spawn spawnDat;
		
		for (int i = 0; i <= 3; i++)
		{
			int keyNpcId = gateKeeper[i];
			int[] loc = _shadowSpawnLoc[locNo][i];
			spawnDat = _shadowSpawns.get(keyNpcId);
			spawnDat.setXYZ(loc[1],loc[2],loc[3]);
			spawnDat.setHeading(loc[4]);
			_shadowSpawns.put(keyNpcId, spawnDat);
		}
	}
	
	public void spawnShadow(int npcId)
	{
		if (!isAttackTime())
			return;
		
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
				continue;
			
			try
			{
				if (mob.getSpawn() != null)
					mob.getSpawn().stopRespawn();
				mob.deleteMe();
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "FourSepulchersManager: Failed deleting mob.", e);
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
					_log.warning("FourSepulchersManager: Attempted to close undefined door. doorId: " + doorId);
				}
			}
			catch (Exception e)
			{
				_log.log(Level.SEVERE, "FourSepulchersManager: Failed closing door", e);
			}
		}
	}
	
	public void managerSay(int min)
	{
		// for attack phase, sending message every 5 minutes
		if (_inAttackTime)
		{
			final String msg;
			
			if (min < 5)
				return; // do not shout when < 5 minutes
			min = (min + 2) / 5 * 5;
			if (min >= Config.FS_TIME_ATTACK)
			{
				msg = /*TODO:1000457*/"ゲームが終了しました。間もなく自動テレポートします。";
			//	msg = "Game over. The teleport will appear momentarily";
			}
			else
			{
				msg = /*TODO:1000455*/"現在" + min + /*TODO:1000456*/"分が経過しました";
			//	String msg = min + " minute(s) have passed."; // now this is a proper message^^
			}
			
			for (L2Spawn temp : _managers)
			{
				if (temp == null)
				{
					_log.warning("FourSepulchersManager: managerSay(): manager is null");
					continue;
				}
				if (!(temp.getLastSpawn() instanceof L2SepulcherNpcInstance))
				{
					_log.warning("FourSepulchersManager: managerSay(): manager is not Sepulcher instance");
					continue;
				}
				// hall not used right now, so its manager will not tell you
				// anything :)
				// if you don't need this - delete next two lines.
				if (!_hallInUse.get(temp.getNpcid()).booleanValue())
					continue;
				
				((L2SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg);
			}
		}
		
		else if (_inEntryTime)
		{
			final NpcStringId msg1 = NpcStringId.YOU_MAY_NOW_ENTER_THE_SEPULCHER;
			final NpcStringId msg2 = NpcStringId.IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER;
			for (L2Spawn temp : _managers)
			{
				if (temp == null)
				{
					_log.warning("FourSepulchersManager: Something goes wrong in managerSay()...");
					continue;
				}
				if (!(temp.getLastSpawn() instanceof L2SepulcherNpcInstance))
				{
					_log.warning("FourSepulchersManager: Something goes wrong in managerSay()...");
					continue;
				}
				((L2SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg1);
				((L2SepulcherNpcInstance) temp.getLastSpawn()).sayInShout(msg2);
			}
		}
	}
	
	protected class ManagerSay implements Runnable
	{
		@Override
		public void run()
		{
			if (_inAttackTime)
			{
				Calendar tmp = Calendar.getInstance();
				tmp.setTimeInMillis(Calendar.getInstance().getTimeInMillis() - _attackTimeStart);
				int min = tmp.get(Calendar.MINUTE);
				managerSay(min);
				if (min + 5 <= Config.FS_TIME_ATTACK)
					ThreadPoolManager.getInstance().scheduleGeneral(new ManagerSay(), 5 * 60000);
			}
			else if (_inEntryTime)
				managerSay(0);
		}
	}
	
	protected class ChangeEntryTime implements Runnable
	{
		@Override
		public void run()
		{
			// _log.info("FourSepulchersManager:In Entry Time");
			_inEntryTime = true;
			_inWarmUpTime = false;
			_inAttackTime = false;
			_inCoolDownTime = false;
			
			long interval = Config.FS_TIME_ENTRY * 60000L;
			ThreadPoolManager.getInstance().scheduleGeneral(new ManagerSay(), 0);
			_changeWarmUpTimeTask = ThreadPoolManager.getInstance().scheduleEffect(new ChangeWarmUpTime(), interval);
			if (_changeEntryTimeTask != null)
			{
				_changeEntryTimeTask.cancel(true);
				_changeEntryTimeTask = null;
			}
			
		}
	}
	
	protected class ChangeWarmUpTime implements Runnable
	{
		@Override
		public void run()
		{
			// _log.info("FourSepulchersManager:In Warm-Up Time");
			_inEntryTime = true;
			_inWarmUpTime = false;
			_inAttackTime = false;
			_inCoolDownTime = false;
			
			long interval = Config.FS_TIME_WARMUP * 60000L;
			_changeAttackTimeTask = ThreadPoolManager.getInstance().scheduleGeneral(new ChangeAttackTime(), interval);
			
			if (_changeWarmUpTimeTask != null)
			{
				_changeWarmUpTimeTask.cancel(true);
				_changeWarmUpTimeTask = null;
			}
		}
	}
	
	protected class ChangeAttackTime implements Runnable
	{
		@Override
		public void run()
		{
			// _log.info("FourSepulchersManager:In Attack Time");
			_inEntryTime = false;
			_inWarmUpTime = false;
			_inAttackTime = true;
			_inCoolDownTime = false;
			
			locationShadowSpawns();
			
			spawnMysteriousBox(31921);
			spawnMysteriousBox(31922);
			spawnMysteriousBox(31923);
			spawnMysteriousBox(31924);
			
			_attackTimeStart = Calendar.getInstance().getTimeInMillis();
			
			// say task
			ThreadPoolManager.getInstance().scheduleGeneral(new ManagerSay(), 5 * 60400);
			
			long interval = Config.FS_TIME_ATTACK * 60000L;
			_changeCoolDownTimeTask = ThreadPoolManager.getInstance().scheduleGeneral(new ChangeCoolDownTime(), interval + OUST_PLAYER_MARGIN_TIME);
			
			if (_changeAttackTimeTask != null)
			{
				_changeAttackTimeTask.cancel(true);
				_changeAttackTimeTask = null;
			}
		}
	}
	
	protected class ChangeCoolDownTime implements Runnable
	{
		@Override
		public void run()
		{
			// _log.info("FourSepulchersManager:In Cool-Down Time");
			_inEntryTime = false;
			_inWarmUpTime = false;
			_inAttackTime = false;
			_inCoolDownTime = true;
			
			clean();
			
			Calendar time = Calendar.getInstance();
			// one hour = 55th min to 55 min of next hour
			if (Calendar.getInstance().get(Calendar.MINUTE) > NEW_CYCLE_MINUTE)
				time.set(Calendar.HOUR, Calendar.getInstance().get(Calendar.HOUR) + 1);
			time.set(Calendar.MINUTE, NEW_CYCLE_MINUTE);
			time.set(Calendar.SECOND, 0);		//+[JOJO] 最大１分の誤差が出てしまうので
			time.set(Calendar.MILLISECOND, 0);	//+[JOJO]
			_log.info("FourSepulchersManager: Entry time: " + com.l2jserver.util.Util.dateFormat(time));
		//	_log.info("FourSepulchersManager: Entry time: " + time.getTime());
			
			long interval = time.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
			_changeEntryTimeTask = ThreadPoolManager.getInstance().scheduleGeneral(new ChangeEntryTime(), interval);
			
			if (_changeCoolDownTimeTask != null)
			{
				_changeCoolDownTimeTask.cancel(true);
				_changeCoolDownTimeTask = null;
			}
		}
	}
	
	public TIntIntHashMap getHallGateKeepers()
	{
		return _hallGateKeepers;
	}
	
	public void showHtmlFile(L2PcInstance player, String file, L2Npc npc, L2PcInstance member)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(npc.getObjectId());
		html.setFile(player.getHtmlPrefix(), "data/html/SepulcherNpc/" + file);
		if (member != null)
			html.replace("%member%", member.getName());
		player.sendPacket(html);
	}
	
	private static class SingletonHolder
	{
		protected static final FourSepulchersManager _instance = new FourSepulchersManager();
	}
}
