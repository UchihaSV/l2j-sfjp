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
package com.l2jserver.gameserver.cache;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javolution.util.FastMap;
import jp.sf.l2j.troja.FastIntObjectMap;

import com.l2jserver.Config;
import com.l2jserver.gameserver.GameTimeController;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.util.Util;
import com.l2jserver.util.file.filter.HTMLFilter;

/**
 * @author Layane
 * rewrite JOJO
 */
public class HtmCache
{
	Logger _log = Logger.getLogger(HtmCache.class.getName());
	
	private static final HTMLFilter htmlFilter = new HTMLFilter();
	
	private static final boolean TIMED_CACHE = true;
	private static final boolean CHECK_HASH_COLLISION = true;
	private static final int EXPIRE_TIME = 60;	//[min]
	final FastIntObjectMap<TimedCache> _timedCache;

	private final FastMap<String, String> _cache;
	
	int _loadedFiles;
	long _bytesBuffLen;
	
	protected HtmCache()
	{
		if (TIMED_CACHE)
		{
if (CHECK_HASH_COLLISION) {{
			checkHashCollision();
}}
			_timedCache = new FastIntObjectMap<TimedCache>().shared();
			_cache = null;
			ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new CacheScheduler(), 3600*1000, 3600*1000);
		}
		else if (Config.LAZY_CACHE)
		{
			_timedCache = null;
			_cache = new FastMap<String, String>().shared();
		}
		else
		{
			_timedCache = null;
			_cache = new FastMap<>();
		}
		reload();
	}
	
	public void reload()
	{
		reload(Config.DATAPACK_ROOT);
	}
	
	private void reload(File f)
	{
		if (getLoadedFiles() > 0)
			_log.info("Cache[HTML]: " + String.format("%.3f", getMemoryUsage()) + " megabytes on " + getLoadedFiles() + " files loaded");

		if (TIMED_CACHE)
		{
			_timedCache.clear();
			_loadedFiles = 0;
			_bytesBuffLen = 0;
			_log.info("Cache[HTML]: Running timed cache");
		}
		else if (Config.LAZY_CACHE)
		{
			_cache.clear();
			_loadedFiles = 0;
			_bytesBuffLen = 0;
			_log.info("Cache[HTML]: Running lazy cache");
		}
		else
		{
			_log.info("Html cache start...");
			parseDir(f);
			_log.info("Cache[HTML]: " + String.format("%.3f", getMemoryUsage()) + " megabytes on " + getLoadedFiles() + " files loaded");
		}
	}
	
	public void reloadPath(File f)
	{
		parseDir(f);
		_log.info("Cache[HTML]: Reloaded specified path.");
	}
	
	public double getMemoryUsage()
	{
		return ((float) _bytesBuffLen / 1048576);
	}
	
	public int getLoadedFiles()
	{
		return _loadedFiles;
	}
	
	private void parseDir(File dir)
	{
		final File[] files = dir.listFiles();
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				loadFile(file);
			}
			else
			{
				parseDir(file);
			}
		}
	}
	
	private String removeBlank(String content)
	{
		char[] ca = new char[content.length()];
		int count = 0;
		boolean modify = false;
		for (int index = 0, len = content.length(); index < len; ++index)
		{
			char ch;
			if ((ch = content.charAt(index)) == '\t' || ch == '\r' || ch == '\n' || ch == '\uFEFF')
				continue;
			ca[count++] = ch;
		}
		if (modify || count != content.length()) content = new String(ca, 0, count);
		return content;
	}
	
	public String loadFile(File file)
	{
		return loadFile(file, false);
	}
	private String loadFile(File file, boolean checked)
	{
		if (!htmlFilter.accept(file))
		{
			return null;
		}
		
		final String relpath = Util.getRelativePath(Config.DATAPACK_ROOT, file);
		String content = null;
		try (FileInputStream fis = new FileInputStream(file))
		{
			byte[] raw = new byte[fis.available()];
			
			fis.read(raw);
			content = new String(raw, UTF_8);
		/*	if (! TIMED_CACHE) */
				content = removeBlank(content);
			
			String oldContent = checked ? null : _cache_get(relpath);
			if (oldContent == null)
			{
				_bytesBuffLen += content.length() * 2;	// unicode (16 bit) = 2 bytes.
				_loadedFiles++;
			}
			else
			{
				_bytesBuffLen = _bytesBuffLen - oldContent.length() * 2 + content.length() * 2;
			}
			_cache_put(relpath, content);
		}
		catch (IOException e)
		{
			_log.log(Level.WARNING, "Problem with htm file " + e.getMessage(), e);
			e.printStackTrace();
		}
		return content;
	}
	
	public String getHtmForce(String prefix, String path)
	{
		String content = getHtm(prefix, path);
		if (content == null)
		{
			content = "<html><body>My text is missing:<br>" + path + "</body></html>";
			_log.warning("Cache[HTML]: Missing HTML page: " + path);
		}
		return content;
	}
	
	public String getHtm(String prefix, String path)
	{
		if ((prefix != null) && !prefix.isEmpty())
		{
			String newPath = prefix + path;
			String content = getHtm(newPath);
			if (content != null)
			{
				return content;
			}
			content = getHtm( path);
			if (content != null)
				_cache_put(newPath, content);
			return content;
		}
		else
			return getHtm(path);
	}
	
	private String getHtm(String path)
	{
		if ((path == null) || path.isEmpty())
		{
			return ""; // avoid possible NPE
		}
		
		String content = _cache_get(path);
		if (TIMED_CACHE)
		{
			if (content == null)
			{
				content = loadFile(new File(Config.DATAPACK_ROOT, path), true);
			}
		}
		else if (Config.LAZY_CACHE)
		{
			if (content == null)
			{
				content = loadFile(new File(Config.DATAPACK_ROOT, path), true);
			}
		}
		else // pass if static cache
		{
		}
		return content;
	}
	
 //	public boolean contains(String path)
 //	{
 //		if (TIMED_CACHE)
 //			return _timedCache.containsKey(path.hashCode());
 //		else
 //			return _cache.containsKey(path.hashCode());
 //	}
 //	
 //	/**
 //	 * @param path The path to the HTM
 //	 * @return {@code true} if the path targets a HTM or HTML file, {@code false} otherwise.
 //	 */
 //	public boolean isLoadable(String path)
 //	{
 //		return htmlFilter.accept(new File(path));
 //	}
		
	/**
	 * Timed Cache
	 * @author  JOJO
	 */
	class TimedCache
	{
		long lastAccessTime;
		String content;
	}
	
	private String _cache_get(String path)
	{
		if (TIMED_CACHE)
		{
			TimedCache item;
			if ((item = _timedCache.get(path.hashCode())) == null) return null;
			item.lastAccessTime = GameTimeController.getInstance().getGameTime();
			return item.content;
		}
		else
		{
			return _cache.get(path);
		}
	}
	
	private void _cache_put(String path, String content)
	{
		if (TIMED_CACHE)
		{
			TimedCache item = new TimedCache();
			item.lastAccessTime = GameTimeController.getInstance().getGameTime();
			item.content = content;
			_timedCache.put(path.hashCode(), item);
		}
		else
		{
			_cache.put(path, content);
		}
	}
	
	class CacheScheduler implements Runnable
	{
		@Override
		public void run()
		{
			boolean update = false;
			int cTime = GameTimeController.getInstance().getGameTime();
			final int[] keys = _timedCache.keys();
			for (int hashcode : keys)
			{
				TimedCache item = _timedCache.get(hashcode);
				if (cTime - item.lastAccessTime > EXPIRE_TIME)
				{
					--_loadedFiles;
					_bytesBuffLen -= item.content.length() * 2;
					_timedCache.remove(hashcode);
					update = true;
				}
			}
			if (update)	//TODO:DEBUG
				_log.info("Cache[HTML]: "
						+ String.format("%.3f", getMemoryUsage()) + " megabytes on " + getLoadedFiles() + " files loaded");
		}
	}
	
	private void checkHashCollision()
	{
if (CHECK_HASH_COLLISION) {{
		FastIntObjectMap<String> map = new FastIntObjectMap<>();
		checkHashCollision_parseDir(map, Config.DATAPACK_ROOT);
}}
	}
	private void checkHashCollision_parseDir(FastIntObjectMap<String> map, File dir)
	{
if (CHECK_HASH_COLLISION) {{
		for (File file : dir.listFiles())
		{
			if (file.isDirectory())
			{
				checkHashCollision_parseDir(map, file);
			}
			else
			{
				final String relpath = Util.getRelativePath(Config.DATAPACK_ROOT, file);
				final int hashcode = relpath.hashCode();
				String v = map.putIfAbsent(hashcode, relpath);
				if (v != null && !v.equals(relpath))
				{
					_log.log(Level.WARNING, "HtmCache: Hashcode collision (" + hashcode + ")");
					_log.log(Level.WARNING, "\t" + v);
					_log.log(Level.WARNING, "\t" + relpath);
				}
			}
		}
}}
	}
	
	public static HtmCache getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final HtmCache _instance = new HtmCache();
	}
}
