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

package com.l2jserver.gameserver.scripting;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.US_ASCII;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Formatter;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author JOJO
 */
public class Native2Ascii
{
	private final String _encoding;
	
	// -*- coding: XXXX -*-
	// vim:fileencoding=XXXX
	private final Pattern _patternCoding = Pattern.compile("coding[=:]\\s*[\"\']?([-\\w.]+)");
	
	private StringBuilder asciiBuffer = new StringBuilder(4096);
	
	private final FileFilter _fileFilter = new FileFilter()
	{
		@Override
		public boolean accept(File file)
		{
			String fileName = file.getName();
			char c0;
			if ((c0 = fileName.charAt(0)) == '.' || c0 == '#')
				return false;
			if (file.isDirectory())
				return true;
			return fileName.endsWith(".java") || fileName.endsWith(".py");
		}
	};
	
	public Native2Ascii(File scriptsDirectory, String encoding)
	{
		_encoding = encoding;
		run(scriptsDirectory);
	}
	
	private void run(final File scriptsDirectory)
	{
		//System.out.println("__FILE__:__LINE__: [" + scriptsDirectory.getAbsoluteFile() + "]");
		
		final File[] files = scriptsDirectory.listFiles(_fileFilter);
		if (files == null)
			return;
		
		for (File file : files)
		{
			if (file.isDirectory())
			{
				run(file);
			}
			else if (file.isFile())
			{
				//System.out.println("__FILE__:__LINE__:   " + file.getAbsoluteFile());
				String encoding = _encoding;
				try
				{
					// Step 1: native --> unicode
					char[] unicodeBuffer;
					{
						FileInputStream fis = new FileInputStream(file);
						byte[] byteBuffer = new byte[fis.available()];
						fis.read(byteBuffer);
						fis.close();
						
						if (byteBuffer.length >= 3
								&& byteBuffer[0] == (byte)0xEF
								&& byteBuffer[1] == (byte)0xBB
								&& byteBuffer[2] == (byte)0xBF)	//UTF8 BOM?
						{
							encoding = "UTF-8";
						}
						else
						{
							Matcher m = _patternCoding.matcher(new String(byteBuffer, ISO_8859_1));
							if (m.find())
								encoding = m.group(1);
						}
						
						unicodeBuffer = new String(byteBuffer, encoding).toCharArray();
					}
					
					// Step 2: unicode --> ascii
					asciiBuffer.setLength(0);
					@SuppressWarnings("resource") Formatter fm = new Formatter(asciiBuffer);
					boolean modify = false;
					int i = 0, length = unicodeBuffer.length;
					//----------------------------------------
					if (file.getPath().endsWith(".java"))
						if (unicodeBuffer[i] == '\uFEFF')	//skip BOM
						{
							++i;
							modify = true;
						}
					//----------------------------------------
					while (i < length)
					{
						char unicode = unicodeBuffer[i++];
						if (unicode >= 0x80)
						{
							fm.format("\\u%04x", (int)unicode);
							modify = true;
						}
						else
						{
							asciiBuffer.append(unicode);
						}
					}
					
					if (modify)
					{
						//----------------------------------------
						// Insert 'u' before "string" if jython
						if (file.getPath().endsWith(".py"))
							jythonInsertU(/*asciiBuffer*/);
						//----------------------------------------
						
						final String asciiString = asciiBuffer.toString();
						//System.out.println("__FILE__:__LINE__: [" + encoding1 + "] " + file.getPath());
						long mtime = file.lastModified();
						OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(file), US_ASCII);
						osw.write(asciiString);
						osw.close();
						file.setLastModified(mtime);
					}
				}
				catch (IOException e)
				{
					Logger.getLogger("Native2Ascii").warning(file.getAbsoluteFile() + " [" + encoding + "] - " + e.toString());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Insert 'u' before "string" if jython
	 */
	private void jythonInsertU(/*StringBuilder asciiBuffer*/)	// like s/".*"/u".*"/g
	{
		int length = asciiBuffer.length();
		char[] ca = new char[length];
		asciiBuffer.getChars(0, length, ca, 0);	// asciiBuffer --> ca[]
		asciiBuffer.setLength(0);
		int i = 0;
		while (i < length)
		{
			char ch = ca[i++];
			if (ch == '#')	// #comment [\r\n]
			{
				asciiBuffer.append(ch);
				while (i < length)
				{
					ch = ca[i++];
					if (ch == '\r' || ch == '\n')
					{
						--i;
						break;
					}
					asciiBuffer.append(ch);
				}
				while (i < length)
				{
					ch = ca[i++];
					if (ch != '\r' && ch != '\n')
					{
						--i;
						break;
					}
					asciiBuffer.append(ch);
				}
			}
			else if (ch == '\"' || ch == '\'')	// "string"
			{
				char quote = ch;
				int start = asciiBuffer.length();
				boolean hasUnicodeEscape = false;
				asciiBuffer.append(ch);
				while (i < length)
				{
					ch = ca[i++];
					if (ch == '\\')		// escape
					{
						if (i + 4 < length && ca[i] == 'u'
								&& isHex(ca[i + 1])
								&& isHex(ca[i + 2])
								&& isHex(ca[i + 3])
								&& isHex(ca[i + 4])
					/*	 || i + 8 < length && ca[i] == 'U'
								&& isHex(ca[i + 1])
								&& isHex(ca[i + 2])
								&& isHex(ca[i + 3])
								&& isHex(ca[i + 4])
								&& isHex(ca[i + 5])
								&& isHex(ca[i + 6])
								&& isHex(ca[i + 7])
								&& isHex(ca[i + 8])*/)
							hasUnicodeEscape = true;
						asciiBuffer.append(ch).append(ca[i++]);
					}
				/*-	else if (ch >= 0x0100)
					{
						isUnicode = true;
						asciiBuffer.append(ch);
					}	-*/
					else if (ch == quote)	// end of string
					{
						asciiBuffer.append(ch);
						if (hasUnicodeEscape && asciiBuffer.charAt(start - 1) != 'u')
							asciiBuffer.insert(start, 'u');
						//if (isUnicodeEscape) System.out.println("\t["+asciiBuffer.substring(start)+"]");
						break;
					}
					else
						asciiBuffer.append(ch);
				}
			}
			else
				asciiBuffer.append(ch);
		}
	}
	
	private boolean isHex(char c)
	{
		return c >= '0' && c <= '9' || c >= 'a' && c <= 'f' || c >= 'A' && c <= 'F';
	}
}
