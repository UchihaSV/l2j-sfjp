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
package com.l2jserver.tools.dbinstaller.console;

import java.sql.Connection;
import java.util.Scanner;
import java.util.prefs.Preferences;

import com.l2jserver.tools.dbinstaller.DBOutputInterface;
import com.l2jserver.tools.dbinstaller.RunTasks;
import com.l2jserver.tools.dbinstaller.util.mysql.MySqlConnect;

/**
 * @author mrTJO
 */
public class DBInstallerConsole implements DBOutputInterface
{
	Connection _con;
	
	public DBInstallerConsole(String db, String dir, String cleanUp)
	{
		String host = "localhost";
		String log = "dbinst_" + host + "_" + db + ".log";
		System.out.println("Welcome to L2J DataBase installer");
		Preferences prop = Preferences.userRoot();
		RunTasks rt = null;
		@SuppressWarnings("resource") Scanner scn = new Scanner(System.in);
	//	try (Scanner scn = new Scanner(new CloseShieldedInputStream(System.in)))
		{
			while (_con == null)
			{
				System.out.printf("%s (%s): ", "Host", prop.get("dbHost_" + db, "localhost"));
				String dbHost = scn.nextLine();
				System.out.printf("%s (%s): ", "Port", prop.get("dbPort_" + db, "3306"));
				String dbPort = scn.nextLine();
				System.out.printf("%s (%s): ", "Username", prop.get("dbUser_" + db, "root"));
				String dbUser = scn.nextLine();
				System.out.printf("%s (%s): ", "Password", "");
				String dbPass = scn.nextLine();
				System.out.printf("%s (%s): ", "Database", prop.get("dbDbse_" + db, db));
				String dbDbse = scn.nextLine();
				
				if (dbHost.isEmpty()) dbHost = prop.get("dbHost_" + db, "localhost");
				if (dbPort.isEmpty()) dbPort = prop.get("dbPort_" + db, "3306");
				if (dbUser.isEmpty()) dbUser = prop.get("dbUser_" + db, "root");
				if (dbDbse.isEmpty()) dbDbse = prop.get("dbDbse_" + db, db);
				
				MySqlConnect connector = new MySqlConnect(dbHost, dbPort, dbUser, dbPass, dbDbse, true);
				host = dbHost;
				log = "dbinst_" + host.replaceAll("[^0-9a-zA-Z.]+", "-") + "_" + dbDbse + ".log";
				
				_con = connector.getConnection();
			}
			
			System.out.print("(C)lean install, (U)pdate or (E)xit? ");
			String resp = scn.next();
			if (resp.equalsIgnoreCase("c"))
			{
				System.out.print("Do you really want to destroy your db (Y/N)?");
				if (scn.next().equalsIgnoreCase("y"))
				{
					rt = new RunTasks(this, host, db, dir, cleanUp, true, log);
				}
			}
			else if (resp.equalsIgnoreCase("u"))
			{
				rt = new RunTasks(this, host, db, dir, cleanUp, false, log);
			}
		}
		
		if (rt != null)
		{
			rt.run();
		}
		else
		{
			System.exit(0);
		}
	}
	
	@Override
	public void appendToProgressArea(String text)
	{
		System.out.println(text);
	}
	
	@Override
	public Connection getConnection()
	{
		return _con;
	}
	
	@Override
	public void setProgressIndeterminate(boolean value)
	{
	}
	
	@Override
	public void setProgressMaximum(int maxValue)
	{
	}
	
	@Override
	public void setProgressValue(int value)
	{
	}
	
	@Override
	public void setFrameVisible(boolean value)
	{
	}
	
	@Override
	public int requestConfirm(String title, String message, int type)
	{
		System.out.print(message);
		String res = "";
		@SuppressWarnings("resource") Scanner scn = new Scanner(System.in);
	//	try (Scanner scn = new Scanner(new CloseShieldedInputStream(System.in)))
		{
			res = scn.next();
		}
		return res.equalsIgnoreCase("y") ? 0 : 1;
	}
	
	@Override
	public void showMessage(String title, String message, int type)
	{
		System.out.println(message);
	}
}
