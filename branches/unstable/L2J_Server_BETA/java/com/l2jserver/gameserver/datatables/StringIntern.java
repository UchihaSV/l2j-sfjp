/*
 * Copyright (C) 2005-2008 L2J_JP / 2008-2013 L2J-SFJP
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
package com.l2jserver.gameserver.datatables;

import javolution.util.FastMap;

import com.l2jserver.Config;

/**
 * データ テーブル内の同じ文字列を1つのインスタンスに集約する。
 * @author JOJO
 */
public class StringIntern
{
	static private FastMap<String, String> _stringSet;
	
	public static void begin()
	{
		if (Config.STRING_INTERN) {
			if (_stringSet != null)
				throw new RuntimeException();
			_stringSet = new FastMap<>();
		}
	}
	
	public static String intern(String v)
	{
		if (Config.STRING_INTERN) {
			if (v == null)
				return null;
			String a;
			if ((a = _stringSet.putIfAbsent(v, v)) != null)
				return a;
			return v;
		} else {
			return v;
		}
	}
	
	public static void end()
	{
		if (Config.STRING_INTERN) {
			if (_stringSet == null || _stringSet.size() == 0)
				throw new RuntimeException();
			_stringSet = null;
		}
	}
}
