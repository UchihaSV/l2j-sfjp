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
package com.l2jserver.util.network;

import static java.nio.charset.StandardCharsets.UTF_16LE;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * This class ...
 * @version $Revision: 1.2.4.1 $ $Date: 2005/03/27 15:30:11 $
 */
public abstract class BaseSendablePacket
{
	private static final Logger _log = Logger.getLogger(BaseSendablePacket.class.getName());
	
	private final ByteArrayOutputStream _bao;
	
	protected BaseSendablePacket()
	{
		_bao = new ByteArrayOutputStream();
	}
	
	protected void writeD(int value)
	{
		_bao.write(value);
		_bao.write(value >>> 8);
		_bao.write(value >>> 16);
		_bao.write(value >>> 24);
	}
	
	protected void writeH(int value)
	{
		_bao.write(value);
		_bao.write(value >>> 8);
	}
	
	protected void writeC(int value)
	{
		_bao.write(value);
	}
	
	protected void writeF(double org)
	{
		long value = Double.doubleToRawLongBits(org);
		_bao.write((int) (value));
		_bao.write((int) (value >>> 8));
		_bao.write((int) (value >>> 16));
		_bao.write((int) (value >>> 24));
		_bao.write((int) (value >>> 32));
		_bao.write((int) (value >>> 40));
		_bao.write((int) (value >>> 48));
		_bao.write((int) (value >>> 56));
	}
	
	protected void writeS(String text)
	{
		try
		{
			if (text != null)
			{
				_bao.write(text.getBytes(UTF_16LE));
			}
		}
		catch (IOException e)
		{
			_log.warning(getClass().getSimpleName() + ": " + e.getMessage());
		}
		
		_bao.write(0);
		_bao.write(0);
	}
	
	protected void writeB(byte[] array)
	{
		try
		{
			_bao.write(array);
		}
		catch (IOException e)
		{
			_log.warning(getClass().getSimpleName() + ": " + e.getMessage());
		}
	}
	
	protected void writeQ(long value)
	{
		_bao.write((int) (value));
		_bao.write((int) (value >>> 8));
		_bao.write((int) (value >>> 16));
		_bao.write((int) (value >>> 24));
		_bao.write((int) (value >>> 32));
		_bao.write((int) (value >>> 40));
		_bao.write((int) (value >>> 48));
		_bao.write((int) (value >>> 56));
	}
	
	public int getLength()
	{
		return _bao.size() + 2;
	}
	
	public byte[] getBytes()
	{
		// if (this instanceof Init)
		// writeD(0x00); //reserve for XOR initial key
		
		writeD(0x00); // reserve for checksum
		
		int padding = _bao.size() % 8;
		if (padding != 0)
		{
			for (; padding < 8; ++padding)
				writeC(0x00);
		}
		
		return _bao.toByteArray();
	}
	
	public abstract byte[] getContent() throws IOException;
}
