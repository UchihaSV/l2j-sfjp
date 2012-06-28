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
	 * Message: 初めまして。私は$s1です。クックッ、お前は$s2だろ？ヒッヒッヒッ。
	 */
	public static final NpcStringId HELLO_I_AM_S1_YOU_ARE_S2_RIGHT_HEHEHE;

	/**
	 * ID: 2<br>
	 * Message: $s1--$s2--$s3--$s4//$s5 ヒッヒッヒッ。
	 */
	public static final NpcStringId S1_S2_S3_S4_S5_HEHEHE;

	/**
	 * ID: 5<br>
	 * Message: $s1/$s2 $s3 時に次回の使用料を引き落とす。
	 */
	public static final NpcStringId WITHDRAW_THE_FEE_FOR_THE_NEXT_TIME_AT_S1_S2_S4;

	/**
	 * ID: 8<br>
	 * Message: 段階
	 */
	public static final NpcStringId STAGE;

	/**
	 * ID: 9<br>
	 * Message: $s1段階
	 */
	public static final NpcStringId STAGE_S1;

	/**
	 * ID: 10<br>
	 * Message: $s1%%
	 */
	public static final NpcStringId S1;

	/**
	 * ID: 2004<br>
	 * Message: 今、私に何をしたんですか。
	 */
	public static final NpcStringId WHAT_DID_YOU_JUST_DO_TO_ME;

	/**
	 * ID: 2005<br>
	 * Message: 私を手なずけようと？無理無理～
	 */
	public static final NpcStringId ARE_YOU_TRYING_TO_TAME_ME_DONT_DO_THAT;

	/**
	 * ID: 2006<br>
	 * Message: そんなものくれなくてもいいですよ。あなたが危なくなるかも？
	 */
	public static final NpcStringId DONT_GIVE_SUCH_A_THING_YOU_CAN_ENDANGER_YOURSELF;

	/**
	 * ID: 2007<br>
	 * Message: ペッペッ．．．なんだこりゃ？
	 */
	public static final NpcStringId YUCK_WHAT_IS_THIS_IT_TASTES_TERRIBLE;

	/**
	 * ID: 2008<br>
	 * Message: お腹空いた～もっとちょうだい。
	 */
	public static final NpcStringId IM_HUNGRY_GIVE_ME_A_LITTLE_MORE_PLEASE;

	/**
	 * ID: 2009<br>
	 * Message: 何これ？食べ物？
	 */
	public static final NpcStringId WHAT_IS_THIS_IS_THIS_EDIBLE;

	/**
	 * ID: 2010<br>
	 * Message: ほっといてよ。
	 */
	public static final NpcStringId DONT_WORRY_ABOUT_ME;

	/**
	 * ID: 2011<br>
	 * Message: 美味しいもの、ありがとう。
	 */
	public static final NpcStringId THANK_YOU_THAT_WAS_DELICIOUS;

	/**
	 * ID: 2012<br>
	 * Message: 段々あなたが好きになってきたよ。
	 */
	public static final NpcStringId I_THINK_I_AM_STARTING_TO_LIKE_YOU;

	/**
	 * ID: 2013<br>
	 * Message: キューキュー。
	 */
	public static final NpcStringId EEEEEK_EEEEEK;

	/**
	 * ID: 2014<br>
	 * Message: 私を手なずけるのはやめな。その気はないから。
	 */
	public static final NpcStringId DONT_KEEP_TRYING_TO_TAME_ME_I_DONT_WANT_TO_BE_TAMED;

	/**
	 * ID: 2015<br>
	 * Message: ただのエサだろう。別にあんたの手でもいいんだぜ。
	 */
	public static final NpcStringId IT_IS_JUST_FOOD_TO_ME_ALTHOUGH_IT_MAY_ALSO_BE_YOUR_HAND;

	/**
	 * ID: 2016<br>
	 * Message: こんな食べたら太っちゃう。ガツガツ。
	 */
	public static final NpcStringId IF_I_KEEP_EATING_LIKE_THIS_WONT_I_BECOME_FAT_CHOMP_CHOMP;

	/**
	 * ID: 2017<br>
	 * Message: 何で食べ物ばっかくれるのかな？
	 */
	public static final NpcStringId WHY_DO_YOU_KEEP_FEEDING_ME;

	/**
	 * ID: 2018<br>
	 * Message: 私を信じるな。裏切るかもしれない。
	 */
	public static final NpcStringId DONT_TRUST_ME_IM_AFRAID_I_MAY_BETRAY_YOU_LATER;

	/**
	 * ID: 2019<br>
	 * Message: グルル．．．
	 */
	public static final NpcStringId GRRRRR;

	/**
	 * ID: 2020<br>
	 * Message: お前が自ら招いたことだ！
	 */
	public static final NpcStringId YOU_BROUGHT_THIS_UPON_YOURSELF;

	/**
	 * ID: 2021<br>
	 * Message: おかしい！私の中によからぬ感情が！
	 */
	public static final NpcStringId I_FEEL_STRANGE_I_KEEP_HAVING_THESE_EVIL_THOUGHTS;

	/**
	 * ID: 2022<br>
	 * Message: 結局．．．こうなるんだな。
	 */
	public static final NpcStringId ALAS_SO_THIS_IS_HOW_IT_ALL_ENDS;

	/**
	 * ID: 2023<br>
	 * Message: 苦しい．．．苦しいよう．．．
	 */
	public static final NpcStringId I_DONT_FEEL_SO_GOOD_OH_MY_MIND_IS_VERY_TROUBLED;

	/**
	 * ID: 2024<br>
	 * Message: $s1、手なずけるってなんだろう？
	 */
	public static final NpcStringId S1_SO_WHAT_DO_YOU_THINK_IT_IS_LIKE_TO_BE_TAMED;

	/**
	 * ID: 2025<br>
	 * Message: $s1、スパイスを見るたびに、あんたの手がなつかしくなるよ。
	 */
	public static final NpcStringId S1_WHENEVER_I_SEE_SPICE_I_THINK_I_WILL_MISS_YOUR_HAND_THAT_USED_TO_FEED_IT_TO_ME;

	/**
	 * ID: 2026<br>
	 * Message: $s1、村には行かないで。そこまで行ける力がない。
	 */
	public static final NpcStringId S1_DONT_GO_TO_THE_VILLAGE_I_DONT_HAVE_THE_STRENGTH_TO_FOLLOW_YOU;

	/**
	 * ID: 2027<br>
	 * Message: 私を信じてくれてありがとう、$s1。あなたの役に立てるといいんだが．．．
	 */
	public static final NpcStringId THANK_YOU_FOR_TRUSTING_ME_S1_I_HOPE_I_WILL_BE_HELPFUL_TO_YOU;

	/**
	 * ID: 2028<br>
	 * Message: $s1、私があなたの役に立てるかな？
	 */
	public static final NpcStringId S1_WILL_I_BE_ABLE_TO_HELP_YOU;

	/**
	 * ID: 2029<br>
	 * Message: がんばれ！よいしょ！
	 */
	public static final NpcStringId I_GUESS_ITS_JUST_MY_ANIMAL_MAGNETISM;

	/**
	 * ID: 2030<br>
	 * Message: 食いしん坊でごめんね。もぐもぐ。
	 */
	public static final NpcStringId TOO_MUCH_SPICY_FOOD_MAKES_ME_SWEAT_LIKE_A_BEAST;

	/**
	 * ID: 2031<br>
	 * Message: あなたと息を合わせるのが楽になってきたよ。
	 */
	public static final NpcStringId ANIMALS_NEED_LOVE_TOO;

	/**
	 * ID: 2032<br>
	 * Message: 手伝ってやろう！
	 */
	public static final NpcStringId WHATD_I_MISS_WHATD_I_MISS;

	/**
	 * ID: 2033<br>
	 * Message: 天気がいいな。ピクニックでも行こうか。
	 */
	public static final NpcStringId I_JUST_KNOW_BEFORE_THIS_IS_OVER_IM_GONNA_NEED_A_LOT_OF_SERIOUS_THERAPY;

	/**
	 * ID: 2034<br>
	 * Message: これといってあなたが気に入ったわけじゃなく．．．あの、その．．．
	 */
	public static final NpcStringId I_SENSE_GREAT_WISDOM_IN_YOU_IM_AN_ANIMAL_AND_I_GOT_INSTINCTS;

	/**
	 * ID: 2035<br>
	 * Message: ここから離れるな。力を貸してやれなくなるぞ。
	 */
	public static final NpcStringId REMEMBER_IM_HERE_TO_HELP;

	/**
	 * ID: 2036<br>
	 * Message: 役に立ってるのかなぁ、私。
	 */
	public static final NpcStringId ARE_WE_THERE_YET;

	/**
	 * ID: 2037<br>
	 * Message: 食べること以外にもできるんだってば！
	 */
	public static final NpcStringId THAT_REALLY_MADE_ME_FEEL_GOOD_TO_SEE_THAT;

	/**
	 * ID: 2038<br>
	 * Message: えいっ、えいっ、えいっ、えーいっ！
	 */
	public static final NpcStringId OH_NO_NO_NO_NOOOOO;

	/**
	 * ID: 2150<br>
	 * Message: 私の眠りを妨げるのは誰だ。
	 */
	public static final NpcStringId WHO_AWOKE_ME;

	/**
	 * ID: 2151<br>
	 * Message: $s1様、ご主人様の命により、ご案内いたします。
	 */
	public static final NpcStringId MY_MASTER_HAS_INSTRUCTED_ME_TO_BE_YOUR_GUIDE_S1;

	/**
	 * ID: 2152<br>
	 * Message: $s1様、この本棚を調べてください。
	 */
	public static final NpcStringId PLEASE_CHECK_THIS_BOOKCASE_S1;

	/**
	 * ID: 2250<br>
	 * Message: $s1という名を持つ者よ、私を呼んだのはお前か．．．
	 */
	public static final NpcStringId DID_YOU_CALL_ME_S1;

	/**
	 * ID: 2251<br>
	 * Message: 意識が揺らぐ．．．もう戻らなくてはならないのか．．．
	 */
	public static final NpcStringId IM_CONFUSED_MAYBE_ITS_TIME_TO_GO_BACK;

	/**
	 * ID: 2450<br>
	 * Message: そ、その聖標は！
	 */
	public static final NpcStringId THAT_SIGN;

	/**
	 * ID: 2550<br>
	 * Message: この箱はご主人様が封じた物。 $s1 触れてはならぬぞ！
	 */
	public static final NpcStringId THAT_BOX_WAS_SEALED_BY_MY_MASTER_S1_DONT_TOUCH_IT;

	/**
	 * ID: 2551<br>
	 * Message: 不死の生命体である私を倒すとは。お前はまさか、領主の加護を受けた者！？
	 */
	public static final NpcStringId YOUVE_ENDED_MY_IMMORTAL_LIFE_YOURE_PROTECTED_BY_THE_FEUDAL_LORD_ARENT_YOU;

	/**
	 * ID: 4151<br>
	 * Message: 配達任務完了。\n初心者案内人のところに行ってください。
	 */
	public static final NpcStringId DELIVERY_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4152<br>
	 * Message: 初心者用ソウル ショット獲得。\n初心者案内人のところに行ってください。
	 */
	public static final NpcStringId ACQUISITION_OF_SOULSHOT_FOR_BEGINNERS_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4153<br>
	 * Message: 初心者用武器交換券獲得。\n初心者案内人のところに行ってください。
	 */
	public static final NpcStringId ACQUISITION_OF_WEAPON_EXCHANGE_COUPON_FOR_BEGINNERS_COMPLETE_N_GO_SPEAK_WITH_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4154<br>
	 * Message: 初心者用武器獲得。\n初心者案内人のところに行ってください。
	 */
	public static final NpcStringId ACQUISITION_OF_RACE_SPECIFIC_WEAPON_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 4155<br>
	 * Message: 最後の任務完了。\n初心者案内人のところに行ってください。
	 */
	public static final NpcStringId LAST_DUTY_COMPLETE_N_GO_FIND_THE_NEWBIE_GUIDE;

	/**
	 * ID: 6051<br>
	 * Message: $s1！お前を生かしてはおけない。恨むのなら、自分の好奇心を恨むんだな。
	 */
	public static final NpcStringId S1_I_MUST_KILL_YOU_BLAME_YOUR_OWN_CURIOSITY;

	/**
	 * ID: 6052<br>
	 * Message: 運のいいやつだ。また来るぜ。
	 */
	public static final NpcStringId YOU_HAVE_GOOD_LUCK_I_SHALL_RETURN;

	/**
	 * ID: 6053<br>
	 * Message: こんなに強いとはな。失敗だ。
	 */
	public static final NpcStringId YOU_ARE_STRONG_THIS_WAS_A_MISTAKE;

	/**
	 * ID: 6054<br>
	 * Message: どこの馬の骨かわからないやつが、なんで口出しするんだ。許さん！
	 */
	public static final NpcStringId WHO_ARE_YOU_TO_JOIN_IN_THE_BATTLE_HOW_UPSETTING;

	/**
	 * ID: 6451<br>
	 * Message: $s1、私を助けに来たのか。
	 */
	public static final NpcStringId S1_DID_YOU_COME_TO_HELP_ME;

	/**
	 * ID: 6551<br>
	 * Message: チッ！ここは違うか！
	 */
	public static final NpcStringId DRATS_HOW_COULD_I_BE_SO_WRONG;

	/**
	 * ID: 6552<br>
	 * Message: $s1！その箱から離れろ！その貨物は俺が頂こう！
	 */
	public static final NpcStringId S1_STEP_BACK_FROM_THE_CONFOUNDED_BOX_I_WILL_TAKE_IT_MYSELF;

	/**
	 * ID: 6553<br>
	 * Message: $s1！また後で戻ってくるぞ。おとなしく待っていろ！
	 */
	public static final NpcStringId S1_I_WILL_BE_BACK_SOON_STAY_THERE_AND_DONT_YOU_DARE_WANDER_OFF;

	/**
	 * ID: 6554<br>
	 * Message: くっ！俺がこんなやつにやられるとは．．．
	 */
	public static final NpcStringId GRR_IVE_BEEN_HIT;

	/**
	 * ID: 6555<br>
	 * Message: くっ！お前は何者だ！なぜ俺の邪魔をするのだ！
	 */
	public static final NpcStringId GRR_WHO_ARE_YOU_AND_WHY_HAVE_YOU_STOPPED_ME;

	/**
	 * ID: 6556<br>
	 * Message: おっと、時間が遅れてしまったな！
	 */
	public static final NpcStringId I_AM_LATE;

	/**
	 * ID: 6557<br>
	 * Message: よろしく頼むぞ！
	 */
	public static final NpcStringId GOOD_LUCK;

	/**
	 * ID: 6750<br>
	 * Message: $s1！お前たちには禁じられた知識だ！立ち去れ！
	 */
	public static final NpcStringId S1_YOU_SEEK_THE_FORBIDDEN_KNOWLEDGE_AND_I_CANNOT_LET_YOU_HAVE_IT;

	/**
	 * ID: 6751<br>
	 * Message: お前に許された時間はここまでか．．．
	 */
	public static final NpcStringId IS_THIS_ALL_I_AM_ALLOWED_TO_HAVE;

	/**
	 * ID: 6752<br>
	 * Message: 今、私を倒せば、いつかお前たちは破滅するだろう．．．
	 */
	public static final NpcStringId YOU_DEFEATED_ME_BUT_OUR_DOOM_APPROACHES;

	/**
	 * ID: 6753<br>
	 * Message: $s1！俺の部下になんてことしてくれるんだ！お前、何者だ！
	 */
	public static final NpcStringId S1_WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS;

	/**
	 * ID: 6754<br>
	 * Message: つ、強い！
	 */
	public static final NpcStringId BEEFCAKE;

	/**
	 * ID: 6755<br>
	 * Message: くっ！てめぇ何者だ！？邪魔だてするつもりか。
	 */
	public static final NpcStringId GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS;

	/**
	 * ID: 6756<br>
	 * Message: 今日はこのくらいにしといてやるが、次はただじゃおかねぇぞ！
	 */
	public static final NpcStringId FAREWELL_AND_WATCH_YOUR_BACK;

	/**
	 * ID: 6757<br>
	 * Message: カマエル！よかった！あいさつは後にして、ちょっと手を貸してくれないか．．．
	 */
	public static final NpcStringId KAMAEL_GOOD_TO_SEE_YOU_I_HAVE_SOMETHING_TO_ASK_YOU;

	/**
	 * ID: 6758<br>
	 * Message: $s1！さあ、コイツを攻撃するのだ！
	 */
	public static final NpcStringId S1_GO_GET_HIM;

	/**
	 * ID: 6759<br>
	 * Message: $s1！何をしている、討て！討つんだ！
	 */
	public static final NpcStringId S1_WHAT_ARE_YOU_DOING_ATTACK_HIM;

	/**
	 * ID: 6760<br>
	 * Message: $s1よ！お前のポテンシャルはその程度ではないはずだ。
	 */
	public static final NpcStringId S1_IS_YOUR_FULL_POTENTIAL;

	/**
	 * ID: 6761<br>
	 * Message: ありがとう！私は今すぐ敵を追いかけて行かねばならない。
	 */
	public static final NpcStringId THANKS_I_MUST_GO_AND_HUNT_DOWN_THOSE_THAT_OPPOSE_ME;

	/**
	 * ID: 6762<br>
	 * Message: しつこいやつだ．．．私はすぐにやつを追いかけなければならない．．．
	 */
	public static final NpcStringId YOU_ARE_SO_STUBBORN_I_MUST_FOLLOW_HIM_NOW;

	/**
	 * ID: 6763<br>
	 * Message: お前ならタブレットから正しい悟りを得ることができるはずだ．．．
	 */
	public static final NpcStringId SEEK_ENLIGHTENMENT_FROM_THE_TABLET;

	/**
	 * ID: 6764<br>
	 * Message: 剣を手にした無礼者よ！滅びよ！
	 */
	public static final NpcStringId ARROGANT_BEINGS_YOU_ARE_ALL_DOOMED;

	/**
	 * ID: 6765<br>
	 * Message: この次元で私に与えられた時間は終わった。運がよかったな．．．
	 */
	public static final NpcStringId MY_TIME_IN_YOUR_WORLD_HAS_COME_TO_AN_END_CONSIDER_YOURSELVES_LUCKY;

	/**
	 * ID: 6766<br>
	 * Message: $s1！神に属さぬ者たちが、おのれ！
	 */
	public static final NpcStringId S1_HOW_DARE_YOU;

	/**
	 * ID: 6767<br>
	 * Message: $s1！ぐああ！神に属さぬ者たちは危険だ．．．
	 */
	public static final NpcStringId S1_AHHAA_YOUR_GOD_FORSAKES_YOU;

	/**
	 * ID: 6851<br>
	 * Message: $s1！そこまでだ。そろそろ死んでもらわねばな。
	 */
	public static final NpcStringId S1_YOUR_TIME_IS_UP_PREPARE_TO_DIE_A_HORRIBLE_DEATH;

	/**
	 * ID: 6852<br>
	 * Message: なんと運のいいやつめ！次は生きて帰れねぇぞ。
	 */
	public static final NpcStringId CONSIDER_YOURSELF_LUCKY_THE_NEXT_TIME_WE_MEET_YOU_WILL_DIE_PERMANENTLY;

	/**
	 * ID: 6853<br>
	 * Message: 覚えていろ！次はただじゃおかねぇぞ！
	 */
	public static final NpcStringId FARE_THEE_WELL_WE_SHALL_MEET_AGAIN;

	/**
	 * ID: 6854<br>
	 * Message: $s1！俺の部下になんてことしてくれるんだ！お前、何者だ！
	 */
	public static final NpcStringId S1_WHO_ARE_YOU_AND_BETTER_YET_WHY_ARE_YOU_BOTHERING_MY_MINIONS;

	/**
	 * ID: 6855<br>
	 * Message: つ、強い！
	 */
	public static final NpcStringId STRENGTH_BEYOND_STRENGTH;

	/**
	 * ID: 6856<br>
	 * Message: くっ！てめぇ何者だ！？邪魔だてするつもりか。
	 */
	public static final NpcStringId GRR_WHY_ARE_YOU_STICKING_YOUR_NOSE_WHERE_IT_DOESNT_BELONG;

	/**
	 * ID: 6857<br>
	 * Message: 今日はこのくらいにしといてやるが、次はただじゃおかねぇぞ！
	 */
	public static final NpcStringId YOUVE_WON_FOR_NOW_BUT_WE_WILL_MEET_AGAIN;

	/**
	 * ID: 6858<br>
	 * Message: 飽きもせずによく追いかけてくるもんだ。暇か。
	 */
	public static final NpcStringId ARE_THEY_TIRED_OF_FOLLOWING_ME;

	/**
	 * ID: 6859<br>
	 * Message: $s1！俺に力を貸してくれないか。
	 */
	public static final NpcStringId S1_CAN_YOU_HELP_ME;

	/**
	 * ID: 6860<br>
	 * Message: $s1！お前の力はそんなもんか。
	 */
	public static final NpcStringId IS_THAT_ALL_YOU_GOT_LITTLE_S1;

	/**
	 * ID: 6861<br>
	 * Message: $s1！がんばれって言ってたやつが逃げるのかよ！？
	 */
	public static final NpcStringId S1_WAKE_UP_FOOL_DONT_LET_HIM_GET_AWAY;

	/**
	 * ID: 6862<br>
	 * Message: もはやお前の邪魔をするやつはいなかろう。では、さらば！
	 */
	public static final NpcStringId THE_PATH_IS_CLEAR_BUT_BE_CAREFUL;

	/**
	 * ID: 6863<br>
	 * Message: ザプキエルを追わなければ。また会おう！
	 */
	public static final NpcStringId I_MUST_FOLLOW_SOMEONE_NOW_SEE_YOU_AROUND;

	/**
	 * ID: 6864<br>
	 * Message: また会えるのを楽しみにしている。
	 */
	public static final NpcStringId MAY_WE_MEET_AGAIN;

	/**
	 * ID: 6865<br>
	 * Message: 神に刃向かった者には天罰を！
	 */
	public static final NpcStringId CURSES_ON_THOSE_WHO_BLASPHEME_THE_GODS;

	/**
	 * ID: 6866<br>
	 * Message: アインハザード様がお呼びだ！次は必ず天罰を下してやろう！
	 */
	public static final NpcStringId EINHASAD_IS_CALLING_ME_YOU_ARE_LUCKY_AND_I_SHALL_CONTINUE_MY_PUNISHMENT_LATER;

	/**
	 * ID: 6867<br>
	 * Message: $s1！神の名においてお前を裁いてやろう。
	 */
	public static final NpcStringId BY_THE_POWER_VESTED_IN_ME_BY_THE_GODS_YOU_S1_SHALL_DIE;

	/**
	 * ID: 6868<br>
	 * Message: $s1！お前を忘れないぞ！
	 */
	public static final NpcStringId S1_I_WILL_NOT_FORGET_YOU;

	/**
	 * ID: 6950<br>
	 * Message: $s1！ベレス様の腹心である俺の邪魔をするとは．．．死ね！
	 */
	public static final NpcStringId S1_HOW_DARE_YOU_INTERFERE_YOU_SHALL_PAY_FOR_THIS;

	/**
	 * ID: 6951<br>
	 * Message: おお、ベレス様が俺を呼んでいる。運がよかったな．．．
	 */
	public static final NpcStringId BELETH_IS_CALLING_ME_YOU_ARE_LUCKY_BUT_STILL_A_FOOL;

	/**
	 * ID: 6952<br>
	 * Message: 覚えてろ！俺はあきらめないからな。
	 */
	public static final NpcStringId I_SHALL_TAKE_MY_LEAVE_BUT_WILL_NEVER_SURRENDER;

	/**
	 * ID: 6954<br>
	 * Message: つ、強い！
	 */
	public static final NpcStringId COWER_BEFORE_MY_AWESOME_AND_MIGHTY_POWER;

	/**
	 * ID: 6955<br>
	 * Message: くっ！てめぇ何者だ！？邪魔だてするつもりか。
	 */
	public static final NpcStringId GRR_CANT_YOU_FIND_SOMETHING_BETTER_TO_DO_WITH_YOUR_TIME;

	/**
	 * ID: 6956<br>
	 * Message: 今日はこのくらいにしといてやるが、次はただじゃおかねぇぞ！
	 */
	public static final NpcStringId I_SHALL_TAKE_MY_LEAVE_BUT_YOUR_HEAD_WILL_BE_MINE_SOME_DAY_OH_YESYES_IT_WILL;

	/**
	 * ID: 6957<br>
	 * Message: ああ、私の子孫よ．．．
	 */
	public static final NpcStringId MY_CHILDREN_ARE_STRONGER;

	/**
	 * ID: 6958<br>
	 * Message: $s1！一緒に目の前の敵を皆殺しにしようではないか。
	 */
	public static final NpcStringId S1_LETS_KILL_THEM_ALL;

	/**
	 * ID: 6959<br>
	 * Message: $s1！がんばれ、子孫よ．．．
	 */
	public static final NpcStringId S1_COME_MY_CHILDREN;

	/**
	 * ID: 6960<br>
	 * Message: $s1！お前の潜在能力を目覚めさせるのだ．．．敵が逃げるのを防げ。
	 */
	public static final NpcStringId S1_MUSTER_YOUR_STRENGTH_PICK_THEM_OFF_LIKE_CHICKENS;

	/**
	 * ID: 6961<br>
	 * Message: ありがとう、子孫よ．．．いつかまた会う日が来るだろう。
	 */
	public static final NpcStringId THANK_YOU_MY_CHILDREN_SOMEDAY_WE_WILL_MEET_AGAIN;

	/**
	 * ID: 6962<br>
	 * Message: 子孫よ．．．私は敵を追いかけねばならない。
	 */
	public static final NpcStringId MY_CHILDREN_SEEK_MY_ENEMIES;

	/**
	 * ID: 6963<br>
	 * Message: 子孫よ．．．お前の未来に祝福を．．．
	 */
	public static final NpcStringId MY_CHILDREN_I_GRANT_YOU_MY_BLESSINGS;

	/**
	 * ID: 6964<br>
	 * Message: 存在してはならない者たちよ！
	 */
	public static final NpcStringId YOU_WORTHLESS_BEINGS;

	/**
	 * ID: 6965<br>
	 * Message: 地上で許された時間が終わったな。運がよかったな．．．
	 */
	public static final NpcStringId MY_TIME_ON_THE_MATERIAL_PLANE_HAS_ENDED_YOU_ARE_LUCKY;

	/**
	 * ID: 6966<br>
	 * Message: $s1！存在してはならない者たちよ、消えるがいい！
	 */
	public static final NpcStringId S1_WORTHLESS_BEINGS_BEGONE;

	/**
	 * ID: 6967<br>
	 * Message: $s1！やはりお前たちは危険だ．．．
	 */
	public static final NpcStringId S1_YOU_ARE_THE_MEANING_OF_THE_WORD_DANGER;

	/**
	 * ID: 7050<br>
	 * Message: よくぞここまで来たな、$s1！私の強さを証明するためだ、死ね！
	 */
	public static final NpcStringId YOU_MADE_IT_HERE_S1_ILL_SHOW_MY_STRENGTH_DIE;

	/**
	 * ID: 7051<br>
	 * Message: ハハハハ！貴様は私を倒せなかった！勝負はここまでにしておこうか。
	 */
	public static final NpcStringId HA_YOU_FAILED_ARE_YOU_READY_TO_QUIT;

	/**
	 * ID: 7052<br>
	 * Message: クッ．．．なぜだ！私は最強のはず．．．私は勝利のためにすべてを棄てたのに！
	 */
	public static final NpcStringId IM_THE_STRONGEST_I_LOST_EVERYTHING_TO_WIN;

	/**
	 * ID: 7053<br>
	 * Message: $s1！彼らが魔王ハリシャの部下だと知ってのことのようだな？
	 */
	public static final NpcStringId S1_ARE_YOU_DOING_THIS_BECAUSE_THEYRE_HALISHAS_MINIONS;

	/**
	 * ID: 7054<br>
	 * Message: 今、私の魂は千年の呪縛から解かれ、ハリシャ様のお傍へ．．．
	 */
	public static final NpcStringId MY_SPIRIT_IS_RELEASED_FROM_THIS_SHELL_IM_GETTING_CLOSE_TO_HALISHA;

	/**
	 * ID: 7055<br>
	 * Message: その方、なぜ他人の争いに首を突っ込むのだ？
	 */
	public static final NpcStringId MIND_YOUR_OWN_BUSINESS;

	/**
	 * ID: 7056<br>
	 * Message: これ以上の戦いは無駄だ．．．これでおしまいだ！
	 */
	public static final NpcStringId THIS_FIGHT_IS_A_WASTE_OF_TIME_GOODBYE;

	/**
	 * ID: 7057<br>
	 * Message: 雲がいくら重く厚かろうとも、その上には常に太陽が輝いているのは変わらぬ事実だ！
	 */
	public static final NpcStringId EVERY_CLOUD_HAS_A_SILVER_LINING_DONT_YOU_THINK;

	/**
	 * ID: 7058<br>
	 * Message: その悪魔の言葉に耳を傾けるな！$s1！
	 */
	public static final NpcStringId S1_DONT_LISTEN_TO_THIS_DEMON;

	/**
	 * ID: 7059<br>
	 * Message: $s1！迷いは心の敵だ！自らの剣を掲げて敵を斬れ！
	 */
	public static final NpcStringId S1_HESITATION_BETRAYS_YOUR_HEART_FIGHT;

	/**
	 * ID: 7060<br>
	 * Message: $s1！正義を守る、誰かを守るということが本当に価値のないことだと思っているのか。
	 */
	public static final NpcStringId S1_ISNT_PROTECTING_SOMEBODY_WORTHY_ISNT_THAT_JUSTICE;

	/**
	 * ID: 7061<br>
	 * Message: あぁ、急な用事を思い出した。それじゃ。
	 */
	public static final NpcStringId I_HAVE_URGENT_BUSINESS_I_GOTTA_GO;

	/**
	 * ID: 7062<br>
	 * Message: ここまでなのか．．．私の努力は．．．
	 */
	public static final NpcStringId ARE_MY_EFFORTS_IN_VAIN_IS_THIS_THE_END;

	/**
	 * ID: 7063<br>
	 * Message: さらばだ、友よ。いつかまた会えることを。
	 */
	public static final NpcStringId GOODBYE_FRIEND_I_HOPE_TO_SEE_YOU_AGAIN;

	/**
	 * ID: 7064<br>
	 * Message: お前ら騎士は常に愚かだな！
	 */
	public static final NpcStringId KNIGHTS_ARE_ALWAYS_FOOLISH;

	/**
	 * ID: 7065<br>
	 * Message: クククッ．．．このぐらいで見逃してやろう
	 */
	public static final NpcStringId ILL_SHOW_MERCY_ON_YOU_FOR_NOW;

	/**
	 * ID: 7066<br>
	 * Message: $s1！守ると言っておきながら守れなかったもの！全部捨ててしまったのか。お前の正義はただの偽善だ！
	 */
	public static final NpcStringId S1_YOUR_JUSTICE_IS_JUST_HYPOCRISY_IF_YOU_GIVE_UP_ON_WHAT_YOUVE_PROMISED_TO_PROTECT;

	/**
	 * ID: 7067<br>
	 * Message: $s1、勝ったと思うなよ！お前の影の中にある闇は、常にお前を見守っているんだからな．．．偽善者め！
	 */
	public static final NpcStringId S1DONT_THINK_YOUVE_WON_YOUR_DARK_SHADOW_WILL_ALWAYS_FOLLOW_YOUHYPOCRITE;

	/**
	 * ID: 7150<br>
	 * Message: テンプル ナイトとは世界樹を守るもの。$s1、長年、ヒューマンと過ごしてそれさえ忘れたのか。
	 */
	public static final NpcStringId A_TEMPLE_KNIGHT_GUARDS_THE_MOTHER_TREE_S1_HAS_HUMAN_CONTACT_MADE_YOU_FORGET_THAT;

	/**
	 * ID: 7151<br>
	 * Message: 今回はこの辺にしておこう。だが忘れるな！お前が今守っている者たちは、いつかエルフを滅ぼす仇となることを！
	 */
	public static final NpcStringId I_MUST_STOP_REMEMBER_THE_ONES_YOURE_PROTECTING_WILL_SOMEDAY_DEFEAT_THE_ELVES;

	/**
	 * ID: 7152<br>
	 * Message: クッ．．．それで貴様が得る物はなんだ？結局復讐の種を散りばめるだけじゃないか！
	 */
	public static final NpcStringId WHAT_THAT_WILL_JUST_STRENGTHEN_THE_ENEMY;

	/**
	 * ID: 7153<br>
	 * Message: 整然とした参拝地の秩序を乱す者．．．$s1に死を！
	 */
	public static final NpcStringId YOU_DARE_TO_DISTURB_THE_ORDER_OF_THE_SHRINE_DIE_S1;

	/**
	 * ID: 7154<br>
	 * Message: 今、私の魂は千年の呪縛から解かれ、ハリシャ様のお傍へ．．．
	 */
	public static final NpcStringId MY_SPIRIT_IS_RELEASING_FROM_THIS_SHELL_IM_GETTING_CLOSE_TO_HALISHA;

	/**
	 * ID: 7156<br>
	 * Message: これ以上の戦いは無駄だ．．．これでおしまいだ！
	 */
	public static final NpcStringId THIS_IS_A_WASTE_OF_TIME_GOODBYE;

	/**
	 * ID: 7157<br>
	 * Message: 私は兄パナケイアとは違う。退け！過去の亡霊よ！
	 */
	public static final NpcStringId IM_NOT_LIKE_MY_BROTHER_PANACEA_GHOST_OF_THE_PAST_BEGONE;

	/**
	 * ID: 7158<br>
	 * Message: $s1！今はエルフだけの時代じゃないんだ！私に手を貸してくれ！
	 */
	public static final NpcStringId THE_ELVES_NO_LONGER_RULE_HELP_ME_S1;

	/**
	 * ID: 7159<br>
	 * Message: 攻撃の手を緩めるな、$s1！こいつは長い執着から生まれた悪魔なんだ！
	 */
	public static final NpcStringId DONT_GIVE_UP_S1_HE_A_DEMON_FROM_THE_PAST;

	/**
	 * ID: 7160<br>
	 * Message: 誇りを持て！$s1のようにたくさんの仲間と過ごしてきた者は知っている！皆で世界を守るという誇りを！
	 */
	public static final NpcStringId BE_PROUD_S1_WE_PROTECT_THIS_WORLD_TOGETHER;

	/**
	 * ID: 7161<br>
	 * Message: さて、行かなくては。次の仕事が私を待っている。
	 */
	public static final NpcStringId I_HAVE_TO_GO_IVE_GOT_SOME_BUSINESS_TO_TAKE_CARE_OF;

	/**
	 * ID: 7162<br>
	 * Message: くっ．．．あいつを逃したら大変なことになる！
	 */
	public static final NpcStringId UGH_DONT_LET_HIM_GET_AWAY;

	/**
	 * ID: 7163<br>
	 * Message: 忘れるな。世界を守ると言う真の誇りを。
	 */
	public static final NpcStringId DONT_FORGET_YOUR_PRIDE_YOURE_PROTECTING_THE_WORLD;

	/**
	 * ID: 7164<br>
	 * Message: ぐおおおおおお。
	 */
	public static final NpcStringId HA_HA_HA;

	/**
	 * ID: 7165<br>
	 * Message: ケケケケケケケ。
	 */
	public static final NpcStringId KUH_HUH;

	/**
	 * ID: 7166<br>
	 * Message: う．．．グル．．．$s1．．．グルルル．．．
	 */
	public static final NpcStringId AAH_KUHS1KUH_HUH;

	/**
	 * ID: 7167<br>
	 * Message: $s1！き．．．おく．．．ぐおお．．．
	 */
	public static final NpcStringId S1RE_MEM_UGHUH;

	/**
	 * ID: 7250<br>
	 * Message: $s1、耳を傾けよ、私の歌に。
	 */
	public static final NpcStringId S1_YOUD_BETTER_LISTEN;

	/**
	 * ID: 7251<br>
	 * Message: ふっ、幕を閉じる時間かな。金はいらないよ。
	 */
	public static final NpcStringId HUH_ITS_CURTAIN_TIME_I_WONT_GET_ANY_MONEY;

	/**
	 * ID: 7252<br>
	 * Message: くっ．．．私の舞台が．．．
	 */
	public static final NpcStringId UGHIT_CANT_BE;

	/**
	 * ID: 7257<br>
	 * Message: 見つけたぞ。もう逃がさない！堕天使ナルシス！
	 */
	public static final NpcStringId YOU_WONT_GET_AWAY_THIS_TIME_NARCISSUS;

	/**
	 * ID: 7258<br>
	 * Message: 攻撃が．．．決まらない？$s1！手を貸してくれ！
	 */
	public static final NpcStringId S1_HELP_ME;

	/**
	 * ID: 7259<br>
	 * Message: さあ、$s1もご一緒に！聴く者を思いやらない歌は、歌っても虚しいだけ！
	 */
	public static final NpcStringId YOU_MUST_BE_AWARE_OF_YOUR_AUDIENCE_WHEN_SINGING_S_JOIN_US_S1_A_SONG_THAT_NOBODY_LISTENS_TO_IS_EMPTY;

	/**
	 * ID: 7260<br>
	 * Message: さあ元気を出して、$s1！あと少しで勝利を手にできます！
	 */
	public static final NpcStringId YOU_MUST_WORK_HARDER_TO_BE_VICTORIOUS_S1;

	/**
	 * ID: 7261<br>
	 * Message: 私の歌は終わりました。お別れの時間ですね。
	 */
	public static final NpcStringId MY_SONG_IS_OVER_I_MUST_GO_GOODBYE;

	/**
	 * ID: 7262<br>
	 * Message: 逃すとは！
	 */
	public static final NpcStringId HOW_COULD_I_MISS;

	/**
	 * ID: 7263<br>
	 * Message: 歌とは調和。一緒にすること。それを忘れないで．．．
	 */
	public static final NpcStringId DONT_FORGET_SONG_COMES_WITH_HARMONY;

	/**
	 * ID: 7264<br>
	 * Message: 歌おう。すべての者があなたの歌声に耳を傾けるでしょう。
	 */
	public static final NpcStringId SING_EVERYONE_WILL_LISTEN;

	/**
	 * ID: 7265<br>
	 * Message: 君は私の祝福を受ける資格すらないな。
	 */
	public static final NpcStringId YOU_DONT_DESERVE_MY_BLESSING;

	/**
	 * ID: 7266<br>
	 * Message: $s1？君までも私の祝福を拒むのか。
	 */
	public static final NpcStringId DO_YOU_REJECT_MY_BLESSING_S1;

	/**
	 * ID: 7267<br>
	 * Message: あぁ．．．$s1。なぜ、なぜ．．．皆が君を称えるだろうに．．．
	 */
	public static final NpcStringId BUT_WHY_S1_EVERYONE_WOULD_PRAISE_YOU;

	/**
	 * ID: 7350<br>
	 * Message: $s1！それしきの攻撃が私に通用すると思うのか。私は不死身だ！無敵だ！
	 */
	public static final NpcStringId S1_ATTACK_ME_IM_IMMORTAL_IM_UNRIVALED;

	/**
	 * ID: 7351<br>
	 * Message: ククク．．．私は不死身だ。傷などすぐ回復する．．．次に会う時こそ貴様の命日だ。
	 */
	public static final NpcStringId HA_IM_IMMORTAL_THIS_SCAR_WILL_SOON_HEAL_YOULL_DIE_NEXT_TIME;

	/**
	 * ID: 7352<br>
	 * Message: 私が負けたと？このソードマスター アイオンが！？メテルス、約束したじゃないか、私に永遠の命をくれると！
	 */
	public static final NpcStringId METELLUS_YOU_PROMISED_ME_AN_IMMORTAL_LIFE_HOW_COULD_I_SWORDMASTER_IRON_LOSE;

	/**
	 * ID: 7357<br>
	 * Message: ほう、堕天使？やり甲斐がありそうだな？
	 */
	public static final NpcStringId FALLEN_ANGEL_ITS_WORTH_TRYING;

	/**
	 * ID: 7358<br>
	 * Message: おい、$s1！見てばかりいないで一緒にやろうぜ！こりゃ手ごたえのある相手だぜ？
	 */
	public static final NpcStringId HEY_S1_WHY_DONT_YOU_JOIN_ITS_YOUR_BEST_SHOT;

	/**
	 * ID: 7359<br>
	 * Message: $s1、あんた、永遠の命に興味があるか。俺はそんなものにゃ興味ないがね！
	 */
	public static final NpcStringId ARE_YOU_INTERESTED_IN_IMMORTAL_LIFE_S1_ITS_TOO_BORING_FOR_ME;

	/**
	 * ID: 7360<br>
	 * Message: $s1、いいぞ！お前の腕を見せてやれ！試練の中で磨きぬいた実力を！
	 */
	public static final NpcStringId EXCELLENT_S1_SHOW_ME_WHAT_YOU_LEARNED_WHEN_YOUR_LIFE_WAS_ON_THE_LINE;

	/**
	 * ID: 7361<br>
	 * Message: それじゃ、お先に失礼するよ。がっつり暴れたし、ちょっと休まないとね。
	 */
	public static final NpcStringId I_HAVE_TO_GO_TAKE_A_BREAK;

	/**
	 * ID: 7362<br>
	 * Message: おい、逃したのか！あれっぽっちのやつも倒せないのか。うーーーっ！ 
	 */
	public static final NpcStringId YOU_MISSED_WHAT_A_FOOL_YOU_ARE;

	/**
	 * ID: 7363<br>
	 * Message: スリルのない戦闘、危険のない修行、逆境のない成長。それに何の意味があるんだ？
	 */
	public static final NpcStringId FIGHTING_WITHOUT_RISK_TRAINING_WITHOUT_DANGER_AND_GROWING_WITHOUT_HARDSHIP_WILL_ONLY_LEAD_TO_AN_INFLATED_EGO_DONT_FORGET;

	/**
	 * ID: 7364<br>
	 * Message: 私はメテルス。お前が望むなら永遠の命を与えよう。
	 */
	public static final NpcStringId DO_YOU_WANT_AN_IMMORTAL_LIFE;

	/**
	 * ID: 7365<br>
	 * Message: もう一度よく考えてみるんだな。永遠の命、敗北のない勝利を．．．
	 */
	public static final NpcStringId THINK_IT_OVER_AN_IMMORTAL_LIFE_AND_ASSURED_VICTORY;

	/**
	 * ID: 7366<br>
	 * Message: $s1！その技は非常に優れている。私の祝福を受けて、その腕をより一層磨いてみないか。
	 */
	public static final NpcStringId THATS_GOOD_S1_DO_YOU_WANT_MY_BLESSING_TO_IMPROVE_YOUR_SKILLS;

	/**
	 * ID: 7367<br>
	 * Message: $s1！なぜ、約束された勝利を拒むのだ！
	 */
	public static final NpcStringId S1_WHY_DO_YOU_REJECT_GUARANTEED_VICTORY;

	/**
	 * ID: 7450<br>
	 * Message: $s1、神の御名においてお前を罰する！いくら強くとも、我ら全員を相手にすることはできまい！
	 */
	public static final NpcStringId IN_THE_NAME_OF_GODS_I_PUNISH_YOU_S1_YOU_CANT_RIVAL_US_ALL_NO_MATTER_HOW_STRONG_YOU_THINK_YOU_ARE;

	/**
	 * ID: 7451<br>
	 * Message: 今回は見逃してやるが、竜の力が目覚めぬようにしてやるわ！
	 */
	public static final NpcStringId I_HAVE_TO_STOP_FOR_NOW_BUT_ILL_DEFEAT_THE_POWER_OF_THE_DRAGON_YET;

	/**
	 * ID: 7452<br>
	 * Message: くっ．．．お前は知らないのだ、目覚めてはならない力．．．それは！
	 */
	public static final NpcStringId IT_ISTHE_POWER_THAT_SHOULDNT_LIVE;

	/**
	 * ID: 7457<br>
	 * Message: 何はともあれ、天使にもなって人間たちの出来事に、いちいちチャチャ入れるのもどうかと思うんだが？
	 */
	public static final NpcStringId ISNT_IT_UNWISE_FOR_AN_ANGEL_TO_INTERFERE_IN_HUMAN_AFFAIRS;

	/**
	 * ID: 7458<br>
	 * Message: ちょっと厳しいですね．．．$s1、余裕があったら手伝ってもらえます？
	 */
	public static final NpcStringId THIS_IS_TOUGH_S1_WOULD_YOU_HELP_ME;

	/**
	 * ID: 7459<br>
	 * Message: $s1！こいつは竜の血を受け継いだ者はすべて警戒しています！あなたも例外ではありません！
	 */
	public static final NpcStringId S1_HES_KEEPING_AN_EYE_ON_ALL_THOSE_IN_SUCCESSION_TO_THE_BLOOD_OF_DRAGONS_INCLUDING_YOU;

	/**
	 * ID: 7460<br>
	 * Message: 敵の背後を取るのです、$s1！私が倒れたら、次はあなたの番です！
	 */
	public static final NpcStringId ATTACK_THE_REAR_S1_IF_IM_KILLED_YOURE_NEXT;

	/**
	 * ID: 7461<br>
	 * Message: 他人の眼が多過ぎますね、見えるところにも、見えないところにも．．．長くはいられませんね、さようなら。
	 */
	public static final NpcStringId I_CANT_STAY_ANY_LONGER_THERE_ARE_TOO_MANY_EYES_ON_US_FAREWELL;

	/**
	 * ID: 7462<br>
	 * Message: 逃げた？無事でしたね、でも．．．
	 */
	public static final NpcStringId GET_AWAY_YOURE_STILL_ALIVE_BUT;

	/**
	 * ID: 7463<br>
	 * Message: 避けることのできない戦いなら、最も強い力で制圧しなくてはなりません。それがすなわち、平和を生む唯一の道ですから。
	 */
	public static final NpcStringId IF_WE_CANT_AVOID_THIS_FIGHT_WELL_NEED_GREAT_STRENGTH_ITS_THE_ONLY_WAY_TO_PEACE;

	/**
	 * ID: 7464<br>
	 * Message: ウォーロード、竜の血を受け継ぐ者！竜の技を継がせてはならない！
	 */
	public static final NpcStringId WARLORD_YOULL_NEVER_LEARN_THE_TECHNIQUES_OF_THE_DRAGON;

	/**
	 * ID: 7465<br>
	 * Message: クッ．．．なぜ邪魔をするのだ．．．お前の母だぞ！
	 */
	public static final NpcStringId HEY_WHY_BOTHER_WITH_THIS_ISNT_IT_YOUR_MOTHERS_BUSINESS;

	/**
	 * ID: 7466<br>
	 * Message: $s1！貴様も母君に反旗を翻すのか！さすがシーレンの子供たちの技を受け継いだ虫けらだな！
	 */
	public static final NpcStringId S1_ARE_YOU_AGAINST_YOUR_MOTHERS_WISHES_YOURE_NOT_WORTHY_OF_THE_SECRETS_OF_SHILENS_CHILDREN;

	/**
	 * ID: 7467<br>
	 * Message: $s1、その技は素晴らしい。しかし一瞬にして最もたくさんの不幸を生む者、それがお前になるだろう！
	 */
	public static final NpcStringId EXCELLENT_TECHNIQUE_S1_UNFORTUNATELY_YOURE_THE_ONE_DESTINED_FOR_TRAGEDY;

	/**
	 * ID: 7550<br>
	 * Message: $s1！ここまでよくぞ追ってきた！しかし私はオークの限界を超えた！巨人と結びし契約の力を見るがいい！
	 */
	public static final NpcStringId S1_YOU_MAY_FOLLOW_ME_BUT_AN_ORC_IS_NO_MATCH_FOR_MY_GIANTS_STRENGTH;

	/**
	 * ID: 7551<br>
	 * Message: うっ．．．体が．．．ここまでか．．．
	 */
	public static final NpcStringId KUHMY_BODY_FAILSTHIS_IS_THE_END;

	/**
	 * ID: 7552<br>
	 * Message: 私は負けぬ！巨人の力を手に入れたこの私は！ぐあああああああ！
	 */
	public static final NpcStringId HOW_COULD_I_LOSE_WITH_THE_POWERS_OF_A_GIANT_AARGH;

	/**
	 * ID: 7557<br>
	 * Message: あれが敵なのか。
	 */
	public static final NpcStringId THATS_THE_ENEMY;

	/**
	 * ID: 7558<br>
	 * Message: ふん！$s1、見ているだけなのか。
	 */
	public static final NpcStringId HMM_S1_WILL_YOU_JUST_STAND_THERE_DOING_NOTHING;

	/**
	 * ID: 7559<br>
	 * Message: $s1、勝利は与えられるものじゃない。戦いに身を投じた者だけが勝利を得る資格を持つんだ。
	 */
	public static final NpcStringId S1_NOTHING_RISKED_NOTHING_GAINED_ONLY_THOSE_WHO_FIGHT_ENJOY_THE_SPOILS_OF_VICTORY;

	/**
	 * ID: 7560<br>
	 * Message: 剣は飾りじゃないぞ。$s1、お前もそう思わないか。
	 */
	public static final NpcStringId A_SWORD_ISNT_JEWELRY_S1_DONT_YOU_AGREE;

	/**
	 * ID: 7561<br>
	 * Message: ふむ、戦いはないのか。私がいる理由はないな。
	 */
	public static final NpcStringId WITH_NO_FIGHT_I_HAVE_NO_REASON_TO_STAY;

	/**
	 * ID: 7562<br>
	 * Message: 逃したか．．．
	 */
	public static final NpcStringId MISS;

	/**
	 * ID: 7563<br>
	 * Message: 自らが決断した戦いにのみ身を投じろ。じゃないと後悔することになるぞ。
	 */
	public static final NpcStringId PICK_YOUR_BATTLES_WISELY_OR_YOULL_REGRET_IT;

	/**
	 * ID: 7564<br>
	 * Message: 愚 か な オ ロ カ 族 よ 巨 人 の 力 に 歯 向 か う の か。
	 */
	public static final NpcStringId WHAT_A_FOOL_TO_CHALLENGE_THE_GIANT_OF_THE_OROKA_TRIBE;

	/**
	 * ID: 7565<br>
	 * Message: エ ネ ル ギ ー 不 足 だ。 一 旦 退 こ う。
	 */
	public static final NpcStringId RUNNING_LOW_ON_STEAM_I_MUST_WITHDRAW;

	/**
	 * ID: 7566<br>
	 * Message: $s1 ガ ー デ ィ ア ン ム ハ ー ク を 倒 し た 者。
	 */
	public static final NpcStringId S1_YOURE_THE_ONE_WHO_DEFEATED_GUARDIAN_MUHARK;

	/**
	 * ID: 7567<br>
	 * Message: $s1！倒 さ な け れ ば な ら ぬ ．．．
	 */
	public static final NpcStringId S1_I_MUST_SUCCEED;

	/**
	 * ID: 7650<br>
	 * Message: $s1、このウルズに追いついた実力だけは認めてやろう。それにしても、本当にアズリアの力に到達した私を相手に戦おうというのか。
	 */
	public static final NpcStringId S1_WOULD_YOU_FIGHT_URUZ_WHO_HAS_REACHED_THE_POWER_OF_AZIRA;

	/**
	 * ID: 7651<br>
	 * Message: ふう．．．まだアズリアの力を完全にコントロールするのは難しいな。
	 */
	public static final NpcStringId I_CANT_HANDLE_THE_POWER_OF_AZIRA_YET_FIRST;

	/**
	 * ID: 7652<br>
	 * Message: そんな！アズリアの力を受け継いだ私が、炎の影に達した私が負けるなんて．．．
	 */
	public static final NpcStringId THIS_CANT_BE_HAPPENING_I_HAVE_THE_POWER_OF_AZIRA_HOW_COULD_I_FALL_SO_EASILY;

	/**
	 * ID: 7657<br>
	 * Message: アズリア！邪悪な炎より生まれた者、私がこの拳で沈めてやろう！
	 */
	public static final NpcStringId AZIRA_BORN_FROM_THE_EVIL_FLAME_ILL_KILL_YOU_WITH_MY_BARE_HANDS;

	/**
	 * ID: 7658<br>
	 * Message: おい！$s1！お前もカバタリ フバイの名を知っているなら、この死霊に一発かましてやれ！
	 */
	public static final NpcStringId S1_IN_THE_NAME_OF_KHAVATARI_HUBAI_STRIKE_THIS_EVIL_WITH_YOUR_FISTS;

	/**
	 * ID: 7659<br>
	 * Message: $s1！挟み撃ちだ！心を拳に込めて、撃て！
	 */
	public static final NpcStringId S1_ATTACK_FROM_BOTH_SIDES_HIT_HARD;

	/**
	 * ID: 7660<br>
	 * Message: $s1！お前の心がこもった拳なら、間違いなく炎の化身にも通用するだろう！
	 */
	public static final NpcStringId S1_STRIKE_WITH_ALL_YOUR_HEART_IT_MUST_WORK;

	/**
	 * ID: 7661<br>
	 * Message: んっ！？もう行かなければ。後でまた会おう。
	 */
	public static final NpcStringId DAMN_ITS_TIME_TO_GO_UNTIL_NEXT_TIME;

	/**
	 * ID: 7662<br>
	 * Message: さすがは炎の死霊．．．しかし私は諦めないぞ！
	 */
	public static final NpcStringId EVIL_SPIRIT_OF_FLAME_I_WONT_GIVE_UP;

	/**
	 * ID: 7663<br>
	 * Message: 死霊をも打ちのめしたその拳、忘れないように！
	 */
	public static final NpcStringId MY_FIST_WORKS_EVEN_ON_THE_EVIL_SPIRIT_DONT_FORGET;

	/**
	 * ID: 7664<br>
	 * Message: 愚かなカバタリ．．．お前たちの技の源である私に、その力が通用すると思っているのか。
	 */
	public static final NpcStringId FOOLISH_KHAVATARIDO_YOU_THINK_YOUR_POWER_WILL_WORK_ON_ME_IM_THE_SOURCE_OF_YOUR_MARTIAL_ART;

	/**
	 * ID: 7665<br>
	 * Message: もうお遊びはやめにしよう．．．
	 */
	public static final NpcStringId NO_MORE_GAMES;

	/**
	 * ID: 7666<br>
	 * Message: $s1、カバタリの道を歩む者か。私がどんな存在か知って戦っているのか。
	 */
	public static final NpcStringId S1ARE_YOU_NEXT_AFTER_KHAVATARI_DO_YOU_KNOW_WHO_I_AM;

	/**
	 * ID: 7667<br>
	 * Message: $s1、カシュ．．．まあまあの攻撃だな。今の肉体では耐えられないほど．．．
	 */
	public static final NpcStringId S1KASHU_NOT_A_BAD_ATTACK_I_CANT_HOLD_ON_MUCH_LONGER;

	/**
	 * ID: 7750<br>
	 * Message: $s1も、アカンも、私の好敵手にはなれない！皆殺しだ！王になるのはこの私だ！
	 */
	public static final NpcStringId S1_AKKAN_YOU_CANT_BE_MY_RIVAL_ILL_KILL_EVERYTHING_ILL_BE_THE_KING;

	/**
	 * ID: 7751<br>
	 * Message: ハハハハ！今回は見逃してやろう！貴様の実力はよーくわかったぞ！
	 */
	public static final NpcStringId HA_ILL_SHOW_MERCY_ON_YOU_THIS_TIME_I_KNOW_WELL_OF_YOUR_TECHNIQUE;

	/**
	 * ID: 7752<br>
	 * Message: 支配者の血を受け継いだ私が！？こんな無様に負けるなんて！？
	 */
	public static final NpcStringId I_HAVE_IN_ME_THE_BLOOD_OF_A_KING_HOW_COULD_I_LOSE;

	/**
	 * ID: 7757<br>
	 * Message: 暴君．．．なのか！？
	 */
	public static final NpcStringId ARE_YOUTYRANT;

	/**
	 * ID: 7758<br>
	 * Message: おまえなんか王じゃない！ただの暴君だ！$s1、力を合わせよう！
	 */
	public static final NpcStringId YOURE_NOT_A_KING_YOURE_JUST_A_TYRANT_S1_WE_MUST_FIGHT_TOGETHER;

	/**
	 * ID: 7759<br>
	 * Message: そんなんだから国が滅びるんだろう！$s1、こんな暴政を放っておけないじゃないか！？
	 */
	public static final NpcStringId SUCH_RULE_IS_RUINING_THE_COUNTRY_S1_I_CANT_BEAR_THIS_TYRANNY_ANY_LONGER;

	/**
	 * ID: 7760<br>
	 * Message: $s1、がんばるんだ！暴政に抗うのは常に指導者の役目なんだぞ！
	 */
	public static final NpcStringId S1_LEADERS_MUST_ALWAYS_RESIST_TYRANNY;

	/**
	 * ID: 7761<br>
	 * Message: あちゃー、長居し過ぎたな。長いこと留守にし過ぎだって怒られそうだな？
	 */
	public static final NpcStringId I_STAYED_TOO_LONG_ILL_BE_PUNISHED_FOR_BEING_AWAY_SO_LONG;

	/**
	 * ID: 7762<br>
	 * Message: 逃げたのか．．．クソッ、ああいうネクラなやつは早くとっ捕まえておかないと！
	 */
	public static final NpcStringId HE_GOT_AWAY_DAMMIT_WE_MUST_CATCH_THIS_DARK_SOUL;

	/**
	 * ID: 7763<br>
	 * Message: それにしても、王って何だ？いい王になるにはどうすればいいんだ？お前も一緒に考えてくれないか。
	 */
	public static final NpcStringId WHAT_IS_A_KING_WHAT_MUST_ONE_DO_TO_BE_A_GOOD_KING_THINK_IT_OVER;

	/**
	 * ID: 7764<br>
	 * Message: 私の前にひざまずけ！愚かな民よ！
	 */
	public static final NpcStringId KNEEL_DOWN_BEFORE_ME_FOOLISH_PEOPLE;

	/**
	 * ID: 7765<br>
	 * Message: 王が立ち退かれる時が来た！頭を下げて王を見送れ！
	 */
	public static final NpcStringId ITS_TIME_FOR_THE_KING_TO_LEAVE_BOW_YOUR_HEAD_AND_SAY_GOODBYE;

	/**
	 * ID: 7766<br>
	 * Message: $s1！この私に逆らうのか！王は支配者！支配とは力！強い力こそが支配に繋がるのだ！
	 */
	public static final NpcStringId S1_YOU_DARE_TO_FIGHT_ME_A_KINGS_POWER_MUST_BE_GREAT_TO_RULE;

	/**
	 * ID: 7767<br>
	 * Message: 貴様ごときが王を斬るとは！$s1、裏切ったな！
	 */
	public static final NpcStringId YOU_WOULD_FIGHT_THE_KING_S1_TRAITOR;

	/**
	 * ID: 7850<br>
	 * Message: デジャカル サルヒ！$s1、サルヒの口 ムダハの威力を見よ！
	 */
	public static final NpcStringId TEJAKAR_SHARUHI_S1_ILL_SHOW_YOU_THE_POWER_OF_SHARUHI_MOUTH_MUDAHA;

	/**
	 * ID: 7851<br>
	 * Message: きゃああああっ！静かにしてろってば！お前の志は私が．．．くそっ、ひとまずは後退だ！
	 */
	public static final NpcStringId AAARGH_MY_SOUL_WONT_KEEP_QUIET_NOW_I_MUST_TAKE_MY_LEAVE;

	/**
	 * ID: 7852<br>
	 * Message: だめだ、サルヒ。お前は私のものだ！私のものなんだあああああっ！
	 */
	public static final NpcStringId NO_SHARUHI_YOURE_SOUL_IS_MINE;

	/**
	 * ID: 7857<br>
	 * Message: デジャカル オロカ！デジャカル ドゥダーマラ！
	 */
	public static final NpcStringId TEJAKAR_OROCA_TEJAKAR_DUDA_MARA;

	/**
	 * ID: 7858<br>
	 * Message: $s1、永き冬と戦う準備が整ったなら、私と一緒にあの霊魂を鎮めよう！
	 */
	public static final NpcStringId S1_WE_MUST_FIGHT_THIS_SOUL_TOGETHER_TO_PREVENT_AN_EVERLASTING_WINTER;

	/**
	 * ID: 7859<br>
	 * Message: $s1！あの霊魂はそなたに反応する者！そなたの攻撃があれを鎮めるだろう！
	 */
	public static final NpcStringId S1_THE_SOUL_RESPONDS_TO_YOU_MAY_YOUR_ATTACK_QUIET_HIM;

	/**
	 * ID: 7860<br>
	 * Message: $s1！サルヒを鎮めるんだ！やつはすでに私の言うことは聞かぬ！
	 */
	public static final NpcStringId S1_CALM_SHARUHI_HE_DOESNT_LISTEN_TO_ME_ANYMORE;

	/**
	 * ID: 7861<br>
	 * Message: 旅立つ時が来た！汝に熱い炎の加護のあらんことを！
	 */
	public static final NpcStringId ITS_TIME_TO_GO_MAY_THE_ETERNAL_FLAME_BLESS_YOU;

	/**
	 * ID: 7862<br>
	 * Message: 彼は去った．．．残念だ．．．残念．．．
	 */
	public static final NpcStringId HE_LEFTTHATS_TOO_BADTOO_BAD;

	/**
	 * ID: 7863<br>
	 * Message: 彼を静めた今の心、忘れずにな．．．
	 */
	public static final NpcStringId DONT_FORGET_YOUR_STRONG_WILL_NOW;

	/**
	 * ID: 7864<br>
	 * Message: シャアアアッ！私は2度と誰の支配も受けぬ！
	 */
	public static final NpcStringId HA_NOBODY_WILL_RULE_OVER_ME_ANYMORE;

	/**
	 * ID: 7865<br>
	 * Message: 自由、自由、じゆううううううう！
	 */
	public static final NpcStringId FREEDOM_FREEDOM_FREEDOM;

	/**
	 * ID: 7866<br>
	 * Message: $s1、私を解き放ってくれたお前も、結局は私を閉じ込めようとしているのだな！シャアアアアッ！
	 */
	public static final NpcStringId S1_YOU_RELEASED_ME_BUT_YOU_ALSO_WANT_TO_CATCH_ME_HA;

	/**
	 * ID: 7867<br>
	 * Message: $s1、お前は私のことを思ってくれているのだな．．．よかろう．．．お前に私の力を貸そう．．．
	 */
	public static final NpcStringId S1MEALL_RIGHTILL_HELP_YOU;

	/**
	 * ID: 7950<br>
	 * Message: 退け！ここは神が禁じた場所だ！
	 */
	public static final NpcStringId GET_OUT_OF_HERE_THIS_PLACE_IS_FORBIDDEN_BY_GOD;

	/**
	 * ID: 7951<br>
	 * Message: アインハザード様が私をお呼びだ。
	 */
	public static final NpcStringId EINHASAD_IS_CALLING_ME;

	/**
	 * ID: 7952<br>
	 * Message: 私を殺すとは．．．神の呪いが怖くないのか。
	 */
	public static final NpcStringId YOU_KILLED_ME_ARENT_YOU_AFRAID_OF_GODS_CURSE;

	/**
	 * ID: 7953<br>
	 * Message: 私の部下を．．．$s1、死にたいのか！
	 */
	public static final NpcStringId YOU_BOTHER_MY_MINIONS_S1_DO_YOU_WANT_TO_DIE;

	/**
	 * ID: 7954<br>
	 * Message: こんなはずが．．．私が．．．負けるとは。
	 */
	public static final NpcStringId WHAT_THE_HELL_I_LOST;

	/**
	 * ID: 7955<br>
	 * Message: 誰だ貴様は！邪魔するな！
	 */
	public static final NpcStringId WHO_ARE_YOU_WHY_ARE_YOU_INTERFERING_IN_OUR_BUSINESS;

	/**
	 * ID: 7956<br>
	 * Message: 強い．．．しかし次は容赦せぬぞ！
	 */
	public static final NpcStringId YOURE_STRONG_ILL_GET_YOU_NEXT_TIME;

	/**
	 * ID: 7957<br>
	 * Message: また会ったな．．．今度こそは倒してやる！
	 */
	public static final NpcStringId WE_MEET_AGAIN_ILL_HAVE_YOU_THIS_TIME;

	/**
	 * ID: 7958<br>
	 * Message: やはり強い．．．おい、$s1、私を手伝ってくれ。
	 */
	public static final NpcStringId A_WORTHY_OPPONENT_S1_HELP_ME;

	/**
	 * ID: 7959<br>
	 * Message: $s1！やつが逃げる前に急げ。
	 */
	public static final NpcStringId S1_HURRY_BEFORE_HE_GETS_AWAY;

	/**
	 * ID: 7960<br>
	 * Message: 殺せ！
	 */
	public static final NpcStringId ILL_KILL_YOU;

	/**
	 * ID: 7961<br>
	 * Message: いつか手合わせ願うぜ。
	 */
	public static final NpcStringId WHY_DONT_YOU_FIGHT_ME_SOMEDAY;

	/**
	 * ID: 7962<br>
	 * Message: また逃したのか！くそっ！
	 */
	public static final NpcStringId I_MISSED_AGAIN_DAMMIT;

	/**
	 * ID: 7963<br>
	 * Message: いつかまた会えるだろう。
	 */
	public static final NpcStringId IM_SURE_WELL_MEET_AGAIN_SOMEDAY;

	/**
	 * ID: 7964<br>
	 * Message: 神に逆らう者に天罰を！
	 */
	public static final NpcStringId CURSE_THOSE_WHO_DEFY_THE_GODS;

	/**
	 * ID: 7966<br>
	 * Message: 神の使者であるこの私にかかってくるとは！
	 */
	public static final NpcStringId YOU_WOULD_FIGHT_ME_A_MESSENGER_OF_THE_GODS;

	/**
	 * ID: 7967<br>
	 * Message: $s1！忘れないぞ！
	 */
	public static final NpcStringId S1_I_WONT_FORGET_YOU;

	/**
	 * ID: 8050<br>
	 * Message: $s1、神聖な地を汚すとは！
	 */
	public static final NpcStringId S1_HOW_COULD_YOU_DESECRATE_A_HOLY_PLACE;

	/**
	 * ID: 8051<br>
	 * Message: より大きな罰が下される前に去るがいい。
	 */
	public static final NpcStringId LEAVE_BEFORE_YOU_ARE_SEVERELY_PUNISHED;

	/**
	 * ID: 8052<br>
	 * Message: アインハザード様、私を見捨てないでください！
	 */
	public static final NpcStringId EINHASAD_DONT_GIVE_UP_ON_ME;

	/**
	 * ID: 8053<br>
	 * Message: 私を探しているのは、$s1、お前か。
	 */
	public static final NpcStringId S1_SO_YOURE_THE_ONE_WHOS_LOOKING_FOR_ME;

	/**
	 * ID: 8054<br>
	 * Message: 下界の生き物などにやられるとは．．．
	 */
	public static final NpcStringId A_MERE_MORTAL_HAS_DEFEATED_ME;

	/**
	 * ID: 8055<br>
	 * Message: クッ、戦いの邪魔をするとは卑怯な。
	 */
	public static final NpcStringId HOW_COWARDLY_TO_INTRUDE_IN_OTHER_PEOPLES_BUSINESS;

	/**
	 * ID: 8056<br>
	 * Message: 時間だ。
	 */
	public static final NpcStringId TIME_IS_UP;

	/**
	 * ID: 8057<br>
	 * Message: 私の剣を受けてみよ！
	 */
	public static final NpcStringId ILL_KILL_YOU_WITH_MY_SWORD;

	/**
	 * ID: 8058<br>
	 * Message: 助けてくれ！
	 */
	public static final NpcStringId HELP_ME;

	/**
	 * ID: 8059<br>
	 * Message: 逃がすな！
	 */
	public static final NpcStringId DONT_MISS;

	/**
	 * ID: 8060<br>
	 * Message: もっと押しこめろ！
	 */
	public static final NpcStringId KEEP_PUSHING;

	/**
	 * ID: 8061<br>
	 * Message: さあ、あいつを捕まえて君たちの恨みを晴らしてやろう。
	 */
	public static final NpcStringId ILL_GET_HIM_YOULL_HAVE_YOUR_REVENGE;

	/**
	 * ID: 8062<br>
	 * Message: またやつを逃がしたのか。必ずお前を片付けてやる。
	 */
	public static final NpcStringId I_MISSED_HIM_AGAIN_ILL_KILL_YOU;

	/**
	 * ID: 8063<br>
	 * Message: 私はやつを追う。
	 */
	public static final NpcStringId I_MUST_FOLLOW_HIM;

	/**
	 * ID: 8150<br>
	 * Message: $s1、神の怒りを恐れるなら去るがいい！
	 */
	public static final NpcStringId S1_YOU_SHOULD_LEAVE_IF_YOU_FEAR_GODS_WRATH;

	/**
	 * ID: 8151<br>
	 * Message: こんな時に何の用だ？
	 */
	public static final NpcStringId WHATS_GOING_ON;

	/**
	 * ID: 8152<br>
	 * Message: また会うことになるだろう！
	 */
	public static final NpcStringId ILL_SEE_YOU_AGAIN;

	/**
	 * ID: 8153<br>
	 * Message: お前は何者だ？なぜ私の部下を痛めつけるのだ？
	 */
	public static final NpcStringId WHO_ARE_YOU_WHY_ARE_YOU_BOTHERING_MY_MINIONS;

	/**
	 * ID: 8154<br>
	 * Message: だめだ！
	 */
	public static final NpcStringId NO_WAY;

	/**
	 * ID: 8155<br>
	 * Message: 誰だ貴様は！邪魔するな！
	 */
	public static final NpcStringId WHY_ARE_YOU_STICKING_YOUR_NOSE_IN_OUR_BUSINESS;

	/**
	 * ID: 8156<br>
	 * Message: 貴様は何者だ？下界の生き物がここまで強いとは．．．
	 */
	public static final NpcStringId WHO_ARE_YOU_HOW_CAN_A_CREATURE_FROM_THE_NETHERWORLD_BE_SO_POWERFUL;

	/**
	 * ID: 8157<br>
	 * Message: これで最後か．．．
	 */
	public static final NpcStringId IS_THIS_THE_END;

	/**
	 * ID: 8158<br>
	 * Message: お前の腕を見せてみろ。こいつを倒すんだ！
	 */
	public static final NpcStringId SHOW_ME_WHAT_YOURE_MADE_OF_KILL_HIM;

	/**
	 * ID: 8159<br>
	 * Message: その程度の腕でやつを倒せると思っているのか。
	 */
	public static final NpcStringId YOU_THINK_YOU_CAN_GET_HIM_WITH_THAT;

	/**
	 * ID: 8160<br>
	 * Message: おい、がんばれよ！やつが逃げようとしてるじゃないか。
	 */
	public static final NpcStringId PULL_YOURSELF_TOGETHER_HES_TRYING_TO_GET_AWAY;

	/**
	 * ID: 8161<br>
	 * Message: ブラック キャットに伝えろ。借りは返したと。
	 */
	public static final NpcStringId TELL_THE_BLACK_CAT_THAT_I_GOT_HIS_PAID_BACK;

	/**
	 * ID: 8162<br>
	 * Message: ブラック キャットのやつ、私を恨むだろうな。
	 */
	public static final NpcStringId BLACK_CAT_HELL_BLAME_ME;

	/**
	 * ID: 8163<br>
	 * Message: これにて失礼。
	 */
	public static final NpcStringId I_GOTTA_GO_NOW;

	/**
	 * ID: 8166<br>
	 * Message: 神の御名においてお前を始末する。
	 */
	public static final NpcStringId ILL_KILL_YOU_IN_THE_NAME_OF_GOD;

	/**
	 * ID: 8167<br>
	 * Message: $s1！また会おう！
	 */
	public static final NpcStringId S1_SEE_YOU_LATER;

	/**
	 * ID: 8251<br>
	 * Message: より大きな罰が下される前に去るがいい。
	 */
	public static final NpcStringId GET_OUT_BEFORE_YOURE_PUNISHED;

	/**
	 * ID: 8252<br>
	 * Message: アインハザード様、私を見捨てないでください！
	 */
	public static final NpcStringId EINHASAD_PLEASE_DONT_GIVE_UP_ON_ME;

	/**
	 * ID: 8253<br>
	 * Message: 私を探しているのは、$s1、お前か。
	 */
	public static final NpcStringId S1_ARE_YOU_LOOKING_FOR_ME;

	/**
	 * ID: 8254<br>
	 * Message: 下界の生き物などにやられるとは．．．
	 */
	public static final NpcStringId A_MERE_MORTAL_IS_KILLING_ME;

	/**
	 * ID: 8256<br>
	 * Message: 下界の生き物よ、これで私の偉大さがわかったか。
	 */
	public static final NpcStringId MORTAL_DONT_YOU_RECOGNIZE_MY_GREATNESS;

	/**
	 * ID: 8257<br>
	 * Message: 今度こそ貴様を必ず倒してやる。
	 */
	public static final NpcStringId ILL_GET_YOU_THIS_TIME;

	/**
	 * ID: 8258<br>
	 * Message: 昔の戦いで負った古傷が疼く。おい、$s1！こいつを一緒に倒そうぜ。
	 */
	public static final NpcStringId ILL_NEVER_FORGET_THE_TASTE_OF_HIS_STEEL_S1_LETS_FIGHT_HIM_TOGETHER;

	/**
	 * ID: 8259<br>
	 * Message: $s1！がんばれよ！このままじゃあいつを逃してしまう！
	 */
	public static final NpcStringId S1_PULL_YOURSELF_TOGETHER_WELL_MISS_HIM;

	/**
	 * ID: 8260<br>
	 * Message: $s1！やつに逃げられそうだ。
	 */
	public static final NpcStringId S1_HES_TRYING_TO_GET_AWAY;

	/**
	 * ID: 8261<br>
	 * Message: また逃したか。だが次こそ必ず．．．
	 */
	public static final NpcStringId I_MISSED_AGAIN_NEXT_TIME;

	/**
	 * ID: 8262<br>
	 * Message: くそっ．．．また失敗か。
	 */
	public static final NpcStringId DAMMIT_FAILED_AGAIN;

	/**
	 * ID: 8353<br>
	 * Message: 私を探しているのは、$s1、お前か。
	 */
	public static final NpcStringId YOU_ARE_THE_ONE_WHOS_LOOKING_FOR_ME_S1;

	/**
	 * ID: 8354<br>
	 * Message: 下界の生き物などにやられるとは．．．
	 */
	public static final NpcStringId A_MERE_MORTAL_HAS_KILLED_ME;

	/**
	 * ID: 8355<br>
	 * Message: クッ．．．貴様は何者だ？なぜ戦いの邪魔をするんだ！
	 */
	public static final NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS;

	/**
	 * ID: 8359<br>
	 * Message: $s1！もっと踏ん張れ。
	 */
	public static final NpcStringId S1_PULL_YOURSELF_TOGETHER;

	/**
	 * ID: 8360<br>
	 * Message: $s1！やつに逃げられそうだ。
	 */
	public static final NpcStringId S1_HELL_GET_AWAY;

	/**
	 * ID: 8452<br>
	 * Message: アインハザード様、私を見捨てないでください！
	 */
	public static final NpcStringId EINHASAD_PLEASE_DONT_FORSAKE_ME;

	/**
	 * ID: 8453<br>
	 * Message: 私を探しているのは、$s1、お前か。
	 */
	public static final NpcStringId LOOKING_FOR_ME_S1;

	/**
	 * ID: 8550<br>
	 * Message: $s1！ビショップ風情が神の御心に背くとは。こざかしい。
	 */
	public static final NpcStringId S1_BISHOP_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD;

	/**
	 * ID: 8551<br>
	 * Message: 思いのほか信仰心が厚いやつだな。また会おう。
	 */
	public static final NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_PAY_YOU_BACK_NEXT_TIME;

	/**
	 * ID: 8552<br>
	 * Message: おお！タナキア様。あなたの志を成し遂げられなかった私をお許しください！
	 */
	public static final NpcStringId TANAKIA_FORGIVE_ME_I_COULDNT_FULFILL_YOUR_DREAM;

	/**
	 * ID: 8553<br>
	 * Message: 私を探しながら部下たちをいびっているというのは$s1、お前か。
	 */
	public static final NpcStringId S1_YOU_ARE_THE_WON_WHOS_BEEN_BOTHERING_MY_MINIONS;

	/**
	 * ID: 8554<br>
	 * Message: くそ．．．貴様ごときに私が負けるとは。
	 */
	public static final NpcStringId DAMN_YOUVE_BEATEN_ME;

	/**
	 * ID: 8555<br>
	 * Message: 戦いを邪魔するお前は何者だ！？卑怯なやつめ。
	 */
	public static final NpcStringId WHO_ARE_YOU_THIS_ISNT_YOUR_BUSINESS_COWARD;

	/**
	 * ID: 8556<br>
	 * Message: ほう、思ったよりやるじゃないか。私を楽しませてくれたお礼に、今回は見逃してやろう。
	 */
	public static final NpcStringId HOW_WEAK_ILL_FORGIVE_YOU_THIS_TIME_BECAUSE_YOU_MADE_ME_LAUGH;

	/**
	 * ID: 8557<br>
	 * Message: 思ったより強いんですね。しかし、私もそう弱くはないですよ。
	 */
	public static final NpcStringId YOU_ARE_STRONGER_THAN_I_THOUGHT_BUT_IM_NO_WEAKLING;

	/**
	 * ID: 8558<br>
	 * Message: 手ごわい相手ですね。$s1！私と力を合わせてあの者を倒しましょう。
	 */
	public static final NpcStringId HES_GOT_A_TOUGH_SHELL_S1_LETS_FIGHT_TOGETHER_AND_CRACK_HIS_SKULL;

	/**
	 * ID: 8560<br>
	 * Message: $s1！本来の力を発揮しないとあいつを倒せません！早く！
	 */
	public static final NpcStringId S1_WE_WONT_BEAT_HIM_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON;

	/**
	 * ID: 8561<br>
	 * Message: では、私はあの者について行きます。
	 */
	public static final NpcStringId ILL_FOLLOW_HIM;

	/**
	 * ID: 8562<br>
	 * Message: また逃したか。追いかけるのは大変そうだ。
	 */
	public static final NpcStringId I_MISSED_AGAIN_HES_HARD_TO_FOLLOW;

	/**
	 * ID: 8563<br>
	 * Message: では、未来の姿に期待しています。
	 */
	public static final NpcStringId WELL_SEE_WHAT_THE_FUTURE_BRINGS;

	/**
	 * ID: 8564<br>
	 * Message: シーレン様のために！
	 */
	public static final NpcStringId FOR_SHILEN;

	/**
	 * ID: 8565<br>
	 * Message: 私は戻ってくる。その時には覚悟するがいい。
	 */
	public static final NpcStringId ILL_BE_BACK_ILL_DEAL_WITH_YOU_THEN;

	/**
	 * ID: 8566<br>
	 * Message: $s1！この私に逆らうのか！
	 */
	public static final NpcStringId S1_ARE_YOU_GOING_TO_FIGHT_ME;

	/**
	 * ID: 8567<br>
	 * Message: $s1！覚えていろ。貴様のことは絶対忘れない。
	 */
	public static final NpcStringId S1_ILL_PAY_YOU_BACK_I_WONT_FORGET_YOU;

	/**
	 * ID: 8650<br>
	 * Message: $s1！プロフィット風情が神の御心に背くとは。こざかしい。
	 */
	public static final NpcStringId S1_PROPHET_HOW_FOOLISH_TO_GO_AGAINST_THE_WILL_OF_GOD;

	/**
	 * ID: 8651<br>
	 * Message: 思いのほか信仰心が厚いやつだな。また会おう。
	 */
	public static final NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_DEAL_WITH_YOU_NEXT_TIME;

	/**
	 * ID: 8653<br>
	 * Message: 私を探しながら部下たちをいびっているというのは$s1、お前か。
	 */
	public static final NpcStringId ARE_YOU_THE_ONE_WHOS_BEEN_BOTHERING_MY_MINIONS_S1;

	/**
	 * ID: 8654<br>
	 * Message: くそ．．．貴様ごときに私が負けるとは。
	 */
	public static final NpcStringId DAMN_I_CANT_BELIEVE_IVE_BEEN_BEATEN_BY_YOU;

	/**
	 * ID: 8655<br>
	 * Message: 戦いを邪魔するお前は何者だ！？卑怯なやつめ。
	 */
	public static final NpcStringId WHO_ARE_YOU_THIS_IS_NONE_OF_YOUR_BUSINESS_COWARD;

	/**
	 * ID: 8657<br>
	 * Message: 光の力で、世にはびこる闇を退けるだろう。
	 */
	public static final NpcStringId ILL_DESTROY_THE_DARKNESS_SURROUNDING_THE_WORLD_WITH_THE_POWER_OF_LIGHT;

	/**
	 * ID: 8658<br>
	 * Message: $s1！私と一緒にあの堕落した者を倒して、真なる光の力を見せてやりましょう。
	 */
	public static final NpcStringId S1_FIGHT_THE_FALLEN_ANGEL_WITH_ME_SHOW_THE_TRUE_POWER_OF_LIGHT;

	/**
	 * ID: 8659<br>
	 * Message: $s1！続けてください。この戦いはここで終わらせなくてはなりません。
	 */
	public static final NpcStringId S1_GO_WE_MUST_STOP_FIGHTING_HERE;

	/**
	 * ID: 8660<br>
	 * Message: $s1！私たちは絶対負けられないのです。歯を食いしばって戦うのです。
	 */
	public static final NpcStringId WE_MUSTNT_LOSE_S1_PULL_YOURSELF_TOGETHER;

	/**
	 * ID: 8661<br>
	 * Message: では、ご縁があればまたお会いしましょう。
	 */
	public static final NpcStringId WELL_MEET_AGAIN_IF_FATE_WILLS_IT;

	/**
	 * ID: 8662<br>
	 * Message: 逃げ去った卑怯な悪魔の後を追います。
	 */
	public static final NpcStringId ILL_FOLLOW_THE_COWARDLY_DEVIL;

	/**
	 * ID: 8750<br>
	 * Message: $s1！エルダー風情が神の御心に背くとは。こざかしい。
	 */
	public static final NpcStringId S1_ELDER_ITS_FOOLISH_OF_YOU_TO_GO_AGAINST_THE_WILL_OF_THE_GODS;

	/**
	 * ID: 8757<br>
	 * Message: 思ったより強いんですね。しかし、私もそう弱くはないですよ。
	 */
	public static final NpcStringId YOURE_STRONGER_THAN_I_THOUGHT_BUT_IM_NO_WEAKLING_EITHER;

	/**
	 * ID: 8760<br>
	 * Message: $s1！本来の力を発揮しないとあいつを倒せません！早く！
	 */
	public static final NpcStringId S1_WELL_NEVER_WIN_UNLESS_WE_GIVE_IT_OUR_ALL_COME_ON;

	/**
	 * ID: 8850<br>
	 * Message: 貴様が$s1？フッ、交感石は私が持っているぞ！
	 */
	public static final NpcStringId ARE_YOU_S1_OH_I_HAVE_THE_RESONANCE_AMULET;

	/**
	 * ID: 8851<br>
	 * Message: くそっ、思ったよりしぶといな。今回はこの辺で見逃してやろう。
	 */
	public static final NpcStringId YOURE_FEISTIER_THAN_I_THOUGHT_ILL_QUIT_HERE_FOR_TODAY;

	/**
	 * ID: 8852<br>
	 * Message: ぐうっ．．．この私が負けるとは．．．
	 */
	public static final NpcStringId AAARGH_I_CANT_BELIEVE_I_LOST;

	/**
	 * ID: 8854<br>
	 * Message: つ、強い．．．
	 */
	public static final NpcStringId YIKES_YOURE_TOUGH;

	/**
	 * ID: 8855<br>
	 * Message: なぜ戦いの邪魔をするのだ．．．
	 */
	public static final NpcStringId WHY_DO_YOU_INTERFERE_IN_OTHER_PEOPLES_BUSINESS;

	/**
	 * ID: 8856<br>
	 * Message: 今日はこの辺で引き下がるとしよう。
	 */
	public static final NpcStringId ILL_STOP_HERE_FOR_TODAY;

	/**
	 * ID: 8857<br>
	 * Message: 今度こそ逃さないぞ！
	 */
	public static final NpcStringId I_WONT_MISS_YOU_THIS_TIME;

	/**
	 * ID: 8858<br>
	 * Message: くそ．．．やはり1人では無理か．．．$s1！手伝ってくれ！
	 */
	public static final NpcStringId DAMMIT_THIS_IS_TOO_HARD_BY_MYSELF_S1_GIVE_ME_A_HAND;

	/**
	 * ID: 8859<br>
	 * Message: $s1！急げ、このままじゃ逃してしまう。
	 */
	public static final NpcStringId S1_HURRY_UP_WELL_MISS_HIM;

	/**
	 * ID: 8860<br>
	 * Message: $s1！急いでくれ。
	 */
	public static final NpcStringId S1_COME_ON_HURRY_UP;

	/**
	 * ID: 8861<br>
	 * Message: これにて失礼。私はあいつを追わねばならぬ。
	 */
	public static final NpcStringId I_GOTTA_GO_FOLLOW_HIM;

	/**
	 * ID: 8862<br>
	 * Message: ちっ、逃げようってのか。待て！
	 */
	public static final NpcStringId HEY_QUIT_RUNNING_STOP;

	/**
	 * ID: 8863<br>
	 * Message: それじゃ、またね～
	 */
	public static final NpcStringId SEE_YOU_NEXT_TIME;

	/**
	 * ID: 8864<br>
	 * Message: 何だぁ？貴様ごときが私を防げると思うのか。
	 */
	public static final NpcStringId WHAT_THINK_YOU_CAN_GET_IN_MY_WAY;

	/**
	 * ID: 8865<br>
	 * Message: ふっ、軟弱な。じゃあな．．．
	 */
	public static final NpcStringId YOU_ARE_SO_WEAK_I_GOTTA_GO_NOW;

	/**
	 * ID: 8866<br>
	 * Message: む？貴様は$s1！丁度いい、一緒に始末してやる。
	 */
	public static final NpcStringId S1_GOOD_ILL_HELP_YOU;

	/**
	 * ID: 8867<br>
	 * Message: $s1、と言ったかな。思ったより強いな。また会おう。
	 */
	public static final NpcStringId S1_YOURE_STRONGER_THAN_I_THOUGHT_SEE_YOU_NEXT_TIME;

	/**
	 * ID: 8951<br>
	 * Message: くそっ、思ったよりしぶといな。今回はこの辺で見逃してやろう。
	 */
	public static final NpcStringId YOURE_FEISTIER_THAN_I_THOUGHT_ILL_STOP_HERE_TODAY;

	/**
	 * ID: 8952<br>
	 * Message: ぐうっ．．．この私が負けるとは．．．
	 */
	public static final NpcStringId AARGH_I_CANT_BELIEVE_I_LOST;

	/**
	 * ID: 8956<br>
	 * Message: 今日はこの辺で引き下がるとしよう。
	 */
	public static final NpcStringId ILL_STOP_HERE_TODAY;

	/**
	 * ID: 8958<br>
	 * Message: くそ．．．やはり1人では無理か．．．$s1！手伝ってくれ！
	 */
	public static final NpcStringId DAMN_ITS_TOO_MUCH_BY_MYSELFS1_GIVE_ME_A_HAND;

	/**
	 * ID: 8959<br>
	 * Message: $s1！急げ、このままじゃ逃してしまう。
	 */
	public static final NpcStringId S1_HURRY_WELL_MISS_HIM;

	/**
	 * ID: 8960<br>
	 * Message: $s1！急いでくれ。
	 */
	public static final NpcStringId S1_HURRY_PLEASE;

	/**
	 * ID: 8961<br>
	 * Message: これにて失礼。私はあいつを追わねばならぬ。
	 */
	public static final NpcStringId I_GOTTA_GO_FOLLOW_HIM_NOW;

	/**
	 * ID: 8962<br>
	 * Message: ちっ、逃げようってのか。待て！
	 */
	public static final NpcStringId ARE_YOU_RUNNING_AWAY_STOP;

	/**
	 * ID: 8964<br>
	 * Message: 何だぁ？貴様ごときが私を防げると思うのか。
	 */
	public static final NpcStringId DO_YOU_THINK_YOU_CAN_STOP_ME;

	/**
	 * ID: 8965<br>
	 * Message: ふっ、軟弱な。じゃあな．．．
	 */
	public static final NpcStringId YOURE_SO_WEAK_I_GOTTA_GO_NOW;

	/**
	 * ID: 8966<br>
	 * Message: む？貴様は$s1！丁度いい、一緒に始末してやる。
	 */
	public static final NpcStringId YOURE_S1_GOOD_ILL_HELP_YOU;

	/**
	 * ID: 9050<br>
	 * Message: 貴様が$s1？フッ、交感石は私が持っているぞ！
	 */
	public static final NpcStringId ARE_YOU_S1_OH_I_HAVE_A_RESONANCE_AMULET;

	/**
	 * ID: 9051<br>
	 * Message: くそっ、思ったよりしぶといな。今回はこの辺で見逃してやろう。
	 */
	public static final NpcStringId HEY_YOURE_MORE_TENACIOUS_THAN_I_THOUGHT_ILL_STOP_HERE_TODAY;

	/**
	 * ID: 9058<br>
	 * Message: くそ．．．やはり1人では無理か．．．$s1！手伝ってくれ！
	 */
	public static final NpcStringId DAMMIT_I_CANT_DO_THIS_ALONE_S1_GIVE_ME_A_HAND;

	/**
	 * ID: 9059<br>
	 * Message: $s1！急げ、このままじゃ逃してしまう。
	 */
	public static final NpcStringId S1_HURRY_OR_WELL_MISS_HIM;

	/**
	 * ID: 9060<br>
	 * Message: $s1！急いでくれ。
	 */
	public static final NpcStringId S1_HURRY_UP;

	/**
	 * ID: 9061<br>
	 * Message: これにて失礼。私はあいつを追わねばならぬ。
	 */
	public static final NpcStringId I_GOTTA_FOLLOW_HIM_NOW;

	/**
	 * ID: 9062<br>
	 * Message: ちっ、逃げようってのか。待て！
	 */
	public static final NpcStringId HEY_ARE_YOU_RUNNING_STOP;

	/**
	 * ID: 9066<br>
	 * Message: む？貴様は$s1！丁度いい、一緒に始末してやる。
	 */
	public static final NpcStringId OH_YOURE_S1_GOOD_ILL_HELP_YOU;

	/**
	 * ID: 9150<br>
	 * Message: クズのような精霊どもと交感する者、$s1！お前に聖なる知恵を手にする資格はない！
	 */
	public static final NpcStringId YOU_CAROUSE_WITH_EVIL_SPIRITS_S1_YOURE_NOT_WORTHY_OF_THE_HOLY_WISDOM;

	/**
	 * ID: 9151<br>
	 * Message: 意地を捨てろ！これ以上歯向かっても無駄だ！
	 */
	public static final NpcStringId YOURE_SO_STUBBORN_I_CANT_BOSS_YOU_AROUND_ANY_MORE_CAN_I;

	/**
	 * ID: 9152<br>
	 * Message: ま、まさか！ヒューマンごときにやられるとは！？
	 */
	public static final NpcStringId HOW_COULD_IT_HAPPEN_DEFEATED_BY_A_HUMAN;

	/**
	 * ID: 9157<br>
	 * Message: ご主人様の仰せに従ってきたにゃん！キャット族の名誉を賭けて、あなたを守りますにゃ！
	 */
	public static final NpcStringId MY_MASTER_SENT_ME_HERE_ILL_GIVE_YOU_A_HAND;

	/**
	 * ID: 9158<br>
	 * Message: うにゃああ！$s1さぁ～ん、助けてぇ！
	 */
	public static final NpcStringId MEOW_MASTER_S1_HELP_ME;

	/**
	 * ID: 9159<br>
	 * Message: $s1さん！やつがベリンダさんをいじめないように懲らしめるにゃ！
	 */
	public static final NpcStringId MASTER_S1_PUNISH_HIM_SO_HE_CANT_BOTHER_BELINDA;

	/**
	 * ID: 9160<br>
	 * Message: $s1さん！このままじゃ逃げられちゃうにゃ！
	 */
	public static final NpcStringId MASTER_S1_WELL_MISS_HIM;

	/**
	 * ID: 9161<br>
	 * Message: にゃああ～ん！ご主人様が呼んでるにゃ！バイバイ！
	 */
	public static final NpcStringId MEOW_MY_MASTER_IS_CALLING_MEOW_I_GOTTA_GO_NOW;

	/**
	 * ID: 9162<br>
	 * Message: にゃう！逃してしまったにゃん！
	 */
	public static final NpcStringId MEOW_I_MISSED_HIM_MEOW;

	/**
	 * ID: 9163<br>
	 * Message: 幸運を祈りますにゃ！それじゃまた！
	 */
	public static final NpcStringId GOOD_LUCK_MEOW_I_GOTTA_GO_NOW;

	/**
	 * ID: 9164<br>
	 * Message: お前はいろいろ知りすぎた。大人しく死んでもらおうか！
	 */
	public static final NpcStringId CURIOSITY_KILLED_THE_CAT_ILL_SHOW_YOU;

	/**
	 * ID: 9165<br>
	 * Message: 本日はここまで！
	 */
	public static final NpcStringId THATS_ALL_FOR_TODAY;

	/**
	 * ID: 9166<br>
	 * Message: ベリンダを奪おうとしているのは貴様か、$s1？死ねっ！
	 */
	public static final NpcStringId ARE_YOU_TRYING_TO_TAKE_BELINDA_FROM_ME_S1_ILL_SHOW_YOU;

	/**
	 * ID: 9167<br>
	 * Message: ベ、ベリンダ！君を愛している！グフッ！
	 */
	public static final NpcStringId BELINDA_I_LOVE_YOU_YIKES;

	/**
	 * ID: 9251<br>
	 * Message: 意地を捨てろ！これ以上歯向かっても無駄だ！
	 */
	public static final NpcStringId YOURE_STUBBORN_AS_A_MULE_GUESS_I_CANT_BOSS_YOU_AROUND_ANY_MORE;

	/**
	 * ID: 9252<br>
	 * Message: ま、まさか！エルフごときにやられるとは！？
	 */
	public static final NpcStringId HOW_COULD_IT_BEDEFEATED_BY_AN_ELF;

	/**
	 * ID: 9257<br>
	 * Message: ラディス様の 最後の 思念により あなたの お手伝いに 来ました。
	 */
	public static final NpcStringId I_CAME_TO_HELP_YOU_ITS_THE_WILL_OF_RADYSS;

	/**
	 * ID: 9258<br>
	 * Message: $s1よ あなたの 力を 貸して ください。
	 */
	public static final NpcStringId S1_FIGHT_WITH_ME;

	/**
	 * ID: 9259<br>
	 * Message: $s1よ やつを 必ず 倒さなくては なりません。
	 */
	public static final NpcStringId S1_WE_MUST_DEFEAT_HIM;

	/**
	 * ID: 9260<br>
	 * Message: $s1よ 時間が ありません 早く やつを 倒さなくては。
	 */
	public static final NpcStringId S1_THERES_NO_TIME_WE_MUST_DEFEAT_HIM;

	/**
	 * ID: 9261<br>
	 * Message: ラディス様が 私を 呼んでおられます もう 行かなくては。
	 */
	public static final NpcStringId RADYSS_IS_CALLING_ME_I_GOTTA_GO_NOW;

	/**
	 * ID: 9262<br>
	 * Message: 同族の 仇を 遂に 討つことが できなかった。
	 */
	public static final NpcStringId I_WAS_UNABLE_TO_AVENGE_MY_BROTHER;

	/**
	 * ID: 9263<br>
	 * Message: 汝に 湖の 祝福の あらんことを。
	 */
	public static final NpcStringId MAY_YOU_BE_BLESSED;

	/**
	 * ID: 9264<br>
	 * Message: 驕慢な者よ、悔い改めよ！愚かな者よ、悟るがいい！罪人よ、許しを乞え！死をもって！
	 */
	public static final NpcStringId THE_PROUD_REPENT_THE_FOOLISH_AWAKEN_SINNERS_DIE;

	/**
	 * ID: 9265<br>
	 * Message: あの世の主がお呼びだ．．．贖罪はまた今度にしよう．．．
	 */
	public static final NpcStringId HELLS_MASTER_IS_CALLING_ATONEMENT_WILL_HAVE_TO_WAIT;

	/**
	 * ID: 9266<br>
	 * Message: $s1、お前の名前も異教徒のリストに載せておいてやる！
	 */
	public static final NpcStringId S1_ILL_REMEMBER_YOUR_NAME_HEATHEN;

	/**
	 * ID: 9267<br>
	 * Message: 聖なる審判に応じぬ者、$s1！その名、忘れぬぞ！
	 */
	public static final NpcStringId I_WONT_FORGET_THE_NAME_OF_ONE_WHO_DOESNT_OBEY_HOLY_JUDGMENT_S1;

	/**
	 * ID: 9351<br>
	 * Message: 意地を捨てろ！これ以上歯向かっても無駄だ！
	 */
	public static final NpcStringId YOURE_STUBBORN_AS_A_MULE_I_GUESS_I_CANT_BOSS_YOU_AROUND_ANY_MORE;

	/**
	 * ID: 9352<br>
	 * Message: ま、まさか！ダーク エルフごときにやられるとは！？
	 */
	public static final NpcStringId COULD_IT_BE_DEFEATED_BY_A_DARK_ELF;

	/**
	 * ID: 9357<br>
	 * Message: 影の召喚師よ、助太刀に来た。
	 */
	public static final NpcStringId SHADOW_SUMMONER_I_CAME_HERE_TO_HELP_YOU;

	/**
	 * ID: 9358<br>
	 * Message: 影の召喚師、$s1よ！あなたの力を貸してください。
	 */
	public static final NpcStringId SHADOW_SUMMONER_S1_FIGHT_WITH_ME;

	/**
	 * ID: 9359<br>
	 * Message: $s1よ、早くやつを倒さなければあなたがやられる！
	 */
	public static final NpcStringId S1_YOULL_DIE_IF_YOU_DONT_KILL_HIM;

	/**
	 * ID: 9360<br>
	 * Message: 急ぐのだ、$s1よ！やつを逃してはなりません！
	 */
	public static final NpcStringId HURRY_S1_DONT_MISS_HIM;

	/**
	 * ID: 9361<br>
	 * Message: これ以上実体を保つことは無理です．．．
	 */
	public static final NpcStringId I_CANT_HOLD_ON_ANY_LONGER;

	/**
	 * ID: 9362<br>
	 * Message: 結局．．．逃してしまった．．．
	 */
	public static final NpcStringId AFTER_ALL_THATI_MISSED_HIM;

	/**
	 * ID: 9363<br>
	 * Message: 影の召喚師よ！汝に深淵の祝福のあらんことを！
	 */
	public static final NpcStringId SHADOW_SUMMONER_MAY_YOU_BE_BLESSED;

	/**
	 * ID: 9364<br>
	 * Message: ご主人様の命により、そなたに死を与えに来た！
	 */
	public static final NpcStringId MY_MASTER_SENT_ME_HERE_TO_KILL_YOU;

	/**
	 * ID: 9365<br>
	 * Message: 影が私を呼んでいる．．．
	 */
	public static final NpcStringId THE_SHADOW_IS_CALLING_ME;

	/**
	 * ID: 9366<br>
	 * Message: $s1、命が惜しくないのか！望みとあらばすぐ闇に葬ってやろう！
	 */
	public static final NpcStringId S1_YOU_WANT_TO_DIE_EARLY_ILL_SEND_YOU_TO_THE_DARKNESS;

	/**
	 * ID: 9367<br>
	 * Message: 影を操る者、$s1よ！そなたの名前は覚えておくぞ！
	 */
	public static final NpcStringId YOU_DEAL_IN_DARKNESS_S1_ILL_PAY_YOU_BACK;

	/**
	 * ID: 9450<br>
	 * Message: 貴様は．．．$s1？ヒンデミットの二の舞にはならんぞ！
	 */
	public static final NpcStringId YOURE_S1_I_WONT_BE_LIKE_HINDEMITH;

	/**
	 * ID: 9451<br>
	 * Message: くそっ、思ったよりしぶといな。今回はこの辺で見逃してやろう。
	 */
	public static final NpcStringId YOURE_FEISTIER_THAN_I_THOUGHT_ILL_STOP_HERE_FOR_TODAY;

	/**
	 * ID: 9453<br>
	 * Message: $s1、近頃私の部下たちをいびっているのはお前か。
	 */
	public static final NpcStringId ARE_YOU_THE_ONE_WHO_IS_BOTHERING_MY_MINIONS_S1;

	/**
	 * ID: 9457<br>
	 * Message: お前に秘伝のタブレットとの交感はさせぬ！交感石を渡せ！
	 */
	public static final NpcStringId I_CANT_LET_YOU_COMMUNE_WITH_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET;

	/**
	 * ID: 9460<br>
	 * Message: $s1！急いでくれ。
	 */
	public static final NpcStringId S1_PLEASE_HURRY;

	/**
	 * ID: 9461<br>
	 * Message: これにて失礼。私はあいつを追わねばならぬ。
	 */
	public static final NpcStringId I_MUST_FOLLOW_HIM_NOW;

	/**
	 * ID: 9462<br>
	 * Message: ちっ、逃げようってのか。待て！
	 */
	public static final NpcStringId ARE_YOU_RUNNING_STOP;

	/**
	 * ID: 9464<br>
	 * Message: 何だ？裏切るのか。怪しいと思ったら．．．チェッ、今日のところはこれで退くとするか。
	 */
	public static final NpcStringId ARE_YOU_BETRAYING_ME_I_THOUGHT_SOMETHING_WAS_WRONGILL_STOP_HERE;

	/**
	 * ID: 9466<br>
	 * Message: 貴様は．．．$s1？2人になったからといって私を止めることができると思うのか。
	 */
	public static final NpcStringId YOURE_S1_EVEN_TWO_OF_YOU_CANT_STOP_ME;

	/**
	 * ID: 9467<br>
	 * Message: 何ぃっ！？私の交感石が．．．$s1、覚えてろ。
	 */
	public static final NpcStringId DAMMIT_MY_RESONANCE_AMULETS1_ILL_NEVER_FORGET_TO_PAY_YOU_BACK;

	/**
	 * ID: 9550<br>
	 * Message: 貴様は．．．$s1？バルトシュタインの二の舞にはならんぞ！
	 */
	public static final NpcStringId ARE_YOU_S1_I_WONT_BE_LIKE_WALDSTEIN;

	/**
	 * ID: 9552<br>
	 * Message: ぐうっ．．．この私が負けるとは．．．
	 */
	public static final NpcStringId YIKES_I_CANT_BELIEVE_I_LOST;

	/**
	 * ID: 9553<br>
	 * Message: $s1、近頃私の部下たちをいびっているのはお前か。
	 */
	public static final NpcStringId ARE_YOU_THE_ONE_BOTHERING_MY_MINIONS_S1;

	/**
	 * ID: 9557<br>
	 * Message: お前に秘伝のタブレットとの交感はさせぬ！交感石を渡せ！
	 */
	public static final NpcStringId YOU_CANT_COMMUNE_WITH_THE_TABLET_OF_VISION_GIVE_ME_THE_RESONANCE_AMULET;

	/**
	 * ID: 9567<br>
	 * Message: 何ぃっ！？私の交感石が．．．$s1、覚えてろ。
	 */
	public static final NpcStringId DAMMIT_MY_RESONANCE_AMULETS1_ILL_NEVER_FORGET_THIS;

	/**
	 * ID: 9650<br>
	 * Message: $s1！貴様が？ハラト様の行く道を邪魔できないよう息の根を止めてやる。
	 */
	public static final NpcStringId YOURE_S1_ILL_KILL_YOU_FOR_HALLATE;

	/**
	 * ID: 9651<br>
	 * Message: 思ったよりしぶといな。しかし貴様は私の相手じゃない。
	 */
	public static final NpcStringId YOURE_TOUGHER_THAN_I_THOUGHT_BUT_YOU_STILL_CANT_RIVAL_ME;

	/**
	 * ID: 9652<br>
	 * Message: ハラト様！非力な私をお許しください！
	 */
	public static final NpcStringId HALLATE_FORGIVE_ME_I_CANT_HELP_YOU;

	/**
	 * ID: 9654<br>
	 * Message: くそ．．．貴様ごときに私が負けるとは。
	 */
	public static final NpcStringId DAMMIT_I_CANT_BELIEVE_YOU_BEAT_ME;

	/**
	 * ID: 9655<br>
	 * Message: 戦いを邪魔するお前は何者だ！？卑怯なやつめ。
	 */
	public static final NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_COWARD;

	/**
	 * ID: 9657<br>
	 * Message: 煉獄の君主！逃がさないぞ。
	 */
	public static final NpcStringId PURGATORY_LORD_I_WONT_FAIL_THIS_TIME;

	/**
	 * ID: 9658<br>
	 * Message: $s1！これまでの成果を見せるチャンスだ！お前の力を見せてやれ。
	 */
	public static final NpcStringId S1_NOWS_THE_TIME_TO_PUT_YOUR_TRAINING_TO_THE_TEST;

	/**
	 * ID: 9659<br>
	 * Message: $s1！お前の実力はそんなもんじゃないだろう。
	 */
	public static final NpcStringId S1_YOUR_SWORD_SKILLS_CANT_BE_THAT_BAD;

	/**
	 * ID: 9660<br>
	 * Message: $s1！お前の本当の力を見せてくれ！
	 */
	public static final NpcStringId S1_SHOW_YOUR_STRENGTH;

	/**
	 * ID: 9661<br>
	 * Message: それじゃ、私は他の用事があるので．．．
	 */
	public static final NpcStringId I_HAVE_SOME_PRESSING_BUSINESS_I_HAVE_TO_GO;

	/**
	 * ID: 9662<br>
	 * Message: 今回も逃がしたのか、くそっ。
	 */
	public static final NpcStringId I_MISSED_HIM_DAMMIT;

	/**
	 * ID: 9663<br>
	 * Message: また遊びに来るといい。
	 */
	public static final NpcStringId TRY_AGAIN_SOMETIME;

	/**
	 * ID: 9664<br>
	 * Message: 私の道を塞ぐ者！死をもって償え！
	 */
	public static final NpcStringId ILL_KILL_ANYONE_WHO_GETS_IN_MY_WAY;

	/**
	 * ID: 9665<br>
	 * Message: 笑わせるな。つまらな過ぎる。
	 */
	public static final NpcStringId THIS_IS_PATHETIC_YOU_MAKE_ME_LAUGH;

	/**
	 * ID: 9666<br>
	 * Message: $s1！貴様ごときが私の前に立ちはだかろうとは！
	 */
	public static final NpcStringId S1_ARE_YOU_TRYING_TO_GET_IN_MY_WAY;

	/**
	 * ID: 9667<br>
	 * Message: $s1！私が戻ってきた日がお前の命日になるだろう。
	 */
	public static final NpcStringId S1_WHEN_I_COME_BACK_ILL_KILL_YOU;

	/**
	 * ID: 9750<br>
	 * Message: $s1？ 貴 様 は こ こ で 死 ぬ の だ。
	 */
	public static final NpcStringId S1_WAKE_UP_TIME_TO_DIE;

	/**
	 * ID: 9751<br>
	 * Message: 思 い の ほ か 強 い な ま た 出 直 す。
	 */
	public static final NpcStringId YOURE_TOUGHER_THAN_I_THOUGHT_ILL_BE_BACK;

	/**
	 * ID: 9752<br>
	 * Message: 私 が 負 け る な ん て。
	 */
	public static final NpcStringId I_LOST_IT_CANT_BE;

	/**
	 * ID: 9757<br>
	 * Message: すばしっこいやつ！今度こそ逃さないぞ。
	 */
	public static final NpcStringId YOURE_A_CUNNING_FIEND_I_WONT_FAIL_AGAIN;

	/**
	 * ID: 9758<br>
	 * Message: $s1！あの野郎がお前を睨んでるぞ！ぼーっとしてないで戦え！
	 */
	public static final NpcStringId S1_ITS_AFTER_YOU_FIGHT;

	/**
	 * ID: 9759<br>
	 * Message: $s1！なに？休みたいって？死ね！
	 */
	public static final NpcStringId S1_YOU_HAVE_TO_FIGHT_BETTER_THAN_THAT_IF_YOU_EXPECT_TO_SURVIVE;

	/**
	 * ID: 9760<br>
	 * Message: $s1！気合入れて戦うんだ！
	 */
	public static final NpcStringId S1_PULL_YOURSELF_TOGETHER_FIGHT;

	/**
	 * ID: 9761<br>
	 * Message: それじゃ、私はあいつを追うぞ。
	 */
	public static final NpcStringId ILL_CATCH_THE_CUNNING_FIEND;

	/**
	 * ID: 9762<br>
	 * Message: ちぇ、また逃したか。すばしっこいやつめ！
	 */
	public static final NpcStringId I_MISSED_HIM_AGAIN_HES_CLEVER;

	/**
	 * ID: 9763<br>
	 * Message: 次は半人前から抜け出しておくんだな！
	 */
	public static final NpcStringId DONT_COWER_LIKE_A_PUPPY_NEXT_TIME;

	/**
	 * ID: 9764<br>
	 * Message: 私の目標はただ1つ！邪魔せずそこをどけ。
	 */
	public static final NpcStringId I_HAVE_ONLY_ONE_GOAL_GET_OUT_OF_MY_WAY;

	/**
	 * ID: 9765<br>
	 * Message: 私がお前に天罰を下すまで待っているんだな。
	 */
	public static final NpcStringId JUST_WAIT_YOULL_GET_YOURS;

	/**
	 * ID: 9766<br>
	 * Message: $s1！この卑怯者め！
	 */
	public static final NpcStringId S1_YOURE_A_COWARD_ARENT_YOU;

	/**
	 * ID: 9767<br>
	 * Message: $s1！次こそお前を必ず倒してやる。
	 */
	public static final NpcStringId S1_ILL_KILL_YOU_NEXT_TIME;

	/**
	 * ID: 9850<br>
	 * Message: $s1！お前が神の御心に逆らうとは。こざかしい。
	 */
	public static final NpcStringId S1_HOW_FOOLISH_TO_ACT_AGAINST_THE_WILL_OF_GOD;

	/**
	 * ID: 9851<br>
	 * Message: 思いのほか信仰心が厚いやつだな。また会おう。
	 */
	public static final NpcStringId YOUR_FAITH_IS_STRONGER_THAN_I_THOUGHT_ILL_GET_YOU_NEXT_TIME;

	/**
	 * ID: 9855<br>
	 * Message: 戦いを邪魔するお前は何者だ！？卑怯なやつめ。
	 */
	public static final NpcStringId WHO_ARE_YOU_MIND_YOUR_OWN_BUSINESS_YOU_COWARD;

	/**
	 * ID: 9857<br>
	 * Message: タナキアさん、あなたの嘘はもうバレてるんですよ。
	 */
	public static final NpcStringId TANAKIA_YOUR_LIE_HAS_ALREADY_BEEN_EXPOSED;

	/**
	 * ID: 9858<br>
	 * Message: $s1！私とともにこの試練を乗り越えましょう。
	 */
	public static final NpcStringId S1_HELP_ME_OVERCOME_THIS;

	/**
	 * ID: 9859<br>
	 * Message: $s1！この程度じゃタナキアは倒せません。
	 */
	public static final NpcStringId S1_WE_CANT_DEFEAT_TANAKIA_THIS_WAY;

	/**
	 * ID: 9860<br>
	 * Message: $s1！チャンスは今しかないんです！早く！
	 */
	public static final NpcStringId S1_HERES_OUR_CHANCE_DONT_SQUANDER_THE_OPPORTUNITY;

	/**
	 * ID: 9861<br>
	 * Message: またご縁があればお会いできるでしょう。さようなら．．．
	 */
	public static final NpcStringId GOODBYE_WELL_MEET_AGAIN_IF_FATE_ALLOWS;

	/**
	 * ID: 9862<br>
	 * Message: 過ちの運命を正すために、タナキアを追います。
	 */
	public static final NpcStringId ILL_FOLLOW_TANAKIA_TO_CORRECT_THIS_FALSEHOOD;

	/**
	 * ID: 9863<br>
	 * Message: あなたが私の運命の流れと交わる時、またお会いするでしょう。
	 */
	public static final NpcStringId ILL_BE_BACK_IF_FATE_ALLOWS;

	/**
	 * ID: 9865<br>
	 * Message: 私は戻ってくる。その時には覚悟するがいい。
	 */
	public static final NpcStringId ILL_BE_BACK_YOULL_PAY;

	/**
	 * ID: 9866<br>
	 * Message: $s1！私の計画を壊すつもりか！
	 */
	public static final NpcStringId S1_ARE_YOU_TRYING_TO_SPOIL_MY_PLAN;

	/**
	 * ID: 9867<br>
	 * Message: $s1！覚えていろ。貴様のことは絶対忘れない。
	 */
	public static final NpcStringId S1_I_WONT_FORGET_YOU_YOULL_PAY;

	/**
	 * ID: 9950<br>
	 * Message: お前が$s1か。不穏な知識を貪る者よ、死を覚悟するがいい．．．
	 */
	public static final NpcStringId S1_YOU_HAVE_AN_AFFINITY_FOR_DANGEROUS_IDEAS_ARE_YOU_READY_TO_DIE;

	/**
	 * ID: 9951<br>
	 * Message: 私に与えられた時間は使い果たした．．．
	 */
	public static final NpcStringId MY_TIME_IS_UP;

	/**
	 * ID: 9952<br>
	 * Message: たかが人間ごときにひざまずくとは！あり得ないことだ！
	 */
	public static final NpcStringId I_CANT_BELIEVE_I_MUST_KNEEL_TO_A_HUMAN;

	/**
	 * ID: 9957<br>
	 * Message: ミネルヴィア！今日こそ決着をつけようぞ！
	 */
	public static final NpcStringId MINERVIA_WHATS_THE_MATTER;

	/**
	 * ID: 9958<br>
	 * Message: くーっ！おい！乙女がピンチなのに、手をこまねいて見てるのかよ！
	 */
	public static final NpcStringId THE_PRINCESS_IS_IN_DANGER_WHY_ARE_YOU_STARING;

	/**
	 * ID: 9959<br>
	 * Message: がんばってよ！$s1さん、早く！
	 */
	public static final NpcStringId MASTER_S1_COME_ON_HURRY_UP;

	/**
	 * ID: 9960<br>
	 * Message: 逃しちゃダメ！$s1さん、がんばって！
	 */
	public static final NpcStringId WE_CANT_FAIL_MASTER_S1_PULL_YOURSELF_TOGETHER;

	/**
	 * ID: 9961<br>
	 * Message: こうしてる場合じゃないわ．．．それじゃ！元気でね！
	 */
	public static final NpcStringId WHAT_AM_I_DOING_I_GOTTA_GO_GOODBYE;

	/**
	 * ID: 9962<br>
	 * Message: ああっ！逃しちゃった．．．
	 */
	public static final NpcStringId DAMMIT_I_MISSED;

	/**
	 * ID: 9963<br>
	 * Message: 残念だけどまたお別れですね．．．幸運を祈るわ！
	 */
	public static final NpcStringId SORRY_BUT_I_MUST_SAY_GOODBYE_AGAIN_GOOD_LUCK_TO_YOU;

	/**
	 * ID: 9964<br>
	 * Message: 決してタブレットの秘密を譲ることはできない！
	 */
	public static final NpcStringId I_CANT_YIELD_THE_SECRET_OF_THE_TABLET;

	/**
	 * ID: 9965<br>
	 * Message: このぐらいにしておいたほうがいいな．．．
	 */
	public static final NpcStringId ILL_STOP_HERE_FOR_NOW;

	/**
	 * ID: 9966<br>
	 * Message: $s1、私の顔に傷をつけるとは！死ね！
	 */
	public static final NpcStringId S1_YOU_DARED_TO_LEAVE_SCAR_ON_MY_FACE_ILL_KILL_YOU;

	/**
	 * ID: 9967<br>
	 * Message: $s1、その名前、覚えておこう．．．ぐむっ！
	 */
	public static final NpcStringId S1_I_WONT_FORGET_YOUR_NAMEHA;

	/**
	 * ID: 10050<br>
	 * Message: お前が$s1か。不穏な知識を貪る者よ、死を覚悟するがいい．．．
	 */
	public static final NpcStringId S1_YOU_HAVE_AN_AFFINITY_FOR_BAD_IDEAS_ARE_YOU_READY_TO_DIE;

	/**
	 * ID: 10052<br>
	 * Message: たかが人間ごときにひざまずくとは！あり得ないことだ！
	 */
	public static final NpcStringId I_CANT_BELIEVE_I_MUST_KNEEL_BEFORE_A_HUMAN;

	/**
	 * ID: 10057<br>
	 * Message: こ、この悪党！こ、交感石を渡せ！
	 */
	public static final NpcStringId YOU_THIEF_GIVE_ME_THE_RESONANCE_AMULET;

	/**
	 * ID: 10058<br>
	 * Message: ううっ！$s1、助けてくれ！
	 */
	public static final NpcStringId UGH_S1_HELP_ME;

	/**
	 * ID: 10059<br>
	 * Message: $s1、頼む！力を合わせれば勝てる！
	 */
	public static final NpcStringId S1_PLEASE_HELP_ME_TOGETHER_WE_CAN_BEAT_HIM;

	/**
	 * ID: 10060<br>
	 * Message: $s1！仲間がやられているのを放っておくつもりか！
	 */
	public static final NpcStringId S1_ARE_YOU_GOING_TO_LET_A_GUILD_MEMBER_DIE;

	/**
	 * ID: 10061<br>
	 * Message: ごめん、やっぱりだめだ！先に行くぞ！
	 */
	public static final NpcStringId IM_SORRY_BUT_I_GOTTA_GO_FIRST;

	/**
	 * ID: 10062<br>
	 * Message: ううっ．．．交換石も手に入らず．．．
	 */
	public static final NpcStringId AAAAH_I_COULDNT_GET_THE_RESONANCE_AMULET;

	/**
	 * ID: 10063<br>
	 * Message: それじゃ、お疲れ！私はこの辺で～
	 */
	public static final NpcStringId TAKE_CARE_I_GOTTA_GO_NOW;

	/**
	 * ID: 10064<br>
	 * Message: 残念だが、これも仕事のうち．．．死んでもらおう！
	 */
	public static final NpcStringId IM_SORRY_BUT_ITS_MY_JOB_TO_KILL_YOU_NOW;

	/**
	 * ID: 10065<br>
	 * Message: ふっ、時間の無駄だったな！
	 */
	public static final NpcStringId WHAT_A_WASTE_OF_TIME;

	/**
	 * ID: 10066<br>
	 * Message: $s1！貴様が？生意気なやつめ！息の根を止めてやる！
	 */
	public static final NpcStringId S1_HOW_COULD_YOU_DO_THIS_ILL_KILL_YOU;

	/**
	 * ID: 10067<br>
	 * Message: $s1！この借りは必ず返すぞ！
	 */
	public static final NpcStringId S1_ILL_PAY_YOU_BACK;

	/**
	 * ID: 10068<br>
	 * Message: お願いだから死んでくれ！
	 */
	public static final NpcStringId WHY_DONT_YOU_JUST_DIE;

	/**
	 * ID: 10069<br>
	 * Message: スポイル5段をなめんなよ！
	 */
	public static final NpcStringId TASTE_THE_STING_OF_LEVEL_5_SPOIL;

	/**
	 * ID: 10070<br>
	 * Message: いいモンたくさん持ってそうだなぁ～
	 */
	public static final NpcStringId THE_ITEM_IS_ALREADY_INSIDE_YOU;

	/**
	 * ID: 10071<br>
	 * Message: 貴重なポーションを飲むはめになるなんて！
	 */
	public static final NpcStringId THIS_POTION_YOURE_MAKING_ME_DRINK_IS_WORTH_ITS_WEIGHT_IN_GOLD;

	/**
	 * ID: 10072<br>
	 * Message: グリズリーの肝で作ったポーションだ！覚悟しろ！
	 */
	public static final NpcStringId THIS_POTION_IS_PREPARED_FROM_THE_GROUND_GALL_OF_A_BEAR_BE_CAREFUL_IT_PACKS_QUITE_A_PUNCH;

	/**
	 * ID: 10073<br>
	 * Message: ひよっこにポーションを使うことになるなんて！
	 */
	public static final NpcStringId HOW_CAN_YOU_USE_A_POTION_ON_A_NEWBIE;

	/**
	 * ID: 10074<br>
	 * Message: $s1、許可された者以外は武器の所持を禁じられている！
	 */
	public static final NpcStringId LISTEN_TO_ME_S1_UNLESS_YOU_HAVE_PRIOR_AUTHORIZATION_YOU_CANT_CARRY_A_WEAPON_HERE;

	/**
	 * ID: 10075<br>
	 * Message: $s1、アインハザード様の祝福のあらんことを！
	 */
	public static final NpcStringId DEAR_S1_MAY_THE_BLESSINGS_OF_EINHASAD_BE_WITH_YOU_ALWAYS;

	/**
	 * ID: 10076<br>
	 * Message: $s1、光の道を共に歩む兄弟よ．．．
	 */
	public static final NpcStringId DEAR_BROTHER_S1_FOLLOW_THE_PATH_OF_LIGHT_WITH_ME;

	/**
	 * ID: 10077<br>
	 * Message: $s1、闇の道を選ぶとは！
	 */
	public static final NpcStringId S1_WHY_WOULD_YOU_CHOOSE_THE_PATH_OF_DARKNESS;

	/**
	 * ID: 10078<br>
	 * Message: $s1、アインハザード様の御心に背くとは！
	 */
	public static final NpcStringId S1_HOW_DARE_YOU_DEFY_THE_WILL_OF_EINHASAD;

	/**
	 * ID: 10079<br>
	 * Message: 祭壇3階の扉が開かれました。
	 */
	public static final NpcStringId THE_DOOR_TO_THE_3RD_FLOOR_OF_THE_ALTAR_IS_NOW_OPEN;

	/**
	 * ID: 11101<br>
	 * Message: エルクロッキー ハンターの証
	 */
	public static final NpcStringId ELROKIAN_HUNTERS;

	/**
	 * ID: 11450<br>
	 * Message: ウェンディを襲う$s1、死ね！
	 */
	public static final NpcStringId YOU_S1_YOU_ATTACKED_WENDY_PREPARE_TO_DIE;

	/**
	 * ID: 11451<br>
	 * Message: 敵$s1を追い返しました。いったん引き返して待機します。
	 */
	public static final NpcStringId S1_YOUR_ENEMY_WAS_DRIVEN_OUT_I_WILL_NOW_WITHDRAW_AND_AWAIT_YOUR_NEXT_COMMAND;

	/**
	 * ID: 11452<br>
	 * Message: 想像以上に敵強し。戦闘不能。退散。
	 */
	public static final NpcStringId THIS_ENEMY_IS_FAR_TOO_POWERFUL_FOR_ME_TO_FIGHT_I_MUST_WITHDRAW;

	/**
	 * ID: 11453<br>
	 * Message: 電波探知器が反応している。#近くで目につくものは、怪しげな石の山だけだ。
	 */
	public static final NpcStringId THE_RADIO_SIGNAL_DETECTOR_IS_RESPONDING_A_SUSPICIOUS_PILE_OF_STONES_CATCHES_YOUR_EYE;

	/**
	 * ID: 11550<br>
	 * Message: ここらへんのはずなんだけど．．．
	 */
	public static final NpcStringId THIS_LOOKS_LIKE_THE_RIGHT_PLACE;

	/**
	 * ID: 11551<br>
	 * Message: 誰かいる。これも運命なのか．．．
	 */
	public static final NpcStringId I_SEE_SOMEONE_IS_THIS_FATE;

	/**
	 * ID: 11552<br>
	 * Message: あなたにここでまた会えるとは。
	 */
	public static final NpcStringId WE_MEET_AGAIN;

	/**
	 * ID: 11553<br>
	 * Message: 私のことはいい。あなたはあなたの運命に従いなさい。
	 */
	public static final NpcStringId DONT_BOTHER_TRYING_TO_FIND_OUT_MORE_ABOUT_ME_FOLLOW_YOUR_OWN_DESTINY;

	/**
	 * ID: 14204<br>
	 * Message: ファーレン エンジェル：選択
	 */
	public static final NpcStringId FALLEN_ANGEL_SELECT;

	/**
	 * ID: 15804<br>
	 * Message: 生意気にも私に挑むとは！
	 */
	public static final NpcStringId _HOW_DARE_YOU_CHALLENGE_ME;

	/**
	 * ID: 15805<br>
	 * Message: べレス様の権勢が天下に．．．
	 */
	public static final NpcStringId THE_POWER_OF_LORD_BELETH_RULES_THE_WHOLE_WORLD;

	/**
	 * ID: 16404<br>
	 * Message: そなたの熱い血を味あわせてもらおう！
	 */
	public static final NpcStringId I_WILL_TASTE_YOUR_BLOOD;

	/**
	 * ID: 16405<br>
	 * Message: クリミスとの契約が終了した．．．
	 */
	public static final NpcStringId I_HAVE_FULFILLED_MY_CONTRACT_WITH_TRADER_CREAMEES;

	/**
	 * ID: 17004<br>
	 * Message: 醒めることのない悪夢に陥れてやる！
	 */
	public static final NpcStringId ILL_CAST_YOU_INTO_AN_ETERNAL_NIGHTMARE;

	/**
	 * ID: 17005<br>
	 * Message: 私の魂はイカルス様のもとへ．．．
	 */
	public static final NpcStringId SEND_MY_SOUL_TO_LICH_KING_ICARUS;

	/**
	 * ID: 17151<br>
	 * Message: 今回はこれくらいにして引き上げよう．．．
	 */
	public static final NpcStringId YOU_SHOULD_CONSIDER_GOING_BACK;

	/**
	 * ID: 17951<br>
	 * Message: ベールに包まれた創造主．．．
	 */
	public static final NpcStringId THE_VEILED_CREATOR;

	/**
	 * ID: 17952<br>
	 * Message: 封印されなければならなかった種族
	 */
	public static final NpcStringId THE_CONSPIRACY_OF_THE_ANCIENT_RACE;

	/**
	 * ID: 17953<br>
	 * Message: 混沌の未来．．．
	 */
	public static final NpcStringId CHAOS_AND_TIME;

	/**
	 * ID: 18451<br>
	 * Message: 侵入者発見！情報漏れを防ぐため、2分後に記録装置を破壊します。
	 */
	public static final NpcStringId INTRUDER_ALERT_THE_ALARM_WILL_SELF_DESTRUCT_IN_2_MINUTES;

	/**
	 * ID: 18452<br>
	 * Message: 記録装置の破壊60秒前です。破壊を止めるにはパスワードを入力してください。
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;

	/**
	 * ID: 18453<br>
	 * Message: 記録装置の破壊30秒前です。破壊を止めるにはパスワードを入力してください。
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;

	/**
	 * ID: 18454<br>
	 * Message: 記録装置の破壊10秒前です。破壊を止めるにはパスワードを入力してください。
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_ENTER_PASSCODE_TO_OVERRIDE;

	/**
	 * ID: 18455<br>
	 * Message: 記録装置が破壊されました。
	 */
	public static final NpcStringId RECORDER_CRUSHED;

	/**
	 * ID: 18552<br>
	 * Message: 記録装置爆破60秒前です。安全な場所に避難してください。
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_60_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;

	/**
	 * ID: 18553<br>
	 * Message: 記録装置爆破30秒前です。安全な場所に避難してください。
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_30_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;

	/**
	 * ID: 18554<br>
	 * Message: 記録装置爆破10秒前です。安全な場所に避難してください。
	 */
	public static final NpcStringId THE_ALARM_WILL_SELF_DESTRUCT_IN_10_SECONDS_PLEASE_EVACUATE_IMMEDIATELY;

	/**
	 * ID: 19304<br>
	 * Message: $s1よ！その品の主はおまえではない！
	 */
	public static final NpcStringId S1_YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM;

	/**
	 * ID: 19305<br>
	 * Message: 気をつけろ！次は生きて帰さねえぞ！
	 */
	public static final NpcStringId NEXT_TIME_YOU_WILL_NOT_ESCAPE;

	/**
	 * ID: 19306<br>
	 * Message: $s1！今日のところは引き下がるが、いつかはお前を捕まえてやる！
	 */
	public static final NpcStringId S1_YOU_MAY_HAVE_WON_THIS_TIME_BUT_NEXT_TIME_I_WILL_SURELY_CAPTURE_YOU;

	/**
	 * ID: 19504<br>
	 * Message: 侵入者だ！黎明の司祭団を守れ！
	 */
	public static final NpcStringId INTRUDER_PROTECT_THE_PRIESTS_OF_DAWN;

	/**
	 * ID: 19505<br>
	 * Message: お前は誰だ？見たことない顔だな。おまえはここには近寄れない。
	 */
	public static final NpcStringId WHO_ARE_YOU_A_NEW_FACE_LIKE_YOU_CANT_APPROACH_THIS_PLACE;

	/**
	 * ID: 19506<br>
	 * Message: そんな子供だましの変身術でここに忍び込むとは！出て行け！
	 */
	public static final NpcStringId HOW_DARE_YOU_INTRUDE_WITH_THAT_TRANSFORMATION_GET_LOST;

	/**
	 * ID: 19604<br>
	 * Message: いったい誰だ！マモンの商人を呼び出したのは！
	 */
	public static final NpcStringId WHO_DARES_SUMMON_THE_MERCHANT_OF_MAMMON;

	/**
	 * ID: 19605<br>
	 * Message: 皇帝との古い約束を守ったぞ．．．
	 */
	public static final NpcStringId THE_ANCIENT_PROMISE_TO_THE_EMPEROR_HAS_BEEN_FULFILLED;

	/**
	 * ID: 19606<br>
	 * Message: アインハザードよ、永遠なれ！
	 */
	public static final NpcStringId FOR_THE_ETERNITY_OF_EINHASAD;

	/**
	 * ID: 19607<br>
	 * Message: シーレンの子供たちよ！所詮お前たちは我々の相手ではない！
	 */
	public static final NpcStringId DEAR_SHILLIENS_OFFSPRINGS_YOU_ARE_NOT_CAPABLE_OF_CONFRONTING_US;

	/**
	 * ID: 19608<br>
	 * Message: アインハザード様の真の力を見せてやろう！
	 */
	public static final NpcStringId ILL_SHOW_YOU_THE_REAL_POWER_OF_EINHASAD;

	/**
	 * ID: 19609<br>
	 * Message: 星の軍勢！シーレンの子供たちを倒せ！
	 */
	public static final NpcStringId DEAR_MILITARY_FORCE_OF_LIGHT_GO_DESTROY_THE_OFFSPRINGS_OF_SHILLIEN;

	/**
	 * ID: 19610<br>
	 * Message: これもすべて$s1様のおかげです。ありがとうございます。
	 */
	public static final NpcStringId EVERYTHING_IS_OWING_TO_S1_THANK_YOU;

	/**
	 * ID: 19611<br>
	 * Message: 私の力はもう弱りつつあります。早く封印装置を作動させてください！
	 */
	public static final NpcStringId MY_POWERS_WEAKENING_HURRY_AND_TURN_ON_THE_SEALING_DEVICE;

	/**
	 * ID: 19612<br>
	 * Message: 封印装置を4つすべて作動させなければなりません！
	 */
	public static final NpcStringId ALL_4_SEALING_DEVICES_MUST_BE_TURNED_ON;

	/**
	 * ID: 19613<br>
	 * Message: リリスの攻撃が激しくなりつつあります。早く作動させてください！
	 */
	public static final NpcStringId LILITHS_ATTACK_IS_GETTING_STRONGER_GO_AHEAD_AND_TURN_IT_ON;

	/**
	 * ID: 19614<br>
	 * Message: $s1！がんばってください。
	 */
	public static final NpcStringId DEAR_S1_GIVE_ME_MORE_STRENGTH;

	/**
	 * ID: 19615<br>
	 * Message: この愚か者めが！この戦争の真の勝者はシーレン様だ！
	 */
	public static final NpcStringId YOU_SUCH_A_FOOL_THE_VICTORY_OVER_THIS_WAR_BELONGS_TO_SHILIEN;

	/**
	 * ID: 19616<br>
	 * Message: 生意気にも私と戦いたいと？笑止千万！
	 */
	public static final NpcStringId HOW_DARE_YOU_TRY_TO_CONTEND_AGAINST_ME_IN_STRENGTH_RIDICULOUS;

	/**
	 * ID: 19617<br>
	 * Message: アナキム！偉大なるシーレン様の御名のもとに息の根を止めてやる！
	 */
	public static final NpcStringId ANAKIM_IN_THE_NAME_OF_GREAT_SHILIEN_I_WILL_CUT_YOUR_THROAT;

	/**
	 * ID: 19618<br>
	 * Message: 所詮お前は私の相手ではない！体でわからせてやろうか！
	 */
	public static final NpcStringId YOU_CANNOT_BE_THE_MATCH_OF_LILITH_ILL_TEACH_YOU_A_LESSON;

	/**
	 * ID: 19619<br>
	 * Message: こんな姿でシーレン様の懐に戻らねばならないとは．．．くやしい．．．
	 */
	public static final NpcStringId I_MUST_GO_BACK_TO_SHILIEN_JUST_LIKE_THIS_OUTRAGEOUS;

	/**
	 * ID: 19804<br>
	 * Message: 黎明の君主に刃向かう者に死を！
	 */
	public static final NpcStringId DEATH_TO_THE_ENEMIES_OF_THE_LORDS_OF_DAWN;

	/**
	 * ID: 19805<br>
	 * Message: お前の未来には、常に我々黎明がいる。約束しよう！
	 */
	public static final NpcStringId WE_WILL_BE_WITH_YOU_ALWAYS;

	/**
	 * ID: 19806<br>
	 * Message: その品の主はおまえではない！
	 */
	public static final NpcStringId YOU_ARE_NOT_THE_OWNER_OF_THAT_ITEM;

	/**
	 * ID: 19807<br>
	 * Message: エンブリオ．．．
	 */
	public static final NpcStringId EMBRYO;

	/**
	 * ID: 20901<br>
	 * Message: カマエル チュートリアル
	 */
	public static final NpcStringId KAMAEL_TUTORIAL;

	/**
	 * ID: 22051<br>
	 * Message: カカイの手下か！
	 */
	public static final NpcStringId IS_IT_A_LACKEY_OF_KAKAI;

	/**
	 * ID: 22052<br>
	 * Message: もう遅い！
	 */
	public static final NpcStringId TOO_LATE;

	/**
	 * ID: 22055<br>
	 * Message: 悔しいぜっ、濡れ衣だ！
	 */
	public static final NpcStringId HOW_REGRETFUL_UNJUST_DISHONOR;

	/**
	 * ID: 22056<br>
	 * Message: いつか必ず復讐してやる！
	 */
	public static final NpcStringId ILL_GET_REVENGE_SOMEDAY;

	/**
	 * ID: 22057<br>
	 * Message: 腹立たしい不当な死！
	 */
	public static final NpcStringId INDIGNANT_AND_UNFAIR_DEATH;

	/**
	 * ID: 22719<br>
	 * Message: 隠された真実はいつか解き明かされるだろう．．．
	 */
	public static final NpcStringId THE_CONCEALED_TRUTH_WILL_ALWAYS_BE_REVEALED;

	/**
	 * ID: 22720<br>
	 * Message: こざかしいやつめ！
	 */
	public static final NpcStringId COWARDLY_GUY;

	/**
	 * ID: 22819<br>
	 * Message: 私は虚無の木．．．戻るべきところを知っている．．．木．．．
	 */
	public static final NpcStringId I_AM_A_TREE_OF_NOTHING_A_TREE_THAT_KNOWS_WHERE_TO_RETURN;

	/**
	 * ID: 22820<br>
	 * Message: 私は胸の奥深くの真実を見せる存在．．．
	 */
	public static final NpcStringId I_AM_A_CREATURE_THAT_SHOWS_THE_TRUTH_OF_THE_PLACE_DEEP_IN_MY_HEART;

	/**
	 * ID: 22821<br>
	 * Message: 私は闇の鏡．．．闇の虚像．．．
	 */
	public static final NpcStringId I_AM_A_MIRROR_OF_DARKNESS_A_VIRTUAL_IMAGE_OF_DARKNESS;

	/**
	 * ID: 22933<br>
	 * Message: 絶対に渡さんぞ！これは私の貴重な財宝だ！
	 */
	public static final NpcStringId I_ABSOLUTELY_CANNOT_GIVE_IT_TO_YOU_IT_IS_MY_PRECIOUS_JEWEL;

	/**
	 * ID: 22934<br>
	 * Message: お前らの命を奪うのは、次までお預けだ！
	 */
	public static final NpcStringId ILL_TAKE_YOUR_LIVES_LATER;

	/**
	 * ID: 22935<br>
	 * Message: その剣はまさか．．．
	 */
	public static final NpcStringId THAT_SWORD_IS_REALLY;

	/**
	 * ID: 22936<br>
	 * Message: だめです！まだ破壊と殺戮の使命を果たしていません！
	 */
	public static final NpcStringId NO_I_HAVENT_COMPLETELY_FINISHED_THE_COMMAND_FOR_DESTRUCTION_AND_SLAUGHTER_YET;

	/**
	 * ID: 22937<br>
	 * Message: 私の眠りを妨げるとは！死ぬがいい！
	 */
	public static final NpcStringId HOW_DARE_YOU_WAKE_ME_NOW_YOU_SHALL_DIE;

	/**
	 * ID: 23060<br>
	 * Message: ケ ッ ト ウ ヲ ハ ジ メ マ ス．．．
	 */
	public static final NpcStringId START_DUEL;

	/**
	 * ID: 23061<br>
	 * Message: ハ ン ソ ク デ ス．．．
	 */
	public static final NpcStringId RULE_VIOLATION;

	/**
	 * ID: 23062<br>
	 * Message: ワ タ シ ノ マ ケ デ ス．．．
	 */
	public static final NpcStringId I_LOSE;

	/**
	 * ID: 23063<br>
	 * Message: にゃにゃ～ん！
	 */
	public static final NpcStringId WHHIISSHH;

	/**
	 * ID: 23065<br>
	 * Message: 御主人様、ごめんにゃ～ん！
	 */
	public static final NpcStringId IM_SORRY_LORD;

	/**
	 * ID: 23066<br>
	 * Message: にゃん！跳びかかれ！
	 */
	public static final NpcStringId WHISH_FIGHT;

	/**
	 * ID: 23068<br>
	 * Message: 負けたにゃん！御主人様許してにゃん！
	 */
	public static final NpcStringId LOST_SORRY_LORD;

	/**
	 * ID: 23072<br>
	 * Message: それじゃ、始めるとするか！
	 */
	public static final NpcStringId SO_SHALL_WE_START;

	/**
	 * ID: 23074<br>
	 * Message: ううっ！私が負けるとは！
	 */
	public static final NpcStringId UGH_I_LOST;

	/**
	 * ID: 23075<br>
	 * Message: 徐々に踏んづけてやる！
	 */
	public static final NpcStringId ILL_WALK_ALL_OVER_YOU;

	/**
	 * ID: 23077<br>
	 * Message: くうっ！そんな馬鹿な！
	 */
	public static final NpcStringId UGH_CAN_THIS_BE_HAPPENING;

	/**
	 * ID: 23078<br>
	 * Message: 当然の結果だ！
	 */
	public static final NpcStringId ITS_THE_NATURAL_RESULT;

	/**
	 * ID: 23079<br>
	 * Message: ほほ～っ！私の勝ちだ！
	 */
	public static final NpcStringId HO_HO_I_WIN;

	/**
	 * ID: 23080<br>
	 * Message: ワ タ シ ノ シ ョ ウ リ デ ス．．．
	 */
	public static final NpcStringId I_WIN;

	/**
	 * ID: 23081<br>
	 * Message: にゃん！勝ったにゃん！
	 */
	public static final NpcStringId WHISH_I_WON;

	/**
	 * ID: 23434<br>
	 * Message: 誰が私の高貴な純血をそんなに欲しているのだ！
	 */
	public static final NpcStringId WHO_DARES_TO_TRY_AND_STEAL_MY_NOBLE_BLOOD;

	/**
	 * ID: 23651<br>
	 * Message: $s1！やっと会えたな！
	 */
	public static final NpcStringId S1_FINALLY_WE_MEET;

	/**
	 * ID: 23652<br>
	 * Message: ふむ、あいつはどこ行ったんだ？
	 */
	public static final NpcStringId HMM_WHERE_DID_MY_FRIEND_GO;

	/**
	 * ID: 23653<br>
	 * Message: 後はしっかり頼んだぞ．．．
	 */
	public static final NpcStringId BEST_OF_LUCK_WITH_YOUR_FUTURE_ENDEAVOURS;

	/**
	 * ID: 23661<br>
	 * Message: $s1！かなり待ったか。
	 */
	public static final NpcStringId S1_DID_YOU_WAIT_FOR_LONG;

	/**
	 * ID: 23671<br>
	 * Message: 私に言われた物は持って来たか。 $s1
	 */
	public static final NpcStringId DID_YOU_BRING_WHAT_I_ASKED_S1;

	/**
	 * ID: 23681<br>
	 * Message: おや？近くに誰か来たのか。
	 */
	public static final NpcStringId HMM_IS_SOMEONE_APPROACHING;

	/**
	 * ID: 23682<br>
	 * Message: なんと、敵の攻撃が！
	 */
	public static final NpcStringId GRAAAH_WERE_BEING_ATTACKED;

	/**
	 * ID: 23683<br>
	 * Message: よし。ではしっかり頼んだぞ。
	 */
	public static final NpcStringId IN_THAT_CASE_I_WISH_YOU_GOOD_LUCK;

	/**
	 * ID: 23685<br>
	 * Message: $s1、全部探して来たか。
	 */
	public static final NpcStringId S1_HAS_EVERYTHING_BEEN_FOUND;

	/**
	 * ID: 23687<br>
	 * Message: では、また会おう！
	 */
	public static final NpcStringId SAFE_TRAVELS;

	/**
	 * ID: 25901<br>
	 * Message: 牧場主の依頼
	 */
	public static final NpcStringId REQUEST_FROM_THE_FARM_OWNER;

	/**
	 * ID: 31603<br>
	 * Message: なぜ我々を弾圧する．．．
	 */
	public static final NpcStringId WHY_DO_YOU_OPPRESS_US_SO;

	/**
	 * ID: 33409<br>
	 * Message: 二度と私の休息の邪魔をするな。
	 */
	public static final NpcStringId DONT_INTERRUPT_MY_REST_AGAIN;

	/**
	 * ID: 33410<br>
	 * Message: これからはお前がグレート デーモン キングだ．．．
	 */
	public static final NpcStringId YOURE_A_GREAT_DEVIL_NOW;

	/**
	 * ID: 33411<br>
	 * Message: やはり私の敵ではなかったか、ウハハハハ！
	 */
	public static final NpcStringId OH_ITS_NOT_AN_OPPONENT_OF_MINE_HA_HA_HA;

	/**
	 * ID: 33412<br>
	 * Message: グ、グレート デーモン キング様．．．
	 */
	public static final NpcStringId OH_GREAT_DEMON_KING;

	/**
	 * ID: 33413<br>
	 * Message: 復讐は魔界の絶対者、ラムセバリウス様が！
	 */
	public static final NpcStringId REVENGE_IS_OVERLORD_RAMSEBALIUS_OF_THE_EVIL_WORLD;

	/**
	 * ID: 33414<br>
	 * Message: 煉獄の王子、ボナパルテリウス様がお前を懲らしめるだろう！
	 */
	public static final NpcStringId BONAPARTERIUS_ABYSS_KING_WILL_PUNISH_YOU;

	/**
	 * ID: 33415<br>
	 * Message: 皆、切に祈りなさい！
	 */
	public static final NpcStringId OK_EVERYBODY_PRAY_FERVENTLY;

	/**
	 * ID: 33416<br>
	 * Message: 両手を天に！叫ぶのです！
	 */
	public static final NpcStringId BOTH_HANDS_TO_HEAVEN_EVERYBODY_YELL_TOGETHER;

	/**
	 * ID: 33417<br>
	 * Message: さぁ！願いよ叶え！
	 */
	public static final NpcStringId ONE_TWO_MAY_YOUR_DREAMS_COME_TRUE;

	/**
	 * ID: 33418<br>
	 * Message: 私の部下のデーモン キングを殺したのは誰だ？
	 */
	public static final NpcStringId WHO_KILLED_MY_UNDERLING_DEVIL;

	/**
	 * ID: 33420<br>
	 * Message: あなたの恋を叶えてあげましょう～ランランラン～
	 */
	public static final NpcStringId I_WILL_MAKE_YOUR_LOVE_COME_TRUE_LOVE_LOVE_LOVE;

	/**
	 * ID: 33421<br>
	 * Message: 私の中に知恵が詰まっています。そんな私は知恵の箱～
	 */
	public static final NpcStringId I_HAVE_WISDOM_IN_ME_I_AM_THE_BOX_OF_WISDOM;

	/**
	 * ID: 33422<br>
	 * Message: クオオオオ！
	 */
	public static final NpcStringId OH_OH_OH;

	/**
	 * ID: 33423<br>
	 * Message: 私たちが愛してあげましょうか。ウフ。
	 */
	public static final NpcStringId DO_YOU_WANT_US_TO_LOVE_YOU_OH;

	/**
	 * ID: 33424<br>
	 * Message: 暗黒の君主を呼ぶ者は誰だ！
	 */
	public static final NpcStringId WHO_IS_CALLING_THE_LORD_OF_DARKNESS;

	/**
	 * ID: 33425<br>
	 * Message: 我は偉大なる皇子、ボナパルテリウス！
	 */
	public static final NpcStringId I_AM_A_GREAT_EMPIRE_BONAPARTERIUS;

	/**
	 * ID: 33426<br>
	 * Message: この絶対者の前に平伏すがいい！
	 */
	public static final NpcStringId LET_YOUR_HEAD_DOWN_BEFORE_THE_LORD;

	/**
	 * ID: 33501<br>
	 * Message: ハンターの歌
	 */
	public static final NpcStringId THE_SONG_OF_THE_HUNTER;

	/**
	 * ID: 33511<br>
	 * Message: 昔の帝国の遺産は我々がもらって行くぞ！
	 */
	public static final NpcStringId WELL_TAKE_THE_PROPERTY_OF_THE_ANCIENT_EMPIRE;

	/**
	 * ID: 33512<br>
	 * Message: 綺麗な光る物を出せ！全部私の物だ！
	 */
	public static final NpcStringId SHOW_ME_THE_PRETTY_SPARKLING_THINGS_THEYRE_ALL_MINE;

	/**
	 * ID: 33513<br>
	 * Message: うぇっ！
	 */
	public static final NpcStringId PRETTY_GOOD;

	/**
	 * ID: 34830<br>
	 * Message: フフフ．．．面白かった。カギは死体をくまなく捜してみることだ。
	 */
	public static final NpcStringId HA_THAT_WAS_FUN_IF_YOU_WISH_TO_FIND_THE_KEY_SEARCH_THE_CORPSE;

	/**
	 * ID: 34831<br>
	 * Message: カギは私が持っている。ほしいなら、力尽くで奪え！
	 */
	public static final NpcStringId I_HAVE_THE_KEY_WHY_DONT_YOU_COME_AND_TAKE_IT;

	/**
	 * ID: 34832<br>
	 * Message: 愚かな．．．神よ、この者たちを．．．
	 */
	public static final NpcStringId YOU_FOOLS_WILL_GET_WHATS_COMING_TO_YOU;

	/**
	 * ID: 34833<br>
	 * Message: 残念だが、お前たちの命を奪わなければならん。
	 */
	public static final NpcStringId SORRY_ABOUT_THIS_BUT_I_MUST_KILL_YOU_NOW;

	/**
	 * ID: 34835<br>
	 * Message: お前たちは知らない．．．7つの封印は．．．グホッ！
	 */
	public static final NpcStringId YOU_GUYS_WOULDNT_KNOW_THE_SEVEN_SEALS_ARE_ARRRGH;

	/**
	 * ID: 34836<br>
	 * Message: この山をお前たちの血で覆い尽くしてやろう！
	 */
	public static final NpcStringId I_SHALL_DRENCH_THIS_MOUNTAIN_WITH_YOUR_BLOOD;

	/**
	 * ID: 34837<br>
	 * Message: そなたたちが手にできる品ではない。手を引くがよい。
	 */
	public static final NpcStringId THAT_DOESNT_BELONG_TO_YOU_DONT_TOUCH_IT;

	/**
	 * ID: 34838<br>
	 * Message: 不信者め！消え失せるがよい！
	 */
	public static final NpcStringId GET_OUT_OF_MY_SIGHT_YOU_INFIDELS;

	/**
	 * ID: 34839<br>
	 * Message: 君にはこれ以上用はないが．．．死体にある鍵は探してみたか。
	 */
	public static final NpcStringId WE_DONT_HAVE_ANY_FURTHER_BUSINESS_TO_DISCUSS_HAVE_YOU_SEARCHED_THE_CORPSE_FOR_THE_KEY;

	/**
	 * ID: 35051<br>
	 * Message: $s1、ステップ $s2のブルー ソウル ストーンを手に入れました。
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_BLUE_SOUL_CRYSTAL;

	/**
	 * ID: 35052<br>
	 * Message: $s1、ステップ $s2のレッド ソウル ストーンを手に入れました。
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_RED_SOUL_CRYSTAL;

	/**
	 * ID: 35053<br>
	 * Message: $s1、ステップ $s2のグリーン ソウル ストーンを手に入れました。
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_GREEN_SOUL_CRYSTAL;

	/**
	 * ID: 35054<br>
	 * Message: $s1、ステップ $s2の呪われたブルー ソウル ストーンを手に入れました。
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_BLUE_CURSED_SOUL_CRYSTAL;

	/**
	 * ID: 35055<br>
	 * Message: $s1、ステップ $s2の呪われたレッド ソウル ストーンを手に入れました。
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_RED_CURSED_SOUL_CRYSTAL;

	/**
	 * ID: 35056<br>
	 * Message: $s1、ステップ $s2の呪われたグリーン ソウル ストーンを手に入れました。
	 */
	public static final NpcStringId S1_HAS_EARNED_A_STAGE_S2_GREEN_CURSED_SOUL_CRYSTAL;

	/**
	 * ID: 37101<br>
	 * Message: 霊魂たちの叫び
	 */
	public static final NpcStringId SHRIEKS_OF_GHOSTS;

	/**
	 * ID: 38451<br>
	 * Message: $s1番目の間：$s2！
	 */
	public static final NpcStringId SLOT_S1_S3;

	/**
	 * ID: 40306<br>
	 * Message: この青二才が、俺を捕まえるだって？
	 */
	public static final NpcStringId YOU_CHILDISH_FOOL_DO_YOU_THINK_YOU_CAN_CATCH_ME;

	/**
	 * ID: 40307<br>
	 * Message: この屈辱は必ず．．．
	 */
	public static final NpcStringId I_MUST_DO_SOMETHING_ABOUT_THIS_SHAMEFUL_INCIDENT;

	/**
	 * ID: 40308<br>
	 * Message: 何っ、挑戦するとでも言うのか！？
	 */
	public static final NpcStringId WHAT_DO_YOU_DARE_TO_CHALLENGE_ME;

	/**
	 * ID: 40309<br>
	 * Message: レッドアイ山賊団の復讐が待っている！
	 */
	public static final NpcStringId THE_RED_EYED_THIEVES_WILL_REVENGE;

	/**
	 * ID: 40310<br>
	 * Message: かかって来い、この青二才！
	 */
	public static final NpcStringId GO_AHEAD_YOU_CHILD;

	/**
	 * ID: 40311<br>
	 * Message: 俺の仲間が必ず復讐を．．．
	 */
	public static final NpcStringId MY_FRIENDS_ARE_SURE_TO_REVENGE;

	/**
	 * ID: 40312<br>
	 * Message: この怖いもの知らずめ、痛い目に遭うぜ！
	 */
	public static final NpcStringId YOU_RUTHLESS_FOOL_I_WILL_SHOW_YOU_WHAT_REAL_FIGHTING_IS_ALL_ABOUT;

	/**
	 * ID: 40313<br>
	 * Message: こ、こんな形で終わるとは．．．無念だ！
	 */
	public static final NpcStringId AHH_HOW_CAN_IT_END_LIKE_THIS_IT_IS_NOT_FAIR;

	/**
	 * ID: 40909<br>
	 * Message: 聖化は俺らのものだ！
	 */
	public static final NpcStringId THE_SACRED_FLAME_IS_OURS;

	/**
	 * ID: 40910<br>
	 * Message: クッ、諦めるしかなさそうだ．．．
	 */
	public static final NpcStringId ARRGHHWE_SHALL_NEVER_SURRENDER;

	/**
	 * ID: 40913<br>
	 * Message: ご命令に従います、ご主人様！
	 */
	public static final NpcStringId AS_YOU_WISH_MASTER;

	/**
	 * ID: 41651<br>
	 * Message: 先に旅立った同胞よ、$s1の！
	 */
	public static final NpcStringId MY_DEAR_FRIEND_OF_S1_WHO_HAS_GONE_ON_AHEAD_OF_ME;

	/**
	 * ID: 41652<br>
	 * Message: 聞け！若者よ！デジャカル ガンジ！消えたパンサーの魂は$s1汝を呼んでいる！
	 */
	public static final NpcStringId LISTEN_TO_TEJAKAR_GANDI_YOUNG_OROKA_THE_SPIRIT_OF_THE_SLAIN_LEOPARD_IS_CALLING_YOU_S1;

	/**
	 * ID: 42046<br>
	 * Message: クウッ！みなで卵を守るのだ！
	 */
	public static final NpcStringId HEY_EVERYBODY_WATCH_THE_EGGS;

	/**
	 * ID: 42047<br>
	 * Message: これで一儲けできると思ったら．．．ククッ！
	 */
	public static final NpcStringId I_THOUGHT_ID_CAUGHT_ONE_SHARE_WHEW;

	/**
	 * ID: 42048<br>
	 * Message: 石が．．．妖精石が．．．砕けて．．．
	 */
	public static final NpcStringId THE_STONE_THE_ELVEN_STONE_BROKE;

	/**
	 * ID: 42049<br>
	 * Message: 卵を奪われたら、我々は死ぬ運命だ！ 
	 */
	public static final NpcStringId IF_THE_EGGS_GET_TAKEN_WERE_DEAD;

	/**
	 * ID: 42111<br>
	 * Message: 妖精の葉をくれ！
	 */
	public static final NpcStringId GIVE_ME_A_FAIRY_LEAF;

	/**
	 * ID: 42112<br>
	 * Message: なぜまた私にまとわりつくんだ？
	 */
	public static final NpcStringId WHY_DO_YOU_BOTHER_ME_AGAIN;

	/**
	 * ID: 42113<br>
	 * Message: ぼうず、お前はもう風の精粋を飲んだ。
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_WIND;

	/**
	 * ID: 42114<br>
	 * Message: 早く行け。守護者の魂から怖いめに遭わされる前に．．．
	 */
	public static final NpcStringId LEAVE_NOW_BEFORE_YOU_INCUR_THE_WRATH_OF_THE_GUARDIAN_GHOST;

	/**
	 * ID: 42115<br>
	 * Message: ぼうず、お前はもう星の精粋を飲んだ。
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_A_STAR;

	/**
	 * ID: 42116<br>
	 * Message: ぼうず、お前はもう黄昏の精粋を飲んだ。
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_DUSK;

	/**
	 * ID: 42117<br>
	 * Message: ぼうず、お前はもうアビスの精粋を飲んだ。
	 */
	public static final NpcStringId HEY_YOUVE_ALREADY_DRUNK_THE_ESSENCE_OF_THE_ABYSS;

	/**
	 * ID: 42118<br>
	 * Message: 我々は妖精木を守らねばならない。
	 */
	public static final NpcStringId WE_MUST_PROTECT_THE_FAIRY_TREE;

	/**
	 * ID: 42119<br>
	 * Message: 不穏な者たちよ、聖なる木から離れろ。
	 */
	public static final NpcStringId GET_OUT_OF_THE_SACRED_TREE_YOU_SCOUNDRELS;

	/**
	 * ID: 42120<br>
	 * Message: 世界の精粋を盗もうとする盗賊たちを成敗してやる．．．
	 */
	public static final NpcStringId DEATH_TO_THE_THIEVES_OF_THE_PURE_WATER_OF_THE_WORLD;

	/**
	 * ID: 42231<br>
	 * Message: おい、兄弟よ！私の助けが必要なようだな？
	 */
	public static final NpcStringId HEY_IT_SEEMS_LIKE_YOU_NEED_MY_HELP_DOESNT_IT;

	/**
	 * ID: 42232<br>
	 * Message: どこへでも付いて行くぜ！それにしてもクソッ、この手錠うっとうしいな！
	 */
	public static final NpcStringId ALMOST_GOT_IT_OUCH_STOP_DAMN_THESE_BLOODY_MANACLES;

	/**
	 * ID: 42233<br>
	 * Message: 痛い！痛過ぎるぞ！俺はひどく痛いんだ！
	 */
	public static final NpcStringId OH_THAT_SMARTS;

	/**
	 * ID: 42234<br>
	 * Message: おい、マスター！油売ってんじゃねぇよ！あいつが俺を殴るんだぜ！
	 */
	public static final NpcStringId HEY_MASTER_PAY_ATTENTION_IM_DYING_OVER_HERE;

	/**
	 * ID: 42235<br>
	 * Message: 俺が一体何をしたというんだ！
	 */
	public static final NpcStringId WHAT_HAVE_I_DONE_TO_DESERVE_THIS;

	/**
	 * ID: 42236<br>
	 * Message: はいはい、大したもんだ、すごいねぇ！で、どうするんだよ？
	 */
	public static final NpcStringId OH_THIS_IS_JUST_GREAT_WHAT_ARE_YOU_GOING_TO_DO_NOW;

	/**
	 * ID: 42237<br>
	 * Message: バカ！お前はマヌケか。俺一人の面倒もちゃんと見れないのかよ？
	 */
	public static final NpcStringId YOU_INCONSIDERATE_MORON_CANT_YOU_EVEN_TAKE_CARE_OF_LITTLE_OLD_ME;

	/**
	 * ID: 42238<br>
	 * Message: ケッ！シン イーター死亡！贖罪にはまだまだ程遠いな！
	 */
	public static final NpcStringId OH_NO_THE_MAN_WHO_EATS_ONES_SINS_HAS_DIED_PENITENCE_IS_FURTHER_AWAY;

	/**
	 * ID: 42239<br>
	 * Message: 特殊スキル？こんな所でむやみに使ったら、血の海になるぜ！
	 */
	public static final NpcStringId USING_A_SPECIAL_SKILL_HERE_COULD_TRIGGER_A_BLOODBATH;

	/**
	 * ID: 42240<br>
	 * Message: おい、兄弟！俺に一体何を期待してるんだ？
	 */
	public static final NpcStringId HEY_WHAT_DO_YOU_EXPECT_OF_ME;

	/**
	 * ID: 42241<br>
	 * Message: うりゃああ！おりゃ！おりゃ！うう、出て行かないぞ！
	 */
	public static final NpcStringId UGGGGGH_PUSH_ITS_NOT_COMING_OUT;

	/**
	 * ID: 42242<br>
	 * Message: あ、外れちまったよ！
	 */
	public static final NpcStringId AH_I_MISSED_THE_MARK;

	/**
	 * ID: 42243<br>
	 * Message: うああ！．．．退屈だ。どっか行くぞ！
	 */
	public static final NpcStringId YAWWWWN_ITS_SO_BORING_HERE_WE_SHOULD_GO_AND_FIND_SOME_ACTION;

	/**
	 * ID: 42244<br>
	 * Message: おい、兄弟！こんなことしてたら、いつまでたっても贖罪が終わらないぜ。
	 */
	public static final NpcStringId HEY_IF_YOU_CONTINUE_TO_WASTE_TIME_YOU_WILL_NEVER_FINISH_YOUR_PENANCE;

	/**
	 * ID: 42245<br>
	 * Message: お前は俺を嫌いなんだろ？同じように俺もお前のことが嫌いなんだよ！
	 */
	public static final NpcStringId I_KNOW_YOU_DONT_LIKE_ME_THE_FEELING_IS_MUTUAL;

	/**
	 * ID: 42246<br>
	 * Message: きついエクトプラズム リキュールで、一杯やりたいところだ。
	 */
	public static final NpcStringId I_NEED_A_DRINK;

	/**
	 * ID: 42247<br>
	 * Message: 月日よ、月日よ．．．こんなことでは、７つの封印をすべて解く前に、家に帰れんではないか。
	 */
	public static final NpcStringId OH_THIS_IS_DRAGGING_ON_TOO_LONG_AT_THIS_RATE_I_WONT_MAKE_IT_HOME_BEFORE_THE_SEVEN_SEALS_ARE_BROKEN;

	/**
	 * ID: 45650<br>
	 * Message: $s1はちりぢりになった魂からアイテム$s2を受け取りました。
	 */
	public static final NpcStringId S1_RECEIVED_A_S2_ITEM_AS_A_REWARD_FROM_THE_SEPARATED_SOUL;

	/**
	 * ID: 45651<br>
	 * Message: 封印されたバーペス ヘルム
	 */
	public static final NpcStringId SEALED_VORPAL_HELMET;

	/**
	 * ID: 45652<br>
	 * Message: 封印されたバーペス レザー ヘルム
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_HELMET;

	/**
	 * ID: 45653<br>
	 * Message: 封印されたバーペス サークレット
	 */
	public static final NpcStringId SEALED_VORPAL_CIRCLET;

	/**
	 * ID: 45654<br>
	 * Message: 封印されたバーペス ブレスト プレート
	 */
	public static final NpcStringId SEALED_VORPAL_BREASTPLATE;

	/**
	 * ID: 45655<br>
	 * Message: 封印されたバーペス レザー プレート
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_BREASTPLATE;

	/**
	 * ID: 45656<br>
	 * Message: 封印されたバーペス チューニック
	 */
	public static final NpcStringId SEALED_VORPAL_TUNIC;

	/**
	 * ID: 45657<br>
	 * Message: 封印されたバーペス ゲートル
	 */
	public static final NpcStringId SEALED_VORPAL_GAITERS;

	/**
	 * ID: 45658<br>
	 * Message: 封印されたバーペス レザー レギンス
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_LEGGING;

	/**
	 * ID: 45659<br>
	 * Message: 封印されたバーペス ホース
	 */
	public static final NpcStringId SEALED_VORPAL_STOCKING;

	/**
	 * ID: 45660<br>
	 * Message: 封印されたバーペス ガントレット
	 */
	public static final NpcStringId SEALED_VORPAL_GAUNTLET;

	/**
	 * ID: 45661<br>
	 * Message: 封印されたバーペス レザー グローブ
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_GLOVES;

	/**
	 * ID: 45662<br>
	 * Message: 封印されたバーペス グローブ
	 */
	public static final NpcStringId SEALED_VORPAL_GLOVES;

	/**
	 * ID: 45663<br>
	 * Message: 封印されたバーペス ブーツ
	 */
	public static final NpcStringId SEALED_VORPAL_BOOTS;

	/**
	 * ID: 45664<br>
	 * Message: 封印されたバーペス レザー ブーツ
	 */
	public static final NpcStringId SEALED_VORPAL_LEATHER_BOOTS;

	/**
	 * ID: 45665<br>
	 * Message: 封印されたバーペス シューズ
	 */
	public static final NpcStringId SEALED_VORPAL_SHOES;

	/**
	 * ID: 45666<br>
	 * Message: 封印されたバーペス シールド
	 */
	public static final NpcStringId SEALED_VORPAL_SHIELD;

	/**
	 * ID: 45667<br>
	 * Message: 封印されたバーペス シギル
	 */
	public static final NpcStringId SEALED_VORPAL_SIGIL;

	/**
	 * ID: 45668<br>
	 * Message: 封印されたバーペス リング
	 */
	public static final NpcStringId SEALED_VORPAL_RING;

	/**
	 * ID: 45669<br>
	 * Message: 封印されたバーペス イアリング
	 */
	public static final NpcStringId SEALED_VORPAL_EARRING;

	/**
	 * ID: 45670<br>
	 * Message: 封印されたバーペス ネックレス
	 */
	public static final NpcStringId SEALED_VORPAL_NECKLACE;

	/**
	 * ID: 45671<br>
	 * Message: ペリエル ソード
	 */
	public static final NpcStringId PERIEL_SWORD;

	/**
	 * ID: 45672<br>
	 * Message: スカル エッジ
	 */
	public static final NpcStringId SKULL_EDGE;

	/**
	 * ID: 45673<br>
	 * Message: ヴィグィック アックス
	 */
	public static final NpcStringId VIGWIK_AXE;

	/**
	 * ID: 45674<br>
	 * Message: イーヴル デイティ マウル
	 */
	public static final NpcStringId DEVILISH_MAUL;

	/**
	 * ID: 45675<br>
	 * Message: フェザーアイ ブレード
	 */
	public static final NpcStringId FEATHER_EYE_BLADE;

	/**
	 * ID: 45676<br>
	 * Message: オクト クロー
	 */
	public static final NpcStringId OCTO_CLAW;

	/**
	 * ID: 45677<br>
	 * Message: ダブルトパ スピアー
	 */
	public static final NpcStringId DOUBLETOP_SPEAR;

	/**
	 * ID: 45678<br>
	 * Message: キューティクル
	 */
	public static final NpcStringId RISING_STAR;

	/**
	 * ID: 45679<br>
	 * Message: ブラック ヴィサージュ
	 */
	public static final NpcStringId BLACK_VISAGE;

	/**
	 * ID: 45680<br>
	 * Message: ベニプラント ソード
	 */
	public static final NpcStringId VENIPLANT_SWORD;

	/**
	 * ID: 45681<br>
	 * Message: スカル カルニウム ボウ
	 */
	public static final NpcStringId SKULL_CARNIUM_BOW;

	/**
	 * ID: 45682<br>
	 * Message: ジェムテイル レイピア
	 */
	public static final NpcStringId GEMTAIL_RAPIER;

	/**
	 * ID: 45683<br>
	 * Message: フィン インヴィンシブル ブレード
	 */
	public static final NpcStringId FINALE_BLADE;

	/**
	 * ID: 45684<br>
	 * Message: ルード カッター クロスボウ
	 */
	public static final NpcStringId DOMINION_CROSSBOW;

	/**
	 * ID: 45685<br>
	 * Message: 祝福された武器強化スクロール：Sグレード
	 */
	public static final NpcStringId BLESSED_WEAPON_ENCHANT_SCROLL_S_GRADE;

	/**
	 * ID: 45686<br>
	 * Message: 祝福された防具強化スクロール：Sグレード
	 */
	public static final NpcStringId BLESSED_ARMOR_ENCHANT_SCROLL_S_GRADE;

	/**
	 * ID: 45687<br>
	 * Message: 火の水晶
	 */
	public static final NpcStringId FIRE_CRYSTAL;

	/**
	 * ID: 45688<br>
	 * Message: 水の水晶
	 */
	public static final NpcStringId WATER_CRYSTAL;

	/**
	 * ID: 45689<br>
	 * Message: 地の水晶
	 */
	public static final NpcStringId EARTH_CRYSTAL;

	/**
	 * ID: 45690<br>
	 * Message: 風の水晶
	 */
	public static final NpcStringId WIND_CRYSTAL;

	/**
	 * ID: 45691<br>
	 * Message: 聖の水晶
	 */
	public static final NpcStringId HOLY_CRYSTAL;

	/**
	 * ID: 45692<br>
	 * Message: 闇の水晶
	 */
	public static final NpcStringId DARK_CRYSTAL;

	/**
	 * ID: 45693<br>
	 * Message: 武器強化スクロール：Sグレード
	 */
	public static final NpcStringId WEAPON_ENCHANT_SCROLL_S_GRADE;

	/**
	 * ID: 46350<br>
	 * Message: こ、攻撃．．．$s1、ロ、ログ．．．$s2
	 */
	public static final NpcStringId ATT_ATTACK_S1_RO_ROGUE_S2;

	/**
	 * ID: 50110<br>
	 * Message: ##########ビンゴ！##########
	 */
	public static final NpcStringId BINGO;

	/**
	 * ID: 50338<br>
	 * Message: 血と名誉！
	 */
	public static final NpcStringId BLOOD_AND_HONOR;

	/**
	 * ID: 50339<br>
	 * Message: 帝国の遺産を汚す者に神の裁きを！
	 */
	public static final NpcStringId CURSE_OF_THE_GODS_ON_THE_ONE_THAT_DEFILES_THE_PROPERTY_OF_THE_EMPIRE;

	/**
	 * ID: 50340<br>
	 * Message: 戦争と死！
	 */
	public static final NpcStringId WAR_AND_DEATH;

	/**
	 * ID: 50341<br>
	 * Message: 野望と権勢！
	 */
	public static final NpcStringId AMBITION_AND_POWER;

	/**
	 * ID: 50503<br>
	 * Message: $s1が$s2未満の祭典で$s3点を記録しました。
	 */
	public static final NpcStringId S1_HAS_WON_THE_MAIN_EVENT_FOR_PLAYERS_UNDER_LEVEL_S2_AND_EARNED_S3_POINTS;

	/**
	 * ID: 50504<br>
	 * Message: $s1が無制限クラスの祭典で$s2点を記録しました。
	 */
	public static final NpcStringId S1_HAS_EARNED_S2_POINTS_IN_THE_MAIN_EVENT_FOR_UNLIMITED_LEVELS;

	/**
	 * ID: 50701<br>
	 * Message: 混沌の中に
	 */
	public static final NpcStringId INTO_THE_CHAOS;

	/**
	 * ID: 50851<br>
	 * Message: クエストを無事クリアしたので、$s1の血盟名声値を獲得しました。
	 */
	public static final NpcStringId YOU_HAVE_SUCCESSFULLY_COMPLETED_A_CLAN_QUEST_S1_POINTS_HAVE_BEEN_ADDED_TO_YOUR_CLANS_REPUTATION_SCORE;

	/**
	 * ID: 60000<br>
	 * Message: 見てみろ、火鉢に火がつくぞ。
	 */
	public static final NpcStringId THE_FURNACE_WILL_GO_OUT_WATCH_AND_SEE;

	/**
	 * ID: 60001<br>
	 * Message: 残り1分だ！
	 */
	public static final NpcStringId THERES_ABOUT_1_MINUTE_LEFT;

	/**
	 * ID: 60002<br>
	 * Message: 残り10秒しかない！
	 */
	public static final NpcStringId THERES_JUST_10_SECONDS_LEFT;

	/**
	 * ID: 60003<br>
	 * Message: ほれ、火鉢に火をつけてみろ。
	 */
	public static final NpcStringId NOW_LIGHT_THE_FURNACES_FIRE;

	/**
	 * ID: 60004<br>
	 * Message: タイムオーバーで失敗だ。もう一回挑むのは難しそうだ。
	 */
	public static final NpcStringId TIME_IS_UP_AND_YOU_HAVE_FAILED_ANY_MORE_WILL_BE_DIFFICULT;

	/**
	 * ID: 60005<br>
	 * Message: これはすごい。成功だ！
	 */
	public static final NpcStringId OH_YOUVE_SUCCEEDED;

	/**
	 * ID: 60006<br>
	 * Message: ああ、失敗か！でも、まだまだやり直しがきくぞ！
	 */
	public static final NpcStringId AH_IS_THIS_FAILURE_BUT_IT_LOOKS_LIKE_I_CAN_KEEP_GOING;

	/**
	 * ID: 60007<br>
	 * Message: 失敗だ。もう一回挑むのは難しそうだ。
	 */
	public static final NpcStringId AH_IVE_FAILED_GOING_FURTHER_WILL_BE_DIFFICULT;

	/**
	 * ID: 60008<br>
	 * Message: バランスの火鉢
	 */
	public static final NpcStringId FURNACE_OF_BALANCE;

	/**
	 * ID: 60009<br>
	 * Message: 守護の火鉢
	 */
	public static final NpcStringId FURNACE_OF_PROTECTION;

	/**
	 * ID: 60010<br>
	 * Message: 闘志の火鉢
	 */
	public static final NpcStringId FURNACE_OF_WILL;

	/**
	 * ID: 60011<br>
	 * Message: 魔力の火鉢
	 */
	public static final NpcStringId FURNACE_OF_MAGIC;

	/**
	 * ID: 60012<br>
	 * Message: 聖なる気が漂いだしました。
	 */
	public static final NpcStringId DIVINE_ENERGY_IS_BEGINNING_TO_ENCIRCLE;

	/**
	 * ID: 60013<br>
	 * Message: ソリナの名誉のために！
	 */
	public static final NpcStringId FOR_THE_GLORY_OF_SOLINA;

	/**
	 * ID: 60014<br>
	 * Message: この地に足を踏み入れたすべての者を断罪せよ！
	 */
	public static final NpcStringId PUNISH_ALL_THOSE_WHO_TREAD_FOOTSTEPS_IN_THIS_PLACE;

	/**
	 * ID: 60015<br>
	 * Message: 我々は真理の剣であり、ソリナの剣である。
	 */
	public static final NpcStringId WE_ARE_THE_SWORD_OF_TRUTH_THE_SWORD_OF_SOLINA;

	/**
	 * ID: 60016<br>
	 * Message: ソリナの名誉のために剣を取ろう。
	 */
	public static final NpcStringId WE_RAISE_OUR_BLADES_FOR_THE_GLORY_OF_SOLINA;

	/**
	 * ID: 60018<br>
	 * Message: そんなに速く行かないで。
	 */
	public static final NpcStringId HEY_DONT_GO_SO_FAST;

	/**
	 * ID: 60019<br>
	 * Message: ついていけません。
	 */
	public static final NpcStringId ITS_HARD_TO_FOLLOW;

	/**
	 * ID: 60020<br>
	 * Message: ハアハア、速すぎてもうついていけません。
	 */
	public static final NpcStringId HUFF_HUFF_YOURE_TOO_FAST_I_CANT_FOLLOW_ANYMORE;

	/**
	 * ID: 60021<br>
	 * Message: ああ、ここは見覚えがあるぞ。
	 */
	public static final NpcStringId AH_I_THINK_I_REMEMBER_THIS_PLACE;

	/**
	 * ID: 60022<br>
	 * Message: ああ、空気がおいしい！
	 */
	public static final NpcStringId AH_FRESH_AIR;

	/**
	 * ID: 60023<br>
	 * Message: ここで何してるんですか。
	 */
	public static final NpcStringId WHAT_WERE_YOU_DOING_HERE;

	/**
	 * ID: 60024<br>
	 * Message: まあ、おそろしい方。もしかして私と同じく宝物を？
	 */
	public static final NpcStringId I_GUESS_YOURE_THE_SILENT_TYPE_THEN_ARE_YOU_LOOKING_FOR_TREASURE_LIKE_ME;

	/**
	 * ID: 60403<br>
	 * Message: 私を呼ぶのは誰だ？
	 */
	public static final NpcStringId WHO_IS_CALLING_ME;

	/**
	 * ID: 60404<br>
	 * Message: 闇と光は表裏一体なんだ。
	 */
	public static final NpcStringId CAN_LIGHT_EXIST_WITHOUT_DARKNESS;

	/**
	 * ID: 60903<br>
	 * Message: ウダン様の眼は避けられない！
	 */
	public static final NpcStringId YOU_CANT_AVOID_THE_EYES_OF_UDAN;

	/**
	 * ID: 60904<br>
	 * Message: ウダン様はすでにお前の顔をご覧になった！
	 */
	public static final NpcStringId UDAN_HAS_ALREADY_SEEN_YOUR_FACE;

	/**
	 * ID: 61050<br>
	 * Message: 水の魔力！これ即ち嵐と津波の力！逆らえば死あるのみ！
	 */
	public static final NpcStringId THE_MAGICAL_POWER_OF_WATER_COMES_FROM_THE_POWER_OF_STORM_AND_HAIL_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU;

	/**
	 * ID: 61051<br>
	 * Message: 束縛の力が弱まっている。貴様らの儀式は失敗だ！
	 */
	public static final NpcStringId THE_POWER_OF_CONSTRAINT_IS_GETTING_WEAKER_YOUR_RITUAL_HAS_FAILED;

	/**
	 * ID: 61503<br>
	 * Message: アセファ様の眼は避けられない！
	 */
	public static final NpcStringId YOU_CANT_AVOID_THE_EYES_OF_ASEFA;

	/**
	 * ID: 61504<br>
	 * Message: アセファ様はすでにお前の顔をご覧になった！
	 */
	public static final NpcStringId ASEFA_HAS_ALREADY_SEEN_YOUR_FACE;

	/**
	 * ID: 61650<br>
	 * Message: 火の魔力は、すなわち火炎と溶岩の力！逆らえば死あるのみ！
	 */
	public static final NpcStringId THE_MAGICAL_POWER_OF_FIRE_IS_ALSO_THE_POWER_OF_FLAMES_AND_LAVA_IF_YOU_DARE_TO_CONFRONT_IT_ONLY_DEATH_WILL_AWAIT_YOU;

	/**
	 * ID: 62503<br>
	 * Message: 美味しそうな匂いがする．．．
	 */
	public static final NpcStringId I_SMELL_SOMETHING_DELICIOUS;

	/**
	 * ID: 62504<br>
	 * Message: うおお！
	 */
	public static final NpcStringId OOOH;

	/**
	 * ID: 66001<br>
	 * Message: フローラン村を救おう
	 */
	public static final NpcStringId AIDING_THE_FLORAN_VILLAGE;

	/**
	 * ID: 66300<br>
	 * Message: カードなし
	 */
	public static final NpcStringId NO_SUCH_CARD;

	/**
	 * ID: 68801<br>
	 * Message: エルクロッキー襲撃隊を退けろ！
	 */
	public static final NpcStringId DEFEAT_THE_ELROKIAN_RAIDERS;

	/**
	 * ID: 70851<br>
	 * Message: 城主様、ご準備はよろしいでしょうか。
	 */
	public static final NpcStringId HAVE_YOU_COMPLETED_YOUR_PREPARATIONS_TO_BECOME_A_LORD;

	/**
	 * ID: 70852<br>
	 * Message: $s1、出発！
	 */
	public static final NpcStringId S1_NOW_DEPART;

	/**
	 * ID: 70853<br>
	 * Message: サイアスを訪ねてください。
	 */
	public static final NpcStringId GO_FIND_SAIUS;

	/**
	 * ID: 70854<br>
	 * Message: 皆の衆、よく聞け！まもなく領主様におなりになる城主様がデュラハンを倒されたぞ！皆、安心して暮らすがよい！
	 */
	public static final NpcStringId LISTEN_YOU_VILLAGERS_OUR_LIEGE_WHO_WILL_SOON_BECOME_A_LORD_HAS_DEFEATED_THE_HEADLESS_KNIGHT_YOU_CAN_NOW_REST_EASY;

	/**
	 * ID: 70855<br>
	 * Message: $s1！てめえ、よくも俺の部下に手を出してくれたな！
	 */
	public static final NpcStringId S1_DO_YOU_DARE_DEFY_MY_SUBORDINATES;

	/**
	 * ID: 70856<br>
	 * Message: 補給を妨害する任務はもう終わりなのか。
	 */
	public static final NpcStringId DOES_MY_MISSION_TO_BLOCK_THE_SUPPLIES_END_HERE;

	/**
	 * ID: 70859<br>
	 * Message: $s1様はグルーディオ城の領主におなりになりました。グルーディオ領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_GLUDIO_LONG_MAY_HE_REIGN;

	/**
	 * ID: 70957<br>
	 * Message: 覚えてろ！次は絶対許さねえからな！
	 */
	public static final NpcStringId YOULL_SEE_I_WONT_FORGIVE_YOU_NEXT_TIME;

	/**
	 * ID: 70959<br>
	 * Message: $s1様はグルーディオ城の領主におなりになりました。グルーディオ領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_LORD_OF_THE_TOWN_OF_DION_LONG_MAY_HE_REIGN;

	/**
	 * ID: 71052<br>
	 * Message: 敵来襲！やつらを1人残らずやっつけろ！
	 */
	public static final NpcStringId ITS_THE_ENEMY_NO_MERCY;

	/**
	 * ID: 71053<br>
	 * Message: 何をするんだ！まだ俺たちの方が優勢じゃないか！
	 */
	public static final NpcStringId WHAT_ARE_YOU_DOING_WE_ARE_STILL_SUPERIOR;

	/**
	 * ID: 71054<br>
	 * Message: くそっ！この仇は必ず．．．
	 */
	public static final NpcStringId HOW_INFURIATING_THIS_ENEMY;

	/**
	 * ID: 71059<br>
	 * Message: $s1様はギラン城の村の領主におなりになりました。ギラン領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_GIRAN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_GIRAN;

	/**
	 * ID: 71151<br>
	 * Message: 城主様、どちらにいらっしゃいますか。
	 */
	public static final NpcStringId MY_LIEGE_WHERE_ARE_YOU;

	/**
	 * ID: 71159<br>
	 * Message: $s1様はインナドリル城の村の領主におなりになりました。オーレン領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_INNADRIL_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_INNADRIL;

	/**
	 * ID: 71252<br>
	 * Message: ネビュライト オーブをすべて見つけました。
	 */
	public static final NpcStringId YOU_HAVE_FOUND_ALL_THE_NEBULITE_ORBS;

	/**
	 * ID: 71259<br>
	 * Message: $s1様はオーレン城の村の領主におなりになりました。オーレン領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_OREN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_OREN;

	/**
	 * ID: 71351<br>
	 * Message: $s1様はアデン城の村の領主におなりになりました。アデン領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_ADEN_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_ADEN;

	/**
	 * ID: 71459<br>
	 * Message: $s1様はシュチュッツガルト城の村の領主におなりになりました。シュチュッツガルト領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_SCHUTTGART_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_SCHUTTGART;

	/**
	 * ID: 71551<br>
	 * Message: $s1、お前のことは忘れないだろう。
	 */
	public static final NpcStringId S1_I_WILL_REMEMBER_YOU;

	/**
	 * ID: 71653<br>
	 * Message: フレデリックが城主様にお目にかかりたいと申しております。
	 */
	public static final NpcStringId FREDERICK_IS_LOOKING_FOR_YOU_MY_LIEGE;

	/**
	 * ID: 71654<br>
	 * Message: ふっふっふっ。我々と取引せずに堪えられるとでも思ったか！
	 */
	public static final NpcStringId HO_HO_DID_YOU_THINK_YOU_COULD_REALLY_STOP_TRADING_WITH_US;

	/**
	 * ID: 71655<br>
	 * Message: 神殿に攻め入りました。
	 */
	public static final NpcStringId YOU_HAVE_CHARGED_INTO_THE_TEMPLE;

	/**
	 * ID: 71656<br>
	 * Message: 邪教の神殿の邪教徒を倒している最中です。
	 */
	public static final NpcStringId YOU_ARE_IN_THE_MIDST_OF_DEALING_WITH_THE_HERETIC_OF_HERETIC_TEMPLE;

	/**
	 * ID: 71657<br>
	 * Message: 邪教の神殿が混乱に陥りつつあります。
	 */
	public static final NpcStringId THE_HERETIC_TEMPLE_IS_DESCENDING_INTO_CHAOS;

	/**
	 * ID: 71659<br>
	 * Message: $s1様はルウン城の村の領主におなりになりました。ルウン領地に栄光あれ！
	 */
	public static final NpcStringId S1_HAS_BECOME_THE_LORD_OF_THE_TOWN_OF_RUNE_MAY_THERE_BE_GLORY_IN_THE_TERRITORY_OF_RUNE;

	/**
	 * ID: 71751<br>
	 * Message: $s1! 領地のために武器を取れ！
	 */
	public static final NpcStringId S1_RAISE_YOUR_WEAPONS_FOR_THE_SAKE_OF_THE_TERRITORY;

	/**
	 * ID: 71752<br>
	 * Message: $s1! 戦争は終わった。汝、未来のために剣を収めよ！
	 */
	public static final NpcStringId S1_THE_WAR_IS_OVER_LOWER_YOUR_SWORD_FOR_THE_SAKE_OF_THE_FUTURE;

	/**
	 * ID: 71755<br>
	 * Message: 領地のバッジ90個、個人名声値450点、$s1アデナ。
	 */
	public static final NpcStringId N91_TERRITORY_BADGES_450_SCORES_IN_INDIVIDUAL_FAME_AND_S2_ADENAS;

	/**
	 * ID: 72903<br>
	 * Message: 傭兵クエストの番号は$s1、memostate1は$s2、memostate2は$s3です。
	 */
	public static final NpcStringId THE_MERCENARY_QUEST_NUMBER_IS_S1_MEMOSTATE1_IS_S2_AND_MEMOSTATE2_IS_S3;

	/**
	 * ID: 72904<br>
	 * Message: user_connected  イベント発生成功、Siege Id $s1、728番memo2の値$s2、729/memo2$s3、255/memo1 $s4です。
	 */
	public static final NpcStringId USER_CONNECTED_EVENT_OCCURRENCE_SUCCESS_SIEGE_ID_IS_S1_NUMBER_728_MEMO2_IS_S2_729_MEMO2_IS_S3_AND_255_MEMO1_IS_S4;

	/**
	 * ID: 72905<br>
	 * Message: 領地投石機、dyingイベント投石機の領地ID$s1、パーティ状態 $s2
	 */
	public static final NpcStringId TERRITORY_CATAPULT_DYING_EVENT_CATAPULTS_TERRITORY_ID_S1_PARTY_STATUS_S2;

	/**
	 * ID: 72951<br>
	 * Message: グルーディオの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_GLUDIO;

	/**
	 * ID: 72952<br>
	 * Message: ディオンの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_DION;

	/**
	 * ID: 72953<br>
	 * Message: ギランの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_GIRAN;

	/**
	 * ID: 72954<br>
	 * Message: オーレンの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_OREN;

	/**
	 * ID: 72955<br>
	 * Message: アデンの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_ADEN;

	/**
	 * ID: 72956<br>
	 * Message: インナドリルの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_INNADRIL;

	/**
	 * ID: 72957<br>
	 * Message: ゴダードの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_GODDARD;

	/**
	 * ID: 72958<br>
	 * Message: ルウンの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_RUNE;

	/**
	 * ID: 72959<br>
	 * Message: シュチュッツガルトの領地投石機を守れ！
	 */
	public static final NpcStringId PROTECT_THE_CATAPULT_OF_SCHUTTGART;

	/**
	 * ID: 72961<br>
	 * Message: グルーディオの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_GLUDIO_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72962<br>
	 * Message: ディオンの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_DION_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72963<br>
	 * Message: ギランの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_GIRAN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72964<br>
	 * Message: オーレンの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_OREN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72965<br>
	 * Message: アデンの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_ADEN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72966<br>
	 * Message: インナドリルの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_INNADRIL_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72967<br>
	 * Message: ゴダードの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_GODDARD_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72968<br>
	 * Message: ルウンの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_RUNE_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72969<br>
	 * Message: シュチュッツガルトの領地投石機が破壊された！
	 */
	public static final NpcStringId THE_CATAPULT_OF_SCHUTTGART_HAS_BEEN_DESTROYED;

	/**
	 * ID: 72981<br>
	 * Message: グルーディオ
	 */
	public static final NpcStringId GLUDIO;

	/**
	 * ID: 72982<br>
	 * Message: ディオン
	 */
	public static final NpcStringId DION;

	/**
	 * ID: 72983<br>
	 * Message: ギラン
	 */
	public static final NpcStringId GIRAN;

	/**
	 * ID: 72984<br>
	 * Message: オーレン
	 */
	public static final NpcStringId OREN;

	/**
	 * ID: 72985<br>
	 * Message: アデン
	 */
	public static final NpcStringId ADEN;

	/**
	 * ID: 72986<br>
	 * Message: インナドリル
	 */
	public static final NpcStringId INNADRIL;

	/**
	 * ID: 72987<br>
	 * Message: ゴダード
	 */
	public static final NpcStringId GODDARD;

	/**
	 * ID: 72988<br>
	 * Message: ルウン
	 */
	public static final NpcStringId RUNE;

	/**
	 * ID: 72989<br>
	 * Message: シュチュッツガルト
	 */
	public static final NpcStringId SCHUTTGART;

	/**
	 * ID: 73051<br>
	 * Message: グルーディオの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GLUDIO;

	/**
	 * ID: 73052<br>
	 * Message: ディオンの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_DION;

	/**
	 * ID: 73053<br>
	 * Message: ギランの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GIRAN;

	/**
	 * ID: 73054<br>
	 * Message: オーレンの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_OREN;

	/**
	 * ID: 73055<br>
	 * Message: アデンの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_ADEN;

	/**
	 * ID: 73056<br>
	 * Message: インナドリルの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_INNADRIL;

	/**
	 * ID: 73057<br>
	 * Message: ゴダードの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_GODDARD;

	/**
	 * ID: 73058<br>
	 * Message: ルウンの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_RUNE;

	/**
	 * ID: 73059<br>
	 * Message: シュチュッツガルトの補給品保管箱を守れ！
	 */
	public static final NpcStringId PROTECT_THE_SUPPLIES_SAFE_OF_SCHUTTGART;

	/**
	 * ID: 73061<br>
	 * Message: グルーディオの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_GLUDIO_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73062<br>
	 * Message: ディオンの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_DION_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73063<br>
	 * Message: ギランの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_GIRAN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73064<br>
	 * Message: オーレンの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_OREN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73065<br>
	 * Message: アデンの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_ADEN_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73066<br>
	 * Message: インナドリルの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_INNADRIL_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73067<br>
	 * Message: ゴダードの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_GODDARD_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73068<br>
	 * Message: ルウンの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_RUNE_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73069<br>
	 * Message: シュチュッツガルトの補給品保管箱が破壊された！
	 */
	public static final NpcStringId THE_SUPPLIES_SAFE_OF_SCHUTTGART_HAS_BEEN_DESTROYED;

	/**
	 * ID: 73151<br>
	 * Message: グルーディオ軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO;

	/**
	 * ID: 73152<br>
	 * Message: ディオン軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_DION;

	/**
	 * ID: 73153<br>
	 * Message: ギラン軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN;

	/**
	 * ID: 73154<br>
	 * Message: オーレン軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_OREN;

	/**
	 * ID: 73155<br>
	 * Message: アデン軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN;

	/**
	 * ID: 73156<br>
	 * Message: インナドリル軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL;

	/**
	 * ID: 73157<br>
	 * Message: ゴダード軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD;

	/**
	 * ID: 73158<br>
	 * Message: ルウン軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE;

	/**
	 * ID: 73159<br>
	 * Message: シュチュッツガルト軍事連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART;

	/**
	 * ID: 73161<br>
	 * Message: グルーディオ軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;

	/**
	 * ID: 73162<br>
	 * Message: ディオン軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_DION_IS_DEAD;

	/**
	 * ID: 73163<br>
	 * Message: ギラン軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;

	/**
	 * ID: 73164<br>
	 * Message: オーレン軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;

	/**
	 * ID: 73165<br>
	 * Message: アデン軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;

	/**
	 * ID: 73166<br>
	 * Message: インナドリル軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;

	/**
	 * ID: 73167<br>
	 * Message: ゴダード軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;

	/**
	 * ID: 73168<br>
	 * Message: ルウン軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;

	/**
	 * ID: 73169<br>
	 * Message: シュチュッツガルト軍事連合長が死んだ！
	 */
	public static final NpcStringId THE_MILITARY_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;

	/**
	 * ID: 73251<br>
	 * Message: グルーディオ宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO;

	/**
	 * ID: 73252<br>
	 * Message: ディオン宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION;

	/**
	 * ID: 73253<br>
	 * Message: ギラン宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN;

	/**
	 * ID: 73254<br>
	 * Message: オーレン宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN;

	/**
	 * ID: 73255<br>
	 * Message: アデン宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN;

	/**
	 * ID: 73256<br>
	 * Message: インナドリル宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL;

	/**
	 * ID: 73257<br>
	 * Message: ゴダード宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD;

	/**
	 * ID: 73258<br>
	 * Message: ルウン宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE;

	/**
	 * ID: 73259<br>
	 * Message: シュチュッツガルト宗教連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART;

	/**
	 * ID: 73261<br>
	 * Message: グルーディオ宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;

	/**
	 * ID: 73262<br>
	 * Message: ディオン宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_DION_IS_DEAD;

	/**
	 * ID: 73263<br>
	 * Message: ギラン宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;

	/**
	 * ID: 73264<br>
	 * Message: オーレン宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;

	/**
	 * ID: 73265<br>
	 * Message: アデン宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;

	/**
	 * ID: 73266<br>
	 * Message: インナドリル宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;

	/**
	 * ID: 73267<br>
	 * Message: ゴダード宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;

	/**
	 * ID: 73268<br>
	 * Message: ルウン宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;

	/**
	 * ID: 73269<br>
	 * Message: シュチュッツガルト宗教連合長が死んだ！
	 */
	public static final NpcStringId THE_RELIGIOUS_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;

	/**
	 * ID: 73351<br>
	 * Message: グルーディオ経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO;

	/**
	 * ID: 73352<br>
	 * Message: ディオン経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION;

	/**
	 * ID: 73353<br>
	 * Message: ギラン経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN;

	/**
	 * ID: 73354<br>
	 * Message: オーレン経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN;

	/**
	 * ID: 73355<br>
	 * Message: アデン経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN;

	/**
	 * ID: 73356<br>
	 * Message: インナドリル経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL;

	/**
	 * ID: 73357<br>
	 * Message: ゴダード経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD;

	/**
	 * ID: 73358<br>
	 * Message: ルウン経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE;

	/**
	 * ID: 73359<br>
	 * Message: シュチュッツガルト経済連合長を守れ！
	 */
	public static final NpcStringId PROTECT_THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART;

	/**
	 * ID: 73361<br>
	 * Message: グルーディオ経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GLUDIO_IS_DEAD;

	/**
	 * ID: 73362<br>
	 * Message: ディオン経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_DION_IS_DEAD;

	/**
	 * ID: 73363<br>
	 * Message: ギラン経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GIRAN_IS_DEAD;

	/**
	 * ID: 73364<br>
	 * Message: オーレン経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_OREN_IS_DEAD;

	/**
	 * ID: 73365<br>
	 * Message: アデン経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_ADEN_IS_DEAD;

	/**
	 * ID: 73366<br>
	 * Message: インナドリル経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_INNADRIL_IS_DEAD;

	/**
	 * ID: 73367<br>
	 * Message: ゴダード経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_GODDARD_IS_DEAD;

	/**
	 * ID: 73368<br>
	 * Message: ルウン経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_RUNE_IS_DEAD;

	/**
	 * ID: 73369<br>
	 * Message: シュチュッツガルト経済連合長が死んだ！
	 */
	public static final NpcStringId THE_ECONOMIC_ASSOCIATION_LEADER_OF_SCHUTTGART_IS_DEAD;

	/**
	 * ID: 73451<br>
	 * Message: 戦場の盾である敵軍のナイトを$s1人倒せ！
	 */
	public static final NpcStringId DEFEAT_S1_ENEMY_KNIGHTS;

	/**
	 * ID: 73461<br>
	 * Message: 敵軍のナイトを$s1人中$s2人倒した。
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_KNIGHTS;

	/**
	 * ID: 73462<br>
	 * Message: 敵のシールドを貫いた！
	 */
	public static final NpcStringId YOU_WEAKENED_THE_ENEMYS_DEFENSE;

	/**
	 * ID: 73551<br>
	 * Message: 敵軍のウォーリアーとローグを$s1人倒せ！
	 */
	public static final NpcStringId DEFEAT_S1_WARRIORS_AND_ROGUES;

	/**
	 * ID: 73561<br>
	 * Message: $s1人中$s2人の攻撃力を弱らせた。
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_WARRIORS_AND_ROGUES;

	/**
	 * ID: 73562<br>
	 * Message: 敵の攻撃力を弱らせた。
	 */
	public static final NpcStringId YOU_WEAKENED_THE_ENEMYS_ATTACK;

	/**
	 * ID: 73651<br>
	 * Message: 敵軍のウィザードと召喚系列を$s1人倒せ！
	 */
	public static final NpcStringId DEFEAT_S1_WIZARDS_AND_SUMMONERS;

	/**
	 * ID: 73661<br>
	 * Message: $s1人中$s2人の魔力を弱らせた。
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_ENEMIES;

	/**
	 * ID: 73662<br>
	 * Message: 敵の魔力を弱らせた。
	 */
	public static final NpcStringId YOU_WEAKENED_THE_ENEMYS_MAGIC;

	/**
	 * ID: 73751<br>
	 * Message: 敵軍の士気を高める祝福をあたえる者どもを$s1人倒せ！
	 */
	public static final NpcStringId DEFEAT_S1_HEALERS_AND_BUFFERS;

	/**
	 * ID: 73761<br>
	 * Message: $s1人中$s2人の祝福を阻止した。
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_HEALERS_AND_BUFFERS;

	/**
	 * ID: 73762<br>
	 * Message: 敵の士気が落ちた！
	 */
	public static final NpcStringId YOU_HAVE_WEAKENED_THE_ENEMYS_SUPPORT;

	/**
	 * ID: 73851<br>
	 * Message: 勝利のカギである敵軍のウォースミスとオーバーロードを$s1人倒せ！
	 */
	public static final NpcStringId DEFEAT_S1_WARSMITHS_AND_OVERLORDS;

	/**
	 * ID: 73861<br>
	 * Message: $s1人中$s2人のカギを片付けた。
	 */
	public static final NpcStringId YOU_HAVE_DEFEATED_S2_OF_S1_WARSMITHS_AND_OVERLORDS;

	/**
	 * ID: 73862<br>
	 * Message: 敵のカギを壊した！
	 */
	public static final NpcStringId YOU_DESTROYED_THE_ENEMYS_PROFESSIONALS;

	/**
	 * ID: 99601<br>
	 * Message: ラララ～今日もゆかいな旅の道～次は何を探しに出よう～
	 */
	public static final NpcStringId TRA_LA_LA_TODAY_IM_GOING_TO_MAKE_ANOTHER_FUN_FILLED_TRIP_I_WONDER_WHAT_I_SHOULD_LOOK_FOR_THIS_TIME;

	/**
	 * ID: 99700<br>
	 * Message: アタシのこと呼んだ？
	 */
	public static final NpcStringId WHATS_THIS_WHY_AM_I_BEING_DISTURBED;

	/**
	 * ID: 99701<br>
	 * Message: こんにちは、アタシ、ポモナ！よろしくお願いしま～す！
	 */
	public static final NpcStringId TA_DA_HERE_I_AM;

	/**
	 * ID: 99702<br>
	 * Message: うふふっ、アタシのこと呼んだ？
	 */
	public static final NpcStringId WHAT_ARE_YOU_LOOKING_AT;

	/**
	 * ID: 99703<br>
	 * Message: みんなお待たせしました！ポモナ、来たわよ！これからどんどん育っちゃうんだから！
	 */
	public static final NpcStringId IF_YOU_GIVE_ME_NECTAR_THIS_LITTLE_SQUASH_WILL_GROW_UP_QUICKLY;

	/**
	 * ID: 99704<br>
	 * Message: あら？アナタに会うのは初めてかしら？アタシ、ポモナ！よろしくね！
	 */
	public static final NpcStringId ARE_YOU_MY_MOMMY;

	/**
	 * ID: 99705<br>
	 * Message: アタシ、アナタのことを愛せるようにがんばるから、いっぱい愛してね！
	 */
	public static final NpcStringId FANCY_MEETING_YOU_HERE;

	/**
	 * ID: 99706<br>
	 * Message: 何か用かしら？エッ！こんなアタシに愛をくれるっていうの？
	 */
	public static final NpcStringId ARE_YOU_AFRAID_OF_THE_BIG_BAD_SQUASH;

	/**
	 * ID: 99707<br>
	 * Message: うふ。美しいアタシの姿、そんなに見たい～？
	 */
	public static final NpcStringId IMPRESSIVE_ARENT_I;

	/**
	 * ID: 99708<br>
	 * Message: アナタに呼ばれてここに来たのよ！早く愛をちょうだ～い！
	 */
	public static final NpcStringId OBEY_ME;

	/**
	 * ID: 99709<br>
	 * Message: こんにちは、アタシ、ポモナ！ここに来たのは初めてかな？よろしくね！
	 */
	public static final NpcStringId RAISE_ME_WELL_AND_YOULL_BE_REWARDED_NEGLECT_ME_AND_SUFFER_THE_CONSEQUENCES;

	/**
	 * ID: 99710<br>
	 * Message: お待たせっ！それでは、変身の時間よ！
	 */
	public static final NpcStringId TRANSFORM;

	/**
	 * ID: 99711<br>
	 * Message: どう？アタシ、キレイになった？？キレイになったって言って！
	 */
	public static final NpcStringId I_FEEL_DIFFERENT;

	/**
	 * ID: 99712<br>
	 * Message: 成熟したわよ！さぁ、どこからでもいーよっ！
	 */
	public static final NpcStringId IM_BIGGER_NOW_BRING_IT_ON;

	/**
	 * ID: 99713<br>
	 * Message: あっ．．．熱いものが全身を駆け巡るの！
	 */
	public static final NpcStringId IM_NOT_A_KID_ANYMORE;

	/**
	 * ID: 99714<br>
	 * Message: アタシのこと、大切にしてくれないのね．．．ハズレちゃえ！
	 */
	public static final NpcStringId BIG_TIME;

	/**
	 * ID: 99716<br>
	 * Message: ご飯がいい？お風呂がいい？それとも、ア・タ・シ？えへへ、これ一度言ってみたかったんだ。
	 */
	public static final NpcStringId IM_ALL_GROWN_UP_NOW;

	/**
	 * ID: 99717<br>
	 * Message: 1千万アデナあげるから許してぇ！これってもう古い？
	 */
	public static final NpcStringId IF_YOU_LET_ME_GO_ILL_BE_YOUR_BEST_FRIEND;

	/**
	 * ID: 99718<br>
	 * Message: アタシの中に秘められたもの見てみたい？
	 */
	public static final NpcStringId IM_CHUCK_FULL_OF_GOODNESS;

	/**
	 * ID: 99719<br>
	 * Message: アタシの今欲しいものわかる？わかってるでしょ？そうよ、もう一つの愛が欲しいの！
	 */
	public static final NpcStringId GOOD_JOB_NOW_WHAT_ARE_YOU_GOING_TO_DO;

	/**
	 * ID: 99720<br>
	 * Message: ねぇ、もう少しネクターをかけてっ！アタシへの愛ってそんなものじゃないでしょ？
	 */
	public static final NpcStringId KEEP_IT_COMING;

	/**
	 * ID: 99721<br>
	 * Message: あぁ、イイ感じ。気持ちよいわよ！もう少しちょうだい！
	 */
	public static final NpcStringId THATS_WHAT_IM_TALKING_ABOUT;

	/**
	 * ID: 99722<br>
	 * Message: アナタの愛で満たされる感じ．．．もっと欲しいの！
	 */
	public static final NpcStringId MAY_I_HAVE_SOME_MORE;

	/**
	 * ID: 99723<br>
	 * Message: もう！まともにできないの？こぼれてるじゃない！プンプン
	 */
	public static final NpcStringId THAT_HIT_THE_SPOT;

	/**
	 * ID: 99724<br>
	 * Message: や～だ～何か変よ！そうよ、アタシへの愛が足りていないのよ！
	 */
	public static final NpcStringId I_FEEL_SPECIAL;

	/**
	 * ID: 99725<br>
	 * Message: あぁ、すっごいいいかも．．．もっとちょうだい！
	 */
	public static final NpcStringId I_THINK_ITS_WORKING;

	/**
	 * ID: 99726<br>
	 * Message: イヤッ！そこじゃないの！ヘタクソ！もう、嫌いになっちゃうよ！
	 */
	public static final NpcStringId YOU_DO_UNDERSTAND;

	/**
	 * ID: 99727<br>
	 * Message: ウワァーン！今のおいしくなかったよ～．．．ねぇ、今の本当にネクターだったの？
	 */
	public static final NpcStringId YUCK_WHAT_IS_THIS_HA_HA_JUST_KIDDING;

	/**
	 * ID: 99728<br>
	 * Message: ねぇ、しっかりしてよ！キレイに育てたいなら、ネクターをキレイにかけるのも大切よ！
	 */
	public static final NpcStringId A_TOTAL_OF_FIVE_AND_ILL_BE_TWICE_AS_ALIVE;

	/**
	 * ID: 99729<br>
	 * Message: 愛に満ちたネクターが欲しいの！
	 */
	public static final NpcStringId NECTAR_IS_SUBLIME;

	/**
	 * ID: 99730<br>
	 * Message: アンッ！アタシ今、顔を叩かれた～顔は命なのっ！お願いだから顔だけはやめて！
	 */
	public static final NpcStringId YOU_CALL_THAT_A_HIT;

	/**
	 * ID: 99731<br>
	 * Message: アタシをいぢめないで～やめてぇ！叩かないでぇ！アタシは、アナタの愛が欲しいの～
	 */
	public static final NpcStringId WHY_ARE_YOU_HITTING_ME_OUCH_STOP_IT_GIVE_ME_NECTAR;

	/**
	 * ID: 99732<br>
	 * Message: アナタの愛が感じられないの！これじゃ、アタシ萎えちゃうよ！
	 */
	public static final NpcStringId STOP_OR_ILL_WILT;

	/**
	 * ID: 99733<br>
	 * Message: アタシとの愛を育てる気はないの？アタシは、アナタの愛のこもったネクターが今欲しいの！
	 */
	public static final NpcStringId IM_NOT_FULLY_GROWN_YET_OH_WELL_DO_WHAT_YOU_WILL_ILL_FADE_AWAY_WITHOUT_NECTAR_ANYWAY;

	/**
	 * ID: 99734<br>
	 * Message: まだ早いの！痛いのはやなの！ネクターが欲しいの！
	 */
	public static final NpcStringId GO_AHEAD_AND_HIT_ME_AGAIN_IT_WONT_DO_YOU_ANY_GOOD;

	/**
	 * ID: 99735<br>
	 * Message: このままじゃしおれちゃうの！もうアタシがどうなってもよいっていうの？
	 */
	public static final NpcStringId WOE_IS_ME_IM_WILTING;

	/**
	 * ID: 99736<br>
	 * Message: アタシがしおれたら、なにも残らないよ？そんなにアタシのこと嫌いなの？それともアナタの愛は終わってしまったの？
	 */
	public static final NpcStringId IM_NOT_FULLY_GROWN_YET_HOW_ABOUT_SOME_NECTAR_TO_EASE_MY_PAIN;

	/**
	 * ID: 99737<br>
	 * Message: 今、別のこと考えてたでしょ？そんなんじゃ、アナタの愛はアタシに伝わってこないの！もっと愛して！
	 */
	public static final NpcStringId THE_END_IS_NEAR;

	/**
	 * ID: 99738<br>
	 * Message: ネェ、アタシを助けてよ～ネクターを一度も飲めずにしおれるなんて、恋を知らずして死ぬのと一緒よ！
	 */
	public static final NpcStringId PRETTY_PLEASE_WITH_SUGAR_ON_TOP_GIVE_ME_SOME_NECTAR;

	/**
	 * ID: 99739<br>
	 * Message: このままだとアタシ、生まれてきた意味もなく、しおれるしかないのね。すっごく悲しい．．．
	 */
	public static final NpcStringId IF_I_DIE_WITHOUT_NECTAR_YOULL_GET_NOTHING;

	/**
	 * ID: 99740<br>
	 * Message: じらし過ぎなの！だんだん飽きてきたの！30秒以内にくれないなら、もう帰っちゃうよ！
	 */
	public static final NpcStringId IM_FEELING_BETTER_ANOTHER_THIRTY_SECONDS_AND_ILL_BE_OUT_OF_HERE;

	/**
	 * ID: 99741<br>
	 * Message: あと20秒！アタシへの愛ってそんなもんだったの？
	 */
	public static final NpcStringId TWENTY_SECONDS_AND_ITS_CIAO_BABY;

	/**
	 * ID: 99742<br>
	 * Message: あぁ、残り10秒！9．．．8．．．7．．．
	 */
	public static final NpcStringId WOOHOO_JUST_TEN_SECONDS_LEFT_NINE_EIGHT_SEVEN;

	/**
	 * ID: 99743<br>
	 * Message: ネクターをくれないなら、2分後に帰っちゃうよ！
	 */
	public static final NpcStringId GIVE_ME_NECTAR_OR_ILL_BE_GONE_IN_TWO_MINUTES;

	/**
	 * ID: 99744<br>
	 * Message: ネクターをくれないなら、1分後にサヨナラなんだから！
	 */
	public static final NpcStringId GIVE_ME_NECTAR_OR_ILL_BE_GONE_IN_ONE_MINUTE;

	/**
	 * ID: 99745<br>
	 * Message: 今アタシね、アナタの未来のことを想像していたの。そしたらね、未来のアタシはアナタにいっぱい愛されていたの。また逢える日まで元気でいてねっ！きっとだよ！
	 */
	public static final NpcStringId SO_LONG_SUCKERS;

	/**
	 * ID: 99746<br>
	 * Message: 愛って何なのかわかる？アナタにそれが理解できたら、もう一度逢えると思うの。また逢いましょうね！
	 */
	public static final NpcStringId IM_OUT_OF_HERE;

	/**
	 * ID: 99747<br>
	 * Message: つまんない！アタシ、もう帰る！そんなんじゃ、他のポモナにも嫌われるんだから！何が悪かったか反省しなさ～い！
	 */
	public static final NpcStringId I_MUST_BE_GOING_HAVE_FUN_EVERYBODY;

	/**
	 * ID: 99748<br>
	 * Message: もう、お別れの時間なの。次に逢える日までに、アタシへの愛を育んでおいてね！
	 */
	public static final NpcStringId TIME_IS_UP_PUT_YOUR_WEAPONS_DOWN;

	/**
	 * ID: 99749<br>
	 * Message: 一方的な愛って、偏ってるっていうか．．．うん、それってダメだと思うの。ここまでしてくれたんだけど、もう行くね。本当にゴメンね。
	 */
	public static final NpcStringId GOOD_FOR_ME_BAD_FOR_YOU;

	/**
	 * ID: 99750<br>
	 * Message: あぁ、すっごくいい音色ね！
	 */
	public static final NpcStringId SOUNDTASTIC;

	/**
	 * ID: 99751<br>
	 * Message: ねぇ、ねぇ、一曲歌ってもいい？
	 */
	public static final NpcStringId I_CAN_SING_ALONG_IF_YOU_LIKE;

	/**
	 * ID: 99752<br>
	 * Message: 愛ってすばらしいの！音色でも愛が伝わるものなのねっ！
	 */
	public static final NpcStringId I_THINK_YOU_NEED_SOME_BACKUP;

	/**
	 * ID: 99753<br>
	 * Message: いい気分になってきた～もう少し聞かせてよ！
	 */
	public static final NpcStringId KEEP_UP_THAT_RHYTHM_AND_YOULL_BE_A_STAR;

	/**
	 * ID: 99754<br>
	 * Message: アタシのためだけに弾いてくれるの？うれしいっ！
	 */
	public static final NpcStringId MY_HEART_YEARNS_FOR_MORE_MUSIC;

	/**
	 * ID: 99755<br>
	 * Message: ちょっと！音が変じゃない？アタシへの愛ってそんなものなの？
	 */
	public static final NpcStringId YOURE_OUT_OF_TUNE_AGAIN;

	/**
	 * ID: 99756<br>
	 * Message: すっかりポモ肌！スゴくうれしいよっ！
	 */
	public static final NpcStringId THIS_IS_AWFUL;

	/**
	 * ID: 99757<br>
	 * Message: ああ～～、身体がムズムズしてきたわ！いい曲を聞くと鳥肌が立つっていうけど、アタシの場合ポモ肌？多分そんな感じよね？
	 */
	public static final NpcStringId I_THINK_I_BROKE_SOMETHING;

	/**
	 * ID: 99758<br>
	 * Message: あっ、この和音！もう最高！もっと聞かせてなの！
	 */
	public static final NpcStringId WHAT_A_LOVELY_MELODY_PLAY_IT_AGAIN;

	/**
	 * ID: 99759<br>
	 * Message: これよ、これ！このサウンドが聴きたかったの！アナタ、ミュージシャンになれるわよっ！
	 */
	public static final NpcStringId MUSIC_TO_MY_UH_EARS;

	/**
	 * ID: 99760<br>
	 * Message: こんなんじゃダメ！いくら叩いても愛は育たないよ！
	 */
	public static final NpcStringId YOU_NEED_MUSIC_LESSONS;

	/**
	 * ID: 99761<br>
	 * Message: 痛いのっ！アタシは音楽が聴きたいの！まじめにやってよ！
	 */
	public static final NpcStringId I_CANT_HEAR_YOU;

	/**
	 * ID: 99762<br>
	 * Message: いいサウンドじゃなきゃ、アタシの心と身体には響かないの！
	 */
	public static final NpcStringId YOU_CANT_HURT_ME_LIKE_THAT;

	/**
	 * ID: 99763<br>
	 * Message: これじゃないの！あれよ、あれ！クロノの楽器！あれでやるの！
	 */
	public static final NpcStringId IM_STRONGER_THAN_YOU_ARE;

	/**
	 * ID: 99764<br>
	 * Message: 何で音楽がないの？アタシ、もう帰っていい？．．．いいの？本当に帰っちゃうよ？
	 */
	public static final NpcStringId NO_MUSIC_IM_OUT_OF_HERE;

	/**
	 * ID: 99765<br>
	 * Message: こういうのは、いやっ！音の出る物でやってよ！
	 */
	public static final NpcStringId THAT_RACKET_IS_GETTING_ON_MY_NERVES_TONE_IT_DOWN_A_BIT;

	/**
	 * ID: 99766<br>
	 * Message: アタシは、音楽じゃなきゃダメなのぉ～武器で叩くだけじゃ意味がないの！
	 */
	public static final NpcStringId YOU_CAN_ONLY_HURT_ME_THROUGH_MUSIC;

	/**
	 * ID: 99767<br>
	 * Message: 楽器でやってよっ！こんな物じゃイヤ！楽器でするの！
	 */
	public static final NpcStringId ONLY_MUSICAL_INSTRUMENTS_CAN_HURT_ME_NOTHING_ELSE;

	/**
	 * ID: 99768<br>
	 * Message: アナタのアタックってスゴイのね～でも、無駄無駄なのぉ～音がないままなんてダメだよ！
	 */
	public static final NpcStringId YOUR_SKILLS_ARE_IMPRESSIVE_BUT_SADLY_USELESS;

	/**
	 * ID: 99769<br>
	 * Message: 刃物で脅しても無駄よ！アタシが欲しいのは音なの！
	 */
	public static final NpcStringId CATCH_A_CHRONO_FOR_ME_PLEASE;

	/**
	 * ID: 99770<br>
	 * Message: ポモナとして、アナタに逢えてよかった。また生まれてきてもポモナでいたいな。アナタとだったらもう一度．．．
	 */
	public static final NpcStringId YOU_GOT_ME;

	/**
	 * ID: 99771<br>
	 * Message: 萎え萎え祈願！ハズレちゃえ！キャハハ
	 */
	public static final NpcStringId NOW_LOOK_AT_WHAT_YOUVE_DONE;

	/**
	 * ID: 99772<br>
	 * Message: アタシとの思い出、大切にしてね～
	 */
	public static final NpcStringId YOU_WIN;

	/**
	 * ID: 99773<br>
	 * Message: これがアナタとアタシの愛の結晶よ！
	 */
	public static final NpcStringId SQUASHED;

	/**
	 * ID: 99774<br>
	 * Message: アナタの想い、受け取ったの。もう、サヨナラだね．．．
	 */
	public static final NpcStringId DONT_TELL_ANYONE;

	/**
	 * ID: 99775<br>
	 * Message: 目が霞んでアナタの顔がもう見えないの．．．でもね、アナタの事は忘れない。また逢えるよね？
	 */
	public static final NpcStringId GROSS_MY_GUTS_ARE_COMING_OUT;

	/**
	 * ID: 99776<br>
	 * Message: あ～、すごくよかったの！満足なの～、これでまた転生することができるかな？お礼としてこれあげるね。
	 */
	public static final NpcStringId TAKE_IT_AND_GO;

	/**
	 * ID: 99777<br>
	 * Message: アナタの愛、いっぱい受けとったの。でも、もうお別れだね。アナタのこと、忘れない！また逢おうね！
	 */
	public static final NpcStringId I_SHOULDVE_LEFT_WHEN_I_COULD;

	/**
	 * ID: 99778<br>
	 * Message: ポモナ、割れちゃったの！いいもの出た～？出なかったとしても、もう一度おねがいね！
	 */
	public static final NpcStringId NOW_LOOK_WHAT_YOU_HAVE_DONE;

	/**
	 * ID: 99779<br>
	 * Message: きゃっ！割れちゃったぁ！アタシの中の愛が～～～～
	 */
	public static final NpcStringId I_FEEL_DIRTY;

	/**
	 * ID: 99780<br>
	 * Message: 初めてなの！優しくしてね！
	 */
	public static final NpcStringId BETTER_LUCK_NEXT_TIME;

	/**
	 * ID: 99781<br>
	 * Message: あぁ、これいいかな？
	 */
	public static final NpcStringId NICE_SHOT;

	/**
	 * ID: 99782<br>
	 * Message: もっと！もっと愛が欲しいの！
	 */
	public static final NpcStringId IM_NOT_AFRAID_OF_YOU;

	/**
	 * ID: 99783<br>
	 * Message: アタシはアナタの愛で育つのよ！
	 */
	public static final NpcStringId IF_I_KNEW_THIS_WAS_GOING_TO_HAPPEN_I_WOULD_HAVE_STAYED_HOME;

	/**
	 * ID: 99784<br>
	 * Message: それでめいいっぱいの愛なの？こんなんじゃアタシだめなの！
	 */
	public static final NpcStringId TRY_HARDER_OR_IM_OUT_OF_HERE;

	/**
	 * ID: 99785<br>
	 * Message: たったその程度で満足させられるとでも思ってるの？
	 */
	public static final NpcStringId IM_TOUGHER_THAN_I_LOOK;

	/**
	 * ID: 99786<br>
	 * Message: すごいね。アタシ、そういうの好きよ。
	 */
	public static final NpcStringId GOOD_STRIKE;

	/**
	 * ID: 99787<br>
	 * Message: そうよ、そこよっ！あーん、もう少し右っ！はふぅ
	 */
	public static final NpcStringId OH_MY_GOURD;

	/**
	 * ID: 99788<br>
	 * Message: ちょっとぉ、アナタの愛ってその程度のものなの？
	 */
	public static final NpcStringId THATS_ALL_YOUVE_GOT;

	/**
	 * ID: 99789<br>
	 * Message: アタシのことだけを考えて！他のことを考えるなんてダメなの！もっと叩いて！
	 */
	public static final NpcStringId WHY_ME;

	/**
	 * ID: 99790<br>
	 * Message: ネクターが欲しいの！どうしてもネクターが欲しいの！
	 */
	public static final NpcStringId BRING_ME_NECTAR;

	/**
	 * ID: 99791<br>
	 * Message: アタシね、ネクターをかけられると大きくなるのっ！
	 */
	public static final NpcStringId I_MUST_HAVE_NECTAR_TO_GROW;

	/**
	 * ID: 99792<br>
	 * Message: さあ早く、アナタの愛をいっぱい頂戴！上手くいけばアタシ、キレイに育つよ！
	 */
	public static final NpcStringId GIVE_ME_SOME_NECTAR_QUICKLY_OR_YOULL_GET_NOTHING;

	/**
	 * ID: 99793<br>
	 * Message: ねぇ、早く愛のこもったネクターをちょうだい！アタシの心とお腹はもうペコペコよっ！
	 */
	public static final NpcStringId PLEASE_GIVE_ME_SOME_NECTAR_IM_HUNGRY;

	/**
	 * ID: 99794<br>
	 * Message: 早く、ネクターをいっぱいかけて！
	 */
	public static final NpcStringId NECTAR_PLEASE;

	/**
	 * ID: 99795<br>
	 * Message: ネクター．．．それってアタシへの愛の表現なの。だからアタシはそれをかけられて大きくなるの！
	 */
	public static final NpcStringId NECTAR_WILL_MAKE_ME_GROW_QUICKLY;

	/**
	 * ID: 99796<br>
	 * Message: こんな未熟なうちに頂こうっていうの？少し気が早いの。もうすこし大きくなってからアタシを召上れ！
	 */
	public static final NpcStringId DONT_YOU_WANT_A_BIGGER_SQUASH_GIVE_ME_SOME_NECTAR_AND_ILL_GROW_MUCH_LARGER;

	/**
	 * ID: 99797<br>
	 * Message: アナタに贈る歌を考えたの。聞いて！アナタに逢えてよかった～アタシの愛を受け止めてくれるから～もしアタシがダメになっても～最後まで一緒に愛を育てよう．．．どう？ダメかな？
	 */
	public static final NpcStringId IF_YOU_RAISE_ME_WELL_YOULL_GET_PRIZES_OR_NOT;

	/**
	 * ID: 99798<br>
	 * Message: きっとアナタにもいいことがあると思うよ！アタシは、どんなことがあってもアナタの愛さえあればぜんぜん平気～信じてるからね！
	 */
	public static final NpcStringId YOU_ARE_HERE_FOR_THE_STUFF_EH_WELL_ITS_MINE_ALL_MINE;

	/**
	 * ID: 99799<br>
	 * Message: ねぇ、アタシを信じてネクターをかけてぇ！アタシが成熟したらきっといい事が訪れるよ！
	 */
	public static final NpcStringId TRUST_ME_GIVE_ME_NECTAR_AND_ILL_BECOME_A_GIANT_SQUASH;

	/**
	 * ID: 528551<br>
	 * Message: そんなあほらしいことを言うのはどの口だ！もう聞いてられやしない。
	 */
	public static final NpcStringId THERES_NOTHING_YOU_CANT_SAY_I_CANT_LISTEN_TO_YOU_ANYMORE;

	/**
	 * ID: 528651<br>
	 * Message: あんなに堂々と出てったのに、帰ってくるときはこのザマ？ホホホ。
	 */
	public static final NpcStringId YOU_ADVANCED_BRAVELY_BUT_GOT_SUCH_A_TINY_RESULT_HOHOHO;

	/**
	 * ID: 1000001<br>
	 * Message: 侵入者を発見しました。
	 */
	public static final NpcStringId A_NON_PERMITTED_TARGET_HAS_BEEN_DISCOVERED;

	/**
	 * ID: 1000002<br>
	 * Message: 侵入者除去システムを起動します。
	 */
	public static final NpcStringId INTRUDER_REMOVAL_SYSTEM_INITIATED;

	/**
	 * ID: 1000003<br>
	 * Message: 侵入者除去中．．．
	 */
	public static final NpcStringId REMOVING_INTRUDERS;

	/**
	 * ID: 1000004<br>
	 * Message: 致命的なエラーが発生しました。
	 */
	public static final NpcStringId A_FATAL_ERROR_HAS_OCCURRED;

	/**
	 * ID: 1000005<br>
	 * Message: システムを終了します．．．
	 */
	public static final NpcStringId SYSTEM_IS_BEING_SHUT_DOWN;

	/**
	 * ID: 1000006<br>
	 * Message: ．．．．．．
	 */
	public static final NpcStringId DOT_DOT_DOT_DOT_DOT_DOT;
	
	/**
	 * ID: 1000007<br>
	 * Message: 覚えてろ！
	 */
	public static final NpcStringId WE_SHALL_SEE_ABOUT_THAT;

	/**
	 * ID: 1000008<br>
	 * Message: この屈辱、忘れぬぞ！
	 */
	public static final NpcStringId I_WILL_DEFINITELY_REPAY_THIS_HUMILIATION;

	/**
	 * ID: 1000009<br>
	 * Message: 撤退！
	 */
	public static final NpcStringId RETREAT;

	/**
	 * ID: 1000010<br>
	 * Message: 戦略的に後退だ！
	 */
	public static final NpcStringId TACTICAL_RETREAT;

	/**
	 * ID: 1000011<br>
	 * Message: 逃げろ！
	 */
	public static final NpcStringId MASS_FLEEING;

	/**
	 * ID: 1000012<br>
	 * Message: 思ったより手強い！
	 */
	public static final NpcStringId ITS_STRONGER_THAN_EXPECTED;

	/**
	 * ID: 1000013<br>
	 * Message: 覚えておけよ！
	 */
	public static final NpcStringId ILL_KILL_YOU_NEXT_TIME;

	/**
	 * ID: 1000014<br>
	 * Message: 次こそは、やってやる！
	 */
	public static final NpcStringId ILL_DEFINITELY_KILL_YOU_NEXT_TIME;

	/**
	 * ID: 1000015<br>
	 * Message: くぅ！手強い！
	 */
	public static final NpcStringId OH_HOW_STRONG;

	/**
	 * ID: 1000016<br>
	 * Message: 侵入者だ！
	 */
	public static final NpcStringId INVADER;

	/**
	 * ID: 1000017<br>
	 * Message: 俺を殺しても何にもならないぜ．．．ククク。
	 */
	public static final NpcStringId THERE_IS_NO_REASON_FOR_YOU_TO_KILL_ME_I_HAVE_NOTHING_YOU_NEED;

	/**
	 * ID: 1000018<br>
	 * Message: 次こそは必ず．．．
	 */
	public static final NpcStringId SOMEDAY_YOU_WILL_PAY;

	/**
	 * ID: 1000019<br>
	 * Message: なぜ俺ばかり殴るんだ！
	 */
	public static final NpcStringId I_WONT_JUST_STAND_STILL_WHILE_YOU_HIT_ME;

	/**
	 * ID: 1000020<br>
	 * Message: 勘弁してくれ！
	 */
	public static final NpcStringId STOP_HITTING;

	/**
	 * ID: 1000021<br>
	 * Message: 骨の芯まで痺れるぜっ！
	 */
	public static final NpcStringId IT_HURTS_TO_THE_BONE;

	/**
	 * ID: 1000022<br>
	 * Message: 俺は殴られ役か！
	 */
	public static final NpcStringId AM_I_THE_NEIGHBORHOOD_DRUM_FOR_BEATING;

	/**
	 * ID: 1000023<br>
	 * Message: ついて来られるもんなら、ついて来な！
	 */
	public static final NpcStringId FOLLOW_ME_IF_YOU_WANT;

	/**
	 * ID: 1000024<br>
	 * Message: 降伏だ！
	 */
	public static final NpcStringId SURRENDER;

	/**
	 * ID: 1000025<br>
	 * Message: あぁ、もうおしまいだ！
	 */
	public static final NpcStringId OH_IM_DEAD;

	/**
	 * ID: 1000026<br>
	 * Message: クソ！首を洗って待っとけ！
	 */
	public static final NpcStringId ILL_BE_BACK;

	/**
	 * ID: 1000027<br>
	 * Message: お、俺を助けてくれるなら、1千万アデナやるぞ！
	 */
	public static final NpcStringId ILL_GIVE_YOU_TEN_MILLION_ARENA_IF_YOU_LET_ME_LIVE;

	/**
	 * ID: 1000028<br>
	 * Message: $s1。おのれの無力さを思い知れ！
	 */
	public static final NpcStringId S1_STOP_KIDDING_YOURSELF_ABOUT_YOUR_OWN_POWERLESSNESS;

	/**
	 * ID: 1000029<br>
	 * Message: $s1！真の恐怖とは何かを教えてやろう！
	 */
	public static final NpcStringId S1_ILL_MAKE_YOU_FEEL_WHAT_TRUE_FEAR_IS;

	/**
	 * ID: 1000030<br>
	 * Message: 愚かにも私にたてついたことを後悔させてやろう。$s1！覚悟しろ！
	 */
	public static final NpcStringId YOURE_REALLY_STUPID_TO_HAVE_CHALLENGED_ME_S1_GET_READY;

	/**
	 * ID: 1000031<br>
	 * Message: $s1。そんな手が通じるとでも思ったのか！
	 */
	public static final NpcStringId S1_DO_YOU_THINK_THATS_GOING_TO_WORK;

	/**
	 * ID: 1000032<br>
	 * Message: 汚された私の名誉、必ずや挽回してみせる！
	 */
	public static final NpcStringId I_WILL_DEFINITELY_RECLAIM_MY_HONOR_WHICH_HAS_BEEN_TARNISHED;

	/**
	 * ID: 1000033<br>
	 * Message: 哀れな騎士の憎しみを受けてみろ！
	 */
	public static final NpcStringId SHOW_ME_THE_WRATH_OF_THE_KNIGHT_WHOSE_HONOR_HAS_BEEN_DOWNTRODDEN;

	/**
	 * ID: 1000034<br>
	 * Message: あわれな偽善者に死を！
	 */
	public static final NpcStringId DEATH_TO_THE_HYPOCRITE;

	/**
	 * ID: 1000035<br>
	 * Message: 濡れ衣を晴らすまで決して眠りに付くことなぞ、できぬわ！
	 */
	public static final NpcStringId ILL_NEVER_SLEEP_UNTIL_IVE_SHED_MY_DISHONOR;

	/**
	 * ID: 1000036<br>
	 * Message: 世の中を呪う者どもよ、私はここだ！
	 */
	public static final NpcStringId IM_HERE_FOR_THE_ONES_THAT_ARE_CURSING_THE_WORLD;

	/**
	 * ID: 1000037<br>
	 * Message: お前もさまよえる魂にしてやろう！
	 */
	public static final NpcStringId ILL_TURN_YOU_INTO_A_MALIGNANT_SPIRIT;

	/**
	 * ID: 1000038<br>
	 * Message: 怨恨と憎悪の力でお前を呪ってやる！
	 */
	public static final NpcStringId ILL_CURSE_YOU_WITH_THE_POWER_OF_REVENGE_AND_HATE;

	/**
	 * ID: 1000039<br>
	 * Message: グレシアの栄光のために！
	 */
	public static final NpcStringId FOR_THE_GLORY_OF_GRACIA;

	/**
	 * ID: 1000040<br>
	 * Message: 生意気にも私と力比べをしたいだと？
	 */
	public static final NpcStringId DO_YOU_DARE_PIT_YOUR_POWER_AGAINST_ME;

	/**
	 * ID: 1000041<br>
	 * Message: こ、この私が敗北するとは！
	 */
	public static final NpcStringId I_I_AM_DEFEATED;

	/**
	 * ID: 1000042<br>
	 * Message: 私はヌルカ様の意思を伝える者だ！ 邪魔だ、どけ！
	 */
	public static final NpcStringId I_AM_CONVEYING_THE_WILL_OF_NURKA_EVERYBODY_GET_OUT_OF_MY_WAY;

	/**
	 * ID: 1000043<br>
	 * Message: 私の前を遮る者は、誰であろうが無事ではいられんぞ！
	 */
	public static final NpcStringId THOSE_WHO_STAND_AGAINST_ME_SHALL_DIE_HORRIBLY;

	/**
	 * ID: 1000044<br>
	 * Message: なに！私の邪魔をするのか！
	 */
	public static final NpcStringId DO_YOU_DARE_TO_BLOCK_MY_WAY;

	/**
	 * ID: 1000045<br>
	 * Message: 私の仲間が復讐してくれるだろう！
	 */
	public static final NpcStringId MY_COMRADES_WILL_GET_REVENGE;

	/**
	 * ID: 1000046<br>
	 * Message: 我々の神聖な大地を血に染める者は許さんぞ！
	 */
	public static final NpcStringId YOU_HEATHEN_BLASPHEMERS_OF_THIS_HOLY_PLACE_WILL_BE_PUNISHED;

	/**
	 * ID: 1000047<br>
	 * Message: 私の権威に挑戦する愚かな者どもよ、前に出よ！
	 */
	public static final NpcStringId STEP_FORWARD_YOU_WORTHLESS_CREATURES_WHO_CHALLENGE_MY_AUTHORITY;

	/**
	 * ID: 1000048<br>
	 * Message: 私の創造者．．．私のご主人様に変わりない．．．忠誠を．．．
	 */
	public static final NpcStringId MY_CREATOR_THE_UNCHANGING_FAITHFULNESS_TO_MY_MASTER;

	/**
	 * ID: 1000049<br>
	 * Message: 塔の．．．主人．．．私のご主人様．．．ご主人様．．．いずこに？
	 */
	public static final NpcStringId MASTER_OF_THE_TOWER_MY_MASTER_MASTER_WHERE_IS_HE;

	/**
	 * ID: 1000050<br>
	 * Message: ワタシハ、コアノイシヲ スイコウスルモノ。
	 */
	public static final NpcStringId I_AM_THE_ONE_CARRYING_OUT_THE_WILL_OF_CORE;

	/**
	 * ID: 1000051<br>
	 * Message: シンニュウシャヲ、ハカイ、セヨ。
	 */
	public static final NpcStringId DESTROY_THE_INVADER;

	/**
	 * ID: 1000052<br>
	 * Message: ジョウタイ、イジョウ、サドウ、フノウ．．．
	 */
	public static final NpcStringId STRANGE_CONDITION_DOESNT_WORK;

	/**
	 * ID: 1000053<br>
	 * Message: ベレス様の命に従い．．．お前たちを監視する！
	 */
	public static final NpcStringId ACCORDING_TO_THE_COMMAND_OF_BELETH_IM_GOING_TO_OBSERVE_YOU_GUYS;

	/**
	 * ID: 1000054<br>
	 * Message: 裏切り、謀略、殺戮．．．そんなことしか知らない愚かな輩どもよ．．．お前たちを見ていると、ハラワタが煮え返りそうだ。
	 */
	public static final NpcStringId YOU_PEOPLE_MAKE_ME_SICK_NO_SENSE_OF_LOYALTY_WHATSOEVER;

	/**
	 * ID: 1000055<br>
	 * Message: 私への挑戦、それはすなわちベレス様に対する挑戦である。
	 */
	public static final NpcStringId A_CHALLENGE_AGAINST_ME_IS_THE_SAME_AS_A_CHALLENGE_AGAINST_BELETH;

	/**
	 * ID: 1000056<br>
	 * Message: ベレス様は、常にお前たちを見ていらっしゃるのだ！
	 */
	public static final NpcStringId BELETH_IS_ALWAYS_WATCHING_OVER_YOU_GUYS;

	/**
	 * ID: 1000057<br>
	 * Message: 世界の破滅が迫っている！ アンタラス様が目を覚まされたぞ！ 
	 */
	public static final NpcStringId THAT_WAS_REALLY_CLOSE_ANTHARAS_OPENED_ITS_EYES;

	/**
	 * ID: 1000058<br>
	 * Message: アンタラス様の意志に逆らう者よ！死ぬがいい！
	 */
	public static final NpcStringId YOU_WHO_DISOBEY_THE_WILL_OF_ANTHARAS_DIE;

	/**
	 * ID: 1000059<br>
	 * Message: アンタラス様、私に死をお与えください！
	 */
	public static final NpcStringId ANTHARAS_HAS_TAKEN_MY_LIFE;

	/**
	 * ID: 1000060<br>
	 * Message: 宝を取り戻すために、地獄の底から舞い戻ったぞ！
	 */
	public static final NpcStringId I_CROSSED_BACK_OVER_THE_MARSHLANDS_OF_DEATH_TO_RECLAIM_THE_TREASURE;

	/**
	 * ID: 1000061<br>
	 * Message: 隠し持っている金銀財宝を我に捧げよ！
	 */
	public static final NpcStringId BRING_OVER_AND_SURRENDER_YOUR_PRECIOUS_GOLD_TREASURE_TO_ME;

	/**
	 * ID: 1000062<br>
	 * Message: クハハハ！お前なんぞイチコロだ！
	 */
	public static final NpcStringId ILL_KILL_YOU_IN_AN_INSTANT;

	/**
	 * ID: 1000063<br>
	 * Message: ダメだ！まだ宝が．．．
	 */
	public static final NpcStringId NO_THE_TREASURE_IS_STILL;

	/**
	 * ID: 1000064<br>
	 * Message: ドラゴンバレーを犯した者どもよ、決して生きては帰さんぞ！
	 */
	public static final NpcStringId INVADERS_OF_DRAGON_VALLEY_WILL_NEVER_LIVE_TO_RETURN;

	/**
	 * ID: 1000065<br>
	 * Message: 私はアンタラス様の命でこの地を守る守護者だ！
	 */
	public static final NpcStringId I_AM_THE_GUARDIAN_THAT_HONORS_THE_COMMAND_OF_ANTHARAS_TO_WATCH_OVER_THIS_PLACE;

	/**
	 * ID: 1000066<br>
	 * Message: 許しなくドラゴンバレーに足を踏み入れるとは！死をもって罪を償うがいい！
	 */
	public static final NpcStringId YOUVE_SET_FOOT_IN_DRAGON_VALLEY_WITHOUT_PERMISSION_THE_PENALTY_IS_DEATH;

	/**
	 * ID: 1000068<br>
	 * Message: 殺戮の喜び！強奪の快感！ククク、今日も一仕事やってやろうじゃねぇか。
	 */
	public static final NpcStringId THE_JOY_OF_KILLING_THE_ECSTASY_OF_LOOTING_HEY_GUYS_LETS_HAVE_A_GO_AT_IT_AGAIN;

	/**
	 * ID: 1000069<br>
	 * Message: 世の中は、なんて怖いもの知らずなやつばっかりなんだ！懲らしめてやんぜ！
	 */
	public static final NpcStringId THERE_REALLY_ARE_STILL_LOTS_OF_FOLKS_IN_THE_WORLD_WITHOUT_FEAR_ILL_TEACH_YOU_A_LESSON;

	/**
	 * ID: 1000070<br>
	 * Message: すべての物を手渡すというなら、命だけは助けてやってもいいぜ！
	 */
	public static final NpcStringId IF_YOU_HAND_OVER_EVERYTHING_YOUVE_GOT_ILL_AT_LEAST_SPARE_YOUR_LIFE;

	/**
	 * ID: 1000071<br>
	 * Message: この程度のやつに屈服するとはな！
	 */
	public static final NpcStringId KNEEL_DOWN_BEFORE_ONE_SUCH_AS_THIS;

	/**
	 * ID: 1000072<br>
	 * Message: ご主人様の命に従い、すべての侵入者を懲らしめるのだ！
	 */
	public static final NpcStringId HONOR_THE_MASTERS_WISHES_AND_PUNISH_ALL_THE_INVADERS;

	/**
	 * ID: 1000073<br>
	 * Message: ご主人様の命に従い、侵入者に死の鉄槌を！
	 */
	public static final NpcStringId FOLLOW_THE_MASTERS_WISHES_AND_PUNISH_THE_INVADERS;

	/**
	 * ID: 1000074<br>
	 * Message: 死は、しばしの休息に過ぎん。
	 */
	public static final NpcStringId DEATH_IS_NOTHING_MORE_THAN_A_MOMENTARY_REST;

	/**
	 * ID: 1000075<br>
	 * Message: よく聞くがいい！お前たちの時代はもう終わりだ！アンタラス様が目覚めるのだ！
	 */
	public static final NpcStringId LISTEN_THIS_IS_THE_END_OF_THE_HUMAN_ERA_ANTHARAS_HAS_AWOKEN;

	/**
	 * ID: 1000076<br>
	 * Message: お前の頭をアンタラス様に献上してやろう！
	 */
	public static final NpcStringId PRESENT_THE_LIVES_OF_FOUR_PEOPLE_TO_ANTHARAS;

	/**
	 * ID: 1000077<br>
	 * Message: こ、この私が、未開な種族に負けるとは！
	 */
	public static final NpcStringId THIS_IS_UNBELIEVABLE_HOW_COULD_I_HAVE_LOST_TO_ONE_SO_INFERIOR_TO_MYSELF;

	/**
	 * ID: 1000078<br>
	 * Message: 闇の支配を背に深淵から戻れり！
	 */
	public static final NpcStringId I_CARRY_THE_POWER_OF_DARKNESS_AND_HAVE_RETURNED_FROM_THE_ABYSS;

	/**
	 * ID: 1000079<br>
	 * Message: なんとも憎らしい。
	 */
	public static final NpcStringId ITS_DETESTABLE;

	/**
	 * ID: 1000080<br>
	 * Message: ついに安息の時を迎えり。
	 */
	public static final NpcStringId I_FINALLY_FIND_REST;

	/**
	 * ID: 1000081<br>
	 * Message: オルフェン様に栄光あれ！
	 */
	public static final NpcStringId GLORY_TO_ORFEN;

	/**
	 * ID: 1000082<br>
	 * Message: オルフェン様の名を語ってこの地を犯そうとするやつらめ！許さんぞ
	 */
	public static final NpcStringId IN_THE_NAME_OF_ORFEN_I_CAN_NEVER_FORGIVE_YOU_WHO_ARE_INVADING_THIS_PLACE;

	/**
	 * ID: 1000083<br>
	 * Message: オルフェン様の地に土足で踏み込んだ罰を受けるのだ！
	 */
	public static final NpcStringId ILL_MAKE_YOU_PAY_THE_PRICE_FOR_FEARLESSLY_ENTERING_ORFENS_LAND;

	/**
	 * ID: 1000084<br>
	 * Message: 仮にこのように虚しく消えたとしても、お前たちは私がかけた呪いによって一生苦しめられるだろう．．．ククク。
	 */
	public static final NpcStringId EVEN_IF_YOU_DISAPPEAR_INTO_NOTHINGNESS_YOU_WILL_STILL_FACE_THE_LIFE_LONG_SUFFERING_OF_THE_CURSE_THAT_I_HAVE_GIVEN_YOU;

	/**
	 * ID: 1000085<br>
	 * Message: 神聖な妖精たちの住みかを荒らす者よ、この私が相手になってやろう！
	 */
	public static final NpcStringId ILL_STAND_AGAINST_ANYONE_THAT_MAKES_LIGHT_OF_THE_SACRED_PLACE_OF_THE_ELVES;

	/**
	 * ID: 1000086<br>
	 * Message: 我々の住みかを汚す者よ、我の手で葬ってやろう！
	 */
	public static final NpcStringId I_WILL_KILL_WITH_MY_OWN_HANDS_ANYONE_THAT_DEFILES_OUR_HOME;

	/**
	 * ID: 1000087<br>
	 * Message: 汝とその手下をこの谷から追い出すまで、私の兄弟たちは決して休むことはないだろう！ 
	 */
	public static final NpcStringId MY_BROTHERS_WILL_NEVER_REST_UNTIL_WE_PUSH_YOU_AND_YOUR_GANG_OUT_OF_THIS_VALLEY;

	/**
	 * ID: 1000088<br>
	 * Message: ヘストゥイ滅亡のその日まで！
	 */
	public static final NpcStringId UNTIL_THE_DAY_OF_DESTRUCTION_OF_HESTUI;

	/**
	 * ID: 1000089<br>
	 * Message: 勇敢なるオークはまだ残っているか！最後の一兵まで戦え！
	 */
	public static final NpcStringId IF_ANY_INTREPID_ORCS_REMAIN_ATTACK_THEM;

	/**
	 * ID: 1000090<br>
	 * Message: 息の根を止めてやる！
	 */
	public static final NpcStringId ILL_BREAK_YOUR_WINDPIPE;

	/**
	 * ID: 1000091<br>
	 * Message: 結局、復讐は失敗した ．．．
	 */
	public static final NpcStringId IS_REVENGE_A_FAILURE;

	/**
	 * ID: 1000092<br>
	 * Message: ドワーフのピカピカ輝くミスリル、きれいな宝石！すべて手にいれるのだ！
	 */
	public static final NpcStringId THE_SPARKLING_MITHRIL_OF_THE_DWARVES_AND_THEIR_PRETTY_TREASURES_ILL_GET_THEM_ALL;

	/**
	 * ID: 1000093<br>
	 * Message: 臆病なチビたち、輝く石は、どこにある．．．
	 */
	public static final NpcStringId WHERE_ARE_ALL_THE_DREADFUL_DWARVES_AND_THEIR_SPARKLING_THINGS;

	/**
	 * ID: 1000094<br>
	 * Message: すべて出せ！きれいな宝石！
	 */
	public static final NpcStringId HAND_OVER_YOUR_PRETTY_TREASURES;

	/**
	 * ID: 1000095<br>
	 * Message: クッ！あの時逃げていれば．．．
	 */
	public static final NpcStringId HEY_YOU_SHOULD_HAVE_RUN_AWAY;

	/**
	 * ID: 1000096<br>
	 * Message: 破壊、消滅、殺戮、滅亡！破壊、消滅、殺戮、滅亡！
	 */
	public static final NpcStringId DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE_DESTRUCTION_EXTINCTION_SLAUGHTER_COLLAPSE;

	/**
	 * ID: 1000097<br>
	 * Message: 破壊！破壊！破壊！破壊！
	 */
	public static final NpcStringId DESTRUCTION_DESTRUCTION_DESTRUCTION_DESTRUCTION;

	/**
	 * ID: 1000098<br>
	 * Message: 破壊！破壊！破．．．
	 */
	public static final NpcStringId DESTRUCTION_DESTRUCTION_DESTRUCTION;

	/**
	 * ID: 1000099<br>
	 * Message: ジャジャーン！帰って来たウザンカ！
	 */
	public static final NpcStringId TA_DA_UTHANKA_HAS_RETURNED;

	/**
	 * ID: 1000100<br>
	 * Message: ワハハハ！この島は今日付けでウザンカ様が頂くのだ！
	 */
	public static final NpcStringId WAH_HA_HA_HA_UTHANKA_HAS_TAKEN_OVER_THIS_ISLAND_TODAY;

	/**
	 * ID: 1000101<br>
	 * Message: おーっと！おぬし、なかなかやるな
	 */
	public static final NpcStringId WHEW_HES_QUITE_A_GUY;

	/**
	 * ID: 1000102<br>
	 * Message: く、悔しい．．．こうもあっさりと負けるとは．．．
	 */
	public static final NpcStringId HOW_EXASPERATING_AND_UNFAIR_TO_HAVE_THINGS_HAPPEN_IN_SUCH_A_MEANINGLESS_WAY_LIKE_THIS;

	/**
	 * ID: 1000103<br>
	 * Message: この世の中は恐怖と悲しみで満たされるべきだ．．．
	 */
	public static final NpcStringId THIS_WORLD_SHOULD_BE_FILLED_WITH_FEAR_AND_SADNESS;

	/**
	 * ID: 1000104<br>
	 * Message: 私に呪いをかけたこの世の中．．．決して許さんぞ！
	 */
	public static final NpcStringId I_WONT_FORGIVE_THE_WORLD_THAT_CURSED_ME;

	/**
	 * ID: 1000105<br>
	 * Message: 皆に私と同じ苦痛を味あわせてやる！
	 */
	public static final NpcStringId ILL_MAKE_EVERYONE_FEEL_THE_SAME_SUFFERING_AS_ME;

	/**
	 * ID: 1000106<br>
	 * Message: お前にも永遠に解けることのない呪いをかけてやろう！
	 */
	public static final NpcStringId ILL_GIVE_YOU_A_CURSE_THAT_YOULL_NEVER_BE_ABLE_TO_REMOVE_FOREVER;

	/**
	 * ID: 1000107<br>
	 * Message: 同胞を殺すやつらに復讐を！
	 */
	public static final NpcStringId ILL_GET_REVENGE_ON_YOU_WHO_SLAUGHTERED_MY_COMPATRIOTS;

	/**
	 * ID: 1000108<br>
	 * Message: 臆病なやつは消えうせろ！勇敢なやつはかかって来い！
	 */
	public static final NpcStringId THOSE_WHO_ARE_AFRAID_SHOULD_GET_AWAY_AND_THOSE_WHO_ARE_BRAVE_SHOULD_FIGHT;

	/**
	 * ID: 1000109<br>
	 * Message: ベレス様から力を与えられた私が簡単にやられるとでも思っているのか！
	 */
	public static final NpcStringId IVE_GOT_POWER_FROM_BELETH_SO_DO_YOU_THINK_ILL_BE_EASILY_DEFEATED;

	/**
	 * ID: 1000110<br>
	 * Message: 私はこうやって消えていくが．．．もう一人の私がお前たちを倒しに来るであろう！
	 */
	public static final NpcStringId I_AM_LEAVING_NOW_BUT_SOON_SOMEONE_WILL_COME_WHO_WILL_TEACH_YOU_ALL_A_LESSON;

	/**
	 * ID: 1000111<br>
	 * Message: お前ら、俺様の縄張を見回りして来い！
	 */
	public static final NpcStringId HEY_GUYS_LETS_MAKE_A_ROUND_OF_OUR_TERRITORY;

	/**
	 * ID: 1000112<br>
	 * Message: 近頃、俺様の縄張で好き勝手なことをやってるやつがいるそうだ．．．
	 */
	public static final NpcStringId THE_RUMOR_IS_THAT_THERE_ARE_WILD_UNCIVILIZED_RUFFIANS_WHO_HAVE_RECENTLY_ARRIVED_IN_MY_TERRITORY;

	/**
	 * ID: 1000113<br>
	 * Message: 俺様が誰だかわかるか！俺がまさしくシロッコ様だ！お前ら、やっちまえ！
	 */
	public static final NpcStringId DO_YOU_KNOW_WHO_I_AM_I_AM_SIROCCO_EVERYONE_ATTACK;

	/**
	 * ID: 1000114<br>
	 * Message: こんなはずでは．．．不敗のシロッコがお前ごときに負けるとは！
	 */
	public static final NpcStringId WHATS_JUST_HAPPENED_THE_INVINCIBLE_SIROCCO_WAS_DEFEATED_BY_SOMEONE_LIKE_YOU;

	/**
	 * ID: 1000115<br>
	 * Message: 腹へったー、キュルル～
	 */
	public static final NpcStringId OH_IM_REALLY_HUNGRY;

	/**
	 * ID: 1000116<br>
	 * Message: 餌の臭いがするぞ、キュルル～
	 */
	public static final NpcStringId I_SMELL_FOOD_OOH;

	/**
	 * ID: 1000117<br>
	 * Message: キュルル～
	 */
	public static final NpcStringId OOH;

	/**
	 * ID: 1000118<br>
	 * Message: ここの蜂蜜はどんな味かな？
	 */
	public static final NpcStringId WHAT_DOES_HONEY_OF_THIS_PLACE_TASTE_LIKE;

	/**
	 * ID: 1000119<br>
	 * Message: うっとりするほど甘いと言われている、黄金の蜂蜜を出せ！
	 */
	public static final NpcStringId GIVE_ME_SOME_SWEET_DELICIOUS_GOLDEN_HONEY;

	/**
	 * ID: 1000120<br>
	 * Message: 蜂蜜を差し出せば、命だけは助けてやる！
	 */
	public static final NpcStringId IF_YOU_GIVE_ME_SOME_HONEY_ILL_AT_LEAST_SPARE_YOUR_LIFE;

	/**
	 * ID: 1000121<br>
	 * Message: 蜂蜜さえ見つかっていれば．．．.お前なんかに負けなかったはずだ。
	 */
	public static final NpcStringId ONLY_FOR_LACK_OF_HONEY_DID_I_LOSE_TO_THE_LIKES_OF_YOU;

	/**
	 * ID: 1000122<br>
	 * Message: 裏切り者のクロボロスはどこだ！
	 */
	public static final NpcStringId WHERE_IS_THE_TRAITOR_KUROBOROS;

	/**
	 * ID: 1000123<br>
	 * Message: 周りをくまなく探せ！
	 */
	public static final NpcStringId LOOK_IN_EVERY_NOOK_AND_CRANNY_AROUND_HERE;

	/**
	 * ID: 1000124<br>
	 * Message: クロボロスの手下か。一刀両断にしてやる！
	 */
	public static final NpcStringId ARE_YOU_LACKEY_OF_KUROBOROS_ILL_KNOCK_YOU_OUT_IN_ONE_SHOT;

	/**
	 * ID: 1000125<br>
	 * Message: 裏切り者を倒せぬまま死ぬとは．．．くやしい！
	 */
	public static final NpcStringId HE_JUST_CLOSED_HIS_EYES_WITHOUT_DISPOSING_OF_THE_TRAITOR_HOW_UNFAIR;

	/**
	 * ID: 1000126<br>
	 * Message: クロボロス様を信じぬ者は地獄に落ちるがいい！
	 */
	public static final NpcStringId HELL_FOR_UNBELIEVERS_IN_KUROBOROS;

	/**
	 * ID: 1000127<br>
	 * Message: このクロボロスを信じない者。この世がすなわち地獄なのだ！
	 */
	public static final NpcStringId THE_PERSON_THAT_DOES_NOT_BELIEVE_IN_KUROBOROS_HIS_LIFE_WILL_SOON_BECOME_HELL;

	/**
	 * ID: 1000128<br>
	 * Message: 偽りの神に仕える悪魔の手下め！この手で成敗してやろう！
	 */
	public static final NpcStringId THE_LACKEY_OF_THAT_DEMENTED_DEVIL_THE_SERVANT_OF_A_FALSE_GOD_ILL_SEND_THAT_FOOL_STRAIGHT_TO_HELL;

	/**
	 * ID: 1000129<br>
	 * Message: う．．．我、死ぬにあらず。しばし消えるのみ．．．再び復活せん！
	 */
	public static final NpcStringId UH_IM_NOT_DYING_IM_JUST_DISAPPEARING_FOR_A_MOMENT_ILL_RESURRECT_AGAIN;

	/**
	 * ID: 1000130<br>
	 * Message: クロボロス教主様、万歳！
	 */
	public static final NpcStringId HAIL_TO_KUROBOROS_THE_FOUNDER_OF_OUR_RELIGION;

	/**
	 * ID: 1000131<br>
	 * Message: クロボロス様を信じる者だけが救われるのだ！
	 */
	public static final NpcStringId ONLY_THOSE_WHO_BELIEVE_IN_PATRIARCH_KUROBOROS_SHALL_RECEIVE_SALVATION;

	/**
	 * ID: 1000132<br>
	 * Message: シャルクがそそのかしたやつだな！お前たちもクロボロス様を信じて救われるがよい！
	 */
	public static final NpcStringId ARE_YOU_THE_ONES_THAT_SHARUK_HAS_INCITED_YOU_ALSO_SHOULD_TRUST_IN_KUROBOROS_AND_BE_SAVED;

	/**
	 * ID: 1000133<br>
	 * Message: クロボロス様がお前たちを懲らしめるだろう。
	 */
	public static final NpcStringId KUROBOROS_WILL_PUNISH_YOU;

	/**
	 * ID: 1000134<br>
	 * Message: 澄んだ輝きを放つ美しい霊魂を持つ者たちよ！私は帰って来たぞ！
	 */
	public static final NpcStringId YOU_WHO_HAVE_BEAUTIFUL_SPIRITS_THAT_SHINE_BRIGHTLY_I_HAVE_RETURNED;

	/**
	 * ID: 1000135<br>
	 * Message: 疲労困憊した者たちよ．．．霊魂を私に授けなさい。
	 */
	public static final NpcStringId YOU_THAT_ARE_WEARY_AND_EXHAUSTED_ENTRUST_YOUR_SOULS_TO_ME;

	/**
	 * ID: 1000136<br>
	 * Message: お前の霊魂の色はうっとりするほど美しい。
	 */
	public static final NpcStringId THE_COLOR_OF_YOUR_SOUL_IS_VERY_ATTRACTIVE;

	/**
	 * ID: 1000137<br>
	 * Message: 生きとし生ける者よ！お前たちの霊魂がどんなに美しいか知っているか！
	 */
	public static final NpcStringId THOSE_OF_YOU_WHO_LIVE_DO_YOU_KNOW_HOW_BEAUTIFUL_YOUR_SOULS_ARE;

	/**
	 * ID: 1000138<br>
	 * Message: み、皆殺しにしてやる．．．
	 */
	public static final NpcStringId IT_WILL_KILL_EVERYONE;

	/**
	 * ID: 1000139<br>
	 * Message: こ、孤独だ．．．
	 */
	public static final NpcStringId IM_SO_LONELY;

	/**
	 * ID: 1000140<br>
	 * Message: わ、私のかたき．．．
	 */
	public static final NpcStringId MY_ENEMY;

	/**
	 * ID: 1000141<br>
	 * Message: もう、孤独ではない！
	 */
	public static final NpcStringId _NOW_IM_NOT_SO_LONELY;

	/**
	 * ID: 1000142<br>
	 * Message: 我々を．．．滅ぼさんとする．．．ピクシーミュリカ！決して許すまい！
	 */
	public static final NpcStringId I_WILL_NEVER_FORGIVE_THE_PIXY_MURIKA_THAT_IS_TRYING_TO_KILL_US;

	/**
	 * ID: 1000143<br>
	 * Message: ミュリカの優れた手下たちよ、かかってこい！
	 */
	public static final NpcStringId ATTACK_ALL_THE_DULL_AND_STUPID_FOLLOWERS_OF_MURIKA;

	/**
	 * ID: 1000144<br>
	 * Message: その程度の覇気では太刀打ちできないぞ！
	 */
	public static final NpcStringId I_DIDNT_HAVE_ANY_IDEA_ABOUT_SUCH_AMBITIONS;

	/**
	 * ID: 1000145<br>
	 * Message: これは終わりではなく．．．始まりに過ぎない。
	 */
	public static final NpcStringId THIS_IS_NOT_THE_END_ITS_JUST_THE_BEGINNING;

	/**
	 * ID: 1000146<br>
	 * Message: ふっ．．．久しぶりの外出だ。何か楽しいことはないかな．．．
	 */
	public static final NpcStringId HEY_SHALL_WE_HAVE_SOME_FUN_FOR_THE_FIRST_TIME_IN_A_LONG_WHILE;

	/**
	 * ID: 1000147<br>
	 * Message: この辺りをのさばり歩いている輩がいるらしいが．．．
	 */
	public static final NpcStringId THEREVE_BEEN_SOME_THINGS_GOING_AROUND_LIKE_CRAZY_HERE_RECENTLY;

	/**
	 * ID: 1000148<br>
	 * Message: こいつ、私を誰だと思っている！私があのマレックスだ！皆の者、かかれ！
	 */
	public static final NpcStringId HEY_DO_YOU_KNOW_WHO_I_AM_I_AM_MALEX_HERALD_OF_DAGONIEL_ATTACK;

	/**
	 * ID: 1000149<br>
	 * Message: こんなはずでは．．．この不敗のマレックスがお前なんぞに負けるとは．．．
	 */
	public static final NpcStringId WHATS_JUST_HAPPENED_THE_INVINCIBLE_MALEX_JUST_LOST_TO_THE_LIKES_OF_YOU;

	/**
	 * ID: 1000150<br>
	 * Message: 虚しい生を繰り返すだけだ．．．
	 */
	public static final NpcStringId ITS_SOMETHING_REPEATED_IN_A_VAIN_LIFE;

	/**
	 * ID: 1000151<br>
	 * Message: 命ある者はみな恐怖に慄け！
	 */
	public static final NpcStringId SHAKE_IN_FEAR_ALL_YOU_WHO_VALUE_YOUR_LIVES;

	/**
	 * ID: 1000152<br>
	 * Message: 消えることのない炎に纏われるような苦しみを味あわせてやる！
	 */
	public static final NpcStringId ILL_MAKE_YOU_FEEL_SUFFERING_LIKE_A_FLAME_THAT_IS_NEVER_EXTINGUISHED;

	/**
	 * ID: 1000153<br>
	 * Message: 再び土の中に．．．
	 */
	public static final NpcStringId BACK_TO_THE_DIRT;

	/**
	 * ID: 1000154<br>
	 * Message: ヴァリカ様！万歳！
	 */
	public static final NpcStringId HAIL_VARIKA;

	/**
	 * ID: 1000155<br>
	 * Message: 誰も我々を止めることはできない！
	 */
	public static final NpcStringId NOBODY_CAN_STOP_US;

	/**
	 * ID: 1000156<br>
	 * Message: 動きが鈍いぞ！
	 */
	public static final NpcStringId YOU_MOVE_SLOWLY;

	/**
	 * ID: 1000157<br>
	 * Message: ヴァリカ様！先に逝きます！
	 */
	public static final NpcStringId VARIKA_GO_FIRST;

	/**
	 * ID: 1000158<br>
	 * Message: ここはどこだ？私は誰だ？
	 */
	public static final NpcStringId WHERE_AM_I_WHO_AM_I;

	/**
	 * ID: 1000159<br>
	 * Message: う．．．頭が割れるように痛い！私は誰なんだ？
	 */
	public static final NpcStringId UH_MY_HEAD_HURTS_LIKE_ITS_GOING_TO_BURST_WHO_AM_I;

	/**
	 * ID: 1000160<br>
	 * Message: 貴様。貴様があの悪魔だな！私をこんな目に合わせた悪魔だな！
	 */
	public static final NpcStringId YOU_JERK_YOURE_A_DEVIL_YOURE_A_DEVIL_TO_HAVE_MADE_ME_LIKE_THIS;

	/**
	 * ID: 1000161<br>
	 * Message: う．．．やっと正気になったか．．．ありがとう。やっとゆっくり休めそうだ。
	 */
	public static final NpcStringId WHERE_AM_I_WHAT_HAPPENED_THANK_YOU;

	/**
	 * ID: 1000162<br>
	 * Message: ウクルマスタ！
	 */
	public static final NpcStringId UKRU_MASTER;

	/**
	 * ID: 1000163<br>
	 * Message: マトゥナ？
	 */
	public static final NpcStringId ARE_YOU_MATU;

	/**
	 * ID: 1000164<br>
	 * Message: マラッグ！トゥバリン！サバラチャ！
	 */
	public static final NpcStringId MARAK_TUBARIN_SABARACHA;

	/**
	 * ID: 1000165<br>
	 * Message: パグリオタマ！
	 */
	public static final NpcStringId PAAGRIO_TAMA;

	/**
	 * ID: 1000166<br>
	 * Message: イカルスの意志を、受け継ぐべし！
	 */
	public static final NpcStringId ACCEPT_THE_WILL_OF_ICARUS;

	/**
	 * ID: 1000167<br>
	 * Message: 邪魔立てする者は許さんぞ！
	 */
	public static final NpcStringId THE_PEOPLE_WHO_ARE_BLOCKING_MY_WAY_WILL_NOT_BE_FORGIVEN;

	/**
	 * ID: 1000168<br>
	 * Message: 浅はかなやろうども．．．
	 */
	public static final NpcStringId YOU_ARE_SCUM;

	/**
	 * ID: 1000169<br>
	 * Message: 動力がまもなく足りなくなります。
	 */
	public static final NpcStringId YOU_LACK_POWER;

	/**
	 * ID: 1000170<br>
	 * Message: 戻る
	 */
	public static final NpcStringId RETURN;

	/**
	 * ID: 1000171<br>
	 * Message: アデナが繰越されました。
	 */
	public static final NpcStringId ADENA_HAS_BEEN_TRANSFERRED;

	/**
	 * ID: 1000172<br>
	 * Message: 回：
	 */
	public static final NpcStringId EVENT_NUMBER;

	/**
	 * ID: 1000173<br>
	 * Message: 1等
	 */
	public static final NpcStringId FIRST_PRIZE;

	/**
	 * ID: 1000174<br>
	 * Message: 2等
	 */
	public static final NpcStringId SECOND_PRIZE;

	/**
	 * ID: 1000175<br>
	 * Message: 3等
	 */
	public static final NpcStringId THIRD_PRIZE;

	/**
	 * ID: 1000176<br>
	 * Message: 4等
	 */
	public static final NpcStringId FOURTH_PRIZE;

	/**
	 * ID: 1000177<br>
	 * Message: 当選した宝くじがありません。
	 */
	public static final NpcStringId THERE_HAS_BEEN_NO_WINNING_LOTTERY_TICKET;

	/**
	 * ID: 1000178<br>
	 * Message: 今回の当選番号：
	 */
	public static final NpcStringId THE_MOST_RECENT_WINNING_LOTTERY_NUMBERS;

	/**
	 * ID: 1000179<br>
	 * Message: 上の番号で選択
	 */
	public static final NpcStringId YOUR_LUCKY_NUMBERS_HAVE_BEEN_SELECTED_ABOVE;

	/**
	 * ID: 1000180<br>
	 * Message: ここに近づこうとする者は誰だ！
	 */
	public static final NpcStringId I_WONDER_WHO_IT_IS_THAT_IS_LURKING_ABOUT;

	/**
	 * ID: 1000181<br>
	 * Message: ここは神聖な魔法を研究する所である。
	 */
	public static final NpcStringId SACRED_MAGICAL_RESEARCH_IS_CONDUCTED_HERE;

	/**
	 * ID: 1000182<br>
	 * Message: 偉大な魔法の力を見せてやろう！
	 */
	public static final NpcStringId BEHOLD_THE_AWESOME_POWER_OF_MAGIC;

	/**
	 * ID: 1000183<br>
	 * Message: なかなか大したものだな。だが、高位メイジには手を出さないほうがいいだろう。
	 */
	public static final NpcStringId YOUR_POWERS_ARE_IMPRESSIVE_BUT_YOU_MUST_NOT_ANNOY_OUR_HIGH_LEVEL_SORCERER;

	/**
	 * ID: 1000184<br>
	 * Message: 砦の主人のバルダ様だ。ウハハハ！
	 */
	public static final NpcStringId I_AM_BARDA_MASTER_OF_THE_BANDIT_STRONGHOLD;

	/**
	 * ID: 1000185<br>
	 * Message: あの砦は元々このバルダ様のものなのだ。
	 */
	public static final NpcStringId I_MASTER_BARDA_ONCE_OWNED_THAT_STRONGHOLD;

	/**
	 * ID: 1000186<br>
	 * Message: へえ、おもしろそうだな。
	 */
	public static final NpcStringId AH_VERY_INTERESTING;

	/**
	 * ID: 1000187<br>
	 * Message: 思ったよりやりそうだな。ここは一旦引いたほうがよさそうだ。
	 */
	public static final NpcStringId YOU_ARE_MORE_POWERFUL_THAN_YOU_APPEAR_WELL_MEET_AGAIN;

	/**
	 * ID: 1000188<br>
	 * Message: バカなメイジどもめ！
	 */
	public static final NpcStringId YOU_FILTHY_SORCERERS_DISGUST_ME;

	/**
	 * ID: 1000189<br>
	 * Message: 生意気にも我らの地に侵入するとはな！ただではおかないぞ！
	 */
	public static final NpcStringId WHY_WOULD_YOU_BUILD_A_TOWER_IN_OUR_TERRITORY;

	/**
	 * ID: 1000190<br>
	 * Message: お前らも邪悪なメイジとグルなのか。
	 */
	public static final NpcStringId ARE_YOU_PART_OF_THAT_EVIL_GANG_OF_SORCERERS;

	/**
	 * ID: 1000191<br>
	 * Message: これだからメイジの子分なんかと戦いたくなかったんだ。
	 */
	public static final NpcStringId THAT_IS_WHY_I_DONT_BOTHER_WITH_ANYONE_BELOW_THE_LEVEL_OF_SORCERER;

	/**
	 * ID: 1000192<br>
	 * Message: 今日も楽しい一日が始まるな．．．
	 */
	public static final NpcStringId AH_ANOTHER_BEAUTIFUL_DAY;

	/**
	 * ID: 1000193<br>
	 * Message: 放火と略奪は俺達の専門さ。
	 */
	public static final NpcStringId OUR_SPECIALTIES_ARE_ARSON_AND_LOOTING;

	/**
	 * ID: 1000194<br>
	 * Message: お前の有り金も残らず頂いてやろう！
	 */
	public static final NpcStringId YOU_WILL_LEAVE_EMPTY_HANDED;

	/**
	 * ID: 1000195<br>
	 * Message: 私が集めた宝物がそんなに欲しいのか。じゃ、探してみることだ！ハハハ！
	 */
	public static final NpcStringId AH_SO_YOU_ADMIRE_MY_TREASURE_DO_YOU_TRY_FINDING_IT_HA;

	/**
	 * ID: 1000196<br>
	 * Message: 皆の者、聞いておるのか。シリオン様のお戻りだ。皆の者、詠い敬拝せよ．．．
	 */
	public static final NpcStringId IS_EVERYBODY_LISTENING_SIRION_HAS_COME_BACK_EVERYONE_CHANT_AND_BOW;

	/**
	 * ID: 1000197<br>
	 * Message: 取るに足りん者どもよ！頭が高い！控えおろう！
	 */
	public static final NpcStringId BOW_DOWN_YOU_WORTHLESS_HUMANS;

	/**
	 * ID: 1000198<br>
	 * Message: 取るに足りんつまらん者．．．
	 */
	public static final NpcStringId VERY_TACKY;

	/**
	 * ID: 1000199<br>
	 * Message: ック．．．私を打ち倒したと安ずるのはまだ．．．
	 */
	public static final NpcStringId DONT_THINK_THAT_YOU_ARE_INVINCIBLE_JUST_BECAUSE_YOU_DEFEATED_ME;

	/**
	 * ID: 1000200<br>
	 * Message: この役立たずが．．．生者の欲望．．．
	 */
	public static final NpcStringId THE_MATERIAL_DESIRES_OF_MORTALS_ARE_ULTIMATELY_MEANINGLESS;

	/**
	 * ID: 1000201<br>
	 * Message: 傲慢の塔が崩壊したワケを考えることだ．．．
	 */
	public static final NpcStringId DO_NOT_FORGET_THE_REASON_THE_TOWER_OF_INSOLENCE_COLLAPSED;

	/**
	 * ID: 1000202<br>
	 * Message: 欲深い者ども。欲深い貴族ども．．．
	 */
	public static final NpcStringId YOU_HUMANS_ARE_ALL_ALIKE_FULL_OF_GREED_AND_AVARICE;

	/**
	 * ID: 1000203<br>
	 * Message: 虚しい。すべてのことが虚しいのだ．．．
	 */
	public static final NpcStringId ALL_FOR_NOTHING;

	/**
	 * ID: 1000204<br>
	 * Message: なぜだ、なぜにこんなに人が多いのだ？
	 */
	public static final NpcStringId WHAT_ARE_ALL_THESE_PEOPLE_DOING_HERE;

	/**
	 * ID: 1000205<br>
	 * Message: どこかに永遠の命の秘密があるはずだ。くそっ！天使どもめ！
	 */
	public static final NpcStringId I_MUST_FIND_THE_SECRET_OF_ETERNAL_LIFE_HERE_AMONG_THESE_ROTTEN_ANGELS;

	/**
	 * ID: 1000206<br>
	 * Message: お前たちも永遠の命の秘密を探してるのか。
	 */
	public static final NpcStringId DO_YOU_ALSO_SEEK_THE_SECRET_OF_IMMORTALITY;

	/**
	 * ID: 1000207<br>
	 * Message: 私が知ってるとしても決して話してやらん。
	 */
	public static final NpcStringId I_SHALL_NEVER_REVEAL_MY_SECRETS;

	/**
	 * ID: 1000208<br>
	 * Message: 生意気にも誰かがここに入って来たというのか。
	 */
	public static final NpcStringId WHO_DARES_ENTER_THIS_PLACE;

	/**
	 * ID: 1000209<br>
	 * Message: ここはお前らのようなやつが入れるところではない。今すぐ出て行け！
	 */
	public static final NpcStringId THIS_IS_NO_PLACE_FOR_HUMANS_YOU_MUST_LEAVE_IMMEDIATELY;

	/**
	 * ID: 1000210<br>
	 * Message: 愚かなる者どもよ。お前たちの愚鈍さがわからぬのか。
	 */
	public static final NpcStringId YOU_POOR_CREATURES_TOO_STUPID_TO_REALIZE_YOUR_OWN_IGNORANCE;

	/**
	 * ID: 1000211<br>
	 * Message: よせ。あの上に行ってはならぬ。
	 */
	public static final NpcStringId YOU_MUSTNT_GO_THERE;

	/**
	 * ID: 1000212<br>
	 * Message: この沼を汚す者は誰だ。
	 */
	public static final NpcStringId WHO_DARES_DISTURB_THIS_MARSH;

	/**
	 * ID: 1000213<br>
	 * Message: 愚か者どもの欲望で、沼地をまた荒れさせるわけにはいかん。
	 */
	public static final NpcStringId THE_HUMANS_MUST_NOT_BE_ALLOWED_TO_DESTROY_THE_MARSHLAND_FOR_THEIR_GREEDY_PURPOSES;

	/**
	 * ID: 1000214<br>
	 * Message: 勇敢なやつだな．．．
	 */
	public static final NpcStringId YOU_ARE_A_BRAVE_MAN;

	/**
	 * ID: 1000215<br>
	 * Message: 愚か者どもよ．．．お前たちが消え去る日がいつか来るはずだ。
	 */
	public static final NpcStringId YOU_IDIOTS_SOME_DAY_YOU_SHALL_ALSO_BE_GONE;

	/**
	 * ID: 1000216<br>
	 * Message: 誰かが森に入って来た．．．
	 */
	public static final NpcStringId SOMEONE_HAS_ENTERED_THE_FOREST;

	/**
	 * ID: 1000217<br>
	 * Message: この森はとても静かで平和な場所だ。
	 */
	public static final NpcStringId THE_FOREST_IS_VERY_QUIET_AND_PEACEFUL;

	/**
	 * ID: 1000218<br>
	 * Message: 私はここだ。この森はとてもいいところだ。
	 */
	public static final NpcStringId STAY_HERE_IN_THIS_WONDERFUL_FOREST;

	/**
	 * ID: 1000219<br>
	 * Message: わ．．．私の魂たち．．．
	 */
	public static final NpcStringId MY_MY_SOULS;

	/**
	 * ID: 1000220<br>
	 * Message: この森は危険だ。
	 */
	public static final NpcStringId THIS_FOREST_IS_A_DANGEROUS_PLACE;

	/**
	 * ID: 1000221<br>
	 * Message: 今すぐこの森から出て行け！さもなくば、大きな災いが降りかかるだろう。
	 */
	public static final NpcStringId UNLESS_YOU_LEAVE_THIS_FOREST_IMMEDIATELY_YOU_ARE_BOUND_TO_RUN_INTO_SERIOUS_TROUBLE;

	/**
	 * ID: 1000222<br>
	 * Message: 今すぐここから出て行け！
	 */
	public static final NpcStringId LEAVE_NOW;

	/**
	 * ID: 1000223<br>
	 * Message: どうして、私の警告を無視するのだ？
	 */
	public static final NpcStringId WHY_DO_YOU_IGNORE_MY_WARNING;

	/**
	 * ID: 1000224<br>
	 * Message: この世のすべてのハリツたちよ．．．お前たちを安らかにしてやろう．．．
	 */
	public static final NpcStringId HARITS_OF_THE_WORLD_I_BRING_YOU_PEACE;

	/**
	 * ID: 1000225<br>
	 * Message: すべてのハリツたちよ！勇気を出せ！
	 */
	public static final NpcStringId HARITS_BE_COURAGEOUS;

	/**
	 * ID: 1000226<br>
	 * Message: お前の赤い心臓を喰ってやる！
	 */
	public static final NpcStringId I_SHALL_EAT_YOUR_STILL_BEATING_HEART;

	/**
	 * ID: 1000227<br>
	 * Message: 私のハリツたちよ．．．再び戻って来る日まで．．．希望を捨て．．．るな．．．
	 */
	public static final NpcStringId HARITS_KEEP_FAITH_UNTIL_THE_DAY_I_RETURN_NEVER_LOSE_HOPE;

	/**
	 * ID: 1000228<br>
	 * Message: 巨人が消えたこの世界！恐れるものはない！
	 */
	public static final NpcStringId EVEN_THE_GIANTS_ARE_GONE_THERES_NOTHING_LEFT_TO_BE_AFRAID_OF_NOW;

	/**
	 * ID: 1000229<br>
	 * Message: 巨人について知っておるな？やつらの滅亡は当然過ぎる結果なのだ！
	 */
	public static final NpcStringId HAVE_YOU_HEARD_OF_THE_GIANTS_THEIR_DOWNFALL_WAS_INEVITABLE;

	/**
	 * ID: 1000230<br>
	 * Message: こいつ！私と戦うのか！
	 */
	public static final NpcStringId WHAT_NERVE_DO_YOU_DARE_TO_CHALLENGE_ME;

	/**
	 * ID: 1000231<br>
	 * Message: お前もまた巨人と同じく邪悪なやつ．．．だな．．．
	 */
	public static final NpcStringId YOU_ARE_AS_EVIL_AS_THE_GIANTS;

	/**
	 * ID: 1000232<br>
	 * Message: このダンジョンはまだ健在だ。
	 */
	public static final NpcStringId THIS_DUNGEON_IS_STILL_IN_GOOD_CONDITION;

	/**
	 * ID: 1000233<br>
	 * Message: 実に雄壮だな。
	 */
	public static final NpcStringId THIS_PLACE_IS_SPECTACULAR_WOULDNT_YOU_SAY;

	/**
	 * ID: 1000234<br>
	 * Message: お前たちは勇敢な戦士だ！
	 */
	public static final NpcStringId YOU_ARE_VERY_BRAVE_WARRIORS;

	/**
	 * ID: 1000235<br>
	 * Message: やはりもう巨人の時代は来ないのか。
	 */
	public static final NpcStringId ARE_THE_GIANTS_TRULY_GONE_FOR_GOOD;

	/**
	 * ID: 1000236<br>
	 * Message: いい墓だ。
	 */
	public static final NpcStringId THESE_GRAVES_ARE_GOOD;

	/**
	 * ID: 1000237<br>
	 * Message: 亡者に金銀財宝など必要はない。
	 */
	public static final NpcStringId GOLD_AND_SILVER_ARE_MEANINGLESS_TO_A_DEAD_MAN;

	/**
	 * ID: 1000238<br>
	 * Message: 間抜な貴族どもめ！なぜ使い道のあるものを埋めるのだ。
	 */
	public static final NpcStringId WHY_WOULD_THOSE_CORRUPT_ARISTOCRATS_BURY_SUCH_USEFUL_THINGS;

	/**
	 * ID: 1000239<br>
	 * Message: せこいやつだ。自分だけいい暮らしをするだなんて。
	 */
	public static final NpcStringId YOU_FILTHY_PIG_EAT_AND_BE_MERRY_NOW_THAT_YOU_HAVE_SHIRKED_YOUR_RESPONSIBILITIES;

	/**
	 * ID: 1000240<br>
	 * Message: ズタズタに切り裂いて、ムシャムシャ喰い散らすだけでは気がおさまらんのだ！アデンのやつらめ、許さんぞ！
	 */
	public static final NpcStringId THOSE_THUGS_IT_WOULD_BE_TOO_MERCIFUL_TO_RIP_THEM_APART_AND_CHEW_THEM_UP_ONE_AT_A_TIME;

	/**
	 * ID: 1000241<br>
	 * Message: 呪われろ、アデンのやつらめ！
	 */
	public static final NpcStringId YOU_ACCURSED_SCOUNDRELS;

	/**
	 * ID: 1000242<br>
	 * Message: フッ．．．アデンのアホどもが送った暗殺者か。
	 */
	public static final NpcStringId HMM_COULD_THIS_BE_THE_ASSASSIN_SENT_BY_THOSE_IDIOTS_FROM_ADEN;

	/**
	 * ID: 1000243<br>
	 * Message: 私の憎悪が消えない限り．．．永遠にお前たちを呪ってやろう！
	 */
	public static final NpcStringId I_SHALL_CURSE_YOUR_NAME_WITH_MY_LAST_BREATH;

	/**
	 * ID: 1000244<br>
	 * Message: 敬愛するシーレン様．．．
	 */
	public static final NpcStringId MY_BELOVED_LORD_SHILEN;

	/**
	 * ID: 1000245<br>
	 * Message: シーレン様の封印を早く解いて差し上げなければならないのに．．．
	 */
	public static final NpcStringId I_MUST_BREAK_THE_SEAL_AND_RELEASE_LORD_SHILEN_AS_SOON_AS_POSSIBLE;

	/**
	 * ID: 1000246<br>
	 * Message: シーレン様の名のもとに、お前たちを処罰する！
	 */
	public static final NpcStringId YOU_SHALL_TASTE_THE_VENGEANCE_OF_LORD_SHILEN;

	/**
	 * ID: 1000247<br>
	 * Message: シーレン様．．．この職務を．．．いつかは．．．完遂し．．．
	 */
	public static final NpcStringId LORD_SHILEN_SOME_DAY_YOU_WILL_ACCOMPLISH_THIS_MISSION;

	/**
	 * ID: 1000248<br>
	 * Message: 不滅に向かって．．．
	 */
	public static final NpcStringId TOWARDS_IMMORTALITY;

	/**
	 * ID: 1000249<br>
	 * Message: 永遠の命が欲しい者．．．私のところに来い。
	 */
	public static final NpcStringId HE_WHO_DESIRES_IMMORTALITY_COME_UNTO_ME;

	/**
	 * ID: 1000250<br>
	 * Message: お前なんぞは私の不老不死のエネルギーにしてやる！
	 */
	public static final NpcStringId YOU_SHALL_BE_SACRIFICED_TO_GAIN_MY_IMMORTALITY;

	/**
	 * ID: 1000251<br>
	 * Message: もう少しで永遠の命が手に入るところだったのに．．．こんなふうに儚く死ぬなんて．．．
	 */
	public static final NpcStringId ETERNAL_LIFE_IN_FRONT_OF_MY_EYES_I_HAVE_COLLAPSED_IN_SUCH_A_WORTHLESS_WAY_LIKE_THIS;

	/**
	 * ID: 1000252<br>
	 * Message: ザケン、なんて卑怯なやつ！
	 */
	public static final NpcStringId ZAKEN_YOU_ARE_A_COWARDLY_CUR;

	/**
	 * ID: 1000253<br>
	 * Message: ザケン、お前は永遠の命を手にしていなかったのか！
	 */
	public static final NpcStringId YOU_ARE_IMMORTAL_ARENT_YOU_ZAKEN;

	/**
	 * ID: 1000254<br>
	 * Message: 私の．．．私の身体を返してくれ．．．
	 */
	public static final NpcStringId PLEASE_RETURN_MY_BODY_TO_ME;

	/**
	 * ID: 1000255<br>
	 * Message: ああ、これでもう．．．安らかに眠れるということなのか．．．
	 */
	public static final NpcStringId FINALLY_WILL_I_BE_ABLE_TO_REST;

	/**
	 * ID: 1000256<br>
	 * Message: なぜこんなに騒々しいのですか。
	 */
	public static final NpcStringId WHAT_IS_ALL_THAT_RACKET;

	/**
	 * ID: 1000257<br>
	 * Message: キルドル様は騒がしいことがお嫌いです。
	 */
	public static final NpcStringId MASTER_GILDOR_DOES_NOT_LIKE_TO_BE_DISTURBED;

	/**
	 * ID: 1000258<br>
	 * Message: もう少し静かにしてくださったらいいのですが．．．
	 */
	public static final NpcStringId PLEASE_JUST_HOLD_IT_DOWN;

	/**
	 * ID: 1000259<br>
	 * Message: キルドル様がお怒りになっても私はもう知りません。
	 */
	public static final NpcStringId IF_YOU_DISTURB_MASTER_GILDOR_I_WONT_BE_ABLE_TO_HELP_YOU;

	/**
	 * ID: 1000260<br>
	 * Message: どこのどいつが来るというんだ？
	 */
	public static final NpcStringId WHO_DARES_APPROACH;

	/**
	 * ID: 1000261<br>
	 * Message: この草原は私の領地．．．
	 */
	public static final NpcStringId THESE_REEDS_ARE_MY_TERRITORY;

	/**
	 * ID: 1000262<br>
	 * Message: 愚か者め！目に物を見せてやる！
	 */
	public static final NpcStringId YOU_FOOLS_TODAY_YOU_SHALL_LEARN_A_LESSON;

	/**
	 * ID: 1000263<br>
	 * Message: 古きよき時代は去って．．．新しい時代が開かれるのか．．．
	 */
	public static final NpcStringId THE_PAST_GOES_BY_IS_A_NEW_ERA_BEGINNING;

	/**
	 * ID: 1000264<br>
	 * Message: ここはエヴァ様の庭園です。
	 */
	public static final NpcStringId THIS_IS_THE_GARDEN_OF_EVA;

	/**
	 * ID: 1000265<br>
	 * Message: エヴァ様の庭園は聖なる場所です。
	 */
	public static final NpcStringId THE_GARDEN_OF_EVA_IS_A_SACRED_PLACE;

	/**
	 * ID: 1000266<br>
	 * Message: エヴァ様を冒涜されるのですか。
	 */
	public static final NpcStringId DO_YOU_MEAN_TO_INSULT_EVA;

	/**
	 * ID: 1000267<br>
	 * Message: こんな無礼なあなたでも．．．エヴァ様は愛で包んでくださるでしょう．．．
	 */
	public static final NpcStringId HOW_RUDE_EVAS_LOVE_IS_AVAILABLE_TO_ALL_EVEN_TO_AN_ILL_MANNERED_LOUT_LIKE_YOURSELF;

	/**
	 * ID: 1000268<br>
	 * Message: ここは元々シーレン様のものだ。
	 */
	public static final NpcStringId THIS_PLACE_ONCE_BELONGED_TO_LORD_SHILEN;

	/**
	 * ID: 1000269<br>
	 * Message: 宮殿を引き渡すのだ。エヴァの精霊たちよ。
	 */
	public static final NpcStringId LEAVE_THIS_PALACE_TO_US_SPIRITS_OF_EVA;

	/**
	 * ID: 1000270<br>
	 * Message: なぜ我々の邪魔をするのだ。
	 */
	public static final NpcStringId WHY_ARE_YOU_GETTING_IN_OUR_WAY;

	/**
	 * ID: 1000271<br>
	 * Message: シーレン様．．．シーレン様の．．．
	 */
	public static final NpcStringId SHILEN_OUR_SHILEN;

	/**
	 * ID: 1000272<br>
	 * Message: パプリオン様が恐れる者．．．ここから出て行くのだ！
	 */
	public static final NpcStringId ALL_WHO_FEAR_OF_FAFURION_LEAVE_THIS_PLACE_AT_ONCE;

	/**
	 * ID: 1000273<br>
	 * Message: パプリオン様の名のもとに、お前を処刑する！
	 */
	public static final NpcStringId YOU_ARE_BEING_PUNISHED_IN_THE_NAME_OF_FAFURION;

	/**
	 * ID: 1000274<br>
	 * Message: ご主人様．．．愚鈍なわたくしをお許しください．．．
	 */
	public static final NpcStringId OH_MASTER_PLEASE_FORGIVE_YOUR_HUMBLE_SERVANT;

	/**
	 * ID: 1000275<br>
	 * Message: 覚悟しろ、異国の侵略者ども！この要塞の支配者である、グスタフ様が剣の相手をしてやろう！
	 */
	public static final NpcStringId PREPARE_TO_DIE_FOREIGN_INVADERS_I_AM_GUSTAV_THE_ETERNAL_RULER_OF_THIS_FORTRESS_AND_I_HAVE_TAKEN_UP_MY_SWORD_TO_REPEL_THEE;

	/**
	 * ID: 1000276<br>
	 * Message: 死者の王国、アデンに栄光を！不死の君主、グスタフ様に栄光を！
	 */
	public static final NpcStringId GLORY_TO_ADEN_THE_KINGDOM_OF_THE_LION_GLORY_TO_SIR_GUSTAV_OUR_IMMORTAL_LORD;

	/**
	 * ID: 1000277<br>
	 * Message: グスタフの戦士たちよ、戦え！侵略者に死を！
	 */
	public static final NpcStringId SOLDIERS_OF_GUSTAV_GO_FORTH_AND_DESTROY_THE_INVADERS;

	/**
	 * ID: 1000278<br>
	 * Message: そんなはずは．．．これが、敗北というものなのか。しかし、必ず戻る！お前たちの首をもらいにな！
	 */
	public static final NpcStringId THIS_IS_UNBELIEVABLE_HAVE_I_REALLY_BEEN_DEFEATED_I_SHALL_RETURN_AND_TAKE_YOUR_HEAD;

	/**
	 * ID: 1000279<br>
	 * Message: これが私の限界なのか．．．だが、グスタフ様の許可なくしては死ねんのだ！
	 */
	public static final NpcStringId COULD_IT_BE_THAT_I_HAVE_REACHED_MY_END_I_CANNOT_DIE_WITHOUT_HONOR_WITHOUT_THE_PERMISSION_OF_SIR_GUSTAV;

	/**
	 * ID: 1000280<br>
	 * Message: また再び敗北の辱めを味わうのか．．．だが、悲劇はこれでは終わらん．．．
	 */
	public static final NpcStringId AH_THE_BITTER_TASTE_OF_DEFEAT_I_FEAR_MY_TORMENTS_ARE_NOT_OVER;

	/**
	 * ID: 1000281<br>
	 * Message: 私はパプリオン様の意志に従う者！
	 */
	public static final NpcStringId I_FOLLOW_THE_WILL_OF_FAFURION;

	/**
	 * ID: 1000282<br>
	 * Message: 幸運の宝くじの販売を行います！
	 */
	public static final NpcStringId TICKETS_FOR_THE_LUCKY_LOTTERY_ARE_NOW_ON_SALE;

	/**
	 * ID: 1000283<br>
	 * Message: 幸運の宝くじの抽選を行います！
	 */
	public static final NpcStringId THE_LUCKY_LOTTERY_DRAWING_IS_ABOUT_TO_BEGIN;

	/**
	 * ID: 1000284<br>
	 * Message: おめでとうございます！第$s1回の当選番号は$s2です。
	 */
	public static final NpcStringId THE_WINNING_NUMBERS_FOR_LUCKY_LOTTERY_S1_ARE_S2_CONGRATULATIONS_TO_THE_WINNERS;

	/**
	 * ID: 1000285<br>
	 * Message: 年齢制限によって宝くじを買うことができません。
	 */
	public static final NpcStringId YOURE_TOO_YOUNG_TO_PLAY_LUCKY_LOTTERY;

	/**
	 * ID: 1000286<br>
	 * Message: $s1！背後に気をつけるんだな！
	 */
	public static final NpcStringId S1_WATCH_YOUR_BACK;

	/**
	 * ID: 1000287<br>
	 * Message: $s1！お手並み拝見といこう！
	 */
	public static final NpcStringId S1_COME_ON_ILL_TAKE_YOU_ON;

	/**
	 * ID: 1000288<br>
	 * Message: $s1！邪魔をするとはけしからん！おぉい、手を貸してくれ！
	 */
	public static final NpcStringId S1_HOW_DARE_YOU_INTERRUPT_OUR_FIGHT_HEY_GUYS_HELP;

	/**
	 * ID: 1000289<br>
	 * Message: 力を貸そう！卑怯者は許さん！
	 */
	public static final NpcStringId ILL_HELP_YOU_IM_NO_COWARD;

	/**
	 * ID: 1000290<br>
	 * Message: 究極の力よ！
	 */
	public static final NpcStringId DEAR_ULTIMATE_POWER;

	/**
	 * ID: 1000291<br>
	 * Message: 全員 $s1 に集中攻撃だ！
	 */
	public static final NpcStringId EVERYBODY_ATTACK_S1;

	/**
	 * ID: 1000292<br>
	 * Message: 命令に従います。
	 */
	public static final NpcStringId I_WILL_FOLLOW_YOUR_ORDER;

	/**
	 * ID: 1000293<br>
	 * Message: これは知らなかっただろう！
	 */
	public static final NpcStringId BET_YOU_DIDNT_EXPECT_THIS;

	/**
	 * ID: 1000294<br>
	 * Message: 出でよ！闇の者ども！
	 */
	public static final NpcStringId COME_OUT_YOU_CHILDREN_OF_DARKNESS;

	/**
	 * ID: 1000295<br>
	 * Message: パーティ メンバー召喚！
	 */
	public static final NpcStringId SUMMON_PARTY_MEMBERS;

	/**
	 * ID: 1000296<br>
	 * Message: ご主人様！お呼びでしょうか。
	 */
	public static final NpcStringId MASTER_DID_YOU_CALL_ME;

	/**
	 * ID: 1000297<br>
	 * Message: 愚か者め！
	 */
	public static final NpcStringId YOU_IDIOT;

	/**
	 * ID: 1000298<br>
	 * Message: これはどうだ！
	 */
	public static final NpcStringId WHAT_ABOUT_THIS;

	/**
	 * ID: 1000299<br>
	 * Message: やるな $s1！これで最後だ！
	 */
	public static final NpcStringId VERY_IMPRESSIVE_S1_THIS_IS_THE_LAST;

	/**
	 * ID: 1000300<br>
	 * Message: 黎明
	 */
	public static final NpcStringId DAWN;

	/**
	 * ID: 1000301<br>
	 * Message: 黄昏
	 */
	public static final NpcStringId DUSK;

	/**
	 * ID: 1000302<br>
	 * Message: なし
	 */
	public static final NpcStringId NOTHINGNESS;

	/**
	 * ID: 1000303<br>
	 * Message: まもなくこの世は滅びるであろう！
	 */
	public static final NpcStringId THIS_WORLD_WILL_SOON_BE_ANNIHILATED;

	/**
	 * ID: 1000304<br>
	 * Message: そなたに呪いをかけてやろう！
	 */
	public static final NpcStringId A_CURSE_UPON_YOU;

	/**
	 * ID: 1000305<br>
	 * Message: 裁きの日が近づいている！
	 */
	public static final NpcStringId THE_DAY_OF_JUDGMENT_IS_NEAR;

	/**
	 * ID: 1000306<br>
	 * Message: そなたに祝福を！
	 */
	public static final NpcStringId I_BESTOW_UPON_YOU_A_BLESSING;

	/**
	 * ID: 1000307<br>
	 * Message: 弱いやつを先に倒すのがケンカの法則だ！
	 */
	public static final NpcStringId THE_FIRST_RULE_OF_FIGHTING_IS_TO_START_BY_KILLING_THE_WEAK_ONES;

	/**
	 * ID: 1000308<br>
	 * Message: アデナ
	 */
	public static final NpcStringId ADENA;

	/**
	 * ID: 1000309<br>
	 * Message: 古代のアデナ
	 */
	public static final NpcStringId ANCIENT_ADENA;

	/**
	 * ID: 1000312<br>
	 * Message: レベル32未満
	 */
	public static final NpcStringId LEVEL_31_OR_LOWER;

	/**
	 * ID: 1000313<br>
	 * Message: レベル43未満
	 */
	public static final NpcStringId LEVEL_42_OR_LOWER;

	/**
	 * ID: 1000314<br>
	 * Message: レベル54未満
	 */
	public static final NpcStringId LEVEL_53_OR_LOWER;

	/**
	 * ID: 1000315<br>
	 * Message: レベル65未満
	 */
	public static final NpcStringId LEVEL_64_OR_LOWER;

	/**
	 * ID: 1000316<br>
	 * Message: 無制限
	 */
	public static final NpcStringId NO_LEVEL_LIMIT;

	/**
	 * ID: 1000317<br>
	 * Message: 2分後に闇の祭典が始まります。受付を行ってください。
	 */
	public static final NpcStringId THE_MAIN_EVENT_WILL_START_IN_2_MINUTES_PLEASE_REGISTER_NOW;

	/**
	 * ID: 1000318<br>
	 * Message: 闇の祭典を始めます。
	 */
	public static final NpcStringId THE_MAIN_EVENT_IS_NOW_STARTING;

	/**
	 * ID: 1000319<br>
	 * Message: 5分後に闇の祭典が終了します。
	 */
	public static final NpcStringId THE_MAIN_EVENT_WILL_CLOSE_IN_5_MINUTES;

	/**
	 * ID: 1000320<br>
	 * Message: 2分後に闇の祭典が終了します。次のゲームの準備をしてください。
	 */
	public static final NpcStringId THE_MAIN_EVENT_WILL_FINISH_IN_2_MINUTES_PLEASE_PREPARE_FOR_THE_NEXT_GAME;

	/**
	 * ID: 1000321<br>
	 * Message: SSQ 貢献度が $s1 上がりました。
	 */
	public static final NpcStringId THE_AMOUNT_OF_SSQ_CONTRIBUTION_HAS_INCREASED_BY_S1;

	/**
	 * ID: 1000322<br>
	 * Message: 記録なし
	 */
	public static final NpcStringId NO_RECORD_EXISTS;

	/**
	 * ID: 1000380<br>
	 * Message: 申し分ない生贄だ！すぐ外に送ってやろう。
	 */
	public static final NpcStringId THAT_WILL_DO_ILL_MOVE_YOU_TO_THE_OUTSIDE_SOON;

	/**
	 * ID: 1000382<br>
	 * Message: 後ろがスキだらけだな！
	 */
	public static final NpcStringId YOUR_REAR_IS_PRACTICALLY_UNGUARDED;

	/**
	 * ID: 1000383<br>
	 * Message: 背を見せるとは！無視しているのか！
	 */
	public static final NpcStringId HOW_DARE_YOU_TURN_YOUR_BACK_ON_ME;

	/**
	 * ID: 1000384<br>
	 * Message: $s1！一対一で勝負だ！
	 */
	public static final NpcStringId S1_ILL_DEAL_WITH_YOU_MYSELF;

	/**
	 * ID: 1000385<br>
	 * Message: $s1！対決だ！
	 */
	public static final NpcStringId S1_THIS_IS_PERSONAL;

	/**
	 * ID: 1000386<br>
	 * Message: $s1！決闘だ！誰も邪魔するな！
	 */
	public static final NpcStringId S1_LEAVE_US_ALONE_THIS_IS_BETWEEN_US;

	/**
	 * ID: 1000387<br>
	 * Message: $s1！お前の命は私がもらう！
	 */
	public static final NpcStringId S1_KILLING_YOU_WILL_BE_A_PLEASURE;

	/**
	 * ID: 1000388<br>
	 * Message: $s1！よくも対決を妨害してくれたな！愚か者め！
	 */
	public static final NpcStringId S1_HEY_WERE_HAVING_A_DUEL_HERE;

	/**
	 * ID: 1000389<br>
	 * Message: 一対一の対決は終わりだ！皆の者、やってしまえ！
	 */
	public static final NpcStringId THE_DUEL_IS_OVER_ATTACK;

	/**
	 * ID: 1000390<br>
	 * Message: 反則だ！皆の者、卑怯者を始末しろ！
	 */
	public static final NpcStringId FOUL_KILL_THE_COWARD;

	/**
	 * ID: 1000391<br>
	 * Message: 神聖な決闘を妨害するとは！ただではおかないぞ！
	 */
	public static final NpcStringId HOW_DARE_YOU_INTERRUPT_A_SACRED_DUEL_YOU_MUST_BE_TAUGHT_A_LESSON;

	/**
	 * ID: 1000392<br>
	 * Message: 卑怯者！消えろ！
	 */
	public static final NpcStringId DIE_YOU_COWARD;

	/**
	 * ID: 1000394<br>
	 * Message: 卑怯者に死を！
	 */
	public static final NpcStringId KILL_THE_COWARD;

	/**
	 * ID: 1000395<br>
	 * Message: 青二才を相手にこのスキルを使うとは！
	 */
	public static final NpcStringId I_NEVER_THOUGHT_ID_USE_THIS_AGAINST_A_NOVICE;

	/**
	 * ID: 1000396<br>
	 * Message: まだまだ私には勝てんぞ！
	 */
	public static final NpcStringId YOU_WONT_TAKE_ME_DOWN_EASILY;

	/**
	 * ID: 1000397<br>
	 * Message: これからが本当の戦いだ！
	 */
	public static final NpcStringId THE_BATTLE_HAS_JUST_BEGUN;

	/**
	 * ID: 1000398<br>
	 * Message: $s1から倒せ！
	 */
	public static final NpcStringId KILL_S1_FIRST;

	/**
	 * ID: 1000399<br>
	 * Message: 集中攻撃だ！目標は$s1！
	 */
	public static final NpcStringId ATTACK_S1;

	/**
	 * ID: 1000400<br>
	 * Message: 攻撃だ！攻撃！
	 */
	public static final NpcStringId ATTACK_ATTACK;

	/**
	 * ID: 1000401<br>
	 * Message: 集中攻撃開始！
	 */
	public static final NpcStringId OVER_HERE;

	/**
	 * ID: 1000402<br>
	 * Message: ラジャー！
	 */
	public static final NpcStringId ROGER;

	/**
	 * ID: 1000403<br>
	 * Message: 皆の者！出て来い！
	 */
	public static final NpcStringId SHOW_YOURSELVES;

	/**
	 * ID: 1000404<br>
	 * Message: 闇の軍勢よ、私に続け！
	 */
	public static final NpcStringId FORCES_OF_DARKNESS_FOLLOW_ME;

	/**
	 * ID: 1000405<br>
	 * Message: 兄弟たちよ、敵を殲滅させろ！
	 */
	public static final NpcStringId DESTROY_THE_ENEMY_MY_BROTHERS;

	/**
	 * ID: 1000406<br>
	 * Message: これからが本番だ！
	 */
	public static final NpcStringId NOW_THE_FUN_STARTS;

	/**
	 * ID: 1000407<br>
	 * Message: 甘く見たのが間違いだった！本気で相手をしてやろう！
	 */
	public static final NpcStringId ENOUGH_FOOLING_AROUND_GET_READY_TO_DIE;

	/**
	 * ID: 1000408<br>
	 * Message: 愚か者めが！今まで手を抜いていただけだ！
	 */
	public static final NpcStringId YOU_IDIOT_IVE_JUST_BEEN_TOYING_WITH_YOU;

	/**
	 * ID: 1000409<br>
	 * Message: 私の本当の力を見せてやろう！
	 */
	public static final NpcStringId WITNESS_MY_TRUE_POWER;

	/**
	 * ID: 1000410<br>
	 * Message: 本当の戦いはこれからだ！
	 */
	public static final NpcStringId NOW_THE_BATTLE_BEGINS;

	/**
	 * ID: 1000411<br>
	 * Message: 久しぶりに血の騒ぐ相手だな！
	 */
	public static final NpcStringId I_MUST_ADMIT_NO_ONE_MAKES_MY_BLOOD_BOIL_QUITE_LIKE_YOU_DO;

	/**
	 * ID: 1000412<br>
	 * Message: こいつは驚いた！私をここまで追い込むとは！
	 */
	public static final NpcStringId YOU_HAVE_MORE_SKILL_THAN_I_THOUGHT;

	/**
	 * ID: 1000413<br>
	 * Message: 200％の戦闘力で相手してやろう！
	 */
	public static final NpcStringId ILL_DOUBLE_MY_STRENGTH;

	/**
	 * ID: 1000414<br>
	 * Message: これが最後だ！終わりにしてやるぞ！
	 */
	public static final NpcStringId PREPARE_TO_DIE;

	/**
	 * ID: 1000415<br>
	 * Message: 皆の者、悲しみに暮れるがよい！間もなく死の女神が降臨するのだ！
	 */
	public static final NpcStringId ALL_IS_LOST_PREPARE_TO_MEET_THE_GODDESS_OF_DEATH;

	/**
	 * ID: 1000416<br>
	 * Message: 皆の者、悲しみに暮れるがよい！間もなく滅亡の予言が実現されるであろう！
	 */
	public static final NpcStringId ALL_IS_LOST_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED;

	/**
	 * ID: 1000417<br>
	 * Message: 滅亡の予言に耳を傾けろ！間もなく終末の時代が始まるのだ！
	 */
	public static final NpcStringId THE_END_OF_TIME_HAS_COME_THE_PROPHECY_OF_DESTRUCTION_HAS_BEEN_FULFILLED;

	/**
	 * ID: 1000418<br>
	 * Message: $s1！そなたに滅亡の兆しが見える！
	 */
	public static final NpcStringId S1_YOU_BRING_AN_ILL_WIND;

	/**
	 * ID: 1000419<br>
	 * Message: $s1！そなたに絶望をくれてやろう！
	 */
	public static final NpcStringId S1_YOU_MIGHT_AS_WELL_GIVE_UP;

	/**
	 * ID: 1000420<br>
	 * Message: 終末の時代を生きる者よ、絶望の淵に落ちよ！
	 */
	public static final NpcStringId YOU_DONT_HAVE_ANY_HOPE_YOUR_END_HAS_COME;

	/**
	 * ID: 1000421<br>
	 * Message: 闇の啓示に耳を傾けろ！闇が眠りから覚めたのだ！
	 */
	public static final NpcStringId THE_PROPHECY_OF_DARKNESS_HAS_BEEN_FULFILLED;

	/**
	 * ID: 1000422<br>
	 * Message: 闇の啓示に耳を傾けろ！混沌の時代が始まったのだ！
	 */
	public static final NpcStringId AS_FORETOLD_IN_THE_PROPHECY_OF_DARKNESS_THE_ERA_OF_CHAOS_HAS_BEGUN;

	/**
	 * ID: 1000423<br>
	 * Message: 闇の啓示に耳を傾けろ！間もなく死の楽園となるだろう！
	 */
	public static final NpcStringId THE_PROPHECY_OF_DARKNESS_HAS_COME_TO_PASS;

	/**
	 * ID: 1000424<br>
	 * Message: $s1！そなたに啓示の祝福を与えよう！
	 */
	public static final NpcStringId S1_I_GIVE_YOU_THE_BLESSING_OF_PROPHECY;

	/**
	 * ID: 1000425<br>
	 * Message: $s1！そなたに深淵の権能を与えよう！
	 */
	public static final NpcStringId S1_I_BESTOW_UPON_YOU_THE_AUTHORITY_OF_THE_ABYSS;

	/**
	 * ID: 1000426<br>
	 * Message: 新しい時代を開く者よ、目を覚ませ！
	 */
	public static final NpcStringId HERALD_OF_THE_NEW_ERA_OPEN_YOUR_EYES;

	/**
	 * ID: 1000427<br>
	 * Message: 弱い者から倒すのが戦いの法則だ！
	 */
	public static final NpcStringId REMEMBER_KILL_THE_WEAKLINGS_FIRST;

	/**
	 * ID: 1000428<br>
	 * Message: ひ弱そうなお前から殺してやる！
	 */
	public static final NpcStringId PREPARE_TO_DIE_MAGGOT;

	/**
	 * ID: 1000429<br>
	 * Message: 生贄はこのくらいで充分だ！すぐ外に出してやるから準備しろ！
	 */
	public static final NpcStringId THAT_WILL_DO_PREPARE_TO_BE_RELEASED;

	/**
	 * ID: 1000430<br>
	 * Message: 引き分け
	 */
	public static final NpcStringId DRAW;

	/**
	 * ID: 1000431<br>
	 * Message: 封印の支配者の皆様！珍しい名品をお見せしに参りました！
	 */
	public static final NpcStringId RULERS_OF_THE_SEAL_I_BRING_YOU_WONDROUS_GIFTS;

	/**
	 * ID: 1000432<br>
	 * Message: 封印の支配者の皆様！最高級の武器をお見せしに参りました！
	 */
	public static final NpcStringId RULERS_OF_THE_SEAL_I_HAVE_SOME_EXCELLENT_WEAPONS_TO_SHOW_YOU;

	/**
	 * ID: 1000433<br>
	 * Message: あちこち行かなきゃならん、あー忙しい、忙しい！
	 */
	public static final NpcStringId IVE_BEEN_SO_BUSY_LATELY_IN_ADDITION_TO_PLANNING_MY_TRIP;

	/**
	 * ID: 1000434<br>
	 * Message: 弱い者いじめとは！許さんぞ！
	 */
	public static final NpcStringId YOUR_TREATMENT_OF_WEAKLINGS_IS_UNFORGIVABLE;

	/**
	 * ID: 1000435<br>
	 * Message: 助けに来たぞ！うっしゃ！
	 */
	public static final NpcStringId IM_HERE_TO_HELP_YOU_HI_YAH;

	/**
	 * ID: 1000436<br>
	 * Message: 正義の名のもとに！
	 */
	public static final NpcStringId JUSTICE_WILL_BE_SERVED;

	/**
	 * ID: 1000437<br>
	 * Message: 光のもとに！栄光の名のもとに！
	 */
	public static final NpcStringId ON_TO_IMMORTAL_GLORY;

	/**
	 * ID: 1000438<br>
	 * Message: 正義は弱い者の味方！
	 */
	public static final NpcStringId JUSTICE_ALWAYS_AVENGES_THE_POWERLESS;

	/**
	 * ID: 1000439<br>
	 * Message: まだ戦えるか。もう少し踏ん張れ！
	 */
	public static final NpcStringId ARE_YOU_HURT_HANG_IN_THERE_WEVE_ALMOST_GOT_THEM;

	/**
	 * ID: 1000440<br>
	 * Message: 私の名か。お前らに教える名などないわ！
	 */
	public static final NpcStringId WHY_SHOULD_I_TELL_YOU_MY_NAME_YOU_CREEP;

	/**
	 * ID: 1000441<br>
	 * Message: 受付可能
	 */
	public static final NpcStringId N1_MINUTE;

	/**
	 * ID: 1000443<br>
	 * Message: $s1城の守備メンバーを内郭にテレポートさせます。
	 */
	public static final NpcStringId THE_DEFENDERS_OF_S1_CASTLE_WILL_BE_TELEPORTED_TO_THE_INNER_CASTLE;

	/**
	 * ID: 1000444<br>
	 * Message: 日曜日
	 */
	public static final NpcStringId SUNDAY;

	/**
	 * ID: 1000445<br>
	 * Message: 月曜日
	 */
	public static final NpcStringId MONDAY;

	/**
	 * ID: 1000446<br>
	 * Message: 火曜日
	 */
	public static final NpcStringId TUESDAY;

	/**
	 * ID: 1000447<br>
	 * Message: 水曜日
	 */
	public static final NpcStringId WEDNESDAY;

	/**
	 * ID: 1000448<br>
	 * Message: 木曜日
	 */
	public static final NpcStringId THURSDAY;

	/**
	 * ID: 1000449<br>
	 * Message: 金曜日
	 */
	public static final NpcStringId FRIDAY;

	/**
	 * ID: 1000450<br>
	 * Message: 土曜日
	 */
	public static final NpcStringId SATURDAY;

	/**
	 * ID: 1000451<br>
	 * Message: 時
	 */
	public static final NpcStringId HOUR;

	/**
	 * ID: 1000452<br>
	 * Message: 最後の呪いを．．．ともに地獄へ行こう！
	 */
	public static final NpcStringId ITS_A_GOOD_DAY_TO_DIE_WELCOME_TO_HELL_MAGGOT;

	/**
	 * ID: 1000453<br>
	 * Message: 2分後に闇の祭典が終了します。
	 */
	public static final NpcStringId THE_FESTIVAL_OF_DARKNESS_WILL_END_IN_TWO_MINUTES;

	/**
	 * ID: 1000454<br>
	 * Message: ノーブレス ゲート パス
	 */
	public static final NpcStringId NOBLESSE_GATE_PASS;

	/**
	 * ID: 1000456<br>
	 * Message: 分が経過しました
	 */
	public static final NpcStringId MINUTES_HAVE_PASSED;
	
	/**
	 * ID: 1000457<br>
	 * Message: ゲームが終了しました。間もなく自動テレポートします。
	 */
	public static final NpcStringId GAME_OVER_THE_TELEPORT_WILL_APPEAR_MOMENTARILY;

	/**
	 * ID: 1000458<br>
	 * Message: 地を這う虫けらどもよ、百眼のデーモンの慈悲深さと偉大さは計り知れぬのだ！ハハハ！
	 */
	public static final NpcStringId YOU_WHO_ARE_LIKE_THE_SLUGS_CRAWLING_ON_THE_GROUND_THE_GENEROSITY_AND_GREATNESS_OF_ME_DAIMON_THE_WHITE_EYED_IS_ENDLESS_HA_HA_HA;

	/**
	 * ID: 1000459<br>
	 * Message: この百眼のデーモンを敵に回すなら基本ぐらいできてないとなぁ？
	 */
	public static final NpcStringId IF_YOU_WANT_TO_BE_THE_OPPONENT_OF_ME_DAIMON_THE_WHITE_EYED_YOU_SHOULD_AT_LEAST_HAVE_THE_BASIC_SKILLS;

	/**
	 * ID: 1000460<br>
	 * Message: 大地に縛られた愚かな者どもよ、重い体で苦戦しているようだな。このデーモンがその苦痛を和らげてやろう。
	 */
	public static final NpcStringId YOU_STUPID_CREATURES_THAT_ARE_BOUND_TO_THE_EARTH_YOU_ARE_SUFFERING_TOO_MUCH_WHILE_DRAGGING_YOUR_FAT_HEAVY_BODIES_I_DAIMON_WILL_LIGHTEN_YOUR_BURDEN;

	/**
	 * ID: 1000461<br>
	 * Message: 貴様たちのような軟弱で愚かな種族が私に敵うはずがない！ウハハハ！
	 */
	public static final NpcStringId A_WEAK_AND_STUPID_TRIBE_LIKE_YOURS_DOESNT_DESERVE_TO_BE_MY_ENEMY_BWA_HA_HA_HA;

	/**
	 * ID: 1000462<br>
	 * Message: この百眼のデーモンの領地を汚す愚か者よ、その代償を払うがいい！
	 */
	public static final NpcStringId YOU_DARE_TO_INVADE_THE_TERRITORY_OF_DAIMON_THE_WHITE_EYED_NOW_YOU_WILL_PAY_THE_PRICE_FOR_YOUR_ACTION;

	/**
	 * ID: 1000463<br>
	 * Message: これこそフローティング アイの偉大なる君主、百眼のデーモンの恩恵だ！ハハハ！
	 */
	public static final NpcStringId THIS_IS_THE_GRACE_OF_DAIMON_THE_WHITE_EYED_THE_GREAT_MONSTER_EYE_LORD_HA_HA_HA;

	/**
	 * ID: 1000464<br>
	 * Message: $s1様がデュエリストの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DUELISTS_CONGRATULATIONS;

	/**
	 * ID: 1000465<br>
	 * Message: $s1様がドレッドノートの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DREADNOUGHTS_CONGRATULATIONS;

	/**
	 * ID: 1000466<br>
	 * Message: $s1様がフェニックス ナイトの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_PHOENIX_KNIGHTS_CONGRATULATIONS;

	/**
	 * ID: 1000467<br>
	 * Message: $s1様がヘル ナイトの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_HELL_KNIGHTS_CONGRATULATIONS;

	/**
	 * ID: 1000468<br>
	 * Message: $s1様がサジタリウスの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_SAGITTARIUS_HERO_CONGRATULATIONS;

	/**
	 * ID: 1000469<br>
	 * Message: $s1様がアドベンチャラーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ADVENTURERS_CONGRATULATIONS;

	/**
	 * ID: 1000470<br>
	 * Message: $s1様がアークメイジの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ARCHMAGES_CONGRATULATIONS;

	/**
	 * ID: 1000471<br>
	 * Message: $s1様がソウル テイカーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOULTAKERS_CONGRATULATIONS;

	/**
	 * ID: 1000472<br>
	 * Message: $s1様がアルカナ ロードの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ARCANA_LORDS_CONGRATULATIONS;

	/**
	 * ID: 1000473<br>
	 * Message: $s1様がカーディナルの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_CARDINALS_CONGRATULATIONS;

	/**
	 * ID: 1000474<br>
	 * Message: $s1様がハイエロファントの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_HIEROPHANTS_CONGRATULATIONS;

	/**
	 * ID: 1000475<br>
	 * Message: $s1様がエヴァス テンプラーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_EVAS_TEMPLARS_CONGRATULATIONS;

	/**
	 * ID: 1000476<br>
	 * Message: $s1様がソード ミューズの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SWORD_MUSES_CONGRATULATIONS;

	/**
	 * ID: 1000477<br>
	 * Message: $s1様がウィンド ライダーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_WIND_RIDERS_CONGRATULATIONS;

	/**
	 * ID: 1000478<br>
	 * Message: $s1様がムーンライト センティネルの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MOONLIGHT_SENTINELS_CONGRATULATIONS;

	/**
	 * ID: 1000479<br>
	 * Message: $s1様がミスティック ミューズの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MYSTIC_MUSES_CONGRATULATIONS;

	/**
	 * ID: 1000480<br>
	 * Message: $s1様がエレメンタル マスターの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_ELEMENTAL_MASTERS_CONGRATULATIONS;

	/**
	 * ID: 1000481<br>
	 * Message: $s1様がエヴァス セイントの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_EVAS_SAINTS_CONGRATULATIONS;

	/**
	 * ID: 1000482<br>
	 * Message: $s1様がシリエン テンプラーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_TEMPLARS_CONGRATULATIONS;

	/**
	 * ID: 1000483<br>
	 * Message: $s1様がスペクトラル ダンサーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_DANCERS_CONGRATULATIONS;

	/**
	 * ID: 1000484<br>
	 * Message: $s1様がゴースト ハンターの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_HUNTERS_CONGRATULATIONS;

	/**
	 * ID: 1000485<br>
	 * Message: $s1様がゴースト センティネルの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GHOST_SENTINELS_CONGRATULATIONS;

	/**
	 * ID: 1000486<br>
	 * Message: $s1様がストーム スクリーマーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_STORM_SCREAMERS_CONGRATULATIONS;

	/**
	 * ID: 1000487<br>
	 * Message: $s1様がスペクトラル マスターの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SPECTRAL_MASTERS_CONGRATULATIONS;

	/**
	 * ID: 1000488<br>
	 * Message: $s1様がシリエン セイントの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_THE_SHILLIEN_SAINTS_CONGRATULATIONS;

	/**
	 * ID: 1000489<br>
	 * Message: $s1様がタイタンの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_TITANS_CONGRATULATIONS;

	/**
	 * ID: 1000490<br>
	 * Message: $s1様がグランド カバタリの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_GRAND_KHAVATARIS_CONGRATULATIONS;

	/**
	 * ID: 1000491<br>
	 * Message: $s1様がドミネーターの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOMINATORS_CONGRATULATIONS;

	/**
	 * ID: 1000492<br>
	 * Message: $s1様がドゥーム クライヤーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMCRYERS_CONGRATULATIONS;

	/**
	 * ID: 1000493<br>
	 * Message: $s1様がフォーチュン シーカーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_FORTUNE_SEEKERS_CONGRATULATIONS;

	/**
	 * ID: 1000494<br>
	 * Message: $s1様がマエストロの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_MAESTROS_CONGRATULATIONS;

	/**
	 * ID: 1000495<br>
	 * Message: 未登録
	 */
	public static final NpcStringId UNREGISTERED;

	/**
	 * ID: 1000496<br>
	 * Message: $s1様がドゥーム ブリンガーの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_DOOMBRINGERS_CONGRATULATIONS;

	/**
	 * ID: 1000497<br>
	 * Message: $s1様がソウル ハウンドの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_SOUL_HOUNDS_CONGRATULATIONS;

	/**
	 * ID: 1000499<br>
	 * Message: $s1様がトリックスターの英雄になりました。おめでとうございます。
	 */
	public static final NpcStringId S1_YOU_HAVE_BECOME_A_HERO_OF_TRICKSTERS_CONGRATULATIONS;

	/**
	 * ID: 1000500<br>
	 * Message: 只今より霊廟内へ入ることができます。
	 */
	public static final NpcStringId YOU_MAY_NOW_ENTER_THE_SEPULCHER;

	/**
	 * ID: 1000501<br>
	 * Message: 霊廟内に入るには、四大霊廟の石像に手を乗せます。
	 */
	public static final NpcStringId IF_YOU_PLACE_YOUR_HAND_ON_THE_STONE_STATUE_IN_FRONT_OF_EACH_SEPULCHER_YOU_WILL_BE_ABLE_TO_ENTER;

	/**
	 * ID: 1000502<br>
	 * Message: ライバルたちの前に妨害モンスターを出現させました。
	 */
	public static final NpcStringId THE_MONSTERS_HAVE_SPAWNED;

	/**
	 * ID: 1000503<br>
	 * Message: 助けてくださってありがとうございます。
	 */
	public static final NpcStringId THANK_YOU_FOR_SAVING_ME;

	/**
	 * ID: 1000504<br>
	 * Message: $s1人未満
	 */
	public static final NpcStringId FEWER_THAN_S2;

	/**
	 * ID: 1000505<br>
	 * Message: $s1人以上
	 */
	public static final NpcStringId MORE_THAN_S2;

	/**
	 * ID: 1000506<br>
	 * Message: ポイント
	 */
	public static final NpcStringId POINT;

	/**
	 * ID: 1000507<br>
	 * Message: 競争
	 */
	public static final NpcStringId COMPETITION;

	/**
	 * ID: 1000508<br>
	 * Message: 封印有効
	 */
	public static final NpcStringId SEAL_VALIDATION;

	/**
	 * ID: 1000509<br>
	 * Message: 準備
	 */
	public static final NpcStringId PREPARATION;

	/**
	 * ID: 1000512<br>
	 * Message: 未所有
	 */
	public static final NpcStringId NO_OWNER;

	/**
	 * ID: 1000513<br>
	 * Message: ここは$s1様には危険です。お戻りください。
	 */
	public static final NpcStringId THIS_PLACE_IS_DANGEROUS_S1_PLEASE_TURN_BACK;

	/**
	 * ID: 1000514<br>
	 * Message: 聖なる眠りを妨げるのは誰だ。
	 */
	public static final NpcStringId WHO_DISTURBS_MY_SACRED_SLEEP;

	/**
	 * ID: 1000515<br>
	 * Message: 盗掘屋よ、去るがいい。ここは我々の安息の地だ。
	 */
	public static final NpcStringId BEGONE_THIEF_LET_OUR_BONES_REST_IN_PEACE;

	/**
	 * ID: 1000516<br>
	 * Message: ヘストゥイたちよ、我々に構わないでくれ。
	 */
	public static final NpcStringId _LEAVE_US_BE_HESTUI_SCUM;

	/**
	 * ID: 1000517<br>
	 * Message: カカイよ、楽には眠れないだろう。
	 */
	public static final NpcStringId THIEVING_KAKAI_MAY_BLOODBUGS_GNAW_YOU_IN_YOUR_SLEEP;

	/**
	 * ID: 1000518<br>
	 * Message: トラベラーズ トークン
	 */
	public static final NpcStringId NEWBIE_TRAVEL_TOKEN;

	/**
	 * ID: 1000519<br>
	 * Message: 炎の支配者であるこの私にあえて挑むというのか。 $s1！
	 */
	public static final NpcStringId ARROGANT_FOOL_YOU_DARE_TO_CHALLENGE_ME_THE_RULER_OF_FLAMES_HERE_IS_YOUR_REWARD;

	/**
	 * ID: 1000520<br>
	 * Message: $s1！その程度の力で私を倒せるとでも思ったか！
	 */
	public static final NpcStringId S1_YOU_CANNOT_HOPE_TO_DEFEAT_ME_WITH_YOUR_MEAGER_STRENGTH;

	/**
	 * ID: 1000521<br>
	 * Message: 神すらも手をつけられない私に$s1お前が挑もうというのか！笑わせるな！
	 */
	public static final NpcStringId NOT_EVEN_THE_GODS_THEMSELVES_COULD_TOUCH_ME_BUT_YOU_S1_YOU_DARE_CHALLENGE_ME_IGNORANT_MORTAL;

	/**
	 * ID: 1000522<br>
	 * Message: 憎悪のレクイエム
	 */
	public static final NpcStringId REQUIEM_OF_HATRED;

	/**
	 * ID: 1000523<br>
	 * Message: 狂喜のフーガ
	 */
	public static final NpcStringId FUGUE_OF_JUBILATION;

	/**
	 * ID: 1000524<br>
	 * Message: 疾走のトッカータ
	 */
	public static final NpcStringId FRENETIC_TOCCATA;

	/**
	 * ID: 1000525<br>
	 * Message: 魅惑のマズルカ
	 */
	public static final NpcStringId HYPNOTIC_MAZURKA;

	/**
	 * ID: 1000526<br>
	 * Message: 悲嘆のコーラル
	 */
	public static final NpcStringId MOURNFUL_CHORALE_PRELUDE;

	/**
	 * ID: 1000527<br>
	 * Message: 孤独のロンド
	 */
	public static final NpcStringId RONDO_OF_SOLITUDE;

	/**
	 * ID: 1000528<br>
	 * Message: オリンピアード トークン
	 */
	public static final NpcStringId OLYMPIAD_TOKEN;

	/**
	 * ID: 1001000<br>
	 * Message: アデン王国
	 */
	public static final NpcStringId THE_KINGDOM_OF_ADEN;

	/**
	 * ID: 1001100<br>
	 * Message: エルモア王国
	 */
	public static final NpcStringId THE_KINGDOM_OF_ELMORE;

	/**
	 * ID: 1010001<br>
	 * Message: 話せる島の村
	 */
	public static final NpcStringId TALKING_ISLAND_VILLAGE;

	/**
	 * ID: 1010002<br>
	 * Message: エルフ村
	 */
	public static final NpcStringId THE_ELVEN_VILLAGE;

	/**
	 * ID: 1010003<br>
	 * Message: ダークエルフ村
	 */
	public static final NpcStringId THE_DARK_ELF_VILLAGE;

	/**
	 * ID: 1010004<br>
	 * Message: グルーディン村
	 */
	public static final NpcStringId THE_VILLAGE_OF_GLUDIN;

	/**
	 * ID: 1010005<br>
	 * Message: グルーディオ城の村
	 */
	public static final NpcStringId THE_TOWN_OF_GLUDIO;

	/**
	 * ID: 1010006<br>
	 * Message: ディオン城の村
	 */
	public static final NpcStringId THE_TOWN_OF_DION;

	/**
	 * ID: 1010007<br>
	 * Message: ギラン城の村
	 */
	public static final NpcStringId THE_TOWN_OF_GIRAN;

	/**
	 * ID: 1010008<br>
	 * Message: オーク村
	 */
	public static final NpcStringId ORC_VILLAGE;

	/**
	 * ID: 1010009<br>
	 * Message: ドワーフ村
	 */
	public static final NpcStringId DWARVEN_VILLAGE;

	/**
	 * ID: 1010010<br>
	 * Message: ダークエルフの森の南部
	 */
	public static final NpcStringId THE_SOUTHERN_PART_OF_THE_DARK_FOREST;

	/**
	 * ID: 1010011<br>
	 * Message: 北東海岸
	 */
	public static final NpcStringId THE_NORTHEAST_COAST;

	/**
	 * ID: 1010012<br>
	 * Message: 荒地の南側入口
	 */
	public static final NpcStringId THE_SOUTHERN_ENTRANCE_TO_THE_WASTELANDSS;

	/**
	 * ID: 1010013<br>
	 * Message: オーレン城の村
	 */
	public static final NpcStringId TOWN_OF_OREN;

	/**
	 * ID: 1010014<br>
	 * Message: 象牙の塔
	 */
	public static final NpcStringId IVORY_TOWER;

	/**
	 * ID: 1010015<br>
	 * Message: 1階ロビー
	 */
	public static final NpcStringId N1ST_FLOOR_LOBBY;

	/**
	 * ID: 1010016<br>
	 * Message: 地下1階商店街
	 */
	public static final NpcStringId UNDERGROUND_SHOPPING_AREA;

	/**
	 * ID: 1010017<br>
	 * Message: 2階ヒューマン ウィザード ギルド
	 */
	public static final NpcStringId N2ND_FLOOR_HUMAN_WIZARD_GUILD;

	/**
	 * ID: 1010018<br>
	 * Message: 3階エルヴン ウィザード ギルド
	 */
	public static final NpcStringId N3RD_FLOOR_ELVEN_WIZARD_GUILD;

	/**
	 * ID: 1010019<br>
	 * Message: 4階ダーク ウィザード ギルド
	 */
	public static final NpcStringId N4TH_FLOOR_DARK_WIZARD_GUILD;

	/**
	 * ID: 1010020<br>
	 * Message: 猟師の村
	 */
	public static final NpcStringId HUNTERS_VILLAGE;

	/**
	 * ID: 1010021<br>
	 * Message: ギラン港
	 */
	public static final NpcStringId GIRAN_HARBOR;

	/**
	 * ID: 1010022<br>
	 * Message: ハーディンの私塾
	 */
	public static final NpcStringId HARDINS_PRIVATE_ACADEMY;

	/**
	 * ID: 1010023<br>
	 * Message: アデン城の村
	 */
	public static final NpcStringId TOWN_OF_ADEN;

	/**
	 * ID: 1010024<br>
	 * Message: 村の広場
	 */
	public static final NpcStringId VILLAGE_SQUARE;

	/**
	 * ID: 1010025<br>
	 * Message: 北門入口
	 */
	public static final NpcStringId NORTH_GATE_ENTRANCE;

	/**
	 * ID: 1010026<br>
	 * Message: 東門入口
	 */
	public static final NpcStringId EAST_GATE_ENTRANCE;

	/**
	 * ID: 1010027<br>
	 * Message: 西門入口
	 */
	public static final NpcStringId WEST_GATE_ENTRANCE;

	/**
	 * ID: 1010028<br>
	 * Message: 南門入口
	 */
	public static final NpcStringId SOUTH_GATE_ENTRANCE;

	/**
	 * ID: 1010029<br>
	 * Message: トゥレック オークの野営地の入口
	 */
	public static final NpcStringId ENTRANCE_TO_TUREK_ORC_CAMP;

	/**
	 * ID: 1010030<br>
	 * Message: 忘れられた神殿の入口
	 */
	public static final NpcStringId ENTRANCE_TO_FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010031<br>
	 * Message: 荒地の入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_WASTELANDS;

	/**
	 * ID: 1010032<br>
	 * Message: 棄てられた露営地の入口
	 */
	public static final NpcStringId ENTRANCE_TO_ABANDONED_CAMP;

	/**
	 * ID: 1010033<br>
	 * Message: クルマ湿地の入口
	 */
	public static final NpcStringId ENTRANCE_TO_CRUMA_MARSHLANDS;

	/**
	 * ID: 1010034<br>
	 * Message: 処刑場の入口
	 */
	public static final NpcStringId ENTRANCE_TO_EXECUTION_GROUNDS;

	/**
	 * ID: 1010035<br>
	 * Message: パルチザンのアジトへの入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FORTRESS_OF_RESISTANCE;

	/**
	 * ID: 1010036<br>
	 * Message: フローラン村
	 */
	public static final NpcStringId ENTRANCE_TO_FLORAN_VILLAGE;

	/**
	 * ID: 1010037<br>
	 * Message: 中立地帯
	 */
	public static final NpcStringId NEUTRAL_ZONE;

	/**
	 * ID: 1010038<br>
	 * Message: ギラン西の道
	 */
	public static final NpcStringId WESTERN_ROAD_OF_GIRAN;

	/**
	 * ID: 1010039<br>
	 * Message: グルーディン村東の道
	 */
	public static final NpcStringId EASTERN_ROAD_OF_GLUDIN_VILLAGE;

	/**
	 * ID: 1010041<br>
	 * Message: クルマの塔の入口
	 */
	public static final NpcStringId ENTRANCE_TO_CRUMA_TOWER;

	/**
	 * ID: 1010042<br>
	 * Message: 死の回廊
	 */
	public static final NpcStringId DEATH_PASS;

	/**
	 * ID: 1010043<br>
	 * Message: 沼地帯北部
	 */
	public static final NpcStringId NORTHERN_PART_OF_THE_MARSHLANDS;

	/**
	 * ID: 1010044<br>
	 * Message: 中立地帯北西部
	 */
	public static final NpcStringId NORTHEAST_OF_THE_NEUTRAL_ZONE;

	/**
	 * ID: 1010045<br>
	 * Message: 不滅の高原中部
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_CENTRAL_REGION;

	/**
	 * ID: 1010046<br>
	 * Message: 不滅の高原南部
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_SOUTHERN_REGION;

	/**
	 * ID: 1010047<br>
	 * Message: 不滅の高原南東部
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_SOUTHEAST_REGION;

	/**
	 * ID: 1010048<br>
	 * Message: 凍りついた滝
	 */
	public static final NpcStringId FROZEN_WATERFALL;

	/**
	 * ID: 1010049<br>
	 * Message: 水上都市ハイネス
	 */
	public static final NpcStringId HEINE;

	/**
	 * ID: 1010050<br>
	 * Message: 傲慢の塔：1階
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_1ST_FLOOR;

	/**
	 * ID: 1010051<br>
	 * Message: 傲慢の塔：5階
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_5TH_FLOOR;

	/**
	 * ID: 1010052<br>
	 * Message: 傲慢の塔：10階
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_10TH_FLOOR;

	/**
	 * ID: 1010053<br>
	 * Message: コロシアム
	 */
	public static final NpcStringId COLISEUM;

	/**
	 * ID: 1010054<br>
	 * Message: モンスター レース場
	 */
	public static final NpcStringId MONSTER_DERBY;

	/**
	 * ID: 1010055<br>
	 * Message: 国境守備隊付近
	 */
	public static final NpcStringId NEAR_THE_FRONTIER_POST;

	/**
	 * ID: 1010056<br>
	 * Message: 胞子の海の入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_SEA_OF_SPORES;

	/**
	 * ID: 1010057<br>
	 * Message: 過去の戦場
	 */
	public static final NpcStringId AN_OLD_BATTLEFIELD;

	/**
	 * ID: 1010058<br>
	 * Message: フェアリーの谷の入口
	 */
	public static final NpcStringId ENTRANCE_TO_ENCHANTED_VALLEY;

	/**
	 * ID: 1010059<br>
	 * Message: 傲慢の塔の入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_TOWER_OF_INSOLENCE;

	/**
	 * ID: 1010060<br>
	 * Message: 火炎の沼
	 */
	public static final NpcStringId BLAZING_SWAMP;

	/**
	 * ID: 1010061<br>
	 * Message: 国立墓地の入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_CEMETERY;

	/**
	 * ID: 1010062<br>
	 * Message: 巨人たちの洞窟入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_GIANTS_CAVE;

	/**
	 * ID: 1010063<br>
	 * Message: 鏡の森の入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FOREST_OF_MIRRORS;

	/**
	 * ID: 1010064<br>
	 * Message: シーレンの封印の入口
	 */
	public static final NpcStringId THE_FORBIDDEN_GATEWAY;

	/**
	 * ID: 1010066<br>
	 * Message: タノール シレノスの野営地
	 */
	public static final NpcStringId ENTRANCE_TO_THE_TANOR_SILENOS_BARRACKS;

	/**
	 * ID: 1010067<br>
	 * Message: ドラゴンバレー
	 */
	public static final NpcStringId DRAGON_VALLEY;

	/**
	 * ID: 1010068<br>
	 * Message: 黎明の神託所
	 */
	public static final NpcStringId ORACLE_OF_DAWN;

	/**
	 * ID: 1010069<br>
	 * Message: 黄昏の神託所
	 */
	public static final NpcStringId ORACLE_OF_DUSK;

	/**
	 * ID: 1010070<br>
	 * Message: 生贄のネクロポリス
	 */
	public static final NpcStringId NECROPOLIS_OF_SACRIFICE;

	/**
	 * ID: 1010071<br>
	 * Message: 巡礼者のネクロポリス
	 */
	public static final NpcStringId THE_PILGRIMS_NECROPOLIS;

	/**
	 * ID: 1010072<br>
	 * Message: 参拝者のネクロポリス
	 */
	public static final NpcStringId NECROPOLIS_OF_WORSHIP;

	/**
	 * ID: 1010073<br>
	 * Message: 烈士のネクロポリス
	 */
	public static final NpcStringId THE_PATRIOTS_NECROPOLIS;

	/**
	 * ID: 1010074<br>
	 * Message: 苦行者のネクロポリス
	 */
	public static final NpcStringId NECROPOLIS_OF_DEVOTION;

	/**
	 * ID: 1010075<br>
	 * Message: 殉教者のネクロポリス
	 */
	public static final NpcStringId NECROPOLIS_OF_MARTYRDOM;

	/**
	 * ID: 1010076<br>
	 * Message: 使徒のネクロポリス
	 */
	public static final NpcStringId THE_DISCIPLES_NECROPOLIS;

	/**
	 * ID: 1010077<br>
	 * Message: 聖者のネクロポリス
	 */
	public static final NpcStringId THE_SAINTS_NECROPOLIS;

	/**
	 * ID: 1010078<br>
	 * Message: 異端者のカタコム
	 */
	public static final NpcStringId THE_CATACOMB_OF_THE_HERETIC;

	/**
	 * ID: 1010079<br>
	 * Message: 烙印のカタコム
	 */
	public static final NpcStringId CATACOMB_OF_THE_BRANDED;

	/**
	 * ID: 1010080<br>
	 * Message: 異教徒のカタコム
	 */
	public static final NpcStringId CATACOMB_OF_THE_APOSTATE;

	/**
	 * ID: 1010081<br>
	 * Message: 魔導のカタコム
	 */
	public static final NpcStringId CATACOMB_OF_THE_WITCH;

	/**
	 * ID: 1010082<br>
	 * Message: 凶星のカタコム
	 */
	public static final NpcStringId CATACOMB_OF_DARK_OMENS;

	/**
	 * ID: 1010083<br>
	 * Message: 密界のカタコム
	 */
	public static final NpcStringId CATACOMB_OF_THE_FORBIDDEN_PATH;

	/**
	 * ID: 1010084<br>
	 * Message: 悲嘆の廃墟入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_RUINS_OF_AGONY;

	/**
	 * ID: 1010085<br>
	 * Message: 絶望の廃墟入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_RUINS_OF_DESPAIR;

	/**
	 * ID: 1010086<br>
	 * Message: アリの巣入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ANT_NEST;

	/**
	 * ID: 1010087<br>
	 * Message: ディオン南部
	 */
	public static final NpcStringId SOUTHERN_DION;

	/**
	 * ID: 1010088<br>
	 * Message: ドラゴンバレー入口
	 */
	public static final NpcStringId ENTRANCE_TO_DRAGON_VALLEY;

	/**
	 * ID: 1010089<br>
	 * Message: 静寂の草原
	 */
	public static final NpcStringId FIELD_OF_SILENCE;

	/**
	 * ID: 1010090<br>
	 * Message: 囁きの草原
	 */
	public static final NpcStringId FIELD_OF_WHISPERS;

	/**
	 * ID: 1010091<br>
	 * Message: クロコダイル アイランド入口
	 */
	public static final NpcStringId ENTRANCE_TO_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010092<br>
	 * Message: オーレン南部草原
	 */
	public static final NpcStringId SOUTHERN_PLAIN_OF_OREN;

	/**
	 * ID: 1010093<br>
	 * Message: 山賊の巣窟入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_BANDIT_STRONGHOLD;

	/**
	 * ID: 1010094<br>
	 * Message: 風の丘
	 */
	public static final NpcStringId WINDY_HILL;

	/**
	 * ID: 1010095<br>
	 * Message: トゥレック オークの野営地
	 */
	public static final NpcStringId ORC_BARRACKS;

	/**
	 * ID: 1010096<br>
	 * Message: フェルメル採集場
	 */
	public static final NpcStringId FELLMERE_HARVESTING_GROUNDS;

	/**
	 * ID: 1010097<br>
	 * Message: 悲嘆の廃墟
	 */
	public static final NpcStringId RUINS_OF_AGONY;

	/**
	 * ID: 1010098<br>
	 * Message: 棄てられた露営地
	 */
	public static final NpcStringId ABANDONED_CAMP;

	/**
	 * ID: 1010099<br>
	 * Message: 赤い岩の尾根
	 */
	public static final NpcStringId RED_ROCK_RIDGE;

	/**
	 * ID: 1010100<br>
	 * Message: ランク リザードマン生息地
	 */
	public static final NpcStringId LANGK_LIZARDMAN_DWELLINGS;

	/**
	 * ID: 1010101<br>
	 * Message: 絶望の廃墟
	 */
	public static final NpcStringId RUINS_OF_DESPAIR;

	/**
	 * ID: 1010102<br>
	 * Message: ウィンダ ウッドの荘園
	 */
	public static final NpcStringId WINDAWOOD_MANOR;

	/**
	 * ID: 1010103<br>
	 * Message: 荒地の北側入口
	 */
	public static final NpcStringId NORTHERN_PATHWAY_TO_THE_WASTELANDS;

	/**
	 * ID: 1010104<br>
	 * Message: 荒地の西側入口
	 */
	public static final NpcStringId WESTERN_PATHWAY_TO_THE_WASTELANDS;

	/**
	 * ID: 1010105<br>
	 * Message: 荒地の南側入口
	 */
	public static final NpcStringId SOUTHERN_PATHWAY_TO_THE_WASTELANDS;

	/**
	 * ID: 1010106<br>
	 * Message: 忘れられた神殿
	 */
	public static final NpcStringId FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010107<br>
	 * Message: アリの巣の南側入口
	 */
	public static final NpcStringId SOUTH_ENTRANCE_OF_ANT_NEST;

	/**
	 * ID: 1010108<br>
	 * Message: アリの巣の東側入口
	 */
	public static final NpcStringId EAST_ENTRANCE_OF_ANT_NEST;

	/**
	 * ID: 1010109<br>
	 * Message: アリの巣の西側入口
	 */
	public static final NpcStringId WEST_ENTRANCE_OF_ANT_NEST;

	/**
	 * ID: 1010110<br>
	 * Message: クルマ湿地
	 */
	public static final NpcStringId CRUMA_MARSHLAND;

	/**
	 * ID: 1010111<br>
	 * Message: ディオン牧草地
	 */
	public static final NpcStringId PLAINS_OF_DION;

	/**
	 * ID: 1010112<br>
	 * Message: ビーハイヴ
	 */
	public static final NpcStringId BEE_HIVE;

	/**
	 * ID: 1010113<br>
	 * Message: パルチザンのアジト
	 */
	public static final NpcStringId FORTRESS_OF_RESISTANCE;

	/**
	 * ID: 1010114<br>
	 * Message: 処刑場
	 */
	public static final NpcStringId EXECUTION_GROUNDS;

	/**
	 * ID: 1010115<br>
	 * Message: タノール峡谷
	 */
	public static final NpcStringId TANOR_CANYON;

	/**
	 * ID: 1010116<br>
	 * Message: クルマの塔
	 */
	public static final NpcStringId CRUMA_TOWER;

	/**
	 * ID: 1010117<br>
	 * Message: ドラゴンバレーの三叉路
	 */
	public static final NpcStringId THREE_WAY_CROSSROADS_AT_DRAGON_VALLEY;

	/**
	 * ID: 1010118<br>
	 * Message: ブレカの巣窟
	 */
	public static final NpcStringId BREKAS_STRONGHOLD;

	/**
	 * ID: 1010119<br>
	 * Message: ゴルコンの花園
	 */
	public static final NpcStringId GORGON_FLOWER_GARDEN;

	/**
	 * ID: 1010120<br>
	 * Message: ドラゴンバレーのダンジョン
	 */
	public static final NpcStringId ANTHARASS_LAIR;

	/**
	 * ID: 1010121<br>
	 * Message: 胞子の海
	 */
	public static final NpcStringId SEA_OF_SPORES;

	/**
	 * ID: 1010122<br>
	 * Message: 無法者の森
	 */
	public static final NpcStringId OUTLAW_FOREST;

	/**
	 * ID: 1010123<br>
	 * Message: 魔導の森と象牙の塔
	 */
	public static final NpcStringId FOREST_OF_EVIL_AND_THE_IVORY_TOWER;

	/**
	 * ID: 1010124<br>
	 * Message: ティマック アウトポスト
	 */
	public static final NpcStringId TIMAK_OUTPOST;

	/**
	 * ID: 1010125<br>
	 * Message: オーレン大平原
	 */
	public static final NpcStringId GREAT_PLAINS_OF_OREN;

	/**
	 * ID: 1010126<br>
	 * Message: 錬金術師の小屋
	 */
	public static final NpcStringId ALCHEMISTS_HUT;

	/**
	 * ID: 1010127<br>
	 * Message: 過去の戦場
	 */
	public static final NpcStringId ANCIENT_BATTLEGROUND;

	/**
	 * ID: 1010128<br>
	 * Message: フェアリーの谷の北側入口
	 */
	public static final NpcStringId NORTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY;

	/**
	 * ID: 1010129<br>
	 * Message: フェアリーの谷の南側入口
	 */
	public static final NpcStringId SOUTHERN_PATHWAY_OF_THE_ENCHANTED_VALLEY;

	/**
	 * ID: 1010130<br>
	 * Message: 猟師の渓谷
	 */
	public static final NpcStringId HUNTERS_VALLEY;

	/**
	 * ID: 1010131<br>
	 * Message: 火炎の沼の西側入口
	 */
	public static final NpcStringId WESTERN_ENTRANCE_OF_BLAZING_SWAMP;

	/**
	 * ID: 1010132<br>
	 * Message: 火炎の沼の東側入口
	 */
	public static final NpcStringId EASTERN_ENTRANCE_OF_BLAZING_SWAMP;

	/**
	 * ID: 1010133<br>
	 * Message: 栄光の平原
	 */
	public static final NpcStringId PLAINS_OF_GLORY;

	/**
	 * ID: 1010134<br>
	 * Message: 激戦の平原
	 */
	public static final NpcStringId WAR_TORN_PLAINS;

	/**
	 * ID: 1010135<br>
	 * Message: 鏡の森の北西側入口
	 */
	public static final NpcStringId NORTHWESTERN_PASSAGE_TO_THE_FOREST_OF_MIRRORS;

	/**
	 * ID: 1010136<br>
	 * Message: アンヘル滝の前
	 */
	public static final NpcStringId THE_FRONT_OF_ANGHEL_WATERFALL;

	/**
	 * ID: 1010137<br>
	 * Message: 破壊された城砦の南側入口
	 */
	public static final NpcStringId SOUTH_ENTRANCE_OF_DEVASTATED_CASTLE;

	/**
	 * ID: 1010138<br>
	 * Message: 破壊された城砦の北側
	 */
	public static final NpcStringId NORTH_ENTRANCE_OF_DEVASTATED_CASTLE;

	/**
	 * ID: 1010139<br>
	 * Message: 国立墓地の北側入口
	 */
	public static final NpcStringId NORTH_ENTRANCE_OF_THE_CEMETERY;

	/**
	 * ID: 1010140<br>
	 * Message: 国立墓地の南側入口
	 */
	public static final NpcStringId SOUTH_ENTRANCE_OF_THE_CEMETERY;

	/**
	 * ID: 1010141<br>
	 * Message: 国立墓地の西側入口
	 */
	public static final NpcStringId WEST_ENTRANCE_OF_THE_CEMETERY;

	/**
	 * ID: 1010142<br>
	 * Message: シーレンの封印の入口
	 */
	public static final NpcStringId ENTRANCE_OF_THE_FORBIDDEN_GATEWAY;

	/**
	 * ID: 1010143<br>
	 * Message: 忘却の平原
	 */
	public static final NpcStringId FORSAKEN_PLAINS;

	/**
	 * ID: 1010144<br>
	 * Message: 傲慢の塔
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE;

	/**
	 * ID: 1010145<br>
	 * Message: 巨人たちの洞窟
	 */
	public static final NpcStringId THE_GIANTS_CAVE;

	/**
	 * ID: 1010146<br>
	 * Message: 静寂の草原の北側地帯
	 */
	public static final NpcStringId NORTHERN_PART_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010147<br>
	 * Message: 静寂の草原の西側地帯
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010148<br>
	 * Message: 囁きの草原の東側地帯
	 */
	public static final NpcStringId EASTERN_PART_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010149<br>
	 * Message: 囁きの草原の西側地帯
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_FIELD_OF_WHISPERS;

	/**
	 * ID: 1010150<br>
	 * Message: クロコダイル アイランド
	 */
	public static final NpcStringId ALLIGATOR_ISLAND;

	/**
	 * ID: 1010151<br>
	 * Message: クロコダイルの白浜
	 */
	public static final NpcStringId ALLIGATOR_BEACH;

	/**
	 * ID: 1010152<br>
	 * Message: 悪魔の島
	 */
	public static final NpcStringId DEVILS_ISLE;

	/**
	 * ID: 1010153<br>
	 * Message: エヴァの水中庭園
	 */
	public static final NpcStringId GARDEN_OF_EVA;

	/**
	 * ID: 1010154<br>
	 * Message: 話せる島
	 */
	public static final NpcStringId TALKING_ISLAND;

	/**
	 * ID: 1010155<br>
	 * Message: エルフ村
	 */
	public static final NpcStringId ELVEN_VILLAGE;

	/**
	 * ID: 1010156<br>
	 * Message: ダークエルフ村
	 */
	public static final NpcStringId DARK_ELF_VILLAGE;

	/**
	 * ID: 1010159<br>
	 * Message: アイリス湖展望台
	 */
	public static final NpcStringId SCENIC_DECK_OF_IRIS_LAKE;

	/**
	 * ID: 1010160<br>
	 * Message: 成人式の祭壇
	 */
	public static final NpcStringId ALTAR_OF_RITES;

	/**
	 * ID: 1010161<br>
	 * Message: ダークエルフの森、滝
	 */
	public static final NpcStringId DARK_FOREST_WATERFALL;

	/**
	 * ID: 1010162<br>
	 * Message: 中立地帯の三叉路
	 */
	public static final NpcStringId THREE_WAY_CROSSROADS_OF_THE_NEUTRAL_ZONE;

	/**
	 * ID: 1010163<br>
	 * Message: ダークエルフの森
	 */
	public static final NpcStringId DARK_FOREST;

	/**
	 * ID: 1010164<br>
	 * Message: 沼地帯
	 */
	public static final NpcStringId SWAMPLAND;

	/**
	 * ID: 1010165<br>
	 * Message: 黒い岩の丘
	 */
	public static final NpcStringId BLACK_ROCK_HILL;

	/**
	 * ID: 1010166<br>
	 * Message: クモの巣
	 */
	public static final NpcStringId SPIDER_NEST;

	/**
	 * ID: 1010167<br>
	 * Message: エルフの森
	 */
	public static final NpcStringId ELVEN_FOREST;

	/**
	 * ID: 1010168<br>
	 * Message: 戦勝記念塔
	 */
	public static final NpcStringId OBELISK_OF_VICTORY;

	/**
	 * ID: 1010169<br>
	 * Message: 話せる島の北側
	 */
	public static final NpcStringId NORTHERN_TERRITORY_OF_TALKING_ISLAND;

	/**
	 * ID: 1010170<br>
	 * Message: 話せる島の南側
	 */
	public static final NpcStringId SOUTHERN_TERRITORY_OF_TALKING_ISLAND;

	/**
	 * ID: 1010171<br>
	 * Message: 魔物の略奪地
	 */
	public static final NpcStringId EVIL_HUNTING_GROUNDS;

	/**
	 * ID: 1010172<br>
	 * Message: メル リザードマン駐屯地
	 */
	public static final NpcStringId MAILLE_LIZARDMEN_BARRACKS;

	/**
	 * ID: 1010173<br>
	 * Message: 悲嘆の廃墟に向かう道
	 */
	public static final NpcStringId RUINS_OF_AGONY_BEND;

	/**
	 * ID: 1010174<br>
	 * Message: 絶望の廃墟に向かう道
	 */
	public static final NpcStringId THE_ENTRANCE_TO_THE_RUINS_OF_DESPAIR;

	/**
	 * ID: 1010175<br>
	 * Message: 風車の丘
	 */
	public static final NpcStringId WINDMILL_HILL;

	/**
	 * ID: 1010177<br>
	 * Message: フローラン開拓地
	 */
	public static final NpcStringId FLORAN_AGRICULTURAL_AREA;

	/**
	 * ID: 1010178<br>
	 * Message: タノール峡谷の西側
	 */
	public static final NpcStringId WESTERN_TANOR_CANYON;

	/**
	 * ID: 1010179<br>
	 * Message: リザード プレイン
	 */
	public static final NpcStringId PLAINS_OF_THE_LIZARDMEN;

	/**
	 * ID: 1010180<br>
	 * Message: 魔導の森
	 */
	public static final NpcStringId FOREST_OF_EVIL;

	/**
	 * ID: 1010181<br>
	 * Message: 虐殺の大地
	 */
	public static final NpcStringId FIELDS_OF_MASSACRE;

	/**
	 * ID: 1010182<br>
	 * Message: サイレント バレー
	 */
	public static final NpcStringId SILENT_VALLEY;

	/**
	 * ID: 1010183<br>
	 * Message: 不滅の高原北部の北側
	 */
	public static final NpcStringId NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION;

	/**
	 * ID: 1010184<br>
	 * Message: 不滅の高原北部の南側
	 */
	public static final NpcStringId SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_NORTHERN_REGION;

	/**
	 * ID: 1010185<br>
	 * Message: 不滅の高原南部の北側
	 */
	public static final NpcStringId NORTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION;

	/**
	 * ID: 1010186<br>
	 * Message: 不滅の高原南部の南側
	 */
	public static final NpcStringId SOUTHERN_AREA_OF_THE_IMMORTAL_PLATEAU_SOUTHERN_REGION;

	/**
	 * ID: 1010187<br>
	 * Message: 鉱山地帯西部
	 */
	public static final NpcStringId WESTERN_MINING_ZONE;

	/**
	 * ID: 1010190<br>
	 * Message: 捨てられた炭鉱入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ABANDONED_COAL_MINES;

	/**
	 * ID: 1010191<br>
	 * Message: ミスリル鉱山入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_MITHRIL_MINES;

	/**
	 * ID: 1010192<br>
	 * Message: 破壊された城砦の西側
	 */
	public static final NpcStringId WEST_AREA_OF_THE_DEVASTATED_CASTLE;

	/**
	 * ID: 1010193<br>
	 * Message: 傲慢の塔3階
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_3RD_FLOOR;

	/**
	 * ID: 1010195<br>
	 * Message: 傲慢の塔7階
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_7TH_FLOOR;

	/**
	 * ID: 1010197<br>
	 * Message: 傲慢の塔13階
	 */
	public static final NpcStringId TOWER_OF_INSOLENCE_13TH_FLOOR;

	/**
	 * ID: 1010199<br>
	 * Message: ゴダード城の村
	 */
	public static final NpcStringId TOWN_OF_GODDARD;

	/**
	 * ID: 1010200<br>
	 * Message: ルウン城の村
	 */
	public static final NpcStringId RUNE_TOWNSHIP;

	/**
	 * ID: 1010201<br>
	 * Message: レクターさんにお届けですね？はい、かしこまりました～
	 */
	public static final NpcStringId A_DELIVERY_FOR_MR_LECTOR_VERY_GOOD;

	/**
	 * ID: 1010202<br>
	 * Message: ふう、ちょっと休もうっと～
	 */
	public static final NpcStringId I_NEED_A_BREAK;

	/**
	 * ID: 1010203<br>
	 * Message: こんにちは、レクターさん！ジャクソンさんもご無沙汰してます！
	 */
	public static final NpcStringId HELLO_MR_LECTOR_LONG_TIME_NO_SEE_MR_JACKSON;

	/**
	 * ID: 1010204<br>
	 * Message: ルンルン～
	 */
	public static final NpcStringId LULU;

	/**
	 * ID: 1010205<br>
	 * Message: 一体どこに行ったんだろう？
	 */
	public static final NpcStringId WHERE_HAS_HE_GONE;

	/**
	 * ID: 1010206<br>
	 * Message: あの、ウィンダウッド様を見かけませんでしたか。
	 */
	public static final NpcStringId HAVE_YOU_SEEN_WINDAWOOD;

	/**
	 * ID: 1010207<br>
	 * Message: 一体どこにいらっしゃるんだろう？
	 */
	public static final NpcStringId WHERE_DID_HE_GO;

	/**
	 * ID: 1010208<br>
	 * Message: 世界樹がだんだん枯れていく．．．
	 */
	public static final NpcStringId THE_MOTHER_TREE_IS_SLOWLY_DYING;

	/**
	 * ID: 1010209<br>
	 * Message: 世界樹を元気にする方法はないかな？
	 */
	public static final NpcStringId HOW_CAN_WE_SAVE_THE_MOTHER_TREE;

	/**
	 * ID: 1010210<br>
	 * Message: いつ見ても世界樹は美しい．．．
	 */
	public static final NpcStringId THE_MOTHER_TREE_IS_ALWAYS_SO_GORGEOUS;

	/**
	 * ID: 1010211<br>
	 * Message: ミンティエル様！汝に湖畔の平和のあらんことを！
	 */
	public static final NpcStringId LADY_MIRABEL_MAY_THE_PEACE_OF_THE_LAKE_BE_WITH_YOU;

	/**
	 * ID: 1010212<br>
	 * Message: お疲れ様です、ライラ様！
	 */
	public static final NpcStringId YOURE_A_HARD_WORKER_RAYLA;

	/**
	 * ID: 1010213<br>
	 * Message: お疲れ様です！
	 */
	public static final NpcStringId YOURE_A_HARD_WORKER;

	/**
	 * ID: 1010214<br>
	 * Message: 数日後には闇の礼拝が始まります。警備の手を緩めないでくださいね！
	 */
	public static final NpcStringId THE_MASS_OF_DARKNESS_WILL_START_IN_A_COUPLE_OF_DAYS_PAY_MORE_ATTENTION_TO_THE_GUARD;

	/**
	 * ID: 1010215<br>
	 * Message: 今日、トロッコを見かけませんでしたか。
	 */
	public static final NpcStringId HAVE_YOU_SEEN_TOROCCO_TODAY;

	/**
	 * ID: 1010216<br>
	 * Message: もしかして、トロッコを見かけませんでしたか。
	 */
	public static final NpcStringId HAVE_YOU_SEEN_TOROCCO;

	/**
	 * ID: 1010217<br>
	 * Message: すばしっこいやつめ！どこに隠れたんだ？
	 */
	public static final NpcStringId WHERE_IS_THAT_FOOL_HIDING;

	/**
	 * ID: 1010218<br>
	 * Message: さあ、それじゃあひと回りしてみようか。
	 */
	public static final NpcStringId CARE_TO_GO_A_ROUND;

	/**
	 * ID: 1010219<br>
	 * Message: ガリタさん！ミオンさん！よい一日を！
	 */
	public static final NpcStringId HAVE_A_NICE_DAY_MR_GARITA_AND_MION;

	/**
	 * ID: 1010220<br>
	 * Message: リードさん！マーフリンさん！エアリーさん！お元気ですか。
	 */
	public static final NpcStringId MR_LID_MURDOC_AND_AIRY_HOW_ARE_YOU_DOING;

	/**
	 * ID: 1010221<br>
	 * Message: 黒い月．．．フフフフ．．．あのお方が目を覚まされたことがわかるか。
	 */
	public static final NpcStringId A_BLACK_MOON_NOW_DO_YOU_UNDERSTAND_THAT_HE_HAS_OPENED_HIS_EYES;

	/**
	 * ID: 1010222<br>
	 * Message: 血の雲が押し寄せてきているそうな．．．フフフ．．．明日には雨が降るだろう．．．血の雨がな．．．
	 */
	public static final NpcStringId CLOUDS_OF_BLOOD_ARE_GATHERING_SOON_IT_WILL_START_TO_RAIN_THE_RAIN_OF_CRIMSON_BLOOD;

	/**
	 * ID: 1010223<br>
	 * Message: 愚かな光が眠りについている頃、闇が目を覚ますとはな．．．フフフ．．．
	 */
	public static final NpcStringId WHILE_THE_FOOLISH_LIGHT_WAS_ASLEEP_THE_DARKNESS_WILL_AWAKEN_FIRST_UH_HUH_HUH;

	/**
	 * ID: 1010224<br>
	 * Message: 最も奥深くの闇だと？そんなものが来たらこの世は終わりだな．．．フフフ．．．
	 */
	public static final NpcStringId IT_IS_THE_DEEPEST_DARKNESS_WITH_ITS_ARRIVAL_THE_WORLD_WILL_SOON_DIE;

	/**
	 * ID: 1010225<br>
	 * Message: 死はまた新たな始まりを指す。フフフ．．．恐れるな。
	 */
	public static final NpcStringId DEATH_IS_JUST_A_NEW_BEGINNING_HUHU_FEAR_NOT;

	/**
	 * ID: 1010226<br>
	 * Message: ああ、死の女神よ！この汚れきった世の中を闇で覆い尽くしたまえ！
	 */
	public static final NpcStringId AHH_BEAUTIFUL_GODDESS_OF_DEATH_COVER_OVER_THE_FILTH_OF_THIS_WORLD_WITH_YOUR_DARKNESS;

	/**
	 * ID: 1010227<br>
	 * Message: すでに女神の復活が始まっている。フフフ．．．ザコのおまえらがあがいたところで無駄だ！
	 */
	public static final NpcStringId THE_GODDESSS_RESURRECTION_HAS_ALREADY_BEGUN_HUHU_INSIGNIFICANT_CREATURES_LIKE_YOU_CAN_DO_NOTHING;

	/**
	 * ID: 1010400<br>
	 * Message: ケロケロッ！こんなところに$s1があるなんて！？
	 */
	public static final NpcStringId CROAK_CROAK_FOOD_LIKE_S1_IN_THIS_PLACE;

	/**
	 * ID: 1010401<br>
	 * Message: $s1、やったね！
	 */
	public static final NpcStringId S1_HOW_LUCKY_I_AM;

	/**
	 * ID: 1010402<br>
	 * Message: 釣り間違えたって言えよ、$s1！
	 */
	public static final NpcStringId PRAY_THAT_YOU_CAUGHT_A_WRONG_FISH_S1;

	/**
	 * ID: 1010403<br>
	 * Message: カエルを甘く見ちゃいかんよ。
	 */
	public static final NpcStringId DO_YOU_KNOW_WHAT_A_FROG_TASTES_LIKE;

	/**
	 * ID: 1010404<br>
	 * Message: カエルの力を見せてやろう！
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_A_FROG;

	/**
	 * ID: 1010405<br>
	 * Message: 一口で飲み込んでやる！
	 */
	public static final NpcStringId I_WILL_SWALLOW_AT_A_MOUTHFUL;

	/**
	 * ID: 1010406<br>
	 * Message: ゴフッ．．．このオレ様が死ぬなんて！
	 */
	public static final NpcStringId UGH_NO_CHANCE_HOW_COULD_THIS_ELDER_PASS_AWAY_LIKE_THIS;

	/**
	 * ID: 1010407<br>
	 * Message: ゲコゲコ！カエル殺し！
	 */
	public static final NpcStringId CROAK_CROAK_A_FROG_IS_DYING;

	/**
	 * ID: 1010408<br>
	 * Message: カエルは美味しくないですよ！あわわ。
	 */
	public static final NpcStringId A_FROG_TASTES_BAD_YUCK;

	/**
	 * ID: 1010409<br>
	 * Message: きゃあっ！$s1、何するのっ？
	 */
	public static final NpcStringId KAAK_S1_WHAT_ARE_YOU_DOING_NOW;

	/**
	 * ID: 1010410<br>
	 * Message: ふふっ、$s1精霊の体に傷をつけるなんて。覚悟はできているんでしょうね？
	 */
	public static final NpcStringId HUH_S1_YOU_PIERCED_INTO_THE_BODY_OF_THE_SPIRIT_WITH_A_NEEDLE_ARE_YOU_READY;

	/**
	 * ID: 1010411<br>
	 * Message: あら、$s1 あなただったのね。でもね、こんな荒っぽい招待に喜ぶ女なんていないわよ？
	 */
	public static final NpcStringId OOH_S1_THATS_YOU_BUT_NO_LADY_IS_PLEASED_WITH_THIS_SAVAGE_INVITATION;

	/**
	 * ID: 1010412<br>
	 * Message: あたしを怒らせたわね！
	 */
	public static final NpcStringId YOU_MADE_ME_ANGRY;

	/**
	 * ID: 1010413<br>
	 * Message: この傷、どうしてくれるのよっ！？
	 */
	public static final NpcStringId IT_IS_BUT_A_SCRATCH_IS_THAT_ALL_YOU_CAN_DO;

	/**
	 * ID: 1010414<br>
	 * Message: あんたも同じ目にあわせてやる！えいっ！
	 */
	public static final NpcStringId FEEL_MY_PAIN;

	/**
	 * ID: 1010415<br>
	 * Message: きゃあっ！そうきたわね、覚えてらっしゃい！
	 */
	public static final NpcStringId ILL_GET_YOU_FOR_THIS;

	/**
	 * ID: 1010416<br>
	 * Message: あなたのエサは食べるなって、他の魚たちに言いふらしてやる！
	 */
	public static final NpcStringId I_WILL_TELL_FISH_NOT_TO_TAKE_YOUR_BAIT;

	/**
	 * ID: 1010417<br>
	 * Message: あたしみたいなか弱い精霊をいじめるなんて．．．うっうっ。
	 */
	public static final NpcStringId YOU_BOTHERED_SUCH_A_WEAK_SPIRITHUH_HUH;

	/**
	 * ID: 1010418<br>
	 * Message: ケケケ．．．$s1．．．食べるぞ．．．ケケッ．．．
	 */
	public static final NpcStringId KE_KE_KES1IM_EATINGKE;

	/**
	 * ID: 1010419<br>
	 * Message: グルル．．．う．．．$s1．．．うらめしや．．．魚ども．．．
	 */
	public static final NpcStringId KUHOOHS1ENMITYFISH;

	/**
	 * ID: 1010420<br>
	 * Message: $s1？ケホッ．．．グル．．．グルッ．．．
	 */
	public static final NpcStringId S1_HUH_HUHHUH;

	/**
	 * ID: 1010421<br>
	 * Message: クケケケケ！ラクル！スピン！アタアアアアアック！
	 */
	public static final NpcStringId KE_KE_KE_RAKUL_SPIN_EH_EH_EH;

	/**
	 * ID: 1010422<br>
	 * Message: おおっ！パプリオン！うおっ！うおっ！
	 */
	public static final NpcStringId AH_FAFURION_AH_AH;

	/**
	 * ID: 1010423<br>
	 * Message: ラクル！ラクル！ラァァクゥゥルゥゥ！
	 */
	public static final NpcStringId RAKUL_RAKUL_RA_KUL;

	/**
	 * ID: 1010424<br>
	 * Message: クエェ．．．恨み．．．魚．．．
	 */
	public static final NpcStringId EHENMITYFISH;

	/**
	 * ID: 1010425<br>
	 * Message: 食われたくない．．．グアァ
	 */
	public static final NpcStringId I_WONT_BE_EATEN_UPKAH_AH_AH;

	/**
	 * ID: 1010426<br>
	 * Message: グハッ！ラクル！ケホッケホッ！グエェ．．．
	 */
	public static final NpcStringId COUGH_RAKUL_COUGH_COUGH_KEH;

	/**
	 * ID: 1010427<br>
	 * Message: パプリオンに栄光あれ！$s1には死を！
	 */
	public static final NpcStringId GLORY_TO_FAFURION_DEATH_TO_S1;

	/**
	 * ID: 1010428<br>
	 * Message: $s1、お前か！うちの可愛い魚たちをいじめてるのは！
	 */
	public static final NpcStringId S1_YOU_ARE_THE_ONE_WHO_BOTHERED_MY_POOR_FISH;

	/**
	 * ID: 1010429<br>
	 * Message: 水竜様！$s1に呪いを！
	 */
	public static final NpcStringId FAFURION_A_CURSE_UPON_S1;

	/**
	 * ID: 1010430<br>
	 * Message: ジャイアント スペシャル アタック！
	 */
	public static final NpcStringId GIANT_SPECIAL_ATTACK;

	/**
	 * ID: 1010431<br>
	 * Message: 魚たちの恨みを思い知れ！
	 */
	public static final NpcStringId KNOW_THE_ENMITY_OF_FISH;

	/**
	 * ID: 1010432<br>
	 * Message: 俺の槍を食らってみろ！
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_A_SPEAR;

	/**
	 * ID: 1010433<br>
	 * Message: パプリオン様に栄光あれ！
	 */
	public static final NpcStringId GLORY_TO_FAFURION;

	/**
	 * ID: 1010434<br>
	 * Message: ぐはああ！
	 */
	public static final NpcStringId YIPES;

	/**
	 * ID: 1010435<br>
	 * Message: 老兵は死なず．．．ただ消え去るのみ．．．
	 */
	public static final NpcStringId AN_OLD_SOLDIER_DOES_NOT_DIE_BUT_JUST_DISAPPEAR;

	/**
	 * ID: 1010436<br>
	 * Message: $s1、この水の騎士の挑戦を受けろ！
	 */
	public static final NpcStringId S1_TAKE_MY_CHALLENGE_THE_KNIGHT_OF_WATER;

	/**
	 * ID: 1010437<br>
	 * Message: 魚たちの報告にあった$s1発見！
	 */
	public static final NpcStringId DISCOVER_S1_IN_THE_TREASURE_CHEST_OF_FISH;

	/**
	 * ID: 1010438<br>
	 * Message: $s1、私がくわえたのはあんたのエサだったのか！
	 */
	public static final NpcStringId S1_I_TOOK_YOUR_BAIT;

	/**
	 * ID: 1010439<br>
	 * Message: 龍宮城仕込みの槍術をご覧あれ！
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_SPEARMANSHIP_USED_IN_DRAGON_KINGS_PALACE;

	/**
	 * ID: 1010440<br>
	 * Message: あんたに贈る最期のプレゼントだぜ！
	 */
	public static final NpcStringId THIS_IS_THE_LAST_GIFT_I_GIVE_YOU;

	/**
	 * ID: 1010441<br>
	 * Message: あんたのエサは旨かったよ！じゃあな！
	 */
	public static final NpcStringId YOUR_BAIT_WAS_TOO_DELICIOUS_NOW_I_WILL_KILL_YOU;

	/**
	 * ID: 1010442<br>
	 * Message: 悔しい！我が同胞の恨みを！
	 */
	public static final NpcStringId WHAT_A_REGRET_THE_ENMITY_OF_MY_BRETHREN;

	/**
	 * ID: 1010443<br>
	 * Message: 覚えてろ！この仇はきっと誰かが．．．
	 */
	public static final NpcStringId ILL_PAY_YOU_BACK_SOMEBODY_WILL_HAVE_MY_REVENGE;

	/**
	 * ID: 1010444<br>
	 * Message: グフッ．．．しかしお前には捕まらないぞ！
	 */
	public static final NpcStringId COUGH_BUT_I_WONT_BE_EATEN_UP_BY_YOU;

	/**
	 * ID: 1010445<br>
	 * Message: $s1、すげぇぞ．．．
	 */
	public static final NpcStringId _S1_I_WILL_KILL_YOU;

	/**
	 * ID: 1010446<br>
	 * Message: $s1、深海にいる俺を釣り上げるなんて．．．
	 */
	public static final NpcStringId S1_HOW_COULD_YOU_CATCH_ME_FROM_THE_DEEP_SEA;

	/**
	 * ID: 1010447<br>
	 * Message: $s1、俺が魚に見えるか。
	 */
	public static final NpcStringId S1_DO_YOU_THINK_I_AM_A_FISH;

	/**
	 * ID: 1010448<br>
	 * Message: はらほろひれ～
	 */
	public static final NpcStringId EBIBIBI;

	/**
	 * ID: 1010449<br>
	 * Message: クククッ、どれ、こんがり焼いてやろうか。
	 */
	public static final NpcStringId HE_HE_HE_DO_YOU_WANT_ME_TO_ROAST_YOU_WELL;

	/**
	 * ID: 1010450<br>
	 * Message: 水から出たからって油断してたんだろ？
	 */
	public static final NpcStringId YOU_DIDNT_KEEP_YOUR_EYES_ON_ME_BECAUSE_I_COME_FROM_THE_SEA;

	/**
	 * ID: 1010451<br>
	 * Message: うひゃ．．．痛い．．．いててててっ！
	 */
	public static final NpcStringId EEEK_I_FEEL_SICKYOW;

	/**
	 * ID: 1010452<br>
	 * Message: チェッ、失敗か．．．せっかく見つけたのに。
	 */
	public static final NpcStringId I_HAVE_FAILED;

	/**
	 * ID: 1010453<br>
	 * Message: 生命活動．．．停止．．．ジジッ．．．
	 */
	public static final NpcStringId ACTIVITY_OF_LIFE_IS_STOPPED_CHIZIFC;

	/**
	 * ID: 1010454<br>
	 * Message: クルルッ！$s1～クルッポー。
	 */
	public static final NpcStringId GROWLING_S1_GROWLING;

	/**
	 * ID: 1010455<br>
	 * Message: $s1の匂いがするぞぅ！
	 */
	public static final NpcStringId I_CAN_SMELL_S1;

	/**
	 * ID: 1010456<br>
	 * Message: 美味しそうだな、$s1！
	 */
	public static final NpcStringId LOOKS_DELICIOUS_S1;

	/**
	 * ID: 1010457<br>
	 * Message: 食ってやるぞおおお！
	 */
	public static final NpcStringId I_WILL_CATCH_YOU;

	/**
	 * ID: 1010458<br>
	 * Message: うへへへ、久しぶりにありつくエサだな！
	 */
	public static final NpcStringId UAH_AH_AH_I_COULDNT_EAT_ANYTHING_FOR_A_LONG_TIME;

	/**
	 * ID: 1010459<br>
	 * Message: お前なんか一口さ！
	 */
	public static final NpcStringId I_CAN_SWALLOW_YOU_AT_A_MOUTHFUL;

	/**
	 * ID: 1010460<br>
	 * Message: ぐお．．．エサにやられるなんて！
	 */
	public static final NpcStringId WHAT_I_AM_DEFEATED_BY_THE_PREY;

	/**
	 * ID: 1010461<br>
	 * Message: お前は俺のエサなんだよ！捕って食ってやる！
	 */
	public static final NpcStringId YOU_ARE_MY_FOOD_I_HAVE_TO_KILL_YOU;

	/**
	 * ID: 1010462<br>
	 * Message: エサに食われるなんて．．．クッ．．．
	 */
	public static final NpcStringId I_CANT_BELIEVE_I_AM_EATEN_UP_BY_MY_PREY_GAH;

	/**
	 * ID: 1010463<br>
	 * Message: 俺を釣ったのは$s1？
	 */
	public static final NpcStringId YOU_CAUGHT_ME_S1;

	/**
	 * ID: 1010464<br>
	 * Message: 俺に会えたことを光栄に思えよ、$s1。
	 */
	public static final NpcStringId YOURE_LUCKY_TO_HAVE_EVEN_SEEN_ME_S1;

	/**
	 * ID: 1010465<br>
	 * Message: $s1、生きて帰れるなんてこれっぽっちも思わないほうがいいぞ。
	 */
	public static final NpcStringId S1_YOU_CANT_LEAVE_HERE_ALIVE_GIVE_UP;

	/**
	 * ID: 1010466<br>
	 * Message: 深淵の力をとくと見ろ．．．
	 */
	public static final NpcStringId I_WILL_SHOW_YOU_THE_POWER_OF_THE_DEEP_SEA;

	/**
	 * ID: 1010467<br>
	 * Message: その釣り竿をへし折ってやる．．．
	 */
	public static final NpcStringId I_WILL_BREAK_THE_FISHING_POLE;

	/**
	 * ID: 1010468<br>
	 * Message: 貴様の屍は、この俺が美味しく頂こう．．．
	 */
	public static final NpcStringId YOUR_CORPSE_WILL_BE_GOOD_FOOD_FOR_ME;

	/**
	 * ID: 1010469<br>
	 * Message: ただの釣り師じゃなかったのか．．．
	 */
	public static final NpcStringId YOU_ARE_A_GOOD_FISHERMAN;

	/**
	 * ID: 1010470<br>
	 * Message: パプリオンが怖くないのか．．．
	 */
	public static final NpcStringId ARENT_YOU_AFRAID_OF_FAFURION;

	/**
	 * ID: 1010471<br>
	 * Message: 素晴らしい、褒めてやるぞ．．．
	 */
	public static final NpcStringId YOU_ARE_EXCELLENT;

	/**
	 * ID: 1010472<br>
	 * Message: 毒装置が作動しました。
	 */
	public static final NpcStringId THE_POISON_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010473<br>
	 * Message: 1分後、攻撃力低下装置が作動します。
	 */
	public static final NpcStringId THE_P_ATK_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_1_MINUTE;

	/**
	 * ID: 1010474<br>
	 * Message: 2分後、防御力低下装置が作動します。
	 */
	public static final NpcStringId THE_DEFENSE_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_2_MINUTES;

	/**
	 * ID: 1010475<br>
	 * Message: 3分後、HP回復減少装置が作動します。
	 */
	public static final NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_WILL_BE_ACTIVATED_IN_3_MINUTES;

	/**
	 * ID: 1010476<br>
	 * Message: 攻撃力低下装置が作動しました。
	 */
	public static final NpcStringId THE_P_ATK_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010477<br>
	 * Message: 防御力低下装置が作動しました。
	 */
	public static final NpcStringId THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010478<br>
	 * Message: HP回復減少装置が作動しました。
	 */
	public static final NpcStringId THE_HP_REGENERATION_REDUCTION_DEVICE_HAS_BEEN_ACTIVATED;

	/**
	 * ID: 1010479<br>
	 * Message: 毒装置が破壊されました。
	 */
	public static final NpcStringId THE_POISON_DEVICE_HAS_NOW_BEEN_DESTROYED;

	/**
	 * ID: 1010480<br>
	 * Message: 攻撃力低下装置が破壊されました。
	 */
	public static final NpcStringId THE_P_ATK_REDUCTION_DEVICE_HAS_NOW_BEEN_DESTROYED;

	/**
	 * ID: 1010481<br>
	 * Message: 防御力低下装置が破壊されました。
	 */
	public static final NpcStringId THE_DEFENSE_REDUCTION_DEVICE_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1010485<br>
	 * Message: 試練の洞窟入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_CAVE_OF_TRIALS;

	/**
	 * ID: 1010486<br>
	 * Message: エルフの遺跡内部
	 */
	public static final NpcStringId INSIDE_THE_ELVEN_RUINS;

	/**
	 * ID: 1010487<br>
	 * Message: エルフの遺跡入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ELVEN_RUINS;

	/**
	 * ID: 1010488<br>
	 * Message: 黒魔法研究所入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_SCHOOL_OF_DARK_ARTS;

	/**
	 * ID: 1010489<br>
	 * Message: 黒魔法研究所中央
	 */
	public static final NpcStringId CENTER_OF_THE_SCHOOL_OF_DARK_ARTS;

	/**
	 * ID: 1010490<br>
	 * Message: エルフの地下要塞入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_ELVEN_FORTRESS;

	/**
	 * ID: 1010491<br>
	 * Message: バルカシレノス駐屯地
	 */
	public static final NpcStringId VARKA_SILENOS_STRONGHOLD;

	/**
	 * ID: 1010492<br>
	 * Message: ケトラー オーク アウトポスト
	 */
	public static final NpcStringId KETRA_ORC_OUTPOST;

	/**
	 * ID: 1010493<br>
	 * Message: ルウン城の村のギルド
	 */
	public static final NpcStringId RUNE_TOWNSHIP_GUILD;

	/**
	 * ID: 1010494<br>
	 * Message: ルウン城の村の神殿
	 */
	public static final NpcStringId RUNE_TOWNSHIP_TEMPLE;

	/**
	 * ID: 1010495<br>
	 * Message: ルウン城の村の商店
	 */
	public static final NpcStringId RUNE_TOWNSHIP_STORE;

	/**
	 * ID: 1010496<br>
	 * Message: 亡者の森入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FOREST_OF_THE_DEAD;

	/**
	 * ID: 1010497<br>
	 * Message: 悲鳴の沼西の入口
	 */
	public static final NpcStringId WESTERN_ENTRANCE_TO_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010498<br>
	 * Message: 忘れられた神殿の入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010499<br>
	 * Message: 忘れられた神殿中央
	 */
	public static final NpcStringId CENTER_OF_THE_FORGOTTEN_TEMPLE;

	/**
	 * ID: 1010500<br>
	 * Message: クルマの塔入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_CRUMA_TOWER;

	/**
	 * ID: 1010501<br>
	 * Message: クルマの塔1階
	 */
	public static final NpcStringId CRUMA_TOWER_FIRST_FLOOR;

	/**
	 * ID: 1010502<br>
	 * Message: クルマの塔2階
	 */
	public static final NpcStringId CRUMA_TOWER_SECOND_FLOOR;

	/**
	 * ID: 1010503<br>
	 * Message: クルマの塔3階
	 */
	public static final NpcStringId CRUMA_TOWER_THIRD_FLOOR;

	/**
	 * ID: 1010504<br>
	 * Message: 悪魔の島入口
	 */
	public static final NpcStringId ENTRANCE_TO_DEVILS_ISLE;

	/**
	 * ID: 1010506<br>
	 * Message: グルーディン決闘場
	 */
	public static final NpcStringId GLUDIN_ARENA;

	/**
	 * ID: 1010507<br>
	 * Message: ギラン決闘場
	 */
	public static final NpcStringId GIRAN_ARENA;

	/**
	 * ID: 1010508<br>
	 * Message: ドラゴンバレーのダンジョン入口
	 */
	public static final NpcStringId ENTRANCE_TO_ANTHARASS_LAIR;

	/**
	 * ID: 1010509<br>
	 * Message: ドラゴンバレーのダンジョン 1階
	 */
	public static final NpcStringId ANTHARASS_LAIR_1ST_LEVEL;

	/**
	 * ID: 1010510<br>
	 * Message: ドラゴンバレーのダンジョン 2階
	 */
	public static final NpcStringId ANTHARASS_LAIR_2ND_LEVEL;

	/**
	 * ID: 1010511<br>
	 * Message: ドラゴンバレーのダンジョン結界の橋
	 */
	public static final NpcStringId ANTHARASS_LAIR_MAGIC_FORCE_FIELD_BRIDGE;

	/**
	 * ID: 1010512<br>
	 * Message: ドラゴンバレーのダンジョン心臓部
	 */
	public static final NpcStringId THE_HEART_OF_ANTHARASS_LAIR;

	/**
	 * ID: 1010513<br>
	 * Message: 静寂の草原 東
	 */
	public static final NpcStringId EAST_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010514<br>
	 * Message: 静寂の草原 西
	 */
	public static final NpcStringId WEST_OF_THE_FIELD_OF_SILENCE;

	/**
	 * ID: 1010515<br>
	 * Message: 囁きの草原 東
	 */
	public static final NpcStringId EAST_OF_THE_FIELD_OF_WHISPERS;

	/**
	 * ID: 1010516<br>
	 * Message: 囁きの草原 西
	 */
	public static final NpcStringId WEST_OF_THE_FIELD_OF_WHISPERS;

	/**
	 * ID: 1010517<br>
	 * Message: エヴァの水中庭園入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_GARDEN_OF_EVA;

	/**
	 * ID: 1010520<br>
	 * Message: クロコダイル アイランド北
	 */
	public static final NpcStringId NORTHERN_PART_OF_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010521<br>
	 * Message: クロコダイル アイランド中央
	 */
	public static final NpcStringId CENTRAL_PART_OF_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010522<br>
	 * Message: エヴァの水中庭園2階
	 */
	public static final NpcStringId GARDEN_OF_EVA_2ND_LEVEL;

	/**
	 * ID: 1010523<br>
	 * Message: エヴァの水中庭園3階
	 */
	public static final NpcStringId GARDEN_OF_EVA_3RD_LEVEL;

	/**
	 * ID: 1010524<br>
	 * Message: エヴァの水中庭園4階
	 */
	public static final NpcStringId GARDEN_OF_EVA_4TH_LEVEL;

	/**
	 * ID: 1010525<br>
	 * Message: エヴァの水中庭園5階
	 */
	public static final NpcStringId GARDEN_OF_EVA_5TH_LEVEL;

	/**
	 * ID: 1010526<br>
	 * Message: エヴァの水中庭園内部
	 */
	public static final NpcStringId INSIDE_THE_GARDEN_OF_EVA;

	/**
	 * ID: 1010527<br>
	 * Message: 四大霊廟
	 */
	public static final NpcStringId FOUR_SEPULCHERS;

	/**
	 * ID: 1010528<br>
	 * Message: 帝国の墓地
	 */
	public static final NpcStringId IMPERIAL_TOMB;

	/**
	 * ID: 1010529<br>
	 * Message: 諸侯の参拝地
	 */
	public static final NpcStringId SHRINE_OF_LOYALTY;

	/**
	 * ID: 1010530<br>
	 * Message: 神々の火鉢入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_FORGE_OF_THE_GODS;

	/**
	 * ID: 1010531<br>
	 * Message: 神々の火鉢上層
	 */
	public static final NpcStringId FORGE_OF_THE_GODS_TOP_LEVEL;

	/**
	 * ID: 1010532<br>
	 * Message: 神々の火鉢下層
	 */
	public static final NpcStringId FORGE_OF_THE_GODS_LOWER_LEVEL;

	/**
	 * ID: 1010533<br>
	 * Message: アルゴスの壁入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_WALL_OF_ARGOS;

	/**
	 * ID: 1010534<br>
	 * Message: バルカ シレノス ヴィレッジ
	 */
	public static final NpcStringId VARKA_SILENOS_VILLAGE;

	/**
	 * ID: 1010535<br>
	 * Message: ケトラー オーク ヴィレッジ
	 */
	public static final NpcStringId KETRA_ORC_VILLAGE;

	/**
	 * ID: 1010536<br>
	 * Message: 温泉地帯入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_HOT_SPRINGS_REGION;

	/**
	 * ID: 1010537<br>
	 * Message: 猛獣の放牧地
	 */
	public static final NpcStringId WILD_BEAST_PASTURES;

	/**
	 * ID: 1010538<br>
	 * Message: 聖者の渓谷入口
	 */
	public static final NpcStringId ENTRANCE_TO_THE_VALLEY_OF_SAINTS;

	/**
	 * ID: 1010539<br>
	 * Message: 呪われた村
	 */
	public static final NpcStringId CURSED_VILLAGE;

	/**
	 * ID: 1010540<br>
	 * Message: 猛獣の放牧地南の入口
	 */
	public static final NpcStringId SOUTHERN_ENTRANCE_OF_THE_WILD_BEAST_PASTURES;

	/**
	 * ID: 1010541<br>
	 * Message: 猛獣の放牧地東
	 */
	public static final NpcStringId EASTERN_PART_OF_THE_WILD_BEAST_PASTURES;

	/**
	 * ID: 1010542<br>
	 * Message: 猛獣の放牧地西
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_WILD_BEAST_PASTURES;

	/**
	 * ID: 1010543<br>
	 * Message: 悲鳴の沼東
	 */
	public static final NpcStringId EASTERN_PART_OF_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010544<br>
	 * Message: 悲鳴の沼西
	 */
	public static final NpcStringId WESTERN_PART_OF_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010545<br>
	 * Message: 悲鳴の沼中央
	 */
	public static final NpcStringId CENTER_OF_THE_SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010547<br>
	 * Message: アデン方面国境関門
	 */
	public static final NpcStringId ADEN_FRONTIER_GATEWAY;

	/**
	 * ID: 1010548<br>
	 * Message: オーレン方面国境関門
	 */
	public static final NpcStringId OREN_FRONTIER_GATEWAY;

	/**
	 * ID: 1010549<br>
	 * Message: 野獣の庭園
	 */
	public static final NpcStringId GARDEN_OF_WILD_BEASTS;

	/**
	 * ID: 1010550<br>
	 * Message: 死霊の関門
	 */
	public static final NpcStringId DEVILS_PASS;

	/**
	 * ID: 1010551<br>
	 * Message: 弾薬をチャージしている最中です。
	 */
	public static final NpcStringId THE_BULLETS_ARE_BEING_LOADED;

	/**
	 * ID: 1010552<br>
	 * Message: 定刻からスタートできます。
	 */
	public static final NpcStringId YOU_CAN_START_AT_THE_SCHEDULED_TIME;

	/**
	 * ID: 1010554<br>
	 * Message: 巨人たちの洞窟上層部
	 */
	public static final NpcStringId UPPER_LEVEL_OF_THE_GIANTS_CAVE;

	/**
	 * ID: 1010555<br>
	 * Message: 巨人たちの洞窟下層部
	 */
	public static final NpcStringId LOWER_LEVEL_OF_THE_GIANTS_CAVE;

	/**
	 * ID: 1010556<br>
	 * Message: 不滅の高原北部
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_NORTHERN_REGION;

	/**
	 * ID: 1010557<br>
	 * Message: エルフの遺跡
	 */
	public static final NpcStringId ELVEN_RUINS;

	/**
	 * ID: 1010558<br>
	 * Message: 歌う滝
	 */
	public static final NpcStringId SINGING_WATERFALL;

	/**
	 * ID: 1010559<br>
	 * Message: 話せる島の西部(北側)
	 */
	public static final NpcStringId TALKING_ISLAND_NORTHERN_TERRITORY;

	/**
	 * ID: 1010560<br>
	 * Message: エルフの地下要塞
	 */
	public static final NpcStringId ELVEN_FORTRESS;

	/**
	 * ID: 1010561<br>
	 * Message: 巡礼者の寺院
	 */
	public static final NpcStringId PILGRIMS_TEMPLE;

	/**
	 * ID: 1010562<br>
	 * Message: グルーディン港
	 */
	public static final NpcStringId GLUDIN_HARBOR;

	/**
	 * ID: 1010563<br>
	 * Message: シーレンの庭園
	 */
	public static final NpcStringId SHILENS_GARDEN;

	/**
	 * ID: 1010564<br>
	 * Message: 黒魔法研究所
	 */
	public static final NpcStringId SCHOOL_OF_DARK_ARTS;

	/**
	 * ID: 1010565<br>
	 * Message: 悲鳴の沼
	 */
	public static final NpcStringId SWAMP_OF_SCREAMS;

	/**
	 * ID: 1010566<br>
	 * Message: アリの巣
	 */
	public static final NpcStringId THE_ANT_NEST;

	/**
	 * ID: 1010568<br>
	 * Message: アルゴスの壁
	 */
	public static final NpcStringId WALL_OF_ARGOS;

	/**
	 * ID: 1010569<br>
	 * Message: 悪霊の棲処
	 */
	public static final NpcStringId DEN_OF_EVIL;

	/**
	 * ID: 1010570<br>
	 * Message: 氷屋の小屋
	 */
	public static final NpcStringId ICEMANS_HUT;

	/**
	 * ID: 1010571<br>
	 * Message: 恥辱の埋葬地
	 */
	public static final NpcStringId CRYPTS_OF_DISGRACE;

	/**
	 * ID: 1010572<br>
	 * Message: 略奪の荒野
	 */
	public static final NpcStringId PLUNDEROUS_PLAINS;

	/**
	 * ID: 1010573<br>
	 * Message: パヴェルの遺跡
	 */
	public static final NpcStringId PAVEL_RUINS;

	/**
	 * ID: 1010574<br>
	 * Message: シュチュッツガルト城の村
	 */
	public static final NpcStringId TOWN_OF_SCHUTTGART;

	/**
	 * ID: 1010575<br>
	 * Message: 沈黙の修道院
	 */
	public static final NpcStringId MONASTERY_OF_SILENCE;

	/**
	 * ID: 1010576<br>
	 * Message: 沈黙の修道院裏門
	 */
	public static final NpcStringId MONASTERY_OF_SILENCE_REAR_GATE;

	/**
	 * ID: 1010577<br>
	 * Message: スタッカートの巣
	 */
	public static final NpcStringId STAKATO_NEST;

	/**
	 * ID: 1010578<br>
	 * Message: 我が領域に足を踏み入れるとはたいした度胸だ！
	 */
	public static final NpcStringId HOW_DARE_YOU_TRESPASS_INTO_MY_TERRITORY_HAVE_YOU_NO_FEAR;

	/**
	 * ID: 1010579<br>
	 * Message: 愚かな！まだうろついていたのか！痛い目にあいたいようだな！
	 */
	public static final NpcStringId FOOLS_WHY_HAVENT_YOU_FLED_YET_PREPARE_TO_LEARN_A_LESSON;

	/**
	 * ID: 1010580<br>
	 * Message: ふはは、見ておれ！ウルトラ スーパー必殺っ！秘密兵器出動だあああっ！
	 */
	public static final NpcStringId BWAH_HA_HA_YOUR_DOOM_IS_AT_HAND_BEHOLD_THE_ULTRA_SECRET_SUPER_WEAPON;

	/**
	 * ID: 1010581<br>
	 * Message: 笑止！私に挑むとは！
	 */
	public static final NpcStringId FOOLISH_INSIGNIFICANT_CREATURES_HOW_DARE_YOU_CHALLENGE_ME;

	/**
	 * ID: 1010582<br>
	 * Message: どうした？もうおしまいか。
	 */
	public static final NpcStringId I_SEE_THAT_NONE_WILL_CHALLENGE_ME_NOW;

	/**
	 * ID: 1010583<br>
	 * Message: うっ、貴様ら．．．覚えてろ．．．
	 */
	public static final NpcStringId URGGH_YOU_WILL_PAY_DEARLY_FOR_THIS_INSULT;

	/**
	 * ID: 1010584<br>
	 * Message: なんだ、一文無しか。チェッ。
	 */
	public static final NpcStringId WHAT_YOU_HAVENT_EVEN_TWO_PENNIES_TO_RUB_TOGETHER_HARUMPH;

	/**
	 * ID: 1010585<br>
	 * Message: 鏡の森
	 */
	public static final NpcStringId FOREST_OF_MIRRORS;

	/**
	 * ID: 1010586<br>
	 * Message: 鏡の森の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_FOREST_OF_MIRRORS;

	/**
	 * ID: 1010588<br>
	 * Message: 空中列車の遺跡
	 */
	public static final NpcStringId SKY_WAGON_RELIC;

	/**
	 * ID: 1010590<br>
	 * Message: ダークエルフの森の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_DARK_FOREST;

	/**
	 * ID: 1010591<br>
	 * Message: 盗掘屋の棲家
	 */
	public static final NpcStringId GRAVE_ROBBER_HIDEOUT;

	/**
	 * ID: 1010592<br>
	 * Message: 亡者の森
	 */
	public static final NpcStringId FOREST_OF_THE_DEAD;

	/**
	 * ID: 1010593<br>
	 * Message: 亡者の森の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_FOREST_OF_THE_DEAD;

	/**
	 * ID: 1010594<br>
	 * Message: ミスリル鉱山
	 */
	public static final NpcStringId MITHRIL_MINES;

	/**
	 * ID: 1010595<br>
	 * Message: ミスリル鉱山の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_MITHRIL_MINES;

	/**
	 * ID: 1010596<br>
	 * Message: 捨てられた炭鉱
	 */
	public static final NpcStringId ABANDONED_COAL_MINES;

	/**
	 * ID: 1010597<br>
	 * Message: 捨てられた炭鉱の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_ABANDONED_COAL_MINES;

	/**
	 * ID: 1010598<br>
	 * Message: 不滅の高原西部
	 */
	public static final NpcStringId IMMORTAL_PLATEAU_WESTERN_REGION;

	/**
	 * ID: 1010600<br>
	 * Message: 聖者の渓谷
	 */
	public static final NpcStringId VALLEY_OF_SAINTS;

	/**
	 * ID: 1010601<br>
	 * Message: 聖者の渓谷の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_VALLEY_OF_SAINTS;

	/**
	 * ID: 1010603<br>
	 * Message: 試練の洞窟
	 */
	public static final NpcStringId CAVE_OF_TRIALS;

	/**
	 * ID: 1010604<br>
	 * Message: シーレンの封印
	 */
	public static final NpcStringId SEAL_OF_SHILEN;

	/**
	 * ID: 1010605<br>
	 * Message: アルゴスの壁の中心
	 */
	public static final NpcStringId THE_CENTER_OF_THE_WALL_OF_ARGOS;

	/**
	 * ID: 1010606<br>
	 * Message: クロコダイル アイランドの中心
	 */
	public static final NpcStringId THE_CENTER_OF_ALLIGATOR_ISLAND;

	/**
	 * ID: 1010607<br>
	 * Message: アンヘル滝
	 */
	public static final NpcStringId ANGHEL_WATERFALL;

	/**
	 * ID: 1010608<br>
	 * Message: エルフの遺跡の中心
	 */
	public static final NpcStringId CENTER_OF_THE_ELVEN_RUINS;

	/**
	 * ID: 1010609<br>
	 * Message: 温泉地帯
	 */
	public static final NpcStringId HOT_SPRINGS;

	/**
	 * ID: 1010610<br>
	 * Message: 温泉地帯中央
	 */
	public static final NpcStringId THE_CENTER_OF_THE_HOT_SPRINGS;

	/**
	 * ID: 1010611<br>
	 * Message: ドラゴンバレー中央
	 */
	public static final NpcStringId THE_CENTER_OF_DRAGON_VALLEY;

	/**
	 * ID: 1010613<br>
	 * Message: 中立地帯中央
	 */
	public static final NpcStringId THE_CENTER_OF_THE_NEUTRAL_ZONE;

	/**
	 * ID: 1010614<br>
	 * Message: クルマ湿地
	 */
	public static final NpcStringId CRUMA_MARSHLANDS;

	/**
	 * ID: 1010615<br>
	 * Message: クルマ湿地中央
	 */
	public static final NpcStringId THE_CENTER_OF_THE_CRUMA_MARSHLANDS;

	/**
	 * ID: 1010617<br>
	 * Message: フェアリーの谷中央
	 */
	public static final NpcStringId THE_CENTER_OF_THE_ENCHANTED_VALLEY;

	/**
	 * ID: 1010618<br>
	 * Message: フェアリーの谷(南側)
	 */
	public static final NpcStringId ENCHANTED_VALLEY_SOUTHERN_REGION;

	/**
	 * ID: 1010619<br>
	 * Message: フェアリーの谷(北側)
	 */
	public static final NpcStringId ENCHANTED_VALLEY_NORTHERN_REGION;

	/**
	 * ID: 1010620<br>
	 * Message: フロスト湖
	 */
	public static final NpcStringId FROST_LAKE;

	/**
	 * ID: 1010621<br>
	 * Message: 荒地
	 */
	public static final NpcStringId WASTELANDS;

	/**
	 * ID: 1010622<br>
	 * Message: 荒地(西側)
	 */
	public static final NpcStringId WASTELANDS_WESTERN_REGION;

	/**
	 * ID: 1010623<br>
	 * Message: 我が城の玉座を狙うのは何奴だ？直ちに立ち去らねば、貴様らの血を持ってその代償を払ってもらおうぞ！
	 */
	public static final NpcStringId WHO_DARES_TO_COVET_THE_THRONE_OF_OUR_CASTLE_LEAVE_IMMEDIATELY_OR_YOU_WILL_PAY_THE_PRICE_OF_YOUR_AUDACITY_WITH_YOUR_VERY_OWN_BLOOD;

	/**
	 * ID: 1010624<br>
	 * Message: ほお、血族ですらない者が、この城を狙っていると？クククッ．．．亡者の恨み、その力を侮るな！
	 */
	public static final NpcStringId HMM_THOSE_WHO_ARE_NOT_OF_THE_BLOODLINE_ARE_COMING_THIS_WAY_TO_TAKE_OVER_THE_CASTLE_HUMPH_THE_BITTER_GRUDGES_OF_THE_DEAD_YOU_MUST_NOT_MAKE_LIGHT_OF_THEIR_POWER;

	/**
	 * ID: 1010625<br>
	 * Message: グオッ．．．私が倒れれば．．．血の結界が！
	 */
	public static final NpcStringId AARGH_IF_I_DIE_THEN_THE_MAGIC_FORCE_FIELD_OF_BLOOD_WILL;

	/**
	 * ID: 1010626<br>
	 * Message: まだ．．．終わりじゃない．．．これで終わらせは．．．しない．．．
	 */
	public static final NpcStringId ITS_NOT_OVER_YET_IT_WONT_BE_OVER_LIKE_THIS_NEVER;

	/**
	 * ID: 1010627<br>
	 * Message: ううん．．．誰だ、寝てるのに頭にネクターかけやがったやつは！
	 */
	public static final NpcStringId OOOH_WHO_POURED_NECTAR_ON_MY_HEAD_WHILE_I_WAS_SLEEPING;

	/**
	 * ID: 1010628<br>
	 * Message: 少々お待ちください。
	 */
	public static final NpcStringId PLEASE_WAIT_A_MOMENT;

	/**
	 * ID: 1010629<br>
	 * Message: さあ、今度は$s1という単語を当ててください。
	 */
	public static final NpcStringId THE_WORD_YOU_NEED_THIS_TIME_IS_S1;

	/**
	 * ID: 1010630<br>
	 * Message: 侵入者だ！警報装置作動。
	 */
	public static final NpcStringId INTRUDERS_SOUND_THE_ALARM;

	/**
	 * ID: 1010631<br>
	 * Message: 警報解除
	 */
	public static final NpcStringId DE_ACTIVATE_THE_ALARM;

	/**
	 * ID: 1010632<br>
	 * Message: まさか．．．防御機能が消えるとは．．．城の中は危険だ！皆外へ！
	 */
	public static final NpcStringId OH_NO_THE_DEFENSES_HAVE_FAILED_IT_IS_TOO_DANGEROUS_TO_REMAIN_INSIDE_THE_CASTLE_FLEE_EVERY_MAN_FOR_HIMSELF;

	/**
	 * ID: 1010633<br>
	 * Message: ゲームスタート！参加された方は、私が申し上げる単語をよーく見て当ててくださいね。
	 */
	public static final NpcStringId THE_GAME_HAS_BEGUN_PARTICIPANTS_PREPARE_TO_LEARN_AN_IMPORTANT_WORD;

	/**
	 * ID: 1010634<br>
	 * Message: 現在$s1チーム ポモナの残りのHPは$s2パーセントです。
	 */
	public static final NpcStringId S1_TEAMS_JACKPOT_HAS_S2_PERCENT_OF_ITS_HP_REMAINING;

	/**
	 * ID: 1010635<br>
	 * Message: 未定
	 */
	public static final NpcStringId UNDECIDED;

	/**
	 * ID: 1010636<br>
	 * Message: ククッ．．．宴が始まるぞ！この血に染みついたヘルマン家の呪いを味わえ！
	 */
	public static final NpcStringId HEH_HEH_I_SEE_THAT_THE_FEAST_HAS_BEGUN_BE_WARY_THE_CURSE_OF_THE_HELLMANN_FAMILY_HAS_POISONED_THIS_LAND;

	/**
	 * ID: 1010637<br>
	 * Message: 眠りから覚めよ、我が家臣たちよ。血を受け継ぎし者たちよ、我が娘が呼んでいる。血の宴の始まりだ！
	 */
	public static final NpcStringId ARISE_MY_FAITHFUL_SERVANTS_YOU_MY_PEOPLE_WHO_HAVE_INHERITED_THE_BLOOD_IT_IS_THE_CALLING_OF_MY_DAUGHTER_THE_FEAST_OF_BLOOD_WILL_NOW_BEGIN;

	/**
	 * ID: 1010639<br>
	 * Message: ぷし～っ、今から約2分程度、スタジアムの清掃が行われます。要らないアイテムを持っている方は地面に捨てちゃってください。
	 */
	public static final NpcStringId GRARR_FOR_THE_NEXT_2_MINUTES_OR_SO_THE_GAME_ARENA_ARE_WILL_BE_CLEANED_THROW_ANY_ITEMS_YOU_DONT_NEED_TO_THE_FLOOR_NOW;

	/**
	 * ID: 1010640<br>
	 * Message: ぷし～っ！$s1チームが相手チームのエリアに温泉硫黄を使用します。
	 */
	public static final NpcStringId GRARR_S1_TEAM_IS_USING_THE_HOT_SPRINGS_SULFUR_ON_THE_OPPONENTS_CAMP;

	/**
	 * ID: 1010641<br>
	 * Message: ぷし～っ！$s1チームがポモナの入れ替えを試みます。
	 */
	public static final NpcStringId GRARR_S1_TEAM_IS_ATTEMPTING_TO_STEAL_THE_JACKPOT;

	/**
	 * ID: 1010642<br>
	 * Message: **空席**
	 */
	public static final NpcStringId _VACANT_SEAT;
	
	/**
	 * ID: 1010643<br>
	 * Message: 残り$s1分です。
	 */
	public static final NpcStringId S1_MINUTES_REMAINING;

	/**
	 * ID: 1010644<br>
	 * Message: 黒聖歌団のステージをぶち壊すとは．．．許せぬ！
	 */
	public static final NpcStringId HOW_DARE_YOU_RUIN_THE_PERFORMANCE_OF_THE_DARK_CHOIR_UNFORGIVABLE;

	/**
	 * ID: 1010645<br>
	 * Message: 黒聖歌団の演奏を妨げる邪魔者を消せ！
	 */
	public static final NpcStringId GET_RID_OF_THE_INVADERS_WHO_INTERRUPT_THE_PERFORMANCE_OF_THE_DARK_CHOIR;

	/**
	 * ID: 1010646<br>
	 * Message: 死の演奏が聞こえないのか！黒聖歌団の恐ろしさを見せてやれ！
	 */
	public static final NpcStringId DONT_YOU_HEAR_THE_MUSIC_OF_DEATH_REVEAL_THE_HORROR_OF_THE_DARK_CHOIR;

	/**
	 * ID: 1010647<br>
	 * Message: 不滅の高原
	 */
	public static final NpcStringId THE_IMMORTAL_PLATEAU;

	/**
	 * ID: 1010648<br>
	 * Message: カマエル村
	 */
	public static final NpcStringId KAMAEL_VILLAGE;

	/**
	 * ID: 1010649<br>
	 * Message: 魂の島拠点
	 */
	public static final NpcStringId ISLE_OF_SOULS_BASE;

	/**
	 * ID: 1010650<br>
	 * Message: 黄金の丘拠点
	 */
	public static final NpcStringId GOLDEN_HILLS_BASE;

	/**
	 * ID: 1010651<br>
	 * Message: ミミルの森拠点
	 */
	public static final NpcStringId MIMIRS_FOREST_BASE;

	/**
	 * ID: 1010652<br>
	 * Message: 魂の島の港
	 */
	public static final NpcStringId ISLE_OF_SOULS_HARBOR;

	/**
	 * ID: 1010653<br>
	 * Message: 第1拠点
	 */
	public static final NpcStringId STRONGHOLD_I;

	/**
	 * ID: 1010654<br>
	 * Message: 第2拠点
	 */
	public static final NpcStringId STRONGHOLD_II;

	/**
	 * ID: 1010655<br>
	 * Message: 第3拠点
	 */
	public static final NpcStringId STRONGHOLD_III;

	/**
	 * ID: 1010656<br>
	 * Message: 要塞西門
	 */
	public static final NpcStringId FORTRESS_WEST_GATE;

	/**
	 * ID: 1010657<br>
	 * Message: 要塞東門
	 */
	public static final NpcStringId FORTRESS_EAST_GATE;

	/**
	 * ID: 1010658<br>
	 * Message: 要塞北門
	 */
	public static final NpcStringId FORTRESS_NORTH_GATE;

	/**
	 * ID: 1010659<br>
	 * Message: 要塞南門
	 */
	public static final NpcStringId FORTRESS_SOUTH_GATE;

	/**
	 * ID: 1010660<br>
	 * Message: 渓谷要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_VALLEY_FORTRESS;

	/**
	 * ID: 1010661<br>
	 * Message: ゴダード城の村広場
	 */
	public static final NpcStringId GODDARD_TOWN_SQUARE;

	/**
	 * ID: 1010662<br>
	 * Message: ゴダード城門前
	 */
	public static final NpcStringId FRONT_OF_THE_GODDARD_CASTLE_GATE;

	/**
	 * ID: 1010663<br>
	 * Message: グルーディオ城の村広場
	 */
	public static final NpcStringId GLUDIO_TOWN_SQUARE;

	/**
	 * ID: 1010664<br>
	 * Message: グルーディオ城門前
	 */
	public static final NpcStringId FRONT_OF_THE_GLUDIO_CASTLE_GATE;

	/**
	 * ID: 1010665<br>
	 * Message: ギラン城の村広場
	 */
	public static final NpcStringId GIRAN_TOWN_SQUARE;

	/**
	 * ID: 1010666<br>
	 * Message: ギラン城門前
	 */
	public static final NpcStringId FRONT_OF_THE_GIRAN_CASTLE_GATE;

	/**
	 * ID: 1010667<br>
	 * Message: 南部要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_SOUTHERN_FORTRESS;

	/**
	 * ID: 1010668<br>
	 * Message: 沼地帯要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_SWAMP_FORTRESS;

	/**
	 * ID: 1010669<br>
	 * Message: ディオン城の村広場
	 */
	public static final NpcStringId DION_TOWN_SQUARE;

	/**
	 * ID: 1010670<br>
	 * Message: ディオン城門前
	 */
	public static final NpcStringId FRONT_OF_THE_DION_CASTLE_GATE;

	/**
	 * ID: 1010671<br>
	 * Message: ルウン城の村広場
	 */
	public static final NpcStringId RUNE_TOWN_SQUARE;

	/**
	 * ID: 1010672<br>
	 * Message: ルウン城門前
	 */
	public static final NpcStringId FRONT_OF_THE_RUNE_CASTLE_GATE;

	/**
	 * ID: 1010673<br>
	 * Message: 白浜要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_WHITE_SAND_FORTRESS;

	/**
	 * ID: 1010674<br>
	 * Message: 盆地要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_BASIN_FORTRESS;

	/**
	 * ID: 1010675<br>
	 * Message: 象牙の塔要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_IVORY_FORTRESS;

	/**
	 * ID: 1010676<br>
	 * Message: シュチュッツガルト城の村広場
	 */
	public static final NpcStringId SCHUTTGART_TOWN_SQUARE;

	/**
	 * ID: 1010677<br>
	 * Message: シュチュッツガルト城門前
	 */
	public static final NpcStringId FRONT_OF_THE_SCHUTTGART_CASTLE_GATE;

	/**
	 * ID: 1010678<br>
	 * Message: アデン城の村広場
	 */
	public static final NpcStringId ADEN_TOWN_SQUARE;

	/**
	 * ID: 1010679<br>
	 * Message: アデン城門前
	 */
	public static final NpcStringId FRONT_OF_THE_ADEN_CASTLE_GATE;

	/**
	 * ID: 1010680<br>
	 * Message: 露営地要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_SHANTY_FORTRESS;

	/**
	 * ID: 1010681<br>
	 * Message: オーレン城の村広場
	 */
	public static final NpcStringId OREN_TOWN_SQUARE;

	/**
	 * ID: 1010682<br>
	 * Message: オーレン城門前
	 */
	public static final NpcStringId FRONT_OF_THE_OREN_CASTLE_GATE;

	/**
	 * ID: 1010683<br>
	 * Message: 遺跡要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_ARCHAIC_FORTRESS;

	/**
	 * ID: 1010684<br>
	 * Message: インナドリル城門前
	 */
	public static final NpcStringId FRONT_OF_THE_INNADRIL_CASTLE_GATE;

	/**
	 * ID: 1010685<br>
	 * Message: アウトポスト要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_BORDER_FORTRESS;

	/**
	 * ID: 1010686<br>
	 * Message: 水上都市ハイネス広場
	 */
	public static final NpcStringId HEINE_TOWN_SQUARE;

	/**
	 * ID: 1010687<br>
	 * Message: ハイヴ要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_HIVE_FORTRESS;

	/**
	 * ID: 1010688<br>
	 * Message: 湖の要塞前
	 */
	public static final NpcStringId FRONT_OF_THE_NARSELL_FORTRESS;

	/**
	 * ID: 1010689<br>
	 * Message: グルーディオ城の前
	 */
	public static final NpcStringId FRONT_OF_THE_GLUDIO_CASTLE;

	/**
	 * ID: 1010690<br>
	 * Message: ディオン城の前
	 */
	public static final NpcStringId FRONT_OF_THE_DION_CASTLE;

	/**
	 * ID: 1010691<br>
	 * Message: ギラン城の前
	 */
	public static final NpcStringId FRONT_OF_THE_GIRAN_CASTLE;

	/**
	 * ID: 1010692<br>
	 * Message: オーレン城の前
	 */
	public static final NpcStringId FRONT_OF_THE_OREN_CASTLE;

	/**
	 * ID: 1010693<br>
	 * Message: アデン城の前
	 */
	public static final NpcStringId FRONT_OF_THE_ADEN_CASTLE;

	/**
	 * ID: 1010694<br>
	 * Message: インナドリル城の前
	 */
	public static final NpcStringId FRONT_OF_THE_INNADRIL_CASTLE;

	/**
	 * ID: 1010695<br>
	 * Message: ゴダード城の前
	 */
	public static final NpcStringId FRONT_OF_THE_GODDARD_CASTLE;

	/**
	 * ID: 1010696<br>
	 * Message: ルウン城の前
	 */
	public static final NpcStringId FRONT_OF_THE_RUNE_CASTLE;

	/**
	 * ID: 1010697<br>
	 * Message: シュチュッツガルト城の前
	 */
	public static final NpcStringId FRONT_OF_THE_SCHUTTGART_CASTLE;

	/**
	 * ID: 1010698<br>
	 * Message: 太古の島乗船場
	 */
	public static final NpcStringId PRIMEVAL_ISLE_WHARF;

	/**
	 * ID: 1010699<br>
	 * Message: 神託の島
	 */
	public static final NpcStringId ISLE_OF_PRAYER;

	/**
	 * ID: 1010700<br>
	 * Message: ミスリル鉱山の西側入口
	 */
	public static final NpcStringId MITHRIL_MINES_WESTERN_ENTRANCE;

	/**
	 * ID: 1010701<br>
	 * Message: ミスリル鉱山の北側入口
	 */
	public static final NpcStringId MITHRIL_MINES_EASTERN_ENTRANCE;

	/**
	 * ID: 1010702<br>
	 * Message: 巨人たちの洞窟上層部
	 */
	public static final NpcStringId THE_GIANTS_CAVE_UPPER_LAYER;

	/**
	 * ID: 1010703<br>
	 * Message: 巨人たちの洞窟下層部
	 */
	public static final NpcStringId THE_GIANTS_CAVE_LOWER_LAYER;

	/**
	 * ID: 1010704<br>
	 * Message: 静寂の草原の中心
	 */
	public static final NpcStringId FIELD_OF_SILENCE_CENTER;

	/**
	 * ID: 1010705<br>
	 * Message: 囁きの草原の中心
	 */
	public static final NpcStringId FIELD_OF_WHISPERS_CENTER;

	/**
	 * ID: 1010706<br>
	 * Message: シャイドの棲み処
	 */
	public static final NpcStringId SHYEEDS_CAVERN;

	/**
	 * ID: 1010709<br>
	 * Message: 不滅の種乗船場
	 */
	public static final NpcStringId SEED_OF_INFINITY_DOCK;

	/**
	 * ID: 1010710<br>
	 * Message: 破滅の種乗船場
	 */
	public static final NpcStringId SEED_OF_DESTRUCTION_DOCK;

	/**
	 * ID: 1010711<br>
	 * Message: 消滅の種乗船場
	 */
	public static final NpcStringId SEED_OF_ANNIHILATION_DOCK;

	/**
	 * ID: 1010712<br>
	 * Message: アデン城の村のアインハザード神殿にいる神官ウッド
	 */
	public static final NpcStringId TOWN_OF_ADEN_EINHASAD_TEMPLE_PRIEST_WOOD;

	/**
	 * ID: 1010713<br>
	 * Message: 猟師の村にいるちりぢりになった魂の前
	 */
	public static final NpcStringId HUNTERS_VILLAGE_SEPARATED_SOUL_FRONT;

	/**
	 * ID: 1029350<br>
	 * Message: 一体今まで何してたの？人を待たせるにも程があるわ！
	 */
	public static final NpcStringId WHAT_TOOK_SO_LONG_I_WAITED_FOR_EVER;

	/**
	 * ID: 1029351<br>
	 * Message: 本のことは図書館長ソフィアに聞いて。
	 */
	public static final NpcStringId I_MUST_ASK_LIBRARIAN_SOPHIA_ABOUT_THE_BOOK;

	/**
	 * ID: 1029352<br>
	 * Message: この図書館、デカいだけで役に立つ本はあまりなさそうね。
	 */
	public static final NpcStringId THIS_LIBRARY_ITS_HUGE_BUT_THERE_ARENT_MANY_USEFUL_BOOKS_RIGHT;

	/**
	 * ID: 1029353<br>
	 * Message: 地下図書館ねぇ．．．ジメジメしててクサいところはいやなんだよね。
	 */
	public static final NpcStringId AN_UNDERGROUND_LIBRARY_I_HATE_DAMP_AND_SMELLY_PLACES;

	/**
	 * ID: 1029354<br>
	 * Message: 私たちの探してる本は、きっとここにあるはずよ。一冊ずつ見てみましょう。
	 */
	public static final NpcStringId THE_BOOK_THAT_WE_SEEK_IS_CERTAINLY_HERE_SEARCH_INCH_BY_INCH;

	/**
	 * ID: 1029450<br>
	 * Message: 東西南北それぞれの部屋で、例の本が置かれた読書台を探して。
	 */
	public static final NpcStringId WE_MUST_SEARCH_HIGH_AND_LOW_IN_EVERY_ROOM_FOR_THE_READING_DESK_THAT_CONTAINS_THE_BOOK_WE_SEEK;

	/**
	 * ID: 1029451<br>
	 * Message: 本の内容は覚えておいて。持って出られないから。
	 */
	public static final NpcStringId REMEMBER_THE_CONTENT_OF_THE_BOOKS_THAT_YOU_FOUND_YOU_CANT_TAKE_THEM_OUT_WITH_YOU;

	/**
	 * ID: 1029452<br>
	 * Message: 一度本を見つけた部屋にはもう入れないようね。
	 */
	public static final NpcStringId IT_SEEMS_THAT_YOU_CANNOT_REMEMBER_TO_THE_ROOM_OF_THE_WATCHER_WHO_FOUND_THE_BOOK;

	/**
	 * ID: 1029453<br>
	 * Message: ここでやるべきことはすべて終わったわ。真ん中の守護者のところに戻りましょう。
	 */
	public static final NpcStringId YOUR_WORK_HERE_IS_DONE_SO_RETURN_TO_THE_CENTRAL_GUARDIAN;

	/**
	 * ID: 1029460<br>
	 * Message: ソリナ様の安息を妨げる侵入者ども．．．早々に立ち去れ。
	 */
	public static final NpcStringId YOU_FOOLISH_INVADERS_WHO_DISTURB_THE_REST_OF_SOLINA_BE_GONE_FROM_THIS_PLACE;

	/**
	 * ID: 1029461<br>
	 * Message: お前たちの望みはわからぬが、ヒューマンごときの手に負えるものではない！
	 */
	public static final NpcStringId I_KNOW_NOT_WHAT_YOU_SEEK_BUT_THIS_TRUTH_CANNOT_BE_HANDLED_BY_MERE_HUMANS;

	/**
	 * ID: 1029462<br>
	 * Message: 愚かしき侵入者どもめ！もう見てはおれぬ！さっさと立ち去れ。これが最後の警告だ。
	 */
	public static final NpcStringId I_WILL_NOT_STAND_BY_AND_WATCH_YOUR_FOOLISH_ACTIONS_I_WARN_YOU_LEAVE_THIS_PLACE_AT_ONCE;

	/**
	 * ID: 1029550<br>
	 * Message: 封印の守護者は結界を解かない限りは全然ダメージを受けないみたい。
	 */
	public static final NpcStringId THE_GUARDIAN_OF_THE_SEAL_DOESNT_SEEM_TO_GET_INJURED_AT_ALL_UNTIL_THE_BARRIER_IS_DESTROYED;

	/**
	 * ID: 1029551<br>
	 * Message: 封印の守護者の前の部屋の装置は、きっと守護者の力を司る結界よ。
	 */
	public static final NpcStringId THE_DEVICE_LOCATED_IN_THE_ROOM_IN_FRONT_OF_THE_GUARDIAN_OF_THE_SEAL_IS_DEFINITELY_THE_BARRIER_THAT_CONTROLS_THE_GUARDIANS_POWER;

	/**
	 * ID: 1029552<br>
	 * Message: 結界を解くには、それに合った聖物を探して装置を動かすのよ。
	 */
	public static final NpcStringId TO_REMOVE_THE_BARRIER_YOU_MUST_FIND_THE_RELICS_THAT_FIT_THE_BARRIER_AND_ACTIVATE_THE_DEVICE;

	/**
	 * ID: 1029553<br>
	 * Message: 守護者は全部倒したわ。封印も解けたはずよ。真ん中のテレポートで移動しましょう。
	 */
	public static final NpcStringId ALL_THE_GUARDIANS_WERE_DEFEATED_AND_THE_SEAL_WAS_REMOVED_TELEPORT_TO_THE_CENTER;

	/**
	 * ID: 1110071<br>
	 * Message: 起きているところです。
	 */
	public static final NpcStringId _IS_THE_PROCESS_OF_STANDING_UP;

	/**
	 * ID: 1110072<br>
	 * Message: 座っているところです。
	 */
	public static final NpcStringId _IS_THE_PROCESS_OF_SITTING_DOWN;

	/**
	 * ID: 1110073<br>
	 * Message: 座っている状態でスキルが使用できます。
	 */
	public static final NpcStringId IT_IS_POSSIBLE_TO_USE_A_SKILL_WHILE_SITTING_DOWN;

	/**
	 * ID: 1110074<br>
	 * Message: 射程距離から外れています。
	 */
	public static final NpcStringId IS_OUT_OF_RANGE;

	/**
	 * ID: 1120300<br>
	 * Message: やめてくれ．．．もう気が済んだろ？
	 */
	public static final NpcStringId THANK_YOU_MY_BOOK_CHILD;

	/**
	 * ID: 1120301<br>
	 * Message: $s1に倒された
	 */
	public static final NpcStringId KILLED_BY_S2;

	/**
	 * ID: 1121000<br>
	 * Message: 執事：ちょっと待ってください。
	 */
	public static final NpcStringId STEWARD_PLEASE_WAIT_A_MOMENT;

	/**
	 * ID: 1121001<br>
	 * Message: 執事：女王様をどうか元の姿に．．．
	 */
	public static final NpcStringId STEWARD_PLEASE_RESTORE_THE_QUEENS_FORMER_APPEARANCE;

	/**
	 * ID: 1121002<br>
	 * Message: 執事：時間がありません。急いでください。
	 */
	public static final NpcStringId STEWARD_WASTE_NO_TIME_PLEASE_HURRY;

	/**
	 * ID: 1121003<br>
	 * Message: 執事：やはり無理だったのか．．．
	 */
	public static final NpcStringId STEWARD_WAS_IT_INDEED_TOO_MUCH_TO_ASK;

	/**
	 * ID: 1121004<br>
	 * Message: フレヤ：ここはどこ？あの日から．．．
	 */
	public static final NpcStringId FREYA_HEATHENS_FEEL_MY_CHILL;

	/**
	 * ID: 1121005<br>
	 * Message: 公開時間は終了しました。城にいる方々は速やかに外へ出てください。
	 */
	public static final NpcStringId ATTENTION_PLEASE_THE_GATES_WILL_BE_CLOSING_SHORTLY_ALL_VISITORS_TO_THE_QUEENS_CASTLE_SHOULD_LEAVE_IMMEDIATELY;

	/**
	 * ID: 1121006<br>
	 * Message: 許可された者以外は武器の所持を禁じられている！
	 */
	public static final NpcStringId YOU_CANNOT_CARRY_A_WEAPON_WITHOUT_AUTHORIZATION;

	/**
	 * ID: 1121007<br>
	 * Message: 私を騙すつもりですね。見損ないました。
	 */
	public static final NpcStringId ARE_YOU_TRYING_TO_DECEIVE_ME_IM_DISAPPOINTED;

	/**
	 * ID: 1121008<br>
	 * Message: 残り30分です。
	 */
	public static final NpcStringId N30_MINUTES_REMAIN;

	/**
	 * ID: 1121009<br>
	 * Message: 残り20分です。
	 */
	public static final NpcStringId N20_MINUTES_REMAIN;

	/**
	 * ID: 1200001<br>
	 * Message: チル コーダ
	 */
	public static final NpcStringId CHILLY_CODA;

	/**
	 * ID: 1200002<br>
	 * Message: バーニング コーダ
	 */
	public static final NpcStringId BURNING_CODA;

	/**
	 * ID: 1200003<br>
	 * Message: ブルー コーダ
	 */
	public static final NpcStringId BLUE_CODA;

	/**
	 * ID: 1200004<br>
	 * Message: レッド コーダ
	 */
	public static final NpcStringId RED_CODA;

	/**
	 * ID: 1200005<br>
	 * Message: ゴールデン コーダ
	 */
	public static final NpcStringId GOLDEN_CODA;

	/**
	 * ID: 1200006<br>
	 * Message: デザート コーダ
	 */
	public static final NpcStringId DESERT_CODA;

	/**
	 * ID: 1200007<br>
	 * Message: リュート コーダ
	 */
	public static final NpcStringId LUTE_CODA;

	/**
	 * ID: 1200008<br>
	 * Message: ツイン コーダ
	 */
	public static final NpcStringId TWIN_CODA;

	/**
	 * ID: 1200009<br>
	 * Message: ダーク コーダ
	 */
	public static final NpcStringId DARK_CODA;

	/**
	 * ID: 1200010<br>
	 * Message: シャイニング コーダ
	 */
	public static final NpcStringId SHINING_CODA;

	/**
	 * ID: 1200011<br>
	 * Message: チル コボル
	 */
	public static final NpcStringId CHILLY_COBOL;

	/**
	 * ID: 1200012<br>
	 * Message: バーニング コボル
	 */
	public static final NpcStringId BURNING_COBOL;

	/**
	 * ID: 1200013<br>
	 * Message: ブルー コボル
	 */
	public static final NpcStringId BLUE_COBOL;

	/**
	 * ID: 1200014<br>
	 * Message: レッド コボル
	 */
	public static final NpcStringId RED_COBOL;

	/**
	 * ID: 1200015<br>
	 * Message: ゴールデン コボル
	 */
	public static final NpcStringId GOLDEN_COBOL;

	/**
	 * ID: 1200016<br>
	 * Message: デザート コボル
	 */
	public static final NpcStringId DESERT_COBOL;

	/**
	 * ID: 1200017<br>
	 * Message: シー コボル
	 */
	public static final NpcStringId SEA_COBOL;

	/**
	 * ID: 1200018<br>
	 * Message: バー コボル
	 */
	public static final NpcStringId THORN_COBOL;

	/**
	 * ID: 1200019<br>
	 * Message: ダップル コボル
	 */
	public static final NpcStringId DAPPLE_COBOL;

	/**
	 * ID: 1200020<br>
	 * Message: グレート コボル
	 */
	public static final NpcStringId GREAT_COBOL;

	/**
	 * ID: 1200021<br>
	 * Message: チル コドラン
	 */
	public static final NpcStringId CHILLY_CODRAN;

	/**
	 * ID: 1200022<br>
	 * Message: バーニング コドラン
	 */
	public static final NpcStringId BURNING_CODRAN;

	/**
	 * ID: 1200023<br>
	 * Message: ブルー コドラン
	 */
	public static final NpcStringId BLUE_CODRAN;

	/**
	 * ID: 1200024<br>
	 * Message: レッド コドラン
	 */
	public static final NpcStringId RED_CODRAN;

	/**
	 * ID: 1200025<br>
	 * Message: ダップル コドラン
	 */
	public static final NpcStringId DAPPLE_CODRAN;

	/**
	 * ID: 1200026<br>
	 * Message: デザート コドラン
	 */
	public static final NpcStringId DESERT_CODRAN;

	/**
	 * ID: 1200027<br>
	 * Message: シー コドラン
	 */
	public static final NpcStringId SEA_CODRAN;

	/**
	 * ID: 1200028<br>
	 * Message: ツイン コドラン
	 */
	public static final NpcStringId TWIN_CODRAN;

	/**
	 * ID: 1200029<br>
	 * Message: バー コドラン
	 */
	public static final NpcStringId THORN_CODRAN;

	/**
	 * ID: 1200030<br>
	 * Message: グレート コドラン
	 */
	public static final NpcStringId GREAT_CODRAN;

	/**
	 * ID: 1200031<br>
	 * Message: 改良ダーク コーダ
	 */
	public static final NpcStringId ALTERNATIVE_DARK_CODA;

	/**
	 * ID: 1200032<br>
	 * Message: 改良レッド コーダ
	 */
	public static final NpcStringId ALTERNATIVE_RED_CODA;

	/**
	 * ID: 1200033<br>
	 * Message: 改良チル コーダ
	 */
	public static final NpcStringId ALTERNATIVE_CHILLY_CODA;

	/**
	 * ID: 1200034<br>
	 * Message: 改良ブルー コーダ
	 */
	public static final NpcStringId ALTERNATIVE_BLUE_CODA;

	/**
	 * ID: 1200035<br>
	 * Message: 改良ゴールデン コーダ
	 */
	public static final NpcStringId ALTERNATIVE_GOLDEN_CODA;

	/**
	 * ID: 1200036<br>
	 * Message: 改良リュート コーダ
	 */
	public static final NpcStringId ALTERNATIVE_LUTE_CODA;

	/**
	 * ID: 1200037<br>
	 * Message: 改良デザート コーダ
	 */
	public static final NpcStringId ALTERNATIVE_DESERT_CODA;

	/**
	 * ID: 1200038<br>
	 * Message: 改良レッド コボル
	 */
	public static final NpcStringId ALTERNATIVE_RED_COBOL;

	/**
	 * ID: 1200039<br>
	 * Message: 改良チル コボル
	 */
	public static final NpcStringId ALTERNATIVE_CHILLY_COBOL;

	/**
	 * ID: 1200040<br>
	 * Message: 改良ブルー コボル
	 */
	public static final NpcStringId ALTERNATIVE_BLUE_COBOL;

	/**
	 * ID: 1200041<br>
	 * Message: 改良バー コボル
	 */
	public static final NpcStringId ALTERNATIVE_THORN_COBOL;

	/**
	 * ID: 1200042<br>
	 * Message: 改良ゴールデン コボル
	 */
	public static final NpcStringId ALTERNATIVE_GOLDEN_COBOL;

	/**
	 * ID: 1200043<br>
	 * Message: 改良グレート コボル
	 */
	public static final NpcStringId ALTERNATIVE_GREAT_COBOL;

	/**
	 * ID: 1200044<br>
	 * Message: 改良レッド コドラン
	 */
	public static final NpcStringId ALTERNATIVE_RED_CODRAN;

	/**
	 * ID: 1200045<br>
	 * Message: 改良シー コドラン
	 */
	public static final NpcStringId ALTERNATIVE_SEA_CODRAN;

	/**
	 * ID: 1200046<br>
	 * Message: 改良チル コドラン
	 */
	public static final NpcStringId ALTERNATIVE_CHILLY_CODRAN;

	/**
	 * ID: 1200047<br>
	 * Message: 改良ブルー コドラン
	 */
	public static final NpcStringId ALTERNATIVE_BLUE_CODRAN;

	/**
	 * ID: 1200048<br>
	 * Message: 改良ツイン コドラン
	 */
	public static final NpcStringId ALTERNATIVE_TWIN_CODRAN;

	/**
	 * ID: 1200049<br>
	 * Message: 改良グレート コドラン
	 */
	public static final NpcStringId ALTERNATIVE_GREAT_CODRAN;

	/**
	 * ID: 1200050<br>
	 * Message: 改良デザート コドラン
	 */
	public static final NpcStringId ALTERNATIVE_DESERT_CODRAN;

	/**
	 * ID: 1300001<br>
	 * Message: 扉が壊された。幕舎をすべて壊し、敵軍の指令本部まで移動しろ！
	 */
	public static final NpcStringId WE_HAVE_BROKEN_THROUGH_THE_GATE_DESTROY_THE_ENCAMPMENT_AND_MOVE_TO_THE_COMMAND_POST;

	/**
	 * ID: 1300002<br>
	 * Message: ついに指令幕舎の扉が開かれた。急いで旗を手に入れ勝利のために高く掲げるのだ！
	 */
	public static final NpcStringId THE_COMMAND_GATE_HAS_OPENED_CAPTURE_THE_FLAG_QUICKLY_AND_RAISE_IT_HIGH_TO_PROCLAIM_OUR_VICTORY;

	/**
	 * ID: 1300003<br>
	 * Message: くそう．．．逃げろ．．．
	 */
	public static final NpcStringId THE_GODS_HAVE_FORSAKEN_US_RETREAT;

	/**
	 * ID: 1300004<br>
	 * Message: お前たちが私の矢を折ろうとも、我々の志を挫くことはできないのだ．．．弓部隊、後退だ！
	 */
	public static final NpcStringId YOU_MAY_HAVE_BROKEN_OUR_ARROWS_BUT_YOU_WILL_NEVER_BREAK_OUR_WILL_ARCHERS_RETREAT;

	/**
	 * ID: 1300005<br>
	 * Message: ああ．．．要塞を守っていた魔法陣の力が弱くなっていく。支援部隊全員後退するのです。
	 */
	public static final NpcStringId AT_LAST_THE_MAGIC_FIELD_THAT_PROTECTS_THE_FORTRESS_HAS_WEAKENED_VOLUNTEERS_STAND_BACK;

	/**
	 * ID: 1300006<br>
	 * Message: ううっ．．．本部．．．こちら守備隊．．．今すぐ応援願う．．．
	 */
	public static final NpcStringId AIIEEEE_COMMAND_CENTER_THIS_IS_GUARD_UNIT_WE_NEED_BACKUP_RIGHT_AWAY;

	/**
	 * ID: 1300007<br>
	 * Message: 要塞戦力の供給が遮断されました。
	 */
	public static final NpcStringId FORTRESS_POWER_DISABLED;

	/**
	 * ID: 1300008<br>
	 * Message: 私の名声と指揮がまさか崩されるとは．．．
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
	 * Message: 全部隊に告ぐ！$s1を集中攻撃せよ！敵に訓練成果を見せてやるのだ！
	 */
	public static final NpcStringId EVERYONE_CONCENTRATE_YOUR_ATTACKS_ON_S1_SHOW_THE_ENEMY_YOUR_RESOLVE;

	/**
	 * ID: 1300013<br>
	 * Message: 敵軍の支援元をまず攻撃するのが戦闘の基本だ。やれっ！
	 */
	public static final NpcStringId ATTACKING_THE_ENEMYS_REINFORCEMENTS_IS_NECESSARY_TIME_TO_DIE;

	/**
	 * ID: 1300014<br>
	 * Message: 火の精霊よ！究極の力をお見せください！敵を焼き払ってくださいますよう！
	 */
	public static final NpcStringId SPIRIT_OF_FIRE_UNLEASH_YOUR_POWER_BURN_THE_ENEMY;

	/**
	 * ID: 1300015<br>
	 * Message: ちょっと、こいつらかなり手ごわいな。手伝ってもらえる？
	 */
	public static final NpcStringId HEY_THESE_FOES_ARE_TOUGHER_THAN_THEY_LOOK_IM_GOING_TO_NEED_SOME_HELP_HERE;

	/**
	 * ID: 1300016<br>
	 * Message: かなり苦戦しているようだが、私が手を貸してやろうか！？
	 */
	public static final NpcStringId DO_YOU_NEED_MY_POWER_YOU_SEEM_TO_BE_STRUGGLING;

	/**
	 * ID: 1300017<br>
	 * Message: 忙しいのはこちらも同じだ。
	 */
	public static final NpcStringId IM_RATHER_BUSY_HERE_AS_WELL;

	/**
	 * ID: 1300018<br>
	 * Message: このままで終わると思うなよ。お前らの野望もすぐに崩されるだろう。
	 */
	public static final NpcStringId DONT_THINK_THAT_ITS_GONNA_END_LIKE_THIS_YOUR_AMBITION_WILL_SOON_BE_DESTROYED_AS_WELL;

	/**
	 * ID: 1300019<br>
	 * Message: 死ぬ覚悟はできてるだろうな！
	 */
	public static final NpcStringId YOU_MUST_HAVE_BEEN_PREPARED_TO_DIE;

	/**
	 * ID: 1300020<br>
	 * Message: うう．．．自分の身ひとつですら思い通りにできないとは、情けない。もうこの座に留まる必要はなかろう。
	 */
	public static final NpcStringId I_FEEL_SO_MUCH_GRIEF_THAT_I_CANT_EVEN_TAKE_CARE_OF_MYSELF_THERE_ISNT_ANY_REASON_FOR_ME_TO_STAY_HERE_ANY_LONGER;

	/**
	 * ID: 1300101<br>
	 * Message: 露営地要塞
	 */
	public static final NpcStringId SHANTY_FORTRESS;

	/**
	 * ID: 1300102<br>
	 * Message: グルーディオ南部要塞
	 */
	public static final NpcStringId SOUTHERN_FORTRESS;

	/**
	 * ID: 1300103<br>
	 * Message: ハイヴ要塞
	 */
	public static final NpcStringId HIVE_FORTRESS;

	/**
	 * ID: 1300104<br>
	 * Message: 渓谷要塞
	 */
	public static final NpcStringId VALLEY_FORTRESS;

	/**
	 * ID: 1300105<br>
	 * Message: 象牙の塔要塞
	 */
	public static final NpcStringId IVORY_FORTRESS;

	/**
	 * ID: 1300106<br>
	 * Message: 湖の要塞
	 */
	public static final NpcStringId NARSELL_FORTRESS;

	/**
	 * ID: 1300107<br>
	 * Message: 盆地要塞
	 */
	public static final NpcStringId BASIN_FORTRESS;

	/**
	 * ID: 1300108<br>
	 * Message: 白浜要塞
	 */
	public static final NpcStringId WHITE_SANDS_FORTRESS;

	/**
	 * ID: 1300109<br>
	 * Message: アウトポスト要塞
	 */
	public static final NpcStringId BORDERLAND_FORTRESS;

	/**
	 * ID: 1300110<br>
	 * Message: 沼地帯要塞
	 */
	public static final NpcStringId SWAMP_FORTRESS;

	/**
	 * ID: 1300111<br>
	 * Message: 遺跡要塞
	 */
	public static final NpcStringId ARCHAIC_FORTRESS;

	/**
	 * ID: 1300112<br>
	 * Message: フローラン境界要塞
	 */
	public static final NpcStringId FLORAN_FORTRESS;

	/**
	 * ID: 1300113<br>
	 * Message: 霧の山脈境界要塞
	 */
	public static final NpcStringId CLOUD_MOUNTAIN_FORTRESS;

	/**
	 * ID: 1300114<br>
	 * Message: タノール境界要塞
	 */
	public static final NpcStringId TANOR_FORTRESS;

	/**
	 * ID: 1300115<br>
	 * Message: ドラゴン スパイン境界要塞
	 */
	public static final NpcStringId DRAGONSPINE_FORTRESS;

	/**
	 * ID: 1300116<br>
	 * Message: 地竜の境界要塞
	 */
	public static final NpcStringId ANTHARASS_FORTRESS;

	/**
	 * ID: 1300117<br>
	 * Message: 西部 国境要塞
	 */
	public static final NpcStringId WESTERN_FORTRESS;

	/**
	 * ID: 1300118<br>
	 * Message: 猟師の境界要塞
	 */
	public static final NpcStringId HUNTERS_FORTRESS;

	/**
	 * ID: 1300119<br>
	 * Message: 草原の境界要塞
	 */
	public static final NpcStringId AARU_FORTRESS;

	/**
	 * ID: 1300120<br>
	 * Message: 死霊の境界要塞
	 */
	public static final NpcStringId DEMON_FORTRESS;

	/**
	 * ID: 1300121<br>
	 * Message: 聖者の境界要塞
	 */
	public static final NpcStringId MONASTIC_FORTRESS;

	/**
	 * ID: 1300122<br>
	 * Message: 独立状態
	 */
	public static final NpcStringId INDEPENDENT_STATE;

	/**
	 * ID: 1300123<br>
	 * Message: 無関係
	 */
	public static final NpcStringId NONPARTISAN;

	/**
	 * ID: 1300124<br>
	 * Message: 契約状態
	 */
	public static final NpcStringId CONTRACT_STATE;

	/**
	 * ID: 1300125<br>
	 * Message: 1番目のパスワードが入力されました。
	 */
	public static final NpcStringId FIRST_PASSWORD_HAS_BEEN_ENTERED;

	/**
	 * ID: 1300126<br>
	 * Message: 2番目のパスワードが入力されました。
	 */
	public static final NpcStringId SECOND_PASSWORD_HAS_BEEN_ENTERED;

	/**
	 * ID: 1300127<br>
	 * Message: パスワードが入力されませんでした。
	 */
	public static final NpcStringId PASSWORD_HAS_NOT_BEEN_ENTERED;

	/**
	 * ID: 1300128<br>
	 * Message: 現在 $s1 / 3回目を行っています。
	 */
	public static final NpcStringId ATTEMPT_S1_3_IS_IN_PROGRESS_THIS_IS_THE_THIRD_ATTEMPT_ON_S1;

	/**
	 * ID: 1300129<br>
	 * Message: 1番の記号を合わせました。
	 */
	public static final NpcStringId THE_1ST_MARK_IS_CORRECT;

	/**
	 * ID: 1300130<br>
	 * Message: 2番の記号を合わせました。
	 */
	public static final NpcStringId THE_2ND_MARK_IS_CORRECT;

	/**
	 * ID: 1300131<br>
	 * Message: 記号の組み合わせに失敗しました。
	 */
	public static final NpcStringId THE_MARKS_HAVE_NOT_BEEN_ASSEMBLED;

	/**
	 * ID: 1300132<br>
	 * Message: まもなく第$s1スタジアムで、オリンピアードクラス無制限チーム競技が始まります。
	 */
	public static final NpcStringId OLYMPIAD_CLASS_FREE_TEAM_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;

	/**
	 * ID: 1300133<br>
	 * Message: 領地要塞
	 */
	public static final NpcStringId DOMAIN_FORTRESS;

	/**
	 * ID: 1300134<br>
	 * Message: 境界要塞
	 */
	public static final NpcStringId BOUNDARY_FORTRESS;

	/**
	 * ID: 1300135<br>
	 * Message: $s1時間 $s2分
	 */
	public static final NpcStringId S1HOUR_S2MINUTE;

	/**
	 * ID: 1300136<br>
	 * Message: 指定できません。
	 */
	public static final NpcStringId NOT_DESIGNATED;

	/**
	 * ID: 1300137<br>
	 * Message: 勇士たちよ、囚われの身の我々を救いに来てくれたんですね。
	 */
	public static final NpcStringId WARRIORS_HAVE_YOU_COME_TO_HELP_THOSE_WHO_ARE_IMPRISONED_HERE;

	/**
	 * ID: 1300138<br>
	 * Message: くらえ！くされ野郎！
	 */
	public static final NpcStringId TAKE_THAT_YOU_WEAKLING;

	/**
	 * ID: 1300139<br>
	 * Message: 俺は強いんだ！
	 */
	public static final NpcStringId BEHOLD_MY_MIGHT;

	/**
	 * ID: 1300140<br>
	 * Message: 気が遠くなりそうだ。
	 */
	public static final NpcStringId YOUR_MIND_IS_GOING_BLANK;

	/**
	 * ID: 1300141<br>
	 * Message: 痛みが骨の髄までしみわたるぜ．．．
	 */
	public static final NpcStringId UGH_IT_HURTS_DOWN_TO_THE_BONES;

	/**
	 * ID: 1300142<br>
	 * Message: もう限界だな。ううっ．．．
	 */
	public static final NpcStringId I_CANT_STAND_IT_ANYMORE_AAH;

	/**
	 * ID: 1300143<br>
	 * Message: きゃ～っ！
	 */
	public static final NpcStringId KYAAAK;

	/**
	 * ID: 1300144<br>
	 * Message: うっ、なんてこったい！
	 */
	public static final NpcStringId GASP_HOW_CAN_THIS_BE;

	/**
	 * ID: 1300145<br>
	 * Message: てめえら！骨と肉をばらばらにされてえのか！
	 */
	public static final NpcStringId ILL_RIP_THE_FLESH_FROM_YOUR_BONES;

	/**
	 * ID: 1300146<br>
	 * Message: 一生、妄想の沼で地獄の責め苦を味あわせてやる！
	 */
	public static final NpcStringId YOULL_FLOUNDER_IN_DELUSION_FOR_THE_REST_OF_YOUR_LIFE;

	/**
	 * ID: 1300147<br>
	 * Message: ここから抜け出す方法はない！
	 */
	public static final NpcStringId THERE_IS_NO_ESCAPE_FROM_THIS_PLACE;

	/**
	 * ID: 1300148<br>
	 * Message: てめえら、俺を誰だと思ってんだ！
	 */
	public static final NpcStringId HOW_DARE_YOU;

	/**
	 * ID: 1300149<br>
	 * Message: 倒してやる！
	 */
	public static final NpcStringId I_SHALL_DEFEAT_YOU;

	/**
	 * ID: 1300150<br>
	 * Message: 1段階スタート！
	 */
	public static final NpcStringId BEGIN_STAGE_1;

	/**
	 * ID: 1300151<br>
	 * Message: 2段階スタート！
	 */
	public static final NpcStringId BEGIN_STAGE_2;

	/**
	 * ID: 1300152<br>
	 * Message: 3段階スタート！
	 */
	public static final NpcStringId BEGIN_STAGE_3;

	/**
	 * ID: 1300153<br>
	 * Message: やり遂げたんですね！私たちは勇者様を信じていました。たいしたことはできませんが、心ばかりのお礼を用意しました。少々お時間をいただけませんでしょうか。
	 */
	public static final NpcStringId YOUVE_DONE_IT_WE_BELIEVED_IN_YOU_WARRIOR_WE_WANT_TO_SHOW_OUR_SINCERITY_THOUGH_IT_IS_SMALL_PLEASE_GIVE_ME_SOME_OF_YOUR_TIME;

	/**
	 * ID: 1300154<br>
	 * Message: 中央拠点の凝縮機が稼動します。
	 */
	public static final NpcStringId THE_CENTRAL_STRONGHOLDS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300155<br>
	 * Message: 第1拠点の凝縮機が稼動します。
	 */
	public static final NpcStringId STRONGHOLD_IS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300156<br>
	 * Message: 第2拠点の凝縮機が稼動します。
	 */
	public static final NpcStringId STRONGHOLD_IIS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300157<br>
	 * Message: 第3拠点の凝縮機が稼動します。
	 */
	public static final NpcStringId STRONGHOLD_IIIS_COMPRESSOR_IS_WORKING;

	/**
	 * ID: 1300158<br>
	 * Message: 中央拠点の凝縮機が破壊されました。
	 */
	public static final NpcStringId THE_CENTRAL_STRONGHOLDS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300159<br>
	 * Message: 第1拠点の凝縮機が破壊されました。
	 */
	public static final NpcStringId STRONGHOLD_IS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300160<br>
	 * Message: 第2拠点の凝縮機が破壊されました。
	 */
	public static final NpcStringId STRONGHOLD_IIS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300161<br>
	 * Message: 第3拠点の凝縮機が破壊されました。
	 */
	public static final NpcStringId STRONGHOLD_IIIS_COMPRESSOR_HAS_BEEN_DESTROYED;

	/**
	 * ID: 1300162<br>
	 * Message: なんてこったい！気が強すぎてとても扱えそうにないぞ。
	 */
	public static final NpcStringId WHAT_A_PREDICAMENT_MY_ATTEMPTS_WERE_UNSUCCESSFUL;

	/**
	 * ID: 1300163<br>
	 * Message: 勇気！覇気！血気！領地戦で戦闘を繰り広げる傭兵たちよ！私の元に集まれ。富と名誉が待っている。
	 */
	public static final NpcStringId COURAGE_AMBITION_PASSION_MERCENARIES_WHO_WANT_TO_REALIZE_THEIR_DREAM_OF_FIGHTING_IN_THE_TERRITORY_WAR_COME_TO_ME_FORTUNE_AND_GLORY_ARE_WAITING_FOR_YOU;

	/**
	 * ID: 1300164<br>
	 * Message: 戦いたいのか。怖いって？隠れようったってそうはいかない。立ち向かうならば、我々傭兵隊が力を貸そう！
	 */
	public static final NpcStringId DO_YOU_WISH_TO_FIGHT_ARE_YOU_AFRAID_NO_MATTER_HOW_HARD_YOU_TRY_YOU_HAVE_NOWHERE_TO_RUN_BUT_IF_YOU_FACE_IT_HEAD_ON_OUR_MERCENARY_TROOP_WILL_HELP_YOU_OUT;

	/**
	 * ID: 1300165<br>
	 * Message: 突撃！突撃！突撃！
	 */
	public static final NpcStringId CHARGE_CHARGE_CHARGE;

	/**
	 * ID: 1300166<br>
	 * Message: まもなく第$s1スタジアムで、オリンピアードクラス無制限個人競技が始まります。
	 */
	public static final NpcStringId OLYMPIAD_CLASS_FREE_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;

	/**
	 * ID: 1300167<br>
	 * Message: まもなく第$s1スタジアムで、オリンピアードクラス別個人競技が始まります。
	 */
	public static final NpcStringId OLYMPIAD_CLASS_INDIVIDUAL_MATCH_IS_GOING_TO_BEGIN_IN_ARENA_S1_IN_A_MOMENT;

	/**
	 * ID: 1600001<br>
	 * Message: 他のキャラクターに補助魔法を詠唱中です。しばらくしてから再度お試しください。
	 */
	public static final NpcStringId ANOTHER_PLAYER_IS_CURRENTLY_BEING_BUFFED_PLEASE_TRY_AGAIN_IN_A_MOMENT;

	/**
	 * ID: 1600002<br>
	 * Message: 変身中には搭乗できません。
	 */
	public static final NpcStringId YOU_CANNOT_MOUNT_WHILE_YOU_ARE_POLYMORPHED;

	/**
	 * ID: 1600003<br>
	 * Message: 戦闘状態や、ストライダーに搭乗した状態ではワイバーンに乗れません。
	 */
	public static final NpcStringId YOU_CANNOT_MOUNT_A_WYVERN_WHILE_IN_BATTLE_MODE_OR_WHILE_MOUNTED_ON_A_STRIDER;

	/**
	 * ID: 1600004<br>
	 * Message: くすん．．．嫌い．．．
	 */
	public static final NpcStringId BOO_HOO_I_HATE;

	/**
	 * ID: 1600005<br>
	 * Message: またね～っ！
	 */
	public static final NpcStringId SEE_YOU_LATER;

	/**
	 * ID: 1600006<br>
	 * Message: よくぞ選んでくれました！
	 */
	public static final NpcStringId YOUVE_MADE_A_GREAT_CHOICE;

	/**
	 * ID: 1600007<br>
	 * Message: 私の中のブツがウズいてるぅ。
	 */
	public static final NpcStringId THANKS_I_FEEL_MORE_RELAXED;

	/**
	 * ID: 1600008<br>
	 * Message: 私を選んで！他のはクズよ！
	 */
	public static final NpcStringId DID_YOU_SEE_THAT_FIRECRACKER_EXPLODE;

	/**
	 * ID: 1600009<br>
	 * Message: いやいやっ！私にさわらないで！
	 */
	public static final NpcStringId I_AM_NOTHING;

	/**
	 * ID: 1600010<br>
	 * Message: 私、見た目よりすごいんです！
	 */
	public static final NpcStringId I_AM_TELLING_THE_TRUTH;

	/**
	 * ID: 1600012<br>
	 * Message: 出発した村へのテレポートはタダでできちゃうよ。
	 */
	public static final NpcStringId ITS_FREE_TO_GO_BACK_TO_THE_VILLAGE_YOU_TELEPORTED_FROM;

	/**
	 * ID: 1600013<br>
	 * Message: 宝物の袋の切れ端を50個集めてくれたら、ボクちゃんが宝物の袋に替えてあげるからね。
	 */
	public static final NpcStringId IF_YOU_COLLECT_50_INDIVIDUAL_TREASURE_SACK_PIECES_YOU_CAN_EXCHANGE_THEM_FOR_A_TREASURE_SACK;

	/**
	 * ID: 1600014<br>
	 * Message: ウサギちゃんに変身しないと箱を見つけられないよ。
	 */
	public static final NpcStringId YOU_MUST_BE_TRANSFORMED_INTO_A_TREASURE_HUNTER_TO_FIND_A_CHEST;

	/**
	 * ID: 1600015<br>
	 * Message: ウサギちゃんの変身はすぐ解けちゃうんだ。だから、好きな場所に移ってからスクロールを使った方がいいよ。
	 */
	public static final NpcStringId YOUD_BETTER_USE_THE_TRANSFORMATION_SPELL_AT_THE_RIGHT_MOMENT_SINCE_IT_DOESNT_LAST_LONG;

	/**
	 * ID: 1600016<br>
	 * Message: 幻想の島はどこでもピースゾーンだからね。
	 */
	public static final NpcStringId ALL_OF_FANTASY_ISLE_IS_A_PEACE_ZONE;

	/**
	 * ID: 1600017<br>
	 * Message: 幻想の島までボクちゃんがタダで送ってあげちゃうよ。
	 */
	public static final NpcStringId IF_YOU_NEED_TO_GO_TO_FANTASY_ISLE_COME_SEE_ME;

	/**
	 * ID: 1600018<br>
	 * Message: ウサギ変身スクロールの販売は12時間ごとだからね。
	 */
	public static final NpcStringId YOU_CAN_ONLY_PURCHASE_A_TREASURE_HUNTER_TRANSFORMATION_SCROLL_ONCE_EVERY_12_HOURS;

	/**
	 * ID: 1600019<br>
	 * Message: あれ？ここまで違う方法で来たの？それじゃ、近くのルウン城の村へ送ってあげるね。
	 */
	public static final NpcStringId IF_YOUR_MEANS_OF_ARRIVAL_WAS_A_BIT_UNCONVENTIONAL_THEN_ILL_BE_SENDING_YOU_BACK_TO_RUNE_TOWNSHIP_WHICH_IS_THE_NEAREST_TOWN;

	/**
	 * ID: 1600020<br>
	 * Message: ドンドンッ。
	 */
	public static final NpcStringId RATTLE;

	/**
	 * ID: 1600021<br>
	 * Message: ガタゴトッ。
	 */
	public static final NpcStringId BUMP;

	/**
	 * ID: 1600022<br>
	 * Message: 後悔しますよ。
	 */
	public static final NpcStringId YOU_WILL_REGRET_THIS;

	/**
	 * ID: 1600023<br>
	 * Message: ふふふ．．．どうだ、勇気があるなら運試ししてみないか。
	 */
	public static final NpcStringId CARE_TO_CHALLENGE_FATE_AND_TEST_YOUR_LUCK;

	/**
	 * ID: 1600024<br>
	 * Message: S80武器は欲しくないか。
	 */
	public static final NpcStringId DONT_PASS_UP_THE_CHANCE_TO_WIN_AN_S80_WEAPON;

	/**
	 * ID: 1800009<br>
	 * Message: ドロップ アイテム分配権限は # $s1が属する連合チャンネルにあります。
	 */
	public static final NpcStringId _S1S_COMMAND_CHANNEL_HAS_LOOTING_RIGHTS;

	/**
	 * ID: 1800010<br>
	 * Message: ドロップ アイテム分配権限を解除します。
	 */
	public static final NpcStringId LOOTING_RULES_ARE_NO_LONGER_ACTIVE;

	/**
	 * ID: 1800011<br>
	 * Message: わ～い！ご主人様のお戻りだ。必ず仕返ししてくださるはず！お前ももうちょっとで死体だよ。
	 */
	public static final NpcStringId OUR_MASTER_NOW_COMES_TO_CLAIM_OUR_VENGEANCE_SOON_YOU_WILL_ALL_BE_NOTHING_MORE_THAN_DIRT;

	/**
	 * ID: 1800012<br>
	 * Message: 黎明の君主に挑む者たちに死を
	 */
	public static final NpcStringId DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORDS_OF_DAWN;

	/**
	 * ID: 1800013<br>
	 * Message: 星座に挑む者たちに死を
	 */
	public static final NpcStringId DEATH_TO_THOSE_WHO_CHALLENGE_THE_LORD;

	/**
	 * ID: 1800014<br>
	 * Message: ブヒブヒ！ブタもやればできるんやでぇ！アンタラス顔負けの超強力 ピッグ スタンや！
	 */
	public static final NpcStringId OINK_OINK_PIGS_CAN_DO_IT_TOO_ANTHARAS_SURPASSING_SUPER_POWERED_PIG_STUN_HOW_DO_LIKE_THEM_APPLES;

	/**
	 * ID: 1800015<br>
	 * Message: ブヒブヒ！食らえっ！ヴァラカスでさえビビるウルトラ ピッグ フィアーや！
	 */
	public static final NpcStringId OINK_OINK_TAKE_THAT_VALAKAS_TERRORIZING_ULTRA_PIG_FEAR_HA_HA;

	/**
	 * ID: 1800016<br>
	 * Message: ブヒブヒ！アンタ、ホ～ンマしつこいなぁ！ええ加減にしいやっ！
	 */
	public static final NpcStringId OINK_OINK_GO_AWAY_STOP_BOTHERING_ME;

	/**
	 * ID: 1800017<br>
	 * Message: ブヒブヒ！みなさん集合や、集合！今こそオレらの力を見せたろうやないか！
	 */
	public static final NpcStringId OINK_OINK_PIGS_OF_THE_WORLD_UNITE_LETS_SHOW_THEM_OUR_STRENGTH;

	/**
	 * ID: 1800018<br>
	 * Message: 治療してくれはったん？おおきに！ブヒブヒ！
	 */
	public static final NpcStringId YOU_HEALED_ME_THANKS_A_LOT_OINK_OINK;

	/**
	 * ID: 1800019<br>
	 * Message: ブヒブヒ！なあ、アンタ！ホンマに治療してるんかいな？めっちゃ痛いやんけ！
	 */
	public static final NpcStringId OINK_OINK_THIS_TREATMENT_HURTS_TOO_MUCH_ARE_YOU_SURE_THAT_YOURE_TRYING_TO_HEAL_ME;

	/**
	 * ID: 1800020<br>
	 * Message: ブヒブヒ！世界調和と繁栄のために、このプリズム ストーンを使って変身や！
	 */
	public static final NpcStringId OINK_OINK_TRANSFORM_WITH_MOON_CRYSTAL_PRISM_POWER;

	/**
	 * ID: 1800021<br>
	 * Message: ブヒブヒ！あ～、いやや！元に戻りたぁない～～
	 */
	public static final NpcStringId OINK_OINK_NOOO_I_DONT_WANT_TO_GO_BACK_TO_NORMAL;

	/**
	 * ID: 1800022<br>
	 * Message: ブヒ！ワテはお金持ちやさかい、特別にサービスさしてもらいますわ！ほな、さいなら。ブヒブヒ！
	 */
	public static final NpcStringId OINK_OINK_IM_RICH_SO_IVE_GOT_PLENTY_TO_SHARE_THANKS;

	/**
	 * ID: 1800023<br>
	 * Message: 不吉な気配に包まれた武器ですね。危険かもしれませんから僕が捨ててあげます！ぷし～っ！
	 */
	public static final NpcStringId ITS_A_WEAPON_SURROUNDED_BY_AN_OMINOUS_AURA_ILL_DISCARD_IT_BECAUSE_IT_MAY_BE_DANGEROUS_MISS;

	/**
	 * ID: 1800024<br>
	 * Message: 悪から救ってくださったことを感謝いたします。
	 */
	public static final NpcStringId THANK_YOU_FOR_SAVING_ME_FROM_THE_CLUTCHES_OF_EVIL;

	/**
	 * ID: 1800025<br>
	 * Message: 今日はこのくらいにしておいてやろう。全員退却だ！
	 */
	public static final NpcStringId THAT_IS_IT_FOR_TODAYLETS_RETREAT_EVERYONE_PULL_BACK;

	/**
	 * ID: 1800026<br>
	 * Message: 助けてくださってありがとうございます。これはささやかなプレゼントです。
	 */
	public static final NpcStringId THANK_YOU_FOR_THE_RESCUE_ITS_A_SMALL_GIFT;

	/**
	 * ID: 1800027<br>
	 * Message: $s1、赤いクリスタルがありません．．．
	 */
	public static final NpcStringId S1_YOU_DONT_HAVE_A_RED_CRYSTAL;

	/**
	 * ID: 1800028<br>
	 * Message: $s1、青いクリスタルがありません．．．
	 */
	public static final NpcStringId S1_YOU_DONT_HAVE_A_BLUE_CRYSTAL;

	/**
	 * ID: 1800029<br>
	 * Message: $s1、透明なクリスタルがありません．．．
	 */
	public static final NpcStringId S1_YOU_DONT_HAVE_A_CLEAR_CRYSTAL;

	/**
	 * ID: 1800030<br>
	 * Message: $s1、私から離れ過ぎたら、あなたを送れませんよ。
	 */
	public static final NpcStringId S1_IF_YOU_ARE_TOO_FAR_AWAY_FROM_MEI_CANT_LET_YOU_GO;

	/**
	 * ID: 1800031<br>
	 * Message: 警報装置作動！侵入者発見！侵入者撃退プログラムを発動します！
	 */
	public static final NpcStringId AN_ALARM_HAS_BEEN_SET_OFF_EVERYBODY_WILL_BE_IN_DANGER_IF_THEY_ARE_NOT_TAKEN_CARE_OF_IMMEDIATELY;

	/**
	 * ID: 1800032<br>
	 * Message: 簡単にやられはしないわ！
	 */
	public static final NpcStringId IT_WILL_NOT_BE_THAT_EASY_TO_KILL_ME;

	/**
	 * ID: 1800033<br>
	 * Message: そ、そんな．．．私の秘密を知るものがいるなんて．．．
	 */
	public static final NpcStringId NOYOU_KNEW_MY_WEAKNESS;

	/**
	 * ID: 1800034<br>
	 * Message: お～い、誰かいませんかぁ？
	 */
	public static final NpcStringId HELLO_IS_ANYONE_THERE;

	/**
	 * ID: 1800035<br>
	 * Message: 誰もいないのかぁ～どのくらい隠れてたんだろう。何日も食べてないから、お腹空いて死にそうだよ！
	 */
	public static final NpcStringId IS_NO_ONE_THERE_HOW_LONG_HAVE_I_BEEN_HIDING_I_HAVE_BEEN_STARVING_FOR_DAYS_AND_CANNOT_HOLD_OUT_ANYMORE;

	/**
	 * ID: 1800036<br>
	 * Message: 誰か～おいしいクリスタルの破片をくれたら、あの冷たいティアーズの隠れ家を教えちゃってもいいけどな～むにゃむにゃ。
	 */
	public static final NpcStringId IF_SOMEONE_WOULD_GIVE_ME_SOME_OF_THOSE_TASTY_CRYSTAL_FRAGMENTS_I_WOULD_GLADLY_TELL_THEM_WHERE_TEARS_IS_HIDING_YUMMY_YUMMY;

	/**
	 * ID: 1800037<br>
	 * Message: お～い、地上から来た人たち～クリスタルの破片を持ってるなら、少しちょうだ～い。
	 */
	public static final NpcStringId HEY_YOU_FROM_ABOVE_THE_GROUND_LETS_SHARE_SOME_CRYSTAL_FRAGMENTS_IF_YOU_HAVE_ANY;

	/**
	 * ID: 1800038<br>
	 * Message: サクサクしてヒンヤリって感じ～うへへ～この味だよ！
	 */
	public static final NpcStringId CRISPY_AND_COLD_FEELING_TEEHEE_DELICIOUS;

	/**
	 * ID: 1800039<br>
	 * Message: ムシャムシャ、すっごくおいし～
	 */
	public static final NpcStringId YUMMY_THIS_IS_SO_TASTY;

	/**
	 * ID: 1800040<br>
	 * Message: クンクン、クリスタルの破片もっとくれ～
	 */
	public static final NpcStringId SNIFF_SNIFF_GIVE_ME_MORE_CRYSTAL_FRAGMENTS;

	/**
	 * ID: 1800041<br>
	 * Message: つれないな！一つだけもらっても嬉しくないよ！もっとたくさんくれない？
	 */
	public static final NpcStringId HOW_INSENSITIVE_ITS_NOT_NICE_TO_GIVE_ME_JUST_A_PIECE_CANT_YOU_GIVE_ME_MORE;

	/**
	 * ID: 1800042<br>
	 * Message: あ～お腹空いたよ～
	 */
	public static final NpcStringId AH_IM_HUNGRY;

	/**
	 * ID: 1800043<br>
	 * Message: 私が本物だよ～
	 */
	public static final NpcStringId IM_THE_REAL_ONE;

	/**
	 * ID: 1800044<br>
	 * Message: 私を選んでったら～
	 */
	public static final NpcStringId PICK_ME;

	/**
	 * ID: 1800045<br>
	 * Message: 信じてよ～
	 */
	public static final NpcStringId TRUST_ME;

	/**
	 * ID: 1800046<br>
	 * Message: アイツじゃなくて私が本物だよ！
	 */
	public static final NpcStringId NOT_THAT_DUDE_IM_THE_REAL_ONE;

	/**
	 * ID: 1800047<br>
	 * Message: ダメダメ、だまされちゃ！私が本物だからね！
	 */
	public static final NpcStringId DONT_BE_FOOLED_DONT_BE_FOOLED_IM_THE_REAL_ONE;

	/**
	 * ID: 1800048<br>
	 * Message: 本物のふりすればいいってことだよね！おっとっ！
	 */
	public static final NpcStringId JUST_ACT_LIKE_THE_REAL_ONE_OOPS;

	/**
	 * ID: 1800049<br>
	 * Message: 残念でした～
	 */
	public static final NpcStringId YOUVE_BEEN_FOOLED;

	/**
	 * ID: 1800050<br>
	 * Message: 申し訳ないけど．．．私はニセモノだよ～
	 */
	public static final NpcStringId SORRY_BUT_IM_THE_FAKE_ONE;

	/**
	 * ID: 1800051<br>
	 * Message: 私が本物だよ～イエ～イ！
	 */
	public static final NpcStringId IM_THE_REAL_ONE_PHEW;

	/**
	 * ID: 1800052<br>
	 * Message: それくらい見つけられないの？
	 */
	public static final NpcStringId CANT_YOU_EVEN_FIND_OUT;

	/**
	 * ID: 1800053<br>
	 * Message: 私のところに来て～
	 */
	public static final NpcStringId FIND_ME;

	/**
	 * ID: 1800054<br>
	 * Message: あらっ！私だってどうしてわかったの？
	 */
	public static final NpcStringId HUH_HOW_DID_YOU_KNOW_IT_WAS_ME;

	/**
	 * ID: 1800055<br>
	 * Message: 賢い選択だね～うっしっし～
	 */
	public static final NpcStringId EXCELLENT_CHOICE_TEEHEE;

	/**
	 * ID: 1800056<br>
	 * Message: よ～くできました～
	 */
	public static final NpcStringId YOUVE_DONE_WELL;

	/**
	 * ID: 1800057<br>
	 * Message: おお～センスあるんじゃない？
	 */
	public static final NpcStringId OH_VERY_SENSIBLE;

	/**
	 * ID: 1800058<br>
	 * Message: ウオオォォォ！ 我が名はバーラー！ 思い知るがよい、我が力を！
	 */
	public static final NpcStringId BEHOLD_THE_MIGHTY_POWER_OF_BAYLOR_FOOLISH_MORTAL;

	/**
	 * ID: 1800059<br>
	 * Message: 誰一人として生きては帰さんぞ！
	 */
	public static final NpcStringId NO_ONE_IS_GOING_TO_SURVIVE;

	/**
	 * ID: 1800060<br>
	 * Message: 地獄とはこういうところだ！
	 */
	public static final NpcStringId YOULL_SEE_WHAT_HELL_IS_LIKE;

	/**
	 * ID: 1800061<br>
	 * Message: この虫ケラどもが！ 地獄に送ってやるわ！
	 */
	public static final NpcStringId YOU_WILL_BE_PUT_IN_JAIL;

	/**
	 * ID: 1800062<br>
	 * Message: この程度か。笑わせるな！地獄へ消えうせろ！
	 */
	public static final NpcStringId WORTHLESS_CREATURE_GO_TO_HELL;

	/**
	 * ID: 1800063<br>
	 * Message: 我が恐怖の力でお前の精神を支配してやろう！
	 */
	public static final NpcStringId ILL_GIVE_YOU_SOMETHING_THAT_YOULL_NEVER_FORGET;

	/**
	 * ID: 1800064<br>
	 * Message: 何を信じて私を選んだのやら．．．ウフフフ。
	 */
	public static final NpcStringId WHY_DID_YOU_TRUST_TO_CHOOSE_ME_HAHAHAHA;

	/**
	 * ID: 1800065<br>
	 * Message: 私を選んだことを後悔させてやろう．．．
	 */
	public static final NpcStringId ILL_MAKE_YOU_REGRET_THAT_YOU_EVER_CHOSE_ME;

	/**
	 * ID: 1800066<br>
	 * Message: 生きて帰れるとでも思っているのか．．．
	 */
	public static final NpcStringId DONT_EXPECT_TO_GET_OUT_ALIVE;

	/**
	 * ID: 1800067<br>
	 * Message: 魔王ベレスよ。我に力を与えたまえ！
	 */
	public static final NpcStringId DEMON_KING_BELETH_GIVE_ME_THE_POWER_AAAHH;

	/**
	 * ID: 1800068<br>
	 * Message: なんと．．．パプリオンの力を感じる．．．
	 */
	public static final NpcStringId NO_I_FEEL_THE_POWER_OF_FAFURION;

	/**
	 * ID: 1800069<br>
	 * Message: パプリオンよ～この哀れな巫女にお力を！
	 */
	public static final NpcStringId FAFURION_PLEASE_GIVE_POWER_TO_THIS_HELPLESS_WITCH;

	/**
	 * ID: 1800070<br>
	 * Message: 私はこの場所に捕らわれの身。バーラーの力を少し弱まらせるくらいしかお力添えできません．．．
	 */
	public static final NpcStringId I_CANT_HELP_YOU_MUCH_BUT_I_CAN_WEAKEN_THE_POWER_OF_BAYLOR_SINCE_IM_LOCKED_UP_HERE;

	/**
	 * ID: 1800071<br>
	 * Message: かなりの実力だな。このくらいなら私を超える腕並みだということを認めてやろう。カギを持ってここを発ちなさい。
	 */
	public static final NpcStringId YOUR_SKILL_IS_IMPRESSIVE_ILL_ADMIT_THAT_YOU_ARE_GOOD_ENOUGH_TO_PASS_TAKE_THE_KEY_AND_LEAVE_THIS_PLACE;

	/**
	 * ID: 1800072<br>
	 * Message: 持っているもの全部出せ！そうすれば命だけは見逃してやるぞ！
	 */
	public static final NpcStringId GIVE_ME_ALL_YOU_HAVE_ITS_THE_ONLY_WAY_ILL_LET_YOU_LIVE;

	/**
	 * ID: 1800073<br>
	 * Message: お．．．お腹すいたぁ。
	 */
	public static final NpcStringId HUN_HUNGRY;

	/**
	 * ID: 1800074<br>
	 * Message: サボってるんじゃないぞ！この虫けらどもめ！
	 */
	public static final NpcStringId DONT_BE_LAZY_YOU_BASTARDS;

	/**
	 * ID: 1800075<br>
	 * Message: 鋼鉄の城の回し者じゃなかったのか．．．びっくりさせないでくれよ．．．
	 */
	public static final NpcStringId THEY_ARE_JUST_HENCHMEN_OF_THE_IRON_CASTLE_WHY_DID_WE_HIDE;

	/**
	 * ID: 1800076<br>
	 * Message: 野郎ども！こいつらに俺たちの力を見せ付けてやれ！
	 */
	public static final NpcStringId GUYS_SHOW_THEM_OUR_POWER;

	/**
	 * ID: 1800077<br>
	 * Message: よくぞここまでたどり着いたな．．．だが秘密の扉を開けさせはせぬぞ！
	 */
	public static final NpcStringId YOU_HAVE_FINALLY_COME_HERE_BUT_YOU_WILL_NOT_BE_ABLE_TO_FIND_THE_SECRET_ROOM;

	/**
	 * ID: 1800078<br>
	 * Message: よくぞここまで来たな！だがカギはそう簡単には渡さんぞ！
	 */
	public static final NpcStringId YOU_HAVE_DONE_WELL_IN_FINDING_ME_BUT_I_CANNOT_JUST_HAND_YOU_THE_KEY;
	
	/**
	 * ID: 1800079<br>
	 * Message: 残り$s1です。
	 */
	public static final NpcStringId S1_SECONDS_REMAINING;

	/**
	 * ID: 1800081<br>
	 * Message: 入場管理人と離れすぎているため、競技が自動的にキャンセルされました。
	 */
	public static final NpcStringId THE_MATCH_IS_AUTOMATICALLY_CANCELED_BECAUSE_YOU_ARE_TOO_FAR_FROM_THE_ADMISSION_MANAGER;

	/**
	 * ID: 1800082<br>
	 * Message: ああ、緊張する．．．もうすぐ出番なのにどうしよう．．．くすん。
	 */
	public static final NpcStringId UGH_I_HAVE_BUTTERFLIES_IN_MY_STOMACH_THE_SHOW_STARTS_SOON;

	/**
	 * ID: 1800083<br>
	 * Message: 本日は特別イベントにお集まりいただき、まことにありがとうございます。
	 */
	public static final NpcStringId THANK_YOU_ALL_FOR_COMING_HERE_TONIGHT;

	/**
	 * ID: 1800084<br>
	 * Message: このようなイベントが開催できたことを嬉しく思います。
	 */
	public static final NpcStringId IT_IS_AN_HONOR_TO_HAVE_THE_SPECIAL_SHOW_TODAY;

	/**
	 * ID: 1800085<br>
	 * Message: この幻想の島では、皆様にお楽しみいただけるよう、常に真心を尽くしております。
	 */
	public static final NpcStringId FANTASY_ISLE_IS_FULLY_COMMITTED_TO_YOUR_HAPPINESS;

	/**
	 * ID: 1800086<br>
	 * Message: それでは、このイベントに華を添えるアデンが生んだ最高の美人歌手、ライラミラさんをご紹介します！
	 */
	public static final NpcStringId NOW_ID_LIKE_TO_INTRODUCE_THE_MOST_BEAUTIFUL_SINGER_IN_ADEN_PLEASE_WELCOMELEYLA_MIRA;

	/**
	 * ID: 1800087<br>
	 * Message: どうぞ～
	 */
	public static final NpcStringId HERE_SHE_COMES;

	/**
	 * ID: 1800088<br>
	 * Message: ライラミラさん、ありがとうございました。お次は．．．
	 */
	public static final NpcStringId THANK_YOU_VERY_MUCH_LEYLA;

	/**
	 * ID: 1800089<br>
	 * Message: はい。お次はアデンが誇る名人芸をお楽しみいただきましょう。
	 */
	public static final NpcStringId NOW_WERE_IN_FOR_A_REAL_TREAT;

	/**
	 * ID: 1800090<br>
	 * Message: まず、ワールド ツアーを終え、この場に駆け付けてくださった幻想の島の曲芸団の皆さんです！
	 */
	public static final NpcStringId JUST_BACK_FROM_THEIR_WORLD_TOUR_PUT_YOUR_HANDS_TOGETHER_FOR_THE_FANTASY_ISLE_CIRCUS;

	/**
	 * ID: 1800091<br>
	 * Message: 曲芸団の皆さ～ん、はりきってどうぞ！
	 */
	public static final NpcStringId COME_ON_EVERYONE;

	/**
	 * ID: 1800092<br>
	 * Message: いかがでしたか。素晴らしい舞台でしたね。
	 */
	public static final NpcStringId DID_YOU_LIKE_IT_THAT_WAS_SO_AMAZING;

	/**
	 * ID: 1800093<br>
	 * Message: さあ、お次は各地から集まった才能溢れる方たちの舞台です。
	 */
	public static final NpcStringId NOW_WE_ALSO_INVITED_INDIVIDUALS_WITH_SPECIAL_TALENTS;

	/**
	 * ID: 1800094<br>
	 * Message: では！トップバッターお願いいたします！
	 */
	public static final NpcStringId LETS_WELCOME_THE_FIRST_PERSON_HERE;

	/**
	 * ID: 1800095<br>
	 * Message: ；；；；う～ん。
	 */
	public static final NpcStringId OH;

	/**
	 * ID: 1800096<br>
	 * Message: はいっ。では次の方お願いいたします。
	 */
	public static final NpcStringId OKAY_NOW_HERE_COMES_THE_NEXT_PERSON_COME_ON_UP_PLEASE;

	/**
	 * ID: 1800097<br>
	 * Message: おお、かなりすごいですねぇ。
	 */
	public static final NpcStringId OH_IT_LOOKS_LIKE_SOMETHING_GREAT_IS_GOING_TO_HAPPEN_RIGHT;

	/**
	 * ID: 1800098<br>
	 * Message: ハッ；；；；
	 */
	public static final NpcStringId OH_MY;

	/**
	 * ID: 1800099<br>
	 * Message: す、すばらしい！では最後の方、お願いいたします！
	 */
	public static final NpcStringId THATS_G_GREAT_NOW_HERE_COMES_THE_LAST_PERSON;

	/**
	 * ID: 1800100<br>
	 * Message: これにて奇人・超人ショーをお開きとさせていただきます。
	 */
	public static final NpcStringId NOW_THIS_IS_THE_END_OF_TODAYS_SHOW;

	/**
	 * ID: 1800101<br>
	 * Message: 皆さんどうでした？楽しんでいただけましたか。
	 */
	public static final NpcStringId HOW_WAS_IT_I_HOPE_YOU_ALL_ENJOYED_IT;

	/**
	 * ID: 1800102<br>
	 * Message: 私ども幻想の島では、皆様に喜んでいただけるように様々なイベントを用意しております。どうぞご期待ください。
	 */
	public static final NpcStringId PLEASE_REMEMBER_THAT_FANTASY_ISLE_IS_ALWAYS_PLANNING_A_LOT_OF_GREAT_SHOWS_FOR_YOU;

	/**
	 * ID: 1800103<br>
	 * Message: 名残惜しいですが、これにて閉演とさせていただきます。
	 */
	public static final NpcStringId WELL_I_WISH_I_COULD_CONTINUE_ALL_NIGHT_LONG_BUT_THIS_IS_IT_FOR_TODAY_THANK_YOU;

	/**
	 * ID: 1800104<br>
	 * Message: それでは皆様ごきげんよう。
	 */
	public static final NpcStringId WE_LOVE_YOU;

	/**
	 * ID: 1800105<br>
	 * Message: なんで誰も集まってくれないの．．．もうすぐイベントが始まるのに．．．くすん。
	 */
	public static final NpcStringId HOW_COME_PEOPLE_ARE_NOT_HERE_WE_ARE_ABOUT_TO_START_THE_SHOW_HMM;

	/**
	 * ID: 1800106<br>
	 * Message: 相手チームが競技をキャンセルしました。
	 */
	public static final NpcStringId THE_OPPONENT_TEAM_CANCELED_THE_MATCH;

	/**
	 * ID: 1800107<br>
	 * Message: そう簡単には手に入らないだろう。
	 */
	public static final NpcStringId ITS_NOT_EASY_TO_OBTAIN;

	/**
	 * ID: 1800108<br>
	 * Message: 正気でここに入ってくるとはな．．．
	 */
	public static final NpcStringId YOURE_OUT_OF_YOUR_MIND_COMING_HERE;

	/**
	 * ID: 1800109<br>
	 * Message: 敵が侵入した！急いで工程にとりかかれ！
	 */
	public static final NpcStringId ENEMY_INVASION_HURRY_UP;

	/**
	 * ID: 1800110<br>
	 * Message: 私のせいで．．．工程が．．．遅れては．．．ならぬ．．．
	 */
	public static final NpcStringId PROCESS_SHOULDNT_BE_DELAYED_BECAUSE_OF_ME;

	/**
	 * ID: 1800111<br>
	 * Message: いいだろう。レオダスはもうお前のものだ！
	 */
	public static final NpcStringId ALRIGHT_NOW_LEODAS_IS_YOURS;

	/**
	 * ID: 1800112<br>
	 * Message: 新たな奴隷が必要なようだな．．．すぐに戻るから待ってろ！
	 */
	public static final NpcStringId WE_MIGHT_NEED_NEW_SLAVES_ILL_BE_BACK_SOON_SO_WAIT;

	/**
	 * ID: 1800113<br>
	 * Message: タイム リープ装置の起動成功！
	 */
	public static final NpcStringId TIME_RIFT_DEVICE_ACTIVATION_SUCCESSFUL;

	/**
	 * ID: 1800114<br>
	 * Message: 我に自由を！さもなくば死を！
	 */
	public static final NpcStringId FREEDOM_OR_DEATH;

	/**
	 * ID: 1800115<br>
	 * Message: これが真の戦士たちの意志だ！
	 */
	public static final NpcStringId THIS_IS_THE_WILL_OF_TRUE_WARRIORS;

	/**
	 * ID: 1800116<br>
	 * Message: 今晩の飯は地獄で食うんだな！
	 */
	public static final NpcStringId WELL_HAVE_DINNER_IN_HELL;
	
	/**
	 * ID: 1800117<br>
	 * Message: 起動装置の初期化：時間設定 現時刻から$s1分後
	 */
	public static final NpcStringId DETONATOR_INITIALIZATION_TIME_S1_MINUTES_FROM_NOW;

	/**
	 * ID: 1800118<br>
	 * Message: ジジジッ：時次元の干渉による不具合：前進現象発生：
	 */
	public static final NpcStringId ZZZZ_CITY_INTERFERENCE_ERROR_FORWARD_EFFECT_CREATED;

	/**
	 * ID: 1800119<br>
	 * Message: ジジジッ：時次元の干渉による不具合：回帰現象発生：
	 */
	public static final NpcStringId ZZZZ_CITY_INTERFERENCE_ERROR_RECURRENCE_EFFECT_CREATED;

	/**
	 * ID: 1800120<br>
	 * Message: 監視兵がすぐかぎつけて来ます！すぐ逃げてください！
	 */
	public static final NpcStringId GUARDS_ARE_COMING_RUN;

	/**
	 * ID: 1800121<br>
	 * Message: もう一人でも脱出できます！
	 */
	public static final NpcStringId NOW_I_CAN_ESCAPE_ON_MY_OWN;

	/**
	 * ID: 1800122<br>
	 * Message: 助けてくださってありがとうございます！
	 */
	public static final NpcStringId THANKS_FOR_YOUR_HELP;

	/**
	 * ID: 1800123<br>
	 * Message: 相手チームがスタジアムへの入場条件を満たしておらず競技がキャンセルされました。
	 */
	public static final NpcStringId MATCH_CANCELLED_OPPONENT_DID_NOT_MEET_THE_STADIUM_ADMISSION_REQUIREMENTS;

	/**
	 * ID: 1800124<br>
	 * Message: くははは～っ。苦痛にのたうちまわりながら死んでゆくがいい！
	 */
	public static final NpcStringId HA_HA_YES_DIE_SLOWLY_WRITHING_IN_PAIN_AND_AGONY;

	/**
	 * ID: 1800125<br>
	 * Message: もっとだ．．．もっと強烈な苦痛が．．．必要なのだ．．．
	 */
	public static final NpcStringId MORE_NEED_MORE_SEVERE_PAIN;

	/**
	 * ID: 1800126<br>
	 * Message: うああ！命が吸い取られるぅぅっ！
	 */
	public static final NpcStringId AHH_MY_LIFE_IS_BEING_DRAINED_OUT;

	/**
	 * ID: 1800127<br>
	 * Message: 熱いものがこの体から湧き上がってくるぞ！
	 */
	public static final NpcStringId SOMETHING_IS_BURNING_INSIDE_MY_BODY;

	/**
	 * ID: 1800128<br>
	 * Message: $s1、スタジアムのレベルに合っていません。
	 */
	public static final NpcStringId S1_NOT_ADEQUATE_FOR_THE_STADIUM_LEVEL;

	/**
	 * ID: 1800129<br>
	 * Message: $s1、お前の命．．．ありがたく受け取ろう．．．
	 */
	public static final NpcStringId S1_THANK_YOU_FOR_GIVING_ME_YOUR_LIFE;

	/**
	 * ID: 1800130<br>
	 * Message: ダメだ、失敗だ！ダリオン様、お許しください！
	 */
	public static final NpcStringId I_FAILED_PLEASE_FORGIVE_ME_DARION;

	/**
	 * ID: 1800131<br>
	 * Message: $s1、必ず戻ってくるぞ．．．安心するのはまだ早い．．．
	 */
	public static final NpcStringId S1_ILL_BE_BACK_DONT_GET_COMFORTABLE;

	/**
	 * ID: 1800132<br>
	 * Message: この私がこのままやられると思ったら．．．大間違いだ！
	 */
	public static final NpcStringId IF_YOU_THINK_IM_GIVING_UP_LIKE_THIS_YOURE_WRONG;

	/**
	 * ID: 1800133<br>
	 * Message: やられっぱなしで黙ってられるか！
	 */
	public static final NpcStringId SO_YOURE_JUST_GOING_TO_WATCH_ME_SUFFER;

	/**
	 * ID: 1800134<br>
	 * Message: まだまだだぁ！
	 */
	public static final NpcStringId ITS_NOT_OVER_YET;

	/**
	 * ID: 1800135<br>
	 * Message: ははっ！死ぬのがそんなに怖いか．．．どれ．．．時間内に私を探し出せば．．．道が開けるかも．．．知れぬぞ．．．
	 */
	public static final NpcStringId HA_HA_YOU_WERE_SO_AFRAID_OF_DEATH_LET_ME_SEE_IF_YOU_FIND_ME_IN_TIME_MAYBE_YOU_CAN_FIND_A_WAY;

	/**
	 * ID: 1800136<br>
	 * Message: 助けてください．．．何かが私の首をじわじわと絞めている．．．
	 */
	public static final NpcStringId DONT_KILL_ME_PLEASE_SOMETHINGS_STRANGLING_ME;

	/**
	 * ID: 1800137<br>
	 * Message: 今夜のラッキーな奴は誰かな？はは！楽しみだな！
	 */
	public static final NpcStringId WHO_WILL_BE_THE_LUCKY_ONE_TONIGHT_HA_HA_CURIOUS_VERY_CURIOUS;

	/**
	 * ID: 1800138<br>
	 * Message: チュッ！チュッ！ピッグが使ってたスタンより強力だぜ！
	 */
	public static final NpcStringId SQUEAK_THIS_WILL_BE_STRONGER_THAN_THE_STUN_THE_PIG_USED_LAST_TIME;

	/**
	 * ID: 1800139<br>
	 * Message: チュッ！チュッ！ヴァラカスも尻尾を巻いて逃げるウルトラ フィアーだ！受けてみやがれっ！
	 */
	public static final NpcStringId SQUEAK_HERE_IT_GOES_EXTREMELY_SCARY_EVEN_TO_VALAKAS;

	/**
	 * ID: 1800140<br>
	 * Message: チュッ！チュッ！どっか行け！もうほっといてくれよっ！
	 */
	public static final NpcStringId SQUEAK_GO_AWAY_LEAVE_US_ALONE;

	/**
	 * ID: 1800141<br>
	 * Message: チュッ！チュッ！みんな、集まれぇ！俺たちの力を見せてやれっ！
	 */
	public static final NpcStringId SQUEAK_GUYS_GATHER_UP_LETS_SHOW_OUR_POWER;

	/**
	 * ID: 1800142<br>
	 * Message: まぁ、別にありがたいからあげるわけじゃないんだぜ～チェッ！
	 */
	public static final NpcStringId ITS_NOT_LIKE_IM_GIVING_THIS_BECAUSE_IM_GRATEFUL_SQUEAK;

	/**
	 * ID: 1800143<br>
	 * Message: チュッ！チュッ！おしりがすっごい痛いんだけど．．．本当に治療してんの！？
	 */
	public static final NpcStringId SQUEAK_EVEN_IF_IT_IS_TREATMENT_MY_BOTTOM_HURTS_SO_MUCH;

	/**
	 * ID: 1800144<br>
	 * Message: チュッ！チュッ！プリズム ストーンで変身だ！
	 */
	public static final NpcStringId SQUEAK_TRANSFORM_TO_MOON_CRYSTAL_PRISM_POWER;

	/**
	 * ID: 1800145<br>
	 * Message: チュッ！チュッ！うわっ！元の姿に戻るの嫌だよぉ～～
	 */
	public static final NpcStringId SQUEAK_OH_NO_I_DONT_WANT_TO_TURN_BACK_AGAIN;

	/**
	 * ID: 1800146<br>
	 * Message: チュッ！チュッ！俺が金持ちだから、いっぱいあげられるんだよ。ありがとうね。
	 */
	public static final NpcStringId SQUEAK_IM_SPECIALLY_GIVING_YOU_A_LOT_SINCE_IM_RICH_THANK_YOU;

	/**
	 * ID: 1800147<br>
	 * Message: ブヒ！ブヒ！怒りが頂点まで達したでぇ！パワー！無限なるパワーやっ！
	 */
	public static final NpcStringId OINK_OINK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER;

	/**
	 * ID: 1800148<br>
	 * Message: ブヒ！ブヒ！むっちゃむかつく！もうあかんわ！
	 */
	public static final NpcStringId OINK_OINK_IM_REALLY_FURIOUS_RIGHT_NOW;

	/**
	 * ID: 1800149<br>
	 * Message: チュッ！チュッ！怒りが頂点まで達したぞ！パワー！無限なるパワーだ！
	 */
	public static final NpcStringId SQUEAK_RAGE_IS_BOILING_UP_INSIDE_OF_ME_POWER_INFINITE_POWER;

	/**
	 * ID: 1800150<br>
	 * Message: チュッ！チュッ！俺もうかなりキレてるぜ！
	 */
	public static final NpcStringId SQUEAK_IM_REALLY_FURIOUS_RIGHT_NOW;

	/**
	 * ID: 1800162<br>
	 * Message: Gグレード
	 */
	public static final NpcStringId G_RANK;

	/**
	 * ID: 1800163<br>
	 * Message: ほお．．．滅ぶべき存在がこれほどの力を持っていようとは誰しも予想しなかったろう。
	 */
	public static final NpcStringId HUH_NO_ONE_WOULD_HAVE_GUESSED_THAT_A_DOOMED_CREATURE_WOULD_BE_SO_POWERFUL;

	/**
	 * ID: 1800164<br>
	 * Message: Sグレード
	 */
	public static final NpcStringId S_GRADE;

	/**
	 * ID: 1800165<br>
	 * Message: Aグレード
	 */
	public static final NpcStringId A_GRADE;

	/**
	 * ID: 1800166<br>
	 * Message: Bグレード
	 */
	public static final NpcStringId B_GRADE;

	/**
	 * ID: 1800167<br>
	 * Message: Cグレード
	 */
	public static final NpcStringId C_GRADE;

	/**
	 * ID: 1800168<br>
	 * Message: Dグレード
	 */
	public static final NpcStringId D_GRADE;

	/**
	 * ID: 1800169<br>
	 * Message: Fグレード
	 */
	public static final NpcStringId F_GRADE;

	/**
	 * ID: 1800170<br>
	 * Message: これは．．．伝説の真の英雄でなければできないほどの偉大な業績だ！
	 */
	public static final NpcStringId THIS_IS_THIS_IS_A_GREAT_ACHIEVEMENT_THAT_IS_WORTHY_OF_THE_TRUE_HEROES_OF_LEGEND;

	/**
	 * ID: 1800171<br>
	 * Message: 実にほれぼれとする。カマロカを通じてやってくる侵略の魔の手を遅らせた驚くべき活躍だ。
	 */
	public static final NpcStringId ADMIRABLE_YOU_GREATLY_DECREASED_THE_SPEED_OF_INVASION_THROUGH_KAMALOKA;

	/**
	 * ID: 1800172<br>
	 * Message: 実に立派だ。他の冒険家たちのお手本にするほどの実力だ。
	 */
	public static final NpcStringId VERY_GOOD_YOUR_SKILL_MAKES_YOU_A_MODEL_FOR_OTHER_ADVENTURERS_TO_FOLLOW;

	/**
	 * ID: 1800173<br>
	 * Message: よくやった。他の冒険家たちもお前ほどの成果を上げられたら、徐々に希望の光が見えてくるだろう。
	 */
	public static final NpcStringId GOOD_WORK_IF_ALL_ADVENTURERS_PRODUCE_RESULTS_LIKE_YOU_WE_WILL_SLOWLY_START_TO_SEE_THE_GLIMMER_OF_HOPE;

	/**
	 * ID: 1800174<br>
	 * Message: 残念だが、リム カマロカは誰でも気軽に近づける存在ではないようだ。
	 */
	public static final NpcStringId UNFORTUNATELY_IT_SEEMS_THAT_RIM_KAMALOKA_CANNOT_BE_EASILY_APPROACHED_BY_EVERYONE;

	/**
	 * ID: 1800175<br>
	 * Message: 失望した。お前をリム カマロカのところに送ったのは間違いだったようだ。
	 */
	public static final NpcStringId HOW_DISAPPOINTING_IT_LOOKS_LIKE_I_MADE_A_MISTAKE_IN_SENDING_YOU_INSIDE_RIM_KAMALOKA;

	/**
	 * ID: 1800176<br>
	 * Message: しん、にゅう、しゃ、しん、にゅう、しゃ、だ。
	 */
	public static final NpcStringId INTRUDER_ALERT_INTRUDER_ALERT;

	/**
	 * ID: 1800177<br>
	 * Message: 何をしているんだ！さっさと私を助けろ！
	 */
	public static final NpcStringId WHAT_ARE_YOU_DOING_HURRY_UP_AND_HELP_ME;

	/**
	 * ID: 1800178<br>
	 * Message: ここまで私を怒らせるとはな．．．生かしちゃおけない！
	 */
	public static final NpcStringId IVE_HAD_IT_UP_TO_HERE_WITH_YOU_ILL_TAKE_CARE_OF_YOU;

	/**
	 * ID: 1800179<br>
	 * Message: うああああ～心が崩れていく！
	 */
	public static final NpcStringId AH_MY_MIND_IS_A_WRECK;

	/**
	 * ID: 1800180<br>
	 * Message: 私の手下の数などたかが知れてると思ってるなら大間違いだ！
	 */
	public static final NpcStringId IF_YOU_THOUGHT_THAT_MY_SUBORDINATES_WOULD_BE_SO_FEW_YOU_ARE_MISTAKEN;

	/**
	 * ID: 1800181<br>
	 * Message: あまりお役には立たないでしょうが、助太刀いたします。
	 */
	public static final NpcStringId THERES_NOT_MUCH_I_CAN_DO_BUT_I_WANT_TO_HELP_YOU;

	/**
	 * ID: 1800182<br>
	 * Message: おい、てめえら！$s1！を痛い目にあわしてやりな！
	 */
	public static final NpcStringId YOU_S1_ATTACK_THEM;

	/**
	 * ID: 1800183<br>
	 * Message: 出でよ、我が手下どもよ！私の命をすり減らしてお前らを呼んだのだ。命を捨てる覚悟であいつらを片付けろ！
	 */
	public static final NpcStringId COME_OUT_MY_SUBORDINATE_I_SUMMON_YOU_TO_DRIVE_THEM_OUT;

	/**
	 * ID: 1800184<br>
	 * Message: 大したことはできませんが、この命捧げてでもお力になります。
	 */
	public static final NpcStringId THERES_NOT_MUCH_I_CAN_DO_BUT_I_WILL_RISK_MY_LIFE_TO_HELP_YOU;

	/**
	 * ID: 1800185<br>
	 * Message: くうっ！こいつは痛いぜ！
	 */
	public static final NpcStringId ARG_THE_PAIN_IS_MORE_THAN_I_CAN_STAND;

	/**
	 * ID: 1800186<br>
	 * Message: あああっ！私の急所をどうして．．．
	 */
	public static final NpcStringId AHH_HOW_DID_HE_FIND_MY_WEAKNESS;

	/**
	 * ID: 1800187<br>
	 * Message: お前が倒したカナビオンﾄどもからカマロカのエッセンスを集めるのに成功したぞ。さあ、これだ。
	 */
	public static final NpcStringId WE_WERE_ABLE_TO_SUCCESSFULLY_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE;

	/**
	 * ID: 1800188<br>
	 * Message: だが、お前が倒したカナビオンどもからカマロカのエッセンスもそれなりに集めることができた。さあ、これだ。
	 */
	public static final NpcStringId BUT_WE_WERE_ABLE_TO_COLLECT_SOMEHOW_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_HERE_THEY_ARE;

	/**
	 * ID: 1800189<br>
	 * Message: お前が倒したカナビオンどもに宿っている闇のオーラは微弱であった。悪いがカマロカのエッセンスを集めるのに失敗したようだ。
	 */
	public static final NpcStringId IM_SORRY_BUT_WE_WERE_UNABLE_TO_COLLECT_THE_ESSENCE_OF_KAMALOKA_FROM_THE_KANABIONS_THAT_YOU_DEFEATED_BECAUSE_THEIR_DARK_ENERGY_WAS_TOO_WEAK;

	/**
	 * ID: 1800190<br>
	 * Message: 単に敵を倒しただけではなく、我々が何を望んでいるのかをお前は充分わかっているようだな。
	 */
	public static final NpcStringId RATHER_THAN_SIMPLY_DEFEATING_THE_ENEMIES_YOU_SEEM_TO_UNDERSTAND_OUR_GOAL_AND_PURPOSE_AS_WELL;

	/**
	 * ID: 1800191<br>
	 * Message: ドップラーやボイドはカナビオンの闇のオーラが一層強化された形だ。カマロカの攻撃を食い止めるためには、やつらを倒すことに一層力を入れねばならない。
	 */
	public static final NpcStringId DOPPLERS_AND_VOIDS_POSSESS_AN_ENHANCED_AMOUNT_OF_THE_KANABIONS_DARK_ENERGY_SO_IT_IS_IMPORTANT_TO_CONCENTRATE_ON_DEFEATING_THEM_WHEN_BLOCKING_THE_KAMALOKIANS_ATTACK;

	/**
	 * ID: 1800192<br>
	 * Message: カナビオンが新たなカナビオンを作っているのを見たことがあるか。攻撃のときや倒すときに、最大のダメージを与えると、そんな場面に出くわすこともある。
	 */
	public static final NpcStringId HAVE_YOU_SEEN_KANABIONS_BEING_REMADE_AS_NEW_KANABIONS_SOMETIMES_YOU_CAN_SEE_IT_OCCUR_MORE_OFTEN_BY_INFLICTING_GREAT_DAMAGE_DURING_AN_ATTACK_OR_AT_THE_MOMENT_YOU_DEFEAT_THEM;

	/**
	 * ID: 1800193<br>
	 * Message: どんな戦闘においてもそうであるように、リム カマロカ内部でもやはり自分を守ることが最優先なのだ。それを忘れるでない。我々は無謀なチャレンジなど望んではいないのだ。
	 */
	public static final NpcStringId AS_IN_ANY_OTHER_BATTLE_IT_IS_CRITICAL_TO_PROTECT_YOURSELF_WHILE_YOU_ARE_INSIDE_RIM_KAMALOKA_WE_DO_NOT_WANT_TO_ATTACK_RECKLESSLY;

	/**
	 * ID: 1800194<br>
	 * Message: 我々は一度の勝利より、完全なる一人の戦士を育てることに価値を置いているのだ。もし今回他人に頼ってしまったなら、次は自分の力だけでチャレンジしてみるのはどうだ？
	 */
	public static final NpcStringId WE_VALUE_DEVELOPING_AN_INDIVIDUALS_OVERALL_POWER_RATHER_THAN_A_ONE_TIME_VICTORY_IF_YOU_RELIED_ON_ANOTHER_PERSONS_SUPPORT_THIS_TIME_WHY_DONT_YOU_TRY_TO_RELY_ON_YOUR_OWN_STRENGTH_NEXT_TIME;

	/**
	 * ID: 1800195<br>
	 * Message: さきほどの戦闘が自分のレベルに合っていると思っているのか。お遊びでレベルの低いカナビオンだけに相手にするのは、戦力の無駄に見えるがな。
	 */
	public static final NpcStringId ARE_YOU_SURE_THAT_THE_BATTLE_JUST_NOW_WAS_AT_THE_APPROPRIATE_LEVEL_BOTHERING_LOWER_KANABIONS_AS_IF_FOR_MERE_ENTERTAINMENT_IS_CONSIDERED_TO_BE_A_WASTED_BATTLE_FOR_US;

	/**
	 * ID: 1800196<br>
	 * Message: 最高の勝利とは与えられた手段を総動員して、相手にチャンスを与えず、できるだけ早く終わらせることだ。そう思わないか。
	 */
	public static final NpcStringId THE_GREATEST_VICTORY_INVOLVES_USING_ALL_AVAILABLE_RESOURCES_ELIMINATING_ALL_OF_THE_ENEMYS_OPPORTUNITIES_AND_BRINGING_IT_TO_THE_FASTEST_POSSIBLE_END_DONT_YOU_THINK_SO;

	/**
	 * ID: 1800197<br>
	 * Message: 非常事態発生！外壁耐久度が急激に落ちている！
	 */
	public static final NpcStringId EMERGENCY_EMERGENCY_THE_OUTER_WALL_IS_WEAKENING_RAPIDLY;

	/**
	 * ID: 1800198<br>
	 * Message: 外壁耐久度残り$s1！
	 */
	public static final NpcStringId THE_REMAINING_STRENGTH_OF_THE_OUTER_WALL_IS_S1;

	/**
	 * ID: 1800199<br>
	 * Message: パスファインダーの救援者
	 */
	public static final NpcStringId PATHFINDER_SAVIOR;

	/**
	 * ID: 1800200<br>
	 * Message: パスファインダーの協力者
	 */
	public static final NpcStringId PATHFINDER_SUPPORTER;

	/**
	 * ID: 1800201<br>
	 * Message: まだ新しい肉体に慣れきっていない一部のカナビオンたちは、身体構造が非常にもろくなることがある。そのときを狙って攻撃すれば、非常に効果を上げられるんだ。
	 */
	public static final NpcStringId SOME_KANABIONS_WHO_HAVENT_FULLY_ADJUSTED_YET_TO_THEIR_NEW_PHYSICAL_FORM_ARE_KNOWN_TO_EXHIBIT_SYMPTOMS_OF_AN_EXTREMELY_WEAKENED_BODY_STRUCTURE_SOMETIMES_IF_YOU_ATTACK_THEM_AT_THAT_MOMENT_YOU_WILL_HAVE_GREAT_RESULTS;

	/**
	 * ID: 1800202<br>
	 * Message: $s1のことは聞いたことあるか。まさに真の$s2だ！
	 */
	public static final NpcStringId HAVE_YOU_EVER_HEARD_OF_S1_THEY_SAY_ITS_A_GENUINE_S2;

	/**
	 * ID: 1800203<br>
	 * Message: クラテのキューブの競技の申し込み終了まであと5分です。
	 */
	public static final NpcStringId THERE_ARE_5_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH;

	/**
	 * ID: 1800204<br>
	 * Message: クラテのキューブの競技の申し込み終了まであと3分です。
	 */
	public static final NpcStringId THERE_ARE_3_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH;

	/**
	 * ID: 1800205<br>
	 * Message: クラテのキューブの競技の申し込み終了まであと1分です。
	 */
	public static final NpcStringId THERE_ARE_1_MINUTES_REMAINING_TO_REGISTER_FOR_KRATEIS_CUBE_MATCH;

	/**
	 * ID: 1800207<br>
	 * Message: まもなく競技が始まります。
	 */
	public static final NpcStringId THE_MATCH_WILL_BEGIN_SHORTLY;

	/**
	 * ID: 1800209<br>
	 * Message: ゴオオオオォ．．．
	 */
	public static final NpcStringId OHHOHOH;

	/**
	 * ID: 1800210<br>
	 * Message: 火
	 */
	public static final NpcStringId FIRE;

	/**
	 * ID: 1800211<br>
	 * Message: 水
	 */
	public static final NpcStringId WATER;

	/**
	 * ID: 1800212<br>
	 * Message: 風
	 */
	public static final NpcStringId WIND;

	/**
	 * ID: 1800213<br>
	 * Message: 地
	 */
	public static final NpcStringId EARTH;

	/**
	 * ID: 1800214<br>
	 * Message: $s1だ．．．
	 */
	public static final NpcStringId ITS_S1;

	/**
	 * ID: 1800215<br>
	 * Message: $s1に強い．．．
	 */
	public static final NpcStringId S1_IS_STRONG;

	/**
	 * ID: 1800216<br>
	 * Message: $s1しか知らないのか．．．
	 */
	public static final NpcStringId ITS_ALWAYS_S1;

	/**
	 * ID: 1800217<br>
	 * Message: $s1はだめだ．．．
	 */
	public static final NpcStringId S1_WONT_DO;

	/**
	 * ID: 1800218<br>
	 * Message: 宝物に手を出そうとする者には呪いがかかるだろう！
	 */
	public static final NpcStringId YOU_WILL_BE_CURSED_FOR_SEEKING_THE_TREASURE;

	/**
	 * ID: 1800219<br>
	 * Message: 飛行船が召喚されました。5分後に自動的に出発します。
	 */
	public static final NpcStringId THE_AIRSHIP_HAS_BEEN_SUMMONED_IT_WILL_AUTOMATICALLY_DEPART_IN_5_MINUTES;

	/**
	 * ID: 1800220<br>
	 * Message: 定期便飛行船が到着しました。1分後にアデンに向って出発します。
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_ADEN_CONTINENT_IN_1_MINUTE;

	/**
	 * ID: 1800221<br>
	 * Message: アデンに向う定期飛行船が出発しました。
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_ADEN_CONTINENT_HAS_DEPARTED;

	/**
	 * ID: 1800222<br>
	 * Message: 定期便飛行船が到着しました。1分後にグレシアに向って出発します。
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_HAS_ARRIVED_IT_WILL_DEPART_FOR_THE_GRACIA_CONTINENT_IN_1_MINUTE;

	/**
	 * ID: 1800223<br>
	 * Message: グレシアに向う定期便飛行船が出発しました。
	 */
	public static final NpcStringId THE_REGULARLY_SCHEDULED_AIRSHIP_THAT_FLIES_TO_THE_GRACIA_CONTINENT_HAS_DEPARTED;

	/**
	 * ID: 1800224<br>
	 * Message: 発着場には他の飛行船が召喚されています。また後ほどご利用ください。
	 */
	public static final NpcStringId ANOTHER_AIRSHIP_HAS_BEEN_SUMMONED_TO_THE_WHARF_PLEASE_TRY_AGAIN_LATER;

	/**
	 * ID: 1800225<br>
	 * Message: おっ？何だか空が変だな．．．あっ！何だ、あれは！
	 */
	public static final NpcStringId HUH_THE_SKY_LOOKS_FUNNY_WHATS_THAT;

	/**
	 * ID: 1800226<br>
	 * Message: バリア オーブに負荷がかかりすぎている。この反応はおそらく．．．
	 */
	public static final NpcStringId A_POWERFUL_SUBORDINATE_IS_BEING_HELD_BY_THE_BARRIER_ORB_THIS_REACTION_MEANS;

	/**
	 * ID: 1800227<br>
	 * Message: 気をつけろ！何か来るぞ！
	 */
	public static final NpcStringId BE_CAREFUL_SOMETHINGS_COMING;

	/**
	 * ID: 1800228<br>
	 * Message: まずは血盟を創設するか、血盟に加入しなければなりません。
	 */
	public static final NpcStringId YOU_MUST_FIRST_FOUND_A_CLAN_OR_BELONG_TO_ONE;

	/**
	 * ID: 1800229<br>
	 * Message: エキムスに挑戦中のパーティはありません。$s1秒以内に入らなければ、不滅の心臓部への攻撃は失敗となります。
	 */
	public static final NpcStringId THERE_IS_NO_PARTY_CURRENTLY_CHALLENGING_EKIMUS_N_IF_NO_PARTY_ENTERS_WITHIN_S1_SECONDS_THE_ATTACK_ON_THE_HEART_OF_IMMORTALITY_WILL_FAIL;

	/**
	 * ID: 1800230<br>
	 * Message: 腫瘍体とつながったエキムスはさらに強力な力を得ました。
	 */
	public static final NpcStringId EKIMUS_HAS_GAINED_STRENGTH_FROM_A_TUMOR;

	/**
	 * ID: 1800231<br>
	 * Message: 腫瘍体とのつながりが切れたエキムスが力を失いました。
	 */
	public static final NpcStringId EKIMUS_HAS_BEEN_WEAKENED_BY_LOSING_STRENGTH_FROM_A_TUMOR;

	/**
	 * ID: 1800233<br>
	 * Message: おらーっ！出てこい、このネズミ野郎！てめえらの悪行、この目でしっかと見てやる！
	 */
	public static final NpcStringId CMON_CMON_SHOW_YOUR_FACE_YOU_LITTLE_RATS_LET_ME_SEE_WHAT_THE_DOOMED_WEAKLINGS_ARE_SCHEMING;

	/**
	 * ID: 1800234<br>
	 * Message: なかなかのもんだな、フフフ．．．おっと、いけない。熱くなり過ぎたな。アルジェクンテ、道を開けろ！
	 */
	public static final NpcStringId IMPRESSIVE_HAHAHA_ITS_SO_MUCH_FUN_BUT_I_NEED_TO_CHILL_A_LITTLE_WHILE_ARGEKUNTE_CLEAR_THE_WAY;

	/**
	 * ID: 1800235<br>
	 * Message: がははははっ！腫瘍体が生き返った今、もはやてめえらの相手をする必要はなさそうだな。
	 */
	public static final NpcStringId KYAHAHA_SINCE_THE_TUMOR_HAS_BEEN_RESURRECTED_I_NO_LONGER_NEED_TO_WASTE_MY_TIME_ON_YOU;

	/**
	 * ID: 1800236<br>
	 * Message: くっ！今日はこれぐらいにしといてやるが、これで終わりだと思うなよ！不滅の種は文字通り永遠に不滅だ！
	 */
	public static final NpcStringId KEU_I_WILL_LEAVE_FOR_NOW_BUT_DONT_THINK_THIS_IS_OVER_THE_SEED_OF_INFINITY_CAN_NEVER_DIE;

	/**
	 * ID: 1800237<br>
	 * Message: がははははっ！あいつは外見だけでからっぽだ。俺の許しがなければ死ぬことすらできない。見よ、秘伝の魔法、デスリス ガーディアンを！
	 */
	public static final NpcStringId KAHAHAHA_THAT_GUYS_NOTHING_HE_CANT_EVEN_KILL_WITHOUT_MY_PERMISSION_SEE_HERE_ULTIMATE_FORGOTTEN_MAGIC_DEATHLESS_GUARDIAN;

	/**
	 * ID: 1800238<br>
	 * Message: 命乞いをしてお前の奴隷に成り下がったあの日の屈辱は永遠に忘れない。コヘメネス！てめえの息の根が止まる瞬間をこの目で見届けてやる！
	 */
	public static final NpcStringId I_CURSE_THE_DAY_THAT_I_BECAME_YOUR_SLAVE_IN_ORDER_TO_ESCAPE_DEATH_COHEMENES_I_SWEAR_THAT_I_SHALL_SEE_YOU_DIE_WITH_MY_OWN_EYES;

	/**
	 * ID: 1800239<br>
	 * Message: 俺のかたきが死にそうだぜ！すっげえわくわくするぜ！ああ、残酷、残酷！
	 */
	public static final NpcStringId MY_ENEMY_IS_DYING_AND_MY_BLOOD_IS_BOILING_WHAT_CRUEL_CURSE_IS_THIS;

	/**
	 * ID: 1800240<br>
	 * Message: 苦痛の棺室
	 */
	public static final NpcStringId HALL_OF_SUFFERING;

	/**
	 * ID: 1800241<br>
	 * Message: 侵食の棺室
	 */
	public static final NpcStringId HALL_OF_EROSION;

	/**
	 * ID: 1800242<br>
	 * Message: 不滅の心臓部
	 */
	public static final NpcStringId HEART_OF_IMMORTALITY;

	/**
	 * ID: 1800243<br>
	 * Message: 攻撃
	 */
	public static final NpcStringId ATTACK;

	/**
	 * ID: 1800244<br>
	 * Message: 防御
	 */
	public static final NpcStringId DEFEND;

	/**
	 * ID: 1800245<br>
	 * Message: おめでとうございます！$s1 $s2に成功しました！まもなくインスタント ゾーンが消滅します。
	 */
	public static final NpcStringId CONGRATULATIONS_YOU_HAVE_SUCCEEDED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE;

	/**
	 * ID: 1800246<br>
	 * Message: $s1 $s2に失敗しました．．．まもなくインスタント ゾーンが消滅します。
	 */
	public static final NpcStringId YOU_HAVE_FAILED_AT_S1_S2_THE_INSTANCE_WILL_SHORTLY_EXPIRE;

	/**
	 * ID: 1800247<br>
	 * Message: $s1のパーティは腫瘍体のすきまをすり抜けて、他のところに移動しました。
	 */
	public static final NpcStringId S1S_PARTY_HAS_MOVED_TO_A_DIFFERENT_LOCATION_THROUGH_THE_CRACK_IN_THE_TUMOR;

	/**
	 * ID: 1800248<br>
	 * Message: $s1のパーティは腫瘍体のすきまをすり抜けて、エキムスの部屋に入りました。
	 */
	public static final NpcStringId S1S_PARTY_HAS_ENTERED_THE_CHAMBER_OF_EKIMUS_THROUGH_THE_CRACK_IN_THE_TUMOR;

	/**
	 * ID: 1800249<br>
	 * Message: エキムスが異常行動を感知しました。挑戦中のパーティは強制追放されます。
	 */
	public static final NpcStringId EKIMUS_HAS_SENSED_ABNORMAL_ACTIVITY_NTHE_ADVANCING_PARTY_IS_FORCEFULLY_EXPELLED;

	/**
	 * ID: 1800250<br>
	 * Message: アイテムが不足しています。飛行船の召喚にはエネルギー スターストーン5つが必要です。
	 */
	public static final NpcStringId THERE_ARENT_ENOUGH_ITEMS_IN_ORDER_TO_SUMMON_THE_AIRSHIP_YOU_NEED_5_ENERGY_STAR_STONES;

	/**
	 * ID: 1800251<br>
	 * Message: 最後まで生き残った再生の種を食い尽さんがために、食い意地の張った魂の捕食者が目覚めました．．．
	 */
	public static final NpcStringId THE_SOUL_DEVOURERS_WHO_ARE_GREEDY_TO_EAT_THE_SEEDS_OF_LIFE_THAT_REMAIN_ALIVE_UNTIL_THE_END_HAVE_AWAKENED;

	/**
	 * ID: 1800252<br>
	 * Message: 1匹目の冥界の魔犬が目覚めました．．．
	 */
	public static final NpcStringId THE_FIRST_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED;

	/**
	 * ID: 1800253<br>
	 * Message: 2匹目の冥界の魔犬が目覚めました．．．
	 */
	public static final NpcStringId THE_SECOND_FERAL_HOUND_OF_THE_NETHERWORLD_HAS_AWAKENED;

	/**
	 * ID: 1800254<br>
	 * Message: 付いてきても無駄だ！秘伝の魔法、ブレイド ターンをくらえっ！
	 */
	public static final NpcStringId CLINGING_ON_WONT_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_BLADE_TURN;

	/**
	 * ID: 1800255<br>
	 * Message: そんなところでいくらあがいても通じないぜ！秘伝の魔法、フォース シールドをくらえっ！
	 */
	public static final NpcStringId EVEN_SPECIAL_SAUCE_CANT_HELP_YOU_ULTIMATE_FORGOTTEN_MAGIC_FORCE_SHIELD;

	/**
	 * ID: 1800256<br>
	 * Message: この虫けらどもめ！束になってかかってきたところで、不滅の力は強くなるばかりだ！
	 */
	public static final NpcStringId YOU_LITTLE_DOOMED_MAGGOTS_EVEN_IF_YOU_KEEP_SWARMING_THE_POWER_OF_IMMORTALITY_WILL_ONLY_GROW_STRONGER;

	/**
	 * ID: 1800257<br>
	 * Message: 飛行船の召喚許可書が入力できました。貴血盟は今後、飛行船の召喚ができるようになります。
	 */
	public static final NpcStringId THE_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_AWARDED_YOUR_CLAN_CAN_NOW_SUMMON_AN_AIRSHIP;

	/**
	 * ID: 1800258<br>
	 * Message: グレシア宝箱が現れました。
	 */
	public static final NpcStringId THE_GRACIA_TREASURE_BOX_HAS_APPEARED;

	/**
	 * ID: 1800259<br>
	 * Message: グレシア宝箱がまもなく消滅します。
	 */
	public static final NpcStringId THE_GRACIA_TREASURE_BOX_WILL_SOON_DISAPPEAR;

	/**
	 * ID: 1800260<br>
	 * Message: 腫瘍体が呪いにかかって$s1のダメージを被りました。
	 */
	public static final NpcStringId YOU_HAVE_BEEN_CURSED_BY_THE_TUMOR_AND_HAVE_INCURRED_S1_DAMAGE;

	/**
	 * ID: 1800261<br>
	 * Message: てめえの挑戦、受けて立つ！$s1！不滅の懐に抱かれて逝きな！
	 */
	public static final NpcStringId I_SHALL_ACCEPT_YOUR_CHALLENGE_S1_COME_AND_DIE_IN_THE_ARMS_OF_IMMORTALITY;

	/**
	 * ID: 1800262<br>
	 * Message: まもなく$s1 $s2に参加します。万全の準備をお願いします。
	 */
	public static final NpcStringId YOU_WILL_PARTICIPATE_IN_S1_S2_SHORTLY_BE_PREPARED_FOR_ANYTHING;

	/**
	 * ID: 1800263<br>
	 * Message: エキムスのアンデッドがやってくる足音が聞こえます。$s1 $s2。今始まりました。
	 */
	public static final NpcStringId YOU_CAN_HEAR_THE_UNDEAD_OF_EKIMUS_RUSHING_TOWARD_YOU_S1_S2_IT_HAS_NOW_BEGUN;

	/**
	 * ID: 1800264<br>
	 * Message: 腫瘍体に死のエネルギーが満ちつつあることが感じられます。
	 */
	public static final NpcStringId YOU_CAN_FEEL_THE_SURGING_ENERGY_OF_DEATH_FROM_THE_TUMOR;

	/**
	 * ID: 1800265<br>
	 * Message: 腫瘍体のまわりに不吉な気が満ちあふれています。
	 */
	public static final NpcStringId THE_AREA_NEAR_THE_TUMOR_IS_FULL_OF_OMINOUS_ENERGY;

	/**
	 * ID: 1800266<br>
	 * Message: 我々をまこうとは、どこまでも愚かしいやつめ！
	 */
	public static final NpcStringId YOU_TRIED_TO_DROP_US_HOW_STUPID;

	/**
	 * ID: 1800267<br>
	 * Message: 我々は血を分けた兄弟だ。弟を置いて先に逝くわけにはいかない。
	 */
	public static final NpcStringId WE_ARE_BLOOD_BRETHREN_I_CANT_FALL_SO_EASILY_HERE_AND_LEAVE_MY_BROTHER_BEHIND;

	/**
	 * ID: 1800268<br>
	 * Message: 子供の頃から兄に憧れていた。その兄を置いて先に逝くわけにはいかない。
	 */
	public static final NpcStringId YOU_WERE_ALWAYS_WHAT_I_ASPIRED_TO_BE_DO_YOU_THINK_I_WOULD_FALL_SO_EASILY_HERE_WHEN_I_HAVE_A_BROTHER_LIKE_THAT;

	/**
	 * ID: 1800269<br>
	 * Message: 腫瘍体とのつながりが切れたエキムスの魔犬に対する統制力が失われました。
	 */
	public static final NpcStringId WITH_ALL_CONNECTIONS_TO_THE_TUMOR_SEVERED_EKIMUS_HAS_LOST_ITS_POWER_TO_CONTROL_THE_FERAL_HOUND;

	/**
	 * ID: 1800270<br>
	 * Message: 腫瘍体と再びつながって力を取り戻したエキムスが魔犬に対する統制力を取り戻しました。
	 */
	public static final NpcStringId WITH_THE_CONNECTION_TO_THE_TUMOR_RESTORED_EKIMUS_HAS_REGAINED_CONTROL_OVER_THE_FERAL_HOUND;

	/**
	 * ID: 1800271<br>
	 * Message: うううううんっ！
	 */
	public static final NpcStringId WOOOONG;

	/**
	 * ID: 1800272<br>
	 * Message: ううんっ うううんっ うううう
	 */
	public static final NpcStringId WOONG_WOONG_WOO;

	/**
	 * ID: 1800273<br>
	 * Message: 敵軍来襲！総員戦闘体制につけ！くはっ！
	 */
	public static final NpcStringId THE_ENEMIES_HAVE_ATTACKED_EVERYONE_COME_OUT_AND_FIGHT_URGH;

	/**
	 * ID: 1800274<br>
	 * Message: $s1内の腫瘍体が破壊されました！卑怯なコヘメネスを引き出すためにはすべての腫瘍体を破壊しなければなりません。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NIN_ORDER_TO_DRAW_OUT_THE_COWARDLY_COHEMENES_YOU_MUST_DESTROY_ALL_THE_TUMORS;

	/**
	 * ID: 1800275<br>
	 * Message: $s1内の腫瘍体が完全に復活しました。力を取り戻したコヘメネスは種の奥深くに逃げていきます。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NTHE_RESTRENGTHENED_COHEMENES_HAS_FLED_DEEPER_INSIDE_THE_SEED;

	/**
	 * ID: 1800276<br>
	 * Message: 飛行船召喚許可書をすでに獲得しています。
	 */
	public static final NpcStringId THE_AWARDED_AIRSHIP_SUMMON_LICENSE_HAS_BEEN_RECEIVED;

	/**
	 * ID: 1800277<br>
	 * Message: 飛行船召喚許可書がありません。これはレコンから発給してもらえます。
	 */
	public static final NpcStringId YOU_DO_NOT_CURRENTLY_HAVE_AN_AIRSHIP_SUMMON_LICENSE_YOU_CAN_EARN_YOUR_AIRSHIP_SUMMON_LICENSE_THROUGH_ENGINEER_LEKON;

	/**
	 * ID: 1800278<br>
	 * Message: 飛行船召喚許可書はすでに入力されています。
	 */
	public static final NpcStringId THE_AIRSHIP_SUMMON_LICENSE_HAS_ALREADY_BEEN_AWARDED;

	/**
	 * ID: 1800279<br>
	 * Message: 何でもいいからアイテムくれよ～
	 */
	public static final NpcStringId IF_YOU_HAVE_ITEMS_PLEASE_GIVE_THEM_TO_ME;

	/**
	 * ID: 1800280<br>
	 * Message: 腹が減ってんだよ！
	 */
	public static final NpcStringId MY_STOMACH_IS_EMPTY;

	/**
	 * ID: 1800281<br>
	 * Message: 腹減った！メシ食わせろ！
	 */
	public static final NpcStringId IM_HUNGRY_IM_HUNGRY;

	/**
	 * ID: 1800282<br>
	 * Message: 何だか足りないなぁ。
	 */
	public static final NpcStringId IM_STILL_NOT_FULL;

	/**
	 * ID: 1800283<br>
	 * Message: まだ腹減ってるぞ！
	 */
	public static final NpcStringId IM_STILL_HUNGRY;

	/**
	 * ID: 1800284<br>
	 * Message: ちょっとは腹が膨らんだけどなぁ．．．
	 */
	public static final NpcStringId I_FEEL_A_LITTLE_WOOZY;

	/**
	 * ID: 1800285<br>
	 * Message: 食うものくれよ！
	 */
	public static final NpcStringId GIVE_ME_SOMETHING_TO_EAT;

	/**
	 * ID: 1800286<br>
	 * Message: さてと、本格的に食うとするか。
	 */
	public static final NpcStringId NOW_ITS_TIME_TO_EAT;

	/**
	 * ID: 1800287<br>
	 * Message: デザートも忘れるなよな。
	 */
	public static final NpcStringId I_ALSO_NEED_A_DESSERT;

	/**
	 * ID: 1800289<br>
	 * Message: もうおなかいっぱい。
	 */
	public static final NpcStringId IM_FULL_NOW_I_DONT_WANT_TO_EAT_ANYMORE;

	/**
	 * ID: 1800290<br>
	 * Message: 食うもんねえから、力が出ねえよ！
	 */
	public static final NpcStringId I_HAVENT_EATEN_ANYTHING_IM_SO_WEAK;

	/**
	 * ID: 1800291<br>
	 * Message: むしゃむしゃ
	 */
	public static final NpcStringId YUM_YUM_YUM_YUM;

	/**
	 * ID: 1800292<br>
	 * Message: シールドの聖なる印章に触れた腫瘍体の外皮が解けて$s1のダメージを被りました。
	 */
	public static final NpcStringId YOUVE_SUSTAINED_S1_DAMAGE_AS_TUMORS_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD;

	/**
	 * ID: 1800293<br>
	 * Message: シールドの聖なる印章に触れた魂の管の外皮が解けて$s1のダメージを被りました。
	 */
	public static final NpcStringId YOUVE_SUSTAINED_S1_DAMAGE_AS_SOUL_COFFINS_SHELL_STARTED_MELTING_AFTER_TOUCHING_THE_SACRED_SEAL_ON_THE_SHIELD;

	/**
	 * ID: 1800295<br>
	 * Message: オベリスクが崩壊した。もはや敵がのさばるのを見てられない！
	 */
	public static final NpcStringId OBELISK_HAS_COLLAPSED_DONT_LET_THE_ENEMIES_JUMP_AROUND_WILDLY_ANYMORE;

	/**
	 * ID: 1800296<br>
	 * Message: 敵が要塞を攻め落とそうとしている。力を尽くして防げ！
	 */
	public static final NpcStringId ENEMIES_ARE_TRYING_TO_DESTROY_THE_FORTRESS_EVERYONE_DEFEND_THE_FORTRESS;

	/**
	 * ID: 1800297<br>
	 * Message: 出でよ、戦士たちよ！破滅の種を守れ。
	 */
	public static final NpcStringId COME_OUT_WARRIORS_PROTECT_SEED_OF_DESTRUCTION;

	/**
	 * ID: 1800298<br>
	 * Message: 再生の種がエキムスのアンデッドから攻撃されています。再生の種が一つでも破壊されたら、侵食の棺室の防御が失敗します。
	 */
	public static final NpcStringId THE_UNDEAD_OF_EKIMUS_IS_ATTACKING_SEED_OF_LIFE_DEFENDING_HALL_OF_EROSION_WILL_FAIL_EVEN_IF_ONE_SEED_OF_LIFE_IS_DESTROYED;

	/**
	 * ID: 1800299<br>
	 * Message: $s1内の腫瘍体が破壊されました！窮地に追いやられたコヘメネスが隣の地域に現れました。
	 */
	public static final NpcStringId ALL_THE_TUMORS_INSIDE_S1_HAVE_BEEN_DESTROYED_DRIVEN_INTO_A_CORNER_COHEMENES_APPEARS_CLOSE_BY;

	/**
	 * ID: 1800300<br>
	 * Message: $s1内の腫瘍体が破壊されました！隣の再生の種を攻撃していたアンデッドが力を失って逃げていきます。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_NEARBY_UNDEAD_THAT_WERE_ATTACKING_SEED_OF_LIFE_START_LOSING_THEIR_ENERGY_AND_RUN_AWAY;

	/**
	 * ID: 1800301<br>
	 * Message: $s1内の腫瘍体が完全に復活しました。力を取り戻したアンデッドが隣の再生の種に押し寄せます。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NRECOVERED_NEARBY_UNDEAD_ARE_SWARMING_TOWARD_SEED_OF_LIFE;

	/**
	 * ID: 1800302<br>
	 * Message: エキムスに力を供給していた$s1内の腫瘍体が破壊されました！
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_THAT_HAS_PROVIDED_ENERGY_N_TO_EKIMUS_IS_DESTROYED;

	/**
	 * ID: 1800303<br>
	 * Message: $s1内の腫瘍体が完全に復活して、エキムスに力を供給し始めました。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_COMPLETELY_RESURRECTED_N_AND_STARTED_TO_ENERGIZE_EKIMUS_AGAIN;

	/**
	 * ID: 1800304<br>
	 * Message: $s1内の腫瘍体が破壊されました！エキムスが餌をおびき寄せるスピードが遅くなりました。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_BEEN_DESTROYED_NTHE_SPEED_THAT_EKIMUS_CALLS_OUT_HIS_PREY_HAS_SLOWED_DOWN;

	/**
	 * ID: 1800305<br>
	 * Message: $s1内の腫瘍体が完全に復活しました。力を取り戻したエキムスがさらに急いで餌を探します。
	 */
	public static final NpcStringId THE_TUMOR_INSIDE_S1_HAS_COMPLETELY_REVIVED_NEKIMUS_STARTED_TO_REGAIN_HIS_ENERGY_AND_IS_DESPERATELY_LOOKING_FOR_HIS_PREY;

	/**
	 * ID: 1800306<br>
	 * Message: も、もっと魂をくれ！
	 */
	public static final NpcStringId BRING_MORE_MORE_SOULS;

	/**
	 * ID: 1800307<br>
	 * Message: すばやく腫瘍体を攻撃しなければ、侵食の棺室の攻撃に失敗します。
	 */
	public static final NpcStringId THE_HALL_OF_EROSION_ATTACK_WILL_FAIL_UNLESS_YOU_MAKE_A_QUICK_ATTACK_AGAINST_THE_TUMOR;

	/**
	 * ID: 1800308<br>
	 * Message: 腫瘍体が脅かされないので、コヘメネスは種の奥深くに逃げていきます。
	 */
	public static final NpcStringId AS_THE_TUMOR_WAS_NOT_THREATENED_COHEMENES_COMPLETELY_RAN_AWAY_TO_DEEP_INSIDE_THE_SEED;

	/**
	 * ID: 1800309<br>
	 * Message: 目標が妨害されたり、制約を受けたりする。
	 */
	public static final NpcStringId YOUR_GOAL_WILL_BE_OBSTRUCTED_OR_BE_UNDER_A_RESTRAINT;

	/**
	 * ID: 1800310<br>
	 * Message: 目標に向って進む過程で予想外の問題が生じるかもしれない。
	 */
	public static final NpcStringId YOU_MAY_FACE_AN_UNFORESEEN_PROBLEM_ON_YOUR_COURSE_TOWARD_THE_GOAL;

	/**
	 * ID: 1800311<br>
	 * Message: 状況が不利になって、不安で焦燥感に狩られることになる。
	 */
	public static final NpcStringId YOU_MAY_FEEL_NERVOUS_AND_ANXIOUS_BECAUSE_OF_UNFAVORABLE_SITUATIONS;

	/**
	 * ID: 1800312<br>
	 * Message: 立場が危うくなれば、判断力が鈍って理にかなわぬことをする。気をつけよ。
	 */
	public static final NpcStringId BE_WARNED_WHEN_THE_SITUATION_IS_DIFFICULT_BECAUSE_YOU_MAY_LOSE_YOUR_JUDGMENT_AND_MAKE_AN_IRRATIONAL_MISTAKE;

	/**
	 * ID: 1800313<br>
	 * Message: 信頼できる人に会えて、チャンスをつかむ。
	 */
	public static final NpcStringId YOU_MAY_MEET_A_TRUSTWORTHY_PERSON_OR_A_GOOD_OPPORTUNITY;

	/**
	 * ID: 1800314<br>
	 * Message: パッとしなかった暮らし向きが上向く。
	 */
	public static final NpcStringId YOUR_DOWNWARD_LIFE_STARTS_TAKING_AN_UPTURN;

	/**
	 * ID: 1800315<br>
	 * Message: 人気が爆発して周囲の視線を一身に集める。
	 */
	public static final NpcStringId YOU_WILL_ATTRACT_ATTENTION_FROM_PEOPLE_WITH_YOUR_POPULARITY;

	/**
	 * ID: 1800316<br>
	 * Message: 仕掛けておいた餌に魚が食いつく。
	 */
	public static final NpcStringId YOUR_STAR_OF_FORTUNE_SAYS_THERELL_BE_FISH_SNAPPING_AT_YOUR_BAIT;

	/**
	 * ID: 1800317<br>
	 * Message: 独断で事を進めれば、あつれきが生じる。
	 */
	public static final NpcStringId THERE_MAY_BE_A_CONFLICT_BORN_OF_YOUR_DOGMATIC_PROCEDURES;

	/**
	 * ID: 1800318<br>
	 * Message: 知恵と創造力で目標を達成する。
	 */
	public static final NpcStringId YOUR_WISDOM_AND_CREATIVITY_MAY_LEAD_YOU_TO_SUCCESS_WITH_YOUR_GOAL;

	/**
	 * ID: 1800319<br>
	 * Message: わき目をふらずに仕事に専心すれば、目標を達成できる。
	 */
	public static final NpcStringId YOU_MAY_ACCOMPLISH_YOUR_GOALS_IF_YOU_DILIGENTLY_PURSUE_THEM_WITHOUT_GIVING_UP;

	/**
	 * ID: 1800320<br>
	 * Message: 猿知恵を働かせず正面から攻めれば力を得られる。
	 */
	public static final NpcStringId YOU_MAY_GET_HELP_IF_YOU_GO_THROUGH_THE_FRONT_DOOR_WITHOUT_SEEKING_TRICKS_OR_MANEUVERS;

	/**
	 * ID: 1800321<br>
	 * Message: 目標を定めて勇気を持って行動すれば、いい結果が得られる。
	 */
	public static final NpcStringId A_GOOD_RESULT_IS_ON_THE_WAY_IF_YOU_SET_A_GOAL_AND_BRAVELY_PROCEED_TOWARD_IT;

	/**
	 * ID: 1800322<br>
	 * Message: いかなる困難があろうとも、万事が上手くいく。
	 */
	public static final NpcStringId EVERYTHING_WILL_BE_SMOOTHLY_MANAGED_NO_MATTER_HOW_DIFFICULT;

	/**
	 * ID: 1800323<br>
	 * Message: 困難があるやもしれぬが、慎重に検討して志を強く持て。
	 */
	public static final NpcStringId BE_FIRM_AND_CAREFULLY_SCRUTINIZE_CIRCUMSTANCES_EVEN_WHEN_THINGS_ARE_DIFFICULT;

	/**
	 * ID: 1800324<br>
	 * Message: 放置していた問題がないか、常に考えよ。
	 */
	public static final NpcStringId ALWAYS_THINK_OVER_TO_FIND_NEGLECTED_PROBLEMS_YOU_HAVENT_TAKEN_CARE_OF_YET;

	/**
	 * ID: 1800325<br>
	 * Message: 寒い懐に金銀財宝が転がり込んでくる。
	 */
	public static final NpcStringId FINANCIAL_FORTUNE_WILL_GREET_YOUR_POOR_LIFE;

	/**
	 * ID: 1800326<br>
	 * Message: 不幸の星の下に生まれたあなた。運気が上昇して富と名誉を手にする。
	 */
	public static final NpcStringId YOU_MAY_ACQUIRE_WEALTH_AND_FAME_AFTER_UNLUCKY_CIRCUMSTANCES;

	/**
	 * ID: 1800327<br>
	 * Message: 困難があっても予想外の助けを得て光が差すようになる。
	 */
	public static final NpcStringId THE_DIFFICULT_SITUATIONS_WILL_TURN_TO_HOPE_WITH_UNFORESEEN_HELP;

	/**
	 * ID: 1800328<br>
	 * Message: 大きな仕事が成功する。
	 */
	public static final NpcStringId A_GREAT_TASK_WILL_RESULT_IN_SUCCESS;

	/**
	 * ID: 1800329<br>
	 * Message: 胸に秘めていた寂しさと葛藤を吹き飛ばしてくれる貴人に会う。
	 */
	public static final NpcStringId YOU_MAY_ENCOUNTER_A_PRECIOUS_PERSON_WHO_WILL_LIFT_YOUR_LONELINESS_AND_DISCORD;

	/**
	 * ID: 1800330<br>
	 * Message: 周囲の人々の激励に力を得る。
	 */
	public static final NpcStringId PEOPLE_AROUND_YOU_WILL_ENCOURAGE_YOUR_EVERY_TASK_IN_THE_FUTURE;

	/**
	 * ID: 1800331<br>
	 * Message: 万事が円滑に進む。
	 */
	public static final NpcStringId EVERYTHING_WILL_BE_SMOOTHLY_MANAGED;

	/**
	 * ID: 1800332<br>
	 * Message: 円満な人間関係を維持すれば、あなたの価値を高めてくれる人が現れる。
	 */
	public static final NpcStringId YOU_WILL_MEET_A_PERSON_WHO_CAN_CHERISH_YOUR_VALUES_IF_YOU_MAINTAIN_GOOD_TIES_WITH_PEOPLE;

	/**
	 * ID: 1800333<br>
	 * Message: 助けの手を差し伸べる誰かが現れる。常に協力する心を忘れずに。
	 */
	public static final NpcStringId MAINTAIN_COOPERATIVE_ATTITUDE_SINCE_YOU_WILL_MEET_SOMEONE_TO_BE_OF_HELP;

	/**
	 * ID: 1800334<br>
	 * Message: 事がうまく運んだとて浮き立たず節制に励め。
	 */
	public static final NpcStringId KEEP_YOUR_MODERATION_AND_EGO_IN_CHECK_EVEN_IN_SUCCESSFUL_PHASES_OF_YOUR_LIFE;

	/**
	 * ID: 1800335<br>
	 * Message: 仕事、生活、人間関係すべてにおいて横着をせず正面からぶつかればいいことが起こる。
	 */
	public static final NpcStringId WHEN_IT_COMES_TO_WORK_LIFESTYLE_AND_RELATIONSHIPS_YOULL_BE_BETTER_OFF_TO_GO_BY_THE_TEXT_RATHER_THAN_TRICKS;

	/**
	 * ID: 1800336<br>
	 * Message: 条件が整って仕事に弾力が付く。
	 */
	public static final NpcStringId YOUR_TASK_WILL_RECEIVE_SUBSTANTIAL_SUPPORT_SINCE_THE_SURROUNDINGS_WILL_FULLY_DEVELOP;

	/**
	 * ID: 1800337<br>
	 * Message: 精神的にも物質的にも助けを得て成功する。
	 */
	public static final NpcStringId YOUR_STAR_OF_FORTUNE_INDICATE_A_SUCCESS_WITH_MENTAL_AND_MATERIAL_ASSISTANCE;

	/**
	 * ID: 1800338<br>
	 * Message: 創造力、才能、気のきいたふるまいで皆から好感を持たれる。
	 */
	public static final NpcStringId YOU_WILL_ENJOY_POPULARITY_WITH_YOUR_CREATIVE_TALENTS_AND_CLEVER_ACTS;

	/**
	 * ID: 1800339<br>
	 * Message: 皆があなたを助けようとする。
	 */
	public static final NpcStringId PEOPLE_WILL_LINE_UP_TO_BE_OF_ASSISTANCE_TO_YOU;

	/**
	 * ID: 1800340<br>
	 * Message: 旅路を共にする友が現れる。
	 */
	public static final NpcStringId YOU_MAY_MEET_SOMEONE_TO_SHARE_YOUR_JOURNEY;

	/**
	 * ID: 1800341<br>
	 * Message: 様々な人に出会って良縁にめぐり合う。
	 */
	public static final NpcStringId YOU_MAY_ACHIEVE_CONNECTIONS_IN_MANY_FIELDS;

	/**
	 * ID: 1800342<br>
	 * Message: 学び、追い求める姿勢が肝心。万事において真摯な態度で臨め。
	 */
	public static final NpcStringId AN_ATTITUDE_THAT_CONTINUALLY_STUDIES_AND_EXPLORES_IS_NEEDED_AND_ALWAYS_BE_SINCERE;

	/**
	 * ID: 1800343<br>
	 * Message: 春の暖かな気に乗ってやってきた蝶が花にとまる。
	 */
	public static final NpcStringId ITS_AN_IMAGE_OF_A_BUTTERFLY_ON_A_FLOWER_IN_WARM_SPRING_AIR;

	/**
	 * ID: 1800344<br>
	 * Message: 仲良く安定した生活に、志が円満成就する。
	 */
	public static final NpcStringId YOUR_GOALS_WILL_MOVE_SMOOTHLY_WITH_PEACE_AND_HAPPINESS_IN_YOUR_LIFE;

	/**
	 * ID: 1800345<br>
	 * Message: 周囲の人々に暖かく接すれば愛情が芽生える。
	 */
	public static final NpcStringId LOVE_MAY_SPROUT_ITS_LEAVES_WHEN_YOU_TREAT_THOSE_AROUND_YOU_WITH_CARE;

	/**
	 * ID: 1800346<br>
	 * Message: 与えられた仕事を着実に進めれば、信頼を得てさらに高いステージに進める。
	 */
	public static final NpcStringId YOU_MAY_CLIMB_INTO_A_HIGHER_POSITION_WITH_OTHERS_TRUST_IF_YOU_FAITHFULLY_CARRY_OUT_YOUR_DUTIES;

	/**
	 * ID: 1800347<br>
	 * Message: 運気上昇。しかし欲を張れば万事水泡に帰す。
	 */
	public static final NpcStringId EVERYTHING_CAN_FALL_APART_IF_YOU_GREEDILY_AIM_BY_PURE_LUCK;

	/**
	 * ID: 1800348<br>
	 * Message: 人との付き合いをこまめに。
	 */
	public static final NpcStringId DO_NOT_UNDERESTIMATE_THE_IMPORTANCE_OF_MEETING_PEOPLE;

	/**
	 * ID: 1800349<br>
	 * Message: 水を得た魚。
	 */
	public static final NpcStringId AN_ARROW_WILL_COALESCE_INTO_THE_BOW;

	/**
	 * ID: 1800350<br>
	 * Message: 枯れかけの木に実がなる。
	 */
	public static final NpcStringId A_BONY_LIMB_OF_A_TREE_MAY_BEAR_ITS_FRUIT;

	/**
	 * ID: 1800351<br>
	 * Message: 努力して得た成果で報賞を得る。
	 */
	public static final NpcStringId YOU_WILL_BE_REWARDED_FOR_YOUR_EFFORTS_AND_ACCOMPLISHMENTS;

	/**
	 * ID: 1800352<br>
	 * Message: 万事、信念を持って推し進めれば大きな成果が得られる。
	 */
	public static final NpcStringId NO_MATTER_WHERE_IT_LIES_YOUR_FAITHFUL_DRIVE_LEADS_YOU_TO_SUCCESS;

	/**
	 * ID: 1800353<br>
	 * Message: あなたの魅力はどんなことでも一所懸命なところ。
	 */
	public static final NpcStringId PEOPLE_WILL_BE_ATTRACTED_TO_YOUR_LOYALTIES;

	/**
	 * ID: 1800354<br>
	 * Message: 人の言うことに振り回されるべからず。自分を信じよ。
	 */
	public static final NpcStringId YOU_MAY_TRUST_YOURSELF_RATHER_THAN_OTHERS_TALKS;

	/**
	 * ID: 1800355<br>
	 * Message: ものの見方を変えて創造的な考え方を持て。
	 */
	public static final NpcStringId CREATIVE_THINKING_AWAY_FROM_THE_OLD_VIEWPOINT_MAY_HELP_YOU;

	/**
	 * ID: 1800356<br>
	 * Message: あせらず余裕を持って結果を待てばいい結果が得られる。
	 */
	public static final NpcStringId PATIENCE_WITHOUT_BEING_IMPETUOUS_OF_THE_RESULTS_WILL_ONLY_BEAR_A_POSITIVE_OUTCOME;

	/**
	 * ID: 1800357<br>
	 * Message: 死人が生き返る。
	 */
	public static final NpcStringId THE_DEAD_WILL_COME_ALIVE;

	/**
	 * ID: 1800358<br>
	 * Message: 驚愕の出来事があなたを待ち受ける。
	 */
	public static final NpcStringId THERE_WILL_BE_A_SHOCKING_INCIDENT;

	/**
	 * ID: 1800359<br>
	 * Message: たなぼたで大成功を収める。
	 */
	public static final NpcStringId YOU_WILL_ENJOY_A_HUGE_SUCCESS_AFTER_UNFORESEEN_LUCK_COMES_BEFORE_YOU;

	/**
	 * ID: 1800360<br>
	 * Message: あきらめかけていたことに奇跡が起きる。最後まであきらめるな。
	 */
	public static final NpcStringId DO_NOT_GIVE_UP_SINCE_THERE_MAY_BE_A_MIRACULOUS_RESCUE_FROM_THE_COURSE_OF_DESPAIR;

	/**
	 * ID: 1800361<br>
	 * Message: 目標達成に最善を尽くす姿勢が必要だ。
	 */
	public static final NpcStringId AN_ATTITUDE_TO_TRY_ONES_BEST_TO_PURSUE_THE_GOAL_IS_NEEDED;

	/**
	 * ID: 1800362<br>
	 * Message: 偶然いい人に出会って、生活の活力を得る。
	 */
	public static final NpcStringId YOU_MAY_GET_A_SHOT_IN_THE_ARM_IN_YOUR_LIFE_AFTER_MEETING_A_GOOD_PERSON;

	/**
	 * ID: 1800363<br>
	 * Message: 暮らしに大きな助けを得る。
	 */
	public static final NpcStringId YOU_MAY_GET_A_BIG_HELP_IN_THE_COURSE_OF_YOUR_LIFE;

	/**
	 * ID: 1800364<br>
	 * Message: 発展へとつながるチャンスをつかむ。
	 */
	public static final NpcStringId A_RARE_OPPORTUNITY_WILL_COME_TO_YOU_SO_YOU_MAY_PROSPER;

	/**
	 * ID: 1800365<br>
	 * Message: 腹をすかせた鷹が獲物を獲る。
	 */
	public static final NpcStringId A_HUNGRY_FALCON_WILL_HAVE_MEAT;

	/**
	 * ID: 1800366<br>
	 * Message: 寒い懐に財宝と食物が転がり込んでくる。
	 */
	public static final NpcStringId A_HOUSEHOLD_IN_NEED_WILL_ACQUIRE_A_FORTUNE_AND_MEAT;

	/**
	 * ID: 1800367<br>
	 * Message: 他人から物質的、精神的な助けを得て苦境を脱する。
	 */
	public static final NpcStringId A_HARD_SITUATION_WILL_COME_TO_ITS_END_WITH_MATERIALISTIC_AND_MENTAL_HELP_FROM_OTHERS;

	/**
	 * ID: 1800368<br>
	 * Message: 夢をあきらめず目標をはっきりとさせれば、愛情と助けの手が差し伸べられる。
	 */
	public static final NpcStringId IF_YOU_SET_A_FIRM_GOAL_WITHOUT_SURRENDER_THERE_WILL_BE_A_PERSON_WHO_CAN_OFFER_HELP_AND_CARE;

	/**
	 * ID: 1800369<br>
	 * Message: 真摯で正直な態度を保てば、周りの人々から信頼を勝ち取る。
	 */
	public static final NpcStringId YOULL_GAIN_OTHERS_TRUST_WHEN_YOU_MAINTAIN_A_SINCERE_AND_HONEST_ATTITUDE;

	/**
	 * ID: 1800370<br>
	 * Message: 誰にも頼らずに暮らせ。
	 */
	public static final NpcStringId BE_INDEPENDENT_AT_ALL_TIMES;

	/**
	 * ID: 1800371<br>
	 * Message: 車の車輪がない。
	 */
	public static final NpcStringId ITS_A_WAGON_WITH_NO_WHEELS;

	/**
	 * ID: 1800372<br>
	 * Message: 志があっても、現実がその実現を妨げる。
	 */
	public static final NpcStringId YOUVE_SET_A_GOAL_BUT_THERE_MAY_BE_OBSTACLES_IN_REALITY;

	/**
	 * ID: 1800373<br>
	 * Message: 目標に向って邁進するが、思ったほどの成果は得られない。
	 */
	public static final NpcStringId YOURE_RUNNING_TOWARD_THE_GOAL_BUT_THERE_WONT_BE_AS_MANY_OUTCOMES_AS_YOU_THOUGHT;

	/**
	 * ID: 1800374<br>
	 * Message: 邪魔が多くて悩まされる。
	 */
	public static final NpcStringId THERE_ARE_MANY_THINGS_TO_CONSIDER_AFTER_ENCOUNTERING_HINDRANCES;

	/**
	 * ID: 1800375<br>
	 * Message: 物事の順序を考えずに強行すれば失敗する。
	 */
	public static final NpcStringId A_RECKLESS_MOVE_MAY_BRING_A_FAILURE;

	/**
	 * ID: 1800376<br>
	 * Message: 万事、慎重にあたれ。さもなくば周りの人々の信頼を失う。
	 */
	public static final NpcStringId YOU_MAY_LOSE_PEOPLES_TRUST_IF_YOU_LACK_PRUDENCE_AT_ALL_TIMES;

	/**
	 * ID: 1800377<br>
	 * Message: 自らを省みつつ好機を待て。
	 */
	public static final NpcStringId YOU_MAY_NEED_TO_REFLECT_ON_YOURSELF_WITH_DELIBERATION_AND_WAIT_FOR_AN_OPPORTUNITY;

	/**
	 * ID: 1800378<br>
	 * Message: 貧士、禄を得る。たなぼたの予感。
	 */
	public static final NpcStringId A_POOR_SCHOLAR_RECEIVES_A_STIPEND;

	/**
	 * ID: 1800379<br>
	 * Message: 試練にパスして富と名誉を得る。
	 */
	public static final NpcStringId A_SCHOLAR_GETS_A_PASS_TOWARD_FAME_AND_FORTUNE;

	/**
	 * ID: 1800380<br>
	 * Message: 抱負と夢が実現する。
	 */
	public static final NpcStringId YOUR_AMBITION_AND_DREAM_WILL_COME_TRUE;

	/**
	 * ID: 1800381<br>
	 * Message: 複雑な問題は少しずつ解決する。
	 */
	public static final NpcStringId COMPLICATED_PROBLEMS_AROUND_YOU_MAY_START_BEING_SOLVED_ONE_AFTER_ANOTHER;

	/**
	 * ID: 1800382<br>
	 * Message: 過ぎ去ったことで悩まず、目標を一つ立てて実現に努力すればいい結果が得られる。
	 */
	public static final NpcStringId YOU_WILL_HAVE_A_GOOD_RESULT_IF_YOU_DILIGENTLY_PURSUE_ONE_GOAL_WITHOUT_BEING_DRAGGED_FROM_YOUR_PAST;

	/**
	 * ID: 1800383<br>
	 * Message: 因習は打破すべし。
	 */
	public static final NpcStringId YOU_MAY_NEED_TO_RID_YOURSELF_OF_OLD_AND_WORN_HABITS;

	/**
	 * ID: 1800384<br>
	 * Message: 任せられた仕事は責任感をもってやりぬけ。ただし、周囲の助けを積極的に求めよ。
	 */
	public static final NpcStringId BE_RESPONSIBLE_WITH_YOUR_TASKS_BUT_DO_NOT_HESITATE_TO_ASK_FOR_COLLEAGUES_HELP;

	/**
	 * ID: 1800385<br>
	 * Message: 鳶が鷹を産む。
	 */
	public static final NpcStringId FISH_TRANSFORMS_INTO_A_DRAGON;

	/**
	 * ID: 1800386<br>
	 * Message: 望みが叶って名誉と財を得る。
	 */
	public static final NpcStringId YOUR_DREAM_MAY_COME_TRUE_AND_FAME_AND_FORTUNE_WILL_COME_TO_YOU;

	/**
	 * ID: 1800387<br>
	 * Message: 計画が思い通りに進む。
	 */
	public static final NpcStringId WHAT_YOUVE_PLANED_WILL_BE_ACCOMPLISHED;

	/**
	 * ID: 1800388<br>
	 * Message: 思わぬところから金銭やチャンスが転がり込む。
	 */
	public static final NpcStringId YOU_MAY_ACQUIRE_MONEY_OR_A_NEW_OPPORTUNITY_FROM_A_PLACE_YOU_WOULDNT_HAVE_THOUGHT_OF;

	/**
	 * ID: 1800389<br>
	 * Message: 提案は多いが、慎重に考えよ。
	 */
	public static final NpcStringId THERE_WILL_BE_MANY_OFFERS_TO_YOU_YOU_MAY_THINK_THEM_OVER_CAREFULLY;

	/**
	 * ID: 1800390<br>
	 * Message: 他人事に首をつっこむな。
	 */
	public static final NpcStringId IT_MAY_BE_A_GOOD_IDEA_NOT_TO_BECOME_INVOLVED_IN_OTHERS_BUSINESS;

	/**
	 * ID: 1800391<br>
	 * Message: 万事が順調に行く。油断したりふさぎ込んだりするのは禁物。
	 */
	public static final NpcStringId EVERYTHING_WILL_GO_SMOOTHLY_BUT_BE_AWARE_OF_DANGER_FROM_BEING_CARELESS_AND_REMISS;

	/**
	 * ID: 1800392<br>
	 * Message: 愛する人には惜しみなく愛を注げ。大きな報賞が得られる。
	 */
	public static final NpcStringId IF_YOU_SINCERELY_CARE_FOR_SOMEONE_YOU_LOVE_A_BIG_REWARD_WILL_RETURN_TO_YOU;

	/**
	 * ID: 1800393<br>
	 * Message: 重病に良薬を得る。
	 */
	public static final NpcStringId A_REMEDY_IS_ON_ITS_WAY_FOR_A_SERIOUS_ILLNESS;

	/**
	 * ID: 1800394<br>
	 * Message: 難病で苦しむが、良薬を得て活力を取り戻す。
	 */
	public static final NpcStringId YOU_MAY_ACQUIRE_A_PRECIOUS_MEDICINE_TO_RECOVER_AFTER_SUFFERING_A_DISEASE_OF_A_SERIOUS_NATURE;

	/**
	 * ID: 1800395<br>
	 * Message: 苦境で貴人に出会い望みが叶う。
	 */
	public static final NpcStringId YOU_MAY_REALIZE_YOUR_DREAM_BY_MEETING_A_MAN_OF_DISTINCTION_AT_A_DIFFICULT_TIME;

	/**
	 * ID: 1800396<br>
	 * Message: 旅路には峠がつきもの。
	 */
	public static final NpcStringId YOU_MAY_SUFFER_ONE_OR_TWO_HARDSHIPS_ON_YOUR_JOURNEY;

	/**
	 * ID: 1800397<br>
	 * Message: 微笑を忘れず挫折しなければ、周りの人々から信頼と助けを得る。
	 */
	public static final NpcStringId IF_YOU_KEEP_SMILING_WITHOUT_DESPAIR_PEOPLE_WILL_COME_TO_TRUST_YOU_AND_OFFER_HELP;

	/**
	 * ID: 1800398<br>
	 * Message: 変化よりは安定を。
	 */
	public static final NpcStringId SEEK_STABILITY_RATHER_THAN_DYNAMICS_IN_YOUR_LIFE;

	/**
	 * ID: 1800399<br>
	 * Message: 万事、慎重かつ確実に進めるべし。
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_TO_BE_CAREFUL_AND_SECURE_AT_ALL_TIMES;

	/**
	 * ID: 1800400<br>
	 * Message: 手足が合わず仕事を進められない。
	 */
	public static final NpcStringId YOU_CANT_PERFORM_THE_JOB_WITH_BOUND_HANDS;

	/**
	 * ID: 1800401<br>
	 * Message: 推進力を失いさまよう。
	 */
	public static final NpcStringId YOU_MAY_LOSE_YOUR_DRIVE_AND_FEEL_LOST;

	/**
	 * ID: 1800402<br>
	 * Message: 問題頻発し、仕事に集中できない。
	 */
	public static final NpcStringId YOU_MAY_BE_UNABLE_TO_CONCENTRATE_WITH_SO_MANY_PROBLEMS_OCCURRING;

	/**
	 * ID: 1800403<br>
	 * Message: 手柄を人に横取りされる。
	 */
	public static final NpcStringId YOUR_ACHIEVEMENT_UNFAIRLY_MAY_GO_SOMEWHERE_ELSE;

	/**
	 * ID: 1800404<br>
	 * Message: 確実でなければ手をつけるな。
	 */
	public static final NpcStringId DO_NOT_START_A_TASK_THATS_NOT_CLEAR_TO_YOU;

	/**
	 * ID: 1800405<br>
	 * Message: 万が一に備えよ。
	 */
	public static final NpcStringId YOU_WILL_NEED_TO_BE_PREPARED_FOR_ALL_EVENTS;

	/**
	 * ID: 1800406<br>
	 * Message: 危機に瀕してもあきらめずにたゆまぬ努力を。認めてくれる人が現れる。
	 */
	public static final NpcStringId SOMEONE_WILL_ACKNOWLEDGE_YOU_IF_YOU_RELENTLESSLY_KEEP_TRYING_AND_DO_NOT_GIVE_UP_WHEN_FACING_HARDSHIPS;

	/**
	 * ID: 1800407<br>
	 * Message: 自分の真の姿が現れる。
	 */
	public static final NpcStringId YOU_MAY_PERFECT_YOURSELF_LIKE_A_DRAGONS_HORN_DECORATES_THE_DRAGON;

	/**
	 * ID: 1800408<br>
	 * Message: 徐々に真価を発揮する。
	 */
	public static final NpcStringId YOUR_TRUE_VALUE_STARTS_TO_SHINE;

	/**
	 * ID: 1800409<br>
	 * Message: 情報通であれ。新しいことを追い求めよ。あなたの価値が上がる。
	 */
	public static final NpcStringId YOUR_STEADY_PURSUIT_OF_NEW_INFORMATION_AND_STAYING_AHEAD_OF_OTHERS_WILL_RAISE_YOUR_VALUE;

	/**
	 * ID: 1800410<br>
	 * Message: 仕事も人間関係も勢いよくせよ。いい結果が得られる。
	 */
	public static final NpcStringId MAINTAINING_CONFIDENCE_WITH_WORK_OR_RELATIONSHIPS_MAY_BRING_GOOD_RESULTS;

	/**
	 * ID: 1800411<br>
	 * Message: 過信は災いのもと。仲間と協力せよ。
	 */
	public static final NpcStringId LEARN_TO_WORK_WITH_OTHERS_SINCE_OVERCONFIDENCE_WILL_BEAR_WRATH;

	/**
	 * ID: 1800412<br>
	 * Message: 鬼に金棒。
	 */
	public static final NpcStringId THE_DRAGON_NOW_ACQUIRES_AN_EAGLES_WINGS;

	/**
	 * ID: 1800413<br>
	 * Message: 鬼に金棒。夢と目標が実現する。
	 */
	public static final NpcStringId AS_THE_DRAGON_FLIES_HIGH_IN_THE_SKY_YOUR_GOALS_AND_DREAMS_MAY_COME_TRUE;

	/**
	 * ID: 1800414<br>
	 * Message: 仕事、趣味、家族、恋人。万事運気上昇。
	 */
	public static final NpcStringId LUCK_ENTERS_INTO_YOUR_WORK_HOBBY_FAMILY_AND_LOVE;

	/**
	 * ID: 1800415<br>
	 * Message: 向うところ敵なし。
	 */
	public static final NpcStringId WHATEVER_YOU_DO_IT_WILL_ACCOMPANY_WINNING;

	/**
	 * ID: 1800416<br>
	 * Message: 思わぬところから金銭やチャンスが転がり込む。
	 */
	public static final NpcStringId ITS_AS_GOOD_AS_IT_GETS_WITH_UNFORESEEN_FORTUNE_ROLLING_YOUR_WAY;

	/**
	 * ID: 1800417<br>
	 * Message: 欲張って後先考えず進めれば怪我をする。
	 */
	public static final NpcStringId A_GREEDY_ACT_WITH_NO_PRUDENCE_WILL_BRING_A_SURPRISE_AT_THE_END;

	/**
	 * ID: 1800418<br>
	 * Message: 冷静に考えて、慎重に行動せよ。
	 */
	public static final NpcStringId THINK_CAREFULLY_AND_ACT_WITH_CAUTION_AT_ALL_TIMES;

	/**
	 * ID: 1800419<br>
	 * Message: どうあがこうと所詮は根無し草。いいかげん。
	 */
	public static final NpcStringId IF_A_TREE_DOESNT_HAVE_ITS_ROOTS_THERE_WILL_BE_NO_FRUIT;

	/**
	 * ID: 1800420<br>
	 * Message: 骨折り損のくたびれ儲け。
	 */
	public static final NpcStringId HARD_WORK_DOESNT_BEAR_FRUIT;

	/**
	 * ID: 1800421<br>
	 * Message: 生活が困窮して苦境に立つ。
	 */
	public static final NpcStringId FINANCIAL_DIFFICULTIES_MAY_BRING_AN_ORDEAL;

	/**
	 * ID: 1800422<br>
	 * Message: 順調に進んでいたことに急ブレーキがかかる。
	 */
	public static final NpcStringId WHAT_USED_TO_BE_WELL_MANAGED_MAY_STUMBLE_ONE_AFTER_ANOTHER;

	/**
	 * ID: 1800423<br>
	 * Message: 思ったほどの成果は得られずもどかしい。
	 */
	public static final NpcStringId A_FEELING_OF_FRUSTRATION_MAY_FOLLOW_DISAPPOINTMENT;

	/**
	 * ID: 1800424<br>
	 * Message: 周りの人々に当り散らせば人間関係にひびが入る。
	 */
	public static final NpcStringId BE_CAUTIONED_AS_UNHARNESSED_BEHAVIOR_AT_DIFFICULT_TIMES_CAN_RUIN_RELATIONSHIPS;

	/**
	 * ID: 1800425<br>
	 * Message: 欲を張らず、足るを知れ。
	 */
	public static final NpcStringId CURTAIL_GREED_AND_BE_GRATEFUL_FOR_SMALL_RETURNS_AS_MODESTY_IS_NEEDED;

	/**
	 * ID: 1800426<br>
	 * Message: せっかく得た人が去る。
	 */
	public static final NpcStringId THE_PERSON_THAT_CAME_UNDER_YOUR_WINGS_WILL_LEAVE;

	/**
	 * ID: 1800427<br>
	 * Message: 人間関係に気を使えば皆仲良くなり、問題も解決。
	 */
	public static final NpcStringId YOUR_WORK_AND_RELATIONSHIP_WITH_COLLEAGUES_WILL_BE_WELL_MANAGED_IF_YOU_MAINTAIN_YOUR_DEVOTION;

	/**
	 * ID: 1800428<br>
	 * Message: 人付き合いで利害関係ばかり考えて礼儀を守らなければ、陰口を叩かれて信頼は地に落ちる。
	 */
	public static final NpcStringId CALCULATING_YOUR_PROFIT_IN_RELATIONSHIPS_WITHOUT_DISPLAYING_ANY_COURTEOUS_MANNERS_WILL_BRING_MALICIOUS_GOSSIP_AND_RUIN_YOUR_VALUE;

	/**
	 * ID: 1800429<br>
	 * Message: 常に相手の立場に立って考えて、真心で接せよ。
	 */
	public static final NpcStringId CONSIDER_OTHERS_SITUATIONS_AND_TREAT_THEM_SINCERELY_AT_ALL_TIMES;

	/**
	 * ID: 1800430<br>
	 * Message: 緊張感を失うな。
	 */
	public static final NpcStringId DO_NOT_LOOSEN_UP_WITH_YOUR_PRECAUTIONS;

	/**
	 * ID: 1800431<br>
	 * Message: 独断で事を進めれば失敗する。周りの言うことに耳を傾けよ。
	 */
	public static final NpcStringId REFLECT_OTHERS_OPINIONS_AS_A_MISTAKE_ALWAYS_LIES_AHEAD_OF_AN_ARBITRARY_DECISION;

	/**
	 * ID: 1800432<br>
	 * Message: らくだが針の穴を通る。
	 */
	public static final NpcStringId A_BLIND_MAN_GOES_RIGHT_THROUGH_THE_DOOR;

	/**
	 * ID: 1800433<br>
	 * Message: 仕事のゴールが見えずもどかしい。
	 */
	public static final NpcStringId A_HEART_FALLS_INTO_HOPELESSNESS_AS_THINGS_ARE_IN_DISARRAY;

	/**
	 * ID: 1800434<br>
	 * Message: 事が迷宮入りしてもどかしい。
	 */
	public static final NpcStringId HOPELESSNESS_MAY_FILL_YOUR_HEART_AS_YOUR_WORK_FALLS_INTO_A_MAZE;

	/**
	 * ID: 1800435<br>
	 * Message: 一所懸命で事にあたっているのに問題にぶちあたる。
	 */
	public static final NpcStringId DIFFICULTIES_LIE_AHEAD_OF_AN_UNFORESEEN_PROBLEM_EVEN_WITH_YOUR_HARD_WORK;

	/**
	 * ID: 1800436<br>
	 * Message: 万事に自信を失い、他人を頼りっきりになる。
	 */
	public static final NpcStringId THERE_MAY_BE_MORE_OCCASIONS_YOU_WILL_WANT_TO_ASK_FAVORS_FROM_OTHERS_AS_YOU_LOSE_CONFIDENCE_IN_YOU;

	/**
	 * ID: 1800437<br>
	 * Message: 消極的過ぎては発展は望めない。所信に従え。
	 */
	public static final NpcStringId BE_BRAVE_AND_AMBITIOUS_AS_NO_BIRD_CAN_FLY_INTO_THE_SKY_BY_STAYING_IN_THEIR_NEST;

	/**
	 * ID: 1800438<br>
	 * Message: 不透明なことには手をつけるな。信頼の置ける人を探せ。
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_NOT_TO_START_AN_UNCLEAR_TASK_AND_ALWAYS_LOOK_FOR_SOMEONE_YOU_CAN_TRUST_AND_RELY_UPON;

	/**
	 * ID: 1800439<br>
	 * Message: 陸に上がった河童のようだ。
	 */
	public static final NpcStringId HUNTING_WONT_BE_SUCCESSFUL_AS_THE_FALCON_LACKS_ITS_CLAWS;

	/**
	 * ID: 1800440<br>
	 * Message: やることなすことうまくいかない。
	 */
	public static final NpcStringId A_PREPARED_PLAN_WONT_MOVE_SMOOTHLY;

	/**
	 * ID: 1800441<br>
	 * Message: 自らを過信して欲張れば、うまくいくはずのことも失敗する。
	 */
	public static final NpcStringId AN_EASY_TASK_MAY_FAIL_IF_ONE_IS_CONSUMED_BY_GREED_AND_OVERCONFIDENCE;

	/**
	 * ID: 1800442<br>
	 * Message: 不利な状況で焦燥感に狩られる。
	 */
	public static final NpcStringId IMPATIENCE_MAY_LIE_AHEAD_AS_THE_SITUATION_IS_UNFAVORABLE;

	/**
	 * ID: 1800443<br>
	 * Message: 悪い目に遭わないように先を見通す目を持て。
	 */
	public static final NpcStringId THOUGHTFUL_FORESIGHT_IS_NEEDED_BEFORE_A_DISASTER_MAY_FALL_UPON_YOU;

	/**
	 * ID: 1800444<br>
	 * Message: 独断的な行動を慎め。皆の心を抱きしめよ。
	 */
	public static final NpcStringId REFRAIN_FROM_DICTATORIAL_ACTS_AS_CARING_FOR_OTHERS_AROUND_YOU_WITH_DIGNITY_IS_MUCH_NEEDED;

	/**
	 * ID: 1800445<br>
	 * Message: くらくらする。
	 */
	public static final NpcStringId THINGS_ARE_MESSY_WITH_NO_GOOD_SIGN;

	/**
	 * ID: 1800446<br>
	 * Message: よからぬことが起きて、苦境に立つ。
	 */
	public static final NpcStringId YOU_MAY_FALL_INTO_A_VEXING_SITUATION_AS_BAD_CIRCUMSTANCES_WILL_ARISE;

	/**
	 * ID: 1800447<br>
	 * Message: 人間関係にひびが入る。
	 */
	public static final NpcStringId RELATIONSHIPS_WITH_PEOPLE_MAY_BE_CONTRARY_TO_YOUR_EXPECTATIONS;

	/**
	 * ID: 1800448<br>
	 * Message: 問題処理は小手先ではなく、根本の見直しから。
	 */
	public static final NpcStringId DO_NOT_SEEK_A_QUICK_FIX_AS_THE_PROBLEM_NEEDS_A_FUNDAMENTAL_RESOLUTION;

	/**
	 * ID: 1800449<br>
	 * Message: 感情を抑えて心を平穏に保て。
	 */
	public static final NpcStringId SEEK_PEACE_IN_YOUR_HEART_AS_VULGAR_DISPLAY_OF_YOUR_EMOTIONS_MAY_HARM_YOU;

	/**
	 * ID: 1800450<br>
	 * Message: 対話は成功への近道。
	 */
	public static final NpcStringId INFORMATION_FOR_SUCCESS_MAY_COME_FROM_THE_CONVERSATIONS_WITH_PEOPLE_AROUND_YOU;

	/**
	 * ID: 1800451<br>
	 * Message: 万事、自信を持て。
	 */
	public static final NpcStringId BE_CONFIDENT_AND_ACT_RELIANTLY_AT_ALL_TIMES;

	/**
	 * ID: 1800452<br>
	 * Message: 棚からぼた餅。
	 */
	public static final NpcStringId A_CHILD_GETS_A_TREASURE;

	/**
	 * ID: 1800453<br>
	 * Message: たなぼた式に金銭とチャンスが得られる。
	 */
	public static final NpcStringId GOOD_FORTUNE_AND_OPPORTUNITY_MAY_LIE_AHEAD_AS_IF_ONES_BORN_IN_A_GOLDEN_SPOON;

	/**
	 * ID: 1800454<br>
	 * Message: 生活は順調。予期せぬ金銭と成果を手にする。
	 */
	public static final NpcStringId YOUR_LIFE_FLOWS_AS_IF_ITS_ON_A_SILK_SURFACE_AND_UNEXPECTED_FORTUNE_AND_SUCCESS_MAY_COME_TO_YOU;

	/**
	 * ID: 1800455<br>
	 * Message: 濡れ手に粟。しばらくの間は運に恵まれる。
	 */
	public static final NpcStringId TEMPORARY_LUCK_MAY_COME_TO_YOU_WITH_NO_EFFORT;

	/**
	 * ID: 1800456<br>
	 * Message: 計画は着実に、実行はすばやく。
	 */
	public static final NpcStringId PLAN_AHEAD_WITH_PATIENCE_BUT_EXECUTE_WITH_SWIFTNESS;

	/**
	 * ID: 1800457<br>
	 * Message: 小さな欠陥を見つけて直す。先を見通す力があなたの価値を高める。
	 */
	public static final NpcStringId THE_ABILITIES_TO_AMEND_FORESEE_AND_ANALYZE_MAY_RAISE_YOUR_VALUE;

	/**
	 * ID: 1800458<br>
	 * Message: 過ちを省みなければ、手に負えないほどの大失敗をする。
	 */
	public static final NpcStringId BIGGER_MISTAKES_WILL_BE_ON_THE_ROAD_IF_YOU_FAIL_TO_CORRECT_A_SMALL_MISTAKE;

	/**
	 * ID: 1800459<br>
	 * Message: 予定外の発見や経験は避けずに受け入れよ。
	 */
	public static final NpcStringId DONT_BE_EVASIVE_TO_ACCEPT_NEW_FINDINGS_OR_EXPERIENCES;

	/**
	 * ID: 1800460<br>
	 * Message: 思ったとおりにならなくても、そうあせるな。
	 */
	public static final NpcStringId DONT_BE_IRRITATED_AS_THE_SITUATIONS_DONT_MOVE_AS_PLANNED;

	/**
	 * ID: 1800461<br>
	 * Message: 明確に意志を示すこと。さもなくば、周りに流される。
	 */
	public static final NpcStringId BE_WARNED_AS_YOU_MAY_BE_OVERWHELMED_BY_SURROUNDINGS_IF_YOU_LACK_A_CLEAR_OPINION;

	/**
	 * ID: 1800462<br>
	 * Message: ぼろは着てても心は錦。
	 */
	public static final NpcStringId YOU_MAY_LIVE_AN_AFFLUENT_LIFE_EVEN_WITHOUT_POSSESSIONS;

	/**
	 * ID: 1800463<br>
	 * Message: 苦難の末に手にした財で人助け。人望が厚い。
	 */
	public static final NpcStringId YOU_WILL_GAIN_POPULARITY_AS_YOU_HELP_PEOPLE_WITH_MONEY_YOU_EARNESTLY_EARNED;

	/**
	 * ID: 1800464<br>
	 * Message: 心も体も軽々。
	 */
	public static final NpcStringId YOUR_HEART_AND_BODY_MAY_BE_IN_HEALTH;

	/**
	 * ID: 1800465<br>
	 * Message: ぼーっとしているうちに望まぬ方向に進む。
	 */
	public static final NpcStringId BE_WARNED_AS_YOU_MAY_BE_DRAGGED_TO_AN_UNWANTED_DIRECTION_IF_NOT_CAUTIOUS;

	/**
	 * ID: 1800466<br>
	 * Message: 新たに人に会っても、いい人にはなかなかめぐり合えない。
	 */
	public static final NpcStringId YOU_MAY_MEET_MANY_NEW_PEOPLE_BUT_IT_WILL_BE_DIFFICULT_TO_FIND_A_PERFECT_PERSON_WHO_WINS_YOUR_HEART;

	/**
	 * ID: 1800467<br>
	 * Message: 周りからなぐさめられる。
	 */
	public static final NpcStringId THERE_MAY_BE_AN_OCCASION_WHERE_YOU_ARE_CONSOLED_BY_PEOPLE;

	/**
	 * ID: 1800468<br>
	 * Message: けだるい日常。今は変化の時でない。
	 */
	public static final NpcStringId IT_MAY_NOT_BE_A_GOOD_TIME_FOR_A_CHANGE_EVEN_IF_THERES_TEDIUM_IN_DAILY_LIFE;

	/**
	 * ID: 1800469<br>
	 * Message: お金を使うことは未来のための投資。
	 */
	public static final NpcStringId THE_MONEY_YOU_SPEND_FOR_YOURSELF_MAY_ACT_AS_AN_INVESTMENT_AND_BRING_YOU_A_RETURN;

	/**
	 * ID: 1800470<br>
	 * Message: 他人のためにお金を使ってもどぶに捨てるのと同じ。他人に注意せよ。
	 */
	public static final NpcStringId THE_MONEY_YOU_SPEND_FOR_OTHERS_WILL_BE_WASTED_SO_BE_CAUTIOUS;

	/**
	 * ID: 1800471<br>
	 * Message: 浪費に注意。
	 */
	public static final NpcStringId BE_WARNED_SO_AS_NOT_TO_HAVE_UNNECESSARY_EXPENSES;

	/**
	 * ID: 1800472<br>
	 * Message: 景品プレゼントやイベントに参加せよ。大当たりの予感。
	 */
	public static final NpcStringId YOUR_STAR_INDICATED_SUCH_GOOD_LUCK_PARTICIPATE_IN_BONUS_GIVEAWAYS_OR_EVENTS;

	/**
	 * ID: 1800473<br>
	 * Message: 偶然幸運をつかむ。
	 */
	public static final NpcStringId YOU_MAY_GRAB_UNEXPECTED_LUCK;

	/**
	 * ID: 1800474<br>
	 * Message: 待ち人来たる。
	 */
	public static final NpcStringId THE_PERSON_IN_YOUR_HEART_MAY_NATURALLY_COME_TO_YOU;

	/**
	 * ID: 1800475<br>
	 * Message: 他人の評価など気にせずにマイペースを維持すればいい結果が得られる。
	 */
	public static final NpcStringId THERE_WILL_BE_A_GOOD_RESULT_IF_YOU_KEEP_YOUR_OWN_PACE_REGARDLESS_OF_OTHERS_JUDGMENT;

	/**
	 * ID: 1800476<br>
	 * Message: 予想外の幸運をつかんでも、舌禍で水泡に帰す。注意せよ。
	 */
	public static final NpcStringId BE_WARNED_AS_UNEXPECTED_LUCK_MAY_BE_WASTED_WITH_YOUR_RECKLESS_COMMENTS;

	/**
	 * ID: 1800477<br>
	 * Message: 飛んで火に入る夏の虫。過信は禁物。
	 */
	public static final NpcStringId OVERCONFIDENCE_WILL_CONVINCE_YOU_TO_CARRY_A_TASK_ABOVE_YOUR_REACH_AND_THERE_WILL_BE_CONSEQUENCES;

	/**
	 * ID: 1800478<br>
	 * Message: 重要な判断は後にせよ。
	 */
	public static final NpcStringId MOMENTARILY_DELAY_AN_IMPORTANT_DECISION;

	/**
	 * ID: 1800479<br>
	 * Message: 目上の人や仲間の言葉でトラブル発生。
	 */
	public static final NpcStringId TROUBLE_SPOTS_LIE_AHEAD_WHEN_TALKING_TO_SUPERIORS_OR_PEOPLE_CLOSE_TO_YOU;

	/**
	 * ID: 1800480<br>
	 * Message: 言葉で相手を傷つけたり傷つけられたりする。
	 */
	public static final NpcStringId BE_WARNED_AS_YOUR_WORDS_CAN_HURT_OTHERS_OR_OTHERS_WORDS_CAN_HURT_YOU;

	/**
	 * ID: 1800481<br>
	 * Message: ビッグマウスは浪費のもと。
	 */
	public static final NpcStringId MAKE_A_LOUD_BOAST_AND_YOU_MAY_HAVE_TO_PAY_TO_COVER_UNNECESSARY_EXPENSES;

	/**
	 * ID: 1800482<br>
	 * Message: いちゃもんをつけられるが、うまくかわせ。さもなくば大喧嘩になる。
	 */
	public static final NpcStringId SKILLFUL_EVASION_IS_NEEDED_WHEN_DEALING_WITH_PEOPLE_WHO_PICK_FIGHTS_AS_A_DISASTER_MAY_ARISE_FROM_IT;

	/**
	 * ID: 1800483<br>
	 * Message: 自己主張のしすぎで反感を買う。
	 */
	public static final NpcStringId KEEP_A_LOW_PROFILE_AS_TOO_STRONG_AN_OPINION_WILL_ATTRACT_ADVERSE_REACTIONS;

	/**
	 * ID: 1800484<br>
	 * Message: 陰口をたたかれぬよう、誤解を買う行動を慎め。
	 */
	public static final NpcStringId DO_NOT_UNNECESSARILY_PROVOKE_MISUNDERSTANDING_AS_YOU_MAY_BE_INVOLVED_IN_MALICIOUS_GOSSIP;

	/**
	 * ID: 1800485<br>
	 * Message: 紛失に注意。
	 */
	public static final NpcStringId CHECK_YOUR_BELONGINGS_AS_YOU_MAY_LOSE_WHAT_YOU_POSSESS;

	/**
	 * ID: 1800486<br>
	 * Message: 相手に話にはあいづちをうて。
	 */
	public static final NpcStringId BE_FLEXIBLE_ENOUGH_TO_PLAY_UP_TO_OTHERS;

	/**
	 * ID: 1800487<br>
	 * Message: 人間関係がこじれる。口を慎め。
	 */
	public static final NpcStringId PAY_SPECIAL_ATTENTION_WHEN_MEETING_OR_TALKING_TO_PEOPLE_AS_RELATIONSHIPS_MAY_GO_AMISS;

	/**
	 * ID: 1800488<br>
	 * Message: 重要な決定の瞬間。人目を気にせず、思い通りに進めよ。ただし、空気を読め。
	 */
	public static final NpcStringId WHEN_THE_IMPORTANT_MOMENT_ARRIVES_DECIDE_UPON_WHAT_YOU_TRULY_WANT_WITHOUT_MEASURING_OTHERS_JUDGMENT;

	/**
	 * ID: 1800489<br>
	 * Message: 書を持って旅に出よ。
	 */
	public static final NpcStringId LUCK_WILL_ALWAYS_FOLLOW_YOU_IF_YOU_TRAVEL_AND_READ_MANY_BOOKS;

	/**
	 * ID: 1800490<br>
	 * Message: いいアイディアが浮かぶ。あなたのアドバイスを必要としているところに行け。
	 */
	public static final NpcStringId HEAD_TO_A_PLACE_THAT_NEEDS_YOUR_ADVICE_AS_GOOD_IDEAS_AND_WISDOM_WILL_FLOURISH;

	/**
	 * ID: 1800491<br>
	 * Message: あなたのアドバイスが相手の人生を変える。
	 */
	public static final NpcStringId SOMEONES_LIFE_MAY_CHANGE_UPON_YOUR_ADVICE;

	/**
	 * ID: 1800492<br>
	 * Message: 近視眼に陥ることなく、遠い未来を念頭に置け。
	 */
	public static final NpcStringId ITS_A_PROPER_TIME_TO_PLAN_FOR_THE_FUTURE_RATHER_THAN_A_SHORT_TERM_PLAN;

	/**
	 * ID: 1800493<br>
	 * Message: よく悩んでいい計画を立てよ。
	 */
	public static final NpcStringId MANY_THOUGHTFUL_PLANS_AT_PRESENT_TIME_WILL_BE_OF_GREAT_HELP_IN_THE_FUTURE;

	/**
	 * ID: 1800494<br>
	 * Message: 近しい人との喧嘩に注意。我慢あるのみ。
	 */
	public static final NpcStringId PATIENCE_MAY_BE_NEEDED_AS_A_BIG_QUARREL_ARISES_BETWEEN_YOU_AND_A_PERSON_CLOSE_TO_YOU;

	/**
	 * ID: 1800495<br>
	 * Message: 金が必要でも無心はするな。プライドを傷つけられるだけだ。
	 */
	public static final NpcStringId DO_NOT_ASK_FOR_FINANCIAL_HELP_WHEN_THE_TIME_IS_DIFFICULT_YOUR_PRIDE_WILL_BE_HURT_WITHOUT_GAINING_ANY_MONEY;

	/**
	 * ID: 1800496<br>
	 * Message: 小さな偶然が良縁につながる。
	 */
	public static final NpcStringId CONNECTION_WITH_A_SPECIAL_PERSON_STARTS_WITH_A_MERE_INCIDENT;

	/**
	 * ID: 1800497<br>
	 * Message: 頑固になれば危機を招く。
	 */
	public static final NpcStringId STUBBORNNESS_REGARDLESS_OF_THE_MATTER_WILL_ONLY_BEAR_DANGER;

	/**
	 * ID: 1800498<br>
	 * Message: ちょっとしたトラブルの種あり。口をつつしみ、マナーにも気をつけよ。
	 */
	public static final NpcStringId KEEP_GOOD_MANNERS_AND_VALUE_TACITURNITY_AS_LIGHT_HEARTEDNESS_MAY_BRING_MISFORTUNE;

	/**
	 * ID: 1800499<br>
	 * Message: 変わった異性に出会う。
	 */
	public static final NpcStringId YOU_MAY_MEET_THE_OPPOSITE_SEX;

	/**
	 * ID: 1800500<br>
	 * Message: 欲張りすぎて災いを招く。
	 */
	public static final NpcStringId GREED_BY_WANTING_TO_TAKE_WEALTH_MAY_BRING_UNFORTUNATE_DISASTER;

	/**
	 * ID: 1800501<br>
	 * Message: 損をする運勢。支出を抑えよ。
	 */
	public static final NpcStringId LOSS_IS_AHEAD_REFRAIN_FROM_INVESTING_TRY_TO_SAVE_THE_MONEY_IN_YOUR_POCKETS;

	/**
	 * ID: 1800502<br>
	 * Message: 金運よからず。金の無心は相手にするな。
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_DIM_AVOID_ANY_OFFERS;

	/**
	 * ID: 1800503<br>
	 * Message: 今日すべきことを明日しようとすれば苦境に立つ。
	 */
	public static final NpcStringId A_BIGGER_CHALLENGE_MAY_BE_WHEN_DELAYING_TODAYS_WORK;

	/**
	 * ID: 1800504<br>
	 * Message: 困難に襲われても責任を持って望めばいい結果を得る。
	 */
	public static final NpcStringId THERE_WILL_BE_DIFFICULTY_BUT_A_GOOD_RESULT_MAY_BE_AHEAD_WHEN_FACING_IT_RESPONSIBLY;

	/**
	 * ID: 1800505<br>
	 * Message: しんどくてももっと責任のある仕事を。必ず得をする。
	 */
	public static final NpcStringId EVEN_WITH_SOME_DIFFICULTIES_EXPAND_THE_RANGE_OF_YOUR_SCOPE_WHERE_YOU_ARE_IN_CHARGE_IT_WILL_RETURN_TO_YOU_AS_HELP;

	/**
	 * ID: 1800506<br>
	 * Message: 身辺整理をせねば、集中できない。
	 */
	public static final NpcStringId FOCUS_ON_MAINTAINING_ORGANIZED_SURROUNDINGS_TO_HELP_REDUCE_YOUR_LOSSES;

	/**
	 * ID: 1800507<br>
	 * Message: 人は追うよりも待つが吉。
	 */
	public static final NpcStringId LUCK_LIES_AHEAD_WHEN_WAITING_FOR_PEOPLE_RATHER_THAN_FOLLOWING_THEM;

	/**
	 * ID: 1800508<br>
	 * Message: 緊急時であってもこちらから手を差し伸べるべからず。却って恨まれる破目に。
	 */
	public static final NpcStringId DO_NOT_OFFER_YOUR_HAND_FIRST_EVEN_WHEN_THINGS_ARE_HASTY_THE_RELATIONSHIP_MAY_FALL_APART;

	/**
	 * ID: 1800509<br>
	 * Message: 金運上昇。
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_RISING_THERE_WILL_BE_SOME_GOOD_RESULT;

	/**
	 * ID: 1800510<br>
	 * Message: 思いつきで事を進めれば、危機に瀕する。
	 */
	public static final NpcStringId YOU_MAY_FALL_IN_DANGER_EACH_TIME_WHEN_ACTING_UPON_IMPROVISATION;

	/**
	 * ID: 1800511<br>
	 * Message: 目上の人の前では歳相応の振る舞いを。
	 */
	public static final NpcStringId BE_WARNED_AS_A_CHILDISHLY_ACT_BEFORE_ELDERS_MAY_RUIN_EVERYTHING;

	/**
	 * ID: 1800512<br>
	 * Message: 天狗になれば運も去る。
	 */
	public static final NpcStringId THINGS_WILL_MOVE_EFFORTLESSLY_BUT_LUCK_WILL_VANISH_WITH_YOUR_AUDACITY;

	/**
	 * ID: 1800513<br>
	 * Message: 謙遜が運を長持ちさせる。
	 */
	public static final NpcStringId LUCK_MAY_BE_CONTINUED_ONLY_WHEN_HUMILITY_IS_MAINTAINED_AFTER_SUCCESS;

	/**
	 * ID: 1800514<br>
	 * Message: 第三者の出現による三角関係にご用心。
	 */
	public static final NpcStringId A_NEW_PERSON_MAY_APPEAR_TO_CREATE_A_LOVE_TRIANGLE;

	/**
	 * ID: 1800515<br>
	 * Message: 自分と同じスタイルの人を探せ。
	 */
	public static final NpcStringId LOOK_FOR_SOMEONE_WITH_A_SIMILAR_STYLE_IT_WILL_OPEN_UP_FOR_THE_GOOD;

	/**
	 * ID: 1800516<br>
	 * Message: オファーは断れ。今は時期ではない。
	 */
	public static final NpcStringId AN_OFFER_MAY_SOON_BE_MADE_TO_COLLABORATE_A_TASK_BUT_DELAYING_IT_WILL_BE_A_GOOD_IDEA;

	/**
	 * ID: 1800517<br>
	 * Message: 急がせるようなオファーは断れ。
	 */
	public static final NpcStringId PARTNERSHIP_IS_OUT_OF_LUCK_AVOID_SOMEONE_WHO_RUSHES_YOU_TO_START_A_COLLABORATION;

	/**
	 * ID: 1800518<br>
	 * Message: 志を共にする人々のネットワークを作れ。後に大きい仕事をすることになる。
	 */
	public static final NpcStringId FOCUS_ON_NETWORKING_WITH_LIKE_MINDED_PEOPLE_THEY_MAY_JOIN_YOU_FOR_A_BIG_MISSION_IN_THE_FUTURE;

	/**
	 * ID: 1800519<br>
	 * Message: あなたは純真な人だと言って近寄ってくる者に気をつけよ。
	 */
	public static final NpcStringId BE_WARNED_WHEN_SOMEONE_SAYS_YOU_ARE_INNOCENT_AS_THATS_NOT_A_COMPLIMENT;

	/**
	 * ID: 1800520<br>
	 * Message: 詐欺の恐れあり。なめられたら大損する。
	 */
	public static final NpcStringId YOU_MAY_BE_SCAMMED_BE_CAUTIOUS_AS_THERE_MAY_BE_A_BIG_LOSS_BY_UNDERESTIMATING_OTHERS;

	/**
	 * ID: 1800521<br>
	 * Message: 今は決定の時期ではない。自分の考えより常識に沿った判断を。
	 */
	public static final NpcStringId LUCK_AT_DECISION_MAKING_IS_DIM_AVOID_SUBJECTIVE_CONCLUSIONS_AND_RELY_ON_UNIVERSAL_COMMON_SENSE;

	/**
	 * ID: 1800522<br>
	 * Message: 心の弱さで重荷を背負い込む。自己主張ははっきりと。
	 */
	public static final NpcStringId YOUR_WEAKNESS_MAY_INVITE_HARDSHIPS_CAUTIOUSLY_TAKE_A_STRONG_POSITION_AS_NEEDED;

	/**
	 * ID: 1800523<br>
	 * Message: おしゃべりの遊び人に用心せよ。トラブルの恐れあり。
	 */
	public static final NpcStringId BE_WARY_OF_SOMEONE_WHO_TALKS_AND_ENTERTAINS_TOO_MUCH_THE_PERSON_MAY_BRING_YOU_MISFORTUNE;

	/**
	 * ID: 1800524<br>
	 * Message: ビギナーズラックの予感あり。
	 */
	public static final NpcStringId YOU_MAY_ENJOY_A_BEGINNERS_LUCK;

	/**
	 * ID: 1800525<br>
	 * Message: 金運はいいが、足るを知るべし。
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_STRONG_BUT_YOU_SHOULD_KNOW_WHEN_TO_WITHDRAW;

	/**
	 * ID: 1800526<br>
	 * Message: 欲張りすぎて儲けを失う。
	 */
	public static final NpcStringId ALREADY_ACQUIRED_WEALTH_CAN_BE_LOST_BY_GREED;

	/**
	 * ID: 1800527<br>
	 * Message: 一人でできることでも誰かとやるといい。
	 */
	public static final NpcStringId EVEN_IF_YOU_CAN_COMPLETE_IT_BY_YOURSELF_ITS_A_GOOD_IDEA_TO_HAVE_SOMEONE_HELP_YOU;

	/**
	 * ID: 1800528<br>
	 * Message: 最も肝心なことは和。わがままを言えば苦境に立つ。
	 */
	public static final NpcStringId MAKE_HARMONY_WITH_PEOPLE_THE_PRIORITY_STUBBORNNESS_MAY_BRING_HARDSHIPS;

	/**
	 * ID: 1800529<br>
	 * Message: 近しい人の知らなかったことがわかる。
	 */
	public static final NpcStringId THERE_MAY_BE_A_CHANCE_WHEN_YOU_CAN_SEE_A_NEW_ASPECT_OF_A_CLOSE_FRIEND;

	/**
	 * ID: 1800530<br>
	 * Message: 先入観を捨てよ。自分とは違うタイプの人ともつきあえ。
	 */
	public static final NpcStringId TRY_TO_BE_CLOSE_TO_SOMEONE_DIFFERENT_FROM_YOU_WITHOUT_ANY_STEREOTYPICAL_JUDGMENT;

	/**
	 * ID: 1800531<br>
	 * Message: リーダーになる予感。人の上に立つが、すこしつらい。
	 */
	public static final NpcStringId GOOD_LUCK_IN_BECOMING_A_LEADER_WITH_MANY_FOLLOWERS_HOWEVER_ITLL_ONLY_BE_AFTER_HARD_WORK;

	/**
	 * ID: 1800532<br>
	 * Message: 金運上昇。損して得取れ。
	 */
	public static final NpcStringId YOUR_WEALTH_LUCK_IS_RISING_EXPENDITURES_WILL_BE_FOLLOWED_BY_SUBSTANTIAL_INCOME_AS_YOU_ARE_ABLE_TO_SUSTAIN;

	/**
	 * ID: 1800533<br>
	 * Message: 金運は最良か最悪のどちらか。
	 */
	public static final NpcStringId BE_CAUTIOUS_AS_YOUR_WEALTH_LUCK_CAN_BE_EITHER_VERY_GOOD_OR_VERY_BAD;

	/**
	 * ID: 1800534<br>
	 * Message: 些細な争いで近しい人との仲が悪くなる。
	 */
	public static final NpcStringId BE_WARNED_AS_A_SMALL_ARGUMENT_CAN_DISTANCE_YOU_FROM_A_CLOSE_FRIEND;

	/**
	 * ID: 1800535<br>
	 * Message: 恋愛運上昇。
	 */
	public static final NpcStringId THERE_IS_LUCK_IN_LOVE_WITH_A_NEW_PERSON;

	/**
	 * ID: 1800536<br>
	 * Message: 情けは人のためならず。
	 */
	public static final NpcStringId A_BIGGER_FORTUNE_WILL_BE_FOLLOWED_BY_YOUR_GOOD_DEED;

	/**
	 * ID: 1800537<br>
	 * Message: 誤解を買えば失恋の恐れあり。
	 */
	public static final NpcStringId THERE_MAY_BE_A_RELATIONSHIP_BREAKING_TRY_TO_ELIMINATE_MISUNDERSTANDINGS;

	/**
	 * ID: 1800538<br>
	 * Message: 感銘を受けても簡単に信じてはならない。
	 */
	public static final NpcStringId BE_CAUTIOUS_NOT_TO_BE_EMOTIONALLY_MOVED_EVEN_IF_ITS_CONVINCING;

	/**
	 * ID: 1800539<br>
	 * Message: 笑う門には福来たる。
	 */
	public static final NpcStringId SMILING_WILL_BRING_GOOD_LUCK;

	/**
	 * ID: 1800540<br>
	 * Message: 小さな損でいつまでもくよくよするな。
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_TO_LET_GO_OF_A_SMALL_LOSS;

	/**
	 * ID: 1800541<br>
	 * Message: コミュニケーションがうまくいかず、誤解が生じる。
	 */
	public static final NpcStringId CONVEYING_YOUR_OWN_TRUTH_MAY_BE_DIFFICULT_AND_EASY_MISUNDERSTANDINGS_WILL_FOLLOW;

	/**
	 * ID: 1800542<br>
	 * Message: 人の集まるところから幸運がやってくる。
	 */
	public static final NpcStringId THERE_IS_GOOD_LUCK_IN_A_PLACE_WITH_MANY_PEOPLE;

	/**
	 * ID: 1800543<br>
	 * Message: まっすぐすぎるのは却ってよくない。
	 */
	public static final NpcStringId TRY_TO_AVOID_DIRECTNESS_IF_YOU_CAN;

	/**
	 * ID: 1800544<br>
	 * Message: 見た目より中身が肝心。
	 */
	public static final NpcStringId VALUE_SUBSTANCE_OPPOSED_TO_THE_SAKE_HONOR_AND_LOOK_BEYOND_WHATS_IN_FRONT_OF_YOU;

	/**
	 * ID: 1800545<br>
	 * Message: 人間関係はちょっと厚かましいぐらいがいい。
	 */
	public static final NpcStringId EXPANDING_A_RELATIONSHIP_WITH_HUMOR_MAY_BE_A_GOOD_IDEA;

	/**
	 * ID: 1800546<br>
	 * Message: ちょっとした賭けで儲かる。
	 */
	public static final NpcStringId AN_ENJOYABLE_EVENT_MAY_BE_AHEAD_IF_YOU_ACCEPT_A_SIMPLE_BET;

	/**
	 * ID: 1800547<br>
	 * Message: 人間関係はウェットになりすぎないように。冷静に考えることも必要。
	 */
	public static final NpcStringId BEING_LEVEL_HEADED_NOT_FOCUSING_ON_EMOTIONS_MAY_HELP_WITH_RELATIONSHIPS;

	/**
	 * ID: 1800548<br>
	 * Message: 仕事は軽重を問わず順序どおり進めるべし。
	 */
	public static final NpcStringId ITS_A_GOOD_IDEA_TO_TAKE_CARE_OF_MATTERS_IN_SEQUENTIAL_ORDER_WITHOUT_MEASURING_THEIR_IMPORTANCE;

	/**
	 * ID: 1800549<br>
	 * Message: しっかり調べて自信を持って行動すれば、皆に慕われる。
	 */
	public static final NpcStringId A_DETERMINED_ACT_AFTER_PREPARED_RESEARCH_WILL_ATTRACT_PEOPLE;

	/**
	 * ID: 1800550<br>
	 * Message: 笑いを取って視線を集めよ。
	 */
	public static final NpcStringId A_LITTLE_HUMOR_MAY_BRING_COMPLETE_ATTENTION_TO_YOU;

	/**
	 * ID: 1800551<br>
	 * Message: 重要な決定は延期せよ。金銭の取引は避けよ。
	 */
	public static final NpcStringId IT_MAY_NOT_BE_A_GOOD_TIME_FOR_AN_IMPORTANT_DECISION_BE_WARY_OF_TEMPTATIONS_AND_AVOID_MONETARY_DEALINGS;

	/**
	 * ID: 1800552<br>
	 * Message: 近しい人の助言を心に刻め。
	 */
	public static final NpcStringId PAY_SPECIAL_ATTENTION_TO_ADVICE_FROM_A_CLOSE_FRIEND;

	/**
	 * ID: 1800553<br>
	 * Message: いかなる問題も第三者の立場から考えよ。円満な解決策が見つかる。
	 */
	public static final NpcStringId THERE_MAY_BE_MODERATE_SOLUTIONS_TO_EVERY_PROBLEM_WHEN_THEYRE_VIEWED_FROM_A_3RD_PARTYS_POINT_OF_VIEW;

	/**
	 * ID: 1800554<br>
	 * Message: 近しい人との取引は断れ。頭の痛いことがおきる。
	 */
	public static final NpcStringId DEALINGS_WITH_CLOSE_FRIENDS_ONLY_BRING_FRUSTRATION_AND_HEADACHE_POLITELY_DECLINE_AND_MENTION_ANOTHER_CHANCE;

	/**
	 * ID: 1800555<br>
	 * Message: 基礎を疎かにすれば仕上げに問題が生じる。
	 */
	public static final NpcStringId THERE_MAY_BE_A_PROBLEM_AT_COMPLETION_IF_THE_BASIC_MATTERS_ARE_NOT_CONSIDERED_FROM_THE_BEGINNING;

	/**
	 * ID: 1800556<br>
	 * Message: 公私の区別をはっきりと。
	 */
	public static final NpcStringId DISTINGUISHING_BUSINESS_FROM_A_PRIVATE_MATTER_IS_NEEDED_TO_SUCCEED;

	/**
	 * ID: 1800557<br>
	 * Message: 仕事が上手くいかないなら横着をしてみるのも一つの手。
	 */
	public static final NpcStringId A_CHANGE_IN_RULES_MAY_BE_HELPFUL_WHEN_PROBLEMS_ARE_PERSISTENT;

	/**
	 * ID: 1800558<br>
	 * Message: 小さなことだと飛ばせば、予想外の状況に苦しめられる。
	 */
	public static final NpcStringId PREPARING_FOR_AN_UNFORESEEN_SITUATION_WILL_BE_DIFFICULT_WHEN_SMALL_MATTERS_ARE_IGNORED;

	/**
	 * ID: 1800559<br>
	 * Message: 他人事には必要以上首を突っ込むな。
	 */
	public static final NpcStringId REFRAIN_FROM_GETTING_INVOLVED_IN_OTHERS_BUSINESS_TRY_TO_BE_LOOSE_AS_A_GOOSE;

	/**
	 * ID: 1800560<br>
	 * Message: 中庸も大切だが、確実な意思表示も時には必要。
	 */
	public static final NpcStringId BEING_NEUTRAL_IS_A_GOOD_WAY_TO_GO_BUT_CLARITY_MAY_BE_HELPFUL_CONTRARY_TO_YOUR_HESITANCE;

	/**
	 * ID: 1800561<br>
	 * Message: 過去のことで誤解が生じる。行動に注意せよ。
	 */
	public static final NpcStringId BE_CAUTIOUS_OF_YOUR_OWN_ACTIONS_THE_PAST_MAY_BRING_MISUNDERSTANDINGS;

	/**
	 * ID: 1800562<br>
	 * Message: 情に流されて時間を無駄にする。時間管理に気をつけよ。
	 */
	public static final NpcStringId PAY_ATTENTION_TO_TIME_MANAGEMENT_EMOTIONS_MAY_WASTE_YOUR_TIME;

	/**
	 * ID: 1800563<br>
	 * Message: 中身のない義侠心は意味がない。
	 */
	public static final NpcStringId HEROISM_WILL_BE_REWARDED_BUT_BE_CAREFUL_NOT_TO_DISPLAY_ARROGANCE_OR_LACK_OF_SINCERITY;

	/**
	 * ID: 1800564<br>
	 * Message: 誤解された人と仲直りせよ。関係が回復する。
	 */
	public static final NpcStringId IF_YOU_WANT_TO_MAINTAIN_RELATIONSHIP_CONNECTIONS_OFFER_RECONCILIATION_TO_THOSE_WHO_HAD_MISUNDERSTANDINGS_WITH_YOU;

	/**
	 * ID: 1800565<br>
	 * Message: 相手に行動にもどかしさを感じたら、解決に乗り出せ。
	 */
	public static final NpcStringId STEP_FORWARD_TO_SOLVE_OTHERS_PROBLEMS_WHEN_THEY_ARE_UNABLE;

	/**
	 * ID: 1800566<br>
	 * Message: 若干の損失も未来への投資。
	 */
	public static final NpcStringId THERE_MAY_BE_A_LITTLE_LOSS_BUT_THINK_OF_IT_AS_AN_INVESTMENT_FOR_YOURSELF;

	/**
	 * ID: 1800567<br>
	 * Message: 欲が欲を呼ぶ。足るを知るべき。
	 */
	public static final NpcStringId AVARICE_BEARS_A_BIGGER_GREED_BEING_SATISFIED_WITH_MODERATION_IS_NEEDED;

	/**
	 * ID: 1800568<br>
	 * Message: 何も考えず事を進めればトラブルに巻き込まれる。状況をよく読め。
	 */
	public static final NpcStringId A_RATIONAL_ANALYSIS_IS_NEEDED_AS_UNPLANNED_ACTIONS_MAY_BRING_CRITICISM;

	/**
	 * ID: 1800569<br>
	 * Message: 人を責めるより自らを省みよ。
	 */
	public static final NpcStringId REFLECT_UPON_YOUR_SHORTCOMINGS_BEFORE_CRITICIZING_OTHERS;

	/**
	 * ID: 1800570<br>
	 * Message: とっさの行動で危機を逃れる。後処理もしっかりと。
	 */
	public static final NpcStringId FOLLOW_UP_CARE_IS_ALWAYS_NEEDED_AFTER_AN_EMERGENCY_EVASION;

	/**
	 * ID: 1800571<br>
	 * Message: 新たな挑戦への欲求がわく。幅広く調べよ。
	 */
	public static final NpcStringId YOU_MAY_LOOK_FOR_A_NEW_CHALLENGE_BUT_VAST_KNOWLEDGE_IS_REQUIRED;

	/**
	 * ID: 1800572<br>
	 * Message: プライドを捨てれば誤解が解ける。
	 */
	public static final NpcStringId WHEN_ONE_PUTS_ASIDE_THEIR_EGO_ANY_MISUNDERSTANDING_WILL_BE_SOLVED;

	/**
	 * ID: 1800573<br>
	 * Message: 天狗にならず周りの助言に耳を傾けよ。
	 */
	public static final NpcStringId LISTEN_TO_THE_ADVICE_THATS_GIVEN_TO_YOU_WITH_A_HUMBLE_ATTITUDE;

	/**
	 * ID: 1800574<br>
	 * Message: 上り坂にいる時下り坂に備えよ。
	 */
	public static final NpcStringId EQUILIBRIUM_IS_ACHIEVED_WHEN_ONE_UNDERSTANDS_A_DOWNSHIFT_IS_EVIDENT_AFTER_THE_RISE;

	/**
	 * ID: 1800575<br>
	 * Message: 種をまいた分だけ収穫がある。計画通りに誠実に進めよ。
	 */
	public static final NpcStringId WHAT_YOU_SOW_IS_WHAT_YOU_REAP_FAITHFULLY_FOLLOW_THE_PLAN;

	/**
	 * ID: 1800576<br>
	 * Message: 思いつきで事を進めれば精神的、金銭的に大損する。用意はしっかりと。
	 */
	public static final NpcStringId METICULOUS_PREPARATION_IS_NEEDED_AS_SPONTANEOUS_ACTIONS_ONLY_BEAR_MENTAL_AND_MONETARY_LOSSES;

	/**
	 * ID: 1800577<br>
	 * Message: 人の目に気を取られてなかなか終わらない。
	 */
	public static final NpcStringId THE_RIGHT_TIME_TO_BEAR_FRUIT_IS_DELAYED_WHILE_THE_FARMER_PONDERS_OPINIONS;

	/**
	 * ID: 1800578<br>
	 * Message: 仲良し同士で助け合え。
	 */
	public static final NpcStringId HELP_EACH_OTHER_AMONG_CLOSE_FRIENDS;

	/**
	 * ID: 1800579<br>
	 * Message: 目先の些細な利益に捕らわれるな。人との関係がこじれる。
	 */
	public static final NpcStringId OBSESSING_OVER_A_SMALL_PROFIT_WILL_PLACE_PEOPLE_APART;

	/**
	 * ID: 1800580<br>
	 * Message: 賭けに負けてもくよくよするな。
	 */
	public static final NpcStringId DONT_CLING_TO_THE_RESULT_OF_A_GAMBLE;

	/**
	 * ID: 1800581<br>
	 * Message: 小さな事故やトラブルの恐れあり。余裕を持て。
	 */
	public static final NpcStringId SMALL_TROUBLES_AND_ARGUMENTS_ARE_AHEAD_FACE_THEM_WITH_A_MATURE_ATTITUDE;

	/**
	 * ID: 1800582<br>
	 * Message: 約束を破れば苦境に立つ。
	 */
	public static final NpcStringId NEGLECTING_A_PROMISE_MAY_PUT_YOU_IN_DISTRESS;

	/**
	 * ID: 1800583<br>
	 * Message: 欠落の恐れあり。取引は保留せよ。
	 */
	public static final NpcStringId DELAY_ANY_DEALINGS_AS_YOU_MAY_EASILY_OMIT_ADDRESSING_WHATS_IMPORTANT_TO_YOU;

	/**
	 * ID: 1800584<br>
	 * Message: 人との比較が助けになる。
	 */
	public static final NpcStringId A_COMPARISON_TO_OTHERS_MAY_BE_HELPFUL;

	/**
	 * ID: 1800585<br>
	 * Message: 成長には痛みが付き物。苦労する甲斐がある。
	 */
	public static final NpcStringId WHAT_YOUVE_ENDURED_WILL_RETURN_AS_A_BENEFIT;

	/**
	 * ID: 1800586<br>
	 * Message: 礼儀を守って正道を行け。
	 */
	public static final NpcStringId TRY_TO_BE_COURTEOUS_TO_THE_OPPOSITE_SEX_AND_FOLLOW_A_VIRTUOUS_PATH;

	/**
	 * ID: 1800587<br>
	 * Message: ちいさなことで大きな笑い。
	 */
	public static final NpcStringId JOY_MAY_COME_FROM_SMALL_THINGS;

	/**
	 * ID: 1800588<br>
	 * Message: 結果よし。自信を持って行動せよ。
	 */
	public static final NpcStringId BE_CONFIDENT_IN_YOUR_ACTIONS_AS_GOOD_LUCK_SHADOWS_THE_RESULT;

	/**
	 * ID: 1800589<br>
	 * Message: やましいところがないなら堂々と向かい合え。
	 */
	public static final NpcStringId BE_CONFIDENT_WITHOUT_HESITATION_WHEN_YOUR_HONESTY_IS_ABOVE_REPROACH_IN_DEALINGS;

	/**
	 * ID: 1800590<br>
	 * Message: 親しき仲にも礼儀あり。さもなくば孤立無援の恐れ。
	 */
	public static final NpcStringId A_MATTER_RELATED_TO_A_CLOSE_FRIEND_CAN_ISOLATE_YOU_KEEP_STAYING_ON_THE_RIGHT_PATH;

	/**
	 * ID: 1800591<br>
	 * Message: 結果のことでいつまでもくよくよするな。よからぬことが起きる。
	 */
	public static final NpcStringId TOO_MUCH_FOCUS_ON_THE_RESULT_MAY_BRING_CONTINUOUS_MISFORTUNE;

	/**
	 * ID: 1800592<br>
	 * Message: 途中で投げ出せば後々困ったことになる。根気を持て。
	 */
	public static final NpcStringId BE_TENACIOUS_UNTIL_THE_FINISH_AS_HALFWAY_ABANDONMENT_CAUSES_A_TROUBLED_ENDING;

	/**
	 * ID: 1800593<br>
	 * Message: 団体行動では得をしない。
	 */
	public static final NpcStringId THERE_WILL_BE_NO_ADVANTAGE_IN_A_GROUP_DEAL;

	/**
	 * ID: 1800594<br>
	 * Message: でしゃばらず一歩引け。状況には能動的に対処せよ。
	 */
	public static final NpcStringId REFRAIN_FROM_STEPPING_UP_BUT_TAKE_A_MOMENT_TO_PONDER_TO_BE_FLEXIBLE_WITH_SITUATIONS;

	/**
	 * ID: 1800595<br>
	 * Message: 情報を最大限利用して小さな幸運をつかめ。
	 */
	public static final NpcStringId THERE_WILL_BE_A_SMALL_OPPORTUNITY_WHEN_INFORMATION_IS_BEST_UTILIZED;

	/**
	 * ID: 1800596<br>
	 * Message: 紛失の恐れあり。大事なものならちゃんと保管せよ。
	 */
	public static final NpcStringId BELONGINGS_ARE_AT_LOOSE_ENDS_KEEP_TRACK_OF_THE_THINGS_YOU_VALUE;

	/**
	 * ID: 1800597<br>
	 * Message: 努力は報われる。
	 */
	public static final NpcStringId WHAT_YOU_SOW_IS_WHAT_YOU_REAP_TRY_YOUR_BEST;

	/**
	 * ID: 1800598<br>
	 * Message: 新たにスタートを切る気持ちで臨めば、不足もすぐに補える。
	 */
	public static final NpcStringId WITH_THE_BEGINNERS_ATTITUDE_SHORTCOMINGS_CAN_BE_EASILY_MENDED;

	/**
	 * ID: 1800599<br>
	 * Message: 問題があれば方向を完全に変えよ。
	 */
	public static final NpcStringId WHEN_FACING_DIFFICULTIES_SEEK_A_TOTALLY_DIFFERENT_DIRECTION;

	/**
	 * ID: 1800600<br>
	 * Message: 欲の張りすぎで積み重ねてきたことが一度に崩れる。
	 */
	public static final NpcStringId LIFETIME_SAVINGS_CAN_DISAPPEAR_WITH_ONE_TIME_GREED;

	/**
	 * ID: 1800601<br>
	 * Message: 極論は自制すれば心穏やか。
	 */
	public static final NpcStringId WITH_YOUR_HEART_AVOID_EXTREMES_AND_PEACE_WILL_STAY;

	/**
	 * ID: 1800602<br>
	 * Message: 一瞬の油断で陰口をたたかれる破目に。
	 */
	public static final NpcStringId BE_CAUTIOUS_AS_INSTANT_RECKLESSNESS_MAY_BRING_MALICIOUS_GOSSIP;

	/**
	 * ID: 1800603<br>
	 * Message: 勝負欲があふれる。あきらめず最後までがんばれ。
	 */
	public static final NpcStringId BE_TENACIOUS_TO_THE_END_BECAUSE_A_STRONG_LUCK_WITH_WINNING_IS_AHEAD;

	/**
	 * ID: 1800604<br>
	 * Message: 近しい人には気を使いやさしくせよ。情けは人のためならず。
	 */
	public static final NpcStringId BE_KIND_TO_AND_CARE_FOR_THOSE_CLOSE_TO_YOU_THEY_MAY_HELP_IN_THE_FUTURE;

	/**
	 * ID: 1800605<br>
	 * Message: ポジティブシンキングがいい結果につながる。
	 */
	public static final NpcStringId POSITIVITY_MAY_BRING_GOOD_RESULTS;

	/**
	 * ID: 1800606<br>
	 * Message: 近しい人のミスはかばってやれ。
	 */
	public static final NpcStringId BE_GRACIOUS_TO_COVER_A_CLOSE_FRIENDS_FAULT;

	/**
	 * ID: 1800607<br>
	 * Message: 予想外の支出あり。
	 */
	public static final NpcStringId BE_PREPARED_FOR_AN_EXPECTED_COST;

	/**
	 * ID: 1800608<br>
	 * Message: 腹を割っても解決できなければ後遺症が生じる。筋を通すより思いやりを。
	 */
	public static final NpcStringId BE_CONSIDERATE_TO_OTHERS_AND_AVOID_FOCUSING_ONLY_ON_WINNING_OR_A_WOUND_WILL_BE_LEFT_UNTREATED;

	/**
	 * ID: 1800609<br>
	 * Message: アクセサリーがラッキーアイテム。
	 */
	public static final NpcStringId AN_ACCESSORY_OR_DECORATION_MAY_BRING_A_GOOD_LUCK;

	/**
	 * ID: 1800610<br>
	 * Message: 自重して謙遜せよ。実りは豊かだ。
	 */
	public static final NpcStringId ONLY_REFLECTION_AND_HUMILITY_MAY_BRING_SUCCESS;

	/**
	 * ID: 1800611<br>
	 * Message: 些細な誤解が争いにつながる。
	 */
	public static final NpcStringId A_SMALL_MISUNDERSTANDING_MAY_CAUSE_QUARRELS;

	/**
	 * ID: 1800612<br>
	 * Message: 無理をせず余裕を持って円満に進めよ。
	 */
	public static final NpcStringId AVOID_ADVANCING_BEYOND_YOUR_ABILITY_AND_FOCUS_ON_THE_FLOWING_STREAM;

	/**
	 * ID: 1800613<br>
	 * Message: 欲張らず思いやりの心を持て。心なくしていい結果があろうか。
	 */
	public static final NpcStringId CONSIDERING_OTHERS_WITH_A_GOOD_HEART_BEFORE_SELF_INTEREST_WILL_BRING_A_TRIUMPH;

	/**
	 * ID: 1800614<br>
	 * Message: 行けなかったところに行ける。
	 */
	public static final NpcStringId VISITING_A_PLACE_YOUVE_NEVER_BEEN_BEFORE_MAY_BRING_LUCK;

	/**
	 * ID: 1800615<br>
	 * Message: 人の集まるところから幸運がやってくる。
	 */
	public static final NpcStringId A_GOOD_THING_MAY_HAPPEN_IN_A_PLACE_WITH_A_FEW_PEOPLE;

	/**
	 * ID: 1800616<br>
	 * Message: 活発なのはいいが軽く見られて信用を失う。ユーモアがあっても、真面目さも必要。
	 */
	public static final NpcStringId BEING_HIGH_STRUNG_CAN_CAUSE_LOSS_OF_TRUST_FROM_OTHERS_BECAUSE_IT_CAN_BE_VIEWED_AS_LIGHT_HEARTED_ACT_SINCERELY_BUT_YET_DO_NOT_LACK_HUMOR;

	/**
	 * ID: 1800617<br>
	 * Message: 過程に問題があっても、後処理をしっかりすればいい結果が得られる。
	 */
	public static final NpcStringId PERFECTION_AT_THE_FINISH_CAN_COVER_FAULTY_WORK_IN_THE_PROCESS;

	/**
	 * ID: 1800618<br>
	 * Message: 仕事が多ければ得られるものも多い。一所懸命動ければそこそこの報賞が得られる。
	 */
	public static final NpcStringId ABSTAIN_FROM_LAZINESS_MUCH_WORK_BRINGS_MANY_GAINS_AND_SATISFACTORY_REWARDS;

	/**
	 * ID: 1800619<br>
	 * Message: 一ヶ所に留まらずどんどん動け。
	 */
	public static final NpcStringId STAYING_BUSY_RATHER_THAN_BEING_STATIONARY_WILL_HELP;

	/**
	 * ID: 1800620<br>
	 * Message: 一人で背負い込んで誘惑でだめになる。
	 */
	public static final NpcStringId HANDLING_THE_WORK_BY_YOURSELF_MAY_LEAD_YOU_INTO_TEMPTATION;

	/**
	 * ID: 1800621<br>
	 * Message: 小さなことでも耳を傾けよ。
	 */
	public static final NpcStringId PAY_ATTENTION_TO_ANY_SMALL_ADVICE_WITHOUT_BEING_INDIFFERENT;

	/**
	 * ID: 1800622<br>
	 * Message: わらしべ長者の予感。小さなことも大切にせよ。
	 */
	public static final NpcStringId SMALL_THINGS_MAKE_UP_BIG_THINGS_SO_EVEN_VALUE_TRIVIAL_MATTERS;

	/**
	 * ID: 1800623<br>
	 * Message: 条件が揃うのを待つより、作ってしまえ。
	 */
	public static final NpcStringId ACTION_TOWARD_THE_RESULT_RATHER_THAN_WAITING_FOR_THE_RIGHT_CIRCUMSTANCES_MAY_LEAD_YOU_TO_A_FAST_SUCCESS;

	/**
	 * ID: 1800624<br>
	 * Message: ちょっとした支出は周りの助けにつながる。惜しむな。
	 */
	public static final NpcStringId DONT_TRY_TO_SAVE_SMALL_EXPENDITURES_IT_WILL_LEAD_TO_FUTURE_RETURNS;

	/**
	 * ID: 1800625<br>
	 * Message: 誘惑に惑わされやすい。感情を抑えよ。
	 */
	public static final NpcStringId BE_CAUTIOUS_TO_CONTROL_EMOTIONS_AS_TEMPTATIONS_ARE_NEARBY;

	/**
	 * ID: 1800626<br>
	 * Message: 小さなことだと疎かにして、後で泣くことになる。
	 */
	public static final NpcStringId BE_WARNED_AS_NEGLECTING_A_MATTER_BECAUSE_ITS_SMALL_CAN_CAUSE_YOU_TROUBLE;

	/**
	 * ID: 1800627<br>
	 * Message: 節約も程度問題。使うべきときは使え。
	 */
	public static final NpcStringId SPEND_WHEN_NEEDED_RATHER_THAN_TRYING_TO_UNCONDITIONALLY_SAVE;

	/**
	 * ID: 1800628<br>
	 * Message: 偏見にとらわれれば、小を得ても大を失う。
	 */
	public static final NpcStringId PREJUDICE_WILL_TAKE_YOU_TO_A_SMALL_GAIN_WITH_A_BIG_LOSS;

	/**
	 * ID: 1800629<br>
	 * Message: ラッキーアイテムはスイーツ。
	 */
	public static final NpcStringId SWEET_FOOD_MAY_BRING_GOOD_LUCK;

	/**
	 * ID: 1800630<br>
	 * Message: 借金は返してもらえる。損失は補填してもらえる。
	 */
	public static final NpcStringId YOU_MAY_BE_PAID_FOR_WHAT_YOURE_OWED_OR_FOR_YOUR_PAST_LOSS;

	/**
	 * ID: 1800631<br>
	 * Message: 基本の問題で摩擦が起こる。
	 */
	public static final NpcStringId THERE_MAY_BE_CONFLICT_IN_BASIC_MATTERS;

	/**
	 * ID: 1800632<br>
	 * Message: 近しい人の小さな行動に気を使え。ただし、おせっかいは禁物。
	 */
	public static final NpcStringId BE_OBSERVANT_TO_CLOSE_FRIENDS_SMALL_BEHAVIORS_WHILE_REFRAINING_FROM_EXCESSIVE_KINDNESS;

	/**
	 * ID: 1800633<br>
	 * Message: ストレスは表に出さず、笑顔を忘れるな。
	 */
	public static final NpcStringId DO_NOT_SHOW_YOUR_DISTRESS_NOR_LOSE_YOUR_SMILE;

	/**
	 * ID: 1800634<br>
	 * Message: 変化を見せれば助けを得る。
	 */
	public static final NpcStringId SHOWING_CHANGE_MAY_BE_OF_HELP;

	/**
	 * ID: 1800635<br>
	 * Message: 時間管理を徹底せよ。望みの結果が得られる。
	 */
	public static final NpcStringId THE_INTENDED_RESULT_MAY_BE_ON_YOUR_WAY_IF_THE_TIME_IS_PERFECTLY_MANAGED;

	/**
	 * ID: 1800636<br>
	 * Message: 融通が利かないなら、つらいことが起きる。
	 */
	public static final NpcStringId HARDSHIPS_MAY_ARISE_IF_FLEXIBILITY_IS_NOT_WELL_PLAYED;

	/**
	 * ID: 1800637<br>
	 * Message: 油断一秒怪我一生。いつも冷静に。
	 */
	public static final NpcStringId KEEP_COOL_HEADED_BECAUSE_CARELESSNESS_OR_INATTENTIVENESS_MAY_CAUSE_MISFORTUNE;

	/**
	 * ID: 1800638<br>
	 * Message: 悪夢は怪我の兆し。
	 */
	public static final NpcStringId BE_CAUTIOUS_AS_YOU_MAY_GET_HURT_AFTER_LAST_NIGHTS_SINISTER_DREAM;

	/**
	 * ID: 1800639<br>
	 * Message: 金運あり。情に流されれば損をする。
	 */
	public static final NpcStringId A_STRONG_WEALTH_LUCK_IS_AHEAD_BUT_BE_CAREFUL_WITH_EMOTIONS_THAT_MAY_BRING_LOSSES;

	/**
	 * ID: 1800640<br>
	 * Message: 好きな人が関連することなら望み度通り進めてもよい。
	 */
	public static final NpcStringId PROCEED_AS_YOU_WISH_WHEN_ITS_PERTINENT_TO_THE_PERSON_YOU_LIKE;

	/**
	 * ID: 1800641<br>
	 * Message: 異性と話して心が通じる。
	 */
	public static final NpcStringId YOU_MAY_DEEPEN_THE_RELATIONSHIP_WITH_THE_OPPOSITE_SEX_THROUGH_CONVERSATION;

	/**
	 * ID: 1800642<br>
	 * Message: 物に残せるところに投資するのが吉。
	 */
	public static final NpcStringId INVESTMENT_INTO_SOLID_MATERIAL_MAY_BRING_PROFIT;

	/**
	 * ID: 1800643<br>
	 * Message: 楽しいところに投資すれば助けを得る。
	 */
	public static final NpcStringId INVESTMENT_INTO_WHAT_YOU_ENJOY_MAY_BE_OF_HELP;

	/**
	 * ID: 1800644<br>
	 * Message: 忙しくなる。きびきびと行動せよ。
	 */
	public static final NpcStringId BEING_BUSY_MAY_HELP_CATCHING_UP_WITH_MANY_CHANGES;

	/**
	 * ID: 1800645<br>
	 * Message: 一瞬の油断で陰口をたたかれる破目に。
	 */
	public static final NpcStringId CHOOSE_SUBSTANCE_OVER_HONOR;

	/**
	 * ID: 1800646<br>
	 * Message: 金銭の取引は凶。いいことをしても悪く言われる。
	 */
	public static final NpcStringId REMEMBER_TO_DECLINE_ANY_FINANCIAL_DEALINGS_BECAUSE_A_GOOD_DEED_MAY_RETURN_AS_RESENTMENT;

	/**
	 * ID: 1800647<br>
	 * Message: 初対面では失敗のないように。
	 */
	public static final NpcStringId BE_CAREFUL_NOT_TO_MAKE_A_MISTAKE_WITH_A_NEW_PERSON;

	/**
	 * ID: 1800648<br>
	 * Message: なかなか解決しないことに時間をかけても解決しない。執着するな。
	 */
	public static final NpcStringId DO_NOT_BE_OBSESSIVE_OVER_A_DRAGGED_OUT_PROJECT_SINCE_IT_WONT_GET_ANY_BETTER_WITH_MORE_TIME;

	/**
	 * ID: 1800649<br>
	 * Message: 譲歩したり損することはするな。
	 */
	public static final NpcStringId DO_NOT_YIELD_WHATS_RIGHTFULLY_YOURS_OR_TOLERATE_LOSSES;

	/**
	 * ID: 1800650<br>
	 * Message: 良縁の兆しあり。異性に関心を持て。
	 */
	public static final NpcStringId THERES_LUCK_IN_RELATIONSHIPS_SO_BECOME_INTERESTED_IN_THE_OPPOSITE_SEX;

	/**
	 * ID: 1800651<br>
	 * Message: 自分のやり方を押し付けず協力すれば一挙両得だ。
	 */
	public static final NpcStringId SEEKING_OTHERS_HELP_RATHER_THAN_TRYING_BY_YOURSELF_MAY_RESULT_IN_TWO_BIRDS_WITH_ONE_STONE;

	/**
	 * ID: 1800652<br>
	 * Message: 相手を説得して利益を得よ。
	 */
	public static final NpcStringId PERSUADING_THE_OTHER_MAY_RESULT_IN_YOUR_GAIN;

	/**
	 * ID: 1800653<br>
	 * Message: 待てば海路の日和あり。
	 */
	public static final NpcStringId A_GOOD_OPPORTUNITY_MAY_COME_WHEN_KEEPING_PATIENCE_WITHOUT_EXCESSIVENESS;

	/**
	 * ID: 1800654<br>
	 * Message: 異性が幸運をもたらす。
	 */
	public static final NpcStringId THE_OPPOSITE_SEX_MAY_BRING_FORTUNE;

	/**
	 * ID: 1800655<br>
	 * Message: 他人の頼みを聞き入れれば幸運がやってくる。
	 */
	public static final NpcStringId DOING_FAVOR_FOR_OTHER_PEOPLE_MAY_BRING_FORTUNE_IN_THE_FUTURE;

	/**
	 * ID: 1800656<br>
	 * Message: 苦しくても笑顔を忘れるな。幸運はすぐそこにある。
	 */
	public static final NpcStringId LUCK_MAY_STAY_NEAR_IF_A_SMILE_IS_KEPT_DURING_DIFFICULT_TIMES;

	/**
	 * ID: 1800657<br>
	 * Message: 爪を隠していた鷹。爪を出すときが来た。
	 */
	public static final NpcStringId YOU_MAY_REVEAL_YOUR_TRUE_SELF_LIKE_IRON_IS_MOLTEN_INTO_AN_STRONG_SWORD;

	/**
	 * ID: 1800658<br>
	 * Message: 隠していた爪を出す。自分の価値が高まる。
	 */
	public static final NpcStringId YOUR_VALUE_WILL_SHINE_AS_YOUR_POTENTIAL_IS_FINALLY_REALIZED;

	/**
	 * ID: 1800659<br>
	 * Message: しんどい任務も根気よく臨んで解決に持ち込め。いい結果と共に隠された能力も発見できる。
	 */
	public static final NpcStringId TENACIOUS_EFFORTS_IN_SOLVING_A_DIFFICULT_MISSION_OR_HARDSHIP_MAY_BRING_GOOD_RESULTS_AS_WELL_AS_REALIZING_YOUR_HIDDEN_POTENTIAL;

	/**
	 * ID: 1800660<br>
	 * Message: 楽天的でユーモアを忘れないあなたの周りには笑顔が絶えない。
	 */
	public static final NpcStringId PEOPLE_WILL_APPRECIATE_YOUR_POSITIVITY_AND_JOYFUL_ENTERTAINING;

	/**
	 * ID: 1800661<br>
	 * Message: 知恵と技で仕事は順調。
	 */
	public static final NpcStringId THINGS_WILL_MOVE_SMOOTHLY_WITH_YOUR_FULL_WISDOM_AND_ABILITIES;

	/**
	 * ID: 1800662<br>
	 * Message: 正しい道へと導く賢者あらわる。
	 */
	public static final NpcStringId YOU_MAY_MEET_A_SAGE_WHO_CAN_HELP_YOU_FIND_THE_RIGHT_PATH;

	/**
	 * ID: 1800663<br>
	 * Message: 持ち合わせの直観力と洞察力が光を放つ。
	 */
	public static final NpcStringId KEEN_INSTINCT_AND_FORESIGHT_WILL_SHINE_THEIR_VALUES;

	/**
	 * ID: 1800664<br>
	 * Message: 周りに幸運をもたらす。
	 */
	public static final NpcStringId YOU_MAY_BRING_GOOD_LUCK_TO_THOSE_AROUND_YOU;

	/**
	 * ID: 1800665<br>
	 * Message: 感情を抑えて目標達成。
	 */
	public static final NpcStringId YOUR_GOAL_MAY_BE_REALIZED_WHEN_EMOTIONAL_DETAILS_ARE_WELL_DEFINED;

	/**
	 * ID: 1800666<br>
	 * Message: 貴人の導きで富を謳歌する。
	 */
	public static final NpcStringId YOU_MAY_ENJOY_AFFLUENCE_AFTER_MEETING_A_PRECIOUS_PERSON;

	/**
	 * ID: 1800667<br>
	 * Message: 物質的な魅力を持った異性に出会う。
	 */
	public static final NpcStringId YOU_MAY_MEET_THE_OPPOSITE_SEX_WHO_HAS_MATERIALISTIC_ATTRACTIONS;

	/**
	 * ID: 1800668<br>
	 * Message: 競争は全力で戦え。
	 */
	public static final NpcStringId A_BIG_SUCCESS_WILL_FOLLOW_ALL_POSSIBLE_EFFORTS_IN_COMPETITION;

	/**
	 * ID: 1800669<br>
	 * Message: 過去の行いの結果が出る。
	 */
	public static final NpcStringId A_CONSEQUENCE_FROM_PAST_ACTIONS_WILL_BE_ON_DISPLAY;

	/**
	 * ID: 1800670<br>
	 * Message: 相手と立場が正反対に。
	 */
	public static final NpcStringId WHATEVER_HAPPENED_TO_YOU_AND_THE_OTHER_PERSON_WILL_REPLAY_BUT_THIS_TIME_THE_OPPOSITE_WILL_BE_THE_RESULT;

	/**
	 * ID: 1800671<br>
	 * Message: 小の虫を殺して大の虫を助ける。
	 */
	public static final NpcStringId YOU_MAY_NEED_TO_SACRIFICE_FOR_A_HIGHER_CAUSE;

	/**
	 * ID: 1800672<br>
	 * Message: 物を失い、精神を得る。
	 */
	public static final NpcStringId YOU_MAY_LOSE_AN_ITEM_BUT_WILL_GAIN_HONOR;

	/**
	 * ID: 1800673<br>
	 * Message: 変化への欲望あふれる。新たなことにチャレンジせよ。
	 */
	public static final NpcStringId A_NEW_TRIAL_OR_START_MAY_BE_SUCCESSFUL_AS_LUCK_SHADOWS_CHANGES;

	/**
	 * ID: 1800674<br>
	 * Message: 詐欺と誘惑がうごめく。本音は隠せ。
	 */
	public static final NpcStringId BE_SOPHISTICATED_WITHOUT_SHOWING_YOUR_TRUE_EMOTIONS_AS_TRICKS_AND_MATERIALISTIC_TEMPTATIONS_LIE_AHEAD;

	/**
	 * ID: 1800675<br>
	 * Message: 君子危うきに近寄らず。
	 */
	public static final NpcStringId DO_NOT_ATTEMPT_A_DANGEROUS_ADVENTURE;

	/**
	 * ID: 1800676<br>
	 * Message: 変化を恐れるな。危機はチャンス。
	 */
	public static final NpcStringId DO_NOT_BE_AFRAID_OF_CHANGE_A_RISK_WILL_BE_ANOTHER_OPPORTUNITY;

	/**
	 * ID: 1800677<br>
	 * Message: 自信と根気で行動せよ。不安定でもうまくやれる。
	 */
	public static final NpcStringId BE_CONFIDENT_AND_ACT_TENACIOUSLY_AT_ALL_TIMES_YOU_MAY_BE_ABLE_TO_ACCOMPLISH_TO_PERFECTION_DURING_SOMEWHAT_UNSTABLE_SITUATIONS;

	/**
	 * ID: 1800678<br>
	 * Message: 明るい未来が待っている。
	 */
	public static final NpcStringId YOU_MAY_EXPECT_A_BRIGHT_AND_HOPEFUL_FUTURE;

	/**
	 * ID: 1800679<br>
	 * Message: 休息はさらなる成長への近道。
	 */
	public static final NpcStringId A_REST_WILL_PROMISE_A_BIGGER_DEVELOPMENT;

	/**
	 * ID: 1800680<br>
	 * Message: ポジティブに考えて行動せよ。
	 */
	public static final NpcStringId FULLY_UTILIZE_POSITIVE_VIEWS;

	/**
	 * ID: 1800681<br>
	 * Message: ポジティブで活力あふれるあなたは舞台の中心に立つ。
	 */
	public static final NpcStringId POSITIVE_THINKING_AND_ENERGETIC_ACTIONS_WILL_TAKE_YOU_TO_THE_CENTER_OF_THE_GLORIOUS_STAGE;

	/**
	 * ID: 1800682<br>
	 * Message: 困難があっても自分の直感を信じて行動せよ。
	 */
	public static final NpcStringId YOUR_SELF_CONFIDENCE_AND_INTUITION_MAY_SOLVE_THE_DIFFICULTIES;

	/**
	 * ID: 1800683<br>
	 * Message: 何もかもが楽しい。笑顔で周りを明るくせよ。
	 */
	public static final NpcStringId EVERYTHING_IS_BRILLIANT_AND_JOYFUL_SHARE_IT_WITH_OTHERS_A_BIGGER_FORTUNE_WILL_FOLLOW;

	/**
	 * ID: 1800684<br>
	 * Message: 過去の行動に対する正当な評価と報賞が待っている。
	 */
	public static final NpcStringId A_FAIR_ASSESSMENT_AND_REWARD_FOR_PAST_ACTIONS_LIE_AHEAD;

	/**
	 * ID: 1800685<br>
	 * Message: ほったらかしの仕事や借金を片付けよ。新たな喜びの兆しあり。
	 */
	public static final NpcStringId PAY_ACCURATELY_THE_OLD_LIABILITY_OR_DEBT_IF_APPLICABLE_A_NEW_JOY_LIES_AHEAD;

	/**
	 * ID: 1800686<br>
	 * Message: 謙遜しすぎて損をする。
	 */
	public static final NpcStringId AN_EXCESSIVE_HUMILITY_CAN_HARM_YOU_BACK;

	/**
	 * ID: 1800687<br>
	 * Message: 今までの仕事の報賞を得る。
	 */
	public static final NpcStringId A_REWARD_FOR_THE_PAST_WORK_WILL_COME_THROUGH;

	/**
	 * ID: 1800688<br>
	 * Message: くたびれ儲けが本当の儲けになる。
	 */
	public static final NpcStringId YOUR_PAST_FRUITLESS_EFFORT_WILL_FINALLY_BE_REWARDED_WITH_SOMETHING_UNEXPECTED;

	/**
	 * ID: 1800689<br>
	 * Message: 再生の力強し。古き捨てて新しきを得よ。
	 */
	public static final NpcStringId THERES_STRONG_LUCK_IN_A_REVIVAL_ABANDON_THE_OLD_AND_CREATE_THE_NEW;

	/**
	 * ID: 1800690<br>
	 * Message: 周りから物心両面で助けられる。
	 */
	public static final NpcStringId YOU_MAY_GAIN_MATERIALISTIC_OR_MENTAL_AID_FROM_CLOSE_FRIENDS;

	/**
	 * ID: 1800691<br>
	 * Message: いい便りが待っている。
	 */
	public static final NpcStringId A_GOOD_BEGINNING_IS_AWAITING_YOU;

	/**
	 * ID: 1800692<br>
	 * Message: 会いたかった人に会える。
	 */
	public static final NpcStringId YOU_MAY_MEET_THE_PERSON_YOUVE_LONGED_TO_SEE;

	/**
	 * ID: 1800693<br>
	 * Message: 正直者は損をする。
	 */
	public static final NpcStringId YOU_MAY_SUSTAIN_A_LOSS_DUE_TO_YOUR_KINDNESS;

	/**
	 * ID: 1800694<br>
	 * Message: 貴人が手を差し伸べる。通りかかりの人をよく見よ。
	 */
	public static final NpcStringId CLOSELY_OBSERVE_PEOPLE_WHO_PASS_BY_SINCE_YOU_MAY_MEET_A_PRECIOUS_PERSON_WHO_CAN_HELP_YOU;

	/**
	 * ID: 1800695<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、破滅の種にいるティアトのドラコニアンと戦う勇気ある冒険家が集まりつつある。
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_WERE_GATHERING_BRAVE_ADVENTURERS_TO_ATTACK_TIATS_MOUNTED_TROOP_THATS_ROOTED_IN_THE_SEED_OF_DESTRUCTION;

	/**
	 * ID: 1800696<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、破滅の種は同盟連合の旗の下に安全に確保されている。
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_DESTRUCTION_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE;

	/**
	 * ID: 1800697<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、ティアトのドラコニアンが種の奪還を企んでいる。あるとあらゆるリソースを破滅の種に投入せよ。
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_TIATS_MOUNTED_TROOP_IS_CURRENTLY_TRYING_TO_RETAKE_SEED_OF_DESTRUCTION_COMMIT_ALL_THE_AVAILABLE_REINFORCEMENTS_INTO_SEED_OF_DESTRUCTION;

	/**
	 * ID: 1800698<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、不滅の種に挑んだ勇気ある冒険家は、守りの弱い苦痛の棺室を通じて侵食の棺室に侵入している！
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_BRAVE_ADVENTURERS_WHO_HAVE_CHALLENGED_THE_SEED_OF_INFINITY_ARE_CURRENTLY_INFILTRATING_THE_HALL_OF_EROSION_THROUGH_THE_DEFENSIVELY_WEAK_HALL_OF_SUFFERING;

	/**
	 * ID: 1800699<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、不滅の種は心臓部まで掃討が完了している。エキムスの直接攻撃する同時に、苦痛の棺室にいるアンデッドの残党の掃討を行っている！
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_SWEEPING_THE_SEED_OF_INFINITY_IS_CURRENTLY_COMPLETE_TO_THE_HEART_OF_THE_SEED_EKIMUS_IS_BEING_DIRECTLY_ATTACKED_AND_THE_UNDEAD_REMAINING_IN_THE_HALL_OF_SUFFERING_ARE_BEING_ERADICATED;
	
	/**
	 * ID: 1800700<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、不滅の種は同盟連合の旗の下に安全に確保されている。
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_SEED_OF_INFINITY_IS_CURRENTLY_SECURED_UNDER_THE_FLAG_OF_THE_KEUCEREUS_ALLIANCE;

	/**
	 * ID: 1800701<br>
	 * Message: 
	 */
	public static final NpcStringId _;
	
	/**
	 * ID: 1800702<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、生き返った不滅の種のアンデッドが苦痛の棺室と侵食の棺室にあふれ出ている。
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_PATRONS_OF_THE_KEUCEREUS_ALLIANCE_BASE_THE_RESURRECTED_UNDEAD_IN_THE_SEED_OF_INFINITY_ARE_POURING_INTO_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;

	/**
	 * ID: 1800703<br>
	 * Message: 伝令、クセルス同盟連合の同志に告げる！現在、生き返った不滅の種のアンデッドどもの手によってエキムスが生き返ろうとしている。全兵力を心臓部と苦痛の棺室に派遣せよ！
	 */
	public static final NpcStringId MESSENGER_INFORM_THE_BROTHERS_IN_KUCEREUS_CLAN_OUTPOST_EKIMUS_IS_ABOUT_TO_BE_REVIVED_BY_THE_RESURRECTED_UNDEAD_IN_SEED_OF_INFINITY_SEND_ALL_REINFORCEMENTS_TO_THE_HEART_AND_THE_HALL_OF_SUFFERING;

	/**
	 * ID: 1800704<br>
	 * Message: 3回刺せ！
	 */
	public static final NpcStringId STABBING_THREE_TIMES;

	/**
	 * ID: 1800705<br>
	 * Message: 愚かしい被造物どもよ。闇の力をお見舞いしてやる。
	 */
	public static final NpcStringId POOR_CREATURES_FEEL_THE_POWER_OF_DARKNESS;

	/**
	 * ID: 1800706<br>
	 * Message: うおおおおっ！
	 */
	public static final NpcStringId WHOAAAAAA;

	/**
	 * ID: 1800707<br>
	 * Message: 俺に挑んだことを後悔させてやる！
	 */
	public static final NpcStringId YOULL_REGRET_CHALLENGING_ME;

	/**
	 * ID: 1800708<br>
	 * Message: 現在、敵に占領されている。我が軍は攻撃を進めている。
	 */
	public static final NpcStringId ITS_CURRENTLY_OCCUPIED_BY_THE_ENEMY_AND_OUR_TROOPS_ARE_ATTACKING;

	/**
	 * ID: 1800709<br>
	 * Message: 現在、我々が占領している。クセルス同盟連合が敗残兵の掃討に当たっている。
	 */
	public static final NpcStringId ITS_UNDER_OCCUPATION_BY_OUR_FORCES_AND_I_HEARD_THAT_KUCEREUS_CLAN_IS_ORGANIZING_THE_REMNANTS;

	/**
	 * ID: 1800710<br>
	 * Message: 現在、我々が占領しているが、敵の強力な攻勢に押され気味だ。
	 */
	public static final NpcStringId ALTHOUGH_WE_CURRENTLY_HAVE_CONTROL_OF_IT_THE_ENEMY_IS_PUSHING_BACK_WITH_A_POWERFUL_ATTACK;

	/**
	 * ID: 1800711<br>
	 * Message: 現在、敵に占領されている。冒険家と連合側が敵の苦痛の棺室と侵食の棺室を激しく攻撃している。
	 */
	public static final NpcStringId ITS_UNDER_THE_ENEMYS_OCCUPATION_AND_THE_MILITARY_FORCES_OF_ADVENTURERS_AND_CLAN_MEMBERS_ARE_UNLEASHING_AN_ONSLAUGHT_UPON_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;

	/**
	 * ID: 1800713<br>
	 * Message: 現在、我々が占領している。内部の調査を行っている。
	 */
	public static final NpcStringId OUR_FORCES_HAVE_OCCUPIED_IT_AND_ARE_CURRENTLY_INVESTIGATING_THE_DEPTHS;

	/**
	 * ID: 1800714<br>
	 * Message: 現在、我々が占領しているが、敵が生き返って苦痛の棺室と侵食の棺室を通じて侵攻中だそうだ。
	 */
	public static final NpcStringId ITS_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_RESURRECTED_AND_IS_ATTACKING_TOWARD_THE_HALL_OF_SUFFERING_AND_THE_HALL_OF_EROSION;

	/**
	 * ID: 1800715<br>
	 * Message: 現在、我々が占領しているが、侵食の棺室はすでに敵の手に落ちた。さらに心臓部に押し寄せてわが軍の退路を絶とうとしている。エキムスの復活も時間の問題だ。
	 */
	public static final NpcStringId ITS_UNDER_OCCUPATION_BY_OUR_FORCES_BUT_THE_ENEMY_HAS_ALREADY_OVERTAKEN_THE_HALL_OF_EROSION_AND_IS_DRIVING_OUT_OUR_FORCES_FROM_THE_HALL_OF_SUFFERING_TOWARD_THE_HEART_IT_SEEMS_THAT_EKIMUS_WILL_REVIVE_SHORTLY;

	/**
	 * ID: 1800717<br>
	 * Message: ティアトの手下が破滅の種を取り返そうとしている。撃退の準備をせよ！
	 */
	public static final NpcStringId TIATS_FOLLOWERS_ARE_COMING_TO_RETAKE_THE_SEED_OF_DESTRUCTION_GET_READY_TO_STOP_THE_ENEMIES;

	/**
	 * ID: 1800718<br>
	 * Message: ああ、痛い．．．この痛み、どうやったら止むだろうか。
	 */
	public static final NpcStringId ITS_HURTING_IM_IN_PAIN_WHAT_CAN_I_DO_FOR_THE_PAIN;

	/**
	 * ID: 1800719<br>
	 * Message: やめてくれ．．．それすらなくなったら、どうやって苦痛と戦えってんだ！
	 */
	public static final NpcStringId NO_WHEN_I_LOSE_THAT_ONE_ILL_BE_IN_MORE_PAIN;

	/**
	 * ID: 1800720<br>
	 * Message: がははは！クリスマス サンタは俺が捕まえた！今年のプレゼントはお預けだ！
	 */
	public static final NpcStringId HAHAHAH_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR;

	/**
	 * ID: 1800721<br>
	 * Message: さあ、かかってこい！
	 */
	public static final NpcStringId NOW_WHY_DONT_YOU_TAKE_UP_THE_CHALLENGE;

	/**
	 * ID: 1800722<br>
	 * Message: やっつけてやる！
	 */
	public static final NpcStringId COME_ON_ILL_TAKE_ALL_OF_YOU_ON;

	/**
	 * ID: 1800723<br>
	 * Message: どうだ！俺の勝ちだな。
	 */
	public static final NpcStringId HOW_ABOUT_IT_I_THINK_I_WON;

	/**
	 * ID: 1800724<br>
	 * Message: 負け犬は消えうせやがれ！
	 */
	public static final NpcStringId NOW_THOSE_OF_YOU_WHO_LOST_GO_AWAY;

	/**
	 * ID: 1800725<br>
	 * Message: もっと腕の立つやつはいないのか！
	 */
	public static final NpcStringId WHAT_A_BUNCH_OF_LOSERS;

	/**
	 * ID: 1800726<br>
	 * Message: クリスマス サンタを救いに来たようだな。だが、相手が悪かったな。
	 */
	public static final NpcStringId I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_PERSON;

	/**
	 * ID: 1800727<br>
	 * Message: うっ、やるじゃねえか。
	 */
	public static final NpcStringId AH_OKAY;

	/**
	 * ID: 1800728<br>
	 * Message: これを出すんじゃなかった！
	 */
	public static final NpcStringId UAGH_I_WASNT_GOING_TO_DO_THAT;

	/**
	 * ID: 1800729<br>
	 * Message: 俺の呪いを受けろ！あ、あれっ？
	 */
	public static final NpcStringId YOURE_CURSED_OH_WHAT;

	/**
	 * ID: 1800730<br>
	 * Message: じゃんけんばっかやってんじゃねえよ！
	 */
	public static final NpcStringId HAVE_YOU_DONE_NOTHING_BUT_ROCK_PAPER_SCISSORS;

	/**
	 * ID: 1800731<br>
	 * Message: もうやめる．．．本当は寂しかったんだ．．．
	 */
	public static final NpcStringId STOP_IT_NO_MORE_I_DID_IT_BECAUSE_I_WAS_TOO_LONELY;

	/**
	 * ID: 1800732<br>
	 * Message: くそっ！クリスマス サンタを放してやらなきゃなんねえとはな！
	 */
	public static final NpcStringId I_HAVE_TO_RELEASE_SANTA_HOW_INFURIATING;

	/**
	 * ID: 1800733<br>
	 * Message: クリスマスなんか大っ嫌いだぁー！
	 */
	public static final NpcStringId I_HATE_HAPPY_MERRY_CHRISTMAS;

	/**
	 * ID: 1800734<br>
	 * Message: あ～かったりい～
	 */
	public static final NpcStringId OH_IM_BORED;

	/**
	 * ID: 1800735<br>
	 * Message: クリスマス サンタが大人しく捕まってるか見に行くか。ひひっ！
	 */
	public static final NpcStringId SHALL_I_GO_TO_TAKE_A_LOOK_IF_SANTA_IS_STILL_THERE_HEHE;

	/**
	 * ID: 1800736<br>
	 * Message: オッホッホ．．．メリークリスマス！
	 */
	public static final NpcStringId OH_HO_HO_MERRY_CHRISTMAS;

	/**
	 * ID: 1800737<br>
	 * Message: クリスマス サンタを助けて来てくれなきゃプレゼントはあげられないよ。
	 */
	public static final NpcStringId SANTA_COULD_GIVE_NICE_PRESENTS_ONLY_IF_HES_RELEASED_FROM_THE_TURKEY;

	/**
	 * ID: 1800738<br>
	 * Message: オッホッホ．．．．みなさん、ご苦労様。お礼は必ずいたします。
	 */
	public static final NpcStringId OH_HO_HO_OH_HO_HO_THANK_YOU_LADIES_AND_GENTLEMEN_I_WILL_REPAY_YOU_FOR_SURE;

	/**
	 * ID: 1800739<br>
	 * Message: メリークリスマス！ご苦労様。
	 */
	public static final NpcStringId UMERRY_CHRISTMAS_YOURE_DOING_A_GOOD_JOB;

	/**
	 * ID: 1800740<br>
	 * Message: メリークリスマス！キャプテン ターキーから救ってくれてありがとう。
	 */
	public static final NpcStringId MERRY_CHRISTMAS_THANK_YOU_FOR_RESCUING_ME_FROM_THAT_WRETCHED_TURKEY;

	/**
	 * ID: 1800741<br>
	 * Message: $s1、君のためにプレゼントを用意した。
	 */
	public static final NpcStringId S1_I_HAVE_PREPARED_A_GIFT_FOR_YOU;

	/**
	 * ID: 1800742<br>
	 * Message: $s1にプレゼントがある。
	 */
	public static final NpcStringId I_HAVE_A_GIFT_FOR_S1;

	/**
	 * ID: 1800743<br>
	 * Message: インベントリを見てごらん。私のプレゼント、気に入ってくれたらありがたいんだが。
	 */
	public static final NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_I_HOPE_YOU_LIKE_THE_GIFT_I_GAVE_YOU;

	/**
	 * ID: 1800744<br>
	 * Message: インベントリを見てごらん。ビッグなプレゼントが入ってるかもしれないぞ。
	 */
	public static final NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_MIGHT_BE_A_BIG_PRESENT;

	/**
	 * ID: 1800745<br>
	 * Message: 君の相手をするのはもううんざりだ。もう行く！
	 */
	public static final NpcStringId IM_TIRED_OF_DEALING_WITH_YOU_IM_LEAVING;

	/**
	 * ID: 1800746<br>
	 * Message: いつまでやるつもりだ！そろそろ飽きてきたぞ。
	 */
	public static final NpcStringId WHEN_ARE_YOU_GOING_TO_STOP_I_SLOWLY_STARTED_TO_BE_TIRED_OF_IT;

	/**
	 * ID: 1800747<br>
	 * Message: クリスマス サンタの伝言：わしを救い出してくれた$s1に祝福がありますように．．．
	 */
	public static final NpcStringId MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S1_WHO_SAVED_ME;

	/**
	 * ID: 1800748<br>
	 * Message: 一度死んでいる私だ。二度死なせることはできまい。
	 */
	public static final NpcStringId I_AM_ALREADY_DEAD_YOU_CANNOT_KILL_ME_AGAIN;

	/**
	 * ID: 1800749<br>
	 * Message: すべての暗黒竜の眷属よ！総員戦闘体制につけ！
	 */
	public static final NpcStringId OH_FOLLOWERS_OF_THE_DRAGON_OF_DARKNESS_FIGHT_BY_MY_SIDE;

	/**
	 * ID: 1800750<br>
	 * Message: ドラコニアン来襲！戦闘態勢についてください。
	 */
	public static final NpcStringId THE_DRAGON_RACE_ARE_INVADING_PREPARE_FOR_BATTLE;

	/**
	 * ID: 1800751<br>
	 * Message: $s1様は$s2領地のサンタクロースをキャプテンターキーから救い出しました。
	 */
	public static final NpcStringId S1_RESCUED_SANTA_CLAUS_OF_S2_TERRITORY_FROM_THE_TURKEY;

	/**
	 * ID: 1800752<br>
	 * Message: サンタ救出成功！
	 */
	public static final NpcStringId SANTA_RESCUE_SUCCESS;

	/**
	 * ID: 1800753<br>
	 * Message: $s1様は武器交換券で+$s2 $s3をお受け取りになりました。
	 */
	public static final NpcStringId S1_RECEIVED_S2_S3_THROUGH_THE_WEAPON_EXCHANGE_COUPON;

	/**
	 * ID: 1800754<br>
	 * Message: 口からでまかせを言うな！
	 */
	public static final NpcStringId DONT_GO_PRATTLING_ON;

	/**
	 * ID: 1800755<br>
	 * Message: プライドもないやつめ！相手する価値もない！
	 */
	public static final NpcStringId YOU_LOWLIFES_WITH_NOT_EVEN_AN_OUNCE_OF_PRIDE_YOURE_NOT_WORTHY_OF_OPPOSING_ME;

	/**
	 * ID: 1800756<br>
	 * Message: あ、間違えた。ブヒブヒ！俺はブタだ！ブヒブヒ！
	 */
	public static final NpcStringId ROAR_NO_OINK_OINK_SEE_IM_A_PIG_OINK_OINK;

	/**
	 * ID: 1800757<br>
	 * Message: ここはどこ？私は誰？ブヒブヒ！
	 */
	public static final NpcStringId WHO_AM_I_WHERE_AM_I_OINK_OINK;

	/**
	 * ID: 1800758<br>
	 * Message: 友達と一緒に遊びに来ただけだよ。ブヒブヒ！
	 */
	public static final NpcStringId I_JUST_FOLLOWED_MY_FRIEND_HERE_FOR_FUN_OINK_OINK;

	/**
	 * ID: 1800759<br>
	 * Message: プハァ～五臓六腑に染み渡る！
	 */
	public static final NpcStringId WOW_THATS_WHAT_I_CALL_A_CURE_ALL;

	/**
	 * ID: 1800760<br>
	 * Message: 食欲がどんどんでてきたぞ。草でも食いに行くべ。
	 */
	public static final NpcStringId IM_STARVING_SHOULD_I_GO_CHEW_SOME_GRASS;

	/**
	 * ID: 1800761<br>
	 * Message: ありがとよ！
	 */
	public static final NpcStringId THANK_YOU_THANK_YOU;

	/**
	 * ID: 1800762<br>
	 * Message: か～っ！元気が出てきたぞ！
	 */
	public static final NpcStringId WHATS_THIS_FEELING_OH_OH_FEELS_LIKE_MY_ENERGY_IS_BACK;

	/**
	 * ID: 1800763<br>
	 * Message: そう、これこれ！カラダが軽くなるこの感じ！
	 */
	public static final NpcStringId MY_BODYS_GETTING_LIGHTER_THIS_FEELING_FEELS_FAMILIAR_SOMEHOW;

	/**
	 * ID: 1800764<br>
	 * Message: わぁ～疲れが溶けていくようだ。
	 */
	public static final NpcStringId WOW_MY_FATIGUE_IS_COMPLETELY_GONE;

	/**
	 * ID: 1800765<br>
	 * Message: 不吉な気が消えてきてる！
	 */
	public static final NpcStringId HEY_THE_OMINOUS_ENERGY_IS_DISAPPEARED;

	/**
	 * ID: 1800766<br>
	 * Message: カラダが綿のように軽くなったぞ。
	 */
	public static final NpcStringId MY_BODY_FEELS_AS_LIGHT_AS_A_FEATHER;

	/**
	 * ID: 1800767<br>
	 * Message: 何、これ？食い物か。
	 */
	public static final NpcStringId WHATS_THIS_FOOD;

	/**
	 * ID: 1800768<br>
	 * Message: 力がみなぎる！もう元気回復剤なんて要らねえぜ！
	 */
	public static final NpcStringId MY_ENERGY_IS_OVERFLOWING_I_DONT_NEED_ANY_FATIGUE_RECOVERY_POTION;

	/**
	 * ID: 1800769<br>
	 * Message: 何やってんだ！？素人かよっ！
	 */
	public static final NpcStringId WHATS_THE_MATTER_THATS_AN_AMATEUR_MOVE;

	/**
	 * ID: 1800770<br>
	 * Message: ダブルチャンス！10秒以内に治療すれば報賞が2倍！
	 */
	public static final NpcStringId FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_10_SECONDS;

	/**
	 * ID: 1800771<br>
	 * Message: ダブルチャンス！40秒以内に治療すれば報賞が2倍！
	 */
	public static final NpcStringId FORTUNE_TIMER_REWARD_INCREASES_2_TIMES_IF_COMPLETED_WITHIN_40_SECONDS;

	/**
	 * ID: 1800772<br>
	 * Message: 残り40秒です。
	 */
	public static final NpcStringId N40_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800773<br>
	 * Message: 残り39秒です。
	 */
	public static final NpcStringId N39_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800774<br>
	 * Message: 残り38秒です。
	 */
	public static final NpcStringId N38_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800775<br>
	 * Message: 残り37秒です。
	 */
	public static final NpcStringId N37_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800776<br>
	 * Message: 残り36秒です。
	 */
	public static final NpcStringId N36_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800777<br>
	 * Message: 残り35秒です。
	 */
	public static final NpcStringId N35_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800778<br>
	 * Message: 残り34秒です。
	 */
	public static final NpcStringId N34_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800779<br>
	 * Message: 残り33秒です。
	 */
	public static final NpcStringId N33_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800780<br>
	 * Message: 残り32秒です。
	 */
	public static final NpcStringId N32_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800781<br>
	 * Message: 残り31秒です。
	 */
	public static final NpcStringId N31_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800782<br>
	 * Message: 残り30秒です。
	 */
	public static final NpcStringId N30_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800783<br>
	 * Message: 残り29秒です。
	 */
	public static final NpcStringId N29_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800784<br>
	 * Message: 残り28秒です。
	 */
	public static final NpcStringId N28_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800785<br>
	 * Message: 残り27秒です。
	 */
	public static final NpcStringId N27_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800786<br>
	 * Message: 残り26秒です。
	 */
	public static final NpcStringId N26_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800787<br>
	 * Message: 残り25秒です。
	 */
	public static final NpcStringId N25_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800788<br>
	 * Message: 残り24秒です。
	 */
	public static final NpcStringId N24_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800789<br>
	 * Message: 残り23秒です。
	 */
	public static final NpcStringId N23_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800790<br>
	 * Message: 残り22秒です。
	 */
	public static final NpcStringId N22_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800791<br>
	 * Message: 残り21秒です。
	 */
	public static final NpcStringId N21_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800792<br>
	 * Message: 残り20秒です。
	 */
	public static final NpcStringId N20_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800793<br>
	 * Message: 残り19秒です。
	 */
	public static final NpcStringId N19_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800794<br>
	 * Message: 残り18秒です。
	 */
	public static final NpcStringId N18_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800795<br>
	 * Message: 残り17秒です。
	 */
	public static final NpcStringId N17_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800796<br>
	 * Message: 残り16秒です。
	 */
	public static final NpcStringId N16_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800797<br>
	 * Message: 残り15秒です。
	 */
	public static final NpcStringId N15_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800798<br>
	 * Message: 残り14秒です。
	 */
	public static final NpcStringId N14_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800799<br>
	 * Message: 残り13秒です。
	 */
	public static final NpcStringId N13_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800800<br>
	 * Message: 残り12秒です。
	 */
	public static final NpcStringId N12_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800801<br>
	 * Message: 残り11秒です。
	 */
	public static final NpcStringId N11_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800802<br>
	 * Message: 残り10秒です。
	 */
	public static final NpcStringId N10_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800803<br>
	 * Message: 残り9秒です。
	 */
	public static final NpcStringId N9_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800804<br>
	 * Message: 残り8秒です。
	 */
	public static final NpcStringId N8_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800805<br>
	 * Message: 残り7秒です。
	 */
	public static final NpcStringId N7_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800806<br>
	 * Message: 残り6秒です。
	 */
	public static final NpcStringId N6_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800807<br>
	 * Message: 残り5秒です。
	 */
	public static final NpcStringId N5_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800808<br>
	 * Message: 残り4秒です。
	 */
	public static final NpcStringId N4_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800809<br>
	 * Message: 残り3秒です。
	 */
	public static final NpcStringId N3_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800810<br>
	 * Message: 残り2秒です。
	 */
	public static final NpcStringId N2_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800811<br>
	 * Message: 残り1秒です。
	 */
	public static final NpcStringId N1_SECONDS_ARE_REMAINING;

	/**
	 * ID: 1800812<br>
	 * Message: 終了！
	 */
	public static final NpcStringId TIME_UP;

	/**
	 * ID: 1800813<br>
	 * Message: 治療に失敗しました．．．
	 */
	public static final NpcStringId MISSION_FAILED;

	/**
	 * ID: 1800814<br>
	 * Message: 治療に成功しました！
	 */
	public static final NpcStringId MISSION_SUCCESS;

	/**
	 * ID: 1800815<br>
	 * Message: なあ、俺には家庭があるんだ。
	 */
	public static final NpcStringId HEY_I_ALREADY_HAVE_AN_OWNER;

	/**
	 * ID: 1800816<br>
	 * Message: おい！俺を焼いて食おうってのか！？キューピッドの元気回復剤を使ってくれよ。
	 */
	public static final NpcStringId HEY_ARE_YOU_PLANNING_ON_EATING_ME_USE_A_CUPIDS_FATIGUE_RECOVERY_POTION_ALREADY;

	/**
	 * ID: 1800817<br>
	 * Message: 生兵法は怪我の元だぜ。素人のマッサージは遠慮して、キューピッドの元気回復剤にしとくわ。
	 */
	public static final NpcStringId ILL_PASS_ON_AN_AMATEURS_MERIDIAN_MASSAGE_USE_A_CUPIDS_FATIGUE_RECOVERY_POTION_ALREADY;

	/**
	 * ID: 1800818<br>
	 * Message: こんなに早く疲れが取れるなんてな。$s1、助かったぜ。
	 */
	public static final NpcStringId I_ALREADY_FEEL_MORE_ENERGETIC_THANKS_S1;

	/**
	 * ID: 1800819<br>
	 * Message: スッキリしたぁ！あんたはマッサージの達人だぜ、$s1先生。
	 */
	public static final NpcStringId HOW_REFRESHING_YOU_WOULDNT_HAPPEN_TO_BE_A_MASTER_MASSEUSE_S1_WOULD_YOU;

	/**
	 * ID: 1800820<br>
	 * Message: すげえな！$s1、あんたはマッサージの歴史を塗り替えた！
	 */
	public static final NpcStringId INCREDIBLE_FROM_NOW_ON_ILL_COMPARE_ALL_MASSAGES_TO_THIS_ONE_WITH_S1;

	/**
	 * ID: 1800821<br>
	 * Message: ひとりでやるのはしんどいだろ？次からは仲間とパーティーを組んでからやりな。
	 */
	public static final NpcStringId ISNT_IT_TOUGH_DOING_IT_ALL_ON_YOUR_OWN_NEXT_TIME_TRY_MAKING_A_PARTY_WITH_SOME_COMRADES;

	/**
	 * ID: 1800822<br>
	 * Message: キタキタキター！巨大化しちゃうぞ～！
	 */
	public static final NpcStringId SORRY_BUT_ILL_LEAVE_MY_FRIEND_IN_YOUR_CARE_AS_WELL_THANKS;

	/**
	 * ID: 1800823<br>
	 * Message: クンクン。香ばしいバゲットの香り！
	 */
	public static final NpcStringId SNIFF_SNIFF_DO_YOU_SMELL_THE_SCENT_OF_A_FRESH_BAKED_BAGUETTE;

	/**
	 * ID: 1800824<br>
	 * Message: わしは誰かって？パン職人になりたかったら連絡しな。
	 */
	public static final NpcStringId WHO_AM_I_LET_ME_KNOW_IF_YOU_WANNA_BUY_MY_BREAD;

	/**
	 * ID: 1800825<br>
	 * Message: 俺はみんなの武器を強力にしたいだけなんだ。アブラカダブラ～やぁっ！
	 */
	public static final NpcStringId I_JUST_WANT_TO_MAKE_YOUR_WEAPONS_STRONGER_ABRA_KADABRA;

	/**
	 * ID: 1800826<br>
	 * Message: え？気に入らないって？素人でもあるまいし。
	 */
	public static final NpcStringId WHAT_YOU_DONT_LIKE_IT_WHATS_THE_MATTER_WITH_YOU_LIKE_AN_AMATEUR;

	/**
	 * ID: 1800827<br>
	 * Message: おい、エープリルフールにうそついたこと、ある？ないって？話にならないな。
	 */
	public static final NpcStringId HEY_DID_YOU_TELL_A_LIE_ON_APRIL_FOOLS_DAY_DONT_TALK_TO_ME_IF_YOU_DIDNT;

	/**
	 * ID: 1800828<br>
	 * Message: クンクン．．．なんで俺がこんなことやんなきゃなんないんだよ！
	 */
	public static final NpcStringId GRUNT_WHATS_WRONG_WITH_ME;

	/**
	 * ID: 1800829<br>
	 * Message: クンクン．．．う～んっ
	 */
	public static final NpcStringId GRUNT_OH;

	/**
	 * ID: 1800830<br>
	 * Message: シーフ ファイターが暗闇からあなたを攻撃します！
	 */
	public static final NpcStringId THE_GRAVE_ROBBER_WARRIOR_HAS_BEEN_FILLED_WITH_DARK_ENERGY_AND_IS_ATTACKING_YOU;

	/**
	 * ID: 1800831<br>
	 * Message: 祭壇の守護者があなたを見つめています！\n悪霊に挑んだ者は死をもって償うのです。
	 */
	public static final NpcStringId THE_ALTAR_GUARDIAN_IS_SCRUTINIZING_YOU_NTHOSE_WHO_DARE_TO_CHALLENGE_USING_THE_POWER_OF_EVIL_SHALL_BE_PUNISHED_WITH_DEATH;

	/**
	 * ID: 1800832<br>
	 * Message: ま、待ってくれ。もうやめにしよう。助けてくれたら1000万アデナやる。
	 */
	public static final NpcStringId WAIT_WAIT_STOP_SAVE_ME_AND_ILL_GIVE_YOU_10000000_ADENA;

	/**
	 * ID: 1800833<br>
	 * Message: 俺．．．戦うのいやなんだけどなぁ．．．
	 */
	public static final NpcStringId I_DONT_WANT_TO_FIGHT;

	/**
	 * ID: 1800834<br>
	 * Message: どうしてやんなきゃなんないの？
	 */
	public static final NpcStringId IS_THIS_REALLY_NECESSARY;

	/**
	 * ID: 1800835<br>
	 * Message: ありがとよ．．．おまえみたいなやつなら、いい友達になれたかもな．．．
	 */
	public static final NpcStringId TH_THANKS_I_COULD_HAVE_BECOME_GOOD_FRIENDS_WITH_YOU;

	/**
	 * ID: 1800836<br>
	 * Message: 約束どおり1000万アデナやろう。俺は意外と約束をよく守るオークかもしれねえだろ？
	 */
	public static final NpcStringId ILL_GIVE_YOU_10000000_ADENA_LIKE_I_PROMISED_I_MIGHT_BE_AN_ORC_WHO_KEEPS_MY_PROMISES;

	/**
	 * ID: 1800837<br>
	 * Message: ありがとよ！でも、1000万アデナの話はうそだよ～ん。あばよ！
	 */
	public static final NpcStringId THANKS_BUT_THAT_THING_ABOUT_10000000_ADENA_WAS_A_LIE_SEE_YA;

	/**
	 * ID: 1800838<br>
	 * Message: 俺の話を信じるとは、おまえも愚かしいな。
	 */
	public static final NpcStringId YOURE_PRETTY_DUMB_TO_BELIEVE_ME;

	/**
	 * ID: 1800839<br>
	 * Message: うっう．．．お前にのろいをかけてやる！
	 */
	public static final NpcStringId UGH_A_CURSE_UPON_YOU;

	/**
	 * ID: 1800840<br>
	 * Message: 俺．．．ほんとに戦うのいやだったのに．．．
	 */
	public static final NpcStringId I_REALLY_DIDNT_WANT_TO_FIGHT;

	/**
	 * ID: 1800841<br>
	 * Message: カーシャの目があなたを凝視します。
	 */
	public static final NpcStringId KASHAS_EYE_IS_SCRUTINIZING_YOU;

	/**
	 * ID: 1800842<br>
	 * Message: カーシャの目から尋常でない気が感じられます。
	 */
	public static final NpcStringId THE_KASHAS_EYE_GIVES_YOU_A_STRANGE_FEELING;

	/**
	 * ID: 1800843<br>
	 * Message: カーシャの目の魔気が急速に強くなりつつあるのが感じられます。
	 */
	public static final NpcStringId THE_EVIL_AURA_OF_THE_KASHAS_EYE_SEEMS_TO_BE_INCREASING_QUICKLY;

	/**
	 * ID: 1800844<br>
	 * Message: わしは祭壇を守っている。祭壇のそばから離れるわけにはいかん！
	 */
	public static final NpcStringId I_PROTECT_THE_ALTAR_YOU_CANT_ESCAPE_THE_ALTAR;

	/**
	 * ID: 1800845<br>
	 * Message: $s1!あやつらを片付けねばならぬ。できる限り力を貸そう。
	 */
	public static final NpcStringId S1_THAT_STRANGER_MUST_BE_DEFEATED_HERE_IS_THE_ULTIMATE_HELP;

	/**
	 * ID: 1800846<br>
	 * Message: おい！$s1、そんなに離れるな。
	 */
	public static final NpcStringId LOOK_HERE_S1_DONT_FALL_TOO_FAR_BEHIND;

	/**
	 * ID: 1800847<br>
	 * Message: よくやった。$s1、力になれて嬉しい。
	 */
	public static final NpcStringId WELL_DONE_S1_YOUR_HELP_IS_MUCH_APPRECIATED;

	/**
	 * ID: 1800848<br>
	 * Message: 我々の眠りを邪魔するのは誰だ！
	 */
	public static final NpcStringId WHO_HAS_AWAKENED_US_FROM_OUR_SLUMBER;

	/**
	 * ID: 1800849<br>
	 * Message: 皆の者！こやつに思い知らせてやれ。
	 */
	public static final NpcStringId ALL_WILL_PAY_A_SEVERE_PRICE_TO_ME_AND_THESE_HERE;

	/**
	 * ID: 1800850<br>
	 * Message: シャイドの雄たけびが回を増しつつあります。
	 */
	public static final NpcStringId SHYEEDS_CRY_IS_STEADILY_DYING_DOWN;

	/**
	 * ID: 1800851<br>
	 * Message: 警報：開発妨害勢力発見！不測の事態対応計画に基づいて自動目標設定！
	 */
	public static final NpcStringId ALERT_ALERT_DAMAGE_DETECTION_RECOGNIZED_COUNTERMEASURES_ENABLED;

	/**
	 * ID: 1800852<br>
	 * Message: 対．象．認．識．ター．ゲッ．ト．設．定．
	 */
	public static final NpcStringId TARGET_RECOGNITION_ACHIEVED_ATTACK_SEQUENCE_COMMENCING;

	/**
	 * ID: 1800853<br>
	 * Message: ター．ゲッ．ト．危．険．レ．ベ．ル．最．高．位．対．応．開．始．
	 */
	public static final NpcStringId TARGET_THREAT_LEVEL_LAUNCHING_STRONGEST_COUNTERMEASURE;

	/**
	 * ID: 1800854<br>
	 * Message: 魔法陣が脅かされている。守護霊たちよ、守りに着け！
	 */
	public static final NpcStringId THE_PURIFICATION_FIELD_IS_BEING_ATTACKED_GUARDIAN_SPIRITS_PROTECT_THE_MAGIC_FORCE;

	/**
	 * ID: 1800855<br>
	 * Message: インナドリルの．．．命のために．．．儀式を．．．終えねば．．．
	 */
	public static final NpcStringId PROTECT_THE_BRAZIERS_OF_PURITY_AT_ALL_COSTS;

	/**
	 * ID: 1800856<br>
	 * Message: 魔法陣を．．．死守せよ．．．この命と．．．引き換えてでも．．．
	 */
	public static final NpcStringId DEFEND_OUR_DOMAIN_EVEN_AT_RISK_OF_YOUR_OWN_LIFE;

	/**
	 * ID: 1800857<br>
	 * Message: プングルイ ムグルワナプ！
	 */
	public static final NpcStringId PEUNGLUI_MUGLANEP;

	/**
	 * ID: 1800858<br>
	 * Message: ナイア ワガナグル プタグン！
	 */
	public static final NpcStringId NAIA_WAGANAGEL_PEUTAGUN;

	/**
	 * ID: 1800859<br>
	 * Message: 駆．動．装．置．半．壊．推．進．力．急．速．低．下．
	 */
	public static final NpcStringId DRIVE_DEVICE_PARTIAL_DESTRUCTION_IMPULSE_RESULT;

	/**
	 * ID: 1800860<br>
	 * Message: 魔法陣に縛られた身とはいえ．．．おまえだけは許さねえ．．．
	 */
	public static final NpcStringId EVEN_THE_MAGIC_FORCE_BINDS_YOU_YOU_WILL_NEVER_BE_FORGIVEN;

	/**
	 * ID: 1800861<br>
	 * Message: 巨人たちよ、侵入者発見！
	 */
	public static final NpcStringId OH_GIANTS_AN_INTRUDER_HAS_BEEN_DISCOVERED;

	/**
	 * ID: 1800862<br>
	 * Message: むなしい．．．しかしこのままでは終われない！
	 */
	public static final NpcStringId ALL_IS_VANITY_BUT_THIS_CANNOT_BE_THE_END;

	/**
	 * ID: 1800863<br>
	 * Message: 私の前に立つ者はすべからく滅する！
	 */
	public static final NpcStringId THOSE_WHO_ARE_IN_FRONT_OF_MY_EYES_WILL_BE_DESTROYED;

	/**
	 * ID: 1800864<br>
	 * Message: 眠い！もう私を起こすな！
	 */
	public static final NpcStringId I_AM_TIRED_DO_NOT_WAKE_ME_UP_AGAIN;

	/**
	 * ID: 1800865<br>
	 * Message: 侵 入 者 発 見
	 */
	public static final NpcStringId _INTRUDER_DETECTED;

	/**
	 * ID: 1800866<br>
	 * Message: あのキャンドルがザケンの居場所を示すのだ。破壊せよ。
	 */
	public static final NpcStringId THE_CANDLES_CAN_LEAD_YOU_TO_ZAKEN_DESTROY_HIM;

	/**
	 * ID: 1800867<br>
	 * Message: ザケン様を眠りから覚ますのは誰だ！
	 */
	public static final NpcStringId WHO_DARES_AWKAWEN_THE_MIGHTY_ZAKEN;

	/**
	 * ID: 1800868<br>
	 * Message: 下の方で私を探しているな、愚か者どもめ。
	 */
	public static final NpcStringId YE_NOT_BE_FINDING_ME_BELOW_THE_DRINK;

	/**
	 * ID: 1800869<br>
	 * Message: 見当違いなところで私を探しているな、まぬけどもめ。
	 */
	public static final NpcStringId YE_MUST_BE_THREE_SHEETS_TO_THE_WIND_IF_YER_LOOKIN_FOR_ME_THERE;

	/**
	 * ID: 1800870<br>
	 * Message: 上の方で私を探しているな、ばか者どもめ。
	 */
	public static final NpcStringId YE_NOT_BE_FINDING_ME_IN_THE_CROWS_NEST;

	/**
	 * ID: 1800871<br>
	 * Message: 悪いがこれだけしかない．．．見逃してくれ！
	 */
	public static final NpcStringId SORRY_BUT_THIS_IS_ALL_I_HAVE_GIVE_ME_A_BREAK;

	/**
	 * ID: 1800872<br>
	 * Message: プングルイ ムグルワナプ ナイア ワガナグル プタグン！
	 */
	public static final NpcStringId PEUNGLUI_MUGLANEP_NAIA_WAGANAGEL_PEUTAGUN;

	/**
	 * ID: 1800873<br>
	 * Message: 駆．動．装．置．全．壊．移．動．停．止．
	 */
	public static final NpcStringId DRIVE_DEVICE_ENTIRE_DESTRUCTION_MOVING_SUSPENSION;

	/**
	 * ID: 1800874<br>
	 * Message: ああ．．．魔法陣からの脱出はもはや．．．
	 */
	public static final NpcStringId AH_AH_FROM_THE_MAGIC_FORCE_NO_MORE_I_WILL_BE_FREED;

	/**
	 * ID: 1800875<br>
	 * Message: もうばれてるぞ！
	 */
	public static final NpcStringId YOU_GUYS_ARE_DETECTED;

	/**
	 * ID: 1800876<br>
	 * Message: 何やつ！
	 */
	public static final NpcStringId WHAT_KIND_OF_CREATURES_ARE_YOU;

	/**
	 * ID: 1800877<br>
	 * Message: レベル$s1の$s2を獲得しました。
	 */
	public static final NpcStringId S2_OF_LEVEL_S1_IS_ACQUIRED;

	/**
	 * ID: 1800878<br>
	 * Message: 太古の生命石獲得
	 */
	public static final NpcStringId LIFE_STONE_FROM_THE_BEGINNING_ACQUIRED;

	/**
	 * ID: 1800879<br>
	 * Message: インベントリの重量、数量が80%以上の状態では太古の生命石は獲得できません。
	 */
	public static final NpcStringId WHEN_INVENTORY_WEIGHT_NUMBER_ARE_MORE_THAN_80_THE_LIFE_STONE_FROM_THE_BEGINNING_CANNOT_BE_ACQUIRED;

	/**
	 * ID: 1800880<br>
	 * Message: おまえらはもはや私の手中から逃れられない！
	 */
	public static final NpcStringId YOU_ARE_UNDER_MY_THUMB;

	/**
	 * ID: 1800881<br>
	 * Message: インスタント ゾーンの残り時間が20分延長されました。
	 */
	public static final NpcStringId N21_MINUTES_ARE_ADDED_TO_THE_REMAINING_TIME_IN_THE_INSTANT_ZONE;

	/**
	 * ID: 1800882<br>
	 * Message: 急げ！
	 */
	public static final NpcStringId HURRY_HURRY;

	/**
	 * ID: 1800883<br>
	 * Message: 俺は流れ者の気質なんだ。
	 */
	public static final NpcStringId I_AM_NOT_THAT_TYPE_OF_PERSON_WHO_STAYS_IN_ONE_PLACE_FOR_A_LONG_TIME;

	/**
	 * ID: 1800884<br>
	 * Message: こうやって立ってるのもつらい。
	 */
	public static final NpcStringId ITS_HARD_FOR_ME_TO_KEEP_STANDING_LIKE_THIS;

	/**
	 * ID: 1800885<br>
	 * Message: 今度はあっちの方に行ってみようか。
	 */
	public static final NpcStringId WHY_DONT_I_GO_THAT_WAY_THIS_TIME;

	/**
	 * ID: 1800886<br>
	 * Message: よく来たな。
	 */
	public static final NpcStringId WELCOME;

	/**
	 * ID: 1800887<br>
	 * Message: お前たちの実力はこんなものか。もっとがんばれ。
	 */
	public static final NpcStringId IS_THAT_IT_IS_THAT_THE_EXTENT_OF_YOUR_ABILITIES_PUT_IN_A_LITTLE_MORE_EFFORT;

	/**
	 * ID: 1800888<br>
	 * Message: お前の実力はこの程度とはな．．．相手にする価値もないわ！
	 */
	public static final NpcStringId YOUR_ABILITIES_ARE_PITIFUL_YOU_ARE_FAR_FROM_A_WORTHY_OPPONENT;

	/**
	 * ID: 1800889<br>
	 * Message: 死んでまで獲物を探す猟犬のように裏道をさまよわせるのか！
	 */
	public static final NpcStringId EVEN_AFTER_DEATH_YOU_ORDER_ME_TO_WANDER_AROUND_LOOKING_FOR_THE_SCAPEGOATS;

	/**
	 * ID: 1800890<br>
	 * Message: 熱中症を受けてみろ！灼熱の熱中症を3段階まで耐え抜くことができたら、ヒューミッド様がおまえの相手をなさるだろう。
	 */
	public static final NpcStringId HERE_GOES_THE_HEATSTROKE_IF_YOU_CAN_WITHSTAND_THE_HOT_HEATSTROKE_UP_TO_THE_3RD_STAGE_THE_SULTRINESS_WILL_COME_TO_YOU;

	/**
	 * ID: 1800891<br>
	 * Message: まあせいぜい期待しろよ。ヒューミッド様は冷気砲攻撃を10回以上耐え抜く熱いお方だからな。
	 */
	public static final NpcStringId JUST_YOU_WAIT_HUMIDITY_IS_A_BLISTERING_FIREBALL_WHICH_CAN_EASILY_WITHSTAND_PLENTY_OF_COOL_AIR_CANNON_ATTACKS;

	/**
	 * ID: 1800892<br>
	 * Message: ヒューミッドを倒すには、ドクター アイスの熱中症予防効果を受けて、冷気砲攻撃を10回以上使ってください。
	 */
	public static final NpcStringId IN_ORDER_TO_DEFEAT_HUMIDITY_YOU_MUST_OBTAIN_THE_HEADSTROKE_PREVENTION_EFFECT_FROM_DOCTOR_ICE_AND_FIRE_MORE_THAN_10_ROUNDS_OF_THE_COOL_AIR_CANNON_ON_IT;

	/**
	 * ID: 1800893<br>
	 * Message: 飛んで火に入る夏の虫．．．$s1かかってこい！
	 */
	public static final NpcStringId YOU_ARE_HERE_S1_ILL_TEACH_YOU_A_LESSON_BRING_IT_ON;

	/**
	 * ID: 1800894<br>
	 * Message: つめてぇ！冷気砲だな！俺は冷たいのが苦手なんだ！
	 */
	public static final NpcStringId THATS_COLD_ISNT_IT_ONE_OF_THOSE_COOL_PACKS_I_HATE_ANYTHING_THATS_COLD;

	/**
	 * ID: 1800895<br>
	 * Message: ふう、はずれだよ～ん！お前の腕はそんなもんか。
	 */
	public static final NpcStringId HUH_YOUVE_MISSED_IS_THAT_ALL_YOU_HAVE;

	/**
	 * ID: 1800896<br>
	 * Message: 俺の盗んだ大切な品をやるから．．．もういじめないでぇ～
	 */
	public static final NpcStringId I_WILL_GIVE_YOU_PRECIOUS_THINGS_THAT_I_HAVE_STOLEN_SO_STOP_BOTHERING_ME;

	/**
	 * ID: 1800897<br>
	 * Message: 大当たりアイテムをやろうとしたのに．．．インベントリーがいっぱいだな。じゃね～
	 */
	public static final NpcStringId I_WAS_GOING_TO_GIVE_YOU_A_JACKPOT_ITEM_YOU_DONT_HAVE_ENOUGH_INVENTORY_ROOM_SEE_YOU_NEXT_TIME;

	/**
	 * ID: 1800898<br>
	 * Message: $s1はヒューミッドを倒してS84アイテムを獲得しました。
	 */
	public static final NpcStringId S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S84;

	/**
	 * ID: 1800899<br>
	 * Message: $s1はヒューミッドを倒してS80アイテムを獲得しました。
	 */
	public static final NpcStringId S1_DEFEATED_THE_SULTRINESS_AND_ACQUIRED_ITEM_S80;

	/**
	 * ID: 1800900<br>
	 * Message: お前のへなちょこ冷気砲ごときが俺に通じるとでも思ってるのか！相手にする価値もない！
	 */
	public static final NpcStringId I_AM_NOT_HERE_FOR_YOU_YOUR_COOL_PACK_ATTACK_DOES_NOT_WORK_AGAINST_ME;

	/**
	 * ID: 1800901<br>
	 * Message: あれっ？どこに隠れたんだ？俺の相手がいないぞ。それじゃ、忙しいから俺はこれで．．．
	 */
	public static final NpcStringId UH_OH_WHERE_ARE_YOU_HIDING_THERE_IS_NOBODY_WHO_MATCHES_MY_SKILLS_WELL_I_GUESS_ID_BETTER_GET_GOING;

	/**
	 * ID: 1800902<br>
	 * Message: リアクションがないなあ。冷気砲もないくせに、俺に挑むのは千年早いわ！
	 */
	public static final NpcStringId WHY_ARE_YOU_NOT_RESPONDING_YOU_DONT_EVEN_HAVE_ANY_COOL_PACKS_YOU_CANT_FIGHT_ME;

	/**
	 * ID: 1800903<br>
	 * Message: ここはどこや！俺を呼んだのは誰や！
	 */
	public static final NpcStringId OH_WHERE_I_BE_WHO_CALL_ME;

	/**
	 * ID: 1800904<br>
	 * Message: じゃーん！俺、スイカやでっ！
	 */
	public static final NpcStringId TADA_ITS_A_WATERMELON;

	/**
	 * ID: 1800906<br>
	 * Message: スイカの登場やでっ！これからどんどん育つでっ！
	 */
	public static final NpcStringId ENTER_THE_WATERMELON_ITS_GONNA_GROW_AND_GROW_FROM_NOW_ON;

	/**
	 * ID: 1800907<br>
	 * Message: へえ、ひさしぶりやん！
	 */
	public static final NpcStringId OH_OUCH_DID_I_SEE_YOU_BEFORE;

	/**
	 * ID: 1800908<br>
	 * Message: 大きいスイカにな～れ！やっぱり夏はスイカやもんねぇ。
	 */
	public static final NpcStringId A_NEW_SEASON_SUMMER_IS_ALL_ABOUT_THE_WATERMELON;

	/**
	 * ID: 1800909<br>
	 * Message: 俺のこと、呼んだ？そろそろ何か出るって思ってるんとちゃうの？
	 */
	public static final NpcStringId DID_YA_CALL_HO_THOUGHT_YOUD_GET_SOMETHING;

	/**
	 * ID: 1800910<br>
	 * Message: 立派に育った俺の姿、見たいやろ？
	 */
	public static final NpcStringId DO_YOU_WANT_TO_SEE_MY_BEAUTIFUL_SELF;

	/**
	 * ID: 1800911<br>
	 * Message: へへへっ！一緒にやろう！
	 */
	public static final NpcStringId HOHOHO_LETS_DO_IT_TOGETHER;

	/**
	 * ID: 1800912<br>
	 * Message: うまいこと育てたらビッグスイカ、失敗したら出来損ないやでっ！
	 */
	public static final NpcStringId ITS_A_GIANT_WATERMELON_IF_YOU_RAISE_IT_RIGHT_AND_A_WATERMELON_SLICE_IF_YOU_MESS_UP;

	/**
	 * ID: 1800913<br>
	 * Message: じゃーん！変身したでっ！
	 */
	public static final NpcStringId TADA_TRANSFORMATION_COMPLETE;

	/**
	 * ID: 1800914<br>
	 * Message: さーて、良質な大きなスイカやろかな？不良な大きなスイカやろかな？
	 */
	public static final NpcStringId AM_I_A_RAIN_WATERMELON_OR_A_DEFECTIVE_WATERMELON;

	/**
	 * ID: 1800915<br>
	 * Message: 大きくなってもうたがな～さあ、かかれっ！
	 */
	public static final NpcStringId NOW_IVE_GOTTEN_BIG_EVERYONE_COME_AT_ME;

	/**
	 * ID: 1800916<br>
	 * Message: 大きくなるぅ～強くなるぅ～願いを言うてみ！
	 */
	public static final NpcStringId GET_BIGGER_GET_STRONGER_TELL_ME_YOUR_WISH;

	/**
	 * ID: 1800917<br>
	 * Message: 出来損ないにな～れ！って、もう育ちまくりやんっ！
	 */
	public static final NpcStringId A_WATERMELON_SLICES_WISH_BUT_IM_BIGGER_ALREADY;

	/**
	 * ID: 1800918<br>
	 * Message: 超でかいスイカにな～れ！はよ起こしてよ！
	 */
	public static final NpcStringId A_LARGE_WATERMELONS_WISH_WELL_TRY_TO_BREAK_ME;

	/**
	 * ID: 1800919<br>
	 * Message: 育ってもうたがな～ほな、逃げるでっ！
	 */
	public static final NpcStringId IM_DONE_GROWING_IM_RUNNING_AWAY_NOW;

	/**
	 * ID: 1800920<br>
	 * Message: 1000万アデナやるから、俺を見逃してくれっ！
	 */
	public static final NpcStringId IF_YOU_LET_ME_GO_ILL_GIVE_YOU_TEN_MILLION_ADENA;

	/**
	 * ID: 1800921<br>
	 * Message: さ～て、俺の中には何が詰まってるかなっ？
	 */
	public static final NpcStringId FREEDOM_WHAT_DO_YOU_THINK_I_HAVE_INSIDE;

	/**
	 * ID: 1800922<br>
	 * Message: よっしゃ、よっしゃ！ほんなら、何をしたらええのかわかってるやろ？
	 */
	public static final NpcStringId OK_OK_GOOD_JOB_YOU_KNOW_WHAT_TO_DO_NEXT_RIGHT;

	/**
	 * ID: 1800923<br>
	 * Message: ちゃんとせえよ！こぼしてるやん！ほんまにもったいない．．．
	 */
	public static final NpcStringId LOOK_HERE_DO_IT_RIGHT_YOU_SPILLED_THIS_PRECIOUS;

	/**
	 * ID: 1800924<br>
	 * Message: ああ、ええ気持ちやっ！もっとかけて！
	 */
	public static final NpcStringId AH_REFRESHING_SPRAY_A_LITTLE_MORE;

	/**
	 * ID: 1800925<br>
	 * Message: むっちゃ気持ちええやん！もっとないの？
	 */
	public static final NpcStringId GULP_GULP_GREAT_BUT_ISNT_THERE_MORE;

	/**
	 * ID: 1800926<br>
	 * Message: この下手くそ！はずしまくりやん！
	 */
	public static final NpcStringId CANT_YOU_EVEN_AIM_RIGHT_HAVE_YOU_EVEN_BEEN_TO_THE_ARMY;

	/**
	 * ID: 1800927<br>
	 * Message: げっ！何やねん、これ！賞味期限切れてるんちゃうか？
	 */
	public static final NpcStringId DID_YOU_MIX_THIS_WITH_WATER_WHYS_IT_TASTE_LIKE_THIS;

	/**
	 * ID: 1800928<br>
	 * Message: ええがな、ええがな。もっとがんがんかけてや！
	 */
	public static final NpcStringId OH_GOOD_DO_A_LITTLE_MORE_YEAH;

	/**
	 * ID: 1800929<br>
	 * Message: そこ、ちゃうっちゅ～ねん！ここ、ここ！この、ヘタクソ！
	 */
	public static final NpcStringId HOHO_ITS_NOT_THERE_OVER_HERE_AM_I_SO_SMALL_THAT_YOU_CAN_EVEN_SPRAY_ME_RIGHT;

	/**
	 * ID: 1800930<br>
	 * Message: げっ！さっきの何やねん！ネクターちゃうんとちゃうの？
	 */
	public static final NpcStringId YUCK_WHAT_IS_THIS_ARE_YOU_SURE_THIS_IS_NECTAR;

	/**
	 * ID: 1800931<br>
	 * Message: ちゃんとやってえな！うまいことかけへんかったら、育つもんも育たんでっ！
	 */
	public static final NpcStringId DO_YOUR_BEST_I_BECOME_A_BIG_WATERMELON_AFTER_JUST_FIVE_BOTTLES;

	/**
	 * ID: 1800932<br>
	 * Message: ほんまにネクターはスイカネクターが最高やなっ！はははっ！
	 */
	public static final NpcStringId OF_COURSE_WATERMELON_IS_THE_BEST_NECTAR_HAHAHA;

	/**
	 * ID: 1800934<br>
	 * Message: なんでどつくねん！暴力ハンタ～イ！もっとネクターくれよ！
	 */
	public static final NpcStringId OWW_YOURE_JUST_BEATING_ME_NOW_GIVE_ME_NECTAR;

	/**
	 * ID: 1800935<br>
	 * Message: 割れたらどないすんねんっ！
	 */
	public static final NpcStringId LOOK_ITS_GONNA_BREAK;

	/**
	 * ID: 1800936<br>
	 * Message: 育ってもおらんのに食うんかいっ！勝手にせえ！
	 */
	public static final NpcStringId NOW_ARE_YOU_TRYING_TO_EAT_WITHOUT_DOING_THE_WORK_FINE_DO_WHAT_YOU_WANT_ILL_HATE_YOU_IF_YOU_DONT_GIVE_ME_ANY_NECTAR;

	/**
	 * ID: 1800937<br>
	 * Message: どんどんどついてや～ネクターもほしいねん！
	 */
	public static final NpcStringId HIT_ME_MORE_HIT_ME_MORE;

	/**
	 * ID: 1800938<br>
	 * Message: しおれてもうたらどないすんねん！責任取ってや～
	 */
	public static final NpcStringId IM_GONNA_WITHER_LIKE_THIS_DAMN_IT;

	/**
	 * ID: 1800939<br>
	 * Message: しおれてもうたら、骨折り損のくたびれ儲けやでっ！そんなに俺が嫌いなんか？
	 */
	public static final NpcStringId HEY_YOU_IF_I_DIE_LIKE_THIS_THERELL_BE_NO_ITEM_EITHER_ARE_YOU_REALLY_SO_STINGY_WITH_THE_NECTAR;

	/**
	 * ID: 1800940<br>
	 * Message: おいおい、もっとがんばれよ～
	 */
	public static final NpcStringId ITS_JUST_A_LITTLE_MORE_GOOD_LUCK;

	/**
	 * ID: 1800941<br>
	 * Message: 俺はネクターも飲まんと死んでまうんや。恋を知らんと死ぬようなもんや！助けてくれっ！
	 */
	public static final NpcStringId SAVE_ME_IM_ABOUT_TO_DIE_WITHOUT_TASTING_NECTAR_EVEN_ONCE;

	/**
	 * ID: 1800942<br>
	 * Message: ああ、俺はもう死ぬんや．．．悲しい．．．
	 */
	public static final NpcStringId IF_I_DIE_LIKE_THIS_ILL_JUST_BE_A_WATERMELON_SLICE;

	/**
	 * ID: 1800943<br>
	 * Message: じらし過ぎやねん！もう飽きたわ！30秒以内にくれへんかったら、帰るでっ！
	 */
	public static final NpcStringId IM_GETTING_STRONGER_I_THINK_ILL_BE_ABLE_TO_RUN_AWAY_IN_30_SECONDS_HOHO;

	/**
	 * ID: 1800944<br>
	 * Message: もう20秒しかないでっ！
	 */
	public static final NpcStringId ITS_GOODBYE_AFTER_20_SECONDS;

	/**
	 * ID: 1800945<br>
	 * Message: あぁ、残り10秒！9．．．8．．．7．．．
	 */
	public static final NpcStringId YEAH_10_SECONDS_LEFT_9_8_7;

	/**
	 * ID: 1800946<br>
	 * Message: ネクターをくれへんかったら、2分後に帰るでっ！
	 */
	public static final NpcStringId IM_LEAVING_IN_2_MINUTES_IF_YOU_DONT_GIVE_ME_ANY_NECTAR;

	/**
	 * ID: 1800947<br>
	 * Message: ネクターをくれへんかったら、1分後に帰るでっ！
	 */
	public static final NpcStringId IM_LEAVING_IN_1_MINUTES_IF_YOU_DONT_GIVE_ME_ANY_NECTAR;

	/**
	 * ID: 1800948<br>
	 * Message: 俺、帰るわ。ほな、さいなら！
	 */
	public static final NpcStringId IM_LEAVING_NOW_THEN_GOODBYE;

	/**
	 * ID: 1800949<br>
	 * Message: 残念やな。ビックスイカとはこれでおさらばや。
	 */
	public static final NpcStringId SORRY_BUT_THIS_LARGE_WATERMELON_IS_DISAPPEARING_HERE;

	/**
	 * ID: 1800950<br>
	 * Message: 遅いねんっ！ほな、さいなら！
	 */
	public static final NpcStringId TOO_LATE_HAVE_A_GOOD_TIME;

	/**
	 * ID: 1800951<br>
	 * Message: 俺、もう行かなあかんねん。次を楽しみにしてるでっ！
	 */
	public static final NpcStringId DING_DING_THATS_THE_BELL_PUT_AWAY_YOUR_WEAPONS_AND_TRY_FOR_NEXT_TIME;

	/**
	 * ID: 1800952<br>
	 * Message: 残念やな。もうちょっとやったのに。
	 */
	public static final NpcStringId TOO_BAD_YOU_RAISED_IT_UP_TOO;

	/**
	 * ID: 1800953<br>
	 * Message: へえ、ええ音色やな。
	 */
	public static final NpcStringId OH_WHAT_A_NICE_SOUND;

	/**
	 * ID: 1800954<br>
	 * Message: なあ、なあ、一曲歌ってもええか？
	 */
	public static final NpcStringId THE_INSTRUMENT_IS_NICE_BUT_THERES_NO_SONG_SHALL_I_SING_FOR_YOU;

	/**
	 * ID: 1800955<br>
	 * Message: ええ曲っちゅうのは果物にも伝わるねん。
	 */
	public static final NpcStringId WHAT_BEAUTIFUL_MUSIC;

	/**
	 * ID: 1800956<br>
	 * Message: ええアンバイやぁ～もっと聞かせてえなぁ！
	 */
	public static final NpcStringId I_FEEL_GOOD_PLAY_SOME_MORE;

	/**
	 * ID: 1800957<br>
	 * Message: 俺のためだけに弾いてくれるんか？うれしいなあ！
	 */
	public static final NpcStringId MY_HEART_IS_BEING_CAPTURED_BY_THE_SOUND_OF_CRONO;

	/**
	 * ID: 1800958<br>
	 * Message: なあ、何か音おかしいんとちゃう？
	 */
	public static final NpcStringId GET_THE_NOTES_RIGHT_HEY_OLD_MAN_THAT_WAS_WRONG;

	/**
	 * ID: 1800959<br>
	 * Message: ええがな、ええがな！
	 */
	public static final NpcStringId I_LIKE_IT;

	/**
	 * ID: 1800960<br>
	 * Message: ああ～、何か身体がムズムズしてきたわ！
	 */
	public static final NpcStringId OOH_MY_BODY_WANTS_TO_OPEN;

	/**
	 * ID: 1800961<br>
	 * Message: あっ、この和音！もう最高やな！もっと聞かせてえなぁ！
	 */
	public static final NpcStringId OH_THIS_CHORD_MY_HEART_IS_BEING_TORN_PLAY_A_LITTLE_MORE;

	/**
	 * ID: 1800962<br>
	 * Message: これやがな！このサウンドが聴きたかってん！アンタ、ミュージシャンになれるんとちゃう？
	 */
	public static final NpcStringId ITS_THIS_THIS_I_WANTED_THIS_SOUND_WHY_DONT_YOU_TRY_BECOMING_A_SINGER;

	/**
	 * ID: 1800963<br>
	 * Message: こんなんちゃう！どつくんやったら、もっとちゃんとせえよ！
	 */
	public static final NpcStringId YOU_CAN_TRY_A_HUNDRED_TIMES_ON_THIS_YOU_WONT_GET_ANYTHING_GOOD;

	/**
	 * ID: 1800964<br>
	 * Message: 痛いっちゅ～ねん！俺は音楽が聴きたいねん！ちゃんとせえよ！
	 */
	public static final NpcStringId IT_HURTS_PLAY_JUST_THE_INSTRUMENT;

	/**
	 * ID: 1800965<br>
	 * Message: 俺の心と身体に響くような、ええサウンドで頼むでっ！
	 */
	public static final NpcStringId ONLY_GOOD_MUSIC_CAN_OPEN_MY_BODY;

	/**
	 * ID: 1800966<br>
	 * Message: これちゃうがな！あれや、あれ！クロノの楽器！あれでやれよ！
	 */
	public static final NpcStringId NOT_THIS_BUT_YOU_KNOW_THAT_WHAT_YOU_GOT_AS_A_CHRONICLE_SOUVENIR_PLAY_WITH_THAT;

	/**
	 * ID: 1800967<br>
	 * Message: ああ、ヒマや。音楽ないから、ヒマでしゃ～ない。俺、もう帰るでっ。
	 */
	public static final NpcStringId WHY_YOU_HAVE_NO_MUSIC_BORING_IM_LEAVING_NOW;

	/**
	 * ID: 1800968<br>
	 * Message: こんなん、俺好かんねん！音の出るやつで頼むわ！
	 */
	public static final NpcStringId NOT_THOSE_SHARP_THINGS_USE_THE_ONES_THAT_MAKE_NICE_SOUNDS;

	/**
	 * ID: 1800969<br>
	 * Message: 大きいスイカは音楽だけでも開くねんで。武器でどつきたおしてもあかん。
	 */
	public static final NpcStringId LARGE_WATERMELONS_ONLY_OPEN_WITH_MUSIC_JUST_STRIKING_WITH_A_WEAPON_WONT_WORK;

	/**
	 * ID: 1800970<br>
	 * Message: 楽器で頼むわ！こんなん、俺好かんねん！楽器でな！
	 */
	public static final NpcStringId STRIKE_WITH_MUSIC_NOT_WITH_SOMETHING_LIKE_THIS_YOU_NEED_MUSIC;

	/**
	 * ID: 1800971<br>
	 * Message: むちゃすごいアタックやな！せやけど音がないんやったら無駄やでっ！
	 */
	public static final NpcStringId YOURE_PRETTY_AMAZING_BUT_ITS_ALL_FOR_NOTHING;

	/**
	 * ID: 1800972<br>
	 * Message: それはモンスター用やっちゅ～ねん！俺がクロノがええねん！
	 */
	public static final NpcStringId USE_THAT_ON_MONSTERS_OK_I_WANT_CRONO;

	/**
	 * ID: 1800973<br>
	 * Message: 割れるでっ！
	 */
	public static final NpcStringId EVERYONE_THE_WATERMELON_IS_BREAKING;

	/**
	 * ID: 1800974<br>
	 * Message: できそこないを何をそないに大事にしてるねん！
	 */
	public static final NpcStringId ITS_LIKE_A_WATERMELON_SLICE;

	/**
	 * ID: 1800976<br>
	 * Message: ビッグスイカになりますようにっ！
	 */
	public static final NpcStringId LARGE_WATERMELON_MAKE_A_WISH;

	/**
	 * ID: 1800977<br>
	 * Message: あかん！俺はもう死ぬ！
	 */
	public static final NpcStringId DONT_TELL_ANYONE_ABOUT_MY_DEATH;

	/**
	 * ID: 1800978<br>
	 * Message: うわっ！何じゃ、こりゃ～っ！
	 */
	public static final NpcStringId UGH_THE_RED_JUICE_IS_FLOWING_OUT;

	/**
	 * ID: 1800979<br>
	 * Message: これで仕舞いやっ！
	 */
	public static final NpcStringId THIS_IS_ALL;

	/**
	 * ID: 1800980<br>
	 * Message: く、くやしいっ！
	 */
	public static final NpcStringId KYAAHH_IM_MAD;

	/**
	 * ID: 1800981<br>
	 * Message: スイカ、割れるでっ！アイテムが出るでっ！
	 */
	public static final NpcStringId EVERYONE_THIS_WATERMELON_BROKE_OPEN_THE_ITEM_IS_FALLING_OUT;

	/**
	 * ID: 1800982<br>
	 * Message: 割れてもうたがなっ！中身がどくどく出てるやん！
	 */
	public static final NpcStringId OH_IT_BURST_THE_CONTENTS_ARE_SPILLING_OUT;

	/**
	 * ID: 1800983<br>
	 * Message: ほな、優しく頼むでっ！
	 */
	public static final NpcStringId HOHOHO_PLAY_BETTER;

	/**
	 * ID: 1800984<br>
	 * Message: へえ、なかなかの腕やん！
	 */
	public static final NpcStringId OH_YOURE_VERY_TALENTED_HUH;

	/**
	 * ID: 1800985<br>
	 * Message: おらおら、ちゃんとどつかんかいっちゅ～ねん！
	 */
	public static final NpcStringId PLAY_SOME_MORE_MORE_MORE_MORE;

	/**
	 * ID: 1800986<br>
	 * Message: 俺はドMやねん。もっとどついてくれよ！
	 */
	public static final NpcStringId I_EAT_HITS_AND_GROW;

	/**
	 * ID: 1800987<br>
	 * Message: がんばらんかいな！時間、あれへんがな。
	 */
	public static final NpcStringId BUCK_UP_THERE_ISNT_MUCH_TIME;

	/**
	 * ID: 1800988<br>
	 * Message: そんなんで割れる思ってんのちゃうやろな？
	 */
	public static final NpcStringId DO_YOU_THINK_ILL_BURST_WITH_JUST_THAT;

	/**
	 * ID: 1800989<br>
	 * Message: ええがな、ええがな！そんなんで引き続き頼むわ。
	 */
	public static final NpcStringId WHAT_A_NICE_ATTACK_YOU_MIGHT_BE_ABLE_TO_KILL_A_PASSING_FLY;

	/**
	 * ID: 1800990<br>
	 * Message: せやせや、そこそこ！ちゃうちゃう！もっと右や！
	 */
	public static final NpcStringId RIGHT_THERE_A_LITTLE_TO_THE_RIGHT_AH_REFRESHING;

	/**
	 * ID: 1800991<br>
	 * Message: なんや、そのネコパンチみたいなんは？お前はあかん！
	 */
	public static final NpcStringId YOU_CALL_THAT_HITTING_BRING_SOME_MORE_TALENTED_FRIENDS;

	/**
	 * ID: 1800992<br>
	 * Message: 考えるより叩くが易し、っちゅ～やっちゃな。
	 */
	public static final NpcStringId DONT_THINK_JUST_HIT_WERE_HITTING;

	/**
	 * ID: 1800993<br>
	 * Message: 頼む、もっとネクターくれ！
	 */
	public static final NpcStringId I_NEED_NECTAR_GOURD_NECTAR;

	/**
	 * ID: 1800994<br>
	 * Message: ネクターなかったら育たへんやん！
	 */
	public static final NpcStringId I_CAN_ONLY_GROW_BY_DRINKING_NECTAR;

	/**
	 * ID: 1800995<br>
	 * Message: がんばって育てろよ！うまいこと育てたらビッグスイカ、失敗したら出来損ないやでっ！
	 */
	public static final NpcStringId GROW_ME_QUICK_IF_YOURE_GOOD_ITS_A_LARGE_WATERMELON_IF_YOURE_BAD_IT_A_WATERMELON_SLICE;

	/**
	 * ID: 1800996<br>
	 * Message: 腹減った～ネクター飲ませえ！腹減った～ネクター飲ませえ！この歌、知らんか？
	 */
	public static final NpcStringId GIMME_NECTAR_IM_HUNGRY;

	/**
	 * ID: 1800998<br>
	 * Message: ネクターくれたらどんどん育つでっ！
	 */
	public static final NpcStringId BRING_ME_NECTAR_THEN_ILL_DRINK_AND_GROW;

	/**
	 * ID: 1800999<br>
	 * Message: 俺はまだ未熟やねん！もうほんまにイラチやなぁ～もうちょっと待てよ！
	 */
	public static final NpcStringId YOU_WANNA_EAT_A_TINY_WATERMELON_LIKE_ME_TRY_GIVING_ME_SOME_NECTAR_ILL_GET_HUGE;

	/**
	 * ID: 1801000<br>
	 * Message: 俺はなあ、将来大物になるねん！ちゃんと育ててくれさえしたらな。
	 */
	public static final NpcStringId HEHEHE_GROW_ME_WELL_AND_YOULL_GET_A_REWARD_GROW_ME_BAD_AND_WHO_KNOWS_WHATLL_HAPPEN;

	/**
	 * ID: 1801001<br>
	 * Message: ビッグなスイカがほしいんか？俺は出来損ないでもええ。
	 */
	public static final NpcStringId YOU_WANT_A_LARGE_WATERMELON_ID_LIKE_TO_BE_A_WATERMELON_SLICE;

	/**
	 * ID: 1801002<br>
	 * Message: 俺を信じてネクターをくれたらええねん！ほんなら、ビッグなスイカになったるでっ！
	 */
	public static final NpcStringId TRUST_ME_AND_BRING_ME_SOME_NECTAR_ILL_BECOME_A_LARGE_WATERMELON_FOR_YOU;

	/**
	 * ID: 1801003<br>
	 * Message: やはりべレスは魔力をすべて回復したようだな。残っているのは痕跡だけだ．．．
	 */
	public static final NpcStringId I_SEE_BELETH_HAS_RECOVERED_ALL_OF_ITS_MAGIC_POWER_WHAT_REMAINS_HERE_IS_JUST_ITS_TRACE;

	/**
	 * ID: 1801004<br>
	 * Message: 指揮チャンネル リーダー $s1、ベレスのリングを得ました。
	 */
	public static final NpcStringId COMMAND_CHANNEL_LEADER_S1_BELETHS_RING_HAS_BEEN_ACQUIRED;

	/**
	 * ID: 1801005<br>
	 * Message: あたいを召喚するとは相当の自信だな。それじゃ、ジャック ゲーム行くぞ！
	 */
	public static final NpcStringId YOU_SUMMONED_ME_SO_YOU_MUST_BE_CONFIDENT_HUH_HERE_I_COME_JACK_GAME;

	/**
	 * ID: 1801006<br>
	 * Message: どうも！ジャック ゲーム、楽しんでくださいね。
	 */
	public static final NpcStringId HELLO_LETS_HAVE_A_GOOD_JACK_GAME;

	/**
	 * ID: 1801007<br>
	 * Message: 始めるぞ！それじゃ好きなカードを出せ！
	 */
	public static final NpcStringId IM_STARTING_NOW_SHOW_ME_THE_CARD_YOU_WANT;

	/**
	 * ID: 1801008<br>
	 * Message: 始めますよ！それじゃ好きなカードを出してください。
	 */
	public static final NpcStringId WELL_START_NOW_SHOW_ME_THE_CARD_YOU_WANT;

	/**
	 * ID: 1801009<br>
	 * Message: ディケイド パンプキン カードでも出そうか。
	 */
	public static final NpcStringId IM_SHOWING_THE_ROTTEN_PUMPKIN_CARD;

	/**
	 * ID: 1801010<br>
	 * Message: ディケイド パンプキン カードを出します。
	 */
	public static final NpcStringId ILL_BE_SHOWING_THE_ROTTEN_PUMPKIN_CARD;

	/**
	 * ID: 1801011<br>
	 * Message: ジャック パンプキン カードでも出そうか。
	 */
	public static final NpcStringId IM_SHOWING_THE_JACK_PUMPKIN_CARD;

	/**
	 * ID: 1801012<br>
	 * Message: ジャック パンプキン カードを出します。
	 */
	public static final NpcStringId ILL_BE_SHOWING_THE_JACK_PUMPKIN_CARD;

	/**
	 * ID: 1801013<br>
	 * Message: あああ、あたいの大事なファンタスティック チョコバナナ ウルトラ フレーバー キャンディが！次はこうは行かないぞ！
	 */
	public static final NpcStringId THATS_MY_PRECIOUS_FANTASTIC_CHOCOLATE_BANANA_ULTRA_FAVOR_CANDY_IM_DEFINITELY_WINNING_THE_NEXT_ROUND;

	/**
	 * ID: 1801014<br>
	 * Message: 大事なキャンディが．．．でもいいです。あげますよ。
	 */
	public static final NpcStringId ITS_MY_PRECIOUS_CANDY_BUT_ILL_HAPPILY_GIVE_IT_TO_YOU;

	/**
	 * ID: 1801015<br>
	 * Message: キャンディがなくなった．．．ちぇっ！おもちゃ箱をくれてやるよ！
	 */
	public static final NpcStringId THE_CANDY_FELL_ILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1801016<br>
	 * Message: キャンディがなくなっちゃいました．．．それじゃ、代わりにおもちゃ箱をあげましょう。
	 */
	public static final NpcStringId SINCE_THE_CANDY_FELL_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1801017<br>
	 * Message: あたしのカード、見てんじゃねえよ！次はすげえスクロールをかけるぞ！
	 */
	public static final NpcStringId YOURE_NOT_PEEKING_AT_MY_CARD_ARE_YOU_THIS_TIME_ILL_WAGER_A_SPECIAL_SCROLL;

	/**
	 * ID: 1801018<br>
	 * Message: わくわくする勝負ですね。次勝ったら特別なスクロールをあげましょう。
	 */
	public static final NpcStringId WERE_GETTING_SERIOUS_NOW_IF_YOU_WIN_AGAIN_ILL_GIVE_YOU_A_SPECIAL_SCROLL;

	/**
	 * ID: 1801019<br>
	 * Message: すげえじゃん！地下世界のプロリーグでも通じるんじゃねえの？
	 */
	public static final NpcStringId YOU_COULD_PROBABLY_ENTER_THE_UNDERWORLD_PRO_LEAGUE;

	/**
	 * ID: 1801020<br>
	 * Message: プロでもここまではできませんよ。すごいですね。
	 */
	public static final NpcStringId EVEN_PROS_CANT_DO_THIS_MUCH_YOURE_AMAZING;

	/**
	 * ID: 1801021<br>
	 * Message: どっちがモンスターだよ！次はあたしの大事な変身スティックをかけるぞ！
	 */
	public static final NpcStringId WHOS_THE_MONSTER_HERE_THIS_TIME_ILL_BET_MY_PRECIOUS_TRANSFORMATION_STICK;

	/**
	 * ID: 1801022<br>
	 * Message: また私の負けですね．．．次は負けませんよ！変身スティックをかけます。
	 */
	public static final NpcStringId I_LOST_AGAIN_I_WONT_LOSE_THIS_TIME_IM_BETTING_MY_TRANSFORMATION_STICK;

	/**
	 * ID: 1801023<br>
	 * Message: くそっ！また負けだ！次はすげえプレゼントをかけるぞ！期待しろよ！
	 */
	public static final NpcStringId LOST_AGAIN_HMPH_NEXT_TIME_ILL_BET_AN_INCREDIBLE_GIFT_WAIT_FOR_IT_IF_YOU_WANT;

	/**
	 * ID: 1801024<br>
	 * Message: ホントにすごいですね。次はものすごいプレゼントをかけますよ。期待してくださいね。
	 */
	public static final NpcStringId YOURE_TOO_GOOD_NEXT_TIME_ILL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_YOU;

	/**
	 * ID: 1801025<br>
	 * Message: これ以上お前に勝たせるのはあたいのプライドが許さねえ！
	 */
	public static final NpcStringId MY_PRIDE_CANT_HANDLE_YOU_WINNING_ANYMORE;

	/**
	 * ID: 1801026<br>
	 * Message: ここで負けたら恥ずかしいなぁ．．．
	 */
	public static final NpcStringId I_WOULD_BE_EMBARRASSING_TO_LOSE_AGAIN_HERE;

	/**
	 * ID: 1801027<br>
	 * Message: 名前は何だ！？覚えておこう。
	 */
	public static final NpcStringId WHATS_YOUR_NAME_IM_GONNA_REMEMBER_YOU;

	/**
	 * ID: 1801028<br>
	 * Message: 地上世界の人はゲームがうまいですねぇ。
	 */
	public static final NpcStringId PEOPLE_FROM_THE_ABOVE_GROUND_WORLD_ARE_REALLY_GOOD_AT_GAMES;

	/**
	 * ID: 1801029<br>
	 * Message: 地下世界でけっこうやってんだろ？
	 */
	public static final NpcStringId YOUVE_PLAYED_A_LOT_IN_THE_UNDERWORLD_HAVENT_YOU;

	/**
	 * ID: 1801030<br>
	 * Message: こんなにうまい人は初めてですよ。
	 */
	public static final NpcStringId IVE_NEVER_MET_SOMEONE_SO_GOOD_BEFORE;

	/**
	 * ID: 1801031<br>
	 * Message: 13連勝かよ。まぐれだよ、まぐれ！
	 */
	public static final NpcStringId N13_WINS_IN_A_ROW_YOURE_PRETTY_LUCKY_TODAY_HUH;

	/**
	 * ID: 1801032<br>
	 * Message: 13連勝だなんて、想像もしませんでしたよ。
	 */
	public static final NpcStringId I_NEVER_THOUGHT_I_WOULD_SEE_13_WINS_IN_A_ROW;

	/**
	 * ID: 1801033<br>
	 * Message: こんなのは生まれて初めてだよ！次はあたいの宝物、ゴールデン ジャック オ ランタンをかけるぞ！
	 */
	public static final NpcStringId THIS_IS_THE_HIGHEST_RECORD_IN_MY_LIFE_NEXT_TIME_ILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN;

	/**
	 * ID: 1801034<br>
	 * Message: プロでも難しい14勝を！次は私の宝物、ゴールデン ジャック オ ランタンをかけますよ！
	 */
	public static final NpcStringId EVEN_PROS_CANT_DO_14_WINS_NEXT_TIME_I_WILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN;

	/**
	 * ID: 1801035<br>
	 * Message: もうだめだ！降参だ！あたいの583年の人生でお前が最高だよ！
	 */
	public static final NpcStringId I_CANT_DO_THIS_ANYMORE_YOU_WIN_I_ACKNOWLEDGE_YOU_AS_THE_BEST_IVE_EVER_MET_IN_ALL_MY_583_YEARS;

	/**
	 * ID: 1801036<br>
	 * Message: これ以上やっても意味がありませんね。最高の相手でしたよ。
	 */
	public static final NpcStringId PLAYING_ANY_MORE_IS_MEANINGLESS_YOU_WERE_MY_GREATEST_OPPONENT;

	/**
	 * ID: 1801037<br>
	 * Message: 今度はあたいの勝ちだな。おもしろかったぞ。
	 */
	public static final NpcStringId I_WON_THIS_ROUND_IT_WAS_FUN;

	/**
	 * ID: 1801038<br>
	 * Message: 今度は私の勝ちですね。楽しかったです。
	 */
	public static final NpcStringId I_WON_THIS_ROUND_IT_WAS_ENJOYABLE;

	/**
	 * ID: 1801039<br>
	 * Message: やっぱ地上人はおもしれえなあ！それじゃな！
	 */
	public static final NpcStringId ABOVE_WORLD_PEOPLE_ARE_SO_FUN_THEN_SEE_YOU_LATER;

	/**
	 * ID: 1801040<br>
	 * Message: また呼んでくださいね。あなたとはまたやりたいですね。
	 */
	public static final NpcStringId CALL_ME_AGAIN_NEXT_TIME_I_WANT_TO_PLAY_AGAIN_WITH_YOU;

	/**
	 * ID: 1801041<br>
	 * Message: ゲーム、もっとやるか。プレゼントはもうないから、キャンディをやる。
	 */
	public static final NpcStringId YOU_WANNA_PLAY_SOME_MORE_IM_OUT_OF_PRESENTS_BUT_ILL_GIVE_YOU_CANDY;

	/**
	 * ID: 1801042<br>
	 * Message: ゲーム、もっとやりますか。プレゼントはもうないけど、勝ったらキャンディをあげましょう。
	 */
	public static final NpcStringId WILL_YOU_PLAY_SOME_MORE_I_DONT_HAVE_ANY_MORE_PRESENTS_BUT_I_WILL_GIVE_YOU_CANDY_IF_YOU_WIN;

	/**
	 * ID: 1801043<br>
	 * Message: お前は今までのジャック ゲーム プレーヤーの中で最高だ！やめだ、やめだ！
	 */
	public static final NpcStringId YOURE_THE_BEST_OUT_OF_ALL_THE_JACKS_GAME_PLAYERS_IVE_EVER_MET_I_GIVE_UP;

	/**
	 * ID: 1801044<br>
	 * Message: わああ、かっこいい！こんなにすごい人は初めてですよ。もうや～めた！
	 */
	public static final NpcStringId WOWWW_AWESOME_REALLY_I_HAVE_NEVER_MET_SOMEONE_AS_GOOD_AS_YOU_BEFORE_NOW_I_CANT_PLAY_ANYMORE;

	/**
	 * ID: 1801045<br>
	 * Message: $s1様はジャック ゲームで $s2連勝達成です！
	 */
	public static final NpcStringId S1_HAS_WON_S2_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1801046<br>
	 * Message: おめでとうございます！$s1様はジャック ゲームで $s2連勝達成です！
	 */
	public static final NpcStringId CONGRATULATIONS_S1_HAS_WON_S2_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1801047<br>
	 * Message: ジャック ゲーム1位、おめでとうございます！
	 */
	public static final NpcStringId CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACKS_GAME;

	/**
	 * ID: 1801048<br>
	 * Message: どもども、ベルダです。ジャック ゲーム1位、おめでとうございます！村にいるクルドから衝撃！のプレゼントがもらえますよ。それじゃ、またゲームしにきてくださいね。
	 */
	public static final NpcStringId HELLO_IM_BELLDANDY_CONGRATULATIONS_ON_WINNING_1ST_PLACE_IN_JACKS_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOULL_GET_AN_AMAZING_GIFT_LETS_PLAY_JACKS_GAME_AGAIN;

	/**
	 * ID: 1801049<br>
	 * Message: お前、ジャック ゲーム初めてだろ？カード出すタイミングもわかんねえみたいだしな。
	 */
	public static final NpcStringId HMM_YOURE_PLAYING_JACKS_GAME_FOR_THE_FIRST_TIME_HUH_YOU_COULDNT_EVEN_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME_MY_GOODNESS;

	/**
	 * ID: 1801050<br>
	 * Message: ジャック ゲームがまだよくわかってないみたいですね。カードはタイミングを合わせて出さなきゃ～
	 */
	public static final NpcStringId OH_YOURE_NOT_VERY_FAMILIAR_WITH_JACKS_GAME_RIGHT_YOU_DIDNT_TAKE_OUT_YOUR_CARD_AT_THE_RIGHT_TIME;

	/**
	 * ID: 1801051<br>
	 * Message: あたいの頭の上のゲージが0になる前にマスクに付いてるカード スキルを使わなきゃなんねえんだよ。
	 */
	public static final NpcStringId YOU_HAVE_TO_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;

	/**
	 * ID: 1801052<br>
	 * Message: 私の頭の上のゲージが0になる前にマスクに付いてるカード スキルを使わなきゃならないんですよ。
	 */
	public static final NpcStringId YOU_MUST_USE_THE_CARD_SKILL_ON_THE_MASK_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;

	/**
	 * ID: 1801053<br>
	 * Message: あたいと同じカードを出したらお前の勝ちだ。違ってたらあたいの勝ちだ。わかったろ？じゃ、もっぺん行くぞ！
	 */
	public static final NpcStringId IF_YOU_SHOW_THE_SAME_CARD_AS_ME_YOU_WIN_IF_THEYRE_DIFFERENT_I_WIN_UNDERSTAND_NOW_LETS_GO;

	/**
	 * ID: 1801054<br>
	 * Message: 私と同じカードを出したらあなたの勝ちです。違ってたら私の勝ちです。それじゃ、もう一回行きますよ！
	 */
	public static final NpcStringId YOU_WILL_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_ITS_MY_VICTORY_IF_THE_CARDS_ARE_DIFFERENT_WELL_LETS_START_AGAIN;

	/**
	 * ID: 1801055<br>
	 * Message: げっ！カード出してねえじゃん！ゲージが0になる前にカード スキルを使わなきゃなんねえんだよ。じゃ、行くぞ！
	 */
	public static final NpcStringId ACK_YOU_DIDNT_SHOW_A_CARD_YOU_HAVE_TO_USE_THE_CARD_SKILL_BEFORE_THE_GAUGE_DISAPPEARS_HMPH_THEN_IM_GOING;

	/**
	 * ID: 1801056<br>
	 * Message: あああ、カード出してませんね。カード スキルはタイミングを合わせて使わなきゃ～残念！それじゃ、行きますよ！
	 */
	public static final NpcStringId AHH_YOU_DIDNT_SHOW_A_CARD_YOU_MUST_USE_THE_CARD_SKILL_AT_THE_RIGHT_TIME_ITS_UNFORTUNATE_THEN_I_WILL_GO_NOW;

	/**
	 * ID: 1801057<br>
	 * Message: あたいがジャック ゲームのこと、教えてやるぜ！そしたら、ゲームが3回できるんだぜ！
	 */
	public static final NpcStringId LETS_LEARN_ABOUT_THE_JACKS_GAME_TOGETHER_YOU_CAN_PLAY_WITH_ME_3_TIMES;

	/**
	 * ID: 1801058<br>
	 * Message: 行くぞ！好きなカードを出せ！カード スキルはマスクに付いてるぞ。
	 */
	public static final NpcStringId LETS_START_SHOW_THE_CARD_YOU_WANT_THE_CARD_SKILL_IS_ATTACHED_TO_THE_MASK;

	/**
	 * ID: 1801059<br>
	 * Message: あたいと同じカードを出したらお前の勝ちだな。
	 */
	public static final NpcStringId YOU_SHOWED_THE_SAME_CARD_AS_ME_SO_YOU_WIN;

	/**
	 * ID: 1801060<br>
	 * Message: あたいと違うカードを出したからお前の負けだ。
	 */
	public static final NpcStringId YOU_SHOWED_A_DIFFERENT_CARD_FROM_ME_SO_YOU_LOSE;

	/**
	 * ID: 1801061<br>
	 * Message: 練習だから買ってもキャンディはやんねえぞ！
	 */
	public static final NpcStringId THAT_WAS_PRACTICE_SO_THERES_NO_CANDY_EVEN_IF_YOU_WIN;

	/**
	 * ID: 1801062<br>
	 * Message: 残念。もっぺん練習しようぜ。
	 */
	public static final NpcStringId ITS_UNFORTUNATE_LETS_PRACTICE_ONE_MORE_TIME;

	/**
	 * ID: 1801063<br>
	 * Message: カードを出すタイミングに気をつけろ。あたいの頭の上のゲージが0になる前にカード スキルを使っちゃいな！
	 */
	public static final NpcStringId YOU_GOTTA_SHOW_THE_CARD_AT_THE_RIGHT_TIME_USE_THE_CARD_SKILL_YOU_WANT_BEFORE_THE_GAUGE_ABOVE_MY_HEAD_DISAPPEARS;

	/**
	 * ID: 1801064<br>
	 * Message: ジャック オ ランタン マスクにカード スキルがついてるだろ？それを使いな。
	 */
	public static final NpcStringId THE_CARD_SKILLS_ARE_ATTACHED_TO_THE_JACK_OLANTERN_MASK_RIGHT_THATS_WHAT_YOU_USE;

	/**
	 * ID: 1801065<br>
	 * Message: あたいと同じカードを出せばお前の勝ちだ。あたいと違うカードを出せばあたいの勝ちだ。それじゃ、もっぺん行くぞ！
	 */
	public static final NpcStringId YOU_WIN_IF_YOU_SHOW_THE_SAME_CARD_AS_ME_AND_I_WIN_IF_THE_CARDS_ARE_DIFFERENT_OK_LETS_GO;

	/**
	 * ID: 1801066<br>
	 * Message: またカード出してねえじゃん！もういい、また今後やろうぜ。それじゃな。
	 */
	public static final NpcStringId YOU_DIDNT_SHOW_A_CARD_AGAIN_WELL_TRY_AGAIN_LATER_IM_GONNA_GO_NOW;

	/**
	 * ID: 1801067<br>
	 * Message: ジャック ゲームのことがちょっとわかったかな。本番はウルとベルダとやるんだ。それじゃな！
	 */
	public static final NpcStringId NOW_DO_YOU_UNDERSTAND_A_LITTLE_ABOUT_JACKS_GAME_THE_REAL_GAMES_WITH_ULDIE_AND_BELLDANDY_WELL_SEE_YOU_LATER;

	/**
	 * ID: 1801068<br>
	 * Message: はははっ。
	 */
	public static final NpcStringId HAHAHAHA;

	/**
	 * ID: 1801069<br>
	 * Message: どこを見てるんだ？
	 */
	public static final NpcStringId WHERE_ARE_YOU_LOOKING;

	/**
	 * ID: 1801070<br>
	 * Message: 私はここにいる。
	 */
	public static final NpcStringId IM_RIGHT_HERE;

	/**
	 * ID: 1801071<br>
	 * Message: 至近距離からの集中攻撃でヴァラカスは集中できないだろう。\n続けて攻撃すればチャンスがつかめるはずだ！
	 */
	public static final NpcStringId ANNOYING_CONCENTRATION_ATTACKS_ARE_DISRUPTING_VALAKASS_CONCENTRATIONNIF_IT_CONTINUES_YOU_MAY_GET_A_GREAT_OPPORTUNITY;

	/**
	 * ID: 1801072<br>
	 * Message: とある勇士の一撃がヴァラカスの分厚い鱗を傷つけた。\nヴァラカスの防御力が大幅に落ちているぞ！
	 */
	public static final NpcStringId SOME_WARRIORS_BLOW_HAS_LEFT_A_HUGE_GASH_BETWEEN_THE_GREAT_SCALES_OF_VALAKASNVALAKASS_P_DEF_IS_GREATLY_DECREASED;

	/**
	 * ID: 1801073<br>
	 * Message: 至近距離からの集中攻撃にうろたえたヴァラカスは隙を見せているぞ！
	 */
	public static final NpcStringId ANNOYING_CONCENTRATION_ATTACKS_OVERWHELMED_VALAKAS_MAKING_IT_FORGET_ITS_RAGE_AND_BECOME_DISTRACTED;

	/**
	 * ID: 1801074<br>
	 * Message: 遠距離からの集中攻撃でヴァラカスは頭に来ている。\n続けて攻撃すれば危機に瀕するかもしれない！
	 */
	public static final NpcStringId LONG_RANGE_CONCENTRATION_ATTACKS_HAVE_ENRAGED_VALAKASNIF_YOU_CONTINUE_IT_MAY_BECOME_A_DANGEROUS_SITUATION;

	/**
	 * ID: 1801075<br>
	 * Message: 卑怯な反撃が続いてヴァラカスの怒りが頂点に達した。\nヴァラカスの攻撃力が大幅に上がったぞ！
	 */
	public static final NpcStringId BECAUSE_THE_COWARDLY_COUNTERATTACKS_CONTINUED_VALAKASS_FURY_HAS_REACHED_ITS_MAXIMUMNVALAKASS_P_ATK_IS_GREATLY_INCREASED;

	/**
	 * ID: 1801076<br>
	 * Message: 遠距離距離からの集中攻撃に怒ったヴァラカス、目標を激しく攻撃しているぞ！
	 */
	public static final NpcStringId VALAKAS_HAS_BEEN_ENRAGED_BY_THE_LONG_RANGE_CONCENTRATION_ATTACKS_AND_IS_COMING_TOWARD_ITS_TARGET_WITH_EVEN_GREATER_ZEAL;

	/**
	 * ID: 1801077<br>
	 * Message: タンタどもよ、聞け！私が戻ってきた！黒い深淵の預言者ユゴロスもいる。恐れるでない！
	 */
	public static final NpcStringId LISTEN_OH_TANTAS_I_HAVE_RETURNED_THE_PROPHET_YUGOROS_OF_THE_BLACK_ABYSS_IS_WITH_ME_SO_DO_NOT_BE_AFRAID;

	/**
	 * ID: 1801078<br>
	 * Message: よく来たな、$s1！黒い深淵にささげるに値するいけにえか見てみようではないか！
	 */
	public static final NpcStringId WELCOME_S1_LET_US_SEE_IF_YOU_HAVE_BROUGHT_A_WORTHY_OFFERING_FOR_THE_BLACK_ABYSS;

	/**
	 * ID: 1801079<br>
	 * Message: 相手にとって不足はない！しかし、私には黒い深淵がくださった聖薬、深淵草がある！
	 */
	public static final NpcStringId WHAT_A_FORMIDABLE_FOE_BUT_I_HAVE_THE_ABYSS_WEED_GIVEN_TO_ME_BY_THE_BLACK_ABYSS_LET_ME_SEE;

	/**
	 * ID: 1801080<br>
	 * Message: むははは！この口の中のぴりっとした感じ！黒い深淵がくださった力が私を生き返らせるのだ！
	 */
	public static final NpcStringId MUHAHAHA_AH_THIS_BURNING_SENSATION_IN_MY_MOUTH_THE_POWER_OF_THE_BLACK_ABYSS_IS_REVIVING_ME;

	/**
	 * ID: 1801081<br>
	 * Message: なんてことだ！深淵草を使えなくするとは．．．それがいかに不敬であるかわかっているのか！
	 */
	public static final NpcStringId NO_HOW_DARE_YOU_STOP_ME_FROM_USING_THE_ABYSS_WEED_DO_YOU_KNOW_WHAT_YOU_HAVE_DONE;

	/**
	 * ID: 1801082<br>
	 * Message: こんなくたびれたやつを、黒い深淵にいけにえとして捧げるわけにはいかない！
	 */
	public static final NpcStringId A_LIMP_CREATURE_LIKE_THIS_IS_UNWORTHY_TO_BE_AN_OFFERING_YOU_HAVE_NO_RIGHT_TO_SACRIFICE_TO_THE_BLACK_ABYSS;

	/**
	 * ID: 1801083<br>
	 * Message: タンタどもよ、聞け！黒い深淵が飢えていらっしゃる！新鮮ないけにえを捧げよ！
	 */
	public static final NpcStringId LISTEN_OH_TANTAS_THE_BLACK_ABYSS_IS_FAMISHED_FIND_SOME_FRESH_OFFERINGS;

	/**
	 * ID: 1801084<br>
	 * Message: こ、この私が敗北するとは！黒い深淵よ、私を受け入れたまえ．．．
	 */
	public static final NpcStringId AH_HOW_COULD_I_LOSE_OH_BLACK_ABYSS_RECEIVE_ME;

	/**
	 * ID: 1801085<br>
	 * Message: 警報装置破壊。侵入者排除。
	 */
	public static final NpcStringId ALARM_SYSTEM_DESTROYED_INTRUDER_EXCLUDED;

	/**
	 * ID: 1801089<br>
	 * Message: 4段階スタート
	 */
	public static final NpcStringId BEGIN_STAGE_4;

	/**
	 * ID: 1801090<br>
	 * Message: 次の戦闘までの残り時間
	 */
	public static final NpcStringId TIME_REMAINING_UNTIL_NEXT_BATTLE;

	/**
	 * ID: 1801091<br>
	 * Message: 猛獣は飼料を食べました。お腹がいっぱいになったのか、何の反応も見せません。
	 */
	public static final NpcStringId THE_BEAST_ATE_THE_FEED_BUT_IT_ISNT_SHOWING_A_RESPONSE_PERHAPS_BECAUSE_ITS_ALREADY_FULL;

	/**
	 * ID: 1801092<br>
	 * Message: 猛獣は飼料を食べずに吐き出します。
	 */
	public static final NpcStringId THE_BEAST_SPIT_OUT_THE_FEED_INSTEAD_OF_EATING_IT;

	/**
	 * ID: 1801093<br>
	 * Message: 猛獣は飼料を食べずにあなたを攻撃します。
	 */
	public static final NpcStringId THE_BEAST_SPIT_OUT_THE_FEED_AND_IS_INSTEAD_ATTACKING_YOU;

	/**
	 * ID: 1801094<br>
	 * Message: ゴールデン スパイスが足りないため、$s1があなたのもとを去ります。
	 */
	public static final NpcStringId S1_IS_LEAVING_YOU_BECAUSE_YOU_DONT_HAVE_ENOUGH_GOLDEN_SPICES;

	/**
	 * ID: 1801095<br>
	 * Message: クリスタル スパイスが足りないため、$s1があなたのもとを去ります。
	 */
	public static final NpcStringId S1_IS_LEAVING_YOU_BECAUSE_YOU_DONT_HAVE_ENOUGH_CRYSTAL_SPICES;

	/**
	 * ID: 1801096<br>
	 * Message: $s1様！神のご加護のあらんことを！
	 */
	public static final NpcStringId S1_MAY_THE_PROTECTION_OF_THE_GODS_BE_UPON_YOU;

	/**
	 * ID: 1801097<br>
	 * Message: フレヤが動き出しました。
	 */
	public static final NpcStringId FREYA_HAS_STARTED_TO_MOVE;

	/**
	 * ID: 1801098<br>
	 * Message: くそ．．． こんなところで死ぬとは．．．
	 */
	public static final NpcStringId HOW_COULD_I_FALL_IN_A_PLACE_LIKE_THIS;

	/**
	 * ID: 1801099<br>
	 * Message: ようやく一息つけそうだ。あなたは．．．誰が遣わしたのか大体の見当はつくが．．．
	 */
	public static final NpcStringId I_CAN_FINALLY_TAKE_A_BREATHER_BY_THE_WAY_WHO_ARE_YOU_HMM_I_THINK_I_KNOW_WHO_SENT_YOU;

	/**
	 * ID: 1801100<br>
	 * Message: バランスの$s1
	 */
	public static final NpcStringId S1_OF_BALANCE;

	/**
	 * ID: 1801101<br>
	 * Message: 敏捷な$s1
	 */
	public static final NpcStringId SWIFT_S2;

	/**
	 * ID: 1801102<br>
	 * Message: 祝福の$s1
	 */
	public static final NpcStringId S1_OF_BLESSING;

	/**
	 * ID: 1801103<br>
	 * Message: 鋭い$s1
	 */
	public static final NpcStringId SHARP_S2;

	/**
	 * ID: 1801104<br>
	 * Message: 有能な$s1
	 */
	public static final NpcStringId USEFUL_S2;

	/**
	 * ID: 1801105<br>
	 * Message: 乱暴な$s1
	 */
	public static final NpcStringId RECKLESS_S2;

	/**
	 * ID: 1801106<br>
	 * Message: アルパイン クッカブロ
	 */
	public static final NpcStringId ALPEN_KOOKABURRA;

	/**
	 * ID: 1801107<br>
	 * Message: アルパイン クーガー
	 */
	public static final NpcStringId ALPEN_COUGAR;

	/**
	 * ID: 1801108<br>
	 * Message: アルパイン バッファロー
	 */
	public static final NpcStringId ALPEN_BUFFALO;

	/**
	 * ID: 1801109<br>
	 * Message: アルパイン グレンデル
	 */
	public static final NpcStringId ALPEN_GRENDEL;

	/**
	 * ID: 1801110<br>
	 * Message: 戦闘終了制限時間
	 */
	public static final NpcStringId BATTLE_END_LIMIT_TIME;

	/**
	 * ID: 1801111<br>
	 * Message: どこからか強い魔力が感じられます。
	 */
	public static final NpcStringId STRONG_MAGIC_POWER_CAN_BE_FELT_FROM_SOMEWHERE;

	/**
	 * ID: 1801112<br>
	 * Message: 俺の訓練兵を攻撃するとは！
	 */
	public static final NpcStringId HOW_DARE_YOU_ATTACK_MY_RECRUITS;

	/**
	 * ID: 1801113<br>
	 * Message: 秩序を乱すのは誰だ！
	 */
	public static final NpcStringId WHO_IS_DISRUPTING_THE_ORDER;

	/**
	 * ID: 1801114<br>
	 * Message: 教官が死んでしまった！
	 */
	public static final NpcStringId THE_DRILLMASTER_IS_DEAD;

	/**
	 * ID: 1801115<br>
	 * Message: 隊列を整えよ！
	 */
	public static final NpcStringId LINE_UP_THE_RANKS;

	/**
	 * ID: 1801116<br>
	 * Message: ほれ、メシだ。
	 */
	public static final NpcStringId I_BROUGHT_THE_FOOD;

	/**
	 * ID: 1801117<br>
	 * Message: さっさとメシ食えよ。
	 */
	public static final NpcStringId COME_AND_EAT;

	/**
	 * ID: 1801118<br>
	 * Message: わあ、おいしそう！
	 */
	public static final NpcStringId LOOKS_DELICIOUS;

	/**
	 * ID: 1801119<br>
	 * Message: メシ食いに行こうぜ。
	 */
	public static final NpcStringId LETS_GO_EAT;

	/**
	 * ID: 1801120<br>
	 * Message: 氷宮よ！侵入者におまえの息吹を！
	 */
	public static final NpcStringId ARCHER_GIVE_YOUR_BREATH_FOR_THE_INTRUDER;

	/**
	 * ID: 1801121<br>
	 * Message: 私の騎士たちよ！忠誠心を示せ！
	 */
	public static final NpcStringId MY_KNIGHTS_SHOW_YOUR_LOYALTY;

	/**
	 * ID: 1801122<br>
	 * Message: もはやがまんならぬ！
	 */
	public static final NpcStringId I_CAN_TAKE_IT_NO_LONGER;

	/**
	 * ID: 1801123<br>
	 * Message: 氷宮よ！私の呼び掛けに答えよ！
	 */
	public static final NpcStringId ARCHER_HEED_MY_CALL;

	/**
	 * ID: 1801124<br>
	 * Message: 空間が徐々に揺れだすのを感じます。
	 */
	public static final NpcStringId THE_SPACE_FEELS_LIKE_ITS_GRADUALLY_STARTING_TO_SHAKE;

	/**
	 * ID: 1801125<br>
	 * Message: もはや看過できん。
	 */
	public static final NpcStringId I_CAN_NO_LONGER_STAND_BY;

	/**
	 * ID: 1801126<br>
	 * Message: タクラカンが力を集めて攻撃を開始します。
	 */
	public static final NpcStringId TAKLACAN_IS_GATHERING_ITS_STRENGTH_AND_LAUNCHING_AN_ATTACK;

	/**
	 * ID: 1801127<br>
	 * Message: タクラカンがコクラコンの力をまともに受けて弱まります。
	 */
	public static final NpcStringId TAKLACAN_RECEIVED_COKRAKON_AND_BECAME_WEAKER;

	/**
	 * ID: 1801128<br>
	 * Message: 周囲にほとばしるコクラコンの力。
	 */
	public static final NpcStringId COKRAKONS_POWER_CAN_BE_FELT_NEARBY;

	/**
	 * ID: 1801129<br>
	 * Message: 姿を隠す準備をするタクラカン。
	 */
	public static final NpcStringId TAKLACAN_IS_PREPARING_TO_HIDE_ITSELF;

	/**
	 * ID: 1801130<br>
	 * Message: 雄叫びを残して空虚の空間へと姿を消すタクラカン。
	 */
	public static final NpcStringId TAKLACAN_DISAPPEARS_INTO_THE_SPACE_OF_FUTILITY_WITH_A_ROAR;

	/**
	 * ID: 1801131<br>
	 * Message: トルンバが動き出しました。
	 */
	public static final NpcStringId TORUMBAS_BODY_IS_STARTING_TO_MOVE;

	/**
	 * ID: 1801132<br>
	 * Message: トルンバの丈夫な皮膚から反応が感じられます。
	 */
	public static final NpcStringId A_RESPONSE_CAN_BE_FELT_FROM_TORUMBAS_TOUGH_SKIN;

	/**
	 * ID: 1801133<br>
	 * Message: トルンバの丈夫な皮膚に跡が残ります。
	 */
	public static final NpcStringId A_MARK_REMAINS_ON_TORUMBAS_TOUGH_SKIN;

	/**
	 * ID: 1801134<br>
	 * Message: トルンバの眼光が弱くなります。
	 */
	public static final NpcStringId THE_LIGHT_IS_BEGINNING_TO_WEAKEN_IN_TORUMBAS_EYES;

	/**
	 * ID: 1801135<br>
	 * Message: トルンバは左足に傷を負いました。
	 */
	public static final NpcStringId TORUMBAS_LEFT_LEG_WAS_DAMAGED;

	/**
	 * ID: 1801136<br>
	 * Message: トルンバは右足に傷を負いました。
	 */
	public static final NpcStringId TORUMBAS_RIGHT_LEG_WAS_DAMAGED;

	/**
	 * ID: 1801137<br>
	 * Message: トルンバは左腕に傷を負いました。
	 */
	public static final NpcStringId TORUMBAS_LEFT_ARM_WAS_DAMAGED;

	/**
	 * ID: 1801138<br>
	 * Message: トルンバは右腕に傷を負いました。
	 */
	public static final NpcStringId TORUMBAS_RIGHT_ARM_WAS_DAMAGED;

	/**
	 * ID: 1801139<br>
	 * Message: トルンバの丈夫な皮膚に深い傷が付きました。
	 */
	public static final NpcStringId A_DEEP_WOUND_APPEARED_ON_TORUMBAS_TOUGH_SKIN;

	/**
	 * ID: 1801140<br>
	 * Message: トルンバの眼光が消え入りそうです。
	 */
	public static final NpcStringId THE_LIGHT_IS_SLOWLY_FADING_FROM_TORUMBAS_EYES;

	/**
	 * ID: 1801141<br>
	 * Message: トルンバは身を隠そうとしています。
	 */
	public static final NpcStringId TORUMBA_IS_PREPARING_TO_HIDE_ITS_BODY;

	/**
	 * ID: 1801142<br>
	 * Message: トルンバは自らの空間に入って姿を消しました。
	 */
	public static final NpcStringId TORUMBA_DISAPPEARED_INTO_HIS_SPACE;

	/**
	 * ID: 1801143<br>
	 * Message: トルンバが歪んだ空間に身を隠そうとしています。
	 */
	public static final NpcStringId TORUMBA_IS_PREPARING_TO_HIDE_ITSELF_IN_THE_TWISTED_SPACE;

	/**
	 * ID: 1801144<br>
	 * Message: ドーパゲンが動き出しました。
	 */
	public static final NpcStringId DOPAGEN_HAS_STARTED_TO_MOVE;

	/**
	 * ID: 1801145<br>
	 * Message: レプティリコンの気が凝縮されつつあることが感じられます。
	 */
	public static final NpcStringId LEPTILIKONS_ENERGY_FEELS_LIKE_ITS_BEING_CONDENSED;

	/**
	 * ID: 1801146<br>
	 * Message: ドーパゲンが姿を隠す兆しを見せています。
	 */
	public static final NpcStringId DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF_WITH_A_STRANGE_SCENT;

	/**
	 * ID: 1801147<br>
	 * Message: 姿を隠す準備をするドーパゲン。
	 */
	public static final NpcStringId DOPAGEN_IS_PREPARING_TO_HIDE_ITSELF;

	/**
	 * ID: 1801148<br>
	 * Message: ドーパゲンは歪んだ空間の間へと姿を消しました。
	 */
	public static final NpcStringId DOPAGEN_IS_DISAPPEARING_IN_BETWEEN_THE_TWISTED_SPACE;

	/**
	 * ID: 1801149<br>
	 * Message: マグエン来襲！
	 */
	public static final NpcStringId MAGUEN_APPEARANCE;

	/**
	 * ID: 1801150<br>
	 * Message: マグエン プラズマ：ビスタコンが充分にあつまりました。
	 */
	public static final NpcStringId ENOUGH_MAGUEN_PLASMA_BISTAKON_HAVE_GATHERED;

	/**
	 * ID: 1801151<br>
	 * Message: マグエン プラズマ：コクラコンが充分にあつまりました。
	 */
	public static final NpcStringId ENOUGH_MAGUEN_PLASMA_COKRAKON_HAVE_GATHERED;

	/**
	 * ID: 1801152<br>
	 * Message: マグエン プラズマ：レプティリコンが充分にあつまりました。
	 */
	public static final NpcStringId ENOUGH_MAGUEN_PLASMA_LEPTILIKON_HAVE_GATHERED;

	/**
	 * ID: 1801153<br>
	 * Message: 収集機いっぱいのプラズマが調和をなして混じり合います。
	 */
	public static final NpcStringId THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_AND_ARE_HARMONIZED;

	/**
	 * ID: 1801154<br>
	 * Message: プラズマ収集機はいっぱいになりましたが、プラズマ同士で衝突、爆発して、消滅します。
	 */
	public static final NpcStringId THE_PLASMAS_HAVE_FILLED_THE_AEROSCOPE_BUT_THEY_ARE_RAMMING_INTO_EACH_OTHER_EXPLODING_AND_DYING;

	/**
	 * ID: 1801155<br>
	 * Message: スッゲー！$s1がソウルストーンの欠片を100個も持っていきやがった。ドロボーだぜ、ドロボー。
	 */
	public static final NpcStringId AMAZING_S1_TOOK_100_OF_THESE_SOUL_STONE_FRAGMENTS_WHAT_A_COMPLETE_SWINDLER;

	/**
	 * ID: 1801156<br>
	 * Message: おい$s1、何かやったのか。1つだったら大して惜しくもないだろう。アハハ！
	 */
	public static final NpcStringId HMM_HEY_DID_YOU_GIVE_S1_SOMETHING_BUT_IT_WAS_JUST_1_HAHA;

	/**
	 * ID: 1801157<br>
	 * Message: $s1がこの$s2個のやつを引いたぞ！ラッキー！
	 */
	public static final NpcStringId S1_PULLED_ONE_WITH_S2_DIGITS_LUCKY_NOT_BAD;

	/**
	 * ID: 1801158<br>
	 * Message: スッカラカンよりはマシだろ？もっと気が悪いって？
	 */
	public static final NpcStringId ITS_BETTER_THAN_LOSING_IT_ALL_RIGHT_OR_DOES_THIS_FEEL_WORSE;

	/**
	 * ID: 1801159<br>
	 * Message: ありゃまあ、$s1はホントにツイてないねぇ。もうこりゃ神頼みしかないな。
	 */
	public static final NpcStringId AHEM_S1_HAS_NO_LUCK_AT_ALL_TRY_PRAYING;

	/**
	 * ID: 1801160<br>
	 * Message: ああ、もう終わりだ！くそっ、$s1！これでも持って行きやがれ！
	 */
	public static final NpcStringId AH_ITS_OVER_WHAT_KIND_OF_GUY_IS_THAT_DAMN_FINE_YOU_S1_TAKE_IT_AND_GET_OUTTA_HERE;

	/**
	 * ID: 1801161<br>
	 * Message: スカがなければ大当たりもない。ほれ、スカだ。
	 */
	public static final NpcStringId A_BIG_PIECE_IS_MADE_UP_OF_LITTLE_PIECES_SO_HERES_A_LITTLE_PIECE;

	/**
	 * ID: 1801162<br>
	 * Message: これぐらいだったらいいだろ？いやだって？おい、泣くなよ～
	 */
	public static final NpcStringId YOU_DONT_FEEL_BAD_RIGHT_ARE_YOU_SAD_BUT_DONT_CRY;

	/**
	 * ID: 1801163<br>
	 * Message: さあ、次は誰だ？どうせ短い人生だ。運だめしと思ってやってみろ。まあ、ともかく見物だけでもしてみなよ。
	 */
	public static final NpcStringId OK_WHOS_NEXT_IT_ALL_DEPENDS_ON_YOUR_FATE_AND_LUCK_RIGHT_AT_LEAST_COME_AND_TAKE_A_LOOK;

	/**
	 * ID: 1801164<br>
	 * Message: もう誰もやらないのか。何、ビビってんだよ！取って食うわけでもあるまいし。ハハハ！
	 */
	public static final NpcStringId NO_ONE_ELSE_DONT_WORRY_I_DONT_BITE_HAHA;

	/**
	 * ID: 1801165<br>
	 * Message: 一万個以上持っていきやがったやつもいるんだぜ。武術だけ達者でよかった時代は終わったんだぜ。なんでもできなきゃ。
	 */
	public static final NpcStringId THERE_WAS_SOMEONE_WHO_WON_10000_FROM_ME_A_WARRIOR_SHOULDNT_JUST_BE_GOOD_AT_FIGHTING_RIGHT_YOUVE_GOTTA_BE_GOOD_IN_EVERYTHING;

	/**
	 * ID: 1801166<br>
	 * Message: さあ、幸運の女神は誰のもとに！そりゃ、女神に聞いてみなきゃワカンナイけどな。
	 */
	public static final NpcStringId OK_MASTER_OF_LUCK_THATS_YOU_HAHA_WELL_ANYONE_CAN_COME_AFTER_ALL;

	/**
	 * ID: 1801167<br>
	 * Message: 過酷な戦場ではギャンブルが一番の娯楽だぜ。それにこれは安全なんだ。
	 */
	public static final NpcStringId SHEDDING_BLOOD_IS_A_GIVEN_ON_THE_BATTLEFIELD_AT_LEAST_ITS_SAFE_HERE;

	/**
	 * ID: 1801168<br>
	 * Message: くうっ！
	 */
	public static final NpcStringId GASP;

	/**
	 * ID: 1801169<br>
	 * Message: まだかよ！
	 */
	public static final NpcStringId IS_IT_STILL_LONG_OFF;

	/**
	 * ID: 1801170<br>
	 * Message: エルミアン様はご無事か。俺が生き残ったことすら信じられない．．．
	 */
	public static final NpcStringId IS_ERMIAN_WELL_EVEN_I_CANT_BELIEVE_THAT_I_SURVIVED_IN_A_PLACE_LIKE_THIS;

	/**
	 * ID: 1801171<br>
	 * Message: 君たち側に付いて幾星霜．．．時間が止まったようにすら感じる。
	 */
	public static final NpcStringId I_DONT_KNOW_HOW_LONG_ITS_BEEN_SINCE_I_PARTED_COMPANY_WITH_YOU_TIME_DOESNT_SEEM_TO_MOVE_IT_JUST_FEELS_TOO_LONG;

	/**
	 * ID: 1801172<br>
	 * Message: 私の立場でこんなことを言うのは申し訳ないが．．．君たちにやられたところがズキズキしてたまらない．．．
	 */
	public static final NpcStringId SORRY_TO_SAY_THIS_BUT_THE_PLACE_YOU_STRUCK_ME_BEFORE_NOW_HURTS_GREATLY;

	/**
	 * ID: 1801173<br>
	 * Message: うう．．．すまないが、もはやここまで。家族のもとに戻りたかった．．．
	 */
	public static final NpcStringId UGH_IM_SORRY_IT_LOOKS_LIKE_THIS_IS_IT_FOR_ME_I_WANTED_TO_LIVE_AND_SEE_MY_FAMILY;

	/**
	 * ID: 1801174<br>
	 * Message: どこだ？見えないぞ。
	 */
	public static final NpcStringId WHERE_ARE_YOU_I_CANT_SEE_ANYTHING;

	/**
	 * ID: 1801175<br>
	 * Message: 一体どこにいるんだ？これじゃ付いていけないじゃないか。
	 */
	public static final NpcStringId WHERE_ARE_YOU_REALLY_I_CANT_FOLLOW_YOU_LIKE_THIS;

	/**
	 * ID: 1801176<br>
	 * Message: すまない．．．もはやこれまで．．．
	 */
	public static final NpcStringId IM_SORRY_THIS_IS_IT_FOR_ME;

	/**
	 * ID: 1801177<br>
	 * Message: ウウウ．．．生き残ってエルミアン様に再度お目にかかろうとは．．．これで家族のもとに帰られるのでしょうか。
	 */
	public static final NpcStringId SOB_TO_SEE_ERMIAN_AGAIN_CAN_I_GO_TO_MY_FAMILY_NOW;

	/**
	 * ID: 1801183<br>
	 * Message: こんなに早く元気になるとはな。$s1、助かったぜ。
	 */
	public static final NpcStringId MY_ENERGY_WAS_RECOVERED_SO_QUICKLY_THANKS_S1;

	/**
	 * ID: 1801187<br>
	 * Message: ジュルル．．．食欲が湧いてきた！
	 */
	public static final NpcStringId IM_STARVING;

	/**
	 * ID: 1801189<br>
	 * Message: 気を失うほどの強い魔力が感じられます。
	 */
	public static final NpcStringId MAGIC_POWER_SO_STRONG_THAT_IT_COULD_MAKE_YOU_LOSE_YOUR_MIND_CAN_BE_FELT_FROM_SOMEWHERE;

	/**
	 * ID: 1801190<br>
	 * Message: お前たち人間の贈り物なんぞ、もらったところで私が喜ぶとでも思ったのか！
	 */
	public static final NpcStringId EVEN_THOUGH_YOU_BRING_SOMETHING_CALLED_A_GIFT_AMONG_YOUR_HUMANS_IT_WOULD_JUST_BE_PROBLEMATIC_FOR_ME;

	/**
	 * ID: 1801191<br>
	 * Message: こういう時はどういう表情をすればいいのやら。人間の感情とはこういうものなのか。
	 */
	public static final NpcStringId I_JUST_DONT_KNOW_WHAT_EXPRESSION_I_SHOULD_HAVE_IT_APPEARED_ON_ME_ARE_HUMANS_EMOTIONS_LIKE_THIS_FEELING;

	/**
	 * ID: 1801192<br>
	 * Message: ありがたいという感情は遠い昔の思い出に残っている．．．
	 */
	public static final NpcStringId THE_FEELING_OF_THANKS_IS_JUST_TOO_MUCH_DISTANT_MEMORY_FOR_ME;

	/**
	 * ID: 1801193<br>
	 * Message: 懐かしい．．．このような感情は前にも感じたことがある．．．
	 */
	public static final NpcStringId BUT_I_KIND_OF_MISS_IT_LIKE_I_HAD_FELT_THIS_FEELING_BEFORE;

	/**
	 * ID: 1801194<br>
	 * Message: 私はアイス クイーン フレヤ．．．こんな気分も感情もメリッサの持っていたものに過ぎない！
	 */
	public static final NpcStringId I_AM_ICE_QUEEN_FREYA_THIS_FEELING_AND_EMOTION_ARE_NOTHING_BUT_A_PART_OF_MELISSAA_MEMORIES;

	/**
	 * ID: 1801195<br>
	 * Message: $s1よ．．．お礼と思って取っておけ。何も怪しむことはない。ちょっと心変わりしただけだ．．．
	 */
	public static final NpcStringId DEAR_S1_THINK_OF_THIS_AS_MY_APPRECIATION_FOR_THE_GIFT_TAKE_THIS_WITH_YOU_THERES_NOTHING_STRANGE_ABOUT_IT_ITS_JUST_A_BIT_OF_MY_CAPRICIOUSNESS;

	/**
	 * ID: 1801196<br>
	 * Message: 誰かの好意なるものは決して気分の悪いものではないな．．．$s1よ、お前の誠意を受け取ることとしよう。
	 */
	public static final NpcStringId THE_KINDNESS_OF_SOMEBODY_IS_NOT_A_BAD_FEELING_DEAR_S1_I_WILL_TAKE_THIS_GIFT_OUT_OF_RESPECT_YOUR_KINDNESS;

	/**
	 * ID: 1811000<br>
	 * Message: ファイター
	 */
	public static final NpcStringId FIGHTER;

	/**
	 * ID: 1811001<br>
	 * Message: ウォーリアー
	 */
	public static final NpcStringId WARRIOR;

	/**
	 * ID: 1811002<br>
	 * Message: グラディエーター
	 */
	public static final NpcStringId GLADIATOR;

	/**
	 * ID: 1811003<br>
	 * Message: ウォーリアー
	 */
	public static final NpcStringId WARLORD;

	/**
	 * ID: 1811004<br>
	 * Message: ナイト
	 */
	public static final NpcStringId KNIGHT;

	/**
	 * ID: 1811005<br>
	 * Message: パラディン
	 */
	public static final NpcStringId PALADIN;

	/**
	 * ID: 1811006<br>
	 * Message: ダーク アベンジャー
	 */
	public static final NpcStringId DARK_AVENGER;

	/**
	 * ID: 1811007<br>
	 * Message: ローグ
	 */
	public static final NpcStringId ROGUE;

	/**
	 * ID: 1811008<br>
	 * Message: トレジャー ハンター
	 */
	public static final NpcStringId TREASURE_HUNTER;

	/**
	 * ID: 1811009<br>
	 * Message: ホーク アイ
	 */
	public static final NpcStringId HAWKEYE;

	/**
	 * ID: 1811010<br>
	 * Message: メイジ
	 */
	public static final NpcStringId MAGE;

	/**
	 * ID: 1811011<br>
	 * Message: ウィザード
	 */
	public static final NpcStringId WIZARD;

	/**
	 * ID: 1811012<br>
	 * Message: ソーサラー
	 */
	public static final NpcStringId SORCERER;

	/**
	 * ID: 1811013<br>
	 * Message: ネクロマンサー
	 */
	public static final NpcStringId NECROMANCER;

	/**
	 * ID: 1811014<br>
	 * Message: ウォーロック
	 */
	public static final NpcStringId WARLOCK;

	/**
	 * ID: 1811015<br>
	 * Message: クレリック
	 */
	public static final NpcStringId CLERIC;

	/**
	 * ID: 1811016<br>
	 * Message: ビショップ
	 */
	public static final NpcStringId BISHOP;

	/**
	 * ID: 1811017<br>
	 * Message: プロフィット
	 */
	public static final NpcStringId PROPHET;

	/**
	 * ID: 1811018<br>
	 * Message: エルヴン ファイター
	 */
	public static final NpcStringId ELVEN_FIGHTER;

	/**
	 * ID: 1811019<br>
	 * Message: エルヴン ナイト
	 */
	public static final NpcStringId ELVEN_KNIGHT;

	/**
	 * ID: 1811020<br>
	 * Message: テンプル ナイト
	 */
	public static final NpcStringId TEMPLE_KNIGHT;

	/**
	 * ID: 1811021<br>
	 * Message: ソード シンガー
	 */
	public static final NpcStringId SWORD_SINGER;

	/**
	 * ID: 1811022<br>
	 * Message: エルヴン スカウト
	 */
	public static final NpcStringId ELVEN_SCOUT;

	/**
	 * ID: 1811023<br>
	 * Message: プレインズ ウォーカー
	 */
	public static final NpcStringId PLAIN_WALKER;

	/**
	 * ID: 1811024<br>
	 * Message: シルバー レンジャー
	 */
	public static final NpcStringId SILVER_RANGER;

	/**
	 * ID: 1811025<br>
	 * Message: エルヴン メイジ
	 */
	public static final NpcStringId ELVEN_MAGE;

	/**
	 * ID: 1811026<br>
	 * Message: エルヴン ウィザード
	 */
	public static final NpcStringId ELVEN_WIZARD;

	/**
	 * ID: 1811027<br>
	 * Message: スペル シンガー
	 */
	public static final NpcStringId SPELL_SINGER;

	/**
	 * ID: 1811028<br>
	 * Message: エレメンタル サマナー
	 */
	public static final NpcStringId ELEMENTAL_SUMMONER;

	/**
	 * ID: 1811029<br>
	 * Message: オラクル
	 */
	public static final NpcStringId ORACLE;

	/**
	 * ID: 1811030<br>
	 * Message: エルダー
	 */
	public static final NpcStringId ELDER;

	/**
	 * ID: 1811031<br>
	 * Message: ダーク ファイター
	 */
	public static final NpcStringId DARK_FIGHTER;

	/**
	 * ID: 1811032<br>
	 * Message: パラス ナイト
	 */
	public static final NpcStringId PALACE_KNIGHT;

	/**
	 * ID: 1811033<br>
	 * Message: シリエン ナイト
	 */
	public static final NpcStringId SHILLIEN_KNIGHT;

	/**
	 * ID: 1811034<br>
	 * Message: ブレイド ダンサー
	 */
	public static final NpcStringId BLADE_DANCER;

	/**
	 * ID: 1811035<br>
	 * Message: アサシン
	 */
	public static final NpcStringId ASSASSIN;

	/**
	 * ID: 1811036<br>
	 * Message: アビス ウォーカー
	 */
	public static final NpcStringId ABYSS_WALKER;

	/**
	 * ID: 1811037<br>
	 * Message: ファントム レンジャー
	 */
	public static final NpcStringId PHANTOM_RANGER;

	/**
	 * ID: 1811038<br>
	 * Message: ダーク メイジ
	 */
	public static final NpcStringId DARK_MAGE;

	/**
	 * ID: 1811039<br>
	 * Message: ダーク ウィザード
	 */
	public static final NpcStringId DARK_WIZARD;

	/**
	 * ID: 1811040<br>
	 * Message: スペル ハウラー
	 */
	public static final NpcStringId SPELLHOWLER;

	/**
	 * ID: 1811041<br>
	 * Message: ファントム サマナー
	 */
	public static final NpcStringId PHANTOM_SUMMONER;

	/**
	 * ID: 1811042<br>
	 * Message: シリエン オラクル
	 */
	public static final NpcStringId SHILLIEN_ORACLE;

	/**
	 * ID: 1811043<br>
	 * Message: シリエン エルダー
	 */
	public static final NpcStringId SHILLIEN_ELDER;

	/**
	 * ID: 1811044<br>
	 * Message: オーク ファイター
	 */
	public static final NpcStringId ORC_FIGHTER;

	/**
	 * ID: 1811045<br>
	 * Message: オーク レイダー
	 */
	public static final NpcStringId ORC_RAIDER;

	/**
	 * ID: 1811046<br>
	 * Message: デストロイヤー
	 */
	public static final NpcStringId DESTROYER;

	/**
	 * ID: 1811047<br>
	 * Message: オーク モンク
	 */
	public static final NpcStringId ORC_MONK;

	/**
	 * ID: 1811048<br>
	 * Message: タイラント
	 */
	public static final NpcStringId TYRANT;

	/**
	 * ID: 1811049<br>
	 * Message: オーク メイジ
	 */
	public static final NpcStringId ORC_MAGE;

	/**
	 * ID: 1811050<br>
	 * Message: オーク シャーマン
	 */
	public static final NpcStringId ORC_SHAMAN;

	/**
	 * ID: 1811051<br>
	 * Message: オーバーロード
	 */
	public static final NpcStringId OVERLORD;

	/**
	 * ID: 1811052<br>
	 * Message: ウォークライアー
	 */
	public static final NpcStringId WARCRYER;

	/**
	 * ID: 1811053<br>
	 * Message: ドワーブン ファイター
	 */
	public static final NpcStringId DWARVEN_FIGHTER;

	/**
	 * ID: 1811054<br>
	 * Message: スカベンジャー
	 */
	public static final NpcStringId SCAVENGER;

	/**
	 * ID: 1811055<br>
	 * Message: バウンティ ハンター
	 */
	public static final NpcStringId BOUNTY_HUNTER;

	/**
	 * ID: 1811056<br>
	 * Message: アルティザン
	 */
	public static final NpcStringId ARTISAN;

	/**
	 * ID: 1811057<br>
	 * Message: ウォースミス
	 */
	public static final NpcStringId WARSMITH;

	/**
	 * ID: 1811088<br>
	 * Message: デュエリスト
	 */
	public static final NpcStringId DUELIST;

	/**
	 * ID: 1811089<br>
	 * Message: ドレッドノート
	 */
	public static final NpcStringId DREADNOUGHT;

	/**
	 * ID: 1811090<br>
	 * Message: フェニックス ナイト
	 */
	public static final NpcStringId PHOENIX_KNIGHT;

	/**
	 * ID: 1811091<br>
	 * Message: ヘル ナイト
	 */
	public static final NpcStringId HELL_KNIGHT;

	/**
	 * ID: 1811092<br>
	 * Message: サジタリウス
	 */
	public static final NpcStringId SAGITTARIUS;

	/**
	 * ID: 1811093<br>
	 * Message: アドベンチャラー
	 */
	public static final NpcStringId ADVENTURER;

	/**
	 * ID: 1811094<br>
	 * Message: アークメイジ
	 */
	public static final NpcStringId ARCHMAGE;

	/**
	 * ID: 1811095<br>
	 * Message: ソウル テイカー
	 */
	public static final NpcStringId SOULTAKER;

	/**
	 * ID: 1811096<br>
	 * Message: アルカナ ロード
	 */
	public static final NpcStringId ARCANA_LORD;

	/**
	 * ID: 1811097<br>
	 * Message: カーディナル
	 */
	public static final NpcStringId CARDINAL;

	/**
	 * ID: 1811098<br>
	 * Message: ハイエロファント
	 */
	public static final NpcStringId HIEROPHANT;

	/**
	 * ID: 1811099<br>
	 * Message: エヴァス テンプラー
	 */
	public static final NpcStringId EVAS_TEMPLAR;

	/**
	 * ID: 1811100<br>
	 * Message: ソード ミューズ
	 */
	public static final NpcStringId SWORD_MUSE;

	/**
	 * ID: 1811101<br>
	 * Message: ウィンド ライダー
	 */
	public static final NpcStringId WIND_RIDER;

	/**
	 * ID: 1811102<br>
	 * Message: ムーンライト センティネル
	 */
	public static final NpcStringId MOONLIGHT_SENTINEL;

	/**
	 * ID: 1811103<br>
	 * Message: ミスティック ミューズ
	 */
	public static final NpcStringId MYSTIC_MUSE;

	/**
	 * ID: 1811104<br>
	 * Message: エレメンタル マスター
	 */
	public static final NpcStringId ELEMENTAL_MASTER;

	/**
	 * ID: 1811105<br>
	 * Message: エヴァス セイント
	 */
	public static final NpcStringId EVAS_SAINT;

	/**
	 * ID: 1811106<br>
	 * Message: シリエン テンプラー
	 */
	public static final NpcStringId SHILLIEN_TEMPLAR;

	/**
	 * ID: 1811107<br>
	 * Message: スペクトラル ダンサー
	 */
	public static final NpcStringId SPECTRAL_DANCER;

	/**
	 * ID: 1811108<br>
	 * Message: ゴースト ハンター
	 */
	public static final NpcStringId GHOST_HUNTER;

	/**
	 * ID: 1811109<br>
	 * Message: ゴースト センティネル
	 */
	public static final NpcStringId GHOST_SENTINEL;

	/**
	 * ID: 1811110<br>
	 * Message: ストーム スクリーマー
	 */
	public static final NpcStringId STORM_SCREAMER;

	/**
	 * ID: 1811111<br>
	 * Message: スペクトラル マスター
	 */
	public static final NpcStringId SPECTRAL_MASTER;

	/**
	 * ID: 1811112<br>
	 * Message: シリエン セイント
	 */
	public static final NpcStringId SHILLIEN_SAINT;

	/**
	 * ID: 1811113<br>
	 * Message: タイタン
	 */
	public static final NpcStringId TITAN;

	/**
	 * ID: 1811114<br>
	 * Message: グランド カバタリ
	 */
	public static final NpcStringId GRAND_KHAVATARI;

	/**
	 * ID: 1811115<br>
	 * Message: ドミネーター
	 */
	public static final NpcStringId DOMINATOR;

	/**
	 * ID: 1811116<br>
	 * Message: ドゥーム クライヤー
	 */
	public static final NpcStringId DOOM_CRYER;

	/**
	 * ID: 1811117<br>
	 * Message: フォーチュン シーカー
	 */
	public static final NpcStringId FORTUNE_SEEKER;

	/**
	 * ID: 1811118<br>
	 * Message: マエストロ
	 */
	public static final NpcStringId MAESTRO;

	/**
	 * ID: 1811123<br>
	 * Message: カマエル ソルジャー
	 */
	public static final NpcStringId KAMAEL_SOLDIER;

	/**
	 * ID: 1811125<br>
	 * Message: トルーパー
	 */
	public static final NpcStringId TROOPER;

	/**
	 * ID: 1811126<br>
	 * Message: ウォーダー
	 */
	public static final NpcStringId WARDER;

	/**
	 * ID: 1811127<br>
	 * Message: バーサーカー
	 */
	public static final NpcStringId BERSERKER;

	/**
	 * ID: 1811128<br>
	 * Message: ソウル ブレーカー
	 */
	public static final NpcStringId SOUL_BREAKER;

	/**
	 * ID: 1811130<br>
	 * Message: アヴァレスタ
	 */
	public static final NpcStringId ARBALESTER;

	/**
	 * ID: 1811131<br>
	 * Message: ドゥーム ブリンガー
	 */
	public static final NpcStringId DOOMBRINGER;

	/**
	 * ID: 1811132<br>
	 * Message: ソウル ハウンド
	 */
	public static final NpcStringId SOUL_HOUND;

	/**
	 * ID: 1811134<br>
	 * Message: トリックスター
	 */
	public static final NpcStringId TRICKSTER;

	/**
	 * ID: 1811135<br>
	 * Message: インスペクター
	 */
	public static final NpcStringId INSPECTOR;

	/**
	 * ID: 1811136<br>
	 * Message: ジュディケーター
	 */
	public static final NpcStringId JUDICATOR;

	/**
	 * ID: 1811137<br>
	 * Message: 誰だ！地竜アンタラス様のご機嫌を損ねるやつは、誰であろうと許さん！
	 */
	public static final NpcStringId WHOS_THERE_IF_YOU_DISTURB_THE_TEMPER_OF_THE_GREAT_LAND_DRAGON_ANTHARAS_I_WILL_NEVER_FORGIVE_YOU;

	/**
	 * ID: 1900000<br>
	 * Message: がははは！クリスマス サンタは俺が捕まえた！今年のプレゼントはお預けだ！
	 */
	public static final NpcStringId KEHAHAHA_I_CAPTURED_SANTA_CLAUS_THERE_WILL_BE_NO_GIFTS_THIS_YEAR;

	/**
	 * ID: 1900003<br>
	 * Message: どうだ！俺の勝ちだな。
	 */
	public static final NpcStringId WELL_I_WIN_RIGHT;

	/**
	 * ID: 1900004<br>
	 * Message: 負け犬は消えうせやがれ！
	 */
	public static final NpcStringId NOW_ALL_OF_YOU_LOSERS_GET_OUT_OF_HERE;

	/**
	 * ID: 1900006<br>
	 * Message: クリスマス サンタを救いに来たようだな。だが、相手が悪かったな。
	 */
	public static final NpcStringId I_GUESS_YOU_CAME_TO_RESCUE_SANTA_BUT_YOU_PICKED_THE_WRONG_OPPONENT;

	/**
	 * ID: 1900007<br>
	 * Message: うっ、やるじゃねえか。
	 */
	public static final NpcStringId OH_NOT_BAD;

	/**
	 * ID: 1900008<br>
	 * Message: これを出すんじゃなかった！
	 */
	public static final NpcStringId AGH_THATS_NOT_WHAT_I_MEANT_TO_DO;

	/**
	 * ID: 1900009<br>
	 * Message: 俺の呪いを受けろ！あ、あれっ？
	 */
	public static final NpcStringId CURSE_YOU_HUH_WHAT;

	/**
	 * ID: 1900015<br>
	 * Message: クリスマス サンタが大人しく捕まってるか見に行くか。ひひっ！
	 */
	public static final NpcStringId SHALL_I_GO_TO_SEE_IF_SANTA_IS_STILL_THERE_HEHE;

	/**
	 * ID: 1900017<br>
	 * Message: クリスマス サンタを助けて来てくれなきゃプレゼントはあげられないよ。
	 */
	public static final NpcStringId SANTA_CAN_GIVE_NICE_PRESENTS_ONLY_IF_HES_RELEASED_FROM_THE_TURKEY;

	/**
	 * ID: 1900018<br>
	 * Message: オッホッホ．．．．みなさん、ご苦労様。お礼は必ずいたします。
	 */
	public static final NpcStringId OH_HO_HO_OH_HO_HO_THANK_YOU_EVERYONE_I_WILL_REPAY_YOU_FOR_SURE;

	/**
	 * ID: 1900019<br>
	 * Message: メリークリスマス！ご苦労様。
	 */
	public static final NpcStringId MERRY_CHRISTMAS_WELL_DONE;

	/**
	 * ID: 1900021<br>
	 * Message: %s、君のためにプレゼントを用意した。
	 */
	public static final NpcStringId S_I_HAVE_PREPARED_A_GIFT_FOR_YOU;

	/**
	 * ID: 1900022<br>
	 * Message: %sにプレゼントがある。
	 */
	public static final NpcStringId I_HAVE_A_GIFT_FOR_S;

	/**
	 * ID: 1900024<br>
	 * Message: インベントリを見てごらん。ビッグなプレゼントが入ってるかもしれないぞ。
	 */
	public static final NpcStringId TAKE_A_LOOK_AT_THE_INVENTORY_PERHAPS_THERE_WILL_BE_A_BIG_PRESENT;

	/**
	 * ID: 1900026<br>
	 * Message: いつまでやるつもりだ！そろそろ飽きてきたぞ。
	 */
	public static final NpcStringId WHEN_ARE_YOU_GOING_TO_STOP_IM_SLOWLY_GETTING_TIRED_OF_THIS;

	/**
	 * ID: 1900027<br>
	 * Message: クリスマス サンタの伝言：わしを救い出してくれた%sに祝福がありますように．．．
	 */
	public static final NpcStringId MESSAGE_FROM_SANTA_CLAUS_MANY_BLESSINGS_TO_S_WHO_SAVED_ME;

	/**
	 * ID: 1900028<br>
	 * Message: 誰だ！私の眠りを妨げるのは。炎の苦痛を与えん！
	 */
	public static final NpcStringId HOW_DARE_YOU_AWAKEN_ME_FEEL_THE_PAIN_OF_THE_FLAMES;

	/**
	 * ID: 1900029<br>
	 * Message: 誰だ！火の威厳に逆らうのは。
	 */
	public static final NpcStringId WHO_DARES_OPPOSE_THE_MAJESTY_OF_FIRE;

	/**
	 * ID: 1900030<br>
	 * Message: い、痛い！そこはだめだ！私のビーズだけは！
	 */
	public static final NpcStringId OH_OUCH_NO_NOT_THERE_NOT_MY_BEAD;

	/**
	 * ID: 1900031<br>
	 * Message: つ、冷たい！冷たいじゃないか！あっあっ。
	 */
	public static final NpcStringId CO_COLD_THATS_COLD_ACK_ACK;

	/**
	 * ID: 1900032<br>
	 * Message: %s、頼むからもう殴らないでくれ．．．頼む。
	 */
	public static final NpcStringId PLEASE_S_DONT_HIT_ME_PLEASE;

	/**
	 * ID: 1900033<br>
	 * Message: くああああっ！恐怖に身を震わせよ！
	 */
	public static final NpcStringId KUAAANNGGG_SHAKE_IN_FEAR;

	/**
	 * ID: 1900034<br>
	 * Message: 俺に下手に手を出したら、大ヤケドするぜ！
	 */
	public static final NpcStringId IF_YOU_ATTACK_ME_RIGHT_NOW_YOURE_REALLY_GOING_TO_GET_IT;

	/**
	 * ID: 1900035<br>
	 * Message: ちょっと待て！俺の必殺技を見せてやろう。
	 */
	public static final NpcStringId JUST_YOU_WAIT_IM_GOING_TO_SHOW_YOU_MY_KILLER_TECHNIQUE;

	/**
	 * ID: 1900036<br>
	 * Message: お前ごときにやられる俺ではない！
	 */
	public static final NpcStringId YOU_DONT_DARE_ATTACK_ME;

	/**
	 * ID: 1900037<br>
	 * Message: 火の精霊とは違うぞ！火の精霊とはな！俺の怒りを食らえ！
	 */
	public static final NpcStringId ITS_DIFFERENT_FROM_THE_SPIRIT_OF_FIRE_ITS_NOT_THE_SPIRIT_OF_FIRE_FEEL_MY_WRATH;

	/**
	 * ID: 1900038<br>
	 * Message: 寒い．．．ここはどこだ．．．俺はこうして死ぬのか．．．
	 */
	public static final NpcStringId COLD_THIS_PLACE_IS_THIS_WHERE_I_DIE;

	/**
	 * ID: 1900039<br>
	 * Message: 体温がどんどん奪われていく．．．グランカインよ．．．申し訳ございません．．．
	 */
	public static final NpcStringId MY_BODY_IS_COOLING_OH_GRAN_KAIN_FORGIVE_ME;

	/**
	 * ID: 1900040<br>
	 * Message: ばかなやつ！俺は素手で攻撃されないとダメージを受けないのだ！
	 */
	public static final NpcStringId IDIOT_I_ONLY_INCUR_DAMAGE_FROM_BARE_HANDED_ATTACKS;

	/**
	 * ID: 1900051<br>
	 * Message: キャンディがなくなった．．．ちぇっ！おもちゃ箱をくれてやるよ！
	 */
	public static final NpcStringId IM_OUT_OF_CANDY_ILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1900052<br>
	 * Message: キャンディがなくなっちゃいました．．．それじゃ、代わりにおもちゃ箱をあげましょう。
	 */
	public static final NpcStringId SINCE_IM_OUT_OF_CANDY_I_WILL_GIVE_YOU_MY_TOY_CHEST_INSTEAD;

	/**
	 * ID: 1900060<br>
	 * Message: ホントにすごいですね。次はものすごいプレゼントをかけますよ。期待してくださいね。
	 */
	public static final NpcStringId YOURE_TOO_GOOD_NEXT_TIME_ILL_GIVE_YOU_AN_INCREDIBLE_GIFT_PLEASE_WAIT_FOR_IT;

	/**
	 * ID: 1900062<br>
	 * Message: ここで負けたら恥ずかしいなぁ．．．
	 */
	public static final NpcStringId I_WOULD_BE_EMBARRASSED_TO_LOSE_AGAIN_HERE;

	/**
	 * ID: 1900070<br>
	 * Message: プロでも難しい14勝を！次は私の宝物、ゴールデン ジャック オ ランタンをかけますよ！
	 */
	public static final NpcStringId EVEN_PROS_CANT_DO_14_WINS_IN_A_ROW_NEXT_TIME_ILL_GIVE_YOU_MY_TREASURE_THE_GOLDEN_JACK_OLANTERN;

	/**
	 * ID: 1900071<br>
	 * Message: もうだめだ！降参だ！あたしの583年の人生でお前が最高だよ！
	 */
	public static final NpcStringId I_CANT_DO_THIS_ANYMORE_YOU_WIN_IN_ALL_MY_583_YEARS_YOURE_THE_BEST_THAT_IVE_SEEN;

	/**
	 * ID: 1900081<br>
	 * Message: %s様はジャック ゲームで%s連勝達成です！
	 */
	public static final NpcStringId S_HAS_WON_S_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1900082<br>
	 * Message: おめでとうございます！%s様はジャック ゲームで%s連勝達成です！
	 */
	public static final NpcStringId CONGRATULATIONS_S_HAS_WON_S_JACKS_GAMES_IN_A_ROW;

	/**
	 * ID: 1900084<br>
	 * Message: どもども、ベルダです。ジャック ゲーム1位、おめでとうございます！村にいる弟のクルドから衝撃！のプレゼントがもらえますよ。それじゃ、またゲームしにきてくださいね。
	 */
	public static final NpcStringId HELLO_IM_BELLDANDY_CONGRATULATIONS_ON_GETTING_1ST_PLACE_IN_JACKS_GAME_IF_YOU_GO_AND_FIND_MY_SIBLING_SKOOLDIE_IN_THE_VILLAGE_YOULL_GET_AN_AMAZING_GIFT_LETS_PLAY_JACKS_GAME_AGAIN;

	/**
	 * ID: 1900104<br>
	 * Message: むにゃむにゃ．．．こっ、こんにちは！いい天気だねぇ．．．
	 */
	public static final NpcStringId YAWN_AHH_HELLO_NICE_WEATHER_WERE_HAVING;

	/**
	 * ID: 1900105<br>
	 * Message: おなかすいた．．．ねえ、離乳食ないの？あれがなきゃ大きくなれないよぉ。
	 */
	public static final NpcStringId AH_IM_HUNGRY_DO_YOU_HAVE_ANY_BABY_FOOD_THATS_WHAT_I_NEED_TO_EAT_TO_GET_BIGGER;

	/**
	 * ID: 1900106<br>
	 * Message: 早く大きくなってサンタさんのそりをひきたいんだ。
	 */
	public static final NpcStringId I_GOTTA_GROW_UP_FAST_I_WANT_TO_PULL_SANTAS_SLED_TOO;

	/**
	 * ID: 1900107<br>
	 * Message: うま～い！これで大きくなれるよ！
	 */
	public static final NpcStringId YUMMY_I_THINK_I_CAN_GROW_UP_NOW;

	/**
	 * ID: 1900108<br>
	 * Message: おかげで少年トナカイになれたよ！
	 */
	public static final NpcStringId THANKS_TO_YOU_I_GREW_UP_INTO_A_BOY_RUDOLPH;

	/**
	 * ID: 1900109<br>
	 * Message: 今日はそり曳き日和だねぇ。
	 */
	public static final NpcStringId ITS_GREAT_WEATHER_FOR_RUNNING_AROUND;

	/**
	 * ID: 1900110<br>
	 * Message: 僕は大きくなったら何になるのかなぁ．．．
	 */
	public static final NpcStringId WHATLL_I_BE_WHEN_I_GROW_UP_I_WONDER;

	/**
	 * ID: 1900111<br>
	 * Message: 僕をちゃんと育ててくれたら、恩返しするからね！
	 */
	public static final NpcStringId IF_YOU_TAKE_GOOD_CARE_OF_ME_ILL_NEVER_FORGET_IT;

	/**
	 * ID: 1900112<br>
	 * Message: 僕をなでなでして！アクション タブにある温かな手スキルを使えばいいんだよ。
	 */
	public static final NpcStringId PLEASE_PET_ME_LOVINGLY_YOU_CAN_USE_THE_HAND_OF_WARMTH_SKILL_UNDER_THE_ACTION_TAB;

	/**
	 * ID: 1900113<br>
	 * Message: 気持ちいい！ありがとう！
	 */
	public static final NpcStringId I_FEEL_GREAT_THANK_YOU;

	/**
	 * ID: 1900114<br>
	 * Message: ううう～ん、気持ちいい！すくすく育つよ！
	 */
	public static final NpcStringId WOO_WHAT_A_GOOD_FEELING_I_GOTTA_GROW_MORE_AND_MORE;

	/**
	 * ID: 1900115<br>
	 * Message: おいしい！ずっと食べたらすぐにでも大きくなりそう。
	 */
	public static final NpcStringId OH_YUMMY_IF_I_KEEP_EATING_THIS_I_THINK_I_CAN_GROW_UP_ALL_THE_WAY;

	/**
	 * ID: 1900116<br>
	 * Message: むしゃむしゃ。おいしい！もっとちょうだい！
	 */
	public static final NpcStringId YUM_YUM_DELICIOUS_GIVE_ME_MORE_OF_THIS;

	/**
	 * ID: 1900117<br>
	 * Message: こ、これは．．．お口の中で繰り広げられる味の攻城戦や～
	 */
	public static final NpcStringId WOW_THIS_TASTE_THERES_A_WHOLE_NEW_WORLD_IN_MY_MOUTH;

	/**
	 * ID: 1900118<br>
	 * Message: うまい、うますぎる！これがあの星の餌か！
	 */
	public static final NpcStringId YEAH_ITS_SO_DELICIOUS_THIS_IS_THAT_STAR_FOOD;

	/**
	 * ID: 1900119<br>
	 * Message: 僕をもっと愛してよ～ちぇっ！
	 */
	public static final NpcStringId PAY_SOME_ATTENTION_TO_ME_HMPH;

	/**
	 * ID: 1900120<br>
	 * Message: ありがとう！おかげで無事に大きくなれたよ。はい、僕からのプレゼント！
	 */
	public static final NpcStringId THANK_YOU_I_WAS_ABLE_TO_GROW_UP_INTO_AN_ADULT_HERE_IS_MY_GIFT;

	/**
	 * ID: 1900121<br>
	 * Message: ありがとう、%s！これで僕もそりをひけるよ。
	 */
	public static final NpcStringId THANK_YOU_S_NOW_I_CAN_PULL_THE_SLED;

	/**
	 * ID: 1900122<br>
	 * Message: %s、長い間お世話になりました。楽しかったよ！
	 */
	public static final NpcStringId S_THANK_YOU_FOR_TAKING_CARE_OF_ME_ALL_THIS_TIME_I_ENJOYED_IT_VERY_MUCH;

	/**
	 * ID: 1900123<br>
	 * Message: %s、そろそろそりを曳きに行かなきゃ。さみしくなるなぁ。
	 */
	public static final NpcStringId S_IT_WONT_BE_LONG_NOW_UNTIL_IT_BECOMES_TIME_TO_PULL_THE_SLED_ITS_TOO_BAD;

	/**
	 * ID: 1900124<br>
	 * Message: 僕はサンタさんのところに戻るよ。今までありがとう！
	 */
	public static final NpcStringId I_MUST_RETURN_TO_SANTA_NOW_THANK_YOU_FOR_EVERYTHING;

	/**
	 * ID: 1900125<br>
	 * Message: こんにちは。わたし、フルドル族の少女。おかげで元の姿に戻れたよ。
	 */
	public static final NpcStringId HELLO_IM_A_GIRL_RUDOLPH_I_WAS_ABLE_TO_FIND_MY_TRUE_SELF_THANKS_TO_YOU;

	/**
	 * ID: 1900126<br>
	 * Message: これは僕からの感謝のプレゼント。今まで育ててくれてありがとう！
	 */
	public static final NpcStringId THIS_IS_MY_GIFT_OF_THANKS_THANK_YOU_FOR_TAKING_CARE_OF_ME;

	/**
	 * ID: 1900127<br>
	 * Message: %s、いつもありがとう！
	 */
	public static final NpcStringId S_I_WAS_ALWAYS_GRATEFUL;

	/**
	 * ID: 1900128<br>
	 * Message: 名残惜しいけど、そろそろ行かなきゃ。
	 */
	public static final NpcStringId IM_A_LITTLE_SAD_ITS_TIME_TO_LEAVE_NOW;

	/**
	 * ID: 1900129<br>
	 * Message: %s、僕はそろそろ故郷に帰らなきゃ。
	 */
	public static final NpcStringId S_THE_TIME_HAS_COME_FOR_ME_TO_RETURN_TO_MY_HOME;

	/**
	 * ID: 1900130<br>
	 * Message: %s、ありがとう！
	 */
	public static final NpcStringId S_THANK_YOU;

	/**
	 * ID: 1900131<br>
	 * Message: はははは！サンタクロースは俺が捕まえた！あれっ？ここはどこ？私はだれ？
	 */
	public static final NpcStringId HAHAHA_I_CAPTURED_SANTA_CLAUS_HUH_WHERE_IS_THIS_WHO_ARE_YOU;

	/**
	 * ID: 1900132<br>
	 * Message: 俺はじゃんけんに負けて捕まってるんだ．．．くそっ！お前に八つ当たりしてやる！あ、あれっ？
	 */
	public static final NpcStringId I_LOST_AT_ROCK_PAPER_SCISSORS_AND_WAS_TAKEN_CAPTIVE_I_MIGHT_AS_WELL_TAKE_OUT_MY_ANGER_ON_YOU_HUH_WHAT;

	/**
	 * ID: 1900133<br>
	 * Message: やることなすこと全部失敗．．．さらばだ！サンタは俺が捕まえてやるからな。見てろよ！
	 */
	public static final NpcStringId NOTHINGS_WORKING_IM_LEAVING_ILL_DEFINITELY_CAPTURE_SANTA_AGAIN_JUST_YOU_WAIT;

	/**
	 * ID: 1900134<br>
	 * Message: トナカイを早く育てなければ．．．今年のクリスマスプレゼントはお預けだ．．．
	 */
	public static final NpcStringId I_MUST_RAISE_RUDOLPH_QUICKLY_THIS_YEARS_CHRISTMAS_GIFTS_HAVE_TO_BE_DELIVERED;

	/**
	 * ID: 1900135<br>
	 * Message: メリークリスマス！おまえさんかね、トナカイを育ててくれたのは。おかげでプレゼントの配達はうまくいったよ。
	 */
	public static final NpcStringId MERRY_CHRISTMAS_THANKS_TO_YOUR_EFFORTS_IN_RAISING_RUDOLPH_THE_GIFT_DELIVERY_WAS_A_SUCCESS;

	/**
	 * ID: 1900136<br>
	 * Message: あと10分で、僕を育てだしてから1時間だよ。
	 */
	public static final NpcStringId IN_10_MINUTES_IT_WILL_BE_1_HOUR_SINCE_YOU_STARTED_RAISING_ME;

	/**
	 * ID: 1900137<br>
	 * Message: 5分後に、お腹ふくれ度と愛情度が99%になったら、おとなになれるよ。
	 */
	public static final NpcStringId AFTER_5_MINUTES_IF_MY_FULL_FEELING_AND_AFFECTION_LEVEL_REACH_99_I_CAN_GROW_BIGGER;

	/**
	 * ID: 1900139<br>
	 * Message: ロッフィー！僕はアデナが大好きな精霊のロッフィーロッフィーだよ～
	 */
	public static final NpcStringId LUCKY_IM_LUCKY_THE_SPIRIT_THAT_LOVES_ADENA;

	/**
	 * ID: 1900140<br>
	 * Message: ロッフィー！アデナ食べたい．．．アデナちょうだいよ！
	 */
	public static final NpcStringId LUCKY_I_WANT_TO_EAT_ADENA_GIVE_IT_TO_ME;

	/**
	 * ID: 1900141<br>
	 * Message: ロッフィー！アデナを食べ過ぎると翼がなくなるんだよね…
	 */
	public static final NpcStringId LUCKY_IF_I_EAT_TOO_MUCH_ADENA_MY_WINGS_DISAPPEAR;

	/**
	 * ID: 1900142<br>
	 * Message: ロッフィーおいしい！ありがと～！
	 */
	public static final NpcStringId YUMMY_THANKS_LUCKY;

	/**
	 * ID: 1900143<br>
	 * Message: うげぇぇ．．．ま、まずいよぉ．．．
	 */
	public static final NpcStringId GRRRR_YUCK;

	/**
	 * ID: 1900144<br>
	 * Message: ロッフィー！アデナすごくおいしい！体大きくなる！
	 */
	public static final NpcStringId LUCKY_THE_ADENA_IS_SO_YUMMY_IM_GETTING_BIGGER;

	/**
	 * ID: 1900145<br>
	 * Message: ロッフィー！もうアデナは終わりだよね？ふぅ．．．体が重いよ。
	 */
	public static final NpcStringId LUCKY_NO_MORE_ADENA_OH_IM_SO_HEAVY;

	/**
	 * ID: 1900146<br>
	 * Message: ロッフィー！お腹いっぱいだよぉ～！もう食べられないってば！
	 */
	public static final NpcStringId LUCKY_IM_FULL_THANKS_FOR_THE_YUMMY_ADENA_OH_IM_SO_HEAVY;

	/**
	 * ID: 1900147<br>
	 * Message: ロッフィー！アデナ少なすぎ．．．最低でも%s以上はないとね！
	 */
	public static final NpcStringId LUCKY_IT_WASNT_ENOUGH_ADENA_ITS_GOTTA_BE_AT_LEAST_S;

	/**
	 * ID: 1900148<br>
	 * Message: あっ、翼がなくなっちゃった！．．．殴るの？もし殴ったら食べたもの吐き出しちゃうから！
	 */
	public static final NpcStringId OH_MY_WINGS_DISAPPEARED_ARE_YOU_GONNA_HIT_ME_IF_YOU_HIT_ME_ILL_THROW_UP_EVERYTHING_THAT_I_ATE;

	/**
	 * ID: 1900149<br>
	 * Message: あっ、翼が．．．ひっひぃ！な、殴るの！？怖いよー！怖いよー！そんなことしたら大変なことになるよ！
	 */
	public static final NpcStringId OH_MY_WINGS_ACK_ARE_YOU_GONNA_HIT_ME_SCARY_SCARY_IF_YOU_HIT_ME_SOMETHING_BAD_WILL_HAPPEN;

	/**
	 * ID: 1900150<br>
	 * Message: 栄光の勇者たちが邪悪な地竜アンタラスを倒しました！
	 */
	public static final NpcStringId THE_EVIL_LAND_DRAGON_ANTHARAS_HAS_BEEN_DEFEATED;

	/**
	 * ID: 1900151<br>
	 * Message: 栄光の勇者たちが邪悪な火竜ヴァラカスを倒しました！
	 */
	public static final NpcStringId THE_EVIL_FIRE_DRAGON_VALAKAS_HAS_BEEN_DEFEATED;

	/**
	 * ID: 1900152<br>
	 * Message: 今からでも遅くはありません。あの方に仕えれば最悪の事態は免れます。
	 */
	public static final NpcStringId TO_SERVE_HIM_NOW_MEANS_YOU_WILL_BE_ABLE_TO_ESCAPE_A_WORSE_SITUATION;

	/**
	 * ID: 1900153<br>
	 * Message: 破滅の女神よ、我々を許したまえ．．．
	 */
	public static final NpcStringId OH_GODDESS_OF_DESTRUCTION_FORGIVE_US;

	/**
	 * ID: 1900154<br>
	 * Message: 空が赤く染まり、大地が腐敗した時、闇の中からあのお方が戻ってこられる。
	 */
	public static final NpcStringId WHEN_THE_SKY_TURNS_BLOOD_RED_AND_THE_EARTH_BEGINS_TO_CRUMBLE_FROM_THE_DARKNESS_SHE_WILL_RETURN;

	/**
	 * ID: 1900155<br>
	 * Message: アンタラスの足元に大地の気が集まります。
	 */
	public static final NpcStringId EARTH_ENERGY_IS_GATHERING_NEAR_ANTHARASS_LEGS;

	/**
	 * ID: 1900156<br>
	 * Message: アンタラスが大地の気を吸収し始めます。
	 */
	public static final NpcStringId ANTHARAS_STARTS_TO_ABSORB_THE_EARTH_ENERGY;

	/**
	 * ID: 1900157<br>
	 * Message: アンタラスが太い尻尾をはね上げます。
	 */
	public static final NpcStringId ANTHARAS_RAISES_ITS_THICK_TAIL;

	/**
	 * ID: 1900158<br>
	 * Message: アンタラスの体から威圧感を感じます。
	 */
	public static final NpcStringId YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_ANTHARAS;

	/**
	 * ID: 1900159<br>
	 * Message: アンタラスが狂気を帯びた目をしています。
	 */
	public static final NpcStringId ANTHARASS_EYES_ARE_FILLED_WITH_RAGE;

	/**
	 * ID: 1900160<br>
	 * Message: $s1、君からはあのお方の気を感じる。
	 */
	public static final NpcStringId S1_I_CAN_FEEL_THEIR_PRESENCE_FROM_YOU;

	/**
	 * ID: 1900161<br>
	 * Message: $s1、兄弟よ、俺の横で一緒に祈ってくれ。
	 */
	public static final NpcStringId S1_BRETHREN_COME_TO_MY_SIDE_AND_FOLLOW_ME;

	/**
	 * ID: 1900162<br>
	 * Message: アンタラスが咆哮します。
	 */
	public static final NpcStringId ANTHARAS_ROARS;

	/**
	 * ID: 1900163<br>
	 * Message: ヴァラカスに火炎の魔力が集まります。
	 */
	public static final NpcStringId FLAME_ENERGY_IS_BEING_DIRECTED_TOWARDS_VALAKAS;

	/**
	 * ID: 1900164<br>
	 * Message: ヴァラカスから威圧感を感じます。
	 */
	public static final NpcStringId YOU_ARE_OVERCOME_BY_THE_STRENGTH_OF_VALAKAS;

	/**
	 * ID: 1900165<br>
	 * Message: ヴァラカスが尻尾を威嚇するように動かします。
	 */
	public static final NpcStringId VALAKASS_TAIL_FLAILS_DANGEROUSLY;

	/**
	 * ID: 1900166<br>
	 * Message: ヴァラカスが尻尾をはね上げます。
	 */
	public static final NpcStringId VALAKAS_RAISES_ITS_TAIL;

	/**
	 * ID: 1900167<br>
	 * Message: ヴァラカスが火炎の気を吸収し始めます。
	 */
	public static final NpcStringId VALAKAS_STARTS_TO_ABSORB_THE_FLAME_ENERGY;

	/**
	 * ID: 1900168<br>
	 * Message: ヴァラカスが自分の左側を睨みつけます。
	 */
	public static final NpcStringId VALAKAS_LOOKS_TO_ITS_LEFT;

	/**
	 * ID: 1900169<br>
	 * Message: ヴァラカスが自分の右側を睨みつけます。
	 */
	public static final NpcStringId VALAKAS_LOOKS_TO_ITS_RIGHT;

	/**
	 * ID: 1900170<br>
	 * Message: 我が権能にて命ずる、創造物よ、粉となれ。
	 */
	public static final NpcStringId BY_MY_AUTHORITY_I_COMMAND_YOU_CREATURE_TURN_TO_DUST;

	/**
	 * ID: 1900171<br>
	 * Message: 我が怒りをこめて命ずる、創造物よ、精神を乱せ。
	 */
	public static final NpcStringId BY_MY_WRATH_I_COMMAND_YOU_CREATURE_LOSE_YOUR_MIND;

	/**
	 * ID: 1900172<br>
	 * Message: 邪悪な竜を倒し、アデン ワールドを守った英雄たちに称賛を送れ！
	 */
	public static final NpcStringId SHOW_RESPECT_TO_THE_HEROES_WHO_DEFEATED_THE_EVIL_DRAGON_AND_PROTECTED_THIS_ADEN_WORLD;

	/**
	 * ID: 1900173<br>
	 * Message: 英雄たちの勝利を祝い、全員で勝利の雄叫びをあげよ！
	 */
	public static final NpcStringId SHOUT_TO_CELEBRATE_THE_VICTORY_OF_THE_HEROES;

	/**
	 * ID: 1900174<br>
	 * Message: 英雄たちの業績を讃え、ネビトの祝福を受けよ！
	 */
	public static final NpcStringId PRAISE_THE_ACHIEVEMENT_OF_THE_HEROES_AND_RECEIVE_NEVITS_BLESSING;

	/**
	 * ID: 1900175<br>
	 * Message: うう．．．私もここまでか．．．
	 */
	public static final NpcStringId UGH_I_THINK_THIS_IS_IT_FOR_ME;

	/**
	 * ID: 1900176<br>
	 * Message: ヴァラカスが召喚獣の召喚者を強制的に引き寄せます。
	 */
	public static final NpcStringId VALAKAS_CALLS_FORTH_THE_SERVITORS_MASTER;

	/**
	 * ID: 1911111<br>
	 * Message: お前はまもなく我々の生贄だ。お前が儀面と罪悪にまみれたと軽蔑してやまない我々のな！
	 */
	public static final NpcStringId YOU_WILL_SOON_BECOME_THE_SACRIFICE_FOR_US_THOSE_FULL_OF_DECEIT_AND_SIN_WHOM_YOU_DESPISE;

	/**
	 * ID: 1911112<br>
	 * Message: 俺の同族がお前を罰する。当然、俺よりも強いやつだ。血を吐いて泣き叫ぶお前の姿が見たいものだ。
	 */
	public static final NpcStringId MY_BRETHREN_WHO_ARE_STRONGER_THAN_ME_WILL_PUNISH_YOU_YOU_WILL_SOON_BE_COVERED_IN_YOUR_OWN_BLOOD_AND_CRYING_IN_ANGUISH;

	/**
	 * ID: 1911113<br>
	 * Message: この俺が魔物ごときにやられるとは．．．ぐうっ．．．
	 */
	public static final NpcStringId HOW_COULD_I_LOSE_AGAINST_THESE_WORTHLESS_CREATURES;

	/**
	 * ID: 1911114<br>
	 * Message: 地獄の烈火の中でお前を待っている！
	 */
	public static final NpcStringId FOOLISH_CREATURES_THE_FLAMES_OF_HELL_ARE_DRAWING_CLOSER;

	/**
	 * ID: 1911115<br>
	 * Message: いくらあげこうとも、この地がお前の血で赤く染まるのは時間の問題だ。
	 */
	public static final NpcStringId NO_MATTER_HOW_YOU_STRUGGLE_THIS_PLACE_WILL_SOON_BE_COVERED_WITH_YOUR_BLOOD;

	/**
	 * ID: 1911116<br>
	 * Message: この地に足を踏み入れた者は生きて帰れぬ！
	 */
	public static final NpcStringId THOSE_WHO_SET_FOOT_IN_THIS_PLACE_SHALL_NOT_LEAVE_ALIVE;

	/**
	 * ID: 1911117<br>
	 * Message: 魔物風情が！地獄の烈火で永遠の苦しみを味わえ！
	 */
	public static final NpcStringId WORTHLESS_CREATURES_I_WILL_GRANT_YOU_ETERNAL_SLEEP_IN_FIRE_AND_BRIMSTONE;

	/**
	 * ID: 1911118<br>
	 * Message: そんなに地獄巡りがしたいのか！望みどおりにしてやろう！
	 */
	public static final NpcStringId IF_YOU_WISH_TO_SEE_HELL_I_WILL_GRANT_YOU_YOUR_WISH;

	/**
	 * ID: 1911119<br>
	 * Message: 経過時間：
	 */
	public static final NpcStringId ELAPSED_TIME;

	/**
	 * ID: 1911120<br>
	 * Message: 残り時間：
	 */
	public static final NpcStringId TIME_REMAINING;

	/**
	 * ID: 10004431<br>
	 * Message: アデン王国の城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_IMPERIAL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004432<br>
	 * Message: グルーディオ城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GLUDIO_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004433<br>
	 * Message: ディオン城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_DION_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004434<br>
	 * Message: ギラン城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GIRAN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004436<br>
	 * Message: アデン城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_ADEN_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004437<br>
	 * Message: インナドリル城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_INNADRIL_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004438<br>
	 * Message: ゴダード城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_GODDARD_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004439<br>
	 * Message: ルウン城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_RUNE_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004440<br>
	 * Message: シュチュッツガルト城の守備メンバーを内郭にテレポートで送ります。
	 */
	public static final NpcStringId IT_TELEPORTS_THE_GUARD_MEMBERS_OF_THE_SCHUTTGART_CASTLE_TO_THE_INSIDE_OF_THE_CASTLE;

	/**
	 * ID: 10004441<br>
	 * Message: エルモア王国の城の守備メンバーを内郭にテレポートで送ります。
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
		S1_MINUTES_REMAINING = new NpcStringId(1010643);
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
		S1_SECONDS_REMAINING = new NpcStringId(1800079);
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
		DETONATOR_INITIALIZATION_TIME_S1_MINUTES_FROM_NOW = new NpcStringId(1800117);
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
	
	private static final void buildFastLookupTable()
	{
		final Field[] fields = NpcStringId.class.getDeclaredFields();
		final ArrayList<NpcStringId> nsIds = new ArrayList<>(fields.length);
		
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
	
	protected static final Builder newBuilder(final String text)
	{
		final ArrayList<Builder> builders = new ArrayList<>();
		
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
		return new BuilderContainer(builders.toArray(new Builder[builders.size()]));
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