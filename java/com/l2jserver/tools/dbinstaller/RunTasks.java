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
package com.l2jserver.tools.dbinstaller;

import java.io.File;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.l2jserver.tools.dbinstaller.util.mysql.DBDumper;
import com.l2jserver.tools.dbinstaller.util.mysql.ScriptExecutor;

/**
 * @author mrTJO
 */
public class RunTasks extends Thread
{
	DBOutputInterface _frame;
	boolean _cleanInstall;
	String _host;
	String _db;
	String _sqlDir;
	String _cleanUpFile;
	String _log;
	
	public RunTasks(DBOutputInterface frame, String host, String db, String sqlDir, String cleanUpFile, boolean cleanInstall, String log)
	{
		_frame = frame;
		_host = host;
		_db = db;
		_cleanInstall = cleanInstall;
		_sqlDir = sqlDir;
		_cleanUpFile = cleanUpFile;
		_log = log;
	}
	
	@Override
	public void run()
	{
		new DBDumper(_frame, _host, _db);
		ScriptExecutor exec = new ScriptExecutor(_frame, _log);
		
		if (_cleanInstall)
		{
			File clnFile = new File(_cleanUpFile);
			if (clnFile.exists())
			{
				_frame.appendToProgressArea("Cleaning Database...");
				exec.execSqlFile(clnFile);
				_frame.appendToProgressArea("Database Cleaned!");
			}
			else
			{
				_frame.appendToProgressArea("Database Cleaning Script Not Found!");
			}
		}
		else
		{
			File updDir = new File(_sqlDir, "updates");
			if (updDir.exists())
			{
				_frame.appendToProgressArea("Installing Updates...");
				exec.execSqlBatch(updDir, true);
				_frame.appendToProgressArea("Database Updates Installed!");
			}
		}
		
		_frame.appendToProgressArea("Installing Database Content...");
		exec.execSqlBatch(new File(_sqlDir));
		_frame.appendToProgressArea("Database Installation Complete!");
		
		File cusDir = new File(_sqlDir, "custom");
		if (cusDir.exists())
		{
			int ch = _frame.requestConfirm("Install Custom", "Do you want to install custom tables?", JOptionPane.YES_NO_OPTION);
			if (ch == 0)
			{
				_frame.appendToProgressArea("Installing Custom Tables...");
				exec.execSqlBatch(cusDir);
				_frame.appendToProgressArea("Custom Tables Installed!");
			}
		}
		
		File modDir = new File(_sqlDir, "mods");
		if (modDir.exists())
		{
			int ch = _frame.requestConfirm("Install Mods", "Do you want to install mod tables?", JOptionPane.YES_NO_OPTION);
			if (ch == 0)
			{
				_frame.appendToProgressArea("Installing Mods Tables...");
				exec.execSqlBatch(modDir);
				_frame.appendToProgressArea("Mods Tables Installed!");
			}
		}
		
		try
		{
			_frame.getConnection().close();
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, "Cannot close MySQL Connection: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
		}
		
	//	_frame.setFrameVisible(false);
		_frame.showMessage("Done!", "Database Installation Complete!", JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
}
