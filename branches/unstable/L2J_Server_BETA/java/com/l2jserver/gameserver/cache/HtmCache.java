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
package com.l2jserver.gameserver.cache;

import gnu.trove.map.hash.TIntObjectHashMap;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.gameserver.GameTimeController;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.util.L2TIntObjectHashMap;
import com.l2jserver.gameserver.util.Util;

/**
 * @author Layane
 * rewrite JOJO
 */
public class HtmCache
{
	/*private*/ static Logger _log = Logger.getLogger(HtmCache.class.getName());
	private static HtmFilter/*FileFilter*/ filter = /*getInstance().*/new HtmFilter();	//[JOJO]
	
	static final boolean TIMED_CACHE = true;
	private static final int EXPIRE_TIME = 60;	//[min]
	private final L2TIntObjectHashMap<TimedCache> _timedCache;

	private final TIntObjectHashMap<String> _cache;
	
	private int _loadedFiles;
	private long _bytesBuffLen;
	
	public static HtmCache getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private HtmCache()
	{
		if (TIMED_CACHE) {
			_timedCache = new L2TIntObjectHashMap<TimedCache>();
			_cache = null;
			ThreadPoolManager.getInstance().scheduleAiAtFixedRate(new CacheScheduler(), 3600*1000, 3600*1000);
		} else if (Config.LAZY_CACHE) {
			_timedCache = null;
			_cache = new L2TIntObjectHashMap<String>();
		} else {
			_timedCache = null;
			_cache = new TIntObjectHashMap<String>();
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
	
	private static class HtmFilter implements FileFilter
	{
		public boolean accept(File file)
		{
			if (!file.isDirectory())
			{
				return (file.getName().endsWith(".htm") || file.getName().endsWith(".html"));
			}
			return true;
		}
	}
	
	private void parseDir(File dir)
	{
	//	FileFilter filter = new HtmFilter();
		File[] files = dir.listFiles(filter);
		
		for (File file : files)
		{
			if (!file.isDirectory())
				loadFile(file);
			else
				parseDir(file);
		}
	}
	
	public String loadFile(File file)
	{
		return loadFile(file, false);
	}
	private String loadFile(File file, boolean checked)
	{
		final String relpath = Util.getRelativePath(Config.DATAPACK_ROOT, file);
		final int hashcode = relpath.hashCode();
		
	//	final HtmFilter filter = new HtmFilter();
		
		if (file.exists() && filter.accept(file) && !file.isDirectory())
		{
			String content;
			FileInputStream fis = null;
			
			try
			{
				fis = new FileInputStream(file);
				byte[] raw = new byte[fis.available()];
				
				fis.read(raw);
				content = new String(raw, "UTF-8");
			/*	if (! TIMED_CACHE) */
					content = content.replaceAll("[\uFEFF\r\n]", "");
				
				String oldContent = checked ? null : _cache_get(hashcode);
				
				if (oldContent == null)
				{
					_bytesBuffLen += content.length() * 2;	// unicode (16 bit) = 2 bytes.
					_loadedFiles++;
				}
				else
				{
					_bytesBuffLen = _bytesBuffLen - oldContent.length() * 2 + content.length() * 2;
				}
				
				_cache_put(hashcode, content);
				
				return content;
			}
			catch (Exception e)
			{
				_log.log(Level.WARNING, "Problem with htm file " + e.getMessage(), e);
				e.printStackTrace();
			}
			finally
			{
				try
				{
					fis.close();
				}
				catch (Exception e1)
				{
				}
			}
		}
		
		return null;
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
		if (prefix != null && !prefix.isEmpty())
		{
			String newPath = prefix + path;
			String content = getHtm(newPath);
			if (content != null)
				return content;
			content = getHtm( path);
			if (content != null)
				_cache_put(newPath.hashCode(), content);
			return content;
		}
		else
			return getHtm(path);
	}
	
	private String getHtm(String path)
	{
		if (path == null || path.isEmpty())
			return ""; // avoid possible NPE
		
		final int hashCode = path.hashCode();
		if (TIMED_CACHE)
		{
			String content = _cache_get(hashCode);
			if (content == null)
				content = loadFile(new File(Config.DATAPACK_ROOT, path), true);
			return content;
		}
		else if (Config.LAZY_CACHE)
		{
			String content = _cache.get(hashCode);
			if (content == null)
				content = loadFile(new File(Config.DATAPACK_ROOT, path), true);
			return content;
		}
		else
		{
			return _cache.get(hashCode);
		}
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
 //	 * Check if an HTM exists and can be loaded
 //	 * @param
 //	 * path The path to the HTM
 //	 * */
 //	public boolean isLoadable(String path)
 //	{
 //		File file = new File(path);
 //		HtmFilter filter = new HtmFilter();
 //
 //		if (file.exists() && filter.accept(file) && !file.isDirectory())
 //			return true;
 //		
 //		return false;
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
	
	private String _cache_get(int hashcode)
	{
		if (TIMED_CACHE)
		{
			TimedCache item;
			if ((item = _timedCache.get(hashcode)) == null) return null;
			item.lastAccessTime = GameTimeController.getInstance().getGameTime();
			return item.content;
		}
		else
		{
			return _cache.get(hashcode);
		}
	}
	
	private void _cache_put(int hashcode, String content)
	{
		if (TIMED_CACHE)
		{
			TimedCache item = new TimedCache();
			item.lastAccessTime = GameTimeController.getInstance().getGameTime();
			item.content = content;
			_timedCache.put(hashcode, item);
		}
		else
		{
			_cache.put(hashcode, content);
		}
	}
	
	class CacheScheduler implements Runnable
	{
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
	
	@SuppressWarnings("synthetic-access")
	private static class SingletonHolder
	{
		protected static final HtmCache _instance = new HtmCache();
	}
}
