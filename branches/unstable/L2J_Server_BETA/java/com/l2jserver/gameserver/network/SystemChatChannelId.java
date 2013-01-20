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
package com.l2jserver.gameserver.network;

/**
 *
 * @author  JOJO
 */
public final class SystemChatChannelId
{
	public static final int
			 Chat_Normal			=  0	//   <white>
			,Chat_Shout				=  1	// ! <dark orange>
			,Chat_Tell				=  2	// " <purple>
			,Chat_Party				=  3	// # <green>
			,Chat_Clan				=  4	// @ <blue/purple>
			,Chat_System			=  5	// (
			,Chat_User_Pet			=  6	// *
			,Chat_GM_Pet			=  7	// *
			,Chat_Market			=  8	// + <pink>
			,Chat_Alliance			=  9	// $ <light green>
			,Chat_Announce			= 10	//   <light cyan>
			,Chat_Custom			= 11	// --> Crashes client
			,Chat_L2Friend			= 12	//
			,Chat_MSN				= 13	//
			,Chat_Party_Room		= 14	//
			,Chat_Commander			= 15	//
			,Chat_Inner_Partymaster	= 16	//
			,Chat_Hero				= 17	// % <blue>
			,Chat_Critical_Announce = 18	//   <dark cyan>
		;
}
