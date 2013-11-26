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
package com.l2jserver.gameserver.model.actor.instance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastList;
import javolution.util.FastMap;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.CtrlIntention;
import com.l2jserver.gameserver.datatables.CharSummonTable;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.datatables.PetDataTable;
import com.l2jserver.gameserver.datatables.SkillTable;
import com.l2jserver.gameserver.datatables.SummonEffectsTable;
import com.l2jserver.gameserver.datatables.SummonEffectsTable.SummonEffect;
import com.l2jserver.gameserver.enums.InstanceType;
import com.l2jserver.gameserver.handler.IItemHandler;
import com.l2jserver.gameserver.handler.ItemHandler;
import com.l2jserver.gameserver.idfactory.IdFactory;
import com.l2jserver.gameserver.instancemanager.CursedWeaponsManager;
import com.l2jserver.gameserver.instancemanager.FortSiegeManager;
import com.l2jserver.gameserver.instancemanager.ItemsOnGroundManager;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Party;
import com.l2jserver.gameserver.model.L2PetData;
import com.l2jserver.gameserver.model.L2PetLevelData;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.TimeStamp;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.L2Summon;
import com.l2jserver.gameserver.model.actor.stat.PetStat;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.model.effects.EffectTemplate;
import com.l2jserver.gameserver.model.effects.L2Effect;
import com.l2jserver.gameserver.model.itemcontainer.Inventory;
import com.l2jserver.gameserver.model.itemcontainer.PcInventory;
import com.l2jserver.gameserver.model.itemcontainer.PetInventory;
import com.l2jserver.gameserver.model.items.L2Item;
import com.l2jserver.gameserver.model.items.L2Weapon;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.model.items.type.L2EtcItemType;
import com.l2jserver.gameserver.model.skills.L2Skill;
import com.l2jserver.gameserver.model.stats.Env;
import com.l2jserver.gameserver.model.zone.ZoneId;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.ActionFailed;
import com.l2jserver.gameserver.network.serverpackets.InventoryUpdate;
import com.l2jserver.gameserver.network.serverpackets.PetInventoryUpdate;
import com.l2jserver.gameserver.network.serverpackets.StatusUpdate;
import com.l2jserver.gameserver.network.serverpackets.StopMove;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.gameserver.taskmanager.DecayTaskManager;
import com.l2jserver.util.Rnd;

public class L2PetInstance extends L2Summon
{
	protected static final Logger _logPet = Logger.getLogger(L2PetInstance.class.getName());
	
	private static final String ADD_SKILL_SAVE = "INSERT INTO character_pet_skills_save (petObjItemId,skill_id,skill_level,effect_count,effect_cur_time,buff_index) VALUES (?,?,?,?,?,?)";
	private static final String RESTORE_SKILL_SAVE = "SELECT petObjItemId,skill_id,skill_level,effect_count,effect_cur_time,buff_index FROM character_pet_skills_save WHERE petObjItemId=? ORDER BY buff_index ASC";
	private static final String DELETE_SKILL_SAVE = "DELETE FROM character_pet_skills_save WHERE petObjItemId=?";
	
	private final Map<Integer, TimeStamp> _reuseTimeStampsSkills = new FastMap<>();
	private final Map<Integer, TimeStamp> _reuseTimeStampsItems = new FastMap<>();
	
	private int _curFed;
	private final PetInventory _inventory;
	private final int _controlObjectId;
	private boolean _respawned;
	private final boolean _mountable;
	private Future<?> _feedTask;
	private L2PetData _data;
	private L2PetLevelData _leveldata;
	
	/** The Experience before the last Death Penalty */
	private long _expBeforeDeath = 0;
	private int _curWeightPenalty = 0;
	
	private static final int PET_DECAY_DELAY = 86400000; // 24 hours
	
	public final L2PetLevelData getPetLevelData()
	{
		if (_leveldata == null)
		{
			_leveldata = PetDataTable.getInstance().getPetLevelData(getTemplate().getId(), getStat().getLevel());
		}
		
		return _leveldata;
	}
	
	public final L2PetData getPetData()
	{
		if (_data == null)
		{
			_data = PetDataTable.getInstance().getPetData(getTemplate().getId());
		}
		
		return _data;
	}
	
	public final void setPetData(L2PetLevelData value)
	{
		_leveldata = value;
	}
	
	/**
	 * Manage Feeding Task.<BR>
	 * Feed or kill the pet depending on hunger level.<br>
	 * If pet has food in inventory and feed level drops below 55% then consume food from inventory.<br>
	 * Send a broadcastStatusUpdate packet for this L2PetInstance
	 */
	class FeedTask implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				if ((getOwner() == null) || !getOwner().hasSummon() || (getOwner().getSummon().getObjectId() != getObjectId()))
				{
					stopFeed();
					return;
				}
				else if (getCurrentFed() > getFeedConsume())
				{
					// eat
					setCurrentFed(getCurrentFed() - getFeedConsume());
				}
				else
				{
					setCurrentFed(0);
				}
				
				broadcastStatusUpdate();
				
				List<Integer> foodIds = getPetData().getFood();
				if (foodIds.isEmpty())
				{
					if (getCurrentFed() == 0)
					{
						// Owl Monk remove PK
						if ((getTemplate().getId() == 16050) && (getOwner() != null))
						{
							getOwner().setPkKills(Math.max(0, getOwner().getPkKills() - Rnd.get(1, 6)));
						}
						sendPacket(SystemMessageId.THE_HELPER_PET_LEAVING);
						deleteMe(getOwner());
					}
					else if (isHungry())
					{
						sendPacket(SystemMessageId.THERE_NOT_MUCH_TIME_REMAINING_UNTIL_HELPER_LEAVES);
					}
					return;
				}
				L2ItemInstance food = null;
				for (int id : foodIds)
				{
					food = getInventory().getItemByItemId(id);
					if (food != null)
					{
						break;
					}
				}
				if (isRunning() && isHungry())
				{
					setWalking();
				}
				else if (!isHungry() && !isRunning())
				{
					setRunning();
				}
				if ((food != null) && isHungry())
				{
					IItemHandler handler = ItemHandler.getInstance().getHandler(food.getEtcItem());
					if (handler != null)
					{
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.PET_TOOK_S1_BECAUSE_HE_WAS_HUNGRY);
						sm.addItemName(food.getId());
						sendPacket(sm);
						handler.useItem(L2PetInstance.this, food, false);
					}
				}
				else
				{
					if (getCurrentFed() == 0)
					{
						SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.YOUR_PET_IS_VERY_HUNGRY);
						sendPacket(sm);
						// High Five: Your Pet won't be gone even when you don't feed it.
						// if (Rnd.get(100) < 30)
						// {
						// stopFeed();
						// sm = SystemMessage.getSystemMessage(SystemMessageId.STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT);
						// sendPacket(sm);
						// _log.info("Hungry pet [" + getTemplate().getName() + "][" + getLevel() + "] deleted for player: " + getOwner() + " Control Item Id :" + getControlObjectId());
						// deleteMe(getOwner());
						// }
					}
					// High Five: Your Pet won't be gone even when you don't feed it.
					// /else if (getCurrentFed() < (0.10 * getPetLevelData().getPetMaxFeed()))
					// {
					// SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.PET_CAN_RUN_AWAY_WHEN_HUNGER_BELOW_10_PERCENT);
					// sendPacket(sm);
					// if (Rnd.get(100) < 3)
					// {
					// stopFeed();
					// sm = SystemMessage.getSystemMessage(SystemMessageId.STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT);
					// sendPacket(sm);
					// _log.info("Hungry pet [" + getTemplate().getName() + "][" + getLevel() + "] deleted for player: " + getOwner() + " Control Item Id :" + getControlObjectId());
					// deleteMe(getOwner());
					// }
					// }
				}
			}
			catch (Exception e)
			{
				_logPet.log(Level.SEVERE, "Pet [ObjectId: " + getObjectId() + "] a feed task error has occurred", e);
			}
		}
		
		/**
		 * @return
		 */
		private int getFeedConsume()
		{
			// if pet is attacking
			if (isAttackingNow())
			{
				return getPetLevelData().getPetFeedBattle();
			}
			return getPetLevelData().getPetFeedNormal();
		}
	}
	
	public synchronized static L2PetInstance spawnPet(L2NpcTemplate template, L2PcInstance owner, L2ItemInstance control)
	{
		if (L2World.getInstance().getPet(owner.getObjectId()) != null)
		{
			return null; // owner has a pet listed in world
		}
		final L2PetData data = PetDataTable.getInstance().getPetData(template.getId());
		
		L2PetInstance pet = restore(control, template, owner);
		// add the pet instance to world
		if (pet != null)
		{
			pet.setTitle(owner.getName());
			if (data.isSynchLevel() && (pet.getLevel() != owner.getLevel()))
			{
				pet.getStat().setLevel((byte) owner.getLevel());
			}
			L2World.getInstance().addPet(owner.getObjectId(), pet);
		}
		
		return pet;
	}
	
	/**
	 * Constructor for new pet
	 * @param objectId
	 * @param template
	 * @param owner
	 * @param control
	 */
	public L2PetInstance(int objectId, L2NpcTemplate template, L2PcInstance owner, L2ItemInstance control)
	{
		this(objectId, template, owner, control, (byte) (template.getIdTemplate() == 12564 ? owner.getLevel() : template.getLevel()));
	}
	
	/**
	 * Constructor for restored pet
	 * @param objectId
	 * @param template
	 * @param owner
	 * @param control
	 * @param level
	 */
	public L2PetInstance(int objectId, L2NpcTemplate template, L2PcInstance owner, L2ItemInstance control, byte level)
	{
		super(objectId, template, owner);
		setInstanceType(InstanceType.L2PetInstance);
		
		_controlObjectId = control.getObjectId();
		
		getStat().setLevel((byte) Math.max(level, PetDataTable.getInstance().getPetMinLevel(template.getId())));
		
		_inventory = new PetInventory(this);
		_inventory.restore();
		
		int npcId = template.getId();
		_mountable = PetDataTable.isMountable(npcId);
		getPetData();
		getPetLevelData();
	}
	
	@Override
	public PetStat getStat()
	{
		return (PetStat) super.getStat();
	}
	
	@Override
	public void initCharStat()
	{
		setStat(new PetStat(this));
	}
	
	public boolean isRespawned()
	{
		return _respawned;
	}
	
	@Override
	public int getSummonType()
	{
		return 2;
	}
	
	@Override
	public int getControlObjectId()
	{
		return _controlObjectId;
	}
	
	public L2ItemInstance getControlItem()
	{
		return getOwner().getInventory().getItemByObjectId(_controlObjectId);
	}
	
	public int getCurrentFed()
	{
		return _curFed;
	}
	
	public void setCurrentFed(int num)
	{
		_curFed = num > getMaxFed() ? getMaxFed() : num;
	}
	
	/**
	 * Returns the pet's currently equipped weapon instance (if any).
	 */
	@Override
	public L2ItemInstance getActiveWeaponInstance()
	{
		for (L2ItemInstance item : getInventory().getItems())
		{
			if ((item.getItemLocation() == L2ItemInstance.ItemLocation.PET_EQUIP) && (item.getItem().getBodyPart() == L2Item.SLOT_R_HAND))
			{
				return item;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the pet's currently equipped weapon (if any).
	 */
	@Override
	public L2Weapon getActiveWeaponItem()
	{
		L2ItemInstance weapon = getActiveWeaponInstance();
		
		if (weapon == null)
		{
			return null;
		}
		
		return (L2Weapon) weapon.getItem();
	}
	
	@Override
	public L2ItemInstance getSecondaryWeaponInstance()
	{
		// temporary? unavailable
		return null;
	}
	
	@Override
	public L2Weapon getSecondaryWeaponItem()
	{
		// temporary? unavailable
		return null;
	}
	
	@Override
	public PetInventory getInventory()
	{
		return _inventory;
	}
	
	/**
	 * Destroys item from inventory and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 * @param process : String Identifier of process triggering this action
	 * @param objectId : int Item Instance identifier of the item to be destroyed
	 * @param count : int Quantity of items to be destroyed
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successfull
	 */
	@Override
	public boolean destroyItem(String process, int objectId, long count, L2Object reference, boolean sendMessage)
	{
		L2ItemInstance item = _inventory.destroyItem(process, objectId, count, getOwner(), reference);
		
		if (item == null)
		{
			if (sendMessage)
			{
				sendPacket(SystemMessageId.NOT_ENOUGH_REQUIRED_ITEMS); // [L2J_JP EDIT] /*You do not have enough required items.|必要アイテムが足りません。*/
			//	sendPacket(SystemMessageId.NOT_ENOUGH_ITEMS); /*Incorrect item count.|購入するアイテムの個数が正しくありません。*/
			}
			
			return false;
		}
		
		// Send Pet inventory update packet
		PetInventoryUpdate petIU = new PetInventoryUpdate();
		petIU.addItem(item);
		sendPacket(petIU);
		
		if (sendMessage)
		{
			if (count > 1)
			{
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_S1_DISAPPEARED);
				sm.addItemName(item.getId());
				sm.addItemNumber(count);
				sendPacket(sm);
			}
			else
			{
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
				sm.addItemName(item.getId());
				sendPacket(sm);
			}
		}
		return true;
	}
	
	/**
	 * Destroy item from inventory by using its <B>itemId</B> and send a Server->Client InventoryUpdate packet to the L2PcInstance.
	 * @param process : String Identifier of process triggering this action
	 * @param itemId : int Item identifier of the item to be destroyed
	 * @param count : int Quantity of items to be destroyed
	 * @param reference : L2Object Object referencing current action like NPC selling item or previous item in transformation
	 * @param sendMessage : boolean Specifies whether to send message to Client about this action
	 * @return boolean informing if the action was successfull
	 */
	@Override
	public boolean destroyItemByItemId(String process, int itemId, long count, L2Object reference, boolean sendMessage)
	{
		L2ItemInstance item = _inventory.destroyItemByItemId(process, itemId, count, getOwner(), reference);
		
		if (item == null)
		{
			if (sendMessage)
			{
				sendPacket(SystemMessageId.NOT_ENOUGH_REQUIRED_ITEMS); // [L2J_JP EDIT] /*You do not have enough required items.|必要アイテムが足りません。*/
			//	sendPacket(SystemMessageId.NOT_ENOUGH_ITEMS); /*Incorrect item count.|購入するアイテムの個数が正しくありません。*/
			}
			return false;
		}
		
		// Send Pet inventory update packet
		PetInventoryUpdate petIU = new PetInventoryUpdate();
		petIU.addItem(item);
		sendPacket(petIU);
		
		if (sendMessage)
		{
			if (count > 1)
			{
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S2_S1_DISAPPEARED);
				sm.addItemName(item.getId());
				sm.addItemNumber(count);
				sendPacket(sm);
			}
			else
			{
				SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
				sm.addItemName(item.getId());
				sendPacket(sm);
			}
		}
		
		return true;
	}
	
	@Override
	protected void doPickupItem(L2Object object)
	{
		if (isDead())
		{
			return;
		}
		
		getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		broadcastPacket(new StopMove(this));
		
		if (!(object instanceof L2ItemInstance))
		{
			// dont try to pickup anything that is not an item :)
			_logPet.warning(this + " trying to pickup wrong target." + object);
			sendPacket(ActionFailed.STATIC_PACKET);
			return;
		}
		
		boolean follow = getFollowStatus();
		final L2ItemInstance target = (L2ItemInstance) object;
		
		// Cursed weapons
		if (CursedWeaponsManager.getInstance().isCursed(target.getId()))
		{
			SystemMessage smsg = SystemMessage.getSystemMessage(SystemMessageId.FAILED_TO_PICKUP_S1);
			smsg.addItemName(target.getId());
			sendPacket(smsg);
			return;
		}
		else if (FortSiegeManager.getInstance().isCombat(target.getId()))
		{
			return;
		}
		
		SystemMessage smsg = null;
		synchronized (target)
		{
			// Check if the target to pick up is visible
			if (!target.isVisible())
			{
				// Send a Server->Client packet ActionFailed to this L2PcInstance
				sendPacket(ActionFailed.STATIC_PACKET);
				return;
			}
			
			if (!target.getDropProtection().tryPickUp(this))
			{
				sendPacket(ActionFailed.STATIC_PACKET);
				smsg = SystemMessage.getSystemMessage(SystemMessageId.FAILED_TO_PICKUP_S1);
				smsg.addItemName(target);
				sendPacket(smsg);
				return;
			}
			
			if (((isInParty() && (getParty().getLootDistribution() == L2Party.ITEM_LOOTER)) || !isInParty()) && !_inventory.validateCapacity(target))
			{
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(SystemMessageId.YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS);
				return;
			}
			
			if ((target.getOwnerId() != 0) && (target.getOwnerId() != getOwner().getObjectId()) && !getOwner().isInLooterParty(target.getOwnerId()))
			{
				if (target.getId() == PcInventory.ADENA_ID)
				{
					smsg = SystemMessage.getSystemMessage(SystemMessageId.FAILED_TO_PICKUP_S1_ADENA);
					smsg.addItemNumber(target.getCount());
				}
				else if (target.getCount() > 1)
				{
					smsg = SystemMessage.getSystemMessage(SystemMessageId.FAILED_TO_PICKUP_S2_S1_S);
					smsg.addItemName(target);
					smsg.addItemNumber(target.getCount());
				}
				else
				{
					smsg = SystemMessage.getSystemMessage(SystemMessageId.FAILED_TO_PICKUP_S1);
					smsg.addItemName(target);
				}
				sendPacket(ActionFailed.STATIC_PACKET);
				sendPacket(smsg);
				return;
			}
			
			if ((target.getItemLootShedule() != null) && ((target.getOwnerId() == getOwner().getObjectId()) || getOwner().isInLooterParty(target.getOwnerId())))
			{
				target.resetOwnerTimer();
			}
			
			// Remove from the ground!
			target.pickupMe(this);
			
			if (Config.SAVE_DROPPED_ITEM)
			{
				ItemsOnGroundManager.getInstance().removeObject(target);
			}
		}
		
		// Herbs
		if (target.getItemType() == L2EtcItemType.HERB)
		{
			IItemHandler handler = ItemHandler.getInstance().getHandler(target.getEtcItem());
			if (handler == null)
			{
				_log.fine("No item handler registered for item ID " + target.getId() + ".");
			}
			else
			{
				handler.useItem(this, target, false);
			}
			
			ItemTable.getInstance().destroyItem("Consume", target, getOwner(), null);
			broadcastStatusUpdate();
		}
		else
		{
			if (target.getId() == PcInventory.ADENA_ID)
			{
				smsg = SystemMessage.getSystemMessage(SystemMessageId.PET_PICKED_S1_ADENA);
				smsg.addItemNumber(target.getCount());
				sendPacket(smsg);
			}
			else if (target.getEnchantLevel() > 0)
			{
				smsg = SystemMessage.getSystemMessage(SystemMessageId.PET_PICKED_S1_S2);
				smsg.addNumber(target.getEnchantLevel());
				smsg.addItemName(target);
				sendPacket(smsg);
			}
			else if (target.getCount() > 1)
			{
				smsg = SystemMessage.getSystemMessage(SystemMessageId.PET_PICKED_S2_S1_S);
				smsg.addItemNumber(target.getCount());
				smsg.addItemName(target);
				sendPacket(smsg);
			}
			else
			{
				smsg = SystemMessage.getSystemMessage(SystemMessageId.PET_PICKED_S1);
				smsg.addItemName(target);
				sendPacket(smsg);
			}
			
			// If owner is in party and it isnt finders keepers, distribute the item instead of stealing it -.-
			if (getOwner().isInParty() && (getOwner().getParty().getLootDistribution() != L2Party.ITEM_LOOTER))
			{
				getOwner().getParty().distributeItem(getOwner(), target);
			}
			else
			{
				final L2ItemInstance item = getInventory().addItem("Pickup", target, getOwner(), this);
				// sendPacket(new PetItemList(getInventory().getItems()));
				sendPacket(new PetInventoryUpdate(item));
			}
		}
		
		getAI().setIntention(CtrlIntention.AI_INTENTION_IDLE);
		
		if (follow)
		{
			followOwner();
		}
	}
	
	@Override
	public void deleteMe(L2PcInstance owner)
	{
		getInventory().transferItemsToOwner();
		super.deleteMe(owner);
		destroyControlItem(owner, false); // this should also delete the pet from the db
		CharSummonTable.getInstance().getPets().remove(getOwner().getObjectId());
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer, true))
		{
			return false;
		}
		stopFeed();
		sendPacket(SystemMessageId.MAKE_SURE_YOU_RESSURECT_YOUR_PET_WITHIN_24_HOURS);
		DecayTaskManager.getInstance().addDecayTask(this, PET_DECAY_DELAY);
		// do not decrease exp if is in duel, arena
		L2PcInstance owner = getOwner();
		if ((owner != null) && !owner.isInDuel() && (!isInsideZone(ZoneId.PVP) || isInsideZone(ZoneId.SIEGE)))
		{
			deathPenalty();
		}
		return true;
	}
	
	@Override
	public void doRevive()
	{
		getOwner().removeReviving();
		
		super.doRevive();
		
		// stopDecay
		DecayTaskManager.getInstance().cancelDecayTask(this);
		startFeed();
		if (!isHungry())
		{
			setRunning();
		}
		getAI().setIntention(CtrlIntention.AI_INTENTION_ACTIVE, null);
	}
	
	@Override
	public void doRevive(double revivePower)
	{
		// Restore the pet's lost experience,
		// depending on the % return of the skill used (based on its power).
		restoreExp(revivePower);
		doRevive();
	}
	
	/**
	 * Transfers item to another inventory
	 * @param process string identifier of process triggering this action
	 * @param objectId Item Identifier of the item to be transfered
	 * @param count Quantity of items to be transfered
	 * @param target
	 * @param actor the player requesting the item transfer
	 * @param reference Object referencing current action like NPC selling item or previous item in transformation
	 * @return L2ItemInstance corresponding to the new item or the updated item in inventory
	 */
	public L2ItemInstance transferItem(String process, int objectId, long count, Inventory target, L2PcInstance actor, L2Object reference)
	{
		L2ItemInstance oldItem = getInventory().getItemByObjectId(objectId);
		L2ItemInstance playerOldItem = target.getItemByItemId(oldItem.getId());
		L2ItemInstance newItem = getInventory().transferItem(process, objectId, count, target, actor, reference);
		
		if (newItem == null)
		{
			return null;
		}
		
		// Send inventory update packet
		PetInventoryUpdate petIU = new PetInventoryUpdate();
		if ((oldItem.getCount() > 0) && (oldItem != newItem))
		{
			petIU.addModifiedItem(oldItem);
		}
		else
		{
			petIU.addRemovedItem(oldItem);
		}
		sendPacket(petIU);
		
		// Send target update packet
		if (!newItem.isStackable())
		{
			InventoryUpdate iu = new InventoryUpdate();
			iu.addNewItem(newItem);
			sendPacket(iu);
		}
		else if ((playerOldItem != null) && newItem.isStackable())
		{
			InventoryUpdate iu = new InventoryUpdate();
			iu.addModifiedItem(newItem);
			sendPacket(iu);
		}
		
		return newItem;
	}
	
	/**
	 * Remove the Pet from DB and its associated item from the player inventory
	 * @param owner The owner from whose inventory we should delete the item
	 * @param evolve
	 */
	public void destroyControlItem(L2PcInstance owner, boolean evolve)
	{
		// remove the pet instance from world
		L2World.getInstance().removePet(owner.getObjectId());
		
		// delete from inventory
		try
		{
			L2ItemInstance removedItem;
			if (evolve)
			{
				removedItem = owner.getInventory().destroyItem("Evolve", getControlObjectId(), 1, getOwner(), this);
			}
			else
			{
				removedItem = owner.getInventory().destroyItem("PetDestroy", getControlObjectId(), 1, getOwner(), this);
				if (removedItem != null)
				{
					SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_DISAPPEARED);
					sm.addItemName(removedItem);
					owner.sendPacket(sm);
				}
			}
			
			if (removedItem == null)
			{
				_log.warning("Couldn't destroy pet control item for " + owner + " pet: " + this + " evolve: " + evolve);
			}
			else
			{
				InventoryUpdate iu = new InventoryUpdate();
				iu.addRemovedItem(removedItem);
				
				owner.sendPacket(iu);
				
				StatusUpdate su = new StatusUpdate(owner);
				su.addAttribute(StatusUpdate.CUR_LOAD, owner.getCurrentLoad());
				owner.sendPacket(su);
				
				owner.broadcastUserInfo();
				
				L2World.getInstance().removeObject(removedItem);
			}
		}
		catch (Exception e)
		{
			_logPet.log(Level.WARNING, "Error while destroying control item: " + e.getMessage(), e);
		}
		
		// pet control item no longer exists, delete the pet from the db
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("DELETE FROM pets WHERE item_obj_id = ?"))
		{
			statement.setInt(1, getControlObjectId());
			statement.execute();
		}
		catch (Exception e)
		{
			_logPet.log(Level.SEVERE, "Failed to delete Pet [ObjectId: " + getObjectId() + "]", e);
		}
	}
	
	public void dropAllItems()
	{
		try
		{
			for (L2ItemInstance item : getInventory().getItems())
			{
				dropItemHere(item);
			}
		}
		catch (Exception e)
		{
			_logPet.log(Level.WARNING, "Pet Drop Error: " + e.getMessage(), e);
		}
	}
	
	public void dropItemHere(L2ItemInstance dropit, boolean protect)
	{
		dropit = getInventory().dropItem("Drop", dropit.getObjectId(), dropit.getCount(), getOwner(), this);
		
		if (dropit != null)
		{
			if (protect)
			{
				dropit.getDropProtection().protect(getOwner());
			}
			_logPet.finer("Item id to drop: " + dropit.getId() + " amount: " + dropit.getCount());
			dropit.dropMe(this, getX(), getY(), getZ() + 100);
		}
	}
	
	public void dropItemHere(L2ItemInstance dropit)
	{
		dropItemHere(dropit, false);
	}
	
	/** @return Returns the mount able. */
	@Override
	public boolean isMountable()
	{
		return _mountable;
	}
	
	private static L2PetInstance restore(L2ItemInstance control, L2NpcTemplate template, L2PcInstance owner)
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT item_obj_id, name, level, curHp, curMp, exp, sp, fed FROM pets WHERE item_obj_id=?"))
		{
			L2PetInstance pet;
			statement.setInt(1, control.getObjectId());
			try (ResultSet rset = statement.executeQuery())
			{
				final int id = IdFactory.getInstance().getNextId();
				if (!rset.next())
				{
					if (template.isType("L2BabyPet"))
					{
						pet = new L2BabyPetInstance(id, template, owner, control);
					}
					else
					{
						pet = new L2PetInstance(id, template, owner, control);
					}
					return pet;
				}
				
				if (template.isType("L2BabyPet"))
				{
					pet = new L2BabyPetInstance(id, template, owner, control, rset.getByte("level"));
				}
				else
				{
					pet = new L2PetInstance(id, template, owner, control, rset.getByte("level"));
				}
				
				pet._respawned = true;
				pet.setName(rset.getString("name"));
				
				long exp = rset.getLong("exp");
				L2PetLevelData info = PetDataTable.getInstance().getPetLevelData(pet.getId(), pet.getLevel());
				// DS: update experience based by level
				// Avoiding pet delevels due to exp per level values changed.
				if ((info != null) && (exp < info.getPetMaxExp()))
				{
					exp = info.getPetMaxExp();
				}
				
				pet.getStat().setExp(exp);
				pet.getStat().setSp(rset.getInt("sp"));
				
				pet.getStatus().setCurrentHp(rset.getInt("curHp"));
				pet.getStatus().setCurrentMp(rset.getInt("curMp"));
				pet.getStatus().setCurrentCp(pet.getMaxCp());
				if (rset.getDouble("curHp") < 1)
				{
					pet.setIsDead(true);
					pet.stopHpMpRegeneration();
				}
				
				pet.setCurrentFed(rset.getInt("fed"));
			}
			return pet;
		}
		catch (Exception e)
		{
			_logPet.log(Level.WARNING, "Could not restore pet data for owner: " + owner + " - " + e.getMessage(), e);
		}
		return null;
	}
	
	@Override
	public void setRestoreSummon(boolean val)
	{
		_restoreSummon = val;
	}
	
	@Override
	public final void stopSkillEffects(boolean removed, int skillId)
	{
		super.stopSkillEffects(removed, skillId);
		List<SummonEffect> effects = SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId());
		if ((effects != null) && !effects.isEmpty())
		{
			for (SummonEffect effect : effects)
			{
				if (effect.getSkill().getId() == skillId)
				{
					SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId()).remove(effect);
				}
			}
		}
	}
	
	@Override
	public void storeMe()
	{
		if (getControlObjectId() == 0)
		{
			// this is a summon, not a pet, don't store anything
			return;
		}
		
		if (!Config.RESTORE_PET_ON_RECONNECT)
		{
			_restoreSummon = false;
		}
		
		String req;
		if (!isRespawned())
		{
			req = "INSERT INTO pets (name,level,curHp,curMp,exp,sp,fed,ownerId,restore,item_obj_id) " + "VALUES (?,?,?,?,?,?,?,?,?,?)";
		}
		else
		{
			req = "UPDATE pets SET name=?,level=?,curHp=?,curMp=?,exp=?,sp=?,fed=?,ownerId=?,restore=? " + "WHERE item_obj_id = ?";
		}
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(req))
		{
			statement.setString(1, getName());
			statement.setInt(2, getStat().getLevel());
			statement.setDouble(3, getStatus().getCurrentHp());
			statement.setDouble(4, getStatus().getCurrentMp());
			statement.setLong(5, getStat().getExp());
			statement.setInt(6, getStat().getSp());
			statement.setInt(7, getCurrentFed());
			statement.setInt(8, getOwner().getObjectId());
			statement.setString(9, String.valueOf(_restoreSummon)); // True restores pet on login
			statement.setInt(10, getControlObjectId());
			statement.executeUpdate();
			
			_respawned = true;
			
			if (_restoreSummon)
			{
				CharSummonTable.getInstance().getPets().put(getOwner().getObjectId(), getControlObjectId());
			}
			else
			{
				CharSummonTable.getInstance().getPets().remove(getOwner().getObjectId());
			}
		}
		catch (Exception e)
		{
			_logPet.log(Level.SEVERE, "Failed to store Pet [ObjectId: " + getObjectId() + "] data", e);
		}
		
		L2ItemInstance itemInst = getControlItem();
		if ((itemInst != null) && (itemInst.getEnchantLevel() != getStat().getLevel()))
		{
			itemInst.setEnchantLevel(getStat().getLevel());
			itemInst.updateDatabase();
		}
	}
	
	@Override
	public void storeEffect(boolean storeEffects)
	{
		if (!Config.SUMMON_STORE_SKILL_COOLTIME)
		{
			return;
		}
		
		// Clear list for overwrite
		if (SummonEffectsTable.getInstance().getPetEffects().contains(getControlObjectId()))
		{
			SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId()).clear();
		}
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps1 = con.prepareStatement(DELETE_SKILL_SAVE);
			PreparedStatement ps2 = con.prepareStatement(ADD_SKILL_SAVE))
		{
			// Delete all current stored effects for summon to avoid dupe
			ps1.setInt(1, getControlObjectId());
			ps1.execute();
			
			int buff_index = 0;
			
			final List<Integer> storedSkills = new FastList<>();
			
			// Store all effect data along with calculated remaining
			if (storeEffects)
			{
				for (L2Effect effect : getAllEffects())
				{
					if (effect == null)
					{
						continue;
					}
					
					switch (effect.getEffectType())
					{
						case HEAL_OVER_TIME:
						case CPHEAL_OVER_TIME:
							// TODO: Fix me.
						case HIDE:
							continue;
					}
					
					L2Skill skill = effect.getSkill();
					if (storedSkills.contains(skill.getReuseHashCode()))
					{
						continue;
					}
					
					storedSkills.add(skill.getReuseHashCode());
					
					if (effect.isInUse() && !skill.isToggle())
					{
						ps2.setInt(1, getControlObjectId());
						ps2.setInt(2, skill.getId());
						ps2.setInt(3, skill.getLevel());
						ps2.setInt(4, effect.getTickCount());
						ps2.setInt(5, effect.getTime());
						ps2.setInt(6, ++buff_index);
						ps2.execute();
						
						if (!SummonEffectsTable.getInstance().getPetEffects().contains(getControlObjectId()))
						{
							SummonEffectsTable.getInstance().getPetEffects().put(getControlObjectId(), new FastList<SummonEffect>());
						}
						
						SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId()).add(SummonEffectsTable.getInstance().new SummonEffect(skill, effect.getTickCount(), effect.getTime()));
					}
				}
			}
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Could not store pet effect data: ", e);
		}
	}
	
	@Override
	public void restoreEffects()
	{
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement ps1 = con.prepareStatement(RESTORE_SKILL_SAVE);
			PreparedStatement ps2 = con.prepareStatement(DELETE_SKILL_SAVE))
		{
			if (!SummonEffectsTable.getInstance().getPetEffects().contains(getControlObjectId()))
			{
				ps1.setInt(1, getControlObjectId());
				try (ResultSet rset = ps1.executeQuery())
				{
					while (rset.next())
					{
						int effectCount = rset.getInt("effect_count");
						int effectCurTime = rset.getInt("effect_cur_time");
						
						final L2Skill skill = SkillTable.getInstance().getInfo(rset.getInt("skill_id"), rset.getInt("skill_level"));
						if (skill == null)
						{
							continue;
						}
						
						if (skill.hasEffects())
						{
							if (!SummonEffectsTable.getInstance().getPetEffects().contains(getControlObjectId()))
							{
								SummonEffectsTable.getInstance().getPetEffects().put(getControlObjectId(), new FastList<SummonEffect>());
							}
							
							SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId()).add(SummonEffectsTable.getInstance().new SummonEffect(skill, effectCount, effectCurTime));
						}
					}
				}
			}
			
			ps2.setInt(1, getControlObjectId());
			ps2.executeUpdate();
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Could not restore " + this + " active effect data: " + e.getMessage(), e);
		}
		finally
		{
			if (SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId()) == null)
			{
				return;
			}
			
			for (SummonEffect se : SummonEffectsTable.getInstance().getPetEffects().get(getControlObjectId()))
			{
				if ((se != null) && se.getSkill().hasEffects())
				{
					if (se.getSkill().hasEffects())
					{
						final Env env = new Env();
						env.setCharacter(this);
						env.setTarget(this);
						env.setSkill(se.getSkill());
						final L2Effect[] effects = new L2Effect[se.getSkill().getEffectTemplates().size()];
						int index = 0;
						for (EffectTemplate et : se.getSkill().getEffectTemplates())
						{
							L2Effect effect = et.getEffect(env);
							if (effect != null)
							{
								effect.setCount(se.getEffectCount());
								effect.setFirstTime(se.getEffectCurTime());
								effect.scheduleEffect();
								effects[index++] = effect;
							}
						}
						getEffectList().add(effects);
					}
				}
			}
		}
	}
	
	public synchronized void stopFeed()
	{
		if (_feedTask != null)
		{
			_feedTask.cancel(false);
			_feedTask = null;
		}
	}
	
	public synchronized void startFeed()
	{
		// stop feeding task if its active
		
		stopFeed();
		if (!isDead() && (getOwner().getSummon() == this))
		{
			_feedTask = ThreadPoolManager.getInstance().scheduleGeneralAtFixedRate(new FeedTask(), 10000, 10000);
		}
	}
	
	@Override
	public synchronized void unSummon(L2PcInstance owner)
	{
		stopFeed();
		stopHpMpRegeneration();
		super.unSummon(owner);
		
		if (!isDead())
		{
			if (getInventory() != null)
			{
				getInventory().deleteMe();
			}
			L2World.getInstance().removePet(owner.getObjectId());
		}
	}
	
	/**
	 * Restore the specified % of experience this L2PetInstance has lost.<BR>
	 * <BR>
	 * @param restorePercent
	 */
	public void restoreExp(double restorePercent)
	{
		if (_expBeforeDeath > 0)
		{
			// Restore the specified % of lost experience.
			getStat().addExp(Math.round(((_expBeforeDeath - getStat().getExp()) * restorePercent) / 100));
			_expBeforeDeath = 0;
		}
	}
	
	private void deathPenalty()
	{
		// TODO: Need Correct Penalty
		
		int lvl = getStat().getLevel();
		double percentLost = (-0.07 * lvl) + 6.5;
		
		// Calculate the Experience loss
		long lostExp = Math.round(((getStat().getExpForLevel(lvl + 1) - getStat().getExpForLevel(lvl)) * percentLost) / 100);
		
		// Get the Experience before applying penalty
		_expBeforeDeath = getStat().getExp();
		
		// Set the new Experience value of the L2PetInstance
		getStat().addExp(-lostExp);
	}
	
	@Override
	public void addExpAndSp(long addToExp, int addToSp)
	{
		if (getId() == 12564)
		{
			getStat().addExpAndSp(Math.round(addToExp * Config.SINEATER_XP_RATE), addToSp);
		}
		else
		{
			getStat().addExpAndSp(Math.round(addToExp * Config.PET_XP_RATE), addToSp);
		}
	}
	
	@Override
	public long getExpForThisLevel()
	{
		return getStat().getExpForLevel(getLevel());
	}
	
	@Override
	public long getExpForNextLevel()
	{
		return getStat().getExpForLevel(getLevel() + 1);
	}
	
	@Override
	public final int getLevel()
	{
		return getStat().getLevel();
	}
	
	public int getMaxFed()
	{
		return getStat().getMaxFeed();
	}
	
	@Override
	public int getCriticalHit(L2Character target, L2Skill skill)
	{
		return getStat().getCriticalHit(target, skill);
	}
	
	@Override
	public int getMAtk(L2Character target, L2Skill skill)
	{
		return getStat().getMAtk(target, skill);
	}
	
	@Override
	public int getMDef(L2Character target, L2Skill skill)
	{
		return getStat().getMDef(target, skill);
	}
	
	@Override
	public final int getSkillLevel(int skillId)
	{
		if (getKnownSkill(skillId) == null)
		{
			return -1;
		}
		
		final int lvl = getLevel();
		return lvl > 70 ? 7 + ((lvl - 70) / 5) : lvl / 10;
	}
	
	public void updateRefOwner(L2PcInstance owner)
	{
		int oldOwnerId = getOwner().getObjectId();
		
		setOwner(owner);
		L2World.getInstance().removePet(oldOwnerId);
		L2World.getInstance().addPet(oldOwnerId, this);
	}
	
	public int getInventoryLimit()
	{
		return Config.INVENTORY_MAXIMUM_PET;
	}
	
	public void refreshOverloaded()
	{
		int maxLoad = getMaxLoad();
		if (maxLoad > 0)
		{
			long weightproc = (((getCurrentLoad() - getBonusWeightPenalty()) * 1000) / maxLoad);
			int newWeightPenalty;
			if ((weightproc < 500) || getOwner().getDietMode())
			{
				newWeightPenalty = 0;
			}
			else if (weightproc < 666)
			{
				newWeightPenalty = 1;
			}
			else if (weightproc < 800)
			{
				newWeightPenalty = 2;
			}
			else if (weightproc < 1000)
			{
				newWeightPenalty = 3;
			}
			else
			{
				newWeightPenalty = 4;
			}
			
			if (_curWeightPenalty != newWeightPenalty)
			{
				_curWeightPenalty = newWeightPenalty;
				if (newWeightPenalty > 0)
				{
					addSkill(SkillTable.getInstance().getInfo(4270, newWeightPenalty));
					setIsOverloaded(getCurrentLoad() >= maxLoad);
				}
				else
				{
					removeSkill(getKnownSkill(4270), true);
					setIsOverloaded(false);
				}
			}
		}
	}
	
	@Override
	public void updateAndBroadcastStatus(int val)
	{
		refreshOverloaded();
		super.updateAndBroadcastStatus(val);
	}
	
	@Override
	public final boolean isHungry()
	{
		return getCurrentFed() < ((getPetData().getHungryLimit() / 100f) * getPetLevelData().getPetMaxFeed());
	}
	
	@Override
	public final int getWeapon()
	{
		L2ItemInstance weapon = getInventory().getPaperdollItem(Inventory.PAPERDOLL_RHAND);
		if (weapon != null)
		{
			return weapon.getId();
		}
		return 0;
	}
	
	@Override
	public final int getArmor()
	{
		L2ItemInstance weapon = getInventory().getPaperdollItem(Inventory.PAPERDOLL_CHEST);
		if (weapon != null)
		{
			return weapon.getId();
		}
		return 0;
	}
	
	public final int getJewel()
	{
		L2ItemInstance weapon = getInventory().getPaperdollItem(Inventory.PAPERDOLL_NECK);
		if (weapon != null)
		{
			return weapon.getId();
		}
		return 0;
	}
	
	@Override
	public short getSoulShotsPerHit()
	{
		return getPetLevelData().getPetSoulShot();
	}
	
	@Override
	public short getSpiritShotsPerHit()
	{
		return getPetLevelData().getPetSpiritShot();
	}
	
	@Override
	public void setName(String name)
	{
		final L2ItemInstance controlItem = getControlItem();
		if (controlItem != null)
		{
			if (controlItem.getCustomType2() == (name == null ? 1 : 0))
			{
				// name not set yet
				controlItem.setCustomType2(name != null ? 1 : 0);
				controlItem.updateDatabase();
				InventoryUpdate iu = new InventoryUpdate();
				iu.addModifiedItem(controlItem);
				sendPacket(iu);
			}
		}
		else
		{
			_log.log(Level.WARNING, "Pet control item null, for pet: " + toString());
		}
		super.setName(name);
	}
	
	public boolean canEatFoodId(int itemId)
	{
		return _data.getFood().contains(itemId);
	}
	
	public Map<Integer, TimeStamp> getSkillReuseTimeStamps()
	{
		return _reuseTimeStampsSkills;
	}
	
	@Override
	public void addTimeStamp(L2Skill skill, long reuse)
	{
		_reuseTimeStampsSkills.put(skill.getReuseHashCode(), new TimeStamp(skill, reuse));
	}
	
	@Override
	public long getSkillRemainingReuseTime(int skillReuseHashId)
	{
		if (_reuseTimeStampsSkills.isEmpty() || !_reuseTimeStampsSkills.containsKey(skillReuseHashId))
		{
			return -1;
		}
		return _reuseTimeStampsSkills.get(skillReuseHashId).getRemaining();
	}
	
	@Override
	public void addTimeStampItem(L2ItemInstance item, long reuse)
	{
		_reuseTimeStampsItems.put(item.getObjectId(), new TimeStamp(item, reuse));
	}
	
	@Override
	public long getItemRemainingReuseTime(int itemObjId)
	{
		if (_reuseTimeStampsItems.isEmpty() || !_reuseTimeStampsItems.containsKey(itemObjId))
		{
			return -1;
		}
		return _reuseTimeStampsItems.get(itemObjId).getRemaining();
	}
	
	@Override
	public boolean isPet()
	{
		return true;
	}
}