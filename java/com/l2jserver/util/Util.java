/*
 * Copyright (C) 2004-2014 L2J Server
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
package com.l2jserver.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Useful utilities common to L2J Server.
 */
public class Util
{
	private static final Logger _log = Logger.getLogger(Util.class.getName());
	
	private static final char[] ILLEGAL_CHARACTERS =
	{
		'/',
		'\n',
		'\r',
		'\t',
		'\0',
		'\f',
		'`',
		'?',
		'*',
		'\\',
		'<',
		'>',
		'|',
		'\"',
		':'
	};
	
	/**
	 * Checks if a host name is internal
	 * @param host the host name to check
	 * @return true: host name is internal<br>
	 *         false: host name is external
	 */
	public static boolean isInternalHostname(String host)
	{
		try
		{
			InetAddress addr = InetAddress.getByName(host);
			return addr.isSiteLocalAddress() || addr.isLoopbackAddress();
		}
		catch (UnknownHostException e)
		{
			_log.warning("Util: " + e.getMessage());
		}
		return false;
	}
	
	/**
	 * Method to generate the hexadecimal representation of a byte array.<br>
	 * 16 bytes per row, while ascii chars or "." is shown at the end of the line.
	 * @param data the byte array to be represented in hexadecimal representation
	 * @param len the number of bytes to represent in hexadecimal representation
	 * @return byte array represented in hexadecimal format
	 */
	public static String printData(byte[] data, int len)
	{
		return HexUtils.bArr2HexEdChars(data, len).toString();	//[JOJO]
	//	return new String(HexUtils.bArr2HexEdChars(data, len));
	}
	
	/**
	 * This call is equivalent to Util.printData(data, data.length)
	 * @see Util#printData(byte[],int)
	 * @param data data to represent in hexadecimal
	 * @return byte array represented in hexadecimal format
	 */
	public static String printData(byte[] data)
	{
		return printData(data, data.length);
	}
	
	/**
	 * Method to represent the remaining bytes of a ByteBuffer as hexadecimal
	 * @param buf ByteBuffer to represent the remaining bytes of as hexadecimal
	 * @return hexadecimal representation of remaining bytes of the ByteBuffer
	 */
	public static String printData(ByteBuffer buf)
	{
		byte[] data = new byte[buf.remaining()];
		buf.get(data);
		String hex = Util.printData(data, data.length);
		buf.position(buf.position() - data.length);
		return hex;
	}
	
	/**
	 * Method to generate a random sequence of bytes returned as byte array
	 * @param size number of random bytes to generate
	 * @return byte array with sequence of random bytes
	 */
	public static byte[] generateHex(int size)
	{
		byte[] array = new byte[size];
		Rnd.nextBytes(array);
		return array;
	}
	
	/**
	 * Method to get the stack trace of a Throwable into a String
	 * @param t Throwable to get the stacktrace from
	 * @return stack trace from Throwable as String
	 */
	//[JOJO]-------------------------------------------------
	/**
	 * 
	 */
	private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm (E)";
	public static String dateFormat()
	{
		return new java.text.SimpleDateFormat(DATE_FORMAT).format(new java.util.Date());
	}
	public static String dateFormat(long d)
	{
		return new java.text.SimpleDateFormat(DATE_FORMAT).format(new java.util.Date(d));
	}
	public static String dateFormat(java.util.Date d)
	{
		return new java.text.SimpleDateFormat(DATE_FORMAT).format(d);
	}
	public static String dateFormat(java.util.Calendar d)
	{
		return new java.text.SimpleDateFormat(DATE_FORMAT).format(d.getTime());
	}
	public static String strTimeLeft(int time)
	{
		if (time % 86400 == 0) return (time / 86400) + "����";
		if (time %  3600 == 0) return (time /  3600) + "����";
		if (time %    60 == 0) return (time /    60) + "����";
		return time + "�b��";
	}
	public static String strTime(int time)
	{
		if (time == 0) return "0�b";
		StringBuilder sb = new StringBuilder();
		long n;
		if ((n = time / 86400) > 0) { sb.append(n).append('��');   time %= 86400; }
		if ((n = time /  3600) > 0) { sb.append(n).append("����"); time %=  3600; }
		if ((n = time /    60) > 0) { sb.append(n).append('��');   time %=    60; }
		if ((n = time        ) > 0) { sb.append(n).append('�b');                  }
		return sb.toString();
	}
	public static String strMillTime(long milliTime)
	{
		if (milliTime == 0) return "0�b";
		if (milliTime <= 999) return milliTime + "�_�b";
		return strTime((int)((milliTime + 999) / 1000)); // �b�����͐؂�グ
	}
	
	/**
	 * 
	 */
	public static BufferedReader utf8BufferedReader(String name) throws FileNotFoundException
	{
		return skipBOM(new BufferedReader(new InputStreamReader(new FileInputStream(name), UTF_8)));
	}
	public static BufferedReader utf8BufferedReader(File file) throws FileNotFoundException
	{
		return skipBOM(new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8)));
	}
	public static BufferedWriter utf8BufferedWriter(String name) throws FileNotFoundException
	{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), UTF_8));
	}
	public static BufferedWriter utf8BufferedWriter(String name, boolean append) throws FileNotFoundException
	{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name, append), UTF_8));
	}
	public static BufferedWriter utf8BufferedWriter(File file) throws FileNotFoundException
	{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), UTF_8));
	}
	public static BufferedWriter utf8BufferedWriter(File file, boolean append) throws FileNotFoundException
	{
		return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), UTF_8));
	}
	private static BufferedReader skipBOM(BufferedReader reader)
	{
		try { reader.mark(1); if (reader.read() != '\uFEFF') reader.reset(); }
		catch (IOException e) { }
		return reader;
	}
	//-------------------------------------------------------
	
	public static String getStackTrace(Throwable t)
	{
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	/**
	 * Replaces most invalid characters for the given string with an underscore.
	 * @param str the string that may contain invalid characters
	 * @return the string with invalid character replaced by underscores
	 */
	public static String replaceIllegalCharacters(String str)
	{
		String valid = str;
		for (char c : ILLEGAL_CHARACTERS)
		{
			valid = valid.replace(c, '_');
		}
		return valid;
	}
	
	/**
	 * Verify if a file name is valid.
	 * @param name the name of the file
	 * @return {@code true} if the file name is valid, {@code false} otherwise
	 */
	public static boolean isValidFileName(String name)
	{
		final File f = new File(name);
		try
		{
			f.getCanonicalPath();
			return true;
		}
		catch (IOException e)
		{
			return false;
		}
	}
	
	//[JOJO]-------------------------------------------------
	public static CharSequence concat_ws(String separator, String a, String b)
	{
		if (a != null && a.length() > 0) {
			if (b != null && b.length() > 0)
				return a + separator + b;
			else
				return a;
		}
		if (b != null) {
			return b;
		}
		return "";
	}
	public static CharSequence concat_ws(String separator, String[] strings)
	{
		StringBuilder result = new StringBuilder();
		for (String s : strings)
		{
			if (s != null && !s.isEmpty()) {
				if (result.length() > 0) result.append(separator);
				result.append(s);
			}
		}
		return result;
	}
	public static CharSequence concat_ws(String separator, Collection<String> strings)
	{
		StringBuilder result = new StringBuilder();
		for (String s : strings)
		{
			if (s != null && !s.isEmpty()) {
				if (result.length() > 0) result.append(separator);
				result.append(s);
			}
		}
		return result;
	}
	//-------------------------------------------------------
}
