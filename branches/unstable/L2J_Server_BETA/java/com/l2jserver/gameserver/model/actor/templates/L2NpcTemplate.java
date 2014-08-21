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
package com.l2jserver.gameserver.model.actor.templates;

import static com.l2jserver.gameserver.datatables.StringIntern.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.datatables.NpcData;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.enums.AISkillScope;
import com.l2jserver.gameserver.enums.AIType;
import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.enums.NpcRace;
import com.l2jserver.gameserver.enums.QuestEventType;
import com.l2jserver.gameserver.enums.Sex;
import com.l2jserver.gameserver.model.L2MinionData;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.base.ClassId;
import com.l2jserver.gameserver.model.drops.DropListScope;
import com.l2jserver.gameserver.model.drops.IDropItem;
import com.l2jserver.gameserver.model.holders.ItemHolder;
import com.l2jserver.gameserver.model.interfaces.IIdentifiable;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.stats.MoveType;
import com.l2jserver.gameserver.util.UnmodifiableArrayList;

/**
 * NPC template.
 * @author Nos
 */
public final class L2NpcTemplate extends L2CharTemplate implements IIdentifiable
{
	private static final Logger _log = Logger.getLogger(L2NpcTemplate.class.getName());
	
	private int _id;
	private int _displayId;
	private byte _level;
	private String _type;
	private String _name;
	private boolean _usingServerSideName;
	private String _title;
	private boolean _usingServerSideTitle;
	private StatsSet _parameters;
	private NpcRace _race;
	private Sex _sex;
	private int _chestId;
	private int _rhandId;
	private int _lhandId;
	private int _weaponEnchant;
	private double _expRate;
	private double _sp;
	private double _raidPoints;
	private boolean _unique;
	private boolean _attackable;
	private boolean _targetable;
	private boolean _undying;
	private boolean _showName;
	private boolean _flying;
	private boolean _canMove;
	private boolean _noSleepMode;
	private boolean _passableDoor;
	private boolean _hasSummoner;
	private boolean _canBeSown;
	private int _corpseTime;
	private AIType _aiType;
	private int _aggroRange;
	private int _clanHelpRange;
	private int _knownRange;	//+[JOJO]
	private int _dodge;
	private boolean _isChaos;
	private boolean _isAggressive;
	private int _soulShot;
	private int _spiritShot;
	private int _soulShotChance;
	private int _spiritShotChance;
	private int _minSkillChance;
	private int _maxSkillChance;
	private int _primarySkillId;
	private int _shortRangeSkillId;
	private int _shortRangeSkillChance;
	private int _longRangeSkillId;
	private int _longRangeSkillChance;
	private L2Skill[] _skills;	//[JOJO] -Map<Integer, L2Skill>
	private Map<AISkillScope, List<L2Skill>> _aiSkillLists;	//[JOJO] -Map<AISkillScope, List<L2Skill>>
	private int[] _clans;		//[JOJO] -Set<Integer>
	private int[] _enemyClans;	//[JOJO] -Set<Integer>
	private Map<DropListScope, List<IDropItem>> _dropLists;
	private double _collisionRadiusGrown;
	private double _collisionHeightGrown;
	
	private List<L2MinionData> _minions = emptyArrayList();
	private List<ClassId> _teachInfo = emptyArrayList();
	private final FastMap<QuestEventType, List<Quest>> _questEvents = new FastMap<>();	//[JOJO] -ConcurrentHashMap
	
	/**
	 * Constructor of L2Character.
	 * @param set The StatsSet object to transfer data to the method
	 */
	public L2NpcTemplate(StatsSet set)
	{
		super(set);
	}
	
	@Override
	public void set(StatsSet set)
	{
		super.set(set);
		_id = set.getInt("id");
		_displayId = set.getInt("displayId", _id);
		_level = set.getByte("level", (byte) 70);
		_type = intern(set.getString("type", "L2Npc"));
		_name = intern(set.getString("name", ""));
		_usingServerSideName = set.getBoolean("usingServerSideName", false);
		_title = intern(set.getString("title", ""));
		_usingServerSideTitle = set.getBoolean("usingServerSideTitle", false);
		_race = set.getEnum("race", NpcRace.class, NpcRace.ETC);
		_sex = set.getEnum("sex", Sex.class, Sex.ETC);
		
		_chestId = set.getInt("chestId", 0);
		_rhandId = set.getInt("rhandId", 0);
		_lhandId = set.getInt("lhandId", 0);
		_weaponEnchant = set.getInt("weaponEnchant", 0);
		
		_expRate = set.getDouble("expRate", 0);
		_sp = set.getDouble("sp", 0);
		_raidPoints = set.getDouble("raidPoints", 0);
		
		_unique = set.getBoolean("unique", false);
		_attackable = set.getBoolean("attackable", true);
		_targetable = set.getBoolean("targetable", true);
		_undying = set.getBoolean("undying", true);
		_showName = set.getBoolean("showName", true);
		_flying = set.getBoolean("flying", false);
		_canMove = set.getBoolean("canMove", true);
		_noSleepMode = set.getBoolean("noSleepMode", false);
		_passableDoor = set.getBoolean("passableDoor", false);
		_hasSummoner = set.getBoolean("hasSummoner", false);
		_canBeSown = set.getBoolean("canBeSown", false);
		
		_corpseTime = set.getInt("corpseTime", Config.DEFAULT_CORPSE_TIME);
		
		_aiType = set.getEnum("aiType", AIType.class, AIType.FIGHTER);
		_aggroRange = set.getInt("aggroRange", 0);
		_clanHelpRange = set.getInt("clanHelpRange", 0);
		_dodge = set.getInt("dodge", 0);
		_isChaos = set.getBoolean("isChaos", false);
		_isAggressive = isAggressiveType(_type, set.getBoolean("isAggressive", true));
	//	_isAggressive = set.getBoolean("isAggressive", true);
		
		_soulShot = set.getInt("soulShot", 0);
		_spiritShot = set.getInt("spiritShot", 0);
		_soulShotChance = set.getInt("shotShotChance", 0);
		_spiritShotChance = set.getInt("spiritShotChance", 0);
		
		_minSkillChance = set.getInt("minSkillChance", 7);
		_maxSkillChance = set.getInt("maxSkillChance", 15);
		_primarySkillId = set.getInt("primarySkillId", 0);
		_shortRangeSkillId = set.getInt("shortRangeSkillId", 0);
		_shortRangeSkillChance = set.getInt("shortRangeSkillChance", 0);
		_longRangeSkillId = set.getInt("longRangeSkillId", 0);
		_longRangeSkillChance = set.getInt("longRangeSkillChance", 0);
		
		_collisionRadiusGrown = set.getDouble("collisionRadiusGrown", 0);
		_collisionHeightGrown = set.getDouble("collisionHeightGrown", 0);
		
		//[JOJO]-------------------------------------------------
		// L2J_Server_BETA rev.6388 L2J_DataPack_BETA rev.10173
		// npc.sql の `aggro` フィールドが XML に正しくコンバートされていない不具合の補正.
		// AttackableKnownList.getDistanceToWatchObject(L2Object)
		// int max = Math.max(300, Math.max(getActiveChar().getAggroRange(), getActiveChar().getTemplate().getClanHelpRange()));
		_knownRange = 300;
		if (_knownRange < _aggroRange) _knownRange = _aggroRange;
		if (_knownRange < _clanHelpRange) _knownRange = _clanHelpRange;
		_isAggressive = isAggressiveType(_type, _isAggressive);
		if (_aggroRange == 0) _isAggressive = false;
		if (!_isAggressive) _aggroRange = 0;
		//-------------------------------------------------------
if (com.l2jserver.Config.FIX_NPC_XML_CANMOVE) {{
		if (InstanceType.isType(_type, InstanceType.L2Attackable) && !canMove(this)) {
			if (com.l2jserver.Config.FIX_NPC_XML_CANMOVE_LOG) if (_canMove) System.out.println("__BASENAME__:__LINE__:(NonMoveMonsters) canMove=" + _canMove + " baseWalkSpd=" + getBaseMoveSpeed(MoveType.WALK) + " baseRunSpd=" + getBaseMoveSpeed(MoveType.RUN) + " " + _race.name() + " " + _aiType.name() + " " + _type + " " + _id + " " + com.l2jserver.util.Util.concat_ws(" ", _title, _name));
			_canMove = false;
		}
}}
	}
	
	//[JOJO]-------------------------------------------------
	public static boolean isAggressiveType(String type, boolean isAggressive)
	{
		return
		  // @Override public boolean isAggressive() { return true; }
		  InstanceType.isTypes(type, InstanceType.L2ControllableMobInstance, InstanceType.L2FestivalMonsterInstance, InstanceType.L2FriendlyMobInstance) ? true
		  // @Override public boolean isAggressive() { return getTemplate().isAggressive() && !isEventMob(); }
		: InstanceType.isTypes(type, InstanceType.L2MonsterInstance, InstanceType.L2GuardInstance) ? isAggressive
		  // L2NpcInstance ...
		: false;

	}
	
	public static final boolean canMove(L2NpcTemplate template)
	{
if (com.l2jserver.Config.FIX_NPC_XML_CANMOVE) {{
		return !(template._aiType == AIType.CORPSE
			|| template.getBaseMoveSpeed(MoveType.WALK) <= 1f && template.getBaseMoveSpeed(MoveType.RUN) <= 1f);
}} else {{
		return true;
}}
	}
	public static final boolean isNoRndWalk(L2NpcTemplate template)
	{
if (com.l2jserver.Config.NEVER_RandomWalk_IF_CORPSE) {{
		return !template.canMove()
			|| template.getAIType() == AIType.CORPSE
			|| template.getBaseMoveSpeed(MoveType.WALK) <= 1f;
}} else {{
		return false;
}}
	}
	//-------------------------------------------------------
	
	@Override
	public int getId()
	{
		return _id;
	}
	
	public int getDisplayId()
	{
		return _displayId;
	}
	
	public byte getLevel()
	{
		return _level;
	}
	
	public String getType()
	{
		return _type;
	}
	
	public boolean isType(String type)
	{
		return getType().equalsIgnoreCase(type);
	}
	
	public String getName()
	{
		return _name;
	}
	
	public boolean isUsingServerSideName()
	{
		return _usingServerSideName;
	}
	
	public String getTitle()
	{
		return _title;
	}
	
	public boolean isUsingServerSideTitle()
	{
		return _usingServerSideTitle;
	}
	
	public boolean hasParameters()
	{
		return _parameters != null;
	}
	
	public StatsSet getParameters()
	{
		return _parameters;
	}
	
	public void setParameters(StatsSet set)
	{
		_parameters = set;
	}
	
	public NpcRace getRace()
	{
		return _race;
	}
	
	public Sex getSex()
	{
		return _sex;
	}
	
	public int getChestId()
	{
		return _chestId;
	}
	
	public int getRHandId()
	{
		return _rhandId;
	}
	
	public int getLHandId()
	{
		return _lhandId;
	}
	
	public int getWeaponEnchant()
	{
		return _weaponEnchant;
	}
	
	public double getExpRate()
	{
		return _expRate;
	}
	
	public double getSP()
	{
		return _sp;
	}
	
	public double getRaidPoints()
	{
		return _raidPoints;
	}
	
	public boolean isUnique()
	{
		return _unique;
	}
	
	public boolean isAttackable()
	{
		return _attackable;
	}
	
	public boolean isTargetable()
	{
		return _targetable;
	}
	
	public boolean isUndying()
	{
		return _undying;
	}
	
	public boolean isShowName()
	{
		return _showName;
	}
	
	public boolean isFlying()
	{
		return _flying;
	}
	
	public boolean canMove()
	{
		return _canMove;
	}
	//[JOJO]-------------------------------------------------
	public void setCanMove(boolean value)
	{
		_canMove = value;
	}
	//-------------------------------------------------------
	
	public boolean isNoSleepMode()
	{
		return _noSleepMode;
	}
	
	public boolean isPassableDoor()
	{
		return _passableDoor;
	}
	
	public boolean hasSummoner()
	{
		return _hasSummoner;
	}
	
	public boolean canBeSown()
	{
		return _canBeSown;
	}
	
	public int getCorpseTime()
	{
		return _corpseTime;
	}
	
	public AIType getAIType()
	{
		return _aiType;
	}
	
	public int getAggroRange()
	{
		return _aggroRange;
	}
	
	public int getClanHelpRange()
	{
		return _clanHelpRange;
	}
	
	//[JOJO]-------------------------------------------------
	public int getKnownRange()
	{
		return _knownRange;
	}
	//-------------------------------------------------------
	
	public int getDodge()
	{
		return _dodge;
	}
	
	public boolean isChaos()
	{
		return _isChaos;
	}
	
	public boolean isAggressive()
	{
		return _isAggressive;
	}
	
	public int getSoulShot()
	{
		return _soulShot;
	}
	
	public int getSpiritShot()
	{
		return _spiritShot;
	}
	
	public int getSoulShotChance()
	{
		return _soulShotChance;
	}
	
	public int getSpiritShotChance()
	{
		return _spiritShotChance;
	}
	
	public int getMinSkillChance()
	{
		return _minSkillChance;
	}
	
	public int getMaxSkillChance()
	{
		return _maxSkillChance;
	}
	
	public int getPrimarySkillId()
	{
		return _primarySkillId;
	}
	
	public int getShortRangeSkillId()
	{
		return _shortRangeSkillId;
	}
	
	public int getShortRangeSkillChance()
	{
		return _shortRangeSkillChance;
	}
	
	public int getLongRangeSkillId()
	{
		return _longRangeSkillId;
	}
	
	public int getLongRangeSkillChance()
	{
		return _longRangeSkillChance;
	}
	
	@Override
	public L2Skill[] getSkills()	//[JOJO] -Map<Integer, L2Skill>
	{
		return _skills;
	}
	
	public void setSkills(ArrayList<L2Skill> skills)
	{
		_skills = skills != null && skills.size() > 0 ? skills.toArray(L2Skill.EMPTY_SKILL_LIST) : L2Skill.EMPTY_SKILL_LIST;
	}
	
	public List<L2Skill> getAISkills(AISkillScope aiSkillScope)	//[JOJO] -List<L2Skill>
	{
		final List<L2Skill> aiSkills = _aiSkillLists.get(aiSkillScope);
		return aiSkills != null ? aiSkills : Collections.<L2Skill> emptyList();
	}
	
	public void setAISkillLists(EnumMap<AISkillScope, List<L2Skill>> aiSkillLists)
	{
		_aiSkillLists = aiSkillLists != null && !aiSkillLists.isEmpty() ? Collections.unmodifiableMap(aiSkillLists) : Collections.<AISkillScope, List<L2Skill>> emptyMap();
		if (aiSkillLists != null)
		{
			for (Entry<AISkillScope, List<L2Skill>> e : aiSkillLists.entrySet())
			{
				List<L2Skill> aiSkills = e.getValue();
				if (aiSkills != null)
					e.setValue(new UnmodifiableArrayList<>(aiSkills));
			}
		}
	}
	
	public int[] getClans()
	{
		return _clans;
	}
	
	/**
	 * @param clans A sorted array of clan ids
	 */
	public void setClans(int[] clans)
	{
		_clans = clans;
	}
	
	/**
	 * @param clans A set of clan names to check if they belong to this NPC template clans.
	 * @return {@code true} if at least one of the clan names belong to this NPC template clans, {@code false} otherwise.
	 */
	public boolean isClan(int[] clans)
	{
		// Using local variable for the sake of reloading since it can be turned to null.
		final int[] clanSet = _clans;
		
		if (clanSet == null || clans == null)
			return false;
		
		if (clanSet == clans)
			return true;
		
if (com.l2jserver.Config.NPCDATA_CLAN_ALL) {{
		if (clanSet[0] == NpcData.CLAN_ALL)	// <clan>ALL</clan>
			return true;
}}
		
		for (int clanId : clans)
			if (com.l2jserver.gameserver.util.Util.contains(clanSet, clanId))
				return true;
		
		return false;
	}
	
	public int[] getEnemyClans()
	{
		return _enemyClans;
	}
	
	/**
	 * @param enemyClans A sorted array of enemy clan ids
	 */
	public void setEnemyClans(int[] enemyClans)
	{
		_enemyClans = enemyClans;
	}
	
	/**
	 * @param clans A set of clan names to check if they belong to this NPC template enemy clans.
	 * @return {@code true} if at least one of the clan names belong to this NPC template enemy clans, {@code false} otherwise.
	 */
	public boolean isEnemyClan(int[] clans)
	{
		// Using local variable for the sake of reloading since it can be turned to null.
		final int[] enemyClans = _enemyClans;
		
		if (enemyClans == null || clans == null)
			return false;
		
		if (enemyClans == clans)
			return true;
		
if (com.l2jserver.Config.NPCDATA_CLAN_ALL) {{
		if (enemyClans[0] == NpcData.CLAN_ALL)	// <clan>ALL</clan>
			return true;
}}
		
		for (int clanId : clans)
			if (com.l2jserver.gameserver.util.Util.contains(enemyClans, clanId))
				return true;
		
		return false;
	}
	
	//[JOJO]-------------------------------------------------
	public String getClanNames()
	{
		return NpcData.getInstance().toClanNames(_clans);
	}
	
	public String getEnemyClanNames()
	{
		return NpcData.getInstance().toClanNames(_enemyClans);
	}
	//-------------------------------------------------------
	
	public Map<DropListScope, List<IDropItem>> getDropLists()
	{
		return _dropLists;
	}
	
	public void setDropLists(Map<DropListScope, List<IDropItem>> dropLists)
	{
		_dropLists = dropLists != null ? Collections.unmodifiableMap(dropLists) : null;
	}
	
	public List<IDropItem> getDropList(DropListScope dropListScope)
	{
		Map<DropListScope, List<IDropItem>> dropLists = _dropLists;
		return dropLists != null ? dropLists.get(dropListScope) : null;
	}
	
	public List<ItemHolder> calculateDrops(DropListScope dropListScope, L2Character victim, L2Character killer)
	{
		List<IDropItem> dropList = getDropList(dropListScope);
		if (dropList == null)
		{
			return null;
		}
		
		List<ItemHolder> calculatedDrops = null;
		for (IDropItem dropItem : dropList)
		{
			List<ItemHolder> drops = dropItem.calculateDrops(victim, killer);
			if ((drops == null) || drops.isEmpty())
			{
				continue;
			}
			
			if (calculatedDrops == null)
			{
				calculatedDrops = new ArrayList<>(drops.size());
			}
			
			calculatedDrops.addAll(drops);
		}
		
		return calculatedDrops;
	}
	
	public double getCollisionRadiusGrown()
	{
		return _collisionRadiusGrown;
	}
	
	public double getCollisionHeightGrown()
	{
		return _collisionHeightGrown;
	}
	
	public static boolean isAssignableTo(Class<?> sub, Class<?> clazz)
	{
		// If clazz represents an interface
		if (clazz.isInterface())
		{
			// check if obj implements the clazz interface
			Class<?>[] interfaces = sub.getInterfaces();
			for (Class<?> interface1 : interfaces)
			{
				if (clazz.getName().equals(interface1.getName()))
				{
					return true;
				}
			}
		}
		else
		{
			do
			{
				if (sub.getName().equals(clazz.getName()))
				{
					return true;
				}
				
				sub = sub.getSuperclass();
			}
			while (sub != null);
		}
		return false;
	}
	
	/**
	 * Checks if obj can be assigned to the Class represented by clazz.<br>
	 * This is true if, and only if, obj is the same class represented by clazz, or a subclass of it or obj implements the interface represented by clazz.
	 * @param obj
	 * @param clazz
	 * @return {@code true} if the object can be assigned to the class, {@code false} otherwise
	 */
	public static boolean isAssignableTo(Object obj, Class<?> clazz)
	{
		return L2NpcTemplate.isAssignableTo(obj.getClass(), clazz);
	}
	
	//[JOJO]-------------------------------------------------
	private static final <T> List <T> emptyArrayList()
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		return Collections.emptyList();	// return Collections.EMPTY_LIST
}} else {{
		return new ArrayList<>();
}}
	}
	//-------------------------------------------------------
	
	public void addQuestEvent(QuestEventType eventType, Quest q)
	{
		List<Quest> quests;
		if ((quests = _questEvents.get(eventType)) == null)
		{
			quests = new ArrayList<>();
			_questEvents.put(eventType, quests);
		}
		
		if (!eventType.isMultipleRegistrationAllowed() && !quests.isEmpty())
		{
			_log.warning("Quest event not allowed in multiple quests.  Skipped addition of Event Type \"" + eventType + "\" for NPC \"" + _name + "\" and quest \"" + q.getName() + "\".");
		}
		else
		{
			quests.add(q);
		}
if (com.l2jserver.Config.FIX_OnKillNotifyTask_THREAD) {{
		// L2AttackableAIScript#onXXX は最後に呼ばれるよう順番を変える.
		for (int i = quests.size() - 1; --i >= 0; )
			if (quests.get(i).getName().equals("L2AttackableAIScript"))
				quests.add(quests.remove(i));
}}
if (com.l2jserver.Config.FIX_onSpawn_for_SpawnTable) {{
		if (eventType == QuestEventType.ON_SPAWN && !q.getName().equals("L2AttackableAIScript")) {
			boolean hasOnSpawn;
			try {
				q.getClass().getDeclaredMethod("onSpawn", L2Npc.class);	// @Override public String onSpawn(L2Npc npc)
				hasOnSpawn = true;
			}
			catch (NoSuchMethodException | SecurityException e) {
				hasOnSpawn = false;
			}
			if (hasOnSpawn) {
				boolean done = false;
				for (L2Spawn spawn : SpawnTable.getInstance().getSpawns(_id)) {
					L2Npc npc;
					if ((npc = spawn.getLastSpawn()) != null && npc.isVisible()) {
						done = true;
						q.onSpawn(npc);
					}
				}
				if (!done)
					for (L2Object o :  L2World.getInstance().getVisibleObjects())
						if (o.isNpc() && o.getId() == _id)
							q.onSpawn((L2Npc) o);
			}
		}
}}
	}
	
	public void removeQuest(Quest q)
	{
		for (Entry<QuestEventType, List<Quest>> entry : _questEvents.entrySet())
		{
			if (entry.getValue().contains(q))
			{
				Iterator<Quest> it = entry.getValue().iterator();
				while (it.hasNext())
				{
					Quest q1 = it.next();
					if (q1 == q)
					{
						it.remove();
					}
				}
				
				if (entry.getValue().isEmpty())
				{
					_questEvents.remove(entry.getKey());
				}
			}
		}
	}
	
	public void addMinionData(L2MinionData minion)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_minions == Collections.EMPTY_LIST) _minions = new ArrayList<>();
}}
		_minions.add(minion);
	}
	
	public boolean canTeach(ClassId classId)
	{
		// If the player is on a third class, fetch the class teacher
		// information for its parent class.
		if (classId.level() == 3)
		{
			return _teachInfo.contains(classId.getParent());
		}
		return _teachInfo.contains(classId);
	}
	
	public Map<QuestEventType, List<Quest>> getEventQuests()
	{
		return _questEvents;
	}
	
	public List<Quest> getEventQuests(QuestEventType EventType)
	{
		return _questEvents.get(EventType);
	}
	
	/**
	 * @return the list of all Minions that must be spawn with the L2NpcInstance using this L2NpcTemplate.
	 */
	public List<L2MinionData> getMinionData()
	{
		return _minions;
	}
	
	public int getNpcId()
	{
		return getId();
	}
	
	public List<ClassId> getTeachInfo()
	{
		return _teachInfo;
	}
	
	public void setTeachInfo(List<ClassId> teachInfo)	// skillLearn.xml
	{
		_teachInfo = teachInfo;
	}
}
