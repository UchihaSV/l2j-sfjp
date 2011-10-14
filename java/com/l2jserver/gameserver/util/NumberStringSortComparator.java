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
package com.l2jserver.gameserver.util;

/**
 * @author PowerUser
 *
 */
public class NumberStringSortComparator implements java.util.Comparator<CharSequence>
{
	private static final boolean DEBUG = false;
	
	@Override
	public int compare(CharSequence s1, CharSequence s2)
	{
		int length = Math.min(s1.length(), s2.length());
		int result;
		for (int index = 0; index < length; ++index)
		{
			char c1 = s1.charAt(index);
			char c2 = s2.charAt(index);
			if (Character.isDigit(c1) && Character.isDigit(c2))
			{
				int x1, y1, x2, y2;
				x1 = x2 = index;
				while (x1 < length && s1.charAt(x1) == '0')
					++x1;
				while (x2 < length && s2.charAt(x2) == '0')
					++x2;
				y1 = x1;
				y2 = x2;
				while (y1 < length && Character.isDigit(s1.charAt(y1)))
					++y1;
				while (y2 < length && Character.isDigit(s2.charAt(y2)))
					++y2;
				if ((result = (y1 - x1) - (y2 - x2)) != 0)
					return result;
				while (x1 < y1 /* && x2 < y2 */)
					if ((result = s1.charAt(x1++) - s2.charAt(x2++)) != 0)
						return result;
				if ((result = y1 - y2) != 0)
					return result;
if (DEBUG) {{
				if (x1 != x2) throw new RuntimeException();
				if (y1 != y2) throw new RuntimeException();
				for (; index < y1; ++index)
					if (s1.charAt(index) != s2.charAt(index))
						throw new RuntimeException();
}}
				index = y1;
			}
			else
			{
				if ((result = c1 - c2) != 0)
					return result;
			}
		}
		return s1.length() - s2.length();
	}
}
