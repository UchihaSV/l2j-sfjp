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
package com.l2jserver.gameserver.model.actor.instance;

import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.CtrlEvent;
import com.l2jserver.gameserver.model.actor.L2Character;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.gameserver.network.clientpackets.Say2;
import com.l2jserver.gameserver.network.serverpackets.NpcSay;
import com.l2jserver.util.Rnd;

public class L2PenaltyMonsterInstance extends L2MonsterInstance
{
	private L2PcInstance _ptk;
	
	//[JOJO]-------------------------------------------------
	final int[] _randomNpcStrings;
	/*
	 * [0-2] onSpawn
	 * [3-5] random timer
	 * [6-8] onKill
	 */
	private static final int[] S18319 =	//�ނꂽ�J�G��
	{
		1010400, //�P���P���b�I����ȂƂ����$s1������Ȃ�āI�H
		1010401, //$s1�A������ˁI
		1010402, //�ނ�ԈႦ�����Č�����A$s1�I
		
		1010403, //�J�G�����Â������Ⴂ�����B
		1010404, //�J�G���̗͂������Ă�낤�I
		1010405, //����ň��ݍ���ł��I
		
		1010406, //�S�t�b�D�D�D���̃I���l�����ʂȂ�āI
		1010407, //�Q�R�Q�R�I�J�G���E���I
		1010408, //�J�G���͔��������Ȃ��ł���I�����B
	};
	private static final int[] S18320 =	//�ނꂽ�E���f�B�[�l
	{
		1010409, //���Ⴀ���I$s1�A������̂��H
		1010410, //�ӂӂ��A$s1����̑̂ɏ�������Ȃ�āB�o��͂ł��Ă����ł��傤�ˁH
		1010411, //����A$s1 ���Ȃ��������̂ˁB�ł��ˁA����ȍr���ۂ����҂Ɋ�ԏ��Ȃ�Ă��Ȃ����H
		
		1010412, //��������{�点����ˁI
		1010413, //���̏��A�ǂ����Ă����̂���I�H
		1010414, //���񂽂������ڂɂ��킹�Ă��I�������I
		
		1010415, //���Ⴀ���I����������ˁA�o���Ă�����Ⴂ�I
		1010416, //���Ȃ��̃G�T�͐H�ׂ�Ȃ��āA���̋������Ɍ����ӂ炵�Ă��I
		1010417, //�������݂����Ȃ��ア����������߂�Ȃ�āD�D�D���������B
	};
	private static final int[] S18321 =	//�ނꂽ���N��
	{
		1010418, //�P�P�P�D�D�D$s1�D�D�D�H�ׂ邼�D�D�D�P�P�b�D�D�D
		1010419, //�O�����D�D�D���D�D�D$s1�D�D�D����߂���D�D�D���ǂ��D�D�D
		1010420, //$s1�H�P�z�b�D�D�D�O���D�D�D�O���b�D�D�D
		
		1010421, //�N�P�P�P�P�I���N���I�X�s���I�A�^�A�A�A�A�A�b�N�I
		1010422, //�������I�p�v���I���I�������I�������I
		1010423, //���N���I���N���I���@�@�N�D�D���D�D�I
		
		1010424, //�N�G�F�D�D�D���݁D�D�D���D�D�D
		1010425, //�H��ꂽ���Ȃ��D�D�D�O�A�@
		1010426, //�O�n�b�I���N���I�P�z�b�P�z�b�I�O�G�F�D�D�D
	};
	private static final int[] S18322 =	//�ނꂽ�V�[�W���C�A���g
	{
		1010427, //�p�v���I���ɉh������I$s1�ɂ͎����I
		1010428, //$s1�A���O���I�����̉����������������߂Ă�̂́I
		1010429, //�����l�I$s1�Ɏ􂢂��I
		
		1010430, //�W���C�A���g �X�y�V���� �A�^�b�N�I
		1010431, //�������̍��݂��v���m��I
		1010432, //���̑���H����Ă݂�I
		
		1010433, //�p�v���I���l�ɉh������I
		1010434, //���͂����I
		1010435, //�V���͎��Ȃ��D�D�D������������̂݁D�D�D
	};
	private static final int[] S18323 =	//�ނꂽ�V�[�z�[�X �\���W���[
	{
		1010436, //$s1�A���̐��̋R�m�̒�����󂯂�I
		1010437, //�������̕񍐂ɂ�����$s1�����I
		1010438, //$s1�A�������킦���̂͂��񂽂̃G�T�������̂��I
		
		1010439, //���{��d���݂̑��p����������I
		1010440, //���񂽂ɑ���Ŋ��̃v���[���g�����I
		1010441, //���񂽂̃G�T�͎|��������I���Ⴀ�ȁI
		
		1010442, //�������I�䂪���E�̍��݂��I
		1010443, //�o���Ă�I���̋w�͂����ƒN�����D�D�D
		1010444, //�O�t�b�D�D�D���������O�ɂ͕߂܂�Ȃ����I
	};
	private static final int[] S18324 =	//�ނꂽ�z�����N���X
	{
		1010445, //$s1�A���������D�D�D
		1010446, //$s1�A�[�C�ɂ��鉴��ނ�グ��Ȃ�āD�D�D
		1010447, //$s1�A�������Ɍ����邩�B
		
		1010448, //�͂�ق�Ђ�`
		1010449, //�N�N�N�b�A�ǂ�A���񂪂�Ă��Ă�낤���B
		1010450, //������o��������Ė��f���Ă��񂾂�H
		
		1010451, //���Ђ�D�D�D�ɂ��D�D�D���ĂĂĂĂ��I
		1010452, //�`�F�b�A���s���D�D�D���������������̂ɁB
		1010453, //���������D�D�D��~�D�D�D�W�W�b�D�D�D
	};
	private static final int[] S18325 =	//�ނꂽ�t�����@
	{
		1010454, //�N�����b�I$s1�`�N���b�|�[�B
		1010455, //$s1�̓��������邼���I
		1010456, //�������������ȁA$s1�I
		
		1010457, //�H���Ă�邼�������I
		1010458, //���ււցA�v���Ԃ�ɂ�����G�T���ȁI
		1010459, //���O�Ȃ񂩈�����I
		
		1010460, //�����D�D�D�G�T�ɂ����Ȃ�āI
		1010461, //���O�͉��̃G�T�Ȃ񂾂�I�߂��ĐH���Ă��I
		1010462, //�G�T�ɐH����Ȃ�āD�D�D�N�b�D�D�D
	};
	private static final int[] S18326 =	//�ނꂽ�����
	{
		1010463, //����ނ����̂�$s1�H
		1010464, //���ɉ�����Ƃ����h�Ɏv����A$s1�B
		1010465, //$s1�A�����ċA���Ȃ�Ă�����ۂ������v��Ȃ��ق����������B
		
		1010466, //�[���̗͂��Ƃ��ƌ���D�D�D
		1010467, //���̒ނ�Ƃ��ւ��܂��Ă��D�D�D
		1010468, //�M�l�̎r�́A���̉������������������D�D�D
		
		1010469, //�����̒ނ�t����Ȃ������̂��D�D�D
		1010470, //�p�v���I�����|���Ȃ��̂��D�D�D
		1010471, //�f���炵���A�J�߂Ă�邼�D�D�D
	};
	//-------------------------------------------------------
	
	public L2PenaltyMonsterInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2PenaltyMonsterInstance);
		//[JOJO]-------------------------------------------------
		switch (getNpcId())
		{
			case 18319: _randomNpcStrings = S18319; break;	//�ނꂽ�J�G��
			case 18320: _randomNpcStrings = S18320; break;	//�ނꂽ�E���f�B�[�l
			case 18321: _randomNpcStrings = S18321; break;	//�ނꂽ���N��
			case 18322: _randomNpcStrings = S18322; break;	//�ނꂽ�V�[�W���C�A���g
			case 18323: _randomNpcStrings = S18323; break;	//�ނꂽ�V�[�z�[�X �\���W���[
			case 18324: _randomNpcStrings = S18324; break;	//�ނꂽ�z�����N���X
			case 18325: _randomNpcStrings = S18325; break;	//�ނꂽ�t�����@
			default:
			case 18326: _randomNpcStrings = S18326; break;	//�ނꂽ�����
		}
		ThreadPoolManager.getInstance().scheduleGeneral(new Runnable(){
			@Override
			public void run()
			{
				if (isVisible() && !isDead())
				{
					broadcastPacket(new NpcSay(getObjectId(), Say2.ALL, getNpcId(), _randomNpcStrings[Rnd.get(3, 5)]));
					ThreadPoolManager.getInstance().scheduleGeneral(this, Rnd.get(5555, 9999));
				}
			}
		}, Rnd.get(5555, 9999));
		//-------------------------------------------------------
	}
	
	@Override
	public L2Character getMostHated()
	{
		if (_ptk != null)
		{
			return _ptk; // always attack only one person
		}
		return super.getMostHated();
	}
	
	public void setPlayerToKill(L2PcInstance ptk)
	{
		//[JOJO]-------------------------------------------------
		broadcastPacket(new NpcSay(getObjectId(), Say2.ALL, getNpcId(), _randomNpcStrings[Rnd.get(0, 2)]).addPcName(ptk));
		//-------------------------------------------------------
		_ptk = ptk;
		addDamageHate(ptk, 0, 10);
		getAI().notifyEvent(CtrlEvent.EVT_ATTACKED, ptk);
		addAttackerToAttackByList(ptk);
	}
	
	@Override
	public boolean doDie(L2Character killer)
	{
		if (!super.doDie(killer))
		{
			return false;
		}
		
		//[JOJO]-------------------------------------------------
		broadcastPacket(new NpcSay(getObjectId(), Say2.ALL, getNpcId(), _randomNpcStrings[Rnd.get(6, 8)]));
		//-------------------------------------------------------
		return true;
	}
}
