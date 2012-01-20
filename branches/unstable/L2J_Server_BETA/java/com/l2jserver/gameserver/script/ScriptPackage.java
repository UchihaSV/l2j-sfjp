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
package com.l2jserver.gameserver.script;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javolution.util.FastList;

/**
 * @author Luis Arias
 */
public class ScriptPackage
{
	private static final Logger _log = Logger.getLogger(ScriptPackage.class.getName());
	
	private List<ScriptDocument> _scriptFiles;
	
	public ScriptPackage(ZipFile pack)
	{
		_scriptFiles = new FastList<ScriptDocument>();
		addFiles(pack);
	}
	
	/**
	 * @param pack 
	 */
	private void addFiles(ZipFile pack)
	{
		String xml = pack.getName().replaceFirst("\\.zip$", ".xml");
		if (new File(xml).exists())
		{
			try
			{
				InputStream in = new FileInputStream(xml);
				ScriptDocument newScript = new ScriptDocument(xml, in);
				_scriptFiles.add(newScript);
				in.close();
				System.out.println("***" + xml + " Çì«Ç›çûÇ›Ç‹ÇµÇΩÅB");
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return;
		}
		
		for (Enumeration<? extends ZipEntry> e = pack.entries(); e.hasMoreElements();)
		{
			ZipEntry entry = e.nextElement();
			if (entry.getName().endsWith(".xml"))
			{
				try
				{
					ScriptDocument newScript = new ScriptDocument(entry.getName(), pack.getInputStream(entry));
					_scriptFiles.add(newScript);
				}
				catch (IOException io)
				{
					_log.warning(getClass().getSimpleName() + ": " + io.getMessage());
				}
			}
		}
	}
}
