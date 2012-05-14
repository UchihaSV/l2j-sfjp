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
package com.l2jserver.gameserver.script.faenor;

import static com.l2jserver.util.Util.dateFormat;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptContext;

import org.w3c.dom.Node;

import com.l2jserver.Config;
import com.l2jserver.gameserver.datatables.EventDroplist;
import com.l2jserver.gameserver.datatables.EventDroplist.DateDrop;
import com.l2jserver.gameserver.datatables.ItemTable;
import com.l2jserver.gameserver.model.L2DropData;
import com.l2jserver.gameserver.model.items.L2Item;
import com.l2jserver.gameserver.script.DateRange;
import com.l2jserver.gameserver.script.EventDrop;
import com.l2jserver.gameserver.script.Parser;
import com.l2jserver.gameserver.script.ParserNotCreatedException;
import com.l2jserver.gameserver.script.ScriptDocument;
import com.l2jserver.gameserver.script.ScriptEngine;
import com.l2jserver.util.file.filter.XMLFilter;

/**
 * @author Luis Arias
 */
public class FaenorScriptEngine extends ScriptEngine
{
	private static Logger _log = Logger.getLogger(FaenorScriptEngine.class.getName());
	public final static String PACKAGE_DIRECTORY = "data/faenor/";
	
	private FaenorScriptEngine()
	{
		File packDirectory = new File(Config.DATAPACK_ROOT, PACKAGE_DIRECTORY);
		
		File[] files = packDirectory.listFiles(new XMLFilter());
		
		for (File file : files)
		{
			_log.info("FaenorScriptEngine: " + file.getPath());
			try (InputStream in = new FileInputStream(file))
			{
				parseScript(new ScriptDocument(file.getName(), in), null);
			}
			catch (IOException e)
			{
				_log.log(Level.WARNING, e.getMessage(), e);
			}
		}
	}
	
	public void parseScript(ScriptDocument script, ScriptContext context)
	{
		Node node = script.getDocument().getFirstChild();
		String parserClass = "faenor.Faenor" + node.getNodeName() + "Parser";
		
		Parser parser = null;
		try
		{
			parser = createParser(parserClass);
		}
		catch (ParserNotCreatedException e)
		{
			_log.log(Level.WARNING, "ERROR: No parser registered for Script: " + parserClass + ": " + e.getMessage(), e);
		}
		
		if (parser == null)
		{
			_log.warning("Unknown Script Type: " + script.getName());
			return;
		}
		
		try
		{
			parser.parseScript(node, context);
		//	_log.info(getClass().getSimpleName() + ": Loaded  " + script.getName() + " Sucessfullty.");
		}
		catch (Exception e)
		{
			_log.log(Level.WARNING, "Script Parsing Failed: " + e.getMessage(), e);
		}
	}
	
	public static FaenorScriptEngine getInstance()
	{
		return SingletonHolder._instance;
	}
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final FaenorScriptEngine _instance = new FaenorScriptEngine();
	}
	
	public void dump()	//+[JOJO]
	{
		System.out.println("FaenorScriptEngine:");
		for (DateDrop dateDrop : EventDroplist.getInstance().getAllDrops())
		{
			EventDrop eventDrop = dateDrop.getEventDrop();
			DateRange dateRange = dateDrop.getDateRange();
			System.out.println("  Active="
					+ dateFormat(dateRange.getStartDate())
					+ "-"
					+ dateFormat(dateRange.getEndDate()));
			System.out.println("  Count=" + eventDrop.getMinCount() + "," + eventDrop.getMaxCount() + " Chance=" + ((double)eventDrop.getDropChance() * 100 / L2DropData.MAX_CHANCE) + "%");
			for (int itemId : eventDrop.getItemIdList())
			{
				L2Item i = ItemTable.getInstance().getTemplate(itemId);
				System.out.println("    Item=" + itemId + " " + (i == null ? "NULL" : i.getName()));
			}
		}
	}
}
