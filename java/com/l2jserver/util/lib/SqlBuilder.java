/**
 * JOJO
 */
package com.l2jserver.util.lib;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlBuilder
{
//	private final boolean NO_BACKSLASH_ESCAPES = false;	// MySQL option
	private final java.sql.Connection _con;
	private final String[] _queries;
	private final String[] _values;
	private java.sql.PreparedStatement _prepareStatement;

	public SqlBuilder(java.sql.Connection con, String s)
	{
		_con = con;
		_queries = s.split("\\?", -1);
		_values = new String[_queries.length];
	}

	public ResultSet executeQuery() throws SQLException
	{
		return prepare().executeQuery();
	}
	public int executeUpdate() throws SQLException
	{
		return prepare().executeUpdate();
	}

	public void setBoolean(int i, boolean flag) { _values[i] = String.valueOf(flag); }
	public void setInt(int i, int j) { _values[i] = String.valueOf(j); }
	public void setLong(int i, long l) { _values[i] = String.valueOf(l); }
	public void setFloat(int i, float f) { _values[i] = String.valueOf(f); }
	public void setDouble(int i, double d) { _values[i] = String.valueOf(d); }
	public void setString(int i, String s) { _values[i] = mysql_real_escape_string(s); }

	public boolean execute() throws SQLException
	{
		return prepare().execute();
	}

	public void close() throws SQLException
	{
		_prepareStatement.close();
	}

	public String getQuery() throws SQLException
	{
		StringBuilder b = new StringBuilder(_queries[0]);
		for (int i = 1; i < _queries.length; ++i)
		{
			if (_values[i] == null)
				throw new SQLException();
			b.append(_values[i]).append(_queries[i]);
		}
		return b.toString();
	}

	private java.sql.PreparedStatement prepare() throws SQLException
	{
		return _prepareStatement = _con.prepareStatement(getQuery());
	}

	private String mysql_real_escape_string(String v)
	{
		return "'" + v.replace("'", "''").replace("\\", "\\\\") + "'";
	}
}
