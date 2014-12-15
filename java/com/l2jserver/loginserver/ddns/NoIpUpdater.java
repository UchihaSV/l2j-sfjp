package com.l2jserver.loginserver.ddns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @auther JOJO
 */
public class NoIpUpdater extends Thread
{
	protected static final Logger _log = Logger.getLogger(NoIpUpdater.class.getName());
	private static final boolean DEBUG = false;
	
	private static final String TITLE = "NoIpUpdater";
	private static final boolean VERIFY = true;
	
	private String DUCUPDATE_QUERY;
	private String HOSTNAMES;
	private String USERNAME;
	private String PASSWORD;
	
	private long INTERVAL;
	private int PERIOD;	// day
	private long SLEEP;
	
	private static final String CACHE_FILE = "log/no-ip.cache";
	
	private boolean load()
	{
		Properties p = new Properties();
		try (InputStream inputStream = new FileInputStream(new File("./config/DynamicDNS.properties"))) {
			p.load(inputStream);
			
			HOSTNAMES = p.getProperty("noip.hostnames");
			USERNAME = p.getProperty("noip.username");
			PASSWORD = p.getProperty("noip.password");
			
			INTERVAL = Long.parseLong(p.getProperty("noip.interval", "600000"));
			PERIOD = Integer.parseInt(p.getProperty("noip.period", "14"));
			SLEEP = Long.parseLong(p.getProperty("noip.sleep", "45000"));
			
			if (HOSTNAMES == null || HOSTNAMES.isEmpty()
			 || USERNAME == null || USERNAME.isEmpty()
			 || PASSWORD == null || PASSWORD.isEmpty()) {
				_log.warning("<!>" + TITLE + ": " + "DynamicDNS.properties does not config");
				return false;
			}
			
			DUCUPDATE_QUERY = "http://dynupdate.noip.com/ducupdate.php"
				+ "?username=" + USERNAME
				+ "&pass=" + PASSWORD
				+ "&h[]=" + HOSTNAMES;
			return true;
		} catch (IOException e) {
			_log.log(Level.SEVERE, "<!>" + TITLE + ": " + e.getMessage(), e);
			return false;
		}
	}
	
	@Override
	public void run()
	{
		if (load())
			_log.info("no-ip updater started successfully, interval time " + INTERVAL + "ms");
		else
			return;
		
		while (!isInterrupted()) {
			update();
			if (isInterrupted()) return;
			
			// Schadule next
			try {
				Thread.sleep(INTERVAL);
			}
			catch (InterruptedException e) {
				if (DEBUG) System.out.println(TITLE + ": " + "Interrupted");
				return;
			}
		}
	}
	
	private void update()
	{
		ArrayList<String> aa = new ArrayList<>();
		try {
			//TODO: if xxx != 200 ...
			URL url = new URL(DUCUPDATE_QUERY);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
				String temp;
				while ((temp = in.readLine()) != null)
					aa.add(temp);
			}
			catch (IOException e) {
				_log.warning("<!>" + TITLE + ": " + e.getMessage());
				return;
			}
		}
		catch (MalformedURLException e) {
			_log.log(Level.WARNING, "", e);
			return;
		}
		
		boolean todo = false;
		Pattern pattern = Pattern.compile(":(\\d+)$");
		for (String e : aa) {
			Matcher m = pattern.matcher(e);
			if (!m.find()) {
				_log.warning("<!>" + TITLE + ": " + "Illegal response " + indentJoin(aa));
				return;
			}
			switch (m.group(1)) {
				case "0":
					todo = true;
					break;
				case "1":
					// Done
					break;
				case "2":
					_log.warning("<!>" + TITLE + ": " + "Hostname error " + indentJoin(aa));
					return;
				case "4":
					_log.warning("<!>" + TITLE + ": " + "Account error " + indentJoin(aa));
					return;
				case "5":
					_log.warning("<!>" + TITLE + ": " + "Busy " + indentJoin(aa));
					return;
				default:
					_log.warning("<!>" + TITLE + ": " + "Unknown error " + indentJoin(aa));
					return;
			}
		}
		pattern = null;
		aa = null;
		
		File cacheFile = new File(CACHE_FILE);
		if (todo) {
			if (cacheFile.exists()) {
				int dd = (int) ((cacheFile.lastModified() - System.currentTimeMillis()) / 86400000);
				if (dd > PERIOD) {
					if (DEBUG) System.out.println(TITLE + ": " + "Last updated " + dd + " days ago. Refresh...");
				}
				else if (dd == 0) {
					if (DEBUG) System.out.println(TITLE + ": " + "Ok.");
					return;
				} else {	// dd < PERIOD
					if (DEBUG) System.out.println(TITLE + ": " + "Last updated " + dd + " days ago. Ok.");
					return;
				}
			} else {
				if (DEBUG) System.out.println(TITLE + ": " + "Refresh...");
			}
			
			try {
				//TODO: if xxx != 200 ...
				URL url = new URL(DUCUPDATE_QUERY + "&ip=0.0.0.0");
				try {
					url.openStream();
				}
				catch (IOException e) {
					_log.warning("<!>" + TITLE + ": " + e.getMessage());
					return;
				}
			}
			catch (MalformedURLException e) {
				_log.log(Level.WARNING, "", e);
				return;
			}
			
			try {
				Thread.sleep(SLEEP);
			}
			catch (InterruptedException e) {
				if (DEBUG) System.out.println(TITLE + ": " + "Interrupted");
				return;
			}
			
			if (DEBUG) System.out.println(TITLE + ": " + "Updating...");
			try {
				//TODO: if xxx != 200 ...
				URL url = new URL(DUCUPDATE_QUERY);
				try {
					url.openStream();
				}
				catch (IOException e) {
					_log.warning("<!>" + TITLE + ": " + e.getMessage());
					return;
				}
			}
			catch (MalformedURLException e) {
				_log.log(Level.WARNING, "", e);
				return;
			}
			if (DEBUG) System.out.println(TITLE + ": " + "Done.");
		}
		
		// touch CACHE_FILE
		if (!cacheFile.exists()) {
			try {
				cacheFile.createNewFile();
			}
			catch (IOException e) {
				_log.log(Level.SEVERE, "", e);
			}
		} else {
			cacheFile.setLastModified(System.currentTimeMillis());
		}
		cacheFile = null;
		
		if (VERIFY) {
			// Verify ip address
			String ip = null;
			try {
				//TODO: if xxx != 200 ...
				URL url = new URL("http://ip1.dynupdate.no-ip.com:8245/");
				try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
					while ((ip = in.readLine()) != null)
						break;
				}
				catch (IOException e) {
					_log.warning("<!>" + TITLE + ": " + e.getMessage());
					return;
				}
			}
			catch (MalformedURLException e) {
				_log.log(Level.WARNING, "", e);
				return;
			}
			_log.info(TITLE + ": " + "New IP " + ip);
		}
	}
	private String indentJoin(Iterable<String> a)
	{
		return join("\n\t", a);
	}
	private String join(CharSequence separator, Iterable<String> a)
	{
		//	return String.join(separator, a);	// if java8
		StringBuilder sb = new StringBuilder();
		for (String s : a) {
			if (sb.length() > 0)
				sb.append(separator);
			sb.append(s);
		}
		return sb.toString();
	}
	
}