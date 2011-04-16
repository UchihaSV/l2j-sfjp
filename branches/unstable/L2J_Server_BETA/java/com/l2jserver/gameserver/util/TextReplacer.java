/* This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l2jserver.gameserver.util;

/*
 * String.replace() ÇÃë„ë÷
 * ÅEê≥ãKï\åªÇ»Çµ
 * 
 * @author  JOJO
 */
public class TextReplacer implements CharSequence/*, Appendable*/ {
	private char[] _ca;
	private int _count;

	public TextReplacer(String s)
	{
		_count = s.length();
		_ca = new char[_count * 2];
		s.getChars(0, _count, _ca, 0);
	}

	public TextReplacer replace(CharSequence token, CharSequence value)
	{
		final char first = token.charAt(0);
		final int token_length = token.length();
		final int value_length = value.length();
		for (int start = 0, end; ; start += value_length) {
			indexOf: {
				int max = _count - token_length;
				unko:
				for (; start < max; start++) {
					if (_ca[start] == first) {
						end = start + 1;
						int i = 1;
						while (i < token_length)
							if (_ca[end++] != token.charAt(i++))
								continue unko;
						/*assert(end == start + token_length);*/
						break indexOf;
					}
				}
				return this;
			}
			int newLen = _count - token_length + value_length;
			if (newLen > _ca.length) {
				char[] copy = new char[newLen * 2];
				System.arraycopy(_ca, 0, copy, 0, _count);
				_ca = copy;
			}
			int d = 0 - token_length + value_length;
			if (d < 0)
				{ for (int i = end; i <= _count-1; i++) _ca[i+d] = _ca[i]; }
			else if (d > 0)
				{ for (int i = _count-1; i >= end; i--) _ca[i+d] = _ca[i]; }
			_count = newLen;
		//	value.getChars(0, value_length, _ca, start);
			for (int i = 0; i < value_length; i++)
				_ca[start+i] = value.charAt(i);
		}
	}
	public TextReplacer replace(CharSequence token, boolean value)
	{
		return replace(token, Boolean.toString(value));
	}
	public TextReplacer replace(CharSequence token, int value)
	{
		return replace(token, Integer.toString(value));
	}
	public TextReplacer replace(CharSequence token, long value)
	{
		return replace(token, Long.toString(value));
	}
	public TextReplacer replace(CharSequence token, float value)
	{
		return replace(token, Float.toString(value));
	}
	public TextReplacer replace(CharSequence token, double value)
	{
		return replace(token, Double.toString(value));
	}

//	public TextReplacer append(CharSequence value)
//	{
//		int newLen = _count + value.length();
//		if (newLen > _ca.length) {
//			char[] copy = new char[newLen * 2];
//	        System.arraycopy(_ca, 0, copy, 0, _count);
//	        _ca = copy;
//		}
//		for (int i = 0; i < value.length(); )
//			_ca[_count++] = value.charAt(i++);
//		return this;
//	}
//	public TextReplacer append(int value)
//	{
//		return append(Integer.toString(value));
//	}

	@Override public String toString() { return new String(_ca, 0, _count); }
	public char charAt(int index) { return _ca[index]; }
	public int length() { return _count; }
	public CharSequence subSequence(int start, int end) { return new String(_ca, start, end-start); }
}
