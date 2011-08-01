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
import java.util.Map;
import java.util.logging.Logger;

import com.l2jserver.Config;
import com.l2jserver.L2DatabaseFactory;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.instancemanager.MapRegionManager;
import com.l2jserver.gameserver.instancemanager.ZoneManager;
import com.l2jserver.gameserver.model.L2MapRegion;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.zone.L2ZoneType;
import com.l2jserver.gameserver.model.zone.type.L2JailZone;

/**
 * @author  TEN / nanasisaso / TSL / JOJO
 */
public class RepairChar extends BaseFavoriteManager
{
	private static Logger _log = Logger.getLogger(RepairChar.class.getName());
	private static RepairChar _instance = new RepairChar();

	private static final boolean CUSTOM_CHARA_REPAIR_NEAREST_TOWN = true;

	/**
	 * @return
	 */
	public static RepairChar getInstance()
	{
		return _instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		// _bbsgetfav;repair_char;_menu
		// _bbsgetfav;repair_char;999999999

		String arg = getArgs(command);
		if (arg.equals("_menu"))
		{
			showRepairCharList(activeChar);
		}
		else if (arg.matches("\\d+"))
		{
			int obj_id = Integer.parseInt(arg);
			doRepairChar(activeChar, obj_id);
		}
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
	}

	// �������������L�����̃��X�g��\��������
	private void showRepairCharList(L2PcInstance activeChar)
	{
		String html = HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/repair_char/repairCharList.htm");

		StringBuilder charList = new StringBuilder();
		if (activeChar.getAccountChars().size() == 0)
		{
			//�����A�J�E���g���̃L�������[���Ȃ��
			charList.append("�����\�ȃL�����N�^�[�����݂��܂���B");
		}
		else
		{
			// �ʃL����������Ȃ�΁i�ʃL���������[������Ȃ��Ȃ�j
			charList.append("�����������L�����N�^�[��I�����ĉ������B<br1>");
			if (CUSTOM_CHARA_REPAIR_NEAREST_TOWN)
				charList.append("�����Ӂ������ꏊ�́A�Ŋ�̑��ƂȂ��Ă��܂��B<br>");
			else
				charList.append("�����Ӂ������ꏊ�́u " + Config.CUSTOM_CHARA_REPAIR_NAME + " �v�ƂȂ��Ă��܂��B<br>");

			for (Map.Entry<Integer, String> chars : activeChar.getAccountChars().entrySet()) {
				int obj_Id = chars.getKey();
				String char_name = chars.getValue();
				charList.append("<a action=\"bypass _bbsgetfav;repair_char;" + obj_Id + "\">");
				charList.append(char_name);
				charList.append("</a>&nbsp;");
			}
		}

		html = html.replace("%charList%", charList.toString());
		separateAndSend(html, activeChar);
	}

	// �L�����N�^�[�����@�\�̎��s����
	@SuppressWarnings("deprecation")
	private void doRepairChar(L2PcInstance activeChar, int obj_id)
	{
		java.sql.Connection con = null;
		try
		{
			con = L2DatabaseFactory.getInstance().getConnection();
			PreparedStatement statement;

			int nx = Config.CUSTOM_CHARA_REPAIR_LOC[0], ny = Config.CUSTOM_CHARA_REPAIR_LOC[1], nz = Config.CUSTOM_CHARA_REPAIR_LOC[2];
			if (CUSTOM_CHARA_REPAIR_NEAREST_TOWN)
			{
				// �Ŋ�̑�
				statement = con.prepareStatement("SELECT x,y,z,pkkills,accesslevel FROM characters WHERE charId=?");
				statement.setInt(1, obj_id);
				ResultSet rs = statement.executeQuery();
				if (rs.next())
				{
					if (rs.getInt("accesslevel") < 0)
					{
						showMessage(activeChar, "�����ł��Ȃ��L�����N�^�[�ł��B");
						return;
					}
					final int x = rs.getInt("x"), y = rs.getInt("y"), z = rs.getInt("z");
					L2MapRegion mr = MapRegionManager.getInstance().getClosestTown(x, y);	/*�Ŋ�̑�*/
					if (mr == null)
						mr = MapRegionManager.getInstance().getMapRegion(146494, 30584);	/*�A�f��*/
					Location loc = mr.getSpawnLoc();
					nx = loc.getX();
					ny = loc.getY();
					nz = loc.getZ();
					if (rs.getInt("pkkills") > 5)
					{
						nx = 17817;
						ny = 170079;
						nz = -3530; /*�ԃl�˃t�H���[������*/
					}
					for (L2ZoneType zone : ZoneManager.getInstance().getAllZones())
						if (zone instanceof L2JailZone && zone.isInsideZone(x, y, z)) /*��������*/
						{
							nx = x;
							ny = y;
							nz = z;
							break;
						}
				}
				rs.close();
				statement.close();
			}

			// �����ړ�����
			statement = con.prepareStatement("UPDATE characters SET x=?,y=?,z=? WHERE charId=?");
			statement.setInt(1, nx);
			statement.setInt(2, ny);
			statement.setInt(3, nz);
			statement.setInt(4, obj_id);
			statement.execute();
			statement.close();

			// �����X�L���폜����
			statement = con.prepareStatement("DELETE FROM character_skills_save WHERE charId=?");
			statement.setInt(1, obj_id);
			statement.execute();
			statement.close();

			// ����������������
			statement = con.prepareStatement("UPDATE items SET loc='INVENTORY', loc_data=0 WHERE owner_id=? AND loc='PAPERDOLL'");
			statement.setInt(1, obj_id);
			statement.execute();
			statement.close();

			showMessage(activeChar, "�L�����N�^�[�������������܂����B");
			_log.info(activeChar.getAccountName() + " ���L�����N�^�[�𕜋����܂����B");
//			FavoriteMain.getInstance().showFavorite(activeChar);
		}
		catch (Exception e)
		{
			showMessage(activeChar, "�L�����N�^�[�����Ɏ��s���܂����B");
			_log.info("Error could not repair char: " + e);
		}
		finally
		{
			L2DatabaseFactory.close(con);
		}
	}
}