/*
 * Copyright (C) 2005-2008 L2J_JP / 2008-2013 L2J-SFJP
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
package com.l2jserver.gameserver.util;

import com.l2jserver.gameserver.GameTimeController;

/**
 * @author  JOJO
 */
public class GameTimeFunction
{
	public static final long DAYMILI = 14400*1000;	// ƒQ[ƒ€“àŠÔ‚Ì‚P“ú‚Ì’·‚³‚ğÀŠÔ‚Å‚ ‚ç‚í‚µ‚½[ƒ~ƒŠ•b]
	public static final long TIMEMILI = 600*1000;	// ƒQ[ƒ€“àŠÔ‚Ì‚PŠÔ‚Ì’·‚³‚ğÀŠÔ‚Å‚ ‚ç‚í‚µ‚½[ƒ~ƒŠ•b]
	public static final long MINUTEMILI = 10*1000;	// ƒQ[ƒ€“àŠÔ‚Ì‚P•ª‚Ì’·‚³‚ğÀŠÔ‚Å‚ ‚ç‚í‚µ‚½[ƒ~ƒŠ•b]

	/**
	 * @param hh,mm,ss ƒQ[ƒ€“àŠÔ‚Ì•ª•b
	 * @return ‚±‚ÌŸ‚Ì•ª•b‚Ü‚ÅA‚ ‚Æ‰½ƒ~ƒŠ•b‚©BÀŠÔ‚Å‚Ìƒ~ƒŠ•b‚Å•Ô‚·B
	 */
	public static long timeLeftMili(int hh, int mm, int ss)
	{
		int now = GameTimeController.getInstance().getGameTicks() * 60 / 100;	//ƒQ[ƒ€“àŠÔ[•b]
		int dd = (hh * 3600 + mm * 60 + ss) - (now % 86400);
		if (dd < 0) dd += 86400;

		return (long)dd * 1000 / 6;	//ƒŠƒAƒ‹ŠÔ‚É•ÏŠ·
	}
}
