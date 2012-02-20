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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import javolution.io.UTF8StreamReader;
import javolution.io.UTF8StreamWriter;

import com.l2jserver.tools.dbinstaller.DBOutputInterface;

/**
 * @author mrTJO
 */
public class ScriptExecutor
{
	DBOutputInterface _frame;
	String _log;
	
	public ScriptExecutor(DBOutputInterface frame, String log)
	{
		_frame = frame;
		_log = log;
		new File(log).delete();
	}
	
	public void execSqlBatch(File dir)
	{
		execSqlBatch(dir, false);
	}
	
	public void execSqlBatch(File dir, boolean skipErrors)
	{
		File[] file = dir.listFiles(new SqlFileFilter());
		_frame.setProgressIndeterminate(false);
		_frame.setProgressMaximum(file.length - 1);
		for (int i = 0; i < file.length; i++)
		{
			_frame.setProgressValue(i);
			execSqlFile(file[i], skipErrors);
		}
	}
	
	public void execSqlFile(File file)
	{
		execSqlFile(file, false);
	}
	
	public void execSqlFile(File file, boolean skipErrors)
	{
		try
		{
			log(file.getPath() + ":");
			_frame.appendToProgressArea("Installing " + file.getName());
			String line;
			Connection con = _frame.getConnection();
			Statement stmt = con.createStatement();
			BufferedReader scn = new BufferedReader(new UTF8StreamReader().setInput(new FileInputStream(file)));
			StringBuilder sb = new StringBuilder();
			Pattern patternSourceStatement = Pattern.compile("^SOURCE[ \t]+(.+);$", Pattern.CASE_INSENSITIVE);
			while ((line = scn.readLine()) != null)
			{
				if (line.startsWith("\uFEFF"))
					line = line.substring(1);
				if (line.startsWith("--"))
					continue;
				else if (line.contains("--"))
					line = line.split("--")[0];
				
				line = line.trim();
				if (!line.isEmpty())
					sb.append(line).append('\n');
				
				if (line.endsWith(";"))
				{
					String sql = sb.toString();
					sb.setLength(0);
					Matcher m = patternSourceStatement.matcher(sql);
					if (m.find())
						execSqlFile(new File(m.group(1)), skipErrors);	//recursive call
					else
						stmt.execute(sql);
				}
			}
			scn.close();
		}
		catch (FileNotFoundException e)
		{
			log(e.getMessage());
			JOptionPane.showMessageDialog(null, "File Not Found!: " + e.getMessage(), "Installer Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException e)
		{
			log(e.getMessage());
			if (!skipErrors)
			{
				Object[] options =
				{
					"Continue", "Abort"
				};
				int n = JOptionPane.showOptionDialog(null, "MySQL Error: " + e.getMessage(), "Script Error", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if (n == 1)
					System.exit(0);
			}
		}
		catch (Exception e)
		{
			log(e.getMessage());
			e.printStackTrace();  //console: java -cp dbinst_gs.jar com.l2jserver.tools.dbinstaller.LauncherGS
			
			Object[] options =
			{
				"Abort"
			};
			JOptionPane.showOptionDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			
			System.exit(0);
		}
	}
	
	public static class SqlFileFilter implements FileFilter
	{
		@Override
		public boolean accept(File pathname)
		{
			return pathname.getName().endsWith(".sql");
		}
	}
	
	private void log(String msg)
	{
		try (BufferedWriter log = new BufferedWriter(new UTF8StreamWriter().setOutput((new FileOutputStream(_log, true))));)
		{
			log.write(msg);
			log.write("\r\n");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
