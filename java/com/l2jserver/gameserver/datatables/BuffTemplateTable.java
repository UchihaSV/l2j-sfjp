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
package com.l2jserver.gameserver.datatables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import javolution.util.FastList;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.templates.L2BuffTemplate;
/**
 * This class represents the buff templates list
 * 
 * Author: JOJO
 * 
 * This code was based on...
 * l2jfree.com BuffTemplateTable.java Revision:2827
 * Author: G1ta0
 */

@Deprecated
public class BuffTemplateTable
{
	private static Logger _log = Logger.getLogger(BuffTemplateTable.class.getName());
    
	private static BuffTemplateTable _instance = new BuffTemplateTable();

	/** The table containing all buff templates */
	private FastList<L2BuffTemplate> _buffs;

	public static BuffTemplateTable getInstance()
	{
		return _instance;
	}

	/**
	 * Create and Load the buff templates from SQL Table buff_templates
	 */
	private BuffTemplateTable()
	{
		_buffs = new FastList<L2BuffTemplate>();
		ReloadBuffTemplates();
	}

	/**
	 * Read and Load the buff templates from SQL Table buff_templates
	 */
	public void ReloadBuffTemplates()
	{
		_buffs.clear();

		java.sql.Connection con = null;
		try 
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM npc_buffer_list ORDER BY id, skill_order");
			ResultSet rset = statement.executeQuery();

			int buffTemplates = 0;
			int templateId = -1;

			while (rset.next())
			{
				if (templateId != rset.getInt("id")) ++buffTemplates;
				L2BuffTemplate template = new L2BuffTemplate(rset);

				if (template.getSkill() == null)
					_log.warning("Error while loading buff template Id " + template.getId() + " skill Id " + template.getSkillId());
				else
					_buffs.add(template);
			}

			_log.info("BuffTemplateTable: Loaded " + buffTemplates + " Buff Templates.");

			rset.close();
			statement.close();
		} 
		catch (Exception e) 
		{
			_log.warning("Error while loading buff templates "+e.getMessage());
			e.printStackTrace();	//XXX:DEBUG
		}
		finally 
		{
			L2DatabaseFactory.close(con);
		}
	}

	/**
	 * @return Returns the template Id by template Name
	 */
	public int getTemplateIdByName(String name)
	{
		for (L2BuffTemplate buffer : _buffs)
		{
			if (buffer.getName().equals(name))
				return buffer.getId();
		}
		return 0;
	}

	/**
	 * @return Returns the buff templates list
	 */
	public FastList<L2BuffTemplate> getBuffTemplateTable()
	{
		return _buffs;
	}
}
