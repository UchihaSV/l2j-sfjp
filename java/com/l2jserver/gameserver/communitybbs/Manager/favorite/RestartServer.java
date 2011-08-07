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

import com.l2jserver.Config;
import com.l2jserver.gameserver.Announcements;
import com.l2jserver.gameserver.GameServer;
import com.l2jserver.gameserver.Shutdown;
import com.l2jserver.gameserver.cache.HtmCache;
import com.l2jserver.gameserver.instancemanager.CastleManager;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.actor.instance.L2PcInstance;
import com.l2jserver.gameserver.model.entity.Castle;
import com.l2jserver.gameserver.network.L2GameClient;
import com.l2jserver.gameserver.util.TextReplacer;

/**
 * @author  TEN / nanasisaso / TSL / JOJO
 */
public class RestartServer extends BaseFavoriteManager
{
	private static RestartServer _instance = new RestartServer();

	//�W�v
	private static boolean _autorestart = false;
	private int _total;		// �T�[�o�[���S��
	private int _score;		// ���X�^��]�҂̐l��
	private int _rate;		// ����

	/**
	 * @return
	 */
	public static RestartServer getInstance()
	{
		return _instance;
	}

	@Override
	public void parsecmd(String command, L2PcInstance activeChar)
	{
		// _bbsgetfav;restart_srv;_menu
		// _bbsgetfav;restart_srv;do

		String arg = getArgs(command);
		if (arg.equals("_menu"))
			showRestartServerMenu(activeChar);
		else if (arg.equals("do"))
			doRestartServer(activeChar);
	}

	@Override
	public void parsewrite(String ar1, String ar2, String ar3, String ar4, String ar5, L2PcInstance activeChar)
	{
	}

	// �T�[�o�ċN���\���Ґ��W�v
	private void countRestartServer()
	{
		int total = 0, score = 0;
		for (L2PcInstance pc : L2World.getInstance().getAllPlayersArray()) {
			L2GameClient c = pc.getClient();
			if (c == null || c.isDetached()) continue;	// �s�݃��[�h�����O
			++total;
			if (pc.voteServerRestart) ++score;
		}
		_total = total;
		_score = score;
		_rate = total == 0 ? 0 : score * 100 / total;
	}

	// �\����ʕ\������
	private void showRestartServerMenu(L2PcInstance activeChar)
	{
		TextReplacer html = new TextReplacer(HtmCache.getInstance().getHtm(activeChar.getHtmlPrefix(), "data/html/CommunityBoard/restart_srv/restartServer.htm"));

		String restartServer;
		if (_autorestart)
		{
			// �ċN���������Ȃ��
			restartServer = "<font color=\"LEVEL\">���ɍċN����������Ă��܂��B</font><br1>"
				+ "<font color=\"LEVEL\">���S�ȏꏊ�Ń��O�A�E�g���Ă��������B</font><br1>";
		}
		else
		{
			restartServer = "<button value=\"�\������\" action=\"bypass _bbsgetfav;restart_srv;do\" width=80 height=22 back=\"L2UI_CT1.BUTTON_DF_DOWN\" fore=\"L2UI_CT1.BUTTON_DF\"><br>"
				+ "�����ӎ�����<br1>"
				+ "�T�[�o�N���� " + com.l2jserver.util.Util.strMillTime(Config.CUSTOM_SERVER_RESTART_INTERVAL) + " �o�߂��Ă��Ȃ��Ɛ\���ł��܂���B<br1>"
				+ "�܂��A����A�J�E���g����̐\���͂P��݂̂ŁA���O�A�E�g�����ꍇ�͖����ƂȂ�܂��B<br1>";
		}

		countRestartServer();

		html = html
			.replace("%SERVER_RESTART_RATE%", Config.CUSTOM_SERVER_RESTART_RATE)
			.replace("%total%", _total)
			.replace("%score%", _score)
			.replace("%rate%", _rate)
			.replace("%restartServer%", restartServer);
		separateAndSend(html.toString(), activeChar);
	}

	// ��Ԋm�F�A���ʕ���
	private void doRestartServer(L2PcInstance activeChar)
	{
		// �ċN���������Ȃ�Ή��L����
		if (_autorestart) {
			showMessage(activeChar, "���ɃT�[�o�[�ċN�������Ɉڍs���Ă��܂��B");
			return;
		}

		// �R�}���h���s�҂��\���ς��ǂ����`�F�b�N
		if (activeChar.voteServerRestart) {
			showMessage(activeChar, "���ɃT�[�o�[�ċN���\���ςł��B");
			return;
		}

		// �����t��
		if (activeChar.getLevel() < 10) {
			showMessage(activeChar, "���x���P�O�ȏ�łȂ���ΐ\���ł��܂���B");
			return;
		}

		// �T�[�o�N����A�w�莞�Ԃ��o�߂��Ă��邩�`�F�b�N
		if (System.currentTimeMillis() < GameServer.gameServer.serverLoadEnd + Config.CUSTOM_SERVER_RESTART_INTERVAL) {
			showMessage(activeChar, "�T�[�o�N����A�K�莞�Ԃ��o�߂��Ă��܂���B");
			return;
		}

		// �U��풆���ǂ����`�F�b�N
		for (Castle castle : CastleManager.getInstance().getCastles()) {
			if (castle.getSiege().getIsInProgress()) {
				showMessage(activeChar, "�U��풆�̓T�[�o�[�ċN����\�����邱�Ƃ��ł��܂���B");
				return;
			}
		}

		activeChar.voteServerRestart = true;
//		showMessage(activeChar, "�T�[�o�[�ċN���\������t�܂����B");
		countRestartServer();

		if (_rate < Config.CUSTOM_SERVER_RESTART_RATE) {
			showMessage(activeChar, "�T�[�o�[�ċN���\������t�܂����B<br>"
					+ "��]�Ґ��������O�C������ " + Config.CUSTOM_SERVER_RESTART_RATE + "�� �ɒB����܂Ń��O�A�E�g�����ɂ��҂��������B");
			return;
		}
		if (_score < Config.CUSTOM_SERVER_RESTART_MINIMUM) {
			showMessage(activeChar, "�T�[�o�[�ċN���\������t�܂����B<br>"
					+ Config.CUSTOM_SERVER_RESTART_MINIMUM + " ���ȏ�̓��ӂ��Ȃ���΃T�[�o�[�ċN���ł��܂���B");
			return;
		}

		_autorestart = true;
		showMessage(activeChar, "�T�[�o�[�ċN����������܂����B");
		Announcements.getInstance().announceToAll("�y�T�[�o�[�ċN���̂��m�点�z");
		Announcements.getInstance().announceToAll("���[�V�X�e���ɂ��A�T�[�o�[�ċN����������܂����B");
		Announcements.getInstance().announceToAll("�����蓖�T�[�o�[�͍ċN�������Ɉڍs���܂��B");
		Announcements.getInstance().announceToAll("���S�ȏꏊ�Ń��O�A�E�g����悤�ɂ��Ă��������B");
		Shutdown.getInstance().startShutdown(null, Config.CUSTOM_SERVER_RESTART_TIME, true);
//		FavoriteMain.getInstance().showFavorite(activeChar);
	}
}