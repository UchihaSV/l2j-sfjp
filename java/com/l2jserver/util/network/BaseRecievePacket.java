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
package com.l2jserver.util.network;

import static java.nio.charset.StandardCharsets.UTF_16LE;

/**
 * This class ...
 * @version $Revision: 1.2.4.1 $ $Date: 2005/03/27 15:30:12 $
 */
public abstract class BaseRecievePacket
{
	private final byte[] _decrypt;
	private int _off;
	
	public BaseRecievePacket(byte[] decrypt)
	{
		_decrypt = decrypt;
		_off = 1; // skip packet type id
	}
	
	public int readD()
	{
		return (_decrypt[_off++] & 0xFF)
		     | (_decrypt[_off++] & 0xFF) << 8
		     | (_decrypt[_off++] & 0xFF) << 16
		     | (_decrypt[_off++] & 0xFF) << 24;
	}
	
	public int readC()
	{
		return _decrypt[_off++] & 0xFF;
	}
	
	public int readH()
	{
		return (_decrypt[_off++] & 0xFF)
		     | (_decrypt[_off++] & 0xFF) << 8;
	}
	
	public double readF()
	{
		int off = _off; _off += 8;
		return Double.longBitsToDouble(
			        (_decrypt[off++] & 0xFF)
			|       (_decrypt[off++] & 0xFF) << 8
			|       (_decrypt[off++] & 0xFF) << 16
			|       (_decrypt[off++] & 0xFF) << 24
			| (long)(_decrypt[off++] & 0xFF) << 32
			| (long)(_decrypt[off++] & 0xFF) << 40
			| (long)(_decrypt[off++] & 0xFF) << 48
			| (long)(_decrypt[off++] & 0xFF) << 56 );
	}
	
	public String readS()
	{
		for (int off = _off, length = _decrypt.length; off < length; off += 2)
		{
			if (_decrypt[off] == 0 && _decrypt[off + 1] == 0)
			{
				String result = new String(_decrypt, _off, off - _off, UTF_16LE);
				_off = off + 2;
				return result;
			}
		}
		return null;
	}
	
	public final byte[] readB(int length)
	{
		byte[] result = new byte[length];
		System.arraycopy(_decrypt, _off, result, 0, length);
		_off += length;
		return result;
	}
	
	public long readQ()
	{
		int off = _off; _off += 8;
		return    (_decrypt[off++] & 0xFF)
		  |       (_decrypt[off++] & 0xFF) << 8
		  |       (_decrypt[off++] & 0xFF) << 16
		  |       (_decrypt[off++] & 0xFF) << 24
		  | (long)(_decrypt[off++] & 0xFF) << 32
		  | (long)(_decrypt[off++] & 0xFF) << 40
		  | (long)(_decrypt[off++] & 0xFF) << 48
		  | (long)(_decrypt[off++] & 0xFF) << 56;
	}
}
