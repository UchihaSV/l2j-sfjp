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
package com.l2jserver.gameserver.instancemanager.games;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.Announcements;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.datatables.SpawnTable;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.model.items.instance.L2ItemInstance;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.util.Rnd;


public class Lottery
{
	public static final long SECOND = 1000;
	public static final long MINUTE = 60000;
	
	protected static final Logger _log = Logger.getLogger(Lottery.class.getName());
	
	private static final String INSERT_LOTTERY = "INSERT INTO games(id, idnr, enddate, prize, newprize) VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_PRICE = "UPDATE games SET prize=?, newprize=? WHERE id = 1 AND idnr = ?";
	private static final String UPDATE_LOTTERY = "UPDATE games SET finished=1, prize=?, newprize=?, number1=?, number2=?, prize1=?, prize2=?, prize3=? WHERE id=1 AND idnr=?";
	private static final String SELECT_LAST_LOTTERY = "SELECT idnr, prize, newprize, enddate, finished FROM games WHERE id = 1 ORDER BY idnr DESC LIMIT 1";
	private static final String SELECT_LOTTERY_ITEM = "SELECT enchant_level, custom_type2 FROM items WHERE item_id = 4442 AND custom_type1 = ?";
	private static final String SELECT_LOTTERY_TICKET = "SELECT number1, number2, prize1, prize2, prize3 FROM games WHERE id = 1 and idnr = ?";
	
	protected int _number;
	protected long _prize;
	protected boolean _isSellingTickets;
	protected boolean _isStarted;
	protected long _enddate;
	
	protected Lottery()
	{
		_number = 1;
		_prize = Config.ALT_LOTTERY_PRIZE;
		_isSellingTickets = false;
		_isStarted = false;
		_enddate = System.currentTimeMillis();
		
		if (Config.ALLOW_LOTTERY)
			(new startLottery()).run();
	}
	
	public static Lottery getInstance()
	{
		return SingletonHolder._instance;
	}
	
	public int getId()
	{
		return _number;
	}
	
	public long getPrize()
	{
		return _prize;
	}
	
	public long getEndDate()
	{
		return _enddate;
	}
	
	public void increasePrize(long count)
	{
		_prize += count;
		Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement(UPDATE_PRICE);
			statement.setLong(1, getPrize());
			statement.setLong(2, getPrize());
			statement.setInt(3, getId());
			statement.execute();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "Lottery: Could not increase current lottery prize: " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
	
	public boolean isSellableTickets()
	{
		return _isSellingTickets;
	}
	
	public boolean isStarted()
	{
		return _isStarted;
	}
	
	private class startLottery implements Runnable
	{
		protected startLottery()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			final long NOW = System.currentTimeMillis();
			Connection con = null;
			PreparedStatement statement;
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				statement = con.prepareStatement(SELECT_LAST_LOTTERY);
				ResultSet rset = statement.executeQuery();
				
				if (rset.next())
				{
					_number = rset.getInt("idnr");
					
					if (rset.getInt("finished") == 1)
					{
						_number++;
						_prize = rset.getLong("newprize");
						rset.close(); rset = null;
						statement.close(); statement = null;
						L2DatabaseFactory.close(con); con = null;
					}
					else
					{
						_prize = rset.getLong("prize");
						_enddate = rset.getLong("enddate");
						rset.close(); rset = null;
						statement.close(); statement = null;
						L2DatabaseFactory.close(con); con = null;
						
						_isStarted = true;
						ThreadPoolManager.getInstance().scheduleGeneral(new finishLottery(), _enddate - NOW);	//ìyój19:00
						
						if (_enddate - 10 * MINUTE - NOW > 0)	//ìyój18:50
						{
							_isSellingTickets = true;
							ThreadPoolManager.getInstance().scheduleGeneral(new stopSellingTickets(), _enddate - 10 * MINUTE - NOW);	//ìyój18:50
						}
						return;
					}
				}
			}
			catch (SQLException e)
			{
				_log.log(Level.WARNING, "Lottery: Could not restore lottery data: " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			
			if (Config.DEBUG)
				_log.info("Lottery: Starting ticket sell for lottery #" + getId() + ".");
			_isSellingTickets = true;
			_isStarted = true;
			
			shout(1000282/*çKâ^ÇÃïÛÇ≠Ç∂ÇÃîÃîÑÇçsÇ¢Ç‹Ç∑ÅI*/);
		//	Announcements.getInstance().announceToAll("Lottery tickets are now available for Lucky Lottery #" + getId() + ".");
			// _enddate = éüÇÃìyójì˙19:00
			Calendar finishtime = Calendar.getInstance();
			finishtime.set(Calendar.MILLISECOND, 0);
			finishtime.set(Calendar.SECOND, 0);
			finishtime.set(Calendar.MINUTE, 0);
			finishtime.set(Calendar.HOUR_OF_DAY, 19);
			finishtime.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			while ((_enddate = finishtime.getTimeInMillis()) <= NOW)
				finishtime.add(Calendar.DAY_OF_MONTH, 7);
			
			ThreadPoolManager.getInstance().scheduleGeneral(new stopSellingTickets(), _enddate - 10 * MINUTE - NOW);	//ìyójì˙18:50 îÃîÑí˜êÿ
			ThreadPoolManager.getInstance().scheduleGeneral(new finishLottery(), _enddate - NOW);	//ìyójì˙19:00 íäëI ... 10ï™å„ îÃîÑäJén
			
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				statement = con.prepareStatement(INSERT_LOTTERY);
				statement.setInt(1, 1);
				statement.setInt(2, getId());
				statement.setLong(3, getEndDate());
				statement.setLong(4, getPrize());
				statement.setLong(5, getPrize());
				statement.execute();
				statement.close();
			}
			catch (SQLException e)
			{
				_log.log(Level.WARNING, "Lottery: Could not store new lottery data: " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
		}
	}
	
	private class stopSellingTickets implements Runnable
	{
		protected stopSellingTickets()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			_isSellingTickets = false;
			if (Config.DEBUG)
				_log.info("Lottery: Stopping ticket sell for lottery #" + getId() + ".");
			
			Announcements.getInstance().announceToAll(SystemMessage.getSystemMessage(SystemMessageId.LOTTERY_TICKET_SALES_TEMP_SUSPENDED));
		}
	}
	
	private class finishLottery implements Runnable
	{
		protected finishLottery()
		{
			// Do nothing
		}
		
		@Override
		public void run()
		{
			_isStarted = false;
			if (Config.DEBUG)
				_log.info("Lottery: Ending lottery #" + getId() + ".");
			shout(1000283/*çKâ^ÇÃïÛÇ≠Ç∂ÇÃíäëIÇçsÇ¢Ç‹Ç∑ÅI*/);
			
			int[] luckynums = new int[5];
			
			for (int i = 0; i < 5; i++)
			{
				int luckynum;
				do
					luckynum = Rnd.get(1, 20);
				while (com.l2jserver.gameserver.util.Util.contains(luckynums, luckynum));
				luckynums[i] = luckynum;
			}
			Arrays.sort(luckynums);
			
			if (Config.DEBUG)
				_log.info("Lottery: The lucky numbers are " + luckynums[0] + ", " + luckynums[1] + ", " + luckynums[2] + ", " + luckynums[3] + ", " + luckynums[4] + ".");
			
			int enchant = 0;
			int type2 = 0;
			
			for (int i = 0; i < 5; i++)
			{
				int num = luckynums[i];
				if (num <= 16)
					enchant |= 1 << (num - 1);
				else
					type2 |= 1 << (num - 16 - 1);
			}
			
			if (Config.DEBUG)
				_log.info("Lottery: Encoded lucky numbers are " + enchant + ", " + type2);
			
			int count1 = 0;
			int count2 = 0;
			int count3 = 0;
			int count4 = 0;
			
			Connection con = null;
			PreparedStatement statement;
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				statement = con.prepareStatement(SELECT_LOTTERY_ITEM);
				statement.setInt(1, getId());
				ResultSet rset = statement.executeQuery();
				
				while (rset.next())
				{
					int curenchant = rset.getInt("enchant_level") & enchant;
					int curtype2 = rset.getInt("custom_type2") & type2;
					
					if (curenchant == 0 && curtype2 == 0)
						continue;
					
					int count = 0;
					
					count = Integer.bitCount(curtype2 << 16 | curenchant);
					
					if (count == 5)
						count1++;
					else if (count == 4)
						count2++;
					else if (count == 3)
						count3++;
					else if (count > 0)
						count4++;
				}
				rset.close();
				statement.close();
			}
			catch (SQLException e)
			{
				_log.log(Level.WARNING, "Lottery: Could restore lottery data: " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			
			long prize4 = count4 * Config.ALT_LOTTERY_2_AND_1_NUMBER_PRIZE;
			long prize1 = 0;
			long prize2 = 0;
			long prize3 = 0;
			
			if (count1 > 0)
				prize1 = (long) ((getPrize() - prize4) * Config.ALT_LOTTERY_5_NUMBER_RATE / count1);
			
			if (count2 > 0)
				prize2 = (long) ((getPrize() - prize4) * Config.ALT_LOTTERY_4_NUMBER_RATE / count2);
			
			if (count3 > 0)
				prize3 = (long) ((getPrize() - prize4) * Config.ALT_LOTTERY_3_NUMBER_RATE / count3);
			
			if (Config.DEBUG)
			{
				_log.info("Lottery: " + count1 + " players with all FIVE numbers each win " + prize1 + ".");
				_log.info("Lottery: " + count2 + " players with FOUR numbers each win " + prize2 + ".");
				_log.info("Lottery: " + count3 + " players with THREE numbers each win " + prize3 + ".");
				_log.info("Lottery: " + count4 + " players with ONE or TWO numbers each win " + prize4 + ".");
			}
			
			long newprize = getPrize() - (prize1 + prize2 + prize3 + prize4);
			if (Config.DEBUG)
				_log.info("Lottery: Jackpot for next lottery is " + newprize + ".");
			
			SystemMessage sm;
			if (count1 > 0)
			{
				// There are winners.
				sm = SystemMessage.getSystemMessage(SystemMessageId.AMOUNT_FOR_WINNER_S1_IS_S2_ADENA_WE_HAVE_S3_PRIZE_WINNER);
				sm.addNumber(getId());
				sm.addItemNumber(getPrize());
				sm.addItemNumber(count1);
				Announcements.getInstance().announceToAll(sm);
			}
			else
			{
				// There are no winners.
				sm = SystemMessage.getSystemMessage(SystemMessageId.AMOUNT_FOR_LOTTERY_S1_IS_S2_ADENA_NO_WINNER);
				sm.addNumber(getId());
				sm.addItemNumber(getPrize());
				Announcements.getInstance().announceToAll(sm);
			}
			
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				statement = con.prepareStatement(UPDATE_LOTTERY);
				statement.setLong(1, getPrize());
				statement.setLong(2, newprize);
				statement.setInt(3, enchant);
				statement.setInt(4, type2);
				statement.setLong(5, prize1);
				statement.setLong(6, prize2);
				statement.setLong(7, prize3);
				statement.setInt(8, getId());
				statement.execute();
				statement.close();
			}
			catch (SQLException e)
			{
				_log.log(Level.WARNING, "Lottery: Could not store finished lottery data: " + e.getMessage(), e);
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			
			class ShoutResult implements Runnable
			{
				final int number;
				final int[] luckynums;
				private ShoutResult(int number, int[] luckynums)
				{
					this.number = number;
					this.luckynums = luckynums;
				}
				private int getId() { return number; }
				@Override
				public void run()
				{
					shout(1000284/*Ç®ÇﬂÇ≈Ç∆Ç§Ç≤Ç¥Ç¢Ç‹Ç∑ÅIëÊ$s1âÒÇÃìñëIî‘çÜÇÕ$s2Ç≈Ç∑ÅB*/
							, String.valueOf(getId())
							, luckynums[0] + " " + luckynums[1] + " " + luckynums[2] + " " + luckynums[3] + " " + luckynums[4] + " ");
				}
			}
			ShoutResult run = new ShoutResult(getId(), luckynums);
			ThreadPoolManager.getInstance().scheduleGeneral(run, 30000);	//19:00:30
			ThreadPoolManager.getInstance().scheduleGeneral(run, 60000);	//19:01:00
			ThreadPoolManager.getInstance().scheduleGeneral(run, 90000);	//19:01:30
			
			ThreadPoolManager.getInstance().scheduleGeneral(new startLottery(), 10 * MINUTE);	//ìyój19:10
			_number++;
			
		}
	}
	
	public int[] decodeNumbers(int enchant, int type2)
	{
		int res[] = new int[5];
		int num = type2 << 16 | enchant;
		int id = 0;
		int nr = 1;
		while (num != 0)
		{
			if ((num & 1) != 0)
				res[id++] = nr;
			num >>>= 1;
			++nr;
		}
		return res;
	}
	
	public long[] checkTicket(L2ItemInstance item)
	{
		return checkTicket(item.getCustomType1(), item.getEnchantLevel(), item.getCustomType2());
	}
	
	public long[] checkTicket(int id, int enchant, int type2)
	{
		long res[] = { 0, 0 };
		
		Connection con = null;
		PreparedStatement statement;
		
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			statement = con.prepareStatement(SELECT_LOTTERY_TICKET);
			statement.setInt(1, id);
			ResultSet rset = statement.executeQuery();
			
			if (rset.next())
			{
				int curenchant = rset.getInt("number1") & enchant;
				int curtype2 = rset.getInt("number2") & type2;
				
				if (curenchant == 0 && curtype2 == 0)
				{
					rset.close();
					statement.close();
					return res;
				}
				
				int count = Integer.bitCount(curtype2 << 16 | curenchant);
				
				switch (count)
				{
					case 0:
						break;
					case 5:
						res[0] = 1;
						res[1] = rset.getLong("prize1");
						break;
					case 4:
						res[0] = 2;
						res[1] = rset.getLong("prize2");
						break;
					case 3:
						res[0] = 3;
						res[1] = rset.getLong("prize3");
						break;
					default:
						res[0] = 4;
						res[1] = 200;
				}
				
				if (Config.DEBUG)
					_log.warning("count: " + count + ", id: " + id + ", enchant: " + enchant + ", type2: " + type2);
			}
			
			rset.close();
			statement.close();
		}
		catch (SQLException e)
		{
			_log.log(Level.WARNING, "Lottery: Could not check lottery ticket #" + id + ": " + e.getMessage(), e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		return res;
	}
	
	private void shout(int npcString, String ... param)
	{
		for (L2Spawn s : SpawnTable.getInstance().getSpawnTable())
		{
			if (s != null && s.getNpcid() >= 30990 && s.getNpcid() <= 30994)
			{
				L2Npc npc = s.getLastSpawn();
				NpcSay packet = new NpcSay(npc.getObjectId(), Say2.SHOUT, npc.getNpcId(), npcString);
				for (String text : param) packet.addStringParameter(text);
				npc.broadcastPacket(packet);
			}
		}
	}
	
	private static class SingletonHolder
	{
		protected static final Lottery _instance = new Lottery();
	}
}
