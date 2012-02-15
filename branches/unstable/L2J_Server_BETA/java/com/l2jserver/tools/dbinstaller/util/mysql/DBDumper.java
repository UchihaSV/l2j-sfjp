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
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	String _db;
	
	public DBDumper(DBOutputInterface frame, String db)
	{
		_frame = frame;
		_db = db;
		createDump();
	}
	
	public void createDump()
	{
		try
		{
			Connection con = _frame.getConnection();
			Formatter form = new Formatter();
			PreparedStatement stmt = con.prepareStatement("SHOW TABLES");
			ResultSet rset = stmt.executeQuery();
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
			
			FileWriterStdout ps = new FileWriterStdout(dump);
			ps.println("-- SET NAMES utf8;");
			ps.println();
			while (rset.next())
			{
				final String table = rset.getString(1);
				_frame.setProgressValue(rset.getRow());
				_frame.appendToProgressArea("Dumping Table " + table);
				PreparedStatement desc;
				ResultSet dset;
				desc = con.prepareStatement("SHOW CREATE TABLE " + table);
				dset = desc.executeQuery();
				ps.println("-- DROP TABLE IF EXISTS `" + table + "`;");
				while (dset.next())
					ps.println(dset.getString(2) + ";");
				ps.flush();
				dset.close();
				desc.close();
				
				desc = con.prepareStatement("SELECT * FROM " + table);
				dset = desc.executeQuery();
				int cnt = 0;
				while (dset.next())
				{
					if (cnt % 100 == 0)
						ps.println("INSERT INTO `" + table + "` VALUES ");
					else
						ps.println(",");
					
					ps.print("\t(");
					for (int i = 1; i <= dset.getMetaData().getColumnCount(); i++)
					{
						final String value = dset.getString(i);
						if (i > 1)
							ps.print(", ");
						
						if (value == null)
							ps.print("NULL");
						else
							ps.print("'" + value.replace("\'", "\\\'") + "'");
					}
					ps.print(")");
					
					++cnt;
					if (cnt % 100 == 0)
						ps.println(";");
				}
				if (cnt % 100 != 0)
					ps.println(";");
				ps.println();
				ps.flush();
				dset.close();
				desc.close();
			}
			rset.close();
			stmt.close();
			ps.flush();
			ps.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		_frame.appendToProgressArea("Dump Complete!");
	}
}
