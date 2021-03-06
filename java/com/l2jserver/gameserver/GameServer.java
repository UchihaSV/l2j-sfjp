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
package com.l2jserver.gameserver;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.mmocore.network.SelectorConfig;
import org.mmocore.network.SelectorThread;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.Server;
import com.l2jserver.UPnPService;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.datatables.AdminTable;
import com.l2jserver.gameserver.datatables.ArmorSetsData;
import com.l2jserver.gameserver.datatables.AugmentationData;
import com.l2jserver.gameserver.datatables.BotReportTable;
import com.l2jserver.gameserver.datatables.BuyListData;
import com.l2jserver.gameserver.datatables.CategoryData;
import com.l2jserver.gameserver.datatables.CharNameTable;
import com.l2jserver.gameserver.datatables.CharSummonTable;
import com.l2jserver.gameserver.datatables.CharTemplateTable;
import com.l2jserver.gameserver.datatables.ClanTable;
import com.l2jserver.gameserver.datatables.ClassListData;
import com.l2jserver.gameserver.datatables.CrestTable;
import com.l2jserver.gameserver.datatables.DoorTable;
import com.l2jserver.gameserver.datatables.EnchantItemData;
import com.l2jserver.gameserver.datatables.EnchantItemGroupsData;
import com.l2jserver.gameserver.datatables.EnchantItemHPBonusData;
import com.l2jserver.gameserver.datatables.EnchantItemOptionsData;
import com.l2jserver.gameserver.datatables.EnchantSkillGroupsData;
import com.l2jserver.gameserver.datatables.EventDroplist;
import com.l2jserver.gameserver.datatables.ExperienceTable;
import com.l2jserver.gameserver.datatables.FishData;
import com.l2jserver.gameserver.datatables.FishingMonstersData;
import com.l2jserver.gameserver.datatables.FishingRodsData;
import com.l2jserver.gameserver.datatables.HennaData;
import com.l2jserver.gameserver.datatables.HitConditionBonus;
import com.l2jserver.gameserver.datatables.InitialEquipmentData;
import com.l2jserver.gameserver.datatables.InitialShortcutData;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.datatables.KarmaData;
import com.l2jserver.gameserver.datatables.MerchantPriceConfigTable;
import com.l2jserver.gameserver.datatables.MultisellData;
import com.l2jserver.gameserver.datatables.NpcBufferTable;
import com.l2jserver.gameserver.datatables.NpcData;
import com.l2jserver.gameserver.datatables.OfflineTradersTable;
import com.l2jserver.gameserver.datatables.OptionsData;
import com.l2jserver.gameserver.datatables.PetDataTable;
import com.l2jserver.gameserver.datatables.RecipeData;
import com.l2jserver.gameserver.datatables.SecondaryAuthData;
import com.l2jserver.gameserver.datatables.SiegeScheduleData;
import com.l2jserver.gameserver.datatables.SkillData;
import com.l2jserver.gameserver.datatables.SkillLearnData;
import com.l2jserver.gameserver.datatables.SkillTreesData;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.datatables.StaticObjects;
import com.l2jserver.gameserver.datatables.SummonSkillsTable;
import com.l2jserver.gameserver.datatables.TeleportLocationTable;
import com.l2jserver.gameserver.datatables.TransformData;
import com.l2jserver.gameserver.datatables.UIData;
import com.l2jserver.gameserver.geoeditorcon.GeoEditorListener;
import com.l2jserver.gameserver.handler.AdminCommandHandler;
import com.l2jserver.gameserver.handler.EffectHandler;
import com.l2jserver.gameserver.handler.TelnetHandler;
import com.l2jserver.gameserver.idfactory.IdFactory;
import com.l2jserver.gameserver.instancemanager.AirShipManager;
import com.l2jserver.gameserver.instancemanager.AntiFeedManager;
import com.l2jserver.gameserver.instancemanager.AuctionManager;
import com.l2jserver.gameserver.instancemanager.BoatManager;
import com.l2jserver.gameserver.instancemanager.CHSiegeManager;
import com.l2jserver.gameserver.instancemanager.CastleManager;
import com.l2jserver.gameserver.instancemanager.CastleManorManager;
import com.l2jserver.gameserver.instancemanager.ClanHallManager;
import com.l2jserver.gameserver.instancemanager.CleanUpManager;
import com.l2jserver.gameserver.instancemanager.CoupleManager;
import com.l2jserver.gameserver.instancemanager.CursedWeaponsManager;
import com.l2jserver.gameserver.instancemanager.DayNightSpawnManager;
import com.l2jserver.gameserver.instancemanager.DimensionalRiftManager;
import com.l2jserver.gameserver.instancemanager.FortManager;
import com.l2jserver.gameserver.instancemanager.FortSiegeManager;
import com.l2jserver.gameserver.instancemanager.FourSepulchersManager;
import com.l2jserver.gameserver.instancemanager.GlobalVariablesManager;
import com.l2jserver.gameserver.instancemanager.GraciaSeedsManager;
import com.l2jserver.gameserver.instancemanager.GrandBossManager;
import com.l2jserver.gameserver.instancemanager.InstanceManager;
import com.l2jserver.gameserver.instancemanager.ItemAuctionManager;
import com.l2jserver.gameserver.instancemanager.ItemsOnGroundManager;
import com.l2jserver.gameserver.instancemanager.MailManager;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.instancemanager.MercTicketManager;
import com.l2jserver.gameserver.instancemanager.PetitionManager;
import com.l2jserver.gameserver.instancemanager.PunishmentManager;
import com.l2jserver.gameserver.instancemanager.QuestManager;
import com.l2jserver.gameserver.instancemanager.RaidBossPointsManager;
import com.l2jserver.gameserver.instancemanager.RaidBossSpawnManager;
import com.l2jserver.gameserver.instancemanager.SiegeManager;
import com.l2jserver.gameserver.instancemanager.TerritoryWarManager;
import com.l2jserver.gameserver.instancemanager.WalkingManager;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.AutoSpawnHandler;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.PartyMatchRoomList;
import com.l2jserver.gameserver.model.PartyMatchWaitingList;
import com.l2jserver.gameserver.model.entity.Hero;
import com.l2jserver.gameserver.model.entity.TvTManager;
import com.l2jserver.gameserver.model.events.EventDispatcher;
import com.l2jserver.gameserver.model.olympiad.Olympiad;
import com.l2jserver.gameserver.network.L2GameClient;
import com.l2jserver.gameserver.network.L2GamePacketHandler;
import com.l2jserver.gameserver.network.communityserver.CommunityServerThread;
import com.l2jserver.gameserver.pathfinding.PathFinding;
import com.l2jserver.gameserver.script.faenor.FaenorScriptEngine;
import com.l2jserver.gameserver.scripting.L2ScriptEngineManager;
import com.l2jserver.gameserver.scripting.Native2Ascii;
import com.l2jserver.gameserver.taskmanager.AutoAnnounceTaskManager;
import com.l2jserver.gameserver.taskmanager.KnownListUpdateTaskManager;
import com.l2jserver.gameserver.taskmanager.TaskManager;
import com.l2jserver.status.Status;
import com.l2jserver.util.DeadLockDetector;
import com.l2jserver.util.IPv4Filter;
import com.l2jserver.util.JavaTea;

public class GameServer
{
	private static final Logger _log = Logger.getLogger(GameServer.class.getName());
	
	private final SelectorThread<L2GameClient> _selectorThread;
	private final L2GamePacketHandler _gamePacketHandler;
	private final DeadLockDetector _deadDetectThread;
	private final IdFactory _idFactory;
	public static GameServer gameServer;
	private final LoginServerThread _loginThread;
	private static Status _statusServer;
	public final long serverLoadEnd;															//+[JOJO]
	@Deprecated public static final Calendar dateTimeServerStarted = Calendar.getInstance();	//-[JOJO]
	
	public long getUsedMemoryMB()
	{
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576; // ;
	}
	
	public SelectorThread<L2GameClient> getSelectorThread()
	{
		return _selectorThread;
	}
	
	public L2GamePacketHandler getL2GamePacketHandler()
	{
		return _gamePacketHandler;
	}
	
	public DeadLockDetector getDeadLockDetectorThread()
	{
		return _deadDetectThread;
	}
	
	public GameServer() throws Exception
	{
		final String GAME_SERVER = "GameServer"; //= getClass().getSimpleName();
		final long serverLoadStart = System.currentTimeMillis();
		
		gameServer = this;
		_log.finest(GAME_SERVER + ": used mem:" + getUsedMemoryMB() + "MB");
		
		if (Config.SERVER_VERSION != null)
		{
			_log.info(GAME_SERVER + ": L2J Server Version:    " + Config.SERVER_VERSION);
		}
		if (Config.DATAPACK_VERSION != null)
		{
			_log.info(GAME_SERVER + ": L2J Datapack Version:  " + Config.DATAPACK_VERSION);
		}
		
		_idFactory = IdFactory.getInstance();
		
		if (!_idFactory.isInitialized())
		{
			_log.severe(GAME_SERVER + ": Could not read object IDs from DB. Please Check Your Data.");
			throw new Exception("Could not initialize the ID factory");
		}
		
		ThreadPoolManager.getInstance();
		EventDispatcher.getInstance();
		
		new File("log/game").mkdirs();
		
		// load script engines
		printSection("Engines");
		L2ScriptEngineManager.getInstance();
		
		printSection("World");
		// start game time control early
		GameTimeController.init();
		InstanceManager.getInstance();
		L2World.getInstance();
		MapRegionManager.getInstance();
		Announcements.getInstance();
		GlobalVariablesManager.getInstance();
		
		printSection("Data");
		CategoryData.getInstance();
		SecondaryAuthData.getInstance();
		
		printSection("Skills");
		EffectHandler.getInstance().executeScript();
		EnchantSkillGroupsData.getInstance();
		SkillTreesData.getInstance();
		SkillData.getInstance();
		SummonSkillsTable.getInstance();
		
		printSection("Items");
		ItemTable.getInstance();
		EnchantItemGroupsData.getInstance();
		EnchantItemData.getInstance();
		EnchantItemOptionsData.getInstance();
		OptionsData.getInstance();
		EnchantItemHPBonusData.getInstance();
		MerchantPriceConfigTable.getInstance().loadInstances();
		BuyListData.getInstance();
		MultisellData.getInstance();
		RecipeData.getInstance();
		ArmorSetsData.getInstance();
		FishData.getInstance();
		FishingMonstersData.getInstance();
		FishingRodsData.getInstance();
		HennaData.getInstance();
		
		printSection("Characters");
		ClassListData.getInstance();
		InitialEquipmentData.getInstance();
		InitialShortcutData.getInstance();
		ExperienceTable.getInstance();
		KarmaData.getInstance();
		HitConditionBonus.getInstance();
		CharTemplateTable.getInstance();
		CharNameTable.getInstance();
		AdminTable.getInstance();
		RaidBossPointsManager.getInstance();
		PetDataTable.getInstance();
		CharSummonTable.getInstance().init();
		
		printSection("Clans");
		ClanTable.getInstance();
		CHSiegeManager.getInstance();
		ClanHallManager.getInstance();
		AuctionManager.getInstance();
		
		printSection("Geodata");
		GeoData.getInstance();
		if (Config.GEODATA == 2)
		{
			PathFinding.getInstance();
		}
		
		printSection("NPCs");
		SkillLearnData.getInstance();
		NpcData.getInstance();
		WalkingManager.getInstance();
		StaticObjects.getInstance();
		ZoneManager.getInstance();
		DoorTable.getInstance();
		ItemAuctionManager.getInstance();
		CastleManager.getInstance().loadInstances();
		NpcBufferTable.getInstance();
		DayNightSpawnManager.getInstance().trim().notifyChangeMode();
		GrandBossManager.getInstance().initZones();
		EventDroplist.getInstance();
		
		printSection("Siege");
		SiegeScheduleData.getInstance();
		SiegeManager.getInstance().getSieges();
		FortSiegeManager.getInstance();
		TerritoryWarManager.getInstance();
		CastleManorManager.getInstance();
		MercTicketManager.getInstance();
		
		printSection("Olympiad");
		Olympiad.getInstance();
		Hero.getInstance();
		
		printSection("Seven Signs");
		SevenSigns.getInstance();
		
		// Call to load caches
		printSection("Cache");
		HtmCache.getInstance();
		CrestTable.getInstance();
		TeleportLocationTable.getInstance();
		UIData.getInstance();
		PartyMatchWaitingList.getInstance();
		PartyMatchRoomList.getInstance();
		PetitionManager.getInstance();
		AugmentationData.getInstance();
		CursedWeaponsManager.getInstance();
		TransformData.getInstance();
		BotReportTable.getInstance();
		
		printSection("Scripts");
		QuestManager.getInstance();
		BoatManager.getInstance();
		AirShipManager.getInstance();
		GraciaSeedsManager.getInstance();
		
		CastleManager.getInstance().activateInstances();
		FortManager.getInstance().activateInstances();
		MerchantPriceConfigTable.getInstance().updateReferences();
		
		if (Config.SCRIPT_ENCODING != null)	//[JOJO]
		{
			Charset scriptEncoding = Charset.forName(Config.SCRIPT_ENCODING);
			Charset defaultCharset = Charset.defaultCharset();
			_log.info("Native2Ascii: Script encoding:" + scriptEncoding.name() + " / Server default charset:" + defaultCharset.name());
			if (!scriptEncoding.equals(defaultCharset))
				new Native2Ascii(new File(Config.DATAPACK_ROOT.getAbsolutePath(), "data/scripts"), Config.SCRIPT_ENCODING);
		}
		
		try
		{
			_log.info(GAME_SERVER + ": Loading server scripts:");
			final File scripts = new File(Config.DATAPACK_ROOT, "data/scripts.cfg");
			if (!Config.ALT_DEV_NO_HANDLERS || !Config.ALT_DEV_NO_QUESTS)
			{
				L2ScriptEngineManager.getInstance().executeScriptList(scripts);
			}
		}
		catch (IOException ioe)
		{
			_log.severe(GAME_SERVER + ": Failed loading scripts.cfg, scripts are not going to be loaded!");
		}
		//[JOJO]-------------------------------------------------
		// data/scripts/handlers/* スクリプトのロードが１つでも失敗するとコマンドが全滅して
		// シャットダウンすらできなくなるので、重要なコマンドのみ予備をロードしておく。
		// GMコマンドの //server_shutdown と //server_restart
		if (AdminCommandHandler.getInstance().getHandler("admin_server_shutdown") == null)
			AdminCommandHandler.getInstance().registerHandler(
					new com.l2jserver.gameserver.handlers.admincommandhandlers.AdminShutdown() );
		// telnetコマンドの shutdown と restart
		if (TelnetHandler.getInstance().getHandler("shutdown") == null)
			TelnetHandler.getInstance().registerHandler(
					new com.l2jserver.gameserver.handlers.telnethandlers.ServerHandler() );
		//-------------------------------------------------------
		
		SpawnTable.getInstance().load();
		FourSepulchersManager.getInstance().init();
		DimensionalRiftManager.getInstance();
		RaidBossSpawnManager.getInstance();
		FortManager.getInstance().loadInstances();
		
		QuestManager.getInstance().report();
		
		if (Config.SAVE_DROPPED_ITEM)
		{
			ItemsOnGroundManager.getInstance();
		}
		
		if ((Config.AUTODESTROY_ITEM_AFTER > 0) || (Config.HERB_AUTO_DESTROY_TIME > 0))
		{
			ItemsAutoDestroy.getInstance();
		}
		
		MonsterRace.getInstance();
		
		SevenSigns.getInstance().spawnSevenSignsNPC();
		SevenSignsFestival.getInstance();
		AutoSpawnHandler.getInstance();
		
		FaenorScriptEngine.getInstance();
		FaenorScriptEngine.getInstance().dump();
		// Init of a cursed weapon manager
		
		_log.info("AutoSpawnHandler: Loaded " + AutoSpawnHandler.getInstance().size() + " handlers in total.");
		
		if (Config.L2JMOD_ALLOW_WEDDING)
		{
			CoupleManager.getInstance();
		}
		
		TaskManager.getInstance();
		
		AntiFeedManager.getInstance().registerEvent(AntiFeedManager.GAME_ID);
		
		if (Config.ALLOW_MAIL)
		{
			MailManager.getInstance();
		}
		
		if (Config.ACCEPT_GEOEDITOR_CONN)
		{
			GeoEditorListener.getInstance();
		}
		
		PunishmentManager.getInstance();
		
		Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());
		
		CleanUpManager.getInstance().execute();	// [L2J_JP ADD - TSL][JOJO]
		if (Config.ALLOW_LOTTERY)
			com.l2jserver.gameserver.instancemanager.games.Lottery.getInstance();	//[JOJO]
		BuyListData.getInstance().checkAllPrice();	//[JOJO]
		
		_log.info("IdFactory: Free ObjectID's remaining: " + IdFactory.getInstance().size());
		
		TvTManager.getInstance();
		KnownListUpdateTaskManager.getInstance();
		
		if ((Config.OFFLINE_TRADE_ENABLE || Config.OFFLINE_CRAFT_ENABLE) && Config.RESTORE_OFFLINERS)
		{
			OfflineTradersTable.getInstance().restoreOfflineTraders();
		}
		
		if (Config.DEADLOCK_DETECTOR)
		{
			_deadDetectThread = new DeadLockDetector();
			_deadDetectThread.setDaemon(true);
			_deadDetectThread.start();
		}
		else
		{
			_deadDetectThread = null;
		}
		System.gc();
		// maxMemory is the upper limit the jvm can use, totalMemory the size of
		// the current allocation pool, freeMemory the unused memory in the allocation pool
		long freeMem = ((Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory()) + Runtime.getRuntime().freeMemory()) / 1048576;
		long totalMem = Runtime.getRuntime().maxMemory() / 1048576;
		_log.info(GAME_SERVER + ": Started, free memory " + freeMem + " Mb of " + totalMem + " Mb");
		Toolkit.getDefaultToolkit().beep();
		
		_loginThread = LoginServerThread.getInstance();
		_loginThread.start();
		
		CommunityServerThread.initialize();
		
		final SelectorConfig sc = new SelectorConfig();
		sc.MAX_READ_PER_PASS = Config.MMO_MAX_READ_PER_PASS;
		sc.MAX_SEND_PER_PASS = Config.MMO_MAX_SEND_PER_PASS;
		sc.SLEEP_TIME = Config.MMO_SELECTOR_SLEEP_TIME;
		sc.HELPER_BUFFER_COUNT = Config.MMO_HELPER_BUFFER_COUNT;
		sc.TCP_NODELAY = Config.MMO_TCP_NODELAY;
		
		_gamePacketHandler = new L2GamePacketHandler();
		_selectorThread = new SelectorThread<>(sc, _gamePacketHandler, _gamePacketHandler, _gamePacketHandler, new IPv4Filter());
		
		InetAddress bindAddress = null;
		if (!Config.GAMESERVER_HOSTNAME.equals("*"))
		{
			try
			{
				bindAddress = InetAddress.getByName(Config.GAMESERVER_HOSTNAME);
			}
			catch (UnknownHostException e1)
			{
				_log.log(Level.SEVERE, GAME_SERVER + ": WARNING: The GameServer bind address is invalid, using all avaliable IPs. Reason: " + e1.getMessage(), e1);
			}
		}
		
		try
		{
			_selectorThread.openServerSocket(bindAddress, Config.PORT_GAME);
			_selectorThread.start();
			_log.info(GAME_SERVER + ": now listening on: " + Config.GAMESERVER_HOSTNAME + ":" + Config.PORT_GAME);
		}
		catch (IOException e)
		{
			_log.log(Level.SEVERE, GAME_SERVER + ": FATAL: Failed to open server socket. Reason: " + e.getMessage(), e);
			System.exit(1);
		}
		_log.info(GAME_SERVER + ": Maximum numbers of connected players: " + Config.MAXIMUM_ONLINE_USERS);
		//[JOJO]-------------------------------------------------
		StringBuilder sb = new StringBuilder(256).append(GAME_SERVER + ": AllowedProtocolRevisions: ");
		for (int protocol : Config.PROTOCOL_LIST)
			sb.append(protocol).append(';');
		sb.setLength(sb.length() - 1);
		_log.info(sb.toString());
		sb = null;
		//-------------------------------------------------------
		/*long*/ serverLoadEnd = System.currentTimeMillis();	//[JOJO] public final long
		_log.info(GAME_SERVER + ": Server loaded in " + ((serverLoadEnd - serverLoadStart) / 1000) + " seconds.");
		
		printSection("UPnP");
		UPnPService.getInstance();
		
		printSection("RUN");
		AutoAnnounceTaskManager.getInstance();
	}
	
	public static void main(String[] args) throws Exception
	{
		Server.serverMode = Server.MODE_GAMESERVER;
		// Local Constants
		final String LOG_FOLDER = "log"; // Name of folder for log file
		final String LOG_NAME = "./log.cfg"; // Name of log file
		
		/*** Main ***/
		// Create log folder
		File logFolder = new File(Config.DATAPACK_ROOT, LOG_FOLDER);
		logFolder.mkdir();
		
		JavaTea.redirect(System.getProperty("tee"));	//[JOJO] java -Dtee=log/stdout.log
		
		// Create input stream for log file -- or store file data into memory
		try (InputStream is = new FileInputStream(new File(LOG_NAME)))
		{
			LogManager.getLogManager().readConfiguration(is);
		}
		
		System.out.println(com.l2jserver.util.Util.dateFormat());
		
		// Initialize config
		Config.load();
		printSection("Database");
		L2DatabaseFactory.getInstance();
	//	gameServer = new GameServer();	//-[JOJO]
		
		if (Config.IS_TELNET_ENABLED)
		{
			_statusServer = new Status(Server.serverMode);
			_statusServer.start();
		}
		else
		{
			_log.info(GameServer.class.getSimpleName() + ": Telnet server is currently disabled.");
		}
		
		gameServer = new GameServer();	//+[OJO]
		
	}
	
	public static void printSection(String s)
	{
		s = "=[ " + s + " ]";
		while (s.length() < 61)
		{
			s = "-" + s;
		}
		_log.info(s);
	}
}
