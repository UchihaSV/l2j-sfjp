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
package com.l2jserver.gameserver.network;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.l2jserver.Config;
import com.l2jserver.gameserver.network.serverpackets.SystemMessage;

/**
 *
 * @author  Noctarius & Nille02 & crion ^ Forsaiken
 */
public final class SystemMessageId
{
	private static final Logger _log = Logger.getLogger(SystemMessageId.class.getName());
	private static final SMLocalisation[] EMPTY_SML_ARRAY = new SMLocalisation[0];
	public static final SystemMessageId[] EMPTY_ARRAY = new SystemMessageId[0];
	
	/**
	 * ID: 0<br>
	 * Message: �T�[�o�[�Ƃ̐ڑ����ؒf����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_DISCONNECTED;
	
	/**
	 * ID: 1<br>
	 * Message: �T�[�o�[��$s1�b��ɒ��f����܂��B�Q�[�����I�����Ă��������B
	 */
	public static final SystemMessageId THE_SERVER_WILL_BE_COMING_DOWN_IN_S1_SECONDS;
	
	/**
	 * ID: 2<br>
	 * Message: $s1�͑��݂��Ȃ����[�U�[�ł��B
	 */
	public static final SystemMessageId S1_DOES_NOT_EXIST;
	
	/**
	 * ID: 3<br>
	 * Message: $s1�̓Q�[���ɐڑ����Ă��܂���B
	 */
	public static final SystemMessageId S1_IS_NOT_ONLINE;
	
	/**
	 * ID: 4<br>
	 * Message: �������g�Ɍ��������\�������邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_INVITE_YOURSELF;
	
	/**
	 * ID: 5<br>
	 * Message: $s1�͂��łɑ��݂��Ă��錌���ł��B
	 */
	public static final SystemMessageId S1_ALREADY_EXISTS;
	
	/**
	 * ID: 6<br>
	 * Message: $s1�͑��݂��錌���ł͂���܂���B
	 */
	public static final SystemMessageId S1_DOES_NOT_EXIST2;
	
	/**
	 * ID: 7<br>
	 * Message: ���ł�$s1�����ɏ������Ă��܂��B
	 */
	public static final SystemMessageId ALREADY_MEMBER_OF_S1;
	
	/**
	 * ID: 8<br>
	 * Message: �����Ɋւ��鑼�̍�ƒ��ł��B
	 */
	public static final SystemMessageId YOU_ARE_WORKING_WITH_ANOTHER_CLAN;
	
	/**
	 * ID: 9<br>
	 * Message: $s1�͌�����ł͂���܂���B
	 */
	public static final SystemMessageId S1_IS_NOT_A_CLAN_LEADER;
	
	/**
	 * ID: 10<br>
	 * Message: $s1�͌����Ɋւ��鑼�̍�ƒ��ł��B
	 */
	public static final SystemMessageId S1_WORKING_WITH_ANOTHER_CLAN;
	
	/**
	 * ID: 11<br>
	 * Message: �����̉����\���҂����܂���B
	 */
	public static final SystemMessageId NO_APPLICANTS_FOR_THIS_CLAN;
	
	/**
	 * ID: 12<br>
	 * Message: �����̉����\���҂̏�񂪕s���m�ł��B
	 */
	public static final SystemMessageId APPLICANT_INFORMATION_INCORRECT;
	
	/**
	 * ID: 13<br>
	 * Message: �U���ւ̎Q����\�����Ă��邽�߁A���U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_CAUSE_CLAN_WILL_PARTICIPATE_IN_CASTLE_SIEGE;
	
	/**
	 * ID: 14<br>
	 * Message: ��܂��̓A�W�g�����L���Ă��邽�߁A���U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_CAUSE_CLAN_OWNS_CASTLES_HIDEOUTS;
	
	/**
	 * ID: 15<br>
	 * Message: �U��풆�ł��B
	 */
	public static final SystemMessageId YOU_ARE_IN_SIEGE;
	
	/**
	 * ID: 16<br>
	 * Message: �U��풆�ł͂���܂���B
	 */
	public static final SystemMessageId YOU_ARE_NOT_IN_SIEGE;
	
	/**
	 * ID: 17<br>
	 * Message: �U��킪�n�܂�܂����B
	 */
	public static final SystemMessageId CASTLE_SIEGE_HAS_BEGUN;
	
	/**
	 * ID: 18<br>
	 * Message: �U��킪�I�����܂����B
	 */
	public static final SystemMessageId CASTLE_SIEGE_HAS_ENDED;
	
	/**
	 * ID: 19<br>
	 * Message: ��傪�ւ��܂����I
	 */
	public static final SystemMessageId NEW_CASTLE_LORD;
	
	/**
	 * ID: 20<br>
	 * Message: �傪�J����܂��B
	 */
	public static final SystemMessageId GATE_IS_OPENING;
	
	/**
	 * ID: 21<br>
	 * Message: �傪�j�󂳂�܂��B
	 */
	public static final SystemMessageId GATE_IS_DESTROYED;
	
	/**
	 * ID: 22<br>
	 * Message: ���肪����߂��Ă��܂��B
	 */
	public static final SystemMessageId TARGET_TOO_FAR;
	
	/**
	 * ID: 23<br>
	 * Message: HP������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_HP;
	
	/**
	 * ID: 24<br>
	 * Message: MP������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_MP;
	
	/**
	 * ID: 25<br>
	 * Message: HP���񕜂���܂��B
	 */
	public static final SystemMessageId REJUVENATING_HP;
	
	/**
	 * ID: 26<br>
	 * Message: MP���񕜂���܂��B
	 */
	public static final SystemMessageId REJUVENATING_MP;
	
	/**
	 * ID: 27<br>
	 * Message: �W�Q���󂯁A�r�������f����܂����B
	 */
	public static final SystemMessageId CASTING_INTERRUPTED;
	
	/**
	 * ID: 28<br>
	 * Message: $s1�A�f�i����ɓ���܂����B
	 */
	public static final SystemMessageId YOU_PICKED_UP_S1_ADENA;
	
	/**
	 * ID: 29<br>
	 * Message: $s1 $s2����ɓ���܂����B
	 */
	public static final SystemMessageId YOU_PICKED_UP_S1_S2;
	
	/**
	 * ID: 30<br>
	 * Message: $s1����ɓ���܂����B
	 */
	public static final SystemMessageId YOU_PICKED_UP_S1;
	
	/**
	 * ID: 31<br>
	 * Message: ��������Ԃł͍s���ł��܂���B
	 */
	public static final SystemMessageId CANT_MOVE_SITTING;
	
	/**
	 * ID: 32<br>
	 * Message: �퓬�s�\�Ɋׂ�܂����B�Ŋ��̃��X�^�[�g �|�C���g�Ɉړ����܂��B
	 */
	public static final SystemMessageId UNABLE_COMBAT_PLEASE_GO_RESTART;
	
	/**
	 * ID: 32<br>
	 * Message: �퓬�s�\�Ɋׂ�܂����B�Ŋ��̃��X�^�[�g �|�C���g�Ɉړ����܂��B
	 */
	public static final SystemMessageId CANT_MOVE_CASTING;
	
	/**
	 * ID: 34<br>
	 * Message: ���l�[�W��II�̐��E�ւ悤�����I
	 */
	public static final SystemMessageId WELCOME_TO_LINEAGE;
	
	/**
	 * ID: 35<br>
	 * Message: $s1�̃_���[�W��^���܂����B
	 */
	public static final SystemMessageId YOU_DID_S1_DMG;
	
	/**
	 * ID: 36<br>
	 * Message: $c1����$s2�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId C1_GAVE_YOU_S2_DMG;
	
	/**
	 * ID: 37<br>
	 * Message: $c1�ɂ����$s2�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId C1_GAVE_YOU_S2_DMG2;
	
	/**
	 * ID: 41<br>
	 * Message: ��̔��ˏ������ł��B
	 */
	public static final SystemMessageId GETTING_READY_TO_SHOOT_AN_ARROW;
	
	/**
	 * ID: 42<br>
	 * Message: $c1�̍U��������܂����B
	 */
	public static final SystemMessageId AVOIDED_C1_ATTACK;
	
	/**
	 * ID: 43<br>
	 * Message: �U�����O��܂����B
	 */
	public static final SystemMessageId MISSED_TARGET;
	
	/**
	 * ID: 44<br>
	 * Message: �N���e�B�J�� �q�b�g�I
	 */
	public static final SystemMessageId CRITICAL_HIT;
	
	/**
	 * ID: 45<br>
	 * Message: $s1�̌o���l�𓾂܂����B
	 */
	public static final SystemMessageId EARNED_S1_EXPERIENCE;
	
	/**
	 * ID: 46<br>
	 * Message: $s1���g�p���܂��B
	 */
	public static final SystemMessageId USE_S1;
	
	/**
	 * ID: 47<br>
	 * Message: $s1�̎g�p���ł��B
	 */
	public static final SystemMessageId BEGIN_TO_USE_S1;
	
	/**
	 * ID: 48<br>
	 * Message: $s1�͍Ďg�p�������ł��邽�߁A�g�p�ł��܂���B
	 */
	public static final SystemMessageId S1_PREPARED_FOR_REUSE;
	
	/**
	 * ID: 49<br>
	 * Message: $s1�𑕔����܂����B
	 */
	public static final SystemMessageId S1_EQUIPPED;
	
	/**
	 * ID: 50<br>
	 * Message: �^�[�Q�b�g���w�肳��Ă��܂���B
	 */
	public static final SystemMessageId TARGET_CANT_FOUND;
	
	/**
	 * ID: 51<br>
	 * Message: �������g�ɂ͎g�p�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_USE_ON_YOURSELF;
	
	/**
	 * ID: 52<br>
	 * Message: $s1�A�f�i�𓾂܂����B
	 */
	public static final SystemMessageId EARNED_S1_ADENA;
	
	/**
	 * ID: 53<br>
	 * Message: $s1 $s2�𓾂܂����B
	 */
	public static final SystemMessageId EARNED_S2_S1_S;
	
	/**
	 * ID: 54<br>
	 * Message: $s1�𓾂܂����B
	 */
	public static final SystemMessageId EARNED_ITEM_S1;
	
	/**
	 * ID: 55<br>
	 * Message: $s1�A�f�i�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_PICKUP_S1_ADENA;
	
	/**
	 * ID: 56<br>
	 * Message: $s1�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_PICKUP_S1;
	
	/**
	 * ID: 57<br>
	 * Message: $s1 $s2�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_PICKUP_S2_S1_S;
	
	/**
	 * ID: 58<br>
	 * Message: $s1�A�f�i�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_EARN_S1_ADENA;
	
	/**
	 * ID: 59<br>
	 * Message: $s1�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_EARN_S1;
	
	/**
	 * ID: 60<br>
	 * Message: $s1 $s2�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_EARN_S2_S1_S;
	
	/**
	 * ID: 61<br>
	 * Message: �����N����܂���ł����B
	 */
	public static final SystemMessageId NOTHING_HAPPENED;
	
	/**
	 * ID: 62<br>
	 * Message: $s1�����ɐ������܂����B
	 */
	public static final SystemMessageId S1_SUCCESSFULLY_ENCHANTED;
	
	/**
	 * ID: 63<br>
	 * Message: +$s1$s2��������������܂����B
	 */
	public static final SystemMessageId S1_S2_SUCCESSFULLY_ENCHANTED;
	
	/**
	 * ID: 64<br>
	 * Message: �����Ɏ��s���܂����B$s1�������܂����B
	 */
	public static final SystemMessageId ENCHANTMENT_FAILED_S1_EVAPORATED;
	
	/**
	 * ID: 65<br>
	 * Message: �����Ɏ��s���܂����B+$s1$s2�������܂����B
	 */
	public static final SystemMessageId ENCHANTMENT_FAILED_S1_S2_EVAPORATED;
	
	/**
	 * ID: 66<br>
	 * Message: $c1���p�[�e�B�[�ɏ��҂��Ă��܂��B�Q�����܂����B
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY;
	
	/**
	 * ID: 67<br>
	 * Message: $s1����$s2�����̊��U���󂯂Ă��܂��B�������܂����B
	 */
	public static final SystemMessageId S1_HAS_INVITED_YOU_TO_JOIN_THE_CLAN_S2;
	
	/**
	 * ID: 68<br>
	 * Message: $s1��������E�ނ��܂����B�����E�ތ�A1���Ԃ͑��̌����ɉ����ł��܂���B
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_WITHDRAW_FROM_THE_S1_CLAN;
	
	/**
	 * ID: 69<br>
	 * Message: $s1����������Ǖ����܂����B������������������1���ԁA�V�����������̉����͂ł��܂���B
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_DISMISS_S1_FROM_THE_CLAN;
	
	/**
	 * ID: 70<br>
	 * Message: $s1���������U���܂��B��낵���ł����B
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_DISPERSE_THE_CLAN_S1;
	
	/**
	 * ID: 71<br>
	 * Message: $s1�����̂Ă܂����B
	 */
	public static final SystemMessageId HOW_MANY_S1_DISCARD;
	
	/**
	 * ID: 72<br>
	 * Message: $s1�����ڂ��܂����B
	 */
	public static final SystemMessageId HOW_MANY_S1_MOVE;
	
	/**
	 * ID: 73<br>
	 * Message: $s1�����j�󂵂܂����B
	 */
	public static final SystemMessageId HOW_MANY_S1_DESTROY;
	
	/**
	 * ID: 74<br>
	 * Message: $s1��j�󂵂܂��B��낵���ł����B
	 */
	public static final SystemMessageId WISH_DESTROY_S1;
	
	/**
	 * ID: 75<br>
	 * Message: ���݂��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId ID_NOT_EXIST;
	
	/**
	 * ID: 76<br>
	 * Message: �p�X���[�h������������܂���B
	 */
	public static final SystemMessageId INCORRECT_PASSWORD;
	
	/**
	 * ID: 77<br>
	 * Message: ����ȏ�͐����ł��܂���B�����̃L�����N�^�[���폜���Ă�蒼���Ă��������B
	 */
	public static final SystemMessageId CANNOT_CREATE_CHARACTER;
	
	/**
	 * ID: 78<br>
	 * Message: �L�����N�^�[���폜�����΁A�����Ă����g�b�s���O �A�C�e�����폜����܂��B�L�����N�^�[���g�b�s���O �A�C�e���������Ă��邩�ǂ������m�F���Ă��������B$s1��{���ɍ폜���܂����B
	 */
	public static final SystemMessageId WISH_DELETE_S1;
	
	/**
	 * ID: 79<br>
	 * Message: ���łɑ��݂��閼�O�ł��B
	 */
	public static final SystemMessageId NAMING_NAME_ALREADY_EXISTS;
	
	/**
	 * ID: 80<br>
	 * Message: ���{��1�`8�����A�܂��͉p��1�`16�����ȓ��ɂ��Ă��������B
	 */
	public static final SystemMessageId NAMING_CHARNAME_UP_TO_16CHARS;
	
	/**
	 * ID: 81<br>
	 * Message: �푰��I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_RACE;
	
	/**
	 * ID: 82<br>
	 * Message: �N���X��I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_OCCUPATION;
	
	/**
	 * ID: 83<br>
	 * Message: ���ʂ�I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_GENDER;
	
	/**
	 * ID: 84<br>
	 * Message: �s�[�X�]�[�����ł͍U���ł��܂���B
	 */
	public static final SystemMessageId CANT_ATK_PEACEZONE;
	
	/**
	 * ID: 85<br>
	 * Message: ���肪�s�[�X�]�[�����ɂ��邽�߁A�U���ł��܂���B
	 */
	public static final SystemMessageId TARGET_IN_PEACEZONE;
	
	/**
	 * ID: 86<br>
	 * Message: �A�J�E���g����͂��Ă��������B
	 */
	public static final SystemMessageId PLEASE_ENTER_ID;
	
	/**
	 * ID: 87<br>
	 * Message: �p�X���[�h����͂��Ă��������B
	 */
	public static final SystemMessageId PLEASE_ENTER_PASSWORD;
	
	/**
	 * ID: 88<br>
	 * Message: �v���g�R���̃o�[�W�������قȂ�܂��B�v���O�������I�����Ă��������B
	 */
	public static final SystemMessageId WRONG_PROTOCOL_CHECK;
	
	/**
	 * ID: 89<br>
	 * Message: �v���g�R���̃o�[�W�������قȂ�܂��B�����Ă��������B
	 */
	public static final SystemMessageId WRONG_PROTOCOL_CONTINUE;
	
	/**
	 * ID: 90<br>
	 * Message: �T�[�o�[�ɐڑ��ł��܂���B
	 */
	public static final SystemMessageId UNABLE_TO_CONNECT;
	
	/**
	 * ID: 91<br>
	 * Message: ���^��I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_HAIRSTYLE;
	
	/**
	 * ID: 92<br>
	 * Message: $s1�̌��ʂ������܂����B
	 */
	public static final SystemMessageId S1_HAS_WORN_OFF;
	
	/**
	 * ID: 93<br>
	 * Message: SP������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SP;
	
	/**
	 * ID: 94<br>
	 * Message: Copyright c NCsoft Corporation. All Rights Reserved.
	 */
	public static final SystemMessageId COPYRIGHT;
	
	/**
	 * ID: 95<br>
	 * Message: $s1�̌o���l��$s2��SP�𓾂܂����B
	 */
	public static final SystemMessageId YOU_EARNED_S1_EXP_AND_S2_SP;
	
	/**
	 * ID: 96<br>
	 * Message: ���x�� �A�b�v���܂����I
	 */
	public static final SystemMessageId YOU_INCREASED_YOUR_LEVEL;
	
	/**
	 * ID: 97<br>
	 * Message: �N�G�X�g �A�C�e���͈ړ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_MOVE_THIS_ITEM;
	
	/**
	 * ID: 98<br>
	 * Message: �N�G�X�g �A�C�e���́A�̂Ă��܂���B
	 */
	public static final SystemMessageId CANNOT_DISCARD_THIS_ITEM;
	
	/**
	 * ID: 99<br>
	 * Message: �N�G�X�g �A�C�e���̓g���[�h�����蔄������ł��܂���B
	 */
	public static final SystemMessageId CANNOT_TRADE_THIS_ITEM;
	
	/**
	 * ID: 100<br>
	 * Message: $c1���g���[�h��\�����Ă��܂��B����ɉ����܂����B
	 */
	public static final SystemMessageId C1_REQUESTS_TRADE;
	
	/**
	 * ID: 101<br>
	 * Message: �퓬���̓��O�A�E�g�ł��܂���B
	 */
	public static final SystemMessageId CANT_LOGOUT_WHILE_FIGHTING;
	
	/**
	 * ID: 102<br>
	 * Message: �퓬���̓��X�^�[�g�ł��܂���B
	 */
	public static final SystemMessageId CANT_RESTART_WHILE_FIGHTING;
	
	/**
	 * ID: 103<br>
	 * Message: ���ݐڑ����Ă���A�J�E���g�ł��B
	 */
	public static final SystemMessageId ID_LOGGED_IN;
	
	/**
	 * ID: 104<br>
	 * Message: �ϐg���ɂ͕����ύX�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_USE_ITEM_WHILE_USING_MAGIC;
	
	/**
	 * ID: 105<br>
	 * Message: $c1���p�[�e�B�[�ɏ��҂��܂����B
	 */
	public static final SystemMessageId C1_INVITED_TO_PARTY;
	
	/**
	 * ID: 106<br>
	 * Message: �p�[�e�B�[�ɎQ�����܂����B
	 */
	public static final SystemMessageId YOU_JOINED_S1_PARTY;
	
	/**
	 * ID: 107<br>
	 * Message: $c1���p�[�e�B�[�ɎQ�����܂����B
	 */
	public static final SystemMessageId C1_JOINED_PARTY;
	
	/**
	 * ID: 108<br>
	 * Message: $c1���p�[�e�B�[����E�ނ��܂����B
	 */
	public static final SystemMessageId C1_LEFT_PARTY;
	
	/**
	 * ID: 109<br>
	 * Message: �^�[�Q�b�g������������܂���B
	 */
	public static final SystemMessageId INCORRECT_TARGET;
	
	/**
	 * ID: 110<br>
	 * Message: $s1�̌��ʂ��������܂��B
	 */
	public static final SystemMessageId YOU_FEEL_S1_EFFECT;
	
	/**
	 * ID: 111<br>
	 * Message: �V�[���h�ɂ��h��ɐ������܂����B
	 */
	public static final SystemMessageId SHIELD_DEFENCE_SUCCESSFULL;
	
	/**
	 * ID: 112<br>
	 * Message: �����܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_ARROWS;
	
	/**
	 * ID: 113<br>
	 * Message: ����������Ȃ����߁A$s1�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId S1_CANNOT_BE_USED;
	
	/**
	 * ID: 114<br>
	 * Message: ���E���̉e�ɓ���܂��B
	 */
	public static final SystemMessageId ENTER_SHADOW_MOTHER_TREE;
	
	/**
	 * ID: 115<br>
	 * Message: ���E���̉e���痣��܂��B
	 */
	public static final SystemMessageId EXIT_SHADOW_MOTHER_TREE;
	
	/**
	 * ID: 116<br>
	 * Message: �s�[�X�]�[���ɓ���܂��B
	 */
	public static final SystemMessageId ENTER_PEACEFUL_ZONE;
	
	/**
	 * ID: 117<br>
	 * Message: �s�[�X�]�[�����痣��܂��B
	 */
	public static final SystemMessageId EXIT_PEACEFUL_ZONE;
	
	/**
	 * ID: 118<br>
	 * Message: $c1�Ƀg���[�h��\�����݂܂��B
	 */
	public static final SystemMessageId REQUEST_C1_FOR_TRADE;
	
	/**
	 * ID: 119<br>
	 * Message: $c1���g���[�h�����ۂ��܂����B
	 */
	public static final SystemMessageId C1_DENIED_TRADE_REQUEST;
	
	/**
	 * ID: 120<br>
	 * Message: $c1�Ƃ̃g���[�h���J�n���܂��B
	 */
	public static final SystemMessageId BEGIN_TRADE_WITH_C1;
	
	/**
	 * ID: 121<br>
	 * Message: $c1���g���[�h����A�C�e�����m�肵�܂����B
	 */
	public static final SystemMessageId C1_CONFIRMED_TRADE;
	
	/**
	 * ID: 122<br>
	 * Message: ���肪�g���[�h����A�C�e�����m�肵�����߁A�A�C�e���̒ǉ��͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_ADJUST_ITEMS_AFTER_TRADE_CONFIRMED;
	
	/**
	 * ID: 123<br>
	 * Message: �g���[�h�������ɍs���܂����B
	 */
	public static final SystemMessageId TRADE_SUCCESSFUL;
	
	/**
	 * ID: 124<br>
	 * Message: $c1���g���[�h���L�����Z�����܂����B
	 */
	public static final SystemMessageId C1_CANCELED_TRADE;
	
	/**
	 * ID: 125<br>
	 * Message: �Q�[�����I�����܂��B
	 */
	public static final SystemMessageId WISH_EXIT_GAME;
	
	/**
	 * ID: 126<br>
	 * Message: �Q�[�������X�^�[�g���܂��B
	 */
	public static final SystemMessageId WISH_RESTART_GAME;
	
	/**
	 * ID: 127<br>
	 * Message: �T�[�o�[�Ƃ̐ڑ����ؒf����܂����B���΂炭���Ă���ڑ����Ȃ����Ă݂Ă��������B
	 */
	public static final SystemMessageId DISCONNECTED_FROM_SERVER;
	
	/**
	 * ID: 128<br>
	 * Message: �L�����N�^�[�̐����Ɏ��s���܂����B
	 */
	public static final SystemMessageId CHARACTER_CREATION_FAILED;
	
	/**
	 * ID: 129<br>
	 * Message: �C���x���g���̃X���b�g�������ς��ł��B
	 */
	public static final SystemMessageId SLOTS_FULL;
	
	/**
	 * ID: 130<br>
	 * Message: �q�ɂ̃X���b�g�������ς��ł��B
	 */
	public static final SystemMessageId WAREHOUSE_FULL;
	
	/**
	 * ID: 131<br>
	 * Message: $s1�����O�C�����܂����B
	 */
	public static final SystemMessageId S1_LOGGED_IN;
	
	/**
	 * ID: 132<br>
	 * Message: $s1���F�l���X�g�ɒǉ�����܂����B
	 */
	public static final SystemMessageId S1_ADDED_TO_FRIENDS;
	
	/**
	 * ID: 133<br>
	 * Message: $s1���F�l���X�g����폜����܂����B
	 */
	public static final SystemMessageId S1_REMOVED_FROM_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 134<br>
	 * Message: �F�l���X�g���m�F���Ă��������B
	 */
	public static final SystemMessageId PLEACE_CHECK_YOUR_FRIEND_LIST_AGAIN;
	
	/**
	 * ID: 135<br>
	 * Message: $c1���������Ȃ��������߁A�p�[�e�B�[���҂��L�����Z������܂����B
	 */
	public static final SystemMessageId C1_DID_NOT_REPLY_TO_YOUR_INVITE;
	
	/**
	 * ID: 136<br>
	 * Message: $c1�̏��҂ɉ������Ȃ��������߁A�������L�����Z������܂����B
	 */
	public static final SystemMessageId YOU_DID_NOT_REPLY_TO_C1_INVITE;
	
	/**
	 * ID: 137<br>
	 * Message: �V���[�g�J�b�g�Ɏw�肳�ꂽ�A�C�e����������܂���B
	 */
	public static final SystemMessageId NO_MORE_ITEMS_SHORTCUT;
	
	/**
	 * ID: 138<br>
	 * Message: �V���[�g�J�b�g���w�肳��Ă��܂���B
	 */
	public static final SystemMessageId DESIGNATE_SHORTCUT;
	
	/**
	 * ID: 139<br>
	 * Message: $c1��$s2�ɂ�����܂���ł����B
	 */
	public static final SystemMessageId C1_RESISTED_YOUR_S2;
	
	/**
	 * ID: 140<br>
	 * Message: MP������Ȃ����߁A�X�L�������f����܂����B
	 */
	public static final SystemMessageId SKILL_REMOVED_DUE_LACK_MP;
	
	/**
	 * ID: 141<br>
	 * Message: �g���[�h����A�C�e�����m�肵�����߁A�A�C�e���̒ǉ��͂ł��܂���B
	 */
	public static final SystemMessageId ONCE_THE_TRADE_IS_CONFIRMED_THE_ITEM_CANNOT_BE_MOVED_AGAIN;
	
	/**
	 * ID: 142<br>
	 * Message: ���łɃg���[�h���ł��B
	 */
	public static final SystemMessageId ALREADY_TRADING;
	
	/**
	 * ID: 143<br>
	 * Message: $c1�͑��̐l�ƃg���[�h���Ă��܂��B
	 */
	public static final SystemMessageId C1_ALREADY_TRADING;
	
	/**
	 * ID: 144<br>
	 * Message: �^�[�Q�b�g������������܂���B
	 */
	public static final SystemMessageId TARGET_IS_INCORRECT;
	
	/**
	 * ID: 145<br>
	 * Message: �^�[�Q�b�g���Q�[�����ɂ��܂���B
	 */
	public static final SystemMessageId TARGET_IS_NOT_FOUND_IN_THE_GAME;
	
	/**
	 * ID: 146<br>
	 * Message: �`���b�g��������܂����B
	 */
	public static final SystemMessageId CHATTING_PERMITTED;
	
	/**
	 * ID: 147<br>
	 * Message: �`���b�g���֎~����܂����B
	 */
	public static final SystemMessageId CHATTING_PROHIBITED;
	
	/**
	 * ID: 148<br>
	 * Message: �N�G�X�g �A�C�e���͎g�p�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_USE_QUEST_ITEMS;
	
	/**
	 * ID: 149<br>
	 * Message: �g���[�h���̓A�C�e�����E������g�p������ł��܂���B
	 */
	public static final SystemMessageId CANNOT_USE_ITEM_WHILE_TRADING;
	
	/**
	 * ID: 150<br>
	 * Message: �l���X��g���[�h���ɁA�A�C�e�����̂Ă邱�Ƃ�j�󂷂邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISCARD_OR_DESTROY_ITEM_WHILE_TRADING;
	
	/**
	 * ID: 151<br>
	 * Message: ���߂��Ď̂Ă��܂���B
	 */
	public static final SystemMessageId CANNOT_DISCARD_DISTANCE_TOO_FAR;
	
	/**
	 * ID: 152<br>
	 * Message: ���҂���Ώۂ�����������܂���B
	 */
	public static final SystemMessageId YOU_HAVE_INVITED_THE_WRONG_TARGET;
	
	/**
	 * ID: 153<br>
	 * Message: $c1�͍�ƒ��ł��B���΂炭���Ă���\�����Ȃ����Ă��������B
	 */
	public static final SystemMessageId C1_IS_BUSY_TRY_LATER;
	
	/**
	 * ID: 154<br>
	 * Message: �p�[�e�B�[ ���[�_�[���������҂��g�p�ł��܂��B
	 */
	public static final SystemMessageId ONLY_LEADER_CAN_INVITE;
	
	/**
	 * ID: 155<br>
	 * Message: �p�[�e�B�[�������ɂȂ�܂����B
	 */
	public static final SystemMessageId PARTY_FULL;
	
	/**
	 * ID: 156<br>
	 * Message: �h���C�������������������܂����B
	 */
	public static final SystemMessageId DRAIN_HALF_SUCCESFUL;
	
	/**
	 * ID: 157<br>
	 * Message: $c1�̃h���C���ɒ�R���܂����B
	 */
	public static final SystemMessageId RESISTED_C1_DRAIN;
	
	/**
	 * ID: 158<br>
	 * Message: �U�������s���܂����B
	 */
	public static final SystemMessageId ATTACK_FAILED;
	
	/**
	 * ID: 159<br>
	 * Message: $c1�̖��@�ɒ�R���܂����B
	 */
	public static final SystemMessageId RESISTED_C1_MAGIC;
	
	/**
	 * ID: 160<br>
	 * Message: $c1�͂��łɃp�[�e�B�[�ɏ������Ă��邽�߁A���҂ł��܂���B
	 */
	public static final SystemMessageId C1_IS_ALREADY_IN_PARTY;
	
	/**
	 * ID: 161<br>
	 * Message: �p�[�e�B�[�ɏ��҂������[�U�[�����܂���B
	 */
	public static final SystemMessageId INVITED_USER_NOT_ONLINE;
	
	/**
	 * ID: 162<br>
	 * Message: �q�ɂ�����߂��Ă��܂��B
	 */
	public static final SystemMessageId WAREHOUSE_TOO_FAR;
	
	/**
	 * ID: 163<br>
	 * Message: �����������Ȃ����߁A�j��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DESTROY_NUMBER_INCORRECT;
	
	/**
	 * ID: 164<br>
	 * Message: ���̃v���Z�X�̉�����҂��Ă��܂��B
	 */
	public static final SystemMessageId WAITING_FOR_ANOTHER_REPLY;
	
	/**
	 * ID: 165<br>
	 * Message: �������g��F�l�Ƃ��ēo�^���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_ADD_YOURSELF_TO_OWN_FRIEND_LIST;
	
	/**
	 * ID: 166<br>
	 * Message: �F�l���X�g���܂��쐬����Ă��܂���B���΂炭���Ă���o�^���Ȃ����Ă��������B
	 */
	public static final SystemMessageId FRIEND_LIST_NOT_READY_YET_REGISTER_LATER;
	
	/**
	 * ID: 167<br>
	 * Message: $c1�͂��łɗF�l���X�g�ɓo�^����Ă��܂��B
	 */
	public static final SystemMessageId C1_ALREADY_ON_FRIEND_LIST;
	
	/**
	 * ID: 168<br>
	 * Message: $c1����F�l���X�g�̓o�^��v������܂����B
	 */
	public static final SystemMessageId C1_REQUESTED_TO_BECOME_FRIENDS;
	
	/**
	 * ID: 169<br>
	 * Message: �o�^�������܂����B 0/1 (����1�A���ۂ�0)
	 */
	public static final SystemMessageId ACCEPT_THE_FRIENDSHIP;
	
	/**
	 * ID: 170<br>
	 * Message: �F�l�o�^��v���������[�U�[���Q�[�����ɂ��܂���B
	 */
	public static final SystemMessageId THE_USER_YOU_REQUESTED_IS_NOT_IN_GAME;
	
	/**
	 * ID: 171<br>
	 * Message: $c1�͗F�l���X�g�ɓo�^����Ă��郆�[�U�[�ł͂���܂���B
	 */
	public static final SystemMessageId C1_NOT_ON_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 172<br>
	 * Message: �ۊǗ�������܂���B
	 */
	public static final SystemMessageId LACK_FUNDS_FOR_TRANSACTION1;
	
	/**
	 * ID: 173<br>
	 * Message: �ۊǗ�������܂���B
	 */
	public static final SystemMessageId LACK_FUNDS_FOR_TRANSACTION2;
	
	/**
	 * ID: 174<br>
	 * Message: ����̃C���x���g���̃X���b�g�������ς��ł��B
	 */
	public static final SystemMessageId OTHER_INVENTORY_FULL;
	
	/**
	 * ID: 175<br>
	 * Message: HP�����S�ɉ񕜂��ꂽ���߁A�X�L�������f����܂����B
	 */
	public static final SystemMessageId SKILL_DEACTIVATED_HP_FULL;
	
	/**
	 * ID: 176<br>
	 * Message: ����̓��b�Z�[�W��M�s�\�ȏ�Ԃł��B
	 */
	public static final SystemMessageId THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE;
	
	/**
	 * ID: 177<br>
	 * Message: ���b�Z�[�W��M�s�\�ȏ�Ԃł��B
	 */
	public static final SystemMessageId MESSAGE_REFUSAL_MODE;
	
	/**
	 * ID: 178<br>
	 * Message: ���b�Z�[�W��M�̏�Ԃł��B
	 */
	public static final SystemMessageId MESSAGE_ACCEPTANCE_MODE;
	
	/**
	 * ID: 179<br>
	 * Message: �����ł̓A�C�e�����̂Ă��܂���B
	 */
	public static final SystemMessageId CANT_DISCARD_HERE;
	
	/**
	 * ID: 180<br>
	 * Message: �폜�܂�$s1�����c���Ă��܂��B�폜���L�����Z�����܂����B
	 */
	public static final SystemMessageId S1_DAYS_LEFT_CANCEL_ACTION;
	
	/**
	 * ID: 181<br>
	 * Message: �^�[�Q�b�g�������܂���B
	 */
	public static final SystemMessageId CANT_SEE_TARGET;
	
	/**
	 * ID: 182<br>
	 * Message: �N�G�X�g�F$s1�𒆒f���܂����B
	 */
	public static final SystemMessageId WANT_QUIT_CURRENT_QUEST;
	
	/**
	 * ID: 183<br>
	 * Message: �T�[�o�[�̐����l���ɒB���܂����B���΂炭���Ă���ڑ����Ȃ����Ă݂Ă��������B
	 */
	public static final SystemMessageId TOO_MANY_USERS;
	
	/**
	 * ID: 184<br>
	 * Message: ���΂炭���Ă����蒼���Ă��������B
	 */
	public static final SystemMessageId TRY_AGAIN_LATER;
	
	/**
	 * ID: 185<br>
	 * Message: �p�[�e�B�[�ɏ��҂��郆�[�U�[��I�����Ă��������B
	 */
	public static final SystemMessageId FIRST_SELECT_USER_TO_INVITE_TO_PARTY;
	
	/**
	 * ID: 186<br>
	 * Message: �������������U���郆�[�U�[��I�����Ă��������B
	 */
	public static final SystemMessageId FIRST_SELECT_USER_TO_INVITE_TO_CLAN;
	
	/**
	 * ID: 187<br>
	 * Message: �Ǖ����郆�[�U�[��I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_USER_TO_EXPEL;
	
	/**
	 * ID: 188<br>
	 * Message: ����������͂��Ă��������B
	 */
	public static final SystemMessageId PLEASE_CREATE_CLAN_NAME;
	
	/**
	 * ID: 189<br>
	 * Message: �������n�݂���܂����B
	 */
	public static final SystemMessageId CLAN_CREATED;
	
	/**
	 * ID: 190<br>
	 * Message: �����̑n�݂Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_CREATE_CLAN;
	
	/**
	 * ID: 191<br>
	 * Message: ������ $s1���������珜������܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_S1_EXPELLED;
	
	/**
	 * ID: 192<br>
	 * Message: ������ $s1�̏����Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_EXPEL_S1;
	
	/**
	 * ID: 193<br>
	 * Message: ���������U����܂����B
	 */
	public static final SystemMessageId CLAN_HAS_DISPERSED;
	
	/**
	 * ID: 194<br>
	 * Message: �����̉��U�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_DISPERSE_CLAN;
	
	/**
	 * ID: 195<br>
	 * Message: �����ɉ�������܂����B
	 */
	public static final SystemMessageId ENTERED_THE_CLAN;
	
	/**
	 * ID: 196<br>
	 * Message: $s1���������������ۂ��܂����B
	 */
	public static final SystemMessageId S1_REFUSED_TO_JOIN_CLAN;
	
	/**
	 * ID: 197<br>
	 * Message: ��������E�ނ��܂����B
	 */
	public static final SystemMessageId YOU_HAVE_WITHDRAWN_FROM_CLAN;
	
	/**
	 * ID: 198<br>
	 * Message: $s1��������̒E�ނɎ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_WITHDRAW_FROM_S1_CLAN;
	
	/**
	 * ID: 199<br>
	 * Message: �������珜������܂����B1���Ԃ͑��̌����ɉ����ł��܂���B
	 */
	public static final SystemMessageId CLAN_MEMBERSHIP_TERMINATED;
	
	/**
	 * ID: 200<br>
	 * Message: �p�[�e�B�[����E�ނ��܂����B
	 */
	public static final SystemMessageId YOU_LEFT_PARTY;
	
	/**
	 * ID: 201<br>
	 * Message: $c1���p�[�e�B�[����Ǖ����܂����B
	 */
	public static final SystemMessageId C1_WAS_EXPELLED_FROM_PARTY;
	
	/**
	 * ID: 202<br>
	 * Message: �p�[�e�B�[����Ǖ�����܂����B
	 */
	public static final SystemMessageId HAVE_BEEN_EXPELLED_FROM_PARTY;
	
	/**
	 * ID: 203<br>
	 * Message: �p�[�e�B�[�����U����܂����B
	 */
	public static final SystemMessageId PARTY_DISPERSED;
	
	/**
	 * ID: 204<br>
	 * Message: �������̐������z���Ă��邩�A�s�K�؂ȕ������g�p����Ă��܂��B��蒼���Ă��������B
	 */
	public static final SystemMessageId INCORRECT_NAME_TRY_AGAIN;
	
	/**
	 * ID: 205<br>
	 * Message: �L�����N�^�[��������������܂���B�T�|�[�g�ɂ��₢���킹���������B
	 */
	public static final SystemMessageId INCORRECT_CHARACTER_NAME_TRY_AGAIN;
	
	/**
	 * ID: 206<br>
	 * Message: �������z�����錌��������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_DECLARE_WAR;
	
	/**
	 * ID: 207<br>
	 * Message: $s1������$s2�Ɍ������\�����܂�܂����B������󂯓���܂����B
	 */
	public static final SystemMessageId S2_OF_THE_CLAN_S1_REQUESTS_WAR;
	
	/**
	 * ID: 212<br>
	 * Message: �������錌�����ł͂���܂���B
	 */
	public static final SystemMessageId YOU_ARE_NOT_A_CLAN_MEMBER;
	
	/**
	 * ID: 213<br>
	 * Message: ��������܂���ł����B���΂炭���Ă����蒼���Ă��������B
	 */
	public static final SystemMessageId NOT_WORKING_PLEASE_TRY_AGAIN_LATER;
	
	/**
	 * ID: 214<br>
	 * Message: �^�C�g�����ύX����܂����B
	 */
	public static final SystemMessageId TITLE_CHANGED;
	
	/**
	 * ID: 215<br>
	 * Message: $s1 �����Ƃ̐푈���n�܂�܂����B
	 */
	public static final SystemMessageId WAR_WITH_THE_S1_CLAN_HAS_BEGUN;
	
	/**
	 * ID: 216<br>
	 * Message: $s1 �����Ƃ̐푈���I�����܂����B
	 */
	public static final SystemMessageId WAR_WITH_THE_S1_CLAN_HAS_ENDED;
	
	/**
	 * ID: 217<br>
	 * Message: $s1 �����Ƃ̐푈�ŏ������܂����I
	 */
	public static final SystemMessageId YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN;
	
	/**
	 * ID: 218<br>
	 * Message: $s1 �����ɍ~�����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN;
	
	/**
	 * ID: 219<br>
	 * Message: �����傪���S���A$s1 �����ɕ����܂����B
	 */
	public static final SystemMessageId YOU_WERE_DEFEATED_BY_S1_CLAN;
	
	/**
	 * ID: 220<br>
	 * Message: ������̏I���܂ł��� $s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_UNTIL_CLAN_WAR_ENDS;
	
	/**
	 * ID: 221<br>
	 * Message: ������̐������Ԃ��߂��A$s1 �����Ƃ̐푈���I�����܂����B
	 */
	public static final SystemMessageId CLAN_WAR_WITH_S1_CLAN_HAS_ENDED;
	
	/**
	 * ID: 222<br>
	 * Message: $s1�������ɉ������܂����B
	 */
	public static final SystemMessageId S1_HAS_JOINED_CLAN;
	
	/**
	 * ID: 223<br>
	 * Message: ������ $s1����������E�ނ��܂����B
	 */
	public static final SystemMessageId S1_HAS_WITHDRAWN_FROM_THE_CLAN;
	
	/**
	 * ID: 224<br>
	 * Message: $s1���������Ȃ��������߁A���������̊��U���L�����Z������܂����B
	 */
	public static final SystemMessageId S1_DID_NOT_RESPOND_TO_CLAN_INVITATION;
	
	/**
	 * ID: 225<br>
	 * Message: $s1�̊��U�ɉ������Ȃ��������߁A�����������L�����Z������܂����B
	 */
	public static final SystemMessageId YOU_DID_NOT_RESPOND_TO_S1_CLAN_INVITATION;
	
	/**
	 * ID: 226<br>
	 * Message: $s1�������������Ȃ��������߁A���z�������ۂ���܂����B
	 */
	public static final SystemMessageId S1_CLAN_DID_NOT_RESPOND;
	
	/**
	 * ID: 227<br>
	 * Message: $s1�����̐��z���ɉ������Ȃ��������߁A�����킪���ۂ���܂����B
	 */
	public static final SystemMessageId CLAN_WAR_REFUSED_YOU_DID_NOT_RESPOND_TO_S1;
	
	/**
	 * ID: 228<br>
	 * Message: �I��v�������ۂ���܂����B
	 */
	public static final SystemMessageId REQUEST_TO_END_WAR_HAS_BEEN_DENIED;
	
	/**
	 * ID: 229<br>
	 * Message: �����n�݂̎��i������܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN;
	
	/**
	 * ID: 230<br>
	 * Message: �������U��10���ȓ��ɂ͐V����������n�݂ł��܂���B
	 */
	public static final SystemMessageId YOU_MUST_WAIT_XX_DAYS_BEFORE_CREATING_A_NEW_CLAN;
	
	/**
	 * ID: 231<br>
	 * Message: �������̏�����1���ȓ��ɂ͐V�����������̉����͂ł��܂���B
	 */
	public static final SystemMessageId YOU_MUST_WAIT_BEFORE_ACCEPTING_A_NEW_MEMBER;
	
	/**
	 * ID: 232<br>
	 * Message: �����܂��͒E�ތ�1���ȓ��ɂ͌����ɉ����ł��܂���B
	 */
	public static final SystemMessageId YOU_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN;
	
	/**
	 * ID: 233<br>
	 * Message: �A�J�f�~�[/�߉q��/�R�m�c�̋󂫂�����܂���̂ŁA�V���Ȍ������̉����͂ł��܂���B
	 */
	public static final SystemMessageId SUBCLAN_IS_FULL;
	
	/**
	 * ID: 234<br>
	 * Message: �^�[�Q�b�g���������ɂ��Ă��������B
	 */
	public static final SystemMessageId TARGET_MUST_BE_IN_CLAN;
	
	/**
	 * ID: 235<br>
	 * Message: �������Ϗ��ł��܂���B
	 */
	public static final SystemMessageId NOT_AUTHORIZED_TO_BESTOW_RIGHTS;
	
	/**
	 * ID: 236<br>
	 * Message: ������݂̂��s���܂��B
	 */
	public static final SystemMessageId ONLY_THE_CLAN_LEADER_IS_ENABLED;
	
	/**
	 * ID: 237<br>
	 * Message: �����傪������܂���B
	 */
	public static final SystemMessageId CLAN_LEADER_NOT_FOUND;
	
	/**
	 * ID: 238<br>
	 * Message: �����ɉ������Ă��܂���B
	 */
	public static final SystemMessageId NOT_JOINED_IN_ANY_CLAN;
	
	/**
	 * ID: 239<br>
	 * Message: ������͒E�ނł��܂���B
	 */
	public static final SystemMessageId CLAN_LEADER_CANNOT_WITHDRAW;
	
	/**
	 * ID: 240<br>
	 * Message: ���݌����풆�ł��B
	 */
	public static final SystemMessageId CURRENTLY_INVOLVED_IN_CLAN_WAR;
	
	/**
	 * ID: 241<br>
	 * Message: $s1�����̌�����͐ڑ����Ă��܂���B
	 */
	public static final SystemMessageId LEADER_OF_S1_CLAN_NOT_FOUND;
	
	/**
	 * ID: 242<br>
	 * Message: �^�[�Q�b�g��I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_TARGET;
	
	/**
	 * ID: 243<br>
	 * Message: �����̌����ɑ΂��Č�����̐��z���͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DECLARE_WAR_ON_ALLIED_CLAN;
	
	/**
	 * ID: 244<br>
	 * Message: �������\�����ގ��i������܂���B
	 */
	public static final SystemMessageId NOT_ALLOWED_TO_CHALLENGE;
	
	/**
	 * ID: 245<br>
	 * Message: �O��̌�����̋��ۂ���5�����o���Ă��܂���B��낵���ł����B
	 */
	public static final SystemMessageId FIVE_DAYS_NOT_PASSED_SINCE_REFUSED_WAR;
	
	/**
	 * ID: 246<br>
	 * Message: ����̌����͌��ݐ푈���ł��B
	 */
	public static final SystemMessageId CLAN_CURRENTLY_AT_WAR;
	
	/**
	 * ID: 247<br>
	 * Message: $s1 �����Ƃ͂��łɐ푈���s�������߁A�O��̐푈����5�����o�߂��Ȃ��Ɛ��z���ł��܂���B
	 */
	public static final SystemMessageId FIVE_DAYS_MUST_PASS_BEFORE_CHALLENGE_AGAIN;
	
	/**
	 * ID: 248<br>
	 * Message: $s1 �����̌����������Ȃ��Đ��z���ł��܂���B
	 */
	public static final SystemMessageId S1_CLAN_NOT_ENOUGH_MEMBERS_FOR_WAR;
	
	/**
	 * ID: 249<br>
	 * Message: $s1 �����ɍ~�����܂����B
	 */
	public static final SystemMessageId WISH_SURRENDER_TO_S1_CLAN;
	
	/**
	 * ID: 250<br>
	 * Message: $s1 �����Ɍl�~�����܂����B������̏�Ԃ���Ƃ�܂��B
	 */
	public static final SystemMessageId YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN;
	
	/**
	 * ID: 251<br>
	 * Message: �����풆�ɂ͑��̌����ɑ΂��Đ��z���ł��܂���B
	 */
	public static final SystemMessageId ALREADY_AT_WAR_WITH_ANOTHER_CLAN;
	
	/**
	 * ID: 252<br>
	 * Message: �~�����錌��������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_SURRENDER_TO;
	
	/**
	 * ID: 253<br>
	 * Message: �I���\�����ތ���������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_END_WAR;
	
	/**
	 * ID: 254<br>
	 * Message: ������͌l�~���ł��܂���B
	 */
	public static final SystemMessageId LEADER_CANT_PERSONALLY_SURRENDER;
	
	/**
	 * ID: 255<br>
	 * Message: $s1 �����ɏI���\�����܂�܂����B���ӂ��܂����B
	 */
	public static final SystemMessageId S1_CLAN_REQUESTED_END_WAR;
	
	/**
	 * ID: 256<br>
	 * Message: �t�^����^�C�g������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_TITLE;
	
	/**
	 * ID: 257<br>
	 * Message: $s1�����ɏI���\�����݂܂����B
	 */
	public static final SystemMessageId DO_YOU_OFFER_S1_CLAN_END_WAR;
	
	/**
	 * ID: 258<br>
	 * Message: �����풆�ł͂���܂���B
	 */
	public static final SystemMessageId NOT_INVOLVED_CLAN_WAR;
	
	/**
	 * ID: 259<br>
	 * Message: �����������X�g����I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_MEMBERS_FROM_LIST;
	
	/**
	 * ID: 260<br>
	 * Message: ������̋��ۂ���5�����o���Ă��Ȃ����߁A�����l��������܂����B
	 */
	public static final SystemMessageId FIVE_DAYS_NOT_PASSED_SINCE_YOU_WERE_REFUSED_WAR;
	
	/**
	 * ID: 261<br>
	 * Message: ������������������܂���B
	 */
	public static final SystemMessageId CLAN_NAME_INCORRECT;
	
	/**
	 * ID: 262<br>
	 * Message: �����������߂��܂��B
	 */
	public static final SystemMessageId CLAN_NAME_TOO_LONG;
	
	/**
	 * ID: 263<br>
	 * Message: $s1�����͉��U�̐\�����ݒ��ł��B
	 */
	public static final SystemMessageId DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 264<br>
	 * Message: �푈���ɂ͌��������U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_WHILE_IN_WAR;
	
	/**
	 * ID: 265<br>
	 * Message: �U�h���̌����͉��U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_WHILE_IN_SIEGE;
	
	/**
	 * ID: 266<br>
	 * Message: �A�W�g�������L���Ă��錌���͉��U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_WHILE_OWNING_CLAN_HALL_OR_CASTLE;
	
	/**
	 * ID: 267<br>
	 * Message: ���U�̐\�����݂�����Ă��܂���B
	 */
	public static final SystemMessageId NO_REQUESTS_TO_DISPERSE;
	
	/**
	 * ID: 268<br>
	 * Message: ���łɌ����ɏ������Ă��܂��B
	 */
	public static final SystemMessageId PLAYER_ALREADY_ANOTHER_CLAN;
	
	/**
	 * ID: 269<br>
	 * Message: �������g���������邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_DISMISS_YOURSELF;
	
	/**
	 * ID: 270<br>
	 * Message: ���łɍ~�����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_ALREADY_SURRENDERED;
	
	/**
	 * ID: 271<br>
	 * Message: �^�C�g���t�^�͌����X�L�� ���x����3�ȏォ��ł��܂��B
	 */
	public static final SystemMessageId CLAN_LVL_3_NEEDED_TO_ENDOWE_TITLE;
	
	/**
	 * ID: 272<br>
	 * Message: �G���u�����o�^�͌����X�L�� ���x����3�ȏォ��ł��܂��B
	 */
	public static final SystemMessageId CLAN_LVL_3_NEEDED_TO_SET_CREST;
	
	/**
	 * ID: 273<br>
	 * Message: ������̕z���͌����X�L�� ���x����3�ȏォ��ł��܂��B
	 */
	public static final SystemMessageId CLAN_LVL_3_NEEDED_TO_DECLARE_WAR;
	
	/**
	 * ID: 274<br>
	 * Message: �����X�L���̃��x�����オ��܂����B
	 */
	public static final SystemMessageId CLAN_LEVEL_INCREASED;
	
	/**
	 * ID: 275<br>
	 * Message: �����X�L���̃��x�� �A�b�v�Ɏ��s���܂����B
	 */
	public static final SystemMessageId CLAN_LEVEL_INCREASE_FAILED;
	
	/**
	 * ID: 276<br>
	 * Message: �X�L���K���ɕK�v�ȃA�C�e��������܂���B
	 */
	public static final SystemMessageId ITEM_MISSING_TO_LEARN_SKILL;
	
	/**
	 * ID: 277<br>
	 * Message: $s1���K�����܂��B
	 */
	public static final SystemMessageId LEARNED_SKILL_S1;
	
	/**
	 * ID: 278<br>
	 * Message: �X�L���K���ɕK�v��SP������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SP_TO_LEARN_SKILL;
	
	/**
	 * ID: 279<br>
	 * Message: �A�f�i������܂���B
	 */
	public static final SystemMessageId YOU_NOT_ENOUGH_ADENA;
	
	/**
	 * ID: 280<br>
	 * Message: ������̂�����܂���B
	 */
	public static final SystemMessageId NO_ITEMS_TO_SELL;
	
	/**
	 * ID: 281<br>
	 * Message: �ۊǗ�������܂���B
	 */
	public static final SystemMessageId YOU_NOT_ENOUGH_ADENA_PAY_FEE;
	
	/**
	 * ID: 282<br>
	 * Message: �a�������̂�����܂���B
	 */
	public static final SystemMessageId NO_ITEM_DEPOSITED_IN_WH;
	
	/**
	 * ID: 283<br>
	 * Message: ���ɓ���܂����B
	 */
	public static final SystemMessageId ENTERED_COMBAT_ZONE;
	
	/**
	 * ID: 284<br>
	 * Message: ��ꂩ��o�܂����B
	 */
	public static final SystemMessageId LEFT_COMBAT_ZONE;
	
	/**
	 * ID: 285<br>
	 * Message: $s1 �������x�z�҂̍���ɐ������܂����B
	 */
	public static final SystemMessageId CLAN_S1_ENGRAVED_RULER;
	
	/**
	 * ID: 286<br>
	 * Message: �w�n���U������Ă��܂��B
	 */
	public static final SystemMessageId BASE_UNDER_ATTACK;
	
	/**
	 * ID: 287<br>
	 * Message: �G�̌������x�z�҂̍�����n�߂܂����B
	 */
	public static final SystemMessageId OPPONENT_STARTED_ENGRAVING;
	
	/**
	 * ID: 288<br>
	 * Message: ��傪���܂����B
	 */
	public static final SystemMessageId CASTLE_GATE_BROKEN_DOWN;
	
	/**
	 * ID: 289<br>
	 * Message: ���ݐw�n��O����n�����邽�ߍ\�z�ł��܂���B
	 */
	public static final SystemMessageId NOT_ANOTHER_HEADQUARTERS;
	
	/**
	 * ID: 290<br>
	 * Message: �����ɂ͐w�n�����Ă��܂���B
	 */
	public static final SystemMessageId NOT_SET_UP_BASE_HERE;
	
	/**
	 * ID: 291<br>
	 * Message: $s1 ������ $s2 �Ƃ̍U���ŏ������܂����B
	 */
	public static final SystemMessageId CLAN_S1_VICTORIOUS_OVER_S2_S_SIEGE;
	
	/**
	 * ID: 292<br>
	 * Message: $s1���U�鎞�����z���܂����B
	 */
	public static final SystemMessageId S1_ANNOUNCED_SIEGE_TIME;
	
	/**
	 * ID: 293<br>
	 * Message: $s1�̍U��o�^���Ԃ��I�����܂����B
	 */
	public static final SystemMessageId REGISTRATION_TERM_FOR_S1_ENDED;
	
	/**
	 * ID: 294<br>
	 * Message: �U���A�v�ǐ��A�W�g��ɎQ�����Ă���U�����̌����ł͂Ȃ��̂Őw�n����邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId BECAUSE_YOUR_CLAN_IS_NOT_CURRENTLY_ON_THE_OFFENSIVE_IN_A_CLAN_HALL_SIEGE_WAR_IT_CANNOT_SUMMON_ITS_BASE_CAMP;
	
	/**
	 * ID: 295<br>
	 * Message: �U���ɎQ�����錌�����Ȃ����߁A$s1�̍U��킪�L�����Z������܂����B
	 */
	public static final SystemMessageId S1_SIEGE_WAS_CANCELED_BECAUSE_NO_CLANS_PARTICIPATED;
	
	/**
	 * ID: 296<br>
	 * Message: ���������痎����$s1�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId FALL_DAMAGE_S1;
	
	/**
	 * ID: 297<br>
	 * Message: �ċz���ł���$s1�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId DROWN_DAMAGE_S1;
	
	/**
	 * ID: 298<br>
	 * Message: $s1�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId YOU_DROPPED_S1;
	
	/**
	 * ID: 299<br>
	 * Message: $c1��$s2��$s3��ɓ���܂����B
	 */
	public static final SystemMessageId C1_OBTAINED_S3_S2;
	
	/**
	 * ID: 300<br>
	 * Message: $c1��$s2����ɓ���܂����B
	 */
	public static final SystemMessageId C1_OBTAINED_S2;
	
	/**
	 * ID: 301<br>
	 * Message: $s1 $s2�������܂����B
	 */
	public static final SystemMessageId S2_S1_DISAPPEARED;
	
	/**
	 * ID: 302<br>
	 * Message: $s1�������܂����B
	 */
	public static final SystemMessageId S1_DISAPPEARED;
	
	/**
	 * ID: 303<br>
	 * Message: ��������A�C�e����I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_ITEM_TO_ENCHANT;
	
	/**
	 * ID: 304<br>
	 * Message: ������ $s1���Q�[���ɐڑ����܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_S1_LOGGED_IN;
	
	/**
	 * ID: 305<br>
	 * Message: �p�[�e�B�[�ւ̎Q�������ۂ���܂����B
	 */
	public static final SystemMessageId PLAYER_DECLINED;
	
	// 306 - 308 empty
	
	/**
	 * ID: 309<br>
	 * Message: �������̏����ɐ������܂����B
	 */
	public static final SystemMessageId YOU_HAVE_SUCCEEDED_IN_EXPELLING_CLAN_MEMBER;
	
	// 310 empty
	
	/**
	 * ID: 311<br>
	 * Message: ������̐\�����݂��󂯓�����܂����B
	 */
	public static final SystemMessageId CLAN_WAR_DECLARATION_ACCEPTED;
	
	/**
	 * ID: 312<br>
	 * Message: ������̐\�����݂����ۂ���܂����B
	 */
	public static final SystemMessageId CLAN_WAR_DECLARATION_REFUSED;
	
	/**
	 * ID: 313<br>
	 * Message: �I��̐\�����݂��󂯓�����܂����B
	 */
	public static final SystemMessageId CEASE_WAR_REQUEST_ACCEPTED;
	
	// 314  - 318 empty
	
	/**
	 * ID: 319<br>
	 * Message: �A�����b�N�ł��Ȃ��h�A�ł��B
	 */
	public static final SystemMessageId UNABLE_TO_UNLOCK_DOOR;
	
	/**
	 * ID: 320<br>
	 * Message: �A�����b�N�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_UNLOCK_DOOR;
	
	/**
	 * ID: 321<br>
	 * Message: ���b�N����Ă��܂���B
	 */
	public static final SystemMessageId ITS_NOT_LOCKED;
	
	/**
	 * ID: 322<br>
	 * Message: �̔��z�����߂Ă��������B
	 */
	public static final SystemMessageId DECIDE_SALES_PRICE;
	
	/**
	 * ID: 323<br>
	 * Message: �C��$s1�i�K�ɏ㏸���܂����B
	 */
	public static final SystemMessageId FORCE_INCREASED_TO_S1;
	
	/**
	 * ID: 324<br>
	 * Message: ����ȏ�͋C���グ���܂���B
	 */
	public static final SystemMessageId FORCE_MAXLEVEL_REACHED;
	
	/**
	 * ID: 325<br>
	 * Message: ���̂����łɂȂ��Ȃ�܂����B
	 */
	public static final SystemMessageId CORPSE_ALREADY_DISAPPEARED;
	
	/**
	 * ID: 326<br>
	 * Message: �Ώۂ����X�g����I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_TARGET_FROM_LIST;
	
	/**
	 * ID: 327<br>
	 * Message: 80�����ȉ��ɂ��Ă��������B
	 */
	public static final SystemMessageId CANNOT_EXCEED_80_CHARACTERS;
	
	/**
	 * ID: 328<br>
	 * Message: ��ڂ���͂��Ă��������B
	 */
	public static final SystemMessageId PLEASE_INPUT_TITLE_LESS_128_CHARACTERS;
	
	/**
	 * ID: 329<br>
	 * Message: ���e����͂��Ă��������B
	 */
	public static final SystemMessageId PLEASE_INPUT_CONTENT_LESS_3000_CHARACTERS;
	
	/**
	 * ID: 330<br>
	 * Message: �R�����g��128�����ȓ��œ��͂��Ă��������B
	 */
	public static final SystemMessageId ONE_LINE_RESPONSE_NOT_EXCEED_128_CHARACTERS;
	
	/**
	 * ID: 331<br>
	 * Message: $s1��SP�𓾂܂����B
	 */
	public static final SystemMessageId ACQUIRED_S1_SP;
	
	/**
	 * ID: 332<br>
	 * Message: �������܂����B
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_BE_RESTORED;
	
	/**
	 * ID: 333<br>
	 * Message: �R�A�̃o���A�ɂ����$s1�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId S1_DAMAGE_BY_CORE_BARRIER;
	
	/**
	 * ID: 334<br>
	 * Message: ���b�Z�[�W����͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_PRIVATE_STORE_MESSAGE;
	
	/**
	 * ID: 335<br>
	 * Message: $s1�𒆒f���܂��B
	 */
	public static final SystemMessageId S1_HAS_BEEN_ABORTED;
	
	/**
	 * ID: 336<br>
	 * Message: $s1���N���X�^���C�Y���܂��B��낵���ł����B
	 */
	public static final SystemMessageId WISH_TO_CRYSTALLIZE_S1;
	
	/**
	 * ID: 337<br>
	 * Message: �\�E�� �V���b�g�ƕ���̃O���[�h����v���܂���B
	 */
	public static final SystemMessageId SOULSHOTS_GRADE_MISMATCH;
	
	/**
	 * ID: 338<br>
	 * Message: �\�E�� �V���b�g������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SOULSHOTS;
	
	/**
	 * ID: 339<br>
	 * Message: �\�E�� �V���b�g���g�p�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_USE_SOULSHOTS;
	
	/**
	 * ID: 340<br>
	 * Message: �l���X�̏������ł��B
	 */
	public static final SystemMessageId PRIVATE_STORE_UNDER_WAY;
	
	/**
	 * ID: 341<br>
	 * Message: �ޗ�������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_MATERIALS;
	
	/**
	 * ID: 342<br>
	 * Message: ����̗͂��g���܂��B
	 */
	public static final SystemMessageId ENABLED_SOULSHOT;
	
	/**
	 * ID: 343<br>
	 * Message: �X�|�C������Ă��Ȃ����߁A�X�E�B�[�p�[�̎g�p�Ɏ��s���܂����B
	 */
	public static final SystemMessageId SWEEPER_FAILED_TARGET_NOT_SPOILED;
	
	/**
	 * ID: 344<br>
	 * Message: ����̗͂������Ȃ�܂����B
	 */
	public static final SystemMessageId SOULSHOTS_DISABLED;
	
	/**
	 * ID: 345<br>
	 * Message: �`���b�g�\�ȏ�Ԃł��B
	 */
	public static final SystemMessageId CHAT_ENABLED;
	
	/**
	 * ID: 346<br>
	 * Message: �`���b�g���֎~����܂����B
	 */
	public static final SystemMessageId CHAT_DISABLED;
	
	/**
	 * ID: 347<br>
	 * Message: �A�C�e���̌�������������܂���B
	 */
	public static final SystemMessageId INCORRECT_ITEM_COUNT;
	
	/**
	 * ID: 348<br>
	 * Message: �A�C�e���̉��i������������܂���B
	 */
	public static final SystemMessageId INCORRECT_ITEM_PRICE;
	
	/**
	 * ID: 349<br>
	 * Message: �l���X���I�����܂����B
	 */
	public static final SystemMessageId PRIVATE_STORE_ALREADY_CLOSED;
	
	/**
	 * ID: 350<br>
	 * Message: �A�C�e�����i�؂�ł��B
	 */
	public static final SystemMessageId ITEM_OUT_OF_STOCK;
	
	/**
	 * ID: 351<br>
	 * Message: �w������A�C�e���̌�������������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_ITEMS;
	
	// 352 - 353: empty
	
	/**
	 * ID: 354<br>
	 * Message: �G���`�����g���L�����Z������܂����B
	 */
	public static final SystemMessageId CANCEL_ENCHANT;
	
	/**
	 * ID: 355<br>
	 * Message: �G���`�����g�̏�������v���܂���B
	 */
	public static final SystemMessageId INAPPROPRIATE_ENCHANT_CONDITION;
	
	/**
	 * ID: 356<br>
	 * Message: ���������ۂ��܂����B
	 */
	public static final SystemMessageId REJECT_RESURRECTION;
	
	/**
	 * ID: 357<br>
	 * Message: ���łɃX�|�C���ɂ������Ă��܂��B
	 */
	public static final SystemMessageId ALREADY_SPOILED;
	
	/**
	 * ID: 358<br>
	 * Message: �U���̏I���܂ł���$s1���Ԃł��B
	 */
	public static final SystemMessageId S1_HOURS_UNTIL_SIEGE_CONCLUSION;
	
	/**
	 * ID: 359<br>
	 * Message: �U���̏I���܂ł���$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_UNTIL_SIEGE_CONCLUSION;
	
	/**
	 * ID: 360<br>
	 * Message: �U���̏I���܂ł���$s1�b�I
	 */
	public static final SystemMessageId CASTLE_SIEGE_S1_SECONDS_LEFT;
	
	/**
	 * ID: 361<br>
	 * Message: �I�[�o�[�q�b�g�I
	 */
	public static final SystemMessageId OVER_HIT;
	
	/**
	 * ID: 362<br>
	 * Message: �I�[�o�[�q�b�g��$s1�̃{�[�i�X�o���l�𓾂܂����B
	 */
	public static final SystemMessageId ACQUIRED_BONUS_EXPERIENCE_THROUGH_OVER_HIT;
	
	/**
	 * ID: 363<br>
	 * Message: �`���b�g�\�܂ł���$s1���ł��B
	 */
	public static final SystemMessageId CHAT_AVAILABLE_S1_MINUTE;
	
	/**
	 * ID: 364<br>
	 * Message: �������郆�[�U�[������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_USER_NAME_TO_SEARCH;
	
	/**
	 * ID: 365<br>
	 * Message: �{���ɃN���X�^���C�Y���܂����B
	 */
	public static final SystemMessageId ARE_YOU_SURE;
	
	/**
	 * ID: 366<br>
	 * Message: ���F��I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_HAIR_COLOR;
	
	/**
	 * ID: 367<br>
	 * Message: �����ɏ������Ă���L�����N�^�[�͍폜�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_REMOVE_CLAN_CHARACTER;
	
	/**
	 * ID: 368<br>
	 * Message: +$s1$s2�𑕔����܂����B
	 */
	public static final SystemMessageId S1_S2_EQUIPPED;
	
	/**
	 * ID: 369<br>
	 * Message: +$s1$s2����ɓ���܂����B
	 */
	public static final SystemMessageId YOU_PICKED_UP_A_S1_S2;
	
	/**
	 * ID: 370<br>
	 * Message: +$s1$s2�̎擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_PICKUP_S1;
	
	/**
	 * ID: 371<br>
	 * Message: +$s1$s2�𓾂܂����B
	 */
	public static final SystemMessageId ACQUIRED_S1_S2;
	
	/**
	 * ID: 372<br>
	 * Message: +$s1$s2�擾�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_EARN_S1;
	
	/**
	 * ID: 373<br>
	 * Message: +$s1$s2��j�󂵂܂��B��낵���ł����B
	 */
	public static final SystemMessageId WISH_DESTROY_S1_S2;
	
	/**
	 * ID: 374<br>
	 * Message: +$s1$s2���N���X�^���C�Y���܂��B��낵���ł����B
	 */
	public static final SystemMessageId WISH_CRYSTALLIZE_S1_S2;
	
	/**
	 * ID: 375<br>
	 * Message: +$s1$s2�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId DROPPED_S1_S2;
	
	/**
	 * ID: 376<br>
	 * Message: $c1�� +$s2$s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_OBTAINED_S2_S3;
	
	/**
	 * ID: 377<br>
	 * Message: +$s1$s2�������܂����B
	 */
	public static final SystemMessageId S1_S2_DISAPPEARED;
	
	/**
	 * ID: 378<br>
	 * Message: $c1�� $s2�𔃂��܂����B
	 */
	public static final SystemMessageId C1_PURCHASED_S2;
	
	/**
	 * ID: 379<br>
	 * Message: $c1�� +$s2$s3�𔃂��܂����B
	 */
	public static final SystemMessageId C1_PURCHASED_S2_S3;
	
	/**
	 * ID: 380<br>
	 * Message: $c1�� $s2 $s3�𔃂��܂����B
	 */
	public static final SystemMessageId C1_PURCHASED_S3_S2_S;
	
	/**
	 * ID: 381<br>
	 * Message: �T�|�[�g�T�[�o�[�Ɍq����܂���B
	 */
	public static final SystemMessageId GAME_CLIENT_UNABLE_TO_CONNECT_TO_PETITION_SERVER;
	
	/**
	 * ID: 382<br>
	 * Message: ����FS�ESPT�E�T�[�r�X�`�[�� ID�Ń`�F�b�N�A�E�g����Ă��邨�q�l�͂��܂���B
	 */
	public static final SystemMessageId NO_USERS_CHECKED_OUT_GM_ID;
	
	/**
	 * ID: 383<br>
	 * Message: �T�|�[�g�̏I�������N�G�X�g���܂����B
	 */
	public static final SystemMessageId REQUEST_CONFIRMED_TO_END_CONSULTATION;
	
	/**
	 * ID: 384<br>
	 * Message: ���[�U�[���Q�[�� �T�[�o�[�ɐڑ����Ă��܂���B
	 */
	public static final SystemMessageId CLIENT_NOT_LOGGED_ONTO_GAME_SERVER;
	
	/**
	 * ID: 385<br>
	 * Message: �T�|�[�g�̊J�n�����N�G�X�g���܂����B
	 */
	public static final SystemMessageId REQUEST_CONFIRMED_TO_BEGIN_CONSULTATION;
	
	/**
	 * ID: 386<br>
	 * Message: �T�|�[�g�v��������ɂ́A���e��6�����ȏ���͂��Ă��������B
	 */
	public static final SystemMessageId PETITION_MORE_THAN_FIVE_CHARACTERS;
	
	/**
	 * ID: 387<br>
	 * Message: �T�|�[�g���I�����܂����B\n�T�|�[�g�ɑ΂���]�������Ē�����΍K���ł��B
	 */
	public static final SystemMessageId THIS_END_THE_PETITION_PLEASE_PROVIDE_FEEDBACK;
	
	/**
	 * ID: 388<br>
	 * Message: ���݁A�Θb�\��Ԃł͂���܂���B
	 */
	public static final SystemMessageId NOT_UNDER_PETITION_CONSULTATION;
	
	/**
	 * ID: 389<br>
	 * Message: - �T�|�[�g���󂯕t���܂����B\n - ��t�ԍ���$s1�Ԃł��B
	 */
	public static final SystemMessageId PETITION_ACCEPTED_RECENT_NO_S1;
	
	/**
	 * ID: 390<br>
	 * Message: ���łɃT�|�[�g�v�����󂯕t���Ă���܂��B
	 */
	public static final SystemMessageId ONLY_ONE_ACTIVE_PETITION_AT_TIME;
	
	/**
	 * ID: 391<br>
	 * Message: ��t�ԍ�$s1�Ԃ��L�����Z������܂����B
	 */
	public static final SystemMessageId RECENT_NO_S1_CANCELED;
	
	/**
	 * ID: 392<br>
	 * Message: ���݃T�|�[�g�Ή����ł��B
	 */
	public static final SystemMessageId UNDER_PETITION_ADVICE;
	
	/**
	 * ID: 393<br>
	 * Message: �T�|�[�g�v���̃L�����Z���Ɏ��s���܂����B���΂炭���Ă����蒼���Ă��������B
	 */
	public static final SystemMessageId FAILED_CANCEL_PETITION_TRY_LATER;
	
	/**
	 * ID: 394<br>
	 * Message: $c1�Ƃ̃T�|�[�g���J�n���܂����B
	 */
	public static final SystemMessageId STARTING_PETITION_WITH_C1;
	
	/**
	 * ID: 395<br>
	 * Message: $c1�Ƃ̃T�|�[�g���I�����܂����B
	 */
	public static final SystemMessageId PETITION_ENDED_WITH_C1;
	
	/**
	 * ID: 396<br>
	 * Message: �����T�C�g�ihttp://lineage2.plaync.jp/�j�Ńp�X���[�h�̕ύX�܂��̓Z�L�����e�B�J�[�h�̍Ĕ��s�����Ă���ڑ����Ă��������B
	 */
	public static final SystemMessageId TRY_AGAIN_AFTER_CHANGING_PASSWORD;
	
	/**
	 * ID: 397<br>
	 * Message: �L���A�J�E���g�ł͂���܂���B
	 */
	public static final SystemMessageId NO_PAID_ACCOUNT;
	
	/**
	 * ID: 398<br>
	 * Message: ��ʐ��̎c�莞�Ԃ�����܂���B
	 */
	public static final SystemMessageId NO_TIME_LEFT_ON_ACCOUNT;
	
	// 399: empty
	
	/**
	 * ID: 400<br>
	 * Message: $s1���̂Ă܂��B��낵���ł����B
	 */
	public static final SystemMessageId WISH_TO_DROP_S1;
	
	/**
	 * ID: 401<br>
	 * Message: �i�s���̃N�G�X�g�����߂��܂��B
	 */
	public static final SystemMessageId TOO_MANY_QUESTS;
	
	/**
	 * ID: 402<br>
	 * Message: ��D�����Ȃ��Ə�D�ł��܂���B
	 */
	public static final SystemMessageId NOT_CORRECT_BOAT_TICKET;
	
	/**
	 * ID: 403<br>
	 * Message: �����ł���A�f�i�̌��E�𒴂��܂����B
	 */
	public static final SystemMessageId EXCEECED_POCKET_ADENA_LIMIT;
	
	/**
	 * ID: 404<br>
	 * Message: �A�C�e�������X�L���̃��x������߂��܂��B
	 */
	public static final SystemMessageId CREATE_LVL_TOO_LOW_TO_REGISTER;
	
	/**
	 * ID: 405<br>
	 * Message: ���i���i�̑��z���傫�߂��܂��B
	 */
	public static final SystemMessageId TOTAL_PRICE_TOO_HIGH;
	
	/**
	 * ID: 406<br>
	 * Message: �T�|�[�g�̐\�����󂯕t���܂����B
	 */
	public static final SystemMessageId PETITION_APP_ACCEPTED;
	
	/**
	 * ID: 407<br>
	 * Message: ���ݑΉ����̃T�|�[�g�v���ł��B
	 */
	public static final SystemMessageId PETITION_UNDER_PROCESS;
	
	/**
	 * ID: 408<br>
	 * Message: �U���̐ݒ����
	 */
	public static final SystemMessageId SET_PERIOD;
	
	/**
	 * ID: 409<br>
	 * Message: �U���̐ݒ�܂�$s1����$s2��$s3�b�ł��B
	 */
	public static final SystemMessageId SET_TIME_S1_S2_S3;
	
	/**
	 * ID: 410<br>
	 * Message: �U���̓o�^����
	 */
	public static final SystemMessageId REGISTRATION_PERIOD;
	
	/**
	 * ID: 411<br>
	 * Message: �U���̓o�^�܂�$s1����$s2��$s3�b�ł��B
	 */
	public static final SystemMessageId REGISTRATION_TIME_S1_S2_S3;
	
	/**
	 * ID: 412<br>
	 * Message: �U���̊J�n�܂�$s1����$s2��$s4�b�ł��B
	 */
	public static final SystemMessageId BATTLE_BEGINS_S1_S2_S3;
	
	/**
	 * ID: 413<br>
	 * Message: �U���̏I���܂�$s1����$s2��$s5�b�ł��B
	 */
	public static final SystemMessageId BATTLE_ENDS_S1_S2_S3;
	
	/**
	 * ID: 414<br>
	 * Message: �U���̑ҋ@����
	 */
	public static final SystemMessageId STANDBY;
	
	/**
	 * ID: 415<br>
	 * Message: �U��풆
	 */
	public static final SystemMessageId UNDER_SIEGE;
	
	/**
	 * ID: 416<br>
	 * Message: �g���[�h�ł��Ȃ���Ԃł��B
	 */
	public static final SystemMessageId ITEM_CANNOT_EXCHANGE;
	
	/**
	 * ID: 417<br>
	 * Message: $s1�𑕔��������܂����B
	 */
	public static final SystemMessageId S1_DISARMED;
	
	/**
	 * ID: 419<br>
	 * Message: �g�p���Ԃ͎c��$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_USAGE_LEFT;
	
	/**
	 * ID: 420<br>
	 * Message: ��ʎg�p���Ԃ��I�����܂����B
	 */
	public static final SystemMessageId TIME_EXPIRED;
	
	/**
	 * ID: 421<br>
	 * Message: ���̃��[�U�[�������A�J�E���g�Ń��O�C�����܂����B
	 */
	public static final SystemMessageId ANOTHER_LOGIN_WITH_ACCOUNT;
	
	/**
	 * ID: 422<br>
	 * Message: ���e�d�ʂ𒴂��܂����B
	 */
	public static final SystemMessageId WEIGHT_LIMIT_EXCEEDED;
	
	/**
	 * ID: 423<br>
	 * Message: �����X�N���[���̎g�p���L�����Z������܂����B
	 */
	public static final SystemMessageId ENCHANT_SCROLL_CANCELLED;
	
	/**
	 * ID: 424<br>
	 * Message: �X�N���[���̋��������ɍ����܂���B
	 */
	public static final SystemMessageId DOES_NOT_FIT_SCROLL_CONDITIONS;
	
	/**
	 * ID: 425<br>
	 * Message: �A�C�e�������X�L���̃��x������߂��邩�A�A�C�e�������X�L��������܂���B
	 */
	public static final SystemMessageId CREATE_LVL_TOO_LOW_TO_REGISTER2;
	
	/**
	 * ID: 445<br>
	 * Message: �i�މ��t�ԍ��F $s1�j
	 */
	public static final SystemMessageId REFERENCE_MEMBERSHIP_WITHDRAWAL_S1;
	
	/**
	 * ID: 447<br>
	 * Message: �B
	 */
	public static final SystemMessageId DOT;
	
	/**
	 * ID: 448<br>
	 * Message: �V�X�e�� �G���[�ł��B���΂炭���Ă��烍�O�C�����Ă��������B
	 */
	public static final SystemMessageId SYSTEM_ERROR_LOGIN_LATER;
	
	/**
	 * ID: 449<br>
	 * Message: �A�J�E���g�ƃp�X���[�h����v���܂���B
	 */
	public static final SystemMessageId PASSWORD_ENTERED_INCORRECT1;
	
	/**
	 * ID: 450<br>
	 * Message: �A�J�E���g�����Ċm�F���ă��O�C�����Ă��������B
	 */
	public static final SystemMessageId CONFIRM_ACCOUNT_LOGIN_LATER;
	
	/**
	 * ID: 451<br>
	 * Message: �A�J�E���g�ƃp�X���[�h����v���܂���B
	 */
	public static final SystemMessageId PASSWORD_ENTERED_INCORRECT2;
	
	/**
	 * ID: 452<br>
	 * Message: �A�J�E���g�����Ċm�F���ă��O�C�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_CONFIRM_ACCOUNT_LOGIN_LATER;
	
	/**
	 * ID: 453<br>
	 * Message: �A�J�E���g��񂪐���������܂���B
	 */
	public static final SystemMessageId ACCOUNT_INFORMATION_INCORRECT;
	
	/**
	 * ID: 455<br>
	 * Message: ���łɎg�p���̃A�J�E���g�ł��B���O�C���ł��܂���B
	 */
	public static final SystemMessageId ACCOUNT_IN_USE;
	
	/**
	 * ID: 456<br>
	 * Message: ���l�[�W��II �́A15�ˈȏ�̕��݂̂����p���������܂��BPvP�T�[�o�[��18�ˈȏ�̕��݂̂����p���������܂��B
	 */
	public static final SystemMessageId LINAGE_MINIMUM_AGE;
	
	/**
	 * ID: 457<br>
	 * Message: ���݁A�Q�[�� �T�[�o�[�̃����e�i���X���ł��B���΂炭���Ă��烍�O�C�����Ă��������B
	 */
	public static final SystemMessageId SERVER_MAINTENANCE;
	
	/**
	 * ID: 458<br>
	 * Message: �v���C�\���Ԃ��I���������߁A���O�C���ł��܂���B
	 */
	public static final SystemMessageId USAGE_TERM_EXPIRED;
	
	/**
	 * ID: 460<br>
	 * Message: ���F�l�b�g�J�t�F���炲���p�̂��q�l�͓X�܃X�^�b�t�ɂ��m�F���������B
	 */
	public static final SystemMessageId TO_REACTIVATE_YOUR_ACCOUNT;
	
	/**
	 * ID: 461<br>
	 * Message: �Q�[�� �T�[�o�[�̐ڑ��Ɏ��s���܂����B
	 */
	public static final SystemMessageId ACCESS_FAILED;
	
	/**
	 * ID: 461<br>
	 * Message: �Q�[�� �T�[�o�[�̐ڑ��Ɏ��s���܂����B
	 */
	public static final SystemMessageId PLEASE_TRY_AGAIN_LATER;
	
	/**
	 * ID: 464<br>
	 * Message: ������݂̂��s���܂��B
	 */
	public static final SystemMessageId FEATURE_ONLY_FOR_ALLIANCE_LEADER;
	
	/**
	 * ID: 465<br>
	 * Message: �����ɉ������Ă��܂���B
	 */
	public static final SystemMessageId NO_CURRENT_ALLIANCES;
	
	/**
	 * ID: 466<br>
	 * Message: �����Ȃ̂ŐV���������͎󂯓�����܂���B
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_THE_LIMIT;
	
	/**
	 * ID: 467<br>
	 * Message: �����Ǖ���1���o���Ă��Ȃ��ꍇ�A�V���������͎󂯓�����܂���B
	 */
	public static final SystemMessageId CANT_INVITE_CLAN_WITHIN_1_DAY;
	
	/**
	 * ID: 468<br>
	 * Message: �Ǖ����ꂽ��E�ނ��������́A1���ȓ��ɂ͓����ɉ����ł��܂���B
	 */
	public static final SystemMessageId CANT_ENTER_ALLIANCE_WITHIN_1_DAY;
	
	/**
	 * ID: 469<br>
	 * Message: ������֌W�ɂ��錌���Ƃ͓���������ł��܂���B
	 */
	public static final SystemMessageId MAY_NOT_ALLY_CLAN_BATTLE;
	
	/**
	 * ID: 470<br>
	 * Message: ������݂̂������̒E�ސ\�����ł��܂��B
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_WITHDRAW_ALLY;
	
	/**
	 * ID: 471<br>
	 * Message: ������͒E�ނł��܂���B
	 */
	public static final SystemMessageId ALLIANCE_LEADER_CANT_WITHDRAW;
	
	/**
	 * ID: 472<br>
	 * Message: �����̌����͒Ǖ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_EXPEL_YOURSELF;
	
	/**
	 * ID: 473<br>
	 * Message: ���������ɑ����錌���ł͂���܂���B
	 */
	public static final SystemMessageId DIFFERENT_ALLIANCE;
	
	/**
	 * ID: 474<br>
	 * Message: �Y�����������݂��܂���B
	 */
	public static final SystemMessageId CLAN_DOESNT_EXISTS;
	
	/**
	 * ID: 475<br>
	 * Message: ���������ɑ����錌���ł͂���܂���B
	 */
	public static final SystemMessageId DIFFERENT_ALLIANCE2;
	
	/**
	 * ID: 476<br>
	 * Message: �摜�t�@�C���̃T�C�Y������������܂���B8x12�s�N�Z���Ɏw�肵�Ă��������B
	 */
	public static final SystemMessageId ADJUST_IMAGE_8_12;
	
	/**
	 * ID: 477<br>
	 * Message: �������Ȃ��������߁A�����̊��U���L�����Z������܂����B
	 */
	public static final SystemMessageId NO_RESPONSE_TO_ALLY_INVITATION;
	
	/**
	 * ID: 478<br>
	 * Message: ���U�ɉ������Ȃ��������߁A�����̉������L�����Z������܂����B
	 */
	public static final SystemMessageId YOU_DID_NOT_RESPOND_TO_ALLY_INVITATION;
	
	/**
	 * ID: 479<br>
	 * Message: $s1���V�����F�l�ɓo�^����܂����B
	 */
	public static final SystemMessageId S1_JOINED_AS_FRIEND;
	
	/**
	 * ID: 480<br>
	 * Message: �F�l���X�g���m�F���Ă��������B
	 */
	public static final SystemMessageId PLEASE_CHECK_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 481<br>
	 * Message: $s1���F�l���X�g����폜����܂����B
	 */
	public static final SystemMessageId S1_HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 482<br>
	 * Message: �������g��F�l�Ƃ��ēo�^���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIENDS_LIST;
	
	/**
	 * ID: 483<br>
	 * Message: �F�l���X�g���܂��쐬����Ă��܂���B���΂炭���Ă��炲���p���������B
	 */
	public static final SystemMessageId FUNCTION_INACCESSIBLE_NOW;
	
	/**
	 * ID: 484<br>
	 * Message: ���łɗF�l���X�g�ɓo�^����Ă��܂��B
	 */
	public static final SystemMessageId S1_ALREADY_IN_FRIENDS_LIST;
	
	/**
	 * ID: 485<br>
	 * Message: �F�l�ɂȂ邱�Ƃ����߂����[�U�[�����܂���B
	 */
	public static final SystemMessageId NO_NEW_INVITATIONS_ACCEPTED;
	
	/**
	 * ID: 486<br>
	 * Message: �F�l���X�g�ɓo�^����Ă��郆�[�U�[�ł͂���܂���B
	 */
	public static final SystemMessageId THE_USER_NOT_IN_FRIENDS_LIST;
	
	/**
	 * ID: 487<br>
	 * Message: ======<�F�l���X�g>======
	 */
	public static final SystemMessageId FRIEND_LIST_HEADER;
	
	/**
	 * ID: 488<br>
	 * Message: $s1�i�X�e�[�^�X�F�I�����C���j
	 */
	public static final SystemMessageId S1_ONLINE;
	
	/**
	 * ID: 489<br>
	 * Message: $s1�i�X�e�[�^�X�F�I�t���C���j
	 */
	public static final SystemMessageId S1_OFFLINE;
	
	/**
	 * ID: 490<br>
	 * Message: ========================
	 */
	public static final SystemMessageId FRIEND_LIST_FOOTER;
	
	/**
	 * ID: 491<br>
	 * Message: =======<�������>=======
	 */
	public static final SystemMessageId ALLIANCE_INFO_HEAD;
	
	/**
	 * ID: 492<br>
	 * Message: �������F$s1
	 */
	public static final SystemMessageId ALLIANCE_NAME_S1;
	
	/**
	 * ID: 493<br>
	 * Message: �ڑ��F$s1�l/���v$s2�l
	 */
	public static final SystemMessageId CONNECTION_S1_TOTAL_S2;
	
	/**
	 * ID: 494<br>
	 * Message: ������F$s1������$s2
	 */
	public static final SystemMessageId ALLIANCE_LEADER_S2_OF_S1;
	
	/**
	 * ID: 495<br>
	 * Message: ���������F���v$s1����
	 */
	public static final SystemMessageId ALLIANCE_CLAN_TOTAL_S1;
	
	/**
	 * ID: 496<br>
	 * Message: =====<���������̏��>=====
	 */
	public static final SystemMessageId CLAN_INFO_HEAD;
	
	/**
	 * ID: 497<br>
	 * Message: �������F$s1
	 */
	public static final SystemMessageId CLAN_INFO_NAME_S1;
	
	/**
	 * ID: 498<br>
	 * Message: ������F$s1
	 */
	public static final SystemMessageId CLAN_INFO_LEADER_S1;
	
	/**
	 * ID: 499<br>
	 * Message: �������x���F$s1
	 */
	public static final SystemMessageId CLAN_INFO_LEVEL_S1;
	
	/**
	 * ID: 500<br>
	 * Message: ------------------------
	 */
	public static final SystemMessageId CLAN_INFO_SEPARATOR;
	
	/**
	 * ID: 501<br>
	 * Message: ========================
	 */
	public static final SystemMessageId CLAN_INFO_FOOT;
	
	/**
	 * ID: 502<br>
	 * Message: ���łɓ����ɉ������Ă��܂��B
	 */
	public static final SystemMessageId ALREADY_JOINED_ALLIANCE;
	
	/**
	 * ID: 503<br>
	 * Message: �F�l$s1�����O�C�����܂����B
	 */
	public static final SystemMessageId FRIEND_S1_HAS_LOGGED_IN;
	
	/**
	 * ID: 504<br>
	 * Message: ������݂̂�������n�݂ł��܂��B
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_CREATE_ALLIANCE;
	
	/**
	 * ID: 505<br>
	 * Message: �������U��̓����͐V���ȓ����̑n�݂͂ł��܂���B
	 */
	public static final SystemMessageId CANT_CREATE_ALLIANCE_10_DAYS_DISOLUTION;
	
	/**
	 * ID: 506<br>
	 * Message: ������������������܂���B
	 */
	public static final SystemMessageId INCORRECT_ALLIANCE_NAME;
	
	/**
	 * ID: 507<br>
	 * Message: �����������߂��܂��B
	 */
	public static final SystemMessageId INCORRECT_ALLIANCE_NAME_LENGTH;
	
	/**
	 * ID: 508<br>
	 * Message: ���łɑ��݂��铯���ł��B
	 */
	public static final SystemMessageId ALLIANCE_ALREADY_EXISTS;
	
	/**
	 * ID: 509<br>
	 * Message: �������̌����ƍU���œG�֌W�Ƃ��Đ\������Ă���̂ŁA��t�ł��܂���B
	 */
	public static final SystemMessageId CANT_ACCEPT_ALLY_ENEMY_FOR_SIEGE;
	
	/**
	 * ID: 510<br>
	 * Message: �����ɏ��҂��܂����B
	 */
	public static final SystemMessageId YOU_INVITED_FOR_ALLIANCE;
	
	/**
	 * ID: 511<br>
	 * Message: �����ɏ��҂���l��I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_USER_TO_INVITE;
	
	/**
	 * ID: 512<br>
	 * Message: �{���ɓ�������E�ނ��܂����B�E�ތ�1���ԁA���̓����ɍĉ����ł��܂���B
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_WITHDRW;
	
	/**
	 * ID: 513<br>
	 * Message: �Ǖ����鑊��̌���������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_NAME_CLAN_TO_EXPEL;
	
	/**
	 * ID: 514<br>
	 * Message: �{���ɉ��U���܂����B���U�����͓������đn�݂ł��܂���B
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_DISOLVE;
	
	/**
	 * ID: 516<br>
	 * Message: $s1�����Ȃ���F�l�Ƃ��ď��҂��܂����B
	 */
	public static final SystemMessageId SI_INVITED_YOU_AS_FRIEND;
	
	/**
	 * ID: 517<br>
	 * Message: �����������܂����B
	 */
	public static final SystemMessageId YOU_ACCEPTED_ALLIANCE;
	
	/**
	 * ID: 518<br>
	 * Message: �����̏��҂Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_INVITE_CLAN_IN_ALLIANCE;
	
	/**
	 * ID: 519<br>
	 * Message: ��������E�ނ��܂����B
	 */
	public static final SystemMessageId YOU_HAVE_WITHDRAWN_FROM_ALLIANCE;
	
	/**
	 * ID: 520<br>
	 * Message: �����̒E�ނɎ��s���܂����B
	 */
	public static final SystemMessageId YOU_HAVE_FAILED_TO_WITHDRAWN_FROM_ALLIANCE;
	
	/**
	 * ID: 521<br>
	 * Message: �����̒Ǖ��ɐ������܂����B
	 */
	public static final SystemMessageId YOU_HAVE_EXPELED_A_CLAN;
	
	/**
	 * ID: 522<br>
	 * Message: �����̒Ǖ��Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_EXPELED_A_CLAN;
	
	/**
	 * ID: 523<br>
	 * Message: ���������U���܂����B
	 */
	public static final SystemMessageId ALLIANCE_DISOLVED;
	
	/**
	 * ID: 524<br>
	 * Message: �����̉��U�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_DISOLVE_ALLIANCE;
	
	/**
	 * ID: 525<br>
	 * Message: �F�l�̏��҂ɐ������܂����B
	 */
	public static final SystemMessageId YOU_HAVE_SUCCEEDED_INVITING_FRIEND;
	
	/**
	 * ID: 526<br>
	 * Message: �F�l�̏��҂Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_INVITE_A_FRIEND;
	
	/**
	 * ID: 527<br>
	 * Message: $s1�����̓�����$s2�ɓ�����\�����܂�܂����B
	 */
	public static final SystemMessageId S2_ALLIANCE_LEADER_OF_S1_REQUESTED_ALLIANCE;
	
	/**
	 * ID: 530<br>
	 * Message: �X�s���b�g �V���b�g������̃O���[�h�ƈ�v���܂���B
	 */
	public static final SystemMessageId SPIRITSHOTS_GRADE_MISMATCH;
	
	/**
	 * ID: 531<br>
	 * Message: �X�s���b�g �V���b�g������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SPIRITSHOTS;
	
	/**
	 * ID: 532<br>
	 * Message: �X�s���b�g �V���b�g���g���܂���B
	 */
	public static final SystemMessageId CANNOT_USE_SPIRITSHOTS;
	
	/**
	 * ID: 533<br>
	 * Message: �}�i�̗͂��g���܂��B
	 */
	public static final SystemMessageId ENABLED_SPIRITSHOT;
	
	/**
	 * ID: 534<br>
	 * Message: �}�i�̗͂��Ȃ��Ȃ�܂����B
	 */
	public static final SystemMessageId DISABLED_SPIRITSHOT;
	
	/**
	 * ID: 536<br>
	 * Message: �A�f�i���ǂꂭ�炢�C���x���g���Ɉڂ��܂����B
	 */
	public static final SystemMessageId HOW_MUCH_ADENA_TRANSFER;
	
	/**
	 * ID: 537<br>
	 * Message: �����ڂ��܂����B
	 */
	public static final SystemMessageId HOW_MUCH_TRANSFER;
	
	/**
	 * ID: 538<br>
	 * Message: SP��$s1����܂����B
	 */
	public static final SystemMessageId SP_DECREASED_S1;
	
	/**
	 * ID: 539<br>
	 * Message: �o���l��$s1����܂����B
	 */
	public static final SystemMessageId EXP_DECREASED_BY_S1;
	
	/**
	 * ID: 540<br>
	 * Message: ������͍폜�ł��܂���B���������U���Ă����蒼���Ă݂Ă��������B
	 */
	public static final SystemMessageId CLAN_LEADERS_MAY_NOT_BE_DELETED;
	
	/**
	 * ID: 541<br>
	 * Message: �������͍폜�ł��܂���B������E�ނ��Ă����蒼���Ă݂Ă��������B
	 */
	public static final SystemMessageId CLAN_MEMBER_MAY_NOT_BE_DELETED;
	
	/**
	 * ID: 542<br>
	 * Message: NPC�T�[�o�[���쓮���Ă��Ȃ����߁A�y�b�g�������ł��܂���B
	 */
	public static final SystemMessageId THE_NPC_SERVER_IS_CURRENTLY_DOWN;
	
	/**
	 * ID: 543<br>
	 * Message: ���łɃy�b�g�����܂��B
	 */
	public static final SystemMessageId YOU_ALREADY_HAVE_A_PET;
	
	/**
	 * ID: 544<br>
	 * Message: �y�b�g�ɓn���Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId ITEM_NOT_FOR_PETS;
	
	/**
	 * ID: 545<br>
	 * Message: �y�b�g�̃C���x���g���̃X���b�g�������ɂ��A�C�e�����n���܂���B
	 */
	public static final SystemMessageId YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS;
	
	/**
	 * ID: 546<br>
	 * Message: �y�b�g�p�C���x���g���̏d�ʐ����ɂ��A�C�e�����n���܂���B
	 */
	public static final SystemMessageId UNABLE_TO_PLACE_ITEM_YOUR_PET_IS_TOO_ENCUMBERED;
	
	/**
	 * ID: 547<br>
	 * Message: �y�b�g���������܂��B
	 */
	public static final SystemMessageId SUMMON_A_PET;
	
	/**
	 * ID: 548<br>
	 * Message: �y�b�g�̖��O��8�����܂ŉ\�ł��B
	 */
	public static final SystemMessageId NAMING_PETNAME_UP_TO_8CHARS;
	
	/**
	 * ID: 549<br>
	 * Message: �������������ɂ́A�����̃��x����5�ȏ�łȂ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId TO_CREATE_AN_ALLY_YOU_CLAN_MUST_BE_LEVEL_5_OR_HIGHER;
	
	/**
	 * ID: 550<br>
	 * Message: ���U�P�\���Ԓ��͓����̑n�݂͂ł��܂���B
	 */
	public static final SystemMessageId YOU_MAY_NOT_CREATE_ALLY_WHILE_DISSOLVING;
	
	/**
	 * ID: 551<br>
	 * Message: ���U�P�\���Ԓ��͌������x�����グ���܂���B
	 */
	public static final SystemMessageId CANNOT_RISE_LEVEL_WHILE_DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 552<br>
	 * Message: ���U�P�\���Ԓ��̓G���u�����̓o�^����э폜�͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_SET_CREST_WHILE_DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 553<br>
	 * Message: ����̌����͉��U���\�����܂�Ă��܂��B
	 */
	public static final SystemMessageId OPPOSING_CLAN_APPLIED_DISPERSION;
	
	/**
	 * ID: 554<br>
	 * Message: �����ɑ����錌���͉��U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISPERSE_THE_CLANS_IN_ALLY;
	
	/**
	 * ID: 555<br>
	 * Message: �����Ă���A�C�e�����d�߂��ē����܂���B
	 */
	public static final SystemMessageId CANT_MOVE_TOO_ENCUMBERED;
	
	/**
	 * ID: 556<br>
	 * Message: ���݂̏�Ԃł͓����܂���B
	 */
	public static final SystemMessageId CANT_MOVE_IN_THIS_STATE;
	
	/**
	 * ID: 557<br>
	 * Message: �y�b�g����������Ă��邽�߁A�j��ł��܂���B
	 */
	public static final SystemMessageId PET_SUMMONED_MAY_NOT_DESTROYED;
	
	/**
	 * ID: 558<br>
	 * Message: �y�b�g����������Ă��邽�߁A�n�ʂɎ̂Ă��܂���B
	 */
	public static final SystemMessageId PET_SUMMONED_MAY_NOT_LET_GO;
	
	/**
	 * ID: 559<br>
	 * Message: $c1����$s2�𔃂��܂����B
	 */
	public static final SystemMessageId PURCHASED_S2_FROM_C1;
	
	/**
	 * ID: 560<br>
	 * Message: $c1����+$s2$s3�𔃂��܂����B
	 */
	public static final SystemMessageId PURCHASED_S2_S3_FROM_C1;
	
	/**
	 * ID: 561<br>
	 * Message: $c1����$s2 $s3�𔃂��܂����B
	 */
	public static final SystemMessageId PURCHASED_S3_S2_S_FROM_C1;
	
	/**
	 * ID: 562<br>
	 * Message: �N���X�^���C�Y�̃X�L�� ���x�����Ⴂ�̂ŃN���X�^���C�Y�ł��܂���B
	 */
	public static final SystemMessageId CRYSTALLIZE_LEVEL_TOO_LOW;
	
	/**
	 * ID: 563<br>
	 * Message: �U���~���̉����Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_DISABLE_TARGET;
	
	/**
	 * ID: 564<br>
	 * Message: �U���~���̕ύX�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_CHANGE_TARGET;
	
	/**
	 * ID: 565<br>
	 * Message: �^��������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_LUCK;
	
	/**
	 * ID: 566<br>
	 * Message: �����Ɏ��s���܂����B
	 */
	public static final SystemMessageId CONFUSION_FAILED;
	
	/**
	 * ID: 567<br>
	 * Message: �t�B�A�[�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FEAR_FAILED;
	
	/**
	 * ID: 568<br>
	 * Message: �L���[�r�b�N�̏����Ɏ��s���܂����B
	 */
	public static final SystemMessageId CUBIC_SUMMONING_FAILED;
	
	/**
	 * ID: 572<br>
	 * Message: $c1�̃p�[�e�B�[���U�ɉ����܂����B�i�A�C�e�����z�F�E�����l�����L�j
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_FINDERS_KEEPERS;
	
	/**
	 * ID: 573<br>
	 * Message: $c1�̃p�[�e�B�[���U�ɉ����܂����B�i�A�C�e�����z�F�p�[�e�B�[�����o�[�Ƀ����_���Łj
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_RANDOM;
	
	/**
	 * ID: 574<br>
	 * Message: �����b��y�b�g�����܂���B
	 */
	public static final SystemMessageId PETS_ARE_NOT_AVAILABLE_AT_THIS_TIME;
	
	/**
	 * ID: 575<br>
	 * Message: ������̃A�f�i���y�b�g�Ɉڂ��܂����B
	 */
	public static final SystemMessageId HOW_MUCH_ADENA_TRANSFER_TO_PET;
	
	/**
	 * ID: 576<br>
	 * Message: �����ڂ��܂����B
	 */
	public static final SystemMessageId HOW_MUCH_TRANSFER2;
	
	/**
	 * ID: 577<br>
	 * Message: �g���[�h��l���X���ɂ͏����ł��܂���B
	 */
	public static final SystemMessageId CANNOT_SUMMON_DURING_TRADE_SHOP;
	
	/**
	 * ID: 578<br>
	 * Message: �퓬���͏����ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_SUMMON_IN_COMBAT;
	
	/**
	 * ID: 579<br>
	 * Message: �퓬���̃y�b�g�͖߂��܂���B
	 */
	public static final SystemMessageId PET_CANNOT_SENT_BACK_DURING_BATTLE;
	
	/**
	 * ID: 580<br>
	 * Message: �����̃y�b�g�⏢���b����x�Ɏg�����Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId SUMMON_ONLY_ONE;
	
	/**
	 * ID: 581<br>
	 * Message: ���O�ɃX�y�[�X�������Ă��܂��B
	 */
	public static final SystemMessageId NAMING_THERE_IS_A_SPACE;
	
	/**
	 * ID: 582<br>
	 * Message: �֎~���ꂽ�L�����N�^�[���ł��B
	 */
	public static final SystemMessageId NAMING_INAPPROPRIATE_CHARACTER_NAME;
	
	/**
	 * ID: 583<br>
	 * Message: ���O�ɋ֎~���ꂽ�P�ꂪ�����Ă��܂��B
	 */
	public static final SystemMessageId NAMING_INCLUDES_FORBIDDEN_WORDS;
	
	/**
	 * ID: 584<br>
	 * Message: ���łɓ������O�̃y�b�g�����܂��B
	 */
	public static final SystemMessageId NAMING_ALREADY_IN_USE_BY_ANOTHER_PET;
	
	/**
	 * ID: 585<br>
	 * Message: �w�����i�����߂Ă��������B
	 */
	public static final SystemMessageId DECIDE_ON_PRICE;
	
	/**
	 * ID: 586<br>
	 * Message: �y�b�g�̃A�C�e���̓V���[�g�J�b�g�ɓo�^�ł��܂���B
	 */
	public static final SystemMessageId PET_NO_SHORTCUT;
	
	/**
	 * ID: 588<br>
	 * Message: �y�b�g�̃C���x���g���������ς��ł��B
	 */
	public static final SystemMessageId PET_INVENTORY_FULL;
	
	/**
	 * ID: 589<br>
	 * Message: ���񂾃y�b�g��߂����Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId DEAD_PET_CANNOT_BE_RETURNED;
	
	/**
	 * ID: 590<br>
	 * Message: ���񂾃y�b�g�ɃA�C�e���͈ړ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_GIVE_ITEMS_TO_DEAD_PET;
	
	/**
	 * ID: 591<br>
	 * Message: �y�b�g�̖��O�ɖ����̕����������Ă��܂��B
	 */
	public static final SystemMessageId NAMING_PETNAME_CONTAINS_INVALID_CHARS;
	
	/**
	 * ID: 592<br>
	 * Message: �{���ɉ��U���܂����B���U����Ə����A�C�e���͏����܂��B
	 */
	public static final SystemMessageId WISH_TO_DISMISS_PET;
	
	/**
	 * ID: 593<br>
	 * Message: �Q�������炦���ꂸ�A�y�b�g�����Ȃ��̌�������܂����B
	 */
	public static final SystemMessageId STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT;
	
	/**
	 * ID: 594<br>
	 * Message: �Q�����y�b�g�͖߂����Ƃ��ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_RESTORE_HUNGRY_PETS;
	
	/**
	 * ID: 595<br>
	 * Message: �y�b�g�����܂�ɂ��������������Ă��܂��B
	 */
	public static final SystemMessageId YOUR_PET_IS_VERY_HUNGRY;
	
	/**
	 * ID: 596<br>
	 * Message: �y�b�g�͂ǂ��ɂ��Q�������̂��܂������A�ˑR�Ƃ��Ă������������Ă��܂��B
	 */
	public static final SystemMessageId YOUR_PET_ATE_A_LITTLE_BUT_IS_STILL_HUNGRY;
	
	/**
	 * ID: 597<br>
	 * Message: �y�b�g���Q���ɂ��܂�ł��Ȃ��ł��܂��B�C�����Ă��������B
	 */
	public static final SystemMessageId YOUR_PET_IS_VERY_HUNGRY_PLEASE_BE_CAREFULL;
	
	/**
	 * ID: 598<br>
	 * Message: ������Ԃ̂��߁A��ʃ`���b�g�͂ł��܂���B
	 */
	public static final SystemMessageId NOT_CHAT_WHILE_INVISIBLE;
	
	/**
	 * ID: 599<br>
	 * Message: �T�|�[�g����d�v�Ȃ��m�点������܂��B�`���b�g�͂��΂炭���~����܂��B
	 */
	public static final SystemMessageId GM_NOTICE_CHAT_DISABLED;
	
	/**
	 * ID: 600<br>
	 * Message: �y�b�g �A�C�e���͑����ł��܂���B
	 */
	public static final SystemMessageId CANNOT_EQUIP_PET_ITEM;
	
	/**
	 * ID: 601<br>
	 * Message: - ��t�̑ҋ@�Ґ���$s1�ł��B
	 */
	public static final SystemMessageId S1_PETITION_ON_WAITING_LIST;
	
	/**
	 * ID: 602<br>
	 * Message: ���݁A�T�|�[�g�V�X�e�����쓮���Ă��܂���B���΂炭���Ă����蒼���Ă��������B
	 */
	public static final SystemMessageId PETITION_SYSTEM_CURRENT_UNAVAILABLE;
	
	/**
	 * ID: 603<br>
	 * Message: ���̃A�C�e���͎̂Ă���g���[�h������ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISCARD_EXCHANGE_ITEM;
	
	/**
	 * ID: 604<br>
	 * Message: �����ł̓y�b�g�⏢���b�������ł��܂���B
	 */
	public static final SystemMessageId NOT_CALL_PET_FROM_THIS_LOCATION;
	
	/**
	 * ID: 605<br>
	 * Message: �F�l���X�g�ɂ͍ő�128�l�܂œo�^�ł��܂��B
	 */
	public static final SystemMessageId MAY_REGISTER_UP_TO_64_PEOPLE;
	
	/**
	 * ID: 606<br>
	 * Message: ����̗F�l���X�g�ɓo�^���ꂽ�l����128�l�𒴂����̂ŁA�o�^�ł��܂���B
	 */
	public static final SystemMessageId OTHER_PERSON_ALREADY_64_PEOPLE;
	
	/**
	 * ID: 607<br>
	 * Message: ����ȏ�K���X�L��������܂���B$s1���x���ɂȂ��Ă��痈�Ă��������B
	 */
	public static final SystemMessageId DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1;
	
	/**
	 * ID: 608<br>
	 * Message: $c1���X�E�B�[�p�[��$s2 $s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_SWEEPED_UP_S3_S2;
	
	/**
	 * ID: 609<br>
	 * Message: $c1���X�E�B�[�p�[��$s2����ɓ���܂����B
	 */
	public static final SystemMessageId C1_SWEEPED_UP_S2;
	
	/**
	 * ID: 610<br>
	 * Message: HP������Ȃ��������߃X�L������������܂����B
	 */
	public static final SystemMessageId SKILL_REMOVED_DUE_LACK_HP;
	
	/**
	 * ID: 611<br>
	 * Message: �G��f�킷���Ƃɐ������܂����B
	 */
	public static final SystemMessageId CONFUSING_SUCCEEDED;
	
	/**
	 * ID: 612<br>
	 * Message: �X�|�C����ԂɂȂ�܂����B
	 */
	public static final SystemMessageId SPOIL_SUCCESS;
	
	/**
	 * ID: 613<br>
	 * Message: ======<�Ւf���X�g>======
	 */
	public static final SystemMessageId BLOCK_LIST_HEADER;
	
	/**
	 * ID: 614<br>
	 * Message: $c1�F$c2
	 */
	public static final SystemMessageId C1_D_C2;
	
	/**
	 * ID: 615<br>
	 * Message: �Ւf���X�g�ւ̓o�^�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_REGISTER_TO_IGNORE_LIST;
	
	/**
	 * ID: 616<br>
	 * Message: �Ւf���X�g����̍폜�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_DELETE_CHARACTER;
	
	/**
	 * ID: 617<br>
	 * Message: $s1���Ւf���X�g�ɓo�^���܂����B
	 */
	public static final SystemMessageId S1_WAS_ADDED_TO_YOUR_IGNORE_LIST;
	
	/**
	 * ID: 618<br>
	 * Message: $s1���Ւf���X�g����폜���܂����B
	 */
	public static final SystemMessageId S1_WAS_REMOVED_FROM_YOUR_IGNORE_LIST;
	
	/**
	 * ID: 619<br>
	 * Message: $s1�����Ȃ����Ւf���܂����B
	 */
	public static final SystemMessageId S1_HAS_ADDED_YOU_TO_IGNORE_LIST;
	
	/**
	 * ID: 620<br>
	 * Message: $s1�͂��Ȃ����Ւf���܂����B
	 */
	public static final SystemMessageId S1_HAS_ADDED_YOU_TO_IGNORE_LIST2;
	
	/**
	 * ID: 621<br>
	 * Message: �ڑ����������ꂽIP��ʂ��ăQ�[���ɐڑ������݂܂����B
	 */
	public static final SystemMessageId CONNECTION_RESTRICTED_IP;
	
	/**
	 * ID: 622<br>
	 * Message: �����풆�ɐ��z���͂ł��܂���B
	 */
	public static final SystemMessageId NO_WAR_DURING_ALLY_BATTLE;
	
	/**
	 * ID: 623<br>
	 * Message: ����̓������i�s�ł��铯����̐��𒴂��Ă��܂��B
	 */
	public static final SystemMessageId OPPONENT_TOO_MUCH_ALLY_BATTLES1;
	
	/**
	 * ID: 624<br>
	 * Message: $s1�����̓����傪�ڑ����Ă��܂���B
	 */
	public static final SystemMessageId S1_LEADER_NOT_CONNECTED;
	
	/**
	 * ID: 625<br>
	 * Message: ������̏I��v�������ۂ���܂����B
	 */
	public static final SystemMessageId ALLY_BATTLE_TRUCE_DENIED;
	
	/**
	 * ID: 626<br>
	 * Message: $s1�������������Ȃ��������߁A���z�������ۂ���܂����B
	 */
	public static final SystemMessageId WAR_PROCLAMATION_HAS_BEEN_REFUSED;
	
	/**
	 * ID: 627<br>
	 * Message: $s1�����̐��z���ɉ������Ȃ��������߁A�����킪���ۂ���܂����B
	 */
	public static final SystemMessageId YOU_REFUSED_CLAN_WAR_PROCLAMATION;
	
	/**
	 * ID: 628<br>
	 * Message: $s1 �����Ƃ͂��łɓ�������s�������߁A�O��̓����킩��5�����o�߂��Ȃ��Ɛ��z���ł��܂���B
	 */
	public static final SystemMessageId ALREADY_AT_WAR_WITH_S1_WAIT_5_DAYS;
	
	/**
	 * ID: 629<br>
	 * Message: ����̌������i�s�ł��錌����̐��𒴂��Ă��܂��B
	 */
	public static final SystemMessageId OPPONENT_TOO_MUCH_ALLY_BATTLES2;
	
	/**
	 * ID: 630<br>
	 * Message: $s1�����Ƃ̓����킪�n�܂�܂����B
	 */
	public static final SystemMessageId WAR_WITH_CLAN_BEGUN;
	
	/**
	 * ID: 631<br>
	 * Message: $s1�����Ƃ̓����킪�I�����܂����B
	 */
	public static final SystemMessageId WAR_WITH_CLAN_ENDED;
	
	/**
	 * ID: 632<br>
	 * Message: $s1�����Ƃ̓�����ŏ������܂����I
	 */
	public static final SystemMessageId WON_WAR_OVER_CLAN;
	
	/**
	 * ID: 633<br>
	 * Message: $s1�����ɍ~�����܂����B
	 */
	public static final SystemMessageId SURRENDERED_TO_CLAN;
	
	/**
	 * ID: 634<br>
	 * Message: �����傪���S���āA$s1�����ɔs�k���܂����B
	 */
	public static final SystemMessageId DEFEATED_BY_CLAN;
	
	/**
	 * ID: 635<br>
	 * Message: ������̐������Ԃ��߂������߁A$s1�����Ƃ̓����킪�I�����܂����B
	 */
	public static final SystemMessageId TIME_UP_WAR_OVER;
	
	/**
	 * ID: 636<br>
	 * Message: �����풆�ł͂���܂���B
	 */
	public static final SystemMessageId NOT_INVOLVED_IN_WAR;
	
	/**
	 * ID: 637<br>
	 * Message: ���������̌������G���ɓo�^���܂����B
	 */
	public static final SystemMessageId ALLY_REGISTERED_SELF_TO_OPPONENT;
	
	/**
	 * ID: 638<br>
	 * Message: ���łɍU����\��������Ԃł��B
	 */
	public static final SystemMessageId ALREADY_REQUESTED_SIEGE_BATTLE;
	
	/**
	 * ID: 639<br>
	 * Message: ���̍U���ɐ\��������ԂȂ̂Ő\���ł��܂���B
	 */
	public static final SystemMessageId APPLICATION_DENIED_BECAUSE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_SIEGE_BATTLE;
	
	// 640 - 641: empty
	
	/**
	 * ID: 642<br>
	 * Message: ���łɍU�����ɓo�^����Ă��邽�ߐ\���ł��܂���B�U�����̓o�^���L�����Z�����Ă��������x��蒼���Ă��������B
	 */
	public static final SystemMessageId ALREADY_ATTACKER_NOT_CANCEL;
	
	/**
	 * ID: 643<br>
	 * Message: ���łɎ�����ɓo�^����Ă��邽�ߐ\���ł��܂���B������̓o�^���L�����Z�����Ă��������x��蒼���Ă��������B
	 */
	public static final SystemMessageId ALREADY_DEFENDER_NOT_CANCEL;
	
	/**
	 * ID: 644<br>
	 * Message: �܂��U���ɓo�^����Ă��܂���B
	 */
	public static final SystemMessageId NOT_REGISTERED_FOR_SIEGE;
	
	/**
	 * ID: 645<br>
	 * Message: ���x��5�ȏ�̌����݂̂��U���ɓo�^�ł��܂��B
	 */
	public static final SystemMessageId ONLY_CLAN_LEVEL_5_ABOVE_MAY_SIEGE;
	
	// 646 - 647: empty
	
	/**
	 * ID: 648<br>
	 * Message: ����ȏ�U�����ɓo�^�ł��܂���B
	 */
	public static final SystemMessageId ATTACKER_SIDE_FULL;
	
	/**
	 * ID: 649<br>
	 * Message: ����ȏ������ɐ\���ł��܂���B
	 */
	public static final SystemMessageId DEFENDER_SIDE_FULL;
	
	/**
	 * ID: 650<br>
	 * Message: ���ݒn�ł͏����ł��܂���B
	 */
	public static final SystemMessageId YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION;
	
	/**
	 * ID: 651<br>
	 * Message: $s1�����ݗ����Ă���ʒu�ƕ����ɔz�u���܂��B��낵���ł����B
	 */
	public static final SystemMessageId PLACE_CURRENT_LOCATION_DIRECTION;
	
	/**
	 * ID: 652<br>
	 * Message: �����b�̃^�[�Q�b�g������������܂���B
	 */
	public static final SystemMessageId TARGET_OF_SUMMON_WRONG;
	
	/**
	 * ID: 653<br>
	 * Message: �b����z�u���錠��������܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_AUTHORITY_TO_POSITION_MERCENARIES;
	
	/**
	 * ID: 654<br>
	 * Message: �b���z�u���L�����Z�����錠��������܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING;
	
	/**
	 * ID: 655<br>
	 * Message: �b�����������Ă����ł͂Ȃ����߁A�z�u�ł��܂���B
	 */
	public static final SystemMessageId MERCENARIES_CANNOT_BE_POSITIONED_HERE;
	
	/**
	 * ID: 656<br>
	 * Message: ���̗b���͔z�u�ł��܂���B
	 */
	public static final SystemMessageId THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE;
	
	/**
	 * ID: 657<br>
	 * Message: �b�����m�̊Ԋu�����߂��邽�߁A�z�u�ł��܂���B
	 */
	public static final SystemMessageId POSITIONING_CANNOT_BE_DONE_BECAUSE_DISTANCE_BETWEEN_MERCENARIES_TOO_SHORT;
	
	/**
	 * ID: 658<br>
	 * Message: ���L���Ă����̗b���ł͂Ȃ����߁A�z�u���L�����Z���ł��܂���B
	 */
	public static final SystemMessageId THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_CANNOT_CANCEL_POSITIONING;
	
	/**
	 * ID: 659<br>
	 * Message: �U��̓o�^���Ԃł͂Ȃ����߁A���F�����ۂ��ł��܂���B
	 */
	public static final SystemMessageId NOT_SIEGE_REGISTRATION_TIME1;
	
	/**
	 * ID: 659<br>
	 * Message: �U��̓o�^���Ԃł͂Ȃ����߁A���F�����ۂ��ł��܂���B
	 */
	public static final SystemMessageId NOT_SIEGE_REGISTRATION_TIME2;
	
	/**
	 * ID: 661<br>
	 * Message: �X�|�C���ł��Ȃ��L�����N�^�[�ł��B
	 */
	public static final SystemMessageId SPOIL_CANNOT_USE;
	
	/**
	 * ID: 662<br>
	 * Message: ���肪�F�B�̏��҂����ۂ��Ă����Ԃł��B
	 */
	public static final SystemMessageId THE_PLAYER_IS_REJECTING_FRIEND_INVITATIONS;
	
	// 663 will crash client
	
	/**
	 * ID: 664<br>
	 * Message: ���l��I�����Ă��������B
	 */
	public static final SystemMessageId CHOOSE_PERSON_TO_RECEIVE;
	
	/**
	 * ID: 665<br>
	 * Message: $s1������$s2�ɓ������\�����܂�܂����B������󂯓���܂����B
	 */
	public static final SystemMessageId APPLYING_ALLIANCE_WAR;
	
	/**
	 * ID: 666<br>
	 * Message: $s1 �����ɏI���\�����܂�܂����B���ӂ��܂����B
	 */
	public static final SystemMessageId REQUEST_FOR_CEASEFIRE;
	
	/**
	 * ID: 667<br>
	 * Message: $s1�U���ɍU�����Ƃ��ēo�^���܂��B��낵���ł����B
	 */
	public static final SystemMessageId REGISTERING_ON_ATTACKING_SIDE;
	
	/**
	 * ID: 668<br>
	 * Message: $s1�U���Ɏ�����Ƃ��ēo�^���܂��B��낵���ł����B
	 */
	public static final SystemMessageId REGISTERING_ON_DEFENDING_SIDE;
	
	/**
	 * ID: 669<br>
	 * Message: $s1�U���ւ̎Q��\�����L�����Z�����܂��B��낵���ł����B
	 */
	public static final SystemMessageId CANCELING_REGISTRATION;
	
	/**
	 * ID: 670<br>
	 * Message: $s1�����̎�����o�^�����ۂ��܂��B��낵���ł����B
	 */
	public static final SystemMessageId REFUSING_REGISTRATION;
	
	/**
	 * ID: 671<br>
	 * Message: $s1�����̎�����o�^�����F���܂��B��낵���ł����B
	 */
	public static final SystemMessageId AGREEING_REGISTRATION;
	
	/**
	 * ID: 672<br>
	 * Message: $s1�A�f�i�������܂����B
	 */
	public static final SystemMessageId S1_DISAPPEARED_ADENA;
	
	/**
	 * ID: 673<br>
	 * Message: �A�W�g�̋����ɂ͌������x��2�ȏ�̌�����̂ݎQ���ł��܂��B
	 */
	public static final SystemMessageId AUCTION_ONLY_CLAN_LEVEL_2_HIGHER;
	
	/**
	 * ID: 674<br>
	 * Message: �������L�����Z�����Ă���7�����߂��Ă��܂���B
	 */
	public static final SystemMessageId NOT_SEVEN_DAYS_SINCE_CANCELING_AUCTION;
	
	/**
	 * ID: 675<br>
	 * Message: �������̃A�W�g�ł͂���܂���B
	 */
	public static final SystemMessageId NO_CLAN_HALLS_UP_FOR_AUCTION;
	
	/**
	 * ID: 676<br>
	 * Message: ���݋����ɓ��D���̂��߁A���̋����ɂ͎Q���ł��܂���B
	 */
	public static final SystemMessageId ALREADY_SUBMITTED_BID;
	
	/**
	 * ID: 677<br>
	 * Message: ���D���i�͍Œ���D�\���i��荂���Ȃ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId BID_PRICE_MUST_BE_HIGHER;
	
	/**
	 * ID: 678<br>
	 * Message: $s1�̋����ɓ��D���܂����B
	 */
	public static final SystemMessageId SUBMITTED_A_BID;
	
	/**
	 * ID: 679<br>
	 * Message: ���D���L�����Z�����܂����B
	 */
	public static final SystemMessageId CANCELED_BID;
	
	/**
	 * ID: 680<br>
	 * Message: �����ɎQ���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_PARTICIPATE_IN_AN_AUCTION;
	
	/**
	 * ID: 681<br>
	 * Message: �������A�W�g�����L���Ă��܂���B
	 */
	//CLAN_HAS_NO_CLAN_HALL(681) // Doesn't exist in Hellbound anymore
	
	
	
	/**
	 * ID: 683<br>
	 * Message: �X�E�B�[�p�[�ɑ΂���D�挠������܂���B
	 */
	public static final SystemMessageId SWEEP_NOT_ALLOWED;
	
	/**
	 * ID: 684<br>
	 * Message: �U�钆�ɂ͗b����z�u�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_POSITION_MERCS_DURING_SIEGE;
	
	/**
	 * ID: 685<br>
	 * Message: ���������ɑ����錌���Ɍ������\�����ނ��Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DECLARE_WAR_ON_ALLY;
	
	/**
	 * ID: 686<br>
	 * Message: ���@�̉΂ɂ����$s1�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId S1_DAMAGE_FROM_FIRE_MAGIC;
	
	/**
	 * ID: 687<br>
	 * Message: �t���[�Y��Ԃł͍s���ł��܂���B���΂炭���҂����������B
	 */
	public static final SystemMessageId CANNOT_MOVE_FROZEN;
	
	/**
	 * ID: 688<br>
	 * Message: ������L���Ă��錌���́A�����I�Ɏ�����ɓo�^����܂��B
	 */
	public static final SystemMessageId CLAN_THAT_OWNS_CASTLE_IS_AUTOMATICALLY_REGISTERED_DEFENDING;
	
	/**
	 * ID: 689<br>
	 * Message: ������L���Ă��錌���́A���̍U���ɂ͎Q��ł��܂���B
	 */
	public static final SystemMessageId CLAN_THAT_OWNS_CASTLE_CANNOT_PARTICIPATE_OTHER_SIEGE;
	
	/**
	 * ID: 690<br>
	 * Message: ������L���Ă��錌���Ɠ��������ɑ����Ă��邽�߁A�U�����ɓo�^�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_ATTACK_ALLIANCE_CASTLE;
	
	/**
	 * ID: 691<br>
	 * Message: $s1�����́A���ł�$s2�����ɉ������Ă��܂��B
	 */
	public static final SystemMessageId S1_CLAN_ALREADY_MEMBER_OF_S2_ALLIANCE;
	
	/**
	 * ID: 692<br>
	 * Message: ���肪�t���[�Y��Ԃł��B���΂炭���҂����������B
	 */
	public static final SystemMessageId OTHER_PARTY_IS_FROZEN;
	
	/**
	 * ID: 693<br>
	 * Message: ��������������̑q�ɂɂ���܂��B
	 */
	public static final SystemMessageId PACKAGE_IN_ANOTHER_WAREHOUSE;
	
	/**
	 * ID: 694<br>
	 * Message: ������������͂���܂���B
	 */
	public static final SystemMessageId NO_PACKAGES_ARRIVED;
	
	/**
	 * ID: 695<br>
	 * Message: �y�b�g�̖��O��ݒ�ł��܂���B
	 */
	public static final SystemMessageId NAMING_YOU_CANNOT_SET_NAME_OF_THE_PET;
	
	/**
	 * ID: 697<br>
	 * Message: �A�C�e���G���`�����g�l���ُ�ł��B
	 */
	public static final SystemMessageId ITEM_ENCHANT_VALUE_STRANGE;
	
	/**
	 * ID: 698<br>
	 * Message: �̔����X�g�ɂ��铯���A�C�e���Ɖ��i���قȂ�܂��B
	 */
	public static final SystemMessageId PRICE_DIFFERENT_FROM_SALES_LIST;
	
	/**
	 * ID: 699<br>
	 * Message: ���ݍw�����܂���B
	 */
	public static final SystemMessageId CURRENTLY_NOT_PURCHASING;
	
	/**
	 * ID: 700<br>
	 * Message: �w�����������܂����B
	 */
	public static final SystemMessageId THE_PURCHASE_IS_COMPLETE;
	
	/**
	 * ID: 701<br>
	 * Message: �K�v�A�C�e��������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_REQUIRED_ITEMS;
	
	/**
	 * ID: 702<br>
	 * Message: ���ݑΉ�����FS�ESPT�E�T�[�r�X�`�[�������܂���B
	 */
	public static final SystemMessageId NO_GM_PROVIDING_SERVICE_NOW;
	
	/**
	 * ID: 703<br>
	 * Message: ======<FS���X�g>======
	 */
	public static final SystemMessageId GM_LIST;
	
	/**
	 * ID: 704<br>
	 * Message: support�F$c1
	 */
	public static final SystemMessageId GM_C1;
	
	/**
	 * ID: 705<br>
	 * Message: �������g���Ւf���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_EXCLUDE_SELF;
	
	/**
	 * ID: 706<br>
	 * Message: �Ւf���X�g�ɂ͍ő�128�l�܂œo�^�ł��܂��B
	 */
	public static final SystemMessageId ONLY_64_NAMES_ON_EXCLUDE_LIST;
	
	/**
	 * ID: 707<br>
	 * Message: �U��풆�̑��ւ̓e���|�[�g�ł��܂���B
	 */
	public static final SystemMessageId NO_PORT_THAT_IS_IN_SIGE;
	
	/**
	 * ID: 708<br>
	 * Message: ��̑q�ɂ𗘗p���錠��������܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CASTLE_WAREHOUSE;
	
	/**
	 * ID: 709<br>
	 * Message: �����q�ɂ𗘗p���錠��������܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CLAN_WAREHOUSE;
	
	/**
	 * ID: 710<br>
	 * Message: �����q�ɂ𗘗p�ł���̂́A�������x����1�ȏ�̌����݂̂ł��B
	 */
	public static final SystemMessageId ONLY_LEVEL_1_CLAN_OR_HIGHER_CAN_USE_WAREHOUSE;
	
	/**
	 * ID: 711<br>
	 * Message: $s1�̍U��킪�n�܂�܂����B
	 */
	public static final SystemMessageId SIEGE_OF_S1_HAS_STARTED;
	
	/**
	 * ID: 712<br>
	 * Message: $s1�̍U��킪�I�����܂����B
	 */
	public static final SystemMessageId SIEGE_OF_S1_HAS_ENDED;
	
	/**
	 * ID: 713<br>
	 * Message: $s1/$s2/$s3 $s4:$s5
	 */
	public static final SystemMessageId S1_S2_S3_D;
	
	/**
	 * ID: 714<br>
	 * Message: �g���b�v���u���쓮���܂����B
	 */
	public static final SystemMessageId A_TRAP_DEVICE_HAS_BEEN_TRIPPED;
	
	/**
	 * ID: 715<br>
	 * Message: �g���b�v���u�̍쓮�����f����܂����B
	 */
	public static final SystemMessageId A_TRAP_DEVICE_HAS_BEEN_STOPPED;
	
	/**
	 * ID: 716<br>
	 * Message: �w�n���Ȃ��ƕ����ł��܂���B
	 */
	public static final SystemMessageId NO_RESURRECTION_WITHOUT_BASE_CAMP;
	
	/**
	 * ID: 717<br>
	 * Message: �K�[�f�B�A���^���[���j�󂳂�A�������s�\�ł��B
	 */
	public static final SystemMessageId TOWER_DESTROYED_NO_RESURRECTION;
	
	/**
	 * ID: 718<br>
	 * Message: �U��풆�͏����J�ł��܂���B
	 */
	public static final SystemMessageId GATES_NOT_OPENED_CLOSED_DURING_SIEGE;
	
	/**
	 * ID: 719<br>
	 * Message: �A�C�e���̒����Ɏ��s���܂����B
	 */
	public static final SystemMessageId ITEM_MIXING_FAILED;
	
	/**
	 * ID: 720<br>
	 * Message: �w���z�����������傫�����߁A�l���X���J�����Ƃ��ł��܂���B
	 */
	public static final SystemMessageId THE_PURCHASE_PRICE_IS_HIGHER_THAN_MONEY;
	
	/**
	 * ID: 721<br>
	 * Message: �U���ɎQ�킵�Ă����Ԃł́A��������邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId NO_ALLY_CREATION_WHILE_SIEGE;
	
	/**
	 * ID: 722<br>
	 * Message: �����̌������U���ɎQ�킵�Ă����Ԃ̂��߁A���������U�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_ALLY_WHILE_IN_SIEGE;
	
	/**
	 * ID: 723<br>
	 * Message: ����̌����́A�U���ɎQ�풆�ł��B
	 */
	public static final SystemMessageId OPPOSING_CLAN_IS_PARTICIPATING_IN_SIEGE;
	
	/**
	 * ID: 724<br>
	 * Message: �U���ɎQ�킵�Ă����Ԃł́A�E�ނł��܂���B
	 */
	public static final SystemMessageId CANNOT_LEAVE_WHILE_SIEGE;
	
	/**
	 * ID: 725<br>
	 * Message: �U���ɎQ�풆�̌����𓯖�����Ǖ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISMISS_WHILE_SIEGE;
	
	/**
	 * ID: 726<br>
	 * Message: �t���[�Y��Ԃ��n�܂�܂����B���΂炭���҂����������B
	 */
	public static final SystemMessageId FROZEN_CONDITION_STARTED;
	
	/**
	 * ID: 727<br>
	 * Message: �t���[�Y��Ԃ���������܂����B
	 */
	public static final SystemMessageId FROZEN_CONDITION_REMOVED;
	
	/**
	 * ID: 728<br>
	 * Message: ���U�̐\����7���ȓ��́A�Ăщ��U��\�����ނ��Ƃ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_APPLY_DISSOLUTION_AGAIN;
	
	/**
	 * ID: 729<br>
	 * Message: �Y���A�C�e�����̂Ă邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId ITEM_NOT_DISCARDED;
	
	/**
	 * ID: 730<br>
	 * Message: - $c1�Ԗڂ̃T�|�[�g���󂯕t������܂����B\n - �����̃T�|�[�g�񐔂͂���$s2��ł��B\n - ���[���h�̏󋵂ɂ���Ă͕ԐM�ɂ����Ԃ��������Ă��܂��ꍇ���������܂��B�\�߂������������܂��B
	 */
	public static final SystemMessageId SUBMITTED_YOU_S1_TH_PETITION_S2_LEFT;
	
	/**
	 * ID: 731<br>
	 * Message: $c1�ɃT�|�[�g����̑Ή��v�������܂����B��t�ԍ���$s2�ł��B
	 */
	public static final SystemMessageId PETITION_S1_RECEIVED_CODE_IS_S2;
	
	/**
	 * ID: 732<br>
	 * Message: $c1�ɃT�|�[�g����̂��A��������܂��B
	 */
	public static final SystemMessageId C1_RECEIVED_CONSULTATION_REQUEST;
	
	/**
	 * ID: 733<br>
	 * Message: ����ɐ\���\��$s1��̃T�|�[�g�����ׂĎ󂯕t���܂����B����ȏ�T�|�[�g��\�����ނ��Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId WE_HAVE_RECEIVED_S1_PETITIONS_TODAY;
	
	/**
	 * ID: 734<br>
	 * Message: �T�|�[�g�̑㗝��t�Ɏ��s���܂����B$c1�����łɃT�|�[�g���󂯕t������Ԃł��B
	 */
	public static final SystemMessageId PETITION_FAILED_C1_ALREADY_SUBMITTED;
	
	/**
	 * ID: 735<br>
	 * Message: $c1�ɑ΂���T�|�[�g�̑㗝��t�Ɏ��s���܂����B�G���[�ԍ���$s2�ł��B
	 */
	public static final SystemMessageId PETITION_FAILED_FOR_C1_ERROR_NUMBER_S2;
	
	/**
	 * ID: 736<br>
	 * Message: �T�|�[�g�v�����L�����Z������܂����B�����󂯕t���\�ȃT�|�[�g�͂���$s1��ł��B
	 */
	public static final SystemMessageId PETITION_CANCELED_SUBMIT_S1_MORE_TODAY;
	
	/**
	 * ID: 737<br>
	 * Message: $c1�ɑ΂���T�|�[�g�̑㗝��t���L�����Z�����܂����B
	 */
	public static final SystemMessageId CANCELED_PETITION_ON_S1;
	
	/**
	 * ID: 738<br>
	 * Message: ���݂̓T�|�[�g�v�����󂯕t���Ă���܂���B
	 */
	public static final SystemMessageId PETITION_NOT_SUBMITTED;
	
	/**
	 * ID: 739<br>
	 * Message: $c1�ɑ΂���T�|�[�g�̑㗝��t�̃L�����Z���Ɏ��s���܂����B�G���[�R�[�h��$s2�ł��B
	 */
	public static final SystemMessageId PETITION_CANCEL_FAILED_FOR_C1_ERROR_NUMBER_S2;
	
	/**
	 * ID: 740<br>
	 * Message: $c1���T�|�[�g�̗v���őΘb�ɎQ�����܂����B
	 */
	public static final SystemMessageId C1_PARTICIPATE_PETITION;
	
	/**
	 * ID: 741<br>
	 * Message: $c1��Θb�ɒǉ�����̂Ɏ��s���܂����B���łɃT�|�[�g���󂯕t������Ԃł��B
	 */
	public static final SystemMessageId FAILED_ADDING_C1_TO_PETITION;
	
	/**
	 * ID: 742<br>
	 * Message: $c1��Θb�ɒǉ�����̂Ɏ��s���܂����B�G���[�R�[�h��$s2�ł��B
	 */
	public static final SystemMessageId PETITION_ADDING_C1_FAILED_ERROR_NUMBER_S2;
	
	/**
	 * ID: 743<br>
	 * Message: $c1���Θb����ޏꂵ�܂����B
	 */
	public static final SystemMessageId C1_LEFT_PETITION_CHAT;
	
	/**
	 * ID: 744<br>
	 * Message: $s1��Θb����폜����̂Ɏ��s���܂����B�G���[�R�[�h��$s2�ł��B
	 */
	public static final SystemMessageId PETITION_REMOVING_S1_FAILED_ERROR_NUMBER_S2;
	
	/**
	 * ID: 745<br>
	 * Message: ���݃T�|�[�g�̑Θb��Ԃł͂���܂���B
	 */
	public static final SystemMessageId YOU_ARE_NOT_IN_PETITION_CHAT;
	
	/**
	 * ID: 746<br>
	 * Message: ���݂̓T�|�[�g�v���̏�Ԃł͂���܂���B
	 */
	public static final SystemMessageId CURRENTLY_NO_PETITION;
	
	/**
	 * ID: 748<br>
	 * Message: ����Ă��邽�߁A�r�������f����܂����B
	 */
	public static final SystemMessageId DIST_TOO_FAR_CASTING_STOPPED;
	
	/**
	 * ID: 749<br>
	 * Message: $s1�̌��ʂ���������܂����B
	 */
	public static final SystemMessageId EFFECT_S1_DISAPPEARED;
	
	/**
	 * ID: 750<br>
	 * Message: ����ȏ�K���X�L��������܂���B
	 */
	public static final SystemMessageId NO_MORE_SKILLS_TO_LEARN;
	
	/**
	 * ID: 751<br>
	 * Message: �����ɑ����錌���ƍU���̊֌W���Փ˂��邽�߁A�����ɏ��҂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_INVITE_CONFLICT_CLAN;
	
	/**
	 * ID: 752<br>
	 * Message: �g�p�ł��Ȃ����O�ł��B
	 */
	public static final SystemMessageId CANNOT_USE_NAME;
	
	/**
	 * ID: 753<br>
	 * Message: �����ɂ͗b����z�u�ł��܂���B
	 */
	public static final SystemMessageId NO_MERCS_HERE;
	
	/**
	 * ID: 754<br>
	 * Message: ���T�̎g�p���Ԃ́A����$s1����$s2���ł��B
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_THIS_WEEK;
	
	/**
	 * ID: 755<br>
	 * Message: ���T�̎g�p���Ԃ́A����$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_THIS_WEEK;
	
	/**
	 * ID: 756<br>
	 * Message: ���T�̎g�p���Ԃ��I�����܂����B
	 */
	public static final SystemMessageId WEEKS_USAGE_TIME_FINISHED;
	
	/**
	 * ID: 757<br>
	 * Message: ��ʎg�p���Ԃ́A����$s1����$s2���ł��B
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_IN_TIME;
	
	/**
	 * ID: 758<br>
	 * Message: ���T�̃v���C���Ԃ́A����$s1����$s2���ł��B
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME;
	
	/**
	 * ID: 759<br>
	 * Message: ���T�̃v���C���Ԃ́A����$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME;
	
	/**
	 * ID: 760<br>
	 * Message: $c1�͌����E�ތ�1���Ԍo�߂��Ă��炸�A�����ɉ����ł��Ȃ���Ԃł��B
	 */
	public static final SystemMessageId C1_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN;
	
	/**
	 * ID: 761<br>
	 * Message: $s1�͓����E�ތ�1�����o�߂��Ă��炸�A�����ɉ����ł��Ȃ���Ԃł��B
	 */
	public static final SystemMessageId S1_CANT_ENTER_ALLIANCE_WITHIN_1_DAY;
	
	/**
	 * ID: 762<br>
	 * Message: $c1��$s2��]������$s3���o�܂����B
	 */
	public static final SystemMessageId C1_ROLLED_S2_S3_EYE_CAME_OUT;
	
	/**
	 * ID: 763<br>
	 * Message: �q�ɂƂ̋������������ߏ����͂����܂���ł����B
	 */
	public static final SystemMessageId FAILED_SENDING_PACKAGE_TOO_FAR;
	
	/**
	 * ID: 764<br>
	 * Message: $s1���ԃv���C���܂����B���N�̂��߂ɂ��΂炭�x�e���Ă��������B
	 */
	public static final SystemMessageId PLAYING_FOR_LONG_TIME;
	
	/**
	 * ID: 769<br>
	 * Message: �s�����[�e�B���e�B�̗��p����������܂����B�s�����[�e�B���e�B�̗��p���I�����āA�ēx���s���Ă��������B
	 */
	public static final SystemMessageId HACKING_TOOL;
	
	/**
	 * ID: 774<br>
	 * Message: �v���C���Ԃ͂���ȏ㌸��܂���B
	 */
	public static final SystemMessageId PLAY_TIME_NO_LONGER_ACCUMULATING;
	
	/**
	 * ID: 775<br>
	 * Message: ���ꂩ��v���C���Ԃ�����܂��B
	 */
	public static final SystemMessageId PLAY_TIME_EXPENDED;
	
	/**
	 * ID: 776<br>
	 * Message: �����ɓ��D�����A�W�g��$s1�����ɗ��D����܂����B
	 */
	public static final SystemMessageId CLANHALL_AWARDED_TO_CLAN;
	
	/**
	 * ID: 777<br>
	 * Message: �����ɓ��D�����A�W�g�����D�ƂȂ�܂����B
	 */
	public static final SystemMessageId CLANHALL_NOT_SOLD;
	
	/**
	 * ID: 778<br>
	 * Message: �����ł̓��O�A�E�g�ł��܂���B
	 */
	public static final SystemMessageId NO_LOGOUT_HERE;
	
	/**
	 * ID: 779<br>
	 * Message: �����ł̓��X�^�[�g�ł��܂���B
	 */
	public static final SystemMessageId NO_RESTART_HERE;
	
	/**
	 * ID: 780<br>
	 * Message: �U���̊Ԃ����ϐ�ł��܂��B
	 */
	public static final SystemMessageId ONLY_VIEW_SIEGE;
	
	/**
	 * ID: 781<br>
	 * Message: �ϐ풆�ɂ͂ł��Ȃ��s���ł��B
	 */
	public static final SystemMessageId OBSERVERS_CANNOT_PARTICIPATE;
	
	/**
	 * ID: 782<br>
	 * Message: �y�b�g�A�܂��͏����b����������Ă����Ԃł͊ϐ�ł��܂���B
	 */
	public static final SystemMessageId NO_OBSERVE_WITH_PET;
	
	/**
	 * ID: 783<br>
	 * Message: ���݁A�󂭂��̔̔����ꎞ���f����Ă���܂��B
	 */
	public static final SystemMessageId LOTTERY_TICKET_SALES_TEMP_SUSPENDED;
	
	/**
	 * ID: 784<br>
	 * Message: �󂭂��̔̔������ߐ؂��܂����B
	 */
	public static final SystemMessageId NO_LOTTERY_TICKETS_AVAILABLE;
	
	/**
	 * ID: 785<br>
	 * Message: �܂�$s1��ڂ̓��I���ʂ��o�Ă��܂���B
	 */
	public static final SystemMessageId LOTTERY_S1_RESULT_NOT_PUBLISHED;
	
	/**
	 * ID: 786<br>
	 * Message: �g���Ȃ��P�ꂪ�܂܂�Ă��܂��B
	 */
	public static final SystemMessageId INCORRECT_SYNTAX;
	
	/**
	 * ID: 787<br>
	 * Message: �\�I���I�����܂����B
	 */
	public static final SystemMessageId CLANHALL_SIEGE_TRYOUTS_FINISHED;
	
	/**
	 * ID: 788<br>
	 * Message: �����킪�I�����܂����B
	 */
	public static final SystemMessageId CLANHALL_SIEGE_FINALS_FINISHED;
	
	/**
	 * ID: 789<br>
	 * Message: �\�I���n�܂�܂����B
	 */
	public static final SystemMessageId CLANHALL_SIEGE_TRYOUTS_BEGUN;
	
	/**
	 * ID: 790<br>
	 * Message: �����킪�n�܂�܂����B
	 */
	public static final SystemMessageId CLANHALL_SIEGE_FINALS_BEGUN;
	
	/**
	 * ID: 791<br>
	 * Message: ������̑ҋ@��Ԃł��B�������Ă��������B
	 */
	public static final SystemMessageId FINAL_MATCH_BEGIN;
	
	/**
	 * ID: 792<br>
	 * Message: �A�W�g�킪�I�����܂����B
	 */
	public static final SystemMessageId CLANHALL_SIEGE_ENDED;
	
	/**
	 * ID: 793<br>
	 * Message: �A�W�g�킪�n�܂�܂����B
	 */
	public static final SystemMessageId CLANHALL_SIEGE_BEGUN;
	
	/**
	 * ID: 794<br>
	 * Message: �Y�����錠��������܂���B
	 */
	public static final SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT;
	
	/**
	 * ID: 795<br>
	 * Message: ������݂̂�������ݒ�ł��܂��B
	 */
	public static final SystemMessageId ONLY_LEADERS_CAN_SET_RIGHTS;
	
	/**
	 * ID: 796<br>
	 * Message: �ϐ�\���Ԃ͎c��$s1���ł��B
	 */
	public static final SystemMessageId REMAINING_OBSERVATION_TIME;
	
	/**
	 * ID: 797<br>
	 * Message: �}�N����48�܂œo�^�\�ł��B
	 */
	public static final SystemMessageId YOU_MAY_CREATE_UP_TO_48_MACROS;
	
	/**
	 * ID: 798<br>
	 * Message: ��x�o�^����ƁA�Ăѐ���}�A�C�e���ɖ߂����Ƃ͂ł��܂���B�o�^���Ă���낵���ł����B
	 */
	public static final SystemMessageId ITEM_REGISTRATION_IRREVERSIBLE;
	
	/**
	 * ID: 799<br>
	 * Message: �ϐ�\���Ԃ��I�����܂����B
	 */
	public static final SystemMessageId OBSERVATION_TIME_EXPIRED;
	
	/**
	 * ID: 800<br>
	 * Message: �A�W�g��̓o�^���Ԃ��߂����̂ŁA�o�^�ł��܂���B
	 */
	public static final SystemMessageId REGISTRATION_PERIOD_OVER;
	
	/**
	 * ID: 801<br>
	 * Message: ����ȏ�̃A�W�g��̓o�^�͂ł��܂���B
	 */
	public static final SystemMessageId REGISTRATION_CLOSED;
	
	/**
	 * ID: 802<br>
	 * Message: ����ȏ�Ď��E�B���h�E���J�����Ƃ��ł��܂���B�E�B���h�E����āA�ēx���s���Ă��������B
	 */
	public static final SystemMessageId PETITION_NOT_ACCEPTED_NOW;
	
	/**
	 * ID: 803<br>
	 * Message: �T�|�[�g�v���̓��e�������Ă��������B
	 */
	public static final SystemMessageId PETITION_NOT_SPECIFIED;
	
	/**
	 * ID: 804<br>
	 * Message: ���ނ�I�����āAFAQ�����m�F���������B
	 */
	public static final SystemMessageId SELECT_TYPE;
	
	/**
	 * ID: 805<br>
	 * Message: ���݂̓T�|�[�g�̎󂯕t�����s���Ă��܂���B$s1���Ȍ�ɍēx�v�����Ă��������B
	 */
	public static final SystemMessageId PETITION_NOT_ACCEPTED_SUBMIT_AT_S1;
	
	/**
	 * ID: 806<br>
	 * Message: �n�`�ɂ͂܂��ďo���Ȃ����́u/escape�v�R�}���h�������Ă݂Ă��������B
	 */
	public static final SystemMessageId TRY_UNSTUCK_WHEN_TRAPPED;
	
	/**
	 * ID: 807<br>
	 * Message: �ړ����s�\�Ȓn�`�ł����̂ŁA���Ƀe���|�[�g���܂��B
	 */
	public static final SystemMessageId STUCK_PREPARE_FOR_TRANSPORT;
	
	/**
	 * ID: 808<br>
	 * Message: �ړ����s�\�Ȓn�`�ł��邱�Ƃ��m�F�ł��܂���ł����B�u/support�v�R�}���h�ŁA�T�|�[�g�v�����s�Ȃ��Ă��������B
	 */
	public static final SystemMessageId STUCK_SUBMIT_PETITION;
	
	/**
	 * ID: 809<br>
	 * Message: �ړ����s�\�Ȓn�`�ł��邱�Ƃ��m�F�ł��܂���ł����B5����ɑ��Ƀe���|�[�g���܂��B
	 */
	public static final SystemMessageId STUCK_TRANSPORT_IN_FIVE_MINUTES;
	
	/**
	 * ID: 810<br>
	 * Message: �L���ȃ}�N���ł͂���܂���B�}�N���Ɋ֘A����w���v�����m�F���������B
	 */
	public static final SystemMessageId INVALID_MACRO;
	
	/**
	 * ID: 811<br>
	 * Message: �ړI�n�i$s1�j�Ɉړ����܂����B
	 */
	public static final SystemMessageId WILL_BE_MOVED;
	
	/**
	 * ID: 812<br>
	 * Message: 㩂ɂ����$s1�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId TRAP_DID_S1_DAMAGE;
	
	/**
	 * ID: 813<br>
	 * Message: 㩂ɂ���ēłɊ|����܂����B
	 */
	public static final SystemMessageId POISONED_BY_TRAP;
	
	/**
	 * ID: 814<br>
	 * Message: 㩂ɂ���Ĉړ����x���ቺ���܂����B
	 */
	public static final SystemMessageId SLOWED_BY_TRAP;
	
	/**
	 * ID: 815<br>
	 * Message: �\�I�ҋ@��Ԃł��B�������Ă��������B
	 */
	public static final SystemMessageId TRYOUTS_ABOUT_TO_BEGIN;
	
	/**
	 * ID: 816<br>
	 * Message: ��$s1�񃂃��X�^�[ ���[�X �`�P�b�g�̔̔����J�n����܂����I
	 */
	public static final SystemMessageId MONSRACE_TICKETS_AVAILABLE_FOR_S1_RACE;
	
	/**
	 * ID: 817<br>
	 * Message: ��$s1�񃂃��X�^�[ ���[�X �`�P�b�g�����ݔ̔����Ă���܂��B
	 */
	public static final SystemMessageId MONSRACE_TICKETS_NOW_AVAILABLE_FOR_S1_RACE;
	
	/**
	 * ID: 818<br>
	 * Message: $s1����Ƀ����X�^�[ ���[�X �`�P�b�g�̔̔����I���������܂��B�����߂ɂ��w�����������B
	 */
	public static final SystemMessageId MONSRACE_TICKETS_STOP_IN_S1_MINUTES;
	
	/**
	 * ID: 819<br>
	 * Message: ��$s1�񃂃��X�^�[ ���[�X �`�P�b�g�̔̔����I���������܂����B�z���������m�F���������B
	 */
	public static final SystemMessageId MONSRACE_S1_TICKET_SALES_CLOSED;
	
	/**
	 * ID: 820<br>
	 * Message: $s1����ɑ�$s2�񃂃��X�^�[ ���[�X���J�n����܂��B
	 */
	public static final SystemMessageId MONSRACE_S2_BEGINS_IN_S1_MINUTES;
	
	/**
	 * ID: 821<br>
	 * Message: 30�b��ɑ�$s1�񃂃��X�^�[ ���[�X���J�n����܂��B
	 */
	public static final SystemMessageId MONSRACE_S1_BEGINS_IN_30_SECONDS;
	
	/**
	 * ID: 822<br>
	 * Message: ��$s1�񃂃��X�^�[ ���[�X���J�n����܂��B
	 */
	public static final SystemMessageId MONSRACE_S1_COUNTDOWN_IN_FIVE_SECONDS;
	
	/**
	 * ID: 823<br>
	 * Message: $s1�b�O�I
	 */
	public static final SystemMessageId MONSRACE_BEGINS_IN_S1_SECONDS;
	
	/**
	 * ID: 824<br>
	 * Message: ���[�X �X�^�[�g�I
	 */
	public static final SystemMessageId MONSRACE_RACE_START;
	
	/**
	 * ID: 825<br>
	 * Message: ��$s1�񃂃��X�^�[ ���[�X���I���������܂����B
	 */
	public static final SystemMessageId MONSRACE_S1_RACE_END;
	
	/**
	 * ID: 826<br>
	 * Message: 1��$s1�ԁA2��$s2�Ԃł��I
	 */
	public static final SystemMessageId MONSRACE_FIRST_PLACE_S1_SECOND_S2;
	
	/**
	 * ID: 827<br>
	 * Message: FS�ESPT�E�T�[�r�X�`�[�����Ւf���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId YOU_MAY_NOT_IMPOSE_A_BLOCK_ON_GM;
	
	/**
	 * ID: 828<br>
	 * Message: $s1�}�N�����폜���܂����B
	 */
	public static final SystemMessageId WISH_TO_DELETE_S1_MACRO;
	
	/**
	 * ID: 829<br>
	 * Message: �������g�𐄑E���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_RECOMMEND_YOURSELF;
	
	/**
	 * ID: 830<br>
	 * Message: $c1�𐄑E���܂����B���E���̎c���$s2�ł��B
	 */
	public static final SystemMessageId YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT;
	
	/**
	 * ID: 831<br>
	 * Message: $c1���琄�E����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_RECOMMENDED_BY_C1;
	
	/**
	 * ID: 832<br>
	 * Message: ���łɐ��E�����Ă��܂��B
	 */
	public static final SystemMessageId THAT_CHARACTER_IS_RECOMMENDED;
	
	/**
	 * ID: 833<br>
	 * Message: ���E�������ׂĎg�p���܂����B���E���͖����ߌ�1���ɏ���������܂��B
	 */
	public static final SystemMessageId NO_MORE_RECOMMENDATIONS_TO_HAVE;
	
	/**
	 * ID: 834<br>
	 * Message: $c1���]�������T�C�R���̖ڂ�$s2�ł��B
	 */
	public static final SystemMessageId C1_ROLLED_S2;
	
	/**
	 * ID: 835<br>
	 * Message: �A�����ăT�C�R����U�邱�Ƃ͂ł��܂���B���Ԃ�u���Ă���g�p���Ă��������B
	 */
	public static final SystemMessageId YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER;
	
	/**
	 * ID: 836<br>
	 * Message: �C���x���g���̃X���b�g�������̒��߂ɂ��A�A�C�e�����󂯎��܂���B
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_ITEM;
	
	/**
	 * ID: 837<br>
	 * Message: �}�N���̐�����32�����܂łł��B
	 */
	public static final SystemMessageId MACRO_DESCRIPTION_MAX_32_CHARS;
	
	/**
	 * ID: 838<br>
	 * Message: �}�N��������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_THE_MACRO_NAME;
	
	/**
	 * ID: 839<br>
	 * Message: �������O�̃}�N��������̂œo�^�ł��܂���B
	 */
	public static final SystemMessageId MACRO_NAME_ALREADY_USED;
	
	/**
	 * ID: 840<br>
	 * Message: ���łɓo�^����Ă��鐻��}�ł��B
	 */
	public static final SystemMessageId RECIPE_ALREADY_REGISTERED;
	
	/**
	 * ID: 841<br>
	 * Message: ����ȏ㐻��}�̓o�^�͂ł��܂���B
	 */
	public static final SystemMessageId NO_FUTHER_RECIPES_CAN_BE_ADDED;
	
	/**
	 * ID: 842<br>
	 * Message: �N���G�C�g �A�C�e���̃X�L�� ���x�����Ⴂ���߁A����}��o�^�ł��܂���B
	 */
	public static final SystemMessageId NOT_AUTHORIZED_REGISTER_RECIPE;
	
	/**
	 * ID: 843<br>
	 * Message: $s1�̐�̐킪�I�����܂����B
	 */
	public static final SystemMessageId SIEGE_OF_S1_FINISHED;
	
	/**
	 * ID: 844<br>
	 * Message: $s1�̐�̐킪�J�n����܂����B
	 */
	public static final SystemMessageId SIEGE_OF_S1_BEGUN;
	
	/**
	 * ID: 845<br>
	 * Message: $s1�̐�̐�̓o�^���Ԃ��I�����܂����B
	 */
	public static final SystemMessageId DEADLINE_FOR_SIEGE_S1_PASSED;
	
	/**
	 * ID: 846<br>
	 * Message: �Q�����錌�����Ȃ����߁A$s1�̐�̐킪�L�����Z������܂����B
	 */
	public static final SystemMessageId SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST;
	
	/**
	 * ID: 847<br>
	 * Message: ���łɃA�W�g�����L���Ă��錌���͐�̐�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId CLAN_OWNING_CLANHALL_MAY_NOT_SIEGE_CLANHALL;
	
	/**
	 * ID: 848<br>
	 * Message: $s1���폜����܂����B
	 */
	public static final SystemMessageId S1_HAS_BEEN_DELETED;
	
	/**
	 * ID: 849<br>
	 * Message: $s1��T�����Ƃ��ł��܂���B
	 */
	public static final SystemMessageId S1_NOT_FOUND;
	
	/**
	 * ID: 850<br>
	 * Message: $s1�����łɑ��݂��܂��B
	 */
	public static final SystemMessageId S1_ALREADY_EXISTS2;
	
	/**
	 * ID: 851<br>
	 * Message: $s1���ǉ�����܂����B
	 */
	public static final SystemMessageId S1_ADDED;
	
	/**
	 * ID: 852<br>
	 * Message: ����}�̏�񂪐���������܂���B
	 */
	public static final SystemMessageId RECIPE_INCORRECT;
	
	/**
	 * ID: 853<br>
	 * Message: �l�����Ԃł́A����}�W�Ǘ��͂ł��܂���B
	 */
	public static final SystemMessageId CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING;
	
	/**
	 * ID: 854<br>
	 * Message: $s1��$s2����܂���B
	 */
	public static final SystemMessageId MISSING_S2_S1_TO_CREATE;
	
	/**
	 * ID: 855<br>
	 * Message: $s1������$s2�Ƃ̐�̐�ŏ������܂����B
	 */
	public static final SystemMessageId S1_CLAN_DEFEATED_S2;
	
	/**
	 * ID: 856<br>
	 * Message: $s1�̐�̐킪���������ŏI�����܂����B
	 */
	public static final SystemMessageId SIEGE_S1_DRAW;
	
	/**
	 * ID: 857<br>
	 * Message: $s1������$s2�Ƃ̗\�I�ŏ������܂����B
	 */
	public static final SystemMessageId S1_CLAN_WON_MATCH_S2;
	
	/**
	 * ID: 858<br>
	 * Message: $s1�̗\�I�͈��������ŏI�����܂����B
	 */
	public static final SystemMessageId MATCH_OF_S1_DRAW;
	
	/**
	 * ID: 859<br>
	 * Message: ����}��o�^���Ă��������B
	 */
	public static final SystemMessageId PLEASE_REGISTER_RECIPE;
	
	/**
	 * ID: 860<br>
	 * Message: ���̐w�n�Ƃ̊Ԋu���������߁A�����ɐw�n�𗧂Ă邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId HEADQUARTERS_TOO_CLOSE;
	
	/**
	 * ID: 861<br>
	 * Message: �����̐������߂��܂����B
	 */
	public static final SystemMessageId TOO_MANY_MEMOS;
	
	/**
	 * ID: 862<br>
	 * Message: �`�P�b�g�̔̔����������Ă��Ȃ��̂ŁA�z�������邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId ODDS_NOT_POSTED;
	
	/**
	 * ID: 863<br>
	 * Message: �΂̋C���������܂��B
	 */
	public static final SystemMessageId FEEL_ENERGY_FIRE;
	
	/**
	 * ID: 864<br>
	 * Message: ���̋C���������܂��B
	 */
	public static final SystemMessageId FEEL_ENERGY_WATER;
	
	/**
	 * ID: 865<br>
	 * Message: ���̋C���������܂��B
	 */
	public static final SystemMessageId FEEL_ENERGY_WIND;
	
	/**
	 * ID: 866<br>
	 * Message: ����ȏ�A�C���W�߂邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId NO_LONGER_ENERGY;
	
	/**
	 * ID: 867<br>
	 * Message: �C���Ȃ��Ȃ�܂����B
	 */
	public static final SystemMessageId ENERGY_DEPLETED;
	
	/**
	 * ID: 868<br>
	 * Message: �΂̋C��`���܂����B
	 */
	public static final SystemMessageId ENERGY_FIRE_DELIVERED;
	
	/**
	 * ID: 869<br>
	 * Message: ���̋C��`���܂����B
	 */
	public static final SystemMessageId ENERGY_WATER_DELIVERED;
	
	/**
	 * ID: 870<br>
	 * Message: ���̋C��`���܂����B
	 */
	public static final SystemMessageId ENERGY_WIND_DELIVERED;
	
	/**
	 * ID: 871<br>
	 * Message: ���łɎ��A�t����ꂽ��Ԃł��B
	 */
	public static final SystemMessageId THE_SEED_HAS_BEEN_SOWN;
	
	/**
	 * ID: 872<br>
	 * Message: ���̑����ł͎g���Ȃ���ł��B
	 */
	public static final SystemMessageId THIS_SEED_MAY_NOT_BE_SOWN_HERE;
	
	/**
	 * ID: 873<br>
	 * Message: �ݕ����󂯎��L�����N�^�[�����܂���B
	 */
	public static final SystemMessageId CHARACTER_DOES_NOT_EXIST;
	
	/**
	 * ID: 874<br>
	 * Message: �ΏۂƂȂ�q�ɂ������ς��ł��B
	 */
	public static final SystemMessageId WAREHOUSE_CAPACITY_EXCEEDED;
	
	/**
	 * ID: 875<br>
	 * Message: �z�����L�����Z������܂����B
	 */
	public static final SystemMessageId CARGO_CANCELED;
	
	/**
	 * ID: 876<br>
	 * Message: �z���Ɏ��s���܂����B
	 */
	public static final SystemMessageId CARGO_NOT_DELIVERED;
	
	/**
	 * ID: 877<br>
	 * Message: ��l���ǉ�����܂����B
	 */
	public static final SystemMessageId SYMBOL_ADDED;
	
	/**
	 * ID: 878<br>
	 * Message: ��l���폜����܂����B
	 */
	public static final SystemMessageId SYMBOL_DELETED;
	
	/**
	 * ID: 879<br>
	 * Message: �����V�X�e���͌��ݓ_�����ł��B
	 */
	public static final SystemMessageId THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE;
	
	/**
	 * ID: 880<br>
	 * Message: �w�����I���܂����B
	 */
	public static final SystemMessageId THE_TRANSACTION_IS_COMPLETE;
	
	/**
	 * ID: 881<br>
	 * Message: �w���A�C�e���̏�񂪐���������܂���B
	 */
	public static final SystemMessageId THERE_IS_A_DISCREPANCY_ON_THE_INVOICE;
	
	/**
	 * ID: 882<br>
	 * Message: ��̐�������������܂���B
	 */
	public static final SystemMessageId THE_SEED_QUANTITY_IS_INCORRECT;
	
	/**
	 * ID: 883<br>
	 * Message: ��̏�񂪐���������܂���B
	 */
	public static final SystemMessageId THE_SEED_INFORMATION_IS_INCORRECT;
	
	/**
	 * ID: 884<br>
	 * Message: �����̏�񂪐V���ɐݒ肳��܂����B
	 */
	public static final SystemMessageId THE_MANOR_INFORMATION_HAS_BEEN_UPDATED;
	
	/**
	 * ID: 885<br>
	 * Message: �앨�̐�������������܂���B
	 */
	public static final SystemMessageId THE_NUMBER_OF_CROPS_IS_INCORRECT;
	
	/**
	 * ID: 886<br>
	 * Message: �앨�̉��i��񂪐���������܂���B
	 */
	public static final SystemMessageId THE_CROPS_ARE_PRICED_INCORRECTLY;
	
	/**
	 * ID: 887<br>
	 * Message: �^�C�v������������܂���B
	 */
	public static final SystemMessageId THE_TYPE_IS_INCORRECT;
	
	/**
	 * ID: 888<br>
	 * Message: �w������앨������܂���B
	 */
	public static final SystemMessageId NO_CROPS_CAN_BE_PURCHASED_AT_THIS_TIME;
	
	/**
	 * ID: 889<br>
	 * Message: ��̐A�t���ɐ������܂����B
	 */
	public static final SystemMessageId THE_SEED_WAS_SUCCESSFULLY_SOWN;
	
	/**
	 * ID: 890<br>
	 * Message: ��̐A�t���Ɏ��s���܂����B
	 */
	public static final SystemMessageId THE_SEED_WAS_NOT_SOWN;
	
	/**
	 * ID: 891<br>
	 * Message: ���n�ɑ΂���D�挠������܂���B
	 */
	public static final SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_HARVEST;
	
	/**
	 * ID: 892<br>
	 * Message: ���n�Ɏ��s���܂����B
	 */
	public static final SystemMessageId THE_HARVEST_HAS_FAILED;
	
	/**
	 * ID: 893<br>
	 * Message: ��̐A�t����Ԃ������A���n�Ɏ��s���܂����B
	 */
	public static final SystemMessageId THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN;
	
	/**
	 * ID: 894<br>
	 * Message: ����}��$s1�������o�^�ł��܂��B
	 */
	public static final SystemMessageId UP_TO_S1_RECIPES_CAN_REGISTER;
	
	/**
	 * ID: 895<br>
	 * Message: �o�^���ꂽ����}������܂���B
	 */
	public static final SystemMessageId NO_RECIPES_REGISTERED;
	
	/**
	 * ID: 896<br>
	 * Message: �N�G�X�g����}�͓o�^�ł��܂���B
	 */
	public static final SystemMessageId FERRY_AT_GLUDIN;
	
	/**
	 * ID: 897<br>
	 * Message: ����萔��������������܂���B
	 */
	public static final SystemMessageId FERRY_LEAVE_TALKING;
	
	/**
	 * ID: 898<br>
	 * Message: ���x��10�ȏ�ɂȂ�Ȃ��ƁA���̐l�𐄑E���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId ONLY_LEVEL_SUP_10_CAN_RECOMMEND;
	
	/**
	 * ID: 899<br>
	 * Message: ��l�����ނ��Ƃ��ł��܂���B
	 */
	public static final SystemMessageId CANT_DRAW_SYMBOL;
	
	/**
	 * ID: 900<br>
	 * Message: ��l�����ޏꏊ������܂���B
	 */
	public static final SystemMessageId SYMBOLS_FULL;
	
	/**
	 * ID: 901<br>
	 * Message: ��l���������邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId SYMBOL_NOT_FOUND;
	
	/**
	 * ID: 902<br>
	 * Message: �A�C�e����������������܂���B
	 */
	public static final SystemMessageId NUMBER_INCORRECT;
	
	/**
	 * ID: 903<br>
	 * Message: �t���[�Y��Ԃł̓T�|�[�g�v�����s���܂���B���΂炭���҂����������B
	 */
	public static final SystemMessageId NO_PETITION_WHILE_FROZEN;
	
	/**
	 * ID: 904<br>
	 * Message: �l���X���J������Ԃł̓A�C�e�����̂Ă��܂���B
	 */
	public static final SystemMessageId NO_DISCARD_WHILE_PRIVATE_STORE;
	
	/**
	 * ID: 905<br>
	 * Message: �q���[�}���̃X�R�A�� $s1�_�ł��B
	 */
	public static final SystemMessageId HUMAN_SCORE_S1;
	
	/**
	 * ID: 906<br>
	 * Message: �G���t�̃X�R�A�� $s1�_�ł��B
	 */
	public static final SystemMessageId ELVES_SCORE_S1;
	
	/**
	 * ID: 907<br>
	 * Message: �_�[�N�G���t�̃X�R�A�� $s1�_�ł��B
	 */
	public static final SystemMessageId DARK_ELVES_SCORE_S1;
	
	/**
	 * ID: 908<br>
	 * Message: �I�[�N�̃X�R�A�� $s1�_�ł��B
	 */
	public static final SystemMessageId ORCS_SCORE_S1;
	
	/**
	 * ID: 909<br>
	 * Message: �h���[�t�̃X�R�A�� $s1�_�ł��B
	 */
	public static final SystemMessageId DWARVEN_SCORE_S1;
	
	/**
	 * ID: 910<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�b���铇�̑��t��)
	 */
	public static final SystemMessageId LOC_TI_S1_S2_S3;
	
	/**
	 * ID: 911<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�O���[�f�B�����t��)
	 */
	public static final SystemMessageId LOC_GLUDIN_S1_S2_S3;
	
	/**
	 * ID: 912<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�O���[�f�B�I��̑��t��)
	 */
	public static final SystemMessageId LOC_GLUDIO_S1_S2_S3;
	
	/**
	 * ID: 913<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�����n�ѕt��)
	 */
	public static final SystemMessageId LOC_NETRAL_ZONE_S1_S2_S3;
	
	/**
	 * ID: 914<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�G���t���t��)
	 */
	public static final SystemMessageId LOC_ELVEN_S1_S2_S3;
	
	/**
	 * ID: 915<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�_�[�N�G���t���t��)
	 */
	public static final SystemMessageId LOC_DARK_ELVEN_S1_S2_S3;
	
	/**
	 * ID: 916<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�f�B�I����̑��t��)
	 */
	public static final SystemMessageId LOC_DION_S1_S2_S3;
	
	/**
	 * ID: 917<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�t���[�������t��)
	 */
	public static final SystemMessageId LOC_FLORAN_S1_S2_S3;
	
	/**
	 * ID: 918<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�M������̑��t��)
	 */
	public static final SystemMessageId LOC_GIRAN_S1_S2_S3;
	
	/**
	 * ID: 919<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�M�����`�t��)
	 */
	public static final SystemMessageId LOC_GIRAN_HARBOR_S1_S2_S3;
	
	/**
	 * ID: 920<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�I�[�N���t��)
	 */
	public static final SystemMessageId LOC_ORC_S1_S2_S3;
	
	/**
	 * ID: 921<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�h���[�t���t��)
	 */
	public static final SystemMessageId LOC_DWARVEN_S1_S2_S3;
	
	/**
	 * ID: 922<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�I�[������̑��t��)
	 */
	public static final SystemMessageId LOC_OREN_S1_S2_S3;
	
	/**
	 * ID: 923<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�t�̑��t��)
	 */
	public static final SystemMessageId LOC_HUNTER_S1_S2_S3;
	
	/**
	 * ID: 924<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�A�f����̑��t��)
	 */
	public static final SystemMessageId LOC_ADEN_S1_S2_S3;
	
	/**
	 * ID: 925<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�R���V�A��)
	 */
	public static final SystemMessageId LOC_COLISEUM_S1_S2_S3;
	
	/**
	 * ID: 926<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�n�C�l�X�t��)
	 */
	public static final SystemMessageId LOC_HEINE_S1_S2_S3;
	
	/**
	 * ID: 927<br>
	 * Message: ���݂̎�����$s1��$s2���ł��B
	 */
	public static final SystemMessageId TIME_S1_S2_IN_THE_DAY;
	
	/**
	 * ID: 928<br>
	 * Message: ���݂̎�����$s1��$s2���ł��B
	 */
	public static final SystemMessageId TIME_S1_S2_IN_THE_NIGHT;
	
	/**
	 * ID: 929<br>
	 * Message: �앨�ɑ΂����܂͏o�܂���ł����B
	 */
	public static final SystemMessageId NO_COMPENSATION_FOR_FARM_PRODUCTS;
	
	/**
	 * ID: 930<br>
	 * Message: ���݁A�󂭂��̔̔����s�Ȃ��Ă���܂���B
	 */
	public static final SystemMessageId NO_LOTTERY_TICKETS_CURRENT_SOLD;
	
	/**
	 * ID: 931<br>
	 * Message: �󂭂����I�ԍ��͂܂����\����Ă���܂���B
	 */
	public static final SystemMessageId LOTTERY_WINNERS_NOT_ANNOUNCED_YET;
	
	/**
	 * ID: 932<br>
	 * Message: �ϐ풆�̂��߁A��ʃ`���b�g�͂ł��܂���B
	 */
	public static final SystemMessageId NO_ALLCHAT_WHILE_OBSERVING;
	
	/**
	 * ID: 933<br>
	 * Message: ��̉��i��񂪐���������܂���B
	 */
	public static final SystemMessageId THE_SEED_PRICING_GREATLY_DIFFERS_FROM_STANDARD_SEED_PRICES;
	
	/**
	 * ID: 934<br>
	 * Message: �폜���ꂽ����}�ł��B
	 */
	public static final SystemMessageId A_DELETED_RECIPE;
	
	/**
	 * ID: 935<br>
	 * Message: ���z�s���̂��߁A���������삵�܂���B
	 */
	public static final SystemMessageId THE_AMOUNT_IS_NOT_SUFFICIENT_AND_SO_THE_MANOR_IS_NOT_IN_OPERATION;
	
	/**
	 * ID: 936<br>
	 * Message: $s1���g�p���܂��B
	 */
	public static final SystemMessageId USE_S1_;
	
	/**
	 * ID: 937<br>
	 * Message: �l�H�[�̏������ł��B
	 */
	public static final SystemMessageId PREPARING_PRIVATE_WORKSHOP;
	
	/**
	 * ID: 938<br>
	 * Message: ���݁A�R�~���j�e�B�[ �T�[�o�[�̓_�����ł��B
	 */
	public static final SystemMessageId CB_OFFLINE;
	
	/**
	 * ID: 939<br>
	 * Message: �S�̎Ւf��ԂȂ̂Ńg���[�h���ł��܂���B
	 */
	public static final SystemMessageId NO_EXCHANGE_WHILE_BLOCKING;
	
	/**
	 * ID: 940<br>
	 * Message: $s1�͑S�̎Ւf��Ԃł��B
	 */
	public static final SystemMessageId S1_BLOCKED_EVERYTHING;
	
	/**
	 * ID: 941<br>
	 * Message: �b���铇�̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_TI;
	
	/**
	 * ID: 942<br>
	 * Message: �O���[�f�B�����Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_GLUDIN;
	
	/**
	 * ID: 943<br>
	 * Message: �O���[�f�B�I��̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_GLUDIO;
	
	/**
	 * ID: 944<br>
	 * Message: �����n�тŃ��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_NEUTRAL_ZONE;
	
	/**
	 * ID: 945<br>
	 * Message: �G���t���Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_ELFEN_VILLAGE;
	
	/**
	 * ID: 946<br>
	 * Message: �_�[�N�G���t���Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_DARKELF_VILLAGE;
	
	/**
	 * ID: 947<br>
	 * Message: �f�B�I����̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_DION;
	
	/**
	 * ID: 948<br>
	 * Message: �t���[�������Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_FLORAN;
	
	/**
	 * ID: 949<br>
	 * Message: �M������̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_GIRAN;
	
	/**
	 * ID: 950<br>
	 * Message: �M�����`�Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_GIRAN_HARBOR;
	
	/**
	 * ID: 951<br>
	 * Message: �I�[�N���Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_ORC_VILLAGE;
	
	/**
	 * ID: 952<br>
	 * Message: �h���[�t���Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_DWARFEN_VILLAGE;
	
	/**
	 * ID: 953<br>
	 * Message: �I�[������̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_OREN;
	
	/**
	 * ID: 954<br>
	 * Message: �t�̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_HUNTERS_VILLAGE;
	
	/**
	 * ID: 955<br>
	 * Message: �A�f����̑��Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_ADEN;
	
	/**
	 * ID: 956<br>
	 * Message: �R���V�A���Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_COLISEUM;
	
	/**
	 * ID: 957<br>
	 * Message: �n�C�l�X�Ń��X�^�[�g���܂��B
	 */
	public static final SystemMessageId RESTART_AT_HEINE;
	
	/**
	 * ID: 958<br>
	 * Message: �l���X��l�H�[�̏������ɁA�A�C�e�����̂Ă邱�Ƃ�j�󂷂邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId ITEMS_CANNOT_BE_DISCARDED_OR_DESTROYED_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP;
	
	/**
	 * ID: 959<br>
	 * Message: $s1�i��$s2�j�̐���ɐ������܂����B
	 */
	public static final SystemMessageId S1_S2_MANUFACTURED_SUCCESSFULLY;
	
	/**
	 * ID: 960<br>
	 * Message: $s1�̐���Ɏ��s���܂����B
	 */
	public static final SystemMessageId S1_MANUFACTURE_FAILURE;
	
	/**
	 * ID: 961<br>
	 * Message: ���ݑS�̎Ւf��Ԃł��B
	 */
	public static final SystemMessageId BLOCKING_ALL;
	
	/**
	 * ID: 962<br>
	 * Message: �S�̎Ւf��Ԃ��������܂����B
	 */
	public static final SystemMessageId NOT_BLOCKING_ALL;
	
	/**
	 * ID: 963<br>
	 * Message: ���쉿�i�����肵�Ă��������B
	 */
	public static final SystemMessageId DETERMINE_MANUFACTURE_PRICE;
	
	/**
	 * ID: 964<br>
	 * Message: �A���`���b�g�ɂ��A��1���ԃ`���b�g���֎~����܂����B
	 */
	public static final SystemMessageId CHATBAN_FOR_1_MINUTE;
	
	/**
	 * ID: 965<br>
	 * Message: �`���b�g�֎~����������܂����B
	 */
	public static final SystemMessageId CHATBAN_REMOVED;
	
	/**
	 * ID: 966<br>
	 * Message: �`���b�g�֎~���ł��B���������O�Ƀ`���b�g�����݂�ꍇ�A�����܂ł̎��Ԃ���蒷���Ȃ�܂��̂ł����ӂ��������B
	 */
	public static final SystemMessageId CHATTING_IS_CURRENTLY_PROHIBITED;
	
	/**
	 * ID: 967<br>
	 * Message: $c1�̃p�[�e�B�[���U�ɉ����܂����B�i�A�C�e�����z�F�X�|�C�����܂߃����_���Ɂj
	 */
	public static final SystemMessageId C1_PARTY_INVITE_RANDOM_INCLUDING_SPOIL;
	
	/**
	 * ID: 968<br>
	 * Message: $c1�̃p�[�e�B�[���U�ɉ����܂����B�i�A�C�e�����z�F���ԂɎ擾�j
	 */
	public static final SystemMessageId C1_PARTY_INVITE_BY_TURN;
	
	/**
	 * ID: 969<br>
	 * Message: $c1�̃p�[�e�B�[���U�ɉ����܂����B�i�A�C�e�����z�F�X�|�C�����܂ߏ��ԂɁj
	 */
	public static final SystemMessageId C1_PARTY_INVITE_BY_TURN_INCLUDING_SPOIL;
	
	/**
	 * ID: 970<br>
	 * Message: $c1����$s2��MP�h���C�����󂯂܂����B
	 */
	public static final SystemMessageId S2_MP_HAS_BEEN_DRAINED_BY_C1;
	
	/**
	 * ID: 971<br>
	 * Message: 255�����𒴂���T�|�[�g�v���͂ł��܂���B���萔�ł������͂�Z�����čēx�v�����s�Ȃ��Ă��������B
	 */
	public static final SystemMessageId PETITION_MAX_CHARS_255;
	
	/**
	 * ID: 972<br>
	 * Message: �y�b�g���g�p�ł��Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId PET_CANNOT_USE_ITEM;
	
	/**
	 * ID: 973<br>
	 * Message: �����Ă���ʂ͈͓̔��œ��͂��Ă��������B
	 */
	public static final SystemMessageId INPUT_NO_MORE_YOU_HAVE;
	
	/**
	 * ID: 974<br>
	 * Message: �\�E�� �X�g�[�������̋z���ɐ������܂����B
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_SUCCEEDED;
	
	/**
	 * ID: 975<br>
	 * Message: �\�E�� �X�g�[�������̋z���Ɏ��s���܂����B
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_FAILED;
	
	/**
	 * ID: 976<br>
	 * Message: �\�E�� �X�g�[�������̋C�ɑς���ꂸ�ӂ��܂����B
	 */
	public static final SystemMessageId SOUL_CRYSTAL_BROKE;
	
	/**
	 * ID: 977<br>
	 * Message: �\�E�� �X�g�[���������N�������̋z���Ɏ��s���܂����B
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_FAILED_RESONATION;
	
	/**
	 * ID: 978<br>
	 * Message: �\�E�� �X�g�[�������̋z�������ۂ��Ă��܂��B
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_REFUSED;
	
	/**
	 * ID: 979<br>
	 * Message: �b���铇�̍`�ɓ������܂����B
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_TALKING;
	
	/**
	 * ID: 980<br>
	 * Message: 10���Ԃ̒┑��A�O���[�f�B���ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GLUDIN_AFTER_10_MINUTES;
	
	/**
	 * ID: 981<br>
	 * Message: 5����ɘb���铇�̍`����O���[�f�B���֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GLUDIN_IN_5_MINUTES;
	
	/**
	 * ID: 982<br>
	 * Message: 1����ɘb���铇�̍`����O���[�f�B���֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GLUDIN_IN_1_MINUTE;
	
	/**
	 * ID: 983<br>
	 * Message: ��D�������͋}���ł��������B
	 */
	public static final SystemMessageId MAKE_HASTE_GET_ON_BOAT;
	
	/**
	 * ID: 984<br>
	 * Message: �Ԃ��Ȃ��b���铇�̍`����O���[�f�B���֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_SOON_FOR_GLUDIN;
	
	/**
	 * ID: 985<br>
	 * Message: �b���铇�̍`����O���[�f�B���֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_GLUDIN;
	
	/**
	 * ID: 986<br>
	 * Message: �O���[�f�B���`�ɓ������܂����B
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_GLUDIN;
	
	/**
	 * ID: 987<br>
	 * Message: 10���Ԃ̒┑��A�b���铇�ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_TALKING_AFTER_10_MINUTES;
	
	/**
	 * ID: 988<br>
	 * Message: 5����ɃO���[�f�B���`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_TALKING_IN_5_MINUTES;
	
	/**
	 * ID: 989<br>
	 * Message: 1����ɃO���[�f�B���`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_TALKING_IN_1_MINUTE;
	
	/**
	 * ID: 990<br>
	 * Message: �Ԃ��Ȃ��O���[�f�B���`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_SOON_FOR_TALKING;
	
	/**
	 * ID: 991<br>
	 * Message: �O���[�f�B���`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_TALKING;
	
	/**
	 * ID: 992<br>
	 * Message: �M�����`�ɓ������܂����B
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_GIRAN;
	
	/**
	 * ID: 993<br>
	 * Message: 10���Ԓ┑������A�M�����ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GIRAN_AFTER_10_MINUTES;
	
	/**
	 * ID: 994<br>
	 * Message: 5����ɃM�����`�ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GIRAN_IN_5_MINUTES;
	
	/**
	 * ID: 995<br>
	 * Message: 1����ɃM�����`�ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GIRAN_IN_1_MINUTE;
	
	/**
	 * ID: 996<br>
	 * Message: �܂��Ȃ��M�����`�ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVE_SOON_FOR_GIRAN;
	
	/**
	 * ID: 997<br>
	 * Message: �M�����`�ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_GIRAN;
	
	/**
	 * ID: 998<br>
	 * Message: �C���i�h�����V���D���������܂����B10���Ԓ┑���܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_ANCHOR_10_MINUTES;
	
	/**
	 * ID: 999<br>
	 * Message: 5����ɃC���i�h�����V���D���o�����܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVE_IN_5_MINUTES;
	
	/**
	 * ID: 1000<br>
	 * Message: 1����ɃC���i�h�����V���D���o�����܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVE_IN_1_MINUTE;
	
	/**
	 * ID: 1001<br>
	 * Message: �܂��Ȃ��C���i�h�����V���D���o�����܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVE_SOON;
	
	/**
	 * ID: 1002<br>
	 * Message: �C���i�h�����V���D���o�����܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVING;
	
	/**
	 * ID: 1003<br>
	 * Message: �����X�^�[ ���[�X �`�P�b�g�̐��Z���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_POSSES_MONS_TICKET;
	
	/**
	 * ID: 1004<br>
	 * Message: �A�W�g�̋����ɓo�^���܂����B
	 */
	public static final SystemMessageId REGISTERED_FOR_CLANHALL;
	
	/**
	 * ID: 1005<br>
	 * Message: �����q�ɂɃA�f�i������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_ADENA_IN_CWH;
	
	/**
	 * ID: 1006<br>
	 * Message: �A�W�g�̋����ɓ��D���܂����B
	 */
	public static final SystemMessageId BID_IN_CLANHALL_AUCTION;
	
	/**
	 * ID: 1007<br>
	 * Message: $s1�̗\�I�o�^�����ߐ؂��܂����B
	 */
	public static final SystemMessageId PRELIMINARY_REGISTRATION_OF_S1_FINISHED;
	
	/**
	 * ID: 1008<br>
	 * Message: �󕠏�Ԃ̃X�g���C�_�[�ɂ͏��~��ł��܂���B
	 */
	public static final SystemMessageId HUNGRY_STRIDER_NOT_MOUNT;
	
	/**
	 * ID: 1009<br>
	 * Message: ���S��Ԃł̓X�g���C�_�[�ɏ�邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId STRIDER_CANT_BE_RIDDEN_WHILE_DEAD;
	
	/**
	 * ID: 1010<br>
	 * Message: ���S�����X�g���C�_�[�ɏ�邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId DEAD_STRIDER_CANT_BE_RIDDEN;
	
	/**
	 * ID: 1011<br>
	 * Message: �퓬���̃X�g���C�_�[�ɏ�邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId STRIDER_IN_BATLLE_CANT_BE_RIDDEN;
	
	/**
	 * ID: 1012<br>
	 * Message: �퓬���̓X�g���C�_�[�ɏ�邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId STRIDER_CANT_BE_RIDDEN_WHILE_IN_BATTLE;
	
	/**
	 * ID: 1013<br>
	 * Message: �����Ă����Ԃ̃X�g���C�_�[�����ɏ�邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId STRIDER_CAN_BE_RIDDEN_ONLY_WHILE_STANDING;
	
	/**
	 * ID: 1014<br>
	 * Message: �y�b�g��$s1�̌o���l�𓾂܂����B
	 */
	public static final SystemMessageId PET_EARNED_S1_EXP;
	
	/**
	 * ID: 1015<br>
	 * Message: �y�b�g��$s1�̃_���[�W��^���܂����B
	 */
	public static final SystemMessageId PET_HIT_FOR_S1_DAMAGE;
	
	/**
	 * ID: 1016<br>
	 * Message: �y�b�g��$c1�ɂ����$s2�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId PET_RECEIVED_S2_DAMAGE_BY_C1;
	
	/**
	 * ID: 1017<br>
	 * Message: �y�b�g�̃N���e�B�J�� �q�b�g�I
	 */
	public static final SystemMessageId CRITICAL_HIT_BY_PET;
	
	/**
	 * ID: 1018<br>
	 * Message: �y�b�g��$s1���g�p���܂��B
	 */
	public static final SystemMessageId PET_USES_S1;
	
	/**
	 * ID: 1019<br>
	 * Message: �y�b�g��$s1���g�p���܂��B
	 */
	public static final SystemMessageId PET_USES_S1_;
	
	/**
	 * ID: 1020<br>
	 * Message: �y�b�g��$s1�𓾂܂����B
	 */
	public static final SystemMessageId PET_PICKED_S1;
	
	/**
	 * ID: 1021<br>
	 * Message: �y�b�g��$s1 $s2�𓾂܂����B
	 */
	public static final SystemMessageId PET_PICKED_S2_S1_S;
	
	/**
	 * ID: 1022<br>
	 * Message: �y�b�g��+$s1$s2�𓾂܂����B
	 */
	public static final SystemMessageId PET_PICKED_S1_S2;
	
	/**
	 * ID: 1023<br>
	 * Message: �y�b�g��$s1�A�f�i�𓾂܂����B
	 */
	public static final SystemMessageId PET_PICKED_S1_ADENA;
	
	/**
	 * ID: 1024<br>
	 * Message: �y�b�g��$s1�𑕔����܂����B
	 */
	public static final SystemMessageId PET_PUT_ON_S1;
	
	/**
	 * ID: 1025<br>
	 * Message: �y�b�g��$s1�̑������������܂����B
	 */
	public static final SystemMessageId PET_TOOK_OFF_S1;
	
	/**
	 * ID: 1026<br>
	 * Message: �����b��$s1�̃_���[�W��^���܂����B
	 */
	public static final SystemMessageId SUMMON_GAVE_DAMAGE_S1;
	
	/**
	 * ID: 1027<br>
	 * Message: �����b��$c1�ɂ����$s2�̃_���[�W���󂯂܂����B
	 */
	public static final SystemMessageId SUMMON_RECEIVED_DAMAGE_S2_BY_S1;
	
	/**
	 * ID: 1028<br>
	 * Message: �����b�̃N���e�B�J�� �q�b�g�I
	 */
	public static final SystemMessageId CRITICAL_HIT_BY_SUMMONED_MOB;
	
	/**
	 * ID: 1029<br>
	 * Message: �����b��$s1���g�p���܂��B
	 */
	public static final SystemMessageId SUMMONED_MOB_USES_S1;
	
	/**
	 * ID: 1030<br>
	 * Message: =======<�p�[�e�B�[���>=======
	 */
	public static final SystemMessageId PARTY_INFORMATION;
	
	/**
	 * ID: 1031<br>
	 * Message: �^�C�v�F �E�����l�����L
	 */
	public static final SystemMessageId LOOTING_FINDERS_KEEPERS;
	
	/**
	 * ID: 1032<br>
	 * Message: �^�C�v�F �����_���ɕ��z
	 */
	public static final SystemMessageId LOOTING_RANDOM;
	
	/**
	 * ID: 1033<br>
	 * Message: �^�C�v�F �X�|�C�����܂߃����_����
	 */
	public static final SystemMessageId LOOTING_RANDOM_INCLUDE_SPOIL;
	
	/**
	 * ID: 1034<br>
	 * Message: �^�C�v�F ���ԂɎ擾
	 */
	public static final SystemMessageId LOOTING_BY_TURN;
	
	/**
	 * ID: 1035<br>
	 * Message: �^�C�v�F �X�|�C�����܂ߏ��Ԃ�
	 */
	public static final SystemMessageId LOOTING_BY_TURN_INCLUDE_SPOIL;
	
	/**
	 * ID: 1036<br>
	 * Message: ���͉\�Ȑ��ʂ𒴉߂��܂����B
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_QUANTITY_THAT_CAN_BE_INPUTTED;
	
	/**
	 * ID: 1037<br>
	 * Message: $c1��$s2�𐻍삵�܂����B
	 */
	public static final SystemMessageId C1_MANUFACTURED_S2;
	
	/**
	 * ID: 1038<br>
	 * Message: $c1��$s2 $s3�𐻍삵�܂����B
	 */
	public static final SystemMessageId C1_MANUFACTURED_S3_S2_S;
	
	/**
	 * ID: 1039<br>
	 * Message: �����q�ɂɗa�����A�C�e���͌�����̂ݎ��o�����\�ł��B����ł���낵���ł����B
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_CAN_RETRIEVE_ITEMS_FROM_CLAN_WAREHOUSE;
	
	/**
	 * ID: 1040<br>
	 * Message: ���������g�b�s���O �A�C�e���͑S�n��̋��E�̏��l������o���܂��B��낵���ł����B
	 */
	public static final SystemMessageId ITEMS_SENT_BY_FREIGHT_PICKED_UP_FROM_ANYWHERE;
	
	/**
	 * ID: 1041<br>
	 * Message: ���̎�̍w�������$s1�A�f�i�ł��B
	 */
	public static final SystemMessageId THE_NEXT_SEED_PURCHASE_PRICE_IS_S1_ADENA;
	
	/**
	 * ID: 1042<br>
	 * Message: ���̍앨�̔̔������$s1�A�f�i�ł��B
	 */
	public static final SystemMessageId THE_NEXT_FARM_GOODS_PURCHASE_PRICE_IS_S1_ADENA;
	
	/**
	 * ID: 1043<br>
	 * Message: ���݁A�u/escape�v�R�}���h�͎g�p�ł��܂���B�T�|�[�g�v�����s�Ȃ��Ă��������B
	 */
	public static final SystemMessageId NO_UNSTUCK_PLEASE_SEND_PETITION;
	
	/**
	 * ID: 1044<br>
	 * Message: �`�P�b�g�̔����̓����X�^�[ ���[�X�̔z���\�������邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId MONSRACE_NO_PAYOUT_INFO;
	
	/**
	 * ID: 1046<br>
	 * Message: �̔����I�����A�����X�^�[ ���[�X�̃`�P�b�g���w���ł��܂���B
	 */
	public static final SystemMessageId MONSRACE_TICKETS_NOT_AVAILABLE;
	
	/**
	 * ID: 1047<br>
	 * Message: $s1�̃A�C�e������Ɏ��s���܂����B
	 */
	public static final SystemMessageId NOT_SUCCEED_PRODUCING_S1;
	
	/**
	 * ID: 1048<br>
	 * Message: �S�̎Ւf��ԂȂ̂ŃE�B�X�p�[���g�p�ł��܂���B
	 */
	public static final SystemMessageId NO_WHISPER_WHEN_BLOCKING;
	
	/**
	 * ID: 1049<br>
	 * Message: �S�̎Ւf��ԂȂ̂Ńp�[�e�B�[�̏��҂��ł��܂���B
	 */
	public static final SystemMessageId NO_PARTY_WHEN_BLOCKING;
	
	/**
	 * ID: 1050<br>
	 * Message: �����̌����R�~���j�e�B�[������܂���B���̑�_�����͑咷�V��K�˂Č����̃��x����2�ȏ�ɏグ��΃R�~���j�e�B�[����������܂��B
	 */
	public static final SystemMessageId NO_CB_IN_MY_CLAN;
	
	/**
	 * ID: 1051<br>
	 * Message: �A�W�g�̎g�p�������x�����܂���ł����B����$s1���܂łɎg�p�����������q�ɂɂ��[�߂��������B
	 */
	public static final SystemMessageId PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW;
	
	/**
	 * ID: 1052<br>
	 * Message: �A�W�g�g�p��������T�ԑؔ[�������߃A�W�g���Ԋ҂���܂����B
	 */
	public static final SystemMessageId THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED;
	
	/**
	 * ID: 1053<br>
	 * Message: �U��풆�̐��ł͕����ł��܂���B
	 */
	public static final SystemMessageId CANNOT_BE_RESURRECTED_DURING_SIEGE;
	
	/**
	 * ID: 1054<br>
	 * Message: �_��̋C�ɖ������G���A�ɓ���܂����B
	 */
	public static final SystemMessageId ENTERED_MYSTICAL_LAND;
	
	/**
	 * ID: 1055<br>
	 * Message: �_��̋C�ɖ������G���A���痣��܂����B
	 */
	public static final SystemMessageId EXITED_MYSTICAL_LAND;
	
	/**
	 * ID: 1056<br>
	 * Message: ��̋��ɂɕۊǂł���A�f�i�̌��x�z�𒴂��܂����B
	 */
	public static final SystemMessageId VAULT_CAPACITY_EXCEEDED;
	
	/**
	 * ID: 1057<br>
	 * Message: �����b�N�X �T�[�o�[�ł̂ݎg�p�\�ȃR�}���h�ł��B
	 */
	public static final SystemMessageId RELAX_SERVER_ONLY;
	
	/**
	 * ID: 1058<br>
	 * Message: ��̔̔������$s1�A�f�i�ł��B
	 */
	public static final SystemMessageId THE_SALES_PRICE_FOR_SEEDS_IS_S1_ADENA;
	
	/**
	 * ID: 1059<br>
	 * Message: �c��̔�����������$s1�A�f�i�ł��B
	 */
	public static final SystemMessageId THE_REMAINING_PURCHASING_IS_S1_ADENA;
	
	/**
	 * ID: 1060<br>
	 * Message: ���̔����Ďc�������z��$s1�A�f�i�ł��B
	 */
	public static final SystemMessageId THE_REMAINDER_AFTER_SELLING_THE_SEEDS_IS_S1;
	
	/**
	 * ID: 1061<br>
	 * Message: �A�C�e������X�L��������Ȃ����߁A����}��o�^�ł��܂���B
	 */
	public static final SystemMessageId CANT_REGISTER_NO_ABILITY_TO_CRAFT;
	
	/**
	 * ID: 1062<br>
	 * Message: �V�K�쐬�̓��x��10����\�ł��B
	 */
	public static final SystemMessageId WRITING_SOMETHING_NEW_POSSIBLE_AFTER_LEVEL_10;
	
	/**
	 * ID: 1063<br>
	 * Message: $s1������$s2���܂ł̓T�|�[�g�𗘗p�ł��܂���B�ړ����s�\�Ȓn�`�ɕ����߂�ꂽ�ꍇ�́u/escape�v�R�}���h�������p���������B
	 */
	public static final SystemMessageId PETITION_UNAVAILABLE;
	
	/**
	 * ID: 1064<br>
	 * Message: +$s1$s2�̑������������܂����B
	 */
	public static final SystemMessageId EQUIPMENT_S1_S2_REMOVED;
	
	/**
	 * ID: 1065<br>
	 * Message: �l���X��l�H�[�̏ꍇ�A�A�C�e�����̂Ă邱�Ƃ�g���[�h���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_TRADE_DISCARD_DROP_ITEM_WHILE_IN_SHOPMODE;
	
	/**
	 * ID: 1066<br>
	 * Message: $s1��HP���񕜂���܂����B
	 */
	public static final SystemMessageId S1_HP_RESTORED;
	
	/**
	 * ID: 1067<br>
	 * Message: $c1�ɂ����HP��$s2�񕜂���܂����B
	 */
	public static final SystemMessageId S2_HP_RESTORED_BY_C1;
	
	/**
	 * ID: 1068<br>
	 * Message: MP��$s1�񕜂��܂����B
	 */
	public static final SystemMessageId S1_MP_RESTORED;
	
	/**
	 * ID: 1069<br>
	 * Message: $c1�ɂ����MP��$s2�񕜂��܂����B
	 */
	public static final SystemMessageId S2_MP_RESTORED_BY_C1;
	
	/**
	 * ID: 1070<br>
	 * Message: �ǂݍ��݌���������܂���B
	 */
	public static final SystemMessageId NO_READ_PERMISSION;
	
	/**
	 * ID: 1071<br>
	 * Message: �������݌���������܂���B
	 */
	public static final SystemMessageId NO_WRITE_PERMISSION;
	
	/**
	 * ID: 1072<br>
	 * Message: ��$s1��ڃ����X�^�[���[�X�̒P�����`�P�b�g���󂯎��܂����B
	 */
	public static final SystemMessageId OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE;
	
	/**
	 * ID: 1073<br>
	 * Message: ��$s1��ڃ����X�^�[���[�X�̘A�����`�P�b�g���󂯎��܂����B
	 */
	public static final SystemMessageId OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE_;
	
	/**
	 * ID: 1074<br>
	 * Message: �N����Ń����X�^�[ ���[�X�̃`�P�b�g���w���ł��܂���B
	 */
	public static final SystemMessageId NOT_MEET_AGE_REQUIREMENT_FOR_MONS_RACE;
	
	/**
	 * ID: 1075<br>
	 * Message: �ē��D���i�͊����̓��D���i��荂���ݒ肵�Ă��������B
	 */
	public static final SystemMessageId BID_AMOUNT_HIGHER_THAN_PREVIOUS_BID;
	
	/**
	 * ID: 1076<br>
	 * Message: �Q�[�����I���ł��܂���B
	 */
	public static final SystemMessageId GAME_CANNOT_TERMINATE_NOW;
	
	/**
	 * ID: 1077<br>
	 * Message: GameGuard�̃G���[�ł��B
	 */
	public static final SystemMessageId GG_EXECUTION_ERROR;
	
	/**
	 * ID: 1078<br>
	 * Message: �Z���Ԃŏd��������e�̔����𑽗ʂɓ��͂���Ǝ����I�Ƀ`���b�g�֎~�ɂȂ�܂��̂ŁA�����ӂ��������B
	 */
	public static final SystemMessageId DONT_SPAM;
	
	/**
	 * ID: 1079<br>
	 * Message: ���肪�`���b�g�֎~���ł��B
	 */
	public static final SystemMessageId TARGET_IS_CHAT_BANNED;
	
	/**
	 * ID: 1080<br>
	 * Message: �t�F�C�X�^�C�v�̃|�[�V�����FA���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_A;
	
	/**
	 * ID: 1081<br>
	 * Message: �w�A�J���[�̃|�[�V�����FA���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_A;
	
	/**
	 * ID: 1082<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FA���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_A;
	
	/**
	 * ID: 1083<br>
	 * Message: �t�F�C�X�^�C�v�̃|�[�V�����FA���g�p���܂����B
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_A_APPLIED;
	
	/**
	 * ID: 1084<br>
	 * Message: �w�A�J���[�̃|�[�V�����FA���g�p���܂����B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_A_APPLIED;
	
	/**
	 * ID: 1085<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FA���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_A_USED;
	
	/**
	 * ID: 1086<br>
	 * Message: �t�F�C�X�^�C�v���ύX����܂����B
	 */
	public static final SystemMessageId FACE_APPEARANCE_CHANGED;
	
	/**
	 * ID: 1087<br>
	 * Message: �w�A�J���[���ύX����܂����B
	 */
	public static final SystemMessageId HAIR_COLOR_CHANGED;
	
	/**
	 * ID: 1088<br>
	 * Message: �w�A�X�^�C�����ύX����܂����B
	 */
	public static final SystemMessageId HAIR_STYLE_CHANGED;
	
	/**
	 * ID: 1089<br>
	 * Message: $c1��1���N�L�O�A�C�e���𓾂܂����B
	 */
	public static final SystemMessageId C1_OBTAINED_ANNIVERSARY_ITEM;
	
	/**
	 * ID: 1090<br>
	 * Message: �t�F�C�X�^�C�v�̃|�[�V�����FB���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_B;
	
	/**
	 * ID: 1091<br>
	 * Message: �t�F�C�X�^�C�v�̃|�[�V�����FC���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_C;
	
	/**
	 * ID: 1092<br>
	 * Message: �w�A�J���[�̃|�[�V�����FB���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_B;
	
	/**
	 * ID: 1093<br>
	 * Message: �w�A�J���[�̃|�[�V�����FC���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_C;
	
	/**
	 * ID: 1094<br>
	 * Message: �w�A�J���[�̃|�[�V�����FD���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_D;
	
	/**
	 * ID: 1095<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FB���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_B;
	
	/**
	 * ID: 1096<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FC���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_C;
	
	/**
	 * ID: 1097<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FD���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_D;
	
	/**
	 * ID: 1098<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FE���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_E;
	
	/**
	 * ID: 1099<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FF���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_F;
	
	/**
	 * ID: 1100<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FG���g�p���܂����B�g�p��̌��ʂ͉i�v�Ɏ������܂��B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_G;
	
	/**
	 * ID: 1101<br>
	 * Message: �t�F�C�X�^�C�v�̃|�[�V�����FB���g�p���܂����B
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_B_APPLIED;
	
	/**
	 * ID: 1102<br>
	 * Message: �t�F�C�X�^�C�v�̃|�[�V�����FC���g�p���܂����B
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_C_APPLIED;
	
	/**
	 * ID: 1103<br>
	 * Message: �w�A�J���[�̃|�[�V�����FB���g�p���܂����B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_B_APPLIED;
	
	/**
	 * ID: 1104<br>
	 * Message: �w�A�J���[�̃|�[�V�����FC���g�p���܂����B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_C_APPLIED;
	
	/**
	 * ID: 1105<br>
	 * Message: �w�A�J���[�̃|�[�V�����FD���g�p���܂����B
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_D_APPLIED;
	
	/**
	 * ID: 1106<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FB���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_B_USED;
	
	/**
	 * ID: 1107<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FC���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_C_USED;
	
	/**
	 * ID: 1108<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FD���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_D_USED;
	
	/**
	 * ID: 1109<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FE���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_E_USED;
	
	/**
	 * ID: 1110<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FF���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_F_USED;
	
	/**
	 * ID: 1111<br>
	 * Message: �w�A�X�^�C���̃|�[�V�����FG���g�p���܂����B
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_G_USED;
	
	/**
	 * ID: 1112<br>
	 * Message: ��$s1��ڕ󂭂��̓��I���z��$s2�A�f�i�ŁA$s3���l��1���ɓ��I���܂����B
	 */
	public static final SystemMessageId AMOUNT_FOR_WINNER_S1_IS_S2_ADENA_WE_HAVE_S3_PRIZE_WINNER;
	
	/**
	 * ID: 1113<br>
	 * Message: ��$s1��ڕ󂭂��̓��I���z��$s2�A�f�i�ł������A���z���I�҂����Ȃ����߁A���I���̌J��z�����������܂����B
	 */
	public static final SystemMessageId AMOUNT_FOR_LOTTERY_S1_IS_S2_ADENA_NO_WINNER;
	
	/**
	 * ID: 1114<br>
	 * Message: ���U�P�\���Ԓ��͍U���ɓo�^�ł��܂���B
	 */
	public static final SystemMessageId CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 1115<br>
	 * Message: �퓬���͌l�~�����邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId INDIVIDUALS_NOT_SURRENDER_DURING_COMBAT;
	
	/**
	 * ID: 1116<br>
	 * Message: �퓬���͌�������E�ނł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_LEAVE_DURING_COMBAT;
	
	/**
	 * ID: 1117<br>
	 * Message: �퓬���̌�������Ǖ����邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CLAN_MEMBER_CANNOT_BE_DISMISSED_DURING_COMBAT;
	
	/**
	 * ID: 1118<br>
	 * Message: �C���x���g���̏d�ʂ܂��͌���80�������̎��̂ݐi�s�ł��܂��B
	 */
	public static final SystemMessageId INVENTORY_LESS_THAN_80_PERCENT;
	
	/**
	 * ID: 1119<br>
	 * Message: �N�G�X�g�̐i�s������ɍs�Ȃ��Ȃ��󋵂ŃN�G�X�g�����݂��̂ŁA�N�G�X�g�������I�Ɏ�������܂����B
	 */
	public static final SystemMessageId QUEST_CANCELED_INVENTORY_EXCEEDS_80_PERCENT;
	
	/**
	 * ID: 1120<br>
	 * Message: ��������E�ނł��܂���ł����B
	 */
	public static final SystemMessageId STILL_CLAN_MEMBER;
	
	/**
	 * ID: 1121<br>
	 * Message: ���E��������܂���B
	 */
	public static final SystemMessageId NO_RIGHT_TO_VOTE;
	
	/**
	 * ID: 1122<br>
	 * Message: ���E���鑊�肪���݂��܂���B
	 */
	public static final SystemMessageId NO_CANDIDATE;
	
	/**
	 * ID: 1123<br>
	 * Message: �C���x���g���̏d�ʂ܂��͌����������z���Ă��邽�߁A���̃X�L���͎g�p�ł��܂���B
	 */
	public static final SystemMessageId WEIGHT_EXCEEDED_SKILL_UNAVAILABLE;
	
	/**
	 * ID: 1124<br>
	 * Message: �X�L���g�p���̂��߁A�A�C�e������E�B���h�E���J�����Ƃ��ł��܂���B
	 */
	public static final SystemMessageId NO_RECIPE_BOOK_WHILE_CASTING;
	
	/**
	 * ID: 1125<br>
	 * Message: �g���[�h���͐���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_CREATED_WHILE_ENGAGED_IN_TRADING;
	
	/**
	 * ID: 1126<br>
	 * Message: �}�C�i�X�̒l�͓��͂ł��܂���B
	 */
	public static final SystemMessageId NO_NEGATIVE_NUMBER;
	
	/**
	 * ID: 1127<br>
	 * Message: ��{���i��10�{�ȏ�̉��i�͐ݒ�ł��܂���B
	 */
	public static final SystemMessageId REWARD_LESS_THAN_10_TIMES_STANDARD_PRICE;
	
	/**
	 * ID: 1128<br>
	 * Message: �X�L���g�p���̂��߁A�l���X��l�H�[���J�����Ƃ��ł��܂���B
	 */
	public static final SystemMessageId PRIVATE_STORE_NOT_WHILE_CASTING;
	
	/**
	 * ID: 1129<br>
	 * Message: ��D���̂��߁A���̍s���͂ł��܂���ł����B
	 */
	public static final SystemMessageId NOT_ALLOWED_ON_BOAT;
	
	/**
	 * ID: 1130<br>
	 * Message: �����$s1�̃_���[�W��^���A�_���[�W�̓]�ڑΏۂ�$s2�̃_���[�W��^���܂����B
	 */
	public static final SystemMessageId GIVEN_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_SERVITOR;
	
	/**
	 * ID: 1131<br>
	 * Message: �[��ɂȂ������߁A$s1�̌��ʂ������܂��B
	 */
	public static final SystemMessageId NIGHT_EFFECT_APPLIES;
	
	/**
	 * ID: 1132<br>
	 * Message: �邪���������߁A$s1�̌��ʂ������܂����B
	 */
	public static final SystemMessageId DAY_EFFECT_DISAPPEARS;
	
	/**
	 * ID: 1133<br>
	 * Message: HP�����������߁A$s1�̌��ʂ������܂��B
	 */
	public static final SystemMessageId HP_DECREASED_EFFECT_APPLIES;
	
	/**
	 * ID: 1134<br>
	 * Message: HP�����������߁A$s1�̌��ʂ������܂����B
	 */
	public static final SystemMessageId HP_INCREASED_EFFECT_DISAPPEARS;
	
	/**
	 * ID: 1135<br>
	 * Message: �퓬���Ɍl���X���J�����Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANT_OPERATE_PRIVATE_STORE_DURING_COMBAT;
	
	/**
	 * ID: 1136<br>
	 * Message: �s���ȃA�N�Z�X�����݂�IP�A�h���X�̂��߁A$s1���Ԃ��̃T�[�o�[�ɐڑ��ł��܂���B�ʂ̃T�[�o�[�������p���������B
	 */
	public static final SystemMessageId ACCOUNT_NOT_ALLOWED_TO_CONNECT;
	
	/**
	 * ID: 1137<br>
	 * Message: $c1�� $s2 $s3�����n���܂����B
	 */
	public static final SystemMessageId C1_HARVESTED_S3_S2S;
	
	/**
	 * ID: 1138<br>
	 * Message: $c1��$s2�����n���܂����B
	 */
	public static final SystemMessageId C1_HARVESTED_S2S;
	
	/**
	 * ID: 1139<br>
	 * Message: �C���x���g���̑��d�ʂ�X���b�g���̐����𒴂��邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId INVENTORY_LIMIT_MUST_NOT_BE_EXCEEDED;
	
	/**
	 * ID: 1140<br>
	 * Message: ����J���܂����B
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_OPEN_THE_GATE;
	
	/**
	 * ID: 1141<br>
	 * Message: �����܂����B
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_CLOSE_THE_GATE;
	
	/**
	 * ID: 1142<br>
	 * Message: �߂��ɂ��ł�$s1�����邽�߁A�d�����ď����ł��܂���B
	 */
	public static final SystemMessageId CANNOT_SUMMON_S1_AGAIN;
	
	/**
	 * ID: 1143<br>
	 * Message: �����b�̈ێ��ɕK�v�ȃA�C�e��������Ȃ����߁A�����b�������Ă��܂��܂����B
	 */
	public static final SystemMessageId SERVITOR_DISAPPEARED_NOT_ENOUGH_ITEMS;
	
	/**
	 * ID: 1144<br>
	 * Message: �Q�[�����ɑΘb���肪���܂���B
	 */
	public static final SystemMessageId NOBODY_IN_GAME_TO_CHAT;
	
	/**
	 * ID: 1145<br>
	 * Message: $c1�� $s2�� $s3 �A�f�i�Ő��삵�܂����B
	 */
	public static final SystemMessageId S2_CREATED_FOR_C1_FOR_S3_ADENA;
	
	/**
	 * ID: 1146<br>
	 * Message: $c1�� $s2�� $s3 �A�f�i�Ő��삵�܂����B
	 */
	public static final SystemMessageId C1_CREATED_S2_FOR_S3_ADENA;
	
	/**
	 * ID: 1147<br>
	 * Message: $c1�� $s2 $s3�� $s4�A�f�i�Ő��삵�܂����B
	 */
	public static final SystemMessageId S2_S3_S_CREATED_FOR_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1148<br>
	 * Message: $c1�� $s2 $s3�� $s4�A�f�i�Ő��삵�܂����B
	 */
	public static final SystemMessageId C1_CREATED_S2_S3_S_FOR_S4_ADENA;
	
	/**
	 * ID: 1149<br>
	 * Message: $c1�� $s2�� $s3 �A�f�i�Ő���ł��܂���ł����B
	 */
	public static final SystemMessageId CREATION_OF_S2_FOR_C1_AT_S3_ADENA_FAILED;
	
	/**
	 * ID: 1150<br>
	 * Message: $c1�� $s2�� $s3 �A�f�i�Ő���ł��܂���ł����B
	 */
	public static final SystemMessageId C1_FAILED_TO_CREATE_S2_FOR_S3_ADENA;
	
	/**
	 * ID: 1151<br>
	 * Message: $c1�� $s2�� $s3 �A�f�i�Ŕ̔����܂����B
	 */
	public static final SystemMessageId S2_SOLD_TO_C1_FOR_S3_ADENA;
	
	/**
	 * ID: 1152<br>
	 * Message: $c1�� $s2 $s3�� $s4 �A�f�i�Ŕ̔����܂����B
	 */
	public static final SystemMessageId S3_S2_S_SOLD_TO_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1153<br>
	 * Message: $c1���� $s2�� $s3 �A�f�i�ōw�����܂����B
	 */
	public static final SystemMessageId S2_PURCHASED_FROM_C1_FOR_S3_ADENA;
	
	/**
	 * ID: 1154<br>
	 * Message: $c1���� $s2 $s3�� $s4 �A�f�i�ōw�����܂����B
	 */
	public static final SystemMessageId S3_S2_S_PURCHASED_FROM_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1155<br>
	 * Message: $c1�� +$s2$s3�� $s4 �A�f�i�Ŕ̔����܂����B
	 */
	public static final SystemMessageId S3_S2_SOLD_TO_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1156<br>
	 * Message: $c1���� +$s2$s3�� $s4 �A�f�i�ōw�����܂����B
	 */
	public static final SystemMessageId S2_S3_PURCHASED_FROM_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1157<br>
	 * Message: �����͖�10�b�ԂŁA��Ԃ̕ω�������Ǝ��������ꍇ������܂��B
	 */
	public static final SystemMessageId TRYING_ON_STATE;
	
	/**
	 * ID: 1158<br>
	 * Message: ���߂��鏊����͔�э~��邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISMOUNT_FROM_ELEVATION;
	
	/**
	 * ID: 1159<br>
	 * Message: �b���铇���̒���D����10����ɃO���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_10_MINUTES;
	
	/**
	 * ID: 1160<br>
	 * Message: �b���铇���̒���D����5����ɃO���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_5_MINUTES;
	
	/**
	 * ID: 1161<br>
	 * Message: �b���铇���̒���D����1����ɃO���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_1_MINUTE;
	
	/**
	 * ID: 1162<br>
	 * Message: �M�����`���̒���D����15����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_15_MINUTES;
	
	/**
	 * ID: 1163<br>
	 * Message: �M�����`���̒���D����10����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_10_MINUTES;
	
	/**
	 * ID: 1164<br>
	 * Message: �M�����`���̒���D����5����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_5_MINUTES;
	
	/**
	 * ID: 1165<br>
	 * Message: �M�����`���̒���D����1����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_1_MINUTE;
	
	/**
	 * ID: 1166<br>
	 * Message: �b���铇���̒���D����20����ɃM�����`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_20_MINUTES;
	
	/**
	 * ID: 1167<br>
	 * Message: �b���铇���̒���D����15����ɃM�����`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_15_MINUTES;
	
	/**
	 * ID: 1168<br>
	 * Message: �b���铇���̒���D����10����ɃM�����`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_10_MINUTES;
	
	/**
	 * ID: 1169<br>
	 * Message: �b���铇���̒���D����5����ɃM�����`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_5_MINUTES;
	
	/**
	 * ID: 1170<br>
	 * Message: �b���铇���̒���D����1����ɃM�����`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_1_MINUTE;
	
	/**
	 * ID: 1171<br>
	 * Message: ��20����C���i�h�����V���D����D��ɓ������܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_20_MINUTES;
	
	/**
	 * ID: 1172<br>
	 * Message: ��15����C���i�h�����V���D����D��ɓ������܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_15_MINUTES;
	
	/**
	 * ID: 1173<br>
	 * Message: ��10����C���i�h�����V���D����D��ɓ������܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_10_MINUTES;
	
	/**
	 * ID: 1174<br>
	 * Message: ��5����C���i�h�����V���D����D��ɓ������܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_5_MINUTES;
	
	/**
	 * ID: 1175<br>
	 * Message: ��1����C���i�h�����V���D����D��ɓ������܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_1_MINUTE;
	
	/**
	 * ID: 1176<br>
	 * Message: �������Ԃł��B
	 */
	public static final SystemMessageId QUEST_EVENT_PERIOD;
	
	/**
	 * ID: 1177<br>
	 * Message: ����L�����Ԃł��B
	 */
	public static final SystemMessageId VALIDATION_PERIOD;
	
	/**
	 * ID: 1178<br>
	 * Message: ���̕�������L���錈�����́A����L�����Ԓ��A�×~�̕���ɂ���ĊJ�������ʂȃ_���W�����ɓ��ꂷ�邱�Ƃ��ł��A���ʂȃ_���W�����ɏo������}�����̏��l�Ǝ�����邱�Ƃ��ł��A�g�k�̃l�N���|���X�ŃA�i�L���A�܂��̓����X�ɉ���Ƃ��ł��܂��B
	 */
	public static final SystemMessageId AVARICE_DESCRIPTION;
	
	/**
	 * ID: 1179<br>
	 * Message: ���̕�������L���錈�����́A����L�����Ԓ��A�[���̕���ɂ���ĊJ�������ʂȃ_���W�����ɓ��ꂷ�邱�Ƃ��ł��A���ɂ���i�Ղ���A�_���W�����ւ̃e���|�[�g �T�[�r�X���󂯂邱�Ƃ��ł��A�_���W�������ɏo������}�����̒b�艮�ɉ���Ƃ��ł��܂��B�[���������`�����(�ŖS�����Ԏ�)�����Ɍ���A���ґ�(�s�ґ�)�ɗL�v��(�L�Q��)���@���r�����܂��B
	 */
	public static final SystemMessageId GNOSIS_DESCRIPTION;
	
	/**
	 * ID: 1180<br>
	 * Message: ����L�����Ԓ��A�������̍ő�CP�ʂ���ю��b��/���A��ǃA�b�v �O���[�h��p/���A��Ǌ�{�h���/��̐ŗ��̌��E�l�����̕�������L�������������ɗL���Ȃ悤�ɕύX����܂��B�܂��A�U��핺��̎g�p����������܂��B�����̊v���R�����̕�������L����ƁA�U���̍ۂɏ鏊�L������������鑤�ɎQ���ł���悤�ɂȂ�܂��B
	 */
	public static final SystemMessageId STRIFE_DESCRIPTION;
	
	/**
	 * ID: 1181<br>
	 * Message: �{���Ƀ^�C�g�����폜���܂����B
	 */
	public static final SystemMessageId CHANGE_TITLE_CONFIRM;
	
	/**
	 * ID: 1182<br>
	 * Message: �{���ɍ폜���܂����B
	 */
	public static final SystemMessageId CREST_DELETE_CONFIRM;
	
	/**
	 * ID: 1183<br>
	 * Message: ������Ԃł��B
	 */
	public static final SystemMessageId INITIAL_PERIOD;
	
	/**
	 * ID: 1184<br>
	 * Message: �T�[�o�[�����W�v���Ԃł��B
	 */
	public static final SystemMessageId RESULTS_PERIOD;
	
	/**
	 * ID: 1185<br>
	 * Message: ����ɍ폜����܂��B
	 */
	public static final SystemMessageId DAYS_LEFT_UNTIL_DELETION;
	
	/**
	 * ID: 1186<br>
	 * Message: �V�K�A�J�E���g���쐬���邽�߂ɂ́A���l�[�W��II �����T�C�g�ihttp://lineage2.plaync.jp/�j�ŉ���o�^������K�v������܂��B
	 */
	public static final SystemMessageId TO_CREATE_ACCOUNT_VISIT_WEBSITE;
	
	/**
	 * ID: 1187<br>
	 * Message: �A�J�E���g�𕴎������ꍇ�A���l�[�W��II �����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g���炨�₢���킹���������B
	 */
	public static final SystemMessageId ACCOUNT_INFORMATION_FORGOTTON_VISIT_WEBSITE;
	
	/**
	 * ID: 1188<br>
	 * Message: ����ȏ㐄�E���󂯂��Ȃ���Ԃł��B
	 */
	public static final SystemMessageId YOUR_TARGET_NO_LONGER_RECEIVE_A_RECOMMENDATION;
	
	/**
	 * ID: 1189<br>
	 * Message: �U�鑤���Վ����������т܂����B�ŏ��̏���֎��ɉ�������܂��B
	 */
	public static final SystemMessageId TEMPORARY_ALLIANCE;
	
	/**
	 * ID: 1189<br>
	 * Message: �U�鑤���Վ����������т܂����B�ŏ��̏���֎��ɉ�������܂��B
	 */
	public static final SystemMessageId TEMPORARY_ALLIANCE_DISSOLVED;
	
	/**
	 * ID: 1191<br>
	 * Message: �O���[�f�B���`���̒���D����10����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_10_MINUTES;
	
	/**
	 * ID: 1192<br>
	 * Message: �O���[�f�B���`���̒���D����5����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_5_MINUTES;
	
	/**
	 * ID: 1193<br>
	 * Message: �O���[�f�B���`���̒���D����1����ɘb���铇�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_1_MINUTE;
	
	/**
	 * ID: 1194<br>
	 * Message: �b���͕���L�����ԓ��ɁA�U���J�n�O�܂Ŕz�u���邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId MERC_CAN_BE_ASSIGNED;
	
	/**
	 * ID: 1195<br>
	 * Message: ���̗b���͐헐�̕���ɂ��z�u�ł��܂���B
	 */
	public static final SystemMessageId MERC_CANT_BE_ASSIGNED_USING_STRIFE;
	
	/**
	 * ID: 1196<br>
	 * Message: �C���ō��i�K�ɒB�������߁A����ȏ�C���W�߂邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId FORCE_MAXIMUM;
	
	/**
	 * ID: 1197<br>
	 * Message: �����b�̔�p��$s1$s2�����Ղ���܂����B
	 */
	public static final SystemMessageId SUMMONING_SERVITOR_COSTS_S2_S1;
	
	/**
	 * ID: 1198<br>
	 * Message: �N���X�^���C�Y���܂����B
	 */
	public static final SystemMessageId CRYSTALLIZATION_SUCCESSFUL;
	
	/**
	 * ID: 1199<br>
	 * Message: =======<������̑���>=======
	 */
	public static final SystemMessageId CLAN_WAR_HEADER;
	
	/**
	 * ID: 1200<br>
	 * Message: =$s1($s2����)
	 */
	public static final SystemMessageId S1_S2_ALLIANCE;
	
	/**
	 * ID: 1201<br>
	 * Message: ���f����N�G�X�g��I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_QUEST_TO_ABOR;
	
	/**
	 * ID: 1202<br>
	 * Message: =$s1(�����Ȃ�)
	 */
	public static final SystemMessageId S1_NO_ALLI_EXISTS;
	
	/**
	 * ID: 1203<br>
	 * Message: �푈���ł͂���܂���B
	 */
	public static final SystemMessageId NO_WAR_IN_PROGRESS;
	
	/**
	 * ID: 1204<br>
	 * Message: �X�N���[���V���b�g��ۑ����܂����B($s1$s2x$s3)
	 */
	public static final SystemMessageId SCREENSHOT;
	
	/**
	 * ID: 1205<br>
	 * Message: ��M�g���C�������ς��ɂȂ�܂����B��M�g���C��100�܂ŕۑ��ł��܂��B
	 */
	public static final SystemMessageId MAILBOX_FULL;
	
	/**
	 * ID: 1206<br>
	 * Message: �����������ς��ɂȂ�܂����B������100�܂œo�^�ł��܂��B
	 */
	public static final SystemMessageId MEMOBOX_FULL;
	
	/**
	 * ID: 1207<br>
	 * Message: ���e����͂��Ă��������B
	 */
	public static final SystemMessageId MAKE_AN_ENTRY;
	
	/**
	 * ID: 1208<br>
	 * Message: $c1�����S���A$s2$s3�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId C1_DIED_DROPPED_S3_S2;
	
	/**
	 * ID: 1209<br>
	 * Message: ���������A���߂łƂ��������܂��B
	 */
	public static final SystemMessageId RAID_WAS_SUCCESSFUL;
	
	
	/**
	 * ID: 1210<br>
	 * Message: �Z�u�� �T�C���F�������Ԃ��n�܂�܂����B�t���̎i�Ղ������̎i�Ղ�K�˂邱�ƂŎQ���ł��܂��B
	 */
	public static final SystemMessageId QUEST_EVENT_PERIOD_BEGUN;
	
	/**
	 * ID: 1211<br>
	 * Message: �Z�u�� �T�C���F�������Ԃ��I�����܂����B���̋������Ԃ�1�T�Ԍ�Ɏn�܂�܂��B
	 */
	public static final SystemMessageId QUEST_EVENT_PERIOD_ENDED;
	
	/**
	 * ID: 1212<br>
	 * Message: �Z�u�� �T�C���F�t���̌N�傽�����×~�̕�����l�����܂����B
	 */
	public static final SystemMessageId DAWN_OBTAINED_AVARICE;
	
	/**
	 * ID: 1213<br>
	 * Message: �Z�u�� �T�C���F�t���̌N�傽�����[���̕�����l�����܂����B
	 */
	public static final SystemMessageId DAWN_OBTAINED_GNOSIS;
	
	/**
	 * ID: 1214<br>
	 * Message: �Z�u�� �T�C���F�t���̌N�傽�����헐�̕�����l�����܂����B
	 */
	public static final SystemMessageId DAWN_OBTAINED_STRIFE;
	
	/**
	 * ID: 1215<br>
	 * Message: �Z�u�� �T�C���F�����̊v���R���×~�̕�����l�����܂����B
	 */
	public static final SystemMessageId DUSK_OBTAINED_AVARICE;
	
	/**
	 * ID: 1216<br>
	 * Message: �Z�u�� �T�C���F�����̊v���R���[���̕�����l�����܂����B
	 */
	public static final SystemMessageId DUSK_OBTAINED_GNOSIS;
	
	/**
	 * ID: 1217<br>
	 * Message: �Z�u�� �T�C���F�����̊v���R���헐�̕�����l�����܂����B
	 */
	public static final SystemMessageId DUSK_OBTAINED_STRIFE;
	
	/**
	 * ID: 1218<br>
	 * Message: �Z�u�� �T�C���F����L�����Ԃ��n�܂�܂��B
	 */
	public static final SystemMessageId SEAL_VALIDATION_PERIOD_BEGUN;
	
	/**
	 * ID: 1219<br>
	 * Message: �Z�u�� �T�C���F����L�����Ԃ��I�����܂����B
	 */
	public static final SystemMessageId SEAL_VALIDATION_PERIOD_ENDED;
	
	/**
	 * ID: 1220<br>
	 * Message: �{���ɏ������܂����B
	 */
	public static final SystemMessageId SUMMON_CONFIRM;
	
	/**
	 * ID: 1221<br>
	 * Message: �{���ɋA�҂����܂����B
	 */
	public static final SystemMessageId RETURN_CONFIRM;
	
	/**
	 * ID: 1222<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�T�|�[�g���k��)
	 */
	public static final SystemMessageId LOC_GM_CONSULATION_SERVICE_S1_S2_S3;
	
	/**
	 * ID: 1223<br>
	 * Message: 5����ɃM�����`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId DEPART_FOR_TALKING_5_MINUTES;
	
	/**
	 * ID: 1224<br>
	 * Message: 1����ɃM�����`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId DEPART_FOR_TALKING_1_MINUTE;
	
	/**
	 * ID: 1225<br>
	 * Message: �Ԃ��Ȃ��M�����`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId DEPART_FOR_TALKING;
	
	/**
	 * ID: 1226<br>
	 * Message: �M�����`����b���铇�֏o�����܂��B
	 */
	public static final SystemMessageId LEAVING_FOR_TALKING;
	
	/**
	 * ID: 1227<br>
	 * Message: $s1�̖��ǂ̃��[��������܂��B
	 */
	public static final SystemMessageId S1_UNREAD_MESSAGES;
	
	/**
	 * ID: 1228<br>
	 * Message: $c1�ɎՒf���ꂽ���߁A���[��������܂���B
	 */
	public static final SystemMessageId C1_BLOCKED_YOU_CANNOT_MAIL;
	
	/**
	 * ID: 1229<br>
	 * Message: ���[��������ȏ㑗�M�ł��܂���B�A�J�E���g������A�����10�ʂ܂ő��M�ł��܂��B
	 */
	public static final SystemMessageId NO_MORE_MESSAGES_TODAY;
	
	/**
	 * ID: 1230<br>
	 * Message: ���[���̑��M�͍ő�5�l�܂ŉ\�ł��B
	 */
	public static final SystemMessageId ONLY_FIVE_RECIPIENTS;
	
	/**
	 * ID: 1231<br>
	 * Message: ���[���𑗐M���܂����B
	 */
	public static final SystemMessageId SENT_MAIL;
	
	/**
	 * ID: 1232<br>
	 * Message: ���[���̑��M�Ɏ��s���܂����B
	 */
	public static final SystemMessageId MESSAGE_NOT_SENT;
	
	/**
	 * ID: 1233<br>
	 * Message: ���[�����͂��܂����B
	 */
	public static final SystemMessageId NEW_MAIL;
	
	/**
	 * ID: 1234<br>
	 * Message: ���[�����������g���C�ɕۑ����܂����B
	 */
	public static final SystemMessageId MAIL_STORED_IN_MAILBOX;
	
	/**
	 * ID: 1235<br>
	 * Message: �F�l�����ׂč폜���܂����B
	 */
	public static final SystemMessageId ALL_FRIENDS_DELETE_CONFIRM;
	
	/**
	 * ID: 1236<br>
	 * Message: �Z�L�����e�B �J�[�h�ԍ�����͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_SECURITY_CARD_NUMBER;
	
	/**
	 * ID: 1237<br>
	 * Message: $s1�Ԃ̃J�[�h�ԍ�����͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_CARD_NUMBER_FOR_S1;
	
	/**
	 * ID: 1238<br>
	 * Message: �������g���C�������ς��ɂȂ������߁A����ȏ�ۑ��ł��܂���B10�܂ŕۑ��ł��܂��B
	 */
	public static final SystemMessageId TEMP_MAILBOX_FULL;
	
	/**
	 * ID: 1239<br>
	 * Message: �L�[�Z�[�t ���W���[���̃��[�f�B���O�Ɏ��s���܂����B�ēx�s���ꍇ�́A�Q�[���I����Ď��s���Ă��������B
	 */
	public static final SystemMessageId KEYBOARD_MODULE_FAILED_LOAD;
	
	/**
	 * ID: 1240<br>
	 * Message: �Z�u�� �T�C���F�����̊v���R���������܂����B
	 */
	public static final SystemMessageId DUSK_WON;
	
	/**
	 * ID: 1241<br>
	 * Message: �Z�u�� �T�C���F�t���̌N�傽�����������܂����B
	 */
	public static final SystemMessageId DAWN_WON;
	
	/**
	 * ID: 1242<br>
	 * Message: 18�˔F�؂��󂯂Ă��Ȃ����[�U�[�l�́A22�����痂��6���܂Ń��O�C���ł��܂���B
	 */
	public static final SystemMessageId NOT_VERIFIED_AGE_NO_LOGIN;
	
	/**
	 * ID: 1243<br>
	 * Message: ���̃Z�L�����e�B �J�[�h�ԍ��͗L���ł͂���܂���B
	 */
	public static final SystemMessageId SECURITY_CARD_NUMBER_INVALID;
	
	/**
	 * ID: 1244<br>
	 * Message: 18�˔F�؂��󂯂Ă��Ȃ����[�U�[�l�́A22�����痂��6���܂ł̊ԁA�ڑ����ł��Ȃ����߃Q�[�����I������܂��B
	 */
	public static final SystemMessageId NOT_VERIFIED_AGE_LOG_OFF;
	
	/**
	 * ID: 1245<br>
	 * Message: �ڑ��I��$s1���O�ł��B
	 */
	public static final SystemMessageId LOGOUT_IN_S1_MINUTES;
	
	/**
	 * ID: 1246<br>
	 * Message: $c1�����S���A$s2�A�f�i�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId C1_DIED_DROPPED_S2_ADENA;
	
	/**
	 * ID: 1247<br>
	 * Message: �������Ԃ��߂������߁A���̂ɊY���̃X�L�����g�p�ł��܂���B
	 */
	public static final SystemMessageId CORPSE_TOO_OLD_SKILL_NOT_USED;
	
	/**
	 * ID: 1248<br>
	 * Message: �󕠂ɂȂ�A�����Ԃ���������܂����B
	 */
	public static final SystemMessageId OUT_OF_FEED_MOUNT_CANCELED;
	
	/**
	 * ID: 1249<br>
	 * Message: ���C�o�[���ɏ�邽�߂ɂ́A�X�g���C�_�[�ɏ������ԂłȂ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId YOU_MAY_ONLY_RIDE_WYVERN_WHILE_RIDING_STRIDER;
	
	/**
	 * ID: 1250<br>
	 * Message: �{���ɍ~�����܂����B������̍~�����A�L�����N�^�[1��̎��S�ɒl����o���l�y�i���e�B������܂��B
	 */
	public static final SystemMessageId SURRENDER_ALLY_WAR_CONFIRM;
	
	/**
	 * ID: 1251<br>
	 * Message: �{���ɒǕ����܂����B�����Ǖ����s�Ȃ����ꍇ�A1���̊ԁA���̌����𓯖��ɉ��������邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId DISMISS_ALLY_CONFIRM;
	
	/**
	 * ID: 1252<br>
	 * Message: �{���ɍ~�����܂����B1��̎��S�ɒl����o���l�y�i���e�B������܂��B
	 */
	public static final SystemMessageId SURRENDER_CONFIRM1;
	
	/**
	 * ID: 1253<br>
	 * Message: �{���Ɍl�~�����܂����B1��̎��S�ɒl����o���l�y�i���e�B������A������ɎQ���ł��Ȃ��Ȃ�܂��B
	 */
	public static final SystemMessageId SURRENDER_CONFIRM2;
	
	/**
	 * ID: 1254<br>
	 * Message: �]���ɉ����Ē����A���肪�Ƃ��������܂����B
	 */
	public static final SystemMessageId THANKS_FOR_FEEDBACK;
	
	/**
	 * ID: 1255<br>
	 * Message: �T�|�[�g���J�n����܂����B
	 */
	public static final SystemMessageId GM_CONSULTATION_BEGUN;
	
	/**
	 * ID: 1256<br>
	 * Message: �R�}���h�̌��ɖ��O�������Ă��������B
	 */
	public static final SystemMessageId PLEASE_WRITE_NAME_AFTER_COMMAND;
	
	/**
	 * ID: 1257<br>
	 * Message: �y�b�g�Ə����b�̓���X�L���̓}�N���ɓo�^�ł��܂���B
	 */
	public static final SystemMessageId PET_SKILL_NOT_AS_MACRO;
	
	/**
	 * ID: 1258<br>
	 * Message: $s1���N���X�^���C�Y���܂����B
	 */
	public static final SystemMessageId S1_CRYSTALLIZED;
	
	/**
	 * ID: 1259<br>
	 * Message: =======<������̑���>=======
	 */
	public static final SystemMessageId ALLIANCE_TARGET_HEADER;
	
	/**
	 * ID: 1260<br>
	 * Message: �Z�u�� �T�C���F���̋����̏������ł��B
	 */
	public static final SystemMessageId PREPARATIONS_PERIOD_BEGUN;
	
	/**
	 * ID: 1261<br>
	 * Message: �Z�u�� �T�C���F�������Ԓ��ł��B�t���̎i�Ղ������̎i�Ղ�K�˂邱�ƂŎQ���ł��܂��B
	 */
	public static final SystemMessageId COMPETITION_PERIOD_BEGUN;
	
	/**
	 * ID: 1262<br>
	 * Message: �Z�u�� �T�C���F�������Ԃ��I�����A���ʂ��W�v���Ă��܂��B
	 */
	public static final SystemMessageId RESULTS_PERIOD_BEGUN;
	
	/**
	 * ID: 1263<br>
	 * Message: �Z�u�� �T�C���F����L�����Ԓ��ł��B�������Ԃ͎��̌��j���ɊJ�n����܂��B
	 */
	public static final SystemMessageId VALIDATION_PERIOD_BEGUN;
	
	/**
	 * ID: 1264<br>
	 * Message: ���̋z���Ɏ��s���܂����B���݂̃\�E�� �X�g�[���ł͂��̍��͋z���ł��܂���B
	 */
	public static final SystemMessageId STONE_CANNOT_ABSORB;
	
	/**
	 * ID: 1265<br>
	 * Message: �\�E�� �X�g�[���������Ă��Ȃ��̂ō����z���ł��܂���B
	 */
	public static final SystemMessageId CANT_ABSORB_WITHOUT_STONE;
	
	/**
	 * ID: 1266<br>
	 * Message: �g���[�h���I�����܂����B
	 */
	public static final SystemMessageId EXCHANGE_HAS_ENDED;
	
	/**
	 * ID: 1267<br>
	 * Message: �v���x��$s1�オ��܂����B
	 */
	public static final SystemMessageId CONTRIB_SCORE_INCREASED_S1;
	
	/**
	 * ID: 1268<br>
	 * Message: $s1�N���X���T�u �N���X�ɒǉ����܂����B
	 */
	public static final SystemMessageId ADD_SUBCLASS_CONFIRM;
	
	/**
	 * ID: 1269<br>
	 * Message: �V�����T�u �N���X��ǉ����܂����B
	 */
	public static final SystemMessageId ADD_NEW_SUBCLASS;
	
	/**
	 * ID: 1270<br>
	 * Message: �T�u �N���X�Ԃ̕ύX���������܂����B
	 */
	public static final SystemMessageId SUBCLASS_TRANSFER_COMPLETED;
	
	/**
	 * ID: 1271<br>
	 * Message: �{���ɎQ�����܂����B���̕���L�����Ԃ܂��t���̌N�及���ɂȂ�܂��B
	 */
	public static final SystemMessageId DAWN_CONFIRM;
	
	/**
	 * ID: 1271<br>
	 * Message: �{���ɎQ�����܂����B���̕���L�����Ԃ܂��t���̌N�及���ɂȂ�܂��B
	 */
	public static final SystemMessageId DUSK_CONFIRM;
	
	/**
	 * ID: 1273<br>
	 * Message: �t���̌N�及���ŃZ�u�� �T�C���ɎQ�����܂����B
	 */
	public static final SystemMessageId SEVENSIGNS_PARTECIPATION_DAWN;
	
	/**
	 * ID: 1274<br>
	 * Message: �����̊v���R�����ŃZ�u�� �T�C���ɎQ�����܂����B
	 */
	public static final SystemMessageId SEVENSIGNS_PARTECIPATION_DUSK;
	
	/**
	 * ID: 1275<br>
	 * Message: ����̋������Ԃ́A�×~�̕���̂��߂ɐ키��I�����܂����B
	 */
	public static final SystemMessageId FIGHT_FOR_AVARICE;
	
	/**
	 * ID: 1276<br>
	 * Message: ����̋������Ԃ́A�[���̕���̂��߂ɐ키��I�����܂����B
	 */
	public static final SystemMessageId FIGHT_FOR_GNOSIS;
	
	/**
	 * ID: 1277<br>
	 * Message: ����̋������Ԃ́A�헐�̕���̂��߂ɐ키��I�����܂����B
	 */
	public static final SystemMessageId FIGHT_FOR_STRIFE;
	
	/**
	 * ID: 1278<br>
	 * Message: NPC�T�[�o�[���쓮���~��Ԃł��B
	 */
	public static final SystemMessageId NPC_SERVER_NOT_OPERATING;
	
	/**
	 * ID: 1279<br>
	 * Message: �v���x�̌��x�𒴉߂��A���s�ł��܂���B
	 */
	public static final SystemMessageId CONTRIB_SCORE_EXCEEDED;
	
	/**
	 * ID: 1280<br>
	 * Message: ���@�̃N���e�B�J�� �q�b�g�I
	 */
	public static final SystemMessageId CRITICAL_HIT_MAGIC;
	
	/**
	 * ID: 1281<br>
	 * Message: �V�[���h�ɂ���z�����h��ɐ������܂����B
	 */
	public static final SystemMessageId YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS;
	
	/**
	 * ID: 1282<br>
	 * Message: �����l��$s1�ɕύX����܂����B
	 */
	public static final SystemMessageId YOUR_KARMA_HAS_BEEN_CHANGED_TO_S1;
	
	/**
	 * ID: 1283<br>
	 * Message: �ŏ��t���[���@�\�ō쓮�����܂��B
	 */
	public static final SystemMessageId MINIMUM_FRAME_ACTIVATED;
	
	/**
	 * ID: 1284<br>
	 * Message: �ŏ��t���[���@�\����������܂����B
	 */
	public static final SystemMessageId MINIMUM_FRAME_DEACTIVATED;
	
	/**
	 * ID: 1285<br>
	 * Message: �݌ɂ��Ȃ����߁A�w���ł��܂���B
	 */
	public static final SystemMessageId NO_INVENTORY_CANNOT_PURCHASE;
	
	/**
	 * ID: 1286<br>
	 * Message: (���̌��j���ߌ�6���܂�)
	 */
	public static final SystemMessageId UNTIL_MONDAY_6PM;
	
	/**
	 * ID: 1287<br>
	 * Message: (�{���ߌ�6���܂�)
	 */
	public static final SystemMessageId UNTIL_TODAY_6PM;
	
	/**
	 * ID: 1288<br>
	 * Message: ���݂̏�Ԃŋ������Ԃ��I������Ɖ��肵�����A$s1���������A����̏��L���͈ȉ��̂悤�ɂȂ�܂��B
	 */
	public static final SystemMessageId S1_WILL_WIN_COMPETITION;
	
	/**
	 * ID: 1289<br>
	 * Message: �ȑO�̎����ŕ�������L���Ă��āA10���ȏ�̐l���������[�����̂�
	 */
	public static final SystemMessageId SEAL_OWNED_10_MORE_VOTED;
	
	/**
	 * ID: 1290<br>
	 * Message: �ȑO�̎����ŕ�������L�ł��Ȃ��������A35���ȏ�̐l���������[�����̂�
	 */
	public static final SystemMessageId SEAL_NOT_OWNED_35_MORE_VOTED;
	
	/**
	 * ID: 1291<br>
	 * Message: �ȑO�̎����ŕ�������L���Ă������A10�������̐l���������[�����̂�
	 */
	public static final SystemMessageId SEAL_OWNED_10_LESS_VOTED;
	
	/**
	 * ID: 1292<br>
	 * Message: �ȑO�̎����ŕ�������L�ł����A35�������̐l���������[�����̂�
	 */
	public static final SystemMessageId SEAL_NOT_OWNED_35_LESS_VOTED;
	
	/**
	 * ID: 1293<br>
	 * Message: ���݂̏�Ԃŋ������Ԃ��I�������ꍇ�A���������ɂȂ�܂��B
	 */
	public static final SystemMessageId COMPETITION_WILL_TIE;
	
	/**
	 * ID: 1294<br>
	 * Message: ���������������̏ꍇ�A����������L�ɂȂ�܂��B
	 */
	public static final SystemMessageId COMPETITION_TIE_SEAL_NOT_AWARDED;
	
	/**
	 * ID: 1295<br>
	 * Message: �X�L���g�p���̓T�u �N���X����邱�Ƃ�A�ύX���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId SUBCLASS_NO_CHANGE_OR_CREATE_WHILE_SKILL_IN_USE;
	
	/**
	 * ID: 1296<br>
	 * Message: �l���X���J�����Ƃ��ł��Ȃ��G���A�ł��B
	 */
	public static final SystemMessageId NO_PRIVATE_STORE_HERE;
	
	/**
	 * ID: 1297<br>
	 * Message: �l�H�[���J�����Ƃ��ł��Ȃ��G���A�ł��B
	 */
	public static final SystemMessageId NO_PRIVATE_WORKSHOP_HERE;
	
	/**
	 * ID: 1298<br>
	 * Message: �����X�^�[ ���[�X�ꂩ��o�܂����B
	 */
	public static final SystemMessageId MONS_EXIT_CONFIRM;
	
	/**
	 * ID: 1299<br>
	 * Message: $c1���W�Q���󂯁A�r�������f����܂����B
	 */
	public static final SystemMessageId C1_CASTING_INTERRUPTED;
	
	/**
	 * ID: 1300<br>
	 * Message: �������L�����Z������܂����B
	 */
	public static final SystemMessageId WEAR_ITEMS_STOPPED;
	
	/**
	 * ID: 1301<br>
	 * Message: �t���̌N�傽���ɉ������Ȃ��Ɨ��p�ł��܂���B
	 */
	public static final SystemMessageId CAN_BE_USED_BY_DAWN;
	
	/**
	 * ID: 1302<br>
	 * Message: �����̊v���R�ɉ������Ȃ��Ɨ��p�ł��܂���B
	 */
	public static final SystemMessageId CAN_BE_USED_BY_DUSK;
	
	/**
	 * ID: 1303<br>
	 * Message: �������Ԃ������p���邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId CAN_BE_USED_DURING_QUEST_EVENT_PERIOD;
	
	/**
	 * ID: 1304<br>
	 * Message: �헐�̕���̉e���ŁA�����̎���o�^�����ׂăL�����Z������܂����B
	 */
	public static final SystemMessageId STRIFE_CANCELED_DEFENSIVE_REGISTRATION;
	
	/**
	 * ID: 1305<br>
	 * Message: �������Ԃ̂݁A����΂�a���邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId SEAL_STONES_ONLY_WHILE_QUEST;
	
	/**
	 * ID: 1306<br>
	 * Message: �������I�����܂����B
	 */
	public static final SystemMessageId NO_LONGER_TRYING_ON;
	
	/**
	 * ID: 1307<br>
	 * Message: ����L�����Ԃɂ������Z�ł��܂���B
	 */
	public static final SystemMessageId SETTLE_ACCOUNT_ONLY_IN_SEAL_VALIDATION;
	
	/**
	 * ID: 1308<br>
	 * Message: ���߂łƂ��������܂��I�I�V�����N���X�ɓ]�E���܂����B
	 */
	public static final SystemMessageId CLASS_TRANSFER;
	
	/**
	 * ID: 1309<br>
	 * Message: ���̋@�\�́A�ŐV�o�[�W������ MSN Messenger �Ŏg�p�ł��܂��B
	 */
	public static final SystemMessageId LATEST_MSN_REQUIRED;
	
	/**
	 * ID: 1310<br>
	 * Message: ���l�[�W��II �� MSN Messenger�̋@�\���g�p����ɂ́A�ŐV�o�[�W������ MSN Messenger ���C���X�g�[������K�v������܂��B
	 */
	public static final SystemMessageId LATEST_MSN_RECOMMENDED;
	
	/**
	 * ID: 1311<br>
	 * Message: �Â��o�[�W������ MSN Messenger �ł̓C���X�^���g ���b�Z�[�W�@�\�������g�p�ł��A�����o�̒ǉ��E�폜����уI�v�V�����@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId MSN_ONLY_BASIC;
	
	/**
	 * ID: 1312<br>
	 * Message: �ŐV�o�[�W������ MSN Messenger ����肷��ɂ́AMSN Messenger �̃T�C�g(http://messenger.live.jp/download)�Ɉړ����Ă��������B
	 */
	public static final SystemMessageId MSN_OBTAINED_FROM;
	
	/**
	 * ID: 1313<br>
	 * Message: $s2 �́A�G�k�E�V�[�E�W���p��(��)���񋟂���T�[�r�X�ɐڑ����Ă��邽�߁A$s1 �Ƃ̃��b�Z�[�W�����́A�T�[�r�X�̉^�c�̂��߂ɕۑ�����܂��B�����Ӓ����Ȃ��ꍇ�́A���̉�b�E�B���h�E����Ă��������B�ڂ����͕��Ѓz�[���y�[�W�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId S1_CHAT_HISTORIES_STORED;
	
	/**
	 * ID: 1314<br>
	 * Message: �ǉ����郁���o�̓d�q���[�� �A�h���X����͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_PASSPORT_FOR_ADDING;
	
	/**
	 * ID: 1315<br>
	 * Message: ���̐l���폜����ƁA�����o ���X�g�ւ̓o�^����������܂��B�������A���̐l���֎~���Ȃ�����A���̐l�͂��Ȃ��̃I�����C����Ԃ��m�F������A���b�Z�[�W�𑗐M���邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId DELETING_A_CONTACT;
	
	/**
	 * ID: 1316<br>
	 * Message: ���̐l���֎~����
	 */
	public static final SystemMessageId CONTACT_WILL_DELETED;
	
	/**
	 * ID: 1317<br>
	 * Message: ���̃����o�̓o�^���������܂���?
	 */
	public static final SystemMessageId CONTACT_DELETE_CONFIRM;
	
	/**
	 * ID: 1318<br>
	 * Message: �֎~�܂��͋֎~���������郁���o��I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_CONTACT_FOR_BLOCK_UNBLOCK;
	
	/**
	 * ID: 1319<br>
	 * Message: �O���[�v��ύX���郁���o��I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_CONTACT_FOR_CHANGE_GROUP;
	
	/**
	 * ID: 1320<br>
	 * Message: �ړ���̃O���[�v��I�����āAOK ���N���b�N���Ă��������B
	 */
	public static final SystemMessageId SELECT_GROUP_PRESS_OK;
	
	/**
	 * ID: 1321<br>
	 * Message: �ǉ�����O���[�v������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_GROUP_NAME;
	
	/**
	 * ID: 1322<br>
	 * Message: �ύX����O���[�v��I�����āA�V�����O���[�v������͂��Ă��������B
	 */
	public static final SystemMessageId SELECT_GROUP_ENTER_NAME;
	
	/**
	 * ID: 1323<br>
	 * Message: �폜����O���[�v��I�����āAOK ���N���b�N���Ă��������B
	 */
	public static final SystemMessageId SELECT_GROUP_TO_DELETE;
	
	/**
	 * ID: 1324<br>
	 * Message: �T�C���C����...
	 */
	public static final SystemMessageId SIGNING_IN;
	
	/**
	 * ID: 1325<br>
	 * Message: ���̏ꏊ�� Messenger �ɃT�C���C���������߁A.NET Messenger Service ����T�C���A�E�g����܂����B
	 */
	public static final SystemMessageId ANOTHER_COMPUTER_LOGOUT;
	
	/**
	 * ID: 1326<br>
	 * Message: $s1 �̔���:
	 */
	public static final SystemMessageId S1_D;
	
	/**
	 * ID: 1327<br>
	 * Message: ���̃��b�Z�[�W�𑗂邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId MESSAGE_NOT_DELIVERED;
	
	/**
	 * ID: 1328<br>
	 * Message: �����̊v���R�����Ȃ̂ŁA�����ł��܂���B
	 */
	public static final SystemMessageId DUSK_NOT_RESURRECTED;
	
	/**
	 * ID: 1329<br>
	 * Message: �l���X����ьl�H�[�͋֎~��Ԃ̂��߁A�J�݂��ł��܂���B
	 */
	public static final SystemMessageId BLOCKED_FROM_USING_STORE;
	
	/**
	 * ID: 1330<br>
	 * Message: $s1���Ԍl���X����ьl�H�[�̊J�݂��֎~����܂����B
	 */
	public static final SystemMessageId NO_STORE_FOR_S1_MINUTES;
	
	/**
	 * ID: 1331<br>
	 * Message: �l���X����ьl�H�[�̊J�݋֎~����������܂����B
	 */
	public static final SystemMessageId NO_LONGER_BLOCKED_USING_STORE;
	
	
	/**
	 * ID: 1332<br>
	 * Message: ����ł����ԂŃA�C�e���͎g���܂���B
	 */
	public static final SystemMessageId NO_ITEMS_AFTER_DEATH;
	
	/**
	 * ID: 1333<br>
	 * Message: ���v���C �t�@�C����ǂݎ��܂���BReplay.ini�t�@�C�����m�F���Ă��������B
	 */
	public static final SystemMessageId REPLAY_INACCESSIBLE;
	
	/**
	 * ID: 1334<br>
	 * Message: �V�����J��������ۑ����܂����B
	 */
	public static final SystemMessageId NEW_CAMERA_STORED;
	
	/**
	 * ID: 1335<br>
	 * Message: �V�����J�������̕ۑ��Ɏ��s���܂����B
	 */
	public static final SystemMessageId CAMERA_STORING_FAILED;
	
	/**
	 * ID: 1336<br>
	 * Message: ���v���C �t�@�C�����������܂����B$s1.$s2 �t�@�C�����m�F���Ă��������B
	 */
	public static final SystemMessageId REPLAY_S1_S2_CORRUPTED;
	
	/**
	 * ID: 1337<br>
	 * Message: ���v���C���I�����܂��B��낵���ł����B
	 */
	public static final SystemMessageId REPLAY_TERMINATE_CONFIRM;
	
	/**
	 * ID: 1338<br>
	 * Message: ��x�Ɉڂ����Ƃ̂ł���ʂ𒴉߂��܂����B
	 */
	public static final SystemMessageId EXCEEDED_MAXIMUM_AMOUNT;
	
	/**
	 * ID: 1339<br>
	 * Message: �}�N���Ɋ��蓖�Ă�ꂽ�V���[�g�J�b�g �L�[��ʂ̃}�N���Ŏg�p���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId MACRO_SHORTCUT_NOT_RUN;
	
	/**
	 * ID: 1340<br>
	 * Message: ���ݎg�p���Ă���N�[�|���ł͐ڑ��ł��Ȃ��T�[�o�[�ł��B
	 */
	public static final SystemMessageId SERVER_NOT_ACCESSED_BY_COUPON;
	
	/**
	 * ID: 1341<br>
	 * Message: ���͂��ꂽ�d�q���[�� �A�h���X�͖����ł��B
	 */
	public static final SystemMessageId INCORRECT_NAME_OR_ADDRESS;
	
	/**
	 * ID: 1342<br>
	 * Message: ���łɃT�C���C�����Ă��܂��B
	 */
	public static final SystemMessageId ALREADY_LOGGED_IN;
	
	/**
	 * ID: 1343<br>
	 * Message: ���͂��ꂽ�T�C���C���������݂��Ȃ����A�p�X���[�h���������Ȃ����߁A�T�C���C���ł��܂���ł����B
	 */
	public static final SystemMessageId INCORRECT_ADDRESS_OR_PASSWORD;
	
	/**
	 * ID: 1344<br>
	 * Message: �T�[�r�X��������Ȃ����߁ANET Messenger Service �ɃT�C���C���ł��܂���ł����B�C���^�[�l�b�g�ɐڑ����Ă��邱�Ƃ��m�F���Ă��������B
	 */
	public static final SystemMessageId NET_LOGIN_FAILED;
	
	/**
	 * ID: 1345<br>
	 * Message: ���̉�b�ɏ��҂���l��I�����āAOK ���N���b�N���܂��B
	 */
	public static final SystemMessageId SELECT_CONTACT_CLICK_OK;
	
	/**
	 * ID: 1346<br>
	 * Message: �����b�Z�[�W����͂��Ă��܂��B
	 */
	public static final SystemMessageId CURRENTLY_ENTERING_CHAT;
	
	/**
	 * ID: 1347<br>
	 * Message: Lineage II Messenger �� �v���������ł��܂���ł����B
	 */
	public static final SystemMessageId MESSENGER_FAILED_CARRYING_OUT_TASK;
	
	/**
	 * ID: 1348<br>
	 * Message: $s1 ����b�ɎQ�����܂����B
	 */
	public static final SystemMessageId S1_ENTERED_CHAT_ROOM;
	
	/**
	 * ID: 1349<br>
	 * Message: $s1 ����b����ސȂ��܂����B
	 */
	public static final SystemMessageId S1_LEFT_CHAT_ROOM;
	
	/**
	 * ID: 1350<br>
	 * Message: ��Ԃ��I�t���C���ɕύX���܂��B���ׂẲ�b�E�B���h�E���I�����܂��B
	 */
	public static final SystemMessageId GOING_OFFLINE;
	
	/**
	 * ID: 1351<br>
	 * Message: �폜���郁���o��I�����āA�폜 ���N���b�N���Ă��������B
	 */
	public static final SystemMessageId SELECT_CONTACT_CLICK_REMOVE;
	
	/**
	 * ID: 1352<br>
	 * Message: $s1 ($s2) �̃����o ���X�g�ɒǉ�����܂����B
	 */
	public static final SystemMessageId ADDED_TO_S1_S2_CONTACT_LIST;
	
	/**
	 * ID: 1353<br>
	 * Message: ���ł��������I�t���C����Ԃɂ��邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId CAN_SET_OPTION_TO_ALWAYS_SHOW_OFFLINE;
	
	/**
	 * ID: 1354<br>
	 * Message: �`���b�g�֎~���̂��߁A��b�ł��܂���B
	 */
	public static final SystemMessageId NO_CHAT_WHILE_BLOCKED;
	
	/**
	 * ID: 1355<br>
	 * Message: ��b�̑���͌��݃`���b�g�֎~�̏�Ԃł��B
	 */
	public static final SystemMessageId CONTACT_CURRENTLY_BLOCKED;
	
	/**
	 * ID: 1356<br>
	 * Message: ��b�̑���͌��ݐڑ����Ă��܂���B
	 */
	public static final SystemMessageId CONTACT_CURRENTLY_OFFLINE;
	
	/**
	 * ID: 1357<br>
	 * Message: ��b�̑���ɂ���ċ֎~����Ă����Ԃł��B
	 */
	public static final SystemMessageId YOU_ARE_BLOCKED;
	
	/**
	 * ID: 1358<br>
	 * Message: �T�C���A�E�g��...
	 */
	public static final SystemMessageId YOU_ARE_LOGGING_OUT;
	
	/**
	 * ID: 1359<br>
	 * Message: $s1 ���T�C���C�����܂����B
	 */
	public static final SystemMessageId S1_LOGGED_IN2;
	
	/**
	 * ID: 1360<br>
	 * Message: $s1 ����̐V�����b�Z�[�W���͂��܂����B
	 */
	public static final SystemMessageId GOT_MESSAGE_FROM_S1;
	
	/**
	 * ID: 1361<br>
	 * Message: �V�X�e�� �g���u���ɂ���� .NET Messenger Service ����T�C���A�E�g����܂����B
	 */
	public static final SystemMessageId LOGGED_OUT_DUE_TO_ERROR;
	
	/**
	 * ID: 1362<br>
	 * Message: �폜���郁���o��I�����Ă��������B�O���[�v���폜����ɂ́A�I�����C����Ԃ̉��ɂ���O�p�`���N���b�N���A�I�v�V���� ���N���b�N���܂��B
	 */
	public static final SystemMessageId SELECT_CONTACT_TO_DELETE;
	
	/**
	 * ID: 1363<br>
	 * Message: ������̐\�����݂����ۂ���܂����B
	 */
	public static final SystemMessageId YOUR_REQUEST_ALLIANCE_WAR_DENIED;
	
	/**
	 * ID: 1364<br>
	 * Message: ������̐\�����݂����ۂ��܂����B
	 */
	public static final SystemMessageId REQUEST_ALLIANCE_WAR_REJECTED;
	
	/**
	 * ID: 1365<br>
	 * Message: $s1������$s2���l�~�����܂����B
	 */
	public static final SystemMessageId S2_OF_S1_SURRENDERED_AS_INDIVIDUAL;
	
	/**
	 * ID: 1366<br>
	 * Message: �폜�ł���̂́A��̃O���[�v�݂̂ł��B���̃O���[�v�̂��ׂẴ����o�𑼂̃O���[�v�Ɉړ����Ă��������B
	 */
	public static final SystemMessageId DELTE_GROUP_INSTRUCTION;
	
	/**
	 * ID: 1367<br>
	 * Message: �֘A�������łȂ��̂ŁA�L�^��ǉ��ł��܂���B
	 */
	public static final SystemMessageId ONLY_GROUP_CAN_ADD_RECORDS;
	
	/**
	 * ID: 1368<br>
	 * Message: �ꏏ�ɒ��p�ł���A�C�e���ł͂���܂���B
	 */
	public static final SystemMessageId YOU_CAN_NOT_TRY_THOSE_ITEMS_ON_AT_THE_SAME_TIME;
	
	/**
	 * ID: 1369<br>
	 * Message: �ݒ�ł���ō����z�𒴉߂��܂����B
	 */
	public static final SystemMessageId EXCEEDED_THE_MAXIMUM;
	
	/**
	 * ID: 1370<br>
	 * Message: $c1�͉^�c�҂Ȃ̂Ń��[���𑗂邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_MAIL_GM_C1;
	
	/**
	 * ID: 1371<br>
	 * Message: �M���͐����Ƀv���C���Ă��Ȃ��Ɣ��f����A$s1���ԓ����Ȃ��Ȃ�[�u���Ƃ��܂����B
	 */
	public static final SystemMessageId GAMEPLAY_RESTRICTION_PENALTY_S1;
	
	/**
	 * ID: 1372<br>
	 * Message: ���ݓ����Ȃ���Ԃł��B������悤�ɂȂ�܂ł̎c�莞�Ԃ͂���$s1���ł��B
	 */
	public static final SystemMessageId PUNISHMENT_CONTINUE_S1_MINUTES;
	
	/**
	 * ID: 1373<br>
	 * Message: $c1�����C�h �{�X�����Ƃ���$s2����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PICKED_UP_S2_FROM_RAIDBOSS;
	
	/**
	 * ID: 1374<br>
	 * Message: $c1�����C�h �{�X�����Ƃ���$s2 $s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PICKED_UP_S3_S2_S_FROM_RAIDBOSS;
	
	/**
	 * ID: 1375<br>
	 * Message: $c1�����C�h �{�X�����Ƃ���$s2�A�f�i����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PICKED_UP_S2_ADENA_FROM_RAIDBOSS;
	
	/**
	 * ID: 1376<br>
	 * Message: $c1�����̐l�����Ƃ���$s2����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PICKED_UP_S2_FROM_ANOTHER_CHARACTER;
	
	/**
	 * ID: 1377<br>
	 * Message: $c1�����̐l�����Ƃ���$s2 $s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PICKED_UP_S3_S2_S_FROM_ANOTHER_CHARACTER;
	
	/**
	 * ID: 1378<br>
	 * Message: $c1�����̐l�����Ƃ���+$s3$s2����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PICKED_UP_S3_S2_FROM_ANOTHER_CHARACTER;
	
	/**
	 * ID: 1379<br>
	 * Message: $c1��$s2�A�f�i����ɓ���܂����B
	 */
	public static final SystemMessageId C1_OBTAINED_S2_ADENA;
	
	/**
	 * ID: 1380<br>
	 * Message: ���ł�$s1�������ł��܂���B
	 */
	public static final SystemMessageId CANT_SUMMON_S1_ON_BATTLEGROUND;
	
	/**
	 * ID: 1381<br>
	 * Message: �p�[�e�B�[ ���[�_�[��$s1��$s2�l�����܂����B
	 */
	public static final SystemMessageId LEADER_OBTAINED_S2_OF_S1;
	
	/**
	 * ID: 1382<br>
	 * Message: �{���ɂ��̕����I������܂����B �N�G�X�g���������邽�߂ɂ́A�I�����������K�������ė��Ȃ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId CHOOSE_WEAPON_CONFIRM;
	
	/**
	 * ID: 1383<br>
	 * Message: �{���ɕς����܂����B
	 */
	public static final SystemMessageId EXCHANGE_CONFIRM;
	
	/**
	 * ID: 1384<br>
	 * Message: $c1���p�[�e�B�[ ���[�_�[�ɂȂ�܂����B
	 */
	public static final SystemMessageId C1_HAS_BECOME_A_PARTY_LEADER;
	
	/**
	 * ID: 1385<br>
	 * Message: �~����Ȃ��G���A�ł��B
	 */
	public static final SystemMessageId NO_DISMOUNT_HERE;
	
	/**
	 * ID: 1386<br>
	 * Message: ��~��Ԃ���������܂����B
	 */
	public static final SystemMessageId NO_LONGER_HELD_IN_PLACE;
	
	/**
	 * ID: 1387<br>
	 * Message: ���p����A�C�e����I�����Ă��������B
	 */
	public static final SystemMessageId SELECT_ITEM_TO_TRY_ON;
	
	/**
	 * ID: 1388<br>
	 * Message: �p�[�e�B�[ ���[������������܂����B
	 */
	public static final SystemMessageId PARTY_ROOM_CREATED;
	
	/**
	 * ID: 1389<br>
	 * Message: �p�[�e�B�[ ���[���̏�񂪏C������܂����B
	 */
	public static final SystemMessageId PARTY_ROOM_REVISED;
	
	/**
	 * ID: 1390<br>
	 * Message: �p�[�e�B�[ ���[���ɓ���ł��܂���B
	 */
	public static final SystemMessageId PARTY_ROOM_FORBIDDEN;
	
	/**
	 * ID: 1391<br>
	 * Message: �p�[�e�B�[ ���[������ޏꂵ�܂����B
	 */
	public static final SystemMessageId PARTY_ROOM_EXITED;
	
	/**
	 * ID: 1392<br>
	 * Message: $c1���p�[�e�B�[ ���[������ޏꂵ�܂����B
	 */
	public static final SystemMessageId C1_LEFT_PARTY_ROOM;
	
	/**
	 * ID: 1393<br>
	 * Message: �p�[�e�B�[ ���[������Ǖ�����܂����B
	 */
	public static final SystemMessageId OUSTED_FROM_PARTY_ROOM;
	
	/**
	 * ID: 1394<br>
	 * Message: $c1���p�[�e�B�[ ���[������Ǖ�����܂����B
	 */
	public static final SystemMessageId C1_KICKED_FROM_PARTY_ROOM;
	
	/**
	 * ID: 1395<br>
	 * Message: �p�[�e�B�[ ���[�����I�����܂����B
	 */
	public static final SystemMessageId PARTY_ROOM_DISBANDED;
	
	/**
	 * ID: 1396<br>
	 * Message: �p�[�e�B�[�ɉ������Ă��Ȃ����A�p�[�e�B�[ ���[�_�[�łȂ��ƁA�p�[�e�B�[ ���[���̃��X�g�����邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANT_VIEW_PARTY_ROOMS;
	
	/**
	 * ID: 1397<br>
	 * Message: �p�[�e�B�[ ���[���̃��[�� ���[�_�[���ύX����܂����B
	 */
	public static final SystemMessageId PARTY_ROOM_LEADER_CHANGED;
	
	/**
	 * ID: 1398<br>
	 * Message: �p�[�e�B�[ �����o�[���W���܂��B
	 */
	public static final SystemMessageId RECRUITING_PARTY_MEMBERS;
	
	/**
	 * ID: 1399<br>
	 * Message: �p�[�e�B�[ ���[�_�[�̂݌������Ϗ����邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId ONLY_A_PARTY_LEADER_CAN_TRANSFER_ONES_RIGHTS_TO_ANOTHER_PLAYER;
	
	/**
	 * ID: 1400<br>
	 * Message: �p�[�e�B�[ ���[�_�[�̌������Ϗ�����Ώۂ�I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_THE_PERSON_TO_WHOM_YOU_WOULD_LIKE_TO_TRANSFER_THE_RIGHTS_OF_A_PARTY_LEADER;
	
	/**
	 * ID: 1401<br>
	 * Message: �������g�Ɍ������Ϗ����邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_TRANSFER_RIGHTS_TO_YOURSELF;
	
	/**
	 * ID: 1402<br>
	 * Message: �p�[�e�B�[ �����o�[�ɂ̂݌����̈Ϗ����\�ł��B
	 */
	public static final SystemMessageId YOU_CAN_TRANSFER_RIGHTS_ONLY_TO_ANOTHER_PARTY_MEMBER;
	
	/**
	 * ID: 1403<br>
	 * Message: �p�[�e�B�[ ���[�_�[�̌����Ϗ��Ɏ��s���܂����B
	 */
	public static final SystemMessageId YOU_HAVE_FAILED_TO_TRANSFER_THE_PARTY_LEADER_RIGHTS;
	
	/**
	 * ID: 1404<br>
	 * Message: �l�H�[�̎�l�����쉿�i��ύX���܂����B�ύX���ꂽ���i��������x�m�F���Č䗘�p���������B
	 */
	public static final SystemMessageId MANUFACTURE_PRICE_HAS_CHANGED;
	
	/**
	 * ID: 1405<br>
	 * Message: $s1��CP���񕜂���܂��B
	 */
	public static final SystemMessageId S1_CP_WILL_BE_RESTORED;
	
	/**
	 * ID: 1406<br>
	 * Message: $c1�ɂ����$s2��CP���񕜂���܂��B
	 */
	public static final SystemMessageId S2_CP_WILL_BE_RESTORED_BY_C1;
	
	/**
	 * ID: 1407<br>
	 * Message: 2�̃A�J�E���g�œ����ɃA�N�Z�X�ł��Ȃ�PC���g�p����Ă��܂��B
	 */
	public static final SystemMessageId NO_LOGIN_WITH_TWO_ACCOUNTS;
	
	/**
	 * ID: 1408<br>
	 * Message: �ۋ����Ԃ̎c���$s1���� $s2���ł��B�\��ς݂̌��ς�$s3���ł��B
	 */
	public static final SystemMessageId PREPAID_LEFT_S1_S2_S3;
	
	/**
	 * ID: 1409<br>
	 * Message: �ۋ����Ԃ����������̂ŗ\��ς݂̌��ς��L���ɂȂ�܂��B�c�莞�Ԃ�$s1���� $s2���ł��B
	 */
	public static final SystemMessageId PREPAID_EXPIRED_S1_S2;
	
	/**
	 * ID: 1410<br>
	 * Message: �ۋ����Ԃ��������܂����B�\��ς݂̌��ς͂���܂���B
	 */
	public static final SystemMessageId PREPAID_EXPIRED;
	
	/**
	 * ID: 1411<br>
	 * Message: �ۋ����ϗ\�񌏐����ύX����܂����B
	 */
	public static final SystemMessageId PREPAID_CHANGED;
	
	/**
	 * ID: 1412<br>
	 * Message: �ۋ����Ԃ͎c��$s1���ł��B
	 */
	public static final SystemMessageId PREPAID_LEFT_S1;
	
	/**
	 * ID: 1413<br>
	 * Message: ����������Ȃ����߁A�p�[�e�B�[ ���[���ɓ���ł��܂���B
	 */
	public static final SystemMessageId CANT_ENTER_PARTY_ROOM;
	
	/**
	 * ID: 1414<br>
	 * Message: �����́A��100�ȏ�A�c5000�����ɂ��Ă��������B
	 */
	public static final SystemMessageId WRONG_GRID_COUNT;
	
	/**
	 * ID: 1415<br>
	 * Message: �R�}���h �t�@�C�����w�肳��Ă��܂���B
	 */
	public static final SystemMessageId COMMAND_FILE_NOT_SENT;
	
	/**
	 * ID: 1416<br>
	 * Message: ��1�`�[���̃��[�_�[���I������Ă��܂���B
	 */
	public static final SystemMessageId TEAM_1_NO_REPRESENTATIVE;
	
	/**
	 * ID: 1417<br>
	 * Message: ��2�`�[���̃��[�_�[���I������Ă��܂���B
	 */
	public static final SystemMessageId TEAM_2_NO_REPRESENTATIVE;
	
	/**
	 * ID: 1418<br>
	 * Message: ��1�`�[���̖��̂��w�肳��Ă��܂���B
	 */
	public static final SystemMessageId TEAM_1_NO_NAME;
	
	/**
	 * ID: 1419<br>
	 * Message: ��2�`�[���̖��̂��w�肳��Ă��܂���B
	 */
	public static final SystemMessageId TEAM_2_NO_NAME;
	
	/**
	 * ID: 1420<br>
	 * Message: ��1�`�[���Ƒ�2�`�[���̖��̂������ł��B
	 */
	public static final SystemMessageId TEAM_NAME_IDENTICAL;
	
	/**
	 * ID: 1421<br>
	 * Message: ���Z�ݒ�t�@�C�����w�肳��Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE1;
	
	/**
	 * ID: 1422<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FBuffCnt����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE2;
	
	/**
	 * ID: 1423<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FBuffID$s1����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE3;
	
	/**
	 * ID: 1424<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FBuffLv$s1����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE4;
	
	/**
	 * ID: 1425<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FDefaultAllow����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE5;
	
	/**
	 * ID: 1426<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FExpSkillCnt����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE6;
	
	/**
	 * ID: 1427<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FExpSkillID$s1����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE7;
	
	/**
	 * ID: 1428<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FExpItemCnt����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE8;
	
	/**
	 * ID: 1429<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FExpItemID$s1����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE9;
	
	/**
	 * ID: 1430<br>
	 * Message: ���Z�ݒ�t�@�C�� �G���[�FTeleportDelay����������Ă��܂���B
	 */
	public static final SystemMessageId RACE_SETUP_FILE10;
	
	/**
	 * ID: 1431<br>
	 * Message: ���Z���ꎞ���f���܂��B
	 */
	public static final SystemMessageId RACE_STOPPED_TEMPORARILY;
	
	/**
	 * ID: 1432<br>
	 * Message: ���肪�Ή���Ԃł��B
	 */
	public static final SystemMessageId OPPONENT_PETRIFIED;
	
	/**
	 * ID: 1433<br>
	 * Message: $s1 �̎g�p�������ɂ��܂��B
	 */
	public static final SystemMessageId USE_OF_S1_WILL_BE_AUTO;
	
	/**
	 * ID: 1434<br>
	 * Message: $s1 �̎����g�p���������܂��B
	 */
	public static final SystemMessageId AUTO_USE_OF_S1_CANCELLED;
	
	/**
	 * ID: 1435<br>
	 * Message: $s1���s�����Ă���̂Ŏ����g�p����������܂����B
	 */
	public static final SystemMessageId AUTO_USE_CANCELLED_LACK_OF_S1;
	
	/**
	 * ID: 1436<br>
	 * Message: $s1���s�����Ă���̂Ŏ����g�p���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_AUTO_USE_LACK_OF_S1;
	
	/**
	 * ID: 1437<br>
	 * Message: �T�C�R�����g���Ȃ��Ȃ�܂����B���ɂ����ʂ̏��X�ł̍w�����ł��܂���B�����A���ɂ����ʂ̏��X�ł̔̔��͂ł��܂��B
	 */
	public static final SystemMessageId DICE_NO_LONGER_ALLOWED;
	
	/**
	 * ID: 1438<br>
	 * Message: �G���`�����g�\�ȃX�L��������܂���B
	 */
	public static final SystemMessageId THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT;
	
	/**
	 * ID: 1439<br>
	 * Message: �X�L���̃G���`�����g�ɕK�v�ȃA�C�e�����s�����Ă��܂��B
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_THAT_SKILL;
	
	/**
	 * ID: 1440<br>
	 * Message: �X�L���̃G���`�����g�ɐ������܂����B�X�L���̃G���`�����g��$s1��������܂����B
	 */
	public static final SystemMessageId YOU_HAVE_SUCCEEDED_IN_ENCHANTING_THE_SKILL_S1;
	
	/**
	 * ID: 1441<br>
	 * Message: �X�L���̃G���`�����g�Ɏ��s���܂����B�X�L���̃G���`�����g������������܂����B
	 */
	public static final SystemMessageId YOU_HAVE_FAILED_TO_ENCHANT_THE_SKILL_S1;
	
	/**
	 * ID: 1443<br>
	 * Message: �X�L���̃G���`�����g�ɕK�v��SP���s�����Ă��܂��B
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ENOUGH_SP_TO_ENCHANT_THAT_SKILL;
	
	/**
	 * ID: 1444<br>
	 * Message: �X�L���̃G���`�����g�ɕK�v�Ȍo���l���s�����Ă��܂��B
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ENOUGH_EXP_TO_ENCHANT_THAT_SKILL;
	
	/**
	 * ID: 1445<br>
	 * Message: �ȑO�̃T�u�N���X�͏��ł��A�V���ȃT�u�N���X�̃��x��40����X�^�[�g���܂��B��낵���ł����B
	 */
	public static final SystemMessageId REPLACE_SUBCLASS_CONFIRM;
	
	/**
	 * ID: 1446<br>
	 * Message: $s1 �� $s2 �s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_FROM_S1_TO_S2_DELAYED;
	
	/**
	 * ID: 1447<br>
	 * Message: �H�����̑ҋ@���ɂ͑��̃X�L�����g�p�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DO_WHILE_FISHING_1;
	
	/**
	 * ID: 1448<br>
	 * Message: �t�B�b�V���O��p�X�L���̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId ONLY_FISHING_SKILLS_NOW;
	
	/**
	 * ID: 1449<br>
	 * Message: �H�����܂����B
	 */
	public static final SystemMessageId GOT_A_BITE;
	
	/**
	 * ID: 1450<br>
	 * Message: ���Ԑ؂�Ŋl���𓦂��܂����B
	 */
	public static final SystemMessageId FISH_SPIT_THE_HOOK;
	
	/**
	 * ID: 1451<br>
	 * Message: �l���𓦂��܂����B
	 */
	public static final SystemMessageId BAIT_STOLEN_BY_FISH;
	
	/**
	 * ID: 1452<br>
	 * Message: �l�����G�T��������ē����܂����B
	 */
	public static final SystemMessageId BAIT_LOST_FISH_GOT_AWAY;
	
	/**
	 * ID: 1453<br>
	 * Message: �ނ�Ƃ𑕔����Ă��܂���B
	 */
	public static final SystemMessageId FISHING_POLE_NOT_EQUIPPED;
	
	/**
	 * ID: 1454<br>
	 * Message: �G�T�����Ă��܂���B
	 */
	public static final SystemMessageId BAIT_ON_HOOK_BEFORE_FISHING;
	
	/**
	 * ID: 1455<br>
	 * Message: �����ł̓t�B�b�V���O�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_FISH_UNDER_WATER;
	
	/**
	 * ID: 1456<br>
	 * Message: ���悵����Ԃł̓t�B�b�V���O�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_FISH_ON_BOAT;
	
	/**
	 * ID: 1457<br>
	 * Message: �ނ��ł͂���܂���B
	 */
	public static final SystemMessageId CANNOT_FISH_HERE;
	
	/**
	 * ID: 1458<br>
	 * Message: �t�B�b�V���O���������܂��B
	 */
	public static final SystemMessageId FISHING_ATTEMPT_CANCELLED;
	
	/**
	 * ID: 1459<br>
	 * Message: �G�T���s�����Ă��܂��B
	 */
	public static final SystemMessageId NOT_ENOUGH_BAIT;
	
	/**
	 * ID: 1460<br>
	 * Message: �t�B�b�V���O���I�����܂��B
	 */
	public static final SystemMessageId REEL_LINE_AND_STOP_FISHING;
	
	/**
	 * ID: 1461<br>
	 * Message: �t�B�b�V���O���J�n���܂��B
	 */
	public static final SystemMessageId CAST_LINE_AND_START_FISHING;
	
	/**
	 * ID: 1462<br>
	 * Message: �|���s���O�X�L���͊l�����H��������Ԃł̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId CAN_USE_PUMPING_ONLY_WHILE_FISHING;
	
	/**
	 * ID: 1463<br>
	 * Message: ���[�����O�X�L���͊l�����H��������Ԃł̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId CAN_USE_REELING_ONLY_WHILE_FISHING;
	
	/**
	 * ID: 1464<br>
	 * Message: �l������R���܂����B
	 */
	public static final SystemMessageId FISH_RESISTED_ATTEMPT_TO_BRING_IT_IN;
	
	/**
	 * ID: 1465<br>
	 * Message: �|���s���O�����A�_���[�W�F $s1
	 */
	public static final SystemMessageId PUMPING_SUCCESFUL_S1_DAMAGE;
	
	/**
	 * ID: 1466<br>
	 * Message: �|���s���O���s�A�_���[�W�F $s1
	 */
	public static final SystemMessageId FISH_RESISTED_PUMPING_S1_HP_REGAINED;
	
	/**
	 * ID: 1467<br>
	 * Message: ���[�����O�����A�_���[�W�F$s1
	 */
	public static final SystemMessageId REELING_SUCCESFUL_S1_DAMAGE;
	
	/**
	 * ID: 1468<br>
	 * Message: ���[�����O���s�A�_���[�W�F$s1
	 */
	public static final SystemMessageId FISH_RESISTED_REELING_S1_HP_REGAINED;
	
	/**
	 * ID: 1469<br>
	 * Message: �l����ނ�グ�܂����B
	 */
	public static final SystemMessageId YOU_CAUGHT_SOMETHING;
	
	/**
	 * ID: 1470<br>
	 * Message: �t�B�b�V���O���ɂ̓A�C�e���̑����A�����A�j�ӁA�h���b�v�͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DO_WHILE_FISHING_2;
	
	/**
	 * ID: 1471<br>
	 * Message: �t�B�b�V���O���ɂ͑��̍s������邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DO_WHILE_FISHING_3;
	
	/**
	 * ID: 1472<br>
	 * Message: �ނ�Ƃł͍U���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_ATTACK_WITH_FISHING_POLE;
	
	/**
	 * ID: 1473<br>
	 * Message: $s1���s�����Ă��܂��B
	 */
	public static final SystemMessageId S1_NOT_SUFFICIENT;
	
	/**
	 * ID: 1474<br>
	 * Message: $s1���g�p�ł��܂���B
	 */
	public static final SystemMessageId S1_NOT_AVAILABLE;
	
	/**
	 * ID: 1475<br>
	 * Message: �y�b�g��$s1�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId PET_DROPPED_S1;
	
	/**
	 * ID: 1476<br>
	 * Message: �y�b�g��+$s1$s2�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId PET_DROPPED_S1_S2;
	
	/**
	 * ID: 1477<br>
	 * Message: �y�b�g��$s1 $s2�𗎂Ƃ��܂����B
	 */
	public static final SystemMessageId PET_DROPPED_S2_S1_S;
	
	/**
	 * ID: 1478<br>
	 * Message: 64x64�s�N�Z����256�F��BMP�t�@�C���̂ݓo�^�ł��܂��B
	 */
	public static final SystemMessageId ONLY_64_PIXEL_256_COLOR_BMP;
	
	/**
	 * ID: 1479<br>
	 * Message: �ނ�Ƃ̃O���[�h�ɍ����t�B�b�V���O �V���b�g�ł͂���܂���B
	 */
	public static final SystemMessageId WRONG_FISHINGSHOT_GRADE;
	
	/**
	 * ID: 1480<br>
	 * Message: �I�����s�A�[�h�̎Q���\�����݂��������܂����B
	 */
	public static final SystemMessageId OLYMPIAD_REMOVE_CONFIRM;
	
	/**
	 * ID: 1481<br>
	 * Message: �N���X�������l���Z��I�����܂����B�Q�����܂����B
	 */
	public static final SystemMessageId OLYMPIAD_NON_CLASS_CONFIRM;
	
	/**
	 * ID: 1482<br>
	 * Message: �N���X�ʎ�����I�����܂����B�Q�����܂����B
	 */
	public static final SystemMessageId OLYMPIAD_CLASS_CONFIRM;
	
	/**
	 * ID: 1483<br>
	 * Message: ���p�Y�ɂȂ�܂����B
	 */
	public static final SystemMessageId HERO_CONFIRM;
	
	/**
	 * ID: 1484<br>
	 * Message: �I�������p�Y�̕�����g�p���܂����B�J�}�G���͎g�p�ł��܂���B
	 */
	public static final SystemMessageId HERO_WEAPON_CONFIRM;
	
	/**
	 * ID: 1485<br>
	 * Message: �b���铇�� �O���[�f�B���`�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_TALKING_GLUDIN_DELAYED;
	
	/**
	 * ID: 1486<br>
	 * Message: �O���[�f�B���`�� �b���铇�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_GLUDIN_TALKING_DELAYED;
	
	/**
	 * ID: 1487<br>
	 * Message: �M�����`�� �b���铇�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_GIRAN_TALKING_DELAYED;
	
	/**
	 * ID: 1488<br>
	 * Message: �b���铇�� �M�����`�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_TALKING_GIRAN_DELAYED;
	
	/**
	 * ID: 1489<br>
	 * Message: �C���i�h�����V���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId INNADRIL_BOAT_DELAYED;
	
	/**
	 * ID: 1490<br>
	 * Message: �앨$s1��$s2���Z���܂����B
	 */
	public static final SystemMessageId TRADED_S2_OF_CROP_S1;
	
	/**
	 * ID: 1491<br>
	 * Message: �앨$s1��$s2���Z����̂Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_IN_TRADING_S2_OF_CROP_S1;
	
	/**
	 * ID: 1492<br>
	 * Message: $s1�b��ɃI�����s�A�[�h �X�^�W�A���Ɉړ����܂��B
	 */
	public static final SystemMessageId YOU_WILL_ENTER_THE_OLYMPIAD_STADIUM_IN_S1_SECOND_S;
	
	/**
	 * ID: 1493<br>
	 * Message: �������肪�Q�[�����I�������̂Ŏ�������������܂����B
	 */
	public static final SystemMessageId THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_ENDS_THE_GAME;
	
	/**
	 * ID: 1494<br>
	 * Message: �������肪�Q�[���̎Q�������𖞂����Ȃ��������߁A��������������܂����B
	 */
	public static final SystemMessageId THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_JOINING_THE_GAME;
	
	/**
	 * ID: 1495<br>
	 * Message: $s1�b��Ɏ������J�n���܂��B
	 */
	public static final SystemMessageId THE_GAME_WILL_START_IN_S1_SECOND_S;
	
	/**
	 * ID: 1496<br>
	 * Message: �������J�n���܂��B
	 */
	public static final SystemMessageId STARTS_THE_GAME;
	
	/**
	 * ID: 1497<br>
	 * Message: $c1���������܂����B
	 */
	public static final SystemMessageId C1_HAS_WON_THE_GAME;
	
	/**
	 * ID: 1498<br>
	 * Message: ���������ł��B
	 */
	public static final SystemMessageId THE_GAME_ENDED_IN_A_TIE;
	
	/**
	 * ID: 1499<br>
	 * Message: $s1�b��ɑ��ɋA�҂��܂��B
	 */
	public static final SystemMessageId YOU_WILL_BE_MOVED_TO_TOWN_IN_S1_SECONDS;
	
	/**
	 * ID: 1500<br>
	 * Message: $c1�͎Q�������ɍ����܂���B�T�u�N���X �L�����N�^�[�ł̓I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_CLASS_CHARACTER;
	
	/**
	 * ID: 1501<br>
	 * Message: $c1�͎Q�������ɍ����܂���B�m�[�u���X�ȊO�̓I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_DOES_NOT_MEET_REQUIREMENTS_ONLY_NOBLESS_CAN_PARTICIPATE_IN_THE_OLYMPIAD;
	
	/**
	 * ID: 1502<br>
	 * Message: $c1�͋��Z�ҋ@�҃��X�g�ɂ��łɓo�^����Ă��܂��B
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST;
	
	/**
	 * ID: 1503<br>
	 * Message: �N���X�ʋ��Z��ڂ̑ҋ@�҃��X�g�ɓo�^����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_CLASSIFIED_GAMES;
	
	/**
	 * ID: 1504<br>
	 * Message: �N���X�������l���Z��ڂ̑ҋ@�҃��X�g�ɓo�^����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_NO_CLASS_GAMES;
	
	/**
	 * ID: 1505<br>
	 * Message: ���Z�ҋ@�҃��X�g����폜����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_DELETED_FROM_THE_WAITING_LIST_OF_A_GAME;
	
	/**
	 * ID: 1506<br>
	 * Message: ���Z�ҋ@�҃��X�g�ɓo�^����Ă��܂���B
	 */
	public static final SystemMessageId YOU_HAVE_NOT_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_A_GAME;
	
	/**
	 * ID: 1507<br>
	 * Message: �I�����s�A�[�h���Z�ł͑����ł��Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId THIS_ITEM_CANT_BE_EQUIPPED_FOR_THE_OLYMPIAD_EVENT;
	
	/**
	 * ID: 1508<br>
	 * Message: �I�����s�A�[�h���Z�ł͎g�p�ł��Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT;
	
	/**
	 * ID: 1509<br>
	 * Message: �I�����s�A�[�h���Z�ł͎g�p�ł��Ȃ��X�L���ł��B
	 */
	public static final SystemMessageId THIS_SKILL_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT;
	
	/**
	 * ID: 1510<br>
	 * Message: $c1��$s2�̌o���l�𕜋����镜�������݂Ă��܂��B�������܂����B
	 */
	public static final SystemMessageId RESSURECTION_REQUEST_BY_C1_FOR_S2_XP;
	
	/**
	 * ID: 1511<br>
	 * Message: �y�b�g�̕��������݂Ă��鎞�́A������𕜊��ł��܂���B
	 */
	public static final SystemMessageId MASTER_CANNOT_RES;
	
	/**
	 * ID: 1512<br>
	 * Message: ������̕��������݂Ă��鎞�́A�y�b�g�𕜊��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_RES_PET;
	
	/**
	 * ID: 1513<br>
	 * Message: ���łɂ��D�����̕�����񎦂���Ă����Ԃł��B
	 */
	public static final SystemMessageId RES_HAS_ALREADY_BEEN_PROPOSED;
	
	/**
	 * ID: 1514<br>
	 * Message: �y�b�g���������Ȃ̂ŁA������̕����������ɂȂ�܂����B
	 */
	public static final SystemMessageId CANNOT_RES_MASTER;
	
	/**
	 * ID: 1515<br>
	 * Message: �����傪�������Ȃ̂ŁA�y�b�g�̕����������ɂȂ�܂����B
	 */
	public static final SystemMessageId CANNOT_RES_PET2;
	
	/**
	 * ID: 1516<br>
	 * Message: ��̐A�t�����ł��Ȃ��Ώۂł��B
	 */
	public static final SystemMessageId THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING;
	
	/**
	 * ID: 1517<br>
	 * Message: �j�����ꂽ�����Ɏ��s���܂����B�Y���A�C�e���̃G���`�����g�l��0�ɂȂ�܂����B
	 */
	public static final SystemMessageId BLESSED_ENCHANT_FAILED;
	
	/**
	 * ID: 1518<br>
	 * Message: �A�C�e���̑��������ɍ���Ȃ����߁A�Y���A�C�e���𑕔��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_EQUIP_ITEM_DUE_TO_BAD_CONDITION;
	
	/**
	 * ID: 1519<br>
	 * Message: �y�b�g�����S���܂����B24���Ԉȓ��ɕ��������Ȃ���΃y�b�g�͏��ł��āA�y�b�g�̎����Ă����A�C�e�������ł��܂��B
	 */
	public static final SystemMessageId MAKE_SURE_YOU_RESSURECT_YOUR_PET_WITHIN_24_HOURS;
	
	/**
	 * ID: 1520<br>
	 * Message: �����b�����S���܂����B
	 */
	public static final SystemMessageId SERVITOR_PASSED_AWAY;
	
	/**
	 * ID: 1521<br>
	 * Message: �������Ԃ��z�����̂ŏ����b�������܂��B
	 */
	public static final SystemMessageId YOUR_SERVITOR_HAS_VANISHED;
	
	/**
	 * ID: 1522<br>
	 * Message: �y�b�g�̎��S��A�������Ԃ��z�����̂Ŏ��̂����ł��܂��B
	 */
	public static final SystemMessageId YOUR_PETS_CORPSE_HAS_DECAYED;
	
	/**
	 * ID: 1523<br>
	 * Message: �y�b�g�⏢���b�͑D�̉^�q���ɐ��ɗ����Ď��S���鋰�ꂪ����̂ŁA�o���O�ɏ������������Ă��������B
	 */
	public static final SystemMessageId RELEASE_PET_ON_BOAT;
	
	/**
	 * ID: 1524<br>
	 * Message: $c1�̃y�b�g��$s2����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PET_GAINED_S2;
	
	/**
	 * ID: 1525<br>
	 * Message: $c1�̃y�b�g��$s2 $s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PET_GAINED_S3_S2_S;
	
	/**
	 * ID: 1526<br>
	 * Message: $c1�̃y�b�g��+$s2$s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PET_GAINED_S2_S3;
	
	/**
	 * ID: 1527<br>
	 * Message: �y�b�g���󕠂������̂�$s1��H�ׂ܂����B
	 */
	public static final SystemMessageId PET_TOOK_S1_BECAUSE_HE_WAS_HUNGRY;
	
	/**
	 * ID: 1528<br>
	 * Message: �T�|�[�g����̂��A��������܂��B
	 */
	public static final SystemMessageId SENT_PETITION_TO_GM;
	
	/**
	 * ID: 1529<br>
	 * Message: $c1����A���ɏ��҂���܂����B�Q�����܂����B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_CONFIRM_FROM_C1;
	
	/**
	 * ID: 1530<br>
	 * Message: �Ώۂ��߂邩���O����͂��Ă��������B
	 */
	public static final SystemMessageId SELECT_TARGET_OR_ENTER_NAME;
	
	/**
	 * ID: 1531<br>
	 * Message: ���z�����鑊��̌���������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_DECLARE_WAR2;
	
	/**
	 * ID: 1532<br>
	 * Message: �푈������������̌���������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_CEASE_FIRE;
	
	/**
	 * ID: 1533<br>
	 * Message: ���m�点�F $c1��$s2����ɓ���܂����B
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PICKED_UP_S2;
	
	/**
	 * ID: 1534<br>
	 * Message: ���m�点�F $c1��+$s2$s3����ɓ���܂����B
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PICKED_UP_S2_S3;
	
	/**
	 * ID: 1535<br>
	 * Message: ���m�点�F $c1�̃y�b�g��$s2����ɓ���܂����B
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PET_PICKED_UP_S2;
	
	/**
	 * ID: 1536<br>
	 * Message: ���m�点�F $c1�̃y�b�g��+$s2$s3����ɓ���܂����B
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PET_PICKED_UP_S2_S3;
	
	/**
	 * ID: 1537<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (���E����̑��t��)
	 */
	public static final SystemMessageId LOC_RUNE_S1_S2_S3;
	
	/**
	 * ID: 1538<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�S�_�[�h��̑��t��)
	 */
	public static final SystemMessageId LOC_GODDARD_S1_S2_S3;
	
	/**
	 * ID: 1539<br>
	 * Message: �ݕ����b���铇�̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_TALKING_VILLAGE;
	
	/**
	 * ID: 1540<br>
	 * Message: �ݕ����_�[�N�G���t���ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_DARKELF_VILLAGE;
	
	/**
	 * ID: 1541<br>
	 * Message: �ݕ����G���t���ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_ELVEN_VILLAGE;
	
	/**
	 * ID: 1542<br>
	 * Message: �ݕ����I�[�N���ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_ORC_VILLAGE;
	
	/**
	 * ID: 1543<br>
	 * Message: �ݕ����h���[�t���ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_DWARVEN_VILLAGE;
	
	/**
	 * ID: 1544<br>
	 * Message: �ݕ����A�f����̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_ADEN;
	
	/**
	 * ID: 1545<br>
	 * Message: �ݕ����I�[������̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_OREN;
	
	/**
	 * ID: 1546<br>
	 * Message: �ݕ����t�̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_HUNTERS;
	
	/**
	 * ID: 1547<br>
	 * Message: �ݕ����f�B�I����̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_DION;
	
	/**
	 * ID: 1548<br>
	 * Message: �ݕ����t���[�������ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_FLORAN;
	
	/**
	 * ID: 1549<br>
	 * Message: �ݕ����O���[�f�B�����ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_GLUDIN;
	
	/**
	 * ID: 1550<br>
	 * Message: �ݕ����O���[�f�B�I��̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_GLUDIO;
	
	/**
	 * ID: 1551<br>
	 * Message: �ݕ����M������̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_GIRAN;
	
	/**
	 * ID: 1552<br>
	 * Message: �ݕ����n�C�l�X�ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_HEINE;
	
	/**
	 * ID: 1553<br>
	 * Message: �ݕ������E����̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_RUNE;
	
	/**
	 * ID: 1554<br>
	 * Message: �ݕ����S�_�[�h��̑��ɓ͂��Ă��܂��B
	 */
	public static final SystemMessageId CARGO_AT_GODDARD;
	
	/**
	 * ID: 1555<br>
	 * Message: �L�����N�^�[�̍폜���������܂����B
	 */
	public static final SystemMessageId CANCEL_CHARACTER_DELETION_CONFIRM;
	
	/**
	 * ID: 1556<br>
	 * Message: ���m�点�̓��e��ۑ����܂����B
	 */
	public static final SystemMessageId CLAN_NOTICE_SAVED;
	
	/**
	 * ID: 1557<br>
	 * Message: ��̉��i��$s1�ȏ�$s2�ȉ��ɂ��Ă��������B
	 */
	public static final SystemMessageId SEED_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1558<br>
	 * Message: ��̐��ʂ�$s1�ȏ�$s2�ȉ��ɂ��Ă��������B
	 */
	public static final SystemMessageId THE_QUANTITY_OF_SEED_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1559<br>
	 * Message: �앨�̉��i��$s1�ȏ�$s2�ȉ��ɂ��Ă��������B
	 */
	public static final SystemMessageId CROP_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1560<br>
	 * Message: �앨�̐��ʂ�$s1�ȏ�$s2�ȉ��ɂ��Ă��������B
	 */
	public static final SystemMessageId THE_QUANTITY_OF_CROP_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1561<br>
	 * Message: $s1�������������z�����܂����B
	 */
	public static final SystemMessageId CLAN_S1_DECLARED_WAR;
	
	/**
	 * ID: 1562<br>
	 * Message: $s1�����ɑ΂��������z�����܂����B��������葊��̌������ɂ���Ď��S�����ꍇ�A�o���l���X�g���ʏ��4����1�ɂȂ�܂��B
	 */
	public static final SystemMessageId CLAN_WAR_DECLARED_AGAINST_S1_IF_KILLED_LOSE_LOW_EXP;
	
	/**
	 * ID: 1563<br>
	 * Message: $s1�����͌������x���܂��͌�������������Ȃ��̂Ō������z���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DECLARE_WAR_TOO_LOW_LEVEL_OR_NOT_ENOUGH_MEMBERS;
	
	/**
	 * ID: 1564<br>
	 * Message: �������x��3�ȏ�A��������15�l�ȏ�̌����݂̂��������z���ł��܂��B
	 */
	public static final SystemMessageId CLAN_WAR_DECLARED_IF_CLAN_LVL3_OR_15_MEMBER;
	
	/**
	 * ID: 1565<br>
	 * Message: ���݂��Ȃ������܂��͒����Ԋ������Ă��Ȃ������Ȃ̂Ő��z���ł��܂���B
	 */
	public static final SystemMessageId CLAN_WAR_CANNOT_DECLARED_CLAN_NOT_EXIST;
	
	/**
	 * ID: 1566<br>
	 * Message: $s1�������z������艺���܂����B
	 */
	public static final SystemMessageId CLAN_S1_HAS_DECIDED_TO_STOP;
	
	/**
	 * ID: 1567<br>
	 * Message: $s1�����ւ̕z������艺���܂����B
	 */
	public static final SystemMessageId WAR_AGAINST_S1_HAS_STOPPED;
	
	/**
	 * ID: 1568<br>
	 * Message: �z���̑Ώۂ�����������܂���B
	 */
	public static final SystemMessageId WRONG_DECLARATION_TARGET;
	
	/**
	 * ID: 1569<br>
	 * Message: ���������񂾌����ɂ͌������z���ł��܂���B
	 */
	public static final SystemMessageId CLAN_WAR_AGAINST_A_ALLIED_CLAN_NOT_WORK;
	
	/**
	 * ID: 1570<br>
	 * Message: 30�ȏ�̌����ɓ����ɐ��z�����邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId TOO_MANY_CLAN_WARS;
	
	/**
	 * ID: 1571<br>
	 * Message: =======<�z�����X�g>=======
	 */
	public static final SystemMessageId CLANS_YOU_DECLARED_WAR_ON;
	
	/**
	 * ID: 1572<br>
	 * Message: ======<��z�����X�g>======
	 */
	public static final SystemMessageId CLANS_THAT_HAVE_DECLARED_WAR_ON_YOU;
	
	/**
	 * ID: 1573<br>
	 * Message: �z�����錌�������݂��܂���B
	 */
	public static final SystemMessageId NO_WARS_AGAINST_YOU;
	
	/**
	 * ID: 1574<br>
	 * Message: �z�����󂯂錌�������݂��܂���B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ONLY_BY_LEVEL_5_CLAN_LEADER_PARTY_LEADER;
	
	/**
	 * ID: 1575<br>
	 * Message: �A���̌����͌������x��5�ȏ�̌�����ł���p�[�e�B�[ ���[�_�[�݂̂��\�ł��B
	 */
	public static final SystemMessageId PET_USE_SPIRITSHOT;
	
	/**
	 * ID: 1576<br>
	 * Message: �y�b�g������̗͂��g�p���܂��B
	 */
	public static final SystemMessageId SERVITOR_USE_SPIRITSHOT;
	
	/**
	 * ID: 1577<br>
	 * Message: �����b������̗͂��g�p���܂��B
	 */
	public static final SystemMessageId SERVITOR_USE_THE_POWER_OF_SPIRIT;
	
	/**
	 * ID: 1578<br>
	 * Message: �l���X��l�H�[���J���Ă��鎞�̓A�C�e���𑕔��ł��܂���B
	 */
	public static final SystemMessageId ITEMS_UNAVAILABLE_FOR_STORE_MANUFACTURE;
	
	/**
	 * ID: 1579<br>
	 * Message: $c1�̃y�b�g��$s2�A�f�i����ɓ���܂����B
	 */
	public static final SystemMessageId C1_PET_GAINED_S2_ADENA;
	
	/**
	 * ID: 1580<br>
	 * Message: �A������������܂����B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_FORMED;
	
	/**
	 * ID: 1581<br>
	 * Message: �A�����������܂����B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_DISBANDED;
	
	/**
	 * ID: 1582<br>
	 * Message: �A���ɎQ�����܂����B
	 */
	public static final SystemMessageId JOINED_COMMAND_CHANNEL;
	
	/**
	 * ID: 1583<br>
	 * Message: �A������Ǖ�����܂����B
	 */
	public static final SystemMessageId DISMISSED_FROM_COMMAND_CHANNEL;
	
	/**
	 * ID: 1584<br>
	 * Message: $c1�̃p�[�e�B�[��A������Ǖ����܂����B
	 */
	public static final SystemMessageId C1_PARTY_DISMISSED_FROM_COMMAND_CHANNEL;
	
	/**
	 * ID: 1585<br>
	 * Message: �A������A�N�e�B�u�ɂȂ�܂����B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_DISBANDED2;
	
	/**
	 * ID: 1586<br>
	 * Message: �A������E�ނ��܂����B
	 */
	public static final SystemMessageId LEFT_COMMAND_CHANNEL;
	
	/**
	 * ID: 1587<br>
	 * Message: $c1�̃p�[�e�B�[���A���`�����l������E�ނ��܂����B
	 */
	public static final SystemMessageId C1_PARTY_LEFT_COMMAND_CHANNEL;
	
	/**
	 * ID: 1588<br>
	 * Message: �A����5�ȏ�̃p�[�e�B�[���Q���������̂݃A�N�e�B�u�ɂȂ�܂��B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ONLY_AT_LEAST_5_PARTIES;
	
	/**
	 * ID: 1589<br>
	 * Message: $c1�ɘA���̌������^�����܂����B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_LEADER_NOW_C1;
	
	/**
	 * ID: 1590<br>
	 * Message: ===<�A�����i�S�p�[�e�B�[���F$s1)>===
	 */
	public static final SystemMessageId GUILD_INFO_HEADER;
	
	/**
	 * ID: 1591<br>
	 * Message: �A���ɏ��҂����v���C���[�����܂���B
	 */
	public static final SystemMessageId NO_USER_INVITED_TO_COMMAND_CHANNEL;
	
	/**
	 * ID: 1592<br>
	 * Message: ����ȏ�A�����J�݂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_LONGER_SETUP_COMMAND_CHANNEL;
	
	/**
	 * ID: 1593<br>
	 * Message: �A���ɏ��҂ł��錠��������܂���B
	 */
	public static final SystemMessageId CANNOT_INVITE_TO_COMMAND_CHANNEL;
	
	/**
	 * ID: 1594<br>
	 * Message: $c1�̃p�[�e�B�[�͂��łɘA���ɎQ�����Ă��܂��B
	 */
	public static final SystemMessageId C1_ALREADY_MEMBER_OF_COMMAND_CHANNEL;
	
	/**
	 * ID: 1595<br>
	 * Message: $s1�ɐ������܂����B
	 */
	public static final SystemMessageId S1_SUCCEEDED;
	
	/**
	 * ID: 1596<br>
	 * Message: $s1�ɓ�����܂����B
	 */
	public static final SystemMessageId HIT_BY_S1;
	
	/**
	 * ID: 1597<br>
	 * Message: $s1�����s���܂����B
	 */
	public static final SystemMessageId S1_FAILED;
	
	/**
	 * ID: 1598<br>
	 * Message: �y�b�g����я����b�����S������Ԃł́A�y�b�g����я����b�p�\�E�� �V���b�g�܂��̓X�s���b�g �V���b�g�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId SOULSHOTS_AND_SPIRITSHOTS_ARE_NOT_AVAILABLE_FOR_A_DEAD_PET;
	
	/**
	 * ID: 1599<br>
	 * Message: �퓬���͊ϐ�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_OBSERVE_IN_COMBAT;
	
	/**
	 * ID: 1600<br>
	 * Message: �����̍��ڂ̐��l�����ׂ�0�ɏ���������܂��B��낵���ł����B
	 */
	public static final SystemMessageId TOMORROW_ITEM_ZERO_CONFIRM;
	
	/**
	 * ID: 1601<br>
	 * Message: �����̍��ڂ̐��l�����ׂč����Ɠ������l�ɐݒ肳��܂��B��낵���ł����B
	 */
	public static final SystemMessageId TOMORROW_ITEM_SAME_CONFIRM;
	
	/**
	 * ID: 1602<br>
	 * Message: �A���`���b�g�̓p�[�e�B�[ ���[�_�[�̂ݎQ���ł��܂��B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ONLY_FOR_PARTY_LEADER;
	
	/**
	 * ID: 1603<br>
	 * Message: �S�̖��߂̓`�����l���J�ݎ҂̂ݍs�����Ƃ��ł��܂��B
	 */
	public static final SystemMessageId ONLY_COMMANDER_GIVE_COMMAND;
	
	/**
	 * ID: 1604<br>
	 * Message: �畞���p���͂��ׂẴX�L���ƁA�������[�V�������K�v�ȃA�C�e�����g�p�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_USE_ITEMS_SKILLS_WITH_FORMALWEAR;
	
	/**
	 * ID: 1605<br>
	 * Message: *�����ł�$s1�����̎�̂ݍw���ł��܂��B
	 */
	public static final SystemMessageId HERE_YOU_CAN_BUY_ONLY_SEEDS_OF_S1_MANOR;
	
	/**
	 * ID: 1606<br>
	 * Message: 3���]�E�N�G�X�g���N���A���A�V���ȃN���X�ɓ]�E���܂����B���߂łƂ��������܂��I
	 */
	public static final SystemMessageId THIRD_CLASS_TRANSFER;
	
	/**
	 * ID: 1607<br>
	 * Message: $s1�A�f�i�𔃎��萔���Ƃ��Ďx�����܂����B
	 */
	public static final SystemMessageId S1_ADENA_HAS_BEEN_WITHDRAWN_TO_PAY_FOR_PURCHASING_FEES;
	
	/**
	 * ID: 1608<br>
	 * Message: �A�f�i���s�����đ���̔���肪�ł��܂���B
	 */
	public static final SystemMessageId INSUFFICIENT_ADENA_TO_BUY_CASTLE;
	
	/**
	 * ID: 1609<br>
	 * Message: ���łɐ��z�����������ł��B
	 */
	public static final SystemMessageId WAR_ALREADY_DECLARED;
	
	/**
	 * ID: 1610<br>
	 * Message: �����̌����ɂ͐��z���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DECLARE_AGAINST_OWN_CLAN;
	
	/**
	 * ID: 1611<br>
	 * Message: �p�[�e�B�[ ���[�_�[�F $c1
	 */
	public static final SystemMessageId PARTY_LEADER_C1;
	
	/**
	 * ID: 1612<br>
	 * Message: =====<�o���z�����X�g>=====
	 */
	public static final SystemMessageId WAR_LIST;
	
	/**
	 * ID: 1613<br>
	 * Message: �o���z����������������܂���B
	 */
	public static final SystemMessageId NO_CLAN_ON_WAR_LIST;
	
	/**
	 * ID: 1614<br>
	 * Message: ���łɊJ�݂��ꂽ�`�����l���ɎQ�����Ă��܂��B
	 */
	public static final SystemMessageId JOINED_CHANNEL_ALREADY_OPEN;
	
	/**
	 * ID: 1615<br>
	 * Message: �`�����l�����A�N�e�B�u������܂�$s1�̃p�[�e�B�[���Q���ł��܂��B
	 */
	public static final SystemMessageId S1_PARTIES_REMAINING_UNTIL_CHANNEL;
	
	/**
	 * ID: 1616<br>
	 * Message: �A�����A�N�e�B�u�ɂȂ�܂����B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ACTIVATED;
	
	/**
	 * ID: 1617<br>
	 * Message: �A���𗘗p�ł��錠��������܂���B
	 */
	public static final SystemMessageId CANT_USE_COMMAND_CHANNEL;
	
	/**
	 * ID: 1618<br>
	 * Message: ���E���`�� �O���[�f�B���`�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_RUNE_GLUDIN_DELAYED;
	
	/**
	 * ID: 1619<br>
	 * Message: �O���[�f�B���`�� ���E���`�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_GLUDIN_RUNE_DELAYED;
	
	/**
	 * ID: 1620<br>
	 * Message: ���E���`�ɓ������܂����B
	 */
	public static final SystemMessageId ARRIVED_AT_RUNE;
	
	/**
	 * ID: 1621<br>
	 * Message: 5����A���E���`����O���[�f�B���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_5_MINUTES;
	
	/**
	 * ID: 1622<br>
	 * Message: 1����A���E���`����O���[�f�B���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_1_MINUTE;
	
	/**
	 * ID: 1623<br>
	 * Message: �܂��Ȃ��A���E���`����O���[�f�B���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_SHORTLY;
	
	/**
	 * ID: 1624<br>
	 * Message: ���E���`����O���[�f�B���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_NOW;
	
	/**
	 * ID: 1625<br>
	 * Message: 10���Ԓ┑��A���E���`�ɏo�����܂��B
	 */
	public static final SystemMessageId REPARTURE_FOR_RUNE_10_MINUTES;
	
	/**
	 * ID: 1626<br>
	 * Message: 5����A�O���[�f�B���`���烋�E���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_5_MINUTES;
	
	/**
	 * ID: 1627<br>
	 * Message: 1����A�O���[�f�B���`���烋�E���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_1_MINUTE;
	
	/**
	 * ID: 1628<br>
	 * Message: �܂��Ȃ��A�O���[�f�B���`���烋�E���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_SHORTLY2;
	
	/**
	 * ID: 1629<br>
	 * Message: �O���[�f�B���`���烋�E���`�s���̑D���o�q���܂��B
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_NOW;
	
	/**
	 * ID: 1630<br>
	 * Message: ���E���`���̒���D���A��15����O���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_15_MINUTES;
	
	/**
	 * ID: 1631<br>
	 * Message: ���E���`���̒���D���A��10����O���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_10_MINUTES;
	
	/**
	 * ID: 1632<br>
	 * Message: ���E���`���̒���D���A��5����O���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_5_MINUTES;
	
	/**
	 * ID: 1633<br>
	 * Message: ���E���`���̒���D���A��1����O���[�f�B���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_1_MINUTE;
	
	/**
	 * ID: 1634<br>
	 * Message: �O���[�f�B���`���̒���D���A��15���ニ�E���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_15_MINUTES;
	
	/**
	 * ID: 1635<br>
	 * Message: �O���[�f�B���`���̒���D���A��10���ニ�E���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_10_MINUTES;
	
	/**
	 * ID: 1636<br>
	 * Message: �O���[�f�B���`���̒���D���A��5���ニ�E���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_5_MINUTES;
	
	/**
	 * ID: 1637<br>
	 * Message: �O���[�f�B���`���̒���D���A��1���ニ�E���`�ɓ������܂��B
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_1_MINUTE;
	
	/**
	 * ID: 1638<br>
	 * Message: ����}�W�A�l�H�[�A�l���X�̎g�p���ɂ̓t�B�b�V���O���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_FISH_WHILE_USING_RECIPE_BOOK;
	
	/**
	 * ID: 1639<br>
	 * Message: ��$s1���I�����s�A�[�h���Ԃ��n�܂�܂����B
	 */
	public static final SystemMessageId OLYMPIAD_PERIOD_S1_HAS_STARTED;
	
	/**
	 * ID: 1640<br>
	 * Message: ��$s1���I�����s�A�[�h���Ԃ��I�����܂����B
	 */
	public static final SystemMessageId OLYMPIAD_PERIOD_S1_HAS_ENDED;
	
	/**
	 * ID: 1641<br>
	 * Message: �I�����s�A�[�h���Z���n�܂�܂����B
	 */
	public static final SystemMessageId THE_OLYMPIAD_GAME_HAS_STARTED;
	
	/**
	 * ID: 1642<br>
	 * Message: �I�����s�A�[�h���Z���I�����܂����B
	 */
	public static final SystemMessageId THE_OLYMPIAD_GAME_HAS_ENDED;
	
	/**
	 * ID: 1643<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�����̋���)
	 */
	public static final SystemMessageId LOC_DIMENSIONAL_GAP_S1_S2_S3;
	
	// 1644 - 1648: none
	
	/**
	 * ID: 1649<br>
	 * Message: �ڑ����Ԃ̗ݐς��ĊJ���܂��B
	 */
	public static final SystemMessageId PLAY_TIME_NOW_ACCUMULATING;
	
	/**
	 * ID: 1650<br>
	 * Message: ��ύ��݂����Ă��܂��̂Őڑ��ł��܂���B���΂炭���Ă���ēx�A�N�Z�X���Ă��������B
	 */
	public static final SystemMessageId TRY_LOGIN_LATER;
	
	/**
	 * ID: 1651<br>
	 * Message: �I�����s�A�[�h���Z���s���鎞�Ԃł͂���܂���B
	 */
	public static final SystemMessageId THE_OLYMPIAD_GAME_IS_NOT_CURRENTLY_IN_PROGRESS;
	
	/**
	 * ID: 1652<br>
	 * Message: ���v���C�^����n�߂܂��B
	 */
	public static final SystemMessageId RECORDING_GAMEPLAY_START;
	
	/**
	 * ID: 1653<br>
	 * Message: ���v���C �t�@�C����ۑ����܂����B($s1)
	 */
	public static final SystemMessageId RECORDING_GAMEPLAY_STOP_S1;
	
	/**
	 * ID: 1654<br>
	 * Message: ���v���C�^��Ɏ��s���܂����B
	 */
	public static final SystemMessageId RECORDING_GAMEPLAY_FAILED;
	
	/**
	 * ID: 1655<br>
	 * Message: �����X�^�[���ނ�܂����I
	 */
	public static final SystemMessageId YOU_CAUGHT_SOMETHING_SMELLY_THROW_IT_BACK;
	
	/**
	 * ID: 1656<br>
	 * Message: NPC�Ƃ̃g���[�h�ɐ������܂����B
	 */
	public static final SystemMessageId SUCCESSFULLY_TRADED_WITH_NPC;
	
	/**
	 * ID: 1657<br>
	 * Message: $c1���I�����s�A�[�h �|�C���g$s2�|�C���g�𓾂܂����B
	 */
	public static final SystemMessageId C1_HAS_GAINED_S2_OLYMPIAD_POINTS;
	
	/**
	 * ID: 1658<br>
	 * Message: $c1���I�����s�A�[�h �|�C���g$s2�|�C���g�������܂����B
	 */
	public static final SystemMessageId C1_HAS_LOST_S2_OLYMPIAD_POINTS;
	
	/**
	 * ID: 1659<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�鍑�̕�n)
	 */
	public static final SystemMessageId LOC_CEMETARY_OF_THE_EMPIRE_S1_S2_S3;
	
	/**
	 * ID: 1660<br>
	 * Message: �`�����l���J�ݎҁF $c1
	 */
	public static final SystemMessageId CHANNEL_CREATOR_C1;
	
	/**
	 * ID: 1661<br>
	 * Message: $c1��$s2 $s3����ɓ���܂����B
	 */
	public static final SystemMessageId C1_OBTAINED_S3_S2_S;
	
	/**
	 * ID: 1662<br>
	 * Message: ��ӏ��Œ����Ԓނ��Ă���ƐH�����m�����Ⴍ�Ȃ�܂��B���̏ꏊ�Ɉړ����đ����Ă��������B
	 */
	public static final SystemMessageId FISH_NO_MORE_BITING_TRY_OTHER_LOCATION;
	
	/**
	 * ID: 1663<br>
	 * Message: �G���T�C����o�^�܂��͍폜���܂����B�A�W�g�܂��͏�����L���錌���ɂ̂݁A�����֘A�A�C�e���ɃG���T�C�����\������܂��B
	 */
	public static final SystemMessageId CLAN_EMBLEM_WAS_SUCCESSFULLY_REGISTERED;
	
	/**
	 * ID: 1664<br>
	 * Message: ������R���Ă���̂ŃE�L�������������Ă��܂��B
	 */
	public static final SystemMessageId FISH_RESISTING_LOOK_BOBBLER;
	
	/**
	 * ID: 1665<br>
	 * Message: �������Ă���̂ŃE�L�̓������ア�ł��B
	 */
	public static final SystemMessageId YOU_WORN_FISH_OUT;
	
	/**
	 * ID: 1666<br>
	 * Message: +$s1$s2����ɓ���܂����B
	 */
	public static final SystemMessageId OBTAINED_S1_S2;
	
	/**
	 * ID: 1667<br>
	 * Message: �C���X�^���g �L���I
	 */
	public static final SystemMessageId LETHAL_STRIKE;
	
	/**
	 * ID: 1668<br>
	 * Message: �����X�L�����q�b�g���܂����I
	 */
	public static final SystemMessageId LETHAL_STRIKE_SUCCESSFUL;
	
	/**
	 * ID: 1669<br>
	 * Message: �A�C�e���ϊ��Ɏ��s���܂����B
	 */
	public static final SystemMessageId NOTHING_INSIDE_THAT;
	
	/**
	 * ID: 1670<br>
	 * Message: ���[�����O(�|���s���O)�X�L���̃��x�����t�B�b�V���O �}�X�^���[�̃��x�����������̂�$s1�̃y�i���e�B���K�p����܂��B
	 */
	public static final SystemMessageId REELING_PUMPING_3_LEVELS_HIGHER_THAN_FISHING_PENALTY;
	
	/**
	 * ID: 1671<br>
	 * Message: ���[�����O�����I(�}�X�^���[ �y�i���e�B�F$s1)
	 */
	public static final SystemMessageId REELING_SUCCESSFUL_PENALTY_S1;
	
	/**
	 * ID: 1672<br>
	 * Message: �|���s���O�����I(�}�X�^���[ �y�i���e�B�F$s1)
	 */
	public static final SystemMessageId PUMPING_SUCCESSFUL_PENALTY_S1;
	
	/**
	 * ID: 1673<br>
	 * Message: ����̃I�����s�A�[�h�̌��݂̐�т�$s1��$s2��$s3�s�ł��B�l�������I�����s�A�[�h �|�C���g��$s4�|�C���g�ł��B
	 */
	public static final SystemMessageId THE_CURRENT_RECORD_FOR_THIS_OLYMPIAD_SESSION_IS_S1_MATCHES_S2_WINS_S3_DEFEATS_YOU_HAVE_EARNED_S4_OLYMPIAD_POINTS;
	
	/**
	 * ID: 1674<br>
	 * Message: �m�[�u���X�̂ݎg�p�ł���R�}���h�ł��B
	 */
	public static final SystemMessageId NOBLESSE_ONLY;
	
	/**
	 * ID: 1675<br>
	 * Message: 04��30������20���̊Ԃ͑����̐ݒ��ύX�ł��܂���B
	 */
	public static final SystemMessageId A_MANOR_CANNOT_BE_SET_UP_BETWEEN_6_AM_AND_8_PM;
	
	/**
	 * ID: 1676<br>
	 * Message: �����b�܂��̓y�b�g�����Ȃ��̂Ŏ����g�p�ł��܂���B
	 */
	public static final SystemMessageId NO_SERVITOR_CANNOT_AUTOMATE_USE;
	
	/**
	 * ID: 1677<br>
	 * Message: �퓬���̌����������݂���̂ŕz�����������܂���B
	 */
	public static final SystemMessageId CANT_STOP_CLAN_WAR_WHILE_IN_COMBAT;
	
	/**
	 * ID: 1678<br>
	 * Message: $s1�����Ɍ������z�����܂���ł����B
	 */
	public static final SystemMessageId NO_CLAN_WAR_AGAINST_CLAN_S1;
	
	/**
	 * ID: 1679<br>
	 * Message: �S�̖��߂̓`�����l���J�ݎ҂̂ݎ��s�ł��܂��B
	 */
	public static final SystemMessageId ONLY_CHANNEL_CREATOR_CAN_GLOBAL_COMMAND;
	
	/**
	 * ID: 1680<br>
	 * Message: $c1���`�����l�����҂����ۂ��܂����B
	 */
	public static final SystemMessageId C1_DECLINED_CHANNEL_INVITATION;
	
	/**
	 * ID: 1681<br>
	 * Message: $c1�̉������Ȃ��̂Ń`�����l�����҂Ɏ��s���܂����B
	 */
	public static final SystemMessageId C1_DID_NOT_RESPOND_CHANNEL_INVITATION_FAILED;
	
	/**
	 * ID: 1682<br>
	 * Message: �`�����l���Ǖ��͊J�ݎ҂̂ݎ��s�ł��܂��B
	 */
	public static final SystemMessageId ONLY_CHANNEL_CREATOR_CAN_DISMISS;
	
	/**
	 * ID: 1683<br>
	 * Message: �`�����l���E�ނ̓p�[�e�B�[ ���[�_�[�̂ݎ��s�ł��܂��B
	 */
	public static final SystemMessageId ONLY_PARTY_LEADER_CAN_LEAVE_CHANNEL;
	
	/**
	 * ID: 1684<br>
	 * Message: �������U���ɂ͌������z���ł��܂���B
	 */
	public static final SystemMessageId NO_CLAN_WAR_AGAINST_DISSOLVING_CLAN;
	
	/**
	 * ID: 1685<br>
	 * Message: PK�J�E���g��1�ȏゾ�Ƒ����ł��Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId YOU_ARE_UNABLE_TO_EQUIP_THIS_ITEM_WHEN_YOUR_PK_COUNT_IS_GREATER_THAN_OR_EQUAL_TO_ONE;
	
	/**
	 * ID: 1686<br>
	 * Message: ��ǂ�����܂����B
	 */
	public static final SystemMessageId CASTLE_WALL_DAMAGED;
	
	/**
	 * ID: 1687<br>
	 * Message: ���C�o�[���ɏ�����܂܂ł͍s���Ȃ��G���A�ł��B�����������܂�Ɠ��悪��������܂��B
	 */
	public static final SystemMessageId AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_WYVERN;
	
	/**
	 * ID: 1688<br>
	 * Message: �l���X��l�H�[�̏�Ԃł̓G���`�����g�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_ENCHANT_WHILE_STORE;
	
	/**
	 * ID: 1689<br>
	 * Message: $c1�̓N���X�ʋ��Z�̑ҋ@�҃��X�g�ɂ��łɓo�^����Ă��܂��B
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST;
	
	/**
	 * ID: 1690<br>
	 * Message: $c1�̓N���X�������l���Z�̑ҋ@�҃��X�g�ɂ��łɓo�^����Ă��܂��B
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_NON_CLASS_LIMITED_MATCH_WAITING_LIST;
	
	/**
	 * ID: 1691<br>
	 * Message: $c1�͎Q�������ɍ����܂���B�C���x���g�� �X���b�g��80���𒴂��Ă��邽�߁A�I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_INVENTORY_SLOT_EXCEEDS_80_PERCENT;
	
	/**
	 * ID: 1692<br>
	 * Message: $c1�͎Q�������ɍ����܂���B�N���X���T�u�N���X�ɕύX�������߁A�Q���ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_WHILE_CHANGED_TO_SUB_CLASS;
	
	/**
	 * ID: 1693<br>
	 * Message: �ҋ@�҃��X�g�ɓo�^������Ԃł͋��Z���ϗ��ł��܂���B
	 */
	public static final SystemMessageId WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME;
	
	/**
	 * ID: 1694<br>
	 * Message: �U��펞�A�m�[�u���X�̌�����̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId ONLY_NOBLESSE_LEADER_CAN_VIEW_SIEGE_STATUS_WINDOW;
	
	/**
	 * ID: 1695<br>
	 * Message: �U��펞�̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId ONLY_DURING_SIEGE;
	
	/**
	 * ID: 1696<br>
	 * Message: �v���C���Ԃ� $s1���Ԃł��B
	 */
	public static final SystemMessageId ACCUMULATED_PLAY_TIME_IS_S1;
	
	/**
	 * ID: 1697<br>
	 * Message: �Q�[�� �v���C���Ԃ��u��J�x�v�𒴂������߁A�Q�[������������l��50���ɒቺ���܂����B���N�̂��߂ɍ��������O�A�E�g���A�x�e���Ƃ������ƂɁA�K�؂ȃX�g���b�`�����s���A�׊w�Ƃ̃o�����X���Ƃ��Ă��������B
	 */
	public static final SystemMessageId ACCUMULATED_PLAY_TIME_WARNING1;
	
	/**
	 * ID: 1698<br>
	 * Message: ���N���Q���鋰��̂���Q�[�����Ԃɓ���܂����B���N�̂��߂ɍ��������O�A�E�g���A�x�e�����Ƃ肭�������B���O�A�E�g���Ȃ��ꍇ�A���N���Q���ăQ�[��������0���ɒቺ���A���O�A�E�g��̗ݐώ��Ԃ�5���ԂɒB���Ȃ��ƌ��ɂ͖߂�܂���B
	 */
	public static final SystemMessageId ACCUMULATED_PLAY_TIME_WARNING2;
	
	/**
	 * ID: 1699<br>
	 * Message: �p�[�e�B�[ �����o�[�������Ǖ��ł��܂���B
	 */
	public static final SystemMessageId CANNOT_DISMISS_PARTY_MEMBER;
	
	/**
	 * ID: 1700<br>
	 * Message: �y�b�g����я����b�p�X�s���b�g �V���b�g������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SPIRITHOTS_FOR_PET;
	
	/**
	 * ID: 1701<br>
	 * Message: �y�b�g����я����b�p�\�E�� �V���b�g������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SOULSHOTS_FOR_PET;
	
	/**
	 * ID: 1702<br>
	 * Message: ��قǃ`�F�b�N�������[�U�[$s1��BOT�g�p�҂Ɣ������܂����B
	 */
	public static final SystemMessageId S1_USING_THIRD_PARTY_PROGRAM;
	
	/**
	 * ID: 1703<br>
	 * Message: ��قǃ`�F�b�N�������[�U�[��BOT�g�p�҂ł͂Ȃ��悤�ł��B
	 */
	public static final SystemMessageId NOT_USING_THIRD_PARTY_PROGRAM;
	
	/**
	 * ID: 1704<br>
	 * Message: �l�H�[�A���X�ݒ�E�B���h�E����Ă�����s���Ă��������B
	 */
	public static final SystemMessageId CLOSE_STORE_WINDOW_AND_TRY_AGAIN;
	
	/**
	 * ID: 1705<br>
	 * Message: �l�b�g�J�t�F �|�C���g�l�����Ԃł��B
	 */
	public static final SystemMessageId PCPOINT_ACQUISITION_PERIOD;
	
	/**
	 * ID: 1706<br>
	 * Message: �l�b�g�J�t�F �|�C���g�L�����Ԃł��B
	 */
	public static final SystemMessageId PCPOINT_USE_PERIOD;
	
	/**
	 * ID: 1707<br>
	 * Message: �l�b�g�J�t�F �|�C���g $s1�|�C���g���l�����܂����B
	 */
	public static final SystemMessageId ACQUIRED_S1_PCPOINT;
	
	/**
	 * ID: 1708<br>
	 * Message: �_�u�� �|�C���g�I�l�b�g�J�t�F �|�C���g $s1�|�C���g���l�����܂����B
	 */
	public static final SystemMessageId ACQUIRED_S1_PCPOINT_DOUBLE;
	
	/**
	 * ID: 1709<br>
	 * Message: $s1�|�C���g���g�p���܂��B
	 */
	public static final SystemMessageId USING_S1_PCPOINT;
	
	/**
	 * ID: 1710<br>
	 * Message: �|�C���g������܂���B
	 */
	public static final SystemMessageId SHORT_OF_ACCUMULATED_POINTS;
	
	/**
	 * ID: 1711<br>
	 * Message: �l�b�g�J�t�F �|�C���g�L���������؂�Ă��܂��B
	 */
	public static final SystemMessageId PCPOINT_USE_PERIOD_EXPIRED;
	
	/**
	 * ID: 1712<br>
	 * Message: �l�b�g�J�t�F �|�C���g�l�����Ԃ��I�����܂����B
	 */
	public static final SystemMessageId PCPOINT_ACCUMULATION_PERIOD_EXPIRED;
	
	/**
	 * ID: 1713<br>
	 * Message: �ҋ@�Ґ�������Ȃ��̂ŁA���Z�J�n�ɒx�ꂪ�o��ꍇ������܂��B
	 */
	public static final SystemMessageId GAMES_DELAYED;
	
	/**
	 * ID: 1714<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�V���`���b�c�K���g��̑��t��)
	 */
	public static final SystemMessageId LOC_SCHUTTGART_S1_S2_S3;
	
	/**
	 * ID: 1715<br>
	 * Message: �s�[�X�]�[��\n- PvP���ł��Ȃ��G���A�ł��B
	 */
	public static final SystemMessageId PEACEFUL_ZONE;
	
	/**
	 * ID: 1716<br>
	 * Message: �ُ��ԃG���A
	 */
	public static final SystemMessageId ALTERED_ZONE;
	
	/**
	 * ID: 1717<br>
	 * Message: �U���]�[��\n-�U��킪�s���Ă���G���A�ł��B\n���S���̕����ɐ���������G���A�ł��B
	 */
	public static final SystemMessageId SIEGE_ZONE;
	
	/**
	 * ID: 1718<br>
	 * Message: ��ʃt�B�[���h
	 */
	public static final SystemMessageId GENERAL_ZONE;
	
	/**
	 * ID: 1719<br>
	 * Message: �Z�u�� �T�C�� �]�[��\n- ���x�� �A�b�v���Ă�HP�AMP��\n�񕜂���Ȃ��G���A�ł��B
	 */
	public static final SystemMessageId SEVENSIGNS_ZONE;
	
	/**
	 * ID: 1720<br>
	 * Message: ---
	 */
	public static final SystemMessageId UNKNOWN1;
	
	/**
	 * ID: 1721<br>
	 * Message: �����G���A
	 */
	public static final SystemMessageId COMBAT_ZONE;
	
	/**
	 * ID: 1722<br>
	 * Message: �l���X�A�l�H�[�Ō�������A�C�e��������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_ITEM_NAME_SEARCH;
	
	/**
	 * ID: 1723<br>
	 * Message: �T�|�[�g�ɑ΂���]�������Ē�����΍K���ł��B
	 */
	public static final SystemMessageId PLEASE_PROVIDE_PETITION_FEEDBACK;
	
	/**
	 * ID: 1724<br>
	 * Message: �퓬���͏����b��߂��܂���B
	 */
	public static final SystemMessageId SERVITOR_NOT_RETURN_IN_BATTLE;
	
	/**
	 * ID: 1725<br>
	 * Message: $s1�̃��C�h �|�C���g���l�����܂����B
	 */
	public static final SystemMessageId EARNED_S1_RAID_POINTS;
	
	/**
	 * ID: 1726<br>
	 * Message: �L�����Ԃ����������̂�$s1�������܂����B
	 */
	public static final SystemMessageId S1_PERIOD_EXPIRED_DISAPPEARED;
	
	/**
	 * ID: 1727<br>
	 * Message: $c1���p�[�e�B�[ ���[���ɏ��҂��Ă��܂��B�����܂����B
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_ROOM_CONFIRM;
	
	/**
	 * ID: 1728<br>
	 * Message: ���肪�p�[�e�B�[ �}�b�`�̏��҂ɉ����܂���ł����B
	 */
	public static final SystemMessageId PARTY_MATCHING_REQUEST_NO_RESPONSE;
	
	/**
	 * ID: 1729<br>
	 * Message: �A�����e���|�[�g���ł��邽�߁A�A���̃p�[�e�B�[�ɉ����ł��܂���B
	 */
	public static final SystemMessageId NOT_JOIN_CHANNEL_WHILE_TELEPORTING;
	
	/**
	 * ID: 1730<br>
	 * Message: �����A�J�f�~�[��n�݂���ɂ́A�����̃��x����5�ȏ�łȂ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN_ACADEMY;
	
	/**
	 * ID: 1731<br>
	 * Message: ������̂݌����A�J�f�~�[��n�݂��邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId ONLY_LEADER_CAN_CREATE_ACADEMY;
	
	/**
	 * ID: 1732<br>
	 * Message: �����A�J�f�~�[��n�݂���ɂ̓A�C�e���u���̏؁v���K�v�ł��B
	 */
	public static final SystemMessageId NEED_BLOODMARK_FOR_ACADEMY;
	
	/**
	 * ID: 1733<br>
	 * Message: �����A�J�f�~�[��n�݂���ɂ̓A�f�i������܂���B
	 */
	public static final SystemMessageId NEED_ADENA_FOR_ACADEMY;
	
	/**
	 * ID: 1734<br>
	 * Message: �����A�J�f�~�[�ɉ�������ɂ́A���̌����ɏ������Ă��炸�A2���]�E���I���Ă��Ȃ����x��40�ȉ��̃L�����N�^�[�łȂ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId ACADEMY_REQUIREMENTS;
	
	/**
	 * ID: 1735<br>
	 * Message: $s1�͌����A�J�f�~�[�̉��������ɍ����܂���B
	 */
	public static final SystemMessageId S1_DOESNOT_MEET_REQUIREMENTS_TO_JOIN_ACADEMY;
	
	/**
	 * ID: 1736<br>
	 * Message: �����A�J�f�~�[�̐����l���ɒB�������߁A�A�J�f�~�[���̉����󂯕t�����ł��܂���B
	 */
	public static final SystemMessageId ACADEMY_MAXIMUM;
	
	/**
	 * ID: 1737<br>
	 * Message: ���ݏ������Ă��錌���̌����A�J�f�~�[�͊J�݂���Ă��܂���B�����A�J�f�~�[���J�݂ł��܂��B
	 */
	public static final SystemMessageId CLAN_CAN_CREATE_ACADEMY;
	
	/**
	 * ID: 1738<br>
	 * Message: ���ݏ������Ă��錌���̌����A�J�f�~�[�͂��łɊJ�݂���Ă��܂��B
	 */
	public static final SystemMessageId CLAN_HAS_ALREADY_ESTABLISHED_A_CLAN_ACADEMY;
	
	/**
	 * ID: 1739<br>
	 * Message: �����A�J�f�~�[��n�݂��܂����B
	 */
	public static final SystemMessageId CLAN_ACADEMY_CREATE_CONFIRM;
	
	/**
	 * ID: 1740<br>
	 * Message: �����A�J�f�~�[������͂��Ă��������B
	 */
	public static final SystemMessageId ACADEMY_CREATE_ENTER_NAME;
	
	/**
	 * ID: 1741<br>
	 * Message: ���߂łƂ��������܂��I$s1�����̌����A�J�f�~�[���n�݂���܂����B
	 */
	public static final SystemMessageId THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED;
	
	/**
	 * ID: 1742<br>
	 * Message: �����A�J�f�~�[���������U���郁�b�Z�[�W��$s1�ɑ���܂��B
	 */
	public static final SystemMessageId ACADEMY_INVITATION_SENT_TO_S1;
	
	/**
	 * ID: 1743<br>
	 * Message: �����A�J�f�~�[��n�݂���ɂ́A�������x����5�ȏ�Ō����傪�A�C�e���u���̏؁vXX�܂��͈��ʂ̃A�f�i���x����Ȃ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId OPEN_ACADEMY_CONDITIONS;
	
	/**
	 * ID: 1744<br>
	 * Message: ���肪�������Ȃ��������߁A�����A�J�f�~�[�����̊��U���L�����Z������܂����B
	 */
	public static final SystemMessageId ACADEMY_JOIN_NO_RESPONSE;
	
	/**
	 * ID: 1745<br>
	 * Message: ���肪�����A�J�f�~�[�����̊��U�����ۂ��܂����B
	 */
	public static final SystemMessageId ACADEMY_JOIN_DECLINE;
	
	/**
	 * ID: 1746<br>
	 * Message: ���łɌ����A�J�f�~�[�ɉ������Ă��܂��B
	 */
	public static final SystemMessageId ALREADY_JOINED_ACADEMY;
	
	/**
	 * ID: 1747<br>
	 * Message: $s1��$s2���������̌����A�J�f�~�[�����̊��U�����Ă��܂����B���҂ɉ����܂����B
	 */
	public static final SystemMessageId JOIN_ACADEMY_REQUEST_BY_S1_FOR_CLAN_S2;
	
	/**
	 * ID: 1748<br>
	 * Message: �����A�J�f�~�[��$s1��2���]�E�ɐ��������������l$s2�|�C���g���l�����܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_GRADUATED_FROM_ACADEMY;
	
	/**
	 * ID: 1749<br>
	 * Message: ���߂łƂ��������܂��I�����A�J�f�~�[�𑲋Ƃ��A������������E�ނ��܂��B���Ǝ҂̓y�i���e�B�[�Ȃ��Ő����Ɍ������Ƃ��đ������ł��܂��B
	 */
	public static final SystemMessageId ACADEMY_MEMBERSHIP_TERMINATED;
	
	/**
	 * ID: 1750<br>
	 * Message: $c1�͎Q�������ɍ����܂���B$s2�̏��L�҂̓I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_JOIN_OLYMPIAD_POSSESSING_S2;
	
	/**
	 * ID: 1751<br>
	 * Message: �O�����h �}�X�^�[����L�O�A�C�e����^�����܂����B
	 */
	public static final SystemMessageId GRAND_MASTER_COMMEMORATIVE_ITEM;
	
	/**
	 * ID: 1752<br>
	 * Message: �����A�J�f�~�[���Ǝ҂��������Ƃ��ĉ������A���������l$s1�|�C���g�𓾂܂����B
	 */
	public static final SystemMessageId MEMBER_GRADUATED_EARNED_S1_REPU;
	
	/**
	 * ID: 1753<br>
	 * Message: �����A�J�f�~�[���ɂ͌����傪�ݒ肵�������ɂ��Y�������͗^�����܂���B
	 */
	public static final SystemMessageId CANT_TRANSFER_PRIVILEGE_TO_ACADEMY_MEMBER;
	
	/**
	 * ID: 1754<br>
	 * Message: �����A�J�f�~�[���ɂ͊Y���������^�����܂���B
	 */
	public static final SystemMessageId RIGHT_CANT_TRANSFERRED_TO_ACADEMY_MEMBER;
	
	/**
	 * ID: 1755<br>
	 * Message: ������$s1�̌P�����Ƃ���$s2���w�肵�܂����B
	 */
	public static final SystemMessageId S2_HAS_BEEN_DESIGNATED_AS_APPRENTICE_OF_CLAN_MEMBER_S1;
	
	/**
	 * ID: 1756<br>
	 * Message: �����A�J�f�~�[�P����$s1���ڑ����܂����B
	 */
	public static final SystemMessageId YOUR_APPRENTICE_S1_HAS_LOGGED_IN;
	
	/**
	 * ID: 1757<br>
	 * Message: �����A�J�f�~�[�P����$c1���ڑ��𒆒f���܂����B
	 */
	public static final SystemMessageId YOUR_APPRENTICE_C1_HAS_LOGGED_OUT;
	
	/**
	 * ID: 1758<br>
	 * Message: �����A�J�f�~�[�㌩�l$c1���ڑ����܂����B
	 */
	public static final SystemMessageId YOUR_SPONSOR_C1_HAS_LOGGED_IN;
	
	/**
	 * ID: 1759<br>
	 * Message: �����A�J�f�~�[�㌩�l$c1���ڑ��𒆒f���܂����B
	 */
	public static final SystemMessageId YOUR_SPONSOR_C1_HAS_LOGGED_OUT;
	
	/**
	 * ID: 1760<br>
	 * Message: ������$c1�̃^�C�g����$s2�Ɏw�肳��܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_TITLE_CHANGED_TO_S2;
	
	/**
	 * ID: 1761<br>
	 * Message: ������$c1�̌����N���X��$s2�Ɏw�肳��܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_PRIVILEGE_CHANGED_TO_S2;
	
	/**
	 * ID: 1762<br>
	 * Message: �P��������C���錠���͂���܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE;
	
	/**
	 * ID: 1763<br>
	 * Message: ������$c1�̌P����$s2���폜����܂����B
	 */
	public static final SystemMessageId S2_CLAN_MEMBER_C1_APPRENTICE_HAS_BEEN_REMOVED;
	
	/**
	 * ID: 1764<br>
	 * Message: �����A�J�f�~�[���̂ݑ����ł���A�C�e���ł��B
	 */
	public static final SystemMessageId EQUIP_ONLY_FOR_ACADEMY;
	
	/**
	 * ID: 1765<br>
	 * Message: �����A�J�f�~�[�𑲋Ƃ���Ƒ����ł��܂���B
	 */
	public static final SystemMessageId EQUIP_NOT_FOR_GRADUATES;
	
	/**
	 * ID: 1766<br>
	 * Message: $c1��$s2�Ƃ��Č��������\���𑗂�܂��B
	 */
	public static final SystemMessageId CLAN_JOIN_APPLICATION_SENT_TO_C1_IN_S2;
	
	/**
	 * ID: 1767<br>
	 * Message: $c1�Ɍ����A�J�f�~�[�։����\���𑗂�܂��B
	 */
	public static final SystemMessageId ACADEMY_JOIN_APPLICATION_SENT_TO_C1;
	
	/**
	 * ID: 1768<br>
	 * Message: $c1����$s2�����̌����A�J�f�~�[�Ƃ��ĉ����\���������܂����B�������܂����B
	 */
	public static final SystemMessageId JOIN_REQUEST_BY_C1_TO_CLAN_S2_ACADEMY;
	
	/**
	 * ID: 1769<br>
	 * Message: $c1����$s2��������$s3�R�m�c�Ƃ��ĉ������҂𑗂��܂����B�������܂����B
	 */
	public static final SystemMessageId JOIN_REQUEST_BY_C1_TO_ORDER_OF_KNIGHTS_S3_UNDER_CLAN_S2;
	
	/**
	 * ID: 1770<br>
	 * Message: ���������l��0�|�C���g�ȉ��ƂȂ萧����󂯂܂��B
	 */
	public static final SystemMessageId CLAN_REPU_0_MAY_FACE_PENALTIES;
	
	/**
	 * ID: 1771<br>
	 * Message: �������x��5�ȏ�ɂȂ茌�������l��~���邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId CLAN_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS;
	
	/**
	 * ID: 1772<br>
	 * Message: �����������U���Ŕs�k������̌����Ɍ��������l��$s1�|�C���g�D���܂����B
	 */
	public static final SystemMessageId CLAN_WAS_DEFEATED_IN_SIEGE_AND_LOST_S1_REPUTATION_POINTS;
	
	/**
	 * ID: 1773<br>
	 * Message: �����������U���ŏ���������̌��������l$s1�|�C���g��D���܂����B
	 */
	public static final SystemMessageId CLAN_VICTORIOUS_IN_SIEGE_AND_GAINED_S1_REPUTATION_POINTS;
	
	/**
	 * ID: 1774<br>
	 * Message: �����������퓬�^�A�W�g��V�K�Ɋl�����āA���������l��$s1�|�C���g��ɓ���܂����B
	 */
	public static final SystemMessageId CLAN_ACQUIRED_CONTESTED_CLAN_HALL_AND_S1_REPUTATION_POINTS;
	
	/**
	 * ID: 1775<br>
	 * Message: ���������̌�����$c1���ł̍ՓT1�ʃp�[�e�B�[�̈���Ƃ��Ċ��􂵁A���������l$s2�|�C���g����ɓ���܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_WAS_IN_HIGHEST_RANKED_PARTY_IN_FESTIVAL_OF_DARKNESS_AND_GAINED_S2_REPUTATION;
	
	/**
	 * ID: 1776<br>
	 * Message: ���������̌�����$c1���p�Y�ƂȂ茌�������l$s2�|�C���g����ɓ���܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_BECAME_HERO_AND_GAINED_S2_REPUTATION_POINTS;
	
	/**
	 * ID: 1777<br>
	 * Message: �����N�G�X�g�𖳎��N���A���A$s1�̌��������l���l�����܂����B
	 */
	public static final SystemMessageId CLAN_QUEST_COMPLETED_AND_S1_POINTS_GAINED;
	
	/**
	 * ID: 1778<br>
	 * Message: ����̌����֐퓬�^�A�W�g��D���A���������̌��������l$s1�|�C���g��D���܂����B
	 */
	public static final SystemMessageId OPPOSING_CLAN_CAPTURED_CLAN_HALL_AND_YOUR_CLAN_LOSES_S1_POINTS;
	
	/**
	 * ID: 1779<br>
	 * Message: �퓬�^�A�W�g��D��ꌌ�������l300�|�C���g�������܂����B
	 */
	public static final SystemMessageId CLAN_LOST_CONTESTED_CLAN_HALL_AND_300_POINTS;
	
	/**
	 * ID: 1780<br>
	 * Message: �����������퓬�^�A�W�g�𑈒D���đ���̌������猌�������l$s1�|�C���g��D���܂����B
	 */
	public static final SystemMessageId CLAN_CAPTURED_CONTESTED_CLAN_HALL_AND_S1_POINTS_DEDUCTED_FROM_OPPONENT;
	
	/**
	 * ID: 1781<br>
	 * Message: ���������̌��������l$s1�|�C���g�𓾂܂����B
	 */
	public static final SystemMessageId CLAN_ADDED_S1S_POINTS_TO_REPUTATION_SCORE;
	
	/**
	 * ID: 1782<br>
	 * Message: ���������̌�����$c1���E���ꂽ�̂ŁA����̌����ɖ����l��$s2�|�C���g�D���܂����B
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_WAS_KILLED_AND_S2_POINTS_DEDUCTED_FROM_REPUTATION;
	
	/**
	 * ID: 1783<br>
	 * Message: ����̌����̃v���C���[��|�����������̌��������l$s1�|�C���g��D���܂����B
	 */
	public static final SystemMessageId FOR_KILLING_OPPOSING_MEMBER_S1_POINTS_WERE_DEDUCTED_FROM_OPPONENTS;
	
	/**
	 * ID: 1784<br>
	 * Message: �������������Ɏ��s������̌����Ɍ��������l$s1�|�C���g��D���܂����B
	 */
	public static final SystemMessageId YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST_AND_ADDED_TO_OPPONENT;
	
	/**
	 * ID: 1785<br>
	 * Message: �����������ۗL���Ă���邪����������$s1�|�C���g�������܂����B
	 */
	public static final SystemMessageId YOUR_CLAN_HAS_BEEN_INITIALIZED_AND_S1_POINTS_LOST;
	
	/**
	 * ID: 1786<br>
	 * Message: �������Ă��錌�������Ɏ��s�����������l$s1�|�C���g�������܂����B
	 */
	public static final SystemMessageId YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST;
	
	/**
	 * ID: 1787<br>
	 * Message: ���������l$s1�|�C���g�����Ղ���܂����B
	 */
	public static final SystemMessageId S1_DEDUCTED_FROM_CLAN_REP;
	
	/**
	 * ID: 1788<br>
	 * Message: �����X�L��$s1����ɓ���܂����B
	 */
	public static final SystemMessageId CLAN_SKILL_S1_ADDED;
	
	/**
	 * ID: 1789<br>
	 * Message: ���������l��0�ȉ��ɂȂ����̂ŁA�����X�L������A�N�e�B�u�����܂��B
	 */
	public static final SystemMessageId REPUTATION_POINTS_0_OR_LOWER_CLAN_SKILLS_DEACTIVATED;
	
	/**
	 * ID: 1790<br>
	 * Message: �����ɖ����Ȃ����߁A�����̃��x�� �A�b�v�͂ł��܂���B
	 */
	public static final SystemMessageId FAILED_TO_INCREASE_CLAN_LEVEL;
	
	/**
	 * ID: 1791<br>
	 * Message: �����ɖ����Ȃ����߁A�P�ʕ����̑n�݂��ł��܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_MILITARY_UNIT;
	
	/**
	 * ID: 1792<br>
	 * Message: �n�݂���R�m�c�̊Ǘ��҂��w�肵�Ă��������B
	 */
	public static final SystemMessageId ASSIGN_MANAGER_FOR_ORDER_OF_KNIGHTS;
	
	/**
	 * ID: 1793<br>
	 * Message: $c1��$s2�̒c���ɔC������܂����B
	 */
	public static final SystemMessageId C1_HAS_BEEN_SELECTED_AS_CAPTAIN_OF_S2;
	
	/**
	 * ID: 1794<br>
	 * Message: $s1�R�m�c���n�݂���܂����B
	 */
	public static final SystemMessageId THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED;
	
	/**
	 * ID: 1795<br>
	 * Message: $s1�߉q�����n�݂���܂����B
	 */
	public static final SystemMessageId THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED;
	
	/**
	 * ID: 1796<br>
	 * Message: ���q�l�̃A�J�E���g�͕s���A�J�E���g���p�⑼�l�ɔ�Q��^����Q�[�� �v���C�Ȃǂ��F�߂�ꂽ���߁A�ꎞ�I�ɂ����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�ɒ��ڂ��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE17;
	
	/**
	 * ID: 1797<br>
	 * Message: $c1�̃��x����$s2�Ɏw�肳��܂����B
	 */
	public static final SystemMessageId C1_PROMOTED_TO_S2;
	
	/**
	 * ID: 1798<br>
	 * Message: ������̌�����$c1�ɈϏ����܂����B
	 */
	public static final SystemMessageId CLAN_LEADER_PRIVILEGES_HAVE_BEEN_TRANSFERRED_TO_C1;
	
	/**
	 * ID: 1799<br>
	 * Message: �ȑO��BOT�g�p�҂��`�F�b�N���ł��B���΂炭���Ă��炨�g�����������B
	 */
	public static final SystemMessageId SEARCHING_FOR_BOT_USERS_TRY_AGAIN_LATER;
	
	/**
	 * ID: 1800<br>
	 * Message: ���[�U�[$c1��BOT�g�p��������܂��B
	 */
	public static final SystemMessageId C1_HISTORY_USING_BOT;
	
	/**
	 * ID: 1801<br>
	 * Message: �̔��ł��܂���ł����B
	 */
	public static final SystemMessageId SELL_ATTEMPT_FAILED;
	
	/**
	 * ID: 1802<br>
	 * Message: ����Ɏ��s���܂����B
	 */
	public static final SystemMessageId TRADE_ATTEMPT_FAILED;
	
	/**
	 * ID: 1803<br>
	 * Message: �������ߐ؂�10���O����͎Q���\�����݂��ł��܂���B
	 */
	public static final SystemMessageId GAME_REQUEST_CANNOT_BE_MADE;
	
	/**
	 * ID: 1804<br>
	 * Message: ���q�l�̃A�J�E���g��7���ԁA�����p����������Ă��܂��B���q�l�̌���/�A�J�E���g����Ȃǂ̍s�ׂ��F�߂�ꂽ���߂ł��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE18;
	
	/**
	 * ID: 1805<br>
	 * Message: ���q�l�̃A�J�E���g��30���ԁA�����p����������Ă��܂��B���q�l�̌���/�A�J�E���g����Ȃǂ̍s�ׂ�2��F�߂�ꂽ���߂ł��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE19;
	
	/**
	 * ID: 1806<br>
	 * Message: ���q�l�̃A�J�E���g�͖������ł����p����������Ă��܂��B���q�l�̌���/�A�J�E���g����Ȃǂ̍s�ׂ�3��ȏ�F�߂�ꂽ���߂ł��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE20;
	
	/**
	 * ID: 1807<br>
	 * Message: ���q�l�̃A�J�E���g��30���ԁA�����p����������Ă��܂��B���q�l������������s�������Ƃ��F�߂�ꂽ���߂ł��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE21;
	
	/**
	 * ID: 1808<br>
	 * Message: ���q�l�̃A�J�E���g�͖������ł����p����������Ă��܂��B���q�l������/�A�J�E���g����Ȃǂ��s�����������F�߂�ꂽ���߂ł��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE22;
	
	/**
	 * ID: 1809<br>
	 * Message: ���q�l�̃A�J�E���g�͂��{�l�l�m�F�̎葱�����K�v�ł��B���l�[�W��II �����T�C�g�ihttp://lineage2.plaync.jp/�j���葱�����s���Ă��������B
	 */
	public static final SystemMessageId ACCOUNT_MUST_VERIFIED;
	
	/**
	 * ID: 1810<br>
	 * Message: ���ҋ��ۏ�ԂɂȂ�܂����B
	 */
	public static final SystemMessageId REFUSE_INVITATION_ACTIVATED;
	
	/**
	 * ID: 1812<br>
	 * Message: ���݁A���ҋ��ۏ�ԂȂ̂ŏ��҂ł��܂���B
	 */
	public static final SystemMessageId REFUSE_INVITATION_CURRENTLY_ACTIVE;
	
	/**
	 * ID: 1813<br>
	 * Message: $s1�̎g�p�\���Ԃ͎c��$s2���Ԃł��B
	 */
	public static final SystemMessageId THERE_IS_S1_HOUR_AND_S2_MINUTE_LEFT_OF_THE_FIXED_USAGE_TIME;
	
	/**
	 * ID: 1814<br>
	 * Message: $s1�̎g�p�\���Ԃ͎c��$s2���ł��B
	 */
	public static final SystemMessageId S2_MINUTE_OF_USAGE_TIME_ARE_LEFT_FOR_S1;
	
	/**
	 * ID: 1815<br>
	 * Message: $s1�G���A��$s2�������܂����B
	 */
	public static final SystemMessageId S2_WAS_DROPPED_IN_THE_S1_REGION;
	
	/**
	 * ID: 1816<br>
	 * Message: $s1�G���A��$s2�̏��L�҂�����܂����B
	 */
	public static final SystemMessageId THE_OWNER_OF_S2_HAS_APPEARED_IN_THE_S1_REGION;
	
	/**
	 * ID: 1817<br>
	 * Message: $s1�G���A��$s2�̏��L�҂����O�C�����܂����B
	 */
	public static final SystemMessageId S2_OWNER_HAS_LOGGED_INTO_THE_S1_REGION;
	
	/**
	 * ID: 1818<br>
	 * Message: $s1�����ł��܂����B
	 */
	public static final SystemMessageId S1_HAS_DISAPPEARED;
	
	/**
	 * ID: 1819<br>
	 * Message: $s1�G���A�ɂ��鉽�҂�����$s2�̎׈��ȋC�z�������܂��B
	 */
	public static final SystemMessageId EVIL_FROM_S2_IN_S1;
	
	/**
	 * ID: 1820<br>
	 * Message: $s1�͌��ݖ����Ă��܂��B
	 */
	public static final SystemMessageId S1_CURRENTLY_SLEEP;
	
	/**
	 * ID: 1821<br>
	 * Message: $s1�G���A��$s2�̎׈��ȋC�z�������܂��B
	 */
	public static final SystemMessageId S2_EVIL_PRESENCE_FELT_IN_S1;
	
	/**
	 * ID: 1822<br>
	 * Message: $s1�͕��󂳂�Ă��܂��B
	 */
	public static final SystemMessageId S1_SEALED;
	
	/**
	 * ID: 1823<br>
	 * Message: �A�W�g��o�^�͒��ߐ؂��܂����B
	 */
	public static final SystemMessageId CLANHALL_WAR_REGISTRATION_PERIOD_ENDED;
	
	/**
	 * ID: 1824<br>
	 * Message: �A�W�g��ɓo�^���܂����B�A�W�g�̓��ɂ��鋣�Z��ֈړ����A�������Ă��������B
	 */
	public static final SystemMessageId REGISTERED_FOR_CLANHALL_WAR;
	
	/**
	 * ID: 1825<br>
	 * Message: �A�W�g��ɓo�^�ł��܂���ł����B���̋@��ɂ��z�����������B
	 */
	public static final SystemMessageId CLANHALL_WAR_REGISTRATION_FAILED;
	
	/**
	 * ID: 1826<br>
	 * Message: �J�n�܂ł���$s1���ł��B�Q���҂͋}���ŃA�W�g�̓��ɂ��鋣�Z��Ɉړ����Ă��������B
	 */
	public static final SystemMessageId CLANHALL_WAR_BEGINS_IN_S1_MINUTES;
	
	/**
	 * ID: 1827<br>
	 * Message: �J�n�܂ł���$s1���ł��B�Q���҂͋��Z��̒��ɓ����Ă��������B
	 */
	public static final SystemMessageId CLANHALL_WAR_BEGINS_IN_S1_MINUTES_ENTER_NOW;
	
	/**
	 * ID: 1828<br>
	 * Message: �J�n�܂ł���$s1�b�ł��B
	 */
	public static final SystemMessageId CLANHALL_WAR_BEGINS_IN_S1_SECONDS;
	
	/**
	 * ID: 1829<br>
	 * Message: �A���̍ő�p�[�e�B�[�����z�����̂ŁA�V�����p�[�e�B�[���������܂���B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_FULL;
	
	/**
	 * ID: 1830<br>
	 * Message: $c1���p�[�e�B�[ ���[���֏��҂ł��܂���B�ҋ@�҃��X�g���X�V���Ă��������B
	 */
	public static final SystemMessageId C1_NOT_ALLOWED_INVITE_TO_PARTY_ROOM;
	
	/**
	 * ID: 1831<br>
	 * Message: $c1�̓p�[�e�B�[ ���[���̏����ɍ����܂���B�ҋ@�҃��X�g���X�V���Ă��������B
	 */
	public static final SystemMessageId C1_NOT_MEET_CONDITIONS_FOR_PARTY_ROOM;
	
	/**
	 * ID: 1832<br>
	 * Message: �p�[�e�B�[ ���[���ւ̓��[�����[�_�[�̂ݏ��҂ł��܂��B
	 */
	public static final SystemMessageId ONLY_ROOM_LEADER_CAN_INVITE;
	
	/**
	 * ID: 1833<br>
	 * Message: $s1��S���̂Ă܂��B��낵���ł����B
	 */
	public static final SystemMessageId CONFIRM_DROP_ALL_OF_S1;
	
	/**
	 * ID: 1834<br>
	 * Message: �p�[�e�B�[ ���[���̒���������̂��߁A����ȏ㏵�҂ł��܂���B
	 */
	public static final SystemMessageId PARTY_ROOM_FULL;
	
	/**
	 * ID: 1835<br>
	 * Message: $s1�̒���������̂��߁A�V���Ȍ����������������邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId S1_CLAN_IS_FULL;
	
	/**
	 * ID: 1836<br>
	 * Message: 2���]�E���I���Ă���̂Ō����A�J�f�~�[�̉��������ɍ����܂���B
	 */
	public static final SystemMessageId CANNOT_JOIN_ACADEMY_AFTER_2ND_OCCUPATION;
	
	/**
	 * ID: 1837<br>
	 * Message: $c1��$s2����������$s3�߉q���ւ̉��������߂Ă��܂��B�������܂����B
	 */
	public static final SystemMessageId C1_SENT_INVITATION_TO_ROYAL_GUARD_S3_OF_CLAN_S2;
	
	/**
	 * ID: 1838<br>
	 * Message: 1. �N�[�|���̓L�����N�^�[������1���̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId COUPON_ONCE_PER_CHARACTER;
	
	/**
	 * ID: 1839<br>
	 * Message: 2. �g�p�ς݂̃V���A���ԍ��͍Ăюg�p�ł��܂���B
	 */
	public static final SystemMessageId SERIAL_MAY_USED_ONCE;
	
	/**
	 * ID: 1840<br>
	 * Message: 3. �V���A���ԍ��̓��͂�5��ȏ�ԈႦ���ꍇ�A��莞��\n �o�ߌ�ɍēx���͂����݂Ă��������B
	 */
	public static final SystemMessageId SERIAL_INPUT_INCORRECT;
	
	/**
	 * ID: 1841<br>
	 * Message: �Q���\�����݂̌�����������Ȃ����߁A�A�W�g��̓L�����Z������܂����B
	 */
	public static final SystemMessageId CLANHALL_WAR_CANCELLED;
	
	/**
	 * ID: 1842<br>
	 * Message: $c1��$s2���珢����\�����݂܂����B�����ɉ����܂����B
	 */
	public static final SystemMessageId C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT;
	
	/**
	 * ID: 1843<br>
	 * Message: $c1�͐퓬���̂��ߏ����ł��܂���B
	 */
	public static final SystemMessageId C1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED;
	
	/**
	 * ID: 1844<br>
	 * Message: $c1�͌��ݎ��S��Ԃ̂��ߏ����ł��܂���B
	 */
	public static final SystemMessageId C1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED;
	
	/**
	 * ID: 1845<br>
	 * Message: �p�Y�p����͔j��ł��܂���B
	 */
	public static final SystemMessageId HERO_WEAPONS_CANT_DESTROYED;
	
	/**
	 * ID: 1846<br>
	 * Message: ��蕨�Ƃ̋���������߂��Ă���̂ŁA����ł��܂���B
	 */
	public static final SystemMessageId TOO_FAR_AWAY_FROM_FENRIR_TO_MOUNT;
	
	/**
	 * ID: 1847<br>
	 * Message: ����$s1�̋����ނ�܂����B
	 */
	public static final SystemMessageId CAUGHT_FISH_S1_LENGTH;
	
	/**
	 * ID: 1848<br>
	 * Message: �啨��ނ����̂Ń����L���O�ɓo�^����܂����B
	 */
	public static final SystemMessageId REGISTERED_IN_FISH_SIZE_RANKING;
	
	/**
	 * ID: 1849<br>
	 * Message: $s1��S���̂Ă܂��B��낵���ł����B
	 */
	public static final SystemMessageId CONFIRM_DISCARD_ALL_OF_S1;
	
	/**
	 * ID: 1850<br>
	 * Message: �R�m�c���ɔC���ł��܂���B
	 */
	public static final SystemMessageId CAPTAIN_OF_ORDER_OF_KNIGHTS_CANNOT_BE_APPOINTED;
	
	/**
	 * ID: 1851<br>
	 * Message: �߉q�����ɔC���ł��܂���B
	 */
	public static final SystemMessageId CAPTAIN_OF_ROYAL_GUARD_CANNOT_BE_APPOINTED;
	
	/**
	 * ID: 1852<br>
	 * Message: ���������l������Ȃ��̂ŃX�L�����K���ł��܂���ł����B
	 */
	public static final SystemMessageId ACQUIRE_SKILL_FAILED_BAD_CLAN_REP_SCORE;
	
	/**
	 * ID: 1853<br>
	 * Message: ������ނ̐��ʐ��A�C�e���𓯎��Ƀg���[�h�ł��܂���B
	 */
	public static final SystemMessageId CANT_EXCHANGE_QUANTITY_ITEMS_OF_SAME_TYPE;
	
	/**
	 * ID: 1854<br>
	 * Message: �A�C�e���ϊ��ɐ������܂����B
	 */
	public static final SystemMessageId ITEM_CONVERTED_SUCCESSFULLY;
	
	/**
	 * ID: 1855<br>
	 * Message: ���̕����Ɩ��̂��d�����܂��B�ʂ̖��O����͂��Ă��������B
	 */
	public static final SystemMessageId ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME;
	
	/**
	 * ID: 1856<br>
	 * Message: ���肪$s1�̏��L�҂ɂȂ����̂ŁA�I�����s�A�[�h���L�����Z������܂����B
	 */
	public static final SystemMessageId OPPONENT_POSSESSES_S1_OLYMPIAD_CANCELLED;
	
	/**
	 * ID: 1857<br>
	 * Message: $c1��$s2�̏��L�҂ɂȂ��Ă��邽�߁A�I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_OWNS_S2_AND_CANNOT_PARTICIPATE_IN_OLYMPIAD;
	
	/**
	 * ID: 1858<br>
	 * Message: $c1�͎��S��Ԃł��邽�߁A�I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_OLYMPIAD_WHILE_DEAD;
	
	/**
	 * ID: 1859<br>
	 * Message: ��x�Ɉړ��\�Ȑ��ʂ𒴂��܂����B
	 */
	public static final SystemMessageId EXCEEDED_QUANTITY_FOR_MOVED;
	
	/**
	 * ID: 1860<br>
	 * Message: ���������l������܂���B
	 */
	public static final SystemMessageId THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW;
	
	/**
	 * ID: 1861<br>
	 * Message: �����G���T�C�����폜���܂����B
	 */
	public static final SystemMessageId CLAN_CREST_HAS_BEEN_DELETED;
	
	/**
	 * ID: 1862<br>
	 * Message: ���������l��0�ȏ�ɂȂ����̂Ō����X�L�����A�N�e�B�u�����܂��B
	 */
	public static final SystemMessageId CLAN_SKILLS_WILL_BE_ACTIVATED_SINCE_REPUTATION_IS_0_OR_HIGHER;
	
	/**
	 * ID: 1863<br>
	 * Message: $c1���A�C�e�����w�������̂ŁA���������l��$s2����������܂��B
	 */
	public static final SystemMessageId C1_PURCHASED_CLAN_ITEM_REDUCING_S2_REPU_POINTS;
	
	/**
	 * ID: 1864<br>
	 * Message: �y�b�g�܂��͏����b���ُ��ԂɂȂ�A���߂𕷂��܂���B
	 */
	public static final SystemMessageId PET_REFUSING_ORDER;
	
	/**
	 * ID: 1865<br>
	 * Message: �y�b�g�܂��͏����b���ُ��Ԃł��B
	 */
	public static final SystemMessageId PET_IN_STATE_OF_DISTRESS;
	
	/**
	 * ID: 1866<br>
	 * Message: $s1��MP�������܂����B
	 */
	public static final SystemMessageId MP_REDUCED_BY_S1;
	
	/**
	 * ID: 1867<br>
	 * Message: �����MP $s1�قǂ������܂����B
	 */
	public static final SystemMessageId YOUR_OPPONENTS_MP_WAS_REDUCED_BY_S1;
	
	/**
	 * ID: 1868<br>
	 * Message: �A�C�e���g�p���̓g���[�h�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_EXCHANCE_USED_ITEM;
	
	/**
	 * ID: 1869<br>
	 * Message: $c1���w������A���̃}�X�^�[ �p�[�e�B�[�ɁA�A�C�e�����z�̌������^�����܂����B
	 */
	public static final SystemMessageId C1_GRANTED_MASTER_PARTY_LOOTING_RIGHTS;
	
	/**
	 * ID: 1870<br>
	 * Message: ���łɃA�C�e�����z�̌������^����ꂽ�A���`�����l�������݂��܂��B
	 */
	public static final SystemMessageId COMMAND_CHANNEL_WITH_LOOTING_RIGHTS_EXISTS;
	
	/**
	 * ID: 1871<br>
	 * Message: $c1���������珜�����܂����B
	 */
	public static final SystemMessageId CONFIRM_DISMISS_C1_FROM_CLAN;
	
	/**
	 * ID: 1872<br>
	 * Message: �l���[�U�[�̎c�莞�Ԃ͂���$s1���� $s2���ł��B
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT;
	
	/**
	 * ID: 1873<br>
	 * Message: �l�b�g�J�t�F��ʂ̎c�莞�Ԃ͂���$s1���� $s2���ł��B
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_FOR_THIS_PCCAFE;
	
	/**
	 * ID: 1874<br>
	 * Message: �l���[�U�[�̎c�莞�Ԃ͂���$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_FOR_THIS_USER;
	
	/**
	 * ID: 1875<br>
	 * Message: �l�b�g�J�t�F��ʂ̎c�莞�Ԃ͂���$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_FOR_THIS_PCCAFE;
	
	/**
	 * ID: 1876<br>
	 * Message: $s1��������E�ނ��܂����B
	 */
	public static final SystemMessageId CONFIRM_LEAVE_S1_CLAN;
	
	/**
	 * ID: 1877<br>
	 * Message: �I���܂ł���$s1���ł��B
	 */
	public static final SystemMessageId GAME_WILL_END_IN_S1_MINUTES;
	
	/**
	 * ID: 1878<br>
	 * Message: �I���܂ł���$s1�b�ł��B
	 */
	public static final SystemMessageId GAME_WILL_END_IN_S1_SECONDS;
	
	/**
	 * ID: 1879<br>
	 * Message: $s1����A���Z��̊O�Ƀe���|�[�g���܂��B
	 */
	public static final SystemMessageId IN_S1_MINUTES_TELEPORTED_OUTSIDE_OF_GAME_ARENA;
	
	/**
	 * ID: 1880<br>
	 * Message: $s1�b��A���Z��̊O�Ƀe���|�[�g���܂��B
	 */
	public static final SystemMessageId IN_S1_SECONDS_TELEPORTED_OUTSIDE_OF_GAME_ARENA;
	
	/**
	 * ID: 1881<br>
	 * Message: $s1�b��ɗ\�I���X�^�[�g���܂��B�������Ă��������B
	 */
	public static final SystemMessageId PRELIMINARY_MATCH_BEGIN_IN_S1_SECONDS;
	
	/**
	 * ID: 1882<br>
	 * Message: ���݂��̃T�[�o�[�ɂ̓L�����N�^�[�𐶐��ł��܂���B
	 */
	public static final SystemMessageId CHARACTERS_NOT_CREATED_FROM_THIS_SERVER;
	
	/**
	 * ID: 1883<br>
	 * Message: ���L�������͓��D�������̂�����܂���B
	 */
	public static final SystemMessageId NO_OFFERINGS_OWN_OR_MADE_BID_FOR;
	
	/**
	 * ID: 1884<br>
	 * Message: �l�b�g�J�t�F �N�[�|���̃V���A���ԍ�����͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_PCROOM_SERIAL_NUMBER;
	
	/**
	 * ID: 1885<br>
	 * Message: �V���A���ԍ�����͂ł��܂���B$s1����ɍēx���݂Ă��������B
	 */
	public static final SystemMessageId SERIAL_NUMBER_CANT_ENTERED;
	
	/**
	 * ID: 1886<br>
	 * Message: ���łɎg�p���ꂽ�V���A���ԍ��ł��B
	 */
	public static final SystemMessageId SERIAL_NUMBER_ALREADY_USED;
	
	/**
	 * ID: 1887<br>
	 * Message: �V���A���ԍ�������������܂���B$s1����͂Ɏ��s�����̂ŁA����$s2��̃`�����X������܂��B
	 */
	public static final SystemMessageId SERIAL_NUMBER_ENTERING_FAILED;
	
	/**
	 * ID: 1888<br>
	 * Message: �V���A���ԍ�������������܂���B5����͂Ɏ��s���܂����B4���Ԍ�ɍēx���݂Ă��������B
	 */
	public static final SystemMessageId SERIAL_NUMBER_ENTERING_FAILED_5_TIMES;
	
	/**
	 * ID: 1889<br>
	 * Message: ���߂łƂ��������܂��B$s1���󂯎��܂����B
	 */
	public static final SystemMessageId CONGRATULATIONS_RECEIVED_S1;
	
	/**
	 * ID: 1890<br>
	 * Message: ���̃N�[�|���͂��łɎg�p�ς݂Ȃ̂ŃV���A���ԍ�����͂ł��܂���B
	 */
	public static final SystemMessageId ALREADY_USED_COUPON_NOT_USE_SERIAL_NUMBER;
	
	/**
	 * ID: 1891<br>
	 * Message: �l���X�܂��͌l�H�[���̓A�C�e�����g�p�ł��܂���B
	 */
	public static final SystemMessageId NOT_USE_ITEMS_IN_PRIVATE_STORE;
	
	/**
	 * ID: 1892<br>
	 * Message: �ȑO�̃o�[�W�����̃��v���C �t�@�C���͍Đ��ł��܂���B
	 */
	public static final SystemMessageId REPLAY_FILE_PREVIOUS_VERSION_CANT_PLAYED;
	
	/**
	 * ID: 1893<br>
	 * Message: �Đ��ł��Ȃ��t�@�C���ł��B
	 */
	public static final SystemMessageId FILE_CANT_REPLAYED;
	
	/**
	 * ID: 1894<br>
	 * Message: ���e�d�ʂ𒴂��邽�߁A�T�u �N���X�̒ǉ���ύX���ł��܂���B
	 */
	public static final SystemMessageId NOT_SUBCLASS_WHILE_OVERWEIGHT;
	
	/**
	 * ID: 1895<br>
	 * Message: $c1�͌��ݏ����ł��Ȃ��n��ɂ��܂��B
	 */
	public static final SystemMessageId C1_IN_SUMMON_BLOCKING_AREA;
	
	/**
	 * ID: 1896<br>
	 * Message: $c1�͂��łɏ������ł��B
	 */
	public static final SystemMessageId C1_ALREADY_SUMMONED;
	
	/**
	 * ID: 1897<br>
	 * Message: �����ɕK�v�ȃA�C�e��$s1������܂���B
	 */
	public static final SystemMessageId S1_REQUIRED_FOR_SUMMONING;
	
	/**
	 * ID: 1898<br>
	 * Message: $c1�̓g���[�h���l���X���s���Ă��邽�ߏ����ł��܂���B
	 */
	public static final SystemMessageId C1_CURRENTLY_TRADING_OR_OPERATING_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED;
	
	/**
	 * ID: 1899<br>
	 * Message: ���ݑ��l�������ł��Ȃ��ꏊ�ɂ��邽�߁A����������ł��܂���B
	 */
	public static final SystemMessageId YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING;
	
	/**
	 * ID: 1900<br>
	 * Message: $c1���p�[�e�B�[ ���[���ɓ������܂����B
	 */
	public static final SystemMessageId C1_ENTERED_PARTY_ROOM;
	
	/**
	 * ID: 1901<br>
	 * Message: $c1����p�[�e�B�[ ���[���ɏ��҂���܂����B
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_ROOM;
	
	/**
	 * ID: 1902<br>
	 * Message: �A�C�e���̃O���[�h����v���Ȃ����ߎg�p�ł��܂���B
	 */
	public static final SystemMessageId INCOMPATIBLE_ITEM_GRADE;
	
	/**
	 * ID: 1903<br>
	 * Message: NCOTP�ɂ��\�����݂̏ꍇ�͌g�ѓd�b��\nNCOTP���s���\�����ꂽ\nNCOTP �p�X���[�h��1���ȓ��ɓ��͂��A\n���\�����݂łȂ��ꍇ�͋󗓂̂܂�\n���O�C�����s���Ă��������B
	 */
	public static final SystemMessageId NCOTP;
	
	/**
	 * ID: 1904<br>
	 * Message: �����b��y�b�g������������Ԃ���́A�T�u �N���X�̒ǉ���ύX���ł��܂���B
	 */
	public static final SystemMessageId CANT_SUBCLASS_WITH_SUMMONED_SERVITOR;
	
	/**
	 * ID: 1905<br>
	 * Message: $s1������$c2��$s3������$c4�Ɍ�ւ��܂��B
	 */
	public static final SystemMessageId S2_OF_S1_WILL_REPLACED_WITH_S4_OF_S3;
	
	/**
	 * ID: 1906<br>
	 * Message: �����ύX���镔����I�����܂��B
	 */
	public static final SystemMessageId SELECT_COMBAT_UNIT;
	
	/**
	 * ID: 1907<br>
	 * Message: �I��Ώۂ𑼂̐l�ƌ�ւ���ꍇ\n��ւ���Ώۂ�I�����܂��B
	 */
	public static final SystemMessageId SELECT_CHARACTER_WHO_WILL;
	
	/**
	 * ID: 1908<br>
	 * Message: $c1�͏����ł��Ȃ���Ԃł��B
	 */
	public static final SystemMessageId C1_STATE_FORBIDS_SUMMONING;
	
	/**
	 * ID: 1909<br>
	 * Message: ==<�ŋ߈�T�Ԃ̌����A�J�f�~�[���Ǝ�>==
	 */
	public static final SystemMessageId ACADEMY_LIST_HEADER;
	
	/**
	 * ID: 1910<br>
	 * Message: ���ƎҁF$c1
	 */
	public static final SystemMessageId GRADUATES_C1;
	
	/**
	 * ID: 1911<br>
	 * Message: �I�����s�A�[�h�ɎQ�����̃��[�U�[�͏����ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_IN_OLYMPIAD;
	
	/**
	 * ID: 1912<br>
	 * Message: NCOTP�ɂ��\�����݂̕��̂ݓ��́B
	 */
	public static final SystemMessageId NCOTP2;
	
	/**
	 * ID: 1913<br>
	 * Message: $s1�̍Ďg�p���Ԃ͎c��$s2���ł��B
	 */
	public static final SystemMessageId TIME_FOR_S1_IS_S2_MINUTES_REMAINING;
	
	/**
	 * ID: 1914<br>
	 * Message: $s1�̍Ďg�p���Ԃ͎c��$s2�b�ł��B
	 */
	public static final SystemMessageId TIME_FOR_S1_IS_S2_SECONDS_REMAINING;
	
	/**
	 * ID: 1915<br>
	 * Message: ���Z�I�����Ԃ܂Ŏc��$s1�b�ł��B
	 */
	public static final SystemMessageId GAME_ENDS_IN_S1_SECONDS;
	
	/**
	 * ID: 1916<br>
	 * Message: ���������炷�����x��$s1���K�p����܂��B
	 */
	public static final SystemMessageId DEATH_PENALTY_LEVEL_S1_ADDED;
	
	/**
	 * ID: 1917<br>
	 * Message: ���������炷���������܂����B
	 */
	public static final SystemMessageId DEATH_PENALTY_LIFTED;
	
	/**
	 * ID: 1918<br>
	 * Message: �y�b�g�̃��x�������߂��邽�߁A�R���g���[���ł��܂���B
	 */
	public static final SystemMessageId PET_TOO_HIGH_TO_CONTROL;
	
	/**
	 * ID: 1919<br>
	 * Message: �I�����s�A�[�h�̎Q���o�^�����ߐ؂��܂����B
	 */
	public static final SystemMessageId OLYMPIAD_REGISTRATION_PERIOD_ENDED;
	
	/**
	 * ID: 1920<br>
	 * Message: �A�J�E���g�͋x���A�J�E���g��Ԃł��B�����ԃQ�[���ɐڑ����Ȃ��ꍇ�͋x���A�J�E���g��ԂƂȂ�A�����T�C�g�ihttp://lineage2.plaync.jp/�j�ŃQ�[���ڑ����\�ȏ�ԂɕύX�ł��܂��B
	 */
	public static final SystemMessageId ACCOUNT_INACTIVITY;
	
	/**
	 * ID: 1921<br>
	 * Message: $s1���E�C���s���Ă���$s2����$s3�����o�߂��܂����B
	 */
	public static final SystemMessageId S2_HOURS_S3_MINUTES_SINCE_S1_KILLED;
	
	/**
	 * ID: 1922<br>
	 * Message: $s1���ۈ���E�C���s�킸�A���ł��܂����B
	 */
	public static final SystemMessageId S1_FAILED_KILLING_EXPIRED;
	
	/**
	 * ID: 1923<br>
	 * Message: �{�얂�p�t�F�N���� �Q�[�g����������܂����I
	 */
	public static final SystemMessageId COURT_MAGICIAN_CREATED_PORTAL;
	
	/**
	 * ID: 1924<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (���Â̓��t��)
	 */
	public static final SystemMessageId LOC_PRIMEVAL_ISLE_S1_S2_S3;
	
	/**
	 * ID: 1925<br>
	 * Message: �헐�̕���̉e���ɂ�菢���ł��܂���B
	 */
	public static final SystemMessageId SEAL_OF_STRIFE_FORBIDS_SUMMONING;
	
	/**
	 * ID: 1926<br>
	 * Message: ������\�����ޑ��肪���܂���B
	 */
	public static final SystemMessageId THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL;
	
	/**
	 * ID: 1927<br>
	 * Message: $c1��1��1�ł̌�����\�����݂܂��B
	 */
	public static final SystemMessageId C1_HAS_BEEN_CHALLENGED_TO_A_DUEL;
	
	/**
	 * ID: 1928<br>
	 * Message: $c1�̃p�[�e�B�[�Ƀp�[�e�B�[���m�ł̌�����\�����݂܂��B
	 */
	public static final SystemMessageId C1_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL;
	
	/**
	 * ID: 1929<br>
	 * Message: $c1��1��1�ł̌������󂯓���܂����B���΂炭����ƌ������n�܂�܂��B
	 */
	public static final SystemMessageId C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1930<br>
	 * Message: $c1�����1��1�ł̌����̐\�����݂��󂯓���܂����B���΂炭����ƌ������n�܂�܂��B
	 */
	public static final SystemMessageId YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1931<br>
	 * Message: $c1��1��1�ł̌��������ۂ��܂����B
	 */
	public static final SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL;
	
	/**
	 * ID: 1932<br>
	 * Message: $c1�������̐\�����݂����ۂ��܂����B
	 */
	public static final SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL2;
	
	/**
	 * ID: 1933<br>
	 * Message: $c1�̃p�[�e�B�[����̃p�[�e�B�[���m�ł̌����̐\�����݂��󂯓���܂����B���΂炭����ƌ������n�܂�܂��B
	 */
	public static final SystemMessageId YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_PARTY_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1934<br>
	 * Message: $c1���p�[�e�B�[���m�ł̌������󂯓���܂����B���΂炭����ƌ������n�܂�܂��B
	 */
	public static final SystemMessageId S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1935<br>
	 * Message: $c1���p�[�e�B�[���m�ł̌��������ۂ��܂����B
	 */
	public static final SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL;
	
	/**
	 * ID: 1936<br>
	 * Message: ����̃p�[�e�B�[�������̐\�����݂����ۂ��܂����B
	 */
	public static final SystemMessageId THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL;
	
	/**
	 * ID: 1937<br>
	 * Message: ���肪�p�[�e�B�[��Ԃł͂Ȃ����߁A�p�[�e�B�[���m�ł̌����̐\�����݂��ł��܂���B
	 */
	public static final SystemMessageId SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY;
	
	/**
	 * ID: 1938<br>
	 * Message: $c1��1��1�ł̌�����\�����݂܂����B
	 */
	public static final SystemMessageId C1_HAS_CHALLENGED_YOU_TO_A_DUEL;
	
	/**
	 * ID: 1939<br>
	 * Message: $c1�̃p�[�e�B�[���p�[�e�B�[���m�ł̌�����\�����݂܂����B
	 */
	public static final SystemMessageId C1_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL;
	
	/**
	 * ID: 1940<br>
	 * Message: ����������Ȃ����߁A������\�����߂܂���B
	 */
	public static final SystemMessageId YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME;
	
	/**
	 * ID: 1941<br>
	 * Message: �������s����ꏊ�ł͂���܂���B
	 */
	public static final SystemMessageId NO_PLACE_FOR_DUEL;
	
	/**
	 * ID: 1942<br>
	 * Message: ����̃p�[�e�B�[���������s�����Ԃł͂���܂���B
	 */
	public static final SystemMessageId THE_OPPOSING_PARTY_IS_CURRENTLY_UNABLE_TO_ACCEPT_A_CHALLENGE_TO_A_DUEL;
	
	/**
	 * ID: 1943<br>
	 * Message: ����̃p�[�e�B�[���������s����ꏊ�ɂ��܂���B
	 */
	public static final SystemMessageId THE_OPPOSING_PARTY_IS_AT_BAD_LOCATION_FOR_A_DUEL;
	
	/**
	 * ID: 1944<br>
	 * Message: ���΂炭����ƌ�����Ɉړ����܂��B
	 */
	public static final SystemMessageId IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE;
	
	/**
	 * ID: 1945<br>
	 * Message: $s1�b��Ɍ������n�܂�܂��B
	 */
	public static final SystemMessageId THE_DUEL_WILL_BEGIN_IN_S1_SECONDS;
	
	/**
	 * ID: 1946<br>
	 * Message: $c1��1��1�ł̌�����\�����݂܂����B�󂯓���܂����B
	 */
	public static final SystemMessageId C1_CHALLENGED_YOU_TO_A_DUEL;
	
	/**
	 * ID: 1947<br>
	 * Message: $c1�̃p�[�e�B�[���p�[�e�B�[���m�ł̌�����\�����݂܂����B�󂯓���܂����B
	 */
	public static final SystemMessageId C1_CHALLENGED_YOU_TO_A_PARTY_DUEL;
	
	/**
	 * ID: 1948<br>
	 * Message: ������ $s1�b��Ɏn�܂�܂��B
	 */
	public static final SystemMessageId THE_DUEL_WILL_BEGIN_IN_S1_SECONDS2;
	
	/**
	 * ID: 1949<br>
	 * Message: �����J�n�I
	 */
	public static final SystemMessageId LET_THE_DUEL_BEGIN;
	
	/**
	 * ID: 1950<br>
	 * Message: $c1�������ɏ������܂����B
	 */
	public static final SystemMessageId C1_HAS_WON_THE_DUEL;
	
	/**
	 * ID: 1951<br>
	 * Message: $c1�̃p�[�e�B�[�������ɏ������܂����B
	 */
	public static final SystemMessageId C1_PARTY_HAS_WON_THE_DUEL;
	
	/**
	 * ID: 1952<br>
	 * Message: ���������ł��B
	 */
	public static final SystemMessageId THE_DUEL_HAS_ENDED_IN_A_TIE;
	
	/**
	 * ID: 1953<br>
	 * Message: $c1�����i�ƂȂ�$s2���������܂����B
	 */
	public static final SystemMessageId SINCE_C1_WAS_DISQUALIFIED_S2_HAS_WON;
	
	/**
	 * ID: 1954<br>
	 * Message: $c1�̃p�[�e�B�[�����i�ƂȂ�$s2�̃p�[�e�B�[���������܂����B
	 */
	public static final SystemMessageId SINCE_C1_PARTY_WAS_DISQUALIFIED_S2_PARTY_HAS_WON;
	
	/**
	 * ID: 1955<br>
	 * Message: $c1�������������������$s2���������܂����B
	 */
	public static final SystemMessageId SINCE_C1_WITHDREW_FROM_THE_DUEL_S2_HAS_WON;
	
	/**
	 * ID: 1956<br>
	 * Message: $c1�̃p�[�e�B�[�������������������$s2�̃p�[�e�B�[���������܂����B
	 */
	public static final SystemMessageId SINCE_C1_PARTY_WITHDREW_FROM_THE_DUEL_S2_PARTY_HAS_WON;
	
	/**
	 * ID: 1957<br>
	 * Message: ���B����A�C�e����u���Ă��������B
	 */
	public static final SystemMessageId SELECT_THE_ITEM_TO_BE_AUGMENTED;
	
	/**
	 * ID: 1958<br>
	 * Message: ���B�ނ�u���Ă��������B
	 */
	public static final SystemMessageId SELECT_THE_CATALYST_FOR_AUGMENTATION;
	
	/**
	 * ID: 1959<br>
	 * Message: $s1 $s2��u���Ă��������B
	 */
	public static final SystemMessageId REQUIRES_S1_S2;
	
	/**
	 * ID: 1960<br>
	 * Message: �A�C�e�����K���Ă��܂���B
	 */
	public static final SystemMessageId THIS_IS_NOT_A_SUITABLE_ITEM;
	
	/**
	 * ID: 1961<br>
	 * Message: �W�F���X�g�[���̐��������Ă��܂���B
	 */
	public static final SystemMessageId GEMSTONE_QUANTITY_IS_INCORRECT;
	
	/**
	 * ID: 1962<br>
	 * Message: �A�C�e�����B�����I
	 */
	public static final SystemMessageId THE_ITEM_WAS_SUCCESSFULLY_AUGMENTED;
	
	/**
	 * ID : 1963<br>
	 * Message: Select the item from which you wish to remove augmentation.
	 */
	public static final SystemMessageId SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION;
	
	/**
	 * ID: 1964<br>
	 * Message: ���B����Ă��Ȃ��A�C�e���͐��B�������s�����Ƃ��ł��܂���B
	 */
	public static final SystemMessageId AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM;
	
	/**
	 * ID: 1965<br>
	 * Message: $s1�̐��B�����ɐ������܂����B
	 */
	public static final SystemMessageId AUGMENTATION_HAS_BEEN_SUCCESSFULLY_REMOVED_FROM_YOUR_S1;
	
	/**
	 * ID: 1966<br>
	 * Message: �w���R�}���h�͘A���̎w���҂������g�p�ł��܂��B
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_CAN_ISSUE_COMMANDS;
	
	/**
	 * ID: 1967<br>
	 * Message: �傪�ł�������Ă��܂��B���΂炭���Ă��������x�s���Ă��������B
	 */
	public static final SystemMessageId GATE_LOCKED_TRY_AGAIN_LATER;
	
	/**
	 * ID: 1968<br>
	 * Message: $s1�̏��L��
	 */
	public static final SystemMessageId S1_OWNER;
	
	/**
	 * ID: 1968<br>
	 * Message: $s1�̏��L��
	 */
	public static final SystemMessageId AREA_S1_APPEARS;
	
	/**
	 * ID: 1970<br>
	 * Message: ���B���ꂽ�A�C�e�����܂����B���邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN;
	
	/**
	 * ID: 1971<br>
	 * Message: �����ނ̃��x�������߂��邽�߁A�g�p�ł��܂���B
	 */
	public static final SystemMessageId HARDENER_LEVEL_TOO_HIGH;
	
	/**
	 * ID: 1972<br>
	 * Message: �l���X��l�H�[�̓r���ł͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_IS_IN_OPERATION;
	
	/**
	 * ID: 1973<br>
	 * Message: �t���[�Y��Ԃł͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_FROZEN;
	
	/**
	 * ID: 1974<br>
	 * Message: ���S������Ԃł͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_DEAD;
	
	/**
	 * ID: 1975<br>
	 * Message: �g���[�h���͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_TRADING;
	
	/**
	 * ID: 1976<br>
	 * Message: �Ή���Ԃł͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_PARALYZED;
	
	/**
	 * ID: 1977<br>
	 * Message: �t�B�b�V���O���͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_FISHING;
	
	/**
	 * ID: 1978<br>
	 * Message: �����Ă����Ԃł͐��B�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_SITTING_DOWN;
	
	/**
	 * ID: 1979<br>
	 * Message: $s1�̎c�����͂�10�ɂȂ�܂����B
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_10;
	
	/**
	 * ID: 1980<br>
	 * Message: $s1�̎c�����͂�5�ɂȂ�܂����B
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_5;
	
	/**
	 * ID: 1981<br>
	 * Message: $s1�̎c�����͂�1�ɂȂ�܂����B���΂炭����Ə��ł��܂��B
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_1;
	
	/**
	 * ID: 1982<br>
	 * Message: $s1�̎c�����͂�0�ƂȂ�A�A�C�e�������ł��܂����B
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_0;
	
	/**
	 * ID: 1984<br>
	 * Message: ���B�{�^���������Ɛ��B���n�܂�܂��B
	 */
	public static final SystemMessageId PRESS_THE_AUGMENT_BUTTON_TO_BEGIN;
	
	/**
	 * ID: 1985<br>
	 * Message: $s1�̃h���b�v�n��($s2)
	 */
	public static final SystemMessageId S1_DROP_AREA_S2;
	
	/**
	 * ID: 1986<br>
	 * Message: $s1�̏��L��($s2)
	 */
	public static final SystemMessageId S1_OWNER_S2;
	
	/**
	 * ID: 1987<br>
	 * Message: $s1
	 */
	public static final SystemMessageId S1;
	
	/**
	 * ID: 1988<br>
	 * Message: ���Â̓��ɓ������܂����B
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_PRIMEVAL;
	
	/**
	 * ID: 1989<br>
	 * Message: 3���Ԓ┑��A���E���`�ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_RUNE_3_MINUTES;
	
	/**
	 * ID: 1990<br>
	 * Message: ���Â̓����烋�E���`�֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_PRIMEVAL_FOR_RUNE_NOW;
	
	/**
	 * ID: 1991<br>
	 * Message: 3���Ԓ┑��A���Â̓��ɏo�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_PRIMEVAL_3_MINUTES;
	
	/**
	 * ID: 1992<br>
	 * Message: ���E���`���瑾�Â̓��֏o�����܂��B
	 */
	public static final SystemMessageId FERRY_LEAVING_RUNE_FOR_PRIMEVAL_NOW;
	
	/**
	 * ID: 1993<br>
	 * Message: ���Â̓��� ���E���`�s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_FROM_PRIMEVAL_TO_RUNE_DELAYED;
	
	/**
	 * ID: 1994<br>
	 * Message: ���E���`�� ���Â̓��s���̒���D�̉^�q�ɒx�ꂪ�o�Ă��܂��B
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_TO_PRIMEVAL_DELAYED;
	
	/**
	 * ID: 1995<br>
	 * Message: $s1����� ̨���ݸ� ��߼��
	 */
	public static final SystemMessageId S1_CHANNEL_FILTER_OPTION;
	
	/**
	 * ID: 1996<br>
	 * Message: ���肪���G��Ԃł��B
	 */
	public static final SystemMessageId ATTACK_WAS_BLOCKED;
	
	/**
	 * ID: 1997<br>
	 * Message: $c1���X�L���U���ɔ������܂��B
	 */
	public static final SystemMessageId C1_PERFORMING_COUNTERATTACK;
	
	/**
	 * ID: 1998<br>
	 * Message: $c1�̃X�L���U���ɔ������܂��B
	 */
	public static final SystemMessageId COUNTERED_C1_ATTACK;
	
	/**
	 * ID: 1999<br>
	 * Message: $c1���X�L���U��������܂����B
	 */
	public static final SystemMessageId C1_DODGES_ATTACK;
	
	/**
	 * ID: 2000<br>
	 * Message: $c1�̃X�L���U��������܂����B
	 */
	public static final SystemMessageId AVOIDED_C1_ATTACK2;
	
	/**
	 * ID: 2001<br>
	 * Message: ���B������ɍs��ꂸ�A���B�����s���܂����B
	 */
	public static final SystemMessageId AUGMENTATION_FAILED_DUE_TO_INAPPROPRIATE_CONDITIONS;
	
	/**
	 * ID: 2002<br>
	 * Message: �ߊl�Ɏ��s���܂����B
	 */
	public static final SystemMessageId TRAP_FAILED;
	
	/**
	 * ID: 2003<br>
	 * Message: ��ʑ����𓾂܂����B
	 */
	public static final SystemMessageId OBTAINED_ORDINARY_MATERIAL;
	
	/**
	 * ID: 2004<br>
	 * Message: ���A�����𓾂܂����B
	 */
	public static final SystemMessageId OBTAINED_RATE_MATERIAL;
	
	/**
	 * ID: 2005<br>
	 * Message: ���j�[�N�����𓾂܂����B
	 */
	public static final SystemMessageId OBTAINED_UNIQUE_MATERIAL;
	
	/**
	 * ID: 2006<br>
	 * Message: �B�ꑮ���𓾂܂����B
	 */
	public static final SystemMessageId OBTAINED_ONLY_MATERIAL;
	
	/**
	 * ID: 2007<br>
	 * Message: ��M�����͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_RECIPIENTS_NAME;
	
	/**
	 * ID: 2008<br>
	 * Message: �{������͂��Ă��������B
	 */
	public static final SystemMessageId ENTER_TEXT;
	
	/**
	 * ID: 2009<br>
	 * Message: 1500�������߂��Ă��܂��B
	 */
	public static final SystemMessageId CANT_EXCEED_1500_CHARACTERS;
	
	/**
	 * ID: 2009<br>
	 * Message: 1500�������߂��Ă��܂��B
	 */
	public static final SystemMessageId S2_S1;
	
	/**
	 * ID: 2011<br>
	 * Message: ���B�ς݃A�C�e���͎̂Ă邱�Ƃ��ł��܂���B
	 */
	public static final SystemMessageId AUGMENTED_ITEM_CANNOT_BE_DISCARDED;
	
	/**
	 * ID: 2012<br>
	 * Message: $s1����������܂����B
	 */
	public static final SystemMessageId S1_HAS_BEEN_ACTIVATED;
	
	/**
	 * ID: 2013<br>
	 * Message: ��܂��͍w���̎c�肪�s�����Ă��܂��B
	 */
	public static final SystemMessageId YOUR_SEED_OR_REMAINING_PURCHASE_AMOUNT_IS_INADEQUATE;
	
	/**
	 * ID: 2014<br>
	 * Message: �ُ펖�Ԃ��������Đi�s�ł��܂���B
	 */
	public static final SystemMessageId MANOR_CANT_ACCEPT_MORE_CROPS;
	
	/**
	 * ID: 2015<br>
	 * Message: ��z�����X�L���̌��ʂ��������A�X�L���̍Ďg�p���\�ł��B
	 */
	public static final SystemMessageId SKILL_READY_TO_USE_AGAIN;
	
	/**
	 * ID: 2016<br>
	 * Message: ��z�����X�L���̌��ʂ��������A�X�L���ُ̈��Ԃ̌��ʎ��Ԃ��������܂����B
	 */
	public static final SystemMessageId SKILL_READY_TO_USE_AGAIN_BUT_TIME_INCREASED;
	
	/**
	 * ID: 2017<br>
	 * Message: $c1�͌l���X��l�H�[���J���Ă��邽�ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE;
	
	/**
	 * ID: 2018<br>
	 * Message: $c1�̓t�B�b�V���O���̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING;
	
	/**
	 * ID: 2019<br>
	 * Message: $c1��HP�܂���MP��50���ȉ��̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_HP_OR_MP_IS_BELOW_50_PERCENT;
	
	/**
	 * ID: 2020<br>
	 * Message: $c1�͌����s�\�Ȓn��i�s�[�X�]�[���A�Z�u�� �T�C���A�����A���X�^�[�g�s�\�Ȓn��j�ɂ��邽�ߌ�����\�����߂܂���B
	 */
	public static final SystemMessageId C1_CANNOT_MAKE_A_CHALLANGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA;
	
	/**
	 * ID: 2021<br>
	 * Message: $c1�͐퓬���̂��߁A�����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE;
	
	/**
	 * ID: 2022<br>
	 * Message: $c1�͂��łɌ������̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL;
	
	/**
	 * ID: 2023<br>
	 * Message: $c1�̓J�I�e�B�b�N�����̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_STATE;
	
	/**
	 * ID: 2024<br>
	 * Message: $c1�̓I�����s�A�[�h���s���Ă��邽�߁A�����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD;
	
	/**
	 * ID: 2025<br>
	 * Message: $c1�̓A�W�g�퐋�s���̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR;
	
	/**
	 * ID: 2026<br>
	 * Message: $c1�͍U���ɎQ�����̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_SIEGE_WAR;
	
	/**
	 * ID: 2027<br>
	 * Message: $c1�͑D�A���C�o�[���A�X�g���C�_�[�Ȃǂɓ��撆�̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_WYVERN_OR_STRIDER;
	
	/**
	 * ID: 2028<br>
	 * Message: $c1�͉������ɂ��邽�ߌ����������ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_C1_IS_TOO_FAR_AWAY;
	
	/**
	 * ID: 2029<br>
	 * Message: $c1�̓e���|�[�g���ł��邽�߁A�I�����s�A�[�h�ɎQ���ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_DURING_TELEPORT;
	
	/**
	 * ID: 2030<br>
	 * Message: ���O�C�����ł��B
	 */
	public static final SystemMessageId CURRENTLY_LOGGING_IN;
	
	/**
	 * ID: 2031<br>
	 * Message: ���΂炭���҂����������B
	 */
	public static final SystemMessageId PLEASE_WAIT_A_MOMENT;
	
	/**
	 * ID: 2032<br>
	 * Message: �A�C�e���̍w���\���ԓ��ł͂���܂���B
	 */
	public static final SystemMessageId NOT_TIME_TO_PURCHASE_ITEM;
	
	/**
	 * ID: 2033<br>
	 * Message: �C���x���g���̐��ʐ����𒴂������߁A�T�u �N���X����邱�Ƃ�ύX���ł��܂���B
	 */
	public static final SystemMessageId NOT_SUBCLASS_WHILE_INVENTORY_FULL;
	
	/**
	 * ID: 2034<br>
	 * Message: �A�C�e���̍w���\���Ԃ܂Ŏc��$s1���� $s2���ł��B
	 */
	public static final SystemMessageId ITEM_PURCHASABLE_IN_S1_HOURS_S2_MINUTES;
	
	/**
	 * ID: 2035<br>
	 * Message: �A�C�e���̍w���\���Ԃ܂Ŏc�� $s1���ł��B
	 */
	public static final SystemMessageId ITEM_PURCHASABLE_IN_S1_MINUTES;
	
	/**
	 * ID: 2036<br>
	 * Message: �p�[�e�B�[�����b�N����Ă��邽�ߏ��҂ł��܂���B
	 */
	public static final SystemMessageId NO_INVITE_PARTY_LOCKED;
	
	/**
	 * ID: 2037<br>
	 * Message: �L�����N�^�[�𐶐��ł��܂���B�Y���T�[�o�[�͈ȑO���������L�����N�^�[�����Ȃ��ꍇ�́A�V�K�L�����N�^�[�𐶐��ł��Ȃ��悤�ɐ�������Ă��܂��B���̃T�[�o�[�������p���������B
	 */
	public static final SystemMessageId CANT_CREATE_CHARACTER_DURING_RESTRICTION;
	
	/**
	 * ID: 2038<br>
	 * Message: �A�C�e���̃h���b�v���ł��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId ACCOUNT_CANT_DROP_ITEMS;
	
	/**
	 * ID: 2039<br>
	 * Message: �A�C�e���̃g���[�h���ł��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId ACCOUNT_CANT_TRADE_ITEMS;
	
	/**
	 * ID: 2040<br>
	 * Message: �Ώۂ̃��[�U�[�ƃA�C�e���̃g���[�h���ł��܂���B
	 */
	public static final SystemMessageId CANT_TRADE_WITH_TARGET;
	
	/**
	 * ID: 2041<br>
	 * Message: �l���X���J�����Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId CANT_OPEN_PRIVATE_STORE;
	
	/**
	 * ID: 2042<br>
	 * Message: ���̃A�J�E���g�͗������[�ɂ��A�ꎞ�I�ɂ����p����������Ă����Ԃł��B\n�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE23;
	
	/**
	 * ID: 2043<br>
	 * Message: �C���x���g���̏d�ʂ܂��͌����������z���Ă��邽�߁A�N�G�X�g�̐��Z���ł��܂���B�C���x���g���ɃX�y�[�X���m�ۂ��Ă����蒼���Ă��������B
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_QUESTITEM;
	
	/**
	 * ID: 2044<br>
	 * Message: �l�H�[���J���Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId CANT_SETUP_PRIVATE_WORKSHOP;
	
	/**
	 * ID: 2045<br>
	 * Message: �l�H�[�������p�ł��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId CANT_USE_PRIVATE_WORKSHOP;
	
	/**
	 * ID: 2046<br>
	 * Message: �l���X�������p�ł��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId CANT_USE_PRIVATE_STORES;
	
	/**
	 * ID: 2047<br>
	 * Message: �����q�ɂ������p�ł��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId CANT_USE_CLAN_WH;
	
	/**
	 * ID: 2048<br>
	 * Message: $s1�ł��łɎg�p����Ă���V���[�g�J�b�g �L�[�Əd�����Ă��܂��B�d�����Ă���V���[�g�J�b�g �L�[�����Z�b�g���A���͂����V���[�g�J�b�g �L�[���g���܂����B
	 */
	public static final SystemMessageId CONFLICTING_SHORTCUT;
	
	/**
	 * ID: 2049<br>
	 * Message: �V���[�g�J�b�g�L�[��K�p���ăT�[�o�[�ɕۑ����܂��B��낵���ł����B
	 */
	public static final SystemMessageId CONFIRM_SHORTCUT_WILL_SAVED_ON_SERVER;
	
	/**
	 * ID: 2050<br>
	 * Message: $s1���������̌f�g���s���܂��B
	 */
	public static final SystemMessageId S1_TRYING_RAISE_FLAG;
	
	/**
	 * ID: 2051<br>
	 * Message: ���q�l�́A�܂����l�[�W��II�̃v���C�ɓ��ӂ���Ă��Ȃ����߁A���l�[�W��II�̃v���C�͂ł��܂���B�����T�C�g�ihttp://lineage2.plaync.jp/�j�ŃQ�[���ɓ��ӂ��Ă���A������x���O�C�����Ă��������B
	 */
	public static final SystemMessageId MUST_ACCEPT_AGREEMENT;
	
	/**
	 * ID: 2052<br>
	 * Message: ���q�l�̃A�J�E���g�͕ی�҂̓��ӂ𓾂Ă��܂���B\n�ی�҂̓��ӂ��ς܂�����ŁA������x���O�C�����Ă��������B
	 */
	public static final SystemMessageId NEED_CONSENT_TO_PLAY_THIS_ACCOUNT;
	
	/**
	 * ID: 2053<br>
	 * Message: ���q�l�̃A�J�E���g�́A�Q�[���ɓ��ӂ��Ă��Ȃ����A���\�����ƂȂ��Ă��܂��B\n�Q�[���ɓ��ӂ��邩�A���\�����L�����Z��������ŁA������x���O�C�����Ă��������B
	 */
	public static final SystemMessageId ACCOUNT_DECLINED_AGREEMENT_OR_PENDING;
	
	/**
	 * ID: 2054<br>
	 * Message: ���q�l�̃A�J�E���g�́A���ׂẴT�[�r�X�̂����p����������Ă����Ԃł��B\n�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ACCOUNT_SUSPENDED;
	
	/**
	 * ID: 2055<br>
	 * Message: ���q�l�̃A�J�E���g�́A���ׂẴQ�[�� �T�[�r�X�̂����p���ł��Ȃ��悤�ɐ�������Ă��܂��B\n�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ACCOUNT_SUSPENDED_FROM_ALL_SERVICES;
	
	/**
	 * ID: 2056<br>
	 * Message: ���q�l�̃A�J�E���g�͓����A�J�E���g�ɕύX���ꂽ���߁A����ڑ��ł��܂���B\n�ύX��̓����A�J�E���g�ł�����x���O�C�����Ă��������B
	 */
	public static final SystemMessageId ACCOUNT_CONVERTED;
	
	/**
	 * ID: 2057<br>
	 * Message: ���Ȃ���$c1���Ւf���܂����B
	 */
	public static final SystemMessageId BLOCKED_C1;
	
	/**
	 * ID: 2058<br>
	 * Message: ���łɕϐg��Ԃł��邽�߁A�ϐg���ł��܂���B
	 */
	public static final SystemMessageId YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN;
	
	/**
	 * ID: 2059<br>
	 * Message: ���肪�������߁A�ϐg�ł��܂���B�ʂ̏ꏊ�Ɉړ����Ă��������x�s���Ă��������B
	 */
	public static final SystemMessageId AREA_UNSUITABLE_FOR_POLYMORPH;
	
	/**
	 * ID: 2060<br>
	 * Message: ���̒��ł͂��]�݂̎p�ɕϐg�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_POLYMORPH_INTO_THE_DESIRED_FORM_IN_WATER;
	
	/**
	 * ID: 2061<br>
	 * Message: �ϐg���ǂ������Ă��邽�ߕϐg�ł��܂���B
	 */
	public static final SystemMessageId CANT_MORPH_DUE_TO_MORPH_PENALTY;
	
	/**
	 * ID: 2062<br>
	 * Message: �����b�A�y�b�g������������Ԃł͕ϐg�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_POLYMORPH_WHEN_YOU_HAVE_SUMMONED_A_SERVITOR;
	
	/**
	 * ID: 2063<br>
	 * Message: �y�b�g�ɏ���Ă����Ԃł͕ϐg�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_PET;
	
	/**
	 * ID: 2064<br>
	 * Message: ����ȃX�L���̉e�����󂯂Ă���Ԃ͕ϐg�ł��܂���B
	 */
	public static final SystemMessageId CANT_MORPH_WHILE_UNDER_SPECIAL_SKILL_EFFECT;
	
	/**
	 * ID: 2065<br>
	 * Message: �Y���A�C�e���͉����ł��܂���B
	 */
	public static final SystemMessageId ITEM_CANNOT_BE_TAKEN_OFF;
	
	/**
	 * ID: 2066<br>
	 * Message: �Y���̕���͍U�����ł��܂���B
	 */
	public static final SystemMessageId THAT_WEAPON_CANT_ATTACK;
	
	/**
	 * ID: 2067<br>
	 * Message: �Y���̕���͕���X�L���ȊO�̂��̑��̃X�L���͎g�p�ł��܂���B
	 */
	public static final SystemMessageId WEAPON_CAN_USE_ONLY_WEAPON_SKILL;
	
	/**
	 * ID: 2068<br>
	 * Message: �G���`�����g �X�L���̃A���g���C���ɕK�v�ȃA�C�e�����s�����Ă��܂��B
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_UNTRAIN_SKILL_ENCHANT;
	
	/**
	 * ID: 2069<br>
	 * Message: �G���`�����g �X�L���̃A���g���C���ɐ������܂����B�G���`�����g �X�L��$s1�̃G���`�����gLv��1�ቺ���܂����B
	 */
	public static final SystemMessageId UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_DECREASED_BY_ONE;
	
	/**
	 * ID: 2070<br>
	 * Message: �G���`�����g �X�L���̃A���g���C���ɐ������܂����B�G���`�����g �X�L��$s1�̃G���`�����gLv��0�ɂȂ�G���`�����g �X�L��������������܂��B
	 */
	public static final SystemMessageId UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_RESETED;
	
	/**
	 * ID: 2071<br>
	 * Message: �G���`�����g �X�L�� ���[�g �`�F���W�ɕK�v�ȃA�C�e��������܂���B
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_CHANGE_SKILL_ENCHANT_ROUTE;
	
	/**
	 * ID: 2072<br>
	 * Message: �G���`�����g �X�L�� ���[�g �`�F���W�ɐ������܂����B�G���`�����g �X�L��$s1��Lv��$s2�ቺ���܂����B
	 */
	public static final SystemMessageId SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WAS_DECREASED_BY_S2;
	
	/**
	 * ID: 2073<br>
	 * Message: �G���`�����g �X�L�� ���[�g �`�F���W�ɐ������܂����B�G���`�����g�X�L��$s1��Lv���ێ�����܂��B
	 */
	public static final SystemMessageId SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WILL_REMAIN;
	
	/**
	 * ID: 2074<br>
	 * Message: �X�L�� �G���`�����g�Ɏ��s���܂����B�G���`�����g �X�L��$s1�̃G���`�����gLv���ێ�����܂��B
	 */
	public static final SystemMessageId SKILL_ENCHANT_FAILED_S1_LEVEL_WILL_REMAIN;
	
	/**
	 * ID: 2075<br>
	 * Message: �������Ԃł͂���܂���B
	 */
	public static final SystemMessageId NO_AUCTION_PERIOD;
	
	/**
	 * ID: 2076<br>
	 * Message: �ō����D���i��1000���𒴂��邽�߁A���D���ł��܂���B
	 */
	public static final SystemMessageId BID_CANT_EXCEED_100_BILLION;
	
	/**
	 * ID: 2077<br>
	 * Message: �ō����D���i��荂�����z�œ��D���Ă��������B
	 */
	public static final SystemMessageId BID_MUST_BE_HIGHER_THAN_CURRENT_BID;
	
	/**
	 * ID: 2078<br>
	 * Message: ���D�ɕK�v�ȃA�f�i������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_ADENA_FOR_THIS_BID;
	
	/**
	 * ID: 2079<br>
	 * Message: ���łɍō��z���D�ƂȂ������A���D�ƂȂ������z������܂��B
	 */
	public static final SystemMessageId HIGHEST_BID_BUT_RESERVE_NOT_MET;
	
	/**
	 * ID: 2080<br>
	 * Message: ��ʓ��D�҂ɂ����D���L�����Z������܂����B
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_OUTBID;
	
	/**
	 * ID: 2081<br>
	 * Message: ���D�ƂȂ������z������܂���B
	 */
	public static final SystemMessageId NO_FUNDS_DUE;
	
	/**
	 * ID: 2082<br>
	 * Message: �C���x���g���̃A�f�i�����e�l�𒴂��܂��B
	 */
	public static final SystemMessageId EXCEEDED_MAX_ADENA_AMOUNT_IN_INVENTORY;
	
	/**
	 * ID: 2083<br>
	 * Message: �������n�܂�܂��B
	 */
	public static final SystemMessageId AUCTION_BEGUN;
	
	/**
	 * ID: 2084<br>
	 * Message: �v�ǂɓG�̌������N�����܂����B
	 */
	public static final SystemMessageId ENEMIES_INTRUDED_FORTRESS;
	
	/**
	 * ID: 2085<br>
	 * Message: ���ꂽ��������L������Ԃł͋��тƃg���[�h �`���b�g�͗��p�ł��܂���B
	 */
	public static final SystemMessageId SHOUT_AND_TRADE_CHAT_CANNOT_BE_USED_WHILE_POSSESSING_CURSED_WEAPON;
	
	/**
	 * ID: 2086<br>
	 * Message: $s1����Ƀ��[�U�[$c2�ɂ��Ă�BOT�������I�����܂��B
	 */
	public static final SystemMessageId SEARCH_ON_S2_FOR_BOT_USE_COMPLETED_IN_S1_MINUTES;
	
	/**
	 * ID: 2087<br>
	 * Message: �v�ǂ��U������Ă��܂��I
	 */
	public static final SystemMessageId A_FORTRESS_IS_UNDER_ATTACK;
	
	/**
	 * ID: 2088<br>
	 * Message: �v�ǐ�J�n�܂Ŏc��$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_UNTIL_THE_FORTRESS_BATTLE_STARTS;
	
	/**
	 * ID: 2089<br>
	 * Message: �v�ǐ�J�n�܂Ŏc��$s1�b�ł��B
	 */
	public static final SystemMessageId S1_SECONDS_UNTIL_THE_FORTRESS_BATTLE_STARTS;
	
	/**
	 * ID: 2090<br>
	 * Message: $s1�̗v�ǐ킪�n�܂�܂����B
	 */
	public static final SystemMessageId THE_FORTRESS_BATTLE_S1_HAS_BEGUN;
	
	/**
	 * ID: 2091<br>
	 * Message: ���q�l�̃A�J�E���g�̓p�X���[�h�̕ύX�����Ȃ���ΐڑ����ł��܂���B\n�����T�C�g�ihttp://lineage2.plaync.jp/�j�Ńp�X���[�h��ύX������ŁA������x��蒼���Ă��������B
	 */
	public static final SystemMessageId CHANGE_PASSWORT_FIRST;
	
	/**
	 * ID: 2092<br>
	 * Message: ���D�ƂȂ������z�����邽�ߓ��D���ł��܂���B
	 */
	public static final SystemMessageId CANNOT_BID_DUE_TO_PASSED_IN_PRICE;
	
	/**
	 * ID: 2093<br>
	 * Message: �����̗��D�ƂȂ������z��$s1�A�f�i�ł��B���D�ƂȂ������z��ԋ����܂����B
	 */
	public static final SystemMessageId PASSED_IN_PRICE_IS_S1_ADENA_WOULD_YOU_LIKE_TO_RETURN_IT;
	
	/**
	 * ID: 2094<br>
	 * Message: ���̃��[�U�[���w�����ł��B���΂炭�o���Ă��������x���������������B
	 */
	public static final SystemMessageId ANOTHER_USER_PURCHASING_TRY_AGAIN_LATER;
	
	/**
	 * ID: 2095<br>
	 * Message: ���т��ł��Ȃ��A�J�E���g�ł��B
	 */
	public static final SystemMessageId ACCOUNT_CANNOT_SHOUT;
	
	/**
	 * ID: 2096<br>
	 * Message: $c1������ł��Ȃ��ʒu�ɂ��邽�ߐi�s�ł��܂���B
	 */
	public static final SystemMessageId C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED;
	
	/**
	 * ID: 2097<br>
	 * Message: $c1�̃��x�������������Ȃ����ߓ���ł��܂���B
	 */
	public static final SystemMessageId C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2098<br>
	 * Message: $c1�̃N�G�X�g�����������Ȃ����ߓ���ł��܂���B
	 */
	public static final SystemMessageId C1_QUEST_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2099<br>
	 * Message: $c1�̃A�C�e�������������Ȃ����ߓ���ł��܂���B
	 */
	public static final SystemMessageId C1_ITEM_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2100<br>
	 * Message: $c1���ē���\���ԂɂȂ�Ȃ����߁A����ł��܂���B
	 */
	public static final SystemMessageId C1_MAY_NOT_REENTER_YET;
	
	/**
	 * ID: 2101<br>
	 * Message: ���݃p�[�e�B�[��Ԃł͂Ȃ����ߓ���ł��܂���B
	 */
	public static final SystemMessageId NOT_IN_PARTY_CANT_ENTER;
	
	/**
	 * ID: 2102<br>
	 * Message: �Y���p�[�e�B�[�����������𒴂������߁A����ł��܂���B
	 */
	public static final SystemMessageId PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER;
	
	/**
	 * ID: 2103<br>
	 * Message: ���݁A�A���`�����l���ɏ������Ă��Ȃ����ߓ���ł��܂���B
	 */
	public static final SystemMessageId NOT_IN_COMMAND_CHANNEL_CANT_ENTER;
	
	/**
	 * ID: 2104<br>
	 * Message: �Y���C���X�^���g �]�[���̍ő吶�����𒴂������߁A����ł��܂���B
	 */
	public static final SystemMessageId MAXIMUM_INSTANCE_ZONE_NUMBER_EXCEEDED_CANT_ENTER;
	
	/**
	 * ID: 2105<br>
	 * Message: ���̃C���X�^���g �]�[���ɓ��ꂵ����Ԃł��邽�߁A�Y���_���W�����ɓ���ł��܂���B
	 */
	public static final SystemMessageId ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER;
	
	/**
	 * ID: 2106<br>
	 * Message: �Y���_���W�����̗��p���ԏI���܂Ŏc��$s1���ł��B���Ԃ��I������ƃ_���W�����̊O�֋����ړ����܂��B
	 */
	public static final SystemMessageId DUNGEON_EXPIRES_IN_S1_MINUTES;
	
	/**
	 * ID: 2107<br>
	 * Message: �C���X�^���g �]�[����$s1����ɍ폜����A�Y�����ԏI����Ƀ_���W�����̊O�֋����ړ����܂��B
	 */
	public static final SystemMessageId INSTANCE_ZONE_TERMINATES_IN_S1_MINUTES;
	
	/**
	 * ID: 2108<br>
	 * Message: ���q�l�̃A�J�E���g�͕s�����[�e�B���e�B�̗��p���F�߂�ꂽ����10���ԁA�Q�[���T�[�r�X���ׂĂ̂����p����������Ă��܂��B���㓯�l�̈ᔽ�s�ׂ��m�F���ꂽ�ꍇ�A�i�v�ɐ�������܂��̂ł����ӂ��������B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE24;
	
	/**
	 * ID: 2109<br>
	 * Message: �T�[�o�[���������ꂽ�ׁA�����ł��g�p�̃L�����N�^�[$s1�́A���̖��O�Əd�����Ă��܂��B�ύX����L�����N�^�[������͂��Ă��������B
	 */
	public static final SystemMessageId CHARACTER_NAME_OVERLAPPING_RENAME_CHARACTER;
	
	/**
	 * ID: 2110<br>
	 * Message: �����̖͂��O�͊��ɑ��݂��Ă��邩�A�����p���ł��Ȃ����O�ł��B�ύX����L�����N�^�[����������x�����͂��������B
	 */
	public static final SystemMessageId CHARACTER_NAME_INVALID_RENAME_CHARACTER;
	
	/**
	 * ID: 2111<br>
	 * Message: �w��̃V���[�g�J�b�g�L�[����͂��܂��B
	 */
	public static final SystemMessageId ENTER_SHORTCUT_TO_ASSIGN;
	
	/**
	 * ID: 2112<br>
	 * Message: �⏕�L�[��CTRL�AALT�ASHIFT�L�[���g�p�ł��A��x�ɓ�̕⏕�L�[����͂ł��܂��B\n��) �uCTRL + ALT + A�v
	 */
	public static final SystemMessageId SUBKEY_EXPLANATION1;
	
	/**
	 * ID: 2113<br>
	 * Message: �g�� �⏕�L�[ ���[�h�ł�CTRL�AALT�ASHIFT�L�[��⏕�L�[�Ƃ��Ċ��p�ł��A��ʃV���[�g�J�b�g�L�[ ���[�h�ł͕⏕�L�[��ALT�̂ݎg�p�ł��܂��B
	 */
	public static final SystemMessageId SUBKEY_EXPLANATION2;
	
	/**
	 * ID: 2114<br>
	 * Message: �g�� �⏕�L�[ ���[�h�ɐݒ肷��ƁA������Ctrl��Shift�Ɏw�肳��Ă��������U���Ƃ��̏�ł̍U����Alt + Q��Alt + E�ɕύX����ACTRL�L�[��SHIFT�L�[��ʂ̃V���[�g�J�b�g�L�[�̎w��L�[�Ŏg�p�\�ƂȂ�悤�ɕύX����܂��B��낵���ł����B
	 */
	public static final SystemMessageId SUBKEY_EXPLANATION3;
	
	/**
	 * ID: 2115<br>
	 * Message: ���q�l�̃A�J�E���g��NC�R�C���Ɋւ���o�O�����p�������Ƃ��F�߂�ꂽ���߁A�����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE25;
	
	/**
	 * ID: 2116<br>
	 * Message: ���q�l�̃A�J�E���g�͖����Ŏx�����ꂽ�R�C�������p�������Ƃ��F�߂�ꂽ���߂����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE26;
	
	/**
	 * ID: 2117<br>
	 * Message: ���q�l�̃A�J�E���g�͑��l�̖��`�𗘗p���������Ȃǂ��F�߂�ꂽ���߁A�ꎞ�I�ɃQ�[���T�[�r�X�̂����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE27;
	
	/**
	 * ID: 2118<br>
	 * Message: ���q�l�̃A�J�E���g�͌��ς̓��p���F�߂�ꂽ���߁A�Q�[���T�[�r�X���ׂĂ̂����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE28;
	
	/**
	 * ID: 2119<br>
	 * Message: ���q�l�̃A�J�E���g�͌��ς̓��p���F�߂�ꂽ�߁A�Q�[���T�[�r�X���ׂĂ̂����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE29;
	
	/**
	 * ID: 2120<br>
	 * Message: ���q�l�̃A�J�E���g�͕s�����[�e�B���e�B�̗��p���F�߂�ꂽ���߁A10���ԃQ�[���T�[�r�X�̗��p�����ׂĐ�������Ă��܂��B���㓯�l�̈ᔽ�s�ׂ��m�F���ꂽ�ꍇ�͉i�v�ɐ�������܂��̂ł����ӂ��������B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE30;
	
	/**
	 * ID: 2121<br>
	 * Message: ���q�l�̃A�J�E���g�͕s�����[�e�B���e�B�̗��p���F�߂�ꂽ���߁A�Q�[���T�[�r�X���ׂĂ̗��p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE31;
	
	/**
	 * ID: 2122<br>
	 * Message: ���q�l�̃A�J�E���g�͕s�����[�e�B���e�B�̗��p���F�߂�ꂽ���߁A�Q�[���T�[�r�X���ׂĂ̗��p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE32;
	
	/**
	 * ID: 2123<br>
	 * Message: ���q�l�̃A�J�E���g�͂��q�l����̗v���ɂ��Q�[���T�[�r�X���ׂĂ̗��p���ł��Ȃ��悤�ɂȂ��Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE33;
	
	/**
	 * ID: 2124<br>
	 * Message: �T�[�o�[��������������ɂ��g�p����Ă����������ł���$s1�́A���̖��O�Əd�����Ă��܂��B�ύX���錌��������͂��Ă��������B
	 */
	public static final SystemMessageId CLAN_NAME_OVERLAPPING_RENAME_CLAN;
	
	/**
	 * ID: 2125<br>
	 * Message: �����̖͂��O�͊��ɑ��݂��Ă��邩���g�p���ł��Ȃ����O�ł��B�ύX���錌������������x���͂��Ă��������B
	 */
	public static final SystemMessageId CLAN_NAME_INVALID_RENAME_CLAN;
	
	/**
	 * ID: 2126<br>
	 * Message: ���q�l�̃A�J�E���g�͌����Ǒ��ɔ�������e�̓x�d�Ȃ�A�b�v���[�h���F�߂�ꂽ���߁A�T�[�r�X�̂����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE34;
	
	/**
	 * ID: 2127<br>
	 * Message: ���q�l�̃A�J�E���g�͈ᔽ�ƂȂ鏑�����݂��F�߂�ꂽ���߁A�����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE35;
	
	/**
	 * ID: 2128<br>
	 * Message: ���q�l�̃A�J�E���g�͏��ƖړI�̃Q�[�����p���F�߂�ꂽ���߁A�Q�[���T�[�r�X���ׂĂ̂����p����������Ă��܂��B�ڂ����͌����T�C�g�ihttp://lineage2.plaync.jp/�j�̃T�|�[�g�܂ł��₢���킹���������B
	 */
	public static final SystemMessageId ILLEGAL_USE36;
	
	/**
	 * ID: 2129<br>
	 * Message: ���B���ꂽ�A�C�e���͕ϊ��ł��܂���B���B������������ɕϊ����Ă��������B
	 */
	public static final SystemMessageId AUGMENTED_ITEM_CANT_CONVERTED;
	
	/**
	 * ID: 2130<br>
	 * Message: �ϊ��ł��Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId CANT_CONVERT_THIS_ITEM;
	
	/**
	 * ID: 2131<br>
	 * Message: �ō����z�����ăA�C�e�������D����܂����B���D���ꂽ�A�C�e���͌l�q�ɂ���󂯎�邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId WON_BID_ITEM_CAN_BE_FOUND_IN_WAREHOUSE;
	
	/**
	 * ID: 2132<br>
	 * Message: ��ʃT�[�o�[�ɐڑ����܂����B
	 */
	public static final SystemMessageId ENTERED_COMMON_SERVER;
	
	/**
	 * ID: 2133<br>
	 * Message: ���l�T�[�o�[�ɐڑ����܂����B
	 */
	public static final SystemMessageId ENTERED_ADULTS_ONLY_SERVER;
	
	/**
	 * ID: 2134<br>
	 * Message: ���N�T�[�o�[�ɐڑ����܂����B
	 */
	public static final SystemMessageId ENTERED_JUVENILES_SERVER;
	
	/**
	 * ID: 2135<br>
	 * Message: ��J�x�̂��ߍs���܂���B
	 */
	public static final SystemMessageId NOT_ALLOWED_DUE_TO_FATIGUE_LEVEL;
	
	/**
	 * ID: 2136<br>
	 * Message: �������ύX�̐\�����󂯕t�����܂����B
	 */
	public static final SystemMessageId CLAN_NAME_CHANCE_PETITION_SUBMITTED;
	
	/**
	 * ID: 2137<br>
	 * Message: $s1�A�C�e����$s2�A�f�i�œ��D���悤�Ƃ��Ă��܂��B��낵���ł����B
	 */
	public static final SystemMessageId CONFIRM_BID_S2_ADENA_FOR_S1_ITEM;
	
	/**
	 * ID: 2138<br>
	 * Message: ���D���i����͂��ĉ������B
	 */
	public static final SystemMessageId ENTER_BID_PRICE;
	
	/**
	 * ID: 2139<br>
	 * Message: $c1�̃y�b�g
	 */
	public static final SystemMessageId C1_PET;
	
	/**
	 * ID: 2140<br>
	 * Message: $c1�̏����b
	 */
	public static final SystemMessageId C1_SERVITOR;
	
	/**
	 * ID: 2141<br>
	 * Message: $c1�̖��@�Ɏキ��R���܂����B
	 */
	public static final SystemMessageId SLIGHTLY_RESISTED_C1_MAGICC;
	
	/**
	 * ID: 2142<br>
	 * Message: $c1�̓p�[�e�B�[ �����o�[�ł͂Ȃ����ߒǕ��ł��܂���B
	 */
	public static final SystemMessageId CANT_EXPEL_C1_NOT_A_PARTY_MEMBER;
	
	/**
	 * ID: 2143<br>
	 * Message: �l���X��l�H�[�̓r���ł͑�����t�^�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP;
	
	/**
	 * ID: 2144<br>
	 * Message: ������t�^�������A�C�e�������I�т��������B
	 */
	public static final SystemMessageId SELECT_ITEM_TO_ADD_ELEMENTAL_POWER;
	
	/**
	 * ID: 2145<br>
	 * Message: ���������ނ̎g�p����������܂����B
	 */
	public static final SystemMessageId ELEMENTAL_ENHANCE_CANCELED;
	
	/**
	 * ID: 2146<br>
	 * Message: ���������ނ̎g�p�����ɍ����܂���B
	 */
	public static final SystemMessageId ELEMENTAL_ENHANCE_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2147<br>
	 * Message: $s1��$s2�̑����̕t�^�ɐ������܂����B
	 */
	public static final SystemMessageId ELEMENTAL_POWER_S2_SUCCESSFULLY_ADDED_TO_S1;
	
	/**
	 * ID: 2148<br>
	 * Message: +$s1$s2��$s3�̑����𐬌��I�ɕt�^���܂����B
	 */
	public static final SystemMessageId ELEMENTAL_POWER_S3_SUCCESSFULLY_ADDED_TO_S1_S2;
	
	/**
	 * ID: 2149<br>
	 * Message: �����̕t�^�Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_ADDING_ELEMENTAL_POWER;
	
	/**
	 * ID: 2150<br>
	 * Message: ���łɕʂ̑������t�^����Ă��邽�߁A�������t�^�ł��܂���B
	 */
	public static final SystemMessageId ANOTHER_ELEMENTAL_POWER_ALREADY_ADDED;
	
	/**
	 * ID: 2151<br>
	 * Message: ���肪���@�ɒ�R�������߃_���[�W���ቺ���܂����B
	 */
	public static final SystemMessageId OPPONENT_HAS_RESISTANCE_MAGIC_DAMAGE_DECREASED;
	
	/**
	 * ID: 2152<br>
	 * Message: �w��̃V���[�g�J�b�g�L�[�����ׂď������āA�V���[�g�J�b�g�L�[�������ݒ�ɖ߂��܂��B��낵���ł����B
	 */
	public static final SystemMessageId CONFIRM_SHORCUT_DELETE;
	
	/**
	 * ID: 2153<br>
	 * Message: ���݂������̃A�J�E���g�̒���10�������ɐڑ�����Ă��邽�߁A����ȏ�ʂ̃A�J�E���g�ł̐ڑ��͂ł��܂���B
	 */
	public static final SystemMessageId MAXIMUM_ACCOUNT_LOGINS_REACHED;
	
	/**
	 * ID: 2154<br>
	 * Message: �^�[�Q�b�g���t���b�O �|�[���ł͂Ȃ����߁A���̌f�g�͂ł��܂���B
	 */
	public static final SystemMessageId THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED;
	
	/**
	 * ID: 2155<br>
	 * Message: ���łɊ����f�g����Ă��邽�ߌf�g�ł��܂���B
	 */
	public static final SystemMessageId A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED;
	
	/**
	 * ID: 2156<br>
	 * Message: �K�v�ȃA�C�e��������Ȃ����߃X�L�����g�p�ł��܂���B
	 */
	public static final SystemMessageId THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL;
	
	/**
	 * ID: 2157<br>
	 * Message: $s1�A�f�i�œ��D���܂��B
	 */
	public static final SystemMessageId BID_WILL_BE_ATTEMPTED_WITH_S1_ADENA;
	
	/**
	 * ID: 2158<br>
	 * Message: �U�鑤�̗Վ��A�������^�[�Q�b�g�ɂ͋����U�����ł��܂���B
	 */
	public static final SystemMessageId FORCED_ATTACK_IS_IMPOSSIBLE_AGAINST_SIEGE_SIDE_TEMPORARY_ALLIED_MEMBERS;
	
	/**
	 * ID: 2159<br>
	 * Message: ���D�҂����邽�ߋ������Ԃ�5���ԉ�������܂����B
	 */
	public static final SystemMessageId BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_5_MINUTES;
	
	/**
	 * ID: 2160<br>
	 * Message: ���D�҂����邽�ߋ������Ԃ�3���ԉ�������܂����B
	 */
	public static final SystemMessageId BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_3_MINUTES;
	
	/**
	 * ID: 2161<br>
	 * Message: �ړ��X�y�[�X���Ȃ����߁A�X�L�����g�p�ł��܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_SPACE_FOR_SKILL;
	
	/**
	 * ID: 2162<br>
	 * Message: ����$s1���������߁A���v$s2�ɂȂ�܂����B
	 */
	public static final SystemMessageId YOUR_SOUL_HAS_INCREASED_BY_S1_SO_IT_IS_NOW_AT_S2;
	
	/**
	 * ID: 2163<br>
	 * Message: ����ȏ㍰�𑝂₷���Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId SOUL_CANNOT_BE_INCREASED_ANYMORE;
	
	/**
	 * ID: 2164<br>
	 * Message: ���ɂ��̂��܂����B
	 */
	public static final SystemMessageId SEIZED_BARRACKS;
	
	/**
	 * ID: 2165<br>
	 * Message: ���ɋ@�\����������܂����B
	 */
	public static final SystemMessageId BARRACKS_FUNCTION_RESTORED;
	
	/**
	 * ID: 2166<br>
	 * Message: ���ׂĂ̖��ɂ���̂���܂����B
	 */
	public static final SystemMessageId ALL_BARRACKS_OCCUPIED;
	
	/**
	 * ID: 2167<br>
	 * Message: ���ӂ̂���X�L���̓s�[�X�]�[���Ŏg�p�ł��܂���B
	 */
	public static final SystemMessageId A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_PEACE_ZONE;
	
	/**
	 * ID: 2168<br>
	 * Message: $c1�������E���܂����B
	 */
	public static final SystemMessageId C1_ACQUIRED_THE_FLAG;
	
	/**
	 * ID: 2169<br>
	 * Message: $s1�̗v�ǐ�ɓo�^����܂����B
	 */
	public static final SystemMessageId REGISTERED_TO_S1_FORTRESS_BATTLE;
	
	/**
	 * ID: 2170<br>
	 * Message: ���ӂ̂���X�L���͑��肪�s�[�X�]�[���ɂ��鎞�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId CANT_USE_BAD_MAGIC_WHEN_OPPONENT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2171<br>
	 * Message: �N���X�^���C�Y�ł��Ȃ��A�C�e���ł��B
	 */
	public static final SystemMessageId ITEM_CANNOT_CRYSTALLIZED;
	
	/**
	 * ID: 2172<br>
	 * Message: +$s1$s2�̋������I�����܂����B
	 */
	public static final SystemMessageId S1_S2_AUCTION_ENDED;
	
	/**
	 * ID: 2173<br>
	 * Message: $s1�̋������I�����܂����B
	 */
	public static final SystemMessageId S1_AUCTION_ENDED;
	
	/**
	 * ID: 2174<br>
	 * Message: $c1���ϐg��Ԃ̂��ߌ����ł��܂���B
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_WHILE_POLYMORPHED;
	
	/**
	 * ID: 2175<br>
	 * Message: �ϐg��Ԃ̃p�[�e�B�[ �����o�[�����邽�߃p�[�e�B�[���m�ł̌������ł��܂���B
	 */
	public static final SystemMessageId CANNOT_PARTY_DUEL_WHILE_A_MEMBER_IS_POLYMORPHED;
	
	/**
	 * ID: 2176<br>
	 * Message: $s1��$s2�������������܂����B
	 */
	public static final SystemMessageId S1_ELEMENTAL_POWER_REMOVED;
	
	/**
	 * ID: 2177<br>
	 * Message: +$s1$s2��$s3�̑������������܂����B
	 */
	public static final SystemMessageId S1_S2_ELEMENTAL_POWER_REMOVED;
	
	/**
	 * ID: 2178<br>
	 * Message: �����̉����Ɏ��s���܂����B
	 */
	public static final SystemMessageId FAILED_TO_REMOVE_ELEMENTAL_POWER;
	
	/**
	 * ID: 2179<br>
	 * Message: �M������̋����ɍō��z����D���ł��B
	 */
	public static final SystemMessageId HIGHEST_BID_FOR_GIRAN_CASTLE;
	
	/**
	 * ID: 2180<br>
	 * Message: �A�f����̋����ɍō��z����D���ł��B
	 */
	public static final SystemMessageId HIGHEST_BID_FOR_ADEN_CASTLE;
	
	/**
	 * ID: 2181<br>
	 * Message: ���E����̋����ɍō��z����D���ł��B
	 */
	public static final SystemMessageId HIGHEST_BID_FOR_RUNE_CASTLE;
	
	/**
	 * ID: 2182<br>
	 * Message: �D�ɏ������Ԃł͕ϐg�ł��܂���B
	 */
	public static final SystemMessageId CANT_POLYMORPH_ON_BOAT;
	
	/**
	 * ID: 2183<br>
	 * Message: $s1�̗v�ǐ킪�I�����܂����B
	 */
	public static final SystemMessageId THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED;
	
	/**
	 * ID: 2184<br>
	 * Message: $s1������$s2�̗v�ǐ�ŏ������܂����B
	 */
	public static final SystemMessageId S1_CLAN_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2;
	
	/**
	 * ID: 2185<br>
	 * Message: �p�[�e�B�[ ���[�_�[�݂̂����ꂷ�邱�Ƃ��ł��܂��B
	 */
	public static final SystemMessageId ONLY_PARTY_LEADER_CAN_ENTER;
	
	/**
	 * ID: 2186<br>
	 * Message: ����ȏ�̍��͋z���ł��܂���B
	 */
	public static final SystemMessageId SOUL_CANNOT_BE_ABSORBED_ANYMORE;
	
	/**
	 * ID: 2187<br>
	 * Message: ���肪�ˌ��ł��Ȃ��ʒu�ɂ��܂��B
	 */
	public static final SystemMessageId CANT_REACH_TARGET_TO_CHARGE;
	
	/**
	 * ID: 2188<br>
	 * Message: ���̃G���`�����g���s���Ă��܂��B�O�̍�Ƃ��I�����ꂽ��܂��s���Ă��������B
	 */
	public static final SystemMessageId ENCHANTMENT_ALREADY_IN_PROGRESS;
	
	/**
	 * ID: 2189<br>
	 * Message: ���ݒn�F $s1�A$s2�A$s3 (�J�}�G�����t��)
	 */
	public static final SystemMessageId LOC_KAMAEL_VILLAGE_S1_S2_S3;
	
	/**
	 * ID: 2190<br>
	 * Message: ���ݒn�F$s1�A$s2�A$s3 (�r�n�쑤�̃L�����v�t��)
	 */
	public static final SystemMessageId LOC_WASTELANDS_CAMP_S1_S2_S3;
	
	/**
	 * ID: 2191<br>
	 * Message: �I�������I�v�V������K�p����ɂ́A�ĂуQ�[�������[�h����K�v������܂��B�������K�p���Ȃ��ꍇ�́A����Q�[�������s����ۂɓK�p����܂��B�������K�p���܂����B
	 */
	public static final SystemMessageId CONFIRM_APPLY_SELECTIONS;
	
	/**
	 * ID: 2192<br>
	 * Message: �A�C�e�������ɓ��D���܂����B
	 */
	public static final SystemMessageId BID_ON_ITEM_AUCTION;
	
	/**
	 * ID: 2193<br>
	 * Message: NPC�Ƃ̋������������ߍ쓮���܂���B
	 */
	public static final SystemMessageId TOO_FAR_FROM_NPC;
	
	/**
	 * ID: 2194<br>
	 * Message: ���ݕϐg���̕ϐg�̂ł͊Y���̌��ʂ�K�p�ł��܂���B
	 */
	public static final SystemMessageId CANT_APPLY_CURRENT_POLYMORPH_WITH_CORRESPONDING_EFFECTS;
	
	/**
	 * ID: 2195<br>
	 * Message: ��������܂���B
	 */
	public static final SystemMessageId THERE_IS_NOT_ENOUGH_SOUL;
	
	/**
	 * ID: 2196<br>
	 * Message: ���L�����Ȃ�
	 */
	public static final SystemMessageId NO_OWNED_CLAN;
	
	/**
	 * ID: 2197<br>
	 * Message: $s1�������L��
	 */
	public static final SystemMessageId OWNED_S1_CLAN;
	
	/**
	 * ID: 2198<br>
	 * Message: �A�C�e�������ɍō��z����D���ł��B
	 */
	public static final SystemMessageId HIGHEST_BID_IN_ITEM_AUCTION;
	
	/**
	 * ID: 2199<br>
	 * Message: NPC�T�[�o�[���쓮���~��Ԃ̂��߁A�C���X�^���g �]�[���ɓ���ł��܂���B
	 */
	public static final SystemMessageId CANT_ENTER_INSTANCE_ZONE_NPC_SERVER_OFFLINE;
	
	/**
	 * ID: 2200<br>
	 * Message: NPC�T�[�o�[�쓮���~�ɂ��Y���C���X�^���g �]�[�����폜����邽�߁A���΂炭���Ă��狭���ޏꂳ��܂��B
	 */
	public static final SystemMessageId INSTANCE_ZONE_TERMINATED_NPC_SERVER_OFFLINE;
	
	/**
	 * ID: 2201<br>
	 * Message: $s1�N$s2��$s3��
	 */
	public static final SystemMessageId S1_YEARS_S2_MONTHS_S3_DAYS;
	
	/**
	 * ID: 2202<br>
	 * Message: $s1��$s2��$s3�b
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_S3_SECONDS;
	
	/**
	 * ID: 2203<br>
	 * Message: $s1��$s2��
	 */
	public static final SystemMessageId S1_MONTHS_S2_DAYS;
	
	/**
	 * ID: 2204<br>
	 * Message: $s1��
	 */
	public static final SystemMessageId S1_HOURS;
	
	/**
	 * ID: 2205<br>
	 * Message: �~�j�}�b�v���g���Ȃ��n��ɓ���܂����B�~�j�}�b�v����܂��B
	 */
	public static final SystemMessageId AREA_FORBIDS_MINIMAP;
	
	/**
	 * ID: 2206<br>
	 * Message: �~�j�}�b�v���g����n��ɓ���܂����B
	 */
	public static final SystemMessageId AREA_ALLOWS_MINIMAP;
	
	/**
	 * ID: 2207<br>
	 * Message: �~�j�}�b�v���g���Ȃ��n��̂��߁A�~�j�}�b�v���J���܂���B
	 */
	public static final SystemMessageId CANT_OPEN_MINIMAP;
	
	/**
	 * ID: 2208<br>
	 * Message: �X�L���K���ɕK�v�ȃX�L�� ���x�������������܂���B
	 */
	public static final SystemMessageId YOU_DONT_MEET_SKILL_LEVEL_REQUIREMENTS;
	
	/**
	 * ID: 2209<br>
	 * Message: ���[�_�[���쓮���Ȃ��n��
	 */
	public static final SystemMessageId AREA_WHERE_RADAR_CANNOT_BE_USED;
	
	/**
	 * ID: 2210<br>
	 * Message: �G���`�����g����Ă��Ȃ���Ԃɖ߂��܂��B
	 */
	public static final SystemMessageId RETURN_TO_UNENCHANTED_CONDITION;
	
	/**
	 * ID: 2211<br>
	 * Message: ��s�X�L�����K�����Ă��Ȃ����ߐV�����X�L�����K���ł��܂���B
	 */
	public static final SystemMessageId NOT_ACQUIRED_DEED_SKILL_CANNOT_ACQUIRE_SKILLS;
	
	/**
	 * ID: 2212<br>
	 * Message: �X�L���K���ɕK�v�ȃN�G�X�g���܂����s���Ă��܂���B
	 */
	public static final SystemMessageId NOT_COMPLETED_QUEST_FOR_SKILL_ACQUISITION;
	
	/**
	 * ID: 2213<br>
	 * Message: �ϐg������Ԃł͑D�ɏ�D�ł��܂���B
	 */
	public static final SystemMessageId CANT_BOARD_SHIP_POLYMORPHED;
	
	/**
	 * ID: 2214<br>
	 * Message: ���ݒ肵���Z�b�e�B���O�ŐV�����L�����N�^�[�𐶐����܂��B��낵���ł����B
	 */
	public static final SystemMessageId CONFIRM_CHARACTER_CREATION;
	
	/**
	 * ID: 2215<br>
	 * Message: $s1�h���
	 */
	public static final SystemMessageId S1_PDEF;
	
	/**
	 * ID: 2216<br>
	 * Message: CPU�h���C�o�[���ŐV�o�[�W�����ł͂���܂���BAMD�f���A���R�A�ȏ��CPU�����g���̏ꍇ�AAMD�̃E�F�u�T�C�g����AMD�f���A���R�A�I�v�e�B�}�C�U�[���_�E�����[�h�A�C���X�g�[�����ĉ������B
	 */
	public static final SystemMessageId PLEASE_UPDATE_CPU_DRIVER;
	
	/**
	 * ID: 2217<br>
	 * Message: �o���X�^�̔��j�ɐ������āA���������l���オ��܂��B
	 */
	public static final SystemMessageId BALLISTA_DESTROYED_CLAN_REPU_INCREASED;
	
	/**
	 * ID: 2218<br>
	 * Message: ���C�� �N���X�̎��ɂ����g����X�L���ł��B
	 */
	public static final SystemMessageId MAIN_CLASS_SKILL_ONLY;
	
	/**
	 * ID: 2219<br>
	 * Message: ���łɏK���������ʌ����̃X�L���ł��B
	 */
	public static final SystemMessageId LOWER_CLAN_SKILL_ALREADY_ACQUIRED;
	
	/**
	 * ID: 2220<br>
	 * Message: �O�i�K���x���̃X�L�����K���Ă��܂���B
	 */
	public static final SystemMessageId PREVIOUS_LEVEL_SKILL_NOT_LEARNED;
	
	/**
	 * ID: 2221<br>
	 * Message: �I�񂾋@�\���A�N�e�B�u�ɂ��܂����B
	 */
	public static final SystemMessageId ACTIVATE_SELECTED_FUNTIONS_CONFIRM;
	
	/**
	 * ID: 2222<br>
	 * Message: ��@���̔z�u�ɂ�15���A�f�i��������܂��B�z�u���܂����B
	 */
	public static final SystemMessageId SCOUT_COSTS_150000_ADENA;
	
	/**
	 * ID: 2223<br>
	 * Message: �v�ǖ�̋����ɂ�20���A�f�i��������܂��B�������܂����B
	 */
	public static final SystemMessageId FORTRESS_GATE_COSTS_200000_ADENA;
	
	/**
	 * ID: 2224<br>
	 * Message: �{�E�K�����ˏ������ł��B
	 */
	public static final SystemMessageId CROSSBOW_PREPARING_TO_FIRE;
	
	/**
	 * ID: 2225<br>
	 * Message: ����ȏ�K���X�L���͂���܂���B$s1���]�E�����Ă��痈�Ă��������B
	 */
	public static final SystemMessageId NO_SKILLS_TO_LEARN_RETURN_AFTER_S1_CLASS_CHANGE;
	
	/**
	 * ID: 2226<br>
	 * Message: �{���g������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_BOLTS;
	
	/**
	 * ID: 2227<br>
	 * Message: �_�񂵂Ă����ʂ̏�̍U���ɂ͍U�鑤�ɓo�^�͂ł��܂���B
	 */
	public static final SystemMessageId NOT_POSSIBLE_TO_REGISTER_TO_CASTLE_SIEGE;
	
	/**
	 * ID: 2228<br>
	 * Message: ���p���Ԑ�����Ԃ̃C���X�^���g �]�[���F
	 */
	public static final SystemMessageId INSTANCE_ZONE_TIME_LIMIT;
	
	/**
	 * ID: 2229<br>
	 * Message: ���p���Ԑ�����Ԃ̃C���X�^���g �]�[���͂���܂���B
	 */
	public static final SystemMessageId NO_INSTANCEZONE_TIME_LIMIT;
	
	/**
	 * ID: 2230<br>
	 * Message: $s1 $s2���� $s3����ɂ����p�ł��܂��B
	 */
	public static final SystemMessageId AVAILABLE_AFTER_S1_S2_HOURS_S3_MINUTES;
	
	/**
	 * ID: 2231<br>
	 * Message: �_�񂵂Ă����ʂ̏�̌��������l������Ȃ����߁A�⋋�i���x������܂���ł����B
	 */
	public static final SystemMessageId REPUTATION_SCORE_FOR_CONTRACT_NOT_ENOUGH;
	
	/**
	 * ID: 2232<br>
	 * Message: $s1���ӂ��O�ɃN���X�^���C�Y���܂��B�����܂����B
	 */
	public static final SystemMessageId S1_CRYSTALLIZED_BEFORE_DESTRUCTION;
	
	/**
	 * ID: 2233<br>
	 * Message: ��ʂ̏�ƌ_�񂵂Ă��邽�߁A�U��o�^�͂ł��܂���B
	 */
	public static final SystemMessageId CANT_REGISTER_TO_SIEGE_DUE_TO_CONTRACT;
	
	/**
	 * ID: 2234<br>
	 * Message: �I�������J�}�G����p�̉p�Y������g���܂����B
	 */
	public static final SystemMessageId CONFIRM_KAMAEL_HERO_WEAPON;
	
	/**
	 * ID: 2235<br>
	 * Message: �����p���̃C���X�^���g �]�[�������������߁A����ł��܂���B
	 */
	public static final SystemMessageId INSTANCE_ZONE_DELETED_CANT_ACCESSED;
	
	/**
	 * ID: 2236<br>
	 * Message: ���C�o�[�����掞�Ԃ͎c��$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_ON_WYVERN;
	
	/**
	 * ID: 2237<br>
	 * Message: ���C�o�[�����掞�Ԃ͎c��$s1�b�ł��B
	 */
	public static final SystemMessageId S1_SECONDS_LEFT_ON_WYVERN;
	
	/**
	 * ID: 2238<br>
	 * Message: $s1�̍U���ɎQ�킵�܂����B���̍U����2���ԍs���܂��B
	 */
	public static final SystemMessageId PARTICIPATING_IN_SIEGE_OF_S1;
	
	/**
	 * ID: 2239<br>
	 * Message: �Q�킵��$s1�̍U��킪�I�����܂����B
	 */
	public static final SystemMessageId SIEGE_OF_S1_FINIHSED;
	
	/**
	 * ID: 2240<br>
	 * Message: ������Ϗ��̐\�����ݎ葱�����ł��錌���̓`�[�� �o�g�� �A�W�g��ɓo�^�ł��܂���B
	 */
	public static final SystemMessageId CANT_REGISTER_TO_TEAM_BATTLE_CLAN_HALL_WAR_WHILE_LORD_ON_TRANSACTION_WAITING_LIST;
	
	/**
	 * ID: 2241<br>
	 * Message: �`�[�� �o�g�� �A�W�g��ɓo�^���������͌�����Ϗ��̐\�����݂͂ł��܂���B
	 */
	public static final SystemMessageId CANT_APPLY_ON_LORD_TRANSACTION_WHILE_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR;
	
	/**
	 * ID: 2242<br>
	 * Message: �`�[�� �o�g�� �A�W�g��ɓo�^�����������͒E�ނ������͒Ǖ����ł��܂���B
	 */
	public static final SystemMessageId MEMBERS_CANT_LEAVE_WHEN_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR;
	
	/**
	 * ID: 2243<br>
	 * Message: �R���̑��A�A�r�[�X�g �t�@�[�� �A�W�g�����L���錌���傪�ϔC����ꍇ�A�ύX��̌�����ł͂Ȃ��ύX�O�̌����傪�A�W�g��ɎQ�����܂��B
	 */
	public static final SystemMessageId WHEN_BANDITSTRONGHOLD_WILDBEASTRESERVRE_CLANLORD_IN_DANGER_PREVIOUS_LORD_PARTICIPATES_IN_BATTLE;
	
	/**
	 * ID: 2244<br>
	 * Message: �I�����Ԃ܂Ŏc��$s1���ł��B
	 */
	public static final SystemMessageId S1_MINUTES_REMAINING;
	
	/**
	 * ID: 2245<br>
	 * Message: �I�����Ԃ܂Ŏc��$s1�b�ł��B
	 */
	public static final SystemMessageId S1_SECONDS_REMAINING;
	
	/**
	 * ID: 2246<br>
	 * Message: $s1����ɗ\�I���n�܂�܂��B
	 */
	public static final SystemMessageId CONTEST_BEGIN_IN_S1_MINUTES;
	
	/**
	 * ID: 2247<br>
	 * Message: �ϐg���͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED;
	
	/**
	 * ID: 2248<br>
	 * Message: �Ή���Ԃł͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED;
	
	/**
	 * ID: 2249<br>
	 * Message: ���S��Ԃł͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD;
	
	/**
	 * ID: 2250<br>
	 * Message: �t�B�b�V���O���͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING;
	
	/**
	 * ID: 2251<br>
	 * Message: �퓬���͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE;
	
	/**
	 * ID: 2252<br>
	 * Message: �������͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL;
	
	/**
	 * ID: 2253<br>
	 * Message: ��������Ԃł͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING;
	
	/**
	 * ID: 2254<br>
	 * Message: �X�L���r�����͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_CASTING;
	
	/**
	 * ID: 2255<br>
	 * Message: ���ꂽ����𑕔�������Ԃł͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_CURSED_WEAPON_IS_EQUIPPED;
	
	/**
	 * ID: 2256<br>
	 * Message: ������������Ԃł͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG;
	
	/**
	 * ID: 2257<br>
	 * Message: �y�b�g����я����b������������Ԃł͏�蕨�ɓ���ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED;
	
	/**
	 * ID: 2258<br>
	 * Message: ���łɕʂ̏�蕨�ɓ��悵�Ă����Ԃł��B
	 */
	public static final SystemMessageId YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP;
	
	/**
	 * ID: 2259<br>
	 * Message: ���ݒn�F$s1�A$s2�A$s3 (���z�̓��t��)
	 */
	public static final SystemMessageId LOC_FANTASY_ISLAND_S1_S2_S3;
	
	/**
	 * ID: 2260<br>
	 * Message: �󕠊��Q�[�W��10���ȏ�ɖ����������Ȃ���΁A�y�b�g�������o�����Ƃ�����܂��B
	 */
	public static final SystemMessageId PET_CAN_RUN_AWAY_WHEN_HUNGER_BELOW_10_PERCENT;
	
	/**
	 * ID: 2261<br>
	 * Message: $c1��$c2��$s3�̃_���[�W��^���܂����B
	 */
	public static final SystemMessageId C1_GAVE_C2_DAMAGE_OF_S3;
	
	/**
	 * ID: 2262<br>
	 * Message: $c1��$c2����$s3�̃_���[�W�𕉂��܂����B
	 */
	public static final SystemMessageId C1_RECEIVED_DAMAGE_OF_S3_FROM_C2;
	
	/**
	 * ID: 2263<br>
	 * Message: $c1��$c2�ɂ��$s3�̃_���[�W�𕉂��܂����B
	 */
	public static final SystemMessageId C1_RECEIVED_DAMAGE_OF_S3_THROUGH_C2;
	
	/**
	 * ID: 2264<br>
	 * Message: $c1��$c2�̍U��������܂����B
	 */
	public static final SystemMessageId C1_EVADED_C2_ATTACK;
	
	/**
	 * ID: 2265<br>
	 * Message: $c1�̍U�����O��܂����B
	 */
	public static final SystemMessageId C1_ATTACK_WENT_ASTRAY;
	
	/**
	 * ID: 2266<br>
	 * Message: $c1�̃N���e�B�J�� �q�b�g�I
	 */
	public static final SystemMessageId C1_HAD_CRITICAL_HIT;
	
	/**
	 * ID: 2267<br>
	 * Message: $c1��$c2�̃h���C���ɒ�R���܂����B
	 */
	public static final SystemMessageId C1_RESISTED_C2_DRAIN;
	
	/**
	 * ID: 2268<br>
	 * Message: $c1���U���Ɏ��s���܂����B
	 */
	public static final SystemMessageId C1_ATTACK_FAILED;
	
	/**
	 * ID: 2269<br>
	 * Message: $c1��$c2�̖��@�ɒ�R���܂����B
	 */
	public static final SystemMessageId C1_RESISTED_C2_DRAIN2;
	
	/**
	 * ID: 2270<br>
	 * Message: ���@�̉��ɂ��$c1��$s2�̃_���[�W�𕉂��܂����B
	 */
	public static final SystemMessageId C1_RECEIVED_DAMAGE_FROM_S2_THROUGH_FIRE_OF_MAGIC;
	
	/**
	 * ID: 2271<br>
	 * Message: $c1��$c2�̖��@�Ɏ኱��R���܂����B
	 */
	public static final SystemMessageId C1_WEAKLY_RESISTED_C2_MAGIC;
	
	/**
	 * ID: 2272<br>
	 * Message: ��ʃ`���b�g ���[�h�ł͎w�肵���L�[���V���[�g�J�b�g�L�[�Ɏw�肷�邱�Ƃ͂ł��܂���B
	 */
	public static final SystemMessageId USE_SHORTCUT_CONFIRM;
	
	/**
	 * ID: 2273<br>
	 * Message: �T�u �N���X�̏�Ԃł͏K���ł��Ȃ��X�L���ł��B���C�� �N���X�ɕύX���Ă��������x�s���Ă��������B
	 */
	public static final SystemMessageId SKILL_NOT_FOR_SUBCLASS;
	
	/**
	 * ID: 2276<br>
	 * Message: ���W�X�^���X�̗v�ǂ�D�҂��܂����B
	 */
	public static final SystemMessageId NPCS_RECAPTURED_FORTRESS;
	
	/**
	 * ID: 2293<br>
	 * Message: ���ݒn�F$s1�A$s2�A$s3 (�|�S�̏����)
	 */
	public static final SystemMessageId LOC_STEEL_CITADEL_S1_S2_S3;
	
	/**
	 * ID: 2296<br>
	 * Message: �o�C�^���e�B �|�C���g���l�����܂����B
	 */
	public static final SystemMessageId GAINED_VITALITY_POINTS;
	
	/**
	 * ID: 2301<br>
	 * Message: ���ݒn�F�|�S�̏���s
	 */
	public static final SystemMessageId LOC_STEEL_CITADEL_RESISTANCE;
	
	/**
	 * ID: 2302<br>
	 * Message: �g�b�s���O �A�C�e�����������܂����B�e���̋��E�̏��l����v���[���g���󂯎��܂��B
	 */
	public static final SystemMessageId YOUR_VITAMIN_ITEM_HAS_ARRIVED;
	
	/**
	 * ID: 2303<br>
	 * Message: $s1�̍Ďg�p���Ԃ͎c��$s2�b�ł��B
	 */
	public static final SystemMessageId S2_SECONDS_REMAINING_FOR_REUSE_S1;
	
	/**
	 * ID: 2304<br>
	 * Message: $s1�̍Ďg�p���Ԃ͎c��$s2��$s3�b�ł��B
	 */
	public static final SystemMessageId S2_MINUTES_S3_SECONDS_REMAINING_FOR_REUSE_S1;
	
	/**
	 * ID: 2305<br>
	 * Message: $s1�̍Ďg�p���Ԃ͎c��$s2����$s3��$s4�b�ł��B
	 */
	public static final SystemMessageId S2_HOURS_S3_MINUTES_S4_SECONDS_REMAINING_FOR_REUSE_S1;
	
	/**
	 * ID: 2306<br>
	 * Message: �E�C�̂����̌��ʂɂ�蕜���ł��܂��B�������ɕ������܂����B\n
	 */
	public static final SystemMessageId RESURRECT_USING_CHARM_OF_COURAGE;
	
	/**
	 * ID: 2314<br>
	 * Message: �o�C�^���e�B���������ӂ�܂��B
	 */
	public static final SystemMessageId VITALITY_IS_AT_MAXIMUM;
	
	/**
	 * ID: 2315<br>
	 * Message: �o�C�^���e�B���������܂����B
	 */
	public static final SystemMessageId VITALITY_HAS_INCREASED;
	
	/**
	 * ID: 2316<br>
	 * Message: �o�C�^���e�B���������܂����B
	 */
	public static final SystemMessageId VITALITY_HAS_DECREASED;
	
	/**
	 * ID: 2317<br>
	 * Message: �o�C�^���e�B�����ׂĂȂ��Ȃ�܂����B
	 */
	public static final SystemMessageId VITALITY_IS_EXHAUSTED;
	
	/**
	 * ID: 2319<br>
	 * Message: $s1�̖����l����ɓ���܂����B
	 */
	public static final SystemMessageId ACQUIRED_S1_REPUTATION_SCORE;
	
	/**
	 * ID: 2321<br>
	 * Message: ���ݒn�F�J�}���J����
	 */
	public static final SystemMessageId LOC_KAMALOKA;
	
	/**
	 * ID: 2322<br>
	 * Message: ���ݒn�F�j�A �J�}���J����
	 */
	
	public static final SystemMessageId LOC_NIA_KAMALOKA;
	/**
	 * ID: 2323<br>
	 * Message: ���ݒn�F���� �J�}���J����
	 */
	public static final SystemMessageId LOC_RIM_KAMALOKA;
	
	/**
	 * ID: 2326<br>
	 * Message: ���������l 50�_���l�����܂����B
	 */
	public static final SystemMessageId ACQUIRED_50_CLAN_FAME_POINTS;
	
	/**
	 * ID: 2327<br>
	 * Message: �����l������܂���B
	 */
	public static final SystemMessageId NOT_ENOUGH_FAME_POINTS;
	
	/**
	 * ID: 2333<br>
	 * Message: �C���x���g���̏d��/�������𒴂��Ă��邽�߁A�g�b�s���O �A�C�e��������܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_RECEIVE_THE_VITAMIN_ITEM;
	
	/**
	 * ID: 2335<br>
	 * Message: �󂯎���g�b�s���O �A�C�e���͂���܂���B
	 */
	public static final SystemMessageId THERE_ARE_NO_MORE_VITAMIN_ITEMS_TO_BE_FOUND;
	
	/**
	 * ID: 2336<br>
	 * Message: �n�[�t�L���I
	 */
	public static final SystemMessageId CP_SIPHON;
	
	/**
	 * ID: 2337<br>
	 * Message: �n�[�t �L���X�L���ɓ�����CP�������܂��B
	 */
	public static final SystemMessageId CP_DISAPPEARS_WHEN_HIT_WITH_A_HALF_KILL_SKILL;
	
	/**
	 * ID: 2348<br>
	 * Message: �퓬���̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE;
	
	/**
	 * ID: 2349<br>
	 * Message: �U���A�v�ǐ�A�A�W�g��Ȃǂ̑�K�͐퓬�ւ̎Q�����̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING;
	
	/**
	 * ID: 2350<br>
	 * Message: �������̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL;
	
	/**
	 * ID: 2351<br>
	 * Message: ��s���̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING;
	
	/**
	 * ID: 2352<br>
	 * Message: �I�����s�A�[�h���Z�i�s���̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH;
	
	/**
	 * ID: 2353<br>
	 * Message: �Ή��A��჏�Ԃł̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_PARALYZED;
	
	/**
	 * ID: 2354<br>
	 * Message: ���S��Ԃł̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD;
	
	/**
	 * ID: 2355<br>
	 * Message: ���̒n��ł̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA;
	
	/**
	 * ID: 2356<br>
	 * Message: �����ł̓t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER;
	
	/**
	 * ID: 2357<br>
	 * Message: �C���X�^���g�]�[���ł� �t���[�e���|�[�g�@�\�͎g�p�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_IN_AN_INSTANT_ZONE;
	
	/**
	 * ID: 2358<br>
	 * Message: �e���|�[�g�ʒu��ۑ�����X�y�[�X������܂���B
	 */
	public static final SystemMessageId YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION;
	
	/**
	 * ID: 2359<br>
	 * Message: �e���|�[�g�A�C�e�����Ȃ����߁A�e���|�[�g�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM;
	
	/**
	 * ID: 2366<br>
	 * Message: ���Ԍ���A�C�e�����폜���܂����B
	 */
	public static final SystemMessageId TIME_LIMITED_ITEM_DELETED;
	
	/**
	 * 	2372	1	There is not much time remaining until the hunting helper pet leaves.
	 */
	public static final SystemMessageId THERE_NOT_MUCH_TIME_REMAINING_UNTIL_HELPER_LEAVES;
	
	/**
	 * 	2373	1	The hunting helper pet is now leaving.	0	B09B79			0	0	0	0	0		none
	 */
	public static final SystemMessageId THE_HELPER_PET_LEAVING;
	
	/**
	 * 	2375	1	The hunting helper pet cannot be returned because there is not much time remaining until it leaves.	0
	 */
	public static final SystemMessageId THE_HELPER_PET_CANNOT_BE_RETURNED;
	
	/**
	 * ID: 2376<br>
	 * Message: �������ɂ̓g�b�s���O �A�C�e�����󂯎��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_RECEIVE_A_VITAMIN_ITEM_DURING_AN_EXCHANGE;
	
	/**
	 * ID: 2390<br>
	 * Message: �t���[�e���|�[�g �X���b�g���ő���Ɋg������Ă��܂��B����ȏ�̊g���͂ł��܂���B
	 */
	public static final SystemMessageId YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT;
	
	/**
	 * ID: 2396<br>
	 * Message: �y�b�g/�����b�̂��̃X�L���͍Ďg�p���ł��邽�ߎg���܂���B
	 */
	public static final SystemMessageId PET_SKILL_CANNOT_BE_USED_RECHARCHING;
	
	/**
	 * ID: 2398<br>
	 * Message: �X���b�g����A�N�e�B�u�ɂȂ��Ă��܂��B
	 */
	public static final SystemMessageId YOU_HAVE_NO_OPEN_MY_TELEPORTS_SLOTS;
	
	/**
	 * ID: 2440<br>
	 * Message: $c1�̓N���X���������Z��ڂ̑ҋ@�҃��X�g�ɂ��łɓo�^����Ă��܂��B
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_NON_CLASS_LIMITED_EVENT_TEAMS;

	/**
	 * ID: 2441<br>
	 * Message: �`�[�����Z�̐\�����݂̓p�[�e�B�[���[�_�[�����ł��܂���B
	 */
	public static final SystemMessageId ONLY_PARTY_LEADER_CAN_REQUEST_TEAM_MATCH;

	/**
	 * ID: 2442<br>
	 * Message: ����������Ȃ����߁A�\�����݂ł��܂���B�`�[�����Z�ɎQ�����邽�߂ɂ�3�l�ȏ�̃p�[�e�B�[�����Ȃ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId PARTY_REQUIREMENTS_NOT_MET;

	/**
	 * ID: 2936<br>
	 * Message: �����̗̒n�ƃX�N���[������v���Ȃ����߁A�U���ł��܂���B
	 */
	public static final SystemMessageId THE_DISGUISE_SCROLL_MEANT_FOR_DIFFERENT_TERRITORY;
	
	/**
	 * ID: 2937<br>
	 * Message: �̒n�������Ă��錌���̌������͋U���ł��܂���B
	 */
	public static final SystemMessageId TERRITORY_OWNING_CLAN_CANNOT_USE_DISGUISE_SCROLL;
	
	/**
	 * ID: 2955<br>
	 * Message: �̒n���p�̋U���A�ϐg�͗̒n��J�n20���O����I��10����܂Ŏg���܂��B
	 */
	public static final SystemMessageId TERRITORY_WAR_SCROLL_CAN_NOT_USED_NOW;
	
	/**
	 * ID: 2400<br>
	 * Message: ���ݎg�p���̃C���X�^���g �]�[���F$s1
	 */
	public static final SystemMessageId INSTANT_ZONE_CURRENTLY_INUSE_S1;
	
	/**
	 * ID: 2402<br>
	 * Message: �̒n��̐\�����݂͊��ɒ��ߐ؂��Ă��܂��B
	 */
	public static final SystemMessageId THE_TERRITORY_WAR_REGISTERING_PERIOD_ENDED;
	
	/**
	 * ID: 2403<br>
	 * Message: �̒n��J�n�܂ł���10���ł��B
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_10_MINUTES;
	
	/**
	 * ID: 2404<br>
	 * Message: �̒n��J�n�܂ł���5���ł��B
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_5_MINUTES;
	
	/**
	 * ID: 2405<br>
	 * Message: �̒n��J�n�܂ł���1���ł��B
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_1_MINUTE;
	
	/**
	 * ID: 2408<br>
	 * Message: �N���X���������Z��ڂ̑ҋ@�҃��X�g�ɓo�^����܂����B
	 */
	public static final SystemMessageId YOU_HAVE_REGISTERED_IN_A_WAITING_LIST_OF_TEAM_GAMES;

	/**
	 * ID: 2409<br>
	 * Message: �t���[�e���|�[�g �X���b�g���g������܂����B
	 */
	public static final SystemMessageId THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED;
	
	/**
	 * ID: 2410<br>
	 * Message: �t���[�e���|�[�g�ł͍s���Ȃ��n��ł��B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA;
	
	/**
	 * ID: 2424<br>
	 * Message: �̏W�Ɏ��s���܂����B
	 */
	public static final SystemMessageId THE_COLLECTION_HAS_FAILED;
	
	/**
	 * ID: 2448<br>
	 * Message: �L�����N�^�[�̃o�[�X�f�C �v���[���g���͂��܂����B�e���̃Q�[�g�L�[�p�[����v���[���g���󂯎��܂��B
	 */
	public static final SystemMessageId YOUR_BIRTHDAY_GIFT_HAS_ARRIVED;
	
	/**
	 * ID: 2449<br>
	 * Message: �a�����܂ł���$s1���ł��B�e���̃Q�[�g�L�[�p�[����v���[���g���󂯎��܂��B
	 */
	public static final SystemMessageId THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY;
	
	/**
	 * ID: 2450<br>
	 * Message: $c1�̃L�����N�^�[�̃o�[�X�f�C��$s2�N$s3��$s4���ł��B
	 */
	public static final SystemMessageId C1_BIRTHDAY_IS_S3_S4_S2;
	
	/**
	 * ID: 2451<br>
	 * Message: �A�[�}�[ �Z�b�g�̑����������������߁A�N���[�N�̑�������������܂��B
	 */
	public static final SystemMessageId CLOAK_REMOVED_BECAUSE_ARMOR_SET_REMOVED;
	
	/**
	 * ID: 2455<br>
	 * Message: �������ꂽ��s�D�����݂��Ȃ����߁A����ł��܂���B
	 */
	public static final SystemMessageId THE_AIRSHIP_MUST_BE_SUMMONED_TO_BOARD;
	
	/**
	 * ID: 2456<br>
	 * Message: ��s�D���l������ɂ́A�������x����5�ȏ�łȂ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId THE_AIRSHIP_NEED_CLANLVL_5_TO_SUMMON;
	
	/**
	 * ID: 2457<br>
	 * Message: ��s�D������������͂��Ă��Ȃ����A�������L�̔�s�D�����݂��Ȃ����߁A��s�D�������ł��܂���B
	 */
	public static final SystemMessageId THE_AIRSHIP_NEED_LICENSE_TO_SUMMON;
	
	/**
	 * ID: 2458<br>
	 * Message: �������L�̔�s�D�͂��łɑ��̌��������g�p���Ă��܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_ALREADY_USED;
	
	/**
	 * ID: 2459<br>
	 * Message: ��s�D�������������łɊl�����Ă��܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_SUMMON_LICENSE_ALREADY_ACQUIRED;
	
	/**
	 * ID: 2460<br>
	 * Message: �������L�̔�s�D�͂��łɑ��݂��Ă��܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_IS_ALREADY_EXISTS;
	
	/**
	 * ID: 2461<br>
	 * Message: �������L�̔�s�D�͌�����̂ݔ����܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_NO_PRIVILEGES;
	
	/**
	 * ID: 2462<br>
	 * Message: $s1���s�����Ă��邽�߁A��s�D�������ł��܂���B
	 */
	public static final SystemMessageId THE_AIRSHIP_NEED_MORE_S1;
	
	/**
	 * ID: 2463<br>
	 * Message: ��s�D�̔R���iEP�j���Ȃ��Ȃ肻���ł��B
	 */
	public static final SystemMessageId THE_AIRSHIP_FUEL_SOON_RUN_OUT;
	
	/**
	 * ID: 2464<br>
	 * Message: ��s�D�̔R��(EP)�����ׂĎg���؂�܂����B���̏�Ԃł͔�s�D�̃X�s�[�h���啝�Ɍ������܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_FUEL_RUN_OUT;
	
	/**
	 * ID: 2465<br>
	 * Message: �N���X�������`�[�����Z��I�����܂����B�Q�����܂����B
	 */
	public static final SystemMessageId OLYMPIAD_3VS3_CONFIRM;
	
	/**
	 * ID: 2491<br>
	 * Message: ��s�D�̔R��������Ȃ����߁A�e���|�[�g�ł��܂���B
	 */
	public static final SystemMessageId THE_AIRSHIP_CANNOT_TELEPORT;
	
	/**
	 * ID: 2492<br>
	 * Message: ��s�D����������܂����B%s����Ɏ����I�ɏo�����܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_SUMMONED;
	
	/**
	 * ID: 2500<br>
	 * Message: �̏W�ɐ������܂����B
	 */
	public static final SystemMessageId THE_COLLECTION_HAS_SUCCEEDED;
	
	/**
	 * ID: 2701<br>
	 * Message: ���Z�������ł��B���΂炭���Ă���ēx���������������B
	 */
	public static final SystemMessageId MATCH_BEING_PREPARED_TRY_LATER;
	
	/**
	 * ID: 2702<br>
	 * Message: �l��������Ȃ����߁A�`�[�����珜�O����܂����B
	 */
	public static final SystemMessageId EXCLUDED_FROM_MATCH_DUE_INCORRECT_COUNT;
	
	/**
	 * ID: 2703<br>
	 * Message: �l���̔䗦������Ȃ����߁A�`�[������������܂����B
	 */
	public static final SystemMessageId TEAM_ADJUSTED_BECAUSE_WRONG_POPULATION_RATIO;
 		
 	/**
 	 * ID: 2704<br>
 	 * Message: ����𒴉߂������߁A�o�^�ł��܂���B
 	 */
	public static final SystemMessageId CANNOT_REGISTER_CAUSE_QUEUE_FULL;
	
	/**
	 * ID: 2705<br>
	 * Message: ���Z�ҋ@���Ԃ�1���ԉ�������܂����B
	 */
	public static final SystemMessageId MATCH_WAITING_TIME_EXTENDED;

	/**
	 * ID: 2706<br>
	 * Message: ����������Ȃ����߁A����ł��܂���B
	 */
	public static final SystemMessageId CANNOT_ENTER_CAUSE_DONT_MATCH_REQUIREMENTS;
	
	/**
	 * ID: 2707<br>
	 * Message: ���Z�o�^�L�����Z����10���b�Ԃ͍ēx�̐\�����݂͂ł��܂���B
	 */
	public static final SystemMessageId CANNOT_REQUEST_REGISTRATION_10_SECS_AFTER;
	
	/**
	 * ID: 2708<br>
	 * Message: ���ꂽ����������Ă��邽�߁A�o�^�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_REGISTER_PROCESSING_CURSED_WEAPON;
	
	/**
	 * ID: 2709<br>
	 * Message: �I�����s�A�[�h�A�n���R���V�A���A�N���g�̃L���[�u�ɐ\�����ݒ��͓o�^�ł��܂���B
	 */
	public static final SystemMessageId COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE;
	
	/**
	 * ID: 2710<br>
	 * Message: ���ݒn�F $s1, $s2, $s3�i�N�Z���X�����̘A����n�̂��΁j
	 */
	public static final SystemMessageId LOC_KEUCEREUS_S1_S2_S3;
	
	/**
	 * ID: 2711<br>
	 * Message: ���ݒn�F $s1, $s2, $s3�i�s�ł̎�̓����j
	 */
	public static final SystemMessageId LOC_IN_SEED_INFINITY_S1_S2_S3;
	
	/**
	 * ID: 2712<br>
	 * Message: ���ݒn�F $s1, $s2, $s3�i�j�ł̎�̓����j
	 */
	public static final SystemMessageId LOC_OUT_SEED_INFINITY_S1_S2_S3;
	
	/**
	 * ID: 2716<br>
	 * Message: ���ݒn�F $s1, $s2, $s3�i�N���t�g�̓����j
	 */
	public static final SystemMessageId LOC_CLEFT_S1_S2_S3;
	
	/**
	 * ID: 2720<br>
	 * Message: ������C���X�^���g �]�[���F$s1�ւ̓��ꂪ��������܂��B���̓���\���Ԃ𒲂ׂ�ɂ̓R�}���h�u/instantzone�v���g���܂��B
	 */
	public static final SystemMessageId INSTANT_ZONE_S1_RESTRICTED;
	
	/**
	 * ID: 2721<br>
	 * Message: �����͔�s�̂ɓ���A����������ł��Ȃ��n��ł��B
	 */
	public static final SystemMessageId BOARD_OR_CANCEL_NOT_POSSIBLE_HERE;
	
	/**
	 * ID: 2722<br>
	 * Message: ������ɂ͑��̔�s�D����������Ă��܂��B�܂���قǂ����p���������B
	 */
	public static final SystemMessageId ANOTHER_AIRSHIP_ALREADY_SUMMONED;
	
	/**
	 * ID: 2727<br>
	 * Message: ����������Ȃ����ߏ��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_NOT_MEET_REQUEIREMENTS;
	
	/**
	 * ID: 2729<br>
	 * Message: �ϐg���ɂ͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_TRANSFORMED;
	
	/**
	 * ID: 2730<br>
	 * Message: �Ή���Ԃł͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_YOU_ARE_PETRIFIED;
	
	/**
	 * ID: 2731<br>
	 * Message: ���񂾏�Ԃł͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHEN_YOU_ARE_DEAD;
	
	/**
	 * ID: 2732<br>
	 * Message: �ނ蒆�ɂ͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_FISHING;
	
	/**
	 * ID: 2733<br>
	 * Message: �퓬���ɂ͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_BATTLE;
	
	/**
	 * ID: 2734<br>
	 * Message: �������ɂ͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_DUEL;
	/**
	 * ID: 2735<br>
	 * Message: ��������Ԃł͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_SITTING_POSITION;
	
	/**
	 * ID: 2736<br>
	 * Message: �X�L���r�����ɂ͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_USING_A_SKILL;
	
	/**
	 * ID: 2737<br>
	 * Message: ���ꂽ����𑕒�������Ԃł͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_A_CURSED_WEAPON_IS_EQUIPPED;
	
	/**
	 * ID: 2738<br>
	 * Message: ������������Ԃł͑Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_HOLDING_A_FLAG;
	
	/**
	 * ID: 2750<br>
	 * Message: $s1��앨�A�j��I��앨��$c2���D�悵�܂����B
	 */
	public static final SystemMessageId THE_S1_WARD_HAS_BEEN_DESTROYED;
	
	/**
	 * ID: 2751<br>
	 * Message: $s1��앨���E�������L�����N�^�[�����S���܂����B
	 */
	public static final SystemMessageId THE_CHAR_THAT_ACQUIRED_S1_WARD_HAS_BEEN_KILLED;
	
	/**
	 * ID: 2762<br>
	 * Message: ����������Ă��邽�߁A�Ώۂ𑀏c�ł��܂���B
	 */
	public static final SystemMessageId CANT_CONTROL_TOO_FAR;
	
	/**
	 * ID: 2766<br>
	 * Message: �s�ł̎탌�x��1�U����
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_1_ATTACK_IN_PROGRESS;
	
	/**
	 * ID: 2767<br>
	 * Message: �s�ł̎탌�x��2�U����
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_2_ATTACK_IN_PROGRESS;
	
	/**
	 * ID: 2768<br>
	 * Message: �s�ł̎��̊���
	 */
	public static final SystemMessageId SEED_OF_INFINITY_CONQUEST_COMPLETE;
	
	/**
	 * ID: 2769<br>
	 * Message: �s�ł̎탌�x��1�h�䒆
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_1_DEFENSE_IN_PROGRESS;
	
	/**
	 * ID: 2770<br>
	 * Message: �s�ł̎탌�x��2�h�䒆
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_2_DEFENSE_IN_PROGRESS;
	
	/**
	 * ID: 2771<br>
	 * Message: �j�ł̎�U����
	 */
	public static final SystemMessageId SEED_OF_DESTRUCTION_ATTACK_IN_PROGRESS;
	
	/**
	 * ID: 2772<br>
	 * Message: �j�ł̎��̊���
	 */
	public static final SystemMessageId SEED_OF_DESTRUCTION_CONQUEST_COMPLETE;
	
	/**
	 * ID: 2773<br>
	 * Message: �j�ł̎�h�䒆
	 */
	public static final SystemMessageId SEED_OF_DESTRUCTION_DEFENSE_IN_PROGRESS;
	
	/**
	 * ID: 2777<br>
	 * Message: ��s�D�̏������������͂ł��܂����B�M�����͍���A��s�D�̏������ł���悤�ɂȂ�܂��B
	 */
	public static final SystemMessageId THE_AIRSHIP_SUMMON_LICENSE_ENTERED;
	
	/**
	 * ID: 2778<br>
	 * Message: ��앨�������Ă����Ԃł͏u�Ԉړ����ʂ��K�p����܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD;
	
	/**
	 * ID: 2795<br>
	 * Message: ���ɑ��̗̒n���\������ł����Ԃł��B
	 */
	public static final SystemMessageId YOU_ALREADY_REQUESTED_TW_REGISTRATION;
	
	/**
	 * ID: 2796<br>
	 * Message: �̒n�������Ă��錌���͗̒n��ɗb���̎��i�ł͎Q��ł��܂���B
	 */
	public static final SystemMessageId THE_TERRITORY_OWNER_CLAN_CANNOT_PARTICIPATE_AS_MERCENARIES;
	
	/**
	 * ID: 2797<br>
	 * Message: ���͗̒n��\�����Ԃł͂���܂���B
	 */
	public static final SystemMessageId NOT_TERRITORY_REGISTRATION_PERIOD;
	
	/**
	 * ID: 2798<br>
	 * Message: �̒n��I��$s1���ԑO�I
	 */
	public static final SystemMessageId THE_TERRITORY_WAR_WILL_END_IN_S1_HOURS;
	
	/**
	 * ID: 2799<br>
	 * Message: �̒n��I��$s1���O�I
	 */
	public static final SystemMessageId THE_TERRITORY_WAR_WILL_END_IN_S1_MINUTES;
	
	/**
	 * ID: 2900<br>
	 * Message: �̒n��I��$s1�b�O�I
	 */
	public static final SystemMessageId S1_SECONDS_TO_THE_END_OF_TERRITORY_WAR;
	
	/**
	 * ID: 2901<br>
	 * Message: �Ώۂ������̒n�����̂��߁A�����U���͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY;
	
	/**
	 * ID: 2902<br>
	 * Message: ��앨�𓾂܂����B���}�ɖ����̑O����n�Ɉړ����Ă��������B
	 */
	public static final SystemMessageId YOU_VE_ACQUIRED_THE_WARD;
	
	/**
	 * ID: 2903<br>
	 * Message: �̒n�킪�n�܂�܂����B
	 */
	public static final SystemMessageId TERRITORY_WAR_HAS_BEGUN;
	
	/**
	 * ID: 2904<br>
	 * Message: �̒n�킪�I���܂����B
	 */
	public static final SystemMessageId TERRITORY_WAR_HAS_ENDED;
	
	/**
	 * ID: 2911<br>
	 * Message: $c1�ɗF�l�o�^��\�����݂܂����B
	 */
	public static final SystemMessageId YOU_REQUESTED_C1_TO_BE_FRIEND;
	
	/**
	 * ID: 2913<br>
	 * Message: $s1������$s2��앨�̒D��ɐ������܂����B
	 */
	public static final SystemMessageId CLAN_S1_HAS_SUCCEDED_IN_CAPTURING_S2_TERRITORY_WARD;
	
	/**
	 * ID: 2914<br>
	 * Message: �̒n��J�n20���O�ł��B�������܂�����`�����l�����A�N�e�B�u�ɂȂ�܂��B��p�U���A�ϑ����ł��܂��B
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_20_MINUTES;
	
	/**
	 * ID: 2922<br>
	 * Message: �u���b�N�`�F�b�J�[�I��5�b�O�I
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_5;
	
	/**
	 * ID: 2923<br>
	 * Message: �u���b�N�`�F�b�J�[�I��4�b�O�I
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_4;
	
	/**
	 * ID: 2924<br>
	 * Message: ��s�ϑ̏�Ԃł͎�̓����ɂ͓���܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_ENTER_SEED_IN_FLYING_TRANSFORM;
	
	/**
	 * ID: 2925<br>
	 * Message: �u���b�N�`�F�b�J�[�I��3�b�O�I
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_3;

	/**
	 * ID: 2926<br>
	 * Message: �u���b�N�`�F�b�J�[�I��2�b�O�I
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_2;

	/**
	 * ID: 2927<br>
	 * Message: �u���b�N�`�F�b�J�[�I��1�b�O�I
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_1;
	
	/**
	 * ID: 2928<br>
	 * Message: $c1�̃`�[�������Z�ŏ������܂����B
	 */
	public static final SystemMessageId TEAM_C1_WON;
	
	/**
	 * ID: 2961<br>
	 * Message: �A�C�e��$s1��$s2�K�v�ł��B
	 */
	public static final SystemMessageId S2_UNIT_OF_THE_ITEM_S1_REQUIRED;
	
	/**
	 * ID: 2964<br>
	 * Message: �m�[�u���X�ɔC�������ꍇ�A���s���̊֘A�N�G�X�g���폜����܂��B��낵���ł����B
	 */
	public static final SystemMessageId CANCEL_NOBLESSE_QUESTS;
	
	/**
	 * ID: 2966<br>
	 * Message: ����X�ւ̓A�C�e����Y�t���Ȃ���΍��o�ł��܂���B�A�C�e����Y�t���Ă��������B
	 */
	public static final SystemMessageId PAYMENT_REQUEST_NO_ITEM;
	
	/**
	 * ID: 2968<br>
	 * Message: ���̃L�����N�^�[�̗X�ւ̌��x��(240��)�𒴂������߁A���o�ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_MAIL_LIMIT_EXCEEDED;
	
	/**
	 * ID: 2969<br>
	 * Message: �O��X�ւ����o���Ă���10�b�o���Ă��Ȃ����߁A���o�ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_LESS_THAN_MINUTE;
	
	/**
	 * ID: 2970<br>
	 * Message: �s�[�X�]�[���łȂ��Ƃ���ɂ͔����ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_NOT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2971<br>
	 * Message: �g���[�h���ɂ͔����ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_DURING_EXCHANGE;
	
	/**
	 * ID: 2972<br>
	 * Message: �l���X�A�܂��͍H�[���g�p���̂��ߔ����ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_PRIVATE_STORE;
	
	/**
	 * ID: 2973<br>
	 * Message: �A�C�e�������A�����������ɂ͔����ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_DURING_ENCHANT;
	
	/**
	 * ID: 2974<br>
	 * Message: ��������A�C�e�����K�؂łȂ����ߔ����ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_BAD_ITEM;
	
	/**
	 * ID: 2975<br>
	 * Message: �A�f�i���s�����Ă��邽�ߔ����ł��܂���B
	 */
	public static final SystemMessageId CANT_FORWARD_NO_ADENA;
	
	/**
	 * ID: 2976<br>
	 * Message: �s�[�X�]�[���łȂ��Ƃ���ł͎�M�ł��܂���B
	 */
	public static final SystemMessageId CANT_RECEIVE_NOT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2977<br>
	 * Message: �g���[�h���ɂ͎�M�ł��܂���B
	 */
	public static final SystemMessageId CANT_RECEIVE_DURING_EXCHANGE;
	
	/**
	 * ID: 2978<br>
	 * Message: �l���X�A�܂��͍H�[���ł��邽�ߎ�M�ł��܂���B
	 */
	public static final SystemMessageId CANT_RECEIVE_PRIVATE_STORE;
	
	/**
	 * ID: 2979<br>
	 * Message: �A�C�e�������A�����������ɂ͎�M�ł��܂���B
	 */
	public static final SystemMessageId CANT_RECEIVE_DURING_ENCHANT;
	
	/**
	 * ID: 2980<br>
	 * Message: �A�f�i���s�����Ă��邽�ߎ�M�ł��܂���B
	 */
	public static final SystemMessageId CANT_RECEIVE_NO_ADENA;
	
	/**
	 * ID: 2981<br>
	 * Message: ���Ɏ��s���܂����B
	 */
	public static final SystemMessageId CANT_RECEIVE_INVENTORY_FULL;
	
	/**
	 * ID: 2982<br>
	 * Message: �s�[�X�]�[���łȂ��Ƃ���ɂ̓L�����Z���ł��܂���B
	 */
	public static final SystemMessageId CANT_CANCEL_NOT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2983<br>
	 * Message: �g���[�h���ɂ̓L�����Z���ł��܂���B
	 */
	public static final SystemMessageId CANT_CANCEL_DURING_EXCHANGE;
	
	/**
	 * ID: 2984<br>
	 * Message: �l���X�A�܂��͍H�[���ł��邽�߃L�����Z���ł��܂���B
	 */
	public static final SystemMessageId CANT_CANCEL_PRIVATE_STORE;
	
	/**
	 * ID: 2985<br>
	 * Message: �A�C�e�������A�����������ɂ̓L�����Z���ł��܂���B
	 */
	public static final SystemMessageId CANT_CANCEL_DURING_ENCHANT;
	
	/**
	 * ID: 2988<br>
	 * Message: �C���x���g���@�G���[�̂��߁A�����L�����Z���ł��܂���ł����B
	 */
	public static final SystemMessageId CANT_CANCEL_INVENTORY_FULL;
	
	/**
	 * ID: 3002<br>
	 * Message: ��M�҂����݂��Ȃ����A�폜���ꂽ�L�����N�^�[�ɂ͗X�֔������ł��܂���B
	 */
	public static final SystemMessageId RECIPIENT_NOT_EXIST;
	
	/**
	 * ID: 3008<br>
	 * Message: �X�ւ��͂��܂����B
	 */
	public static final SystemMessageId MAIL_ARRIVED;
	
	/**
	 * ID: 3009<br>
	 * Message: �X�ւ̍��o���������܂����B
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_SENT;
	
	/**
	 * ID: 3010<br>
	 * Message: �X�ւ̕ԑ����������܂����B
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_RETURNED;
	
	/**
	 * ID: 3011<br>
	 * Message: �X�ւ̃L�����Z�����������܂����B
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_CANCELLED;
	
	/**
	 * ID: 3012<br>
	 * Message: �X�ւ̎�悪�������܂����B
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_RECEIVED;
	
	/**
	 * ID: 3013<br>
	 * Message: $c1��+$s2$s3�G���`�����g�ɐ������܂����B
	 */
	public static final SystemMessageId C1_SUCCESSFULY_ENCHANTED_A_S2_S3;
	
	/**
	 * ID: 3014<br>
	 * Message: �I���������[�����폜���܂����B
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_ERASE_MAIL;
	
	/**
	 * ID: 3015<br>
	 * Message: �폜���郁�[����I�����Ă��������B
	 */
	public static final SystemMessageId PLEASE_SELECT_MAIL_TO_BE_DELETED;
	
	/**
	 * ID: 3016<br>
	 * Message: �A�C�e���̓Y�t�͍ő�8�܂łł��܂��B
	 */
	public static final SystemMessageId ITEM_SELECTION_POSSIBLE_UP_TO_8;
	
	/**
	 * ID: 3019<br>
	 * Message: �������g�ɂ͗X�ւ����o�ł��܂���B
	 */
	public static final SystemMessageId YOU_CANT_SEND_MAIL_TO_YOURSELF;
	
	/**
	 * ID: 3020<br>
	 * Message: ����X�ւ͎󂯎����z����͂��Ȃ���΍��o�ł��܂���B
	 */
	public static final SystemMessageId PAYMENT_AMOUNT_NOT_ENTERED;
	
	/**
	 * ID: 3023<br>
	 * Message: �J�[�V���̖ڂ��痬��o��C���}���ɋ��܂����̂��������܂��B
	 */
	public static final SystemMessageId I_CAN_FEEL_ENERGY_KASHA_EYE_GETTING_STRONGER_RAPIDLY;
	
	/**
	 * ID: 3024<br>
	 * Message: �J�[�V���̖ڂ����ɂł��j�􂹂�΂���̐����ł���ǂ肤���܂��B
	 */
	public static final SystemMessageId KASHA_EYE_PITCHES_TOSSES_EXPLODE;
	
	/**
	 * ID: 3025<br>
	 * Message: $s2��������x�����܂����B$s1�A�f�i���l�����܂����B
	 */
	public static final SystemMessageId PAYMENT_OF_S1_ADENA_COMPLETED_BY_S2;
	
	/**
	 * ID: 3026<br>
	 * Message: �X�L�� �G���`�����g�@�\���g���郌�x���ł͂���܂���B���̋@�\���g���ɂ́A���x��76�ȏ�łȂ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_SKILL_ENCHANT_ON_THIS_LEVEL;
	
	/**
	 * ID: 3027<br>
	 * Message: �X�L�� �G���`�����g�@�\���g����N���X�ł͂���܂���B���̋@�\���g���ɂ́A3���]�E���I���Ă��Ȃ���΂Ȃ�܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_SKILL_ENCHANT_IN_THIS_CLASS;
	
	/**
	 * ID: 3028<br>
	 * Message: �X�L�� �G���`�����g�@�\���g�����Ԃł͂���܂���B���̋@�\�͔�퓬��ԂŎg���܂��B�ϐg�A�퓬�A����̊e��Ԃł͎g���܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_USE_SKILL_ENCHANT_ATTACKING_TRANSFORMED_BOAT;
	
	/**
	 * ID: 3029<br>
	 * Message: $s1���X�ւ�ԑ����܂����B
	 */
	public static final SystemMessageId S1_RETURNED_MAIL;
	
	/**
	 * ID: 3030<br>
	 * Message: ���l�����łɎ󂯎���Ă��邽�߁A���o�̃L�����Z���͂ł��܂���B
	 */
	public static final SystemMessageId YOU_CANT_CANCEL_RECEIVED_MAIL;
	
	/**
	 * ID: 3059<br>
	 * Message: $s1�����ҋ@���Ԓ��Ɏ󂯎��Ȃ��������߁A�����I�ɕԑ�����܂����B
	 */
	public static final SystemMessageId S1_NOT_RECEIVE_DURING_WAITING_TIME_MAIL_RETURNED;
	
	/**
	 * ID: 3062<br>
	 * Message: $s1�A�f�i�Ō��ς��܂����B
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_PAY_S1_ADENA;
	
	/**
	 * ID: 3063<br>
	 * Message: �{���ɕԑ����܂����B
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_FORWARD;
	
	/**
	 * ID: 3064<br>
	 * Message: ���ǂ̗X�ւ�����܂��B
	 */
	public static final SystemMessageId UNREAD_MAIL;
	
	/**
	 * ID: 3065<br>
	 * Message: ���ݒn�F�����̌��E����
	 */
	public static final SystemMessageId LOC_DELUSION_CHAMBER;
	
	/**
	 * ID: 3066<br>
	 * Message: �s�[�X�]�[���ȊO�̏ꏊ�ł́A�X�ւɓY�t���ꂽ�A�C�e���̎��⍷�o�͂ł��܂���B���ʂ����邱�Ƃ����\�ł��B
	 */
	public static final SystemMessageId CANT_USE_MAIL_OUTSIDE_PEACE_ZONE;
	
	/**
	 * ID: 3067<br>
	 * Message: $s1���X�ւ̍��o���L�����Z�����܂����B
	 */
	public static final SystemMessageId S1_CANCELLED_MAIL;
	
	/**
	 * ID: 3068<br>
	 * Message: ���ҋ@���Ԃ��߂������߁A�X�ւ��ԑ�����܂����B
	 */
	public static final SystemMessageId MAIL_RETURNED;
	
	/**
	 * ID: 3069<br>
	 * Message: �{���Ɏ�����L�����Z�����܂����B
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_CANCEL_TRANSACTION;
	
	/**
	 * ID: 3072<br>
	 * Message: $s1���X�ւɓY�t���ꂽ�A�C�e�����l�����܂����B
	 */
	public static final SystemMessageId S1_ACQUIRED_ATTACHED_ITEM;
	
	/**
	 * ID: 3073<br>
	 * Message: $s1�A�C�e����$s2�l�����܂����B
	 */
	public static final SystemMessageId YOU_ACQUIRED_S2_S1;
	
	/**
	 * ID: 3074<br>
	 * Message: �K��̎��l���̒����𒴂��Ă��܂��B
	 */
	public static final SystemMessageId ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED;
	
	/**
	 * ID: 3075<br>
	 * Message: �K��̃^�C�g���̒����𒴂��Ă��܂��B
	 */
	public static final SystemMessageId ALLOWED_LENGTH_FOR_TITLE_EXCEEDED;
	
	/**
	 * ID: 3077<br>
	 * Message: ����̃L�����N�^�[���X�ւ̌��x��(240��)�𒴂������߁A���o�ł��܂���B
	 */
	public static final SystemMessageId MAIL_LIMIT_EXCEEDED;
	
	/**
	 * ID: 3078<br>
	 * Message: ����X�ւł��B�{���ɍ����o���܂����B
	 */
	public static final SystemMessageId YOU_MAKING_PAYMENT_REQUEST;
	
	/**
	 * ID: 3079<br>
	 * Message: �y�b�g �C���x���g���ɃA�C�e�������邽�߁A�y�b�g�̔̔��A�l���X�̔̔��o�^�A�A�C�e���̃h���b�v�͂ł��܂���B�y�b�g �C���x���g������ɂ��Ă��������B
	 */
	public static final SystemMessageId ITEMS_IN_PET_INVENTORY;
	
	/**
	 * ID: 3080<br>
	 * Message: �A�f�i���s�����Ă��邽�߃X�L�� �����N�����Z�b�g�ł��܂���B
	 */
	public static final SystemMessageId CANNOT_RESET_SKILL_LINK_BECAUSE_NOT_ENOUGH_ADENA;
	
	/**
	 * ID: 3081<br>
	 * Message: ���肪���σA�f�i���l���ł��Ȃ������ɂȂ��Ă��邽�߁A�󂯎��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_RECEIVE_CONDITION_OPPONENT_CANT_ACQUIRE_ADENA;
	
	/**
	 * ID: 3082<br>
	 * Message: �Ւf���Ă���L�����N�^�[�ɂ͗X�ւ������o���܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_SEND_MAIL_TO_CHAR_BLOCK_YOU;
	
	/**
	 * ID: 3108<br>
	 * Message: ����A�A�N�e�B�u �����X�^�[���h�����邨���ꂪ����܂��B
	 */
	public static final SystemMessageId YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS;
	
	/**
	 * ID: 3119<br>
	 * Message: �J�b�v�� �A�N�V�����̐\�����݂����ۂ���܂����B
	 */
	public static final SystemMessageId COUPLE_ACTION_DENIED;
	
	/**
	 * ID: 3120<br>
	 * Message: �ΏۂƂ̈ʒu����������Ȃ����ߐ\�����݂ł��܂���B
	 */
	public static final SystemMessageId TARGET_DO_NOT_MEET_LOC_REQUIREMENTS;
	
	/**
	 * ID: 3121<br>
	 * Message: �J�b�v�� �A�N�V�������L�����Z������܂����B
	 */
	public static final SystemMessageId COUPLE_ACTION_CANCELED;
	
	/**
	 * ID: 3135<br>
	 * Message: $s1�ւ̕ύX�ɂ��āA���ӂ����߂Ă��܂��B
	 */
	public static final SystemMessageId REQUESTING_APPROVAL_CHANGE_PARTY_LOOT_S1;
	
	/**
	 * ID: 3137<br>
	 * Message: �A�C�e�����z�^�C�v�̕ύX���L�����Z������܂����B
	 */
	public static final SystemMessageId PARTY_LOOT_CHANGE_CANCELLED;
	
	/**
	 * ID: 3138<br>
	 * Message: �A�C�e�����z�^�C�v��$s1�ɕύX����܂����B
	 */
	public static final SystemMessageId PARTY_LOOT_CHANGED_S1;
	
	/**
	 * ID: 3144<br>
	 * Message: $s1��$s2�̑�����t�^���܂����B$s3�ɑ΂���ϐ������サ�܂����B
	 */
	public static final SystemMessageId THE_S2_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_RES_TO_S3_INCREASED;
	
	/**
	 * ID: 3150<br>
	 * Message: $c1�ɃJ�b�v�� �A�N�V������\�����݂܂����B
	 */
	public static final SystemMessageId YOU_HAVE_REQUESTED_COUPLE_ACTION_C1;
	
	/**
	 * ID: 3152<br>
	 * Message: $s1��$s2�̑������������܂����B$s3�ɑ΂���ϐ����ቺ���܂����B
	 */
	public static final SystemMessageId S1_S2_ATTRIBUTE_REMOVED_RESISTANCE_S3_DECREASED;
	
	/**
	 * ID: 3156<br>
	 * Message: �萔�����s�����Ă��邽�߁A�����������ł��܂���B
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_FUNDS_TO_CANCEL_ATTRIBUTE;
	
	/**
	 * ID: 3160<br>
	 * Message: +$s1$s2��$s3�̑������������܂����B$s4�ɑ΂���ϐ����ቺ���܂����B
	 */
	public static final SystemMessageId S1_S2_S3_ATTRIBUTE_REMOVED_RESISTANCE_TO_S4_DECREASED;
	
	/**
	 * ID: 3163<br>
	 * Message: +$s1$s2��$s3�̑�����t�^���܂����B$s4�ɑ΂���ϐ������サ�܂����B
	 */
	public static final SystemMessageId THE_S3_ATTRIBUTE_BESTOWED_ON_S1_S2_RESISTANCE_TO_S4_INCREASED;
	
	/**
	 * ID: 3164<br>
	 * Message: $c1�͂��łɃJ�b�v�����ۏ�Ԃ̂��߁A�V���ɃJ�b�v�� �A�N�V�����̐\�����݂͂ł��܂���B
	 */
	public static final SystemMessageId C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS;
	
	/**
	 * ID: 3168<br>
	 * Message: $c1�͂��łɃJ�b�v�����ۏ�Ԃ̂��߁A�V���Ƀp�[�e�B�[�̐\�����݂͂ł��܂���B
	 */
	public static final SystemMessageId C1_IS_SET_TO_REFUSE_PARTY_REQUEST;
	
	/**
	 * ID: 3169<br>
	 * Message: $c1�͂��łɌ����\�����ۏ�Ԃ̂��߁A�V���Ɍ����̐\�����݂͂ł��܂���B
	 */
	public static final SystemMessageId C1_IS_SET_TO_REFUSE_DUEL_REQUEST;
	
	/**
	 * ID: 3206<br>
	 * Message: ���݁A�����Ă��鐄�E���͂���܂���B
	 */
	public static final SystemMessageId YOU_CURRENTLY_DO_NOT_HAVE_ANY_RECOMMENDATIONS;
	
	/**
	 * ID: 3207<br>
	 * Message: ���E����$s1��ɓ���܂����B
	 */
	public static final SystemMessageId YOU_OBTAINED_S1_RECOMMENDATIONS;
	
	/**
	 * ID: 6501<br>
	 * Message: �t���[�e���|�[�g�̊����Ȃ����߁A�u�b�N�}�[�N��ۑ��ł��܂���B
	 */
	public static final SystemMessageId YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG;
	
	/**
	 * ID: 6503<br>
	 * Message: �L���v�e�� �^�[�L�[������܂����B�N���X�}�X �T���^���~���o���܂��傤�B
	 */
	public static final SystemMessageId THOMAS_D_TURKEY_APPEARED;
	
	/**
	 * ID: 6504<br>
	 * Message: �L���v�e�� �^�[�L�[�Ƃ̐킢�ɏ����āA�N���X�}�X �T���^���~���o���܂����B
	 */
	public static final SystemMessageId THOMAS_D_TURKEY_DEFETED;
	
	/**
	 * ID: 6505<br>
	 * Message: �N���X�}�X �T���^���~���o���Ȃ��܂܁A�L���v�e�� �^�[�L�[�������Ă��܂��܂����B
	 */
	public static final SystemMessageId THOMAS_D_TURKEY_DISAPPEARED;
	
	/**
	 * Array containing all SystemMessageIds<br>
	 * Important: Always initialize with a length of the highest SystemMessageId + 1!!!
	 */
	private static SystemMessageId[] VALUES;
	
	static
	{
		YOU_HAVE_BEEN_DISCONNECTED = new SystemMessageId(0);
		THE_SERVER_WILL_BE_COMING_DOWN_IN_S1_SECONDS = new SystemMessageId(1);
		S1_DOES_NOT_EXIST = new SystemMessageId(2);
		S1_IS_NOT_ONLINE = new SystemMessageId(3);
		CANNOT_INVITE_YOURSELF = new SystemMessageId(4);
		S1_ALREADY_EXISTS = new SystemMessageId(5);
		S1_DOES_NOT_EXIST2 = new SystemMessageId(6);
		ALREADY_MEMBER_OF_S1 = new SystemMessageId(7);
		YOU_ARE_WORKING_WITH_ANOTHER_CLAN = new SystemMessageId(8);
		S1_IS_NOT_A_CLAN_LEADER = new SystemMessageId(9);
		S1_WORKING_WITH_ANOTHER_CLAN = new SystemMessageId(10);
		NO_APPLICANTS_FOR_THIS_CLAN = new SystemMessageId(11);
		APPLICANT_INFORMATION_INCORRECT = new SystemMessageId(12);
		CANNOT_DISSOLVE_CAUSE_CLAN_WILL_PARTICIPATE_IN_CASTLE_SIEGE = new SystemMessageId(13);
		CANNOT_DISSOLVE_CAUSE_CLAN_OWNS_CASTLES_HIDEOUTS = new SystemMessageId(14);
		YOU_ARE_IN_SIEGE = new SystemMessageId(15);
		YOU_ARE_NOT_IN_SIEGE = new SystemMessageId(16);
		CASTLE_SIEGE_HAS_BEGUN = new SystemMessageId(17);
		CASTLE_SIEGE_HAS_ENDED = new SystemMessageId(18);
		NEW_CASTLE_LORD = new SystemMessageId(19);
		GATE_IS_OPENING = new SystemMessageId(20);
		GATE_IS_DESTROYED = new SystemMessageId(21);
		TARGET_TOO_FAR = new SystemMessageId(22);
		NOT_ENOUGH_HP = new SystemMessageId(23);
		NOT_ENOUGH_MP = new SystemMessageId(24);
		REJUVENATING_HP = new SystemMessageId(25);
		REJUVENATING_MP = new SystemMessageId(26);
		CASTING_INTERRUPTED = new SystemMessageId(27);
		YOU_PICKED_UP_S1_ADENA = new SystemMessageId(28);
		YOU_PICKED_UP_S1_S2 = new SystemMessageId(29);
		YOU_PICKED_UP_S1 = new SystemMessageId(30);
		CANT_MOVE_SITTING = new SystemMessageId(31);
		UNABLE_COMBAT_PLEASE_GO_RESTART = new SystemMessageId(32);
		CANT_MOVE_CASTING = new SystemMessageId(33);
		WELCOME_TO_LINEAGE = new SystemMessageId(34);
		YOU_DID_S1_DMG = new SystemMessageId(35);
		C1_GAVE_YOU_S2_DMG = new SystemMessageId(36);
		C1_GAVE_YOU_S2_DMG2 = new SystemMessageId(37);
		GETTING_READY_TO_SHOOT_AN_ARROW = new SystemMessageId(41);
		AVOIDED_C1_ATTACK = new SystemMessageId(42);
		MISSED_TARGET = new SystemMessageId(43);
		CRITICAL_HIT = new SystemMessageId(44);
		EARNED_S1_EXPERIENCE = new SystemMessageId(45);
		USE_S1 = new SystemMessageId(46);
		BEGIN_TO_USE_S1 = new SystemMessageId(47);
		S1_PREPARED_FOR_REUSE = new SystemMessageId(48);
		S1_EQUIPPED = new SystemMessageId(49);
		TARGET_CANT_FOUND = new SystemMessageId(50);
		CANNOT_USE_ON_YOURSELF = new SystemMessageId(51);
		EARNED_S1_ADENA = new SystemMessageId(52);
		EARNED_S2_S1_S = new SystemMessageId(53);
		EARNED_ITEM_S1 = new SystemMessageId(54);
		FAILED_TO_PICKUP_S1_ADENA = new SystemMessageId(55);
		FAILED_TO_PICKUP_S1 = new SystemMessageId(56);
		FAILED_TO_PICKUP_S2_S1_S = new SystemMessageId(57);
		FAILED_TO_EARN_S1_ADENA = new SystemMessageId(58);
		FAILED_TO_EARN_S1 = new SystemMessageId(59);
		FAILED_TO_EARN_S2_S1_S = new SystemMessageId(60);
		NOTHING_HAPPENED = new SystemMessageId(61);
		S1_SUCCESSFULLY_ENCHANTED = new SystemMessageId(62);
		S1_S2_SUCCESSFULLY_ENCHANTED = new SystemMessageId(63);
		ENCHANTMENT_FAILED_S1_EVAPORATED = new SystemMessageId(64);
		ENCHANTMENT_FAILED_S1_S2_EVAPORATED = new SystemMessageId(65);
		C1_INVITED_YOU_TO_PARTY = new SystemMessageId(66);
		S1_HAS_INVITED_YOU_TO_JOIN_THE_CLAN_S2 = new SystemMessageId(67);
		WOULD_YOU_LIKE_TO_WITHDRAW_FROM_THE_S1_CLAN = new SystemMessageId(68);
		WOULD_YOU_LIKE_TO_DISMISS_S1_FROM_THE_CLAN = new SystemMessageId(69);
		DO_YOU_WISH_TO_DISPERSE_THE_CLAN_S1 = new SystemMessageId(70);
		HOW_MANY_S1_DISCARD = new SystemMessageId(71);
		HOW_MANY_S1_MOVE = new SystemMessageId(72);
		HOW_MANY_S1_DESTROY = new SystemMessageId(73);
		WISH_DESTROY_S1 = new SystemMessageId(74);
		ID_NOT_EXIST = new SystemMessageId(75);
		INCORRECT_PASSWORD = new SystemMessageId(76);
		CANNOT_CREATE_CHARACTER = new SystemMessageId(77);
		WISH_DELETE_S1 = new SystemMessageId(78);
		NAMING_NAME_ALREADY_EXISTS = new SystemMessageId(79);
		NAMING_CHARNAME_UP_TO_16CHARS = new SystemMessageId(80);
		PLEASE_SELECT_RACE = new SystemMessageId(81);
		PLEASE_SELECT_OCCUPATION = new SystemMessageId(82);
		PLEASE_SELECT_GENDER = new SystemMessageId(83);
		CANT_ATK_PEACEZONE = new SystemMessageId(84);
		TARGET_IN_PEACEZONE = new SystemMessageId(85);
		PLEASE_ENTER_ID = new SystemMessageId(86);
		PLEASE_ENTER_PASSWORD = new SystemMessageId(87);
		WRONG_PROTOCOL_CHECK = new SystemMessageId(88);
		WRONG_PROTOCOL_CONTINUE = new SystemMessageId(89);
		UNABLE_TO_CONNECT = new SystemMessageId(90);
		PLEASE_SELECT_HAIRSTYLE = new SystemMessageId(91);
		S1_HAS_WORN_OFF = new SystemMessageId(92);
		NOT_ENOUGH_SP = new SystemMessageId(93);
		COPYRIGHT = new SystemMessageId(94);
		YOU_EARNED_S1_EXP_AND_S2_SP = new SystemMessageId(95);
		YOU_INCREASED_YOUR_LEVEL = new SystemMessageId(96);
		CANNOT_MOVE_THIS_ITEM = new SystemMessageId(97);
		CANNOT_DISCARD_THIS_ITEM = new SystemMessageId(98);
		CANNOT_TRADE_THIS_ITEM = new SystemMessageId(99);
		C1_REQUESTS_TRADE = new SystemMessageId(100);
		CANT_LOGOUT_WHILE_FIGHTING = new SystemMessageId(101);
		CANT_RESTART_WHILE_FIGHTING = new SystemMessageId(102);
		ID_LOGGED_IN = new SystemMessageId(103);
		CANNOT_USE_ITEM_WHILE_USING_MAGIC = new SystemMessageId(104);
		C1_INVITED_TO_PARTY = new SystemMessageId(105);
		YOU_JOINED_S1_PARTY = new SystemMessageId(106);
		C1_JOINED_PARTY = new SystemMessageId(107);
		C1_LEFT_PARTY = new SystemMessageId(108);
		INCORRECT_TARGET = new SystemMessageId(109);
		YOU_FEEL_S1_EFFECT = new SystemMessageId(110);
		SHIELD_DEFENCE_SUCCESSFULL = new SystemMessageId(111);
		NOT_ENOUGH_ARROWS = new SystemMessageId(112);
		S1_CANNOT_BE_USED = new SystemMessageId(113);
		ENTER_SHADOW_MOTHER_TREE = new SystemMessageId(114);
		EXIT_SHADOW_MOTHER_TREE = new SystemMessageId(115);
		ENTER_PEACEFUL_ZONE = new SystemMessageId(116);
		EXIT_PEACEFUL_ZONE = new SystemMessageId(117);
		REQUEST_C1_FOR_TRADE = new SystemMessageId(118);
		C1_DENIED_TRADE_REQUEST = new SystemMessageId(119);
		BEGIN_TRADE_WITH_C1 = new SystemMessageId(120);
		C1_CONFIRMED_TRADE = new SystemMessageId(121);
		CANNOT_ADJUST_ITEMS_AFTER_TRADE_CONFIRMED = new SystemMessageId(122);
		TRADE_SUCCESSFUL = new SystemMessageId(123);
		C1_CANCELED_TRADE = new SystemMessageId(124);
		WISH_EXIT_GAME = new SystemMessageId(125);
		WISH_RESTART_GAME = new SystemMessageId(126);
		DISCONNECTED_FROM_SERVER = new SystemMessageId(127);
		CHARACTER_CREATION_FAILED = new SystemMessageId(128);
		SLOTS_FULL = new SystemMessageId(129);
		WAREHOUSE_FULL = new SystemMessageId(130);
		S1_LOGGED_IN = new SystemMessageId(131);
		S1_ADDED_TO_FRIENDS = new SystemMessageId(132);
		S1_REMOVED_FROM_YOUR_FRIENDS_LIST = new SystemMessageId(133);
		PLEACE_CHECK_YOUR_FRIEND_LIST_AGAIN = new SystemMessageId(134);
		C1_DID_NOT_REPLY_TO_YOUR_INVITE = new SystemMessageId(135);
		YOU_DID_NOT_REPLY_TO_C1_INVITE = new SystemMessageId(136);
		NO_MORE_ITEMS_SHORTCUT = new SystemMessageId(137);
		DESIGNATE_SHORTCUT = new SystemMessageId(138);
		C1_RESISTED_YOUR_S2 = new SystemMessageId(139);
		SKILL_REMOVED_DUE_LACK_MP = new SystemMessageId(140);
		ONCE_THE_TRADE_IS_CONFIRMED_THE_ITEM_CANNOT_BE_MOVED_AGAIN = new SystemMessageId(141);
		ALREADY_TRADING = new SystemMessageId(142);
		C1_ALREADY_TRADING = new SystemMessageId(143);
		TARGET_IS_INCORRECT = new SystemMessageId(144);
		TARGET_IS_NOT_FOUND_IN_THE_GAME = new SystemMessageId(145);
		CHATTING_PERMITTED = new SystemMessageId(146);
		CHATTING_PROHIBITED = new SystemMessageId(147);
		CANNOT_USE_QUEST_ITEMS = new SystemMessageId(148);
		CANNOT_USE_ITEM_WHILE_TRADING = new SystemMessageId(149);
		CANNOT_DISCARD_OR_DESTROY_ITEM_WHILE_TRADING = new SystemMessageId(150);
		CANNOT_DISCARD_DISTANCE_TOO_FAR = new SystemMessageId(151);
		YOU_HAVE_INVITED_THE_WRONG_TARGET = new SystemMessageId(152);
		C1_IS_BUSY_TRY_LATER = new SystemMessageId(153);
		ONLY_LEADER_CAN_INVITE = new SystemMessageId(154);
		PARTY_FULL = new SystemMessageId(155);
		DRAIN_HALF_SUCCESFUL = new SystemMessageId(156);
		RESISTED_C1_DRAIN = new SystemMessageId(157);
		ATTACK_FAILED = new SystemMessageId(158);
		RESISTED_C1_MAGIC = new SystemMessageId(159);
		C1_IS_ALREADY_IN_PARTY = new SystemMessageId(160);
		INVITED_USER_NOT_ONLINE = new SystemMessageId(161);
		WAREHOUSE_TOO_FAR = new SystemMessageId(162);
		CANNOT_DESTROY_NUMBER_INCORRECT = new SystemMessageId(163);
		WAITING_FOR_ANOTHER_REPLY = new SystemMessageId(164);
		YOU_CANNOT_ADD_YOURSELF_TO_OWN_FRIEND_LIST = new SystemMessageId(165);
		FRIEND_LIST_NOT_READY_YET_REGISTER_LATER = new SystemMessageId(166);
		C1_ALREADY_ON_FRIEND_LIST = new SystemMessageId(167);
		C1_REQUESTED_TO_BECOME_FRIENDS = new SystemMessageId(168);
		ACCEPT_THE_FRIENDSHIP = new SystemMessageId(169);
		THE_USER_YOU_REQUESTED_IS_NOT_IN_GAME = new SystemMessageId(170);
		C1_NOT_ON_YOUR_FRIENDS_LIST = new SystemMessageId(171);
		LACK_FUNDS_FOR_TRANSACTION1 = new SystemMessageId(172);
		LACK_FUNDS_FOR_TRANSACTION2 = new SystemMessageId(173);
		OTHER_INVENTORY_FULL = new SystemMessageId(174);
		SKILL_DEACTIVATED_HP_FULL = new SystemMessageId(175);
		THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE = new SystemMessageId(176);
		MESSAGE_REFUSAL_MODE = new SystemMessageId(177);
		MESSAGE_ACCEPTANCE_MODE = new SystemMessageId(178);
		CANT_DISCARD_HERE = new SystemMessageId(179);
		S1_DAYS_LEFT_CANCEL_ACTION = new SystemMessageId(180);
		CANT_SEE_TARGET = new SystemMessageId(181);
		WANT_QUIT_CURRENT_QUEST = new SystemMessageId(182);
		TOO_MANY_USERS = new SystemMessageId(183);
		TRY_AGAIN_LATER = new SystemMessageId(184);
		FIRST_SELECT_USER_TO_INVITE_TO_PARTY = new SystemMessageId(185);
		FIRST_SELECT_USER_TO_INVITE_TO_CLAN = new SystemMessageId(186);
		SELECT_USER_TO_EXPEL = new SystemMessageId(187);
		PLEASE_CREATE_CLAN_NAME = new SystemMessageId(188);
		CLAN_CREATED = new SystemMessageId(189);
		FAILED_TO_CREATE_CLAN = new SystemMessageId(190);
		CLAN_MEMBER_S1_EXPELLED = new SystemMessageId(191);
		FAILED_EXPEL_S1 = new SystemMessageId(192);
		CLAN_HAS_DISPERSED = new SystemMessageId(193);
		FAILED_TO_DISPERSE_CLAN = new SystemMessageId(194);
		ENTERED_THE_CLAN = new SystemMessageId(195);
		S1_REFUSED_TO_JOIN_CLAN = new SystemMessageId(196);
		YOU_HAVE_WITHDRAWN_FROM_CLAN = new SystemMessageId(197);
		FAILED_TO_WITHDRAW_FROM_S1_CLAN = new SystemMessageId(198);
		CLAN_MEMBERSHIP_TERMINATED = new SystemMessageId(199);
		YOU_LEFT_PARTY = new SystemMessageId(200);
		C1_WAS_EXPELLED_FROM_PARTY = new SystemMessageId(201);
		HAVE_BEEN_EXPELLED_FROM_PARTY = new SystemMessageId(202);
		PARTY_DISPERSED = new SystemMessageId(203);
		INCORRECT_NAME_TRY_AGAIN = new SystemMessageId(204);
		INCORRECT_CHARACTER_NAME_TRY_AGAIN = new SystemMessageId(205);
		ENTER_CLAN_NAME_TO_DECLARE_WAR = new SystemMessageId(206);
		S2_OF_THE_CLAN_S1_REQUESTS_WAR = new SystemMessageId(207);
		YOU_ARE_NOT_A_CLAN_MEMBER = new SystemMessageId(212);
		NOT_WORKING_PLEASE_TRY_AGAIN_LATER = new SystemMessageId(213);
		TITLE_CHANGED = new SystemMessageId(214);
		WAR_WITH_THE_S1_CLAN_HAS_BEGUN = new SystemMessageId(215);
		WAR_WITH_THE_S1_CLAN_HAS_ENDED = new SystemMessageId(216);
		YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN = new SystemMessageId(217);
		YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN = new SystemMessageId(218);
		YOU_WERE_DEFEATED_BY_S1_CLAN = new SystemMessageId(219);
		S1_MINUTES_LEFT_UNTIL_CLAN_WAR_ENDS = new SystemMessageId(220);
		CLAN_WAR_WITH_S1_CLAN_HAS_ENDED = new SystemMessageId(221);
		S1_HAS_JOINED_CLAN = new SystemMessageId(222);
		S1_HAS_WITHDRAWN_FROM_THE_CLAN = new SystemMessageId(223);
		S1_DID_NOT_RESPOND_TO_CLAN_INVITATION = new SystemMessageId(224);
		YOU_DID_NOT_RESPOND_TO_S1_CLAN_INVITATION = new SystemMessageId(225);
		S1_CLAN_DID_NOT_RESPOND = new SystemMessageId(226);
		CLAN_WAR_REFUSED_YOU_DID_NOT_RESPOND_TO_S1 = new SystemMessageId(227);
		REQUEST_TO_END_WAR_HAS_BEEN_DENIED = new SystemMessageId(228);
		YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN = new SystemMessageId(229);
		YOU_MUST_WAIT_XX_DAYS_BEFORE_CREATING_A_NEW_CLAN = new SystemMessageId(230);
		YOU_MUST_WAIT_BEFORE_ACCEPTING_A_NEW_MEMBER = new SystemMessageId(231);
		YOU_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN = new SystemMessageId(232);
		SUBCLAN_IS_FULL = new SystemMessageId(233);
		TARGET_MUST_BE_IN_CLAN = new SystemMessageId(234);
		NOT_AUTHORIZED_TO_BESTOW_RIGHTS = new SystemMessageId(235);
		ONLY_THE_CLAN_LEADER_IS_ENABLED = new SystemMessageId(236);
		CLAN_LEADER_NOT_FOUND = new SystemMessageId(237);
		NOT_JOINED_IN_ANY_CLAN = new SystemMessageId(238);
		CLAN_LEADER_CANNOT_WITHDRAW = new SystemMessageId(239);
		CURRENTLY_INVOLVED_IN_CLAN_WAR = new SystemMessageId(240);
		LEADER_OF_S1_CLAN_NOT_FOUND = new SystemMessageId(241);
		SELECT_TARGET = new SystemMessageId(242);
		CANNOT_DECLARE_WAR_ON_ALLIED_CLAN = new SystemMessageId(243);
		NOT_ALLOWED_TO_CHALLENGE = new SystemMessageId(244);
		FIVE_DAYS_NOT_PASSED_SINCE_REFUSED_WAR = new SystemMessageId(245);
		CLAN_CURRENTLY_AT_WAR = new SystemMessageId(246);
		FIVE_DAYS_MUST_PASS_BEFORE_CHALLENGE_AGAIN = new SystemMessageId(247);
		S1_CLAN_NOT_ENOUGH_MEMBERS_FOR_WAR = new SystemMessageId(248);
		WISH_SURRENDER_TO_S1_CLAN = new SystemMessageId(249);
		YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN = new SystemMessageId(250);
		ALREADY_AT_WAR_WITH_ANOTHER_CLAN = new SystemMessageId(251);
		ENTER_CLAN_NAME_TO_SURRENDER_TO = new SystemMessageId(252);
		ENTER_CLAN_NAME_TO_END_WAR = new SystemMessageId(253);
		LEADER_CANT_PERSONALLY_SURRENDER = new SystemMessageId(254);
		S1_CLAN_REQUESTED_END_WAR = new SystemMessageId(255);
		ENTER_TITLE = new SystemMessageId(256);
		DO_YOU_OFFER_S1_CLAN_END_WAR = new SystemMessageId(257);
		NOT_INVOLVED_CLAN_WAR = new SystemMessageId(258);
		SELECT_MEMBERS_FROM_LIST = new SystemMessageId(259);
		FIVE_DAYS_NOT_PASSED_SINCE_YOU_WERE_REFUSED_WAR = new SystemMessageId(260);
		CLAN_NAME_INCORRECT = new SystemMessageId(261);
		CLAN_NAME_TOO_LONG = new SystemMessageId(262);
		DISSOLUTION_IN_PROGRESS = new SystemMessageId(263);
		CANNOT_DISSOLVE_WHILE_IN_WAR = new SystemMessageId(264);
		CANNOT_DISSOLVE_WHILE_IN_SIEGE = new SystemMessageId(265);
		CANNOT_DISSOLVE_WHILE_OWNING_CLAN_HALL_OR_CASTLE = new SystemMessageId(266);
		NO_REQUESTS_TO_DISPERSE = new SystemMessageId(267);
		PLAYER_ALREADY_ANOTHER_CLAN = new SystemMessageId(268);
		YOU_CANNOT_DISMISS_YOURSELF = new SystemMessageId(269);
		YOU_HAVE_ALREADY_SURRENDERED = new SystemMessageId(270);
		CLAN_LVL_3_NEEDED_TO_ENDOWE_TITLE = new SystemMessageId(271);
		CLAN_LVL_3_NEEDED_TO_SET_CREST = new SystemMessageId(272);
		CLAN_LVL_3_NEEDED_TO_DECLARE_WAR = new SystemMessageId(273);
		CLAN_LEVEL_INCREASED = new SystemMessageId(274);
		CLAN_LEVEL_INCREASE_FAILED = new SystemMessageId(275);
		ITEM_MISSING_TO_LEARN_SKILL = new SystemMessageId(276);
		LEARNED_SKILL_S1 = new SystemMessageId(277);
		NOT_ENOUGH_SP_TO_LEARN_SKILL = new SystemMessageId(278);
		YOU_NOT_ENOUGH_ADENA = new SystemMessageId(279);
		NO_ITEMS_TO_SELL = new SystemMessageId(280);
		YOU_NOT_ENOUGH_ADENA_PAY_FEE = new SystemMessageId(281);
		NO_ITEM_DEPOSITED_IN_WH = new SystemMessageId(282);
		ENTERED_COMBAT_ZONE = new SystemMessageId(283);
		LEFT_COMBAT_ZONE = new SystemMessageId(284);
		CLAN_S1_ENGRAVED_RULER = new SystemMessageId(285);
		BASE_UNDER_ATTACK = new SystemMessageId(286);
		OPPONENT_STARTED_ENGRAVING = new SystemMessageId(287);
		CASTLE_GATE_BROKEN_DOWN = new SystemMessageId(288);
		NOT_ANOTHER_HEADQUARTERS = new SystemMessageId(289);
		NOT_SET_UP_BASE_HERE = new SystemMessageId(290);
		CLAN_S1_VICTORIOUS_OVER_S2_S_SIEGE = new SystemMessageId(291);
		S1_ANNOUNCED_SIEGE_TIME = new SystemMessageId(292);
		REGISTRATION_TERM_FOR_S1_ENDED = new SystemMessageId(293);
		BECAUSE_YOUR_CLAN_IS_NOT_CURRENTLY_ON_THE_OFFENSIVE_IN_A_CLAN_HALL_SIEGE_WAR_IT_CANNOT_SUMMON_ITS_BASE_CAMP = new SystemMessageId(294);
		S1_SIEGE_WAS_CANCELED_BECAUSE_NO_CLANS_PARTICIPATED = new SystemMessageId(295);
		FALL_DAMAGE_S1 = new SystemMessageId(296);
		DROWN_DAMAGE_S1 = new SystemMessageId(297);
		YOU_DROPPED_S1 = new SystemMessageId(298);
		C1_OBTAINED_S3_S2 = new SystemMessageId(299);
		C1_OBTAINED_S2 = new SystemMessageId(300);
		S2_S1_DISAPPEARED = new SystemMessageId(301);
		S1_DISAPPEARED = new SystemMessageId(302);
		SELECT_ITEM_TO_ENCHANT = new SystemMessageId(303);
		CLAN_MEMBER_S1_LOGGED_IN = new SystemMessageId(304);
		PLAYER_DECLINED = new SystemMessageId(305);
		YOU_HAVE_SUCCEEDED_IN_EXPELLING_CLAN_MEMBER = new SystemMessageId(309);
		CLAN_WAR_DECLARATION_ACCEPTED = new SystemMessageId(311);
		CLAN_WAR_DECLARATION_REFUSED = new SystemMessageId(312);
		CEASE_WAR_REQUEST_ACCEPTED = new SystemMessageId(313);
		UNABLE_TO_UNLOCK_DOOR = new SystemMessageId(319);
		FAILED_TO_UNLOCK_DOOR = new SystemMessageId(320);
		ITS_NOT_LOCKED = new SystemMessageId(321);
		DECIDE_SALES_PRICE = new SystemMessageId(322);
		FORCE_INCREASED_TO_S1 = new SystemMessageId(323);
		FORCE_MAXLEVEL_REACHED = new SystemMessageId(324);
		CORPSE_ALREADY_DISAPPEARED = new SystemMessageId(325);
		SELECT_TARGET_FROM_LIST = new SystemMessageId(326);
		CANNOT_EXCEED_80_CHARACTERS = new SystemMessageId(327);
		PLEASE_INPUT_TITLE_LESS_128_CHARACTERS = new SystemMessageId(328);
		PLEASE_INPUT_CONTENT_LESS_3000_CHARACTERS = new SystemMessageId(329);
		ONE_LINE_RESPONSE_NOT_EXCEED_128_CHARACTERS = new SystemMessageId(330);
		ACQUIRED_S1_SP = new SystemMessageId(331);
		DO_YOU_WANT_TO_BE_RESTORED = new SystemMessageId(332);
		S1_DAMAGE_BY_CORE_BARRIER = new SystemMessageId(333);
		ENTER_PRIVATE_STORE_MESSAGE = new SystemMessageId(334);
		S1_HAS_BEEN_ABORTED = new SystemMessageId(335);
		WISH_TO_CRYSTALLIZE_S1 = new SystemMessageId(336);
		SOULSHOTS_GRADE_MISMATCH = new SystemMessageId(337);
		NOT_ENOUGH_SOULSHOTS = new SystemMessageId(338);
		CANNOT_USE_SOULSHOTS = new SystemMessageId(339);
		PRIVATE_STORE_UNDER_WAY = new SystemMessageId(340);
		NOT_ENOUGH_MATERIALS = new SystemMessageId(341);
		ENABLED_SOULSHOT = new SystemMessageId(342);
		SWEEPER_FAILED_TARGET_NOT_SPOILED = new SystemMessageId(343);
		SOULSHOTS_DISABLED = new SystemMessageId(344);
		CHAT_ENABLED = new SystemMessageId(345);
		CHAT_DISABLED = new SystemMessageId(346);
		INCORRECT_ITEM_COUNT = new SystemMessageId(347);
		INCORRECT_ITEM_PRICE = new SystemMessageId(348);
		PRIVATE_STORE_ALREADY_CLOSED = new SystemMessageId(349);
		ITEM_OUT_OF_STOCK = new SystemMessageId(350);
		NOT_ENOUGH_ITEMS = new SystemMessageId(351);
		CANCEL_ENCHANT = new SystemMessageId(354);
		INAPPROPRIATE_ENCHANT_CONDITION = new SystemMessageId(355);
		REJECT_RESURRECTION = new SystemMessageId(356);
		ALREADY_SPOILED = new SystemMessageId(357);
		S1_HOURS_UNTIL_SIEGE_CONCLUSION = new SystemMessageId(358);
		S1_MINUTES_UNTIL_SIEGE_CONCLUSION = new SystemMessageId(359);
		CASTLE_SIEGE_S1_SECONDS_LEFT = new SystemMessageId(360);
		OVER_HIT = new SystemMessageId(361);
		ACQUIRED_BONUS_EXPERIENCE_THROUGH_OVER_HIT = new SystemMessageId(362);
		CHAT_AVAILABLE_S1_MINUTE = new SystemMessageId(363);
		ENTER_USER_NAME_TO_SEARCH = new SystemMessageId(364);
		ARE_YOU_SURE = new SystemMessageId(365);
		PLEASE_SELECT_HAIR_COLOR = new SystemMessageId(366);
		CANNOT_REMOVE_CLAN_CHARACTER = new SystemMessageId(367);
		S1_S2_EQUIPPED = new SystemMessageId(368);
		YOU_PICKED_UP_A_S1_S2 = new SystemMessageId(369);
		FAILED_PICKUP_S1 = new SystemMessageId(370);
		ACQUIRED_S1_S2 = new SystemMessageId(371);
		FAILED_EARN_S1 = new SystemMessageId(372);
		WISH_DESTROY_S1_S2 = new SystemMessageId(373);
		WISH_CRYSTALLIZE_S1_S2 = new SystemMessageId(374);
		DROPPED_S1_S2 = new SystemMessageId(375);
		C1_OBTAINED_S2_S3 = new SystemMessageId(376);
		S1_S2_DISAPPEARED = new SystemMessageId(377);
		C1_PURCHASED_S2 = new SystemMessageId(378);
		C1_PURCHASED_S2_S3 = new SystemMessageId(379);
		C1_PURCHASED_S3_S2_S = new SystemMessageId(380);
		GAME_CLIENT_UNABLE_TO_CONNECT_TO_PETITION_SERVER = new SystemMessageId(381);
		NO_USERS_CHECKED_OUT_GM_ID = new SystemMessageId(382);
		REQUEST_CONFIRMED_TO_END_CONSULTATION = new SystemMessageId(383);
		CLIENT_NOT_LOGGED_ONTO_GAME_SERVER = new SystemMessageId(384);
		REQUEST_CONFIRMED_TO_BEGIN_CONSULTATION = new SystemMessageId(385);
		PETITION_MORE_THAN_FIVE_CHARACTERS = new SystemMessageId(386);
		THIS_END_THE_PETITION_PLEASE_PROVIDE_FEEDBACK = new SystemMessageId(387);
		NOT_UNDER_PETITION_CONSULTATION = new SystemMessageId(388);
		PETITION_ACCEPTED_RECENT_NO_S1 = new SystemMessageId(389);
		ONLY_ONE_ACTIVE_PETITION_AT_TIME = new SystemMessageId(390);
		RECENT_NO_S1_CANCELED = new SystemMessageId(391);
		UNDER_PETITION_ADVICE = new SystemMessageId(392);
		FAILED_CANCEL_PETITION_TRY_LATER = new SystemMessageId(393);
		STARTING_PETITION_WITH_C1 = new SystemMessageId(394);
		PETITION_ENDED_WITH_C1 = new SystemMessageId(395);
		TRY_AGAIN_AFTER_CHANGING_PASSWORD = new SystemMessageId(396);
		NO_PAID_ACCOUNT = new SystemMessageId(397);
		NO_TIME_LEFT_ON_ACCOUNT = new SystemMessageId(398);
		WISH_TO_DROP_S1 = new SystemMessageId(400);
		TOO_MANY_QUESTS = new SystemMessageId(401);
		NOT_CORRECT_BOAT_TICKET = new SystemMessageId(402);
		EXCEECED_POCKET_ADENA_LIMIT = new SystemMessageId(403);
		CREATE_LVL_TOO_LOW_TO_REGISTER = new SystemMessageId(404);
		TOTAL_PRICE_TOO_HIGH = new SystemMessageId(405);
		PETITION_APP_ACCEPTED = new SystemMessageId(406);
		PETITION_UNDER_PROCESS = new SystemMessageId(407);
		SET_PERIOD = new SystemMessageId(408);
		SET_TIME_S1_S2_S3 = new SystemMessageId(409);
		REGISTRATION_PERIOD = new SystemMessageId(410);
		REGISTRATION_TIME_S1_S2_S3 = new SystemMessageId(411);
		BATTLE_BEGINS_S1_S2_S3 = new SystemMessageId(412);
		BATTLE_ENDS_S1_S2_S3 = new SystemMessageId(413);
		STANDBY = new SystemMessageId(414);
		UNDER_SIEGE = new SystemMessageId(415);
		ITEM_CANNOT_EXCHANGE = new SystemMessageId(416);
		S1_DISARMED = new SystemMessageId(417);
		S1_MINUTES_USAGE_LEFT = new SystemMessageId(419);
		TIME_EXPIRED = new SystemMessageId(420);
		ANOTHER_LOGIN_WITH_ACCOUNT = new SystemMessageId(421);
		WEIGHT_LIMIT_EXCEEDED = new SystemMessageId(422);
		ENCHANT_SCROLL_CANCELLED = new SystemMessageId(423);
		DOES_NOT_FIT_SCROLL_CONDITIONS = new SystemMessageId(424);
		CREATE_LVL_TOO_LOW_TO_REGISTER2 = new SystemMessageId(425);
		REFERENCE_MEMBERSHIP_WITHDRAWAL_S1 = new SystemMessageId(445);
		DOT = new SystemMessageId(447);
		SYSTEM_ERROR_LOGIN_LATER = new SystemMessageId(448);
		PASSWORD_ENTERED_INCORRECT1 = new SystemMessageId(449);
		CONFIRM_ACCOUNT_LOGIN_LATER = new SystemMessageId(450);
		PASSWORD_ENTERED_INCORRECT2 = new SystemMessageId(451);
		PLEASE_CONFIRM_ACCOUNT_LOGIN_LATER = new SystemMessageId(452);
		ACCOUNT_INFORMATION_INCORRECT = new SystemMessageId(453);
		ACCOUNT_IN_USE = new SystemMessageId(455);
		LINAGE_MINIMUM_AGE = new SystemMessageId(456);
		SERVER_MAINTENANCE = new SystemMessageId(457);
		USAGE_TERM_EXPIRED = new SystemMessageId(458);
		TO_REACTIVATE_YOUR_ACCOUNT = new SystemMessageId(460);
		ACCESS_FAILED = new SystemMessageId(461);
		PLEASE_TRY_AGAIN_LATER = new SystemMessageId(462);
		FEATURE_ONLY_FOR_ALLIANCE_LEADER = new SystemMessageId(464);
		NO_CURRENT_ALLIANCES = new SystemMessageId(465);
		YOU_HAVE_EXCEEDED_THE_LIMIT = new SystemMessageId(466);
		CANT_INVITE_CLAN_WITHIN_1_DAY = new SystemMessageId(467);
		CANT_ENTER_ALLIANCE_WITHIN_1_DAY = new SystemMessageId(468);
		MAY_NOT_ALLY_CLAN_BATTLE = new SystemMessageId(469);
		ONLY_CLAN_LEADER_WITHDRAW_ALLY = new SystemMessageId(470);
		ALLIANCE_LEADER_CANT_WITHDRAW = new SystemMessageId(471);
		CANNOT_EXPEL_YOURSELF = new SystemMessageId(472);
		DIFFERENT_ALLIANCE = new SystemMessageId(473);
		CLAN_DOESNT_EXISTS = new SystemMessageId(474);
		DIFFERENT_ALLIANCE2 = new SystemMessageId(475);
		ADJUST_IMAGE_8_12 = new SystemMessageId(476);
		NO_RESPONSE_TO_ALLY_INVITATION = new SystemMessageId(477);
		YOU_DID_NOT_RESPOND_TO_ALLY_INVITATION = new SystemMessageId(478);
		S1_JOINED_AS_FRIEND = new SystemMessageId(479);
		PLEASE_CHECK_YOUR_FRIENDS_LIST = new SystemMessageId(480);
		S1_HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST = new SystemMessageId(481);
		YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIENDS_LIST = new SystemMessageId(482);
		FUNCTION_INACCESSIBLE_NOW = new SystemMessageId(483);
		S1_ALREADY_IN_FRIENDS_LIST = new SystemMessageId(484);
		NO_NEW_INVITATIONS_ACCEPTED = new SystemMessageId(485);
		THE_USER_NOT_IN_FRIENDS_LIST = new SystemMessageId(486);
		FRIEND_LIST_HEADER = new SystemMessageId(487);
		S1_ONLINE = new SystemMessageId(488);
		S1_OFFLINE = new SystemMessageId(489);
		FRIEND_LIST_FOOTER = new SystemMessageId(490);
		ALLIANCE_INFO_HEAD = new SystemMessageId(491);
		ALLIANCE_NAME_S1 = new SystemMessageId(492);
		CONNECTION_S1_TOTAL_S2 = new SystemMessageId(493);
		ALLIANCE_LEADER_S2_OF_S1 = new SystemMessageId(494);
		ALLIANCE_CLAN_TOTAL_S1 = new SystemMessageId(495);
		CLAN_INFO_HEAD = new SystemMessageId(496);
		CLAN_INFO_NAME_S1 = new SystemMessageId(497);
		CLAN_INFO_LEADER_S1 = new SystemMessageId(498);
		CLAN_INFO_LEVEL_S1 = new SystemMessageId(499);
		CLAN_INFO_SEPARATOR = new SystemMessageId(500);
		CLAN_INFO_FOOT = new SystemMessageId(501);
		ALREADY_JOINED_ALLIANCE = new SystemMessageId(502);
		FRIEND_S1_HAS_LOGGED_IN = new SystemMessageId(503);
		ONLY_CLAN_LEADER_CREATE_ALLIANCE = new SystemMessageId(504);
		CANT_CREATE_ALLIANCE_10_DAYS_DISOLUTION = new SystemMessageId(505);
		INCORRECT_ALLIANCE_NAME = new SystemMessageId(506);
		INCORRECT_ALLIANCE_NAME_LENGTH = new SystemMessageId(507);
		ALLIANCE_ALREADY_EXISTS = new SystemMessageId(508);
		CANT_ACCEPT_ALLY_ENEMY_FOR_SIEGE = new SystemMessageId(509);
		YOU_INVITED_FOR_ALLIANCE = new SystemMessageId(510);
		SELECT_USER_TO_INVITE = new SystemMessageId(511);
		DO_YOU_WISH_TO_WITHDRW = new SystemMessageId(512);
		ENTER_NAME_CLAN_TO_EXPEL = new SystemMessageId(513);
		DO_YOU_WISH_TO_DISOLVE = new SystemMessageId(514);
		SI_INVITED_YOU_AS_FRIEND = new SystemMessageId(516);
		YOU_ACCEPTED_ALLIANCE = new SystemMessageId(517);
		FAILED_TO_INVITE_CLAN_IN_ALLIANCE = new SystemMessageId(518);
		YOU_HAVE_WITHDRAWN_FROM_ALLIANCE = new SystemMessageId(519);
		YOU_HAVE_FAILED_TO_WITHDRAWN_FROM_ALLIANCE = new SystemMessageId(520);
		YOU_HAVE_EXPELED_A_CLAN = new SystemMessageId(521);
		FAILED_TO_EXPELED_A_CLAN = new SystemMessageId(522);
		ALLIANCE_DISOLVED = new SystemMessageId(523);
		FAILED_TO_DISOLVE_ALLIANCE = new SystemMessageId(524);
		YOU_HAVE_SUCCEEDED_INVITING_FRIEND = new SystemMessageId(525);
		FAILED_TO_INVITE_A_FRIEND = new SystemMessageId(526);
		S2_ALLIANCE_LEADER_OF_S1_REQUESTED_ALLIANCE = new SystemMessageId(527);
		SPIRITSHOTS_GRADE_MISMATCH = new SystemMessageId(530);
		NOT_ENOUGH_SPIRITSHOTS = new SystemMessageId(531);
		CANNOT_USE_SPIRITSHOTS = new SystemMessageId(532);
		ENABLED_SPIRITSHOT = new SystemMessageId(533);
		DISABLED_SPIRITSHOT = new SystemMessageId(534);
		HOW_MUCH_ADENA_TRANSFER = new SystemMessageId(536);
		HOW_MUCH_TRANSFER = new SystemMessageId(537);
		SP_DECREASED_S1 = new SystemMessageId(538);
		EXP_DECREASED_BY_S1 = new SystemMessageId(539);
		CLAN_LEADERS_MAY_NOT_BE_DELETED = new SystemMessageId(540);
		CLAN_MEMBER_MAY_NOT_BE_DELETED = new SystemMessageId(541);
		THE_NPC_SERVER_IS_CURRENTLY_DOWN = new SystemMessageId(542);
		YOU_ALREADY_HAVE_A_PET = new SystemMessageId(543);
		ITEM_NOT_FOR_PETS = new SystemMessageId(544);
		YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS = new SystemMessageId(545);
		UNABLE_TO_PLACE_ITEM_YOUR_PET_IS_TOO_ENCUMBERED = new SystemMessageId(546);
		SUMMON_A_PET = new SystemMessageId(547);
		NAMING_PETNAME_UP_TO_8CHARS = new SystemMessageId(548);
		TO_CREATE_AN_ALLY_YOU_CLAN_MUST_BE_LEVEL_5_OR_HIGHER = new SystemMessageId(549);
		YOU_MAY_NOT_CREATE_ALLY_WHILE_DISSOLVING = new SystemMessageId(550);
		CANNOT_RISE_LEVEL_WHILE_DISSOLUTION_IN_PROGRESS = new SystemMessageId(551);
		CANNOT_SET_CREST_WHILE_DISSOLUTION_IN_PROGRESS = new SystemMessageId(552);
		OPPOSING_CLAN_APPLIED_DISPERSION = new SystemMessageId(553);
		CANNOT_DISPERSE_THE_CLANS_IN_ALLY = new SystemMessageId(554);
		CANT_MOVE_TOO_ENCUMBERED = new SystemMessageId(555);
		CANT_MOVE_IN_THIS_STATE = new SystemMessageId(556);
		PET_SUMMONED_MAY_NOT_DESTROYED = new SystemMessageId(557);
		PET_SUMMONED_MAY_NOT_LET_GO = new SystemMessageId(558);
		PURCHASED_S2_FROM_C1 = new SystemMessageId(559);
		PURCHASED_S2_S3_FROM_C1 = new SystemMessageId(560);
		PURCHASED_S3_S2_S_FROM_C1 = new SystemMessageId(561);
		CRYSTALLIZE_LEVEL_TOO_LOW = new SystemMessageId(562);
		FAILED_DISABLE_TARGET = new SystemMessageId(563);
		FAILED_CHANGE_TARGET = new SystemMessageId(564);
		NOT_ENOUGH_LUCK = new SystemMessageId(565);
		CONFUSION_FAILED = new SystemMessageId(566);
		FEAR_FAILED = new SystemMessageId(567);
		CUBIC_SUMMONING_FAILED = new SystemMessageId(568);
		C1_INVITED_YOU_TO_PARTY_FINDERS_KEEPERS = new SystemMessageId(572);
		C1_INVITED_YOU_TO_PARTY_RANDOM = new SystemMessageId(573);
		PETS_ARE_NOT_AVAILABLE_AT_THIS_TIME = new SystemMessageId(574);
		HOW_MUCH_ADENA_TRANSFER_TO_PET = new SystemMessageId(575);
		HOW_MUCH_TRANSFER2 = new SystemMessageId(576);
		CANNOT_SUMMON_DURING_TRADE_SHOP = new SystemMessageId(577);
		YOU_CANNOT_SUMMON_IN_COMBAT = new SystemMessageId(578);
		PET_CANNOT_SENT_BACK_DURING_BATTLE = new SystemMessageId(579);
		SUMMON_ONLY_ONE = new SystemMessageId(580);
		NAMING_THERE_IS_A_SPACE = new SystemMessageId(581);
		NAMING_INAPPROPRIATE_CHARACTER_NAME = new SystemMessageId(582);
		NAMING_INCLUDES_FORBIDDEN_WORDS = new SystemMessageId(583);
		NAMING_ALREADY_IN_USE_BY_ANOTHER_PET = new SystemMessageId(584);
		DECIDE_ON_PRICE = new SystemMessageId(585);
		PET_NO_SHORTCUT = new SystemMessageId(586);
		PET_INVENTORY_FULL = new SystemMessageId(588);
		DEAD_PET_CANNOT_BE_RETURNED = new SystemMessageId(589);
		CANNOT_GIVE_ITEMS_TO_DEAD_PET = new SystemMessageId(590);
		NAMING_PETNAME_CONTAINS_INVALID_CHARS = new SystemMessageId(591);
		WISH_TO_DISMISS_PET = new SystemMessageId(592);
		STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT = new SystemMessageId(593);
		YOU_CANNOT_RESTORE_HUNGRY_PETS = new SystemMessageId(594);
		YOUR_PET_IS_VERY_HUNGRY = new SystemMessageId(595);
		YOUR_PET_ATE_A_LITTLE_BUT_IS_STILL_HUNGRY = new SystemMessageId(596);
		YOUR_PET_IS_VERY_HUNGRY_PLEASE_BE_CAREFULL = new SystemMessageId(597);
		NOT_CHAT_WHILE_INVISIBLE = new SystemMessageId(598);
		GM_NOTICE_CHAT_DISABLED = new SystemMessageId(599);
		CANNOT_EQUIP_PET_ITEM = new SystemMessageId(600);
		S1_PETITION_ON_WAITING_LIST = new SystemMessageId(601);
		PETITION_SYSTEM_CURRENT_UNAVAILABLE = new SystemMessageId(602);
		CANNOT_DISCARD_EXCHANGE_ITEM = new SystemMessageId(603);
		NOT_CALL_PET_FROM_THIS_LOCATION = new SystemMessageId(604);
		MAY_REGISTER_UP_TO_64_PEOPLE = new SystemMessageId(605);
		OTHER_PERSON_ALREADY_64_PEOPLE = new SystemMessageId(606);
		DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1 = new SystemMessageId(607);
		C1_SWEEPED_UP_S3_S2 = new SystemMessageId(608);
		C1_SWEEPED_UP_S2 = new SystemMessageId(609);
		SKILL_REMOVED_DUE_LACK_HP = new SystemMessageId(610);
		CONFUSING_SUCCEEDED = new SystemMessageId(611);
		SPOIL_SUCCESS = new SystemMessageId(612);
		BLOCK_LIST_HEADER = new SystemMessageId(613);
		C1_D_C2 = new SystemMessageId(614);
		FAILED_TO_REGISTER_TO_IGNORE_LIST = new SystemMessageId(615);
		FAILED_TO_DELETE_CHARACTER = new SystemMessageId(616);
		S1_WAS_ADDED_TO_YOUR_IGNORE_LIST = new SystemMessageId(617);
		S1_WAS_REMOVED_FROM_YOUR_IGNORE_LIST = new SystemMessageId(618);
		S1_HAS_ADDED_YOU_TO_IGNORE_LIST = new SystemMessageId(619);
		S1_HAS_ADDED_YOU_TO_IGNORE_LIST2 = new SystemMessageId(620);
		CONNECTION_RESTRICTED_IP = new SystemMessageId(621);
		NO_WAR_DURING_ALLY_BATTLE = new SystemMessageId(622);
		OPPONENT_TOO_MUCH_ALLY_BATTLES1 = new SystemMessageId(623);
		S1_LEADER_NOT_CONNECTED = new SystemMessageId(624);
		ALLY_BATTLE_TRUCE_DENIED = new SystemMessageId(625);
		WAR_PROCLAMATION_HAS_BEEN_REFUSED = new SystemMessageId(626);
		YOU_REFUSED_CLAN_WAR_PROCLAMATION = new SystemMessageId(627);
		ALREADY_AT_WAR_WITH_S1_WAIT_5_DAYS = new SystemMessageId(628);
		OPPONENT_TOO_MUCH_ALLY_BATTLES2 = new SystemMessageId(629);
		WAR_WITH_CLAN_BEGUN = new SystemMessageId(630);
		WAR_WITH_CLAN_ENDED = new SystemMessageId(631);
		WON_WAR_OVER_CLAN = new SystemMessageId(632);
		SURRENDERED_TO_CLAN = new SystemMessageId(633);
		DEFEATED_BY_CLAN = new SystemMessageId(634);
		TIME_UP_WAR_OVER = new SystemMessageId(635);
		NOT_INVOLVED_IN_WAR = new SystemMessageId(636);
		ALLY_REGISTERED_SELF_TO_OPPONENT = new SystemMessageId(637);
		ALREADY_REQUESTED_SIEGE_BATTLE = new SystemMessageId(638);
		APPLICATION_DENIED_BECAUSE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_SIEGE_BATTLE = new SystemMessageId(639);
		ALREADY_ATTACKER_NOT_CANCEL = new SystemMessageId(642);
		ALREADY_DEFENDER_NOT_CANCEL = new SystemMessageId(643);
		NOT_REGISTERED_FOR_SIEGE = new SystemMessageId(644);
		ONLY_CLAN_LEVEL_5_ABOVE_MAY_SIEGE = new SystemMessageId(645);
		ATTACKER_SIDE_FULL = new SystemMessageId(648);
		DEFENDER_SIDE_FULL = new SystemMessageId(649);
		YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION = new SystemMessageId(650);
		PLACE_CURRENT_LOCATION_DIRECTION = new SystemMessageId(651);
		TARGET_OF_SUMMON_WRONG = new SystemMessageId(652);
		YOU_DO_NOT_HAVE_AUTHORITY_TO_POSITION_MERCENARIES = new SystemMessageId(653);
		YOU_DO_NOT_HAVE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING = new SystemMessageId(654);
		MERCENARIES_CANNOT_BE_POSITIONED_HERE = new SystemMessageId(655);
		THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE = new SystemMessageId(656);
		POSITIONING_CANNOT_BE_DONE_BECAUSE_DISTANCE_BETWEEN_MERCENARIES_TOO_SHORT = new SystemMessageId(657);
		THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_CANNOT_CANCEL_POSITIONING = new SystemMessageId(658);
		NOT_SIEGE_REGISTRATION_TIME1 = new SystemMessageId(659);
		NOT_SIEGE_REGISTRATION_TIME2 = new SystemMessageId(660);
		SPOIL_CANNOT_USE = new SystemMessageId(661);
		THE_PLAYER_IS_REJECTING_FRIEND_INVITATIONS = new SystemMessageId(662);
		CHOOSE_PERSON_TO_RECEIVE = new SystemMessageId(664);
		APPLYING_ALLIANCE_WAR = new SystemMessageId(665);
		REQUEST_FOR_CEASEFIRE = new SystemMessageId(666);
		REGISTERING_ON_ATTACKING_SIDE = new SystemMessageId(667);
		REGISTERING_ON_DEFENDING_SIDE = new SystemMessageId(668);
		CANCELING_REGISTRATION = new SystemMessageId(669);
		REFUSING_REGISTRATION = new SystemMessageId(670);
		AGREEING_REGISTRATION = new SystemMessageId(671);
		S1_DISAPPEARED_ADENA = new SystemMessageId(672);
		AUCTION_ONLY_CLAN_LEVEL_2_HIGHER = new SystemMessageId(673);
		NOT_SEVEN_DAYS_SINCE_CANCELING_AUCTION = new SystemMessageId(674);
		NO_CLAN_HALLS_UP_FOR_AUCTION = new SystemMessageId(675);
		ALREADY_SUBMITTED_BID = new SystemMessageId(676);
		BID_PRICE_MUST_BE_HIGHER = new SystemMessageId(677);
		SUBMITTED_A_BID = new SystemMessageId(678);
		CANCELED_BID = new SystemMessageId(679);
		CANNOT_PARTICIPATE_IN_AN_AUCTION = new SystemMessageId(680);
		SWEEP_NOT_ALLOWED = new SystemMessageId(683);
		CANNOT_POSITION_MERCS_DURING_SIEGE = new SystemMessageId(684);
		CANNOT_DECLARE_WAR_ON_ALLY = new SystemMessageId(685);
		S1_DAMAGE_FROM_FIRE_MAGIC = new SystemMessageId(686);
		CANNOT_MOVE_FROZEN = new SystemMessageId(687);
		CLAN_THAT_OWNS_CASTLE_IS_AUTOMATICALLY_REGISTERED_DEFENDING = new SystemMessageId(688);
		CLAN_THAT_OWNS_CASTLE_CANNOT_PARTICIPATE_OTHER_SIEGE = new SystemMessageId(689);
		CANNOT_ATTACK_ALLIANCE_CASTLE = new SystemMessageId(690);
		S1_CLAN_ALREADY_MEMBER_OF_S2_ALLIANCE = new SystemMessageId(691);
		OTHER_PARTY_IS_FROZEN = new SystemMessageId(692);
		PACKAGE_IN_ANOTHER_WAREHOUSE = new SystemMessageId(693);
		NO_PACKAGES_ARRIVED = new SystemMessageId(694);
		NAMING_YOU_CANNOT_SET_NAME_OF_THE_PET = new SystemMessageId(695);
		ITEM_ENCHANT_VALUE_STRANGE = new SystemMessageId(697);
		PRICE_DIFFERENT_FROM_SALES_LIST = new SystemMessageId(698);
		CURRENTLY_NOT_PURCHASING = new SystemMessageId(699);
		THE_PURCHASE_IS_COMPLETE = new SystemMessageId(700);
		NOT_ENOUGH_REQUIRED_ITEMS = new SystemMessageId(701);
		NO_GM_PROVIDING_SERVICE_NOW = new SystemMessageId(702);
		GM_LIST = new SystemMessageId(703);
		GM_C1 = new SystemMessageId(704);
		CANNOT_EXCLUDE_SELF = new SystemMessageId(705);
		ONLY_64_NAMES_ON_EXCLUDE_LIST = new SystemMessageId(706);
		NO_PORT_THAT_IS_IN_SIGE = new SystemMessageId(707);
		YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CASTLE_WAREHOUSE = new SystemMessageId(708);
		YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CLAN_WAREHOUSE = new SystemMessageId(709);
		ONLY_LEVEL_1_CLAN_OR_HIGHER_CAN_USE_WAREHOUSE = new SystemMessageId(710);
		SIEGE_OF_S1_HAS_STARTED = new SystemMessageId(711);
		SIEGE_OF_S1_HAS_ENDED = new SystemMessageId(712);
		S1_S2_S3_D = new SystemMessageId(713);
		A_TRAP_DEVICE_HAS_BEEN_TRIPPED = new SystemMessageId(714);
		A_TRAP_DEVICE_HAS_BEEN_STOPPED = new SystemMessageId(715);
		NO_RESURRECTION_WITHOUT_BASE_CAMP = new SystemMessageId(716);
		TOWER_DESTROYED_NO_RESURRECTION = new SystemMessageId(717);
		GATES_NOT_OPENED_CLOSED_DURING_SIEGE = new SystemMessageId(718);
		ITEM_MIXING_FAILED = new SystemMessageId(719);
		THE_PURCHASE_PRICE_IS_HIGHER_THAN_MONEY = new SystemMessageId(720);
		NO_ALLY_CREATION_WHILE_SIEGE = new SystemMessageId(721);
		CANNOT_DISSOLVE_ALLY_WHILE_IN_SIEGE = new SystemMessageId(722);
		OPPOSING_CLAN_IS_PARTICIPATING_IN_SIEGE = new SystemMessageId(723);
		CANNOT_LEAVE_WHILE_SIEGE = new SystemMessageId(724);
		CANNOT_DISMISS_WHILE_SIEGE = new SystemMessageId(725);
		FROZEN_CONDITION_STARTED = new SystemMessageId(726);
		FROZEN_CONDITION_REMOVED = new SystemMessageId(727);
		CANNOT_APPLY_DISSOLUTION_AGAIN = new SystemMessageId(728);
		ITEM_NOT_DISCARDED = new SystemMessageId(729);
		SUBMITTED_YOU_S1_TH_PETITION_S2_LEFT = new SystemMessageId(730);
		PETITION_S1_RECEIVED_CODE_IS_S2 = new SystemMessageId(731);
		C1_RECEIVED_CONSULTATION_REQUEST = new SystemMessageId(732);
		WE_HAVE_RECEIVED_S1_PETITIONS_TODAY = new SystemMessageId(733);
		PETITION_FAILED_C1_ALREADY_SUBMITTED = new SystemMessageId(734);
		PETITION_FAILED_FOR_C1_ERROR_NUMBER_S2 = new SystemMessageId(735);
		PETITION_CANCELED_SUBMIT_S1_MORE_TODAY = new SystemMessageId(736);
		CANCELED_PETITION_ON_S1 = new SystemMessageId(737);
		PETITION_NOT_SUBMITTED = new SystemMessageId(738);
		PETITION_CANCEL_FAILED_FOR_C1_ERROR_NUMBER_S2 = new SystemMessageId(739);
		C1_PARTICIPATE_PETITION = new SystemMessageId(740);
		FAILED_ADDING_C1_TO_PETITION = new SystemMessageId(741);
		PETITION_ADDING_C1_FAILED_ERROR_NUMBER_S2 = new SystemMessageId(742);
		C1_LEFT_PETITION_CHAT = new SystemMessageId(743);
		PETITION_REMOVING_S1_FAILED_ERROR_NUMBER_S2 = new SystemMessageId(744);
		YOU_ARE_NOT_IN_PETITION_CHAT = new SystemMessageId(745);
		CURRENTLY_NO_PETITION = new SystemMessageId(746);
		DIST_TOO_FAR_CASTING_STOPPED = new SystemMessageId(748);
		EFFECT_S1_DISAPPEARED = new SystemMessageId(749);
		NO_MORE_SKILLS_TO_LEARN = new SystemMessageId(750);
		CANNOT_INVITE_CONFLICT_CLAN = new SystemMessageId(751);
		CANNOT_USE_NAME = new SystemMessageId(752);
		NO_MERCS_HERE = new SystemMessageId(753);
		S1_HOURS_S2_MINUTES_LEFT_THIS_WEEK = new SystemMessageId(754);
		S1_MINUTES_LEFT_THIS_WEEK = new SystemMessageId(755);
		WEEKS_USAGE_TIME_FINISHED = new SystemMessageId(756);
		S1_HOURS_S2_MINUTES_LEFT_IN_TIME = new SystemMessageId(757);
		S1_HOURS_S2_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME = new SystemMessageId(758);
		S1_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME = new SystemMessageId(759);
		C1_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN = new SystemMessageId(760);
		S1_CANT_ENTER_ALLIANCE_WITHIN_1_DAY = new SystemMessageId(761);
		C1_ROLLED_S2_S3_EYE_CAME_OUT = new SystemMessageId(762);
		FAILED_SENDING_PACKAGE_TOO_FAR = new SystemMessageId(763);
		PLAYING_FOR_LONG_TIME = new SystemMessageId(764);
		HACKING_TOOL = new SystemMessageId(769);
		PLAY_TIME_NO_LONGER_ACCUMULATING = new SystemMessageId(774);
		PLAY_TIME_EXPENDED = new SystemMessageId(775);
		CLANHALL_AWARDED_TO_CLAN = new SystemMessageId(776);
		CLANHALL_NOT_SOLD = new SystemMessageId(777);
		NO_LOGOUT_HERE = new SystemMessageId(778);
		NO_RESTART_HERE = new SystemMessageId(779);
		ONLY_VIEW_SIEGE = new SystemMessageId(780);
		OBSERVERS_CANNOT_PARTICIPATE = new SystemMessageId(781);
		NO_OBSERVE_WITH_PET = new SystemMessageId(782);
		LOTTERY_TICKET_SALES_TEMP_SUSPENDED = new SystemMessageId(783);
		NO_LOTTERY_TICKETS_AVAILABLE = new SystemMessageId(784);
		LOTTERY_S1_RESULT_NOT_PUBLISHED = new SystemMessageId(785);
		INCORRECT_SYNTAX = new SystemMessageId(786);
		CLANHALL_SIEGE_TRYOUTS_FINISHED = new SystemMessageId(787);
		CLANHALL_SIEGE_FINALS_FINISHED = new SystemMessageId(788);
		CLANHALL_SIEGE_TRYOUTS_BEGUN = new SystemMessageId(789);
		CLANHALL_SIEGE_FINALS_BEGUN = new SystemMessageId(790);
		FINAL_MATCH_BEGIN = new SystemMessageId(791);
		CLANHALL_SIEGE_ENDED = new SystemMessageId(792);
		CLANHALL_SIEGE_BEGUN = new SystemMessageId(793);
		YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT = new SystemMessageId(794);
		ONLY_LEADERS_CAN_SET_RIGHTS = new SystemMessageId(795);
		REMAINING_OBSERVATION_TIME = new SystemMessageId(796);
		YOU_MAY_CREATE_UP_TO_48_MACROS = new SystemMessageId(797);
		ITEM_REGISTRATION_IRREVERSIBLE = new SystemMessageId(798);
		OBSERVATION_TIME_EXPIRED = new SystemMessageId(799);
		REGISTRATION_PERIOD_OVER = new SystemMessageId(800);
		REGISTRATION_CLOSED = new SystemMessageId(801);
		PETITION_NOT_ACCEPTED_NOW = new SystemMessageId(802);
		PETITION_NOT_SPECIFIED = new SystemMessageId(803);
		SELECT_TYPE = new SystemMessageId(804);
		PETITION_NOT_ACCEPTED_SUBMIT_AT_S1 = new SystemMessageId(805);
		TRY_UNSTUCK_WHEN_TRAPPED = new SystemMessageId(806);
		STUCK_PREPARE_FOR_TRANSPORT = new SystemMessageId(807);
		STUCK_SUBMIT_PETITION = new SystemMessageId(808);
		STUCK_TRANSPORT_IN_FIVE_MINUTES = new SystemMessageId(809);
		INVALID_MACRO = new SystemMessageId(810);
		WILL_BE_MOVED = new SystemMessageId(811);
		TRAP_DID_S1_DAMAGE = new SystemMessageId(812);
		POISONED_BY_TRAP = new SystemMessageId(813);
		SLOWED_BY_TRAP = new SystemMessageId(814);
		TRYOUTS_ABOUT_TO_BEGIN = new SystemMessageId(815);
		MONSRACE_TICKETS_AVAILABLE_FOR_S1_RACE = new SystemMessageId(816);
		MONSRACE_TICKETS_NOW_AVAILABLE_FOR_S1_RACE = new SystemMessageId(817);
		MONSRACE_TICKETS_STOP_IN_S1_MINUTES = new SystemMessageId(818);
		MONSRACE_S1_TICKET_SALES_CLOSED = new SystemMessageId(819);
		MONSRACE_S2_BEGINS_IN_S1_MINUTES = new SystemMessageId(820);
		MONSRACE_S1_BEGINS_IN_30_SECONDS = new SystemMessageId(821);
		MONSRACE_S1_COUNTDOWN_IN_FIVE_SECONDS = new SystemMessageId(822);
		MONSRACE_BEGINS_IN_S1_SECONDS = new SystemMessageId(823);
		MONSRACE_RACE_START = new SystemMessageId(824);
		MONSRACE_S1_RACE_END = new SystemMessageId(825);
		MONSRACE_FIRST_PLACE_S1_SECOND_S2 = new SystemMessageId(826);
		YOU_MAY_NOT_IMPOSE_A_BLOCK_ON_GM = new SystemMessageId(827);
		WISH_TO_DELETE_S1_MACRO = new SystemMessageId(828);
		YOU_CANNOT_RECOMMEND_YOURSELF = new SystemMessageId(829);
		YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT = new SystemMessageId(830);
		YOU_HAVE_BEEN_RECOMMENDED_BY_C1 = new SystemMessageId(831);
		THAT_CHARACTER_IS_RECOMMENDED = new SystemMessageId(832);
		NO_MORE_RECOMMENDATIONS_TO_HAVE = new SystemMessageId(833);
		C1_ROLLED_S2 = new SystemMessageId(834);
		YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER = new SystemMessageId(835);
		YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_ITEM = new SystemMessageId(836);
		MACRO_DESCRIPTION_MAX_32_CHARS = new SystemMessageId(837);
		ENTER_THE_MACRO_NAME = new SystemMessageId(838);
		MACRO_NAME_ALREADY_USED = new SystemMessageId(839);
		RECIPE_ALREADY_REGISTERED = new SystemMessageId(840);
		NO_FUTHER_RECIPES_CAN_BE_ADDED = new SystemMessageId(841);
		NOT_AUTHORIZED_REGISTER_RECIPE = new SystemMessageId(842);
		SIEGE_OF_S1_FINISHED = new SystemMessageId(843);
		SIEGE_OF_S1_BEGUN = new SystemMessageId(844);
		DEADLINE_FOR_SIEGE_S1_PASSED = new SystemMessageId(845);
		SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST = new SystemMessageId(846);
		CLAN_OWNING_CLANHALL_MAY_NOT_SIEGE_CLANHALL = new SystemMessageId(847);
		S1_HAS_BEEN_DELETED = new SystemMessageId(848);
		S1_NOT_FOUND = new SystemMessageId(849);
		S1_ALREADY_EXISTS2 = new SystemMessageId(850);
		S1_ADDED = new SystemMessageId(851);
		RECIPE_INCORRECT = new SystemMessageId(852);
		CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING = new SystemMessageId(853);
		MISSING_S2_S1_TO_CREATE = new SystemMessageId(854);
		S1_CLAN_DEFEATED_S2 = new SystemMessageId(855);
		SIEGE_S1_DRAW = new SystemMessageId(856);
		S1_CLAN_WON_MATCH_S2 = new SystemMessageId(857);
		MATCH_OF_S1_DRAW = new SystemMessageId(858);
		PLEASE_REGISTER_RECIPE = new SystemMessageId(859);
		HEADQUARTERS_TOO_CLOSE = new SystemMessageId(860);
		TOO_MANY_MEMOS = new SystemMessageId(861);
		ODDS_NOT_POSTED = new SystemMessageId(862);
		FEEL_ENERGY_FIRE = new SystemMessageId(863);
		FEEL_ENERGY_WATER = new SystemMessageId(864);
		FEEL_ENERGY_WIND = new SystemMessageId(865);
		NO_LONGER_ENERGY = new SystemMessageId(866);
		ENERGY_DEPLETED = new SystemMessageId(867);
		ENERGY_FIRE_DELIVERED = new SystemMessageId(868);
		ENERGY_WATER_DELIVERED = new SystemMessageId(869);
		ENERGY_WIND_DELIVERED = new SystemMessageId(870);
		THE_SEED_HAS_BEEN_SOWN = new SystemMessageId(871);
		THIS_SEED_MAY_NOT_BE_SOWN_HERE = new SystemMessageId(872);
		CHARACTER_DOES_NOT_EXIST = new SystemMessageId(873);
		WAREHOUSE_CAPACITY_EXCEEDED = new SystemMessageId(874);
		CARGO_CANCELED = new SystemMessageId(875);
		CARGO_NOT_DELIVERED = new SystemMessageId(876);
		SYMBOL_ADDED = new SystemMessageId(877);
		SYMBOL_DELETED = new SystemMessageId(878);
		THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE = new SystemMessageId(879);
		THE_TRANSACTION_IS_COMPLETE = new SystemMessageId(880);
		THERE_IS_A_DISCREPANCY_ON_THE_INVOICE = new SystemMessageId(881);
		THE_SEED_QUANTITY_IS_INCORRECT = new SystemMessageId(882);
		THE_SEED_INFORMATION_IS_INCORRECT = new SystemMessageId(883);
		THE_MANOR_INFORMATION_HAS_BEEN_UPDATED = new SystemMessageId(884);
		THE_NUMBER_OF_CROPS_IS_INCORRECT = new SystemMessageId(885);
		THE_CROPS_ARE_PRICED_INCORRECTLY = new SystemMessageId(886);
		THE_TYPE_IS_INCORRECT = new SystemMessageId(887);
		NO_CROPS_CAN_BE_PURCHASED_AT_THIS_TIME = new SystemMessageId(888);
		THE_SEED_WAS_SUCCESSFULLY_SOWN = new SystemMessageId(889);
		THE_SEED_WAS_NOT_SOWN = new SystemMessageId(890);
		YOU_ARE_NOT_AUTHORIZED_TO_HARVEST = new SystemMessageId(891);
		THE_HARVEST_HAS_FAILED = new SystemMessageId(892);
		THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN = new SystemMessageId(893);
		UP_TO_S1_RECIPES_CAN_REGISTER = new SystemMessageId(894);
		NO_RECIPES_REGISTERED = new SystemMessageId(895);
		FERRY_AT_GLUDIN = new SystemMessageId(896);
		FERRY_LEAVE_TALKING = new SystemMessageId(897);
		ONLY_LEVEL_SUP_10_CAN_RECOMMEND = new SystemMessageId(898);
		CANT_DRAW_SYMBOL = new SystemMessageId(899);
		SYMBOLS_FULL = new SystemMessageId(900);
		SYMBOL_NOT_FOUND = new SystemMessageId(901);
		NUMBER_INCORRECT = new SystemMessageId(902);
		NO_PETITION_WHILE_FROZEN = new SystemMessageId(903);
		NO_DISCARD_WHILE_PRIVATE_STORE = new SystemMessageId(904);
		HUMAN_SCORE_S1 = new SystemMessageId(905);
		ELVES_SCORE_S1 = new SystemMessageId(906);
		DARK_ELVES_SCORE_S1 = new SystemMessageId(907);
		ORCS_SCORE_S1 = new SystemMessageId(908);
		DWARVEN_SCORE_S1 = new SystemMessageId(909);
		LOC_TI_S1_S2_S3 = new SystemMessageId(910);
		LOC_GLUDIN_S1_S2_S3 = new SystemMessageId(911);
		LOC_GLUDIO_S1_S2_S3 = new SystemMessageId(912);
		LOC_NETRAL_ZONE_S1_S2_S3 = new SystemMessageId(913);
		LOC_ELVEN_S1_S2_S3 = new SystemMessageId(914);
		LOC_DARK_ELVEN_S1_S2_S3 = new SystemMessageId(915);
		LOC_DION_S1_S2_S3 = new SystemMessageId(916);
		LOC_FLORAN_S1_S2_S3 = new SystemMessageId(917);
		LOC_GIRAN_S1_S2_S3 = new SystemMessageId(918);
		LOC_GIRAN_HARBOR_S1_S2_S3 = new SystemMessageId(919);
		LOC_ORC_S1_S2_S3 = new SystemMessageId(920);
		LOC_DWARVEN_S1_S2_S3 = new SystemMessageId(921);
		LOC_OREN_S1_S2_S3 = new SystemMessageId(922);
		LOC_HUNTER_S1_S2_S3 = new SystemMessageId(923);
		LOC_ADEN_S1_S2_S3 = new SystemMessageId(924);
		LOC_COLISEUM_S1_S2_S3 = new SystemMessageId(925);
		LOC_HEINE_S1_S2_S3 = new SystemMessageId(926);
		TIME_S1_S2_IN_THE_DAY = new SystemMessageId(927);
		TIME_S1_S2_IN_THE_NIGHT = new SystemMessageId(928);
		NO_COMPENSATION_FOR_FARM_PRODUCTS = new SystemMessageId(929);
		NO_LOTTERY_TICKETS_CURRENT_SOLD = new SystemMessageId(930);
		LOTTERY_WINNERS_NOT_ANNOUNCED_YET = new SystemMessageId(931);
		NO_ALLCHAT_WHILE_OBSERVING = new SystemMessageId(932);
		THE_SEED_PRICING_GREATLY_DIFFERS_FROM_STANDARD_SEED_PRICES = new SystemMessageId(933);
		A_DELETED_RECIPE = new SystemMessageId(934);
		THE_AMOUNT_IS_NOT_SUFFICIENT_AND_SO_THE_MANOR_IS_NOT_IN_OPERATION = new SystemMessageId(935);
		USE_S1_ = new SystemMessageId(936);
		PREPARING_PRIVATE_WORKSHOP = new SystemMessageId(937);
		CB_OFFLINE = new SystemMessageId(938);
		NO_EXCHANGE_WHILE_BLOCKING = new SystemMessageId(939);
		S1_BLOCKED_EVERYTHING = new SystemMessageId(940);
		RESTART_AT_TI = new SystemMessageId(941);
		RESTART_AT_GLUDIN = new SystemMessageId(942);
		RESTART_AT_GLUDIO = new SystemMessageId(943);
		RESTART_AT_NEUTRAL_ZONE = new SystemMessageId(944);
		RESTART_AT_ELFEN_VILLAGE = new SystemMessageId(945);
		RESTART_AT_DARKELF_VILLAGE = new SystemMessageId(946);
		RESTART_AT_DION = new SystemMessageId(947);
		RESTART_AT_FLORAN = new SystemMessageId(948);
		RESTART_AT_GIRAN = new SystemMessageId(949);
		RESTART_AT_GIRAN_HARBOR = new SystemMessageId(950);
		RESTART_AT_ORC_VILLAGE = new SystemMessageId(951);
		RESTART_AT_DWARFEN_VILLAGE = new SystemMessageId(952);
		RESTART_AT_OREN = new SystemMessageId(953);
		RESTART_AT_HUNTERS_VILLAGE = new SystemMessageId(954);
		RESTART_AT_ADEN = new SystemMessageId(955);
		RESTART_AT_COLISEUM = new SystemMessageId(956);
		RESTART_AT_HEINE = new SystemMessageId(957);
		ITEMS_CANNOT_BE_DISCARDED_OR_DESTROYED_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP = new SystemMessageId(958);
		S1_S2_MANUFACTURED_SUCCESSFULLY = new SystemMessageId(959);
		S1_MANUFACTURE_FAILURE = new SystemMessageId(960);
		BLOCKING_ALL = new SystemMessageId(961);
		NOT_BLOCKING_ALL = new SystemMessageId(962);
		DETERMINE_MANUFACTURE_PRICE = new SystemMessageId(963);
		CHATBAN_FOR_1_MINUTE = new SystemMessageId(964);
		CHATBAN_REMOVED = new SystemMessageId(965);
		CHATTING_IS_CURRENTLY_PROHIBITED = new SystemMessageId(966);
		C1_PARTY_INVITE_RANDOM_INCLUDING_SPOIL = new SystemMessageId(967);
		C1_PARTY_INVITE_BY_TURN = new SystemMessageId(968);
		C1_PARTY_INVITE_BY_TURN_INCLUDING_SPOIL = new SystemMessageId(969);
		S2_MP_HAS_BEEN_DRAINED_BY_C1 = new SystemMessageId(970);
		PETITION_MAX_CHARS_255 = new SystemMessageId(971);
		PET_CANNOT_USE_ITEM = new SystemMessageId(972);
		INPUT_NO_MORE_YOU_HAVE = new SystemMessageId(973);
		SOUL_CRYSTAL_ABSORBING_SUCCEEDED = new SystemMessageId(974);
		SOUL_CRYSTAL_ABSORBING_FAILED = new SystemMessageId(975);
		SOUL_CRYSTAL_BROKE = new SystemMessageId(976);
		SOUL_CRYSTAL_ABSORBING_FAILED_RESONATION = new SystemMessageId(977);
		SOUL_CRYSTAL_ABSORBING_REFUSED = new SystemMessageId(978);
		FERRY_ARRIVED_AT_TALKING = new SystemMessageId(979);
		FERRY_LEAVE_FOR_GLUDIN_AFTER_10_MINUTES = new SystemMessageId(980);
		FERRY_LEAVE_FOR_GLUDIN_IN_5_MINUTES = new SystemMessageId(981);
		FERRY_LEAVE_FOR_GLUDIN_IN_1_MINUTE = new SystemMessageId(982);
		MAKE_HASTE_GET_ON_BOAT = new SystemMessageId(983);
		FERRY_LEAVE_SOON_FOR_GLUDIN = new SystemMessageId(984);
		FERRY_LEAVING_FOR_GLUDIN = new SystemMessageId(985);
		FERRY_ARRIVED_AT_GLUDIN = new SystemMessageId(986);
		FERRY_LEAVE_FOR_TALKING_AFTER_10_MINUTES = new SystemMessageId(987);
		FERRY_LEAVE_FOR_TALKING_IN_5_MINUTES = new SystemMessageId(988);
		FERRY_LEAVE_FOR_TALKING_IN_1_MINUTE = new SystemMessageId(989);
		FERRY_LEAVE_SOON_FOR_TALKING = new SystemMessageId(990);
		FERRY_LEAVING_FOR_TALKING = new SystemMessageId(991);
		FERRY_ARRIVED_AT_GIRAN = new SystemMessageId(992);
		FERRY_LEAVE_FOR_GIRAN_AFTER_10_MINUTES = new SystemMessageId(993);
		FERRY_LEAVE_FOR_GIRAN_IN_5_MINUTES = new SystemMessageId(994);
		FERRY_LEAVE_FOR_GIRAN_IN_1_MINUTE = new SystemMessageId(995);
		FERRY_LEAVE_SOON_FOR_GIRAN = new SystemMessageId(996);
		FERRY_LEAVING_FOR_GIRAN = new SystemMessageId(997);
		INNADRIL_BOAT_ANCHOR_10_MINUTES = new SystemMessageId(998);
		INNADRIL_BOAT_LEAVE_IN_5_MINUTES = new SystemMessageId(999);
		INNADRIL_BOAT_LEAVE_IN_1_MINUTE = new SystemMessageId(1000);
		INNADRIL_BOAT_LEAVE_SOON = new SystemMessageId(1001);
		INNADRIL_BOAT_LEAVING = new SystemMessageId(1002);
		CANNOT_POSSES_MONS_TICKET = new SystemMessageId(1003);
		REGISTERED_FOR_CLANHALL = new SystemMessageId(1004);
		NOT_ENOUGH_ADENA_IN_CWH = new SystemMessageId(1005);
		BID_IN_CLANHALL_AUCTION = new SystemMessageId(1006);
		PRELIMINARY_REGISTRATION_OF_S1_FINISHED = new SystemMessageId(1007);
		HUNGRY_STRIDER_NOT_MOUNT = new SystemMessageId(1008);
		STRIDER_CANT_BE_RIDDEN_WHILE_DEAD = new SystemMessageId(1009);
		DEAD_STRIDER_CANT_BE_RIDDEN = new SystemMessageId(1010);
		STRIDER_IN_BATLLE_CANT_BE_RIDDEN = new SystemMessageId(1011);
		STRIDER_CANT_BE_RIDDEN_WHILE_IN_BATTLE = new SystemMessageId(1012);
		STRIDER_CAN_BE_RIDDEN_ONLY_WHILE_STANDING = new SystemMessageId(1013);
		PET_EARNED_S1_EXP = new SystemMessageId(1014);
		PET_HIT_FOR_S1_DAMAGE = new SystemMessageId(1015);
		PET_RECEIVED_S2_DAMAGE_BY_C1 = new SystemMessageId(1016);
		CRITICAL_HIT_BY_PET = new SystemMessageId(1017);
		PET_USES_S1 = new SystemMessageId(1018);
		PET_USES_S1_ = new SystemMessageId(1019);
		PET_PICKED_S1 = new SystemMessageId(1020);
		PET_PICKED_S2_S1_S = new SystemMessageId(1021);
		PET_PICKED_S1_S2 = new SystemMessageId(1022);
		PET_PICKED_S1_ADENA = new SystemMessageId(1023);
		PET_PUT_ON_S1 = new SystemMessageId(1024);
		PET_TOOK_OFF_S1 = new SystemMessageId(1025);
		SUMMON_GAVE_DAMAGE_S1 = new SystemMessageId(1026);
		SUMMON_RECEIVED_DAMAGE_S2_BY_S1 = new SystemMessageId(1027);
		CRITICAL_HIT_BY_SUMMONED_MOB = new SystemMessageId(1028);
		SUMMONED_MOB_USES_S1 = new SystemMessageId(1029);
		PARTY_INFORMATION = new SystemMessageId(1030);
		LOOTING_FINDERS_KEEPERS = new SystemMessageId(1031);
		LOOTING_RANDOM = new SystemMessageId(1032);
		LOOTING_RANDOM_INCLUDE_SPOIL = new SystemMessageId(1033);
		LOOTING_BY_TURN = new SystemMessageId(1034);
		LOOTING_BY_TURN_INCLUDE_SPOIL = new SystemMessageId(1035);
		YOU_HAVE_EXCEEDED_QUANTITY_THAT_CAN_BE_INPUTTED = new SystemMessageId(1036);
		C1_MANUFACTURED_S2 = new SystemMessageId(1037);
		C1_MANUFACTURED_S3_S2_S = new SystemMessageId(1038);
		ONLY_CLAN_LEADER_CAN_RETRIEVE_ITEMS_FROM_CLAN_WAREHOUSE = new SystemMessageId(1039);
		ITEMS_SENT_BY_FREIGHT_PICKED_UP_FROM_ANYWHERE = new SystemMessageId(1040);
		THE_NEXT_SEED_PURCHASE_PRICE_IS_S1_ADENA = new SystemMessageId(1041);
		THE_NEXT_FARM_GOODS_PURCHASE_PRICE_IS_S1_ADENA = new SystemMessageId(1042);
		NO_UNSTUCK_PLEASE_SEND_PETITION = new SystemMessageId(1043);
		MONSRACE_NO_PAYOUT_INFO = new SystemMessageId(1044);
		MONSRACE_TICKETS_NOT_AVAILABLE = new SystemMessageId(1046);
		NOT_SUCCEED_PRODUCING_S1 = new SystemMessageId(1047);
		NO_WHISPER_WHEN_BLOCKING = new SystemMessageId(1048);
		NO_PARTY_WHEN_BLOCKING = new SystemMessageId(1049);
		NO_CB_IN_MY_CLAN = new SystemMessageId(1050);
		PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW = new SystemMessageId(1051);
		THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED = new SystemMessageId(1052);
		CANNOT_BE_RESURRECTED_DURING_SIEGE = new SystemMessageId(1053);
		ENTERED_MYSTICAL_LAND = new SystemMessageId(1054);
		EXITED_MYSTICAL_LAND = new SystemMessageId(1055);
		VAULT_CAPACITY_EXCEEDED = new SystemMessageId(1056);
		RELAX_SERVER_ONLY = new SystemMessageId(1057);
		THE_SALES_PRICE_FOR_SEEDS_IS_S1_ADENA = new SystemMessageId(1058);
		THE_REMAINING_PURCHASING_IS_S1_ADENA = new SystemMessageId(1059);
		THE_REMAINDER_AFTER_SELLING_THE_SEEDS_IS_S1 = new SystemMessageId(1060);
		CANT_REGISTER_NO_ABILITY_TO_CRAFT = new SystemMessageId(1061);
		WRITING_SOMETHING_NEW_POSSIBLE_AFTER_LEVEL_10 = new SystemMessageId(1062);
		PETITION_UNAVAILABLE = new SystemMessageId(1063);
		EQUIPMENT_S1_S2_REMOVED = new SystemMessageId(1064);
		CANNOT_TRADE_DISCARD_DROP_ITEM_WHILE_IN_SHOPMODE = new SystemMessageId(1065);
		S1_HP_RESTORED = new SystemMessageId(1066);
		S2_HP_RESTORED_BY_C1 = new SystemMessageId(1067);
		S1_MP_RESTORED = new SystemMessageId(1068);
		S2_MP_RESTORED_BY_C1 = new SystemMessageId(1069);
		NO_READ_PERMISSION = new SystemMessageId(1070);
		NO_WRITE_PERMISSION = new SystemMessageId(1071);
		OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE = new SystemMessageId(1072);
		OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE_ = new SystemMessageId(1073);
		NOT_MEET_AGE_REQUIREMENT_FOR_MONS_RACE = new SystemMessageId(1074);
		BID_AMOUNT_HIGHER_THAN_PREVIOUS_BID = new SystemMessageId(1075);
		GAME_CANNOT_TERMINATE_NOW = new SystemMessageId(1076);
		GG_EXECUTION_ERROR = new SystemMessageId(1077);
		DONT_SPAM = new SystemMessageId(1078);
		TARGET_IS_CHAT_BANNED = new SystemMessageId(1079);
		FACELIFT_POTION_TYPE_A = new SystemMessageId(1080);
		HAIRDYE_POTION_TYPE_A = new SystemMessageId(1081);
		HAIRSTYLE_POTION_TYPE_A = new SystemMessageId(1082);
		FACELIFT_POTION_TYPE_A_APPLIED = new SystemMessageId(1083);
		HAIRDYE_POTION_TYPE_A_APPLIED = new SystemMessageId(1084);
		HAIRSTYLE_POTION_TYPE_A_USED = new SystemMessageId(1085);
		FACE_APPEARANCE_CHANGED = new SystemMessageId(1086);
		HAIR_COLOR_CHANGED = new SystemMessageId(1087);
		HAIR_STYLE_CHANGED = new SystemMessageId(1088);
		C1_OBTAINED_ANNIVERSARY_ITEM = new SystemMessageId(1089);
		FACELIFT_POTION_TYPE_B = new SystemMessageId(1090);
		FACELIFT_POTION_TYPE_C = new SystemMessageId(1091);
		HAIRDYE_POTION_TYPE_B = new SystemMessageId(1092);
		HAIRDYE_POTION_TYPE_C = new SystemMessageId(1093);
		HAIRDYE_POTION_TYPE_D = new SystemMessageId(1094);
		HAIRSTYLE_POTION_TYPE_B = new SystemMessageId(1095);
		HAIRSTYLE_POTION_TYPE_C = new SystemMessageId(1096);
		HAIRSTYLE_POTION_TYPE_D = new SystemMessageId(1097);
		HAIRSTYLE_POTION_TYPE_E = new SystemMessageId(1098);
		HAIRSTYLE_POTION_TYPE_F = new SystemMessageId(1099);
		HAIRSTYLE_POTION_TYPE_G = new SystemMessageId(1100);
		FACELIFT_POTION_TYPE_B_APPLIED = new SystemMessageId(1101);
		FACELIFT_POTION_TYPE_C_APPLIED = new SystemMessageId(1102);
		HAIRDYE_POTION_TYPE_B_APPLIED = new SystemMessageId(1103);
		HAIRDYE_POTION_TYPE_C_APPLIED = new SystemMessageId(1104);
		HAIRDYE_POTION_TYPE_D_APPLIED = new SystemMessageId(1105);
		HAIRSTYLE_POTION_TYPE_B_USED = new SystemMessageId(1106);
		HAIRSTYLE_POTION_TYPE_C_USED = new SystemMessageId(1107);
		HAIRSTYLE_POTION_TYPE_D_USED = new SystemMessageId(1108);
		HAIRSTYLE_POTION_TYPE_E_USED = new SystemMessageId(1109);
		HAIRSTYLE_POTION_TYPE_F_USED = new SystemMessageId(1110);
		HAIRSTYLE_POTION_TYPE_G_USED = new SystemMessageId(1111);
		AMOUNT_FOR_WINNER_S1_IS_S2_ADENA_WE_HAVE_S3_PRIZE_WINNER = new SystemMessageId(1112);
		AMOUNT_FOR_LOTTERY_S1_IS_S2_ADENA_NO_WINNER = new SystemMessageId(1113);
		CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS = new SystemMessageId(1114);
		INDIVIDUALS_NOT_SURRENDER_DURING_COMBAT = new SystemMessageId(1115);
		YOU_CANNOT_LEAVE_DURING_COMBAT = new SystemMessageId(1116);
		CLAN_MEMBER_CANNOT_BE_DISMISSED_DURING_COMBAT = new SystemMessageId(1117);
		INVENTORY_LESS_THAN_80_PERCENT = new SystemMessageId(1118);
		QUEST_CANCELED_INVENTORY_EXCEEDS_80_PERCENT = new SystemMessageId(1119);
		STILL_CLAN_MEMBER = new SystemMessageId(1120);
		NO_RIGHT_TO_VOTE = new SystemMessageId(1121);
		NO_CANDIDATE = new SystemMessageId(1122);
		WEIGHT_EXCEEDED_SKILL_UNAVAILABLE = new SystemMessageId(1123);
		NO_RECIPE_BOOK_WHILE_CASTING = new SystemMessageId(1124);
		CANNOT_CREATED_WHILE_ENGAGED_IN_TRADING = new SystemMessageId(1125);
		NO_NEGATIVE_NUMBER = new SystemMessageId(1126);
		REWARD_LESS_THAN_10_TIMES_STANDARD_PRICE = new SystemMessageId(1127);
		PRIVATE_STORE_NOT_WHILE_CASTING = new SystemMessageId(1128);
		NOT_ALLOWED_ON_BOAT = new SystemMessageId(1129);
		GIVEN_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_SERVITOR = new SystemMessageId(1130);
		NIGHT_EFFECT_APPLIES = new SystemMessageId(1131);
		DAY_EFFECT_DISAPPEARS = new SystemMessageId(1132);
		HP_DECREASED_EFFECT_APPLIES = new SystemMessageId(1133);
		HP_INCREASED_EFFECT_DISAPPEARS = new SystemMessageId(1134);
		CANT_OPERATE_PRIVATE_STORE_DURING_COMBAT = new SystemMessageId(1135);
		ACCOUNT_NOT_ALLOWED_TO_CONNECT = new SystemMessageId(1136);
		C1_HARVESTED_S3_S2S = new SystemMessageId(1137);
		C1_HARVESTED_S2S = new SystemMessageId(1138);
		INVENTORY_LIMIT_MUST_NOT_BE_EXCEEDED = new SystemMessageId(1139);
		WOULD_YOU_LIKE_TO_OPEN_THE_GATE = new SystemMessageId(1140);
		WOULD_YOU_LIKE_TO_CLOSE_THE_GATE = new SystemMessageId(1141);
		CANNOT_SUMMON_S1_AGAIN = new SystemMessageId(1142);
		SERVITOR_DISAPPEARED_NOT_ENOUGH_ITEMS = new SystemMessageId(1143);
		NOBODY_IN_GAME_TO_CHAT = new SystemMessageId(1144);
		S2_CREATED_FOR_C1_FOR_S3_ADENA = new SystemMessageId(1145);
		C1_CREATED_S2_FOR_S3_ADENA = new SystemMessageId(1146);
		S2_S3_S_CREATED_FOR_C1_FOR_S4_ADENA = new SystemMessageId(1147);
		C1_CREATED_S2_S3_S_FOR_S4_ADENA = new SystemMessageId(1148);
		CREATION_OF_S2_FOR_C1_AT_S3_ADENA_FAILED = new SystemMessageId(1149);
		C1_FAILED_TO_CREATE_S2_FOR_S3_ADENA = new SystemMessageId(1150);
		S2_SOLD_TO_C1_FOR_S3_ADENA = new SystemMessageId(1151);
		S3_S2_S_SOLD_TO_C1_FOR_S4_ADENA = new SystemMessageId(1152);
		S2_PURCHASED_FROM_C1_FOR_S3_ADENA = new SystemMessageId(1153);
		S3_S2_S_PURCHASED_FROM_C1_FOR_S4_ADENA = new SystemMessageId(1154);
		S3_S2_SOLD_TO_C1_FOR_S4_ADENA = new SystemMessageId(1155);
		S2_S3_PURCHASED_FROM_C1_FOR_S4_ADENA = new SystemMessageId(1156);
		TRYING_ON_STATE = new SystemMessageId(1157);
		CANNOT_DISMOUNT_FROM_ELEVATION = new SystemMessageId(1158);
		FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_10_MINUTES = new SystemMessageId(1159);
		FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_5_MINUTES = new SystemMessageId(1160);
		FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_1_MINUTE = new SystemMessageId(1161);
		FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_15_MINUTES = new SystemMessageId(1162);
		FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_10_MINUTES = new SystemMessageId(1163);
		FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_5_MINUTES = new SystemMessageId(1164);
		FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_1_MINUTE = new SystemMessageId(1165);
		FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_20_MINUTES = new SystemMessageId(1166);
		FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_15_MINUTES = new SystemMessageId(1167);
		FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_10_MINUTES = new SystemMessageId(1168);
		FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_5_MINUTES = new SystemMessageId(1169);
		FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_1_MINUTE = new SystemMessageId(1170);
		INNADRIL_BOAT_ARRIVE_20_MINUTES = new SystemMessageId(1171);
		INNADRIL_BOAT_ARRIVE_15_MINUTES = new SystemMessageId(1172);
		INNADRIL_BOAT_ARRIVE_10_MINUTES = new SystemMessageId(1173);
		INNADRIL_BOAT_ARRIVE_5_MINUTES = new SystemMessageId(1174);
		INNADRIL_BOAT_ARRIVE_1_MINUTE = new SystemMessageId(1175);
		QUEST_EVENT_PERIOD = new SystemMessageId(1176);
		VALIDATION_PERIOD = new SystemMessageId(1177);
		AVARICE_DESCRIPTION = new SystemMessageId(1178);
		GNOSIS_DESCRIPTION = new SystemMessageId(1179);
		STRIFE_DESCRIPTION = new SystemMessageId(1180);
		CHANGE_TITLE_CONFIRM = new SystemMessageId(1181);
		CREST_DELETE_CONFIRM = new SystemMessageId(1182);
		INITIAL_PERIOD = new SystemMessageId(1183);
		RESULTS_PERIOD = new SystemMessageId(1184);
		DAYS_LEFT_UNTIL_DELETION = new SystemMessageId(1185);
		TO_CREATE_ACCOUNT_VISIT_WEBSITE = new SystemMessageId(1186);
		ACCOUNT_INFORMATION_FORGOTTON_VISIT_WEBSITE = new SystemMessageId(1187);
		YOUR_TARGET_NO_LONGER_RECEIVE_A_RECOMMENDATION = new SystemMessageId(1188);
		TEMPORARY_ALLIANCE = new SystemMessageId(1189);
		TEMPORARY_ALLIANCE_DISSOLVED = new SystemMessageId(1190);
		FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_10_MINUTES = new SystemMessageId(1191);
		FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_5_MINUTES = new SystemMessageId(1192);
		FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_1_MINUTE = new SystemMessageId(1193);
		MERC_CAN_BE_ASSIGNED = new SystemMessageId(1194);
		MERC_CANT_BE_ASSIGNED_USING_STRIFE = new SystemMessageId(1195);
		FORCE_MAXIMUM = new SystemMessageId(1196);
		SUMMONING_SERVITOR_COSTS_S2_S1 = new SystemMessageId(1197);
		CRYSTALLIZATION_SUCCESSFUL = new SystemMessageId(1198);
		CLAN_WAR_HEADER = new SystemMessageId(1199);
		S1_S2_ALLIANCE = new SystemMessageId(1200);
		SELECT_QUEST_TO_ABOR = new SystemMessageId(1201);
		S1_NO_ALLI_EXISTS = new SystemMessageId(1202);
		NO_WAR_IN_PROGRESS = new SystemMessageId(1203);
		SCREENSHOT = new SystemMessageId(1204);
		MAILBOX_FULL = new SystemMessageId(1205);
		MEMOBOX_FULL = new SystemMessageId(1206);
		MAKE_AN_ENTRY = new SystemMessageId(1207);
		C1_DIED_DROPPED_S3_S2 = new SystemMessageId(1208);
		RAID_WAS_SUCCESSFUL = new SystemMessageId(1209);
		QUEST_EVENT_PERIOD_BEGUN = new SystemMessageId(1210);
		QUEST_EVENT_PERIOD_ENDED = new SystemMessageId(1211);
		DAWN_OBTAINED_AVARICE = new SystemMessageId(1212);
		DAWN_OBTAINED_GNOSIS = new SystemMessageId(1213);
		DAWN_OBTAINED_STRIFE = new SystemMessageId(1214);
		DUSK_OBTAINED_AVARICE = new SystemMessageId(1215);
		DUSK_OBTAINED_GNOSIS = new SystemMessageId(1216);
		DUSK_OBTAINED_STRIFE = new SystemMessageId(1217);
		SEAL_VALIDATION_PERIOD_BEGUN = new SystemMessageId(1218);
		SEAL_VALIDATION_PERIOD_ENDED = new SystemMessageId(1219);
		SUMMON_CONFIRM = new SystemMessageId(1220);
		RETURN_CONFIRM = new SystemMessageId(1221);
		LOC_GM_CONSULATION_SERVICE_S1_S2_S3 = new SystemMessageId(1222);
		DEPART_FOR_TALKING_5_MINUTES = new SystemMessageId(1223);
		DEPART_FOR_TALKING_1_MINUTE = new SystemMessageId(1224);
		DEPART_FOR_TALKING = new SystemMessageId(1225);
		LEAVING_FOR_TALKING = new SystemMessageId(1226);
		S1_UNREAD_MESSAGES = new SystemMessageId(1227);
		C1_BLOCKED_YOU_CANNOT_MAIL = new SystemMessageId(1228);
		NO_MORE_MESSAGES_TODAY = new SystemMessageId(1229);
		ONLY_FIVE_RECIPIENTS = new SystemMessageId(1230);
		SENT_MAIL = new SystemMessageId(1231);
		MESSAGE_NOT_SENT = new SystemMessageId(1232);
		NEW_MAIL = new SystemMessageId(1233);
		MAIL_STORED_IN_MAILBOX = new SystemMessageId(1234);
		ALL_FRIENDS_DELETE_CONFIRM = new SystemMessageId(1235);
		ENTER_SECURITY_CARD_NUMBER = new SystemMessageId(1236);
		ENTER_CARD_NUMBER_FOR_S1 = new SystemMessageId(1237);
		TEMP_MAILBOX_FULL = new SystemMessageId(1238);
		KEYBOARD_MODULE_FAILED_LOAD = new SystemMessageId(1239);
		DUSK_WON = new SystemMessageId(1240);
		DAWN_WON = new SystemMessageId(1241);
		NOT_VERIFIED_AGE_NO_LOGIN = new SystemMessageId(1242);
		SECURITY_CARD_NUMBER_INVALID = new SystemMessageId(1243);
		NOT_VERIFIED_AGE_LOG_OFF = new SystemMessageId(1244);
		LOGOUT_IN_S1_MINUTES = new SystemMessageId(1245);
		C1_DIED_DROPPED_S2_ADENA = new SystemMessageId(1246);
		CORPSE_TOO_OLD_SKILL_NOT_USED = new SystemMessageId(1247);
		OUT_OF_FEED_MOUNT_CANCELED = new SystemMessageId(1248);
		YOU_MAY_ONLY_RIDE_WYVERN_WHILE_RIDING_STRIDER = new SystemMessageId(1249);
		SURRENDER_ALLY_WAR_CONFIRM = new SystemMessageId(1250);
		DISMISS_ALLY_CONFIRM = new SystemMessageId(1251);
		SURRENDER_CONFIRM1 = new SystemMessageId(1252);
		SURRENDER_CONFIRM2 = new SystemMessageId(1253);
		THANKS_FOR_FEEDBACK = new SystemMessageId(1254);
		GM_CONSULTATION_BEGUN = new SystemMessageId(1255);
		PLEASE_WRITE_NAME_AFTER_COMMAND = new SystemMessageId(1256);
		PET_SKILL_NOT_AS_MACRO = new SystemMessageId(1257);
		S1_CRYSTALLIZED = new SystemMessageId(1258);
		ALLIANCE_TARGET_HEADER = new SystemMessageId(1259);
		PREPARATIONS_PERIOD_BEGUN = new SystemMessageId(1260);
		COMPETITION_PERIOD_BEGUN = new SystemMessageId(1261);
		RESULTS_PERIOD_BEGUN = new SystemMessageId(1262);
		VALIDATION_PERIOD_BEGUN = new SystemMessageId(1263);
		STONE_CANNOT_ABSORB = new SystemMessageId(1264);
		CANT_ABSORB_WITHOUT_STONE = new SystemMessageId(1265);
		EXCHANGE_HAS_ENDED = new SystemMessageId(1266);
		CONTRIB_SCORE_INCREASED_S1 = new SystemMessageId(1267);
		ADD_SUBCLASS_CONFIRM = new SystemMessageId(1268);
		ADD_NEW_SUBCLASS = new SystemMessageId(1269);
		SUBCLASS_TRANSFER_COMPLETED = new SystemMessageId(1270);
		DAWN_CONFIRM = new SystemMessageId(1271);
		DUSK_CONFIRM = new SystemMessageId(1272);
		SEVENSIGNS_PARTECIPATION_DAWN = new SystemMessageId(1273);
		SEVENSIGNS_PARTECIPATION_DUSK = new SystemMessageId(1274);
		FIGHT_FOR_AVARICE = new SystemMessageId(1275);
		FIGHT_FOR_GNOSIS = new SystemMessageId(1276);
		FIGHT_FOR_STRIFE = new SystemMessageId(1277);
		NPC_SERVER_NOT_OPERATING = new SystemMessageId(1278);
		CONTRIB_SCORE_EXCEEDED = new SystemMessageId(1279);
		CRITICAL_HIT_MAGIC = new SystemMessageId(1280);
		YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS = new SystemMessageId(1281);
		YOUR_KARMA_HAS_BEEN_CHANGED_TO_S1 = new SystemMessageId(1282);
		MINIMUM_FRAME_ACTIVATED = new SystemMessageId(1283);
		MINIMUM_FRAME_DEACTIVATED = new SystemMessageId(1284);
		NO_INVENTORY_CANNOT_PURCHASE = new SystemMessageId(1285);
		UNTIL_MONDAY_6PM = new SystemMessageId(1286);
		UNTIL_TODAY_6PM = new SystemMessageId(1287);
		S1_WILL_WIN_COMPETITION = new SystemMessageId(1288);
		SEAL_OWNED_10_MORE_VOTED = new SystemMessageId(1289);
		SEAL_NOT_OWNED_35_MORE_VOTED = new SystemMessageId(1290);
		SEAL_OWNED_10_LESS_VOTED = new SystemMessageId(1291);
		SEAL_NOT_OWNED_35_LESS_VOTED = new SystemMessageId(1292);
		COMPETITION_WILL_TIE = new SystemMessageId(1293);
		COMPETITION_TIE_SEAL_NOT_AWARDED = new SystemMessageId(1294);
		SUBCLASS_NO_CHANGE_OR_CREATE_WHILE_SKILL_IN_USE = new SystemMessageId(1295);
		NO_PRIVATE_STORE_HERE = new SystemMessageId(1296);
		NO_PRIVATE_WORKSHOP_HERE = new SystemMessageId(1297);
		MONS_EXIT_CONFIRM = new SystemMessageId(1298);
		C1_CASTING_INTERRUPTED = new SystemMessageId(1299);
		WEAR_ITEMS_STOPPED = new SystemMessageId(1300);
		CAN_BE_USED_BY_DAWN = new SystemMessageId(1301);
		CAN_BE_USED_BY_DUSK = new SystemMessageId(1302);
		CAN_BE_USED_DURING_QUEST_EVENT_PERIOD = new SystemMessageId(1303);
		STRIFE_CANCELED_DEFENSIVE_REGISTRATION = new SystemMessageId(1304);
		SEAL_STONES_ONLY_WHILE_QUEST = new SystemMessageId(1305);
		NO_LONGER_TRYING_ON = new SystemMessageId(1306);
		SETTLE_ACCOUNT_ONLY_IN_SEAL_VALIDATION = new SystemMessageId(1307);
		CLASS_TRANSFER = new SystemMessageId(1308);
		LATEST_MSN_REQUIRED = new SystemMessageId(1309);
		LATEST_MSN_RECOMMENDED = new SystemMessageId(1310);
		MSN_ONLY_BASIC = new SystemMessageId(1311);
		MSN_OBTAINED_FROM = new SystemMessageId(1312);
		S1_CHAT_HISTORIES_STORED = new SystemMessageId(1313);
		ENTER_PASSPORT_FOR_ADDING = new SystemMessageId(1314);
		DELETING_A_CONTACT = new SystemMessageId(1315);
		CONTACT_WILL_DELETED = new SystemMessageId(1316);
		CONTACT_DELETE_CONFIRM = new SystemMessageId(1317);
		SELECT_CONTACT_FOR_BLOCK_UNBLOCK = new SystemMessageId(1318);
		SELECT_CONTACT_FOR_CHANGE_GROUP = new SystemMessageId(1319);
		SELECT_GROUP_PRESS_OK = new SystemMessageId(1320);
		ENTER_GROUP_NAME = new SystemMessageId(1321);
		SELECT_GROUP_ENTER_NAME = new SystemMessageId(1322);
		SELECT_GROUP_TO_DELETE = new SystemMessageId(1323);
		SIGNING_IN = new SystemMessageId(1324);
		ANOTHER_COMPUTER_LOGOUT = new SystemMessageId(1325);
		S1_D = new SystemMessageId(1326);
		MESSAGE_NOT_DELIVERED = new SystemMessageId(1327);
		DUSK_NOT_RESURRECTED = new SystemMessageId(1328);
		BLOCKED_FROM_USING_STORE = new SystemMessageId(1329);
		NO_STORE_FOR_S1_MINUTES = new SystemMessageId(1330);
		NO_LONGER_BLOCKED_USING_STORE = new SystemMessageId(1331);
		NO_ITEMS_AFTER_DEATH = new SystemMessageId(1332);
		REPLAY_INACCESSIBLE = new SystemMessageId(1333);
		NEW_CAMERA_STORED = new SystemMessageId(1334);
		CAMERA_STORING_FAILED = new SystemMessageId(1335);
		REPLAY_S1_S2_CORRUPTED = new SystemMessageId(1336);
		REPLAY_TERMINATE_CONFIRM = new SystemMessageId(1337);
		EXCEEDED_MAXIMUM_AMOUNT = new SystemMessageId(1338);
		MACRO_SHORTCUT_NOT_RUN = new SystemMessageId(1339);
		SERVER_NOT_ACCESSED_BY_COUPON = new SystemMessageId(1340);
		INCORRECT_NAME_OR_ADDRESS = new SystemMessageId(1341);
		ALREADY_LOGGED_IN = new SystemMessageId(1342);
		INCORRECT_ADDRESS_OR_PASSWORD = new SystemMessageId(1343);
		NET_LOGIN_FAILED = new SystemMessageId(1344);
		SELECT_CONTACT_CLICK_OK = new SystemMessageId(1345);
		CURRENTLY_ENTERING_CHAT = new SystemMessageId(1346);
		MESSENGER_FAILED_CARRYING_OUT_TASK = new SystemMessageId(1347);
		S1_ENTERED_CHAT_ROOM = new SystemMessageId(1348);
		S1_LEFT_CHAT_ROOM = new SystemMessageId(1349);
		GOING_OFFLINE = new SystemMessageId(1350);
		SELECT_CONTACT_CLICK_REMOVE = new SystemMessageId(1351);
		ADDED_TO_S1_S2_CONTACT_LIST = new SystemMessageId(1352);
		CAN_SET_OPTION_TO_ALWAYS_SHOW_OFFLINE = new SystemMessageId(1353);
		NO_CHAT_WHILE_BLOCKED = new SystemMessageId(1354);
		CONTACT_CURRENTLY_BLOCKED = new SystemMessageId(1355);
		CONTACT_CURRENTLY_OFFLINE = new SystemMessageId(1356);
		YOU_ARE_BLOCKED = new SystemMessageId(1357);
		YOU_ARE_LOGGING_OUT = new SystemMessageId(1358);
		S1_LOGGED_IN2 = new SystemMessageId(1359);
		GOT_MESSAGE_FROM_S1 = new SystemMessageId(1360);
		LOGGED_OUT_DUE_TO_ERROR = new SystemMessageId(1361);
		SELECT_CONTACT_TO_DELETE = new SystemMessageId(1362);
		YOUR_REQUEST_ALLIANCE_WAR_DENIED = new SystemMessageId(1363);
		REQUEST_ALLIANCE_WAR_REJECTED = new SystemMessageId(1364);
		S2_OF_S1_SURRENDERED_AS_INDIVIDUAL = new SystemMessageId(1365);
		DELTE_GROUP_INSTRUCTION = new SystemMessageId(1366);
		ONLY_GROUP_CAN_ADD_RECORDS = new SystemMessageId(1367);
		YOU_CAN_NOT_TRY_THOSE_ITEMS_ON_AT_THE_SAME_TIME = new SystemMessageId(1368);
		EXCEEDED_THE_MAXIMUM = new SystemMessageId(1369);
		CANNOT_MAIL_GM_C1 = new SystemMessageId(1370);
		GAMEPLAY_RESTRICTION_PENALTY_S1 = new SystemMessageId(1371);
		PUNISHMENT_CONTINUE_S1_MINUTES = new SystemMessageId(1372);
		C1_PICKED_UP_S2_FROM_RAIDBOSS = new SystemMessageId(1373);
		C1_PICKED_UP_S3_S2_S_FROM_RAIDBOSS = new SystemMessageId(1374);
		C1_PICKED_UP_S2_ADENA_FROM_RAIDBOSS = new SystemMessageId(1375);
		C1_PICKED_UP_S2_FROM_ANOTHER_CHARACTER = new SystemMessageId(1376);
		C1_PICKED_UP_S3_S2_S_FROM_ANOTHER_CHARACTER = new SystemMessageId(1377);
		C1_PICKED_UP_S3_S2_FROM_ANOTHER_CHARACTER = new SystemMessageId(1378);
		C1_OBTAINED_S2_ADENA = new SystemMessageId(1379);
		CANT_SUMMON_S1_ON_BATTLEGROUND = new SystemMessageId(1380);
		LEADER_OBTAINED_S2_OF_S1 = new SystemMessageId(1381);
		CHOOSE_WEAPON_CONFIRM = new SystemMessageId(1382);
		EXCHANGE_CONFIRM = new SystemMessageId(1383);
		C1_HAS_BECOME_A_PARTY_LEADER = new SystemMessageId(1384);
		NO_DISMOUNT_HERE = new SystemMessageId(1385);
		NO_LONGER_HELD_IN_PLACE = new SystemMessageId(1386);
		SELECT_ITEM_TO_TRY_ON = new SystemMessageId(1387);
		PARTY_ROOM_CREATED = new SystemMessageId(1388);
		PARTY_ROOM_REVISED = new SystemMessageId(1389);
		PARTY_ROOM_FORBIDDEN = new SystemMessageId(1390);
		PARTY_ROOM_EXITED = new SystemMessageId(1391);
		C1_LEFT_PARTY_ROOM = new SystemMessageId(1392);
		OUSTED_FROM_PARTY_ROOM = new SystemMessageId(1393);
		C1_KICKED_FROM_PARTY_ROOM = new SystemMessageId(1394);
		PARTY_ROOM_DISBANDED = new SystemMessageId(1395);
		CANT_VIEW_PARTY_ROOMS = new SystemMessageId(1396);
		PARTY_ROOM_LEADER_CHANGED = new SystemMessageId(1397);
		RECRUITING_PARTY_MEMBERS = new SystemMessageId(1398);
		ONLY_A_PARTY_LEADER_CAN_TRANSFER_ONES_RIGHTS_TO_ANOTHER_PLAYER = new SystemMessageId(1399);
		PLEASE_SELECT_THE_PERSON_TO_WHOM_YOU_WOULD_LIKE_TO_TRANSFER_THE_RIGHTS_OF_A_PARTY_LEADER = new SystemMessageId(1400);
		YOU_CANNOT_TRANSFER_RIGHTS_TO_YOURSELF = new SystemMessageId(1401);
		YOU_CAN_TRANSFER_RIGHTS_ONLY_TO_ANOTHER_PARTY_MEMBER = new SystemMessageId(1402);
		YOU_HAVE_FAILED_TO_TRANSFER_THE_PARTY_LEADER_RIGHTS = new SystemMessageId(1403);
		MANUFACTURE_PRICE_HAS_CHANGED = new SystemMessageId(1404);
		S1_CP_WILL_BE_RESTORED = new SystemMessageId(1405);
		S2_CP_WILL_BE_RESTORED_BY_C1 = new SystemMessageId(1406);
		NO_LOGIN_WITH_TWO_ACCOUNTS = new SystemMessageId(1407);
		PREPAID_LEFT_S1_S2_S3 = new SystemMessageId(1408);
		PREPAID_EXPIRED_S1_S2 = new SystemMessageId(1409);
		PREPAID_EXPIRED = new SystemMessageId(1410);
		PREPAID_CHANGED = new SystemMessageId(1411);
		PREPAID_LEFT_S1 = new SystemMessageId(1412);
		CANT_ENTER_PARTY_ROOM = new SystemMessageId(1413);
		WRONG_GRID_COUNT = new SystemMessageId(1414);
		COMMAND_FILE_NOT_SENT = new SystemMessageId(1415);
		TEAM_1_NO_REPRESENTATIVE = new SystemMessageId(1416);
		TEAM_2_NO_REPRESENTATIVE = new SystemMessageId(1417);
		TEAM_1_NO_NAME = new SystemMessageId(1418);
		TEAM_2_NO_NAME = new SystemMessageId(1419);
		TEAM_NAME_IDENTICAL = new SystemMessageId(1420);
		RACE_SETUP_FILE1 = new SystemMessageId(1421);
		RACE_SETUP_FILE2 = new SystemMessageId(1422);
		RACE_SETUP_FILE3 = new SystemMessageId(1423);
		RACE_SETUP_FILE4 = new SystemMessageId(1424);
		RACE_SETUP_FILE5 = new SystemMessageId(1425);
		RACE_SETUP_FILE6 = new SystemMessageId(1426);
		RACE_SETUP_FILE7 = new SystemMessageId(1427);
		RACE_SETUP_FILE8 = new SystemMessageId(1428);
		RACE_SETUP_FILE9 = new SystemMessageId(1429);
		RACE_SETUP_FILE10 = new SystemMessageId(1430);
		RACE_STOPPED_TEMPORARILY = new SystemMessageId(1431);
		OPPONENT_PETRIFIED = new SystemMessageId(1432);
		USE_OF_S1_WILL_BE_AUTO = new SystemMessageId(1433);
		AUTO_USE_OF_S1_CANCELLED = new SystemMessageId(1434);
		AUTO_USE_CANCELLED_LACK_OF_S1 = new SystemMessageId(1435);
		CANNOT_AUTO_USE_LACK_OF_S1 = new SystemMessageId(1436);
		DICE_NO_LONGER_ALLOWED = new SystemMessageId(1437);
		THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT = new SystemMessageId(1438);
		YOU_DONT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_THAT_SKILL = new SystemMessageId(1439);
		YOU_HAVE_SUCCEEDED_IN_ENCHANTING_THE_SKILL_S1 = new SystemMessageId(1440);
		YOU_HAVE_FAILED_TO_ENCHANT_THE_SKILL_S1 = new SystemMessageId(1441);
		YOU_DONT_HAVE_ENOUGH_SP_TO_ENCHANT_THAT_SKILL = new SystemMessageId(1443);
		YOU_DONT_HAVE_ENOUGH_EXP_TO_ENCHANT_THAT_SKILL = new SystemMessageId(1444);
		REPLACE_SUBCLASS_CONFIRM = new SystemMessageId(1445);
		FERRY_FROM_S1_TO_S2_DELAYED = new SystemMessageId(1446);
		CANNOT_DO_WHILE_FISHING_1 = new SystemMessageId(1447);
		ONLY_FISHING_SKILLS_NOW = new SystemMessageId(1448);
		GOT_A_BITE = new SystemMessageId(1449);
		FISH_SPIT_THE_HOOK = new SystemMessageId(1450);
		BAIT_STOLEN_BY_FISH = new SystemMessageId(1451);
		BAIT_LOST_FISH_GOT_AWAY = new SystemMessageId(1452);
		FISHING_POLE_NOT_EQUIPPED = new SystemMessageId(1453);
		BAIT_ON_HOOK_BEFORE_FISHING = new SystemMessageId(1454);
		CANNOT_FISH_UNDER_WATER = new SystemMessageId(1455);
		CANNOT_FISH_ON_BOAT = new SystemMessageId(1456);
		CANNOT_FISH_HERE = new SystemMessageId(1457);
		FISHING_ATTEMPT_CANCELLED = new SystemMessageId(1458);
		NOT_ENOUGH_BAIT = new SystemMessageId(1459);
		REEL_LINE_AND_STOP_FISHING = new SystemMessageId(1460);
		CAST_LINE_AND_START_FISHING = new SystemMessageId(1461);
		CAN_USE_PUMPING_ONLY_WHILE_FISHING = new SystemMessageId(1462);
		CAN_USE_REELING_ONLY_WHILE_FISHING = new SystemMessageId(1463);
		FISH_RESISTED_ATTEMPT_TO_BRING_IT_IN = new SystemMessageId(1464);
		PUMPING_SUCCESFUL_S1_DAMAGE = new SystemMessageId(1465);
		FISH_RESISTED_PUMPING_S1_HP_REGAINED = new SystemMessageId(1466);
		REELING_SUCCESFUL_S1_DAMAGE = new SystemMessageId(1467);
		FISH_RESISTED_REELING_S1_HP_REGAINED = new SystemMessageId(1468);
		YOU_CAUGHT_SOMETHING = new SystemMessageId(1469);
		CANNOT_DO_WHILE_FISHING_2 = new SystemMessageId(1470);
		CANNOT_DO_WHILE_FISHING_3 = new SystemMessageId(1471);
		CANNOT_ATTACK_WITH_FISHING_POLE = new SystemMessageId(1472);
		S1_NOT_SUFFICIENT = new SystemMessageId(1473);
		S1_NOT_AVAILABLE = new SystemMessageId(1474);
		PET_DROPPED_S1 = new SystemMessageId(1475);
		PET_DROPPED_S1_S2 = new SystemMessageId(1476);
		PET_DROPPED_S2_S1_S = new SystemMessageId(1477);
		ONLY_64_PIXEL_256_COLOR_BMP = new SystemMessageId(1478);
		WRONG_FISHINGSHOT_GRADE = new SystemMessageId(1479);
		OLYMPIAD_REMOVE_CONFIRM = new SystemMessageId(1480);
		OLYMPIAD_NON_CLASS_CONFIRM = new SystemMessageId(1481);
		OLYMPIAD_CLASS_CONFIRM = new SystemMessageId(1482);
		HERO_CONFIRM = new SystemMessageId(1483);
		HERO_WEAPON_CONFIRM = new SystemMessageId(1484);
		FERRY_TALKING_GLUDIN_DELAYED = new SystemMessageId(1485);
		FERRY_GLUDIN_TALKING_DELAYED = new SystemMessageId(1486);
		FERRY_GIRAN_TALKING_DELAYED = new SystemMessageId(1487);
		FERRY_TALKING_GIRAN_DELAYED = new SystemMessageId(1488);
		INNADRIL_BOAT_DELAYED = new SystemMessageId(1489);
		TRADED_S2_OF_CROP_S1 = new SystemMessageId(1490);
		FAILED_IN_TRADING_S2_OF_CROP_S1 = new SystemMessageId(1491);
		YOU_WILL_ENTER_THE_OLYMPIAD_STADIUM_IN_S1_SECOND_S = new SystemMessageId(1492);
		THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_ENDS_THE_GAME = new SystemMessageId(1493);
		THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_JOINING_THE_GAME = new SystemMessageId(1494);
		THE_GAME_WILL_START_IN_S1_SECOND_S = new SystemMessageId(1495);
		STARTS_THE_GAME = new SystemMessageId(1496);
		C1_HAS_WON_THE_GAME = new SystemMessageId(1497);
		THE_GAME_ENDED_IN_A_TIE = new SystemMessageId(1498);
		YOU_WILL_BE_MOVED_TO_TOWN_IN_S1_SECONDS = new SystemMessageId(1499);
		C1_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_CLASS_CHARACTER = new SystemMessageId(1500);
		C1_DOES_NOT_MEET_REQUIREMENTS_ONLY_NOBLESS_CAN_PARTICIPATE_IN_THE_OLYMPIAD = new SystemMessageId(1501);
		C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST = new SystemMessageId(1502);
		YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_CLASSIFIED_GAMES = new SystemMessageId(1503);
		YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_NO_CLASS_GAMES = new SystemMessageId(1504);
		YOU_HAVE_BEEN_DELETED_FROM_THE_WAITING_LIST_OF_A_GAME = new SystemMessageId(1505);
		YOU_HAVE_NOT_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_A_GAME = new SystemMessageId(1506);
		THIS_ITEM_CANT_BE_EQUIPPED_FOR_THE_OLYMPIAD_EVENT = new SystemMessageId(1507);
		THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT = new SystemMessageId(1508);
		THIS_SKILL_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT = new SystemMessageId(1509);
		RESSURECTION_REQUEST_BY_C1_FOR_S2_XP = new SystemMessageId(1510);
		MASTER_CANNOT_RES = new SystemMessageId(1511);
		CANNOT_RES_PET = new SystemMessageId(1512);
		RES_HAS_ALREADY_BEEN_PROPOSED = new SystemMessageId(1513);
		CANNOT_RES_MASTER = new SystemMessageId(1514);
		CANNOT_RES_PET2 = new SystemMessageId(1515);
		THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING = new SystemMessageId(1516);
		BLESSED_ENCHANT_FAILED = new SystemMessageId(1517);
		CANNOT_EQUIP_ITEM_DUE_TO_BAD_CONDITION = new SystemMessageId(1518);
		MAKE_SURE_YOU_RESSURECT_YOUR_PET_WITHIN_24_HOURS = new SystemMessageId(1519);
		SERVITOR_PASSED_AWAY = new SystemMessageId(1520);
		YOUR_SERVITOR_HAS_VANISHED = new SystemMessageId(1521);
		YOUR_PETS_CORPSE_HAS_DECAYED = new SystemMessageId(1522);
		RELEASE_PET_ON_BOAT = new SystemMessageId(1523);
		C1_PET_GAINED_S2 = new SystemMessageId(1524);
		C1_PET_GAINED_S3_S2_S = new SystemMessageId(1525);
		C1_PET_GAINED_S2_S3 = new SystemMessageId(1526);
		PET_TOOK_S1_BECAUSE_HE_WAS_HUNGRY = new SystemMessageId(1527);
		SENT_PETITION_TO_GM = new SystemMessageId(1528);
		COMMAND_CHANNEL_CONFIRM_FROM_C1 = new SystemMessageId(1529);
		SELECT_TARGET_OR_ENTER_NAME = new SystemMessageId(1530);
		ENTER_CLAN_NAME_TO_DECLARE_WAR2 = new SystemMessageId(1531);
		ENTER_CLAN_NAME_TO_CEASE_FIRE = new SystemMessageId(1532);
		ANNOUNCEMENT_C1_PICKED_UP_S2 = new SystemMessageId(1533);
		ANNOUNCEMENT_C1_PICKED_UP_S2_S3 = new SystemMessageId(1534);
		ANNOUNCEMENT_C1_PET_PICKED_UP_S2 = new SystemMessageId(1535);
		ANNOUNCEMENT_C1_PET_PICKED_UP_S2_S3 = new SystemMessageId(1536);
		LOC_RUNE_S1_S2_S3 = new SystemMessageId(1537);
		LOC_GODDARD_S1_S2_S3 = new SystemMessageId(1538);
		CARGO_AT_TALKING_VILLAGE = new SystemMessageId(1539);
		CARGO_AT_DARKELF_VILLAGE = new SystemMessageId(1540);
		CARGO_AT_ELVEN_VILLAGE = new SystemMessageId(1541);
		CARGO_AT_ORC_VILLAGE = new SystemMessageId(1542);
		CARGO_AT_DWARVEN_VILLAGE = new SystemMessageId(1543);
		CARGO_AT_ADEN = new SystemMessageId(1544);
		CARGO_AT_OREN = new SystemMessageId(1545);
		CARGO_AT_HUNTERS = new SystemMessageId(1546);
		CARGO_AT_DION = new SystemMessageId(1547);
		CARGO_AT_FLORAN = new SystemMessageId(1548);
		CARGO_AT_GLUDIN = new SystemMessageId(1549);
		CARGO_AT_GLUDIO = new SystemMessageId(1550);
		CARGO_AT_GIRAN = new SystemMessageId(1551);
		CARGO_AT_HEINE = new SystemMessageId(1552);
		CARGO_AT_RUNE = new SystemMessageId(1553);
		CARGO_AT_GODDARD = new SystemMessageId(1554);
		CANCEL_CHARACTER_DELETION_CONFIRM = new SystemMessageId(1555);
		CLAN_NOTICE_SAVED = new SystemMessageId(1556);
		SEED_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = new SystemMessageId(1557);
		THE_QUANTITY_OF_SEED_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = new SystemMessageId(1558);
		CROP_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = new SystemMessageId(1559);
		THE_QUANTITY_OF_CROP_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2 = new SystemMessageId(1560);
		CLAN_S1_DECLARED_WAR = new SystemMessageId(1561);
		CLAN_WAR_DECLARED_AGAINST_S1_IF_KILLED_LOSE_LOW_EXP = new SystemMessageId(1562);
		CANNOT_DECLARE_WAR_TOO_LOW_LEVEL_OR_NOT_ENOUGH_MEMBERS = new SystemMessageId(1563);
		CLAN_WAR_DECLARED_IF_CLAN_LVL3_OR_15_MEMBER = new SystemMessageId(1564);
		CLAN_WAR_CANNOT_DECLARED_CLAN_NOT_EXIST = new SystemMessageId(1565);
		CLAN_S1_HAS_DECIDED_TO_STOP = new SystemMessageId(1566);
		WAR_AGAINST_S1_HAS_STOPPED = new SystemMessageId(1567);
		WRONG_DECLARATION_TARGET = new SystemMessageId(1568);
		CLAN_WAR_AGAINST_A_ALLIED_CLAN_NOT_WORK = new SystemMessageId(1569);
		TOO_MANY_CLAN_WARS = new SystemMessageId(1570);
		CLANS_YOU_DECLARED_WAR_ON = new SystemMessageId(1571);
		CLANS_THAT_HAVE_DECLARED_WAR_ON_YOU = new SystemMessageId(1572);
		NO_WARS_AGAINST_YOU = new SystemMessageId(1573);
		COMMAND_CHANNEL_ONLY_BY_LEVEL_5_CLAN_LEADER_PARTY_LEADER = new SystemMessageId(1574);
		PET_USE_SPIRITSHOT = new SystemMessageId(1575);
		SERVITOR_USE_SPIRITSHOT = new SystemMessageId(1576);
		SERVITOR_USE_THE_POWER_OF_SPIRIT = new SystemMessageId(1577);
		ITEMS_UNAVAILABLE_FOR_STORE_MANUFACTURE = new SystemMessageId(1578);
		C1_PET_GAINED_S2_ADENA = new SystemMessageId(1579);
		COMMAND_CHANNEL_FORMED = new SystemMessageId(1580);
		COMMAND_CHANNEL_DISBANDED = new SystemMessageId(1581);
		JOINED_COMMAND_CHANNEL = new SystemMessageId(1582);
		DISMISSED_FROM_COMMAND_CHANNEL = new SystemMessageId(1583);
		C1_PARTY_DISMISSED_FROM_COMMAND_CHANNEL = new SystemMessageId(1584);
		COMMAND_CHANNEL_DISBANDED2 = new SystemMessageId(1585);
		LEFT_COMMAND_CHANNEL = new SystemMessageId(1586);
		C1_PARTY_LEFT_COMMAND_CHANNEL = new SystemMessageId(1587);
		COMMAND_CHANNEL_ONLY_AT_LEAST_5_PARTIES = new SystemMessageId(1588);
		COMMAND_CHANNEL_LEADER_NOW_C1 = new SystemMessageId(1589);
		GUILD_INFO_HEADER = new SystemMessageId(1590);
		NO_USER_INVITED_TO_COMMAND_CHANNEL = new SystemMessageId(1591);
		CANNOT_LONGER_SETUP_COMMAND_CHANNEL = new SystemMessageId(1592);
		CANNOT_INVITE_TO_COMMAND_CHANNEL = new SystemMessageId(1593);
		C1_ALREADY_MEMBER_OF_COMMAND_CHANNEL = new SystemMessageId(1594);
		S1_SUCCEEDED = new SystemMessageId(1595);
		HIT_BY_S1 = new SystemMessageId(1596);
		S1_FAILED = new SystemMessageId(1597);
		SOULSHOTS_AND_SPIRITSHOTS_ARE_NOT_AVAILABLE_FOR_A_DEAD_PET = new SystemMessageId(1598);
		CANNOT_OBSERVE_IN_COMBAT = new SystemMessageId(1599);
		TOMORROW_ITEM_ZERO_CONFIRM = new SystemMessageId(1600);
		TOMORROW_ITEM_SAME_CONFIRM = new SystemMessageId(1601);
		COMMAND_CHANNEL_ONLY_FOR_PARTY_LEADER = new SystemMessageId(1602);
		ONLY_COMMANDER_GIVE_COMMAND = new SystemMessageId(1603);
		CANNOT_USE_ITEMS_SKILLS_WITH_FORMALWEAR = new SystemMessageId(1604);
		HERE_YOU_CAN_BUY_ONLY_SEEDS_OF_S1_MANOR = new SystemMessageId(1605);
		THIRD_CLASS_TRANSFER = new SystemMessageId(1606);
		S1_ADENA_HAS_BEEN_WITHDRAWN_TO_PAY_FOR_PURCHASING_FEES = new SystemMessageId(1607);
		INSUFFICIENT_ADENA_TO_BUY_CASTLE = new SystemMessageId(1608);
		WAR_ALREADY_DECLARED = new SystemMessageId(1609);
		CANNOT_DECLARE_AGAINST_OWN_CLAN = new SystemMessageId(1610);
		PARTY_LEADER_C1 = new SystemMessageId(1611);
		WAR_LIST = new SystemMessageId(1612);
		NO_CLAN_ON_WAR_LIST = new SystemMessageId(1613);
		JOINED_CHANNEL_ALREADY_OPEN = new SystemMessageId(1614);
		S1_PARTIES_REMAINING_UNTIL_CHANNEL = new SystemMessageId(1615);
		COMMAND_CHANNEL_ACTIVATED = new SystemMessageId(1616);
		CANT_USE_COMMAND_CHANNEL = new SystemMessageId(1617);
		FERRY_RUNE_GLUDIN_DELAYED = new SystemMessageId(1618);
		FERRY_GLUDIN_RUNE_DELAYED = new SystemMessageId(1619);
		ARRIVED_AT_RUNE = new SystemMessageId(1620);
		DEPARTURE_FOR_GLUDIN_5_MINUTES = new SystemMessageId(1621);
		DEPARTURE_FOR_GLUDIN_1_MINUTE = new SystemMessageId(1622);
		DEPARTURE_FOR_GLUDIN_SHORTLY = new SystemMessageId(1623);
		DEPARTURE_FOR_GLUDIN_NOW = new SystemMessageId(1624);
		REPARTURE_FOR_RUNE_10_MINUTES = new SystemMessageId(1625);
		DEPARTURE_FOR_RUNE_5_MINUTES = new SystemMessageId(1626);
		DEPARTURE_FOR_RUNE_1_MINUTE = new SystemMessageId(1627);
		DEPARTURE_FOR_GLUDIN_SHORTLY2 = new SystemMessageId(1628);
		DEPARTURE_FOR_RUNE_NOW = new SystemMessageId(1629);
		FERRY_FROM_RUNE_AT_GLUDIN_15_MINUTES = new SystemMessageId(1630);
		FERRY_FROM_RUNE_AT_GLUDIN_10_MINUTES = new SystemMessageId(1631);
		FERRY_FROM_RUNE_AT_GLUDIN_5_MINUTES = new SystemMessageId(1632);
		FERRY_FROM_RUNE_AT_GLUDIN_1_MINUTE = new SystemMessageId(1633);
		FERRY_FROM_GLUDIN_AT_RUNE_15_MINUTES = new SystemMessageId(1634);
		FERRY_FROM_GLUDIN_AT_RUNE_10_MINUTES = new SystemMessageId(1635);
		FERRY_FROM_GLUDIN_AT_RUNE_5_MINUTES = new SystemMessageId(1636);
		FERRY_FROM_GLUDIN_AT_RUNE_1_MINUTE = new SystemMessageId(1637);
		CANNOT_FISH_WHILE_USING_RECIPE_BOOK = new SystemMessageId(1638);
		OLYMPIAD_PERIOD_S1_HAS_STARTED = new SystemMessageId(1639);
		OLYMPIAD_PERIOD_S1_HAS_ENDED = new SystemMessageId(1640);
		THE_OLYMPIAD_GAME_HAS_STARTED = new SystemMessageId(1641);
		THE_OLYMPIAD_GAME_HAS_ENDED = new SystemMessageId(1642);
		LOC_DIMENSIONAL_GAP_S1_S2_S3 = new SystemMessageId(1643);
		PLAY_TIME_NOW_ACCUMULATING = new SystemMessageId(1649);
		TRY_LOGIN_LATER = new SystemMessageId(1650);
		THE_OLYMPIAD_GAME_IS_NOT_CURRENTLY_IN_PROGRESS = new SystemMessageId(1651);
		RECORDING_GAMEPLAY_START = new SystemMessageId(1652);
		RECORDING_GAMEPLAY_STOP_S1 = new SystemMessageId(1653);
		RECORDING_GAMEPLAY_FAILED = new SystemMessageId(1654);
		YOU_CAUGHT_SOMETHING_SMELLY_THROW_IT_BACK = new SystemMessageId(1655);
		SUCCESSFULLY_TRADED_WITH_NPC = new SystemMessageId(1656);
		C1_HAS_GAINED_S2_OLYMPIAD_POINTS = new SystemMessageId(1657);
		C1_HAS_LOST_S2_OLYMPIAD_POINTS = new SystemMessageId(1658);
		LOC_CEMETARY_OF_THE_EMPIRE_S1_S2_S3 = new SystemMessageId(1659);
		CHANNEL_CREATOR_C1 = new SystemMessageId(1660);
		C1_OBTAINED_S3_S2_S = new SystemMessageId(1661);
		FISH_NO_MORE_BITING_TRY_OTHER_LOCATION = new SystemMessageId(1662);
		CLAN_EMBLEM_WAS_SUCCESSFULLY_REGISTERED = new SystemMessageId(1663);
		FISH_RESISTING_LOOK_BOBBLER = new SystemMessageId(1664);
		YOU_WORN_FISH_OUT = new SystemMessageId(1665);
		OBTAINED_S1_S2 = new SystemMessageId(1666);
		LETHAL_STRIKE = new SystemMessageId(1667);
		LETHAL_STRIKE_SUCCESSFUL = new SystemMessageId(1668);
		NOTHING_INSIDE_THAT = new SystemMessageId(1669);
		REELING_PUMPING_3_LEVELS_HIGHER_THAN_FISHING_PENALTY = new SystemMessageId(1670);
		REELING_SUCCESSFUL_PENALTY_S1 = new SystemMessageId(1671);
		PUMPING_SUCCESSFUL_PENALTY_S1 = new SystemMessageId(1672);
		THE_CURRENT_RECORD_FOR_THIS_OLYMPIAD_SESSION_IS_S1_MATCHES_S2_WINS_S3_DEFEATS_YOU_HAVE_EARNED_S4_OLYMPIAD_POINTS = new SystemMessageId(1673);
		NOBLESSE_ONLY = new SystemMessageId(1674);
		A_MANOR_CANNOT_BE_SET_UP_BETWEEN_6_AM_AND_8_PM = new SystemMessageId(1675);
		NO_SERVITOR_CANNOT_AUTOMATE_USE = new SystemMessageId(1676);
		CANT_STOP_CLAN_WAR_WHILE_IN_COMBAT = new SystemMessageId(1677);
		NO_CLAN_WAR_AGAINST_CLAN_S1 = new SystemMessageId(1678);
		ONLY_CHANNEL_CREATOR_CAN_GLOBAL_COMMAND = new SystemMessageId(1679);
		C1_DECLINED_CHANNEL_INVITATION = new SystemMessageId(1680);
		C1_DID_NOT_RESPOND_CHANNEL_INVITATION_FAILED = new SystemMessageId(1681);
		ONLY_CHANNEL_CREATOR_CAN_DISMISS = new SystemMessageId(1682);
		ONLY_PARTY_LEADER_CAN_LEAVE_CHANNEL = new SystemMessageId(1683);
		NO_CLAN_WAR_AGAINST_DISSOLVING_CLAN = new SystemMessageId(1684);
		YOU_ARE_UNABLE_TO_EQUIP_THIS_ITEM_WHEN_YOUR_PK_COUNT_IS_GREATER_THAN_OR_EQUAL_TO_ONE = new SystemMessageId(1685);
		CASTLE_WALL_DAMAGED = new SystemMessageId(1686);
		AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_WYVERN = new SystemMessageId(1687);
		CANNOT_ENCHANT_WHILE_STORE = new SystemMessageId(1688);
		C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST = new SystemMessageId(1689);
		C1_IS_ALREADY_REGISTERED_ON_THE_NON_CLASS_LIMITED_MATCH_WAITING_LIST = new SystemMessageId(1690);
		C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_INVENTORY_SLOT_EXCEEDS_80_PERCENT = new SystemMessageId(1691);
		C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_WHILE_CHANGED_TO_SUB_CLASS = new SystemMessageId(1692);
		WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME = new SystemMessageId(1693);
		ONLY_NOBLESSE_LEADER_CAN_VIEW_SIEGE_STATUS_WINDOW = new SystemMessageId(1694);
		ONLY_DURING_SIEGE = new SystemMessageId(1695);
		ACCUMULATED_PLAY_TIME_IS_S1 = new SystemMessageId(1696);
		ACCUMULATED_PLAY_TIME_WARNING1 = new SystemMessageId(1697);
		ACCUMULATED_PLAY_TIME_WARNING2 = new SystemMessageId(1698);
		CANNOT_DISMISS_PARTY_MEMBER = new SystemMessageId(1699);
		NOT_ENOUGH_SPIRITHOTS_FOR_PET = new SystemMessageId(1700);
		NOT_ENOUGH_SOULSHOTS_FOR_PET = new SystemMessageId(1701);
		S1_USING_THIRD_PARTY_PROGRAM = new SystemMessageId(1702);
		NOT_USING_THIRD_PARTY_PROGRAM = new SystemMessageId(1703);
		CLOSE_STORE_WINDOW_AND_TRY_AGAIN = new SystemMessageId(1704);
		PCPOINT_ACQUISITION_PERIOD = new SystemMessageId(1705);
		PCPOINT_USE_PERIOD = new SystemMessageId(1706);
		ACQUIRED_S1_PCPOINT = new SystemMessageId(1707);
		ACQUIRED_S1_PCPOINT_DOUBLE = new SystemMessageId(1708);
		USING_S1_PCPOINT = new SystemMessageId(1709);
		SHORT_OF_ACCUMULATED_POINTS = new SystemMessageId(1710);
		PCPOINT_USE_PERIOD_EXPIRED = new SystemMessageId(1711);
		PCPOINT_ACCUMULATION_PERIOD_EXPIRED = new SystemMessageId(1712);
		GAMES_DELAYED = new SystemMessageId(1713);
		LOC_SCHUTTGART_S1_S2_S3 = new SystemMessageId(1714);
		PEACEFUL_ZONE = new SystemMessageId(1715);
		ALTERED_ZONE = new SystemMessageId(1716);
		SIEGE_ZONE = new SystemMessageId(1717);
		GENERAL_ZONE = new SystemMessageId(1718);
		SEVENSIGNS_ZONE = new SystemMessageId(1719);
		UNKNOWN1 = new SystemMessageId(1720);
		COMBAT_ZONE = new SystemMessageId(1721);
		ENTER_ITEM_NAME_SEARCH = new SystemMessageId(1722);
		PLEASE_PROVIDE_PETITION_FEEDBACK = new SystemMessageId(1723);
		SERVITOR_NOT_RETURN_IN_BATTLE = new SystemMessageId(1724);
		EARNED_S1_RAID_POINTS = new SystemMessageId(1725);
		S1_PERIOD_EXPIRED_DISAPPEARED = new SystemMessageId(1726);
		C1_INVITED_YOU_TO_PARTY_ROOM_CONFIRM = new SystemMessageId(1727);
		PARTY_MATCHING_REQUEST_NO_RESPONSE = new SystemMessageId(1728);
		NOT_JOIN_CHANNEL_WHILE_TELEPORTING = new SystemMessageId(1729);
		YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN_ACADEMY = new SystemMessageId(1730);
		ONLY_LEADER_CAN_CREATE_ACADEMY = new SystemMessageId(1731);
		NEED_BLOODMARK_FOR_ACADEMY = new SystemMessageId(1732);
		NEED_ADENA_FOR_ACADEMY = new SystemMessageId(1733);
		ACADEMY_REQUIREMENTS = new SystemMessageId(1734);
		S1_DOESNOT_MEET_REQUIREMENTS_TO_JOIN_ACADEMY = new SystemMessageId(1735);
		ACADEMY_MAXIMUM = new SystemMessageId(1736);
		CLAN_CAN_CREATE_ACADEMY = new SystemMessageId(1737);
		CLAN_HAS_ALREADY_ESTABLISHED_A_CLAN_ACADEMY = new SystemMessageId(1738);
		CLAN_ACADEMY_CREATE_CONFIRM = new SystemMessageId(1739);
		ACADEMY_CREATE_ENTER_NAME = new SystemMessageId(1740);
		THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED = new SystemMessageId(1741);
		ACADEMY_INVITATION_SENT_TO_S1 = new SystemMessageId(1742);
		OPEN_ACADEMY_CONDITIONS = new SystemMessageId(1743);
		ACADEMY_JOIN_NO_RESPONSE = new SystemMessageId(1744);
		ACADEMY_JOIN_DECLINE = new SystemMessageId(1745);
		ALREADY_JOINED_ACADEMY = new SystemMessageId(1746);
		JOIN_ACADEMY_REQUEST_BY_S1_FOR_CLAN_S2 = new SystemMessageId(1747);
		CLAN_MEMBER_GRADUATED_FROM_ACADEMY = new SystemMessageId(1748);
		ACADEMY_MEMBERSHIP_TERMINATED = new SystemMessageId(1749);
		C1_CANNOT_JOIN_OLYMPIAD_POSSESSING_S2 = new SystemMessageId(1750);
		GRAND_MASTER_COMMEMORATIVE_ITEM = new SystemMessageId(1751);
		MEMBER_GRADUATED_EARNED_S1_REPU = new SystemMessageId(1752);
		CANT_TRANSFER_PRIVILEGE_TO_ACADEMY_MEMBER = new SystemMessageId(1753);
		RIGHT_CANT_TRANSFERRED_TO_ACADEMY_MEMBER = new SystemMessageId(1754);
		S2_HAS_BEEN_DESIGNATED_AS_APPRENTICE_OF_CLAN_MEMBER_S1 = new SystemMessageId(1755);
		YOUR_APPRENTICE_S1_HAS_LOGGED_IN = new SystemMessageId(1756);
		YOUR_APPRENTICE_C1_HAS_LOGGED_OUT = new SystemMessageId(1757);
		YOUR_SPONSOR_C1_HAS_LOGGED_IN = new SystemMessageId(1758);
		YOUR_SPONSOR_C1_HAS_LOGGED_OUT = new SystemMessageId(1759);
		CLAN_MEMBER_C1_TITLE_CHANGED_TO_S2 = new SystemMessageId(1760);
		CLAN_MEMBER_C1_PRIVILEGE_CHANGED_TO_S2 = new SystemMessageId(1761);
		YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE = new SystemMessageId(1762);
		S2_CLAN_MEMBER_C1_APPRENTICE_HAS_BEEN_REMOVED = new SystemMessageId(1763);
		EQUIP_ONLY_FOR_ACADEMY = new SystemMessageId(1764);
		EQUIP_NOT_FOR_GRADUATES = new SystemMessageId(1765);
		CLAN_JOIN_APPLICATION_SENT_TO_C1_IN_S2 = new SystemMessageId(1766);
		ACADEMY_JOIN_APPLICATION_SENT_TO_C1 = new SystemMessageId(1767);
		JOIN_REQUEST_BY_C1_TO_CLAN_S2_ACADEMY = new SystemMessageId(1768);
		JOIN_REQUEST_BY_C1_TO_ORDER_OF_KNIGHTS_S3_UNDER_CLAN_S2 = new SystemMessageId(1769);
		CLAN_REPU_0_MAY_FACE_PENALTIES = new SystemMessageId(1770);
		CLAN_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS = new SystemMessageId(1771);
		CLAN_WAS_DEFEATED_IN_SIEGE_AND_LOST_S1_REPUTATION_POINTS = new SystemMessageId(1772);
		CLAN_VICTORIOUS_IN_SIEGE_AND_GAINED_S1_REPUTATION_POINTS = new SystemMessageId(1773);
		CLAN_ACQUIRED_CONTESTED_CLAN_HALL_AND_S1_REPUTATION_POINTS = new SystemMessageId(1774);
		CLAN_MEMBER_C1_WAS_IN_HIGHEST_RANKED_PARTY_IN_FESTIVAL_OF_DARKNESS_AND_GAINED_S2_REPUTATION = new SystemMessageId(1775);
		CLAN_MEMBER_C1_BECAME_HERO_AND_GAINED_S2_REPUTATION_POINTS = new SystemMessageId(1776);
		CLAN_QUEST_COMPLETED_AND_S1_POINTS_GAINED = new SystemMessageId(1777);
		OPPOSING_CLAN_CAPTURED_CLAN_HALL_AND_YOUR_CLAN_LOSES_S1_POINTS = new SystemMessageId(1778);
		CLAN_LOST_CONTESTED_CLAN_HALL_AND_300_POINTS = new SystemMessageId(1779);
		CLAN_CAPTURED_CONTESTED_CLAN_HALL_AND_S1_POINTS_DEDUCTED_FROM_OPPONENT = new SystemMessageId(1780);
		CLAN_ADDED_S1S_POINTS_TO_REPUTATION_SCORE = new SystemMessageId(1781);
		CLAN_MEMBER_C1_WAS_KILLED_AND_S2_POINTS_DEDUCTED_FROM_REPUTATION = new SystemMessageId(1782);
		FOR_KILLING_OPPOSING_MEMBER_S1_POINTS_WERE_DEDUCTED_FROM_OPPONENTS = new SystemMessageId(1783);
		YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST_AND_ADDED_TO_OPPONENT = new SystemMessageId(1784);
		YOUR_CLAN_HAS_BEEN_INITIALIZED_AND_S1_POINTS_LOST = new SystemMessageId(1785);
		YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST = new SystemMessageId(1786);
		S1_DEDUCTED_FROM_CLAN_REP = new SystemMessageId(1787);
		CLAN_SKILL_S1_ADDED = new SystemMessageId(1788);
		REPUTATION_POINTS_0_OR_LOWER_CLAN_SKILLS_DEACTIVATED = new SystemMessageId(1789);
		FAILED_TO_INCREASE_CLAN_LEVEL = new SystemMessageId(1790);
		YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_MILITARY_UNIT = new SystemMessageId(1791);
		ASSIGN_MANAGER_FOR_ORDER_OF_KNIGHTS = new SystemMessageId(1792);
		C1_HAS_BEEN_SELECTED_AS_CAPTAIN_OF_S2 = new SystemMessageId(1793);
		THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED = new SystemMessageId(1794);
		THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED = new SystemMessageId(1795);
		ILLEGAL_USE17 = new SystemMessageId(1796);
		C1_PROMOTED_TO_S2 = new SystemMessageId(1797);
		CLAN_LEADER_PRIVILEGES_HAVE_BEEN_TRANSFERRED_TO_C1 = new SystemMessageId(1798);
		SEARCHING_FOR_BOT_USERS_TRY_AGAIN_LATER = new SystemMessageId(1799);
		C1_HISTORY_USING_BOT = new SystemMessageId(1800);
		SELL_ATTEMPT_FAILED = new SystemMessageId(1801);
		TRADE_ATTEMPT_FAILED = new SystemMessageId(1802);
		GAME_REQUEST_CANNOT_BE_MADE = new SystemMessageId(1803);
		ILLEGAL_USE18 = new SystemMessageId(1804);
		ILLEGAL_USE19 = new SystemMessageId(1805);
		ILLEGAL_USE20 = new SystemMessageId(1806);
		ILLEGAL_USE21 = new SystemMessageId(1807);
		ILLEGAL_USE22 = new SystemMessageId(1808);
		ACCOUNT_MUST_VERIFIED = new SystemMessageId(1809);
		REFUSE_INVITATION_ACTIVATED = new SystemMessageId(1810);
		REFUSE_INVITATION_CURRENTLY_ACTIVE = new SystemMessageId(1812);
		THERE_IS_S1_HOUR_AND_S2_MINUTE_LEFT_OF_THE_FIXED_USAGE_TIME = new SystemMessageId(1813);
		S2_MINUTE_OF_USAGE_TIME_ARE_LEFT_FOR_S1 = new SystemMessageId(1814);
		S2_WAS_DROPPED_IN_THE_S1_REGION = new SystemMessageId(1815);
		THE_OWNER_OF_S2_HAS_APPEARED_IN_THE_S1_REGION = new SystemMessageId(1816);
		S2_OWNER_HAS_LOGGED_INTO_THE_S1_REGION = new SystemMessageId(1817);
		S1_HAS_DISAPPEARED = new SystemMessageId(1818);
		EVIL_FROM_S2_IN_S1 = new SystemMessageId(1819);
		S1_CURRENTLY_SLEEP = new SystemMessageId(1820);
		S2_EVIL_PRESENCE_FELT_IN_S1 = new SystemMessageId(1821);
		S1_SEALED = new SystemMessageId(1822);
		CLANHALL_WAR_REGISTRATION_PERIOD_ENDED = new SystemMessageId(1823);
		REGISTERED_FOR_CLANHALL_WAR = new SystemMessageId(1824);
		CLANHALL_WAR_REGISTRATION_FAILED = new SystemMessageId(1825);
		CLANHALL_WAR_BEGINS_IN_S1_MINUTES = new SystemMessageId(1826);
		CLANHALL_WAR_BEGINS_IN_S1_MINUTES_ENTER_NOW = new SystemMessageId(1827);
		CLANHALL_WAR_BEGINS_IN_S1_SECONDS = new SystemMessageId(1828);
		COMMAND_CHANNEL_FULL = new SystemMessageId(1829);
		C1_NOT_ALLOWED_INVITE_TO_PARTY_ROOM = new SystemMessageId(1830);
		C1_NOT_MEET_CONDITIONS_FOR_PARTY_ROOM = new SystemMessageId(1831);
		ONLY_ROOM_LEADER_CAN_INVITE = new SystemMessageId(1832);
		CONFIRM_DROP_ALL_OF_S1 = new SystemMessageId(1833);
		PARTY_ROOM_FULL = new SystemMessageId(1834);
		S1_CLAN_IS_FULL = new SystemMessageId(1835);
		CANNOT_JOIN_ACADEMY_AFTER_2ND_OCCUPATION = new SystemMessageId(1836);
		C1_SENT_INVITATION_TO_ROYAL_GUARD_S3_OF_CLAN_S2 = new SystemMessageId(1837);
		COUPON_ONCE_PER_CHARACTER = new SystemMessageId(1838);
		SERIAL_MAY_USED_ONCE = new SystemMessageId(1839);
		SERIAL_INPUT_INCORRECT = new SystemMessageId(1840);
		CLANHALL_WAR_CANCELLED = new SystemMessageId(1841);
		C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT = new SystemMessageId(1842);
		C1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED = new SystemMessageId(1843);
		C1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED = new SystemMessageId(1844);
		HERO_WEAPONS_CANT_DESTROYED = new SystemMessageId(1845);
		TOO_FAR_AWAY_FROM_FENRIR_TO_MOUNT = new SystemMessageId(1846);
		CAUGHT_FISH_S1_LENGTH = new SystemMessageId(1847);
		REGISTERED_IN_FISH_SIZE_RANKING = new SystemMessageId(1848);
		CONFIRM_DISCARD_ALL_OF_S1 = new SystemMessageId(1849);
		CAPTAIN_OF_ORDER_OF_KNIGHTS_CANNOT_BE_APPOINTED = new SystemMessageId(1850);
		CAPTAIN_OF_ROYAL_GUARD_CANNOT_BE_APPOINTED = new SystemMessageId(1851);
		ACQUIRE_SKILL_FAILED_BAD_CLAN_REP_SCORE = new SystemMessageId(1852);
		CANT_EXCHANGE_QUANTITY_ITEMS_OF_SAME_TYPE = new SystemMessageId(1853);
		ITEM_CONVERTED_SUCCESSFULLY = new SystemMessageId(1854);
		ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME = new SystemMessageId(1855);
		OPPONENT_POSSESSES_S1_OLYMPIAD_CANCELLED = new SystemMessageId(1856);
		C1_OWNS_S2_AND_CANNOT_PARTICIPATE_IN_OLYMPIAD = new SystemMessageId(1857);
		C1_CANNOT_PARTICIPATE_OLYMPIAD_WHILE_DEAD = new SystemMessageId(1858);
		EXCEEDED_QUANTITY_FOR_MOVED = new SystemMessageId(1859);
		THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW = new SystemMessageId(1860);
		CLAN_CREST_HAS_BEEN_DELETED = new SystemMessageId(1861);
		CLAN_SKILLS_WILL_BE_ACTIVATED_SINCE_REPUTATION_IS_0_OR_HIGHER = new SystemMessageId(1862);
		C1_PURCHASED_CLAN_ITEM_REDUCING_S2_REPU_POINTS = new SystemMessageId(1863);
		PET_REFUSING_ORDER = new SystemMessageId(1864);
		PET_IN_STATE_OF_DISTRESS = new SystemMessageId(1865);
		MP_REDUCED_BY_S1 = new SystemMessageId(1866);
		YOUR_OPPONENTS_MP_WAS_REDUCED_BY_S1 = new SystemMessageId(1867);
		CANNOT_EXCHANCE_USED_ITEM = new SystemMessageId(1868);
		C1_GRANTED_MASTER_PARTY_LOOTING_RIGHTS = new SystemMessageId(1869);
		COMMAND_CHANNEL_WITH_LOOTING_RIGHTS_EXISTS = new SystemMessageId(1870);
		CONFIRM_DISMISS_C1_FROM_CLAN = new SystemMessageId(1871);
		S1_HOURS_S2_MINUTES_LEFT = new SystemMessageId(1872);
		S1_HOURS_S2_MINUTES_LEFT_FOR_THIS_PCCAFE = new SystemMessageId(1873);
		S1_MINUTES_LEFT_FOR_THIS_USER = new SystemMessageId(1874);
		S1_MINUTES_LEFT_FOR_THIS_PCCAFE = new SystemMessageId(1875);
		CONFIRM_LEAVE_S1_CLAN = new SystemMessageId(1876);
		GAME_WILL_END_IN_S1_MINUTES = new SystemMessageId(1877);
		GAME_WILL_END_IN_S1_SECONDS = new SystemMessageId(1878);
		IN_S1_MINUTES_TELEPORTED_OUTSIDE_OF_GAME_ARENA = new SystemMessageId(1879);
		IN_S1_SECONDS_TELEPORTED_OUTSIDE_OF_GAME_ARENA = new SystemMessageId(1880);
		PRELIMINARY_MATCH_BEGIN_IN_S1_SECONDS = new SystemMessageId(1881);
		CHARACTERS_NOT_CREATED_FROM_THIS_SERVER = new SystemMessageId(1882);
		NO_OFFERINGS_OWN_OR_MADE_BID_FOR = new SystemMessageId(1883);
		ENTER_PCROOM_SERIAL_NUMBER = new SystemMessageId(1884);
		SERIAL_NUMBER_CANT_ENTERED = new SystemMessageId(1885);
		SERIAL_NUMBER_ALREADY_USED = new SystemMessageId(1886);
		SERIAL_NUMBER_ENTERING_FAILED = new SystemMessageId(1887);
		SERIAL_NUMBER_ENTERING_FAILED_5_TIMES = new SystemMessageId(1888);
		CONGRATULATIONS_RECEIVED_S1 = new SystemMessageId(1889);
		ALREADY_USED_COUPON_NOT_USE_SERIAL_NUMBER = new SystemMessageId(1890);
		NOT_USE_ITEMS_IN_PRIVATE_STORE = new SystemMessageId(1891);
		REPLAY_FILE_PREVIOUS_VERSION_CANT_PLAYED = new SystemMessageId(1892);
		FILE_CANT_REPLAYED = new SystemMessageId(1893);
		NOT_SUBCLASS_WHILE_OVERWEIGHT = new SystemMessageId(1894);
		C1_IN_SUMMON_BLOCKING_AREA = new SystemMessageId(1895);
		C1_ALREADY_SUMMONED = new SystemMessageId(1896);
		S1_REQUIRED_FOR_SUMMONING = new SystemMessageId(1897);
		C1_CURRENTLY_TRADING_OR_OPERATING_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED = new SystemMessageId(1898);
		YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING = new SystemMessageId(1899);
		C1_ENTERED_PARTY_ROOM = new SystemMessageId(1900);
		C1_INVITED_YOU_TO_PARTY_ROOM = new SystemMessageId(1901);
		INCOMPATIBLE_ITEM_GRADE = new SystemMessageId(1902);
		NCOTP = new SystemMessageId(1903);
		CANT_SUBCLASS_WITH_SUMMONED_SERVITOR = new SystemMessageId(1904);
		S2_OF_S1_WILL_REPLACED_WITH_S4_OF_S3 = new SystemMessageId(1905);
		SELECT_COMBAT_UNIT = new SystemMessageId(1906);
		SELECT_CHARACTER_WHO_WILL = new SystemMessageId(1907);
		C1_STATE_FORBIDS_SUMMONING = new SystemMessageId(1908);
		ACADEMY_LIST_HEADER = new SystemMessageId(1909);
		GRADUATES_C1 = new SystemMessageId(1910);
		YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_IN_OLYMPIAD = new SystemMessageId(1911);
		NCOTP2 = new SystemMessageId(1912);
		TIME_FOR_S1_IS_S2_MINUTES_REMAINING = new SystemMessageId(1913);
		TIME_FOR_S1_IS_S2_SECONDS_REMAINING = new SystemMessageId(1914);
		GAME_ENDS_IN_S1_SECONDS = new SystemMessageId(1915);
		DEATH_PENALTY_LEVEL_S1_ADDED = new SystemMessageId(1916);
		DEATH_PENALTY_LIFTED = new SystemMessageId(1917);
		PET_TOO_HIGH_TO_CONTROL = new SystemMessageId(1918);
		OLYMPIAD_REGISTRATION_PERIOD_ENDED = new SystemMessageId(1919);
		ACCOUNT_INACTIVITY = new SystemMessageId(1920);
		S2_HOURS_S3_MINUTES_SINCE_S1_KILLED = new SystemMessageId(1921);
		S1_FAILED_KILLING_EXPIRED = new SystemMessageId(1922);
		COURT_MAGICIAN_CREATED_PORTAL = new SystemMessageId(1923);
		LOC_PRIMEVAL_ISLE_S1_S2_S3 = new SystemMessageId(1924);
		SEAL_OF_STRIFE_FORBIDS_SUMMONING = new SystemMessageId(1925);
		THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL = new SystemMessageId(1926);
		C1_HAS_BEEN_CHALLENGED_TO_A_DUEL = new SystemMessageId(1927);
		C1_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL = new SystemMessageId(1928);
		C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = new SystemMessageId(1929);
		YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = new SystemMessageId(1930);
		C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL = new SystemMessageId(1931);
		C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL2 = new SystemMessageId(1932);
		YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_PARTY_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = new SystemMessageId(1933);
		S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS = new SystemMessageId(1934);
		C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL = new SystemMessageId(1935);
		THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL = new SystemMessageId(1936);
		SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY = new SystemMessageId(1937);
		C1_HAS_CHALLENGED_YOU_TO_A_DUEL = new SystemMessageId(1938);
		C1_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL = new SystemMessageId(1939);
		YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME = new SystemMessageId(1940);
		NO_PLACE_FOR_DUEL = new SystemMessageId(1941);
		THE_OPPOSING_PARTY_IS_CURRENTLY_UNABLE_TO_ACCEPT_A_CHALLENGE_TO_A_DUEL = new SystemMessageId(1942);
		THE_OPPOSING_PARTY_IS_AT_BAD_LOCATION_FOR_A_DUEL = new SystemMessageId(1943);
		IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE = new SystemMessageId(1944);
		THE_DUEL_WILL_BEGIN_IN_S1_SECONDS = new SystemMessageId(1945);
		C1_CHALLENGED_YOU_TO_A_DUEL = new SystemMessageId(1946);
		C1_CHALLENGED_YOU_TO_A_PARTY_DUEL = new SystemMessageId(1947);
		THE_DUEL_WILL_BEGIN_IN_S1_SECONDS2 = new SystemMessageId(1948);
		LET_THE_DUEL_BEGIN = new SystemMessageId(1949);
		C1_HAS_WON_THE_DUEL = new SystemMessageId(1950);
		C1_PARTY_HAS_WON_THE_DUEL = new SystemMessageId(1951);
		THE_DUEL_HAS_ENDED_IN_A_TIE = new SystemMessageId(1952);
		SINCE_C1_WAS_DISQUALIFIED_S2_HAS_WON = new SystemMessageId(1953);
		SINCE_C1_PARTY_WAS_DISQUALIFIED_S2_PARTY_HAS_WON = new SystemMessageId(1954);
		SINCE_C1_WITHDREW_FROM_THE_DUEL_S2_HAS_WON = new SystemMessageId(1955);
		SINCE_C1_PARTY_WITHDREW_FROM_THE_DUEL_S2_PARTY_HAS_WON = new SystemMessageId(1956);
		SELECT_THE_ITEM_TO_BE_AUGMENTED = new SystemMessageId(1957);
		SELECT_THE_CATALYST_FOR_AUGMENTATION = new SystemMessageId(1958);
		REQUIRES_S1_S2 = new SystemMessageId(1959);
		THIS_IS_NOT_A_SUITABLE_ITEM = new SystemMessageId(1960);
		GEMSTONE_QUANTITY_IS_INCORRECT = new SystemMessageId(1961);
		THE_ITEM_WAS_SUCCESSFULLY_AUGMENTED = new SystemMessageId(1962);
		SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION = new SystemMessageId(1963);
		AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM = new SystemMessageId(1964);
		AUGMENTATION_HAS_BEEN_SUCCESSFULLY_REMOVED_FROM_YOUR_S1 = new SystemMessageId(1965);
		ONLY_CLAN_LEADER_CAN_ISSUE_COMMANDS = new SystemMessageId(1966);
		GATE_LOCKED_TRY_AGAIN_LATER = new SystemMessageId(1967);
		S1_OWNER = new SystemMessageId(1968);
		AREA_S1_APPEARS = new SystemMessageId(1969);
		ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN = new SystemMessageId(1970);
		HARDENER_LEVEL_TOO_HIGH = new SystemMessageId(1971);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_IS_IN_OPERATION = new SystemMessageId(1972);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_FROZEN = new SystemMessageId(1973);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_DEAD = new SystemMessageId(1974);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_TRADING = new SystemMessageId(1975);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_PARALYZED = new SystemMessageId(1976);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_FISHING = new SystemMessageId(1977);
		YOU_CANNOT_AUGMENT_ITEMS_WHILE_SITTING_DOWN = new SystemMessageId(1978);
		S1S_REMAINING_MANA_IS_NOW_10 = new SystemMessageId(1979);
		S1S_REMAINING_MANA_IS_NOW_5 = new SystemMessageId(1980);
		S1S_REMAINING_MANA_IS_NOW_1 = new SystemMessageId(1981);
		S1S_REMAINING_MANA_IS_NOW_0 = new SystemMessageId(1982);
		PRESS_THE_AUGMENT_BUTTON_TO_BEGIN = new SystemMessageId(1984);
		S1_DROP_AREA_S2 = new SystemMessageId(1985);
		S1_OWNER_S2 = new SystemMessageId(1986);
		S1 = new SystemMessageId(1987);
		FERRY_ARRIVED_AT_PRIMEVAL = new SystemMessageId(1988);
		FERRY_LEAVING_FOR_RUNE_3_MINUTES = new SystemMessageId(1989);
		FERRY_LEAVING_PRIMEVAL_FOR_RUNE_NOW = new SystemMessageId(1990);
		FERRY_LEAVING_FOR_PRIMEVAL_3_MINUTES = new SystemMessageId(1991);
		FERRY_LEAVING_RUNE_FOR_PRIMEVAL_NOW = new SystemMessageId(1992);
		FERRY_FROM_PRIMEVAL_TO_RUNE_DELAYED = new SystemMessageId(1993);
		FERRY_FROM_RUNE_TO_PRIMEVAL_DELAYED = new SystemMessageId(1994);
		S1_CHANNEL_FILTER_OPTION = new SystemMessageId(1995);
		ATTACK_WAS_BLOCKED = new SystemMessageId(1996);
		C1_PERFORMING_COUNTERATTACK = new SystemMessageId(1997);
		COUNTERED_C1_ATTACK = new SystemMessageId(1998);
		C1_DODGES_ATTACK = new SystemMessageId(1999);
		AVOIDED_C1_ATTACK2 = new SystemMessageId(2000);
		AUGMENTATION_FAILED_DUE_TO_INAPPROPRIATE_CONDITIONS = new SystemMessageId(2001);
		TRAP_FAILED = new SystemMessageId(2002);
		OBTAINED_ORDINARY_MATERIAL = new SystemMessageId(2003);
		OBTAINED_RATE_MATERIAL = new SystemMessageId(2004);
		OBTAINED_UNIQUE_MATERIAL = new SystemMessageId(2005);
		OBTAINED_ONLY_MATERIAL = new SystemMessageId(2006);
		ENTER_RECIPIENTS_NAME = new SystemMessageId(2007);
		ENTER_TEXT = new SystemMessageId(2008);
		CANT_EXCEED_1500_CHARACTERS = new SystemMessageId(2009);
		S2_S1 = new SystemMessageId(2010);
		AUGMENTED_ITEM_CANNOT_BE_DISCARDED = new SystemMessageId(2011);
		S1_HAS_BEEN_ACTIVATED = new SystemMessageId(2012);
		YOUR_SEED_OR_REMAINING_PURCHASE_AMOUNT_IS_INADEQUATE = new SystemMessageId(2013);
		MANOR_CANT_ACCEPT_MORE_CROPS = new SystemMessageId(2014);
		SKILL_READY_TO_USE_AGAIN = new SystemMessageId(2015);
		SKILL_READY_TO_USE_AGAIN_BUT_TIME_INCREASED = new SystemMessageId(2016);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE = new SystemMessageId(2017);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING = new SystemMessageId(2018);
		C1_CANNOT_DUEL_BECAUSE_C1_HP_OR_MP_IS_BELOW_50_PERCENT = new SystemMessageId(2019);
		C1_CANNOT_MAKE_A_CHALLANGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA = new SystemMessageId(2020);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE = new SystemMessageId(2021);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL = new SystemMessageId(2022);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_STATE = new SystemMessageId(2023);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD = new SystemMessageId(2024);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR = new SystemMessageId(2025);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_SIEGE_WAR = new SystemMessageId(2026);
		C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_WYVERN_OR_STRIDER = new SystemMessageId(2027);
		C1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_C1_IS_TOO_FAR_AWAY = new SystemMessageId(2028);
		C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_DURING_TELEPORT = new SystemMessageId(2029);
		CURRENTLY_LOGGING_IN = new SystemMessageId(2030);
		PLEASE_WAIT_A_MOMENT = new SystemMessageId(2031);
		NOT_TIME_TO_PURCHASE_ITEM = new SystemMessageId(2032);
		NOT_SUBCLASS_WHILE_INVENTORY_FULL = new SystemMessageId(2033);
		ITEM_PURCHASABLE_IN_S1_HOURS_S2_MINUTES = new SystemMessageId(2034);
		ITEM_PURCHASABLE_IN_S1_MINUTES = new SystemMessageId(2035);
		NO_INVITE_PARTY_LOCKED = new SystemMessageId(2036);
		CANT_CREATE_CHARACTER_DURING_RESTRICTION = new SystemMessageId(2037);
		ACCOUNT_CANT_DROP_ITEMS = new SystemMessageId(2038);
		ACCOUNT_CANT_TRADE_ITEMS = new SystemMessageId(2039);
		CANT_TRADE_WITH_TARGET = new SystemMessageId(2040);
		CANT_OPEN_PRIVATE_STORE = new SystemMessageId(2041);
		ILLEGAL_USE23 = new SystemMessageId(2042);
		YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_QUESTITEM = new SystemMessageId(2043);
		CANT_SETUP_PRIVATE_WORKSHOP = new SystemMessageId(2044);
		CANT_USE_PRIVATE_WORKSHOP = new SystemMessageId(2045);
		CANT_USE_PRIVATE_STORES = new SystemMessageId(2046);
		CANT_USE_CLAN_WH = new SystemMessageId(2047);
		CONFLICTING_SHORTCUT = new SystemMessageId(2048);
		CONFIRM_SHORTCUT_WILL_SAVED_ON_SERVER = new SystemMessageId(2049);
		S1_TRYING_RAISE_FLAG = new SystemMessageId(2050);
		MUST_ACCEPT_AGREEMENT = new SystemMessageId(2051);
		NEED_CONSENT_TO_PLAY_THIS_ACCOUNT = new SystemMessageId(2052);
		ACCOUNT_DECLINED_AGREEMENT_OR_PENDING = new SystemMessageId(2053);
		ACCOUNT_SUSPENDED = new SystemMessageId(2054);
		ACCOUNT_SUSPENDED_FROM_ALL_SERVICES = new SystemMessageId(2055);
		ACCOUNT_CONVERTED = new SystemMessageId(2056);
		BLOCKED_C1 = new SystemMessageId(2057);
		YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN = new SystemMessageId(2058);
		AREA_UNSUITABLE_FOR_POLYMORPH = new SystemMessageId(2059);
		YOU_CANNOT_POLYMORPH_INTO_THE_DESIRED_FORM_IN_WATER = new SystemMessageId(2060);
		CANT_MORPH_DUE_TO_MORPH_PENALTY = new SystemMessageId(2061);
		YOU_CANNOT_POLYMORPH_WHEN_YOU_HAVE_SUMMONED_A_SERVITOR = new SystemMessageId(2062);
		YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_PET = new SystemMessageId(2063);
		CANT_MORPH_WHILE_UNDER_SPECIAL_SKILL_EFFECT = new SystemMessageId(2064);
		ITEM_CANNOT_BE_TAKEN_OFF = new SystemMessageId(2065);
		THAT_WEAPON_CANT_ATTACK = new SystemMessageId(2066);
		WEAPON_CAN_USE_ONLY_WEAPON_SKILL = new SystemMessageId(2067);
		YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_UNTRAIN_SKILL_ENCHANT = new SystemMessageId(2068);
		UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_DECREASED_BY_ONE = new SystemMessageId(2069);
		UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_RESETED = new SystemMessageId(2070);
		YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_CHANGE_SKILL_ENCHANT_ROUTE = new SystemMessageId(2071);
		SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WAS_DECREASED_BY_S2 = new SystemMessageId(2072);
		SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WILL_REMAIN = new SystemMessageId(2073);
		SKILL_ENCHANT_FAILED_S1_LEVEL_WILL_REMAIN = new SystemMessageId(2074);
		NO_AUCTION_PERIOD = new SystemMessageId(2075);
		BID_CANT_EXCEED_100_BILLION = new SystemMessageId(2076);
		BID_MUST_BE_HIGHER_THAN_CURRENT_BID = new SystemMessageId(2077);
		NOT_ENOUGH_ADENA_FOR_THIS_BID = new SystemMessageId(2078);
		HIGHEST_BID_BUT_RESERVE_NOT_MET = new SystemMessageId(2079);
		YOU_HAVE_BEEN_OUTBID = new SystemMessageId(2080);
		NO_FUNDS_DUE = new SystemMessageId(2081);
		EXCEEDED_MAX_ADENA_AMOUNT_IN_INVENTORY = new SystemMessageId(2082);
		AUCTION_BEGUN = new SystemMessageId(2083);
		ENEMIES_INTRUDED_FORTRESS = new SystemMessageId(2084);
		SHOUT_AND_TRADE_CHAT_CANNOT_BE_USED_WHILE_POSSESSING_CURSED_WEAPON = new SystemMessageId(2085);
		SEARCH_ON_S2_FOR_BOT_USE_COMPLETED_IN_S1_MINUTES = new SystemMessageId(2086);
		A_FORTRESS_IS_UNDER_ATTACK = new SystemMessageId(2087);
		S1_MINUTES_UNTIL_THE_FORTRESS_BATTLE_STARTS = new SystemMessageId(2088);
		S1_SECONDS_UNTIL_THE_FORTRESS_BATTLE_STARTS = new SystemMessageId(2089);
		THE_FORTRESS_BATTLE_S1_HAS_BEGUN = new SystemMessageId(2090);
		CHANGE_PASSWORT_FIRST = new SystemMessageId(2091);
		CANNOT_BID_DUE_TO_PASSED_IN_PRICE = new SystemMessageId(2092);
		PASSED_IN_PRICE_IS_S1_ADENA_WOULD_YOU_LIKE_TO_RETURN_IT = new SystemMessageId(2093);
		ANOTHER_USER_PURCHASING_TRY_AGAIN_LATER = new SystemMessageId(2094);
		ACCOUNT_CANNOT_SHOUT = new SystemMessageId(2095);
		C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED = new SystemMessageId(2096);
		C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT = new SystemMessageId(2097);
		C1_QUEST_REQUIREMENT_NOT_SUFFICIENT = new SystemMessageId(2098);
		C1_ITEM_REQUIREMENT_NOT_SUFFICIENT = new SystemMessageId(2099);
		C1_MAY_NOT_REENTER_YET = new SystemMessageId(2100);
		NOT_IN_PARTY_CANT_ENTER = new SystemMessageId(2101);
		PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER = new SystemMessageId(2102);
		NOT_IN_COMMAND_CHANNEL_CANT_ENTER = new SystemMessageId(2103);
		MAXIMUM_INSTANCE_ZONE_NUMBER_EXCEEDED_CANT_ENTER = new SystemMessageId(2104);
		ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER = new SystemMessageId(2105);
		DUNGEON_EXPIRES_IN_S1_MINUTES = new SystemMessageId(2106);
		INSTANCE_ZONE_TERMINATES_IN_S1_MINUTES = new SystemMessageId(2107);
		ILLEGAL_USE24 = new SystemMessageId(2108);
		CHARACTER_NAME_OVERLAPPING_RENAME_CHARACTER = new SystemMessageId(2109);
		CHARACTER_NAME_INVALID_RENAME_CHARACTER = new SystemMessageId(2110);
		ENTER_SHORTCUT_TO_ASSIGN = new SystemMessageId(2111);
		SUBKEY_EXPLANATION1 = new SystemMessageId(2112);
		SUBKEY_EXPLANATION2 = new SystemMessageId(2113);
		SUBKEY_EXPLANATION3 = new SystemMessageId(2114);
		ILLEGAL_USE25 = new SystemMessageId(2115);
		ILLEGAL_USE26 = new SystemMessageId(2116);
		ILLEGAL_USE27 = new SystemMessageId(2117);
		ILLEGAL_USE28 = new SystemMessageId(2118);
		ILLEGAL_USE29 = new SystemMessageId(2119);
		ILLEGAL_USE30 = new SystemMessageId(2120);
		ILLEGAL_USE31 = new SystemMessageId(2121);
		ILLEGAL_USE32 = new SystemMessageId(2122);
		ILLEGAL_USE33 = new SystemMessageId(2123);
		CLAN_NAME_OVERLAPPING_RENAME_CLAN = new SystemMessageId(2124);
		CLAN_NAME_INVALID_RENAME_CLAN = new SystemMessageId(2125);
		ILLEGAL_USE34 = new SystemMessageId(2126);
		ILLEGAL_USE35 = new SystemMessageId(2127);
		ILLEGAL_USE36 = new SystemMessageId(2128);
		AUGMENTED_ITEM_CANT_CONVERTED = new SystemMessageId(2129);
		CANT_CONVERT_THIS_ITEM = new SystemMessageId(2130);
		WON_BID_ITEM_CAN_BE_FOUND_IN_WAREHOUSE = new SystemMessageId(2131);
		ENTERED_COMMON_SERVER = new SystemMessageId(2132);
		ENTERED_ADULTS_ONLY_SERVER = new SystemMessageId(2133);
		ENTERED_JUVENILES_SERVER = new SystemMessageId(2134);
		NOT_ALLOWED_DUE_TO_FATIGUE_LEVEL = new SystemMessageId(2135);
		CLAN_NAME_CHANCE_PETITION_SUBMITTED = new SystemMessageId(2136);
		CONFIRM_BID_S2_ADENA_FOR_S1_ITEM = new SystemMessageId(2137);
		ENTER_BID_PRICE = new SystemMessageId(2138);
		C1_PET = new SystemMessageId(2139);
		C1_SERVITOR = new SystemMessageId(2140);
		SLIGHTLY_RESISTED_C1_MAGICC = new SystemMessageId(2141);
		CANT_EXPEL_C1_NOT_A_PARTY_MEMBER = new SystemMessageId(2142);
		CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP = new SystemMessageId(2143);
		SELECT_ITEM_TO_ADD_ELEMENTAL_POWER = new SystemMessageId(2144);
		ELEMENTAL_ENHANCE_CANCELED = new SystemMessageId(2145);
		ELEMENTAL_ENHANCE_REQUIREMENT_NOT_SUFFICIENT = new SystemMessageId(2146);
		ELEMENTAL_POWER_S2_SUCCESSFULLY_ADDED_TO_S1 = new SystemMessageId(2147);
		ELEMENTAL_POWER_S3_SUCCESSFULLY_ADDED_TO_S1_S2 = new SystemMessageId(2148);
		FAILED_ADDING_ELEMENTAL_POWER = new SystemMessageId(2149);
		ANOTHER_ELEMENTAL_POWER_ALREADY_ADDED = new SystemMessageId(2150);
		OPPONENT_HAS_RESISTANCE_MAGIC_DAMAGE_DECREASED = new SystemMessageId(2151);
		CONFIRM_SHORCUT_DELETE = new SystemMessageId(2152);
		MAXIMUM_ACCOUNT_LOGINS_REACHED = new SystemMessageId(2153);
		THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED = new SystemMessageId(2154);
		A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED = new SystemMessageId(2155);
		THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL = new SystemMessageId(2156);
		BID_WILL_BE_ATTEMPTED_WITH_S1_ADENA = new SystemMessageId(2157);
		FORCED_ATTACK_IS_IMPOSSIBLE_AGAINST_SIEGE_SIDE_TEMPORARY_ALLIED_MEMBERS = new SystemMessageId(2158);
		BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_5_MINUTES = new SystemMessageId(2159);
		BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_3_MINUTES = new SystemMessageId(2160);
		NOT_ENOUGH_SPACE_FOR_SKILL = new SystemMessageId(2161);
		YOUR_SOUL_HAS_INCREASED_BY_S1_SO_IT_IS_NOW_AT_S2 = new SystemMessageId(2162);
		SOUL_CANNOT_BE_INCREASED_ANYMORE = new SystemMessageId(2163);
		SEIZED_BARRACKS = new SystemMessageId(2164);
		BARRACKS_FUNCTION_RESTORED = new SystemMessageId(2165);
		ALL_BARRACKS_OCCUPIED = new SystemMessageId(2166);
		A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_PEACE_ZONE = new SystemMessageId(2167);
		C1_ACQUIRED_THE_FLAG = new SystemMessageId(2168);
		REGISTERED_TO_S1_FORTRESS_BATTLE = new SystemMessageId(2169);
		CANT_USE_BAD_MAGIC_WHEN_OPPONENT_IN_PEACE_ZONE = new SystemMessageId(2170);
		ITEM_CANNOT_CRYSTALLIZED = new SystemMessageId(2171);
		S1_S2_AUCTION_ENDED = new SystemMessageId(2172);
		S1_AUCTION_ENDED = new SystemMessageId(2173);
		C1_CANNOT_DUEL_WHILE_POLYMORPHED = new SystemMessageId(2174);
		CANNOT_PARTY_DUEL_WHILE_A_MEMBER_IS_POLYMORPHED = new SystemMessageId(2175);
		S1_ELEMENTAL_POWER_REMOVED = new SystemMessageId(2176);
		S1_S2_ELEMENTAL_POWER_REMOVED = new SystemMessageId(2177);
		FAILED_TO_REMOVE_ELEMENTAL_POWER = new SystemMessageId(2178);
		HIGHEST_BID_FOR_GIRAN_CASTLE = new SystemMessageId(2179);
		HIGHEST_BID_FOR_ADEN_CASTLE = new SystemMessageId(2180);
		HIGHEST_BID_FOR_RUNE_CASTLE = new SystemMessageId(2181);
		CANT_POLYMORPH_ON_BOAT = new SystemMessageId(2182);
		THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED = new SystemMessageId(2183);
		S1_CLAN_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2 = new SystemMessageId(2184);
		ONLY_PARTY_LEADER_CAN_ENTER = new SystemMessageId(2185);
		SOUL_CANNOT_BE_ABSORBED_ANYMORE = new SystemMessageId(2186);
		CANT_REACH_TARGET_TO_CHARGE = new SystemMessageId(2187);
		ENCHANTMENT_ALREADY_IN_PROGRESS = new SystemMessageId(2188);
		LOC_KAMAEL_VILLAGE_S1_S2_S3 = new SystemMessageId(2189);
		LOC_WASTELANDS_CAMP_S1_S2_S3 = new SystemMessageId(2190);
		CONFIRM_APPLY_SELECTIONS = new SystemMessageId(2191);
		BID_ON_ITEM_AUCTION = new SystemMessageId(2192);
		TOO_FAR_FROM_NPC = new SystemMessageId(2193);
		CANT_APPLY_CURRENT_POLYMORPH_WITH_CORRESPONDING_EFFECTS = new SystemMessageId(2194);
		THERE_IS_NOT_ENOUGH_SOUL = new SystemMessageId(2195);
		NO_OWNED_CLAN = new SystemMessageId(2196);
		OWNED_S1_CLAN = new SystemMessageId(2197);
		HIGHEST_BID_IN_ITEM_AUCTION = new SystemMessageId(2198);
		CANT_ENTER_INSTANCE_ZONE_NPC_SERVER_OFFLINE = new SystemMessageId(2199);
		INSTANCE_ZONE_TERMINATED_NPC_SERVER_OFFLINE = new SystemMessageId(2200);
		S1_YEARS_S2_MONTHS_S3_DAYS = new SystemMessageId(2201);
		S1_HOURS_S2_MINUTES_S3_SECONDS = new SystemMessageId(2202);
		S1_MONTHS_S2_DAYS = new SystemMessageId(2203);
		S1_HOURS = new SystemMessageId(2204);
		AREA_FORBIDS_MINIMAP = new SystemMessageId(2205);
		AREA_ALLOWS_MINIMAP = new SystemMessageId(2206);
		CANT_OPEN_MINIMAP = new SystemMessageId(2207);
		YOU_DONT_MEET_SKILL_LEVEL_REQUIREMENTS = new SystemMessageId(2208);
		AREA_WHERE_RADAR_CANNOT_BE_USED = new SystemMessageId(2209);
		RETURN_TO_UNENCHANTED_CONDITION = new SystemMessageId(2210);
		NOT_ACQUIRED_DEED_SKILL_CANNOT_ACQUIRE_SKILLS = new SystemMessageId(2211);
		NOT_COMPLETED_QUEST_FOR_SKILL_ACQUISITION = new SystemMessageId(2212);
		CANT_BOARD_SHIP_POLYMORPHED = new SystemMessageId(2213);
		CONFIRM_CHARACTER_CREATION = new SystemMessageId(2214);
		S1_PDEF = new SystemMessageId(2215);
		PLEASE_UPDATE_CPU_DRIVER = new SystemMessageId(2216);
		BALLISTA_DESTROYED_CLAN_REPU_INCREASED = new SystemMessageId(2217);
		MAIN_CLASS_SKILL_ONLY = new SystemMessageId(2218);
		LOWER_CLAN_SKILL_ALREADY_ACQUIRED = new SystemMessageId(2219);
		PREVIOUS_LEVEL_SKILL_NOT_LEARNED = new SystemMessageId(2220);
		ACTIVATE_SELECTED_FUNTIONS_CONFIRM = new SystemMessageId(2221);
		SCOUT_COSTS_150000_ADENA = new SystemMessageId(2222);
		FORTRESS_GATE_COSTS_200000_ADENA = new SystemMessageId(2223);
		CROSSBOW_PREPARING_TO_FIRE = new SystemMessageId(2224);
		NO_SKILLS_TO_LEARN_RETURN_AFTER_S1_CLASS_CHANGE = new SystemMessageId(2225);
		NOT_ENOUGH_BOLTS = new SystemMessageId(2226);
		NOT_POSSIBLE_TO_REGISTER_TO_CASTLE_SIEGE = new SystemMessageId(2227);
		INSTANCE_ZONE_TIME_LIMIT = new SystemMessageId(2228);
		NO_INSTANCEZONE_TIME_LIMIT = new SystemMessageId(2229);
		AVAILABLE_AFTER_S1_S2_HOURS_S3_MINUTES = new SystemMessageId(2230);
		REPUTATION_SCORE_FOR_CONTRACT_NOT_ENOUGH = new SystemMessageId(2231);
		S1_CRYSTALLIZED_BEFORE_DESTRUCTION = new SystemMessageId(2232);
		CANT_REGISTER_TO_SIEGE_DUE_TO_CONTRACT = new SystemMessageId(2233);
		CONFIRM_KAMAEL_HERO_WEAPON = new SystemMessageId(2234);
		INSTANCE_ZONE_DELETED_CANT_ACCESSED = new SystemMessageId(2235);
		S1_MINUTES_LEFT_ON_WYVERN = new SystemMessageId(2236);
		S1_SECONDS_LEFT_ON_WYVERN = new SystemMessageId(2237);
		PARTICIPATING_IN_SIEGE_OF_S1 = new SystemMessageId(2238);
		SIEGE_OF_S1_FINIHSED = new SystemMessageId(2239);
		CANT_REGISTER_TO_TEAM_BATTLE_CLAN_HALL_WAR_WHILE_LORD_ON_TRANSACTION_WAITING_LIST = new SystemMessageId(2240);
		CANT_APPLY_ON_LORD_TRANSACTION_WHILE_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR = new SystemMessageId(2241);
		MEMBERS_CANT_LEAVE_WHEN_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR = new SystemMessageId(2242);
		WHEN_BANDITSTRONGHOLD_WILDBEASTRESERVRE_CLANLORD_IN_DANGER_PREVIOUS_LORD_PARTICIPATES_IN_BATTLE = new SystemMessageId(2243);
		S1_MINUTES_REMAINING = new SystemMessageId(2244);
		S1_SECONDS_REMAINING = new SystemMessageId(2245);
		CONTEST_BEGIN_IN_S1_MINUTES = new SystemMessageId(2246);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED = new SystemMessageId(2247);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED = new SystemMessageId(2248);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD = new SystemMessageId(2249);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING = new SystemMessageId(2250);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE = new SystemMessageId(2251);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL = new SystemMessageId(2252);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING = new SystemMessageId(2253);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_CASTING = new SystemMessageId(2254);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_CURSED_WEAPON_IS_EQUIPPED = new SystemMessageId(2255);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG = new SystemMessageId(2256);
		YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED = new SystemMessageId(2257);
		YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP = new SystemMessageId(2258);
		LOC_FANTASY_ISLAND_S1_S2_S3 = new SystemMessageId(2259);
		PET_CAN_RUN_AWAY_WHEN_HUNGER_BELOW_10_PERCENT = new SystemMessageId(2260);
		C1_GAVE_C2_DAMAGE_OF_S3 = new SystemMessageId(2261);
		C1_RECEIVED_DAMAGE_OF_S3_FROM_C2 = new SystemMessageId(2262);
		C1_RECEIVED_DAMAGE_OF_S3_THROUGH_C2 = new SystemMessageId(2263);
		C1_EVADED_C2_ATTACK = new SystemMessageId(2264);
		C1_ATTACK_WENT_ASTRAY = new SystemMessageId(2265);
		C1_HAD_CRITICAL_HIT = new SystemMessageId(2266);
		C1_RESISTED_C2_DRAIN = new SystemMessageId(2267);
		C1_ATTACK_FAILED = new SystemMessageId(2268);
		C1_RESISTED_C2_DRAIN2 = new SystemMessageId(2269);
		C1_RECEIVED_DAMAGE_FROM_S2_THROUGH_FIRE_OF_MAGIC = new SystemMessageId(2270);
		C1_WEAKLY_RESISTED_C2_MAGIC = new SystemMessageId(2271);
		USE_SHORTCUT_CONFIRM = new SystemMessageId(2272);
		SKILL_NOT_FOR_SUBCLASS = new SystemMessageId(2273);
		NPCS_RECAPTURED_FORTRESS = new SystemMessageId(2276);
		LOC_STEEL_CITADEL_S1_S2_S3 = new SystemMessageId(2293);
		GAINED_VITALITY_POINTS = new SystemMessageId(2296);
		LOC_STEEL_CITADEL_RESISTANCE = new SystemMessageId(2301);
		YOUR_VITAMIN_ITEM_HAS_ARRIVED = new SystemMessageId(2302);
		S2_SECONDS_REMAINING_FOR_REUSE_S1 = new SystemMessageId(2303);
		S2_MINUTES_S3_SECONDS_REMAINING_FOR_REUSE_S1 = new SystemMessageId(2304);
		S2_HOURS_S3_MINUTES_S4_SECONDS_REMAINING_FOR_REUSE_S1 = new SystemMessageId(2305);
		RESURRECT_USING_CHARM_OF_COURAGE = new SystemMessageId(2306);
		VITALITY_IS_AT_MAXIMUM = new SystemMessageId(2314);
		VITALITY_HAS_INCREASED = new SystemMessageId(2315);
		VITALITY_HAS_DECREASED = new SystemMessageId(2316);
		VITALITY_IS_EXHAUSTED = new SystemMessageId(2317);
		ACQUIRED_S1_REPUTATION_SCORE = new SystemMessageId(2319);
		LOC_KAMALOKA = new SystemMessageId(2321);
		LOC_NIA_KAMALOKA = new SystemMessageId(2322);
		LOC_RIM_KAMALOKA = new SystemMessageId(2323);
		ACQUIRED_50_CLAN_FAME_POINTS = new SystemMessageId(2326);
		NOT_ENOUGH_FAME_POINTS = new SystemMessageId(2327);
		YOU_CANNOT_RECEIVE_THE_VITAMIN_ITEM = new SystemMessageId(2333);
		THERE_ARE_NO_MORE_VITAMIN_ITEMS_TO_BE_FOUND = new SystemMessageId(2335);
		CP_SIPHON = new SystemMessageId(2336);
		CP_DISAPPEARS_WHEN_HIT_WITH_A_HALF_KILL_SKILL = new SystemMessageId(2337);
		YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE = new SystemMessageId(2348);
		YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING = new SystemMessageId(2349);
		YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL = new SystemMessageId(2350);
		YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING = new SystemMessageId(2351);
		YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH = new SystemMessageId(2352);
		YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_PARALYZED = new SystemMessageId(2353);
		YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD = new SystemMessageId(2354);
		YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA = new SystemMessageId(2355);
		YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER = new SystemMessageId(2356);
		YOU_CANNOT_USE_MY_TELEPORTS_IN_AN_INSTANT_ZONE = new SystemMessageId(2357);
		YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION = new SystemMessageId(2358);
		YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM = new SystemMessageId(2359);
		TIME_LIMITED_ITEM_DELETED = new SystemMessageId(2366);
		THERE_NOT_MUCH_TIME_REMAINING_UNTIL_HELPER_LEAVES = new SystemMessageId(2372);
		THE_HELPER_PET_LEAVING = new SystemMessageId(2373);
		THE_HELPER_PET_CANNOT_BE_RETURNED = new SystemMessageId(2375);
		YOU_CANNOT_RECEIVE_A_VITAMIN_ITEM_DURING_AN_EXCHANGE = new SystemMessageId(2376);
		YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT = new SystemMessageId(2390);
		PET_SKILL_CANNOT_BE_USED_RECHARCHING = new SystemMessageId(2396);
		YOU_HAVE_NO_OPEN_MY_TELEPORTS_SLOTS = new SystemMessageId(2398);
		C1_IS_ALREADY_REGISTERED_NON_CLASS_LIMITED_EVENT_TEAMS = new SystemMessageId(2440);
		ONLY_PARTY_LEADER_CAN_REQUEST_TEAM_MATCH = new SystemMessageId(2441);
		PARTY_REQUIREMENTS_NOT_MET = new SystemMessageId(2442);
		THE_DISGUISE_SCROLL_MEANT_FOR_DIFFERENT_TERRITORY = new SystemMessageId(2936);
		TERRITORY_OWNING_CLAN_CANNOT_USE_DISGUISE_SCROLL = new SystemMessageId(2937);
		TERRITORY_WAR_SCROLL_CAN_NOT_USED_NOW = new SystemMessageId(2955);
		INSTANT_ZONE_CURRENTLY_INUSE_S1 = new SystemMessageId(2400);
		THE_TERRITORY_WAR_REGISTERING_PERIOD_ENDED = new SystemMessageId(2402);
		TERRITORY_WAR_BEGINS_IN_10_MINUTES = new SystemMessageId(2403);
		TERRITORY_WAR_BEGINS_IN_5_MINUTES = new SystemMessageId(2404);
		TERRITORY_WAR_BEGINS_IN_1_MINUTE = new SystemMessageId(2405);
		YOU_HAVE_REGISTERED_IN_A_WAITING_LIST_OF_TEAM_GAMES = new SystemMessageId(2408);
		THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED = new SystemMessageId(2409);
		YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA = new SystemMessageId(2410);
		THE_COLLECTION_HAS_FAILED = new SystemMessageId(2424);
		YOUR_BIRTHDAY_GIFT_HAS_ARRIVED = new SystemMessageId(2448);
		THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY = new SystemMessageId(2449);
		C1_BIRTHDAY_IS_S3_S4_S2 = new SystemMessageId(2450);
		CLOAK_REMOVED_BECAUSE_ARMOR_SET_REMOVED = new SystemMessageId(2451);
		THE_AIRSHIP_MUST_BE_SUMMONED_TO_BOARD = new SystemMessageId(2455);
		THE_AIRSHIP_NEED_CLANLVL_5_TO_SUMMON = new SystemMessageId(2456);
		THE_AIRSHIP_NEED_LICENSE_TO_SUMMON = new SystemMessageId(2457);
		THE_AIRSHIP_ALREADY_USED = new SystemMessageId(2458);
		THE_AIRSHIP_SUMMON_LICENSE_ALREADY_ACQUIRED = new SystemMessageId(2459);
		THE_AIRSHIP_IS_ALREADY_EXISTS = new SystemMessageId(2460);
		THE_AIRSHIP_NO_PRIVILEGES = new SystemMessageId(2461);
		THE_AIRSHIP_NEED_MORE_S1 = new SystemMessageId(2462);
		THE_AIRSHIP_FUEL_SOON_RUN_OUT = new SystemMessageId(2463);
		THE_AIRSHIP_FUEL_RUN_OUT = new SystemMessageId(2464);
		OLYMPIAD_3VS3_CONFIRM = new SystemMessageId(2465);
		THE_AIRSHIP_CANNOT_TELEPORT = new SystemMessageId(2491);
		THE_AIRSHIP_SUMMONED = new SystemMessageId(2492);
		THE_COLLECTION_HAS_SUCCEEDED = new SystemMessageId(2500);
		MATCH_BEING_PREPARED_TRY_LATER = new SystemMessageId(2701);
		EXCLUDED_FROM_MATCH_DUE_INCORRECT_COUNT = new SystemMessageId(2702);
		TEAM_ADJUSTED_BECAUSE_WRONG_POPULATION_RATIO = new SystemMessageId(2703);
		CANNOT_REGISTER_CAUSE_QUEUE_FULL = new SystemMessageId(2704);
		MATCH_WAITING_TIME_EXTENDED = new SystemMessageId(2705);
		CANNOT_ENTER_CAUSE_DONT_MATCH_REQUIREMENTS = new SystemMessageId(2706);
		CANNOT_REQUEST_REGISTRATION_10_SECS_AFTER = new SystemMessageId(2707);
		CANNOT_REGISTER_PROCESSING_CURSED_WEAPON = new SystemMessageId(2708);
		COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE = new SystemMessageId(2709);
		LOC_KEUCEREUS_S1_S2_S3 = new SystemMessageId(2710);
		LOC_IN_SEED_INFINITY_S1_S2_S3 = new SystemMessageId(2711);
		LOC_OUT_SEED_INFINITY_S1_S2_S3 = new SystemMessageId(2712);
		LOC_CLEFT_S1_S2_S3 = new SystemMessageId(2716);
		INSTANT_ZONE_S1_RESTRICTED = new SystemMessageId(2720);
		BOARD_OR_CANCEL_NOT_POSSIBLE_HERE = new SystemMessageId(2721);
		ANOTHER_AIRSHIP_ALREADY_SUMMONED = new SystemMessageId(2722);
		YOU_CANNOT_BOARD_NOT_MEET_REQUEIREMENTS = new SystemMessageId(2727);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_TRANSFORMED = new SystemMessageId(2729);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_YOU_ARE_PETRIFIED = new SystemMessageId(2730);
		YOU_CANNOT_CONTROL_THE_HELM_WHEN_YOU_ARE_DEAD = new SystemMessageId(2731);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_FISHING = new SystemMessageId(2732);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_BATTLE = new SystemMessageId(2733);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_DUEL = new SystemMessageId(2734);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_SITTING_POSITION = new SystemMessageId(2735);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_USING_A_SKILL = new SystemMessageId(2736);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_A_CURSED_WEAPON_IS_EQUIPPED = new SystemMessageId(2737);
		YOU_CANNOT_CONTROL_THE_HELM_WHILE_HOLDING_A_FLAG = new SystemMessageId(2738);
		THE_S1_WARD_HAS_BEEN_DESTROYED = new SystemMessageId(2750);
		THE_CHAR_THAT_ACQUIRED_S1_WARD_HAS_BEEN_KILLED = new SystemMessageId(2751);
		CANT_CONTROL_TOO_FAR = new SystemMessageId(2762);
		SEED_OF_INFINITY_STAGE_1_ATTACK_IN_PROGRESS = new SystemMessageId(2766);
		SEED_OF_INFINITY_STAGE_2_ATTACK_IN_PROGRESS = new SystemMessageId(2767);
		SEED_OF_INFINITY_CONQUEST_COMPLETE = new SystemMessageId(2768);
		SEED_OF_INFINITY_STAGE_1_DEFENSE_IN_PROGRESS = new SystemMessageId(2769);
		SEED_OF_INFINITY_STAGE_2_DEFENSE_IN_PROGRESS = new SystemMessageId(2770);
		SEED_OF_DESTRUCTION_ATTACK_IN_PROGRESS = new SystemMessageId(2771);
		SEED_OF_DESTRUCTION_CONQUEST_COMPLETE = new SystemMessageId(2772);
		SEED_OF_DESTRUCTION_DEFENSE_IN_PROGRESS = new SystemMessageId(2773);
		THE_AIRSHIP_SUMMON_LICENSE_ENTERED = new SystemMessageId(2777);
		YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD = new SystemMessageId(2778);
		YOU_ALREADY_REQUESTED_TW_REGISTRATION = new SystemMessageId(2795);
		THE_TERRITORY_OWNER_CLAN_CANNOT_PARTICIPATE_AS_MERCENARIES = new SystemMessageId(2796);
		NOT_TERRITORY_REGISTRATION_PERIOD = new SystemMessageId(2797);
		THE_TERRITORY_WAR_WILL_END_IN_S1_HOURS = new SystemMessageId(2798);
		THE_TERRITORY_WAR_WILL_END_IN_S1_MINUTES = new SystemMessageId(2799);
		S1_SECONDS_TO_THE_END_OF_TERRITORY_WAR = new SystemMessageId(2900);
		YOU_CANNOT_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY = new SystemMessageId(2901);
		YOU_VE_ACQUIRED_THE_WARD = new SystemMessageId(2902);
		TERRITORY_WAR_HAS_BEGUN = new SystemMessageId(2903);
		TERRITORY_WAR_HAS_ENDED = new SystemMessageId(2904);
		YOU_REQUESTED_C1_TO_BE_FRIEND = new SystemMessageId(2911);
		CLAN_S1_HAS_SUCCEDED_IN_CAPTURING_S2_TERRITORY_WARD = new SystemMessageId(2913);
		TERRITORY_WAR_BEGINS_IN_20_MINUTES = new SystemMessageId(2914);
		BLOCK_CHECKER_ENDS_5 = new SystemMessageId(2922);
		BLOCK_CHECKER_ENDS_4 = new SystemMessageId(2923);
		YOU_CANNOT_ENTER_SEED_IN_FLYING_TRANSFORM = new SystemMessageId(2924);
		BLOCK_CHECKER_ENDS_3 = new SystemMessageId(2925);
		BLOCK_CHECKER_ENDS_2 = new SystemMessageId(2926);
		BLOCK_CHECKER_ENDS_1 = new SystemMessageId(2927);
		TEAM_C1_WON = new SystemMessageId(2928);
		S2_UNIT_OF_THE_ITEM_S1_REQUIRED = new SystemMessageId(2961);
		CANCEL_NOBLESSE_QUESTS = new SystemMessageId(2964);
		PAYMENT_REQUEST_NO_ITEM = new SystemMessageId(2966);
		CANT_FORWARD_MAIL_LIMIT_EXCEEDED = new SystemMessageId(2968);
		CANT_FORWARD_LESS_THAN_MINUTE = new SystemMessageId(2969);
		CANT_FORWARD_NOT_IN_PEACE_ZONE = new SystemMessageId(2970);
		CANT_FORWARD_DURING_EXCHANGE = new SystemMessageId(2971);
		CANT_FORWARD_PRIVATE_STORE = new SystemMessageId(2972);
		CANT_FORWARD_DURING_ENCHANT = new SystemMessageId(2973);
		CANT_FORWARD_BAD_ITEM = new SystemMessageId(2974);
		CANT_FORWARD_NO_ADENA = new SystemMessageId(2975);
		CANT_RECEIVE_NOT_IN_PEACE_ZONE = new SystemMessageId(2976);
		CANT_RECEIVE_DURING_EXCHANGE = new SystemMessageId(2977);
		CANT_RECEIVE_PRIVATE_STORE = new SystemMessageId(2978);
		CANT_RECEIVE_DURING_ENCHANT = new SystemMessageId(2979);
		CANT_RECEIVE_NO_ADENA = new SystemMessageId(2980);
		CANT_RECEIVE_INVENTORY_FULL = new SystemMessageId(2981);
		CANT_CANCEL_NOT_IN_PEACE_ZONE = new SystemMessageId(2982);
		CANT_CANCEL_DURING_EXCHANGE = new SystemMessageId(2983);
		CANT_CANCEL_PRIVATE_STORE = new SystemMessageId(2984);
		CANT_CANCEL_DURING_ENCHANT = new SystemMessageId(2985);
		CANT_CANCEL_INVENTORY_FULL = new SystemMessageId(2988);
		RECIPIENT_NOT_EXIST = new SystemMessageId(3002);
		MAIL_ARRIVED = new SystemMessageId(3008);
		MAIL_SUCCESSFULLY_SENT = new SystemMessageId(3009);
		MAIL_SUCCESSFULLY_RETURNED = new SystemMessageId(3010);
		MAIL_SUCCESSFULLY_CANCELLED = new SystemMessageId(3011);
		MAIL_SUCCESSFULLY_RECEIVED = new SystemMessageId(3012);
		C1_SUCCESSFULY_ENCHANTED_A_S2_S3 = new SystemMessageId(3013);
		DO_YOU_WISH_TO_ERASE_MAIL = new SystemMessageId(3014);
		PLEASE_SELECT_MAIL_TO_BE_DELETED = new SystemMessageId(3015);
		ITEM_SELECTION_POSSIBLE_UP_TO_8 = new SystemMessageId(3016);
		YOU_CANT_SEND_MAIL_TO_YOURSELF = new SystemMessageId(3019);
		PAYMENT_AMOUNT_NOT_ENTERED = new SystemMessageId(3020);
		I_CAN_FEEL_ENERGY_KASHA_EYE_GETTING_STRONGER_RAPIDLY = new SystemMessageId(3023);
		KASHA_EYE_PITCHES_TOSSES_EXPLODE = new SystemMessageId(3024);
		PAYMENT_OF_S1_ADENA_COMPLETED_BY_S2 = new SystemMessageId(3025);
		YOU_CANNOT_USE_SKILL_ENCHANT_ON_THIS_LEVEL = new SystemMessageId(3026);
		YOU_CANNOT_USE_SKILL_ENCHANT_IN_THIS_CLASS = new SystemMessageId(3027);
		YOU_CANNOT_USE_SKILL_ENCHANT_ATTACKING_TRANSFORMED_BOAT = new SystemMessageId(3028);
		S1_RETURNED_MAIL = new SystemMessageId(3029);
		YOU_CANT_CANCEL_RECEIVED_MAIL = new SystemMessageId(3030);
		S1_NOT_RECEIVE_DURING_WAITING_TIME_MAIL_RETURNED = new SystemMessageId(3059);
		DO_YOU_WANT_TO_PAY_S1_ADENA = new SystemMessageId(3062);
		DO_YOU_WANT_TO_FORWARD = new SystemMessageId(3063);
		UNREAD_MAIL = new SystemMessageId(3064);
		LOC_DELUSION_CHAMBER = new SystemMessageId(3065);
		CANT_USE_MAIL_OUTSIDE_PEACE_ZONE = new SystemMessageId(3066);
		S1_CANCELLED_MAIL = new SystemMessageId(3067);
		MAIL_RETURNED = new SystemMessageId(3068);
		DO_YOU_WANT_TO_CANCEL_TRANSACTION = new SystemMessageId(3069);
		S1_ACQUIRED_ATTACHED_ITEM = new SystemMessageId(3072);
		YOU_ACQUIRED_S2_S1 = new SystemMessageId(3073);
		ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED = new SystemMessageId(3074);
		ALLOWED_LENGTH_FOR_TITLE_EXCEEDED = new SystemMessageId(3075);
		MAIL_LIMIT_EXCEEDED = new SystemMessageId(3077);
		YOU_MAKING_PAYMENT_REQUEST = new SystemMessageId(3078);
		ITEMS_IN_PET_INVENTORY = new SystemMessageId(3079);
		CANNOT_RESET_SKILL_LINK_BECAUSE_NOT_ENOUGH_ADENA = new SystemMessageId(3080);
		YOU_CANNOT_RECEIVE_CONDITION_OPPONENT_CANT_ACQUIRE_ADENA = new SystemMessageId(3081);
		YOU_CANNOT_SEND_MAIL_TO_CHAR_BLOCK_YOU = new SystemMessageId(3082);
		YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS = new SystemMessageId(3108);
		COUPLE_ACTION_DENIED = new SystemMessageId(3119);
		TARGET_DO_NOT_MEET_LOC_REQUIREMENTS = new SystemMessageId(3120);
		COUPLE_ACTION_CANCELED = new SystemMessageId(3121);
		REQUESTING_APPROVAL_CHANGE_PARTY_LOOT_S1 = new SystemMessageId(3135);
		PARTY_LOOT_CHANGE_CANCELLED = new SystemMessageId(3137);
		PARTY_LOOT_CHANGED_S1 = new SystemMessageId(3138);
		THE_S2_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_RES_TO_S3_INCREASED = new SystemMessageId(3144);
		YOU_HAVE_REQUESTED_COUPLE_ACTION_C1 = new SystemMessageId(3150);
		S1_S2_ATTRIBUTE_REMOVED_RESISTANCE_S3_DECREASED = new SystemMessageId(3152);
		YOU_DO_NOT_HAVE_ENOUGH_FUNDS_TO_CANCEL_ATTRIBUTE = new SystemMessageId(3156);
		S1_S2_S3_ATTRIBUTE_REMOVED_RESISTANCE_TO_S4_DECREASED = new SystemMessageId(3160);
		THE_S3_ATTRIBUTE_BESTOWED_ON_S1_S2_RESISTANCE_TO_S4_INCREASED = new SystemMessageId(3163);
		C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS = new SystemMessageId(3164);
		C1_IS_SET_TO_REFUSE_PARTY_REQUEST = new SystemMessageId(3168);
		C1_IS_SET_TO_REFUSE_DUEL_REQUEST = new SystemMessageId(3169);
		YOU_CURRENTLY_DO_NOT_HAVE_ANY_RECOMMENDATIONS = new SystemMessageId(3206);
		YOU_OBTAINED_S1_RECOMMENDATIONS = new SystemMessageId(3207);
		YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG = new SystemMessageId(6501);
		THOMAS_D_TURKEY_APPEARED = new SystemMessageId(6503);
		THOMAS_D_TURKEY_DEFETED = new SystemMessageId(6504);
		THOMAS_D_TURKEY_DISAPPEARED = new SystemMessageId(6505);
		
		buildFastLookupTable();
	}
	
	private final static void buildFastLookupTable()
	{
		final Field[] fields = SystemMessageId.class.getDeclaredFields();
		final ArrayList<SystemMessageId> smIds = new ArrayList<SystemMessageId>(fields.length);
		
		int maxId = 0, mod;
		SystemMessageId smId;
		for (final Field field : fields)
		{
			mod = field.getModifiers();
			if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && Modifier.isFinal(mod) && field.getType().equals(SystemMessageId.class))
			{
				try
				{
					smId = (SystemMessageId) field.get(null);
					smId.setName(field.getName());
					smId.setParamCount(parseMessageParameters(field.getName()));
					maxId = Math.max(maxId, smId.getId());
					smIds.add(smId);
				}
				catch (final Exception e)
				{
					_log.log(Level.WARNING, "SystemMessageId: Failed field access for '" + field.getName() + "'", e);
				}
			}
		}
		
		VALUES = new SystemMessageId[maxId + 1];
		for (int i = smIds.size(); i-- > 0;)
		{
			smId = smIds.get(i);
			VALUES[smId.getId()] = smId;
		}
	}
	
	private static final int parseMessageParameters(final String name)
	{
		int paramCount = 0;
		char c1, c2;
		for (int i = 0; i < name.length() - 1; i++)
		{
			c1 = name.charAt(i);
			if (c1 == 'C' || c1 == 'S')
			{
				c2 = name.charAt(i + 1);
				if (Character.isDigit(c2))
				{
					paramCount = Math.max(paramCount, Character.getNumericValue(c2));
					i++;
				}
			}
		}
		return paramCount;
	}
	
	public static final SystemMessageId getSystemMessageId(final int id)
	{
		final SystemMessageId smi = getSystemMessageIdInternal(id);
		return smi == null ? new SystemMessageId(id) : smi;
	}

	private static final SystemMessageId getSystemMessageIdInternal(final int id)
	{
		if (id < 0 || id >= VALUES.length)
			return null;
		
		return VALUES[id];
	}
	
	public static final SystemMessageId getSystemMessageId(final String name)
	{
		try
		{
			return (SystemMessageId) SystemMessageId.class.getField(name).get(null);
		}
		catch (final Exception e)
		{
			return null;
		}
	}
	
	public static final void reloadLocalisations()
	{
		for (final SystemMessageId smId : VALUES)
		{
			if (smId != null)
				smId.removeAllLocalisations();
		}
		
		if (!Config.L2JMOD_MULTILANG_SM_ENABLE)
		{
			_log.log(Level.INFO, "SystemMessageId: MultiLanguage disabled.");
			return;
		}
		
		final List<String> languages = Config.L2JMOD_MULTILANG_SM_ALLOWED;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		
		File file;
		Node node;
		Document doc;
		NamedNodeMap nnmb;
		SystemMessageId smId;
		String text;
		for (final String lang : languages)
		{
			file = new File(Config.DATAPACK_ROOT, "/data/lang/" + lang + "/sm/SystemMessageLocalisation.xml");
			if (!file.isFile())
				continue;
			
			_log.log(Level.INFO, "SystemMessageId: Loading localisation for '" + lang + "'");
			
			try
			{
				doc = factory.newDocumentBuilder().parse(file);
				for (Node na = doc.getFirstChild(); na != null; na = na.getNextSibling())
				{
					if ("list".equals(na.getNodeName()))
					{
						for (Node nb = na.getFirstChild(); nb != null; nb = nb.getNextSibling())
						{
							if ("sm".equals(nb.getNodeName()))
							{
								nnmb = nb.getAttributes();
								node = nnmb.getNamedItem("id");
								if (node != null)
								{
									smId = getSystemMessageId(Integer.parseInt(node.getNodeValue()));
									if (smId == null)
									{
										_log.log(Level.WARNING, "SystemMessageId: Unknown SMID '" + node.getNodeValue() + "', lang '" + lang + "'.");
										continue;
									}
								}
								else
								{
									node = nnmb.getNamedItem("name");
									smId = getSystemMessageId(node.getNodeValue());
									if (smId == null)
									{
										_log.log(Level.WARNING, "SystemMessageId: Unknown SMID '" + node.getNodeValue() + "', lang '" + lang + "'.");
										continue;
									}
								}
								
								node = nnmb.getNamedItem("text");
								if (node == null)
								{
									_log.log(Level.WARNING, "SystemMessageId: No text defined for SMID '" + smId + "', lang '" + lang + "'.");
									continue;
								}
								
								text = node.getNodeValue();
								if (text.isEmpty() || text.length() > 255)
								{
									_log.log(Level.WARNING, "SystemMessageId: Invalid text defined for SMID '" + smId + "' (to long or empty), lang '" + lang + "'.");
									continue;
								}
								
								smId.attachLocalizedText(lang, text);
							}
						}
					}
				}
			}
			catch (final Exception e)
			{
				_log.log(Level.SEVERE, "SystemMessageId: Failed loading '" + file + "'", e);
			}
		}
	}
	
	private static final Builder newBuilder(final String text)
	{
		final ArrayList<Builder> builders = new ArrayList<Builder>();
		
		int index1 = 0, index2 = 0, paramId, subTextLen;
		
		final char[] array = text.toCharArray();
		final int arrayLength = array.length;
		
		char c, c2, c3;
		LOOP:
		for (;index1 < arrayLength; index1++)
		{
			c = array[index1];
			if (c == '$' && index1 < arrayLength - 2)
			{
				c2 = array[index1 + 1];
				if (c2 == 'c' || c2 == 's' || c2 == 'p' || c2 == 'C' || c2 == 'S' || c2 == 'P')
				{
					c3 = array[index1 + 2];
					if (Character.isDigit(c3))
					{
						paramId = Character.getNumericValue(c3);
						subTextLen = index1 - index2;
						if (subTextLen != 0)
							builders.add(new BuilderText(new String(array, index2, subTextLen)));
						
						builders.add(new BuilderObject(paramId));
						index1 += 2;
						index2 = index1 + 1;
						continue LOOP;
					}
				}
			}
		}
		
		if (arrayLength >= index1)
		{
			subTextLen = index1 - index2;
			if (subTextLen != 0)
				builders.add(new BuilderText(new String(array, index2, subTextLen)));
		}
		
		if (builders.size() == 1)
		{
			return builders.get(0);
		}
		else
		{
			return new BuilderContainer(builders.toArray(new Builder[builders.size()]));
		}
	}
	
	private final int _id;
	private String _name;
	private byte _params;
	private SMLocalisation[] _localisations;
	private SystemMessage _staticSystemMessage;
	
	private SystemMessageId(final int id)
	{
		_id = id;
		_localisations = EMPTY_SML_ARRAY;
	}
	
	public final int getId()
	{
		return _id;
	}
	
	private final void setName(final String name)
	{
		_name = name;
	}
	
	public final String getName()
	{
		return _name;
	}
	
	public final int getParamCount()
	{
		return _params;
	}
	
	/**
	 * You better don`t touch this!
	 * 
	 * @param params
	 */
	public final void setParamCount(final int params)
	{
		if (params < 0)
			throw new IllegalArgumentException("Invalid negative param count: " + params);
		
		if (params > 10)
			throw new IllegalArgumentException("Maximum param count exceeded: " + params);
		
		if (params != 0)
			_staticSystemMessage = null;
		
		_params = (byte) params;
	}
	
	public final SMLocalisation getLocalisation(final String lang)
	{
		SMLocalisation sml;
		for (int i = _localisations.length; i-- > 0;)
		{
			sml = _localisations[i];
			if (sml.getLanguage().hashCode() == lang.hashCode())
				return sml;
		}
		return null;
	}
	
	public final void attachLocalizedText(final String lang, final String text)
	{
		final int length = _localisations.length;
		final SMLocalisation[] localisations = Arrays.copyOf(_localisations, length + 1);
		localisations[length] = new SMLocalisation(lang, text);
		_localisations = localisations;
	}
	
	public final void removeAllLocalisations()
	{
		_localisations = EMPTY_SML_ARRAY;
	}
	
	public final SystemMessage getStaticSystemMessage()
	{
		return _staticSystemMessage;
	}
	
	public final void setStaticSystemMessage(final SystemMessage sm)
	{
		_staticSystemMessage = sm;
	}
	
	@Override
	public final String toString()
	{
		return "SM[" + getId() + ":" + getName() + "]";
	}
	
	public static final class SMLocalisation
	{
		private final String _lang;
		private final Builder _builder;
		
		public SMLocalisation(final String lang, final String text)
		{
			_lang = lang;
			_builder = newBuilder(text);
		}
		
		public final String getLanguage()
		{
			return _lang;
		}
		
		public final String getLocalisation(final Object... params)
		{
			return _builder.toString(params);
		}
	}
	
	/**
	 * 
	 * @author Forsaiken
	 *
	 */
	private static interface Builder
	{
		public String toString(final Object param);
		
		public String toString(final Object... params);
		
		public int getIndex();
	}
	
	/**
	 * 
	 * @author Forsaiken
	 *
	 */
	private static final class BuilderContainer implements Builder
	{
		private final Builder[] _builders;
		
		public BuilderContainer(final Builder[] builders)
		{
			_builders = builders;
		}
		
		@Override
		public final String toString(final Object param)
		{
			return toString(new Object[]{param});
		}
		
		@Override
		public final String toString(final Object... params)
		{
			final int buildersLength = _builders.length;
			final int paramsLength = params.length;
			final String[] builds = new String[buildersLength];
			
			Builder builder;
			String build;
			int i, paramIndex, buildTextLen = 0;
			if (paramsLength != 0)
			{
				for (i = buildersLength; i-- > 0;)
				{
					builder = _builders[i];
					paramIndex = builder.getIndex();
					build = paramIndex != -1 && paramIndex < paramsLength ? builder.toString(params[paramIndex]) : builder.toString();
					buildTextLen += build.length();
					builds[i] = build;
				}
			}
			else
			{
				for (i = buildersLength; i-- > 0;)
				{
					build = _builders[i].toString();
					buildTextLen += build.length();
					builds[i] = build;
				}
			}
			
			final FastStringBuilder fsb = new FastStringBuilder(buildTextLen);
			for (i = 0; i < buildersLength; i++)
			{
				fsb.append(builds[i]);
			}
			return fsb.toString();
		}
		
		@Override
		public final int getIndex()
		{
			return -1;
		}
	}
	
	/**
	 * 
	 * @author Forsaiken
	 *
	 */
	private static final class BuilderText implements Builder
	{
		private final String _text;
		
		public BuilderText(final String text)
		{
			_text = text;
		}
		
		@Override
		public final String toString(final Object param)
		{
			return toString();
		}
		
		@Override
		public final String toString(final Object... params)
		{
			return toString();
		}
		
		@Override
		public final int getIndex()
		{
			return -1;
		}
		
		@Override
		public final String toString()
		{
			return _text;
		}
	}
	
	/**
	 * 
	 * @author Forsaiken
	 *
	 */
	private static final class BuilderObject implements Builder
	{
		private final int _index;
		
		public BuilderObject(final int id)
		{
			if (id < 1 || id > 9)
				throw new RuntimeException("Illegal id " + id);
			
			_index = id - 1;
		}
		
		@Override
		public final String toString(final Object param)
		{
			return param == null ? "null" : param.toString();
		}
		
		@Override
		public final String toString(final Object... params)
		{
			if (params == null || params.length == 0)
				return "null";
			
			return params[0].toString();
		}
		
		@Override
		public final int getIndex()
		{
			return _index;
		}
		
		@Override
		public final String toString()
		{
			return "[PARAM-" + (_index + 1) + "]";
		}
	}
	
	/**
	 * 
	 * @author Forsaiken
	 *
	 */
	private static final class FastStringBuilder
	{
		private final char[] _array;
		private int _len;
		
		public FastStringBuilder(final int capacity)
		{
			_array = new char[capacity];
		}
		
		public final void append(final String text)
		{
			text.getChars(0, text.length(), _array, _len);
			_len += text.length();
		}
		
		@Override
		public final String toString()
		{
			return new String(_array);
		}
	}
}