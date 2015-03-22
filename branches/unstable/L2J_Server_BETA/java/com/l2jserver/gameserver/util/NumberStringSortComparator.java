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
public class NumberStringSortComparator implements java.util.Comparator<CharSequence>
{
	protected final boolean isDigit(char c) { return '0' <= c && c <= '9'; }
	protected final char toUpper(char c) { return (char) ('a' <= c && c <= 'z' ? c - 'a' + 'A' : c); }

	@Override
	public int compare(CharSequence a, CharSequence b)
	{
		int result;
		final int na = a.length(), nb = b.length();
		int ia = 0, ib = 0;
		
		while (ia < na && ib < nb)
		{
			char ca = a.charAt(ia);
			char cb = b.charAt(ib);
			if (isDigit(ca) && isDigit(cb))
			{
				while (ia < na && a.charAt(ia) == '0')
					++ia;
				while (ib < nb && b.charAt(ib) == '0')
					++ib;
				int xa = ia, xb = ib;
				while (ia < na && isDigit(a.charAt(ia)))
					++ia;
				while (ib < nb && isDigit(b.charAt(ib)))
					++ib;
				if ((result = (ia - xa) - (ib - xb)) != 0)
					return result;
				while (xa < ia)
					if ((result = a.charAt(xa++) - b.charAt(xb++)) != 0)
						return result;
				if ((result = ia - ib) != 0)
					return result;
			}
			else
			{
				if ((result = ca - cb) != 0)
					return result;
				++ia;
				++ib;
			}
		}
		return na - nb;
	}
}
