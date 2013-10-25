/*
 * Copyright (C) 2004-2013 L2J Server
 * 
 * This file is part of L2J Server.
 * 
 * L2J Server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * L2J Server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.l2jserver.gameserver.instancemanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.model.variables.AbstractVariables;

/**
 * Global Variables Manager.
 * @author xban1x
 */
public final class GlobalVariablesManager extends AbstractVariables
{
	private static final Logger _log = Logger.getLogger(GlobalVariablesManager.class.getName());
	
	// SQL Queries.
	private static final String SELECT_QUERY = "SELECT * FROM global_variables";
	private static final String DELETE_QUERY = "DELETE FROM global_variables";
	private static final String INSERT_QUERY = "INSERT INTO global_variables (var, val) VALUES (?, ?)";
	
	protected GlobalVariablesManager()
	{
		load();
	}
	
	@Override
	protected void load()
	{
		// Restore previous variables.
		try (Connection con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement st = con.prepareStatement(SELECT_QUERY))
		{
			try (ResultSet rset = st.executeQuery())
			{
				while (rset.next())
				{
					set(rset.getString("var"), rset.getString("value"));
				}
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't restore global variables");
		}
		_log.log(Level.INFO, getClass().getSimpleName() + ": Loaded " + getSet().size() + " variables.");
	}
	
	@Override
	public void store()
	{
		// No changes, nothing to store.
		if (!isChanges())
		{
			return;
		}
		setChanges(false);
		
		try (Connection con = L2DatabaseFactory.getInstance().getConnection())
		{
			// Clear previous entries.
			try (PreparedStatement st = con.prepareStatement(DELETE_QUERY))
			{
				st.execute();
			}
			
			// Insert all variables.
			try (PreparedStatement st = con.prepareStatement(INSERT_QUERY))
			{
				for (Entry<String, Object> entry : getSet().entrySet())
				{
					st.setString(1, entry.getKey());
					st.setString(2, String.valueOf(entry.getValue()));
					st.addBatch();
				}
				st.executeBatch();
			}
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, getClass().getSimpleName() + ": Couldn't save global variables to database.", e);
		}
		_log.log(Level.INFO, getClass().getSimpleName() + ": Stored " + getSet().size() + " variables.");
	}
	
	/**
	 * Gets the single instance of {@code GlobalVariablesManager}.
	 * @return single instance of {@code GlobalVariablesManager}
	 */
	public static final GlobalVariablesManager getInstance()
	{
		return SingletonHolder._instance;
	}
	
	private static class SingletonHolder
	{
		protected static final GlobalVariablesManager _instance = new GlobalVariablesManager();
	}
}