/* This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.l2jserver.status;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * Executes a given command if the os platform is appropriate.
 * 
 * @author  JOJO
 */

//[参考] http://svn.apache.org/repos/asf/ant/core/trunk/src/main/org/apache/tools/ant/taskdefs/Exec.java
//[参考] http://www.atmarkit.co.jp/bbs/phpBB/viewtopic.php?topic=25599&forum=12&2

public class TelnetExec {

	private static final String myos = System.getProperty("os.name");

	private PrintWriter _print;

	public TelnetExec(PrintWriter print) {
		this._print = print;
	}

	/**
	 * Execute the command.
	 * @param command the command to exec
	 * @return the exit value of the command
	 */
	public int exec(String command) {
		int exitValue = -1;

		String[] commands;
		if (myos.startsWith("Windows")) {
			commands = new String[] { "cmd.exe", "/C", command };
		} else if (myos.startsWith("Mac OS")) {
			return exitValue;
		} else {
			commands = new String[] { "/bin/sh" , "-c" , command };
//			commands = new String[] { "/bin/sh" , "-exec" , command };
//			commands = new String[] { command };
		}

		try {
		//	echo(command);

			Process process = Runtime.getRuntime().exec(commands);
			StreamPumper stdOutPumper = new StreamPumper(process.getInputStream()); // STDOUT
			StreamPumper stdErrPumper = new StreamPumper(process.getErrorStream()); // STDERR
			stdOutPumper.start();
			stdErrPumper.start();
			process.waitFor();
			stdOutPumper.join();
			stdErrPumper.join();
			process.destroy();
			exitValue = process.exitValue();
		} catch (Exception e) {
		}

		return exitValue;
	}

	void echo(String line)
	{
		_print.println(line);
		_print.flush();
	}

	class StreamPumper extends Thread {

		private static final int SLEEP_TIME = 5;

		private BufferedReader pumpStreamReader;

		StreamPumper(final InputStream is) {
			this.pumpStreamReader = new BufferedReader(new InputStreamReader(is));
		}

		@Override
		public void run() {
			try {
				String line;
				while ((line = pumpStreamReader.readLine()) != null) {
					echo(line);
					sleep(SLEEP_TIME);
				}
				pumpStreamReader.close();
			} catch (InterruptedException e) {
			} catch (IOException e) {
			}
		}
	}
}
