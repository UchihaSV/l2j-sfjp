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
package com.l2jserver.gameserver.script;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Luis Arias
 */
public class DateRange
{
	protected static final Logger _log = Logger.getLogger(DateRange.class.getName());
	private final Date _startDate, _endDate;
	
	public DateRange(Date from, Date to)
	{
		_startDate = from;
		_endDate = to;
	}
	
	private static SimpleDateFormat format_date_short_JP = new SimpleDateFormat("yyyy/MM/dd", Locale.JAPAN);
	private static SimpleDateFormat format_date_long_JP = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN);
	
	public static DateRange parse(String dateRange, DateFormat format)
	{
		String[] date = dateRange.split("-");
		if (date.length == 2 && date[0].contains("****") && date[1].contains("****"))
		{
			Calendar now = Calendar.getInstance();
			String year = String.valueOf(now.get(Calendar.YEAR));
			date[0] = date[0].replace("****", year);
			date[1] = date[1].replace("****", year);
			DateRange range = _parse(date[0] + "-" + date[1], format);
			if (range.getStartDate() == null || range.getEndDate() == null)
				return range; // Invalid
			
			GregorianCalendar startDate = new GregorianCalendar();
			GregorianCalendar endDate = new GregorianCalendar();
			startDate.setTime(range.getStartDate());
			endDate.setTime(range.getEndDate());
			if (startDate.after(endDate))
			{
				startDate.add(Calendar.YEAR, -1);
			}
			if (now.after(endDate))
			{
				startDate.add(Calendar.YEAR, 1);
				endDate.add(Calendar.YEAR, 1);
			}
			return new DateRange(startDate.getTime(), endDate.getTime());
		}
		return _parse(dateRange, format);
	}
	
	private static DateRange _parse(String dateRange, DateFormat format)
	{
		String[] date = dateRange.split("-");
		if (date.length == 2)
		{
			final DateFormat format0 =
				  date[0].matches("\\d{4}/\\d{1,2}/\\d{1,2}") ? format_date_short_JP
				: date[0].matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}") ? format_date_long_JP
				: format;
			final DateFormat format1 =
				  date[1].matches("\\d{4}/\\d{1,2}/\\d{1,2}") ? format_date_short_JP
				: date[1].matches("\\d{4}/\\d{1,2}/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}") ? format_date_long_JP
				: format;
			
			try
			{
				Date start, end;
				synchronized (format0) { start = format0.parse(date[0]); }
				synchronized (format1) { end = format1.parse(date[1]); }
				return new DateRange(start, end);
			}
			catch (ParseException e)
			{
				_log.log(Level.WARNING, "Invalid Date Format.", e);
			}
		}
		return new DateRange(null, null);
	}
	
	public boolean isValid()
	{
		return (_startDate != null) && (_endDate != null) && _startDate.before(_endDate);
	}
	
	public boolean isWithinRange(Date date)
	{
		return date.after(_startDate) && date.before(_endDate);
	}
	
	public Date getEndDate()
	{
		return _endDate;
	}
	
	public Date getStartDate()
	{
		return _startDate;
	}
	
	@Override
	public String toString()
	{
		return "DateRange: From: " + getStartDate() + " To: " + getEndDate();
	}
}
