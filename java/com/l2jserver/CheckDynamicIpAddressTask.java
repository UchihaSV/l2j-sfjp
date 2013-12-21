package com.l2jserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.l2jserver.Config.IPConfigData;
import com.l2jserver.gameserver.GameServer;
import com.l2jserver.gameserver.LoginServerThread;
import com.l2jserver.gameserver.ThreadPoolManager;

/*
 * @auther JOJO
 */
class CheckDynamicIpAddressTask implements Runnable
{
	private static final Logger _log = Logger.getLogger(CheckDynamicIpAddressTask.class.getName());
	
	// Configuration
	static final boolean CHECK_DYNAMIC_IP = true;	//[JOJO]IPアドレスを定期的に検査して変化していればログインサーバに接続しなおす.
	static final long CHECK_DYNAMIC_IP_ADDRESS_INITIAL = 1800_000L;
	static final long CHECK_DYNAMIC_IP_ADDRESS_DELAY = 900_000L;
	private static String[] WHAT_IS_MY_IP_URL = {
		"http://api.externalip.net/ip/",
		"http://bot.whatismyipaddress.com",
		"http://ipecho.net/plain",
		"http://myip.dnsomatic.com",
		"http://taruo.net/ip/?",
		"http://whatsmyip.net/",
		"http://www.cybersyndrome.net/evc.html",
		"http://www.ip-adress.com/",
		"http://www.myipaddress.com/show-my-ip-address/",
		"http://www.tracemyip.org/",
	//	"http://www.ugtop.com/spill.shtml",
	};
	
	private static int _index = -1;
	private static ScheduledFuture<?> _checkDynamicIpAddressTask;
	
	@Override
	public void run()
	{
		if (CHECK_DYNAMIC_IP) {
			if (GameServer.gameServer.serverLoadEnd == 0)
				return;
			
			String ip = whatIsMyIp();
			if (ip != null) {
				if (ip.equals(IPConfigData.externalIp)) {
					_log.fine("Network Config: Current external IP address is '" + ip + "'");
				}
				else {
					_log.warning("Network Config: External IP address was changed '" + ip + "' from '" + IPConfigData.externalIp + "'. Trying to reconnect the Login Server.");
					List<String> hosts = Config.GAME_SERVER_HOSTS;
					List<String> subnets = Config.GAME_SERVER_SUBNETS;
					for (int i = hosts.size(); --i >= 0;) {
						if (hosts.get(i).equals(IPConfigData.externalIp) && subnets.get(i).equals("0.0.0.0/0")) {
							hosts.set(i, ip);
							IPConfigData.externalIp = ip;
							System.out.println(toIpConfigXml());
							LoginServerThread.getInstance().disconnect();
							break;
						}
					}
				}
			}
		}
	}
	
	private String whatIsMyIp()
	{
		if (CHECK_DYNAMIC_IP) {
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
			}
			catch (IOException e)
			{
				_log.log(Level.WARNING, url, e);
			}
		}
		return null;
	}
	
	public static String toIpConfigXml()
	{
		StringBuilder a = new StringBuilder(), b = new StringBuilder();
		List<String> hosts = Config.GAME_SERVER_HOSTS;
		List<String> subnets = Config.GAME_SERVER_SUBNETS;
		for (int i = 0; i < hosts.size(); ++i) {
			String subnet = subnets.get(i), host = hosts.get(i);
			if (subnet.equals("0.0.0.0/0"))
				a.append("<gameserver address=\"").append(host).append("\">\n");
			else
				b.append("\t<define subnet=\"").append(subnet).append(" address=\"").append(host).append("\" />\n");
		}
		return a.append(b).append("</gameserver>").toString();
	}
	
	public static void start()
	{
		if (CHECK_DYNAMIC_IP) {
			if (_checkDynamicIpAddressTask == null)
				_checkDynamicIpAddressTask = ThreadPoolManager.getInstance().scheduleGeneralWithFixedDelay(
					new CheckDynamicIpAddressTask()
					, CHECK_DYNAMIC_IP_ADDRESS_INITIAL
					, CHECK_DYNAMIC_IP_ADDRESS_DELAY);
		}
	}
}
