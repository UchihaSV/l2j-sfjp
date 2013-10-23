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
package com.l2jserver.gameserver.model.buylist;

import java.util.Collection;

import jp.sf.l2j.troja.FastIntObjectMap;

/**
 * @author Nos
 */
public final class L2BuyList
{
	private final int _listId;
	private final FastIntObjectMap<Product> _products = new FastIntObjectMap<>();	//[JOJO] LinkedHashMap --> FastIntObjectMap
	protected int[] _allowedNpcs = null;	//[JOJO] Set<Integer> --> int[]
	
	public L2BuyList(int listId)
	{
		_listId = listId;
	}
	
	public int getListId()
	{
		return _listId;
	}
	
	public Collection<Product> getProducts()
	{
		return _products.values();
	}
	
	public Product getProductByItemId(int itemId)
	{
		return _products.get(itemId);
	}
	
	public void addProduct(Product product)
	{
		_products.put(product.getItemId(), product);
	}
	
	public void addAllowedNpc(int npcId)
	{
		_allowedNpcs = SortedIntArraySet.add(_allowedNpcs, npcId);
	}
	
	public boolean isNpcAllowed(int npcId)
	{
		return SortedIntArraySet.contains(_allowedNpcs, npcId);
	}
	
	//[JOJO]-------------------------------------------------
	// デバッグ用
	/**@Deprecated*/public int[] getAllowedNpcs()
	{
		return _allowedNpcs;
	}
	//-------------------------------------------------------
}
