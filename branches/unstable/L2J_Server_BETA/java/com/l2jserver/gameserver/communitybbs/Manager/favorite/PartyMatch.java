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
package com.l2jserver.gameserver.communitybbs.Manager.favorite;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.instancemanager.CastleManager;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.model.BlockList;
import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Party;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.network.SystemMessageId;
import com.l2jserver.gameserver.network.serverpackets.AskJoinParty;
import com.l2jserver.gameserver.network.serverpackets.NpcHtmlMessage;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;
import com.l2jserver.gameserver.util.TextReplacer;

/**
 * @author  TEN / nanasisaso / TSL / JOJO
 */
public class PartyMatch extends BaseFavoriteManager
{
	private static Logger _log = Logger.getLogger(PartyMatch.class.getName());
	private static PartyMatch _instance = new PartyMatch();

	/**
	 * @return
	 */
	public static PartyMatch getInstance()
	{
		return _instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
				if (activeChar == null) return;
	
				// _bbsgetfav;bbs_party_match;****
	
				String subcmd = getArgs(command);
				if (subcmd.startsWith("_menu"))
				{
					int roomId = getRoomIdByCharId(activeChar.getObjectId());
					if (roomId != -1)
					{
						//参加済みなら参加していた部屋を表示する。
						showPartyRoom(activeChar, roomId);
					}
					else
					{
						String[] values = subcmd.split(" ");
						//未参加ならリストを表示する。
						if (values.length == 3)
							showPartyRoomList(activeChar, values[1], values[2]);
						else
							showPartyRoomList(activeChar, null, null);
					}
				}
				//パーティールーム開設画面
				else if (subcmd.startsWith("_createRoomWindow"))
				{
					showConfigWindow(activeChar, "party_match/createPartyRoom.htm");
					return;
				}
				//_createRoom min max limit lootdist title
				else if (subcmd.startsWith("_createRoom"))
				{
					String[] values = subcmd.split(" *\\| *", 7);
					//項目が揃ってなければエラー
					if (values.length != 7)
					{
						activeChar.sendMessage("パーティー ルームの開設に失敗しました。");
						return;
					}
					else
					{
						try
						{
							int roomId = getRoomIdByCharId(activeChar.getObjectId()); //values[1] is dummy.
							int min = Integer.parseInt(values[2]);
							int max = Integer.parseInt(values[3]);
							int limit = Integer.parseInt(values[4]);
							int lootdist = Integer.parseInt(values[5]);
							String title = values[6];
							if (roomId != -1)
								modifyPartyRoom(activeChar, roomId, min, max, limit, lootdist, title);
							else
								roomId = createPartyRoom(activeChar, min, max, limit, lootdist, title);
							if (roomId > -1)
								showPartyRoom(activeChar, roomId);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("パーティー ルームの開設に失敗しました。");
							return;
						}
					}
				}
				//パーティールーム設定変更画面
				else if (subcmd.startsWith("_modifyRoomWindow"))
				{
					showConfigWindow(activeChar, "party_match/modifyPartyRoom.htm");
					return;
				}
				//_modifyRoom roomid min max limit lootdist title
				else if (subcmd.startsWith("_modifyRoom"))
				{
					String[] values = subcmd.split(" *\\| *", 7);
					//項目が揃ってなければエラー
					if (values.length != 7)
					{
						activeChar.sendMessage("パーティー ルーム設定の変更に失敗しました。");
						return;
					}
					else
					{
						try
						{
							int roomId = Integer.parseInt(values[1]);
							PartyRoomInfo pi = getPartyRoomInfo(roomId);
							int min = values[2].matches("\\d+") ? Integer.parseInt(values[2]) : pi.min;
							int max = values[3].matches("\\d+") ? Integer.parseInt(values[3]) : pi.max;
							int limit = values[4].matches("\\d+") ? Integer.parseInt(values[4]) : pi.limit;
							int lootdist = values[5].matches("\\d+") ? Integer.parseInt(values[5]) : pi.type;
							String title = values[6].length() > 0 ? values[6] : pi.title;
							modifyPartyRoom(activeChar, roomId, min, max, limit, lootdist, title);
							showPartyRoom(activeChar, roomId);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("パーティー ルーム設定の変更に失敗しました。");
							return;
						}
					}
				}
				//_joinRoom roomid
				else if (subcmd.startsWith("_joinRoom"))
				{
					String[] values = subcmd.split(" ");
					//項目が揃ってなければエラー
					if (values.length < 2)
					{
						activeChar.sendMessage("パーティー ルームへの入室に失敗しました。");
						return;
					}
					else
					{
						try
						{
							int roomId = Integer.parseInt(values[1]);
							if (joinPartyRoom(activeChar, roomId))
								showPartyRoom(activeChar, roomId);
							else
							{
								activeChar.sendMessage("パーティー ルームへの入室に失敗しました。");
								return;
							}
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("パーティー ルームへの入室に失敗しました。");
							return;
						}
					}
				}
				//_leaveRoom roomid
				else if (subcmd.startsWith("_leaveRoom"))
				{
					String[] values = subcmd.split(" ");
					//項目が揃ってなければエラー
					if (values.length < 2)
					{
						activeChar.sendMessage("パーティー ルームからの退室に失敗しました。");
						return;
					}
					else
					{
						try
						{
							int roomId = Integer.parseInt(values[1]);
							leavePartyRoom(activeChar, roomId);
							showPartyRoomList(activeChar, null, null);
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("パーティー ルームからの退室に失敗しました。");
							return;
						}
					}
				}
				//_kickRoom roomid char_obj_id
				else if (subcmd.startsWith("_kickRoom"))
				{
					String[] values = subcmd.split(" ");
					//項目が揃ってなければエラー
					if (values.length < 3)
					{
						activeChar.sendMessage("パーティー ルームからの強制退室に失敗しました。");
						return;
					}
					else
					{
						try
						{
							int roomId = Integer.parseInt(values[1]);
							int objectId = Integer.parseInt(values[2]);
							L2PcInstance character = (L2PcInstance)L2World.getInstance().findObject(objectId);
							if (character != null)
								leavePartyRoom(character, roomId);
							return;
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("パーティー ルームからの強制退室に失敗しました。");
							return;
						}
					}
				}
				//_joinParty roomid char_obj_id
				else if (subcmd.startsWith("_joinParty"))
				{
					String[] values = subcmd.split(" ");
					//項目が揃ってなければエラー
					if (values.length < 3)
					{
						activeChar.sendMessage("パーティへの勧誘に失敗しました。");
						return;
					}
					else
					{
						try
						{
							int roomId = Integer.parseInt(values[1]);
							int objectId = Integer.parseInt(values[2]);
							L2PcInstance target = (L2PcInstance)L2World.getInstance().findObject(objectId);
							if (!requestJoinParty(activeChar, target, roomId))
								activeChar.sendMessage("パーティへの勧誘に失敗しました。");
							return;
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("パーティへの勧誘に失敗しました。");
							return;
						}
					}
				}
				else
				{
					separateAndSend("<html><body><br><center> the command: " + command + " is not implemented yet</center><br></body></html>", activeChar);
					return;
				}
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
	}

	//部屋番号の生成
	private synchronized int createPartyRoomId()
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT MAX(id) FROM party_match_list");
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				return result.getInt(1) + 1;
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			return -1;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		return 1;//部屋が1つもない場合は1番から
	}

	//パーティールームを開設する。
	private int createPartyRoom(L2PcInstance activeChar, int min, int max, int limit, int lootdist, String title)
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("INSERT INTO party_match_list VALUES (?,?,?,?,?,?,?,?)");
			int nextId = createPartyRoomId();
			if (nextId == -1)
			{
				activeChar.sendMessage("パーティー ルームの開設に失敗しました。");
				return -1;
			}
			statement.setInt(1, nextId);
			statement.setInt(2, activeChar.getObjectId());
			statement.setInt(3, min);
			statement.setInt(4, max);
			statement.setInt(5, limit);
			int castleIndex = CastleManager.getInstance().findNearestCastleIndex(activeChar);
			if (castleIndex < 0)
				statement.setString(6, "不明");
			else
				statement.setString(6, CastleManager.getInstance().getCastles().get(castleIndex).getLocName());
			statement.setInt(7, lootdist);
//			title = quoteString(title);
			statement.setString(8, title);
			statement.execute();
			statement.close();

			statement = con.prepareStatement("INSERT INTO party_match_room VALUES(?,?,?)");
			statement.setInt(1, nextId);
			statement.setInt(2, activeChar.getObjectId());
			statement.setInt(3, 0);
			statement.execute();
			statement.close();
			activeChar.sendMessage("パーティー ルームを開設しました。");
			return nextId;
		}
		catch (Exception e)
		{
			_log.info("could not create Party Room: " + e);
			activeChar.sendMessage("パーティー ルームの開設に失敗しました。");
			return -1;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	//パーティールームの設定を変更する（リーダーのみ）
	private void modifyPartyRoom(L2PcInstance activeChar, int roomId, int min, int max, int limit, int lootdist, String title)
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("UPDATE party_match_list SET min = ?, max = ?, `limit` = ?, loc = ?, type = ?, title = ? WHERE id = ?");
			statement.setInt(1, min);
			statement.setInt(2, max);
			statement.setInt(3, limit);
			int castleIndex = CastleManager.getInstance().findNearestCastleIndex(activeChar);
			if (castleIndex < 0)
				statement.setString(4, "不明");
			else
				statement.setString(4, CastleManager.getInstance().getCastles().get(castleIndex).getLocName());
			statement.setInt(5, lootdist);
//			title = quoteString(title);
			statement.setString(6, title);
			statement.setInt(7, roomId);
			statement.execute();
			statement.close();
			/*
			 * この部分に、変更後の条件を満たさないキャラクターの退室処理が必要かも
			 *
			 */
		}
		catch (Exception e)
		{
			_log.info("could not modify Party Room: " + e);
			activeChar.sendMessage("パーティー ルーム設定の変更に失敗しました。");
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		brodeCastRoomInfo(activeChar, roomId);
		activeChar.sendMessage("パーティー ルーム設定を変更しました。");
	}

	//パーティールームに入室する。
	private boolean joinPartyRoom(L2PcInstance activeChar, int roomId)
	{
		PartyRoomInfo pi = getPartyRoomInfo(roomId);
		if (pi.limit == -1 || pi.count == -1)
		{
			activeChar.sendMessage("ルームが見つかりません。");
			return false;
		}
		else if (pi.limit <= pi.count)
		{
			activeChar.sendMessage("人数の上限に達しているため、入室できません。");
			return false;
		}
		else if (activeChar.getLevel() < pi.min || pi.max < activeChar.getLevel())
		{
			activeChar.sendMessage("レベル制限に合っていないため、入室できません。");
			return false;
		}
		else
		{
			java.sql.Connection con = null;
			try
			{
				con = L2DatabaseFactory.getInstance().getConnection();
				PreparedStatement statement = con.prepareStatement("INSERT INTO party_match_room VALUES(?,?,?)");
				statement.setInt(1, roomId);
				statement.setInt(2, activeChar.getObjectId());
				if (activeChar.getParty() != null && activeChar.getParty() == ((L2PcInstance)L2World.getInstance().findObject(pi.owner_id)).getParty())
					statement.setInt(3, 1);
				else
					statement.setInt(3, 2);
				statement.execute();
				statement.close();
			}
			catch (Exception e)
			{
				_log.info("could not join Party Room: " + e);
				activeChar.sendMessage("パーティー ルームへの入室に失敗しました。");
				return false;
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			brodeCastRoomInfo(activeChar, roomId);
			activeChar.sendMessage("パーティー ルームへ入室しました。");
		}
		return true;
	}

	//パーティールームから退室する
	private void leavePartyRoom(L2PcInstance activeChar, int roomId)
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT owner_id FROM party_match_list WHERE id = ?");
			statement.setInt(1, roomId);
			ResultSet result = statement.executeQuery();
			int owner_id = 0;
			while (result.next())
			{
				owner_id = result.getInt(1);
			}
			result.close();
			statement.close();

			//退室者がオーナーだった場合、部屋を閉じる
			if (owner_id == activeChar.getObjectId())
			{
				statement = con.prepareStatement("SELECT char_obj_id FROM party_match_room WHERE room_id = ?");
				statement.setInt(1, roomId);
				result = statement.executeQuery();
				while (result.next())
				{
					int object_id = result.getInt(1);
					L2PcInstance member = (L2PcInstance)L2World.getInstance().findObject(object_id);
					if (member != null)
					{
						showPartyRoomList(member, null, null);
						member.sendMessage("パーティー ルームが解散されました。");
					}
				}
				result.close();
				statement.close();

				statement = con.prepareStatement("DELETE FROM party_match_list WHERE id = ?");
				statement.setInt(1, roomId);
				statement.execute();
				statement.close();
				statement = con.prepareStatement("DELETE FROM party_match_room WHERE room_id = ?");
				statement.setInt(1, roomId);
				statement.execute();
				statement.close();
			}
			else
			{
				statement = con.prepareStatement("SELECT char_obj_id FROM party_match_room WHERE room_id = ?");
				statement.setInt(1, roomId);
				result = statement.executeQuery();
				while (result.next())
				{
					int object_id = result.getInt(1);
					L2PcInstance member = (L2PcInstance)L2World.getInstance().findObject(object_id);
					if (member != null)
					{
						if (object_id == activeChar.getObjectId())
						{
							showPartyRoomList(member, null, null);
							member.sendMessage("パーティールームから退室しました。");
						}
						else
						{
							showPartyRoom(member, roomId);
							member.sendMessage(activeChar.getName() + "が退室しました。");
						}
					}
				}
				result.close();
				statement.close();

				statement = con.prepareStatement("DELETE FROM party_match_room WHERE room_id = ? AND char_obj_id = ?");
				statement.setInt(1, roomId);
				statement.setInt(2, activeChar.getObjectId());
				statement.execute();
				statement.close();
			}
		}
		catch (Exception e)
		{
			_log.info("could not delete Party Room: " + e);
			activeChar.sendMessage("パーティー ルームの終了に失敗しました。");
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		brodeCastRoomInfo(activeChar, roomId);
		activeChar.sendMessage("パーティー ルームを終了しました。");
	}

	//パーティーに勧誘する
	private boolean requestJoinParty(L2PcInstance requestor, L2PcInstance target, int roomId)
	{
		PartyRoomInfo pi = getPartyRoomInfo(roomId);

		//////////////////////////////////////////////////////////////////////
		// ここから: RequestJoinParty.java#runImpl() をほぼ丸写し
		if (requestor == null)
			return false;

		if (target == null)
		{
			requestor.sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
			return false;
		}

		if (target.getAppearance().getInvisible())
		{
			requestor.sendPacket(SystemMessageId.TARGET_IS_INCORRECT);
			return false;
		}

		if (target.isInParty())
		{
			SystemMessage msg = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_ALREADY_IN_PARTY);
			msg.addString(target.getName());
			requestor.sendPacket(msg);
			return false;
		}

		if (BlockList.isBlocked(target, requestor))
		{
			SystemMessage sm = SystemMessage.getSystemMessage(SystemMessageId.S1_HAS_ADDED_YOU_TO_IGNORE_LIST);
			sm.addCharName(target);
			requestor.sendPacket(sm);
			return false;
		}

		if (target == requestor)
		{
			requestor.sendPacket(SystemMessageId.INCORRECT_TARGET);
			return false;
		}

		if (target.isCursedWeaponEquipped() || requestor.isCursedWeaponEquipped())
		{
			requestor.sendPacket(SystemMessageId.INCORRECT_TARGET);
			return false;
		}

		if (target.isInJail() || requestor.isInJail())
		{
			requestor.sendMessage("Player is in Jail");
			return false;
		}

		if (target.isInOlympiadMode() || requestor.isInOlympiadMode())
			return false;
		// ここまで
		//////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////
		// ここから: RequestJoinParty.java#addTargetToParty() をほぼ丸写し
		SystemMessage msg;

		// summary of ppl already in party and ppl that get invitation
		if (!requestor.getParty().isLeader(requestor))
		{
			requestor.sendPacket(SystemMessageId.ONLY_LEADER_CAN_INVITE);
			return false;
		}
		if (requestor.getParty() != null && requestor.getParty().getMemberCount() >= 9)
//		if (requestor.getParty() != null && requestor.getParty().getMemberCount() + requestor.getParty().getPendingInvitationNumber() >= 9)
		{
			requestor.sendPacket(SystemMessageId.PARTY_FULL);
			return false;
		}
		if (requestor.getParty().getPendingInvitation())
		{
			requestor.sendPacket(SystemMessageId.WAITING_FOR_ANOTHER_REPLY);
			return false;
		}

		if (!target.isProcessingRequest())
		{
			if (requestor.getParty() == null)
				requestor.setParty(new L2Party(requestor, pi.type));
			requestor.onTransactionRequest(target);
			target.sendPacket(new AskJoinParty(requestor.getName(), pi.type));
			requestor.getParty().setPendingInvitation(true);
//			requestor.getParty().increasePendingInvitationNumber();
			
			if (Config.DEBUG)
				_log.fine("sent out a party invitation to:"+target.getName());
		}
		else
		{
			msg = SystemMessage.getSystemMessage(SystemMessageId.C1_IS_BUSY_TRY_LATER);
			msg.addString(target.getName());
			requestor.sendPacket(msg);
			
			if (Config.DEBUG)
				_log.warning(requestor.getName() + " already received a party invitation");
		}
		// ここまで
		//////////////////////////////////////////////////////////////////////

		return true;
	}

	//パーティールームリストを表示する。
	private void showPartyRoomList(L2PcInstance activeChar, String optionArea, String optionLevel)
	{
		String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/party_match/partyRoomList.htm");

		StringBuilder where_str = new StringBuilder();
		if (optionArea != null && !optionArea.equals("全体"))
		{
			where_str.append(" loc = '" + optionArea + "'");
		}
		if (optionLevel != null && !optionLevel.equals("全体"))
		{
			String[]v = optionLevel.split("-");
			int minlevel = Integer.parseInt(v[0]);
			int maxlevel = Integer.parseInt(v[1]);
			if (where_str.length() != 0)
				where_str.append(" AND");
			where_str.append(" min <= '" + minlevel + "' AND max >= '" + maxlevel + "'");
		}
		if (where_str.length() != 0)
			where_str.append(" WHERE" + where_str);
		where_str.append(" ORDER BY id");
		StringBuilder roomList = new StringBuilder();
		StringBuilder DelRoom = new StringBuilder();
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT id FROM party_match_list" + where_str);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				PartyRoomInfo pi = getPartyRoomInfo(result.getInt(1));
				L2PcInstance leader = (L2PcInstance)L2World.getInstance().findObject(pi.owner_id);
				if (leader == null)
				{
					if (DelRoom.length() != 0)
						DelRoom.append(",");
					DelRoom.append(pi.roomId);
				}
				else if (pi.roomId != -1 && pi.owner_id != -1)
				{
					roomList.append("<table width=610><tr>");
					roomList.append("<td width=30 align=center>" + pi.roomId + "</td>");
					roomList.append("<td width=160>" + pi.title + "</td>");
					roomList.append("<td width=120>" + leader.getName() + "</td>");
					roomList.append("<td width=120>" + pi.loc + "</td>");
					roomList.append("<td width=50 align=center>" + pi.min + " - " + pi.max + "</td>");
					roomList.append("<td width=50 align=center>" + pi.count + " / " + pi.limit + "</td>");
					roomList.append("<td width=80 align=center><button value=\"入室\" action=\"bypass _bbsgetfav;bbs_party_match;_joinRoom " + pi.roomId + "\" width=65 height=20 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\"></td>");
					roomList.append("</tr></table>");
				}
			}
			result.close();
			statement.close();

			if (DelRoom.length() != 0)
			{
				statement = con.prepareStatement("DELETE FROM party_match_list WHERE id in (?)");
				statement.setString(1, DelRoom.toString());
				statement.execute();
				statement.close();
				statement = con.prepareStatement("DELETE FROM party_match_room WHERE room_id in (?)");
				statement.setString(1, DelRoom.toString());
				statement.execute();
				statement.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			separateAndSend("<html><body><br><center>パーティールームリスト作成中にエラーが発生しました。</center><br></body></html>", activeChar);
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		html = html.replace("%roomList%", roomList);//ルームリスト
		separateAndSend(html.toString(), activeChar);
	}

	//パーティールームを表示する。
	private void showPartyRoom(L2PcInstance activeChar, int roomId)
	{
		PartyRoomInfo pi = getPartyRoomInfo(roomId);
		if (pi.roomId == -1 && pi.owner_id == -1)
			return;
		StringBuilder memberList = new StringBuilder();
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT * FROM party_match_room WHERE room_id = ? ORDER BY memberType");
			statement.setInt(1, roomId);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				L2Object obj = L2World.getInstance().findObject(result.getInt(2));
				if (obj != null && obj instanceof L2PcInstance)
				{
					L2PcInstance member = (L2PcInstance)obj;
					memberList.append("<table width=610><tr>");
					memberList.append("<td width=120>" + member.getName() + "</td>");
					memberList.append("<td width=130 align=center>" + member.getTemplate().getClassNameHtm() + "</td>");
					memberList.append("<td width=40 align=center>" + member.getLevel() + "</td>");
					memberList.append("<td width=130 align=center>" + MapRegionManager.getInstance().getClosestTownName(member) + "</td>");
					if (result.getInt(3) == 0)
					{
						memberList.append("<td width=120 align=center>ルームリーダー</td>");
					}
					else if (result.getInt(3) == 1)
					{
						memberList.append("<td width=120 align=center>パーティーメンバ</td>");
					}
					else
					{
						memberList.append("<td width=120 align=center>パーティー希望者</td>");
					}
					if (activeChar.getObjectId() == pi.owner_id && activeChar.getObjectId() != member.getObjectId())
					{
						memberList.append("<td width=70 align=center>");
						if (result.getInt(3) == 2)
							memberList.append("<button value=\"ＰＴ勧誘\" action=\"bypass _bbsgetfav;bbs_party_match;_joinParty " + roomId + " " + member.getObjectId() + "\" width=65 height=20 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\">");
						memberList.append("<button value=\"強制追放\" action=\"bypass _bbsgetfav;bbs_party_match;_kickRoom " + roomId + " " + member.getObjectId() + "\" width=65 height=20 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\">");
						memberList.append("</td>");
					}
					else
						memberList.append("<td width=70 align=center></td>");
					memberList.append("</tr></table>");
				}
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			separateAndSend("<html><body><br><center>パーティールーム作成中にエラーが発生しました。</center><br></body></html>", activeChar);
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		TextReplacer html = new TextReplacer(HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/party_match/partyRoom.htm"));
		html.replace("%roomId%", pi.roomId);	//部屋番号
		html.replace("%title%", htmlescape(pi.title));	//題目
		html.replace("%location%", pi.loc);	//場所
		html.replace("%current%", pi.count);	//現在の人数
		html.replace("%max%", pi.limit);	//上限の人数
		String lootdistribution = "";
		switch (pi.type)
		{
			case L2Party.ITEM_LOOTER:
				lootdistribution = "拾った人が所有";
				break;
			case L2Party.ITEM_RANDOM:
				lootdistribution = "ランダムに分配";
				break;
			case L2Party.ITEM_RANDOM_SPOIL:
				lootdistribution = "スポイルを含めランダムに";
				break;
			case L2Party.ITEM_ORDER:
				lootdistribution = "順番に取得";
				break;
			case L2Party.ITEM_ORDER_SPOIL:
				lootdistribution = "スポイルを含め順番に";
				break;
		}
		html.replace("%rootType%", lootdistribution);	//タイプ
		html.replace("%minlevel%", pi.min);	//最低レベル
		html.replace("%maxlevel%", pi.max);	//最高レベル
		html.replace("%memberList%", memberList);	//メンバーリスト
		String modifyButton = "";
		if (activeChar.getObjectId() == pi.owner_id)
			modifyButton = "<button value=\"ルーム設定\" action=\"bypass _bbsgetfav;bbs_party_match;_modifyRoomWindow\" width=80 height=22 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\">";
		html.replace("%modifyPartyRoom%", modifyButton);//設定変更ボタン

		separateAndSend(html.toString(), activeChar);
	}

	//設定ウィンドウの表示
	private void showConfigWindow(L2PcInstance activeChar, String filename)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/" + filename);
		html.replace("%roomId%", getRoomIdByCharId(activeChar.getObjectId()));//部屋番号
		activeChar.sendPacket(html);
	}

	//キャラクターが参加している部屋のIDを返す（未所属は-1）
	public int getRoomIdByCharId(int char_obj_id)
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT room_id FROM party_match_room WHERE char_obj_id = ?");
			statement.setInt(1, char_obj_id);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				return result.getInt(1);
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			return -1;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		return -1;
	}

	class PartyRoomInfo {
		int roomId;			//部屋番号
		int owner_id = 0;	//主催者ID
		int min = 1;		//最低レベル
		int max = 100;		//最高レベル
		int limit = -1;		//上限
		String loc = "";	//パーティの位置
		int type = 0;		//拾った人、ランダム
		String title = "";	//題目
		int count = -1;		//現在の人数
		
		PartyRoomInfo(int _roomId) { this.roomId = _roomId; }
	}

	//パーティールームの設定・情報を返す
	public PartyRoomInfo getPartyRoomInfo(int roomId)
	{
		PartyRoomInfo pi = new PartyRoomInfo(roomId);

		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement = con.prepareStatement("SELECT owner_id, min, max, `limit`, loc, type, title FROM party_match_list WHERE id = ?");
			statement.setInt(1, roomId);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				pi.owner_id = result.getInt(1);	//主催者ID
				pi.min = result.getInt(2);		//最低レベル
				pi.max = result.getInt(3);		//最高レベル
				pi.limit = result.getInt(4);	//上限
				pi.loc = result.getString(5);	//パーティの位置
				pi.type = result.getInt(6);		//拾った人、ランダム
				pi.title = result.getString(7);	//題目
			}
			result.close();
			statement.close();

			statement = con.prepareStatement("SELECT COUNT(*) FROM party_match_room WHERE room_id = ?");
			statement.setInt(1, roomId);
			result = statement.executeQuery();
			while (result.next())
			{
				pi.count = result.getInt(1);	//現在の人数
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return pi;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		return pi;
	}

	//パーティールームの状況が変化したら、メンバー全員に送信する。
	public void brodeCastRoomInfo(L2PcInstance activeChar, int roomId)
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();

			PreparedStatement statement = con.prepareStatement("SELECT char_obj_id, memberType FROM party_match_room WHERE room_id = ?");
			statement.setInt(1, roomId);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				int object_id = result.getInt(1);
				int member_type = result.getInt(2);
				L2PcInstance member = (L2PcInstance)L2World.getInstance().findObject(object_id);
				if (member != null)
				{
					if (member_type != 0)
					{
						PreparedStatement statement2 = con.prepareStatement("UPDATE party_match_room SET memberType = ? WHERE room_id = ? AND char_obj_id = ?");
						statement2.setInt(1, member.isInParty() ? 1 : 2);
						statement2.setInt(2, roomId);
						statement2.setInt(3, member.getObjectId());
						statement2.executeUpdate();
						statement2.close();
					}

					showPartyRoom(member, roomId);
				}
			}
			result.close();
			statement.close();
		}
		catch (Exception e)
		{
			_log.info("could not update Party Room Info: " + e);
			activeChar.sendMessage("パーティー ルーム情報の更新に失敗しました。");
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
}