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
import com.l2jserver.gameserver.network.serverpackets.ExShowScreenMessage;

/**
 * NpcStringId implementation, based on
 * SystemMessageId class
 * 
 * @author mrTJO
 */
public final class NpcStringId
{
	private static final Logger _log = Logger.getLogger(NpcStringId.class.getName());
	private static final NSLocalisation[] EMPTY_NSL_ARRAY = new NSLocalisation[0];
	public static final NpcStringId[] EMPTY_ARRAY = new NpcStringId[0];
	
	/**
	 * ID: 1<br>
	 * Message: ���߂܂��āB����$s1�ł��B�N�b�N�b�A���O��$s2����H�q�b�q�b�q�b�B
	 */
	public static final NpcStringId HELLO_I_AM_S1_YOU_ARE_S2_RIGHT_HEHEHE;

	/**
	 * ID: 2<br>
	 * Message: $s1--$s2--$s3--$s4//$s5 �q�b�q�b�q�b�B
	 */
	public static final NpcStringId S1_S2_S3_S4_S5_HEHEHE;

	/**
	 * ID: 5<br>
	 * Message: $s1/$s2 $s3 ���Ɏ���̎g�p�����������Ƃ��B
	 */
	public static final NpcStringId WITHDRAW_THE_FEE_FOR_THE_NEXT_TIME_AT_S1_S2_S4;

	/**
	 * ID: 8<br>
	 * Message: �i�K
	 */
	public static final NpcStringId STAGE;

	/**
	 * ID: 9<br>
	 * Message: $s1�i�K
	 */
	public static final NpcStringId STAGE_S1;

	/**
	 * ID: 10<br>
	 * Message: $s1%%
	 */
	public static final NpcStringId S1;

	/**
	 * ID: 2004<br>
	 * Message: ���A���ɉ���������ł����B
	 */
	public static final NpcStringId WHAT_DID_YOU_JUST_DO_TO_ME;

	/**
	 * ID: 2005<br>
	 * Message: ������Ȃ����悤�ƁH���������`
	 */
	public static final NpcStringId ARE_YOU_TRYING_TO_TAME_ME_DONT_DO_THAT;

	/**
	 * ID: 2006<br>
	 * Message: ����Ȃ��̂���Ȃ��Ă������ł���B���Ȃ�����Ȃ��Ȃ邩���H
	 */
	public static final NpcStringId DONT_GIVE_SUCH_A_THING_YOU_CAN_ENDANGER_YOURSELF;

	/**
	 * ID: 2007<br>
	 * Message: �y�b�y�b�D�D�D�Ȃ񂾂����H
	 */
	public static final NpcStringId YUCK_WHAT_IS_THIS_IT_TASTES_TERRIBLE;

	/**
	 * ID: 2008<br>
	 * Message: �����󂢂��`�����Ƃ��傤�����B
	 */
	public static final NpcStringId IM_HUNGRY_GIVE_ME_A_LITTLE_MORE_PLEASE;

	/**
	 * ID: 2009<br>
	 * Message: ������H�H�ו��H
	 */
	public static final NpcStringId WHAT_IS_THIS_IS_THIS_EDIBLE;

	/**
	 * ID: 2010<br>
	 * Message: �ق��Ƃ��Ă�B
	 */
	public static final NpcStringId DONT_WORRY_ABOUT_ME;

	/**
	 * ID: 2011<br>
	 * Message: �����������́A���肪�Ƃ��B
	 */
	public static final NpcStringId THANK_YOU_THAT_WAS_DELICIOUS;

	/**
	 * ID: 2012<br>
	 * Message: �i�X���Ȃ����D���ɂȂ��Ă�����B
	 */
	public static final NpcStringId I_THINK_I_AM_STARTING_TO_LIKE_YOU;

	/**
	 * ID: 2013<br>
	 * Message: �L���[�L���[�B
	 */
	public static final NpcStringId EEEEEK_EEEEEK;

	/**
	 * ID: 2014<br>
	 * Message: ������Ȃ�����̂͂�߂ȁB���̋C�͂Ȃ�����B
	 */
	public static final NpcStringId DONT_KEEP_TRYING_TO_TAME_ME_I_DONT_WANT_TO_BE_TAMED;

	/**
	 * ID: 2015<br>
	 * Message: �����̃G�T���낤�B�ʂɂ��񂽂̎�ł������񂾂��B
	 */
	public static final NpcStringId IT_IS_JUST_FOOD_TO_ME_ALTHOUGH_IT_MAY_ALSO_BE_YOUR_HAND;

	/**
	 * ID: 2016<br>
	 * Message: ����ȐH�ׂ��瑾�����Ⴄ�B�K�c�K�c�B
	 */
	public static final NpcStringId IF_I_KEEP_EATING_LIKE_THIS_WONT_I_BECOME_FAT_CHOMP_CHOMP;

	/**
	 * ID: 2017<br>
	 * Message: ���ŐH�ו��΂��������̂��ȁH
	 */
	public static final NpcStringId WHY_DO_YOU_KEEP_FEEDING_ME;

	/**
	 * ID: 2018<br>
	 * Message: ����M����ȁB���؂邩������Ȃ��B
	 */
	public static final NpcStringId DONT_TRUST_ME_IM_AFRAID_I_MAY_BETRAY_YOU_LATER;

	/**
	 * ID: 2019<br>
	 * Message: �O�����D�D�D
	 */
	public static final NpcStringId GRRRRR;

	/**
	 * ID: 2020<br>
	 * Message: ���O�����珵�������Ƃ��I
	 */
	public static final NpcStringId YOU_BROUGHT_THIS_UPON_YOURSELF;

	/**
	 * ID: 2021<br>
	 * Message: ���������I���̒��ɂ悩��ʊ���I
	 */
	public static final NpcStringId I_FEEL_STRANGE_I_KEEP_HAVING_THESE_EVIL_THOUGHTS;

	/**
	 * ID: 2022<br>
	 * Message: ���ǁD�D�D�����Ȃ�񂾂ȁB
	 */
	public static final NpcStringId ALAS_SO_THIS_IS_HOW_IT_ALL_ENDS;

	/**
	 * ID: 2023<br>
	 * Message: �ꂵ���D�D�D�ꂵ���悤�D�D�D
	 */
	public static final NpcStringId I_DONT_FEEL_SO_GOOD_OH_MY_MIND_IS_VERY_TROUBLED;

	/**
	 * ID: 2024<br>
	 * Message: $s1�A��Ȃ�������ĂȂ񂾂낤�H
	 */
	public static final NpcStringId S1_SO_WHAT_DO_YOU_THINK_IT_IS_LIKE_TO_BE_TAMED;

	/**
	 * ID: 2025<br>
	 * Message: $s1�A�X�p�C�X�����邽�тɁA���񂽂̎肪�Ȃ������Ȃ��B
	 */
	public static final NpcStringId S1_WHENEVER_I_SEE_SPICE_I_THINK_I_WILL_MISS_YOUR_HAND_THAT_USED_TO_FEED_IT_TO_ME;

	/**
	 * ID: 2026<br>
	 * Message: $s1�A���ɂ͍s���Ȃ��ŁB�����܂ōs����͂��Ȃ��B
	 */
	public static final NpcStringId S1_DONT_GO_TO_THE_VILLAGE_I_DONT_HAVE_THE_STRENGTH_TO_FOLLOW_YOU;

	/**
	 * ID: 2027<br>
	 * Message: ����M���Ă���Ă��肪�Ƃ��A$s1�B���Ȃ��̖��ɗ��Ă�Ƃ����񂾂��D�D�D
	 */
	public static final NpcStringId THANK_YOU_FOR_TRUSTING_ME_S1_I_HOPE_I_WILL_BE_HELPFUL_TO_YOU;

	/**
	 * ID: 2028<br>
	 * Message: $s1�A�������Ȃ��̖��ɗ��Ă邩�ȁH
	 */
	public static final NpcStringId S1_WILL_I_BE_ABLE_TO_HELP_YOU;

	/**
	 * ID: 2029<br>
	 * Message: ����΂�I�悢����I
	 */
	public static final NpcStringId I_GUESS_ITS_JUST_MY_ANIMAL_MAGNETISM;

	/**
	 * ID: 2030<br>
	 * Message: �H������V�ł��߂�ˁB���������B
	 */
	public static final NpcStringId TOO_MUCH_SPICY_FOOD_MAKES_ME_SWEAT_LIKE_A_BEAST;

	/**
	 * ID: 2031<br>
	 * Message: ���Ȃ��Ƒ������킹��̂��y�ɂȂ��Ă�����B
	 */
	public static final NpcStringId ANIMALS_NEED_LOVE_TOO;

	/**
	 * ID: 2032<br>
	 * Message: ��`���Ă�낤�I
	 */
	public static final NpcStringId WHATD_I_MISS_WHATD_I_MISS;

	/**
	 * ID: 2033<br>
	 * Message: �V�C�������ȁB�s�N�j�b�N�ł��s�������B
	 */
	public static final NpcStringId I_JUST_KNOW_BEFORE_THIS_IS_OVER_IM_GONNA_NEED_A_LOT_OF_SERIOUS_THERAPY;

	/**
	 * ID: 2034<br>
	 * Message: ����Ƃ����Ă��Ȃ����C�ɓ������킯����Ȃ��D�D�D���́A���́D�D�D
	 */
	public static final NpcStringId I_SENSE_GREAT_WISDOM_IN_YOU_IM_AN_ANIMAL_AND_I_GOT_INSTINCTS;

	/**
	 * ID: 2035<br>
	 * Message: �������痣���ȁB�͂�݂��Ă��Ȃ��Ȃ邼�B
	 */
	public static final NpcStringId REMEMBER_IM_HERE_TO_HELP;

	/**
	 * ID: 2036<br>
	 * Message: ���ɗ����Ă�̂��Ȃ��A���B
	 */
	public static final NpcStringId ARE_WE_THERE_YET;

	/**
	 * ID: 2037<br>
	 * Message: �H�ׂ邱�ƈȊO�ɂ��ł���񂾂��Ă΁I
	 */
	public static final NpcStringId THAT_REALLY_MADE_ME_FEEL_GOOD_TO_SEE_THAT;

	/**
	 * ID: 2038<br>
	 * Message: �������A�������A�������A���[�����I
	 */
	public static final NpcStringId OH_NO_NO_NO_NOOOOO;

	/**
	 * ID: 2150<br>
	 * Message: ���̖����W����̂͒N���B
	 */
	public static final NpcStringId WHO_AWOKE_ME;

	/**
	 * ID: 2151<br>
	 * Message: $s1�l�A����l�l�̖��ɂ��A���ē��������܂��B
	 */
	public static final NpcStringId MY_MASTER_HAS_INSTRUCTED_ME_TO_BE_YOUR_GUIDE_S1;

	/**
	 * ID: 2152<br>
	 * Message: $s1�l�A���̖{�I�𒲂ׂĂ��������B
	 */
	public static final NpcStringId PLEASE_CHECK_THIS_BOOKCASE_S1;

	/**
	 * ID: 2250<br>
	 * Message: $s1�Ƃ����������҂�A�����Ă񂾂̂͂��O���D�D�D
	 */
	public static final NpcStringId DID_YOU_CALL_ME_S1;

	/**
	 * ID: 2251<br>
	 * Message: �ӎ����h�炮�D�D�D�����߂�Ȃ��Ă͂Ȃ�Ȃ��̂��D�D�D
	 */
	public static final NpcStringId IM_CONFUSED_MAYBE_ITS_TIME_TO_GO_BACK;

	/**
	 * ID: 2450<br>
	 * Message: ���A���̐��W�́I
	 */
	public static final NpcStringId THAT_SIGN;

	/**
	 * ID: 2550<br>
	 * Message: ���̔��͂���l�l�����������B $s1 �G��Ă͂Ȃ�ʂ��I
	 */
	public static final NpcStringId THAT_BOX_WAS_SEALED_BY_MY_MASTER_S1_DONT_TOUCH_IT;

	/**
	 * ID: 2551<br>
	 * Message: �s���̐����̂ł��鎄��|���Ƃ́B���O�͂܂����A�̎�̉�����󂯂��ҁI�H
	 */
	public static final NpcStringId YOUVE_ENDED_MY_IMMORTAL_LIFE_YOURE_PROTECTED_BY_THE_FEUDAL_LORD_ARENT_YOU;

	/**
	 * ID: 4151<br>
	 * Message: �z�B�C�������B\n���S�҈ē��l�̂Ƃ���ɍs���Ă��������B
	 */
	public static final NpcStringId DELIVERY_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4152<br>
	 * Message: ���S�җp�\�E�� �V���b�g�l���B\n���S�҈ē��l�̂Ƃ���ɍs���Ă��������B
	 */
	public static final NpcStringId ACQUISITION_OF_SOULSHOT_FOR_BEGINNERS_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4153<br>
	 * Message: ���S�җp����������l���B\n���S�҈ē��l�̂Ƃ���ɍs���Ă��������B
	 */
	public static final NpcStringId ACQUISITION_OF_WEAPON_EXCHANGE_COUPON_FOR_BEGINNERS_COMPLETE_N_GO_SPEAK_WITH_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4154<br>
	 * Message: ���S�җp����l���B\n���S�҈ē��l�̂Ƃ���ɍs���Ă��������B
	 */
	public static final NpcStringId ACQUISITION_OF_RACE_SPECIFIC_WEAPON_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4155<br>
	 * Message: �Ō�̔C�������B\n���S�҈ē��l�̂Ƃ���ɍs���Ă��������B
	 */
	public static final NpcStringId LAST_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 6051<br>
	 * Message: $s1�I���O�𐶂����Ă͂����Ȃ��B���ނ̂Ȃ�A�����̍D��S�����ނ񂾂ȁB
	 */
	public static final NpcStringId S1_I_MUST_KILL_YOU_BLAME_YOUR_OWN_CURIOSITY;

	/**
	 * ID: 6052<br>
	 * Message: �^�̂�������B�܂����邺�B
	 */
	public static final NpcStringId YOU_HAVE_GOOD_LUCK_I_SHALL_RETURN;

	/**
	 * ID: 6053<br>
	 * Message: ����Ȃɋ����Ƃ͂ȁB���s���B
	 */
	public static final NpcStringId YOU_ARE_STRONG_THIS_WAS_A_MISTAKE;

	/**
	 * ID: 6054<br>
	 * Message: �ǂ��̔n�̍����킩��Ȃ�����A�Ȃ�Ō��o������񂾁B������I
	 */
	public static final NpcStringId WHO_ARE_YOU_TO_JOIN_IN_THE_BATTLE_HOW_UPSETTING;

	/**
	 * ID: 6451<br>
	 * Message: $s1�A���������ɗ����̂��B
	 */
	public static final NpcStringId S1_DID_YOU_COME_TO_HELP_ME;

	/**
	 * ID: 6551<br>
	 * Message: �`�b�I�����͈Ⴄ���I
	 */
	public static final NpcStringId DRATS_HOW_COULD_I_BE_SO_WRONG;

	/**
	 * ID: 6552<br>
	 * Message: $s1�I���̔����痣���I���̉ݕ��͉����������I
	 */
	public static final NpcStringId S1_STEP_BACK_FROM_THE_CONFOUNDED_BOX_I_WILL_TAKE_IT_MYSELF;

	/**
	 * ID: 6553<br>
	 * Message: $s1�I�܂���Ŗ߂��Ă��邼�B���ƂȂ����҂��Ă���I
	 */
	public static final NpcStringId S1_I_WILL_BE_BACK_SOON_STAY_THERE_AND_DONT_YOU_DARE_WANDER_OFF;

	/**
	 * ID: 6554<br>
	 * Message: �����I��������Ȃ�ɂ����Ƃ́D�D�D
	 */
	public static final NpcStringId GRR_IVE_BEEN_HIT;

	/**
	 * ID: 6555<br>
	 * Message: �����I���O�͉��҂��I�Ȃ����̎ז�������̂��I
	 */
	public static final NpcStringId GRR_WHO_ARE_YOU_AND_WHY_HAVE_YOU_STOPPED_ME;

	/**
	 * ID: 6556<br>
	 * Message: �����ƁA���Ԃ��x��Ă��܂����ȁI
	 */
	public static final NpcStringId I_AM_LATE;

	/**
	 * ID: 6557<br>
	 * Message: ��낵�����ނ��I
	 */
	public static final NpcStringId GOOD_LUCK;

	/**
	 * ID: 6750<br>
	 * Message: $s1�I���O�����ɂ͋ւ���ꂽ�m�����I��������I
	 */
	public static final NpcStringId S1_YOU_SEEK_THE_FORBIDDEN_KNOWLEDGE_AND_I_CANNOT_LET_YOU_HAVE_IT;

	/**
	 * ID: 6751<br>
	 * Message: ���O�ɋ����ꂽ���Ԃ͂����܂ł��D�D�D
	 */
	public static final NpcStringId IS_THIS_ALL_I_AM_ALLOWED_TO_HAVE;

	/**
	 * ID: 6752<br>
	 * Message: ���A����|���΁A�������O�����͔j�ł��邾�낤�D�D�D
	 */
	public static final NpcStringId YOU_DEFEATED_ME_BUT_OUR_DOOM_APPROACHES;

	/**
	 * ID: 6753<br>
	 * Message: $s1�I���̕����ɂȂ�Ă��Ƃ��Ă����񂾁I���O�A���҂��I
	 */
	public static final NpcStringId S1_WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS;

	/**
	 * ID: 6754<br>
	 * Message: �A�����I
	 */
	public static final NpcStringId BEEFCAKE;

	/**
	 * ID: 6755<br>
	 * Message: �����I�Ă߂����҂��I�H�ז����Ă�����肩�B
	 */
	public static final NpcStringId GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS;

	/**
	 * ID: 6756<br>
	 * Message: �����͂��̂��炢�ɂ��Ƃ��Ă�邪�A���͂������Ⴈ���˂����I
	 */
	public static final NpcStringId FAREWELL_AND_WATCH_YOUR_BACK;

	/**
	 * ID: 6757<br>
	 * Message: �J�}�G���I�悩�����I�������͌�ɂ��āA������Ǝ��݂��Ă���Ȃ����D�D�D
	 */
	public static final NpcStringId KAMAEL_GOOD_TO_SEE_YOU_I_HAVE_SOMETHING_TO_ASK_YOU;

	/**
	 * ID: 6758<br>
	 * Message: $s1�I�����A�R�C�c���U������̂��I
	 */
	public static final NpcStringId S1_GO_GET_HIM;

	/**
	 * ID: 6759<br>
	 * Message: $s1�I�������Ă���A���āI���񂾁I
	 */
	public static final NpcStringId S1_WHAT_ARE_YOU_DOING_ATTACK_HIM;

	/**
	 * ID: 6760<br>
	 * Message: $s1��I���O�̃|�e���V�����͂��̒��x�ł͂Ȃ��͂����B
	 */
	public static final NpcStringId S1_IS_YOUR_FULL_POTENTIAL;

	/**
	 * ID: 6761<br>
	 * Message: ���肪�Ƃ��I���͍������G��ǂ������čs���˂΂Ȃ�Ȃ��B
	 */
	public static final NpcStringId THANKS_I_MUST_GO_AND_HUNT_DOWN_THOSE_THAT_OPPOSE_ME;

	/**
	 * ID: 6762<br>
	 * Message: ����������D�D�D���͂����ɂ��ǂ������Ȃ���΂Ȃ�Ȃ��D�D�D
	 */
	public static final NpcStringId YOU_ARE_SO_STUBBORN_I_MUST_FOLLOW_HIM_NOW;

	/**
	 * ID: 6763<br>
	 * Message: ���O�Ȃ�^�u���b�g���琳�������𓾂邱�Ƃ��ł���͂����D�D�D
	 */
	public static final NpcStringId SEEK_ENLIGHTENMENT_FROM_THE_TABLET;

	/**
	 * ID: 6764<br>
	 * Message: ������ɂ�������҂�I�łт�I
	 */
	public static final NpcStringId ARROGANT_BEINGS_YOU_ARE_ALL_DOOMED;

	/**
	 * ID: 6765<br>
	 * Message: ���̎����Ŏ��ɗ^����ꂽ���Ԃ͏I������B�^���悩�����ȁD�D�D
	 */
	public static final NpcStringId MY_TIME_IN_YOUR_WORLD_HAS_COME_TO_AN_END_CONSIDER_YOURSELVES_LUCKY;

	/**
	 * ID: 6766<br>
	 * Message: $s1�I�_�ɑ����ʎ҂������A���̂�I
	 */
	public static final NpcStringId S1_HOW_DARE_YOU;

	/**
	 * ID: 6767<br>
	 * Message: $s1�I�������I�_�ɑ����ʎ҂����͊댯���D�D�D
	 */
	public static final NpcStringId S1_AHHAA_YOUR_GOD_FORSAKES_YOU;

	/**
	 * ID: 6851<br>
	 * Message: $s1�I�����܂ł��B���낻�뎀��ł����˂΂ȁB
	 */
	public static final NpcStringId S1_YOUR_TIME_IS_UP_PREPARE_TO_DIE_A_HORRIBLE_DEATH;

	/**
	 * ID: 6852<br>
	 * Message: �Ȃ�Ɖ^�̂�����߁I���͐����ċA��˂����B
	 */
	public static final NpcStringId CONSIDER_YOURSELF_LUCKY_THE_NEXT_TIME_WE_MEET_YOU_WILL_DIE_PERMANENTLY;

	/**
	 * ID: 6853<br>
	 * Message: �o���Ă���I���͂������Ⴈ���˂����I
	 */
	public static final NpcStringId FARE_THEE_WELL_WE_SHALL_MEET_AGAIN;

	/**
	 * ID: 6854<br>
	 * Message: $s1�I���̕����ɂȂ�Ă��Ƃ��Ă����񂾁I���O�A���҂��I
	 */
	public static final NpcStringId S1_WHO_ARE_YOU_AND_BETTER_YET_WHY_ARE_YOU_BOTHERING_MY_MINIONS;

	/**
	 * ID: 6855<br>
	 * Message: �A�����I
	 */
	public static final NpcStringId STRENGTH_BEYOND_STRENGTH;

	/**
	 * ID: 6856<br>
	 * Message: �����I�Ă߂����҂��I�H�ז����Ă�����肩�B
	 */
	public static final NpcStringId GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_WHERE_IT_DOESNT_BELONG;

	/**
	 * ID: 6857<br>
	 * Message: �����͂��̂��炢�ɂ��Ƃ��Ă�邪�A���͂������Ⴈ���˂����I
	 */
	public static final NpcStringId YOUVE_WON_FOR_NOW_BUT_WE_WILL_MEET_AGAIN;

	/**
	 * ID: 6858<br>
	 * Message: �O���������ɂ悭�ǂ������Ă�����񂾁B�ɂ��B
	 */
	public static final NpcStringId ARE_THEY_TIRED_OF_FOLLOWING_ME;

	/**
	 * ID: 6859<br>
	 * Message: $s1�I���ɗ͂�݂��Ă���Ȃ����B
	 */
	public static final NpcStringId S1_CAN_YOU_HELP_ME;

	/**
	 * ID: 6860<br>
	 * Message: $s1�I���O�̗͂͂���Ȃ��񂩁B
	 */
	public static final NpcStringId IS_THAT_ALL_YOU_GOT_LITTLE_S1;

	/**
	 * ID: 6861<br>
	 * Message: $s1�I����΂���Č����Ă����������̂���I�H
	 */
	public static final NpcStringId S1_WAKE_UP_FOOL_DONT_LET_HIM_GET_AWAY;

	/**
	 * ID: 6862<br>
	 * Message: ���͂₨�O�̎ז��������͂��Ȃ��낤�B�ł́A����΁I
	 */
	public static final NpcStringId THE_PATH_IS_CLEAR_BUT_BE_CAREFUL;

	/**
	 * ID: 6863<br>
	 * Message: �U�v�L�G����ǂ�Ȃ���΁B�܂�����I
	 */
	public static final NpcStringId I_MUST_FOLLOW_SOMEONE_NOW_SEE_YOU_AROUND;

	/**
	 * ID: 6864<br>
	 * Message: �܂����̂��y���݂ɂ��Ă���B
	 */
	public static final NpcStringId MAY_WE_MEET_AGAIN;

	/**
	 * ID: 6865<br>
	 * Message: �_�ɐn���������҂ɂ͓V�����I
	 */
	public static final NpcStringId CURSES_ON_THOSE_WHO_BLASPHEME_THE_GODS;

	/**
	 * ID: 6866<br>
	 * Message: �A�C���n�U�[�h�l�����Ăт��I���͕K���V���������Ă�낤�I
	 */
	public static final NpcStringId EINHASAD_IS_CALLING_ME_YOU_ARE_LUCKY_AND_I_SHALL_CONTINUE_MY_PUNISHMENT_LATER;

	/**
	 * ID: 6867<br>
	 * Message: $s1�I�_�̖��ɂ����Ă��O���ق��Ă�낤�B
	 */
	public static final NpcStringId BY_THE_POWER_VESTED_IN_ME_BY_THE_GODS_YOU_S1_SHALL_DIE;

	/**
	 * ID: 6868<br>
	 * Message: $s1�I���O��Y��Ȃ����I
	 */
	public static final NpcStringId S1_I_WILL_NOT_FORGET_YOU;

	/**
	 * ID: 6950<br>
	 * Message: $s1�I�x���X�l�̕��S�ł��鉴�̎ז�������Ƃ́D�D�D���ˁI
	 */
	public static final NpcStringId S1_HOW_DARE_YOU_INTERFERE_YOU_SHALL_PAY_FOR_THIS;

	/**
	 * ID: 6951<br>
	 * Message: �����A�x���X�l�������Ă�ł���B�^���悩�����ȁD�D�D
	 */
	public static final NpcStringId BELETH_IS_CALLING_ME_YOU_ARE_LUCKY_BUT_STILL_A_FOOL;

	/**
	 * ID: 6952<br>
	 * Message: �o���Ă�I���͂�����߂Ȃ�����ȁB
	 */
	public static final NpcStringId I_SHALL_TAKE_MY_LEAVE_BUT_WILL_NEVER_SURRENDER;

	/**
	 * ID: 6954<br>
	 * Message: �A�����I
	 */
	public static final NpcStringId COWER_BEFORE_MY_AWESOME_AND_MIGHTY_POWER;

	/**
	 * ID: 6955<br>
	 * Message: �����I�Ă߂����҂��I�H�ז����Ă�����肩�B
	 */
	public static final NpcStringId GRR_CANT_YOU_FIND_SOMETHING_BETTER_TO_DO_WITH_YOUR_TIME;

	/**
	 * ID: 6956<br>
	 * Message: �����͂��̂��炢�ɂ��Ƃ��Ă�邪�A���͂������Ⴈ���˂����I
	 */
	public static final NpcStringId I_SHALL_TAKE_MY_LEAVE_BUT_YOUR_HEAD_WILL_BE_MINE_SOME_DAY_OH_YESYES_IT_WILL;

	/**
	 * ID: 6957<br>
	 * Message: �����A���̎q����D�D�D
	 */
	public static final NpcStringId MY_CHILDREN_ARE_STRONGER;

	/**
	 * ID: 6958<br>
	 * Message: $s1�I�ꏏ�ɖڂ̑O�̓G���F�E���ɂ��悤�ł͂Ȃ����B
	 */
	public static final NpcStringId S1_LETS_KILL_THEM_ALL;

	/**
	 * ID: 6959<br>
	 * Message: $s1�I����΂�A�q����D�D�D
	 */
	public static final NpcStringId S1_COME_MY_CHILDREN;

	/**
	 * ID: 6960<br>
	 * Message: $s1�I���O�̐��ݔ\�͂�ڊo�߂�����̂��D�D�D�G��������̂�h���B
	 */
	public static final NpcStringId S1_MUSTER_YOUR_STRENGTH_PICK_THEM_OFF_LIKE_CHICKENS;

	/**
	 * ID: 6961<br>
	 * Message: ���肪�Ƃ��A�q����D�D�D�����܂���������邾�낤�B
	 */
	public static final NpcStringId THANK_YOU_MY_CHILDREN_SOMEDAY_WE_WILL_MEET_AGAIN;

	/**
	 * ID: 6962<br>
	 * Message: �q����D�D�D���͓G��ǂ������˂΂Ȃ�Ȃ��B
	 */
	public static final NpcStringId MY_CHILDREN_SEEK_MY_ENEMIES;

	/**
	 * ID: 6963<br>
	 * Message: �q����D�D�D���O�̖����ɏj�����D�D�D
	 */
	public static final NpcStringId MY_CHILDREN_I_GRANT_YOU_MY_BLESSINGS;

	/**
	 * ID: 6964<br>
	 * Message: ���݂��Ă͂Ȃ�Ȃ��҂�����I
	 */
	public static final NpcStringId YOU_WORTHLESS_BEINGS;

	/**
	 * ID: 6965<br>
	 * Message: �n��ŋ����ꂽ���Ԃ��I������ȁB�^���悩�����ȁD�D�D
	 */
	public static final NpcStringId MY_TIME_ON_THE_MATERIAL_PLANE_HAS_ENDED_YOU_ARE_LUCKY;

	/**
	 * ID: 6966<br>
	 * Message: $s1�I���݂��Ă͂Ȃ�Ȃ��҂�����A�����邪�����I
	 */
	public static final NpcStringId S1_WORTHLESS_BEINGS_BEGONE;

	/**
	 * ID: 6967<br>
	 * Message: $s1�I��͂肨�O�����͊댯���D�D�D
	 */
	public static final NpcStringId S1_YOU_ARE_THE_MEANING_OF_THE_WORD_DANGER;

	/**
	 * ID: 7050<br>
	 * Message: �悭�������܂ŗ����ȁA$s1�I���̋������ؖ����邽�߂��A���ˁI
	 */
	public static final NpcStringId YOU_MADE_IT_HERE_S1_ILL_SHOW_MY_STRENGTH_DIE;

	/**
	 * ID: 7051<br>
	 * Message: �n�n�n�n�I�M�l�͎���|���Ȃ������I�����͂����܂łɂ��Ă��������B
	 */
	public static final NpcStringId HA_YOU_FAILED_ARE_YOU_READY_TO_QUIT;

	/**
	 * ID: 7052<br>
	 * Message: �N�b�D�D�D�Ȃ����I���͍ŋ��̂͂��D�D�D���͏����̂��߂ɂ��ׂĂ����Ă��̂ɁI
	 */
	public static final NpcStringId IM_THE_STRONGEST_I_LOST_EVERYTHING_TO_WIN;

	/**
	 * ID: 7053<br>
	 * Message: $s1�I�ނ炪�����n���V���̕������ƒm���Ă̂��Ƃ̂悤���ȁH
	 */
	public static final NpcStringId S1_ARE_YOU_DOING_THIS_BECAUSE_THEYRE_HALISHAS_MINIONS;

	/**
	 * ID: 7054<br>
	 * Message: ���A���̍��͐�N�̎������������A�n���V���l�̂��T�ցD�D�D
	 */
	public static final NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_IM_GETTING_CLOSE_TO_HALISHA;

	/**
	 * ID: 7055<br>
	 * Message: ���̕��A�Ȃ����l�̑����Ɏ��˂����ނ̂��H
	 */
	public static final NpcStringId MIND_YOUR_OWN_BUSINESS;

	/**
	 * ID: 7056<br>
	 * Message: ����ȏ�̐킢�͖��ʂ��D�D�D����ł����܂����I
	 */
	public static final NpcStringId THIS_FIGHT_IS_A_WASTE_OF_TIME_GOODBYE;

	/**
	 * ID: 7057<br>
	 * Message: �_��������d�������낤�Ƃ��A���̏�ɂ͏�ɑ��z���P���Ă���͕̂ς��ʎ������I
	 */
	public static final NpcStringId EVERY_CLOUD_HAS_A_SILVER_LINING_DONT_YOU_THINK;

	/**
	 * ID: 7058<br>
	 * Message: ���̈����̌��t�Ɏ����X����ȁI$s1�I
	 */
	public static final NpcStringId S1_DONT_LISTEN_TO_THIS_DEMON;

	/**
	 * ID: 7059<br>
	 * Message: $s1�I�����͐S�̓G���I����̌����f���ēG���a��I
	 */
	public static final NpcStringId S1_HESITATION_BETRAYS_YOUR_HEART_FIGHT;

	/**
	 * ID: 7060<br>
	 * Message: $s1�I���`�����A�N�������Ƃ������Ƃ��{���ɉ��l�̂Ȃ����Ƃ��Ǝv���Ă���̂��B
	 */
	public static final NpcStringId S1_ISNT_PROTECTING_SOMEBODY_WORTHY_ISNT_THAT_JUSTICE;

	/**
	 * ID: 7061<br>
	 * Message: �����A�}�ȗp�����v���o�����B���ꂶ��B
	 */
	public static final NpcStringId I_HAVE_URGENT_BUSINESS_I_GOTTA_GO;

	/**
	 * ID: 7062<br>
	 * Message: �����܂łȂ̂��D�D�D���̓w�͂́D�D�D
	 */
	public static final NpcStringId ARE_MY_EFFORTS_IN_VAIN_IS_THIS_THE_END;

	/**
	 * ID: 7063<br>
	 * Message: ����΂��A�F��B�����܂���邱�Ƃ��B
	 */
	public static final NpcStringId GOODBYE_FRIEND_I_HOPE_TO_SEE_YOU_AGAIN;

	/**
	 * ID: 7064<br>
	 * Message: ���O��R�m�͏�ɋ������ȁI
	 */
	public static final NpcStringId KNIGHTS_ARE_ALWAYS_FOOLISH;

	/**
	 * ID: 7065<br>
	 * Message: �N�N�N�b�D�D�D���̂��炢�Ō������Ă�낤
	 */
	public static final NpcStringId ILL_SHOW_MERCY_ON_YOU_FOR_NOW;

	/**
	 * ID: 7066<br>
	 * Message: $s1�I���ƌ����Ă����Ȃ�����Ȃ��������́I�S���̂ĂĂ��܂����̂��B���O�̐��`�͂����̋U�P���I
	 */
	public static final NpcStringId S1_YOUR_JUSTICE_IS_JUST_HYPOCRISY_IF_YOU_GIVE_UP_ON_WHAT_YOUVE_PROMISED_TO_PROTECT;

	/**
	 * ID: 7067<br>
	 * Message: $s1�A�������Ǝv���Ȃ�I���O�̉e�̒��ɂ���ł́A��ɂ��O��������Ă���񂾂���ȁD�D�D�U�P�҂߁I
	 */
	public static final NpcStringId S1DONT_THINK_YOUVE_WON_YOUR_DARK_SHADOW_WILL_ALWAYS_FOLLOW_YOUHYPOCRITE;

	/**
	 * ID: 7150<br>
	 * Message: �e���v�� �i�C�g�Ƃ͐��E���������́B$s1�A���N�A�q���[�}���Ɖ߂����Ă��ꂳ���Y�ꂽ�̂��B
	 */
	public static final NpcStringId A_TEMPLE_KNIGHT_GUARDS_THE_MOTHER_TREE_S1_HAS_HUMAN_CONTACT_MADE_YOU_FORGET_THAT;

	/**
	 * ID: 7151<br>
	 * Message: ����͂��̕ӂɂ��Ă������B�����Y���ȁI���O��������Ă���҂����́A�����G���t��łڂ��w�ƂȂ邱�Ƃ��I
	 */
	public static final NpcStringId I_MUST_STOP_REMEMBER_THE_ONES_YOURE_PROTECTING_WILL_SOMEDAY_DEFEAT_THE_ELVES;

	/**
	 * ID: 7152<br>
	 * Message: �N�b�D�D�D����ŋM�l�����镨�͂Ȃ񂾁H���Ǖ��Q�̎���U��΂߂邾������Ȃ����I
	 */
	public static final NpcStringId WHAT_THAT_WILL_JUST_STRENGTHEN_THE_ENEMY;

	/**
	 * ID: 7153<br>
	 * Message: ���R�Ƃ����Q�q�n�̒����𗐂��ҁD�D�D$s1�Ɏ����I
	 */
	public static final NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1;

	/**
	 * ID: 7154<br>
	 * Message: ���A���̍��͐�N�̎������������A�n���V���l�̂��T�ցD�D�D
	 */
	public static final NpcStringId MY_SPIRIT_IS_RELEASING_FROM_THIS_SHELL_IM_GETTING_CLOSE_TO_HALISHA;

	/**
	 * ID: 7156<br>
	 * Message: ����ȏ�̐킢�͖��ʂ��D�D�D����ł����܂����I
	 */
	public static final NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE;

	/**
	 * ID: 7157<br>
	 * Message: ���͌Z�p�i�P�C�A�Ƃ͈Ⴄ�B�ނ��I�ߋ��̖S���I
	 */
	public static final NpcStringId IM_NOT_LIKE_MY_BROTHER_PANACEA_GHOST_OF_THE_PAST_BEGONE;

	/**
	 * ID: 7158<br>
	 * Message: $s1�I���̓G���t�����̎��ザ��Ȃ��񂾁I���Ɏ��݂��Ă���I
	 */
	public static final NpcStringId THE_ELVES_NO_LONGER_RULE_HELP_ME_S1;

	/**
	 * ID: 7159<br>
	 * Message: �U���̎���ɂ߂�ȁA$s1�I�����͒����������琶�܂ꂽ�����Ȃ񂾁I
	 */
	public static final NpcStringId DONT_GIVE_UP_S1_HE_A_DEMON_FROM_THE_PAST;

	/**
	 * ID: 7160<br>
	 * Message: �ւ�����āI$s1�̂悤�ɂ�������̒��ԂƉ߂����Ă����҂͒m���Ă���I�F�Ő��E�����Ƃ����ւ���I
	 */
	public static final NpcStringId BE_PROUD_S1_WE_PROTECT_THIS_WORLD_TOGETHER;

	/**
	 * ID: 7161<br>
	 * Message: ���āA�s���Ȃ��ẮB���̎d��������҂��Ă���B
	 */
	public static final NpcStringId I_HAVE_TO_GO_IVE_GOT_SOME_BUSINESS_TO_TAKE_CARE_OF;

	/**
	 * ID: 7162<br>
	 * Message: �����D�D�D�����𓦂������ςȂ��ƂɂȂ�I
	 */
	public static final NpcStringId UGH_DONT_LET_HIM_GET_AWAY;

	/**
	 * ID: 7163<br>
	 * Message: �Y���ȁB���E�����ƌ����^�̌ւ���B
	 */
	public static final NpcStringId DONT_FORGET_YOUR_PRIDE_YOURE_PROTECTING_THE_WORLD;

	/**
	 * ID: 7164<br>
	 * Message: ���������������B
	 */
	public static final NpcStringId HA_HA_HA;

	/**
	 * ID: 7165<br>
	 * Message: �P�P�P�P�P�P�P�B
	 */
	public static final NpcStringId KUH_HUH;

	/**
	 * ID: 7166<br>
	 * Message: ���D�D�D�O���D�D�D$s1�D�D�D�O�������D�D�D
	 */
	public static final NpcStringId AAH_KUHS1KUH_HUH;

	/**
	 * ID: 7167<br>
	 * Message: $s1�I���D�D�D�����D�D�D�������D�D�D
	 */
	public static final NpcStringId S1RE_MEM_UGHUH;

	/**
	 * ID: 7250<br>
	 * Message: $s1�A�����X����A���̉̂ɁB
	 */
	public static final NpcStringId S1_YOUD_BETTER_LISTEN;

	/**
	 * ID: 7251<br>
	 * Message: �ӂ��A������鎞�Ԃ��ȁB���͂���Ȃ���B
	 */
	public static final NpcStringId HUH_ITS_CURTAIN_TIME_I_WONT_GET_ANY_MONEY;

	/**
	 * ID: 7252<br>
	 * Message: �����D�D�D���̕��䂪�D�D�D
	 */
	public static final NpcStringId UGHIT_CANT_BE;

	/**
	 * ID: 7257<br>
	 * Message: ���������B�����������Ȃ��I�V�g�i���V�X�I
	 */
	public static final NpcStringId YOU_WONT_GET_AWAY_THIS_TIME_NARCISSUS;

	/**
	 * ID: 7258<br>
	 * Message: �U�����D�D�D���܂�Ȃ��H$s1�I���݂��Ă���I
	 */
	public static final NpcStringId S1_HELP_ME;

	/**
	 * ID: 7259<br>
	 * Message: �����A$s1�����ꏏ�ɁI�����҂��v�����Ȃ��̂́A�̂��Ă������������I
	 */
	public static final NpcStringId YOU_MUST_BE_AWARE_OF_YOUR_AUDIENCE_WHEN_SINGING_S_JOIN_US_S1_A_SONG_THAT_NOBODY_LISTENS_TO_IS_EMPTY;

	/**
	 * ID: 7260<br>
	 * Message: �������C���o���āA$s1�I���Ə����ŏ�������ɂł��܂��I
	 */
	public static final NpcStringId YOU_MUST_WORK_HARDER_TO_BE_VICTORIOUS_S1;

	/**
	 * ID: 7261<br>
	 * Message: ���̉̂͏I���܂����B���ʂ�̎��Ԃł��ˁB
	 */
	public static final NpcStringId MY_SONG_IS_OVER_I_MUST_GO_GOODBYE;

	/**
	 * ID: 7262<br>
	 * Message: �����Ƃ́I
	 */
	public static final NpcStringId HOW_COULD_I_MISS;

	/**
	 * ID: 7263<br>
	 * Message: �̂Ƃ͒��a�B�ꏏ�ɂ��邱�ƁB�����Y��Ȃ��ŁD�D�D
	 */
	public static final NpcStringId DONT_FORGET_SONG_COMES_WITH_HARMONY;

	/**
	 * ID: 7264<br>
	 * Message: �̂����B���ׂĂ̎҂����Ȃ��̉̐��Ɏ����X����ł��傤�B
	 */
	public static final NpcStringId SING_EVERYONE_WILL_LISTEN;

	/**
	 * ID: 7265<br>
	 * Message: �N�͎��̏j�����󂯂鎑�i����Ȃ��ȁB
	 */
	public static final NpcStringId YOU_DONT_DESERVE_MY_BLESSING;

	/**
	 * ID: 7266<br>
	 * Message: $s1�H�N�܂ł����̏j�������ނ̂��B
	 */
	public static final NpcStringId DO_YOU_REJECT_MY_BLESSING_S1;

	/**
	 * ID: 7267<br>
	 * Message: �����D�D�D$s1�B�Ȃ��A�Ȃ��D�D�D�F���N���̂��邾�낤�ɁD�D�D
	 */
	public static final NpcStringId BUT_WHY_S1_EVERYONE_WOULD_PRAISE_YOU;

	/**
	 * ID: 7350<br>
	 * Message: $s1�I���ꂵ���̍U�������ɒʗp����Ǝv���̂��B���͕s���g���I���G���I
	 */
	public static final NpcStringId S1_ATTACK_ME_IM_IMMORTAL_IM_UNRIVALED;

	/**
	 * ID: 7351<br>
	 * Message: �N�N�N�D�D�D���͕s���g���B���Ȃǂ����񕜂���D�D�D���ɉ�������M�l�̖������B
	 */
	public static final NpcStringId HA_IM_IMMORTAL_THIS_SCAR_WILL_SOON_HEAL_YOULL_DIE_NEXT_TIME;

	/**
	 * ID: 7352<br>
	 * Message: �����������ƁH���̃\�[�h�}�X�^�[ �A�C�I�����I�H���e���X�A�񑩂�������Ȃ����A���ɉi���̖��������ƁI
	 */
	public static final NpcStringId METELLUS_YOU_PROMISED_ME_AN_IMMORTAL_LIFE_HOW_COULD_I_SWORDMASTER_IRON_LOSE;

	/**
	 * ID: 7357<br>
	 * Message: �ق��A�V�g�H���b�オ���肻�����ȁH
	 */
	public static final NpcStringId FALLEN_ANGEL_ITS_WORTH_TRYING;

	/**
	 * ID: 7358<br>
	 * Message: �����A$s1�I���Ă΂��肢�Ȃ��ňꏏ�ɂ�낤���I�����育�����̂��鑊�肾���H
	 */
	public static final NpcStringId HEY_S1_WHY_DONT_YOU_JOIN_ITS_YOUR_BEST_SHOT;

	/**
	 * ID: 7359<br>
	 * Message: $s1�A���񂽁A�i���̖��ɋ��������邩�B���͂���Ȃ��̂ɂዻ���Ȃ����ˁI
	 */
	public static final NpcStringId ARE_YOU_INTERESTED_IN_IMMORTAL_LIFE_S1_ITS_TOO_BORING_FOR_ME;

	/**
	 * ID: 7360<br>
	 * Message: $s1�A�������I���O�̘r�������Ă��I�����̒��Ŗ����ʂ������͂��I
	 */
	public static final NpcStringId EXCELLENT_S1_SHOW_ME_WHAT_YOU_LEARNED_WHEN_YOUR_LIFE_WAS_ON_THE_LINE;

	/**
	 * ID: 7361<br>
	 * Message: ���ꂶ��A����Ɏ��炷���B������\�ꂽ���A������Ƌx�܂Ȃ��ƂˁB
	 */
	public static final NpcStringId I_HAVE_TO_GO_TAKE_A_BREAK;

	/**
	 * ID: 7362<br>
	 * Message: �����A�������̂��I������ۂ����̂���|���Ȃ��̂��B���[�[�[���I�@
	 */
	public static final NpcStringId YOU_MISSED_WHAT_A_FOOL_YOU_ARE;

	/**
	 * ID: 7363<br>
	 * Message: �X�����̂Ȃ��퓬�A�댯�̂Ȃ��C�s�A�t���̂Ȃ������B����ɉ��̈Ӗ�������񂾁H
	 */
	public static final NpcStringId FIGHTING_WITHOUT_RISK_TRAINING_WITHOUT_DANGER_AND_GROWING_WITHOUT_HARDSHIP_WILL_ONLY_LEAD_TO_AN_INFLATED_EGO_DONT_FORGET;

	/**
	 * ID: 7364<br>
	 * Message: ���̓��e���X�B���O���]�ނȂ�i���̖���^���悤�B
	 */
	public static final NpcStringId DO_YOU_WANT_AN_IMMORTAL_LIFE;

	/**
	 * ID: 7365<br>
	 * Message: ������x�悭�l���Ă݂�񂾂ȁB�i���̖��A�s�k�̂Ȃ��������D�D�D
	 */
	public static final NpcStringId THINK_IT_OVER_AN_IMMORTAL_LIFE_AND_ASSURED_VICTORY;

	/**
	 * ID: 7366<br>
	 * Message: $s1�I���̋Z�͔��ɗD��Ă���B���̏j�����󂯂āA���̘r������w�����Ă݂Ȃ����B
	 */
	public static final NpcStringId THATS_GOOD_S1_DO_YOU_WANT_MY_BLESSING_TO_IMPROVE_YOUR_SKILLS;

	/**
	 * ID: 7367<br>
	 * Message: $s1�I�Ȃ��A�񑩂��ꂽ���������ނ̂��I
	 */
	public static final NpcStringId S1_WHY_DO_YOU_REJECT_GUARANTEED_VICTORY;

	/**
	 * ID: 7450<br>
	 * Message: $s1�A�_�̌䖼�ɂ����Ă��O�𔱂���I�����狭���Ƃ��A���S���𑊎�ɂ��邱�Ƃ͂ł��܂��I
	 */
	public static final NpcStringId IN_THE_NAME_OF_GODS_I_PUNISH_YOU_S1_YOU_CANT_RIVAL_US_ALL_NO_MATTER_HOW_STRONG_YOU_THINK_YOU_ARE;

	/**
	 * ID: 7451<br>
	 * Message: ����͌������Ă�邪�A���̗͂��ڊo�߂ʂ悤�ɂ��Ă���I
	 */
	public static final NpcStringId I_HAVE_TO_STOP_FOR_NOW_BUT_ILL_DEFEAT_THE_POWER_OF_THE_DRAGON_YET;

	/**
	 * ID: 7452<br>
	 * Message: �����D�D�D���O�͒m��Ȃ��̂��A�ڊo�߂Ă͂Ȃ�Ȃ��́D�D�D����́I
	 */
	public static final NpcStringId IT_ISTHE_POWER_THAT_SHOULDNT_LIVE;

	/**
	 * ID: 7457<br>
	 * Message: ���͂Ƃ�����A�V�g�ɂ��Ȃ��Đl�Ԃ����̏o�����ɁA���������`���`�������̂��ǂ����Ǝv���񂾂��H
	 */
	public static final NpcStringId ISNT_IT_UNWISE_FOR_AN_ANGEL_TO_INTERFERE_IN_HUMAN_AFFAIRS;

	/**
	 * ID: 7458<br>
	 * Message: ������ƌ������ł��ˁD�D�D$s1�A�]�T�����������`���Ă��炦�܂��H
	 */
	public static final NpcStringId THIS_IS_TOUGH_S1_WOULD_YOU_HELP_ME;

	/**
	 * ID: 7459<br>
	 * Message: $s1�I�����͗��̌����󂯌p�����҂͂��ׂČx�����Ă��܂��I���Ȃ�����O�ł͂���܂���I
	 */
	public static final NpcStringId S1_HES_KEEPING_AN_EYE_ON_ALL_THOSE_IN_SUCCESSION_TO_THE_BLOOD_OF_DRAGONS_INCLUDING_YOU;

	/**
	 * ID: 7460<br>
	 * Message: �G�̔w������̂ł��A$s1�I�����|�ꂽ��A���͂��Ȃ��̔Ԃł��I
	 */
	public static final NpcStringId ATTACK_THE_REAR_S1_IF_IM_KILLED_YOURE_NEXT;

	/**
	 * ID: 7461<br>
	 * Message: ���l�̊Ⴊ���߂��܂��ˁA������Ƃ���ɂ��A�����Ȃ��Ƃ���ɂ��D�D�D�����͂����܂���ˁA���悤�Ȃ�B
	 */
	public static final NpcStringId I_CANT_STAY_ANY_LONGER_THERE_ARE_TOO_MANY_EYES_ON_US_FAREWELL;

	/**
	 * ID: 7462<br>
	 * Message: �������H�����ł����ˁA�ł��D�D�D
	 */
	public static final NpcStringId GET_AWAY_YOURE_STILL_ALIVE_BUT;

	/**
	 * ID: 7463<br>
	 * Message: �����邱�Ƃ̂ł��Ȃ��킢�Ȃ�A�ł������͂Ő������Ȃ��Ă͂Ȃ�܂���B���ꂪ���Ȃ킿�A���a�𐶂ޗB��̓��ł�����B
	 */
	public static final NpcStringId IF_WE_CANT_AVOID_THIS_FIGHT_WELL_NEED_GREAT_STRENGTH_ITS_THE_ONLY_WAY_TO_PEACE;

	/**
	 * ID: 7464<br>
	 * Message: �E�H�[���[�h�A���̌����󂯌p���ҁI���̋Z���p�����Ă͂Ȃ�Ȃ��I
	 */
	public static final NpcStringId WARLORD_YOULL_NEVER_LEARN_THE_TECHNIQUES_OF_THE_DRAGON;

	/**
	 * ID: 7465<br>
	 * Message: �N�b�D�D�D�Ȃ��ז�������̂��D�D�D���O�̕ꂾ���I
	 */
	public static final NpcStringId HEY_WHY_BOTHER_WITH_THIS_ISNT_IT_YOUR_MOTHERS_BUSINESS;

	/**
	 * ID: 7466<br>
	 * Message: $s1�I�M�l����N�ɔ�����|���̂��I�������V�[�����̎q�������̋Z���󂯌p���������炾�ȁI
	 */
	public static final NpcStringId S1_ARE_YOU_AGAINST_YOUR_MOTHERS_WISHES_YOURE_NOT_WORTHY_OF_THE_SECRETS_OF_SHILENS_CHILDREN;

	/**
	 * ID: 7467<br>
	 * Message: $s1�A���̋Z�͑f���炵���B��������u�ɂ��čł���������̕s�K�𐶂ގҁA���ꂪ���O�ɂȂ邾�낤�I
	 */
	public static final NpcStringId EXCELLENT_TECHNIQUE_S1_UNFORTUNATELY_YOURE_THE_ONE_DESTINED_FOR_TRAGEDY;

	/**
	 * ID: 7550<br>
	 * Message: $s1�I�����܂ł悭���ǂ��Ă����I���������̓I�[�N�̌��E�𒴂����I���l�ƌ��т��_��̗͂����邪�����I
	 */
	public static final NpcStringId S1_YOU_MAY_FOLLOW_ME_BUT_AN_ORC_IS_NO_MATCH_FOR_MY_GIANTS_STRENGTH;

	/**
	 * ID: 7551<br>
	 * Message: �����D�D�D�̂��D�D�D�����܂ł��D�D�D
	 */
	public static final NpcStringId KUHMY_BODY_FAILSTHIS_IS_THE_END;

	/**
	 * ID: 7552<br>
	 * Message: ���͕����ʁI���l�̗͂���ɓ��ꂽ���̎��́I�����������������I
	 */
	public static final NpcStringId HOW_COULD_I_LOSE_WITH_THE_POWERS_OF_A_GIANT_AARGH;

	/**
	 * ID: 7557<br>
	 * Message: ���ꂪ�G�Ȃ̂��B
	 */
	public static final NpcStringId THATS_THE_ENEMY;

	/**
	 * ID: 7558<br>
	 * Message: �ӂ�I$s1�A���Ă��邾���Ȃ̂��B
	 */
	public static final NpcStringId HMM_S1_WILL_YOU_JUST_STAND_THERE_DOING_NOTHING;

	/**
	 * ID: 7559<br>
	 * Message: $s1�A�����͗^��������̂���Ȃ��B�킢�ɐg�𓊂����҂����������𓾂鎑�i�����񂾁B
	 */
	public static final NpcStringId S1_NOTHING_RISKED_NOTHING_GAINED_ONLY_THOSE_WHO_FIGHT_ENJOY_THE_SPOILS_OF_VICTORY;

	/**
	 * ID: 7560<br>
	 * Message: ���͏��肶��Ȃ����B$s1�A���O�������v��Ȃ����B
	 */
	public static final NpcStringId A_SWORD_ISNT_JEWELRY_S1_DONT_YOU_AGREE;

	/**
	 * ID: 7561<br>
	 * Message: �ӂށA�킢�͂Ȃ��̂��B�������闝�R�͂Ȃ��ȁB
	 */
	public static final NpcStringId WITH_NO_FIGHT_I_HAVE_NO_REASON_TO_STAY;

	/**
	 * ID: 7562<br>
	 * Message: ���������D�D�D
	 */
	public static final NpcStringId MISS;

	/**
	 * ID: 7563<br>
	 * Message: ���炪���f�����킢�ɂ̂ݐg�𓊂���B����Ȃ��ƌ�����邱�ƂɂȂ邼�B
	 */
	public static final NpcStringId PICK_YOUR_BATTLES_WISELY_OR_YOULL_REGRET_IT;

	/**
	 * ID: 7564<br>
	 * Message: �� �� �� �I �� �J �� �� �� �l �� �� �� �� �� �� �� �� ���B
	 */
	public static final NpcStringId WHAT_A_FOOL_TO_CHALLENGE_THE_GIANT_OF_THE_OROKA_TRIBE;

	/**
	 * ID: 7565<br>
	 * Message: �G �l �� �M �[ �s �� ���B �� �U �� �� ���B
	 */
	public static final NpcStringId RUNNING_LOW_ON_STEAM_I_MUST_WITHDRAW;

	/**
	 * ID: 7566<br>
	 * Message: $s1 �K �[ �f �B �A �� �� �n �[ �N �� �| �� �� �ҁB
	 */
	public static final NpcStringId S1_YOURE_THE_ONE_WHO_DEFEATED_GUARDIAN_MUHARK;

	/**
	 * ID: 7567<br>
	 * Message: $s1�I�| �� �� �� �� �� �� �� �� �D�D�D
	 */
	public static final NpcStringId S1_I_MUST_SUCCEED;

	/**
	 * ID: 7650<br>
	 * Message: $s1�A���̃E���Y�ɒǂ��������͂����͔F�߂Ă�낤�B����ɂ��Ă��A�{���ɃA�Y���A�̗͂ɓ��B�������𑊎�ɐ킨���Ƃ����̂��B
	 */
	public static final NpcStringId S1_WOULD_YOU_FIGHT_URUZ_WHO_HAS_REACHED_THE_POWER_OF_AZIRA;

	/**
	 * ID: 7651<br>
	 * Message: �ӂ��D�D�D�܂��A�Y���A�̗͂����S�ɃR���g���[������͓̂���ȁB
	 */
	public static final NpcStringId I_CANT_HANDLE_THE_POWER_OF_AZIRA_YET_FIRST;

	/**
	 * ID: 7652<br>
	 * Message: ����ȁI�A�Y���A�̗͂��󂯌p���������A���̉e�ɒB��������������Ȃ�āD�D�D
	 */
	public static final NpcStringId THIS_CANT_BE_HAPPENING_I_HAVE_THE_POWER_OF_AZIRA_HOW_COULD_I_FALL_SO_EASILY;

	/**
	 * ID: 7657<br>
	 * Message: �A�Y���A�I�׈��ȉ���萶�܂ꂽ�ҁA�������̌��Œ��߂Ă�낤�I
	 */
	public static final NpcStringId AZIRA_BORN_FROM_THE_EVIL_FLAME_ILL_KILL_YOU_WITH_MY_BARE_HANDS;

	/**
	 * ID: 7658<br>
	 * Message: �����I$s1�I���O���J�o�^�� �t�o�C�̖���m���Ă���Ȃ�A���̎���Ɉꔭ���܂��Ă��I
	 */
	public static final NpcStringId S1_IN_THE_NAME_OF_KHAVATARI_HUBAI_STRIKE_THIS_EVIL_WITH_YOUR_FISTS;

	/**
	 * ID: 7659<br>
	 * Message: $s1�I���݌������I�S�����ɍ��߂āA���āI
	 */
	public static final NpcStringId S1_ATTACK_FROM_BOTH_SIDES_HIT_HARD;

	/**
	 * ID: 7660<br>
	 * Message: $s1�I���O�̐S�������������Ȃ�A�ԈႢ�Ȃ����̉��g�ɂ��ʗp���邾�낤�I
	 */
	public static final NpcStringId S1_STRIKE_WITH_ALL_YOUR_HEART_IT_MUST_WORK;

	/**
	 * ID: 7661<br>
	 * Message: ����I�H�����s���Ȃ���΁B��ł܂�����B
	 */
	public static final NpcStringId DAMN_ITS_TIME_TO_GO_UNTIL_NEXT_TIME;

	/**
	 * ID: 7662<br>
	 * Message: �������͉��̎���D�D�D���������͒��߂Ȃ����I
	 */
	public static final NpcStringId EVIL_SPIRIT_OF_FLAME_I_WONT_GIVE_UP;

	/**
	 * ID: 7663<br>
	 * Message: ��������ł��̂߂������̌��A�Y��Ȃ��悤�ɁI
	 */
	public static final NpcStringId MY_FIST_WORKS_EVEN_ON_THE_EVIL_SPIRIT_DONT_FORGET;

	/**
	 * ID: 7664<br>
	 * Message: �����ȃJ�o�^���D�D�D���O�����̋Z�̌��ł��鎄�ɁA���̗͂��ʗp����Ǝv���Ă���̂��B
	 */
	public static final NpcStringId FOOLISH_KHAVATARIDO_YOU_THINK_YOUR_POWER_WILL_WORK_ON_ME_IM_THE_SOURCE_OF_YOUR_MARTIAL_ART;

	/**
	 * ID: 7665<br>
	 * Message: �������V�т͂�߂ɂ��悤�D�D�D
	 */
	public static final NpcStringId NO_MORE_GAMES;

	/**
	 * ID: 7666<br>
	 * Message: $s1�A�J�o�^���̓�����ގ҂��B�����ǂ�ȑ��݂��m���Đ���Ă���̂��B
	 */
	public static final NpcStringId S1ARE_YOU_NEXT_AFTER_KHAVATARI_DO_YOU_KNOW_WHO_I_AM;

	/**
	 * ID: 7667<br>
	 * Message: $s1�A�J�V���D�D�D�܂��܂��̍U�����ȁB���̓��̂ł͑ς����Ȃ��قǁD�D�D
	 */
	public static final NpcStringId S1KASHU_NOT_A_BAD_ATTACK_I_CANT_HOLD_ON_MUCH_LONGER;

	/**
	 * ID: 7750<br>
	 * Message: $s1���A�A�J�����A���̍D�G��ɂ͂Ȃ�Ȃ��I�F�E�����I���ɂȂ�̂͂��̎����I
	 */
	public static final NpcStringId S1_AKKAN_YOU_CANT_BE_MY_RIVAL_ILL_KILL_EVERYTHING_ILL_BE_THE_KING;

	/**
	 * ID: 7751<br>
	 * Message: �n�n�n�n�I����͌������Ă�낤�I�M�l�̎��͂͂�[���킩�������I
	 */
	public static final NpcStringId HA_ILL_SHOW_MERCY_ON_YOU_THIS_TIME_I_KNOW_WELL_OF_YOUR_TECHNIQUE;

	/**
	 * ID: 7752<br>
	 * Message: �x�z�҂̌����󂯌p���������I�H����Ȗ��l�ɕ�����Ȃ�āI�H
	 */
	public static final NpcStringId I_HAVE_IN_ME_THE_BLOOD_OF_A_KING_HOW_COULD_I_LOSE;

	/**
	 * ID: 7757<br>
	 * Message: �\�N�D�D�D�Ȃ̂��I�H
	 */
	public static final NpcStringId ARE_YOUTYRANT;

	/**
	 * ID: 7758<br>
	 * Message: ���܂��Ȃ񂩉�����Ȃ��I�����̖\�N���I$s1�A�͂����킹�悤�I
	 */
	public static final NpcStringId YOURE_NOT_A_KING_YOURE_JUST_A_TYRANT_S1_WE_MUST_FIGHT_TOGETHER;

	/**
	 * ID: 7759<br>
	 * Message: ����Ȃ񂾂��獑���łт�񂾂낤�I$s1�A����Ȗ\��������Ă����Ȃ�����Ȃ����I�H
	 */
	public static final NpcStringId SUCH_RULE_IS_RUINING_THE_COUNTRY_S1_I_CANT_BEAR_THIS_TYRANNY_ANY_LONGER;

	/**
	 * ID: 7760<br>
	 * Message: $s1�A����΂�񂾁I�\���ɍR���̂͏�Ɏw���҂̖�ڂȂ񂾂��I
	 */
	public static final NpcStringId S1_LEADERS_MUST_ALWAYS_RESIST_TYRANNY;

	/**
	 * ID: 7761<br>
	 * Message: ������[�A�������߂����ȁB�������Ɨ���ɂ��߂������ē{��ꂻ�����ȁH
	 */
	public static final NpcStringId I_STAYED_TOO_LONG_ILL_BE_PUNISHED_FOR_BEING_AWAY_SO_LONG;

	/**
	 * ID: 7762<br>
	 * Message: �������̂��D�D�D�N�\�b�A���������l�N���Ȃ�͑����Ƃ��߂܂��Ă����Ȃ��ƁI
	 */
	public static final NpcStringId HE_GOT_AWAY_DAMMIT_WE_MUST_CATCH_THIS_DARK_SOUL;

	/**
	 * ID: 7763<br>
	 * Message: ����ɂ��Ă��A�����ĉ����H�������ɂȂ�ɂ͂ǂ�����΂����񂾁H���O���ꏏ�ɍl���Ă���Ȃ����B
	 */
	public static final NpcStringId WHAT_IS_A_KING_WHAT_MUST_ONE_DO_TO_BE_A_GOOD_KING_THINK_IT_OVER;

	/**
	 * ID: 7764<br>
	 * Message: ���̑O�ɂЂ��܂����I�����Ȗ���I
	 */
	public static final NpcStringId KNEEL_DOWN_BEFORE_ME_FOOLISH_PEOPLE;

	/**
	 * ID: 7765<br>
	 * Message: ���������ނ���鎞�������I���������ĉ���������I
	 */
	public static final NpcStringId ITS_TIME_FOR_THE_KING_TO_LEAVE_BOW_YOUR_HEAD_AND_SAY_GOODBYE;

	/**
	 * ID: 7766<br>
	 * Message: $s1�I���̎��ɋt�炤�̂��I���͎x�z�ҁI�x�z�Ƃ͗́I�����͂������x�z�Ɍq����̂��I
	 */
	public static final NpcStringId S1_YOU_DARE_TO_FIGHT_ME_A_KINGS_POWER_MUST_BE_GREAT_TO_RULE;

	/**
	 * ID: 7767<br>
	 * Message: �M�l���Ƃ��������a��Ƃ́I$s1�A���؂����ȁI
	 */
	public static final NpcStringId YOU_WOULD_FIGHT_THE_KING_S1_TRAITOR;

	/**
	 * ID: 7850<br>
	 * Message: �f�W���J�� �T���q�I$s1�A�T���q�̌� ���_�n�̈З͂�����I
	 */
	public static final NpcStringId TEJAKAR_SHARUHI_S1_ILL_SHOW_YOU_THE_POWER_OF_SHARUHI_MOUTH_MUDAHA;

	/**
	 * ID: 7851<br>
	 * Message: ���Ⴀ���������I�Â��ɂ��Ă���Ă΁I���O�̎u�͎����D�D�D�������A�ЂƂ܂��͌�ނ��I
	 */
	public static final NpcStringId AAARGH_MY_SOUL_WONT_KEEP_QUIET_NOW_I_MUST_TAKE_MY_LEAVE;

	/**
	 * ID: 7852<br>
	 * Message: ���߂��A�T���q�B���O�͎��̂��̂��I���̂��̂Ȃ񂾂������������I
	 */
	public static final NpcStringId NO_SHARUHI_YOURE_SOUL_IS_MINE;

	/**
	 * ID: 7857<br>
	 * Message: �f�W���J�� �I���J�I�f�W���J�� �h�D�_�[�}���I
	 */
	public static final NpcStringId TEJAKAR_OROCA_TEJAKAR_DUDA_MARA;

	/**
	 * ID: 7858<br>
	 * Message: $s1�A�i���~�Ɛ키�������������Ȃ�A���ƈꏏ�ɂ��̗썰����߂悤�I
	 */
	public static final NpcStringId S1_WE_MUST_FIGHT_THIS_SOUL_TOGETHER_TO_PREVENT_AN_EVERLASTING_WINTER;

	/**
	 * ID: 7859<br>
	 * Message: $s1�I���̗썰�͂��Ȃ��ɔ�������ҁI���Ȃ��̍U�����������߂邾�낤�I
	 */
	public static final NpcStringId S1_THE_SOUL_RESPONDS_TO_YOU_MAY_YOUR_ATTACK_QUIET_HIM;

	/**
	 * ID: 7860<br>
	 * Message: $s1�I�T���q����߂�񂾁I��͂��łɎ��̌������Ƃ͕����ʁI
	 */
	public static final NpcStringId S1_CALM_SHARUHI_HE_DOESNT_LISTEN_TO_ME_ANYMORE;

	/**
	 * ID: 7861<br>
	 * Message: �������������I���ɔM�����̉���̂���񂱂Ƃ��I
	 */
	public static final NpcStringId ITS_TIME_TO_GO_MAY_THE_ETERNAL_FLAME_BLESS_YOU;

	/**
	 * ID: 7862<br>
	 * Message: �ނ͋������D�D�D�c�O���D�D�D�c�O�D�D�D
	 */
	public static final NpcStringId HE_LEFTTHATS_TOO_BADTOO_BAD;

	/**
	 * ID: 7863<br>
	 * Message: �ނ�Â߂����̐S�A�Y�ꂸ�ɂȁD�D�D
	 */
	public static final NpcStringId DONT_FORGET_YOUR_STRONG_WILL_NOW;

	/**
	 * ID: 7864<br>
	 * Message: �V���A�A�A�b�I����2�x�ƒN�̎x�z���󂯂ʁI
	 */
	public static final NpcStringId HA_NOBODY_WILL_RULE_OVER_ME_ANYMORE;

	/**
	 * ID: 7865<br>
	 * Message: ���R�A���R�A���䂤�������������I
	 */
	public static final NpcStringId FREEDOM_FREEDOM_FREEDOM;

	/**
	 * ID: 7866<br>
	 * Message: $s1�A�������������Ă��ꂽ���O���A���ǂ͎�������߂悤�Ƃ��Ă���̂��ȁI�V���A�A�A�A�b�I
	 */
	public static final NpcStringId S1_YOU_RELEASED_ME_BUT_YOU_ALSO_WANT_TO_CATCH_ME_HA;

	/**
	 * ID: 7867<br>
	 * Message: $s1�A���O�͎��̂��Ƃ��v���Ă���Ă���̂��ȁD�D�D�悩�낤�D�D�D���O�Ɏ��̗͂�݂����D�D�D
	 */
	public static final NpcStringId S1MEALL_RIGHTILL_HELP_YOU;

	/**
	 * ID: 7950<br>
	 * Message: �ނ��I�����͐_���ւ����ꏊ���I
	 */
	public static final NpcStringId GET_OUT_OF_HERE_THIS_PLACE_IS_FORBIDDEN_BY_GOD;

	/**
	 * ID: 7951<br>
	 * Message: �A�C���n�U�[�h�l���������Ăт��B
	 */
	public static final NpcStringId EINHASAD_IS_CALLING_ME;

	/**
	 * ID: 7952<br>
	 * Message: �����E���Ƃ́D�D�D�_�̎􂢂��|���Ȃ��̂��B
	 */
	public static final NpcStringId YOU_KILLED_ME_ARENT_YOU_AFRAID_OF_GODS_CURSE;

	/**
	 * ID: 7953<br>
	 * Message: ���̕������D�D�D$s1�A���ɂ����̂��I
	 */
	public static final NpcStringId YOU_BOTHER_MY_MINIONS_S1_DO_YOU_WANT_TO_DIE;

	/**
	 * ID: 7954<br>
	 * Message: ����Ȃ͂����D�D�D�����D�D�D������Ƃ́B
	 */
	public static final NpcStringId WHAT_THE_HELL_I_LOST;

	/**
	 * ID: 7955<br>
	 * Message: �N���M�l�́I�ז�����ȁI
	 */
	public static final NpcStringId WHO_ARE_YOU_WHY_ARE_YOU_INTERFERING_IN_OUR_BUSINESS;

	/**
	 * ID: 7956<br>
	 * Message: �����D�D�D���������͗e�͂��ʂ��I
	 */
	public static final NpcStringId YOURE_STRONG_ILL_GET_YOU_NEXT_TIME;

	/**
	 * ID: 7957<br>
	 * Message: �܂�������ȁD�D�D���x�����͓|���Ă��I
	 */
	public static final NpcStringId WE_MEET_AGAIN_ILL_HAVE_YOU_THIS_TIME;

	/**
	 * ID: 7958<br>
	 * Message: ��͂苭���D�D�D�����A$s1�A������`���Ă���B
	 */
	public static final NpcStringId A_WORTHY_OPPONENT_S1_HELP_ME;

	/**
	 * ID: 7959<br>
	 * Message: $s1�I���������O�ɋ}���B
	 */
	public static final NpcStringId S1_HURRY_BEFORE_HE_GETS_AWAY;

	/**
	 * ID: 7960<br>
	 * Message: �E���I
	 */
	public static final NpcStringId ILL_KILL_YOU;

	/**
	 * ID: 7961<br>
	 * Message: �����荇�킹�肤���B
	 */
	public static final NpcStringId WHY_DONT_YOU_FIGHT_ME_SOMEDAY;

	/**
	 * ID: 7962<br>
	 * Message: �܂��������̂��I�������I
	 */
	public static final NpcStringId I_MISSED_AGAIN_DAMMIT;

	/**
	 * ID: 7963<br>
	 * Message: �����܂���邾�낤�B
	 */
	public static final NpcStringId IM_SURE_WELL_MEET_AGAIN_SOMEDAY;

	/**
	 * ID: 7964<br>
	 * Message: �_�ɋt�炤�҂ɓV�����I
	 */
	public static final NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS;

	/**
	 * ID: 7966<br>
	 * Message: �_�̎g�҂ł��邱�̎��ɂ������Ă���Ƃ́I
	 */
	public static final NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS;

	/**
	 * ID: 7967<br>
	 * Message: $s1�I�Y��Ȃ����I
	 */
	public static final NpcStringId S1_I_WONT_FORGET_YOU;

	/**
	 * ID: 8050<br>
	 * Message: $s1�A�_���Ȓn�������Ƃ́I
	 */
	public static final NpcStringId S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE;

	/**
	 * ID: 8051<br>
	 * Message: ���傫�Ȕ����������O�ɋ��邪�����B
	 */
	public static final NpcStringId LEAVE_BEFORE_YOU_ARE_SEVERELY_PUNISHED;

	/**
	 * ID: 8052<br>
	 * Message: �A�C���n�U�[�h�l�A�������̂ĂȂ��ł��������I
	 */
	public static final NpcStringId EINHASAD_DONT_GIVE_UP_ON_ME;

	/**
	 * ID: 8053<br>
	 * Message: ����T���Ă���̂́A$s1�A���O���B
	 */
	public static final NpcStringId S1_SO_YOURE_THE_ONE_WHOS_LOOKING_FOR_ME;

	/**
	 * ID: 8054<br>
	 * Message: ���E�̐������Ȃǂɂ����Ƃ́D�D�D
	 */
	public static final NpcStringId A_MERE_MORTAL_HAS_DEFEATED_ME;

	/**
	 * ID: 8055<br>
	 * Message: �N�b�A�킢�̎ז�������Ƃ͔ڋ��ȁB
	 */
	public static final NpcStringId HOW_COWARDLY_TO_INTRUDE_IN_OTHER_PEOPLES_BUSINESS;

	/**
	 * ID: 8056<br>
	 * Message: ���Ԃ��B
	 */
	public static final NpcStringId TIME_IS_UP;

	/**
	 * ID: 8057<br>
	 * Message: ���̌����󂯂Ă݂�I
	 */
	public static final NpcStringId ILL_KILL_YOU_WITH_MY_SWORD;

	/**
	 * ID: 8058<br>
	 * Message: �����Ă���I
	 */
	public static final NpcStringId HELP_ME;

	/**
	 * ID: 8059<br>
	 * Message: �������ȁI
	 */
	public static final NpcStringId DONT_MISS;

	/**
	 * ID: 8060<br>
	 * Message: �����Ɖ������߂�I
	 */
	public static final NpcStringId KEEP_PUSHING;

	/**
	 * ID: 8061<br>
	 * Message: �����A������߂܂��ČN�����̍��݂𐰂炵�Ă�낤�B
	 */
	public static final NpcStringId ILL_GET_HIM_YOULL_HAVE_YOUR_REVENGE;

	/**
	 * ID: 8062<br>
	 * Message: �܂���𓦂������̂��B�K�����O��Еt���Ă��B
	 */
	public static final NpcStringId I_MISSED_HIM_AGAIN_ILL_KILL_YOU;

	/**
	 * ID: 8063<br>
	 * Message: ���͂��ǂ��B
	 */
	public static final NpcStringId I_MUST_FOLLOW_HIM;

	/**
	 * ID: 8150<br>
	 * Message: $s1�A�_�̓{��������Ȃ狎�邪�����I
	 */
	public static final NpcStringId S1_YOU_SHOULD_LEAVE_IF_YOU_FEAR_GODS_WRATH;

	/**
	 * ID: 8151<br>
	 * Message: ����Ȏ��ɉ��̗p���H
	 */
	public static final NpcStringId WHATS_GOING_ON;

	/**
	 * ID: 8152<br>
	 * Message: �܂�����ƂɂȂ邾�낤�I
	 */
	public static final NpcStringId ILL_SEE_YOU_AGAIN;

	/**
	 * ID: 8153<br>
	 * Message: ���O�͉��҂��H�Ȃ����̕�����ɂ߂���̂��H
	 */
	public static final NpcStringId WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS;

	/**
	 * ID: 8154<br>
	 * Message: ���߂��I
	 */
	public static final NpcStringId NO_WAY;

	/**
	 * ID: 8155<br>
	 * Message: �N���M�l�́I�ז�����ȁI
	 */
	public static final NpcStringId WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS;

	/**
	 * ID: 8156<br>
	 * Message: �M�l�͉��҂��H���E�̐������������܂ŋ����Ƃ́D�D�D
	 */
	public static final NpcStringId WHO_ARE_YOU_HOW_CAN_A_CREATURE_FROM_THE_NETHERWORLD_BE_SO_POWERFUL;

	/**
	 * ID: 8157<br>
	 * Message: ����ōŌォ�D�D�D
	 */
	public static final NpcStringId IS_THIS_THE_END;

	/**
	 * ID: 8158<br>
	 * Message: ���O�̘r�������Ă݂�B������|���񂾁I
	 */
	public static final NpcStringId SHOW_ME_WHAT_YOURE_MADE_OF_KILL_HIM;

	/**
	 * ID: 8159<br>
	 * Message: ���̒��x�̘r�ł��|����Ǝv���Ă���̂��B
	 */
	public static final NpcStringId YOU_THINK_YOU_CAN_GET_HIM_WITH_THAT;

	/**
	 * ID: 8160<br>
	 * Message: �����A����΂��I��������悤�Ƃ��Ă邶��Ȃ����B
	 */
	public static final NpcStringId PULL_YOURSELF_TOGETHER_HES_TRYING_TO_GET_AWAY;

	/**
	 * ID: 8161<br>
	 * Message: �u���b�N �L���b�g�ɓ`����B�؂�͕Ԃ����ƁB
	 */
	public static final NpcStringId TELL_THE_BLACK_CAT_THAT_I_GOT_HIS_PAID_BACK;

	/**
	 * ID: 8162<br>
	 * Message: �u���b�N �L���b�g�̂�A�������ނ��낤�ȁB
	 */
	public static final NpcStringId BLACK_CAT_HELL_BLAME_ME;

	/**
	 * ID: 8163<br>
	 * Message: ����ɂĎ���B
	 */
	public static final NpcStringId I_GOTTA_GO_NOW;

	/**
	 * ID: 8166<br>
	 * Message: �_�̌䖼�ɂ����Ă��O���n������B
	 */
	public static final NpcStringId ILL_KILL_YOU_IN_THE_NAME_OF_GOD;

	/**
	 * ID: 8167<br>
	 * Message: $s1�I�܂�����I
	 */
	public static final NpcStringId S1_SEE_YOU_LATER;

	/**
	 * ID: 8251<br>
	 * Message: ���傫�Ȕ����������O�ɋ��邪�����B
	 */
	public static final NpcStringId GET_OUT_BEFORE_YOURE_PUNISHED;

	/**
	 * ID: 8252<br>
	 * Message: �A�C���n�U�[�h�l�A�������̂ĂȂ��ł��������I
	 */
	public static final NpcStringId EINHASAD_PLEASE_DONT_GIVE_UP_ON_ME;

	/**
	 * ID: 8253<br>
	 * Message: ����T���Ă���̂́A$s1�A���O���B
	 */
	public static final NpcStringId S1_ARE_YOU_LOOKING_FOR_ME;

	/**
	 * ID: 8254<br>
	 * Message: ���E�̐������Ȃǂɂ����Ƃ́D�D�D
	 */
	public static final NpcStringId A_MERE_MORTAL_IS_KILLING_ME;

	/**
	 * ID: 8256<br>
	 * Message: ���E�̐�������A����Ŏ��̈̑傳���킩�������B
	 */
	public static final NpcStringId MORTAL_DONT_YOU_RECOGNIZE_MY_GREATNESS;

	/**
	 * ID: 8257<br>
	 * Message: ���x�����M�l��K���|���Ă��B
	 */
	public static final NpcStringId ILL_GET_YOU_THIS_TIME;

	/**
	 * ID: 8258<br>
	 * Message: �̂̐킢�ŕ������Ï����u���B�����A$s1�I�������ꏏ�ɓ|�������B
	 */
	public static final NpcStringId ILL_NEVER_FORGET_THE_TASTE_OF_HIS_STEEL_S1_LETS_FIGHT_HIM_TOGETHER;

	/**
	 * ID: 8259<br>
	 * Message: $s1�I����΂��I���̂܂܂��Ⴀ���𓦂��Ă��܂��I
	 */
	public static final NpcStringId S1_PULL_YOURSELF_TOGETHER_WELL_MISS_HIM;

	/**
	 * ID: 8260<br>
	 * Message: $s1�I��ɓ�����ꂻ�����B
	 */
	public static final NpcStringId S1_HES_TRYING_TO_GET_AWAY;

	/**
	 * ID: 8261<br>
	 * Message: �܂����������B�����������K���D�D�D
	 */
	public static final NpcStringId I_MISSED_AGAIN_NEXT_TIME;

	/**
	 * ID: 8262<br>
	 * Message: �������D�D�D�܂����s���B
	 */
	public static final NpcStringId DAMMIT_FAILED_AGAIN;

	/**
	 * ID: 8353<br>
	 * Message: ����T���Ă���̂́A$s1�A���O���B
	 */
	public static final NpcStringId YOU_ARE_THE_ONE_WHOS_LOOKING_FOR_ME_S1;

	/**
	 * ID: 8354<br>
	 * Message: ���E�̐������Ȃǂɂ����Ƃ́D�D�D
	 */
	public static final NpcStringId A_MERE_MORTAL_HAS_KILLED_ME;

	/**
	 * ID: 8355<br>
	 * Message: �N�b�D�D�D�M�l�͉��҂��H�Ȃ��킢�̎ז�������񂾁I
	 */
	public static final NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS;

	/**
	 * ID: 8359<br>
	 * Message: $s1�I�����Ɠ��񒣂�B
	 */
	public static final NpcStringId S1_PULL_YOURSELF_TOGETHER;

	/**
	 * ID: 8360<br>
	 * Message: $s1�I��ɓ�����ꂻ�����B
	 */
	public static final NpcStringId S1_HELL_GET_AWAY;

	/**
	 * ID: 8452<br>
	 * Message: �A�C���n�U�[�h�l�A�������̂ĂȂ��ł��������I
	 */
	public static final NpcStringId EINHASAD_PLEASE_DONT_FORSAKE_ME;

	/**
	 * ID: 8453<br>
	 * Message: ����T���Ă���̂́A$s1�A���O���B
	 */
	public static final NpcStringId LOOKING_FOR_ME_S1;

	/**
	 * ID: 8550<br>
	 * Message: $s1�I�r�V���b�v����_�̌�S�ɔw���Ƃ́B�����������B
	 */
	public static final NpcStringId S1_BISHOP_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD;

	/**
	 * ID: 8551<br>
	 * Message: �v���̂ق��M�S����������ȁB�܂�����B
	 */
	public static final NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_PAY_YOU_BACK_NEXT_TIME;

	/**
	 * ID: 8552<br>
	 * Message: �����I�^�i�L�A�l�B���Ȃ��̎u�𐬂��������Ȃ������������������������I
	 */
	public static final NpcStringId TANAKIA_FORGIVE_ME_I_COULDNT_FULFILL_YOUR_DREAM;

	/**
	 * ID: 8553<br>
	 * Message: ����T���Ȃ��畔�����������т��Ă���Ƃ����̂�$s1�A���O���B
	 */
	public static final NpcStringId S1_YOU_ARE_THE_WON_WHOS_BEEN_BOTHERING_MY_MINIONS;

	/**
	 * ID: 8554<br>
	 * Message: �����D�D�D�M�l���Ƃ��Ɏ���������Ƃ́B
	 */
	public static final NpcStringId DAMN_YOUVE_BEATEN_ME;

	/**
	 * ID: 8555<br>
	 * Message: �킢���ז����邨�O�͉��҂��I�H�ڋ��Ȃ�߁B
	 */
	public static final NpcStringId WHO_ARE_YOU_THIS_ISNT_YOUR_BUSINESS_COWARD;

	/**
	 * ID: 8556<br>
	 * Message: �ق��A�v��������邶��Ȃ����B�����y���܂��Ă��ꂽ����ɁA����͌������Ă�낤�B
	 */
	public static final NpcStringId HOW_WEAK_ILL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH;

	/**
	 * ID: 8557<br>
	 * Message: �v������苭����ł��ˁB�������A���������キ�͂Ȃ��ł���B
	 */
	public static final NpcStringId YOU_ARE_STRONGER_THAN_I_THOUGHT_BUT_IM_NO_WEAKLING;

	/**
	 * ID: 8558<br>
	 * Message: �育�킢����ł��ˁB$s1�I���Ɨ͂����킹�Ă��̎҂�|���܂��傤�B
	 */
	public static final NpcStringId HES_GOT_A_TOUGH_SHELL_S1_LETS_FIGHT_TOGETHER_AND_CRACK_HIS_SKULL;

	/**
	 * ID: 8560<br>
	 * Message: $s1�I�{���̗͂𔭊����Ȃ��Ƃ�����|���܂���I�����I
	 */
	public static final NpcStringId S1_WE_WONT_BEAT_HIM_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON;

	/**
	 * ID: 8561<br>
	 * Message: �ł́A���͂��̎҂ɂ��čs���܂��B
	 */
	public static final NpcStringId ILL_FOLLOW_HIM;

	/**
	 * ID: 8562<br>
	 * Message: �܂����������B�ǂ�������̂͑�ς������B
	 */
	public static final NpcStringId I_MISSED_AGAIN_HES_HARD_TO_FOLLOW;

	/**
	 * ID: 8563<br>
	 * Message: �ł́A�����̎p�Ɋ��҂��Ă��܂��B
	 */
	public static final NpcStringId WELL_SEE_WHAT_THE_FUTURE_BRINGS;

	/**
	 * ID: 8564<br>
	 * Message: �V�[�����l�̂��߂ɁI
	 */
	public static final NpcStringId FOR_SHILEN;

	/**
	 * ID: 8565<br>
	 * Message: ���͖߂��Ă���B���̎��ɂ͊o�傷�邪�����B
	 */
	public static final NpcStringId ILL_BE_BACK_ILL_DEAL_WITH_YOU_THEN;

	/**
	 * ID: 8566<br>
	 * Message: $s1�I���̎��ɋt�炤�̂��I
	 */
	public static final NpcStringId S1_ARE_YOU_GOING_TO_FIGHT_ME;

	/**
	 * ID: 8567<br>
	 * Message: $s1�I�o���Ă���B�M�l�̂��Ƃ͐�ΖY��Ȃ��B
	 */
	public static final NpcStringId S1_ILL_PAY_YOU_BACK_I_WONT_FORGET_YOU;

	/**
	 * ID: 8650<br>
	 * Message: $s1�I�v���t�B�b�g����_�̌�S�ɔw���Ƃ́B�����������B
	 */
	public static final NpcStringId S1_PROPHET_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD;

	/**
	 * ID: 8651<br>
	 * Message: �v���̂ق��M�S����������ȁB�܂�����B
	 */
	public static final NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_DEAL_WITH_YOU_NEXT_TIME;

	/**
	 * ID: 8653<br>
	 * Message: ����T���Ȃ��畔�����������т��Ă���Ƃ����̂�$s1�A���O���B
	 */
	public static final NpcStringId ARE_YOU_THE_ONE_WHOS_BEEN_BOTHERING_MY_MINIONS_S1;

	/**
	 * ID: 8654<br>
	 * Message: �����D�D�D�M�l���Ƃ��Ɏ���������Ƃ́B
	 */
	public static final NpcStringId DAMN_I_CANT_BELIEVE_IVE_BEEN_BEATEN_BY_YOU;

	/**
	 * ID: 8655<br>
	 * Message: �킢���ז����邨�O�͉��҂��I�H�ڋ��Ȃ�߁B
	 */
	public static final NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS_COWARD;

	/**
	 * ID: 8657<br>
	 * Message: ���̗͂ŁA���ɂ͂т���ł�ނ��邾�낤�B
	 */
	public static final NpcStringId ILL_DESTROY_THE_DARKNESS_SURROUNDING_THE_WORLD_WITH_THE_POWER_OF_LIGHT;

	/**
	 * ID: 8658<br>
	 * Message: $s1�I���ƈꏏ�ɂ��̑������҂�|���āA�^�Ȃ���̗͂������Ă��܂��傤�B
	 */
	public static final NpcStringId S1_FIGHT_THE_FALLEN_ANGEL_WITH_ME_SHOW_THE_TRUE_POWER_OF_LIGHT;

	/**
	 * ID: 8659<br>
	 * Message: $s1�I�����Ă��������B���̐킢�͂����ŏI��点�Ȃ��Ă͂Ȃ�܂���B
	 */
	public static final NpcStringId S1_GO_WE_MUST_STOP_FIGHTING_HERE;

	/**
	 * ID: 8660<br>
	 * Message: $s1�I�������͐�Ε������Ȃ��̂ł��B����H�����΂��Đ키�̂ł��B
	 */
	public static final NpcStringId WE_MUSTNT_LOSE_S1_PULL_YOURSELF_TOGETHER;

	/**
	 * ID: 8661<br>
	 * Message: �ł́A����������΂܂�������܂��傤�B
	 */
	public static final NpcStringId WELL_MEET_AGAIN_IF_FATE_WILLS_IT;

	/**
	 * ID: 8662<br>
	 * Message: �����������ڋ��Ȉ����̌��ǂ��܂��B
	 */
	public static final NpcStringId ILL_FOLLOW_THE_COWARDLY_DEVIL;

	/**
	 * ID: 8750<br>
	 * Message: $s1�I�G���_�[����_�̌�S�ɔw���Ƃ́B�����������B
	 */
	public static final NpcStringId S1_ELDER_ITS_FOOLISH_OF_YOU_TO_GO_AGAINST_THE_WILL_OF_THE_GODS;

	/**
	 * ID: 8757<br>
	 * Message: �v������苭����ł��ˁB�������A���������キ�͂Ȃ��ł���B
	 */
	public static final NpcStringId YOURE_STRONGER_THAN_I_THOUGHT_BUT_IM_NO_WEAKLING_EITHER;

	/**
	 * ID: 8760<br>
	 * Message: $s1�I�{���̗͂𔭊����Ȃ��Ƃ�����|���܂���I�����I
	 */
	public static final NpcStringId S1_WELL_NEVER_WIN_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON;

	/**
	 * ID: 8850<br>
	 * Message: �M�l��$s1�H�t�b�A�����΂͎��������Ă��邼�I
	 */
	public static final NpcStringId ARE_YOU_S1_OH_I_HAVE_THE_RESONANCE_AMULET;

	/**
	 * ID: 8851<br>
	 * Message: �������A�v������肵�ԂƂ��ȁB����͂��̕ӂŌ������Ă�낤�B
	 */
	public static final NpcStringId YOURE_FEISTIER_THAN_I_THOUGHT_ILL_QUIT_HERE_FOR_TODAY;

	/**
	 * ID: 8852<br>
	 * Message: �������D�D�D���̎���������Ƃ́D�D�D
	 */
	public static final NpcStringId AAARGH_I_CANT_BELIEVE_I_LOST;

	/**
	 * ID: 8854<br>
	 * Message: �A�����D�D�D
	 */
	public static final NpcStringId YIKES_YOURE_TOUGH;

	/**
	 * ID: 8855<br>
	 * Message: �Ȃ��킢�̎ז�������̂��D�D�D
	 */
	public static final NpcStringId WHY_DO_YOU_INTERFERE_IN_OTHER_PEOPLES_BUSINESS;

	/**
	 * ID: 8856<br>
	 * Message: �����͂��̕ӂň���������Ƃ��悤�B
	 */
	public static final NpcStringId ILL_STOP_HERE_FOR_TODAY;

	/**
	 * ID: 8857<br>
	 * Message: ���x���������Ȃ����I
	 */
	public static final NpcStringId I_WONT_MISS_YOU_THIS_TIME;

	/**
	 * ID: 8858<br>
	 * Message: �����D�D�D��͂�1�l�ł͖������D�D�D$s1�I��`���Ă���I
	 */
	public static final NpcStringId DAMMIT_THIS_IS_TOO_HARD_BY_MYSELF_S1_GIVE_ME_A_HAND;

	/**
	 * ID: 8859<br>
	 * Message: $s1�I�}���A���̂܂܂��ᓦ���Ă��܂��B
	 */
	public static final NpcStringId S1_HURRY_UP_WELL_MISS_HIM;

	/**
	 * ID: 8860<br>
	 * Message: $s1�I�}���ł���B
	 */
	public static final NpcStringId S1_COME_ON_HURRY_UP;

	/**
	 * ID: 8861<br>
	 * Message: ����ɂĎ���B���͂�����ǂ�˂΂Ȃ�ʁB
	 */
	public static final NpcStringId I_GOTTA_GO_FOLLOW_HIM;

	/**
	 * ID: 8862<br>
	 * Message: �����A�����悤���Ă̂��B�҂āI
	 */
	public static final NpcStringId HEY_QUIT_RUNNING_STOP;

	/**
	 * ID: 8863<br>
	 * Message: ���ꂶ��A�܂��ˁ`
	 */
	public static final NpcStringId SEE_YOU_NEXT_TIME;

	/**
	 * ID: 8864<br>
	 * Message: �������H�M�l���Ƃ�������h����Ǝv���̂��B
	 */
	public static final NpcStringId WHAT_THINK_YOU_CAN_GET_IN_MY_WAY;

	/**
	 * ID: 8865<br>
	 * Message: �ӂ��A���ȁB���Ⴀ�ȁD�D�D
	 */
	public static final NpcStringId YOU_ARE_SO_WEAK_I_GOTTA_GO_NOW;

	/**
	 * ID: 8866<br>
	 * Message: �ށH�M�l��$s1�I���x�����A�ꏏ�Ɏn�����Ă��B
	 */
	public static final NpcStringId S1_GOOD_ILL_HELP_YOU;

	/**
	 * ID: 8867<br>
	 * Message: $s1�A�ƌ��������ȁB�v������苭���ȁB�܂�����B
	 */
	public static final NpcStringId S1_YOURE_STRONGER_THAN_I_THOUGHT_SEE_YOU_NEXT_TIME;

	/**
	 * ID: 8951<br>
	 * Message: �������A�v������肵�ԂƂ��ȁB����͂��̕ӂŌ������Ă�낤�B
	 */
	public static final NpcStringId YOURE_FEISTIER_THAN_I_THOUGHT_ILL_STOP_HERE_TODAY;

	/**
	 * ID: 8952<br>
	 * Message: �������D�D�D���̎���������Ƃ́D�D�D
	 */
	public static final NpcStringId AARGH_I_CANT_BELIEVE_I_LOST;

	/**
	 * ID: 8956<br>
	 * Message: �����͂��̕ӂň���������Ƃ��悤�B
	 */
	public static final NpcStringId ILL_STOP_HERE_TODAY;

	/**
	 * ID: 8958<br>
	 * Message: �����D�D�D��͂�1�l�ł͖������D�D�D$s1�I��`���Ă���I
	 */
	public static final NpcStringId DAMN_ITS_TOO_MUCH_BY_MYSELFS1_GIVE_ME_A_HAND;

	/**
	 * ID: 8959<br>
	 * Message: $s1�I�}���A���̂܂܂��ᓦ���Ă��܂��B
	 */
	public static final NpcStringId S1_HURRY_WELL_MISS_HIM;

	/**
	 * ID: 8960<br>
	 * Message: $s1�I�}���ł���B
	 */
	public static final NpcStringId S1_HURRY_PLEASE;

	/**
	 * ID: 8961<br>
	 * Message: ����ɂĎ���B���͂�����ǂ�˂΂Ȃ�ʁB
	 */
	public static final NpcStringId I_GOTTA_GO_FOLLOW_HIM_NOW;

	/**
	 * ID: 8962<br>
	 * Message: �����A�����悤���Ă̂��B�҂āI
	 */
	public static final NpcStringId ARE_YOU_RUNNING_AWAY_STOP;

	/**
	 * ID: 8964<br>
	 * Message: �������H�M�l���Ƃ�������h����Ǝv���̂��B
	 */
	public static final NpcStringId DO_YOU_THINK_YOU_CAN_STOP_ME;

	/**
	 * ID: 8965<br>
	 * Message: �ӂ��A���ȁB���Ⴀ�ȁD�D�D
	 */
	public static final NpcStringId YOURE_SO_WEAK_I_GOTTA_GO_NOW;

	/**
	 * ID: 8966<br>
	 * Message: �ށH�M�l��$s1�I���x�����A�ꏏ�Ɏn�����Ă��B
	 */
	public static final NpcStringId YOURE_S1_GOOD_ILL_HELP_YOU;

	/**
	 * ID: 9050<br>
	 * Message: �M�l��$s1�H�t�b�A�����΂͎��������Ă��邼�I
	 */
	public static final NpcStringId ARE_YOU_S1_OH_I_HAVE_A_RESONANCE_AMULET;

	/**
	 * ID: 9051<br>
	 * Message: �������A�v������肵�ԂƂ��ȁB����͂��̕ӂŌ������Ă�낤�B
	 */
	public static final NpcStringId HEY_YOURE_MORE_TENACIOUS_THAN_I_THOUGHT_ILL_STOP_HERE_TODAY;

	/**
	 * ID: 9058<br>
	 * Message: �����D�D�D��͂�1�l�ł͖������D�D�D$s1�I��`���Ă���I
	 */
	public static final NpcStringId DAMMIT_I_CANT_DO_THIS_ALONE_S1_GIVE_ME_A_HAND;

	/**
	 * ID: 9059<br>
	 * Message: $s1�I�}���A���̂܂܂��ᓦ���Ă��܂��B
	 */
	public static final NpcStringId S1_HURRY_OR_WELL_MISS_HIM;

	/**
	 * ID: 9060<br>
	 * Message: $s1�I�}���ł���B
	 */
	public static final NpcStringId S1_HURRY_UP;

	/**
	 * ID: 9061<br>
	 * Message: ����ɂĎ���B���͂�����ǂ�˂΂Ȃ�ʁB
	 */
	public static final NpcStringId I_GOTTA_FOLLOW_HIM_NOW;

	/**
	 * ID: 9062<br>
	 * Message: �����A�����悤���Ă̂��B�҂āI
	 */
	public static final NpcStringId HEY_ARE_YOU_RUNNING_STOP;

	/**
	 * ID: 9066<br>
	 * Message: �ށH�M�l��$s1�I���x�����A�ꏏ�Ɏn�����Ă��B
	 */
	public static final NpcStringId OH_YOURE_S1_GOOD_ILL_HELP_YOU;

	/**
	 * ID: 9150<br>
	 * Message: �N�Y�̂悤�Ȑ���ǂ��ƌ�������ҁA$s1�I���O�ɐ��Ȃ�m�b����ɂ��鎑�i�͂Ȃ��I
	 */
	public static final NpcStringId YOU_CAROUSE_WITH_EVIL_SPIRITS_S1_YOURE_NOT_WORTHY_OF_THE_HOLY_WISDOM;

	/**
	 * ID: 9151<br>
	 * Message: �Ӓn���̂Ă�I����ȏ㎕�������Ă����ʂ��I
	 */
	public static final NpcStringId YOURE_SO_STUBBORN_I_CANT_BOSS_YOU_AROUND_ANY_MORE_CAN_I;

	/**
	 * ID: 9152<br>
	 * Message: �܁A�܂����I�q���[�}�����Ƃ��ɂ����Ƃ́I�H
	 */
	public static final NpcStringId HOW_COULD_IT_HAPPEN_DEFEATED_BY_A_HUMAN;

	/**
	 * ID: 9157<br>
	 * Message: ����l�l�̋��ɏ]���Ă����ɂ��I�L���b�g���̖��_��q���āA���Ȃ������܂��ɂ�I
	 */
	public static final NpcStringId MY_MASTER_SENT_ME_HERE_ILL_GIVE_YOU_A_HAND;

	/**
	 * ID: 9158<br>
	 * Message: ���ɂႠ���I$s1�����`��A�����Ă��I
	 */
	public static final NpcStringId MEOW_MASTER_S1_HELP_ME;

	/**
	 * ID: 9159<br>
	 * Message: $s1����I����x�����_����������߂Ȃ��悤�ɒ��炵�߂�ɂ�I
	 */
	public static final NpcStringId MASTER_S1_PUNISH_HIM_SO_HE_CANT_BOTHER_BELINDA;

	/**
	 * ID: 9160<br>
	 * Message: $s1����I���̂܂܂��ᓦ����ꂿ�Ⴄ�ɂ�I
	 */
	public static final NpcStringId MASTER_S1_WELL_MISS_HIM;

	/**
	 * ID: 9161<br>
	 * Message: �ɂႠ���`��I����l�l���Ă�ł�ɂ�I�o�C�o�C�I
	 */
	public static final NpcStringId MEOW_MY_MASTER_IS_CALLING_MEOW_I_GOTTA_GO_NOW;

	/**
	 * ID: 9162<br>
	 * Message: �ɂႤ�I�����Ă��܂����ɂ��I
	 */
	public static final NpcStringId MEOW_I_MISSED_HIM_MEOW;

	/**
	 * ID: 9163<br>
	 * Message: �K�^���F��܂��ɂ�I���ꂶ��܂��I
	 */
	public static final NpcStringId GOOD_LUCK_MEOW_I_GOTTA_GO_NOW;

	/**
	 * ID: 9164<br>
	 * Message: ���O�͂��낢��m�肷�����B��l��������ł��炨�����I
	 */
	public static final NpcStringId CURIOSITY_KILLED_THE_CAT_ILL_SHOW_YOU;

	/**
	 * ID: 9165<br>
	 * Message: �{���͂����܂ŁI
	 */
	public static final NpcStringId THATS_ALL_FOR_TODAY;

	/**
	 * ID: 9166<br>
	 * Message: �x�����_��D�����Ƃ��Ă���̂͋M�l���A$s1�H���˂��I
	 */
	public static final NpcStringId ARE_YOU_TRYING_TO_TAKE_BELINDA_FROM_ME_S1_ILL_SHOW_YOU;

	/**
	 * ID: 9167<br>
	 * Message: �x�A�x�����_�I�N�������Ă���I�O�t�b�I
	 */
	public static final NpcStringId BELINDA_I_LOVE_YOU_YIKES;

	/**
	 * ID: 9251<br>
	 * Message: �Ӓn���̂Ă�I����ȏ㎕�������Ă����ʂ��I
	 */
	public static final NpcStringId YOURE_STUBBORN_AS_A_MULE_GUESS_I_CANT_BOSS_YOU_AROUND_ANY_MORE;

	/**
	 * ID: 9252<br>
	 * Message: �܁A�܂����I�G���t���Ƃ��ɂ����Ƃ́I�H
	 */
	public static final NpcStringId HOW_COULD_IT_BEDEFEATED_BY_AN_ELF;

	/**
	 * ID: 9257<br>
	 * Message: ���f�B�X�l�� �Ō�� �v�O�ɂ�� ���Ȃ��� ����`���� ���܂����B
	 */
	public static final NpcStringId I_CAME_TO_HELP_YOU_ITS_THE_WILL_OF_RADYSS;

	/**
	 * ID: 9258<br>
	 * Message: $s1�� ���Ȃ��� �͂� �݂��� ���������B
	 */
	public static final NpcStringId S1_FIGHT_WITH_ME;

	/**
	 * ID: 9259<br>
	 * Message: $s1�� ��� �K�� �|���Ȃ��Ă� �Ȃ�܂���B
	 */
	public static final NpcStringId S1_WE_MUST_DEFEAT_HIM;

	/**
	 * ID: 9260<br>
	 * Message: $s1�� ���Ԃ� ����܂��� ���� ��� �|���Ȃ��ẮB
	 */
	public static final NpcStringId S1_THERES_NO_TIME_WE_MUST_DEFEAT_HIM;

	/**
	 * ID: 9261<br>
	 * Message: ���f�B�X�l�� ���� �Ă�ł����܂� ���� �s���Ȃ��ẮB
	 */
	public static final NpcStringId RADYSS_IS_CALLING_ME_I_GOTTA_GO_NOW;

	/**
	 * ID: 9262<br>
	 * Message: ������ �w�� ���� �����Ƃ� �ł��Ȃ������B
	 */
	public static final NpcStringId I_WAS_UNABLE_TO_AVENGE_MY_BROTHER;

	/**
	 * ID: 9263<br>
	 * Message: ���� �΂� �j���� ����񂱂Ƃ��B
	 */
	public static final NpcStringId MAY_YOU_BE_BLESSED;

	/**
	 * ID: 9264<br>
	 * Message: 遖��Ȏ҂�A�������߂�I�����Ȏ҂�A��邪�����I�ߐl��A��������I���������āI
	 */
	public static final NpcStringId THE_PROUD_REPENT_THE_FOOLISH_AWAKEN_SINNERS_DIE;

	/**
	 * ID: 9265<br>
	 * Message: ���̐��̎傪���Ăт��D�D�D�܍߂͂܂����x�ɂ��悤�D�D�D
	 */
	public static final NpcStringId HELLS_MASTER_IS_CALLING_ATONEMENT_WILL_HAVE_TO_WAIT;

	/**
	 * ID: 9266<br>
	 * Message: $s1�A���O�̖��O���ً��k�̃��X�g�ɍڂ��Ă����Ă��I
	 */
	public static final NpcStringId S1_ILL_REMEMBER_YOUR_NAME_HEATHEN;

	/**
	 * ID: 9267<br>
	 * Message: ���Ȃ�R���ɉ����ʎҁA$s1�I���̖��A�Y��ʂ��I
	 */
	public static final NpcStringId I_WONT_FORGET_THE_NAME_OF_ONE_WHO_DOESNT_OBEY_HOLY_JUDGMENT_S1;

	/**
	 * ID: 9351<br>
	 * Message: �Ӓn���̂Ă�I����ȏ㎕�������Ă����ʂ��I
	 */
	public static final NpcStringId YOURE_STUBBORN_AS_A_MULE_I_GUESS_I_CANT_BOSS_YOU_AROUND_ANY_MORE;

	/**
	 * ID: 9352<br>
	 * Message: �܁A�܂����I�_�[�N �G���t���Ƃ��ɂ����Ƃ́I�H
	 */
	public static final NpcStringId COULD_IT_BE_DEFEATED_BY_A_DARK_ELF;

	/**
	 * ID: 9357<br>
	 * Message: �e�̏����t��A�������ɗ����B
	 */
	public static final NpcStringId SHADOW_SUMMONER_I_CAME_HERE_TO_HELP_YOU;

	/**
	 * ID: 9358<br>
	 * Message: �e�̏����t�A$s1��I���Ȃ��̗͂�݂��Ă��������B
	 */
	public static final NpcStringId SHADOW_SUMMONER_S1_FIGHT_WITH_ME;

	/**
	 * ID: 9359<br>
	 * Message: $s1��A�������|���Ȃ���΂��Ȃ��������I
	 */
	public static final NpcStringId S1_YOULL_DIE_IF_YOU_DONT_KILL_HIM;

	/**
	 * ID: 9360<br>
	 * Message: �}���̂��A$s1��I��𓦂��Ă͂Ȃ�܂���I
	 */
	public static final NpcStringId HURRY_S1_DONT_MISS_HIM;

	/**
	 * ID: 9361<br>
	 * Message: ����ȏ���̂�ۂ��Ƃ͖����ł��D�D�D
	 */
	public static final NpcStringId I_CANT_HOLD_ON_ANY_LONGER;

	/**
	 * ID: 9362<br>
	 * Message: ���ǁD�D�D�����Ă��܂����D�D�D
	 */
	public static final NpcStringId AFTER_ALL_THATI_MISSED_HIM;

	/**
	 * ID: 9363<br>
	 * Message: �e�̏����t��I���ɐ[���̏j���̂���񂱂Ƃ��I
	 */
	public static final NpcStringId SHADOW_SUMMONER_MAY_YOU_BE_BLESSED;

	/**
	 * ID: 9364<br>
	 * Message: ����l�l�̖��ɂ��A���Ȃ��Ɏ���^���ɗ����I
	 */
	public static final NpcStringId MY_MASTER_SENT_ME_HERE_TO_KILL_YOU;

	/**
	 * ID: 9365<br>
	 * Message: �e�������Ă�ł���D�D�D
	 */
	public static final NpcStringId THE_SHADOW_IS_CALLING_ME;

	/**
	 * ID: 9366<br>
	 * Message: $s1�A�����ɂ����Ȃ��̂��I�]�݂Ƃ���΂����łɑ����Ă�낤�I
	 */
	public static final NpcStringId S1_YOU_WANT_TO_DIE_EARLY_ILL_SEND_YOU_TO_THE_DARKNESS;

	/**
	 * ID: 9367<br>
	 * Message: �e�𑀂�ҁA$s1��I���Ȃ��̖��O�͊o���Ă������I
	 */
	public static final NpcStringId YOU_DEAL_IN_DARKNESS_S1_ILL_PAY_YOU_BACK;

	/**
	 * ID: 9450<br>
	 * Message: �M�l�́D�D�D$s1�H�q���f�~�b�g�̓�̕��ɂ͂Ȃ�񂼁I
	 */
	public static final NpcStringId YOURE_S1_I_WONT_BE_LIKE_HINDEMITH;

	/**
	 * ID: 9451<br>
	 * Message: �������A�v������肵�ԂƂ��ȁB����͂��̕ӂŌ������Ă�낤�B
	 */
	public static final NpcStringId YOURE_FEISTIER_THAN_I_THOUGHT_ILL_STOP_HERE_FOR_TODAY;

	/**
	 * ID: 9453<br>
	 * Message: $s1�A�ߍ����̕������������т��Ă���̂͂��O���B
	 */
	public static final NpcStringId ARE_YOU_THE_ONE_WHO_IS_BOTHERING_MY_MINIONS_S1;

	/**
	 * ID: 9457<br>
	 * Message: ���O�ɔ�`�̃^�u���b�g�Ƃ̌����͂����ʁI�����΂�n���I
	 */
	public static final NpcStringId I_CANT_LET_YOU_COMMUNE_WITH_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET;

	/**
	 * ID: 9460<br>
	 * Message: $s1�I�}���ł���B
	 */
	public static final NpcStringId S1_PLEASE_HURRY;

	/**
	 * ID: 9461<br>
	 * Message: ����ɂĎ���B���͂�����ǂ�˂΂Ȃ�ʁB
	 */
	public static final NpcStringId I_MUST_FOLLOW_HIM_NOW;

	/**
	 * ID: 9462<br>
	 * Message: �����A�����悤���Ă̂��B�҂āI
	 */
	public static final NpcStringId ARE_YOU_RUNNING_STOP;

	/**
	 * ID: 9464<br>
	 * Message: �����H���؂�̂��B�������Ǝv������D�D�D�`�F�b�A�����̂Ƃ���͂���őނ��Ƃ��邩�B
	 */
	public static final NpcStringId ARE_YOU_BETRAYING_ME_I_THOUGHT_SOMETHING_WAS_WRONGILL_STOP_HERE;

	/**
	 * ID: 9466<br>
	 * Message: �M�l�́D�D�D$s1�H2�l�ɂȂ�������Ƃ����Ď����~�߂邱�Ƃ��ł���Ǝv���̂��B
	 */
	public static final NpcStringId YOURE_S1_EVEN_TWO_OF_YOU_CANT_STOP_ME;

	/**
	 * ID: 9467<br>
	 * Message: �������I�H���̌����΂��D�D�D$s1�A�o���Ă�B
	 */
	public static final NpcStringId DAMMIT_MY_RESONANCE_AMULETS1_ILL_NEVER_FORGET_TO_PAY_YOU_BACK;

	/**
	 * ID: 9550<br>
	 * Message: �M�l�́D�D�D$s1�H�o���g�V���^�C���̓�̕��ɂ͂Ȃ�񂼁I
	 */
	public static final NpcStringId ARE_YOU_S1_I_WONT_BE_LIKE_WALDSTEIN;

	/**
	 * ID: 9552<br>
	 * Message: �������D�D�D���̎���������Ƃ́D�D�D
	 */
	public static final NpcStringId YIKES_I_CANT_BELIEVE_I_LOST;

	/**
	 * ID: 9553<br>
	 * Message: $s1�A�ߍ����̕������������т��Ă���̂͂��O���B
	 */
	public static final NpcStringId ARE_YOU_THE_ONE_BOTHERING_MY_MINIONS_S1;

	/**
	 * ID: 9557<br>
	 * Message: ���O�ɔ�`�̃^�u���b�g�Ƃ̌����͂����ʁI�����΂�n���I
	 */
	public static final NpcStringId YOU_CANT_COMMUNE_WITH_THE_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET;

	/**
	 * ID: 9567<br>
	 * Message: �������I�H���̌����΂��D�D�D$s1�A�o���Ă�B
	 */
	public static final NpcStringId DAMMIT_MY_RESONANCE_AMULETS1_ILL_NEVER_FORGET_THIS;

	/**
	 * ID: 9650<br>
	 * Message: $s1�I�M�l���H�n���g�l�̍s�������ז��ł��Ȃ��悤���̍����~�߂Ă��B
	 */
	public static final NpcStringId YOURE_S1_ILL_KILL_YOU_FOR_HALLATE;

	/**
	 * ID: 9651<br>
	 * Message: �v������肵�ԂƂ��ȁB�������M�l�͎��̑��肶��Ȃ��B
	 */
	public static final NpcStringId YOURE_TOUGHER_THAN_I_THOUGHT_BUT_YOU_STILL_CANT_RIVAL_ME;

	/**
	 * ID: 9652<br>
	 * Message: �n���g�l�I��͂Ȏ������������������I
	 */
	public static final NpcStringId HALLATE_FORGIVE_ME_I_CANT_HELP_YOU;

	/**
	 * ID: 9654<br>
	 * Message: �����D�D�D�M�l���Ƃ��Ɏ���������Ƃ́B
	 */
	public static final NpcStringId DAMMIT_I_CANT_BELIEVE_YOU_BEAT_ME;

	/**
	 * ID: 9655<br>
	 * Message: �킢���ז����邨�O�͉��҂��I�H�ڋ��Ȃ�߁B
	 */
	public static final NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_COWARD;

	/**
	 * ID: 9657<br>
	 * Message: �����̌N��I�������Ȃ����B
	 */
	public static final NpcStringId PURGATORY_LORD_I_WONT_FAIL_THIS_TIME;

	/**
	 * ID: 9658<br>
	 * Message: $s1�I����܂ł̐��ʂ�������`�����X���I���O�̗͂������Ă��B
	 */
	public static final NpcStringId S1_NOWS_THE_TIME_TO_PUT_YOUR_TRAINING_TO_THE_TEST;

	/**
	 * ID: 9659<br>
	 * Message: $s1�I���O�̎��͂͂���Ȃ��񂶂�Ȃ����낤�B
	 */
	public static final NpcStringId S1_YOUR_SWORD_SKILLS_CANT_BE_THAT_BAD;

	/**
	 * ID: 9660<br>
	 * Message: $s1�I���O�̖{���̗͂������Ă���I
	 */
	public static final NpcStringId S1_SHOW_YOUR_STRENGTH;

	/**
	 * ID: 9661<br>
	 * Message: ���ꂶ��A���͑��̗p��������̂ŁD�D�D
	 */
	public static final NpcStringId I_HAVE_SOME_PRESSING_BUSINESS_I_HAVE_TO_GO;

	/**
	 * ID: 9662<br>
	 * Message: ��������������̂��A�������B
	 */
	public static final NpcStringId I_MISSED_HIM_DAMMIT;

	/**
	 * ID: 9663<br>
	 * Message: �܂��V�тɗ���Ƃ����B
	 */
	public static final NpcStringId TRY_AGAIN_SOMETIME;

	/**
	 * ID: 9664<br>
	 * Message: ���̓����ǂ��ҁI���������ď����I
	 */
	public static final NpcStringId ILL_KILL_ANYONE_WHO_GETS_IN_MY_WAY;

	/**
	 * ID: 9665<br>
	 * Message: �΂킹��ȁB�܂�ȉ߂���B
	 */
	public static final NpcStringId THIS_IS_PATHETIC_YOU_MAKE_ME_LAUGH;

	/**
	 * ID: 9666<br>
	 * Message: $s1�I�M�l���Ƃ������̑O�ɗ����͂����낤�Ƃ́I
	 */
	public static final NpcStringId S1_ARE_YOU_TRYING_TO_GET_IN_MY_WAY;

	/**
	 * ID: 9667<br>
	 * Message: $s1�I�����߂��Ă����������O�̖����ɂȂ邾�낤�B
	 */
	public static final NpcStringId S1_WHEN_I_COME_BACK_ILL_KILL_YOU;

	/**
	 * ID: 9750<br>
	 * Message: $s1�H �M �l �� �� �� �� �� �� �� ���B
	 */
	public static final NpcStringId S1_WAKE_UP_TIME_TO_DIE;

	/**
	 * ID: 9751<br>
	 * Message: �v �� �� �� �� �� �� �� �� �� �o �� ���B
	 */
	public static final NpcStringId YOURE_TOUGHER_THAN_I_THOUGHT_ILL_BE_BACK;

	/**
	 * ID: 9752<br>
	 * Message: �� �� �� �� �� �� �� �āB
	 */
	public static final NpcStringId I_LOST_IT_CANT_BE;

	/**
	 * ID: 9757<br>
	 * Message: ���΂���������I���x���������Ȃ����B
	 */
	public static final NpcStringId YOURE_A_CUNNING_FIEND_I_WONT_FAIL_AGAIN;

	/**
	 * ID: 9758<br>
	 * Message: $s1�I���̖�Y�����O���ɂ�ł邼�I�ځ[���Ƃ��ĂȂ��Ő킦�I
	 */
	public static final NpcStringId S1_ITS_AFTER_YOU_FIGHT;

	/**
	 * ID: 9759<br>
	 * Message: $s1�I�ȂɁH�x�݂������āH���ˁI
	 */
	public static final NpcStringId S1_YOU_HAVE_TO_FIGHT_BETTER_THAN_THAT_IF_YOU_EXPECT_TO_SURVIVE;

	/**
	 * ID: 9760<br>
	 * Message: $s1�I�C������Đ키�񂾁I
	 */
	public static final NpcStringId S1_PULL_YOURSELF_TOGETHER_FIGHT;

	/**
	 * ID: 9761<br>
	 * Message: ���ꂶ��A���͂�����ǂ����B
	 */
	public static final NpcStringId ILL_CATCH_THE_CUNNING_FIEND;

	/**
	 * ID: 9762<br>
	 * Message: �����A�܂����������B���΂���������߁I
	 */
	public static final NpcStringId I_MISSED_HIM_AGAIN_HES_CLEVER;

	/**
	 * ID: 9763<br>
	 * Message: ���͔��l�O���甲���o���Ă����񂾂ȁI
	 */
	public static final NpcStringId DONT_COWER_LIKE_A_PUPPY_NEXT_TIME;

	/**
	 * ID: 9764<br>
	 * Message: ���̖ڕW�͂���1�I�ז������������ǂ��B
	 */
	public static final NpcStringId I_HAVE_ONLY_ONE_GOAL_GET_OUT_OF_MY_WAY;

	/**
	 * ID: 9765<br>
	 * Message: �������O�ɓV���������܂ő҂��Ă���񂾂ȁB
	 */
	public static final NpcStringId JUST_WAIT_YOULL_GET_YOURS;

	/**
	 * ID: 9766<br>
	 * Message: $s1�I���̔ڋ��҂߁I
	 */
	public static final NpcStringId S1_YOURE_A_COWARD_ARENT_YOU;

	/**
	 * ID: 9767<br>
	 * Message: $s1�I���������O��K���|���Ă��B
	 */
	public static final NpcStringId S1_ILL_KILL_YOU_NEXT_TIME;

	/**
	 * ID: 9850<br>
	 * Message: $s1�I���O���_�̌�S�ɋt�炤�Ƃ́B�����������B
	 */
	public static final NpcStringId S1_HOW_FOOLISH_TO_ACT_AGAINST_THE_WILL_OF_GOD;

	/**
	 * ID: 9851<br>
	 * Message: �v���̂ق��M�S����������ȁB�܂�����B
	 */
	public static final NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_GET_YOU_NEXT_TIME;

	/**
	 * ID: 9855<br>
	 * Message: �킢���ז����邨�O�͉��҂��I�H�ڋ��Ȃ�߁B
	 */
	public static final NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_YOU_COWARD;

	/**
	 * ID: 9857<br>
	 * Message: �^�i�L�A����A���Ȃ��̉R�͂����o���Ă��ł���B
	 */
	public static final NpcStringId TANAKIA_YOUR_LIE_HAS_ALREADY_BEEN_EXPOSED;

	/**
	 * ID: 9858<br>
	 * Message: $s1�I���ƂƂ��ɂ��̎��������z���܂��傤�B
	 */
	public static final NpcStringId S1_HELP_ME_OVERCOME_THIS;

	/**
	 * ID: 9859<br>
	 * Message: $s1�I���̒��x����^�i�L�A�͓|���܂���B
	 */
	public static final NpcStringId S1_WE_CANT_DEFEAT_TANAKIA_THIS_WAY;

	/**
	 * ID: 9860<br>
	 * Message: $s1�I�`�����X�͍������Ȃ���ł��I�����I
	 */
	public static final NpcStringId S1_HERES_OUR_CHANCE_DONT_SQUANDER_THE_OPPORTUNITY;

	/**
	 * ID: 9861<br>
	 * Message: �܂�����������΂���ł���ł��傤�B���悤�Ȃ�D�D�D
	 */
	public static final NpcStringId GOODBYE_WELL_MEET_AGAIN_IF_FATE_ALLOWS;

	/**
	 * ID: 9862<br>
	 * Message: �߂��̉^���𐳂����߂ɁA�^�i�L�A��ǂ��܂��B
	 */
	public static final NpcStringId ILL_FOLLOW_TANAKIA_TO_CORRECT_THIS_FALSEHOOD;

	/**
	 * ID: 9863<br>
	 * Message: ���Ȃ������̉^���̗���ƌ���鎞�A�܂��������ł��傤�B
	 */
	public static final NpcStringId ILL_BE_BACK_IF_FATE_ALLOWS;

	/**
	 * ID: 9865<br>
	 * Message: ���͖߂��Ă���B���̎��ɂ͊o�傷�邪�����B
	 */
	public static final NpcStringId ILL_BE_BACK_YOULL_PAY;

	/**
	 * ID: 9866<br>
	 * Message: $s1�I���̌v����󂷂��肩�I
	 */
	public static final NpcStringId S1_ARE_YOU_TRYING_TO_SPOIL_MY_PLAN;

	/**
	 * ID: 9867<br>
	 * Message: $s1�I�o���Ă���B�M�l�̂��Ƃ͐�ΖY��Ȃ��B
	 */
	public static final NpcStringId S1_I_WONT_FORGET_YOU_YOULL_PAY;

	/**
	 * ID: 9950<br>
	 * Message: ���O��$s1���B�s���Ȓm�����Â�҂�A�����o�傷�邪�����D�D�D
	 */
	public static final NpcStringId S1_YOU_HAVE_AN_AFFINITY_FOR_DANGEROUS_IDEAS_ARE_YOU_READY_TO_DIE;

	/**
	 * ID: 9951<br>
	 * Message: ���ɗ^����ꂽ���Ԃ͎g���ʂ������D�D�D
	 */
	public static final NpcStringId MY_TIME_IS_UP;

	/**
	 * ID: 9952<br>
	 * Message: �������l�Ԃ��Ƃ��ɂЂ��܂����Ƃ́I���蓾�Ȃ����Ƃ��I
	 */
	public static final NpcStringId I_CANT_BELIEVE_I_MUST_KNEEL_TO_A_HUMAN;

	/**
	 * ID: 9957<br>
	 * Message: �~�l�����B�A�I�����������������悤���I
	 */
	public static final NpcStringId MINERVIA_WHATS_THE_MATTER;

	/**
	 * ID: 9958<br>
	 * Message: ���[���I�����I�������s���`�Ȃ̂ɁA������܂˂��Č��Ă�̂���I
	 */
	public static final NpcStringId THE_PRINCESS_IS_IN_DANGER_WHY_ARE_YOU_STARING;

	/**
	 * ID: 9959<br>
	 * Message: ����΂��Ă�I$s1����A�����I
	 */
	public static final NpcStringId MASTER_S1_COME_ON_HURRY_UP;

	/**
	 * ID: 9960<br>
	 * Message: ��������_���I$s1����A����΂��āI
	 */
	public static final NpcStringId WE_CANT_FAIL_MASTER_S1_PULL_YOURSELF_TOGETHER;

	/**
	 * ID: 9961<br>
	 * Message: �������Ă�ꍇ����Ȃ���D�D�D���ꂶ��I���C�łˁI
	 */
	public static final NpcStringId WHAT_AM_I_DOING_I_GOTTA_GO_GOODBYE;

	/**
	 * ID: 9962<br>
	 * Message: �������I������������D�D�D
	 */
	public static final NpcStringId DAMMIT_I_MISSED;

	/**
	 * ID: 9963<br>
	 * Message: �c�O�����ǂ܂����ʂ�ł��ˁD�D�D�K�^���F���I
	 */
	public static final NpcStringId SORRY_BUT_I_MUST_SAY_GOODBYE_AGAIN_GOOD_LUCK_TO_YOU;

	/**
	 * ID: 9964<br>
	 * Message: �����ă^�u���b�g�̔閧�����邱�Ƃ͂ł��Ȃ��I
	 */
	public static final NpcStringId I_CANT_YIELD_THE_SECRET_OF_THE_TABLET;

	/**
	 * ID: 9965<br>
	 * Message: ���̂��炢�ɂ��Ă������ق��������ȁD�D�D
	 */
	public static final NpcStringId ILL_STOP_HERE_FOR_NOW;

	/**
	 * ID: 9966<br>
	 * Message: $s1�A���̊�ɏ�������Ƃ́I���ˁI
	 */
	public static final NpcStringId S1_YOU_DARED_TO_LEAVE_SCAR_ON_MY_FACE_ILL_KILL_YOU;

	/**
	 * ID: 9967<br>
	 * Message: $s1�A���̖��O�A�o���Ă������D�D�D���ނ��I
	 */
	public static final NpcStringId S1_I_WONT_FORGET_YOUR_NAMEHA;

	/**
	 * ID: 10050<br>
	 * Message: ���O��$s1���B�s���Ȓm�����Â�҂�A�����o�傷�邪�����D�D�D
	 */
	public static final NpcStringId S1_YOU_HAVE_AN_AFFINITY_FOR_BAD_IDEAS_ARE_YOU_READY_TO_DIE;

	/**
	 * ID: 10052<br>
	 * Message: �������l�Ԃ��Ƃ��ɂЂ��܂����Ƃ́I���蓾�Ȃ����Ƃ��I
	 */
	public static final NpcStringId I_CANT_BELIEVE_I_MUST_KNEEL_BEFORE_A_HUMAN;

	/**
	 * ID: 10057<br>
	 * Message: ���A���̈��}�I���A�����΂�n���I
	 */
	public static final NpcStringId YOU_THIEF_GIVE_ME_THE_RESONANCE_AMULET;

	/**
	 * ID: 10058<br>
	 * Message: �������I$s1�A�����Ă���I
	 */
	public static final NpcStringId UGH_S1_HELP_ME;

	/**
	 * ID: 10059<br>
	 * Message: $s1�A���ށI�͂����킹��Ώ��Ă�I
	 */
	public static final NpcStringId S1_PLEASE_HELP_ME_TOGETHER_WE_CAN_BEAT_HIM;

	/**
	 * ID: 10060<br>
	 * Message: $s1�I���Ԃ�����Ă���̂�����Ă������肩�I
	 */
	public static final NpcStringId S1_ARE_YOU_GOING_TO_LET_A_GUILD_MEMBER_DIE;

	/**
	 * ID: 10061<br>
	 * Message: ���߂�A����ς肾�߂��I��ɍs�����I
	 */
	public static final NpcStringId IM_SORRY_BUT_I_GOTTA_GO_FIRST;

	/**
	 * ID: 10062<br>
	 * Message: �������D�D�D�����΂���ɓ��炸�D�D�D
	 */
	public static final NpcStringId AAAAH_I_COULDNT_GET_THE_RESONANCE_AMULET;

	/**
	 * ID: 10063<br>
	 * Message: ���ꂶ��A�����I���͂��̕ӂŁ`
	 */
	public static final NpcStringId TAKE_CARE_I_GOTTA_GO_NOW;

	/**
	 * ID: 10064<br>
	 * Message: �c�O�����A������d���̂����D�D�D����ł��炨���I
	 */
	public static final NpcStringId IM_SORRY_BUT_ITS_MY_JOB_TO_KILL_YOU_NOW;

	/**
	 * ID: 10065<br>
	 * Message: �ӂ��A���Ԃ̖��ʂ������ȁI
	 */
	public static final NpcStringId WHAT_A_WASTE_OF_TIME;

	/**
	 * ID: 10066<br>
	 * Message: $s1�I�M�l���H���ӋC�Ȃ�߁I���̍����~�߂Ă��I
	 */
	public static final NpcStringId S1_HOW_COULD_YOU_DO_THIS_ILL_KILL_YOU;

	/**
	 * ID: 10067<br>
	 * Message: $s1�I���̎؂�͕K���Ԃ����I
	 */
	public static final NpcStringId S1_ILL_PAY_YOU_BACK;

	/**
	 * ID: 10068<br>
	 * Message: ���肢�����玀��ł���I
	 */
	public static final NpcStringId WHY_DONT_YOU_JUST_DIE;

	/**
	 * ID: 10069<br>
	 * Message: �X�|�C��5�i���Ȃ߂�Ȃ�I
	 */
	public static final NpcStringId TASTE_THE_STING_OF_LEVEL_5_SPOIL;

	/**
	 * ID: 10070<br>
	 * Message: ���������������񎝂��Ă������Ȃ��`
	 */
	public static final NpcStringId THE_ITEM_IS_ALREADY_INSIDE_YOU;

	/**
	 * ID: 10071<br>
	 * Message: �M�d�ȃ|�[�V���������ނ͂߂ɂȂ�Ȃ�āI
	 */
	public static final NpcStringId THIS_POTION_YOURE_MAKING_ME_DRINK_IS_WORTH_ITS_WEIGHT_IN_GOLD;

	/**
	 * ID: 10072<br>
	 * Message: �O���Y���[�̊̂ō�����|�[�V�������I�o�債��I
	 */
	public static final NpcStringId THIS_POTION_IS_PREPARED_FROM_THE_GROUND_GALL_OF_A_BEAR_BE_CAREFUL_IT_PACKS_QUITE_A_PUNCH;

	/**
	 * ID: 10073<br>
	 * Message: �Ђ�����Ƀ|�[�V�������g�����ƂɂȂ�Ȃ�āI
	 */
	public static final NpcStringId HOW_CAN_YOU_USE_A_POTION_ON_A_NEWBIE;

	/**
	 * ID: 10074<br>
	 * Message: $s1�A�����ꂽ�҈ȊO�͕���̏������ւ����Ă���I
	 */
	public static final NpcStringId LISTEN_TO_ME_S1_UNLESS_YOU_HAVE_PRIOR_AUTHORIZATION_YOU_CANT_CARRY_A_WEAPON_HERE;

	/**
	 * ID: 10075<br>
	 * Message: $s1�A�A�C���n�U�[�h�l�̏j���̂���񂱂Ƃ��I
	 */
	public static final NpcStringId DEAR_S1_MAY_THE_BLESSINGS_OF_EINHASAD_BE_WITH_YOU_ALWAYS;

	/**
	 * ID: 10076<br>
	 * Message: $s1�A���̓������ɕ��ތZ���D�D�D
	 */
	public static final NpcStringId DEAR_BROTHER_S1_FOLLOW_THE_PATH_OF_LIGHT_WITH_ME;

	/**
	 * ID: 10077<br>
	 * Message: $s1�A�ł̓���I�ԂƂ́I
	 */
	public static final NpcStringId S1_WHY_WOULD_YOU_CHOOSE_THE_PATH_OF_DARKNESS;

	/**
	 * ID: 10078<br>
	 * Message: $s1�A�A�C���n�U�[�h�l�̌�S�ɔw���Ƃ́I
	 */
	public static final NpcStringId S1_HOW_DARE_YOU_DEFY_THE_WILL_OF_EINHASAD;

	/**
	 * ID: 10079<br>
	 * Message: �Ւd3�K�̔����J����܂����B
	 */
	public static final NpcStringId THE_DOOR_TO_THE_3RD_FLOOR_OF_THE_ALTAR_IS_NOW_OPEN;

	/**
	 * ID: 11101<br>
	 * Message: �G���N���b�L�[ �n���^�[�̏�
	 */
	public static final NpcStringId ELROKIAN_HUNTERS;

	/**
	 * ID: 11450<br>
	 * Message: �E�F���f�B���P��$s1�A���ˁI
	 */
	public static final NpcStringId YOU_S1_YOU_ATTACKED_WENDY_PREPARE_TO_DIE;

	/**
	 * ID: 11451<br>
	 * Message: �G$s1��ǂ��Ԃ��܂����B������������Ԃ��đҋ@���܂��B
	 */
	public static final NpcStringId S1_YOUR_ENEMY_WAS_DRIVEN_OUT_I_WILL_NOW_WITHDRAW_AND_AWAIT_YOUR_NEXT_COMMAND;

	/**
	 * ID: 11452<br>
	 * Message: �z���ȏ�ɓG�����B�퓬�s�\�B�ގU�B
	 */
	public static final NpcStringId THIS_ENEMY_IS_FAR_TOO_POWERFUL_FOR_ME_TO_FIGHT_I_MUST_WITHDRAW;

	/**
	 * ID: 11453<br>
	 * Message: �d�g�T�m�킪�������Ă���B#�߂��Ŗڂɂ����̂́A�������Ȑ΂̎R�������B
	 */
	public static final NpcStringId THE_RADIO_SIGNAL_DETECTOR_IS_RESPONDING_A_SUSPICIOUS_PILE_OF_STONES_CATCHES_YOUR_EYE;

	/**
	 * ID: 11550<br>
	 * Message: ������ւ�̂͂��Ȃ񂾂��ǁD�D�D
	 */
	public static final NpcStringId THIS_LOOKS_LIKE_THE_RIGHT_PLACE;

	/**
	 * ID: 11551<br>
	 * Message: �N������B������^���Ȃ̂��D�D�D
	 */
	public static final NpcStringId I_SEE_SOMEONE_IS_THIS_FATE;

	/**
	 * ID: 11552<br>
	 * Message: ���Ȃ��ɂ����ł܂����Ƃ́B
	 */
	public static final NpcStringId WE_MEET_AGAIN;

	/**
	 * ID: 11553<br>
	 * Message: ���̂��Ƃ͂����B���Ȃ��͂��Ȃ��̉^���ɏ]���Ȃ����B
	 */
	public static final NpcStringId DONT_BOTHER_TRYING_TO_FIND_OUT_MORE_ABOUT_ME_FOLLOW_YOUR_OWN_DESTINY;

	/**
	 * ID: 14204<br>
	 * Message: �t�@�[���� �G���W�F���F�I��
	 */
	public static final NpcStringId FALLEN_ANGEL_SELECT;

	/**
	 * ID: 15804<br>
	 * Message: ���ӋC�ɂ����ɒ��ނƂ́I
	 */
	public static final NpcStringId _HOW_DARE_YOU_CHALLENGE_ME;

	/**
	 * ID: 15805<br>
	 * Message: �׃��X�l�̌������V���ɁD�D�D
	 */
	public static final NpcStringId THE_POWER_OF_LORD_BELETH_RULES_THE_WHOLE_WORLD;

	/**
	 * ID: 16404<br>
	 * Message: ���Ȃ��̔M�����𖡂��킹�Ă��炨���I
	 */
	public static final NpcStringId I_WILL_TASTE_YOUR_BLOOD;

	/**
	 * ID: 16405<br>
	 * Message: �N���~�X�Ƃ̌_�񂪏I�������D�D�D
	 */
	public static final NpcStringId I_HAVE_FULFILLED_MY_CONTRACT_WITH_TRADER_CREAMEES;

	/**
	 * ID: 17004<br>
	 * Message: ���߂邱�Ƃ̂Ȃ������Ɋׂ�Ă��I
	 */
	public static final NpcStringId ILL_CAST_YOU_INTO_AN_ETERNAL_NIGHTMARE;

	/**
	 * ID: 17005<br>
	 * Message: ���̍��̓C�J���X�l�̂��ƂցD�D�D
	 */
	public static final NpcStringId SEND_MY_SOUL_TO_LICH_KING_ICARUS;

	/**
	 * ID: 17151<br>
	 * Message: ����͂��ꂭ�炢�ɂ��Ĉ����グ�悤�D�D�D
	 */
	public static final NpcStringId YOU_SHOULD_CONSIDER_GOING_BACK;

	/**
	 * ID: 17951<br>
	 * Message: �x�[���ɕ�܂ꂽ�n����D�D�D
	 */
	public static final NpcStringId THE_VEILED_CREATOR;

	/**
	 * ID: 17952<br>
	 * Message: ���󂳂�Ȃ���΂Ȃ�Ȃ������푰
	 */
	public static final NpcStringId THE_CONSPIRACY_OF_THE_ANCIENT_RACE;

	/**
	 * ID: 17953<br>
	 * Message: ���ׂ̖����D�D�D
	 */
	public static final NpcStringId CHAOS_AND_TIME;

	/**
	 * ID: 18451<br>
	 * Message: �N���Ҕ����I���R���h�����߁A2����ɋL�^���u��j�󂵂܂��B
	 */
	public static final NpcStringId INTRUDER_ALERT_THE_ALARM_WILL_SELF_DESTRUCT_IN_2_MINUTES;

	/**
	 * ID: 18452<br>
	 * Message: �L�^���u�̔j��60�b�O�ł��B�j����~�߂�ɂ̓p�X���[�h����͂��Ă��������B
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;

	/**
	 * ID: 18453<br>
	 * Message: �L�^���u�̔j��30�b�O�ł��B�j����~�߂�ɂ̓p�X���[�h����͂��Ă��������B
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;

	/**
	 * ID: 18454<br>
	 * Message: �L�^���u�̔j��10�b�O�ł��B�j����~�߂�ɂ̓p�X���[�h����͂��Ă��������B
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;

	/**
	 * ID: 18455<br>
	 * Message: �L�^���u���j�󂳂�܂����B
	 */
	public static final NpcStringId RECORDER_CRUSHED;

	/**
	 * ID: 18552<br>
	 * Message: �L�^���u���j60�b�O�ł��B���S�ȏꏊ�ɔ��Ă��������B
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;

	/**
	 * ID: 18553<br>
	 * Message: �L�^���u���j30�b�O�ł��B���S�ȏꏊ�ɔ��Ă��������B
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;

	/**
	 * ID: 18554<br>
	 * Message: �L�^���u���j10�b�O�ł��B���S�ȏꏊ�ɔ��Ă��������B
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;

	/**
	 * ID: 19304<br>
	 * Message: $s1��I���̕i�̎�͂��܂��ł͂Ȃ��I
	 */
	public static final NpcStringId S1_YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM;

	/**
	 * ID: 19305<br>
	 * Message: �C������I���͐����ċA���˂����I
	 */
	public static final NpcStringId NEXT_TIME_YOU_WILL_NOT_ESCAPE;

	/**
	 * ID: 19306<br>
	 * Message: $s1�I�����̂Ƃ���͈��������邪�A�����͂��O��߂܂��Ă��I
	 */
	public static final NpcStringId S1_YOU_MAY_HAVE_WON_THIS_TIME_BUT_NEXT_TIME_I_WILL_SURELY_CAPTURE_YOU;

	/**
	 * ID: 19504<br>
	 * Message: �N���҂��I�t���̎i�Ւc�����I
	 */
	public static final NpcStringId INTRUDER_PROTECT_THE_PRIESTS_OF_DAWN;

	/**
	 * ID: 19505<br>
	 * Message: ���O�͒N���H�������ƂȂ��炾�ȁB���܂��͂����ɂ͋ߊ��Ȃ��B
	 */
	public static final NpcStringId WHO_ARE_YOU_A_NEW_FACE_LIKE_YOU_CANT_APPROACH_THIS_PLACE;

	/**
	 * ID: 19506<br>
	 * Message: ����Ȏq�����܂��̕ϐg�p�ł����ɔE�э��ނƂ́I�o�čs���I
	 */
	public static final NpcStringId HOW_DARE_YOU_INTRUDE_WITH_THAT_TRANSFORMATION_GET_LOST;

	/**
	 * ID: 19604<br>
	 * Message: ���������N���I�}�����̏��l���Ăяo�����̂́I
	 */
	public static final NpcStringId WHO_DARES_SUMMON_THE_MERCHANT_OF_MAMMON;

	/**
	 * ID: 19605<br>
	 * Message: �c��Ƃ̌Â��񑩂���������D�D�D
	 */
	public static final NpcStringId THE_ANCIENT_PROMISE_TO_THE_EMPEROR_HAS_BEEN_FULFILLED;

	/**
	 * ID: 19606<br>
	 * Message: �A�C���n�U�[�h��A�i���Ȃ�I
	 */
	public static final NpcStringId FOR_THE_ETERNITY_OF_EINHASAD;

	/**
	 * ID: 19607<br>
	 * Message: �V�[�����̎q��������I���F���O�����͉�X�̑���ł͂Ȃ��I
	 */
	public static final NpcStringId DEAR_SHILLIENS_OFFSPRINGS_YOU_ARE_NOT_CAPABLE_OF_CONFRONTING_US;

	/**
	 * ID: 19608<br>
	 * Message: �A�C���n�U�[�h�l�̐^�̗͂������Ă�낤�I
	 */
	public static final NpcStringId ILL_SHOW_YOU_THE_REAL_POWER_OF_EINHASAD;

	/**
	 * ID: 19609<br>
	 * Message: ���̌R���I�V�[�����̎q��������|���I
	 */
	public static final NpcStringId DEAR_MILITARY_FORCE_OF_LIGHT_GO_DESTROY_THE_OFFSPRINGS_OF_SHILLIEN;

	/**
	 * ID: 19610<br>
	 * Message: ��������ׂ�$s1�l�̂������ł��B���肪�Ƃ��������܂��B
	 */
	public static final NpcStringId EVERYTHING_IS_OWING_TO_S1_THANK_YOU;

	/**
	 * ID: 19611<br>
	 * Message: ���̗͂͂���������܂��B�������󑕒u���쓮�����Ă��������I
	 */
	public static final NpcStringId MY_POWERS_WEAKENING_HURRY_AND_TURN_ON_THE_SEALING_DEVICE;

	/**
	 * ID: 19612<br>
	 * Message: ���󑕒u��4���ׂč쓮�����Ȃ���΂Ȃ�܂���I
	 */
	public static final NpcStringId ALL_4_SEALING_DEVICES_MUST_BE_TURNED_ON;

	/**
	 * ID: 19613<br>
	 * Message: �����X�̍U�����������Ȃ����܂��B�����쓮�����Ă��������I
	 */
	public static final NpcStringId LILITHS_ATTACK_IS_GETTING_STRONGER_GO_AHEAD_AND_TURN_IT_ON;

	/**
	 * ID: 19614<br>
	 * Message: $s1�I����΂��Ă��������B
	 */
	public static final NpcStringId DEAR_S1_GIVE_ME_MORE_STRENGTH;

	/**
	 * ID: 19615<br>
	 * Message: ���̋����҂߂��I���̐푈�̐^�̏��҂̓V�[�����l���I
	 */
	public static final NpcStringId YOU_SUCH_A_FOOL_THE_VICTORY_OVER_THIS_WAR_BELONGS_TO_SHILIEN;

	/**
	 * ID: 19616<br>
	 * Message: ���ӋC�ɂ����Ɛ킢�����ƁH�Ύ~�疜�I
	 */
	public static final NpcStringId HOW_DARE_YOU_TRY_TO_CONTEND_AGAINST_ME_IN_STRENGTH_RIDICULOUS;

	/**
	 * ID: 19617<br>
	 * Message: �A�i�L���I�̑�Ȃ�V�[�����l�̌䖼�̂��Ƃɑ��̍����~�߂Ă��I
	 */
	public static final NpcStringId ANAKIM_IN_THE_NAME_OF_GREAT_SHILIEN_I_WILL_CUT_YOUR_THROAT;

	/**
	 * ID: 19618<br>
	 * Message: ���F���O�͎��̑���ł͂Ȃ��I�̂ł킩�点�Ă�낤���I
	 */
	public static final NpcStringId YOU_CANNOT_BE_THE_MATCH_OF_LILITH_ILL_TEACH_YOU_A_LESSON;

	/**
	 * ID: 19619<br>
	 * Message: ����Ȏp�ŃV�[�����l�̉��ɖ߂�˂΂Ȃ�Ȃ��Ƃ́D�D�D���₵���D�D�D
	 */
	public static final NpcStringId I_MUST_GO_BACK_TO_SHILIEN_JUST_LIKE_THIS_OUTRAGEOUS;

	/**
	 * ID: 19804<br>
	 * Message: �t���̌N��ɐn�������҂Ɏ����I
	 */
	public static final NpcStringId DEATH_TO_THE_ENEMIES_OF_THE_LORDS_OF_DAWN;

	/**
	 * ID: 19805<br>
	 * Message: ���O�̖����ɂ́A��ɉ�X�t��������B�񑩂��悤�I
	 */
	public static final NpcStringId WE_WILL_BE_WITH_YOU_ALWAYS;

	/**
	 * ID: 19806<br>
	 * Message: ���̕i�̎�͂��܂��ł͂Ȃ��I
	 */
	public static final NpcStringId YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM;

	/**
	 * ID: 19807<br>
	 * Message: �G���u���I�D�D�D
	 */
	public static final NpcStringId EMBRYO;

	/**
	 * ID: 20901<br>
	 * Message: �J�}�G�� �`���[�g���A��
	 */
	public static final NpcStringId KAMAEL_TUTORIAL;

	/**
	 * ID: 22051<br>
	 * Message: �J�J�C�̎艺���I
	 */
	public static final NpcStringId IS_IT_A_LACKEY_OF_KAKAI;

	/**
	 * ID: 22052<br>
	 * Message: �����x���I
	 */
	public static final NpcStringId TOO_LATE;

	/**
	 * ID: 22055<br>
	 * Message: �����������A�G��߂��I
	 */
	public static final NpcStringId HOW_REGRETFUL_UNJUST_DISHONOR;

	/**
	 * ID: 22056<br>
	 * Message: �����K�����Q���Ă��I
	 */
	public static final NpcStringId ILL_GET_REVENGE_SOMEDAY;

	/**
	 * ID: 22057<br>
	 * Message: �����������s���Ȏ��I
	 */
	public static final NpcStringId INDIGNANT_AND_UNFAIR_DEATH;

	/**
	 * ID: 22719<br>
	 * Message: �B���ꂽ�^���͂���������������邾�낤�D�D�D
	 */
	public static final NpcStringId THE_CONCEALED_TRUTH_WILL_ALWAYS_BE_REVEALED;

	/**
	 * ID: 22720<br>
	 * Message: ������������߁I
	 */
	public static final NpcStringId COWARDLY_GUY;

	/**
	 * ID: 22819<br>
	 * Message: ���͋����̖؁D�D�D�߂�ׂ��Ƃ����m���Ă���D�D�D�؁D�D�D
	 */
	public static final NpcStringId I_AM_A_TREE_OF_NOTHING_A_TREE_THAT_KNOWS_WHERE_TO_RETURN;

	/**
	 * ID: 22820<br>
	 * Message: ���͋��̉��[���̐^���������鑶�݁D�D�D
	 */
	public static final NpcStringId I_AM_A_CREATURE_THAT_SHOWS_THE_TRUTH_OF_THE_PLACE_DEEP_IN_MY_HEART;

	/**
	 * ID: 22821<br>
	 * Message: ���͈ł̋��D�D�D�ł̋����D�D�D
	 */
	public static final NpcStringId I_AM_A_MIRROR_OF_DARKNESS_A_VIRTUAL_IMAGE_OF_DARKNESS;

	/**
	 * ID: 22933<br>
	 * Message: ��΂ɓn���񂼁I����͎��̋M�d�ȍ��󂾁I
	 */
	public static final NpcStringId I_ABSOLUTELY_CANNOT_GIVE_IT_TO_YOU_IT_IS_MY_PRECIOUS_JEWEL;

	/**
	 * ID: 22934<br>
	 * Message: ���O��̖���D���̂́A���܂ł��a�����I
	 */
	public static final NpcStringId ILL_TAKE_YOUR_LIVES_LATER;

	/**
	 * ID: 22935<br>
	 * Message: ���̌��͂܂����D�D�D
	 */
	public static final NpcStringId THAT_SWORD_IS_REALLY;

	/**
	 * ID: 22936<br>
	 * Message: ���߂ł��I�܂��j��ƎE�C�̎g�����ʂ����Ă��܂���I
	 */
	public static final NpcStringId NO_I_HAVENT_COMPLETELY_FINISHED_THE_COMMAND_FOR_DESTRUCTION_AND_SLAUGHTER_YET;

	/**
	 * ID: 22937<br>
	 * Message: ���̖����W����Ƃ́I���ʂ������I
	 */
	public static final NpcStringId HOW_DARE_YOU_WAKE_ME_NOW_YOU_SHALL_DIE;

	/**
	 * ID: 23060<br>
	 * Message: �P �b �g �E �� �n �W �� �} �X�D�D�D
	 */
	public static final NpcStringId START_DUEL;

	/**
	 * ID: 23061<br>
	 * Message: �n �� �\ �N �f �X�D�D�D
	 */
	public static final NpcStringId RULE_VIOLATION;

	/**
	 * ID: 23062<br>
	 * Message: �� �^ �V �m �} �P �f �X�D�D�D
	 */
	public static final NpcStringId I_LOSE;

	/**
	 * ID: 23063<br>
	 * Message: �ɂ�ɂ�`��I
	 */
	public static final NpcStringId WHHIISSHH;

	/**
	 * ID: 23065<br>
	 * Message: ���l�l�A���߂�ɂ�`��I
	 */
	public static final NpcStringId IM_SORRY_LORD;

	/**
	 * ID: 23066<br>
	 * Message: �ɂ��I���т�����I
	 */
	public static final NpcStringId WHISH_FIGHT;

	/**
	 * ID: 23068<br>
	 * Message: �������ɂ��I���l�l�����Ăɂ��I
	 */
	public static final NpcStringId LOST_SORRY_LORD;

	/**
	 * ID: 23072<br>
	 * Message: ���ꂶ��A�n�߂�Ƃ��邩�I
	 */
	public static final NpcStringId SO_SHALL_WE_START;

	/**
	 * ID: 23074<br>
	 * Message: �������I����������Ƃ́I
	 */
	public static final NpcStringId UGH_I_LOST;

	/**
	 * ID: 23075<br>
	 * Message: ���X�ɓ���Â��Ă��I
	 */
	public static final NpcStringId ILL_WALK_ALL_OVER_YOU;

	/**
	 * ID: 23077<br>
	 * Message: �������I����Ȕn���ȁI
	 */
	public static final NpcStringId UGH_CAN_THIS_BE_HAPPENING;

	/**
	 * ID: 23078<br>
	 * Message: ���R�̌��ʂ��I
	 */
	public static final NpcStringId ITS_THE_NATURAL_RESULT;

	/**
	 * ID: 23079<br>
	 * Message: �قف`���I���̏������I
	 */
	public static final NpcStringId HO_HO_I_WIN;

	/**
	 * ID: 23080<br>
	 * Message: �� �^ �V �m �V �� �E �� �f �X�D�D�D
	 */
	public static final NpcStringId I_WIN;

	/**
	 * ID: 23081<br>
	 * Message: �ɂ��I�������ɂ��I
	 */
	public static final NpcStringId WHISH_I_WON;

	/**
	 * ID: 23434<br>
	 * Message: �N�����̍��M�ȏ���������Ȃɗ~���Ă���̂��I
	 */
	public static final NpcStringId WHO_DARES_TO_TRY_AND_STEAL_MY_NOBLE_BLOOD;

	/**
	 * ID: 23651<br>
	 * Message: $s1�I����Ɖ���ȁI
	 */
	public static final NpcStringId S1_FINALLY_WE_MEET;

	/**
	 * ID: 23652<br>
	 * Message: �ӂށA�����͂ǂ��s�����񂾁H
	 */
	public static final NpcStringId HMM_WHERE_DID_MY_FRIEND_GO;

	/**
	 * ID: 23653<br>
	 * Message: ��͂������藊�񂾂��D�D�D
	 */
	public static final NpcStringId BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS;

	/**
	 * ID: 23661<br>
	 * Message: $s1�I���Ȃ�҂������B
	 */
	public static final NpcStringId S1_DID_YOU_WAIT_FOR_LONG;

	/**
	 * ID: 23671<br>
	 * Message: ���Ɍ���ꂽ���͎����ė������B $s1
	 */
	public static final NpcStringId DID_YOU_BRING_WHAT_I_ASKED_S1;

	/**
	 * ID: 23681<br>
	 * Message: ����H�߂��ɒN�������̂��B
	 */
	public static final NpcStringId HMM_IS_SOMEONE_APPROACHING;

	/**
	 * ID: 23682<br>
	 * Message: �Ȃ�ƁA�G�̍U�����I
	 */
	public static final NpcStringId GRAAAH_WERE_BEING_ATTACKED;

	/**
	 * ID: 23683<br>
	 * Message: �悵�B�ł͂������藊�񂾂��B
	 */
	public static final NpcStringId IN_THAT_CASE_I_WISH_YOU_GOOD_LUCK;

	/**
	 * ID: 23685<br>
	 * Message: $s1�A�S���T���ė������B
	 */
	public static final NpcStringId S1_HAS_EVERYTHING_BEEN_FOUND;

	/**
	 * ID: 23687<br>
	 * Message: �ł́A�܂�����I
	 */
	public static final NpcStringId SAFE_TRAVELS;

	/**
	 * ID: 25901<br>
	 * Message: �q���̈˗�
	 */
	public static final NpcStringId REQUEST_FROM_THE_FARM_OWNER;

	/**
	 * ID: 31603<br>
	 * Message: �Ȃ���X��e������D�D�D
	 */
	public static final NpcStringId WHY_DO_YOU_OPPRESS_US_SO;

	/**
	 * ID: 33409<br>
	 * Message: ��x�Ǝ��̋x���̎ז�������ȁB
	 */
	public static final NpcStringId DONT_INTERRUPT_MY_REST_AGAIN;

	/**
	 * ID: 33410<br>
	 * Message: ���ꂩ��͂��O���O���[�g �f�[���� �L���O���D�D�D
	 */
	public static final NpcStringId YOURE_A_GREAT_DEVIL_NOW;

	/**
	 * ID: 33411<br>
	 * Message: ��͂莄�̓G�ł͂Ȃ��������A�E�n�n�n�n�I
	 */
	public static final NpcStringId OH_ITS_NOT_AN_OPPONENT_OF_MINE_HA_HA_HA;

	/**
	 * ID: 33412<br>
	 * Message: �O�A�O���[�g �f�[���� �L���O�l�D�D�D
	 */
	public static final NpcStringId OH_GREAT_DEMON_KING;

	/**
	 * ID: 33413<br>
	 * Message: ���Q�͖��E�̐�ΎҁA�����Z�o���E�X�l���I
	 */
	public static final NpcStringId REVENGE_IS_OVERLORD_RAMSEBALIUS_OF_THE_EVIL_WORLD;

	/**
	 * ID: 33414<br>
	 * Message: �����̉��q�A�{�i�p���e���E�X�l�����O�𒦂炵�߂邾�낤�I
	 */
	public static final NpcStringId BONAPARTERIUS_ABYSS_KING_WILL_PUNISH_YOU;

	/**
	 * ID: 33415<br>
	 * Message: �F�A�؂ɋF��Ȃ����I
	 */
	public static final NpcStringId OK_EVERYBODY_PRAY_FERVENTLY;

	/**
	 * ID: 33416<br>
	 * Message: �����V�ɁI���Ԃ̂ł��I
	 */
	public static final NpcStringId BOTH_HANDS_TO_HEAVEN_EVERYBODY_YELL_TOGETHER;

	/**
	 * ID: 33417<br>
	 * Message: �����I�肢�抐���I
	 */
	public static final NpcStringId ONE_TWO_MAY_YOUR_DREAMS_COME_TRUE;

	/**
	 * ID: 33418<br>
	 * Message: ���̕����̃f�[���� �L���O���E�����̂͒N���H
	 */
	public static final NpcStringId WHO_KILLED_MY_UNDERLING_DEVIL;

	/**
	 * ID: 33420<br>
	 * Message: ���Ȃ��̗��������Ă����܂��傤�`�������������`
	 */
	public static final NpcStringId I_WILL_MAKE_YOUR_LOVE_COME_TRUE_LOVE_LOVE_LOVE;

	/**
	 * ID: 33421<br>
	 * Message: ���̒��ɒm�b���l�܂��Ă��܂��B����Ȏ��͒m�b�̔��`
	 */
	public static final NpcStringId I_HAVE_WISDOM_IN_ME_I_AM_THE_BOX_OF_WISDOM;

	/**
	 * ID: 33422<br>
	 * Message: �N�I�I�I�I�I
	 */
	public static final NpcStringId OH_OH_OH;

	/**
	 * ID: 33423<br>
	 * Message: �������������Ă����܂��傤���B�E�t�B
	 */
	public static final NpcStringId DO_YOU_WANT_US_TO_LOVE_YOU_OH;

	/**
	 * ID: 33424<br>
	 * Message: �Í��̌N����ĂԎ҂͒N���I
	 */
	public static final NpcStringId WHO_IS_CALLING_THE_LORD_OF_DARKNESS;

	/**
	 * ID: 33425<br>
	 * Message: ��͈̑�Ȃ�c�q�A�{�i�p���e���E�X�I
	 */
	public static final NpcStringId I_AM_A_GREAT_EMPIRE_BONAPARTERIUS;

	/**
	 * ID: 33426<br>
	 * Message: ���̐�Ύ҂̑O�ɕ������������I
	 */
	public static final NpcStringId LET_YOUR_HEAD_DOWN_BEFORE_THE_LORD;

	/**
	 * ID: 33501<br>
	 * Message: �n���^�[�̉�
	 */
	public static final NpcStringId THE_SONG_OF_THE_HUNTER;

	/**
	 * ID: 33511<br>
	 * Message: �̂̒鍑�̈�Y�͉�X��������čs�����I
	 */
	public static final NpcStringId WELL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE;

	/**
	 * ID: 33512<br>
	 * Message: �Y��Ȍ��镨���o���I�S�����̕����I
	 */
	public static final NpcStringId SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEYRE_ALL_MINE;

	/**
	 * ID: 33513<br>
	 * Message: �������I
	 */
	public static final NpcStringId PRETTY_GOOD;

	/**
	 * ID: 34830<br>
	 * Message: �t�t�t�D�D�D�ʔ��������B�J�M�͎��̂����܂Ȃ��{���Ă݂邱�Ƃ��B
	 */
	public static final NpcStringId HA_THAT_WAS_FUN_IF_YOU_WISH_TO_FIND_THE_KEY_SEARCH_THE_CORPSE;

	/**
	 * ID: 34831<br>
	 * Message: �J�M�͎��������Ă���B�ق����Ȃ�A�͐s���ŒD���I
	 */
	public static final NpcStringId I_HAVE_THE_KEY_WHY_DONT_YOU_COME_AND_TAKE_IT;

	/**
	 * ID: 34832<br>
	 * Message: �����ȁD�D�D�_��A���̎҂������D�D�D
	 */
	public static final NpcStringId YOU_FOOLS_WILL_GET_WHATS_COMING_TO_YOU;

	/**
	 * ID: 34833<br>
	 * Message: �c�O�����A���O�����̖���D��Ȃ���΂Ȃ��B
	 */
	public static final NpcStringId SORRY_ABOUT_THIS_BUT_I_MUST_KILL_YOU_NOW;

	/**
	 * ID: 34835<br>
	 * Message: ���O�����͒m��Ȃ��D�D�D7�̕���́D�D�D�O�z�b�I
	 */
	public static final NpcStringId YOU_GUYS_WOULDNT_KNOW_THE_SEVEN_SEALS_ARE_ARRRGH;

	/**
	 * ID: 34836<br>
	 * Message: ���̎R�����O�����̌��ŕ����s�����Ă�낤�I
	 */
	public static final NpcStringId I_SHALL_DRENCH_THIS_MOUNTAIN_WITH_YOUR_BLOOD;

	/**
	 * ID: 34837<br>
	 * Message: ���Ȃ���������ɂł���i�ł͂Ȃ��B����������悢�B
	 */
	public static final NpcStringId THAT_DOESNT_BELONG_TO_YOU_DONT_TOUCH_IT;

	/**
	 * ID: 34838<br>
	 * Message: �s�M�҂߁I���������邪�悢�I
	 */
	public static final NpcStringId GET_OUT_OF_MY_SIGHT_YOU_INFIDELS;

	/**
	 * ID: 34839<br>
	 * Message: �N�ɂ͂���ȏ�p�͂Ȃ����D�D�D���̂ɂ��錮�͒T���Ă݂����B
	 */
	public static final NpcStringId WE_DONT_HAVE_ANY_FURTHER_BUSINESS_TO_DISCUSS_HAVE_YOU_SEARCHED_THE_CORPSE_FOR_THE_KEY;

	/**
	 * ID: 35051<br>
	 * Message: $s1�A�X�e�b�v $s2�̃u���[ �\�E�� �X�g�[������ɓ���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_BLUE_SOUL_CRYSTAL;

	/**
	 * ID: 35052<br>
	 * Message: $s1�A�X�e�b�v $s2�̃��b�h �\�E�� �X�g�[������ɓ���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_RED_SOUL_CRYSTAL;

	/**
	 * ID: 35053<br>
	 * Message: $s1�A�X�e�b�v $s2�̃O���[�� �\�E�� �X�g�[������ɓ���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_GREEN_SOUL_CRYSTAL;

	/**
	 * ID: 35054<br>
	 * Message: $s1�A�X�e�b�v $s2�̎��ꂽ�u���[ �\�E�� �X�g�[������ɓ���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_BLUE_CURSED_SOUL_CRYSTAL;

	/**
	 * ID: 35055<br>
	 * Message: $s1�A�X�e�b�v $s2�̎��ꂽ���b�h �\�E�� �X�g�[������ɓ���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_RED_CURSED_SOUL_CRYSTAL;

	/**
	 * ID: 35056<br>
	 * Message: $s1�A�X�e�b�v $s2�̎��ꂽ�O���[�� �\�E�� �X�g�[������ɓ���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_GREEN_CURSED_SOUL_CRYSTAL;

	/**
	 * ID: 37101<br>
	 * Message: �썰�����̋���
	 */
	public static final NpcStringId SHRIEKS_OF_GHOSTS;

	/**
	 * ID: 38451<br>
	 * Message: $s1�Ԗڂ̊ԁF$s2�I
	 */
	public static final NpcStringId SLOT_S1_S3;

	/**
	 * ID: 40306<br>
	 * Message: ���̐�˂��A����߂܂��邾���āH
	 */
	public static final NpcStringId YOU_CHILDISH_FOOL_DO_YOU_THINK_YOU_CAN_CATCH_ME;

	/**
	 * ID: 40307<br>
	 * Message: ���̋��J�͕K���D�D�D
	 */
	public static final NpcStringId I_MUST_DO_SOMETHING_ABOUT_THIS_SHAMEFUL_INCIDENT;

	/**
	 * ID: 40308<br>
	 * Message: �����A���킷��Ƃł������̂��I�H
	 */
	public static final NpcStringId WHAT_DO_YOU_DARE_TO_CHALLENGE_ME;

	/**
	 * ID: 40309<br>
	 * Message: ���b�h�A�C�R���c�̕��Q���҂��Ă���I
	 */
	public static final NpcStringId THE_RED_EYED_THIEVES_WILL_REVENGE;

	/**
	 * ID: 40310<br>
	 * Message: �������ė����A���̐�ˁI
	 */
	public static final NpcStringId GO_AHEAD_YOU_CHILD;

	/**
	 * ID: 40311<br>
	 * Message: ���̒��Ԃ��K�����Q���D�D�D
	 */
	public static final NpcStringId MY_FRIENDS_ARE_SURE_TO_REVENGE;

	/**
	 * ID: 40312<br>
	 * Message: ���̕|�����̒m�炸�߁A�ɂ��ڂɑ������I
	 */
	public static final NpcStringId YOU_RUTHLESS_FOOL_I_WILL_SHOW_YOU_WHAT_REAL_FIGHTING_IS_ALL_ABOUT;

	/**
	 * ID: 40313<br>
	 * Message: ���A����Ȍ`�ŏI���Ƃ́D�D�D���O���I
	 */
	public static final NpcStringId AHH_HOW_CAN_IT_END_LIKE_THIS_IT_IS_NOT_FAIR;

	/**
	 * ID: 40909<br>
	 * Message: �����͉���̂��̂��I
	 */
	public static final NpcStringId THE_SACRED_FLAME_IS_OURS;

	/**
	 * ID: 40910<br>
	 * Message: �N�b�A���߂邵���Ȃ��������D�D�D
	 */
	public static final NpcStringId ARRGHHWE_SHALL_NEVER_SURRENDER;

	/**
	 * ID: 40913<br>
	 * Message: �����߂ɏ]���܂��A����l�l�I
	 */
	public static final NpcStringId AS_YOU_WISH_MASTER;

	/**
	 * ID: 41651<br>
	 * Message: ��ɗ����������E��A$s1�́I
	 */
	public static final NpcStringId MY_DEAR_FRIEND_OF_S1_WHO_HAS_GONE_ON_AHEAD_OF_ME;

	/**
	 * ID: 41652<br>
	 * Message: �����I��҂�I�f�W���J�� �K���W�I�������p���T�[�̍���$s1�����Ă�ł���I
	 */
	public static final NpcStringId LISTEN_TO_TEJAKAR_GANDI_YOUNG_OROKA_THE_SPIRIT_OF_THE_SLAIN_LEOPARD_IS_CALLING_YOU_S1;

	/**
	 * ID: 42046<br>
	 * Message: �N�E�b�I�݂Ȃŗ������̂��I
	 */
	public static final NpcStringId HEY_EVERYBODY_WATCH_THE_EGGS;

	/**
	 * ID: 42047<br>
	 * Message: ����ň�ׂ��ł���Ǝv������D�D�D�N�N�b�I
	 */
	public static final NpcStringId I_THOUGHT_ID_CAUGHT_ONE_SHARE_WHEW;

	/**
	 * ID: 42048<br>
	 * Message: �΂��D�D�D�d���΂��D�D�D�ӂ��āD�D�D
	 */
	public static final NpcStringId THE_STONE_THE_ELVEN_STONE_BROKE;

	/**
	 * ID: 42049<br>
	 * Message: ����D��ꂽ��A��X�͎��ʉ^�����I 
	 */
	public static final NpcStringId IF_THE_EGGS_GET_TAKEN_WERE_DEAD;

	/**
	 * ID: 42111<br>
	 * Message: �d���̗t������I
	 */
	public static final NpcStringId GIVE_ME_A_FAIRY_LEAF;

	/**
	 * ID: 42112<br>
	 * Message: �Ȃ��܂����ɂ܂Ƃ����񂾁H
	 */
	public static final NpcStringId WHY_DO_YOU_BOTHER_ME_AGAIN;

	/**
	 * ID: 42113<br>
	 * Message: �ڂ����A���O�͂������̐��������񂾁B
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_WIND;

	/**
	 * ID: 42114<br>
	 * Message: �����s���B���҂̍�����|���߂ɑ��킳���O�ɁD�D�D
	 */
	public static final NpcStringId LEAVE_NOW_BEFORE_YOU_INCUR_THE_WRATH_OF_THE_GUARDIAN_GHOST;

	/**
	 * ID: 42115<br>
	 * Message: �ڂ����A���O�͂������̐��������񂾁B
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_A_STAR;

	/**
	 * ID: 42116<br>
	 * Message: �ڂ����A���O�͂��������̐��������񂾁B
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_DUSK;

	/**
	 * ID: 42117<br>
	 * Message: �ڂ����A���O�͂����A�r�X�̐��������񂾁B
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_THE_ABYSS;

	/**
	 * ID: 42118<br>
	 * Message: ��X�͗d���؂����˂΂Ȃ�Ȃ��B
	 */
	public static final NpcStringId WE_MUST_PROTECT_THE_FAIRY_TREE;

	/**
	 * ID: 42119<br>
	 * Message: �s���Ȏ҂�����A���Ȃ�؂��痣���B
	 */
	public static final NpcStringId GET_OUT_OF_THE_SACRED_TREE_YOU_SCOUNDRELS;

	/**
	 * ID: 42120<br>
	 * Message: ���E�̐����𓐂����Ƃ��铐�������𐬔s���Ă��D�D�D
	 */
	public static final NpcStringId DEATH_TO_THE_THIEVES_OF_THE_PURE_WATER_OF_THE_WORLD;

	/**
	 * ID: 42231<br>
	 * Message: �����A�Z���I���̏������K�v�Ȃ悤���ȁH
	 */
	public static final NpcStringId HEY_IT_SEEMS_LIKE_YOU_NEED_MY_HELP_DOESNT_IT;

	/**
	 * ID: 42232<br>
	 * Message: �ǂ��ւł��t���čs�����I����ɂ��Ă��N�\�b�A���̎�������Ƃ������ȁI
	 */
	public static final NpcStringId ALMOST_GOT_IT_OUCH_STOP_DAMN_THESE_BLOODY_MANACLES;

	/**
	 * ID: 42233<br>
	 * Message: �ɂ��I�ɉ߂��邼�I���͂Ђǂ��ɂ��񂾁I
	 */
	public static final NpcStringId OH_THAT_SMARTS;

	/**
	 * ID: 42234<br>
	 * Message: �����A�}�X�^�[�I�������Ă񂶂�˂���I��������������񂾂��I
	 */
	public static final NpcStringId HEY_MASTER_PAY_ATTENTION_IM_DYING_OVER_HERE;

	/**
	 * ID: 42235<br>
	 * Message: ������̉��������Ƃ����񂾁I
	 */
	public static final NpcStringId WHAT_HAVE_I_DONE_TO_DESERVE_THIS;

	/**
	 * ID: 42236<br>
	 * Message: �͂��͂��A�債�����񂾁A�������˂��I�ŁA�ǂ�����񂾂�H
	 */
	public static final NpcStringId OH_THIS_IS_JUST_GREAT_WHAT_ARE_YOU_GOING_TO_DO_NOW;

	/**
	 * ID: 42237<br>
	 * Message: �o�J�I���O�̓}�k�P���B����l�̖ʓ|�������ƌ���Ȃ��̂���H
	 */
	public static final NpcStringId YOU_INCONSIDERATE_MORON_CANT_YOU_EVEN_TAKE_CARE_OF_LITTLE_OLD_ME;

	/**
	 * ID: 42238<br>
	 * Message: �P�b�I�V�� �C�[�^�[���S�I�܍߂ɂ͂܂��܂��������ȁI
	 */
	public static final NpcStringId OH_NO_THE_MAN_WHO_EATS_ONES_SINS_HAS_DIED_PENITENCE_IS_FURTHER_AWAY;

	/**
	 * ID: 42239<br>
	 * Message: ����X�L���H����ȏ��łނ�݂Ɏg������A���̊C�ɂȂ邺�I
	 */
	public static final NpcStringId USING_A_SPECIAL_SKILL_HERE_COULD_TRIGGER_A_BLOODBATH;

	/**
	 * ID: 42240<br>
	 * Message: �����A�Z��I���Ɉ�̉������҂��Ă�񂾁H
	 */
	public static final NpcStringId HEY_WHAT_DO_YOU_EXPECT_OF_ME;

	/**
	 * ID: 42241<br>
	 * Message: ����Ⴀ���I�����I�����I�����A�o�čs���Ȃ����I
	 */
	public static final NpcStringId UGGGGGH_PUSH_ITS_NOT_COMING_OUT;

	/**
	 * ID: 42242<br>
	 * Message: ���A�O�ꂿ�܂�����I
	 */
	public static final NpcStringId AH_I_MISSED_THE_MARK;

	/**
	 * ID: 42243<br>
	 * Message: �������I�D�D�D�ދ����B�ǂ����s�����I
	 */
	public static final NpcStringId YAWWWWN_ITS_SO_BORING_HERE_WE_SHOULD_GO_AND_FIND_SOME_ACTION;

	/**
	 * ID: 42244<br>
	 * Message: �����A�Z��I����Ȃ��Ƃ��Ă���A���܂ł����Ă��܍߂��I���Ȃ����B
	 */
	public static final NpcStringId HEY_IF_YOU_CONTINUE_TO_WASTE_TIME_YOU_WILL_NEVER_FINISH_YOUR_PENANCE;

	/**
	 * ID: 42245<br>
	 * Message: ���O�͉��������Ȃ񂾂�H�����悤�ɉ������O�̂��Ƃ������Ȃ񂾂�I
	 */
	public static final NpcStringId I_KNOW_YOU_DONT_LIKE_ME_THE_FEELING_IS_MUTUAL;

	/**
	 * ID: 42246<br>
	 * Message: �����G�N�g�v���Y�� ���L���[���ŁA��t��肽���Ƃ��낾�B
	 */
	public static final NpcStringId I_NEED_A_DRINK;

	/**
	 * ID: 42247<br>
	 * Message: ������A������D�D�D����Ȃ��Ƃł́A�V�̕�������ׂĉ����O�ɁA�ƂɋA���ł͂Ȃ����B
	 */
	public static final NpcStringId OH_THIS_IS_DRAGGING_ON_TOO_LONG_AT_THIS_RATE_I_WONT_MAKE_IT_HOME_BEFORE_THE_SEVEN_SEALS_ARE_BROKEN;

	/**
	 * ID: 45650<br>
	 * Message: $s1�͂������ɂȂ���������A�C�e��$s2���󂯎��܂����B
	 */
	public static final NpcStringId S1_RECEIVED_A_S2_ITEM_AS_A_REWARD_FROM_THE_SEPARATED_SOUL;

	/**
	 * ID: 45651<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �w����
	 */
	public static final NpcStringId SEALED_VORPAL_HELMET;

	/**
	 * ID: 45652<br>
	 * Message: ���󂳂ꂽ�o�[�y�X ���U�[ �w����
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_HELMET;

	/**
	 * ID: 45653<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �T�[�N���b�g
	 */
	public static final NpcStringId SEALED_VORPAL_CIRCLET;

	/**
	 * ID: 45654<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �u���X�g �v���[�g
	 */
	public static final NpcStringId SEALED_VORPAL_BREASTPLATE;

	/**
	 * ID: 45655<br>
	 * Message: ���󂳂ꂽ�o�[�y�X ���U�[ �v���[�g
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_BREASTPLATE;

	/**
	 * ID: 45656<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �`���[�j�b�N
	 */
	public static final NpcStringId SEALED_VORPAL_TUNIC;

	/**
	 * ID: 45657<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �Q�[�g��
	 */
	public static final NpcStringId SEALED_VORPAL_GAITERS;

	/**
	 * ID: 45658<br>
	 * Message: ���󂳂ꂽ�o�[�y�X ���U�[ ���M���X
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_LEGGING;

	/**
	 * ID: 45659<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �z�[�X
	 */
	public static final NpcStringId SEALED_VORPAL_STOCKING;

	/**
	 * ID: 45660<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �K���g���b�g
	 */
	public static final NpcStringId SEALED_VORPAL_GAUNTLET;

	/**
	 * ID: 45661<br>
	 * Message: ���󂳂ꂽ�o�[�y�X ���U�[ �O���[�u
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_GLOVES;

	/**
	 * ID: 45662<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �O���[�u
	 */
	public static final NpcStringId SEALED_VORPAL_GLOVES;

	/**
	 * ID: 45663<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �u�[�c
	 */
	public static final NpcStringId SEALED_VORPAL_BOOTS;

	/**
	 * ID: 45664<br>
	 * Message: ���󂳂ꂽ�o�[�y�X ���U�[ �u�[�c
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_BOOTS;

	/**
	 * ID: 45665<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �V���[�Y
	 */
	public static final NpcStringId SEALED_VORPAL_SHOES;

	/**
	 * ID: 45666<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �V�[���h
	 */
	public static final NpcStringId SEALED_VORPAL_SHIELD;

	/**
	 * ID: 45667<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �V�M��
	 */
	public static final NpcStringId SEALED_VORPAL_SIGIL;

	/**
	 * ID: 45668<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �����O
	 */
	public static final NpcStringId SEALED_VORPAL_RING;

	/**
	 * ID: 45669<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �C�A�����O
	 */
	public static final NpcStringId SEALED_VORPAL_EARRING;

	/**
	 * ID: 45670<br>
	 * Message: ���󂳂ꂽ�o�[�y�X �l�b�N���X
	 */
	public static final NpcStringId SEALED_VORPAL_NECKLACE;

	/**
	 * ID: 45671<br>
	 * Message: �y���G�� �\�[�h
	 */
	public static final NpcStringId PERIEL_SWORD;

	/**
	 * ID: 45672<br>
	 * Message: �X�J�� �G�b�W
	 */
	public static final NpcStringId SKULL_EDGE;

	/**
	 * ID: 45673<br>
	 * Message: ���B�O�B�b�N �A�b�N�X
	 */
	public static final NpcStringId VIGWIK_AXE;

	/**
	 * ID: 45674<br>
	 * Message: �C�[���� �f�C�e�B �}�E��
	 */
	public static final NpcStringId DEVILISH_MAUL;

	/**
	 * ID: 45675<br>
	 * Message: �t�F�U�[�A�C �u���[�h
	 */
	public static final NpcStringId FEATHER_EYE_BLADE;

	/**
	 * ID: 45676<br>
	 * Message: �I�N�g �N���[
	 */
	public static final NpcStringId OCTO_CLAW;

	/**
	 * ID: 45677<br>
	 * Message: �_�u���g�p�@�X�s�A�[
	 */
	public static final NpcStringId DOUBLETOP_SPEAR;

	/**
	 * ID: 45678<br>
	 * Message: �L���[�e�B�N��
	 */
	public static final NpcStringId RISING_STAR;

	/**
	 * ID: 45679<br>
	 * Message: �u���b�N ���B�T�[�W��
	 */
	public static final NpcStringId BLACK_VISAGE;

	/**
	 * ID: 45680<br>
	 * Message: �x�j�v�����g �\�[�h
	 */
	public static final NpcStringId VENIPLANT_SWORD;

	/**
	 * ID: 45681<br>
	 * Message: �X�J�� �J���j�E�� �{�E
	 */
	public static final NpcStringId SKULL_CARNIUM_BOW;

	/**
	 * ID: 45682<br>
	 * Message: �W�F���e�C�� ���C�s�A
	 */
	public static final NpcStringId GEMTAIL_RAPIER;

	/**
	 * ID: 45683<br>
	 * Message: �t�B�� �C�����B���V�u�� �u���[�h
	 */
	public static final NpcStringId FINALE_BLADE;

	/**
	 * ID: 45684<br>
	 * Message: ���[�h �J�b�^�[ �N���X�{�E
	 */
	public static final NpcStringId DOMINION_CROSSBOW;

	/**
	 * ID: 45685<br>
	 * Message: �j�����ꂽ���틭���X�N���[���FS�O���[�h
	 */
	public static final NpcStringId BLESSED_WEAPON_ENCHANT_SCROLL_S_GRADE;

	/**
	 * ID: 45686<br>
	 * Message: �j�����ꂽ�h����X�N���[���FS�O���[�h
	 */
	public static final NpcStringId BLESSED_ARMOR_ENCHANT_SCROLL_S_GRADE;

	/**
	 * ID: 45687<br>
	 * Message: �΂̐���
	 */
	public static final NpcStringId FIRE_CRYSTAL;

	/**
	 * ID: 45688<br>
	 * Message: ���̐���
	 */
	public static final NpcStringId WATER_CRYSTAL;

	/**
	 * ID: 45689<br>
	 * Message: �n�̐���
	 */
	public static final NpcStringId EARTH_CRYSTAL;

	/**
	 * ID: 45690<br>
	 * Message: ���̐���
	 */
	public static final NpcStringId WIND_CRYSTAL;

	/**
	 * ID: 45691<br>
	 * Message: ���̐���
	 */
	public static final NpcStringId HOLY_CRYSTAL;

	/**
	 * ID: 45692<br>
	 * Message: �ł̐���
	 */
	public static final NpcStringId DARK_CRYSTAL;

	/**
	 * ID: 45693<br>
	 * Message: ���틭���X�N���[���FS�O���[�h
	 */
	public static final NpcStringId WEAPON_ENCHANT_SCROLL_S_GRADE;

	/**
	 * ID: 46350<br>
	 * Message: ���A�U���D�D�D$s1�A���A���O�D�D�D$s2
	 */
	public static final NpcStringId ATT_ATTACK_S1_RO_ROGUE_S2;

	/**
	 * ID: 50110<br>
	 * Message: ##########�r���S�I##########
	 */
	public static final NpcStringId BINGO;

	/**
	 * ID: 50338<br>
	 * Message: ���Ɩ��_�I
	 */
	public static final NpcStringId BLOOD_AND_HONOR;

	/**
	 * ID: 50339<br>
	 * Message: �鍑�̈�Y�������҂ɐ_�̍ق����I
	 */
	public static final NpcStringId CURSE_OF_THE_GODS_ON_THE_ONE_THAT_DEFILES_THE_PROPERTY_OF_THE_EMPIRE;

	/**
	 * ID: 50340<br>
	 * Message: �푈�Ǝ��I
	 */
	public static final NpcStringId WAR_AND_DEATH;

	/**
	 * ID: 50341<br>
	 * Message: ��]�ƌ����I
	 */
	public static final NpcStringId AMBITION_AND_POWER;

	/**
	 * ID: 50503<br>
	 * Message: $s1��$s2�����̍ՓT��$s3�_���L�^���܂����B
	 */
	public static final NpcStringId S1_HAS_WON_THE_MAIN_EVENT_FOR_PLAYERS_UNDER_LEVEL_S2_AND_EARNED_S3_POINTS;

	/**
	 * ID: 50504<br>
	 * Message: $s1���������N���X�̍ՓT��$s2�_���L�^���܂����B
	 */
	public static final NpcStringId S1_HAS_EARNED_S2_POINTS_IN_THE_MAIN_EVENT_FOR_UNLIMITED_LEVELS;

	/**
	 * ID: 50701<br>
	 * Message: ���ׂ̒���
	 */
	public static final NpcStringId INTO_THE_CHAOS;

	/**
	 * ID: 50851<br>
	 * Message: �N�G�X�g�𖳎��N���A�����̂ŁA$s1�̌��������l���l�����܂����B
	 */
	public static final NpcStringId YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLANS_REPUTATION_SCORE;

	/**
	 * ID: 60000<br>
	 * Message: ���Ă݂�A�Δ��ɉ΂������B
	 */
	public static final NpcStringId THE_FURNACE_WILL_GO_OUT_WATCH_AND_SEE;

	/**
	 * ID: 60001<br>
	 * Message: �c��1�����I
	 */
	public static final NpcStringId THERES_ABOUT_1_MINUTE_LEFT;

	/**
	 * ID: 60002<br>
	 * Message: �c��10�b�����Ȃ��I
	 */
	public static final NpcStringId THERES_JUST_10_SECONDS_LEFT;

	/**
	 * ID: 60003<br>
	 * Message: �ق�A�Δ��ɉ΂����Ă݂�B
	 */
	public static final NpcStringId NOW_LIGHT_THE_FURNACES_FIRE;

	/**
	 * ID: 60004<br>
	 * Message: �^�C���I�[�o�[�Ŏ��s���B������񒧂ނ͓̂�������B
	 */
	public static final NpcStringId TIME_IS_UP_AND_YOU_HAVE_FAILED_ANY_MORE_WILL_BE_DIFFICULT;

	/**
	 * ID: 60005<br>
	 * Message: ����͂������B�������I
	 */
	public static final NpcStringId OH_YOUVE_SUCCEEDED;

	/**
	 * ID: 60006<br>
	 * Message: �����A���s���I�ł��A�܂��܂���蒼�����������I
	 */
	public static final NpcStringId AH_IS_THIS_FAILURE_BUT_IT_LOOKS_LIKE_I_CAN_KEEP_GOING;

	/**
	 * ID: 60007<br>
	 * Message: ���s���B������񒧂ނ͓̂�������B
	 */
	public static final NpcStringId AH_IVE_FAILED_GOING_FURTHER_WILL_BE_DIFFICULT;

	/**
	 * ID: 60008<br>
	 * Message: �o�����X�̉Δ�
	 */
	public static final NpcStringId FURNACE_OF_BALANCE;

	/**
	 * ID: 60009<br>
	 * Message: ���̉Δ�
	 */
	public static final NpcStringId FURNACE_OF_PROTECTION;

	/**
	 * ID: 60010<br>
	 * Message: ���u�̉Δ�
	 */
	public static final NpcStringId FURNACE_OF_WILL;

	/**
	 * ID: 60011<br>
	 * Message: ���͂̉Δ�
	 */
	public static final NpcStringId FURNACE_OF_MAGIC;

	/**
	 * ID: 60012<br>
	 * Message: ���Ȃ�C���Y�������܂����B
	 */
	public static final NpcStringId DIVINE_ENERGY_IS_BEGINNING_TO_ENCIRCLE;

	/**
	 * ID: 60013<br>
	 * Message: �\���A�̖��_�̂��߂ɁI
	 */
	public static final NpcStringId FOR_THE_GLORY_OF_SOLINA;

	/**
	 * ID: 60014<br>
	 * Message: ���̒n�ɑ��𓥂ݓ��ꂽ���ׂĂ̎҂�f�߂���I
	 */
	public static final NpcStringId PUNISH_ALL_THOSE_WHO_TREAD_FOOTSTEPS_IN_THIS_PLACE;

	/**
	 * ID: 60015<br>
	 * Message: ��X�͐^���̌��ł���A�\���i�̌��ł���B
	 */
	public static final NpcStringId WE_ARE_THE_SWORD_OF_TRUTH_THE_SWORD_OF_SOLINA;

	/**
	 * ID: 60016<br>
	 * Message: �\���A�̖��_�̂��߂Ɍ�����낤�B
	 */
	public static final NpcStringId WE_RAISE_OUR_BLADES_FOR_THE_GLORY_OF_SOLINA;

	/**
	 * ID: 60018<br>
	 * Message: ����Ȃɑ����s���Ȃ��ŁB
	 */
	public static final NpcStringId HEY_DONT_GO_SO_FAST;

	/**
	 * ID: 60019<br>
	 * Message: ���Ă����܂���B
	 */
	public static final NpcStringId ITS_HARD_TO_FOLLOW;

	/**
	 * ID: 60020<br>
	 * Message: �n�A�n�A�A�������Ă������Ă����܂���B
	 */
	public static final NpcStringId HUFF_HUFF_YOURE_TOO_FAST_I_CANT_FOLLOW_ANYMORE;

	/**
	 * ID: 60021<br>
	 * Message: �����A�����͌��o�������邼�B
	 */
	public static final NpcStringId AH_I_THINK_I_REMEMBER_THIS_PLACE;

	/**
	 * ID: 60022<br>
	 * Message: �����A��C�����������I
	 */
	public static final NpcStringId AH_FRESH_AIR;

	/**
	 * ID: 60023<br>
	 * Message: �����ŉ����Ă��ł����B
	 */
	public static final NpcStringId WHAT_WERE_YOU_DOING_HERE;

	/**
	 * ID: 60024<br>
	 * Message: �܂��A�����낵�����B���������Ď��Ɠ������󕨂��H
	 */
	public static final NpcStringId I_GUESS_YOURE_THE_SILENT_TYPE_THEN_ARE_YOU_LOOKING_FOR_TREASURE_LIKE_ME;

	/**
	 * ID: 60403<br>
	 * Message: �����ĂԂ̂͒N���H
	 */
	public static final NpcStringId WHO_IS_CALLING_ME;

	/**
	 * ID: 60404<br>
	 * Message: �łƌ��͕\����̂Ȃ񂾁B
	 */
	public static final NpcStringId CAN_LIGHT_EXIST_WITHOUT_DARKNESS;

	/**
	 * ID: 60903<br>
	 * Message: �E�_���l�̊�͔������Ȃ��I
	 */
	public static final NpcStringId YOU_CANT_AVOID_THE_EYES_OF_UDAN;

	/**
	 * ID: 60904<br>
	 * Message: �E�_���l�͂��łɂ��O�̊�������ɂȂ����I
	 */
	public static final NpcStringId UDAN_HAS_ALREADY_SEEN_YOUR_FACE;

	/**
	 * ID: 61050<br>
	 * Message: ���̖��́I���ꑦ�����ƒÔg�̗́I�t�炦�Ύ�����̂݁I
	 */
	public static final NpcStringId THE_MAGICAL_POWER_OF_WATER_COMES_FROM_THE_POWER_OF_STORM_AND_HAIL_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU;

	/**
	 * ID: 61051<br>
	 * Message: �����̗͂���܂��Ă���B�M�l��̋V���͎��s���I
	 */
	public static final NpcStringId THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED;

	/**
	 * ID: 61503<br>
	 * Message: �A�Z�t�@�l�̊�͔������Ȃ��I
	 */
	public static final NpcStringId YOU_CANT_AVOID_THE_EYES_OF_ASEFA;

	/**
	 * ID: 61504<br>
	 * Message: �A�Z�t�@�l�͂��łɂ��O�̊�������ɂȂ����I
	 */
	public static final NpcStringId ASEFA_HAS_ALREADY_SEEN_YOUR_FACE;

	/**
	 * ID: 61650<br>
	 * Message: �΂̖��͂́A���Ȃ킿�Ή��Ɨn��̗́I�t�炦�Ύ�����̂݁I
	 */
	public static final NpcStringId THE_MAGICAL_POWER_OF_FIRE_IS_ALSO_THE_POWER_OF_FLAMES_AND_LAVA_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU;

	/**
	 * ID: 62503<br>
	 * Message: �����������ȓ���������D�D�D
	 */
	public static final NpcStringId I_SMELL_SOMETHING_DELICIOUS;

	/**
	 * ID: 62504<br>
	 * Message: �������I
	 */
	public static final NpcStringId OOOH;

	/**
	 * ID: 66001<br>
	 * Message: �t���[���������~����
	 */
	public static final NpcStringId AIDING_THE_FLORAN_VILLAGE;

	/**
	 * ID: 66300<br>
	 * Message: �J�[�h�Ȃ�
	 */
	public static final NpcStringId NO_SUCH_CARD;

	/**
	 * ID: 68801<br>
	 * Message: �G���N���b�L�[�P������ނ���I
	 */
	public static final NpcStringId DEFEAT_THE_ELROKIAN_RAIDERS;

	/**
	 * ID: 70851<br>
	 * Message: ���l�A�������͂�낵���ł��傤���B
	 */
	public static final NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD;

	/**
	 * ID: 70852<br>
	 * Message: $s1�A�o���I
	 */
	public static final NpcStringId S1_NOW_DEPART;

	/**
	 * ID: 70853<br>
	 * Message: �T�C�A�X��K�˂Ă��������B
	 */
	public static final NpcStringId GO_FIND_SAIUS;

	/**
	 * ID: 70854<br>
	 * Message: �F�̏O�A�悭�����I�܂��Ȃ��̎�l�ɂ��Ȃ�ɂȂ���l���f�����n����|���ꂽ���I�F�A���S���ĕ�炷���悢�I
	 */
	public static final NpcStringId LISTEN_YOU_VILLAGERS_OUR_LIEGE_WHO_WILL_SOON_BECOME_A_LORD_HAS_DEFEATED_THE_HEADLESS_KNIGHT_YOU_CAN_NOW_REST_EASY;

	/**
	 * ID: 70855<br>
	 * Message: $s1�I�Ă߂��A�悭�����̕����Ɏ���o���Ă��ꂽ�ȁI
	 */
	public static final NpcStringId S1_DO_YOU_DARE_DEFY_MY_SUBORDINATES;

	/**
	 * ID: 70856<br>
	 * Message: �⋋��W�Q����C���͂����I���Ȃ̂��B
	 */
	public static final NpcStringId DOES_MY_MISSION_TO_BLOCK_THE_SUPPLIES_END_HERE;

	/**
	 * ID: 70859<br>
	 * Message: $s1�l�̓O���[�f�B�I��̗̎�ɂ��Ȃ�ɂȂ�܂����B�O���[�f�B�I�̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_GLUDIO_LONG_MAY_HE_REIGN;

	/**
	 * ID: 70957<br>
	 * Message: �o���Ă�I���͐�΋����˂�����ȁI
	 */
	public static final NpcStringId YOULL_SEE_I_WONT_FORGIVE_YOU_NEXT_TIME;

	/**
	 * ID: 70959<br>
	 * Message: $s1�l�̓O���[�f�B�I��̗̎�ɂ��Ȃ�ɂȂ�܂����B�O���[�f�B�I�̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_DION_LONG_MAY_HE_REIGN;

	/**
	 * ID: 71052<br>
	 * Message: �G���P�I����1�l�c�炸�������I
	 */
	public static final NpcStringId ITS_THE_ENEMY_NO_MERCY;

	/**
	 * ID: 71053<br>
	 * Message: ��������񂾁I�܂��������̕����D������Ȃ����I
	 */
	public static final NpcStringId WHAT_ARE_YOU_DOING_WE_ARE_STILL_SUPERIOR;

	/**
	 * ID: 71054<br>
	 * Message: �������I���̋w�͕K���D�D�D
	 */
	public static final NpcStringId HOW_INFURIATING_THIS_ENEMY;

	/**
	 * ID: 71059<br>
	 * Message: $s1�l�̓M������̑��̗̎�ɂ��Ȃ�ɂȂ�܂����B�M�����̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GIRAN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GIRAN;

	/**
	 * ID: 71151<br>
	 * Message: ���l�A�ǂ���ɂ�������Ⴂ�܂����B
	 */
	public static final NpcStringId MY_LIEGE_WHERE_ARE_YOU;

	/**
	 * ID: 71159<br>
	 * Message: $s1�l�̓C���i�h������̑��̗̎�ɂ��Ȃ�ɂȂ�܂����B�I�[�����̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_INNADRIL_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_INNADRIL;

	/**
	 * ID: 71252<br>
	 * Message: �l�r�����C�g �I�[�u�����ׂČ����܂����B
	 */
	public static final NpcStringId YOU_HAVE_FOUND_ALL_THE_NEBULITE_ORBS;

	/**
	 * ID: 71259<br>
	 * Message: $s1�l�̓I�[������̑��̗̎�ɂ��Ȃ�ɂȂ�܂����B�I�[�����̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_OREN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_OREN;

	/**
	 * ID: 71351<br>
	 * Message: $s1�l�̓A�f����̑��̗̎�ɂ��Ȃ�ɂȂ�܂����B�A�f���̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_ADEN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_ADEN;

	/**
	 * ID: 71459<br>
	 * Message: $s1�l�̓V���`���b�c�K���g��̑��̗̎�ɂ��Ȃ�ɂȂ�܂����B�V���`���b�c�K���g�̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_SCHUTTGART_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_SCHUTTGART;

	/**
	 * ID: 71551<br>
	 * Message: $s1�A���O�̂��Ƃ͖Y��Ȃ����낤�B
	 */
	public static final NpcStringId S1_I_WILL_REMEMBER_YOU;

	/**
	 * ID: 71653<br>
	 * Message: �t���f���b�N�����l�ɂ��ڂɂ����肽���Ɛ\���Ă���܂��B
	 */
	public static final NpcStringId FREDERICK_IS_LOOKING_FOR_YOU_MY_LIEGE;

	/**
	 * ID: 71654<br>
	 * Message: �ӂ��ӂ��ӂ��B��X�Ǝ�������Ɋ�������Ƃł��v�������I
	 */
	public static final NpcStringId HO_HO_DID_YOU_THINK_YOU_COULD_REALLY_STOP_TRADING_WITH_US;

	/**
	 * ID: 71655<br>
	 * Message: �_�a�ɍU�ߓ���܂����B
	 */
	public static final NpcStringId YOU_HAVE_CHARGED_INTO_THE_TEMPLE;

	/**
	 * ID: 71656<br>
	 * Message: �׋��̐_�a�̎׋��k��|���Ă���Œ��ł��B
	 */
	public static final NpcStringId YOU_ARE_IN_THE_MIDST_OF_DEALING_WITH_THE_HERETIC_OF_HERETIC_TEMPLE;

	/**
	 * ID: 71657<br>
	 * Message: �׋��̐_�a�������Ɋׂ����܂��B
	 */
	public static final NpcStringId THE_HERETIC_TEMPLE_IS_DESCENDING_INTO_CHAOS;

	/**
	 * ID: 71659<br>
	 * Message: $s1�l�̓��E����̑��̗̎�ɂ��Ȃ�ɂȂ�܂����B���E���̒n�ɉh������I
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_RUNE_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_RUNE;

	/**
	 * ID: 71751<br>
	 * Message: $s1! �̒n�̂��߂ɕ�������I
	 */
	public static final NpcStringId S1_RAISE_YOUR_WEAPONS_FOR_THE_SAKE_OF_THE_TERRITORY;

	/**
	 * ID: 71752<br>
	 * Message: $s1! �푈�͏I������B���A�����̂��߂Ɍ������߂�I
	 */
	public static final NpcStringId S1_THE_WAR_IS_OVER_LOWER_YOUR_SWORD_FOR_THE_SAKE_OF_THE_FUTURE;

	/**
	 * ID: 71755<br>
	 * Message: �̒n�̃o�b�W90�A�l�����l450�_�A$s1�A�f�i�B
	 */
	public static final NpcStringId N91_TERRITORY_BADGES_450_SCORES_IN_INDIVIDUAL_FAME_AND_S2_ADENAS;

	/**
	 * ID: 72903<br>
	 * Message: �b���N�G�X�g�̔ԍ���$s1�Amemostate1��$s2�Amemostate2��$s3�ł��B
	 */
	public static final NpcStringId THE_MERCENARY_QUEST_NUMBER_IS_S1_MEMOSTATE1_IS_S2_AND_MEMOSTATE2_IS_S3;

	/**
	 * ID: 72904<br>
	 * Message: user_connected  �C�x���g���������ASiege Id $s1�A728��memo2�̒l$s2�A729/memo2$s3�A255/memo1 $s4�ł��B
	 */
	public static final NpcStringId USER_CONNECTED_EVENT_OCCURRENCE_SUCCESS_SIEGE_ID_IS_S1_NUMBER_728_MEMO2_IS_S2_729_MEMO2_IS_S3_AND_255_MEMO1_IS_S4;

	/**
	 * ID: 72905<br>
	 * Message: �̒n���΋@�Adying�C�x���g���΋@�̗̒nID$s1�A�p�[�e�B��� $s2
	 */
	public static final NpcStringId TERRITORY_CATAPULT_DYING_EVENT_CATAPULTS_TERRITORY_ID_S1_PARTY_STATUS_S2;

	/**
	 * ID: 72951<br>
	 * Message: �O���[�f�B�I�̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_GLUDIO;

	/**
	 * ID: 72952<br>
	 * Message: �f�B�I���̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_DION;

	/**
	 * ID: 72953<br>
	 * Message: �M�����̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_GIRAN;

	/**
	 * ID: 72954<br>
	 * Message: �I�[�����̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_OREN;

	/**
	 * ID: 72955<br>
	 * Message: �A�f���̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_ADEN;

	/**
	 * ID: 72956<br>
	 * Message: �C���i�h�����̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_INNADRIL;

	/**
	 * ID: 72957<br>
	 * Message: �S�_�[�h�̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_GODDARD;

	/**
	 * ID: 72958<br>
	 * Message: ���E���̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_RUNE;

	/**
	 * ID: 72959<br>
	 * Message: �V���`���b�c�K���g�̗̒n���΋@�����I
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_SCHUTTGART;

	/**
	 * ID: 72961<br>
	 * Message: �O���[�f�B�I�̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_GLUDIO_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72962<br>
	 * Message: �f�B�I���̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_DION_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72963<br>
	 * Message: �M�����̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_GIRAN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72964<br>
	 * Message: �I�[�����̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_OREN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72965<br>
	 * Message: �A�f���̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_ADEN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72966<br>
	 * Message: �C���i�h�����̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_INNADRIL_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72967<br>
	 * Message: �S�_�[�h�̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_GODDARD_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72968<br>
	 * Message: ���E���̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_RUNE_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72969<br>
	 * Message: �V���`���b�c�K���g�̗̒n���΋@���j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_CATAPULT_OF_SCHUTTGART_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72981<br>
	 * Message: �O���[�f�B�I
	 */
	public static final NpcStringId GLUDIO;

	/**
	 * ID: 72982<br>
	 * Message: �f�B�I��
	 */
	public static final NpcStringId DION;

	/**
	 * ID: 72983<br>
	 * Message: �M����
	 */
	public static final NpcStringId GIRAN;

	/**
	 * ID: 72984<br>
	 * Message: �I�[����
	 */
	public static final NpcStringId OREN;

	/**
	 * ID: 72985<br>
	 * Message: �A�f��
	 */
	public static final NpcStringId ADEN;

	/**
	 * ID: 72986<br>
	 * Message: �C���i�h����
	 */
	public static final NpcStringId INNADRIL;

	/**
	 * ID: 72987<br>
	 * Message: �S�_�[�h
	 */
	public static final NpcStringId GODDARD;

	/**
	 * ID: 72988<br>
	 * Message: ���E��
	 */
	public static final NpcStringId RUNE;

	/**
	 * ID: 72989<br>
	 * Message: �V���`���b�c�K���g
	 */
	public static final NpcStringId SCHUTTGART;

	/**
	 * ID: 73051<br>
	 * Message: �O���[�f�B�I�̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GLUDIO;

	/**
	 * ID: 73052<br>
	 * Message: �f�B�I���̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_DION;

	/**
	 * ID: 73053<br>
	 * Message: �M�����̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GIRAN;

	/**
	 * ID: 73054<br>
	 * Message: �I�[�����̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_OREN;

	/**
	 * ID: 73055<br>
	 * Message: �A�f���̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_ADEN;

	/**
	 * ID: 73056<br>
	 * Message: �C���i�h�����̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_INNADRIL;

	/**
	 * ID: 73057<br>
	 * Message: �S�_�[�h�̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GODDARD;

	/**
	 * ID: 73058<br>
	 * Message: ���E���̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_RUNE;

	/**
	 * ID: 73059<br>
	 * Message: �V���`���b�c�K���g�̕⋋�i�ۊǔ������I
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_SCHUTTGART;

	/**
	 * ID: 73061<br>
	 * Message: �O���[�f�B�I�̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_GLUDIO_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73062<br>
	 * Message: �f�B�I���̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_DION_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73063<br>
	 * Message: �M�����̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_GIRAN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73064<br>
	 * Message: �I�[�����̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_OREN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73065<br>
	 * Message: �A�f���̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_ADEN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73066<br>
	 * Message: �C���i�h�����̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_INNADRIL_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73067<br>
	 * Message: �S�_�[�h�̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_GODDARD_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73068<br>
	 * Message: ���E���̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_RUNE_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73069<br>
	 * Message: �V���`���b�c�K���g�̕⋋�i�ۊǔ����j�󂳂ꂽ�I
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_SCHUTTGART_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73151<br>
	 * Message: �O���[�f�B�I�R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO;

	/**
	 * ID: 73152<br>
	 * Message: �f�B�I���R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_DION;

	/**
	 * ID: 73153<br>
	 * Message: �M�����R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN;

	/**
	 * ID: 73154<br>
	 * Message: �I�[�����R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_OREN;

	/**
	 * ID: 73155<br>
	 * Message: �A�f���R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN;

	/**
	 * ID: 73156<br>
	 * Message: �C���i�h�����R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL;

	/**
	 * ID: 73157<br>
	 * Message: �S�_�[�h�R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD;

	/**
	 * ID: 73158<br>
	 * Message: ���E���R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE;

	/**
	 * ID: 73159<br>
	 * Message: �V���`���b�c�K���g�R���A���������I
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART;

	/**
	 * ID: 73161<br>
	 * Message: �O���[�f�B�I�R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;

	/**
	 * ID: 73162<br>
	 * Message: �f�B�I���R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_DION_IS_DEAD;

	/**
	 * ID: 73163<br>
	 * Message: �M�����R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;

	/**
	 * ID: 73164<br>
	 * Message: �I�[�����R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;

	/**
	 * ID: 73165<br>
	 * Message: �A�f���R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;

	/**
	 * ID: 73166<br>
	 * Message: �C���i�h�����R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;

	/**
	 * ID: 73167<br>
	 * Message: �S�_�[�h�R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;

	/**
	 * ID: 73168<br>
	 * Message: ���E���R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;

	/**
	 * ID: 73169<br>
	 * Message: �V���`���b�c�K���g�R���A���������񂾁I
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;

	/**
	 * ID: 73251<br>
	 * Message: �O���[�f�B�I�@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO;

	/**
	 * ID: 73252<br>
	 * Message: �f�B�I���@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION;

	/**
	 * ID: 73253<br>
	 * Message: �M�����@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN;

	/**
	 * ID: 73254<br>
	 * Message: �I�[�����@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN;

	/**
	 * ID: 73255<br>
	 * Message: �A�f���@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN;

	/**
	 * ID: 73256<br>
	 * Message: �C���i�h�����@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL;

	/**
	 * ID: 73257<br>
	 * Message: �S�_�[�h�@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD;

	/**
	 * ID: 73258<br>
	 * Message: ���E���@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE;

	/**
	 * ID: 73259<br>
	 * Message: �V���`���b�c�K���g�@���A���������I
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART;

	/**
	 * ID: 73261<br>
	 * Message: �O���[�f�B�I�@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;

	/**
	 * ID: 73262<br>
	 * Message: �f�B�I���@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION_IS_DEAD;

	/**
	 * ID: 73263<br>
	 * Message: �M�����@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;

	/**
	 * ID: 73264<br>
	 * Message: �I�[�����@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;

	/**
	 * ID: 73265<br>
	 * Message: �A�f���@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;

	/**
	 * ID: 73266<br>
	 * Message: �C���i�h�����@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;

	/**
	 * ID: 73267<br>
	 * Message: �S�_�[�h�@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;

	/**
	 * ID: 73268<br>
	 * Message: ���E���@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;

	/**
	 * ID: 73269<br>
	 * Message: �V���`���b�c�K���g�@���A���������񂾁I
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;

	/**
	 * ID: 73351<br>
	 * Message: �O���[�f�B�I�o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO;

	/**
	 * ID: 73352<br>
	 * Message: �f�B�I���o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION;

	/**
	 * ID: 73353<br>
	 * Message: �M�����o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN;

	/**
	 * ID: 73354<br>
	 * Message: �I�[�����o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN;

	/**
	 * ID: 73355<br>
	 * Message: �A�f���o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN;

	/**
	 * ID: 73356<br>
	 * Message: �C���i�h�����o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL;

	/**
	 * ID: 73357<br>
	 * Message: �S�_�[�h�o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD;

	/**
	 * ID: 73358<br>
	 * Message: ���E���o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE;

	/**
	 * ID: 73359<br>
	 * Message: �V���`���b�c�K���g�o�ϘA���������I
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART;

	/**
	 * ID: 73361<br>
	 * Message: �O���[�f�B�I�o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;

	/**
	 * ID: 73362<br>
	 * Message: �f�B�I���o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION_IS_DEAD;

	/**
	 * ID: 73363<br>
	 * Message: �M�����o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;

	/**
	 * ID: 73364<br>
	 * Message: �I�[�����o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;

	/**
	 * ID: 73365<br>
	 * Message: �A�f���o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;

	/**
	 * ID: 73366<br>
	 * Message: �C���i�h�����o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;

	/**
	 * ID: 73367<br>
	 * Message: �S�_�[�h�o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;

	/**
	 * ID: 73368<br>
	 * Message: ���E���o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;

	/**
	 * ID: 73369<br>
	 * Message: �V���`���b�c�K���g�o�ϘA���������񂾁I
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;

	/**
	 * ID: 73451<br>
	 * Message: ���̏��ł���G�R�̃i�C�g��$s1�l�|���I
	 */
	public static final NpcStringId DEFEAT_S1_ENEMY_KNIGHTS;

	/**
	 * ID: 73461<br>
	 * Message: �G�R�̃i�C�g��$s1�l��$s2�l�|�����B
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_KNIGHTS;

	/**
	 * ID: 73462<br>
	 * Message: �G�̃V�[���h���т����I
	 */
	public static final NpcStringId YOU_WEAKENED_THE_ENEMYS_DEFENSE;

	/**
	 * ID: 73551<br>
	 * Message: �G�R�̃E�H�[���A�[�ƃ��[�O��$s1�l�|���I
	 */
	public static final NpcStringId DEFEAT_S1_WARRIORS_AND_ROGUES;

	/**
	 * ID: 73561<br>
	 * Message: $s1�l��$s2�l�̍U���͂���点���B
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_WARRIORS_AND_ROGUES;

	/**
	 * ID: 73562<br>
	 * Message: �G�̍U���͂���点���B
	 */
	public static final NpcStringId YOU_WEAKENED_THE_ENEMYS_ATTACK;

	/**
	 * ID: 73651<br>
	 * Message: �G�R�̃E�B�U�[�h�Ə����n���$s1�l�|���I
	 */
	public static final NpcStringId DEFEAT_S1_WIZARDS_AND_SUMMONERS;

	/**
	 * ID: 73661<br>
	 * Message: $s1�l��$s2�l�̖��͂���点���B
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_ENEMIES;

	/**
	 * ID: 73662<br>
	 * Message: �G�̖��͂���点���B
	 */
	public static final NpcStringId YOU_WEAKENED_THE_ENEMYS_MAGIC;

	/**
	 * ID: 73751<br>
	 * Message: �G�R�̎m�C�����߂�j������������҂ǂ���$s1�l�|���I
	 */
	public static final NpcStringId DEFEAT_S1_HEALERS_AND_BUFFERS;

	/**
	 * ID: 73761<br>
	 * Message: $s1�l��$s2�l�̏j����j�~�����B
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_HEALERS_AND_BUFFERS;

	/**
	 * ID: 73762<br>
	 * Message: �G�̎m�C���������I
	 */
	public static final NpcStringId YOU_HAVE_WEAKENED_THE_ENEMYS_SUPPORT;

	/**
	 * ID: 73851<br>
	 * Message: �����̃J�M�ł���G�R�̃E�H�[�X�~�X�ƃI�[�o�[���[�h��$s1�l�|���I
	 */
	public static final NpcStringId DEFEAT_S1_WARSMITHS_AND_OVERLORDS;

	/**
	 * ID: 73861<br>
	 * Message: $s1�l��$s2�l�̃J�M��Еt�����B
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_WARSMITHS_AND_OVERLORDS;

	/**
	 * ID: 73862<br>
	 * Message: �G�̃J�M���󂵂��I
	 */
	public static final NpcStringId YOU_DESTROYED_THE_ENEMYS_PROFESSIONALS;

	/**
	 * ID: 99601<br>
	 * Message: �������`�������䂩���ȗ��̓��`���͉���T���ɏo�悤�`
	 */
	public static final NpcStringId TRA_LA_LA_TODAY_IM_GOING_TO_MAKE_ANOTHER_FUN_FILLED_TRIP_I_WONDER_WHAT_I_SHOULD_LOOK_FOR_THIS_TIME;

	/**
	 * ID: 99700<br>
	 * Message: �A�^�V�̂��ƌĂ񂾁H
	 */
	public static final NpcStringId WHATS_THIS_WHY_AM_I_BEING_DISTURBED;

	/**
	 * ID: 99701<br>
	 * Message: ����ɂ��́A�A�^�V�A�|���i�I��낵�����肢���܁`���I
	 */
	public static final NpcStringId TA_DA_HERE_I_AM;

	/**
	 * ID: 99702<br>
	 * Message: ���ӂӂ��A�A�^�V�̂��ƌĂ񂾁H
	 */
	public static final NpcStringId WHAT_ARE_YOU_LOOKING_AT;

	/**
	 * ID: 99703<br>
	 * Message: �݂�Ȃ��҂������܂����I�|���i�A�������I���ꂩ��ǂ�ǂ������Ⴄ�񂾂���I
	 */
	public static final NpcStringId IF_YOU_GIVE_ME_NECTAR_THIS_LITTLE_SQUASH_WILL_GROW_UP_QUICKLY;

	/**
	 * ID: 99704<br>
	 * Message: ����H�A�i�^�ɉ�̂͏��߂Ă�����H�A�^�V�A�|���i�I��낵���ˁI
	 */
	public static final NpcStringId ARE_YOU_MY_MOMMY;

	/**
	 * ID: 99705<br>
	 * Message: �A�^�V�A�A�i�^�̂��Ƃ�������悤�ɂ���΂邩��A�����ς������ĂˁI
	 */
	public static final NpcStringId FANCY_MEETING_YOU_HERE;

	/**
	 * ID: 99706<br>
	 * Message: �����p������H�G�b�I����ȃA�^�V�Ɉ����������Ă����́H
	 */
	public static final NpcStringId ARE_YOU_AFRAID_OF_THE_BIG_BAD_SQUASH;

	/**
	 * ID: 99707<br>
	 * Message: ���ӁB�������A�^�V�̎p�A����ȂɌ������`�H
	 */
	public static final NpcStringId IMPRESSIVE_ARENT_I;

	/**
	 * ID: 99708<br>
	 * Message: �A�i�^�ɌĂ΂�Ă����ɗ����̂�I�����������傤���`���I
	 */
	public static final NpcStringId OBEY_ME;

	/**
	 * ID: 99709<br>
	 * Message: ����ɂ��́A�A�^�V�A�|���i�I�����ɗ����̂͏��߂Ă��ȁH��낵���ˁI
	 */
	public static final NpcStringId RAISE_ME_WELL_AND_YOULL_BE_REWARDED_NEGLECT_ME_AND_SUFFER_THE_CONSEQUENCES;

	/**
	 * ID: 99710<br>
	 * Message: ���҂������I����ł́A�ϐg�̎��Ԃ�I
	 */
	public static final NpcStringId TRANSFORM;

	/**
	 * ID: 99711<br>
	 * Message: �ǂ��H�A�^�V�A�L���C�ɂȂ����H�H�L���C�ɂȂ������Č����āI
	 */
	public static final NpcStringId I_FEEL_DIFFERENT;

	/**
	 * ID: 99712<br>
	 * Message: ���n�������I�����A�ǂ�����ł����[����I
	 */
	public static final NpcStringId IM_BIGGER_NOW_BRING_IT_ON;

	/**
	 * ID: 99713<br>
	 * Message: �����D�D�D�M�����̂��S�g���삯����́I
	 */
	public static final NpcStringId IM_NOT_A_KID_ANYMORE;

	/**
	 * ID: 99714<br>
	 * Message: �A�^�V�̂��ƁA��؂ɂ��Ă���Ȃ��̂ˁD�D�D�n�Y�����Ⴆ�I
	 */
	public static final NpcStringId BIG_TIME;

	/**
	 * ID: 99716<br>
	 * Message: ���т������H�����C�������H����Ƃ��A�A�E�^�E�V�H���ւցA�����x�����Ă݂��������񂾁B
	 */
	public static final NpcStringId IM_ALL_GROWN_UP_NOW;

	/**
	 * ID: 99717<br>
	 * Message: 1�疜�A�f�i�����邩�狖���Ă��I������Ă����Â��H
	 */
	public static final NpcStringId IF_YOU_LET_ME_GO_ILL_BE_YOUR_BEST_FRIEND;

	/**
	 * ID: 99718<br>
	 * Message: �A�^�V�̒��ɔ�߂�ꂽ���̌��Ă݂����H
	 */
	public static final NpcStringId IM_CHUCK_FULL_OF_GOODNESS;

	/**
	 * ID: 99719<br>
	 * Message: �A�^�V�̍��~�������̂킩��H�킩���Ă�ł���H������A������̈����~�����́I
	 */
	public static final NpcStringId GOOD_JOB_NOW_WHAT_ARE_YOU_GOING_TO_DO;

	/**
	 * ID: 99720<br>
	 * Message: �˂��A���������l�N�^�[�������Ă��I�A�^�V�ւ̈����Ă���Ȃ��̂���Ȃ��ł���H
	 */
	public static final NpcStringId KEEP_IT_COMING;

	/**
	 * ID: 99721<br>
	 * Message: �����A�C�C�����B�C�����悢���I�����������傤�����I
	 */
	public static final NpcStringId THATS_WHAT_IM_TALKING_ABOUT;

	/**
	 * ID: 99722<br>
	 * Message: �A�i�^�̈��Ŗ�������銴���D�D�D�����Ɨ~�����́I
	 */
	public static final NpcStringId MAY_I_HAVE_SOME_MORE;

	/**
	 * ID: 99723<br>
	 * Message: �����I�܂Ƃ��ɂł��Ȃ��́H���ڂ�Ă邶��Ȃ��I�v���v��
	 */
	public static final NpcStringId THAT_HIT_THE_SPOT;

	/**
	 * ID: 99724<br>
	 * Message: ��`���`�����ς�I������A�A�^�V�ւ̈�������Ă��Ȃ��̂�I
	 */
	public static final NpcStringId I_FEEL_SPECIAL;

	/**
	 * ID: 99725<br>
	 * Message: �����A�����������������D�D�D�����Ƃ��傤�����I
	 */
	public static final NpcStringId I_THINK_ITS_WORKING;

	/**
	 * ID: 99726<br>
	 * Message: �C���b�I��������Ȃ��́I�w�^�N�\�I�����A�����ɂȂ����Ⴄ��I
	 */
	public static final NpcStringId YOU_DO_UNDERSTAND;

	/**
	 * ID: 99727<br>
	 * Message: �E���@�[���I���̂��������Ȃ�������`�D�D�D�˂��A���̖{���Ƀl�N�^�[�������́H
	 */
	public static final NpcStringId YUCK_WHAT_IS_THIS_HA_HA_JUST_KIDDING;

	/**
	 * ID: 99728<br>
	 * Message: �˂��A�������肵�Ă�I�L���C�Ɉ�Ă����Ȃ�A�l�N�^�[���L���C�ɂ�����̂���؂�I
	 */
	public static final NpcStringId A_TOTAL_OF_FIVE_AND_ILL_BE_TWICE_AS_ALIVE;

	/**
	 * ID: 99729<br>
	 * Message: ���ɖ������l�N�^�[���~�����́I
	 */
	public static final NpcStringId NECTAR_IS_SUBLIME;

	/**
	 * ID: 99730<br>
	 * Message: �A���b�I�A�^�V���A���@���ꂽ�`��͖��Ȃ̂��I���肢������炾���͂�߂āI
	 */
	public static final NpcStringId YOU_CALL_THAT_A_HIT;

	/**
	 * ID: 99731<br>
	 * Message: �A�^�V�������߂Ȃ��Ł`��߂Ă��I�@���Ȃ��ł��I�A�^�V�́A�A�i�^�̈����~�����́`
	 */
	public static final NpcStringId WHY_ARE_YOU_HITTING_ME_OUCH_STOP_IT_GIVE_ME_NECTAR;

	/**
	 * ID: 99732<br>
	 * Message: �A�i�^�̈����������Ȃ��́I���ꂶ��A�A�^�V�ނ����Ⴄ��I
	 */
	public static final NpcStringId STOP_OR_ILL_WILT;

	/**
	 * ID: 99733<br>
	 * Message: �A�^�V�Ƃ̈�����Ă�C�͂Ȃ��́H�A�^�V�́A�A�i�^�̈��̂��������l�N�^�[�����~�����́I
	 */
	public static final NpcStringId IM_NOT_FULLY_GROWN_YET_OH_WELL_DO_WHAT_YOU_WILL_ILL_FADE_AWAY_WITHOUT_NECTAR_ANYWAY;

	/**
	 * ID: 99734<br>
	 * Message: �܂������́I�ɂ��̂͂�Ȃ́I�l�N�^�[���~�����́I
	 */
	public static final NpcStringId GO_AHEAD_AND_HIT_ME_AGAIN_IT_WONT_DO_YOU_ANY_GOOD;

	/**
	 * ID: 99735<br>
	 * Message: ���̂܂܂��Ⴕ���ꂿ�Ⴄ�́I�����A�^�V���ǂ��Ȃ��Ă��悢���Ă����́H
	 */
	public static final NpcStringId WOE_IS_ME_IM_WILTING;

	/**
	 * ID: 99736<br>
	 * Message: �A�^�V�������ꂽ��A�Ȃɂ��c��Ȃ���H����ȂɃA�^�V�̂��ƌ����Ȃ́H����Ƃ��A�i�^�̈��͏I����Ă��܂����́H
	 */
	public static final NpcStringId IM_NOT_FULLY_GROWN_YET_HOW_ABOUT_SOME_NECTAR_TO_EASE_MY_PAIN;

	/**
	 * ID: 99737<br>
	 * Message: ���A�ʂ̂��ƍl���Ă��ł���H����Ȃ񂶂�A�A�i�^�̈��̓A�^�V�ɓ`����Ă��Ȃ��́I�����ƈ����āI
	 */
	public static final NpcStringId THE_END_IS_NEAR;

	/**
	 * ID: 99738<br>
	 * Message: �l�F�A�A�^�V�������Ă�`�l�N�^�[����x�����߂��ɂ������Ȃ�āA����m�炸���Ď��ʂ̂ƈꏏ��I
	 */
	public static final NpcStringId PRETTY_PLEASE_WITH_SUGAR_ON_TOP_GIVE_ME_SOME_NECTAR;

	/**
	 * ID: 99739<br>
	 * Message: ���̂܂܂��ƃA�^�V�A���܂�Ă����Ӗ����Ȃ��A������邵���Ȃ��̂ˁB���������߂����D�D�D
	 */
	public static final NpcStringId IF_I_DIE_WITHOUT_NECTAR_YOULL_GET_NOTHING;

	/**
	 * ID: 99740<br>
	 * Message: ���炵�߂��Ȃ́I���񂾂�O���Ă����́I30�b�ȓ��ɂ���Ȃ��Ȃ�A�����A�����Ⴄ��I
	 */
	public static final NpcStringId IM_FEELING_BETTER_ANOTHER_THIRTY_SECONDS_AND_ILL_BE_OUT_OF_HERE;

	/**
	 * ID: 99741<br>
	 * Message: ����20�b�I�A�^�V�ւ̈����Ă���Ȃ��񂾂����́H
	 */
	public static final NpcStringId TWENTY_SECONDS_AND_ITS_CIAO_BABY;

	/**
	 * ID: 99742<br>
	 * Message: �����A�c��10�b�I9�D�D�D8�D�D�D7�D�D�D
	 */
	public static final NpcStringId WOOHOO_JUST_TEN_SECONDS_LEFT_NINE_EIGHT_SEVEN;

	/**
	 * ID: 99743<br>
	 * Message: �l�N�^�[������Ȃ��Ȃ�A2����ɋA�����Ⴄ��I
	 */
	public static final NpcStringId GIVE_ME_NECTAR_OR_ILL_BE_GONE_IN_TWO_MINUTES;

	/**
	 * ID: 99744<br>
	 * Message: �l�N�^�[������Ȃ��Ȃ�A1����ɃT���i���Ȃ񂾂���I
	 */
	public static final NpcStringId GIVE_ME_NECTAR_OR_ILL_BE_GONE_IN_ONE_MINUTE;

	/**
	 * ID: 99745<br>
	 * Message: ���A�^�V�ˁA�A�i�^�̖����̂��Ƃ�z�����Ă����́B��������ˁA�����̃A�^�V�̓A�i�^�ɂ����ς�������Ă����́B�܂���������܂Ō��C�ł��Ă˂��I�����Ƃ���I
	 */
	public static final NpcStringId SO_LONG_SUCKERS;

	/**
	 * ID: 99746<br>
	 * Message: �����ĉ��Ȃ̂��킩��H�A�i�^�ɂ��ꂪ�����ł�����A������x������Ǝv���́B�܂������܂��傤�ˁI
	 */
	public static final NpcStringId IM_OUT_OF_HERE;

	/**
	 * ID: 99747<br>
	 * Message: �܂�Ȃ��I�A�^�V�A�����A��I����Ȃ񂶂�A���̃|���i�ɂ�������񂾂���I�����������������Ȃ��Ȃ��`���I
	 */
	public static final NpcStringId I_MUST_BE_GOING_HAVE_FUN_EVERYBODY;

	/**
	 * ID: 99748<br>
	 * Message: �����A���ʂ�̎��ԂȂ́B���Ɉ�������܂łɁA�A�^�V�ւ̈������ł����ĂˁI
	 */
	public static final NpcStringId TIME_IS_UP_PUT_YOUR_WEAPONS_DOWN;

	/**
	 * ID: 99749<br>
	 * Message: ����I�Ȉ����āA�΂��Ă���Ă������D�D�D����A������ă_�����Ǝv���́B�����܂ł��Ă��ꂽ�񂾂��ǁA�����s���ˁB�{���ɃS�����ˁB
	 */
	public static final NpcStringId GOOD_FOR_ME_BAD_FOR_YOU;

	/**
	 * ID: 99750<br>
	 * Message: �����A���������������F�ˁI
	 */
	public static final NpcStringId SOUNDTASTIC;

	/**
	 * ID: 99751<br>
	 * Message: �˂��A�˂��A��ȉ̂��Ă������H
	 */
	public static final NpcStringId I_CAN_SING_ALONG_IF_YOU_LIKE;

	/**
	 * ID: 99752<br>
	 * Message: �����Ă��΂炵���́I���F�ł������`�����̂Ȃ̂˂��I
	 */
	public static final NpcStringId I_THINK_YOU_NEED_SOME_BACKUP;

	/**
	 * ID: 99753<br>
	 * Message: �����C���ɂȂ��Ă����`���������������Ă�I
	 */
	public static final NpcStringId KEEP_UP_THAT_RHYTHM_AND_YOULL_BE_A_STAR;

	/**
	 * ID: 99754<br>
	 * Message: �A�^�V�̂��߂����ɒe���Ă����́H���ꂵ�����I
	 */
	public static final NpcStringId MY_HEART_YEARNS_FOR_MORE_MUSIC;

	/**
	 * ID: 99755<br>
	 * Message: ������ƁI�����ς���Ȃ��H�A�^�V�ւ̈����Ă���Ȃ��̂Ȃ́H
	 */
	public static final NpcStringId YOURE_OUT_OF_TUNE_AGAIN;

	/**
	 * ID: 99756<br>
	 * Message: ��������|�����I�X�S�����ꂵ������I
	 */
	public static final NpcStringId THIS_IS_AWFUL;

	/**
	 * ID: 99757<br>
	 * Message: �����`�`�A�g�̂����Y���Y���Ă�����I�����Ȃ𕷂��ƒ����������Ă������ǁA�A�^�V�̏ꍇ�|�����H��������Ȋ�����ˁH
	 */
	public static final NpcStringId I_THINK_I_BROKE_SOMETHING;

	/**
	 * ID: 99758<br>
	 * Message: �����A���̘a���I�����ō��I�����ƕ������ĂȂ́I
	 */
	public static final NpcStringId WHAT_A_LOVELY_MELODY_PLAY_IT_AGAIN;

	/**
	 * ID: 99759<br>
	 * Message: �����A����I���̃T�E���h���������������́I�A�i�^�A�~���[�W�V�����ɂȂ������I
	 */
	public static final NpcStringId MUSIC_TO_MY_UH_EARS;

	/**
	 * ID: 99760<br>
	 * Message: ����Ȃ񂶂�_���I������@���Ă����͈炽�Ȃ���I
	 */
	public static final NpcStringId YOU_NEED_MUSIC_LESSONS;

	/**
	 * ID: 99761<br>
	 * Message: �ɂ��̂��I�A�^�V�͉��y�����������́I�܂��߂ɂ���Ă�I
	 */
	public static final NpcStringId I_CANT_HEAR_YOU;

	/**
	 * ID: 99762<br>
	 * Message: �����T�E���h����Ȃ���A�A�^�V�̐S�Ɛg�̂ɂ͋����Ȃ��́I
	 */
	public static final NpcStringId YOU_CANT_HURT_ME_LIKE_THAT;

	/**
	 * ID: 99763<br>
	 * Message: ���ꂶ��Ȃ��́I�����A����I�N���m�̊y��I����ł��́I
	 */
	public static final NpcStringId IM_STRONGER_THAN_YOU_ARE;

	/**
	 * ID: 99764<br>
	 * Message: ���ŉ��y���Ȃ��́H�A�^�V�A�����A���Ă����H�D�D�D�����́H�{���ɋA�����Ⴄ��H
	 */
	public static final NpcStringId NO_MUSIC_IM_OUT_OF_HERE;

	/**
	 * ID: 99765<br>
	 * Message: ���������̂́A������I���̏o�镨�ł���Ă�I
	 */
	public static final NpcStringId THAT_RACKET_IS_GETTING_ON_MY_NERVES_TONE_IT_DOWN_A_BIT;

	/**
	 * ID: 99766<br>
	 * Message: �A�^�V�́A���y����Ȃ���_���Ȃ̂��`����Œ@����������Ӗ����Ȃ��́I
	 */
	public static final NpcStringId YOU_CAN_ONLY_HURT_ME_THROUGH_MUSIC;

	/**
	 * ID: 99767<br>
	 * Message: �y��ł���Ă���I����ȕ�����C���I�y��ł���́I
	 */
	public static final NpcStringId ONLY_MUSICAL_INSTRUMENTS_CAN_HURT_ME_NOTHING_ELSE;

	/**
	 * ID: 99768<br>
	 * Message: �A�i�^�̃A�^�b�N���ăX�S�C�̂ˁ`�ł��A���ʖ��ʂȂ̂��`�����Ȃ��܂܂Ȃ�ă_������I
	 */
	public static final NpcStringId YOUR_SKILLS_ARE_IMPRESSIVE_BUT_SADLY_USELESS;

	/**
	 * ID: 99769<br>
	 * Message: �n���ŋ����Ă����ʂ�I�A�^�V���~�����͉̂��Ȃ́I
	 */
	public static final NpcStringId CATCH_A_CHRONO_FOR_ME_PLEASE;

	/**
	 * ID: 99770<br>
	 * Message: �|���i�Ƃ��āA�A�i�^�Ɉ����Ă悩�����B�܂����܂�Ă��Ă��|���i�ł������ȁB�A�i�^�Ƃ������������x�D�D�D
	 */
	public static final NpcStringId YOU_GOT_ME;

	/**
	 * ID: 99771<br>
	 * Message: �ނ��ނ��F��I�n�Y�����Ⴆ�I�L���n�n
	 */
	public static final NpcStringId NOW_LOOK_AT_WHAT_YOUVE_DONE;

	/**
	 * ID: 99772<br>
	 * Message: �A�^�V�Ƃ̎v���o�A��؂ɂ��Ăˁ`
	 */
	public static final NpcStringId YOU_WIN;

	/**
	 * ID: 99773<br>
	 * Message: ���ꂪ�A�i�^�ƃA�^�V�̈��̌�����I
	 */
	public static final NpcStringId SQUASHED;

	/**
	 * ID: 99774<br>
	 * Message: �A�i�^�̑z���A�󂯎�����́B�����A�T���i�����ˁD�D�D
	 */
	public static final NpcStringId DONT_TELL_ANYONE;

	/**
	 * ID: 99775<br>
	 * Message: �ڂ�����ŃA�i�^�̊炪���������Ȃ��́D�D�D�ł��ˁA�A�i�^�̎��͖Y��Ȃ��B�܂��������ˁH
	 */
	public static final NpcStringId GROSS_MY_GUTS_ARE_COMING_OUT;

	/**
	 * ID: 99776<br>
	 * Message: ���`�A�������悩�����́I�����Ȃ́`�A����ł܂��]�����邱�Ƃ��ł��邩�ȁH����Ƃ��Ă��ꂠ����ˁB
	 */
	public static final NpcStringId TAKE_IT_AND_GO;

	/**
	 * ID: 99777<br>
	 * Message: �A�i�^�̈��A�����ς��󂯂Ƃ����́B�ł��A�������ʂꂾ�ˁB�A�i�^�̂��ƁA�Y��Ȃ��I�܂��������ˁI
	 */
	public static final NpcStringId I_SHOULDVE_LEFT_WHEN_I_COULD;

	/**
	 * ID: 99778<br>
	 * Message: �|���i�A���ꂿ������́I�������̏o���`�H�o�Ȃ������Ƃ��Ă��A������x���˂����ˁI
	 */
	public static final NpcStringId NOW_LOOK_WHAT_YOU_HAVE_DONE;

	/**
	 * ID: 99779<br>
	 * Message: ������I���ꂿ��������I�A�^�V�̒��̈����`�`�`�`
	 */
	public static final NpcStringId I_FEEL_DIRTY;

	/**
	 * ID: 99780<br>
	 * Message: ���߂ĂȂ́I�D�������ĂˁI
	 */
	public static final NpcStringId BETTER_LUCK_NEXT_TIME;

	/**
	 * ID: 99781<br>
	 * Message: �����A���ꂢ�����ȁH
	 */
	public static final NpcStringId NICE_SHOT;

	/**
	 * ID: 99782<br>
	 * Message: �����ƁI�����ƈ����~�����́I
	 */
	public static final NpcStringId IM_NOT_AFRAID_OF_YOU;

	/**
	 * ID: 99783<br>
	 * Message: �A�^�V�̓A�i�^�̈��ň�̂�I
	 */
	public static final NpcStringId IF_I_KNEW_THIS_WAS_GOING_TO_HAPPEN_I_WOULD_HAVE_STAYED_HOME;

	/**
	 * ID: 99784<br>
	 * Message: ����ł߂������ς��̈��Ȃ́H����Ȃ񂶂�A�^�V���߂Ȃ́I
	 */
	public static final NpcStringId TRY_HARDER_OR_IM_OUT_OF_HERE;

	/**
	 * ID: 99785<br>
	 * Message: ���������̒��x�Ŗ�����������Ƃł��v���Ă�́H
	 */
	public static final NpcStringId IM_TOUGHER_THAN_I_LOOK;

	/**
	 * ID: 99786<br>
	 * Message: �������ˁB�A�^�V�A���������̍D����B
	 */
	public static final NpcStringId GOOD_STRIKE;

	/**
	 * ID: 99787<br>
	 * Message: ������A��������I���[��A���������E���I�͂ӂ�
	 */
	public static final NpcStringId OH_MY_GOURD;

	/**
	 * ID: 99788<br>
	 * Message: ������Ƃ��A�A�i�^�̈����Ă��̒��x�̂��̂Ȃ́H
	 */
	public static final NpcStringId THATS_ALL_YOUVE_GOT;

	/**
	 * ID: 99789<br>
	 * Message: �A�^�V�̂��Ƃ������l���āI���̂��Ƃ��l����Ȃ�ă_���Ȃ́I�����ƒ@���āI
	 */
	public static final NpcStringId WHY_ME;

	/**
	 * ID: 99790<br>
	 * Message: �l�N�^�[���~�����́I�ǂ����Ă��l�N�^�[���~�����́I
	 */
	public static final NpcStringId BRING_ME_NECTAR;

	/**
	 * ID: 99791<br>
	 * Message: �A�^�V�ˁA�l�N�^�[����������Ƒ傫���Ȃ�̂��I
	 */
	public static final NpcStringId I_MUST_HAVE_NECTAR_TO_GROW;

	/**
	 * ID: 99792<br>
	 * Message: ���������A�A�i�^�̈��������ς����ՁI��肭�����΃A�^�V�A�L���C�Ɉ��I
	 */
	public static final NpcStringId GIVE_ME_SOME_NECTAR_QUICKLY_OR_YOULL_GET_NOTHING;

	/**
	 * ID: 99793<br>
	 * Message: �˂��A�������̂��������l�N�^�[�����傤�����I�A�^�V�̐S�Ƃ����͂����y�R�y�R����I
	 */
	public static final NpcStringId PLEASE_GIVE_ME_SOME_NECTAR_IM_HUNGRY;

	/**
	 * ID: 99794<br>
	 * Message: �����A�l�N�^�[�������ς������āI
	 */
	public static final NpcStringId NECTAR_PLEASE;

	/**
	 * ID: 99795<br>
	 * Message: �l�N�^�[�D�D�D������ăA�^�V�ւ̈��̕\���Ȃ́B������A�^�V�͂�����������đ傫���Ȃ�́I
	 */
	public static final NpcStringId NECTAR_WILL_MAKE_ME_GROW_QUICKLY;

	/**
	 * ID: 99796<br>
	 * Message: ����Ȗ��n�Ȃ����ɒ��������Ă����́H�����C�������́B�����������傫���Ȃ��Ă���A�^�V�������I
	 */
	public static final NpcStringId DONT_YOU_WANT_A_BIGGER_SQUASH_GIVE_ME_SOME_NECTAR_AND_ILL_GROW_MUCH_LARGER;

	/**
	 * ID: 99797<br>
	 * Message: �A�i�^�ɑ���̂��l�����́B�����āI�A�i�^�Ɉ����Ă悩�����`�A�^�V�̈����󂯎~�߂Ă���邩��`�����A�^�V���_���ɂȂ��Ă��`�Ō�܂ňꏏ�Ɉ�����Ă悤�D�D�D�ǂ��H�_�����ȁH
	 */
	public static final NpcStringId IF_YOU_RAISE_ME_WELL_YOULL_GET_PRIZES_OR_NOT;

	/**
	 * ID: 99798<br>
	 * Message: �����ƃA�i�^�ɂ��������Ƃ�����Ǝv����I�A�^�V�́A�ǂ�Ȃ��Ƃ������Ă��A�i�^�̈���������΂��񂺂񕽋C�`�M���Ă邩��ˁI
	 */
	public static final NpcStringId YOU_ARE_HERE_FOR_THE_STUFF_EH_WELL_ITS_MINE_ALL_MINE;

	/**
	 * ID: 99799<br>
	 * Message: �˂��A�A�^�V��M���ăl�N�^�[�������Ă��I�A�^�V�����n�����炫���Ƃ��������K����I
	 */
	public static final NpcStringId TRUST_ME_GIVE_ME_NECTAR_AND_ILL_BECOME_A_GIANT_SQUASH;

	/**
	 * ID: 528551<br>
	 * Message: ����Ȃ��ق炵�����Ƃ������̂͂ǂ̌����I���������Ă��₵�Ȃ��B
	 */
	public static final NpcStringId THERES_NOTHING_YOU_CANT_SAY_I_CANT_LISTEN_TO_YOU_ANYMORE;

	/**
	 * ID: 528651<br>
	 * Message: ����Ȃɓ��X�Əo�Ă����̂ɁA�A���Ă���Ƃ��͂��̃U�}�H�z�z�z�B
	 */
	public static final NpcStringId YOU_ADVANCED_BRAVELY_BUT_GOT_SUCH_A_TINY_RESULT_HOHOHO;

	/**
	 * ID: 1000001<br>
	 * Message: �N���҂𔭌����܂����B
	 */
	public static final NpcStringId A_NON_PERMITTED_TARGET_HAS_BEEN_DISCOVERED;

	/**
	 * ID: 1000002<br>
	 * Message: �N���ҏ����V�X�e�����N�����܂��B
	 */
	public static final NpcStringId INTRUDER_REMOVAL_SYSTEM_INITIATED;

	/**
	 * ID: 1000003<br>
	 * Message: �N���ҏ������D�D�D
	 */
	public static final NpcStringId REMOVING_INTRUDERS;

	/**
	 * ID: 1000004<br>
	 * Message: �v���I�ȃG���[���������܂����B
	 */
	public static final NpcStringId A_FATAL_ERROR_HAS_OCCURRED;

	/**
	 * ID: 1000005<br>
	 * Message: �V�X�e�����I�����܂��D�D�D
	 */
	public static final NpcStringId SYSTEM_IS_BEING_SHUT_DOWN;

	/**
	 * ID: 1000006<br>
	 * Message: �D�D�D�D�D�D
	 */
	public static final NpcStringId DOT_DOT_DOT_DOT_DOT_DOT;
	
	/**
	 * ID: 1000007<br>
	 * Message: �o���Ă�I
	 */
	public static final NpcStringId WE_SHALL_SEE_ABOUT_THAT;

	/**
	 * ID: 1000008<br>
	 * Message: ���̋��J�A�Y��ʂ��I
	 */
	public static final NpcStringId I_WILL_DEFINITELY_REPAY_THIS_HUMILIATION;

	/**
	 * ID: 1000009<br>
	 * Message: �P�ށI
	 */
	public static final NpcStringId RETREAT;

	/**
	 * ID: 1000010<br>
	 * Message: �헪�I�Ɍ�ނ��I
	 */
	public static final NpcStringId TACTICAL_RETREAT;

	/**
	 * ID: 1000011<br>
	 * Message: ������I
	 */
	public static final NpcStringId MASS_FLEEING;

	/**
	 * ID: 1000012<br>
	 * Message: �v�������苭���I
	 */
	public static final NpcStringId ITS_STRONGER_THAN_EXPECTED;

	/**
	 * ID: 1000013<br>
	 * Message: �o���Ă�����I
	 */
	public static final NpcStringId ILL_KILL_YOU_NEXT_TIME;

	/**
	 * ID: 1000014<br>
	 * Message: �������́A����Ă��I
	 */
	public static final NpcStringId ILL_DEFINITELY_KILL_YOU_NEXT_TIME;

	/**
	 * ID: 1000015<br>
	 * Message: �����I�苭���I
	 */
	public static final NpcStringId OH_HOW_STRONG;

	/**
	 * ID: 1000016<br>
	 * Message: �N���҂��I
	 */
	public static final NpcStringId INVADER;

	/**
	 * ID: 1000017<br>
	 * Message: �����E���Ă����ɂ��Ȃ�Ȃ����D�D�D�N�N�N�B
	 */
	public static final NpcStringId THERE_IS_NO_REASON_FOR_YOU_TO_KILL_ME_I_HAVE_NOTHING_YOU_NEED;

	/**
	 * ID: 1000018<br>
	 * Message: �������͕K���D�D�D
	 */
	public static final NpcStringId SOMEDAY_YOU_WILL_PAY;

	/**
	 * ID: 1000019<br>
	 * Message: �Ȃ����΂��艣��񂾁I
	 */
	public static final NpcStringId I_WONT_JUST_STAND_STILL_WHILE_YOU_HIT_ME;

	/**
	 * ID: 1000020<br>
	 * Message: ���ق��Ă���I
	 */
	public static final NpcStringId STOP_HITTING;

	/**
	 * ID: 1000021<br>
	 * Message: ���̐c�܂�Ⴢ�邺���I
	 */
	public static final NpcStringId IT_HURTS_TO_THE_BONE;

	/**
	 * ID: 1000022<br>
	 * Message: ���͉��������I
	 */
	public static final NpcStringId AM_I_THE_NEIGHBORHOOD_DRUM_FOR_BEATING;

	/**
	 * ID: 1000023<br>
	 * Message: ���ė��������Ȃ�A���ė��ȁI
	 */
	public static final NpcStringId FOLLOW_ME_IF_YOU_WANT;

	/**
	 * ID: 1000024<br>
	 * Message: �~�����I
	 */
	public static final NpcStringId SURRENDER;

	/**
	 * ID: 1000025<br>
	 * Message: �����A���������܂����I
	 */
	public static final NpcStringId OH_IM_DEAD;

	/**
	 * ID: 1000026<br>
	 * Message: �N�\�I������đ҂��Ƃ��I
	 */
	public static final NpcStringId ILL_BE_BACK;

	/**
	 * ID: 1000027<br>
	 * Message: ���A���������Ă����Ȃ�A1�疜�A�f�i��邼�I
	 */
	public static final NpcStringId ILL_GIVE_YOU_TEN_MILLION_ARENA_IF_YOU_LET_ME_LIVE;

	/**
	 * ID: 1000028<br>
	 * Message: $s1�B���̂�̖��͂����v���m��I
	 */
	public static final NpcStringId S1_STOP_KIDDING_YOURSELF_ABOUT_YOUR_OWN_POWERLESSNESS;

	/**
	 * ID: 1000029<br>
	 * Message: $s1�I�^�̋��|�Ƃ͉����������Ă�낤�I
	 */
	public static final NpcStringId S1_ILL_MAKE_YOU_FEEL_WHAT_TRUE_FEAR_IS;

	/**
	 * ID: 1000030<br>
	 * Message: �����ɂ����ɂ��Ă������Ƃ���������Ă�낤�B$s1�I�o�債��I
	 */
	public static final NpcStringId YOURE_REALLY_STUPID_TO_HAVE_CHALLENGED_ME_S1_GET_READY;

	/**
	 * ID: 1000031<br>
	 * Message: $s1�B����Ȏ肪�ʂ���Ƃł��v�����̂��I
	 */
	public static final NpcStringId S1_DO_YOU_THINK_THATS_GOING_TO_WORK;

	/**
	 * ID: 1000032<br>
	 * Message: �����ꂽ���̖��_�A�K����҉񂵂Ă݂���I
	 */
	public static final NpcStringId I_WILL_DEFINITELY_RECLAIM_MY_HONOR_WHICH_HAS_BEEN_TARNISHED;

	/**
	 * ID: 1000033<br>
	 * Message: ����ȋR�m�̑����݂��󂯂Ă݂�I
	 */
	public static final NpcStringId SHOW_ME_THE_WRATH_OF_THE_KNIGHT_WHOSE_HONOR_HAS_BEEN_DOWNTRODDEN;

	/**
	 * ID: 1000034<br>
	 * Message: �����ȋU�P�҂Ɏ����I
	 */
	public static final NpcStringId DEATH_TO_THE_HYPOCRITE;

	/**
	 * ID: 1000035<br>
	 * Message: �G��߂𐰂炷�܂Ō����Ė���ɕt�����ƂȂ��A�ł��ʂ�I
	 */
	public static final NpcStringId ILL_NEVER_SLEEP_UNTIL_IVE_SHED_MY_DISHONOR;

	/**
	 * ID: 1000036<br>
	 * Message: ���̒����􂤎҂ǂ���A���͂������I
	 */
	public static final NpcStringId IM_HERE_FOR_THE_ONES_THAT_ARE_CURSING_THE_WORLD;

	/**
	 * ID: 1000037<br>
	 * Message: ���O�����܂悦�鍰�ɂ��Ă�낤�I
	 */
	public static final NpcStringId ILL_TURN_YOU_INTO_A_MALIGNANT_SPIRIT;

	/**
	 * ID: 1000038<br>
	 * Message: �����Ƒ����̗͂ł��O������Ă��I
	 */
	public static final NpcStringId ILL_CURSE_YOU_WITH_THE_POWER_OF_REVENGE_AND_HATE;

	/**
	 * ID: 1000039<br>
	 * Message: �O���V�A�̉h���̂��߂ɁI
	 */
	public static final NpcStringId FOR_THE_GLORY_OF_GRACIA;

	/**
	 * ID: 1000040<br>
	 * Message: ���ӋC�ɂ����Ɨ͔�ׂ����������ƁH
	 */
	public static final NpcStringId DO_YOU_DARE_PIT_YOUR_POWER_AGAINST_ME;

	/**
	 * ID: 1000041<br>
	 * Message: ���A���̎����s�k����Ƃ́I
	 */
	public static final NpcStringId I_I_AM_DEFEATED;

	/**
	 * ID: 1000042<br>
	 * Message: ���̓k���J�l�̈ӎv��`����҂��I �ז����A�ǂ��I
	 */
	public static final NpcStringId I_AM_CONVEYING_THE_WILL_OF_NURKA_EVERYBODY_GET_OUT_OF_MY_WAY;

	/**
	 * ID: 1000043<br>
	 * Message: ���̑O���Ղ�҂́A�N�ł��낤�������ł͂����񂼁I
	 */
	public static final NpcStringId THOSE_WHO_STAND_AGAINST_ME_SHALL_DIE_HORRIBLY;

	/**
	 * ID: 1000044<br>
	 * Message: �ȂɁI���̎ז�������̂��I
	 */
	public static final NpcStringId DO_YOU_DARE_TO_BLOCK_MY_WAY;

	/**
	 * ID: 1000045<br>
	 * Message: ���̒��Ԃ����Q���Ă���邾�낤�I
	 */
	public static final NpcStringId MY_COMRADES_WILL_GET_REVENGE;

	/**
	 * ID: 1000046<br>
	 * Message: ��X�̐_���ȑ�n�����ɐ��߂�҂͋����񂼁I
	 */
	public static final NpcStringId YOU_HEATHEN_BLASPHEMERS_OF_THIS_HOLY_PLACE_WILL_BE_PUNISHED;

	/**
	 * ID: 1000047<br>
	 * Message: ���̌��Ђɒ��킷������Ȏ҂ǂ���A�O�ɏo��I
	 */
	public static final NpcStringId STEP_FORWARD_YOU_WORTHLESS_CREATURES_WHO_CHALLENGE_MY_AUTHORITY;

	/**
	 * ID: 1000048<br>
	 * Message: ���̑n���ҁD�D�D���̂���l�l�ɕς��Ȃ��D�D�D�������D�D�D
	 */
	public static final NpcStringId MY_CREATOR_THE_UNCHANGING_FAITHFULNESS_TO_MY_MASTER;

	/**
	 * ID: 1000049<br>
	 * Message: ���́D�D�D��l�D�D�D���̂���l�l�D�D�D����l�l�D�D�D�������ɁH
	 */
	public static final NpcStringId MASTER_OF_THE_TOWER_MY_MASTER_MASTER_WHERE_IS_HE;

	/**
	 * ID: 1000050<br>
	 * Message: ���^�V�n�A�R�A�m�C�V�� �X�C�R�E�X�����m�B
	 */
	public static final NpcStringId I_AM_THE_ONE_CARRYING_OUT_THE_WILL_OF_CORE;

	/**
	 * ID: 1000051<br>
	 * Message: �V���j���E�V�����A�n�J�C�A�Z���B
	 */
	public static final NpcStringId DESTROY_THE_INVADER;

	/**
	 * ID: 1000052<br>
	 * Message: �W���E�^�C�A�C�W���E�A�T�h�E�A�t�m�E�D�D�D
	 */
	public static final NpcStringId STRANGE_CONDITION_DOESNT_WORK;

	/**
	 * ID: 1000053<br>
	 * Message: �x���X�l�̖��ɏ]���D�D�D���O�������Ď�����I
	 */
	public static final NpcStringId ACCORDING_TO_THE_COMMAND_OF_BELETH_IM_GOING_TO_OBSERVE_YOU_GUYS;

	/**
	 * ID: 1000054<br>
	 * Message: ���؂�A�d���A�E�C�D�D�D����Ȃ��Ƃ����m��Ȃ������Ȕy�ǂ���D�D�D���O���������Ă���ƁA�n�����^���ς��Ԃ肻�����B
	 */
	public static final NpcStringId YOU_PEOPLE_MAKE_ME_SICK_NO_SENSE_OF_LOYALTY_WHATSOEVER;

	/**
	 * ID: 1000055<br>
	 * Message: ���ւ̒���A����͂��Ȃ킿�x���X�l�ɑ΂��钧��ł���B
	 */
	public static final NpcStringId A_CHALLENGE_AGAINST_ME_IS_THE_SAME_AS_A_CHALLENGE_AGAINST_BELETH;

	/**
	 * ID: 1000056<br>
	 * Message: �x���X�l�́A��ɂ��O���������Ă���������̂��I
	 */
	public static final NpcStringId BELETH_IS_ALWAYS_WATCHING_OVER_YOU_GUYS;

	/**
	 * ID: 1000057<br>
	 * Message: ���E�̔j�ł������Ă���I �A���^���X�l���ڂ��o�܂��ꂽ���I 
	 */
	public static final NpcStringId THAT_WAS_REALLY_CLOSE_ANTHARAS_OPENED_ITS_EYES;

	/**
	 * ID: 1000058<br>
	 * Message: �A���^���X�l�̈ӎu�ɋt�炤�҂�I���ʂ������I
	 */
	public static final NpcStringId YOU_WHO_DISOBEY_THE_WILL_OF_ANTHARAS_DIE;

	/**
	 * ID: 1000059<br>
	 * Message: �A���^���X�l�A���Ɏ������^�����������I
	 */
	public static final NpcStringId ANTHARAS_HAS_TAKEN_MY_LIFE;

	/**
	 * ID: 1000060<br>
	 * Message: ������߂����߂ɁA�n���̒ꂩ�畑���߂������I
	 */
	public static final NpcStringId I_CROSSED_BACK_OVER_THE_MARSHLANDS_OF_DEATH_TO_RECLAIM_THE_TREASURE;

	/**
	 * ID: 1000061<br>
	 * Message: �B�������Ă������������ɕ�����I
	 */
	public static final NpcStringId BRING_OVER_AND_SURRENDER_YOUR_PRECIOUS_GOLD_TREASURE_TO_ME;

	/**
	 * ID: 1000062<br>
	 * Message: �N�n�n�n�I���O�Ȃ񂼃C�`�R�����I
	 */
	public static final NpcStringId ILL_KILL_YOU_IN_AN_INSTANT;

	/**
	 * ID: 1000063<br>
	 * Message: �_�����I�܂��󂪁D�D�D
	 */
	public static final NpcStringId NO_THE_TREASURE_IS_STILL;

	/**
	 * ID: 1000064<br>
	 * Message: �h���S���o���[��Ƃ����҂ǂ���A�����Đ����Ă͋A���񂼁I
	 */
	public static final NpcStringId INVADERS_OF_DRAGON_VALLEY_WILL_NEVER_LIVE_TO_RETURN;

	/**
	 * ID: 1000065<br>
	 * Message: ���̓A���^���X�l�̖��ł��̒n�������҂��I
	 */
	public static final NpcStringId I_AM_THE_GUARDIAN_THAT_HONORS_THE_COMMAND_OF_ANTHARAS_TO_WATCH_OVER_THIS_PLACE;

	/**
	 * ID: 1000066<br>
	 * Message: �����Ȃ��h���S���o���[�ɑ��𓥂ݓ����Ƃ́I���������č߂������������I
	 */
	public static final NpcStringId YOUVE_SET_FOOT_IN_DRAGON_VALLEY_WITHOUT_PERMISSION_THE_PENALTY_IS_DEATH;

	/**
	 * ID: 1000068<br>
	 * Message: �E�C�̊�сI���D�̉����I�N�N�N�A��������d������Ă�낤����˂����B
	 */
	public static final NpcStringId THE_JOY_OF_KILLING_THE_ECSTASY_OF_LOOTING_HEY_GUYS_LETS_HAVE_A_GO_AT_IT_AGAIN;

	/**
	 * ID: 1000069<br>
	 * Message: ���̒��́A�Ȃ�ĕ|�����̒m�炸�Ȃ�΂�����Ȃ񂾁I���炵�߂Ă�񂺁I
	 */
	public static final NpcStringId THERE_REALLY_ARE_STILL_LOTS_OF_FOLKS_IN_THE_WORLD_WITHOUT_FEAR_ILL_TEACH_YOU_A_LESSON;

	/**
	 * ID: 1000070<br>
	 * Message: ���ׂĂ̕�����n���Ƃ����Ȃ�A�������͏����Ă���Ă��������I
	 */
	public static final NpcStringId IF_YOU_HAND_OVER_EVERYTHING_YOUVE_GOT_ILL_AT_LEAST_SPARE_YOUR_LIFE;

	/**
	 * ID: 1000071<br>
	 * Message: ���̒��x�̂�ɋ�������Ƃ͂ȁI
	 */
	public static final NpcStringId KNEEL_DOWN_BEFORE_ONE_SUCH_AS_THIS;

	/**
	 * ID: 1000072<br>
	 * Message: ����l�l�̖��ɏ]���A���ׂĂ̐N���҂𒦂炵�߂�̂��I
	 */
	public static final NpcStringId HONOR_THE_MASTERS_WISHES_AND_PUNISH_ALL_THE_INVADERS;

	/**
	 * ID: 1000073<br>
	 * Message: ����l�l�̖��ɏ]���A�N���҂Ɏ��̓S�Ƃ��I
	 */
	public static final NpcStringId FOLLOW_THE_MASTERS_WISHES_AND_PUNISH_THE_INVADERS;

	/**
	 * ID: 1000074<br>
	 * Message: ���́A���΂��̋x���ɉ߂���B
	 */
	public static final NpcStringId DEATH_IS_NOTHING_MORE_THAN_A_MOMENTARY_REST;

	/**
	 * ID: 1000075<br>
	 * Message: �悭�����������I���O�����̎���͂����I��肾�I�A���^���X�l���ڊo�߂�̂��I
	 */
	public static final NpcStringId LISTEN_THIS_IS_THE_END_OF_THE_HUMAN_ERA_ANTHARAS_HAS_AWOKEN;

	/**
	 * ID: 1000076<br>
	 * Message: ���O�̓����A���^���X�l�Ɍ��サ�Ă�낤�I
	 */
	public static final NpcStringId PRESENT_THE_LIVES_OF_FOUR_PEOPLE_TO_ANTHARAS;

	/**
	 * ID: 1000077<br>
	 * Message: ���A���̎����A���J�Ȏ푰�ɕ�����Ƃ́I
	 */
	public static final NpcStringId THIS_IS_UNBELIEVABLE_HOW_COULD_I_HAVE_LOST_TO_ONE_SO_INFERIOR_TO_MYSELF;

	/**
	 * ID: 1000078<br>
	 * Message: �ł̎x�z��w�ɐ[������߂��I
	 */
	public static final NpcStringId I_CARRY_THE_POWER_OF_DARKNESS_AND_HAVE_RETURNED_FROM_THE_ABYSS;

	/**
	 * ID: 1000079<br>
	 * Message: �Ȃ�Ƃ����炵���B
	 */
	public static final NpcStringId ITS_DETESTABLE;

	/**
	 * ID: 1000080<br>
	 * Message: ���Ɉ����̎����}����B
	 */
	public static final NpcStringId I_FINALLY_FIND_REST;

	/**
	 * ID: 1000081<br>
	 * Message: �I���t�F���l�ɉh������I
	 */
	public static final NpcStringId GLORY_TO_ORFEN;

	/**
	 * ID: 1000082<br>
	 * Message: �I���t�F���l�̖�������Ă��̒n��Ƃ����Ƃ�����߁I������
	 */
	public static final NpcStringId IN_THE_NAME_OF_ORFEN_I_CAN_NEVER_FORGIVE_YOU_WHO_ARE_INVADING_THIS_PLACE;

	/**
	 * ID: 1000083<br>
	 * Message: �I���t�F���l�̒n�ɓy���œ��ݍ��񂾔����󂯂�̂��I
	 */
	public static final NpcStringId ILL_MAKE_YOU_PAY_THE_PRICE_FOR_FEARLESSLY_ENTERING_ORFENS_LAND;

	/**
	 * ID: 1000084<br>
	 * Message: ���ɂ��̂悤�ɋ������������Ƃ��Ă��A���O�����͎����������􂢂ɂ���Ĉꐶ�ꂵ�߂��邾�낤�D�D�D�N�N�N�B
	 */
	public static final NpcStringId EVEN_IF_YOU_DISAPPEAR_INTO_NOTHINGNESS_YOU_WILL_STILL_FACE_THE_LIFE_LONG_SUFFERING_OF_THE_CURSE_THAT_I_HAVE_GIVEN_YOU;

	/**
	 * ID: 1000085<br>
	 * Message: �_���ȗd�������̏Z�݂����r�炷�҂�A���̎�������ɂȂ��Ă�낤�I
	 */
	public static final NpcStringId ILL_STAND_AGAINST_ANYONE_THAT_MAKES_LIGHT_OF_THE_SACRED_PLACE_OF_THE_ELVES;

	/**
	 * ID: 1000086<br>
	 * Message: ��X�̏Z�݂��������҂�A��̎�ő����Ă�낤�I
	 */
	public static final NpcStringId I_WILL_KILL_WITH_MY_OWN_HANDS_ANYONE_THAT_DEFILES_OUR_HOME;

	/**
	 * ID: 1000087<br>
	 * Message: ���Ƃ��̎艺�����̒J����ǂ��o���܂ŁA���̌Z�킽���͌����ċx�ނ��Ƃ͂Ȃ����낤�I 
	 */
	public static final NpcStringId MY_BROTHERS_WILL_NEVER_REST_UNTIL_WE_PUSH_YOU_AND_YOUR_GANG_OUT_OF_THIS_VALLEY;

	/**
	 * ID: 1000088<br>
	 * Message: �w�X�g�D�C�ŖS�̂��̓��܂ŁI
	 */
	public static final NpcStringId UNTIL_THE_DAY_OF_DESTRUCTION_OF_HESTUI;

	/**
	 * ID: 1000089<br>
	 * Message: �E���Ȃ�I�[�N�͂܂��c���Ă��邩�I�Ō�̈ꕺ�܂Ő킦�I
	 */
	public static final NpcStringId IF_ANY_INTREPID_ORCS_REMAIN_ATTACK_THEM;

	/**
	 * ID: 1000090<br>
	 * Message: ���̍����~�߂Ă��I
	 */
	public static final NpcStringId ILL_BREAK_YOUR_WINDPIPE;

	/**
	 * ID: 1000091<br>
	 * Message: ���ǁA���Q�͎��s���� �D�D�D
	 */
	public static final NpcStringId IS_REVENGE_A_FAILURE;

	/**
	 * ID: 1000092<br>
	 * Message: �h���[�t�̃s�J�s�J�P���~�X�����A���ꂢ�ȕ�΁I���ׂĎ�ɂ����̂��I
	 */
	public static final NpcStringId THE_SPARKLING_MITHRIL_OF_THE_DWARVES_AND_THEIR_PRETTY_TREASURES_ILL_GET_THEM_ALL;

	/**
	 * ID: 1000093<br>
	 * Message: ���a�ȃ`�r�����A�P���΂́A�ǂ��ɂ���D�D�D
	 */
	public static final NpcStringId WHERE_ARE_ALL_THE_DREADFUL_DWARVES_AND_THEIR_SPARKLING_THINGS;

	/**
	 * ID: 1000094<br>
	 * Message: ���ׂďo���I���ꂢ�ȕ�΁I
	 */
	public static final NpcStringId HAND_OVER_YOUR_PRETTY_TREASURES;

	/**
	 * ID: 1000095<br>
	 * Message: �N�b�I���̎������Ă���΁D�D�D
	 */
	public static final NpcStringId HEY_YOU_SHOULD_HAVE_RUN_AWAY;

	/**
	 * ID: 1000096<br>
	 * Message: �j��A���ŁA�E�C�A�ŖS�I�j��A���ŁA�E�C�A�ŖS�I
	 */
	public static final NpcStringId DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE_DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE;

	/**
	 * ID: 1000097<br>
	 * Message: �j��I�j��I�j��I�j��I
	 */
	public static final NpcStringId DESTRUCTION_DESTRUCTION_DESTRUCTION_DESTRUCTION;

	/**
	 * ID: 1000098<br>
	 * Message: �j��I�j��I�j�D�D�D
	 */
	public static final NpcStringId DESTRUCTION_DESTRUCTION_DESTRUCTION;

	/**
	 * ID: 1000099<br>
	 * Message: �W���W���[���I�A���ė����E�U���J�I
	 */
	public static final NpcStringId TA_DA_UTHANKA_HAS_RETURNED;

	/**
	 * ID: 1000100<br>
	 * Message: ���n�n�n�I���̓��͍����t���ŃE�U���J�l�������̂��I
	 */
	public static final NpcStringId WAH_HA_HA_HA_UTHANKA_HAS_TAKEN_OVER_THIS_ISLAND_TODAY;

	/**
	 * ID: 1000101<br>
	 * Message: ���[���ƁI���ʂ��A�Ȃ��Ȃ�����
	 */
	public static final NpcStringId WHEW_HES_QUITE_A_GUY;

	/**
	 * ID: 1000102<br>
	 * Message: ���A�������D�D�D��������������ƕ�����Ƃ́D�D�D
	 */
	public static final NpcStringId HOW_EXASPERATING_AND_UNFAIR_TO_HAVE_THINGS_HAPPEN_IN_SUCH_A_MEANINGLESS_WAY_LIKE_THIS;

	/**
	 * ID: 1000103<br>
	 * Message: ���̐��̒��͋��|�Ɣ߂��݂Ŗ��������ׂ����D�D�D
	 */
	public static final NpcStringId THIS_WORLD_SHOULD_BE_FILLED_WITH_FEAR_AND_SADNESS;

	/**
	 * ID: 1000104<br>
	 * Message: ���Ɏ􂢂����������̐��̒��D�D�D�����ċ����񂼁I
	 */
	public static final NpcStringId I_WONT_FORGIVE_THE_WORLD_THAT_CURSED_ME;

	/**
	 * ID: 1000105<br>
	 * Message: �F�Ɏ��Ɠ�����ɂ𖡂��킹�Ă��I
	 */
	public static final NpcStringId ILL_MAKE_EVERYONE_FEEL_THE_SAME_SUFFERING_AS_ME;

	/**
	 * ID: 1000106<br>
	 * Message: ���O�ɂ��i���ɉ����邱�Ƃ̂Ȃ��􂢂������Ă�낤�I
	 */
	public static final NpcStringId ILL_GIVE_YOU_A_CURSE_THAT_YOULL_NEVER_BE_ABLE_TO_REMOVE_FOREVER;

	/**
	 * ID: 1000107<br>
	 * Message: ���E���E�����ɕ��Q���I
	 */
	public static final NpcStringId ILL_GET_REVENGE_ON_YOU_WHO_SLAUGHTERED_MY_COMPATRIOTS;

	/**
	 * ID: 1000108<br>
	 * Message: ���a�Ȃ�͏���������I�E���Ȃ�͂������ė����I
	 */
	public static final NpcStringId THOSE_WHO_ARE_AFRAID_SHOULD_GET_AWAY_AND_THOSE_WHO_ARE_BRAVE_SHOULD_FIGHT;

	/**
	 * ID: 1000109<br>
	 * Message: �x���X�l����͂�^����ꂽ�����ȒP�ɂ����Ƃł��v���Ă���̂��I
	 */
	public static final NpcStringId IVE_GOT_POWER_FROM_BELETH_SO_DO_YOU_THINK_ILL_BE_EASILY_DEFEATED;

	/**
	 * ID: 1000110<br>
	 * Message: ���͂�������ď����Ă������D�D�D������l�̎������O������|���ɗ���ł��낤�I
	 */
	public static final NpcStringId I_AM_LEAVING_NOW_BUT_SOON_SOMEONE_WILL_COME_WHO_WILL_TEACH_YOU_ALL_A_LESSON;

	/**
	 * ID: 1000111<br>
	 * Message: ���O��A���l�̓꒣������肵�ė����I
	 */
	public static final NpcStringId HEY_GUYS_LETS_MAKE_A_ROUND_OF_OUR_TERRITORY;

	/**
	 * ID: 1000112<br>
	 * Message: �ߍ��A���l�̓꒣�ōD������Ȃ��Ƃ�����Ă������邻�����D�D�D
	 */
	public static final NpcStringId THE_RUMOR_IS_THAT_THERE_ARE_WILD_UNCIVILIZED_RUFFIANS_WHO_HAVE_RECENTLY_ARRIVED_IN_MY_TERRITORY;

	/**
	 * ID: 1000113<br>
	 * Message: ���l���N�����킩�邩�I�����܂������V���b�R�l���I���O��A������܂��I
	 */
	public static final NpcStringId DO_YOU_KNOW_WHO_I_AM_I_AM_SIROCCO_EVERYONE_ATTACK;

	/**
	 * ID: 1000114<br>
	 * Message: ����Ȃ͂��ł́D�D�D�s�s�̃V���b�R�����O���Ƃ��ɕ�����Ƃ́I
	 */
	public static final NpcStringId WHATS_JUST_HAPPENED_THE_INVINCIBLE_SIROCCO_WAS_DEFEATED_BY_SOMEONE_LIKE_YOU;

	/**
	 * ID: 1000115<br>
	 * Message: ���ւ����[�A�L�������`
	 */
	public static final NpcStringId OH_IM_REALLY_HUNGRY;

	/**
	 * ID: 1000116<br>
	 * Message: �a�̏L�������邼�A�L�������`
	 */
	public static final NpcStringId I_SMELL_FOOD_OOH;

	/**
	 * ID: 1000117<br>
	 * Message: �L�������`
	 */
	public static final NpcStringId OOH;

	/**
	 * ID: 1000118<br>
	 * Message: �����̖I���͂ǂ�Ȗ����ȁH
	 */
	public static final NpcStringId WHAT_DOES_HONEY_OF_THIS_PLACE_TASTE_LIKE;

	/**
	 * ID: 1000119<br>
	 * Message: �����Ƃ肷��قǊÂ��ƌ����Ă���A�����̖I�����o���I
	 */
	public static final NpcStringId GIVE_ME_SOME_SWEET_DELICIOUS_GOLDEN_HONEY;

	/**
	 * ID: 1000120<br>
	 * Message: �I���������o���΁A�������͏����Ă��I
	 */
	public static final NpcStringId IF_YOU_GIVE_ME_SOME_HONEY_ILL_AT_LEAST_SPARE_YOUR_LIFE;

	/**
	 * ID: 1000121<br>
	 * Message: �I�������������Ă���΁D�D�D.���O�Ȃ񂩂ɕ����Ȃ������͂����B
	 */
	public static final NpcStringId ONLY_FOR_LACK_OF_HONEY_DID_I_LOSE_TO_THE_LIKES_OF_YOU;

	/**
	 * ID: 1000122<br>
	 * Message: ���؂�҂̃N���{���X�͂ǂ����I
	 */
	public static final NpcStringId WHERE_IS_THE_TRAITOR_KUROBOROS;

	/**
	 * ID: 1000123<br>
	 * Message: ��������܂Ȃ��T���I
	 */
	public static final NpcStringId LOOK_IN_EVERY_NOOK_AND_CRANNY_AROUND_HERE;

	/**
	 * ID: 1000124<br>
	 * Message: �N���{���X�̎艺���B�꓁���f�ɂ��Ă��I
	 */
	public static final NpcStringId ARE_YOU_LACKEY_OF_KUROBOROS_ILL_KNOCK_YOU_OUT_IN_ONE_SHOT;

	/**
	 * ID: 1000125<br>
	 * Message: ���؂�҂�|���ʂ܂܎��ʂƂ́D�D�D���₵���I
	 */
	public static final NpcStringId HE_JUST_CLOSED_HIS_EYES_WITHOUT_DISPOSING_OF_THE_TRAITOR_HOW_UNFAIR;

	/**
	 * ID: 1000126<br>
	 * Message: �N���{���X�l��M���ʎ҂͒n���ɗ����邪�����I
	 */
	public static final NpcStringId HELL_FOR_UNBELIEVERS_IN_KUROBOROS;

	/**
	 * ID: 1000127<br>
	 * Message: ���̃N���{���X��M���Ȃ��ҁB���̐������Ȃ킿�n���Ȃ̂��I
	 */
	public static final NpcStringId THE_PERSON_THAT_DOES_NOT_BELIEVE_IN_KUROBOROS_HIS_LIFE_WILL_SOON_BECOME_HELL;

	/**
	 * ID: 1000128<br>
	 * Message: �U��̐_�Ɏd���鈫���̎艺�߁I���̎�Ő��s���Ă�낤�I
	 */
	public static final NpcStringId THE_LACKEY_OF_THAT_DEMENTED_DEVIL_THE_SERVANT_OF_A_FALSE_GOD_ILL_SEND_THAT_FOOL_STRAIGHT_TO_HELL;

	/**
	 * ID: 1000129<br>
	 * Message: ���D�D�D��A���ʂɂ��炸�B���΂�������̂݁D�D�D�Ăѕ�������I
	 */
	public static final NpcStringId UH_IM_NOT_DYING_IM_JUST_DISAPPEARING_FOR_A_MOMENT_ILL_RESURRECT_AGAIN;

	/**
	 * ID: 1000130<br>
	 * Message: �N���{���X����l�A���΁I
	 */
	public static final NpcStringId HAIL_TO_KUROBOROS_THE_FOUNDER_OF_OUR_RELIGION;

	/**
	 * ID: 1000131<br>
	 * Message: �N���{���X�l��M����҂������~����̂��I
	 */
	public static final NpcStringId ONLY_THOSE_WHO_BELIEVE_IN_PATRIARCH_KUROBOROS_SHALL_RECEIVE_SALVATION;

	/**
	 * ID: 1000132<br>
	 * Message: �V�����N�������̂���������ȁI���O�������N���{���X�l��M���ċ~���邪�悢�I
	 */
	public static final NpcStringId ARE_YOU_THE_ONES_THAT_SHARUK_HAS_INCITED_YOU_ALSO_SHOULD_TRUST_IN_KUROBOROS_AND_BE_SAVED;

	/**
	 * ID: 1000133<br>
	 * Message: �N���{���X�l�����O�����𒦂炵�߂邾�낤�B
	 */
	public static final NpcStringId KUROBOROS_WILL_PUNISH_YOU;

	/**
	 * ID: 1000134<br>
	 * Message: ���񂾋P������������썰�����҂�����I���͋A���ė������I
	 */
	public static final NpcStringId YOU_WHO_HAVE_BEAUTIFUL_SPIRITS_THAT_SHINE_BRIGHTLY_I_HAVE_RETURNED;

	/**
	 * ID: 1000135<br>
	 * Message: ��J���ނ����҂�����D�D�D�썰�����Ɏ����Ȃ����B
	 */
	public static final NpcStringId YOU_THAT_ARE_WEARY_AND_EXHAUSTED_ENTRUST_YOUR_SOULS_TO_ME;

	/**
	 * ID: 1000136<br>
	 * Message: ���O�̗썰�̐F�͂����Ƃ肷��قǔ������B
	 */
	public static final NpcStringId THE_COLOR_OF_YOUR_SOUL_IS_VERY_ATTRACTIVE;

	/**
	 * ID: 1000137<br>
	 * Message: �����Ƃ�������҂�I���O�����̗썰���ǂ�Ȃɔ��������m���Ă��邩�I
	 */
	public static final NpcStringId THOSE_OF_YOU_WHO_LIVE_DO_YOU_KNOW_HOW_BEAUTIFUL_YOUR_SOULS_ARE;

	/**
	 * ID: 1000138<br>
	 * Message: �݁A�F�E���ɂ��Ă��D�D�D
	 */
	public static final NpcStringId IT_WILL_KILL_EVERYONE;

	/**
	 * ID: 1000139<br>
	 * Message: ���A�ǓƂ��D�D�D
	 */
	public static final NpcStringId IM_SO_LONELY;

	/**
	 * ID: 1000140<br>
	 * Message: ��A���̂������D�D�D
	 */
	public static final NpcStringId MY_ENEMY;

	/**
	 * ID: 1000141<br>
	 * Message: �����A�ǓƂł͂Ȃ��I
	 */
	public static final NpcStringId _NOW_IM_NOT_SO_LONELY;

	/**
	 * ID: 1000142<br>
	 * Message: ��X���D�D�D�łڂ���Ƃ���D�D�D�s�N�V�[�~�����J�I�����ċ����܂��I
	 */
	public static final NpcStringId I_WILL_NEVER_FORGIVE_THE_PIXY_MURIKA_THAT_IS_TRYING_TO_KILL_US;

	/**
	 * ID: 1000143<br>
	 * Message: �~�����J�̗D�ꂽ�艺������A�������Ă����I
	 */
	public static final NpcStringId ATTACK_ALL_THE_DULL_AND_STUPID_FOLLOWERS_OF_MURIKA;

	/**
	 * ID: 1000144<br>
	 * Message: ���̒��x�̔e�C�ł͑����ł��ł��Ȃ����I
	 */
	public static final NpcStringId I_DIDNT_HAVE_ANY_IDEA_ABOUT_SUCH_AMBITIONS;

	/**
	 * ID: 1000145<br>
	 * Message: ����͏I���ł͂Ȃ��D�D�D�n�܂�ɉ߂��Ȃ��B
	 */
	public static final NpcStringId THIS_IS_NOT_THE_END_ITS_JUST_THE_BEGINNING;

	/**
	 * ID: 1000146<br>
	 * Message: �ӂ��D�D�D�v���Ԃ�̊O�o���B�����y�������Ƃ͂Ȃ����ȁD�D�D
	 */
	public static final NpcStringId HEY_SHALL_WE_HAVE_SOME_FUN_FOR_THE_FIRST_TIME_IN_A_LONG_WHILE;

	/**
	 * ID: 1000147<br>
	 * Message: ���̕ӂ���̂��΂�����Ă���y������炵�����D�D�D
	 */
	public static final NpcStringId THEREVE_BEEN_SOME_THINGS_GOING_AROUND_LIKE_CRAZY_HERE_RECENTLY;

	/**
	 * ID: 1000148<br>
	 * Message: �����A����N���Ǝv���Ă���I�������̃}���b�N�X���I�F�̎ҁA������I
	 */
	public static final NpcStringId HEY_DO_YOU_KNOW_WHO_I_AM_I_AM_MALEX_HERALD_OF_DAGONIEL_ATTACK;

	/**
	 * ID: 1000149<br>
	 * Message: ����Ȃ͂��ł́D�D�D���̕s�s�̃}���b�N�X�����O�Ȃ񂼂ɕ�����Ƃ́D�D�D
	 */
	public static final NpcStringId WHATS_JUST_HAPPENED_THE_INVINCIBLE_MALEX_JUST_LOST_TO_THE_LIKES_OF_YOU;

	/**
	 * ID: 1000150<br>
	 * Message: �����������J��Ԃ��������D�D�D
	 */
	public static final NpcStringId ITS_SOMETHING_REPEATED_IN_A_VAIN_LIFE;

	/**
	 * ID: 1000151<br>
	 * Message: ������҂݂͂ȋ��|�ɜɂ��I
	 */
	public static final NpcStringId SHAKE_IN_FEAR_ALL_YOU_WHO_VALUE_YOUR_LIVES;

	/**
	 * ID: 1000152<br>
	 * Message: �����邱�Ƃ̂Ȃ����ɓZ����悤�ȋꂵ�݂𖡂��킹�Ă��I
	 */
	public static final NpcStringId ILL_MAKE_YOU_FEEL_SUFFERING_LIKE_A_FLAME_THAT_IS_NEVER_EXTINGUISHED;

	/**
	 * ID: 1000153<br>
	 * Message: �Ăѓy�̒��ɁD�D�D
	 */
	public static final NpcStringId BACK_TO_THE_DIRT;

	/**
	 * ID: 1000154<br>
	 * Message: ���@���J�l�I���΁I
	 */
	public static final NpcStringId HAIL_VARIKA;

	/**
	 * ID: 1000155<br>
	 * Message: �N����X���~�߂邱�Ƃ͂ł��Ȃ��I
	 */
	public static final NpcStringId NOBODY_CAN_STOP_US;

	/**
	 * ID: 1000156<br>
	 * Message: �������݂����I
	 */
	public static final NpcStringId YOU_MOVE_SLOWLY;

	/**
	 * ID: 1000157<br>
	 * Message: ���@���J�l�I��ɐ����܂��I
	 */
	public static final NpcStringId VARIKA_GO_FIRST;

	/**
	 * ID: 1000158<br>
	 * Message: �����͂ǂ����H���͒N���H
	 */
	public static final NpcStringId WHERE_AM_I_WHO_AM_I;

	/**
	 * ID: 1000159<br>
	 * Message: ���D�D�D���������悤�ɒɂ��I���͒N�Ȃ񂾁H
	 */
	public static final NpcStringId UH_MY_HEAD_HURTS_LIKE_ITS_GOING_TO_BURST_WHO_AM_I;

	/**
	 * ID: 1000160<br>
	 * Message: �M�l�B�M�l�����̈������ȁI��������Ȗڂɍ��킹���������ȁI
	 */
	public static final NpcStringId YOU_JERK_YOURE_A_DEVIL_YOURE_A_DEVIL_TO_HAVE_MADE_ME_LIKE_THIS;

	/**
	 * ID: 1000161<br>
	 * Message: ���D�D�D����Ɛ��C�ɂȂ������D�D�D���肪�Ƃ��B����Ƃ������x�߂������B
	 */
	public static final NpcStringId WHERE_AM_I_WHAT_HAPPENED_THANK_YOU;

	/**
	 * ID: 1000162<br>
	 * Message: �E�N���}�X�^�I
	 */
	public static final NpcStringId UKRU_MASTER;

	/**
	 * ID: 1000163<br>
	 * Message: �}�g�D�i�H
	 */
	public static final NpcStringId ARE_YOU_MATU;

	/**
	 * ID: 1000164<br>
	 * Message: �}���b�O�I�g�D�o�����I�T�o���`���I
	 */
	public static final NpcStringId MARAK_TUBARIN_SABARACHA;

	/**
	 * ID: 1000165<br>
	 * Message: �p�O���I�^�}�I
	 */
	public static final NpcStringId PAAGRIO_TAMA;

	/**
	 * ID: 1000166<br>
	 * Message: �C�J���X�̈ӎu���A�󂯌p���ׂ��I
	 */
	public static final NpcStringId ACCEPT_THE_WILL_OF_ICARUS;

	/**
	 * ID: 1000167<br>
	 * Message: �ז����Ă���҂͋����񂼁I
	 */
	public static final NpcStringId THE_PEOPLE_WHO_ARE_BLOCKING_MY_WAY_WILL_NOT_BE_FORGIVEN;

	/**
	 * ID: 1000168<br>
	 * Message: ��͂��Ȃ�낤�ǂ��D�D�D
	 */
	public static final NpcStringId YOU_ARE_SCUM;

	/**
	 * ID: 1000169<br>
	 * Message: ���͂��܂��Ȃ�����Ȃ��Ȃ�܂��B
	 */
	public static final NpcStringId YOU_LACK_POWER;

	/**
	 * ID: 1000170<br>
	 * Message: �߂�
	 */
	public static final NpcStringId RETURN;

	/**
	 * ID: 1000171<br>
	 * Message: �A�f�i���J�z����܂����B
	 */
	public static final NpcStringId ADENA_HAS_BEEN_TRANSFERRED;

	/**
	 * ID: 1000172<br>
	 * Message: ��F
	 */
	public static final NpcStringId EVENT_NUMBER;

	/**
	 * ID: 1000173<br>
	 * Message: 1��
	 */
	public static final NpcStringId FIRST_PRIZE;

	/**
	 * ID: 1000174<br>
	 * Message: 2��
	 */
	public static final NpcStringId SECOND_PRIZE;

	/**
	 * ID: 1000175<br>
	 * Message: 3��
	 */
	public static final NpcStringId THIRD_PRIZE;

	/**
	 * ID: 1000176<br>
	 * Message: 4��
	 */
	public static final NpcStringId FOURTH_PRIZE;

	/**
	 * ID: 1000177<br>
	 * Message: ���I�����󂭂�������܂���B
	 */
	public static final NpcStringId THERE_HAS_BEEN_NO_WINNING_LOTTERY_TICKET;

	/**
	 * ID: 1000178<br>
	 * Message: ����̓��I�ԍ��F
	 */
	public static final NpcStringId THE_MOST_RECENT_WINNING_LOTTERY_NUMBERS;

	/**
	 * ID: 1000179<br>
	 * Message: ��̔ԍ��őI��
	 */
	public static final NpcStringId YOUR_LUCKY_NUMBERS_HAVE_BEEN_SELECTED_ABOVE;

	/**
	 * ID: 1000180<br>
	 * Message: �����ɋ߂Â����Ƃ���҂͒N���I
	 */
	public static final NpcStringId I_WONDER_WHO_IT_IS_THAT_IS_LURKING_ABOUT;

	/**
	 * ID: 1000181<br>
	 * Message: �����͐_���Ȗ��@���������鏊�ł���B
	 */
	public static final NpcStringId SACRED_MAGICAL_RESEARCH_IS_CONDUCTED_HERE;

	/**
	 * ID: 1000182<br>
	 * Message: �̑�Ȗ��@�̗͂������Ă�낤�I
	 */
	public static final NpcStringId BEHOLD_THE_AWESOME_POWER_OF_MAGIC;

	/**
	 * ID: 1000183<br>
	 * Message: �Ȃ��Ȃ��債�����̂��ȁB�����A���ʃ��C�W�ɂ͎���o���Ȃ��ق����������낤�B
	 */
	public static final NpcStringId YOUR_POWERS_ARE_IMPRESSIVE_BUT_YOU_MUST_NOT_ANNOY_OUR_HIGH_LEVEL_SORCERER;

	/**
	 * ID: 1000184<br>
	 * Message: �Ԃ̎�l�̃o���_�l���B�E�n�n�n�I
	 */
	public static final NpcStringId I_AM_BARDA_MASTER_OF_THE_BANDIT_STRONGHOLD;

	/**
	 * ID: 1000185<br>
	 * Message: ���̍Ԃ͌��X���̃o���_�l�̂��̂Ȃ̂��B
	 */
	public static final NpcStringId I_MASTER_BARDA_ONCE_OWNED_THAT_STRONGHOLD;

	/**
	 * ID: 1000186<br>
	 * Message: �ւ��A�������낻�����ȁB
	 */
	public static final NpcStringId AH_VERY_INTERESTING;

	/**
	 * ID: 1000187<br>
	 * Message: �v��������肻�����ȁB�����͈�U�������ق����悳�������B
	 */
	public static final NpcStringId YOU_ARE_MORE_POWERFUL_THAN_YOU_APPEAR_WELL_MEET_AGAIN;

	/**
	 * ID: 1000188<br>
	 * Message: �o�J�ȃ��C�W�ǂ��߁I
	 */
	public static final NpcStringId YOU_FILTHY_SORCERERS_DISGUST_ME;

	/**
	 * ID: 1000189<br>
	 * Message: ���ӋC�ɂ����̒n�ɐN������Ƃ͂ȁI�����ł͂����Ȃ����I
	 */
	public static final NpcStringId WHY_WOULD_YOU_BUILD_A_TOWER_IN_OUR_TERRITORY;

	/**
	 * ID: 1000190<br>
	 * Message: ���O����׈��ȃ��C�W�ƃO���Ȃ̂��B
	 */
	public static final NpcStringId ARE_YOU_PART_OF_THAT_EVIL_GANG_OF_SORCERERS;

	/**
	 * ID: 1000191<br>
	 * Message: ���ꂾ���烁�C�W�̎q���Ȃ񂩂Ɛ킢�����Ȃ������񂾁B
	 */
	public static final NpcStringId THAT_IS_WHY_I_DONT_BOTHER_WITH_ANYONE_BELOW_THE_LEVEL_OF_SORCERER;

	/**
	 * ID: 1000192<br>
	 * Message: �������y����������n�܂�ȁD�D�D
	 */
	public static final NpcStringId AH_ANOTHER_BEAUTIFUL_DAY;

	/**
	 * ID: 1000193<br>
	 * Message: ���΂Ɨ��D�͉��B�̐�傳�B
	 */
	public static final NpcStringId OUR_SPECIALTIES_ARE_ARSON_AND_LOOTING;

	/**
	 * ID: 1000194<br>
	 * Message: ���O�̗L������c�炸�����Ă�낤�I
	 */
	public static final NpcStringId YOU_WILL_LEAVE_EMPTY_HANDED;

	/**
	 * ID: 1000195<br>
	 * Message: �����W�߂��󕨂�����Ȃɗ~�����̂��B����A�T���Ă݂邱�Ƃ��I�n�n�n�I
	 */
	public static final NpcStringId AH_SO_YOU_ADMIRE_MY_TREASURE_DO_YOU_TRY_FINDING_IT_HA;

	/**
	 * ID: 1000196<br>
	 * Message: �F�̎ҁA�����Ă���̂��B�V���I���l�̂��߂肾�B�F�̎ҁA�r���h�q����D�D�D
	 */
	public static final NpcStringId IS_EVERYBODY_LISTENING_SIRION_HAS_COME_BACK_EVERYONE_CHANT_AND_BOW;

	/**
	 * ID: 1000197<br>
	 * Message: ���ɑ����҂ǂ���I���������I�T�����낤�I
	 */
	public static final NpcStringId BOW_DOWN_YOU_WORTHLESS_HUMANS;

	/**
	 * ID: 1000198<br>
	 * Message: ���ɑ����܂��ҁD�D�D
	 */
	public static final NpcStringId VERY_TACKY;

	/**
	 * ID: 1000199<br>
	 * Message: �b�N�D�D�D����ł��|�����ƈ�����̂͂܂��D�D�D
	 */
	public static final NpcStringId DONT_THINK_THAT_YOU_ARE_INVINCIBLE_JUST_BECAUSE_YOU_DEFEATED_ME;

	/**
	 * ID: 1000200<br>
	 * Message: ���̖𗧂������D�D�D���҂̗~�]�D�D�D
	 */
	public static final NpcStringId THE_MATERIAL_DESIRES_OF_MORTALS_ARE_ULTIMATELY_MEANINGLESS;

	/**
	 * ID: 1000201<br>
	 * Message: �����̓������󂵂����P���l���邱�Ƃ��D�D�D
	 */
	public static final NpcStringId DO_NOT_FORGET_THE_REASON_THE_TOWER_OF_INSOLENCE_COLLAPSED;

	/**
	 * ID: 1000202<br>
	 * Message: �~�[���҂ǂ��B�~�[���M���ǂ��D�D�D
	 */
	public static final NpcStringId YOU_HUMANS_ARE_ALL_ALIKE_FULL_OF_GREED_AND_AVARICE;

	/**
	 * ID: 1000203<br>
	 * Message: �������B���ׂĂ̂��Ƃ��������̂��D�D�D
	 */
	public static final NpcStringId ALL_FOR_NOTHING;

	/**
	 * ID: 1000204<br>
	 * Message: �Ȃ����A�Ȃ��ɂ���Ȃɐl�������̂��H
	 */
	public static final NpcStringId WHAT_ARE_ALL_THESE_PEOPLE_DOING_HERE;

	/**
	 * ID: 1000205<br>
	 * Message: �ǂ����ɉi���̖��̔閧������͂����B�������I�V�g�ǂ��߁I
	 */
	public static final NpcStringId I_MUST_FIND_THE_SECRET_OF_ETERNAL_LIFE_HERE_AMONG_THESE_ROTTEN_ANGELS;

	/**
	 * ID: 1000206<br>
	 * Message: ���O�������i���̖��̔閧��T���Ă�̂��B
	 */
	public static final NpcStringId DO_YOU_ALSO_SEEK_THE_SECRET_OF_IMMORTALITY;

	/**
	 * ID: 1000207<br>
	 * Message: �����m���Ă�Ƃ��Ă������Ęb���Ă���B
	 */
	public static final NpcStringId I_SHALL_NEVER_REVEAL_MY_SECRETS;

	/**
	 * ID: 1000208<br>
	 * Message: ���ӋC�ɂ��N���������ɓ����ė����Ƃ����̂��B
	 */
	public static final NpcStringId WHO_DARES_ENTER_THIS_PLACE;

	/**
	 * ID: 1000209<br>
	 * Message: �����͂��O��̂悤�Ȃ�������Ƃ���ł͂Ȃ��B�������o�čs���I
	 */
	public static final NpcStringId THIS_IS_NO_PLACE_FOR_HUMANS_YOU_MUST_LEAVE_IMMEDIATELY;

	/**
	 * ID: 1000210<br>
	 * Message: �����Ȃ�҂ǂ���B���O�����̋�݂����킩��ʂ̂��B
	 */
	public static final NpcStringId YOU_POOR_CREATURES_TOO_STUPID_TO_REALIZE_YOUR_OWN_IGNORANCE;

	/**
	 * ID: 1000211<br>
	 * Message: �悹�B���̏�ɍs���Ă͂Ȃ�ʁB
	 */
	public static final NpcStringId YOU_MUSTNT_GO_THERE;

	/**
	 * ID: 1000212<br>
	 * Message: ���̏��������҂͒N���B
	 */
	public static final NpcStringId WHO_DARES_DISTURB_THIS_MARSH;

	/**
	 * ID: 1000213<br>
	 * Message: �����҂ǂ��̗~�]�ŁA���n���܂��r�ꂳ����킯�ɂ͂�����B
	 */
	public static final NpcStringId THE_HUMANS_MUST_NOT_BE_ALLOWED_TO_DESTROY_THE_MARSHLAND_FOR_THEIR_GREEDY_PURPOSES;

	/**
	 * ID: 1000214<br>
	 * Message: �E���Ȃ���ȁD�D�D
	 */
	public static final NpcStringId YOU_ARE_A_BRAVE_MAN;

	/**
	 * ID: 1000215<br>
	 * Message: �����҂ǂ���D�D�D���O�������������������������͂����B
	 */
	public static final NpcStringId YOU_IDIOTS_SOME_DAY_YOU_SHALL_ALSO_BE_GONE;

	/**
	 * ID: 1000216<br>
	 * Message: �N�����X�ɓ����ė����D�D�D
	 */
	public static final NpcStringId SOMEONE_HAS_ENTERED_THE_FOREST;

	/**
	 * ID: 1000217<br>
	 * Message: ���̐X�͂ƂĂ��Â��ŕ��a�ȏꏊ���B
	 */
	public static final NpcStringId THE_FOREST_IS_VERY_QUIET_AND_PEACEFUL;

	/**
	 * ID: 1000218<br>
	 * Message: ���͂������B���̐X�͂ƂĂ������Ƃ��낾�B
	 */
	public static final NpcStringId STAY_HERE_IN_THIS_WONDERFUL_FOREST;

	/**
	 * ID: 1000219<br>
	 * Message: ��D�D�D���̍������D�D�D
	 */
	public static final NpcStringId MY_MY_SOULS;

	/**
	 * ID: 1000220<br>
	 * Message: ���̐X�͊댯���B
	 */
	public static final NpcStringId THIS_FOREST_IS_A_DANGEROUS_PLACE;

	/**
	 * ID: 1000221<br>
	 * Message: ���������̐X����o�čs���I�����Ȃ��΁A�傫�ȍЂ����~�肩���邾�낤�B
	 */
	public static final NpcStringId UNLESS_YOU_LEAVE_THIS_FOREST_IMMEDIATELY_YOU_ARE_BOUND_TO_RUN_INTO_SERIOUS_TROUBLE;

	/**
	 * ID: 1000222<br>
	 * Message: ��������������o�čs���I
	 */
	public static final NpcStringId LEAVE_NOW;

	/**
	 * ID: 1000223<br>
	 * Message: �ǂ����āA���̌x���𖳎�����̂��H
	 */
	public static final NpcStringId WHY_DO_YOU_IGNORE_MY_WARNING;

	/**
	 * ID: 1000224<br>
	 * Message: ���̐��̂��ׂẴn���c������D�D�D���O���������炩�ɂ��Ă�낤�D�D�D
	 */
	public static final NpcStringId HARITS_OF_THE_WORLD_I_BRING_YOU_PEACE;

	/**
	 * ID: 1000225<br>
	 * Message: ���ׂẴn���c������I�E�C���o���I
	 */
	public static final NpcStringId HARITS_BE_COURAGEOUS;

	/**
	 * ID: 1000226<br>
	 * Message: ���O�̐Ԃ��S��������Ă��I
	 */
	public static final NpcStringId I_SHALL_EAT_YOUR_STILL_BEATING_HEART;

	/**
	 * ID: 1000227<br>
	 * Message: ���̃n���c������D�D�D�Ăі߂��ė�����܂ŁD�D�D��]���̂āD�D�D��ȁD�D�D
	 */
	public static final NpcStringId HARITS_KEEP_FAITH_UNTIL_THE_DAY_I_RETURN_NEVER_LOSE_HOPE;

	/**
	 * ID: 1000228<br>
	 * Message: ���l�����������̐��E�I�������̂͂Ȃ��I
	 */
	public static final NpcStringId EVEN_THE_GIANTS_ARE_GONE_THERES_NOTHING_LEFT_TO_BE_AFRAID_OF_NOW;

	/**
	 * ID: 1000229<br>
	 * Message: ���l�ɂ��Ēm���Ă���ȁH���̖ŖS�͓��R�߂��錋�ʂȂ̂��I
	 */
	public static final NpcStringId HAVE_YOU_HEARD_OF_THE_GIANTS_THEIR_DOWNFALL_WAS_INEVITABLE;

	/**
	 * ID: 1000230<br>
	 * Message: �����I���Ɛ키�̂��I
	 */
	public static final NpcStringId WHAT_NERVE_DO_YOU_DARE_TO_CHALLENGE_ME;

	/**
	 * ID: 1000231<br>
	 * Message: ���O���܂����l�Ɠ������׈��Ȃ�D�D�D���ȁD�D�D
	 */
	public static final NpcStringId YOU_ARE_AS_EVIL_AS_THE_GIANTS;

	/**
	 * ID: 1000232<br>
	 * Message: ���̃_���W�����͂܂����݂��B
	 */
	public static final NpcStringId THIS_DUNGEON_IS_STILL_IN_GOOD_CONDITION;

	/**
	 * ID: 1000233<br>
	 * Message: ���ɗY�s���ȁB
	 */
	public static final NpcStringId THIS_PLACE_IS_SPECTACULAR_WOULDNT_YOU_SAY;

	/**
	 * ID: 1000234<br>
	 * Message: ���O�����͗E���Ȑ�m���I
	 */
	public static final NpcStringId YOU_ARE_VERY_BRAVE_WARRIORS;

	/**
	 * ID: 1000235<br>
	 * Message: ��͂�������l�̎���͗��Ȃ��̂��B
	 */
	public static final NpcStringId ARE_THE_GIANTS_TRULY_GONE_FOR_GOOD;

	/**
	 * ID: 1000236<br>
	 * Message: �����悾�B
	 */
	public static final NpcStringId THESE_GRAVES_ARE_GOOD;

	/**
	 * ID: 1000237<br>
	 * Message: �S�҂ɋ������ȂǕK�v�͂Ȃ��B
	 */
	public static final NpcStringId GOLD_AND_SILVER_ARE_MEANINGLESS_TO_A_DEAD_MAN;

	/**
	 * ID: 1000238<br>
	 * Message: �Ԕ��ȋM���ǂ��߁I�Ȃ��g�����̂�����̂𖄂߂�̂��B
	 */
	public static final NpcStringId WHY_WOULD_THOSE_CORRUPT_ARISTOCRATS_BURY_SUCH_USEFUL_THINGS;

	/**
	 * ID: 1000239<br>
	 * Message: ����������B��������������炵�����邾�Ȃ�āB
	 */
	public static final NpcStringId YOU_FILTHY_PIG_EAT_AND_BE_MERRY_NOW_THAT_YOU_HAVE_SHIRKED_YOUR_RESPONSIBILITIES;

	/**
	 * ID: 1000240<br>
	 * Message: �Y�^�Y�^�ɐ؂�􂢂āA���V�����V���򂢎U�炷�����ł͋C�������܂��̂��I�A�f���̂��߁A�����񂼁I
	 */
	public static final NpcStringId THOSE_THUGS_IT_WOULD_BE_TOO_MERCIFUL_TO_RIP_THEM_APART_AND_CHEW_THEM_UP_ONE_AT_A_TIME;

	/**
	 * ID: 1000241<br>
	 * Message: �����A�A�f���̂��߁I
	 */
	public static final NpcStringId YOU_ACCURSED_SCOUNDRELS;

	/**
	 * ID: 1000242<br>
	 * Message: �t�b�D�D�D�A�f���̃A�z�ǂ����������ÎE�҂��B
	 */
	public static final NpcStringId HMM_COULD_THIS_BE_THE_ASSASSIN_SENT_BY_THOSE_IDIOTS_FROM_ADEN;

	/**
	 * ID: 1000243<br>
	 * Message: ���̑����������Ȃ�����D�D�D�i���ɂ��O����������Ă�낤�I
	 */
	public static final NpcStringId I_SHALL_CURSE_YOUR_NAME_WITH_MY_LAST_BREATH;

	/**
	 * ID: 1000244<br>
	 * Message: �h������V�[�����l�D�D�D
	 */
	public static final NpcStringId MY_BELOVED_LORD_SHILEN;

	/**
	 * ID: 1000245<br>
	 * Message: �V�[�����l�̕���𑁂������č����グ�Ȃ���΂Ȃ�Ȃ��̂ɁD�D�D
	 */
	public static final NpcStringId I_MUST_BREAK_THE_SEAL_AND_RELEASE_LORD_SHILEN_AS_SOON_AS_POSSIBLE;

	/**
	 * ID: 1000246<br>
	 * Message: �V�[�����l�̖��̂��ƂɁA���O��������������I
	 */
	public static final NpcStringId YOU_SHALL_TASTE_THE_VENGEANCE_OF_LORD_SHILEN;

	/**
	 * ID: 1000247<br>
	 * Message: �V�[�����l�D�D�D���̐E�����D�D�D�����́D�D�D�������D�D�D
	 */
	public static final NpcStringId LORD_SHILEN_SOME_DAY_YOU_WILL_ACCOMPLISH_THIS_MISSION;

	/**
	 * ID: 1000248<br>
	 * Message: �s�łɌ������āD�D�D
	 */
	public static final NpcStringId TOWARDS_IMMORTALITY;

	/**
	 * ID: 1000249<br>
	 * Message: �i���̖����~�����ҁD�D�D���̂Ƃ���ɗ����B
	 */
	public static final NpcStringId HE_WHO_DESIRES_IMMORTALITY_COME_UNTO_ME;

	/**
	 * ID: 1000250<br>
	 * Message: ���O�Ȃ񂼂͎��̕s�V�s���̃G�l���M�[�ɂ��Ă��I
	 */
	public static final NpcStringId YOU_SHALL_BE_SACRIFICED_TO_GAIN_MY_IMMORTALITY;

	/**
	 * ID: 1000251<br>
	 * Message: ���������ŉi���̖�����ɓ���Ƃ��낾�����̂ɁD�D�D����Ȃӂ��əR�����ʂȂ�āD�D�D
	 */
	public static final NpcStringId ETERNAL_LIFE_IN_FRONT_OF_MY_EYES_I_HAVE_COLLAPSED_IN_SUCH_A_WORTHLESS_WAY_LIKE_THIS;

	/**
	 * ID: 1000252<br>
	 * Message: �U�P���A�Ȃ�Ĕڋ��Ȃ�I
	 */
	public static final NpcStringId ZAKEN_YOU_ARE_A_COWARDLY_CUR;

	/**
	 * ID: 1000253<br>
	 * Message: �U�P���A���O�͉i���̖�����ɂ��Ă��Ȃ������̂��I
	 */
	public static final NpcStringId YOU_ARE_IMMORTAL_ARENT_YOU_ZAKEN;

	/**
	 * ID: 1000254<br>
	 * Message: ���́D�D�D���̐g�̂�Ԃ��Ă���D�D�D
	 */
	public static final NpcStringId PLEASE_RETURN_MY_BODY_TO_ME;

	/**
	 * ID: 1000255<br>
	 * Message: �����A����ł����D�D�D���炩�ɖ����Ƃ������ƂȂ̂��D�D�D
	 */
	public static final NpcStringId FINALLY_WILL_I_BE_ABLE_TO_REST;

	/**
	 * ID: 1000256<br>
	 * Message: �Ȃ�����Ȃɑ��X�����̂ł����B
	 */
	public static final NpcStringId WHAT_IS_ALL_THAT_RACKET;

	/**
	 * ID: 1000257<br>
	 * Message: �L���h���l�͑����������Ƃ��������ł��B
	 */
	public static final NpcStringId MASTER_GILDOR_DOES_NOT_LIKE_TO_BE_DISTURBED;

	/**
	 * ID: 1000258<br>
	 * Message: ���������Â��ɂ��Ă����������炢���̂ł����D�D�D
	 */
	public static final NpcStringId PLEASE_JUST_HOLD_IT_DOWN;

	/**
	 * ID: 1000259<br>
	 * Message: �L���h���l�����{��ɂȂ��Ă����͂����m��܂���B
	 */
	public static final NpcStringId IF_YOU_DISTURB_MASTER_GILDOR_I_WONT_BE_ABLE_TO_HELP_YOU;

	/**
	 * ID: 1000260<br>
	 * Message: �ǂ��̂ǂ�������Ƃ����񂾁H
	 */
	public static final NpcStringId WHO_DARES_APPROACH;

	/**
	 * ID: 1000261<br>
	 * Message: ���̑����͎��̗̒n�D�D�D
	 */
	public static final NpcStringId THESE_REEDS_ARE_MY_TERRITORY;

	/**
	 * ID: 1000262<br>
	 * Message: �����҂߁I�ڂɕ��������Ă��I
	 */
	public static final NpcStringId YOU_FOOLS_TODAY_YOU_SHALL_LEARN_A_LESSON;

	/**
	 * ID: 1000263<br>
	 * Message: �Â��悫����͋����āD�D�D�V�������オ�J�����̂��D�D�D
	 */
	public static final NpcStringId THE_PAST_GOES_BY_IS_A_NEW_ERA_BEGINNING;

	/**
	 * ID: 1000264<br>
	 * Message: �����̓G���@�l�̒뉀�ł��B
	 */
	public static final NpcStringId THIS_IS_THE_GARDEN_OF_EVA;

	/**
	 * ID: 1000265<br>
	 * Message: �G���@�l�̒뉀�͐��Ȃ�ꏊ�ł��B
	 */
	public static final NpcStringId THE_GARDEN_OF_EVA_IS_A_SACRED_PLACE;

	/**
	 * ID: 1000266<br>
	 * Message: �G���@�l��`�������̂ł����B
	 */
	public static final NpcStringId DO_YOU_MEAN_TO_INSULT_EVA;

	/**
	 * ID: 1000267<br>
	 * Message: ����Ȗ���Ȃ��Ȃ��ł��D�D�D�G���@�l�͈��ŕ��ł�������ł��傤�D�D�D
	 */
	public static final NpcStringId HOW_RUDE_EVAS_LOVE_IS_AVAILABLE_TO_ALL_EVEN_TO_AN_ILL_MANNERED_LOUT_LIKE_YOURSELF;

	/**
	 * ID: 1000268<br>
	 * Message: �����͌��X�V�[�����l�̂��̂��B
	 */
	public static final NpcStringId THIS_PLACE_ONCE_BELONGED_TO_LORD_SHILEN;

	/**
	 * ID: 1000269<br>
	 * Message: �{�a�������n���̂��B�G���@�̐��삽����B
	 */
	public static final NpcStringId LEAVE_THIS_PALACE_TO_US_SPIRITS_OF_EVA;

	/**
	 * ID: 1000270<br>
	 * Message: �Ȃ���X�̎ז�������̂��B
	 */
	public static final NpcStringId WHY_ARE_YOU_GETTING_IN_OUR_WAY;

	/**
	 * ID: 1000271<br>
	 * Message: �V�[�����l�D�D�D�V�[�����l�́D�D�D
	 */
	public static final NpcStringId SHILEN_OUR_SHILEN;

	/**
	 * ID: 1000272<br>
	 * Message: �p�v���I���l�������ҁD�D�D��������o�čs���̂��I
	 */
	public static final NpcStringId ALL_WHO_FEAR_OF_FAFURION_LEAVE_THIS_PLACE_AT_ONCE;

	/**
	 * ID: 1000273<br>
	 * Message: �p�v���I���l�̖��̂��ƂɁA���O�����Y����I
	 */
	public static final NpcStringId YOU_ARE_BEING_PUNISHED_IN_THE_NAME_OF_FAFURION;

	/**
	 * ID: 1000274<br>
	 * Message: ����l�l�D�D�D��݂Ȃ킽���������������������D�D�D
	 */
	public static final NpcStringId OH_MASTER_PLEASE_FORGIVE_YOUR_HUMBLE_SERVANT;

	/**
	 * ID: 1000275<br>
	 * Message: �o�債��A�ٍ��̐N���҂ǂ��I���̗v�ǂ̎x�z�҂ł���A�O�X�^�t�l�����̑�������Ă�낤�I
	 */
	public static final NpcStringId PREPARE_TO_DIE_FOREIGN_INVADERS_I_AM_GUSTAV_THE_ETERNAL_RULER_OF_THIS_FORTRESS_AND_I_HAVE_TAKEN_UP_MY_SWORD_TO_REPEL_THEE;

	/**
	 * ID: 1000276<br>
	 * Message: ���҂̉����A�A�f���ɉh�����I�s���̌N��A�O�X�^�t�l�ɉh�����I
	 */
	public static final NpcStringId GLORY_TO_ADEN_THE_KINGDOM_OF_THE_LION_GLORY_TO_SIR_GUSTAV_OUR_IMMORTAL_LORD;

	/**
	 * ID: 1000277<br>
	 * Message: �O�X�^�t�̐�m������A�킦�I�N���҂Ɏ����I
	 */
	public static final NpcStringId SOLDIERS_OF_GUSTAV_GO_FORTH_AND_DESTROY_THE_INVADERS;

	/**
	 * ID: 1000278<br>
	 * Message: ����Ȃ͂��́D�D�D���ꂪ�A�s�k�Ƃ������̂Ȃ̂��B�������A�K���߂�I���O�����̎�����炢�ɂȁI
	 */
	public static final NpcStringId THIS_IS_UNBELIEVABLE_HAVE_I_REALLY_BEEN_DEFEATED_I_SHALL_RETURN_AND_TAKE_YOUR_HEAD;

	/**
	 * ID: 1000279<br>
	 * Message: ���ꂪ���̌��E�Ȃ̂��D�D�D�����A�O�X�^�t�l�̋��Ȃ����Ă͎��˂�̂��I
	 */
	public static final NpcStringId COULD_IT_BE_THAT_I_HAVE_REACHED_MY_END_I_CANNOT_DIE_WITHOUT_HONOR_WITHOUT_THE_PERMISSION_OF_SIR_GUSTAV;

	/**
	 * ID: 1000280<br>
	 * Message: �܂��Ăєs�k�̐J�߂𖡂키�̂��D�D�D�����A�ߌ��͂���ł͏I����D�D�D
	 */
	public static final NpcStringId AH_THE_BITTER_TASTE_OF_DEFEAT_I_FEAR_MY_TORMENTS_ARE_NOT_OVER;

	/**
	 * ID: 1000281<br>
	 * Message: ���̓p�v���I���l�̈ӎu�ɏ]���ҁI
	 */
	public static final NpcStringId I_FOLLOW_THE_WILL_OF_FAFURION;

	/**
	 * ID: 1000282<br>
	 * Message: �K�^�̕󂭂��̔̔����s���܂��I
	 */
	public static final NpcStringId TICKETS_FOR_THE_LUCKY_LOTTERY_ARE_NOW_ON_SALE;

	/**
	 * ID: 1000283<br>
	 * Message: �K�^�̕󂭂��̒��I���s���܂��I
	 */
	public static final NpcStringId THE_LUCKY_LOTTERY_DRAWING_IS_ABOUT_TO_BEGIN;

	/**
	 * ID: 1000284<br>
	 * Message: ���߂łƂ��������܂��I��$s1��̓��I�ԍ���$s2�ł��B
	 */
	public static final NpcStringId THE_WINNING_NUMBERS_FOR_LUCKY_LOTTERY_S1_ARE_S2_CONGRATULATIONS_TO_THE_WINNERS;

	/**
	 * ID: 1000285<br>
	 * Message: �N����ɂ���ĕ󂭂��𔃂����Ƃ��ł��܂���B
	 */
	public static final NpcStringId YOURE_TOO_YOUNG_TO_PLAY_LUCKY_LOTTERY;

	/**
	 * ID: 1000286<br>
	 * Message: $s1�I�w��ɋC������񂾂ȁI
	 */
	public static final NpcStringId S1_WATCH_YOUR_BACK;

	/**
	 * ID: 1000287<br>
	 * Message: $s1�I������ݔq���Ƃ������I
	 */
	public static final NpcStringId S1_COME_ON_ILL_TAKE_YOU_ON;

	/**
	 * ID: 1000288<br>
	 * Message: $s1�I�ז�������Ƃ͂��������I�������A���݂��Ă���I
	 */
	public static final NpcStringId S1_HOW_DARE_YOU_INTERRUPT_OUR_FIGHT_HEY_GUYS_HELP;

	/**
	 * ID: 1000289<br>
	 * Message: �͂�݂����I�ڋ��҂͋�����I
	 */
	public static final NpcStringId ILL_HELP_YOU_IM_NO_COWARD;

	/**
	 * ID: 1000290<br>
	 * Message: ���ɂ̗͂�I
	 */
	public static final NpcStringId DEAR_ULTIMATE_POWER;

	/**
	 * ID: 1000291<br>
	 * Message: �S�� $s1 �ɏW���U�����I
	 */
	public static final NpcStringId EVERYBODY_ATTACK_S1;

	/**
	 * ID: 1000292<br>
	 * Message: ���߂ɏ]���܂��B
	 */
	public static final NpcStringId I_WILL_FOLLOW_YOUR_ORDER;

	/**
	 * ID: 1000293<br>
	 * Message: ����͒m��Ȃ��������낤�I
	 */
	public static final NpcStringId BET_YOU_DIDNT_EXPECT_THIS;

	/**
	 * ID: 1000294<br>
	 * Message: �o�ł�I�ł̎҂ǂ��I
	 */
	public static final NpcStringId COME_OUT_YOU_CHILDREN_OF_DARKNESS;

	/**
	 * ID: 1000295<br>
	 * Message: �p�[�e�B �����o�[�����I
	 */
	public static final NpcStringId SUMMON_PARTY_MEMBERS;

	/**
	 * ID: 1000296<br>
	 * Message: ����l�l�I���Ăтł��傤���B
	 */
	public static final NpcStringId MASTER_DID_YOU_CALL_ME;

	/**
	 * ID: 1000297<br>
	 * Message: �����҂߁I
	 */
	public static final NpcStringId YOU_IDIOT;

	/**
	 * ID: 1000298<br>
	 * Message: ����͂ǂ����I
	 */
	public static final NpcStringId WHAT_ABOUT_THIS;

	/**
	 * ID: 1000299<br>
	 * Message: ���� $s1�I����ōŌゾ�I
	 */
	public static final NpcStringId VERY_IMPRESSIVE_S1_THIS_IS_THE_LAST;

	/**
	 * ID: 1000300<br>
	 * Message: �t��
	 */
	public static final NpcStringId DAWN;

	/**
	 * ID: 1000301<br>
	 * Message: ����
	 */
	public static final NpcStringId DUSK;

	/**
	 * ID: 1000302<br>
	 * Message: �Ȃ�
	 */
	public static final NpcStringId NOTHINGNESS;

	/**
	 * ID: 1000303<br>
	 * Message: �܂��Ȃ����̐��͖łт�ł��낤�I
	 */
	public static final NpcStringId THIS_WORLD_WILL_SOON_BE_ANNIHILATED;

	/**
	 * ID: 1000304<br>
	 * Message: ���Ȃ��Ɏ􂢂������Ă�낤�I
	 */
	public static final NpcStringId A_CURSE_UPON_YOU;

	/**
	 * ID: 1000305<br>
	 * Message: �ق��̓����߂Â��Ă���I
	 */
	public static final NpcStringId THE_DAY_OF_JUDGMENT_IS_NEAR;

	/**
	 * ID: 1000306<br>
	 * Message: ���Ȃ��ɏj�����I
	 */
	public static final NpcStringId I_BESTOW_UPON_YOU_A_BLESSING;

	/**
	 * ID: 1000307<br>
	 * Message: �ア����ɓ|���̂��P���J�̖@�����I
	 */
	public static final NpcStringId THE_FIRST_RULE_OF_FIGHTING_IS_TO_START_BY_KILLING_THE_WEAK_ONES;

	/**
	 * ID: 1000308<br>
	 * Message: �A�f�i
	 */
	public static final NpcStringId ADENA;

	/**
	 * ID: 1000309<br>
	 * Message: �Ñ�̃A�f�i
	 */
	public static final NpcStringId ANCIENT_ADENA;

	/**
	 * ID: 1000312<br>
	 * Message: ���x��32����
	 */
	public static final NpcStringId LEVEL_31_OR_LOWER;

	/**
	 * ID: 1000313<br>
	 * Message: ���x��43����
	 */
	public static final NpcStringId LEVEL_42_OR_LOWER;

	/**
	 * ID: 1000314<br>
	 * Message: ���x��54����
	 */
	public static final NpcStringId LEVEL_53_OR_LOWER;

	/**
	 * ID: 1000315<br>
	 * Message: ���x��65����
	 */
	public static final NpcStringId LEVEL_64_OR_LOWER;

	/**
	 * ID: 1000316<br>
	 * Message: ������
	 */
	public static final NpcStringId NO_LEVEL_LIMIT;

	/**
	 * ID: 1000317<br>
	 * Message: 2����Ɉł̍ՓT���n�܂�܂��B��t���s���Ă��������B
	 */
	public static final NpcStringId THE_MAIN_EVENT_WILL_START_IN_2_MINUTES_PLEASE_REGISTER_NOW;

	/**
	 * ID: 1000318<br>
	 * Message: �ł̍ՓT���n�߂܂��B
	 */
	public static final NpcStringId THE_MAIN_EVENT_IS_NOW_STARTING;

	/**
	 * ID: 1000319<br>
	 * Message: 5����Ɉł̍ՓT���I�����܂��B
	 */
	public static final NpcStringId THE_MAIN_EVENT_WILL_CLOSE_IN_5_MINUTES;

	/**
	 * ID: 1000320<br>
	 * Message: 2����Ɉł̍ՓT���I�����܂��B���̃Q�[���̏��������Ă��������B
	 */
	public static final NpcStringId THE_MAIN_EVENT_WILL_FINISH_IN_2_MINUTES_PLEASE_PREPARE_FOR_THE_NEXT_GAME;

	/**
	 * ID: 1000321<br>
	 * Message: SSQ �v���x�� $s1 �オ��܂����B
	 */
	public static final NpcStringId THE_AMOUNT_OF_SSQ_CONTRIBUTION_HAS_INCREASED_BY_S1;

	/**
	 * ID: 1000322<br>
	 * Message: �L�^�Ȃ�
	 */
	public static final NpcStringId NO_RECORD_EXISTS;

	/**
	 * ID: 1000380<br>
	 * Message: �\�����Ȃ����т��I�����O�ɑ����Ă�낤�B
	 */
	public static final NpcStringId THAT_WILL_DO_ILL_MOVE_YOU_TO_THE_OUTSIDE_SOON;

	/**
	 * ID: 1000382<br>
	 * Message: ��낪�X�L���炯���ȁI
	 */
	public static final NpcStringId YOUR_REAR_IS_PRACTICALLY_UNGUARDED;

	/**
	 * ID: 1000383<br>
	 * Message: �w��������Ƃ́I�������Ă���̂��I
	 */
	public static final NpcStringId HOW_DARE_YOU_TURN_YOUR_BACK_ON_ME;

	/**
	 * ID: 1000384<br>
	 * Message: $s1�I��Έ�ŏ������I
	 */
	public static final NpcStringId S1_ILL_DEAL_WITH_YOU_MYSELF;

	/**
	 * ID: 1000385<br>
	 * Message: $s1�I�Ό����I
	 */
	public static final NpcStringId S1_THIS_IS_PERSONAL;

	/**
	 * ID: 1000386<br>
	 * Message: $s1�I�������I�N���ז�����ȁI
	 */
	public static final NpcStringId S1_LEAVE_US_ALONE_THIS_IS_BETWEEN_US;

	/**
	 * ID: 1000387<br>
	 * Message: $s1�I���O�̖��͎������炤�I
	 */
	public static final NpcStringId S1_KILLING_YOU_WILL_BE_A_PLEASURE;

	/**
	 * ID: 1000388<br>
	 * Message: $s1�I�悭���Ό���W�Q���Ă��ꂽ�ȁI�����҂߁I
	 */
	public static final NpcStringId S1_HEY_WERE_HAVING_A_DUEL_HERE;

	/**
	 * ID: 1000389<br>
	 * Message: ��Έ�̑Ό��͏I��肾�I�F�̎ҁA����Ă��܂��I
	 */
	public static final NpcStringId THE_DUEL_IS_OVER_ATTACK;

	/**
	 * ID: 1000390<br>
	 * Message: �������I�F�̎ҁA�ڋ��҂��n������I
	 */
	public static final NpcStringId FOUL_KILL_THE_COWARD;

	/**
	 * ID: 1000391<br>
	 * Message: �_���Ȍ�����W�Q����Ƃ́I�����ł͂����Ȃ����I
	 */
	public static final NpcStringId HOW_DARE_YOU_INTERRUPT_A_SACRED_DUEL_YOU_MUST_BE_TAUGHT_A_LESSON;

	/**
	 * ID: 1000392<br>
	 * Message: �ڋ��ҁI������I
	 */
	public static final NpcStringId DIE_YOU_COWARD;

	/**
	 * ID: 1000394<br>
	 * Message: �ڋ��҂Ɏ����I
	 */
	public static final NpcStringId KILL_THE_COWARD;

	/**
	 * ID: 1000395<br>
	 * Message: ��˂𑊎�ɂ��̃X�L�����g���Ƃ́I
	 */
	public static final NpcStringId I_NEVER_THOUGHT_ID_USE_THIS_AGAINST_A_NOVICE;

	/**
	 * ID: 1000396<br>
	 * Message: �܂��܂����ɂ͏��Ă񂼁I
	 */
	public static final NpcStringId YOU_WONT_TAKE_ME_DOWN_EASILY;

	/**
	 * ID: 1000397<br>
	 * Message: ���ꂩ�炪�{���̐킢���I
	 */
	public static final NpcStringId THE_BATTLE_HAS_JUST_BEGUN;

	/**
	 * ID: 1000398<br>
	 * Message: $s1����|���I
	 */
	public static final NpcStringId KILL_S1_FIRST;

	/**
	 * ID: 1000399<br>
	 * Message: �W���U�����I�ڕW��$s1�I
	 */
	public static final NpcStringId ATTACK_S1;

	/**
	 * ID: 1000400<br>
	 * Message: �U�����I�U���I
	 */
	public static final NpcStringId ATTACK_ATTACK;

	/**
	 * ID: 1000401<br>
	 * Message: �W���U���J�n�I
	 */
	public static final NpcStringId OVER_HERE;

	/**
	 * ID: 1000402<br>
	 * Message: ���W���[�I
	 */
	public static final NpcStringId ROGER;

	/**
	 * ID: 1000403<br>
	 * Message: �F�̎ҁI�o�ė����I
	 */
	public static final NpcStringId SHOW_YOURSELVES;

	/**
	 * ID: 1000404<br>
	 * Message: �ł̌R����A���ɑ����I
	 */
	public static final NpcStringId FORCES_OF_DARKNESS_FOLLOW_ME;

	/**
	 * ID: 1000405<br>
	 * Message: �Z�킽����A�G��r�ł�����I
	 */
	public static final NpcStringId DESTROY_THE_ENEMY_MY_BROTHERS;

	/**
	 * ID: 1000406<br>
	 * Message: ���ꂩ�炪�{�Ԃ��I
	 */
	public static final NpcStringId NOW_THE_FUN_STARTS;

	/**
	 * ID: 1000407<br>
	 * Message: �Â������̂��ԈႢ�������I�{�C�ő�������Ă�낤�I
	 */
	public static final NpcStringId ENOUGH_FOOLING_AROUND_GET_READY_TO_DIE;

	/**
	 * ID: 1000408<br>
	 * Message: �����҂߂��I���܂Ŏ�𔲂��Ă����������I
	 */
	public static final NpcStringId YOU_IDIOT_IVE_JUST_BEEN_TOYING_WITH_YOU;

	/**
	 * ID: 1000409<br>
	 * Message: ���̖{���̗͂������Ă�낤�I
	 */
	public static final NpcStringId WITNESS_MY_TRUE_POWER;

	/**
	 * ID: 1000410<br>
	 * Message: �{���̐킢�͂��ꂩ�炾�I
	 */
	public static final NpcStringId NOW_THE_BATTLE_BEGINS;

	/**
	 * ID: 1000411<br>
	 * Message: �v���Ԃ�Ɍ��̑������肾�ȁI
	 */
	public static final NpcStringId I_MUST_ADMIT_NO_ONE_MAKES_MY_BLOOD_BOIL_QUITE_LIKE_YOU_DO;

	/**
	 * ID: 1000412<br>
	 * Message: �����͋������I���������܂Œǂ����ނƂ́I
	 */
	public static final NpcStringId YOU_HAVE_MORE_SKILL_THAN_I_THOUGHT;

	/**
	 * ID: 1000413<br>
	 * Message: 200���̐퓬�͂ő��肵�Ă�낤�I
	 */
	public static final NpcStringId ILL_DOUBLE_MY_STRENGTH;

	/**
	 * ID: 1000414<br>
	 * Message: ���ꂪ�Ōゾ�I�I���ɂ��Ă�邼�I
	 */
	public static final NpcStringId PREPARE_TO_DIE;

	/**
	 * ID: 1000415<br>
	 * Message: �F�̎ҁA�߂��݂ɕ��邪�悢�I�Ԃ��Ȃ����̏��_���~�Ղ���̂��I
	 */
	public static final NpcStringId ALL_IS_LOST_PREPARE_TO_MEET_THE_GODDESS_OF_DEATH;

	/**
	 * ID: 1000416<br>
	 * Message: �F�̎ҁA�߂��݂ɕ��邪�悢�I�Ԃ��Ȃ��ŖS�̗\�������������ł��낤�I
	 */
	public static final NpcStringId ALL_IS_LOST_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED;

	/**
	 * ID: 1000417<br>
	 * Message: �ŖS�̗\���Ɏ����X����I�Ԃ��Ȃ��I���̎��オ�n�܂�̂��I
	 */
	public static final NpcStringId THE_END_OF_TIME_HAS_COME_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED;

	/**
	 * ID: 1000418<br>
	 * Message: $s1�I���Ȃ��ɖŖS�̒�����������I
	 */
	public static final NpcStringId S1_YOU_BRING_AN_ILL_WIND;

	/**
	 * ID: 1000419<br>
	 * Message: $s1�I���Ȃ��ɐ�]������Ă�낤�I
	 */
	public static final NpcStringId S1_YOU_MIGHT_AS_WELL_GIVE_UP;

	/**
	 * ID: 1000420<br>
	 * Message: �I���̎���𐶂���҂�A��]�̕��ɗ�����I
	 */
	public static final NpcStringId YOU_DONT_HAVE_ANY_HOPE_YOUR_END_HAS_COME;

	/**
	 * ID: 1000421<br>
	 * Message: �ł̌[���Ɏ����X����I�ł����肩��o�߂��̂��I
	 */
	public static final NpcStringId THE_PROPHECY_OF_DARKNESS_HAS_BEEN_FULFILLED;

	/**
	 * ID: 1000422<br>
	 * Message: �ł̌[���Ɏ����X����I���ׂ̎��オ�n�܂����̂��I
	 */
	public static final NpcStringId AS_FORETOLD_IN_THE_PROPHECY_OF_DARKNESS_THE_ERA_OF_CHAOS_HAS_BEGUN;

	/**
	 * ID: 1000423<br>
	 * Message: �ł̌[���Ɏ����X����I�Ԃ��Ȃ����̊y���ƂȂ邾�낤�I
	 */
	public static final NpcStringId THE_PROPHECY_OF_DARKNESS_HAS_COME_TO_PASS;

	/**
	 * ID: 1000424<br>
	 * Message: $s1�I���Ȃ��Ɍ[���̏j����^���悤�I
	 */
	public static final NpcStringId S1_I_GIVE_YOU_THE_BLESSING_OF_PROPHECY;

	/**
	 * ID: 1000425<br>
	 * Message: $s1�I���Ȃ��ɐ[���̌��\��^���悤�I
	 */
	public static final NpcStringId S1_I_BESTOW_UPON_YOU_THE_AUTHORITY_OF_THE_ABYSS;

	/**
	 * ID: 1000426<br>
	 * Message: �V����������J���҂�A�ڂ��o�܂��I
	 */
	public static final NpcStringId HERALD_OF_THE_NEW_ERA_OPEN_YOUR_EYES;

	/**
	 * ID: 1000427<br>
	 * Message: �ア�҂���|���̂��킢�̖@�����I
	 */
	public static final NpcStringId REMEMBER_KILL_THE_WEAKLINGS_FIRST;

	/**
	 * ID: 1000428<br>
	 * Message: �Ўセ���Ȃ��O����E���Ă��I
	 */
	public static final NpcStringId PREPARE_TO_DIE_MAGGOT;

	/**
	 * ID: 1000429<br>
	 * Message: ���т͂��̂��炢�ŏ[�����I�����O�ɏo���Ă�邩�珀������I
	 */
	public static final NpcStringId THAT_WILL_DO_PREPARE_TO_BE_RELEASED;

	/**
	 * ID: 1000430<br>
	 * Message: ��������
	 */
	public static final NpcStringId DRAW;

	/**
	 * ID: 1000431<br>
	 * Message: ����̎x�z�҂̊F�l�I���������i�����������ɎQ��܂����I
	 */
	public static final NpcStringId RULERS_OF_THE_SEAL_I_BRING_YOU_WONDROUS_GIFTS;

	/**
	 * ID: 1000432<br>
	 * Message: ����̎x�z�҂̊F�l�I�ō����̕�������������ɎQ��܂����I
	 */
	public static final NpcStringId RULERS_OF_THE_SEAL_I_HAVE_SOME_EXCELLENT_WEAPONS_TO_SHOW_YOU;

	/**
	 * ID: 1000433<br>
	 * Message: ���������s���Ȃ���Ȃ��A���[�Z�����A�Z�����I
	 */
	public static final NpcStringId IVE_BEEN_SO_BUSY_LATELY_IN_ADDITION_TO_PLANNING_MY_TRIP;

	/**
	 * ID: 1000434<br>
	 * Message: �ア�҂����߂Ƃ́I�����񂼁I
	 */
	public static final NpcStringId YOUR_TREATMENT_OF_WEAKLINGS_IS_UNFORGIVABLE;

	/**
	 * ID: 1000435<br>
	 * Message: �����ɗ������I��������I
	 */
	public static final NpcStringId IM_HERE_TO_HELP_YOU_HI_YAH;

	/**
	 * ID: 1000436<br>
	 * Message: ���`�̖��̂��ƂɁI
	 */
	public static final NpcStringId JUSTICE_WILL_BE_SERVED;

	/**
	 * ID: 1000437<br>
	 * Message: ���̂��ƂɁI�h���̖��̂��ƂɁI
	 */
	public static final NpcStringId ON_TO_IMMORTAL_GLORY;

	/**
	 * ID: 1000438<br>
	 * Message: ���`�͎ア�҂̖����I
	 */
	public static final NpcStringId JUSTICE_ALWAYS_AVENGES_THE_POWERLESS;

	/**
	 * ID: 1000439<br>
	 * Message: �܂��킦�邩�B�����������񒣂�I
	 */
	public static final NpcStringId ARE_YOU_HURT_HANG_IN_THERE_WEVE_ALMOST_GOT_THEM;

	/**
	 * ID: 1000440<br>
	 * Message: ���̖����B���O��ɋ����閼�ȂǂȂ���I
	 */
	public static final NpcStringId WHY_SHOULD_I_TELL_YOU_MY_NAME_YOU_CREEP;

	/**
	 * ID: 1000441<br>
	 * Message: ��t�\
	 */
	public static final NpcStringId N1_MINUTE;

	/**
	 * ID: 1000443<br>
	 * Message: $s1��̎�������o�[����s�Ƀe���|�[�g�����܂��B
	 */
	public static final NpcStringId THE_DEFENDERS_OF_S1_CASTLE_WILL_BE_TELEPORTED_TO_THE_INNER_CASTLE;

	/**
	 * ID: 1000444<br>
	 * Message: ���j��
	 */
	public static final NpcStringId SUNDAY;

	/**
	 * ID: 1000445<br>
	 * Message: ���j��
	 */
	public static final NpcStringId MONDAY;

	/**
	 * ID: 1000446<br>
	 * Message: �Ηj��
	 */
	public static final NpcStringId TUESDAY;

	/**
	 * ID: 1000447<br>
	 * Message: ���j��
	 */
	public static final NpcStringId WEDNESDAY;

	/**
	 * ID: 1000448<br>
	 * Message: �ؗj��
	 */
	public static final NpcStringId THURSDAY;

	/**
	 * ID: 1000449<br>
	 * Message: ���j��
	 */
	public static final NpcStringId FRIDAY;

	/**
	 * ID: 1000450<br>
	 * Message: �y�j��
	 */
	public static final NpcStringId SATURDAY;

	/**
	 * ID: 1000451<br>
	 * Message: ��
	 */
	public static final NpcStringId HOUR;

	/**
	 * ID: 1000452<br>
	 * Message: �Ō�̎􂢂��D�D�D�Ƃ��ɒn���֍s�����I
	 */
	public static final NpcStringId ITS_A_GOOD_DAY_TO_DIE_WELCOME_TO_HELL_MAGGOT;

	/**
	 * ID: 1000453<br>
	 * Message: 2����Ɉł̍ՓT���I�����܂��B
	 */
	public static final NpcStringId THE_FESTIVAL_OF_DARKNESS_WILL_END_IN_TWO_MINUTES;

	/**
	 * ID: 1000454<br>
	 * Message: �m�[�u���X �Q�[�g �p�X
	 */
	public static final NpcStringId NOBLESSE_GATE_PASS;

	/**
	 * ID: 1000456<br>
	 * Message: �����o�߂��܂���
	 */
	public static final NpcStringId MINUTES_HAVE_PASSED;
	
	/**
	 * ID: 1000457<br>
	 * Message: �Q�[�����I�����܂����B�Ԃ��Ȃ������e���|�[�g���܂��B
	 */
	public static final NpcStringId GAME_OVER_THE_TELEPORT_WILL_APPEAR_MOMENTARILY;

	/**
	 * ID: 1000458<br>
	 * Message: �n�𔇂�������ǂ���A�S��̃f�[�����̎��ߐ[���ƈ̑傳�͌v��m��ʂ̂��I�n�n�n�I
	 */
	public static final NpcStringId YOU_WHO_ARE_LIKE_THE_SLUGS_CRAWLING_ON_THE_GROUND_THE_GENEROSITY_AND_GREATNESS_OF_ME_DAIMON_THE_WHITE_EYED_IS_ENDLESS_HA_HA_HA;

	/**
	 * ID: 1000459<br>
	 * Message: ���̕S��̃f�[������G�ɉ񂷂Ȃ��{���炢�ł��ĂȂ��ƂȂ��H
	 */
	public static final NpcStringId IF_YOU_WANT_TO_BE_THE_OPPONENT_OF_ME_DAIMON_THE_WHITE_EYED_YOU_SHOULD_AT_LEAST_HAVE_THE_BASIC_SKILLS;

	/**
	 * ID: 1000460<br>
	 * Message: ��n�ɔ���ꂽ�����Ȏ҂ǂ���A�d���̂ŋ�킵�Ă���悤���ȁB���̃f�[���������̋�ɂ�a�炰�Ă�낤�B
	 */
	public static final NpcStringId YOU_STUPID_CREATURES_THAT_ARE_BOUND_TO_THE_EARTH_YOU_ARE_SUFFERING_TOO_MUCH_WHILE_DRAGGING_YOUR_FAT_HEAVY_BODIES_I_DAIMON_WILL_LIGHTEN_YOUR_BURDEN;

	/**
	 * ID: 1000461<br>
	 * Message: �M�l�����̂悤�ȓ��ŋ����Ȏ푰�����ɓG���͂����Ȃ��I�E�n�n�n�I
	 */
	public static final NpcStringId A_WEAK_AND_STUPID_TRIBE_LIKE_YOURS_DOESNT_DESERVE_TO_BE_MY_ENEMY_BWA_HA_HA_HA;

	/**
	 * ID: 1000462<br>
	 * Message: ���̕S��̃f�[�����̗̒n�����������҂�A���̑㏞�𕥂��������I
	 */
	public static final NpcStringId YOU_DARE_TO_INVADE_THE_TERRITORY_OF_DAIMON_THE_WHITE_EYED_NOW_YOU_WILL_PAY_THE_PRICE_FOR_YOUR_ACTION;

	/**
	 * ID: 1000463<br>
	 * Message: ���ꂱ���t���[�e�B���O �A�C�̈̑�Ȃ�N��A�S��̃f�[�����̉��b���I�n�n�n�I
	 */
	public static final NpcStringId THIS_IS_THE_GRACE_OF_DAIMON_THE_WHITE_EYED_THE_GREAT_MONSTER_EYE_LORD_HA_HA_HA;

	/**
	 * ID: 1000464<br>
	 * Message: $s1�l���f���G���X�g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DUELISTS_CONGRATULATIONS;

	/**
	 * ID: 1000465<br>
	 * Message: $s1�l���h���b�h�m�[�g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DREADNOUGHTS_CONGRATULATIONS;

	/**
	 * ID: 1000466<br>
	 * Message: $s1�l���t�F�j�b�N�X �i�C�g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_PHOENIX_KNIGHTS_CONGRATULATIONS;

	/**
	 * ID: 1000467<br>
	 * Message: $s1�l���w�� �i�C�g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_HELL_KNIGHTS_CONGRATULATIONS;

	/**
	 * ID: 1000468<br>
	 * Message: $s1�l���T�W�^���E�X�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_SAGITTARIUS_HERO_CONGRATULATIONS;

	/**
	 * ID: 1000469<br>
	 * Message: $s1�l���A�h�x���`�����[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ADVENTURERS_CONGRATULATIONS;

	/**
	 * ID: 1000470<br>
	 * Message: $s1�l���A�[�N���C�W�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ARCHMAGES_CONGRATULATIONS;

	/**
	 * ID: 1000471<br>
	 * Message: $s1�l���\�E�� �e�C�J�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOULTAKERS_CONGRATULATIONS;

	/**
	 * ID: 1000472<br>
	 * Message: $s1�l���A���J�i ���[�h�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ARCANA_LORDS_CONGRATULATIONS;

	/**
	 * ID: 1000473<br>
	 * Message: $s1�l���J�[�f�B�i���̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_CARDINALS_CONGRATULATIONS;

	/**
	 * ID: 1000474<br>
	 * Message: $s1�l���n�C�G���t�@���g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_HIEROPHANTS_CONGRATULATIONS;

	/**
	 * ID: 1000475<br>
	 * Message: $s1�l���G���@�X �e���v���[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_EVAS_TEMPLARS_CONGRATULATIONS;

	/**
	 * ID: 1000476<br>
	 * Message: $s1�l���\�[�h �~���[�Y�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SWORD_MUSES_CONGRATULATIONS;

	/**
	 * ID: 1000477<br>
	 * Message: $s1�l���E�B���h ���C�_�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_WIND_RIDERS_CONGRATULATIONS;

	/**
	 * ID: 1000478<br>
	 * Message: $s1�l�����[�����C�g �Z���e�B�l���̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MOONLIGHT_SENTINELS_CONGRATULATIONS;

	/**
	 * ID: 1000479<br>
	 * Message: $s1�l���~�X�e�B�b�N �~���[�Y�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MYSTIC_MUSES_CONGRATULATIONS;

	/**
	 * ID: 1000480<br>
	 * Message: $s1�l���G�������^�� �}�X�^�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ELEMENTAL_MASTERS_CONGRATULATIONS;

	/**
	 * ID: 1000481<br>
	 * Message: $s1�l���G���@�X �Z�C���g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_EVAS_SAINTS_CONGRATULATIONS;

	/**
	 * ID: 1000482<br>
	 * Message: $s1�l���V���G�� �e���v���[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_TEMPLARS_CONGRATULATIONS;

	/**
	 * ID: 1000483<br>
	 * Message: $s1�l���X�y�N�g���� �_���T�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_DANCERS_CONGRATULATIONS;

	/**
	 * ID: 1000484<br>
	 * Message: $s1�l���S�[�X�g �n���^�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_HUNTERS_CONGRATULATIONS;

	/**
	 * ID: 1000485<br>
	 * Message: $s1�l���S�[�X�g �Z���e�B�l���̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_SENTINELS_CONGRATULATIONS;

	/**
	 * ID: 1000486<br>
	 * Message: $s1�l���X�g�[�� �X�N���[�}�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_STORM_SCREAMERS_CONGRATULATIONS;

	/**
	 * ID: 1000487<br>
	 * Message: $s1�l���X�y�N�g���� �}�X�^�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_MASTERS_CONGRATULATIONS;

	/**
	 * ID: 1000488<br>
	 * Message: $s1�l���V���G�� �Z�C���g�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_SAINTS_CONGRATULATIONS;

	/**
	 * ID: 1000489<br>
	 * Message: $s1�l���^�C�^���̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_TITANS_CONGRATULATIONS;

	/**
	 * ID: 1000490<br>
	 * Message: $s1�l���O�����h �J�o�^���̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GRAND_KHAVATARIS_CONGRATULATIONS;

	/**
	 * ID: 1000491<br>
	 * Message: $s1�l���h�~�l�[�^�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOMINATORS_CONGRATULATIONS;

	/**
	 * ID: 1000492<br>
	 * Message: $s1�l���h�D�[�� �N���C���[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMCRYERS_CONGRATULATIONS;

	/**
	 * ID: 1000493<br>
	 * Message: $s1�l���t�H�[�`���� �V�[�J�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_FORTUNE_SEEKERS_CONGRATULATIONS;

	/**
	 * ID: 1000494<br>
	 * Message: $s1�l���}�G�X�g���̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MAESTROS_CONGRATULATIONS;

	/**
	 * ID: 1000495<br>
	 * Message: ���o�^
	 */
	public static final NpcStringId UNREGISTERED;

	/**
	 * ID: 1000496<br>
	 * Message: $s1�l���h�D�[�� �u�����K�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMBRINGERS_CONGRATULATIONS;

	/**
	 * ID: 1000497<br>
	 * Message: $s1�l���\�E�� �n�E���h�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOUL_HOUNDS_CONGRATULATIONS;

	/**
	 * ID: 1000499<br>
	 * Message: $s1�l���g���b�N�X�^�[�̉p�Y�ɂȂ�܂����B���߂łƂ��������܂��B
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_TRICKSTERS_CONGRATULATIONS;

	/**
	 * ID: 1000500<br>
	 * Message: ��������_���֓��邱�Ƃ��ł��܂��B
	 */
	public static final NpcStringId YOU_MAY_NOW_ENTER_THE_SEPULCHER;

	/**
	 * ID: 1000501<br>
	 * Message: ��_���ɓ���ɂ́A�l���_�̐Α��Ɏ���悹�܂��B
	 */
	public static final NpcStringId IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER;

	/**
	 * ID: 1000502<br>
	 * Message: ���C�o�������̑O�ɖW�Q�����X�^�[���o�������܂����B
	 */
	public static final NpcStringId THE_MONSTERS_HAVE_SPAWNED;

	/**
	 * ID: 1000503<br>
	 * Message: �����Ă��������Ă��肪�Ƃ��������܂��B
	 */
	public static final NpcStringId THANK_YOU_FOR_SAVING_ME;

	/**
	 * ID: 1000504<br>
	 * Message: $s1�l����
	 */
	public static final NpcStringId FEWER_THAN_S2;

	/**
	 * ID: 1000505<br>
	 * Message: $s1�l�ȏ�
	 */
	public static final NpcStringId MORE_THAN_S2;

	/**
	 * ID: 1000506<br>
	 * Message: �|�C���g
	 */
	public static final NpcStringId POINT;

	/**
	 * ID: 1000507<br>
	 * Message: ����
	 */
	public static final NpcStringId COMPETITION;

	/**
	 * ID: 1000508<br>
	 * Message: ����L��
	 */
	public static final NpcStringId SEAL_VALIDATION;

	/**
	 * ID: 1000509<br>
	 * Message: ����
	 */
	public static final NpcStringId PREPARATION;

	/**
	 * ID: 1000512<br>
	 * Message: �����L
	 */
	public static final NpcStringId NO_OWNER;

	/**
	 * ID: 1000513<br>
	 * Message: ������$s1�l�ɂ͊댯�ł��B���߂肭�������B
	 */
	public static final NpcStringId THIS_PLACE_IS_DANGEROUS_S1_PLEASE_TURN_BACK;

	/**
	 * ID: 1000514<br>
	 * Message: ���Ȃ閰���W����̂͒N���B
	 */
	public static final NpcStringId WHO_DISTURBS_MY_SACRED_SLEEP;

	/**
	 * ID: 1000515<br>
	 * Message: ���@����A���邪�����B�����͉�X�̈����̒n���B
	 */
	public static final NpcStringId BEGONE_THIEF_LET_OUR_BONES_REST_IN_PEACE;

	/**
	 * ID: 1000516<br>
	 * Message: �w�X�g�D�C������A��X�ɍ\��Ȃ��ł���B
	 */
	public static final NpcStringId _LEAVE_US_BE_HESTUI_SCUM;

	/**
	 * ID: 1000517<br>
	 * Message: �J�J�C��A�y�ɂ͖���Ȃ����낤�B
	 */
	public static final NpcStringId THIEVING_KAKAI_MAY_BLOODBUGS_GNAW_YOU_IN_YOUR_SLEEP;

	/**
	 * ID: 1000518<br>
	 * Message: �g���x���[�Y �g�[�N��
	 */
	public static final NpcStringId NEWBIE_TRAVEL_TOKEN;

	/**
	 * ID: 1000519<br>
	 * Message: ���̎x�z�҂ł��邱�̎��ɂ����Ē��ނƂ����̂��B $s1�I
	 */
	public static final NpcStringId ARROGANT_FOOL_YOU_DARE_TO_CHALLENGE_ME_THE_RULER_OF_FLAMES_HERE_IS_YOUR_REWARD;

	/**
	 * ID: 1000520<br>
	 * Message: $s1�I���̒��x�̗͂Ŏ���|����Ƃł��v�������I
	 */
	public static final NpcStringId S1_YOU_CANNOT_HOPE_TO_DEFEAT_ME_WITH_YOUR_MEAGER_STRENGTH;

	/**
	 * ID: 1000521<br>
	 * Message: �_�������������Ȃ�����$s1���O���������Ƃ����̂��I�΂킹��ȁI
	 */
	public static final NpcStringId NOT_EVEN_THE_GODS_THEMSELVES_COULD_TOUCH_ME_BUT_YOU_S1_YOU_DARE_CHALLENGE_ME_IGNORANT_MORTAL;

	/**
	 * ID: 1000522<br>
	 * Message: �����̃��N�C�G��
	 */
	public static final NpcStringId REQUIEM_OF_HATRED;

	/**
	 * ID: 1000523<br>
	 * Message: ����̃t�[�K
	 */
	public static final NpcStringId FUGUE_OF_JUBILATION;

	/**
	 * ID: 1000524<br>
	 * Message: �����̃g�b�J�[�^
	 */
	public static final NpcStringId FRENETIC_TOCCATA;

	/**
	 * ID: 1000525<br>
	 * Message: ���f�̃}�Y���J
	 */
	public static final NpcStringId HYPNOTIC_MAZURKA;

	/**
	 * ID: 1000526<br>
	 * Message: �ߒQ�̃R�[����
	 */
	public static final NpcStringId MOURNFUL_CHORALE_PRELUDE;

	/**
	 * ID: 1000527<br>
	 * Message: �ǓƂ̃����h
	 */
	public static final NpcStringId RONDO_OF_SOLITUDE;

	/**
	 * ID: 1000528<br>
	 * Message: �I�����s�A�[�h �g�[�N��
	 */
	public static final NpcStringId OLYMPIAD_TOKEN;

	/**
	 * ID: 1001000<br>
	 * Message: �A�f������
	 */
	public static final NpcStringId THE_KINGDOM_OF_ADEN;

	/**
	 * ID: 1001100<br>
	 * Message: �G�����A����
	 */
	public static final NpcStringId THE_KINGDOM_OF_ELMORE;

	/**
	 * ID: 1010001<br>
	 * Message: �b���铇�̑�
	 */
	public static final NpcStringId TALKING_ISLAND_VILLAGE;

	/**
	 * ID: 1010002<br>
	 * Message: �G���t��
	 */
	public static final NpcStringId THE_ELVEN_VILLAGE;

	/**
	 * ID: 1010003<br>
	 * Message: �_�[�N�G���t��
	 */
	public static final NpcStringId THE_DARK_ELF_VILLAGE;

	/**
	 * ID: 1010004<br>
	 * Message: �O���[�f�B����
	 */
	public static final NpcStringId THE_VILLAGE_OF_GLUDIN;

	/**
	 * ID: 1010005<br>
	 * Message: �O���[�f�B�I��̑�
	 */
	public static final NpcStringId THE_TOWN_OF_GLUDIO;

	/**
	 * ID: 1010006<br>
	 * Message: �f�B�I����̑�
	 */
	public static final NpcStringId THE_TOWN_OF_DION;

	/**
	 * ID: 1010007<br>
	 * Message: �M������̑�
	 */
	public static final NpcStringId THE_TOWN_OF_GIRAN;

	/**
	 * ID: 1010008<br>
	 * Message: �I�[�N��
	 */
	public static final NpcStringId ORC_VILLAGE;

	/**
	 * ID: 1010009<br>
	 * Message: �h���[�t��
	 */
	public static final NpcStringId DWARVEN_VILLAGE;

	/**
	 * ID: 1010010<br>
	 * Message: �_�[�N�G���t�̐X�̓암
	 */
	public static final NpcStringId THE_SOUTHERN_PART_OF_THE_DARK_FOREST;

	/**
	 * ID: 1010011<br>
	 * Message: �k���C��
	 */
	public static final NpcStringId THE_NORTHEAST_COAST;

	/**
	 * ID: 1010012<br>
	 * Message: �r�n�̓쑤����
	 */
	public static final NpcStringId THE_SOUTHERN_ENTRANCE_TO_THE_WASTELANDSS;

	/**
	 * ID: 1010013<br>
	 * Message: �I�[������̑�
	 */
	public static final NpcStringId TOWN_OF_OREN;

	/**
	 * ID: 1010014<br>
	 * Message: �ۉ�̓�
	 */
	public static final NpcStringId IVORY_TOWER;

	/**
	 * ID: 1010015<br>
	 * Message: 1�K���r�[
	 */
	public static final NpcStringId N1ST_FLOOR_LOBBY;

	/**
	 * ID: 1010016<br>
	 * Message: �n��1�K���X�X
	 */
	public static final NpcStringId UNDERGROUND_SHOPPING_AREA;

	/**
	 * ID: 1010017<br>
	 * Message: 2�K�q���[�}�� �E�B�U�[�h �M���h
	 */
	public static final NpcStringId N2ND_FLOOR_HUMAN_WIZARD_GUILD;

	/**
	 * ID: 1010018<br>
	 * Message: 3�K�G������ �E�B�U�[�h �M���h
	 */
	public static final NpcStringId N3RD_FLOOR_ELVEN_WIZARD_GUILD;

	/**
	 * ID: 1010019<br>
	 * Message: 4�K�_�[�N �E�B�U�[�h �M���h
	 */
	public static final NpcStringId N4TH_FLOOR_DARK_WIZARD_GUILD;

	/**
	 * ID: 1010020<br>
	 * Message: �t�̑�
	 */
	public static final NpcStringId HUNTERS_VILLAGE;

	/**
	 * ID: 1010021<br>
	 * Message: �M�����`
	 */
	public static final NpcStringId GIRAN_HARBOR;

	/**
	 * ID: 1010022<br>
	 * Message: �n�[�f�B���̎��m
	 */
	public static final NpcStringId HARDINS_PRIVATE_ACADEMY;

	/**
	 * ID: 1010023<br>
	 * Message: �A�f����̑�
	 */
	public static final NpcStringId TOWN_OF_ADEN;

	/**
	 * ID: 1010024<br>
	 * Message: ���̍L��
	 */
	public static final NpcStringId VILLAGE_SQUARE;

	/**
	 * ID: 1010025<br>
	 * Message: �k�����
	 */
	public static final NpcStringId NORTH_GATE_ENTRANCE;

	/**
	 * ID: 1010026<br>
	 * Message: �������
	 */
	public static final NpcStringId EAST_GATE_ENTRANCE;

	/**
	 * ID: 1010027<br>
	 * Message: �������
	 */
	public static final NpcStringId WEST_GATE_ENTRANCE;

	/**
	 * ID: 1010028<br>
	 * Message: ������
	 */
	public static final NpcStringId SOUTH_GATE_ENTRANCE;

	/**
	 * ID: 1010029<br>
	 * Message: �g�D���b�N �I�[�N�̖�c�n�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_TUREK_ORC_CAMP;

	/**
	 * ID: 1010030<br>
	 * Message: �Y���ꂽ�_�a�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010031<br>
	 * Message: �r�n�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_WASTELANDS;

	/**
	 * ID: 1010032<br>
	 * Message: ���Ă�ꂽ�I�c�n�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_ABANDONED_CAMP;

	/**
	 * ID: 1010033<br>
	 * Message: �N���}���n�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_CRUMA_MARSHLANDS;

	/**
	 * ID: 1010034<br>
	 * Message: ���Y��̓���
	 */
	public static final NpcStringId ENTRANCE_TO_EXECUTION_GROUNDS;

	/**
	 * ID: 1010035<br>
	 * Message: �p���`�U���̃A�W�g�ւ̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FORTRESS_OF_RESISTANCE;

	/**
	 * ID: 1010036<br>
	 * Message: �t���[������
	 */
	public static final NpcStringId ENTRANCE_TO_FLORAN_VILLAGE;

	/**
	 * ID: 1010037<br>
	 * Message: �����n��
	 */
	public static final NpcStringId NEUTRAL_ZONE;

	/**
	 * ID: 1010038<br>
	 * Message: �M�������̓�
	 */
	public static final NpcStringId WESTERN_ROAD_OF_GIRAN;

	/**
	 * ID: 1010039<br>
	 * Message: �O���[�f�B�������̓�
	 */
	public static final NpcStringId EASTERN_ROAD_OF_GLUDIN_VILLAGE;

	/**
	 * ID: 1010041<br>
	 * Message: �N���}�̓��̓���
	 */
	public static final NpcStringId ENTRANCE_TO_CRUMA_TOWER;

	/**
	 * ID: 1010042<br>
	 * Message: ���̉�L
	 */
	public static final NpcStringId DEATH_PASS;

	/**
	 * ID: 1010043<br>
	 * Message: ���n�іk��
	 */
	public static final NpcStringId NORTHERN_PART_OF_THE_MARSHLANDS;

	/**
	 * ID: 1010044<br>
	 * Message: �����n�іk����
	 */
	public static final NpcStringId NORTHEAST_OF_THE_NEUTRAL_ZONE;

	/**
	 * ID: 1010045<br>
	 * Message: �s�ł̍�������
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_CENTRAL_REGION;

	/**
	 * ID: 1010046<br>
	 * Message: �s�ł̍����암
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_SOUTHERN_REGION;

	/**
	 * ID: 1010047<br>
	 * Message: �s�ł̍����쓌��
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_SOUTHEAST_REGION;

	/**
	 * ID: 1010048<br>
	 * Message: ���������
	 */
	public static final NpcStringId FROZEN_WATERFALL;

	/**
	 * ID: 1010049<br>
	 * Message: ����s�s�n�C�l�X
	 */
	public static final NpcStringId HEINE;

	/**
	 * ID: 1010050<br>
	 * Message: �����̓��F1�K
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_1ST_FLOOR;

	/**
	 * ID: 1010051<br>
	 * Message: �����̓��F5�K
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_5TH_FLOOR;

	/**
	 * ID: 1010052<br>
	 * Message: �����̓��F10�K
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_10TH_FLOOR;

	/**
	 * ID: 1010053<br>
	 * Message: �R���V�A��
	 */
	public static final NpcStringId COLISEUM;

	/**
	 * ID: 1010054<br>
	 * Message: �����X�^�[ ���[�X��
	 */
	public static final NpcStringId MONSTER_DERBY;

	/**
	 * ID: 1010055<br>
	 * Message: ����������t��
	 */
	public static final NpcStringId NEAR_THE_FRONTIER_POST;

	/**
	 * ID: 1010056<br>
	 * Message: �E�q�̊C�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_SEA_OF_SPORES;

	/**
	 * ID: 1010057<br>
	 * Message: �ߋ��̐��
	 */
	public static final NpcStringId AN_OLD_BATTLEFIELD;

	/**
	 * ID: 1010058<br>
	 * Message: �t�F�A���[�̒J�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_ENCHANTED_VALLEY;

	/**
	 * ID: 1010059<br>
	 * Message: �����̓��̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_TOWER_OF_INSOLENCE;

	/**
	 * ID: 1010060<br>
	 * Message: �Ή��̏�
	 */
	public static final NpcStringId BLAZING_SWAMP;

	/**
	 * ID: 1010061<br>
	 * Message: ������n�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_CEMETERY;

	/**
	 * ID: 1010062<br>
	 * Message: ���l�����̓��A����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_GIANTS_CAVE;

	/**
	 * ID: 1010063<br>
	 * Message: ���̐X�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FOREST_OF_MIRRORS;

	/**
	 * ID: 1010064<br>
	 * Message: �V�[�����̕���̓���
	 */
	public static final NpcStringId THE_FORBIDDEN_GATEWAY;

	/**
	 * ID: 1010066<br>
	 * Message: �^�m�[�� �V���m�X�̖�c�n
	 */
	public static final NpcStringId ENTRANCE_TO_THE_TANOR_SILENOS_BARRACKS;

	/**
	 * ID: 1010067<br>
	 * Message: �h���S���o���[
	 */
	public static final NpcStringId DRAGON_VALLEY;

	/**
	 * ID: 1010068<br>
	 * Message: �t���̐_����
	 */
	public static final NpcStringId ORACLE_OF_DAWN;

	/**
	 * ID: 1010069<br>
	 * Message: �����̐_����
	 */
	public static final NpcStringId ORACLE_OF_DUSK;

	/**
	 * ID: 1010070<br>
	 * Message: ���т̃l�N���|���X
	 */
	public static final NpcStringId NECROPOLIS_OF_SACRIFICE;

	/**
	 * ID: 1010071<br>
	 * Message: ����҂̃l�N���|���X
	 */
	public static final NpcStringId THE_PILGRIMS_NECROPOLIS;

	/**
	 * ID: 1010072<br>
	 * Message: �Q�q�҂̃l�N���|���X
	 */
	public static final NpcStringId NECROPOLIS_OF_WORSHIP;

	/**
	 * ID: 1010073<br>
	 * Message: ��m�̃l�N���|���X
	 */
	public static final NpcStringId THE_PATRIOTS_NECROPOLIS;

	/**
	 * ID: 1010074<br>
	 * Message: ��s�҂̃l�N���|���X
	 */
	public static final NpcStringId NECROPOLIS_OF_DEVOTION;

	/**
	 * ID: 1010075<br>
	 * Message: �}���҂̃l�N���|���X
	 */
	public static final NpcStringId NECROPOLIS_OF_MARTYRDOM;

	/**
	 * ID: 1010076<br>
	 * Message: �g�k�̃l�N���|���X
	 */
	public static final NpcStringId THE_DISCIPLES_NECROPOLIS;

	/**
	 * ID: 1010077<br>
	 * Message: ���҂̃l�N���|���X
	 */
	public static final NpcStringId THE_SAINTS_NECROPOLIS;

	/**
	 * ID: 1010078<br>
	 * Message: �ْ[�҂̃J�^�R��
	 */
	public static final NpcStringId THE_CATACOMB_OF_THE_HERETIC;

	/**
	 * ID: 1010079<br>
	 * Message: ����̃J�^�R��
	 */
	public static final NpcStringId CATACOMB_OF_THE_BRANDED;

	/**
	 * ID: 1010080<br>
	 * Message: �ً��k�̃J�^�R��
	 */
	public static final NpcStringId CATACOMB_OF_THE_APOSTATE;

	/**
	 * ID: 1010081<br>
	 * Message: �����̃J�^�R��
	 */
	public static final NpcStringId CATACOMB_OF_THE_WITCH;

	/**
	 * ID: 1010082<br>
	 * Message: �����̃J�^�R��
	 */
	public static final NpcStringId CATACOMB_OF_DARK_OMENS;

	/**
	 * ID: 1010083<br>
	 * Message: ���E�̃J�^�R��
	 */
	public static final NpcStringId CATACOMB_OF_THE_FORBIDDEN_PATH;

	/**
	 * ID: 1010084<br>
	 * Message: �ߒQ�̔p�Г���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_RUINS_OF_AGONY;

	/**
	 * ID: 1010085<br>
	 * Message: ��]�̔p�Г���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_RUINS_OF_DESPAIR;

	/**
	 * ID: 1010086<br>
	 * Message: �A���̑�����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ANT_NEST;

	/**
	 * ID: 1010087<br>
	 * Message: �f�B�I���암
	 */
	public static final NpcStringId SOUTHERN_DION;

	/**
	 * ID: 1010088<br>
	 * Message: �h���S���o���[����
	 */
	public static final NpcStringId ENTRANCE_TO_DRAGON_VALLEY;

	/**
	 * ID: 1010089<br>
	 * Message: �Î�̑���
	 */
	public static final NpcStringId FIELD_OF_SILENCE;

	/**
	 * ID: 1010090<br>
	 * Message: �����̑���
	 */
	public static final NpcStringId FIELD_OF_WHISPERS;

	/**
	 * ID: 1010091<br>
	 * Message: �N���R�_�C�� �A�C�����h����
	 */
	public static final NpcStringId ENTRANCE_TO_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010092<br>
	 * Message: �I�[�����암����
	 */
	public static final NpcStringId SOUTHERN_PLAIN_OF_OREN;

	/**
	 * ID: 1010093<br>
	 * Message: �R���̑��A����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_BANDIT_STRONGHOLD;

	/**
	 * ID: 1010094<br>
	 * Message: ���̋u
	 */
	public static final NpcStringId WINDY_HILL;

	/**
	 * ID: 1010095<br>
	 * Message: �g�D���b�N �I�[�N�̖�c�n
	 */
	public static final NpcStringId ORC_BARRACKS;

	/**
	 * ID: 1010096<br>
	 * Message: �t�F�������̏W��
	 */
	public static final NpcStringId FELLMERE_HARVESTING_GROUNDS;

	/**
	 * ID: 1010097<br>
	 * Message: �ߒQ�̔p��
	 */
	public static final NpcStringId RUINS_OF_AGONY;

	/**
	 * ID: 1010098<br>
	 * Message: ���Ă�ꂽ�I�c�n
	 */
	public static final NpcStringId ABANDONED_CAMP;

	/**
	 * ID: 1010099<br>
	 * Message: �Ԃ���̔���
	 */
	public static final NpcStringId RED_ROCK_RIDGE;

	/**
	 * ID: 1010100<br>
	 * Message: �����N ���U�[�h�}�������n
	 */
	public static final NpcStringId LANGK_LIZARDMAN_DWELLINGS;

	/**
	 * ID: 1010101<br>
	 * Message: ��]�̔p��
	 */
	public static final NpcStringId RUINS_OF_DESPAIR;

	/**
	 * ID: 1010102<br>
	 * Message: �E�B���_ �E�b�h�̑���
	 */
	public static final NpcStringId WINDAWOOD_MANOR;

	/**
	 * ID: 1010103<br>
	 * Message: �r�n�̖k������
	 */
	public static final NpcStringId NORTHERN_PATHWAY_TO_THE_WASTELANDS;

	/**
	 * ID: 1010104<br>
	 * Message: �r�n�̐�������
	 */
	public static final NpcStringId WESTERN_PATHWAY_TO_THE_WASTELANDS;

	/**
	 * ID: 1010105<br>
	 * Message: �r�n�̓쑤����
	 */
	public static final NpcStringId SOUTHERN_PATHWAY_TO_THE_WASTELANDS;

	/**
	 * ID: 1010106<br>
	 * Message: �Y���ꂽ�_�a
	 */
	public static final NpcStringId FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010107<br>
	 * Message: �A���̑��̓쑤����
	 */
	public static final NpcStringId SOUTH_ENTRANCE_OF_ANT_NEST;

	/**
	 * ID: 1010108<br>
	 * Message: �A���̑��̓�������
	 */
	public static final NpcStringId EAST_ENTRANCE_OF_ANT_NEST;

	/**
	 * ID: 1010109<br>
	 * Message: �A���̑��̐�������
	 */
	public static final NpcStringId WEST_ENTRANCE_OF_ANT_NEST;

	/**
	 * ID: 1010110<br>
	 * Message: �N���}���n
	 */
	public static final NpcStringId CRUMA_MARSHLAND;

	/**
	 * ID: 1010111<br>
	 * Message: �f�B�I���q���n
	 */
	public static final NpcStringId PLAINS_OF_DION;

	/**
	 * ID: 1010112<br>
	 * Message: �r�[�n�C��
	 */
	public static final NpcStringId BEE_HIVE;

	/**
	 * ID: 1010113<br>
	 * Message: �p���`�U���̃A�W�g
	 */
	public static final NpcStringId FORTRESS_OF_RESISTANCE;

	/**
	 * ID: 1010114<br>
	 * Message: ���Y��
	 */
	public static final NpcStringId EXECUTION_GROUNDS;

	/**
	 * ID: 1010115<br>
	 * Message: �^�m�[�����J
	 */
	public static final NpcStringId TANOR_CANYON;

	/**
	 * ID: 1010116<br>
	 * Message: �N���}�̓�
	 */
	public static final NpcStringId CRUMA_TOWER;

	/**
	 * ID: 1010117<br>
	 * Message: �h���S���o���[�̎O���H
	 */
	public static final NpcStringId THREE_WAY_CROSSROADS_AT_DRAGON_VALLEY;

	/**
	 * ID: 1010118<br>
	 * Message: �u���J�̑��A
	 */
	public static final NpcStringId BREKAS_STRONGHOLD;

	/**
	 * ID: 1010119<br>
	 * Message: �S���R���̉ԉ�
	 */
	public static final NpcStringId GORGON_FLOWER_GARDEN;

	/**
	 * ID: 1010120<br>
	 * Message: �h���S���o���[�̃_���W����
	 */
	public static final NpcStringId ANTHARASS_LAIR;

	/**
	 * ID: 1010121<br>
	 * Message: �E�q�̊C
	 */
	public static final NpcStringId SEA_OF_SPORES;

	/**
	 * ID: 1010122<br>
	 * Message: ���@�҂̐X
	 */
	public static final NpcStringId OUTLAW_FOREST;

	/**
	 * ID: 1010123<br>
	 * Message: �����̐X�Əۉ�̓�
	 */
	public static final NpcStringId FOREST_OF_EVIL_AND_THE_IVORY_TOWER;

	/**
	 * ID: 1010124<br>
	 * Message: �e�B�}�b�N �A�E�g�|�X�g
	 */
	public static final NpcStringId TIMAK_OUTPOST;

	/**
	 * ID: 1010125<br>
	 * Message: �I�[�����啽��
	 */
	public static final NpcStringId GREAT_PLAINS_OF_OREN;

	/**
	 * ID: 1010126<br>
	 * Message: �B���p�t�̏���
	 */
	public static final NpcStringId ALCHEMISTS_HUT;

	/**
	 * ID: 1010127<br>
	 * Message: �ߋ��̐��
	 */
	public static final NpcStringId ANCIENT_BATTLEGROUND;

	/**
	 * ID: 1010128<br>
	 * Message: �t�F�A���[�̒J�̖k������
	 */
	public static final NpcStringId NORTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY;

	/**
	 * ID: 1010129<br>
	 * Message: �t�F�A���[�̒J�̓쑤����
	 */
	public static final NpcStringId SOUTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY;

	/**
	 * ID: 1010130<br>
	 * Message: �t�̌k�J
	 */
	public static final NpcStringId HUNTERS_VALLEY;

	/**
	 * ID: 1010131<br>
	 * Message: �Ή��̏��̐�������
	 */
	public static final NpcStringId WESTERN_ENTRANCE_OF_BLAZING_SWAMP;

	/**
	 * ID: 1010132<br>
	 * Message: �Ή��̏��̓�������
	 */
	public static final NpcStringId EASTERN_ENTRANCE_OF_BLAZING_SWAMP;

	/**
	 * ID: 1010133<br>
	 * Message: �h���̕���
	 */
	public static final NpcStringId PLAINS_OF_GLORY;

	/**
	 * ID: 1010134<br>
	 * Message: ����̕���
	 */
	public static final NpcStringId WAR_TORN_PLAINS;

	/**
	 * ID: 1010135<br>
	 * Message: ���̐X�̖k��������
	 */
	public static final NpcStringId NORTHWESTERN_PASSAGE_TO_THE_FOREST_OF_MIRRORS;

	/**
	 * ID: 1010136<br>
	 * Message: �A���w����̑O
	 */
	public static final NpcStringId THE_FRONT_OF_ANGHEL_WATERFALL;

	/**
	 * ID: 1010137<br>
	 * Message: �j�󂳂ꂽ��Ԃ̓쑤����
	 */
	public static final NpcStringId SOUTH_ENTRANCE_OF_DEVASTATED_CASTLE;

	/**
	 * ID: 1010138<br>
	 * Message: �j�󂳂ꂽ��Ԃ̖k��
	 */
	public static final NpcStringId NORTH_ENTRANCE_OF_DEVASTATED_CASTLE;

	/**
	 * ID: 1010139<br>
	 * Message: ������n�̖k������
	 */
	public static final NpcStringId NORTH_ENTRANCE_OF_THE_CEMETERY;

	/**
	 * ID: 1010140<br>
	 * Message: ������n�̓쑤����
	 */
	public static final NpcStringId SOUTH_ENTRANCE_OF_THE_CEMETERY;

	/**
	 * ID: 1010141<br>
	 * Message: ������n�̐�������
	 */
	public static final NpcStringId WEST_ENTRANCE_OF_THE_CEMETERY;

	/**
	 * ID: 1010142<br>
	 * Message: �V�[�����̕���̓���
	 */
	public static final NpcStringId ENTRANCE_OF_THE_FORBIDDEN_GATEWAY;

	/**
	 * ID: 1010143<br>
	 * Message: �Y�p�̕���
	 */
	public static final NpcStringId FORSAKEN_PLAINS;

	/**
	 * ID: 1010144<br>
	 * Message: �����̓�
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE;

	/**
	 * ID: 1010145<br>
	 * Message: ���l�����̓��A
	 */
	public static final NpcStringId THE_GIANTS_CAVE;

	/**
	 * ID: 1010146<br>
	 * Message: �Î�̑����̖k���n��
	 */
	public static final NpcStringId NORTHERN_PART_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010147<br>
	 * Message: �Î�̑����̐����n��
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010148<br>
	 * Message: �����̑����̓����n��
	 */
	public static final NpcStringId EASTERN_PART_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010149<br>
	 * Message: �����̑����̐����n��
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_FIELD_OF_WHISPERS;

	/**
	 * ID: 1010150<br>
	 * Message: �N���R�_�C�� �A�C�����h
	 */
	public static final NpcStringId ALLIGATOR_ISLAND;

	/**
	 * ID: 1010151<br>
	 * Message: �N���R�_�C���̔��l
	 */
	public static final NpcStringId ALLIGATOR_BEACH;

	/**
	 * ID: 1010152<br>
	 * Message: �����̓�
	 */
	public static final NpcStringId DEVILS_ISLE;

	/**
	 * ID: 1010153<br>
	 * Message: �G���@�̐����뉀
	 */
	public static final NpcStringId GARDEN_OF_EVA;

	/**
	 * ID: 1010154<br>
	 * Message: �b���铇
	 */
	public static final NpcStringId TALKING_ISLAND;

	/**
	 * ID: 1010155<br>
	 * Message: �G���t��
	 */
	public static final NpcStringId ELVEN_VILLAGE;

	/**
	 * ID: 1010156<br>
	 * Message: �_�[�N�G���t��
	 */
	public static final NpcStringId DARK_ELF_VILLAGE;

	/**
	 * ID: 1010159<br>
	 * Message: �A�C���X�ΓW�]��
	 */
	public static final NpcStringId SCENIC_DECK_OF_IRIS_LAKE;

	/**
	 * ID: 1010160<br>
	 * Message: ���l���̍Ւd
	 */
	public static final NpcStringId ALTAR_OF_RITES;

	/**
	 * ID: 1010161<br>
	 * Message: �_�[�N�G���t�̐X�A��
	 */
	public static final NpcStringId DARK_FOREST_WATERFALL;

	/**
	 * ID: 1010162<br>
	 * Message: �����n�т̎O���H
	 */
	public static final NpcStringId THREE_WAY_CROSSROADS_OF_THE_NEUTRAL_ZONE;

	/**
	 * ID: 1010163<br>
	 * Message: �_�[�N�G���t�̐X
	 */
	public static final NpcStringId DARK_FOREST;

	/**
	 * ID: 1010164<br>
	 * Message: ���n��
	 */
	public static final NpcStringId SWAMPLAND;

	/**
	 * ID: 1010165<br>
	 * Message: ������̋u
	 */
	public static final NpcStringId BLACK_ROCK_HILL;

	/**
	 * ID: 1010166<br>
	 * Message: �N���̑�
	 */
	public static final NpcStringId SPIDER_NEST;

	/**
	 * ID: 1010167<br>
	 * Message: �G���t�̐X
	 */
	public static final NpcStringId ELVEN_FOREST;

	/**
	 * ID: 1010168<br>
	 * Message: �폟�L�O��
	 */
	public static final NpcStringId OBELISK_OF_VICTORY;

	/**
	 * ID: 1010169<br>
	 * Message: �b���铇�̖k��
	 */
	public static final NpcStringId NORTHERN_TERRITORY_OF_TALKING_ISLAND;

	/**
	 * ID: 1010170<br>
	 * Message: �b���铇�̓쑤
	 */
	public static final NpcStringId SOUTHERN_TERRITORY_OF_TALKING_ISLAND;

	/**
	 * ID: 1010171<br>
	 * Message: �����̗��D�n
	 */
	public static final NpcStringId EVIL_HUNTING_GROUNDS;

	/**
	 * ID: 1010172<br>
	 * Message: ���� ���U�[�h�}�����Ԓn
	 */
	public static final NpcStringId MAILLE_LIZARDMEN_BARRACKS;

	/**
	 * ID: 1010173<br>
	 * Message: �ߒQ�̔p�ЂɌ�������
	 */
	public static final NpcStringId RUINS_OF_AGONY_BEND;

	/**
	 * ID: 1010174<br>
	 * Message: ��]�̔p�ЂɌ�������
	 */
	public static final NpcStringId THE_ENTRANCE_TO_THE_RUINS_OF_DESPAIR;

	/**
	 * ID: 1010175<br>
	 * Message: ���Ԃ̋u
	 */
	public static final NpcStringId WINDMILL_HILL;

	/**
	 * ID: 1010177<br>
	 * Message: �t���[�����J��n
	 */
	public static final NpcStringId FLORAN_AGRICULTURAL_AREA;

	/**
	 * ID: 1010178<br>
	 * Message: �^�m�[�����J�̐���
	 */
	public static final NpcStringId WESTERN_TANOR_CANYON;

	/**
	 * ID: 1010179<br>
	 * Message: ���U�[�h �v���C��
	 */
	public static final NpcStringId PLAINS_OF_THE_LIZARDMEN;

	/**
	 * ID: 1010180<br>
	 * Message: �����̐X
	 */
	public static final NpcStringId FOREST_OF_EVIL;

	/**
	 * ID: 1010181<br>
	 * Message: �s�E�̑�n
	 */
	public static final NpcStringId FIELDS_OF_MASSACRE;

	/**
	 * ID: 1010182<br>
	 * Message: �T�C�����g �o���[
	 */
	public static final NpcStringId SILENT_VALLEY;

	/**
	 * ID: 1010183<br>
	 * Message: �s�ł̍����k���̖k��
	 */
	public static final NpcStringId NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION;

	/**
	 * ID: 1010184<br>
	 * Message: �s�ł̍����k���̓쑤
	 */
	public static final NpcStringId SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION;

	/**
	 * ID: 1010185<br>
	 * Message: �s�ł̍����암�̖k��
	 */
	public static final NpcStringId NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION;

	/**
	 * ID: 1010186<br>
	 * Message: �s�ł̍����암�̓쑤
	 */
	public static final NpcStringId SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION;

	/**
	 * ID: 1010187<br>
	 * Message: �z�R�n�ѐ���
	 */
	public static final NpcStringId WESTERN_MINING_ZONE;

	/**
	 * ID: 1010190<br>
	 * Message: �̂Ă�ꂽ�Y�z����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ABANDONED_COAL_MINES;

	/**
	 * ID: 1010191<br>
	 * Message: �~�X�����z�R����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_MITHRIL_MINES;

	/**
	 * ID: 1010192<br>
	 * Message: �j�󂳂ꂽ��Ԃ̐���
	 */
	public static final NpcStringId WEST_AREA_OF_THE_DEVASTATED_CASTLE;

	/**
	 * ID: 1010193<br>
	 * Message: �����̓�3�K
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_3RD_FLOOR;

	/**
	 * ID: 1010195<br>
	 * Message: �����̓�7�K
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_7TH_FLOOR;

	/**
	 * ID: 1010197<br>
	 * Message: �����̓�13�K
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_13TH_FLOOR;

	/**
	 * ID: 1010199<br>
	 * Message: �S�_�[�h��̑�
	 */
	public static final NpcStringId TOWN_OF_GODDARD;

	/**
	 * ID: 1010200<br>
	 * Message: ���E����̑�
	 */
	public static final NpcStringId RUNE_TOWNSHIP;

	/**
	 * ID: 1010201<br>
	 * Message: ���N�^�[����ɂ��͂��ł��ˁH�͂��A�������܂�܂����`
	 */
	public static final NpcStringId A_DELIVERY_FOR_MR_LECTOR_VERY_GOOD;

	/**
	 * ID: 1010202<br>
	 * Message: �ӂ��A������Ƌx�������Ɓ`
	 */
	public static final NpcStringId I_NEED_A_BREAK;

	/**
	 * ID: 1010203<br>
	 * Message: ����ɂ��́A���N�^�[����I�W���N�\������������������Ă܂��I
	 */
	public static final NpcStringId HELLO_MR_LECTOR_LONG_TIME_NO_SEE_MR_JACKSON;

	/**
	 * ID: 1010204<br>
	 * Message: ���������`
	 */
	public static final NpcStringId LULU;

	/**
	 * ID: 1010205<br>
	 * Message: ��̂ǂ��ɍs�����񂾂낤�H
	 */
	public static final NpcStringId WHERE_HAS_HE_GONE;

	/**
	 * ID: 1010206<br>
	 * Message: ���́A�E�B���_�E�b�h�l���������܂���ł������B
	 */
	public static final NpcStringId HAVE_YOU_SEEN_WINDAWOOD;

	/**
	 * ID: 1010207<br>
	 * Message: ��̂ǂ��ɂ���������񂾂낤�H
	 */
	public static final NpcStringId WHERE_DID_HE_GO;

	/**
	 * ID: 1010208<br>
	 * Message: ���E�������񂾂�͂�Ă����D�D�D
	 */
	public static final NpcStringId THE_MOTHER_TREE_IS_SLOWLY_DYING;

	/**
	 * ID: 1010209<br>
	 * Message: ���E�������C�ɂ�����@�͂Ȃ����ȁH
	 */
	public static final NpcStringId HOW_CAN_WE_SAVE_THE_MOTHER_TREE;

	/**
	 * ID: 1010210<br>
	 * Message: �����Ă����E���͔������D�D�D
	 */
	public static final NpcStringId THE_MOTHER_TREE_IS_ALWAYS_SO_GORGEOUS;

	/**
	 * ID: 1010211<br>
	 * Message: �~���e�B�G���l�I���ɌΔȂ̕��a�̂���񂱂Ƃ��I
	 */
	public static final NpcStringId LADY_MIRABEL_MAY_THE_PEACE_OF_THE_LAKE_BE_WITH_YOU;

	/**
	 * ID: 1010212<br>
	 * Message: �����l�ł��A���C���l�I
	 */
	public static final NpcStringId YOURE_A_HARD_WORKER_RAYLA;

	/**
	 * ID: 1010213<br>
	 * Message: �����l�ł��I
	 */
	public static final NpcStringId YOURE_A_HARD_WORKER;

	/**
	 * ID: 1010214<br>
	 * Message: ������ɂ͈ł̗�q���n�܂�܂��B�x���̎���ɂ߂Ȃ��ł��������ˁI
	 */
	public static final NpcStringId THE_MASS_OF_DARKNESS_WILL_START_IN_A_COUPLE_OF_DAYS_PAY_MORE_ATTENTION_TO_THE_GUARD;

	/**
	 * ID: 1010215<br>
	 * Message: �����A�g���b�R���������܂���ł������B
	 */
	public static final NpcStringId HAVE_YOU_SEEN_TOROCCO_TODAY;

	/**
	 * ID: 1010216<br>
	 * Message: ���������āA�g���b�R���������܂���ł������B
	 */
	public static final NpcStringId HAVE_YOU_SEEN_TOROCCO;

	/**
	 * ID: 1010217<br>
	 * Message: ���΂���������߁I�ǂ��ɉB�ꂽ�񂾁H
	 */
	public static final NpcStringId WHERE_IS_THAT_FOOL_HIDING;

	/**
	 * ID: 1010218<br>
	 * Message: �����A���ꂶ�Ⴀ�ЂƉ�肵�Ă݂悤���B
	 */
	public static final NpcStringId CARE_TO_GO_A_ROUND;

	/**
	 * ID: 1010219<br>
	 * Message: �K���^����I�~�I������I�悢������I
	 */
	public static final NpcStringId HAVE_A_NICE_DAY_MR_GARITA_AND_MION;

	/**
	 * ID: 1010220<br>
	 * Message: ���[�h����I�}�[�t��������I�G�A���[����I�����C�ł����B
	 */
	public static final NpcStringId MR_LID_MURDOC_AND_AIRY_HOW_ARE_YOU_DOING;

	/**
	 * ID: 1010221<br>
	 * Message: �������D�D�D�t�t�t�t�D�D�D���̂������ڂ��o�܂��ꂽ���Ƃ��킩�邩�B
	 */
	public static final NpcStringId A_BLACK_MOON_NOW_DO_YOU_UNDERSTAND_THAT_HE_HAS_OPENED_HIS_EYES;

	/**
	 * ID: 1010222<br>
	 * Message: ���̉_�������񂹂Ă��Ă��邻���ȁD�D�D�t�t�t�D�D�D�����ɂ͉J���~�邾�낤�D�D�D���̉J���ȁD�D�D
	 */
	public static final NpcStringId CLOUDS_OF_BLOOD_ARE_GATHERING_SOON_IT_WILL_START_TO_RAIN_THE_RAIN_OF_CRIMSON_BLOOD;

	/**
	 * ID: 1010223<br>
	 * Message: �����Ȍ�������ɂ��Ă��鍠�A�ł��ڂ��o�܂��Ƃ͂ȁD�D�D�t�t�t�D�D�D
	 */
	public static final NpcStringId WHILE_THE_FOOLISH_LIGHT_WAS_ASLEEP_THE_DARKNESS_WILL_AWAKEN_FIRST_UH_HUH_HUH;

	/**
	 * ID: 1010224<br>
	 * Message: �ł����[���̈ł��ƁH����Ȃ��̂������炱�̐��͏I��肾�ȁD�D�D�t�t�t�D�D�D
	 */
	public static final NpcStringId IT_IS_THE_DEEPEST_DARKNESS_WITH_ITS_ARRIVAL_THE_WORLD_WILL_SOON_DIE;

	/**
	 * ID: 1010225<br>
	 * Message: ���͂܂��V���Ȏn�܂���w���B�t�t�t�D�D�D�����ȁB
	 */
	public static final NpcStringId DEATH_IS_JUST_A_NEW_BEGINNING_HUHU_FEAR_NOT;

	/**
	 * ID: 1010226<br>
	 * Message: �����A���̏��_��I���̉��ꂫ�������̒����łŕ����s�������܂��I
	 */
	public static final NpcStringId AHH_BEAUTIFUL_GODDESS_OF_DEATH_COVER_OVER_THE_FILTH_OF_THIS_WORLD_WITH_YOUR_DARKNESS;

	/**
	 * ID: 1010227<br>
	 * Message: ���łɏ��_�̕������n�܂��Ă���B�t�t�t�D�D�D�U�R�̂��܂��炪���������Ƃ���Ŗ��ʂ��I
	 */
	public static final NpcStringId THE_GODDESSS_RESURRECTION_HAS_ALREADY_BEGUN_HUHU_INSIGNIFICANT_CREATURES_LIKE_YOU_CAN_DO_NOTHING;

	/**
	 * ID: 1010400<br>
	 * Message: �P���P���b�I����ȂƂ����$s1������Ȃ�āI�H
	 */
	public static final NpcStringId CROAK_CROAK_FOOD_LIKE_S1_IN_THIS_PLACE;

	/**
	 * ID: 1010401<br>
	 * Message: $s1�A������ˁI
	 */
	public static final NpcStringId S1_HOW_LUCKY_I_AM;

	/**
	 * ID: 1010402<br>
	 * Message: �ނ�ԈႦ�����Č�����A$s1�I
	 */
	public static final NpcStringId PRAY_THAT_YOU_CAUGHT_A_WRONG_FISH_S1;

	/**
	 * ID: 1010403<br>
	 * Message: �J�G�����Â������Ⴂ�����B
	 */
	public static final NpcStringId DO_YOU_KNOW_WHAT_A_FROG_TASTES_LIKE;

	/**
	 * ID: 1010404<br>
	 * Message: �J�G���̗͂������Ă�낤�I
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_A_FROG;

	/**
	 * ID: 1010405<br>
	 * Message: ����ň��ݍ���ł��I
	 */
	public static final NpcStringId I_WILL_SWALLOW_AT_A_MOUTHFUL;

	/**
	 * ID: 1010406<br>
	 * Message: �S�t�b�D�D�D���̃I���l�����ʂȂ�āI
	 */
	public static final NpcStringId UGH_NO_CHANCE_HOW_COULD_THIS_ELDER_PASS_AWAY_LIKE_THIS;

	/**
	 * ID: 1010407<br>
	 * Message: �Q�R�Q�R�I�J�G���E���I
	 */
	public static final NpcStringId CROAK_CROAK_A_FROG_IS_DYING;

	/**
	 * ID: 1010408<br>
	 * Message: �J�G���͔��������Ȃ��ł���I�����B
	 */
	public static final NpcStringId A_FROG_TASTES_BAD_YUCK;

	/**
	 * ID: 1010409<br>
	 * Message: ���Ⴀ���I$s1�A������̂��H
	 */
	public static final NpcStringId KAAK_S1_WHAT_ARE_YOU_DOING_NOW;

	/**
	 * ID: 1010410<br>
	 * Message: �ӂӂ��A$s1����̑̂ɏ�������Ȃ�āB�o��͂ł��Ă����ł��傤�ˁH
	 */
	public static final NpcStringId HUH_S1_YOU_PIERCED_INTO_THE_BODY_OF_THE_SPIRIT_WITH_A_NEEDLE_ARE_YOU_READY;

	/**
	 * ID: 1010411<br>
	 * Message: ����A$s1 ���Ȃ��������̂ˁB�ł��ˁA����ȍr���ۂ����҂Ɋ�ԏ��Ȃ�Ă��Ȃ����H
	 */
	public static final NpcStringId OOH_S1_THATS_YOU_BUT_NO_LADY_IS_PLEASED_WITH_THIS_SAVAGE_INVITATION;

	/**
	 * ID: 1010412<br>
	 * Message: ��������{�点����ˁI
	 */
	public static final NpcStringId YOU_MADE_ME_ANGRY;

	/**
	 * ID: 1010413<br>
	 * Message: ���̏��A�ǂ����Ă����̂���I�H
	 */
	public static final NpcStringId IT_IS_BUT_A_SCRATCH_IS_THAT_ALL_YOU_CAN_DO;

	/**
	 * ID: 1010414<br>
	 * Message: ���񂽂������ڂɂ��킹�Ă��I�������I
	 */
	public static final NpcStringId FEEL_MY_PAIN;

	/**
	 * ID: 1010415<br>
	 * Message: ���Ⴀ���I����������ˁA�o���Ă�����Ⴂ�I
	 */
	public static final NpcStringId ILL_GET_YOU_FOR_THIS;

	/**
	 * ID: 1010416<br>
	 * Message: ���Ȃ��̃G�T�͐H�ׂ�Ȃ��āA���̋������Ɍ����ӂ炵�Ă��I
	 */
	public static final NpcStringId I_WILL_TELL_FISH_NOT_TO_TAKE_YOUR_BAIT;

	/**
	 * ID: 1010417<br>
	 * Message: �������݂����Ȃ��ア����������߂�Ȃ�āD�D�D���������B
	 */
	public static final NpcStringId YOU_BOTHERED_SUCH_A_WEAK_SPIRITHUH_HUH;

	/**
	 * ID: 1010418<br>
	 * Message: �P�P�P�D�D�D$s1�D�D�D�H�ׂ邼�D�D�D�P�P�b�D�D�D
	 */
	public static final NpcStringId KE_KE_KES1IM_EATINGKE;

	/**
	 * ID: 1010419<br>
	 * Message: �O�����D�D�D���D�D�D$s1�D�D�D����߂���D�D�D���ǂ��D�D�D
	 */
	public static final NpcStringId KUHOOHS1ENMITYFISH;

	/**
	 * ID: 1010420<br>
	 * Message: $s1�H�P�z�b�D�D�D�O���D�D�D�O���b�D�D�D
	 */
	public static final NpcStringId S1_HUH_HUHHUH;

	/**
	 * ID: 1010421<br>
	 * Message: �N�P�P�P�P�I���N���I�X�s���I�A�^�A�A�A�A�A�b�N�I
	 */
	public static final NpcStringId KE_KE_KE_RAKUL_SPIN_EH_EH_EH;

	/**
	 * ID: 1010422<br>
	 * Message: �������I�p�v���I���I�������I�������I
	 */
	public static final NpcStringId AH_FAFURION_AH_AH;

	/**
	 * ID: 1010423<br>
	 * Message: ���N���I���N���I���@�@�N�D�D���D�D�I
	 */
	public static final NpcStringId RAKUL_RAKUL_RA_KUL;

	/**
	 * ID: 1010424<br>
	 * Message: �N�G�F�D�D�D���݁D�D�D���D�D�D
	 */
	public static final NpcStringId EHENMITYFISH;

	/**
	 * ID: 1010425<br>
	 * Message: �H��ꂽ���Ȃ��D�D�D�O�A�@
	 */
	public static final NpcStringId I_WONT_BE_EATEN_UPKAH_AH_AH;

	/**
	 * ID: 1010426<br>
	 * Message: �O�n�b�I���N���I�P�z�b�P�z�b�I�O�G�F�D�D�D
	 */
	public static final NpcStringId COUGH_RAKUL_COUGH_COUGH_KEH;

	/**
	 * ID: 1010427<br>
	 * Message: �p�v���I���ɉh������I$s1�ɂ͎����I
	 */
	public static final NpcStringId GLORY_TO_FAFURION_DEATH_TO_S1;

	/**
	 * ID: 1010428<br>
	 * Message: $s1�A���O���I�����̉����������������߂Ă�̂́I
	 */
	public static final NpcStringId S1_YOU_ARE_THE_ONE_WHO_BOTHERED_MY_POOR_FISH;

	/**
	 * ID: 1010429<br>
	 * Message: �����l�I$s1�Ɏ􂢂��I
	 */
	public static final NpcStringId FAFURION_A_CURSE_UPON_S1;

	/**
	 * ID: 1010430<br>
	 * Message: �W���C�A���g �X�y�V���� �A�^�b�N�I
	 */
	public static final NpcStringId GIANT_SPECIAL_ATTACK;

	/**
	 * ID: 1010431<br>
	 * Message: �������̍��݂��v���m��I
	 */
	public static final NpcStringId KNOW_THE_ENMITY_OF_FISH;

	/**
	 * ID: 1010432<br>
	 * Message: ���̑���H����Ă݂�I
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_A_SPEAR;

	/**
	 * ID: 1010433<br>
	 * Message: �p�v���I���l�ɉh������I
	 */
	public static final NpcStringId GLORY_TO_FAFURION;

	/**
	 * ID: 1010434<br>
	 * Message: ���͂����I
	 */
	public static final NpcStringId YIPES;

	/**
	 * ID: 1010435<br>
	 * Message: �V���͎��Ȃ��D�D�D������������̂݁D�D�D
	 */
	public static final NpcStringId AN_OLD_SOLDIER_DOES_NOT_DIE_BUT_JUST_DISAPPEAR;

	/**
	 * ID: 1010436<br>
	 * Message: $s1�A���̐��̋R�m�̒�����󂯂�I
	 */
	public static final NpcStringId S1_TAKE_MY_CHALLENGE_THE_KNIGHT_OF_WATER;

	/**
	 * ID: 1010437<br>
	 * Message: �������̕񍐂ɂ�����$s1�����I
	 */
	public static final NpcStringId DISCOVER_S1_IN_THE_TREASURE_CHEST_OF_FISH;

	/**
	 * ID: 1010438<br>
	 * Message: $s1�A�������킦���̂͂��񂽂̃G�T�������̂��I
	 */
	public static final NpcStringId S1_I_TOOK_YOUR_BAIT;

	/**
	 * ID: 1010439<br>
	 * Message: ���{��d���݂̑��p����������I
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_SPEARMANSHIP_USED_IN_DRAGON_KINGS_PALACE;

	/**
	 * ID: 1010440<br>
	 * Message: ���񂽂ɑ���Ŋ��̃v���[���g�����I
	 */
	public static final NpcStringId THIS_IS_THE_LAST_GIFT_I_GIVE_YOU;

	/**
	 * ID: 1010441<br>
	 * Message: ���񂽂̃G�T�͎|��������I���Ⴀ�ȁI
	 */
	public static final NpcStringId YOUR_BAIT_WAS_TOO_DELICIOUS_NOW_I_WILL_KILL_YOU;

	/**
	 * ID: 1010442<br>
	 * Message: �������I�䂪���E�̍��݂��I
	 */
	public static final NpcStringId WHAT_A_REGRET_THE_ENMITY_OF_MY_BRETHREN;

	/**
	 * ID: 1010443<br>
	 * Message: �o���Ă�I���̋w�͂����ƒN�����D�D�D
	 */
	public static final NpcStringId ILL_PAY_YOU_BACK_SOMEBODY_WILL_HAVE_MY_REVENGE;

	/**
	 * ID: 1010444<br>
	 * Message: �O�t�b�D�D�D���������O�ɂ͕߂܂�Ȃ����I
	 */
	public static final NpcStringId COUGH_BUT_I_WONT_BE_EATEN_UP_BY_YOU;

	/**
	 * ID: 1010445<br>
	 * Message: $s1�A���������D�D�D
	 */
	public static final NpcStringId _S1_I_WILL_KILL_YOU;

	/**
	 * ID: 1010446<br>
	 * Message: $s1�A�[�C�ɂ��鉴��ނ�グ��Ȃ�āD�D�D
	 */
	public static final NpcStringId S1_HOW_COULD_YOU_CATCH_ME_FROM_THE_DEEP_SEA;

	/**
	 * ID: 1010447<br>
	 * Message: $s1�A�������Ɍ����邩�B
	 */
	public static final NpcStringId S1_DO_YOU_THINK_I_AM_A_FISH;

	/**
	 * ID: 1010448<br>
	 * Message: �͂�ق�Ђ�`
	 */
	public static final NpcStringId EBIBIBI;

	/**
	 * ID: 1010449<br>
	 * Message: �N�N�N�b�A�ǂ�A���񂪂�Ă��Ă�낤���B
	 */
	public static final NpcStringId HE_HE_HE_DO_YOU_WANT_ME_TO_ROAST_YOU_WELL;

	/**
	 * ID: 1010450<br>
	 * Message: ������o��������Ė��f���Ă��񂾂�H
	 */
	public static final NpcStringId YOU_DIDNT_KEEP_YOUR_EYES_ON_ME_BECAUSE_I_COME_FROM_THE_SEA;

	/**
	 * ID: 1010451<br>
	 * Message: ���Ђ�D�D�D�ɂ��D�D�D���ĂĂĂĂ��I
	 */
	public static final NpcStringId EEEK_I_FEEL_SICKYOW;

	/**
	 * ID: 1010452<br>
	 * Message: �`�F�b�A���s���D�D�D���������������̂ɁB
	 */
	public static final NpcStringId I_HAVE_FAILED;

	/**
	 * ID: 1010453<br>
	 * Message: ���������D�D�D��~�D�D�D�W�W�b�D�D�D
	 */
	public static final NpcStringId ACTIVITY_OF_LIFE_IS_STOPPED_CHIZIFC;

	/**
	 * ID: 1010454<br>
	 * Message: �N�����b�I$s1�`�N���b�|�[�B
	 */
	public static final NpcStringId GROWLING_S1_GROWLING;

	/**
	 * ID: 1010455<br>
	 * Message: $s1�̓��������邼���I
	 */
	public static final NpcStringId I_CAN_SMELL_S1;

	/**
	 * ID: 1010456<br>
	 * Message: �������������ȁA$s1�I
	 */
	public static final NpcStringId LOOKS_DELICIOUS_S1;

	/**
	 * ID: 1010457<br>
	 * Message: �H���Ă�邼�������I
	 */
	public static final NpcStringId I_WILL_CATCH_YOU;

	/**
	 * ID: 1010458<br>
	 * Message: ���ււցA�v���Ԃ�ɂ�����G�T���ȁI
	 */
	public static final NpcStringId UAH_AH_AH_I_COULDNT_EAT_ANYTHING_FOR_A_LONG_TIME;

	/**
	 * ID: 1010459<br>
	 * Message: ���O�Ȃ񂩈�����I
	 */
	public static final NpcStringId I_CAN_SWALLOW_YOU_AT_A_MOUTHFUL;

	/**
	 * ID: 1010460<br>
	 * Message: �����D�D�D�G�T�ɂ����Ȃ�āI
	 */
	public static final NpcStringId WHAT_I_AM_DEFEATED_BY_THE_PREY;

	/**
	 * ID: 1010461<br>
	 * Message: ���O�͉��̃G�T�Ȃ񂾂�I�߂��ĐH���Ă��I
	 */
	public static final NpcStringId YOU_ARE_MY_FOOD_I_HAVE_TO_KILL_YOU;

	/**
	 * ID: 1010462<br>
	 * Message: �G�T�ɐH����Ȃ�āD�D�D�N�b�D�D�D
	 */
	public static final NpcStringId I_CANT_BELIEVE_I_AM_EATEN_UP_BY_MY_PREY_GAH;

	/**
	 * ID: 1010463<br>
	 * Message: ����ނ����̂�$s1�H
	 */
	public static final NpcStringId YOU_CAUGHT_ME_S1;

	/**
	 * ID: 1010464<br>
	 * Message: ���ɉ�����Ƃ����h�Ɏv����A$s1�B
	 */
	public static final NpcStringId YOURE_LUCKY_TO_HAVE_EVEN_SEEN_ME_S1;

	/**
	 * ID: 1010465<br>
	 * Message: $s1�A�����ċA���Ȃ�Ă�����ۂ������v��Ȃ��ق����������B
	 */
	public static final NpcStringId S1_YOU_CANT_LEAVE_HERE_ALIVE_GIVE_UP;

	/**
	 * ID: 1010466<br>
	 * Message: �[���̗͂��Ƃ��ƌ���D�D�D
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_THE_DEEP_SEA;

	/**
	 * ID: 1010467<br>
	 * Message: ���̒ނ�Ƃ��ւ��܂��Ă��D�D�D
	 */
	public static final NpcStringId I_WILL_BREAK_THE_FISHING_POLE;

	/**
	 * ID: 1010468<br>
	 * Message: �M�l�̎r�́A���̉������������������D�D�D
	 */
	public static final NpcStringId YOUR_CORPSE_WILL_BE_GOOD_FOOD_FOR_ME;

	/**
	 * ID: 1010469<br>
	 * Message: �����̒ނ�t����Ȃ������̂��D�D�D
	 */
	public static final NpcStringId YOU_ARE_A_GOOD_FISHERMAN;

	/**
	 * ID: 1010470<br>
	 * Message: �p�v���I�����|���Ȃ��̂��D�D�D
	 */
	public static final NpcStringId ARENT_YOU_AFRAID_OF_FAFURION;

	/**
	 * ID: 1010471<br>
	 * Message: �f���炵���A�J�߂Ă�邼�D�D�D
	 */
	public static final NpcStringId YOU_ARE_EXCELLENT;

	/**
	 * ID: 1010472<br>
	 * Message: �ő��u���쓮���܂����B
	 */
	public static final NpcStringId THE_POISON_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010473<br>
	 * Message: 1����A�U���͒ቺ���u���쓮���܂��B
	 */
	public static final NpcStringId THE_P_ATK_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_1_MINUTE;

	/**
	 * ID: 1010474<br>
	 * Message: 2����A�h��͒ቺ���u���쓮���܂��B
	 */
	public static final NpcStringId THE_DEFENSE_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_2_MINUTES;

	/**
	 * ID: 1010475<br>
	 * Message: 3����AHP�񕜌������u���쓮���܂��B
	 */
	public static final NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_3_MINUTES;

	/**
	 * ID: 1010476<br>
	 * Message: �U���͒ቺ���u���쓮���܂����B
	 */
	public static final NpcStringId THE_P_ATK_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010477<br>
	 * Message: �h��͒ቺ���u���쓮���܂����B
	 */
	public static final NpcStringId THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010478<br>
	 * Message: HP�񕜌������u���쓮���܂����B
	 */
	public static final NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010479<br>
	 * Message: �ő��u���j�󂳂�܂����B
	 */
	public static final NpcStringId THE_POISON_DEVICE_HAS_NOW_BEEN_DESTROYED;

	/**
	 * ID: 1010480<br>
	 * Message: �U���͒ቺ���u���j�󂳂�܂����B
	 */
	public static final NpcStringId THE_P_ATK_REDUCTION_DEVICE_HAS_NOW_BEEN_DESTROYED;

	/**
	 * ID: 1010481<br>
	 * Message: �h��͒ቺ���u���j�󂳂�܂����B
	 */
	public static final NpcStringId THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1010485<br>
	 * Message: �����̓��A����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_CAVE_OF_TRIALS;

	/**
	 * ID: 1010486<br>
	 * Message: �G���t�̈�Փ���
	 */
	public static final NpcStringId INSIDE_THE_ELVEN_RUINS;

	/**
	 * ID: 1010487<br>
	 * Message: �G���t�̈�Փ���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ELVEN_RUINS;

	/**
	 * ID: 1010488<br>
	 * Message: �����@����������
	 */
	public static final NpcStringId ENTRANCE_TO_THE_SCHOOL_OF_DARK_ARTS;

	/**
	 * ID: 1010489<br>
	 * Message: �����@����������
	 */
	public static final NpcStringId CENTER_OF_THE_SCHOOL_OF_DARK_ARTS;

	/**
	 * ID: 1010490<br>
	 * Message: �G���t�̒n���v�Ǔ���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ELVEN_FORTRESS;

	/**
	 * ID: 1010491<br>
	 * Message: �o���J�V���m�X���Ԓn
	 */
	public static final NpcStringId VARKA_SILENOS_STRONGHOLD;

	/**
	 * ID: 1010492<br>
	 * Message: �P�g���[ �I�[�N �A�E�g�|�X�g
	 */
	public static final NpcStringId KETRA_ORC_OUTPOST;

	/**
	 * ID: 1010493<br>
	 * Message: ���E����̑��̃M���h
	 */
	public static final NpcStringId RUNE_TOWNSHIP_GUILD;

	/**
	 * ID: 1010494<br>
	 * Message: ���E����̑��̐_�a
	 */
	public static final NpcStringId RUNE_TOWNSHIP_TEMPLE;

	/**
	 * ID: 1010495<br>
	 * Message: ���E����̑��̏��X
	 */
	public static final NpcStringId RUNE_TOWNSHIP_STORE;

	/**
	 * ID: 1010496<br>
	 * Message: �S�҂̐X����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FOREST_OF_THE_DEAD;

	/**
	 * ID: 1010497<br>
	 * Message: �ߖ̏����̓���
	 */
	public static final NpcStringId WESTERN_ENTRANCE_TO_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010498<br>
	 * Message: �Y���ꂽ�_�a�̓���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010499<br>
	 * Message: �Y���ꂽ�_�a����
	 */
	public static final NpcStringId CENTER_OF_THE_FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010500<br>
	 * Message: �N���}�̓�����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_CRUMA_TOWER;

	/**
	 * ID: 1010501<br>
	 * Message: �N���}�̓�1�K
	 */
	public static final NpcStringId CRUMA_TOWER_FIRST_FLOOR;

	/**
	 * ID: 1010502<br>
	 * Message: �N���}�̓�2�K
	 */
	public static final NpcStringId CRUMA_TOWER_SECOND_FLOOR;

	/**
	 * ID: 1010503<br>
	 * Message: �N���}�̓�3�K
	 */
	public static final NpcStringId CRUMA_TOWER_THIRD_FLOOR;

	/**
	 * ID: 1010504<br>
	 * Message: �����̓�����
	 */
	public static final NpcStringId ENTRANCE_TO_DEVILS_ISLE;

	/**
	 * ID: 1010506<br>
	 * Message: �O���[�f�B��������
	 */
	public static final NpcStringId GLUDIN_ARENA;

	/**
	 * ID: 1010507<br>
	 * Message: �M����������
	 */
	public static final NpcStringId GIRAN_ARENA;

	/**
	 * ID: 1010508<br>
	 * Message: �h���S���o���[�̃_���W��������
	 */
	public static final NpcStringId ENTRANCE_TO_ANTHARASS_LAIR;

	/**
	 * ID: 1010509<br>
	 * Message: �h���S���o���[�̃_���W���� 1�K
	 */
	public static final NpcStringId ANTHARASS_LAIR_1ST_LEVEL;

	/**
	 * ID: 1010510<br>
	 * Message: �h���S���o���[�̃_���W���� 2�K
	 */
	public static final NpcStringId ANTHARASS_LAIR_2ND_LEVEL;

	/**
	 * ID: 1010511<br>
	 * Message: �h���S���o���[�̃_���W�������E�̋�
	 */
	public static final NpcStringId ANTHARASS_LAIR_MAGIC_FORCE_FIELD_BRIDGE;

	/**
	 * ID: 1010512<br>
	 * Message: �h���S���o���[�̃_���W�����S����
	 */
	public static final NpcStringId THE_HEART_OF_ANTHARASS_LAIR;

	/**
	 * ID: 1010513<br>
	 * Message: �Î�̑��� ��
	 */
	public static final NpcStringId EAST_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010514<br>
	 * Message: �Î�̑��� ��
	 */
	public static final NpcStringId WEST_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010515<br>
	 * Message: �����̑��� ��
	 */
	public static final NpcStringId EAST_OF_THE_FIELD_OF_WHISPERS;

	/**
	 * ID: 1010516<br>
	 * Message: �����̑��� ��
	 */
	public static final NpcStringId WEST_OF_THE_FIELD_OF_WHISPERS;

	/**
	 * ID: 1010517<br>
	 * Message: �G���@�̐����뉀����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_GARDEN_OF_EVA;

	/**
	 * ID: 1010520<br>
	 * Message: �N���R�_�C�� �A�C�����h�k
	 */
	public static final NpcStringId NORTHERN_PART_OF_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010521<br>
	 * Message: �N���R�_�C�� �A�C�����h����
	 */
	public static final NpcStringId CENTRAL_PART_OF_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010522<br>
	 * Message: �G���@�̐����뉀2�K
	 */
	public static final NpcStringId GARDEN_OF_EVA_2ND_LEVEL;

	/**
	 * ID: 1010523<br>
	 * Message: �G���@�̐����뉀3�K
	 */
	public static final NpcStringId GARDEN_OF_EVA_3RD_LEVEL;

	/**
	 * ID: 1010524<br>
	 * Message: �G���@�̐����뉀4�K
	 */
	public static final NpcStringId GARDEN_OF_EVA_4TH_LEVEL;

	/**
	 * ID: 1010525<br>
	 * Message: �G���@�̐����뉀5�K
	 */
	public static final NpcStringId GARDEN_OF_EVA_5TH_LEVEL;

	/**
	 * ID: 1010526<br>
	 * Message: �G���@�̐����뉀����
	 */
	public static final NpcStringId INSIDE_THE_GARDEN_OF_EVA;

	/**
	 * ID: 1010527<br>
	 * Message: �l���_
	 */
	public static final NpcStringId FOUR_SEPULCHERS;

	/**
	 * ID: 1010528<br>
	 * Message: �鍑�̕�n
	 */
	public static final NpcStringId IMPERIAL_TOMB;

	/**
	 * ID: 1010529<br>
	 * Message: ����̎Q�q�n
	 */
	public static final NpcStringId SHRINE_OF_LOYALTY;

	/**
	 * ID: 1010530<br>
	 * Message: �_�X�̉Δ�����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FORGE_OF_THE_GODS;

	/**
	 * ID: 1010531<br>
	 * Message: �_�X�̉Δ���w
	 */
	public static final NpcStringId FORGE_OF_THE_GODS_TOP_LEVEL;

	/**
	 * ID: 1010532<br>
	 * Message: �_�X�̉Δ����w
	 */
	public static final NpcStringId FORGE_OF_THE_GODS_LOWER_LEVEL;

	/**
	 * ID: 1010533<br>
	 * Message: �A���S�X�̕Ǔ���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_WALL_OF_ARGOS;

	/**
	 * ID: 1010534<br>
	 * Message: �o���J �V���m�X ���B���b�W
	 */
	public static final NpcStringId VARKA_SILENOS_VILLAGE;

	/**
	 * ID: 1010535<br>
	 * Message: �P�g���[ �I�[�N ���B���b�W
	 */
	public static final NpcStringId KETRA_ORC_VILLAGE;

	/**
	 * ID: 1010536<br>
	 * Message: ����n�ѓ���
	 */
	public static final NpcStringId ENTRANCE_TO_THE_HOT_SPRINGS_REGION;

	/**
	 * ID: 1010537<br>
	 * Message: �ҏb�̕��q�n
	 */
	public static final NpcStringId WILD_BEAST_PASTURES;

	/**
	 * ID: 1010538<br>
	 * Message: ���҂̌k�J����
	 */
	public static final NpcStringId ENTRANCE_TO_THE_VALLEY_OF_SAINTS;

	/**
	 * ID: 1010539<br>
	 * Message: ���ꂽ��
	 */
	public static final NpcStringId CURSED_VILLAGE;

	/**
	 * ID: 1010540<br>
	 * Message: �ҏb�̕��q�n��̓���
	 */
	public static final NpcStringId SOUTHERN_ENTRANCE_OF_THE_WILD_BEAST_PASTURES;

	/**
	 * ID: 1010541<br>
	 * Message: �ҏb�̕��q�n��
	 */
	public static final NpcStringId EASTERN_PART_OF_THE_WILD_BEAST_PASTURES;

	/**
	 * ID: 1010542<br>
	 * Message: �ҏb�̕��q�n��
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_WILD_BEAST_PASTURES;

	/**
	 * ID: 1010543<br>
	 * Message: �ߖ̏���
	 */
	public static final NpcStringId EASTERN_PART_OF_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010544<br>
	 * Message: �ߖ̏���
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010545<br>
	 * Message: �ߖ̏�����
	 */
	public static final NpcStringId CENTER_OF_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010547<br>
	 * Message: �A�f�����ʍ����֖�
	 */
	public static final NpcStringId ADEN_FRONTIER_GATEWAY;

	/**
	 * ID: 1010548<br>
	 * Message: �I�[�������ʍ����֖�
	 */
	public static final NpcStringId OREN_FRONTIER_GATEWAY;

	/**
	 * ID: 1010549<br>
	 * Message: ��b�̒뉀
	 */
	public static final NpcStringId GARDEN_OF_WILD_BEASTS;

	/**
	 * ID: 1010550<br>
	 * Message: ����֖̊�
	 */
	public static final NpcStringId DEVILS_PASS;

	/**
	 * ID: 1010551<br>
	 * Message: �e����`���[�W���Ă���Œ��ł��B
	 */
	public static final NpcStringId THE_BULLETS_ARE_BEING_LOADED;

	/**
	 * ID: 1010552<br>
	 * Message: �荏����X�^�[�g�ł��܂��B
	 */
	public static final NpcStringId YOU_CAN_START_AT_THE_SCHEDULED_TIME;

	/**
	 * ID: 1010554<br>
	 * Message: ���l�����̓��A��w��
	 */
	public static final NpcStringId UPPER_LEVEL_OF_THE_GIANTS_CAVE;

	/**
	 * ID: 1010555<br>
	 * Message: ���l�����̓��A���w��
	 */
	public static final NpcStringId LOWER_LEVEL_OF_THE_GIANTS_CAVE;

	/**
	 * ID: 1010556<br>
	 * Message: �s�ł̍����k��
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_NORTHERN_REGION;

	/**
	 * ID: 1010557<br>
	 * Message: �G���t�̈��
	 */
	public static final NpcStringId ELVEN_RUINS;

	/**
	 * ID: 1010558<br>
	 * Message: �̂���
	 */
	public static final NpcStringId SINGING_WATERFALL;

	/**
	 * ID: 1010559<br>
	 * Message: �b���铇�̐���(�k��)
	 */
	public static final NpcStringId TALKING_ISLAND_NORTHERN_TERRITORY;

	/**
	 * ID: 1010560<br>
	 * Message: �G���t�̒n���v��
	 */
	public static final NpcStringId ELVEN_FORTRESS;

	/**
	 * ID: 1010561<br>
	 * Message: ����҂̎��@
	 */
	public static final NpcStringId PILGRIMS_TEMPLE;

	/**
	 * ID: 1010562<br>
	 * Message: �O���[�f�B���`
	 */
	public static final NpcStringId GLUDIN_HARBOR;

	/**
	 * ID: 1010563<br>
	 * Message: �V�[�����̒뉀
	 */
	public static final NpcStringId SHILENS_GARDEN;

	/**
	 * ID: 1010564<br>
	 * Message: �����@������
	 */
	public static final NpcStringId SCHOOL_OF_DARK_ARTS;

	/**
	 * ID: 1010565<br>
	 * Message: �ߖ̏�
	 */
	public static final NpcStringId SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010566<br>
	 * Message: �A���̑�
	 */
	public static final NpcStringId THE_ANT_NEST;

	/**
	 * ID: 1010568<br>
	 * Message: �A���S�X�̕�
	 */
	public static final NpcStringId WALL_OF_ARGOS;

	/**
	 * ID: 1010569<br>
	 * Message: ����̐���
	 */
	public static final NpcStringId DEN_OF_EVIL;

	/**
	 * ID: 1010570<br>
	 * Message: �X���̏���
	 */
	public static final NpcStringId ICEMANS_HUT;

	/**
	 * ID: 1010571<br>
	 * Message: �p�J�̖����n
	 */
	public static final NpcStringId CRYPTS_OF_DISGRACE;

	/**
	 * ID: 1010572<br>
	 * Message: ���D�̍r��
	 */
	public static final NpcStringId PLUNDEROUS_PLAINS;

	/**
	 * ID: 1010573<br>
	 * Message: �p���F���̈��
	 */
	public static final NpcStringId PAVEL_RUINS;

	/**
	 * ID: 1010574<br>
	 * Message: �V���`���b�c�K���g��̑�
	 */
	public static final NpcStringId TOWN_OF_SCHUTTGART;

	/**
	 * ID: 1010575<br>
	 * Message: ���ق̏C���@
	 */
	public static final NpcStringId MONASTERY_OF_SILENCE;

	/**
	 * ID: 1010576<br>
	 * Message: ���ق̏C���@����
	 */
	public static final NpcStringId MONASTERY_OF_SILENCE_REAR_GATE;

	/**
	 * ID: 1010577<br>
	 * Message: �X�^�b�J�[�g�̑�
	 */
	public static final NpcStringId STAKATO_NEST;

	/**
	 * ID: 1010578<br>
	 * Message: �䂪�̈�ɑ��𓥂ݓ����Ƃ͂��������x�����I
	 */
	public static final NpcStringId HOW_DARE_YOU_TRESPASS_INTO_MY_TERRITORY_HAVE_YOU_NO_FEAR;

	/**
	 * ID: 1010579<br>
	 * Message: �����ȁI�܂�������Ă����̂��I�ɂ��ڂɂ��������悤���ȁI
	 */
	public static final NpcStringId FOOLS_WHY_HAVENT_YOU_FLED_YET_PREPARE_TO_LEARN_A_LESSON;

	/**
	 * ID: 1010580<br>
	 * Message: �ӂ͂́A���Ă���I�E���g�� �X�[�p�[�K�E���I�閧����o�������������I
	 */
	public static final NpcStringId BWAH_HA_HA_YOUR_DOOM_IS_AT_HAND_BEHOLD_THE_ULTRA_SECRET_SUPER_WEAPON;

	/**
	 * ID: 1010581<br>
	 * Message: �Ύ~�I���ɒ��ނƂ́I
	 */
	public static final NpcStringId FOOLISH_INSIGNIFICANT_CREATURES_HOW_DARE_YOU_CHALLENGE_ME;

	/**
	 * ID: 1010582<br>
	 * Message: �ǂ������H���������܂����B
	 */
	public static final NpcStringId I_SEE_THAT_NONE_WILL_CHALLENGE_ME_NOW;

	/**
	 * ID: 1010583<br>
	 * Message: �����A�M�l��D�D�D�o���Ă�D�D�D
	 */
	public static final NpcStringId URGGH_YOU_WILL_PAY_DEARLY_FOR_THIS_INSULT;

	/**
	 * ID: 1010584<br>
	 * Message: �Ȃ񂾁A�ꕶ�������B�`�F�b�B
	 */
	public static final NpcStringId WHAT_YOU_HAVENT_EVEN_TWO_PENNIES_TO_RUB_TOGETHER_HARUMPH;

	/**
	 * ID: 1010585<br>
	 * Message: ���̐X
	 */
	public static final NpcStringId FOREST_OF_MIRRORS;

	/**
	 * ID: 1010586<br>
	 * Message: ���̐X�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_FOREST_OF_MIRRORS;

	/**
	 * ID: 1010588<br>
	 * Message: �󒆗�Ԃ̈��
	 */
	public static final NpcStringId SKY_WAGON_RELIC;

	/**
	 * ID: 1010590<br>
	 * Message: �_�[�N�G���t�̐X�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_DARK_FOREST;

	/**
	 * ID: 1010591<br>
	 * Message: ���@���̐���
	 */
	public static final NpcStringId GRAVE_ROBBER_HIDEOUT;

	/**
	 * ID: 1010592<br>
	 * Message: �S�҂̐X
	 */
	public static final NpcStringId FOREST_OF_THE_DEAD;

	/**
	 * ID: 1010593<br>
	 * Message: �S�҂̐X�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_FOREST_OF_THE_DEAD;

	/**
	 * ID: 1010594<br>
	 * Message: �~�X�����z�R
	 */
	public static final NpcStringId MITHRIL_MINES;

	/**
	 * ID: 1010595<br>
	 * Message: �~�X�����z�R�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_MITHRIL_MINES;

	/**
	 * ID: 1010596<br>
	 * Message: �̂Ă�ꂽ�Y�z
	 */
	public static final NpcStringId ABANDONED_COAL_MINES;

	/**
	 * ID: 1010597<br>
	 * Message: �̂Ă�ꂽ�Y�z�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_ABANDONED_COAL_MINES;

	/**
	 * ID: 1010598<br>
	 * Message: �s�ł̍�������
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_WESTERN_REGION;

	/**
	 * ID: 1010600<br>
	 * Message: ���҂̌k�J
	 */
	public static final NpcStringId VALLEY_OF_SAINTS;

	/**
	 * ID: 1010601<br>
	 * Message: ���҂̌k�J�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_VALLEY_OF_SAINTS;

	/**
	 * ID: 1010603<br>
	 * Message: �����̓��A
	 */
	public static final NpcStringId CAVE_OF_TRIALS;

	/**
	 * ID: 1010604<br>
	 * Message: �V�[�����̕���
	 */
	public static final NpcStringId SEAL_OF_SHILEN;

	/**
	 * ID: 1010605<br>
	 * Message: �A���S�X�̕ǂ̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_THE_WALL_OF_ARGOS;

	/**
	 * ID: 1010606<br>
	 * Message: �N���R�_�C�� �A�C�����h�̒��S
	 */
	public static final NpcStringId THE_CENTER_OF_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010607<br>
	 * Message: �A���w����
	 */
	public static final NpcStringId ANGHEL_WATERFALL;

	/**
	 * ID: 1010608<br>
	 * Message: �G���t�̈�Ղ̒��S
	 */
	public static final NpcStringId CENTER_OF_THE_ELVEN_RUINS;

	/**
	 * ID: 1010609<br>
	 * Message: ����n��
	 */
	public static final NpcStringId HOT_SPRINGS;

	/**
	 * ID: 1010610<br>
	 * Message: ����n�ђ���
	 */
	public static final NpcStringId THE_CENTER_OF_THE_HOT_SPRINGS;

	/**
	 * ID: 1010611<br>
	 * Message: �h���S���o���[����
	 */
	public static final NpcStringId THE_CENTER_OF_DRAGON_VALLEY;

	/**
	 * ID: 1010613<br>
	 * Message: �����n�ђ���
	 */
	public static final NpcStringId THE_CENTER_OF_THE_NEUTRAL_ZONE;

	/**
	 * ID: 1010614<br>
	 * Message: �N���}���n
	 */
	public static final NpcStringId CRUMA_MARSHLANDS;

	/**
	 * ID: 1010615<br>
	 * Message: �N���}���n����
	 */
	public static final NpcStringId THE_CENTER_OF_THE_CRUMA_MARSHLANDS;

	/**
	 * ID: 1010617<br>
	 * Message: �t�F�A���[�̒J����
	 */
	public static final NpcStringId THE_CENTER_OF_THE_ENCHANTED_VALLEY;

	/**
	 * ID: 1010618<br>
	 * Message: �t�F�A���[�̒J(�쑤)
	 */
	public static final NpcStringId ENCHANTED_VALLEY_SOUTHERN_REGION;

	/**
	 * ID: 1010619<br>
	 * Message: �t�F�A���[�̒J(�k��)
	 */
	public static final NpcStringId ENCHANTED_VALLEY_NORTHERN_REGION;

	/**
	 * ID: 1010620<br>
	 * Message: �t���X�g��
	 */
	public static final NpcStringId FROST_LAKE;

	/**
	 * ID: 1010621<br>
	 * Message: �r�n
	 */
	public static final NpcStringId WASTELANDS;

	/**
	 * ID: 1010622<br>
	 * Message: �r�n(����)
	 */
	public static final NpcStringId WASTELANDS_WESTERN_REGION;

	/**
	 * ID: 1010623<br>
	 * Message: �䂪��̋ʍ���_���͉̂��z���H�����ɗ�������˂΁A�M�l��̌��������Ă��̑㏞�𕥂��Ă��炨�����I
	 */
	public static final NpcStringId WHO_DARES_TO_COVET_THE_THRONE_OF_OUR_CASTLE_LEAVE_IMMEDIATELY_OR_YOU_WILL_PAY_THE_PRICE_OF_YOUR_AUDACITY_WITH_YOUR_VERY_OWN_BLOOD;

	/**
	 * ID: 1010624<br>
	 * Message: �ق��A�����ł���Ȃ��҂��A���̏��_���Ă���ƁH�N�N�N�b�D�D�D�S�҂̍��݁A���̗͂𕎂�ȁI
	 */
	public static final NpcStringId HMM_THOSE_WHO_ARE_NOT_OF_THE_BLOODLINE_ARE_COMING_THIS_WAY_TO_TAKE_OVER_THE_CASTLE_HUMPH_THE_BITTER_GRUDGES_OF_THE_DEAD_YOU_MUST_NOT_MAKE_LIGHT_OF_THEIR_POWER;

	/**
	 * ID: 1010625<br>
	 * Message: �O�I�b�D�D�D�����|���΁D�D�D���̌��E���I
	 */
	public static final NpcStringId AARGH_IF_I_DIE_THEN_THE_MAGIC_FORCE_FIELD_OF_BLOOD_WILL;

	/**
	 * ID: 1010626<br>
	 * Message: �܂��D�D�D�I��肶��Ȃ��D�D�D����ŏI��点�́D�D�D���Ȃ��D�D�D
	 */
	public static final NpcStringId ITS_NOT_OVER_YET_IT_WONT_BE_OVER_LIKE_THIS_NEVER;

	/**
	 * ID: 1010627<br>
	 * Message: ������D�D�D�N���A�Q�Ă�̂ɓ��Ƀl�N�^�[�����₪������́I
	 */
	public static final NpcStringId OOOH_WHO_POURED_NECTAR_ON_MY_HEAD_WHILE_I_WAS_SLEEPING;

	/**
	 * ID: 1010628<br>
	 * Message: ���X���҂����������B
	 */
	public static final NpcStringId PLEASE_WAIT_A_MOMENT;

	/**
	 * ID: 1010629<br>
	 * Message: �����A���x��$s1�Ƃ����P��𓖂ĂĂ��������B
	 */
	public static final NpcStringId THE_WORD_YOU_NEED_THIS_TIME_IS_S1;

	/**
	 * ID: 1010630<br>
	 * Message: �N���҂��I�x�񑕒u�쓮�B
	 */
	public static final NpcStringId INTRUDERS_SOUND_THE_ALARM;

	/**
	 * ID: 1010631<br>
	 * Message: �x�����
	 */
	public static final NpcStringId DE_ACTIVATE_THE_ALARM;

	/**
	 * ID: 1010632<br>
	 * Message: �܂����D�D�D�h��@�\��������Ƃ́D�D�D��̒��͊댯���I�F�O�ցI
	 */
	public static final NpcStringId OH_NO_THE_DEFENSES_HAVE_FAILED_IT_IS_TOO_DANGEROUS_TO_REMAIN_INSIDE_THE_CASTLE_FLEE_EVERY_MAN_FOR_HIMSELF;

	/**
	 * ID: 1010633<br>
	 * Message: �Q�[���X�^�[�g�I�Q�����ꂽ���́A�����\���グ��P�����[�����ē��ĂĂ��������ˁB
	 */
	public static final NpcStringId THE_GAME_HAS_BEGUN_PARTICIPANTS_PREPARE_TO_LEARN_AN_IMPORTANT_WORD;

	/**
	 * ID: 1010634<br>
	 * Message: ����$s1�`�[�� �|���i�̎c���HP��$s2�p�[�Z���g�ł��B
	 */
	public static final NpcStringId S1_TEAMS_JACKPOT_HAS_S2_PERCENT_OF_ITS_HP_REMAINING;

	/**
	 * ID: 1010635<br>
	 * Message: ����
	 */
	public static final NpcStringId UNDECIDED;

	/**
	 * ID: 1010636<br>
	 * Message: �N�N�b�D�D�D�����n�܂邼�I���̌��ɐ��݂����w���}���Ƃ̎􂢂𖡂킦�I
	 */
	public static final NpcStringId HEH_HEH_I_SEE_THAT_THE_FEAST_HAS_BEGUN_BE_WARY_THE_CURSE_OF_THE_HELLMANN_FAMILY_HAS_POISONED_THIS_LAND;

	/**
	 * ID: 1010637<br>
	 * Message: ���肩��o�߂�A�䂪�Ɛb������B�����󂯌p�����҂�����A�䂪�����Ă�ł���B���̉��̎n�܂肾�I
	 */
	public static final NpcStringId ARISE_MY_FAITHFUL_SERVANTS_YOU_MY_PEOPLE_WHO_HAVE_INHERITED_THE_BLOOD_IT_IS_THE_CALLING_OF_MY_DAUGHTER_THE_FEAST_OF_BLOOD_WILL_NOW_BEGIN;

	/**
	 * ID: 1010639<br>
	 * Message: �Ղ��`���A�������2�����x�A�X�^�W�A���̐��|���s���܂��B�v��Ȃ��A�C�e���������Ă�����͒n�ʂɎ̂Ă�����Ă��������B
	 */
	public static final NpcStringId GRARR_FOR_THE_NEXT_2_MINUTES_OR_SO_THE_GAME_ARENA_ARE_WILL_BE_CLEANED_THROW_ANY_ITEMS_YOU_DONT_NEED_TO_THE_FLOOR_NOW;

	/**
	 * ID: 1010640<br>
	 * Message: �Ղ��`���I$s1�`�[��������`�[���̃G���A�ɉ��򗰉����g�p���܂��B
	 */
	public static final NpcStringId GRARR_S1_TEAM_IS_USING_THE_HOT_SPRINGS_SULFUR_ON_THE_OPPONENTS_CAMP;

	/**
	 * ID: 1010641<br>
	 * Message: �Ղ��`���I$s1�`�[�����|���i�̓���ւ������݂܂��B
	 */
	public static final NpcStringId GRARR_S1_TEAM_IS_ATTEMPTING_TO_STEAL_THE_JACKPOT;

	/**
	 * ID: 1010642<br>
	 * Message: **���**
	 */
	public static final NpcStringId _VACANT_SEAT;

	/**
	 * ID: 1010644<br>
	 * Message: �����̒c�̃X�e�[�W���Ԃ��󂷂Ƃ́D�D�D�����ʁI
	 */
	public static final NpcStringId HOW_DARE_YOU_RUIN_THE_PERFORMANCE_OF_THE_DARK_CHOIR_UNFORGIVABLE;

	/**
	 * ID: 1010645<br>
	 * Message: �����̒c�̉��t��W����ז��҂������I
	 */
	public static final NpcStringId GET_RID_OF_THE_INVADERS_WHO_INTERRUPT_THE_PERFORMANCE_OF_THE_DARK_CHOIR;

	/**
	 * ID: 1010646<br>
	 * Message: ���̉��t���������Ȃ��̂��I�����̒c�̋��낵���������Ă��I
	 */
	public static final NpcStringId DONT_YOU_HEAR_THE_MUSIC_OF_DEATH_REVEAL_THE_HORROR_OF_THE_DARK_CHOIR;

	/**
	 * ID: 1010647<br>
	 * Message: �s�ł̍���
	 */
	public static final NpcStringId THE_IMMORTAL_PLATEAU;

	/**
	 * ID: 1010648<br>
	 * Message: �J�}�G����
	 */
	public static final NpcStringId KAMAEL_VILLAGE;

	/**
	 * ID: 1010649<br>
	 * Message: ���̓����_
	 */
	public static final NpcStringId ISLE_OF_SOULS_BASE;

	/**
	 * ID: 1010650<br>
	 * Message: �����̋u���_
	 */
	public static final NpcStringId GOLDEN_HILLS_BASE;

	/**
	 * ID: 1010651<br>
	 * Message: �~�~���̐X���_
	 */
	public static final NpcStringId MIMIRS_FOREST_BASE;

	/**
	 * ID: 1010652<br>
	 * Message: ���̓��̍`
	 */
	public static final NpcStringId ISLE_OF_SOULS_HARBOR;

	/**
	 * ID: 1010653<br>
	 * Message: ��1���_
	 */
	public static final NpcStringId STRONGHOLD_I;

	/**
	 * ID: 1010654<br>
	 * Message: ��2���_
	 */
	public static final NpcStringId STRONGHOLD_II;

	/**
	 * ID: 1010655<br>
	 * Message: ��3���_
	 */
	public static final NpcStringId STRONGHOLD_III;

	/**
	 * ID: 1010656<br>
	 * Message: �v�ǐ���
	 */
	public static final NpcStringId FORTRESS_WEST_GATE;

	/**
	 * ID: 1010657<br>
	 * Message: �v�Ǔ���
	 */
	public static final NpcStringId FORTRESS_EAST_GATE;

	/**
	 * ID: 1010658<br>
	 * Message: �v�ǖk��
	 */
	public static final NpcStringId FORTRESS_NORTH_GATE;

	/**
	 * ID: 1010659<br>
	 * Message: �v�Ǔ��
	 */
	public static final NpcStringId FORTRESS_SOUTH_GATE;

	/**
	 * ID: 1010660<br>
	 * Message: �k�J�v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_VALLEY_FORTRESS;

	/**
	 * ID: 1010661<br>
	 * Message: �S�_�[�h��̑��L��
	 */
	public static final NpcStringId GODDARD_TOWN_SQUARE;

	/**
	 * ID: 1010662<br>
	 * Message: �S�_�[�h���O
	 */
	public static final NpcStringId FRONT_OF_THE_GODDARD_CASTLE_GATE;

	/**
	 * ID: 1010663<br>
	 * Message: �O���[�f�B�I��̑��L��
	 */
	public static final NpcStringId GLUDIO_TOWN_SQUARE;

	/**
	 * ID: 1010664<br>
	 * Message: �O���[�f�B�I���O
	 */
	public static final NpcStringId FRONT_OF_THE_GLUDIO_CASTLE_GATE;

	/**
	 * ID: 1010665<br>
	 * Message: �M������̑��L��
	 */
	public static final NpcStringId GIRAN_TOWN_SQUARE;

	/**
	 * ID: 1010666<br>
	 * Message: �M�������O
	 */
	public static final NpcStringId FRONT_OF_THE_GIRAN_CASTLE_GATE;

	/**
	 * ID: 1010667<br>
	 * Message: �암�v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_SOUTHERN_FORTRESS;

	/**
	 * ID: 1010668<br>
	 * Message: ���n�їv�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_SWAMP_FORTRESS;

	/**
	 * ID: 1010669<br>
	 * Message: �f�B�I����̑��L��
	 */
	public static final NpcStringId DION_TOWN_SQUARE;

	/**
	 * ID: 1010670<br>
	 * Message: �f�B�I�����O
	 */
	public static final NpcStringId FRONT_OF_THE_DION_CASTLE_GATE;

	/**
	 * ID: 1010671<br>
	 * Message: ���E����̑��L��
	 */
	public static final NpcStringId RUNE_TOWN_SQUARE;

	/**
	 * ID: 1010672<br>
	 * Message: ���E�����O
	 */
	public static final NpcStringId FRONT_OF_THE_RUNE_CASTLE_GATE;

	/**
	 * ID: 1010673<br>
	 * Message: ���l�v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_WHITE_SAND_FORTRESS;

	/**
	 * ID: 1010674<br>
	 * Message: �~�n�v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_BASIN_FORTRESS;

	/**
	 * ID: 1010675<br>
	 * Message: �ۉ�̓��v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_IVORY_FORTRESS;

	/**
	 * ID: 1010676<br>
	 * Message: �V���`���b�c�K���g��̑��L��
	 */
	public static final NpcStringId SCHUTTGART_TOWN_SQUARE;

	/**
	 * ID: 1010677<br>
	 * Message: �V���`���b�c�K���g���O
	 */
	public static final NpcStringId FRONT_OF_THE_SCHUTTGART_CASTLE_GATE;

	/**
	 * ID: 1010678<br>
	 * Message: �A�f����̑��L��
	 */
	public static final NpcStringId ADEN_TOWN_SQUARE;

	/**
	 * ID: 1010679<br>
	 * Message: �A�f�����O
	 */
	public static final NpcStringId FRONT_OF_THE_ADEN_CASTLE_GATE;

	/**
	 * ID: 1010680<br>
	 * Message: �I�c�n�v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_SHANTY_FORTRESS;

	/**
	 * ID: 1010681<br>
	 * Message: �I�[������̑��L��
	 */
	public static final NpcStringId OREN_TOWN_SQUARE;

	/**
	 * ID: 1010682<br>
	 * Message: �I�[�������O
	 */
	public static final NpcStringId FRONT_OF_THE_OREN_CASTLE_GATE;

	/**
	 * ID: 1010683<br>
	 * Message: ��՗v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_ARCHAIC_FORTRESS;

	/**
	 * ID: 1010684<br>
	 * Message: �C���i�h�������O
	 */
	public static final NpcStringId FRONT_OF_THE_INNADRIL_CASTLE_GATE;

	/**
	 * ID: 1010685<br>
	 * Message: �A�E�g�|�X�g�v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_BORDER_FORTRESS;

	/**
	 * ID: 1010686<br>
	 * Message: ����s�s�n�C�l�X�L��
	 */
	public static final NpcStringId HEINE_TOWN_SQUARE;

	/**
	 * ID: 1010687<br>
	 * Message: �n�C���v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_HIVE_FORTRESS;

	/**
	 * ID: 1010688<br>
	 * Message: �΂̗v�ǑO
	 */
	public static final NpcStringId FRONT_OF_THE_NARSELL_FORTRESS;

	/**
	 * ID: 1010689<br>
	 * Message: �O���[�f�B�I��̑O
	 */
	public static final NpcStringId FRONT_OF_THE_GLUDIO_CASTLE;

	/**
	 * ID: 1010690<br>
	 * Message: �f�B�I����̑O
	 */
	public static final NpcStringId FRONT_OF_THE_DION_CASTLE;

	/**
	 * ID: 1010691<br>
	 * Message: �M������̑O
	 */
	public static final NpcStringId FRONT_OF_THE_GIRAN_CASTLE;

	/**
	 * ID: 1010692<br>
	 * Message: �I�[������̑O
	 */
	public static final NpcStringId FRONT_OF_THE_OREN_CASTLE;

	/**
	 * ID: 1010693<br>
	 * Message: �A�f����̑O
	 */
	public static final NpcStringId FRONT_OF_THE_ADEN_CASTLE;

	/**
	 * ID: 1010694<br>
	 * Message: �C���i�h������̑O
	 */
	public static final NpcStringId FRONT_OF_THE_INNADRIL_CASTLE;

	/**
	 * ID: 1010695<br>
	 * Message: �S�_�[�h��̑O
	 */
	public static final NpcStringId FRONT_OF_THE_GODDARD_CASTLE;

	/**
	 * ID: 1010696<br>
	 * Message: ���E����̑O
	 */
	public static final NpcStringId FRONT_OF_THE_RUNE_CASTLE;

	/**
	 * ID: 1010697<br>
	 * Message: �V���`���b�c�K���g��̑O
	 */
	public static final NpcStringId FRONT_OF_THE_SCHUTTGART_CASTLE;

	/**
	 * ID: 1010698<br>
	 * Message: ���Â̓���D��
	 */
	public static final NpcStringId PRIMEVAL_ISLE_WHARF;

	/**
	 * ID: 1010699<br>
	 * Message: �_���̓�
	 */
	public static final NpcStringId ISLE_OF_PRAYER;

	/**
	 * ID: 1010700<br>
	 * Message: �~�X�����z�R�̐�������
	 */
	public static final NpcStringId MITHRIL_MINES_WESTERN_ENTRANCE;

	/**
	 * ID: 1010701<br>
	 * Message: �~�X�����z�R�̖k������
	 */
	public static final NpcStringId MITHRIL_MINES_EASTERN_ENTRANCE;

	/**
	 * ID: 1010702<br>
	 * Message: ���l�����̓��A��w��
	 */
	public static final NpcStringId THE_GIANTS_CAVE_UPPER_LAYER;

	/**
	 * ID: 1010703<br>
	 * Message: ���l�����̓��A���w��
	 */
	public static final NpcStringId THE_GIANTS_CAVE_LOWER_LAYER;

	/**
	 * ID: 1010704<br>
	 * Message: �Î�̑����̒��S
	 */
	public static final NpcStringId FIELD_OF_SILENCE_CENTER;

	/**
	 * ID: 1010705<br>
	 * Message: �����̑����̒��S
	 */
	public static final NpcStringId FIELD_OF_WHISPERS_CENTER;

	/**
	 * ID: 1010706<br>
	 * Message: �V���C�h�̐��ݏ�
	 */
	public static final NpcStringId SHYEEDS_CAVERN;

	/**
	 * ID: 1010709<br>
	 * Message: �s�ł̎��D��
	 */
	public static final NpcStringId SEED_OF_INFINITY_DOCK;

	/**
	 * ID: 1010710<br>
	 * Message: �j�ł̎��D��
	 */
	public static final NpcStringId SEED_OF_DESTRUCTION_DOCK;

	/**
	 * ID: 1010711<br>
	 * Message: ���ł̎��D��
	 */
	public static final NpcStringId SEED_OF_ANNIHILATION_DOCK;

	/**
	 * ID: 1010712<br>
	 * Message: �A�f����̑��̃A�C���n�U�[�h�_�a�ɂ���_���E�b�h
	 */
	public static final NpcStringId TOWN_OF_ADEN_EINHASAD_TEMPLE_PRIEST_WOOD;

	/**
	 * ID: 1010713<br>
	 * Message: �t�̑��ɂ��邿�����ɂȂ������̑O
	 */
	public static final NpcStringId HUNTERS_VILLAGE_SEPARATED_SOUL_FRONT;

	/**
	 * ID: 1029350<br>
	 * Message: ��̍��܂ŉ����Ă��́H�l��҂�����ɂ����������I
	 */
	public static final NpcStringId WHAT_TOOK_SO_LONG_I_WAITED_FOR_EVER;

	/**
	 * ID: 1029351<br>
	 * Message: �{�̂��Ƃ͐}���ْ��\�t�B�A�ɕ����āB
	 */
	public static final NpcStringId I_MUST_ASK_LIBRARIAN_SOPHIA_ABOUT_THE_BOOK;

	/**
	 * ID: 1029352<br>
	 * Message: ���̐}���فA�f�J�������Ŗ��ɗ��{�͂��܂�Ȃ������ˁB
	 */
	public static final NpcStringId THIS_LIBRARY_ITS_HUGE_BUT_THERE_ARENT_MANY_USEFUL_BOOKS_RIGHT;

	/**
	 * ID: 1029353<br>
	 * Message: �n���}���ق˂��D�D�D�W���W�����ĂăN�T���Ƃ���͂���Ȃ񂾂�ˁB
	 */
	public static final NpcStringId AN_UNDERGROUND_LIBRARY_I_HATE_DAMP_AND_SMELLY_PLACES;

	/**
	 * ID: 1029354<br>
	 * Message: �������̒T���Ă�{�́A�����Ƃ����ɂ���͂���B��������Ă݂܂��傤�B
	 */
	public static final NpcStringId THE_BOOK_THAT_WE_SEEK_IS_CERTAINLY_HERE_SEARCH_INCH_BY_INCH;

	/**
	 * ID: 1029450<br>
	 * Message: ������k���ꂼ��̕����ŁA��̖{���u���ꂽ�Ǐ����T���āB
	 */
	public static final NpcStringId WE_MUST_SEARCH_HIGH_AND_LOW_IN_EVERY_ROOM_FOR_THE_READING_DESK_THAT_CONTAINS_THE_BOOK_WE_SEEK;

	/**
	 * ID: 1029451<br>
	 * Message: �{�̓��e�͊o���Ă����āB�����ďo���Ȃ�����B
	 */
	public static final NpcStringId REMEMBER_THE_CONTENT_OF_THE_BOOKS_THAT_YOU_FOUND_YOU_CANT_TAKE_THEM_OUT_WITH_YOU;

	/**
	 * ID: 1029452<br>
	 * Message: ��x�{�������������ɂ͂�������Ȃ��悤�ˁB
	 */
	public static final NpcStringId IT_SEEMS_THAT_YOU_CANNOT_REMEMBER_TO_THE_ROOM_OF_THE_WATCHER_WHO_FOUND_THE_BOOK;

	/**
	 * ID: 1029453<br>
	 * Message: �����ł��ׂ����Ƃ͂��ׂďI�������B�^�񒆂̎��҂̂Ƃ���ɖ߂�܂��傤�B
	 */
	public static final NpcStringId YOUR_WORK_HERE_IS_DONE_SO_RETURN_TO_THE_CENTRAL_GUARDIAN;

	/**
	 * ID: 1029460<br>
	 * Message: �\���i�l�̈�����W����N���҂ǂ��D�D�D���X�ɗ�������B
	 */
	public static final NpcStringId YOU_FOOLISH_INVADERS_WHO_DISTURB_THE_REST_OF_SOLINA_BE_GONE_FROM_THIS_PLACE;

	/**
	 * ID: 1029461<br>
	 * Message: ���O�����̖]�݂͂킩��ʂ��A�q���[�}�����Ƃ��̎�ɕ�������̂ł͂Ȃ��I
	 */
	public static final NpcStringId I_KNOW_NOT_WHAT_YOU_SEEK_BUT_THIS_TRUTH_CANNOT_BE_HANDLED_BY_MERE_HUMANS;

	/**
	 * ID: 1029462<br>
	 * Message: ���������N���҂ǂ��߁I�������Ă͂���ʁI�������Ɨ�������B���ꂪ�Ō�̌x�����B
	 */
	public static final NpcStringId I_WILL_NOT_STAND_BY_AND_WATCH_YOUR_FOOLISH_ACTIONS_I_WARN_YOU_LEAVE_THIS_PLACE_AT_ONCE;

	/**
	 * ID: 1029550<br>
	 * Message: ����̎��҂͌��E�������Ȃ�����͑S�R�_���[�W���󂯂Ȃ��݂����B
	 */
	public static final NpcStringId THE_GUARDIAN_OF_THE_SEAL_DOESNT_SEEM_TO_GET_INJURED_AT_ALL_UNTIL_THE_BARRIER_IS_DESTROYED;

	/**
	 * ID: 1029551<br>
	 * Message: ����̎��҂̑O�̕����̑��u�́A�����Ǝ��҂̗͂��i�錋�E��B
	 */
	public static final NpcStringId THE_DEVICE_LOCATED_IN_THE_ROOM_IN_FRONT_OF_THE_GUARDIAN_OF_THE_SEAL_IS_DEFINITELY_THE_BARRIER_THAT_CONTROLS_THE_GUARDIANS_POWER;

	/**
	 * ID: 1029552<br>
	 * Message: ���E�������ɂ́A����ɍ�����������T���đ��u�𓮂����̂�B
	 */
	public static final NpcStringId TO_REMOVE_THE_BARRIER_YOU_MUST_FIND_THE_RELICS_THAT_FIT_THE_BARRIER_AND_ACTIVATE_THE_DEVICE;

	/**
	 * ID: 1029553<br>
	 * Message: ���҂͑S���|������B������������͂���B�^�񒆂̃e���|�[�g�ňړ����܂��傤�B
	 */
	public static final NpcStringId ALL_THE_GUARDIANS_WERE_DEFEATED_AND_THE_SEAL_WAS_REMOVED_TELEPORT_TO_THE_CENTER;

	/**
	 * ID: 1110071<br>
	 * Message: �N���Ă���Ƃ���ł��B
	 */
	public static final NpcStringId _IS_THE_PROCESS_OF_STANDING_UP;

	/**
	 * ID: 1110072<br>
	 * Message: �����Ă���Ƃ���ł��B
	 */
	public static final NpcStringId _IS_THE_PROCESS_OF_SITTING_DOWN;

	/**
	 * ID: 1110073<br>
	 * Message: �����Ă����ԂŃX�L�����g�p�ł��܂��B
	 */
	public static final NpcStringId IT_IS_POSSIBLE_TO_USE_A_SKILL_WHILE_SITTING_DOWN;

	/**
	 * ID: 1110074<br>
	 * Message: �˒���������O��Ă��܂��B
	 */
	public static final NpcStringId IS_OUT_OF_RANGE;

	/**
	 * ID: 1120300<br>
	 * Message: ��߂Ă���D�D�D�����C���ς񂾂�H
	 */
	public static final NpcStringId THANK_YOU_MY_BOOK_CHILD;

	/**
	 * ID: 1120301<br>
	 * Message: $s1�ɓ|���ꂽ
	 */
	public static final NpcStringId KILLED_BY_S2;

	/**
	 * ID: 1121000<br>
	 * Message: �����F������Ƒ҂��Ă��������B
	 */
	public static final NpcStringId STEWARD_PLEASE_WAIT_A_MOMENT;

	/**
	 * ID: 1121001<br>
	 * Message: �����F�����l���ǂ������̎p�ɁD�D�D
	 */
	public static final NpcStringId STEWARD_PLEASE_RESTORE_THE_QUEENS_FORMER_APPEARANCE;

	/**
	 * ID: 1121002<br>
	 * Message: �����F���Ԃ�����܂���B�}���ł��������B
	 */
	public static final NpcStringId STEWARD_WASTE_NO_TIME_PLEASE_HURRY;

	/**
	 * ID: 1121003<br>
	 * Message: �����F��͂薳���������̂��D�D�D
	 */
	public static final NpcStringId STEWARD_WAS_IT_INDEED_TOO_MUCH_TO_ASK;

	/**
	 * ID: 1121004<br>
	 * Message: �t�����F�����͂ǂ��H���̓�����D�D�D
	 */
	public static final NpcStringId FREYA_HEATHENS_FEEL_MY_CHILL;

	/**
	 * ID: 1121005<br>
	 * Message: ���J���Ԃ͏I�����܂����B��ɂ�����X�͑��₩�ɊO�֏o�Ă��������B
	 */
	public static final NpcStringId ATTENTION_PLEASE_THE_GATES_WILL_BE_CLOSING_SHORTLY_ALL_VISITORS_TO_THE_QUEENS_CASTLE_SHOULD_LEAVE_IMMEDIATELY;

	/**
	 * ID: 1121006<br>
	 * Message: �����ꂽ�҈ȊO�͕���̏������ւ����Ă���I
	 */
	public static final NpcStringId YOU_CANNOT_CARRY_A_WEAPON_WITHOUT_AUTHORIZATION;

	/**
	 * ID: 1121007<br>
	 * Message: �����x������ł��ˁB�����Ȃ��܂����B
	 */
	public static final NpcStringId ARE_YOU_TRYING_TO_DECEIVE_ME_IM_DISAPPOINTED;

	/**
	 * ID: 1121008<br>
	 * Message: �c��30���ł��B
	 */
	public static final NpcStringId N30_MINUTES_REMAIN;

	/**
	 * ID: 1121009<br>
	 * Message: �c��20���ł��B
	 */
	public static final NpcStringId N20_MINUTES_REMAIN;

	/**
	 * ID: 1200001<br>
	 * Message: �`�� �R�[�_
	 */
	public static final NpcStringId CHILLY_CODA;

	/**
	 * ID: 1200002<br>
	 * Message: �o�[�j���O �R�[�_
	 */
	public static final NpcStringId BURNING_CODA;

	/**
	 * ID: 1200003<br>
	 * Message: �u���[ �R�[�_
	 */
	public static final NpcStringId BLUE_CODA;

	/**
	 * ID: 1200004<br>
	 * Message: ���b�h �R�[�_
	 */
	public static final NpcStringId RED_CODA;

	/**
	 * ID: 1200005<br>
	 * Message: �S�[���f�� �R�[�_
	 */
	public static final NpcStringId GOLDEN_CODA;

	/**
	 * ID: 1200006<br>
	 * Message: �f�U�[�g �R�[�_
	 */
	public static final NpcStringId DESERT_CODA;

	/**
	 * ID: 1200007<br>
	 * Message: �����[�g �R�[�_
	 */
	public static final NpcStringId LUTE_CODA;

	/**
	 * ID: 1200008<br>
	 * Message: �c�C�� �R�[�_
	 */
	public static final NpcStringId TWIN_CODA;

	/**
	 * ID: 1200009<br>
	 * Message: �_�[�N �R�[�_
	 */
	public static final NpcStringId DARK_CODA;

	/**
	 * ID: 1200010<br>
	 * Message: �V���C�j���O �R�[�_
	 */
	public static final NpcStringId SHINING_CODA;

	/**
	 * ID: 1200011<br>
	 * Message: �`�� �R�{��
	 */
	public static final NpcStringId CHILLY_COBOL;

	/**
	 * ID: 1200012<br>
	 * Message: �o�[�j���O �R�{��
	 */
	public static final NpcStringId BURNING_COBOL;

	/**
	 * ID: 1200013<br>
	 * Message: �u���[ �R�{��
	 */
	public static final NpcStringId BLUE_COBOL;

	/**
	 * ID: 1200014<br>
	 * Message: ���b�h �R�{��
	 */
	public static final NpcStringId RED_COBOL;

	/**
	 * ID: 1200015<br>
	 * Message: �S�[���f�� �R�{��
	 */
	public static final NpcStringId GOLDEN_COBOL;

	/**
	 * ID: 1200016<br>
	 * Message: �f�U�[�g �R�{��
	 */
	public static final NpcStringId DESERT_COBOL;

	/**
	 * ID: 1200017<br>
	 * Message: �V�[ �R�{��
	 */
	public static final NpcStringId SEA_COBOL;

	/**
	 * ID: 1200018<br>
	 * Message: �o�[ �R�{��
	 */
	public static final NpcStringId THORN_COBOL;

	/**
	 * ID: 1200019<br>
	 * Message: �_�b�v�� �R�{��
	 */
	public static final NpcStringId DAPPLE_COBOL;

	/**
	 * ID: 1200020<br>
	 * Message: �O���[�g �R�{��
	 */
	public static final NpcStringId GREAT_COBOL;

	/**
	 * ID: 1200021<br>
	 * Message: �`�� �R�h����
	 */
	public static final NpcStringId CHILLY_CODRAN;

	/**
	 * ID: 1200022<br>
	 * Message: �o�[�j���O �R�h����
	 */
	public static final NpcStringId BURNING_CODRAN;

	/**
	 * ID: 1200023<br>
	 * Message: �u���[ �R�h����
	 */
	public static final NpcStringId BLUE_CODRAN;

	/**
	 * ID: 1200024<br>
	 * Message: ���b�h �R�h����
	 */
	public static final NpcStringId RED_CODRAN;

	/**
	 * ID: 1200025<br>
	 * Message: �_�b�v�� �R�h����
	 */
	public static final NpcStringId DAPPLE_CODRAN;

	/**
	 * ID: 1200026<br>
	 * Message: �f�U�[�g �R�h����
	 */
	public static final NpcStringId DESERT_CODRAN;

	/**
	 * ID: 1200027<br>
	 * Message: �V�[ �R�h����
	 */
	public static final NpcStringId SEA_CODRAN;

	/**
	 * ID: 1200028<br>
	 * Message: �c�C�� �R�h����
	 */
	public static final NpcStringId TWIN_CODRAN;

	/**
	 * ID: 1200029<br>
	 * Message: �o�[ �R�h����
	 */
	public static final NpcStringId THORN_CODRAN;

	/**
	 * ID: 1200030<br>
	 * Message: �O���[�g �R�h����
	 */
	public static final NpcStringId GREAT_CODRAN;

	/**
	 * ID: 1200031<br>
	 * Message: ���ǃ_�[�N �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_DARK_CODA;

	/**
	 * ID: 1200032<br>
	 * Message: ���ǃ��b�h �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_RED_CODA;

	/**
	 * ID: 1200033<br>
	 * Message: ���ǃ`�� �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_CHILLY_CODA;

	/**
	 * ID: 1200034<br>
	 * Message: ���ǃu���[ �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_BLUE_CODA;

	/**
	 * ID: 1200035<br>
	 * Message: ���ǃS�[���f�� �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_GOLDEN_CODA;

	/**
	 * ID: 1200036<br>
	 * Message: ���ǃ����[�g �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_LUTE_CODA;

	/**
	 * ID: 1200037<br>
	 * Message: ���ǃf�U�[�g �R�[�_
	 */
	public static final NpcStringId ALTERNATIVE_DESERT_CODA;

	/**
	 * ID: 1200038<br>
	 * Message: ���ǃ��b�h �R�{��
	 */
	public static final NpcStringId ALTERNATIVE_RED_COBOL;

	/**
	 * ID: 1200039<br>
	 * Message: ���ǃ`�� �R�{��
	 */
	public static final NpcStringId ALTERNATIVE_CHILLY_COBOL;

	/**
	 * ID: 1200040<br>
	 * Message: ���ǃu���[ �R�{��
	 */
	public static final NpcStringId ALTERNATIVE_BLUE_COBOL;

	/**
	 * ID: 1200041<br>
	 * Message: ���ǃo�[ �R�{��
	 */
	public static final NpcStringId ALTERNATIVE_THORN_COBOL;

	/**
	 * ID: 1200042<br>
	 * Message: ���ǃS�[���f�� �R�{��
	 */
	public static final NpcStringId ALTERNATIVE_GOLDEN_COBOL;

	/**
	 * ID: 1200043<br>
	 * Message: ���ǃO���[�g �R�{��
	 */
	public static final NpcStringId ALTERNATIVE_GREAT_COBOL;

	/**
	 * ID: 1200044<br>
	 * Message: ���ǃ��b�h �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_RED_CODRAN;

	/**
	 * ID: 1200045<br>
	 * Message: ���ǃV�[ �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_SEA_CODRAN;

	/**
	 * ID: 1200046<br>
	 * Message: ���ǃ`�� �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_CHILLY_CODRAN;

	/**
	 * ID: 1200047<br>
	 * Message: ���ǃu���[ �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_BLUE_CODRAN;

	/**
	 * ID: 1200048<br>
	 * Message: ���ǃc�C�� �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_TWIN_CODRAN;

	/**
	 * ID: 1200049<br>
	 * Message: ���ǃO���[�g �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_GREAT_CODRAN;

	/**
	 * ID: 1200050<br>
	 * Message: ���ǃf�U�[�g �R�h����
	 */
	public static final NpcStringId ALTERNATIVE_DESERT_CODRAN;

	/**
	 * ID: 1300001<br>
	 * Message: �����󂳂ꂽ�B���ɂ����ׂĉ󂵁A�G�R�̎w�ߖ{���܂ňړ�����I
	 */
	public static final NpcStringId WE_HAVE_BROKEN_THROUGH_THE_GATE_DESTROY_THE_ENCAMPMENT_AND_MOVE_TO_THE_COMMAND_POST;

	/**
	 * ID: 1300002<br>
	 * Message: ���Ɏw�ߖ��ɂ̔����J���ꂽ�B�}���Ŋ�����ɓ��ꏟ���̂��߂ɍ����f����̂��I
	 */
	public static final NpcStringId THE_COMMAND_GATE_HAS_OPENED_CAPTURE_THE_FLAG_QUICKLY_AND_RAISE_IT_HIGH_TO_PROCLAIM_OUR_VICTORY;

	/**
	 * ID: 1300003<br>
	 * Message: �������D�D�D������D�D�D
	 */
	public static final NpcStringId THE_GODS_HAVE_FORSAKEN_US_RETREAT;

	/**
	 * ID: 1300004<br>
	 * Message: ���O���������̖��܂낤�Ƃ��A��X�̎u���������Ƃ͂ł��Ȃ��̂��D�D�D�|�����A��ނ��I
	 */
	public static final NpcStringId YOU_MAY_HAVE_BROKEN_OUR_ARROWS_BUT_YOU_WILL_NEVER_BREAK_OUR_WILL_ARCHERS_RETREAT;

	/**
	 * ID: 1300005<br>
	 * Message: �����D�D�D�v�ǂ�����Ă������@�w�̗͂��キ�Ȃ��Ă����B�x�������S����ނ���̂ł��B
	 */
	public static final NpcStringId AT_LAST_THE_MAGIC_FIELD_THAT_PROTECTS_THE_FORTRESS_HAS_WEAKENED_VOLUNTEERS_STAND_BACK;

	/**
	 * ID: 1300006<br>
	 * Message: �������D�D�D�{���D�D�D�����������D�D�D�����������肤�D�D�D
	 */
	public static final NpcStringId AIIEEEE_COMMAND_CENTER_THIS_IS_GUARD_UNIT_WE_NEED_BACKUP_RIGHT_AWAY;

	/**
	 * ID: 1300007<br>
	 * Message: �v�ǐ�͂̋������Ւf����܂����B
	 */
	public static final NpcStringId FORTRESS_POWER_DISABLED;

	/**
	 * ID: 1300008<br>
	 * Message: ���̖����Ǝw�����܂����������Ƃ́D�D�D
	 */
	public static final NpcStringId OH_MY_WHAT_HAS_BECOME_OF_ME_MY_FAME_MY_FRIENDS_LOST_ALL_LOST;

	/**
	 * ID: 1300009<br>
	 * Message: No.1 Machine Power Off!
	 */
	public static final NpcStringId MACHINE_NO_1_POWER_OFF;

	/**
	 * ID: 1300010<br>
	 * Message: No.2 Machine Power Off!
	 */
	public static final NpcStringId MACHINE_NO_2_POWER_OFF;

	/**
	 * ID: 1300011<br>
	 * Message: No.3 Machine Power Off!
	 */
	public static final NpcStringId MACHINE_NO_3_POWER_OFF;

	/**
	 * ID: 1300012<br>
	 * Message: �S�����ɍ����I$s1���W���U������I�G�ɌP�����ʂ������Ă��̂��I
	 */
	public static final NpcStringId EVERYONE_CONCENTRATE_YOUR_ATTACKS_ON_S1_SHOW_THE_ENEMY_YOUR_RESOLVE;

	/**
	 * ID: 1300013<br>
	 * Message: �G�R�̎x�������܂��U������̂��퓬�̊�{���B�����I
	 */
	public static final NpcStringId ATTACKING_THE_ENEMYS_REINFORCEMENTS_IS_NECESSARY_TIME_TO_DIE;

	/**
	 * ID: 1300014<br>
	 * Message: �΂̐����I���ɂ̗͂����������������I�G���Ă������Ă��������܂��悤�I
	 */
	public static final NpcStringId SPIRIT_OF_FIRE_UNLEASH_YOUR_POWER_BURN_THE_ENEMY;

	/**
	 * ID: 1300015<br>
	 * Message: ������ƁA�����炩�Ȃ�育�킢�ȁB��`���Ă��炦��H
	 */
	public static final NpcStringId HEY_THESE_FOES_ARE_TOUGHER_THAN_THEY_LOOK_IM_GOING_TO_NEED_SOME_HELP_HERE;

	/**
	 * ID: 1300016<br>
	 * Message: ���Ȃ��킵�Ă���悤�����A�������݂��Ă�낤���I�H
	 */
	public static final NpcStringId DO_YOU_NEED_MY_POWER_YOU_SEEM_TO_BE_STRUGGLING;

	/**
	 * ID: 1300017<br>
	 * Message: �Z�����̂͂�������������B
	 */
	public static final NpcStringId IM_RATHER_BUSY_HERE_AS_WELL;

	/**
	 * ID: 1300018<br>
	 * Message: ���̂܂܂ŏI���Ǝv���Ȃ�B���O��̖�]�������ɕ�����邾�낤�B
	 */
	public static final NpcStringId DONT_THINK_THAT_ITS_GONNA_END_LIKE_THIS_YOUR_AMBITION_WILL_SOON_BE_DESTROYED_AS_WELL;

	/**
	 * ID: 1300019<br>
	 * Message: ���ʊo��͂ł��Ă邾�낤�ȁI
	 */
	public static final NpcStringId YOU_MUST_HAVE_BEEN_PREPARED_TO_DIE;

	/**
	 * ID: 1300020<br>
	 * Message: �����D�D�D�����̐g�ЂƂł���v���ʂ�ɂł��Ȃ��Ƃ́A��Ȃ��B�������̍��ɗ��܂�K�v�͂Ȃ��낤�B
	 */
	public static final NpcStringId I_FEEL_SO_MUCH_GRIEF_THAT_I_CANT_EVEN_TAKE_CARE_OF_MYSELF_THERE_ISNT_ANY_REASON_FOR_ME_TO_STAY_HERE_ANY_LONGER;

	/**
	 * ID: 1300101<br>
	 * Message: �I�c�n�v��
	 */
	public static final NpcStringId SHANTY_FORTRESS;

	/**
	 * ID: 1300102<br>
	 * Message: �O���[�f�B�I�암�v��
	 */
	public static final NpcStringId SOUTHERN_FORTRESS;

	/**
	 * ID: 1300103<br>
	 * Message: �n�C���v��
	 */
	public static final NpcStringId HIVE_FORTRESS;

	/**
	 * ID: 1300104<br>
	 * Message: �k�J�v��
	 */
	public static final NpcStringId VALLEY_FORTRESS;

	/**
	 * ID: 1300105<br>
	 * Message: �ۉ�̓��v��
	 */
	public static final NpcStringId IVORY_FORTRESS;

	/**
	 * ID: 1300106<br>
	 * Message: �΂̗v��
	 */
	public static final NpcStringId NARSELL_FORTRESS;

	/**
	 * ID: 1300107<br>
	 * Message: �~�n�v��
	 */
	public static final NpcStringId BASIN_FORTRESS;

	/**
	 * ID: 1300108<br>
	 * Message: ���l�v��
	 */
	public static final NpcStringId WHITE_SANDS_FORTRESS;

	/**
	 * ID: 1300109<br>
	 * Message: �A�E�g�|�X�g�v��
	 */
	public static final NpcStringId BORDERLAND_FORTRESS;

	/**
	 * ID: 1300110<br>
	 * Message: ���n�їv��
	 */
	public static final NpcStringId SWAMP_FORTRESS;

	/**
	 * ID: 1300111<br>
	 * Message: ��՗v��
	 */
	public static final NpcStringId ARCHAIC_FORTRESS;

	/**
	 * ID: 1300112<br>
	 * Message: �t���[�������E�v��
	 */
	public static final NpcStringId FLORAN_FORTRESS;

	/**
	 * ID: 1300113<br>
	 * Message: ���̎R�����E�v��
	 */
	public static final NpcStringId CLOUD_MOUNTAIN_FORTRESS;

	/**
	 * ID: 1300114<br>
	 * Message: �^�m�[�����E�v��
	 */
	public static final NpcStringId TANOR_FORTRESS;

	/**
	 * ID: 1300115<br>
	 * Message: �h���S�� �X�p�C�����E�v��
	 */
	public static final NpcStringId DRAGONSPINE_FORTRESS;

	/**
	 * ID: 1300116<br>
	 * Message: �n���̋��E�v��
	 */
	public static final NpcStringId ANTHARASS_FORTRESS;

	/**
	 * ID: 1300117<br>
	 * Message: ���� �����v��
	 */
	public static final NpcStringId WESTERN_FORTRESS;

	/**
	 * ID: 1300118<br>
	 * Message: �t�̋��E�v��
	 */
	public static final NpcStringId HUNTERS_FORTRESS;

	/**
	 * ID: 1300119<br>
	 * Message: �����̋��E�v��
	 */
	public static final NpcStringId AARU_FORTRESS;

	/**
	 * ID: 1300120<br>
	 * Message: ����̋��E�v��
	 */
	public static final NpcStringId DEMON_FORTRESS;

	/**
	 * ID: 1300121<br>
	 * Message: ���҂̋��E�v��
	 */
	public static final NpcStringId MONASTIC_FORTRESS;

	/**
	 * ID: 1300122<br>
	 * Message: �Ɨ����
	 */
	public static final NpcStringId INDEPENDENT_STATE;

	/**
	 * ID: 1300123<br>
	 * Message: ���֌W
	 */
	public static final NpcStringId NONPARTISAN;

	/**
	 * ID: 1300124<br>
	 * Message: �_����
	 */
	public static final NpcStringId CONTRACT_STATE;

	/**
	 * ID: 1300125<br>
	 * Message: 1�Ԗڂ̃p�X���[�h�����͂���܂����B
	 */
	public static final NpcStringId FIRST_PASSWORD_HAS_BEEN_ENTERED;

	/**
	 * ID: 1300126<br>
	 * Message: 2�Ԗڂ̃p�X���[�h�����͂���܂����B
	 */
	public static final NpcStringId SECOND_PASSWORD_HAS_BEEN_ENTERED;

	/**
	 * ID: 1300127<br>
	 * Message: �p�X���[�h�����͂���܂���ł����B
	 */
	public static final NpcStringId PASSWORD_HAS_NOT_BEEN_ENTERED;

	/**
	 * ID: 1300128<br>
	 * Message: ���� $s1 / 3��ڂ��s���Ă��܂��B
	 */
	public static final NpcStringId ATTEMPT_S1_3_IS_IN_PROGRESS_THIS_IS_THE_THIRD_ATTEMPT_ON_S1;

	/**
	 * ID: 1300129<br>
	 * Message: 1�Ԃ̋L�������킹�܂����B
	 */
	public static final NpcStringId THE_1ST_MARK_IS_CORRECT;

	/**
	 * ID: 1300130<br>
	 * Message: 2�Ԃ̋L�������킹�܂����B
	 */
	public static final NpcStringId THE_2ND_MARK_IS_CORRECT;

	/**
	 * ID: 1300131<br>
	 * Message: �L���̑g�ݍ��킹�Ɏ��s���܂����B
	 */
	public static final NpcStringId THE_MARKS_HAVE_NOT_BEEN_ASSEMBLED;

	/**
	 * ID: 1300132<br>
	 * Message: �܂��Ȃ���$s1�X�^�W�A���ŁA�I�����s�A�[�h�N���X�������`�[�����Z���n�܂�܂��B
	 */
	public static final NpcStringId OLYMPIAD_CLASS_FREE_TEAM_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;

	/**
	 * ID: 1300133<br>
	 * Message: �̒n�v��
	 */
	public static final NpcStringId DOMAIN_FORTRESS;

	/**
	 * ID: 1300134<br>
	 * Message: ���E�v��
	 */
	public static final NpcStringId BOUNDARY_FORTRESS;

	/**
	 * ID: 1300135<br>
	 * Message: $s1���� $s2��
	 */
	public static final NpcStringId S1HOUR_S2MINUTE;

	/**
	 * ID: 1300136<br>
	 * Message: �w��ł��܂���B
	 */
	public static final NpcStringId NOT_DESIGNATED;

	/**
	 * ID: 1300137<br>
	 * Message: �E�m������A�����̐g�̉�X���~���ɗ��Ă��ꂽ��ł��ˁB
	 */
	public static final NpcStringId WARRIORS_HAVE_YOU_COME_TO_HELP_THOSE_WHO_ARE_IMPRISONED_HERE;

	/**
	 * ID: 1300138<br>
	 * Message: ���炦�I�������Y�I
	 */
	public static final NpcStringId TAKE_THAT_YOU_WEAKLING;

	/**
	 * ID: 1300139<br>
	 * Message: ���͋����񂾁I
	 */
	public static final NpcStringId BEHOLD_MY_MIGHT;

	/**
	 * ID: 1300140<br>
	 * Message: �C�������Ȃ肻�����B
	 */
	public static final NpcStringId YOUR_MIND_IS_GOING_BLANK;

	/**
	 * ID: 1300141<br>
	 * Message: �ɂ݂����̐��܂ł��݂킽�邺�D�D�D
	 */
	public static final NpcStringId UGH_IT_HURTS_DOWN_TO_THE_BONES;

	/**
	 * ID: 1300142<br>
	 * Message: �������E���ȁB�������D�D�D
	 */
	public static final NpcStringId I_CANT_STAND_IT_ANYMORE_AAH;

	/**
	 * ID: 1300143<br>
	 * Message: ����`���I
	 */
	public static final NpcStringId KYAAAK;

	/**
	 * ID: 1300144<br>
	 * Message: �����A�Ȃ�Ă��������I
	 */
	public static final NpcStringId GASP_HOW_CAN_THIS_BE;

	/**
	 * ID: 1300145<br>
	 * Message: �Ă߂���I���Ɠ����΂�΂�ɂ���Ă��̂��I
	 */
	public static final NpcStringId ILL_RIP_THE_FLESH_FROM_YOUR_BONES;

	/**
	 * ID: 1300146<br>
	 * Message: �ꐶ�A�ϑz�̏��Œn���̐ӂߋ�𖡂��킹�Ă��I
	 */
	public static final NpcStringId YOULL_FLOUNDER_IN_DELUSION_FOR_THE_REST_OF_YOUR_LIFE;

	/**
	 * ID: 1300147<br>
	 * Message: �������甲���o�����@�͂Ȃ��I
	 */
	public static final NpcStringId THERE_IS_NO_ESCAPE_FROM_THIS_PLACE;

	/**
	 * ID: 1300148<br>
	 * Message: �Ă߂���A����N���Ǝv���Ă񂾁I
	 */
	public static final NpcStringId HOW_DARE_YOU;

	/**
	 * ID: 1300149<br>
	 * Message: �|���Ă��I
	 */
	public static final NpcStringId I_SHALL_DEFEAT_YOU;

	/**
	 * ID: 1300150<br>
	 * Message: 1�i�K�X�^�[�g�I
	 */
	public static final NpcStringId BEGIN_STAGE_1;

	/**
	 * ID: 1300151<br>
	 * Message: 2�i�K�X�^�[�g�I
	 */
	public static final NpcStringId BEGIN_STAGE_2;

	/**
	 * ID: 1300152<br>
	 * Message: 3�i�K�X�^�[�g�I
	 */
	public static final NpcStringId BEGIN_STAGE_3;

	/**
	 * ID: 1300153<br>
	 * Message: ��萋������ł��ˁI�������͗E�җl��M���Ă��܂����B�����������Ƃ͂ł��܂��񂪁A�S�΂���̂����p�ӂ��܂����B���X�����Ԃ����������܂���ł��傤���B
	 */
	public static final NpcStringId YOUVE_DONE_IT_WE_BELIEVED_IN_YOU_WARRIOR_WE_WANT_TO_SHOW_OUR_SINCERITY_THOUGH_IT_IS_SMALL_PLEASE_GIVE_ME_SOME_OF_YOUR_TIME;

	/**
	 * ID: 1300154<br>
	 * Message: �������_�̋Ïk�@���ғ����܂��B
	 */
	public static final NpcStringId THE_CENTRAL_STRONGHOLDS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300155<br>
	 * Message: ��1���_�̋Ïk�@���ғ����܂��B
	 */
	public static final NpcStringId STRONGHOLD_IS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300156<br>
	 * Message: ��2���_�̋Ïk�@���ғ����܂��B
	 */
	public static final NpcStringId STRONGHOLD_IIS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300157<br>
	 * Message: ��3���_�̋Ïk�@���ғ����܂��B
	 */
	public static final NpcStringId STRONGHOLD_IIIS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300158<br>
	 * Message: �������_�̋Ïk�@���j�󂳂�܂����B
	 */
	public static final NpcStringId THE_CENTRAL_STRONGHOLDS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300159<br>
	 * Message: ��1���_�̋Ïk�@���j�󂳂�܂����B
	 */
	public static final NpcStringId STRONGHOLD_IS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300160<br>
	 * Message: ��2���_�̋Ïk�@���j�󂳂�܂����B
	 */
	public static final NpcStringId STRONGHOLD_IIS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300161<br>
	 * Message: ��3���_�̋Ïk�@���j�󂳂�܂����B
	 */
	public static final NpcStringId STRONGHOLD_IIIS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300162<br>
	 * Message: �Ȃ�Ă��������I�C���������ĂƂĂ����������ɂȂ����B
	 */
	public static final NpcStringId WHAT_A_PREDICAMENT_MY_ATTEMPTS_WERE_UNSUCCESSFUL;

	/**
	 * ID: 1300163<br>
	 * Message: �E�C�I�e�C�I���C�I�̒n��Ő퓬���J��L����b��������I���̌��ɏW�܂�B�x�Ɩ��_���҂��Ă���B
	 */
	public static final NpcStringId COURAGE_AMBITION_PASSION_MERCENARIES_WHO_WANT_TO_REALIZE_THEIR_DREAM_OF_FIGHTING_IN_THE_TERRITORY_WAR_COME_TO_ME_FORTUNE_AND_GLORY_ARE_WAITING_FOR_YOU;

	/**
	 * ID: 1300164<br>
	 * Message: �킢�����̂��B�|�����āH�B��悤�������Ă����͂����Ȃ��B�����������Ȃ�΁A��X�b�������͂�݂����I
	 */
	public static final NpcStringId DO_YOU_WISH_TO_FIGHT_ARE_YOU_AFRAID_NO_MATTER_HOW_HARD_YOU_TRY_YOU_HAVE_NOWHERE_TO_RUN_BUT_IF_YOU_FACE_IT_HEAD_ON_OUR_MERCENARY_TROOP_WILL_HELP_YOU_OUT;

	/**
	 * ID: 1300165<br>
	 * Message: �ˌ��I�ˌ��I�ˌ��I
	 */
	public static final NpcStringId CHARGE_CHARGE_CHARGE;

	/**
	 * ID: 1300166<br>
	 * Message: �܂��Ȃ���$s1�X�^�W�A���ŁA�I�����s�A�[�h�N���X�������l���Z���n�܂�܂��B
	 */
	public static final NpcStringId OLYMPIAD_CLASS_FREE_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;

	/**
	 * ID: 1300167<br>
	 * Message: �܂��Ȃ���$s1�X�^�W�A���ŁA�I�����s�A�[�h�N���X�ʌl���Z���n�܂�܂��B
	 */
	public static final NpcStringId OLYMPIAD_CLASS_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;

	/**
	 * ID: 1600001<br>
	 * Message: ���̃L�����N�^�[�ɕ⏕���@���r�����ł��B���΂炭���Ă���ēx���������������B
	 */
	public static final NpcStringId ANOTHER_PLAYER_IS_CURRENTLY_BEING_BUFFED_PLEASE_TRY_AGAIN_IN_A_MOMENT;

	/**
	 * ID: 1600002<br>
	 * Message: �ϐg���ɂ͓���ł��܂���B
	 */
	public static final NpcStringId YOU_CANNOT_MOUNT_WHILE_YOU_ARE_POLYMORPHED;

	/**
	 * ID: 1600003<br>
	 * Message: �퓬��Ԃ�A�X�g���C�_�[�ɓ��悵����Ԃł̓��C�o�[���ɏ��܂���B
	 */
	public static final NpcStringId YOU_CANNOT_MOUNT_A_WYVERN_WHILE_IN_BATTLE_MODE_OR_WHILE_MOUNTED_ON_A_STRIDER;

	/**
	 * ID: 1600004<br>
	 * Message: ������D�D�D�����D�D�D
	 */
	public static final NpcStringId BOO_HOO_I_HATE;

	/**
	 * ID: 1600005<br>
	 * Message: �܂��ˁ`���I
	 */
	public static final NpcStringId SEE_YOU_LATER;

	/**
	 * ID: 1600006<br>
	 * Message: �悭���I��ł���܂����I
	 */
	public static final NpcStringId YOUVE_MADE_A_GREAT_CHOICE;

	/**
	 * ID: 1600007<br>
	 * Message: ���̒��̃u�c���E�Y���Ă那�B
	 */
	public static final NpcStringId THANKS_I_FEEL_MORE_RELAXED;

	/**
	 * ID: 1600008<br>
	 * Message: ����I��ŁI���̂̓N�Y��I
	 */
	public static final NpcStringId DID_YOU_SEE_THAT_FIRECRACKER_EXPLODE;

	/**
	 * ID: 1600009<br>
	 * Message: ���₢����I���ɂ����Ȃ��ŁI
	 */
	public static final NpcStringId I_AM_NOTHING;

	/**
	 * ID: 1600010<br>
	 * Message: ���A�����ڂ�肷������ł��I
	 */
	public static final NpcStringId I_AM_TELLING_THE_TRUTH;

	/**
	 * ID: 1600012<br>
	 * Message: �o���������ւ̃e���|�[�g�̓^�_�łł����Ⴄ��B
	 */
	public static final NpcStringId ITS_FREE_TO_GO_BACK_TO_THE_VILLAGE_YOU_TELEPORTED_FROM;

	/**
	 * ID: 1600013<br>
	 * Message: �󕨂̑܂̐؂�[��50�W�߂Ă��ꂽ��A�{�N����񂪕󕨂̑܂ɑւ��Ă����邩��ˁB
	 */
	public static final NpcStringId IF_YOU_COLLECT_50_INDIVIDUAL_TREASURE_SACK_PIECES_YOU_CAN_EXCHANGE_THEM_FOR_A_TREASURE_SACK;

	/**
	 * ID: 1600014<br>
	 * Message: �E�T�M�����ɕϐg���Ȃ��Ɣ����������Ȃ���B
	 */
	public static final NpcStringId YOU_MUST_BE_TRANSFORMED_INTO_A_TREASURE_HUNTER_TO_FIND_A_CHEST;

	/**
	 * ID: 1600015<br>
	 * Message: �E�T�M�����̕ϐg�͂����������Ⴄ�񂾁B������A�D���ȏꏊ�Ɉڂ��Ă���X�N���[�����g��������������B
	 */
	public static final NpcStringId YOUD_BETTER_USE_THE_TRANSFORMATION_SPELL_AT_THE_RIGHT_MOMENT_SINCE_IT_DOESNT_LAST_LONG;

	/**
	 * ID: 1600016<br>
	 * Message: ���z�̓��͂ǂ��ł��s�[�X�]�[��������ˁB
	 */
	public static final NpcStringId ALL_OF_FANTASY_ISLE_IS_A_PEACE_ZONE;

	/**
	 * ID: 1600017<br>
	 * Message: ���z�̓��܂Ń{�N����񂪃^�_�ő����Ă������Ⴄ��B
	 */
	public static final NpcStringId IF_YOU_NEED_TO_GO_TO_FANTASY_ISLE_COME_SEE_ME;

	/**
	 * ID: 1600018<br>
	 * Message: �E�T�M�ϐg�X�N���[���̔̔���12���Ԃ��Ƃ�����ˁB
	 */
	public static final NpcStringId YOU_CAN_ONLY_PURCHASE_A_TREASURE_HUNTER_TRANSFORMATION_SCROLL_ONCE_EVERY_12_HOURS;

	/**
	 * ID: 1600019<br>
	 * Message: ����H�����܂ňႤ���@�ŗ����́H���ꂶ��A�߂��̃��E����̑��֑����Ă�����ˁB
	 */
	public static final NpcStringId IF_YOUR_MEANS_OF_ARRIVAL_WAS_A_BIT_UNCONVENTIONAL_THEN_ILL_BE_SENDING_YOU_BACK_TO_RUNE_TOWNSHIP_WHICH_IS_THE_NEAREST_TOWN;

	/**
	 * ID: 1600020<br>
	 * Message: �h���h���b�B
	 */
	public static final NpcStringId RATTLE;

	/**
	 * ID: 1600021<br>
	 * Message: �K�^�S�g�b�B
	 */
	public static final NpcStringId BUMP;

	/**
	 * ID: 1600022<br>
	 * Message: ������܂���B
	 */
	public static final NpcStringId YOU_WILL_REGRET_THIS;

	/**
	 * ID: 1600023<br>
	 * Message: �ӂӂӁD�D�D�ǂ����A�E�C������Ȃ�^�������Ă݂Ȃ����B
	 */
	public static final NpcStringId CARE_TO_CHALLENGE_FATE_AND_TEST_YOUR_LUCK;

	/**
	 * ID: 1600024<br>
	 * Message: S80����͗~�����Ȃ����B
	 */
	public static final NpcStringId DONT_PASS_UP_THE_CHANCE_TO_WIN_AN_S80_WEAPON;

	/**
	 * ID: 1800009<br>
	 * Message: �h���b�v �A�C�e�����z������ # $s1��������A���`�����l���ɂ���܂��B
	 */
	public static final NpcStringId _S1S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;

	/**
	 * ID: 1800010<br>
	 * Message: �h���b�v �A�C�e�����z�������������܂��B
	 */
	public static final NpcStringId LOOTING_RULES_ARE_NO_LONGER_ACTIVE;

	/**
	 * ID: 1800011<br>
	 * Message: ��`���I����l�l�̂��߂肾�B�K���d�Ԃ����Ă�������͂��I���O������������ƂŎ��̂���B
	 */
	public static final NpcStringId OUR_MASTER_NOW_COMES_TO_CLAIM_OUR_VENGEANCE_SOON_YOU_WILL_ALL_BE_NOTHING_MORE_THAN_DIRT;

	/**
	 * ID: 1800012<br>
	 * Message: �t���̌N��ɒ��ގ҂����Ɏ���
	 */
	public static final NpcStringId DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORDS_OF_DAWN;

	/**
	 * ID: 1800013<br>
	 * Message: �����ɒ��ގ҂����Ɏ���
	 */
	public static final NpcStringId DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORD;

	/**
	 * ID: 1800014<br>
	 * Message: �u�q�u�q�I�u�^�����΂ł�����ł��I�A���^���X�畉���̒����� �s�b�O �X�^����I
	 */
	public static final NpcStringId OINK_OINK_PIGS_CAN_DO_IT_TOO_ANTHARAS_SURPASSING_SUPER_POWERED_PIG_STUN_HOW_DO_LIKE_THEM_APPLES;

	/**
	 * ID: 1800015<br>
	 * Message: �u�q�u�q�I�H�炦���I���@���J�X�ł����r�r��E���g�� �s�b�O �t�B�A�[��I
	 */
	public static final NpcStringId OINK_OINK_TAKE_THAT_VALAKAS_TERRORIZING_ULTRA_PIG_FEAR_HA_HA;

	/**
	 * ID: 1800016<br>
	 * Message: �u�q�u�q�I�A���^�A�z�`���}�������Ȃ��I���������ɂ�������I
	 */
	public static final NpcStringId OINK_OINK_GO_AWAY_STOP_BOTHERING_ME;

	/**
	 * ID: 1800017<br>
	 * Message: �u�q�u�q�I�݂Ȃ���W����A�W���I�������I����̗͂��������낤��Ȃ����I
	 */
	public static final NpcStringId OINK_OINK_PIGS_OF_THE_WORLD_UNITE_LETS_SHOW_THEM_OUR_STRENGTH;

	/**
	 * ID: 1800018<br>
	 * Message: ���Â��Ă���͂�����H�������ɁI�u�q�u�q�I
	 */
	public static final NpcStringId YOU_HEALED_ME_THANKS_A_LOT_OINK_OINK;

	/**
	 * ID: 1800019<br>
	 * Message: �u�q�u�q�I�Ȃ��A�A���^�I�z���}�Ɏ��Â��Ă�񂩂��ȁH�߂�����ɂ���񂯁I
	 */
	public static final NpcStringId OINK_OINK_THIS_TREATMENT_HURTS_TOO_MUCH_ARE_YOU_SURE_THAT_YOURE_TRYING_TO_HEAL_ME;

	/**
	 * ID: 1800020<br>
	 * Message: �u�q�u�q�I���E���a�Ɣɉh�̂��߂ɁA���̃v���Y�� �X�g�[�����g���ĕϐg��I
	 */
	public static final NpcStringId OINK_OINK_TRANSFORM_WITH_MOON_CRYSTAL_PRISM_POWER;

	/**
	 * ID: 1800021<br>
	 * Message: �u�q�u�q�I���`�A�����I���ɖ߂肽���Ȃ��`�`
	 */
	public static final NpcStringId OINK_OINK_NOOO_I_DONT_WANT_TO_GO_BACK_TO_NORMAL;

	/**
	 * ID: 1800022<br>
	 * Message: �u�q�I���e�͂��������₳�����A���ʂɃT�[�r�X�����Ă��炢�܂���I�قȁA�����Ȃ�B�u�q�u�q�I
	 */
	public static final NpcStringId OINK_OINK_IM_RICH_SO_IVE_GOT_PLENTY_TO_SHARE_THANKS;

	/**
	 * ID: 1800023<br>
	 * Message: �s�g�ȋC�z�ɕ�܂ꂽ����ł��ˁB�댯��������܂��񂩂�l���̂ĂĂ����܂��I�Ղ��`���I
	 */
	public static final NpcStringId ITS_A_WEAPON_SURROUNDED_BY_AN_OMINOUS_AURA_ILL_DISCARD_IT_BECAUSE_IT_MAY_BE_DANGEROUS_MISS;

	/**
	 * ID: 1800024<br>
	 * Message: ������~���Ă������������Ƃ����ӂ������܂��B
	 */
	public static final NpcStringId THANK_YOU_FOR_SAVING_ME_FROM_THE_CLUTCHES_OF_EVIL;

	/**
	 * ID: 1800025<br>
	 * Message: �����͂��̂��炢�ɂ��Ă����Ă�낤�B�S���ދp���I
	 */
	public static final NpcStringId THAT_IS_IT_FOR_TODAYLETS_RETREAT_EVERYONE_PULL_BACK;

	/**
	 * ID: 1800026<br>
	 * Message: �����Ă��������Ă��肪�Ƃ��������܂��B����͂����₩�ȃv���[���g�ł��B
	 */
	public static final NpcStringId THANK_YOU_FOR_THE_RESCUE_ITS_A_SMALL_GIFT;

	/**
	 * ID: 1800027<br>
	 * Message: $s1�A�Ԃ��N���X�^��������܂���D�D�D
	 */
	public static final NpcStringId S1_YOU_DONT_HAVE_A_RED_CRYSTAL;

	/**
	 * ID: 1800028<br>
	 * Message: $s1�A���N���X�^��������܂���D�D�D
	 */
	public static final NpcStringId S1_YOU_DONT_HAVE_A_BLUE_CRYSTAL;

	/**
	 * ID: 1800029<br>
	 * Message: $s1�A�����ȃN���X�^��������܂���D�D�D
	 */
	public static final NpcStringId S1_YOU_DONT_HAVE_A_CLEAR_CRYSTAL;

	/**
	 * ID: 1800030<br>
	 * Message: $s1�A�����痣��߂�����A���Ȃ��𑗂�܂����B
	 */
	public static final NpcStringId S1_IF_YOU_ARE_TOO_FAR_AWAY_FROM_MEI_CANT_LET_YOU_GO;

	/**
	 * ID: 1800031<br>
	 * Message: �x�񑕒u�쓮�I�N���Ҕ����I�N���Ҍ��ރv���O�����𔭓����܂��I
	 */
	public static final NpcStringId AN_ALARM_HAS_BEEN_SET_OFF_EVERYBODY_WILL_BE_IN_DANGER_IF_THEY_ARE_NOT_TAKEN_CARE_OF_IMMEDIATELY;

	/**
	 * ID: 1800032<br>
	 * Message: �ȒP�ɂ���͂��Ȃ���I
	 */
	public static final NpcStringId IT_WILL_NOT_BE_THAT_EASY_TO_KILL_ME;

	/**
	 * ID: 1800033<br>
	 * Message: ���A����ȁD�D�D���̔閧��m����̂�����Ȃ�āD�D�D
	 */
	public static final NpcStringId NOYOU_KNEW_MY_WEAKNESS;

	/**
	 * ID: 1800034<br>
	 * Message: ���`���A�N�����܂��񂩂��H
	 */
	public static final NpcStringId HELLO_IS_ANYONE_THERE;

	/**
	 * ID: 1800035<br>
	 * Message: �N�����Ȃ��̂����`�ǂ̂��炢�B��Ă��񂾂낤�B�������H�ׂĂȂ�����A�����󂢂Ď��ɂ�������I
	 */
	public static final NpcStringId IS_NO_ONE_THERE_HOW_LONG_HAVE_I_BEEN_HIDING_I_HAVE_BEEN_STARVING_FOR_DAYS_AND_CANNOT_HOLD_OUT_ANYMORE;

	/**
	 * ID: 1800036<br>
	 * Message: �N���`���������N���X�^���̔j�Ђ����ꂽ��A���̗₽���e�B�A�[�Y�̉B��Ƃ�����������Ă��������ǂȁ`�ނɂ�ނɂ�B
	 */
	public static final NpcStringId IF_SOMEONE_WOULD_GIVE_ME_SOME_OF_THOSE_TASTY_CRYSTAL_FRAGMENTS_I_WOULD_GLADLY_TELL_THEM_WHERE_TEARS_IS_HIDING_YUMMY_YUMMY;

	/**
	 * ID: 1800037<br>
	 * Message: ���`���A�n�ォ�痈���l�����`�N���X�^���̔j�Ђ������Ă�Ȃ�A�������傤���`���B
	 */
	public static final NpcStringId HEY_YOU_FROM_ABOVE_THE_GROUND_LETS_SHARE_SOME_CRYSTAL_FRAGMENTS_IF_YOU_HAVE_ANY;

	/**
	 * ID: 1800038<br>
	 * Message: �T�N�T�N���ăq���������Ċ����`���ւց`���̖�����I
	 */
	public static final NpcStringId CRISPY_AND_COLD_FEELING_TEEHEE_DELICIOUS;

	/**
	 * ID: 1800039<br>
	 * Message: ���V�����V���A���������������`
	 */
	public static final NpcStringId YUMMY_THIS_IS_SO_TASTY;

	/**
	 * ID: 1800040<br>
	 * Message: �N���N���A�N���X�^���̔j�Ђ����Ƃ���`
	 */
	public static final NpcStringId SNIFF_SNIFF_GIVE_ME_MORE_CRYSTAL_FRAGMENTS;

	/**
	 * ID: 1800041<br>
	 * Message: ��Ȃ��ȁI�����������Ă��������Ȃ���I�����Ƃ������񂭂�Ȃ��H
	 */
	public static final NpcStringId HOW_INSENSITIVE_ITS_NOT_NICE_TO_GIVE_ME_JUST_A_PIECE_CANT_YOU_GIVE_ME_MORE;

	/**
	 * ID: 1800042<br>
	 * Message: ���`�����󂢂���`
	 */
	public static final NpcStringId AH_IM_HUNGRY;

	/**
	 * ID: 1800043<br>
	 * Message: �����{������`
	 */
	public static final NpcStringId IM_THE_REAL_ONE;

	/**
	 * ID: 1800044<br>
	 * Message: ����I��ł�����`
	 */
	public static final NpcStringId PICK_ME;

	/**
	 * ID: 1800045<br>
	 * Message: �M���Ă�`
	 */
	public static final NpcStringId TRUST_ME;

	/**
	 * ID: 1800046<br>
	 * Message: �A�C�c����Ȃ��Ď����{������I
	 */
	public static final NpcStringId NOT_THAT_DUDE_IM_THE_REAL_ONE;

	/**
	 * ID: 1800047<br>
	 * Message: �_���_���A���܂��ꂿ��I�����{��������ˁI
	 */
	public static final NpcStringId DONT_BE_FOOLED_DONT_BE_FOOLED_IM_THE_REAL_ONE;

	/**
	 * ID: 1800048<br>
	 * Message: �{���̂ӂ肷��΂������Ă��Ƃ���ˁI�����Ƃ��I
	 */
	public static final NpcStringId JUST_ACT_LIKE_THE_REAL_ONE_OOPS;

	/**
	 * ID: 1800049<br>
	 * Message: �c�O�ł����`
	 */
	public static final NpcStringId YOUVE_BEEN_FOOLED;

	/**
	 * ID: 1800050<br>
	 * Message: �\����Ȃ����ǁD�D�D���̓j�Z���m����`
	 */
	public static final NpcStringId SORRY_BUT_IM_THE_FAKE_ONE;

	/**
	 * ID: 1800051<br>
	 * Message: �����{������`�C�G�`�C�I
	 */
	public static final NpcStringId IM_THE_REAL_ONE_PHEW;

	/**
	 * ID: 1800052<br>
	 * Message: ���ꂭ�炢�������Ȃ��́H
	 */
	public static final NpcStringId CANT_YOU_EVEN_FIND_OUT;

	/**
	 * ID: 1800053<br>
	 * Message: ���̂Ƃ���ɗ��ā`
	 */
	public static final NpcStringId FIND_ME;

	/**
	 * ID: 1800054<br>
	 * Message: ������I�������Ăǂ����Ă킩�����́H
	 */
	public static final NpcStringId HUH_HOW_DID_YOU_KNOW_IT_WAS_ME;

	/**
	 * ID: 1800055<br>
	 * Message: �����I�����ˁ`�����������`
	 */
	public static final NpcStringId EXCELLENT_CHOICE_TEEHEE;

	/**
	 * ID: 1800056<br>
	 * Message: ��`���ł��܂����`
	 */
	public static final NpcStringId YOUVE_DONE_WELL;

	/**
	 * ID: 1800057<br>
	 * Message: �����`�Z���X����񂶂�Ȃ��H
	 */
	public static final NpcStringId OH_VERY_SENSIBLE;

	/**
	 * ID: 1800058<br>
	 * Message: �E�I�I�H�H�H�I �䂪���̓o�[���[�I �v���m�邪�悢�A�䂪�͂��I
	 */
	public static final NpcStringId BEHOLD_THE_MIGHTY_POWER_OF_BAYLOR_FOOLISH_MORTAL;

	/**
	 * ID: 1800059<br>
	 * Message: �N��l�Ƃ��Đ����Ă͋A���񂼁I
	 */
	public static final NpcStringId NO_ONE_IS_GOING_TO_SURVIVE;

	/**
	 * ID: 1800060<br>
	 * Message: �n���Ƃ͂��������Ƃ��낾�I
	 */
	public static final NpcStringId YOULL_SEE_WHAT_HELL_IS_LIKE;

	/**
	 * ID: 1800061<br>
	 * Message: ���̒��P���ǂ����I �n���ɑ����Ă���I
	 */
	public static final NpcStringId YOU_WILL_BE_PUT_IN_JAIL;

	/**
	 * ID: 1800062<br>
	 * Message: ���̒��x���B�΂킹��ȁI�n���֏���������I
	 */
	public static final NpcStringId WORTHLESS_CREATURE_GO_TO_HELL;

	/**
	 * ID: 1800063<br>
	 * Message: �䂪���|�̗͂ł��O�̐��_���x�z���Ă�낤�I
	 */
	public static final NpcStringId ILL_GIVE_YOU_SOMETHING_THAT_YOULL_NEVER_FORGET;

	/**
	 * ID: 1800064<br>
	 * Message: ����M���Ď���I�񂾂̂��D�D�D�E�t�t�t�B
	 */
	public static final NpcStringId WHY_DID_YOU_TRUST_TO_CHOOSE_ME_HAHAHAHA;

	/**
	 * ID: 1800065<br>
	 * Message: ����I�񂾂��Ƃ���������Ă�낤�D�D�D
	 */
	public static final NpcStringId ILL_MAKE_YOU_REGRET_THAT_YOU_EVER_CHOSE_ME;

	/**
	 * ID: 1800066<br>
	 * Message: �����ċA���Ƃł��v���Ă���̂��D�D�D
	 */
	public static final NpcStringId DONT_EXPECT_TO_GET_OUT_ALIVE;

	/**
	 * ID: 1800067<br>
	 * Message: �����x���X��B��ɗ͂�^�����܂��I
	 */
	public static final NpcStringId DEMON_KING_BELETH_GIVE_ME_THE_POWER_AAAHH;

	/**
	 * ID: 1800068<br>
	 * Message: �Ȃ�ƁD�D�D�p�v���I���̗͂�������D�D�D
	 */
	public static final NpcStringId NO_I_FEEL_THE_POWER_OF_FAFURION;

	/**
	 * ID: 1800069<br>
	 * Message: �p�v���I����`���̈���țޏ��ɂ��͂��I
	 */
	public static final NpcStringId FAFURION_PLEASE_GIVE_POWER_TO_THIS_HELPLESS_WITCH;

	/**
	 * ID: 1800070<br>
	 * Message: ���͂��̏ꏊ�ɕ߂���̐g�B�o�[���[�̗͂�������܂点�邭�炢�������͓Y���ł��܂���D�D�D
	 */
	public static final NpcStringId I_CANT_HELP_YOU_MUCH_BUT_I_CAN_WEAKEN_THE_POWER_OF_BAYLOR_SINCE_IM_LOCKED_UP_HERE;

	/**
	 * ID: 1800071<br>
	 * Message: ���Ȃ�̎��͂��ȁB���̂��炢�Ȃ玄�𒴂���r���݂��Ƃ������Ƃ�F�߂Ă�낤�B�J�M�������Ă����𔭂��Ȃ����B
	 */
	public static final NpcStringId YOUR_SKILL_IS_IMPRESSIVE_ILL_ADMIT_THAT_YOU_ARE_GOOD_ENOUGH_TO_PASS_TAKE_THE_KEY_AND_LEAVE_THIS_PLACE;

	/**
	 * ID: 1800072<br>
	 * Message: �����Ă�����̑S���o���I��������Ζ������͌������Ă�邼�I
	 */
	public static final NpcStringId GIVE_ME_ALL_YOU_HAVE_ITS_THE_ONLY_WAY_ILL_LET_YOU_LIVE;

	/**
	 * ID: 1800073<br>
	 * Message: ���D�D�D�������������B
	 */
	public static final NpcStringId HUN_HUNGRY;

	/**
	 * ID: 1800074<br>
	 * Message: �T�{���Ă�񂶂�Ȃ����I���̒�����ǂ��߁I
	 */
	public static final NpcStringId DONT_BE_LAZY_YOU_BASTARDS;

	/**
	 * ID: 1800075<br>
	 * Message: �|�S�̏�̉񂵎҂���Ȃ������̂��D�D�D�т����肳���Ȃ��ł����D�D�D
	 */
	public static final NpcStringId THEY_ARE_JUST_HENCHMEN_OF_THE_IRON_CASTLE_WHY_DID_WE_HIDE;

	/**
	 * ID: 1800076<br>
	 * Message: ��Y�ǂ��I������ɉ������̗͂������t���Ă��I
	 */
	public static final NpcStringId GUYS_SHOW_THEM_OUR_POWER;

	/**
	 * ID: 1800077<br>
	 * Message: �悭�������܂ł��ǂ蒅�����ȁD�D�D�����閧�̔����J�������͂��ʂ��I
	 */
	public static final NpcStringId YOU_HAVE_FINALLY_COME_HERE_BUT_YOU_WILL_NOT_BE_ABLE_TO_FIND_THE_SECRET_ROOM;

	/**
	 * ID: 1800078<br>
	 * Message: �悭�������܂ŗ����ȁI�����J�M�͂����ȒP�ɂ͓n���񂼁I
	 */
	public static final NpcStringId YOU_HAVE_DONE_WELL_IN_FINDING_ME_BUT_I_CANNOT_JUST_HAND_YOU_THE_KEY;

	/**
	 * ID: 1800081<br>
	 * Message: ����Ǘ��l�Ɨ��ꂷ���Ă��邽�߁A���Z�������I�ɃL�����Z������܂����B
	 */
	public static final NpcStringId THE_MATCH_IS_AUTOMATICALLY_CANCELED_BECAUSE_YOU_ARE_TOO_FAR_FROM_THE_ADMISSION_MANAGER;

	/**
	 * ID: 1800082<br>
	 * Message: �����A�ْ�����D�D�D���������o�ԂȂ̂ɂǂ����悤�D�D�D������B
	 */
	public static final NpcStringId UGH_I_HAVE_BUTTERFLIES_IN_MY_STOMACH_THE_SHOW_STARTS_SOON;

	/**
	 * ID: 1800083<br>
	 * Message: �{���͓��ʃC�x���g�ɂ��W�܂肢�������A�܂��Ƃɂ��肪�Ƃ��������܂��B
	 */
	public static final NpcStringId THANK_YOU_ALL_FOR_COMING_HERE_TONIGHT;

	/**
	 * ID: 1800084<br>
	 * Message: ���̂悤�ȃC�x���g���J�Âł������Ƃ��������v���܂��B
	 */
	public static final NpcStringId IT_IS_AN_HONOR_TO_HAVE_THE_SPECIAL_SHOW_TODAY;

	/**
	 * ID: 1800085<br>
	 * Message: ���̌��z�̓��ł́A�F�l�ɂ��y���݂���������悤�A��ɐ^�S��s�����Ă���܂��B
	 */
	public static final NpcStringId FANTASY_ISLE_IS_FULLY_COMMITTED_TO_YOUR_HAPPINESS;

	/**
	 * ID: 1800086<br>
	 * Message: ����ł́A���̃C�x���g�ɉ؂�Y����A�f�������񂾍ō��̔��l�̎�A���C���~����������Љ�܂��I
	 */
	public static final NpcStringId NOW_ID_LIKE_TO_INTRODUCE_THE_MOST_BEAUTIFUL_SINGER_IN_ADEN_PLEASE_WELCOMELEYLA_MIRA;

	/**
	 * ID: 1800087<br>
	 * Message: �ǂ����`
	 */
	public static final NpcStringId HERE_SHE_COMES;

	/**
	 * ID: 1800088<br>
	 * Message: ���C���~������A���肪�Ƃ��������܂����B�����́D�D�D
	 */
	public static final NpcStringId THANK_YOU_VERY_MUCH_LEYLA;

	/**
	 * ID: 1800089<br>
	 * Message: �͂��B�����̓A�f�����ւ閼�l�|�����y���݂��������܂��傤�B
	 */
	public static final NpcStringId NOW_WERE_IN_FOR_A_REAL_TREAT;

	/**
	 * ID: 1800090<br>
	 * Message: �܂��A���[���h �c�A�[���I���A���̏�ɋ삯�t���Ă������������z�̓��̋Ȍ|�c�̊F����ł��I
	 */
	public static final NpcStringId JUST_BACK_FROM_THEIR_WORLD_TOUR_PUT_YOUR_HANDS_TOGETHER_FOR_THE_FANTASY_ISLE_CIRCUS;

	/**
	 * ID: 1800091<br>
	 * Message: �Ȍ|�c�̊F���`��A�͂肫���Ăǂ����I
	 */
	public static final NpcStringId COME_ON_EVERYONE;

	/**
	 * ID: 1800092<br>
	 * Message: �������ł������B�f���炵������ł����ˁB
	 */
	public static final NpcStringId DID_YOU_LIKE_IT_THAT_WAS_SO_AMAZING;

	/**
	 * ID: 1800093<br>
	 * Message: �����A�����͊e�n����W�܂����˔\����������̕���ł��B
	 */
	public static final NpcStringId NOW_WE_ALSO_INVITED_INDIVIDUALS_WITH_SPECIAL_TALENTS;

	/**
	 * ID: 1800094<br>
	 * Message: �ł́I�g�b�v�o�b�^�[���肢�������܂��I
	 */
	public static final NpcStringId LETS_WELCOME_THE_FIRST_PERSON_HERE;

	/**
	 * ID: 1800095<br>
	 * Message: �G�G�G�G���`��B
	 */
	public static final NpcStringId OH;

	/**
	 * ID: 1800096<br>
	 * Message: �͂����B�ł͎��̕����肢�������܂��B
	 */
	public static final NpcStringId OKAY_NOW_HERE_COMES_THE_NEXT_PERSON_COME_ON_UP_PLEASE;

	/**
	 * ID: 1800097<br>
	 * Message: �����A���Ȃ肷�����ł��˂��B
	 */
	public static final NpcStringId OH_IT_LOOKS_LIKE_SOMETHING_GREAT_IS_GOING_TO_HAPPEN_RIGHT;

	/**
	 * ID: 1800098<br>
	 * Message: �n�b�G�G�G�G
	 */
	public static final NpcStringId OH_MY;

	/**
	 * ID: 1800099<br>
	 * Message: ���A���΂炵���I�ł͍Ō�̕��A���肢�������܂��I
	 */
	public static final NpcStringId THATS_G_GREAT_NOW_HERE_COMES_THE_LAST_PERSON;

	/**
	 * ID: 1800100<br>
	 * Message: ����ɂĊ�l�E���l�V���[�����J���Ƃ����Ă��������܂��B
	 */
	public static final NpcStringId NOW_THIS_IS_THE_END_OF_TODAYS_SHOW;

	/**
	 * ID: 1800101<br>
	 * Message: �F����ǂ��ł����H�y����ł��������܂������B
	 */
	public static final NpcStringId HOW_WAS_IT_I_HOPE_YOU_ALL_ENJOYED_IT;

	/**
	 * ID: 1800102<br>
	 * Message: ���ǂ����z�̓��ł́A�F�l�Ɋ��ł���������悤�ɗl�X�ȃC�x���g��p�ӂ��Ă���܂��B�ǂ��������҂��������B
	 */
	public static final NpcStringId PLEASE_REMEMBER_THAT_FANTASY_ISLE_IS_ALWAYS_PLANNING_A_LOT_OF_GREAT_SHOWS_FOR_YOU;

	/**
	 * ID: 1800103<br>
	 * Message: ���c�ɂ����ł����A����ɂĕ��Ƃ����Ă��������܂��B
	 */
	public static final NpcStringId WELL_I_WISH_I_COULD_CONTINUE_ALL_NIGHT_LONG_BUT_THIS_IS_IT_FOR_TODAY_THANK_YOU;

	/**
	 * ID: 1800104<br>
	 * Message: ����ł͊F�l��������悤�B
	 */
	public static final NpcStringId WE_LOVE_YOU;

	/**
	 * ID: 1800105<br>
	 * Message: �Ȃ�ŒN���W�܂��Ă���Ȃ��́D�D�D���������C�x���g���n�܂�̂ɁD�D�D������B
	 */
	public static final NpcStringId HOW_COME_PEOPLE_ARE_NOT_HERE_WE_ARE_ABOUT_TO_START_THE_SHOW_HMM;

	/**
	 * ID: 1800106<br>
	 * Message: ����`�[�������Z���L�����Z�����܂����B
	 */
	public static final NpcStringId THE_OPPONENT_TEAM_CANCELED_THE_MATCH;

	/**
	 * ID: 1800107<br>
	 * Message: �����ȒP�ɂ͎�ɓ���Ȃ����낤�B
	 */
	public static final NpcStringId ITS_NOT_EASY_TO_OBTAIN;

	/**
	 * ID: 1800108<br>
	 * Message: ���C�ł����ɓ����Ă���Ƃ͂ȁD�D�D
	 */
	public static final NpcStringId YOURE_OUT_OF_YOUR_MIND_COMING_HERE;

	/**
	 * ID: 1800109<br>
	 * Message: �G���N�������I�}���ōH���ɂƂ肩����I
	 */
	public static final NpcStringId ENEMY_INVASION_HURRY_UP;

	/**
	 * ID: 1800110<br>
	 * Message: ���̂����ŁD�D�D�H�����D�D�D�x��ẮD�D�D�Ȃ�ʁD�D�D
	 */
	public static final NpcStringId PROCESS_SHOULDNT_BE_DELAYED_BECAUSE_OF_ME;

	/**
	 * ID: 1800111<br>
	 * Message: �������낤�B���I�_�X�͂������O�̂��̂��I
	 */
	public static final NpcStringId ALRIGHT_NOW_LEODAS_IS_YOURS;

	/**
	 * ID: 1800112<br>
	 * Message: �V���ȓz�ꂪ�K�v�Ȃ悤���ȁD�D�D�����ɖ߂邩��҂��Ă�I
	 */
	public static final NpcStringId WE_MIGHT_NEED_NEW_SLAVES_ILL_BE_BACK_SOON_SO_WAIT;

	/**
	 * ID: 1800113<br>
	 * Message: �^�C�� ���[�v���u�̋N�������I
	 */
	public static final NpcStringId TIME_RIFT_DEVICE_ACTIVATION_SUCCESSFUL;

	/**
	 * ID: 1800114<br>
	 * Message: ��Ɏ��R���I�����Ȃ��Ύ����I
	 */
	public static final NpcStringId FREEDOM_OR_DEATH;

	/**
	 * ID: 1800115<br>
	 * Message: ���ꂪ�^�̐�m�����̈ӎu���I
	 */
	public static final NpcStringId THIS_IS_THE_WILL_OF_TRUE_WARRIORS;

	/**
	 * ID: 1800116<br>
	 * Message: ���ӂ̔т͒n���ŐH���񂾂ȁI
	 */
	public static final NpcStringId WELL_HAVE_DINNER_IN_HELL;

	/**
	 * ID: 1800118<br>
	 * Message: �W�W�W�b�F�������̊��ɂ��s��F�O�i���۔����F
	 */
	public static final NpcStringId ZZZZ_CITY_INTERFERENCE_ERROR_FORWARD_EFFECT_CREATED;

	/**
	 * ID: 1800119<br>
	 * Message: �W�W�W�b�F�������̊��ɂ��s��F��A���۔����F
	 */
	public static final NpcStringId ZZZZ_CITY_INTERFERENCE_ERROR_RECURRENCE_EFFECT_CREATED;

	/**
	 * ID: 1800120<br>
	 * Message: �Ď����������������ė��܂��I���������Ă��������I
	 */
	public static final NpcStringId GUARDS_ARE_COMING_RUN;

	/**
	 * ID: 1800121<br>
	 * Message: ������l�ł��E�o�ł��܂��I
	 */
	public static final NpcStringId NOW_I_CAN_ESCAPE_ON_MY_OWN;

	/**
	 * ID: 1800122<br>
	 * Message: �����Ă��������Ă��肪�Ƃ��������܂��I
	 */
	public static final NpcStringId THANKS_FOR_YOUR_HELP;

	/**
	 * ID: 1800123<br>
	 * Message: ����`�[�����X�^�W�A���ւ̓�������𖞂����Ă��炸���Z���L�����Z������܂����B
	 */
	public static final NpcStringId MATCH_CANCELLED_OPPONENT_DID_NOT_MEET_THE_STADIUM_ADMISSION_REQUIREMENTS;

	/**
	 * ID: 1800124<br>
	 * Message: ���͂͂́`���B��ɂɂ̂������܂��Ȃ��玀��ł䂭�������I
	 */
	public static final NpcStringId HA_HA_YES_DIE_SLOWLY_WRITHING_IN_PAIN_AND_AGONY;

	/**
	 * ID: 1800125<br>
	 * Message: �����Ƃ��D�D�D�����Ƌ���ȋ�ɂ��D�D�D�K�v�Ȃ̂��D�D�D
	 */
	public static final NpcStringId MORE_NEED_MORE_SEVERE_PAIN;

	/**
	 * ID: 1800126<br>
	 * Message: �������I�����z������那�����I
	 */
	public static final NpcStringId AHH_MY_LIFE_IS_BEING_DRAINED_OUT;

	/**
	 * ID: 1800127<br>
	 * Message: �M�����̂����̑̂���N���オ���Ă��邼�I
	 */
	public static final NpcStringId SOMETHING_IS_BURNING_INSIDE_MY_BODY;

	/**
	 * ID: 1800128<br>
	 * Message: $s1�A�X�^�W�A���̃��x���ɍ����Ă��܂���B
	 */
	public static final NpcStringId S1_NOT_ADEQUATE_FOR_THE_STADIUM_LEVEL;

	/**
	 * ID: 1800129<br>
	 * Message: $s1�A���O�̖��D�D�D���肪�����󂯎�낤�D�D�D
	 */
	public static final NpcStringId S1_THANK_YOU_FOR_GIVING_ME_YOUR_LIFE;

	/**
	 * ID: 1800130<br>
	 * Message: �_�����A���s���I�_���I���l�A���������������I
	 */
	public static final NpcStringId I_FAILED_PLEASE_FORGIVE_ME_DARION;

	/**
	 * ID: 1800131<br>
	 * Message: $s1�A�K���߂��Ă��邼�D�D�D���S����̂͂܂������D�D�D
	 */
	public static final NpcStringId S1_ILL_BE_BACK_DONT_GET_COMFORTABLE;

	/**
	 * ID: 1800132<br>
	 * Message: ���̎������̂܂܂����Ǝv������D�D�D��ԈႢ���I
	 */
	public static final NpcStringId IF_YOU_THINK_IM_GIVING_UP_LIKE_THIS_YOURE_WRONG;

	/**
	 * ID: 1800133<br>
	 * Message: ������ςȂ��Ŗق��Ă��邩�I
	 */
	public static final NpcStringId SO_YOURE_JUST_GOING_TO_WATCH_ME_SUFFER;

	/**
	 * ID: 1800134<br>
	 * Message: �܂��܂������I
	 */
	public static final NpcStringId ITS_NOT_OVER_YET;

	/**
	 * ID: 1800135<br>
	 * Message: �͂͂��I���ʂ̂�����Ȃɕ|�����D�D�D�ǂ�D�D�D���ԓ��Ɏ���T���o���΁D�D�D�����J���邩���D�D�D�m��ʂ��D�D�D
	 */
	public static final NpcStringId HA_HA_YOU_WERE_SO_AFRAID_OF_DEATH_LET_ME_SEE_IF_YOU_FIND_ME_IN_TIME_MAYBE_YOU_CAN_FIND_A_WAY;

	/**
	 * ID: 1800136<br>
	 * Message: �����Ă��������D�D�D���������̎�����킶��ƍi�߂Ă���D�D�D
	 */
	public static final NpcStringId DONT_KILL_ME_PLEASE_SOMETHINGS_STRANGLING_ME;

	/**
	 * ID: 1800137<br>
	 * Message: ����̃��b�L�[�ȓz�͒N���ȁH�͂́I�y���݂��ȁI
	 */
	public static final NpcStringId WHO_WILL_BE_THE_LUCKY_ONE_TONIGHT_HA_HA_CURIOUS_VERY_CURIOUS;

	/**
	 * ID: 1800138<br>
	 * Message: �`���b�I�`���b�I�s�b�O���g���Ă��X�^����苭�͂����I
	 */
	public static final NpcStringId SQUEAK_THIS_WILL_BE_STRONGER_THAN_THE_STUN_THE_PIG_USED_LAST_TIME;

	/**
	 * ID: 1800139<br>
	 * Message: �`���b�I�`���b�I���@���J�X���K���������ē�����E���g�� �t�B�A�[���I�󂯂Ă݂₪����I
	 */
	public static final NpcStringId SQUEAK_HERE_IT_GOES_EXTREMELY_SCARY_EVEN_TO_VALAKAS;

	/**
	 * ID: 1800140<br>
	 * Message: �`���b�I�`���b�I�ǂ����s���I�����ق��Ƃ��Ă������I
	 */
	public static final NpcStringId SQUEAK_GO_AWAY_LEAVE_US_ALONE;

	/**
	 * ID: 1800141<br>
	 * Message: �`���b�I�`���b�I�݂�ȁA�W�܂ꂥ�I�������̗͂������Ă����I
	 */
	public static final NpcStringId SQUEAK_GUYS_GATHER_UP_LETS_SHOW_OUR_POWER;

	/**
	 * ID: 1800142<br>
	 * Message: �܂��A�ʂɂ��肪�������炠����킯����Ȃ��񂾂��`�`�F�b�I
	 */
	public static final NpcStringId ITS_NOT_LIKE_IM_GIVING_THIS_BECAUSE_IM_GRATEFUL_SQUEAK;

	/**
	 * ID: 1800143<br>
	 * Message: �`���b�I�`���b�I�����肪���������ɂ��񂾂��ǁD�D�D�{���Ɏ��Â��Ă�́I�H
	 */
	public static final NpcStringId SQUEAK_EVEN_IF_IT_IS_TREATMENT_MY_BOTTOM_HURTS_SO_MUCH;

	/**
	 * ID: 1800144<br>
	 * Message: �`���b�I�`���b�I�v���Y�� �X�g�[���ŕϐg���I
	 */
	public static final NpcStringId SQUEAK_TRANSFORM_TO_MOON_CRYSTAL_PRISM_POWER;

	/**
	 * ID: 1800145<br>
	 * Message: �`���b�I�`���b�I������I���̎p�ɖ߂�̌����悧�`�`
	 */
	public static final NpcStringId SQUEAK_OH_NO_I_DONT_WANT_TO_TURN_BACK_AGAIN;

	/**
	 * ID: 1800146<br>
	 * Message: �`���b�I�`���b�I����������������A�����ς���������񂾂�B���肪�Ƃ��ˁB
	 */
	public static final NpcStringId SQUEAK_IM_SPECIALLY_GIVING_YOU_A_LOT_SINCE_IM_RICH_THANK_YOU;

	/**
	 * ID: 1800147<br>
	 * Message: �u�q�I�u�q�I�{�肪���_�܂ŒB�����ł��I�p���[�I�����Ȃ�p���[����I
	 */
	public static final NpcStringId OINK_OINK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER;

	/**
	 * ID: 1800148<br>
	 * Message: �u�q�I�u�q�I�ނ�����ނ����I�����������I
	 */
	public static final NpcStringId OINK_OINK_IM_REALLY_FURIOUS_RIGHT_NOW;

	/**
	 * ID: 1800149<br>
	 * Message: �`���b�I�`���b�I�{�肪���_�܂ŒB�������I�p���[�I�����Ȃ�p���[���I
	 */
	public static final NpcStringId SQUEAK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER;

	/**
	 * ID: 1800150<br>
	 * Message: �`���b�I�`���b�I���������Ȃ�L���Ă邺�I
	 */
	public static final NpcStringId SQUEAK_IM_REALLY_FURIOUS_RIGHT_NOW;

	/**
	 * ID: 1800162<br>
	 * Message: G�O���[�h
	 */
	public static final NpcStringId G_RANK;

	/**
	 * ID: 1800163<br>
	 * Message: �ق��D�D�D�łԂׂ����݂�����قǂ̗͂������Ă��悤�Ƃ͒N�����\�z���Ȃ������낤�B
	 */
	public static final NpcStringId HUH_NO_ONE_WOULD_HAVE_GUESSED_THAT_A_DOOMED_CREATURE_WOULD_BE_SO_POWERFUL;

	/**
	 * ID: 1800164<br>
	 * Message: S�O���[�h
	 */
	public static final NpcStringId S_GRADE;

	/**
	 * ID: 1800165<br>
	 * Message: A�O���[�h
	 */
	public static final NpcStringId A_GRADE;

	/**
	 * ID: 1800166<br>
	 * Message: B�O���[�h
	 */
	public static final NpcStringId B_GRADE;

	/**
	 * ID: 1800167<br>
	 * Message: C�O���[�h
	 */
	public static final NpcStringId C_GRADE;

	/**
	 * ID: 1800168<br>
	 * Message: D�O���[�h
	 */
	public static final NpcStringId D_GRADE;

	/**
	 * ID: 1800169<br>
	 * Message: F�O���[�h
	 */
	public static final NpcStringId F_GRADE;

	/**
	 * ID: 1800170<br>
	 * Message: ����́D�D�D�`���̐^�̉p�Y�łȂ���΂ł��Ȃ��قǂ̈̑�ȋƐт��I
	 */
	public static final NpcStringId THIS_IS_THIS_IS_A_GREAT_ACHIEVEMENT_THAT_IS_WORTHY_OF_THE_TRUE_HEROES_OF_LEGEND;

	/**
	 * ID: 1800171<br>
	 * Message: ���ɂق�ڂ�Ƃ���B�J�}���J��ʂ��Ă���Ă���N���̖��̎��x�点�������ׂ����􂾁B
	 */
	public static final NpcStringId ADMIRABLE_YOU_GREATLY_DECREASED_THE_SPEED_OF_INVASION_THROUGH_KAMALOKA;

	/**
	 * ID: 1800172<br>
	 * Message: ���ɗ��h���B���̖`���Ƃ����̂���{�ɂ���قǂ̎��͂��B
	 */
	public static final NpcStringId VERY_GOOD_YOUR_SKILL_MAKES_YOU_A_MODEL_FOR_OTHER_ADVENTURERS_TO_FOLLOW;

	/**
	 * ID: 1800173<br>
	 * Message: �悭������B���̖`���Ƃ��������O�قǂ̐��ʂ��グ��ꂽ��A���X�Ɋ�]�̌��������Ă��邾�낤�B
	 */
	public static final NpcStringId GOOD_WORK_IF_ALL_ADVENTURERS_PRODUCE_RESULTS_LIKE_YOU_WE_WILL_SLOWLY_START_TO_SEE_THE_GLIMMER_OF_HOPE;

	/**
	 * ID: 1800174<br>
	 * Message: �c�O�����A���� �J�}���J�͒N�ł��C�y�ɋ߂Â��鑶�݂ł͂Ȃ��悤���B
	 */
	public static final NpcStringId UNFORTUNATELY_IT_SEEMS_THAT_RIM_KAMALOKA_CANNOT_BE_EASILY_APPROACHED_BY_EVERYONE;

	/**
	 * ID: 1800175<br>
	 * Message: ���]�����B���O������ �J�}���J�̂Ƃ���ɑ������̂͊ԈႢ�������悤���B
	 */
	public static final NpcStringId HOW_DISAPPOINTING_IT_LOOKS_LIKE_I_MADE_A_MISTAKE_IN_SENDING_YOU_INSIDE_RIM_KAMALOKA;

	/**
	 * ID: 1800176<br>
	 * Message: ����A�ɂイ�A����A����A�ɂイ�A����A���B
	 */
	public static final NpcStringId INTRUDER_ALERT_INTRUDER_ALERT;

	/**
	 * ID: 1800177<br>
	 * Message: �������Ă���񂾁I�������Ǝ���������I
	 */
	public static final NpcStringId WHAT_ARE_YOU_DOING_HURRY_UP_AND_HELP_ME;

	/**
	 * ID: 1800178<br>
	 * Message: �����܂Ŏ���{�点��Ƃ͂ȁD�D�D���������Ⴈ���Ȃ��I
	 */
	public static final NpcStringId IVE_HAD_IT_UP_TO_HERE_WITH_YOU_ILL_TAKE_CARE_OF_YOU;

	/**
	 * ID: 1800179<br>
	 * Message: �����������`�S������Ă����I
	 */
	public static final NpcStringId AH_MY_MIND_IS_A_WRECK;

	/**
	 * ID: 1800180<br>
	 * Message: ���̎艺�̐��Ȃǂ������m��Ă�Ǝv���Ă�Ȃ��ԈႢ���I
	 */
	public static final NpcStringId IF_YOU_THOUGHT_THAT_MY_SUBORDINATES_WOULD_BE_SO_FEW_YOU_ARE_MISTAKEN;

	/**
	 * ID: 1800181<br>
	 * Message: ���܂肨���ɂ͗����Ȃ��ł��傤���A�������������܂��B
	 */
	public static final NpcStringId THERES_NOT_MUCH_I_CAN_DO_BUT_I_WANT_TO_HELP_YOU;

	/**
	 * ID: 1800182<br>
	 * Message: �����A�Ă߂���I$s1�I��ɂ��ڂɂ��킵�Ă��ȁI
	 */
	public static final NpcStringId YOU_S1_ATTACK_THEM;

	/**
	 * ID: 1800183<br>
	 * Message: �o�ł�A�䂪�艺�ǂ���I���̖������茸�炵�Ă��O����Ă񂾂̂��B�����̂Ă�o��ł������Еt����I
	 */
	public static final NpcStringId COME_OUT_MY_SUBORDINATE_I_SUMMON_YOU_TO_DRIVE_THEM_OUT;

	/**
	 * ID: 1800184<br>
	 * Message: �債�����Ƃ͂ł��܂��񂪁A���̖������Ăł����͂ɂȂ�܂��B
	 */
	public static final NpcStringId THERES_NOT_MUCH_I_CAN_DO_BUT_I_WILL_RISK_MY_LIFE_TO_HELP_YOU;

	/**
	 * ID: 1800185<br>
	 * Message: �������I�����͒ɂ����I
	 */
	public static final NpcStringId ARG_THE_PAIN_IS_MORE_THAN_I_CAN_STAND;

	/**
	 * ID: 1800186<br>
	 * Message: ���������I���̋}�����ǂ����āD�D�D
	 */
	public static final NpcStringId AHH_HOW_DID_HE_FIND_MY_WEAKNESS;

	/**
	 * ID: 1800187<br>
	 * Message: ���O���|�����J�i�r�I��Ăǂ�����J�}���J�̃G�b�Z���X���W�߂�̂ɐ����������B�����A���ꂾ�B
	 */
	public static final NpcStringId WE_WERE_ABLE_TO_SUCCESSFULLY_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE;

	/**
	 * ID: 1800188<br>
	 * Message: �����A���O���|�����J�i�r�I���ǂ�����J�}���J�̃G�b�Z���X������Ȃ�ɏW�߂邱�Ƃ��ł����B�����A���ꂾ�B
	 */
	public static final NpcStringId BUT_WE_WERE_ABLE_TO_COLLECT_SOMEHOW_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE;

	/**
	 * ID: 1800189<br>
	 * Message: ���O���|�����J�i�r�I���ǂ��ɏh���Ă���ł̃I�[���͔���ł������B�������J�}���J�̃G�b�Z���X���W�߂�̂Ɏ��s�����悤���B
	 */
	public static final NpcStringId IM_SORRY_BUT_WE_WERE_UNABLE_TO_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_BECAUSE_THEIR_DARK_ENERGY_WAS_TOO_WEAK;

	/**
	 * ID: 1800190<br>
	 * Message: �P�ɓG��|���������ł͂Ȃ��A��X������]��ł���̂������O�͏[���킩���Ă���悤���ȁB
	 */
	public static final NpcStringId RATHER_THAN_SIMPLY_DEFEATING_THE_ENEMIES_YOU_SEEM_TO_UNDERSTAND_OUR_GOAL_AND_PURPOSE_AS_WELL;

	/**
	 * ID: 1800191<br>
	 * Message: �h�b�v���[��{�C�h�̓J�i�r�I���̈ł̃I�[������w�������ꂽ�`���B�J�}���J�̍U����H���~�߂邽�߂ɂ́A����|�����ƂɈ�w�͂����˂΂Ȃ�Ȃ��B
	 */
	public static final NpcStringId DOPPLERS_AND_VOIDS_POSSESS_AN_ENHANCED_AMOUNT_OF_THE_KANABIONS_DARK_ENERGY_SO_IT_IS_IMPORTANT_TO_CONCENTRATE_ON_DEFEATING_THEM_WHEN_BLOCKING_THE_KAMALOKIANS_ATTACK;

	/**
	 * ID: 1800192<br>
	 * Message: �J�i�r�I�����V���ȃJ�i�r�I��������Ă���̂��������Ƃ����邩�B�U���̂Ƃ���|���Ƃ��ɁA�ő�̃_���[�W��^����ƁA����ȏ�ʂɏo���킷���Ƃ�����B
	 */
	public static final NpcStringId HAVE_YOU_SEEN_KANABIONS_BEING_REMADE_AS_NEW_KANABIONS_SOMETIMES_YOU_CAN_SEE_IT_OCCUR_MORE_OFTEN_BY_INFLICTING_GREAT_DAMAGE_DURING_AN_ATTACK_OR_AT_THE_MOMENT_YOU_DEFEAT_THEM;

	/**
	 * ID: 1800193<br>
	 * Message: �ǂ�Ȑ퓬�ɂ����Ă������ł���悤�ɁA���� �J�}���J�����ł���͂莩������邱�Ƃ��ŗD��Ȃ̂��B�����Y���łȂ��B��X�͖��d�ȃ`�������W�Ȃǖ]��ł͂��Ȃ��̂��B
	 */
	public static final NpcStringId AS_IN_ANY_OTHER_BATTLE_IT_IS_CRITICAL_TO_PROTECT_YOURSELF_WHILE_YOU_ARE_INSIDE_RIM_KAMALOKA_WE_DO_NOT_WANT_TO_ATTACK_RECKLESSLY;

	/**
	 * ID: 1800194<br>
	 * Message: ��X�͈�x�̏������A���S�Ȃ��l�̐�m����Ă邱�Ƃɉ��l��u���Ă���̂��B�������񑼐l�ɗ����Ă��܂����Ȃ�A���͎����̗͂����Ń`�������W���Ă݂�̂͂ǂ����H
	 */
	public static final NpcStringId WE_VALUE_DEVELOPING_AN_INDIVIDUALS_OVERALL_POWER_RATHER_THAN_A_ONE_TIME_VICTORY_IF_YOU_RELIED_ON_ANOTHER_PERSONS_SUPPORT_THIS_TIME_WHY_DONT_YOU_TRY_TO_RELY_ON_YOUR_OWN_STRENGTH_NEXT_TIME;

	/**
	 * ID: 1800195<br>
	 * Message: �����قǂ̐퓬�������̃��x���ɍ����Ă���Ǝv���Ă���̂��B���V�тŃ��x���̒Ⴂ�J�i�r�I�������ɑ���ɂ���̂́A��̖͂��ʂɌ����邪�ȁB
	 */
	public static final NpcStringId ARE_YOU_SURE_THAT_THE_BATTLE_JUST_NOW_WAS_AT_THE_APPROPRIATE_LEVEL_BOTHERING_LOWER_KANABIONS_AS_IF_FOR_MERE_ENTERTAINMENT_IS_CONSIDERED_TO_BE_A_WASTED_BATTLE_FOR_US;

	/**
	 * ID: 1800196<br>
	 * Message: �ō��̏����Ƃ͗^����ꂽ��i�𑍓������āA����Ƀ`�����X��^�����A�ł��邾�������I��点�邱�Ƃ��B�����v��Ȃ����B
	 */
	public static final NpcStringId THE_GREATEST_VICTORY_INVOLVES_USING_ALL_AVAILABLE_RESOURCES_ELIMINATING_ALL_OF_THE_ENEMYS_OPPORTUNITIES_AND_BRINGING_IT_TO_THE_FASTEST_POSSIBLE_END_DONT_YOU_THINK_SO;

	/**
	 * ID: 1800197<br>
	 * Message: ��펖�Ԕ����I�O�Ǒϋv�x���}���ɗ����Ă���I
	 */
	public static final NpcStringId EMERGENCY_EMERGENCY_THE_OUTER_WALL_IS_WEAKENING_RAPIDLY;

	/**
	 * ID: 1800198<br>
	 * Message: �O�Ǒϋv�x�c��$s1�I
	 */
	public static final NpcStringId THE_REMAINING_STRENGTH_OF_THE_OUTER_WALL_IS_S1;

	/**
	 * ID: 1800199<br>
	 * Message: �p�X�t�@�C���_�[�̋~����
	 */
	public static final NpcStringId PATHFINDER_SAVIOR;

	/**
	 * ID: 1800200<br>
	 * Message: �p�X�t�@�C���_�[�̋��͎�
	 */
	public static final NpcStringId PATHFINDER_SUPPORTER;

	/**
	 * ID: 1800201<br>
	 * Message: �܂��V�������̂Ɋ��ꂫ���Ă��Ȃ��ꕔ�̃J�i�r�I�������́A�g�̍\�������ɂ��낭�Ȃ邱�Ƃ�����B���̂Ƃ���_���čU������΁A���Ɍ��ʂ��グ����񂾁B
	 */
	public static final NpcStringId SOME_KANABIONS_WHO_HAVENT_FULLY_ADJUSTED_YET_TO_THEIR_NEW_PHYSICAL_FORM_ARE_KNOWN_TO_EXHIBIT_SYMPTOMS_OF_AN_EXTREMELY_WEAKENED_BODY_STRUCTURE_SOMETIMES_IF_YOU_ATTACK_THEM_AT_THAT_MOMENT_YOU_WILL_HAVE_GREAT_RESULTS;

	/**
	 * ID: 1800202<br>
	 * Message: $s1�̂��Ƃ͕��������Ƃ��邩�B�܂��ɐ^��$s2���I
	 */
	public static final NpcStringId HAVE_YOU_EVER_HEARD_OF_S1_THEY_SAY_ITS_A_GENUINE_S2;

	/**
	 * ID: 1800203<br>
	 * Message: �N���e�̃L���[�u�̋��Z�̐\�����ݏI���܂ł���5���ł��B
	 */
	public static final NpcStringId THERE_ARE_5_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH;

	/**
	 * ID: 1800204<br>
	 * Message: �N���e�̃L���[�u�̋��Z�̐\�����ݏI���܂ł���3���ł��B
	 */
	public static final NpcStringId THERE_ARE_3_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH;

	/**
	 * ID: 1800205<br>
	 * Message: �N���e�̃L���[�u�̋��Z�̐\�����ݏI���܂ł���1���ł��B
	 */
	public static final NpcStringId THERE_ARE_1_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH;

	/**
	 * ID: 1800207<br>
	 * Message: �܂��Ȃ����Z���n�܂�܂��B
	 */
	public static final NpcStringId THE_MATCH_WILL_BEGIN_SHORTLY;

	/**
	 * ID: 1800209<br>
	 * Message: �S�I�I�I�I�H�D�D�D
	 */
	public static final NpcStringId OHHOHOH;

	/**
	 * ID: 1800210<br>
	 * Message: ��
	 */
	public static final NpcStringId FIRE;

	/**
	 * ID: 1800211<br>
	 * Message: ��
	 */
	public static final NpcStringId WATER;

	/**
	 * ID: 1800212<br>
	 * Message: ��
	 */
	public static final NpcStringId WIND;

	/**
	 * ID: 1800213<br>
	 * Message: �n
	 */
	public static final NpcStringId EARTH;

	/**
	 * ID: 1800214<br>
	 * Message: $s1���D�D�D
	 */
	public static final NpcStringId ITS_S1;

	/**
	 * ID: 1800215<br>
	 * Message: $s1�ɋ����D�D�D
	 */
	public static final NpcStringId S1_IS_STRONG;

	/**
	 * ID: 1800216<br>
	 * Message: $s1�����m��Ȃ��̂��D�D�D
	 */
	public static final NpcStringId ITS_ALWAYS_S1;

	/**
	 * ID: 1800217<br>
	 * Message: $s1�͂��߂��D�D�D
	 */
	public static final NpcStringId S1_WONT_DO;

	/**
	 * ID: 1800218<br>
	 * Message: �󕨂Ɏ���o�����Ƃ���҂ɂ͎􂢂������邾�낤�I
	 */
	public static final NpcStringId YOU_WILL_BE_CURSED_FOR_SEEKING_THE_TREASURE;

	/**
	 * ID: 1800219<br>
	 * Message: ��s�D����������܂����B5����Ɏ����I�ɏo�����܂��B
	 */
	public static final NpcStringId THE_AIRSHIP_HAS_BEEN_SUMMONED_IT_WILL_AUTOMATICALLY_DEPART_IN_5_MINUTES;

	/**
	 * ID: 1800220<br>
	 * Message: ����֔�s�D���������܂����B1����ɃA�f���Ɍ����ďo�����܂��B
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_ADEN_CONTINENT_IN_1_MINUTE;

	/**
	 * ID: 1800221<br>
	 * Message: �A�f���Ɍ��������s�D���o�����܂����B
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_ADEN_CONTINENT_HAS_DEPARTED;

	/**
	 * ID: 1800222<br>
	 * Message: ����֔�s�D���������܂����B1����ɃO���V�A�Ɍ����ďo�����܂��B
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_GRACIA_CONTINENT_IN_1_MINUTE;

	/**
	 * ID: 1800223<br>
	 * Message: �O���V�A�Ɍ�������֔�s�D���o�����܂����B
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_GRACIA_CONTINENT_HAS_DEPARTED;

	/**
	 * ID: 1800224<br>
	 * Message: ������ɂ͑��̔�s�D����������Ă��܂��B�܂���قǂ����p���������B
	 */
	public static final NpcStringId ANOTHER_AIRSHIP_HAS_BEEN_SUMMONED_TO_THE_WHARF_PLEASE_TRY_AGAIN_LATER;

	/**
	 * ID: 1800225<br>
	 * Message: �����H�������󂪕ς��ȁD�D�D�����I�����A����́I
	 */
	public static final NpcStringId HUH_THE_SKY_LOOKS_FUNNY_WHATS_THAT;

	/**
	 * ID: 1800226<br>
	 * Message: �o���A �I�[�u�ɕ��ׂ������肷���Ă���B���̔����͂����炭�D�D�D
	 */
	public static final NpcStringId A_POWERFUL_SUBORDINATE_IS_BEING_HELD_BY_THE_BARRIER_ORB_THIS_REACTION_MEANS;

	/**
	 * ID: 1800227<br>
	 * Message: �C������I�������邼�I
	 */
	public static final NpcStringId BE_CAREFUL_SOMETHINGS_COMING;

	/**
	 * ID: 1800228<br>
	 * Message: �܂��͌�����n�݂��邩�A�����ɉ������Ȃ���΂Ȃ�܂���B
	 */
	public static final NpcStringId YOU_MUST_FIRST_FOUND_A_CLAN_OR_BELONG_TO_ONE;

	/**
	 * ID: 1800229<br>
	 * Message: �G�L���X�ɒ��풆�̃p�[�e�B�͂���܂���B$s1�b�ȓ��ɓ���Ȃ���΁A�s�ł̐S�����ւ̍U���͎��s�ƂȂ�܂��B
	 */
	public static final NpcStringId THERE_IS_NO_PARTY_CURRENTLY_CHALLENGING_EKIMUS_N_IF_NO_PARTY_ENTERS_WITHIN_S1_SECONDS_THE_ATTACK_ON_THE_HEART_OF_IMMORTALITY_WILL_FAIL;

	/**
	 * ID: 1800230<br>
	 * Message: ��ᇑ̂ƂȂ������G�L���X�͂���ɋ��͂ȗ͂𓾂܂����B
	 */
	public static final NpcStringId EKIMUS_HAS_GAINED_STRENGTH_FROM_A_TUMOR;

	/**
	 * ID: 1800231<br>
	 * Message: ��ᇑ̂Ƃ̂Ȃ��肪�؂ꂽ�G�L���X���͂������܂����B
	 */
	public static final NpcStringId EKIMUS_HAS_BEEN_WEAKENED_BY_LOSING_STRENGTH_FROM_A_TUMOR;

	/**
	 * ID: 1800233<br>
	 * Message: ����[���I�o�Ă����A���̃l�Y�~��Y�I�Ă߂���̈��s�A���̖ڂł������ƌ��Ă��I
	 */
	public static final NpcStringId CMON_CMON_SHOW_YOUR_FACE_YOU_LITTLE_RATS_LET_ME_SEE_WHAT_THE_DOOMED_WEAKLINGS_ARE_SCHEMING;

	/**
	 * ID: 1800234<br>
	 * Message: �Ȃ��Ȃ��̂��񂾂ȁA�t�t�t�D�D�D�����ƁA�����Ȃ��B�M���Ȃ�߂����ȁB�A���W�F�N���e�A�����J����I
	 */
	public static final NpcStringId IMPRESSIVE_HAHAHA_ITS_SO_MUCH_FUN_BUT_I_NEED_TO_CHILL_A_LITTLE_WHILE_ARGEKUNTE_CLEAR_THE_WAY;

	/**
	 * ID: 1800235<br>
	 * Message: ���͂͂͂͂��I��ᇑ̂������Ԃ������A���͂�Ă߂���̑��������K�v�͂Ȃ��������ȁB
	 */
	public static final NpcStringId KYAHAHA_SINCE_THE_TUMOR_HAS_BEEN_RESURRECTED_I_NO_LONGER_NEED_TO_WASTE_MY_TIME_ON_YOU;

	/**
	 * ID: 1800236<br>
	 * Message: �����I�����͂��ꂮ�炢�ɂ��Ƃ��Ă�邪�A����ŏI��肾�Ǝv���Ȃ�I�s�ł̎�͕����ʂ�i���ɕs�ł��I
	 */
	public static final NpcStringId KEU_I_WILL_LEAVE_FOR_NOW_BUT_DONT_THINK_THIS_IS_OVER_THE_SEED_OF_INFINITY_CAN_NEVER_DIE;

	/**
	 * ID: 1800237<br>
	 * Message: ���͂͂͂͂��I�����͊O�������ł�����ۂ��B���̋������Ȃ���Ύ��ʂ��Ƃ���ł��Ȃ��B����A��`�̖��@�A�f�X���X �K�[�f�B�A�����I
	 */
	public static final NpcStringId KAHAHAHA_THAT_GUYS_NOTHING_HE_CANT_EVEN_KILL_WITHOUT_MY_PERMISSION_SEE_HERE_ULTIMATE_FORGOTTEN_MAGIC_DEATHLESS_GUARDIAN;

	/**
	 * ID: 1800238<br>
	 * Message: ��������Ă��O�̓z��ɐ��艺���������̓��̋��J�͉i���ɖY��Ȃ��B�R�w���l�X�I�Ă߂��̑��̍����~�܂�u�Ԃ����̖ڂŌ��͂��Ă��I
	 */
	public static final NpcStringId I_CURSE_THE_DAY_THAT_I_BECAME_YOUR_SLAVE_IN_ORDER_TO_ESCAPE_DEATH_COHEMENES_I_SWEAR_THAT_I_SHALL_SEE_YOU_DIE_WITH_MY_OWN_EYES;

	/**
	 * ID: 1800239<br>
	 * Message: ���̂����������ɂ��������I���������킭�킭���邺�I�����A�c���A�c���I
	 */
	public static final NpcStringId MY_ENEMY_IS_DYING_AND_MY_BLOOD_IS_BOILING_WHAT_CRUEL_CURSE_IS_THIS;

	/**
	 * ID: 1800240<br>
	 * Message: ��ɂ̊���
	 */
	public static final NpcStringId HALL_OF_SUFFERING;

	/**
	 * ID: 1800241<br>
	 * Message: �N�H�̊���
	 */
	public static final NpcStringId HALL_OF_EROSION;

	/**
	 * ID: 1800242<br>
	 * Message: �s�ł̐S����
	 */
	public static final NpcStringId HEART_OF_IMMORTALITY;

	/**
	 * ID: 1800243<br>
	 * Message: �U��
	 */
	public static final NpcStringId ATTACK;

	/**
	 * ID: 1800244<br>
	 * Message: �h��
	 */
	public static final NpcStringId DEFEND;

	/**
	 * ID: 1800245<br>
	 * Message: ���߂łƂ��������܂��I$s1 $s2�ɐ������܂����I�܂��Ȃ��C���X�^���g �]�[�������ł��܂��B
	 */
	public static final NpcStringId CONGRATULATIONS_YOU_HAVE_SUCCEEDED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE;

	/**
	 * ID: 1800246<br>
	 * Message: $s1 $s2�Ɏ��s���܂����D�D�D�܂��Ȃ��C���X�^���g �]�[�������ł��܂��B
	 */
	public static final NpcStringId YOU_HAVE_FAILED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE;

	/**
	 * ID: 1800247<br>
	 * Message: $s1�̃p�[�e�B�͎�ᇑ̂̂����܂����蔲���āA���̂Ƃ���Ɉړ����܂����B
	 */
	public static final NpcStringId S1S_PARTY_HAS_MOVED_TO_A_DIFFERENT_LOCATION_THROUGH_THE_CRACK_IN_THE_TUMOR;

	/**
	 * ID: 1800248<br>
	 * Message: $s1�̃p�[�e�B�͎�ᇑ̂̂����܂����蔲���āA�G�L���X�̕����ɓ���܂����B
	 */
	public static final NpcStringId S1S_PARTY_HAS_ENTERED_THE_CHAMBER_OF_EKIMUS_THROUGH_THE_CRACK_IN_THE_TUMOR;

	/**
	 * ID: 1800249<br>
	 * Message: �G�L���X���ُ�s�������m���܂����B���풆�̃p�[�e�B�͋����Ǖ�����܂��B
	 */
	public static final NpcStringId EKIMUS_HAS_SENSED_ABNORMAL_ACTIVITY_NTHE_ADVANCING_PARTY_IS_FORCEFULLY_EXPELLED;

	/**
	 * ID: 1800250<br>
	 * Message: �A�C�e�����s�����Ă��܂��B��s�D�̏����ɂ̓G�l���M�[ �X�^�[�X�g�[��5���K�v�ł��B
	 */
	public static final NpcStringId THERE_ARENT_ENOUGH_ITEMS_IN_ORDER_TO_SUMMON_THE_AIRSHIP_YOU_NEED_5_ENERGY_STAR_STONES;

	/**
	 * ID: 1800251<br>
	 * Message: �Ō�܂Ő����c�����Đ��̎��H���s���񂪂��߂ɁA�H���Ӓn�̒��������̕ߐH�҂��ڊo�߂܂����D�D�D
	 */
	public static final NpcStringId THE_SOUL_DEVOURERS_WHO_ARE_GREEDY_TO_EAT_THE_SEEDS_OF_LIFE_THAT_REMAIN_ALIVE_UNTIL_THE_END_HAVE_AWAKENED;

	/**
	 * ID: 1800252<br>
	 * Message: 1�C�ڂ̖��E�̖������ڊo�߂܂����D�D�D
	 */
	public static final NpcStringId THE_FIRST_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED;

	/**
	 * ID: 1800253<br>
	 * Message: 2�C�ڂ̖��E�̖������ڊo�߂܂����D�D�D
	 */
	public static final NpcStringId THE_SECOND_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED;

	/**
	 * ID: 1800254<br>
	 * Message: �t���Ă��Ă����ʂ��I��`�̖��@�A�u���C�h �^�[�������炦���I
	 */
	public static final NpcStringId CLINGING_ON_WONT_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_BLADE_TURN;

	/**
	 * ID: 1800255<br>
	 * Message: ����ȂƂ���ł����炠�����Ă��ʂ��Ȃ����I��`�̖��@�A�t�H�[�X �V�[���h�����炦���I
	 */
	public static final NpcStringId EVEN_SPECIAL_SAUCE_CANT_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_FORCE_SHIELD;

	/**
	 * ID: 1800256<br>
	 * Message: ���̒�����ǂ��߁I���ɂȂ��Ă������Ă����Ƃ���ŁA�s�ł̗͂͋����Ȃ�΂��肾�I
	 */
	public static final NpcStringId YOU_LITTLE_DOOMED_MAGGOTS_EVEN_IF_YOU_KEEP_SWARMING_THE_POWER_OF_IMMORTALITY_WILL_ONLY_GROW_STRONGER;

	/**
	 * ID: 1800257<br>
	 * Message: ��s�D�̏������������͂ł��܂����B�M�����͍���A��s�D�̏������ł���悤�ɂȂ�܂��B
	 */
	public static final NpcStringId THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_AWARDED_YOUR_CLAN_CAN_NOW_SUMMON_AN_AIRSHIP;

	/**
	 * ID: 1800258<br>
	 * Message: �O���V�A�󔠂�����܂����B
	 */
	public static final NpcStringId THE_GRACIA_TREASURE_BOX_HAS_APPEARED;

	/**
	 * ID: 1800259<br>
	 * Message: �O���V�A�󔠂��܂��Ȃ����ł��܂��B
	 */
	public static final NpcStringId THE_GRACIA_TREASURE_BOX_WILL_SOON_DISAPPEAR;

	/**
	 * ID: 1800260<br>
	 * Message: ��ᇑ̂��􂢂ɂ�������$s1�̃_���[�W����܂����B
	 */
	public static final NpcStringId YOU_HAVE_BEEN_CURSED_BY_THE_TUMOR_AND_HAVE_INCURRED_S1_DAMAGE;

	/**
	 * ID: 1800261<br>
	 * Message: �Ă߂��̒���A�󂯂ė��I$s1�I�s�ł̉��ɕ�����Đ����ȁI
	 */
	public static final NpcStringId I_SHALL_ACCEPT_YOUR_CHALLENGE_S1_COME_AND_DIE_IN_THE_ARMS_OF_IMMORTALITY;

	/**
	 * ID: 1800262<br>
	 * Message: �܂��Ȃ�$s1 $s2�ɎQ�����܂��B���S�̏��������肢���܂��B
	 */
	public static final NpcStringId YOU_WILL_PARTICIPATE_IN_S1_S2_SHORTLY_BE_PREPARED_FOR_ANYTHING;

	/**
	 * ID: 1800263<br>
	 * Message: �G�L���X�̃A���f�b�h������Ă��鑫�����������܂��B$s1 $s2�B���n�܂�܂����B
	 */
	public static final NpcStringId YOU_CAN_HEAR_THE_UNDEAD_OF_EKIMUS_RUSHING_TOWARD_YOU_S1_S2_IT_HAS_NOW_BEGUN;

	/**
	 * ID: 1800264<br>
	 * Message: ��ᇑ̂Ɏ��̃G�l���M�[���������邱�Ƃ��������܂��B
	 */
	public static final NpcStringId YOU_CAN_FEEL_THE_SURGING_ENERGY_OF_DEATH_FROM_THE_TUMOR;

	/**
	 * ID: 1800265<br>
	 * Message: ��ᇑ̂̂܂��ɕs�g�ȋC���������ӂ�Ă��܂��B
	 */
	public static final NpcStringId THE_AREA_NEAR_THE_TUMOR_IS_FULL_OF_OMINOUS_ENERGY;

	/**
	 * ID: 1800266<br>
	 * Message: ��X���܂����Ƃ́A�ǂ��܂ł�����������߁I
	 */
	public static final NpcStringId YOU_TRIED_TO_DROP_US_HOW_STUPID;

	/**
	 * ID: 1800267<br>
	 * Message: ��X�͌��𕪂����Z�킾�B���u���Đ�ɐ����킯�ɂ͂����Ȃ��B
	 */
	public static final NpcStringId WE_ARE_BLOOD_BRETHREN_I_CANT_FALL_SO_EASILY_HERE_AND_LEAVE_MY_BROTHER_BEHIND;

	/**
	 * ID: 1800268<br>
	 * Message: �q���̍�����Z�ɓ���Ă����B���̌Z��u���Đ�ɐ����킯�ɂ͂����Ȃ��B
	 */
	public static final NpcStringId YOU_WERE_ALWAYS_WHAT_I_ASPIRED_TO_BE_DO_YOU_THINK_I_WOULD_FALL_SO_EASILY_HERE_WHEN_I_HAVE_A_BROTHER_LIKE_THAT;

	/**
	 * ID: 1800269<br>
	 * Message: ��ᇑ̂Ƃ̂Ȃ��肪�؂ꂽ�G�L���X�̖����ɑ΂��铝���͂������܂����B
	 */
	public static final NpcStringId WITH_ALL_CONNECTIONS_TO_THE_TUMOR_SEVERED_EKIMUS_HAS_LOST_ITS_POWER_TO_CONTROL_THE_FERAL_HOUND;

	/**
	 * ID: 1800270<br>
	 * Message: ��ᇑ̂ƍĂтȂ����ė͂����߂����G�L���X�������ɑ΂��铝���͂����߂��܂����B
	 */
	public static final NpcStringId WITH_THE_CONNECTION_TO_THE_TUMOR_RESTORED_EKIMUS_HAS_REGAINED_CONTROL_OVER_THE_FERAL_HOUND;

	/**
	 * ID: 1800271<br>
	 * Message: ��������������I
	 */
	public static final NpcStringId WOOOONG;

	/**
	 * ID: 1800272<br>
	 * Message: ������� ��������� ��������
	 */
	public static final NpcStringId WOONG_WOONG_WOO;

	/**
	 * ID: 1800273<br>
	 * Message: �G�R���P�I�����퓬�̐��ɂ��I���͂��I
	 */
	public static final NpcStringId THE_ENEMIES_HAVE_ATTACKED_EVERYONE_COME_OUT_AND_FIGHT_URGH;

	/**
	 * ID: 1800274<br>
	 * Message: $s1���̎�ᇑ̂��j�󂳂�܂����I�ڋ��ȃR�w���l�X�������o�����߂ɂ͂��ׂĂ̎�ᇑ̂�j�󂵂Ȃ���΂Ȃ�܂���B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NIN_ORDER_TO_DRAW_OUT_THE_COWARDLY_COHEMENES_YOU_MUST_DESTROY_ALL_THE_TUMORS;

	/**
	 * ID: 1800275<br>
	 * Message: $s1���̎�ᇑ̂����S�ɕ������܂����B�͂����߂����R�w���l�X�͎�̉��[���ɓ����Ă����܂��B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NTHE_RESTRENGTHENED_COHEMENES_HAS_FLED_DEEPER_INSIDE_THE_SEED;

	/**
	 * ID: 1800276<br>
	 * Message: ��s�D�������������łɊl�����Ă��܂��B
	 */
	public static final NpcStringId THE_AWARDED_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_RECEIVED;

	/**
	 * ID: 1800277<br>
	 * Message: ��s�D��������������܂���B����̓��R�����甭�����Ă��炦�܂��B
	 */
	public static final NpcStringId YOU_DO_NOT_CURRENTLY_HAVE_AN_AIRSHIP_SUMMON_LICENSE_YOU_CAN_EARN_YOUR_AIRSHIP_SUMMON_LICENSE_THROUGH_ENGINEER_LEKON;

	/**
	 * ID: 1800278<br>
	 * Message: ��s�D���������͂��łɓ��͂���Ă��܂��B
	 */
	public static final NpcStringId THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_AWARDED;

	/**
	 * ID: 1800279<br>
	 * Message: ���ł���������A�C�e�������`
	 */
	public static final NpcStringId IF_YOU_HAVE_ITEMS_PLEASE_GIVE_THEM_TO_ME;

	/**
	 * ID: 1800280<br>
	 * Message: ���������Ă񂾂�I
	 */
	public static final NpcStringId MY_STOMACH_IS_EMPTY;

	/**
	 * ID: 1800281<br>
	 * Message: ���������I���V�H�킹��I
	 */
	public static final NpcStringId IM_HUNGRY_IM_HUNGRY;

	/**
	 * ID: 1800282<br>
	 * Message: ����������Ȃ��Ȃ��B
	 */
	public static final NpcStringId IM_STILL_NOT_FULL;

	/**
	 * ID: 1800283<br>
	 * Message: �܂��������Ă邼�I
	 */
	public static final NpcStringId IM_STILL_HUNGRY;

	/**
	 * ID: 1800284<br>
	 * Message: ������Ƃ͕����c��񂾂��ǂȂ��D�D�D
	 */
	public static final NpcStringId I_FEEL_A_LITTLE_WOOZY;

	/**
	 * ID: 1800285<br>
	 * Message: �H�����̂����I
	 */
	public static final NpcStringId GIVE_ME_SOMETHING_TO_EAT;

	/**
	 * ID: 1800286<br>
	 * Message: ���ĂƁA�{�i�I�ɐH���Ƃ��邩�B
	 */
	public static final NpcStringId NOW_ITS_TIME_TO_EAT;

	/**
	 * ID: 1800287<br>
	 * Message: �f�U�[�g���Y���Ȃ�ȁB
	 */
	public static final NpcStringId I_ALSO_NEED_A_DESSERT;

	/**
	 * ID: 1800289<br>
	 * Message: �������Ȃ������ς��B
	 */
	public static final NpcStringId IM_FULL_NOW_I_DONT_WANT_TO_EAT_ANYMORE;

	/**
	 * ID: 1800290<br>
	 * Message: �H������˂�����A�͂��o�˂���I
	 */
	public static final NpcStringId I_HAVENT_EATEN_ANYTHING_IM_SO_WEAK;

	/**
	 * ID: 1800291<br>
	 * Message: �ނ���ނ���
	 */
	public static final NpcStringId YUM_YUM_YUM_YUM;

	/**
	 * ID: 1800292<br>
	 * Message: �V�[���h�̐��Ȃ��͂ɐG�ꂽ��ᇑ̂̊O�炪������$s1�̃_���[�W����܂����B
	 */
	public static final NpcStringId YOUVE_SUSTAINED_S1_DAMAGE_AS_TUMORS_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD;

	/**
	 * ID: 1800293<br>
	 * Message: �V�[���h�̐��Ȃ��͂ɐG�ꂽ���̊ǂ̊O�炪������$s1�̃_���[�W����܂����B
	 */
	public static final NpcStringId YOUVE_SUSTAINED_S1_DAMAGE_AS_SOUL_COFFINS_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD;

	/**
	 * ID: 1800295<br>
	 * Message: �I�x���X�N�����󂵂��B���͂�G���̂��΂�̂����Ă��Ȃ��I
	 */
	public static final NpcStringId OBELISK_HAS_COLLAPSED_DONT_LET_THE_ENEMIES_JUMP_AROUND_WILDLY_ANYMORE;

	/**
	 * ID: 1800296<br>
	 * Message: �G���v�ǂ��U�ߗ��Ƃ����Ƃ��Ă���B�͂�s�����Ėh���I
	 */
	public static final NpcStringId ENEMIES_ARE_TRYING_TO_DESTROY_THE_FORTRESS_EVERYONE_DEFEND_THE_FORTRESS;

	/**
	 * ID: 1800297<br>
	 * Message: �o�ł�A��m������I�j�ł̎�����B
	 */
	public static final NpcStringId COME_OUT_WARRIORS_PROTECT_SEED_OF_DESTRUCTION;

	/**
	 * ID: 1800298<br>
	 * Message: �Đ��̎킪�G�L���X�̃A���f�b�h����U������Ă��܂��B�Đ��̎킪��ł��j�󂳂ꂽ��A�N�H�̊����̖h�䂪���s���܂��B
	 */
	public static final NpcStringId THE_UNDEAD_OF_EKIMUS_IS_ATTACKING_SEED_OF_LIFE_DEFENDING_HALL_OF_EROSION_WILL_FAIL_EVEN_IF_ONE_SEED_OF_LIFE_IS_DESTROYED;

	/**
	 * ID: 1800299<br>
	 * Message: $s1���̎�ᇑ̂��j�󂳂�܂����I���n�ɒǂ����ꂽ�R�w���l�X���ׂ̒n��Ɍ���܂����B
	 */
	public static final NpcStringId ALL_THE_TUMORS_INSIDE_S1_HAVE_BEEN_DESTROYED_DRIVEN_INTO_A_CORNER_COHEMENES_APPEARS_CLOSE_BY;

	/**
	 * ID: 1800300<br>
	 * Message: $s1���̎�ᇑ̂��j�󂳂�܂����I�ׂ̍Đ��̎���U�����Ă����A���f�b�h���͂������ē����Ă����܂��B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_NEARBY_UNDEAD_THAT_WERE_ATTACKING_SEED_OF_LIFE_START_LOSING_THEIR_ENERGY_AND_RUN_AWAY;

	/**
	 * ID: 1800301<br>
	 * Message: $s1���̎�ᇑ̂����S�ɕ������܂����B�͂����߂����A���f�b�h���ׂ̍Đ��̎�ɉ����񂹂܂��B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NRECOVERED_NEARBY_UNDEAD_ARE_SWARMING_TOWARD_SEED_OF_LIFE;

	/**
	 * ID: 1800302<br>
	 * Message: �G�L���X�ɗ͂��������Ă���$s1���̎�ᇑ̂��j�󂳂�܂����I
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_THAT_HAS_PROVIDED_ENERGY_N_TO_EKIMUS_IS_DESTROYED;

	/**
	 * ID: 1800303<br>
	 * Message: $s1���̎�ᇑ̂����S�ɕ������āA�G�L���X�ɗ͂��������n�߂܂����B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_COMPLETELY_RESURRECTED_N_AND_STARTED_TO_ENERGIZE_EKIMUS_AGAIN;

	/**
	 * ID: 1800304<br>
	 * Message: $s1���̎�ᇑ̂��j�󂳂�܂����I�G�L���X���a�����т��񂹂�X�s�[�h���x���Ȃ�܂����B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_SPEED_THAT_EKIMUS_CALLS_OUT_HIS_PREY_HAS_SLOWED_DOWN;

	/**
	 * ID: 1800305<br>
	 * Message: $s1���̎�ᇑ̂����S�ɕ������܂����B�͂����߂����G�L���X������ɋ}���ŉa��T���܂��B
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NEKIMUS_STARTED_TO_REGAIN_HIS_ENERGY_AND_IS_DESPERATELY_LOOKING_FOR_HIS_PREY;

	/**
	 * ID: 1800306<br>
	 * Message: ���A�����ƍ�������I
	 */
	public static final NpcStringId BRING_MORE_MORE_SOULS;

	/**
	 * ID: 1800307<br>
	 * Message: ���΂₭��ᇑ̂��U�����Ȃ���΁A�N�H�̊����̍U���Ɏ��s���܂��B
	 */
	public static final NpcStringId THE_HALL_OF_EROSION_ATTACK_WILL_FAIL_UNLESS_YOU_MAKE_A_QUICK_ATTACK_AGAINST_THE_TUMOR;

	/**
	 * ID: 1800308<br>
	 * Message: ��ᇑ̂���������Ȃ��̂ŁA�R�w���l�X�͎�̉��[���ɓ����Ă����܂��B
	 */
	public static final NpcStringId AS_THE_TUMOR_WAS_NOT_THREATENED_COHEMENES_COMPLETELY_RAN_AWAY_TO_DEEP_INSIDE_THE_SEED;

	/**
	 * ID: 1800309<br>
	 * Message: �ڕW���W�Q���ꂽ��A������󂯂��肷��B
	 */
	public static final NpcStringId YOUR_GOAL_WILL_BE_OBSTRUCTED_OR_BE_UNDER_A_RESTRAINT;

	/**
	 * ID: 1800310<br>
	 * Message: �ڕW�Ɍ����Đi�މߒ��ŗ\�z�O�̖�肪�����邩������Ȃ��B
	 */
	public static final NpcStringId YOU_MAY_FACE_AN_UNFORESEEN_PROBLEM_ON_YOUR_COURSE_TOWARD_THE_GOAL;

	/**
	 * ID: 1800311<br>
	 * Message: �󋵂��s���ɂȂ��āA�s���ŏő����Ɏ���邱�ƂɂȂ�B
	 */
	public static final NpcStringId YOU_MAY_FEEL_NERVOUS_AND_ANXIOUS_BECAUSE_OF_UNFAVORABLE_SITUATIONS;

	/**
	 * ID: 1800312<br>
	 * Message: ���ꂪ�낤���Ȃ�΁A���f�͂��݂��ė��ɂ��Ȃ�ʂ��Ƃ�����B�C������B
	 */
	public static final NpcStringId BE_WARNED_WHEN_THE_SITUATION_IS_DIFFICULT_BECAUSE_YOU_MAY_LOSE_YOUR_JUDGMENT_AND_MAKE_AN_IRRATIONAL_MISTAKE;

	/**
	 * ID: 1800313<br>
	 * Message: �M���ł���l�ɉ�āA�`�����X�����ށB
	 */
	public static final NpcStringId YOU_MAY_MEET_A_TRUSTWORTHY_PERSON_OR_A_GOOD_OPPORTUNITY;

	/**
	 * ID: 1800314<br>
	 * Message: �p�b�Ƃ��Ȃ�������炵������������B
	 */
	public static final NpcStringId YOUR_DOWNWARD_LIFE_STARTS_TAKING_AN_UPTURN;

	/**
	 * ID: 1800315<br>
	 * Message: �l�C���������Ď��͂̎�������g�ɏW�߂�B
	 */
	public static final NpcStringId YOU_WILL_ATTRACT_ATTENTION_FROM_PEOPLE_WITH_YOUR_POPULARITY;

	/**
	 * ID: 1800316<br>
	 * Message: �d�|���Ă������a�ɋ����H�����B
	 */
	public static final NpcStringId YOUR_STAR_OF_FORTUNE_SAYS_THERELL_BE_FISH_SNAPPING_AT_YOUR_BAIT;

	/**
	 * ID: 1800317<br>
	 * Message: �ƒf�Ŏ���i�߂�΁A���ꂫ��������B
	 */
	public static final NpcStringId THERE_MAY_BE_A_CONFLICT_BORN_OF_YOUR_DOGMATIC_PROCEDURES;

	/**
	 * ID: 1800318<br>
	 * Message: �m�b�Ƒn���͂ŖڕW��B������B
	 */
	public static final NpcStringId YOUR_WISDOM_AND_CREATIVITY_MAY_LEAD_YOU_TO_SUCCESS_WITH_YOUR_GOAL;

	/**
	 * ID: 1800319<br>
	 * Message: �킫�ڂ��ӂ炸�Ɏd���ɐ�S����΁A�ڕW��B���ł���B
	 */
	public static final NpcStringId YOU_MAY_ACCOMPLISH_YOUR_GOALS_IF_YOU_DILIGENTLY_PURSUE_THEM_WITHOUT_GIVING_UP;

	/**
	 * ID: 1800320<br>
	 * Message: ���m�b�𓭂��������ʂ���U�߂�Η͂𓾂���B
	 */
	public static final NpcStringId YOU_MAY_GET_HELP_IF_YOU_GO_THROUGH_THE_FRONT_DOOR_WITHOUT_SEEKING_TRICKS_OR_MANEUVERS;

	/**
	 * ID: 1800321<br>
	 * Message: �ڕW���߂ėE�C�������čs������΁A�������ʂ�������B
	 */
	public static final NpcStringId A_GOOD_RESULT_IS_ON_THE_WAY_IF_YOU_SET_A_GOAL_AND_BRAVELY_PROCEED_TOWARD_IT;

	/**
	 * ID: 1800322<br>
	 * Message: �����Ȃ鍢����낤�Ƃ��A��������肭�����B
	 */
	public static final NpcStringId EVERYTHING_WILL_BE_SMOOTHLY_MANAGED_NO_MATTER_HOW_DIFFICULT;

	/**
	 * ID: 1800323<br>
	 * Message: �������������ʂ��A�T�d�Ɍ������Ďu���������āB
	 */
	public static final NpcStringId BE_FIRM_AND_CAREFULLY_SCRUTINIZE_CIRCUMSTANCES_EVEN_WHEN_THINGS_ARE_DIFFICULT;

	/**
	 * ID: 1800324<br>
	 * Message: ���u���Ă�����肪�Ȃ����A��ɍl����B
	 */
	public static final NpcStringId ALWAYS_THINK_OVER_TO_FIND_NEGLECTED_PROBLEMS_YOU_HAVENT_TAKEN_CARE_OF_YET;

	/**
	 * ID: 1800325<br>
	 * Message: �������ɋ�����󂪓]���荞��ł���B
	 */
	public static final NpcStringId FINANCIAL_FORTUNE_WILL_GREET_YOUR_POOR_LIFE;

	/**
	 * ID: 1800326<br>
	 * Message: �s�K�̐��̉��ɐ��܂ꂽ���Ȃ��B�^�C���㏸���ĕx�Ɩ��_����ɂ���B
	 */
	public static final NpcStringId YOU_MAY_ACQUIRE_WEALTH_AND_FAME_AFTER_UNLUCKY_CIRCUMSTANCES;

	/**
	 * ID: 1800327<br>
	 * Message: ��������Ă��\�z�O�̏����𓾂Č��������悤�ɂȂ�B
	 */
	public static final NpcStringId THE_DIFFICULT_SITUATIONS_WILL_TURN_TO_HOPE_WITH_UNFORESEEN_HELP;

	/**
	 * ID: 1800328<br>
	 * Message: �傫�Ȏd������������B
	 */
	public static final NpcStringId A_GREAT_TASK_WILL_RESULT_IN_SUCCESS;

	/**
	 * ID: 1800329<br>
	 * Message: ���ɔ�߂Ă����₵���Ɗ����𐁂���΂��Ă����M�l�ɉ�B
	 */
	public static final NpcStringId YOU_MAY_ENCOUNTER_A_PRECIOUS_PERSON_WHO_WILL_LIFT_YOUR_LONELINESS_AND_DISCORD;

	/**
	 * ID: 1800330<br>
	 * Message: ���͂̐l�X�̌���ɗ͂𓾂�B
	 */
	public static final NpcStringId PEOPLE_AROUND_YOU_WILL_ENCOURAGE_YOUR_EVERY_TASK_IN_THE_FUTURE;

	/**
	 * ID: 1800331<br>
	 * Message: �������~���ɐi�ށB
	 */
	public static final NpcStringId EVERYTHING_WILL_BE_SMOOTHLY_MANAGED;

	/**
	 * ID: 1800332<br>
	 * Message: �~���Ȑl�Ԋ֌W���ێ�����΁A���Ȃ��̉��l�����߂Ă����l�������B
	 */
	public static final NpcStringId YOU_WILL_MEET_A_PERSON_WHO_CAN_CHERISH_YOUR_VALUES_IF_YOU_MAINTAIN_GOOD_TIES_WITH_PEOPLE;

	/**
	 * ID: 1800333<br>
	 * Message: �����̎�������L�ׂ�N���������B��ɋ��͂���S��Y�ꂸ�ɁB
	 */
	public static final NpcStringId MAINTAIN_COOPERATIVE_ATTITUDE_SINCE_YOU_WILL_MEET_SOMEONE_TO_BE_OF_HELP;

	/**
	 * ID: 1800334<br>
	 * Message: �������܂��^�񂾂Ƃĕ����������ߐ��ɗ�߁B
	 */
	public static final NpcStringId KEEP_YOUR_MODERATION_AND_EGO_IN_CHECK_EVEN_IN_SUCCESSFUL_PHASES_OF_YOUR_LIFE;

	/**
	 * ID: 1800335<br>
	 * Message: �d���A�����A�l�Ԋ֌W���ׂĂɂ����ĉ������������ʂ���Ԃ���΂������Ƃ��N����B
	 */
	public static final NpcStringId WHEN_IT_COMES_TO_WORK_LIFESTYLE_AND_RELATIONSHIPS_YOULL_BE_BETTER_OFF_TO_GO_BY_THE_TEXT_RATHER_THAN_TRICKS;

	/**
	 * ID: 1800336<br>
	 * Message: �����������Ďd���ɒe�͂��t���B
	 */
	public static final NpcStringId YOUR_TASK_WILL_RECEIVE_SUBSTANTIAL_SUPPORT_SINCE_THE_SURROUNDINGS_WILL_FULLY_DEVELOP;

	/**
	 * ID: 1800337<br>
	 * Message: ���_�I�ɂ������I�ɂ������𓾂Đ�������B
	 */
	public static final NpcStringId YOUR_STAR_OF_FORTUNE_INDICATE_A_SUCCESS_WITH_MENTAL_AND_MATERIAL_ASSISTANCE;

	/**
	 * ID: 1800338<br>
	 * Message: �n���́A�˔\�A�C�̂������ӂ�܂��ŊF����D�����������B
	 */
	public static final NpcStringId YOU_WILL_ENJOY_POPULARITY_WITH_YOUR_CREATIVE_TALENTS_AND_CLEVER_ACTS;

	/**
	 * ID: 1800339<br>
	 * Message: �F�����Ȃ��������悤�Ƃ���B
	 */
	public static final NpcStringId PEOPLE_WILL_LINE_UP_TO_BE_OF_ASSISTANCE_TO_YOU;

	/**
	 * ID: 1800340<br>
	 * Message: ���H�����ɂ���F�������B
	 */
	public static final NpcStringId YOU_MAY_MEET_SOMEONE_TO_SHARE_YOUR_JOURNEY;

	/**
	 * ID: 1800341<br>
	 * Message: �l�X�Ȑl�ɏo����ėǉ��ɂ߂��荇���B
	 */
	public static final NpcStringId YOU_MAY_ACHIEVE_CONNECTIONS_IN_MANY_FIELDS;

	/**
	 * ID: 1800342<br>
	 * Message: �w�сA�ǂ����߂�p�����̐S�B�����ɂ����Đ^���ȑԓx�ŗՂ߁B
	 */
	public static final NpcStringId AN_ATTITUDE_THAT_CONTINUALLY_STUDIES_AND_EXPLORES_IS_NEEDED_AND_ALWAYS_BE_SINCERE;

	/**
	 * ID: 1800343<br>
	 * Message: �t�̒g���ȋC�ɏ���Ă���Ă��������ԂɂƂ܂�B
	 */
	public static final NpcStringId ITS_AN_IMAGE_OF_A_BUTTERFLY_ON_A_FLOWER_IN_WARM_SPRING_AIR;

	/**
	 * ID: 1800344<br>
	 * Message: ���ǂ����肵�������ɁA�u���~�����A����B
	 */
	public static final NpcStringId YOUR_GOALS_WILL_MOVE_SMOOTHLY_WITH_PEACE_AND_HAPPINESS_IN_YOUR_LIFE;

	/**
	 * ID: 1800345<br>
	 * Message: ���͂̐l�X�ɒg�����ڂ���Έ���萶����B
	 */
	public static final NpcStringId LOVE_MAY_SPROUT_ITS_LEAVES_WHEN_YOU_TREAT_THOSE_AROUND_YOU_WITH_CARE;

	/**
	 * ID: 1800346<br>
	 * Message: �^����ꂽ�d���𒅎��ɐi�߂�΁A�M���𓾂Ă���ɍ����X�e�[�W�ɐi�߂�B
	 */
	public static final NpcStringId YOU_MAY_CLIMB_INTO_A_HIGHER_POSITION_WITH_OTHERS_TRUST_IF_YOU_FAITHFULLY_CARRY_OUT_YOUR_DUTIES;

	/**
	 * ID: 1800347<br>
	 * Message: �^�C�㏸�B�������~�𒣂�Ζ������A�ɋA���B
	 */
	public static final NpcStringId EVERYTHING_CAN_FALL_APART_IF_YOU_GREEDILY_AIM_BY_PURE_LUCK;

	/**
	 * ID: 1800348<br>
	 * Message: �l�Ƃ̕t�����������܂߂ɁB
	 */
	public static final NpcStringId DO_NOT_UNDERESTIMATE_THE_IMPORTANCE_OF_MEETING_PEOPLE;

	/**
	 * ID: 1800349<br>
	 * Message: ���𓾂����B
	 */
	public static final NpcStringId AN_ARROW_WILL_COALESCE_INTO_THE_BOW;

	/**
	 * ID: 1800350<br>
	 * Message: �͂ꂩ���̖؂Ɏ����Ȃ�B
	 */
	public static final NpcStringId A_BONY_LIMB_OF_A_TREE_MAY_BEAR_ITS_FRUIT;

	/**
	 * ID: 1800351<br>
	 * Message: �w�͂��ē������ʂŕ�܂𓾂�B
	 */
	public static final NpcStringId YOU_WILL_BE_REWARDED_FOR_YOUR_EFFORTS_AND_ACCOMPLISHMENTS;

	/**
	 * ID: 1800352<br>
	 * Message: �����A�M�O�������Đ����i�߂�Α傫�Ȑ��ʂ�������B
	 */
	public static final NpcStringId NO_MATTER_WHERE_IT_LIES_YOUR_FAITHFUL_DRIVE_LEADS_YOU_TO_SUCCESS;

	/**
	 * ID: 1800353<br>
	 * Message: ���Ȃ��̖��͂͂ǂ�Ȃ��Ƃł��ꏊ�����ȂƂ���B
	 */
	public static final NpcStringId PEOPLE_WILL_BE_ATTRACTED_TO_YOUR_LOYALTIES;

	/**
	 * ID: 1800354<br>
	 * Message: �l�̌������ƂɐU��񂳂��ׂ��炸�B������M����B
	 */
	public static final NpcStringId YOU_MAY_TRUST_YOURSELF_RATHER_THAN_OTHERS_TALKS;

	/**
	 * ID: 1800355<br>
	 * Message: ���̂̌�����ς��đn���I�ȍl���������āB
	 */
	public static final NpcStringId CREATIVE_THINKING_AWAY_FROM_THE_OLD_VIEWPOINT_MAY_HELP_YOU;

	/**
	 * ID: 1800356<br>
	 * Message: �����炸�]�T�������Č��ʂ�҂Ă΂������ʂ�������B
	 */
	public static final NpcStringId PATIENCE_WITHOUT_BEING_IMPETUOUS_OF_THE_RESULTS_WILL_ONLY_BEAR_A_POSITIVE_OUTCOME;

	/**
	 * ID: 1800357<br>
	 * Message: ���l�������Ԃ�B
	 */
	public static final NpcStringId THE_DEAD_WILL_COME_ALIVE;

	/**
	 * ID: 1800358<br>
	 * Message: �����̏o���������Ȃ���҂��󂯂�B
	 */
	public static final NpcStringId THERE_WILL_BE_A_SHOCKING_INCIDENT;

	/**
	 * ID: 1800359<br>
	 * Message: ���Ȃڂ��ő听�������߂�B
	 */
	public static final NpcStringId YOU_WILL_ENJOY_A_HUGE_SUCCESS_AFTER_UNFORESEEN_LUCK_COMES_BEFORE_YOU;

	/**
	 * ID: 1800360<br>
	 * Message: ������߂����Ă������ƂɊ�Ղ��N����B�Ō�܂ł�����߂�ȁB
	 */
	public static final NpcStringId DO_NOT_GIVE_UP_SINCE_THERE_MAY_BE_A_MIRACULOUS_RESCUE_FROM_THE_COURSE_OF_DESPAIR;

	/**
	 * ID: 1800361<br>
	 * Message: �ڕW�B���ɍőP��s�����p�����K�v���B
	 */
	public static final NpcStringId AN_ATTITUDE_TO_TRY_ONES_BEST_TO_PURSUE_THE_GOAL_IS_NEEDED;

	/**
	 * ID: 1800362<br>
	 * Message: ���R�����l�ɏo����āA�����̊��͂𓾂�B
	 */
	public static final NpcStringId YOU_MAY_GET_A_SHOT_IN_THE_ARM_IN_YOUR_LIFE_AFTER_MEETING_A_GOOD_PERSON;

	/**
	 * ID: 1800363<br>
	 * Message: ��炵�ɑ傫�ȏ����𓾂�B
	 */
	public static final NpcStringId YOU_MAY_GET_A_BIG_HELP_IN_THE_COURSE_OF_YOUR_LIFE;

	/**
	 * ID: 1800364<br>
	 * Message: ���W�ւƂȂ���`�����X�����ށB
	 */
	public static final NpcStringId A_RARE_OPPORTUNITY_WILL_COME_TO_YOU_SO_YOU_MAY_PROSPER;

	/**
	 * ID: 1800365<br>
	 * Message: �������������邪�l�����l��B
	 */
	public static final NpcStringId A_HUNGRY_FALCON_WILL_HAVE_MEAT;

	/**
	 * ID: 1800366<br>
	 * Message: �������ɍ���ƐH�����]���荞��ł���B
	 */
	public static final NpcStringId A_HOUSEHOLD_IN_NEED_WILL_ACQUIRE_A_FORTUNE_AND_MEAT;

	/**
	 * ID: 1800367<br>
	 * Message: ���l���畨���I�A���_�I�ȏ����𓾂ċꋫ��E����B
	 */
	public static final NpcStringId A_HARD_SITUATION_WILL_COME_TO_ITS_END_WITH_MATERIALISTIC_AND_MENTAL_HELP_FROM_OTHERS;

	/**
	 * ID: 1800368<br>
	 * Message: ����������߂��ڕW���͂�����Ƃ�����΁A����Ə����̎肪�����L�ׂ���B
	 */
	public static final NpcStringId IF_YOU_SET_A_FIRM_GOAL_WITHOUT_SURRENDER_THERE_WILL_BE_A_PERSON_WHO_CAN_OFFER_HELP_AND_CARE;

	/**
	 * ID: 1800369<br>
	 * Message: �^���Ő����ȑԓx��ۂĂ΁A����̐l�X����M�����������B
	 */
	public static final NpcStringId YOULL_GAIN_OTHERS_TRUST_WHEN_YOU_MAINTAIN_A_SINCERE_AND_HONEST_ATTITUDE;

	/**
	 * ID: 1800370<br>
	 * Message: �N�ɂ����炸�ɕ�点�B
	 */
	public static final NpcStringId BE_INDEPENDENT_AT_ALL_TIMES;

	/**
	 * ID: 1800371<br>
	 * Message: �Ԃ̎ԗւ��Ȃ��B
	 */
	public static final NpcStringId ITS_A_WAGON_WITH_NO_WHEELS;

	/**
	 * ID: 1800372<br>
	 * Message: �u�������Ă��A���������̎�����W����B
	 */
	public static final NpcStringId YOUVE_SET_A_GOAL_BUT_THERE_MAY_BE_OBSTACLES_IN_REALITY;

	/**
	 * ID: 1800373<br>
	 * Message: �ڕW�Ɍ�����簐i���邪�A�v�����قǂ̐��ʂ͓����Ȃ��B
	 */
	public static final NpcStringId YOURE_RUNNING_TOWARD_THE_GOAL_BUT_THERE_WONT_BE_AS_MANY_OUTCOMES_AS_YOU_THOUGHT;

	/**
	 * ID: 1800374<br>
	 * Message: �ז��������ĔY�܂����B
	 */
	public static final NpcStringId THERE_ARE_MANY_THINGS_TO_CONSIDER_AFTER_ENCOUNTERING_HINDRANCES;

	/**
	 * ID: 1800375<br>
	 * Message: �����̏������l�����ɋ��s����Ύ��s����B
	 */
	public static final NpcStringId A_RECKLESS_MOVE_MAY_BRING_A_FAILURE;

	/**
	 * ID: 1800376<br>
	 * Message: �����A�T�d�ɂ�����B�����Ȃ��Ύ���̐l�X�̐M���������B
	 */
	public static final NpcStringId YOU_MAY_LOSE_PEOPLES_TRUST_IF_YOU_LACK_PRUDENCE_AT_ALL_TIMES;

	/**
	 * ID: 1800377<br>
	 * Message: ������Ȃ݂D�@��҂āB
	 */
	public static final NpcStringId YOU_MAY_NEED_TO_REFLECT_ON_YOURSELF_WITH_DELIBERATION_AND_WAIT_FOR_AN_OPPORTUNITY;

	/**
	 * ID: 1800378<br>
	 * Message: �n�m�A�\�𓾂�B���Ȃڂ��̗\���B
	 */
	public static final NpcStringId A_POOR_SCHOLAR_RECEIVES_A_STIPEND;

	/**
	 * ID: 1800379<br>
	 * Message: �����Ƀp�X���ĕx�Ɩ��_�𓾂�B
	 */
	public static final NpcStringId A_SCHOLAR_GETS_A_PASS_TOWARD_FAME_AND_FORTUNE;

	/**
	 * ID: 1800380<br>
	 * Message: �����Ɩ�����������B
	 */
	public static final NpcStringId YOUR_AMBITION_AND_DREAM_WILL_COME_TRUE;

	/**
	 * ID: 1800381<br>
	 * Message: ���G�Ȗ��͏�������������B
	 */
	public static final NpcStringId COMPLICATED_PROBLEMS_AROUND_YOU_MAY_START_BEING_SOLVED_ONE_AFTER_ANOTHER;

	/**
	 * ID: 1800382<br>
	 * Message: �߂����������ƂŔY�܂��A�ڕW������ĂĎ����ɓw�͂���΂������ʂ�������B
	 */
	public static final NpcStringId YOU_WILL_HAVE_A_GOOD_RESULT_IF_YOU_DILIGENTLY_PURSUE_ONE_GOAL_WITHOUT_BEING_DRAGGED_FROM_YOUR_PAST;

	/**
	 * ID: 1800383<br>
	 * Message: ���K�͑Ŕj���ׂ��B
	 */
	public static final NpcStringId YOU_MAY_NEED_TO_RID_YOURSELF_OF_OLD_AND_WORN_HABITS;

	/**
	 * ID: 1800384<br>
	 * Message: �C����ꂽ�d���͐ӔC���������Ă��ʂ��B�������A���͂̏�����ϋɓI�ɋ��߂�B
	 */
	public static final NpcStringId BE_RESPONSIBLE_WITH_YOUR_TASKS_BUT_DO_NOT_HESITATE_TO_ASK_FOR_COLLEAGUES_HELP;

	/**
	 * ID: 1800385<br>
	 * Message: �΂�����Y�ށB
	 */
	public static final NpcStringId FISH_TRANSFORMS_INTO_A_DRAGON;

	/**
	 * ID: 1800386<br>
	 * Message: �]�݂������Ė��_�ƍ��𓾂�B
	 */
	public static final NpcStringId YOUR_DREAM_MAY_COME_TRUE_AND_FAME_AND_FORTUNE_WILL_COME_TO_YOU;

	/**
	 * ID: 1800387<br>
	 * Message: �v�悪�v���ʂ�ɐi�ށB
	 */
	public static final NpcStringId WHAT_YOUVE_PLANED_WILL_BE_ACCOMPLISHED;

	/**
	 * ID: 1800388<br>
	 * Message: �v��ʂƂ��납����K��`�����X���]���荞�ށB
	 */
	public static final NpcStringId YOU_MAY_ACQUIRE_MONEY_OR_A_NEW_OPPORTUNITY_FROM_A_PLACE_YOU_WOULDNT_HAVE_THOUGHT_OF;

	/**
	 * ID: 1800389<br>
	 * Message: ��Ă͑������A�T�d�ɍl����B
	 */
	public static final NpcStringId THERE_WILL_BE_MANY_OFFERS_TO_YOU_YOU_MAY_THINK_THEM_OVER_CAREFULLY;

	/**
	 * ID: 1800390<br>
	 * Message: ���l���Ɏ�������ނȁB
	 */
	public static final NpcStringId IT_MAY_BE_A_GOOD_IDEA_NOT_TO_BECOME_INVOLVED_IN_OTHERS_BUSINESS;

	/**
	 * ID: 1800391<br>
	 * Message: �����������ɍs���B���f������ӂ������񂾂肷��̂͋֕��B
	 */
	public static final NpcStringId EVERYTHING_WILL_GO_SMOOTHLY_BUT_BE_AWARE_OF_DANGER_FROM_BEING_CARELESS_AND_REMISS;

	/**
	 * ID: 1800392<br>
	 * Message: ������l�ɂ͐ɂ��݂Ȃ����𒍂��B�傫�ȕ�܂�������B
	 */
	public static final NpcStringId IF_YOU_SINCERELY_CARE_FOR_SOMEONE_YOU_LOVE_A_BIG_REWARD_WILL_RETURN_TO_YOU;

	/**
	 * ID: 1800393<br>
	 * Message: �d�a�ɗǖ�𓾂�B
	 */
	public static final NpcStringId A_REMEDY_IS_ON_ITS_WAY_FOR_A_SERIOUS_ILLNESS;

	/**
	 * ID: 1800394<br>
	 * Message: ��a�ŋꂵ�ނ��A�ǖ�𓾂Ċ��͂����߂��B
	 */
	public static final NpcStringId YOU_MAY_ACQUIRE_A_PRECIOUS_MEDICINE_TO_RECOVER_AFTER_SUFFERING_A_DISEASE_OF_A_SERIOUS_NATURE;

	/**
	 * ID: 1800395<br>
	 * Message: �ꋫ�ŋM�l�ɏo��]�݂������B
	 */
	public static final NpcStringId YOU_MAY_REALIZE_YOUR_DREAM_BY_MEETING_A_MAN_OF_DISTINCTION_AT_A_DIFFICULT_TIME;

	/**
	 * ID: 1800396<br>
	 * Message: ���H�ɂ͓��������́B
	 */
	public static final NpcStringId YOU_MAY_SUFFER_ONE_OR_TWO_HARDSHIPS_ON_YOUR_JOURNEY;

	/**
	 * ID: 1800397<br>
	 * Message: ���΂�Y�ꂸ���܂��Ȃ���΁A����̐l�X����M���Ə����𓾂�B
	 */
	public static final NpcStringId IF_YOU_KEEP_SMILING_WITHOUT_DESPAIR_PEOPLE_WILL_COME_TO_TRUST_YOU_AND_OFFER_HELP;

	/**
	 * ID: 1800398<br>
	 * Message: �ω����͈�����B
	 */
	public static final NpcStringId SEEK_STABILITY_RATHER_THAN_DYNAMICS_IN_YOUR_LIFE;

	/**
	 * ID: 1800399<br>
	 * Message: �����A�T�d���m���ɐi�߂�ׂ��B
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_TO_BE_CAREFUL_AND_SECURE_AT_ALL_TIMES;

	/**
	 * ID: 1800400<br>
	 * Message: �葫�����킸�d����i�߂��Ȃ��B
	 */
	public static final NpcStringId YOU_CANT_PERFORM_THE_JOB_WITH_BOUND_HANDS;

	/**
	 * ID: 1800401<br>
	 * Message: ���i�͂��������܂悤�B
	 */
	public static final NpcStringId YOU_MAY_LOSE_YOUR_DRIVE_AND_FEEL_LOST;

	/**
	 * ID: 1800402<br>
	 * Message: ���p�����A�d���ɏW���ł��Ȃ��B
	 */
	public static final NpcStringId YOU_MAY_BE_UNABLE_TO_CONCENTRATE_WITH_SO_MANY_PROBLEMS_OCCURRING;

	/**
	 * ID: 1800403<br>
	 * Message: �蕿��l�ɉ���肳���B
	 */
	public static final NpcStringId YOUR_ACHIEVEMENT_UNFAIRLY_MAY_GO_SOMEWHERE_ELSE;

	/**
	 * ID: 1800404<br>
	 * Message: �m���łȂ���Ύ������ȁB
	 */
	public static final NpcStringId DO_NOT_START_A_TASK_THATS_NOT_CLEAR_TO_YOU;

	/**
	 * ID: 1800405<br>
	 * Message: ������ɔ�����B
	 */
	public static final NpcStringId YOU_WILL_NEED_TO_BE_PREPARED_FOR_ALL_EVENTS;

	/**
	 * ID: 1800406<br>
	 * Message: ��@�ɕm���Ă�������߂��ɂ���܂ʓw�͂��B�F�߂Ă����l�������B
	 */
	public static final NpcStringId SOMEONE_WILL_ACKNOWLEDGE_YOU_IF_YOU_RELENTLESSLY_KEEP_TRYING_AND_DO_NOT_GIVE_UP_WHEN_FACING_HARDSHIPS;

	/**
	 * ID: 1800407<br>
	 * Message: �����̐^�̎p�������B
	 */
	public static final NpcStringId YOU_MAY_PERFECT_YOURSELF_LIKE_A_DRAGONS_HORN_DECORATES_THE_DRAGON;

	/**
	 * ID: 1800408<br>
	 * Message: ���X�ɐ^���𔭊�����B
	 */
	public static final NpcStringId YOUR_TRUE_VALUE_STARTS_TO_SHINE;

	/**
	 * ID: 1800409<br>
	 * Message: ���ʂł���B�V�������Ƃ�ǂ����߂�B���Ȃ��̉��l���オ��B
	 */
	public static final NpcStringId YOUR_STEADY_PURSUIT_OF_NEW_INFORMATION_AND_STAYING_AHEAD_OF_OTHERS_WILL_RAISE_YOUR_VALUE;

	/**
	 * ID: 1800410<br>
	 * Message: �d�����l�Ԋ֌W�������悭����B�������ʂ�������B
	 */
	public static final NpcStringId MAINTAINING_CONFIDENCE_WITH_WORK_OR_RELATIONSHIPS_MAY_BRING_GOOD_RESULTS;

	/**
	 * ID: 1800411<br>
	 * Message: �ߐM�͍Ђ��̂��ƁB���ԂƋ��͂���B
	 */
	public static final NpcStringId LEARN_TO_WORK_WITH_OTHERS_SINCE_OVERCONFIDENCE_WILL_BEAR_WRATH;

	/**
	 * ID: 1800412<br>
	 * Message: �S�ɋ��_�B
	 */
	public static final NpcStringId THE_DRAGON_NOW_ACQUIRES_AN_EAGLES_WINGS;

	/**
	 * ID: 1800413<br>
	 * Message: �S�ɋ��_�B���ƖڕW����������B
	 */
	public static final NpcStringId AS_THE_DRAGON_FLIES_HIGH_IN_THE_SKY_YOUR_GOALS_AND_DREAMS_MAY_COME_TRUE;

	/**
	 * ID: 1800414<br>
	 * Message: �d���A��A�Ƒ��A���l�B�����^�C�㏸�B
	 */
	public static final NpcStringId LUCK_ENTERS_INTO_YOUR_WORK_HOBBY_FAMILY_AND_LOVE;

	/**
	 * ID: 1800415<br>
	 * Message: �����Ƃ���G�Ȃ��B
	 */
	public static final NpcStringId WHATEVER_YOU_DO_IT_WILL_ACCOMPANY_WINNING;

	/**
	 * ID: 1800416<br>
	 * Message: �v��ʂƂ��납����K��`�����X���]���荞�ށB
	 */
	public static final NpcStringId ITS_AS_GOOD_AS_IT_GETS_WITH_UNFORESEEN_FORTUNE_ROLLING_YOUR_WAY;

	/**
	 * ID: 1800417<br>
	 * Message: �~�����Č��l�����i�߂�Ή��������B
	 */
	public static final NpcStringId A_GREEDY_ACT_WITH_NO_PRUDENCE_WILL_BRING_A_SURPRISE_AT_THE_END;

	/**
	 * ID: 1800418<br>
	 * Message: ��Âɍl���āA�T�d�ɍs������B
	 */
	public static final NpcStringId THINK_CAREFULLY_AND_ACT_WITH_CAUTION_AT_ALL_TIMES;

	/**
	 * ID: 1800419<br>
	 * Message: �ǂ����������Ə��F�͍��������B����������B
	 */
	public static final NpcStringId IF_A_TREE_DOESNT_HAVE_ITS_ROOTS_THERE_WILL_BE_NO_FRUIT;

	/**
	 * ID: 1800420<br>
	 * Message: ���܂葹�̂����т�ׂ��B
	 */
	public static final NpcStringId HARD_WORK_DOESNT_BEAR_FRUIT;

	/**
	 * ID: 1800421<br>
	 * Message: �������������ċꋫ�ɗ��B
	 */
	public static final NpcStringId FINANCIAL_DIFFICULTIES_MAY_BRING_AN_ORDEAL;

	/**
	 * ID: 1800422<br>
	 * Message: �����ɐi��ł������Ƃɋ}�u���[�L��������B
	 */
	public static final NpcStringId WHAT_USED_TO_BE_WELL_MANAGED_MAY_STUMBLE_ONE_AFTER_ANOTHER;

	/**
	 * ID: 1800423<br>
	 * Message: �v�����قǂ̐��ʂ͓���ꂸ���ǂ������B
	 */
	public static final NpcStringId A_FEELING_OF_FRUSTRATION_MAY_FOLLOW_DISAPPOINTMENT;

	/**
	 * ID: 1800424<br>
	 * Message: ����̐l�X�ɓ���U�点�ΐl�Ԋ֌W�ɂЂт�����B
	 */
	public static final NpcStringId BE_CAUTIONED_AS_UNHARNESSED_BEHAVIOR_AT_DIFFICULT_TIMES_CAN_RUIN_RELATIONSHIPS;

	/**
	 * ID: 1800425<br>
	 * Message: �~�𒣂炸�A�����m��B
	 */
	public static final NpcStringId CURTAIL_GREED_AND_BE_GRATEFUL_FOR_SMALL_RETURNS_AS_MODESTY_IS_NEEDED;

	/**
	 * ID: 1800426<br>
	 * Message: �������������l������B
	 */
	public static final NpcStringId THE_PERSON_THAT_CAME_UNDER_YOUR_WINGS_WILL_LEAVE;

	/**
	 * ID: 1800427<br>
	 * Message: �l�Ԋ֌W�ɋC���g���ΊF���ǂ��Ȃ�A���������B
	 */
	public static final NpcStringId YOUR_WORK_AND_RELATIONSHIP_WITH_COLLEAGUES_WILL_BE_WELL_MANAGED_IF_YOU_MAINTAIN_YOUR_DEVOTION;

	/**
	 * ID: 1800428<br>
	 * Message: �l�t�������ŗ��Q�֌W�΂���l���ė�V�����Ȃ���΁A�A����@����ĐM���͒n�ɗ�����B
	 */
	public static final NpcStringId CALCULATING_YOUR_PROFIT_IN_RELATIONSHIPS_WITHOUT_DISPLAYING_ANY_COURTEOUS_MANNERS_WILL_BRING_MALICIOUS_GOSSIP_AND_RUIN_YOUR_VALUE;

	/**
	 * ID: 1800429<br>
	 * Message: ��ɑ���̗���ɗ����čl���āA�^�S�Őڂ���B
	 */
	public static final NpcStringId CONSIDER_OTHERS_SITUATIONS_AND_TREAT_THEM_SINCERELY_AT_ALL_TIMES;

	/**
	 * ID: 1800430<br>
	 * Message: �ْ����������ȁB
	 */
	public static final NpcStringId DO_NOT_LOOSEN_UP_WITH_YOUR_PRECAUTIONS;

	/**
	 * ID: 1800431<br>
	 * Message: �ƒf�Ŏ���i�߂�Ύ��s����B����̌������ƂɎ����X����B
	 */
	public static final NpcStringId REFLECT_OTHERS_OPINIONS_AS_A_MISTAKE_ALWAYS_LIES_AHEAD_OF_AN_ARBITRARY_DECISION;

	/**
	 * ID: 1800432<br>
	 * Message: �炭�����j�̌���ʂ�B
	 */
	public static final NpcStringId A_BLIND_MAN_GOES_RIGHT_THROUGH_THE_DOOR;

	/**
	 * ID: 1800433<br>
	 * Message: �d���̃S�[�������������ǂ������B
	 */
	public static final NpcStringId A_HEART_FALLS_INTO_HOPELESSNESS_AS_THINGS_ARE_IN_DISARRAY;

	/**
	 * ID: 1800434<br>
	 * Message: �������{���肵�Ă��ǂ������B
	 */
	public static final NpcStringId HOPELESSNESS_MAY_FILL_YOUR_HEART_AS_YOUR_WORK_FALLS_INTO_A_MAZE;

	/**
	 * ID: 1800435<br>
	 * Message: �ꏊ�����Ŏ��ɂ������Ă���̂ɖ��ɂԂ�������B
	 */
	public static final NpcStringId DIFFICULTIES_LIE_AHEAD_OF_AN_UNFORESEEN_PROBLEM_EVEN_WITH_YOUR_HARD_WORK;

	/**
	 * ID: 1800436<br>
	 * Message: �����Ɏ��M�������A���l�𗊂������ɂȂ�B
	 */
	public static final NpcStringId THERE_MAY_BE_MORE_OCCASIONS_YOU_WILL_WANT_TO_ASK_FAVORS_FROM_OTHERS_AS_YOU_LOSE_CONFIDENCE_IN_YOU;

	/**
	 * ID: 1800437<br>
	 * Message: ���ɓI�߂��Ă͔��W�͖]�߂Ȃ��B���M�ɏ]���B
	 */
	public static final NpcStringId BE_BRAVE_AND_AMBITIOUS_AS_NO_BIRD_CAN_FLY_INTO_THE_SKY_BY_STAYING_IN_THEIR_NEST;

	/**
	 * ID: 1800438<br>
	 * Message: �s�����Ȃ��Ƃɂ͎������ȁB�M���̒u����l��T���B
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_NOT_TO_START_AN_UNCLEAR_TASK_AND_ALWAYS_LOOK_FOR_SOMEONE_YOU_CAN_TRUST_AND_RELY_UPON;

	/**
	 * ID: 1800439<br>
	 * Message: ���ɏオ�����͓��̂悤���B
	 */
	public static final NpcStringId HUNTING_WONT_BE_SUCCESSFUL_AS_THE_FALCON_LACKS_ITS_CLAWS;

	/**
	 * ID: 1800440<br>
	 * Message: ��邱�ƂȂ����Ƃ��܂������Ȃ��B
	 */
	public static final NpcStringId A_PREPARED_PLAN_WONT_MOVE_SMOOTHLY;

	/**
	 * ID: 1800441<br>
	 * Message: ������ߐM���ė~����΁A���܂������͂��̂��Ƃ����s����B
	 */
	public static final NpcStringId AN_EASY_TASK_MAY_FAIL_IF_ONE_IS_CONSUMED_BY_GREED_AND_OVERCONFIDENCE;

	/**
	 * ID: 1800442<br>
	 * Message: �s���ȏ󋵂ŏő����Ɏ����B
	 */
	public static final NpcStringId IMPATIENCE_MAY_LIE_AHEAD_AS_THE_SITUATION_IS_UNFAVORABLE;

	/**
	 * ID: 1800443<br>
	 * Message: �����ڂɑ���Ȃ��悤�ɐ�����ʂ��ڂ����āB
	 */
	public static final NpcStringId THOUGHTFUL_FORESIGHT_IS_NEEDED_BEFORE_A_DISASTER_MAY_FALL_UPON_YOU;

	/**
	 * ID: 1800444<br>
	 * Message: �ƒf�I�ȍs����T�߁B�F�̐S��������߂�B
	 */
	public static final NpcStringId REFRAIN_FROM_DICTATORIAL_ACTS_AS_CARING_FOR_OTHERS_AROUND_YOU_WITH_DIGNITY_IS_MUCH_NEEDED;

	/**
	 * ID: 1800445<br>
	 * Message: ���炭�炷��B
	 */
	public static final NpcStringId THINGS_ARE_MESSY_WITH_NO_GOOD_SIGN;

	/**
	 * ID: 1800446<br>
	 * Message: �悩��ʂ��Ƃ��N���āA�ꋫ�ɗ��B
	 */
	public static final NpcStringId YOU_MAY_FALL_INTO_A_VEXING_SITUATION_AS_BAD_CIRCUMSTANCES_WILL_ARISE;

	/**
	 * ID: 1800447<br>
	 * Message: �l�Ԋ֌W�ɂЂт�����B
	 */
	public static final NpcStringId RELATIONSHIPS_WITH_PEOPLE_MAY_BE_CONTRARY_TO_YOUR_EXPECTATIONS;

	/**
	 * ID: 1800448<br>
	 * Message: ��菈���͏����ł͂Ȃ��A���{�̌���������B
	 */
	public static final NpcStringId DO_NOT_SEEK_A_QUICK_FIX_AS_THE_PROBLEM_NEEDS_A_FUNDAMENTAL_RESOLUTION;

	/**
	 * ID: 1800449<br>
	 * Message: �����}���ĐS�𕽉��ɕۂāB
	 */
	public static final NpcStringId SEEK_PEACE_IN_YOUR_HEART_AS_VULGAR_DISPLAY_OF_YOUR_EMOTIONS_MAY_HARM_YOU;

	/**
	 * ID: 1800450<br>
	 * Message: �Θb�͐����ւ̋ߓ��B
	 */
	public static final NpcStringId INFORMATION_FOR_SUCCESS_MAY_COME_FROM_THE_CONVERSATIONS_WITH_PEOPLE_AROUND_YOU;

	/**
	 * ID: 1800451<br>
	 * Message: �����A���M�����āB
	 */
	public static final NpcStringId BE_CONFIDENT_AND_ACT_RELIANTLY_AT_ALL_TIMES;

	/**
	 * ID: 1800452<br>
	 * Message: �I����ڂ��݁B
	 */
	public static final NpcStringId A_CHILD_GETS_A_TREASURE;

	/**
	 * ID: 1800453<br>
	 * Message: ���Ȃڂ����ɋ��K�ƃ`�����X��������B
	 */
	public static final NpcStringId GOOD_FORTUNE_AND_OPPORTUNITY_MAY_LIE_AHEAD_AS_IF_ONES_BORN_IN_A_GOLDEN_SPOON;

	/**
	 * ID: 1800454<br>
	 * Message: �����͏����B�\�����ʋ��K�Ɛ��ʂ���ɂ���B
	 */
	public static final NpcStringId YOUR_LIFE_FLOWS_AS_IF_ITS_ON_A_SILK_SURFACE_AND_UNEXPECTED_FORTUNE_AND_SUCCESS_MAY_COME_TO_YOU;

	/**
	 * ID: 1800455<br>
	 * Message: �G���Ɉ��B���΂炭�̊Ԃ͉^�Ɍb�܂��B
	 */
	public static final NpcStringId TEMPORARY_LUCK_MAY_COME_TO_YOU_WITH_NO_EFFORT;

	/**
	 * ID: 1800456<br>
	 * Message: �v��͒����ɁA���s�͂��΂₭�B
	 */
	public static final NpcStringId PLAN_AHEAD_WITH_PATIENCE_BUT_EXECUTE_WITH_SWIFTNESS;

	/**
	 * ID: 1800457<br>
	 * Message: �����Ȍ��ׂ������Ē����B������ʂ��͂����Ȃ��̉��l�����߂�B
	 */
	public static final NpcStringId THE_ABILITIES_TO_AMEND_FORESEE_AND_ANALYZE_MAY_RAISE_YOUR_VALUE;

	/**
	 * ID: 1800458<br>
	 * Message: �߂����Ȃ݂Ȃ���΁A��ɕ����Ȃ��قǂ̑厸�s������B
	 */
	public static final NpcStringId BIGGER_MISTAKES_WILL_BE_ON_THE_ROAD_IF_YOU_FAIL_TO_CORRECT_A_SMALL_MISTAKE;

	/**
	 * ID: 1800459<br>
	 * Message: �\��O�̔�����o���͔������Ɏ󂯓����B
	 */
	public static final NpcStringId DONT_BE_EVASIVE_TO_ACCEPT_NEW_FINDINGS_OR_EXPERIENCES;

	/**
	 * ID: 1800460<br>
	 * Message: �v�����Ƃ���ɂȂ�Ȃ��Ă��A����������ȁB
	 */
	public static final NpcStringId DONT_BE_IRRITATED_AS_THE_SITUATIONS_DONT_MOVE_AS_PLANNED;

	/**
	 * ID: 1800461<br>
	 * Message: ���m�Ɉӎu���������ƁB�����Ȃ��΁A����ɗ������B
	 */
	public static final NpcStringId BE_WARNED_AS_YOU_MAY_BE_OVERWHELMED_BY_SURROUNDINGS_IF_YOU_LACK_A_CLEAR_OPINION;

	/**
	 * ID: 1800462<br>
	 * Message: �ڂ�͒��ĂĂ��S�͋сB
	 */
	public static final NpcStringId YOU_MAY_LIVE_AN_AFFLUENT_LIFE_EVEN_WITHOUT_POSSESSIONS;

	/**
	 * ID: 1800463<br>
	 * Message: ���̖��Ɏ�ɂ������Ől�����B�l�]�������B
	 */
	public static final NpcStringId YOU_WILL_GAIN_POPULARITY_AS_YOU_HELP_PEOPLE_WITH_MONEY_YOU_EARNESTLY_EARNED;

	/**
	 * ID: 1800464<br>
	 * Message: �S���̂��y�X�B
	 */
	public static final NpcStringId YOUR_HEART_AND_BODY_MAY_BE_IN_HEALTH;

	/**
	 * ID: 1800465<br>
	 * Message: �ځ[���Ƃ��Ă��邤���ɖ]�܂ʕ����ɐi�ށB
	 */
	public static final NpcStringId BE_WARNED_AS_YOU_MAY_BE_DRAGGED_TO_AN_UNWANTED_DIRECTION_IF_NOT_CAUTIOUS;

	/**
	 * ID: 1800466<br>
	 * Message: �V���ɐl�ɉ���Ă��A�����l�ɂ͂Ȃ��Ȃ��߂��荇���Ȃ��B
	 */
	public static final NpcStringId YOU_MAY_MEET_MANY_NEW_PEOPLE_BUT_IT_WILL_BE_DIFFICULT_TO_FIND_A_PERFECT_PERSON_WHO_WINS_YOUR_HEART;

	/**
	 * ID: 1800467<br>
	 * Message: ���肩��Ȃ����߂���B
	 */
	public static final NpcStringId THERE_MAY_BE_AN_OCCASION_WHERE_YOU_ARE_CONSOLED_BY_PEOPLE;

	/**
	 * ID: 1800468<br>
	 * Message: �����邢����B���͕ω��̎��łȂ��B
	 */
	public static final NpcStringId IT_MAY_NOT_BE_A_GOOD_TIME_FOR_A_CHANGE_EVEN_IF_THERES_TEDIUM_IN_DAILY_LIFE;

	/**
	 * ID: 1800469<br>
	 * Message: �������g�����Ƃ͖����̂��߂̓����B
	 */
	public static final NpcStringId THE_MONEY_YOU_SPEND_FOR_YOURSELF_MAY_ACT_AS_AN_INVESTMENT_AND_BRING_YOU_A_RETURN;

	/**
	 * ID: 1800470<br>
	 * Message: ���l�̂��߂ɂ������g���Ă��ǂԂɎ̂Ă�̂Ɠ����B���l�ɒ��ӂ���B
	 */
	public static final NpcStringId THE_MONEY_YOU_SPEND_FOR_OTHERS_WILL_BE_WASTED_SO_BE_CAUTIOUS;

	/**
	 * ID: 1800471<br>
	 * Message: �Q��ɒ��ӁB
	 */
	public static final NpcStringId BE_WARNED_SO_AS_NOT_TO_HAVE_UNNECESSARY_EXPENSES;

	/**
	 * ID: 1800472<br>
	 * Message: �i�i�v���[���g��C�x���g�ɎQ������B�哖����̗\���B
	 */
	public static final NpcStringId YOUR_STAR_INDICATED_SUCH_GOOD_LUCK_PARTICIPATE_IN_BONUS_GIVEAWAYS_OR_EVENTS;

	/**
	 * ID: 1800473<br>
	 * Message: ���R�K�^�����ށB
	 */
	public static final NpcStringId YOU_MAY_GRAB_UNEXPECTED_LUCK;

	/**
	 * ID: 1800474<br>
	 * Message: �҂��l������B
	 */
	public static final NpcStringId THE_PERSON_IN_YOUR_HEART_MAY_NATURALLY_COME_TO_YOU;

	/**
	 * ID: 1800475<br>
	 * Message: ���l�̕]���ȂǋC�ɂ����Ƀ}�C�y�[�X���ێ�����΂������ʂ�������B
	 */
	public static final NpcStringId THERE_WILL_BE_A_GOOD_RESULT_IF_YOU_KEEP_YOUR_OWN_PACE_REGARDLESS_OF_OTHERS_JUDGMENT;

	/**
	 * ID: 1800476<br>
	 * Message: �\�z�O�̍K�^������ł��A��ЂŐ��A�ɋA���B���ӂ���B
	 */
	public static final NpcStringId BE_WARNED_AS_UNEXPECTED_LUCK_MAY_BE_WASTED_WITH_YOUR_RECKLESS_COMMENTS;

	/**
	 * ID: 1800477<br>
	 * Message: ���ŉ΂ɓ���Ă̒��B�ߐM�͋֕��B
	 */
	public static final NpcStringId OVERCONFIDENCE_WILL_CONVINCE_YOU_TO_CARRY_A_TASK_ABOVE_YOUR_REACH_AND_THERE_WILL_BE_CONSEQUENCES;

	/**
	 * ID: 1800478<br>
	 * Message: �d�v�Ȕ��f�͌�ɂ���B
	 */
	public static final NpcStringId MOMENTARILY_DELAY_AN_IMPORTANT_DECISION;

	/**
	 * ID: 1800479<br>
	 * Message: �ڏ�̐l�⒇�Ԃ̌��t�Ńg���u�������B
	 */
	public static final NpcStringId TROUBLE_SPOTS_LIE_AHEAD_WHEN_TALKING_TO_SUPERIORS_OR_PEOPLE_CLOSE_TO_YOU;

	/**
	 * ID: 1800480<br>
	 * Message: ���t�ő�����������菝����ꂽ�肷��B
	 */
	public static final NpcStringId BE_WARNED_AS_YOUR_WORDS_CAN_HURT_OTHERS_OR_OTHERS_WORDS_CAN_HURT_YOU;

	/**
	 * ID: 1800481<br>
	 * Message: �r�b�O�}�E�X�͘Q��̂��ƁB
	 */
	public static final NpcStringId MAKE_A_LOUD_BOAST_AND_YOU_MAY_HAVE_TO_PAY_TO_COVER_UNNECESSARY_EXPENSES;

	/**
	 * ID: 1800482<br>
	 * Message: ���������������邪�A���܂����킹�B�����Ȃ��Α化�܂ɂȂ�B
	 */
	public static final NpcStringId SKILLFUL_EVASION_IS_NEEDED_WHEN_DEALING_WITH_PEOPLE_WHO_PICK_FIGHTS_AS_A_DISASTER_MAY_ARISE_FROM_IT;

	/**
	 * ID: 1800483<br>
	 * Message: ���Ȏ咣�̂������Ŕ����𔃂��B
	 */
	public static final NpcStringId KEEP_A_LOW_PROFILE_AS_TOO_STRONG_AN_OPINION_WILL_ATTRACT_ADVERSE_REACTIONS;

	/**
	 * ID: 1800484<br>
	 * Message: �A������������ʂ悤�A����𔃂��s����T�߁B
	 */
	public static final NpcStringId DO_NOT_UNNECESSARILY_PROVOKE_MISUNDERSTANDING_AS_YOU_MAY_BE_INVOLVED_IN_MALICIOUS_GOSSIP;

	/**
	 * ID: 1800485<br>
	 * Message: �����ɒ��ӁB
	 */
	public static final NpcStringId CHECK_YOUR_BELONGINGS_AS_YOU_MAY_LOSE_WHAT_YOU_POSSESS;

	/**
	 * ID: 1800486<br>
	 * Message: ����ɘb�ɂ͂����Â������āB
	 */
	public static final NpcStringId BE_FLEXIBLE_ENOUGH_TO_PLAY_UP_TO_OTHERS;

	/**
	 * ID: 1800487<br>
	 * Message: �l�Ԋ֌W���������B����T�߁B
	 */
	public static final NpcStringId PAY_SPECIAL_ATTENTION_WHEN_MEETING_OR_TALKING_TO_PEOPLE_AS_RELATIONSHIPS_MAY_GO_AMISS;

	/**
	 * ID: 1800488<br>
	 * Message: �d�v�Ȍ���̏u�ԁB�l�ڂ��C�ɂ����A�v���ʂ�ɐi�߂�B�������A��C��ǂ߁B
	 */
	public static final NpcStringId WHEN_THE_IMPORTANT_MOMENT_ARRIVES_DECIDE_UPON_WHAT_YOU_TRULY_WANT_WITHOUT_MEASURING_OTHERS_JUDGMENT;

	/**
	 * ID: 1800489<br>
	 * Message: ���������ė��ɏo��B
	 */
	public static final NpcStringId LUCK_WILL_ALWAYS_FOLLOW_YOU_IF_YOU_TRAVEL_AND_READ_MANY_BOOKS;

	/**
	 * ID: 1800490<br>
	 * Message: �����A�C�f�B�A�������ԁB���Ȃ��̃A�h�o�C�X��K�v�Ƃ��Ă���Ƃ���ɍs���B
	 */
	public static final NpcStringId HEAD_TO_A_PLACE_THAT_NEEDS_YOUR_ADVICE_AS_GOOD_IDEAS_AND_WISDOM_WILL_FLOURISH;

	/**
	 * ID: 1800491<br>
	 * Message: ���Ȃ��̃A�h�o�C�X������̐l����ς���B
	 */
	public static final NpcStringId SOMEONES_LIFE_MAY_CHANGE_UPON_YOUR_ADVICE;

	/**
	 * ID: 1800492<br>
	 * Message: �ߎ���Ɋׂ邱�ƂȂ��A����������O���ɒu���B
	 */
	public static final NpcStringId ITS_A_PROPER_TIME_TO_PLAN_FOR_THE_FUTURE_RATHER_THAN_A_SHORT_TERM_PLAN;

	/**
	 * ID: 1800493<br>
	 * Message: �悭�Y��ł����v��𗧂Ă�B
	 */
	public static final NpcStringId MANY_THOUGHTFUL_PLANS_AT_PRESENT_TIME_WILL_BE_OF_GREAT_HELP_IN_THE_FUTURE;

	/**
	 * ID: 1800494<br>
	 * Message: �߂����l�Ƃ̌��܂ɒ��ӁB�䖝����̂݁B
	 */
	public static final NpcStringId PATIENCE_MAY_BE_NEEDED_AS_A_BIG_QUARREL_ARISES_BETWEEN_YOU_AND_A_PERSON_CLOSE_TO_YOU;

	/**
	 * ID: 1800495<br>
	 * Message: �����K�v�ł����S�͂���ȁB�v���C�h���������邾�����B
	 */
	public static final NpcStringId DO_NOT_ASK_FOR_FINANCIAL_HELP_WHEN_THE_TIME_IS_DIFFICULT_YOUR_PRIDE_WILL_BE_HURT_WITHOUT_GAINING_ANY_MONEY;

	/**
	 * ID: 1800496<br>
	 * Message: �����ȋ��R���ǉ��ɂȂ���B
	 */
	public static final NpcStringId CONNECTION_WITH_A_SPECIAL_PERSON_STARTS_WITH_A_MERE_INCIDENT;

	/**
	 * ID: 1800497<br>
	 * Message: ��łɂȂ�Ί�@�������B
	 */
	public static final NpcStringId STUBBORNNESS_REGARDLESS_OF_THE_MATTER_WILL_ONLY_BEAR_DANGER;

	/**
	 * ID: 1800498<br>
	 * Message: ������Ƃ����g���u���̎킠��B�������݁A�}�i�[�ɂ��C������B
	 */
	public static final NpcStringId KEEP_GOOD_MANNERS_AND_VALUE_TACITURNITY_AS_LIGHT_HEARTEDNESS_MAY_BRING_MISFORTUNE;

	/**
	 * ID: 1800499<br>
	 * Message: �ς�����ِ��ɏo��B
	 */
	public static final NpcStringId YOU_MAY_MEET_THE_OPPOSITE_SEX;

	/**
	 * ID: 1800500<br>
	 * Message: �~���肷���čЂ��������B
	 */
	public static final NpcStringId GREED_BY_WANTING_TO_TAKE_WEALTH_MAY_BRING_UNFORTUNATE_DISASTER;

	/**
	 * ID: 1800501<br>
	 * Message: ��������^���B�x�o��}����B
	 */
	public static final NpcStringId LOSS_IS_AHEAD_REFRAIN_FROM_INVESTING_TRY_TO_SAVE_THE_MONEY_IN_YOUR_POCKETS;

	/**
	 * ID: 1800502<br>
	 * Message: ���^�悩�炸�B���̖��S�͑���ɂ���ȁB
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_DIM_AVOID_ANY_OFFERS;

	/**
	 * ID: 1800503<br>
	 * Message: �������ׂ����Ƃ𖾓����悤�Ƃ���΋ꋫ�ɗ��B
	 */
	public static final NpcStringId A_BIGGER_CHALLENGE_MAY_BE_WHEN_DELAYING_TODAYS_WORK;

	/**
	 * ID: 1800504<br>
	 * Message: ����ɏP���Ă��ӔC�������Ė]�߂΂������ʂ𓾂�B
	 */
	public static final NpcStringId THERE_WILL_BE_DIFFICULTY_BUT_A_GOOD_RESULT_MAY_BE_AHEAD_WHEN_FACING_IT_RESPONSIBLY;

	/**
	 * ID: 1800505<br>
	 * Message: ����ǂ��Ă������ƐӔC�̂���d�����B�K����������B
	 */
	public static final NpcStringId EVEN_WITH_SOME_DIFFICULTIES_EXPAND_THE_RANGE_OF_YOUR_SCOPE_WHERE_YOU_ARE_IN_CHARGE_IT_WILL_RETURN_TO_YOU_AS_HELP;

	/**
	 * ID: 1800506<br>
	 * Message: �g�Ӑ��������˂΁A�W���ł��Ȃ��B
	 */
	public static final NpcStringId FOCUS_ON_MAINTAINING_ORGANIZED_SURROUNDINGS_TO_HELP_REDUCE_YOUR_LOSSES;

	/**
	 * ID: 1800507<br>
	 * Message: �l�͒ǂ������҂��g�B
	 */
	public static final NpcStringId LUCK_LIES_AHEAD_WHEN_WAITING_FOR_PEOPLE_RATHER_THAN_FOLLOWING_THEM;

	/**
	 * ID: 1800508<br>
	 * Message: �ً}���ł����Ă������炩���������L�ׂ�ׂ��炸�B�p���č��܂��j�ڂɁB
	 */
	public static final NpcStringId DO_NOT_OFFER_YOUR_HAND_FIRST_EVEN_WHEN_THINGS_ARE_HASTY_THE_RELATIONSHIP_MAY_FALL_APART;

	/**
	 * ID: 1800509<br>
	 * Message: ���^�㏸�B
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_RISING_THERE_WILL_BE_SOME_GOOD_RESULT;

	/**
	 * ID: 1800510<br>
	 * Message: �v�����Ŏ���i�߂�΁A��@�ɕm����B
	 */
	public static final NpcStringId YOU_MAY_FALL_IN_DANGER_EACH_TIME_WHEN_ACTING_UPON_IMPROVISATION;

	/**
	 * ID: 1800511<br>
	 * Message: �ڏ�̐l�̑O�ł͍Α����̐U�镑�����B
	 */
	public static final NpcStringId BE_WARNED_AS_A_CHILDISHLY_ACT_BEFORE_ELDERS_MAY_RUIN_EVERYTHING;

	/**
	 * ID: 1800512<br>
	 * Message: �V��ɂȂ�Ή^������B
	 */
	public static final NpcStringId THINGS_WILL_MOVE_EFFORTLESSLY_BUT_LUCK_WILL_VANISH_WITH_YOUR_AUDACITY;

	/**
	 * ID: 1800513<br>
	 * Message: �������^�𒷎���������B
	 */
	public static final NpcStringId LUCK_MAY_BE_CONTINUED_ONLY_WHEN_HUMILITY_IS_MAINTAINED_AFTER_SUCCESS;

	/**
	 * ID: 1800514<br>
	 * Message: ��O�҂̏o���ɂ��O�p�֌W�ɂ��p�S�B
	 */
	public static final NpcStringId A_NEW_PERSON_MAY_APPEAR_TO_CREATE_A_LOVE_TRIANGLE;

	/**
	 * ID: 1800515<br>
	 * Message: �����Ɠ����X�^�C���̐l��T���B
	 */
	public static final NpcStringId LOOK_FOR_SOMEONE_WITH_A_SIMILAR_STYLE_IT_WILL_OPEN_UP_FOR_THE_GOOD;

	/**
	 * ID: 1800516<br>
	 * Message: �I�t�@�[�͒f��B���͎����ł͂Ȃ��B
	 */
	public static final NpcStringId AN_OFFER_MAY_SOON_BE_MADE_TO_COLLABORATE_A_TASK_BUT_DELAYING_IT_WILL_BE_A_GOOD_IDEA;

	/**
	 * ID: 1800517<br>
	 * Message: �}������悤�ȃI�t�@�[�͒f��B
	 */
	public static final NpcStringId PARTNERSHIP_IS_OUT_OF_LUCK_AVOID_SOMEONE_WHO_RUSHES_YOU_TO_START_A_COLLABORATION;

	/**
	 * ID: 1800518<br>
	 * Message: �u�����ɂ���l�X�̃l�b�g���[�N�����B��ɑ傫���d�������邱�ƂɂȂ�B
	 */
	public static final NpcStringId FOCUS_ON_NETWORKING_WITH_LIKE_MINDED_PEOPLE_THEY_MAY_JOIN_YOU_FOR_A_BIG_MISSION_IN_THE_FUTURE;

	/**
	 * ID: 1800519<br>
	 * Message: ���Ȃ��͏��^�Ȑl���ƌ����ċߊ���Ă���҂ɋC������B
	 */
	public static final NpcStringId BE_WARNED_WHEN_SOMEONE_SAYS_YOU_ARE_INNOCENT_AS_THATS_NOT_A_COMPLIMENT;

	/**
	 * ID: 1800520<br>
	 * Message: ���\�̋��ꂠ��B�Ȃ߂�ꂽ��呹����B
	 */
	public static final NpcStringId YOU_MAY_BE_SCAMMED_BE_CAUTIOUS_AS_THERE_MAY_BE_A_BIG_LOSS_BY_UNDERESTIMATING_OTHERS;

	/**
	 * ID: 1800521<br>
	 * Message: ���͌���̎����ł͂Ȃ��B�����̍l�����펯�ɉ��������f���B
	 */
	public static final NpcStringId LUCK_AT_DECISION_MAKING_IS_DIM_AVOID_SUBJECTIVE_CONCLUSIONS_AND_RELY_ON_UNIVERSAL_COMMON_SENSE;

	/**
	 * ID: 1800522<br>
	 * Message: �S�̎コ�ŏd�ׂ�w�������ށB���Ȏ咣�͂͂�����ƁB
	 */
	public static final NpcStringId YOUR_WEAKNESS_MAY_INVITE_HARDSHIPS_CAUTIOUSLY_TAKE_A_STRONG_POSITION_AS_NEEDED;

	/**
	 * ID: 1800523<br>
	 * Message: ������ׂ�̗V�ѐl�ɗp�S����B�g���u���̋��ꂠ��B
	 */
	public static final NpcStringId BE_WARY_OF_SOMEONE_WHO_TALKS_AND_ENTERTAINS_TOO_MUCH_THE_PERSON_MAY_BRING_YOU_MISFORTUNE;

	/**
	 * ID: 1800524<br>
	 * Message: �r�M�i�[�Y���b�N�̗\������B
	 */
	public static final NpcStringId YOU_MAY_ENJOY_A_BEGINNERS_LUCK;

	/**
	 * ID: 1800525<br>
	 * Message: ���^�͂������A�����m��ׂ��B
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_STRONG_BUT_YOU_SHOULD_KNOW_WHEN_TO_WITHDRAW;

	/**
	 * ID: 1800526<br>
	 * Message: �~���肷���Ėׂ��������B
	 */
	public static final NpcStringId ALREADY_ACQUIRED_WEALTH_CAN_BE_LOST_BY_GREED;

	/**
	 * ID: 1800527<br>
	 * Message: ��l�łł��邱�Ƃł��N���Ƃ��Ƃ����B
	 */
	public static final NpcStringId EVEN_IF_YOU_CAN_COMPLETE_IT_BY_YOURSELF_ITS_A_GOOD_IDEA_TO_HAVE_SOMEONE_HELP_YOU;

	/**
	 * ID: 1800528<br>
	 * Message: �ł��̐S�Ȃ��Ƃ͘a�B�킪�܂܂������΋ꋫ�ɗ��B
	 */
	public static final NpcStringId MAKE_HARMONY_WITH_PEOPLE_THE_PRIORITY_STUBBORNNESS_MAY_BRING_HARDSHIPS;

	/**
	 * ID: 1800529<br>
	 * Message: �߂����l�̒m��Ȃ��������Ƃ��킩��B
	 */
	public static final NpcStringId THERE_MAY_BE_A_CHANCE_WHEN_YOU_CAN_SEE_A_NEW_ASPECT_OF_A_CLOSE_FRIEND;

	/**
	 * ID: 1800530<br>
	 * Message: ����ς��̂Ă�B�����Ƃ͈Ⴄ�^�C�v�̐l�Ƃ��������B
	 */
	public static final NpcStringId TRY_TO_BE_CLOSE_TO_SOMEONE_DIFFERENT_FROM_YOU_WITHOUT_ANY_STEREOTYPICAL_JUDGMENT;

	/**
	 * ID: 1800531<br>
	 * Message: ���[�_�[�ɂȂ�\���B�l�̏�ɗ����A�������炢�B
	 */
	public static final NpcStringId GOOD_LUCK_IN_BECOMING_A_LEADER_WITH_MANY_FOLLOWERS_HOWEVER_ITLL_ONLY_BE_AFTER_HARD_WORK;

	/**
	 * ID: 1800532<br>
	 * Message: ���^�㏸�B�����ē����B
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_RISING_EXPENDITURES_WILL_BE_FOLLOWED_BY_SUBSTANTIAL_INCOME_AS_YOU_ARE_ABLE_TO_SUSTAIN;

	/**
	 * ID: 1800533<br>
	 * Message: ���^�͍ŗǂ��ň��̂ǂ��炩�B
	 */
	public static final NpcStringId BE_CAUTIOUS_AS_YOUR_WEALTH_LUCK_CAN_BE_EITHER_VERY_GOOD_OR_VERY_BAD;

	/**
	 * ID: 1800534<br>
	 * Message: ���ׂȑ����ŋ߂����l�Ƃ̒��������Ȃ�B
	 */
	public static final NpcStringId BE_WARNED_AS_A_SMALL_ARGUMENT_CAN_DISTANCE_YOU_FROM_A_CLOSE_FRIEND;

	/**
	 * ID: 1800535<br>
	 * Message: �����^�㏸�B
	 */
	public static final NpcStringId THERE_IS_LUCK_IN_LOVE_WITH_A_NEW_PERSON;

	/**
	 * ID: 1800536<br>
	 * Message: ��͐l�̂��߂Ȃ炸�B
	 */
	public static final NpcStringId A_BIGGER_FORTUNE_WILL_BE_FOLLOWED_BY_YOUR_GOOD_DEED;

	/**
	 * ID: 1800537<br>
	 * Message: ����𔃂��Ύ����̋��ꂠ��B
	 */
	public static final NpcStringId THERE_MAY_BE_A_RELATIONSHIP_BREAKING_TRY_TO_ELIMINATE_MISUNDERSTANDINGS;

	/**
	 * ID: 1800538<br>
	 * Message: �������󂯂Ă��ȒP�ɐM���Ă͂Ȃ�Ȃ��B
	 */
	public static final NpcStringId BE_CAUTIOUS_NOT_TO_BE_EMOTIONALLY_MOVED_EVEN_IF_ITS_CONVINCING;

	/**
	 * ID: 1800539<br>
	 * Message: �΂���ɂ͕�������B
	 */
	public static final NpcStringId SMILING_WILL_BRING_GOOD_LUCK;

	/**
	 * ID: 1800540<br>
	 * Message: �����ȑ��ł��܂ł����悭�悷��ȁB
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_TO_LET_GO_OF_A_SMALL_LOSS;

	/**
	 * ID: 1800541<br>
	 * Message: �R�~���j�P�[�V���������܂��������A�����������B
	 */
	public static final NpcStringId CONVEYING_YOUR_OWN_TRUTH_MAY_BE_DIFFICULT_AND_EASY_MISUNDERSTANDINGS_WILL_FOLLOW;

	/**
	 * ID: 1800542<br>
	 * Message: �l�̏W�܂�Ƃ��납��K�^������Ă���B
	 */
	public static final NpcStringId THERE_IS_GOOD_LUCK_IN_A_PLACE_WITH_MANY_PEOPLE;

	/**
	 * ID: 1800543<br>
	 * Message: �܂�����������̂͋p���Ă悭�Ȃ��B
	 */
	public static final NpcStringId TRY_TO_AVOID_DIRECTNESS_IF_YOU_CAN;

	/**
	 * ID: 1800544<br>
	 * Message: �����ڂ�蒆�g���̐S�B
	 */
	public static final NpcStringId VALUE_SUBSTANCE_OPPOSED_TO_THE_SAKE_HONOR_AND_LOOK_BEYOND_WHATS_IN_FRONT_OF_YOU;

	/**
	 * ID: 1800545<br>
	 * Message: �l�Ԋ֌W�͂�����ƌ����܂������炢�������B
	 */
	public static final NpcStringId EXPANDING_A_RELATIONSHIP_WITH_HUMOR_MAY_BE_A_GOOD_IDEA;

	/**
	 * ID: 1800546<br>
	 * Message: ������Ƃ����q���Ŗׂ���B
	 */
	public static final NpcStringId AN_ENJOYABLE_EVENT_MAY_BE_AHEAD_IF_YOU_ACCEPT_A_SIMPLE_BET;

	/**
	 * ID: 1800547<br>
	 * Message: �l�Ԋ֌W�̓E�F�b�g�ɂȂ肷���Ȃ��悤�ɁB��Âɍl���邱�Ƃ��K�v�B
	 */
	public static final NpcStringId BEING_LEVEL_HEADED_NOT_FOCUSING_ON_EMOTIONS_MAY_HELP_WITH_RELATIONSHIPS;

	/**
	 * ID: 1800548<br>
	 * Message: �d���͌y�d���킸�����ǂ���i�߂�ׂ��B
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_TO_TAKE_CARE_OF_MATTERS_IN_SEQUENTIAL_ORDER_WITHOUT_MEASURING_THEIR_IMPORTANCE;

	/**
	 * ID: 1800549<br>
	 * Message: �������蒲�ׂĎ��M�������čs������΁A�F�ɕ����B
	 */
	public static final NpcStringId A_DETERMINED_ACT_AFTER_PREPARED_RESEARCH_WILL_ATTRACT_PEOPLE;

	/**
	 * ID: 1800550<br>
	 * Message: �΂�������Ď������W�߂�B
	 */
	public static final NpcStringId A_LITTLE_HUMOR_MAY_BRING_COMPLETE_ATTENTION_TO_YOU;

	/**
	 * ID: 1800551<br>
	 * Message: �d�v�Ȍ���͉�������B���K�̎���͔�����B
	 */
	public static final NpcStringId IT_MAY_NOT_BE_A_GOOD_TIME_FOR_AN_IMPORTANT_DECISION_BE_WARY_OF_TEMPTATIONS_AND_AVOID_MONETARY_DEALINGS;

	/**
	 * ID: 1800552<br>
	 * Message: �߂����l�̏�����S�ɍ��߁B
	 */
	public static final NpcStringId PAY_SPECIAL_ATTENTION_TO_ADVICE_FROM_A_CLOSE_FRIEND;

	/**
	 * ID: 1800553<br>
	 * Message: �����Ȃ������O�҂̗��ꂩ��l����B�~���ȉ����􂪌�����B
	 */
	public static final NpcStringId THERE_MAY_BE_MODERATE_SOLUTIONS_TO_EVERY_PROBLEM_WHEN_THEYRE_VIEWED_FROM_A_3RD_PARTYS_POINT_OF_VIEW;

	/**
	 * ID: 1800554<br>
	 * Message: �߂����l�Ƃ̎���͒f��B���̒ɂ����Ƃ�������B
	 */
	public static final NpcStringId DEALINGS_WITH_CLOSE_FRIENDS_ONLY_BRING_FRUSTRATION_AND_HEADACHE_POLITELY_DECLINE_AND_MENTION_ANOTHER_CHANCE;

	/**
	 * ID: 1800555<br>
	 * Message: ��b��a���ɂ���Ύd�グ�ɖ�肪������B
	 */
	public static final NpcStringId THERE_MAY_BE_A_PROBLEM_AT_COMPLETION_IF_THE_BASIC_MATTERS_ARE_NOT_CONSIDERED_FROM_THE_BEGINNING;

	/**
	 * ID: 1800556<br>
	 * Message: �����̋�ʂ��͂�����ƁB
	 */
	public static final NpcStringId DISTINGUISHING_BUSINESS_FROM_A_PRIVATE_MATTER_IS_NEEDED_TO_SUCCEED;

	/**
	 * ID: 1800557<br>
	 * Message: �d������肭�����Ȃ��Ȃ牡�������Ă݂�̂���̎�B
	 */
	public static final NpcStringId A_CHANGE_IN_RULES_MAY_BE_HELPFUL_WHEN_PROBLEMS_ARE_PERSISTENT;

	/**
	 * ID: 1800558<br>
	 * Message: �����Ȃ��Ƃ��Ɣ�΂��΁A�\�z�O�̏󋵂ɋꂵ�߂���B
	 */
	public static final NpcStringId PREPARING_FOR_AN_UNFORESEEN_SITUATION_WILL_BE_DIFFICULT_WHEN_SMALL_MATTERS_ARE_IGNORED;

	/**
	 * ID: 1800559<br>
	 * Message: ���l���ɂ͕K�v�ȏ���˂����ނȁB
	 */
	public static final NpcStringId REFRAIN_FROM_GETTING_INVOLVED_IN_OTHERS_BUSINESS_TRY_TO_BE_LOOSE_AS_A_GOOSE;

	/**
	 * ID: 1800560<br>
	 * Message: ���f����؂����A�m���Ȉӎv�\�������ɂ͕K�v�B
	 */
	public static final NpcStringId BEING_NEUTRAL_IS_A_GOOD_WAY_TO_GO_BUT_CLARITY_MAY_BE_HELPFUL_CONTRARY_TO_YOUR_HESITANCE;

	/**
	 * ID: 1800561<br>
	 * Message: �ߋ��̂��ƂŌ����������B�s���ɒ��ӂ���B
	 */
	public static final NpcStringId BE_CAUTIOUS_OF_YOUR_OWN_ACTIONS_THE_PAST_MAY_BRING_MISUNDERSTANDINGS;

	/**
	 * ID: 1800562<br>
	 * Message: ��ɗ�����Ď��Ԃ𖳑ʂɂ���B���ԊǗ��ɋC������B
	 */
	public static final NpcStringId PAY_ATTENTION_TO_TIME_MANAGEMENT_EMOTIONS_MAY_WASTE_YOUR_TIME;

	/**
	 * ID: 1800563<br>
	 * Message: ���g�̂Ȃ��`���S�͈Ӗ����Ȃ��B
	 */
	public static final NpcStringId HEROISM_WILL_BE_REWARDED_BUT_BE_CAREFUL_NOT_TO_DISPLAY_ARROGANCE_OR_LACK_OF_SINCERITY;

	/**
	 * ID: 1800564<br>
	 * Message: ������ꂽ�l�ƒ����肹��B�֌W���񕜂���B
	 */
	public static final NpcStringId IF_YOU_WANT_TO_MAINTAIN_RELATIONSHIP_CONNECTIONS_OFFER_RECONCILIATION_TO_THOSE_WHO_HAD_MISUNDERSTANDINGS_WITH_YOU;

	/**
	 * ID: 1800565<br>
	 * Message: ����ɍs���ɂ��ǂ���������������A�����ɏ��o���B
	 */
	public static final NpcStringId STEP_FORWARD_TO_SOLVE_OTHERS_PROBLEMS_WHEN_THEY_ARE_UNABLE;

	/**
	 * ID: 1800566<br>
	 * Message: �኱�̑����������ւ̓����B
	 */
	public static final NpcStringId THERE_MAY_BE_A_LITTLE_LOSS_BUT_THINK_OF_IT_AS_AN_INVESTMENT_FOR_YOURSELF;

	/**
	 * ID: 1800567<br>
	 * Message: �~���~���ĂԁB�����m��ׂ��B
	 */
	public static final NpcStringId AVARICE_BEARS_A_BIGGER_GREED_BEING_SATISFIED_WITH_MODERATION_IS_NEEDED;

	/**
	 * ID: 1800568<br>
	 * Message: �����l��������i�߂�΃g���u���Ɋ������܂��B�󋵂��悭�ǂ߁B
	 */
	public static final NpcStringId A_RATIONAL_ANALYSIS_IS_NEEDED_AS_UNPLANNED_ACTIONS_MAY_BRING_CRITICISM;

	/**
	 * ID: 1800569<br>
	 * Message: �l��ӂ߂��莩����Ȃ݂�B
	 */
	public static final NpcStringId REFLECT_UPON_YOUR_SHORTCOMINGS_BEFORE_CRITICIZING_OTHERS;

	/**
	 * ID: 1800570<br>
	 * Message: �Ƃ����̍s���Ŋ�@�𓦂��B�㏈������������ƁB
	 */
	public static final NpcStringId FOLLOW_UP_CARE_IS_ALWAYS_NEEDED_AFTER_AN_EMERGENCY_EVASION;

	/**
	 * ID: 1800571<br>
	 * Message: �V���Ȓ���ւ̗~�����킭�B���L�����ׂ�B
	 */
	public static final NpcStringId YOU_MAY_LOOK_FOR_A_NEW_CHALLENGE_BUT_VAST_KNOWLEDGE_IS_REQUIRED;

	/**
	 * ID: 1800572<br>
	 * Message: �v���C�h���̂Ă�Ό����������B
	 */
	public static final NpcStringId WHEN_ONE_PUTS_ASIDE_THEIR_EGO_ANY_MISUNDERSTANDING_WILL_BE_SOLVED;

	/**
	 * ID: 1800573<br>
	 * Message: �V��ɂȂ炸����̏����Ɏ����X����B
	 */
	public static final NpcStringId LISTEN_TO_THE_ADVICE_THATS_GIVEN_TO_YOU_WITH_A_HUMBLE_ATTITUDE;

	/**
	 * ID: 1800574<br>
	 * Message: ����ɂ��鎞�����ɔ�����B
	 */
	public static final NpcStringId EQUILIBRIUM_IS_ACHIEVED_WHEN_ONE_UNDERSTANDS_A_DOWNSHIFT_IS_EVIDENT_AFTER_THE_RISE;

	/**
	 * ID: 1800575<br>
	 * Message: ����܂������������n������B�v��ʂ�ɐ����ɐi�߂�B
	 */
	public static final NpcStringId WHAT_YOU_SOW_IS_WHAT_YOU_REAP_FAITHFULLY_FOLLOW_THE_PLAN;

	/**
	 * ID: 1800576<br>
	 * Message: �v�����Ŏ���i�߂�ΐ��_�I�A���K�I�ɑ呹����B�p�ӂ͂�������ƁB
	 */
	public static final NpcStringId METICULOUS_PREPARATION_IS_NEEDED_AS_SPONTANEOUS_ACTIONS_ONLY_BEAR_MENTAL_AND_MONETARY_LOSSES;

	/**
	 * ID: 1800577<br>
	 * Message: �l�̖ڂɋC������ĂȂ��Ȃ��I���Ȃ��B
	 */
	public static final NpcStringId THE_RIGHT_TIME_TO_BEAR_FRUIT_IS_DELAYED_WHILE_THE_FARMER_PONDERS_OPINIONS;

	/**
	 * ID: 1800578<br>
	 * Message: ���ǂ����m�ŏ��������B
	 */
	public static final NpcStringId HELP_EACH_OTHER_AMONG_CLOSE_FRIENDS;

	/**
	 * ID: 1800579<br>
	 * Message: �ڐ�̍��ׂȗ��v�ɕ߂����ȁB�l�Ƃ̊֌W���������B
	 */
	public static final NpcStringId OBSESSING_OVER_A_SMALL_PROFIT_WILL_PLACE_PEOPLE_APART;

	/**
	 * ID: 1800580<br>
	 * Message: �q���ɕ����Ă����悭�悷��ȁB
	 */
	public static final NpcStringId DONT_CLING_TO_THE_RESULT_OF_A_GAMBLE;

	/**
	 * ID: 1800581<br>
	 * Message: �����Ȏ��̂�g���u���̋��ꂠ��B�]�T�����āB
	 */
	public static final NpcStringId SMALL_TROUBLES_AND_ARGUMENTS_ARE_AHEAD_FACE_THEM_WITH_A_MATURE_ATTITUDE;

	/**
	 * ID: 1800582<br>
	 * Message: �񑩂�j��΋ꋫ�ɗ��B
	 */
	public static final NpcStringId NEGLECTING_A_PROMISE_MAY_PUT_YOU_IN_DISTRESS;

	/**
	 * ID: 1800583<br>
	 * Message: �����̋��ꂠ��B����͕ۗ�����B
	 */
	public static final NpcStringId DELAY_ANY_DEALINGS_AS_YOU_MAY_EASILY_OMIT_ADDRESSING_WHATS_IMPORTANT_TO_YOU;

	/**
	 * ID: 1800584<br>
	 * Message: �l�Ƃ̔�r�������ɂȂ�B
	 */
	public static final NpcStringId A_COMPARISON_TO_OTHERS_MAY_BE_HELPFUL;

	/**
	 * ID: 1800585<br>
	 * Message: �����ɂ͒ɂ݂��t�����B��J����b�オ����B
	 */
	public static final NpcStringId WHAT_YOUVE_ENDURED_WILL_RETURN_AS_A_BENEFIT;

	/**
	 * ID: 1800586<br>
	 * Message: ��V������Đ������s���B
	 */
	public static final NpcStringId TRY_TO_BE_COURTEOUS_TO_THE_OPPOSITE_SEX_AND_FOLLOW_A_VIRTUOUS_PATH;

	/**
	 * ID: 1800587<br>
	 * Message: �������Ȃ��Ƃő傫�ȏ΂��B
	 */
	public static final NpcStringId JOY_MAY_COME_FROM_SMALL_THINGS;

	/**
	 * ID: 1800588<br>
	 * Message: ���ʂ悵�B���M�������čs������B
	 */
	public static final NpcStringId BE_CONFIDENT_IN_YOUR_ACTIONS_AS_GOOD_LUCK_SHADOWS_THE_RESULT;

	/**
	 * ID: 1800589<br>
	 * Message: ��܂����Ƃ��낪�Ȃ��Ȃ瓰�X�ƌ����������B
	 */
	public static final NpcStringId BE_CONFIDENT_WITHOUT_HESITATION_WHEN_YOUR_HONESTY_IS_ABOVE_REPROACH_IN_DEALINGS;

	/**
	 * ID: 1800590<br>
	 * Message: �e�������ɂ���V����B�����Ȃ��ΌǗ������̋���B
	 */
	public static final NpcStringId A_MATTER_RELATED_TO_A_CLOSE_FRIEND_CAN_ISOLATE_YOU_KEEP_STAYING_ON_THE_RIGHT_PATH;

	/**
	 * ID: 1800591<br>
	 * Message: ���ʂ̂��Ƃł��܂ł����悭�悷��ȁB�悩��ʂ��Ƃ��N����B
	 */
	public static final NpcStringId TOO_MUCH_FOCUS_ON_THE_RESULT_MAY_BRING_CONTINUOUS_MISFORTUNE;

	/**
	 * ID: 1800592<br>
	 * Message: �r���œ����o���Ό�X���������ƂɂȂ�B���C�����āB
	 */
	public static final NpcStringId BE_TENACIOUS_UNTIL_THE_FINISH_AS_HALFWAY_ABANDONMENT_CAUSES_A_TROUBLED_ENDING;

	/**
	 * ID: 1800593<br>
	 * Message: �c�̍s���ł͓������Ȃ��B
	 */
	public static final NpcStringId THERE_WILL_BE_NO_ADVANTAGE_IN_A_GROUP_DEAL;

	/**
	 * ID: 1800594<br>
	 * Message: �ł���΂炸��������B�󋵂ɂ͔\���I�ɑΏ�����B
	 */
	public static final NpcStringId REFRAIN_FROM_STEPPING_UP_BUT_TAKE_A_MOMENT_TO_PONDER_TO_BE_FLEXIBLE_WITH_SITUATIONS;

	/**
	 * ID: 1800595<br>
	 * Message: �����ő�����p���ď����ȍK�^�����߁B
	 */
	public static final NpcStringId THERE_WILL_BE_A_SMALL_OPPORTUNITY_WHEN_INFORMATION_IS_BEST_UTILIZED;

	/**
	 * ID: 1800596<br>
	 * Message: �����̋��ꂠ��B�厖�Ȃ��̂Ȃ炿���ƕۊǂ���B
	 */
	public static final NpcStringId BELONGINGS_ARE_AT_LOOSE_ENDS_KEEP_TRACK_OF_THE_THINGS_YOU_VALUE;

	/**
	 * ID: 1800597<br>
	 * Message: �w�͕͂����B
	 */
	public static final NpcStringId WHAT_YOU_SOW_IS_WHAT_YOU_REAP_TRY_YOUR_BEST;

	/**
	 * ID: 1800598<br>
	 * Message: �V���ɃX�^�[�g��؂�C�����ŗՂ߂΁A�s���������ɕ₦��B
	 */
	public static final NpcStringId WITH_THE_BEGINNERS_ATTITUDE_SHORTCOMINGS_CAN_BE_EASILY_MENDED;

	/**
	 * ID: 1800599<br>
	 * Message: ��肪����Ε��������S�ɕς���B
	 */
	public static final NpcStringId WHEN_FACING_DIFFICULTIES_SEEK_A_TOTALLY_DIFFERENT_DIRECTION;

	/**
	 * ID: 1800600<br>
	 * Message: �~�̒��肷���Őςݏd�˂Ă������Ƃ���x�ɕ����B
	 */
	public static final NpcStringId LIFETIME_SAVINGS_CAN_DISAPPEAR_WITH_ONE_TIME_GREED;

	/**
	 * ID: 1800601<br>
	 * Message: �ɘ_�͎�������ΐS���₩�B
	 */
	public static final NpcStringId WITH_YOUR_HEART_AVOID_EXTREMES_AND_PEACE_WILL_STAY;

	/**
	 * ID: 1800602<br>
	 * Message: ��u�̖��f�ŉA�������������j�ڂɁB
	 */
	public static final NpcStringId BE_CAUTIOUS_AS_INSTANT_RECKLESSNESS_MAY_BRING_MALICIOUS_GOSSIP;

	/**
	 * ID: 1800603<br>
	 * Message: �����~�����ӂ��B������߂��Ō�܂ł���΂�B
	 */
	public static final NpcStringId BE_TENACIOUS_TO_THE_END_BECAUSE_A_STRONG_LUCK_WITH_WINNING_IS_AHEAD;

	/**
	 * ID: 1800604<br>
	 * Message: �߂����l�ɂ͋C���g���₳��������B��͐l�̂��߂Ȃ炸�B
	 */
	public static final NpcStringId BE_KIND_TO_AND_CARE_FOR_THOSE_CLOSE_TO_YOU_THEY_MAY_HELP_IN_THE_FUTURE;

	/**
	 * ID: 1800605<br>
	 * Message: �|�W�e�B�u�V���L���O���������ʂɂȂ���B
	 */
	public static final NpcStringId POSITIVITY_MAY_BRING_GOOD_RESULTS;

	/**
	 * ID: 1800606<br>
	 * Message: �߂����l�̃~�X�͂��΂��Ă��B
	 */
	public static final NpcStringId BE_GRACIOUS_TO_COVER_A_CLOSE_FRIENDS_FAULT;

	/**
	 * ID: 1800607<br>
	 * Message: �\�z�O�̎x�o����B
	 */
	public static final NpcStringId BE_PREPARED_FOR_AN_EXPECTED_COST;

	/**
	 * ID: 1800608<br>
	 * Message: ���������Ă������ł��Ȃ���Ό��ǂ�������B�؂�ʂ����v�������B
	 */
	public static final NpcStringId BE_CONSIDERATE_TO_OTHERS_AND_AVOID_FOCUSING_ONLY_ON_WINNING_OR_A_WOUND_WILL_BE_LEFT_UNTREATED;

	/**
	 * ID: 1800609<br>
	 * Message: �A�N�Z�T���[�����b�L�[�A�C�e���B
	 */
	public static final NpcStringId AN_ACCESSORY_OR_DECORATION_MAY_BRING_A_GOOD_LUCK;

	/**
	 * ID: 1800610<br>
	 * Message: ���d���Č�������B����͖L�����B
	 */
	public static final NpcStringId ONLY_REFLECTION_AND_HUMILITY_MAY_BRING_SUCCESS;

	/**
	 * ID: 1800611<br>
	 * Message: ���ׂȌ���������ɂȂ���B
	 */
	public static final NpcStringId A_SMALL_MISUNDERSTANDING_MAY_CAUSE_QUARRELS;

	/**
	 * ID: 1800612<br>
	 * Message: �����������]�T�������ĉ~���ɐi�߂�B
	 */
	public static final NpcStringId AVOID_ADVANCING_BEYOND_YOUR_ABILITY_AND_FOCUS_ON_THE_FLOWING_STREAM;

	/**
	 * ID: 1800613<br>
	 * Message: �~���炸�v�����̐S�����āB�S�Ȃ����Ă������ʂ����낤���B
	 */
	public static final NpcStringId CONSIDERING_OTHERS_WITH_A_GOOD_HEART_BEFORE_SELF_INTEREST_WILL_BRING_A_TRIUMPH;

	/**
	 * ID: 1800614<br>
	 * Message: �s���Ȃ������Ƃ���ɍs����B
	 */
	public static final NpcStringId VISITING_A_PLACE_YOUVE_NEVER_BEEN_BEFORE_MAY_BRING_LUCK;

	/**
	 * ID: 1800615<br>
	 * Message: �l�̏W�܂�Ƃ��납��K�^������Ă���B
	 */
	public static final NpcStringId A_GOOD_THING_MAY_HAPPEN_IN_A_PLACE_WITH_A_FEW_PEOPLE;

	/**
	 * ID: 1800616<br>
	 * Message: �����Ȃ̂͂������y�������ĐM�p�������B���[���A�������Ă��A�^�ʖڂ����K�v�B
	 */
	public static final NpcStringId BEING_HIGH_STRUNG_CAN_CAUSE_LOSS_OF_TRUST_FROM_OTHERS_BECAUSE_IT_CAN_BE_VIEWED_AS_LIGHT_HEARTED_ACT_SINCERELY_BUT_YET_DO_NOT_LACK_HUMOR;

	/**
	 * ID: 1800617<br>
	 * Message: �ߒ��ɖ�肪�����Ă��A�㏈�����������肷��΂������ʂ�������B
	 */
	public static final NpcStringId PERFECTION_AT_THE_FINISH_CAN_COVER_FAULTY_WORK_IN_THE_PROCESS;

	/**
	 * ID: 1800618<br>
	 * Message: �d����������Γ�������̂������B�ꏊ����������΂��������̕�܂�������B
	 */
	public static final NpcStringId ABSTAIN_FROM_LAZINESS_MUCH_WORK_BRINGS_MANY_GAINS_AND_SATISFACTORY_REWARDS;

	/**
	 * ID: 1800619<br>
	 * Message: �ꃖ���ɗ��܂炸�ǂ�ǂ񓮂��B
	 */
	public static final NpcStringId STAYING_BUSY_RATHER_THAN_BEING_STATIONARY_WILL_HELP;

	/**
	 * ID: 1800620<br>
	 * Message: ��l�Ŕw��������ŗU�f�ł��߂ɂȂ�B
	 */
	public static final NpcStringId HANDLING_THE_WORK_BY_YOURSELF_MAY_LEAD_YOU_INTO_TEMPTATION;

	/**
	 * ID: 1800621<br>
	 * Message: �����Ȃ��Ƃł������X����B
	 */
	public static final NpcStringId PAY_ATTENTION_TO_ANY_SMALL_ADVICE_WITHOUT_BEING_INDIFFERENT;

	/**
	 * ID: 1800622<br>
	 * Message: ��炵�ג��҂̗\���B�����Ȃ��Ƃ���؂ɂ���B
	 */
	public static final NpcStringId SMALL_THINGS_MAKE_UP_BIG_THINGS_SO_EVEN_VALUE_TRIVIAL_MATTERS;

	/**
	 * ID: 1800623<br>
	 * Message: �����������̂�҂��A����Ă��܂��B
	 */
	public static final NpcStringId ACTION_TOWARD_THE_RESULT_RATHER_THAN_WAITING_FOR_THE_RIGHT_CIRCUMSTANCES_MAY_LEAD_YOU_TO_A_FAST_SUCCESS;

	/**
	 * ID: 1800624<br>
	 * Message: ������Ƃ����x�o�͎���̏����ɂȂ���B�ɂ��ނȁB
	 */
	public static final NpcStringId DONT_TRY_TO_SAVE_SMALL_EXPENDITURES_IT_WILL_LEAD_TO_FUTURE_RETURNS;

	/**
	 * ID: 1800625<br>
	 * Message: �U�f�ɘf�킳��₷���B�����}����B
	 */
	public static final NpcStringId BE_CAUTIOUS_TO_CONTROL_EMOTIONS_AS_TEMPTATIONS_ARE_NEARBY;

	/**
	 * ID: 1800626<br>
	 * Message: �����Ȃ��Ƃ��Ƒa���ɂ��āA��ŋ������ƂɂȂ�B
	 */
	public static final NpcStringId BE_WARNED_AS_NEGLECTING_A_MATTER_BECAUSE_ITS_SMALL_CAN_CAUSE_YOU_TROUBLE;

	/**
	 * ID: 1800627<br>
	 * Message: �ߖ�����x���B�g���ׂ��Ƃ��͎g���B
	 */
	public static final NpcStringId SPEND_WHEN_NEEDED_RATHER_THAN_TRYING_TO_UNCONDITIONALLY_SAVE;

	/**
	 * ID: 1800628<br>
	 * Message: �Ό��ɂƂ����΁A���𓾂Ă���������B
	 */
	public static final NpcStringId PREJUDICE_WILL_TAKE_YOU_TO_A_SMALL_GAIN_WITH_A_BIG_LOSS;

	/**
	 * ID: 1800629<br>
	 * Message: ���b�L�[�A�C�e���̓X�C�[�c�B
	 */
	public static final NpcStringId SWEET_FOOD_MAY_BRING_GOOD_LUCK;

	/**
	 * ID: 1800630<br>
	 * Message: �؋��͕Ԃ��Ă��炦��B�����͕�U���Ă��炦��B
	 */
	public static final NpcStringId YOU_MAY_BE_PAID_FOR_WHAT_YOURE_OWED_OR_FOR_YOUR_PAST_LOSS;

	/**
	 * ID: 1800631<br>
	 * Message: ��{�̖��Ŗ��C���N����B
	 */
	public static final NpcStringId THERE_MAY_BE_CONFLICT_IN_BASIC_MATTERS;

	/**
	 * ID: 1800632<br>
	 * Message: �߂����l�̏����ȍs���ɋC���g���B�������A�����������͋֕��B
	 */
	public static final NpcStringId BE_OBSERVANT_TO_CLOSE_FRIENDS_SMALL_BEHAVIORS_WHILE_REFRAINING_FROM_EXCESSIVE_KINDNESS;

	/**
	 * ID: 1800633<br>
	 * Message: �X�g���X�͕\�ɏo�����A�Ί��Y���ȁB
	 */
	public static final NpcStringId DO_NOT_SHOW_YOUR_DISTRESS_NOR_LOSE_YOUR_SMILE;

	/**
	 * ID: 1800634<br>
	 * Message: �ω���������Ώ����𓾂�B
	 */
	public static final NpcStringId SHOWING_CHANGE_MAY_BE_OF_HELP;

	/**
	 * ID: 1800635<br>
	 * Message: ���ԊǗ���O�ꂹ��B�]�݂̌��ʂ�������B
	 */
	public static final NpcStringId THE_INTENDED_RESULT_MAY_BE_ON_YOUR_WAY_IF_THE_TIME_IS_PERFECTLY_MANAGED;

	/**
	 * ID: 1800636<br>
	 * Message: �Z�ʂ������Ȃ��Ȃ�A�炢���Ƃ��N����B
	 */
	public static final NpcStringId HARDSHIPS_MAY_ARISE_IF_FLEXIBILITY_IS_NOT_WELL_PLAYED;

	/**
	 * ID: 1800637<br>
	 * Message: ���f��b����ꐶ�B������ÂɁB
	 */
	public static final NpcStringId KEEP_COOL_HEADED_BECAUSE_CARELESSNESS_OR_INATTENTIVENESS_MAY_CAUSE_MISFORTUNE;

	/**
	 * ID: 1800638<br>
	 * Message: �����͉���̒����B
	 */
	public static final NpcStringId BE_CAUTIOUS_AS_YOU_MAY_GET_HURT_AFTER_LAST_NIGHTS_SINISTER_DREAM;

	/**
	 * ID: 1800639<br>
	 * Message: ���^����B��ɗ������Α�������B
	 */
	public static final NpcStringId A_STRONG_WEALTH_LUCK_IS_AHEAD_BUT_BE_CAREFUL_WITH_EMOTIONS_THAT_MAY_BRING_LOSSES;

	/**
	 * ID: 1800640<br>
	 * Message: �D���Ȑl���֘A���邱�ƂȂ�]�ݓx�ʂ�i�߂Ă��悢�B
	 */
	public static final NpcStringId PROCEED_AS_YOU_WISH_WHEN_ITS_PERTINENT_TO_THE_PERSON_YOU_LIKE;

	/**
	 * ID: 1800641<br>
	 * Message: �ِ��Ƙb���ĐS���ʂ���B
	 */
	public static final NpcStringId YOU_MAY_DEEPEN_THE_RELATIONSHIP_WITH_THE_OPPOSITE_SEX_THROUGH_CONVERSATION;

	/**
	 * ID: 1800642<br>
	 * Message: ���Ɏc����Ƃ���ɓ�������̂��g�B
	 */
	public static final NpcStringId INVESTMENT_INTO_SOLID_MATERIAL_MAY_BRING_PROFIT;

	/**
	 * ID: 1800643<br>
	 * Message: �y�����Ƃ���ɓ�������Ώ����𓾂�B
	 */
	public static final NpcStringId INVESTMENT_INTO_WHAT_YOU_ENJOY_MAY_BE_OF_HELP;

	/**
	 * ID: 1800644<br>
	 * Message: �Z�����Ȃ�B���т��тƍs������B
	 */
	public static final NpcStringId BEING_BUSY_MAY_HELP_CATCHING_UP_WITH_MANY_CHANGES;

	/**
	 * ID: 1800645<br>
	 * Message: ��u�̖��f�ŉA�������������j�ڂɁB
	 */
	public static final NpcStringId CHOOSE_SUBSTANCE_OVER_HONOR;

	/**
	 * ID: 1800646<br>
	 * Message: ���K�̎���͋��B�������Ƃ����Ă�����������B
	 */
	public static final NpcStringId REMEMBER_TO_DECLINE_ANY_FINANCIAL_DEALINGS_BECAUSE_A_GOOD_DEED_MAY_RETURN_AS_RESENTMENT;

	/**
	 * ID: 1800647<br>
	 * Message: ���Ζʂł͎��s�̂Ȃ��悤�ɁB
	 */
	public static final NpcStringId BE_CAREFUL_NOT_TO_MAKE_A_MISTAKE_WITH_A_NEW_PERSON;

	/**
	 * ID: 1800648<br>
	 * Message: �Ȃ��Ȃ��������Ȃ����ƂɎ��Ԃ������Ă��������Ȃ��B��������ȁB
	 */
	public static final NpcStringId DO_NOT_BE_OBSESSIVE_OVER_A_DRAGGED_OUT_PROJECT_SINCE_IT_WONT_GET_ANY_BETTER_WITH_MORE_TIME;

	/**
	 * ID: 1800649<br>
	 * Message: ���������葹���邱�Ƃ͂���ȁB
	 */
	public static final NpcStringId DO_NOT_YIELD_WHATS_RIGHTFULLY_YOURS_OR_TOLERATE_LOSSES;

	/**
	 * ID: 1800650<br>
	 * Message: �ǉ��̒�������B�ِ��Ɋ֐S�����āB
	 */
	public static final NpcStringId THERES_LUCK_IN_RELATIONSHIPS_SO_BECOME_INTERESTED_IN_THE_OPPOSITE_SEX;

	/**
	 * ID: 1800651<br>
	 * Message: �����̂����������t�������͂���Έꋓ�������B
	 */
	public static final NpcStringId SEEKING_OTHERS_HELP_RATHER_THAN_TRYING_BY_YOURSELF_MAY_RESULT_IN_TWO_BIRDS_WITH_ONE_STONE;

	/**
	 * ID: 1800652<br>
	 * Message: �����������ė��v�𓾂�B
	 */
	public static final NpcStringId PERSUADING_THE_OTHER_MAY_RESULT_IN_YOUR_GAIN;

	/**
	 * ID: 1800653<br>
	 * Message: �҂ĂΊC�H�̓��a����B
	 */
	public static final NpcStringId A_GOOD_OPPORTUNITY_MAY_COME_WHEN_KEEPING_PATIENCE_WITHOUT_EXCESSIVENESS;

	/**
	 * ID: 1800654<br>
	 * Message: �ِ����K�^�������炷�B
	 */
	public static final NpcStringId THE_OPPOSITE_SEX_MAY_BRING_FORTUNE;

	/**
	 * ID: 1800655<br>
	 * Message: ���l�̗��݂𕷂������΍K�^������Ă���B
	 */
	public static final NpcStringId DOING_FAVOR_FOR_OTHER_PEOPLE_MAY_BRING_FORTUNE_IN_THE_FUTURE;

	/**
	 * ID: 1800656<br>
	 * Message: �ꂵ���Ă��Ί��Y���ȁB�K�^�͂��������ɂ���B
	 */
	public static final NpcStringId LUCK_MAY_STAY_NEAR_IF_A_SMILE_IS_KEPT_DURING_DIFFICULT_TIMES;

	/**
	 * ID: 1800657<br>
	 * Message: �܂��B���Ă�����B�܂��o���Ƃ��������B
	 */
	public static final NpcStringId YOU_MAY_REVEAL_YOUR_TRUE_SELF_LIKE_IRON_IS_MOLTEN_INTO_AN_STRONG_SWORD;

	/**
	 * ID: 1800658<br>
	 * Message: �B���Ă����܂��o���B�����̉��l�����܂�B
	 */
	public static final NpcStringId YOUR_VALUE_WILL_SHINE_AS_YOUR_POTENTIAL_IS_FINALLY_REALIZED;

	/**
	 * ID: 1800659<br>
	 * Message: ����ǂ��C�������C�悭�Ղ�ŉ����Ɏ������߁B�������ʂƋ��ɉB���ꂽ�\�͂������ł���B
	 */
	public static final NpcStringId TENACIOUS_EFFORTS_IN_SOLVING_A_DIFFICULT_MISSION_OR_HARDSHIP_MAY_BRING_GOOD_RESULTS_AS_WELL_AS_REALIZING_YOUR_HIDDEN_POTENTIAL;

	/**
	 * ID: 1800660<br>
	 * Message: �y�V�I�Ń��[���A��Y��Ȃ����Ȃ��̎���ɂ͏Ί炪�₦�Ȃ��B
	 */
	public static final NpcStringId PEOPLE_WILL_APPRECIATE_YOUR_POSITIVITY_AND_JOYFUL_ENTERTAINING;

	/**
	 * ID: 1800661<br>
	 * Message: �m�b�ƋZ�Ŏd���͏����B
	 */
	public static final NpcStringId THINGS_WILL_MOVE_SMOOTHLY_WITH_YOUR_FULL_WISDOM_AND_ABILITIES;

	/**
	 * ID: 1800662<br>
	 * Message: ���������ւƓ������҂�����B
	 */
	public static final NpcStringId YOU_MAY_MEET_A_SAGE_WHO_CAN_HELP_YOU_FIND_THE_RIGHT_PATH;

	/**
	 * ID: 1800663<br>
	 * Message: �������킹�̒��ϗ͂Ɠ��@�͂�������B
	 */
	public static final NpcStringId KEEN_INSTINCT_AND_FORESIGHT_WILL_SHINE_THEIR_VALUES;

	/**
	 * ID: 1800664<br>
	 * Message: ����ɍK�^�������炷�B
	 */
	public static final NpcStringId YOU_MAY_BRING_GOOD_LUCK_TO_THOSE_AROUND_YOU;

	/**
	 * ID: 1800665<br>
	 * Message: �����}���ĖڕW�B���B
	 */
	public static final NpcStringId YOUR_GOAL_MAY_BE_REALIZED_WHEN_EMOTIONAL_DETAILS_ARE_WELL_DEFINED;

	/**
	 * ID: 1800666<br>
	 * Message: �M�l�̓����ŕx��搉̂���B
	 */
	public static final NpcStringId YOU_MAY_ENJOY_AFFLUENCE_AFTER_MEETING_A_PRECIOUS_PERSON;

	/**
	 * ID: 1800667<br>
	 * Message: �����I�Ȗ��͂��������ِ��ɏo��B
	 */
	public static final NpcStringId YOU_MAY_MEET_THE_OPPOSITE_SEX_WHO_HAS_MATERIALISTIC_ATTRACTIONS;

	/**
	 * ID: 1800668<br>
	 * Message: �����͑S�͂Ő킦�B
	 */
	public static final NpcStringId A_BIG_SUCCESS_WILL_FOLLOW_ALL_POSSIBLE_EFFORTS_IN_COMPETITION;

	/**
	 * ID: 1800669<br>
	 * Message: �ߋ��̍s���̌��ʂ��o��B
	 */
	public static final NpcStringId A_CONSEQUENCE_FROM_PAST_ACTIONS_WILL_BE_ON_DISPLAY;

	/**
	 * ID: 1800670<br>
	 * Message: ����Ɨ��ꂪ�����΂ɁB
	 */
	public static final NpcStringId WHATEVER_HAPPENED_TO_YOU_AND_THE_OTHER_PERSON_WILL_REPLAY_BUT_THIS_TIME_THE_OPPOSITE_WILL_BE_THE_RESULT;

	/**
	 * ID: 1800671<br>
	 * Message: ���̒����E���đ�̒���������B
	 */
	public static final NpcStringId YOU_MAY_NEED_TO_SACRIFICE_FOR_A_HIGHER_CAUSE;

	/**
	 * ID: 1800672<br>
	 * Message: ���������A���_�𓾂�B
	 */
	public static final NpcStringId YOU_MAY_LOSE_AN_ITEM_BUT_WILL_GAIN_HONOR;

	/**
	 * ID: 1800673<br>
	 * Message: �ω��ւ̗~�]���ӂ��B�V���Ȃ��ƂɃ`�������W����B
	 */
	public static final NpcStringId A_NEW_TRIAL_OR_START_MAY_BE_SUCCESSFUL_AS_LUCK_SHADOWS_CHANGES;

	/**
	 * ID: 1800674<br>
	 * Message: ���\�ƗU�f�������߂��B�{���͉B���B
	 */
	public static final NpcStringId BE_SOPHISTICATED_WITHOUT_SHOWING_YOUR_TRUE_EMOTIONS_AS_TRICKS_AND_MATERIALISTIC_TEMPTATIONS_LIE_AHEAD;

	/**
	 * ID: 1800675<br>
	 * Message: �N�q�낤���ɋߊ�炸�B
	 */
	public static final NpcStringId DO_NOT_ATTEMPT_A_DANGEROUS_ADVENTURE;

	/**
	 * ID: 1800676<br>
	 * Message: �ω��������ȁB��@�̓`�����X�B
	 */
	public static final NpcStringId DO_NOT_BE_AFRAID_OF_CHANGE_A_RISK_WILL_BE_ANOTHER_OPPORTUNITY;

	/**
	 * ID: 1800677<br>
	 * Message: ���M�ƍ��C�ōs������B�s����ł����܂�����B
	 */
	public static final NpcStringId BE_CONFIDENT_AND_ACT_TENACIOUSLY_AT_ALL_TIMES_YOU_MAY_BE_ABLE_TO_ACCOMPLISH_TO_PERFECTION_DURING_SOMEWHAT_UNSTABLE_SITUATIONS;

	/**
	 * ID: 1800678<br>
	 * Message: ���邢�������҂��Ă���B
	 */
	public static final NpcStringId YOU_MAY_EXPECT_A_BRIGHT_AND_HOPEFUL_FUTURE;

	/**
	 * ID: 1800679<br>
	 * Message: �x���͂���Ȃ鐬���ւ̋ߓ��B
	 */
	public static final NpcStringId A_REST_WILL_PROMISE_A_BIGGER_DEVELOPMENT;

	/**
	 * ID: 1800680<br>
	 * Message: �|�W�e�B�u�ɍl���čs������B
	 */
	public static final NpcStringId FULLY_UTILIZE_POSITIVE_VIEWS;

	/**
	 * ID: 1800681<br>
	 * Message: �|�W�e�B�u�Ŋ��͂��ӂ�邠�Ȃ��͕���̒��S�ɗ��B
	 */
	public static final NpcStringId POSITIVE_THINKING_AND_ENERGETIC_ACTIONS_WILL_TAKE_YOU_TO_THE_CENTER_OF_THE_GLORIOUS_STAGE;

	/**
	 * ID: 1800682<br>
	 * Message: ��������Ă������̒�����M���čs������B
	 */
	public static final NpcStringId YOUR_SELF_CONFIDENCE_AND_INTUITION_MAY_SOLVE_THE_DIFFICULTIES;

	/**
	 * ID: 1800683<br>
	 * Message: �����������y�����B�Ί�Ŏ���𖾂邭����B
	 */
	public static final NpcStringId EVERYTHING_IS_BRILLIANT_AND_JOYFUL_SHARE_IT_WITH_OTHERS_A_BIGGER_FORTUNE_WILL_FOLLOW;

	/**
	 * ID: 1800684<br>
	 * Message: �ߋ��̍s���ɑ΂��鐳���ȕ]���ƕ�܂��҂��Ă���B
	 */
	public static final NpcStringId A_FAIR_ASSESSMENT_AND_REWARD_FOR_PAST_ACTIONS_LIE_AHEAD;

	/**
	 * ID: 1800685<br>
	 * Message: �ق����炩���̎d����؋���Еt����B�V���Ȋ�т̒�������B
	 */
	public static final NpcStringId PAY_ACCURATELY_THE_OLD_LIABILITY_OR_DEBT_IF_APPLICABLE_A_NEW_JOY_LIES_AHEAD;

	/**
	 * ID: 1800686<br>
	 * Message: �����������đ�������B
	 */
	public static final NpcStringId AN_EXCESSIVE_HUMILITY_CAN_HARM_YOU_BACK;

	/**
	 * ID: 1800687<br>
	 * Message: ���܂ł̎d���̕�܂𓾂�B
	 */
	public static final NpcStringId A_REWARD_FOR_THE_PAST_WORK_WILL_COME_THROUGH;

	/**
	 * ID: 1800688<br>
	 * Message: �����т�ׂ����{���ׂ̖��ɂȂ�B
	 */
	public static final NpcStringId YOUR_PAST_FRUITLESS_EFFORT_WILL_FINALLY_BE_REWARDED_WITH_SOMETHING_UNEXPECTED;

	/**
	 * ID: 1800689<br>
	 * Message: �Đ��̗͋����B�Â��̂ĂĐV�����𓾂�B
	 */
	public static final NpcStringId THERES_STRONG_LUCK_IN_A_REVIVAL_ABANDON_THE_OLD_AND_CREATE_THE_NEW;

	/**
	 * ID: 1800690<br>
	 * Message: ���肩�畨�S���ʂŏ�������B
	 */
	public static final NpcStringId YOU_MAY_GAIN_MATERIALISTIC_OR_MENTAL_AID_FROM_CLOSE_FRIENDS;

	/**
	 * ID: 1800691<br>
	 * Message: �����ւ肪�҂��Ă���B
	 */
	public static final NpcStringId A_GOOD_BEGINNING_IS_AWAITING_YOU;

	/**
	 * ID: 1800692<br>
	 * Message: ����������l�ɉ��B
	 */
	public static final NpcStringId YOU_MAY_MEET_THE_PERSON_YOUVE_LONGED_TO_SEE;

	/**
	 * ID: 1800693<br>
	 * Message: �����҂͑�������B
	 */
	public static final NpcStringId YOU_MAY_SUSTAIN_A_LOSS_DUE_TO_YOUR_KINDNESS;

	/**
	 * ID: 1800694<br>
	 * Message: �M�l����������L�ׂ�B�ʂ肩����̐l���悭����B
	 */
	public static final NpcStringId CLOSELY_OBSERVE_PEOPLE_WHO_PASS_BY_SINCE_YOU_MAY_MEET_A_PRECIOUS_PERSON_WHO_CAN_HELP_YOU;

	/**
	 * ID: 1800695<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�j�ł̎�ɂ���e�B�A�g�̃h���R�j�A���Ɛ키�E�C����`���Ƃ��W�܂����B
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_WERE_GATHERING_BRAVE_ADVENTURERS_TO_ATTACK_TIATS_MOUNTED_TROOP_THATS_ROOTED_IN_THE_SEED_OF_DESTRUCTION;

	/**
	 * ID: 1800696<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�j�ł̎�͓����A���̊��̉��Ɉ��S�Ɋm�ۂ���Ă���B
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_DESTRUCTION_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE;

	/**
	 * ID: 1800697<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�e�B�A�g�̃h���R�j�A������̒D�҂����ł���B����Ƃ����郊�\�[�X��j�ł̎�ɓ�������B
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_TIATS_MOUNTED_TROOP_IS_CURRENTLY_TRYING_TO_RETAKE_SEED_OF_DESTRUCTION_COMMIT_ALL_THE_AVAILABLE_REINFORCEMENTS_INTO_SEED_OF_DESTRUCTION;

	/**
	 * ID: 1800698<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�s�ł̎�ɒ��񂾗E�C����`���Ƃ́A���̎ア��ɂ̊�����ʂ��ĐN�H�̊����ɐN�����Ă���I
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_BRAVE_ADVENTURERS_WHO_HAVE_CHALLENGED_THE_SEED_OF_INFINITY_ARE_CURRENTLY_INFILTRATING_THE_HALL_OF_EROSION_THROUGH_THE_DEFENSIVELY_WEAK_HALL_OF_SUFFERING;

	/**
	 * ID: 1800699<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�s�ł̎�͐S�����܂ő|�����������Ă���B�G�L���X�̒��ڍU�����铯���ɁA��ɂ̊����ɂ���A���f�b�h�̎c�}�̑|�����s���Ă���I
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_SWEEPING_THE_SEED_OF_INFINITY_IS_CURRENTLY_COMPLETE_TO_THE_HEART_OF_THE_SEED_EKIMUS_IS_BEING_DIRECTLY_ATTACKED_AND_THE_UNDEAD_REMAINING_IN_THE_HALL_OF_SUFFERING_ARE_BEING_ERADICATED;
	
	/**
	 * ID: 1800700<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�s�ł̎�͓����A���̊��̉��Ɉ��S�Ɋm�ۂ���Ă���B
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_INFINITY_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE;

	/**
	 * ID: 1800701<br>
	 * Message: 
	 */
	public static final NpcStringId _;
	
	/**
	 * ID: 1800702<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�����Ԃ����s�ł̎�̃A���f�b�h����ɂ̊����ƐN�H�̊����ɂ��ӂ�o�Ă���B
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_RESURRECTED_UNDEAD_IN_THE_SEED_OF_INFINITY_ARE_POURING_INTO_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;

	/**
	 * ID: 1800703<br>
	 * Message: �`�߁A�N�Z���X�����A���̓��u�ɍ�����I���݁A�����Ԃ����s�ł̎�̃A���f�b�h�ǂ��̎�ɂ���ăG�L���X�������Ԃ낤�Ƃ��Ă���B�S���͂�S�����Ƌ�ɂ̊����ɔh������I
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_EKIMUS_IS_ABOUT_TO_BE_REVIVED_BY_THE_RESURRECTED_UNDEAD_IN_SEED_OF_INFINITY_SEND_ALL_REINFORCEMENTS_TO_THE_HEART_AND_THE_HALL_OF_SUFFERING;

	/**
	 * ID: 1800704<br>
	 * Message: 3��h���I
	 */
	public static final NpcStringId STABBING_THREE_TIMES;

	/**
	 * ID: 1800705<br>
	 * Message: ���������푢���ǂ���B�ł̗͂������������Ă��B
	 */
	public static final NpcStringId POOR_CREATURES_FEEL_THE_POWER_OF_DARKNESS;

	/**
	 * ID: 1800706<br>
	 * Message: �������������I
	 */
	public static final NpcStringId WHOAAAAAA;

	/**
	 * ID: 1800707<br>
	 * Message: ���ɒ��񂾂��Ƃ���������Ă��I
	 */
	public static final NpcStringId YOULL_REGRET_CHALLENGING_ME;

	/**
	 * ID: 1800708<br>
	 * Message: ���݁A�G�ɐ�̂���Ă���B�䂪�R�͍U����i�߂Ă���B
	 */
	public static final NpcStringId ITS_CURRENTLY_OCCUPIED_BY_THE_ENEMY_AND_OUR_TROOPS_ARE_ATTACKING;

	/**
	 * ID: 1800709<br>
	 * Message: ���݁A��X����̂��Ă���B�N�Z���X�����A�����s�c���̑|���ɓ������Ă���B
	 */
	public static final NpcStringId ITS_UNDER_OCCUPATION_BY_OUR_FORCES_AND_I_HEARD_THAT_KUCEREUS_CLAN_IS_ORGANIZING_THE_REMNANTS;

	/**
	 * ID: 1800710<br>
	 * Message: ���݁A��X����̂��Ă��邪�A�G�̋��͂ȍU���ɉ�����C�����B
	 */
	public static final NpcStringId ALTHOUGH_WE_CURRENTLY_HAVE_CONTROL_OF_IT_THE_ENEMY_IS_PUSHING_BACK_WITH_A_POWERFUL_ATTACK;

	/**
	 * ID: 1800711<br>
	 * Message: ���݁A�G�ɐ�̂���Ă���B�`���ƂƘA�������G�̋�ɂ̊����ƐN�H�̊������������U�����Ă���B
	 */
	public static final NpcStringId ITS_UNDER_THE_ENEMYS_OCCUPATION_AND_THE_MILITARY_FORCES_OF_ADVENTURERS_AND_CLAN_MEMBERS_ARE_UNLEASHING_AN_ONSLAUGHT_UPON_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;

	/**
	 * ID: 1800713<br>
	 * Message: ���݁A��X����̂��Ă���B�����̒������s���Ă���B
	 */
	public static final NpcStringId OUR_FORCES_HAVE_OCCUPIED_IT_AND_ARE_CURRENTLY_INVESTIGATING_THE_DEPTHS;

	/**
	 * ID: 1800714<br>
	 * Message: ���݁A��X����̂��Ă��邪�A�G�������Ԃ��ċ�ɂ̊����ƐN�H�̊�����ʂ��ĐN�U�����������B
	 */
	public static final NpcStringId ITS_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_RESURRECTED_AND_IS_ATTACKING_TOWARD_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;

	/**
	 * ID: 1800715<br>
	 * Message: ���݁A��X����̂��Ă��邪�A�N�H�̊����͂��łɓG�̎�ɗ������B����ɐS�����ɉ����񂹂Ă킪�R�̑ޘH���Ƃ��Ƃ��Ă���B�G�L���X�̕��������Ԃ̖�肾�B
	 */
	public static final NpcStringId ITS_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_ALREADY_OVERTAKEN_THE_HALL_OF_EROSION_AND_IS_DRIVING_OUT_OUR_FORCES_FROM_THE_HALL_OF_SUFFERING_TOWARD_THE_HEART_IT_SEEMS_THAT_EKIMUS_WILL_REVIVE_SHORTLY;

	/**
	 * ID: 1800717<br>
	 * Message: �e�B�A�g�̎艺���j�ł̎�����Ԃ����Ƃ��Ă���B���ނ̏���������I
	 */
	public static final NpcStringId TIATS_FOLLOWERS_ARE_COMING_TO_RETAKE_THE_SEED_OF_DESTRUCTION_GET_READY_TO_STOP_THE_ENEMIES;

	/**
	 * ID: 1800718<br>
	 * Message: �����A�ɂ��D�D�D���̒ɂ݁A�ǂ��������~�ނ��낤���B
	 */
	public static final NpcStringId ITS_HURTING_IM_IN_PAIN_WHAT_CAN_I_DO_FOR_THE_PAIN;

	/**
	 * ID: 1800719<br>
	 * Message: ��߂Ă���D�D�D���ꂷ��Ȃ��Ȃ�����A�ǂ�����ċ�ɂƐ킦���Ă񂾁I
	 */
	public static final NpcStringId NO_WHEN_I_LOSE_THAT_ONE_ILL_BE_IN_MORE_PAIN;

	/**
	 * ID: 1800720<br>
	 * Message: ���͂͂́I�N���X�}�X �T���^�͉����߂܂����I���N�̃v���[���g�͂��a�����I
	 */
	public static final NpcStringId HAHAHAH_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR;

	/**
	 * ID: 1800721<br>
	 * Message: �����A�������Ă����I
	 */
	public static final NpcStringId NOW_WHY_DONT_YOU_TAKE_UP_THE_CHALLENGE;

	/**
	 * ID: 1800722<br>
	 * Message: ������Ă��I
	 */
	public static final NpcStringId COME_ON_ILL_TAKE_ALL_OF_YOU_ON;

	/**
	 * ID: 1800723<br>
	 * Message: �ǂ����I���̏������ȁB
	 */
	public static final NpcStringId HOW_ABOUT_IT_I_THINK_I_WON;

	/**
	 * ID: 1800724<br>
	 * Message: �������͏��������₪��I
	 */
	public static final NpcStringId NOW_THOSE_OF_YOU_WHO_LOST_GO_AWAY;

	/**
	 * ID: 1800725<br>
	 * Message: �����Ƙr�̗���͂��Ȃ��̂��I
	 */
	public static final NpcStringId WHAT_A_BUNCH_OF_LOSERS;

	/**
	 * ID: 1800726<br>
	 * Message: �N���X�}�X �T���^���~���ɗ����悤���ȁB�����A���肪���������ȁB
	 */
	public static final NpcStringId I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_PERSON;

	/**
	 * ID: 1800727<br>
	 * Message: �����A��邶��˂����B
	 */
	public static final NpcStringId AH_OKAY;

	/**
	 * ID: 1800728<br>
	 * Message: ������o���񂶂�Ȃ������I
	 */
	public static final NpcStringId UAGH_I_WASNT_GOING_TO_DO_THAT;

	/**
	 * ID: 1800729<br>
	 * Message: ���̎􂢂��󂯂�I���A������H
	 */
	public static final NpcStringId YOURE_CURSED_OH_WHAT;

	/**
	 * ID: 1800730<br>
	 * Message: ����񂯂�΂�������Ă񂶂�˂���I
	 */
	public static final NpcStringId HAVE_YOU_DONE_NOTHING_BUT_ROCK_PAPER_SCISSORS;

	/**
	 * ID: 1800731<br>
	 * Message: ������߂�D�D�D�{���͎₵�������񂾁D�D�D
	 */
	public static final NpcStringId STOP_IT_NO_MORE_I_DID_IT_BECAUSE_I_WAS_TOO_LONELY;

	/**
	 * ID: 1800732<br>
	 * Message: �������I�N���X�}�X �T���^������Ă��Ȃ���Ȃ�˂��Ƃ͂ȁI
	 */
	public static final NpcStringId I_HAVE_TO_RELEASE_SANTA_HOW_INFURIATING;

	/**
	 * ID: 1800733<br>
	 * Message: �N���X�}�X�Ȃ񂩑�����������[�I
	 */
	public static final NpcStringId I_HATE_HAPPY_MERRY_CHRISTMAS;

	/**
	 * ID: 1800734<br>
	 * Message: ���`�������肢�`
	 */
	public static final NpcStringId OH_IM_BORED;

	/**
	 * ID: 1800735<br>
	 * Message: �N���X�}�X �T���^����l�����߂܂��Ă邩���ɍs�����B�ЂЂ��I
	 */
	public static final NpcStringId SHALL_I_GO_TO_TAKE_A_LOOK_IF_SANTA_IS_STILL_THERE_HEHE;

	/**
	 * ID: 1800736<br>
	 * Message: �I�b�z�b�z�D�D�D�����[�N���X�}�X�I
	 */
	public static final NpcStringId OH_HO_HO_MERRY_CHRISTMAS;

	/**
	 * ID: 1800737<br>
	 * Message: �N���X�}�X �T���^�������ė��Ă���Ȃ���v���[���g�͂������Ȃ���B
	 */
	public static final NpcStringId SANTA_COULD_GIVE_NICE_PRESENTS_ONLY_IF_HES_RELEASED_FROM_THE_TURKEY;

	/**
	 * ID: 1800738<br>
	 * Message: �I�b�z�b�z�D�D�D�D�݂Ȃ���A����J�l�B����͕K���������܂��B
	 */
	public static final NpcStringId OH_HO_HO_OH_HO_HO_THANK_YOU_LADIES_AND_GENTLEMEN_I_WILL_REPAY_YOU_FOR_SURE;

	/**
	 * ID: 1800739<br>
	 * Message: �����[�N���X�}�X�I����J�l�B
	 */
	public static final NpcStringId UMERRY_CHRISTMAS_YOURE_DOING_A_GOOD_JOB;

	/**
	 * ID: 1800740<br>
	 * Message: �����[�N���X�}�X�I�L���v�e�� �^�[�L�[����~���Ă���Ă��肪�Ƃ��B
	 */
	public static final NpcStringId MERRY_CHRISTMAS_THANK_YOU_FOR_RESCUING_ME_FROM_THAT_WRETCHED_TURKEY;

	/**
	 * ID: 1800741<br>
	 * Message: $s1�A�N�̂��߂Ƀv���[���g��p�ӂ����B
	 */
	public static final NpcStringId S1_I_HAVE_PREPARED_A_GIFT_FOR_YOU;

	/**
	 * ID: 1800742<br>
	 * Message: $s1�Ƀv���[���g������B
	 */
	public static final NpcStringId I_HAVE_A_GIFT_FOR_S1;

	/**
	 * ID: 1800743<br>
	 * Message: �C���x���g�������Ă����B���̃v���[���g�A�C�ɓ����Ă��ꂽ�炠�肪�����񂾂��B
	 */
	public static final NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_I_HOPE_YOU_LIKE_THE_GIFT_I_GAVE_YOU;

	/**
	 * ID: 1800744<br>
	 * Message: �C���x���g�������Ă����B�r�b�O�ȃv���[���g�������Ă邩������Ȃ����B
	 */
	public static final NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_MIGHT_BE_A_BIG_PRESENT;

	/**
	 * ID: 1800745<br>
	 * Message: �N�̑��������̂͂������񂴂肾�B�����s���I
	 */
	public static final NpcStringId IM_TIRED_OF_DEALING_WITH_YOU_IM_LEAVING;

	/**
	 * ID: 1800746<br>
	 * Message: ���܂ł����肾�I���낻��O���Ă������B
	 */
	public static final NpcStringId WHEN_ARE_YOU_GOING_TO_STOP_I_SLOWLY_STARTED_TO_BE_TIRED_OF_IT;

	/**
	 * ID: 1800747<br>
	 * Message: �N���X�}�X �T���^�̓`���F�킵���~���o���Ă��ꂽ$s1�ɏj��������܂��悤�ɁD�D�D
	 */
	public static final NpcStringId MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S1_WHO_SAVED_ME;

	/**
	 * ID: 1800748<br>
	 * Message: ��x����ł��鎄���B��x���Ȃ��邱�Ƃ͂ł��܂��B
	 */
	public static final NpcStringId I_AM_ALREADY_DEAD_YOU_CANNOT_KILL_ME_AGAIN;

	/**
	 * ID: 1800749<br>
	 * Message: ���ׂĂ̈Í������ő���I�����퓬�̐��ɂ��I
	 */
	public static final NpcStringId OH_FOLLOWERS_OF_THE_DRAGON_OF_DARKNESS_FIGHT_BY_MY_SIDE;

	/**
	 * ID: 1800750<br>
	 * Message: �h���R�j�A�����P�I�퓬�Ԑ��ɂ��Ă��������B
	 */
	public static final NpcStringId THE_DRAGON_RACE_ARE_INVADING_PREPARE_FOR_BATTLE;

	/**
	 * ID: 1800751<br>
	 * Message: $s1�l��$s2�̒n�̃T���^�N���[�X���L���v�e���^�[�L�[����~���o���܂����B
	 */
	public static final NpcStringId S1_RESCUED_SANTA_CLAUS_OF_S2_TERRITORY_FROM_THE_TURKEY;

	/**
	 * ID: 1800752<br>
	 * Message: �T���^�~�o�����I
	 */
	public static final NpcStringId SANTA_RESCUE_SUCCESS;

	/**
	 * ID: 1800753<br>
	 * Message: $s1�l�͕����������+$s2 $s3�����󂯎��ɂȂ�܂����B
	 */
	public static final NpcStringId S1_RECEIVED_S2_S3_THROUGH_THE_WEAPON_EXCHANGE_COUPON;

	/**
	 * ID: 1800754<br>
	 * Message: ������ł܂����������ȁI
	 */
	public static final NpcStringId DONT_GO_PRATTLING_ON;

	/**
	 * ID: 1800755<br>
	 * Message: �v���C�h���Ȃ���߁I���肷�鉿�l���Ȃ��I
	 */
	public static final NpcStringId YOU_LOWLIFES_WITH_NOT_EVEN_AN_OUNCE_OF_PRIDE_YOURE_NOT_WORTHY_OF_OPPOSING_ME;

	/**
	 * ID: 1800756<br>
	 * Message: �����[���I���A�ԈႦ���B�u�q�u�q�I�����I���̓u�^�����I�u�q�u�q�I
	 */
	public static final NpcStringId ROAR_NO_OINK_OINK_SEE_IM_A_PIG_OINK_OINK;

	/**
	 * ID: 1800757<br>
	 * Message: �����͂ǂ��H���͒N�H�u�q�u�q�I
	 */
	public static final NpcStringId WHO_AM_I_WHERE_AM_I_OINK_OINK;

	/**
	 * ID: 1800758<br>
	 * Message: �����ƈꏏ�ɗV�тɗ�����������B�u�q�u�q�I
	 */
	public static final NpcStringId I_JUST_FOLLOWED_MY_FRIEND_HERE_FOR_FUN_OINK_OINK;

	/**
	 * ID: 1800759<br>
	 * Message: ������`���I�ܑ��Z�D�ɐ��ݓn��ȁI
	 */
	public static final NpcStringId WOW_THATS_WHAT_I_CALL_A_CURE_ALL;

	/**
	 * ID: 1800760<br>
	 * Message: ������`���I�H�~���ǂ�ǂ�łĂ������B���ł��H���ɍs���ׁB
	 */
	public static final NpcStringId IM_STARVING_SHOULD_I_GO_CHEW_SOME_GRASS;

	/**
	 * ID: 1800761<br>
	 * Message: ���肪�Ƃ�B���x�A��t�������B�������ȁB
	 */
	public static final NpcStringId THANK_YOU_THANK_YOU;

	/**
	 * ID: 1800762<br>
	 * Message: ������`���I���`���I���C���o�Ă������I
	 */
	public static final NpcStringId WHATS_THIS_FEELING_OH_OH_FEELS_LIKE_MY_ENERGY_IS_BACK;

	/**
	 * ID: 1800763<br>
	 * Message: ������`���I�����A���������I
	 */
	public static final NpcStringId MY_BODYS_GETTING_LIGHTER_THIS_FEELING_FEELS_FAMILIAR_SOMEHOW;

	/**
	 * ID: 1800764<br>
	 * Message: ��`��ꂪ�n���Ă����悤���B
	 */
	public static final NpcStringId WOW_MY_FATIGUE_IS_COMPLETELY_GONE;

	/**
	 * ID: 1800765<br>
	 * Message: �ւ��`��J�Q�[�W���ǂ�ǂ񉺂����Ă����I��ꂪ�ǂ�ǂ����B
	 */
	public static final NpcStringId HEY_THE_OMINOUS_ENERGY_IS_DISAPPEARED;

	/**
	 * ID: 1800766<br>
	 * Message: ������`���I�J���_���y���Ȃ������B
	 */
	public static final NpcStringId MY_BODY_FEELS_AS_LIGHT_AS_A_FEATHER;

	/**
	 * ID: 1800767<br>
	 * Message: ���A����H�H�������B�u�q�u�q�I
	 */
	public static final NpcStringId WHATS_THIS_FOOD;

	/**
	 * ID: 1800768<br>
	 * Message: �p���[�}���^���I������J�񕜍܂Ȃ�ėv��˂����I�u�q�u�q�I
	 */
	public static final NpcStringId MY_ENERGY_IS_OVERFLOWING_I_DONT_NEED_ANY_FATIGUE_RECOVERY_POTION;

	/**
	 * ID: 1800769<br>
	 * Message: ������Ă񂾁I�H�f�l������I��J�񕜍܂͋��ɂł�����Ă��ȁB�u�q�u�q�I
	 */
	public static final NpcStringId WHATS_THE_MATTER_THATS_AN_AMATEUR_MOVE;

	/**
	 * ID: 1800770<br>
	 * Message: �K�^�̃^�C�}�[�F10�b�ȓ��ɏI���Ε�܂�2�{�I
	 */
	public static final NpcStringId FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_10_SECONDS;

	/**
	 * ID: 1800771<br>
	 * Message: �K�^�̃^�C�}�[�F40�b�ȓ��ɏI���Ε�܂�2�{�I
	 */
	public static final NpcStringId FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_40_SECONDS;

	/**
	 * ID: 1800772<br>
	 * Message: �c��40�b�ł��B
	 */
	public static final NpcStringId N40_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800773<br>
	 * Message: �c��39�b�ł��B
	 */
	public static final NpcStringId N39_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800774<br>
	 * Message: �c��38�b�ł��B
	 */
	public static final NpcStringId N38_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800775<br>
	 * Message: �c��37�b�ł��B
	 */
	public static final NpcStringId N37_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800776<br>
	 * Message: �c��36�b�ł��B
	 */
	public static final NpcStringId N36_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800777<br>
	 * Message: �c��35�b�ł��B
	 */
	public static final NpcStringId N35_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800778<br>
	 * Message: �c��34�b�ł��B
	 */
	public static final NpcStringId N34_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800779<br>
	 * Message: �c��33�b�ł��B
	 */
	public static final NpcStringId N33_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800780<br>
	 * Message: �c��32�b�ł��B
	 */
	public static final NpcStringId N32_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800781<br>
	 * Message: �c��31�b�ł��B
	 */
	public static final NpcStringId N31_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800782<br>
	 * Message: �c��30�b�ł��B
	 */
	public static final NpcStringId N30_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800783<br>
	 * Message: �c��29�b�ł��B
	 */
	public static final NpcStringId N29_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800784<br>
	 * Message: �c��28�b�ł��B
	 */
	public static final NpcStringId N28_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800785<br>
	 * Message: �c��27�b�ł��B
	 */
	public static final NpcStringId N27_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800786<br>
	 * Message: �c��26�b�ł��B
	 */
	public static final NpcStringId N26_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800787<br>
	 * Message: �c��25�b�ł��B
	 */
	public static final NpcStringId N25_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800788<br>
	 * Message: �c��24�b�ł��B
	 */
	public static final NpcStringId N24_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800789<br>
	 * Message: �c��23�b�ł��B
	 */
	public static final NpcStringId N23_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800790<br>
	 * Message: �c��22�b�ł��B
	 */
	public static final NpcStringId N22_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800791<br>
	 * Message: �c��21�b�ł��B
	 */
	public static final NpcStringId N21_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800792<br>
	 * Message: �c��20�b�ł��B
	 */
	public static final NpcStringId N20_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800793<br>
	 * Message: �c��19�b�ł��B
	 */
	public static final NpcStringId N19_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800794<br>
	 * Message: �c��18�b�ł��B
	 */
	public static final NpcStringId N18_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800795<br>
	 * Message: �c��17�b�ł��B
	 */
	public static final NpcStringId N17_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800796<br>
	 * Message: �c��16�b�ł��B
	 */
	public static final NpcStringId N16_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800797<br>
	 * Message: �c��15�b�ł��B
	 */
	public static final NpcStringId N15_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800798<br>
	 * Message: �c��14�b�ł��B
	 */
	public static final NpcStringId N14_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800799<br>
	 * Message: �c��13�b�ł��B
	 */
	public static final NpcStringId N13_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800800<br>
	 * Message: �c��12�b�ł��B
	 */
	public static final NpcStringId N12_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800801<br>
	 * Message: �c��11�b�ł��B
	 */
	public static final NpcStringId N11_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800802<br>
	 * Message: �c��10�b�ł��B
	 */
	public static final NpcStringId N10_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800803<br>
	 * Message: �c��9�b�ł��B
	 */
	public static final NpcStringId N9_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800804<br>
	 * Message: �c��8�b�ł��B
	 */
	public static final NpcStringId N8_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800805<br>
	 * Message: �c��7�b�ł��B
	 */
	public static final NpcStringId N7_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800806<br>
	 * Message: �c��6�b�ł��B
	 */
	public static final NpcStringId N6_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800807<br>
	 * Message: �c��5�b�ł��B
	 */
	public static final NpcStringId N5_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800808<br>
	 * Message: �c��4�b�ł��B
	 */
	public static final NpcStringId N4_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800809<br>
	 * Message: �c��3�b�ł��B
	 */
	public static final NpcStringId N3_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800810<br>
	 * Message: �c��2�b�ł��B
	 */
	public static final NpcStringId N2_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800811<br>
	 * Message: �c��1�b�ł��B
	 */
	public static final NpcStringId N1_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800812<br>
	 * Message: �I���I
	 */
	public static final NpcStringId TIME_UP;

	/**
	 * ID: 1800813<br>
	 * Message: Mission Failed!
	 */
	public static final NpcStringId MISSION_FAILED;

	/**
	 * ID: 1800814<br>
	 * Message: Mission Success!
	 */
	public static final NpcStringId MISSION_SUCCESS;

	/**
	 * ID: 1800815<br>
	 * Message: �Ȃ��A���ɂ͉ƒ낪����񂾁B
	 */
	public static final NpcStringId HEY_I_ALREADY_HAVE_AN_OWNER;

	/**
	 * ID: 1800816<br>
	 * Message: �����I�����Ă��ĐH�������Ă̂��H�I�L���[�s�b�h�̔�J�񕜍܂��g���Ă����B
	 */
	public static final NpcStringId HEY_ARE_YOU_PLANNING_ON_EATING_ME_USE_A_CUPIDS_FATIGUE_RECOVERY_POTION_ALREADY;

	/**
	 * ID: 1800817<br>
	 * Message: �����@�͉���̌������B�f�l�̃}�b�T�[�W�͉������āA�L���[�s�b�h�̔�J�񕜍܂ɂ��Ƃ���B
	 */
	public static final NpcStringId ILL_PASS_ON_AN_AMATEURS_MERIDIAN_MASSAGE_USE_A_CUPIDS_FATIGUE_RECOVERY_POTION_ALREADY;

	/**
	 * ID: 1800818<br>
	 * Message: ����`���I����Ȃɑ�����ꂪ����Ȃ�ĂȁB$s1�A�����������B
	 */
	public static final NpcStringId I_ALREADY_FEEL_MORE_ENERGETIC_THANKS_S1;

	/**
	 * ID: 1800819<br>
	 * Message: �������ȁI���񂽂̓}�b�T�[�W�̒B�l�����A$s1�搶�B
	 */
	public static final NpcStringId HOW_REFRESHING_YOU_WOULDNT_HAPPEN_TO_BE_A_MASTER_MASSEUSE_S1_WOULD_YOU;

	/**
	 * ID: 1800820<br>
	 * Message: �������ȁI$s1�A���񂽂̓}�b�T�[�W�̗��j��h��ւ����I
	 */
	public static final NpcStringId INCREDIBLE_FROM_NOW_ON_ILL_COMPARE_ALL_MASSAGES_TO_THIS_ONE_WITH_S1;

	/**
	 * ID: 1800821<br>
	 * Message: �ЂƂ�ł��̂͂���ǂ�����H������͒��Ԃƃp�[�e�B��g��ł�����ȁB
	 */
	public static final NpcStringId ISNT_IT_TOUGH_DOING_IT_ALL_ON_YOUR_OWN_NEXT_TIME_TRY_MAKING_A_PARTY_WITH_SOME_COMRADES;

	/**
	 * ID: 1800822<br>
	 * Message: ��股���ǂ��A���̗F�B�ɂ����Ă���Ă���˂����ȁH
	 */
	public static final NpcStringId SORRY_BUT_ILL_LEAVE_MY_FRIEND_IN_YOUR_CARE_AS_WELL_THANKS;

	/**
	 * ID: 1800823<br>
	 * Message: �N���N���B���΂����o�Q�b�g�̍���I
	 */
	public static final NpcStringId SNIFF_SNIFF_DO_YOU_SMELL_THE_SCENT_OF_A_FRESH_BAKED_BAGUETTE;

	/**
	 * ID: 1800824<br>
	 * Message: �킵�͒N�����āH�p���E�l�ɂȂ肽��������A�����ȁB
	 */
	public static final NpcStringId WHO_AM_I_LET_ME_KNOW_IF_YOU_WANNA_BUY_MY_BREAD;

	/**
	 * ID: 1800825<br>
	 * Message: ���݂͂�Ȃ̕�������͂ɂ����������Ȃ񂾁B�A�u���J�_�u���`�₟���I
	 */
	public static final NpcStringId I_JUST_WANT_TO_MAKE_YOUR_WEAPONS_STRONGER_ABRA_KADABRA;

	/**
	 * ID: 1800826<br>
	 * Message: ���H�C�ɓ���Ȃ����āH�f�l�ł�����܂����B
	 */
	public static final NpcStringId WHAT_YOU_DONT_LIKE_IT_WHATS_THE_MATTER_WITH_YOU_LIKE_AN_AMATEUR;

	/**
	 * ID: 1800827<br>
	 * Message: �����A�G�[�v�����t�[���ɂ����������ƁA����H�Ȃ����āH�b�ɂȂ�Ȃ��ȁB
	 */
	public static final NpcStringId HEY_DID_YOU_TELL_A_LIE_ON_APRIL_FOOLS_DAY_DONT_TALK_TO_ME_IF_YOU_DIDNT;

	/**
	 * ID: 1800828<br>
	 * Message: �N���N���D�D�D�Ȃ�ŉ�������Ȃ��Ƃ��Ȃ���Ȃ�Ȃ��񂾂�I
	 */
	public static final NpcStringId GRUNT_WHATS_WRONG_WITH_ME;

	/**
	 * ID: 1800829<br>
	 * Message: �N���N���D�D�D���`���
	 */
	public static final NpcStringId GRUNT_OH;

	/**
	 * ID: 1800830<br>
	 * Message: �V�[�t �t�@�C�^�[���Èł��炠�Ȃ����U�����܂��I
	 */
	public static final NpcStringId THE_GRAVE_ROBBER_WARRIOR_HAS_BEEN_FILLED_WITH_DARK_ENERGY_AND_IS_ATTACKING_YOU;

	/**
	 * ID: 1800831<br>
	 * Message: �Ւd�̎��҂����Ȃ������߂Ă��܂��I\n����ɒ��񂾎҂͎��������ď����̂ł��B
	 */
	public static final NpcStringId THE_ALTAR_GUARDIAN_IS_SCRUTINIZING_YOU_NTHOSE_WHO_DARE_TO_CHALLENGE_USING_THE_POWER_OF_EVIL_SHALL_BE_PUNISHED_WITH_DEATH;

	/**
	 * ID: 1800832<br>
	 * Message: �܁A�҂��Ă���B������߂ɂ��悤�B�����Ă��ꂽ��1000���A�f�i���B
	 */
	public static final NpcStringId WAIT_WAIT_STOP_SAVE_ME_AND_ILL_GIVE_YOU_10000000_ADENA;

	/**
	 * ID: 1800833<br>
	 * Message: ���D�D�D�키�̂���Ȃ񂾂��ǂȂ��D�D�D
	 */
	public static final NpcStringId I_DONT_WANT_TO_FIGHT;

	/**
	 * ID: 1800834<br>
	 * Message: �ǂ����Ă��Ȃ���Ȃ�Ȃ��́H
	 */
	public static final NpcStringId IS_THIS_REALLY_NECESSARY;

	/**
	 * ID: 1800835<br>
	 * Message: ���肪�Ƃ�D�D�D���܂��݂����Ȃ�Ȃ�A�����F�B�ɂȂꂽ�����ȁD�D�D
	 */
	public static final NpcStringId TH_THANKS_I_COULD_HAVE_BECOME_GOOD_FRIENDS_WITH_YOU;

	/**
	 * ID: 1800836<br>
	 * Message: �񑩂ǂ���1000���A�f�i��낤�B���͈ӊO�Ɩ񑩂��悭���I�[�N��������˂�����H
	 */
	public static final NpcStringId ILL_GIVE_YOU_10000000_ADENA_LIKE_I_PROMISED_I_MIGHT_BE_AN_ORC_WHO_KEEPS_MY_PROMISES;

	/**
	 * ID: 1800837<br>
	 * Message: ���肪�Ƃ�I�ł��A1000���A�f�i�̘b�͂�������`��B���΂�I
	 */
	public static final NpcStringId THANKS_BUT_THAT_THING_ABOUT_10000000_ADENA_WAS_A_LIE_SEE_YA;

	/**
	 * ID: 1800838<br>
	 * Message: ���̘b��M����Ƃ́A���܂������������ȁB
	 */
	public static final NpcStringId YOURE_PRETTY_DUMB_TO_BELIEVE_ME;

	/**
	 * ID: 1800839<br>
	 * Message: �������D�D�D���O�ɂ̂낢�������Ă��I
	 */
	public static final NpcStringId UGH_A_CURSE_UPON_YOU;

	/**
	 * ID: 1800840<br>
	 * Message: ���D�D�D�ق�Ƃɐ키�̂��₾�����̂ɁD�D�D
	 */
	public static final NpcStringId I_REALLY_DIDNT_WANT_TO_FIGHT;

	/**
	 * ID: 1800841<br>
	 * Message: �J�[�V���̖ڂ����Ȃ����Î����܂��B
	 */
	public static final NpcStringId KASHAS_EYE_IS_SCRUTINIZING_YOU;

	/**
	 * ID: 1800842<br>
	 * Message: �J�[�V���̖ڂ���q��łȂ��C���������܂��B
	 */
	public static final NpcStringId THE_KASHAS_EYE_GIVES_YOU_A_STRANGE_FEELING;

	/**
	 * ID: 1800843<br>
	 * Message: �J�[�V���̖ڂ̖��C���}���ɋ����Ȃ����̂��������܂��B
	 */
	public static final NpcStringId THE_EVIL_AURA_OF_THE_KASHAS_EYE_SEEMS_TO_BE_INCREASING_QUICKLY;

	/**
	 * ID: 1800844<br>
	 * Message: �킵�͍Ւd������Ă���B�Ւd�̂��΂��痣���킯�ɂ͂�����I
	 */
	public static final NpcStringId I_PROTECT_THE_ALTAR_YOU_CANT_ESCAPE_THE_ALTAR;

	/**
	 * ID: 1800845<br>
	 * Message: $s1!������Еt���˂΂Ȃ�ʁB�ł������͂�݂����B
	 */
	public static final NpcStringId S1_THAT_STRANGER_MUST_BE_DEFEATED_HERE_IS_THE_ULTIMATE_HELP;

	/**
	 * ID: 1800846<br>
	 * Message: �����I$s1�A����Ȃɗ����ȁB
	 */
	public static final NpcStringId LOOK_HERE_S1_DONT_FALL_TOO_FAR_BEHIND;

	/**
	 * ID: 1800847<br>
	 * Message: �悭������B$s1�A�͂ɂȂ�Ċ������B
	 */
	public static final NpcStringId WELL_DONE_S1_YOUR_HELP_IS_MUCH_APPRECIATED;

	/**
	 * ID: 1800848<br>
	 * Message: ��X�̖�����ז�����̂͒N���I
	 */
	public static final NpcStringId WHO_HAS_AWAKENED_US_FROM_OUR_SLUMBER;

	/**
	 * ID: 1800849<br>
	 * Message: �F�̎ҁI����Ɏv���m�点�Ă��B
	 */
	public static final NpcStringId ALL_WILL_PAY_A_SEVERE_PRICE_TO_ME_AND_THESE_HERE;

	/**
	 * ID: 1800850<br>
	 * Message: �V���C�h�̗Y�����т���𑝂�����܂��B
	 */
	public static final NpcStringId SHYEEDS_CRY_IS_STEADILY_DYING_DOWN;

	/**
	 * ID: 1800851<br>
	 * Message: �x��F�J���W�Q���͔����I�s���̎��ԑΉ��v��Ɋ�Â��Ď����ڕW�ݒ�I
	 */
	public static final NpcStringId ALERT_ALERT_DAMAGE_DETECTION_RECOGNIZED_COUNTERMEASURES_ENABLED;

	/**
	 * ID: 1800852<br>
	 * Message: �΁D�ہD�F�D���D�^�[�D�Q�b�D�g�D�݁D��D
	 */
	public static final NpcStringId TARGET_RECOGNITION_ACHIEVED_ATTACK_SEQUENCE_COMMENCING;

	/**
	 * ID: 1800853<br>
	 * Message: �^�[�D�Q�b�D�g�D��D���D���D�x�D���D�ŁD���D�ʁD�΁D���D�J�D�n�D
	 */
	public static final NpcStringId TARGET_THREAT_LEVEL_LAUNCHING_STRONGEST_COUNTERMEASURE;

	/**
	 * ID: 1800854<br>
	 * Message: ���@�w����������Ă���B���삽����A���ɒ����I
	 */
	public static final NpcStringId THE_PURIFICATION_FIELD_IS_BEING_ATTACKED_GUARDIAN_SPIRITS_PROTECT_THE_MAGIC_FORCE;

	/**
	 * ID: 1800855<br>
	 * Message: �C���i�h�����́D�D�D���̂��߂ɁD�D�D�V�����D�D�D�I���˂΁D�D�D
	 */
	public static final NpcStringId PROTECT_THE_BRAZIERS_OF_PURITY_AT_ALL_COSTS;

	/**
	 * ID: 1800856<br>
	 * Message: ���@�w���D�D�D���点��D�D�D���̖��ƁD�D�D���������Ăł��D�D�D
	 */
	public static final NpcStringId DEFEND_OUR_DOMAIN_EVEN_AT_RISK_OF_YOUR_OWN_LIFE;

	/**
	 * ID: 1800857<br>
	 * Message: �v���O���C ���O�����i�v�I
	 */
	public static final NpcStringId PEUNGLUI_MUGLANEP;

	/**
	 * ID: 1800858<br>
	 * Message: �i�C�A ���K�i�O�� �v�^�O���I
	 */
	public static final NpcStringId NAIA_WAGANAGEL_PEUTAGUN;

	/**
	 * ID: 1800859<br>
	 * Message: ��D���D���D�u�D���D��D���D�i�D�́D�}�D���D��D���D
	 */
	public static final NpcStringId DRIVE_DEVICE_PARTIAL_DESTRUCTION_IMPULSE_RESULT;

	/**
	 * ID: 1800860<br>
	 * Message: ���@�w�ɔ���ꂽ�g�Ƃ͂����D�D�D���܂������͋����˂��D�D�D
	 */
	public static final NpcStringId EVEN_THE_MAGIC_FORCE_BINDS_YOU_YOU_WILL_NEVER_BE_FORGIVEN;

	/**
	 * ID: 1800861<br>
	 * Message: ���l������A�N���Ҕ����I
	 */
	public static final NpcStringId OH_GIANTS_AN_INTRUDER_HAS_BEEN_DISCOVERED;

	/**
	 * ID: 1800862<br>
	 * Message: �ނȂ����D�D�D���������̂܂܂ł͏I���Ȃ��I
	 */
	public static final NpcStringId ALL_IS_VANITY_BUT_THIS_CANNOT_BE_THE_END;

	/**
	 * ID: 1800863<br>
	 * Message: ���̑O�ɗ��҂͂��ׂ��炭�ł���I
	 */
	public static final NpcStringId THOSE_WHO_ARE_IN_FRONT_OF_MY_EYES_WILL_BE_DESTROYED;

	/**
	 * ID: 1800864<br>
	 * Message: �����I���������N�����ȁI
	 */
	public static final NpcStringId I_AM_TIRED_DO_NOT_WAKE_ME_UP_AGAIN;

	/**
	 * ID: 1800865<br>
	 * Message: �N �� �� �� ��
	 */
	public static final NpcStringId _INTRUDER_DETECTED;

	/**
	 * ID: 1800866<br>
	 * Message: ���̃L�����h�����U�P���̋��ꏊ�������̂��B�j�󂹂�B
	 */
	public static final NpcStringId THE_CANDLES_CAN_LEAD_YOU_TO_ZAKEN_DESTROY_HIM;

	/**
	 * ID: 1800867<br>
	 * Message: �U�P���l�𖰂肩��o�܂��̂͒N���I
	 */
	public static final NpcStringId WHO_DARES_AWKAWEN_THE_MIGHTY_ZAKEN;

	/**
	 * ID: 1800868<br>
	 * Message: ���̕��Ŏ���T���Ă���ȁA�����҂ǂ��߁B
	 */
	public static final NpcStringId YE_NOT_BE_FINDING_ME_BELOW_THE_DRINK;

	/**
	 * ID: 1800869<br>
	 * Message: �����Ⴂ�ȂƂ���Ŏ���T���Ă���ȁA�܂ʂ��ǂ��߁B
	 */
	public static final NpcStringId YE_MUST_BE_THREE_SHEETS_TO_THE_WIND_IF_YER_LOOKIN_FOR_ME_THERE;

	/**
	 * ID: 1800870<br>
	 * Message: ��̕��Ŏ���T���Ă���ȁA�΂��҂ǂ��߁B
	 */
	public static final NpcStringId YE_NOT_BE_FINDING_ME_IN_THE_CROWS_NEST;

	/**
	 * ID: 1800871<br>
	 * Message: ���������ꂾ�������Ȃ��D�D�D�������Ă���I
	 */
	public static final NpcStringId SORRY_BUT_THIS_IS_ALL_I_HAVE_GIVE_ME_A_BREAK;

	/**
	 * ID: 1800872<br>
	 * Message: �v���O���C ���O�����i�v �i�C�A ���K�i�O�� �v�^�O���I
	 */
	public static final NpcStringId PEUNGLUI_MUGLANEP_NAIA_WAGANAGEL_PEUTAGUN;

	/**
	 * ID: 1800873<br>
	 * Message: ��D���D���D�u�D�S�D��D�ځD���D��D�~�D
	 */
	public static final NpcStringId DRIVE_DEVICE_ENTIRE_DESTRUCTION_MOVING_SUSPENSION;

	/**
	 * ID: 1800874<br>
	 * Message: �����D�D�D���@�w����̒E�o�͂��͂�D�D�D
	 */
	public static final NpcStringId AH_AH_FROM_THE_MAGIC_FORCE_NO_MORE_I_WILL_BE_FREED;

	/**
	 * ID: 1800875<br>
	 * Message: �����΂�Ă邼�I
	 */
	public static final NpcStringId YOU_GUYS_ARE_DETECTED;

	/**
	 * ID: 1800876<br>
	 * Message: ����I
	 */
	public static final NpcStringId WHAT_KIND_OF_CREATURES_ARE_YOU;

	/**
	 * ID: 1800877<br>
	 * Message: ���x��$s1��$s2���l�����܂����B
	 */
	public static final NpcStringId S2_OF_LEVEL_S1_IS_ACQUIRED;

	/**
	 * ID: 1800878<br>
	 * Message: ���Â̐����Ίl��
	 */
	public static final NpcStringId LIFE_STONE_FROM_THE_BEGINNING_ACQUIRED;

	/**
	 * ID: 1800879<br>
	 * Message: �C���x���g���̏d�ʁA���ʂ�80%�ȏ�̏�Ԃł͑��Â̐����΂͊l���ł��܂���B
	 */
	public static final NpcStringId WHEN_INVENTORY_WEIGHT_NUMBER_ARE_MORE_THAN_80_THE_LIFE_STONE_FROM_THE_BEGINNING_CANNOT_BE_ACQUIRED;

	/**
	 * ID: 1800880<br>
	 * Message: ���܂���͂��͂⎄�̎蒆���瓦����Ȃ��I
	 */
	public static final NpcStringId YOU_ARE_UNDER_MY_THUMB;

	/**
	 * ID: 1800881<br>
	 * Message: �C���X�^���g �]�[���̎c�莞�Ԃ�20����������܂����B
	 */
	public static final NpcStringId N21_MINUTES_ARE_ADDED_TO_THE_REMAINING_TIME_IN_THE_INSTANT_ZONE;

	/**
	 * ID: 1800882<br>
	 * Message: �}���I
	 */
	public static final NpcStringId HURRY_HURRY;

	/**
	 * ID: 1800883<br>
	 * Message: ���͗���҂̋C���Ȃ񂾁B
	 */
	public static final NpcStringId I_AM_NOT_THAT_TYPE_OF_PERSON_WHO_STAYS_IN_ONE_PLACE_FOR_A_LONG_TIME;

	/**
	 * ID: 1800884<br>
	 * Message: ��������ė����Ă�̂��炢�B
	 */
	public static final NpcStringId ITS_HARD_FOR_ME_TO_KEEP_STANDING_LIKE_THIS;

	/**
	 * ID: 1800885<br>
	 * Message: ���x�͂������̕��ɍs���Ă݂悤���B
	 */
	public static final NpcStringId WHY_DONT_I_GO_THAT_WAY_THIS_TIME;

	/**
	 * ID: 1800886<br>
	 * Message: �悭�����ȁB
	 */
	public static final NpcStringId WELCOME;

	/**
	 * ID: 1800887<br>
	 * Message: ���O�����̎��͂͂���Ȃ��̂��B�����Ƃ���΂�B
	 */
	public static final NpcStringId IS_THAT_IT_IS_THAT_THE_EXTENT_OF_YOUR_ABILITIES_PUT_IN_A_LITTLE_MORE_EFFORT;

	/**
	 * ID: 1800888<br>
	 * Message: ���O�̎��͂͂��̒��x�Ƃ͂ȁD�D�D����ɂ��鉿�l���Ȃ���I
	 */
	public static final NpcStringId YOUR_ABILITIES_ARE_PITIFUL_YOU_ARE_FAR_FROM_A_WORTHY_OPPONENT;

	/**
	 * ID: 1800889<br>
	 * Message: ����ł܂Ŋl����T�����̂悤�ɗ��������܂�킹��̂��I
	 */
	public static final NpcStringId EVEN_AFTER_DEATH_YOU_ORDER_ME_TO_WANDER_AROUND_LOOKING_FOR_THE_SCAPEGOATS;

	/**
	 * ID: 1800890<br>
	 * Message: �M���ǂ��󂯂Ă݂�I�ܔM�̔M���ǂ�3�i�K�܂őς��������Ƃ��ł�����A�q���[�~�b�h�l�����܂��̑�����Ȃ��邾�낤�B
	 */
	public static final NpcStringId HERE_GOES_THE_HEATSTROKE_IF_YOU_CAN_WITHSTAND_THE_HOT_HEATSTROKE_UP_TO_THE_3RD_STAGE_THE_SULTRINESS_WILL_COME_TO_YOU;

	/**
	 * ID: 1800891<br>
	 * Message: �܂������������҂����B�q���[�~�b�h�l�͗�C�C�U����10��ȏ�ς������M������������ȁB
	 */
	public static final NpcStringId JUST_YOU_WAIT_HUMIDITY_IS_A_BLISTERING_FIREBALL_WHICH_CAN_EASILY_WITHSTAND_PLENTY_OF_COOL_AIR_CANNON_ATTACKS;

	/**
	 * ID: 1800892<br>
	 * Message: �q���[�~�b�h��|���ɂ́A�h�N�^�[ �A�C�X�̔M���Ǘ\�h���ʂ��󂯂āA��C�C�U����10��ȏ�g���Ă��������B
	 */
	public static final NpcStringId IN_ORDER_TO_DEFEAT_HUMIDITY_YOU_MUST_OBTAIN_THE_HEADSTROKE_PREVENTION_EFFECT_FROM_DOCTOR_ICE_AND_FIRE_MORE_THAN_10_ROUNDS_OF_THE_COOL_AIR_CANNON_ON_IT;

	/**
	 * ID: 1800893<br>
	 * Message: ���ŉ΂ɓ���Ă̒��D�D�D$s1�������Ă����I
	 */
	public static final NpcStringId YOU_ARE_HERE_S1_ILL_TEACH_YOU_A_LESSON_BRING_IT_ON;

	/**
	 * ID: 1800894<br>
	 * Message: �߂Ă��I��C�C���ȁI���͗₽���̂����Ȃ񂾁I
	 */
	public static final NpcStringId THATS_COLD_ISNT_IT_ONE_OF_THOSE_COOL_PACKS_I_HATE_ANYTHING_THATS_COLD;

	/**
	 * ID: 1800895<br>
	 * Message: �ӂ��A�͂��ꂾ��`��I���O�̘r�͂���Ȃ��񂩁B
	 */
	public static final NpcStringId HUH_YOUVE_MISSED_IS_THAT_ALL_YOU_HAVE;

	/**
	 * ID: 1800896<br>
	 * Message: ���̓��񂾑�؂ȕi����邩��D�D�D���������߂Ȃ��ł��`
	 */
	public static final NpcStringId I_WILL_GIVE_YOU_PRECIOUS_THINGS_THAT_I_HAVE_STOLEN_SO_STOP_BOTHERING_ME;

	/**
	 * ID: 1800897<br>
	 * Message: �哖����A�C�e������낤�Ƃ����̂ɁD�D�D�C���x���g���[�������ς����ȁB����ˁ`
	 */
	public static final NpcStringId I_WAS_GOING_TO_GIVE_YOU_A_JACKPOT_ITEM_YOU_DONT_HAVE_ENOUGH_INVENTORY_ROOM_SEE_YOU_NEXT_TIME;

	/**
	 * ID: 1800898<br>
	 * Message: $s1�̓q���[�~�b�h��|����S84�A�C�e�����l�����܂����B
	 */
	public static final NpcStringId S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S84;

	/**
	 * ID: 1800899<br>
	 * Message: $s1�̓q���[�~�b�h��|����S80�A�C�e�����l�����܂����B
	 */
	public static final NpcStringId S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S80;

	/**
	 * ID: 1800900<br>
	 * Message: ���O�̂ւȂ��傱��C�C���Ƃ������ɒʂ���Ƃł��v���Ă�̂��I����ɂ��鉿�l���Ȃ��I
	 */
	public static final NpcStringId I_AM_NOT_HERE_FOR_YOU_YOUR_COOL_PACK_ATTACK_DOES_NOT_WORK_AGAINST_ME;

	/**
	 * ID: 1800901<br>
	 * Message: ������H�ǂ��ɉB�ꂽ�񂾁H���̑��肪���Ȃ����B���ꂶ��A�Z�������牴�͂���ŁD�D�D
	 */
	public static final NpcStringId UH_OH_WHERE_ARE_YOU_HIDING_THERE_IS_NOBODY_WHO_MATCHES_MY_SKILLS_WELL_I_GUESS_ID_BETTER_GET_GOING;

	/**
	 * ID: 1800902<br>
	 * Message: ���A�N�V�������Ȃ��Ȃ��B��C�C���Ȃ������ɁA���ɒ��ނ̂͐�N������I
	 */
	public static final NpcStringId WHY_ARE_YOU_NOT_RESPONDING_YOU_DONT_EVEN_HAVE_ANY_COOL_PACKS_YOU_CANT_FIGHT_ME;

	/**
	 * ID: 1800903<br>
	 * Message: �����͂ǂ���I�����Ă񂾂̂͒N��I
	 */
	public static final NpcStringId OH_WHERE_I_BE_WHO_CALL_ME;

	/**
	 * ID: 1800904<br>
	 * Message: ����[��I���A�X�C�J��ł��I
	 */
	public static final NpcStringId TADA_ITS_A_WATERMELON;

	/**
	 * ID: 1800906<br>
	 * Message: �X�C�J�̓o���ł��I���ꂩ��ǂ�ǂ��ł��I
	 */
	public static final NpcStringId ENTER_THE_WATERMELON_ITS_GONNA_GROW_AND_GROW_FROM_NOW_ON;

	/**
	 * ID: 1800907<br>
	 * Message: �ւ��A�Ђ����Ԃ���I
	 */
	public static final NpcStringId OH_OUCH_DID_I_SEE_YOU_BEFORE;

	/**
	 * ID: 1800908<br>
	 * Message: �傫���X�C�J�ɂȁ`��I����ς�Ă̓X�C�J�����˂��B
	 */
	public static final NpcStringId A_NEW_SEASON_SUMMER_IS_ALL_ABOUT_THE_WATERMELON;

	/**
	 * ID: 1800909<br>
	 * Message: ���̂��ƁA�Ă񂾁H���낻�뉽���o����Ďv���Ă��Ƃ��Ⴄ�́H
	 */
	public static final NpcStringId DID_YA_CALL_HO_THOUGHT_YOUD_GET_SOMETHING;

	/**
	 * ID: 1800910<br>
	 * Message: ���h�Ɉ�������̎p�A���������H
	 */
	public static final NpcStringId DO_YOU_WANT_TO_SEE_MY_BEAUTIFUL_SELF;

	/**
	 * ID: 1800911<br>
	 * Message: �ւււ��I�ꏏ�ɂ�낤�I
	 */
	public static final NpcStringId HOHOHO_LETS_DO_IT_TOGETHER;

	/**
	 * ID: 1800912<br>
	 * Message: ���܂����ƈ�Ă���r�b�O�X�C�J�A���s������o�����Ȃ���ł��I
	 */
	public static final NpcStringId ITS_A_GIANT_WATERMELON_IF_YOU_RAISE_IT_RIGHT_AND_A_WATERMELON_SLICE_IF_YOU_MESS_UP;

	/**
	 * ID: 1800913<br>
	 * Message: ����[��I�ϐg�����ł��I
	 */
	public static final NpcStringId TADA_TRANSFORMATION_COMPLETE;

	/**
	 * ID: 1800914<br>
	 * Message: ���[�āA�ǎ��ȑ傫�ȃX�C�J��납�ȁH�s�ǂȑ傫�ȃX�C�J��납�ȁH
	 */
	public static final NpcStringId AM_I_A_RAIN_WATERMELON_OR_A_DEFECTIVE_WATERMELON;

	/**
	 * ID: 1800915<br>
	 * Message: �傫���Ȃ��Ă��������ȁ`�����A��������I
	 */
	public static final NpcStringId NOW_IVE_GOTTEN_BIG_EVERYONE_COME_AT_ME;

	/**
	 * ID: 1800916<br>
	 * Message: �傫���Ȃ那�`�����Ȃ那�`�肢�������Ă݁I
	 */
	public static final NpcStringId GET_BIGGER_GET_STRONGER_TELL_ME_YOUR_WISH;

	/**
	 * ID: 1800917<br>
	 * Message: �o�����Ȃ��ɂȁ`��I���āA�����炿�܂�������I
	 */
	public static final NpcStringId A_WATERMELON_SLICES_WISH_BUT_IM_BIGGER_ALREADY;

	/**
	 * ID: 1800918<br>
	 * Message: ���ł����X�C�J�ɂȁ`��I�͂�N�����Ă�I
	 */
	public static final NpcStringId A_LARGE_WATERMELONS_WISH_WELL_TRY_TO_BREAK_ME;

	/**
	 * ID: 1800919<br>
	 * Message: ����Ă��������ȁ`�قȁA������ł��I
	 */
	public static final NpcStringId IM_DONE_GROWING_IM_RUNNING_AWAY_NOW;

	/**
	 * ID: 1800920<br>
	 * Message: 1000���A�f�i��邩��A�����������Ă�����I
	 */
	public static final NpcStringId IF_YOU_LET_ME_GO_ILL_GIVE_YOU_TEN_MILLION_ADENA;

	/**
	 * ID: 1800921<br>
	 * Message: ���`�āA���̒��ɂ͉����l�܂��Ă邩�Ȃ��H
	 */
	public static final NpcStringId FREEDOM_WHAT_DO_YOU_THINK_I_HAVE_INSIDE;

	/**
	 * ID: 1800922<br>
	 * Message: �������A�������I�ق�Ȃ�A���������炦���̂��킩���Ă���H
	 */
	public static final NpcStringId OK_OK_GOOD_JOB_YOU_KNOW_WHAT_TO_DO_NEXT_RIGHT;

	/**
	 * ID: 1800923<br>
	 * Message: �����Ƃ�����I���ڂ��Ă���I�ق�܂ɂ��������Ȃ��D�D�D
	 */
	public static final NpcStringId LOOK_HERE_DO_IT_RIGHT_YOU_SPILLED_THIS_PRECIOUS;

	/**
	 * ID: 1800924<br>
	 * Message: �����A�����C��������I�����Ƃ����āI
	 */
	public static final NpcStringId AH_REFRESHING_SPRAY_A_LITTLE_MORE;

	/**
	 * ID: 1800925<br>
	 * Message: �ނ�����C�����������I�����ƂȂ��́H
	 */
	public static final NpcStringId GULP_GULP_GREAT_BUT_ISNT_THERE_MORE;

	/**
	 * ID: 1800926<br>
	 * Message: ���̉��肭���I�͂����܂�����I
	 */
	public static final NpcStringId CANT_YOU_EVEN_AIM_RIGHT_HAVE_YOU_EVEN_BEEN_TO_THE_ARMY;

	/**
	 * ID: 1800927<br>
	 * Message: �����I����˂�A����I�ܖ������؂�Ă�񂿂Ⴄ���H
	 */
	public static final NpcStringId DID_YOU_MIX_THIS_WITH_WATER_WHYS_IT_TASTE_LIKE_THIS;

	/**
	 * ID: 1800928<br>
	 * Message: �������ȁA�������ȁB�����Ƃ��񂪂񂩂��Ă�I
	 */
	public static final NpcStringId OH_GOOD_DO_A_LITTLE_MORE_YEAH;

	/**
	 * ID: 1800929<br>
	 * Message: �����A���Ⴄ������`�˂�I�����A�����I���́A�w�^�N�\�I
	 */
	public static final NpcStringId HOHO_ITS_NOT_THERE_OVER_HERE_AM_I_SO_SMALL_THAT_YOU_CAN_EVEN_SPRAY_ME_RIGHT;

	/**
	 * ID: 1800930<br>
	 * Message: �����I�������̉���˂�I�l�N�^�[���Ⴄ��Ƃ��Ⴄ�́H
	 */
	public static final NpcStringId YUCK_WHAT_IS_THIS_ARE_YOU_SURE_THIS_IS_NECTAR;

	/**
	 * ID: 1800931<br>
	 * Message: �����Ƃ���Ă��ȁI���܂����Ƃ����ւ񂩂�����A�������炽��ł��I
	 */
	public static final NpcStringId DO_YOUR_BEST_I_BECOME_A_BIG_WATERMELON_AFTER_JUST_FIVE_BOTTLES;

	/**
	 * ID: 1800932<br>
	 * Message: �ق�܂Ƀl�N�^�[�̓X�C�J�l�N�^�[���ō���Ȃ��I�͂͂͂��I
	 */
	public static final NpcStringId OF_COURSE_WATERMELON_IS_THE_BEST_NECTAR_HAHAHA;

	/**
	 * ID: 1800934<br>
	 * Message: �Ȃ�łǂ��˂�I�\�̓n���^�`�C�I�����ƃl�N�^�[�����I
	 */
	public static final NpcStringId OWW_YOURE_JUST_BEATING_ME_NOW_GIVE_ME_NECTAR;

	/**
	 * ID: 1800935<br>
	 * Message: ���ꂽ��ǂȂ�����˂���I
	 */
	public static final NpcStringId LOOK_ITS_GONNA_BREAK;

	/**
	 * ID: 1800936<br>
	 * Message: ����Ă������̂ɐH���񂩂����I����ɂ����I
	 */
	public static final NpcStringId NOW_ARE_YOU_TRYING_TO_EAT_WITHOUT_DOING_THE_WORK_FINE_DO_WHAT_YOU_WANT_ILL_HATE_YOU_IF_YOU_DONT_GIVE_ME_ANY_NECTAR;

	/**
	 * ID: 1800937<br>
	 * Message: �ǂ�ǂ�ǂ��Ă�`�l�N�^�[���ق����˂�I
	 */
	public static final NpcStringId HIT_ME_MORE_HIT_ME_MORE;

	/**
	 * ID: 1800938<br>
	 * Message: ������Ă�������ǂȂ�����˂�I�ӔC����Ă�`
	 */
	public static final NpcStringId IM_GONNA_WITHER_LIKE_THIS_DAMN_IT;

	/**
	 * ID: 1800939<br>
	 * Message: ������Ă�������A���܂葹�̂����т�ׂ���ł��I����Ȃɉ��������Ȃ񂩁H
	 */
	public static final NpcStringId HEY_YOU_IF_I_DIE_LIKE_THIS_THERELL_BE_NO_ITEM_EITHER_ARE_YOU_REALLY_SO_STINGY_WITH_THE_NECTAR;

	/**
	 * ID: 1800940<br>
	 * Message: ���������A�����Ƃ���΂��`
	 */
	public static final NpcStringId ITS_JUST_A_LITTLE_MORE_GOOD_LUCK;

	/**
	 * ID: 1800941<br>
	 * Message: ���̓l�N�^�[�����܂�Ǝ���ł܂����B����m���Ǝ��ʂ悤�Ȃ����I�����Ă�����I
	 */
	public static final NpcStringId SAVE_ME_IM_ABOUT_TO_DIE_WITHOUT_TASTING_NECTAR_EVEN_ONCE;

	/**
	 * ID: 1800942<br>
	 * Message: �����A���͂������ʂ��D�D�D�߂����D�D�D
	 */
	public static final NpcStringId IF_I_DIE_LIKE_THIS_ILL_JUST_BE_A_WATERMELON_SLICE;

	/**
	 * ID: 1800943<br>
	 * Message: ���炵�߂���˂�I�����O������I30�b�ȓ��ɂ���ւ񂩂�����A�A��ł��I
	 */
	public static final NpcStringId IM_GETTING_STRONGER_I_THINK_ILL_BE_ABLE_TO_RUN_AWAY_IN_30_SECONDS_HOHO;

	/**
	 * ID: 1800944<br>
	 * Message: ����20�b�����Ȃ��ł��I
	 */
	public static final NpcStringId ITS_GOODBYE_AFTER_20_SECONDS;

	/**
	 * ID: 1800945<br>
	 * Message: �����A�c��10�b�I9�D�D�D8�D�D�D7�D�D�D
	 */
	public static final NpcStringId YEAH_10_SECONDS_LEFT_9_8_7;

	/**
	 * ID: 1800946<br>
	 * Message: �l�N�^�[������ւ񂩂�����A2����ɋA��ł��I
	 */
	public static final NpcStringId IM_LEAVING_IN_2_MINUTES_IF_YOU_DONT_GIVE_ME_ANY_NECTAR;

	/**
	 * ID: 1800947<br>
	 * Message: �l�N�^�[������ւ񂩂�����A1����ɋA��ł��I
	 */
	public static final NpcStringId IM_LEAVING_IN_1_MINUTES_IF_YOU_DONT_GIVE_ME_ANY_NECTAR;

	/**
	 * ID: 1800948<br>
	 * Message: ���A�A���B�قȁA�����Ȃ�I
	 */
	public static final NpcStringId IM_LEAVING_NOW_THEN_GOODBYE;

	/**
	 * ID: 1800949<br>
	 * Message: �c�O��ȁB�r�b�N�X�C�J�Ƃ͂���ł�����΂�B
	 */
	public static final NpcStringId SORRY_BUT_THIS_LARGE_WATERMELON_IS_DISAPPEARING_HERE;

	/**
	 * ID: 1800950<br>
	 * Message: �x���˂���I�قȁA�����Ȃ�I
	 */
	public static final NpcStringId TOO_LATE_HAVE_A_GOOD_TIME;

	/**
	 * ID: 1800951<br>
	 * Message: ���A�����s���Ȃ�����˂�B�����y���݂ɂ��Ă�ł��I
	 */
	public static final NpcStringId DING_DING_THATS_THE_BELL_PUT_AWAY_YOUR_WEAPONS_AND_TRY_FOR_NEXT_TIME;

	/**
	 * ID: 1800952<br>
	 * Message: �c�O��ȁB����������Ƃ�����̂ɁB
	 */
	public static final NpcStringId TOO_BAD_YOU_RAISED_IT_UP_TOO;

	/**
	 * ID: 1800953<br>
	 * Message: �ւ��A�������F��ȁB
	 */
	public static final NpcStringId OH_WHAT_A_NICE_SOUND;

	/**
	 * ID: 1800954<br>
	 * Message: �Ȃ��A�Ȃ��A��ȉ̂��Ă��������H
	 */
	public static final NpcStringId THE_INSTRUMENT_IS_NICE_BUT_THERES_NO_SONG_SHALL_I_SING_FOR_YOU;

	/**
	 * ID: 1800955<br>
	 * Message: �����Ȃ����イ�͉̂ʕ��ɂ��`���˂�B
	 */
	public static final NpcStringId WHAT_BEAUTIFUL_MUSIC;

	/**
	 * ID: 1800956<br>
	 * Message: �����A���o�C�₟�`�����ƕ������Ă��Ȃ��I
	 */
	public static final NpcStringId I_FEEL_GOOD_PLAY_SOME_MORE;

	/**
	 * ID: 1800957<br>
	 * Message: ���̂��߂����ɒe���Ă����񂩁H���ꂵ���Ȃ��I
	 */
	public static final NpcStringId MY_HEART_IS_BEING_CAPTURED_BY_THE_SOUND_OF_CRONO;

	/**
	 * ID: 1800958<br>
	 * Message: �Ȃ��A����������������Ƃ��Ⴄ�H
	 */
	public static final NpcStringId GET_THE_NOTES_RIGHT_HEY_OLD_MAN_THAT_WAS_WRONG;

	/**
	 * ID: 1800959<br>
	 * Message: �������ȁA�������ȁI
	 */
	public static final NpcStringId I_LIKE_IT;

	/**
	 * ID: 1800960<br>
	 * Message: �����`�A�����g�̂����Y���Y���Ă�����I
	 */
	public static final NpcStringId OOH_MY_BODY_WANTS_TO_OPEN;

	/**
	 * ID: 1800961<br>
	 * Message: �����A���̘a���I�����ō���ȁI�����ƕ������Ă��Ȃ��I
	 */
	public static final NpcStringId OH_THIS_CHORD_MY_HEART_IS_BEING_TORN_PLAY_A_LITTLE_MORE;

	/**
	 * ID: 1800962<br>
	 * Message: ����₪�ȁI���̃T�E���h�������������Ă�I�A���^�A�~���[�W�V�����ɂȂ���Ƃ��Ⴄ�H
	 */
	public static final NpcStringId ITS_THIS_THIS_I_WANTED_THIS_SOUND_WHY_DONT_YOU_TRY_BECOMING_A_SINGER;

	/**
	 * ID: 1800963<br>
	 * Message: ����Ȃ񂿂Ⴄ�I�ǂ���������A�����Ƃ����Ƃ�����I
	 */
	public static final NpcStringId YOU_CAN_TRY_A_HUNDRED_TIMES_ON_THIS_YOU_WONT_GET_ANYTHING_GOOD;

	/**
	 * ID: 1800964<br>
	 * Message: �ɂ�������`�˂�I���͉��y�����������˂�I�����Ƃ�����I
	 */
	public static final NpcStringId IT_HURTS_PLAY_JUST_THE_INSTRUMENT;

	/**
	 * ID: 1800965<br>
	 * Message: ���̐S�Ɛg�̂ɋ����悤�ȁA�����T�E���h�ŗ��ނł��I
	 */
	public static final NpcStringId ONLY_GOOD_MUSIC_CAN_OPEN_MY_BODY;

	/**
	 * ID: 1800966<br>
	 * Message: ���ꂿ�Ⴄ���ȁI�����A����I�N���m�̊y��I����ł���I
	 */
	public static final NpcStringId NOT_THIS_BUT_YOU_KNOW_THAT_WHAT_YOU_GOT_AS_A_CHRONICLE_SOUVENIR_PLAY_WITH_THAT;

	/**
	 * ID: 1800967<br>
	 * Message: �����A�q�}��B���y�Ȃ�����A�q�}�ł���`�Ȃ��B���A�����A��ł��B
	 */
	public static final NpcStringId WHY_YOU_HAVE_NO_MUSIC_BORING_IM_LEAVING_NOW;

	/**
	 * ID: 1800968<br>
	 * Message: ����Ȃ�A���D����˂�I���̏o���ŗ��ނ�I
	 */
	public static final NpcStringId NOT_THOSE_SHARP_THINGS_USE_THE_ONES_THAT_MAKE_NICE_SOUNDS;

	/**
	 * ID: 1800969<br>
	 * Message: �傫���X�C�J�͉��y�����ł��J���˂�ŁB����łǂ��������Ă�������B
	 */
	public static final NpcStringId LARGE_WATERMELONS_ONLY_OPEN_WITH_MUSIC_JUST_STRIKING_WITH_A_WEAPON_WONT_WORK;

	/**
	 * ID: 1800970<br>
	 * Message: �y��ŗ��ނ�I����Ȃ�A���D����˂�I�y��łȁI
	 */
	public static final NpcStringId STRIKE_WITH_MUSIC_NOT_WITH_SOMETHING_LIKE_THIS_YOU_NEED_MUSIC;

	/**
	 * ID: 1800971<br>
	 * Message: �ނ��Ⴗ�����A�^�b�N��ȁI���₯�ǉ����Ȃ��������疳�ʂ�ł��I
	 */
	public static final NpcStringId YOURE_PRETTY_AMAZING_BUT_ITS_ALL_FOR_NOTHING;

	/**
	 * ID: 1800972<br>
	 * Message: ����̓����X�^�[�p�������`�˂�I�����N���m�������˂�I
	 */
	public static final NpcStringId USE_THAT_ON_MONSTERS_OK_I_WANT_CRONO;

	/**
	 * ID: 1800973<br>
	 * Message: �����ł��I
	 */
	public static final NpcStringId EVERYONE_THE_WATERMELON_IS_BREAKING;

	/**
	 * ID: 1800974<br>
	 * Message: �ł������Ȃ����������Ȃ��ɑ厖�ɂ��Ă�˂�I
	 */
	public static final NpcStringId ITS_LIKE_A_WATERMELON_SLICE;

	/**
	 * ID: 1800976<br>
	 * Message: �r�b�O�X�C�J�ɂȂ�܂��悤�ɂ��I
	 */
	public static final NpcStringId LARGE_WATERMELON_MAKE_A_WISH;

	/**
	 * ID: 1800977<br>
	 * Message: ������I���͂������ʁI
	 */
	public static final NpcStringId DONT_TELL_ANYONE_ABOUT_MY_DEATH;

	/**
	 * ID: 1800978<br>
	 * Message: ������I������A�����`���I
	 */
	public static final NpcStringId UGH_THE_RED_JUICE_IS_FLOWING_OUT;

	/**
	 * ID: 1800979<br>
	 * Message: ����Ŏd��������I
	 */
	public static final NpcStringId THIS_IS_ALL;

	/**
	 * ID: 1800980<br>
	 * Message: ���A���₵�����I
	 */
	public static final NpcStringId KYAAHH_IM_MAD;

	/**
	 * ID: 1800981<br>
	 * Message: �X�C�J�A�����ł��I�A�C�e�����o��ł��I
	 */
	public static final NpcStringId EVERYONE_THIS_WATERMELON_BROKE_OPEN_THE_ITEM_IS_FALLING_OUT;

	/**
	 * ID: 1800982<br>
	 * Message: ����Ă��������Ȃ��I���g���ǂ��ǂ��o�Ă���I
	 */
	public static final NpcStringId OH_IT_BURST_THE_CONTENTS_ARE_SPILLING_OUT;

	/**
	 * ID: 1800983<br>
	 * Message: �قȁA�D�������ނł��I
	 */
	public static final NpcStringId HOHOHO_PLAY_BETTER;

	/**
	 * ID: 1800984<br>
	 * Message: �ւ��A�Ȃ��Ȃ��̘r���I
	 */
	public static final NpcStringId OH_YOURE_VERY_TALENTED_HUH;

	/**
	 * ID: 1800985<br>
	 * Message: ���炨��A�����Ƃǂ��񂩂�������`�˂�I
	 */
	public static final NpcStringId PLAY_SOME_MORE_MORE_MORE_MORE;

	/**
	 * ID: 1800986<br>
	 * Message: ���̓hM��˂�B�����Ƃǂ��Ă����I
	 */
	public static final NpcStringId I_EAT_HITS_AND_GROW;

	/**
	 * ID: 1800987<br>
	 * Message: ����΂�񂩂��ȁI���ԁA����ւ񂪂ȁB
	 */
	public static final NpcStringId BUCK_UP_THERE_ISNT_MUCH_TIME;

	/**
	 * ID: 1800988<br>
	 * Message: ����Ȃ�Ŋ����v���Ă�̂��Ⴄ���ȁH
	 */
	public static final NpcStringId DO_YOU_THINK_ILL_BURST_WITH_JUST_THAT;

	/**
	 * ID: 1800989<br>
	 * Message: �������ȁA�������ȁI����Ȃ�ň����������ނ�B
	 */
	public static final NpcStringId WHAT_A_NICE_ATTACK_YOU_MIGHT_BE_ABLE_TO_KILL_A_PASSING_FLY;

	/**
	 * ID: 1800990<br>
	 * Message: ���₹��A���������I���Ⴄ���Ⴄ�I�����ƉE��I
	 */
	public static final NpcStringId RIGHT_THERE_A_LITTLE_TO_THE_RIGHT_AH_REFRESHING;

	/**
	 * ID: 1800991<br>
	 * Message: �Ȃ��A���̃l�R�p���`�݂����Ȃ�́H���O�͂�����I
	 */
	public static final NpcStringId YOU_CALL_THAT_HITTING_BRING_SOME_MORE_TALENTED_FRIENDS;

	/**
	 * ID: 1800992<br>
	 * Message: �l������@�����Ղ��A������`�������ȁB
	 */
	public static final NpcStringId DONT_THINK_JUST_HIT_WERE_HITTING;

	/**
	 * ID: 1800993<br>
	 * Message: ���ށA�����ƃl�N�^�[����I
	 */
	public static final NpcStringId I_NEED_NECTAR_GOURD_NECTAR;

	/**
	 * ID: 1800994<br>
	 * Message: �l�N�^�[�Ȃ�������炽�ւ���I
	 */
	public static final NpcStringId I_CAN_ONLY_GROW_BY_DRINKING_NECTAR;

	/**
	 * ID: 1800995<br>
	 * Message: ����΂��Ĉ�Ă��I���܂����ƈ�Ă���r�b�O�X�C�J�A���s������o�����Ȃ���ł��I
	 */
	public static final NpcStringId GROW_ME_QUICK_IF_YOURE_GOOD_ITS_A_LARGE_WATERMELON_IF_YOURE_BAD_IT_A_WATERMELON_SLICE;

	/**
	 * ID: 1800996<br>
	 * Message: ���������`�l�N�^�[���܂����I���������`�l�N�^�[���܂����I���̉́A�m��񂩁H
	 */
	public static final NpcStringId GIMME_NECTAR_IM_HUNGRY;

	/**
	 * ID: 1800998<br>
	 * Message: �l�N�^�[���ꂽ��ǂ�ǂ��ł��I
	 */
	public static final NpcStringId BRING_ME_NECTAR_THEN_ILL_DRINK_AND_GROW;

	/**
	 * ID: 1800999<br>
	 * Message: ���͂܂����n��˂�I�����ق�܂ɃC���`��Ȃ��`����������Ƒ҂Ă�I
	 */
	public static final NpcStringId YOU_WANNA_EAT_A_TINY_WATERMELON_LIKE_ME_TRY_GIVING_ME_SOME_NECTAR_ILL_GET_HUGE;

	/**
	 * ID: 1801000<br>
	 * Message: ���͂Ȃ��A�����啨�ɂȂ�˂�I�����ƈ�ĂĂ��ꂳ��������ȁB
	 */
	public static final NpcStringId HEHEHE_GROW_ME_WELL_AND_YOULL_GET_A_REWARD_GROW_ME_BAD_AND_WHO_KNOWS_WHATLL_HAPPEN;

	/**
	 * ID: 1801001<br>
	 * Message: �r�b�O�ȃX�C�J���ق����񂩁H���͏o�����Ȃ��ł������B
	 */
	public static final NpcStringId YOU_WANT_A_LARGE_WATERMELON_ID_LIKE_TO_BE_A_WATERMELON_SLICE;

	/**
	 * ID: 1801002<br>
	 * Message: ����M���ăl�N�^�[�����ꂽ�炦���˂�I�ق�Ȃ�A�r�b�O�ȃX�C�J�ɂȂ�����ł��I
	 */
	public static final NpcStringId TRUST_ME_AND_BRING_ME_SOME_NECTAR_ILL_BECOME_A_LARGE_WATERMELON_FOR_YOU;

	/**
	 * ID: 1801003<br>
	 * Message: ��͂�׃��X�͖��͂����ׂĉ񕜂����悤���ȁB�c���Ă���͍̂��Ղ������D�D�D
	 */
	public static final NpcStringId I_SEE_BELETH_HAS_RECOVERED_ALL_OF_ITS_MAGIC_POWER_WHAT_REMAINS_HERE_IS_JUST_ITS_TRACE;

	/**
	 * ID: 1801004<br>
	 * Message: �w���`�����l�� ���[�_�[ $s1�A�x���X�̃����O�𓾂܂����B
	 */
	public static final NpcStringId COMMAND_CHANNEL_LEADER_S1_BELETHS_RING_HAS_BEEN_ACQUIRED;

	/**
	 * ID: 1801005<br>
	 * Message: ����������������Ƃ͑����̎��M���ȁB���ꂶ��A�W���b�N �Q�[���s�����I
	 */
	public static final NpcStringId YOU_SUMMONED_ME_SO_YOU_MUST_BE_CONFIDENT_HUH_HERE_I_COME_JACK_GAME;

	/**
	 * ID: 1801006<br>
	 * Message: �ǂ����I�W���b�N �Q�[���A�y����ł��������ˁB
	 */
	public static final NpcStringId HELLO_LETS_HAVE_A_GOOD_JACK_GAME;

	/**
	 * ID: 1801007<br>
	 * Message: �n�߂邼�I���ꂶ��D���ȃJ�[�h���o���I
	 */
	public static final NpcStringId IM_STARTING_NOW_SHOW_ME_THE_CARD_YOU_WANT;

	/**
	 * ID: 1801008<br>
	 * Message: �n�߂܂���I���ꂶ��D���ȃJ�[�h���o���Ă��������B
	 */
	public static final NpcStringId WELL_START_NOW_SHOW_ME_THE_CARD_YOU_WANT;

	/**
	 * ID: 1801009<br>
	 * Message: �f�B�P�C�h �p���v�L�� �J�[�h�ł��o�������B
	 */
	public static final NpcStringId IM_SHOWING_THE_ROTTEN_PUMPKIN_CARD;

	/**
	 * ID: 1801010<br>
	 * Message: �f�B�P�C�h �p���v�L�� �J�[�h���o���܂��B
	 */
	public static final NpcStringId ILL_BE_SHOWING_THE_ROTTEN_PUMPKIN_CARD;

	/**
	 * ID: 1801011<br>
	 * Message: �W���b�N �p���v�L�� �J�[�h�ł��o�������B
	 */
	public static final NpcStringId IM_SHOWING_THE_JACK_PUMPKIN_CARD;

	/**
	 * ID: 1801012<br>
	 * Message: �W���b�N �p���v�L�� �J�[�h���o���܂��B
	 */
	public static final NpcStringId ILL_BE_SHOWING_THE_JACK_PUMPKIN_CARD;

	/**
	 * ID: 1801013<br>
	 * Message: �������A�������̑厖�ȃt�@���^�X�e�B�b�N �`���R�o�i�i �E���g�� �t���[�o�[ �L�����f�B���I���͂����͍s���Ȃ����I
	 */
	public static final NpcStringId THATS_MY_PRECIOUS_FANTASTIC_CHOCOLATE_BANANA_ULTRA_FAVOR_CANDY_IM_DEFINITELY_WINNING_THE_NEXT_ROUND;

	/**
	 * ID: 1801014<br>
	 * Message: �厖�ȃL�����f�B���D�D�D�ł������ł��B�����܂���B
	 */
	public static final NpcStringId ITS_MY_PRECIOUS_CANDY_BUT_ILL_HAPPILY_GIVE_IT_TO_YOU;

	/**
	 * ID: 1801015<br>
	 * Message: �L�����f�B���Ȃ��Ȃ����D�D�D�������I�������ᔠ������Ă���I
	 */
	public static final NpcStringId THE_CANDY_FELL_ILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1801016<br>
	 * Message: �L�����f�B���Ȃ��Ȃ����Ⴂ�܂����D�D�D���ꂶ��A����ɂ������ᔠ�������܂��傤�B
	 */
	public static final NpcStringId SINCE_THE_CANDY_FELL_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1801017<br>
	 * Message: �������̃J�[�h�A���Ă񂶂�˂���I���͂������X�N���[���������邼�I
	 */
	public static final NpcStringId YOURE_NOT_PEEKING_AT_MY_CARD_ARE_YOU_THIS_TIME_ILL_WAGER_A_SPECIAL_SCROLL;

	/**
	 * ID: 1801018<br>
	 * Message: �킭�킭���鏟���ł��ˁB������������ʂȃX�N���[���������܂��傤�B
	 */
	public static final NpcStringId WERE_GETTING_SERIOUS_NOW_IF_YOU_WIN_AGAIN_ILL_GIVE_YOU_A_SPECIAL_SCROLL;

	/**
	 * ID: 1801019<br>
	 * Message: �����������I�n�����E�̃v�����[�O�ł��ʂ���񂶂�˂��́H
	 */
	public static final NpcStringId YOU_COULD_PROBABLY_ENTER_THE_UNDERWORLD_PRO_LEAGUE;

	/**
	 * ID: 1801020<br>
	 * Message: �v���ł������܂ł͂ł��܂����B�������ł��ˁB
	 */
	public static final NpcStringId EVEN_PROS_CANT_DO_THIS_MUCH_YOURE_AMAZING;

	/**
	 * ID: 1801021<br>
	 * Message: �ǂ����������X�^�[����I���͂������̑厖�ȕϐg�X�e�B�b�N�������邼�I
	 */
	public static final NpcStringId WHOS_THE_MONSTER_HERE_THIS_TIME_ILL_BET_MY_PRECIOUS_TRANSFORMATION_STICK;

	/**
	 * ID: 1801022<br>
	 * Message: �܂����̕����ł��ˁD�D�D���͕����܂����I�ϐg�X�e�B�b�N�������܂��B
	 */
	public static final NpcStringId I_LOST_AGAIN_I_WONT_LOSE_THIS_TIME_IM_BETTING_MY_TRANSFORMATION_STICK;

	/**
	 * ID: 1801023<br>
	 * Message: �������I�܂��������I���͂������v���[���g�������邼�I���҂����I
	 */
	public static final NpcStringId LOST_AGAIN_HMPH_NEXT_TIME_ILL_BET_AN_INCREDIBLE_GIFT_WAIT_FOR_IT_IF_YOU_WANT;

	/**
	 * ID: 1801024<br>
	 * Message: �z���g�ɂ������ł��ˁB���͂��̂������v���[���g�������܂���B���҂��Ă��������ˁB
	 */
	public static final NpcStringId YOURE_TOO_GOOD_NEXT_TIME_ILL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_YOU;

	/**
	 * ID: 1801025<br>
	 * Message: ����ȏエ�O�ɏ�������̂͂������̃v���C�h�������˂��I
	 */
	public static final NpcStringId MY_PRIDE_CANT_HANDLE_YOU_WINNING_ANYMORE;

	/**
	 * ID: 1801026<br>
	 * Message: �����ŕ�������p���������Ȃ��D�D�D
	 */
	public static final NpcStringId I_WOULD_BE_EMBARRASSING_TO_LOSE_AGAIN_HERE;

	/**
	 * ID: 1801027<br>
	 * Message: ���O�͉����I�H�o���Ă������B
	 */
	public static final NpcStringId WHATS_YOUR_NAME_IM_GONNA_REMEMBER_YOU;

	/**
	 * ID: 1801028<br>
	 * Message: �n�㐢�E�̐l�̓Q�[�������܂��ł��˂��B
	 */
	public static final NpcStringId PEOPLE_FROM_THE_ABOVE_GROUND_WORLD_ARE_REALLY_GOOD_AT_GAMES;

	/**
	 * ID: 1801029<br>
	 * Message: �n�����E�ł�����������Ă񂾂�H
	 */
	public static final NpcStringId YOUVE_PLAYED_A_LOT_IN_THE_UNDERWORLD_HAVENT_YOU;

	/**
	 * ID: 1801030<br>
	 * Message: ����Ȃɂ��܂��l�͏��߂Ăł���B
	 */
	public static final NpcStringId IVE_NEVER_MET_SOMEONE_SO_GOOD_BEFORE;

	/**
	 * ID: 1801031<br>
	 * Message: 13�A������B�܂��ꂾ��A�܂���I
	 */
	public static final NpcStringId N13_WINS_IN_A_ROW_YOURE_PRETTY_LUCKY_TODAY_HUH;

	/**
	 * ID: 1801032<br>
	 * Message: 13�A�����Ȃ�āA�z�������܂���ł�����B
	 */
	public static final NpcStringId I_NEVER_THOUGHT_I_WOULD_SEE_13_WINS_IN_A_ROW;

	/**
	 * ID: 1801033<br>
	 * Message: ����Ȃ̂͐��܂�ď��߂Ă���I���͂������̕󕨁A�S�[���f�� �W���b�N �I �����^���������邼�I
	 */
	public static final NpcStringId THIS_IS_THE_HIGHEST_RECORD_IN_MY_LIFE_NEXT_TIME_ILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN;

	/**
	 * ID: 1801034<br>
	 * Message: �v���ł����14�����I���͎��̕󕨁A�S�[���f�� �W���b�N �I �����^���������܂���I
	 */
	public static final NpcStringId EVEN_PROS_CANT_DO_14_WINS_NEXT_TIME_I_WILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN;

	/**
	 * ID: 1801035<br>
	 * Message: �������߂��I�~�Q���I��������583�N�̐l���ł��O���ō�����I
	 */
	public static final NpcStringId I_CANT_DO_THIS_ANYMORE_YOU_WIN_I_ACKNOWLEDGE_YOU_AS_THE_BEST_IVE_EVER_MET_IN_ALL_MY_583_YEARS;

	/**
	 * ID: 1801036<br>
	 * Message: ����ȏ����Ă��Ӗ�������܂���ˁB�ō��̑���ł�����B
	 */
	public static final NpcStringId PLAYING_ANY_MORE_IS_MEANINGLESS_YOU_WERE_MY_GREATEST_OPPONENT;

	/**
	 * ID: 1801037<br>
	 * Message: ���x�͂������̏������ȁB�������납�������B
	 */
	public static final NpcStringId I_WON_THIS_ROUND_IT_WAS_FUN;

	/**
	 * ID: 1801038<br>
	 * Message: ���x�͎��̏����ł��ˁB�y���������ł��B
	 */
	public static final NpcStringId I_WON_THIS_ROUND_IT_WAS_ENJOYABLE;

	/**
	 * ID: 1801039<br>
	 * Message: ����ϒn��l�͂������ꂦ�Ȃ��I���ꂶ��ȁI
	 */
	public static final NpcStringId ABOVE_WORLD_PEOPLE_ARE_SO_FUN_THEN_SEE_YOU_LATER;

	/**
	 * ID: 1801040<br>
	 * Message: �܂��Ă�ł��������ˁB���Ȃ��Ƃ͂܂���肽���ł��ˁB
	 */
	public static final NpcStringId CALL_ME_AGAIN_NEXT_TIME_I_WANT_TO_PLAY_AGAIN_WITH_YOU;

	/**
	 * ID: 1801041<br>
	 * Message: �Q�[���A�����Ƃ�邩�B�v���[���g�͂����Ȃ�����A�L�����f�B�����B
	 */
	public static final NpcStringId YOU_WANNA_PLAY_SOME_MORE_IM_OUT_OF_PRESENTS_BUT_ILL_GIVE_YOU_CANDY;

	/**
	 * ID: 1801042<br>
	 * Message: �Q�[���A�����Ƃ��܂����B�v���[���g�͂����Ȃ����ǁA��������L�����f�B�������܂��傤�B
	 */
	public static final NpcStringId WILL_YOU_PLAY_SOME_MORE_I_DONT_HAVE_ANY_MORE_PRESENTS_BUT_I_WILL_GIVE_YOU_CANDY_IF_YOU_WIN;

	/**
	 * ID: 1801043<br>
	 * Message: ���O�͍��܂ł̃W���b�N �Q�[�� �v���[���[�̒��ōō����I��߂��A��߂��I
	 */
	public static final NpcStringId YOURE_THE_BEST_OUT_OF_ALL_THE_JACKS_GAME_PLAYERS_IVE_EVER_MET_I_GIVE_UP;

	/**
	 * ID: 1801044<br>
	 * Message: �킠���A�����������I����Ȃɂ������l�͏��߂Ăł���B������`�߂��I
	 */
	public static final NpcStringId WOWWW_AWESOME_REALLY_I_HAVE_NEVER_MET_SOMEONE_AS_GOOD_AS_YOU_BEFORE_NOW_I_CANT_PLAY_ANYMORE;

	/**
	 * ID: 1801045<br>
	 * Message: $s1�l�̓W���b�N �Q�[���� $s2�A���B���ł��I
	 */
	public static final NpcStringId S1_HAS_WON_S2_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1801046<br>
	 * Message: ���߂łƂ��������܂��I$s1�l�̓W���b�N �Q�[���� $s2�A���B���ł��I
	 */
	public static final NpcStringId CONGRATULATIONS_S1_HAS_WON_S2_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1801047<br>
	 * Message: �W���b�N �Q�[��1�ʁA���߂łƂ��������܂��I
	 */
	public static final NpcStringId CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACKS_GAME;

	/**
	 * ID: 1801048<br>
	 * Message: �ǂ��ǂ��A�x���_�ł��B�W���b�N�@�Q�[��1�ʁA���߂łƂ��������܂��I���ɂ���N���h����Ռ��I�̃v���[���g�����炦�܂���B���ꂶ��A�܂��Q�[�����ɂ��Ă��������ˁB
	 */
	public static final NpcStringId HELLO_IM_BELLDANDY_CONGRATULATIONS_ON_WINNING_1ST_PLACE_IN_JACKS_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOULL_GET_AN_AMAZING_GIFT_LETS_PLAY_JACKS_GAME_AGAIN;

	/**
	 * ID: 1801049<br>
	 * Message: ���O�A�W���b�N �Q�[�����߂Ă���H�J�[�h�o���^�C�~���O���킩��˂��݂��������ȁB
	 */
	public static final NpcStringId HMM_YOURE_PLAYING_JACKS_GAME_FOR_THE_FIRST_TIME_HUH_YOU_COULDNT_EVEN_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME_MY_GOODNESS;

	/**
	 * ID: 1801050<br>
	 * Message: �W���b�N �Q�[�����܂��悭�킩���ĂȂ��݂����ł��ˁB�J�[�h�̓^�C�~���O�����킹�ďo���Ȃ���`
	 */
	public static final NpcStringId OH_YOURE_NOT_VERY_FAMILIAR_WITH_JACKS_GAME_RIGHT_YOU_DIDNT_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME;

	/**
	 * ID: 1801051<br>
	 * Message: �������̓��̏�̃Q�[�W��0�ɂȂ�O�Ƀ}�X�N�ɕt���Ă�J�[�h �X�L�����g��Ȃ���Ȃ�˂��񂾂�B
	 */
	public static final NpcStringId YOU_HAVE_TO_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;

	/**
	 * ID: 1801052<br>
	 * Message: ���̓��̏�̃Q�[�W��0�ɂȂ�O�Ƀ}�X�N�ɕt���Ă�J�[�h �X�L�����g��Ȃ���Ȃ�Ȃ���ł���B
	 */
	public static final NpcStringId YOU_MUST_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;

	/**
	 * ID: 1801053<br>
	 * Message: �������Ɠ����J�[�h���o�����炨�O�̏������B����Ă��炠�����̏������B�킩������H����A�����؂�s�����I
	 */
	public static final NpcStringId IF_YOU_SHOW_THE_SAME_CARD_AS_ME_YOU_WIN_IF_THEYRE_DIFFERENT_I_WIN_UNDERSTAND_NOW_LETS_GO;

	/**
	 * ID: 1801054<br>
	 * Message: ���Ɠ����J�[�h���o�����炠�Ȃ��̏����ł��B����Ă��玄�̏����ł��B���ꂶ��A�������s���܂���I
	 */
	public static final NpcStringId YOU_WILL_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_ITS_MY_VICTORY_IF_THE_CARDS_ARE_DIFFERENT_WELL_LETS_START_AGAIN;

	/**
	 * ID: 1801055<br>
	 * Message: �����I�J�[�h�o���Ă˂������I�Q�[�W��0�ɂȂ�O�ɃJ�[�h �X�L�����g��Ȃ���Ȃ�˂��񂾂�B����A�s�����I
	 */
	public static final NpcStringId ACK_YOU_DIDNT_SHOW_A_CARD_YOU_HAVE_TO_USE_THE_CARD_SKILL_BEFORE_THE_GAUGE_DISAPPEARS_HMPH_THEN_IM_GOING;

	/**
	 * ID: 1801056<br>
	 * Message: �������A�J�[�h�o���Ă܂���ˁB�J�[�h �X�L���̓^�C�~���O�����킹�Ďg��Ȃ���`�c�O�I���ꂶ��A�s���܂���I
	 */
	public static final NpcStringId AHH_YOU_DIDNT_SHOW_A_CARD_YOU_MUST_USE_THE_CARD_SKILL_AT_THE_RIGHT_TIME_ITS_UNFORTUNATE_THEN_I_WILL_GO_NOW;

	/**
	 * ID: 1801057<br>
	 * Message: ���������W���b�N �Q�[���̂��ƁA�����Ă�邺�I��������A�Q�[����3��ł���񂾂��I
	 */
	public static final NpcStringId LETS_LEARN_ABOUT_THE_JACKS_GAME_TOGETHER_YOU_CAN_PLAY_WITH_ME_3_TIMES;

	/**
	 * ID: 1801058<br>
	 * Message: �s�����I�D���ȃJ�[�h���o���I�J�[�h �X�L���̓}�X�N�ɕt���Ă邼�B
	 */
	public static final NpcStringId LETS_START_SHOW_THE_CARD_YOU_WANT_THE_CARD_SKILL_IS_ATTACHED_TO_THE_MASK;

	/**
	 * ID: 1801059<br>
	 * Message: �������Ɠ����J�[�h���o�����炨�O�̏������ȁB
	 */
	public static final NpcStringId YOU_SHOWED_THE_SAME_CARD_AS_ME_SO_YOU_WIN;

	/**
	 * ID: 1801060<br>
	 * Message: �������ƈႤ�J�[�h���o�������炨�O�̕������B
	 */
	public static final NpcStringId YOU_SHOWED_A_DIFFERENT_CARD_FROM_ME_SO_YOU_LOSE;

	/**
	 * ID: 1801061<br>
	 * Message: ���K�����甃���Ă��L�����f�B�͂��˂����I
	 */
	public static final NpcStringId THAT_WAS_PRACTICE_SO_THERES_NO_CANDY_EVEN_IF_YOU_WIN;

	/**
	 * ID: 1801062<br>
	 * Message: �c�O�B�����؂���K���悤���B
	 */
	public static final NpcStringId ITS_UNFORTUNATE_LETS_PRACTICE_ONE_MORE_TIME;

	/**
	 * ID: 1801063<br>
	 * Message: �J�[�h���o���^�C�~���O�ɋC������B�������̓��̏�̃Q�[�W��0�ɂȂ�O�ɃJ�[�h�@�X�L�����g�����Ⴂ�ȁI
	 */
	public static final NpcStringId YOU_GOTTA_SHOW_THE_CARD_AT_THE_RIGHT_TIME_USE_THE_CARD_SKILL_YOU_WANT_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;

	/**
	 * ID: 1801064<br>
	 * Message: �W���b�N �I �����^�� �}�X�N�ɃJ�[�h �X�L�������Ă邾��H������g���ȁB
	 */
	public static final NpcStringId THE_CARD_SKILLS_ARE_ATTACHED_TO_THE_JACK_OLANTERN_MASK_RIGHT_THATS_WHAT_YOU_USE;

	/**
	 * ID: 1801065<br>
	 * Message: �������Ɠ����J�[�h���o���΂��O�̏������B�������ƈႤ�J�[�h���o���΂������̏������B���ꂶ��A�����؂�s�����I
	 */
	public static final NpcStringId YOU_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_AND_I_WIN_IF_THE_CARDS_ARE_DIFFERENT_OK_LETS_GO;

	/**
	 * ID: 1801066<br>
	 * Message: �܂��J�[�h�o���Ă˂������I���������A�܂������낤���B���ꂶ��ȁB
	 */
	public static final NpcStringId YOU_DIDNT_SHOW_A_CARD_AGAIN_WELL_TRY_AGAIN_LATER_IM_GONNA_GO_NOW;

	/**
	 * ID: 1801067<br>
	 * Message: �W���b�N �Q�[���̂��Ƃ�������Ƃ킩�������ȁB�{�Ԃ̓E���ƃx���_�Ƃ��񂾁B���ꂶ��ȁI
	 */
	public static final NpcStringId NOW_DO_YOU_UNDERSTAND_A_LITTLE_ABOUT_JACKS_GAME_THE_REAL_GAMES_WITH_ULDIE_AND_BELLDANDY_WELL_SEE_YOU_LATER;

	/**
	 * ID: 1801068<br>
	 * Message: �͂͂͂��B
	 */
	public static final NpcStringId HAHAHAHA;

	/**
	 * ID: 1801069<br>
	 * Message: �ǂ������Ă�񂾁H
	 */
	public static final NpcStringId WHERE_ARE_YOU_LOOKING;

	/**
	 * ID: 1801070<br>
	 * Message: ���͂����ɂ���B
	 */
	public static final NpcStringId IM_RIGHT_HERE;

	/**
	 * ID: 1801071<br>
	 * Message: ���ߋ�������̏W���U���Ń��@���J�X�͏W���ł��Ȃ����낤�B\n�����čU������΃`�����X�����߂�͂����I
	 */
	public static final NpcStringId ANNOYING_CONCENTRATION_ATTACKS_ARE_DISRUPTING_VALAKASS_CONCENTRATIONNIF_IT_CONTINUES_YOU_MAY_GET_A_GREAT_OPPORTUNITY;

	/**
	 * ID: 1801072<br>
	 * Message: �Ƃ���E�m�̈ꌂ�����@���J�X�̕������؂��������B\n���@���J�X�̖h��͂��啝�ɗ����Ă��邼�I
	 */
	public static final NpcStringId SOME_WARRIORS_BLOW_HAS_LEFT_A_HUGE_GASH_BETWEEN_THE_GREAT_SCALES_OF_VALAKASNVALAKASS_P_DEF_IS_GREATLY_DECREASED;

	/**
	 * ID: 1801073<br>
	 * Message: ���ߋ�������̏W���U���ɂ��낽�������@���J�X�͌��������Ă��邼�I
	 */
	public static final NpcStringId ANNOYING_CONCENTRATION_ATTACKS_OVERWHELMED_VALAKAS_MAKING_IT_FORGET_ITS_RAGE_AND_BECOME_DISTRACTED;

	/**
	 * ID: 1801074<br>
	 * Message: ����������̏W���U���Ń��@���J�X�͓��ɗ��Ă���B\n�����čU������Ί�@�ɕm���邩������Ȃ��I
	 */
	public static final NpcStringId LONG_RANGE_CONCENTRATION_ATTACKS_HAVE_ENRAGED_VALAKASNIF_YOU_CONTINUE_IT_MAY_BECOME_A_DANGEROUS_SITUATION;

	/**
	 * ID: 1801075<br>
	 * Message: �ڋ��Ȕ����������ă��@���J�X�̓{�肪���_�ɒB�����B\n���@���J�X�̍U���͂��啝�ɏオ�������I
	 */
	public static final NpcStringId BECAUSE_THE_COWARDLY_COUNTERATTACKS_CONTINUED_VALAKASS_FURY_HAS_REACHED_ITS_MAXIMUMNVALAKASS_P_ATK_IS_GREATLY_INCREASED;

	/**
	 * ID: 1801076<br>
	 * Message: ��������������̏W���U���ɓ{�������@���J�X�A�ڕW���������U�����Ă��邼�I
	 */
	public static final NpcStringId VALAKAS_HAS_BEEN_ENRAGED_BY_THE_LONG_RANGE_CONCENTRATION_ATTACKS_AND_IS_COMING_TOWARD_ITS_TARGET_WITH_EVEN_GREATER_ZEAL;

	/**
	 * ID: 1801077<br>
	 * Message: �^���^�ǂ���A�����I�����߂��Ă����I�����[���̗a���҃��S���X������B�����łȂ��I
	 */
	public static final NpcStringId LISTEN_OH_TANTAS_I_HAVE_RETURNED_THE_PROPHET_YUGOROS_OF_THE_BLACK_ABYSS_IS_WITH_ME_SO_DO_NOT_BE_AFRAID;

	/**
	 * ID: 1801078<br>
	 * Message: �悭�����ȁA$s1�I�����[���ɂ�������ɒl���邢���ɂ������Ă݂悤�ł͂Ȃ����I
	 */
	public static final NpcStringId WELCOME_S1_LET_US_SEE_IF_YOU_HAVE_BROUGHT_A_WORTHY_OFFERING_FOR_THE_BLACK_ABYSS;

	/**
	 * ID: 1801079<br>
	 * Message: ����ɂƂ��ĕs���͂Ȃ��I�������A���ɂ͍����[������������������A�[����������I
	 */
	public static final NpcStringId WHAT_A_FORMIDABLE_FOE_BUT_I_HAVE_THE_ABYSS_WEED_GIVEN_TO_ME_BY_THE_BLACK_ABYSS_LET_ME_SEE;

	/**
	 * ID: 1801080<br>
	 * Message: �ނ͂͂́I���̌��̒��̂҂���Ƃ��������I�����[���������������͂����𐶂��Ԃ点��̂��I
	 */
	public static final NpcStringId MUHAHAHA_AH_THIS_BURNING_SENSATION_IN_MY_MOUTH_THE_POWER_OF_THE_BLACK_ABYSS_IS_REVIVING_ME;

	/**
	 * ID: 1801081<br>
	 * Message: �Ȃ�Ă��Ƃ��I�[�������g���Ȃ�����Ƃ́D�D�D���ꂪ�����ɕs�h�ł��邩�킩���Ă���̂��I
	 */
	public static final NpcStringId NO_HOW_DARE_YOU_STOP_ME_FROM_USING_THE_ABYSS_WEED_DO_YOU_KNOW_WHAT_YOU_HAVE_DONE;

	/**
	 * ID: 1801082<br>
	 * Message: ����Ȃ����тꂽ����A�����[���ɂ����ɂ��Ƃ��ĕ�����킯�ɂ͂����Ȃ��I
	 */
	public static final NpcStringId A_LIMP_CREATURE_LIKE_THIS_IS_UNWORTHY_TO_BE_AN_OFFERING_YOU_HAVE_NO_RIGHT_TO_SACRIFICE_TO_THE_BLACK_ABYSS;

	/**
	 * ID: 1801083<br>
	 * Message: �^���^�ǂ���A�����I�����[�����Q���Ă���������I�V�N�Ȃ����ɂ��������I
	 */
	public static final NpcStringId LISTEN_OH_TANTAS_THE_BLACK_ABYSS_IS_FAMISHED_FIND_SOME_FRESH_OFFERINGS;

	/**
	 * ID: 1801084<br>
	 * Message: ���A���̎����s�k����Ƃ́I�����[����A�����󂯓��ꂽ�܂��D�D�D
	 */
	public static final NpcStringId AH_HOW_COULD_I_LOSE_OH_BLACK_ABYSS_RECEIVE_ME;

	/**
	 * ID: 1801085<br>
	 * Message: �x�񑕒u�j��B�N���Ҕr���B
	 */
	public static final NpcStringId ALARM_SYSTEM_DESTROYED_INTRUDER_EXCLUDED;

	/**
	 * ID: 1801089<br>
	 * Message: 4�i�K�X�^�[�g
	 */
	public static final NpcStringId BEGIN_STAGE_4;

	/**
	 * ID: 1801090<br>
	 * Message: ���̐퓬�܂ł̎c�莞��
	 */
	public static final NpcStringId TIME_REMAINING_UNTIL_NEXT_BATTLE;

	/**
	 * ID: 1801091<br>
	 * Message: �ҏb�͎�����H�ׂ܂����B�����������ς��ɂȂ����̂��A���̔����������܂���B
	 */
	public static final NpcStringId THE_BEAST_ATE_THE_FEED_BUT_IT_ISNT_SHOWING_A_RESPONSE_PERHAPS_BECAUSE_ITS_ALREADY_FULL;

	/**
	 * ID: 1801092<br>
	 * Message: �ҏb�͎�����H�ׂ��ɓf���o���܂��B
	 */
	public static final NpcStringId THE_BEAST_SPIT_OUT_THE_FEED_INSTEAD_OF_EATING_IT;

	/**
	 * ID: 1801093<br>
	 * Message: �ҏb�͎�����H�ׂ��ɂ��Ȃ����U�����܂��B
	 */
	public static final NpcStringId THE_BEAST_SPIT_OUT_THE_FEED_AND_IS_INSTEAD_ATTACKING_YOU;

	/**
	 * ID: 1801094<br>
	 * Message: �S�[���f�� �X�p�C�X������Ȃ����߁A$s1�����Ȃ��̂��Ƃ�����܂��B
	 */
	public static final NpcStringId S1_IS_LEAVING_YOU_BECAUSE_YOU_DONT_HAVE_ENOUGH_GOLDEN_SPICES;

	/**
	 * ID: 1801095<br>
	 * Message: �N���X�^�� �X�p�C�X������Ȃ����߁A$s1�����Ȃ��̂��Ƃ�����܂��B
	 */
	public static final NpcStringId S1_IS_LEAVING_YOU_BECAUSE_YOU_DONT_HAVE_ENOUGH_CRYSTAL_SPICES;

	/**
	 * ID: 1801096<br>
	 * Message: $s1�l�I�_�̂�����̂���񂱂Ƃ��I
	 */
	public static final NpcStringId S1_MAY_THE_PROTECTION_OF_THE_GODS_BE_UPON_YOU;

	/**
	 * ID: 1801097<br>
	 * Message: �t�����������o���܂����B
	 */
	public static final NpcStringId FREYA_HAS_STARTED_TO_MOVE;

	/**
	 * ID: 1801098<br>
	 * Message: �����D�D�D ����ȂƂ���Ŏ��ʂƂ́D�D�D
	 */
	public static final NpcStringId HOW_COULD_I_FALL_IN_A_PLACE_LIKE_THIS;

	/**
	 * ID: 1801099<br>
	 * Message: �悤�₭�ꑧ���������B���Ȃ��́D�D�D�N�����킵���̂���̂̌����͂����D�D�D
	 */
	public static final NpcStringId I_CAN_FINALLY_TAKE_A_BREATHER_BY_THE_WAY_WHO_ARE_YOU_HMM_I_THINK_I_KNOW_WHO_SENT_YOU;

	/**
	 * ID: 1801100<br>
	 * Message: �o�����X��$s1
	 */
	public static final NpcStringId S1_OF_BALANCE;

	/**
	 * ID: 1801101<br>
	 * Message: �q����$s1
	 */
	public static final NpcStringId SWIFT_S2;

	/**
	 * ID: 1801102<br>
	 * Message: �j����$s1
	 */
	public static final NpcStringId S1_OF_BLESSING;

	/**
	 * ID: 1801103<br>
	 * Message: �s��$s1
	 */
	public static final NpcStringId SHARP_S2;

	/**
	 * ID: 1801104<br>
	 * Message: �g���ł̂���$s1
	 */
	public static final NpcStringId USEFUL_S2;

	/**
	 * ID: 1801105<br>
	 * Message: ���\��$s1
	 */
	public static final NpcStringId RECKLESS_S2;

	/**
	 * ID: 1801106<br>
	 * Message: �A���p�C�� �N�b�J�u��
	 */
	public static final NpcStringId ALPEN_KOOKABURRA;

	/**
	 * ID: 1801107<br>
	 * Message: �A���p�C�� �N�[�K�[
	 */
	public static final NpcStringId ALPEN_COUGAR;

	/**
	 * ID: 1801108<br>
	 * Message: �A���p�C�� �o�b�t�@���[
	 */
	public static final NpcStringId ALPEN_BUFFALO;

	/**
	 * ID: 1801109<br>
	 * Message: �A���p�C�� �O�����f��
	 */
	public static final NpcStringId ALPEN_GRENDEL;

	/**
	 * ID: 1801110<br>
	 * Message: �퓬�I����������
	 */
	public static final NpcStringId BATTLE_END_LIMIT_TIME;

	/**
	 * ID: 1801111<br>
	 * Message: �ǂ����炩�������͂��������܂��B
	 */
	public static final NpcStringId STRONG_MAGIC_POWER_CAN_BE_FELT_FROM_SOMEWHERE;

	/**
	 * ID: 1801112<br>
	 * Message: ���̌P�������U������Ƃ́I
	 */
	public static final NpcStringId HOW_DARE_YOU_ATTACK_MY_RECRUITS;

	/**
	 * ID: 1801113<br>
	 * Message: �����𗐂��̂͒N���I
	 */
	public static final NpcStringId WHO_IS_DISRUPTING_THE_ORDER;

	/**
	 * ID: 1801114<br>
	 * Message: ����������ł��܂����I
	 */
	public static final NpcStringId THE_DRILLMASTER_IS_DEAD;

	/**
	 * ID: 1801115<br>
	 * Message: ����𐮂���I
	 */
	public static final NpcStringId LINE_UP_THE_RANKS;

	/**
	 * ID: 1801116<br>
	 * Message: �ق�A���V���B
	 */
	public static final NpcStringId I_BROUGHT_THE_FOOD;

	/**
	 * ID: 1801117<br>
	 * Message: �������ƃ��V�H����B
	 */
	public static final NpcStringId COME_AND_EAT;

	/**
	 * ID: 1801118<br>
	 * Message: �킠�A�����������I
	 */
	public static final NpcStringId LOOKS_DELICIOUS;

	/**
	 * ID: 1801119<br>
	 * Message: ���V�H���ɍs�������B
	 */
	public static final NpcStringId LETS_GO_EAT;

	/**
	 * ID: 1801120<br>
	 * Message: �X�{��I�N���҂ɂ��܂��̑������I
	 */
	public static final NpcStringId ARCHER_GIVE_YOUR_BREATH_FOR_THE_INTRUDER;

	/**
	 * ID: 1801121<br>
	 * Message: ���̋R�m������I�����S�������I
	 */
	public static final NpcStringId MY_KNIGHTS_SHOW_YOUR_LOYALTY;

	/**
	 * ID: 1801122<br>
	 * Message: ���͂₪�܂�Ȃ�ʁI
	 */
	public static final NpcStringId I_CAN_TAKE_IT_NO_LONGER;

	/**
	 * ID: 1801123<br>
	 * Message: �X�{��I���̌Ăъ|���ɓ�����I
	 */
	public static final NpcStringId ARCHER_HEED_MY_CALL;

	/**
	 * ID: 1801124<br>
	 * Message: ��Ԃ����X�ɗh�ꂾ���̂������܂��B
	 */
	public static final NpcStringId THE_SPACE_FEELS_LIKE_ITS_GRADUALLY_STARTING_TO_SHAKE;

	/**
	 * ID: 1801125<br>
	 * Message: ���͂�ŉ߂ł���B
	 */
	public static final NpcStringId I_CAN_NO_LONGER_STAND_BY;

	/**
	 * ID: 1801126<br>
	 * Message: �^�N���J�����͂��W�߂čU�����J�n���܂��B
	 */
	public static final NpcStringId TAKLACAN_IS_GATHERING_ITS_STRENGTH_AND_LAUNCHING_AN_ATTACK;

	/**
	 * ID: 1801127<br>
	 * Message: �^�N���J�����R�N���R���̗͂��܂Ƃ��Ɏ󂯂Ď�܂�܂��B
	 */
	public static final NpcStringId TAKLACAN_RECEIVED_COKRAKON_AND_BECAME_WEAKER;

	/**
	 * ID: 1801128<br>
	 * Message: ���͂ɂقƂ΂���R�N���R���̗́B
	 */
	public static final NpcStringId COKRAKONS_POWER_CAN_BE_FELT_NEARBY;

	/**
	 * ID: 1801129<br>
	 * Message: �p���B������������^�N���J���B
	 */
	public static final NpcStringId TAKLACAN_IS_PREPARING_TO_HIDE_ITSELF;

	/**
	 * ID: 1801130<br>
	 * Message: �Y���т��c���ċ󋕂̋�ԂւƎp�������^�N���J���B
	 */
	public static final NpcStringId TAKLACAN_DISAPPEARS_INTO_THE_SPACE_OF_FUTILITY_WITH_A_ROAR;

	/**
	 * ID: 1801131<br>
	 * Message: �g�����o�������o���܂����B
	 */
	public static final NpcStringId TORUMBAS_BODY_IS_STARTING_TO_MOVE;

	/**
	 * ID: 1801132<br>
	 * Message: �g�����o�̏�v�Ȕ畆���甽�����������܂��B
	 */
	public static final NpcStringId A_RESPONSE_CAN_BE_FELT_FROM_TORUMBAS_TOUGH_SKIN;

	/**
	 * ID: 1801133<br>
	 * Message: �g�����o�̏�v�Ȕ畆�ɐՂ��c��܂��B
	 */
	public static final NpcStringId A_MARK_REMAINS_ON_TORUMBAS_TOUGH_SKIN;

	/**
	 * ID: 1801134<br>
	 * Message: �g�����o�̊�����キ�Ȃ�܂��B
	 */
	public static final NpcStringId THE_LIGHT_IS_BEGINNING_TO_WEAKEN_IN_TORUMBAS_EYES;

	/**
	 * ID: 1801135<br>
	 * Message: �g�����o�͍����ɏ��𕉂��܂����B
	 */
	public static final NpcStringId TORUMBAS_LEFT_LEG_WAS_DAMAGED;

	/**
	 * ID: 1801136<br>
	 * Message: �g�����o�͉E���ɏ��𕉂��܂����B
	 */
	public static final NpcStringId TORUMBAS_RIGHT_LEG_WAS_DAMAGED;

	/**
	 * ID: 1801137<br>
	 * Message: �g�����o�͍��r�ɏ��𕉂��܂����B
	 */
	public static final NpcStringId TORUMBAS_LEFT_ARM_WAS_DAMAGED;

	/**
	 * ID: 1801138<br>
	 * Message: �g�����o�͉E�r�ɏ��𕉂��܂����B
	 */
	public static final NpcStringId TORUMBAS_RIGHT_ARM_WAS_DAMAGED;

	/**
	 * ID: 1801139<br>
	 * Message: �g�����o�̏�v�Ȕ畆�ɐ[�������t���܂����B
	 */
	public static final NpcStringId A_DEEP_WOUND_APPEARED_ON_TORUMBAS_TOUGH_SKIN;

	/**
	 * ID: 1801140<br>
	 * Message: �g�����o�̊�����������肻���ł��B
	 */
	public static final NpcStringId THE_LIGHT_IS_SLOWLY_FADING_FROM_TORUMBAS_EYES;

	/**
	 * ID: 1801141<br>
	 * Message: �g�����o�͐g���B�����Ƃ��Ă��܂��B
	 */
	public static final NpcStringId TORUMBA_IS_PREPARING_TO_HIDE_ITS_BODY;

	/**
	 * ID: 1801142<br>
	 * Message: �g�����o�͎���̋�Ԃɓ����Ďp�������܂����B
	 */
	public static final NpcStringId TORUMBA_DISAPPEARED_INTO_HIS_SPACE;

	/**
	 * ID: 1801143<br>
	 * Message: �g�����o���c�񂾋�Ԃɐg���B�����Ƃ��Ă��܂��B
	 */
	public static final NpcStringId TORUMBA_IS_PREPARING_TO_HIDE_ITSELF_IN_THE_TWISTED_SPACE;

	/**
	 * ID: 1801144<br>
	 * Message: �h�[�p�Q���������o���܂����B
	 */
	public static final NpcStringId DOPAGEN_HAS_STARTED_TO_MOVE;

	/**
	 * ID: 1801145<br>
	 * Message: ���v�e�B���R���̋C���Ïk������邱�Ƃ��������܂��B
	 */
	public static final NpcStringId LEPTILIKONS_ENERGY_FEELS_LIKE_ITS_BEING_CONDENSED;

	/**
	 * ID: 1801146<br>
	 * Message: �h�[�p�Q�����p���B�������������Ă��܂��B
	 */
	public static final NpcStringId DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF_WITH_A_STRANGE_SCENT;

	/**
	 * ID: 1801147<br>
	 * Message: �p���B������������h�[�p�Q���B
	 */
	public static final NpcStringId DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF;

	/**
	 * ID: 1801148<br>
	 * Message: �h�[�p�Q���͘c�񂾋�Ԃ̊ԂւƎp�������܂����B
	 */
	public static final NpcStringId DOPAGEN_IS_DISAPPEARING_IN_BETWEEN_THE_TWISTED_SPACE;

	/**
	 * ID: 1801149<br>
	 * Message: �}�O�G�����P�I
	 */
	public static final NpcStringId MAGUEN_APPEARANCE;

	/**
	 * ID: 1801150<br>
	 * Message: �}�O�G�� �v���Y�}�F�r�X�^�R�����[���ɂ��܂�܂����B
	 */
	public static final NpcStringId ENOUGH_MAGUEN_PLASMA_BISTAKON_HAVE_GATHERED;

	/**
	 * ID: 1801151<br>
	 * Message: �}�O�G�� �v���Y�}�F�R�N���R�����[���ɂ��܂�܂����B
	 */
	public static final NpcStringId ENOUGH_MAGUEN_PLASMA_COKRAKON_HAVE_GATHERED;

	/**
	 * ID: 1801152<br>
	 * Message: �}�O�G�� �v���Y�}�F���v�e�B���R�����[���ɂ��܂�܂����B
	 */
	public static final NpcStringId ENOUGH_MAGUEN_PLASMA_LEPTILIKON_HAVE_GATHERED;

	/**
	 * ID: 1801153<br>
	 * Message: ���W�@�����ς��̃v���Y�}�����a���Ȃ��č����荇���܂��B
	 */
	public static final NpcStringId THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_AND_ARE_HARMONIZED;

	/**
	 * ID: 1801154<br>
	 * Message: �v���Y�}���W�@�͂����ς��ɂȂ�܂������A�v���Y�}���m�ŏՓˁA�������āA���ł��܂��B
	 */
	public static final NpcStringId THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_BUT_THEY_ARE_RAMMING_INTO_EACH_OTHER_EXPLODING_AND_DYING;

	/**
	 * ID: 1801155<br>
	 * Message: �X�b�Q�[�I$s1���\�E���X�g�[���̌��Ђ�100�������Ă����₪�����B�h���{�[�����A�h���{�[�B
	 */
	public static final NpcStringId AMAZING_S1_TOOK_100_OF_THESE_SOUL_STONE_FRAGMENTS_WHAT_A_COMPLETE_SWINDLER;

	/**
	 * ID: 1801156<br>
	 * Message: ����$s1�A����������̂��B1��������債�Đɂ������Ȃ����낤�B�A�n�n�I
	 */
	public static final NpcStringId HMM_HEY_DID_YOU_GIVE_S1_SOMETHING_BUT_IT_WAS_JUST_1_HAHA;

	/**
	 * ID: 1801157<br>
	 * Message: $s1������$s2�̂�����������I���b�L�[�I
	 */
	public static final NpcStringId S1_PULLED_ONE_WITH_S2_DIGITS_LUCKY_NOT_BAD;

	/**
	 * ID: 1801158<br>
	 * Message: �X�b�J���J�����̓}�V����H�����ƋC���������āH
	 */
	public static final NpcStringId ITS_BETTER_THAN_LOSING_IT_ALL_RIGHT_OR_DOES_THIS_FEEL_WORSE;

	/**
	 * ID: 1801159<br>
	 * Message: �����܂��A$s1�̓z���g�Ƀc�C�ĂȂ��˂��B���������_���݂����Ȃ��ȁB
	 */
	public static final NpcStringId AHEM_S1_HAS_NO_LUCK_AT_ALL_TRY_PRAYING;

	/**
	 * ID: 1801160<br>
	 * Message: �����A�����I��肾�I�������A$s1�I����ł������čs���₪��I
	 */
	public static final NpcStringId AH_ITS_OVER_WHAT_KIND_OF_GUY_IS_THAT_DAMN_FINE_YOU_S1_TAKE_IT_AND_GET_OUTTA_HERE;

	/**
	 * ID: 1801161<br>
	 * Message: �X�J���Ȃ���Α哖������Ȃ��B�ق�A�X�J���B
	 */
	public static final NpcStringId A_BIG_PIECE_IS_MADE_UP_OF_LITTLE_PIECES_SO_HERES_A_LITTLE_PIECE;

	/**
	 * ID: 1801162<br>
	 * Message: ���ꂮ�炢�������炢������H���₾���āH�����A�����Ȃ�`
	 */
	public static final NpcStringId YOU_DONT_FEEL_BAD_RIGHT_ARE_YOU_SAD_BUT_DONT_CRY;

	/**
	 * ID: 1801163<br>
	 * Message: �����A���͒N���H�ǂ����Z���l�����B�^���߂��Ǝv���Ă���Ă݂�B�܂��A�Ƃ��������������ł����Ă݂Ȃ�B
	 */
	public static final NpcStringId OK_WHOS_NEXT_IT_ALL_DEPENDS_ON_YOUR_FATE_AND_LUCK_RIGHT_AT_LEAST_COME_AND_TAKE_A_LOOK;

	/**
	 * ID: 1801164<br>
	 * Message: �����N�����Ȃ��̂��B���A�r�r���Ă񂾂�I����ĐH���킯�ł�����܂����B�n�n�n�I
	 */
	public static final NpcStringId NO_ONE_ELSE_DONT_WORRY_I_DONT_BITE_HAHA;

	/**
	 * ID: 1801165<br>
	 * Message: �ꖜ�ȏ㎝���Ă����₪�����������񂾂��B���p�����B�҂ł悩��������͏I������񂾂��B�Ȃ�ł��ł��Ȃ���B
	 */
	public static final NpcStringId THERE_WAS_SOMEONE_WHO_WON_10000_FROM_ME_A_WARRIOR_SHOULDNT_JUST_BE_GOOD_AT_FIGHTING_RIGHT_YOUVE_GOTTA_BE_GOOD_IN_EVERYTHING;

	/**
	 * ID: 1801166<br>
	 * Message: �����A�K�^�̏��_�͒N�̂��ƂɁI�����A���_�ɕ����Ă݂Ȃ��჏�J���i�C���ǂȁB
	 */
	public static final NpcStringId OK_MASTER_OF_LUCK_THATS_YOU_HAHA_WELL_ANYONE_CAN_COME_AFTER_ALL;

	/**
	 * ID: 1801167<br>
	 * Message: �ߍ��Ȑ��ł̓M�����u������Ԃ̌�y�����B����ɂ���͈��S�Ȃ񂾁B
	 */
	public static final NpcStringId SHEDDING_BLOOD_IS_A_GIVEN_ON_THE_BATTLEFIELD_AT_LEAST_ITS_SAFE_HERE;

	/**
	 * ID: 1801168<br>
	 * Message: �������I
	 */
	public static final NpcStringId GASP;

	/**
	 * ID: 1801169<br>
	 * Message: �܂�����I
	 */
	public static final NpcStringId IS_IT_STILL_LONG_OFF;

	/**
	 * ID: 1801170<br>
	 * Message: �G���~�A���l�͂��������B���������c�������Ƃ���M�����Ȃ��D�D�D
	 */
	public static final NpcStringId IS_ERMIAN_WELL_EVEN_I_CANT_BELIEVE_THAT_I_SURVIVED_IN_A_PLACE_LIKE_THIS;

	/**
	 * ID: 1801171<br>
	 * Message: �N�������ɕt���Ċ������D�D�D���Ԃ��~�܂����悤�ɂ��犴����B
	 */
	public static final NpcStringId I_DONT_KNOW_HOW_LONG_ITS_BEEN_SINCE_I_PARTED_COMPANY_WITH_YOU_TIME_DOESNT_SEEM_TO_MOVE_IT_JUST_FEELS_TOO_LONG;

	/**
	 * ID: 1801172<br>
	 * Message: ���̗���ł���Ȃ��Ƃ������̂͐\����Ȃ����D�D�D�N�����ɂ��ꂽ�Ƃ��낪�Y�L�Y�L���Ă��܂�Ȃ��D�D�D
	 */
	public static final NpcStringId SORRY_TO_SAY_THIS_BUT_THE_PLACE_YOU_STRUCK_ME_BEFORE_NOW_HURTS_GREATLY;

	/**
	 * ID: 1801173<br>
	 * Message: �����D�D�D���܂Ȃ����A���͂₱���܂ŁB�Ƒ��̂��Ƃɖ߂肽�������D�D�D
	 */
	public static final NpcStringId UGH_IM_SORRY_IT_LOOKS_LIKE_THIS_IS_IT_FOR_ME_I_WANTED_TO_LIVE_AND_SEE_MY_FAMILY;

	/**
	 * ID: 1801174<br>
	 * Message: �ǂ����H�����Ȃ����B
	 */
	public static final NpcStringId WHERE_ARE_YOU_I_CANT_SEE_ANYTHING;

	/**
	 * ID: 1801175<br>
	 * Message: ��̂ǂ��ɂ���񂾁H���ꂶ��t���Ă����Ȃ�����Ȃ����B
	 */
	public static final NpcStringId WHERE_ARE_YOU_REALLY_I_CANT_FOLLOW_YOU_LIKE_THIS;

	/**
	 * ID: 1801176<br>
	 * Message: ���܂Ȃ��D�D�D���͂₱��܂ŁD�D�D
	 */
	public static final NpcStringId IM_SORRY_THIS_IS_IT_FOR_ME;

	/**
	 * ID: 1801177<br>
	 * Message: �E�E�E�D�D�D�����c���ăG���~�A���l�ɍēx���ڂɂ����낤�Ƃ́D�D�D����ŉƑ��̂��ƂɋA����̂ł��傤���B
	 */
	public static final NpcStringId SOB_TO_SEE_ERMIAN_AGAIN_CAN_I_GO_TO_MY_FAMILY_NOW;

	/**
	 * ID: 1801183<br>
	 * Message: �����[���I����Ȃɑ�����ꂪ����Ȃ�ĂȁB$s1�A�����������B
	 */
	public static final NpcStringId MY_ENERGY_WAS_RECOVERED_SO_QUICKLY_THANKS_S1;

	/**
	 * ID: 1801187<br>
	 * Message: �W�������D�D�D�H�~���N���Ă������D�D�D
	 */
	public static final NpcStringId IM_STARVING;

	/**
	 * ID: 1801189<br>
	 * Message: �C�������قǂ̋������͂��������܂��B
	 */
	public static final NpcStringId MAGIC_POWER_SO_STRONG_THAT_IT_COULD_MAKE_YOU_LOSE_YOUR_MIND_CAN_BE_FELT_FROM_SOMEWHERE;

	/**
	 * ID: 1801190<br>
	 * Message: ���O�����l�Ԃ̑��蕨�Ȃ񂼁A��������Ƃ���Ŏ�����ԂƂł��v�����̂��I
	 */
	public static final NpcStringId EVEN_THOUGH_YOU_BRING_SOMETHING_CALLED_A_GIFT_AMONG_YOUR_HUMANS_IT_WOULD_JUST_BE_PROBLEMATIC_FOR_ME;

	/**
	 * ID: 1801191<br>
	 * Message: �����������͂ǂ������\�������΂����̂��B�l�Ԃ̊���Ƃ͂����������̂Ȃ̂��B
	 */
	public static final NpcStringId I_JUST_DONT_KNOW_WHAT_EXPRESSION_I_SHOULD_HAVE_IT_APPEARED_ON_ME_ARE_HUMANS_EMOTIONS_LIKE_THIS_FEELING;

	/**
	 * ID: 1801192<br>
	 * Message: ���肪�����Ƃ�������͉����̂̎v���o�Ɏc���Ă���D�D�D
	 */
	public static final NpcStringId THE_FEELING_OF_THANKS_IS_JUST_TOO_MUCH_DISTANT_MEMORY_FOR_ME;

	/**
	 * ID: 1801193<br>
	 * Message: ���������D�D�D���̂悤�Ȋ���͑O�ɂ����������Ƃ�����D�D�D
	 */
	public static final NpcStringId BUT_I_KIND_OF_MISS_IT_LIKE_I_HAD_FELT_THIS_FEELING_BEFORE;

	/**
	 * ID: 1801194<br>
	 * Message: ���̓A�C�X �N�C�[�� �t�����D�D�D����ȋC��������������b�T�̎����Ă������̂ɉ߂��Ȃ��I
	 */
	public static final NpcStringId I_AM_ICE_QUEEN_FREYA_THIS_FEELING_AND_EMOTION_ARE_NOTHING_BUT_A_PART_OF_MELISSAA_MEMORIES;

	/**
	 * ID: 1801195<br>
	 * Message: $s1��D�D�D����Ǝv���Ď���Ă����B���������ނ��Ƃ͂Ȃ��B������ƐS�ς�肵���������D�D�D
	 */
	public static final NpcStringId DEAR_S1_THINK_OF_THIS_AS_MY_APPRECIATION_FOR_THE_GIFT_TAKE_THIS_WITH_YOU_THERES_NOTHING_STRANGE_ABOUT_IT_ITS_JUST_A_BIT_OF_MY_CAPRICIOUSNESS;

	/**
	 * ID: 1801196<br>
	 * Message: �N���̍D�ӂȂ���̂͌����ċC���̈������̂ł͂Ȃ��ȁD�D�D$s1��A���O�̐��ӂ��󂯎�邱�ƂƂ��悤�B
	 */
	public static final NpcStringId THE_KINDNESS_OF_SOMEBODY_IS_NOT_A_BAD_FEELING_DEAR_S1_I_WILL_TAKE_THIS_GIFT_OUT_OF_RESPECT_YOUR_KINDNESS;

	/**
	 * ID: 1811000<br>
	 * Message: �t�@�C�^�[
	 */
	public static final NpcStringId FIGHTER;

	/**
	 * ID: 1811001<br>
	 * Message: �E�H�[���A�[
	 */
	public static final NpcStringId WARRIOR;

	/**
	 * ID: 1811002<br>
	 * Message: �O���f�B�G�[�^�[
	 */
	public static final NpcStringId GLADIATOR;

	/**
	 * ID: 1811003<br>
	 * Message: �E�H�[���A�[
	 */
	public static final NpcStringId WARLORD;

	/**
	 * ID: 1811004<br>
	 * Message: �i�C�g
	 */
	public static final NpcStringId KNIGHT;

	/**
	 * ID: 1811005<br>
	 * Message: �p���f�B��
	 */
	public static final NpcStringId PALADIN;

	/**
	 * ID: 1811006<br>
	 * Message: �_�[�N �A�x���W���[
	 */
	public static final NpcStringId DARK_AVENGER;

	/**
	 * ID: 1811007<br>
	 * Message: ���[�O
	 */
	public static final NpcStringId ROGUE;

	/**
	 * ID: 1811008<br>
	 * Message: �g���W���[ �n���^�[
	 */
	public static final NpcStringId TREASURE_HUNTER;

	/**
	 * ID: 1811009<br>
	 * Message: �z�[�N �A�C
	 */
	public static final NpcStringId HAWKEYE;

	/**
	 * ID: 1811010<br>
	 * Message: ���C�W
	 */
	public static final NpcStringId MAGE;

	/**
	 * ID: 1811011<br>
	 * Message: �E�B�U�[�h
	 */
	public static final NpcStringId WIZARD;

	/**
	 * ID: 1811012<br>
	 * Message: �\�[�T���[
	 */
	public static final NpcStringId SORCERER;

	/**
	 * ID: 1811013<br>
	 * Message: �l�N���}���T�[
	 */
	public static final NpcStringId NECROMANCER;

	/**
	 * ID: 1811014<br>
	 * Message: �E�H�[���b�N
	 */
	public static final NpcStringId WARLOCK;

	/**
	 * ID: 1811015<br>
	 * Message: �N�����b�N
	 */
	public static final NpcStringId CLERIC;

	/**
	 * ID: 1811016<br>
	 * Message: �r�V���b�v
	 */
	public static final NpcStringId BISHOP;

	/**
	 * ID: 1811017<br>
	 * Message: �v���t�B�b�g
	 */
	public static final NpcStringId PROPHET;

	/**
	 * ID: 1811018<br>
	 * Message: �G������ �t�@�C�^�[
	 */
	public static final NpcStringId ELVEN_FIGHTER;

	/**
	 * ID: 1811019<br>
	 * Message: �G������ �i�C�g
	 */
	public static final NpcStringId ELVEN_KNIGHT;

	/**
	 * ID: 1811020<br>
	 * Message: �e���v�� �i�C�g
	 */
	public static final NpcStringId TEMPLE_KNIGHT;

	/**
	 * ID: 1811021<br>
	 * Message: �\�[�h �V���K�[
	 */
	public static final NpcStringId SWORD_SINGER;

	/**
	 * ID: 1811022<br>
	 * Message: �G������ �X�J�E�g
	 */
	public static final NpcStringId ELVEN_SCOUT;

	/**
	 * ID: 1811023<br>
	 * Message: �v���C���Y �E�H�[�J�[
	 */
	public static final NpcStringId PLAIN_WALKER;

	/**
	 * ID: 1811024<br>
	 * Message: �V���o�[ �����W���[
	 */
	public static final NpcStringId SILVER_RANGER;

	/**
	 * ID: 1811025<br>
	 * Message: �G������ ���C�W
	 */
	public static final NpcStringId ELVEN_MAGE;

	/**
	 * ID: 1811026<br>
	 * Message: �G������ �E�B�U�[�h
	 */
	public static final NpcStringId ELVEN_WIZARD;

	/**
	 * ID: 1811027<br>
	 * Message: �X�y�� �V���K�[
	 */
	public static final NpcStringId SPELL_SINGER;

	/**
	 * ID: 1811028<br>
	 * Message: �G�������^�� �T�}�i�[
	 */
	public static final NpcStringId ELEMENTAL_SUMMONER;

	/**
	 * ID: 1811029<br>
	 * Message: �I���N��
	 */
	public static final NpcStringId ORACLE;

	/**
	 * ID: 1811030<br>
	 * Message: �G���_�[
	 */
	public static final NpcStringId ELDER;

	/**
	 * ID: 1811031<br>
	 * Message: �_�[�N �t�@�C�^�[
	 */
	public static final NpcStringId DARK_FIGHTER;

	/**
	 * ID: 1811032<br>
	 * Message: �p���X �i�C�g
	 */
	public static final NpcStringId PALACE_KNIGHT;

	/**
	 * ID: 1811033<br>
	 * Message: �V���G�� �i�C�g
	 */
	public static final NpcStringId SHILLIEN_KNIGHT;

	/**
	 * ID: 1811034<br>
	 * Message: �u���C�h �_���T�[
	 */
	public static final NpcStringId BLADE_DANCER;

	/**
	 * ID: 1811035<br>
	 * Message: �A�T�V��
	 */
	public static final NpcStringId ASSASSIN;

	/**
	 * ID: 1811036<br>
	 * Message: �A�r�X �E�H�[�J�[
	 */
	public static final NpcStringId ABYSS_WALKER;

	/**
	 * ID: 1811037<br>
	 * Message: �t�@���g�� �����W���[
	 */
	public static final NpcStringId PHANTOM_RANGER;

	/**
	 * ID: 1811038<br>
	 * Message: �_�[�N ���C�W
	 */
	public static final NpcStringId DARK_MAGE;

	/**
	 * ID: 1811039<br>
	 * Message: �_�[�N �E�B�U�[�h
	 */
	public static final NpcStringId DARK_WIZARD;

	/**
	 * ID: 1811040<br>
	 * Message: �X�y�� �n�E���[
	 */
	public static final NpcStringId SPELLHOWLER;

	/**
	 * ID: 1811041<br>
	 * Message: �t�@���g�� �T�}�i�[
	 */
	public static final NpcStringId PHANTOM_SUMMONER;

	/**
	 * ID: 1811042<br>
	 * Message: �V���G�� �I���N��
	 */
	public static final NpcStringId SHILLIEN_ORACLE;

	/**
	 * ID: 1811043<br>
	 * Message: �V���G�� �G���_�[
	 */
	public static final NpcStringId SHILLIEN_ELDER;

	/**
	 * ID: 1811044<br>
	 * Message: �I�[�N �t�@�C�^�[
	 */
	public static final NpcStringId ORC_FIGHTER;

	/**
	 * ID: 1811045<br>
	 * Message: �I�[�N ���C�_�[
	 */
	public static final NpcStringId ORC_RAIDER;

	/**
	 * ID: 1811046<br>
	 * Message: �f�X�g���C���[
	 */
	public static final NpcStringId DESTROYER;

	/**
	 * ID: 1811047<br>
	 * Message: �I�[�N �����N
	 */
	public static final NpcStringId ORC_MONK;

	/**
	 * ID: 1811048<br>
	 * Message: �^�C�����g
	 */
	public static final NpcStringId TYRANT;

	/**
	 * ID: 1811049<br>
	 * Message: �I�[�N ���C�W
	 */
	public static final NpcStringId ORC_MAGE;

	/**
	 * ID: 1811050<br>
	 * Message: �I�[�N �V���[�}��
	 */
	public static final NpcStringId ORC_SHAMAN;

	/**
	 * ID: 1811051<br>
	 * Message: �I�[�o�[���[�h
	 */
	public static final NpcStringId OVERLORD;

	/**
	 * ID: 1811052<br>
	 * Message: �E�H�[�N���C�A�[
	 */
	public static final NpcStringId WARCRYER;

	/**
	 * ID: 1811053<br>
	 * Message: �h���[�u�� �t�@�C�^�[
	 */
	public static final NpcStringId DWARVEN_FIGHTER;

	/**
	 * ID: 1811054<br>
	 * Message: �X�J�x���W���[
	 */
	public static final NpcStringId SCAVENGER;

	/**
	 * ID: 1811055<br>
	 * Message: �o�E���e�B �n���^�[
	 */
	public static final NpcStringId BOUNTY_HUNTER;

	/**
	 * ID: 1811056<br>
	 * Message: �A���e�B�U��
	 */
	public static final NpcStringId ARTISAN;

	/**
	 * ID: 1811057<br>
	 * Message: �E�H�[�X�~�X
	 */
	public static final NpcStringId WARSMITH;

	/**
	 * ID: 1811088<br>
	 * Message: �f���G���X�g
	 */
	public static final NpcStringId DUELIST;

	/**
	 * ID: 1811089<br>
	 * Message: �h���b�h�m�[�g
	 */
	public static final NpcStringId DREADNOUGHT;

	/**
	 * ID: 1811090<br>
	 * Message: �t�F�j�b�N�X �i�C�g
	 */
	public static final NpcStringId PHOENIX_KNIGHT;

	/**
	 * ID: 1811091<br>
	 * Message: �w�� �i�C�g
	 */
	public static final NpcStringId HELL_KNIGHT;

	/**
	 * ID: 1811092<br>
	 * Message: �T�W�^���E�X
	 */
	public static final NpcStringId SAGITTARIUS;

	/**
	 * ID: 1811093<br>
	 * Message: �A�h�x���`�����[
	 */
	public static final NpcStringId ADVENTURER;

	/**
	 * ID: 1811094<br>
	 * Message: �A�[�N���C�W
	 */
	public static final NpcStringId ARCHMAGE;

	/**
	 * ID: 1811095<br>
	 * Message: �\�E�� �e�C�J�[
	 */
	public static final NpcStringId SOULTAKER;

	/**
	 * ID: 1811096<br>
	 * Message: �A���J�i ���[�h
	 */
	public static final NpcStringId ARCANA_LORD;

	/**
	 * ID: 1811097<br>
	 * Message: �J�[�f�B�i��
	 */
	public static final NpcStringId CARDINAL;

	/**
	 * ID: 1811098<br>
	 * Message: �n�C�G���t�@���g
	 */
	public static final NpcStringId HIEROPHANT;

	/**
	 * ID: 1811099<br>
	 * Message: �G���@�X �e���v���[
	 */
	public static final NpcStringId EVAS_TEMPLAR;

	/**
	 * ID: 1811100<br>
	 * Message: �\�[�h �~���[�Y
	 */
	public static final NpcStringId SWORD_MUSE;

	/**
	 * ID: 1811101<br>
	 * Message: �E�B���h ���C�_�[
	 */
	public static final NpcStringId WIND_RIDER;

	/**
	 * ID: 1811102<br>
	 * Message: ���[�����C�g �Z���e�B�l��
	 */
	public static final NpcStringId MOONLIGHT_SENTINEL;

	/**
	 * ID: 1811103<br>
	 * Message: �~�X�e�B�b�N �~���[�Y
	 */
	public static final NpcStringId MYSTIC_MUSE;

	/**
	 * ID: 1811104<br>
	 * Message: �G�������^�� �}�X�^�[
	 */
	public static final NpcStringId ELEMENTAL_MASTER;

	/**
	 * ID: 1811105<br>
	 * Message: �G���@�X �Z�C���g
	 */
	public static final NpcStringId EVAS_SAINT;

	/**
	 * ID: 1811106<br>
	 * Message: �V���G�� �e���v���[
	 */
	public static final NpcStringId SHILLIEN_TEMPLAR;

	/**
	 * ID: 1811107<br>
	 * Message: �X�y�N�g���� �_���T�[
	 */
	public static final NpcStringId SPECTRAL_DANCER;

	/**
	 * ID: 1811108<br>
	 * Message: �S�[�X�g �n���^�[
	 */
	public static final NpcStringId GHOST_HUNTER;

	/**
	 * ID: 1811109<br>
	 * Message: �S�[�X�g �Z���e�B�l��
	 */
	public static final NpcStringId GHOST_SENTINEL;

	/**
	 * ID: 1811110<br>
	 * Message: �X�g�[�� �X�N���[�}�[
	 */
	public static final NpcStringId STORM_SCREAMER;

	/**
	 * ID: 1811111<br>
	 * Message: �X�y�N�g���� �}�X�^�[
	 */
	public static final NpcStringId SPECTRAL_MASTER;

	/**
	 * ID: 1811112<br>
	 * Message: �V���G�� �Z�C���g
	 */
	public static final NpcStringId SHILLIEN_SAINT;

	/**
	 * ID: 1811113<br>
	 * Message: �^�C�^��
	 */
	public static final NpcStringId TITAN;

	/**
	 * ID: 1811114<br>
	 * Message: �O�����h �J�o�^��
	 */
	public static final NpcStringId GRAND_KHAVATARI;

	/**
	 * ID: 1811115<br>
	 * Message: �h�~�l�[�^�[
	 */
	public static final NpcStringId DOMINATOR;

	/**
	 * ID: 1811116<br>
	 * Message: �h�D�[�� �N���C���[
	 */
	public static final NpcStringId DOOM_CRYER;

	/**
	 * ID: 1811117<br>
	 * Message: �t�H�[�`���� �V�[�J�[
	 */
	public static final NpcStringId FORTUNE_SEEKER;

	/**
	 * ID: 1811118<br>
	 * Message: �}�G�X�g��
	 */
	public static final NpcStringId MAESTRO;

	/**
	 * ID: 1811123<br>
	 * Message: �J�}�G�� �\���W���[
	 */
	public static final NpcStringId KAMAEL_SOLDIER;

	/**
	 * ID: 1811125<br>
	 * Message: �g���[�p�[
	 */
	public static final NpcStringId TROOPER;

	/**
	 * ID: 1811126<br>
	 * Message: �E�H�[�_�[
	 */
	public static final NpcStringId WARDER;

	/**
	 * ID: 1811127<br>
	 * Message: �o�[�T�[�J�[
	 */
	public static final NpcStringId BERSERKER;

	/**
	 * ID: 1811128<br>
	 * Message: �\�E�� �u���[�J�[
	 */
	public static final NpcStringId SOUL_BREAKER;

	/**
	 * ID: 1811130<br>
	 * Message: �A���@���X�^
	 */
	public static final NpcStringId ARBALESTER;

	/**
	 * ID: 1811131<br>
	 * Message: �h�D�[�� �u�����K�[
	 */
	public static final NpcStringId DOOMBRINGER;

	/**
	 * ID: 1811132<br>
	 * Message: �\�E�� �n�E���h
	 */
	public static final NpcStringId SOUL_HOUND;

	/**
	 * ID: 1811134<br>
	 * Message: �g���b�N�X�^�[
	 */
	public static final NpcStringId TRICKSTER;

	/**
	 * ID: 1811135<br>
	 * Message: �C���X�y�N�^�[
	 */
	public static final NpcStringId INSPECTOR;

	/**
	 * ID: 1811136<br>
	 * Message: �W���f�B�P�[�^�[
	 */
	public static final NpcStringId JUDICATOR;

	/**
	 * ID: 1811137<br>
	 * Message: �N���I�n���A���^���X�l�̂��@���𑹂˂��́A�N�ł��낤�Ƌ�����I
	 */
	public static final NpcStringId WHOS_THERE_IF_YOU_DISTURB_THE_TEMPER_OF_THE_GREAT_LAND_DRAGON_ANTHARAS_I_WILL_NEVER_FORGIVE_YOU;

	/**
	 * ID: 1900000<br>
	 * Message: ���͂͂́I�N���X�}�X �T���^�͉����߂܂����I���N�̃v���[���g�͂��a�����I
	 */
	public static final NpcStringId KEHAHAHA_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR;

	/**
	 * ID: 1900003<br>
	 * Message: �ǂ����I���̏������ȁB
	 */
	public static final NpcStringId WELL_I_WIN_RIGHT;

	/**
	 * ID: 1900004<br>
	 * Message: �������͏��������₪��I
	 */
	public static final NpcStringId NOW_ALL_OF_YOU_LOSERS_GET_OUT_OF_HERE;

	/**
	 * ID: 1900006<br>
	 * Message: �N���X�}�X �T���^���~���ɗ����悤���ȁB�����A���肪���������ȁB
	 */
	public static final NpcStringId I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_OPPONENT;

	/**
	 * ID: 1900007<br>
	 * Message: �����A��邶��˂����B
	 */
	public static final NpcStringId OH_NOT_BAD;

	/**
	 * ID: 1900008<br>
	 * Message: ������o���񂶂�Ȃ������I
	 */
	public static final NpcStringId AGH_THATS_NOT_WHAT_I_MEANT_TO_DO;

	/**
	 * ID: 1900009<br>
	 * Message: ���̎􂢂��󂯂�I���A������H
	 */
	public static final NpcStringId CURSE_YOU_HUH_WHAT;

	/**
	 * ID: 1900015<br>
	 * Message: �N���X�}�X �T���^����l�����߂܂��Ă邩���ɍs�����B�ЂЂ��I
	 */
	public static final NpcStringId SHALL_I_GO_TO_SEE_IF_SANTA_IS_STILL_THERE_HEHE;

	/**
	 * ID: 1900017<br>
	 * Message: �N���X�}�X �T���^�������ė��Ă���Ȃ���v���[���g�͂������Ȃ���B
	 */
	public static final NpcStringId SANTA_CAN_GIVE_NICE_PRESENTS_ONLY_IF_HES_RELEASED_FROM_THE_TURKEY;

	/**
	 * ID: 1900018<br>
	 * Message: �I�b�z�b�z�D�D�D�D�݂Ȃ���A����J�l�B����͕K���������܂��B
	 */
	public static final NpcStringId OH_HO_HO_OH_HO_HO_THANK_YOU_EVERYONE_I_WILL_REPAY_YOU_FOR_SURE;

	/**
	 * ID: 1900019<br>
	 * Message: �����[�N���X�}�X�I����J�l�B
	 */
	public static final NpcStringId MERRY_CHRISTMAS_WELL_DONE;

	/**
	 * ID: 1900021<br>
	 * Message: %s�A�N�̂��߂Ƀv���[���g��p�ӂ����B
	 */
	public static final NpcStringId S_I_HAVE_PREPARED_A_GIFT_FOR_YOU;

	/**
	 * ID: 1900022<br>
	 * Message: %s�Ƀv���[���g������B
	 */
	public static final NpcStringId I_HAVE_A_GIFT_FOR_S;

	/**
	 * ID: 1900024<br>
	 * Message: �C���x���g�������Ă����B�r�b�O�ȃv���[���g�������Ă邩������Ȃ����B
	 */
	public static final NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_WILL_BE_A_BIG_PRESENT;

	/**
	 * ID: 1900026<br>
	 * Message: ���܂ł����肾�I���낻��O���Ă������B
	 */
	public static final NpcStringId WHEN_ARE_YOU_GOING_TO_STOP_IM_SLOWLY_GETTING_TIRED_OF_THIS;

	/**
	 * ID: 1900027<br>
	 * Message: �N���X�}�X �T���^�̓`���F�킵���~���o���Ă��ꂽ%s�ɏj��������܂��悤�ɁD�D�D
	 */
	public static final NpcStringId MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S_WHO_SAVED_ME;

	/**
	 * ID: 1900028<br>
	 * Message: �N���I���̖����W����̂́B���̋�ɂ�^����I
	 */
	public static final NpcStringId HOW_DARE_YOU_AWAKEN_ME_FEEL_THE_PAIN_OF_THE_FLAMES;

	/**
	 * ID: 1900029<br>
	 * Message: �N���I�΂̈Ќ��ɋt�炤�̂́B
	 */
	public static final NpcStringId WHO_DARES_OPPOSE_THE_MAJESTY_OF_FIRE;

	/**
	 * ID: 1900030<br>
	 * Message: ���A�ɂ��I�����͂��߂��I���̃r�[�Y�����́I
	 */
	public static final NpcStringId OH_OUCH_NO_NOT_THERE_NOT_MY_BEAD;

	/**
	 * ID: 1900031<br>
	 * Message: �A�₽���I�₽������Ȃ����I���������B
	 */
	public static final NpcStringId CO_COLD_THATS_COLD_ACK_ACK;

	/**
	 * ID: 1900032<br>
	 * Message: %s�A���ނ����������Ȃ��ł���D�D�D���ށB
	 */
	public static final NpcStringId PLEASE_S_DONT_HIT_ME_PLEASE;

	/**
	 * ID: 1900033<br>
	 * Message: �������������I���|�ɐg��k�킹��I
	 */
	public static final NpcStringId KUAAANNGGG_SHAKE_IN_FEAR;

	/**
	 * ID: 1900034<br>
	 * Message: ���ɉ���Ɏ���o������A�僄�P�h���邺�I
	 */
	public static final NpcStringId IF_YOU_ATTACK_ME_RIGHT_NOW_YOURE_REALLY_GOING_TO_GET_IT;

	/**
	 * ID: 1900035<br>
	 * Message: ������Ƒ҂āI���̕K�E�Z�������Ă�낤�B
	 */
	public static final NpcStringId JUST_YOU_WAIT_IM_GOING_TO_SHOW_YOU_MY_KILLER_TECHNIQUE;

	/**
	 * ID: 1900036<br>
	 * Message: ���O���Ƃ��ɂ���鉴�ł͂Ȃ��I
	 */
	public static final NpcStringId YOU_DONT_DARE_ATTACK_ME;

	/**
	 * ID: 1900037<br>
	 * Message: �΂̐���Ƃ͈Ⴄ���I�΂̐���Ƃ͂ȁI���̓{���H�炦�I
	 */
	public static final NpcStringId ITS_DIFFERENT_FROM_THE_SPIRIT_OF_FIRE_ITS_NOT_THE_SPIRIT_OF_FIRE_FEEL_MY_WRATH;

	/**
	 * ID: 1900038<br>
	 * Message: �����D�D�D�����͂ǂ����D�D�D���͂������Ď��ʂ̂��D�D�D
	 */
	public static final NpcStringId COLD_THIS_PLACE_IS_THIS_WHERE_I_DIE;

	/**
	 * ID: 1900039<br>
	 * Message: �̉����ǂ�ǂ�D���Ă����D�D�D�O�����J�C����D�D�D�\���󂲂����܂���D�D�D
	 */
	public static final NpcStringId MY_BODY_IS_COOLING_OH_GRAN_KAIN_FORGIVE_ME;

	/**
	 * ID: 1900040<br>
	 * Message: �΂��Ȃ�I���͑f��ōU������Ȃ��ƃ_���[�W���󂯂Ȃ��̂��I
	 */
	public static final NpcStringId IDIOT_I_ONLY_INCUR_DAMAGE_FROM_BARE_HANDED_ATTACKS;

	/**
	 * ID: 1900051<br>
	 * Message: �L�����f�B���Ȃ��Ȃ����D�D�D�������I�������ᔠ������Ă���I
	 */
	public static final NpcStringId IM_OUT_OF_CANDY_ILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1900052<br>
	 * Message: �L�����f�B���Ȃ��Ȃ����Ⴂ�܂����D�D�D���ꂶ��A����ɂ������ᔠ�������܂��傤�B
	 */
	public static final NpcStringId SINCE_IM_OUT_OF_CANDY_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1900060<br>
	 * Message: �z���g�ɂ������ł��ˁB���͂��̂������v���[���g�������܂���B���҂��Ă��������ˁB
	 */
	public static final NpcStringId YOURE_TOO_GOOD_NEXT_TIME_ILL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_IT;

	/**
	 * ID: 1900062<br>
	 * Message: �����ŕ�������p���������Ȃ��D�D�D
	 */
	public static final NpcStringId I_WOULD_BE_EMBARRASSED_TO_LOSE_AGAIN_HERE;

	/**
	 * ID: 1900070<br>
	 * Message: �v���ł����14�����I���͎��̕󕨁A�S�[���f�� �W���b�N �I �����^���������܂���I
	 */
	public static final NpcStringId EVEN_PROS_CANT_DO_14_WINS_IN_A_ROW_NEXT_TIME_ILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN;

	/**
	 * ID: 1900071<br>
	 * Message: �������߂��I�~�Q���I��������583�N�̐l���ł��O���ō�����I
	 */
	public static final NpcStringId I_CANT_DO_THIS_ANYMORE_YOU_WIN_IN_ALL_MY_583_YEARS_YOURE_THE_BEST_THAT_IVE_SEEN;

	/**
	 * ID: 1900081<br>
	 * Message: %s�l�̓W���b�N �Q�[����%s�A���B���ł��I
	 */
	public static final NpcStringId S_HAS_WON_S_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1900082<br>
	 * Message: ���߂łƂ��������܂��I%s�l�̓W���b�N �Q�[����%s�A���B���ł��I
	 */
	public static final NpcStringId CONGRATULATIONS_S_HAS_WON_S_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1900084<br>
	 * Message: �ǂ��ǂ��A�x���_�ł��B�W���b�N�@�Q�[��1�ʁA���߂łƂ��������܂��I���ɂ����̃N���h����Ռ��I�̃v���[���g�����炦�܂���B���ꂶ��A�܂��Q�[�����ɂ��Ă��������ˁB
	 */
	public static final NpcStringId HELLO_IM_BELLDANDY_CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACKS_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOULL_GET_AN_AMAZING_GIFT_LETS_PLAY_JACKS_GAME_AGAIN;

	/**
	 * ID: 1900104<br>
	 * Message: �ނɂ�ނɂ�D�D�D�����A����ɂ��́I�����V�C���˂��D�D�D
	 */
	public static final NpcStringId YAWN_AHH_HELLO_NICE_WEATHER_WERE_HAVING;

	/**
	 * ID: 1900105<br>
	 * Message: ���Ȃ��������D�D�D�˂��A�����H�Ȃ��́H���ꂪ�Ȃ���傫���Ȃ�Ȃ��悧�B
	 */
	public static final NpcStringId AH_IM_HUNGRY_DO_YOU_HAVE_ANY_BABY_FOOD_THATS_WHAT_I_NEED_TO_EAT_TO_GET_BIGGER;

	/**
	 * ID: 1900106<br>
	 * Message: �����傫���Ȃ��ăT���^����̂�����Ђ������񂾁B
	 */
	public static final NpcStringId I_GOTTA_GROW_UP_FAST_I_WANT_TO_PULL_SANTAS_SLED_TOO;

	/**
	 * ID: 1900107<br>
	 * Message: ���܁`���I����ő傫���Ȃ���I
	 */
	public static final NpcStringId YUMMY_I_THINK_I_CAN_GROW_UP_NOW;

	/**
	 * ID: 1900108<br>
	 * Message: �������ŏ��N�g�i�J�C�ɂȂꂽ��I
	 */
	public static final NpcStringId THANKS_TO_YOU_I_GREW_UP_INTO_A_BOY_RUDOLPH;

	/**
	 * ID: 1900109<br>
	 * Message: �����͂���g�����a���˂��B
	 */
	public static final NpcStringId ITS_GREAT_WEATHER_FOR_RUNNING_AROUND;

	/**
	 * ID: 1900110<br>
	 * Message: �l�͑傫���Ȃ����牽�ɂȂ�̂��Ȃ��D�D�D
	 */
	public static final NpcStringId WHATLL_I_BE_WHEN_I_GROW_UP_I_WONDER;

	/**
	 * ID: 1900111<br>
	 * Message: �l�������ƈ�ĂĂ��ꂽ��A���Ԃ����邩��ˁI
	 */
	public static final NpcStringId IF_YOU_TAKE_GOOD_CARE_OF_ME_ILL_NEVER_FORGET_IT;

	/**
	 * ID: 1900112<br>
	 * Message: �l���ȂłȂł��āI�A�N�V���� �^�u�ɂ��鉷���Ȏ�X�L�����g���΂����񂾂�B
	 */
	public static final NpcStringId PLEASE_PET_ME_LOVINGLY_YOU_CAN_USE_THE_HAND_OF_WARMTH_SKILL_UNDER_THE_ACTION_TAB;

	/**
	 * ID: 1900113<br>
	 * Message: �C���������I���肪�Ƃ��I
	 */
	public static final NpcStringId I_FEEL_GREAT_THANK_YOU;

	/**
	 * ID: 1900114<br>
	 * Message: �������`��A�C���������I�����������I
	 */
	public static final NpcStringId WOO_WHAT_A_GOOD_FEELING_I_GOTTA_GROW_MORE_AND_MORE;

	/**
	 * ID: 1900115<br>
	 * Message: ���������I�����ƐH�ׂ��炷���ɂł��傫���Ȃ肻���B
	 */
	public static final NpcStringId OH_YUMMY_IF_I_KEEP_EATING_THIS_I_THINK_I_CAN_GROW_UP_ALL_THE_WAY;

	/**
	 * ID: 1900116<br>
	 * Message: �ނ���ނ���B���������I�����Ƃ��傤�����I
	 */
	public static final NpcStringId YUM_YUM_DELICIOUS_GIVE_ME_MORE_OF_THIS;

	/**
	 * ID: 1900117<br>
	 * Message: ���A����́D�D�D�����̒��ŌJ��L�����閡�̍U����`
	 */
	public static final NpcStringId WOW_THIS_TASTE_THERES_A_WHOLE_NEW_WORLD_IN_MY_MOUTH;

	/**
	 * ID: 1900118<br>
	 * Message: ���܂��A���܂�����I���ꂪ���̐��̉a���I
	 */
	public static final NpcStringId YEAH_ITS_SO_DELICIOUS_THIS_IS_THAT_STAR_FOOD;

	/**
	 * ID: 1900119<br>
	 * Message: �l�������ƈ����Ă�`�������I
	 */
	public static final NpcStringId PAY_SOME_ATTENTION_TO_ME_HMPH;

	/**
	 * ID: 1900120<br>
	 * Message: ���肪�Ƃ��I�������Ŗ����ɑ傫���Ȃꂽ��B�͂��A�l����̃v���[���g�I
	 */
	public static final NpcStringId THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_AN_ADULT_HERE_IS_MY_GIFT;

	/**
	 * ID: 1900121<br>
	 * Message: ���肪�Ƃ��A%s�I����Ŗl��������Ђ����B
	 */
	public static final NpcStringId THANK_YOU_S_NOW_I_CAN_PULL_THE_SLED;

	/**
	 * ID: 1900122<br>
	 * Message: %s�A�����Ԃ����b�ɂȂ�܂����B�y����������I
	 */
	public static final NpcStringId S_THANK_YOU_FOR_TAKING_CARE_OF_ME_ALL_THIS_TIME_I_ENJOYED_IT_VERY_MUCH;

	/**
	 * ID: 1900123<br>
	 * Message: %s�A���낻�낻����g���ɍs���Ȃ���B���݂����Ȃ�Ȃ��B
	 */
	public static final NpcStringId S_IT_WONT_BE_LONG_NOW_UNTIL_IT_BECOMES_TIME_TO_PULL_THE_SLED_ITS_TOO_BAD;

	/**
	 * ID: 1900124<br>
	 * Message: �l�̓T���^����̂Ƃ���ɖ߂��B���܂ł��肪�Ƃ��I
	 */
	public static final NpcStringId I_MUST_RETURN_TO_SANTA_NOW_THANK_YOU_FOR_EVERYTHING;

	/**
	 * ID: 1900125<br>
	 * Message: ����ɂ��́B�킽���A�t���h�����̏����B�������Ō��̎p�ɖ߂ꂽ��B
	 */
	public static final NpcStringId HELLO_IM_A_GIRL_RUDOLPH_I_WAS_ABLE_TO_FIND_MY_TRUE_SELF_THANKS_TO_YOU;

	/**
	 * ID: 1900126<br>
	 * Message: ����͖l����̊��ӂ̃v���[���g�B���܂ň�ĂĂ���Ă��肪�Ƃ��I
	 */
	public static final NpcStringId THIS_IS_MY_GIFT_OF_THANKS_THANK_YOU_FOR_TAKING_CARE_OF_ME;

	/**
	 * ID: 1900127<br>
	 * Message: %s�A�������肪�Ƃ��I
	 */
	public static final NpcStringId S_I_WAS_ALWAYS_GRATEFUL;

	/**
	 * ID: 1900128<br>
	 * Message: ���c�ɂ������ǁA���낻��s���Ȃ���B
	 */
	public static final NpcStringId IM_A_LITTLE_SAD_ITS_TIME_TO_LEAVE_NOW;

	/**
	 * ID: 1900129<br>
	 * Message: %s�A�l�͂��낻��̋��ɋA��Ȃ���B
	 */
	public static final NpcStringId S_THE_TIME_HAS_COME_FOR_ME_TO_RETURN_TO_MY_HOME;

	/**
	 * ID: 1900130<br>
	 * Message: %s�A���肪�Ƃ��I
	 */
	public static final NpcStringId S_THANK_YOU;

	/**
	 * ID: 1900131<br>
	 * Message: �͂͂͂́I�T���^�N���[�X�͉����߂܂����I������H�����͂ǂ��H���͂���H
	 */
	public static final NpcStringId HAHAHA_I_CAPTURED_SANTA_CLAUS_HUH_WHERE_IS_THIS_WHO_ARE_YOU;

	/**
	 * ID: 1900132<br>
	 * Message: ���͂���񂯂�ɕ����ĕ߂܂��Ă�񂾁D�D�D�������I���O�ɔ������肵�Ă��I���A������H
	 */
	public static final NpcStringId I_LOST_AT_ROCK_PAPER_SCISSORS_AND_WAS_TAKEN_CAPTIVE_I_MIGHT_AS_WELL_TAKE_OUT_MY_ANGER_ON_YOU_HUH_WHAT;

	/**
	 * ID: 1900133<br>
	 * Message: ��邱�ƂȂ����ƑS�����s�D�D�D����΂��I�T���^�͉����߂܂��Ă�邩��ȁB���Ă��I
	 */
	public static final NpcStringId NOTHINGS_WORKING_IM_LEAVING_ILL_DEFINITELY_CAPTURE_SANTA_AGAIN_JUST_YOU_WAIT;

	/**
	 * ID: 1900134<br>
	 * Message: �g�i�J�C�𑁂���ĂȂ���΁D�D�D���N�̃N���X�}�X�v���[���g�͂��a�����D�D�D
	 */
	public static final NpcStringId I_MUST_RAISE_RUDOLPH_QUICKLY_THIS_YEARS_CHRISTMAS_GIFTS_HAVE_TO_BE_DELIVERED;

	/**
	 * ID: 1900135<br>
	 * Message: �����[�N���X�}�X�I���܂����񂩂ˁA�g�i�J�C����ĂĂ��ꂽ�̂́B�������Ńv���[���g�̔z�B�͂��܂���������B
	 */
	public static final NpcStringId MERRY_CHRISTMAS_THANKS_TO_YOUR_EFFORTS_IN_RAISING_RUDOLPH_THE_GIFT_DELIVERY_WAS_A_SUCCESS;

	/**
	 * ID: 1900136<br>
	 * Message: ����10���ŁA�l����Ă����Ă���1���Ԃ���B
	 */
	public static final NpcStringId IN_10_MINUTES_IT_WILL_BE_1_HOUR_SINCE_YOU_STARTED_RAISING_ME;

	/**
	 * ID: 1900137<br>
	 * Message: 5����ɁA�����ӂ���x�ƈ���x��99%�ɂȂ�����A���ƂȂɂȂ���B
	 */
	public static final NpcStringId AFTER_5_MINUTES_IF_MY_FULL_FEELING_AND_AFFECTION_LEVEL_REACH_99_I_CAN_GROW_BIGGER;

	/**
	 * ID: 1900139<br>
	 * Message: ���b�t�B�[�I�l�̓A�f�i����D���Ȑ���̃��b�t�B�[���b�t�B�[����`
	 */
	public static final NpcStringId LUCKY_IM_LUCKY_THE_SPIRIT_THAT_LOVES_ADENA;

	/**
	 * ID: 1900140<br>
	 * Message: ���b�t�B�[�I�A�f�i�H�ׂ����D�D�D�A�f�i���傤������I
	 */
	public static final NpcStringId LUCKY_I_WANT_TO_EAT_ADENA_GIVE_IT_TO_ME;

	/**
	 * ID: 1900141<br>
	 * Message: ���b�t�B�[�I�A�f�i��H�׉߂���Ɨ����Ȃ��Ȃ�񂾂�ˁc
	 */
	public static final NpcStringId LUCKY_IF_I_EAT_TOO_MUCH_ADENA_MY_WINGS_DISAPPEAR;

	/**
	 * ID: 1900142<br>
	 * Message: ���b�t�B�[���������I���肪�Ɓ`�I
	 */
	public static final NpcStringId YUMMY_THANKS_LUCKY;

	/**
	 * ID: 1900143<br>
	 * Message: ���������D�D�D�܁A�܂����悧�D�D�D
	 */
	public static final NpcStringId GRRRR_YUCK;

	/**
	 * ID: 1900144<br>
	 * Message: ���b�t�B�[�I�A�f�i���������������I�̑傫���Ȃ�I
	 */
	public static final NpcStringId LUCKY_THE_ADENA_IS_SO_YUMMY_IM_GETTING_BIGGER;

	/**
	 * ID: 1900145<br>
	 * Message: ���b�t�B�[�I�����A�f�i�͏I��肾��ˁH�ӂ��D�D�D�̂��d����B
	 */
	public static final NpcStringId LUCKY_NO_MORE_ADENA_OH_IM_SO_HEAVY;

	/**
	 * ID: 1900146<br>
	 * Message: ���b�t�B�[�I���������ς����悧�`�I�����H�ׂ��Ȃ����Ă΁I
	 */
	public static final NpcStringId LUCKY_IM_FULL_THANKS_FOR_THE_YUMMY_ADENA_OH_IM_SO_HEAVY;

	/**
	 * ID: 1900147<br>
	 * Message: ���b�t�B�[�I�A�f�i���Ȃ����D�D�D�Œ�ł�%s�ȏ�͂Ȃ��ƂˁI
	 */
	public static final NpcStringId LUCKY_IT_WASNT_ENOUGH_ADENA_ITS_GOTTA_BE_AT_LEAST_S;

	/**
	 * ID: 1900148<br>
	 * Message: �����A�����Ȃ��Ȃ���������I�D�D�D����́H������������H�ׂ����̓f���o�����Ⴄ����I
	 */
	public static final NpcStringId OH_MY_WINGS_DISAPPEARED_ARE_YOU_GONNA_HIT_ME_IF_YOU_HIT_ME_ILL_THROW_UP_EVERYTHING_THAT_I_ATE;

	/**
	 * ID: 1900149<br>
	 * Message: �����A�����D�D�D�Ђ��Ђ��I�ȁA����́I�H�|����[�I�|����[�I����Ȃ��Ƃ������ςȂ��ƂɂȂ��I
	 */
	public static final NpcStringId OH_MY_WINGS_ACK_ARE_YOU_GONNA_HIT_ME_SCARY_SCARY_IF_YOU_HIT_ME_SOMETHING_BAD_WILL_HAPPEN;

	/**
	 * ID: 1900150<br>
	 * Message: �h���̗E�҂������׈��Ȓn���A���^���X��|���܂����I
	 */
	public static final NpcStringId THE_EVIL_LAND_DRAGON_ANTHARAS_HAS_BEEN_DEFEATED;

	/**
	 * ID: 1900151<br>
	 * Message: �h���̗E�҂������׈��ȉΗ����@���J�X��|���܂����I
	 */
	public static final NpcStringId THE_EVIL_FIRE_DRAGON_VALAKAS_HAS_BEEN_DEFEATED;

	/**
	 * ID: 1900152<br>
	 * Message: ������ł��x���͂���܂���B���̕��Ɏd����΍ň��̎��Ԃ͖Ƃ�܂��B
	 */
	public static final NpcStringId TO_SERVE_HIM_NOW_MEANS_YOU_WILL_BE_ABLE_TO_ESCAPE_A_WORSE_SITUATION;

	/**
	 * ID: 1900153<br>
	 * Message: �j�ł̏��_��A��X���������܂��D�D�D
	 */
	public static final NpcStringId OH_GODDESS_OF_DESTRUCTION_FORGIVE_US;

	/**
	 * ID: 1900154<br>
	 * Message: �󂪐Ԃ����܂�A��n�����s�������A�ł̒����炠�̂������߂��Ă�����B
	 */
	public static final NpcStringId WHEN_THE_SKY_TURNS_BLOOD_RED_AND_THE_EARTH_BEGINS_TO_CRUMBLE_FROM_THE_DARKNESS_SHE_WILL_RETURN;

	/**
	 * ID: 1900155<br>
	 * Message: �A���^���X�̑����ɑ�n�̋C���W�܂�܂��B
	 */
	public static final NpcStringId EARTH_ENERGY_IS_GATHERING_NEAR_ANTHARASS_LEGS;

	/**
	 * ID: 1900156<br>
	 * Message: �A���^���X����n�̋C���z�����n�߂܂��B
	 */
	public static final NpcStringId ANTHARAS_STARTS_TO_ABSORB_THE_EARTH_ENERGY;

	/**
	 * ID: 1900157<br>
	 * Message: �A���^���X�������K�����͂ˏグ�܂��B
	 */
	public static final NpcStringId ANTHARAS_RAISES_ITS_THICK_TAIL;

	/**
	 * ID: 1900158<br>
	 * Message: �A���^���X�̑̂���Ј����������܂��B
	 */
	public static final NpcStringId YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_ANTHARAS;

	/**
	 * ID: 1900159<br>
	 * Message: �A���^���X�����C��тт��ڂ����Ă��܂��B
	 */
	public static final NpcStringId ANTHARASS_EYES_ARE_FILLED_WITH_RAGE;

	/**
	 * ID: 1900160<br>
	 * Message: $s1�A�N����͂��̂����̋C��������B
	 */
	public static final NpcStringId S1_I_CAN_FEEL_THEIR_PRESENCE_FROM_YOU;

	/**
	 * ID: 1900161<br>
	 * Message: $s1�A�Z���A���̉��ňꏏ�ɋF���Ă���B
	 */
	public static final NpcStringId S1_BRETHREN_COME_TO_MY_SIDE_AND_FOLLOW_ME;

	/**
	 * ID: 1900162<br>
	 * Message: �A���^���X�����K���܂��B
	 */
	public static final NpcStringId ANTHARAS_ROARS;

	/**
	 * ID: 1900163<br>
	 * Message: ���@���J�X�ɉΉ��̖��͂��W�܂�܂��B
	 */
	public static final NpcStringId FLAME_ENERGY_IS_BEING_DIRECTED_TOWARDS_VALAKAS;

	/**
	 * ID: 1900164<br>
	 * Message: ���@���J�X����Ј����������܂��B
	 */
	public static final NpcStringId YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_VALAKAS;

	/**
	 * ID: 1900165<br>
	 * Message: ���@���J�X���K�����Њd����悤�ɓ������܂��B
	 */
	public static final NpcStringId VALAKASS_TAIL_FLAILS_DANGEROUSLY;

	/**
	 * ID: 1900166<br>
	 * Message: ���@���J�X���K�����͂ˏグ�܂��B
	 */
	public static final NpcStringId VALAKAS_RAISES_ITS_TAIL;

	/**
	 * ID: 1900167<br>
	 * Message: ���@���J�X���Ή��̋C���z�����n�߂܂��B
	 */
	public static final NpcStringId VALAKAS_STARTS_TO_ABSORB_THE_FLAME_ENERGY;

	/**
	 * ID: 1900168<br>
	 * Message: ���@���J�X�������̍������ɂ݂��܂��B
	 */
	public static final NpcStringId VALAKAS_LOOKS_TO_ITS_LEFT;

	/**
	 * ID: 1900169<br>
	 * Message: ���@���J�X�������̉E�����ɂ݂��܂��B
	 */
	public static final NpcStringId VALAKAS_LOOKS_TO_ITS_RIGHT;

	/**
	 * ID: 1900170<br>
	 * Message: �䂪���\�ɂĖ�����A�n������A���ƂȂ�B
	 */
	public static final NpcStringId BY_MY_AUTHORITY_I_COMMAND_YOU_CREATURE_TURN_TO_DUST;

	/**
	 * ID: 1900171<br>
	 * Message: �䂪�{������߂Ė�����A�n������A���_�𗐂��B
	 */
	public static final NpcStringId BY_MY_WRATH_I_COMMAND_YOU_CREATURE_LOSE_YOUR_MIND;

	/**
	 * ID: 1900172<br>
	 * Message: �׈��ȗ���|���A�A�f�� ���[���h��������p�Y�����ɏ̎^�𑗂�I
	 */
	public static final NpcStringId SHOW_RESPECT_TO_THE_HEROES_WHO_DEFEATED_THE_EVIL_DRAGON_AND_PROTECTED_THIS_ADEN_WORLD;

	/**
	 * ID: 1900173<br>
	 * Message: �p�Y�����̏������j���A�S���ŏ����̗Y���т�������I
	 */
	public static final NpcStringId SHOUT_TO_CELEBRATE_THE_VICTORY_OF_THE_HEROES;

	/**
	 * ID: 1900174<br>
	 * Message: �p�Y�����̋Ɛт��]���A�l�r�g�̏j�����󂯂�I
	 */
	public static final NpcStringId PRAISE_THE_ACHIEVEMENT_OF_THE_HEROES_AND_RECEIVE_NEVITS_BLESSING;

	/**
	 * ID: 1900175<br>
	 * Message: Ugh I think this is it for me�
	 */
	public static final NpcStringId UGH_I_THINK_THIS_IS_IT_FOR_ME;

	/**
	 * ID: 1900176<br>
	 * Message: Valakas calls forth the servitor's master.
	 */
	public static final NpcStringId VALAKAS_CALLS_FORTH_THE_SERVITORS_MASTER;

	/**
	 * ID: 1911111<br>
	 * Message: ���O�͂܂��Ȃ���X�̐��т��B���O���V�ʂƍ߈��ɂ܂݂ꂽ�ƌy�̂��Ă�܂Ȃ���X�̂ȁI
	 */
	public static final NpcStringId YOU_WILL_SOON_BECOME_THE_SACRIFICE_FOR_US_THOSE_FULL_OF_DECEIT_AND_SIN_WHOM_YOU_DESPISE;

	/**
	 * ID: 1911112<br>
	 * Message: ���̓��������O�𔱂���B���R�A��������������B����f���ċ������Ԃ��O�̎p�����������̂��B
	 */
	public static final NpcStringId MY_BRETHREN_WHO_ARE_STRONGER_THAN_ME_WILL_PUNISH_YOU_YOU_WILL_SOON_BE_COVERED_IN_YOUR_OWN_BLOOD_AND_CRYING_IN_ANGUISH;

	/**
	 * ID: 1911113<br>
	 * Message: ���̉����������Ƃ��ɂ����Ƃ́D�D�D�������D�D�D
	 */
	public static final NpcStringId HOW_COULD_I_LOSE_AGAINST_THESE_WORTHLESS_CREATURES;

	/**
	 * ID: 1911114<br>
	 * Message: �n���̗�΂̒��ł��O��҂��Ă���I
	 */
	public static final NpcStringId FOOLISH_CREATURES_THE_FLAMES_OF_HELL_ARE_DRAWING_CLOSER;

	/**
	 * ID: 1911115<br>
	 * Message: �����炠�������Ƃ��A���̒n�����O�̌��ŐԂ����܂�͎̂��Ԃ̖�肾�B
	 */
	public static final NpcStringId NO_MATTER_HOW_YOU_STRUGGLE_THIS_PLACE_WILL_SOON_BE_COVERED_WITH_YOUR_BLOOD;

	/**
	 * ID: 1911116<br>
	 * Message: ���̒n�ɑ��𓥂ݓ��ꂽ�҂͐����ċA��ʁI
	 */
	public static final NpcStringId THOSE_WHO_SET_FOOT_IN_THIS_PLACE_SHALL_NOT_LEAVE_ALIVE;

	/**
	 * ID: 1911117<br>
	 * Message: ��������I�n���̗�΂ŉi���̋ꂵ�݂𖡂킦�I
	 */
	public static final NpcStringId WORTHLESS_CREATURES_I_WILL_GRANT_YOU_ETERNAL_SLEEP_IN_FIRE_AND_BRIMSTONE;

	/**
	 * ID: 1911118<br>
	 * Message: ����Ȃɒn�����肪�������̂��I�]�݂ǂ���ɂ��Ă�낤�I
	 */
	public static final NpcStringId IF_YOU_WISH_TO_SEE_HELL_I_WILL_GRANT_YOU_YOUR_WISH;

	/**
	 * ID: 1911119<br>
	 * Message: �o�ߎ��ԁF
	 */
	public static final NpcStringId ELAPSED_TIME;

	/**
	 * ID: 1911120<br>
	 * Message: �c�莞�ԁF
	 */
	public static final NpcStringId TIME_REMAINING;

	/**
	 * ID: 10004431<br>
	 * Message: �A�f�������̏�̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004432<br>
	 * Message: �O���[�f�B�I��̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GLUDIO_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004433<br>
	 * Message: �f�B�I����̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_DION_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004434<br>
	 * Message: �M������̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GIRAN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004436<br>
	 * Message: �A�f����̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004437<br>
	 * Message: �C���i�h������̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_INNADRIL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004438<br>
	 * Message: �S�_�[�h��̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GODDARD_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004439<br>
	 * Message: ���E����̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_RUNE_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004440<br>
	 * Message: �V���`���b�c�K���g��̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_SCHUTTGART_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004441<br>
	 * Message: �G�����A�����̏�̎�������o�[����s�Ƀe���|�[�g�ő���܂��B
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ELMORE_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;
	
	/**
	 * Array containing all NpcStringId<br>
	 * Important: Always initialize with a length of the highest NpcStringId + 1!!!
	 */
	private static NpcStringId[] VALUES;
	
	static
	{
		HELLO_I_AM_S1_YOU_ARE_S2_RIGHT_HEHEHE = new NpcStringId(1);
		S1_S2_S3_S4_S5_HEHEHE = new NpcStringId(2);
		WITHDRAW_THE_FEE_FOR_THE_NEXT_TIME_AT_S1_S2_S4 = new NpcStringId(5);
		STAGE = new NpcStringId(8);
		STAGE_S1 = new NpcStringId(9);
		S1 = new NpcStringId(10);
		WHAT_DID_YOU_JUST_DO_TO_ME = new NpcStringId(2004);
		ARE_YOU_TRYING_TO_TAME_ME_DONT_DO_THAT = new NpcStringId(2005);
		DONT_GIVE_SUCH_A_THING_YOU_CAN_ENDANGER_YOURSELF = new NpcStringId(2006);
		YUCK_WHAT_IS_THIS_IT_TASTES_TERRIBLE = new NpcStringId(2007);
		IM_HUNGRY_GIVE_ME_A_LITTLE_MORE_PLEASE = new NpcStringId(2008);
		WHAT_IS_THIS_IS_THIS_EDIBLE = new NpcStringId(2009);
		DONT_WORRY_ABOUT_ME = new NpcStringId(2010);
		THANK_YOU_THAT_WAS_DELICIOUS = new NpcStringId(2011);
		I_THINK_I_AM_STARTING_TO_LIKE_YOU = new NpcStringId(2012);
		EEEEEK_EEEEEK = new NpcStringId(2013);
		DONT_KEEP_TRYING_TO_TAME_ME_I_DONT_WANT_TO_BE_TAMED = new NpcStringId(2014);
		IT_IS_JUST_FOOD_TO_ME_ALTHOUGH_IT_MAY_ALSO_BE_YOUR_HAND = new NpcStringId(2015);
		IF_I_KEEP_EATING_LIKE_THIS_WONT_I_BECOME_FAT_CHOMP_CHOMP = new NpcStringId(2016);
		WHY_DO_YOU_KEEP_FEEDING_ME = new NpcStringId(2017);
		DONT_TRUST_ME_IM_AFRAID_I_MAY_BETRAY_YOU_LATER = new NpcStringId(2018);
		GRRRRR = new NpcStringId(2019);
		YOU_BROUGHT_THIS_UPON_YOURSELF = new NpcStringId(2020);
		I_FEEL_STRANGE_I_KEEP_HAVING_THESE_EVIL_THOUGHTS = new NpcStringId(2021);
		ALAS_SO_THIS_IS_HOW_IT_ALL_ENDS = new NpcStringId(2022);
		I_DONT_FEEL_SO_GOOD_OH_MY_MIND_IS_VERY_TROUBLED = new NpcStringId(2023);
		S1_SO_WHAT_DO_YOU_THINK_IT_IS_LIKE_TO_BE_TAMED = new NpcStringId(2024);
		S1_WHENEVER_I_SEE_SPICE_I_THINK_I_WILL_MISS_YOUR_HAND_THAT_USED_TO_FEED_IT_TO_ME = new NpcStringId(2025);
		S1_DONT_GO_TO_THE_VILLAGE_I_DONT_HAVE_THE_STRENGTH_TO_FOLLOW_YOU = new NpcStringId(2026);
		THANK_YOU_FOR_TRUSTING_ME_S1_I_HOPE_I_WILL_BE_HELPFUL_TO_YOU = new NpcStringId(2027);
		S1_WILL_I_BE_ABLE_TO_HELP_YOU = new NpcStringId(2028);
		I_GUESS_ITS_JUST_MY_ANIMAL_MAGNETISM = new NpcStringId(2029);
		TOO_MUCH_SPICY_FOOD_MAKES_ME_SWEAT_LIKE_A_BEAST = new NpcStringId(2030);
		ANIMALS_NEED_LOVE_TOO = new NpcStringId(2031);
		WHATD_I_MISS_WHATD_I_MISS = new NpcStringId(2032);
		I_JUST_KNOW_BEFORE_THIS_IS_OVER_IM_GONNA_NEED_A_LOT_OF_SERIOUS_THERAPY = new NpcStringId(2033);
		I_SENSE_GREAT_WISDOM_IN_YOU_IM_AN_ANIMAL_AND_I_GOT_INSTINCTS = new NpcStringId(2034);
		REMEMBER_IM_HERE_TO_HELP = new NpcStringId(2035);
		ARE_WE_THERE_YET = new NpcStringId(2036);
		THAT_REALLY_MADE_ME_FEEL_GOOD_TO_SEE_THAT = new NpcStringId(2037);
		OH_NO_NO_NO_NOOOOO = new NpcStringId(2038);
		WHO_AWOKE_ME = new NpcStringId(2150);
		MY_MASTER_HAS_INSTRUCTED_ME_TO_BE_YOUR_GUIDE_S1 = new NpcStringId(2151);
		PLEASE_CHECK_THIS_BOOKCASE_S1 = new NpcStringId(2152);
		DID_YOU_CALL_ME_S1 = new NpcStringId(2250);
		IM_CONFUSED_MAYBE_ITS_TIME_TO_GO_BACK = new NpcStringId(2251);
		THAT_SIGN = new NpcStringId(2450);
		THAT_BOX_WAS_SEALED_BY_MY_MASTER_S1_DONT_TOUCH_IT = new NpcStringId(2550);
		YOUVE_ENDED_MY_IMMORTAL_LIFE_YOURE_PROTECTED_BY_THE_FEUDAL_LORD_ARENT_YOU = new NpcStringId(2551);
		DELIVERY_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE = new NpcStringId(4151);
		ACQUISITION_OF_SOULSHOT_FOR_BEGINNERS_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE = new NpcStringId(4152);
		ACQUISITION_OF_WEAPON_EXCHANGE_COUPON_FOR_BEGINNERS_COMPLETE_N_GO_SPEAK_WITH_THE_NEWBIE_GUIDE = new NpcStringId(4153);
		ACQUISITION_OF_RACE_SPECIFIC_WEAPON_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE = new NpcStringId(4154);
		LAST_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE = new NpcStringId(4155);
		S1_I_MUST_KILL_YOU_BLAME_YOUR_OWN_CURIOSITY = new NpcStringId(6051);
		YOU_HAVE_GOOD_LUCK_I_SHALL_RETURN = new NpcStringId(6052);
		YOU_ARE_STRONG_THIS_WAS_A_MISTAKE = new NpcStringId(6053);
		WHO_ARE_YOU_TO_JOIN_IN_THE_BATTLE_HOW_UPSETTING = new NpcStringId(6054);
		S1_DID_YOU_COME_TO_HELP_ME = new NpcStringId(6451);
		DRATS_HOW_COULD_I_BE_SO_WRONG = new NpcStringId(6551);
		S1_STEP_BACK_FROM_THE_CONFOUNDED_BOX_I_WILL_TAKE_IT_MYSELF = new NpcStringId(6552);
		S1_I_WILL_BE_BACK_SOON_STAY_THERE_AND_DONT_YOU_DARE_WANDER_OFF = new NpcStringId(6553);
		GRR_IVE_BEEN_HIT = new NpcStringId(6554);
		GRR_WHO_ARE_YOU_AND_WHY_HAVE_YOU_STOPPED_ME = new NpcStringId(6555);
		I_AM_LATE = new NpcStringId(6556);
		GOOD_LUCK = new NpcStringId(6557);
		S1_YOU_SEEK_THE_FORBIDDEN_KNOWLEDGE_AND_I_CANNOT_LET_YOU_HAVE_IT = new NpcStringId(6750);
		IS_THIS_ALL_I_AM_ALLOWED_TO_HAVE = new NpcStringId(6751);
		YOU_DEFEATED_ME_BUT_OUR_DOOM_APPROACHES = new NpcStringId(6752);
		S1_WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS = new NpcStringId(6753);
		BEEFCAKE = new NpcStringId(6754);
		GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS = new NpcStringId(6755);
		FAREWELL_AND_WATCH_YOUR_BACK = new NpcStringId(6756);
		KAMAEL_GOOD_TO_SEE_YOU_I_HAVE_SOMETHING_TO_ASK_YOU = new NpcStringId(6757);
		S1_GO_GET_HIM = new NpcStringId(6758);
		S1_WHAT_ARE_YOU_DOING_ATTACK_HIM = new NpcStringId(6759);
		S1_IS_YOUR_FULL_POTENTIAL = new NpcStringId(6760);
		THANKS_I_MUST_GO_AND_HUNT_DOWN_THOSE_THAT_OPPOSE_ME = new NpcStringId(6761);
		YOU_ARE_SO_STUBBORN_I_MUST_FOLLOW_HIM_NOW = new NpcStringId(6762);
		SEEK_ENLIGHTENMENT_FROM_THE_TABLET = new NpcStringId(6763);
		ARROGANT_BEINGS_YOU_ARE_ALL_DOOMED = new NpcStringId(6764);
		MY_TIME_IN_YOUR_WORLD_HAS_COME_TO_AN_END_CONSIDER_YOURSELVES_LUCKY = new NpcStringId(6765);
		S1_HOW_DARE_YOU = new NpcStringId(6766);
		S1_AHHAA_YOUR_GOD_FORSAKES_YOU = new NpcStringId(6767);
		S1_YOUR_TIME_IS_UP_PREPARE_TO_DIE_A_HORRIBLE_DEATH = new NpcStringId(6851);
		CONSIDER_YOURSELF_LUCKY_THE_NEXT_TIME_WE_MEET_YOU_WILL_DIE_PERMANENTLY = new NpcStringId(6852);
		FARE_THEE_WELL_WE_SHALL_MEET_AGAIN = new NpcStringId(6853);
		S1_WHO_ARE_YOU_AND_BETTER_YET_WHY_ARE_YOU_BOTHERING_MY_MINIONS = new NpcStringId(6854);
		STRENGTH_BEYOND_STRENGTH = new NpcStringId(6855);
		GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_WHERE_IT_DOESNT_BELONG = new NpcStringId(6856);
		YOUVE_WON_FOR_NOW_BUT_WE_WILL_MEET_AGAIN = new NpcStringId(6857);
		ARE_THEY_TIRED_OF_FOLLOWING_ME = new NpcStringId(6858);
		S1_CAN_YOU_HELP_ME = new NpcStringId(6859);
		IS_THAT_ALL_YOU_GOT_LITTLE_S1 = new NpcStringId(6860);
		S1_WAKE_UP_FOOL_DONT_LET_HIM_GET_AWAY = new NpcStringId(6861);
		THE_PATH_IS_CLEAR_BUT_BE_CAREFUL = new NpcStringId(6862);
		I_MUST_FOLLOW_SOMEONE_NOW_SEE_YOU_AROUND = new NpcStringId(6863);
		MAY_WE_MEET_AGAIN = new NpcStringId(6864);
		CURSES_ON_THOSE_WHO_BLASPHEME_THE_GODS = new NpcStringId(6865);
		EINHASAD_IS_CALLING_ME_YOU_ARE_LUCKY_AND_I_SHALL_CONTINUE_MY_PUNISHMENT_LATER = new NpcStringId(6866);
		BY_THE_POWER_VESTED_IN_ME_BY_THE_GODS_YOU_S1_SHALL_DIE = new NpcStringId(6867);
		S1_I_WILL_NOT_FORGET_YOU = new NpcStringId(6868);
		S1_HOW_DARE_YOU_INTERFERE_YOU_SHALL_PAY_FOR_THIS = new NpcStringId(6950);
		BELETH_IS_CALLING_ME_YOU_ARE_LUCKY_BUT_STILL_A_FOOL = new NpcStringId(6951);
		I_SHALL_TAKE_MY_LEAVE_BUT_WILL_NEVER_SURRENDER = new NpcStringId(6952);
		COWER_BEFORE_MY_AWESOME_AND_MIGHTY_POWER = new NpcStringId(6954);
		GRR_CANT_YOU_FIND_SOMETHING_BETTER_TO_DO_WITH_YOUR_TIME = new NpcStringId(6955);
		I_SHALL_TAKE_MY_LEAVE_BUT_YOUR_HEAD_WILL_BE_MINE_SOME_DAY_OH_YESYES_IT_WILL = new NpcStringId(6956);
		MY_CHILDREN_ARE_STRONGER = new NpcStringId(6957);
		S1_LETS_KILL_THEM_ALL = new NpcStringId(6958);
		S1_COME_MY_CHILDREN = new NpcStringId(6959);
		S1_MUSTER_YOUR_STRENGTH_PICK_THEM_OFF_LIKE_CHICKENS = new NpcStringId(6960);
		THANK_YOU_MY_CHILDREN_SOMEDAY_WE_WILL_MEET_AGAIN = new NpcStringId(6961);
		MY_CHILDREN_SEEK_MY_ENEMIES = new NpcStringId(6962);
		MY_CHILDREN_I_GRANT_YOU_MY_BLESSINGS = new NpcStringId(6963);
		YOU_WORTHLESS_BEINGS = new NpcStringId(6964);
		MY_TIME_ON_THE_MATERIAL_PLANE_HAS_ENDED_YOU_ARE_LUCKY = new NpcStringId(6965);
		S1_WORTHLESS_BEINGS_BEGONE = new NpcStringId(6966);
		S1_YOU_ARE_THE_MEANING_OF_THE_WORD_DANGER = new NpcStringId(6967);
		YOU_MADE_IT_HERE_S1_ILL_SHOW_MY_STRENGTH_DIE = new NpcStringId(7050);
		HA_YOU_FAILED_ARE_YOU_READY_TO_QUIT = new NpcStringId(7051);
		IM_THE_STRONGEST_I_LOST_EVERYTHING_TO_WIN = new NpcStringId(7052);
		S1_ARE_YOU_DOING_THIS_BECAUSE_THEYRE_HALISHAS_MINIONS = new NpcStringId(7053);
		MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_IM_GETTING_CLOSE_TO_HALISHA = new NpcStringId(7054);
		MIND_YOUR_OWN_BUSINESS = new NpcStringId(7055);
		THIS_FIGHT_IS_A_WASTE_OF_TIME_GOODBYE = new NpcStringId(7056);
		EVERY_CLOUD_HAS_A_SILVER_LINING_DONT_YOU_THINK = new NpcStringId(7057);
		S1_DONT_LISTEN_TO_THIS_DEMON = new NpcStringId(7058);
		S1_HESITATION_BETRAYS_YOUR_HEART_FIGHT = new NpcStringId(7059);
		S1_ISNT_PROTECTING_SOMEBODY_WORTHY_ISNT_THAT_JUSTICE = new NpcStringId(7060);
		I_HAVE_URGENT_BUSINESS_I_GOTTA_GO = new NpcStringId(7061);
		ARE_MY_EFFORTS_IN_VAIN_IS_THIS_THE_END = new NpcStringId(7062);
		GOODBYE_FRIEND_I_HOPE_TO_SEE_YOU_AGAIN = new NpcStringId(7063);
		KNIGHTS_ARE_ALWAYS_FOOLISH = new NpcStringId(7064);
		ILL_SHOW_MERCY_ON_YOU_FOR_NOW = new NpcStringId(7065);
		S1_YOUR_JUSTICE_IS_JUST_HYPOCRISY_IF_YOU_GIVE_UP_ON_WHAT_YOUVE_PROMISED_TO_PROTECT = new NpcStringId(7066);
		S1DONT_THINK_YOUVE_WON_YOUR_DARK_SHADOW_WILL_ALWAYS_FOLLOW_YOUHYPOCRITE = new NpcStringId(7067);
		A_TEMPLE_KNIGHT_GUARDS_THE_MOTHER_TREE_S1_HAS_HUMAN_CONTACT_MADE_YOU_FORGET_THAT = new NpcStringId(7150);
		I_MUST_STOP_REMEMBER_THE_ONES_YOURE_PROTECTING_WILL_SOMEDAY_DEFEAT_THE_ELVES = new NpcStringId(7151);
		WHAT_THAT_WILL_JUST_STRENGTHEN_THE_ENEMY = new NpcStringId(7152);
		YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1 = new NpcStringId(7153);
		MY_SPIRIT_IS_RELEASING_FROM_THIS_SHELL_IM_GETTING_CLOSE_TO_HALISHA = new NpcStringId(7154);
		THIS_IS_A_WASTE_OF_TIME_GOODBYE = new NpcStringId(7156);
		IM_NOT_LIKE_MY_BROTHER_PANACEA_GHOST_OF_THE_PAST_BEGONE = new NpcStringId(7157);
		THE_ELVES_NO_LONGER_RULE_HELP_ME_S1 = new NpcStringId(7158);
		DONT_GIVE_UP_S1_HE_A_DEMON_FROM_THE_PAST = new NpcStringId(7159);
		BE_PROUD_S1_WE_PROTECT_THIS_WORLD_TOGETHER = new NpcStringId(7160);
		I_HAVE_TO_GO_IVE_GOT_SOME_BUSINESS_TO_TAKE_CARE_OF = new NpcStringId(7161);
		UGH_DONT_LET_HIM_GET_AWAY = new NpcStringId(7162);
		DONT_FORGET_YOUR_PRIDE_YOURE_PROTECTING_THE_WORLD = new NpcStringId(7163);
		HA_HA_HA = new NpcStringId(7164);
		KUH_HUH = new NpcStringId(7165);
		AAH_KUHS1KUH_HUH = new NpcStringId(7166);
		S1RE_MEM_UGHUH = new NpcStringId(7167);
		S1_YOUD_BETTER_LISTEN = new NpcStringId(7250);
		HUH_ITS_CURTAIN_TIME_I_WONT_GET_ANY_MONEY = new NpcStringId(7251);
		UGHIT_CANT_BE = new NpcStringId(7252);
		YOU_WONT_GET_AWAY_THIS_TIME_NARCISSUS = new NpcStringId(7257);
		S1_HELP_ME = new NpcStringId(7258);
		YOU_MUST_BE_AWARE_OF_YOUR_AUDIENCE_WHEN_SINGING_S_JOIN_US_S1_A_SONG_THAT_NOBODY_LISTENS_TO_IS_EMPTY = new NpcStringId(7259);
		YOU_MUST_WORK_HARDER_TO_BE_VICTORIOUS_S1 = new NpcStringId(7260);
		MY_SONG_IS_OVER_I_MUST_GO_GOODBYE = new NpcStringId(7261);
		HOW_COULD_I_MISS = new NpcStringId(7262);
		DONT_FORGET_SONG_COMES_WITH_HARMONY = new NpcStringId(7263);
		SING_EVERYONE_WILL_LISTEN = new NpcStringId(7264);
		YOU_DONT_DESERVE_MY_BLESSING = new NpcStringId(7265);
		DO_YOU_REJECT_MY_BLESSING_S1 = new NpcStringId(7266);
		BUT_WHY_S1_EVERYONE_WOULD_PRAISE_YOU = new NpcStringId(7267);
		S1_ATTACK_ME_IM_IMMORTAL_IM_UNRIVALED = new NpcStringId(7350);
		HA_IM_IMMORTAL_THIS_SCAR_WILL_SOON_HEAL_YOULL_DIE_NEXT_TIME = new NpcStringId(7351);
		METELLUS_YOU_PROMISED_ME_AN_IMMORTAL_LIFE_HOW_COULD_I_SWORDMASTER_IRON_LOSE = new NpcStringId(7352);
		FALLEN_ANGEL_ITS_WORTH_TRYING = new NpcStringId(7357);
		HEY_S1_WHY_DONT_YOU_JOIN_ITS_YOUR_BEST_SHOT = new NpcStringId(7358);
		ARE_YOU_INTERESTED_IN_IMMORTAL_LIFE_S1_ITS_TOO_BORING_FOR_ME = new NpcStringId(7359);
		EXCELLENT_S1_SHOW_ME_WHAT_YOU_LEARNED_WHEN_YOUR_LIFE_WAS_ON_THE_LINE = new NpcStringId(7360);
		I_HAVE_TO_GO_TAKE_A_BREAK = new NpcStringId(7361);
		YOU_MISSED_WHAT_A_FOOL_YOU_ARE = new NpcStringId(7362);
		FIGHTING_WITHOUT_RISK_TRAINING_WITHOUT_DANGER_AND_GROWING_WITHOUT_HARDSHIP_WILL_ONLY_LEAD_TO_AN_INFLATED_EGO_DONT_FORGET = new NpcStringId(7363);
		DO_YOU_WANT_AN_IMMORTAL_LIFE = new NpcStringId(7364);
		THINK_IT_OVER_AN_IMMORTAL_LIFE_AND_ASSURED_VICTORY = new NpcStringId(7365);
		THATS_GOOD_S1_DO_YOU_WANT_MY_BLESSING_TO_IMPROVE_YOUR_SKILLS = new NpcStringId(7366);
		S1_WHY_DO_YOU_REJECT_GUARANTEED_VICTORY = new NpcStringId(7367);
		IN_THE_NAME_OF_GODS_I_PUNISH_YOU_S1_YOU_CANT_RIVAL_US_ALL_NO_MATTER_HOW_STRONG_YOU_THINK_YOU_ARE = new NpcStringId(7450);
		I_HAVE_TO_STOP_FOR_NOW_BUT_ILL_DEFEAT_THE_POWER_OF_THE_DRAGON_YET = new NpcStringId(7451);
		IT_ISTHE_POWER_THAT_SHOULDNT_LIVE = new NpcStringId(7452);
		ISNT_IT_UNWISE_FOR_AN_ANGEL_TO_INTERFERE_IN_HUMAN_AFFAIRS = new NpcStringId(7457);
		THIS_IS_TOUGH_S1_WOULD_YOU_HELP_ME = new NpcStringId(7458);
		S1_HES_KEEPING_AN_EYE_ON_ALL_THOSE_IN_SUCCESSION_TO_THE_BLOOD_OF_DRAGONS_INCLUDING_YOU = new NpcStringId(7459);
		ATTACK_THE_REAR_S1_IF_IM_KILLED_YOURE_NEXT = new NpcStringId(7460);
		I_CANT_STAY_ANY_LONGER_THERE_ARE_TOO_MANY_EYES_ON_US_FAREWELL = new NpcStringId(7461);
		GET_AWAY_YOURE_STILL_ALIVE_BUT = new NpcStringId(7462);
		IF_WE_CANT_AVOID_THIS_FIGHT_WELL_NEED_GREAT_STRENGTH_ITS_THE_ONLY_WAY_TO_PEACE = new NpcStringId(7463);
		WARLORD_YOULL_NEVER_LEARN_THE_TECHNIQUES_OF_THE_DRAGON = new NpcStringId(7464);
		HEY_WHY_BOTHER_WITH_THIS_ISNT_IT_YOUR_MOTHERS_BUSINESS = new NpcStringId(7465);
		S1_ARE_YOU_AGAINST_YOUR_MOTHERS_WISHES_YOURE_NOT_WORTHY_OF_THE_SECRETS_OF_SHILENS_CHILDREN = new NpcStringId(7466);
		EXCELLENT_TECHNIQUE_S1_UNFORTUNATELY_YOURE_THE_ONE_DESTINED_FOR_TRAGEDY = new NpcStringId(7467);
		S1_YOU_MAY_FOLLOW_ME_BUT_AN_ORC_IS_NO_MATCH_FOR_MY_GIANTS_STRENGTH = new NpcStringId(7550);
		KUHMY_BODY_FAILSTHIS_IS_THE_END = new NpcStringId(7551);
		HOW_COULD_I_LOSE_WITH_THE_POWERS_OF_A_GIANT_AARGH = new NpcStringId(7552);
		THATS_THE_ENEMY = new NpcStringId(7557);
		HMM_S1_WILL_YOU_JUST_STAND_THERE_DOING_NOTHING = new NpcStringId(7558);
		S1_NOTHING_RISKED_NOTHING_GAINED_ONLY_THOSE_WHO_FIGHT_ENJOY_THE_SPOILS_OF_VICTORY = new NpcStringId(7559);
		A_SWORD_ISNT_JEWELRY_S1_DONT_YOU_AGREE = new NpcStringId(7560);
		WITH_NO_FIGHT_I_HAVE_NO_REASON_TO_STAY = new NpcStringId(7561);
		MISS = new NpcStringId(7562);
		PICK_YOUR_BATTLES_WISELY_OR_YOULL_REGRET_IT = new NpcStringId(7563);
		WHAT_A_FOOL_TO_CHALLENGE_THE_GIANT_OF_THE_OROKA_TRIBE = new NpcStringId(7564);
		RUNNING_LOW_ON_STEAM_I_MUST_WITHDRAW = new NpcStringId(7565);
		S1_YOURE_THE_ONE_WHO_DEFEATED_GUARDIAN_MUHARK = new NpcStringId(7566);
		S1_I_MUST_SUCCEED = new NpcStringId(7567);
		S1_WOULD_YOU_FIGHT_URUZ_WHO_HAS_REACHED_THE_POWER_OF_AZIRA = new NpcStringId(7650);
		I_CANT_HANDLE_THE_POWER_OF_AZIRA_YET_FIRST = new NpcStringId(7651);
		THIS_CANT_BE_HAPPENING_I_HAVE_THE_POWER_OF_AZIRA_HOW_COULD_I_FALL_SO_EASILY = new NpcStringId(7652);
		AZIRA_BORN_FROM_THE_EVIL_FLAME_ILL_KILL_YOU_WITH_MY_BARE_HANDS = new NpcStringId(7657);
		S1_IN_THE_NAME_OF_KHAVATARI_HUBAI_STRIKE_THIS_EVIL_WITH_YOUR_FISTS = new NpcStringId(7658);
		S1_ATTACK_FROM_BOTH_SIDES_HIT_HARD = new NpcStringId(7659);
		S1_STRIKE_WITH_ALL_YOUR_HEART_IT_MUST_WORK = new NpcStringId(7660);
		DAMN_ITS_TIME_TO_GO_UNTIL_NEXT_TIME = new NpcStringId(7661);
		EVIL_SPIRIT_OF_FLAME_I_WONT_GIVE_UP = new NpcStringId(7662);
		MY_FIST_WORKS_EVEN_ON_THE_EVIL_SPIRIT_DONT_FORGET = new NpcStringId(7663);
		FOOLISH_KHAVATARIDO_YOU_THINK_YOUR_POWER_WILL_WORK_ON_ME_IM_THE_SOURCE_OF_YOUR_MARTIAL_ART = new NpcStringId(7664);
		NO_MORE_GAMES = new NpcStringId(7665);
		S1ARE_YOU_NEXT_AFTER_KHAVATARI_DO_YOU_KNOW_WHO_I_AM = new NpcStringId(7666);
		S1KASHU_NOT_A_BAD_ATTACK_I_CANT_HOLD_ON_MUCH_LONGER = new NpcStringId(7667);
		S1_AKKAN_YOU_CANT_BE_MY_RIVAL_ILL_KILL_EVERYTHING_ILL_BE_THE_KING = new NpcStringId(7750);
		HA_ILL_SHOW_MERCY_ON_YOU_THIS_TIME_I_KNOW_WELL_OF_YOUR_TECHNIQUE = new NpcStringId(7751);
		I_HAVE_IN_ME_THE_BLOOD_OF_A_KING_HOW_COULD_I_LOSE = new NpcStringId(7752);
		ARE_YOUTYRANT = new NpcStringId(7757);
		YOURE_NOT_A_KING_YOURE_JUST_A_TYRANT_S1_WE_MUST_FIGHT_TOGETHER = new NpcStringId(7758);
		SUCH_RULE_IS_RUINING_THE_COUNTRY_S1_I_CANT_BEAR_THIS_TYRANNY_ANY_LONGER = new NpcStringId(7759);
		S1_LEADERS_MUST_ALWAYS_RESIST_TYRANNY = new NpcStringId(7760);
		I_STAYED_TOO_LONG_ILL_BE_PUNISHED_FOR_BEING_AWAY_SO_LONG = new NpcStringId(7761);
		HE_GOT_AWAY_DAMMIT_WE_MUST_CATCH_THIS_DARK_SOUL = new NpcStringId(7762);
		WHAT_IS_A_KING_WHAT_MUST_ONE_DO_TO_BE_A_GOOD_KING_THINK_IT_OVER = new NpcStringId(7763);
		KNEEL_DOWN_BEFORE_ME_FOOLISH_PEOPLE = new NpcStringId(7764);
		ITS_TIME_FOR_THE_KING_TO_LEAVE_BOW_YOUR_HEAD_AND_SAY_GOODBYE = new NpcStringId(7765);
		S1_YOU_DARE_TO_FIGHT_ME_A_KINGS_POWER_MUST_BE_GREAT_TO_RULE = new NpcStringId(7766);
		YOU_WOULD_FIGHT_THE_KING_S1_TRAITOR = new NpcStringId(7767);
		TEJAKAR_SHARUHI_S1_ILL_SHOW_YOU_THE_POWER_OF_SHARUHI_MOUTH_MUDAHA = new NpcStringId(7850);
		AAARGH_MY_SOUL_WONT_KEEP_QUIET_NOW_I_MUST_TAKE_MY_LEAVE = new NpcStringId(7851);
		NO_SHARUHI_YOURE_SOUL_IS_MINE = new NpcStringId(7852);
		TEJAKAR_OROCA_TEJAKAR_DUDA_MARA = new NpcStringId(7857);
		S1_WE_MUST_FIGHT_THIS_SOUL_TOGETHER_TO_PREVENT_AN_EVERLASTING_WINTER = new NpcStringId(7858);
		S1_THE_SOUL_RESPONDS_TO_YOU_MAY_YOUR_ATTACK_QUIET_HIM = new NpcStringId(7859);
		S1_CALM_SHARUHI_HE_DOESNT_LISTEN_TO_ME_ANYMORE = new NpcStringId(7860);
		ITS_TIME_TO_GO_MAY_THE_ETERNAL_FLAME_BLESS_YOU = new NpcStringId(7861);
		HE_LEFTTHATS_TOO_BADTOO_BAD = new NpcStringId(7862);
		DONT_FORGET_YOUR_STRONG_WILL_NOW = new NpcStringId(7863);
		HA_NOBODY_WILL_RULE_OVER_ME_ANYMORE = new NpcStringId(7864);
		FREEDOM_FREEDOM_FREEDOM = new NpcStringId(7865);
		S1_YOU_RELEASED_ME_BUT_YOU_ALSO_WANT_TO_CATCH_ME_HA = new NpcStringId(7866);
		S1MEALL_RIGHTILL_HELP_YOU = new NpcStringId(7867);
		GET_OUT_OF_HERE_THIS_PLACE_IS_FORBIDDEN_BY_GOD = new NpcStringId(7950);
		EINHASAD_IS_CALLING_ME = new NpcStringId(7951);
		YOU_KILLED_ME_ARENT_YOU_AFRAID_OF_GODS_CURSE = new NpcStringId(7952);
		YOU_BOTHER_MY_MINIONS_S1_DO_YOU_WANT_TO_DIE = new NpcStringId(7953);
		WHAT_THE_HELL_I_LOST = new NpcStringId(7954);
		WHO_ARE_YOU_WHY_ARE_YOU_INTERFERING_IN_OUR_BUSINESS = new NpcStringId(7955);
		YOURE_STRONG_ILL_GET_YOU_NEXT_TIME = new NpcStringId(7956);
		WE_MEET_AGAIN_ILL_HAVE_YOU_THIS_TIME = new NpcStringId(7957);
		A_WORTHY_OPPONENT_S1_HELP_ME = new NpcStringId(7958);
		S1_HURRY_BEFORE_HE_GETS_AWAY = new NpcStringId(7959);
		ILL_KILL_YOU = new NpcStringId(7960);
		WHY_DONT_YOU_FIGHT_ME_SOMEDAY = new NpcStringId(7961);
		I_MISSED_AGAIN_DAMMIT = new NpcStringId(7962);
		IM_SURE_WELL_MEET_AGAIN_SOMEDAY = new NpcStringId(7963);
		CURSE_THOSE_WHO_DEFY_THE_GODS = new NpcStringId(7964);
		YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS = new NpcStringId(7966);
		S1_I_WONT_FORGET_YOU = new NpcStringId(7967);
		S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE = new NpcStringId(8050);
		LEAVE_BEFORE_YOU_ARE_SEVERELY_PUNISHED = new NpcStringId(8051);
		EINHASAD_DONT_GIVE_UP_ON_ME = new NpcStringId(8052);
		S1_SO_YOURE_THE_ONE_WHOS_LOOKING_FOR_ME = new NpcStringId(8053);
		A_MERE_MORTAL_HAS_DEFEATED_ME = new NpcStringId(8054);
		HOW_COWARDLY_TO_INTRUDE_IN_OTHER_PEOPLES_BUSINESS = new NpcStringId(8055);
		TIME_IS_UP = new NpcStringId(8056);
		ILL_KILL_YOU_WITH_MY_SWORD = new NpcStringId(8057);
		HELP_ME = new NpcStringId(8058);
		DONT_MISS = new NpcStringId(8059);
		KEEP_PUSHING = new NpcStringId(8060);
		ILL_GET_HIM_YOULL_HAVE_YOUR_REVENGE = new NpcStringId(8061);
		I_MISSED_HIM_AGAIN_ILL_KILL_YOU = new NpcStringId(8062);
		I_MUST_FOLLOW_HIM = new NpcStringId(8063);
		S1_YOU_SHOULD_LEAVE_IF_YOU_FEAR_GODS_WRATH = new NpcStringId(8150);
		WHATS_GOING_ON = new NpcStringId(8151);
		ILL_SEE_YOU_AGAIN = new NpcStringId(8152);
		WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS = new NpcStringId(8153);
		NO_WAY = new NpcStringId(8154);
		WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS = new NpcStringId(8155);
		WHO_ARE_YOU_HOW_CAN_A_CREATURE_FROM_THE_NETHERWORLD_BE_SO_POWERFUL = new NpcStringId(8156);
		IS_THIS_THE_END = new NpcStringId(8157);
		SHOW_ME_WHAT_YOURE_MADE_OF_KILL_HIM = new NpcStringId(8158);
		YOU_THINK_YOU_CAN_GET_HIM_WITH_THAT = new NpcStringId(8159);
		PULL_YOURSELF_TOGETHER_HES_TRYING_TO_GET_AWAY = new NpcStringId(8160);
		TELL_THE_BLACK_CAT_THAT_I_GOT_HIS_PAID_BACK = new NpcStringId(8161);
		BLACK_CAT_HELL_BLAME_ME = new NpcStringId(8162);
		I_GOTTA_GO_NOW = new NpcStringId(8163);
		ILL_KILL_YOU_IN_THE_NAME_OF_GOD = new NpcStringId(8166);
		S1_SEE_YOU_LATER = new NpcStringId(8167);
		GET_OUT_BEFORE_YOURE_PUNISHED = new NpcStringId(8251);
		EINHASAD_PLEASE_DONT_GIVE_UP_ON_ME = new NpcStringId(8252);
		S1_ARE_YOU_LOOKING_FOR_ME = new NpcStringId(8253);
		A_MERE_MORTAL_IS_KILLING_ME = new NpcStringId(8254);
		MORTAL_DONT_YOU_RECOGNIZE_MY_GREATNESS = new NpcStringId(8256);
		ILL_GET_YOU_THIS_TIME = new NpcStringId(8257);
		ILL_NEVER_FORGET_THE_TASTE_OF_HIS_STEEL_S1_LETS_FIGHT_HIM_TOGETHER = new NpcStringId(8258);
		S1_PULL_YOURSELF_TOGETHER_WELL_MISS_HIM = new NpcStringId(8259);
		S1_HES_TRYING_TO_GET_AWAY = new NpcStringId(8260);
		I_MISSED_AGAIN_NEXT_TIME = new NpcStringId(8261);
		DAMMIT_FAILED_AGAIN = new NpcStringId(8262);
		YOU_ARE_THE_ONE_WHOS_LOOKING_FOR_ME_S1 = new NpcStringId(8353);
		A_MERE_MORTAL_HAS_KILLED_ME = new NpcStringId(8354);
		WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS = new NpcStringId(8355);
		S1_PULL_YOURSELF_TOGETHER = new NpcStringId(8359);
		S1_HELL_GET_AWAY = new NpcStringId(8360);
		EINHASAD_PLEASE_DONT_FORSAKE_ME = new NpcStringId(8452);
		LOOKING_FOR_ME_S1 = new NpcStringId(8453);
		S1_BISHOP_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD = new NpcStringId(8550);
		YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_PAY_YOU_BACK_NEXT_TIME = new NpcStringId(8551);
		TANAKIA_FORGIVE_ME_I_COULDNT_FULFILL_YOUR_DREAM = new NpcStringId(8552);
		S1_YOU_ARE_THE_WON_WHOS_BEEN_BOTHERING_MY_MINIONS = new NpcStringId(8553);
		DAMN_YOUVE_BEATEN_ME = new NpcStringId(8554);
		WHO_ARE_YOU_THIS_ISNT_YOUR_BUSINESS_COWARD = new NpcStringId(8555);
		HOW_WEAK_ILL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH = new NpcStringId(8556);
		YOU_ARE_STRONGER_THAN_I_THOUGHT_BUT_IM_NO_WEAKLING = new NpcStringId(8557);
		HES_GOT_A_TOUGH_SHELL_S1_LETS_FIGHT_TOGETHER_AND_CRACK_HIS_SKULL = new NpcStringId(8558);
		S1_WE_WONT_BEAT_HIM_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON = new NpcStringId(8560);
		ILL_FOLLOW_HIM = new NpcStringId(8561);
		I_MISSED_AGAIN_HES_HARD_TO_FOLLOW = new NpcStringId(8562);
		WELL_SEE_WHAT_THE_FUTURE_BRINGS = new NpcStringId(8563);
		FOR_SHILEN = new NpcStringId(8564);
		ILL_BE_BACK_ILL_DEAL_WITH_YOU_THEN = new NpcStringId(8565);
		S1_ARE_YOU_GOING_TO_FIGHT_ME = new NpcStringId(8566);
		S1_ILL_PAY_YOU_BACK_I_WONT_FORGET_YOU = new NpcStringId(8567);
		S1_PROPHET_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD = new NpcStringId(8650);
		YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_DEAL_WITH_YOU_NEXT_TIME = new NpcStringId(8651);
		ARE_YOU_THE_ONE_WHOS_BEEN_BOTHERING_MY_MINIONS_S1 = new NpcStringId(8653);
		DAMN_I_CANT_BELIEVE_IVE_BEEN_BEATEN_BY_YOU = new NpcStringId(8654);
		WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS_COWARD = new NpcStringId(8655);
		ILL_DESTROY_THE_DARKNESS_SURROUNDING_THE_WORLD_WITH_THE_POWER_OF_LIGHT = new NpcStringId(8657);
		S1_FIGHT_THE_FALLEN_ANGEL_WITH_ME_SHOW_THE_TRUE_POWER_OF_LIGHT = new NpcStringId(8658);
		S1_GO_WE_MUST_STOP_FIGHTING_HERE = new NpcStringId(8659);
		WE_MUSTNT_LOSE_S1_PULL_YOURSELF_TOGETHER = new NpcStringId(8660);
		WELL_MEET_AGAIN_IF_FATE_WILLS_IT = new NpcStringId(8661);
		ILL_FOLLOW_THE_COWARDLY_DEVIL = new NpcStringId(8662);
		S1_ELDER_ITS_FOOLISH_OF_YOU_TO_GO_AGAINST_THE_WILL_OF_THE_GODS = new NpcStringId(8750);
		YOURE_STRONGER_THAN_I_THOUGHT_BUT_IM_NO_WEAKLING_EITHER = new NpcStringId(8757);
		S1_WELL_NEVER_WIN_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON = new NpcStringId(8760);
		ARE_YOU_S1_OH_I_HAVE_THE_RESONANCE_AMULET = new NpcStringId(8850);
		YOURE_FEISTIER_THAN_I_THOUGHT_ILL_QUIT_HERE_FOR_TODAY = new NpcStringId(8851);
		AAARGH_I_CANT_BELIEVE_I_LOST = new NpcStringId(8852);
		YIKES_YOURE_TOUGH = new NpcStringId(8854);
		WHY_DO_YOU_INTERFERE_IN_OTHER_PEOPLES_BUSINESS = new NpcStringId(8855);
		ILL_STOP_HERE_FOR_TODAY = new NpcStringId(8856);
		I_WONT_MISS_YOU_THIS_TIME = new NpcStringId(8857);
		DAMMIT_THIS_IS_TOO_HARD_BY_MYSELF_S1_GIVE_ME_A_HAND = new NpcStringId(8858);
		S1_HURRY_UP_WELL_MISS_HIM = new NpcStringId(8859);
		S1_COME_ON_HURRY_UP = new NpcStringId(8860);
		I_GOTTA_GO_FOLLOW_HIM = new NpcStringId(8861);
		HEY_QUIT_RUNNING_STOP = new NpcStringId(8862);
		SEE_YOU_NEXT_TIME = new NpcStringId(8863);
		WHAT_THINK_YOU_CAN_GET_IN_MY_WAY = new NpcStringId(8864);
		YOU_ARE_SO_WEAK_I_GOTTA_GO_NOW = new NpcStringId(8865);
		S1_GOOD_ILL_HELP_YOU = new NpcStringId(8866);
		S1_YOURE_STRONGER_THAN_I_THOUGHT_SEE_YOU_NEXT_TIME = new NpcStringId(8867);
		YOURE_FEISTIER_THAN_I_THOUGHT_ILL_STOP_HERE_TODAY = new NpcStringId(8951);
		AARGH_I_CANT_BELIEVE_I_LOST = new NpcStringId(8952);
		ILL_STOP_HERE_TODAY = new NpcStringId(8956);
		DAMN_ITS_TOO_MUCH_BY_MYSELFS1_GIVE_ME_A_HAND = new NpcStringId(8958);
		S1_HURRY_WELL_MISS_HIM = new NpcStringId(8959);
		S1_HURRY_PLEASE = new NpcStringId(8960);
		I_GOTTA_GO_FOLLOW_HIM_NOW = new NpcStringId(8961);
		ARE_YOU_RUNNING_AWAY_STOP = new NpcStringId(8962);
		DO_YOU_THINK_YOU_CAN_STOP_ME = new NpcStringId(8964);
		YOURE_SO_WEAK_I_GOTTA_GO_NOW = new NpcStringId(8965);
		YOURE_S1_GOOD_ILL_HELP_YOU = new NpcStringId(8966);
		ARE_YOU_S1_OH_I_HAVE_A_RESONANCE_AMULET = new NpcStringId(9050);
		HEY_YOURE_MORE_TENACIOUS_THAN_I_THOUGHT_ILL_STOP_HERE_TODAY = new NpcStringId(9051);
		DAMMIT_I_CANT_DO_THIS_ALONE_S1_GIVE_ME_A_HAND = new NpcStringId(9058);
		S1_HURRY_OR_WELL_MISS_HIM = new NpcStringId(9059);
		S1_HURRY_UP = new NpcStringId(9060);
		I_GOTTA_FOLLOW_HIM_NOW = new NpcStringId(9061);
		HEY_ARE_YOU_RUNNING_STOP = new NpcStringId(9062);
		OH_YOURE_S1_GOOD_ILL_HELP_YOU = new NpcStringId(9066);
		YOU_CAROUSE_WITH_EVIL_SPIRITS_S1_YOURE_NOT_WORTHY_OF_THE_HOLY_WISDOM = new NpcStringId(9150);
		YOURE_SO_STUBBORN_I_CANT_BOSS_YOU_AROUND_ANY_MORE_CAN_I = new NpcStringId(9151);
		HOW_COULD_IT_HAPPEN_DEFEATED_BY_A_HUMAN = new NpcStringId(9152);
		MY_MASTER_SENT_ME_HERE_ILL_GIVE_YOU_A_HAND = new NpcStringId(9157);
		MEOW_MASTER_S1_HELP_ME = new NpcStringId(9158);
		MASTER_S1_PUNISH_HIM_SO_HE_CANT_BOTHER_BELINDA = new NpcStringId(9159);
		MASTER_S1_WELL_MISS_HIM = new NpcStringId(9160);
		MEOW_MY_MASTER_IS_CALLING_MEOW_I_GOTTA_GO_NOW = new NpcStringId(9161);
		MEOW_I_MISSED_HIM_MEOW = new NpcStringId(9162);
		GOOD_LUCK_MEOW_I_GOTTA_GO_NOW = new NpcStringId(9163);
		CURIOSITY_KILLED_THE_CAT_ILL_SHOW_YOU = new NpcStringId(9164);
		THATS_ALL_FOR_TODAY = new NpcStringId(9165);
		ARE_YOU_TRYING_TO_TAKE_BELINDA_FROM_ME_S1_ILL_SHOW_YOU = new NpcStringId(9166);
		BELINDA_I_LOVE_YOU_YIKES = new NpcStringId(9167);
		YOURE_STUBBORN_AS_A_MULE_GUESS_I_CANT_BOSS_YOU_AROUND_ANY_MORE = new NpcStringId(9251);
		HOW_COULD_IT_BEDEFEATED_BY_AN_ELF = new NpcStringId(9252);
		I_CAME_TO_HELP_YOU_ITS_THE_WILL_OF_RADYSS = new NpcStringId(9257);
		S1_FIGHT_WITH_ME = new NpcStringId(9258);
		S1_WE_MUST_DEFEAT_HIM = new NpcStringId(9259);
		S1_THERES_NO_TIME_WE_MUST_DEFEAT_HIM = new NpcStringId(9260);
		RADYSS_IS_CALLING_ME_I_GOTTA_GO_NOW = new NpcStringId(9261);
		I_WAS_UNABLE_TO_AVENGE_MY_BROTHER = new NpcStringId(9262);
		MAY_YOU_BE_BLESSED = new NpcStringId(9263);
		THE_PROUD_REPENT_THE_FOOLISH_AWAKEN_SINNERS_DIE = new NpcStringId(9264);
		HELLS_MASTER_IS_CALLING_ATONEMENT_WILL_HAVE_TO_WAIT = new NpcStringId(9265);
		S1_ILL_REMEMBER_YOUR_NAME_HEATHEN = new NpcStringId(9266);
		I_WONT_FORGET_THE_NAME_OF_ONE_WHO_DOESNT_OBEY_HOLY_JUDGMENT_S1 = new NpcStringId(9267);
		YOURE_STUBBORN_AS_A_MULE_I_GUESS_I_CANT_BOSS_YOU_AROUND_ANY_MORE = new NpcStringId(9351);
		COULD_IT_BE_DEFEATED_BY_A_DARK_ELF = new NpcStringId(9352);
		SHADOW_SUMMONER_I_CAME_HERE_TO_HELP_YOU = new NpcStringId(9357);
		SHADOW_SUMMONER_S1_FIGHT_WITH_ME = new NpcStringId(9358);
		S1_YOULL_DIE_IF_YOU_DONT_KILL_HIM = new NpcStringId(9359);
		HURRY_S1_DONT_MISS_HIM = new NpcStringId(9360);
		I_CANT_HOLD_ON_ANY_LONGER = new NpcStringId(9361);
		AFTER_ALL_THATI_MISSED_HIM = new NpcStringId(9362);
		SHADOW_SUMMONER_MAY_YOU_BE_BLESSED = new NpcStringId(9363);
		MY_MASTER_SENT_ME_HERE_TO_KILL_YOU = new NpcStringId(9364);
		THE_SHADOW_IS_CALLING_ME = new NpcStringId(9365);
		S1_YOU_WANT_TO_DIE_EARLY_ILL_SEND_YOU_TO_THE_DARKNESS = new NpcStringId(9366);
		YOU_DEAL_IN_DARKNESS_S1_ILL_PAY_YOU_BACK = new NpcStringId(9367);
		YOURE_S1_I_WONT_BE_LIKE_HINDEMITH = new NpcStringId(9450);
		YOURE_FEISTIER_THAN_I_THOUGHT_ILL_STOP_HERE_FOR_TODAY = new NpcStringId(9451);
		ARE_YOU_THE_ONE_WHO_IS_BOTHERING_MY_MINIONS_S1 = new NpcStringId(9453);
		I_CANT_LET_YOU_COMMUNE_WITH_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET = new NpcStringId(9457);
		S1_PLEASE_HURRY = new NpcStringId(9460);
		I_MUST_FOLLOW_HIM_NOW = new NpcStringId(9461);
		ARE_YOU_RUNNING_STOP = new NpcStringId(9462);
		ARE_YOU_BETRAYING_ME_I_THOUGHT_SOMETHING_WAS_WRONGILL_STOP_HERE = new NpcStringId(9464);
		YOURE_S1_EVEN_TWO_OF_YOU_CANT_STOP_ME = new NpcStringId(9466);
		DAMMIT_MY_RESONANCE_AMULETS1_ILL_NEVER_FORGET_TO_PAY_YOU_BACK = new NpcStringId(9467);
		ARE_YOU_S1_I_WONT_BE_LIKE_WALDSTEIN = new NpcStringId(9550);
		YIKES_I_CANT_BELIEVE_I_LOST = new NpcStringId(9552);
		ARE_YOU_THE_ONE_BOTHERING_MY_MINIONS_S1 = new NpcStringId(9553);
		YOU_CANT_COMMUNE_WITH_THE_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET = new NpcStringId(9557);
		DAMMIT_MY_RESONANCE_AMULETS1_ILL_NEVER_FORGET_THIS = new NpcStringId(9567);
		YOURE_S1_ILL_KILL_YOU_FOR_HALLATE = new NpcStringId(9650);
		YOURE_TOUGHER_THAN_I_THOUGHT_BUT_YOU_STILL_CANT_RIVAL_ME = new NpcStringId(9651);
		HALLATE_FORGIVE_ME_I_CANT_HELP_YOU = new NpcStringId(9652);
		DAMMIT_I_CANT_BELIEVE_YOU_BEAT_ME = new NpcStringId(9654);
		WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_COWARD = new NpcStringId(9655);
		PURGATORY_LORD_I_WONT_FAIL_THIS_TIME = new NpcStringId(9657);
		S1_NOWS_THE_TIME_TO_PUT_YOUR_TRAINING_TO_THE_TEST = new NpcStringId(9658);
		S1_YOUR_SWORD_SKILLS_CANT_BE_THAT_BAD = new NpcStringId(9659);
		S1_SHOW_YOUR_STRENGTH = new NpcStringId(9660);
		I_HAVE_SOME_PRESSING_BUSINESS_I_HAVE_TO_GO = new NpcStringId(9661);
		I_MISSED_HIM_DAMMIT = new NpcStringId(9662);
		TRY_AGAIN_SOMETIME = new NpcStringId(9663);
		ILL_KILL_ANYONE_WHO_GETS_IN_MY_WAY = new NpcStringId(9664);
		THIS_IS_PATHETIC_YOU_MAKE_ME_LAUGH = new NpcStringId(9665);
		S1_ARE_YOU_TRYING_TO_GET_IN_MY_WAY = new NpcStringId(9666);
		S1_WHEN_I_COME_BACK_ILL_KILL_YOU = new NpcStringId(9667);
		S1_WAKE_UP_TIME_TO_DIE = new NpcStringId(9750);
		YOURE_TOUGHER_THAN_I_THOUGHT_ILL_BE_BACK = new NpcStringId(9751);
		I_LOST_IT_CANT_BE = new NpcStringId(9752);
		YOURE_A_CUNNING_FIEND_I_WONT_FAIL_AGAIN = new NpcStringId(9757);
		S1_ITS_AFTER_YOU_FIGHT = new NpcStringId(9758);
		S1_YOU_HAVE_TO_FIGHT_BETTER_THAN_THAT_IF_YOU_EXPECT_TO_SURVIVE = new NpcStringId(9759);
		S1_PULL_YOURSELF_TOGETHER_FIGHT = new NpcStringId(9760);
		ILL_CATCH_THE_CUNNING_FIEND = new NpcStringId(9761);
		I_MISSED_HIM_AGAIN_HES_CLEVER = new NpcStringId(9762);
		DONT_COWER_LIKE_A_PUPPY_NEXT_TIME = new NpcStringId(9763);
		I_HAVE_ONLY_ONE_GOAL_GET_OUT_OF_MY_WAY = new NpcStringId(9764);
		JUST_WAIT_YOULL_GET_YOURS = new NpcStringId(9765);
		S1_YOURE_A_COWARD_ARENT_YOU = new NpcStringId(9766);
		S1_ILL_KILL_YOU_NEXT_TIME = new NpcStringId(9767);
		S1_HOW_FOOLISH_TO_ACT_AGAINST_THE_WILL_OF_GOD = new NpcStringId(9850);
		YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_GET_YOU_NEXT_TIME = new NpcStringId(9851);
		WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_YOU_COWARD = new NpcStringId(9855);
		TANAKIA_YOUR_LIE_HAS_ALREADY_BEEN_EXPOSED = new NpcStringId(9857);
		S1_HELP_ME_OVERCOME_THIS = new NpcStringId(9858);
		S1_WE_CANT_DEFEAT_TANAKIA_THIS_WAY = new NpcStringId(9859);
		S1_HERES_OUR_CHANCE_DONT_SQUANDER_THE_OPPORTUNITY = new NpcStringId(9860);
		GOODBYE_WELL_MEET_AGAIN_IF_FATE_ALLOWS = new NpcStringId(9861);
		ILL_FOLLOW_TANAKIA_TO_CORRECT_THIS_FALSEHOOD = new NpcStringId(9862);
		ILL_BE_BACK_IF_FATE_ALLOWS = new NpcStringId(9863);
		ILL_BE_BACK_YOULL_PAY = new NpcStringId(9865);
		S1_ARE_YOU_TRYING_TO_SPOIL_MY_PLAN = new NpcStringId(9866);
		S1_I_WONT_FORGET_YOU_YOULL_PAY = new NpcStringId(9867);
		S1_YOU_HAVE_AN_AFFINITY_FOR_DANGEROUS_IDEAS_ARE_YOU_READY_TO_DIE = new NpcStringId(9950);
		MY_TIME_IS_UP = new NpcStringId(9951);
		I_CANT_BELIEVE_I_MUST_KNEEL_TO_A_HUMAN = new NpcStringId(9952);
		MINERVIA_WHATS_THE_MATTER = new NpcStringId(9957);
		THE_PRINCESS_IS_IN_DANGER_WHY_ARE_YOU_STARING = new NpcStringId(9958);
		MASTER_S1_COME_ON_HURRY_UP = new NpcStringId(9959);
		WE_CANT_FAIL_MASTER_S1_PULL_YOURSELF_TOGETHER = new NpcStringId(9960);
		WHAT_AM_I_DOING_I_GOTTA_GO_GOODBYE = new NpcStringId(9961);
		DAMMIT_I_MISSED = new NpcStringId(9962);
		SORRY_BUT_I_MUST_SAY_GOODBYE_AGAIN_GOOD_LUCK_TO_YOU = new NpcStringId(9963);
		I_CANT_YIELD_THE_SECRET_OF_THE_TABLET = new NpcStringId(9964);
		ILL_STOP_HERE_FOR_NOW = new NpcStringId(9965);
		S1_YOU_DARED_TO_LEAVE_SCAR_ON_MY_FACE_ILL_KILL_YOU = new NpcStringId(9966);
		S1_I_WONT_FORGET_YOUR_NAMEHA = new NpcStringId(9967);
		S1_YOU_HAVE_AN_AFFINITY_FOR_BAD_IDEAS_ARE_YOU_READY_TO_DIE = new NpcStringId(10050);
		I_CANT_BELIEVE_I_MUST_KNEEL_BEFORE_A_HUMAN = new NpcStringId(10052);
		YOU_THIEF_GIVE_ME_THE_RESONANCE_AMULET = new NpcStringId(10057);
		UGH_S1_HELP_ME = new NpcStringId(10058);
		S1_PLEASE_HELP_ME_TOGETHER_WE_CAN_BEAT_HIM = new NpcStringId(10059);
		S1_ARE_YOU_GOING_TO_LET_A_GUILD_MEMBER_DIE = new NpcStringId(10060);
		IM_SORRY_BUT_I_GOTTA_GO_FIRST = new NpcStringId(10061);
		AAAAH_I_COULDNT_GET_THE_RESONANCE_AMULET = new NpcStringId(10062);
		TAKE_CARE_I_GOTTA_GO_NOW = new NpcStringId(10063);
		IM_SORRY_BUT_ITS_MY_JOB_TO_KILL_YOU_NOW = new NpcStringId(10064);
		WHAT_A_WASTE_OF_TIME = new NpcStringId(10065);
		S1_HOW_COULD_YOU_DO_THIS_ILL_KILL_YOU = new NpcStringId(10066);
		S1_ILL_PAY_YOU_BACK = new NpcStringId(10067);
		WHY_DONT_YOU_JUST_DIE = new NpcStringId(10068);
		TASTE_THE_STING_OF_LEVEL_5_SPOIL = new NpcStringId(10069);
		THE_ITEM_IS_ALREADY_INSIDE_YOU = new NpcStringId(10070);
		THIS_POTION_YOURE_MAKING_ME_DRINK_IS_WORTH_ITS_WEIGHT_IN_GOLD = new NpcStringId(10071);
		THIS_POTION_IS_PREPARED_FROM_THE_GROUND_GALL_OF_A_BEAR_BE_CAREFUL_IT_PACKS_QUITE_A_PUNCH = new NpcStringId(10072);
		HOW_CAN_YOU_USE_A_POTION_ON_A_NEWBIE = new NpcStringId(10073);
		LISTEN_TO_ME_S1_UNLESS_YOU_HAVE_PRIOR_AUTHORIZATION_YOU_CANT_CARRY_A_WEAPON_HERE = new NpcStringId(10074);
		DEAR_S1_MAY_THE_BLESSINGS_OF_EINHASAD_BE_WITH_YOU_ALWAYS = new NpcStringId(10075);
		DEAR_BROTHER_S1_FOLLOW_THE_PATH_OF_LIGHT_WITH_ME = new NpcStringId(10076);
		S1_WHY_WOULD_YOU_CHOOSE_THE_PATH_OF_DARKNESS = new NpcStringId(10077);
		S1_HOW_DARE_YOU_DEFY_THE_WILL_OF_EINHASAD = new NpcStringId(10078);
		THE_DOOR_TO_THE_3RD_FLOOR_OF_THE_ALTAR_IS_NOW_OPEN = new NpcStringId(10079);
		ELROKIAN_HUNTERS = new NpcStringId(11101);
		YOU_S1_YOU_ATTACKED_WENDY_PREPARE_TO_DIE = new NpcStringId(11450);
		S1_YOUR_ENEMY_WAS_DRIVEN_OUT_I_WILL_NOW_WITHDRAW_AND_AWAIT_YOUR_NEXT_COMMAND = new NpcStringId(11451);
		THIS_ENEMY_IS_FAR_TOO_POWERFUL_FOR_ME_TO_FIGHT_I_MUST_WITHDRAW = new NpcStringId(11452);
		THE_RADIO_SIGNAL_DETECTOR_IS_RESPONDING_A_SUSPICIOUS_PILE_OF_STONES_CATCHES_YOUR_EYE = new NpcStringId(11453);
		THIS_LOOKS_LIKE_THE_RIGHT_PLACE = new NpcStringId(11550);
		I_SEE_SOMEONE_IS_THIS_FATE = new NpcStringId(11551);
		WE_MEET_AGAIN = new NpcStringId(11552);
		DONT_BOTHER_TRYING_TO_FIND_OUT_MORE_ABOUT_ME_FOLLOW_YOUR_OWN_DESTINY = new NpcStringId(11553);
		FALLEN_ANGEL_SELECT = new NpcStringId(14204);
		_HOW_DARE_YOU_CHALLENGE_ME = new NpcStringId(15804);
		THE_POWER_OF_LORD_BELETH_RULES_THE_WHOLE_WORLD = new NpcStringId(15805);
		I_WILL_TASTE_YOUR_BLOOD = new NpcStringId(16404);
		I_HAVE_FULFILLED_MY_CONTRACT_WITH_TRADER_CREAMEES = new NpcStringId(16405);
		ILL_CAST_YOU_INTO_AN_ETERNAL_NIGHTMARE = new NpcStringId(17004);
		SEND_MY_SOUL_TO_LICH_KING_ICARUS = new NpcStringId(17005);
		YOU_SHOULD_CONSIDER_GOING_BACK = new NpcStringId(17151);
		THE_VEILED_CREATOR = new NpcStringId(17951);
		THE_CONSPIRACY_OF_THE_ANCIENT_RACE = new NpcStringId(17952);
		CHAOS_AND_TIME = new NpcStringId(17953);
		INTRUDER_ALERT_THE_ALARM_WILL_SELF_DESTRUCT_IN_2_MINUTES = new NpcStringId(18451);
		THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_ENTER_PASSCODE_TO_OVERRIDE = new NpcStringId(18452);
		THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_ENTER_PASSCODE_TO_OVERRIDE = new NpcStringId(18453);
		THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_ENTER_PASSCODE_TO_OVERRIDE = new NpcStringId(18454);
		RECORDER_CRUSHED = new NpcStringId(18455);
		THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_PLEASE_EVACUATE_IMMEDIATELY = new NpcStringId(18552);
		THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_PLEASE_EVACUATE_IMMEDIATELY = new NpcStringId(18553);
		THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_PLEASE_EVACUATE_IMMEDIATELY = new NpcStringId(18554);
		S1_YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM = new NpcStringId(19304);
		NEXT_TIME_YOU_WILL_NOT_ESCAPE = new NpcStringId(19305);
		S1_YOU_MAY_HAVE_WON_THIS_TIME_BUT_NEXT_TIME_I_WILL_SURELY_CAPTURE_YOU = new NpcStringId(19306);
		INTRUDER_PROTECT_THE_PRIESTS_OF_DAWN = new NpcStringId(19504);
		WHO_ARE_YOU_A_NEW_FACE_LIKE_YOU_CANT_APPROACH_THIS_PLACE = new NpcStringId(19505);
		HOW_DARE_YOU_INTRUDE_WITH_THAT_TRANSFORMATION_GET_LOST = new NpcStringId(19506);
		WHO_DARES_SUMMON_THE_MERCHANT_OF_MAMMON = new NpcStringId(19604);
		THE_ANCIENT_PROMISE_TO_THE_EMPEROR_HAS_BEEN_FULFILLED = new NpcStringId(19605);
		FOR_THE_ETERNITY_OF_EINHASAD = new NpcStringId(19606);
		DEAR_SHILLIENS_OFFSPRINGS_YOU_ARE_NOT_CAPABLE_OF_CONFRONTING_US = new NpcStringId(19607);
		ILL_SHOW_YOU_THE_REAL_POWER_OF_EINHASAD = new NpcStringId(19608);
		DEAR_MILITARY_FORCE_OF_LIGHT_GO_DESTROY_THE_OFFSPRINGS_OF_SHILLIEN = new NpcStringId(19609);
		EVERYTHING_IS_OWING_TO_S1_THANK_YOU = new NpcStringId(19610);
		MY_POWERS_WEAKENING_HURRY_AND_TURN_ON_THE_SEALING_DEVICE = new NpcStringId(19611);
		ALL_4_SEALING_DEVICES_MUST_BE_TURNED_ON = new NpcStringId(19612);
		LILITHS_ATTACK_IS_GETTING_STRONGER_GO_AHEAD_AND_TURN_IT_ON = new NpcStringId(19613);
		DEAR_S1_GIVE_ME_MORE_STRENGTH = new NpcStringId(19614);
		YOU_SUCH_A_FOOL_THE_VICTORY_OVER_THIS_WAR_BELONGS_TO_SHILIEN = new NpcStringId(19615);
		HOW_DARE_YOU_TRY_TO_CONTEND_AGAINST_ME_IN_STRENGTH_RIDICULOUS = new NpcStringId(19616);
		ANAKIM_IN_THE_NAME_OF_GREAT_SHILIEN_I_WILL_CUT_YOUR_THROAT = new NpcStringId(19617);
		YOU_CANNOT_BE_THE_MATCH_OF_LILITH_ILL_TEACH_YOU_A_LESSON = new NpcStringId(19618);
		I_MUST_GO_BACK_TO_SHILIEN_JUST_LIKE_THIS_OUTRAGEOUS = new NpcStringId(19619);
		DEATH_TO_THE_ENEMIES_OF_THE_LORDS_OF_DAWN = new NpcStringId(19804);
		WE_WILL_BE_WITH_YOU_ALWAYS = new NpcStringId(19805);
		YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM = new NpcStringId(19806);
		EMBRYO = new NpcStringId(19807);
		KAMAEL_TUTORIAL = new NpcStringId(20901);
		IS_IT_A_LACKEY_OF_KAKAI = new NpcStringId(22051);
		TOO_LATE = new NpcStringId(22052);
		HOW_REGRETFUL_UNJUST_DISHONOR = new NpcStringId(22055);
		ILL_GET_REVENGE_SOMEDAY = new NpcStringId(22056);
		INDIGNANT_AND_UNFAIR_DEATH = new NpcStringId(22057);
		THE_CONCEALED_TRUTH_WILL_ALWAYS_BE_REVEALED = new NpcStringId(22719);
		COWARDLY_GUY = new NpcStringId(22720);
		I_AM_A_TREE_OF_NOTHING_A_TREE_THAT_KNOWS_WHERE_TO_RETURN = new NpcStringId(22819);
		I_AM_A_CREATURE_THAT_SHOWS_THE_TRUTH_OF_THE_PLACE_DEEP_IN_MY_HEART = new NpcStringId(22820);
		I_AM_A_MIRROR_OF_DARKNESS_A_VIRTUAL_IMAGE_OF_DARKNESS = new NpcStringId(22821);
		I_ABSOLUTELY_CANNOT_GIVE_IT_TO_YOU_IT_IS_MY_PRECIOUS_JEWEL = new NpcStringId(22933);
		ILL_TAKE_YOUR_LIVES_LATER = new NpcStringId(22934);
		THAT_SWORD_IS_REALLY = new NpcStringId(22935);
		NO_I_HAVENT_COMPLETELY_FINISHED_THE_COMMAND_FOR_DESTRUCTION_AND_SLAUGHTER_YET = new NpcStringId(22936);
		HOW_DARE_YOU_WAKE_ME_NOW_YOU_SHALL_DIE = new NpcStringId(22937);
		START_DUEL = new NpcStringId(23060);
		RULE_VIOLATION = new NpcStringId(23061);
		I_LOSE = new NpcStringId(23062);
		WHHIISSHH = new NpcStringId(23063);
		IM_SORRY_LORD = new NpcStringId(23065);
		WHISH_FIGHT = new NpcStringId(23066);
		LOST_SORRY_LORD = new NpcStringId(23068);
		SO_SHALL_WE_START = new NpcStringId(23072);
		UGH_I_LOST = new NpcStringId(23074);
		ILL_WALK_ALL_OVER_YOU = new NpcStringId(23075);
		UGH_CAN_THIS_BE_HAPPENING = new NpcStringId(23077);
		ITS_THE_NATURAL_RESULT = new NpcStringId(23078);
		HO_HO_I_WIN = new NpcStringId(23079);
		I_WIN = new NpcStringId(23080);
		WHISH_I_WON = new NpcStringId(23081);
		WHO_DARES_TO_TRY_AND_STEAL_MY_NOBLE_BLOOD = new NpcStringId(23434);
		S1_FINALLY_WE_MEET = new NpcStringId(23651);
		HMM_WHERE_DID_MY_FRIEND_GO = new NpcStringId(23652);
		BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS = new NpcStringId(23653);
		S1_DID_YOU_WAIT_FOR_LONG = new NpcStringId(23661);
		DID_YOU_BRING_WHAT_I_ASKED_S1 = new NpcStringId(23671);
		HMM_IS_SOMEONE_APPROACHING = new NpcStringId(23681);
		GRAAAH_WERE_BEING_ATTACKED = new NpcStringId(23682);
		IN_THAT_CASE_I_WISH_YOU_GOOD_LUCK = new NpcStringId(23683);
		S1_HAS_EVERYTHING_BEEN_FOUND = new NpcStringId(23685);
		SAFE_TRAVELS = new NpcStringId(23687);
		REQUEST_FROM_THE_FARM_OWNER = new NpcStringId(25901);
		WHY_DO_YOU_OPPRESS_US_SO = new NpcStringId(31603);
		DONT_INTERRUPT_MY_REST_AGAIN = new NpcStringId(33409);
		YOURE_A_GREAT_DEVIL_NOW = new NpcStringId(33410);
		OH_ITS_NOT_AN_OPPONENT_OF_MINE_HA_HA_HA = new NpcStringId(33411);
		OH_GREAT_DEMON_KING = new NpcStringId(33412);
		REVENGE_IS_OVERLORD_RAMSEBALIUS_OF_THE_EVIL_WORLD = new NpcStringId(33413);
		BONAPARTERIUS_ABYSS_KING_WILL_PUNISH_YOU = new NpcStringId(33414);
		OK_EVERYBODY_PRAY_FERVENTLY = new NpcStringId(33415);
		BOTH_HANDS_TO_HEAVEN_EVERYBODY_YELL_TOGETHER = new NpcStringId(33416);
		ONE_TWO_MAY_YOUR_DREAMS_COME_TRUE = new NpcStringId(33417);
		WHO_KILLED_MY_UNDERLING_DEVIL = new NpcStringId(33418);
		I_WILL_MAKE_YOUR_LOVE_COME_TRUE_LOVE_LOVE_LOVE = new NpcStringId(33420);
		I_HAVE_WISDOM_IN_ME_I_AM_THE_BOX_OF_WISDOM = new NpcStringId(33421);
		OH_OH_OH = new NpcStringId(33422);
		DO_YOU_WANT_US_TO_LOVE_YOU_OH = new NpcStringId(33423);
		WHO_IS_CALLING_THE_LORD_OF_DARKNESS = new NpcStringId(33424);
		I_AM_A_GREAT_EMPIRE_BONAPARTERIUS = new NpcStringId(33425);
		LET_YOUR_HEAD_DOWN_BEFORE_THE_LORD = new NpcStringId(33426);
		THE_SONG_OF_THE_HUNTER = new NpcStringId(33501);
		WELL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE = new NpcStringId(33511);
		SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEYRE_ALL_MINE = new NpcStringId(33512);
		PRETTY_GOOD = new NpcStringId(33513);
		HA_THAT_WAS_FUN_IF_YOU_WISH_TO_FIND_THE_KEY_SEARCH_THE_CORPSE = new NpcStringId(34830);
		I_HAVE_THE_KEY_WHY_DONT_YOU_COME_AND_TAKE_IT = new NpcStringId(34831);
		YOU_FOOLS_WILL_GET_WHATS_COMING_TO_YOU = new NpcStringId(34832);
		SORRY_ABOUT_THIS_BUT_I_MUST_KILL_YOU_NOW = new NpcStringId(34833);
		YOU_GUYS_WOULDNT_KNOW_THE_SEVEN_SEALS_ARE_ARRRGH = new NpcStringId(34835);
		I_SHALL_DRENCH_THIS_MOUNTAIN_WITH_YOUR_BLOOD = new NpcStringId(34836);
		THAT_DOESNT_BELONG_TO_YOU_DONT_TOUCH_IT = new NpcStringId(34837);
		GET_OUT_OF_MY_SIGHT_YOU_INFIDELS = new NpcStringId(34838);
		WE_DONT_HAVE_ANY_FURTHER_BUSINESS_TO_DISCUSS_HAVE_YOU_SEARCHED_THE_CORPSE_FOR_THE_KEY = new NpcStringId(34839);
		S1_HAS_EARNED_A_STAGE_S2_BLUE_SOUL_CRYSTAL = new NpcStringId(35051);
		S1_HAS_EARNED_A_STAGE_S2_RED_SOUL_CRYSTAL = new NpcStringId(35052);
		S1_HAS_EARNED_A_STAGE_S2_GREEN_SOUL_CRYSTAL = new NpcStringId(35053);
		S1_HAS_EARNED_A_STAGE_S2_BLUE_CURSED_SOUL_CRYSTAL = new NpcStringId(35054);
		S1_HAS_EARNED_A_STAGE_S2_RED_CURSED_SOUL_CRYSTAL = new NpcStringId(35055);
		S1_HAS_EARNED_A_STAGE_S2_GREEN_CURSED_SOUL_CRYSTAL = new NpcStringId(35056);
		SHRIEKS_OF_GHOSTS = new NpcStringId(37101);
		SLOT_S1_S3 = new NpcStringId(38451);
		YOU_CHILDISH_FOOL_DO_YOU_THINK_YOU_CAN_CATCH_ME = new NpcStringId(40306);
		I_MUST_DO_SOMETHING_ABOUT_THIS_SHAMEFUL_INCIDENT = new NpcStringId(40307);
		WHAT_DO_YOU_DARE_TO_CHALLENGE_ME = new NpcStringId(40308);
		THE_RED_EYED_THIEVES_WILL_REVENGE = new NpcStringId(40309);
		GO_AHEAD_YOU_CHILD = new NpcStringId(40310);
		MY_FRIENDS_ARE_SURE_TO_REVENGE = new NpcStringId(40311);
		YOU_RUTHLESS_FOOL_I_WILL_SHOW_YOU_WHAT_REAL_FIGHTING_IS_ALL_ABOUT = new NpcStringId(40312);
		AHH_HOW_CAN_IT_END_LIKE_THIS_IT_IS_NOT_FAIR = new NpcStringId(40313);
		THE_SACRED_FLAME_IS_OURS = new NpcStringId(40909);
		ARRGHHWE_SHALL_NEVER_SURRENDER = new NpcStringId(40910);
		AS_YOU_WISH_MASTER = new NpcStringId(40913);
		MY_DEAR_FRIEND_OF_S1_WHO_HAS_GONE_ON_AHEAD_OF_ME = new NpcStringId(41651);
		LISTEN_TO_TEJAKAR_GANDI_YOUNG_OROKA_THE_SPIRIT_OF_THE_SLAIN_LEOPARD_IS_CALLING_YOU_S1 = new NpcStringId(41652);
		HEY_EVERYBODY_WATCH_THE_EGGS = new NpcStringId(42046);
		I_THOUGHT_ID_CAUGHT_ONE_SHARE_WHEW = new NpcStringId(42047);
		THE_STONE_THE_ELVEN_STONE_BROKE = new NpcStringId(42048);
		IF_THE_EGGS_GET_TAKEN_WERE_DEAD = new NpcStringId(42049);
		GIVE_ME_A_FAIRY_LEAF = new NpcStringId(42111);
		WHY_DO_YOU_BOTHER_ME_AGAIN = new NpcStringId(42112);
		HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_WIND = new NpcStringId(42113);
		LEAVE_NOW_BEFORE_YOU_INCUR_THE_WRATH_OF_THE_GUARDIAN_GHOST = new NpcStringId(42114);
		HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_A_STAR = new NpcStringId(42115);
		HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_DUSK = new NpcStringId(42116);
		HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_THE_ABYSS = new NpcStringId(42117);
		WE_MUST_PROTECT_THE_FAIRY_TREE = new NpcStringId(42118);
		GET_OUT_OF_THE_SACRED_TREE_YOU_SCOUNDRELS = new NpcStringId(42119);
		DEATH_TO_THE_THIEVES_OF_THE_PURE_WATER_OF_THE_WORLD = new NpcStringId(42120);
		HEY_IT_SEEMS_LIKE_YOU_NEED_MY_HELP_DOESNT_IT = new NpcStringId(42231);
		ALMOST_GOT_IT_OUCH_STOP_DAMN_THESE_BLOODY_MANACLES = new NpcStringId(42232);
		OH_THAT_SMARTS = new NpcStringId(42233);
		HEY_MASTER_PAY_ATTENTION_IM_DYING_OVER_HERE = new NpcStringId(42234);
		WHAT_HAVE_I_DONE_TO_DESERVE_THIS = new NpcStringId(42235);
		OH_THIS_IS_JUST_GREAT_WHAT_ARE_YOU_GOING_TO_DO_NOW = new NpcStringId(42236);
		YOU_INCONSIDERATE_MORON_CANT_YOU_EVEN_TAKE_CARE_OF_LITTLE_OLD_ME = new NpcStringId(42237);
		OH_NO_THE_MAN_WHO_EATS_ONES_SINS_HAS_DIED_PENITENCE_IS_FURTHER_AWAY = new NpcStringId(42238);
		USING_A_SPECIAL_SKILL_HERE_COULD_TRIGGER_A_BLOODBATH = new NpcStringId(42239);
		HEY_WHAT_DO_YOU_EXPECT_OF_ME = new NpcStringId(42240);
		UGGGGGH_PUSH_ITS_NOT_COMING_OUT = new NpcStringId(42241);
		AH_I_MISSED_THE_MARK = new NpcStringId(42242);
		YAWWWWN_ITS_SO_BORING_HERE_WE_SHOULD_GO_AND_FIND_SOME_ACTION = new NpcStringId(42243);
		HEY_IF_YOU_CONTINUE_TO_WASTE_TIME_YOU_WILL_NEVER_FINISH_YOUR_PENANCE = new NpcStringId(42244);
		I_KNOW_YOU_DONT_LIKE_ME_THE_FEELING_IS_MUTUAL = new NpcStringId(42245);
		I_NEED_A_DRINK = new NpcStringId(42246);
		OH_THIS_IS_DRAGGING_ON_TOO_LONG_AT_THIS_RATE_I_WONT_MAKE_IT_HOME_BEFORE_THE_SEVEN_SEALS_ARE_BROKEN = new NpcStringId(42247);
		S1_RECEIVED_A_S2_ITEM_AS_A_REWARD_FROM_THE_SEPARATED_SOUL = new NpcStringId(45650);
		SEALED_VORPAL_HELMET = new NpcStringId(45651);
		SEALED_VORPAL_LEATHER_HELMET = new NpcStringId(45652);
		SEALED_VORPAL_CIRCLET = new NpcStringId(45653);
		SEALED_VORPAL_BREASTPLATE = new NpcStringId(45654);
		SEALED_VORPAL_LEATHER_BREASTPLATE = new NpcStringId(45655);
		SEALED_VORPAL_TUNIC = new NpcStringId(45656);
		SEALED_VORPAL_GAITERS = new NpcStringId(45657);
		SEALED_VORPAL_LEATHER_LEGGING = new NpcStringId(45658);
		SEALED_VORPAL_STOCKING = new NpcStringId(45659);
		SEALED_VORPAL_GAUNTLET = new NpcStringId(45660);
		SEALED_VORPAL_LEATHER_GLOVES = new NpcStringId(45661);
		SEALED_VORPAL_GLOVES = new NpcStringId(45662);
		SEALED_VORPAL_BOOTS = new NpcStringId(45663);
		SEALED_VORPAL_LEATHER_BOOTS = new NpcStringId(45664);
		SEALED_VORPAL_SHOES = new NpcStringId(45665);
		SEALED_VORPAL_SHIELD = new NpcStringId(45666);
		SEALED_VORPAL_SIGIL = new NpcStringId(45667);
		SEALED_VORPAL_RING = new NpcStringId(45668);
		SEALED_VORPAL_EARRING = new NpcStringId(45669);
		SEALED_VORPAL_NECKLACE = new NpcStringId(45670);
		PERIEL_SWORD = new NpcStringId(45671);
		SKULL_EDGE = new NpcStringId(45672);
		VIGWIK_AXE = new NpcStringId(45673);
		DEVILISH_MAUL = new NpcStringId(45674);
		FEATHER_EYE_BLADE = new NpcStringId(45675);
		OCTO_CLAW = new NpcStringId(45676);
		DOUBLETOP_SPEAR = new NpcStringId(45677);
		RISING_STAR = new NpcStringId(45678);
		BLACK_VISAGE = new NpcStringId(45679);
		VENIPLANT_SWORD = new NpcStringId(45680);
		SKULL_CARNIUM_BOW = new NpcStringId(45681);
		GEMTAIL_RAPIER = new NpcStringId(45682);
		FINALE_BLADE = new NpcStringId(45683);
		DOMINION_CROSSBOW = new NpcStringId(45684);
		BLESSED_WEAPON_ENCHANT_SCROLL_S_GRADE = new NpcStringId(45685);
		BLESSED_ARMOR_ENCHANT_SCROLL_S_GRADE = new NpcStringId(45686);
		FIRE_CRYSTAL = new NpcStringId(45687);
		WATER_CRYSTAL = new NpcStringId(45688);
		EARTH_CRYSTAL = new NpcStringId(45689);
		WIND_CRYSTAL = new NpcStringId(45690);
		HOLY_CRYSTAL = new NpcStringId(45691);
		DARK_CRYSTAL = new NpcStringId(45692);
		WEAPON_ENCHANT_SCROLL_S_GRADE = new NpcStringId(45693);
		ATT_ATTACK_S1_RO_ROGUE_S2 = new NpcStringId(46350);
		BINGO = new NpcStringId(50110);
		BLOOD_AND_HONOR = new NpcStringId(50338);
		CURSE_OF_THE_GODS_ON_THE_ONE_THAT_DEFILES_THE_PROPERTY_OF_THE_EMPIRE = new NpcStringId(50339);
		WAR_AND_DEATH = new NpcStringId(50340);
		AMBITION_AND_POWER = new NpcStringId(50341);
		S1_HAS_WON_THE_MAIN_EVENT_FOR_PLAYERS_UNDER_LEVEL_S2_AND_EARNED_S3_POINTS = new NpcStringId(50503);
		S1_HAS_EARNED_S2_POINTS_IN_THE_MAIN_EVENT_FOR_UNLIMITED_LEVELS = new NpcStringId(50504);
		INTO_THE_CHAOS = new NpcStringId(50701);
		YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLANS_REPUTATION_SCORE = new NpcStringId(50851);
		THE_FURNACE_WILL_GO_OUT_WATCH_AND_SEE = new NpcStringId(60000);
		THERES_ABOUT_1_MINUTE_LEFT = new NpcStringId(60001);
		THERES_JUST_10_SECONDS_LEFT = new NpcStringId(60002);
		NOW_LIGHT_THE_FURNACES_FIRE = new NpcStringId(60003);
		TIME_IS_UP_AND_YOU_HAVE_FAILED_ANY_MORE_WILL_BE_DIFFICULT = new NpcStringId(60004);
		OH_YOUVE_SUCCEEDED = new NpcStringId(60005);
		AH_IS_THIS_FAILURE_BUT_IT_LOOKS_LIKE_I_CAN_KEEP_GOING = new NpcStringId(60006);
		AH_IVE_FAILED_GOING_FURTHER_WILL_BE_DIFFICULT = new NpcStringId(60007);
		FURNACE_OF_BALANCE = new NpcStringId(60008);
		FURNACE_OF_PROTECTION = new NpcStringId(60009);
		FURNACE_OF_WILL = new NpcStringId(60010);
		FURNACE_OF_MAGIC = new NpcStringId(60011);
		DIVINE_ENERGY_IS_BEGINNING_TO_ENCIRCLE = new NpcStringId(60012);
		FOR_THE_GLORY_OF_SOLINA = new NpcStringId(60013);
		PUNISH_ALL_THOSE_WHO_TREAD_FOOTSTEPS_IN_THIS_PLACE = new NpcStringId(60014);
		WE_ARE_THE_SWORD_OF_TRUTH_THE_SWORD_OF_SOLINA = new NpcStringId(60015);
		WE_RAISE_OUR_BLADES_FOR_THE_GLORY_OF_SOLINA = new NpcStringId(60016);
		HEY_DONT_GO_SO_FAST = new NpcStringId(60018);
		ITS_HARD_TO_FOLLOW = new NpcStringId(60019);
		HUFF_HUFF_YOURE_TOO_FAST_I_CANT_FOLLOW_ANYMORE = new NpcStringId(60020);
		AH_I_THINK_I_REMEMBER_THIS_PLACE = new NpcStringId(60021);
		AH_FRESH_AIR = new NpcStringId(60022);
		WHAT_WERE_YOU_DOING_HERE = new NpcStringId(60023);
		I_GUESS_YOURE_THE_SILENT_TYPE_THEN_ARE_YOU_LOOKING_FOR_TREASURE_LIKE_ME = new NpcStringId(60024);
		WHO_IS_CALLING_ME = new NpcStringId(60403);
		CAN_LIGHT_EXIST_WITHOUT_DARKNESS = new NpcStringId(60404);
		YOU_CANT_AVOID_THE_EYES_OF_UDAN = new NpcStringId(60903);
		UDAN_HAS_ALREADY_SEEN_YOUR_FACE = new NpcStringId(60904);
		THE_MAGICAL_POWER_OF_WATER_COMES_FROM_THE_POWER_OF_STORM_AND_HAIL_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU = new NpcStringId(61050);
		THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED = new NpcStringId(61051);
		YOU_CANT_AVOID_THE_EYES_OF_ASEFA = new NpcStringId(61503);
		ASEFA_HAS_ALREADY_SEEN_YOUR_FACE = new NpcStringId(61504);
		THE_MAGICAL_POWER_OF_FIRE_IS_ALSO_THE_POWER_OF_FLAMES_AND_LAVA_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU = new NpcStringId(61650);
		I_SMELL_SOMETHING_DELICIOUS = new NpcStringId(62503);
		OOOH = new NpcStringId(62504);
		AIDING_THE_FLORAN_VILLAGE = new NpcStringId(66001);
		NO_SUCH_CARD = new NpcStringId(66300);
		DEFEAT_THE_ELROKIAN_RAIDERS = new NpcStringId(68801);
		HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD = new NpcStringId(70851);
		S1_NOW_DEPART = new NpcStringId(70852);
		GO_FIND_SAIUS = new NpcStringId(70853);
		LISTEN_YOU_VILLAGERS_OUR_LIEGE_WHO_WILL_SOON_BECOME_A_LORD_HAS_DEFEATED_THE_HEADLESS_KNIGHT_YOU_CAN_NOW_REST_EASY = new NpcStringId(70854);
		S1_DO_YOU_DARE_DEFY_MY_SUBORDINATES = new NpcStringId(70855);
		DOES_MY_MISSION_TO_BLOCK_THE_SUPPLIES_END_HERE = new NpcStringId(70856);
		S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_GLUDIO_LONG_MAY_HE_REIGN = new NpcStringId(70859);
		YOULL_SEE_I_WONT_FORGIVE_YOU_NEXT_TIME = new NpcStringId(70957);
		S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_DION_LONG_MAY_HE_REIGN = new NpcStringId(70959);
		ITS_THE_ENEMY_NO_MERCY = new NpcStringId(71052);
		WHAT_ARE_YOU_DOING_WE_ARE_STILL_SUPERIOR = new NpcStringId(71053);
		HOW_INFURIATING_THIS_ENEMY = new NpcStringId(71054);
		S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GIRAN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GIRAN = new NpcStringId(71059);
		MY_LIEGE_WHERE_ARE_YOU = new NpcStringId(71151);
		S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_INNADRIL_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_INNADRIL = new NpcStringId(71159);
		YOU_HAVE_FOUND_ALL_THE_NEBULITE_ORBS = new NpcStringId(71252);
		S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_OREN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_OREN = new NpcStringId(71259);
		S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_ADEN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_ADEN = new NpcStringId(71351);
		S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_SCHUTTGART_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_SCHUTTGART = new NpcStringId(71459);
		S1_I_WILL_REMEMBER_YOU = new NpcStringId(71551);
		FREDERICK_IS_LOOKING_FOR_YOU_MY_LIEGE = new NpcStringId(71653);
		HO_HO_DID_YOU_THINK_YOU_COULD_REALLY_STOP_TRADING_WITH_US = new NpcStringId(71654);
		YOU_HAVE_CHARGED_INTO_THE_TEMPLE = new NpcStringId(71655);
		YOU_ARE_IN_THE_MIDST_OF_DEALING_WITH_THE_HERETIC_OF_HERETIC_TEMPLE = new NpcStringId(71656);
		THE_HERETIC_TEMPLE_IS_DESCENDING_INTO_CHAOS = new NpcStringId(71657);
		S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_RUNE_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_RUNE = new NpcStringId(71659);
		S1_RAISE_YOUR_WEAPONS_FOR_THE_SAKE_OF_THE_TERRITORY = new NpcStringId(71751);
		S1_THE_WAR_IS_OVER_LOWER_YOUR_SWORD_FOR_THE_SAKE_OF_THE_FUTURE = new NpcStringId(71752);
		N91_TERRITORY_BADGES_450_SCORES_IN_INDIVIDUAL_FAME_AND_S2_ADENAS = new NpcStringId(71755);
		THE_MERCENARY_QUEST_NUMBER_IS_S1_MEMOSTATE1_IS_S2_AND_MEMOSTATE2_IS_S3 = new NpcStringId(72903);
		USER_CONNECTED_EVENT_OCCURRENCE_SUCCESS_SIEGE_ID_IS_S1_NUMBER_728_MEMO2_IS_S2_729_MEMO2_IS_S3_AND_255_MEMO1_IS_S4 = new NpcStringId(72904);
		TERRITORY_CATAPULT_DYING_EVENT_CATAPULTS_TERRITORY_ID_S1_PARTY_STATUS_S2 = new NpcStringId(72905);
		PROTECT_THE_CATAPULT_OF_GLUDIO = new NpcStringId(72951);
		PROTECT_THE_CATAPULT_OF_DION = new NpcStringId(72952);
		PROTECT_THE_CATAPULT_OF_GIRAN = new NpcStringId(72953);
		PROTECT_THE_CATAPULT_OF_OREN = new NpcStringId(72954);
		PROTECT_THE_CATAPULT_OF_ADEN = new NpcStringId(72955);
		PROTECT_THE_CATAPULT_OF_INNADRIL = new NpcStringId(72956);
		PROTECT_THE_CATAPULT_OF_GODDARD = new NpcStringId(72957);
		PROTECT_THE_CATAPULT_OF_RUNE = new NpcStringId(72958);
		PROTECT_THE_CATAPULT_OF_SCHUTTGART = new NpcStringId(72959);
		THE_CATAPULT_OF_GLUDIO_HAS_BEEN_DESTROYED = new NpcStringId(72961);
		THE_CATAPULT_OF_DION_HAS_BEEN_DESTROYED = new NpcStringId(72962);
		THE_CATAPULT_OF_GIRAN_HAS_BEEN_DESTROYED = new NpcStringId(72963);
		THE_CATAPULT_OF_OREN_HAS_BEEN_DESTROYED = new NpcStringId(72964);
		THE_CATAPULT_OF_ADEN_HAS_BEEN_DESTROYED = new NpcStringId(72965);
		THE_CATAPULT_OF_INNADRIL_HAS_BEEN_DESTROYED = new NpcStringId(72966);
		THE_CATAPULT_OF_GODDARD_HAS_BEEN_DESTROYED = new NpcStringId(72967);
		THE_CATAPULT_OF_RUNE_HAS_BEEN_DESTROYED = new NpcStringId(72968);
		THE_CATAPULT_OF_SCHUTTGART_HAS_BEEN_DESTROYED = new NpcStringId(72969);
		GLUDIO = new NpcStringId(72981);
		DION = new NpcStringId(72982);
		GIRAN = new NpcStringId(72983);
		OREN = new NpcStringId(72984);
		ADEN = new NpcStringId(72985);
		INNADRIL = new NpcStringId(72986);
		GODDARD = new NpcStringId(72987);
		RUNE = new NpcStringId(72988);
		SCHUTTGART = new NpcStringId(72989);
		PROTECT_THE_SUPPLIES_SAFE_OF_GLUDIO = new NpcStringId(73051);
		PROTECT_THE_SUPPLIES_SAFE_OF_DION = new NpcStringId(73052);
		PROTECT_THE_SUPPLIES_SAFE_OF_GIRAN = new NpcStringId(73053);
		PROTECT_THE_SUPPLIES_SAFE_OF_OREN = new NpcStringId(73054);
		PROTECT_THE_SUPPLIES_SAFE_OF_ADEN = new NpcStringId(73055);
		PROTECT_THE_SUPPLIES_SAFE_OF_INNADRIL = new NpcStringId(73056);
		PROTECT_THE_SUPPLIES_SAFE_OF_GODDARD = new NpcStringId(73057);
		PROTECT_THE_SUPPLIES_SAFE_OF_RUNE = new NpcStringId(73058);
		PROTECT_THE_SUPPLIES_SAFE_OF_SCHUTTGART = new NpcStringId(73059);
		THE_SUPPLIES_SAFE_OF_GLUDIO_HAS_BEEN_DESTROYED = new NpcStringId(73061);
		THE_SUPPLIES_SAFE_OF_DION_HAS_BEEN_DESTROYED = new NpcStringId(73062);
		THE_SUPPLIES_SAFE_OF_GIRAN_HAS_BEEN_DESTROYED = new NpcStringId(73063);
		THE_SUPPLIES_SAFE_OF_OREN_HAS_BEEN_DESTROYED = new NpcStringId(73064);
		THE_SUPPLIES_SAFE_OF_ADEN_HAS_BEEN_DESTROYED = new NpcStringId(73065);
		THE_SUPPLIES_SAFE_OF_INNADRIL_HAS_BEEN_DESTROYED = new NpcStringId(73066);
		THE_SUPPLIES_SAFE_OF_GODDARD_HAS_BEEN_DESTROYED = new NpcStringId(73067);
		THE_SUPPLIES_SAFE_OF_RUNE_HAS_BEEN_DESTROYED = new NpcStringId(73068);
		THE_SUPPLIES_SAFE_OF_SCHUTTGART_HAS_BEEN_DESTROYED = new NpcStringId(73069);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO = new NpcStringId(73151);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_DION = new NpcStringId(73152);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN = new NpcStringId(73153);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_OREN = new NpcStringId(73154);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN = new NpcStringId(73155);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL = new NpcStringId(73156);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD = new NpcStringId(73157);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE = new NpcStringId(73158);
		PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART = new NpcStringId(73159);
		THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD = new NpcStringId(73161);
		THE_MILITARY_ASSOCIATION_LEADER_OF_DION_IS_DEAD = new NpcStringId(73162);
		THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD = new NpcStringId(73163);
		THE_MILITARY_ASSOCIATION_LEADER_OF_OREN_IS_DEAD = new NpcStringId(73164);
		THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD = new NpcStringId(73165);
		THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD = new NpcStringId(73166);
		THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD = new NpcStringId(73167);
		THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD = new NpcStringId(73168);
		THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD = new NpcStringId(73169);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO = new NpcStringId(73251);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION = new NpcStringId(73252);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN = new NpcStringId(73253);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN = new NpcStringId(73254);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN = new NpcStringId(73255);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL = new NpcStringId(73256);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD = new NpcStringId(73257);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE = new NpcStringId(73258);
		PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART = new NpcStringId(73259);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD = new NpcStringId(73261);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION_IS_DEAD = new NpcStringId(73262);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD = new NpcStringId(73263);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN_IS_DEAD = new NpcStringId(73264);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD = new NpcStringId(73265);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD = new NpcStringId(73266);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD = new NpcStringId(73267);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD = new NpcStringId(73268);
		THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD = new NpcStringId(73269);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO = new NpcStringId(73351);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION = new NpcStringId(73352);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN = new NpcStringId(73353);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN = new NpcStringId(73354);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN = new NpcStringId(73355);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL = new NpcStringId(73356);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD = new NpcStringId(73357);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE = new NpcStringId(73358);
		PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART = new NpcStringId(73359);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD = new NpcStringId(73361);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION_IS_DEAD = new NpcStringId(73362);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD = new NpcStringId(73363);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN_IS_DEAD = new NpcStringId(73364);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD = new NpcStringId(73365);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD = new NpcStringId(73366);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD = new NpcStringId(73367);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD = new NpcStringId(73368);
		THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD = new NpcStringId(73369);
		DEFEAT_S1_ENEMY_KNIGHTS = new NpcStringId(73451);
		YOU_HAVE_DEFEATED_S2_OF_S1_KNIGHTS = new NpcStringId(73461);
		YOU_WEAKENED_THE_ENEMYS_DEFENSE = new NpcStringId(73462);
		DEFEAT_S1_WARRIORS_AND_ROGUES = new NpcStringId(73551);
		YOU_HAVE_DEFEATED_S2_OF_S1_WARRIORS_AND_ROGUES = new NpcStringId(73561);
		YOU_WEAKENED_THE_ENEMYS_ATTACK = new NpcStringId(73562);
		DEFEAT_S1_WIZARDS_AND_SUMMONERS = new NpcStringId(73651);
		YOU_HAVE_DEFEATED_S2_OF_S1_ENEMIES = new NpcStringId(73661);
		YOU_WEAKENED_THE_ENEMYS_MAGIC = new NpcStringId(73662);
		DEFEAT_S1_HEALERS_AND_BUFFERS = new NpcStringId(73751);
		YOU_HAVE_DEFEATED_S2_OF_S1_HEALERS_AND_BUFFERS = new NpcStringId(73761);
		YOU_HAVE_WEAKENED_THE_ENEMYS_SUPPORT = new NpcStringId(73762);
		DEFEAT_S1_WARSMITHS_AND_OVERLORDS = new NpcStringId(73851);
		YOU_HAVE_DEFEATED_S2_OF_S1_WARSMITHS_AND_OVERLORDS = new NpcStringId(73861);
		YOU_DESTROYED_THE_ENEMYS_PROFESSIONALS = new NpcStringId(73862);
		TRA_LA_LA_TODAY_IM_GOING_TO_MAKE_ANOTHER_FUN_FILLED_TRIP_I_WONDER_WHAT_I_SHOULD_LOOK_FOR_THIS_TIME = new NpcStringId(99601);
		WHATS_THIS_WHY_AM_I_BEING_DISTURBED = new NpcStringId(99700);
		TA_DA_HERE_I_AM = new NpcStringId(99701);
		WHAT_ARE_YOU_LOOKING_AT = new NpcStringId(99702);
		IF_YOU_GIVE_ME_NECTAR_THIS_LITTLE_SQUASH_WILL_GROW_UP_QUICKLY = new NpcStringId(99703);
		ARE_YOU_MY_MOMMY = new NpcStringId(99704);
		FANCY_MEETING_YOU_HERE = new NpcStringId(99705);
		ARE_YOU_AFRAID_OF_THE_BIG_BAD_SQUASH = new NpcStringId(99706);
		IMPRESSIVE_ARENT_I = new NpcStringId(99707);
		OBEY_ME = new NpcStringId(99708);
		RAISE_ME_WELL_AND_YOULL_BE_REWARDED_NEGLECT_ME_AND_SUFFER_THE_CONSEQUENCES = new NpcStringId(99709);
		TRANSFORM = new NpcStringId(99710);
		I_FEEL_DIFFERENT = new NpcStringId(99711);
		IM_BIGGER_NOW_BRING_IT_ON = new NpcStringId(99712);
		IM_NOT_A_KID_ANYMORE = new NpcStringId(99713);
		BIG_TIME = new NpcStringId(99714);
		IM_ALL_GROWN_UP_NOW = new NpcStringId(99716);
		IF_YOU_LET_ME_GO_ILL_BE_YOUR_BEST_FRIEND = new NpcStringId(99717);
		IM_CHUCK_FULL_OF_GOODNESS = new NpcStringId(99718);
		GOOD_JOB_NOW_WHAT_ARE_YOU_GOING_TO_DO = new NpcStringId(99719);
		KEEP_IT_COMING = new NpcStringId(99720);
		THATS_WHAT_IM_TALKING_ABOUT = new NpcStringId(99721);
		MAY_I_HAVE_SOME_MORE = new NpcStringId(99722);
		THAT_HIT_THE_SPOT = new NpcStringId(99723);
		I_FEEL_SPECIAL = new NpcStringId(99724);
		I_THINK_ITS_WORKING = new NpcStringId(99725);
		YOU_DO_UNDERSTAND = new NpcStringId(99726);
		YUCK_WHAT_IS_THIS_HA_HA_JUST_KIDDING = new NpcStringId(99727);
		A_TOTAL_OF_FIVE_AND_ILL_BE_TWICE_AS_ALIVE = new NpcStringId(99728);
		NECTAR_IS_SUBLIME = new NpcStringId(99729);
		YOU_CALL_THAT_A_HIT = new NpcStringId(99730);
		WHY_ARE_YOU_HITTING_ME_OUCH_STOP_IT_GIVE_ME_NECTAR = new NpcStringId(99731);
		STOP_OR_ILL_WILT = new NpcStringId(99732);
		IM_NOT_FULLY_GROWN_YET_OH_WELL_DO_WHAT_YOU_WILL_ILL_FADE_AWAY_WITHOUT_NECTAR_ANYWAY = new NpcStringId(99733);
		GO_AHEAD_AND_HIT_ME_AGAIN_IT_WONT_DO_YOU_ANY_GOOD = new NpcStringId(99734);
		WOE_IS_ME_IM_WILTING = new NpcStringId(99735);
		IM_NOT_FULLY_GROWN_YET_HOW_ABOUT_SOME_NECTAR_TO_EASE_MY_PAIN = new NpcStringId(99736);
		THE_END_IS_NEAR = new NpcStringId(99737);
		PRETTY_PLEASE_WITH_SUGAR_ON_TOP_GIVE_ME_SOME_NECTAR = new NpcStringId(99738);
		IF_I_DIE_WITHOUT_NECTAR_YOULL_GET_NOTHING = new NpcStringId(99739);
		IM_FEELING_BETTER_ANOTHER_THIRTY_SECONDS_AND_ILL_BE_OUT_OF_HERE = new NpcStringId(99740);
		TWENTY_SECONDS_AND_ITS_CIAO_BABY = new NpcStringId(99741);
		WOOHOO_JUST_TEN_SECONDS_LEFT_NINE_EIGHT_SEVEN = new NpcStringId(99742);
		GIVE_ME_NECTAR_OR_ILL_BE_GONE_IN_TWO_MINUTES = new NpcStringId(99743);
		GIVE_ME_NECTAR_OR_ILL_BE_GONE_IN_ONE_MINUTE = new NpcStringId(99744);
		SO_LONG_SUCKERS = new NpcStringId(99745);
		IM_OUT_OF_HERE = new NpcStringId(99746);
		I_MUST_BE_GOING_HAVE_FUN_EVERYBODY = new NpcStringId(99747);
		TIME_IS_UP_PUT_YOUR_WEAPONS_DOWN = new NpcStringId(99748);
		GOOD_FOR_ME_BAD_FOR_YOU = new NpcStringId(99749);
		SOUNDTASTIC = new NpcStringId(99750);
		I_CAN_SING_ALONG_IF_YOU_LIKE = new NpcStringId(99751);
		I_THINK_YOU_NEED_SOME_BACKUP = new NpcStringId(99752);
		KEEP_UP_THAT_RHYTHM_AND_YOULL_BE_A_STAR = new NpcStringId(99753);
		MY_HEART_YEARNS_FOR_MORE_MUSIC = new NpcStringId(99754);
		YOURE_OUT_OF_TUNE_AGAIN = new NpcStringId(99755);
		THIS_IS_AWFUL = new NpcStringId(99756);
		I_THINK_I_BROKE_SOMETHING = new NpcStringId(99757);
		WHAT_A_LOVELY_MELODY_PLAY_IT_AGAIN = new NpcStringId(99758);
		MUSIC_TO_MY_UH_EARS = new NpcStringId(99759);
		YOU_NEED_MUSIC_LESSONS = new NpcStringId(99760);
		I_CANT_HEAR_YOU = new NpcStringId(99761);
		YOU_CANT_HURT_ME_LIKE_THAT = new NpcStringId(99762);
		IM_STRONGER_THAN_YOU_ARE = new NpcStringId(99763);
		NO_MUSIC_IM_OUT_OF_HERE = new NpcStringId(99764);
		THAT_RACKET_IS_GETTING_ON_MY_NERVES_TONE_IT_DOWN_A_BIT = new NpcStringId(99765);
		YOU_CAN_ONLY_HURT_ME_THROUGH_MUSIC = new NpcStringId(99766);
		ONLY_MUSICAL_INSTRUMENTS_CAN_HURT_ME_NOTHING_ELSE = new NpcStringId(99767);
		YOUR_SKILLS_ARE_IMPRESSIVE_BUT_SADLY_USELESS = new NpcStringId(99768);
		CATCH_A_CHRONO_FOR_ME_PLEASE = new NpcStringId(99769);
		YOU_GOT_ME = new NpcStringId(99770);
		NOW_LOOK_AT_WHAT_YOUVE_DONE = new NpcStringId(99771);
		YOU_WIN = new NpcStringId(99772);
		SQUASHED = new NpcStringId(99773);
		DONT_TELL_ANYONE = new NpcStringId(99774);
		GROSS_MY_GUTS_ARE_COMING_OUT = new NpcStringId(99775);
		TAKE_IT_AND_GO = new NpcStringId(99776);
		I_SHOULDVE_LEFT_WHEN_I_COULD = new NpcStringId(99777);
		NOW_LOOK_WHAT_YOU_HAVE_DONE = new NpcStringId(99778);
		I_FEEL_DIRTY = new NpcStringId(99779);
		BETTER_LUCK_NEXT_TIME = new NpcStringId(99780);
		NICE_SHOT = new NpcStringId(99781);
		IM_NOT_AFRAID_OF_YOU = new NpcStringId(99782);
		IF_I_KNEW_THIS_WAS_GOING_TO_HAPPEN_I_WOULD_HAVE_STAYED_HOME = new NpcStringId(99783);
		TRY_HARDER_OR_IM_OUT_OF_HERE = new NpcStringId(99784);
		IM_TOUGHER_THAN_I_LOOK = new NpcStringId(99785);
		GOOD_STRIKE = new NpcStringId(99786);
		OH_MY_GOURD = new NpcStringId(99787);
		THATS_ALL_YOUVE_GOT = new NpcStringId(99788);
		WHY_ME = new NpcStringId(99789);
		BRING_ME_NECTAR = new NpcStringId(99790);
		I_MUST_HAVE_NECTAR_TO_GROW = new NpcStringId(99791);
		GIVE_ME_SOME_NECTAR_QUICKLY_OR_YOULL_GET_NOTHING = new NpcStringId(99792);
		PLEASE_GIVE_ME_SOME_NECTAR_IM_HUNGRY = new NpcStringId(99793);
		NECTAR_PLEASE = new NpcStringId(99794);
		NECTAR_WILL_MAKE_ME_GROW_QUICKLY = new NpcStringId(99795);
		DONT_YOU_WANT_A_BIGGER_SQUASH_GIVE_ME_SOME_NECTAR_AND_ILL_GROW_MUCH_LARGER = new NpcStringId(99796);
		IF_YOU_RAISE_ME_WELL_YOULL_GET_PRIZES_OR_NOT = new NpcStringId(99797);
		YOU_ARE_HERE_FOR_THE_STUFF_EH_WELL_ITS_MINE_ALL_MINE = new NpcStringId(99798);
		TRUST_ME_GIVE_ME_NECTAR_AND_ILL_BECOME_A_GIANT_SQUASH = new NpcStringId(99799);
		THERES_NOTHING_YOU_CANT_SAY_I_CANT_LISTEN_TO_YOU_ANYMORE = new NpcStringId(528551);
		YOU_ADVANCED_BRAVELY_BUT_GOT_SUCH_A_TINY_RESULT_HOHOHO = new NpcStringId(528651);
		A_NON_PERMITTED_TARGET_HAS_BEEN_DISCOVERED = new NpcStringId(1000001);
		INTRUDER_REMOVAL_SYSTEM_INITIATED = new NpcStringId(1000002);
		REMOVING_INTRUDERS = new NpcStringId(1000003);
		A_FATAL_ERROR_HAS_OCCURRED = new NpcStringId(1000004);
		SYSTEM_IS_BEING_SHUT_DOWN = new NpcStringId(1000005);
		DOT_DOT_DOT_DOT_DOT_DOT = new NpcStringId(1000006);
		WE_SHALL_SEE_ABOUT_THAT = new NpcStringId(1000007);
		I_WILL_DEFINITELY_REPAY_THIS_HUMILIATION = new NpcStringId(1000008);
		RETREAT = new NpcStringId(1000009);
		TACTICAL_RETREAT = new NpcStringId(1000010);
		MASS_FLEEING = new NpcStringId(1000011);
		ITS_STRONGER_THAN_EXPECTED = new NpcStringId(1000012);
		ILL_KILL_YOU_NEXT_TIME = new NpcStringId(1000013);
		ILL_DEFINITELY_KILL_YOU_NEXT_TIME = new NpcStringId(1000014);
		OH_HOW_STRONG = new NpcStringId(1000015);
		INVADER = new NpcStringId(1000016);
		THERE_IS_NO_REASON_FOR_YOU_TO_KILL_ME_I_HAVE_NOTHING_YOU_NEED = new NpcStringId(1000017);
		SOMEDAY_YOU_WILL_PAY = new NpcStringId(1000018);
		I_WONT_JUST_STAND_STILL_WHILE_YOU_HIT_ME = new NpcStringId(1000019);
		STOP_HITTING = new NpcStringId(1000020);
		IT_HURTS_TO_THE_BONE = new NpcStringId(1000021);
		AM_I_THE_NEIGHBORHOOD_DRUM_FOR_BEATING = new NpcStringId(1000022);
		FOLLOW_ME_IF_YOU_WANT = new NpcStringId(1000023);
		SURRENDER = new NpcStringId(1000024);
		OH_IM_DEAD = new NpcStringId(1000025);
		ILL_BE_BACK = new NpcStringId(1000026);
		ILL_GIVE_YOU_TEN_MILLION_ARENA_IF_YOU_LET_ME_LIVE = new NpcStringId(1000027);
		S1_STOP_KIDDING_YOURSELF_ABOUT_YOUR_OWN_POWERLESSNESS = new NpcStringId(1000028);
		S1_ILL_MAKE_YOU_FEEL_WHAT_TRUE_FEAR_IS = new NpcStringId(1000029);
		YOURE_REALLY_STUPID_TO_HAVE_CHALLENGED_ME_S1_GET_READY = new NpcStringId(1000030);
		S1_DO_YOU_THINK_THATS_GOING_TO_WORK = new NpcStringId(1000031);
		I_WILL_DEFINITELY_RECLAIM_MY_HONOR_WHICH_HAS_BEEN_TARNISHED = new NpcStringId(1000032);
		SHOW_ME_THE_WRATH_OF_THE_KNIGHT_WHOSE_HONOR_HAS_BEEN_DOWNTRODDEN = new NpcStringId(1000033);
		DEATH_TO_THE_HYPOCRITE = new NpcStringId(1000034);
		ILL_NEVER_SLEEP_UNTIL_IVE_SHED_MY_DISHONOR = new NpcStringId(1000035);
		IM_HERE_FOR_THE_ONES_THAT_ARE_CURSING_THE_WORLD = new NpcStringId(1000036);
		ILL_TURN_YOU_INTO_A_MALIGNANT_SPIRIT = new NpcStringId(1000037);
		ILL_CURSE_YOU_WITH_THE_POWER_OF_REVENGE_AND_HATE = new NpcStringId(1000038);
		FOR_THE_GLORY_OF_GRACIA = new NpcStringId(1000039);
		DO_YOU_DARE_PIT_YOUR_POWER_AGAINST_ME = new NpcStringId(1000040);
		I_I_AM_DEFEATED = new NpcStringId(1000041);
		I_AM_CONVEYING_THE_WILL_OF_NURKA_EVERYBODY_GET_OUT_OF_MY_WAY = new NpcStringId(1000042);
		THOSE_WHO_STAND_AGAINST_ME_SHALL_DIE_HORRIBLY = new NpcStringId(1000043);
		DO_YOU_DARE_TO_BLOCK_MY_WAY = new NpcStringId(1000044);
		MY_COMRADES_WILL_GET_REVENGE = new NpcStringId(1000045);
		YOU_HEATHEN_BLASPHEMERS_OF_THIS_HOLY_PLACE_WILL_BE_PUNISHED = new NpcStringId(1000046);
		STEP_FORWARD_YOU_WORTHLESS_CREATURES_WHO_CHALLENGE_MY_AUTHORITY = new NpcStringId(1000047);
		MY_CREATOR_THE_UNCHANGING_FAITHFULNESS_TO_MY_MASTER = new NpcStringId(1000048);
		MASTER_OF_THE_TOWER_MY_MASTER_MASTER_WHERE_IS_HE = new NpcStringId(1000049);
		I_AM_THE_ONE_CARRYING_OUT_THE_WILL_OF_CORE = new NpcStringId(1000050);
		DESTROY_THE_INVADER = new NpcStringId(1000051);
		STRANGE_CONDITION_DOESNT_WORK = new NpcStringId(1000052);
		ACCORDING_TO_THE_COMMAND_OF_BELETH_IM_GOING_TO_OBSERVE_YOU_GUYS = new NpcStringId(1000053);
		YOU_PEOPLE_MAKE_ME_SICK_NO_SENSE_OF_LOYALTY_WHATSOEVER = new NpcStringId(1000054);
		A_CHALLENGE_AGAINST_ME_IS_THE_SAME_AS_A_CHALLENGE_AGAINST_BELETH = new NpcStringId(1000055);
		BELETH_IS_ALWAYS_WATCHING_OVER_YOU_GUYS = new NpcStringId(1000056);
		THAT_WAS_REALLY_CLOSE_ANTHARAS_OPENED_ITS_EYES = new NpcStringId(1000057);
		YOU_WHO_DISOBEY_THE_WILL_OF_ANTHARAS_DIE = new NpcStringId(1000058);
		ANTHARAS_HAS_TAKEN_MY_LIFE = new NpcStringId(1000059);
		I_CROSSED_BACK_OVER_THE_MARSHLANDS_OF_DEATH_TO_RECLAIM_THE_TREASURE = new NpcStringId(1000060);
		BRING_OVER_AND_SURRENDER_YOUR_PRECIOUS_GOLD_TREASURE_TO_ME = new NpcStringId(1000061);
		ILL_KILL_YOU_IN_AN_INSTANT = new NpcStringId(1000062);
		NO_THE_TREASURE_IS_STILL = new NpcStringId(1000063);
		INVADERS_OF_DRAGON_VALLEY_WILL_NEVER_LIVE_TO_RETURN = new NpcStringId(1000064);
		I_AM_THE_GUARDIAN_THAT_HONORS_THE_COMMAND_OF_ANTHARAS_TO_WATCH_OVER_THIS_PLACE = new NpcStringId(1000065);
		YOUVE_SET_FOOT_IN_DRAGON_VALLEY_WITHOUT_PERMISSION_THE_PENALTY_IS_DEATH = new NpcStringId(1000066);
		THE_JOY_OF_KILLING_THE_ECSTASY_OF_LOOTING_HEY_GUYS_LETS_HAVE_A_GO_AT_IT_AGAIN = new NpcStringId(1000068);
		THERE_REALLY_ARE_STILL_LOTS_OF_FOLKS_IN_THE_WORLD_WITHOUT_FEAR_ILL_TEACH_YOU_A_LESSON = new NpcStringId(1000069);
		IF_YOU_HAND_OVER_EVERYTHING_YOUVE_GOT_ILL_AT_LEAST_SPARE_YOUR_LIFE = new NpcStringId(1000070);
		KNEEL_DOWN_BEFORE_ONE_SUCH_AS_THIS = new NpcStringId(1000071);
		HONOR_THE_MASTERS_WISHES_AND_PUNISH_ALL_THE_INVADERS = new NpcStringId(1000072);
		FOLLOW_THE_MASTERS_WISHES_AND_PUNISH_THE_INVADERS = new NpcStringId(1000073);
		DEATH_IS_NOTHING_MORE_THAN_A_MOMENTARY_REST = new NpcStringId(1000074);
		LISTEN_THIS_IS_THE_END_OF_THE_HUMAN_ERA_ANTHARAS_HAS_AWOKEN = new NpcStringId(1000075);
		PRESENT_THE_LIVES_OF_FOUR_PEOPLE_TO_ANTHARAS = new NpcStringId(1000076);
		THIS_IS_UNBELIEVABLE_HOW_COULD_I_HAVE_LOST_TO_ONE_SO_INFERIOR_TO_MYSELF = new NpcStringId(1000077);
		I_CARRY_THE_POWER_OF_DARKNESS_AND_HAVE_RETURNED_FROM_THE_ABYSS = new NpcStringId(1000078);
		ITS_DETESTABLE = new NpcStringId(1000079);
		I_FINALLY_FIND_REST = new NpcStringId(1000080);
		GLORY_TO_ORFEN = new NpcStringId(1000081);
		IN_THE_NAME_OF_ORFEN_I_CAN_NEVER_FORGIVE_YOU_WHO_ARE_INVADING_THIS_PLACE = new NpcStringId(1000082);
		ILL_MAKE_YOU_PAY_THE_PRICE_FOR_FEARLESSLY_ENTERING_ORFENS_LAND = new NpcStringId(1000083);
		EVEN_IF_YOU_DISAPPEAR_INTO_NOTHINGNESS_YOU_WILL_STILL_FACE_THE_LIFE_LONG_SUFFERING_OF_THE_CURSE_THAT_I_HAVE_GIVEN_YOU = new NpcStringId(1000084);
		ILL_STAND_AGAINST_ANYONE_THAT_MAKES_LIGHT_OF_THE_SACRED_PLACE_OF_THE_ELVES = new NpcStringId(1000085);
		I_WILL_KILL_WITH_MY_OWN_HANDS_ANYONE_THAT_DEFILES_OUR_HOME = new NpcStringId(1000086);
		MY_BROTHERS_WILL_NEVER_REST_UNTIL_WE_PUSH_YOU_AND_YOUR_GANG_OUT_OF_THIS_VALLEY = new NpcStringId(1000087);
		UNTIL_THE_DAY_OF_DESTRUCTION_OF_HESTUI = new NpcStringId(1000088);
		IF_ANY_INTREPID_ORCS_REMAIN_ATTACK_THEM = new NpcStringId(1000089);
		ILL_BREAK_YOUR_WINDPIPE = new NpcStringId(1000090);
		IS_REVENGE_A_FAILURE = new NpcStringId(1000091);
		THE_SPARKLING_MITHRIL_OF_THE_DWARVES_AND_THEIR_PRETTY_TREASURES_ILL_GET_THEM_ALL = new NpcStringId(1000092);
		WHERE_ARE_ALL_THE_DREADFUL_DWARVES_AND_THEIR_SPARKLING_THINGS = new NpcStringId(1000093);
		HAND_OVER_YOUR_PRETTY_TREASURES = new NpcStringId(1000094);
		HEY_YOU_SHOULD_HAVE_RUN_AWAY = new NpcStringId(1000095);
		DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE_DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE = new NpcStringId(1000096);
		DESTRUCTION_DESTRUCTION_DESTRUCTION_DESTRUCTION = new NpcStringId(1000097);
		DESTRUCTION_DESTRUCTION_DESTRUCTION = new NpcStringId(1000098);
		TA_DA_UTHANKA_HAS_RETURNED = new NpcStringId(1000099);
		WAH_HA_HA_HA_UTHANKA_HAS_TAKEN_OVER_THIS_ISLAND_TODAY = new NpcStringId(1000100);
		WHEW_HES_QUITE_A_GUY = new NpcStringId(1000101);
		HOW_EXASPERATING_AND_UNFAIR_TO_HAVE_THINGS_HAPPEN_IN_SUCH_A_MEANINGLESS_WAY_LIKE_THIS = new NpcStringId(1000102);
		THIS_WORLD_SHOULD_BE_FILLED_WITH_FEAR_AND_SADNESS = new NpcStringId(1000103);
		I_WONT_FORGIVE_THE_WORLD_THAT_CURSED_ME = new NpcStringId(1000104);
		ILL_MAKE_EVERYONE_FEEL_THE_SAME_SUFFERING_AS_ME = new NpcStringId(1000105);
		ILL_GIVE_YOU_A_CURSE_THAT_YOULL_NEVER_BE_ABLE_TO_REMOVE_FOREVER = new NpcStringId(1000106);
		ILL_GET_REVENGE_ON_YOU_WHO_SLAUGHTERED_MY_COMPATRIOTS = new NpcStringId(1000107);
		THOSE_WHO_ARE_AFRAID_SHOULD_GET_AWAY_AND_THOSE_WHO_ARE_BRAVE_SHOULD_FIGHT = new NpcStringId(1000108);
		IVE_GOT_POWER_FROM_BELETH_SO_DO_YOU_THINK_ILL_BE_EASILY_DEFEATED = new NpcStringId(1000109);
		I_AM_LEAVING_NOW_BUT_SOON_SOMEONE_WILL_COME_WHO_WILL_TEACH_YOU_ALL_A_LESSON = new NpcStringId(1000110);
		HEY_GUYS_LETS_MAKE_A_ROUND_OF_OUR_TERRITORY = new NpcStringId(1000111);
		THE_RUMOR_IS_THAT_THERE_ARE_WILD_UNCIVILIZED_RUFFIANS_WHO_HAVE_RECENTLY_ARRIVED_IN_MY_TERRITORY = new NpcStringId(1000112);
		DO_YOU_KNOW_WHO_I_AM_I_AM_SIROCCO_EVERYONE_ATTACK = new NpcStringId(1000113);
		WHATS_JUST_HAPPENED_THE_INVINCIBLE_SIROCCO_WAS_DEFEATED_BY_SOMEONE_LIKE_YOU = new NpcStringId(1000114);
		OH_IM_REALLY_HUNGRY = new NpcStringId(1000115);
		I_SMELL_FOOD_OOH = new NpcStringId(1000116);
		OOH = new NpcStringId(1000117);
		WHAT_DOES_HONEY_OF_THIS_PLACE_TASTE_LIKE = new NpcStringId(1000118);
		GIVE_ME_SOME_SWEET_DELICIOUS_GOLDEN_HONEY = new NpcStringId(1000119);
		IF_YOU_GIVE_ME_SOME_HONEY_ILL_AT_LEAST_SPARE_YOUR_LIFE = new NpcStringId(1000120);
		ONLY_FOR_LACK_OF_HONEY_DID_I_LOSE_TO_THE_LIKES_OF_YOU = new NpcStringId(1000121);
		WHERE_IS_THE_TRAITOR_KUROBOROS = new NpcStringId(1000122);
		LOOK_IN_EVERY_NOOK_AND_CRANNY_AROUND_HERE = new NpcStringId(1000123);
		ARE_YOU_LACKEY_OF_KUROBOROS_ILL_KNOCK_YOU_OUT_IN_ONE_SHOT = new NpcStringId(1000124);
		HE_JUST_CLOSED_HIS_EYES_WITHOUT_DISPOSING_OF_THE_TRAITOR_HOW_UNFAIR = new NpcStringId(1000125);
		HELL_FOR_UNBELIEVERS_IN_KUROBOROS = new NpcStringId(1000126);
		THE_PERSON_THAT_DOES_NOT_BELIEVE_IN_KUROBOROS_HIS_LIFE_WILL_SOON_BECOME_HELL = new NpcStringId(1000127);
		THE_LACKEY_OF_THAT_DEMENTED_DEVIL_THE_SERVANT_OF_A_FALSE_GOD_ILL_SEND_THAT_FOOL_STRAIGHT_TO_HELL = new NpcStringId(1000128);
		UH_IM_NOT_DYING_IM_JUST_DISAPPEARING_FOR_A_MOMENT_ILL_RESURRECT_AGAIN = new NpcStringId(1000129);
		HAIL_TO_KUROBOROS_THE_FOUNDER_OF_OUR_RELIGION = new NpcStringId(1000130);
		ONLY_THOSE_WHO_BELIEVE_IN_PATRIARCH_KUROBOROS_SHALL_RECEIVE_SALVATION = new NpcStringId(1000131);
		ARE_YOU_THE_ONES_THAT_SHARUK_HAS_INCITED_YOU_ALSO_SHOULD_TRUST_IN_KUROBOROS_AND_BE_SAVED = new NpcStringId(1000132);
		KUROBOROS_WILL_PUNISH_YOU = new NpcStringId(1000133);
		YOU_WHO_HAVE_BEAUTIFUL_SPIRITS_THAT_SHINE_BRIGHTLY_I_HAVE_RETURNED = new NpcStringId(1000134);
		YOU_THAT_ARE_WEARY_AND_EXHAUSTED_ENTRUST_YOUR_SOULS_TO_ME = new NpcStringId(1000135);
		THE_COLOR_OF_YOUR_SOUL_IS_VERY_ATTRACTIVE = new NpcStringId(1000136);
		THOSE_OF_YOU_WHO_LIVE_DO_YOU_KNOW_HOW_BEAUTIFUL_YOUR_SOULS_ARE = new NpcStringId(1000137);
		IT_WILL_KILL_EVERYONE = new NpcStringId(1000138);
		IM_SO_LONELY = new NpcStringId(1000139);
		MY_ENEMY = new NpcStringId(1000140);
		_NOW_IM_NOT_SO_LONELY = new NpcStringId(1000141);
		I_WILL_NEVER_FORGIVE_THE_PIXY_MURIKA_THAT_IS_TRYING_TO_KILL_US = new NpcStringId(1000142);
		ATTACK_ALL_THE_DULL_AND_STUPID_FOLLOWERS_OF_MURIKA = new NpcStringId(1000143);
		I_DIDNT_HAVE_ANY_IDEA_ABOUT_SUCH_AMBITIONS = new NpcStringId(1000144);
		THIS_IS_NOT_THE_END_ITS_JUST_THE_BEGINNING = new NpcStringId(1000145);
		HEY_SHALL_WE_HAVE_SOME_FUN_FOR_THE_FIRST_TIME_IN_A_LONG_WHILE = new NpcStringId(1000146);
		THEREVE_BEEN_SOME_THINGS_GOING_AROUND_LIKE_CRAZY_HERE_RECENTLY = new NpcStringId(1000147);
		HEY_DO_YOU_KNOW_WHO_I_AM_I_AM_MALEX_HERALD_OF_DAGONIEL_ATTACK = new NpcStringId(1000148);
		WHATS_JUST_HAPPENED_THE_INVINCIBLE_MALEX_JUST_LOST_TO_THE_LIKES_OF_YOU = new NpcStringId(1000149);
		ITS_SOMETHING_REPEATED_IN_A_VAIN_LIFE = new NpcStringId(1000150);
		SHAKE_IN_FEAR_ALL_YOU_WHO_VALUE_YOUR_LIVES = new NpcStringId(1000151);
		ILL_MAKE_YOU_FEEL_SUFFERING_LIKE_A_FLAME_THAT_IS_NEVER_EXTINGUISHED = new NpcStringId(1000152);
		BACK_TO_THE_DIRT = new NpcStringId(1000153);
		HAIL_VARIKA = new NpcStringId(1000154);
		NOBODY_CAN_STOP_US = new NpcStringId(1000155);
		YOU_MOVE_SLOWLY = new NpcStringId(1000156);
		VARIKA_GO_FIRST = new NpcStringId(1000157);
		WHERE_AM_I_WHO_AM_I = new NpcStringId(1000158);
		UH_MY_HEAD_HURTS_LIKE_ITS_GOING_TO_BURST_WHO_AM_I = new NpcStringId(1000159);
		YOU_JERK_YOURE_A_DEVIL_YOURE_A_DEVIL_TO_HAVE_MADE_ME_LIKE_THIS = new NpcStringId(1000160);
		WHERE_AM_I_WHAT_HAPPENED_THANK_YOU = new NpcStringId(1000161);
		UKRU_MASTER = new NpcStringId(1000162);
		ARE_YOU_MATU = new NpcStringId(1000163);
		MARAK_TUBARIN_SABARACHA = new NpcStringId(1000164);
		PAAGRIO_TAMA = new NpcStringId(1000165);
		ACCEPT_THE_WILL_OF_ICARUS = new NpcStringId(1000166);
		THE_PEOPLE_WHO_ARE_BLOCKING_MY_WAY_WILL_NOT_BE_FORGIVEN = new NpcStringId(1000167);
		YOU_ARE_SCUM = new NpcStringId(1000168);
		YOU_LACK_POWER = new NpcStringId(1000169);
		RETURN = new NpcStringId(1000170);
		ADENA_HAS_BEEN_TRANSFERRED = new NpcStringId(1000171);
		EVENT_NUMBER = new NpcStringId(1000172);
		FIRST_PRIZE = new NpcStringId(1000173);
		SECOND_PRIZE = new NpcStringId(1000174);
		THIRD_PRIZE = new NpcStringId(1000175);
		FOURTH_PRIZE = new NpcStringId(1000176);
		THERE_HAS_BEEN_NO_WINNING_LOTTERY_TICKET = new NpcStringId(1000177);
		THE_MOST_RECENT_WINNING_LOTTERY_NUMBERS = new NpcStringId(1000178);
		YOUR_LUCKY_NUMBERS_HAVE_BEEN_SELECTED_ABOVE = new NpcStringId(1000179);
		I_WONDER_WHO_IT_IS_THAT_IS_LURKING_ABOUT = new NpcStringId(1000180);
		SACRED_MAGICAL_RESEARCH_IS_CONDUCTED_HERE = new NpcStringId(1000181);
		BEHOLD_THE_AWESOME_POWER_OF_MAGIC = new NpcStringId(1000182);
		YOUR_POWERS_ARE_IMPRESSIVE_BUT_YOU_MUST_NOT_ANNOY_OUR_HIGH_LEVEL_SORCERER = new NpcStringId(1000183);
		I_AM_BARDA_MASTER_OF_THE_BANDIT_STRONGHOLD = new NpcStringId(1000184);
		I_MASTER_BARDA_ONCE_OWNED_THAT_STRONGHOLD = new NpcStringId(1000185);
		AH_VERY_INTERESTING = new NpcStringId(1000186);
		YOU_ARE_MORE_POWERFUL_THAN_YOU_APPEAR_WELL_MEET_AGAIN = new NpcStringId(1000187);
		YOU_FILTHY_SORCERERS_DISGUST_ME = new NpcStringId(1000188);
		WHY_WOULD_YOU_BUILD_A_TOWER_IN_OUR_TERRITORY = new NpcStringId(1000189);
		ARE_YOU_PART_OF_THAT_EVIL_GANG_OF_SORCERERS = new NpcStringId(1000190);
		THAT_IS_WHY_I_DONT_BOTHER_WITH_ANYONE_BELOW_THE_LEVEL_OF_SORCERER = new NpcStringId(1000191);
		AH_ANOTHER_BEAUTIFUL_DAY = new NpcStringId(1000192);
		OUR_SPECIALTIES_ARE_ARSON_AND_LOOTING = new NpcStringId(1000193);
		YOU_WILL_LEAVE_EMPTY_HANDED = new NpcStringId(1000194);
		AH_SO_YOU_ADMIRE_MY_TREASURE_DO_YOU_TRY_FINDING_IT_HA = new NpcStringId(1000195);
		IS_EVERYBODY_LISTENING_SIRION_HAS_COME_BACK_EVERYONE_CHANT_AND_BOW = new NpcStringId(1000196);
		BOW_DOWN_YOU_WORTHLESS_HUMANS = new NpcStringId(1000197);
		VERY_TACKY = new NpcStringId(1000198);
		DONT_THINK_THAT_YOU_ARE_INVINCIBLE_JUST_BECAUSE_YOU_DEFEATED_ME = new NpcStringId(1000199);
		THE_MATERIAL_DESIRES_OF_MORTALS_ARE_ULTIMATELY_MEANINGLESS = new NpcStringId(1000200);
		DO_NOT_FORGET_THE_REASON_THE_TOWER_OF_INSOLENCE_COLLAPSED = new NpcStringId(1000201);
		YOU_HUMANS_ARE_ALL_ALIKE_FULL_OF_GREED_AND_AVARICE = new NpcStringId(1000202);
		ALL_FOR_NOTHING = new NpcStringId(1000203);
		WHAT_ARE_ALL_THESE_PEOPLE_DOING_HERE = new NpcStringId(1000204);
		I_MUST_FIND_THE_SECRET_OF_ETERNAL_LIFE_HERE_AMONG_THESE_ROTTEN_ANGELS = new NpcStringId(1000205);
		DO_YOU_ALSO_SEEK_THE_SECRET_OF_IMMORTALITY = new NpcStringId(1000206);
		I_SHALL_NEVER_REVEAL_MY_SECRETS = new NpcStringId(1000207);
		WHO_DARES_ENTER_THIS_PLACE = new NpcStringId(1000208);
		THIS_IS_NO_PLACE_FOR_HUMANS_YOU_MUST_LEAVE_IMMEDIATELY = new NpcStringId(1000209);
		YOU_POOR_CREATURES_TOO_STUPID_TO_REALIZE_YOUR_OWN_IGNORANCE = new NpcStringId(1000210);
		YOU_MUSTNT_GO_THERE = new NpcStringId(1000211);
		WHO_DARES_DISTURB_THIS_MARSH = new NpcStringId(1000212);
		THE_HUMANS_MUST_NOT_BE_ALLOWED_TO_DESTROY_THE_MARSHLAND_FOR_THEIR_GREEDY_PURPOSES = new NpcStringId(1000213);
		YOU_ARE_A_BRAVE_MAN = new NpcStringId(1000214);
		YOU_IDIOTS_SOME_DAY_YOU_SHALL_ALSO_BE_GONE = new NpcStringId(1000215);
		SOMEONE_HAS_ENTERED_THE_FOREST = new NpcStringId(1000216);
		THE_FOREST_IS_VERY_QUIET_AND_PEACEFUL = new NpcStringId(1000217);
		STAY_HERE_IN_THIS_WONDERFUL_FOREST = new NpcStringId(1000218);
		MY_MY_SOULS = new NpcStringId(1000219);
		THIS_FOREST_IS_A_DANGEROUS_PLACE = new NpcStringId(1000220);
		UNLESS_YOU_LEAVE_THIS_FOREST_IMMEDIATELY_YOU_ARE_BOUND_TO_RUN_INTO_SERIOUS_TROUBLE = new NpcStringId(1000221);
		LEAVE_NOW = new NpcStringId(1000222);
		WHY_DO_YOU_IGNORE_MY_WARNING = new NpcStringId(1000223);
		HARITS_OF_THE_WORLD_I_BRING_YOU_PEACE = new NpcStringId(1000224);
		HARITS_BE_COURAGEOUS = new NpcStringId(1000225);
		I_SHALL_EAT_YOUR_STILL_BEATING_HEART = new NpcStringId(1000226);
		HARITS_KEEP_FAITH_UNTIL_THE_DAY_I_RETURN_NEVER_LOSE_HOPE = new NpcStringId(1000227);
		EVEN_THE_GIANTS_ARE_GONE_THERES_NOTHING_LEFT_TO_BE_AFRAID_OF_NOW = new NpcStringId(1000228);
		HAVE_YOU_HEARD_OF_THE_GIANTS_THEIR_DOWNFALL_WAS_INEVITABLE = new NpcStringId(1000229);
		WHAT_NERVE_DO_YOU_DARE_TO_CHALLENGE_ME = new NpcStringId(1000230);
		YOU_ARE_AS_EVIL_AS_THE_GIANTS = new NpcStringId(1000231);
		THIS_DUNGEON_IS_STILL_IN_GOOD_CONDITION = new NpcStringId(1000232);
		THIS_PLACE_IS_SPECTACULAR_WOULDNT_YOU_SAY = new NpcStringId(1000233);
		YOU_ARE_VERY_BRAVE_WARRIORS = new NpcStringId(1000234);
		ARE_THE_GIANTS_TRULY_GONE_FOR_GOOD = new NpcStringId(1000235);
		THESE_GRAVES_ARE_GOOD = new NpcStringId(1000236);
		GOLD_AND_SILVER_ARE_MEANINGLESS_TO_A_DEAD_MAN = new NpcStringId(1000237);
		WHY_WOULD_THOSE_CORRUPT_ARISTOCRATS_BURY_SUCH_USEFUL_THINGS = new NpcStringId(1000238);
		YOU_FILTHY_PIG_EAT_AND_BE_MERRY_NOW_THAT_YOU_HAVE_SHIRKED_YOUR_RESPONSIBILITIES = new NpcStringId(1000239);
		THOSE_THUGS_IT_WOULD_BE_TOO_MERCIFUL_TO_RIP_THEM_APART_AND_CHEW_THEM_UP_ONE_AT_A_TIME = new NpcStringId(1000240);
		YOU_ACCURSED_SCOUNDRELS = new NpcStringId(1000241);
		HMM_COULD_THIS_BE_THE_ASSASSIN_SENT_BY_THOSE_IDIOTS_FROM_ADEN = new NpcStringId(1000242);
		I_SHALL_CURSE_YOUR_NAME_WITH_MY_LAST_BREATH = new NpcStringId(1000243);
		MY_BELOVED_LORD_SHILEN = new NpcStringId(1000244);
		I_MUST_BREAK_THE_SEAL_AND_RELEASE_LORD_SHILEN_AS_SOON_AS_POSSIBLE = new NpcStringId(1000245);
		YOU_SHALL_TASTE_THE_VENGEANCE_OF_LORD_SHILEN = new NpcStringId(1000246);
		LORD_SHILEN_SOME_DAY_YOU_WILL_ACCOMPLISH_THIS_MISSION = new NpcStringId(1000247);
		TOWARDS_IMMORTALITY = new NpcStringId(1000248);
		HE_WHO_DESIRES_IMMORTALITY_COME_UNTO_ME = new NpcStringId(1000249);
		YOU_SHALL_BE_SACRIFICED_TO_GAIN_MY_IMMORTALITY = new NpcStringId(1000250);
		ETERNAL_LIFE_IN_FRONT_OF_MY_EYES_I_HAVE_COLLAPSED_IN_SUCH_A_WORTHLESS_WAY_LIKE_THIS = new NpcStringId(1000251);
		ZAKEN_YOU_ARE_A_COWARDLY_CUR = new NpcStringId(1000252);
		YOU_ARE_IMMORTAL_ARENT_YOU_ZAKEN = new NpcStringId(1000253);
		PLEASE_RETURN_MY_BODY_TO_ME = new NpcStringId(1000254);
		FINALLY_WILL_I_BE_ABLE_TO_REST = new NpcStringId(1000255);
		WHAT_IS_ALL_THAT_RACKET = new NpcStringId(1000256);
		MASTER_GILDOR_DOES_NOT_LIKE_TO_BE_DISTURBED = new NpcStringId(1000257);
		PLEASE_JUST_HOLD_IT_DOWN = new NpcStringId(1000258);
		IF_YOU_DISTURB_MASTER_GILDOR_I_WONT_BE_ABLE_TO_HELP_YOU = new NpcStringId(1000259);
		WHO_DARES_APPROACH = new NpcStringId(1000260);
		THESE_REEDS_ARE_MY_TERRITORY = new NpcStringId(1000261);
		YOU_FOOLS_TODAY_YOU_SHALL_LEARN_A_LESSON = new NpcStringId(1000262);
		THE_PAST_GOES_BY_IS_A_NEW_ERA_BEGINNING = new NpcStringId(1000263);
		THIS_IS_THE_GARDEN_OF_EVA = new NpcStringId(1000264);
		THE_GARDEN_OF_EVA_IS_A_SACRED_PLACE = new NpcStringId(1000265);
		DO_YOU_MEAN_TO_INSULT_EVA = new NpcStringId(1000266);
		HOW_RUDE_EVAS_LOVE_IS_AVAILABLE_TO_ALL_EVEN_TO_AN_ILL_MANNERED_LOUT_LIKE_YOURSELF = new NpcStringId(1000267);
		THIS_PLACE_ONCE_BELONGED_TO_LORD_SHILEN = new NpcStringId(1000268);
		LEAVE_THIS_PALACE_TO_US_SPIRITS_OF_EVA = new NpcStringId(1000269);
		WHY_ARE_YOU_GETTING_IN_OUR_WAY = new NpcStringId(1000270);
		SHILEN_OUR_SHILEN = new NpcStringId(1000271);
		ALL_WHO_FEAR_OF_FAFURION_LEAVE_THIS_PLACE_AT_ONCE = new NpcStringId(1000272);
		YOU_ARE_BEING_PUNISHED_IN_THE_NAME_OF_FAFURION = new NpcStringId(1000273);
		OH_MASTER_PLEASE_FORGIVE_YOUR_HUMBLE_SERVANT = new NpcStringId(1000274);
		PREPARE_TO_DIE_FOREIGN_INVADERS_I_AM_GUSTAV_THE_ETERNAL_RULER_OF_THIS_FORTRESS_AND_I_HAVE_TAKEN_UP_MY_SWORD_TO_REPEL_THEE = new NpcStringId(1000275);
		GLORY_TO_ADEN_THE_KINGDOM_OF_THE_LION_GLORY_TO_SIR_GUSTAV_OUR_IMMORTAL_LORD = new NpcStringId(1000276);
		SOLDIERS_OF_GUSTAV_GO_FORTH_AND_DESTROY_THE_INVADERS = new NpcStringId(1000277);
		THIS_IS_UNBELIEVABLE_HAVE_I_REALLY_BEEN_DEFEATED_I_SHALL_RETURN_AND_TAKE_YOUR_HEAD = new NpcStringId(1000278);
		COULD_IT_BE_THAT_I_HAVE_REACHED_MY_END_I_CANNOT_DIE_WITHOUT_HONOR_WITHOUT_THE_PERMISSION_OF_SIR_GUSTAV = new NpcStringId(1000279);
		AH_THE_BITTER_TASTE_OF_DEFEAT_I_FEAR_MY_TORMENTS_ARE_NOT_OVER = new NpcStringId(1000280);
		I_FOLLOW_THE_WILL_OF_FAFURION = new NpcStringId(1000281);
		TICKETS_FOR_THE_LUCKY_LOTTERY_ARE_NOW_ON_SALE = new NpcStringId(1000282);
		THE_LUCKY_LOTTERY_DRAWING_IS_ABOUT_TO_BEGIN = new NpcStringId(1000283);
		THE_WINNING_NUMBERS_FOR_LUCKY_LOTTERY_S1_ARE_S2_CONGRATULATIONS_TO_THE_WINNERS = new NpcStringId(1000284);
		YOURE_TOO_YOUNG_TO_PLAY_LUCKY_LOTTERY = new NpcStringId(1000285);
		S1_WATCH_YOUR_BACK = new NpcStringId(1000286);
		S1_COME_ON_ILL_TAKE_YOU_ON = new NpcStringId(1000287);
		S1_HOW_DARE_YOU_INTERRUPT_OUR_FIGHT_HEY_GUYS_HELP = new NpcStringId(1000288);
		ILL_HELP_YOU_IM_NO_COWARD = new NpcStringId(1000289);
		DEAR_ULTIMATE_POWER = new NpcStringId(1000290);
		EVERYBODY_ATTACK_S1 = new NpcStringId(1000291);
		I_WILL_FOLLOW_YOUR_ORDER = new NpcStringId(1000292);
		BET_YOU_DIDNT_EXPECT_THIS = new NpcStringId(1000293);
		COME_OUT_YOU_CHILDREN_OF_DARKNESS = new NpcStringId(1000294);
		SUMMON_PARTY_MEMBERS = new NpcStringId(1000295);
		MASTER_DID_YOU_CALL_ME = new NpcStringId(1000296);
		YOU_IDIOT = new NpcStringId(1000297);
		WHAT_ABOUT_THIS = new NpcStringId(1000298);
		VERY_IMPRESSIVE_S1_THIS_IS_THE_LAST = new NpcStringId(1000299);
		DAWN = new NpcStringId(1000300);
		DUSK = new NpcStringId(1000301);
		NOTHINGNESS = new NpcStringId(1000302);
		THIS_WORLD_WILL_SOON_BE_ANNIHILATED = new NpcStringId(1000303);
		A_CURSE_UPON_YOU = new NpcStringId(1000304);
		THE_DAY_OF_JUDGMENT_IS_NEAR = new NpcStringId(1000305);
		I_BESTOW_UPON_YOU_A_BLESSING = new NpcStringId(1000306);
		THE_FIRST_RULE_OF_FIGHTING_IS_TO_START_BY_KILLING_THE_WEAK_ONES = new NpcStringId(1000307);
		ADENA = new NpcStringId(1000308);
		ANCIENT_ADENA = new NpcStringId(1000309);
		LEVEL_31_OR_LOWER = new NpcStringId(1000312);
		LEVEL_42_OR_LOWER = new NpcStringId(1000313);
		LEVEL_53_OR_LOWER = new NpcStringId(1000314);
		LEVEL_64_OR_LOWER = new NpcStringId(1000315);
		NO_LEVEL_LIMIT = new NpcStringId(1000316);
		THE_MAIN_EVENT_WILL_START_IN_2_MINUTES_PLEASE_REGISTER_NOW = new NpcStringId(1000317);
		THE_MAIN_EVENT_IS_NOW_STARTING = new NpcStringId(1000318);
		THE_MAIN_EVENT_WILL_CLOSE_IN_5_MINUTES = new NpcStringId(1000319);
		THE_MAIN_EVENT_WILL_FINISH_IN_2_MINUTES_PLEASE_PREPARE_FOR_THE_NEXT_GAME = new NpcStringId(1000320);
		THE_AMOUNT_OF_SSQ_CONTRIBUTION_HAS_INCREASED_BY_S1 = new NpcStringId(1000321);
		NO_RECORD_EXISTS = new NpcStringId(1000322);
		THAT_WILL_DO_ILL_MOVE_YOU_TO_THE_OUTSIDE_SOON = new NpcStringId(1000380);
		YOUR_REAR_IS_PRACTICALLY_UNGUARDED = new NpcStringId(1000382);
		HOW_DARE_YOU_TURN_YOUR_BACK_ON_ME = new NpcStringId(1000383);
		S1_ILL_DEAL_WITH_YOU_MYSELF = new NpcStringId(1000384);
		S1_THIS_IS_PERSONAL = new NpcStringId(1000385);
		S1_LEAVE_US_ALONE_THIS_IS_BETWEEN_US = new NpcStringId(1000386);
		S1_KILLING_YOU_WILL_BE_A_PLEASURE = new NpcStringId(1000387);
		S1_HEY_WERE_HAVING_A_DUEL_HERE = new NpcStringId(1000388);
		THE_DUEL_IS_OVER_ATTACK = new NpcStringId(1000389);
		FOUL_KILL_THE_COWARD = new NpcStringId(1000390);
		HOW_DARE_YOU_INTERRUPT_A_SACRED_DUEL_YOU_MUST_BE_TAUGHT_A_LESSON = new NpcStringId(1000391);
		DIE_YOU_COWARD = new NpcStringId(1000392);
		KILL_THE_COWARD = new NpcStringId(1000394);
		I_NEVER_THOUGHT_ID_USE_THIS_AGAINST_A_NOVICE = new NpcStringId(1000395);
		YOU_WONT_TAKE_ME_DOWN_EASILY = new NpcStringId(1000396);
		THE_BATTLE_HAS_JUST_BEGUN = new NpcStringId(1000397);
		KILL_S1_FIRST = new NpcStringId(1000398);
		ATTACK_S1 = new NpcStringId(1000399);
		ATTACK_ATTACK = new NpcStringId(1000400);
		OVER_HERE = new NpcStringId(1000401);
		ROGER = new NpcStringId(1000402);
		SHOW_YOURSELVES = new NpcStringId(1000403);
		FORCES_OF_DARKNESS_FOLLOW_ME = new NpcStringId(1000404);
		DESTROY_THE_ENEMY_MY_BROTHERS = new NpcStringId(1000405);
		NOW_THE_FUN_STARTS = new NpcStringId(1000406);
		ENOUGH_FOOLING_AROUND_GET_READY_TO_DIE = new NpcStringId(1000407);
		YOU_IDIOT_IVE_JUST_BEEN_TOYING_WITH_YOU = new NpcStringId(1000408);
		WITNESS_MY_TRUE_POWER = new NpcStringId(1000409);
		NOW_THE_BATTLE_BEGINS = new NpcStringId(1000410);
		I_MUST_ADMIT_NO_ONE_MAKES_MY_BLOOD_BOIL_QUITE_LIKE_YOU_DO = new NpcStringId(1000411);
		YOU_HAVE_MORE_SKILL_THAN_I_THOUGHT = new NpcStringId(1000412);
		ILL_DOUBLE_MY_STRENGTH = new NpcStringId(1000413);
		PREPARE_TO_DIE = new NpcStringId(1000414);
		ALL_IS_LOST_PREPARE_TO_MEET_THE_GODDESS_OF_DEATH = new NpcStringId(1000415);
		ALL_IS_LOST_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED = new NpcStringId(1000416);
		THE_END_OF_TIME_HAS_COME_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED = new NpcStringId(1000417);
		S1_YOU_BRING_AN_ILL_WIND = new NpcStringId(1000418);
		S1_YOU_MIGHT_AS_WELL_GIVE_UP = new NpcStringId(1000419);
		YOU_DONT_HAVE_ANY_HOPE_YOUR_END_HAS_COME = new NpcStringId(1000420);
		THE_PROPHECY_OF_DARKNESS_HAS_BEEN_FULFILLED = new NpcStringId(1000421);
		AS_FORETOLD_IN_THE_PROPHECY_OF_DARKNESS_THE_ERA_OF_CHAOS_HAS_BEGUN = new NpcStringId(1000422);
		THE_PROPHECY_OF_DARKNESS_HAS_COME_TO_PASS = new NpcStringId(1000423);
		S1_I_GIVE_YOU_THE_BLESSING_OF_PROPHECY = new NpcStringId(1000424);
		S1_I_BESTOW_UPON_YOU_THE_AUTHORITY_OF_THE_ABYSS = new NpcStringId(1000425);
		HERALD_OF_THE_NEW_ERA_OPEN_YOUR_EYES = new NpcStringId(1000426);
		REMEMBER_KILL_THE_WEAKLINGS_FIRST = new NpcStringId(1000427);
		PREPARE_TO_DIE_MAGGOT = new NpcStringId(1000428);
		THAT_WILL_DO_PREPARE_TO_BE_RELEASED = new NpcStringId(1000429);
		DRAW = new NpcStringId(1000430);
		RULERS_OF_THE_SEAL_I_BRING_YOU_WONDROUS_GIFTS = new NpcStringId(1000431);
		RULERS_OF_THE_SEAL_I_HAVE_SOME_EXCELLENT_WEAPONS_TO_SHOW_YOU = new NpcStringId(1000432);
		IVE_BEEN_SO_BUSY_LATELY_IN_ADDITION_TO_PLANNING_MY_TRIP = new NpcStringId(1000433);
		YOUR_TREATMENT_OF_WEAKLINGS_IS_UNFORGIVABLE = new NpcStringId(1000434);
		IM_HERE_TO_HELP_YOU_HI_YAH = new NpcStringId(1000435);
		JUSTICE_WILL_BE_SERVED = new NpcStringId(1000436);
		ON_TO_IMMORTAL_GLORY = new NpcStringId(1000437);
		JUSTICE_ALWAYS_AVENGES_THE_POWERLESS = new NpcStringId(1000438);
		ARE_YOU_HURT_HANG_IN_THERE_WEVE_ALMOST_GOT_THEM = new NpcStringId(1000439);
		WHY_SHOULD_I_TELL_YOU_MY_NAME_YOU_CREEP = new NpcStringId(1000440);
		N1_MINUTE = new NpcStringId(1000441);
		THE_DEFENDERS_OF_S1_CASTLE_WILL_BE_TELEPORTED_TO_THE_INNER_CASTLE = new NpcStringId(1000443);
		SUNDAY = new NpcStringId(1000444);
		MONDAY = new NpcStringId(1000445);
		TUESDAY = new NpcStringId(1000446);
		WEDNESDAY = new NpcStringId(1000447);
		THURSDAY = new NpcStringId(1000448);
		FRIDAY = new NpcStringId(1000449);
		SATURDAY = new NpcStringId(1000450);
		HOUR = new NpcStringId(1000451);
		ITS_A_GOOD_DAY_TO_DIE_WELCOME_TO_HELL_MAGGOT = new NpcStringId(1000452);
		THE_FESTIVAL_OF_DARKNESS_WILL_END_IN_TWO_MINUTES = new NpcStringId(1000453);
		NOBLESSE_GATE_PASS = new NpcStringId(1000454);
		MINUTES_HAVE_PASSED = new NpcStringId(1000456);
		GAME_OVER_THE_TELEPORT_WILL_APPEAR_MOMENTARILY = new NpcStringId(1000457);
		YOU_WHO_ARE_LIKE_THE_SLUGS_CRAWLING_ON_THE_GROUND_THE_GENEROSITY_AND_GREATNESS_OF_ME_DAIMON_THE_WHITE_EYED_IS_ENDLESS_HA_HA_HA = new NpcStringId(1000458);
		IF_YOU_WANT_TO_BE_THE_OPPONENT_OF_ME_DAIMON_THE_WHITE_EYED_YOU_SHOULD_AT_LEAST_HAVE_THE_BASIC_SKILLS = new NpcStringId(1000459);
		YOU_STUPID_CREATURES_THAT_ARE_BOUND_TO_THE_EARTH_YOU_ARE_SUFFERING_TOO_MUCH_WHILE_DRAGGING_YOUR_FAT_HEAVY_BODIES_I_DAIMON_WILL_LIGHTEN_YOUR_BURDEN = new NpcStringId(1000460);
		A_WEAK_AND_STUPID_TRIBE_LIKE_YOURS_DOESNT_DESERVE_TO_BE_MY_ENEMY_BWA_HA_HA_HA = new NpcStringId(1000461);
		YOU_DARE_TO_INVADE_THE_TERRITORY_OF_DAIMON_THE_WHITE_EYED_NOW_YOU_WILL_PAY_THE_PRICE_FOR_YOUR_ACTION = new NpcStringId(1000462);
		THIS_IS_THE_GRACE_OF_DAIMON_THE_WHITE_EYED_THE_GREAT_MONSTER_EYE_LORD_HA_HA_HA = new NpcStringId(1000463);
		S1_YOU_HAVE_BECOME_A_HERO_OF_DUELISTS_CONGRATULATIONS = new NpcStringId(1000464);
		S1_YOU_HAVE_BECOME_A_HERO_OF_DREADNOUGHTS_CONGRATULATIONS = new NpcStringId(1000465);
		S1_YOU_HAVE_BECOME_A_HERO_OF_PHOENIX_KNIGHTS_CONGRATULATIONS = new NpcStringId(1000466);
		S1_YOU_HAVE_BECOME_A_HERO_OF_HELL_KNIGHTS_CONGRATULATIONS = new NpcStringId(1000467);
		S1_YOU_HAVE_BECOME_A_SAGITTARIUS_HERO_CONGRATULATIONS = new NpcStringId(1000468);
		S1_YOU_HAVE_BECOME_A_HERO_OF_ADVENTURERS_CONGRATULATIONS = new NpcStringId(1000469);
		S1_YOU_HAVE_BECOME_A_HERO_OF_ARCHMAGES_CONGRATULATIONS = new NpcStringId(1000470);
		S1_YOU_HAVE_BECOME_A_HERO_OF_SOULTAKERS_CONGRATULATIONS = new NpcStringId(1000471);
		S1_YOU_HAVE_BECOME_A_HERO_OF_ARCANA_LORDS_CONGRATULATIONS = new NpcStringId(1000472);
		S1_YOU_HAVE_BECOME_A_HERO_OF_CARDINALS_CONGRATULATIONS = new NpcStringId(1000473);
		S1_YOU_HAVE_BECOME_A_HERO_OF_HIEROPHANTS_CONGRATULATIONS = new NpcStringId(1000474);
		S1_YOU_HAVE_BECOME_A_HERO_OF_EVAS_TEMPLARS_CONGRATULATIONS = new NpcStringId(1000475);
		S1_YOU_HAVE_BECOME_A_HERO_OF_SWORD_MUSES_CONGRATULATIONS = new NpcStringId(1000476);
		S1_YOU_HAVE_BECOME_A_HERO_OF_WIND_RIDERS_CONGRATULATIONS = new NpcStringId(1000477);
		S1_YOU_HAVE_BECOME_A_HERO_OF_MOONLIGHT_SENTINELS_CONGRATULATIONS = new NpcStringId(1000478);
		S1_YOU_HAVE_BECOME_A_HERO_OF_MYSTIC_MUSES_CONGRATULATIONS = new NpcStringId(1000479);
		S1_YOU_HAVE_BECOME_A_HERO_OF_ELEMENTAL_MASTERS_CONGRATULATIONS = new NpcStringId(1000480);
		S1_YOU_HAVE_BECOME_A_HERO_OF_EVAS_SAINTS_CONGRATULATIONS = new NpcStringId(1000481);
		S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_TEMPLARS_CONGRATULATIONS = new NpcStringId(1000482);
		S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_DANCERS_CONGRATULATIONS = new NpcStringId(1000483);
		S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_HUNTERS_CONGRATULATIONS = new NpcStringId(1000484);
		S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_SENTINELS_CONGRATULATIONS = new NpcStringId(1000485);
		S1_YOU_HAVE_BECOME_A_HERO_OF_STORM_SCREAMERS_CONGRATULATIONS = new NpcStringId(1000486);
		S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_MASTERS_CONGRATULATIONS = new NpcStringId(1000487);
		S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_SAINTS_CONGRATULATIONS = new NpcStringId(1000488);
		S1_YOU_HAVE_BECOME_A_HERO_OF_TITANS_CONGRATULATIONS = new NpcStringId(1000489);
		S1_YOU_HAVE_BECOME_A_HERO_OF_GRAND_KHAVATARIS_CONGRATULATIONS = new NpcStringId(1000490);
		S1_YOU_HAVE_BECOME_A_HERO_OF_DOMINATORS_CONGRATULATIONS = new NpcStringId(1000491);
		S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMCRYERS_CONGRATULATIONS = new NpcStringId(1000492);
		S1_YOU_HAVE_BECOME_A_HERO_OF_FORTUNE_SEEKERS_CONGRATULATIONS = new NpcStringId(1000493);
		S1_YOU_HAVE_BECOME_A_HERO_OF_MAESTROS_CONGRATULATIONS = new NpcStringId(1000494);
		UNREGISTERED = new NpcStringId(1000495);
		S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMBRINGERS_CONGRATULATIONS = new NpcStringId(1000496);
		S1_YOU_HAVE_BECOME_A_HERO_OF_SOUL_HOUNDS_CONGRATULATIONS = new NpcStringId(1000497);
		S1_YOU_HAVE_BECOME_A_HERO_OF_TRICKSTERS_CONGRATULATIONS = new NpcStringId(1000499);
		YOU_MAY_NOW_ENTER_THE_SEPULCHER = new NpcStringId(1000500);
		IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER = new NpcStringId(1000501);
		THE_MONSTERS_HAVE_SPAWNED = new NpcStringId(1000502);
		THANK_YOU_FOR_SAVING_ME = new NpcStringId(1000503);
		FEWER_THAN_S2 = new NpcStringId(1000504);
		MORE_THAN_S2 = new NpcStringId(1000505);
		POINT = new NpcStringId(1000506);
		COMPETITION = new NpcStringId(1000507);
		SEAL_VALIDATION = new NpcStringId(1000508);
		PREPARATION = new NpcStringId(1000509);
		NO_OWNER = new NpcStringId(1000512);
		THIS_PLACE_IS_DANGEROUS_S1_PLEASE_TURN_BACK = new NpcStringId(1000513);
		WHO_DISTURBS_MY_SACRED_SLEEP = new NpcStringId(1000514);
		BEGONE_THIEF_LET_OUR_BONES_REST_IN_PEACE = new NpcStringId(1000515);
		_LEAVE_US_BE_HESTUI_SCUM = new NpcStringId(1000516);
		THIEVING_KAKAI_MAY_BLOODBUGS_GNAW_YOU_IN_YOUR_SLEEP = new NpcStringId(1000517);
		NEWBIE_TRAVEL_TOKEN = new NpcStringId(1000518);
		ARROGANT_FOOL_YOU_DARE_TO_CHALLENGE_ME_THE_RULER_OF_FLAMES_HERE_IS_YOUR_REWARD = new NpcStringId(1000519);
		S1_YOU_CANNOT_HOPE_TO_DEFEAT_ME_WITH_YOUR_MEAGER_STRENGTH = new NpcStringId(1000520);
		NOT_EVEN_THE_GODS_THEMSELVES_COULD_TOUCH_ME_BUT_YOU_S1_YOU_DARE_CHALLENGE_ME_IGNORANT_MORTAL = new NpcStringId(1000521);
		REQUIEM_OF_HATRED = new NpcStringId(1000522);
		FUGUE_OF_JUBILATION = new NpcStringId(1000523);
		FRENETIC_TOCCATA = new NpcStringId(1000524);
		HYPNOTIC_MAZURKA = new NpcStringId(1000525);
		MOURNFUL_CHORALE_PRELUDE = new NpcStringId(1000526);
		RONDO_OF_SOLITUDE = new NpcStringId(1000527);
		OLYMPIAD_TOKEN = new NpcStringId(1000528);
		THE_KINGDOM_OF_ADEN = new NpcStringId(1001000);
		THE_KINGDOM_OF_ELMORE = new NpcStringId(1001100);
		TALKING_ISLAND_VILLAGE = new NpcStringId(1010001);
		THE_ELVEN_VILLAGE = new NpcStringId(1010002);
		THE_DARK_ELF_VILLAGE = new NpcStringId(1010003);
		THE_VILLAGE_OF_GLUDIN = new NpcStringId(1010004);
		THE_TOWN_OF_GLUDIO = new NpcStringId(1010005);
		THE_TOWN_OF_DION = new NpcStringId(1010006);
		THE_TOWN_OF_GIRAN = new NpcStringId(1010007);
		ORC_VILLAGE = new NpcStringId(1010008);
		DWARVEN_VILLAGE = new NpcStringId(1010009);
		THE_SOUTHERN_PART_OF_THE_DARK_FOREST = new NpcStringId(1010010);
		THE_NORTHEAST_COAST = new NpcStringId(1010011);
		THE_SOUTHERN_ENTRANCE_TO_THE_WASTELANDSS = new NpcStringId(1010012);
		TOWN_OF_OREN = new NpcStringId(1010013);
		IVORY_TOWER = new NpcStringId(1010014);
		N1ST_FLOOR_LOBBY = new NpcStringId(1010015);
		UNDERGROUND_SHOPPING_AREA = new NpcStringId(1010016);
		N2ND_FLOOR_HUMAN_WIZARD_GUILD = new NpcStringId(1010017);
		N3RD_FLOOR_ELVEN_WIZARD_GUILD = new NpcStringId(1010018);
		N4TH_FLOOR_DARK_WIZARD_GUILD = new NpcStringId(1010019);
		HUNTERS_VILLAGE = new NpcStringId(1010020);
		GIRAN_HARBOR = new NpcStringId(1010021);
		HARDINS_PRIVATE_ACADEMY = new NpcStringId(1010022);
		TOWN_OF_ADEN = new NpcStringId(1010023);
		VILLAGE_SQUARE = new NpcStringId(1010024);
		NORTH_GATE_ENTRANCE = new NpcStringId(1010025);
		EAST_GATE_ENTRANCE = new NpcStringId(1010026);
		WEST_GATE_ENTRANCE = new NpcStringId(1010027);
		SOUTH_GATE_ENTRANCE = new NpcStringId(1010028);
		ENTRANCE_TO_TUREK_ORC_CAMP = new NpcStringId(1010029);
		ENTRANCE_TO_FORGOTTEN_TEMPLE = new NpcStringId(1010030);
		ENTRANCE_TO_THE_WASTELANDS = new NpcStringId(1010031);
		ENTRANCE_TO_ABANDONED_CAMP = new NpcStringId(1010032);
		ENTRANCE_TO_CRUMA_MARSHLANDS = new NpcStringId(1010033);
		ENTRANCE_TO_EXECUTION_GROUNDS = new NpcStringId(1010034);
		ENTRANCE_TO_THE_FORTRESS_OF_RESISTANCE = new NpcStringId(1010035);
		ENTRANCE_TO_FLORAN_VILLAGE = new NpcStringId(1010036);
		NEUTRAL_ZONE = new NpcStringId(1010037);
		WESTERN_ROAD_OF_GIRAN = new NpcStringId(1010038);
		EASTERN_ROAD_OF_GLUDIN_VILLAGE = new NpcStringId(1010039);
		ENTRANCE_TO_CRUMA_TOWER = new NpcStringId(1010041);
		DEATH_PASS = new NpcStringId(1010042);
		NORTHERN_PART_OF_THE_MARSHLANDS = new NpcStringId(1010043);
		NORTHEAST_OF_THE_NEUTRAL_ZONE = new NpcStringId(1010044);
		IMMORTAL_PLATEAU_CENTRAL_REGION = new NpcStringId(1010045);
		IMMORTAL_PLATEAU_SOUTHERN_REGION = new NpcStringId(1010046);
		IMMORTAL_PLATEAU_SOUTHEAST_REGION = new NpcStringId(1010047);
		FROZEN_WATERFALL = new NpcStringId(1010048);
		HEINE = new NpcStringId(1010049);
		TOWER_OF_INSOLENCE_1ST_FLOOR = new NpcStringId(1010050);
		TOWER_OF_INSOLENCE_5TH_FLOOR = new NpcStringId(1010051);
		TOWER_OF_INSOLENCE_10TH_FLOOR = new NpcStringId(1010052);
		COLISEUM = new NpcStringId(1010053);
		MONSTER_DERBY = new NpcStringId(1010054);
		NEAR_THE_FRONTIER_POST = new NpcStringId(1010055);
		ENTRANCE_TO_THE_SEA_OF_SPORES = new NpcStringId(1010056);
		AN_OLD_BATTLEFIELD = new NpcStringId(1010057);
		ENTRANCE_TO_ENCHANTED_VALLEY = new NpcStringId(1010058);
		ENTRANCE_TO_THE_TOWER_OF_INSOLENCE = new NpcStringId(1010059);
		BLAZING_SWAMP = new NpcStringId(1010060);
		ENTRANCE_TO_THE_CEMETERY = new NpcStringId(1010061);
		ENTRANCE_TO_THE_GIANTS_CAVE = new NpcStringId(1010062);
		ENTRANCE_TO_THE_FOREST_OF_MIRRORS = new NpcStringId(1010063);
		THE_FORBIDDEN_GATEWAY = new NpcStringId(1010064);
		ENTRANCE_TO_THE_TANOR_SILENOS_BARRACKS = new NpcStringId(1010066);
		DRAGON_VALLEY = new NpcStringId(1010067);
		ORACLE_OF_DAWN = new NpcStringId(1010068);
		ORACLE_OF_DUSK = new NpcStringId(1010069);
		NECROPOLIS_OF_SACRIFICE = new NpcStringId(1010070);
		THE_PILGRIMS_NECROPOLIS = new NpcStringId(1010071);
		NECROPOLIS_OF_WORSHIP = new NpcStringId(1010072);
		THE_PATRIOTS_NECROPOLIS = new NpcStringId(1010073);
		NECROPOLIS_OF_DEVOTION = new NpcStringId(1010074);
		NECROPOLIS_OF_MARTYRDOM = new NpcStringId(1010075);
		THE_DISCIPLES_NECROPOLIS = new NpcStringId(1010076);
		THE_SAINTS_NECROPOLIS = new NpcStringId(1010077);
		THE_CATACOMB_OF_THE_HERETIC = new NpcStringId(1010078);
		CATACOMB_OF_THE_BRANDED = new NpcStringId(1010079);
		CATACOMB_OF_THE_APOSTATE = new NpcStringId(1010080);
		CATACOMB_OF_THE_WITCH = new NpcStringId(1010081);
		CATACOMB_OF_DARK_OMENS = new NpcStringId(1010082);
		CATACOMB_OF_THE_FORBIDDEN_PATH = new NpcStringId(1010083);
		ENTRANCE_TO_THE_RUINS_OF_AGONY = new NpcStringId(1010084);
		ENTRANCE_TO_THE_RUINS_OF_DESPAIR = new NpcStringId(1010085);
		ENTRANCE_TO_THE_ANT_NEST = new NpcStringId(1010086);
		SOUTHERN_DION = new NpcStringId(1010087);
		ENTRANCE_TO_DRAGON_VALLEY = new NpcStringId(1010088);
		FIELD_OF_SILENCE = new NpcStringId(1010089);
		FIELD_OF_WHISPERS = new NpcStringId(1010090);
		ENTRANCE_TO_ALLIGATOR_ISLAND = new NpcStringId(1010091);
		SOUTHERN_PLAIN_OF_OREN = new NpcStringId(1010092);
		ENTRANCE_TO_THE_BANDIT_STRONGHOLD = new NpcStringId(1010093);
		WINDY_HILL = new NpcStringId(1010094);
		ORC_BARRACKS = new NpcStringId(1010095);
		FELLMERE_HARVESTING_GROUNDS = new NpcStringId(1010096);
		RUINS_OF_AGONY = new NpcStringId(1010097);
		ABANDONED_CAMP = new NpcStringId(1010098);
		RED_ROCK_RIDGE = new NpcStringId(1010099);
		LANGK_LIZARDMAN_DWELLINGS = new NpcStringId(1010100);
		RUINS_OF_DESPAIR = new NpcStringId(1010101);
		WINDAWOOD_MANOR = new NpcStringId(1010102);
		NORTHERN_PATHWAY_TO_THE_WASTELANDS = new NpcStringId(1010103);
		WESTERN_PATHWAY_TO_THE_WASTELANDS = new NpcStringId(1010104);
		SOUTHERN_PATHWAY_TO_THE_WASTELANDS = new NpcStringId(1010105);
		FORGOTTEN_TEMPLE = new NpcStringId(1010106);
		SOUTH_ENTRANCE_OF_ANT_NEST = new NpcStringId(1010107);
		EAST_ENTRANCE_OF_ANT_NEST = new NpcStringId(1010108);
		WEST_ENTRANCE_OF_ANT_NEST = new NpcStringId(1010109);
		CRUMA_MARSHLAND = new NpcStringId(1010110);
		PLAINS_OF_DION = new NpcStringId(1010111);
		BEE_HIVE = new NpcStringId(1010112);
		FORTRESS_OF_RESISTANCE = new NpcStringId(1010113);
		EXECUTION_GROUNDS = new NpcStringId(1010114);
		TANOR_CANYON = new NpcStringId(1010115);
		CRUMA_TOWER = new NpcStringId(1010116);
		THREE_WAY_CROSSROADS_AT_DRAGON_VALLEY = new NpcStringId(1010117);
		BREKAS_STRONGHOLD = new NpcStringId(1010118);
		GORGON_FLOWER_GARDEN = new NpcStringId(1010119);
		ANTHARASS_LAIR = new NpcStringId(1010120);
		SEA_OF_SPORES = new NpcStringId(1010121);
		OUTLAW_FOREST = new NpcStringId(1010122);
		FOREST_OF_EVIL_AND_THE_IVORY_TOWER = new NpcStringId(1010123);
		TIMAK_OUTPOST = new NpcStringId(1010124);
		GREAT_PLAINS_OF_OREN = new NpcStringId(1010125);
		ALCHEMISTS_HUT = new NpcStringId(1010126);
		ANCIENT_BATTLEGROUND = new NpcStringId(1010127);
		NORTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY = new NpcStringId(1010128);
		SOUTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY = new NpcStringId(1010129);
		HUNTERS_VALLEY = new NpcStringId(1010130);
		WESTERN_ENTRANCE_OF_BLAZING_SWAMP = new NpcStringId(1010131);
		EASTERN_ENTRANCE_OF_BLAZING_SWAMP = new NpcStringId(1010132);
		PLAINS_OF_GLORY = new NpcStringId(1010133);
		WAR_TORN_PLAINS = new NpcStringId(1010134);
		NORTHWESTERN_PASSAGE_TO_THE_FOREST_OF_MIRRORS = new NpcStringId(1010135);
		THE_FRONT_OF_ANGHEL_WATERFALL = new NpcStringId(1010136);
		SOUTH_ENTRANCE_OF_DEVASTATED_CASTLE = new NpcStringId(1010137);
		NORTH_ENTRANCE_OF_DEVASTATED_CASTLE = new NpcStringId(1010138);
		NORTH_ENTRANCE_OF_THE_CEMETERY = new NpcStringId(1010139);
		SOUTH_ENTRANCE_OF_THE_CEMETERY = new NpcStringId(1010140);
		WEST_ENTRANCE_OF_THE_CEMETERY = new NpcStringId(1010141);
		ENTRANCE_OF_THE_FORBIDDEN_GATEWAY = new NpcStringId(1010142);
		FORSAKEN_PLAINS = new NpcStringId(1010143);
		TOWER_OF_INSOLENCE = new NpcStringId(1010144);
		THE_GIANTS_CAVE = new NpcStringId(1010145);
		NORTHERN_PART_OF_THE_FIELD_OF_SILENCE = new NpcStringId(1010146);
		WESTERN_PART_OF_THE_FIELD_OF_SILENCE = new NpcStringId(1010147);
		EASTERN_PART_OF_THE_FIELD_OF_SILENCE = new NpcStringId(1010148);
		WESTERN_PART_OF_THE_FIELD_OF_WHISPERS = new NpcStringId(1010149);
		ALLIGATOR_ISLAND = new NpcStringId(1010150);
		ALLIGATOR_BEACH = new NpcStringId(1010151);
		DEVILS_ISLE = new NpcStringId(1010152);
		GARDEN_OF_EVA = new NpcStringId(1010153);
		TALKING_ISLAND = new NpcStringId(1010154);
		ELVEN_VILLAGE = new NpcStringId(1010155);
		DARK_ELF_VILLAGE = new NpcStringId(1010156);
		SCENIC_DECK_OF_IRIS_LAKE = new NpcStringId(1010159);
		ALTAR_OF_RITES = new NpcStringId(1010160);
		DARK_FOREST_WATERFALL = new NpcStringId(1010161);
		THREE_WAY_CROSSROADS_OF_THE_NEUTRAL_ZONE = new NpcStringId(1010162);
		DARK_FOREST = new NpcStringId(1010163);
		SWAMPLAND = new NpcStringId(1010164);
		BLACK_ROCK_HILL = new NpcStringId(1010165);
		SPIDER_NEST = new NpcStringId(1010166);
		ELVEN_FOREST = new NpcStringId(1010167);
		OBELISK_OF_VICTORY = new NpcStringId(1010168);
		NORTHERN_TERRITORY_OF_TALKING_ISLAND = new NpcStringId(1010169);
		SOUTHERN_TERRITORY_OF_TALKING_ISLAND = new NpcStringId(1010170);
		EVIL_HUNTING_GROUNDS = new NpcStringId(1010171);
		MAILLE_LIZARDMEN_BARRACKS = new NpcStringId(1010172);
		RUINS_OF_AGONY_BEND = new NpcStringId(1010173);
		THE_ENTRANCE_TO_THE_RUINS_OF_DESPAIR = new NpcStringId(1010174);
		WINDMILL_HILL = new NpcStringId(1010175);
		FLORAN_AGRICULTURAL_AREA = new NpcStringId(1010177);
		WESTERN_TANOR_CANYON = new NpcStringId(1010178);
		PLAINS_OF_THE_LIZARDMEN = new NpcStringId(1010179);
		FOREST_OF_EVIL = new NpcStringId(1010180);
		FIELDS_OF_MASSACRE = new NpcStringId(1010181);
		SILENT_VALLEY = new NpcStringId(1010182);
		NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION = new NpcStringId(1010183);
		SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION = new NpcStringId(1010184);
		NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION = new NpcStringId(1010185);
		SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION = new NpcStringId(1010186);
		WESTERN_MINING_ZONE = new NpcStringId(1010187);
		ENTRANCE_TO_THE_ABANDONED_COAL_MINES = new NpcStringId(1010190);
		ENTRANCE_TO_THE_MITHRIL_MINES = new NpcStringId(1010191);
		WEST_AREA_OF_THE_DEVASTATED_CASTLE = new NpcStringId(1010192);
		TOWER_OF_INSOLENCE_3RD_FLOOR = new NpcStringId(1010193);
		TOWER_OF_INSOLENCE_7TH_FLOOR = new NpcStringId(1010195);
		TOWER_OF_INSOLENCE_13TH_FLOOR = new NpcStringId(1010197);
		TOWN_OF_GODDARD = new NpcStringId(1010199);
		RUNE_TOWNSHIP = new NpcStringId(1010200);
		A_DELIVERY_FOR_MR_LECTOR_VERY_GOOD = new NpcStringId(1010201);
		I_NEED_A_BREAK = new NpcStringId(1010202);
		HELLO_MR_LECTOR_LONG_TIME_NO_SEE_MR_JACKSON = new NpcStringId(1010203);
		LULU = new NpcStringId(1010204);
		WHERE_HAS_HE_GONE = new NpcStringId(1010205);
		HAVE_YOU_SEEN_WINDAWOOD = new NpcStringId(1010206);
		WHERE_DID_HE_GO = new NpcStringId(1010207);
		THE_MOTHER_TREE_IS_SLOWLY_DYING = new NpcStringId(1010208);
		HOW_CAN_WE_SAVE_THE_MOTHER_TREE = new NpcStringId(1010209);
		THE_MOTHER_TREE_IS_ALWAYS_SO_GORGEOUS = new NpcStringId(1010210);
		LADY_MIRABEL_MAY_THE_PEACE_OF_THE_LAKE_BE_WITH_YOU = new NpcStringId(1010211);
		YOURE_A_HARD_WORKER_RAYLA = new NpcStringId(1010212);
		YOURE_A_HARD_WORKER = new NpcStringId(1010213);
		THE_MASS_OF_DARKNESS_WILL_START_IN_A_COUPLE_OF_DAYS_PAY_MORE_ATTENTION_TO_THE_GUARD = new NpcStringId(1010214);
		HAVE_YOU_SEEN_TOROCCO_TODAY = new NpcStringId(1010215);
		HAVE_YOU_SEEN_TOROCCO = new NpcStringId(1010216);
		WHERE_IS_THAT_FOOL_HIDING = new NpcStringId(1010217);
		CARE_TO_GO_A_ROUND = new NpcStringId(1010218);
		HAVE_A_NICE_DAY_MR_GARITA_AND_MION = new NpcStringId(1010219);
		MR_LID_MURDOC_AND_AIRY_HOW_ARE_YOU_DOING = new NpcStringId(1010220);
		A_BLACK_MOON_NOW_DO_YOU_UNDERSTAND_THAT_HE_HAS_OPENED_HIS_EYES = new NpcStringId(1010221);
		CLOUDS_OF_BLOOD_ARE_GATHERING_SOON_IT_WILL_START_TO_RAIN_THE_RAIN_OF_CRIMSON_BLOOD = new NpcStringId(1010222);
		WHILE_THE_FOOLISH_LIGHT_WAS_ASLEEP_THE_DARKNESS_WILL_AWAKEN_FIRST_UH_HUH_HUH = new NpcStringId(1010223);
		IT_IS_THE_DEEPEST_DARKNESS_WITH_ITS_ARRIVAL_THE_WORLD_WILL_SOON_DIE = new NpcStringId(1010224);
		DEATH_IS_JUST_A_NEW_BEGINNING_HUHU_FEAR_NOT = new NpcStringId(1010225);
		AHH_BEAUTIFUL_GODDESS_OF_DEATH_COVER_OVER_THE_FILTH_OF_THIS_WORLD_WITH_YOUR_DARKNESS = new NpcStringId(1010226);
		THE_GODDESSS_RESURRECTION_HAS_ALREADY_BEGUN_HUHU_INSIGNIFICANT_CREATURES_LIKE_YOU_CAN_DO_NOTHING = new NpcStringId(1010227);
		CROAK_CROAK_FOOD_LIKE_S1_IN_THIS_PLACE = new NpcStringId(1010400);
		S1_HOW_LUCKY_I_AM = new NpcStringId(1010401);
		PRAY_THAT_YOU_CAUGHT_A_WRONG_FISH_S1 = new NpcStringId(1010402);
		DO_YOU_KNOW_WHAT_A_FROG_TASTES_LIKE = new NpcStringId(1010403);
		I_WILL_SHOW_YOU_THE_POWER_OF_A_FROG = new NpcStringId(1010404);
		I_WILL_SWALLOW_AT_A_MOUTHFUL = new NpcStringId(1010405);
		UGH_NO_CHANCE_HOW_COULD_THIS_ELDER_PASS_AWAY_LIKE_THIS = new NpcStringId(1010406);
		CROAK_CROAK_A_FROG_IS_DYING = new NpcStringId(1010407);
		A_FROG_TASTES_BAD_YUCK = new NpcStringId(1010408);
		KAAK_S1_WHAT_ARE_YOU_DOING_NOW = new NpcStringId(1010409);
		HUH_S1_YOU_PIERCED_INTO_THE_BODY_OF_THE_SPIRIT_WITH_A_NEEDLE_ARE_YOU_READY = new NpcStringId(1010410);
		OOH_S1_THATS_YOU_BUT_NO_LADY_IS_PLEASED_WITH_THIS_SAVAGE_INVITATION = new NpcStringId(1010411);
		YOU_MADE_ME_ANGRY = new NpcStringId(1010412);
		IT_IS_BUT_A_SCRATCH_IS_THAT_ALL_YOU_CAN_DO = new NpcStringId(1010413);
		FEEL_MY_PAIN = new NpcStringId(1010414);
		ILL_GET_YOU_FOR_THIS = new NpcStringId(1010415);
		I_WILL_TELL_FISH_NOT_TO_TAKE_YOUR_BAIT = new NpcStringId(1010416);
		YOU_BOTHERED_SUCH_A_WEAK_SPIRITHUH_HUH = new NpcStringId(1010417);
		KE_KE_KES1IM_EATINGKE = new NpcStringId(1010418);
		KUHOOHS1ENMITYFISH = new NpcStringId(1010419);
		S1_HUH_HUHHUH = new NpcStringId(1010420);
		KE_KE_KE_RAKUL_SPIN_EH_EH_EH = new NpcStringId(1010421);
		AH_FAFURION_AH_AH = new NpcStringId(1010422);
		RAKUL_RAKUL_RA_KUL = new NpcStringId(1010423);
		EHENMITYFISH = new NpcStringId(1010424);
		I_WONT_BE_EATEN_UPKAH_AH_AH = new NpcStringId(1010425);
		COUGH_RAKUL_COUGH_COUGH_KEH = new NpcStringId(1010426);
		GLORY_TO_FAFURION_DEATH_TO_S1 = new NpcStringId(1010427);
		S1_YOU_ARE_THE_ONE_WHO_BOTHERED_MY_POOR_FISH = new NpcStringId(1010428);
		FAFURION_A_CURSE_UPON_S1 = new NpcStringId(1010429);
		GIANT_SPECIAL_ATTACK = new NpcStringId(1010430);
		KNOW_THE_ENMITY_OF_FISH = new NpcStringId(1010431);
		I_WILL_SHOW_YOU_THE_POWER_OF_A_SPEAR = new NpcStringId(1010432);
		GLORY_TO_FAFURION = new NpcStringId(1010433);
		YIPES = new NpcStringId(1010434);
		AN_OLD_SOLDIER_DOES_NOT_DIE_BUT_JUST_DISAPPEAR = new NpcStringId(1010435);
		S1_TAKE_MY_CHALLENGE_THE_KNIGHT_OF_WATER = new NpcStringId(1010436);
		DISCOVER_S1_IN_THE_TREASURE_CHEST_OF_FISH = new NpcStringId(1010437);
		S1_I_TOOK_YOUR_BAIT = new NpcStringId(1010438);
		I_WILL_SHOW_YOU_SPEARMANSHIP_USED_IN_DRAGON_KINGS_PALACE = new NpcStringId(1010439);
		THIS_IS_THE_LAST_GIFT_I_GIVE_YOU = new NpcStringId(1010440);
		YOUR_BAIT_WAS_TOO_DELICIOUS_NOW_I_WILL_KILL_YOU = new NpcStringId(1010441);
		WHAT_A_REGRET_THE_ENMITY_OF_MY_BRETHREN = new NpcStringId(1010442);
		ILL_PAY_YOU_BACK_SOMEBODY_WILL_HAVE_MY_REVENGE = new NpcStringId(1010443);
		COUGH_BUT_I_WONT_BE_EATEN_UP_BY_YOU = new NpcStringId(1010444);
		_S1_I_WILL_KILL_YOU = new NpcStringId(1010445);
		S1_HOW_COULD_YOU_CATCH_ME_FROM_THE_DEEP_SEA = new NpcStringId(1010446);
		S1_DO_YOU_THINK_I_AM_A_FISH = new NpcStringId(1010447);
		EBIBIBI = new NpcStringId(1010448);
		HE_HE_HE_DO_YOU_WANT_ME_TO_ROAST_YOU_WELL = new NpcStringId(1010449);
		YOU_DIDNT_KEEP_YOUR_EYES_ON_ME_BECAUSE_I_COME_FROM_THE_SEA = new NpcStringId(1010450);
		EEEK_I_FEEL_SICKYOW = new NpcStringId(1010451);
		I_HAVE_FAILED = new NpcStringId(1010452);
		ACTIVITY_OF_LIFE_IS_STOPPED_CHIZIFC = new NpcStringId(1010453);
		GROWLING_S1_GROWLING = new NpcStringId(1010454);
		I_CAN_SMELL_S1 = new NpcStringId(1010455);
		LOOKS_DELICIOUS_S1 = new NpcStringId(1010456);
		I_WILL_CATCH_YOU = new NpcStringId(1010457);
		UAH_AH_AH_I_COULDNT_EAT_ANYTHING_FOR_A_LONG_TIME = new NpcStringId(1010458);
		I_CAN_SWALLOW_YOU_AT_A_MOUTHFUL = new NpcStringId(1010459);
		WHAT_I_AM_DEFEATED_BY_THE_PREY = new NpcStringId(1010460);
		YOU_ARE_MY_FOOD_I_HAVE_TO_KILL_YOU = new NpcStringId(1010461);
		I_CANT_BELIEVE_I_AM_EATEN_UP_BY_MY_PREY_GAH = new NpcStringId(1010462);
		YOU_CAUGHT_ME_S1 = new NpcStringId(1010463);
		YOURE_LUCKY_TO_HAVE_EVEN_SEEN_ME_S1 = new NpcStringId(1010464);
		S1_YOU_CANT_LEAVE_HERE_ALIVE_GIVE_UP = new NpcStringId(1010465);
		I_WILL_SHOW_YOU_THE_POWER_OF_THE_DEEP_SEA = new NpcStringId(1010466);
		I_WILL_BREAK_THE_FISHING_POLE = new NpcStringId(1010467);
		YOUR_CORPSE_WILL_BE_GOOD_FOOD_FOR_ME = new NpcStringId(1010468);
		YOU_ARE_A_GOOD_FISHERMAN = new NpcStringId(1010469);
		ARENT_YOU_AFRAID_OF_FAFURION = new NpcStringId(1010470);
		YOU_ARE_EXCELLENT = new NpcStringId(1010471);
		THE_POISON_DEVICE_HAS_BEEN_ACTIVATED = new NpcStringId(1010472);
		THE_P_ATK_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_1_MINUTE = new NpcStringId(1010473);
		THE_DEFENSE_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_2_MINUTES = new NpcStringId(1010474);
		THE_HP_REGENERATION_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_3_MINUTES = new NpcStringId(1010475);
		THE_P_ATK_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED = new NpcStringId(1010476);
		THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED = new NpcStringId(1010477);
		THE_HP_REGENERATION_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED = new NpcStringId(1010478);
		THE_POISON_DEVICE_HAS_NOW_BEEN_DESTROYED = new NpcStringId(1010479);
		THE_P_ATK_REDUCTION_DEVICE_HAS_NOW_BEEN_DESTROYED = new NpcStringId(1010480);
		THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_DESTROYED = new NpcStringId(1010481);
		ENTRANCE_TO_THE_CAVE_OF_TRIALS = new NpcStringId(1010485);
		INSIDE_THE_ELVEN_RUINS = new NpcStringId(1010486);
		ENTRANCE_TO_THE_ELVEN_RUINS = new NpcStringId(1010487);
		ENTRANCE_TO_THE_SCHOOL_OF_DARK_ARTS = new NpcStringId(1010488);
		CENTER_OF_THE_SCHOOL_OF_DARK_ARTS = new NpcStringId(1010489);
		ENTRANCE_TO_THE_ELVEN_FORTRESS = new NpcStringId(1010490);
		VARKA_SILENOS_STRONGHOLD = new NpcStringId(1010491);
		KETRA_ORC_OUTPOST = new NpcStringId(1010492);
		RUNE_TOWNSHIP_GUILD = new NpcStringId(1010493);
		RUNE_TOWNSHIP_TEMPLE = new NpcStringId(1010494);
		RUNE_TOWNSHIP_STORE = new NpcStringId(1010495);
		ENTRANCE_TO_THE_FOREST_OF_THE_DEAD = new NpcStringId(1010496);
		WESTERN_ENTRANCE_TO_THE_SWAMP_OF_SCREAMS = new NpcStringId(1010497);
		ENTRANCE_TO_THE_FORGOTTEN_TEMPLE = new NpcStringId(1010498);
		CENTER_OF_THE_FORGOTTEN_TEMPLE = new NpcStringId(1010499);
		ENTRANCE_TO_THE_CRUMA_TOWER = new NpcStringId(1010500);
		CRUMA_TOWER_FIRST_FLOOR = new NpcStringId(1010501);
		CRUMA_TOWER_SECOND_FLOOR = new NpcStringId(1010502);
		CRUMA_TOWER_THIRD_FLOOR = new NpcStringId(1010503);
		ENTRANCE_TO_DEVILS_ISLE = new NpcStringId(1010504);
		GLUDIN_ARENA = new NpcStringId(1010506);
		GIRAN_ARENA = new NpcStringId(1010507);
		ENTRANCE_TO_ANTHARASS_LAIR = new NpcStringId(1010508);
		ANTHARASS_LAIR_1ST_LEVEL = new NpcStringId(1010509);
		ANTHARASS_LAIR_2ND_LEVEL = new NpcStringId(1010510);
		ANTHARASS_LAIR_MAGIC_FORCE_FIELD_BRIDGE = new NpcStringId(1010511);
		THE_HEART_OF_ANTHARASS_LAIR = new NpcStringId(1010512);
		EAST_OF_THE_FIELD_OF_SILENCE = new NpcStringId(1010513);
		WEST_OF_THE_FIELD_OF_SILENCE = new NpcStringId(1010514);
		EAST_OF_THE_FIELD_OF_WHISPERS = new NpcStringId(1010515);
		WEST_OF_THE_FIELD_OF_WHISPERS = new NpcStringId(1010516);
		ENTRANCE_TO_THE_GARDEN_OF_EVA = new NpcStringId(1010517);
		NORTHERN_PART_OF_ALLIGATOR_ISLAND = new NpcStringId(1010520);
		CENTRAL_PART_OF_ALLIGATOR_ISLAND = new NpcStringId(1010521);
		GARDEN_OF_EVA_2ND_LEVEL = new NpcStringId(1010522);
		GARDEN_OF_EVA_3RD_LEVEL = new NpcStringId(1010523);
		GARDEN_OF_EVA_4TH_LEVEL = new NpcStringId(1010524);
		GARDEN_OF_EVA_5TH_LEVEL = new NpcStringId(1010525);
		INSIDE_THE_GARDEN_OF_EVA = new NpcStringId(1010526);
		FOUR_SEPULCHERS = new NpcStringId(1010527);
		IMPERIAL_TOMB = new NpcStringId(1010528);
		SHRINE_OF_LOYALTY = new NpcStringId(1010529);
		ENTRANCE_TO_THE_FORGE_OF_THE_GODS = new NpcStringId(1010530);
		FORGE_OF_THE_GODS_TOP_LEVEL = new NpcStringId(1010531);
		FORGE_OF_THE_GODS_LOWER_LEVEL = new NpcStringId(1010532);
		ENTRANCE_TO_THE_WALL_OF_ARGOS = new NpcStringId(1010533);
		VARKA_SILENOS_VILLAGE = new NpcStringId(1010534);
		KETRA_ORC_VILLAGE = new NpcStringId(1010535);
		ENTRANCE_TO_THE_HOT_SPRINGS_REGION = new NpcStringId(1010536);
		WILD_BEAST_PASTURES = new NpcStringId(1010537);
		ENTRANCE_TO_THE_VALLEY_OF_SAINTS = new NpcStringId(1010538);
		CURSED_VILLAGE = new NpcStringId(1010539);
		SOUTHERN_ENTRANCE_OF_THE_WILD_BEAST_PASTURES = new NpcStringId(1010540);
		EASTERN_PART_OF_THE_WILD_BEAST_PASTURES = new NpcStringId(1010541);
		WESTERN_PART_OF_THE_WILD_BEAST_PASTURES = new NpcStringId(1010542);
		EASTERN_PART_OF_THE_SWAMP_OF_SCREAMS = new NpcStringId(1010543);
		WESTERN_PART_OF_THE_SWAMP_OF_SCREAMS = new NpcStringId(1010544);
		CENTER_OF_THE_SWAMP_OF_SCREAMS = new NpcStringId(1010545);
		ADEN_FRONTIER_GATEWAY = new NpcStringId(1010547);
		OREN_FRONTIER_GATEWAY = new NpcStringId(1010548);
		GARDEN_OF_WILD_BEASTS = new NpcStringId(1010549);
		DEVILS_PASS = new NpcStringId(1010550);
		THE_BULLETS_ARE_BEING_LOADED = new NpcStringId(1010551);
		YOU_CAN_START_AT_THE_SCHEDULED_TIME = new NpcStringId(1010552);
		UPPER_LEVEL_OF_THE_GIANTS_CAVE = new NpcStringId(1010554);
		LOWER_LEVEL_OF_THE_GIANTS_CAVE = new NpcStringId(1010555);
		IMMORTAL_PLATEAU_NORTHERN_REGION = new NpcStringId(1010556);
		ELVEN_RUINS = new NpcStringId(1010557);
		SINGING_WATERFALL = new NpcStringId(1010558);
		TALKING_ISLAND_NORTHERN_TERRITORY = new NpcStringId(1010559);
		ELVEN_FORTRESS = new NpcStringId(1010560);
		PILGRIMS_TEMPLE = new NpcStringId(1010561);
		GLUDIN_HARBOR = new NpcStringId(1010562);
		SHILENS_GARDEN = new NpcStringId(1010563);
		SCHOOL_OF_DARK_ARTS = new NpcStringId(1010564);
		SWAMP_OF_SCREAMS = new NpcStringId(1010565);
		THE_ANT_NEST = new NpcStringId(1010566);
		WALL_OF_ARGOS = new NpcStringId(1010568);
		DEN_OF_EVIL = new NpcStringId(1010569);
		ICEMANS_HUT = new NpcStringId(1010570);
		CRYPTS_OF_DISGRACE = new NpcStringId(1010571);
		PLUNDEROUS_PLAINS = new NpcStringId(1010572);
		PAVEL_RUINS = new NpcStringId(1010573);
		TOWN_OF_SCHUTTGART = new NpcStringId(1010574);
		MONASTERY_OF_SILENCE = new NpcStringId(1010575);
		MONASTERY_OF_SILENCE_REAR_GATE = new NpcStringId(1010576);
		STAKATO_NEST = new NpcStringId(1010577);
		HOW_DARE_YOU_TRESPASS_INTO_MY_TERRITORY_HAVE_YOU_NO_FEAR = new NpcStringId(1010578);
		FOOLS_WHY_HAVENT_YOU_FLED_YET_PREPARE_TO_LEARN_A_LESSON = new NpcStringId(1010579);
		BWAH_HA_HA_YOUR_DOOM_IS_AT_HAND_BEHOLD_THE_ULTRA_SECRET_SUPER_WEAPON = new NpcStringId(1010580);
		FOOLISH_INSIGNIFICANT_CREATURES_HOW_DARE_YOU_CHALLENGE_ME = new NpcStringId(1010581);
		I_SEE_THAT_NONE_WILL_CHALLENGE_ME_NOW = new NpcStringId(1010582);
		URGGH_YOU_WILL_PAY_DEARLY_FOR_THIS_INSULT = new NpcStringId(1010583);
		WHAT_YOU_HAVENT_EVEN_TWO_PENNIES_TO_RUB_TOGETHER_HARUMPH = new NpcStringId(1010584);
		FOREST_OF_MIRRORS = new NpcStringId(1010585);
		THE_CENTER_OF_THE_FOREST_OF_MIRRORS = new NpcStringId(1010586);
		SKY_WAGON_RELIC = new NpcStringId(1010588);
		THE_CENTER_OF_THE_DARK_FOREST = new NpcStringId(1010590);
		GRAVE_ROBBER_HIDEOUT = new NpcStringId(1010591);
		FOREST_OF_THE_DEAD = new NpcStringId(1010592);
		THE_CENTER_OF_THE_FOREST_OF_THE_DEAD = new NpcStringId(1010593);
		MITHRIL_MINES = new NpcStringId(1010594);
		THE_CENTER_OF_THE_MITHRIL_MINES = new NpcStringId(1010595);
		ABANDONED_COAL_MINES = new NpcStringId(1010596);
		THE_CENTER_OF_THE_ABANDONED_COAL_MINES = new NpcStringId(1010597);
		IMMORTAL_PLATEAU_WESTERN_REGION = new NpcStringId(1010598);
		VALLEY_OF_SAINTS = new NpcStringId(1010600);
		THE_CENTER_OF_THE_VALLEY_OF_SAINTS = new NpcStringId(1010601);
		CAVE_OF_TRIALS = new NpcStringId(1010603);
		SEAL_OF_SHILEN = new NpcStringId(1010604);
		THE_CENTER_OF_THE_WALL_OF_ARGOS = new NpcStringId(1010605);
		THE_CENTER_OF_ALLIGATOR_ISLAND = new NpcStringId(1010606);
		ANGHEL_WATERFALL = new NpcStringId(1010607);
		CENTER_OF_THE_ELVEN_RUINS = new NpcStringId(1010608);
		HOT_SPRINGS = new NpcStringId(1010609);
		THE_CENTER_OF_THE_HOT_SPRINGS = new NpcStringId(1010610);
		THE_CENTER_OF_DRAGON_VALLEY = new NpcStringId(1010611);
		THE_CENTER_OF_THE_NEUTRAL_ZONE = new NpcStringId(1010613);
		CRUMA_MARSHLANDS = new NpcStringId(1010614);
		THE_CENTER_OF_THE_CRUMA_MARSHLANDS = new NpcStringId(1010615);
		THE_CENTER_OF_THE_ENCHANTED_VALLEY = new NpcStringId(1010617);
		ENCHANTED_VALLEY_SOUTHERN_REGION = new NpcStringId(1010618);
		ENCHANTED_VALLEY_NORTHERN_REGION = new NpcStringId(1010619);
		FROST_LAKE = new NpcStringId(1010620);
		WASTELANDS = new NpcStringId(1010621);
		WASTELANDS_WESTERN_REGION = new NpcStringId(1010622);
		WHO_DARES_TO_COVET_THE_THRONE_OF_OUR_CASTLE_LEAVE_IMMEDIATELY_OR_YOU_WILL_PAY_THE_PRICE_OF_YOUR_AUDACITY_WITH_YOUR_VERY_OWN_BLOOD = new NpcStringId(1010623);
		HMM_THOSE_WHO_ARE_NOT_OF_THE_BLOODLINE_ARE_COMING_THIS_WAY_TO_TAKE_OVER_THE_CASTLE_HUMPH_THE_BITTER_GRUDGES_OF_THE_DEAD_YOU_MUST_NOT_MAKE_LIGHT_OF_THEIR_POWER = new NpcStringId(1010624);
		AARGH_IF_I_DIE_THEN_THE_MAGIC_FORCE_FIELD_OF_BLOOD_WILL = new NpcStringId(1010625);
		ITS_NOT_OVER_YET_IT_WONT_BE_OVER_LIKE_THIS_NEVER = new NpcStringId(1010626);
		OOOH_WHO_POURED_NECTAR_ON_MY_HEAD_WHILE_I_WAS_SLEEPING = new NpcStringId(1010627);
		PLEASE_WAIT_A_MOMENT = new NpcStringId(1010628);
		THE_WORD_YOU_NEED_THIS_TIME_IS_S1 = new NpcStringId(1010629);
		INTRUDERS_SOUND_THE_ALARM = new NpcStringId(1010630);
		DE_ACTIVATE_THE_ALARM = new NpcStringId(1010631);
		OH_NO_THE_DEFENSES_HAVE_FAILED_IT_IS_TOO_DANGEROUS_TO_REMAIN_INSIDE_THE_CASTLE_FLEE_EVERY_MAN_FOR_HIMSELF = new NpcStringId(1010632);
		THE_GAME_HAS_BEGUN_PARTICIPANTS_PREPARE_TO_LEARN_AN_IMPORTANT_WORD = new NpcStringId(1010633);
		S1_TEAMS_JACKPOT_HAS_S2_PERCENT_OF_ITS_HP_REMAINING = new NpcStringId(1010634);
		UNDECIDED = new NpcStringId(1010635);
		HEH_HEH_I_SEE_THAT_THE_FEAST_HAS_BEGUN_BE_WARY_THE_CURSE_OF_THE_HELLMANN_FAMILY_HAS_POISONED_THIS_LAND = new NpcStringId(1010636);
		ARISE_MY_FAITHFUL_SERVANTS_YOU_MY_PEOPLE_WHO_HAVE_INHERITED_THE_BLOOD_IT_IS_THE_CALLING_OF_MY_DAUGHTER_THE_FEAST_OF_BLOOD_WILL_NOW_BEGIN = new NpcStringId(1010637);
		GRARR_FOR_THE_NEXT_2_MINUTES_OR_SO_THE_GAME_ARENA_ARE_WILL_BE_CLEANED_THROW_ANY_ITEMS_YOU_DONT_NEED_TO_THE_FLOOR_NOW = new NpcStringId(1010639);
		GRARR_S1_TEAM_IS_USING_THE_HOT_SPRINGS_SULFUR_ON_THE_OPPONENTS_CAMP = new NpcStringId(1010640);
		GRARR_S1_TEAM_IS_ATTEMPTING_TO_STEAL_THE_JACKPOT = new NpcStringId(1010641);
		_VACANT_SEAT = new NpcStringId(1010642);
		HOW_DARE_YOU_RUIN_THE_PERFORMANCE_OF_THE_DARK_CHOIR_UNFORGIVABLE = new NpcStringId(1010644);
		GET_RID_OF_THE_INVADERS_WHO_INTERRUPT_THE_PERFORMANCE_OF_THE_DARK_CHOIR = new NpcStringId(1010645);
		DONT_YOU_HEAR_THE_MUSIC_OF_DEATH_REVEAL_THE_HORROR_OF_THE_DARK_CHOIR = new NpcStringId(1010646);
		THE_IMMORTAL_PLATEAU = new NpcStringId(1010647);
		KAMAEL_VILLAGE = new NpcStringId(1010648);
		ISLE_OF_SOULS_BASE = new NpcStringId(1010649);
		GOLDEN_HILLS_BASE = new NpcStringId(1010650);
		MIMIRS_FOREST_BASE = new NpcStringId(1010651);
		ISLE_OF_SOULS_HARBOR = new NpcStringId(1010652);
		STRONGHOLD_I = new NpcStringId(1010653);
		STRONGHOLD_II = new NpcStringId(1010654);
		STRONGHOLD_III = new NpcStringId(1010655);
		FORTRESS_WEST_GATE = new NpcStringId(1010656);
		FORTRESS_EAST_GATE = new NpcStringId(1010657);
		FORTRESS_NORTH_GATE = new NpcStringId(1010658);
		FORTRESS_SOUTH_GATE = new NpcStringId(1010659);
		FRONT_OF_THE_VALLEY_FORTRESS = new NpcStringId(1010660);
		GODDARD_TOWN_SQUARE = new NpcStringId(1010661);
		FRONT_OF_THE_GODDARD_CASTLE_GATE = new NpcStringId(1010662);
		GLUDIO_TOWN_SQUARE = new NpcStringId(1010663);
		FRONT_OF_THE_GLUDIO_CASTLE_GATE = new NpcStringId(1010664);
		GIRAN_TOWN_SQUARE = new NpcStringId(1010665);
		FRONT_OF_THE_GIRAN_CASTLE_GATE = new NpcStringId(1010666);
		FRONT_OF_THE_SOUTHERN_FORTRESS = new NpcStringId(1010667);
		FRONT_OF_THE_SWAMP_FORTRESS = new NpcStringId(1010668);
		DION_TOWN_SQUARE = new NpcStringId(1010669);
		FRONT_OF_THE_DION_CASTLE_GATE = new NpcStringId(1010670);
		RUNE_TOWN_SQUARE = new NpcStringId(1010671);
		FRONT_OF_THE_RUNE_CASTLE_GATE = new NpcStringId(1010672);
		FRONT_OF_THE_WHITE_SAND_FORTRESS = new NpcStringId(1010673);
		FRONT_OF_THE_BASIN_FORTRESS = new NpcStringId(1010674);
		FRONT_OF_THE_IVORY_FORTRESS = new NpcStringId(1010675);
		SCHUTTGART_TOWN_SQUARE = new NpcStringId(1010676);
		FRONT_OF_THE_SCHUTTGART_CASTLE_GATE = new NpcStringId(1010677);
		ADEN_TOWN_SQUARE = new NpcStringId(1010678);
		FRONT_OF_THE_ADEN_CASTLE_GATE = new NpcStringId(1010679);
		FRONT_OF_THE_SHANTY_FORTRESS = new NpcStringId(1010680);
		OREN_TOWN_SQUARE = new NpcStringId(1010681);
		FRONT_OF_THE_OREN_CASTLE_GATE = new NpcStringId(1010682);
		FRONT_OF_THE_ARCHAIC_FORTRESS = new NpcStringId(1010683);
		FRONT_OF_THE_INNADRIL_CASTLE_GATE = new NpcStringId(1010684);
		FRONT_OF_THE_BORDER_FORTRESS = new NpcStringId(1010685);
		HEINE_TOWN_SQUARE = new NpcStringId(1010686);
		FRONT_OF_THE_HIVE_FORTRESS = new NpcStringId(1010687);
		FRONT_OF_THE_NARSELL_FORTRESS = new NpcStringId(1010688);
		FRONT_OF_THE_GLUDIO_CASTLE = new NpcStringId(1010689);
		FRONT_OF_THE_DION_CASTLE = new NpcStringId(1010690);
		FRONT_OF_THE_GIRAN_CASTLE = new NpcStringId(1010691);
		FRONT_OF_THE_OREN_CASTLE = new NpcStringId(1010692);
		FRONT_OF_THE_ADEN_CASTLE = new NpcStringId(1010693);
		FRONT_OF_THE_INNADRIL_CASTLE = new NpcStringId(1010694);
		FRONT_OF_THE_GODDARD_CASTLE = new NpcStringId(1010695);
		FRONT_OF_THE_RUNE_CASTLE = new NpcStringId(1010696);
		FRONT_OF_THE_SCHUTTGART_CASTLE = new NpcStringId(1010697);
		PRIMEVAL_ISLE_WHARF = new NpcStringId(1010698);
		ISLE_OF_PRAYER = new NpcStringId(1010699);
		MITHRIL_MINES_WESTERN_ENTRANCE = new NpcStringId(1010700);
		MITHRIL_MINES_EASTERN_ENTRANCE = new NpcStringId(1010701);
		THE_GIANTS_CAVE_UPPER_LAYER = new NpcStringId(1010702);
		THE_GIANTS_CAVE_LOWER_LAYER = new NpcStringId(1010703);
		FIELD_OF_SILENCE_CENTER = new NpcStringId(1010704);
		FIELD_OF_WHISPERS_CENTER = new NpcStringId(1010705);
		SHYEEDS_CAVERN = new NpcStringId(1010706);
		SEED_OF_INFINITY_DOCK = new NpcStringId(1010709);
		SEED_OF_DESTRUCTION_DOCK = new NpcStringId(1010710);
		SEED_OF_ANNIHILATION_DOCK = new NpcStringId(1010711);
		TOWN_OF_ADEN_EINHASAD_TEMPLE_PRIEST_WOOD = new NpcStringId(1010712);
		HUNTERS_VILLAGE_SEPARATED_SOUL_FRONT = new NpcStringId(1010713);
		WHAT_TOOK_SO_LONG_I_WAITED_FOR_EVER = new NpcStringId(1029350);
		I_MUST_ASK_LIBRARIAN_SOPHIA_ABOUT_THE_BOOK = new NpcStringId(1029351);
		THIS_LIBRARY_ITS_HUGE_BUT_THERE_ARENT_MANY_USEFUL_BOOKS_RIGHT = new NpcStringId(1029352);
		AN_UNDERGROUND_LIBRARY_I_HATE_DAMP_AND_SMELLY_PLACES = new NpcStringId(1029353);
		THE_BOOK_THAT_WE_SEEK_IS_CERTAINLY_HERE_SEARCH_INCH_BY_INCH = new NpcStringId(1029354);
		WE_MUST_SEARCH_HIGH_AND_LOW_IN_EVERY_ROOM_FOR_THE_READING_DESK_THAT_CONTAINS_THE_BOOK_WE_SEEK = new NpcStringId(1029450);
		REMEMBER_THE_CONTENT_OF_THE_BOOKS_THAT_YOU_FOUND_YOU_CANT_TAKE_THEM_OUT_WITH_YOU = new NpcStringId(1029451);
		IT_SEEMS_THAT_YOU_CANNOT_REMEMBER_TO_THE_ROOM_OF_THE_WATCHER_WHO_FOUND_THE_BOOK = new NpcStringId(1029452);
		YOUR_WORK_HERE_IS_DONE_SO_RETURN_TO_THE_CENTRAL_GUARDIAN = new NpcStringId(1029453);
		YOU_FOOLISH_INVADERS_WHO_DISTURB_THE_REST_OF_SOLINA_BE_GONE_FROM_THIS_PLACE = new NpcStringId(1029460);
		I_KNOW_NOT_WHAT_YOU_SEEK_BUT_THIS_TRUTH_CANNOT_BE_HANDLED_BY_MERE_HUMANS = new NpcStringId(1029461);
		I_WILL_NOT_STAND_BY_AND_WATCH_YOUR_FOOLISH_ACTIONS_I_WARN_YOU_LEAVE_THIS_PLACE_AT_ONCE = new NpcStringId(1029462);
		THE_GUARDIAN_OF_THE_SEAL_DOESNT_SEEM_TO_GET_INJURED_AT_ALL_UNTIL_THE_BARRIER_IS_DESTROYED = new NpcStringId(1029550);
		THE_DEVICE_LOCATED_IN_THE_ROOM_IN_FRONT_OF_THE_GUARDIAN_OF_THE_SEAL_IS_DEFINITELY_THE_BARRIER_THAT_CONTROLS_THE_GUARDIANS_POWER = new NpcStringId(1029551);
		TO_REMOVE_THE_BARRIER_YOU_MUST_FIND_THE_RELICS_THAT_FIT_THE_BARRIER_AND_ACTIVATE_THE_DEVICE = new NpcStringId(1029552);
		ALL_THE_GUARDIANS_WERE_DEFEATED_AND_THE_SEAL_WAS_REMOVED_TELEPORT_TO_THE_CENTER = new NpcStringId(1029553);
		_IS_THE_PROCESS_OF_STANDING_UP = new NpcStringId(1110071);
		_IS_THE_PROCESS_OF_SITTING_DOWN = new NpcStringId(1110072);
		IT_IS_POSSIBLE_TO_USE_A_SKILL_WHILE_SITTING_DOWN = new NpcStringId(1110073);
		IS_OUT_OF_RANGE = new NpcStringId(1110074);
		THANK_YOU_MY_BOOK_CHILD = new NpcStringId(1120300);
		KILLED_BY_S2 = new NpcStringId(1120301);
		STEWARD_PLEASE_WAIT_A_MOMENT = new NpcStringId(1121000);
		STEWARD_PLEASE_RESTORE_THE_QUEENS_FORMER_APPEARANCE = new NpcStringId(1121001);
		STEWARD_WASTE_NO_TIME_PLEASE_HURRY = new NpcStringId(1121002);
		STEWARD_WAS_IT_INDEED_TOO_MUCH_TO_ASK = new NpcStringId(1121003);
		FREYA_HEATHENS_FEEL_MY_CHILL = new NpcStringId(1121004);
		ATTENTION_PLEASE_THE_GATES_WILL_BE_CLOSING_SHORTLY_ALL_VISITORS_TO_THE_QUEENS_CASTLE_SHOULD_LEAVE_IMMEDIATELY = new NpcStringId(1121005);
		YOU_CANNOT_CARRY_A_WEAPON_WITHOUT_AUTHORIZATION = new NpcStringId(1121006);
		ARE_YOU_TRYING_TO_DECEIVE_ME_IM_DISAPPOINTED = new NpcStringId(1121007);
		N30_MINUTES_REMAIN = new NpcStringId(1121008);
		N20_MINUTES_REMAIN = new NpcStringId(1121009);
		CHILLY_CODA = new NpcStringId(1200001);
		BURNING_CODA = new NpcStringId(1200002);
		BLUE_CODA = new NpcStringId(1200003);
		RED_CODA = new NpcStringId(1200004);
		GOLDEN_CODA = new NpcStringId(1200005);
		DESERT_CODA = new NpcStringId(1200006);
		LUTE_CODA = new NpcStringId(1200007);
		TWIN_CODA = new NpcStringId(1200008);
		DARK_CODA = new NpcStringId(1200009);
		SHINING_CODA = new NpcStringId(1200010);
		CHILLY_COBOL = new NpcStringId(1200011);
		BURNING_COBOL = new NpcStringId(1200012);
		BLUE_COBOL = new NpcStringId(1200013);
		RED_COBOL = new NpcStringId(1200014);
		GOLDEN_COBOL = new NpcStringId(1200015);
		DESERT_COBOL = new NpcStringId(1200016);
		SEA_COBOL = new NpcStringId(1200017);
		THORN_COBOL = new NpcStringId(1200018);
		DAPPLE_COBOL = new NpcStringId(1200019);
		GREAT_COBOL = new NpcStringId(1200020);
		CHILLY_CODRAN = new NpcStringId(1200021);
		BURNING_CODRAN = new NpcStringId(1200022);
		BLUE_CODRAN = new NpcStringId(1200023);
		RED_CODRAN = new NpcStringId(1200024);
		DAPPLE_CODRAN = new NpcStringId(1200025);
		DESERT_CODRAN = new NpcStringId(1200026);
		SEA_CODRAN = new NpcStringId(1200027);
		TWIN_CODRAN = new NpcStringId(1200028);
		THORN_CODRAN = new NpcStringId(1200029);
		GREAT_CODRAN = new NpcStringId(1200030);
		ALTERNATIVE_DARK_CODA = new NpcStringId(1200031);
		ALTERNATIVE_RED_CODA = new NpcStringId(1200032);
		ALTERNATIVE_CHILLY_CODA = new NpcStringId(1200033);
		ALTERNATIVE_BLUE_CODA = new NpcStringId(1200034);
		ALTERNATIVE_GOLDEN_CODA = new NpcStringId(1200035);
		ALTERNATIVE_LUTE_CODA = new NpcStringId(1200036);
		ALTERNATIVE_DESERT_CODA = new NpcStringId(1200037);
		ALTERNATIVE_RED_COBOL = new NpcStringId(1200038);
		ALTERNATIVE_CHILLY_COBOL = new NpcStringId(1200039);
		ALTERNATIVE_BLUE_COBOL = new NpcStringId(1200040);
		ALTERNATIVE_THORN_COBOL = new NpcStringId(1200041);
		ALTERNATIVE_GOLDEN_COBOL = new NpcStringId(1200042);
		ALTERNATIVE_GREAT_COBOL = new NpcStringId(1200043);
		ALTERNATIVE_RED_CODRAN = new NpcStringId(1200044);
		ALTERNATIVE_SEA_CODRAN = new NpcStringId(1200045);
		ALTERNATIVE_CHILLY_CODRAN = new NpcStringId(1200046);
		ALTERNATIVE_BLUE_CODRAN = new NpcStringId(1200047);
		ALTERNATIVE_TWIN_CODRAN = new NpcStringId(1200048);
		ALTERNATIVE_GREAT_CODRAN = new NpcStringId(1200049);
		ALTERNATIVE_DESERT_CODRAN = new NpcStringId(1200050);
		WE_HAVE_BROKEN_THROUGH_THE_GATE_DESTROY_THE_ENCAMPMENT_AND_MOVE_TO_THE_COMMAND_POST = new NpcStringId(1300001);
		THE_COMMAND_GATE_HAS_OPENED_CAPTURE_THE_FLAG_QUICKLY_AND_RAISE_IT_HIGH_TO_PROCLAIM_OUR_VICTORY = new NpcStringId(1300002);
		THE_GODS_HAVE_FORSAKEN_US_RETREAT = new NpcStringId(1300003);
		YOU_MAY_HAVE_BROKEN_OUR_ARROWS_BUT_YOU_WILL_NEVER_BREAK_OUR_WILL_ARCHERS_RETREAT = new NpcStringId(1300004);
		AT_LAST_THE_MAGIC_FIELD_THAT_PROTECTS_THE_FORTRESS_HAS_WEAKENED_VOLUNTEERS_STAND_BACK = new NpcStringId(1300005);
		AIIEEEE_COMMAND_CENTER_THIS_IS_GUARD_UNIT_WE_NEED_BACKUP_RIGHT_AWAY = new NpcStringId(1300006);
		FORTRESS_POWER_DISABLED = new NpcStringId(1300007);
		OH_MY_WHAT_HAS_BECOME_OF_ME_MY_FAME_MY_FRIENDS_LOST_ALL_LOST = new NpcStringId(1300008);
		MACHINE_NO_1_POWER_OFF = new NpcStringId(1300009);
		MACHINE_NO_2_POWER_OFF = new NpcStringId(1300010);
		MACHINE_NO_3_POWER_OFF = new NpcStringId(1300011);
		EVERYONE_CONCENTRATE_YOUR_ATTACKS_ON_S1_SHOW_THE_ENEMY_YOUR_RESOLVE = new NpcStringId(1300012);
		ATTACKING_THE_ENEMYS_REINFORCEMENTS_IS_NECESSARY_TIME_TO_DIE = new NpcStringId(1300013);
		SPIRIT_OF_FIRE_UNLEASH_YOUR_POWER_BURN_THE_ENEMY = new NpcStringId(1300014);
		HEY_THESE_FOES_ARE_TOUGHER_THAN_THEY_LOOK_IM_GOING_TO_NEED_SOME_HELP_HERE = new NpcStringId(1300015);
		DO_YOU_NEED_MY_POWER_YOU_SEEM_TO_BE_STRUGGLING = new NpcStringId(1300016);
		IM_RATHER_BUSY_HERE_AS_WELL = new NpcStringId(1300017);
		DONT_THINK_THAT_ITS_GONNA_END_LIKE_THIS_YOUR_AMBITION_WILL_SOON_BE_DESTROYED_AS_WELL = new NpcStringId(1300018);
		YOU_MUST_HAVE_BEEN_PREPARED_TO_DIE = new NpcStringId(1300019);
		I_FEEL_SO_MUCH_GRIEF_THAT_I_CANT_EVEN_TAKE_CARE_OF_MYSELF_THERE_ISNT_ANY_REASON_FOR_ME_TO_STAY_HERE_ANY_LONGER = new NpcStringId(1300020);
		SHANTY_FORTRESS = new NpcStringId(1300101);
		SOUTHERN_FORTRESS = new NpcStringId(1300102);
		HIVE_FORTRESS = new NpcStringId(1300103);
		VALLEY_FORTRESS = new NpcStringId(1300104);
		IVORY_FORTRESS = new NpcStringId(1300105);
		NARSELL_FORTRESS = new NpcStringId(1300106);
		BASIN_FORTRESS = new NpcStringId(1300107);
		WHITE_SANDS_FORTRESS = new NpcStringId(1300108);
		BORDERLAND_FORTRESS = new NpcStringId(1300109);
		SWAMP_FORTRESS = new NpcStringId(1300110);
		ARCHAIC_FORTRESS = new NpcStringId(1300111);
		FLORAN_FORTRESS = new NpcStringId(1300112);
		CLOUD_MOUNTAIN_FORTRESS = new NpcStringId(1300113);
		TANOR_FORTRESS = new NpcStringId(1300114);
		DRAGONSPINE_FORTRESS = new NpcStringId(1300115);
		ANTHARASS_FORTRESS = new NpcStringId(1300116);
		WESTERN_FORTRESS = new NpcStringId(1300117);
		HUNTERS_FORTRESS = new NpcStringId(1300118);
		AARU_FORTRESS = new NpcStringId(1300119);
		DEMON_FORTRESS = new NpcStringId(1300120);
		MONASTIC_FORTRESS = new NpcStringId(1300121);
		INDEPENDENT_STATE = new NpcStringId(1300122);
		NONPARTISAN = new NpcStringId(1300123);
		CONTRACT_STATE = new NpcStringId(1300124);
		FIRST_PASSWORD_HAS_BEEN_ENTERED = new NpcStringId(1300125);
		SECOND_PASSWORD_HAS_BEEN_ENTERED = new NpcStringId(1300126);
		PASSWORD_HAS_NOT_BEEN_ENTERED = new NpcStringId(1300127);
		ATTEMPT_S1_3_IS_IN_PROGRESS_THIS_IS_THE_THIRD_ATTEMPT_ON_S1 = new NpcStringId(1300128);
		THE_1ST_MARK_IS_CORRECT = new NpcStringId(1300129);
		THE_2ND_MARK_IS_CORRECT = new NpcStringId(1300130);
		THE_MARKS_HAVE_NOT_BEEN_ASSEMBLED = new NpcStringId(1300131);
		OLYMPIAD_CLASS_FREE_TEAM_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT = new NpcStringId(1300132);
		DOMAIN_FORTRESS = new NpcStringId(1300133);
		BOUNDARY_FORTRESS = new NpcStringId(1300134);
		S1HOUR_S2MINUTE = new NpcStringId(1300135);
		NOT_DESIGNATED = new NpcStringId(1300136);
		WARRIORS_HAVE_YOU_COME_TO_HELP_THOSE_WHO_ARE_IMPRISONED_HERE = new NpcStringId(1300137);
		TAKE_THAT_YOU_WEAKLING = new NpcStringId(1300138);
		BEHOLD_MY_MIGHT = new NpcStringId(1300139);
		YOUR_MIND_IS_GOING_BLANK = new NpcStringId(1300140);
		UGH_IT_HURTS_DOWN_TO_THE_BONES = new NpcStringId(1300141);
		I_CANT_STAND_IT_ANYMORE_AAH = new NpcStringId(1300142);
		KYAAAK = new NpcStringId(1300143);
		GASP_HOW_CAN_THIS_BE = new NpcStringId(1300144);
		ILL_RIP_THE_FLESH_FROM_YOUR_BONES = new NpcStringId(1300145);
		YOULL_FLOUNDER_IN_DELUSION_FOR_THE_REST_OF_YOUR_LIFE = new NpcStringId(1300146);
		THERE_IS_NO_ESCAPE_FROM_THIS_PLACE = new NpcStringId(1300147);
		HOW_DARE_YOU = new NpcStringId(1300148);
		I_SHALL_DEFEAT_YOU = new NpcStringId(1300149);
		BEGIN_STAGE_1 = new NpcStringId(1300150);
		BEGIN_STAGE_2 = new NpcStringId(1300151);
		BEGIN_STAGE_3 = new NpcStringId(1300152);
		YOUVE_DONE_IT_WE_BELIEVED_IN_YOU_WARRIOR_WE_WANT_TO_SHOW_OUR_SINCERITY_THOUGH_IT_IS_SMALL_PLEASE_GIVE_ME_SOME_OF_YOUR_TIME = new NpcStringId(1300153);
		THE_CENTRAL_STRONGHOLDS_COMPRESSOR_IS_WORKING = new NpcStringId(1300154);
		STRONGHOLD_IS_COMPRESSOR_IS_WORKING = new NpcStringId(1300155);
		STRONGHOLD_IIS_COMPRESSOR_IS_WORKING = new NpcStringId(1300156);
		STRONGHOLD_IIIS_COMPRESSOR_IS_WORKING = new NpcStringId(1300157);
		THE_CENTRAL_STRONGHOLDS_COMPRESSOR_HAS_BEEN_DESTROYED = new NpcStringId(1300158);
		STRONGHOLD_IS_COMPRESSOR_HAS_BEEN_DESTROYED = new NpcStringId(1300159);
		STRONGHOLD_IIS_COMPRESSOR_HAS_BEEN_DESTROYED = new NpcStringId(1300160);
		STRONGHOLD_IIIS_COMPRESSOR_HAS_BEEN_DESTROYED = new NpcStringId(1300161);
		WHAT_A_PREDICAMENT_MY_ATTEMPTS_WERE_UNSUCCESSFUL = new NpcStringId(1300162);
		COURAGE_AMBITION_PASSION_MERCENARIES_WHO_WANT_TO_REALIZE_THEIR_DREAM_OF_FIGHTING_IN_THE_TERRITORY_WAR_COME_TO_ME_FORTUNE_AND_GLORY_ARE_WAITING_FOR_YOU = new NpcStringId(1300163);
		DO_YOU_WISH_TO_FIGHT_ARE_YOU_AFRAID_NO_MATTER_HOW_HARD_YOU_TRY_YOU_HAVE_NOWHERE_TO_RUN_BUT_IF_YOU_FACE_IT_HEAD_ON_OUR_MERCENARY_TROOP_WILL_HELP_YOU_OUT = new NpcStringId(1300164);
		CHARGE_CHARGE_CHARGE = new NpcStringId(1300165);
		OLYMPIAD_CLASS_FREE_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT = new NpcStringId(1300166);
		OLYMPIAD_CLASS_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT = new NpcStringId(1300167);
		ANOTHER_PLAYER_IS_CURRENTLY_BEING_BUFFED_PLEASE_TRY_AGAIN_IN_A_MOMENT = new NpcStringId(1600001);
		YOU_CANNOT_MOUNT_WHILE_YOU_ARE_POLYMORPHED = new NpcStringId(1600002);
		YOU_CANNOT_MOUNT_A_WYVERN_WHILE_IN_BATTLE_MODE_OR_WHILE_MOUNTED_ON_A_STRIDER = new NpcStringId(1600003);
		BOO_HOO_I_HATE = new NpcStringId(1600004);
		SEE_YOU_LATER = new NpcStringId(1600005);
		YOUVE_MADE_A_GREAT_CHOICE = new NpcStringId(1600006);
		THANKS_I_FEEL_MORE_RELAXED = new NpcStringId(1600007);
		DID_YOU_SEE_THAT_FIRECRACKER_EXPLODE = new NpcStringId(1600008);
		I_AM_NOTHING = new NpcStringId(1600009);
		I_AM_TELLING_THE_TRUTH = new NpcStringId(1600010);
		ITS_FREE_TO_GO_BACK_TO_THE_VILLAGE_YOU_TELEPORTED_FROM = new NpcStringId(1600012);
		IF_YOU_COLLECT_50_INDIVIDUAL_TREASURE_SACK_PIECES_YOU_CAN_EXCHANGE_THEM_FOR_A_TREASURE_SACK = new NpcStringId(1600013);
		YOU_MUST_BE_TRANSFORMED_INTO_A_TREASURE_HUNTER_TO_FIND_A_CHEST = new NpcStringId(1600014);
		YOUD_BETTER_USE_THE_TRANSFORMATION_SPELL_AT_THE_RIGHT_MOMENT_SINCE_IT_DOESNT_LAST_LONG = new NpcStringId(1600015);
		ALL_OF_FANTASY_ISLE_IS_A_PEACE_ZONE = new NpcStringId(1600016);
		IF_YOU_NEED_TO_GO_TO_FANTASY_ISLE_COME_SEE_ME = new NpcStringId(1600017);
		YOU_CAN_ONLY_PURCHASE_A_TREASURE_HUNTER_TRANSFORMATION_SCROLL_ONCE_EVERY_12_HOURS = new NpcStringId(1600018);
		IF_YOUR_MEANS_OF_ARRIVAL_WAS_A_BIT_UNCONVENTIONAL_THEN_ILL_BE_SENDING_YOU_BACK_TO_RUNE_TOWNSHIP_WHICH_IS_THE_NEAREST_TOWN = new NpcStringId(1600019);
		RATTLE = new NpcStringId(1600020);
		BUMP = new NpcStringId(1600021);
		YOU_WILL_REGRET_THIS = new NpcStringId(1600022);
		CARE_TO_CHALLENGE_FATE_AND_TEST_YOUR_LUCK = new NpcStringId(1600023);
		DONT_PASS_UP_THE_CHANCE_TO_WIN_AN_S80_WEAPON = new NpcStringId(1600024);
		_S1S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS = new NpcStringId(1800009);
		LOOTING_RULES_ARE_NO_LONGER_ACTIVE = new NpcStringId(1800010);
		OUR_MASTER_NOW_COMES_TO_CLAIM_OUR_VENGEANCE_SOON_YOU_WILL_ALL_BE_NOTHING_MORE_THAN_DIRT = new NpcStringId(1800011);
		DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORDS_OF_DAWN = new NpcStringId(1800012);
		DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORD = new NpcStringId(1800013);
		OINK_OINK_PIGS_CAN_DO_IT_TOO_ANTHARAS_SURPASSING_SUPER_POWERED_PIG_STUN_HOW_DO_LIKE_THEM_APPLES = new NpcStringId(1800014);
		OINK_OINK_TAKE_THAT_VALAKAS_TERRORIZING_ULTRA_PIG_FEAR_HA_HA = new NpcStringId(1800015);
		OINK_OINK_GO_AWAY_STOP_BOTHERING_ME = new NpcStringId(1800016);
		OINK_OINK_PIGS_OF_THE_WORLD_UNITE_LETS_SHOW_THEM_OUR_STRENGTH = new NpcStringId(1800017);
		YOU_HEALED_ME_THANKS_A_LOT_OINK_OINK = new NpcStringId(1800018);
		OINK_OINK_THIS_TREATMENT_HURTS_TOO_MUCH_ARE_YOU_SURE_THAT_YOURE_TRYING_TO_HEAL_ME = new NpcStringId(1800019);
		OINK_OINK_TRANSFORM_WITH_MOON_CRYSTAL_PRISM_POWER = new NpcStringId(1800020);
		OINK_OINK_NOOO_I_DONT_WANT_TO_GO_BACK_TO_NORMAL = new NpcStringId(1800021);
		OINK_OINK_IM_RICH_SO_IVE_GOT_PLENTY_TO_SHARE_THANKS = new NpcStringId(1800022);
		ITS_A_WEAPON_SURROUNDED_BY_AN_OMINOUS_AURA_ILL_DISCARD_IT_BECAUSE_IT_MAY_BE_DANGEROUS_MISS = new NpcStringId(1800023);
		THANK_YOU_FOR_SAVING_ME_FROM_THE_CLUTCHES_OF_EVIL = new NpcStringId(1800024);
		THAT_IS_IT_FOR_TODAYLETS_RETREAT_EVERYONE_PULL_BACK = new NpcStringId(1800025);
		THANK_YOU_FOR_THE_RESCUE_ITS_A_SMALL_GIFT = new NpcStringId(1800026);
		S1_YOU_DONT_HAVE_A_RED_CRYSTAL = new NpcStringId(1800027);
		S1_YOU_DONT_HAVE_A_BLUE_CRYSTAL = new NpcStringId(1800028);
		S1_YOU_DONT_HAVE_A_CLEAR_CRYSTAL = new NpcStringId(1800029);
		S1_IF_YOU_ARE_TOO_FAR_AWAY_FROM_MEI_CANT_LET_YOU_GO = new NpcStringId(1800030);
		AN_ALARM_HAS_BEEN_SET_OFF_EVERYBODY_WILL_BE_IN_DANGER_IF_THEY_ARE_NOT_TAKEN_CARE_OF_IMMEDIATELY = new NpcStringId(1800031);
		IT_WILL_NOT_BE_THAT_EASY_TO_KILL_ME = new NpcStringId(1800032);
		NOYOU_KNEW_MY_WEAKNESS = new NpcStringId(1800033);
		HELLO_IS_ANYONE_THERE = new NpcStringId(1800034);
		IS_NO_ONE_THERE_HOW_LONG_HAVE_I_BEEN_HIDING_I_HAVE_BEEN_STARVING_FOR_DAYS_AND_CANNOT_HOLD_OUT_ANYMORE = new NpcStringId(1800035);
		IF_SOMEONE_WOULD_GIVE_ME_SOME_OF_THOSE_TASTY_CRYSTAL_FRAGMENTS_I_WOULD_GLADLY_TELL_THEM_WHERE_TEARS_IS_HIDING_YUMMY_YUMMY = new NpcStringId(1800036);
		HEY_YOU_FROM_ABOVE_THE_GROUND_LETS_SHARE_SOME_CRYSTAL_FRAGMENTS_IF_YOU_HAVE_ANY = new NpcStringId(1800037);
		CRISPY_AND_COLD_FEELING_TEEHEE_DELICIOUS = new NpcStringId(1800038);
		YUMMY_THIS_IS_SO_TASTY = new NpcStringId(1800039);
		SNIFF_SNIFF_GIVE_ME_MORE_CRYSTAL_FRAGMENTS = new NpcStringId(1800040);
		HOW_INSENSITIVE_ITS_NOT_NICE_TO_GIVE_ME_JUST_A_PIECE_CANT_YOU_GIVE_ME_MORE = new NpcStringId(1800041);
		AH_IM_HUNGRY = new NpcStringId(1800042);
		IM_THE_REAL_ONE = new NpcStringId(1800043);
		PICK_ME = new NpcStringId(1800044);
		TRUST_ME = new NpcStringId(1800045);
		NOT_THAT_DUDE_IM_THE_REAL_ONE = new NpcStringId(1800046);
		DONT_BE_FOOLED_DONT_BE_FOOLED_IM_THE_REAL_ONE = new NpcStringId(1800047);
		JUST_ACT_LIKE_THE_REAL_ONE_OOPS = new NpcStringId(1800048);
		YOUVE_BEEN_FOOLED = new NpcStringId(1800049);
		SORRY_BUT_IM_THE_FAKE_ONE = new NpcStringId(1800050);
		IM_THE_REAL_ONE_PHEW = new NpcStringId(1800051);
		CANT_YOU_EVEN_FIND_OUT = new NpcStringId(1800052);
		FIND_ME = new NpcStringId(1800053);
		HUH_HOW_DID_YOU_KNOW_IT_WAS_ME = new NpcStringId(1800054);
		EXCELLENT_CHOICE_TEEHEE = new NpcStringId(1800055);
		YOUVE_DONE_WELL = new NpcStringId(1800056);
		OH_VERY_SENSIBLE = new NpcStringId(1800057);
		BEHOLD_THE_MIGHTY_POWER_OF_BAYLOR_FOOLISH_MORTAL = new NpcStringId(1800058);
		NO_ONE_IS_GOING_TO_SURVIVE = new NpcStringId(1800059);
		YOULL_SEE_WHAT_HELL_IS_LIKE = new NpcStringId(1800060);
		YOU_WILL_BE_PUT_IN_JAIL = new NpcStringId(1800061);
		WORTHLESS_CREATURE_GO_TO_HELL = new NpcStringId(1800062);
		ILL_GIVE_YOU_SOMETHING_THAT_YOULL_NEVER_FORGET = new NpcStringId(1800063);
		WHY_DID_YOU_TRUST_TO_CHOOSE_ME_HAHAHAHA = new NpcStringId(1800064);
		ILL_MAKE_YOU_REGRET_THAT_YOU_EVER_CHOSE_ME = new NpcStringId(1800065);
		DONT_EXPECT_TO_GET_OUT_ALIVE = new NpcStringId(1800066);
		DEMON_KING_BELETH_GIVE_ME_THE_POWER_AAAHH = new NpcStringId(1800067);
		NO_I_FEEL_THE_POWER_OF_FAFURION = new NpcStringId(1800068);
		FAFURION_PLEASE_GIVE_POWER_TO_THIS_HELPLESS_WITCH = new NpcStringId(1800069);
		I_CANT_HELP_YOU_MUCH_BUT_I_CAN_WEAKEN_THE_POWER_OF_BAYLOR_SINCE_IM_LOCKED_UP_HERE = new NpcStringId(1800070);
		YOUR_SKILL_IS_IMPRESSIVE_ILL_ADMIT_THAT_YOU_ARE_GOOD_ENOUGH_TO_PASS_TAKE_THE_KEY_AND_LEAVE_THIS_PLACE = new NpcStringId(1800071);
		GIVE_ME_ALL_YOU_HAVE_ITS_THE_ONLY_WAY_ILL_LET_YOU_LIVE = new NpcStringId(1800072);
		HUN_HUNGRY = new NpcStringId(1800073);
		DONT_BE_LAZY_YOU_BASTARDS = new NpcStringId(1800074);
		THEY_ARE_JUST_HENCHMEN_OF_THE_IRON_CASTLE_WHY_DID_WE_HIDE = new NpcStringId(1800075);
		GUYS_SHOW_THEM_OUR_POWER = new NpcStringId(1800076);
		YOU_HAVE_FINALLY_COME_HERE_BUT_YOU_WILL_NOT_BE_ABLE_TO_FIND_THE_SECRET_ROOM = new NpcStringId(1800077);
		YOU_HAVE_DONE_WELL_IN_FINDING_ME_BUT_I_CANNOT_JUST_HAND_YOU_THE_KEY = new NpcStringId(1800078);
		THE_MATCH_IS_AUTOMATICALLY_CANCELED_BECAUSE_YOU_ARE_TOO_FAR_FROM_THE_ADMISSION_MANAGER = new NpcStringId(1800081);
		UGH_I_HAVE_BUTTERFLIES_IN_MY_STOMACH_THE_SHOW_STARTS_SOON = new NpcStringId(1800082);
		THANK_YOU_ALL_FOR_COMING_HERE_TONIGHT = new NpcStringId(1800083);
		IT_IS_AN_HONOR_TO_HAVE_THE_SPECIAL_SHOW_TODAY = new NpcStringId(1800084);
		FANTASY_ISLE_IS_FULLY_COMMITTED_TO_YOUR_HAPPINESS = new NpcStringId(1800085);
		NOW_ID_LIKE_TO_INTRODUCE_THE_MOST_BEAUTIFUL_SINGER_IN_ADEN_PLEASE_WELCOMELEYLA_MIRA = new NpcStringId(1800086);
		HERE_SHE_COMES = new NpcStringId(1800087);
		THANK_YOU_VERY_MUCH_LEYLA = new NpcStringId(1800088);
		NOW_WERE_IN_FOR_A_REAL_TREAT = new NpcStringId(1800089);
		JUST_BACK_FROM_THEIR_WORLD_TOUR_PUT_YOUR_HANDS_TOGETHER_FOR_THE_FANTASY_ISLE_CIRCUS = new NpcStringId(1800090);
		COME_ON_EVERYONE = new NpcStringId(1800091);
		DID_YOU_LIKE_IT_THAT_WAS_SO_AMAZING = new NpcStringId(1800092);
		NOW_WE_ALSO_INVITED_INDIVIDUALS_WITH_SPECIAL_TALENTS = new NpcStringId(1800093);
		LETS_WELCOME_THE_FIRST_PERSON_HERE = new NpcStringId(1800094);
		OH = new NpcStringId(1800095);
		OKAY_NOW_HERE_COMES_THE_NEXT_PERSON_COME_ON_UP_PLEASE = new NpcStringId(1800096);
		OH_IT_LOOKS_LIKE_SOMETHING_GREAT_IS_GOING_TO_HAPPEN_RIGHT = new NpcStringId(1800097);
		OH_MY = new NpcStringId(1800098);
		THATS_G_GREAT_NOW_HERE_COMES_THE_LAST_PERSON = new NpcStringId(1800099);
		NOW_THIS_IS_THE_END_OF_TODAYS_SHOW = new NpcStringId(1800100);
		HOW_WAS_IT_I_HOPE_YOU_ALL_ENJOYED_IT = new NpcStringId(1800101);
		PLEASE_REMEMBER_THAT_FANTASY_ISLE_IS_ALWAYS_PLANNING_A_LOT_OF_GREAT_SHOWS_FOR_YOU = new NpcStringId(1800102);
		WELL_I_WISH_I_COULD_CONTINUE_ALL_NIGHT_LONG_BUT_THIS_IS_IT_FOR_TODAY_THANK_YOU = new NpcStringId(1800103);
		WE_LOVE_YOU = new NpcStringId(1800104);
		HOW_COME_PEOPLE_ARE_NOT_HERE_WE_ARE_ABOUT_TO_START_THE_SHOW_HMM = new NpcStringId(1800105);
		THE_OPPONENT_TEAM_CANCELED_THE_MATCH = new NpcStringId(1800106);
		ITS_NOT_EASY_TO_OBTAIN = new NpcStringId(1800107);
		YOURE_OUT_OF_YOUR_MIND_COMING_HERE = new NpcStringId(1800108);
		ENEMY_INVASION_HURRY_UP = new NpcStringId(1800109);
		PROCESS_SHOULDNT_BE_DELAYED_BECAUSE_OF_ME = new NpcStringId(1800110);
		ALRIGHT_NOW_LEODAS_IS_YOURS = new NpcStringId(1800111);
		WE_MIGHT_NEED_NEW_SLAVES_ILL_BE_BACK_SOON_SO_WAIT = new NpcStringId(1800112);
		TIME_RIFT_DEVICE_ACTIVATION_SUCCESSFUL = new NpcStringId(1800113);
		FREEDOM_OR_DEATH = new NpcStringId(1800114);
		THIS_IS_THE_WILL_OF_TRUE_WARRIORS = new NpcStringId(1800115);
		WELL_HAVE_DINNER_IN_HELL = new NpcStringId(1800116);
		ZZZZ_CITY_INTERFERENCE_ERROR_FORWARD_EFFECT_CREATED = new NpcStringId(1800118);
		ZZZZ_CITY_INTERFERENCE_ERROR_RECURRENCE_EFFECT_CREATED = new NpcStringId(1800119);
		GUARDS_ARE_COMING_RUN = new NpcStringId(1800120);
		NOW_I_CAN_ESCAPE_ON_MY_OWN = new NpcStringId(1800121);
		THANKS_FOR_YOUR_HELP = new NpcStringId(1800122);
		MATCH_CANCELLED_OPPONENT_DID_NOT_MEET_THE_STADIUM_ADMISSION_REQUIREMENTS = new NpcStringId(1800123);
		HA_HA_YES_DIE_SLOWLY_WRITHING_IN_PAIN_AND_AGONY = new NpcStringId(1800124);
		MORE_NEED_MORE_SEVERE_PAIN = new NpcStringId(1800125);
		AHH_MY_LIFE_IS_BEING_DRAINED_OUT = new NpcStringId(1800126);
		SOMETHING_IS_BURNING_INSIDE_MY_BODY = new NpcStringId(1800127);
		S1_NOT_ADEQUATE_FOR_THE_STADIUM_LEVEL = new NpcStringId(1800128);
		S1_THANK_YOU_FOR_GIVING_ME_YOUR_LIFE = new NpcStringId(1800129);
		I_FAILED_PLEASE_FORGIVE_ME_DARION = new NpcStringId(1800130);
		S1_ILL_BE_BACK_DONT_GET_COMFORTABLE = new NpcStringId(1800131);
		IF_YOU_THINK_IM_GIVING_UP_LIKE_THIS_YOURE_WRONG = new NpcStringId(1800132);
		SO_YOURE_JUST_GOING_TO_WATCH_ME_SUFFER = new NpcStringId(1800133);
		ITS_NOT_OVER_YET = new NpcStringId(1800134);
		HA_HA_YOU_WERE_SO_AFRAID_OF_DEATH_LET_ME_SEE_IF_YOU_FIND_ME_IN_TIME_MAYBE_YOU_CAN_FIND_A_WAY = new NpcStringId(1800135);
		DONT_KILL_ME_PLEASE_SOMETHINGS_STRANGLING_ME = new NpcStringId(1800136);
		WHO_WILL_BE_THE_LUCKY_ONE_TONIGHT_HA_HA_CURIOUS_VERY_CURIOUS = new NpcStringId(1800137);
		SQUEAK_THIS_WILL_BE_STRONGER_THAN_THE_STUN_THE_PIG_USED_LAST_TIME = new NpcStringId(1800138);
		SQUEAK_HERE_IT_GOES_EXTREMELY_SCARY_EVEN_TO_VALAKAS = new NpcStringId(1800139);
		SQUEAK_GO_AWAY_LEAVE_US_ALONE = new NpcStringId(1800140);
		SQUEAK_GUYS_GATHER_UP_LETS_SHOW_OUR_POWER = new NpcStringId(1800141);
		ITS_NOT_LIKE_IM_GIVING_THIS_BECAUSE_IM_GRATEFUL_SQUEAK = new NpcStringId(1800142);
		SQUEAK_EVEN_IF_IT_IS_TREATMENT_MY_BOTTOM_HURTS_SO_MUCH = new NpcStringId(1800143);
		SQUEAK_TRANSFORM_TO_MOON_CRYSTAL_PRISM_POWER = new NpcStringId(1800144);
		SQUEAK_OH_NO_I_DONT_WANT_TO_TURN_BACK_AGAIN = new NpcStringId(1800145);
		SQUEAK_IM_SPECIALLY_GIVING_YOU_A_LOT_SINCE_IM_RICH_THANK_YOU = new NpcStringId(1800146);
		OINK_OINK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER = new NpcStringId(1800147);
		OINK_OINK_IM_REALLY_FURIOUS_RIGHT_NOW = new NpcStringId(1800148);
		SQUEAK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER = new NpcStringId(1800149);
		SQUEAK_IM_REALLY_FURIOUS_RIGHT_NOW = new NpcStringId(1800150);
		G_RANK = new NpcStringId(1800162);
		HUH_NO_ONE_WOULD_HAVE_GUESSED_THAT_A_DOOMED_CREATURE_WOULD_BE_SO_POWERFUL = new NpcStringId(1800163);
		S_GRADE = new NpcStringId(1800164);
		A_GRADE = new NpcStringId(1800165);
		B_GRADE = new NpcStringId(1800166);
		C_GRADE = new NpcStringId(1800167);
		D_GRADE = new NpcStringId(1800168);
		F_GRADE = new NpcStringId(1800169);
		THIS_IS_THIS_IS_A_GREAT_ACHIEVEMENT_THAT_IS_WORTHY_OF_THE_TRUE_HEROES_OF_LEGEND = new NpcStringId(1800170);
		ADMIRABLE_YOU_GREATLY_DECREASED_THE_SPEED_OF_INVASION_THROUGH_KAMALOKA = new NpcStringId(1800171);
		VERY_GOOD_YOUR_SKILL_MAKES_YOU_A_MODEL_FOR_OTHER_ADVENTURERS_TO_FOLLOW = new NpcStringId(1800172);
		GOOD_WORK_IF_ALL_ADVENTURERS_PRODUCE_RESULTS_LIKE_YOU_WE_WILL_SLOWLY_START_TO_SEE_THE_GLIMMER_OF_HOPE = new NpcStringId(1800173);
		UNFORTUNATELY_IT_SEEMS_THAT_RIM_KAMALOKA_CANNOT_BE_EASILY_APPROACHED_BY_EVERYONE = new NpcStringId(1800174);
		HOW_DISAPPOINTING_IT_LOOKS_LIKE_I_MADE_A_MISTAKE_IN_SENDING_YOU_INSIDE_RIM_KAMALOKA = new NpcStringId(1800175);
		INTRUDER_ALERT_INTRUDER_ALERT = new NpcStringId(1800176);
		WHAT_ARE_YOU_DOING_HURRY_UP_AND_HELP_ME = new NpcStringId(1800177);
		IVE_HAD_IT_UP_TO_HERE_WITH_YOU_ILL_TAKE_CARE_OF_YOU = new NpcStringId(1800178);
		AH_MY_MIND_IS_A_WRECK = new NpcStringId(1800179);
		IF_YOU_THOUGHT_THAT_MY_SUBORDINATES_WOULD_BE_SO_FEW_YOU_ARE_MISTAKEN = new NpcStringId(1800180);
		THERES_NOT_MUCH_I_CAN_DO_BUT_I_WANT_TO_HELP_YOU = new NpcStringId(1800181);
		YOU_S1_ATTACK_THEM = new NpcStringId(1800182);
		COME_OUT_MY_SUBORDINATE_I_SUMMON_YOU_TO_DRIVE_THEM_OUT = new NpcStringId(1800183);
		THERES_NOT_MUCH_I_CAN_DO_BUT_I_WILL_RISK_MY_LIFE_TO_HELP_YOU = new NpcStringId(1800184);
		ARG_THE_PAIN_IS_MORE_THAN_I_CAN_STAND = new NpcStringId(1800185);
		AHH_HOW_DID_HE_FIND_MY_WEAKNESS = new NpcStringId(1800186);
		WE_WERE_ABLE_TO_SUCCESSFULLY_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE = new NpcStringId(1800187);
		BUT_WE_WERE_ABLE_TO_COLLECT_SOMEHOW_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE = new NpcStringId(1800188);
		IM_SORRY_BUT_WE_WERE_UNABLE_TO_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_BECAUSE_THEIR_DARK_ENERGY_WAS_TOO_WEAK = new NpcStringId(1800189);
		RATHER_THAN_SIMPLY_DEFEATING_THE_ENEMIES_YOU_SEEM_TO_UNDERSTAND_OUR_GOAL_AND_PURPOSE_AS_WELL = new NpcStringId(1800190);
		DOPPLERS_AND_VOIDS_POSSESS_AN_ENHANCED_AMOUNT_OF_THE_KANABIONS_DARK_ENERGY_SO_IT_IS_IMPORTANT_TO_CONCENTRATE_ON_DEFEATING_THEM_WHEN_BLOCKING_THE_KAMALOKIANS_ATTACK = new NpcStringId(1800191);
		HAVE_YOU_SEEN_KANABIONS_BEING_REMADE_AS_NEW_KANABIONS_SOMETIMES_YOU_CAN_SEE_IT_OCCUR_MORE_OFTEN_BY_INFLICTING_GREAT_DAMAGE_DURING_AN_ATTACK_OR_AT_THE_MOMENT_YOU_DEFEAT_THEM = new NpcStringId(1800192);
		AS_IN_ANY_OTHER_BATTLE_IT_IS_CRITICAL_TO_PROTECT_YOURSELF_WHILE_YOU_ARE_INSIDE_RIM_KAMALOKA_WE_DO_NOT_WANT_TO_ATTACK_RECKLESSLY = new NpcStringId(1800193);
		WE_VALUE_DEVELOPING_AN_INDIVIDUALS_OVERALL_POWER_RATHER_THAN_A_ONE_TIME_VICTORY_IF_YOU_RELIED_ON_ANOTHER_PERSONS_SUPPORT_THIS_TIME_WHY_DONT_YOU_TRY_TO_RELY_ON_YOUR_OWN_STRENGTH_NEXT_TIME = new NpcStringId(1800194);
		ARE_YOU_SURE_THAT_THE_BATTLE_JUST_NOW_WAS_AT_THE_APPROPRIATE_LEVEL_BOTHERING_LOWER_KANABIONS_AS_IF_FOR_MERE_ENTERTAINMENT_IS_CONSIDERED_TO_BE_A_WASTED_BATTLE_FOR_US = new NpcStringId(1800195);
		THE_GREATEST_VICTORY_INVOLVES_USING_ALL_AVAILABLE_RESOURCES_ELIMINATING_ALL_OF_THE_ENEMYS_OPPORTUNITIES_AND_BRINGING_IT_TO_THE_FASTEST_POSSIBLE_END_DONT_YOU_THINK_SO = new NpcStringId(1800196);
		EMERGENCY_EMERGENCY_THE_OUTER_WALL_IS_WEAKENING_RAPIDLY = new NpcStringId(1800197);
		THE_REMAINING_STRENGTH_OF_THE_OUTER_WALL_IS_S1 = new NpcStringId(1800198);
		PATHFINDER_SAVIOR = new NpcStringId(1800199);
		PATHFINDER_SUPPORTER = new NpcStringId(1800200);
		SOME_KANABIONS_WHO_HAVENT_FULLY_ADJUSTED_YET_TO_THEIR_NEW_PHYSICAL_FORM_ARE_KNOWN_TO_EXHIBIT_SYMPTOMS_OF_AN_EXTREMELY_WEAKENED_BODY_STRUCTURE_SOMETIMES_IF_YOU_ATTACK_THEM_AT_THAT_MOMENT_YOU_WILL_HAVE_GREAT_RESULTS = new NpcStringId(1800201);
		HAVE_YOU_EVER_HEARD_OF_S1_THEY_SAY_ITS_A_GENUINE_S2 = new NpcStringId(1800202);
		THERE_ARE_5_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH = new NpcStringId(1800203);
		THERE_ARE_3_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH = new NpcStringId(1800204);
		THERE_ARE_1_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH = new NpcStringId(1800205);
		THE_MATCH_WILL_BEGIN_SHORTLY = new NpcStringId(1800207);
		OHHOHOH = new NpcStringId(1800209);
		FIRE = new NpcStringId(1800210);
		WATER = new NpcStringId(1800211);
		WIND = new NpcStringId(1800212);
		EARTH = new NpcStringId(1800213);
		ITS_S1 = new NpcStringId(1800214);
		S1_IS_STRONG = new NpcStringId(1800215);
		ITS_ALWAYS_S1 = new NpcStringId(1800216);
		S1_WONT_DO = new NpcStringId(1800217);
		YOU_WILL_BE_CURSED_FOR_SEEKING_THE_TREASURE = new NpcStringId(1800218);
		THE_AIRSHIP_HAS_BEEN_SUMMONED_IT_WILL_AUTOMATICALLY_DEPART_IN_5_MINUTES = new NpcStringId(1800219);
		THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_ADEN_CONTINENT_IN_1_MINUTE = new NpcStringId(1800220);
		THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_ADEN_CONTINENT_HAS_DEPARTED = new NpcStringId(1800221);
		THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_GRACIA_CONTINENT_IN_1_MINUTE = new NpcStringId(1800222);
		THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_GRACIA_CONTINENT_HAS_DEPARTED = new NpcStringId(1800223);
		ANOTHER_AIRSHIP_HAS_BEEN_SUMMONED_TO_THE_WHARF_PLEASE_TRY_AGAIN_LATER = new NpcStringId(1800224);
		HUH_THE_SKY_LOOKS_FUNNY_WHATS_THAT = new NpcStringId(1800225);
		A_POWERFUL_SUBORDINATE_IS_BEING_HELD_BY_THE_BARRIER_ORB_THIS_REACTION_MEANS = new NpcStringId(1800226);
		BE_CAREFUL_SOMETHINGS_COMING = new NpcStringId(1800227);
		YOU_MUST_FIRST_FOUND_A_CLAN_OR_BELONG_TO_ONE = new NpcStringId(1800228);
		THERE_IS_NO_PARTY_CURRENTLY_CHALLENGING_EKIMUS_N_IF_NO_PARTY_ENTERS_WITHIN_S1_SECONDS_THE_ATTACK_ON_THE_HEART_OF_IMMORTALITY_WILL_FAIL = new NpcStringId(1800229);
		EKIMUS_HAS_GAINED_STRENGTH_FROM_A_TUMOR = new NpcStringId(1800230);
		EKIMUS_HAS_BEEN_WEAKENED_BY_LOSING_STRENGTH_FROM_A_TUMOR = new NpcStringId(1800231);
		CMON_CMON_SHOW_YOUR_FACE_YOU_LITTLE_RATS_LET_ME_SEE_WHAT_THE_DOOMED_WEAKLINGS_ARE_SCHEMING = new NpcStringId(1800233);
		IMPRESSIVE_HAHAHA_ITS_SO_MUCH_FUN_BUT_I_NEED_TO_CHILL_A_LITTLE_WHILE_ARGEKUNTE_CLEAR_THE_WAY = new NpcStringId(1800234);
		KYAHAHA_SINCE_THE_TUMOR_HAS_BEEN_RESURRECTED_I_NO_LONGER_NEED_TO_WASTE_MY_TIME_ON_YOU = new NpcStringId(1800235);
		KEU_I_WILL_LEAVE_FOR_NOW_BUT_DONT_THINK_THIS_IS_OVER_THE_SEED_OF_INFINITY_CAN_NEVER_DIE = new NpcStringId(1800236);
		KAHAHAHA_THAT_GUYS_NOTHING_HE_CANT_EVEN_KILL_WITHOUT_MY_PERMISSION_SEE_HERE_ULTIMATE_FORGOTTEN_MAGIC_DEATHLESS_GUARDIAN = new NpcStringId(1800237);
		I_CURSE_THE_DAY_THAT_I_BECAME_YOUR_SLAVE_IN_ORDER_TO_ESCAPE_DEATH_COHEMENES_I_SWEAR_THAT_I_SHALL_SEE_YOU_DIE_WITH_MY_OWN_EYES = new NpcStringId(1800238);
		MY_ENEMY_IS_DYING_AND_MY_BLOOD_IS_BOILING_WHAT_CRUEL_CURSE_IS_THIS = new NpcStringId(1800239);
		HALL_OF_SUFFERING = new NpcStringId(1800240);
		HALL_OF_EROSION = new NpcStringId(1800241);
		HEART_OF_IMMORTALITY = new NpcStringId(1800242);
		ATTACK = new NpcStringId(1800243);
		DEFEND = new NpcStringId(1800244);
		CONGRATULATIONS_YOU_HAVE_SUCCEEDED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE = new NpcStringId(1800245);
		YOU_HAVE_FAILED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE = new NpcStringId(1800246);
		S1S_PARTY_HAS_MOVED_TO_A_DIFFERENT_LOCATION_THROUGH_THE_CRACK_IN_THE_TUMOR = new NpcStringId(1800247);
		S1S_PARTY_HAS_ENTERED_THE_CHAMBER_OF_EKIMUS_THROUGH_THE_CRACK_IN_THE_TUMOR = new NpcStringId(1800248);
		EKIMUS_HAS_SENSED_ABNORMAL_ACTIVITY_NTHE_ADVANCING_PARTY_IS_FORCEFULLY_EXPELLED = new NpcStringId(1800249);
		THERE_ARENT_ENOUGH_ITEMS_IN_ORDER_TO_SUMMON_THE_AIRSHIP_YOU_NEED_5_ENERGY_STAR_STONES = new NpcStringId(1800250);
		THE_SOUL_DEVOURERS_WHO_ARE_GREEDY_TO_EAT_THE_SEEDS_OF_LIFE_THAT_REMAIN_ALIVE_UNTIL_THE_END_HAVE_AWAKENED = new NpcStringId(1800251);
		THE_FIRST_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED = new NpcStringId(1800252);
		THE_SECOND_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED = new NpcStringId(1800253);
		CLINGING_ON_WONT_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_BLADE_TURN = new NpcStringId(1800254);
		EVEN_SPECIAL_SAUCE_CANT_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_FORCE_SHIELD = new NpcStringId(1800255);
		YOU_LITTLE_DOOMED_MAGGOTS_EVEN_IF_YOU_KEEP_SWARMING_THE_POWER_OF_IMMORTALITY_WILL_ONLY_GROW_STRONGER = new NpcStringId(1800256);
		THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_AWARDED_YOUR_CLAN_CAN_NOW_SUMMON_AN_AIRSHIP = new NpcStringId(1800257);
		THE_GRACIA_TREASURE_BOX_HAS_APPEARED = new NpcStringId(1800258);
		THE_GRACIA_TREASURE_BOX_WILL_SOON_DISAPPEAR = new NpcStringId(1800259);
		YOU_HAVE_BEEN_CURSED_BY_THE_TUMOR_AND_HAVE_INCURRED_S1_DAMAGE = new NpcStringId(1800260);
		I_SHALL_ACCEPT_YOUR_CHALLENGE_S1_COME_AND_DIE_IN_THE_ARMS_OF_IMMORTALITY = new NpcStringId(1800261);
		YOU_WILL_PARTICIPATE_IN_S1_S2_SHORTLY_BE_PREPARED_FOR_ANYTHING = new NpcStringId(1800262);
		YOU_CAN_HEAR_THE_UNDEAD_OF_EKIMUS_RUSHING_TOWARD_YOU_S1_S2_IT_HAS_NOW_BEGUN = new NpcStringId(1800263);
		YOU_CAN_FEEL_THE_SURGING_ENERGY_OF_DEATH_FROM_THE_TUMOR = new NpcStringId(1800264);
		THE_AREA_NEAR_THE_TUMOR_IS_FULL_OF_OMINOUS_ENERGY = new NpcStringId(1800265);
		YOU_TRIED_TO_DROP_US_HOW_STUPID = new NpcStringId(1800266);
		WE_ARE_BLOOD_BRETHREN_I_CANT_FALL_SO_EASILY_HERE_AND_LEAVE_MY_BROTHER_BEHIND = new NpcStringId(1800267);
		YOU_WERE_ALWAYS_WHAT_I_ASPIRED_TO_BE_DO_YOU_THINK_I_WOULD_FALL_SO_EASILY_HERE_WHEN_I_HAVE_A_BROTHER_LIKE_THAT = new NpcStringId(1800268);
		WITH_ALL_CONNECTIONS_TO_THE_TUMOR_SEVERED_EKIMUS_HAS_LOST_ITS_POWER_TO_CONTROL_THE_FERAL_HOUND = new NpcStringId(1800269);
		WITH_THE_CONNECTION_TO_THE_TUMOR_RESTORED_EKIMUS_HAS_REGAINED_CONTROL_OVER_THE_FERAL_HOUND = new NpcStringId(1800270);
		WOOOONG = new NpcStringId(1800271);
		WOONG_WOONG_WOO = new NpcStringId(1800272);
		THE_ENEMIES_HAVE_ATTACKED_EVERYONE_COME_OUT_AND_FIGHT_URGH = new NpcStringId(1800273);
		THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NIN_ORDER_TO_DRAW_OUT_THE_COWARDLY_COHEMENES_YOU_MUST_DESTROY_ALL_THE_TUMORS = new NpcStringId(1800274);
		THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NTHE_RESTRENGTHENED_COHEMENES_HAS_FLED_DEEPER_INSIDE_THE_SEED = new NpcStringId(1800275);
		THE_AWARDED_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_RECEIVED = new NpcStringId(1800276);
		YOU_DO_NOT_CURRENTLY_HAVE_AN_AIRSHIP_SUMMON_LICENSE_YOU_CAN_EARN_YOUR_AIRSHIP_SUMMON_LICENSE_THROUGH_ENGINEER_LEKON = new NpcStringId(1800277);
		THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_AWARDED = new NpcStringId(1800278);
		IF_YOU_HAVE_ITEMS_PLEASE_GIVE_THEM_TO_ME = new NpcStringId(1800279);
		MY_STOMACH_IS_EMPTY = new NpcStringId(1800280);
		IM_HUNGRY_IM_HUNGRY = new NpcStringId(1800281);
		IM_STILL_NOT_FULL = new NpcStringId(1800282);
		IM_STILL_HUNGRY = new NpcStringId(1800283);
		I_FEEL_A_LITTLE_WOOZY = new NpcStringId(1800284);
		GIVE_ME_SOMETHING_TO_EAT = new NpcStringId(1800285);
		NOW_ITS_TIME_TO_EAT = new NpcStringId(1800286);
		I_ALSO_NEED_A_DESSERT = new NpcStringId(1800287);
		IM_FULL_NOW_I_DONT_WANT_TO_EAT_ANYMORE = new NpcStringId(1800289);
		I_HAVENT_EATEN_ANYTHING_IM_SO_WEAK = new NpcStringId(1800290);
		YUM_YUM_YUM_YUM = new NpcStringId(1800291);
		YOUVE_SUSTAINED_S1_DAMAGE_AS_TUMORS_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD = new NpcStringId(1800292);
		YOUVE_SUSTAINED_S1_DAMAGE_AS_SOUL_COFFINS_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD = new NpcStringId(1800293);
		OBELISK_HAS_COLLAPSED_DONT_LET_THE_ENEMIES_JUMP_AROUND_WILDLY_ANYMORE = new NpcStringId(1800295);
		ENEMIES_ARE_TRYING_TO_DESTROY_THE_FORTRESS_EVERYONE_DEFEND_THE_FORTRESS = new NpcStringId(1800296);
		COME_OUT_WARRIORS_PROTECT_SEED_OF_DESTRUCTION = new NpcStringId(1800297);
		THE_UNDEAD_OF_EKIMUS_IS_ATTACKING_SEED_OF_LIFE_DEFENDING_HALL_OF_EROSION_WILL_FAIL_EVEN_IF_ONE_SEED_OF_LIFE_IS_DESTROYED = new NpcStringId(1800298);
		ALL_THE_TUMORS_INSIDE_S1_HAVE_BEEN_DESTROYED_DRIVEN_INTO_A_CORNER_COHEMENES_APPEARS_CLOSE_BY = new NpcStringId(1800299);
		THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_NEARBY_UNDEAD_THAT_WERE_ATTACKING_SEED_OF_LIFE_START_LOSING_THEIR_ENERGY_AND_RUN_AWAY = new NpcStringId(1800300);
		THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NRECOVERED_NEARBY_UNDEAD_ARE_SWARMING_TOWARD_SEED_OF_LIFE = new NpcStringId(1800301);
		THE_TUMOR_INSIDE_S1_THAT_HAS_PROVIDED_ENERGY_N_TO_EKIMUS_IS_DESTROYED = new NpcStringId(1800302);
		THE_TUMOR_INSIDE_S1_HAS_BEEN_COMPLETELY_RESURRECTED_N_AND_STARTED_TO_ENERGIZE_EKIMUS_AGAIN = new NpcStringId(1800303);
		THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_SPEED_THAT_EKIMUS_CALLS_OUT_HIS_PREY_HAS_SLOWED_DOWN = new NpcStringId(1800304);
		THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NEKIMUS_STARTED_TO_REGAIN_HIS_ENERGY_AND_IS_DESPERATELY_LOOKING_FOR_HIS_PREY = new NpcStringId(1800305);
		BRING_MORE_MORE_SOULS = new NpcStringId(1800306);
		THE_HALL_OF_EROSION_ATTACK_WILL_FAIL_UNLESS_YOU_MAKE_A_QUICK_ATTACK_AGAINST_THE_TUMOR = new NpcStringId(1800307);
		AS_THE_TUMOR_WAS_NOT_THREATENED_COHEMENES_COMPLETELY_RAN_AWAY_TO_DEEP_INSIDE_THE_SEED = new NpcStringId(1800308);
		YOUR_GOAL_WILL_BE_OBSTRUCTED_OR_BE_UNDER_A_RESTRAINT = new NpcStringId(1800309);
		YOU_MAY_FACE_AN_UNFORESEEN_PROBLEM_ON_YOUR_COURSE_TOWARD_THE_GOAL = new NpcStringId(1800310);
		YOU_MAY_FEEL_NERVOUS_AND_ANXIOUS_BECAUSE_OF_UNFAVORABLE_SITUATIONS = new NpcStringId(1800311);
		BE_WARNED_WHEN_THE_SITUATION_IS_DIFFICULT_BECAUSE_YOU_MAY_LOSE_YOUR_JUDGMENT_AND_MAKE_AN_IRRATIONAL_MISTAKE = new NpcStringId(1800312);
		YOU_MAY_MEET_A_TRUSTWORTHY_PERSON_OR_A_GOOD_OPPORTUNITY = new NpcStringId(1800313);
		YOUR_DOWNWARD_LIFE_STARTS_TAKING_AN_UPTURN = new NpcStringId(1800314);
		YOU_WILL_ATTRACT_ATTENTION_FROM_PEOPLE_WITH_YOUR_POPULARITY = new NpcStringId(1800315);
		YOUR_STAR_OF_FORTUNE_SAYS_THERELL_BE_FISH_SNAPPING_AT_YOUR_BAIT = new NpcStringId(1800316);
		THERE_MAY_BE_A_CONFLICT_BORN_OF_YOUR_DOGMATIC_PROCEDURES = new NpcStringId(1800317);
		YOUR_WISDOM_AND_CREATIVITY_MAY_LEAD_YOU_TO_SUCCESS_WITH_YOUR_GOAL = new NpcStringId(1800318);
		YOU_MAY_ACCOMPLISH_YOUR_GOALS_IF_YOU_DILIGENTLY_PURSUE_THEM_WITHOUT_GIVING_UP = new NpcStringId(1800319);
		YOU_MAY_GET_HELP_IF_YOU_GO_THROUGH_THE_FRONT_DOOR_WITHOUT_SEEKING_TRICKS_OR_MANEUVERS = new NpcStringId(1800320);
		A_GOOD_RESULT_IS_ON_THE_WAY_IF_YOU_SET_A_GOAL_AND_BRAVELY_PROCEED_TOWARD_IT = new NpcStringId(1800321);
		EVERYTHING_WILL_BE_SMOOTHLY_MANAGED_NO_MATTER_HOW_DIFFICULT = new NpcStringId(1800322);
		BE_FIRM_AND_CAREFULLY_SCRUTINIZE_CIRCUMSTANCES_EVEN_WHEN_THINGS_ARE_DIFFICULT = new NpcStringId(1800323);
		ALWAYS_THINK_OVER_TO_FIND_NEGLECTED_PROBLEMS_YOU_HAVENT_TAKEN_CARE_OF_YET = new NpcStringId(1800324);
		FINANCIAL_FORTUNE_WILL_GREET_YOUR_POOR_LIFE = new NpcStringId(1800325);
		YOU_MAY_ACQUIRE_WEALTH_AND_FAME_AFTER_UNLUCKY_CIRCUMSTANCES = new NpcStringId(1800326);
		THE_DIFFICULT_SITUATIONS_WILL_TURN_TO_HOPE_WITH_UNFORESEEN_HELP = new NpcStringId(1800327);
		A_GREAT_TASK_WILL_RESULT_IN_SUCCESS = new NpcStringId(1800328);
		YOU_MAY_ENCOUNTER_A_PRECIOUS_PERSON_WHO_WILL_LIFT_YOUR_LONELINESS_AND_DISCORD = new NpcStringId(1800329);
		PEOPLE_AROUND_YOU_WILL_ENCOURAGE_YOUR_EVERY_TASK_IN_THE_FUTURE = new NpcStringId(1800330);
		EVERYTHING_WILL_BE_SMOOTHLY_MANAGED = new NpcStringId(1800331);
		YOU_WILL_MEET_A_PERSON_WHO_CAN_CHERISH_YOUR_VALUES_IF_YOU_MAINTAIN_GOOD_TIES_WITH_PEOPLE = new NpcStringId(1800332);
		MAINTAIN_COOPERATIVE_ATTITUDE_SINCE_YOU_WILL_MEET_SOMEONE_TO_BE_OF_HELP = new NpcStringId(1800333);
		KEEP_YOUR_MODERATION_AND_EGO_IN_CHECK_EVEN_IN_SUCCESSFUL_PHASES_OF_YOUR_LIFE = new NpcStringId(1800334);
		WHEN_IT_COMES_TO_WORK_LIFESTYLE_AND_RELATIONSHIPS_YOULL_BE_BETTER_OFF_TO_GO_BY_THE_TEXT_RATHER_THAN_TRICKS = new NpcStringId(1800335);
		YOUR_TASK_WILL_RECEIVE_SUBSTANTIAL_SUPPORT_SINCE_THE_SURROUNDINGS_WILL_FULLY_DEVELOP = new NpcStringId(1800336);
		YOUR_STAR_OF_FORTUNE_INDICATE_A_SUCCESS_WITH_MENTAL_AND_MATERIAL_ASSISTANCE = new NpcStringId(1800337);
		YOU_WILL_ENJOY_POPULARITY_WITH_YOUR_CREATIVE_TALENTS_AND_CLEVER_ACTS = new NpcStringId(1800338);
		PEOPLE_WILL_LINE_UP_TO_BE_OF_ASSISTANCE_TO_YOU = new NpcStringId(1800339);
		YOU_MAY_MEET_SOMEONE_TO_SHARE_YOUR_JOURNEY = new NpcStringId(1800340);
		YOU_MAY_ACHIEVE_CONNECTIONS_IN_MANY_FIELDS = new NpcStringId(1800341);
		AN_ATTITUDE_THAT_CONTINUALLY_STUDIES_AND_EXPLORES_IS_NEEDED_AND_ALWAYS_BE_SINCERE = new NpcStringId(1800342);
		ITS_AN_IMAGE_OF_A_BUTTERFLY_ON_A_FLOWER_IN_WARM_SPRING_AIR = new NpcStringId(1800343);
		YOUR_GOALS_WILL_MOVE_SMOOTHLY_WITH_PEACE_AND_HAPPINESS_IN_YOUR_LIFE = new NpcStringId(1800344);
		LOVE_MAY_SPROUT_ITS_LEAVES_WHEN_YOU_TREAT_THOSE_AROUND_YOU_WITH_CARE = new NpcStringId(1800345);
		YOU_MAY_CLIMB_INTO_A_HIGHER_POSITION_WITH_OTHERS_TRUST_IF_YOU_FAITHFULLY_CARRY_OUT_YOUR_DUTIES = new NpcStringId(1800346);
		EVERYTHING_CAN_FALL_APART_IF_YOU_GREEDILY_AIM_BY_PURE_LUCK = new NpcStringId(1800347);
		DO_NOT_UNDERESTIMATE_THE_IMPORTANCE_OF_MEETING_PEOPLE = new NpcStringId(1800348);
		AN_ARROW_WILL_COALESCE_INTO_THE_BOW = new NpcStringId(1800349);
		A_BONY_LIMB_OF_A_TREE_MAY_BEAR_ITS_FRUIT = new NpcStringId(1800350);
		YOU_WILL_BE_REWARDED_FOR_YOUR_EFFORTS_AND_ACCOMPLISHMENTS = new NpcStringId(1800351);
		NO_MATTER_WHERE_IT_LIES_YOUR_FAITHFUL_DRIVE_LEADS_YOU_TO_SUCCESS = new NpcStringId(1800352);
		PEOPLE_WILL_BE_ATTRACTED_TO_YOUR_LOYALTIES = new NpcStringId(1800353);
		YOU_MAY_TRUST_YOURSELF_RATHER_THAN_OTHERS_TALKS = new NpcStringId(1800354);
		CREATIVE_THINKING_AWAY_FROM_THE_OLD_VIEWPOINT_MAY_HELP_YOU = new NpcStringId(1800355);
		PATIENCE_WITHOUT_BEING_IMPETUOUS_OF_THE_RESULTS_WILL_ONLY_BEAR_A_POSITIVE_OUTCOME = new NpcStringId(1800356);
		THE_DEAD_WILL_COME_ALIVE = new NpcStringId(1800357);
		THERE_WILL_BE_A_SHOCKING_INCIDENT = new NpcStringId(1800358);
		YOU_WILL_ENJOY_A_HUGE_SUCCESS_AFTER_UNFORESEEN_LUCK_COMES_BEFORE_YOU = new NpcStringId(1800359);
		DO_NOT_GIVE_UP_SINCE_THERE_MAY_BE_A_MIRACULOUS_RESCUE_FROM_THE_COURSE_OF_DESPAIR = new NpcStringId(1800360);
		AN_ATTITUDE_TO_TRY_ONES_BEST_TO_PURSUE_THE_GOAL_IS_NEEDED = new NpcStringId(1800361);
		YOU_MAY_GET_A_SHOT_IN_THE_ARM_IN_YOUR_LIFE_AFTER_MEETING_A_GOOD_PERSON = new NpcStringId(1800362);
		YOU_MAY_GET_A_BIG_HELP_IN_THE_COURSE_OF_YOUR_LIFE = new NpcStringId(1800363);
		A_RARE_OPPORTUNITY_WILL_COME_TO_YOU_SO_YOU_MAY_PROSPER = new NpcStringId(1800364);
		A_HUNGRY_FALCON_WILL_HAVE_MEAT = new NpcStringId(1800365);
		A_HOUSEHOLD_IN_NEED_WILL_ACQUIRE_A_FORTUNE_AND_MEAT = new NpcStringId(1800366);
		A_HARD_SITUATION_WILL_COME_TO_ITS_END_WITH_MATERIALISTIC_AND_MENTAL_HELP_FROM_OTHERS = new NpcStringId(1800367);
		IF_YOU_SET_A_FIRM_GOAL_WITHOUT_SURRENDER_THERE_WILL_BE_A_PERSON_WHO_CAN_OFFER_HELP_AND_CARE = new NpcStringId(1800368);
		YOULL_GAIN_OTHERS_TRUST_WHEN_YOU_MAINTAIN_A_SINCERE_AND_HONEST_ATTITUDE = new NpcStringId(1800369);
		BE_INDEPENDENT_AT_ALL_TIMES = new NpcStringId(1800370);
		ITS_A_WAGON_WITH_NO_WHEELS = new NpcStringId(1800371);
		YOUVE_SET_A_GOAL_BUT_THERE_MAY_BE_OBSTACLES_IN_REALITY = new NpcStringId(1800372);
		YOURE_RUNNING_TOWARD_THE_GOAL_BUT_THERE_WONT_BE_AS_MANY_OUTCOMES_AS_YOU_THOUGHT = new NpcStringId(1800373);
		THERE_ARE_MANY_THINGS_TO_CONSIDER_AFTER_ENCOUNTERING_HINDRANCES = new NpcStringId(1800374);
		A_RECKLESS_MOVE_MAY_BRING_A_FAILURE = new NpcStringId(1800375);
		YOU_MAY_LOSE_PEOPLES_TRUST_IF_YOU_LACK_PRUDENCE_AT_ALL_TIMES = new NpcStringId(1800376);
		YOU_MAY_NEED_TO_REFLECT_ON_YOURSELF_WITH_DELIBERATION_AND_WAIT_FOR_AN_OPPORTUNITY = new NpcStringId(1800377);
		A_POOR_SCHOLAR_RECEIVES_A_STIPEND = new NpcStringId(1800378);
		A_SCHOLAR_GETS_A_PASS_TOWARD_FAME_AND_FORTUNE = new NpcStringId(1800379);
		YOUR_AMBITION_AND_DREAM_WILL_COME_TRUE = new NpcStringId(1800380);
		COMPLICATED_PROBLEMS_AROUND_YOU_MAY_START_BEING_SOLVED_ONE_AFTER_ANOTHER = new NpcStringId(1800381);
		YOU_WILL_HAVE_A_GOOD_RESULT_IF_YOU_DILIGENTLY_PURSUE_ONE_GOAL_WITHOUT_BEING_DRAGGED_FROM_YOUR_PAST = new NpcStringId(1800382);
		YOU_MAY_NEED_TO_RID_YOURSELF_OF_OLD_AND_WORN_HABITS = new NpcStringId(1800383);
		BE_RESPONSIBLE_WITH_YOUR_TASKS_BUT_DO_NOT_HESITATE_TO_ASK_FOR_COLLEAGUES_HELP = new NpcStringId(1800384);
		FISH_TRANSFORMS_INTO_A_DRAGON = new NpcStringId(1800385);
		YOUR_DREAM_MAY_COME_TRUE_AND_FAME_AND_FORTUNE_WILL_COME_TO_YOU = new NpcStringId(1800386);
		WHAT_YOUVE_PLANED_WILL_BE_ACCOMPLISHED = new NpcStringId(1800387);
		YOU_MAY_ACQUIRE_MONEY_OR_A_NEW_OPPORTUNITY_FROM_A_PLACE_YOU_WOULDNT_HAVE_THOUGHT_OF = new NpcStringId(1800388);
		THERE_WILL_BE_MANY_OFFERS_TO_YOU_YOU_MAY_THINK_THEM_OVER_CAREFULLY = new NpcStringId(1800389);
		IT_MAY_BE_A_GOOD_IDEA_NOT_TO_BECOME_INVOLVED_IN_OTHERS_BUSINESS = new NpcStringId(1800390);
		EVERYTHING_WILL_GO_SMOOTHLY_BUT_BE_AWARE_OF_DANGER_FROM_BEING_CARELESS_AND_REMISS = new NpcStringId(1800391);
		IF_YOU_SINCERELY_CARE_FOR_SOMEONE_YOU_LOVE_A_BIG_REWARD_WILL_RETURN_TO_YOU = new NpcStringId(1800392);
		A_REMEDY_IS_ON_ITS_WAY_FOR_A_SERIOUS_ILLNESS = new NpcStringId(1800393);
		YOU_MAY_ACQUIRE_A_PRECIOUS_MEDICINE_TO_RECOVER_AFTER_SUFFERING_A_DISEASE_OF_A_SERIOUS_NATURE = new NpcStringId(1800394);
		YOU_MAY_REALIZE_YOUR_DREAM_BY_MEETING_A_MAN_OF_DISTINCTION_AT_A_DIFFICULT_TIME = new NpcStringId(1800395);
		YOU_MAY_SUFFER_ONE_OR_TWO_HARDSHIPS_ON_YOUR_JOURNEY = new NpcStringId(1800396);
		IF_YOU_KEEP_SMILING_WITHOUT_DESPAIR_PEOPLE_WILL_COME_TO_TRUST_YOU_AND_OFFER_HELP = new NpcStringId(1800397);
		SEEK_STABILITY_RATHER_THAN_DYNAMICS_IN_YOUR_LIFE = new NpcStringId(1800398);
		ITS_A_GOOD_IDEA_TO_BE_CAREFUL_AND_SECURE_AT_ALL_TIMES = new NpcStringId(1800399);
		YOU_CANT_PERFORM_THE_JOB_WITH_BOUND_HANDS = new NpcStringId(1800400);
		YOU_MAY_LOSE_YOUR_DRIVE_AND_FEEL_LOST = new NpcStringId(1800401);
		YOU_MAY_BE_UNABLE_TO_CONCENTRATE_WITH_SO_MANY_PROBLEMS_OCCURRING = new NpcStringId(1800402);
		YOUR_ACHIEVEMENT_UNFAIRLY_MAY_GO_SOMEWHERE_ELSE = new NpcStringId(1800403);
		DO_NOT_START_A_TASK_THATS_NOT_CLEAR_TO_YOU = new NpcStringId(1800404);
		YOU_WILL_NEED_TO_BE_PREPARED_FOR_ALL_EVENTS = new NpcStringId(1800405);
		SOMEONE_WILL_ACKNOWLEDGE_YOU_IF_YOU_RELENTLESSLY_KEEP_TRYING_AND_DO_NOT_GIVE_UP_WHEN_FACING_HARDSHIPS = new NpcStringId(1800406);
		YOU_MAY_PERFECT_YOURSELF_LIKE_A_DRAGONS_HORN_DECORATES_THE_DRAGON = new NpcStringId(1800407);
		YOUR_TRUE_VALUE_STARTS_TO_SHINE = new NpcStringId(1800408);
		YOUR_STEADY_PURSUIT_OF_NEW_INFORMATION_AND_STAYING_AHEAD_OF_OTHERS_WILL_RAISE_YOUR_VALUE = new NpcStringId(1800409);
		MAINTAINING_CONFIDENCE_WITH_WORK_OR_RELATIONSHIPS_MAY_BRING_GOOD_RESULTS = new NpcStringId(1800410);
		LEARN_TO_WORK_WITH_OTHERS_SINCE_OVERCONFIDENCE_WILL_BEAR_WRATH = new NpcStringId(1800411);
		THE_DRAGON_NOW_ACQUIRES_AN_EAGLES_WINGS = new NpcStringId(1800412);
		AS_THE_DRAGON_FLIES_HIGH_IN_THE_SKY_YOUR_GOALS_AND_DREAMS_MAY_COME_TRUE = new NpcStringId(1800413);
		LUCK_ENTERS_INTO_YOUR_WORK_HOBBY_FAMILY_AND_LOVE = new NpcStringId(1800414);
		WHATEVER_YOU_DO_IT_WILL_ACCOMPANY_WINNING = new NpcStringId(1800415);
		ITS_AS_GOOD_AS_IT_GETS_WITH_UNFORESEEN_FORTUNE_ROLLING_YOUR_WAY = new NpcStringId(1800416);
		A_GREEDY_ACT_WITH_NO_PRUDENCE_WILL_BRING_A_SURPRISE_AT_THE_END = new NpcStringId(1800417);
		THINK_CAREFULLY_AND_ACT_WITH_CAUTION_AT_ALL_TIMES = new NpcStringId(1800418);
		IF_A_TREE_DOESNT_HAVE_ITS_ROOTS_THERE_WILL_BE_NO_FRUIT = new NpcStringId(1800419);
		HARD_WORK_DOESNT_BEAR_FRUIT = new NpcStringId(1800420);
		FINANCIAL_DIFFICULTIES_MAY_BRING_AN_ORDEAL = new NpcStringId(1800421);
		WHAT_USED_TO_BE_WELL_MANAGED_MAY_STUMBLE_ONE_AFTER_ANOTHER = new NpcStringId(1800422);
		A_FEELING_OF_FRUSTRATION_MAY_FOLLOW_DISAPPOINTMENT = new NpcStringId(1800423);
		BE_CAUTIONED_AS_UNHARNESSED_BEHAVIOR_AT_DIFFICULT_TIMES_CAN_RUIN_RELATIONSHIPS = new NpcStringId(1800424);
		CURTAIL_GREED_AND_BE_GRATEFUL_FOR_SMALL_RETURNS_AS_MODESTY_IS_NEEDED = new NpcStringId(1800425);
		THE_PERSON_THAT_CAME_UNDER_YOUR_WINGS_WILL_LEAVE = new NpcStringId(1800426);
		YOUR_WORK_AND_RELATIONSHIP_WITH_COLLEAGUES_WILL_BE_WELL_MANAGED_IF_YOU_MAINTAIN_YOUR_DEVOTION = new NpcStringId(1800427);
		CALCULATING_YOUR_PROFIT_IN_RELATIONSHIPS_WITHOUT_DISPLAYING_ANY_COURTEOUS_MANNERS_WILL_BRING_MALICIOUS_GOSSIP_AND_RUIN_YOUR_VALUE = new NpcStringId(1800428);
		CONSIDER_OTHERS_SITUATIONS_AND_TREAT_THEM_SINCERELY_AT_ALL_TIMES = new NpcStringId(1800429);
		DO_NOT_LOOSEN_UP_WITH_YOUR_PRECAUTIONS = new NpcStringId(1800430);
		REFLECT_OTHERS_OPINIONS_AS_A_MISTAKE_ALWAYS_LIES_AHEAD_OF_AN_ARBITRARY_DECISION = new NpcStringId(1800431);
		A_BLIND_MAN_GOES_RIGHT_THROUGH_THE_DOOR = new NpcStringId(1800432);
		A_HEART_FALLS_INTO_HOPELESSNESS_AS_THINGS_ARE_IN_DISARRAY = new NpcStringId(1800433);
		HOPELESSNESS_MAY_FILL_YOUR_HEART_AS_YOUR_WORK_FALLS_INTO_A_MAZE = new NpcStringId(1800434);
		DIFFICULTIES_LIE_AHEAD_OF_AN_UNFORESEEN_PROBLEM_EVEN_WITH_YOUR_HARD_WORK = new NpcStringId(1800435);
		THERE_MAY_BE_MORE_OCCASIONS_YOU_WILL_WANT_TO_ASK_FAVORS_FROM_OTHERS_AS_YOU_LOSE_CONFIDENCE_IN_YOU = new NpcStringId(1800436);
		BE_BRAVE_AND_AMBITIOUS_AS_NO_BIRD_CAN_FLY_INTO_THE_SKY_BY_STAYING_IN_THEIR_NEST = new NpcStringId(1800437);
		ITS_A_GOOD_IDEA_NOT_TO_START_AN_UNCLEAR_TASK_AND_ALWAYS_LOOK_FOR_SOMEONE_YOU_CAN_TRUST_AND_RELY_UPON = new NpcStringId(1800438);
		HUNTING_WONT_BE_SUCCESSFUL_AS_THE_FALCON_LACKS_ITS_CLAWS = new NpcStringId(1800439);
		A_PREPARED_PLAN_WONT_MOVE_SMOOTHLY = new NpcStringId(1800440);
		AN_EASY_TASK_MAY_FAIL_IF_ONE_IS_CONSUMED_BY_GREED_AND_OVERCONFIDENCE = new NpcStringId(1800441);
		IMPATIENCE_MAY_LIE_AHEAD_AS_THE_SITUATION_IS_UNFAVORABLE = new NpcStringId(1800442);
		THOUGHTFUL_FORESIGHT_IS_NEEDED_BEFORE_A_DISASTER_MAY_FALL_UPON_YOU = new NpcStringId(1800443);
		REFRAIN_FROM_DICTATORIAL_ACTS_AS_CARING_FOR_OTHERS_AROUND_YOU_WITH_DIGNITY_IS_MUCH_NEEDED = new NpcStringId(1800444);
		THINGS_ARE_MESSY_WITH_NO_GOOD_SIGN = new NpcStringId(1800445);
		YOU_MAY_FALL_INTO_A_VEXING_SITUATION_AS_BAD_CIRCUMSTANCES_WILL_ARISE = new NpcStringId(1800446);
		RELATIONSHIPS_WITH_PEOPLE_MAY_BE_CONTRARY_TO_YOUR_EXPECTATIONS = new NpcStringId(1800447);
		DO_NOT_SEEK_A_QUICK_FIX_AS_THE_PROBLEM_NEEDS_A_FUNDAMENTAL_RESOLUTION = new NpcStringId(1800448);
		SEEK_PEACE_IN_YOUR_HEART_AS_VULGAR_DISPLAY_OF_YOUR_EMOTIONS_MAY_HARM_YOU = new NpcStringId(1800449);
		INFORMATION_FOR_SUCCESS_MAY_COME_FROM_THE_CONVERSATIONS_WITH_PEOPLE_AROUND_YOU = new NpcStringId(1800450);
		BE_CONFIDENT_AND_ACT_RELIANTLY_AT_ALL_TIMES = new NpcStringId(1800451);
		A_CHILD_GETS_A_TREASURE = new NpcStringId(1800452);
		GOOD_FORTUNE_AND_OPPORTUNITY_MAY_LIE_AHEAD_AS_IF_ONES_BORN_IN_A_GOLDEN_SPOON = new NpcStringId(1800453);
		YOUR_LIFE_FLOWS_AS_IF_ITS_ON_A_SILK_SURFACE_AND_UNEXPECTED_FORTUNE_AND_SUCCESS_MAY_COME_TO_YOU = new NpcStringId(1800454);
		TEMPORARY_LUCK_MAY_COME_TO_YOU_WITH_NO_EFFORT = new NpcStringId(1800455);
		PLAN_AHEAD_WITH_PATIENCE_BUT_EXECUTE_WITH_SWIFTNESS = new NpcStringId(1800456);
		THE_ABILITIES_TO_AMEND_FORESEE_AND_ANALYZE_MAY_RAISE_YOUR_VALUE = new NpcStringId(1800457);
		BIGGER_MISTAKES_WILL_BE_ON_THE_ROAD_IF_YOU_FAIL_TO_CORRECT_A_SMALL_MISTAKE = new NpcStringId(1800458);
		DONT_BE_EVASIVE_TO_ACCEPT_NEW_FINDINGS_OR_EXPERIENCES = new NpcStringId(1800459);
		DONT_BE_IRRITATED_AS_THE_SITUATIONS_DONT_MOVE_AS_PLANNED = new NpcStringId(1800460);
		BE_WARNED_AS_YOU_MAY_BE_OVERWHELMED_BY_SURROUNDINGS_IF_YOU_LACK_A_CLEAR_OPINION = new NpcStringId(1800461);
		YOU_MAY_LIVE_AN_AFFLUENT_LIFE_EVEN_WITHOUT_POSSESSIONS = new NpcStringId(1800462);
		YOU_WILL_GAIN_POPULARITY_AS_YOU_HELP_PEOPLE_WITH_MONEY_YOU_EARNESTLY_EARNED = new NpcStringId(1800463);
		YOUR_HEART_AND_BODY_MAY_BE_IN_HEALTH = new NpcStringId(1800464);
		BE_WARNED_AS_YOU_MAY_BE_DRAGGED_TO_AN_UNWANTED_DIRECTION_IF_NOT_CAUTIOUS = new NpcStringId(1800465);
		YOU_MAY_MEET_MANY_NEW_PEOPLE_BUT_IT_WILL_BE_DIFFICULT_TO_FIND_A_PERFECT_PERSON_WHO_WINS_YOUR_HEART = new NpcStringId(1800466);
		THERE_MAY_BE_AN_OCCASION_WHERE_YOU_ARE_CONSOLED_BY_PEOPLE = new NpcStringId(1800467);
		IT_MAY_NOT_BE_A_GOOD_TIME_FOR_A_CHANGE_EVEN_IF_THERES_TEDIUM_IN_DAILY_LIFE = new NpcStringId(1800468);
		THE_MONEY_YOU_SPEND_FOR_YOURSELF_MAY_ACT_AS_AN_INVESTMENT_AND_BRING_YOU_A_RETURN = new NpcStringId(1800469);
		THE_MONEY_YOU_SPEND_FOR_OTHERS_WILL_BE_WASTED_SO_BE_CAUTIOUS = new NpcStringId(1800470);
		BE_WARNED_SO_AS_NOT_TO_HAVE_UNNECESSARY_EXPENSES = new NpcStringId(1800471);
		YOUR_STAR_INDICATED_SUCH_GOOD_LUCK_PARTICIPATE_IN_BONUS_GIVEAWAYS_OR_EVENTS = new NpcStringId(1800472);
		YOU_MAY_GRAB_UNEXPECTED_LUCK = new NpcStringId(1800473);
		THE_PERSON_IN_YOUR_HEART_MAY_NATURALLY_COME_TO_YOU = new NpcStringId(1800474);
		THERE_WILL_BE_A_GOOD_RESULT_IF_YOU_KEEP_YOUR_OWN_PACE_REGARDLESS_OF_OTHERS_JUDGMENT = new NpcStringId(1800475);
		BE_WARNED_AS_UNEXPECTED_LUCK_MAY_BE_WASTED_WITH_YOUR_RECKLESS_COMMENTS = new NpcStringId(1800476);
		OVERCONFIDENCE_WILL_CONVINCE_YOU_TO_CARRY_A_TASK_ABOVE_YOUR_REACH_AND_THERE_WILL_BE_CONSEQUENCES = new NpcStringId(1800477);
		MOMENTARILY_DELAY_AN_IMPORTANT_DECISION = new NpcStringId(1800478);
		TROUBLE_SPOTS_LIE_AHEAD_WHEN_TALKING_TO_SUPERIORS_OR_PEOPLE_CLOSE_TO_YOU = new NpcStringId(1800479);
		BE_WARNED_AS_YOUR_WORDS_CAN_HURT_OTHERS_OR_OTHERS_WORDS_CAN_HURT_YOU = new NpcStringId(1800480);
		MAKE_A_LOUD_BOAST_AND_YOU_MAY_HAVE_TO_PAY_TO_COVER_UNNECESSARY_EXPENSES = new NpcStringId(1800481);
		SKILLFUL_EVASION_IS_NEEDED_WHEN_DEALING_WITH_PEOPLE_WHO_PICK_FIGHTS_AS_A_DISASTER_MAY_ARISE_FROM_IT = new NpcStringId(1800482);
		KEEP_A_LOW_PROFILE_AS_TOO_STRONG_AN_OPINION_WILL_ATTRACT_ADVERSE_REACTIONS = new NpcStringId(1800483);
		DO_NOT_UNNECESSARILY_PROVOKE_MISUNDERSTANDING_AS_YOU_MAY_BE_INVOLVED_IN_MALICIOUS_GOSSIP = new NpcStringId(1800484);
		CHECK_YOUR_BELONGINGS_AS_YOU_MAY_LOSE_WHAT_YOU_POSSESS = new NpcStringId(1800485);
		BE_FLEXIBLE_ENOUGH_TO_PLAY_UP_TO_OTHERS = new NpcStringId(1800486);
		PAY_SPECIAL_ATTENTION_WHEN_MEETING_OR_TALKING_TO_PEOPLE_AS_RELATIONSHIPS_MAY_GO_AMISS = new NpcStringId(1800487);
		WHEN_THE_IMPORTANT_MOMENT_ARRIVES_DECIDE_UPON_WHAT_YOU_TRULY_WANT_WITHOUT_MEASURING_OTHERS_JUDGMENT = new NpcStringId(1800488);
		LUCK_WILL_ALWAYS_FOLLOW_YOU_IF_YOU_TRAVEL_AND_READ_MANY_BOOKS = new NpcStringId(1800489);
		HEAD_TO_A_PLACE_THAT_NEEDS_YOUR_ADVICE_AS_GOOD_IDEAS_AND_WISDOM_WILL_FLOURISH = new NpcStringId(1800490);
		SOMEONES_LIFE_MAY_CHANGE_UPON_YOUR_ADVICE = new NpcStringId(1800491);
		ITS_A_PROPER_TIME_TO_PLAN_FOR_THE_FUTURE_RATHER_THAN_A_SHORT_TERM_PLAN = new NpcStringId(1800492);
		MANY_THOUGHTFUL_PLANS_AT_PRESENT_TIME_WILL_BE_OF_GREAT_HELP_IN_THE_FUTURE = new NpcStringId(1800493);
		PATIENCE_MAY_BE_NEEDED_AS_A_BIG_QUARREL_ARISES_BETWEEN_YOU_AND_A_PERSON_CLOSE_TO_YOU = new NpcStringId(1800494);
		DO_NOT_ASK_FOR_FINANCIAL_HELP_WHEN_THE_TIME_IS_DIFFICULT_YOUR_PRIDE_WILL_BE_HURT_WITHOUT_GAINING_ANY_MONEY = new NpcStringId(1800495);
		CONNECTION_WITH_A_SPECIAL_PERSON_STARTS_WITH_A_MERE_INCIDENT = new NpcStringId(1800496);
		STUBBORNNESS_REGARDLESS_OF_THE_MATTER_WILL_ONLY_BEAR_DANGER = new NpcStringId(1800497);
		KEEP_GOOD_MANNERS_AND_VALUE_TACITURNITY_AS_LIGHT_HEARTEDNESS_MAY_BRING_MISFORTUNE = new NpcStringId(1800498);
		YOU_MAY_MEET_THE_OPPOSITE_SEX = new NpcStringId(1800499);
		GREED_BY_WANTING_TO_TAKE_WEALTH_MAY_BRING_UNFORTUNATE_DISASTER = new NpcStringId(1800500);
		LOSS_IS_AHEAD_REFRAIN_FROM_INVESTING_TRY_TO_SAVE_THE_MONEY_IN_YOUR_POCKETS = new NpcStringId(1800501);
		YOUR_WEALTH_LUCK_IS_DIM_AVOID_ANY_OFFERS = new NpcStringId(1800502);
		A_BIGGER_CHALLENGE_MAY_BE_WHEN_DELAYING_TODAYS_WORK = new NpcStringId(1800503);
		THERE_WILL_BE_DIFFICULTY_BUT_A_GOOD_RESULT_MAY_BE_AHEAD_WHEN_FACING_IT_RESPONSIBLY = new NpcStringId(1800504);
		EVEN_WITH_SOME_DIFFICULTIES_EXPAND_THE_RANGE_OF_YOUR_SCOPE_WHERE_YOU_ARE_IN_CHARGE_IT_WILL_RETURN_TO_YOU_AS_HELP = new NpcStringId(1800505);
		FOCUS_ON_MAINTAINING_ORGANIZED_SURROUNDINGS_TO_HELP_REDUCE_YOUR_LOSSES = new NpcStringId(1800506);
		LUCK_LIES_AHEAD_WHEN_WAITING_FOR_PEOPLE_RATHER_THAN_FOLLOWING_THEM = new NpcStringId(1800507);
		DO_NOT_OFFER_YOUR_HAND_FIRST_EVEN_WHEN_THINGS_ARE_HASTY_THE_RELATIONSHIP_MAY_FALL_APART = new NpcStringId(1800508);
		YOUR_WEALTH_LUCK_IS_RISING_THERE_WILL_BE_SOME_GOOD_RESULT = new NpcStringId(1800509);
		YOU_MAY_FALL_IN_DANGER_EACH_TIME_WHEN_ACTING_UPON_IMPROVISATION = new NpcStringId(1800510);
		BE_WARNED_AS_A_CHILDISHLY_ACT_BEFORE_ELDERS_MAY_RUIN_EVERYTHING = new NpcStringId(1800511);
		THINGS_WILL_MOVE_EFFORTLESSLY_BUT_LUCK_WILL_VANISH_WITH_YOUR_AUDACITY = new NpcStringId(1800512);
		LUCK_MAY_BE_CONTINUED_ONLY_WHEN_HUMILITY_IS_MAINTAINED_AFTER_SUCCESS = new NpcStringId(1800513);
		A_NEW_PERSON_MAY_APPEAR_TO_CREATE_A_LOVE_TRIANGLE = new NpcStringId(1800514);
		LOOK_FOR_SOMEONE_WITH_A_SIMILAR_STYLE_IT_WILL_OPEN_UP_FOR_THE_GOOD = new NpcStringId(1800515);
		AN_OFFER_MAY_SOON_BE_MADE_TO_COLLABORATE_A_TASK_BUT_DELAYING_IT_WILL_BE_A_GOOD_IDEA = new NpcStringId(1800516);
		PARTNERSHIP_IS_OUT_OF_LUCK_AVOID_SOMEONE_WHO_RUSHES_YOU_TO_START_A_COLLABORATION = new NpcStringId(1800517);
		FOCUS_ON_NETWORKING_WITH_LIKE_MINDED_PEOPLE_THEY_MAY_JOIN_YOU_FOR_A_BIG_MISSION_IN_THE_FUTURE = new NpcStringId(1800518);
		BE_WARNED_WHEN_SOMEONE_SAYS_YOU_ARE_INNOCENT_AS_THATS_NOT_A_COMPLIMENT = new NpcStringId(1800519);
		YOU_MAY_BE_SCAMMED_BE_CAUTIOUS_AS_THERE_MAY_BE_A_BIG_LOSS_BY_UNDERESTIMATING_OTHERS = new NpcStringId(1800520);
		LUCK_AT_DECISION_MAKING_IS_DIM_AVOID_SUBJECTIVE_CONCLUSIONS_AND_RELY_ON_UNIVERSAL_COMMON_SENSE = new NpcStringId(1800521);
		YOUR_WEAKNESS_MAY_INVITE_HARDSHIPS_CAUTIOUSLY_TAKE_A_STRONG_POSITION_AS_NEEDED = new NpcStringId(1800522);
		BE_WARY_OF_SOMEONE_WHO_TALKS_AND_ENTERTAINS_TOO_MUCH_THE_PERSON_MAY_BRING_YOU_MISFORTUNE = new NpcStringId(1800523);
		YOU_MAY_ENJOY_A_BEGINNERS_LUCK = new NpcStringId(1800524);
		YOUR_WEALTH_LUCK_IS_STRONG_BUT_YOU_SHOULD_KNOW_WHEN_TO_WITHDRAW = new NpcStringId(1800525);
		ALREADY_ACQUIRED_WEALTH_CAN_BE_LOST_BY_GREED = new NpcStringId(1800526);
		EVEN_IF_YOU_CAN_COMPLETE_IT_BY_YOURSELF_ITS_A_GOOD_IDEA_TO_HAVE_SOMEONE_HELP_YOU = new NpcStringId(1800527);
		MAKE_HARMONY_WITH_PEOPLE_THE_PRIORITY_STUBBORNNESS_MAY_BRING_HARDSHIPS = new NpcStringId(1800528);
		THERE_MAY_BE_A_CHANCE_WHEN_YOU_CAN_SEE_A_NEW_ASPECT_OF_A_CLOSE_FRIEND = new NpcStringId(1800529);
		TRY_TO_BE_CLOSE_TO_SOMEONE_DIFFERENT_FROM_YOU_WITHOUT_ANY_STEREOTYPICAL_JUDGMENT = new NpcStringId(1800530);
		GOOD_LUCK_IN_BECOMING_A_LEADER_WITH_MANY_FOLLOWERS_HOWEVER_ITLL_ONLY_BE_AFTER_HARD_WORK = new NpcStringId(1800531);
		YOUR_WEALTH_LUCK_IS_RISING_EXPENDITURES_WILL_BE_FOLLOWED_BY_SUBSTANTIAL_INCOME_AS_YOU_ARE_ABLE_TO_SUSTAIN = new NpcStringId(1800532);
		BE_CAUTIOUS_AS_YOUR_WEALTH_LUCK_CAN_BE_EITHER_VERY_GOOD_OR_VERY_BAD = new NpcStringId(1800533);
		BE_WARNED_AS_A_SMALL_ARGUMENT_CAN_DISTANCE_YOU_FROM_A_CLOSE_FRIEND = new NpcStringId(1800534);
		THERE_IS_LUCK_IN_LOVE_WITH_A_NEW_PERSON = new NpcStringId(1800535);
		A_BIGGER_FORTUNE_WILL_BE_FOLLOWED_BY_YOUR_GOOD_DEED = new NpcStringId(1800536);
		THERE_MAY_BE_A_RELATIONSHIP_BREAKING_TRY_TO_ELIMINATE_MISUNDERSTANDINGS = new NpcStringId(1800537);
		BE_CAUTIOUS_NOT_TO_BE_EMOTIONALLY_MOVED_EVEN_IF_ITS_CONVINCING = new NpcStringId(1800538);
		SMILING_WILL_BRING_GOOD_LUCK = new NpcStringId(1800539);
		ITS_A_GOOD_IDEA_TO_LET_GO_OF_A_SMALL_LOSS = new NpcStringId(1800540);
		CONVEYING_YOUR_OWN_TRUTH_MAY_BE_DIFFICULT_AND_EASY_MISUNDERSTANDINGS_WILL_FOLLOW = new NpcStringId(1800541);
		THERE_IS_GOOD_LUCK_IN_A_PLACE_WITH_MANY_PEOPLE = new NpcStringId(1800542);
		TRY_TO_AVOID_DIRECTNESS_IF_YOU_CAN = new NpcStringId(1800543);
		VALUE_SUBSTANCE_OPPOSED_TO_THE_SAKE_HONOR_AND_LOOK_BEYOND_WHATS_IN_FRONT_OF_YOU = new NpcStringId(1800544);
		EXPANDING_A_RELATIONSHIP_WITH_HUMOR_MAY_BE_A_GOOD_IDEA = new NpcStringId(1800545);
		AN_ENJOYABLE_EVENT_MAY_BE_AHEAD_IF_YOU_ACCEPT_A_SIMPLE_BET = new NpcStringId(1800546);
		BEING_LEVEL_HEADED_NOT_FOCUSING_ON_EMOTIONS_MAY_HELP_WITH_RELATIONSHIPS = new NpcStringId(1800547);
		ITS_A_GOOD_IDEA_TO_TAKE_CARE_OF_MATTERS_IN_SEQUENTIAL_ORDER_WITHOUT_MEASURING_THEIR_IMPORTANCE = new NpcStringId(1800548);
		A_DETERMINED_ACT_AFTER_PREPARED_RESEARCH_WILL_ATTRACT_PEOPLE = new NpcStringId(1800549);
		A_LITTLE_HUMOR_MAY_BRING_COMPLETE_ATTENTION_TO_YOU = new NpcStringId(1800550);
		IT_MAY_NOT_BE_A_GOOD_TIME_FOR_AN_IMPORTANT_DECISION_BE_WARY_OF_TEMPTATIONS_AND_AVOID_MONETARY_DEALINGS = new NpcStringId(1800551);
		PAY_SPECIAL_ATTENTION_TO_ADVICE_FROM_A_CLOSE_FRIEND = new NpcStringId(1800552);
		THERE_MAY_BE_MODERATE_SOLUTIONS_TO_EVERY_PROBLEM_WHEN_THEYRE_VIEWED_FROM_A_3RD_PARTYS_POINT_OF_VIEW = new NpcStringId(1800553);
		DEALINGS_WITH_CLOSE_FRIENDS_ONLY_BRING_FRUSTRATION_AND_HEADACHE_POLITELY_DECLINE_AND_MENTION_ANOTHER_CHANCE = new NpcStringId(1800554);
		THERE_MAY_BE_A_PROBLEM_AT_COMPLETION_IF_THE_BASIC_MATTERS_ARE_NOT_CONSIDERED_FROM_THE_BEGINNING = new NpcStringId(1800555);
		DISTINGUISHING_BUSINESS_FROM_A_PRIVATE_MATTER_IS_NEEDED_TO_SUCCEED = new NpcStringId(1800556);
		A_CHANGE_IN_RULES_MAY_BE_HELPFUL_WHEN_PROBLEMS_ARE_PERSISTENT = new NpcStringId(1800557);
		PREPARING_FOR_AN_UNFORESEEN_SITUATION_WILL_BE_DIFFICULT_WHEN_SMALL_MATTERS_ARE_IGNORED = new NpcStringId(1800558);
		REFRAIN_FROM_GETTING_INVOLVED_IN_OTHERS_BUSINESS_TRY_TO_BE_LOOSE_AS_A_GOOSE = new NpcStringId(1800559);
		BEING_NEUTRAL_IS_A_GOOD_WAY_TO_GO_BUT_CLARITY_MAY_BE_HELPFUL_CONTRARY_TO_YOUR_HESITANCE = new NpcStringId(1800560);
		BE_CAUTIOUS_OF_YOUR_OWN_ACTIONS_THE_PAST_MAY_BRING_MISUNDERSTANDINGS = new NpcStringId(1800561);
		PAY_ATTENTION_TO_TIME_MANAGEMENT_EMOTIONS_MAY_WASTE_YOUR_TIME = new NpcStringId(1800562);
		HEROISM_WILL_BE_REWARDED_BUT_BE_CAREFUL_NOT_TO_DISPLAY_ARROGANCE_OR_LACK_OF_SINCERITY = new NpcStringId(1800563);
		IF_YOU_WANT_TO_MAINTAIN_RELATIONSHIP_CONNECTIONS_OFFER_RECONCILIATION_TO_THOSE_WHO_HAD_MISUNDERSTANDINGS_WITH_YOU = new NpcStringId(1800564);
		STEP_FORWARD_TO_SOLVE_OTHERS_PROBLEMS_WHEN_THEY_ARE_UNABLE = new NpcStringId(1800565);
		THERE_MAY_BE_A_LITTLE_LOSS_BUT_THINK_OF_IT_AS_AN_INVESTMENT_FOR_YOURSELF = new NpcStringId(1800566);
		AVARICE_BEARS_A_BIGGER_GREED_BEING_SATISFIED_WITH_MODERATION_IS_NEEDED = new NpcStringId(1800567);
		A_RATIONAL_ANALYSIS_IS_NEEDED_AS_UNPLANNED_ACTIONS_MAY_BRING_CRITICISM = new NpcStringId(1800568);
		REFLECT_UPON_YOUR_SHORTCOMINGS_BEFORE_CRITICIZING_OTHERS = new NpcStringId(1800569);
		FOLLOW_UP_CARE_IS_ALWAYS_NEEDED_AFTER_AN_EMERGENCY_EVASION = new NpcStringId(1800570);
		YOU_MAY_LOOK_FOR_A_NEW_CHALLENGE_BUT_VAST_KNOWLEDGE_IS_REQUIRED = new NpcStringId(1800571);
		WHEN_ONE_PUTS_ASIDE_THEIR_EGO_ANY_MISUNDERSTANDING_WILL_BE_SOLVED = new NpcStringId(1800572);
		LISTEN_TO_THE_ADVICE_THATS_GIVEN_TO_YOU_WITH_A_HUMBLE_ATTITUDE = new NpcStringId(1800573);
		EQUILIBRIUM_IS_ACHIEVED_WHEN_ONE_UNDERSTANDS_A_DOWNSHIFT_IS_EVIDENT_AFTER_THE_RISE = new NpcStringId(1800574);
		WHAT_YOU_SOW_IS_WHAT_YOU_REAP_FAITHFULLY_FOLLOW_THE_PLAN = new NpcStringId(1800575);
		METICULOUS_PREPARATION_IS_NEEDED_AS_SPONTANEOUS_ACTIONS_ONLY_BEAR_MENTAL_AND_MONETARY_LOSSES = new NpcStringId(1800576);
		THE_RIGHT_TIME_TO_BEAR_FRUIT_IS_DELAYED_WHILE_THE_FARMER_PONDERS_OPINIONS = new NpcStringId(1800577);
		HELP_EACH_OTHER_AMONG_CLOSE_FRIENDS = new NpcStringId(1800578);
		OBSESSING_OVER_A_SMALL_PROFIT_WILL_PLACE_PEOPLE_APART = new NpcStringId(1800579);
		DONT_CLING_TO_THE_RESULT_OF_A_GAMBLE = new NpcStringId(1800580);
		SMALL_TROUBLES_AND_ARGUMENTS_ARE_AHEAD_FACE_THEM_WITH_A_MATURE_ATTITUDE = new NpcStringId(1800581);
		NEGLECTING_A_PROMISE_MAY_PUT_YOU_IN_DISTRESS = new NpcStringId(1800582);
		DELAY_ANY_DEALINGS_AS_YOU_MAY_EASILY_OMIT_ADDRESSING_WHATS_IMPORTANT_TO_YOU = new NpcStringId(1800583);
		A_COMPARISON_TO_OTHERS_MAY_BE_HELPFUL = new NpcStringId(1800584);
		WHAT_YOUVE_ENDURED_WILL_RETURN_AS_A_BENEFIT = new NpcStringId(1800585);
		TRY_TO_BE_COURTEOUS_TO_THE_OPPOSITE_SEX_AND_FOLLOW_A_VIRTUOUS_PATH = new NpcStringId(1800586);
		JOY_MAY_COME_FROM_SMALL_THINGS = new NpcStringId(1800587);
		BE_CONFIDENT_IN_YOUR_ACTIONS_AS_GOOD_LUCK_SHADOWS_THE_RESULT = new NpcStringId(1800588);
		BE_CONFIDENT_WITHOUT_HESITATION_WHEN_YOUR_HONESTY_IS_ABOVE_REPROACH_IN_DEALINGS = new NpcStringId(1800589);
		A_MATTER_RELATED_TO_A_CLOSE_FRIEND_CAN_ISOLATE_YOU_KEEP_STAYING_ON_THE_RIGHT_PATH = new NpcStringId(1800590);
		TOO_MUCH_FOCUS_ON_THE_RESULT_MAY_BRING_CONTINUOUS_MISFORTUNE = new NpcStringId(1800591);
		BE_TENACIOUS_UNTIL_THE_FINISH_AS_HALFWAY_ABANDONMENT_CAUSES_A_TROUBLED_ENDING = new NpcStringId(1800592);
		THERE_WILL_BE_NO_ADVANTAGE_IN_A_GROUP_DEAL = new NpcStringId(1800593);
		REFRAIN_FROM_STEPPING_UP_BUT_TAKE_A_MOMENT_TO_PONDER_TO_BE_FLEXIBLE_WITH_SITUATIONS = new NpcStringId(1800594);
		THERE_WILL_BE_A_SMALL_OPPORTUNITY_WHEN_INFORMATION_IS_BEST_UTILIZED = new NpcStringId(1800595);
		BELONGINGS_ARE_AT_LOOSE_ENDS_KEEP_TRACK_OF_THE_THINGS_YOU_VALUE = new NpcStringId(1800596);
		WHAT_YOU_SOW_IS_WHAT_YOU_REAP_TRY_YOUR_BEST = new NpcStringId(1800597);
		WITH_THE_BEGINNERS_ATTITUDE_SHORTCOMINGS_CAN_BE_EASILY_MENDED = new NpcStringId(1800598);
		WHEN_FACING_DIFFICULTIES_SEEK_A_TOTALLY_DIFFERENT_DIRECTION = new NpcStringId(1800599);
		LIFETIME_SAVINGS_CAN_DISAPPEAR_WITH_ONE_TIME_GREED = new NpcStringId(1800600);
		WITH_YOUR_HEART_AVOID_EXTREMES_AND_PEACE_WILL_STAY = new NpcStringId(1800601);
		BE_CAUTIOUS_AS_INSTANT_RECKLESSNESS_MAY_BRING_MALICIOUS_GOSSIP = new NpcStringId(1800602);
		BE_TENACIOUS_TO_THE_END_BECAUSE_A_STRONG_LUCK_WITH_WINNING_IS_AHEAD = new NpcStringId(1800603);
		BE_KIND_TO_AND_CARE_FOR_THOSE_CLOSE_TO_YOU_THEY_MAY_HELP_IN_THE_FUTURE = new NpcStringId(1800604);
		POSITIVITY_MAY_BRING_GOOD_RESULTS = new NpcStringId(1800605);
		BE_GRACIOUS_TO_COVER_A_CLOSE_FRIENDS_FAULT = new NpcStringId(1800606);
		BE_PREPARED_FOR_AN_EXPECTED_COST = new NpcStringId(1800607);
		BE_CONSIDERATE_TO_OTHERS_AND_AVOID_FOCUSING_ONLY_ON_WINNING_OR_A_WOUND_WILL_BE_LEFT_UNTREATED = new NpcStringId(1800608);
		AN_ACCESSORY_OR_DECORATION_MAY_BRING_A_GOOD_LUCK = new NpcStringId(1800609);
		ONLY_REFLECTION_AND_HUMILITY_MAY_BRING_SUCCESS = new NpcStringId(1800610);
		A_SMALL_MISUNDERSTANDING_MAY_CAUSE_QUARRELS = new NpcStringId(1800611);
		AVOID_ADVANCING_BEYOND_YOUR_ABILITY_AND_FOCUS_ON_THE_FLOWING_STREAM = new NpcStringId(1800612);
		CONSIDERING_OTHERS_WITH_A_GOOD_HEART_BEFORE_SELF_INTEREST_WILL_BRING_A_TRIUMPH = new NpcStringId(1800613);
		VISITING_A_PLACE_YOUVE_NEVER_BEEN_BEFORE_MAY_BRING_LUCK = new NpcStringId(1800614);
		A_GOOD_THING_MAY_HAPPEN_IN_A_PLACE_WITH_A_FEW_PEOPLE = new NpcStringId(1800615);
		BEING_HIGH_STRUNG_CAN_CAUSE_LOSS_OF_TRUST_FROM_OTHERS_BECAUSE_IT_CAN_BE_VIEWED_AS_LIGHT_HEARTED_ACT_SINCERELY_BUT_YET_DO_NOT_LACK_HUMOR = new NpcStringId(1800616);
		PERFECTION_AT_THE_FINISH_CAN_COVER_FAULTY_WORK_IN_THE_PROCESS = new NpcStringId(1800617);
		ABSTAIN_FROM_LAZINESS_MUCH_WORK_BRINGS_MANY_GAINS_AND_SATISFACTORY_REWARDS = new NpcStringId(1800618);
		STAYING_BUSY_RATHER_THAN_BEING_STATIONARY_WILL_HELP = new NpcStringId(1800619);
		HANDLING_THE_WORK_BY_YOURSELF_MAY_LEAD_YOU_INTO_TEMPTATION = new NpcStringId(1800620);
		PAY_ATTENTION_TO_ANY_SMALL_ADVICE_WITHOUT_BEING_INDIFFERENT = new NpcStringId(1800621);
		SMALL_THINGS_MAKE_UP_BIG_THINGS_SO_EVEN_VALUE_TRIVIAL_MATTERS = new NpcStringId(1800622);
		ACTION_TOWARD_THE_RESULT_RATHER_THAN_WAITING_FOR_THE_RIGHT_CIRCUMSTANCES_MAY_LEAD_YOU_TO_A_FAST_SUCCESS = new NpcStringId(1800623);
		DONT_TRY_TO_SAVE_SMALL_EXPENDITURES_IT_WILL_LEAD_TO_FUTURE_RETURNS = new NpcStringId(1800624);
		BE_CAUTIOUS_TO_CONTROL_EMOTIONS_AS_TEMPTATIONS_ARE_NEARBY = new NpcStringId(1800625);
		BE_WARNED_AS_NEGLECTING_A_MATTER_BECAUSE_ITS_SMALL_CAN_CAUSE_YOU_TROUBLE = new NpcStringId(1800626);
		SPEND_WHEN_NEEDED_RATHER_THAN_TRYING_TO_UNCONDITIONALLY_SAVE = new NpcStringId(1800627);
		PREJUDICE_WILL_TAKE_YOU_TO_A_SMALL_GAIN_WITH_A_BIG_LOSS = new NpcStringId(1800628);
		SWEET_FOOD_MAY_BRING_GOOD_LUCK = new NpcStringId(1800629);
		YOU_MAY_BE_PAID_FOR_WHAT_YOURE_OWED_OR_FOR_YOUR_PAST_LOSS = new NpcStringId(1800630);
		THERE_MAY_BE_CONFLICT_IN_BASIC_MATTERS = new NpcStringId(1800631);
		BE_OBSERVANT_TO_CLOSE_FRIENDS_SMALL_BEHAVIORS_WHILE_REFRAINING_FROM_EXCESSIVE_KINDNESS = new NpcStringId(1800632);
		DO_NOT_SHOW_YOUR_DISTRESS_NOR_LOSE_YOUR_SMILE = new NpcStringId(1800633);
		SHOWING_CHANGE_MAY_BE_OF_HELP = new NpcStringId(1800634);
		THE_INTENDED_RESULT_MAY_BE_ON_YOUR_WAY_IF_THE_TIME_IS_PERFECTLY_MANAGED = new NpcStringId(1800635);
		HARDSHIPS_MAY_ARISE_IF_FLEXIBILITY_IS_NOT_WELL_PLAYED = new NpcStringId(1800636);
		KEEP_COOL_HEADED_BECAUSE_CARELESSNESS_OR_INATTENTIVENESS_MAY_CAUSE_MISFORTUNE = new NpcStringId(1800637);
		BE_CAUTIOUS_AS_YOU_MAY_GET_HURT_AFTER_LAST_NIGHTS_SINISTER_DREAM = new NpcStringId(1800638);
		A_STRONG_WEALTH_LUCK_IS_AHEAD_BUT_BE_CAREFUL_WITH_EMOTIONS_THAT_MAY_BRING_LOSSES = new NpcStringId(1800639);
		PROCEED_AS_YOU_WISH_WHEN_ITS_PERTINENT_TO_THE_PERSON_YOU_LIKE = new NpcStringId(1800640);
		YOU_MAY_DEEPEN_THE_RELATIONSHIP_WITH_THE_OPPOSITE_SEX_THROUGH_CONVERSATION = new NpcStringId(1800641);
		INVESTMENT_INTO_SOLID_MATERIAL_MAY_BRING_PROFIT = new NpcStringId(1800642);
		INVESTMENT_INTO_WHAT_YOU_ENJOY_MAY_BE_OF_HELP = new NpcStringId(1800643);
		BEING_BUSY_MAY_HELP_CATCHING_UP_WITH_MANY_CHANGES = new NpcStringId(1800644);
		CHOOSE_SUBSTANCE_OVER_HONOR = new NpcStringId(1800645);
		REMEMBER_TO_DECLINE_ANY_FINANCIAL_DEALINGS_BECAUSE_A_GOOD_DEED_MAY_RETURN_AS_RESENTMENT = new NpcStringId(1800646);
		BE_CAREFUL_NOT_TO_MAKE_A_MISTAKE_WITH_A_NEW_PERSON = new NpcStringId(1800647);
		DO_NOT_BE_OBSESSIVE_OVER_A_DRAGGED_OUT_PROJECT_SINCE_IT_WONT_GET_ANY_BETTER_WITH_MORE_TIME = new NpcStringId(1800648);
		DO_NOT_YIELD_WHATS_RIGHTFULLY_YOURS_OR_TOLERATE_LOSSES = new NpcStringId(1800649);
		THERES_LUCK_IN_RELATIONSHIPS_SO_BECOME_INTERESTED_IN_THE_OPPOSITE_SEX = new NpcStringId(1800650);
		SEEKING_OTHERS_HELP_RATHER_THAN_TRYING_BY_YOURSELF_MAY_RESULT_IN_TWO_BIRDS_WITH_ONE_STONE = new NpcStringId(1800651);
		PERSUADING_THE_OTHER_MAY_RESULT_IN_YOUR_GAIN = new NpcStringId(1800652);
		A_GOOD_OPPORTUNITY_MAY_COME_WHEN_KEEPING_PATIENCE_WITHOUT_EXCESSIVENESS = new NpcStringId(1800653);
		THE_OPPOSITE_SEX_MAY_BRING_FORTUNE = new NpcStringId(1800654);
		DOING_FAVOR_FOR_OTHER_PEOPLE_MAY_BRING_FORTUNE_IN_THE_FUTURE = new NpcStringId(1800655);
		LUCK_MAY_STAY_NEAR_IF_A_SMILE_IS_KEPT_DURING_DIFFICULT_TIMES = new NpcStringId(1800656);
		YOU_MAY_REVEAL_YOUR_TRUE_SELF_LIKE_IRON_IS_MOLTEN_INTO_AN_STRONG_SWORD = new NpcStringId(1800657);
		YOUR_VALUE_WILL_SHINE_AS_YOUR_POTENTIAL_IS_FINALLY_REALIZED = new NpcStringId(1800658);
		TENACIOUS_EFFORTS_IN_SOLVING_A_DIFFICULT_MISSION_OR_HARDSHIP_MAY_BRING_GOOD_RESULTS_AS_WELL_AS_REALIZING_YOUR_HIDDEN_POTENTIAL = new NpcStringId(1800659);
		PEOPLE_WILL_APPRECIATE_YOUR_POSITIVITY_AND_JOYFUL_ENTERTAINING = new NpcStringId(1800660);
		THINGS_WILL_MOVE_SMOOTHLY_WITH_YOUR_FULL_WISDOM_AND_ABILITIES = new NpcStringId(1800661);
		YOU_MAY_MEET_A_SAGE_WHO_CAN_HELP_YOU_FIND_THE_RIGHT_PATH = new NpcStringId(1800662);
		KEEN_INSTINCT_AND_FORESIGHT_WILL_SHINE_THEIR_VALUES = new NpcStringId(1800663);
		YOU_MAY_BRING_GOOD_LUCK_TO_THOSE_AROUND_YOU = new NpcStringId(1800664);
		YOUR_GOAL_MAY_BE_REALIZED_WHEN_EMOTIONAL_DETAILS_ARE_WELL_DEFINED = new NpcStringId(1800665);
		YOU_MAY_ENJOY_AFFLUENCE_AFTER_MEETING_A_PRECIOUS_PERSON = new NpcStringId(1800666);
		YOU_MAY_MEET_THE_OPPOSITE_SEX_WHO_HAS_MATERIALISTIC_ATTRACTIONS = new NpcStringId(1800667);
		A_BIG_SUCCESS_WILL_FOLLOW_ALL_POSSIBLE_EFFORTS_IN_COMPETITION = new NpcStringId(1800668);
		A_CONSEQUENCE_FROM_PAST_ACTIONS_WILL_BE_ON_DISPLAY = new NpcStringId(1800669);
		WHATEVER_HAPPENED_TO_YOU_AND_THE_OTHER_PERSON_WILL_REPLAY_BUT_THIS_TIME_THE_OPPOSITE_WILL_BE_THE_RESULT = new NpcStringId(1800670);
		YOU_MAY_NEED_TO_SACRIFICE_FOR_A_HIGHER_CAUSE = new NpcStringId(1800671);
		YOU_MAY_LOSE_AN_ITEM_BUT_WILL_GAIN_HONOR = new NpcStringId(1800672);
		A_NEW_TRIAL_OR_START_MAY_BE_SUCCESSFUL_AS_LUCK_SHADOWS_CHANGES = new NpcStringId(1800673);
		BE_SOPHISTICATED_WITHOUT_SHOWING_YOUR_TRUE_EMOTIONS_AS_TRICKS_AND_MATERIALISTIC_TEMPTATIONS_LIE_AHEAD = new NpcStringId(1800674);
		DO_NOT_ATTEMPT_A_DANGEROUS_ADVENTURE = new NpcStringId(1800675);
		DO_NOT_BE_AFRAID_OF_CHANGE_A_RISK_WILL_BE_ANOTHER_OPPORTUNITY = new NpcStringId(1800676);
		BE_CONFIDENT_AND_ACT_TENACIOUSLY_AT_ALL_TIMES_YOU_MAY_BE_ABLE_TO_ACCOMPLISH_TO_PERFECTION_DURING_SOMEWHAT_UNSTABLE_SITUATIONS = new NpcStringId(1800677);
		YOU_MAY_EXPECT_A_BRIGHT_AND_HOPEFUL_FUTURE = new NpcStringId(1800678);
		A_REST_WILL_PROMISE_A_BIGGER_DEVELOPMENT = new NpcStringId(1800679);
		FULLY_UTILIZE_POSITIVE_VIEWS = new NpcStringId(1800680);
		POSITIVE_THINKING_AND_ENERGETIC_ACTIONS_WILL_TAKE_YOU_TO_THE_CENTER_OF_THE_GLORIOUS_STAGE = new NpcStringId(1800681);
		YOUR_SELF_CONFIDENCE_AND_INTUITION_MAY_SOLVE_THE_DIFFICULTIES = new NpcStringId(1800682);
		EVERYTHING_IS_BRILLIANT_AND_JOYFUL_SHARE_IT_WITH_OTHERS_A_BIGGER_FORTUNE_WILL_FOLLOW = new NpcStringId(1800683);
		A_FAIR_ASSESSMENT_AND_REWARD_FOR_PAST_ACTIONS_LIE_AHEAD = new NpcStringId(1800684);
		PAY_ACCURATELY_THE_OLD_LIABILITY_OR_DEBT_IF_APPLICABLE_A_NEW_JOY_LIES_AHEAD = new NpcStringId(1800685);
		AN_EXCESSIVE_HUMILITY_CAN_HARM_YOU_BACK = new NpcStringId(1800686);
		A_REWARD_FOR_THE_PAST_WORK_WILL_COME_THROUGH = new NpcStringId(1800687);
		YOUR_PAST_FRUITLESS_EFFORT_WILL_FINALLY_BE_REWARDED_WITH_SOMETHING_UNEXPECTED = new NpcStringId(1800688);
		THERES_STRONG_LUCK_IN_A_REVIVAL_ABANDON_THE_OLD_AND_CREATE_THE_NEW = new NpcStringId(1800689);
		YOU_MAY_GAIN_MATERIALISTIC_OR_MENTAL_AID_FROM_CLOSE_FRIENDS = new NpcStringId(1800690);
		A_GOOD_BEGINNING_IS_AWAITING_YOU = new NpcStringId(1800691);
		YOU_MAY_MEET_THE_PERSON_YOUVE_LONGED_TO_SEE = new NpcStringId(1800692);
		YOU_MAY_SUSTAIN_A_LOSS_DUE_TO_YOUR_KINDNESS = new NpcStringId(1800693);
		CLOSELY_OBSERVE_PEOPLE_WHO_PASS_BY_SINCE_YOU_MAY_MEET_A_PRECIOUS_PERSON_WHO_CAN_HELP_YOU = new NpcStringId(1800694);
		MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_WERE_GATHERING_BRAVE_ADVENTURERS_TO_ATTACK_TIATS_MOUNTED_TROOP_THATS_ROOTED_IN_THE_SEED_OF_DESTRUCTION = new NpcStringId(1800695);
		MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_DESTRUCTION_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE = new NpcStringId(1800696);
		MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_TIATS_MOUNTED_TROOP_IS_CURRENTLY_TRYING_TO_RETAKE_SEED_OF_DESTRUCTION_COMMIT_ALL_THE_AVAILABLE_REINFORCEMENTS_INTO_SEED_OF_DESTRUCTION = new NpcStringId(1800697);
		MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_BRAVE_ADVENTURERS_WHO_HAVE_CHALLENGED_THE_SEED_OF_INFINITY_ARE_CURRENTLY_INFILTRATING_THE_HALL_OF_EROSION_THROUGH_THE_DEFENSIVELY_WEAK_HALL_OF_SUFFERING = new NpcStringId(1800698);
		MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_SWEEPING_THE_SEED_OF_INFINITY_IS_CURRENTLY_COMPLETE_TO_THE_HEART_OF_THE_SEED_EKIMUS_IS_BEING_DIRECTLY_ATTACKED_AND_THE_UNDEAD_REMAINING_IN_THE_HALL_OF_SUFFERING_ARE_BEING_ERADICATED = new NpcStringId(1800699);
		MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_INFINITY_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE = new NpcStringId(1800700);
		_ = new NpcStringId(1800701);
		MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_RESURRECTED_UNDEAD_IN_THE_SEED_OF_INFINITY_ARE_POURING_INTO_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION = new NpcStringId(1800702);
		MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_EKIMUS_IS_ABOUT_TO_BE_REVIVED_BY_THE_RESURRECTED_UNDEAD_IN_SEED_OF_INFINITY_SEND_ALL_REINFORCEMENTS_TO_THE_HEART_AND_THE_HALL_OF_SUFFERING = new NpcStringId(1800703);
		STABBING_THREE_TIMES = new NpcStringId(1800704);
		POOR_CREATURES_FEEL_THE_POWER_OF_DARKNESS = new NpcStringId(1800705);
		WHOAAAAAA = new NpcStringId(1800706);
		YOULL_REGRET_CHALLENGING_ME = new NpcStringId(1800707);
		ITS_CURRENTLY_OCCUPIED_BY_THE_ENEMY_AND_OUR_TROOPS_ARE_ATTACKING = new NpcStringId(1800708);
		ITS_UNDER_OCCUPATION_BY_OUR_FORCES_AND_I_HEARD_THAT_KUCEREUS_CLAN_IS_ORGANIZING_THE_REMNANTS = new NpcStringId(1800709);
		ALTHOUGH_WE_CURRENTLY_HAVE_CONTROL_OF_IT_THE_ENEMY_IS_PUSHING_BACK_WITH_A_POWERFUL_ATTACK = new NpcStringId(1800710);
		ITS_UNDER_THE_ENEMYS_OCCUPATION_AND_THE_MILITARY_FORCES_OF_ADVENTURERS_AND_CLAN_MEMBERS_ARE_UNLEASHING_AN_ONSLAUGHT_UPON_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION = new NpcStringId(1800711);
		OUR_FORCES_HAVE_OCCUPIED_IT_AND_ARE_CURRENTLY_INVESTIGATING_THE_DEPTHS = new NpcStringId(1800713);
		ITS_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_RESURRECTED_AND_IS_ATTACKING_TOWARD_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION = new NpcStringId(1800714);
		ITS_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_ALREADY_OVERTAKEN_THE_HALL_OF_EROSION_AND_IS_DRIVING_OUT_OUR_FORCES_FROM_THE_HALL_OF_SUFFERING_TOWARD_THE_HEART_IT_SEEMS_THAT_EKIMUS_WILL_REVIVE_SHORTLY = new NpcStringId(1800715);
		TIATS_FOLLOWERS_ARE_COMING_TO_RETAKE_THE_SEED_OF_DESTRUCTION_GET_READY_TO_STOP_THE_ENEMIES = new NpcStringId(1800717);
		ITS_HURTING_IM_IN_PAIN_WHAT_CAN_I_DO_FOR_THE_PAIN = new NpcStringId(1800718);
		NO_WHEN_I_LOSE_THAT_ONE_ILL_BE_IN_MORE_PAIN = new NpcStringId(1800719);
		HAHAHAH_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR = new NpcStringId(1800720);
		NOW_WHY_DONT_YOU_TAKE_UP_THE_CHALLENGE = new NpcStringId(1800721);
		COME_ON_ILL_TAKE_ALL_OF_YOU_ON = new NpcStringId(1800722);
		HOW_ABOUT_IT_I_THINK_I_WON = new NpcStringId(1800723);
		NOW_THOSE_OF_YOU_WHO_LOST_GO_AWAY = new NpcStringId(1800724);
		WHAT_A_BUNCH_OF_LOSERS = new NpcStringId(1800725);
		I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_PERSON = new NpcStringId(1800726);
		AH_OKAY = new NpcStringId(1800727);
		UAGH_I_WASNT_GOING_TO_DO_THAT = new NpcStringId(1800728);
		YOURE_CURSED_OH_WHAT = new NpcStringId(1800729);
		HAVE_YOU_DONE_NOTHING_BUT_ROCK_PAPER_SCISSORS = new NpcStringId(1800730);
		STOP_IT_NO_MORE_I_DID_IT_BECAUSE_I_WAS_TOO_LONELY = new NpcStringId(1800731);
		I_HAVE_TO_RELEASE_SANTA_HOW_INFURIATING = new NpcStringId(1800732);
		I_HATE_HAPPY_MERRY_CHRISTMAS = new NpcStringId(1800733);
		OH_IM_BORED = new NpcStringId(1800734);
		SHALL_I_GO_TO_TAKE_A_LOOK_IF_SANTA_IS_STILL_THERE_HEHE = new NpcStringId(1800735);
		OH_HO_HO_MERRY_CHRISTMAS = new NpcStringId(1800736);
		SANTA_COULD_GIVE_NICE_PRESENTS_ONLY_IF_HES_RELEASED_FROM_THE_TURKEY = new NpcStringId(1800737);
		OH_HO_HO_OH_HO_HO_THANK_YOU_LADIES_AND_GENTLEMEN_I_WILL_REPAY_YOU_FOR_SURE = new NpcStringId(1800738);
		UMERRY_CHRISTMAS_YOURE_DOING_A_GOOD_JOB = new NpcStringId(1800739);
		MERRY_CHRISTMAS_THANK_YOU_FOR_RESCUING_ME_FROM_THAT_WRETCHED_TURKEY = new NpcStringId(1800740);
		S1_I_HAVE_PREPARED_A_GIFT_FOR_YOU = new NpcStringId(1800741);
		I_HAVE_A_GIFT_FOR_S1 = new NpcStringId(1800742);
		TAKE_A_LOOK_AT_THE_INVENTORY_I_HOPE_YOU_LIKE_THE_GIFT_I_GAVE_YOU = new NpcStringId(1800743);
		TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_MIGHT_BE_A_BIG_PRESENT = new NpcStringId(1800744);
		IM_TIRED_OF_DEALING_WITH_YOU_IM_LEAVING = new NpcStringId(1800745);
		WHEN_ARE_YOU_GOING_TO_STOP_I_SLOWLY_STARTED_TO_BE_TIRED_OF_IT = new NpcStringId(1800746);
		MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S1_WHO_SAVED_ME = new NpcStringId(1800747);
		I_AM_ALREADY_DEAD_YOU_CANNOT_KILL_ME_AGAIN = new NpcStringId(1800748);
		OH_FOLLOWERS_OF_THE_DRAGON_OF_DARKNESS_FIGHT_BY_MY_SIDE = new NpcStringId(1800749);
		THE_DRAGON_RACE_ARE_INVADING_PREPARE_FOR_BATTLE = new NpcStringId(1800750);
		S1_RESCUED_SANTA_CLAUS_OF_S2_TERRITORY_FROM_THE_TURKEY = new NpcStringId(1800751);
		SANTA_RESCUE_SUCCESS = new NpcStringId(1800752);
		S1_RECEIVED_S2_S3_THROUGH_THE_WEAPON_EXCHANGE_COUPON = new NpcStringId(1800753);
		DONT_GO_PRATTLING_ON = new NpcStringId(1800754);
		YOU_LOWLIFES_WITH_NOT_EVEN_AN_OUNCE_OF_PRIDE_YOURE_NOT_WORTHY_OF_OPPOSING_ME = new NpcStringId(1800755);
		ROAR_NO_OINK_OINK_SEE_IM_A_PIG_OINK_OINK = new NpcStringId(1800756);
		WHO_AM_I_WHERE_AM_I_OINK_OINK = new NpcStringId(1800757);
		I_JUST_FOLLOWED_MY_FRIEND_HERE_FOR_FUN_OINK_OINK = new NpcStringId(1800758);
		WOW_THATS_WHAT_I_CALL_A_CURE_ALL = new NpcStringId(1800759);
		IM_STARVING_SHOULD_I_GO_CHEW_SOME_GRASS = new NpcStringId(1800760);
		THANK_YOU_THANK_YOU = new NpcStringId(1800761);
		WHATS_THIS_FEELING_OH_OH_FEELS_LIKE_MY_ENERGY_IS_BACK = new NpcStringId(1800762);
		MY_BODYS_GETTING_LIGHTER_THIS_FEELING_FEELS_FAMILIAR_SOMEHOW = new NpcStringId(1800763);
		WOW_MY_FATIGUE_IS_COMPLETELY_GONE = new NpcStringId(1800764);
		HEY_THE_OMINOUS_ENERGY_IS_DISAPPEARED = new NpcStringId(1800765);
		MY_BODY_FEELS_AS_LIGHT_AS_A_FEATHER = new NpcStringId(1800766);
		WHATS_THIS_FOOD = new NpcStringId(1800767);
		MY_ENERGY_IS_OVERFLOWING_I_DONT_NEED_ANY_FATIGUE_RECOVERY_POTION = new NpcStringId(1800768);
		WHATS_THE_MATTER_THATS_AN_AMATEUR_MOVE = new NpcStringId(1800769);
		FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_10_SECONDS = new NpcStringId(1800770);
		FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_40_SECONDS = new NpcStringId(1800771);
		N40_SECONDS_ARE_REMAINING = new NpcStringId(1800772);
		N39_SECONDS_ARE_REMAINING = new NpcStringId(1800773);
		N38_SECONDS_ARE_REMAINING = new NpcStringId(1800774);
		N37_SECONDS_ARE_REMAINING = new NpcStringId(1800775);
		N36_SECONDS_ARE_REMAINING = new NpcStringId(1800776);
		N35_SECONDS_ARE_REMAINING = new NpcStringId(1800777);
		N34_SECONDS_ARE_REMAINING = new NpcStringId(1800778);
		N33_SECONDS_ARE_REMAINING = new NpcStringId(1800779);
		N32_SECONDS_ARE_REMAINING = new NpcStringId(1800780);
		N31_SECONDS_ARE_REMAINING = new NpcStringId(1800781);
		N30_SECONDS_ARE_REMAINING = new NpcStringId(1800782);
		N29_SECONDS_ARE_REMAINING = new NpcStringId(1800783);
		N28_SECONDS_ARE_REMAINING = new NpcStringId(1800784);
		N27_SECONDS_ARE_REMAINING = new NpcStringId(1800785);
		N26_SECONDS_ARE_REMAINING = new NpcStringId(1800786);
		N25_SECONDS_ARE_REMAINING = new NpcStringId(1800787);
		N24_SECONDS_ARE_REMAINING = new NpcStringId(1800788);
		N23_SECONDS_ARE_REMAINING = new NpcStringId(1800789);
		N22_SECONDS_ARE_REMAINING = new NpcStringId(1800790);
		N21_SECONDS_ARE_REMAINING = new NpcStringId(1800791);
		N20_SECONDS_ARE_REMAINING = new NpcStringId(1800792);
		N19_SECONDS_ARE_REMAINING = new NpcStringId(1800793);
		N18_SECONDS_ARE_REMAINING = new NpcStringId(1800794);
		N17_SECONDS_ARE_REMAINING = new NpcStringId(1800795);
		N16_SECONDS_ARE_REMAINING = new NpcStringId(1800796);
		N15_SECONDS_ARE_REMAINING = new NpcStringId(1800797);
		N14_SECONDS_ARE_REMAINING = new NpcStringId(1800798);
		N13_SECONDS_ARE_REMAINING = new NpcStringId(1800799);
		N12_SECONDS_ARE_REMAINING = new NpcStringId(1800800);
		N11_SECONDS_ARE_REMAINING = new NpcStringId(1800801);
		N10_SECONDS_ARE_REMAINING = new NpcStringId(1800802);
		N9_SECONDS_ARE_REMAINING = new NpcStringId(1800803);
		N8_SECONDS_ARE_REMAINING = new NpcStringId(1800804);
		N7_SECONDS_ARE_REMAINING = new NpcStringId(1800805);
		N6_SECONDS_ARE_REMAINING = new NpcStringId(1800806);
		N5_SECONDS_ARE_REMAINING = new NpcStringId(1800807);
		N4_SECONDS_ARE_REMAINING = new NpcStringId(1800808);
		N3_SECONDS_ARE_REMAINING = new NpcStringId(1800809);
		N2_SECONDS_ARE_REMAINING = new NpcStringId(1800810);
		N1_SECONDS_ARE_REMAINING = new NpcStringId(1800811);
		TIME_UP = new NpcStringId(1800812);
		MISSION_FAILED = new NpcStringId(1800813);
		MISSION_SUCCESS = new NpcStringId(1800814);
		HEY_I_ALREADY_HAVE_AN_OWNER = new NpcStringId(1800815);
		HEY_ARE_YOU_PLANNING_ON_EATING_ME_USE_A_CUPIDS_FATIGUE_RECOVERY_POTION_ALREADY = new NpcStringId(1800816);
		ILL_PASS_ON_AN_AMATEURS_MERIDIAN_MASSAGE_USE_A_CUPIDS_FATIGUE_RECOVERY_POTION_ALREADY = new NpcStringId(1800817);
		I_ALREADY_FEEL_MORE_ENERGETIC_THANKS_S1 = new NpcStringId(1800818);
		HOW_REFRESHING_YOU_WOULDNT_HAPPEN_TO_BE_A_MASTER_MASSEUSE_S1_WOULD_YOU = new NpcStringId(1800819);
		INCREDIBLE_FROM_NOW_ON_ILL_COMPARE_ALL_MASSAGES_TO_THIS_ONE_WITH_S1 = new NpcStringId(1800820);
		ISNT_IT_TOUGH_DOING_IT_ALL_ON_YOUR_OWN_NEXT_TIME_TRY_MAKING_A_PARTY_WITH_SOME_COMRADES = new NpcStringId(1800821);
		SORRY_BUT_ILL_LEAVE_MY_FRIEND_IN_YOUR_CARE_AS_WELL_THANKS = new NpcStringId(1800822);
		SNIFF_SNIFF_DO_YOU_SMELL_THE_SCENT_OF_A_FRESH_BAKED_BAGUETTE = new NpcStringId(1800823);
		WHO_AM_I_LET_ME_KNOW_IF_YOU_WANNA_BUY_MY_BREAD = new NpcStringId(1800824);
		I_JUST_WANT_TO_MAKE_YOUR_WEAPONS_STRONGER_ABRA_KADABRA = new NpcStringId(1800825);
		WHAT_YOU_DONT_LIKE_IT_WHATS_THE_MATTER_WITH_YOU_LIKE_AN_AMATEUR = new NpcStringId(1800826);
		HEY_DID_YOU_TELL_A_LIE_ON_APRIL_FOOLS_DAY_DONT_TALK_TO_ME_IF_YOU_DIDNT = new NpcStringId(1800827);
		GRUNT_WHATS_WRONG_WITH_ME = new NpcStringId(1800828);
		GRUNT_OH = new NpcStringId(1800829);
		THE_GRAVE_ROBBER_WARRIOR_HAS_BEEN_FILLED_WITH_DARK_ENERGY_AND_IS_ATTACKING_YOU = new NpcStringId(1800830);
		THE_ALTAR_GUARDIAN_IS_SCRUTINIZING_YOU_NTHOSE_WHO_DARE_TO_CHALLENGE_USING_THE_POWER_OF_EVIL_SHALL_BE_PUNISHED_WITH_DEATH = new NpcStringId(1800831);
		WAIT_WAIT_STOP_SAVE_ME_AND_ILL_GIVE_YOU_10000000_ADENA = new NpcStringId(1800832);
		I_DONT_WANT_TO_FIGHT = new NpcStringId(1800833);
		IS_THIS_REALLY_NECESSARY = new NpcStringId(1800834);
		TH_THANKS_I_COULD_HAVE_BECOME_GOOD_FRIENDS_WITH_YOU = new NpcStringId(1800835);
		ILL_GIVE_YOU_10000000_ADENA_LIKE_I_PROMISED_I_MIGHT_BE_AN_ORC_WHO_KEEPS_MY_PROMISES = new NpcStringId(1800836);
		THANKS_BUT_THAT_THING_ABOUT_10000000_ADENA_WAS_A_LIE_SEE_YA = new NpcStringId(1800837);
		YOURE_PRETTY_DUMB_TO_BELIEVE_ME = new NpcStringId(1800838);
		UGH_A_CURSE_UPON_YOU = new NpcStringId(1800839);
		I_REALLY_DIDNT_WANT_TO_FIGHT = new NpcStringId(1800840);
		KASHAS_EYE_IS_SCRUTINIZING_YOU = new NpcStringId(1800841);
		THE_KASHAS_EYE_GIVES_YOU_A_STRANGE_FEELING = new NpcStringId(1800842);
		THE_EVIL_AURA_OF_THE_KASHAS_EYE_SEEMS_TO_BE_INCREASING_QUICKLY = new NpcStringId(1800843);
		I_PROTECT_THE_ALTAR_YOU_CANT_ESCAPE_THE_ALTAR = new NpcStringId(1800844);
		S1_THAT_STRANGER_MUST_BE_DEFEATED_HERE_IS_THE_ULTIMATE_HELP = new NpcStringId(1800845);
		LOOK_HERE_S1_DONT_FALL_TOO_FAR_BEHIND = new NpcStringId(1800846);
		WELL_DONE_S1_YOUR_HELP_IS_MUCH_APPRECIATED = new NpcStringId(1800847);
		WHO_HAS_AWAKENED_US_FROM_OUR_SLUMBER = new NpcStringId(1800848);
		ALL_WILL_PAY_A_SEVERE_PRICE_TO_ME_AND_THESE_HERE = new NpcStringId(1800849);
		SHYEEDS_CRY_IS_STEADILY_DYING_DOWN = new NpcStringId(1800850);
		ALERT_ALERT_DAMAGE_DETECTION_RECOGNIZED_COUNTERMEASURES_ENABLED = new NpcStringId(1800851);
		TARGET_RECOGNITION_ACHIEVED_ATTACK_SEQUENCE_COMMENCING = new NpcStringId(1800852);
		TARGET_THREAT_LEVEL_LAUNCHING_STRONGEST_COUNTERMEASURE = new NpcStringId(1800853);
		THE_PURIFICATION_FIELD_IS_BEING_ATTACKED_GUARDIAN_SPIRITS_PROTECT_THE_MAGIC_FORCE = new NpcStringId(1800854);
		PROTECT_THE_BRAZIERS_OF_PURITY_AT_ALL_COSTS = new NpcStringId(1800855);
		DEFEND_OUR_DOMAIN_EVEN_AT_RISK_OF_YOUR_OWN_LIFE = new NpcStringId(1800856);
		PEUNGLUI_MUGLANEP = new NpcStringId(1800857);
		NAIA_WAGANAGEL_PEUTAGUN = new NpcStringId(1800858);
		DRIVE_DEVICE_PARTIAL_DESTRUCTION_IMPULSE_RESULT = new NpcStringId(1800859);
		EVEN_THE_MAGIC_FORCE_BINDS_YOU_YOU_WILL_NEVER_BE_FORGIVEN = new NpcStringId(1800860);
		OH_GIANTS_AN_INTRUDER_HAS_BEEN_DISCOVERED = new NpcStringId(1800861);
		ALL_IS_VANITY_BUT_THIS_CANNOT_BE_THE_END = new NpcStringId(1800862);
		THOSE_WHO_ARE_IN_FRONT_OF_MY_EYES_WILL_BE_DESTROYED = new NpcStringId(1800863);
		I_AM_TIRED_DO_NOT_WAKE_ME_UP_AGAIN = new NpcStringId(1800864);
		_INTRUDER_DETECTED = new NpcStringId(1800865);
		THE_CANDLES_CAN_LEAD_YOU_TO_ZAKEN_DESTROY_HIM = new NpcStringId(1800866);
		WHO_DARES_AWKAWEN_THE_MIGHTY_ZAKEN = new NpcStringId(1800867);
		YE_NOT_BE_FINDING_ME_BELOW_THE_DRINK = new NpcStringId(1800868);
		YE_MUST_BE_THREE_SHEETS_TO_THE_WIND_IF_YER_LOOKIN_FOR_ME_THERE = new NpcStringId(1800869);
		YE_NOT_BE_FINDING_ME_IN_THE_CROWS_NEST = new NpcStringId(1800870);
		SORRY_BUT_THIS_IS_ALL_I_HAVE_GIVE_ME_A_BREAK = new NpcStringId(1800871);
		PEUNGLUI_MUGLANEP_NAIA_WAGANAGEL_PEUTAGUN = new NpcStringId(1800872);
		DRIVE_DEVICE_ENTIRE_DESTRUCTION_MOVING_SUSPENSION = new NpcStringId(1800873);
		AH_AH_FROM_THE_MAGIC_FORCE_NO_MORE_I_WILL_BE_FREED = new NpcStringId(1800874);
		YOU_GUYS_ARE_DETECTED = new NpcStringId(1800875);
		WHAT_KIND_OF_CREATURES_ARE_YOU = new NpcStringId(1800876);
		S2_OF_LEVEL_S1_IS_ACQUIRED = new NpcStringId(1800877);
		LIFE_STONE_FROM_THE_BEGINNING_ACQUIRED = new NpcStringId(1800878);
		WHEN_INVENTORY_WEIGHT_NUMBER_ARE_MORE_THAN_80_THE_LIFE_STONE_FROM_THE_BEGINNING_CANNOT_BE_ACQUIRED = new NpcStringId(1800879);
		YOU_ARE_UNDER_MY_THUMB = new NpcStringId(1800880);
		N21_MINUTES_ARE_ADDED_TO_THE_REMAINING_TIME_IN_THE_INSTANT_ZONE = new NpcStringId(1800881);
		HURRY_HURRY = new NpcStringId(1800882);
		I_AM_NOT_THAT_TYPE_OF_PERSON_WHO_STAYS_IN_ONE_PLACE_FOR_A_LONG_TIME = new NpcStringId(1800883);
		ITS_HARD_FOR_ME_TO_KEEP_STANDING_LIKE_THIS = new NpcStringId(1800884);
		WHY_DONT_I_GO_THAT_WAY_THIS_TIME = new NpcStringId(1800885);
		WELCOME = new NpcStringId(1800886);
		IS_THAT_IT_IS_THAT_THE_EXTENT_OF_YOUR_ABILITIES_PUT_IN_A_LITTLE_MORE_EFFORT = new NpcStringId(1800887);
		YOUR_ABILITIES_ARE_PITIFUL_YOU_ARE_FAR_FROM_A_WORTHY_OPPONENT = new NpcStringId(1800888);
		EVEN_AFTER_DEATH_YOU_ORDER_ME_TO_WANDER_AROUND_LOOKING_FOR_THE_SCAPEGOATS = new NpcStringId(1800889);
		HERE_GOES_THE_HEATSTROKE_IF_YOU_CAN_WITHSTAND_THE_HOT_HEATSTROKE_UP_TO_THE_3RD_STAGE_THE_SULTRINESS_WILL_COME_TO_YOU = new NpcStringId(1800890);
		JUST_YOU_WAIT_HUMIDITY_IS_A_BLISTERING_FIREBALL_WHICH_CAN_EASILY_WITHSTAND_PLENTY_OF_COOL_AIR_CANNON_ATTACKS = new NpcStringId(1800891);
		IN_ORDER_TO_DEFEAT_HUMIDITY_YOU_MUST_OBTAIN_THE_HEADSTROKE_PREVENTION_EFFECT_FROM_DOCTOR_ICE_AND_FIRE_MORE_THAN_10_ROUNDS_OF_THE_COOL_AIR_CANNON_ON_IT = new NpcStringId(1800892);
		YOU_ARE_HERE_S1_ILL_TEACH_YOU_A_LESSON_BRING_IT_ON = new NpcStringId(1800893);
		THATS_COLD_ISNT_IT_ONE_OF_THOSE_COOL_PACKS_I_HATE_ANYTHING_THATS_COLD = new NpcStringId(1800894);
		HUH_YOUVE_MISSED_IS_THAT_ALL_YOU_HAVE = new NpcStringId(1800895);
		I_WILL_GIVE_YOU_PRECIOUS_THINGS_THAT_I_HAVE_STOLEN_SO_STOP_BOTHERING_ME = new NpcStringId(1800896);
		I_WAS_GOING_TO_GIVE_YOU_A_JACKPOT_ITEM_YOU_DONT_HAVE_ENOUGH_INVENTORY_ROOM_SEE_YOU_NEXT_TIME = new NpcStringId(1800897);
		S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S84 = new NpcStringId(1800898);
		S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S80 = new NpcStringId(1800899);
		I_AM_NOT_HERE_FOR_YOU_YOUR_COOL_PACK_ATTACK_DOES_NOT_WORK_AGAINST_ME = new NpcStringId(1800900);
		UH_OH_WHERE_ARE_YOU_HIDING_THERE_IS_NOBODY_WHO_MATCHES_MY_SKILLS_WELL_I_GUESS_ID_BETTER_GET_GOING = new NpcStringId(1800901);
		WHY_ARE_YOU_NOT_RESPONDING_YOU_DONT_EVEN_HAVE_ANY_COOL_PACKS_YOU_CANT_FIGHT_ME = new NpcStringId(1800902);
		OH_WHERE_I_BE_WHO_CALL_ME = new NpcStringId(1800903);
		TADA_ITS_A_WATERMELON = new NpcStringId(1800904);
		ENTER_THE_WATERMELON_ITS_GONNA_GROW_AND_GROW_FROM_NOW_ON = new NpcStringId(1800906);
		OH_OUCH_DID_I_SEE_YOU_BEFORE = new NpcStringId(1800907);
		A_NEW_SEASON_SUMMER_IS_ALL_ABOUT_THE_WATERMELON = new NpcStringId(1800908);
		DID_YA_CALL_HO_THOUGHT_YOUD_GET_SOMETHING = new NpcStringId(1800909);
		DO_YOU_WANT_TO_SEE_MY_BEAUTIFUL_SELF = new NpcStringId(1800910);
		HOHOHO_LETS_DO_IT_TOGETHER = new NpcStringId(1800911);
		ITS_A_GIANT_WATERMELON_IF_YOU_RAISE_IT_RIGHT_AND_A_WATERMELON_SLICE_IF_YOU_MESS_UP = new NpcStringId(1800912);
		TADA_TRANSFORMATION_COMPLETE = new NpcStringId(1800913);
		AM_I_A_RAIN_WATERMELON_OR_A_DEFECTIVE_WATERMELON = new NpcStringId(1800914);
		NOW_IVE_GOTTEN_BIG_EVERYONE_COME_AT_ME = new NpcStringId(1800915);
		GET_BIGGER_GET_STRONGER_TELL_ME_YOUR_WISH = new NpcStringId(1800916);
		A_WATERMELON_SLICES_WISH_BUT_IM_BIGGER_ALREADY = new NpcStringId(1800917);
		A_LARGE_WATERMELONS_WISH_WELL_TRY_TO_BREAK_ME = new NpcStringId(1800918);
		IM_DONE_GROWING_IM_RUNNING_AWAY_NOW = new NpcStringId(1800919);
		IF_YOU_LET_ME_GO_ILL_GIVE_YOU_TEN_MILLION_ADENA = new NpcStringId(1800920);
		FREEDOM_WHAT_DO_YOU_THINK_I_HAVE_INSIDE = new NpcStringId(1800921);
		OK_OK_GOOD_JOB_YOU_KNOW_WHAT_TO_DO_NEXT_RIGHT = new NpcStringId(1800922);
		LOOK_HERE_DO_IT_RIGHT_YOU_SPILLED_THIS_PRECIOUS = new NpcStringId(1800923);
		AH_REFRESHING_SPRAY_A_LITTLE_MORE = new NpcStringId(1800924);
		GULP_GULP_GREAT_BUT_ISNT_THERE_MORE = new NpcStringId(1800925);
		CANT_YOU_EVEN_AIM_RIGHT_HAVE_YOU_EVEN_BEEN_TO_THE_ARMY = new NpcStringId(1800926);
		DID_YOU_MIX_THIS_WITH_WATER_WHYS_IT_TASTE_LIKE_THIS = new NpcStringId(1800927);
		OH_GOOD_DO_A_LITTLE_MORE_YEAH = new NpcStringId(1800928);
		HOHO_ITS_NOT_THERE_OVER_HERE_AM_I_SO_SMALL_THAT_YOU_CAN_EVEN_SPRAY_ME_RIGHT = new NpcStringId(1800929);
		YUCK_WHAT_IS_THIS_ARE_YOU_SURE_THIS_IS_NECTAR = new NpcStringId(1800930);
		DO_YOUR_BEST_I_BECOME_A_BIG_WATERMELON_AFTER_JUST_FIVE_BOTTLES = new NpcStringId(1800931);
		OF_COURSE_WATERMELON_IS_THE_BEST_NECTAR_HAHAHA = new NpcStringId(1800932);
		OWW_YOURE_JUST_BEATING_ME_NOW_GIVE_ME_NECTAR = new NpcStringId(1800934);
		LOOK_ITS_GONNA_BREAK = new NpcStringId(1800935);
		NOW_ARE_YOU_TRYING_TO_EAT_WITHOUT_DOING_THE_WORK_FINE_DO_WHAT_YOU_WANT_ILL_HATE_YOU_IF_YOU_DONT_GIVE_ME_ANY_NECTAR = new NpcStringId(1800936);
		HIT_ME_MORE_HIT_ME_MORE = new NpcStringId(1800937);
		IM_GONNA_WITHER_LIKE_THIS_DAMN_IT = new NpcStringId(1800938);
		HEY_YOU_IF_I_DIE_LIKE_THIS_THERELL_BE_NO_ITEM_EITHER_ARE_YOU_REALLY_SO_STINGY_WITH_THE_NECTAR = new NpcStringId(1800939);
		ITS_JUST_A_LITTLE_MORE_GOOD_LUCK = new NpcStringId(1800940);
		SAVE_ME_IM_ABOUT_TO_DIE_WITHOUT_TASTING_NECTAR_EVEN_ONCE = new NpcStringId(1800941);
		IF_I_DIE_LIKE_THIS_ILL_JUST_BE_A_WATERMELON_SLICE = new NpcStringId(1800942);
		IM_GETTING_STRONGER_I_THINK_ILL_BE_ABLE_TO_RUN_AWAY_IN_30_SECONDS_HOHO = new NpcStringId(1800943);
		ITS_GOODBYE_AFTER_20_SECONDS = new NpcStringId(1800944);
		YEAH_10_SECONDS_LEFT_9_8_7 = new NpcStringId(1800945);
		IM_LEAVING_IN_2_MINUTES_IF_YOU_DONT_GIVE_ME_ANY_NECTAR = new NpcStringId(1800946);
		IM_LEAVING_IN_1_MINUTES_IF_YOU_DONT_GIVE_ME_ANY_NECTAR = new NpcStringId(1800947);
		IM_LEAVING_NOW_THEN_GOODBYE = new NpcStringId(1800948);
		SORRY_BUT_THIS_LARGE_WATERMELON_IS_DISAPPEARING_HERE = new NpcStringId(1800949);
		TOO_LATE_HAVE_A_GOOD_TIME = new NpcStringId(1800950);
		DING_DING_THATS_THE_BELL_PUT_AWAY_YOUR_WEAPONS_AND_TRY_FOR_NEXT_TIME = new NpcStringId(1800951);
		TOO_BAD_YOU_RAISED_IT_UP_TOO = new NpcStringId(1800952);
		OH_WHAT_A_NICE_SOUND = new NpcStringId(1800953);
		THE_INSTRUMENT_IS_NICE_BUT_THERES_NO_SONG_SHALL_I_SING_FOR_YOU = new NpcStringId(1800954);
		WHAT_BEAUTIFUL_MUSIC = new NpcStringId(1800955);
		I_FEEL_GOOD_PLAY_SOME_MORE = new NpcStringId(1800956);
		MY_HEART_IS_BEING_CAPTURED_BY_THE_SOUND_OF_CRONO = new NpcStringId(1800957);
		GET_THE_NOTES_RIGHT_HEY_OLD_MAN_THAT_WAS_WRONG = new NpcStringId(1800958);
		I_LIKE_IT = new NpcStringId(1800959);
		OOH_MY_BODY_WANTS_TO_OPEN = new NpcStringId(1800960);
		OH_THIS_CHORD_MY_HEART_IS_BEING_TORN_PLAY_A_LITTLE_MORE = new NpcStringId(1800961);
		ITS_THIS_THIS_I_WANTED_THIS_SOUND_WHY_DONT_YOU_TRY_BECOMING_A_SINGER = new NpcStringId(1800962);
		YOU_CAN_TRY_A_HUNDRED_TIMES_ON_THIS_YOU_WONT_GET_ANYTHING_GOOD = new NpcStringId(1800963);
		IT_HURTS_PLAY_JUST_THE_INSTRUMENT = new NpcStringId(1800964);
		ONLY_GOOD_MUSIC_CAN_OPEN_MY_BODY = new NpcStringId(1800965);
		NOT_THIS_BUT_YOU_KNOW_THAT_WHAT_YOU_GOT_AS_A_CHRONICLE_SOUVENIR_PLAY_WITH_THAT = new NpcStringId(1800966);
		WHY_YOU_HAVE_NO_MUSIC_BORING_IM_LEAVING_NOW = new NpcStringId(1800967);
		NOT_THOSE_SHARP_THINGS_USE_THE_ONES_THAT_MAKE_NICE_SOUNDS = new NpcStringId(1800968);
		LARGE_WATERMELONS_ONLY_OPEN_WITH_MUSIC_JUST_STRIKING_WITH_A_WEAPON_WONT_WORK = new NpcStringId(1800969);
		STRIKE_WITH_MUSIC_NOT_WITH_SOMETHING_LIKE_THIS_YOU_NEED_MUSIC = new NpcStringId(1800970);
		YOURE_PRETTY_AMAZING_BUT_ITS_ALL_FOR_NOTHING = new NpcStringId(1800971);
		USE_THAT_ON_MONSTERS_OK_I_WANT_CRONO = new NpcStringId(1800972);
		EVERYONE_THE_WATERMELON_IS_BREAKING = new NpcStringId(1800973);
		ITS_LIKE_A_WATERMELON_SLICE = new NpcStringId(1800974);
		LARGE_WATERMELON_MAKE_A_WISH = new NpcStringId(1800976);
		DONT_TELL_ANYONE_ABOUT_MY_DEATH = new NpcStringId(1800977);
		UGH_THE_RED_JUICE_IS_FLOWING_OUT = new NpcStringId(1800978);
		THIS_IS_ALL = new NpcStringId(1800979);
		KYAAHH_IM_MAD = new NpcStringId(1800980);
		EVERYONE_THIS_WATERMELON_BROKE_OPEN_THE_ITEM_IS_FALLING_OUT = new NpcStringId(1800981);
		OH_IT_BURST_THE_CONTENTS_ARE_SPILLING_OUT = new NpcStringId(1800982);
		HOHOHO_PLAY_BETTER = new NpcStringId(1800983);
		OH_YOURE_VERY_TALENTED_HUH = new NpcStringId(1800984);
		PLAY_SOME_MORE_MORE_MORE_MORE = new NpcStringId(1800985);
		I_EAT_HITS_AND_GROW = new NpcStringId(1800986);
		BUCK_UP_THERE_ISNT_MUCH_TIME = new NpcStringId(1800987);
		DO_YOU_THINK_ILL_BURST_WITH_JUST_THAT = new NpcStringId(1800988);
		WHAT_A_NICE_ATTACK_YOU_MIGHT_BE_ABLE_TO_KILL_A_PASSING_FLY = new NpcStringId(1800989);
		RIGHT_THERE_A_LITTLE_TO_THE_RIGHT_AH_REFRESHING = new NpcStringId(1800990);
		YOU_CALL_THAT_HITTING_BRING_SOME_MORE_TALENTED_FRIENDS = new NpcStringId(1800991);
		DONT_THINK_JUST_HIT_WERE_HITTING = new NpcStringId(1800992);
		I_NEED_NECTAR_GOURD_NECTAR = new NpcStringId(1800993);
		I_CAN_ONLY_GROW_BY_DRINKING_NECTAR = new NpcStringId(1800994);
		GROW_ME_QUICK_IF_YOURE_GOOD_ITS_A_LARGE_WATERMELON_IF_YOURE_BAD_IT_A_WATERMELON_SLICE = new NpcStringId(1800995);
		GIMME_NECTAR_IM_HUNGRY = new NpcStringId(1800996);
		BRING_ME_NECTAR_THEN_ILL_DRINK_AND_GROW = new NpcStringId(1800998);
		YOU_WANNA_EAT_A_TINY_WATERMELON_LIKE_ME_TRY_GIVING_ME_SOME_NECTAR_ILL_GET_HUGE = new NpcStringId(1800999);
		HEHEHE_GROW_ME_WELL_AND_YOULL_GET_A_REWARD_GROW_ME_BAD_AND_WHO_KNOWS_WHATLL_HAPPEN = new NpcStringId(1801000);
		YOU_WANT_A_LARGE_WATERMELON_ID_LIKE_TO_BE_A_WATERMELON_SLICE = new NpcStringId(1801001);
		TRUST_ME_AND_BRING_ME_SOME_NECTAR_ILL_BECOME_A_LARGE_WATERMELON_FOR_YOU = new NpcStringId(1801002);
		I_SEE_BELETH_HAS_RECOVERED_ALL_OF_ITS_MAGIC_POWER_WHAT_REMAINS_HERE_IS_JUST_ITS_TRACE = new NpcStringId(1801003);
		COMMAND_CHANNEL_LEADER_S1_BELETHS_RING_HAS_BEEN_ACQUIRED = new NpcStringId(1801004);
		YOU_SUMMONED_ME_SO_YOU_MUST_BE_CONFIDENT_HUH_HERE_I_COME_JACK_GAME = new NpcStringId(1801005);
		HELLO_LETS_HAVE_A_GOOD_JACK_GAME = new NpcStringId(1801006);
		IM_STARTING_NOW_SHOW_ME_THE_CARD_YOU_WANT = new NpcStringId(1801007);
		WELL_START_NOW_SHOW_ME_THE_CARD_YOU_WANT = new NpcStringId(1801008);
		IM_SHOWING_THE_ROTTEN_PUMPKIN_CARD = new NpcStringId(1801009);
		ILL_BE_SHOWING_THE_ROTTEN_PUMPKIN_CARD = new NpcStringId(1801010);
		IM_SHOWING_THE_JACK_PUMPKIN_CARD = new NpcStringId(1801011);
		ILL_BE_SHOWING_THE_JACK_PUMPKIN_CARD = new NpcStringId(1801012);
		THATS_MY_PRECIOUS_FANTASTIC_CHOCOLATE_BANANA_ULTRA_FAVOR_CANDY_IM_DEFINITELY_WINNING_THE_NEXT_ROUND = new NpcStringId(1801013);
		ITS_MY_PRECIOUS_CANDY_BUT_ILL_HAPPILY_GIVE_IT_TO_YOU = new NpcStringId(1801014);
		THE_CANDY_FELL_ILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD = new NpcStringId(1801015);
		SINCE_THE_CANDY_FELL_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD = new NpcStringId(1801016);
		YOURE_NOT_PEEKING_AT_MY_CARD_ARE_YOU_THIS_TIME_ILL_WAGER_A_SPECIAL_SCROLL = new NpcStringId(1801017);
		WERE_GETTING_SERIOUS_NOW_IF_YOU_WIN_AGAIN_ILL_GIVE_YOU_A_SPECIAL_SCROLL = new NpcStringId(1801018);
		YOU_COULD_PROBABLY_ENTER_THE_UNDERWORLD_PRO_LEAGUE = new NpcStringId(1801019);
		EVEN_PROS_CANT_DO_THIS_MUCH_YOURE_AMAZING = new NpcStringId(1801020);
		WHOS_THE_MONSTER_HERE_THIS_TIME_ILL_BET_MY_PRECIOUS_TRANSFORMATION_STICK = new NpcStringId(1801021);
		I_LOST_AGAIN_I_WONT_LOSE_THIS_TIME_IM_BETTING_MY_TRANSFORMATION_STICK = new NpcStringId(1801022);
		LOST_AGAIN_HMPH_NEXT_TIME_ILL_BET_AN_INCREDIBLE_GIFT_WAIT_FOR_IT_IF_YOU_WANT = new NpcStringId(1801023);
		YOURE_TOO_GOOD_NEXT_TIME_ILL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_YOU = new NpcStringId(1801024);
		MY_PRIDE_CANT_HANDLE_YOU_WINNING_ANYMORE = new NpcStringId(1801025);
		I_WOULD_BE_EMBARRASSING_TO_LOSE_AGAIN_HERE = new NpcStringId(1801026);
		WHATS_YOUR_NAME_IM_GONNA_REMEMBER_YOU = new NpcStringId(1801027);
		PEOPLE_FROM_THE_ABOVE_GROUND_WORLD_ARE_REALLY_GOOD_AT_GAMES = new NpcStringId(1801028);
		YOUVE_PLAYED_A_LOT_IN_THE_UNDERWORLD_HAVENT_YOU = new NpcStringId(1801029);
		IVE_NEVER_MET_SOMEONE_SO_GOOD_BEFORE = new NpcStringId(1801030);
		N13_WINS_IN_A_ROW_YOURE_PRETTY_LUCKY_TODAY_HUH = new NpcStringId(1801031);
		I_NEVER_THOUGHT_I_WOULD_SEE_13_WINS_IN_A_ROW = new NpcStringId(1801032);
		THIS_IS_THE_HIGHEST_RECORD_IN_MY_LIFE_NEXT_TIME_ILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN = new NpcStringId(1801033);
		EVEN_PROS_CANT_DO_14_WINS_NEXT_TIME_I_WILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN = new NpcStringId(1801034);
		I_CANT_DO_THIS_ANYMORE_YOU_WIN_I_ACKNOWLEDGE_YOU_AS_THE_BEST_IVE_EVER_MET_IN_ALL_MY_583_YEARS = new NpcStringId(1801035);
		PLAYING_ANY_MORE_IS_MEANINGLESS_YOU_WERE_MY_GREATEST_OPPONENT = new NpcStringId(1801036);
		I_WON_THIS_ROUND_IT_WAS_FUN = new NpcStringId(1801037);
		I_WON_THIS_ROUND_IT_WAS_ENJOYABLE = new NpcStringId(1801038);
		ABOVE_WORLD_PEOPLE_ARE_SO_FUN_THEN_SEE_YOU_LATER = new NpcStringId(1801039);
		CALL_ME_AGAIN_NEXT_TIME_I_WANT_TO_PLAY_AGAIN_WITH_YOU = new NpcStringId(1801040);
		YOU_WANNA_PLAY_SOME_MORE_IM_OUT_OF_PRESENTS_BUT_ILL_GIVE_YOU_CANDY = new NpcStringId(1801041);
		WILL_YOU_PLAY_SOME_MORE_I_DONT_HAVE_ANY_MORE_PRESENTS_BUT_I_WILL_GIVE_YOU_CANDY_IF_YOU_WIN = new NpcStringId(1801042);
		YOURE_THE_BEST_OUT_OF_ALL_THE_JACKS_GAME_PLAYERS_IVE_EVER_MET_I_GIVE_UP = new NpcStringId(1801043);
		WOWWW_AWESOME_REALLY_I_HAVE_NEVER_MET_SOMEONE_AS_GOOD_AS_YOU_BEFORE_NOW_I_CANT_PLAY_ANYMORE = new NpcStringId(1801044);
		S1_HAS_WON_S2_JACKS_GAMES_IN_A_ROW = new NpcStringId(1801045);
		CONGRATULATIONS_S1_HAS_WON_S2_JACKS_GAMES_IN_A_ROW = new NpcStringId(1801046);
		CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACKS_GAME = new NpcStringId(1801047);
		HELLO_IM_BELLDANDY_CONGRATULATIONS_ON_WINNING_1ST_PLACE_IN_JACKS_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOULL_GET_AN_AMAZING_GIFT_LETS_PLAY_JACKS_GAME_AGAIN = new NpcStringId(1801048);
		HMM_YOURE_PLAYING_JACKS_GAME_FOR_THE_FIRST_TIME_HUH_YOU_COULDNT_EVEN_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME_MY_GOODNESS = new NpcStringId(1801049);
		OH_YOURE_NOT_VERY_FAMILIAR_WITH_JACKS_GAME_RIGHT_YOU_DIDNT_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME = new NpcStringId(1801050);
		YOU_HAVE_TO_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS = new NpcStringId(1801051);
		YOU_MUST_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS = new NpcStringId(1801052);
		IF_YOU_SHOW_THE_SAME_CARD_AS_ME_YOU_WIN_IF_THEYRE_DIFFERENT_I_WIN_UNDERSTAND_NOW_LETS_GO = new NpcStringId(1801053);
		YOU_WILL_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_ITS_MY_VICTORY_IF_THE_CARDS_ARE_DIFFERENT_WELL_LETS_START_AGAIN = new NpcStringId(1801054);
		ACK_YOU_DIDNT_SHOW_A_CARD_YOU_HAVE_TO_USE_THE_CARD_SKILL_BEFORE_THE_GAUGE_DISAPPEARS_HMPH_THEN_IM_GOING = new NpcStringId(1801055);
		AHH_YOU_DIDNT_SHOW_A_CARD_YOU_MUST_USE_THE_CARD_SKILL_AT_THE_RIGHT_TIME_ITS_UNFORTUNATE_THEN_I_WILL_GO_NOW = new NpcStringId(1801056);
		LETS_LEARN_ABOUT_THE_JACKS_GAME_TOGETHER_YOU_CAN_PLAY_WITH_ME_3_TIMES = new NpcStringId(1801057);
		LETS_START_SHOW_THE_CARD_YOU_WANT_THE_CARD_SKILL_IS_ATTACHED_TO_THE_MASK = new NpcStringId(1801058);
		YOU_SHOWED_THE_SAME_CARD_AS_ME_SO_YOU_WIN = new NpcStringId(1801059);
		YOU_SHOWED_A_DIFFERENT_CARD_FROM_ME_SO_YOU_LOSE = new NpcStringId(1801060);
		THAT_WAS_PRACTICE_SO_THERES_NO_CANDY_EVEN_IF_YOU_WIN = new NpcStringId(1801061);
		ITS_UNFORTUNATE_LETS_PRACTICE_ONE_MORE_TIME = new NpcStringId(1801062);
		YOU_GOTTA_SHOW_THE_CARD_AT_THE_RIGHT_TIME_USE_THE_CARD_SKILL_YOU_WANT_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS = new NpcStringId(1801063);
		THE_CARD_SKILLS_ARE_ATTACHED_TO_THE_JACK_OLANTERN_MASK_RIGHT_THATS_WHAT_YOU_USE = new NpcStringId(1801064);
		YOU_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_AND_I_WIN_IF_THE_CARDS_ARE_DIFFERENT_OK_LETS_GO = new NpcStringId(1801065);
		YOU_DIDNT_SHOW_A_CARD_AGAIN_WELL_TRY_AGAIN_LATER_IM_GONNA_GO_NOW = new NpcStringId(1801066);
		NOW_DO_YOU_UNDERSTAND_A_LITTLE_ABOUT_JACKS_GAME_THE_REAL_GAMES_WITH_ULDIE_AND_BELLDANDY_WELL_SEE_YOU_LATER = new NpcStringId(1801067);
		HAHAHAHA = new NpcStringId(1801068);
		WHERE_ARE_YOU_LOOKING = new NpcStringId(1801069);
		IM_RIGHT_HERE = new NpcStringId(1801070);
		ANNOYING_CONCENTRATION_ATTACKS_ARE_DISRUPTING_VALAKASS_CONCENTRATIONNIF_IT_CONTINUES_YOU_MAY_GET_A_GREAT_OPPORTUNITY = new NpcStringId(1801071);
		SOME_WARRIORS_BLOW_HAS_LEFT_A_HUGE_GASH_BETWEEN_THE_GREAT_SCALES_OF_VALAKASNVALAKASS_P_DEF_IS_GREATLY_DECREASED = new NpcStringId(1801072);
		ANNOYING_CONCENTRATION_ATTACKS_OVERWHELMED_VALAKAS_MAKING_IT_FORGET_ITS_RAGE_AND_BECOME_DISTRACTED = new NpcStringId(1801073);
		LONG_RANGE_CONCENTRATION_ATTACKS_HAVE_ENRAGED_VALAKASNIF_YOU_CONTINUE_IT_MAY_BECOME_A_DANGEROUS_SITUATION = new NpcStringId(1801074);
		BECAUSE_THE_COWARDLY_COUNTERATTACKS_CONTINUED_VALAKASS_FURY_HAS_REACHED_ITS_MAXIMUMNVALAKASS_P_ATK_IS_GREATLY_INCREASED = new NpcStringId(1801075);
		VALAKAS_HAS_BEEN_ENRAGED_BY_THE_LONG_RANGE_CONCENTRATION_ATTACKS_AND_IS_COMING_TOWARD_ITS_TARGET_WITH_EVEN_GREATER_ZEAL = new NpcStringId(1801076);
		LISTEN_OH_TANTAS_I_HAVE_RETURNED_THE_PROPHET_YUGOROS_OF_THE_BLACK_ABYSS_IS_WITH_ME_SO_DO_NOT_BE_AFRAID = new NpcStringId(1801077);
		WELCOME_S1_LET_US_SEE_IF_YOU_HAVE_BROUGHT_A_WORTHY_OFFERING_FOR_THE_BLACK_ABYSS = new NpcStringId(1801078);
		WHAT_A_FORMIDABLE_FOE_BUT_I_HAVE_THE_ABYSS_WEED_GIVEN_TO_ME_BY_THE_BLACK_ABYSS_LET_ME_SEE = new NpcStringId(1801079);
		MUHAHAHA_AH_THIS_BURNING_SENSATION_IN_MY_MOUTH_THE_POWER_OF_THE_BLACK_ABYSS_IS_REVIVING_ME = new NpcStringId(1801080);
		NO_HOW_DARE_YOU_STOP_ME_FROM_USING_THE_ABYSS_WEED_DO_YOU_KNOW_WHAT_YOU_HAVE_DONE = new NpcStringId(1801081);
		A_LIMP_CREATURE_LIKE_THIS_IS_UNWORTHY_TO_BE_AN_OFFERING_YOU_HAVE_NO_RIGHT_TO_SACRIFICE_TO_THE_BLACK_ABYSS = new NpcStringId(1801082);
		LISTEN_OH_TANTAS_THE_BLACK_ABYSS_IS_FAMISHED_FIND_SOME_FRESH_OFFERINGS = new NpcStringId(1801083);
		AH_HOW_COULD_I_LOSE_OH_BLACK_ABYSS_RECEIVE_ME = new NpcStringId(1801084);
		ALARM_SYSTEM_DESTROYED_INTRUDER_EXCLUDED = new NpcStringId(1801085);
		BEGIN_STAGE_4 = new NpcStringId(1801089);
		TIME_REMAINING_UNTIL_NEXT_BATTLE = new NpcStringId(1801090);
		THE_BEAST_ATE_THE_FEED_BUT_IT_ISNT_SHOWING_A_RESPONSE_PERHAPS_BECAUSE_ITS_ALREADY_FULL = new NpcStringId(1801091);
		THE_BEAST_SPIT_OUT_THE_FEED_INSTEAD_OF_EATING_IT = new NpcStringId(1801092);
		THE_BEAST_SPIT_OUT_THE_FEED_AND_IS_INSTEAD_ATTACKING_YOU = new NpcStringId(1801093);
		S1_IS_LEAVING_YOU_BECAUSE_YOU_DONT_HAVE_ENOUGH_GOLDEN_SPICES = new NpcStringId(1801094);
		S1_IS_LEAVING_YOU_BECAUSE_YOU_DONT_HAVE_ENOUGH_CRYSTAL_SPICES = new NpcStringId(1801095);
		S1_MAY_THE_PROTECTION_OF_THE_GODS_BE_UPON_YOU = new NpcStringId(1801096);
		FREYA_HAS_STARTED_TO_MOVE = new NpcStringId(1801097);
		HOW_COULD_I_FALL_IN_A_PLACE_LIKE_THIS = new NpcStringId(1801098);
		I_CAN_FINALLY_TAKE_A_BREATHER_BY_THE_WAY_WHO_ARE_YOU_HMM_I_THINK_I_KNOW_WHO_SENT_YOU = new NpcStringId(1801099);
		S1_OF_BALANCE = new NpcStringId(1801100);
		SWIFT_S2 = new NpcStringId(1801101);
		S1_OF_BLESSING = new NpcStringId(1801102);
		SHARP_S2 = new NpcStringId(1801103);
		USEFUL_S2 = new NpcStringId(1801104);
		RECKLESS_S2 = new NpcStringId(1801105);
		ALPEN_KOOKABURRA = new NpcStringId(1801106);
		ALPEN_COUGAR = new NpcStringId(1801107);
		ALPEN_BUFFALO = new NpcStringId(1801108);
		ALPEN_GRENDEL = new NpcStringId(1801109);
		BATTLE_END_LIMIT_TIME = new NpcStringId(1801110);
		STRONG_MAGIC_POWER_CAN_BE_FELT_FROM_SOMEWHERE = new NpcStringId(1801111);
		HOW_DARE_YOU_ATTACK_MY_RECRUITS = new NpcStringId(1801112);
		WHO_IS_DISRUPTING_THE_ORDER = new NpcStringId(1801113);
		THE_DRILLMASTER_IS_DEAD = new NpcStringId(1801114);
		LINE_UP_THE_RANKS = new NpcStringId(1801115);
		I_BROUGHT_THE_FOOD = new NpcStringId(1801116);
		COME_AND_EAT = new NpcStringId(1801117);
		LOOKS_DELICIOUS = new NpcStringId(1801118);
		LETS_GO_EAT = new NpcStringId(1801119);
		ARCHER_GIVE_YOUR_BREATH_FOR_THE_INTRUDER = new NpcStringId(1801120);
		MY_KNIGHTS_SHOW_YOUR_LOYALTY = new NpcStringId(1801121);
		I_CAN_TAKE_IT_NO_LONGER = new NpcStringId(1801122);
		ARCHER_HEED_MY_CALL = new NpcStringId(1801123);
		THE_SPACE_FEELS_LIKE_ITS_GRADUALLY_STARTING_TO_SHAKE = new NpcStringId(1801124);
		I_CAN_NO_LONGER_STAND_BY = new NpcStringId(1801125);
		TAKLACAN_IS_GATHERING_ITS_STRENGTH_AND_LAUNCHING_AN_ATTACK = new NpcStringId(1801126);
		TAKLACAN_RECEIVED_COKRAKON_AND_BECAME_WEAKER = new NpcStringId(1801127);
		COKRAKONS_POWER_CAN_BE_FELT_NEARBY = new NpcStringId(1801128);
		TAKLACAN_IS_PREPARING_TO_HIDE_ITSELF = new NpcStringId(1801129);
		TAKLACAN_DISAPPEARS_INTO_THE_SPACE_OF_FUTILITY_WITH_A_ROAR = new NpcStringId(1801130);
		TORUMBAS_BODY_IS_STARTING_TO_MOVE = new NpcStringId(1801131);
		A_RESPONSE_CAN_BE_FELT_FROM_TORUMBAS_TOUGH_SKIN = new NpcStringId(1801132);
		A_MARK_REMAINS_ON_TORUMBAS_TOUGH_SKIN = new NpcStringId(1801133);
		THE_LIGHT_IS_BEGINNING_TO_WEAKEN_IN_TORUMBAS_EYES = new NpcStringId(1801134);
		TORUMBAS_LEFT_LEG_WAS_DAMAGED = new NpcStringId(1801135);
		TORUMBAS_RIGHT_LEG_WAS_DAMAGED = new NpcStringId(1801136);
		TORUMBAS_LEFT_ARM_WAS_DAMAGED = new NpcStringId(1801137);
		TORUMBAS_RIGHT_ARM_WAS_DAMAGED = new NpcStringId(1801138);
		A_DEEP_WOUND_APPEARED_ON_TORUMBAS_TOUGH_SKIN = new NpcStringId(1801139);
		THE_LIGHT_IS_SLOWLY_FADING_FROM_TORUMBAS_EYES = new NpcStringId(1801140);
		TORUMBA_IS_PREPARING_TO_HIDE_ITS_BODY = new NpcStringId(1801141);
		TORUMBA_DISAPPEARED_INTO_HIS_SPACE = new NpcStringId(1801142);
		TORUMBA_IS_PREPARING_TO_HIDE_ITSELF_IN_THE_TWISTED_SPACE = new NpcStringId(1801143);
		DOPAGEN_HAS_STARTED_TO_MOVE = new NpcStringId(1801144);
		LEPTILIKONS_ENERGY_FEELS_LIKE_ITS_BEING_CONDENSED = new NpcStringId(1801145);
		DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF_WITH_A_STRANGE_SCENT = new NpcStringId(1801146);
		DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF = new NpcStringId(1801147);
		DOPAGEN_IS_DISAPPEARING_IN_BETWEEN_THE_TWISTED_SPACE = new NpcStringId(1801148);
		MAGUEN_APPEARANCE = new NpcStringId(1801149);
		ENOUGH_MAGUEN_PLASMA_BISTAKON_HAVE_GATHERED = new NpcStringId(1801150);
		ENOUGH_MAGUEN_PLASMA_COKRAKON_HAVE_GATHERED = new NpcStringId(1801151);
		ENOUGH_MAGUEN_PLASMA_LEPTILIKON_HAVE_GATHERED = new NpcStringId(1801152);
		THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_AND_ARE_HARMONIZED = new NpcStringId(1801153);
		THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_BUT_THEY_ARE_RAMMING_INTO_EACH_OTHER_EXPLODING_AND_DYING = new NpcStringId(1801154);
		AMAZING_S1_TOOK_100_OF_THESE_SOUL_STONE_FRAGMENTS_WHAT_A_COMPLETE_SWINDLER = new NpcStringId(1801155);
		HMM_HEY_DID_YOU_GIVE_S1_SOMETHING_BUT_IT_WAS_JUST_1_HAHA = new NpcStringId(1801156);
		S1_PULLED_ONE_WITH_S2_DIGITS_LUCKY_NOT_BAD = new NpcStringId(1801157);
		ITS_BETTER_THAN_LOSING_IT_ALL_RIGHT_OR_DOES_THIS_FEEL_WORSE = new NpcStringId(1801158);
		AHEM_S1_HAS_NO_LUCK_AT_ALL_TRY_PRAYING = new NpcStringId(1801159);
		AH_ITS_OVER_WHAT_KIND_OF_GUY_IS_THAT_DAMN_FINE_YOU_S1_TAKE_IT_AND_GET_OUTTA_HERE = new NpcStringId(1801160);
		A_BIG_PIECE_IS_MADE_UP_OF_LITTLE_PIECES_SO_HERES_A_LITTLE_PIECE = new NpcStringId(1801161);
		YOU_DONT_FEEL_BAD_RIGHT_ARE_YOU_SAD_BUT_DONT_CRY = new NpcStringId(1801162);
		OK_WHOS_NEXT_IT_ALL_DEPENDS_ON_YOUR_FATE_AND_LUCK_RIGHT_AT_LEAST_COME_AND_TAKE_A_LOOK = new NpcStringId(1801163);
		NO_ONE_ELSE_DONT_WORRY_I_DONT_BITE_HAHA = new NpcStringId(1801164);
		THERE_WAS_SOMEONE_WHO_WON_10000_FROM_ME_A_WARRIOR_SHOULDNT_JUST_BE_GOOD_AT_FIGHTING_RIGHT_YOUVE_GOTTA_BE_GOOD_IN_EVERYTHING = new NpcStringId(1801165);
		OK_MASTER_OF_LUCK_THATS_YOU_HAHA_WELL_ANYONE_CAN_COME_AFTER_ALL = new NpcStringId(1801166);
		SHEDDING_BLOOD_IS_A_GIVEN_ON_THE_BATTLEFIELD_AT_LEAST_ITS_SAFE_HERE = new NpcStringId(1801167);
		GASP = new NpcStringId(1801168);
		IS_IT_STILL_LONG_OFF = new NpcStringId(1801169);
		IS_ERMIAN_WELL_EVEN_I_CANT_BELIEVE_THAT_I_SURVIVED_IN_A_PLACE_LIKE_THIS = new NpcStringId(1801170);
		I_DONT_KNOW_HOW_LONG_ITS_BEEN_SINCE_I_PARTED_COMPANY_WITH_YOU_TIME_DOESNT_SEEM_TO_MOVE_IT_JUST_FEELS_TOO_LONG = new NpcStringId(1801171);
		SORRY_TO_SAY_THIS_BUT_THE_PLACE_YOU_STRUCK_ME_BEFORE_NOW_HURTS_GREATLY = new NpcStringId(1801172);
		UGH_IM_SORRY_IT_LOOKS_LIKE_THIS_IS_IT_FOR_ME_I_WANTED_TO_LIVE_AND_SEE_MY_FAMILY = new NpcStringId(1801173);
		WHERE_ARE_YOU_I_CANT_SEE_ANYTHING = new NpcStringId(1801174);
		WHERE_ARE_YOU_REALLY_I_CANT_FOLLOW_YOU_LIKE_THIS = new NpcStringId(1801175);
		IM_SORRY_THIS_IS_IT_FOR_ME = new NpcStringId(1801176);
		SOB_TO_SEE_ERMIAN_AGAIN_CAN_I_GO_TO_MY_FAMILY_NOW = new NpcStringId(1801177);
		MY_ENERGY_WAS_RECOVERED_SO_QUICKLY_THANKS_S1 = new NpcStringId(1801183);
		IM_STARVING = new NpcStringId(1801187);
		MAGIC_POWER_SO_STRONG_THAT_IT_COULD_MAKE_YOU_LOSE_YOUR_MIND_CAN_BE_FELT_FROM_SOMEWHERE = new NpcStringId(1801189);
		EVEN_THOUGH_YOU_BRING_SOMETHING_CALLED_A_GIFT_AMONG_YOUR_HUMANS_IT_WOULD_JUST_BE_PROBLEMATIC_FOR_ME = new NpcStringId(1801190);
		I_JUST_DONT_KNOW_WHAT_EXPRESSION_I_SHOULD_HAVE_IT_APPEARED_ON_ME_ARE_HUMANS_EMOTIONS_LIKE_THIS_FEELING = new NpcStringId(1801191);
		THE_FEELING_OF_THANKS_IS_JUST_TOO_MUCH_DISTANT_MEMORY_FOR_ME = new NpcStringId(1801192);
		BUT_I_KIND_OF_MISS_IT_LIKE_I_HAD_FELT_THIS_FEELING_BEFORE = new NpcStringId(1801193);
		I_AM_ICE_QUEEN_FREYA_THIS_FEELING_AND_EMOTION_ARE_NOTHING_BUT_A_PART_OF_MELISSAA_MEMORIES = new NpcStringId(1801194);
		DEAR_S1_THINK_OF_THIS_AS_MY_APPRECIATION_FOR_THE_GIFT_TAKE_THIS_WITH_YOU_THERES_NOTHING_STRANGE_ABOUT_IT_ITS_JUST_A_BIT_OF_MY_CAPRICIOUSNESS = new NpcStringId(1801195);
		THE_KINDNESS_OF_SOMEBODY_IS_NOT_A_BAD_FEELING_DEAR_S1_I_WILL_TAKE_THIS_GIFT_OUT_OF_RESPECT_YOUR_KINDNESS = new NpcStringId(1801196);
		FIGHTER = new NpcStringId(1811000);
		WARRIOR = new NpcStringId(1811001);
		GLADIATOR = new NpcStringId(1811002);
		WARLORD = new NpcStringId(1811003);
		KNIGHT = new NpcStringId(1811004);
		PALADIN = new NpcStringId(1811005);
		DARK_AVENGER = new NpcStringId(1811006);
		ROGUE = new NpcStringId(1811007);
		TREASURE_HUNTER = new NpcStringId(1811008);
		HAWKEYE = new NpcStringId(1811009);
		MAGE = new NpcStringId(1811010);
		WIZARD = new NpcStringId(1811011);
		SORCERER = new NpcStringId(1811012);
		NECROMANCER = new NpcStringId(1811013);
		WARLOCK = new NpcStringId(1811014);
		CLERIC = new NpcStringId(1811015);
		BISHOP = new NpcStringId(1811016);
		PROPHET = new NpcStringId(1811017);
		ELVEN_FIGHTER = new NpcStringId(1811018);
		ELVEN_KNIGHT = new NpcStringId(1811019);
		TEMPLE_KNIGHT = new NpcStringId(1811020);
		SWORD_SINGER = new NpcStringId(1811021);
		ELVEN_SCOUT = new NpcStringId(1811022);
		PLAIN_WALKER = new NpcStringId(1811023);
		SILVER_RANGER = new NpcStringId(1811024);
		ELVEN_MAGE = new NpcStringId(1811025);
		ELVEN_WIZARD = new NpcStringId(1811026);
		SPELL_SINGER = new NpcStringId(1811027);
		ELEMENTAL_SUMMONER = new NpcStringId(1811028);
		ORACLE = new NpcStringId(1811029);
		ELDER = new NpcStringId(1811030);
		DARK_FIGHTER = new NpcStringId(1811031);
		PALACE_KNIGHT = new NpcStringId(1811032);
		SHILLIEN_KNIGHT = new NpcStringId(1811033);
		BLADE_DANCER = new NpcStringId(1811034);
		ASSASSIN = new NpcStringId(1811035);
		ABYSS_WALKER = new NpcStringId(1811036);
		PHANTOM_RANGER = new NpcStringId(1811037);
		DARK_MAGE = new NpcStringId(1811038);
		DARK_WIZARD = new NpcStringId(1811039);
		SPELLHOWLER = new NpcStringId(1811040);
		PHANTOM_SUMMONER = new NpcStringId(1811041);
		SHILLIEN_ORACLE = new NpcStringId(1811042);
		SHILLIEN_ELDER = new NpcStringId(1811043);
		ORC_FIGHTER = new NpcStringId(1811044);
		ORC_RAIDER = new NpcStringId(1811045);
		DESTROYER = new NpcStringId(1811046);
		ORC_MONK = new NpcStringId(1811047);
		TYRANT = new NpcStringId(1811048);
		ORC_MAGE = new NpcStringId(1811049);
		ORC_SHAMAN = new NpcStringId(1811050);
		OVERLORD = new NpcStringId(1811051);
		WARCRYER = new NpcStringId(1811052);
		DWARVEN_FIGHTER = new NpcStringId(1811053);
		SCAVENGER = new NpcStringId(1811054);
		BOUNTY_HUNTER = new NpcStringId(1811055);
		ARTISAN = new NpcStringId(1811056);
		WARSMITH = new NpcStringId(1811057);
		DUELIST = new NpcStringId(1811088);
		DREADNOUGHT = new NpcStringId(1811089);
		PHOENIX_KNIGHT = new NpcStringId(1811090);
		HELL_KNIGHT = new NpcStringId(1811091);
		SAGITTARIUS = new NpcStringId(1811092);
		ADVENTURER = new NpcStringId(1811093);
		ARCHMAGE = new NpcStringId(1811094);
		SOULTAKER = new NpcStringId(1811095);
		ARCANA_LORD = new NpcStringId(1811096);
		CARDINAL = new NpcStringId(1811097);
		HIEROPHANT = new NpcStringId(1811098);
		EVAS_TEMPLAR = new NpcStringId(1811099);
		SWORD_MUSE = new NpcStringId(1811100);
		WIND_RIDER = new NpcStringId(1811101);
		MOONLIGHT_SENTINEL = new NpcStringId(1811102);
		MYSTIC_MUSE = new NpcStringId(1811103);
		ELEMENTAL_MASTER = new NpcStringId(1811104);
		EVAS_SAINT = new NpcStringId(1811105);
		SHILLIEN_TEMPLAR = new NpcStringId(1811106);
		SPECTRAL_DANCER = new NpcStringId(1811107);
		GHOST_HUNTER = new NpcStringId(1811108);
		GHOST_SENTINEL = new NpcStringId(1811109);
		STORM_SCREAMER = new NpcStringId(1811110);
		SPECTRAL_MASTER = new NpcStringId(1811111);
		SHILLIEN_SAINT = new NpcStringId(1811112);
		TITAN = new NpcStringId(1811113);
		GRAND_KHAVATARI = new NpcStringId(1811114);
		DOMINATOR = new NpcStringId(1811115);
		DOOM_CRYER = new NpcStringId(1811116);
		FORTUNE_SEEKER = new NpcStringId(1811117);
		MAESTRO = new NpcStringId(1811118);
		KAMAEL_SOLDIER = new NpcStringId(1811123);
		TROOPER = new NpcStringId(1811125);
		WARDER = new NpcStringId(1811126);
		BERSERKER = new NpcStringId(1811127);
		SOUL_BREAKER = new NpcStringId(1811128);
		ARBALESTER = new NpcStringId(1811130);
		DOOMBRINGER = new NpcStringId(1811131);
		SOUL_HOUND = new NpcStringId(1811132);
		TRICKSTER = new NpcStringId(1811134);
		INSPECTOR = new NpcStringId(1811135);
		JUDICATOR = new NpcStringId(1811136);
		WHOS_THERE_IF_YOU_DISTURB_THE_TEMPER_OF_THE_GREAT_LAND_DRAGON_ANTHARAS_I_WILL_NEVER_FORGIVE_YOU = new NpcStringId(1811137);
		KEHAHAHA_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR = new NpcStringId(1900000);
		WELL_I_WIN_RIGHT = new NpcStringId(1900003);
		NOW_ALL_OF_YOU_LOSERS_GET_OUT_OF_HERE = new NpcStringId(1900004);
		I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_OPPONENT = new NpcStringId(1900006);
		OH_NOT_BAD = new NpcStringId(1900007);
		AGH_THATS_NOT_WHAT_I_MEANT_TO_DO = new NpcStringId(1900008);
		CURSE_YOU_HUH_WHAT = new NpcStringId(1900009);
		SHALL_I_GO_TO_SEE_IF_SANTA_IS_STILL_THERE_HEHE = new NpcStringId(1900015);
		SANTA_CAN_GIVE_NICE_PRESENTS_ONLY_IF_HES_RELEASED_FROM_THE_TURKEY = new NpcStringId(1900017);
		OH_HO_HO_OH_HO_HO_THANK_YOU_EVERYONE_I_WILL_REPAY_YOU_FOR_SURE = new NpcStringId(1900018);
		MERRY_CHRISTMAS_WELL_DONE = new NpcStringId(1900019);
		S_I_HAVE_PREPARED_A_GIFT_FOR_YOU = new NpcStringId(1900021);
		I_HAVE_A_GIFT_FOR_S = new NpcStringId(1900022);
		TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_WILL_BE_A_BIG_PRESENT = new NpcStringId(1900024);
		WHEN_ARE_YOU_GOING_TO_STOP_IM_SLOWLY_GETTING_TIRED_OF_THIS = new NpcStringId(1900026);
		MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S_WHO_SAVED_ME = new NpcStringId(1900027);
		HOW_DARE_YOU_AWAKEN_ME_FEEL_THE_PAIN_OF_THE_FLAMES = new NpcStringId(1900028);
		WHO_DARES_OPPOSE_THE_MAJESTY_OF_FIRE = new NpcStringId(1900029);
		OH_OUCH_NO_NOT_THERE_NOT_MY_BEAD = new NpcStringId(1900030);
		CO_COLD_THATS_COLD_ACK_ACK = new NpcStringId(1900031);
		PLEASE_S_DONT_HIT_ME_PLEASE = new NpcStringId(1900032);
		KUAAANNGGG_SHAKE_IN_FEAR = new NpcStringId(1900033);
		IF_YOU_ATTACK_ME_RIGHT_NOW_YOURE_REALLY_GOING_TO_GET_IT = new NpcStringId(1900034);
		JUST_YOU_WAIT_IM_GOING_TO_SHOW_YOU_MY_KILLER_TECHNIQUE = new NpcStringId(1900035);
		YOU_DONT_DARE_ATTACK_ME = new NpcStringId(1900036);
		ITS_DIFFERENT_FROM_THE_SPIRIT_OF_FIRE_ITS_NOT_THE_SPIRIT_OF_FIRE_FEEL_MY_WRATH = new NpcStringId(1900037);
		COLD_THIS_PLACE_IS_THIS_WHERE_I_DIE = new NpcStringId(1900038);
		MY_BODY_IS_COOLING_OH_GRAN_KAIN_FORGIVE_ME = new NpcStringId(1900039);
		IDIOT_I_ONLY_INCUR_DAMAGE_FROM_BARE_HANDED_ATTACKS = new NpcStringId(1900040);
		IM_OUT_OF_CANDY_ILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD = new NpcStringId(1900051);
		SINCE_IM_OUT_OF_CANDY_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD = new NpcStringId(1900052);
		YOURE_TOO_GOOD_NEXT_TIME_ILL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_IT = new NpcStringId(1900060);
		I_WOULD_BE_EMBARRASSED_TO_LOSE_AGAIN_HERE = new NpcStringId(1900062);
		EVEN_PROS_CANT_DO_14_WINS_IN_A_ROW_NEXT_TIME_ILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN = new NpcStringId(1900070);
		I_CANT_DO_THIS_ANYMORE_YOU_WIN_IN_ALL_MY_583_YEARS_YOURE_THE_BEST_THAT_IVE_SEEN = new NpcStringId(1900071);
		S_HAS_WON_S_JACKS_GAMES_IN_A_ROW = new NpcStringId(1900081);
		CONGRATULATIONS_S_HAS_WON_S_JACKS_GAMES_IN_A_ROW = new NpcStringId(1900082);
		HELLO_IM_BELLDANDY_CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACKS_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOULL_GET_AN_AMAZING_GIFT_LETS_PLAY_JACKS_GAME_AGAIN = new NpcStringId(1900084);
		YAWN_AHH_HELLO_NICE_WEATHER_WERE_HAVING = new NpcStringId(1900104);
		AH_IM_HUNGRY_DO_YOU_HAVE_ANY_BABY_FOOD_THATS_WHAT_I_NEED_TO_EAT_TO_GET_BIGGER = new NpcStringId(1900105);
		I_GOTTA_GROW_UP_FAST_I_WANT_TO_PULL_SANTAS_SLED_TOO = new NpcStringId(1900106);
		YUMMY_I_THINK_I_CAN_GROW_UP_NOW = new NpcStringId(1900107);
		THANKS_TO_YOU_I_GREW_UP_INTO_A_BOY_RUDOLPH = new NpcStringId(1900108);
		ITS_GREAT_WEATHER_FOR_RUNNING_AROUND = new NpcStringId(1900109);
		WHATLL_I_BE_WHEN_I_GROW_UP_I_WONDER = new NpcStringId(1900110);
		IF_YOU_TAKE_GOOD_CARE_OF_ME_ILL_NEVER_FORGET_IT = new NpcStringId(1900111);
		PLEASE_PET_ME_LOVINGLY_YOU_CAN_USE_THE_HAND_OF_WARMTH_SKILL_UNDER_THE_ACTION_TAB = new NpcStringId(1900112);
		I_FEEL_GREAT_THANK_YOU = new NpcStringId(1900113);
		WOO_WHAT_A_GOOD_FEELING_I_GOTTA_GROW_MORE_AND_MORE = new NpcStringId(1900114);
		OH_YUMMY_IF_I_KEEP_EATING_THIS_I_THINK_I_CAN_GROW_UP_ALL_THE_WAY = new NpcStringId(1900115);
		YUM_YUM_DELICIOUS_GIVE_ME_MORE_OF_THIS = new NpcStringId(1900116);
		WOW_THIS_TASTE_THERES_A_WHOLE_NEW_WORLD_IN_MY_MOUTH = new NpcStringId(1900117);
		YEAH_ITS_SO_DELICIOUS_THIS_IS_THAT_STAR_FOOD = new NpcStringId(1900118);
		PAY_SOME_ATTENTION_TO_ME_HMPH = new NpcStringId(1900119);
		THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_AN_ADULT_HERE_IS_MY_GIFT = new NpcStringId(1900120);
		THANK_YOU_S_NOW_I_CAN_PULL_THE_SLED = new NpcStringId(1900121);
		S_THANK_YOU_FOR_TAKING_CARE_OF_ME_ALL_THIS_TIME_I_ENJOYED_IT_VERY_MUCH = new NpcStringId(1900122);
		S_IT_WONT_BE_LONG_NOW_UNTIL_IT_BECOMES_TIME_TO_PULL_THE_SLED_ITS_TOO_BAD = new NpcStringId(1900123);
		I_MUST_RETURN_TO_SANTA_NOW_THANK_YOU_FOR_EVERYTHING = new NpcStringId(1900124);
		HELLO_IM_A_GIRL_RUDOLPH_I_WAS_ABLE_TO_FIND_MY_TRUE_SELF_THANKS_TO_YOU = new NpcStringId(1900125);
		THIS_IS_MY_GIFT_OF_THANKS_THANK_YOU_FOR_TAKING_CARE_OF_ME = new NpcStringId(1900126);
		S_I_WAS_ALWAYS_GRATEFUL = new NpcStringId(1900127);
		IM_A_LITTLE_SAD_ITS_TIME_TO_LEAVE_NOW = new NpcStringId(1900128);
		S_THE_TIME_HAS_COME_FOR_ME_TO_RETURN_TO_MY_HOME = new NpcStringId(1900129);
		S_THANK_YOU = new NpcStringId(1900130);
		HAHAHA_I_CAPTURED_SANTA_CLAUS_HUH_WHERE_IS_THIS_WHO_ARE_YOU = new NpcStringId(1900131);
		I_LOST_AT_ROCK_PAPER_SCISSORS_AND_WAS_TAKEN_CAPTIVE_I_MIGHT_AS_WELL_TAKE_OUT_MY_ANGER_ON_YOU_HUH_WHAT = new NpcStringId(1900132);
		NOTHINGS_WORKING_IM_LEAVING_ILL_DEFINITELY_CAPTURE_SANTA_AGAIN_JUST_YOU_WAIT = new NpcStringId(1900133);
		I_MUST_RAISE_RUDOLPH_QUICKLY_THIS_YEARS_CHRISTMAS_GIFTS_HAVE_TO_BE_DELIVERED = new NpcStringId(1900134);
		MERRY_CHRISTMAS_THANKS_TO_YOUR_EFFORTS_IN_RAISING_RUDOLPH_THE_GIFT_DELIVERY_WAS_A_SUCCESS = new NpcStringId(1900135);
		IN_10_MINUTES_IT_WILL_BE_1_HOUR_SINCE_YOU_STARTED_RAISING_ME = new NpcStringId(1900136);
		AFTER_5_MINUTES_IF_MY_FULL_FEELING_AND_AFFECTION_LEVEL_REACH_99_I_CAN_GROW_BIGGER = new NpcStringId(1900137);
		LUCKY_IM_LUCKY_THE_SPIRIT_THAT_LOVES_ADENA = new NpcStringId(1900139);
		LUCKY_I_WANT_TO_EAT_ADENA_GIVE_IT_TO_ME = new NpcStringId(1900140);
		LUCKY_IF_I_EAT_TOO_MUCH_ADENA_MY_WINGS_DISAPPEAR = new NpcStringId(1900141);
		YUMMY_THANKS_LUCKY = new NpcStringId(1900142);
		GRRRR_YUCK = new NpcStringId(1900143);
		LUCKY_THE_ADENA_IS_SO_YUMMY_IM_GETTING_BIGGER = new NpcStringId(1900144);
		LUCKY_NO_MORE_ADENA_OH_IM_SO_HEAVY = new NpcStringId(1900145);
		LUCKY_IM_FULL_THANKS_FOR_THE_YUMMY_ADENA_OH_IM_SO_HEAVY = new NpcStringId(1900146);
		LUCKY_IT_WASNT_ENOUGH_ADENA_ITS_GOTTA_BE_AT_LEAST_S = new NpcStringId(1900147);
		OH_MY_WINGS_DISAPPEARED_ARE_YOU_GONNA_HIT_ME_IF_YOU_HIT_ME_ILL_THROW_UP_EVERYTHING_THAT_I_ATE = new NpcStringId(1900148);
		OH_MY_WINGS_ACK_ARE_YOU_GONNA_HIT_ME_SCARY_SCARY_IF_YOU_HIT_ME_SOMETHING_BAD_WILL_HAPPEN = new NpcStringId(1900149);
		THE_EVIL_LAND_DRAGON_ANTHARAS_HAS_BEEN_DEFEATED = new NpcStringId(1900150);
		THE_EVIL_FIRE_DRAGON_VALAKAS_HAS_BEEN_DEFEATED = new NpcStringId(1900151);
		TO_SERVE_HIM_NOW_MEANS_YOU_WILL_BE_ABLE_TO_ESCAPE_A_WORSE_SITUATION = new NpcStringId(1900152);
		OH_GODDESS_OF_DESTRUCTION_FORGIVE_US = new NpcStringId(1900153);
		WHEN_THE_SKY_TURNS_BLOOD_RED_AND_THE_EARTH_BEGINS_TO_CRUMBLE_FROM_THE_DARKNESS_SHE_WILL_RETURN = new NpcStringId(1900154);
		EARTH_ENERGY_IS_GATHERING_NEAR_ANTHARASS_LEGS = new NpcStringId(1900155);
		ANTHARAS_STARTS_TO_ABSORB_THE_EARTH_ENERGY = new NpcStringId(1900156);
		ANTHARAS_RAISES_ITS_THICK_TAIL = new NpcStringId(1900157);
		YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_ANTHARAS = new NpcStringId(1900158);
		ANTHARASS_EYES_ARE_FILLED_WITH_RAGE = new NpcStringId(1900159);
		S1_I_CAN_FEEL_THEIR_PRESENCE_FROM_YOU = new NpcStringId(1900160);
		S1_BRETHREN_COME_TO_MY_SIDE_AND_FOLLOW_ME = new NpcStringId(1900161);
		ANTHARAS_ROARS = new NpcStringId(1900162);
		FLAME_ENERGY_IS_BEING_DIRECTED_TOWARDS_VALAKAS = new NpcStringId(1900163);
		YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_VALAKAS = new NpcStringId(1900164);
		VALAKASS_TAIL_FLAILS_DANGEROUSLY = new NpcStringId(1900165);
		VALAKAS_RAISES_ITS_TAIL = new NpcStringId(1900166);
		VALAKAS_STARTS_TO_ABSORB_THE_FLAME_ENERGY = new NpcStringId(1900167);
		VALAKAS_LOOKS_TO_ITS_LEFT = new NpcStringId(1900168);
		VALAKAS_LOOKS_TO_ITS_RIGHT = new NpcStringId(1900169);
		BY_MY_AUTHORITY_I_COMMAND_YOU_CREATURE_TURN_TO_DUST = new NpcStringId(1900170);
		BY_MY_WRATH_I_COMMAND_YOU_CREATURE_LOSE_YOUR_MIND = new NpcStringId(1900171);
		SHOW_RESPECT_TO_THE_HEROES_WHO_DEFEATED_THE_EVIL_DRAGON_AND_PROTECTED_THIS_ADEN_WORLD = new NpcStringId(1900172);
		SHOUT_TO_CELEBRATE_THE_VICTORY_OF_THE_HEROES = new NpcStringId(1900173);
		PRAISE_THE_ACHIEVEMENT_OF_THE_HEROES_AND_RECEIVE_NEVITS_BLESSING = new NpcStringId(1900174);
		UGH_I_THINK_THIS_IS_IT_FOR_ME = new NpcStringId(1900175);
		VALAKAS_CALLS_FORTH_THE_SERVITORS_MASTER = new NpcStringId(1900176);
		YOU_WILL_SOON_BECOME_THE_SACRIFICE_FOR_US_THOSE_FULL_OF_DECEIT_AND_SIN_WHOM_YOU_DESPISE = new NpcStringId(1911111);
		MY_BRETHREN_WHO_ARE_STRONGER_THAN_ME_WILL_PUNISH_YOU_YOU_WILL_SOON_BE_COVERED_IN_YOUR_OWN_BLOOD_AND_CRYING_IN_ANGUISH = new NpcStringId(1911112);
		HOW_COULD_I_LOSE_AGAINST_THESE_WORTHLESS_CREATURES = new NpcStringId(1911113);
		FOOLISH_CREATURES_THE_FLAMES_OF_HELL_ARE_DRAWING_CLOSER = new NpcStringId(1911114);
		NO_MATTER_HOW_YOU_STRUGGLE_THIS_PLACE_WILL_SOON_BE_COVERED_WITH_YOUR_BLOOD = new NpcStringId(1911115);
		THOSE_WHO_SET_FOOT_IN_THIS_PLACE_SHALL_NOT_LEAVE_ALIVE = new NpcStringId(1911116);
		WORTHLESS_CREATURES_I_WILL_GRANT_YOU_ETERNAL_SLEEP_IN_FIRE_AND_BRIMSTONE = new NpcStringId(1911117);
		IF_YOU_WISH_TO_SEE_HELL_I_WILL_GRANT_YOU_YOUR_WISH = new NpcStringId(1911118);
		ELAPSED_TIME = new NpcStringId(1911119);
		TIME_REMAINING = new NpcStringId(1911120);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004431);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GLUDIO_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004432);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_DION_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004433);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GIRAN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004434);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004436);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_INNADRIL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004437);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GODDARD_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004438);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_RUNE_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004439);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_SCHUTTGART_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004440);
		IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ELMORE_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE = new NpcStringId(10004441);
		
		buildFastLookupTable();
	}
	
	private final static void buildFastLookupTable()
	{
		final Field[] fields = NpcStringId.class.getDeclaredFields();
		final ArrayList<NpcStringId> nsIds = new ArrayList<NpcStringId>(fields.length);
		
		int maxId = 0, mod;
		NpcStringId nsId;
		for (final Field field : fields)
		{
			mod = field.getModifiers();
			if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && Modifier.isFinal(mod) && field.getType().equals(NpcStringId.class))
			{
				try
				{
					nsId = (NpcStringId) field.get(null);
					nsId.setName(field.getName());
					nsId.setParamCount(parseMessageParameters(field.getName()));
					maxId = Math.max(maxId, nsId.getId());
					nsIds.add(nsId);
				}
				catch (final Exception e)
				{
					_log.log(Level.WARNING, "NpcStringId: Failed field access for '" + field.getName() + "'", e);
				}
			}
		}
		
		VALUES = new NpcStringId[maxId + 1];
		for (int i = nsIds.size(); i-- > 0;)
		{
			nsId = nsIds.get(i);
			VALUES[nsId.getId()] = nsId;
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
	
	public static final NpcStringId getNpcStringId(final int id)
	{
		final NpcStringId nsi = getNpcStringIdInternal(id);
		return nsi == null ? new NpcStringId(id) : nsi;
	}

	private static final NpcStringId getNpcStringIdInternal(final int id)
	{
		if (id < 0 || id >= VALUES.length)
			return null;
		
		return VALUES[id];
	}
	
	public static final NpcStringId getNpcStringId(final String name)
	{
		try
		{
			return (NpcStringId) NpcStringId.class.getField(name).get(null);
		}
		catch (final Exception e)
		{
			return null;
		}
	}
	
	public static final void reloadLocalisations()
	{
		for (final NpcStringId nsId : VALUES)
		{
			if (nsId != null)
				nsId.removeAllLocalisations();
		}
		
		if (!Config.L2JMOD_MULTILANG_NS_ENABLE)
		{
			_log.log(Level.INFO, "NpcStringId: MultiLanguage disabled.");
			return;
		}
		
		final List<String> languages = Config.L2JMOD_MULTILANG_NS_ALLOWED;
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setIgnoringComments(true);
		
		File file;
		Node node;
		Document doc;
		NamedNodeMap nnmb;
		NpcStringId nsId;
		String text;
		for (final String lang : languages)
		{
			file = new File(Config.DATAPACK_ROOT, "/data/lang/" + lang + "/ns/NpcStringLocalisation.xml");
			if (!file.isFile())
				continue;
			
			_log.log(Level.INFO, "NpcStringId: Loading localisation for '" + lang + "'");
			
			try
			{
				doc = factory.newDocumentBuilder().parse(file);
				for (Node na = doc.getFirstChild(); na != null; na = na.getNextSibling())
				{
					if ("list".equals(na.getNodeName()))
					{
						for (Node nb = na.getFirstChild(); nb != null; nb = nb.getNextSibling())
						{
							if ("ns".equals(nb.getNodeName()))
							{
								nnmb = nb.getAttributes();
								node = nnmb.getNamedItem("id");
								if (node != null)
								{
									nsId = getNpcStringId(Integer.parseInt(node.getNodeValue()));
									if (nsId == null)
									{
										_log.log(Level.WARNING, "NpcStringId: Unknown NSID '" + node.getNodeValue() + "', lang '" + lang + "'.");
										continue;
									}
								}
								else
								{
									node = nnmb.getNamedItem("name");
									nsId = getNpcStringId(node.getNodeValue());
									if (nsId == null)
									{
										_log.log(Level.WARNING, "NpcStringId: Unknown NSID '" + node.getNodeValue() + "', lang '" + lang + "'.");
										continue;
									}
								}
								
								node = nnmb.getNamedItem("text");
								if (node == null)
								{
									_log.log(Level.WARNING, "NpcStringId: No text defined for NSID '" + nsId + "', lang '" + lang + "'.");
									continue;
								}
								
								text = node.getNodeValue();
								if (text.isEmpty() || text.length() > 255)
								{
									_log.log(Level.WARNING, "NpcStringId: Invalid text defined for NSID '" + nsId + "' (to long or empty), lang '" + lang + "'.");
									continue;
								}
								
								nsId.attachLocalizedText(lang, text);
							}
						}
					}
				}
			}
			catch (final Exception e)
			{
				_log.log(Level.SEVERE, "NpcStringId: Failed loading '" + file + "'", e);
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
	private NSLocalisation[] _localisations;
	private ExShowScreenMessage _staticScreenMessage;
	
	private NpcStringId(final int id)
	{
		_id = id;
		_localisations = EMPTY_NSL_ARRAY;
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
			_staticScreenMessage = null;
		
		_params = (byte) params;
	}
	
	public final NSLocalisation getLocalisation(final String lang)
	{
		NSLocalisation nsl;
		for (int i = _localisations.length; i-- > 0;)
		{
			nsl = _localisations[i];
			if (nsl.getLanguage().hashCode() == lang.hashCode())
				return nsl;
		}
		return null;
	}
	
	public final void attachLocalizedText(final String lang, final String text)
	{
		final int length = _localisations.length;
		final NSLocalisation[] localisations = Arrays.copyOf(_localisations, length + 1);
		localisations[length] = new NSLocalisation(lang, text);
		_localisations = localisations;
	}
	
	public final void removeAllLocalisations()
	{
		_localisations = EMPTY_NSL_ARRAY;
	}
	
	public final ExShowScreenMessage getStaticScreenMessage()
	{
		return _staticScreenMessage;
	}
	
	public final void setStaticSystemMessage(final ExShowScreenMessage ns)
	{
		_staticScreenMessage = ns;
	}
	
	@Override
	public final String toString()
	{
		return "NS[" + getId() + ":" + getName() + "]";
	}
	
	public static final class NSLocalisation
	{
		private final String _lang;
		private final Builder _builder;
		
		public NSLocalisation(final String lang, final String text)
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