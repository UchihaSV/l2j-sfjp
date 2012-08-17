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
package com.l2jserver.tools.dbinstaller.util.mysql;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.GregorianCalendar;

import com.l2jserver.tools.dbinstaller.DBOutputInterface;
import com.l2jserver.tools.dbinstaller.util.FileWriterStdout;

/**
 * @author mrTJO
 */
public class DBDumper
{
	DBOutputInterface _frame;
	String _host;
	String _db;
	
	public DBDumper(DBOutputInterface frame, String host, String db)
	{
		_frame = frame;
		_host = host;
		_db = db;
		createDump();
	}
	
	public void createDump()
	{
		try (Formatter form = new Formatter())
		{
			try (Connection con = _frame.getConnection())
			{
				String serverVersion = "Unknown";
				try (Statement s = con.createStatement();
					ResultSet rset = s.executeQuery("SELECT VERSION()"))
				{
					if (rset.next())
						serverVersion = rset.getString(1);
				}
				catch (Exception e) {/*ignore error*/}
				
				try (Statement s = con.createStatement();
					ResultSet rset = s.executeQuery("SHOW TABLES"))
				{
					File dump = new File("dumps", form.format("%1$s_dump_%2$tY%2$tm%2$td-%2$tH%2$tM%2$tS.sql", _db, new GregorianCalendar().getTime()).toString());
					new File("dumps").mkdir();
					dump.createNewFile();
					
					_frame.appendToProgressArea("Writing dump " + dump.getName());
					if (rset.last())
					{
						int rows = rset.getRow();
						rset.beforeFirst();
						if (rows > 0)
						{
							_frame.setProgressIndeterminate(false);
							_frame.setProgressMaximum(rows);
						}
					}
					
					try (FileWriter fileWriter = new FileWriter(dump);
						FileWriterStdout fws = new FileWriterStdout(fileWriter))
					{
						fws.println("-- L2J DBDumper 1.0");
						fws.println("--");
						fws.println("-- Host: " + _host + "    Database: " + _db);
						fws.println("-- ------------------------------------------------------");
						fws.println("-- Server version\t	" + serverVersion);
						fws.println();
						fws.println("SET NAMES utf8;");
						fws.println();
						while (rset.next())
						{
							final String table = rset.getString(1);
							_frame.setProgressValue(rset.getRow());
							_frame.appendToProgressArea("Dumping Table " + table);
							try (Statement desc = con.createStatement();
								ResultSet dset = desc.executeQuery("SHOW CREATE TABLE " + table))
							{
								fws.println("DROP TABLE IF EXISTS `" + table + "`;");
								while (dset.next())
									fws.println(dset.getString(2) + ";");
							}
							
							try (Statement desc = con.createStatement();
								ResultSet dset = desc.executeQuery("SELECT * FROM " + table))
							{
								int cnt = 0;
								while (dset.next())
								{
									if (cnt == 0)
										fws.println("\nLOCK TABLES `" + table + "` WRITE;");
									if (cnt % 100 == 0)
										fws.println("INSERT INTO `" + table + "` VALUES ");
									else
										fws.println(",");
									
									fws.print("(");
									for (int i = 1; i <= dset.getMetaData().getColumnCount(); i++)
									{
										final String value = dset.getString(i);
										if (i > 1)
											fws.print(", ");
										
										if (value == null)
											fws.print("NULL");
										else
											fws.print("'" + value.replace("\'", "\\\'") + "'");
									}
									fws.print(")");
									
									++cnt;
									if (cnt % 100 == 0)
										fws.println(";");
								}
								if (cnt % 100 != 0)
									fws.println(";");
								if (cnt > 0)
									fws.println("UNLOCK TABLES;");
								fws.println();
							}
						}
						fws.flush();
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		_frame.appendToProgressArea("Dump Complete!");
	}
}
