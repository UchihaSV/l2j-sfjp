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
package com.l2jserver.tools.dbinstaller.util;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @author mrTJO
 */
public class FileWriterStdout extends BufferedWriter
{
	public FileWriterStdout(File file) throws IOException
	{
		super(new OutputStreamWriter(new FileOutputStream(file), UTF_8));
	}
	
	public void println() throws IOException
	{
		write("\n");
	}
	
	public void println(String line) throws IOException
	{
		write(line);
		write("\n");
	}
	
	public void print(String text) throws IOException
	{
		write(text);
	}
}
