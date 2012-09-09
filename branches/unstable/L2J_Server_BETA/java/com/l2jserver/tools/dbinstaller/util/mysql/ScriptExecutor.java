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

import static java.nio.charset.StandardCharsets.UTF_8;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.l2jserver.tools.dbinstaller.DBOutputInterface;
import com.l2jserver.util.file.filter.SQLFilter;

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
		File[] file = dir.listFiles(new SQLFilter());
		Arrays.sort(file);
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
	
	private void execSqlFile(File file, boolean skipErrors)
	{
		execSqlFile(file, skipErrors, "Installing ");
	}
	
	private void execSqlFile(File file, boolean skipErrors, String header)
	{
		int lineNumber = 0;
		log(file.getPath() + ":");
		_frame.appendToProgressArea(header + file.getName());
		Connection con = _frame.getConnection();
		try (Statement stmt = con.createStatement();
			LineNumberReader scn = new LineNumberReader(new InputStreamReader(new FileInputStream(file), UTF_8)))
		{
			StringBuilder sb = new StringBuilder();
			Pattern patternComment1 = Pattern.compile("(?<!-)--\\s.*");	// -- comment
	//TODO:	Pattern patternComment2 = Pattern.compile("#.*");			// #comment
	//TODO:																// /*comment*/
			Pattern patternSourceStatement = Pattern.compile("^SOURCE[ \t]+(.+);$", Pattern.CASE_INSENSITIVE);
			String line;
			while ((line = scn.readLine()) != null)
			{
				line = line + "\n";
				if (line.startsWith("\uFEFF"))
					line = line.substring(1);
				String trimed = patternComment1.matcher(line).replaceFirst("").trim();
				if (sb.length() == 0)
				{
					if (trimed.length() == 0)
						continue;
					lineNumber = scn.getLineNumber();
				}
				sb.append(line);
				
				if (trimed.endsWith(";"))
				{
					String sql = sb.toString();
					sb.setLength(0);
					Matcher m = patternSourceStatement.matcher(sql);
					if (m.find())
						execSqlFile(new File(m.group(1)), skipErrors, "SOURCE ");	//recursive call
					else if (skipErrors)
						try
						{
							stmt.execute(sql);
						}
						catch (SQLException e)
						{
							log(e.getMessage());
						}
					else
						stmt.execute(sql);
				}
			}
			if (sb.length() > 0)
				stmt.execute(sb.toString());	//TODO: SOURCE, skipErrors
		}
		catch (FileNotFoundException e)
		{
			log(e);
			JOptionPane.showMessageDialog(null, "File Not Found!: " + e.getMessage(), "Installer Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException e)
		{
			log(e);
			assert !skipErrors;
			try
			{
				Object[] options =
				{
					"Continue",
					"Abort"
				};
				
				Matcher m = Pattern.compile(" at line (\\d+)"/*, Pattern.CASE_INSENSITIVE*/).matcher(e.getMessage());
				if (m.find())
					lineNumber = lineNumber + Integer.parseInt(m.group(1)) - 1;
				m = null;
				
				int n = JOptionPane.showOptionDialog(null, "MySQL Error: " + e.getMessage()
					+ "\r\n" + file.getPath() + (lineNumber > 0 ? " at line " + lineNumber : "")
					, "Script Error", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if (n == 1)
					System.exit(0);
			}
			catch (HeadlessException h)
			{
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			log(e);
			
			Object[] options =
			{
				"Abort"
			};
			JOptionPane.showOptionDialog(null, "Error: " + e.getMessage()
				+ "\r\n" + file.getPath()
				, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
			
			System.exit(0);
		}
	}
	private PrintWriter openLog()
	{
		try
		{
			return new PrintWriter(new OutputStreamWriter(new FileOutputStream(_log, true), UTF_8));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	private void log(String msg)
	{
		System.out.println(msg);	// console: java -jar dbinst_gs.jar
		
		PrintWriter log = openLog();
		log.println(msg);
		log.close();
	}
	private void log(Throwable error)
	{
		error.printStackTrace();
		
		PrintWriter log = openLog();
		error.printStackTrace(log);
		log.close();
	}
 //	@SuppressWarnings("unused") private void log(String msg, Throwable error)
 //	{
 //		System.out.print(msg);	// console: java -jar dbinst_gs.jar
 //		System.out.print(" - ");
 //		error.printStackTrace();
 //		
 //		PrintWriter log = openLog();
 //		log.print(msg);
 //		log.print(" - ");
 //		error.printStackTrace(log);
 //		log.close();
 //	}
}
