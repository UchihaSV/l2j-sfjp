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
package com.l2jserver.gameserver.datatables;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.l2jserver.gameserver.engines.DocumentParser;
import com.l2jserver.gameserver.model.L2Seed;
import com.l2jserver.gameserver.model.StatsSet;
import com.l2jserver.gameserver.model.items.L2Item;
import com.l2jserver.util.L2FastMap;

/**
 * Service class for manor
 * @author l3x, UnAfraid
 */
public class ManorData extends DocumentParser
{
	private static Logger _log = Logger.getLogger(ManorData.class.getName());
	
	private static Map<Integer, L2Seed> _seeds;
	
	protected ManorData()
	{
		_seeds = new L2FastMap<>(true);
		load();
	}
	
	@Override
	public void load()
	{
		_seeds.clear();
		parseDatapackFile("data/seeds.xml");
		_log.log(Level.INFO, getClass().getSimpleName() + ": Loaded: " + _seeds.size() + " Seeds");
	}
	
	@Override
	protected void parseDocument()
	{
		Document doc = getCurrentDocument();
		doc.getDocumentElement().normalize();
		StatsSet set;
		for (Node n = doc.getFirstChild(); n != null; n = n.getNextSibling())
		{
			if ("list".equalsIgnoreCase(n.getNodeName()))
			{
				// castle
				for (Node d = n.getFirstChild(); d != null; d = d.getNextSibling())
				{
					if ("castle".equalsIgnoreCase(d.getNodeName()))
					{
						int castleId = parseInt(d.getAttributes(), "id");
						// crop
						for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling())
						{
							if ("crop".equalsIgnoreCase(c.getNodeName()))
							{
								set = new StatsSet();
								set.set("cropId", parseString(c.getAttributes(), "id"));
								set.set("castleId", castleId);
								
								for (Node a = c.getFirstChild(); a != null; a = a.getNextSibling())
								{
									switch (a.getNodeName())
									{
										case "seed_id":
										case "mature_id":
										case "reward1":
										case "reward2":
										case "alternative":
										case "level":
										case "limit_seed":
										case "limit_crops":
										{
											set.set(a.getNodeName(), parseString(a.getAttributes(), "val"));
											break;
										}
									}
								}
								
								L2Seed seed = new L2Seed(set);
								_seeds.put(seed.getSeedId(), seed);
							}
						}
					}
				}
			}
		}
	}
	
	public Collection<L2Seed> getSeedsData()
	{
		return _seeds.values();
	}
	
	public List<Integer> getAllCrops()
	{
		List<Integer> crops = new ArrayList<>();
		
		for (L2Seed seed : getSeedsData())
		{
			if (!crops.contains(seed.getCropId()) && (seed.getCropId() != 0) && !crops.contains(seed.getCropId()))
			{
				crops.add(seed.getCropId());
			}
		}
		
		return crops;
	}
	
	public int getSeedBasicPrice(int seedId)
	{
		final L2Item seedItem = ItemTable.getInstance().getTemplate(seedId);
		if (seedItem != null)
		{
			return seedItem.getReferencePrice();
		}
		return 0;
	}
	
	public int getSeedBasicPriceByCrop(int cropId)
	{
		for (L2Seed seed : getSeedsData())
		{
			if (seed.getCropId() == cropId)
			{
				return getSeedBasicPrice(seed.getSeedId());
			}
		}
		return 0;
	}
	
	public int getCropBasicPrice(int cropId)
	{
		final L2Item cropItem = ItemTable.getInstance().getTemplate(cropId);
		if (cropItem != null)
		{
			return cropItem.getReferencePrice();
		}
		return 0;
	}
	
	public int getMatureCrop(int cropId)
	{
		for (L2Seed seed : getSeedsData())
		{
			if (seed.getCropId() == cropId)
			{
				return seed.getMatureId();
			}
		}
		return 0;
	}
	
	/**
	 * Returns price which lord pays to buy one seed
	 * @param seedId
	 * @return seed price
	 */
	public long getSeedBuyPrice(int seedId)
	{
		long buyPrice = getSeedBasicPrice(seedId);
		return (buyPrice > 0 ? buyPrice : 1);
	}
	
	public int getSeedMinLevel(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getLevel() - 5;
		}
		return -1;
	}
	
	public int getSeedMaxLevel(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getLevel() + 5;
		}
		return -1;
	}
	
	public int getSeedLevelByCrop(int cropId)
	{
		for (L2Seed seed : getSeedsData())
		{
			if (seed.getCropId() == cropId)
			{
				return seed.getLevel();
			}
		}
		return 0;
	}
	
	public int getSeedLevel(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getLevel();
		}
		return -1;
	}
	
	public boolean isAlternative(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.isAlternative();
		}
		return false;
	}
	
	public int getCropType(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getCropId();
		}
		return -1;
	}
	
	public int getRewardItem(int cropId, int type)
	{
		for (L2Seed seed : getSeedsData())
		{
			if (seed.getCropId() == cropId)
			{
				return seed.getReward(type); // there can be several seeds with same crop, but reward should be the same for all.
			}
		}
		return -1;
	}
	
	public int getRewardItemBySeed(int seedId, int type)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getReward(type);
		}
		return 0;
	}
	
	/**
	 * Return all crops which can be purchased by given castle
	 * @param castleId
	 * @return
	 */
	public List<Integer> getCropsForCastle(int castleId)
	{
		List<Integer> crops = new ArrayList<>();
		
		for (L2Seed seed : getSeedsData())
		{
			if ((seed.getCastleId() == castleId) && !crops.contains(seed.getCropId()))
			{
				crops.add(seed.getCropId());
			}
		}
		
		return crops;
	}
	
	/**
	 * Return list of seed ids, which belongs to castle with given id
	 * @param castleId - id of the castle
	 * @return seedIds - list of seed ids
	 */
	public List<Integer> getSeedsForCastle(int castleId)
	{
		List<Integer> seedsID = new ArrayList<>();
		
		for (L2Seed seed : getSeedsData())
		{
			if ((seed.getCastleId() == castleId) && !seedsID.contains(seed.getSeedId()))
			{
				seedsID.add(seed.getSeedId());
			}
		}
		
		return seedsID;
	}
	
	/**
	 * Returns castle id where seed can be sowned<br>
	 * @param seedId
	 * @return castleId
	 */
	public int getCastleIdForSeed(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getCastleId();
		}
		return 0;
	}
	
	public int getSeedSaleLimit(int seedId)
	{
		L2Seed seed = _seeds.get(seedId);
		
		if (seed != null)
		{
			return seed.getSeedLimit();
		}
		return 0;
	}
	
	public int getCropPuchaseLimit(int cropId)
	{
		for (L2Seed seed : getSeedsData())
		{
			if (seed.getCropId() == cropId)
			{
				return seed.getCropLimit();
			}
		}
		return 0;
	}
	
	public static ManorData getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final ManorData _instance = new ManorData();
	}
}
