//@formatter:off
package com.l2jserver.gameserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.l2jserver.Config;
import com.l2jserver.Config.IPConfigData;

/*
 * @auther JOJO
 */
public class CheckDynamicIpAddressTask implements Runnable
{
	private static final Logger _log = Logger.getLogger(CheckDynamicIpAddressTask.class.getName());
	
	// Configuration
	static final long CHECK_DYNAMIC_IP_ADDRESS_INITIAL = 1800_000L;
	static final long CHECK_DYNAMIC_IP_ADDRESS_DELAY = 900_000L;
	private static String[] WHAT_IS_MY_IP_URL = {
	//	"http://api.externalip.net/ip/",	// "Website suspended. This website has been suspended as of March 1st 2014."
		"http://bot.whatismyipaddress.com",
		"http://checkip.dyndns.com/",
		"http://checkip.dyndns.org/",
		"http://checkmyip.com/",
		"http://ip1.dynupdate.no-ip.com:8245/",
		"http://ipaddress.com/",
		"http://ipecho.net/plain",
		"http://myip.dnsomatic.com",
		"http://myip.is/",
		"http://myip.nt0.biz/",
		"http://taruo.net/ip/?",
		"http://whatismyipaddress.com/ip-lookup",
		"http://whatsmyip.net/",
		"http://www.akakagemaru.info/cgi-bin/index.cgi",
		"http://www.canyouseeme.org/",
		"http://www.cybersyndrome.net/evc.html",
		"http://www.findmyipaddress.com/",
		"http://www.howtofindmyipaddress.com/",
		"http://www.ipchicken.com/",
		"http://www.iprivacytools.com/my-ip-address/",
		"http://www.my-ip-address.net/",
		"http://www.show-ip-addr.com/en/",
		"http://www.showmyip.gr/",
		"http://www.tracemyip.org/",
		"http://www.whereisip.net/",
	//	"http://www.ugtop.com/spill.shtml",
	};
	
	public static boolean isAutoIpConfig;
	private static int _index = -1;
	private static ScheduledFuture<?> _checkDynamicIpAddressTask;
	
	@Override
	public void run()
	{
if (com.l2jserver.Config.CHECK_DYNAMIC_IP_ADDRESS_TASK) {{
		if (LoginServerThread.getInstance().getServerName() == null)
			return;
		
		String ip = whatIsMyIp();
		if (ip != null) {
			if (ip.equals(IPConfigData.externalIp)) {
				_log.fine("Network Config: Current external IP address is '" + ip + "'");
			}
			else CHANGE: {
				_log.warning("Network Config: External IP address was changed '" + ip + "' from '" + IPConfigData.externalIp + "'. Trying to reconnect the Login Server.");
				List<String> hosts = Config.GAME_SERVER_HOSTS;
				List<String> subnets = Config.GAME_SERVER_SUBNETS;
				for (int i = hosts.size(); --i >= 0;) {
					if (hosts.get(i).equals(IPConfigData.externalIp) && subnets.get(i).equals("0.0.0.0/0")) {
						hosts.set(i, ip);
						IPConfigData.externalIp = ip;
						System.out.println(toIpConfigXml());
						LoginServerThread.getInstance().disconnect();
						break CHANGE;
					}
				}
			}
		}
}}
	}
	
	private String whatIsMyIp()
	{
if (com.l2jserver.Config.CHECK_DYNAMIC_IP_ADDRESS_TASK) {{
		_index = (_index + 1) % WHAT_IS_MY_IP_URL.length;
		String url = WHAT_IS_MY_IP_URL[_index];
		_log.info("Network Config: Check external IP address from '" + url + "'");
		try {
			URL autoIp = new URL(url);
			try (BufferedReader in = new BufferedReader(new InputStreamReader(autoIp.openStream()))) {
				final Pattern pattern = Pattern.compile("(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)");
				String temp;
				while ((temp = in.readLine()) != null) {
					Matcher match = pattern.matcher(temp);
					while (match.find())
						return match.group();
				}
			}
			catch (IOException e) {
				_log.warning("<!>Network Config: " + e.getMessage());
			}
		}
		catch (MalformedURLException e) {
			_log.log(Level.WARNING, "", e);
		}
}}
		return null;
	}
	
	public static String toIpConfigXml()
	{
		final String T = "\t|";	// indent
		StringBuilder a = new StringBuilder(), b = new StringBuilder();
		List<String> hosts = Config.GAME_SERVER_HOSTS;
		List<String> subnets = Config.GAME_SERVER_SUBNETS;
		for (int i = 0; i < hosts.size(); ++i) {
			String subnet = subnets.get(i), host = hosts.get(i);
			if (subnet.equals("0.0.0.0/0"))
				a.append(T + "<gameserver address=\"").append(host).append("\">\n");
			else
				b.append(T + "\t<define subnet=\"").append(subnet).append(" address=\"").append(host).append("\" />\n");
		}
		return a.append(b).append(T + "</gameserver>").toString();
	}
	
	public static void start()
	{
if (com.l2jserver.Config.CHECK_DYNAMIC_IP_ADDRESS_TASK) {{
		if (isAutoIpConfig)
			if (_checkDynamicIpAddressTask == null)
				_checkDynamicIpAddressTask = ThreadPoolManager.getInstance().scheduleGeneralWithFixedDelay(
					new CheckDynamicIpAddressTask()
					, CHECK_DYNAMIC_IP_ADDRESS_INITIAL
					, CHECK_DYNAMIC_IP_ADDRESS_DELAY);
}}
	}
}
