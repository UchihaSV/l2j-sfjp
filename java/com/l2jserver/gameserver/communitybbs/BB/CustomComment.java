/*
 * This program is free software; you can redistribute it and/or modify
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
package com.l2jserver.gameserver.communitybbs.BB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author  JOJO
 * 
 * åfé¶î¬"àÍåæÉRÉÅÉìÉg"óp
 */
public class CustomComment /**extends CustomMsg**/
{
	private static Logger _log = Logger.getLogger(CustomComment.class.getName());

	private int _msgId;
	private int _comId;
	private long _msgDate;
	private String _fromName;
	private String _fromId;
	private String _message;
	private String _title;

	public static int list(List<CustomComment> comTable
			, int msgId
			, int offset, int limit)
	{
		int comTotal = -1;

		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			try (PreparedStatement statement = con.prepareStatement("SELECT SQL_CALC_FOUND_ROWS com_id,msg_date,from_name,from_id,message"
											+ " FROM commentz"
											+ " WHERE msg_id=?"
											+ " AND delflg=0 ORDER BY com_id DESC"
											+ " LIMIT ?,?"))
			{
				statement.setInt(1, msgId);
				statement.setInt(2, offset);
				statement.setInt(3, limit);
				ResultSet result = statement.executeQuery();
				while (result.next())
				{
					CustomComment t = new CustomComment();
						t._msgId = msgId;
						t._comId = result.getInt("com_id");
						t._msgDate = result.getLong("msg_date");
						t._fromName = result.getString("from_name");
						t._fromId = result.getString("from_id");
						t._message = result.getString("message");
					comTable.add(t);
				}
			}
			try (Statement statement = con.createStatement())
			{
				ResultSet result = statement.executeQuery("SELECT FOUND_ROWS()");
				if (result.next()) comTotal = result.getInt(1);
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "data error on commentz: ", e);
		}
		return comTotal;
	}

	public CustomComment load(int msgId, int comId)
	{
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement
				= con.prepareStatement("SELECT C.msg_date,C.from_name,C.from_id,C.message"
												+ ",M.title"
												+ " FROM commentz AS C"
												+ " LEFT JOIN messagez AS M ON C.msg_id=M.msg_id"
												+ " WHERE C.msg_id=? AND com_id=?"
												+ " AND C.delflg=0") )
		{
			statement.setInt(1, msgId);
			statement.setInt(2, comId);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				_msgId = msgId;
				_comId = comId;
				_msgDate = result.getLong("msg_date");
				_fromName = result.getString("from_name");
				_fromId = result.getString("from_id");
				_message = result.getString("message");
				_title = result.getString("title");
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "data error on commentz: ", e);
		}
		return this;
	}

	public static boolean insert(int msgId, String comment, L2PcInstance activeChar)
	{
		boolean isSuccess = false;

		final long now = System.currentTimeMillis();
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			try (PreparedStatement statement = con.prepareStatement("INSERT INTO commentz"
				+ " SET msg_date=?, from_name=?, from_id=?, message=?"
				+ ", msg_id=?") )
			{
				statement.setLong(1, now);
				statement.setString(2, activeChar.getName());
				statement.setString(3, activeChar.getAccountName2());
				statement.setString(4, comment);
				statement.setInt(5, msgId);
				statement.executeUpdate();
			}

			// "age"
			try (PreparedStatement statement = con.prepareStatement("UPDATE messagez SET last_date=?"
					+ ", com_max=(SELECT MAX(com_id) FROM commentz WHERE commentz.msg_id=messagez.msg_id GROUP BY msg_id)"
					+ " WHERE msg_id=?"))
			{
				statement.setLong(1, now);
				statement.setInt(2, msgId);
				statement.executeUpdate();
			}

			isSuccess = true;
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "error while insert commentz to db ", e);
		}
		return isSuccess;
	}

	public static boolean delete(int msgId, int comId, int delflg)
	{
		boolean isSuccess = false;
		if (msgId == 0) return isSuccess;
		if (comId == 0) return isSuccess;
		
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE commentz SET delflg=? WHERE msg_id=? AND com_id=?") )
		{
			statement.setInt(1, delflg);
			statement.setInt(2, msgId);
			statement.setInt(3, comId);
			statement.executeUpdate();
			
			isSuccess = true;
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "error while delete commentz from db ", e);
		}
		return isSuccess;
	}
	
	public boolean isOwn(L2PcInstance activeChar)
	{
		return _fromId.equals(activeChar.getAccountName2());
	}

	/**
	 * access
	 */
	public int getMsgId()
	{
		return _msgId;
	}
	public int getComId()
	{
		return _comId;
	}
	public String getDate()
	{
		return CustomMsg.dateFormat(_msgDate);
	}
	public String getFromName()
	{
		return _fromName;
	}
	public String getFromId()
	{
		return _fromId;
	}
	public String getMessage()
	{
		return _message;
	}
	public String getTitle()
	{
		return _title;
	}
}