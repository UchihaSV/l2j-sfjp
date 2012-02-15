/* This program is free software: you can redistribute it and/or modify it under
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
package com.l2jserver.util;

import java.lang.management.LockInfo;
import java.lang.management.ManagementFactory;
import java.lang.management.MonitorInfo;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.gameserver.Announcements;
import com.l2jserver.gameserver.Shutdown;

/**
 * Thread to check for deadlocked threads.
 * @author -Nemesiss- L2M
 */
public class DeadLockDetector extends Thread
{
	private static Logger _log = Logger.getLogger(DeadLockDetector.class.getName());

	/** Interval to check for deadlocked threads */
	private static final int _sleepTime = Config.DEADLOCK_CHECK_INTERVAL*1000;
	
	private final ThreadMXBean tmx;
	
	public DeadLockDetector()
	{
		super("DeadLockDetector");
		tmx = ManagementFactory.getThreadMXBean();
	}
	
	@Override
	public final void run()
	{
		boolean deadlock = false;
		while(!deadlock)
		{
			try
			{
				long[] ids = tmx.findDeadlockedThreads();
				
				if(ids != null)
				{
					deadlock = true;
					ThreadInfo[] tis = tmx.getThreadInfo(ids,true,true);
					StringBuilder info = new StringBuilder()
						.append("DeadLock Found!\n");
					for(ThreadInfo ti : tis)
					{
if (Config.FIX_THREADINFO_TO_STRING) {{
						appendThreadInfo(info, ti);
}} else {{
						info.append(ti.toString());
}}
					}
					
					for(ThreadInfo ti : tis)
					{
						LockInfo[] locks = ti.getLockedSynchronizers();
						MonitorInfo[] monitors = ti.getLockedMonitors();
						if(locks.length == 0 && monitors.length == 0)
						{
							continue;
						}
						
						ThreadInfo dl = ti;
						info.append("Java-level deadlock:\n")
						    .append("\t")
						    .append(dl.getThreadName())
						    .append(" is waiting to lock ")
						    .append(dl.getLockInfo().toString())
						    .append(" which is held by ")
						    .append(dl.getLockOwnerName())
						    .append("\n");
						while((dl = tmx.getThreadInfo(new long[]{dl.getLockOwnerId()},true,true)[0]).getThreadId() != ti.getThreadId())
						{
							info.append("\t")
							    .append(dl.getThreadName())
							    .append(" is waiting to lock ")
							    .append(dl.getLockInfo().toString())
							    .append(" which is held by ")
							    .append(dl.getLockOwnerName())
							    .append("\n");
						}
					}
					_log.warning(info.toString());
					
					if(Config.RESTART_ON_DEADLOCK)
					{
						Announcements an = Announcements.getInstance();
						an.announceToAll("Server has stability issues - restarting now.");
						Shutdown.getInstance().startTelnetShutdown("DeadLockDetector - Auto Restart",60,true);
					}
					
				}
				Thread.sleep(_sleepTime);
			}
			catch(Exception e)
			{
				_log.log(Level.WARNING,"DeadLockDetector: ",e);
			}
		}
	}
    //[JOJO]-------------------------------------------------
    // Copy of java.lang.management.ThreadInfo#toString()
    /**
     * Returns a string representation of this thread info.
     * The format of this string depends on the implementation.
     * The returned string will typically include
     * the {@linkplain #getThreadName thread name},
     * the {@linkplain #getThreadId thread ID},
     * its {@linkplain #getThreadState state},
     * and a {@linkplain #getStackTrace stack trace} if any.
     *
     * @return a string representation of this thread info.
     */
    void appendThreadInfo(StringBuilder sb, java.lang.management.ThreadInfo ti) {
if (Config.FIX_THREADINFO_TO_STRING) {{
        MonitorInfo[]       lockedMonitors = ti.getLockedMonitors();
        StackTraceElement[] stackTrace = ti.getStackTrace();
        
        sb.append("\"").append(ti.getThreadName()).append("\"" +
                                             " Id=").append(ti.getThreadId()).append(" ")
                                             .append(ti.getThreadState());
        if (ti.getLockName() != null) {
            sb.append(" on ").append(ti.getLockName());
        }
        if (ti.getLockOwnerName() != null) {
            sb.append(" owned by \"").append(ti.getLockOwnerName())
                      .append("\" Id=").append(ti.getLockOwnerId());
        }
        if (ti.isSuspended()) {
            sb.append(" (suspended)");
        }
        if (ti.isInNative()) {
            sb.append(" (in native)");
        }
        sb.append('\n');
        int i = 0;
        for (; i < stackTrace.length /*&& i < MAX_FRAMES*/; i++) {
            StackTraceElement ste = stackTrace[i];
            sb.append("\tat ").append(ste.toString())
              .append('\n');
            if (i == 0 && ti.getLockInfo() != null) {
                Thread.State ts = ti.getThreadState();
                switch (ts) {
                    case BLOCKED:
                        sb.append("\t-  blocked on ").append(ti.getLockInfo())
                          .append('\n');
                        break;
                    case WAITING:
                        sb.append("\t-  waiting on ").append(ti.getLockInfo())
                          .append('\n');
                        break;
                    case TIMED_WAITING:
                        sb.append("\t-  waiting on ").append(ti.getLockInfo())
                          .append('\n');
                        break;
                    default:
                }
            }

            for (MonitorInfo mi : lockedMonitors) {
                if (mi.getLockedStackDepth() == i) {
                    sb.append("\t-  locked ").append(mi)
                      .append('\n');
                }
            }
       }
       if (i < stackTrace.length) {
           sb.append("\t..."
                   + '\n');
       }

       LockInfo[] locks = ti.getLockedSynchronizers();
       if (locks.length > 0) {
           sb.append("\n\tNumber of locked synchronizers = " + locks.length)
             .append('\n');
           for (LockInfo li : locks) {
               sb.append("\t- ").append(li)
                 .append('\n');
           }
       }
       sb.append('\n');
       /*return sb.toString();*/
}}
    }
    /*private static final int MAX_FRAMES = 8;*/
    //-------------------------------------------------------
}
