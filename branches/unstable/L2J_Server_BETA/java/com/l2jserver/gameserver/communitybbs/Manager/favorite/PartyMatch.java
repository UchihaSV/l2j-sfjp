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
						//�Q���ς݂Ȃ�Q�����Ă���������\������B
						showPartyRoom(activeChar, roomId);
					}
					else
					{
						String[] values = subcmd.split(" ");
						//���Q���Ȃ烊�X�g��\������B
						if (values.length == 3)
							showPartyRoomList(activeChar, values[1], values[2]);
						else
							showPartyRoomList(activeChar, null, null);
					}
				}
				//�p�[�e�B�[���[���J�݉��
				else if (subcmd.startsWith("_createRoomWindow"))
				{
					showConfigWindow(activeChar, "party_match/createPartyRoom.htm");
					return;
				}
				//_createRoom min max limit lootdist title
				else if (subcmd.startsWith("_createRoom"))
				{
					String[] values = subcmd.split(" *\\| *", 7);
					//���ڂ������ĂȂ���΃G���[
					if (values.length != 7)
					{
						activeChar.sendMessage("�p�[�e�B�[ ���[���̊J�݂Ɏ��s���܂����B");
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
							activeChar.sendMessage("�p�[�e�B�[ ���[���̊J�݂Ɏ��s���܂����B");
							return;
						}
					}
				}
				//�p�[�e�B�[���[���ݒ�ύX���
				else if (subcmd.startsWith("_modifyRoomWindow"))
				{
					showConfigWindow(activeChar, "party_match/modifyPartyRoom.htm");
					return;
				}
				//_modifyRoom roomid min max limit lootdist title
				else if (subcmd.startsWith("_modifyRoom"))
				{
					String[] values = subcmd.split(" *\\| *", 7);
					//���ڂ������ĂȂ���΃G���[
					if (values.length != 7)
					{
						activeChar.sendMessage("�p�[�e�B�[ ���[���ݒ�̕ύX�Ɏ��s���܂����B");
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
							activeChar.sendMessage("�p�[�e�B�[ ���[���ݒ�̕ύX�Ɏ��s���܂����B");
							return;
						}
					}
				}
				//_joinRoom roomid
				else if (subcmd.startsWith("_joinRoom"))
				{
					String[] values = subcmd.split(" ");
					//���ڂ������ĂȂ���΃G���[
					if (values.length < 2)
					{
						activeChar.sendMessage("�p�[�e�B�[ ���[���ւ̓����Ɏ��s���܂����B");
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
								activeChar.sendMessage("�p�[�e�B�[ ���[���ւ̓����Ɏ��s���܂����B");
								return;
							}
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("�p�[�e�B�[ ���[���ւ̓����Ɏ��s���܂����B");
							return;
						}
					}
				}
				//_leaveRoom roomid
				else if (subcmd.startsWith("_leaveRoom"))
				{
					String[] values = subcmd.split(" ");
					//���ڂ������ĂȂ���΃G���[
					if (values.length < 2)
					{
						activeChar.sendMessage("�p�[�e�B�[ ���[������̑ގ��Ɏ��s���܂����B");
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
							activeChar.sendMessage("�p�[�e�B�[ ���[������̑ގ��Ɏ��s���܂����B");
							return;
						}
					}
				}
				//_kickRoom roomid char_obj_id
				else if (subcmd.startsWith("_kickRoom"))
				{
					String[] values = subcmd.split(" ");
					//���ڂ������ĂȂ���΃G���[
					if (values.length < 3)
					{
						activeChar.sendMessage("�p�[�e�B�[ ���[������̋����ގ��Ɏ��s���܂����B");
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
							activeChar.sendMessage("�p�[�e�B�[ ���[������̋����ގ��Ɏ��s���܂����B");
							return;
						}
					}
				}
				//_joinParty roomid char_obj_id
				else if (subcmd.startsWith("_joinParty"))
				{
					String[] values = subcmd.split(" ");
					//���ڂ������ĂȂ���΃G���[
					if (values.length < 3)
					{
						activeChar.sendMessage("�p�[�e�B�ւ̊��U�Ɏ��s���܂����B");
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
								activeChar.sendMessage("�p�[�e�B�ւ̊��U�Ɏ��s���܂����B");
							return;
						}
						catch (Exception e)
						{
							e.printStackTrace();
							activeChar.sendMessage("�p�[�e�B�ւ̊��U�Ɏ��s���܂����B");
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

	//�����ԍ��̐���
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
		return 1;//������1���Ȃ��ꍇ��1�Ԃ���
	}

	//�p�[�e�B�[���[�����J�݂���B
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
				activeChar.sendMessage("�p�[�e�B�[ ���[���̊J�݂Ɏ��s���܂����B");
				return -1;
			}
			statement.setInt(1, nextId);
			statement.setInt(2, activeChar.getObjectId());
			statement.setInt(3, min);
			statement.setInt(4, max);
			statement.setInt(5, limit);
			int castleIndex = CastleManager.getInstance().findNearestCastleIndex(activeChar);
			if (castleIndex < 0)
				statement.setString(6, "�s��");
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
			activeChar.sendMessage("�p�[�e�B�[ ���[�����J�݂��܂����B");
			return nextId;
		}
		catch (Exception e)
		{
			_log.info("could not create Party Room: " + e);
			activeChar.sendMessage("�p�[�e�B�[ ���[���̊J�݂Ɏ��s���܂����B");
			return -1;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}

	//�p�[�e�B�[���[���̐ݒ��ύX����i���[�_�[�̂݁j
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
				statement.setString(4, "�s��");
			else
				statement.setString(4, CastleManager.getInstance().getCastles().get(castleIndex).getLocName());
			statement.setInt(5, lootdist);
//			title = quoteString(title);
			statement.setString(6, title);
			statement.setInt(7, roomId);
			statement.execute();
			statement.close();
			/*
			 * ���̕����ɁA�ύX��̏����𖞂����Ȃ��L�����N�^�[�̑ގ��������K�v����
			 *
			 */
		}
		catch (Exception e)
		{
			_log.info("could not modify Party Room: " + e);
			activeChar.sendMessage("�p�[�e�B�[ ���[���ݒ�̕ύX�Ɏ��s���܂����B");
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		brodeCastRoomInfo(activeChar, roomId);
		activeChar.sendMessage("�p�[�e�B�[ ���[���ݒ��ύX���܂����B");
	}

	//�p�[�e�B�[���[���ɓ�������B
	private boolean joinPartyRoom(L2PcInstance activeChar, int roomId)
	{
		PartyRoomInfo pi = getPartyRoomInfo(roomId);
		if (pi.limit == -1 || pi.count == -1)
		{
			activeChar.sendMessage("���[����������܂���B");
			return false;
		}
		else if (pi.limit <= pi.count)
		{
			activeChar.sendMessage("�l���̏���ɒB���Ă��邽�߁A�����ł��܂���B");
			return false;
		}
		else if (activeChar.getLevel() < pi.min || pi.max < activeChar.getLevel())
		{
			activeChar.sendMessage("���x�������ɍ����Ă��Ȃ����߁A�����ł��܂���B");
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
				activeChar.sendMessage("�p�[�e�B�[ ���[���ւ̓����Ɏ��s���܂����B");
				return false;
			}
			finally
			{
				L2DatabaseFactory.close(con);
			}
			brodeCastRoomInfo(activeChar, roomId);
			activeChar.sendMessage("�p�[�e�B�[ ���[���֓������܂����B");
		}
		return true;
	}

	//�p�[�e�B�[���[������ގ�����
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

			//�ގ��҂��I�[�i�[�������ꍇ�A���������
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
						member.sendMessage("�p�[�e�B�[ ���[�������U����܂����B");
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
							member.sendMessage("�p�[�e�B�[���[������ގ����܂����B");
						}
						else
						{
							showPartyRoom(member, roomId);
							member.sendMessage(activeChar.getName() + "���ގ����܂����B");
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
			activeChar.sendMessage("�p�[�e�B�[ ���[���̏I���Ɏ��s���܂����B");
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
		brodeCastRoomInfo(activeChar, roomId);
		activeChar.sendMessage("�p�[�e�B�[ ���[�����I�����܂����B");
	}

	//�p�[�e�B�[�Ɋ��U����
	private boolean requestJoinParty(L2PcInstance requestor, L2PcInstance target, int roomId)
	{
		PartyRoomInfo pi = getPartyRoomInfo(roomId);

		//////////////////////////////////////////////////////////////////////
		// ��������: RequestJoinParty.java#runImpl() ���قڊێʂ�
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
		// �����܂�
		//////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////
		// ��������: RequestJoinParty.java#addTargetToParty() ���قڊێʂ�
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
		// �����܂�
		//////////////////////////////////////////////////////////////////////

		return true;
	}

	//�p�[�e�B�[���[�����X�g��\������B
	private void showPartyRoomList(L2PcInstance activeChar, String optionArea, String optionLevel)
	{
		String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/party_match/partyRoomList.htm");

		StringBuilder where_str = new StringBuilder();
		if (optionArea != null && !optionArea.equals("�S��"))
		{
			where_str.append(" loc = '" + optionArea + "'");
		}
		if (optionLevel != null && !optionLevel.equals("�S��"))
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
					roomList.append("<td width=80 align=center><button value=\"����\" action=\"bypass _bbsgetfav;bbs_party_match;_joinRoom " + pi.roomId + "\" width=65 height=20 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\"></td>");
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
			separateAndSend("<html><body><br><center>�p�[�e�B�[���[�����X�g�쐬���ɃG���[���������܂����B</center><br></body></html>", activeChar);
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		html = html.replace("%roomList%", roomList);//���[�����X�g
		separateAndSend(html.toString(), activeChar);
	}

	//�p�[�e�B�[���[����\������B
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
						memberList.append("<td width=120 align=center>���[�����[�_�[</td>");
					}
					else if (result.getInt(3) == 1)
					{
						memberList.append("<td width=120 align=center>�p�[�e�B�[�����o</td>");
					}
					else
					{
						memberList.append("<td width=120 align=center>�p�[�e�B�[��]��</td>");
					}
					if (activeChar.getObjectId() == pi.owner_id && activeChar.getObjectId() != member.getObjectId())
					{
						memberList.append("<td width=70 align=center>");
						if (result.getInt(3) == 2)
							memberList.append("<button value=\"�o�s���U\" action=\"bypass _bbsgetfav;bbs_party_match;_joinParty " + roomId + " " + member.getObjectId() + "\" width=65 height=20 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\">");
						memberList.append("<button value=\"�����Ǖ�\" action=\"bypass _bbsgetfav;bbs_party_match;_kickRoom " + roomId + " " + member.getObjectId() + "\" width=65 height=20 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\">");
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
			separateAndSend("<html><body><br><center>�p�[�e�B�[���[���쐬���ɃG���[���������܂����B</center><br></body></html>", activeChar);
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}

		TextReplacer html = new TextReplacer(HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/party_match/partyRoom.htm"));
		html.replace("%roomId%", pi.roomId);	//�����ԍ�
		html.replace("%title%", htmlescape(pi.title));	//���
		html.replace("%location%", pi.loc);	//�ꏊ
		html.replace("%current%", pi.count);	//���݂̐l��
		html.replace("%max%", pi.limit);	//����̐l��
		String lootdistribution = "";
		switch (pi.type)
		{
			case L2Party.ITEM_LOOTER:
				lootdistribution = "�E�����l�����L";
				break;
			case L2Party.ITEM_RANDOM:
				lootdistribution = "�����_���ɕ��z";
				break;
			case L2Party.ITEM_RANDOM_SPOIL:
				lootdistribution = "�X�|�C�����܂߃����_����";
				break;
			case L2Party.ITEM_ORDER:
				lootdistribution = "���ԂɎ擾";
				break;
			case L2Party.ITEM_ORDER_SPOIL:
				lootdistribution = "�X�|�C�����܂ߏ��Ԃ�";
				break;
		}
		html.replace("%rootType%", lootdistribution);	//�^�C�v
		html.replace("%minlevel%", pi.min);	//�Œ჌�x��
		html.replace("%maxlevel%", pi.max);	//�ō����x��
		html.replace("%memberList%", memberList);	//�����o�[���X�g
		String modifyButton = "";
		if (activeChar.getObjectId() == pi.owner_id)
			modifyButton = "<button value=\"���[���ݒ�\" action=\"bypass _bbsgetfav;bbs_party_match;_modifyRoomWindow\" width=80 height=22 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\">";
		html.replace("%modifyPartyRoom%", modifyButton);//�ݒ�ύX�{�^��

		separateAndSend(html.toString(), activeChar);
	}

	//�ݒ�E�B���h�E�̕\��
	private void showConfigWindow(L2PcInstance activeChar, String filename)
	{
		NpcHtmlMessage html = new NpcHtmlMessage(5);
		html.setFile(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/" + filename);
		html.replace("%roomId%", getRoomIdByCharId(activeChar.getObjectId()));//�����ԍ�
		activeChar.sendPacket(html);
	}

	//�L�����N�^�[���Q�����Ă��镔����ID��Ԃ��i��������-1�j
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
		int roomId;			//�����ԍ�
		int owner_id = 0;	//��Î�ID
		int min = 1;		//�Œ჌�x��
		int max = 100;		//�ō����x��
		int limit = -1;		//���
		String loc = "";	//�p�[�e�B�̈ʒu
		int type = 0;		//�E�����l�A�����_��
		String title = "";	//���
		int count = -1;		//���݂̐l��
		
		PartyRoomInfo(int _roomId) { this.roomId = _roomId; }
	}

	//�p�[�e�B�[���[���̐ݒ�E����Ԃ�
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
				pi.owner_id = result.getInt(1);	//��Î�ID
				pi.min = result.getInt(2);		//�Œ჌�x��
				pi.max = result.getInt(3);		//�ō����x��
				pi.limit = result.getInt(4);	//���
				pi.loc = result.getString(5);	//�p�[�e�B�̈ʒu
				pi.type = result.getInt(6);		//�E�����l�A�����_��
				pi.title = result.getString(7);	//���
			}
			result.close();
			statement.close();

			statement = con.prepareStatement("SELECT COUNT(*) FROM party_match_room WHERE room_id = ?");
			statement.setInt(1, roomId);
			result = statement.executeQuery();
			while (result.next())
			{
				pi.count = result.getInt(1);	//���݂̐l��
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

	//�p�[�e�B�[���[���̏󋵂��ω�������A�����o�[�S���ɑ��M����B
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
			activeChar.sendMessage("�p�[�e�B�[ ���[�����̍X�V�Ɏ��s���܂����B");
			return;
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
}