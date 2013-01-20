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
package com.l2jserver.gameserver.util;

/**
 * @author JOJO
 *
 */
public class QuestNameSortComparator implements java.util.Comparator<CharSequence>
{
	@Override
	public int compare(CharSequence s1, CharSequence s2)
	{
		int result;
		final int len1 = s1.length(), len2 = s2.length();
		int a1 = 0, a2 = 0;
		if (len1 > 2 && s1.charAt(0) == 'Q' && Character.isDigit(s1.charAt(1))) ++a1;
		if (len2 > 2 && s2.charAt(0) == 'Q' && Character.isDigit(s2.charAt(1))) ++a2;
		
		while (a1 < len1 && a2 < len2)
		{
			char c1 = s1.charAt(a1);
			char c2 = s2.charAt(a2);
			if (Character.isDigit(c1) && Character.isDigit(c2))
			{
				while (a1 < len1 && s1.charAt(a1) == '0')
					++a1;
				while (a2 < len2 && s2.charAt(a2) == '0')
					++a2;
				int x1 = a1, x2 = a2;
				while (a1 < len1 && Character.isDigit(s1.charAt(a1)))
					++a1;
				while (a2 < len2 && Character.isDigit(s2.charAt(a2)))
					++a2;
				if ((result = (a1 - x1) - (a2 - x2)) != 0)
					return result;
				while (x1 < a1 /* && x2 < a2 */)
					if ((result = s1.charAt(x1++) - s2.charAt(x2++)) != 0)
						return result;
				if ((result = a1 - a2) != 0)
					return result;
			}
			else
			{
				if ((result = c1 - c2) != 0)
					return result;
				++a1;
				++a2;
			}
		}
		return len1 - len2;
	}
}
