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
 * String.replace() ‚Ì‘ã‘Ö
 * E³‹K•\Œ»‚È‚µ
 * E‚R‰ñˆÈã’uŠ·‚·‚é‚È‚ç‚‘¬
 * 
 * @author  JOJO
 */
public class TextReplacerLite implements CharSequence/*, Appendable*/ {
	StringBuilder sb;

	public TextReplacerLite(CharSequence s)
	{
		this.sb = new StringBuilder(s);
	}

	public TextReplacerLite replace(CharSequence token, CharSequence value)
	{
		for (int start = 0; (start = sb.indexOf(token.toString(), start)) >= 0; start += value.length()) {
			int end = start + token.length();
			sb.replace(start, end, value.toString());
		}
		return this;
	}
//	public TextReplacer replaceOnce(CharSequence token, CharSequence value)
//	{
//		int start;
//		if ((start = sb.indexOf(token.toString())) >= 0) {
//			int end = start + token.length();
//			sb.replace(start, end, value.toString());
//		}
//		return this;
//	}
	public TextReplacerLite replace(CharSequence token, int value)
	{
		return replace(token, String.valueOf(value));
	}

//	public TextReplacer append(CharSequence s)
//	{
//		sb.append(s);
//		return this;
//	}

	@Override public String toString() { return sb.toString(); }
	@Override public char charAt(int index) { return sb.charAt(index); }
	@Override public int length() { return sb.length(); }
	@Override public CharSequence subSequence(int start, int end) { return sb.subSequence(start, end); }
}
