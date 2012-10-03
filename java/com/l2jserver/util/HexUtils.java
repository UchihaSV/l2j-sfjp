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
package com.l2jserver.util;


/**
 * @author FBIagent
 * modify JOJO
 */
public class HexUtils
{
	// lookup table for hex characters
	private static final char[] NIBBLE_CHAR_LOOKUP =
	{
		'0',
		'1',
		'2',
		'3',
		'4',
		'5',
		'6',
		'7',
		'8',
		'9',
		'A',
		'B',
		'C',
		'D',
		'E',
		'F'
	};
	
	/**
	 * Method to generate the hexadecimal character presentation of a byte<br>
	 * This call is equivalent to {@link HexUtils#b2HexChars(byte, char[], int)} with parameters (data, null, 0)
	 * @param data byte to generate the hexadecimal character presentation from
	 * @return a new char array with exactly 2 elements
	 */
	public static char[] b2HexChars(final byte data)
	{
		return b2HexChars(data, null, 0);
	}
	
	/**
	 * Method to generate the hexadecimal character presentation of a byte
	 * @param data byte to generate the hexadecimal character presentation from
	 * @param dstHexChars the char array the hexadecimal character presentation should be copied to, if this is null, dstOffset is ignored and a new char array with 2 elements is created
	 * @param dstOffset offset at which the hexadecimal character presentation is copied to dstHexChars
	 * @return the char array the hexadecimal character presentation was copied to
	 */
	public static char[] b2HexChars(final byte data, char[] dstHexChars, int dstOffset)
	{
		if (dstHexChars == null)
		{
			dstHexChars = new char[2];
			dstOffset = 0;
		}
		
		// /////////////////////////////
		// NIBBLE LOOKUP
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >> 4 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data      & 0x0F];
		
		return dstHexChars;
	}
	
	/**
	 * Method to generate the hexadecimal character presentation of an integer This call is equivalent to {@link HexUtils#int2HexChars(int, char[], int)} with parameters (data, null, 0)
	 * @param data integer to generate the hexadecimal character presentation from
	 * @return new char array with 8 elements
	 */
	public static char[] int2HexChars(final int data)
	{
		return int2HexChars(data, new char[8], 0);
	}
	
	/**
	 * Method to generate the hexadecimal character presentation of an integer
	 * @param data integer to generate the hexadecimal character presentation from
	 * @param dstHexChars the char array the hexadecimal character presentation should be copied to, if this is null, dstOffset is ignored and a new char array with 8 elements is created
	 * @param dstOffset offset at which the hexadecimal character presentation is copied to dstHexChars
	 * @return the char array the hexadecimal character presentation was copied to
	 */
	public static char[] int2HexChars(final int data, char[] dstHexChars, int dstOffset)
	{
		if (dstHexChars == null)
		{
			dstHexChars = new char[8];
			dstOffset = 0;
		}
		
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >> 28 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >> 24 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >> 20 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >> 16 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >> 12 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >>  8 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data >>  4 & 0x0F];
		dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data       & 0x0F];
		return dstHexChars;
	}
	
	/**
	 * Method to generate the hexadecimal character presentation of a byte array<br>
	 * This call is equivalent to {@link HexUtils#bArr2HexChars(byte[], int, int, char[], int)} with parameters (data, offset, len, null, 0)
	 * @param data byte array to generate the hexadecimal character presentation from
	 * @param offset offset where to start in data array
	 * @param len number of bytes to generate the hexadecimal character presentation from
	 * @return a new char array with len*2 elements
	 */
	public static char[] bArr2HexChars(final byte[] data, final int offset, final int len)
	{
		return bArr2HexChars(data, offset, len, null, 0);
	}
	
	/**
	 * Method to generate the hexadecimal character presentation of a byte array
	 * @param data byte array to generate the hexadecimal character presentation from
	 * @param offset offset where to start in data array
	 * @param len number of bytes to generate the hexadecimal character presentation from
	 * @param dstHexChars the char array the hexadecimal character presentation should be copied to, if this is null, dstOffset is ignored and a new char array with len*2 elements is created
	 * @param dstOffset offset at which the hexadecimal character presentation is copied to dstHexChars
	 * @return the char array the hexadecimal character presentation was copied to
	 */
	public static char[] bArr2HexChars(final byte[] data, final int offset, final int len, char[] dstHexChars, int dstOffset)
	{
		if (dstHexChars == null)
		{
			dstHexChars = new char[len * 2];
			dstOffset = 0;
		}
		
		for (int dataIdx = offset, end = len + offset; dataIdx < end; ++dataIdx)
		{
			// /////////////////////////////
			// NIBBLE LOOKUP, we duplicate the code from b2HexChars here, we want to save a few cycles(for charsIdx increment)
			dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data[dataIdx] >> 4 & 0x0F];
			dstHexChars[dstOffset++] = NIBBLE_CHAR_LOOKUP[data[dataIdx]      & 0x0F];
		}
		
		return dstHexChars;
	}
	
	public static char[] bArr2AsciiChars(byte[] data, final int offset, final int len)
	{
		return bArr2AsciiChars(data, offset, len, new char[len], 0);
	}
	
	public static char[] bArr2AsciiChars(byte[] data, final int offset, final int len, char[] dstAsciiChars, int dstOffset)
	{
		if (dstAsciiChars == null)
		{
			dstAsciiChars = new char[len];
			dstOffset = 0;
		}
		
		for (int dataIdx = offset, end = len + offset; dataIdx < end; ++dataIdx)
		{
			int b = data[dataIdx] & 0xFF;
			if (b > 0x1f && b < 0x80)
			{
				dstAsciiChars[dstOffset++] = (char) b;
			}
			else
			{
				dstAsciiChars[dstOffset++] = '.';
			}
		}
		
		return dstAsciiChars;
	}
	
	/**
	 * Method to generate the hexadecimal character representation of a byte array like in a hex editor<br>
	 * Line Format: {OFFSET} {HEXADECIMAL} {ASCII}({NEWLINE})<br>
	 * {OFFSET} = offset of the first byte in line(8 chars)<br>
	 * {HEXADECIMAL} = hexadecimal character representation({@link #_HEX_ED_BPL}*2 chars)<br>
	 * {ASCII} = ascii character presentation({@link #_HEX_ED_BPL} chars)
	 * @param data byte array to generate the hexadecimal character representation
	 * @param len the number of bytes to generate the hexadecimal character representation from
	 * @return byte array which contains the hexadecimal character representation of the given byte array
	 */
	public static CharSequence bArr2HexEdChars(byte[] data, int len)	//[JOJO]
 //	public static char[] bArr2HexEdChars(byte[] data, int len)
	{
		StringBuilder sb = new StringBuilder(len * 4);
		int ix = 0;
		while (ix < len)
		{
			sb.append(NIBBLE_CHAR_LOOKUP[ix >> 20 & 0x0F])
			  .append(NIBBLE_CHAR_LOOKUP[ix >> 16 & 0x0F])
			  .append(NIBBLE_CHAR_LOOKUP[ix >> 12 & 0x0F])
			  .append(NIBBLE_CHAR_LOOKUP[ix >>  8 & 0x0F])
			  .append(NIBBLE_CHAR_LOOKUP[ix >>  4 & 0x0F])
			  .append(NIBBLE_CHAR_LOOKUP[ix       & 0x0F])
			  .append(' ');
			
			int end = ix + 16;
			for (int col = 0; ix < end; ++ix, ++col)
			{
				if (ix < len)
				{
					int b = data[ix];
					sb.append(col == 8 ? '-' : ' ')
					  .append(NIBBLE_CHAR_LOOKUP[b >> 4 & 0x0F])
					  .append(NIBBLE_CHAR_LOOKUP[b      & 0x0F]);
				}
				else
				{
					sb.append(' ')
					  .append(' ')
					  .append(' ');
				}
			}
			ix -= 16;
			sb.append(' ')
			  .append(' ');
			for (; ix < end; ++ix)
			{
				if (ix < len)
				{
					int b = data[ix];
					sb.append(b < 0x20 ? '\uFF65' : (char)(b & 0xFF));
				}
				else
				{
					break;
				}
			}
			sb.append('\n');
		}
		return sb;
	}
}
