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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;

/**
 * @author  TSL
 * @editor  JOJO
 */
public class CustomMsg
{
	private static Logger _log = Logger.getLogger(CustomMsg.class.getName());

	public enum MsgType
	{
		//type
		  DEFAULT	//0.自由掲示板
		, ANNOUNCE	//1.お知らせ
		, PRIVATE	//2.個人メール
		, CLAN		//3.血盟掲示板
		, MEMO		//4.個人メモ
		/*↑ここから上は互換性のために変更しないでください↑*/
		/*↓ここから下は好きに変更して構いません↓*/
		, QA		//5.Ｑ＆Ａ掲示板
		, TRADE		//6.トレード掲示板
		, EVENT		//7.イベント掲示板
		, HELLO		//8.あいさつ掲示板
		, RECRUIT	//9.血盟員募集掲示板
		;

		private String _nameLCase;  @Override public String toString() { return _nameLCase; }
		private String _caption;  public String getCaption() { return _caption; }

		//permission
		public boolean isMail, isClanmemberOnly;
		public boolean canComment = true, canReply, canEdit, canPostAdminOnly;
		static {
			PRIVATE.canComment = MEMO.canComment = false;		//一言コメント禁止
			PRIVATE.isMail = PRIVATE.canReply = true;			//メール
			MEMO.canReply = RECRUIT.canReply = TRADE.canReply = true;	//TODO:【テスト中】
			CLAN.isClanmemberOnly = true;						//クランメンバーだけ閲覧許可
			ANNOUNCE.canPostAdminOnly = true;					//ＧＭだけ書き込み許可
			MEMO.canEdit = ANNOUNCE.canEdit = true;				//[編集]許可

			//！[編集]は運営上、必要かどうか考えて許可する。
			//！いちど投稿したのを後から書き換えると混乱を招きはしないか？
			DEFAULT.canEdit = QA.canEdit = TRADE.canEdit = CLAN.canEdit = true;

			DEFAULT._caption = "&$750;";		//[750自由掲示板]
			ANNOUNCE._caption = "&$748;";		//[748お知らせ]
			PRIVATE._caption = "&$919;";		//[919メール ボックス]
			CLAN._caption = "&$382;&$387;";		//[382血盟][387掲示板]
			MEMO._caption = "&$403;";			//[403メモ]
			QA._caption = "Q&A&$387;";			//Q&A[387掲示板]
			TRADE._caption = "&$362;&$387;";	//[362トレード][387掲示板]
			EVENT._caption = "イベント掲示板";
			HELLO._caption = "あいさつ掲示板";
			RECRUIT._caption = "血盟員募集掲示板";

			//※改造する人へ：CustomBBSManager.java の getSubMenu() にも掲示板名の設定があります。
		}

		private MsgType()
		{
			_nameLCase = super.name().toLowerCase();
		}

		//method
		private static final MsgType[] _values = MsgType.values();

		public static MsgType enumOf(int v) {	// int to MsgType
			return _values[v];
		}
		public static MsgType enumOf(String v) {	// String to MsgType. Like valueOf()
			for (MsgType item : _values) if (item._nameLCase.equals(v) /*|| item.name().equals(v)*/) return item;
			throw new java.lang.IllegalArgumentException(v);
		}
	}

	private static final DateFormat _dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
	public static String dateFormat(long date)
	{
		synchronized (_dateFormat) {
			return _dateFormat.format(new Date(date));
		}
	}

	public static final int DELETE_OWNER = 1, DELETE_ADMIN = 2;

	private int _msgId;
	private long _msgDate;
	private MsgType _msgType;
	private String _toName;
	private String _toId;
	private String _fromName;
	private String _fromId;
	private String _title;
	private String _message;
	private int _readflg;
	private int _comCount;
	private boolean _isSuccess = false;

	/**
	 * @param msgTable - タイトル一覧受け取り用テーブル
	 * @param msgType
	 * @param activeChar
	 * @param offset - 開始位置
	 * @param limit - 受け取るレコード件数
	 * @return >=0 総数、-1 エラー
	 */
	public static int list(List<CustomMsg> msgTable
			, MsgType msgType, L2PcInstance activeChar
			, int offset, int limit)
	{
		int msgTotal = -1;
		String toId;

		switch(msgType)
		{
		case ANNOUNCE:
			toId = "SERVER_ALL_MEMBER";
			break;
		case PRIVATE:
			toId = activeChar.getAccountName2();
			break;
		case CLAN:
			toId = String.valueOf(activeChar.getClan().getClanId());
			break;
		case MEMO:
			toId = activeChar.getAccountName2();
			break;
		//case DEFAULT:
		//case QA:
		//case TRADE:
		//case EVENTS:
		//case HELLO:
		//case RECRUIT:
		default:
			toId = "";
			break;
		}

		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			try (PreparedStatement statement = con.prepareStatement("SELECT SQL_CALC_FOUND_ROWS msg_id,from_name,title,last_date,com_max,readflg"
				+ " FROM messagez"
				+ " WHERE msg_type=?"
				+ "  AND to_id=?"
				+ "  AND delflg=0"
				+ " ORDER BY last_date DESC,msg_id DESC LIMIT ?,?") )
			{
				statement.setInt(1, msgType.ordinal());
				statement.setString(2, toId);
				statement.setInt(3, offset);
				statement.setInt(4, limit);
				ResultSet result = statement.executeQuery();
				while (result.next())
				{
					CustomMsg t = new CustomMsg();
						t._msgId = result.getInt("msg_id");
						t._fromName = result.getString("from_name");
						t._title = result.getString("title");
						t._msgDate = result.getLong("last_date");
						t._readflg = result.getInt("readflg");
						t._comCount = result.getInt("com_max");
					msgTable.add(t);
				}
			}
			
			try (Statement statement = con.createStatement())
			{
				ResultSet result = statement.executeQuery("SELECT FOUND_ROWS()");
				if (result.next()) msgTotal = result.getInt(1);
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "data error on messagez: ", e);
		}
		return msgTotal;
	}

	public CustomMsg load(int msgId)
	{
		_msgId = msgId;

		_isSuccess = false;
		if (_msgId == 0) return this;
		
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM messagez WHERE msg_id=? AND delflg=0") )
		{
			statement.setInt(1, _msgId);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				_msgDate = result.getLong("msg_date");
				_msgType = MsgType.enumOf(result.getInt("msg_type"));
				_toName = result.getString("to_name");
				_toId = result.getString("to_id");
				_fromName = result.getString("from_name");
				_fromId = result.getString("from_id");
				_title = result.getString("title");
				_message = result.getString("message");
				_readflg = result.getInt("readflg");
			}
			
			_isSuccess = true;
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "data error on messagez " + _msgId + " : ", e);
		}
		return this;
	}
	
	/**
	 * @return false = error, true = success
	 */
	public static boolean insert(MsgType msgType, String toName, String title, String message, L2PcInstance activeChar)
	{
		boolean _isSuccess = false;
//-		if (activeChar == null) return _isSuccess;
//-		System.out.println("__BASENAME__:__LINE__: post " + activeChar.getName() + "[" + activeChar.getClient().getConnection().getInetAddress().getHostName() + "]");

		String fromName = activeChar.getName();
		String fromId = activeChar.getAccountName2();
		String toId;

		switch(msgType)
		{
		case ANNOUNCE:
			toName = "ServerMembers";
			toId = "SERVER_ALL_MEMBER";
			break;
		case PRIVATE:
			if (toName == null) return _isSuccess;
			if ((toId = getIdFromCharName(toName)) == null) return _isSuccess;
			break;
		case CLAN:
			if (activeChar.getClan() == null) return _isSuccess;
			toName = activeChar.getClan().getName();
			toId = String.valueOf(activeChar.getClan().getClanId());
			break;
		case MEMO:
			toName = fromName; //= activeChar.getName();
			toId = fromId; //= activeChar.getAccountName2();
			break;
		//case DEFAULT:
		//case QA:
		//case TRADE:
		//case EVENTS:
		//case HELLO:
		//case RECRUIT:
		default:
			toName = "";
			toId = "";
			break;
		}
		
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO messagez " +
					"(msg_date, last_date, msg_type, to_name, to_id, from_name, from_id, title, message) " +
					"values " +
					"(?,?,?,?,?,?,?,?,?)") )
		{
			long now = System.currentTimeMillis();
			statement.setLong(1, now);
			statement.setLong(2, now);
			statement.setInt(3, msgType.ordinal());
			statement.setString(4, toName);
			statement.setString(5, toId);
			statement.setString(6, fromName);
			statement.setString(7, fromId);
			statement.setString(8, title);
			statement.setString(9, message);
			statement.executeUpdate();

//			statement = con.prepareStatement("SELECT LAST_INSERT_ID()");	//MySQL 5.1.12
//			ResultSet result = statement.executeQuery();
//			if (result.next())
//			{
//				msgId = result.getInt(1);
//			}
//			result.close();
//			statement.close();

			_isSuccess = true;
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "error while insert messagez to db ", e);
		}
		return _isSuccess;
	}
	
	/**
	 * @return false = error, true = success
	 */
	public static boolean update(int msgId, String title, String message, L2PcInstance activeChar)
	{
		boolean isSuccess = false;
		if (msgId == 0) return isSuccess;
		
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE messagez SET last_date=?, title=?, message=? WHERE msg_id=?") )
		{
			long now = System.currentTimeMillis();
			statement.setLong(1, now);
			statement.setString(2, title);
			statement.setString(3, message);
			statement.setInt(4, msgId);
			statement.executeUpdate();
			
			isSuccess = true;			
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "error while update messagez to db ", e);
		}
		return isSuccess;
	}

	/**
	 * @return false = error, true = success
	 */
	public static boolean delete(int msgId, int delflg)
	{
		boolean isSuccess = false;
		if (msgId == 0) return isSuccess;
		
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE messagez SET delflg=? WHERE msg_id=?") )
		{
			statement.setInt(1, delflg);
			statement.setInt(2, msgId);
			statement.executeUpdate();

			isSuccess = true;
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "error while delete messagez from db ", e);
		}
		return isSuccess;
	}

	/**
	 * @param readFlg
	 */
	public void setReadFlg(boolean readFlg)
	{
		_isSuccess = false;
		if (_msgId == 0) return;
		
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE messagez SET readflg=? WHERE msg_id=?") )
		{
			statement.setInt(1, readFlg ? 1 : 0);
			statement.setInt(2, _msgId);
			statement.executeUpdate();
			
			_readflg = readFlg ? 1 : 0;
			_isSuccess = true;			
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "error while update messagez to db ", e);
		}
	}

	/**
	 * @return Number of the unread mail
	 */
	public static int getPrivateMsgCnt(L2PcInstance activeChar)
	{
		int ret = 0;
		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT COUNT(*) FROM messagez"
					+ " WHERE msg_type=2"
					+ " AND to_id=?"
					+ " AND readflg=0"
					+ " AND delflg=0") )
		{
			statement.setString(1, activeChar.getAccountName2());
			ResultSet result = statement.executeQuery();
			if (result.next())
				ret = result.getInt(1);
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "data error on messagez: ", e);
		}
		return ret;
	}

	/**
	 * @param name  Player character name
	 * @return Account name
	 */
	private static String getIdFromCharName(String name)
	{
		String ret = null;

		L2PcInstance pc = L2World.getInstance().getPlayer(name);
		if (pc != null) return pc.getClient().getAccountName();
//		pc = null;

		try (java.sql.Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT account_name FROM characters WHERE char_name=?") )
		{
			statement.setString(1, name);
			ResultSet result = statement.executeQuery();
			if (result.next())
				ret = result.getString("account_name");
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "data error on characters : ", e);
		}
		return ret;
	}

	public boolean isOwn(L2PcInstance activeChar)
	{
		if (getMsgType().isMail)
			return _toId.equals(activeChar.getAccountName2());
		else
			return _fromId.equals(activeChar.getAccountName2());
	}

	/**
	 * access
	 */
	public int getMsgId()
	{
		return _msgId;
	}
	public MsgType getMsgType()
	{
		return _msgType;
	}
	public String getDate()
	{
		return CustomMsg.dateFormat(_msgDate);
	}
	public String getToName()
	{
		return _toName;
	}
	public String getToId()
	{
		return _toId;
	}
	public String getFromName()
	{
		return _fromName;
	}
	public String getFromId()
	{
		return _fromId;
	}
	public String getTitle()
	{
		return _title;
	}
	public String getMessage()
	{
		return _message;
	}
	public boolean getReadFlg()
	{
		return (_readflg == 1);
	}
	public int getComCount()
	{
		return _comCount;
	}

	public boolean isSuccess()
	{
		return _isSuccess;
	}
}