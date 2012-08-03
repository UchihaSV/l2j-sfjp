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
package com.l2jserver.gameserver.model.actor.templates;

import static com.l2jserver.gameserver.datatables.StringIntern.intern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javolution.util.FastMap;

import com.l2jserver.gameserver.datatables.HerbDropTable;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.model.L2DropCategory;
import com.l2jserver.gameserver.model.L2DropData;
import com.l2jserver.gameserver.model.L2MinionData;
import com.l2jserver.gameserver.model.L2NpcAIData;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.actor.instance.L2MonsterInstance;
import com.l2jserver.gameserver.model.base.ClassId;
import com.l2jserver.gameserver.model.quest.Quest;
import com.l2jserver.gameserver.model.quest.Quest.QuestEventType;
import com.l2jserver.gameserver.model.skills.L2Skill;

/**
 * @author Zoey76
 */
public final class L2NpcTemplate extends L2CharTemplate
{
	private static final Logger _log = Logger.getLogger(L2NpcTemplate.class.getName());
	
	private final int _npcId;
	private final int _idTemplate;
	private final String _type;
	private final String _name;
	private final boolean _serverSideName;
	private final String _title;
	private final boolean _serverSideTitle;
	private final String _sex;
	private final byte _level;
	private final int _rewardExp;
	private final int _rewardSp;
	private final int _rHand;
	private final int _lHand;
	private final int _enchantEffect;
	
	private Race _race;
	private final String _clientClass;
	
	private final int _dropHerbGroup;
	private final boolean _isCustom;
	/**
	 * Doesn't include all mobs that are involved in quests, just plain quest monsters for preventing champion spawn.
	 */
	private final boolean _isQuestMonster;
	private final float _baseVitalityDivider;
	
	// Skill AI
	private List<L2Skill> _buffSkills;// = new ArrayList<>();
	private List<L2Skill> _negativeSkills;// = new ArrayList<>();
	private List<L2Skill> _debuffSkills;// = new ArrayList<>();
	private List<L2Skill> _atkSkills;// = new ArrayList<>();
	private List<L2Skill> _rootSkills;// = new ArrayList<>();
	private List<L2Skill> _stunSkills;// = new ArrayList<>();
	private List<L2Skill> _sleepSkills;// = new ArrayList<>();
	private List<L2Skill> _paralyzeSkills;// = new ArrayList<>();
	private List<L2Skill> _fossilSkills;// = new ArrayList<>();
	private List<L2Skill> _floatSkills;// = new ArrayList<>();
	private List<L2Skill> _immobilizeSkills;// = new ArrayList<>();
	private List<L2Skill> _healSkills;// = new ArrayList<>();
	private List<L2Skill> _resSkills;// = new ArrayList<>();
	private List<L2Skill> _dotSkills;// = new ArrayList<>();
	private List<L2Skill> _cotSkills;// = new ArrayList<>();
	private List<L2Skill> _universalSkills;// = new ArrayList<>();
	private List<L2Skill> _manaSkills;// = new ArrayList<>();
	private List<L2Skill> _longRangeSkills;// = new ArrayList<>();
	private List<L2Skill> _shortRangeSkills;// = new ArrayList<>();
	private List<L2Skill> _generalSkills;// = new ArrayList<>();
	private List<L2Skill> _suicideSkills;// = new ArrayList<>();
	
	private L2NpcAIData _AIdataStatic = new L2NpcAIData();
	
	/**
	 * The table containing all Item that can be dropped by L2NpcInstance using this L2NpcTemplate
	 */
	private final List<L2DropCategory> _categories = new ArrayList<>(0);
	
	/**
	 * The table containing all Minions that must be spawn with the L2NpcInstance using this L2NpcTemplate
	 */
	private List<L2MinionData> _minions;// = new ArrayList<>();
	
	private List<ClassId> _teachInfo;// = new ArrayList<>();
	
	private final Map<Integer, L2Skill> _skills = new FastMap<Integer, L2Skill>().shared();
	
	/**
	 * Contains a list of quests for each event type (questStart, questAttack, questKill, etc).
	 */
	private final Map<QuestEventType, List<Quest>> _questEvents = new FastMap<QuestEventType, List<Quest>>().shared();
	
	public static enum AIType
	{
		FIGHTER,
		ARCHER,
		BALANCED,
		MAGE,
		HEALER,
		CORPSE
	}
	
	public static enum Race
	{
		UNDEAD,
		MAGICCREATURE,
		BEAST,
		ANIMAL,
		PLANT,
		HUMANOID,
		SPIRIT,
		ANGEL,
		DEMON,
		DRAGON,
		GIANT,
		BUG,
		FAIRIE,
		HUMAN,
		ELVE,
		DARKELVE,
		ORC,
		DWARVE,
		OTHER,
		NONLIVING,
		SIEGEWEAPON,
		DEFENDINGARMY,
		MERCENARIE,
		UNKNOWN,
		KAMAEL,
		NONE
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
	
	/**
	 * Constructor of L2Character.
	 * @param set The StatsSet object to transfer data to the method
	 */
	public L2NpcTemplate(StatsSet set)
	{
		super(set);
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		_buffSkills = Collections.emptyList();
		_negativeSkills = Collections.emptyList();
		_debuffSkills = Collections.emptyList();
		_atkSkills = Collections.emptyList();
		_rootSkills = Collections.emptyList();
		_stunSkills = Collections.emptyList();
		_sleepSkills = Collections.emptyList();
		_paralyzeSkills = Collections.emptyList();
		_fossilSkills = Collections.emptyList();
		_floatSkills = Collections.emptyList();
		_immobilizeSkills = Collections.emptyList();
		_healSkills = Collections.emptyList();
		_resSkills = Collections.emptyList();
		_dotSkills = Collections.emptyList();
		_cotSkills = Collections.emptyList();
		_universalSkills = Collections.emptyList();
		_manaSkills = Collections.emptyList();
		_longRangeSkills = Collections.emptyList();
		_shortRangeSkills = Collections.emptyList();
		_generalSkills = Collections.emptyList();
		_suicideSkills = Collections.emptyList();
		
		//_categories = new Collections.emptyList();
		_minions = Collections.emptyList();
		_teachInfo = Collections.emptyList();
		//_skills = Collections.emptyMap();
}} else {{
		_buffSkills = new ArrayList<>(0);
		_negativeSkills = new ArrayList<>(0);
		_debuffSkills = new ArrayList<>(0);
		_atkSkills = new ArrayList<>(0);
		_rootSkills = new ArrayList<>(0);
		_stunSkills = new ArrayList<>();
		_sleepSkills = new ArrayList<>(0);
		_paralyzeSkills = new ArrayList<>(0);
		_fossilSkills = new ArrayList<>();
		_floatSkills = new ArrayList<>();
		_immobilizeSkills = new ArrayList<>(0);
		_healSkills = new ArrayList<>(0);
		_resSkills = new ArrayList<>();
		_dotSkills = new ArrayList<>(0);
		_cotSkills = new ArrayList<>(0);
		_universalSkills = new ArrayList<>(0);
		_manaSkills = new ArrayList<>();
		_longRangeSkills = new ArrayList<>(0);
		_shortRangeSkills = new ArrayList<>(0);
		_generalSkills = new ArrayList<>(0);
		_suicideSkills = new ArrayList<>(0);
		
		//_categories = new ArrayList<>(0);
		_minions = new ArrayList<>(0);
		_teachInfo = new ArrayList<>(0);
		//_skills = new FastMap<Integer, L2Skill>().shared();
}}
		_npcId = set.getInteger("npcId");
		_idTemplate = set.getInteger("idTemplate");
		_type = intern(set.getString("type"));
		_name = intern(set.getString("name"));
		_serverSideName = set.getBool("serverSideName");
		_title = intern(set.getString("title"));
		_isQuestMonster = getTitle().equalsIgnoreCase("Quest Monster") || getTitle().equals("クエストモンスター");	// [L2J_JP EDIT - TSL][JOJO]
		_serverSideTitle = set.getBool("serverSideTitle");
		_sex = intern(set.getString("sex"));
		_level = set.getByte("level");
		_rewardExp = set.getInteger("rewardExp");
		_rewardSp = set.getInteger("rewardSp");
		_rHand = set.getInteger("rhand");
		_lHand = set.getInteger("lhand");
		_enchantEffect = set.getInteger("enchant");
		_race = null;
		final int herbGroup = set.getInteger("dropHerbGroup");
		if ((herbGroup > 0) && (HerbDropTable.getInstance().getHerbDroplist(herbGroup) == null))
		{
			_log.warning("Missing Herb Drop Group for npcId: " + getNpcId());
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
	
	private void addAtkSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_atkSkills == Collections.EMPTY_LIST) _atkSkills = new ArrayList<>();
}}
		_atkSkills.add(skill);
	}
	
	private void addBuffSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_buffSkills == Collections.EMPTY_LIST) _buffSkills = new ArrayList<>();
}}
		_buffSkills.add(skill);
	}
	
	private void addCOTSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_cotSkills == Collections.EMPTY_LIST) _cotSkills = new ArrayList<>();
}}
		_cotSkills.add(skill);
	}
	
	private void addDebuffSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_debuffSkills == Collections.EMPTY_LIST) _debuffSkills = new ArrayList<>();
}}
		_debuffSkills.add(skill);
	}
	
	private void addDOTSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_dotSkills == Collections.EMPTY_LIST) _dotSkills = new ArrayList<>();
}}
		_dotSkills.add(skill);
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
			synchronized (_categories)
			{
				boolean catExists = false;
				for (L2DropCategory cat : _categories)
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
					_categories.add(cat);
				}
			}
		}
	}
	
	@SuppressWarnings("unused") private void addFloatSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_floatSkills == Collections.EMPTY_LIST) _floatSkills = new ArrayList<>();
}}
		_floatSkills.add(skill);
	}
	
	@SuppressWarnings("unused") private void addFossilSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_fossilSkills == Collections.EMPTY_LIST) _fossilSkills = new ArrayList<>();
}}
		_fossilSkills.add(skill);
	}
	
	private void addGeneralSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_generalSkills == Collections.EMPTY_LIST) _generalSkills = new ArrayList<>();
		_generalSkills.add(skill);
}} else {{
		getGeneralskills().add(skill);
}}
	}
	
	private void addHealSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_healSkills == Collections.EMPTY_LIST) _healSkills = new ArrayList<>();
}}
		_healSkills.add(skill);
	}
	
	private void addImmobiliseSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_immobilizeSkills == Collections.EMPTY_LIST) _immobilizeSkills = new ArrayList<>();
}}
		_immobilizeSkills.add(skill);
	}
	
	@SuppressWarnings("unused") private void addManaHealSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_manaSkills == Collections.EMPTY_LIST) _manaSkills = new ArrayList<>();
}}
		_manaSkills.add(skill);
	}
	
	private void addNegativeSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_negativeSkills == Collections.EMPTY_LIST) _negativeSkills = new ArrayList<>();
}}
		_negativeSkills.add(skill);
	}
	
	private void addParalyzeSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_paralyzeSkills == Collections.EMPTY_LIST) _paralyzeSkills = new ArrayList<>();
}}
		_paralyzeSkills.add(skill);
	}
	
	public void addQuestEvent(QuestEventType EventType, Quest q)
	{
		List<Quest> quests = _questEvents.get(EventType);
		if (quests == null)
		{
			quests = new ArrayList<>();
			quests.add(q);
			_questEvents.put(EventType, quests);
		}
		else
		{
			if (!EventType.isMultipleRegistrationAllowed() && !quests.isEmpty())
			{
				_log.warning("Quest event not allowed in multiple quests.  Skipped addition of Event Type \"" + EventType + "\" for NPC \"" + _name + "\" and quest \"" + q.getName() + "\".");
			}
			else
			{
				quests.add(q);
			}
		}
if (com.l2jserver.Config.FIX_L2AttackableAIScript_super) {{
		// L2AttackableAIScript#onXXX は最後に呼ばれるよう順番を変える.
		for (int i = quests.size() - 1; --i >= 0; )
			if (quests.get(i).getName().equals("L2AttackableAIScript"))
				quests.add(quests.remove(i));
}}
if (com.l2jserver.Config.FIX_onSpawn_for_SpawnTable) {{
		if (EventType == QuestEventType.ON_SPAWN && !q.getName().equals("L2AttackableAIScript")) {
			boolean hasOnSpawn;
			try {
				q.getClass().getDeclaredMethod("onSpawn", L2Npc.class);	// @Override public String onSpawn(L2Npc npc)
				hasOnSpawn = true;
			}
			catch (NoSuchMethodException | SecurityException e) {
				hasOnSpawn = false;
			}
			if (hasOnSpawn)
				for (L2Spawn spawn : SpawnTable.getInstance().getSpawnTable()) {
					L2Npc npc;
					if (spawn != null && (npc = spawn.getLastSpawn()) != null) {
						if (npc.getNpcId() == _npcId && npc.isVisible()) {
						//	System.out.println("__BASENAME__:__LINE__: " + q.getClass().getSimpleName() + ".onSpawn(" + npc.getNpcId() + npc.getName() + ")");
							q.onSpawn(npc);
						}
						L2MonsterInstance leader;
						if (npc instanceof L2MonsterInstance && (leader = (L2MonsterInstance)npc).hasMinions()) {
							for (L2MonsterInstance minion : leader.getMinionList().getSpawnedMinions())
								if (minion.getNpcId() == _npcId && minion.isVisible()) {
								//	System.out.println("__BASENAME__:__LINE__: " + q.getClass().getSimpleName() + ".onSpawn(+" + minion.getNpcId() + minion.getName() + ")");
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
						it.remove();
				}
				
				if (entry.getValue().isEmpty())
				{
					_questEvents.remove(entry.getKey());
				}
			}
		}
	}
	
	public void addRaidData(L2MinionData minion)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_minions == Collections.EMPTY_LIST) _minions = new ArrayList<>();
}}
		_minions.add(minion);
	}
	
	private void addRangeSkill(L2Skill skill)
	{
		if ((skill.getCastRange() <= 150) && (skill.getCastRange() > 0))
		{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
			if (_shortRangeSkills == Collections.EMPTY_LIST) _shortRangeSkills = new ArrayList<>();
}}
			_shortRangeSkills.add(skill);
		}
		else if (skill.getCastRange() > 150)
		{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
			if (_longRangeSkills == Collections.EMPTY_LIST) _longRangeSkills = new ArrayList<>();
}}
			_longRangeSkills.add(skill);
		}
	}
	
	private void addResSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_resSkills == Collections.EMPTY_LIST) _resSkills = new ArrayList<>();
}}
		_resSkills.add(skill);
	}
	
	private void addRootSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_rootSkills == Collections.EMPTY_LIST) _rootSkills = new ArrayList<>();
}}
		_rootSkills.add(skill);
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
				switch (skill.getSkillType())
				{
					case BUFF:
						addBuffSkill(skill);
						break;
					case HEAL:
					case HOT:
					case HEAL_PERCENT:
					case HEAL_STATIC:
					case BALANCE_LIFE:
						addHealSkill(skill);
						break;
					case RESURRECT:
						addResSkill(skill);
						break;
					case DEBUFF:
						addDebuffSkill(skill);
						addCOTSkill(skill);
						addRangeSkill(skill);
						break;
					case ROOT:
						addRootSkill(skill);
						addImmobiliseSkill(skill);
						addRangeSkill(skill);
						break;
					case SLEEP:
						addSleepSkill(skill);
						addImmobiliseSkill(skill);
						break;
					case STUN:
						addRootSkill(skill);
						addImmobiliseSkill(skill);
						addRangeSkill(skill);
						break;
					case PARALYZE:
						addParalyzeSkill(skill);
						addImmobiliseSkill(skill);
						addRangeSkill(skill);
						break;
					case PDAM:
					case MDAM:
					case BLOW:
					case DRAIN:
					case CHARGEDAM:
					case FATAL:
					case DEATHLINK:
					case CPDAM:
					case MANADAM:
					case CPDAMPERCENT:
						addAtkSkill(skill);
						addUniversalSkill(skill);
						addRangeSkill(skill);
						break;
					case POISON:
					case DOT:
					case MDOT:
					case BLEED:
						addDOTSkill(skill);
						addRangeSkill(skill);
						break;
					case MUTE:
					case FEAR:
						addCOTSkill(skill);
						addRangeSkill(skill);
						break;
					case CANCEL:
					case NEGATE:
						addNegativeSkill(skill);
						addRangeSkill(skill);
						break;
					default:
						addUniversalSkill(skill);
						break;
				}
			}
		}
		
		_skills.put(skill.getId(), skill);
	}
	
	private void addSleepSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_sleepSkills == Collections.EMPTY_LIST) _sleepSkills = new ArrayList<>();
}}
		_sleepSkills.add(skill);
	}
	
	@SuppressWarnings("unused") private void addStunSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_stunSkills == Collections.EMPTY_LIST) _stunSkills = new ArrayList<>();
}}
		_stunSkills.add(skill);
	}
	
	private void addSuicideSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_suicideSkills == Collections.EMPTY_LIST) _suicideSkills = new ArrayList<>();
}}
		_suicideSkills.add(skill);
	}
	
	public void addTeachInfo(ClassId classId)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_teachInfo == Collections.EMPTY_LIST) _teachInfo = new ArrayList<>();
}}
		_teachInfo.add(classId);
	}
	
	private void addUniversalSkill(L2Skill skill)
	{
if (com.l2jserver.Config.INITIALIZE_EMPTY_COLLECTION) {{
		if (_universalSkills == Collections.EMPTY_LIST) _universalSkills = new ArrayList<>();
}}
		_universalSkills.add(skill);
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
	public synchronized void clearAllDropData()
	{
		for (L2DropCategory cat : _categories)
		{
			cat.clearAllDrops();
		}
		_categories.clear();
	}
	
	public L2NpcAIData getAIDataStatic()
	{
		return _AIdataStatic;
	}
	
	/**
	 * @return the list of all possible item drops of this L2NpcTemplate.<br>
	 *         (ie full drops and part drops, mats, miscellaneous & UNCATEGORIZED)
	 */
	public List<L2DropData> getAllDropData()
	{
		final List<L2DropData> list = new ArrayList<>();
		for (L2DropCategory tmp : _categories)
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
		return _atkSkills;
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
		return _buffSkills;
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
		return _cotSkills;
	}
	
	/**
	 * @return the debuff skills.
	 */
	public List<L2Skill> getDebuffSkills()
	{
		return _debuffSkills;
	}
	
	/**
	 * @return the list of all possible UNCATEGORIZED drops of this L2NpcTemplate.
	 */
	public List<L2DropCategory> getDropData()
	{
		return _categories;
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
		return _generalSkills;
	}
	
	/**
	 * @return the heal skills.
	 */
	public List<L2Skill> getHealSkills()
	{
		return _healSkills;
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
		return _immobilizeSkills;
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
		return _longRangeSkills;
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
		return _negativeSkills;
	}
	
	/**
	 * @return the npc Id.
	 */
	public int getNpcId()
	{
		return _npcId;
	}
	
	/**
	 * @return the NPC race.
	 */
	public Race getRace()
	{
		if (_race == null)
		{
			_race = Race.NONE;
		}
		return _race;
	}
	
	/**
	 * @return the resurrection skills.
	 */
	public List<L2Skill> getResSkills()
	{
		return _resSkills;
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
		return _shortRangeSkills;
	}
	
	@Override
	public Map<Integer, L2Skill> getSkills()
	{
		return _skills;
	}
	
	public List<L2Skill> getSuicideSkills()
	{
		return _suicideSkills;
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
		return _universalSkills;
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
	
 //	/**
 //	 * @return {@code true} if the NPC is Christmas Special Tree, {@code false} otherwise.
 //	 */
 //	public boolean isSpecialTree()
 //	{
 //		return _npcId == L2XmassTreeInstance.SPECIAL_TREE_ID;
 //	}
	
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
		return _race == Race.UNDEAD;
	}
	
	public void setAIData(L2NpcAIData AIData)
	{
		_AIdataStatic = AIData;
	}
	
	public void setRace(int raceId)
	{
		switch (raceId)
		{
			case 1:
				_race = Race.UNDEAD;
				break;
			case 2:
				_race = Race.MAGICCREATURE;
				break;
			case 3:
				_race = Race.BEAST;
				break;
			case 4:
				_race = Race.ANIMAL;
				break;
			case 5:
				_race = Race.PLANT;
				break;
			case 6:
				_race = Race.HUMANOID;
				break;
			case 7:
				_race = Race.SPIRIT;
				break;
			case 8:
				_race = Race.ANGEL;
				break;
			case 9:
				_race = Race.DEMON;
				break;
			case 10:
				_race = Race.DRAGON;
				break;
			case 11:
				_race = Race.GIANT;
				break;
			case 12:
				_race = Race.BUG;
				break;
			case 13:
				_race = Race.FAIRIE;
				break;
			case 14:
				_race = Race.HUMAN;
				break;
			case 15:
				_race = Race.ELVE;
				break;
			case 16:
				_race = Race.DARKELVE;
				break;
			case 17:
				_race = Race.ORC;
				break;
			case 18:
				_race = Race.DWARVE;
				break;
			case 19:
				_race = Race.OTHER;
				break;
			case 20:
				_race = Race.NONLIVING;
				break;
			case 21:
				_race = Race.SIEGEWEAPON;
				break;
			case 22:
				_race = Race.DEFENDINGARMY;
				break;
			case 23:
				_race = Race.MERCENARIE;
				break;
			case 24:
				_race = Race.UNKNOWN;
				break;
			case 25:
				_race = Race.KAMAEL;
				break;
			default:
				_race = Race.NONE;
				break;
		}
	}
}
