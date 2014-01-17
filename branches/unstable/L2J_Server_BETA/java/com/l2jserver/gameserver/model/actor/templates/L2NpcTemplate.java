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
package com.l2jserver.gameserver.model.actor.templates;

import static com.l2jserver.gameserver.datatables.StringIntern.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import com.l2jserver.gameserver.datatables.HerbDropTable;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.enums.AISkillType;
import com.l2jserver.gameserver.enums.NpcRace;
import com.l2jserver.gameserver.enums.QuestEventType;
import com.l2jserver.gameserver.model.L2DropCategory;
import com.l2jserver.gameserver.model.L2DropData;
import com.l2jserver.gameserver.model.L2MinionData;
import com.l2jserver.gameserver.model.L2NpcAIData;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jserver.gameserver.model.base.ClassId;
import com.l2jserver.gameserver.model.effects.L2EffectType;
import com.l2jserver.gameserver.model.interfaces.IIdentifiable;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.skills.L2SkillType;

/**
 * NPC template.
 * @author Zoey76
 */
public final class L2NpcTemplate extends L2CharTemplate implements IIdentifiable
{
	private static final Logger _log = Logger.getLogger(L2NpcTemplate.class.getName());
	
	private int _npcId;
	private int _idTemplate;
	private String _type;
	private String _name;
	private boolean _serverSideName;
	private String _title;
	private boolean _serverSideTitle;
	private String _sex;
	private byte _level;
	private int _rewardExp;
	private int _rewardSp;
	private int _rHand;
	private int _lHand;
	private int _enchantEffect;
	
	private NpcRace _race = NpcRace.NONE;
	private String _clientClass;
	
	private int _dropHerbGroup;
	private boolean _isCustom;
	private boolean _isQuestMonster;
	
	private float _baseVitalityDivider;
	
	private final ConcurrentHashMap<AISkillType, List<L2Skill>> _aiSkills = new ConcurrentHashMap<>();
	
	private final ConcurrentHashMap<Integer, L2Skill> _skills = new ConcurrentHashMap<>();
	
	private final List<L2DropCategory> _dropCategories = new CopyOnWriteArrayList<>();
	
	private List<L2MinionData> _minions = emptyArrayList();
	
	private List<ClassId> _teachInfo = emptyArrayList();
	
	private final ConcurrentHashMap<QuestEventType, List<Quest>> _questEvents = new ConcurrentHashMap<>();
	
	private L2NpcAIData _aiData;
	
	private StatsSet _parameters;
	
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
		_npcId = set.getInt("npcId");
		_idTemplate = set.getInt("idTemplate");
		_type = intern(set.getString("type"));
		_name = intern(set.getString("name"));
		_serverSideName = set.getBoolean("serverSideName");
		_title = intern(set.getString("title"));
		_isQuestMonster = getTitle().equalsIgnoreCase("Quest Monster") || getTitle().equals("クエストモンスター");	// [L2J_JP EDIT - TSL][JOJO]
		_serverSideTitle = set.getBoolean("serverSideTitle");
		_sex = intern(set.getString("sex"));
		_level = set.getByte("level");
		_rewardExp = set.getInt("rewardExp");
		_rewardSp = set.getInt("rewardSp");
		_rHand = set.getInt("rhand");
		_lHand = set.getInt("lhand");
		_enchantEffect = set.getInt("enchant");
		final int herbGroup = set.getInt("dropHerbGroup");
		if ((herbGroup > 0) && (HerbDropTable.getInstance().getHerbDroplist(herbGroup) == null))
		{
			_log.warning("Missing Herb Drop Group for npcId: " + getId());
			_dropHerbGroup = 0;
		}
		else
		{
			_dropHerbGroup = herbGroup;
		}
		
		_clientClass = intern(set.getString("client_class"));
		
		// TODO: Could be loaded from db.
		_baseVitalityDivider = (getLevel() > 0) && (getRewardExp() > 0) ? (getBaseHpMax() * 9 * getLevel() * getLevel()) / (100 * getRewardExp()) : 0;
		
		_isCustom = _npcId != _idTemplate;
	}
	
	public void resetSkills()
	{
	//[JOJO]-------------------------------------------------
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		final List<L2Skill> EMPTY_LIST = Collections.<L2Skill> emptyList();
		for (AISkillType type : AISkillType.values())
		{
			_aiSkills.put(type, EMPTY_LIST);
		}
}} else {{
		for (AISkillType type : AISkillType.values())
		{
			_aiSkills.put(type, new ArrayList<L2Skill>(0));
		}
}}
	//-------------------------------------------------------
		_skills.clear();
	}
	
	public void resetDroplist()
	{
		_dropCategories.clear();
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
		return new ArrayList<>(0);
}}
	}
	private void addAISkill(AISkillType aiSkillType, L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		List<L2Skill> skills;
		if ((skills = _aiSkills.get(aiSkillType)) == Collections.EMPTY_LIST)
			_aiSkills.put(aiSkillType, skills = new ArrayList<>());
		skills.add(skill);
}}
	}
	//-------------------------------------------------------
	
	private void addAtkSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.ATTACK, skill);
}} else {{
		getAtkSkills().add(skill);
}}
	}
	
	private void addBuffSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.BUFF, skill);
}} else {{
		getBuffSkills().add(skill);
}}
	}
	
	private void addCOTSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.COT, skill);
}} else {{
		getCostOverTimeSkills().add(skill);
}}
	}
	
	private void addDebuffSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.DEBUFF, skill);
}} else {{
		getDebuffSkills().add(skill);
}}
	}
	
	/**
	 * Add a drop to a given category.<br>
	 * If the category does not exist, create it.
	 * @param drop
	 * @param categoryType
	 */
	public void addDropData(L2DropData drop, int categoryType)
	{
		if (!drop.isQuestDrop())
		{
			// If the category doesn't already exist, create it first
			boolean catExists = false;
			for (L2DropCategory cat : _dropCategories)
			{
				// If the category exists, add the drop to this category.
				if (cat.getCategoryType() == categoryType)
				{
					cat.addDropData(drop, isType("L2RaidBoss") || isType("L2GrandBoss"));
					catExists = true;
					break;
				}
			}
			// If the category doesn't exit, create it and add the drop
			if (!catExists)
			{
				final L2DropCategory cat = new L2DropCategory(categoryType);
				cat.addDropData(drop, isType("L2RaidBoss") || isType("L2GrandBoss"));
				_dropCategories.add(cat);
			}
		}
	}
	
	private void addGeneralSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.GENERAL, skill);
}} else {{
		getGeneralskills().add(skill);
}}
	}
	
	private void addHealSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.HEAL, skill);
}} else {{
		getHealSkills().add(skill);
}}
	}
	
	private void addImmobiliseSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.IMMOBILIZE, skill);
}} else {{
		getImmobiliseSkills().add(skill);
}}
	}
	
	private void addNegativeSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.NEGATIVE, skill);
}} else {{
		getNegativeSkills().add(skill);
}}
	}
	
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
			if (hasOnSpawn)
			  for (Set<L2Spawn> t : SpawnTable.getInstance().getSpawnTable().values())
				for (L2Spawn spawn : t) {
					L2Npc npc;
					if (spawn != null && (npc = spawn.getLastSpawn()) != null) {
						if (npc.getId() == _npcId && npc.isVisible()) {
							q.onSpawn(npc);
						}
						L2MonsterInstance leader;
						if (npc.isMonster() && (leader = (L2MonsterInstance)npc).hasMinions()) {
							for (L2MonsterInstance minion : leader.getMinionList().getSpawnedMinions())
								if (minion.getId() == _npcId && minion.isVisible()) {
									q.onSpawn(minion);
								}
						}
					}
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
	
	private void addRangeSkill(L2Skill skill)
	{
		if (skill.getCastRange() > 150)
		{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
			addAISkill(AISkillType.LONG_RANGE, skill);
}} else {{
			getLongRangeSkills().add(skill);
}}
		}
		else if (skill.getCastRange() > 0)
		{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
			addAISkill(AISkillType.SHORT_RANGE, skill);
}} else {{
			getShortRangeSkills().add(skill);
}}
		}
	}
	
	private void addResSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.RES, skill);
}} else {{
		getResSkills().add(skill);
}}
	}
	
	public void addSkill(L2Skill skill)
	{
		if (!skill.isPassive())
		{
			if (skill.isSuicideAttack())
			{
				addSuicideSkill(skill);
			}
			else
			{
				addGeneralSkill(skill);
				
				if (skill.isContinuous())
				{
					if (!skill.isDebuff())
					{
						addBuffSkill(skill);
					}
					else
					{
						addDebuffSkill(skill);
						addCOTSkill(skill);
						addRangeSkill(skill);
					}
				}
				else if (skill.getSkillType() == L2SkillType.DUMMY)
				{
					if (skill.hasEffectType(L2EffectType.DISPEL))
					{
						addNegativeSkill(skill);
						addRangeSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.HEAL, L2EffectType.HEAL_PERCENT))
					{
						addHealSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.PHYSICAL_ATTACK, L2EffectType.PHYSICAL_ATTACK_HP_LINK, L2EffectType.FATAL_BLOW, L2EffectType.ENERGY_ATTACK, L2EffectType.MAGICAL_ATTACK_MP, L2EffectType.MAGICAL_ATTACK, L2EffectType.DEATH_LINK, L2EffectType.HP_DRAIN))
					{
						addAtkSkill(skill);
						addUniversalSkill(skill);
						addRangeSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.SLEEP))
					{
						addImmobiliseSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.STUN, L2EffectType.ROOT))
					{
						addImmobiliseSkill(skill);
						addRangeSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.MUTE, L2EffectType.FEAR))
					{
						addCOTSkill(skill);
						addRangeSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.PARALYZE))
					{
						addImmobiliseSkill(skill);
						addRangeSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.DMG_OVER_TIME, L2EffectType.DMG_OVER_TIME_PERCENT))
					{
						addRangeSkill(skill);
					}
					else if (skill.hasEffectType(L2EffectType.RESURRECTION))
					{
						addResSkill(skill);
					}
					else
					{
						addUniversalSkill(skill);
					}
				}
			}
		}
		
		_skills.put(skill.getId(), skill);
	}
	
	private void addSuicideSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.SUICIDE, skill);
}} else {{
		getSuicideSkills().add(skill);
}}
	}
	
	public void setTeachInfo(List<ClassId> teachInfo)	// skillLearn.xml
	{
		_teachInfo = teachInfo;
	}
	
	private void addUniversalSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		addAISkill(AISkillType.UNIVERSAL, skill);
}} else {{
		getUniversalSkills().add(skill);
}}
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
	
	/**
	 * Empty all possible drops of this L2NpcTemplate.
	 */
	public void clearAllDropData()
	{
		for (L2DropCategory cat : _dropCategories)
		{
			cat.clearAllDrops();
		}
		_dropCategories.clear();
	}
	
	public L2NpcAIData getAIDataStatic()
	{
		if (_aiData == null)
		{
			_aiData = new L2NpcAIData();
		}
		return _aiData;
	}
	
	/**
	 * @return the list of all possible item drops of this L2NpcTemplate.<br>
	 *         (ie full drops and part drops, mats, miscellaneous & UNCATEGORIZED)
	 */
	public List<L2DropData> getAllDropData()
	{
		final List<L2DropData> list = new ArrayList<>();
		for (L2DropCategory tmp : _dropCategories)
		{
			list.addAll(tmp.getAllDrops());
		}
		return list;
	}
	
	/**
	 * @return the attack skills.
	 */
	public List<L2Skill> getAtkSkills()
	{
		return _aiSkills.get(AISkillType.ATTACK);
	}
	
	/**
	 * @return the base vitality divider value.
	 */
	public float getBaseVitalityDivider()
	{
		return _baseVitalityDivider;
	}
	
	/**
	 * @return the buff skills.
	 */
	public List<L2Skill> getBuffSkills()
	{
		return _aiSkills.get(AISkillType.BUFF);
	}
	
	/**
	 * @return the client class (same as texture path).
	 */
	public String getClientClass()
	{
		return _clientClass;
	}
	
	/**
	 * @return the cost over time skills.
	 */
	public List<L2Skill> getCostOverTimeSkills()
	{
		return _aiSkills.get(AISkillType.COT);
	}
	
	/**
	 * @return the debuff skills.
	 */
	public List<L2Skill> getDebuffSkills()
	{
		return _aiSkills.get(AISkillType.DEBUFF);
	}
	
	/**
	 * @return the list of all possible UNCATEGORIZED drops of this L2NpcTemplate.
	 */
	public List<L2DropCategory> getDropData()
	{
		return _dropCategories;
	}
	
	/**
	 * @return the drop herb group.
	 */
	public int getDropHerbGroup()
	{
		return _dropHerbGroup;
	}
	
	/**
	 * @return the enchant effect.
	 */
	public int getEnchantEffect()
	{
		return _enchantEffect;
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
	 * @return the general skills.
	 */
	public List<L2Skill> getGeneralskills()
	{
		return _aiSkills.get(AISkillType.GENERAL);
	}
	
	/**
	 * @return the heal skills.
	 */
	public List<L2Skill> getHealSkills()
	{
		return _aiSkills.get(AISkillType.HEAL);
	}
	
	/**
	 * @return the Id template.
	 */
	public int getIdTemplate()
	{
		return _idTemplate;
	}
	
	/**
	 * @return the immobilize skills.
	 */
	public List<L2Skill> getImmobiliseSkills()
	{
		return _aiSkills.get(AISkillType.IMMOBILIZE);
	}
	
	/**
	 * @return the left hand item.
	 */
	public int getLeftHand()
	{
		return _lHand;
	}
	
	/**
	 * @return the NPC level.
	 */
	public byte getLevel()
	{
		return _level;
	}
	
	/**
	 * @return the long range skills.
	 */
	public List<L2Skill> getLongRangeSkills()
	{
		return _aiSkills.get(AISkillType.LONG_RANGE);
	}
	
	/**
	 * @return the list of all Minions that must be spawn with the L2NpcInstance using this L2NpcTemplate.
	 */
	public List<L2MinionData> getMinionData()
	{
		return _minions;
	}
	
	/**
	 * @return the NPC name.
	 */
	public String getName()
	{
		return _name;
	}
	
	/**
	 * @return the negative skills.
	 */
	public List<L2Skill> getNegativeSkills()
	{
		return _aiSkills.get(AISkillType.NEGATIVE);
	}
	
	/**
	 * Gets the NPC ID.
	 * @return the NPC ID
	 */
	@Override
	public int getId()
	{
		return _npcId;
	}
	
	public int getNpcId()
	{
		return getId();
	}
	
	/**
	 * @return the NPC race.
	 */
	public NpcRace getRace()
	{
		return _race;
	}
	
	/**
	 * @return the resurrection skills.
	 */
	public List<L2Skill> getResSkills()
	{
		return _aiSkills.get(AISkillType.RES);
	}
	
	/**
	 * @return the reward Exp.
	 */
	public int getRewardExp()
	{
		return _rewardExp;
	}
	
	/**
	 * @return the reward SP.
	 */
	public int getRewardSp()
	{
		return _rewardSp;
	}
	
	/**
	 * @return the right hand weapon.
	 */
	public int getRightHand()
	{
		return _rHand;
	}
	
	/**
	 * @return the NPC sex.
	 */
	public String getSex()
	{
		return _sex;
	}
	
	/**
	 * @return the short range skills.
	 */
	public List<L2Skill> getShortRangeSkills()
	{
		return _aiSkills.get(AISkillType.SHORT_RANGE);
	}
	
	@Override
	public Map<Integer, L2Skill> getSkills()
	{
		return _skills;
	}
	
	public List<L2Skill> getSuicideSkills()
	{
		return _aiSkills.get(AISkillType.SUICIDE);
	}
	
	public List<ClassId> getTeachInfo()
	{
		return _teachInfo;
	}
	
	/**
	 * @return the NPC title.
	 */
	public String getTitle()
	{
		return _title;
	}
	
	/**
	 * @return the NPC type.
	 */
	public String getType()
	{
		return _type;
	}
	
	/**
	 * @return the universal skills.
	 */
	public List<L2Skill> getUniversalSkills()
	{
		return _aiSkills.get(AISkillType.UNIVERSAL);
	}
	
	/**
	 * @return {@code true} if the NPC is custom, {@code false} otherwise.
	 */
	public boolean isCustom()
	{
		return _isCustom;
	}
	
	/**
	 * @return {@code true} if the NPC is a quest monster, {@code false} otherwise.
	 */
	public boolean isQuestMonster()
	{
		return _isQuestMonster;
	}
	
	/**
	 * @return {@code true} if the NPC uses server side name, {@code false} otherwise.
	 */
	public boolean isServerSideName()
	{
		return _serverSideName;
	}
	
	/**
	 * @return {@code true} if the NPC uses server side title, {@code false} otherwise.
	 */
	public boolean isServerSideTitle()
	{
		return _serverSideTitle;
	}
	
	/**
	 * Checks types, ignore case.
	 * @param t the type to check.
	 * @return {@code true} if the type are the same, {@code false} otherwise.
	 */
	public boolean isType(String t)
	{
		return _type.equalsIgnoreCase(t);
	}
	
	/**
	 * @return {@code true} if the NPC is an undead, {@code false} otherwise.
	 */
	public boolean isUndead()
	{
		return _race == NpcRace.UNDEAD;
	}
	
	public void setAIData(L2NpcAIData aiData)
	{
		_aiData = aiData;
	}
	
	public void setRace(int raceId)
	{
		switch (raceId)
		{
			case 1:
				_race = NpcRace.UNDEAD;
				break;
			case 2:
				_race = NpcRace.MAGICCREATURE;
				break;
			case 3:
				_race = NpcRace.BEAST;
				break;
			case 4:
				_race = NpcRace.ANIMAL;
				break;
			case 5:
				_race = NpcRace.PLANT;
				break;
			case 6:
				_race = NpcRace.HUMANOID;
				break;
			case 7:
				_race = NpcRace.SPIRIT;
				break;
			case 8:
				_race = NpcRace.ANGEL;
				break;
			case 9:
				_race = NpcRace.DEMON;
				break;
			case 10:
				_race = NpcRace.DRAGON;
				break;
			case 11:
				_race = NpcRace.GIANT;
				break;
			case 12:
				_race = NpcRace.BUG;
				break;
			case 13:
				_race = NpcRace.FAIRIE;
				break;
			case 14:
				_race = NpcRace.HUMAN;
				break;
			case 15:
				_race = NpcRace.ELVE;
				break;
			case 16:
				_race = NpcRace.DARKELVE;
				break;
			case 17:
				_race = NpcRace.ORC;
				break;
			case 18:
				_race = NpcRace.DWARVE;
				break;
			case 19:
				_race = NpcRace.OTHER;
				break;
			case 20:
				_race = NpcRace.NONLIVING;
				break;
			case 21:
				_race = NpcRace.SIEGEWEAPON;
				break;
			case 22:
				_race = NpcRace.DEFENDINGARMY;
				break;
			case 23:
				_race = NpcRace.MERCENARIE;
				break;
			case 24:
				_race = NpcRace.UNKNOWN;
				break;
			case 25:
				_race = NpcRace.KAMAEL;
				break;
			default:
				_race = NpcRace.NONE;
				break;
		}
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
}
