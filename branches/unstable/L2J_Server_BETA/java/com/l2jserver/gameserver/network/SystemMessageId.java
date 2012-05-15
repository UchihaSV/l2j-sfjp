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
 * @author Noctarius & Nille02 & crion ^ Forsaiken
 */
public final class SystemMessageId
{
	private static final Logger _log = Logger.getLogger(SystemMessageId.class.getName());
	private static final SMLocalisation[] EMPTY_SML_ARRAY = new SMLocalisation[0];
	public static final SystemMessageId[] EMPTY_ARRAY = new SystemMessageId[0];
	
	/**
	 * ID: 0<br>
	 * Message: サーバーとの接続が切断されました。
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_DISCONNECTED;
	
	/**
	 * ID: 1<br>
	 * Message: サーバーが$s1秒後に中断されます。ゲームを終了してください。
	 */
	public static final SystemMessageId THE_SERVER_WILL_BE_COMING_DOWN_IN_S1_SECONDS;
	
	/**
	 * ID: 2<br>
	 * Message: $s1は存在しないユーザーです。
	 */
	public static final SystemMessageId S1_DOES_NOT_EXIST;
	
	/**
	 * ID: 3<br>
	 * Message: $s1はゲームに接続していません。
	 */
	public static final SystemMessageId S1_IS_NOT_ONLINE;
	
	/**
	 * ID: 4<br>
	 * Message: 自分自身に血盟加入申請をすることはできません。
	 */
	public static final SystemMessageId CANNOT_INVITE_YOURSELF;
	
	/**
	 * ID: 5<br>
	 * Message: $s1はすでに存在している血盟です。
	 */
	public static final SystemMessageId S1_ALREADY_EXISTS;
	
	/**
	 * ID: 6<br>
	 * Message: $s1は存在する血盟ではありません。
	 */
	public static final SystemMessageId S1_DOES_NOT_EXIST2;
	
	/**
	 * ID: 7<br>
	 * Message: すでに$s1血盟に所属しています。
	 */
	public static final SystemMessageId ALREADY_MEMBER_OF_S1;
	
	/**
	 * ID: 8<br>
	 * Message: 血盟に関する他の作業中です。
	 */
	public static final SystemMessageId YOU_ARE_WORKING_WITH_ANOTHER_CLAN;
	
	/**
	 * ID: 9<br>
	 * Message: $s1は血盟主ではありません。
	 */
	public static final SystemMessageId S1_IS_NOT_A_CLAN_LEADER;
	
	/**
	 * ID: 10<br>
	 * Message: $s1は血盟に関する他の作業中です。
	 */
	public static final SystemMessageId S1_WORKING_WITH_ANOTHER_CLAN;
	
	/**
	 * ID: 11<br>
	 * Message: 血盟の加入申請者がいません。
	 */
	public static final SystemMessageId NO_APPLICANTS_FOR_THIS_CLAN;
	
	/**
	 * ID: 12<br>
	 * Message: 血盟の加入申請者の情報が不正確です。
	 */
	public static final SystemMessageId APPLICANT_INFORMATION_INCORRECT;
	
	/**
	 * ID: 13<br>
	 * Message: 攻城戦への参加を申請しているため、解散できません。
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_CAUSE_CLAN_WILL_PARTICIPATE_IN_CASTLE_SIEGE;
	
	/**
	 * ID: 14<br>
	 * Message: 城またはアジトを所有しているため、解散できません。
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_CAUSE_CLAN_OWNS_CASTLES_HIDEOUTS;
	
	/**
	 * ID: 15<br>
	 * Message: 攻城戦中です。
	 */
	public static final SystemMessageId YOU_ARE_IN_SIEGE;
	
	/**
	 * ID: 16<br>
	 * Message: 攻城戦中ではありません。
	 */
	public static final SystemMessageId YOU_ARE_NOT_IN_SIEGE;
	
	/**
	 * ID: 17<br>
	 * Message: 攻城戦が始まりました。
	 */
	public static final SystemMessageId CASTLE_SIEGE_HAS_BEGUN;
	
	/**
	 * ID: 18<br>
	 * Message: 攻城戦が終了しました。
	 */
	public static final SystemMessageId CASTLE_SIEGE_HAS_ENDED;
	
	/**
	 * ID: 19<br>
	 * Message: 城主が替わりました！
	 */
	public static final SystemMessageId NEW_CASTLE_LORD;
	
	/**
	 * ID: 20<br>
	 * Message: 門が開かれます。
	 */
	public static final SystemMessageId GATE_IS_OPENING;
	
	/**
	 * ID: 21<br>
	 * Message: 門が破壊されます。
	 */
	public static final SystemMessageId GATE_IS_DESTROYED;
	
	/**
	 * ID: 22<br>
	 * Message: 相手が離れ過ぎています。
	 */
	public static final SystemMessageId TARGET_TOO_FAR;
	
	/**
	 * ID: 23<br>
	 * Message: HPが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_HP;
	
	/**
	 * ID: 24<br>
	 * Message: MPが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_MP;
	
	/**
	 * ID: 25<br>
	 * Message: HPが回復されます。
	 */
	public static final SystemMessageId REJUVENATING_HP;
	
	/**
	 * ID: 26<br>
	 * Message: MPが回復されます。
	 */
	public static final SystemMessageId REJUVENATING_MP;
	
	/**
	 * ID: 27<br>
	 * Message: 妨害を受け、詠唱が中断されました。
	 */
	public static final SystemMessageId CASTING_INTERRUPTED;
	
	/**
	 * ID: 28<br>
	 * Message: $s1アデナを手に入れました。
	 */
	public static final SystemMessageId YOU_PICKED_UP_S1_ADENA;
	
	/**
	 * ID: 29<br>
	 * Message: $s1 $s2個を手に入れました。
	 */
	public static final SystemMessageId YOU_PICKED_UP_S1_S2;
	
	/**
	 * ID: 30<br>
	 * Message: $s1を手に入れました。
	 */
	public static final SystemMessageId YOU_PICKED_UP_S1;
	
	/**
	 * ID: 31<br>
	 * Message: 座った状態では行動できません。
	 */
	public static final SystemMessageId CANT_MOVE_SITTING;
	
	/**
	 * ID: 32<br>
	 * Message: 戦闘不能に陥りました。最寄りのリスタート ポイントに移動します。
	 */
	public static final SystemMessageId UNABLE_COMBAT_PLEASE_GO_RESTART;
	
	/**
	 * ID: 32<br>
	 * Message: 戦闘不能に陥りました。最寄りのリスタート ポイントに移動します。
	 */
	public static final SystemMessageId CANT_MOVE_CASTING;
	
	/**
	 * ID: 34<br>
	 * Message: リネージュIIの世界へようこそ！
	 */
	public static final SystemMessageId WELCOME_TO_LINEAGE;
	
	/**
	 * ID: 35<br>
	 * Message: $s1のダメージを与えました。
	 */
	public static final SystemMessageId YOU_DID_S1_DMG;
	
	/**
	 * ID: 36<br>
	 * Message: $c1から$s2のダメージを受けました。
	 */
	public static final SystemMessageId C1_GAVE_YOU_S2_DMG;
	
	/**
	 * ID: 37<br>
	 * Message: $c1によって$s2のダメージを受けました。
	 */
	public static final SystemMessageId C1_GAVE_YOU_S2_DMG2;
	
	/**
	 * ID: 41<br>
	 * Message: 矢の発射準備中です。
	 */
	public static final SystemMessageId GETTING_READY_TO_SHOOT_AN_ARROW;
	
	/**
	 * ID: 42<br>
	 * Message: $c1の攻撃を避けました。
	 */
	public static final SystemMessageId AVOIDED_C1_ATTACK;
	
	/**
	 * ID: 43<br>
	 * Message: 攻撃が外れました。
	 */
	public static final SystemMessageId MISSED_TARGET;
	
	/**
	 * ID: 44<br>
	 * Message: クリティカル ヒット！
	 */
	public static final SystemMessageId CRITICAL_HIT;
	
	/**
	 * ID: 45<br>
	 * Message: $s1の経験値を得ました。
	 */
	public static final SystemMessageId EARNED_S1_EXPERIENCE;
	
	/**
	 * ID: 46<br>
	 * Message: $s1を使用します。
	 */
	public static final SystemMessageId USE_S1;
	
	/**
	 * ID: 47<br>
	 * Message: $s1の使用中です。
	 */
	public static final SystemMessageId BEGIN_TO_USE_S1;
	
	/**
	 * ID: 48<br>
	 * Message: $s1は再使用準備中であるため、使用できません。
	 */
	public static final SystemMessageId S1_PREPARED_FOR_REUSE;
	
	/**
	 * ID: 49<br>
	 * Message: $s1を装備しました。
	 */
	public static final SystemMessageId S1_EQUIPPED;
	
	/**
	 * ID: 50<br>
	 * Message: ターゲットが指定されていません。
	 */
	public static final SystemMessageId TARGET_CANT_FOUND;
	
	/**
	 * ID: 51<br>
	 * Message: 自分自身には使用できません。
	 */
	public static final SystemMessageId CANNOT_USE_ON_YOURSELF;
	
	/**
	 * ID: 52<br>
	 * Message: $s1アデナを得ました。
	 */
	public static final SystemMessageId EARNED_S1_ADENA;
	
	/**
	 * ID: 53<br>
	 * Message: $s1 $s2個を得ました。
	 */
	public static final SystemMessageId EARNED_S2_S1_S;
	
	/**
	 * ID: 54<br>
	 * Message: $s1を得ました。
	 */
	public static final SystemMessageId EARNED_ITEM_S1;
	
	/**
	 * ID: 55<br>
	 * Message: $s1アデナの取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_PICKUP_S1_ADENA;
	
	/**
	 * ID: 56<br>
	 * Message: $s1の取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_PICKUP_S1;
	
	/**
	 * ID: 57<br>
	 * Message: $s1 $s2個の取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_PICKUP_S2_S1_S;
	
	/**
	 * ID: 58<br>
	 * Message: $s1アデナの取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_EARN_S1_ADENA;
	
	/**
	 * ID: 59<br>
	 * Message: $s1の取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_EARN_S1;
	
	/**
	 * ID: 60<br>
	 * Message: $s1 $s2個の取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_EARN_S2_S1_S;
	
	/**
	 * ID: 61<br>
	 * Message: 何も起こりませんでした。
	 */
	public static final SystemMessageId NOTHING_HAPPENED;
	
	/**
	 * ID: 62<br>
	 * Message: $s1強化に成功しました。
	 */
	public static final SystemMessageId S1_SUCCESSFULLY_ENCHANTED;
	
	/**
	 * ID: 63<br>
	 * Message: +$s1$s2が無事強化されました。
	 */
	public static final SystemMessageId S1_S2_SUCCESSFULLY_ENCHANTED;
	
	/**
	 * ID: 64<br>
	 * Message: 強化に失敗しました。$s1が消えました。
	 */
	public static final SystemMessageId ENCHANTMENT_FAILED_S1_EVAPORATED;
	
	/**
	 * ID: 65<br>
	 * Message: 強化に失敗しました。+$s1$s2が消えました。
	 */
	public static final SystemMessageId ENCHANTMENT_FAILED_S1_S2_EVAPORATED;
	
	/**
	 * ID: 66<br>
	 * Message: $c1 がパーティに招待しています。参加しますか。
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY;
	
	/**
	 * ID: 67<br>
	 * Message: $s1 から$s2血盟の勧誘を受けています。加入しますか。
	 */
	public static final SystemMessageId S1_HAS_INVITED_YOU_TO_JOIN_THE_CLAN_S2;
	
	/**
	 * ID: 68<br>
	 * Message: $s1血盟から脱退しますか。血盟脱退後、1日間は他の血盟に加入できません。
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_WITHDRAW_FROM_THE_S1_CLAN;
	
	/**
	 * ID: 69<br>
	 * Message: $s1を血盟から追放しますか。血盟員を除名したら1日間、新しい血盟員の加入はできません。
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_DISMISS_S1_FROM_THE_CLAN;
	
	/**
	 * ID: 70<br>
	 * Message: $s1血盟を解散します。よろしいですか。
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_DISPERSE_THE_CLAN_S1;
	
	/**
	 * ID: 71<br>
	 * Message: $s1を何個捨てますか。
	 */
	public static final SystemMessageId HOW_MANY_S1_DISCARD;
	
	/**
	 * ID: 72<br>
	 * Message: $s1を何個移しますか。
	 */
	public static final SystemMessageId HOW_MANY_S1_MOVE;
	
	/**
	 * ID: 73<br>
	 * Message: $s1を何個破壊しますか。
	 */
	public static final SystemMessageId HOW_MANY_S1_DESTROY;
	
	/**
	 * ID: 74<br>
	 * Message: $s1を破壊します。よろしいですか。
	 */
	public static final SystemMessageId WISH_DESTROY_S1;
	
	/**
	 * ID: 75<br>
	 * Message: 存在しないアカウントです。
	 */
	public static final SystemMessageId ID_NOT_EXIST;
	
	/**
	 * ID: 76<br>
	 * Message: パスワードが正しくありません。
	 */
	public static final SystemMessageId INCORRECT_PASSWORD;
	
	/**
	 * ID: 77<br>
	 * Message: これ以上は生成できません。既存のキャラクターを削除してやり直してください。
	 */
	public static final SystemMessageId CANNOT_CREATE_CHARACTER;
	
	/**
	 * ID: 78<br>
	 * Message: キャラクターが削除されれば、持っていたトッピング アイテムも削除されます。キャラクターがトッピング アイテムを持っているかどうかを確認してください。$s1を本当に削除しますか。
	 */
	public static final SystemMessageId WISH_DELETE_S1;
	
	/**
	 * ID: 79<br>
	 * Message: すでに存在する名前です。
	 */
	public static final SystemMessageId NAMING_NAME_ALREADY_EXISTS;
	
	/**
	 * ID: 80<br>
	 * Message: 日本語1～8文字、または英語1～16文字以内にしてください。
	 */
	public static final SystemMessageId NAMING_CHARNAME_UP_TO_16CHARS;
	
	/**
	 * ID: 81<br>
	 * Message: 種族を選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_RACE;
	
	/**
	 * ID: 82<br>
	 * Message: クラスを選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_OCCUPATION;
	
	/**
	 * ID: 83<br>
	 * Message: 性別を選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_GENDER;
	
	/**
	 * ID: 84<br>
	 * Message: ピースゾーン内では攻撃できません。
	 */
	public static final SystemMessageId CANT_ATK_PEACEZONE;
	
	/**
	 * ID: 85<br>
	 * Message: 相手がピースゾーン内にいるため、攻撃できません。
	 */
	public static final SystemMessageId TARGET_IN_PEACEZONE;
	
	/**
	 * ID: 86<br>
	 * Message: アカウントを入力してください。
	 */
	public static final SystemMessageId PLEASE_ENTER_ID;
	
	/**
	 * ID: 87<br>
	 * Message: パスワードを入力してください。
	 */
	public static final SystemMessageId PLEASE_ENTER_PASSWORD;
	
	/**
	 * ID: 88<br>
	 * Message: プロトコルのバージョンが異なります。プログラムを終了してください。
	 */
	public static final SystemMessageId WRONG_PROTOCOL_CHECK;
	
	/**
	 * ID: 89<br>
	 * Message: プロトコルのバージョンが異なります。続けてください。
	 */
	public static final SystemMessageId WRONG_PROTOCOL_CONTINUE;
	
	/**
	 * ID: 90<br>
	 * Message: サーバーに接続できません。
	 */
	public static final SystemMessageId UNABLE_TO_CONNECT;
	
	/**
	 * ID: 91<br>
	 * Message: 髪型を選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_HAIRSTYLE;
	
	/**
	 * ID: 92<br>
	 * Message: $s1の効果が消えました。
	 */
	public static final SystemMessageId S1_HAS_WORN_OFF;
	
	/**
	 * ID: 93<br>
	 * Message: SPが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SP;
	
	/**
	 * ID: 94<br>
	 * Message: Copyright c NCsoft Corporation. All Rights Reserved.
	 */
	public static final SystemMessageId COPYRIGHT;
	
	/**
	 * ID: 95<br>
	 * Message: $s1の経験値と$s2のSPを得ました。
	 */
	public static final SystemMessageId YOU_EARNED_S1_EXP_AND_S2_SP;
	
	/**
	 * ID: 96<br>
	 * Message: レベル アップしました！
	 */
	public static final SystemMessageId YOU_INCREASED_YOUR_LEVEL;
	
	/**
	 * ID: 97<br>
	 * Message: クエスト アイテムは移動できません。
	 */
	public static final SystemMessageId CANNOT_MOVE_THIS_ITEM;
	
	/**
	 * ID: 98<br>
	 * Message: クエスト アイテムは、捨てられません。
	 */
	public static final SystemMessageId CANNOT_DISCARD_THIS_ITEM;
	
	/**
	 * ID: 99<br>
	 * Message: クエスト アイテムはトレードしたり売ったりできません。
	 */
	public static final SystemMessageId CANNOT_TRADE_THIS_ITEM;
	
	/**
	 * ID: 100<br>
	 * Message: $c1がトレードを申請しています。取引に応じますか。
	 */
	public static final SystemMessageId C1_REQUESTS_TRADE;
	
	/**
	 * ID: 101<br>
	 * Message: 戦闘中はログアウトできません。
	 */
	public static final SystemMessageId CANT_LOGOUT_WHILE_FIGHTING;
	
	/**
	 * ID: 102<br>
	 * Message: 戦闘中はリスタートできません。
	 */
	public static final SystemMessageId CANT_RESTART_WHILE_FIGHTING;
	
	/**
	 * ID: 103<br>
	 * Message: 現在接続しているアカウントです。
	 */
	public static final SystemMessageId ID_LOGGED_IN;
	
	/**
	 * ID: 104<br>
	 * Message: 変身中には武器を変更できません。
	 */
	public static final SystemMessageId CANNOT_CHANGE_WEAPON_DURING_AN_ATTACK;
	
	/**
	 * ID: 105<br>
	 * Message: $c1 をパーティに招待しました。
	 */
	public static final SystemMessageId C1_INVITED_TO_PARTY;
	
	/**
	 * ID: 106<br>
	 * Message: パーティに参加しました。
	 */
	public static final SystemMessageId YOU_JOINED_S1_PARTY;
	
	/**
	 * ID: 107<br>
	 * Message: $c1がパーティに参加しました。
	 */
	public static final SystemMessageId C1_JOINED_PARTY;
	
	/**
	 * ID: 108<br>
	 * Message: $c1がパーティから脱退しました。
	 */
	public static final SystemMessageId C1_LEFT_PARTY;
	
	/**
	 * ID: 109<br>
	 * Message: ターゲットが正しくありません。
	 */
	public static final SystemMessageId INCORRECT_TARGET;
	
	/**
	 * ID: 110<br>
	 * Message: $s1の効果が感じられます。
	 */
	public static final SystemMessageId YOU_FEEL_S1_EFFECT;
	
	/**
	 * ID: 111<br>
	 * Message: シールドによる防御に成功しました。
	 */
	public static final SystemMessageId SHIELD_DEFENCE_SUCCESSFULL;
	
	/**
	 * ID: 112<br>
	 * Message: 矢が足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_ARROWS;
	
	/**
	 * ID: 113<br>
	 * Message: 条件が合わないため、$s1は使用できません。
	 */
	public static final SystemMessageId S1_CANNOT_BE_USED;
	
	/**
	 * ID: 114<br>
	 * Message: 世界樹の影に入ります。
	 */
	public static final SystemMessageId ENTER_SHADOW_MOTHER_TREE;
	
	/**
	 * ID: 115<br>
	 * Message: 世界樹の影から離れます。
	 */
	public static final SystemMessageId EXIT_SHADOW_MOTHER_TREE;
	
	/**
	 * ID: 116<br>
	 * Message: ピースゾーンに入ります。
	 */
	public static final SystemMessageId ENTER_PEACEFUL_ZONE;
	
	/**
	 * ID: 117<br>
	 * Message: ピースゾーンから離れます。
	 */
	public static final SystemMessageId EXIT_PEACEFUL_ZONE;
	
	/**
	 * ID: 118<br>
	 * Message: $c1 にトレードを申し込みます。
	 */
	public static final SystemMessageId REQUEST_C1_FOR_TRADE;
	
	/**
	 * ID: 119<br>
	 * Message: $c1 がトレードを拒否しました。
	 */
	public static final SystemMessageId C1_DENIED_TRADE_REQUEST;
	
	/**
	 * ID: 120<br>
	 * Message: $c1とのトレードを開始します。
	 */
	public static final SystemMessageId BEGIN_TRADE_WITH_C1;
	
	/**
	 * ID: 121<br>
	 * Message: $c1 がトレードするアイテムを確定しました。
	 */
	public static final SystemMessageId C1_CONFIRMED_TRADE;
	
	/**
	 * ID: 122<br>
	 * Message: 相手がトレードするアイテムを確定したため、アイテムの追加はできません。
	 */
	public static final SystemMessageId CANNOT_ADJUST_ITEMS_AFTER_TRADE_CONFIRMED;
	
	/**
	 * ID: 123<br>
	 * Message: トレードが無事に行われました。
	 */
	public static final SystemMessageId TRADE_SUCCESSFUL;
	
	/**
	 * ID: 124<br>
	 * Message: $c1 がトレードをキャンセルしました。
	 */
	public static final SystemMessageId C1_CANCELED_TRADE;
	
	/**
	 * ID: 125<br>
	 * Message: ゲームを終了します。
	 */
	public static final SystemMessageId WISH_EXIT_GAME;
	
	/**
	 * ID: 126<br>
	 * Message: ゲームをリスタートします。
	 */
	public static final SystemMessageId WISH_RESTART_GAME;
	
	/**
	 * ID: 127<br>
	 * Message: サーバーとの接続が切断されました。しばらくしてから再度ご接続ください。
	 */
	public static final SystemMessageId DISCONNECTED_FROM_SERVER;
	
	/**
	 * ID: 128<br>
	 * Message: キャラクターの生成に失敗しました。
	 */
	public static final SystemMessageId CHARACTER_CREATION_FAILED;
	
	/**
	 * ID: 129<br>
	 * Message: インベントリのスロットがいっぱいです。
	 */
	public static final SystemMessageId SLOTS_FULL;
	
	/**
	 * ID: 130<br>
	 * Message: 倉庫のスロットがいっぱいです。
	 */
	public static final SystemMessageId WAREHOUSE_FULL;
	
	/**
	 * ID: 131<br>
	 * Message: $s1がログインしました。
	 */
	public static final SystemMessageId S1_LOGGED_IN;
	
	/**
	 * ID: 132<br>
	 * Message: $s1が友人リストに追加されました。
	 */
	public static final SystemMessageId S1_ADDED_TO_FRIENDS;
	
	/**
	 * ID: 133<br>
	 * Message: $s1が友人リストから削除されました。
	 */
	public static final SystemMessageId S1_REMOVED_FROM_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 134<br>
	 * Message: 友人リストを確認してください。
	 */
	public static final SystemMessageId PLEACE_CHECK_YOUR_FRIEND_LIST_AGAIN;
	
	/**
	 * ID: 135<br>
	 * Message: $c1が応答しなかったため、パーティ招待がキャンセルされました。
	 */
	public static final SystemMessageId C1_DID_NOT_REPLY_TO_YOUR_INVITE;
	
	/**
	 * ID: 136<br>
	 * Message: $c1の招待に応答しなかったため、加入がキャンセルされました。
	 */
	public static final SystemMessageId YOU_DID_NOT_REPLY_TO_C1_INVITE;
	
	/**
	 * ID: 137<br>
	 * Message: ショートカットに指定されたアイテムが見つかりません。
	 */
	public static final SystemMessageId NO_MORE_ITEMS_SHORTCUT;
	
	/**
	 * ID: 138<br>
	 * Message: ショートカットが指定されていません。
	 */
	public static final SystemMessageId DESIGNATE_SHORTCUT;
	
	/**
	 * ID: 139<br>
	 * Message: $c1が$s2にかかりませんでした。
	 */
	public static final SystemMessageId C1_RESISTED_YOUR_S2;
	
	/**
	 * ID: 140<br>
	 * Message: MPが足りないため、スキルが中断されました。
	 */
	public static final SystemMessageId SKILL_REMOVED_DUE_LACK_MP;
	
	/**
	 * ID: 141<br>
	 * Message: トレードするアイテムを確定したため、アイテムの追加はできません。
	 */
	public static final SystemMessageId ONCE_THE_TRADE_IS_CONFIRMED_THE_ITEM_CANNOT_BE_MOVED_AGAIN;
	
	/**
	 * ID: 142<br>
	 * Message: すでにトレード中です。
	 */
	public static final SystemMessageId ALREADY_TRADING;
	
	/**
	 * ID: 143<br>
	 * Message: $c1 は他の人とトレードしています。
	 */
	public static final SystemMessageId C1_ALREADY_TRADING;
	
	/**
	 * ID: 144<br>
	 * Message: ターゲットが正しくありません。
	 */
	public static final SystemMessageId TARGET_IS_INCORRECT;
	
	/**
	 * ID: 145<br>
	 * Message: ターゲットがゲーム内にいません。
	 */
	public static final SystemMessageId TARGET_IS_NOT_FOUND_IN_THE_GAME;
	
	/**
	 * ID: 146<br>
	 * Message: チャットが許可されました。
	 */
	public static final SystemMessageId CHATTING_PERMITTED;
	
	/**
	 * ID: 147<br>
	 * Message: チャットが禁止されました。
	 */
	public static final SystemMessageId CHATTING_PROHIBITED;
	
	/**
	 * ID: 148<br>
	 * Message: クエスト アイテムは使用できません。
	 */
	public static final SystemMessageId CANNOT_USE_QUEST_ITEMS;
	
	/**
	 * ID: 149<br>
	 * Message: トレード中はアイテムを拾ったり使用したりできません。
	 */
	public static final SystemMessageId CANNOT_USE_ITEM_WHILE_TRADING;
	
	/**
	 * ID: 150<br>
	 * Message: 個人商店やトレード中に、アイテムを捨てることや破壊することはできません。
	 */
	public static final SystemMessageId CANNOT_DISCARD_OR_DESTROY_ITEM_WHILE_TRADING;
	
	/**
	 * ID: 151<br>
	 * Message: 遠過ぎて捨てられません。
	 */
	public static final SystemMessageId CANNOT_DISCARD_DISTANCE_TOO_FAR;
	
	/**
	 * ID: 152<br>
	 * Message: 招待する対象が正しくありません。
	 */
	public static final SystemMessageId YOU_HAVE_INVITED_THE_WRONG_TARGET;
	
	/**
	 * ID: 153<br>
	 * Message: $c1 は作業中です。しばらくしてから申請しなおしてください。
	 */
	public static final SystemMessageId C1_IS_BUSY_TRY_LATER;
	
	/**
	 * ID: 154<br>
	 * Message: パーティ リーダーだけが招待を使用できます。
	 */
	public static final SystemMessageId ONLY_LEADER_CAN_INVITE;
	
	/**
	 * ID: 155<br>
	 * Message: パーティが満員になりました。
	 */
	public static final SystemMessageId PARTY_FULL;
	
	/**
	 * ID: 156<br>
	 * Message: ドレインが半分だけ成功しました。
	 */
	public static final SystemMessageId DRAIN_HALF_SUCCESFUL;
	
	/**
	 * ID: 157<br>
	 * Message: $c1のドレインに抵抗しました。
	 */
	public static final SystemMessageId RESISTED_C1_DRAIN;
	
	/**
	 * ID: 158<br>
	 * Message: 攻撃が失敗しました。
	 */
	public static final SystemMessageId ATTACK_FAILED;
	
	/**
	 * ID: 159<br>
	 * Message: $c1の魔法に抵抗しました。
	 */
	public static final SystemMessageId RESISTED_C1_MAGIC;
	
	/**
	 * ID: 160<br>
	 * Message: $c1 はすでにパーティに所属しているため、招待できません。
	 */
	public static final SystemMessageId C1_IS_ALREADY_IN_PARTY;
	
	/**
	 * ID: 161<br>
	 * Message: パーティに招待したユーザーがいません。
	 */
	public static final SystemMessageId INVITED_USER_NOT_ONLINE;
	
	/**
	 * ID: 162<br>
	 * Message: 倉庫が離れ過ぎています。
	 */
	public static final SystemMessageId WAREHOUSE_TOO_FAR;
	
	/**
	 * ID: 163<br>
	 * Message: 個数が正しくないため、破壊できません。
	 */
	public static final SystemMessageId CANNOT_DESTROY_NUMBER_INCORRECT;
	
	/**
	 * ID: 164<br>
	 * Message: 他のプロセスの応答を待っています。
	 */
	public static final SystemMessageId WAITING_FOR_ANOTHER_REPLY;
	
	/**
	 * ID: 165<br>
	 * Message: 自分自身を友人として登録することはできません。
	 */
	public static final SystemMessageId YOU_CANNOT_ADD_YOURSELF_TO_OWN_FRIEND_LIST;
	
	/**
	 * ID: 166<br>
	 * Message: 友人リストがまだ作成されていません。しばらくしてから登録しなおしてください。
	 */
	public static final SystemMessageId FRIEND_LIST_NOT_READY_YET_REGISTER_LATER;
	
	/**
	 * ID: 167<br>
	 * Message: $c1 はすでに友人リストに登録されています。
	 */
	public static final SystemMessageId C1_ALREADY_ON_FRIEND_LIST;
	
	/**
	 * ID: 168<br>
	 * Message: $c1 から友人リストの登録を要請されました。
	 */
	public static final SystemMessageId C1_REQUESTED_TO_BECOME_FRIENDS;
	
	/**
	 * ID: 169<br>
	 * Message: 登録を許可しますか。 0/1 (許可は1、拒否は0)
	 */
	public static final SystemMessageId ACCEPT_THE_FRIENDSHIP;
	
	/**
	 * ID: 170<br>
	 * Message: 友人登録を要請したユーザーがゲーム内にいません。
	 */
	public static final SystemMessageId THE_USER_YOU_REQUESTED_IS_NOT_IN_GAME;
	
	/**
	 * ID: 171<br>
	 * Message: $c1 は友人リストに登録されているユーザーではありません。
	 */
	public static final SystemMessageId C1_NOT_ON_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 172<br>
	 * Message: 保管料がありません。
	 */
	public static final SystemMessageId LACK_FUNDS_FOR_TRANSACTION1;
	
	/**
	 * ID: 173<br>
	 * Message: 保管料が足りません。
	 */
	public static final SystemMessageId LACK_FUNDS_FOR_TRANSACTION2;
	
	/**
	 * ID: 174<br>
	 * Message: 相手のインベントリのスロットがいっぱいです。
	 */
	public static final SystemMessageId OTHER_INVENTORY_FULL;
	
	/**
	 * ID: 175<br>
	 * Message: HPが完全に回復されたため、スキルが中断されました。
	 */
	public static final SystemMessageId SKILL_DEACTIVATED_HP_FULL;
	
	/**
	 * ID: 176<br>
	 * Message: 相手はメッセージ受信不可能な状態です。
	 */
	public static final SystemMessageId THE_PERSON_IS_IN_MESSAGE_REFUSAL_MODE;
	
	/**
	 * ID: 177<br>
	 * Message: メッセージ受信不可能な状態です。
	 */
	public static final SystemMessageId MESSAGE_REFUSAL_MODE;
	
	/**
	 * ID: 178<br>
	 * Message: メッセージ受信可の状態です。
	 */
	public static final SystemMessageId MESSAGE_ACCEPTANCE_MODE;
	
	/**
	 * ID: 179<br>
	 * Message: ここではアイテムを捨てられません。
	 */
	public static final SystemMessageId CANT_DISCARD_HERE;
	
	/**
	 * ID: 180<br>
	 * Message: 削除まで$s1日が残っています。削除をキャンセルしますか。
	 */
	public static final SystemMessageId S1_DAYS_LEFT_CANCEL_ACTION;
	
	/**
	 * ID: 181<br>
	 * Message: ターゲットが見えません。
	 */
	public static final SystemMessageId CANT_SEE_TARGET;
	
	/**
	 * ID: 182<br>
	 * Message: クエスト：$s1を中断しますか。
	 */
	public static final SystemMessageId WANT_QUIT_CURRENT_QUEST;
	
	/**
	 * ID: 183<br>
	 * Message: サーバーの制限人数に達しました。しばらくしてから接続しなおしてみてください。
	 */
	public static final SystemMessageId TOO_MANY_USERS;
	
	/**
	 * ID: 184<br>
	 * Message: しばらくしてからやり直してください。
	 */
	public static final SystemMessageId TRY_AGAIN_LATER;
	
	/**
	 * ID: 185<br>
	 * Message: パーティに招待するユーザーを選択してください。
	 */
	public static final SystemMessageId FIRST_SELECT_USER_TO_INVITE_TO_PARTY;
	
	/**
	 * ID: 186<br>
	 * Message: 血盟加入を勧誘するユーザーを選択してください。
	 */
	public static final SystemMessageId FIRST_SELECT_USER_TO_INVITE_TO_CLAN;
	
	/**
	 * ID: 187<br>
	 * Message: 追放するユーザーを選択してください。
	 */
	public static final SystemMessageId SELECT_USER_TO_EXPEL;
	
	/**
	 * ID: 188<br>
	 * Message: 血盟名を入力してください。
	 */
	public static final SystemMessageId PLEASE_CREATE_CLAN_NAME;
	
	/**
	 * ID: 189<br>
	 * Message: 血盟が創設されました。
	 */
	public static final SystemMessageId CLAN_CREATED;
	
	/**
	 * ID: 190<br>
	 * Message: 血盟の創設に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_CREATE_CLAN;
	
	/**
	 * ID: 191<br>
	 * Message: 血盟員 $s1が血盟から除名されました。
	 */
	public static final SystemMessageId CLAN_MEMBER_S1_EXPELLED;
	
	/**
	 * ID: 192<br>
	 * Message: 血盟員 $s1の除名に失敗しました。
	 */
	public static final SystemMessageId FAILED_EXPEL_S1;
	
	/**
	 * ID: 193<br>
	 * Message: 血盟が解散されました。
	 */
	public static final SystemMessageId CLAN_HAS_DISPERSED;
	
	/**
	 * ID: 194<br>
	 * Message: 血盟の解散に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_DISPERSE_CLAN;
	
	/**
	 * ID: 195<br>
	 * Message: 血盟に加入されました。
	 */
	public static final SystemMessageId ENTERED_THE_CLAN;
	
	/**
	 * ID: 196<br>
	 * Message: $s1が血盟加入を拒否しました。
	 */
	public static final SystemMessageId S1_REFUSED_TO_JOIN_CLAN;
	
	/**
	 * ID: 197<br>
	 * Message: 血盟から脱退しました。
	 */
	public static final SystemMessageId YOU_HAVE_WITHDRAWN_FROM_CLAN;
	
	/**
	 * ID: 198<br>
	 * Message: $s1血盟からの脱退に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_WITHDRAW_FROM_S1_CLAN;
	
	/**
	 * ID: 199<br>
	 * Message: 血盟から除名されました。1日間は他の血盟に加入できません。
	 */
	public static final SystemMessageId CLAN_MEMBERSHIP_TERMINATED;
	
	/**
	 * ID: 200<br>
	 * Message: パーティから脱退しました。
	 */
	public static final SystemMessageId YOU_LEFT_PARTY;
	
	/**
	 * ID: 201<br>
	 * Message: $c1をパーティから追放しました。
	 */
	public static final SystemMessageId C1_WAS_EXPELLED_FROM_PARTY;
	
	/**
	 * ID: 202<br>
	 * Message: パーティから追放されました。
	 */
	public static final SystemMessageId HAVE_BEEN_EXPELLED_FROM_PARTY;
	
	/**
	 * ID: 203<br>
	 * Message: パーティが解散されました。
	 */
	public static final SystemMessageId PARTY_DISPERSED;
	
	/**
	 * ID: 204<br>
	 * Message: 文字数の制限を越えているか、不適切な文字が使用されています。やり直してください。
	 */
	public static final SystemMessageId INCORRECT_NAME_TRY_AGAIN;
	
	/**
	 * ID: 205<br>
	 * Message: キャラクター名が正しくありません。サポートにお問い合わせください。
	 */
	public static final SystemMessageId INCORRECT_CHARACTER_NAME_TRY_AGAIN;
	
	/**
	 * ID: 206<br>
	 * Message: 血盟戦を布告する血盟名を入力してください。
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_DECLARE_WAR;
	
	/**
	 * ID: 207<br>
	 * Message: $s1血盟の$s2に血盟戦を申し込まれました。挑戦を受け入れますか。
	 */
	public static final SystemMessageId S2_OF_THE_CLAN_S1_REQUESTS_WAR;
	
	/**
	 * ID: 212<br>
	 * Message: 所属する血盟員ではありません。
	 */
	public static final SystemMessageId YOU_ARE_NOT_A_CLAN_MEMBER;
	
	/**
	 * ID: 213<br>
	 * Message: 処理されませんでした。しばらくしてからやり直してください。
	 */
	public static final SystemMessageId NOT_WORKING_PLEASE_TRY_AGAIN_LATER;
	
	/**
	 * ID: 214<br>
	 * Message: タイトルが変更されました。
	 */
	public static final SystemMessageId TITLE_CHANGED;
	
	/**
	 * ID: 215<br>
	 * Message: $s1 血盟との戦争が始まりました。
	 */
	public static final SystemMessageId WAR_WITH_THE_S1_CLAN_HAS_BEGUN;
	
	/**
	 * ID: 216<br>
	 * Message: $s1 血盟との戦争が終了しました。
	 */
	public static final SystemMessageId WAR_WITH_THE_S1_CLAN_HAS_ENDED;
	
	/**
	 * ID: 217<br>
	 * Message: $s1 血盟との戦争で勝利しました！
	 */
	public static final SystemMessageId YOU_HAVE_WON_THE_WAR_OVER_THE_S1_CLAN;
	
	/**
	 * ID: 218<br>
	 * Message: $s1 血盟に降伏しました。
	 */
	public static final SystemMessageId YOU_HAVE_SURRENDERED_TO_THE_S1_CLAN;
	
	/**
	 * ID: 219<br>
	 * Message: 血盟主が死亡し、$s1 血盟に負けました。
	 */
	public static final SystemMessageId YOU_WERE_DEFEATED_BY_S1_CLAN;
	
	/**
	 * ID: 220<br>
	 * Message: 血盟戦の終了まであと $s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_UNTIL_CLAN_WAR_ENDS;
	
	/**
	 * ID: 221<br>
	 * Message: 血盟戦の制限時間が過ぎ、$s1 血盟との戦争が終了しました。
	 */
	public static final SystemMessageId CLAN_WAR_WITH_S1_CLAN_HAS_ENDED;
	
	/**
	 * ID: 222<br>
	 * Message: $s1が血盟に加入しました。
	 */
	public static final SystemMessageId S1_HAS_JOINED_CLAN;
	
	/**
	 * ID: 223<br>
	 * Message: 血盟員 $s1が血盟から脱退しました。
	 */
	public static final SystemMessageId S1_HAS_WITHDRAWN_FROM_THE_CLAN;
	
	/**
	 * ID: 224<br>
	 * Message: $s1が応答しなかったため、血盟加入の勧誘がキャンセルされました。
	 */
	public static final SystemMessageId S1_DID_NOT_RESPOND_TO_CLAN_INVITATION;
	
	/**
	 * ID: 225<br>
	 * Message: $s1の勧誘に応答しなかったため、血盟加入がキャンセルされました。
	 */
	public static final SystemMessageId YOU_DID_NOT_RESPOND_TO_S1_CLAN_INVITATION;
	
	/**
	 * ID: 226<br>
	 * Message: $s1血盟が応答しなかったため、宣戦布告が拒否されました。
	 */
	public static final SystemMessageId S1_CLAN_DID_NOT_RESPOND;
	
	/**
	 * ID: 227<br>
	 * Message: $s1血盟の宣戦布告に応答しなかったため、血盟戦が拒否されました。
	 */
	public static final SystemMessageId CLAN_WAR_REFUSED_YOU_DID_NOT_RESPOND_TO_S1;
	
	/**
	 * ID: 228<br>
	 * Message: 終戦要請が拒否されました。
	 */
	public static final SystemMessageId REQUEST_TO_END_WAR_HAS_BEEN_DENIED;
	
	/**
	 * ID: 229<br>
	 * Message: 血盟創設の資格がありません。
	 */
	public static final SystemMessageId YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN;
	
	/**
	 * ID: 230<br>
	 * Message: 血盟解散後10日以内には新しく血盟を創設できません。
	 */
	public static final SystemMessageId YOU_MUST_WAIT_XX_DAYS_BEFORE_CREATING_A_NEW_CLAN;
	
	/**
	 * ID: 231<br>
	 * Message: 血盟員の除名後1日以内には新しい血盟員の加入はできません。
	 */
	public static final SystemMessageId YOU_MUST_WAIT_BEFORE_ACCEPTING_A_NEW_MEMBER;
	
	/**
	 * ID: 232<br>
	 * Message: 除名または脱退後1日以内には血盟に加入できません。
	 */
	public static final SystemMessageId YOU_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN;
	
	/**
	 * ID: 233<br>
	 * Message: アカデミー/近衛隊/騎士団の空きがありませんので、新たな血盟員の加入はできません。
	 */
	public static final SystemMessageId SUBCLAN_IS_FULL;
	
	/**
	 * ID: 234<br>
	 * Message: ターゲットを血盟員にしてください。
	 */
	public static final SystemMessageId TARGET_MUST_BE_IN_CLAN;
	
	/**
	 * ID: 235<br>
	 * Message: 権限が委譲できません。
	 */
	public static final SystemMessageId NOT_AUTHORIZED_TO_BESTOW_RIGHTS;
	
	/**
	 * ID: 236<br>
	 * Message: 血盟主のみが行えます。
	 */
	public static final SystemMessageId ONLY_THE_CLAN_LEADER_IS_ENABLED;
	
	/**
	 * ID: 237<br>
	 * Message: 血盟主が見つかりません。
	 */
	public static final SystemMessageId CLAN_LEADER_NOT_FOUND;
	
	/**
	 * ID: 238<br>
	 * Message: 血盟に加入していません。
	 */
	public static final SystemMessageId NOT_JOINED_IN_ANY_CLAN;
	
	/**
	 * ID: 239<br>
	 * Message: 血盟主は脱退できません。
	 */
	public static final SystemMessageId CLAN_LEADER_CANNOT_WITHDRAW;
	
	/**
	 * ID: 240<br>
	 * Message: 現在血盟戦中です。
	 */
	public static final SystemMessageId CURRENTLY_INVOLVED_IN_CLAN_WAR;
	
	/**
	 * ID: 241<br>
	 * Message: $s1血盟の血盟主は接続していません。
	 */
	public static final SystemMessageId LEADER_OF_S1_CLAN_NOT_FOUND;
	
	/**
	 * ID: 242<br>
	 * Message: ターゲットを選択してください。
	 */
	public static final SystemMessageId SELECT_TARGET;
	
	/**
	 * ID: 243<br>
	 * Message: 同盟の血盟に対して血盟戦の宣戦布告はできません。
	 */
	public static final SystemMessageId CANNOT_DECLARE_WAR_ON_ALLIED_CLAN;
	
	/**
	 * ID: 244<br>
	 * Message: 血盟戦を申し込む資格がありません。
	 */
	public static final SystemMessageId NOT_ALLOWED_TO_CHALLENGE;
	
	/**
	 * ID: 245<br>
	 * Message: 前回の血盟戦の拒否から5日が経っていません。よろしいですか。
	 */
	public static final SystemMessageId FIVE_DAYS_NOT_PASSED_SINCE_REFUSED_WAR;
	
	/**
	 * ID: 246<br>
	 * Message: 相手の血盟は現在戦争中です。
	 */
	public static final SystemMessageId CLAN_CURRENTLY_AT_WAR;
	
	/**
	 * ID: 247<br>
	 * Message: $s1 血盟とはすでに戦争を行ったため、前回の戦争から5日が経過しないと宣戦布告できません。
	 */
	public static final SystemMessageId FIVE_DAYS_MUST_PASS_BEFORE_CHALLENGE_AGAIN;
	
	/**
	 * ID: 248<br>
	 * Message: $s1 血盟の血盟員が少なくて宣戦布告できません。
	 */
	public static final SystemMessageId S1_CLAN_NOT_ENOUGH_MEMBERS_FOR_WAR;
	
	/**
	 * ID: 249<br>
	 * Message: $s1 血盟に降伏しますか。
	 */
	public static final SystemMessageId WISH_SURRENDER_TO_S1_CLAN;
	
	/**
	 * ID: 250<br>
	 * Message: $s1 血盟に個人降伏しました。血盟戦の状態から免れます。
	 */
	public static final SystemMessageId YOU_HAVE_PERSONALLY_SURRENDERED_TO_THE_S1_CLAN;
	
	/**
	 * ID: 251<br>
	 * Message: 血盟戦中には他の血盟に対して宣戦布告できません。
	 */
	public static final SystemMessageId ALREADY_AT_WAR_WITH_ANOTHER_CLAN;
	
	/**
	 * ID: 252<br>
	 * Message: 降伏する血盟名を入力してください。
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_SURRENDER_TO;
	
	/**
	 * ID: 253<br>
	 * Message: 終戦を申し込む血盟名を入力してください。
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_END_WAR;
	
	/**
	 * ID: 254<br>
	 * Message: 血盟主は個人降伏できません。
	 */
	public static final SystemMessageId LEADER_CANT_PERSONALLY_SURRENDER;
	
	/**
	 * ID: 255<br>
	 * Message: $s1 血盟に終戦を申し込まれました。同意しますか。
	 */
	public static final SystemMessageId S1_CLAN_REQUESTED_END_WAR;
	
	/**
	 * ID: 256<br>
	 * Message: 付与するタイトルを入力してください。
	 */
	public static final SystemMessageId ENTER_TITLE;
	
	/**
	 * ID: 257<br>
	 * Message: $s1血盟に終戦を申し込みますか。
	 */
	public static final SystemMessageId DO_YOU_OFFER_S1_CLAN_END_WAR;
	
	/**
	 * ID: 258<br>
	 * Message: 血盟戦中ではありません。
	 */
	public static final SystemMessageId NOT_INVOLVED_CLAN_WAR;
	
	/**
	 * ID: 259<br>
	 * Message: 血盟員をリストから選択してください。
	 */
	public static final SystemMessageId SELECT_MEMBERS_FROM_LIST;
	
	/**
	 * ID: 260<br>
	 * Message: 血盟戦の拒否から5日が経っていないため、名声値が下がりました。
	 */
	public static final SystemMessageId FIVE_DAYS_NOT_PASSED_SINCE_YOU_WERE_REFUSED_WAR;
	
	/**
	 * ID: 261<br>
	 * Message: 血盟名が正しくありません。
	 */
	public static final SystemMessageId CLAN_NAME_INCORRECT;
	
	/**
	 * ID: 262<br>
	 * Message: 血盟名が長過ぎます。
	 */
	public static final SystemMessageId CLAN_NAME_TOO_LONG;
	
	/**
	 * ID: 263<br>
	 * Message: $s1血盟は解散の申し込み中です。
	 */
	public static final SystemMessageId DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 264<br>
	 * Message: 戦争中には血盟を解散できません。
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_WHILE_IN_WAR;
	
	/**
	 * ID: 265<br>
	 * Message: 攻防中の血盟は解散できません。
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_WHILE_IN_SIEGE;
	
	/**
	 * ID: 266<br>
	 * Message: アジトや城を所有している血盟は解散できません。
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_WHILE_OWNING_CLAN_HALL_OR_CASTLE;
	
	/**
	 * ID: 267<br>
	 * Message: 解散の申し込みがされていません。
	 */
	public static final SystemMessageId NO_REQUESTS_TO_DISPERSE;
	
	/**
	 * ID: 268<br>
	 * Message: すでに血盟に所属しています。
	 */
	public static final SystemMessageId PLAYER_ALREADY_ANOTHER_CLAN;
	
	/**
	 * ID: 269<br>
	 * Message: 自分自身を除名することはできません。
	 */
	public static final SystemMessageId YOU_CANNOT_DISMISS_YOURSELF;
	
	/**
	 * ID: 270<br>
	 * Message: すでに降伏しました。
	 */
	public static final SystemMessageId YOU_HAVE_ALREADY_SURRENDERED;
	
	/**
	 * ID: 271<br>
	 * Message: タイトル付与は血盟スキル レベルが3以上からできます。
	 */
	public static final SystemMessageId CLAN_LVL_3_NEEDED_TO_ENDOWE_TITLE;
	
	/**
	 * ID: 272<br>
	 * Message: エンブレム登録は血盟スキル レベルが3以上からできます。
	 */
	public static final SystemMessageId CLAN_LVL_3_NEEDED_TO_SET_CREST;
	
	/**
	 * ID: 273<br>
	 * Message: 血盟戦の布告は血盟スキル レベルが3以上からできます。
	 */
	public static final SystemMessageId CLAN_LVL_3_NEEDED_TO_DECLARE_WAR;
	
	/**
	 * ID: 274<br>
	 * Message: 血盟スキルのレベルが上がりました。
	 */
	public static final SystemMessageId CLAN_LEVEL_INCREASED;
	
	/**
	 * ID: 275<br>
	 * Message: 血盟スキルのレベル アップに失敗しました。
	 */
	public static final SystemMessageId CLAN_LEVEL_INCREASE_FAILED;
	
	/**
	 * ID: 276<br>
	 * Message: スキル習得に必要なアイテムが足りません。
	 */
	public static final SystemMessageId ITEM_MISSING_TO_LEARN_SKILL;
	
	/**
	 * ID: 277<br>
	 * Message: $s1を習得します。
	 */
	public static final SystemMessageId LEARNED_SKILL_S1;
	
	/**
	 * ID: 278<br>
	 * Message: スキル習得に必要なSPが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SP_TO_LEARN_SKILL;
	
	/**
	 * ID: 279<br>
	 * Message: アデナが足りません。
	 */
	public static final SystemMessageId YOU_NOT_ENOUGH_ADENA;
	
	/**
	 * ID: 280<br>
	 * Message: 売るものがありません。
	 */
	public static final SystemMessageId NO_ITEMS_TO_SELL;
	
	/**
	 * ID: 281<br>
	 * Message: 保管料が足りません。
	 */
	public static final SystemMessageId YOU_NOT_ENOUGH_ADENA_PAY_FEE;
	
	/**
	 * ID: 282<br>
	 * Message: 預けたものがありません。
	 */
	public static final SystemMessageId NO_ITEM_DEPOSITED_IN_WH;
	
	/**
	 * ID: 283<br>
	 * Message: 戦場に入りました。
	 */
	public static final SystemMessageId ENTERED_COMBAT_ZONE;
	
	/**
	 * ID: 284<br>
	 * Message: 戦場から出ました。
	 */
	public static final SystemMessageId LEFT_COMBAT_ZONE;
	
	/**
	 * ID: 285<br>
	 * Message: $s1 血盟が支配者の刻印に成功しました。
	 */
	public static final SystemMessageId CLAN_S1_ENGRAVED_RULER;
	
	/**
	 * ID: 286<br>
	 * Message: 陣地が攻撃されています。
	 */
	public static final SystemMessageId BASE_UNDER_ATTACK;
	
	/**
	 * ID: 287<br>
	 * Message: 敵の血盟が支配者の刻印を始めました。
	 */
	public static final SystemMessageId OPPONENT_STARTED_ENGRAVING;
	
	/**
	 * ID: 288<br>
	 * Message: 城門が壊れました。
	 */
	public static final SystemMessageId CASTLE_GATE_BROKEN_DOWN;
	
	/**
	 * ID: 289<br>
	 * Message: 現在陣地や前哨基地があるため構築できません。
	 */
	public static final SystemMessageId NOT_ANOTHER_HEADQUARTERS;
	
	/**
	 * ID: 290<br>
	 * Message: ここには陣地を建てられません。
	 */
	public static final SystemMessageId NOT_SET_UP_BASE_HERE;
	
	/**
	 * ID: 291<br>
	 * Message: $s1 血盟が $s2 との攻城戦で勝利しました。
	 */
	public static final SystemMessageId CLAN_S1_VICTORIOUS_OVER_S2_S_SIEGE;
	
	/**
	 * ID: 292<br>
	 * Message: $s1が攻城時刻を宣布しました。
	 */
	public static final SystemMessageId S1_ANNOUNCED_SIEGE_TIME;
	
	/**
	 * ID: 293<br>
	 * Message: $s1の攻城登録期間が終了しました。
	 */
	public static final SystemMessageId REGISTRATION_TERM_FOR_S1_ENDED;
	
	/**
	 * ID: 294<br>
	 * Message: 攻城戦、要塞戦やアジト戦に参加している攻撃側の血盟ではないので陣地を作ることはできません。
	 */
	public static final SystemMessageId BECAUSE_YOUR_CLAN_IS_NOT_CURRENTLY_ON_THE_OFFENSIVE_IN_A_CLAN_HALL_SIEGE_WAR_IT_CANNOT_SUMMON_ITS_BASE_CAMP;
	
	/**
	 * ID: 295<br>
	 * Message: 攻城戦に参加する血盟がないため、$s1の攻城戦がキャンセルされました。
	 */
	public static final SystemMessageId S1_SIEGE_WAS_CANCELED_BECAUSE_NO_CLANS_PARTICIPATED;
	
	/**
	 * ID: 296<br>
	 * Message: 高い所から落ちて$s1のダメージを受けました。
	 */
	public static final SystemMessageId FALL_DAMAGE_S1;
	
	/**
	 * ID: 297<br>
	 * Message: 呼吸ができず$s1のダメージを受けました。
	 */
	public static final SystemMessageId DROWN_DAMAGE_S1;
	
	/**
	 * ID: 298<br>
	 * Message: $s1を落としました。
	 */
	public static final SystemMessageId YOU_DROPPED_S1;
	
	/**
	 * ID: 299<br>
	 * Message: $c1が$s2を$s3個手に入れました。
	 */
	public static final SystemMessageId C1_OBTAINED_S3_S2;
	
	/**
	 * ID: 300<br>
	 * Message: $c1が$s2を手に入れました。
	 */
	public static final SystemMessageId C1_OBTAINED_S2;
	
	/**
	 * ID: 301<br>
	 * Message: $s1 $s2個が消えました。
	 */
	public static final SystemMessageId S2_S1_DISAPPEARED;
	
	/**
	 * ID: 302<br>
	 * Message: $s1が消えました。
	 */
	public static final SystemMessageId S1_DISAPPEARED;
	
	/**
	 * ID: 303<br>
	 * Message: 強化するアイテムを選択してください。
	 */
	public static final SystemMessageId SELECT_ITEM_TO_ENCHANT;
	
	/**
	 * ID: 304<br>
	 * Message: 血盟員 $s1がゲームに接続しました。
	 */
	public static final SystemMessageId CLAN_MEMBER_S1_LOGGED_IN;
	
	/**
	 * ID: 305<br>
	 * Message: パーティへの参加を拒否されました。
	 */
	public static final SystemMessageId PLAYER_DECLINED;
	
	/**
	 * ID: 306<br>
	 * Message: キャラクターの削除に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_DELETE_CHAR;
	
	/**
	 * ID: 307<br>
	 * Message: 倉庫とのトレードに失敗しました。
	 */
	public static final SystemMessageId CANNOT_TRADE_WAREHOUSE_KEEPER;
	
	/**
	 * ID: 308<br>
	 * Message: 血盟の勧誘に失敗しました。
	 */
	public static final SystemMessageId PLAYER_DECLINED_CLAN_INVITATION;
	
	/**
	 * ID: 309<br>
	 * Message: 血盟員の除名に成功しました。
	 */
	public static final SystemMessageId YOU_HAVE_SUCCEEDED_IN_EXPELLING_CLAN_MEMBER;
	
	/**
	 * ID: 310<br>
	 * Message: 血盟員の除名に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_EXPEL_CLAN_MEMBER;
	
	/**
	 * ID: 311<br>
	 * Message: 血盟戦の申し込みが受け入れられました。
	 */
	public static final SystemMessageId CLAN_WAR_DECLARATION_ACCEPTED;
	
	/**
	 * ID: 312<br>
	 * Message: 血盟戦の申し込みが拒否されました。
	 */
	public static final SystemMessageId CLAN_WAR_DECLARATION_REFUSED;
	
	/**
	 * ID: 313<br>
	 * Message: 終戦の申し込みが受け入れられました。
	 */
	public static final SystemMessageId CEASE_WAR_REQUEST_ACCEPTED;
	
	/**
	 * ID: 314<br>
	 * Message: 降伏に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_SURRENDER;
	
	/**
	 * ID: 315<br>
	 * Message: 個人降伏に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_PERSONALLY_SURRENDER;
	
	/**
	 * ID: 316<br>
	 * Message: パーティからの脱退に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_WITHDRAW_FROM_THE_PARTY;
	
	/**
	 * ID: 317<br>
	 * Message: パーティ メンバーの追放に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_EXPEL_THE_PARTY_MEMBER;
	
	/**
	 * ID: 318<br>
	 * Message: パーティの解散に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_DISPERSE_THE_PARTY;
	
	/**
	 * ID: 319<br>
	 * Message: アンロックできないドアです。
	 */
	public static final SystemMessageId UNABLE_TO_UNLOCK_DOOR;
	
	/**
	 * ID: 320<br>
	 * Message: アンロックに失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_UNLOCK_DOOR;
	
	/**
	 * ID: 321<br>
	 * Message: ロックされていません。
	 */
	public static final SystemMessageId ITS_NOT_LOCKED;
	
	/**
	 * ID: 322<br>
	 * Message: 販売額を決めてください。
	 */
	public static final SystemMessageId DECIDE_SALES_PRICE;
	
	/**
	 * ID: 323<br>
	 * Message: 気が$s1段階に上昇しました。
	 */
	public static final SystemMessageId FORCE_INCREASED_TO_S1;
	
	/**
	 * ID: 324<br>
	 * Message: これ以上は気を上げられません。
	 */
	public static final SystemMessageId FORCE_MAXLEVEL_REACHED;
	
	/**
	 * ID: 325<br>
	 * Message: 死体がすでになくなりました。
	 */
	public static final SystemMessageId CORPSE_ALREADY_DISAPPEARED;
	
	/**
	 * ID: 326<br>
	 * Message: 対象をリストから選択してください。
	 */
	public static final SystemMessageId SELECT_TARGET_FROM_LIST;
	
	/**
	 * ID: 327<br>
	 * Message: 80文字以下にしてください。
	 */
	public static final SystemMessageId CANNOT_EXCEED_80_CHARACTERS;
	
	/**
	 * ID: 328<br>
	 * Message: 題目を入力してください。
	 */
	public static final SystemMessageId PLEASE_INPUT_TITLE_LESS_128_CHARACTERS;
	
	/**
	 * ID: 329<br>
	 * Message: 内容を入力してください。
	 */
	public static final SystemMessageId PLEASE_INPUT_CONTENT_LESS_3000_CHARACTERS;
	
	/**
	 * ID: 330<br>
	 * Message: コメントは128文字以内で入力してください。
	 */
	public static final SystemMessageId ONE_LINE_RESPONSE_NOT_EXCEED_128_CHARACTERS;
	
	/**
	 * ID: 331<br>
	 * Message: $s1のSPを得ました。
	 */
	public static final SystemMessageId ACQUIRED_S1_SP;
	
	/**
	 * ID: 332<br>
	 * Message: 復活しますか。
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_BE_RESTORED;
	
	/**
	 * ID: 333<br>
	 * Message: コアのバリアによって$s1のダメージを受けました。
	 */
	public static final SystemMessageId S1_DAMAGE_BY_CORE_BARRIER;
	
	/**
	 * ID: 334<br>
	 * Message: メッセージを入力してください。
	 */
	public static final SystemMessageId ENTER_PRIVATE_STORE_MESSAGE;
	
	/**
	 * ID: 335<br>
	 * Message: $s1を中断します。
	 */
	public static final SystemMessageId S1_HAS_BEEN_ABORTED;
	
	/**
	 * ID: 336<br>
	 * Message: $s1をクリスタライズします。よろしいですか。
	 */
	public static final SystemMessageId WISH_TO_CRYSTALLIZE_S1;
	
	/**
	 * ID: 337<br>
	 * Message: ソウル ショットと武器のグレードが一致しません。
	 */
	public static final SystemMessageId SOULSHOTS_GRADE_MISMATCH;
	
	/**
	 * ID: 338<br>
	 * Message: ソウル ショットが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SOULSHOTS;
	
	/**
	 * ID: 339<br>
	 * Message: ソウル ショットが使用できません。
	 */
	public static final SystemMessageId CANNOT_USE_SOULSHOTS;
	
	/**
	 * ID: 340<br>
	 * Message: 個人商店の準備中です。
	 */
	public static final SystemMessageId PRIVATE_STORE_UNDER_WAY;
	
	/**
	 * ID: 341<br>
	 * Message: 材料が足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_MATERIALS;
	
	/**
	 * ID: 342<br>
	 * Message: 精霊の力を使います。
	 */
	public static final SystemMessageId ENABLED_SOULSHOT;
	
	/**
	 * ID: 343<br>
	 * Message: スポイルされていないため、スウィーパーの使用に失敗しました。
	 */
	public static final SystemMessageId SWEEPER_FAILED_TARGET_NOT_SPOILED;
	
	/**
	 * ID: 344<br>
	 * Message: 精霊の力が無くなりました。
	 */
	public static final SystemMessageId SOULSHOTS_DISABLED;
	
	/**
	 * ID: 345<br>
	 * Message: チャット可能な状態です。
	 */
	public static final SystemMessageId CHAT_ENABLED;
	
	/**
	 * ID: 346<br>
	 * Message: チャットが禁止されました。
	 */
	public static final SystemMessageId CHAT_DISABLED;
	
	/**
	 * ID: 347<br>
	 * Message: アイテムの個数が正しくありません。
	 */
	public static final SystemMessageId INCORRECT_ITEM_COUNT;
	
	/**
	 * ID: 348<br>
	 * Message: アイテムの価格が正しくありません。
	 */
	public static final SystemMessageId INCORRECT_ITEM_PRICE;
	
	/**
	 * ID: 349<br>
	 * Message: 個人商店が終了しました。
	 */
	public static final SystemMessageId PRIVATE_STORE_ALREADY_CLOSED;
	
	/**
	 * ID: 350<br>
	 * Message: アイテムが品切れです。
	 */
	public static final SystemMessageId ITEM_OUT_OF_STOCK;
	
	/**
	 * ID: 351<br>
	 * Message: 購入するアイテムの個数が正しくありません。
	 */
	public static final SystemMessageId NOT_ENOUGH_ITEMS;
	
	/**
	 * ID: 352<br>
	 * Message: アイテムが正しくありません。
	 */
	public static final SystemMessageId INCORRECT_ITEM;
	
	/**
	 * ID: 353<br>
	 * Message: 購入に失敗しました。
	 */
	public static final SystemMessageId CANNOT_PURCHASE;
	
	/**
	 * ID: 354<br>
	 * Message: エンチャントがキャンセルされました。
	 */
	public static final SystemMessageId CANCEL_ENCHANT;
	
	/**
	 * ID: 355<br>
	 * Message: エンチャントの条件が一致しません。
	 */
	public static final SystemMessageId INAPPROPRIATE_ENCHANT_CONDITION;
	
	/**
	 * ID: 356<br>
	 * Message: 復活を拒否しました。
	 */
	public static final SystemMessageId REJECT_RESURRECTION;
	
	/**
	 * ID: 357<br>
	 * Message: すでにスポイルにかかっています。
	 */
	public static final SystemMessageId ALREADY_SPOILED;
	
	/**
	 * ID: 358<br>
	 * Message: 攻城戦の終了まであと$s1時間です。
	 */
	public static final SystemMessageId S1_HOURS_UNTIL_SIEGE_CONCLUSION;
	
	/**
	 * ID: 359<br>
	 * Message: 攻城戦の終了まであと$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_UNTIL_SIEGE_CONCLUSION;
	
	/**
	 * ID: 360<br>
	 * Message: 攻城戦の終了まであと$s1秒！
	 */
	public static final SystemMessageId CASTLE_SIEGE_S1_SECONDS_LEFT;
	
	/**
	 * ID: 361<br>
	 * Message: オーバーヒット！
	 */
	public static final SystemMessageId OVER_HIT;
	
	/**
	 * ID: 362<br>
	 * Message: オーバーヒットで$s1のボーナス経験値を得ました。
	 */
	public static final SystemMessageId ACQUIRED_BONUS_EXPERIENCE_THROUGH_OVER_HIT;
	
	/**
	 * ID: 363<br>
	 * Message: チャット可能まであと$s1分です。
	 */
	public static final SystemMessageId CHAT_AVAILABLE_S1_MINUTE;
	
	/**
	 * ID: 364<br>
	 * Message: 検索するユーザー名を入力してください。
	 */
	public static final SystemMessageId ENTER_USER_NAME_TO_SEARCH;
	
	/**
	 * ID: 365<br>
	 * Message: 本当にクリスタライズしますか。
	 */
	public static final SystemMessageId ARE_YOU_SURE;
	
	/**
	 * ID: 366<br>
	 * Message: 髪色を選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_HAIR_COLOR;
	
	/**
	 * ID: 367<br>
	 * Message: 血盟に所属しているキャラクターは削除できません。
	 */
	public static final SystemMessageId CANNOT_REMOVE_CLAN_CHARACTER;
	
	/**
	 * ID: 368<br>
	 * Message: +$s1$s2を装備しました。
	 */
	public static final SystemMessageId S1_S2_EQUIPPED;
	
	/**
	 * ID: 369<br>
	 * Message: +$s1$s2を手に入れました。
	 */
	public static final SystemMessageId YOU_PICKED_UP_A_S1_S2;
	
	/**
	 * ID: 370<br>
	 * Message: +$s1$s2の取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_PICKUP_S1;
	
	/**
	 * ID: 371<br>
	 * Message: +$s1$s2を得ました。
	 */
	public static final SystemMessageId ACQUIRED_S1_S2;
	
	/**
	 * ID: 372<br>
	 * Message: +$s1$s2取得に失敗しました。
	 */
	public static final SystemMessageId FAILED_EARN_S1;
	
	/**
	 * ID: 373<br>
	 * Message: +$s1$s2を破壊します。よろしいですか。
	 */
	public static final SystemMessageId WISH_DESTROY_S1_S2;
	
	/**
	 * ID: 374<br>
	 * Message: +$s1$s2をクリスタライズします。よろしいですか。
	 */
	public static final SystemMessageId WISH_CRYSTALLIZE_S1_S2;
	
	/**
	 * ID: 375<br>
	 * Message: +$s1$s2を落としました。
	 */
	public static final SystemMessageId DROPPED_S1_S2;
	
	/**
	 * ID: 376<br>
	 * Message: $c1が +$s2$s3を手に入れました。
	 */
	public static final SystemMessageId C1_OBTAINED_S2_S3;
	
	/**
	 * ID: 377<br>
	 * Message: +$s1$s2が消えました。
	 */
	public static final SystemMessageId S1_S2_DISAPPEARED;
	
	/**
	 * ID: 378<br>
	 * Message: $c1が $s2を買いました。
	 */
	public static final SystemMessageId C1_PURCHASED_S2;
	
	/**
	 * ID: 379<br>
	 * Message: $c1が +$s2$s3を買いました。
	 */
	public static final SystemMessageId C1_PURCHASED_S2_S3;
	
	/**
	 * ID: 380<br>
	 * Message: $c1が $s2 $s3個を買いました。
	 */
	public static final SystemMessageId C1_PURCHASED_S3_S2_S;
	
	/**
	 * ID: 381<br>
	 * Message: サポートサーバーに繋がりません。
	 */
	public static final SystemMessageId GAME_CLIENT_UNABLE_TO_CONNECT_TO_PETITION_SERVER;
	
	/**
	 * ID: 382<br>
	 * Message: 現在FS・SPT・サービスチーム IDでチェックアウトされているお客様はいません。
	 */
	public static final SystemMessageId NO_USERS_CHECKED_OUT_GM_ID;
	
	/**
	 * ID: 383<br>
	 * Message: サポートの終了をリクエストしました。
	 */
	public static final SystemMessageId REQUEST_CONFIRMED_TO_END_CONSULTATION;
	
	/**
	 * ID: 384<br>
	 * Message: ユーザーがゲーム サーバーに接続していません。
	 */
	public static final SystemMessageId CLIENT_NOT_LOGGED_ONTO_GAME_SERVER;
	
	/**
	 * ID: 385<br>
	 * Message: サポートの開始をリクエストしました。
	 */
	public static final SystemMessageId REQUEST_CONFIRMED_TO_BEGIN_CONSULTATION;
	
	/**
	 * ID: 386<br>
	 * Message: サポート要請をするには、内容を6文字以上入力してください。
	 */
	public static final SystemMessageId PETITION_MORE_THAN_FIVE_CHARACTERS;
	
	/**
	 * ID: 387<br>
	 * Message: サポートが終了しました。\nサポートに対する評価をして頂ければ幸いです。
	 */
	public static final SystemMessageId THIS_END_THE_PETITION_PLEASE_PROVIDE_FEEDBACK;
	
	/**
	 * ID: 388<br>
	 * Message: 現在、対話可能状態ではありません。
	 */
	public static final SystemMessageId NOT_UNDER_PETITION_CONSULTATION;
	
	/**
	 * ID: 389<br>
	 * Message: - サポートを受け付けました。\n - 受付番号は$s1番です。
	 */
	public static final SystemMessageId PETITION_ACCEPTED_RECENT_NO_S1;
	
	/**
	 * ID: 390<br>
	 * Message: すでにサポート要請を受け付けております。
	 */
	public static final SystemMessageId ONLY_ONE_ACTIVE_PETITION_AT_TIME;
	
	/**
	 * ID: 391<br>
	 * Message: 受付番号$s1番がキャンセルされました。
	 */
	public static final SystemMessageId RECENT_NO_S1_CANCELED;
	
	/**
	 * ID: 392<br>
	 * Message: 現在サポート対応中です。
	 */
	public static final SystemMessageId UNDER_PETITION_ADVICE;
	
	/**
	 * ID: 393<br>
	 * Message: サポート要請のキャンセルに失敗しました。しばらくしてからやり直してください。
	 */
	public static final SystemMessageId FAILED_CANCEL_PETITION_TRY_LATER;
	
	/**
	 * ID: 394<br>
	 * Message: $c1とのサポートを開始しました。
	 */
	public static final SystemMessageId STARTING_PETITION_WITH_C1;
	
	/**
	 * ID: 395<br>
	 * Message: $c1とのサポートが終了しました。
	 */
	public static final SystemMessageId PETITION_ENDED_WITH_C1;
	
	/**
	 * ID: 396<br>
	 * Message: 公式サイト（http://lineage2.plaync.jp/）でパスワードの変更またはセキュリティカードの再発行をしてから接続してください。
	 */
	public static final SystemMessageId TRY_AGAIN_AFTER_CHANGING_PASSWORD;
	
	/**
	 * ID: 397<br>
	 * Message: 有料アカウントではありません。
	 */
	public static final SystemMessageId NO_PAID_ACCOUNT;
	
	/**
	 * ID: 398<br>
	 * Message: 定量制の残り時間がありません。
	 */
	public static final SystemMessageId NO_TIME_LEFT_ON_ACCOUNT;
	
	/**
	 * ID: 399<br>
	 * Message: システムエラーです。
	 */
	public static final SystemMessageId SYSTEM_ERROR;
	
	/**
	 * ID: 400<br>
	 * Message: $s1を捨てます。よろしいですか。
	 */
	public static final SystemMessageId WISH_TO_DROP_S1;
	
	/**
	 * ID: 401<br>
	 * Message: 進行中のクエストが多過ぎます。
	 */
	public static final SystemMessageId TOO_MANY_QUESTS;
	
	/**
	 * ID: 402<br>
	 * Message: 乗船券がないと乗船できません。
	 */
	public static final SystemMessageId NOT_CORRECT_BOAT_TICKET;
	
	/**
	 * ID: 403<br>
	 * Message: 所持できるアデナの限界を超えました。
	 */
	public static final SystemMessageId EXCEECED_POCKET_ADENA_LIMIT;
	
	/**
	 * ID: 404<br>
	 * Message: アイテム調合スキルのレベルが低過ぎます。
	 */
	public static final SystemMessageId CREATE_LVL_TOO_LOW_TO_REGISTER;
	
	/**
	 * ID: 405<br>
	 * Message: 商品価格の総額が大き過ぎます。
	 */
	public static final SystemMessageId TOTAL_PRICE_TOO_HIGH;
	
	/**
	 * ID: 406<br>
	 * Message: サポートの申請を受け付けました。
	 */
	public static final SystemMessageId PETITION_APP_ACCEPTED;
	
	/**
	 * ID: 407<br>
	 * Message: 現在対応中のサポート要請です。
	 */
	public static final SystemMessageId PETITION_UNDER_PROCESS;
	
	/**
	 * ID: 408<br>
	 * Message: 攻城戦の設定期間
	 */
	public static final SystemMessageId SET_PERIOD;
	
	/**
	 * ID: 409<br>
	 * Message: 攻城戦の設定まで$s1時間$s2分$s3秒です。
	 */
	public static final SystemMessageId SET_TIME_S1_S2_S3;
	
	/**
	 * ID: 410<br>
	 * Message: 攻城戦の登録期間
	 */
	public static final SystemMessageId REGISTRATION_PERIOD;
	
	/**
	 * ID: 411<br>
	 * Message: 攻城戦の登録まで$s1時間$s2分$s3秒です。
	 */
	public static final SystemMessageId REGISTRATION_TIME_S1_S2_S3;
	
	/**
	 * ID: 412<br>
	 * Message: 攻城戦の開始まで$s1時間$s2分$s4秒です。
	 */
	public static final SystemMessageId BATTLE_BEGINS_S1_S2_S3;
	
	/**
	 * ID: 413<br>
	 * Message: 攻城戦の終了まで$s1時間$s2分$s5秒です。
	 */
	public static final SystemMessageId BATTLE_ENDS_S1_S2_S3;
	
	/**
	 * ID: 414<br>
	 * Message: 攻城戦の待機期間
	 */
	public static final SystemMessageId STANDBY;
	
	/**
	 * ID: 415<br>
	 * Message: 攻城戦中
	 */
	public static final SystemMessageId UNDER_SIEGE;
	
	/**
	 * ID: 416<br>
	 * Message: トレードできない状態です。
	 */
	public static final SystemMessageId ITEM_CANNOT_EXCHANGE;
	
	/**
	 * ID: 417<br>
	 * Message: $s1を装備解除しました。
	 */
	public static final SystemMessageId S1_DISARMED;
	
	/**
	 * ID: 419<br>
	 * Message: 使用時間は残り$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_USAGE_LEFT;
	
	/**
	 * ID: 420<br>
	 * Message: 定量使用時間が終了しました。
	 */
	public static final SystemMessageId TIME_EXPIRED;
	
	/**
	 * ID: 421<br>
	 * Message: 他のユーザーが同じアカウントでログインしました。
	 */
	public static final SystemMessageId ANOTHER_LOGIN_WITH_ACCOUNT;
	
	/**
	 * ID: 422<br>
	 * Message: 許容重量を超えました。
	 */
	public static final SystemMessageId WEIGHT_LIMIT_EXCEEDED;
	
	/**
	 * ID: 423<br>
	 * Message: 強化スクロールの使用がキャンセルされました。
	 */
	public static final SystemMessageId ENCHANT_SCROLL_CANCELLED;
	
	/**
	 * ID: 424<br>
	 * Message: スクロールの強化条件に合いません。
	 */
	public static final SystemMessageId DOES_NOT_FIT_SCROLL_CONDITIONS;
	
	/**
	 * ID: 425<br>
	 * Message: アイテム調合スキルのレベルが低過ぎるか、アイテム調合スキルがありません。
	 */
	public static final SystemMessageId CREATE_LVL_TOO_LOW_TO_REGISTER2;
	
	/**
	 * ID: 445<br>
	 * Message: （退会受付番号： $s1）
	 */
	public static final SystemMessageId REFERENCE_MEMBERSHIP_WITHDRAWAL_S1;
	
	/**
	 * ID: 447<br>
	 * Message: 。
	 */
	public static final SystemMessageId DOT;
	
	/**
	 * ID: 448<br>
	 * Message: システム エラーです。しばらくしてからログインしてください。
	 */
	public static final SystemMessageId SYSTEM_ERROR_LOGIN_LATER;
	
	/**
	 * ID: 449<br>
	 * Message: アカウントとパスワードが一致しません。
	 */
	public static final SystemMessageId PASSWORD_ENTERED_INCORRECT1;
	
	/**
	 * ID: 450<br>
	 * Message: アカウント情報を再確認してログインしてください。
	 */
	public static final SystemMessageId CONFIRM_ACCOUNT_LOGIN_LATER;
	
	/**
	 * ID: 451<br>
	 * Message: アカウントとパスワードが一致しません。
	 */
	public static final SystemMessageId PASSWORD_ENTERED_INCORRECT2;
	
	/**
	 * ID: 452<br>
	 * Message: アカウント情報を再確認してログインしてください。
	 */
	public static final SystemMessageId PLEASE_CONFIRM_ACCOUNT_LOGIN_LATER;
	
	/**
	 * ID: 453<br>
	 * Message: アカウント情報が正しくありません。
	 */
	public static final SystemMessageId ACCOUNT_INFORMATION_INCORRECT;
	
	/**
	 * ID: 455<br>
	 * Message: すでに使用中のアカウントです。ログインできません。
	 */
	public static final SystemMessageId ACCOUNT_IN_USE;
	
	/**
	 * ID: 456<br>
	 * Message: リネージュII は、15才以上の方のみご利用いただけます。PvPサーバーは18才以上の方のみご利用いただけます。
	 */
	public static final SystemMessageId LINAGE_MINIMUM_AGE;
	
	/**
	 * ID: 457<br>
	 * Message: 現在、ゲーム サーバーのメンテナンス中です。しばらくしてからログインしてください。
	 */
	public static final SystemMessageId SERVER_MAINTENANCE;
	
	/**
	 * ID: 458<br>
	 * Message: プレイ可能期間が終了したため、ログインできません。
	 */
	public static final SystemMessageId USAGE_TERM_EXPIRED;
	
	/**
	 * ID: 460<br>
	 * Message: 公認ネットカフェからご利用のお客様は店舗スタッフにご確認ください。
	 */
	public static final SystemMessageId TO_REACTIVATE_YOUR_ACCOUNT;
	
	/**
	 * ID: 461<br>
	 * Message: ゲーム サーバーの接続に失敗しました。
	 */
	public static final SystemMessageId ACCESS_FAILED;
	
	/**
	 * ID: 461<br>
	 * Message: ゲーム サーバーの接続に失敗しました。
	 */
	public static final SystemMessageId PLEASE_TRY_AGAIN_LATER;
	
	/**
	 * ID: 464<br>
	 * Message: 同盟主のみが行えます。
	 */
	public static final SystemMessageId FEATURE_ONLY_FOR_ALLIANCE_LEADER;
	
	/**
	 * ID: 465<br>
	 * Message: 同盟に加入していません。
	 */
	public static final SystemMessageId NO_CURRENT_ALLIANCES;
	
	/**
	 * ID: 466<br>
	 * Message: 満員なので新しい血盟は受け入れられません。
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_THE_LIMIT;
	
	/**
	 * ID: 467<br>
	 * Message: 血盟追放後1日経っていない場合、新しい血盟は受け入れられません。
	 */
	public static final SystemMessageId CANT_INVITE_CLAN_WITHIN_1_DAY;
	
	/**
	 * ID: 468<br>
	 * Message: 追放されたり脱退した血盟は、1日以内には同盟に加入できません。
	 */
	public static final SystemMessageId CANT_ENTER_ALLIANCE_WITHIN_1_DAY;
	
	/**
	 * ID: 469<br>
	 * Message: 血盟戦関係にある血盟とは同盟を締結できません。
	 */
	public static final SystemMessageId MAY_NOT_ALLY_CLAN_BATTLE;
	
	/**
	 * ID: 470<br>
	 * Message: 血盟主のみが同盟の脱退申請ができます。
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_WITHDRAW_ALLY;
	
	/**
	 * ID: 471<br>
	 * Message: 同盟主は脱退できません。
	 */
	public static final SystemMessageId ALLIANCE_LEADER_CANT_WITHDRAW;
	
	/**
	 * ID: 472<br>
	 * Message: 自分の血盟は追放できません。
	 */
	public static final SystemMessageId CANNOT_EXPEL_YOURSELF;
	
	/**
	 * ID: 473<br>
	 * Message: 同じ同盟に属する血盟ではありません。
	 */
	public static final SystemMessageId DIFFERENT_ALLIANCE;
	
	/**
	 * ID: 474<br>
	 * Message: 該当血盟が存在しません。
	 */
	public static final SystemMessageId CLAN_DOESNT_EXISTS;
	
	/**
	 * ID: 475<br>
	 * Message: 同じ同盟に属する血盟ではありません。
	 */
	public static final SystemMessageId DIFFERENT_ALLIANCE2;
	
	/**
	 * ID: 476<br>
	 * Message: 画像ファイルのサイズが正しくありません。8x12ピクセルに指定してください。
	 */
	public static final SystemMessageId ADJUST_IMAGE_8_12;
	
	/**
	 * ID: 477<br>
	 * Message: 応答がなかったため、同盟の勧誘がキャンセルされました。
	 */
	public static final SystemMessageId NO_RESPONSE_TO_ALLY_INVITATION;
	
	/**
	 * ID: 478<br>
	 * Message: 勧誘に応答しなかったため、同盟の加入がキャンセルされました。
	 */
	public static final SystemMessageId YOU_DID_NOT_RESPOND_TO_ALLY_INVITATION;
	
	/**
	 * ID: 479<br>
	 * Message: $s1が新しい友人に登録されました。
	 */
	public static final SystemMessageId S1_JOINED_AS_FRIEND;
	
	/**
	 * ID: 480<br>
	 * Message: 友人リストを確認してください。
	 */
	public static final SystemMessageId PLEASE_CHECK_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 481<br>
	 * Message: $s1が友人リストから削除されました。
	 */
	public static final SystemMessageId S1_HAS_BEEN_DELETED_FROM_YOUR_FRIENDS_LIST;
	
	/**
	 * ID: 482<br>
	 * Message: 自分自身を友人として登録することはできません。
	 */
	public static final SystemMessageId YOU_CANNOT_ADD_YOURSELF_TO_YOUR_OWN_FRIENDS_LIST;
	
	/**
	 * ID: 483<br>
	 * Message: 友人リストがまだ作成されていません。しばらくしてからご利用ください。
	 */
	public static final SystemMessageId FUNCTION_INACCESSIBLE_NOW;
	
	/**
	 * ID: 484<br>
	 * Message: すでに友人リストに登録されています。
	 */
	public static final SystemMessageId S1_ALREADY_IN_FRIENDS_LIST;
	
	/**
	 * ID: 485<br>
	 * Message: 友人になることを勧めたユーザーがいません。
	 */
	public static final SystemMessageId NO_NEW_INVITATIONS_ACCEPTED;
	
	/**
	 * ID: 486<br>
	 * Message: 友人リストに登録されているユーザーではありません。
	 */
	public static final SystemMessageId THE_USER_NOT_IN_FRIENDS_LIST;
	
	/**
	 * ID: 487<br>
	 * Message: ======<友人リスト>======
	 */
	public static final SystemMessageId FRIEND_LIST_HEADER;
	
	/**
	 * ID: 488<br>
	 * Message: $s1（ステータス：オンライン）
	 */
	public static final SystemMessageId S1_ONLINE;
	
	/**
	 * ID: 489<br>
	 * Message: $s1（ステータス：オフライン）
	 */
	public static final SystemMessageId S1_OFFLINE;
	
	/**
	 * ID: 490<br>
	 * Message: ========================
	 */
	public static final SystemMessageId FRIEND_LIST_FOOTER;
	
	/**
	 * ID: 491<br>
	 * Message: =======<同盟情報>=======
	 */
	public static final SystemMessageId ALLIANCE_INFO_HEAD;
	
	/**
	 * ID: 492<br>
	 * Message: 同盟名：$s1
	 */
	public static final SystemMessageId ALLIANCE_NAME_S1;
	
	/**
	 * ID: 493<br>
	 * Message: 接続：$s1人/合計$s2人
	 */
	public static final SystemMessageId CONNECTION_S1_TOTAL_S2;
	
	/**
	 * ID: 494<br>
	 * Message: 同盟主：$s1血盟の$s2
	 */
	public static final SystemMessageId ALLIANCE_LEADER_S2_OF_S1;
	
	/**
	 * ID: 495<br>
	 * Message: 所属血盟：合計$s1血盟
	 */
	public static final SystemMessageId ALLIANCE_CLAN_TOTAL_S1;
	
	/**
	 * ID: 496<br>
	 * Message: =====<所属血盟の情報>=====
	 */
	public static final SystemMessageId CLAN_INFO_HEAD;
	
	/**
	 * ID: 497<br>
	 * Message: 血盟名：$s1
	 */
	public static final SystemMessageId CLAN_INFO_NAME_S1;
	
	/**
	 * ID: 498<br>
	 * Message: 血盟主：$s1
	 */
	public static final SystemMessageId CLAN_INFO_LEADER_S1;
	
	/**
	 * ID: 499<br>
	 * Message: 血盟レベル：$s1
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
	 * Message: すでに同盟に加入しています。
	 */
	public static final SystemMessageId ALREADY_JOINED_ALLIANCE;
	
	/**
	 * ID: 503<br>
	 * Message: 友人$s1 がログインしました。
	 */
	public static final SystemMessageId FRIEND_S1_HAS_LOGGED_IN;
	
	/**
	 * ID: 504<br>
	 * Message: 血盟主のみが同盟を創設できます。
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_CREATE_ALLIANCE;
	
	/**
	 * ID: 505<br>
	 * Message: 同盟解散後の当日は新たな同盟の創設はできません。
	 */
	public static final SystemMessageId CANT_CREATE_ALLIANCE_10_DAYS_DISOLUTION;
	
	/**
	 * ID: 506<br>
	 * Message: 同盟名が正しくありません。
	 */
	public static final SystemMessageId INCORRECT_ALLIANCE_NAME;
	
	/**
	 * ID: 507<br>
	 * Message: 同盟名が長過ぎます。
	 */
	public static final SystemMessageId INCORRECT_ALLIANCE_NAME_LENGTH;
	
	/**
	 * ID: 508<br>
	 * Message: すでに存在する同盟です。
	 */
	public static final SystemMessageId ALLIANCE_ALREADY_EXISTS;
	
	/**
	 * ID: 509<br>
	 * Message: 同盟中の血盟と攻城戦で敵関係として申請されているので、受付できません。
	 */
	public static final SystemMessageId CANT_ACCEPT_ALLY_ENEMY_FOR_SIEGE;
	
	/**
	 * ID: 510<br>
	 * Message: 同盟に招待しました。
	 */
	public static final SystemMessageId YOU_INVITED_FOR_ALLIANCE;
	
	/**
	 * ID: 511<br>
	 * Message: 同盟に招待する人を選択してください。
	 */
	public static final SystemMessageId SELECT_USER_TO_INVITE;
	
	/**
	 * ID: 512<br>
	 * Message: 本当に同盟から脱退しますか。脱退後1日間、他の同盟に再加入できません。
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_WITHDRW;
	
	/**
	 * ID: 513<br>
	 * Message: 追放する相手の血盟名を入力してください。
	 */
	public static final SystemMessageId ENTER_NAME_CLAN_TO_EXPEL;
	
	/**
	 * ID: 514<br>
	 * Message: 本当に解散しますか。解散当日は同盟を再創設できません。
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_DISOLVE;
	
	/**
	 * ID: 516<br>
	 * Message: $s1 があなたを友人として招待しました。
	 */
	public static final SystemMessageId SI_INVITED_YOU_AS_FRIEND;
	
	/**
	 * ID: 517<br>
	 * Message: 同盟を許可しました。
	 */
	public static final SystemMessageId YOU_ACCEPTED_ALLIANCE;
	
	/**
	 * ID: 518<br>
	 * Message: 同盟の招待に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_INVITE_CLAN_IN_ALLIANCE;
	
	/**
	 * ID: 519<br>
	 * Message: 同盟から脱退しました。
	 */
	public static final SystemMessageId YOU_HAVE_WITHDRAWN_FROM_ALLIANCE;
	
	/**
	 * ID: 520<br>
	 * Message: 同盟の脱退に失敗しました。
	 */
	public static final SystemMessageId YOU_HAVE_FAILED_TO_WITHDRAWN_FROM_ALLIANCE;
	
	/**
	 * ID: 521<br>
	 * Message: 血盟の追放に成功しました。
	 */
	public static final SystemMessageId YOU_HAVE_EXPELED_A_CLAN;
	
	/**
	 * ID: 522<br>
	 * Message: 血盟の追放に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_EXPELED_A_CLAN;
	
	/**
	 * ID: 523<br>
	 * Message: 同盟を解散しました。
	 */
	public static final SystemMessageId ALLIANCE_DISOLVED;
	
	/**
	 * ID: 524<br>
	 * Message: 同盟の解散に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_DISOLVE_ALLIANCE;
	
	/**
	 * ID: 525<br>
	 * Message: 友人の招待に成功しました。
	 */
	public static final SystemMessageId YOU_HAVE_SUCCEEDED_INVITING_FRIEND;
	
	/**
	 * ID: 526<br>
	 * Message: 友人の招待に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_INVITE_A_FRIEND;
	
	/**
	 * ID: 527<br>
	 * Message: $s1同盟の同盟主$s2 に同盟を申し込まれました。
	 */
	public static final SystemMessageId S2_ALLIANCE_LEADER_OF_S1_REQUESTED_ALLIANCE;
	
	/**
	 * ID: 530<br>
	 * Message: スピリット ショットが武器のグレードと一致しません。
	 */
	public static final SystemMessageId SPIRITSHOTS_GRADE_MISMATCH;
	
	/**
	 * ID: 531<br>
	 * Message: スピリット ショットが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SPIRITSHOTS;
	
	/**
	 * ID: 532<br>
	 * Message: スピリット ショットが使えません。
	 */
	public static final SystemMessageId CANNOT_USE_SPIRITSHOTS;
	
	/**
	 * ID: 533<br>
	 * Message: マナの力を使います。
	 */
	public static final SystemMessageId ENABLED_SPIRITSHOT;
	
	/**
	 * ID: 534<br>
	 * Message: マナの力がなくなりました。
	 */
	public static final SystemMessageId DISABLED_SPIRITSHOT;
	
	/**
	 * ID: 536<br>
	 * Message: アデナをどれくらいインベントリに移しますか。
	 */
	public static final SystemMessageId HOW_MUCH_ADENA_TRANSFER;
	
	/**
	 * ID: 537<br>
	 * Message: いくつ移しますか。
	 */
	public static final SystemMessageId HOW_MUCH_TRANSFER;
	
	/**
	 * ID: 538<br>
	 * Message: SPが$s1減りました。
	 */
	public static final SystemMessageId SP_DECREASED_S1;
	
	/**
	 * ID: 539<br>
	 * Message: 経験値が$s1減りました。
	 */
	public static final SystemMessageId EXP_DECREASED_BY_S1;
	
	/**
	 * ID: 540<br>
	 * Message: 血盟主は削除できません。血盟を解散してからやり直してみてください。
	 */
	public static final SystemMessageId CLAN_LEADERS_MAY_NOT_BE_DELETED;
	
	/**
	 * ID: 541<br>
	 * Message: 血盟員は削除できません。血盟を脱退してからやり直してみてください。
	 */
	public static final SystemMessageId CLAN_MEMBER_MAY_NOT_BE_DELETED;
	
	/**
	 * ID: 542<br>
	 * Message: NPCサーバーが作動していないため、ペットを召喚できません。
	 */
	public static final SystemMessageId THE_NPC_SERVER_IS_CURRENTLY_DOWN;
	
	/**
	 * ID: 543<br>
	 * Message: すでにペットがいます。
	 */
	public static final SystemMessageId YOU_ALREADY_HAVE_A_PET;
	
	/**
	 * ID: 544<br>
	 * Message: ペットに渡せないアイテムです。
	 */
	public static final SystemMessageId ITEM_NOT_FOR_PETS;
	
	/**
	 * ID: 545<br>
	 * Message: ペットのインベントリのスロット数制限によりアイテムが渡せません。
	 */
	public static final SystemMessageId YOUR_PET_CANNOT_CARRY_ANY_MORE_ITEMS;
	
	/**
	 * ID: 546<br>
	 * Message: ペット用インベントリの重量制限によりアイテムが渡せません。
	 */
	public static final SystemMessageId UNABLE_TO_PLACE_ITEM_YOUR_PET_IS_TOO_ENCUMBERED;
	
	/**
	 * ID: 547<br>
	 * Message: ペットを召喚します。
	 */
	public static final SystemMessageId SUMMON_A_PET;
	
	/**
	 * ID: 548<br>
	 * Message: ペットの名前は8文字まで可能です。
	 */
	public static final SystemMessageId NAMING_PETNAME_UP_TO_8CHARS;
	
	/**
	 * ID: 549<br>
	 * Message: 同盟を締結するには、血盟のレベルが5以上でなければなりません。
	 */
	public static final SystemMessageId TO_CREATE_AN_ALLY_YOU_CLAN_MUST_BE_LEVEL_5_OR_HIGHER;
	
	/**
	 * ID: 550<br>
	 * Message: 解散猶予期間中は同盟の創設はできません。
	 */
	public static final SystemMessageId YOU_MAY_NOT_CREATE_ALLY_WHILE_DISSOLVING;
	
	/**
	 * ID: 551<br>
	 * Message: 解散猶予期間中は血盟レベルを上げられません。
	 */
	public static final SystemMessageId CANNOT_RISE_LEVEL_WHILE_DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 552<br>
	 * Message: 解散猶予期間中はエンブレムの登録および削除はできません。
	 */
	public static final SystemMessageId CANNOT_SET_CREST_WHILE_DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 553<br>
	 * Message: 相手の血盟は解散が申し込まれています。
	 */
	public static final SystemMessageId OPPOSING_CLAN_APPLIED_DISPERSION;
	
	/**
	 * ID: 554<br>
	 * Message: 同盟に属する血盟は解散できません。
	 */
	public static final SystemMessageId CANNOT_DISPERSE_THE_CLANS_IN_ALLY;
	
	/**
	 * ID: 555<br>
	 * Message: 持っているアイテムが重過ぎて動けません。
	 */
	public static final SystemMessageId CANT_MOVE_TOO_ENCUMBERED;
	
	/**
	 * ID: 556<br>
	 * Message: 現在の状態では動けません。
	 */
	public static final SystemMessageId CANT_MOVE_IN_THIS_STATE;
	
	/**
	 * ID: 557<br>
	 * Message: ペットが召喚されているため、破壊できません。
	 */
	public static final SystemMessageId PET_SUMMONED_MAY_NOT_DESTROYED;
	
	/**
	 * ID: 558<br>
	 * Message: ペットが召喚されているため、地面に捨てられません。
	 */
	public static final SystemMessageId PET_SUMMONED_MAY_NOT_LET_GO;
	
	/**
	 * ID: 559<br>
	 * Message: $c1から$s2を買いました。
	 */
	public static final SystemMessageId PURCHASED_S2_FROM_C1;
	
	/**
	 * ID: 560<br>
	 * Message: $c1から+$s2$s3を買いました。
	 */
	public static final SystemMessageId PURCHASED_S2_S3_FROM_C1;
	
	/**
	 * ID: 561<br>
	 * Message: $c1から$s2 $s3個を買いました。
	 */
	public static final SystemMessageId PURCHASED_S3_S2_S_FROM_C1;
	
	/**
	 * ID: 562<br>
	 * Message: クリスタライズのスキル レベルが低いのでクリスタライズできません。
	 */
	public static final SystemMessageId CRYSTALLIZE_LEVEL_TOO_LOW;
	
	/**
	 * ID: 563<br>
	 * Message: 攻撃欲求の解除に失敗しました。
	 */
	public static final SystemMessageId FAILED_DISABLE_TARGET;
	
	/**
	 * ID: 564<br>
	 * Message: 攻撃欲求の変更に失敗しました。
	 */
	public static final SystemMessageId FAILED_CHANGE_TARGET;
	
	/**
	 * ID: 565<br>
	 * Message: 運勢が足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_LUCK;
	
	/**
	 * ID: 566<br>
	 * Message: 混乱に失敗しました。
	 */
	public static final SystemMessageId CONFUSION_FAILED;
	
	/**
	 * ID: 567<br>
	 * Message: フィアーに失敗しました。
	 */
	public static final SystemMessageId FEAR_FAILED;
	
	/**
	 * ID: 568<br>
	 * Message: キュービックの召喚に失敗しました。
	 */
	public static final SystemMessageId CUBIC_SUMMONING_FAILED;
	
	/**
	 * ID: 572<br>
	 * Message: $c1 のパーティ勧誘に応じますか。（アイテム分配：拾った人が所有）
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_FINDERS_KEEPERS;
	
	/**
	 * ID: 573<br>
	 * Message: $c1 のパーティ勧誘に応じますか。（アイテム分配：パーティ メンバーにランダムで）
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_RANDOM;
	
	/**
	 * ID: 574<br>
	 * Message: 召喚獣やペットがいません。
	 */
	public static final SystemMessageId PETS_ARE_NOT_AVAILABLE_AT_THIS_TIME;
	
	/**
	 * ID: 575<br>
	 * Message: いくらのアデナをペットに移しますか。
	 */
	public static final SystemMessageId HOW_MUCH_ADENA_TRANSFER_TO_PET;
	
	/**
	 * ID: 576<br>
	 * Message: いくつ移しますか。
	 */
	public static final SystemMessageId HOW_MUCH_TRANSFER2;
	
	/**
	 * ID: 577<br>
	 * Message: トレードや個人商店中には召喚できません。
	 */
	public static final SystemMessageId CANNOT_SUMMON_DURING_TRADE_SHOP;
	
	/**
	 * ID: 578<br>
	 * Message: 戦闘中は召喚できません。
	 */
	public static final SystemMessageId YOU_CANNOT_SUMMON_IN_COMBAT;
	
	/**
	 * ID: 579<br>
	 * Message: 戦闘中のペットは戻せません。
	 */
	public static final SystemMessageId PET_CANNOT_SENT_BACK_DURING_BATTLE;
	
	/**
	 * ID: 580<br>
	 * Message: 複数のペットや召喚獣を一度に使うことはできません。
	 */
	public static final SystemMessageId SUMMON_ONLY_ONE;
	
	/**
	 * ID: 581<br>
	 * Message: 名前にスペースが入っています。
	 */
	public static final SystemMessageId NAMING_THERE_IS_A_SPACE;
	
	/**
	 * ID: 582<br>
	 * Message: 禁止されたキャラクター名です。
	 */
	public static final SystemMessageId NAMING_INAPPROPRIATE_CHARACTER_NAME;
	
	/**
	 * ID: 583<br>
	 * Message: 名前に禁止された単語が入っています。
	 */
	public static final SystemMessageId NAMING_INCLUDES_FORBIDDEN_WORDS;
	
	/**
	 * ID: 584<br>
	 * Message: すでに同じ名前のペットがいます。
	 */
	public static final SystemMessageId NAMING_ALREADY_IN_USE_BY_ANOTHER_PET;
	
	/**
	 * ID: 585<br>
	 * Message: 購入価格を決めてください。
	 */
	public static final SystemMessageId DECIDE_ON_PRICE;
	
	/**
	 * ID: 586<br>
	 * Message: ペットのアイテムはショートカットに登録できません。
	 */
	public static final SystemMessageId PET_NO_SHORTCUT;
	
	/**
	 * ID: 588<br>
	 * Message: ペットのインベントリがいっぱいです。
	 */
	public static final SystemMessageId PET_INVENTORY_FULL;
	
	/**
	 * ID: 589<br>
	 * Message: 死んだペットを戻すことはできません。
	 */
	public static final SystemMessageId DEAD_PET_CANNOT_BE_RETURNED;
	
	/**
	 * ID: 590<br>
	 * Message: 死んだペットにアイテムは移動できません。
	 */
	public static final SystemMessageId CANNOT_GIVE_ITEMS_TO_DEAD_PET;
	
	/**
	 * ID: 591<br>
	 * Message: ペットの名前に無効の文字が入っています。
	 */
	public static final SystemMessageId NAMING_PETNAME_CONTAINS_INVALID_CHARS;
	
	/**
	 * ID: 592<br>
	 * Message: 本当に解散しますか。解散すると召喚アイテムは消えます。
	 */
	public static final SystemMessageId WISH_TO_DISMISS_PET;
	
	/**
	 * ID: 593<br>
	 * Message: 飢えをこらえきれず、ペットがあなたの元を去りました。
	 */
	public static final SystemMessageId STARVING_GRUMPY_AND_FED_UP_YOUR_PET_HAS_LEFT;
	
	/**
	 * ID: 594<br>
	 * Message: 飢えたペットは戻すことができません。
	 */
	public static final SystemMessageId YOU_CANNOT_RESTORE_HUNGRY_PETS;
	
	/**
	 * ID: 595<br>
	 * Message: ペットがあまりにもお腹をすかせています。
	 */
	public static final SystemMessageId YOUR_PET_IS_VERY_HUNGRY;
	
	/**
	 * ID: 596<br>
	 * Message: ペットはどうにか飢えをしのぎましたが、依然としてお腹をすかせています。
	 */
	public static final SystemMessageId YOUR_PET_ATE_A_LITTLE_BUT_IS_STILL_HUNGRY;
	
	/**
	 * ID: 597<br>
	 * Message: ペットが飢えにがまんできないでいます。気をつけてください。
	 */
	public static final SystemMessageId YOUR_PET_IS_VERY_HUNGRY_PLEASE_BE_CAREFULL;
	
	/**
	 * ID: 598<br>
	 * Message: 透明状態のため、一般チャットはできません。
	 */
	public static final SystemMessageId NOT_CHAT_WHILE_INVISIBLE;
	
	/**
	 * ID: 599<br>
	 * Message: サポートから重要なお知らせがあります。チャットはしばらく中止されます。
	 */
	public static final SystemMessageId GM_NOTICE_CHAT_DISABLED;
	
	/**
	 * ID: 600<br>
	 * Message: ペット アイテムは装備できません。
	 */
	public static final SystemMessageId CANNOT_EQUIP_PET_ITEM;
	
	/**
	 * ID: 601<br>
	 * Message: - 受付の待機者数は$s1です。
	 */
	public static final SystemMessageId S1_PETITION_ON_WAITING_LIST;
	
	/**
	 * ID: 602<br>
	 * Message: 現在、サポートシステムが作動していません。しばらくしてからやり直してください。
	 */
	public static final SystemMessageId PETITION_SYSTEM_CURRENT_UNAVAILABLE;
	
	/**
	 * ID: 603<br>
	 * Message: そのアイテムは捨てたりトレードしたりできません。
	 */
	public static final SystemMessageId CANNOT_DISCARD_EXCHANGE_ITEM;
	
	/**
	 * ID: 604<br>
	 * Message: ここではペットや召喚獣を召喚できません。
	 */
	public static final SystemMessageId NOT_CALL_PET_FROM_THIS_LOCATION;
	
	/**
	 * ID: 605<br>
	 * Message: 友人リストには最大128人まで登録できます。
	 */
	public static final SystemMessageId MAY_REGISTER_UP_TO_64_PEOPLE;
	
	/**
	 * ID: 606<br>
	 * Message: 相手の友人リストに登録された人数が128人を超えたので、登録できません。
	 */
	public static final SystemMessageId OTHER_PERSON_ALREADY_64_PEOPLE;
	
	/**
	 * ID: 607<br>
	 * Message: これ以上習うスキルがありません。$s1レベルになってから来てください。
	 */
	public static final SystemMessageId DO_NOT_HAVE_FURTHER_SKILLS_TO_LEARN_S1;
	
	/**
	 * ID: 608<br>
	 * Message: $c1がスウィーパーで$s2 $s3個を手に入れました。
	 */
	public static final SystemMessageId C1_SWEEPED_UP_S3_S2;
	
	/**
	 * ID: 609<br>
	 * Message: $c1がスウィーパーで$s2を手に入れました。
	 */
	public static final SystemMessageId C1_SWEEPED_UP_S2;
	
	/**
	 * ID: 610<br>
	 * Message: HPが足りなかったためスキルが解除されました。
	 */
	public static final SystemMessageId SKILL_REMOVED_DUE_LACK_HP;
	
	/**
	 * ID: 611<br>
	 * Message: 敵を惑わすことに成功しました。
	 */
	public static final SystemMessageId CONFUSING_SUCCEEDED;
	
	/**
	 * ID: 612<br>
	 * Message: スポイル状態になりました。
	 */
	public static final SystemMessageId SPOIL_SUCCESS;
	
	/**
	 * ID: 613<br>
	 * Message: ========<遮断リスト>========
	 */
	public static final SystemMessageId BLOCK_LIST_HEADER;
	
	/**
	 * ID: 614<br>
	 * Message: $c1：$c2
	 */
	public static final SystemMessageId C1_D_C2;
	
	/**
	 * ID: 615<br>
	 * Message: 遮断リストへの登録に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_REGISTER_TO_IGNORE_LIST;
	
	/**
	 * ID: 616<br>
	 * Message: 遮断リストからの削除に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_DELETE_CHARACTER;
	
	/**
	 * ID: 617<br>
	 * Message: $s1を遮断リストに登録しました。
	 */
	public static final SystemMessageId S1_WAS_ADDED_TO_YOUR_IGNORE_LIST;
	
	/**
	 * ID: 618<br>
	 * Message: $s1を遮断リストから削除しました。
	 */
	public static final SystemMessageId S1_WAS_REMOVED_FROM_YOUR_IGNORE_LIST;
	
	/**
	 * ID: 619<br>
	 * Message: $s1があなたを遮断しました。
	 */
	public static final SystemMessageId S1_HAS_ADDED_YOU_TO_IGNORE_LIST;
	
	/**
	 * ID: 620<br>
	 * Message: $s1はあなたを遮断しました。
	 */
	public static final SystemMessageId S1_HAS_ADDED_YOU_TO_IGNORE_LIST2;
	
	/**
	 * ID: 621<br>
	 * Message: 接続が制限されたIPを通じてゲームに接続を試みました。
	 */
	public static final SystemMessageId CONNECTION_RESTRICTED_IP;
	
	/**
	 * ID: 622<br>
	 * Message: 同盟戦中に宣戦布告はできません。
	 */
	public static final SystemMessageId NO_WAR_DURING_ALLY_BATTLE;
	
	/**
	 * ID: 623<br>
	 * Message: 相手の同盟が進行できる同盟戦の数を超えています。
	 */
	public static final SystemMessageId OPPONENT_TOO_MUCH_ALLY_BATTLES1;
	
	/**
	 * ID: 624<br>
	 * Message: $s1同盟の同盟主が接続していません。
	 */
	public static final SystemMessageId S1_LEADER_NOT_CONNECTED;
	
	/**
	 * ID: 625<br>
	 * Message: 同盟戦の終戦要請が拒否されました。
	 */
	public static final SystemMessageId ALLY_BATTLE_TRUCE_DENIED;
	
	/**
	 * ID: 626<br>
	 * Message: $s1血盟が応答しなかったため、宣戦布告が拒否されました。
	 */
	public static final SystemMessageId WAR_PROCLAMATION_HAS_BEEN_REFUSED;
	
	/**
	 * ID: 627<br>
	 * Message: $s1同盟の宣戦布告に応答しなかったため、同盟戦が拒否されました。
	 */
	public static final SystemMessageId YOU_REFUSED_CLAN_WAR_PROCLAMATION;
	
	/**
	 * ID: 628<br>
	 * Message: $s1 同盟とはすでに同盟戦を行ったため、前回の同盟戦から5日が経過しないと宣戦布告できません。
	 */
	public static final SystemMessageId ALREADY_AT_WAR_WITH_S1_WAIT_5_DAYS;
	
	/**
	 * ID: 629<br>
	 * Message: 相手の血盟が進行できる血盟戦の数を超えています。
	 */
	public static final SystemMessageId OPPONENT_TOO_MUCH_ALLY_BATTLES2;
	
	/**
	 * ID: 630<br>
	 * Message: $s1同盟との同盟戦が始まりました。
	 */
	public static final SystemMessageId WAR_WITH_CLAN_BEGUN;
	
	/**
	 * ID: 631<br>
	 * Message: $s1同盟との同盟戦が終了しました。
	 */
	public static final SystemMessageId WAR_WITH_CLAN_ENDED;
	
	/**
	 * ID: 632<br>
	 * Message: $s1同盟との同盟戦で勝利しました！
	 */
	public static final SystemMessageId WON_WAR_OVER_CLAN;
	
	/**
	 * ID: 633<br>
	 * Message: $s1同盟に降伏しました。
	 */
	public static final SystemMessageId SURRENDERED_TO_CLAN;
	
	/**
	 * ID: 634<br>
	 * Message: 同盟主が死亡して、$s1同盟に敗北しました。
	 */
	public static final SystemMessageId DEFEATED_BY_CLAN;
	
	/**
	 * ID: 635<br>
	 * Message: 同盟戦の制限時間が過ぎたため、$s1同盟との同盟戦が終了しました。
	 */
	public static final SystemMessageId TIME_UP_WAR_OVER;
	
	/**
	 * ID: 636<br>
	 * Message: 同盟戦中ではありません。
	 */
	public static final SystemMessageId NOT_INVOLVED_IN_WAR;
	
	/**
	 * ID: 637<br>
	 * Message: 同盟所属の血盟が敵側に登録しました。
	 */
	public static final SystemMessageId ALLY_REGISTERED_SELF_TO_OPPONENT;
	
	/**
	 * ID: 638<br>
	 * Message: すでに攻城戦を申請した状態です。
	 */
	public static final SystemMessageId ALREADY_REQUESTED_SIEGE_BATTLE;
	
	/**
	 * ID: 639<br>
	 * Message: 他の攻城戦に申請した状態なので申請できません。
	 */
	public static final SystemMessageId APPLICATION_DENIED_BECAUSE_ALREADY_SUBMITTED_A_REQUEST_FOR_ANOTHER_SIEGE_BATTLE;
	
	/**
	 * ID: 640<br>
	 * Message: 城守の拒否に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_REFUSE_CASTLE_DEFENSE_AID;
	
	/**
	 * ID: 641<br>
	 * Message: 城守の承認に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_APPROVE_CASTLE_DEFENSE_AID;
	
	/**
	 * ID: 642<br>
	 * Message: すでに攻撃側に登録されているため申請できません。攻撃側の登録をキャンセルしてからもう一度やり直してください。
	 */
	public static final SystemMessageId ALREADY_ATTACKER_NOT_CANCEL;
	
	/**
	 * ID: 643<br>
	 * Message: すでに守備側に登録されているため申請できません。守備側の登録をキャンセルしてからもう一度やり直してください。
	 */
	public static final SystemMessageId ALREADY_DEFENDER_NOT_CANCEL;
	
	/**
	 * ID: 644<br>
	 * Message: まだ攻城戦に登録されていません。
	 */
	public static final SystemMessageId NOT_REGISTERED_FOR_SIEGE;
	
	/**
	 * ID: 645<br>
	 * Message: レベル5以上の血盟のみが攻城戦に登録できます。
	 */
	public static final SystemMessageId ONLY_CLAN_LEVEL_5_ABOVE_MAY_SIEGE;
	
	/**
	 * ID: 646<br>
	 * Message: 守備側のリストを変更する権限がありません。
	 */
	public static final SystemMessageId DO_NOT_HAVE_AUTHORITY_TO_MODIFY_CASTLE_DEFENDER_LIST;
	
	/**
	 * ID: 647<br>
	 * Message: 攻城時刻を設定する権限がありません。
	 */
	public static final SystemMessageId DO_NOT_HAVE_AUTHORITY_TO_MODIFY_SIEGE_TIME;
	
	/**
	 * ID: 648<br>
	 * Message: これ以上攻撃側に登録できません。
	 */
	public static final SystemMessageId ATTACKER_SIDE_FULL;
	
	/**
	 * ID: 649<br>
	 * Message: これ以上守備側に申請できません。
	 */
	public static final SystemMessageId DEFENDER_SIDE_FULL;
	
	/**
	 * ID: 650<br>
	 * Message: 現在地では召喚できません。
	 */
	public static final SystemMessageId YOU_MAY_NOT_SUMMON_FROM_YOUR_CURRENT_LOCATION;
	
	/**
	 * ID: 651<br>
	 * Message: $s1を現在立っている位置と方向に配置します。よろしいですか。
	 */
	public static final SystemMessageId PLACE_CURRENT_LOCATION_DIRECTION;
	
	/**
	 * ID: 652<br>
	 * Message: 召喚獣のターゲットが正しくありません。
	 */
	public static final SystemMessageId TARGET_OF_SUMMON_WRONG;
	
	/**
	 * ID: 653<br>
	 * Message: 傭兵を配置する権限がありません。
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_AUTHORITY_TO_POSITION_MERCENARIES;
	
	/**
	 * ID: 654<br>
	 * Message: 傭兵配置をキャンセルする権限がありません。
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_AUTHORITY_TO_CANCEL_MERCENARY_POSITIONING;
	
	/**
	 * ID: 655<br>
	 * Message: 傭兵が所属している城ではないため、配置できません。
	 */
	public static final SystemMessageId MERCENARIES_CANNOT_BE_POSITIONED_HERE;
	
	/**
	 * ID: 656<br>
	 * Message: この傭兵は配置できません。
	 */
	public static final SystemMessageId THIS_MERCENARY_CANNOT_BE_POSITIONED_ANYMORE;
	
	/**
	 * ID: 657<br>
	 * Message: 傭兵同士の間隔が狭過ぎるため、配置できません。
	 */
	public static final SystemMessageId POSITIONING_CANNOT_BE_DONE_BECAUSE_DISTANCE_BETWEEN_MERCENARIES_TOO_SHORT;
	
	/**
	 * ID: 658<br>
	 * Message: 所有している城の傭兵ではないため、配置をキャンセルできません。
	 */
	public static final SystemMessageId THIS_IS_NOT_A_MERCENARY_OF_A_CASTLE_THAT_YOU_OWN_AND_SO_CANNOT_CANCEL_POSITIONING;
	
	/**
	 * ID: 659<br>
	 * Message: 攻城の登録期間ではないため、承認も拒否もできません。
	 */
	public static final SystemMessageId NOT_SIEGE_REGISTRATION_TIME1;
	
	/**
	 * ID: 659<br>
	 * Message: 攻城の登録期間ではないため、承認も拒否もできません。
	 */
	public static final SystemMessageId NOT_SIEGE_REGISTRATION_TIME2;
	
	/**
	 * ID: 661<br>
	 * Message: スポイルできないキャラクターです。
	 */
	public static final SystemMessageId SPOIL_CANNOT_USE;
	
	/**
	 * ID: 662<br>
	 * Message: 相手が友達の招待を拒否している状態です。
	 */
	public static final SystemMessageId THE_PLAYER_IS_REJECTING_FRIEND_INVITATIONS;
	
	/**
	 * ID: 663<br>
	 * Message: 攻城時刻は$s1です。宣布後の変更はできません。よろしいですか。
	 */
	public static final SystemMessageId SIEGE_TIME_DECLARED_FOR_S1;
	
	/**
	 * ID: 664<br>
	 * Message: 受取人を選択してください。
	 */
	public static final SystemMessageId CHOOSE_PERSON_TO_RECEIVE;
	
	/**
	 * ID: 665<br>
	 * Message: $s1同盟の$s2に同盟戦を申し込まれました。挑戦を受け入れますか。
	 */
	public static final SystemMessageId APPLYING_ALLIANCE_WAR;
	
	/**
	 * ID: 666<br>
	 * Message: $s1 同盟に終戦を申し込まれました。同意しますか。
	 */
	public static final SystemMessageId REQUEST_FOR_CEASEFIRE;
	
	/**
	 * ID: 667<br>
	 * Message: $s1攻城戦に攻撃側として登録します。よろしいですか。
	 */
	public static final SystemMessageId REGISTERING_ON_ATTACKING_SIDE;
	
	/**
	 * ID: 668<br>
	 * Message: $s1攻城戦に守備側として登録します。よろしいですか。
	 */
	public static final SystemMessageId REGISTERING_ON_DEFENDING_SIDE;
	
	/**
	 * ID: 669<br>
	 * Message: $s1攻城戦への参戦申請をキャンセルします。よろしいですか。
	 */
	public static final SystemMessageId CANCELING_REGISTRATION;
	
	/**
	 * ID: 670<br>
	 * Message: $s1血盟の守備側登録を拒否します。よろしいですか。
	 */
	public static final SystemMessageId REFUSING_REGISTRATION;
	
	/**
	 * ID: 671<br>
	 * Message: $s1血盟の守備側登録を承認します。よろしいですか。
	 */
	public static final SystemMessageId AGREEING_REGISTRATION;
	
	/**
	 * ID: 672<br>
	 * Message: $s1アデナが消えました。
	 */
	public static final SystemMessageId S1_DISAPPEARED_ADENA;
	
	/**
	 * ID: 673<br>
	 * Message: アジトの競売には血盟レベル2以上の血盟主のみ参加できます。
	 */
	public static final SystemMessageId AUCTION_ONLY_CLAN_LEVEL_2_HIGHER;
	
	/**
	 * ID: 674<br>
	 * Message: 競売をキャンセルしてから7日が過ぎていません。
	 */
	public static final SystemMessageId NOT_SEVEN_DAYS_SINCE_CANCELING_AUCTION;
	
	/**
	 * ID: 675<br>
	 * Message: 競売中のアジトではありません。
	 */
	public static final SystemMessageId NO_CLAN_HALLS_UP_FOR_AUCTION;
	
	/**
	 * ID: 676<br>
	 * Message: 現在競売に入札中のため、他の競売には参加できません。
	 */
	public static final SystemMessageId ALREADY_SUBMITTED_BID;
	
	/**
	 * ID: 677<br>
	 * Message: 入札価格は最低入札可能価格より高くなければなりません。
	 */
	public static final SystemMessageId BID_PRICE_MUST_BE_HIGHER;
	
	/**
	 * ID: 678<br>
	 * Message: $s1の競売に入札しました。
	 */
	public static final SystemMessageId SUBMITTED_A_BID_OF_S1;
	
	/**
	 * ID: 679<br>
	 * Message: 入札をキャンセルしました。
	 */
	public static final SystemMessageId CANCELED_BID;
	
	/**
	 * ID: 680<br>
	 * Message: 競売に参加できません。
	 */
	public static final SystemMessageId CANNOT_PARTICIPATE_IN_AN_AUCTION;
	
	/**
	 * ID: 681<br>
	 * Message: 血盟がアジトを所有していません。
	 */
	public static final SystemMessageId CLAN_HAS_NO_CLAN_HALL;
	
	/**
	 * ID: 682<br>
	 * Message: 指定した場所に移動しますか。
	 */
	public static final SystemMessageId MOVING_TO_ANOTHER_VILLAGE;
	
	/**
	 * ID: 683<br>
	 * Message: スウィーパーに対する優先権がありません。
	 */
	public static final SystemMessageId SWEEP_NOT_ALLOWED;
	
	/**
	 * ID: 684<br>
	 * Message: 攻城中には傭兵を配置できません。
	 */
	public static final SystemMessageId CANNOT_POSITION_MERCS_DURING_SIEGE;
	
	/**
	 * ID: 685<br>
	 * Message: 同じ同盟に属する血盟に血盟戦を申し込むことはできません。
	 */
	public static final SystemMessageId CANNOT_DECLARE_WAR_ON_ALLY;
	
	/**
	 * ID: 686<br>
	 * Message: 魔法の火によって$s1のダメージを受けました。
	 */
	public static final SystemMessageId S1_DAMAGE_FROM_FIRE_MAGIC;
	
	/**
	 * ID: 687<br>
	 * Message: フリーズ状態では行動できません。しばらくお待ちください。
	 */
	public static final SystemMessageId CANNOT_MOVE_FROZEN;
	
	/**
	 * ID: 688<br>
	 * Message: 城を所有している血盟は、自動的に守備側に登録されます。
	 */
	public static final SystemMessageId CLAN_THAT_OWNS_CASTLE_IS_AUTOMATICALLY_REGISTERED_DEFENDING;
	
	/**
	 * ID: 689<br>
	 * Message: 城を所有している血盟は、他の攻城戦には参戦できません。
	 */
	public static final SystemMessageId CLAN_THAT_OWNS_CASTLE_CANNOT_PARTICIPATE_OTHER_SIEGE;
	
	/**
	 * ID: 690<br>
	 * Message: 城を所有している血盟と同じ同盟に属しているため、攻撃側に登録できません。
	 */
	public static final SystemMessageId CANNOT_ATTACK_ALLIANCE_CASTLE;
	
	/**
	 * ID: 691<br>
	 * Message: $s1血盟は、すでに$s2同盟に加盟しています。
	 */
	public static final SystemMessageId S1_CLAN_ALREADY_MEMBER_OF_S2_ALLIANCE;
	
	/**
	 * ID: 692<br>
	 * Message: 相手がフリーズ状態です。しばらくお待ちください。
	 */
	public static final SystemMessageId OTHER_PARTY_IS_FROZEN;
	
	/**
	 * ID: 693<br>
	 * Message: 到着した小包が他の倉庫にあります。
	 */
	public static final SystemMessageId PACKAGE_IN_ANOTHER_WAREHOUSE;
	
	/**
	 * ID: 694<br>
	 * Message: 到着した小包はありません。
	 */
	public static final SystemMessageId NO_PACKAGES_ARRIVED;
	
	/**
	 * ID: 695<br>
	 * Message: ペットの名前を設定できません。
	 */
	public static final SystemMessageId NAMING_YOU_CANNOT_SET_NAME_OF_THE_PET;
	
	/**
	 * ID: 697<br>
	 * Message: アイテムエンチャント値が異常です。
	 */
	public static final SystemMessageId ITEM_ENCHANT_VALUE_STRANGE;
	
	/**
	 * ID: 698<br>
	 * Message: 販売リストにある同じアイテムと価格が異なります。
	 */
	public static final SystemMessageId PRICE_DIFFERENT_FROM_SALES_LIST;
	
	/**
	 * ID: 699<br>
	 * Message: 現在購入しません。
	 */
	public static final SystemMessageId CURRENTLY_NOT_PURCHASING;
	
	/**
	 * ID: 700<br>
	 * Message: 購入が完了しました。
	 */
	public static final SystemMessageId THE_PURCHASE_IS_COMPLETE;
	
	/**
	 * ID: 701<br>
	 * Message: 必要アイテムが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_REQUIRED_ITEMS;
	
	/**
	 * ID: 702<br>
	 * Message: 現在対応中のFS・SPT・サービスチームがいません。
	 */
	public static final SystemMessageId NO_GM_PROVIDING_SERVICE_NOW;
	
	/**
	 * ID: 703<br>
	 * Message: ======<FSリスト>======
	 */
	public static final SystemMessageId GM_LIST;
	
	/**
	 * ID: 704<br>
	 * Message: support：$c1
	 */
	public static final SystemMessageId GM_C1;
	
	/**
	 * ID: 705<br>
	 * Message: 自分自身を遮断することはできません。
	 */
	public static final SystemMessageId CANNOT_EXCLUDE_SELF;
	
	/**
	 * ID: 706<br>
	 * Message: 遮断リストには最大128人まで登録できます。
	 */
	public static final SystemMessageId ONLY_64_NAMES_ON_EXCLUDE_LIST;
	
	/**
	 * ID: 707<br>
	 * Message: 攻城戦中の村へはテレポートできません。
	 */
	public static final SystemMessageId NO_PORT_THAT_IS_IN_SIGE;
	
	/**
	 * ID: 708<br>
	 * Message: 城の倉庫を利用する権限がありません。
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CASTLE_WAREHOUSE;
	
	/**
	 * ID: 709<br>
	 * Message: 血盟倉庫を利用する権限がありません。
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_USE_CLAN_WAREHOUSE;
	
	/**
	 * ID: 710<br>
	 * Message: 血盟倉庫を利用できるのは、血盟レベルが1以上の血盟のみです。
	 */
	public static final SystemMessageId ONLY_LEVEL_1_CLAN_OR_HIGHER_CAN_USE_WAREHOUSE;
	
	/**
	 * ID: 711<br>
	 * Message: $s1の攻城戦が始まりました。
	 */
	public static final SystemMessageId SIEGE_OF_S1_HAS_STARTED;
	
	/**
	 * ID: 712<br>
	 * Message: $s1の攻城戦が終了しました。
	 */
	public static final SystemMessageId SIEGE_OF_S1_HAS_ENDED;
	
	/**
	 * ID: 713<br>
	 * Message: $s1/$s2/$s3 $s4:$s5
	 */
	public static final SystemMessageId S1_S2_S3_D;
	
	/**
	 * ID: 714<br>
	 * Message: トラップ装置が作動しました。
	 */
	public static final SystemMessageId A_TRAP_DEVICE_HAS_BEEN_TRIPPED;
	
	/**
	 * ID: 715<br>
	 * Message: トラップ装置の作動が中断されました。
	 */
	public static final SystemMessageId A_TRAP_DEVICE_HAS_BEEN_STOPPED;
	
	/**
	 * ID: 716<br>
	 * Message: 陣地がないと復活できません。
	 */
	public static final SystemMessageId NO_RESURRECTION_WITHOUT_BASE_CAMP;
	
	/**
	 * ID: 717<br>
	 * Message: ガーディアンタワーが破壊され、復活が不可能です。
	 */
	public static final SystemMessageId TOWER_DESTROYED_NO_RESURRECTION;
	
	/**
	 * ID: 718<br>
	 * Message: 攻城戦中は城門を開閉できません。
	 */
	public static final SystemMessageId GATES_NOT_OPENED_CLOSED_DURING_SIEGE;
	
	/**
	 * ID: 719<br>
	 * Message: アイテムの調合に失敗しました。
	 */
	public static final SystemMessageId ITEM_MIXING_FAILED;
	
	/**
	 * ID: 720<br>
	 * Message: 購入額が所持金より大きいため、個人商店を開くことができません。
	 */
	public static final SystemMessageId THE_PURCHASE_PRICE_IS_HIGHER_THAN_MONEY;
	
	/**
	 * ID: 721<br>
	 * Message: 攻城戦に参戦している状態では、同盟を作ることができません。
	 */
	public static final SystemMessageId NO_ALLY_CREATION_WHILE_SIEGE;
	
	/**
	 * ID: 722<br>
	 * Message: 所属の血盟が攻城戦に参戦している状態のため、同盟を解散できません。
	 */
	public static final SystemMessageId CANNOT_DISSOLVE_ALLY_WHILE_IN_SIEGE;
	
	/**
	 * ID: 723<br>
	 * Message: 相手の血盟は、攻城戦に参戦中です。
	 */
	public static final SystemMessageId OPPOSING_CLAN_IS_PARTICIPATING_IN_SIEGE;
	
	/**
	 * ID: 724<br>
	 * Message: 攻城戦に参戦している状態では、脱退できません。
	 */
	public static final SystemMessageId CANNOT_LEAVE_WHILE_SIEGE;
	
	/**
	 * ID: 725<br>
	 * Message: 攻城戦に参戦中の血盟を同盟から追放できません。
	 */
	public static final SystemMessageId CANNOT_DISMISS_WHILE_SIEGE;
	
	/**
	 * ID: 726<br>
	 * Message: フリーズ状態が始まりました。しばらくお待ちください。
	 */
	public static final SystemMessageId FROZEN_CONDITION_STARTED;
	
	/**
	 * ID: 727<br>
	 * Message: フリーズ状態が解除されました。
	 */
	public static final SystemMessageId FROZEN_CONDITION_REMOVED;
	
	/**
	 * ID: 728<br>
	 * Message: 解散の申請後7日以内は、再び解散を申し込むことができません。
	 */
	public static final SystemMessageId CANNOT_APPLY_DISSOLUTION_AGAIN;
	
	/**
	 * ID: 729<br>
	 * Message: 該当アイテムを捨てることはできません。
	 */
	public static final SystemMessageId ITEM_NOT_DISCARDED;
	
	/**
	 * ID: 730<br>
	 * Message: - $c1番目のサポートが受け付けされました。\n - 今日のサポート回数はあと$s2回です。\n - ワールドの状況によっては返信にお時間がかかってしまう場合がございます。予めご了承下さいませ。
	 */
	public static final SystemMessageId SUBMITTED_YOU_S1_TH_PETITION_S2_LEFT;
	
	/**
	 * ID: 731<br>
	 * Message: $c1 にサポートからの対応要請をしました。受付番号は$s2です。
	 */
	public static final SystemMessageId PETITION_S1_RECEIVED_CODE_IS_S2;
	
	/**
	 * ID: 732<br>
	 * Message: $c1 にサポートからのご連絡があります。
	 */
	public static final SystemMessageId C1_RECEIVED_CONSULTATION_REQUEST;
	
	/**
	 * ID: 733<br>
	 * Message: 一日に申請可能な$s1回のサポートをすべて受け付けました。これ以上サポートを申し込むことはできません。
	 */
	public static final SystemMessageId WE_HAVE_RECEIVED_S1_PETITIONS_TODAY;
	
	/**
	 * ID: 734<br>
	 * Message: サポートの代理受付に失敗しました。$c1 がすでにサポートを受け付けた状態です。
	 */
	public static final SystemMessageId PETITION_FAILED_C1_ALREADY_SUBMITTED;
	
	/**
	 * ID: 735<br>
	 * Message: $c1 に対するサポートの代理受付に失敗しました。エラー番号は$s2です。
	 */
	public static final SystemMessageId PETITION_FAILED_FOR_C1_ERROR_NUMBER_S2;
	
	/**
	 * ID: 736<br>
	 * Message: サポート要請がキャンセルされました。今日受け付け可能なサポートはあと$s1回です。
	 */
	public static final SystemMessageId PETITION_CANCELED_SUBMIT_S1_MORE_TODAY;
	
	/**
	 * ID: 737<br>
	 * Message: $c1 に対するサポートの代理受付をキャンセルしました。
	 */
	public static final SystemMessageId CANCELED_PETITION_ON_S1;
	
	/**
	 * ID: 738<br>
	 * Message: 現在はサポート要請を受け付けておりません。
	 */
	public static final SystemMessageId PETITION_NOT_SUBMITTED;
	
	/**
	 * ID: 739<br>
	 * Message: $c1 に対するサポートの代理受付のキャンセルに失敗しました。エラーコードは$s2です。
	 */
	public static final SystemMessageId PETITION_CANCEL_FAILED_FOR_C1_ERROR_NUMBER_S2;
	
	/**
	 * ID: 740<br>
	 * Message: $c1 がサポートの要請で対話に参加しました。
	 */
	public static final SystemMessageId C1_PARTICIPATE_PETITION;
	
	/**
	 * ID: 741<br>
	 * Message: $c1 を対話に追加するのに失敗しました。すでにサポートを受け付けた状態です。
	 */
	public static final SystemMessageId FAILED_ADDING_C1_TO_PETITION;
	
	/**
	 * ID: 742<br>
	 * Message: $c1 を対話に追加するのに失敗しました。エラーコードは$s2です。
	 */
	public static final SystemMessageId PETITION_ADDING_C1_FAILED_ERROR_NUMBER_S2;
	
	/**
	 * ID: 743<br>
	 * Message: $c1 が対話から退場しました。
	 */
	public static final SystemMessageId C1_LEFT_PETITION_CHAT;
	
	/**
	 * ID: 744<br>
	 * Message: $s1 を対話から削除するのに失敗しました。エラーコードは$s2です。
	 */
	public static final SystemMessageId PETITION_REMOVING_S1_FAILED_ERROR_NUMBER_S2;
	
	/**
	 * ID: 745<br>
	 * Message: 現在サポートの対話状態ではありません。
	 */
	public static final SystemMessageId YOU_ARE_NOT_IN_PETITION_CHAT;
	
	/**
	 * ID: 746<br>
	 * Message: 現在はサポート要請の状態ではありません。
	 */
	public static final SystemMessageId CURRENTLY_NO_PETITION;
	
	/**
	 * ID: 748<br>
	 * Message: 離れているため、詠唱が中断されました。
	 */
	public static final SystemMessageId DIST_TOO_FAR_CASTING_STOPPED;
	
	/**
	 * ID: 749<br>
	 * Message: $s1の効果が解除されました。
	 */
	public static final SystemMessageId EFFECT_S1_DISAPPEARED;
	
	/**
	 * ID: 750<br>
	 * Message: これ以上習うスキルがありません。
	 */
	public static final SystemMessageId NO_MORE_SKILLS_TO_LEARN;
	
	/**
	 * ID: 751<br>
	 * Message: 同盟に属する血盟と攻城戦の関係が衝突するため、同盟に招待できません。
	 */
	public static final SystemMessageId CANNOT_INVITE_CONFLICT_CLAN;
	
	/**
	 * ID: 752<br>
	 * Message: 使用できない名前です。
	 */
	public static final SystemMessageId CANNOT_USE_NAME;
	
	/**
	 * ID: 753<br>
	 * Message: ここには傭兵を配置できません。
	 */
	public static final SystemMessageId NO_MERCS_HERE;
	
	/**
	 * ID: 754<br>
	 * Message: 今週の使用時間は、あと$s1時間$s2分です。
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_THIS_WEEK;
	
	/**
	 * ID: 755<br>
	 * Message: 今週の使用時間は、あと$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_THIS_WEEK;
	
	/**
	 * ID: 756<br>
	 * Message: 今週の使用時間が終了しました。
	 */
	public static final SystemMessageId WEEKS_USAGE_TIME_FINISHED;
	
	/**
	 * ID: 757<br>
	 * Message: 定量使用時間は、あと$s1時間$s2分です。
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_IN_TIME;
	
	/**
	 * ID: 758<br>
	 * Message: 今週のプレイ時間は、あと$s1時間$s2分です。
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME;
	
	/**
	 * ID: 759<br>
	 * Message: 今週のプレイ時間は、あと$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_THIS_WEEKS_PLAY_TIME;
	
	/**
	 * ID: 760<br>
	 * Message: $c1 は血盟脱退後1日間経過しておらず、血盟に加入できない状態です。
	 */
	public static final SystemMessageId C1_MUST_WAIT_BEFORE_JOINING_ANOTHER_CLAN;
	
	/**
	 * ID: 761<br>
	 * Message: $s1は同盟脱退後1日が経過しておらず、同盟に加入できない状態です。
	 */
	public static final SystemMessageId S1_CANT_ENTER_ALLIANCE_WITHIN_1_DAY;
	
	/**
	 * ID: 762<br>
	 * Message: $c1が$s2を転がして$s3が出ました。
	 */
	public static final SystemMessageId C1_ROLLED_S2_S3_EYE_CAME_OUT;
	
	/**
	 * ID: 763<br>
	 * Message: 倉庫との距離が遠いため小包を届けられませんでした。
	 */
	public static final SystemMessageId FAILED_SENDING_PACKAGE_TOO_FAR;
	
	/**
	 * ID: 764<br>
	 * Message: $s1時間プレイしました。健康のためにしばらく休憩してください。
	 */
	public static final SystemMessageId PLAYING_FOR_LONG_TIME;
	
	/**
	 * ID: 769<br>
	 * Message: 不正ユーティリティの利用が発見されました。不正ユーティリティの利用を終了して、再度実行してください。
	 */
	public static final SystemMessageId HACKING_TOOL;
	
	/**
	 * ID: 774<br>
	 * Message: プレイ時間はこれ以上減りません。
	 */
	public static final SystemMessageId PLAY_TIME_NO_LONGER_ACCUMULATING;
	
	/**
	 * ID: 775<br>
	 * Message: これからプレイ時間が減ります。
	 */
	public static final SystemMessageId PLAY_TIME_EXPENDED;
	
	/**
	 * ID: 776<br>
	 * Message: 競売に入札したアジトが$s1血盟に落札されました。
	 */
	public static final SystemMessageId CLANHALL_AWARDED_TO_CLAN;
	
	/**
	 * ID: 777<br>
	 * Message: 競売に入札したアジトが流札となりました。
	 */
	public static final SystemMessageId CLANHALL_NOT_SOLD;
	
	/**
	 * ID: 778<br>
	 * Message: ここではログアウトできません。
	 */
	public static final SystemMessageId NO_LOGOUT_HERE;
	
	/**
	 * ID: 779<br>
	 * Message: ここではリスタートできません。
	 */
	public static final SystemMessageId NO_RESTART_HERE;
	
	/**
	 * ID: 780<br>
	 * Message: 攻城戦の間だけ観戦できます。
	 */
	public static final SystemMessageId ONLY_VIEW_SIEGE;
	
	/**
	 * ID: 781<br>
	 * Message: 観戦中にはできない行動です。
	 */
	public static final SystemMessageId OBSERVERS_CANNOT_PARTICIPATE;
	
	/**
	 * ID: 782<br>
	 * Message: ペット、または召喚獣が召喚されている状態では観戦できません。
	 */
	public static final SystemMessageId NO_OBSERVE_WITH_PET;
	
	/**
	 * ID: 783<br>
	 * Message: 現在、宝くじの販売が一時中断されております。
	 */
	public static final SystemMessageId LOTTERY_TICKET_SALES_TEMP_SUSPENDED;
	
	/**
	 * ID: 784<br>
	 * Message: 宝くじの販売が締め切られました。
	 */
	public static final SystemMessageId NO_LOTTERY_TICKETS_AVAILABLE;
	
	/**
	 * ID: 785<br>
	 * Message: まだ$s1回目の当選結果が出ていません。
	 */
	public static final SystemMessageId LOTTERY_S1_RESULT_NOT_PUBLISHED;
	
	/**
	 * ID: 786<br>
	 * Message: 使えない単語が含まれています。
	 */
	public static final SystemMessageId INCORRECT_SYNTAX;
	
	/**
	 * ID: 787<br>
	 * Message: 予選が終了しました。
	 */
	public static final SystemMessageId CLANHALL_SIEGE_TRYOUTS_FINISHED;
	
	/**
	 * ID: 788<br>
	 * Message: 決勝戦が終了しました。
	 */
	public static final SystemMessageId CLANHALL_SIEGE_FINALS_FINISHED;
	
	/**
	 * ID: 789<br>
	 * Message: 予選が始まりました。
	 */
	public static final SystemMessageId CLANHALL_SIEGE_TRYOUTS_BEGUN;
	
	/**
	 * ID: 790<br>
	 * Message: 決勝戦が始まりました。
	 */
	public static final SystemMessageId CLANHALL_SIEGE_FINALS_BEGUN;
	
	/**
	 * ID: 791<br>
	 * Message: 決勝戦の待機状態です。準備してください。
	 */
	public static final SystemMessageId FINAL_MATCH_BEGIN;
	
	/**
	 * ID: 792<br>
	 * Message: アジト戦が終了しました。
	 */
	public static final SystemMessageId CLANHALL_SIEGE_ENDED;
	
	/**
	 * ID: 793<br>
	 * Message: アジト戦が始まりました。
	 */
	public static final SystemMessageId CLANHALL_SIEGE_BEGUN;
	
	/**
	 * ID: 794<br>
	 * Message: 該当する権限がありません。
	 */
	public static final SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_DO_THAT;
	
	/**
	 * ID: 795<br>
	 * Message: 血盟主のみが権限を設定できます。
	 */
	public static final SystemMessageId ONLY_LEADERS_CAN_SET_RIGHTS;
	
	/**
	 * ID: 796<br>
	 * Message: 観戦可能時間は残り$s1分です。
	 */
	public static final SystemMessageId REMAINING_OBSERVATION_TIME;
	
	/**
	 * ID: 797<br>
	 * Message: マクロは48個まで登録可能です。
	 */
	public static final SystemMessageId YOU_MAY_CREATE_UP_TO_48_MACROS;
	
	/**
	 * ID: 798<br>
	 * Message: 一度登録すると、再び製作図アイテムに戻すことはできません。登録してもよろしいですか。
	 */
	public static final SystemMessageId ITEM_REGISTRATION_IRREVERSIBLE;
	
	/**
	 * ID: 799<br>
	 * Message: 観戦可能時間が終了しました。
	 */
	public static final SystemMessageId OBSERVATION_TIME_EXPIRED;
	
	/**
	 * ID: 800<br>
	 * Message: アジト戦の登録期間が過ぎたので、登録できません。
	 */
	public static final SystemMessageId REGISTRATION_PERIOD_OVER;
	
	/**
	 * ID: 801<br>
	 * Message: これ以上のアジト戦の登録はできません。
	 */
	public static final SystemMessageId REGISTRATION_CLOSED;
	
	/**
	 * ID: 802<br>
	 * Message: これ以上監視ウィンドウを開くことができません。ウィンドウを閉じて、再度実行してください。
	 */
	public static final SystemMessageId PETITION_NOT_ACCEPTED_NOW;
	
	/**
	 * ID: 803<br>
	 * Message: サポート要請の内容を書いてください。
	 */
	public static final SystemMessageId PETITION_NOT_SPECIFIED;
	
	/**
	 * ID: 804<br>
	 * Message: 分類を選択して、FAQをご確認ください。
	 */
	public static final SystemMessageId SELECT_TYPE;
	
	/**
	 * ID: 805<br>
	 * Message: 現在はサポートの受け付けを行っていません。$s1時以後に再度要請してください。
	 */
	public static final SystemMessageId PETITION_NOT_ACCEPTED_SUBMIT_AT_S1;
	
	/**
	 * ID: 806<br>
	 * Message: 地形にはまって出られない時は「/escape」コマンドを試してみてください。
	 */
	public static final SystemMessageId TRY_UNSTUCK_WHEN_TRAPPED;
	
	/**
	 * ID: 807<br>
	 * Message: 移動が不可能な地形でしたので、村にテレポートします。
	 */
	public static final SystemMessageId STUCK_PREPARE_FOR_TRANSPORT;
	
	/**
	 * ID: 808<br>
	 * Message: 移動が不可能な地形であることを確認できませんでした。「/support」コマンドで、サポート要請を行なってください。
	 */
	public static final SystemMessageId STUCK_SUBMIT_PETITION;
	
	/**
	 * ID: 809<br>
	 * Message: 移動が不可能な地形であることを確認できませんでした。5分後に村にテレポートします。
	 */
	public static final SystemMessageId STUCK_TRANSPORT_IN_FIVE_MINUTES;
	
	/**
	 * ID: 810<br>
	 * Message: 有効なマクロではありません。マクロに関連するヘルプをご確認ください。
	 */
	public static final SystemMessageId INVALID_MACRO;
	
	/**
	 * ID: 811<br>
	 * Message: 目的地（$s1）に移動しますか。
	 */
	public static final SystemMessageId WILL_BE_MOVED;
	
	/**
	 * ID: 812<br>
	 * Message: 罠によって$s1のダメージを受けました。
	 */
	public static final SystemMessageId TRAP_DID_S1_DAMAGE;
	
	/**
	 * ID: 813<br>
	 * Message: 罠によって毒に掛かりました。
	 */
	public static final SystemMessageId POISONED_BY_TRAP;
	
	/**
	 * ID: 814<br>
	 * Message: 罠によって移動速度が低下しました。
	 */
	public static final SystemMessageId SLOWED_BY_TRAP;
	
	/**
	 * ID: 815<br>
	 * Message: 予選待機状態です。準備してください。
	 */
	public static final SystemMessageId TRYOUTS_ABOUT_TO_BEGIN;
	
	/**
	 * ID: 816<br>
	 * Message: 第$s1回モンスター レース チケットの販売が開始されました！
	 */
	public static final SystemMessageId MONSRACE_TICKETS_AVAILABLE_FOR_S1_RACE;
	
	/**
	 * ID: 817<br>
	 * Message: 第$s1回モンスター レース チケットを現在販売しております。
	 */
	public static final SystemMessageId MONSRACE_TICKETS_NOW_AVAILABLE_FOR_S1_RACE;
	
	/**
	 * ID: 818<br>
	 * Message: $s1分後にモンスター レース チケットの販売が終了いたします。お早めにご購入ください。
	 */
	public static final SystemMessageId MONSRACE_TICKETS_STOP_IN_S1_MINUTES;
	
	/**
	 * ID: 819<br>
	 * Message: 第$s1回モンスター レース チケットの販売が終了いたしました。配当率をご確認ください。
	 */
	public static final SystemMessageId MONSRACE_S1_TICKET_SALES_CLOSED;
	
	/**
	 * ID: 820<br>
	 * Message: $s1分後に第$s2回モンスター レースが開始されます。
	 */
	public static final SystemMessageId MONSRACE_S2_BEGINS_IN_S1_MINUTES;
	
	/**
	 * ID: 821<br>
	 * Message: 30秒後に第$s1回モンスター レースが開始されます。
	 */
	public static final SystemMessageId MONSRACE_S1_BEGINS_IN_30_SECONDS;
	
	/**
	 * ID: 822<br>
	 * Message: 第$s1回モンスター レースが開始されます。
	 */
	public static final SystemMessageId MONSRACE_S1_COUNTDOWN_IN_FIVE_SECONDS;
	
	/**
	 * ID: 823<br>
	 * Message: $s1秒前！
	 */
	public static final SystemMessageId MONSRACE_BEGINS_IN_S1_SECONDS;
	
	/**
	 * ID: 824<br>
	 * Message: レース スタート！
	 */
	public static final SystemMessageId MONSRACE_RACE_START;
	
	/**
	 * ID: 825<br>
	 * Message: 第$s1回モンスター レースが終了いたしました。
	 */
	public static final SystemMessageId MONSRACE_S1_RACE_END;
	
	/**
	 * ID: 826<br>
	 * Message: 1着$s1番、2着$s2番です！
	 */
	public static final SystemMessageId MONSRACE_FIRST_PLACE_S1_SECOND_S2;
	
	/**
	 * ID: 827<br>
	 * Message: FS・SPT・サービスチームを遮断することはできません。
	 */
	public static final SystemMessageId YOU_MAY_NOT_IMPOSE_A_BLOCK_ON_GM;
	
	/**
	 * ID: 828<br>
	 * Message: $s1マクロを削除しますか。
	 */
	public static final SystemMessageId WISH_TO_DELETE_S1_MACRO;
	
	/**
	 * ID: 829<br>
	 * Message: 自分自身を推薦することはできません。
	 */
	public static final SystemMessageId YOU_CANNOT_RECOMMEND_YOURSELF;
	
	/**
	 * ID: 830<br>
	 * Message: $c1を推薦しました。推薦権の残りは$s2です。
	 */
	public static final SystemMessageId YOU_HAVE_RECOMMENDED_C1_YOU_HAVE_S2_RECOMMENDATIONS_LEFT;
	
	/**
	 * ID: 831<br>
	 * Message: $c1から推薦されました。
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_RECOMMENDED_BY_C1;
	
	/**
	 * ID: 832<br>
	 * Message: すでに推薦をしています。
	 */
	public static final SystemMessageId THAT_CHARACTER_IS_RECOMMENDED;
	
	/**
	 * ID: 833<br>
	 * Message: 推薦権をすべて使用しました。推薦数は毎日午後1時に初期化されます。
	 */
	public static final SystemMessageId NO_MORE_RECOMMENDATIONS_TO_HAVE;
	
	/**
	 * ID: 834<br>
	 * Message: $c1が転がしたサイコロの目は$s2です。
	 */
	public static final SystemMessageId C1_ROLLED_S2;
	
	/**
	 * ID: 835<br>
	 * Message: 連続してサイコロを振ることはできません。時間を置いてから使用してください。
	 */
	public static final SystemMessageId YOU_MAY_NOT_THROW_THE_DICE_AT_THIS_TIME_TRY_AGAIN_LATER;
	
	/**
	 * ID: 836<br>
	 * Message: インベントリのスロット数制限の超過により、アイテムが受け取れません。
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_ITEM;
	
	/**
	 * ID: 837<br>
	 * Message: マクロの説明は32文字までです。
	 */
	public static final SystemMessageId MACRO_DESCRIPTION_MAX_32_CHARS;
	
	/**
	 * ID: 838<br>
	 * Message: マクロ名を入力してください。
	 */
	public static final SystemMessageId ENTER_THE_MACRO_NAME;
	
	/**
	 * ID: 839<br>
	 * Message: 同じ名前のマクロがあるので登録できません。
	 */
	public static final SystemMessageId MACRO_NAME_ALREADY_USED;
	
	/**
	 * ID: 840<br>
	 * Message: すでに登録されている製作図です。
	 */
	public static final SystemMessageId RECIPE_ALREADY_REGISTERED;
	
	/**
	 * ID: 841<br>
	 * Message: これ以上製作図の登録はできません。
	 */
	public static final SystemMessageId NO_FUTHER_RECIPES_CAN_BE_ADDED;
	
	/**
	 * ID: 842<br>
	 * Message: クリエイト アイテムのスキル レベルが低いため、製作図を登録できません。
	 */
	public static final SystemMessageId NOT_AUTHORIZED_REGISTER_RECIPE;
	
	/**
	 * ID: 843<br>
	 * Message: $s1の占領戦が終了しました。
	 */
	public static final SystemMessageId SIEGE_OF_S1_FINISHED;
	
	/**
	 * ID: 844<br>
	 * Message: $s1の占領戦が開始されました。
	 */
	public static final SystemMessageId SIEGE_OF_S1_BEGUN;
	
	/**
	 * ID: 845<br>
	 * Message: $s1の占領戦の登録時間が終了しました。
	 */
	public static final SystemMessageId DEADLINE_FOR_SIEGE_S1_PASSED;
	
	/**
	 * ID: 846<br>
	 * Message: 参加する血盟がないため、$s1の占領戦がキャンセルされました。
	 */
	public static final SystemMessageId SIEGE_OF_S1_HAS_BEEN_CANCELED_DUE_TO_LACK_OF_INTEREST;
	
	/**
	 * ID: 847<br>
	 * Message: すでにアジトを所有している血盟は占領戦に参加できません。
	 */
	public static final SystemMessageId CLAN_OWNING_CLANHALL_MAY_NOT_SIEGE_CLANHALL;
	
	/**
	 * ID: 848<br>
	 * Message: $s1が削除されました。
	 */
	public static final SystemMessageId S1_HAS_BEEN_DELETED;
	
	/**
	 * ID: 849<br>
	 * Message: $s1を探すことができません。
	 */
	public static final SystemMessageId S1_NOT_FOUND;
	
	/**
	 * ID: 850<br>
	 * Message: $s1がすでに存在します。
	 */
	public static final SystemMessageId S1_ALREADY_EXISTS2;
	
	/**
	 * ID: 851<br>
	 * Message: $s1が追加されました。
	 */
	public static final SystemMessageId S1_ADDED;
	
	/**
	 * ID: 852<br>
	 * Message: 製作図の情報が正しくありません。
	 */
	public static final SystemMessageId RECIPE_INCORRECT;
	
	/**
	 * ID: 853<br>
	 * Message: 個人製作状態では、製作図集管理はできません。
	 */
	public static final SystemMessageId CANT_ALTER_RECIPEBOOK_WHILE_CRAFTING;
	
	/**
	 * ID: 854<br>
	 * Message: $s1が$s2足りません。
	 */
	public static final SystemMessageId MISSING_S2_S1_TO_CREATE;
	
	/**
	 * ID: 855<br>
	 * Message: $s1血盟が$s2との占領戦で勝利しました。
	 */
	public static final SystemMessageId S1_CLAN_DEFEATED_S2;
	
	/**
	 * ID: 856<br>
	 * Message: $s1の占領戦が引き分けで終了しました。
	 */
	public static final SystemMessageId SIEGE_S1_DRAW;
	
	/**
	 * ID: 857<br>
	 * Message: $s1血盟が$s2との予選で勝利しました。
	 */
	public static final SystemMessageId S1_CLAN_WON_MATCH_S2;
	
	/**
	 * ID: 858<br>
	 * Message: $s1の予選は引き分けで終了しました。
	 */
	public static final SystemMessageId MATCH_OF_S1_DRAW;
	
	/**
	 * ID: 859<br>
	 * Message: 製作図を登録してください。
	 */
	public static final SystemMessageId PLEASE_REGISTER_RECIPE;
	
	/**
	 * ID: 860<br>
	 * Message: 他の陣地との間隔が狭いため、ここに陣地を立てることができません。
	 */
	public static final SystemMessageId HEADQUARTERS_TOO_CLOSE;
	
	/**
	 * ID: 861<br>
	 * Message: メモの数が超過しました。
	 */
	public static final SystemMessageId TOO_MANY_MEMOS;
	
	/**
	 * ID: 862<br>
	 * Message: チケットの販売が完了していないので、配当を見ることができません。
	 */
	public static final SystemMessageId ODDS_NOT_POSTED;
	
	/**
	 * ID: 863<br>
	 * Message: 火の気が感じられます。
	 */
	public static final SystemMessageId FEEL_ENERGY_FIRE;
	
	/**
	 * ID: 864<br>
	 * Message: 水の気が感じられます。
	 */
	public static final SystemMessageId FEEL_ENERGY_WATER;
	
	/**
	 * ID: 865<br>
	 * Message: 風の気が感じられます。
	 */
	public static final SystemMessageId FEEL_ENERGY_WIND;
	
	/**
	 * ID: 866<br>
	 * Message: これ以上、気を集めることができません。
	 */
	public static final SystemMessageId NO_LONGER_ENERGY;
	
	/**
	 * ID: 867<br>
	 * Message: 気がなくなりました。
	 */
	public static final SystemMessageId ENERGY_DEPLETED;
	
	/**
	 * ID: 868<br>
	 * Message: 火の気を伝えました。
	 */
	public static final SystemMessageId ENERGY_FIRE_DELIVERED;
	
	/**
	 * ID: 869<br>
	 * Message: 水の気を伝えました。
	 */
	public static final SystemMessageId ENERGY_WATER_DELIVERED;
	
	/**
	 * ID: 870<br>
	 * Message: 風の気を伝えました。
	 */
	public static final SystemMessageId ENERGY_WIND_DELIVERED;
	
	/**
	 * ID: 871<br>
	 * Message: すでに種を植付けられた状態です。
	 */
	public static final SystemMessageId THE_SEED_HAS_BEEN_SOWN;
	
	/**
	 * ID: 872<br>
	 * Message: この荘園では使えない種です。
	 */
	public static final SystemMessageId THIS_SEED_MAY_NOT_BE_SOWN_HERE;
	
	/**
	 * ID: 873<br>
	 * Message: 貨物を受け取るキャラクターがいません。
	 */
	public static final SystemMessageId CHARACTER_DOES_NOT_EXIST;
	
	/**
	 * ID: 874<br>
	 * Message: 対象となる倉庫がいっぱいです。
	 */
	public static final SystemMessageId WAREHOUSE_CAPACITY_EXCEEDED;
	
	/**
	 * ID: 875<br>
	 * Message: 配送がキャンセルされました。
	 */
	public static final SystemMessageId CARGO_CANCELED;
	
	/**
	 * ID: 876<br>
	 * Message: 配送に失敗しました。
	 */
	public static final SystemMessageId CARGO_NOT_DELIVERED;
	
	/**
	 * ID: 877<br>
	 * Message: 紋様が追加されました。
	 */
	public static final SystemMessageId SYMBOL_ADDED;
	
	/**
	 * ID: 878<br>
	 * Message: 紋様が削除されました。
	 */
	public static final SystemMessageId SYMBOL_DELETED;
	
	/**
	 * ID: 879<br>
	 * Message: 荘園システムは現在点検中です。
	 */
	public static final SystemMessageId THE_MANOR_SYSTEM_IS_CURRENTLY_UNDER_MAINTENANCE;
	
	/**
	 * ID: 880<br>
	 * Message: 購入が終わりました。
	 */
	public static final SystemMessageId THE_TRANSACTION_IS_COMPLETE;
	
	/**
	 * ID: 881<br>
	 * Message: 購入アイテムの情報が正しくありません。
	 */
	public static final SystemMessageId THERE_IS_A_DISCREPANCY_ON_THE_INVOICE;
	
	/**
	 * ID: 882<br>
	 * Message: 種の数が正しくありません。
	 */
	public static final SystemMessageId THE_SEED_QUANTITY_IS_INCORRECT;
	
	/**
	 * ID: 883<br>
	 * Message: 種の情報が正しくありません。
	 */
	public static final SystemMessageId THE_SEED_INFORMATION_IS_INCORRECT;
	
	/**
	 * ID: 884<br>
	 * Message: 荘園の情報が新たに設定されました。
	 */
	public static final SystemMessageId THE_MANOR_INFORMATION_HAS_BEEN_UPDATED;
	
	/**
	 * ID: 885<br>
	 * Message: 作物の数が正しくありません。
	 */
	public static final SystemMessageId THE_NUMBER_OF_CROPS_IS_INCORRECT;
	
	/**
	 * ID: 886<br>
	 * Message: 作物の価格情報が正しくありません。
	 */
	public static final SystemMessageId THE_CROPS_ARE_PRICED_INCORRECTLY;
	
	/**
	 * ID: 887<br>
	 * Message: タイプが正しくありません。
	 */
	public static final SystemMessageId THE_TYPE_IS_INCORRECT;
	
	/**
	 * ID: 888<br>
	 * Message: 購入する作物がありません。
	 */
	public static final SystemMessageId NO_CROPS_CAN_BE_PURCHASED_AT_THIS_TIME;
	
	/**
	 * ID: 889<br>
	 * Message: 種の植付けに成功しました。
	 */
	public static final SystemMessageId THE_SEED_WAS_SUCCESSFULLY_SOWN;
	
	/**
	 * ID: 890<br>
	 * Message: 種の植付けに失敗しました。
	 */
	public static final SystemMessageId THE_SEED_WAS_NOT_SOWN;
	
	/**
	 * ID: 891<br>
	 * Message: 収穫に対する優先権がありません。
	 */
	public static final SystemMessageId YOU_ARE_NOT_AUTHORIZED_TO_HARVEST;
	
	/**
	 * ID: 892<br>
	 * Message: 収穫に失敗しました。
	 */
	public static final SystemMessageId THE_HARVEST_HAS_FAILED;
	
	/**
	 * ID: 893<br>
	 * Message: 種の植付け状態が悪く、収穫に失敗しました。
	 */
	public static final SystemMessageId THE_HARVEST_FAILED_BECAUSE_THE_SEED_WAS_NOT_SOWN;
	
	/**
	 * ID: 894<br>
	 * Message: 製作図は$s1枚だけ登録できます。
	 */
	public static final SystemMessageId UP_TO_S1_RECIPES_CAN_REGISTER;
	
	/**
	 * ID: 895<br>
	 * Message: 登録された製作図がありません。
	 */
	public static final SystemMessageId NO_RECIPES_REGISTERED;
	
	/**
	 * ID: 896<br>
	 * Message: クエスト製作図は登録できません。
	 */
	public static final SystemMessageId FERRY_AT_GLUDIN;
	
	/**
	 * ID: 897<br>
	 * Message: 製作手数料が正しくありません。
	 */
	public static final SystemMessageId FERRY_LEAVE_TALKING;
	
	/**
	 * ID: 898<br>
	 * Message: レベル10以上にならないと、他の人を推薦することはできません。
	 */
	public static final SystemMessageId ONLY_LEVEL_SUP_10_CAN_RECOMMEND;
	
	/**
	 * ID: 899<br>
	 * Message: 紋様を刻むことができません。
	 */
	public static final SystemMessageId CANT_DRAW_SYMBOL;
	
	/**
	 * ID: 900<br>
	 * Message: 紋様を刻む場所がありません。
	 */
	public static final SystemMessageId SYMBOLS_FULL;
	
	/**
	 * ID: 901<br>
	 * Message: 紋様情報を見つけることができません。
	 */
	public static final SystemMessageId SYMBOL_NOT_FOUND;
	
	/**
	 * ID: 902<br>
	 * Message: アイテム数が正しくありません。
	 */
	public static final SystemMessageId NUMBER_INCORRECT;
	
	/**
	 * ID: 903<br>
	 * Message: フリーズ状態ではサポート要請が行えません。しばらくお待ちください。
	 */
	public static final SystemMessageId NO_PETITION_WHILE_FROZEN;
	
	/**
	 * ID: 904<br>
	 * Message: 個人商店を開いた状態ではアイテムを捨てられません。
	 */
	public static final SystemMessageId NO_DISCARD_WHILE_PRIVATE_STORE;
	
	/**
	 * ID: 905<br>
	 * Message: ヒューマンのスコアは $s1点です。
	 */
	public static final SystemMessageId HUMAN_SCORE_S1;
	
	/**
	 * ID: 906<br>
	 * Message: エルフのスコアは $s1点です。
	 */
	public static final SystemMessageId ELVES_SCORE_S1;
	
	/**
	 * ID: 907<br>
	 * Message: ダークエルフのスコアは $s1点です。
	 */
	public static final SystemMessageId DARK_ELVES_SCORE_S1;
	
	/**
	 * ID: 908<br>
	 * Message: オークのスコアは $s1点です。
	 */
	public static final SystemMessageId ORCS_SCORE_S1;
	
	/**
	 * ID: 909<br>
	 * Message: ドワーフのスコアは $s1点です。
	 */
	public static final SystemMessageId DWARVEN_SCORE_S1;
	
	/**
	 * ID: 910<br>
	 * Message: 現在地： $s1、$s2、$s3 (話せる島の村付近)
	 */
	public static final SystemMessageId LOC_TI_S1_S2_S3;
	
	/**
	 * ID: 911<br>
	 * Message: 現在地： $s1、$s2、$s3 (グルーディン村付近)
	 */
	public static final SystemMessageId LOC_GLUDIN_S1_S2_S3;
	
	/**
	 * ID: 912<br>
	 * Message: 現在地： $s1、$s2、$s3 (グルーディオ城の村付近)
	 */
	public static final SystemMessageId LOC_GLUDIO_S1_S2_S3;
	
	/**
	 * ID: 913<br>
	 * Message: 現在地： $s1、$s2、$s3 (中立地帯付近)
	 */
	public static final SystemMessageId LOC_NEUTRAL_ZONE_S1_S2_S3;
	
	/**
	 * ID: 914<br>
	 * Message: 現在地： $s1、$s2、$s3 (エルフ村付近)
	 */
	public static final SystemMessageId LOC_ELVEN_S1_S2_S3;
	
	/**
	 * ID: 915<br>
	 * Message: 現在地： $s1、$s2、$s3 (ダークエルフ村付近)
	 */
	public static final SystemMessageId LOC_DARK_ELVEN_S1_S2_S3;
	
	/**
	 * ID: 916<br>
	 * Message: 現在地： $s1、$s2、$s3 (ディオン城の村付近)
	 */
	public static final SystemMessageId LOC_DION_S1_S2_S3;
	
	/**
	 * ID: 917<br>
	 * Message: 現在地： $s1、$s2、$s3 (フローラン村付近)
	 */
	public static final SystemMessageId LOC_FLORAN_S1_S2_S3;
	
	/**
	 * ID: 918<br>
	 * Message: 現在地： $s1、$s2、$s3 (ギラン城の村付近)
	 */
	public static final SystemMessageId LOC_GIRAN_S1_S2_S3;
	
	/**
	 * ID: 919<br>
	 * Message: 現在地： $s1、$s2、$s3 (ギラン港付近)
	 */
	public static final SystemMessageId LOC_GIRAN_HARBOR_S1_S2_S3;
	
	/**
	 * ID: 920<br>
	 * Message: 現在地： $s1、$s2、$s3 (オーク村付近)
	 */
	public static final SystemMessageId LOC_ORC_S1_S2_S3;
	
	/**
	 * ID: 921<br>
	 * Message: 現在地： $s1、$s2、$s3 (ドワーフ村付近)
	 */
	public static final SystemMessageId LOC_DWARVEN_S1_S2_S3;
	
	/**
	 * ID: 922<br>
	 * Message: 現在地： $s1、$s2、$s3 (オーレン城の村付近)
	 */
	public static final SystemMessageId LOC_OREN_S1_S2_S3;
	
	/**
	 * ID: 923<br>
	 * Message: 現在地： $s1、$s2、$s3 (猟師の村付近)
	 */
	public static final SystemMessageId LOC_HUNTER_S1_S2_S3;
	
	/**
	 * ID: 924<br>
	 * Message: 現在地： $s1、$s2、$s3 (アデン城の村付近)
	 */
	public static final SystemMessageId LOC_ADEN_S1_S2_S3;
	
	/**
	 * ID: 925<br>
	 * Message: 現在地： $s1、$s2、$s3 (コロシアム)
	 */
	public static final SystemMessageId LOC_COLISEUM_S1_S2_S3;
	
	/**
	 * ID: 926<br>
	 * Message: 現在地： $s1、$s2、$s3 (ハイネス付近)
	 */
	public static final SystemMessageId LOC_HEINE_S1_S2_S3;
	
	/**
	 * ID: 927<br>
	 * Message: 現在の時刻は$s1時$s2分です。
	 */
	public static final SystemMessageId TIME_S1_S2_IN_THE_DAY;
	
	/**
	 * ID: 928<br>
	 * Message: 現在の時刻は$s1時$s2分です。
	 */
	public static final SystemMessageId TIME_S1_S2_IN_THE_NIGHT;
	
	/**
	 * ID: 929<br>
	 * Message: 作物に対する報賞は出ませんでした。
	 */
	public static final SystemMessageId NO_COMPENSATION_FOR_FARM_PRODUCTS;
	
	/**
	 * ID: 930<br>
	 * Message: 現在、宝くじの販売を行なっておりません。
	 */
	public static final SystemMessageId NO_LOTTERY_TICKETS_CURRENT_SOLD;
	
	/**
	 * ID: 931<br>
	 * Message: 宝くじ当選番号はまだ発表されておりません。
	 */
	public static final SystemMessageId LOTTERY_WINNERS_NOT_ANNOUNCED_YET;
	
	/**
	 * ID: 932<br>
	 * Message: 観戦中のため、一般チャットはできません。
	 */
	public static final SystemMessageId NO_ALLCHAT_WHILE_OBSERVING;
	
	/**
	 * ID: 933<br>
	 * Message: 種の価格情報が正しくありません。
	 */
	public static final SystemMessageId THE_SEED_PRICING_GREATLY_DIFFERS_FROM_STANDARD_SEED_PRICES;
	
	/**
	 * ID: 934<br>
	 * Message: 削除された製作図です。
	 */
	public static final SystemMessageId A_DELETED_RECIPE;
	
	/**
	 * ID: 935<br>
	 * Message: 金額不足のため、荘園が動作しません。
	 */
	public static final SystemMessageId THE_AMOUNT_IS_NOT_SUFFICIENT_AND_SO_THE_MANOR_IS_NOT_IN_OPERATION;
	
	/**
	 * ID: 936<br>
	 * Message: $s1を使用します。
	 */
	public static final SystemMessageId USE_S1_;
	
	/**
	 * ID: 937<br>
	 * Message: 個人工房の準備中です。
	 */
	public static final SystemMessageId PREPARING_PRIVATE_WORKSHOP;
	
	/**
	 * ID: 938<br>
	 * Message: 現在、コミュニティー サーバーの点検中です。
	 */
	public static final SystemMessageId CB_OFFLINE;
	
	/**
	 * ID: 939<br>
	 * Message: 全体遮断状態なのでトレードができません。
	 */
	public static final SystemMessageId NO_EXCHANGE_WHILE_BLOCKING;
	
	/**
	 * ID: 940<br>
	 * Message: $s1は全体遮断状態です。
	 */
	public static final SystemMessageId S1_BLOCKED_EVERYTHING;
	
	/**
	 * ID: 941<br>
	 * Message: 話せる島の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_TI;
	
	/**
	 * ID: 942<br>
	 * Message: グルーディン村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_GLUDIN;
	
	/**
	 * ID: 943<br>
	 * Message: グルーディオ城の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_GLUDIO;
	
	/**
	 * ID: 944<br>
	 * Message: 中立地帯でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_NEUTRAL_ZONE;
	
	/**
	 * ID: 945<br>
	 * Message: エルフ村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_ELFEN_VILLAGE;
	
	/**
	 * ID: 946<br>
	 * Message: ダークエルフ村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_DARKELF_VILLAGE;
	
	/**
	 * ID: 947<br>
	 * Message: ディオン城の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_DION;
	
	/**
	 * ID: 948<br>
	 * Message: フローラン村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_FLORAN;
	
	/**
	 * ID: 949<br>
	 * Message: ギラン城の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_GIRAN;
	
	/**
	 * ID: 950<br>
	 * Message: ギラン港でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_GIRAN_HARBOR;
	
	/**
	 * ID: 951<br>
	 * Message: オーク村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_ORC_VILLAGE;
	
	/**
	 * ID: 952<br>
	 * Message: ドワーフ村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_DWARFEN_VILLAGE;
	
	/**
	 * ID: 953<br>
	 * Message: オーレン城の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_OREN;
	
	/**
	 * ID: 954<br>
	 * Message: 猟師の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_HUNTERS_VILLAGE;
	
	/**
	 * ID: 955<br>
	 * Message: アデン城の村でリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_ADEN;
	
	/**
	 * ID: 956<br>
	 * Message: コロシアムでリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_COLISEUM;
	
	/**
	 * ID: 957<br>
	 * Message: ハイネスでリスタートします。
	 */
	public static final SystemMessageId RESTART_AT_HEINE;
	
	/**
	 * ID: 958<br>
	 * Message: 個人商店や個人工房の準備中に、アイテムを捨てることや破壊することはできません。
	 */
	public static final SystemMessageId ITEMS_CANNOT_BE_DISCARDED_OR_DESTROYED_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP;
	
	/**
	 * ID: 959<br>
	 * Message: $s1（※$s2）の製作に成功しました。
	 */
	public static final SystemMessageId S1_S2_MANUFACTURED_SUCCESSFULLY;
	
	/**
	 * ID: 960<br>
	 * Message: $s1の製作に失敗しました。
	 */
	public static final SystemMessageId S1_MANUFACTURE_FAILURE;
	
	/**
	 * ID: 961<br>
	 * Message: 現在全体遮断状態です。
	 */
	public static final SystemMessageId BLOCKING_ALL;
	
	/**
	 * ID: 962<br>
	 * Message: 全体遮断状態を解除しました。
	 */
	public static final SystemMessageId NOT_BLOCKING_ALL;
	
	/**
	 * ID: 963<br>
	 * Message: 製作価格を決定してください。
	 */
	public static final SystemMessageId DETERMINE_MANUFACTURE_PRICE;
	
	/**
	 * ID: 964<br>
	 * Message: 連続チャットにより、約1分間チャットが禁止されました。
	 */
	public static final SystemMessageId CHATBAN_FOR_1_MINUTE;
	
	/**
	 * ID: 965<br>
	 * Message: チャット禁止が解除されました。
	 */
	public static final SystemMessageId CHATBAN_REMOVED;
	
	/**
	 * ID: 966<br>
	 * Message: チャット禁止中です。解除される前にチャットを試みる場合、解除までの時間がより長くなりますのでご注意ください。
	 */
	public static final SystemMessageId CHATTING_IS_CURRENTLY_PROHIBITED;
	
	/**
	 * ID: 967<br>
	 * Message: $c1 のパーティ勧誘に応じますか。（アイテム分配：スポイルを含めランダムに）
	 */
	public static final SystemMessageId C1_PARTY_INVITE_RANDOM_INCLUDING_SPOIL;
	
	/**
	 * ID: 968<br>
	 * Message: $c1 のパーティ勧誘に応じますか。（アイテム分配：順番に取得）
	 */
	public static final SystemMessageId C1_PARTY_INVITE_BY_TURN;
	
	/**
	 * ID: 969<br>
	 * Message: $c1 のパーティ勧誘に応じますか。（アイテム分配：スポイルを含め順番に）
	 */
	public static final SystemMessageId C1_PARTY_INVITE_BY_TURN_INCLUDING_SPOIL;
	
	/**
	 * ID: 970<br>
	 * Message: $c1から$s2のMPドレインを受けました。
	 */
	public static final SystemMessageId S2_MP_HAS_BEEN_DRAINED_BY_C1;
	
	/**
	 * ID: 971<br>
	 * Message: 255文字を超えるサポート要請はできません。お手数ですが文章を短くして再度要請を行なってください。
	 */
	public static final SystemMessageId PETITION_MAX_CHARS_255;
	
	/**
	 * ID: 972<br>
	 * Message: ペットが使用できないアイテムです。
	 */
	public static final SystemMessageId PET_CANNOT_USE_ITEM;
	
	/**
	 * ID: 973<br>
	 * Message: 持っている量の範囲内で入力してください。
	 */
	public static final SystemMessageId INPUT_NO_MORE_YOU_HAVE;
	
	/**
	 * ID: 974<br>
	 * Message: ソウル ストーンが魂の吸収に成功しました。
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_SUCCEEDED;
	
	/**
	 * ID: 975<br>
	 * Message: ソウル ストーンが魂の吸収に失敗しました。
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_FAILED;
	
	/**
	 * ID: 976<br>
	 * Message: ソウル ストーンが魂の気に耐えられず砕けました。
	 */
	public static final SystemMessageId SOUL_CRYSTAL_BROKE;
	
	/**
	 * ID: 977<br>
	 * Message: ソウル ストーンが共鳴を起こし魂の吸収に失敗しました。
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_FAILED_RESONATION;
	
	/**
	 * ID: 978<br>
	 * Message: ソウル ストーンが魂の吸収を拒否しています。
	 */
	public static final SystemMessageId SOUL_CRYSTAL_ABSORBING_REFUSED;
	
	/**
	 * ID: 979<br>
	 * Message: 話せる島の港に到着しました。
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_TALKING;
	
	/**
	 * ID: 980<br>
	 * Message: 10分間の停泊後、グルーディンに出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GLUDIN_AFTER_10_MINUTES;
	
	/**
	 * ID: 981<br>
	 * Message: 5分後に話せる島の港からグルーディンへ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GLUDIN_IN_5_MINUTES;
	
	/**
	 * ID: 982<br>
	 * Message: 1分後に話せる島の港からグルーディンへ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GLUDIN_IN_1_MINUTE;
	
	/**
	 * ID: 983<br>
	 * Message: 乗船される方は急いでください。
	 */
	public static final SystemMessageId MAKE_HASTE_GET_ON_BOAT;
	
	/**
	 * ID: 984<br>
	 * Message: 間もなく話せる島の港からグルーディンへ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_SOON_FOR_GLUDIN;
	
	/**
	 * ID: 985<br>
	 * Message: 話せる島の港からグルーディンへ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_GLUDIN;
	
	/**
	 * ID: 986<br>
	 * Message: グルーディン港に到着しました。
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_GLUDIN;
	
	/**
	 * ID: 987<br>
	 * Message: 10分間の停泊後、話せる島に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_TALKING_AFTER_10_MINUTES;
	
	/**
	 * ID: 988<br>
	 * Message: 5分後にグルーディン港から話せる島へ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_TALKING_IN_5_MINUTES;
	
	/**
	 * ID: 989<br>
	 * Message: 1分後にグルーディン港から話せる島へ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_TALKING_IN_1_MINUTE;
	
	/**
	 * ID: 990<br>
	 * Message: 間もなくグルーディン港から話せる島へ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_SOON_FOR_TALKING;
	
	/**
	 * ID: 991<br>
	 * Message: グルーディン港から話せる島へ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_TALKING;
	
	/**
	 * ID: 992<br>
	 * Message: ギラン港に到着しました。
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_GIRAN;
	
	/**
	 * ID: 993<br>
	 * Message: 10分間停泊した後、ギランに出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GIRAN_AFTER_10_MINUTES;
	
	/**
	 * ID: 994<br>
	 * Message: 5分後にギラン港に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GIRAN_IN_5_MINUTES;
	
	/**
	 * ID: 995<br>
	 * Message: 1分後にギラン港に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_FOR_GIRAN_IN_1_MINUTE;
	
	/**
	 * ID: 996<br>
	 * Message: まもなくギラン港に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVE_SOON_FOR_GIRAN;
	
	/**
	 * ID: 997<br>
	 * Message: ギラン港に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_GIRAN;
	
	/**
	 * ID: 998<br>
	 * Message: インナドリル遊覧船が到着しました。10分間停泊します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_ANCHOR_10_MINUTES;
	
	/**
	 * ID: 999<br>
	 * Message: 5分後にインナドリル遊覧船が出発します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVE_IN_5_MINUTES;
	
	/**
	 * ID: 1000<br>
	 * Message: 1分後にインナドリル遊覧船が出発します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVE_IN_1_MINUTE;
	
	/**
	 * ID: 1001<br>
	 * Message: まもなくインナドリル遊覧船が出発します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVE_SOON;
	
	/**
	 * ID: 1002<br>
	 * Message: インナドリル遊覧船が出発します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_LEAVING;
	
	/**
	 * ID: 1003<br>
	 * Message: モンスター レース チケットの清算ができません。
	 */
	public static final SystemMessageId CANNOT_POSSES_MONS_TICKET;
	
	/**
	 * ID: 1004<br>
	 * Message: アジトの競売に登録しました。
	 */
	public static final SystemMessageId REGISTERED_FOR_CLANHALL;
	
	/**
	 * ID: 1005<br>
	 * Message: 血盟倉庫にアデナが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_ADENA_IN_CWH;
	
	/**
	 * ID: 1006<br>
	 * Message: アジトの競売に入札しました。
	 */
	public static final SystemMessageId BID_IN_CLANHALL_AUCTION;
	
	/**
	 * ID: 1007<br>
	 * Message: $s1の予選登録が締め切られました。
	 */
	public static final SystemMessageId PRELIMINARY_REGISTRATION_OF_S1_FINISHED;
	
	/**
	 * ID: 1008<br>
	 * Message: 空腹状態のストライダーには乗り降りできません。
	 */
	public static final SystemMessageId HUNGRY_STRIDER_NOT_MOUNT;
	
	/**
	 * ID: 1009<br>
	 * Message: 死亡状態ではストライダーに乗ることができません。
	 */
	public static final SystemMessageId STRIDER_CANT_BE_RIDDEN_WHILE_DEAD;
	
	/**
	 * ID: 1010<br>
	 * Message: 死亡したストライダーに乗ることはできません。
	 */
	public static final SystemMessageId DEAD_STRIDER_CANT_BE_RIDDEN;
	
	/**
	 * ID: 1011<br>
	 * Message: 戦闘中のストライダーに乗ることはできません。
	 */
	public static final SystemMessageId STRIDER_IN_BATLLE_CANT_BE_RIDDEN;
	
	/**
	 * ID: 1012<br>
	 * Message: 戦闘中はストライダーに乗ることができません。
	 */
	public static final SystemMessageId STRIDER_CANT_BE_RIDDEN_WHILE_IN_BATTLE;
	
	/**
	 * ID: 1013<br>
	 * Message: 立っている状態のストライダーだけに乗ることができます。
	 */
	public static final SystemMessageId STRIDER_CAN_BE_RIDDEN_ONLY_WHILE_STANDING;
	
	/**
	 * ID: 1014<br>
	 * Message: ペットが$s1の経験値を得ました。
	 */
	public static final SystemMessageId PET_EARNED_S1_EXP;
	
	/**
	 * ID: 1015<br>
	 * Message: ペットが$s1のダメージを与えました。
	 */
	public static final SystemMessageId PET_HIT_FOR_S1_DAMAGE;
	
	/**
	 * ID: 1016<br>
	 * Message: ペットが$c1によって$s2のダメージを受けました。
	 */
	public static final SystemMessageId PET_RECEIVED_S2_DAMAGE_BY_C1;
	
	/**
	 * ID: 1017<br>
	 * Message: ペットのクリティカル ヒット！
	 */
	public static final SystemMessageId CRITICAL_HIT_BY_PET;
	
	/**
	 * ID: 1018<br>
	 * Message: ペットが$s1を使用します。
	 */
	public static final SystemMessageId PET_USES_S1;
	
	/**
	 * ID: 1019<br>
	 * Message: ペットが$s1を使用します。
	 */
	public static final SystemMessageId PET_USES_S1_;
	
	/**
	 * ID: 1020<br>
	 * Message: ペットが$s1を得ました。
	 */
	public static final SystemMessageId PET_PICKED_S1;
	
	/**
	 * ID: 1021<br>
	 * Message: ペットが$s1 $s2個を得ました。
	 */
	public static final SystemMessageId PET_PICKED_S2_S1_S;
	
	/**
	 * ID: 1022<br>
	 * Message: ペットが+$s1$s2を得ました。
	 */
	public static final SystemMessageId PET_PICKED_S1_S2;
	
	/**
	 * ID: 1023<br>
	 * Message: ペットが$s1アデナを得ました。
	 */
	public static final SystemMessageId PET_PICKED_S1_ADENA;
	
	/**
	 * ID: 1024<br>
	 * Message: ペットが$s1を装備しました。
	 */
	public static final SystemMessageId PET_PUT_ON_S1;
	
	/**
	 * ID: 1025<br>
	 * Message: ペットが$s1の装備を解除しました。
	 */
	public static final SystemMessageId PET_TOOK_OFF_S1;
	
	/**
	 * ID: 1026<br>
	 * Message: 召喚獣が$s1のダメージを与えました。
	 */
	public static final SystemMessageId SUMMON_GAVE_DAMAGE_S1;
	
	/**
	 * ID: 1027<br>
	 * Message: 召喚獣が$c1によって$s2のダメージを受けました。
	 */
	public static final SystemMessageId SUMMON_RECEIVED_DAMAGE_S2_BY_S1;
	
	/**
	 * ID: 1028<br>
	 * Message: 召喚獣のクリティカル ヒット！
	 */
	public static final SystemMessageId CRITICAL_HIT_BY_SUMMONED_MOB;
	
	/**
	 * ID: 1029<br>
	 * Message: 召喚獣が$s1を使用します。
	 */
	public static final SystemMessageId SUMMONED_MOB_USES_S1;
	
	/**
	 * ID: 1030<br>
	 * Message: =======<パーティ情報>=======
	 */
	public static final SystemMessageId PARTY_INFORMATION;
	
	/**
	 * ID: 1031<br>
	 * Message: タイプ： 拾った人が所有
	 */
	public static final SystemMessageId LOOTING_FINDERS_KEEPERS;
	
	/**
	 * ID: 1032<br>
	 * Message: タイプ： ランダムに分配
	 */
	public static final SystemMessageId LOOTING_RANDOM;
	
	/**
	 * ID: 1033<br>
	 * Message: タイプ： スポイルを含めランダムに
	 */
	public static final SystemMessageId LOOTING_RANDOM_INCLUDE_SPOIL;
	
	/**
	 * ID: 1034<br>
	 * Message: タイプ： 順番に取得
	 */
	public static final SystemMessageId LOOTING_BY_TURN;
	
	/**
	 * ID: 1035<br>
	 * Message: タイプ： スポイルを含め順番に
	 */
	public static final SystemMessageId LOOTING_BY_TURN_INCLUDE_SPOIL;
	
	/**
	 * ID: 1036<br>
	 * Message: 入力可能な数量を超過しました。
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_QUANTITY_THAT_CAN_BE_INPUTTED;
	
	/**
	 * ID: 1037<br>
	 * Message: $c1が$s2を製作しました。
	 */
	public static final SystemMessageId C1_MANUFACTURED_S2;
	
	/**
	 * ID: 1038<br>
	 * Message: $c1が$s2 $s3個を製作しました。
	 */
	public static final SystemMessageId C1_MANUFACTURED_S3_S2_S;
	
	/**
	 * ID: 1039<br>
	 * Message: 血盟倉庫に預けたアイテムは血盟主のみ取り出しが可能です。それでもよろしいですか。
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_CAN_RETRIEVE_ITEMS_FROM_CLAN_WAREHOUSE;
	
	/**
	 * ID: 1040<br>
	 * Message: 発送したトッピング アイテムは全地域の境界の商人から取り出せます。よろしいですか。
	 */
	public static final SystemMessageId ITEMS_SENT_BY_FREIGHT_PICKED_UP_FROM_ANYWHERE;
	
	/**
	 * ID: 1041<br>
	 * Message: 次の種の購入代金は$s1アデナです。
	 */
	public static final SystemMessageId THE_NEXT_SEED_PURCHASE_PRICE_IS_S1_ADENA;
	
	/**
	 * ID: 1042<br>
	 * Message: 次の作物の販売代金は$s1アデナです。
	 */
	public static final SystemMessageId THE_NEXT_FARM_GOODS_PURCHASE_PRICE_IS_S1_ADENA;
	
	/**
	 * ID: 1043<br>
	 * Message: 現在、「/escape」コマンドは使用できません。サポート要請を行なってください。
	 */
	public static final SystemMessageId NO_UNSTUCK_PLEASE_SEND_PETITION;
	
	/**
	 * ID: 1044<br>
	 * Message: チケット販売中はモンスター レースの配当表示を見ることができません。
	 */
	public static final SystemMessageId MONSRACE_NO_PAYOUT_INFO;
	
	/**
	 * ID: 1046<br>
	 * Message: 販売が終了し、モンスター レースのチケットを購入できません。
	 */
	public static final SystemMessageId MONSRACE_TICKETS_NOT_AVAILABLE;
	
	/**
	 * ID: 1047<br>
	 * Message: $s1のアイテム製作に失敗しました。
	 */
	public static final SystemMessageId NOT_SUCCEED_PRODUCING_S1;
	
	/**
	 * ID: 1048<br>
	 * Message: 全体遮断状態なのでウィスパーを使用できません。
	 */
	public static final SystemMessageId NO_WHISPER_WHEN_BLOCKING;
	
	/**
	 * ID: 1049<br>
	 * Message: 全体遮断状態なのでパーティーの招待ができません。
	 */
	public static final SystemMessageId NO_PARTY_WHEN_BLOCKING;
	
	/**
	 * ID: 1050<br>
	 * Message: 自分の血盟コミュニティーがありません。村の大神官又は大長老を訪ねて血盟のレベルを2以上に上げればコミュニティーが生成されます。
	 */
	public static final SystemMessageId NO_CB_IN_MY_CLAN;
	
	/**
	 * ID: 1051<br>
	 * Message: アジトの使用料金が支払われませんでした。明日$s1時までに使用料金を血盟倉庫にお納めください。
	 */
	public static final SystemMessageId PAYMENT_FOR_YOUR_CLAN_HALL_HAS_NOT_BEEN_MADE_PLEASE_MAKE_PAYMENT_TO_YOUR_CLAN_WAREHOUSE_BY_S1_TOMORROW;
	
	/**
	 * ID: 1052<br>
	 * Message: アジト使用料金を一週間滞納したためアジトが返還されました。
	 */
	public static final SystemMessageId THE_CLAN_HALL_FEE_IS_ONE_WEEK_OVERDUE_THEREFORE_THE_CLAN_HALL_OWNERSHIP_HAS_BEEN_REVOKED;
	
	/**
	 * ID: 1053<br>
	 * Message: 攻城戦中の戦場では復活できません。
	 */
	public static final SystemMessageId CANNOT_BE_RESURRECTED_DURING_SIEGE;
	
	/**
	 * ID: 1054<br>
	 * Message: 神秘の気に満ちたエリアに入りました。
	 */
	public static final SystemMessageId ENTERED_MYSTICAL_LAND;
	
	/**
	 * ID: 1055<br>
	 * Message: 神秘の気に満ちたエリアから離れました。
	 */
	public static final SystemMessageId EXITED_MYSTICAL_LAND;
	
	/**
	 * ID: 1056<br>
	 * Message: 城の金庫に保管できるアデナの限度額を超えました。
	 */
	public static final SystemMessageId VAULT_CAPACITY_EXCEEDED;
	
	/**
	 * ID: 1057<br>
	 * Message: リラックス サーバーでのみ使用可能なコマンドです。
	 */
	public static final SystemMessageId RELAX_SERVER_ONLY;
	
	/**
	 * ID: 1058<br>
	 * Message: 種の販売代金は$s1アデナです。
	 */
	public static final SystemMessageId THE_SALES_PRICE_FOR_SEEDS_IS_S1_ADENA;
	
	/**
	 * ID: 1059<br>
	 * Message: 残りの買い入れ代金は$s1アデナです。
	 */
	public static final SystemMessageId THE_REMAINING_PURCHASING_IS_S1_ADENA;
	
	/**
	 * ID: 1060<br>
	 * Message: 種を販売して残った金額は$s1アデナです。
	 */
	public static final SystemMessageId THE_REMAINDER_AFTER_SELLING_THE_SEEDS_IS_S1;
	
	/**
	 * ID: 1061<br>
	 * Message: アイテム製作スキルが足りないため、製作図を登録できません。
	 */
	public static final SystemMessageId CANT_REGISTER_NO_ABILITY_TO_CRAFT;
	
	/**
	 * ID: 1062<br>
	 * Message: 新規作成はレベル10から可能です。
	 */
	public static final SystemMessageId WRITING_SOMETHING_NEW_POSSIBLE_AFTER_LEVEL_10;
	
	/**
	 * ID: 1063<br>
	 * Message: $s1時から$s2時まではサポートを利用できません。移動が不可能な地形に閉じ込められた場合は「/escape」コマンドをご利用ください。
	 */
	public static final SystemMessageId PETITION_UNAVAILABLE;
	
	/**
	 * ID: 1064<br>
	 * Message: +$s1$s2の装備を解除しました。
	 */
	public static final SystemMessageId EQUIPMENT_S1_S2_REMOVED;
	
	/**
	 * ID: 1065<br>
	 * Message: 個人商店や個人工房の場合、アイテムを捨てることやトレードすることはできません。
	 */
	public static final SystemMessageId CANNOT_TRADE_DISCARD_DROP_ITEM_WHILE_IN_SHOPMODE;
	
	/**
	 * ID: 1066<br>
	 * Message: $s1のHPが回復されました。
	 */
	public static final SystemMessageId S1_HP_RESTORED;
	
	/**
	 * ID: 1067<br>
	 * Message: $c1によってHPが$s2回復されました。
	 */
	public static final SystemMessageId S2_HP_RESTORED_BY_C1;
	
	/**
	 * ID: 1068<br>
	 * Message: MPが$s1回復しました。
	 */
	public static final SystemMessageId S1_MP_RESTORED;
	
	/**
	 * ID: 1069<br>
	 * Message: $c1によってMPが$s2回復しました。
	 */
	public static final SystemMessageId S2_MP_RESTORED_BY_C1;
	
	/**
	 * ID: 1070<br>
	 * Message: 読み込み権限がありません。
	 */
	public static final SystemMessageId NO_READ_PERMISSION;
	
	/**
	 * ID: 1071<br>
	 * Message: 書き込み権限がありません。
	 */
	public static final SystemMessageId NO_WRITE_PERMISSION;
	
	/**
	 * ID: 1072<br>
	 * Message: 第$s1回目モンスターレースの単勝式チケットを受け取りました。
	 */
	public static final SystemMessageId OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE;
	
	/**
	 * ID: 1073<br>
	 * Message: 第$s1回目モンスターレースの連複式チケットを受け取りました。
	 */
	public static final SystemMessageId OBTAINED_TICKET_FOR_MONS_RACE_S1_SINGLE_;
	
	/**
	 * ID: 1074<br>
	 * Message: 年齢制限でモンスター レースのチケットを購入できません。
	 */
	public static final SystemMessageId NOT_MEET_AGE_REQUIREMENT_FOR_MONS_RACE;
	
	/**
	 * ID: 1075<br>
	 * Message: 再入札価格は既存の入札価格より高く設定してください。
	 */
	public static final SystemMessageId BID_AMOUNT_HIGHER_THAN_PREVIOUS_BID;
	
	/**
	 * ID: 1076<br>
	 * Message: ゲームを終了できません。
	 */
	public static final SystemMessageId GAME_CANNOT_TERMINATE_NOW;
	
	/**
	 * ID: 1077<br>
	 * Message: GameGuardのエラーです。
	 */
	public static final SystemMessageId GG_EXECUTION_ERROR;
	
	/**
	 * ID: 1078<br>
	 * Message: 短時間で重複する内容の発言を多量に入力すると自動的にチャット禁止になりますので、ご注意ください。
	 */
	public static final SystemMessageId DONT_SPAM;
	
	/**
	 * ID: 1079<br>
	 * Message: 相手がチャット禁止中です。
	 */
	public static final SystemMessageId TARGET_IS_CHAT_BANNED;
	
	/**
	 * ID: 1080<br>
	 * Message: フェイスタイプのポーション：Aを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_A;
	
	/**
	 * ID: 1081<br>
	 * Message: ヘアカラーのポーション：Aを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_A;
	
	/**
	 * ID: 1082<br>
	 * Message: ヘアスタイルのポーション：Aを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_A;
	
	/**
	 * ID: 1083<br>
	 * Message: フェイスタイプのポーション：Aを使用しました。
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_A_APPLIED;
	
	/**
	 * ID: 1084<br>
	 * Message: ヘアカラーのポーション：Aを使用しました。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_A_APPLIED;
	
	/**
	 * ID: 1085<br>
	 * Message: ヘアスタイルのポーション：Aを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_A_USED;
	
	/**
	 * ID: 1086<br>
	 * Message: フェイスタイプが変更されました。
	 */
	public static final SystemMessageId FACE_APPEARANCE_CHANGED;
	
	/**
	 * ID: 1087<br>
	 * Message: ヘアカラーが変更されました。
	 */
	public static final SystemMessageId HAIR_COLOR_CHANGED;
	
	/**
	 * ID: 1088<br>
	 * Message: ヘアスタイルが変更されました。
	 */
	public static final SystemMessageId HAIR_STYLE_CHANGED;
	
	/**
	 * ID: 1089<br>
	 * Message: $c1 が1周年記念アイテムを得ました。
	 */
	public static final SystemMessageId C1_OBTAINED_ANNIVERSARY_ITEM;
	
	/**
	 * ID: 1090<br>
	 * Message: フェイスタイプのポーション：Bを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_B;
	
	/**
	 * ID: 1091<br>
	 * Message: フェイスタイプのポーション：Cを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_C;
	
	/**
	 * ID: 1092<br>
	 * Message: ヘアカラーのポーション：Bを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_B;
	
	/**
	 * ID: 1093<br>
	 * Message: ヘアカラーのポーション：Cを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_C;
	
	/**
	 * ID: 1094<br>
	 * Message: ヘアカラーのポーション：Dを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_D;
	
	/**
	 * ID: 1095<br>
	 * Message: ヘアスタイルのポーション：Bを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_B;
	
	/**
	 * ID: 1096<br>
	 * Message: ヘアスタイルのポーション：Cを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_C;
	
	/**
	 * ID: 1097<br>
	 * Message: ヘアスタイルのポーション：Dを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_D;
	
	/**
	 * ID: 1098<br>
	 * Message: ヘアスタイルのポーション：Eを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_E;
	
	/**
	 * ID: 1099<br>
	 * Message: ヘアスタイルのポーション：Fを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_F;
	
	/**
	 * ID: 1100<br>
	 * Message: ヘアスタイルのポーション：Gを使用しますか。使用後の効果は永久に持続します。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_G;
	
	/**
	 * ID: 1101<br>
	 * Message: フェイスタイプのポーション：Bを使用しました。
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_B_APPLIED;
	
	/**
	 * ID: 1102<br>
	 * Message: フェイスタイプのポーション：Cを使用しました。
	 */
	public static final SystemMessageId FACELIFT_POTION_TYPE_C_APPLIED;
	
	/**
	 * ID: 1103<br>
	 * Message: ヘアカラーのポーション：Bを使用しました。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_B_APPLIED;
	
	/**
	 * ID: 1104<br>
	 * Message: ヘアカラーのポーション：Cを使用しました。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_C_APPLIED;
	
	/**
	 * ID: 1105<br>
	 * Message: ヘアカラーのポーション：Dを使用しました。
	 */
	public static final SystemMessageId HAIRDYE_POTION_TYPE_D_APPLIED;
	
	/**
	 * ID: 1106<br>
	 * Message: ヘアスタイルのポーション：Bを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_B_USED;
	
	/**
	 * ID: 1107<br>
	 * Message: ヘアスタイルのポーション：Cを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_C_USED;
	
	/**
	 * ID: 1108<br>
	 * Message: ヘアスタイルのポーション：Dを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_D_USED;
	
	/**
	 * ID: 1109<br>
	 * Message: ヘアスタイルのポーション：Eを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_E_USED;
	
	/**
	 * ID: 1110<br>
	 * Message: ヘアスタイルのポーション：Fを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_F_USED;
	
	/**
	 * ID: 1111<br>
	 * Message: ヘアスタイルのポーション：Gを使用しました。
	 */
	public static final SystemMessageId HAIRSTYLE_POTION_TYPE_G_USED;
	
	/**
	 * ID: 1112<br>
	 * Message: 第$s1回目宝くじの当選金額は$s2アデナで、$s3名様が1等に当選しました。
	 */
	public static final SystemMessageId AMOUNT_FOR_WINNER_S1_IS_S2_ADENA_WE_HAVE_S3_PRIZE_WINNER;
	
	/**
	 * ID: 1113<br>
	 * Message: 第$s1回目宝くじの当選金額は$s2アデナでしたが、高額当選者がいないため、当選金の繰り越しが発生しました。
	 */
	public static final SystemMessageId AMOUNT_FOR_LOTTERY_S1_IS_S2_ADENA_NO_WINNER;
	
	/**
	 * ID: 1114<br>
	 * Message: 解散猶予期間中は攻城戦に登録できません。
	 */
	public static final SystemMessageId CANT_PARTICIPATE_IN_SIEGE_WHILE_DISSOLUTION_IN_PROGRESS;
	
	/**
	 * ID: 1115<br>
	 * Message: 戦闘中は個人降伏することはできません。
	 */
	public static final SystemMessageId INDIVIDUALS_NOT_SURRENDER_DURING_COMBAT;
	
	/**
	 * ID: 1116<br>
	 * Message: 戦闘中は血盟から脱退できません。
	 */
	public static final SystemMessageId YOU_CANNOT_LEAVE_DURING_COMBAT;
	
	/**
	 * ID: 1117<br>
	 * Message: 戦闘中の血盟員を追放することはできません。
	 */
	public static final SystemMessageId CLAN_MEMBER_CANNOT_BE_DISMISSED_DURING_COMBAT;
	
	/**
	 * ID: 1118<br>
	 * Message: インベントリの重量または個数が80％未満の時のみ進行できます。
	 */
	public static final SystemMessageId INVENTORY_LESS_THAN_80_PERCENT;
	
	/**
	 * ID: 1119<br>
	 * Message: クエストの進行が正常に行なえない状況でクエストを試みたので、クエストが自動的に取り消されました。
	 */
	public static final SystemMessageId QUEST_CANCELED_INVENTORY_EXCEEDS_80_PERCENT;
	
	/**
	 * ID: 1120<br>
	 * Message: 血盟から脱退できませんでした。
	 */
	public static final SystemMessageId STILL_CLAN_MEMBER;
	
	/**
	 * ID: 1121<br>
	 * Message: 推薦権がありません。
	 */
	public static final SystemMessageId NO_RIGHT_TO_VOTE;
	
	/**
	 * ID: 1122<br>
	 * Message: 推薦する相手が存在しません。
	 */
	public static final SystemMessageId NO_CANDIDATE;
	
	/**
	 * ID: 1123<br>
	 * Message: インベントリの重量または個数が制限を越えているため、このスキルは使用できません。
	 */
	public static final SystemMessageId WEIGHT_EXCEEDED_SKILL_UNAVAILABLE;
	
	/**
	 * ID: 1124<br>
	 * Message: スキル使用中のため、アイテム製作ウィンドウを開くことができません。
	 */
	public static final SystemMessageId NO_RECIPE_BOOK_WHILE_CASTING;
	
	/**
	 * ID: 1125<br>
	 * Message: トレード中は製作できません。
	 */
	public static final SystemMessageId CANNOT_CREATED_WHILE_ENGAGED_IN_TRADING;
	
	/**
	 * ID: 1126<br>
	 * Message: マイナスの値は入力できません。
	 */
	public static final SystemMessageId NO_NEGATIVE_NUMBER;
	
	/**
	 * ID: 1127<br>
	 * Message: 基本価格の10倍以上の価格は設定できません。
	 */
	public static final SystemMessageId REWARD_LESS_THAN_10_TIMES_STANDARD_PRICE;
	
	/**
	 * ID: 1128<br>
	 * Message: スキル使用中のため、個人商店や個人工房を開くことができません。
	 */
	public static final SystemMessageId PRIVATE_STORE_NOT_WHILE_CASTING;
	
	/**
	 * ID: 1129<br>
	 * Message: 乗船中のため、その行動はできませんでした。
	 */
	public static final SystemMessageId NOT_ALLOWED_ON_BOAT;
	
	/**
	 * ID: 1130<br>
	 * Message: 相手に$s1のダメージを与え、ダメージの転移対象に$s2のダメージを与えました。
	 */
	public static final SystemMessageId GIVEN_S1_DAMAGE_TO_YOUR_TARGET_AND_S2_DAMAGE_TO_SERVITOR;
	
	/**
	 * ID: 1131<br>
	 * Message: 深夜になったため、$s1の効果を感じます。
	 */
	public static final SystemMessageId NIGHT_EFFECT_APPLIES;
	
	/**
	 * ID: 1132<br>
	 * Message: 夜が明けたため、$s1の効果が消えました。
	 */
	public static final SystemMessageId DAY_EFFECT_DISAPPEARS;
	
	/**
	 * ID: 1133<br>
	 * Message: HPが減ったため、$s1の効果を感じます。
	 */
	public static final SystemMessageId HP_DECREASED_EFFECT_APPLIES;
	
	/**
	 * ID: 1134<br>
	 * Message: HPが増えたため、$s1の効果が消えました。
	 */
	public static final SystemMessageId HP_INCREASED_EFFECT_DISAPPEARS;
	
	/**
	 * ID: 1135<br>
	 * Message: 戦闘中に個人商店を開くことはできません。
	 */
	public static final SystemMessageId CANT_OPERATE_PRIVATE_STORE_DURING_COMBAT;
	
	/**
	 * ID: 1136<br>
	 * Message: 不正なアクセスを試みたIPアドレスのため、$s1分間このサーバーに接続できません。別のサーバーをご利用ください。
	 */
	public static final SystemMessageId ACCOUNT_NOT_ALLOWED_TO_CONNECT;
	
	/**
	 * ID: 1137<br>
	 * Message: $c1が $s2 $s3個を収穫しました。
	 */
	public static final SystemMessageId C1_HARVESTED_S3_S2S;
	
	/**
	 * ID: 1138<br>
	 * Message: $c1が$s2を収穫しました。
	 */
	public static final SystemMessageId C1_HARVESTED_S2S;
	
	/**
	 * ID: 1139<br>
	 * Message: インベントリの総重量やスロット数の制限を超えることはできません。
	 */
	public static final SystemMessageId INVENTORY_LIMIT_MUST_NOT_BE_EXCEEDED;
	
	/**
	 * ID: 1140<br>
	 * Message: 門を開けますか。
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_OPEN_THE_GATE;
	
	/**
	 * ID: 1141<br>
	 * Message: 門を閉じますか。
	 */
	public static final SystemMessageId WOULD_YOU_LIKE_TO_CLOSE_THE_GATE;
	
	/**
	 * ID: 1142<br>
	 * Message: 近くにすでに$s1がいるため、重複して召喚できません。
	 */
	public static final SystemMessageId CANNOT_SUMMON_S1_AGAIN;
	
	/**
	 * ID: 1143<br>
	 * Message: 召喚獣の維持に必要なアイテムが足りないため、召喚獣が消えてしまいました。
	 */
	public static final SystemMessageId SERVITOR_DISAPPEARED_NOT_ENOUGH_ITEMS;
	
	/**
	 * ID: 1144<br>
	 * Message: ゲーム内に対話相手がいません。
	 */
	public static final SystemMessageId NOBODY_IN_GAME_TO_CHAT;
	
	/**
	 * ID: 1145<br>
	 * Message: $c1に $s2を $s3 アデナで製作しました。
	 */
	public static final SystemMessageId S2_CREATED_FOR_C1_FOR_S3_ADENA;
	
	/**
	 * ID: 1146<br>
	 * Message: $c1が $s2を $s3 アデナで製作しました。
	 */
	public static final SystemMessageId C1_CREATED_S2_FOR_S3_ADENA;
	
	/**
	 * ID: 1147<br>
	 * Message: $c1に $s2 $s3個を $s4アデナで製作しました。
	 */
	public static final SystemMessageId S2_S3_S_CREATED_FOR_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1148<br>
	 * Message: $c1が $s2 $s3個を $s4アデナで製作しました。
	 */
	public static final SystemMessageId C1_CREATED_S2_S3_S_FOR_S4_ADENA;
	
	/**
	 * ID: 1149<br>
	 * Message: $c1に $s2を $s3 アデナで製作できませんでした。
	 */
	public static final SystemMessageId CREATION_OF_S2_FOR_C1_AT_S3_ADENA_FAILED;
	
	/**
	 * ID: 1150<br>
	 * Message: $c1が $s2を $s3 アデナで製作できませんでした。
	 */
	public static final SystemMessageId C1_FAILED_TO_CREATE_S2_FOR_S3_ADENA;
	
	/**
	 * ID: 1151<br>
	 * Message: $c1に $s2を $s3 アデナで販売しました。
	 */
	public static final SystemMessageId S2_SOLD_TO_C1_FOR_S3_ADENA;
	
	/**
	 * ID: 1152<br>
	 * Message: $c1に $s2 $s3個を $s4 アデナで販売しました。
	 */
	public static final SystemMessageId S3_S2_S_SOLD_TO_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1153<br>
	 * Message: $c1から $s2を $s3 アデナで購入しました。
	 */
	public static final SystemMessageId S2_PURCHASED_FROM_C1_FOR_S3_ADENA;
	
	/**
	 * ID: 1154<br>
	 * Message: $c1から $s2 $s3個を $s4 アデナで購入しました。
	 */
	public static final SystemMessageId S3_S2_S_PURCHASED_FROM_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1155<br>
	 * Message: $c1に +$s2$s3を $s4 アデナで販売しました。
	 */
	public static final SystemMessageId S3_S2_SOLD_TO_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1156<br>
	 * Message: $c1から +$s2$s3を $s4 アデナで購入しました。
	 */
	public static final SystemMessageId S2_S3_PURCHASED_FROM_C1_FOR_S4_ADENA;
	
	/**
	 * ID: 1157<br>
	 * Message: 試着は約10秒間で、状態の変化があると取り消される場合があります。
	 */
	public static final SystemMessageId TRYING_ON_STATE;
	
	/**
	 * ID: 1158<br>
	 * Message: 高過ぎる所からは飛び降りることができません。
	 */
	public static final SystemMessageId CANNOT_DISMOUNT_FROM_ELEVATION;
	
	/**
	 * ID: 1159<br>
	 * Message: 話せる島発の定期船が約10分後にグルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_10_MINUTES;
	
	/**
	 * ID: 1160<br>
	 * Message: 話せる島発の定期船が約5分後にグルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_5_MINUTES;
	
	/**
	 * ID: 1161<br>
	 * Message: 話せる島発の定期船が約1分後にグルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GLUDIN_1_MINUTE;
	
	/**
	 * ID: 1162<br>
	 * Message: ギラン港発の定期船が約15分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_15_MINUTES;
	
	/**
	 * ID: 1163<br>
	 * Message: ギラン港発の定期船が約10分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_10_MINUTES;
	
	/**
	 * ID: 1164<br>
	 * Message: ギラン港発の定期船が約5分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_5_MINUTES;
	
	/**
	 * ID: 1165<br>
	 * Message: ギラン港発の定期船が約1分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GIRAN_ARRIVE_AT_TALKING_1_MINUTE;
	
	/**
	 * ID: 1166<br>
	 * Message: 話せる島発の定期船が約20分後にギラン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_20_MINUTES;
	
	/**
	 * ID: 1167<br>
	 * Message: 話せる島発の定期船が約15分後にギラン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_15_MINUTES;
	
	/**
	 * ID: 1168<br>
	 * Message: 話せる島発の定期船が約10分後にギラン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_10_MINUTES;
	
	/**
	 * ID: 1169<br>
	 * Message: 話せる島発の定期船が約5分後にギラン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_5_MINUTES;
	
	/**
	 * ID: 1170<br>
	 * Message: 話せる島発の定期船が約1分後にギラン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_TALKING_ARRIVE_AT_GIRAN_1_MINUTE;
	
	/**
	 * ID: 1171<br>
	 * Message: 約20分後インナドリル遊覧船が乗船場に到着します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_20_MINUTES;
	
	/**
	 * ID: 1172<br>
	 * Message: 約15分後インナドリル遊覧船が乗船場に到着します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_15_MINUTES;
	
	/**
	 * ID: 1173<br>
	 * Message: 約10分後インナドリル遊覧船が乗船場に到着します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_10_MINUTES;
	
	/**
	 * ID: 1174<br>
	 * Message: 約5分後インナドリル遊覧船が乗船場に到着します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_5_MINUTES;
	
	/**
	 * ID: 1175<br>
	 * Message: 約1分後インナドリル遊覧船が乗船場に到着します。
	 */
	public static final SystemMessageId INNADRIL_BOAT_ARRIVE_1_MINUTE;
	
	/**
	 * ID: 1176<br>
	 * Message: 競争期間です。
	 */
	public static final SystemMessageId SSQ_COMPETITION_UNDERWAY;
	
	/**
	 * ID: 1177<br>
	 * Message: 封印有効期間です。
	 */
	public static final SystemMessageId VALIDATION_PERIOD;
	
	/**
	 * ID: 1178<br>
	 * Message: この封印を所有する決死隊は、封印有効期間中、貪欲の封印によって開かれる特別なダンジョンに入場することができ、特別なダンジョンに出現するマモンの商人と取引することができ、使徒のネクロポリスでアナキム、またはリリスに会うことができます。
	 */
	public static final SystemMessageId AVARICE_DESCRIPTION;
	
	/**
	 * ID: 1179<br>
	 * Message: この封印を所有する決死隊は、封印有効期間中、啓示の封印によって開かれる特別なダンジョンに入場することができ、村にいる司祭から、ダンジョンへのテレポート サービスを受けることができ、ダンジョン内に出現するマモンの鍛冶屋に会うことができます。啓示を言い伝える者(滅亡を叫ぶ者)が村に現れ、勝者側(敗者側)に有益な(有害な)魔法を詠唱します。
	 */
	public static final SystemMessageId GNOSIS_DESCRIPTION;
	
	/**
	 * ID: 1180<br>
	 * Message: 封印有効期間中、決死隊の最大CP量および守城傭兵/城門、城壁アップ グレード費用/城門、城壁基本防御力/城の税率の限界値がこの封印を所有した決死隊側に有利なように変更されます。また、攻城戦兵器の使用も制限されます。黄昏の革命軍がこの封印を所有すると、攻城戦の際に城所有血盟だけが守城側に参加できるようになります。
	 */
	public static final SystemMessageId STRIFE_DESCRIPTION;
	
	/**
	 * ID: 1181<br>
	 * Message: 本当にタイトルを削除しますか。
	 */
	public static final SystemMessageId CHANGE_TITLE_CONFIRM;
	
	/**
	 * ID: 1182<br>
	 * Message: 本当に削除しますか。
	 */
	public static final SystemMessageId CREST_DELETE_CONFIRM;
	
	/**
	 * ID: 1183<br>
	 * Message: 初期状態です。
	 */
	public static final SystemMessageId INITIAL_PERIOD;
	
	/**
	 * ID: 1184<br>
	 * Message: サーバー内部集計期間です。
	 */
	public static final SystemMessageId RESULTS_PERIOD;
	
	/**
	 * ID: 1185<br>
	 * Message: 日後に削除されます。
	 */
	public static final SystemMessageId DAYS_LEFT_UNTIL_DELETION;
	
	/**
	 * ID: 1186<br>
	 * Message: 新規アカウントを作成するためには、リネージュII 公式サイト（http://lineage2.plaync.jp/）で会員登録をする必要があります。
	 */
	public static final SystemMessageId TO_CREATE_ACCOUNT_VISIT_WEBSITE;
	
	/**
	 * ID: 1187<br>
	 * Message: アカウントを紛失した場合、リネージュII 公式サイト（http://lineage2.plaync.jp/）のサポートからお問い合わせください。
	 */
	public static final SystemMessageId ACCOUNT_INFORMATION_FORGOTTON_VISIT_WEBSITE;
	
	/**
	 * ID: 1188<br>
	 * Message: これ以上推薦を受けられない状態です。
	 */
	public static final SystemMessageId YOUR_TARGET_NO_LONGER_RECEIVE_A_RECOMMENDATION;
	
	/**
	 * ID: 1189<br>
	 * Message: 攻城側が臨時同盟を結びました。最初の城主交替時に解除されます。
	 */
	public static final SystemMessageId TEMPORARY_ALLIANCE;
	
	/**
	 * ID: 1189<br>
	 * Message: 攻城側が臨時同盟を結びました。最初の城主交替時に解除されます。
	 */
	public static final SystemMessageId TEMPORARY_ALLIANCE_DISSOLVED;
	
	/**
	 * ID: 1191<br>
	 * Message: グルーディン港発の定期船が約10分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_10_MINUTES;
	
	/**
	 * ID: 1192<br>
	 * Message: グルーディン港発の定期船が約5分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_5_MINUTES;
	
	/**
	 * ID: 1193<br>
	 * Message: グルーディン港発の定期船が約1分後に話せる島に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_ARRIVE_AT_TALKING_1_MINUTE;
	
	/**
	 * ID: 1194<br>
	 * Message: 傭兵は封印有効期間内に、攻城戦開始前まで配置することができます。
	 */
	public static final SystemMessageId MERC_CAN_BE_ASSIGNED;
	
	/**
	 * ID: 1195<br>
	 * Message: この傭兵は戦乱の封印により配置できません。
	 */
	public static final SystemMessageId MERC_CANT_BE_ASSIGNED_USING_STRIFE;
	
	/**
	 * ID: 1196<br>
	 * Message: 気が最高段階に達したため、これ以上気を集めることはできません。
	 */
	public static final SystemMessageId FORCE_MAXIMUM;
	
	/**
	 * ID: 1197<br>
	 * Message: 召喚獣の費用で$s1$s2個が消耗されました。
	 */
	public static final SystemMessageId SUMMONING_SERVITOR_COSTS_S2_S1;
	
	/**
	 * ID: 1198<br>
	 * Message: クリスタライズしました。
	 */
	public static final SystemMessageId CRYSTALLIZATION_SUCCESSFUL;
	
	/**
	 * ID: 1199<br>
	 * Message: =======<血盟戦の相手>=======
	 */
	public static final SystemMessageId CLAN_WAR_HEADER;
	
	/**
	 * ID: 1200<br>
	 * Message: =$s1($s2同盟)
	 */
	public static final SystemMessageId S1_S2_ALLIANCE;
	
	/**
	 * ID: 1201<br>
	 * Message: 中断するクエストを選択してください。
	 */
	public static final SystemMessageId SELECT_QUEST_TO_ABOR;
	
	/**
	 * ID: 1202<br>
	 * Message: =$s1(同盟なし)
	 */
	public static final SystemMessageId S1_NO_ALLI_EXISTS;
	
	/**
	 * ID: 1203<br>
	 * Message: 戦争中ではありません。
	 */
	public static final SystemMessageId NO_WAR_IN_PROGRESS;
	
	/**
	 * ID: 1204<br>
	 * Message: スクリーンショットを保存しました。($s1$s2x$s3)
	 */
	public static final SystemMessageId SCREENSHOT;
	
	/**
	 * ID: 1205<br>
	 * Message: 受信トレイがいっぱいになりました。受信トレイは100個まで保存できます。
	 */
	public static final SystemMessageId MAILBOX_FULL;
	
	/**
	 * ID: 1206<br>
	 * Message: メモがいっぱいになりました。メモは100個まで登録できます。
	 */
	public static final SystemMessageId MEMOBOX_FULL;
	
	/**
	 * ID: 1207<br>
	 * Message: 内容を入力してください。
	 */
	public static final SystemMessageId MAKE_AN_ENTRY;
	
	/**
	 * ID: 1208<br>
	 * Message: $c1が死亡し、$s2$s3個を落としました。
	 */
	public static final SystemMessageId C1_DIED_DROPPED_S3_S2;
	
	/**
	 * ID: 1209<br>
	 * Message: 討伐成功、おめでとうございます。
	 */
	public static final SystemMessageId RAID_WAS_SUCCESSFUL;
	
	/**
	 * ID: 1210<br>
	 * Message: セブン サイン：競争期間が始まりました。黎明の司祭か黄昏の司祭を訪ねることで参加できます。
	 */
	public static final SystemMessageId QUEST_EVENT_PERIOD_BEGUN;
	
	/**
	 * ID: 1211<br>
	 * Message: セブン サイン：競争期間を終了しました。次の競争期間は1週間後に始まります。
	 */
	public static final SystemMessageId QUEST_EVENT_PERIOD_ENDED;
	
	/**
	 * ID: 1212<br>
	 * Message: セブン サイン：黎明の君主たちが貪欲の封印を獲得しました。
	 */
	public static final SystemMessageId DAWN_OBTAINED_AVARICE;
	
	/**
	 * ID: 1213<br>
	 * Message: セブン サイン：黎明の君主たちが啓示の封印を獲得しました。
	 */
	public static final SystemMessageId DAWN_OBTAINED_GNOSIS;
	
	/**
	 * ID: 1214<br>
	 * Message: セブン サイン：黎明の君主たちが戦乱の封印を獲得しました。
	 */
	public static final SystemMessageId DAWN_OBTAINED_STRIFE;
	
	/**
	 * ID: 1215<br>
	 * Message: セブン サイン：黄昏の革命軍が貪欲の封印を獲得しました。
	 */
	public static final SystemMessageId DUSK_OBTAINED_AVARICE;
	
	/**
	 * ID: 1216<br>
	 * Message: セブン サイン：黄昏の革命軍が啓示の封印を獲得しました。
	 */
	public static final SystemMessageId DUSK_OBTAINED_GNOSIS;
	
	/**
	 * ID: 1217<br>
	 * Message: セブン サイン：黄昏の革命軍が戦乱の封印を獲得しました。
	 */
	public static final SystemMessageId DUSK_OBTAINED_STRIFE;
	
	/**
	 * ID: 1218<br>
	 * Message: セブン サイン：封印有効期間が始まります。
	 */
	public static final SystemMessageId SEAL_VALIDATION_PERIOD_BEGUN;
	
	/**
	 * ID: 1219<br>
	 * Message: セブン サイン：封印有効期間が終了しました。
	 */
	public static final SystemMessageId SEAL_VALIDATION_PERIOD_ENDED;
	
	/**
	 * ID: 1220<br>
	 * Message: 本当に召喚しますか。
	 */
	public static final SystemMessageId SUMMON_CONFIRM;
	
	/**
	 * ID: 1221<br>
	 * Message: 本当に帰還させますか。
	 */
	public static final SystemMessageId RETURN_CONFIRM;
	
	/**
	 * ID: 1222<br>
	 * Message: 現在地： $s1、$s2、$s3 (サポート相談所)
	 */
	public static final SystemMessageId LOC_GM_CONSULATION_SERVICE_S1_S2_S3;
	
	/**
	 * ID: 1223<br>
	 * Message: 5分後にギラン港から話せる島へ出発します。
	 */
	public static final SystemMessageId DEPART_FOR_TALKING_5_MINUTES;
	
	/**
	 * ID: 1224<br>
	 * Message: 1分後にギラン港から話せる島へ出発します。
	 */
	public static final SystemMessageId DEPART_FOR_TALKING_1_MINUTE;
	
	/**
	 * ID: 1225<br>
	 * Message: 間もなくギラン港から話せる島へ出発します。
	 */
	public static final SystemMessageId DEPART_FOR_TALKING;
	
	/**
	 * ID: 1226<br>
	 * Message: ギラン港から話せる島へ出発します。
	 */
	public static final SystemMessageId LEAVING_FOR_TALKING;
	
	/**
	 * ID: 1227<br>
	 * Message: $s1個の未読のメールがあります。
	 */
	public static final SystemMessageId S1_UNREAD_MESSAGES;
	
	/**
	 * ID: 1228<br>
	 * Message: $c1に遮断されたため、メールが送れません。
	 */
	public static final SystemMessageId C1_BLOCKED_YOU_CANNOT_MAIL;
	
	/**
	 * ID: 1229<br>
	 * Message: メールをこれ以上送信できません。アカウント当たり、一日に10通まで送信できます。
	 */
	public static final SystemMessageId NO_MORE_MESSAGES_TODAY;
	
	/**
	 * ID: 1230<br>
	 * Message: メールの送信は最大5人まで可能です。
	 */
	public static final SystemMessageId ONLY_FIVE_RECIPIENTS;
	
	/**
	 * ID: 1231<br>
	 * Message: メールを送信しました。
	 */
	public static final SystemMessageId SENT_MAIL;
	
	/**
	 * ID: 1232<br>
	 * Message: メールの送信に失敗しました。
	 */
	public static final SystemMessageId MESSAGE_NOT_SENT;
	
	/**
	 * ID: 1233<br>
	 * Message: メールが届きました。
	 */
	public static final SystemMessageId NEW_MAIL;
	
	/**
	 * ID: 1234<br>
	 * Message: メールを下書きトレイに保存しました。
	 */
	public static final SystemMessageId MAIL_STORED_IN_MAILBOX;
	
	/**
	 * ID: 1235<br>
	 * Message: 友人をすべて削除しますか。
	 */
	public static final SystemMessageId ALL_FRIENDS_DELETE_CONFIRM;
	
	/**
	 * ID: 1236<br>
	 * Message: セキュリティ カード番号を入力してください。
	 */
	public static final SystemMessageId ENTER_SECURITY_CARD_NUMBER;
	
	/**
	 * ID: 1237<br>
	 * Message: $s1番のカード番号を入力してください。
	 */
	public static final SystemMessageId ENTER_CARD_NUMBER_FOR_S1;
	
	/**
	 * ID: 1238<br>
	 * Message: 下書きトレイがいっぱいになったため、これ以上保存できません。10個まで保存できます。
	 */
	public static final SystemMessageId TEMP_MAILBOX_FULL;
	
	/**
	 * ID: 1239<br>
	 * Message: キーセーフ モジュールのローディングに失敗しました。再度行う場合は、ゲーム終了後再実行してください。
	 */
	public static final SystemMessageId KEYBOARD_MODULE_FAILED_LOAD;
	
	/**
	 * ID: 1240<br>
	 * Message: セブン サイン：黄昏の革命軍が勝利しました。
	 */
	public static final SystemMessageId DUSK_WON;
	
	/**
	 * ID: 1241<br>
	 * Message: セブン サイン：黎明の君主たちが勝利しました。
	 */
	public static final SystemMessageId DAWN_WON;
	
	/**
	 * ID: 1242<br>
	 * Message: 18才認証を受けていないユーザー様は、22時から翌日6時までログインできません。
	 */
	public static final SystemMessageId NOT_VERIFIED_AGE_NO_LOGIN;
	
	/**
	 * ID: 1243<br>
	 * Message: このセキュリティ カード番号は有効ではありません。
	 */
	public static final SystemMessageId SECURITY_CARD_NUMBER_INVALID;
	
	/**
	 * ID: 1244<br>
	 * Message: 18才認証を受けていないユーザー様は、22時から翌日6時までの間、接続ができないためゲームが終了されます。
	 */
	public static final SystemMessageId NOT_VERIFIED_AGE_LOG_OFF;
	
	/**
	 * ID: 1245<br>
	 * Message: 接続終了$s1分前です。
	 */
	public static final SystemMessageId LOGOUT_IN_S1_MINUTES;
	
	/**
	 * ID: 1246<br>
	 * Message: $c1が死亡し、$s2アデナを落としました。
	 */
	public static final SystemMessageId C1_DIED_DROPPED_S2_ADENA;
	
	/**
	 * ID: 1247<br>
	 * Message: 制限時間が過ぎたため、死体に該当のスキルを使用できません。
	 */
	public static final SystemMessageId CORPSE_TOO_OLD_SKILL_NOT_USED;
	
	/**
	 * ID: 1248<br>
	 * Message: 空腹になり、搭乗状態が解除されました。
	 */
	public static final SystemMessageId OUT_OF_FEED_MOUNT_CANCELED;
	
	/**
	 * ID: 1249<br>
	 * Message: ワイバーンに乗るためには、ストライダーに乗った状態でなければなりません。
	 */
	public static final SystemMessageId YOU_MAY_ONLY_RIDE_WYVERN_WHILE_RIDING_STRIDER;
	
	/**
	 * ID: 1250<br>
	 * Message: 本当に降伏しますか。同盟戦の降伏時、キャラクター1回の死亡に値する経験値ペナルティがあります。
	 */
	public static final SystemMessageId SURRENDER_ALLY_WAR_CONFIRM;
	
	/**
	 * ID: 1251<br>
	 * Message: 本当に追放しますか。同盟追放を行なった場合、1日の間、他の血盟を同盟に加入させることができません。
	 */
	public static final SystemMessageId DISMISS_ALLY_CONFIRM;
	
	/**
	 * ID: 1252<br>
	 * Message: 本当に降伏しますか。1回の死亡に値する経験値ペナルティがあります。
	 */
	public static final SystemMessageId SURRENDER_CONFIRM1;
	
	/**
	 * ID: 1253<br>
	 * Message: 本当に個人降伏しますか。1回の死亡に値する経験値ペナルティがあり、血盟戦に参加できなくなります。
	 */
	public static final SystemMessageId SURRENDER_CONFIRM2;
	
	/**
	 * ID: 1254<br>
	 * Message: 評価に応じて頂き、ありがとうございました。
	 */
	public static final SystemMessageId THANKS_FOR_FEEDBACK;
	
	/**
	 * ID: 1255<br>
	 * Message: サポートが開始されました。
	 */
	public static final SystemMessageId GM_CONSULTATION_BEGUN;
	
	/**
	 * ID: 1256<br>
	 * Message: コマンドの後ろに名前を書いてください。
	 */
	public static final SystemMessageId PLEASE_WRITE_NAME_AFTER_COMMAND;
	
	/**
	 * ID: 1257<br>
	 * Message: ペットと召喚獣の特殊スキルはマクロに登録できません。
	 */
	public static final SystemMessageId PET_SKILL_NOT_AS_MACRO;
	
	/**
	 * ID: 1258<br>
	 * Message: $s1をクリスタライズしました。
	 */
	public static final SystemMessageId S1_CRYSTALLIZED;
	
	/**
	 * ID: 1259<br>
	 * Message: =======<同盟戦の相手>=======
	 */
	public static final SystemMessageId ALLIANCE_TARGET_HEADER;
	
	/**
	 * ID: 1260<br>
	 * Message: セブン サイン：次の競争の準備中です。
	 */
	public static final SystemMessageId PREPARATIONS_PERIOD_BEGUN;
	
	/**
	 * ID: 1261<br>
	 * Message: セブン サイン：競争期間中です。黎明の司祭か黄昏の司祭を訪ねることで参加できます。
	 */
	public static final SystemMessageId COMPETITION_PERIOD_BEGUN;
	
	/**
	 * ID: 1262<br>
	 * Message: セブン サイン：競争期間が終了し、結果を集計しています。
	 */
	public static final SystemMessageId RESULTS_PERIOD_BEGUN;
	
	/**
	 * ID: 1263<br>
	 * Message: セブン サイン：封印有効期間中です。競争期間は次の月曜日に開始されます。
	 */
	public static final SystemMessageId VALIDATION_PERIOD_BEGUN;
	
	/**
	 * ID: 1264<br>
	 * Message: 魂の吸収に失敗しました。現在のソウル ストーンではこの魂は吸収できません。
	 */
	public static final SystemMessageId STONE_CANNOT_ABSORB;
	
	/**
	 * ID: 1265<br>
	 * Message: ソウル ストーンを持っていないので魂を吸収できません。
	 */
	public static final SystemMessageId CANT_ABSORB_WITHOUT_STONE;
	
	/**
	 * ID: 1266<br>
	 * Message: トレードが終了しました。
	 */
	public static final SystemMessageId EXCHANGE_HAS_ENDED;
	
	/**
	 * ID: 1267<br>
	 * Message: 貢献度が$s1上がりました。
	 */
	public static final SystemMessageId CONTRIB_SCORE_INCREASED_S1;
	
	/**
	 * ID: 1268<br>
	 * Message: $s1クラスをサブ クラスに追加しますか。
	 */
	public static final SystemMessageId ADD_SUBCLASS_CONFIRM;
	
	/**
	 * ID: 1269<br>
	 * Message: 新しいサブ クラスを追加しました。
	 */
	public static final SystemMessageId ADD_NEW_SUBCLASS;
	
	/**
	 * ID: 1270<br>
	 * Message: サブ クラス間の変更が完了しました。
	 */
	public static final SystemMessageId SUBCLASS_TRANSFER_COMPLETED;
	
	/**
	 * ID: 1271<br>
	 * Message: 本当に参加しますか。次の封印有効期間まで黎明の君主所属になります。
	 */
	public static final SystemMessageId DAWN_CONFIRM;
	
	/**
	 * ID: 1271<br>
	 * Message: 本当に参加しますか。次の封印有効期間まで黎明の君主所属になります。
	 */
	public static final SystemMessageId DUSK_CONFIRM;
	
	/**
	 * ID: 1273<br>
	 * Message: 黎明の君主所属でセブン サインに参加しました。
	 */
	public static final SystemMessageId SEVENSIGNS_PARTECIPATION_DAWN;
	
	/**
	 * ID: 1274<br>
	 * Message: 黄昏の革命軍所属でセブン サインに参加しました。
	 */
	public static final SystemMessageId SEVENSIGNS_PARTECIPATION_DUSK;
	
	/**
	 * ID: 1275<br>
	 * Message: 今回の競争期間は、貪欲の封印のために戦うを選択しました。
	 */
	public static final SystemMessageId FIGHT_FOR_AVARICE;
	
	/**
	 * ID: 1276<br>
	 * Message: 今回の競争期間は、啓示の封印のために戦うを選択しました。
	 */
	public static final SystemMessageId FIGHT_FOR_GNOSIS;
	
	/**
	 * ID: 1277<br>
	 * Message: 今回の競争期間は、戦乱の封印のために戦うを選択しました。
	 */
	public static final SystemMessageId FIGHT_FOR_STRIFE;
	
	/**
	 * ID: 1278<br>
	 * Message: NPCサーバーが作動中止状態です。
	 */
	public static final SystemMessageId NPC_SERVER_NOT_OPERATING;
	
	/**
	 * ID: 1279<br>
	 * Message: 貢献度の限度を超過し、続行できません。
	 */
	public static final SystemMessageId CONTRIB_SCORE_EXCEEDED;
	
	/**
	 * ID: 1280<br>
	 * Message: 魔法のクリティカル ヒット！
	 */
	public static final SystemMessageId CRITICAL_HIT_MAGIC;
	
	/**
	 * ID: 1281<br>
	 * Message: シールドによる卓越した防御に成功しました。
	 */
	public static final SystemMessageId YOUR_EXCELLENT_SHIELD_DEFENSE_WAS_A_SUCCESS;
	
	/**
	 * ID: 1282<br>
	 * Message: 性向値が$s1に変更されました。
	 */
	public static final SystemMessageId YOUR_KARMA_HAS_BEEN_CHANGED_TO_S1;
	
	/**
	 * ID: 1283<br>
	 * Message: 最小フレーム機能で作動させます。
	 */
	public static final SystemMessageId MINIMUM_FRAME_ACTIVATED;
	
	/**
	 * ID: 1284<br>
	 * Message: 最小フレーム機能が解除されました。
	 */
	public static final SystemMessageId MINIMUM_FRAME_DEACTIVATED;
	
	/**
	 * ID: 1285<br>
	 * Message: 在庫がないため、購入できません。
	 */
	public static final SystemMessageId NO_INVENTORY_CANNOT_PURCHASE;
	
	/**
	 * ID: 1286<br>
	 * Message: (次の月曜日午後6時まで)
	 */
	public static final SystemMessageId UNTIL_MONDAY_6PM;
	
	/**
	 * ID: 1287<br>
	 * Message: (本日午後6時まで)
	 */
	public static final SystemMessageId UNTIL_TODAY_6PM;
	
	/**
	 * ID: 1288<br>
	 * Message: 現在の状態で競争期間が終了すると仮定した時、$s1が勝利し、封印の所有権は以下のようになります。
	 */
	public static final SystemMessageId S1_WILL_WIN_COMPETITION;
	
	/**
	 * ID: 1289<br>
	 * Message: 以前の周期で封印を所有していて、10％以上の人たちが投票したので
	 */
	public static final SystemMessageId SEAL_OWNED_10_MORE_VOTED;
	
	/**
	 * ID: 1290<br>
	 * Message: 以前の周期で封印を所有できなかったが、35％以上の人たちが投票したので
	 */
	public static final SystemMessageId SEAL_NOT_OWNED_35_MORE_VOTED;
	
	/**
	 * ID: 1291<br>
	 * Message: 以前の周期で封印を所有していたが、10％未満の人たちが投票したので
	 */
	public static final SystemMessageId SEAL_OWNED_10_LESS_VOTED;
	
	/**
	 * ID: 1292<br>
	 * Message: 以前の周期で封印を所有できず、35％未満の人たちが投票したので
	 */
	public static final SystemMessageId SEAL_NOT_OWNED_35_LESS_VOTED;
	
	/**
	 * ID: 1293<br>
	 * Message: 現在の状態で競争期間が終了した場合、引き分けになります。
	 */
	public static final SystemMessageId COMPETITION_WILL_TIE;
	
	/**
	 * ID: 1294<br>
	 * Message: 競争が引き分けの場合、封印も未所有になります。
	 */
	public static final SystemMessageId COMPETITION_TIE_SEAL_NOT_AWARDED;
	
	/**
	 * ID: 1295<br>
	 * Message: スキル使用中はサブ クラスを作ることや、変更することはできません。
	 */
	public static final SystemMessageId SUBCLASS_NO_CHANGE_OR_CREATE_WHILE_SKILL_IN_USE;
	
	/**
	 * ID: 1296<br>
	 * Message: 個人商店を開くことができないエリアです。
	 */
	public static final SystemMessageId NO_PRIVATE_STORE_HERE;
	
	/**
	 * ID: 1297<br>
	 * Message: 個人工房を開くことができないエリアです。
	 */
	public static final SystemMessageId NO_PRIVATE_WORKSHOP_HERE;
	
	/**
	 * ID: 1298<br>
	 * Message: モンスター レース場から出ますか。
	 */
	public static final SystemMessageId MONS_EXIT_CONFIRM;
	
	/**
	 * ID: 1299<br>
	 * Message: $c1が妨害を受け、詠唱が中断されました。
	 */
	public static final SystemMessageId C1_CASTING_INTERRUPTED;
	
	/**
	 * ID: 1300<br>
	 * Message: 試着がキャンセルされました。
	 */
	public static final SystemMessageId WEAR_ITEMS_STOPPED;
	
	/**
	 * ID: 1301<br>
	 * Message: 黎明の君主たちに加入しないと利用できません。
	 */
	public static final SystemMessageId CAN_BE_USED_BY_DAWN;
	
	/**
	 * ID: 1302<br>
	 * Message: 黄昏の革命軍に加入しないと利用できません。
	 */
	public static final SystemMessageId CAN_BE_USED_BY_DUSK;
	
	/**
	 * ID: 1303<br>
	 * Message: 競争期間だけ利用することができます。
	 */
	public static final SystemMessageId CAN_BE_USED_DURING_QUEST_EVENT_PERIOD;
	
	/**
	 * ID: 1304<br>
	 * Message: 戦乱の封印の影響で、血盟の守備登録がすべてキャンセルされました。
	 */
	public static final SystemMessageId STRIFE_CANCELED_DEFENSIVE_REGISTRATION;
	
	/**
	 * ID: 1305<br>
	 * Message: 競争期間のみ、封印石を預けることができます。
	 */
	public static final SystemMessageId SEAL_STONES_ONLY_WHILE_QUEST;
	
	/**
	 * ID: 1306<br>
	 * Message: 試着が終了しました。
	 */
	public static final SystemMessageId NO_LONGER_TRYING_ON;
	
	/**
	 * ID: 1307<br>
	 * Message: 封印有効期間にしか精算できません。
	 */
	public static final SystemMessageId SETTLE_ACCOUNT_ONLY_IN_SEAL_VALIDATION;
	
	/**
	 * ID: 1308<br>
	 * Message: おめでとうございます！！新しいクラスに転職しました。
	 */
	public static final SystemMessageId CLASS_TRANSFER;
	
	/**
	 * ID: 1309<br>
	 * Message: この機能は、最新バージョンの MSN Messenger で使用できます。
	 */
	public static final SystemMessageId LATEST_MSN_REQUIRED;
	
	/**
	 * ID: 1310<br>
	 * Message: リネージュII で MSN Messengerの機能を使用するには、最新バージョンの MSN Messenger をインストールする必要があります。
	 */
	public static final SystemMessageId LATEST_MSN_RECOMMENDED;
	
	/**
	 * ID: 1311<br>
	 * Message: 古いバージョンの MSN Messenger ではインスタント メッセージ機能だけが使用でき、メンバの追加・削除およびオプション機能は使用できません。
	 */
	public static final SystemMessageId MSN_ONLY_BASIC;
	
	/**
	 * ID: 1312<br>
	 * Message: 最新バージョンの MSN Messenger を入手するには、MSN Messenger のサイト(http://messenger.live.jp/download)に移動してください。
	 */
	public static final SystemMessageId MSN_OBTAINED_FROM;
	
	/**
	 * ID: 1313<br>
	 * Message: $s2 は、エヌ・シー・ジャパン(株)が提供するサービスに接続しているため、$s1 とのメッセージ履歴は、サービスの運営のために保存されます。ご同意頂けない場合は、この会話ウィンドウを閉じてください。詳しくは弊社ホームページまでお問い合わせください。
	 */
	public static final SystemMessageId S1_CHAT_HISTORIES_STORED;
	
	/**
	 * ID: 1314<br>
	 * Message: 追加するメンバの電子メール アドレスを入力してください。
	 */
	public static final SystemMessageId ENTER_PASSPORT_FOR_ADDING;
	
	/**
	 * ID: 1315<br>
	 * Message: この人を削除すると、メンバ リストへの登録が解除されます。ただし、この人を禁止しない限り、この人はあなたのオンライン状態を確認したり、メッセージを送信することができます。
	 */
	public static final SystemMessageId DELETING_A_CONTACT;
	
	/**
	 * ID: 1316<br>
	 * Message: この人を禁止する
	 */
	public static final SystemMessageId CONTACT_WILL_DELETED;
	
	/**
	 * ID: 1317<br>
	 * Message: このメンバの登録を解除しますか?
	 */
	public static final SystemMessageId CONTACT_DELETE_CONFIRM;
	
	/**
	 * ID: 1318<br>
	 * Message: 禁止または禁止を解除するメンバを選択してください。
	 */
	public static final SystemMessageId SELECT_CONTACT_FOR_BLOCK_UNBLOCK;
	
	/**
	 * ID: 1319<br>
	 * Message: グループを変更するメンバを選択してください。
	 */
	public static final SystemMessageId SELECT_CONTACT_FOR_CHANGE_GROUP;
	
	/**
	 * ID: 1320<br>
	 * Message: 移動先のグループを選択して、OK をクリックしてください。
	 */
	public static final SystemMessageId SELECT_GROUP_PRESS_OK;
	
	/**
	 * ID: 1321<br>
	 * Message: 追加するグループ名を入力してください。
	 */
	public static final SystemMessageId ENTER_GROUP_NAME;
	
	/**
	 * ID: 1322<br>
	 * Message: 変更するグループを選択して、新しいグループ名を入力してください。
	 */
	public static final SystemMessageId SELECT_GROUP_ENTER_NAME;
	
	/**
	 * ID: 1323<br>
	 * Message: 削除するグループを選択して、OK をクリックしてください。
	 */
	public static final SystemMessageId SELECT_GROUP_TO_DELETE;
	
	/**
	 * ID: 1324<br>
	 * Message: サインイン中...
	 */
	public static final SystemMessageId SIGNING_IN;
	
	/**
	 * ID: 1325<br>
	 * Message: 他の場所で Messenger にサインインしたため、.NET Messenger Service からサインアウトされました。
	 */
	public static final SystemMessageId ANOTHER_COMPUTER_LOGOUT;
	
	/**
	 * ID: 1326<br>
	 * Message: $s1 の発言:
	 */
	public static final SystemMessageId S1_D;
	
	/**
	 * ID: 1327<br>
	 * Message: 次のメッセージを送ることができません。
	 */
	public static final SystemMessageId MESSAGE_NOT_DELIVERED;
	
	/**
	 * ID: 1328<br>
	 * Message: 黄昏の革命軍所属なので、復活できません。
	 */
	public static final SystemMessageId DUSK_NOT_RESURRECTED;
	
	/**
	 * ID: 1329<br>
	 * Message: 個人商店および個人工房は禁止状態のため、開設ができません。
	 */
	public static final SystemMessageId BLOCKED_FROM_USING_STORE;
	
	/**
	 * ID: 1330<br>
	 * Message: $s1分間個人商店および個人工房の開設が禁止されました。
	 */
	public static final SystemMessageId NO_STORE_FOR_S1_MINUTES;
	
	/**
	 * ID: 1331<br>
	 * Message: 個人商店および個人工房の開設禁止が解除されました。
	 */
	public static final SystemMessageId NO_LONGER_BLOCKED_USING_STORE;
	
	/**
	 * ID: 1332<br>
	 * Message: 死んでいる状態でアイテムは使えません。
	 */
	public static final SystemMessageId NO_ITEMS_AFTER_DEATH;
	
	/**
	 * ID: 1333<br>
	 * Message: リプレイ ファイルを読み取れません。Replay.iniファイルを確認してください。
	 */
	public static final SystemMessageId REPLAY_INACCESSIBLE;
	
	/**
	 * ID: 1334<br>
	 * Message: 新しいカメラ情報を保存しました。
	 */
	public static final SystemMessageId NEW_CAMERA_STORED;
	
	/**
	 * ID: 1335<br>
	 * Message: 新しいカメラ情報の保存に失敗しました。
	 */
	public static final SystemMessageId CAMERA_STORING_FAILED;
	
	/**
	 * ID: 1336<br>
	 * Message: リプレイ ファイルが損傷しました。$s1.$s2 ファイルを確認してください。
	 */
	public static final SystemMessageId REPLAY_S1_S2_CORRUPTED;
	
	/**
	 * ID: 1337<br>
	 * Message: リプレイを終了します。よろしいですか。
	 */
	public static final SystemMessageId REPLAY_TERMINATE_CONFIRM;
	
	/**
	 * ID: 1338<br>
	 * Message: 一度に移すことのできる量を超過しました。
	 */
	public static final SystemMessageId EXCEEDED_MAXIMUM_AMOUNT;
	
	/**
	 * ID: 1339<br>
	 * Message: マクロに割り当てられたショートカット キーを別のマクロで使用することはできません。
	 */
	public static final SystemMessageId MACRO_SHORTCUT_NOT_RUN;
	
	/**
	 * ID: 1340<br>
	 * Message: 現在使用しているクーポンでは接続できないサーバーです。
	 */
	public static final SystemMessageId SERVER_NOT_ACCESSED_BY_COUPON;
	
	/**
	 * ID: 1341<br>
	 * Message: 入力された電子メール アドレスは無効です。
	 */
	public static final SystemMessageId INCORRECT_NAME_OR_ADDRESS;
	
	/**
	 * ID: 1342<br>
	 * Message: すでにサインインしています。
	 */
	public static final SystemMessageId ALREADY_LOGGED_IN;
	
	/**
	 * ID: 1343<br>
	 * Message: 入力されたサインイン名が存在しないか、パスワードが正しくないため、サインインできませんでした。
	 */
	public static final SystemMessageId INCORRECT_ADDRESS_OR_PASSWORD;
	
	/**
	 * ID: 1344<br>
	 * Message: サービスが見つからないため、NET Messenger Service にサインインできませんでした。インターネットに接続していることを確認してください。
	 */
	public static final SystemMessageId NET_LOGIN_FAILED;
	
	/**
	 * ID: 1345<br>
	 * Message: この会話に招待する人を選択して、OK をクリックします。
	 */
	public static final SystemMessageId SELECT_CONTACT_CLICK_OK;
	
	/**
	 * ID: 1346<br>
	 * Message: がメッセージを入力しています。
	 */
	public static final SystemMessageId CURRENTLY_ENTERING_CHAT;
	
	/**
	 * ID: 1347<br>
	 * Message: Lineage II Messenger は 要求を完了できませんでした。
	 */
	public static final SystemMessageId MESSENGER_FAILED_CARRYING_OUT_TASK;
	
	/**
	 * ID: 1348<br>
	 * Message: $s1 が会話に参加しました。
	 */
	public static final SystemMessageId S1_ENTERED_CHAT_ROOM;
	
	/**
	 * ID: 1349<br>
	 * Message: $s1 が会話から退席しました。
	 */
	public static final SystemMessageId S1_LEFT_CHAT_ROOM;
	
	/**
	 * ID: 1350<br>
	 * Message: 状態をオフラインに変更します。すべての会話ウィンドウが終了します。
	 */
	public static final SystemMessageId GOING_OFFLINE;
	
	/**
	 * ID: 1351<br>
	 * Message: 削除するメンバを選択して、削除 をクリックしてください。
	 */
	public static final SystemMessageId SELECT_CONTACT_CLICK_REMOVE;
	
	/**
	 * ID: 1352<br>
	 * Message: $s1 ($s2) のメンバ リストに追加されました。
	 */
	public static final SystemMessageId ADDED_TO_S1_S2_CONTACT_LIST;
	
	/**
	 * ID: 1353<br>
	 * Message: いつでも自分をオフライン状態にすることができます。
	 */
	public static final SystemMessageId CAN_SET_OPTION_TO_ALWAYS_SHOW_OFFLINE;
	
	/**
	 * ID: 1354<br>
	 * Message: チャット禁止中のため、会話できません。
	 */
	public static final SystemMessageId NO_CHAT_WHILE_BLOCKED;
	
	/**
	 * ID: 1355<br>
	 * Message: 会話の相手は現在チャット禁止の状態です。
	 */
	public static final SystemMessageId CONTACT_CURRENTLY_BLOCKED;
	
	/**
	 * ID: 1356<br>
	 * Message: 会話の相手は現在接続していません。
	 */
	public static final SystemMessageId CONTACT_CURRENTLY_OFFLINE;
	
	/**
	 * ID: 1357<br>
	 * Message: 会話の相手によって禁止されている状態です。
	 */
	public static final SystemMessageId YOU_ARE_BLOCKED;
	
	/**
	 * ID: 1358<br>
	 * Message: サインアウト中...
	 */
	public static final SystemMessageId YOU_ARE_LOGGING_OUT;
	
	/**
	 * ID: 1359<br>
	 * Message: $s1 がサインインしました。
	 */
	public static final SystemMessageId S1_LOGGED_IN2;
	
	/**
	 * ID: 1360<br>
	 * Message: $s1 からの新着メッセージが届きました。
	 */
	public static final SystemMessageId GOT_MESSAGE_FROM_S1;
	
	/**
	 * ID: 1361<br>
	 * Message: システム トラブルによって .NET Messenger Service からサインアウトされました。
	 */
	public static final SystemMessageId LOGGED_OUT_DUE_TO_ERROR;
	
	/**
	 * ID: 1362<br>
	 * Message: 削除するメンバを選択してください。グループを削除するには、オンライン状態の横にある三角形をクリックし、オプション をクリックします。
	 */
	public static final SystemMessageId SELECT_CONTACT_TO_DELETE;
	
	/**
	 * ID: 1363<br>
	 * Message: 同盟戦の申し込みが拒否されました。
	 */
	public static final SystemMessageId YOUR_REQUEST_ALLIANCE_WAR_DENIED;
	
	/**
	 * ID: 1364<br>
	 * Message: 同盟戦の申し込みを拒否しました。
	 */
	public static final SystemMessageId REQUEST_ALLIANCE_WAR_REJECTED;
	
	/**
	 * ID: 1365<br>
	 * Message: $s1血盟の$s2が個人降伏しました。
	 */
	public static final SystemMessageId S2_OF_S1_SURRENDERED_AS_INDIVIDUAL;
	
	/**
	 * ID: 1366<br>
	 * Message: 削除できるのは、空のグループのみです。このグループのすべてのメンバを他のグループに移動してください。
	 */
	public static final SystemMessageId DELTE_GROUP_INSTRUCTION;
	
	/**
	 * ID: 1367<br>
	 * Message: 関連決死隊でないので、記録を追加できません。
	 */
	public static final SystemMessageId ONLY_GROUP_CAN_ADD_RECORDS;
	
	/**
	 * ID: 1368<br>
	 * Message: 一緒に着用できるアイテムではありません。
	 */
	public static final SystemMessageId YOU_CAN_NOT_TRY_THOSE_ITEMS_ON_AT_THE_SAME_TIME;
	
	/**
	 * ID: 1369<br>
	 * Message: 設定できる最高金額を超過しました。
	 */
	public static final SystemMessageId EXCEEDED_THE_MAXIMUM;
	
	/**
	 * ID: 1370<br>
	 * Message: $c1 は運営者なのでメールを送ることができません。
	 */
	public static final SystemMessageId CANNOT_MAIL_GM_C1;
	
	/**
	 * ID: 1371<br>
	 * Message: 貴方は正当にプレイしていないと判断され、$s1分間動けなくなる措置がとられました。
	 */
	public static final SystemMessageId GAMEPLAY_RESTRICTION_PENALTY_S1;
	
	/**
	 * ID: 1372<br>
	 * Message: 現在動けない状態です。動けるようになるまでの残り時間はあと$s1分です。
	 */
	public static final SystemMessageId PUNISHMENT_CONTINUE_S1_MINUTES;
	
	/**
	 * ID: 1373<br>
	 * Message: $c1がレイド ボスが落とした$s2を手に入れました。
	 */
	public static final SystemMessageId C1_PICKED_UP_S2_FROM_RAIDBOSS;
	
	/**
	 * ID: 1374<br>
	 * Message: $c1がレイド ボスが落とした$s2 $s3個を手に入れました。
	 */
	public static final SystemMessageId C1_PICKED_UP_S3_S2_S_FROM_RAIDBOSS;
	
	/**
	 * ID: 1375<br>
	 * Message: $c1がレイド ボスが落とした$s2アデナを手に入れました。
	 */
	public static final SystemMessageId C1_PICKED_UP_S2_ADENA_FROM_RAIDBOSS;
	
	/**
	 * ID: 1376<br>
	 * Message: $c1が他の人が落とした$s2を手に入れました。
	 */
	public static final SystemMessageId C1_PICKED_UP_S2_FROM_ANOTHER_CHARACTER;
	
	/**
	 * ID: 1377<br>
	 * Message: $c1が他の人が落とした$s2 $s3個を手に入れました。
	 */
	public static final SystemMessageId C1_PICKED_UP_S3_S2_S_FROM_ANOTHER_CHARACTER;
	
	/**
	 * ID: 1378<br>
	 * Message: $c1が他の人が落とした+$s3$s2を手に入れました。
	 */
	public static final SystemMessageId C1_PICKED_UP_S3_S2_FROM_ANOTHER_CHARACTER;
	
	/**
	 * ID: 1379<br>
	 * Message: $c1が$s2アデナを手に入れました。
	 */
	public static final SystemMessageId C1_OBTAINED_S2_ADENA;
	
	/**
	 * ID: 1380<br>
	 * Message: 戦場では$s1を召喚できません。
	 */
	public static final SystemMessageId CANT_SUMMON_S1_ON_BATTLEGROUND;
	
	/**
	 * ID: 1381<br>
	 * Message: パーティ リーダーが$s1を$s2個獲得しました。
	 */
	public static final SystemMessageId LEADER_OBTAINED_S2_OF_S1;
	
	/**
	 * ID: 1382<br>
	 * Message: 本当にこの武器を選択されますか。 クエストを完遂するためには、選択した武器を必ず持って来なければなりません。
	 */
	public static final SystemMessageId CHOOSE_WEAPON_CONFIRM;
	
	/**
	 * ID: 1383<br>
	 * Message: 本当に変えられますか。
	 */
	public static final SystemMessageId EXCHANGE_CONFIRM;
	
	/**
	 * ID: 1384<br>
	 * Message: $c1がパーティ リーダーになりました。
	 */
	public static final SystemMessageId C1_HAS_BECOME_A_PARTY_LEADER;
	
	/**
	 * ID: 1385<br>
	 * Message: 降りられないエリアです。
	 */
	public static final SystemMessageId NO_DISMOUNT_HERE;
	
	/**
	 * ID: 1386<br>
	 * Message: 停止状態が解除されました。
	 */
	public static final SystemMessageId NO_LONGER_HELD_IN_PLACE;
	
	/**
	 * ID: 1387<br>
	 * Message: 着用するアイテムを選択してください。
	 */
	public static final SystemMessageId SELECT_ITEM_TO_TRY_ON;
	
	/**
	 * ID: 1388<br>
	 * Message: パーティ ルームが生成されました。
	 */
	public static final SystemMessageId PARTY_ROOM_CREATED;
	
	/**
	 * ID: 1389<br>
	 * Message: パーティ ルームの情報が修正されました。
	 */
	public static final SystemMessageId PARTY_ROOM_REVISED;
	
	/**
	 * ID: 1390<br>
	 * Message: パーティ ルームに入場できません。
	 */
	public static final SystemMessageId PARTY_ROOM_FORBIDDEN;
	
	/**
	 * ID: 1391<br>
	 * Message: パーティ ルームから退場しました。
	 */
	public static final SystemMessageId PARTY_ROOM_EXITED;
	
	/**
	 * ID: 1392<br>
	 * Message: $c1がパーティ ルームから退場しました。
	 */
	public static final SystemMessageId C1_LEFT_PARTY_ROOM;
	
	/**
	 * ID: 1393<br>
	 * Message: パーティ ルームから追放されました。
	 */
	public static final SystemMessageId OUSTED_FROM_PARTY_ROOM;
	
	/**
	 * ID: 1394<br>
	 * Message: $c1がパーティ ルームから追放されました。
	 */
	public static final SystemMessageId C1_KICKED_FROM_PARTY_ROOM;
	
	/**
	 * ID: 1395<br>
	 * Message: パーティ ルームが終了しました。
	 */
	public static final SystemMessageId PARTY_ROOM_DISBANDED;
	
	/**
	 * ID: 1396<br>
	 * Message: パーティに加入していないか、パーティ リーダーでないと、パーティ ルームのリストを見ることはできません。
	 */
	public static final SystemMessageId CANT_VIEW_PARTY_ROOMS;
	
	/**
	 * ID: 1397<br>
	 * Message: パーティ ルームのルーム リーダーが変更されました。
	 */
	public static final SystemMessageId PARTY_ROOM_LEADER_CHANGED;
	
	/**
	 * ID: 1398<br>
	 * Message: パーティ メンバーを募集します。
	 */
	public static final SystemMessageId RECRUITING_PARTY_MEMBERS;
	
	/**
	 * ID: 1399<br>
	 * Message: パーティ リーダーのみ権限を委譲することができます。
	 */
	public static final SystemMessageId ONLY_A_PARTY_LEADER_CAN_TRANSFER_ONES_RIGHTS_TO_ANOTHER_PLAYER;
	
	/**
	 * ID: 1400<br>
	 * Message: パーティ リーダーの権限を委譲する対象を選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_THE_PERSON_TO_WHOM_YOU_WOULD_LIKE_TO_TRANSFER_THE_RIGHTS_OF_A_PARTY_LEADER;
	
	/**
	 * ID: 1401<br>
	 * Message: 自分自身に権限を委譲することはできません。
	 */
	public static final SystemMessageId YOU_CANNOT_TRANSFER_RIGHTS_TO_YOURSELF;
	
	/**
	 * ID: 1402<br>
	 * Message: パーティ メンバーにのみ権限の委譲が可能です。
	 */
	public static final SystemMessageId YOU_CAN_TRANSFER_RIGHTS_ONLY_TO_ANOTHER_PARTY_MEMBER;
	
	/**
	 * ID: 1403<br>
	 * Message: パーティ リーダーの権限委譲に失敗しました。
	 */
	public static final SystemMessageId YOU_HAVE_FAILED_TO_TRANSFER_THE_PARTY_LEADER_RIGHTS;
	
	/**
	 * ID: 1404<br>
	 * Message: 個人工房の主人が製作価格を変更しました。変更された価格をもう一度確認して御利用ください。
	 */
	public static final SystemMessageId MANUFACTURE_PRICE_HAS_CHANGED;
	
	/**
	 * ID: 1405<br>
	 * Message: $s1のCPが回復されます。
	 */
	public static final SystemMessageId S1_CP_WILL_BE_RESTORED;
	
	/**
	 * ID: 1406<br>
	 * Message: $c1によって$s2のCPが回復されます。
	 */
	public static final SystemMessageId S2_CP_WILL_BE_RESTORED_BY_C1;
	
	/**
	 * ID: 1407<br>
	 * Message: 2つのアカウントで同時にアクセスできないPCを使用されています。
	 */
	public static final SystemMessageId NO_LOGIN_WITH_TWO_ACCOUNTS;
	
	/**
	 * ID: 1408<br>
	 * Message: 課金時間の残りは$s1時間 $s2分です。予約済みの決済は$s3件です。
	 */
	public static final SystemMessageId PREPAID_LEFT_S1_S2_S3;
	
	/**
	 * ID: 1409<br>
	 * Message: 課金時間が満了したので予約済みの決済が有効になります。残り時間は$s1時間 $s2分です。
	 */
	public static final SystemMessageId PREPAID_EXPIRED_S1_S2;
	
	/**
	 * ID: 1410<br>
	 * Message: 課金時間が満了しました。予約済みの決済はありません。
	 */
	public static final SystemMessageId PREPAID_EXPIRED;
	
	/**
	 * ID: 1411<br>
	 * Message: 課金決済予約件数が変更されました。
	 */
	public static final SystemMessageId PREPAID_CHANGED;
	
	/**
	 * ID: 1412<br>
	 * Message: 課金時間は残り$s1分です。
	 */
	public static final SystemMessageId PREPAID_LEFT_S1;
	
	/**
	 * ID: 1413<br>
	 * Message: 条件が合わないため、パーティ ルームに入場できません。
	 */
	public static final SystemMessageId CANT_ENTER_PARTY_ROOM;
	
	/**
	 * ID: 1414<br>
	 * Message: 長さは、横100以上、縦5000未満にしてください。
	 */
	public static final SystemMessageId WRONG_GRID_COUNT;
	
	/**
	 * ID: 1415<br>
	 * Message: コマンド ファイルが指定されていません。
	 */
	public static final SystemMessageId COMMAND_FILE_NOT_SENT;
	
	/**
	 * ID: 1416<br>
	 * Message: 第1チームのリーダーが選択されていません。
	 */
	public static final SystemMessageId TEAM_1_NO_REPRESENTATIVE;
	
	/**
	 * ID: 1417<br>
	 * Message: 第2チームのリーダーが選択されていません。
	 */
	public static final SystemMessageId TEAM_2_NO_REPRESENTATIVE;
	
	/**
	 * ID: 1418<br>
	 * Message: 第1チームの名称が指定されていません。
	 */
	public static final SystemMessageId TEAM_1_NO_NAME;
	
	/**
	 * ID: 1419<br>
	 * Message: 第2チームの名称が指定されていません。
	 */
	public static final SystemMessageId TEAM_2_NO_NAME;
	
	/**
	 * ID: 1420<br>
	 * Message: 第1チームと第2チームの名称が同じです。
	 */
	public static final SystemMessageId TEAM_NAME_IDENTICAL;
	
	/**
	 * ID: 1421<br>
	 * Message: 競技設定ファイルが指定されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE1;
	
	/**
	 * ID: 1422<br>
	 * Message: 競技設定ファイル エラー：BuffCntが明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE2;
	
	/**
	 * ID: 1423<br>
	 * Message: 競技設定ファイル エラー：BuffID$s1が明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE3;
	
	/**
	 * ID: 1424<br>
	 * Message: 競技設定ファイル エラー：BuffLv$s1が明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE4;
	
	/**
	 * ID: 1425<br>
	 * Message: 競技設定ファイル エラー：DefaultAllowが明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE5;
	
	/**
	 * ID: 1426<br>
	 * Message: 競技設定ファイル エラー：ExpSkillCntが明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE6;
	
	/**
	 * ID: 1427<br>
	 * Message: 競技設定ファイル エラー：ExpSkillID$s1が明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE7;
	
	/**
	 * ID: 1428<br>
	 * Message: 競技設定ファイル エラー：ExpItemCntが明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE8;
	
	/**
	 * ID: 1429<br>
	 * Message: 競技設定ファイル エラー：ExpItemID$s1が明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE9;
	
	/**
	 * ID: 1430<br>
	 * Message: 競技設定ファイル エラー：TeleportDelayが明示されていません。
	 */
	public static final SystemMessageId RACE_SETUP_FILE10;
	
	/**
	 * ID: 1431<br>
	 * Message: 競技を一時中断します。
	 */
	public static final SystemMessageId RACE_STOPPED_TEMPORARILY;
	
	/**
	 * ID: 1432<br>
	 * Message: 相手が石化状態です。
	 */
	public static final SystemMessageId OPPONENT_PETRIFIED;
	
	/**
	 * ID: 1433<br>
	 * Message: $s1 の使用を自動にします。
	 */
	public static final SystemMessageId USE_OF_S1_WILL_BE_AUTO;
	
	/**
	 * ID: 1434<br>
	 * Message: $s1 の自動使用を解除します。
	 */
	public static final SystemMessageId AUTO_USE_OF_S1_CANCELLED;
	
	/**
	 * ID: 1435<br>
	 * Message: $s1が不足しているので自動使用が解除されました。
	 */
	public static final SystemMessageId AUTO_USE_CANCELLED_LACK_OF_S1;
	
	/**
	 * ID: 1436<br>
	 * Message: $s1が不足しているので自動使用ができません。
	 */
	public static final SystemMessageId CANNOT_AUTO_USE_LACK_OF_S1;
	
	/**
	 * ID: 1437<br>
	 * Message: サイコロが使えなくなりました。村にある一般の商店での購入もできません。ただ、村にある一般の商店での販売はできます。
	 */
	public static final SystemMessageId DICE_NO_LONGER_ALLOWED;
	
	/**
	 * ID: 1438<br>
	 * Message: エンチャント可能なスキルがありません。
	 */
	public static final SystemMessageId THERE_IS_NO_SKILL_THAT_ENABLES_ENCHANT;
	
	/**
	 * ID: 1439<br>
	 * Message: スキルのエンチャントに必要なアイテムが不足しています。
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ALL_OF_THE_ITEMS_NEEDED_TO_ENCHANT_THAT_SKILL;
	
	/**
	 * ID: 1440<br>
	 * Message: スキルのエンチャントに成功しました。スキルのエンチャントが$s1強化されました。
	 */
	public static final SystemMessageId YOU_HAVE_SUCCEEDED_IN_ENCHANTING_THE_SKILL_S1;
	
	/**
	 * ID: 1441<br>
	 * Message: スキルのエンチャントに失敗しました。スキルのエンチャントが初期化されました。
	 */
	public static final SystemMessageId YOU_HAVE_FAILED_TO_ENCHANT_THE_SKILL_S1;
	
	/**
	 * ID: 1443<br>
	 * Message: スキルのエンチャントに必要なSPが不足しています。
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ENOUGH_SP_TO_ENCHANT_THAT_SKILL;
	
	/**
	 * ID: 1444<br>
	 * Message: スキルのエンチャントに必要な経験値が不足しています。
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ENOUGH_EXP_TO_ENCHANT_THAT_SKILL;
	
	/**
	 * ID: 1445<br>
	 * Message: 以前のサブクラスは消滅し、新たなサブクラスのレベル40からスタートします。よろしいですか。
	 */
	public static final SystemMessageId REPLACE_SUBCLASS_CONFIRM;
	
	/**
	 * ID: 1446<br>
	 * Message: $s1 発 $s2 行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_FROM_S1_TO_S2_DELAYED;
	
	/**
	 * ID: 1447<br>
	 * Message: 食いつきの待機中には他のスキルを使用できません。
	 */
	public static final SystemMessageId CANNOT_DO_WHILE_FISHING_1;
	
	/**
	 * ID: 1448<br>
	 * Message: フィッシング専用スキルのみ使用できます。
	 */
	public static final SystemMessageId ONLY_FISHING_SKILLS_NOW;
	
	/**
	 * ID: 1449<br>
	 * Message: 食いつきました。
	 */
	public static final SystemMessageId GOT_A_BITE;
	
	/**
	 * ID: 1450<br>
	 * Message: 時間切れで獲物を逃しました。
	 */
	public static final SystemMessageId FISH_SPIT_THE_HOOK;
	
	/**
	 * ID: 1451<br>
	 * Message: 獲物を逃しました。
	 */
	public static final SystemMessageId BAIT_STOLEN_BY_FISH;
	
	/**
	 * ID: 1452<br>
	 * Message: 獲物がエサだけ取って逃げました。
	 */
	public static final SystemMessageId BAIT_LOST_FISH_GOT_AWAY;
	
	/**
	 * ID: 1453<br>
	 * Message: 釣り竿を装備していません。
	 */
	public static final SystemMessageId FISHING_POLE_NOT_EQUIPPED;
	
	/**
	 * ID: 1454<br>
	 * Message: エサがついていません。
	 */
	public static final SystemMessageId BAIT_ON_HOOK_BEFORE_FISHING;
	
	/**
	 * ID: 1455<br>
	 * Message: 水中ではフィッシングできません。
	 */
	public static final SystemMessageId CANNOT_FISH_UNDER_WATER;
	
	/**
	 * ID: 1456<br>
	 * Message: 搭乗した状態ではフィッシングできません。
	 */
	public static final SystemMessageId CANNOT_FISH_ON_BOAT;
	
	/**
	 * ID: 1457<br>
	 * Message: 釣り場ではありません。
	 */
	public static final SystemMessageId CANNOT_FISH_HERE;
	
	/**
	 * ID: 1458<br>
	 * Message: フィッシングを取り消します。
	 */
	public static final SystemMessageId FISHING_ATTEMPT_CANCELLED;
	
	/**
	 * ID: 1459<br>
	 * Message: エサが不足しています。
	 */
	public static final SystemMessageId NOT_ENOUGH_BAIT;
	
	/**
	 * ID: 1460<br>
	 * Message: フィッシングを終了します。
	 */
	public static final SystemMessageId REEL_LINE_AND_STOP_FISHING;
	
	/**
	 * ID: 1461<br>
	 * Message: フィッシングを開始します。
	 */
	public static final SystemMessageId CAST_LINE_AND_START_FISHING;
	
	/**
	 * ID: 1462<br>
	 * Message: ポンピングスキルは獲物が食いついた状態でのみ使用できます。
	 */
	public static final SystemMessageId CAN_USE_PUMPING_ONLY_WHILE_FISHING;
	
	/**
	 * ID: 1463<br>
	 * Message: リーリングスキルは獲物が食いついた状態でのみ使用できます。
	 */
	public static final SystemMessageId CAN_USE_REELING_ONLY_WHILE_FISHING;
	
	/**
	 * ID: 1464<br>
	 * Message: 獲物が抵抗しました。
	 */
	public static final SystemMessageId FISH_RESISTED_ATTEMPT_TO_BRING_IT_IN;
	
	/**
	 * ID: 1465<br>
	 * Message: ポンピング成功、ダメージ： $s1
	 */
	public static final SystemMessageId PUMPING_SUCCESFUL_S1_DAMAGE;
	
	/**
	 * ID: 1466<br>
	 * Message: ポンピング失敗、ダメージ： $s1
	 */
	public static final SystemMessageId FISH_RESISTED_PUMPING_S1_HP_REGAINED;
	
	/**
	 * ID: 1467<br>
	 * Message: リーリング成功、ダメージ：$s1
	 */
	public static final SystemMessageId REELING_SUCCESFUL_S1_DAMAGE;
	
	/**
	 * ID: 1468<br>
	 * Message: リーリング失敗、ダメージ：$s1
	 */
	public static final SystemMessageId FISH_RESISTED_REELING_S1_HP_REGAINED;
	
	/**
	 * ID: 1469<br>
	 * Message: 獲物を釣り上げました。
	 */
	public static final SystemMessageId YOU_CAUGHT_SOMETHING;
	
	/**
	 * ID: 1470<br>
	 * Message: フィッシング中にはアイテムの装備、解除、破砕、ドロップはできません。
	 */
	public static final SystemMessageId CANNOT_DO_WHILE_FISHING_2;
	
	/**
	 * ID: 1471<br>
	 * Message: フィッシング中には他の行動を取ることができません。
	 */
	public static final SystemMessageId CANNOT_DO_WHILE_FISHING_3;
	
	/**
	 * ID: 1472<br>
	 * Message: 釣り竿では攻撃できません。
	 */
	public static final SystemMessageId CANNOT_ATTACK_WITH_FISHING_POLE;
	
	/**
	 * ID: 1473<br>
	 * Message: $s1が不足しています。
	 */
	public static final SystemMessageId S1_NOT_SUFFICIENT;
	
	/**
	 * ID: 1474<br>
	 * Message: $s1を使用できません。
	 */
	public static final SystemMessageId S1_NOT_AVAILABLE;
	
	/**
	 * ID: 1475<br>
	 * Message: ペットが$s1を落としました。
	 */
	public static final SystemMessageId PET_DROPPED_S1;
	
	/**
	 * ID: 1476<br>
	 * Message: ペットが+$s1$s2を落としました。
	 */
	public static final SystemMessageId PET_DROPPED_S1_S2;
	
	/**
	 * ID: 1477<br>
	 * Message: ペットが$s1 $s2個を落としました。
	 */
	public static final SystemMessageId PET_DROPPED_S2_S1_S;
	
	/**
	 * ID: 1478<br>
	 * Message: 64x64ピクセルの256色のBMPファイルのみ登録できます。
	 */
	public static final SystemMessageId ONLY_64_PIXEL_256_COLOR_BMP;
	
	/**
	 * ID: 1479<br>
	 * Message: 釣り竿のグレードに合うフィッシング ショットではありません。
	 */
	public static final SystemMessageId WRONG_FISHINGSHOT_GRADE;
	
	/**
	 * ID: 1480<br>
	 * Message: オリンピアードの参加申し込みを取り消しますか。
	 */
	public static final SystemMessageId OLYMPIAD_REMOVE_CONFIRM;
	
	/**
	 * ID: 1481<br>
	 * Message: クラス無制限個人競技を選択しました。参加しますか。
	 */
	public static final SystemMessageId OLYMPIAD_NON_CLASS_CONFIRM;
	
	/**
	 * ID: 1482<br>
	 * Message: クラス別試合を選択しました。参加しますか。
	 */
	public static final SystemMessageId OLYMPIAD_CLASS_CONFIRM;
	
	/**
	 * ID: 1483<br>
	 * Message: 今英雄になりますか。
	 */
	public static final SystemMessageId HERO_CONFIRM;
	
	/**
	 * ID: 1484<br>
	 * Message: 選択した英雄の武器を使用しますか。カマエルは使用できません。
	 */
	public static final SystemMessageId HERO_WEAPON_CONFIRM;
	
	/**
	 * ID: 1485<br>
	 * Message: 話せる島発 グルーディン港行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_TALKING_GLUDIN_DELAYED;
	
	/**
	 * ID: 1486<br>
	 * Message: グルーディン港発 話せる島行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_GLUDIN_TALKING_DELAYED;
	
	/**
	 * ID: 1487<br>
	 * Message: ギラン港発 話せる島行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_GIRAN_TALKING_DELAYED;
	
	/**
	 * ID: 1488<br>
	 * Message: 話せる島発 ギラン港行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_TALKING_GIRAN_DELAYED;
	
	/**
	 * ID: 1489<br>
	 * Message: インナドリル遊覧船の運航に遅れが出ています。
	 */
	public static final SystemMessageId INNADRIL_BOAT_DELAYED;
	
	/**
	 * ID: 1490<br>
	 * Message: 作物$s1を$s2個精算しました。
	 */
	public static final SystemMessageId TRADED_S2_OF_CROP_S1;
	
	/**
	 * ID: 1491<br>
	 * Message: 作物$s1を$s2個精算するのに失敗しました。
	 */
	public static final SystemMessageId FAILED_IN_TRADING_S2_OF_CROP_S1;
	
	/**
	 * ID: 1492<br>
	 * Message: $s1秒後にオリンピアード スタジアムに移動します。
	 */
	public static final SystemMessageId YOU_WILL_ENTER_THE_OLYMPIAD_STADIUM_IN_S1_SECOND_S;
	
	/**
	 * ID: 1493<br>
	 * Message: 試合相手がゲームを終了したので試合が取り消されました。
	 */
	public static final SystemMessageId THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_ENDS_THE_GAME;
	
	/**
	 * ID: 1494<br>
	 * Message: 試合相手がゲームの参加条件を満たさなかったため、試合が取り消されました。
	 */
	public static final SystemMessageId THE_GAME_HAS_BEEN_CANCELLED_BECAUSE_THE_OTHER_PARTY_DOES_NOT_MEET_THE_REQUIREMENTS_FOR_JOINING_THE_GAME;
	
	/**
	 * ID: 1495<br>
	 * Message: $s1秒後に試合を開始します。
	 */
	public static final SystemMessageId THE_GAME_WILL_START_IN_S1_SECOND_S;
	
	/**
	 * ID: 1496<br>
	 * Message: 試合を開始します。
	 */
	public static final SystemMessageId STARTS_THE_GAME;
	
	/**
	 * ID: 1497<br>
	 * Message: $c1が勝利しました。
	 */
	public static final SystemMessageId C1_HAS_WON_THE_GAME;
	
	/**
	 * ID: 1498<br>
	 * Message: 引き分けです。
	 */
	public static final SystemMessageId THE_GAME_ENDED_IN_A_TIE;
	
	/**
	 * ID: 1499<br>
	 * Message: $s1秒後に村に帰還します。
	 */
	public static final SystemMessageId YOU_WILL_BE_MOVED_TO_TOWN_IN_S1_SECONDS;
	
	/**
	 * ID: 1500<br>
	 * Message: $c1 は参加条件に合いません。サブクラス キャラクターではオリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_CANT_JOIN_THE_OLYMPIAD_WITH_A_SUB_CLASS_CHARACTER;
	
	/**
	 * ID: 1501<br>
	 * Message: $c1 は参加条件に合いません。ノーブレス以外はオリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_DOES_NOT_MEET_REQUIREMENTS_ONLY_NOBLESS_CAN_PARTICIPATE_IN_THE_OLYMPIAD;
	
	/**
	 * ID: 1502<br>
	 * Message: $c1 は競技待機者リストにすでに登録されています。
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_MATCH_WAITING_LIST;
	
	/**
	 * ID: 1503<br>
	 * Message: クラス別競技種目の待機者リストに登録されました。
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_CLASSIFIED_GAMES;
	
	/**
	 * ID: 1504<br>
	 * Message: クラス無制限個人競技種目の待機者リストに登録されました。
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_NO_CLASS_GAMES;
	
	/**
	 * ID: 1505<br>
	 * Message: 競技待機者リストから削除されました。
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_DELETED_FROM_THE_WAITING_LIST_OF_A_GAME;
	
	/**
	 * ID: 1506<br>
	 * Message: 競技待機者リストに登録されていません。
	 */
	public static final SystemMessageId YOU_HAVE_NOT_BEEN_REGISTERED_IN_A_WAITING_LIST_OF_A_GAME;
	
	/**
	 * ID: 1507<br>
	 * Message: オリンピアード競技では装備できないアイテムです。
	 */
	public static final SystemMessageId THIS_ITEM_CANT_BE_EQUIPPED_FOR_THE_OLYMPIAD_EVENT;
	
	/**
	 * ID: 1508<br>
	 * Message: オリンピアード競技では使用できないアイテムです。
	 */
	public static final SystemMessageId THIS_ITEM_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT;
	
	/**
	 * ID: 1509<br>
	 * Message: オリンピアード競技では使用できないスキルです。
	 */
	public static final SystemMessageId THIS_SKILL_IS_NOT_AVAILABLE_FOR_THE_OLYMPIAD_EVENT;
	
	/**
	 * ID: 1510<br>
	 * Message: $c1が$s2の経験値を復旧する復活を試みています。復活しますか。
	 */
	public static final SystemMessageId RESSURECTION_REQUEST_BY_C1_FOR_S2_XP;
	
	/**
	 * ID: 1511<br>
	 * Message: ペットの復活を試みている時は、飼い主を復活できません。
	 */
	public static final SystemMessageId MASTER_CANNOT_RES;
	
	/**
	 * ID: 1512<br>
	 * Message: 飼い主の復活を試みている時は、ペットを復活できません。
	 */
	public static final SystemMessageId CANNOT_RES_PET;
	
	/**
	 * ID: 1513<br>
	 * Message: すでにより好条件の復活を提示されている状態です。
	 */
	public static final SystemMessageId RES_HAS_ALREADY_BEEN_PROPOSED;
	
	/**
	 * ID: 1514<br>
	 * Message: ペットが復活中なので、飼い主の復活が無効になりました。
	 */
	public static final SystemMessageId CANNOT_RES_MASTER;
	
	/**
	 * ID: 1515<br>
	 * Message: 飼い主が復活中なので、ペットの復活が無効になりました。
	 */
	public static final SystemMessageId CANNOT_RES_PET2;
	
	/**
	 * ID: 1516<br>
	 * Message: 種の植付けができない対象です。
	 */
	public static final SystemMessageId THE_TARGET_IS_UNAVAILABLE_FOR_SEEDING;
	
	/**
	 * ID: 1517<br>
	 * Message: 祝福された強化に失敗しました。該当アイテムのエンチャント値が0になりました。
	 */
	public static final SystemMessageId BLESSED_ENCHANT_FAILED;
	
	/**
	 * ID: 1518<br>
	 * Message: アイテムの装備条件に合わないため、該当アイテムを装備できません。
	 */
	public static final SystemMessageId CANNOT_EQUIP_ITEM_DUE_TO_BAD_CONDITION;
	
	/**
	 * ID: 1519<br>
	 * Message: ペットが死亡しました。24時間以内に復活させなければペットは消滅して、ペットの持っていたアイテムも消滅します。
	 */
	public static final SystemMessageId MAKE_SURE_YOU_RESSURECT_YOUR_PET_WITHIN_24_HOURS;
	
	/**
	 * ID: 1520<br>
	 * Message: 召喚獣が死亡しました。
	 */
	public static final SystemMessageId SERVITOR_PASSED_AWAY;
	
	/**
	 * ID: 1521<br>
	 * Message: 召喚時間を越えたので召喚獣が消えます。
	 */
	public static final SystemMessageId YOUR_SERVITOR_HAS_VANISHED;
	
	/**
	 * ID: 1522<br>
	 * Message: ペットの死亡後、制限時間を越えたので死体が消滅します。
	 */
	public static final SystemMessageId YOUR_PETS_CORPSE_HAS_DECAYED;
	
	/**
	 * ID: 1523<br>
	 * Message: ペットや召喚獣は船の運航中に水に落ちて死亡する恐れがあるので、出発前に召喚を解除してください。
	 */
	public static final SystemMessageId RELEASE_PET_ON_BOAT;
	
	/**
	 * ID: 1524<br>
	 * Message: $c1のペットが$s2を手に入れました。
	 */
	public static final SystemMessageId C1_PET_GAINED_S2;
	
	/**
	 * ID: 1525<br>
	 * Message: $c1のペットが$s2 $s3個を手に入れました。
	 */
	public static final SystemMessageId C1_PET_GAINED_S3_S2_S;
	
	/**
	 * ID: 1526<br>
	 * Message: $c1のペットが+$s2$s3を手に入れました。
	 */
	public static final SystemMessageId C1_PET_GAINED_S2_S3;
	
	/**
	 * ID: 1527<br>
	 * Message: ペットが空腹だったので$s1を食べました。
	 */
	public static final SystemMessageId PET_TOOK_S1_BECAUSE_HE_WAS_HUNGRY;
	
	/**
	 * ID: 1528<br>
	 * Message: サポートからのご連絡があります。
	 */
	public static final SystemMessageId SENT_PETITION_TO_GM;
	
	/**
	 * ID: 1529<br>
	 * Message: $c1から連合に招待されました。参加しますか。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_CONFIRM_FROM_C1;
	
	/**
	 * ID: 1530<br>
	 * Message: 対象を定めるか名前を入力してください。
	 */
	public static final SystemMessageId SELECT_TARGET_OR_ENTER_NAME;
	
	/**
	 * ID: 1531<br>
	 * Message: 宣戦布告する相手の血盟名を入力してください。
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_DECLARE_WAR2;
	
	/**
	 * ID: 1532<br>
	 * Message: 戦争を取り消す相手の血盟名を入力してください。
	 */
	public static final SystemMessageId ENTER_CLAN_NAME_TO_CEASE_FIRE;
	
	/**
	 * ID: 1533<br>
	 * Message: お知らせ： $c1が$s2を手に入れました。
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PICKED_UP_S2;
	
	/**
	 * ID: 1534<br>
	 * Message: お知らせ： $c1が+$s2$s3を手に入れました。
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PICKED_UP_S2_S3;
	
	/**
	 * ID: 1535<br>
	 * Message: お知らせ： $c1のペットが$s2を手に入れました。
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PET_PICKED_UP_S2;
	
	/**
	 * ID: 1536<br>
	 * Message: お知らせ： $c1のペットが+$s2$s3を手に入れました。
	 */
	public static final SystemMessageId ANNOUNCEMENT_C1_PET_PICKED_UP_S2_S3;
	
	/**
	 * ID: 1537<br>
	 * Message: 現在地： $s1、$s2、$s3 (ルウン城の村付近)
	 */
	public static final SystemMessageId LOC_RUNE_S1_S2_S3;
	
	/**
	 * ID: 1538<br>
	 * Message: 現在地： $s1、$s2、$s3 (ゴダード城の村付近)
	 */
	public static final SystemMessageId LOC_GODDARD_S1_S2_S3;
	
	/**
	 * ID: 1539<br>
	 * Message: 貨物が話せる島の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_TALKING_VILLAGE;
	
	/**
	 * ID: 1540<br>
	 * Message: 貨物がダークエルフ村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_DARKELF_VILLAGE;
	
	/**
	 * ID: 1541<br>
	 * Message: 貨物がエルフ村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_ELVEN_VILLAGE;
	
	/**
	 * ID: 1542<br>
	 * Message: 貨物がオーク村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_ORC_VILLAGE;
	
	/**
	 * ID: 1543<br>
	 * Message: 貨物がドワーフ村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_DWARVEN_VILLAGE;
	
	/**
	 * ID: 1544<br>
	 * Message: 貨物がアデン城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_ADEN;
	
	/**
	 * ID: 1545<br>
	 * Message: 貨物がオーレン城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_OREN;
	
	/**
	 * ID: 1546<br>
	 * Message: 貨物が猟師の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_HUNTERS;
	
	/**
	 * ID: 1547<br>
	 * Message: 貨物がディオン城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_DION;
	
	/**
	 * ID: 1548<br>
	 * Message: 貨物がフローラン村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_FLORAN;
	
	/**
	 * ID: 1549<br>
	 * Message: 貨物がグルーディン村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_GLUDIN;
	
	/**
	 * ID: 1550<br>
	 * Message: 貨物がグルーディオ城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_GLUDIO;
	
	/**
	 * ID: 1551<br>
	 * Message: 貨物がギラン城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_GIRAN;
	
	/**
	 * ID: 1552<br>
	 * Message: 貨物がハイネスに届いています。
	 */
	public static final SystemMessageId CARGO_AT_HEINE;
	
	/**
	 * ID: 1553<br>
	 * Message: 貨物がルウン城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_RUNE;
	
	/**
	 * ID: 1554<br>
	 * Message: 貨物がゴダード城の村に届いています。
	 */
	public static final SystemMessageId CARGO_AT_GODDARD;
	
	/**
	 * ID: 1555<br>
	 * Message: キャラクターの削除を取り消しますか。
	 */
	public static final SystemMessageId CANCEL_CHARACTER_DELETION_CONFIRM;
	
	/**
	 * ID: 1556<br>
	 * Message: お知らせの内容を保存しました。
	 */
	public static final SystemMessageId CLAN_NOTICE_SAVED;
	
	/**
	 * ID: 1557<br>
	 * Message: 種の価格は$s1以上$s2以下にしてください。
	 */
	public static final SystemMessageId SEED_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1558<br>
	 * Message: 種の数量は$s1以上$s2以下にしてください。
	 */
	public static final SystemMessageId THE_QUANTITY_OF_SEED_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1559<br>
	 * Message: 作物の価格は$s1以上$s2以下にしてください。
	 */
	public static final SystemMessageId CROP_PRICE_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1560<br>
	 * Message: 作物の数量は$s1以上$s2以下にしてください。
	 */
	public static final SystemMessageId THE_QUANTITY_OF_CROP_SHOULD_BE_MORE_THAN_S1_AND_LESS_THAN_S2;
	
	/**
	 * ID: 1561<br>
	 * Message: $s1血盟が血盟戦を布告しました。
	 */
	public static final SystemMessageId CLAN_S1_DECLARED_WAR;
	
	/**
	 * ID: 1562<br>
	 * Message: $s1血盟に対し血盟戦を布告しました。ただ今より相手の血盟員によって死亡した場合、経験値ロストが通常の4分の1になります。
	 */
	public static final SystemMessageId CLAN_WAR_DECLARED_AGAINST_S1_IF_KILLED_LOSE_LOW_EXP;
	
	/**
	 * ID: 1563<br>
	 * Message: $s1血盟は血盟レベルまたは血盟員数が足りないので血盟戦を布告できません。
	 */
	public static final SystemMessageId CANNOT_DECLARE_WAR_TOO_LOW_LEVEL_OR_NOT_ENOUGH_MEMBERS;
	
	/**
	 * ID: 1564<br>
	 * Message: 血盟レベル3以上、血盟員数15人以上の血盟のみが血盟戦を布告できます。
	 */
	public static final SystemMessageId CLAN_WAR_DECLARED_IF_CLAN_LVL3_OR_15_MEMBER;
	
	/**
	 * ID: 1565<br>
	 * Message: 存在しない血盟または長期間活動していない血盟なので宣戦布告できません。
	 */
	public static final SystemMessageId CLAN_WAR_CANNOT_DECLARED_CLAN_NOT_EXIST;
	
	/**
	 * ID: 1566<br>
	 * Message: $s1血盟が布告を取り下げました。
	 */
	public static final SystemMessageId CLAN_S1_HAS_DECIDED_TO_STOP;
	
	/**
	 * ID: 1567<br>
	 * Message: $s1血盟への布告を取り下げました。
	 */
	public static final SystemMessageId WAR_AGAINST_S1_HAS_STOPPED;
	
	/**
	 * ID: 1568<br>
	 * Message: 布告の対象が正しくありません。
	 */
	public static final SystemMessageId WRONG_DECLARATION_TARGET;
	
	/**
	 * ID: 1569<br>
	 * Message: 同盟を結んだ血盟には血盟戦を布告できません。
	 */
	public static final SystemMessageId CLAN_WAR_AGAINST_A_ALLIED_CLAN_NOT_WORK;
	
	/**
	 * ID: 1570<br>
	 * Message: 30以上の血盟に同時に宣戦布告することはできません。
	 */
	public static final SystemMessageId TOO_MANY_CLAN_WARS;
	
	/**
	 * ID: 1571<br>
	 * Message: =======<布告リスト>=======
	 */
	public static final SystemMessageId CLANS_YOU_DECLARED_WAR_ON;
	
	/**
	 * ID: 1572<br>
	 * Message: ======<被布告リスト>======
	 */
	public static final SystemMessageId CLANS_THAT_HAVE_DECLARED_WAR_ON_YOU;
	
	/**
	 * ID: 1573<br>
	 * Message: 布告する血盟が存在しません。
	 */
	public static final SystemMessageId NO_WARS_AGAINST_YOU;
	
	/**
	 * ID: 1574<br>
	 * Message: 布告を受ける血盟が存在しません。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ONLY_BY_LEVEL_5_CLAN_LEADER_PARTY_LEADER;
	
	/**
	 * ID: 1575<br>
	 * Message: 連合の結成は血盟レベル5以上の血盟主でありパーティ リーダーのみが可能です。
	 */
	public static final SystemMessageId PET_USE_SPIRITSHOT;
	
	/**
	 * ID: 1576<br>
	 * Message: ペットが精霊の力を使用します。
	 */
	public static final SystemMessageId SERVITOR_USE_SPIRITSHOT;
	
	/**
	 * ID: 1577<br>
	 * Message: 召喚獣が精霊の力を使用します。
	 */
	public static final SystemMessageId SERVITOR_USE_THE_POWER_OF_SPIRIT;
	
	/**
	 * ID: 1578<br>
	 * Message: 個人商店や個人工房を開いている時はアイテムを装備できません。
	 */
	public static final SystemMessageId ITEMS_UNAVAILABLE_FOR_STORE_MANUFACTURE;
	
	/**
	 * ID: 1579<br>
	 * Message: $c1のペットが$s2アデナを手に入れました。
	 */
	public static final SystemMessageId C1_PET_GAINED_S2_ADENA;
	
	/**
	 * ID: 1580<br>
	 * Message: 連合が結成されました。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_FORMED;
	
	/**
	 * ID: 1581<br>
	 * Message: 連合を解除しました。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_DISBANDED;
	
	/**
	 * ID: 1582<br>
	 * Message: 連合に参加しました。
	 */
	public static final SystemMessageId JOINED_COMMAND_CHANNEL;
	
	/**
	 * ID: 1583<br>
	 * Message: 連合から追放されました。
	 */
	public static final SystemMessageId DISMISSED_FROM_COMMAND_CHANNEL;
	
	/**
	 * ID: 1584<br>
	 * Message: $c1のパーティを連合から追放しました。
	 */
	public static final SystemMessageId C1_PARTY_DISMISSED_FROM_COMMAND_CHANNEL;
	
	/**
	 * ID: 1585<br>
	 * Message: 連合が非アクティブになりました。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_DISBANDED2;
	
	/**
	 * ID: 1586<br>
	 * Message: 連合から脱退しました。
	 */
	public static final SystemMessageId LEFT_COMMAND_CHANNEL;
	
	/**
	 * ID: 1587<br>
	 * Message: $c1のパーティが連合チャンネルから脱退しました。
	 */
	public static final SystemMessageId C1_PARTY_LEFT_COMMAND_CHANNEL;
	
	/**
	 * ID: 1588<br>
	 * Message: 連合は5つ以上のパーティが参加した時のみアクティブになります。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ONLY_AT_LEAST_5_PARTIES;
	
	/**
	 * ID: 1589<br>
	 * Message: $c1に連合の権限が与えられました。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_LEADER_NOW_C1;
	
	/**
	 * ID: 1590<br>
	 * Message: ===<連合情報（全パーティ数：$s1)>===
	 */
	public static final SystemMessageId GUILD_INFO_HEADER;
	
	/**
	 * ID: 1591<br>
	 * Message: 連合に招待したプレイヤーがいません。
	 */
	public static final SystemMessageId NO_USER_INVITED_TO_COMMAND_CHANNEL;
	
	/**
	 * ID: 1592<br>
	 * Message: これ以上連合を開設できません。
	 */
	public static final SystemMessageId CANNOT_LONGER_SETUP_COMMAND_CHANNEL;
	
	/**
	 * ID: 1593<br>
	 * Message: 連合に招待できる権限がありません。
	 */
	public static final SystemMessageId CANNOT_INVITE_TO_COMMAND_CHANNEL;
	
	/**
	 * ID: 1594<br>
	 * Message: $c1のパーティはすでに連合に参加しています。
	 */
	public static final SystemMessageId C1_ALREADY_MEMBER_OF_COMMAND_CHANNEL;
	
	/**
	 * ID: 1595<br>
	 * Message: $s1に成功しました。
	 */
	public static final SystemMessageId S1_SUCCEEDED;
	
	/**
	 * ID: 1596<br>
	 * Message: $s1に当たりました。
	 */
	public static final SystemMessageId HIT_BY_S1;
	
	/**
	 * ID: 1597<br>
	 * Message: $s1が失敗しました。
	 */
	public static final SystemMessageId S1_FAILED;
	
	/**
	 * ID: 1598<br>
	 * Message: ペットおよび召喚獣が死亡した状態では、ペットおよび召喚獣用ソウル ショットまたはスピリット ショットは使用できません。
	 */
	public static final SystemMessageId SOULSHOTS_AND_SPIRITSHOTS_ARE_NOT_AVAILABLE_FOR_A_DEAD_PET;
	
	/**
	 * ID: 1599<br>
	 * Message: 戦闘中は観戦できません。
	 */
	public static final SystemMessageId CANNOT_OBSERVE_IN_COMBAT;
	
	/**
	 * ID: 1600<br>
	 * Message: 明日の項目の数値がすべて0に初期化されます。よろしいですか。
	 */
	public static final SystemMessageId TOMORROW_ITEM_ZERO_CONFIRM;
	
	/**
	 * ID: 1601<br>
	 * Message: 明日の項目の数値がすべて今日と同じ数値に設定されます。よろしいですか。
	 */
	public static final SystemMessageId TOMORROW_ITEM_SAME_CONFIRM;
	
	/**
	 * ID: 1602<br>
	 * Message: 連合チャットはパーティ リーダーのみ参加できます。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ONLY_FOR_PARTY_LEADER;
	
	/**
	 * ID: 1603<br>
	 * Message: 全体命令はチャンネル開設者のみ行うことができます。
	 */
	public static final SystemMessageId ONLY_COMMANDER_GIVE_COMMAND;
	
	/**
	 * ID: 1604<br>
	 * Message: 礼服着用中はすべてのスキルと、発動モーションが必要なアイテムが使用できません。
	 */
	public static final SystemMessageId CANNOT_USE_ITEMS_SKILLS_WITH_FORMALWEAR;
	
	/**
	 * ID: 1605<br>
	 * Message: *ここでは$s1荘園の種のみ購入できます。
	 */
	public static final SystemMessageId HERE_YOU_CAN_BUY_ONLY_SEEDS_OF_S1_MANOR;
	
	/**
	 * ID: 1606<br>
	 * Message: 3次転職クエストをクリアし、新たなクラスに転職しました。おめでとうございます！
	 */
	public static final SystemMessageId THIRD_CLASS_TRANSFER;
	
	/**
	 * ID: 1607<br>
	 * Message: $s1アデナを買取り手数料として支払いました。
	 */
	public static final SystemMessageId S1_ADENA_HAS_BEEN_WITHDRAWN_TO_PAY_FOR_PURCHASING_FEES;
	
	/**
	 * ID: 1608<br>
	 * Message: アデナが不足して他城の買取りができません。
	 */
	public static final SystemMessageId INSUFFICIENT_ADENA_TO_BUY_CASTLE;
	
	/**
	 * ID: 1609<br>
	 * Message: すでに宣戦布告した血盟です。
	 */
	public static final SystemMessageId WAR_ALREADY_DECLARED;
	
	/**
	 * ID: 1610<br>
	 * Message: 自分の血盟には宣戦布告できません。
	 */
	public static final SystemMessageId CANNOT_DECLARE_AGAINST_OWN_CLAN;
	
	/**
	 * ID: 1611<br>
	 * Message: パーティ リーダー： $c1
	 */
	public static final SystemMessageId PARTY_LEADER_C1;
	
	/**
	 * ID: 1612<br>
	 * Message: =====<双方布告リスト>=====
	 */
	public static final SystemMessageId WAR_LIST;
	
	/**
	 * ID: 1613<br>
	 * Message: 双方布告した血盟がありません。
	 */
	public static final SystemMessageId NO_CLAN_ON_WAR_LIST;
	
	/**
	 * ID: 1614<br>
	 * Message: すでに開設されたチャンネルに参加しています。
	 */
	public static final SystemMessageId JOINED_CHANNEL_ALREADY_OPEN;
	
	/**
	 * ID: 1615<br>
	 * Message: チャンネルがアクティブ化するまで$s1のパーティが参加できます。
	 */
	public static final SystemMessageId S1_PARTIES_REMAINING_UNTIL_CHANNEL;
	
	/**
	 * ID: 1616<br>
	 * Message: 連合がアクティブになりました。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_ACTIVATED;
	
	/**
	 * ID: 1617<br>
	 * Message: 連合を利用できる権限がありません。
	 */
	public static final SystemMessageId CANT_USE_COMMAND_CHANNEL;
	
	/**
	 * ID: 1618<br>
	 * Message: ルウン港発 グルーディン港行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_RUNE_GLUDIN_DELAYED;
	
	/**
	 * ID: 1619<br>
	 * Message: グルーディン港発 ルウン港行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_GLUDIN_RUNE_DELAYED;
	
	/**
	 * ID: 1620<br>
	 * Message: ルウン港に到着しました。
	 */
	public static final SystemMessageId ARRIVED_AT_RUNE;
	
	/**
	 * ID: 1621<br>
	 * Message: 5分後、ルウン港からグルーディン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_5_MINUTES;
	
	/**
	 * ID: 1622<br>
	 * Message: 1分後、ルウン港からグルーディン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_1_MINUTE;
	
	/**
	 * ID: 1623<br>
	 * Message: まもなく、ルウン港からグルーディン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_SHORTLY;
	
	/**
	 * ID: 1624<br>
	 * Message: ルウン港からグルーディン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_NOW;
	
	/**
	 * ID: 1625<br>
	 * Message: 10分間停泊後、ルウン港に出発します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_10_MINUTES;
	
	/**
	 * ID: 1626<br>
	 * Message: 5分後、グルーディン港からルウン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_5_MINUTES;
	
	/**
	 * ID: 1627<br>
	 * Message: 1分後、グルーディン港からルウン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_1_MINUTE;
	
	/**
	 * ID: 1628<br>
	 * Message: まもなく、グルーディン港からルウン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_GLUDIN_SHORTLY2;
	
	/**
	 * ID: 1629<br>
	 * Message: グルーディン港からルウン港行きの船が出航します。
	 */
	public static final SystemMessageId DEPARTURE_FOR_RUNE_NOW;
	
	/**
	 * ID: 1630<br>
	 * Message: ルウン港発の定期船が、約15分後グルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_15_MINUTES;
	
	/**
	 * ID: 1631<br>
	 * Message: ルウン港発の定期船が、約10分後グルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_10_MINUTES;
	
	/**
	 * ID: 1632<br>
	 * Message: ルウン港発の定期船が、約5分後グルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_5_MINUTES;
	
	/**
	 * ID: 1633<br>
	 * Message: ルウン港発の定期船が、約1分後グルーディン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_AT_GLUDIN_1_MINUTE;
	
	/**
	 * ID: 1634<br>
	 * Message: グルーディン港発の定期船が、約15分後ルウン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_15_MINUTES;
	
	/**
	 * ID: 1635<br>
	 * Message: グルーディン港発の定期船が、約10分後ルウン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_10_MINUTES;
	
	/**
	 * ID: 1636<br>
	 * Message: グルーディン港発の定期船が、約5分後ルウン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_5_MINUTES;
	
	/**
	 * ID: 1637<br>
	 * Message: グルーディン港発の定期船が、約1分後ルウン港に到着します。
	 */
	public static final SystemMessageId FERRY_FROM_GLUDIN_AT_RUNE_1_MINUTE;
	
	/**
	 * ID: 1638<br>
	 * Message: 製作図集、個人工房、個人商店の使用中にはフィッシングができません。
	 */
	public static final SystemMessageId CANNOT_FISH_WHILE_USING_RECIPE_BOOK;
	
	/**
	 * ID: 1639<br>
	 * Message: 第$s1期オリンピアード期間が始まりました。
	 */
	public static final SystemMessageId OLYMPIAD_PERIOD_S1_HAS_STARTED;
	
	/**
	 * ID: 1640<br>
	 * Message: 第$s1期オリンピアード期間が終了しました。
	 */
	public static final SystemMessageId OLYMPIAD_PERIOD_S1_HAS_ENDED;
	
	/**
	 * ID: 1641<br>
	 * Message: オリンピアード競技が始まりました。
	 */
	public static final SystemMessageId THE_OLYMPIAD_GAME_HAS_STARTED;
	
	/**
	 * ID: 1642<br>
	 * Message: オリンピアード競技が終了しました。
	 */
	public static final SystemMessageId THE_OLYMPIAD_GAME_HAS_ENDED;
	
	/**
	 * ID: 1643<br>
	 * Message: 現在地： $s1、$s2、$s3 (次元の狭間)
	 */
	public static final SystemMessageId LOC_DIMENSIONAL_GAP_S1_S2_S3;
	
	// 1644 - 1648: none
	
	/**
	 * ID: 1649<br>
	 * Message: 接続時間の累積を再開します。
	 */
	public static final SystemMessageId PLAY_TIME_NOW_ACCUMULATING;
	
	/**
	 * ID: 1650<br>
	 * Message: 大変混みあっていますので接続できません。しばらくしてから再度アクセスしてください。
	 */
	public static final SystemMessageId TRY_LOGIN_LATER;
	
	/**
	 * ID: 1651<br>
	 * Message: オリンピアード競技が行われる時間ではありません。
	 */
	public static final SystemMessageId THE_OLYMPIAD_GAME_IS_NOT_CURRENTLY_IN_PROGRESS;
	
	/**
	 * ID: 1652<br>
	 * Message: リプレイ録画を始めます。
	 */
	public static final SystemMessageId RECORDING_GAMEPLAY_START;
	
	/**
	 * ID: 1653<br>
	 * Message: リプレイ ファイルを保存しました。($s1)
	 */
	public static final SystemMessageId RECORDING_GAMEPLAY_STOP_S1;
	
	/**
	 * ID: 1654<br>
	 * Message: リプレイ録画に失敗しました。
	 */
	public static final SystemMessageId RECORDING_GAMEPLAY_FAILED;
	
	/**
	 * ID: 1655<br>
	 * Message: モンスターが釣れました！
	 */
	public static final SystemMessageId YOU_CAUGHT_SOMETHING_SMELLY_THROW_IT_BACK;
	
	/**
	 * ID: 1656<br>
	 * Message: NPCとのトレードに成功しました。
	 */
	public static final SystemMessageId SUCCESSFULLY_TRADED_WITH_NPC;
	
	/**
	 * ID: 1657<br>
	 * Message: $c1がオリンピアード ポイント$s2ポイントを得ました。
	 */
	public static final SystemMessageId C1_HAS_GAINED_S2_OLYMPIAD_POINTS;
	
	/**
	 * ID: 1658<br>
	 * Message: $c1がオリンピアード ポイント$s2ポイントを失いました。
	 */
	public static final SystemMessageId C1_HAS_LOST_S2_OLYMPIAD_POINTS;
	
	/**
	 * ID: 1659<br>
	 * Message: 現在地： $s1、$s2、$s3 (帝国の墓地)
	 */
	public static final SystemMessageId LOC_CEMETARY_OF_THE_EMPIRE_S1_S2_S3;
	
	/**
	 * ID: 1660<br>
	 * Message: チャンネル開設者： $c1
	 */
	public static final SystemMessageId CHANNEL_CREATOR_C1;
	
	/**
	 * ID: 1661<br>
	 * Message: $c1が$s2 $s3個を手に入れました。
	 */
	public static final SystemMessageId C1_OBTAINED_S3_S2_S;
	
	/**
	 * ID: 1662<br>
	 * Message: 一箇所で長時間釣っていると食いつく確率が低くなります。他の場所に移動して続けてください。
	 */
	public static final SystemMessageId FISH_NO_MORE_BITING_TRY_OTHER_LOCATION;
	
	/**
	 * ID: 1663<br>
	 * Message: エンサインを登録または削除しました。アジトまたは城を所有する血盟にのみ、血盟関連アイテムにエンサインが表示されます。
	 */
	public static final SystemMessageId CLAN_EMBLEM_WAS_SUCCESSFULLY_REGISTERED;
	
	/**
	 * ID: 1664<br>
	 * Message: 魚が抵抗しているのでウキが激しく動いています。
	 */
	public static final SystemMessageId FISH_RESISTING_LOOK_BOBBLER;
	
	/**
	 * ID: 1665<br>
	 * Message: 魚が疲れているのでウキの動きが弱いです。
	 */
	public static final SystemMessageId YOU_WORN_FISH_OUT;
	
	/**
	 * ID: 1666<br>
	 * Message: +$s1$s2を手に入れました。
	 */
	public static final SystemMessageId OBTAINED_S1_S2;
	
	/**
	 * ID: 1667<br>
	 * Message: インスタント キル！
	 */
	public static final SystemMessageId LETHAL_STRIKE;
	
	/**
	 * ID: 1668<br>
	 * Message: 即死スキルがヒットしました！
	 */
	public static final SystemMessageId LETHAL_STRIKE_SUCCESSFUL;
	
	/**
	 * ID: 1669<br>
	 * Message: アイテム変換に失敗しました。
	 */
	public static final SystemMessageId NOTHING_INSIDE_THAT;
	
	/**
	 * ID: 1670<br>
	 * Message: リーリング(ポンピング)スキルのレベルがフィッシング マスタリーのレベルよりも高いので$s1のペナルティが適用されます。
	 */
	public static final SystemMessageId REELING_PUMPING_3_LEVELS_HIGHER_THAN_FISHING_PENALTY;
	
	/**
	 * ID: 1671<br>
	 * Message: リーリング成功！(マスタリー ペナルティ：$s1)
	 */
	public static final SystemMessageId REELING_SUCCESSFUL_PENALTY_S1;
	
	/**
	 * ID: 1672<br>
	 * Message: ポンピング成功！(マスタリー ペナルティ：$s1)
	 */
	public static final SystemMessageId PUMPING_SUCCESSFUL_PENALTY_S1;
	
	/**
	 * ID: 1673<br>
	 * Message: 今回のオリンピアードの現在の戦績は$s1戦$s2勝$s3敗です。獲得したオリンピアード ポイントは$s4ポイントです。
	 */
	public static final SystemMessageId THE_CURRENT_RECORD_FOR_THIS_OLYMPIAD_SESSION_IS_S1_MATCHES_S2_WINS_S3_DEFEATS_YOU_HAVE_EARNED_S4_OLYMPIAD_POINTS;
	
	/**
	 * ID: 1674<br>
	 * Message: 対象がノーブレスの場合、このコマンドは使えません。
	 */
	public static final SystemMessageId NOBLESSE_ONLY;
	
	/**
	 * ID: 1675<br>
	 * Message: 04時30分から20時の間は荘園の設定を変更できません。
	 */
	public static final SystemMessageId A_MANOR_CANNOT_BE_SET_UP_BETWEEN_6_AM_AND_8_PM;
	
	/**
	 * ID: 1676<br>
	 * Message: 召喚獣またはペットがいないので自動使用できません。
	 */
	public static final SystemMessageId NO_SERVITOR_CANNOT_AUTOMATE_USE;
	
	/**
	 * ID: 1677<br>
	 * Message: 戦闘中の血盟員が存在するので布告を取り消せません。
	 */
	public static final SystemMessageId CANT_STOP_CLAN_WAR_WHILE_IN_COMBAT;
	
	/**
	 * ID: 1678<br>
	 * Message: $s1血盟に血盟戦を布告しませんでした。
	 */
	public static final SystemMessageId NO_CLAN_WAR_AGAINST_CLAN_S1;
	
	/**
	 * ID: 1679<br>
	 * Message: 全体命令はチャンネル開設者のみ実行できます。
	 */
	public static final SystemMessageId ONLY_CHANNEL_CREATOR_CAN_GLOBAL_COMMAND;
	
	/**
	 * ID: 1680<br>
	 * Message: $c1がチャンネル招待を拒否しました。
	 */
	public static final SystemMessageId C1_DECLINED_CHANNEL_INVITATION;
	
	/**
	 * ID: 1681<br>
	 * Message: $c1の応答がないのでチャンネル招待に失敗しました。
	 */
	public static final SystemMessageId C1_DID_NOT_RESPOND_CHANNEL_INVITATION_FAILED;
	
	/**
	 * ID: 1682<br>
	 * Message: チャンネル追放は開設者のみ実行できます。
	 */
	public static final SystemMessageId ONLY_CHANNEL_CREATOR_CAN_DISMISS;
	
	/**
	 * ID: 1683<br>
	 * Message: チャンネル脱退はパーティ リーダーのみ実行できます。
	 */
	public static final SystemMessageId ONLY_PARTY_LEADER_CAN_LEAVE_CHANNEL;
	
	/**
	 * ID: 1684<br>
	 * Message: 血盟解散中には血盟戦を布告できません。
	 */
	public static final SystemMessageId NO_CLAN_WAR_AGAINST_DISSOLVING_CLAN;
	
	/**
	 * ID: 1685<br>
	 * Message: PKカウントが1以上だと装備できないアイテムです。
	 */
	public static final SystemMessageId YOU_ARE_UNABLE_TO_EQUIP_THIS_ITEM_WHEN_YOUR_PK_COUNT_IS_GREATER_THAN_OR_EQUAL_TO_ONE;
	
	/**
	 * ID: 1686<br>
	 * Message: 城壁が崩れました。
	 */
	public static final SystemMessageId CASTLE_WALL_DAMAGED;
	
	/**
	 * ID: 1687<br>
	 * Message: ワイバーンに乗ったままでは行けないエリアです。引き続き留まると搭乗が解除されます。
	 */
	public static final SystemMessageId AREA_CANNOT_BE_ENTERED_WHILE_MOUNTED_WYVERN;
	
	/**
	 * ID: 1688<br>
	 * Message: 個人商店や個人工房の状態ではエンチャントできません。
	 */
	public static final SystemMessageId CANNOT_ENCHANT_WHILE_STORE;
	
	/**
	 * ID: 1689<br>
	 * Message: $c1 はクラス別競技の待機者リストにすでに登録されています。
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_CLASS_MATCH_WAITING_LIST;
	
	/**
	 * ID: 1690<br>
	 * Message: $c1 はクラス無制限個人競技の待機者リストにすでに登録されています。
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_ON_THE_NON_CLASS_LIMITED_MATCH_WAITING_LIST;
	
	/**
	 * ID: 1691<br>
	 * Message: $c1 は参加条件に合いません。インベントリ スロットが80％を超えているため、オリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_INVENTORY_SLOT_EXCEEDS_80_PERCENT;
	
	/**
	 * ID: 1692<br>
	 * Message: $c1 は参加条件に合いません。クラスをサブクラスに変更したため、参加できません。
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_WHILE_CHANGED_TO_SUB_CLASS;
	
	/**
	 * ID: 1693<br>
	 * Message: 待機者リストに登録した状態では競技を観覧できません。
	 */
	public static final SystemMessageId WHILE_YOU_ARE_ON_THE_WAITING_LIST_YOU_ARE_NOT_ALLOWED_TO_WATCH_THE_GAME;
	
	/**
	 * ID: 1694<br>
	 * Message: 攻城戦時、ノーブレスの血盟主のみ使用できます。
	 */
	public static final SystemMessageId ONLY_NOBLESSE_LEADER_CAN_VIEW_SIEGE_STATUS_WINDOW;
	
	/**
	 * ID: 1695<br>
	 * Message: 攻城戦時のみ使用できます。
	 */
	public static final SystemMessageId ONLY_DURING_SIEGE;
	
	/**
	 * ID: 1696<br>
	 * Message: プレイ時間は $s1時間です。
	 */
	public static final SystemMessageId ACCUMULATED_PLAY_TIME_IS_S1;
	
	/**
	 * ID: 1697<br>
	 * Message: ゲーム プレイ時間が「疲労度」を超えたため、ゲーム効率が正常値の50％に低下しました。健康のために今すぐログアウトし、休憩をとったあとに、適切なストレッチ等を行い、勉学とのバランスをとってください。
	 */
	public static final SystemMessageId ACCUMULATED_PLAY_TIME_WARNING1;
	
	/**
	 * ID: 1698<br>
	 * Message: 健康を害する恐れのあるゲーム時間に入りました。健康のために今すぐログアウトし、休憩をおとりください。ログアウトしない場合、健康を害してゲーム効率が0％に低下し、ログアウト後の累積時間が5時間に達しないと元には戻りません。
	 */
	public static final SystemMessageId ACCUMULATED_PLAY_TIME_WARNING2;
	
	/**
	 * ID: 1699<br>
	 * Message: パーティ メンバーを強制追放できません。
	 */
	public static final SystemMessageId CANNOT_DISMISS_PARTY_MEMBER;
	
	/**
	 * ID: 1700<br>
	 * Message: ペットおよび召喚獣用スピリット ショットが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SPIRITHOTS_FOR_PET;
	
	/**
	 * ID: 1701<br>
	 * Message: ペットおよび召喚獣用ソウル ショットが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SOULSHOTS_FOR_PET;
	
	/**
	 * ID: 1702<br>
	 * Message: 先ほどチェックしたユーザー$s1はBOT使用者と判明しました。
	 */
	public static final SystemMessageId S1_USING_THIRD_PARTY_PROGRAM;
	
	/**
	 * ID: 1703<br>
	 * Message: 先ほどチェックしたユーザーはBOT使用者ではないようです。
	 */
	public static final SystemMessageId NOT_USING_THIRD_PARTY_PROGRAM;
	
	/**
	 * ID: 1704<br>
	 * Message: 個人工房、商店設定ウィンドウを閉じてから実行してください。
	 */
	public static final SystemMessageId CLOSE_STORE_WINDOW_AND_TRY_AGAIN;
	
	/**
	 * ID: 1705<br>
	 * Message: ネットカフェ ポイント獲得期間です。
	 */
	public static final SystemMessageId PCPOINT_ACQUISITION_PERIOD;
	
	/**
	 * ID: 1706<br>
	 * Message: ネットカフェ ポイント有効期間です。
	 */
	public static final SystemMessageId PCPOINT_USE_PERIOD;
	
	/**
	 * ID: 1707<br>
	 * Message: ネットカフェ ポイント $s1ポイントを獲得しました。
	 */
	public static final SystemMessageId ACQUIRED_S1_PCPOINT;
	
	/**
	 * ID: 1708<br>
	 * Message: ダブル ポイント！ネットカフェ ポイント $s1ポイントを獲得しました。
	 */
	public static final SystemMessageId ACQUIRED_S1_PCPOINT_DOUBLE;
	
	/**
	 * ID: 1709<br>
	 * Message: $s1ポイントを使用します。
	 */
	public static final SystemMessageId USING_S1_PCPOINT;
	
	/**
	 * ID: 1710<br>
	 * Message: ポイントが足りません。
	 */
	public static final SystemMessageId SHORT_OF_ACCUMULATED_POINTS;
	
	/**
	 * ID: 1711<br>
	 * Message: ネットカフェ ポイント有効期限が切れています。
	 */
	public static final SystemMessageId PCPOINT_USE_PERIOD_EXPIRED;
	
	/**
	 * ID: 1712<br>
	 * Message: ネットカフェ ポイント獲得期間が終了しました。
	 */
	public static final SystemMessageId PCPOINT_ACCUMULATION_PERIOD_EXPIRED;
	
	/**
	 * ID: 1713<br>
	 * Message: 待機者数が足りないので、競技開始に遅れが出る場合があります。
	 */
	public static final SystemMessageId GAMES_DELAYED;
	
	/**
	 * ID: 1714<br>
	 * Message: 現在地： $s1、$s2、$s3 (シュチュッツガルト城の村付近)
	 */
	public static final SystemMessageId LOC_SCHUTTGART_S1_S2_S3;
	
	/**
	 * ID: 1715<br>
	 * Message: ピースゾーン\n- PvPができないエリアです。
	 */
	public static final SystemMessageId PEACEFUL_ZONE;
	
	/**
	 * ID: 1716<br>
	 * Message: 異常状態エリア
	 */
	public static final SystemMessageId ALTERED_ZONE;
	
	/**
	 * ID: 1717<br>
	 * Message: 攻城戦ゾーン\n-攻城戦が行われているエリアです。\n死亡時の復活に制限があるエリアです。
	 */
	public static final SystemMessageId SIEGE_ZONE;
	
	/**
	 * ID: 1718<br>
	 * Message: 一般フィールド
	 */
	public static final SystemMessageId GENERAL_ZONE;
	
	/**
	 * ID: 1719<br>
	 * Message: セブン サイン ゾーン\n- レベル アップしてもHP、MPが\n回復されないエリアです。
	 */
	public static final SystemMessageId SEVENSIGNS_ZONE;
	
	/**
	 * ID: 1720<br>
	 * Message: ---
	 */
	public static final SystemMessageId UNKNOWN1;
	
	/**
	 * ID: 1721<br>
	 * Message: 紛争エリア
	 */
	public static final SystemMessageId COMBAT_ZONE;
	
	/**
	 * ID: 1722<br>
	 * Message: 個人商店、個人工房で検索するアイテム名を入力してください。
	 */
	public static final SystemMessageId ENTER_ITEM_NAME_SEARCH;
	
	/**
	 * ID: 1723<br>
	 * Message: サポートに対する評価をして頂ければ幸いです。
	 */
	public static final SystemMessageId PLEASE_PROVIDE_PETITION_FEEDBACK;
	
	/**
	 * ID: 1724<br>
	 * Message: 戦闘中は召喚獣を戻せません。
	 */
	public static final SystemMessageId SERVITOR_NOT_RETURN_IN_BATTLE;
	
	/**
	 * ID: 1725<br>
	 * Message: $s1のレイド ポイントを獲得しました。
	 */
	public static final SystemMessageId EARNED_S1_RAID_POINTS;
	
	/**
	 * ID: 1726<br>
	 * Message: 有効期間が満了したので$s1が消えました。
	 */
	public static final SystemMessageId S1_PERIOD_EXPIRED_DISAPPEARED;
	
	/**
	 * ID: 1727<br>
	 * Message: $s1 から$s2パーティー ルームに招待されました。応じますか。
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_ROOM_CONFIRM;
	
	/**
	 * ID: 1728<br>
	 * Message: 相手がパーティ マッチの招待に応じませんでした。
	 */
	public static final SystemMessageId PARTY_MATCHING_REQUEST_NO_RESPONSE;
	
	/**
	 * ID: 1729<br>
	 * Message: 連合がテレポート中であるため、連合のパーティに加入できません。
	 */
	public static final SystemMessageId NOT_JOIN_CHANNEL_WHILE_TELEPORTING;
	
	/**
	 * ID: 1730<br>
	 * Message: 血盟アカデミーを創設するには、血盟のレベルが5以上でなければなりません。
	 */
	public static final SystemMessageId YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_CLAN_ACADEMY;
	
	/**
	 * ID: 1731<br>
	 * Message: 血盟主のみ血盟アカデミーを創設することができます。
	 */
	public static final SystemMessageId ONLY_LEADER_CAN_CREATE_ACADEMY;
	
	/**
	 * ID: 1732<br>
	 * Message: 血盟アカデミーを創設するにはアイテム「血の証」が必要です。
	 */
	public static final SystemMessageId NEED_BLOODMARK_FOR_ACADEMY;
	
	/**
	 * ID: 1733<br>
	 * Message: 血盟アカデミーを創設するにはアデナが足りません。
	 */
	public static final SystemMessageId NEED_ADENA_FOR_ACADEMY;
	
	/**
	 * ID: 1734<br>
	 * Message: 血盟アカデミーに加入するには、他の血盟に所属しておらず、2次転職を終えていないレベル40以下のキャラクターでなければなりません。
	 */
	public static final SystemMessageId ACADEMY_REQUIREMENTS;
	
	/**
	 * ID: 1735<br>
	 * Message: $s1 は血盟アカデミーの加入条件に合いません。
	 */
	public static final SystemMessageId S1_DOESNOT_MEET_REQUIREMENTS_TO_JOIN_ACADEMY;
	
	/**
	 * ID: 1736<br>
	 * Message: 血盟アカデミーの制限人数に達したため、アカデミー員の加入受け付けができません。
	 */
	public static final SystemMessageId ACADEMY_MAXIMUM;
	
	/**
	 * ID: 1737<br>
	 * Message: 現在所属している血盟の血盟アカデミーは開設されていません。血盟アカデミーを開設できます。
	 */
	public static final SystemMessageId CLAN_CAN_CREATE_ACADEMY;
	
	/**
	 * ID: 1738<br>
	 * Message: 現在所属している血盟の血盟アカデミーはすでに開設されています。
	 */
	public static final SystemMessageId CLAN_HAS_ALREADY_ESTABLISHED_A_CLAN_ACADEMY;
	
	/**
	 * ID: 1739<br>
	 * Message: 血盟アカデミーを創設しますか。
	 */
	public static final SystemMessageId CLAN_ACADEMY_CREATE_CONFIRM;
	
	/**
	 * ID: 1740<br>
	 * Message: 血盟アカデミー名を入力してください。
	 */
	public static final SystemMessageId ACADEMY_CREATE_ENTER_NAME;
	
	/**
	 * ID: 1741<br>
	 * Message: おめでとうございます！$s1血盟の血盟アカデミーが創設されました。
	 */
	public static final SystemMessageId THE_S1S_CLAN_ACADEMY_HAS_BEEN_CREATED;
	
	/**
	 * ID: 1742<br>
	 * Message: 血盟アカデミー加入を勧誘するメッセージを$s1に送ります。
	 */
	public static final SystemMessageId ACADEMY_INVITATION_SENT_TO_S1;
	
	/**
	 * ID: 1743<br>
	 * Message: 血盟アカデミーを創設するには、血盟レベルが5以上で血盟主がアイテム「血の証」XX個または一定量のアデナを支払わなければなりません。
	 */
	public static final SystemMessageId OPEN_ACADEMY_CONDITIONS;
	
	/**
	 * ID: 1744<br>
	 * Message: 相手が応答しなかったため、血盟アカデミー加入の勧誘がキャンセルされました。
	 */
	public static final SystemMessageId ACADEMY_JOIN_NO_RESPONSE;
	
	/**
	 * ID: 1745<br>
	 * Message: 相手が血盟アカデミー加入の勧誘を拒否しました。
	 */
	public static final SystemMessageId ACADEMY_JOIN_DECLINE;
	
	/**
	 * ID: 1746<br>
	 * Message: すでに血盟アカデミーに加入しています。
	 */
	public static final SystemMessageId ALREADY_JOINED_ACADEMY;
	
	/**
	 * ID: 1747<br>
	 * Message: $s1が$s2血盟所属の血盟アカデミー加入の勧誘をしてきました。招待に応じますか。
	 */
	public static final SystemMessageId JOIN_ACADEMY_REQUEST_BY_S1_FOR_CLAN_S2;
	
	/**
	 * ID: 1748<br>
	 * Message: 血盟アカデミー員$s1が2次転職に成功し血盟名声値$s2ポイントを獲得しました。
	 */
	public static final SystemMessageId CLAN_MEMBER_GRADUATED_FROM_ACADEMY;
	
	/**
	 * ID: 1749<br>
	 * Message: おめでとうございます！血盟アカデミーを卒業し、所属血盟から脱退します。卒業者はペナルティーなしで正式に血盟員として即加入できます。
	 */
	public static final SystemMessageId ACADEMY_MEMBERSHIP_TERMINATED;
	
	/**
	 * ID: 1750<br>
	 * Message: $c1 は参加条件に合いません。$s2の所有者はオリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_CANNOT_JOIN_OLYMPIAD_POSSESSING_S2;
	
	/**
	 * ID: 1751<br>
	 * Message: グランド マスターから記念アイテムを与えられました。
	 */
	public static final SystemMessageId GRAND_MASTER_COMMEMORATIVE_ITEM;
	
	/**
	 * ID: 1752<br>
	 * Message: 血盟アカデミー卒業者が血盟員として加入し、血盟名声値$s1ポイントを得ました。
	 */
	public static final SystemMessageId MEMBER_GRADUATED_EARNED_S1_REPU;
	
	/**
	 * ID: 1753<br>
	 * Message: 血盟アカデミー員には血盟主が設定した権限により該当権限は与えられません。
	 */
	public static final SystemMessageId CANT_TRANSFER_PRIVILEGE_TO_ACADEMY_MEMBER;
	
	/**
	 * ID: 1754<br>
	 * Message: 血盟アカデミー員には該当権限が与えられません。
	 */
	public static final SystemMessageId RIGHT_CANT_TRANSFERRED_TO_ACADEMY_MEMBER;
	
	/**
	 * ID: 1755<br>
	 * Message: 血盟員$s1の訓練生として$s2を指定しました。
	 */
	public static final SystemMessageId S2_HAS_BEEN_DESIGNATED_AS_APPRENTICE_OF_CLAN_MEMBER_S1;
	
	/**
	 * ID: 1756<br>
	 * Message: 血盟アカデミー訓練生$s1が接続しました。
	 */
	public static final SystemMessageId YOUR_APPRENTICE_S1_HAS_LOGGED_IN;
	
	/**
	 * ID: 1757<br>
	 * Message: 血盟アカデミー訓練生$c1が接続を中断しました。
	 */
	public static final SystemMessageId YOUR_APPRENTICE_C1_HAS_LOGGED_OUT;
	
	/**
	 * ID: 1758<br>
	 * Message: 血盟アカデミー後見人$c1が接続しました。
	 */
	public static final SystemMessageId YOUR_SPONSOR_C1_HAS_LOGGED_IN;
	
	/**
	 * ID: 1759<br>
	 * Message: 血盟アカデミー後見人$c1が接続を中断しました。
	 */
	public static final SystemMessageId YOUR_SPONSOR_C1_HAS_LOGGED_OUT;
	
	/**
	 * ID: 1760<br>
	 * Message: 血盟員$c1のタイトルが$s2に指定されました。
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_TITLE_CHANGED_TO_S2;
	
	/**
	 * ID: 1761<br>
	 * Message: 血盟員$c1の権限クラスが$s2に指定されました。
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_PRIVILEGE_CHANGED_TO_S2;
	
	/**
	 * ID: 1762<br>
	 * Message: 訓練生を解任する権限はありません。
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_THE_RIGHT_TO_DISMISS_AN_APPRENTICE;
	
	/**
	 * ID: 1763<br>
	 * Message: 血盟員$c1の訓練生$s2が削除されました。
	 */
	public static final SystemMessageId S2_CLAN_MEMBER_C1_APPRENTICE_HAS_BEEN_REMOVED;
	
	/**
	 * ID: 1764<br>
	 * Message: 血盟アカデミー員のみ装備できるアイテムです。
	 */
	public static final SystemMessageId EQUIP_ONLY_FOR_ACADEMY;
	
	/**
	 * ID: 1765<br>
	 * Message: 血盟アカデミーを卒業すると装備できません。
	 */
	public static final SystemMessageId EQUIP_NOT_FOR_GRADUATES;
	
	/**
	 * ID: 1766<br>
	 * Message: $c1に$s2として血盟加入申請を送ります。
	 */
	public static final SystemMessageId CLAN_JOIN_APPLICATION_SENT_TO_C1_IN_S2;
	
	/**
	 * ID: 1767<br>
	 * Message: $c1に血盟アカデミーへ加入申請を送ります。
	 */
	public static final SystemMessageId ACADEMY_JOIN_APPLICATION_SENT_TO_C1;
	
	/**
	 * ID: 1768<br>
	 * Message: $c1 から$s2 血盟の血盟アカデミーとして加入申請が送られました。加入しますか。
	 */
	public static final SystemMessageId JOIN_REQUEST_BY_C1_TO_CLAN_S2_ACADEMY;
	
	/**
	 * ID: 1769<br>
	 * Message: $c1 から$s2 血盟所属$s3 騎士団として加入招待を送られました。加入しますか。
	 */
	public static final SystemMessageId JOIN_REQUEST_BY_C1_TO_ORDER_OF_KNIGHTS_S3_UNDER_CLAN_S2;
	
	/**
	 * ID: 1770<br>
	 * Message: 血盟名声値が0ポイント以下となり制約を受けます。
	 */
	public static final SystemMessageId CLAN_REPU_0_MAY_FACE_PENALTIES;
	
	/**
	 * ID: 1771<br>
	 * Message: 血盟レベル5以上になり血盟名声値を蓄えることができます。
	 */
	public static final SystemMessageId CLAN_CAN_ACCUMULATE_CLAN_REPUTATION_POINTS;
	
	/**
	 * ID: 1772<br>
	 * Message: 所属血盟が攻城戦で敗北し相手の血盟に血盟名声値が$s1ポイント奪われました。
	 */
	public static final SystemMessageId CLAN_WAS_DEFEATED_IN_SIEGE_AND_LOST_S1_REPUTATION_POINTS;
	
	/**
	 * ID: 1773<br>
	 * Message: 所属血盟が攻城戦で勝利し相手の血盟名声値$s1ポイントを奪いました。
	 */
	public static final SystemMessageId CLAN_VICTORIOUS_IN_SIEGE_AND_GAINED_S1_REPUTATION_POINTS;
	
	/**
	 * ID: 1774<br>
	 * Message: 所属血盟が戦闘型アジトを新規に獲得して、血盟名声値を$s1ポイント手に入れました。
	 */
	public static final SystemMessageId CLAN_ACQUIRED_CONTESTED_CLAN_HALL_AND_S1_REPUTATION_POINTS;
	
	/**
	 * ID: 1775<br>
	 * Message: 所属血盟の血盟員$c1が闇の祭典1位パーティの一員として活躍し、血盟名声値$s2ポイントを手に入れました。
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_WAS_IN_HIGHEST_RANKED_PARTY_IN_FESTIVAL_OF_DARKNESS_AND_GAINED_S2_REPUTATION;
	
	/**
	 * ID: 1776<br>
	 * Message: 所属血盟の血盟員$c1 が英雄となり血盟名声値$s2ポイントを手に入れました。
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_BECAME_HERO_AND_GAINED_S2_REPUTATION_POINTS;
	
	/**
	 * ID: 1777<br>
	 * Message: 血盟クエストを無事クリアし、$s1の血盟名声値を獲得しました。
	 */
	public static final SystemMessageId CLAN_QUEST_COMPLETED_AND_S1_POINTS_GAINED;
	
	/**
	 * ID: 1778<br>
	 * Message: 相手の血盟へ戦闘型アジトを奪われ、所属血盟の血盟名声値$s1ポイントを奪われました。
	 */
	public static final SystemMessageId OPPOSING_CLAN_CAPTURED_CLAN_HALL_AND_YOUR_CLAN_LOSES_S1_POINTS;
	
	/**
	 * ID: 1779<br>
	 * Message: 戦闘型アジトを奪われ血盟名声値300ポイントを失いました。
	 */
	public static final SystemMessageId CLAN_LOST_CONTESTED_CLAN_HALL_AND_300_POINTS;
	
	/**
	 * ID: 1780<br>
	 * Message: 所属血盟が戦闘型アジトを争奪して相手の血盟から血盟名声値$s1ポイントを奪いました。
	 */
	public static final SystemMessageId CLAN_CAPTURED_CONTESTED_CLAN_HALL_AND_S1_POINTS_DEDUCTED_FROM_OPPONENT;
	
	/**
	 * ID: 1781<br>
	 * Message: 所属血盟の血盟名声値$s1ポイントを得ました。
	 */
	public static final SystemMessageId CLAN_ADDED_S1S_POINTS_TO_REPUTATION_SCORE;
	
	/**
	 * ID: 1782<br>
	 * Message: 所属血盟の血盟員$c1が殺されたので、相手の血盟に名声値を$s2ポイント奪われました。
	 */
	public static final SystemMessageId CLAN_MEMBER_C1_WAS_KILLED_AND_S2_POINTS_DEDUCTED_FROM_REPUTATION;
	
	/**
	 * ID: 1783<br>
	 * Message: 相手の血盟のプレイヤーを倒し所属血盟の血盟名声値$s1ポイントを奪いました。
	 */
	public static final SystemMessageId FOR_KILLING_OPPOSING_MEMBER_S1_POINTS_WERE_DEDUCTED_FROM_OPPONENTS;
	
	/**
	 * ID: 1784<br>
	 * Message: 所属血盟が守城に失敗し相手の血盟に血盟名声値$s1ポイントを奪われました。
	 */
	public static final SystemMessageId YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST_AND_ADDED_TO_OPPONENT;
	
	/**
	 * ID: 1785<br>
	 * Message: 所属血盟が保有している城が初期化され$s1ポイントを失いました。
	 */
	public static final SystemMessageId YOUR_CLAN_HAS_BEEN_INITIALIZED_AND_S1_POINTS_LOST;
	
	/**
	 * ID: 1786<br>
	 * Message: 所属している血盟が守城に失敗し血盟名声値$s1ポイントを失いました。
	 */
	public static final SystemMessageId YOUR_CLAN_FAILED_TO_DEFEND_CASTLE_AND_S1_POINTS_LOST;
	
	/**
	 * ID: 1787<br>
	 * Message: 血盟名声値$s1ポイントが消耗されました。
	 */
	public static final SystemMessageId S1_DEDUCTED_FROM_CLAN_REP;
	
	/**
	 * ID: 1788<br>
	 * Message: 血盟スキル$s1を手に入れました。
	 */
	public static final SystemMessageId CLAN_SKILL_S1_ADDED;
	
	/**
	 * ID: 1789<br>
	 * Message: 血盟名声値が0以下になったので、血盟スキルが非アクティブ化します。
	 */
	public static final SystemMessageId REPUTATION_POINTS_0_OR_LOWER_CLAN_SKILLS_DEACTIVATED;
	
	/**
	 * ID: 1790<br>
	 * Message: 条件に満たないため、血盟のレベル アップはできません。
	 */
	public static final SystemMessageId FAILED_TO_INCREASE_CLAN_LEVEL;
	
	/**
	 * ID: 1791<br>
	 * Message: 条件に満たないため、単位部隊の創設ができません。
	 */
	public static final SystemMessageId YOU_DO_NOT_MEET_CRITERIA_IN_ORDER_TO_CREATE_A_MILITARY_UNIT;
	
	/**
	 * ID: 1792<br>
	 * Message: 創設する騎士団の管理者を指定してください。
	 */
	public static final SystemMessageId ASSIGN_MANAGER_FOR_ORDER_OF_KNIGHTS;
	
	/**
	 * ID: 1793<br>
	 * Message: $c1 が$s2の団長に任命されました。
	 */
	public static final SystemMessageId C1_HAS_BEEN_SELECTED_AS_CAPTAIN_OF_S2;
	
	/**
	 * ID: 1794<br>
	 * Message: $s1騎士団が創設されました。
	 */
	public static final SystemMessageId THE_KNIGHTS_OF_S1_HAVE_BEEN_CREATED;
	
	/**
	 * ID: 1795<br>
	 * Message: $s1近衛隊が創設されました。
	 */
	public static final SystemMessageId THE_ROYAL_GUARD_OF_S1_HAVE_BEEN_CREATED;
	
	/**
	 * ID: 1796<br>
	 * Message: お客様のアカウントは、不正アカウント盗用や他人に被害を与えるゲーム プレイなどが認められたため、一時的にご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートに直接お問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE17;
	
	/**
	 * ID: 1797<br>
	 * Message: $c1のレベルが$s2に指定されました。
	 */
	public static final SystemMessageId C1_PROMOTED_TO_S2;
	
	/**
	 * ID: 1798<br>
	 * Message: 血盟主の権限を$c1 に委譲しました。
	 */
	public static final SystemMessageId CLAN_LEADER_PRIVILEGES_HAVE_BEEN_TRANSFERRED_TO_C1;
	
	/**
	 * ID: 1799<br>
	 * Message: 以前のBOT使用者をチェック中です。しばらくしてからお使いください。
	 */
	public static final SystemMessageId SEARCHING_FOR_BOT_USERS_TRY_AGAIN_LATER;
	
	/**
	 * ID: 1800<br>
	 * Message: ユーザー$c1 はBOT使用歴があります。
	 */
	public static final SystemMessageId C1_HISTORY_USING_BOT;
	
	/**
	 * ID: 1801<br>
	 * Message: 販売できませんでした。
	 */
	public static final SystemMessageId SELL_ATTEMPT_FAILED;
	
	/**
	 * ID: 1802<br>
	 * Message: 取引に失敗しました。
	 */
	public static final SystemMessageId TRADE_ATTEMPT_FAILED;
	
	/**
	 * ID: 1803<br>
	 * Message: 試合締め切り10分前からは参加申し込みができません。
	 */
	public static final SystemMessageId GAME_REQUEST_CANNOT_BE_MADE;
	
	/**
	 * ID: 1804<br>
	 * Message: お客様のアカウントは、現金、アカウント取引などの行為が認められたため、7日間、ご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE18;
	
	/**
	 * ID: 1805<br>
	 * Message: お客様のアカウントは、現金、アカウント取引などの行為が2度認められたため、30日間、ご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE19;
	
	/**
	 * ID: 1806<br>
	 * Message: お客様のアカウントは、現金、アカウント取引などの行為が3度以上認められたため、無期限でご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE20;
	
	/**
	 * ID: 1807<br>
	 * Message: お客様のアカウントは、現金取引の事実が認められたため、30日間、ご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE21;
	
	/**
	 * ID: 1808<br>
	 * Message: お客様のアカウントは、現金、アカウント取引などの行為が2度認められたため、無期限でご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE22;
	
	/**
	 * ID: 1809<br>
	 * Message: お客様のアカウントは、ご本人様確認の手続きが必要です。リネージュII 公式サイト（http://lineage2.plaync.jp/）より手続きを行ってください。
	 */
	public static final SystemMessageId ACCOUNT_MUST_VERIFIED;
	
	/**
	 * ID: 1810<br>
	 * Message: 招待拒否状態になりました。
	 */
	public static final SystemMessageId REFUSE_INVITATION_ACTIVATED;
	
	/**
	 * ID: 1812<br>
	 * Message: 現在、招待拒否状態なので招待できません。
	 */
	public static final SystemMessageId REFUSE_INVITATION_CURRENTLY_ACTIVE;
	
	/**
	 * ID: 1813<br>
	 * Message: $s1の使用可能時間は残り$s2時間です。
	 */
	public static final SystemMessageId THERE_IS_S1_HOUR_AND_S2_MINUTE_LEFT_OF_THE_FIXED_USAGE_TIME;
	
	/**
	 * ID: 1814<br>
	 * Message: $s1の使用可能時間は残り$s2分です。
	 */
	public static final SystemMessageId S2_MINUTE_OF_USAGE_TIME_ARE_LEFT_FOR_S1;
	
	/**
	 * ID: 1815<br>
	 * Message: $s1エリアに$s2が落ちました。
	 */
	public static final SystemMessageId S2_WAS_DROPPED_IN_THE_S1_REGION;
	
	/**
	 * ID: 1816<br>
	 * Message: $s1エリアに$s2の所有者が現れました。
	 */
	public static final SystemMessageId THE_OWNER_OF_S2_HAS_APPEARED_IN_THE_S1_REGION;
	
	/**
	 * ID: 1817<br>
	 * Message: $s1エリアに$s2の所有者がログインしました。
	 */
	public static final SystemMessageId S2_OWNER_HAS_LOGGED_INTO_THE_S1_REGION;
	
	/**
	 * ID: 1818<br>
	 * Message: $s1が消滅しました。
	 */
	public static final SystemMessageId S1_HAS_DISAPPEARED;
	
	/**
	 * ID: 1819<br>
	 * Message: $s1エリアにいる何者かから$s2の邪悪な気配を感じます。
	 */
	public static final SystemMessageId EVIL_FROM_S2_IN_S1;
	
	/**
	 * ID: 1820<br>
	 * Message: $s1は現在眠っています。
	 */
	public static final SystemMessageId S1_CURRENTLY_SLEEP;
	
	/**
	 * ID: 1821<br>
	 * Message: $s1エリアで$s2の邪悪な気配を感じます。
	 */
	public static final SystemMessageId S2_EVIL_PRESENCE_FELT_IN_S1;
	
	/**
	 * ID: 1822<br>
	 * Message: $s1は封印されています。
	 */
	public static final SystemMessageId S1_SEALED;
	
	/**
	 * ID: 1823<br>
	 * Message: アジト戦登録は締め切られました。
	 */
	public static final SystemMessageId CLANHALL_WAR_REGISTRATION_PERIOD_ENDED;
	
	/**
	 * ID: 1824<br>
	 * Message: アジト戦に登録しました。アジトの東にある競技場へ移動し、準備してください。
	 */
	public static final SystemMessageId REGISTERED_FOR_CLANHALL_WAR;
	
	/**
	 * ID: 1825<br>
	 * Message: アジト戦に登録できませんでした。次の機会にお越しください。
	 */
	public static final SystemMessageId CLANHALL_WAR_REGISTRATION_FAILED;
	
	/**
	 * ID: 1826<br>
	 * Message: 開始まであと$s1分です。参加者は急いでアジトの東にある競技場に移動してください。
	 */
	public static final SystemMessageId CLANHALL_WAR_BEGINS_IN_S1_MINUTES;
	
	/**
	 * ID: 1827<br>
	 * Message: 開始まであと$s1分です。参加者は競技場の中に入ってください。
	 */
	public static final SystemMessageId CLANHALL_WAR_BEGINS_IN_S1_MINUTES_ENTER_NOW;
	
	/**
	 * ID: 1828<br>
	 * Message: 開始まであと$s1秒です。
	 */
	public static final SystemMessageId CLANHALL_WAR_BEGINS_IN_S1_SECONDS;
	
	/**
	 * ID: 1829<br>
	 * Message: 連合の最大パーティ数を越えたので、新しいパーティを加えられません。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_FULL;
	
	/**
	 * ID: 1830<br>
	 * Message: $c1 をパーティ ルームへ招待できません。待機者リストを更新してください。
	 */
	public static final SystemMessageId C1_NOT_ALLOWED_INVITE_TO_PARTY_ROOM;
	
	/**
	 * ID: 1831<br>
	 * Message: $c1 はパーティ ルームの条件に合いません。待機者リストを更新してください。
	 */
	public static final SystemMessageId C1_NOT_MEET_CONDITIONS_FOR_PARTY_ROOM;
	
	/**
	 * ID: 1832<br>
	 * Message: パーティ ルームへはルームリーダーのみ招待できます。
	 */
	public static final SystemMessageId ONLY_ROOM_LEADER_CAN_INVITE;
	
	/**
	 * ID: 1833<br>
	 * Message: $s1を全部捨てます。よろしいですか。
	 */
	public static final SystemMessageId CONFIRM_DROP_ALL_OF_S1;
	
	/**
	 * ID: 1834<br>
	 * Message: パーティ ルームの定員が満員のため、これ以上招待できません。
	 */
	public static final SystemMessageId PARTY_ROOM_FULL;
	
	/**
	 * ID: 1835<br>
	 * Message: $s1の定員が満員のため、新たな血盟員を加入させることはできません。
	 */
	public static final SystemMessageId S1_CLAN_IS_FULL;
	
	/**
	 * ID: 1836<br>
	 * Message: 2次転職を終えているので血盟アカデミーの加入条件に合いません。
	 */
	public static final SystemMessageId CANNOT_JOIN_ACADEMY_AFTER_2ND_OCCUPATION;
	
	/**
	 * ID: 1837<br>
	 * Message: $c1 が$s2血盟所属の$s3近衛隊への加入を勧めています。加入しますか。
	 */
	public static final SystemMessageId C1_SENT_INVITATION_TO_ROYAL_GUARD_S3_OF_CLAN_S2;
	
	/**
	 * ID: 1838<br>
	 * Message: 1. クーポンはキャラクターあたり1枚のみ使用できます。
	 */
	public static final SystemMessageId COUPON_ONCE_PER_CHARACTER;
	
	/**
	 * ID: 1839<br>
	 * Message: 2. 使用済みのシリアル番号は再び使用できません。
	 */
	public static final SystemMessageId SERIAL_MAY_USED_ONCE;
	
	/**
	 * ID: 1840<br>
	 * Message: 3. シリアル番号の入力を5回以上間違えた場合、一定時間\n 経過後に再度入力を試みてください。
	 */
	public static final SystemMessageId SERIAL_INPUT_INCORRECT;
	
	/**
	 * ID: 1841<br>
	 * Message: 参加申し込みの血盟数が足りないため、アジト戦はキャンセルされました。
	 */
	public static final SystemMessageId CLANHALL_WAR_CANCELLED;
	
	/**
	 * ID: 1842<br>
	 * Message: $c1 が$s2から召喚を申し込みました。召喚に応じますか。
	 */
	public static final SystemMessageId C1_WISHES_TO_SUMMON_YOU_FROM_S2_DO_YOU_ACCEPT;
	
	/**
	 * ID: 1843<br>
	 * Message: $c1 は戦闘中のため召喚できません。
	 */
	public static final SystemMessageId C1_IS_ENGAGED_IN_COMBAT_AND_CANNOT_BE_SUMMONED;
	
	/**
	 * ID: 1844<br>
	 * Message: $c1 は現在死亡状態のため召喚できません。
	 */
	public static final SystemMessageId C1_IS_DEAD_AT_THE_MOMENT_AND_CANNOT_BE_SUMMONED;
	
	/**
	 * ID: 1845<br>
	 * Message: 英雄用武器は破壊できません。
	 */
	public static final SystemMessageId HERO_WEAPONS_CANT_DESTROYED;
	
	/**
	 * ID: 1846<br>
	 * Message: 乗り物との距離が離れ過ぎているので、搭乗できません。
	 */
	public static final SystemMessageId TOO_FAR_AWAY_FROM_FENRIR_TO_MOUNT;
	
	/**
	 * ID: 1847<br>
	 * Message: 長さ$s1の魚が釣れました。
	 */
	public static final SystemMessageId CAUGHT_FISH_S1_LENGTH;
	
	/**
	 * ID: 1848<br>
	 * Message: 大物を釣ったのでランキングに登録されました。
	 */
	public static final SystemMessageId REGISTERED_IN_FISH_SIZE_RANKING;
	
	/**
	 * ID: 1849<br>
	 * Message: $s1を全部捨てます。よろしいですか。
	 */
	public static final SystemMessageId CONFIRM_DISCARD_ALL_OF_S1;
	
	/**
	 * ID: 1850<br>
	 * Message: 騎士団長に任命できません。
	 */
	public static final SystemMessageId CAPTAIN_OF_ORDER_OF_KNIGHTS_CANNOT_BE_APPOINTED;
	
	/**
	 * ID: 1851<br>
	 * Message: 近衛隊長に任命できません。
	 */
	public static final SystemMessageId CAPTAIN_OF_ROYAL_GUARD_CANNOT_BE_APPOINTED;
	
	/**
	 * ID: 1852<br>
	 * Message: 血盟名声値が足りないのでスキルを習得できませんでした。
	 */
	public static final SystemMessageId ACQUIRE_SKILL_FAILED_BAD_CLAN_REP_SCORE;
	
	/**
	 * ID: 1853<br>
	 * Message: 同じ種類の数量性アイテムを同時にトレードできません。
	 */
	public static final SystemMessageId CANT_EXCHANGE_QUANTITY_ITEMS_OF_SAME_TYPE;
	
	/**
	 * ID: 1854<br>
	 * Message: アイテム変換に成功しました。
	 */
	public static final SystemMessageId ITEM_CONVERTED_SUCCESSFULLY;
	
	/**
	 * ID: 1855<br>
	 * Message: 他の部隊と名称が重複します。別の名前を入力してください。
	 */
	public static final SystemMessageId ANOTHER_MILITARY_UNIT_IS_ALREADY_USING_THAT_NAME;
	
	/**
	 * ID: 1856<br>
	 * Message: 相手が$s1の所有者になったので、オリンピアードがキャンセルされました。
	 */
	public static final SystemMessageId OPPONENT_POSSESSES_S1_OLYMPIAD_CANCELLED;
	
	/**
	 * ID: 1857<br>
	 * Message: $c1 は$s2の所有者になっているため、オリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_OWNS_S2_AND_CANNOT_PARTICIPATE_IN_OLYMPIAD;
	
	/**
	 * ID: 1858<br>
	 * Message: $c1 は死亡状態であるため、オリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_OLYMPIAD_WHILE_DEAD;
	
	/**
	 * ID: 1859<br>
	 * Message: 一度に移動可能な数量を超えました。
	 */
	public static final SystemMessageId EXCEEDED_QUANTITY_FOR_MOVED;
	
	/**
	 * ID: 1860<br>
	 * Message: 血盟名声値が足りません。
	 */
	public static final SystemMessageId THE_CLAN_REPUTATION_SCORE_IS_TOO_LOW;
	
	/**
	 * ID: 1861<br>
	 * Message: 血盟エンサインを削除しました。
	 */
	public static final SystemMessageId CLAN_CREST_HAS_BEEN_DELETED;
	
	/**
	 * ID: 1862<br>
	 * Message: 血盟名声値が0以上になったので血盟スキルがアクティブ化します。
	 */
	public static final SystemMessageId CLAN_SKILLS_WILL_BE_ACTIVATED_SINCE_REPUTATION_IS_0_OR_HIGHER;
	
	/**
	 * ID: 1863<br>
	 * Message: $c1 がアイテムを購入したので、血盟名声値が$s2差し引かれます。
	 */
	public static final SystemMessageId C1_PURCHASED_CLAN_ITEM_REDUCING_S2_REPU_POINTS;
	
	/**
	 * ID: 1864<br>
	 * Message: ペットまたは召喚獣が異常状態になり、命令を聞きません。
	 */
	public static final SystemMessageId PET_REFUSING_ORDER;
	
	/**
	 * ID: 1865<br>
	 * Message: ペットまたは召喚獣が異常状態です。
	 */
	public static final SystemMessageId PET_IN_STATE_OF_DISTRESS;
	
	/**
	 * ID: 1866<br>
	 * Message: $s1のMPが消えました。
	 */
	public static final SystemMessageId MP_REDUCED_BY_S1;
	
	/**
	 * ID: 1867<br>
	 * Message: 相手のMP $s1ほどを消しました。
	 */
	public static final SystemMessageId YOUR_OPPONENTS_MP_WAS_REDUCED_BY_S1;
	
	/**
	 * ID: 1868<br>
	 * Message: アイテム使用中はトレードできません。
	 */
	public static final SystemMessageId CANNOT_EXCHANCE_USED_ITEM;
	
	/**
	 * ID: 1869<br>
	 * Message: $c1が指揮する連合のマスター パーティに、アイテム分配の権限が与えられました。
	 */
	public static final SystemMessageId C1_GRANTED_MASTER_PARTY_LOOTING_RIGHTS;
	
	/**
	 * ID: 1870<br>
	 * Message: すでにアイテム分配の権限が与えられた連合チャンネルが存在します。
	 */
	public static final SystemMessageId COMMAND_CHANNEL_WITH_LOOTING_RIGHTS_EXISTS;
	
	/**
	 * ID: 1871<br>
	 * Message: $c1を血盟から除名しますか。
	 */
	public static final SystemMessageId CONFIRM_DISMISS_C1_FROM_CLAN;
	
	/**
	 * ID: 1872<br>
	 * Message: 個人ユーザーの残り時間はあと$s1時間 $s2分です。
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT;
	
	/**
	 * ID: 1873<br>
	 * Message: ネットカフェ定量の残り時間はあと$s1時間 $s2分です。
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_LEFT_FOR_THIS_PCCAFE;
	
	/**
	 * ID: 1874<br>
	 * Message: 個人ユーザーの残り時間はあと$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_FOR_THIS_USER;
	
	/**
	 * ID: 1875<br>
	 * Message: ネットカフェ定量の残り時間はあと$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_FOR_THIS_PCCAFE;
	
	/**
	 * ID: 1876<br>
	 * Message: $s1血盟から脱退しますか。
	 */
	public static final SystemMessageId CONFIRM_LEAVE_S1_CLAN;
	
	/**
	 * ID: 1877<br>
	 * Message: 終了まであと$s1分です。
	 */
	public static final SystemMessageId GAME_WILL_END_IN_S1_MINUTES;
	
	/**
	 * ID: 1878<br>
	 * Message: 終了まであと$s1秒です。
	 */
	public static final SystemMessageId GAME_WILL_END_IN_S1_SECONDS;
	
	/**
	 * ID: 1879<br>
	 * Message: $s1分後、競技場の外にテレポートします。
	 */
	public static final SystemMessageId IN_S1_MINUTES_TELEPORTED_OUTSIDE_OF_GAME_ARENA;
	
	/**
	 * ID: 1880<br>
	 * Message: $s1秒後、競技場の外にテレポートします。
	 */
	public static final SystemMessageId IN_S1_SECONDS_TELEPORTED_OUTSIDE_OF_GAME_ARENA;
	
	/**
	 * ID: 1881<br>
	 * Message: $s1秒後に予選がスタートします。準備してください。
	 */
	public static final SystemMessageId PRELIMINARY_MATCH_BEGIN_IN_S1_SECONDS;
	
	/**
	 * ID: 1882<br>
	 * Message: 現在このサーバーにはキャラクターを生成できません。
	 */
	public static final SystemMessageId CHARACTERS_NOT_CREATED_FROM_THIS_SERVER;
	
	/**
	 * ID: 1883<br>
	 * Message: 所有もしくは入札したものがありません。
	 */
	public static final SystemMessageId NO_OFFERINGS_OWN_OR_MADE_BID_FOR;
	
	/**
	 * ID: 1884<br>
	 * Message: ネットカフェ クーポンのシリアル番号を入力してください。
	 */
	public static final SystemMessageId ENTER_PCROOM_SERIAL_NUMBER;
	
	/**
	 * ID: 1885<br>
	 * Message: シリアル番号を入力できません。$s1分後に再度試みてください。
	 */
	public static final SystemMessageId SERIAL_NUMBER_CANT_ENTERED;
	
	/**
	 * ID: 1886<br>
	 * Message: すでに使用されたシリアル番号です。
	 */
	public static final SystemMessageId SERIAL_NUMBER_ALREADY_USED;
	
	/**
	 * ID: 1887<br>
	 * Message: シリアル番号が正しくありません。$s1回入力に失敗したので、あと$s2回のチャンスがあります。
	 */
	public static final SystemMessageId SERIAL_NUMBER_ENTERING_FAILED;
	
	/**
	 * ID: 1888<br>
	 * Message: シリアル番号が正しくありません。5回入力に失敗しました。4時間後に再度試みてください。
	 */
	public static final SystemMessageId SERIAL_NUMBER_ENTERING_FAILED_5_TIMES;
	
	/**
	 * ID: 1889<br>
	 * Message: おめでとうございます。$s1を受け取りました。
	 */
	public static final SystemMessageId CONGRATULATIONS_RECEIVED_S1;
	
	/**
	 * ID: 1890<br>
	 * Message: このクーポンはすでに使用済みなのでシリアル番号を入力できません。
	 */
	public static final SystemMessageId ALREADY_USED_COUPON_NOT_USE_SERIAL_NUMBER;
	
	/**
	 * ID: 1891<br>
	 * Message: 個人商店または個人工房中はアイテムを使用できません。
	 */
	public static final SystemMessageId NOT_USE_ITEMS_IN_PRIVATE_STORE;
	
	/**
	 * ID: 1892<br>
	 * Message: 以前のバージョンのリプレイ ファイルは再生できません。
	 */
	public static final SystemMessageId REPLAY_FILE_PREVIOUS_VERSION_CANT_PLAYED;
	
	/**
	 * ID: 1893<br>
	 * Message: 再生できないファイルです。
	 */
	public static final SystemMessageId FILE_CANT_REPLAYED;
	
	/**
	 * ID: 1894<br>
	 * Message: 許容重量を超えるため、サブ クラスの追加や変更ができません。
	 */
	public static final SystemMessageId NOT_SUBCLASS_WHILE_OVERWEIGHT;
	
	/**
	 * ID: 1895<br>
	 * Message: $c1 は現在召喚できない地域にいます。
	 */
	public static final SystemMessageId C1_IN_SUMMON_BLOCKING_AREA;
	
	/**
	 * ID: 1896<br>
	 * Message: $c1 はすでに召喚中です。
	 */
	public static final SystemMessageId C1_ALREADY_SUMMONED;
	
	/**
	 * ID: 1897<br>
	 * Message: 召喚に必要なアイテム$s1がありません。
	 */
	public static final SystemMessageId S1_REQUIRED_FOR_SUMMONING;
	
	/**
	 * ID: 1898<br>
	 * Message: $c1 はトレードか個人商店を行っているため召喚できません。
	 */
	public static final SystemMessageId C1_CURRENTLY_TRADING_OR_OPERATING_PRIVATE_STORE_AND_CANNOT_BE_SUMMONED;
	
	/**
	 * ID: 1899<br>
	 * Message: 現在他人を召喚できない場所にいるため、相手を召喚できません。
	 */
	public static final SystemMessageId YOUR_TARGET_IS_IN_AN_AREA_WHICH_BLOCKS_SUMMONING;
	
	/**
	 * ID: 1900<br>
	 * Message: $c1 がパーティ ルームに入室しました。
	 */
	public static final SystemMessageId C1_ENTERED_PARTY_ROOM;
	
	/**
	 * ID: 1901<br>
	 * Message: $s1 から$s2パーティー ルームに招待されました。
	 */
	public static final SystemMessageId C1_INVITED_YOU_TO_PARTY_ROOM;
	
	/**
	 * ID: 1902<br>
	 * Message: アイテムのグレードが一致しないため使用できません。
	 */
	public static final SystemMessageId INCOMPATIBLE_ITEM_GRADE;
	
	/**
	 * ID: 1903<br>
	 * Message: NCOTPにお申し込みの場合は携帯電話の\nNCOTPを行い表示された\nNCOTP パスワードを1分以内に入力し、\nお申し込みでない場合は空欄のまま\nログインを行ってください。
	 */
	public static final SystemMessageId NCOTP;
	
	/**
	 * ID: 1904<br>
	 * Message: 召喚獣やペットを召喚した状態からは、サブ クラスの追加や変更ができません。
	 */
	public static final SystemMessageId CANT_SUBCLASS_WITH_SUMMONED_SERVITOR;
	
	/**
	 * ID: 1905<br>
	 * Message: $s1所属の$c2を$s3所属の$c4に交替します。
	 */
	public static final SystemMessageId S2_OF_S1_WILL_REPLACED_WITH_S4_OF_S3;
	
	/**
	 * ID: 1906<br>
	 * Message: 所属変更する部隊を選択します。
	 */
	public static final SystemMessageId SELECT_COMBAT_UNIT;
	
	/**
	 * ID: 1907<br>
	 * Message: 選択対象を他の人と交替する場合\n交替する対象を選択します。
	 */
	public static final SystemMessageId SELECT_CHARACTER_WHO_WILL;
	
	/**
	 * ID: 1908<br>
	 * Message: $c1 は召喚できない状態です。
	 */
	public static final SystemMessageId C1_STATE_FORBIDS_SUMMONING;
	
	/**
	 * ID: 1909<br>
	 * Message: ==<最近一週間の血盟アカデミー卒業者>==
	 */
	public static final SystemMessageId ACADEMY_LIST_HEADER;
	
	/**
	 * ID: 1910<br>
	 * Message: 卒業者：$c1
	 */
	public static final SystemMessageId GRADUATES_C1;
	
	/**
	 * ID: 1911<br>
	 * Message: オリンピアードに参加中のユーザーは召喚できません。
	 */
	public static final SystemMessageId YOU_CANNOT_SUMMON_PLAYERS_WHO_ARE_IN_OLYMPIAD;
	
	/**
	 * ID: 1912<br>
	 * Message: NCOTPにお申し込みの方のみ入力。
	 */
	public static final SystemMessageId NCOTP2;
	
	/**
	 * ID: 1913<br>
	 * Message: $s1の再使用時間は残り$s2分です。
	 */
	public static final SystemMessageId TIME_FOR_S1_IS_S2_MINUTES_REMAINING;
	
	/**
	 * ID: 1914<br>
	 * Message: $s1の再使用時間は残り$s2秒です。
	 */
	public static final SystemMessageId TIME_FOR_S1_IS_S2_SECONDS_REMAINING;
	
	/**
	 * ID: 1915<br>
	 * Message: 競技終了時間まで残り$s1秒です。
	 */
	public static final SystemMessageId GAME_ENDS_IN_S1_SECONDS;
	
	/**
	 * ID: 1916<br>
	 * Message: 死がもたらす傷レベル$s1が適用されます。
	 */
	public static final SystemMessageId DEATH_PENALTY_LEVEL_S1_ADDED;
	
	/**
	 * ID: 1917<br>
	 * Message: 死がもたらす傷が消えました。
	 */
	public static final SystemMessageId DEATH_PENALTY_LIFTED;
	
	/**
	 * ID: 1918<br>
	 * Message: ペットのレベルが高過ぎるため、コントロールできません。
	 */
	public static final SystemMessageId PET_TOO_HIGH_TO_CONTROL;
	
	/**
	 * ID: 1919<br>
	 * Message: オリンピアードの参加登録が締め切られました。
	 */
	public static final SystemMessageId OLYMPIAD_REGISTRATION_PERIOD_ENDED;
	
	/**
	 * ID: 1920<br>
	 * Message: アカウントは休眠アカウント状態です。一定期間ゲームに接続しない場合は休眠アカウント状態となり、公式サイト（http://lineage2.plaync.jp/）でゲーム接続が可能な状態に変更できます。
	 */
	public static final SystemMessageId ACCOUNT_INACTIVITY;
	
	/**
	 * ID: 1921<br>
	 * Message: $s1が殺戮を行ってから$s2時間$s3分が経過しました。
	 */
	public static final SystemMessageId S2_HOURS_S3_MINUTES_SINCE_S1_KILLED;
	
	/**
	 * ID: 1922<br>
	 * Message: $s1が丸一日殺戮を行わず、消滅しました。
	 */
	public static final SystemMessageId S1_FAILED_KILLING_EXPIRED;
	
	/**
	 * ID: 1923<br>
	 * Message: 宮廷魔術師：クラン ゲートが生成されました！
	 */
	public static final SystemMessageId COURT_MAGICIAN_CREATED_PORTAL;
	
	/**
	 * ID: 1924<br>
	 * Message: 現在地： $s1、$s2、$s3 (太古の島付近)
	 */
	public static final SystemMessageId LOC_PRIMEVAL_ISLE_S1_S2_S3;
	
	/**
	 * ID: 1925<br>
	 * Message: 戦乱の封印の影響により召喚できません。
	 */
	public static final SystemMessageId SEAL_OF_STRIFE_FORBIDS_SUMMONING;
	
	/**
	 * ID: 1926<br>
	 * Message: 決闘を申し込む相手がいません。
	 */
	public static final SystemMessageId THERE_IS_NO_OPPONENT_TO_RECEIVE_YOUR_CHALLENGE_FOR_A_DUEL;
	
	/**
	 * ID: 1927<br>
	 * Message: $c1 に1対1での決闘を申し込みます。
	 */
	public static final SystemMessageId C1_HAS_BEEN_CHALLENGED_TO_A_DUEL;
	
	/**
	 * ID: 1928<br>
	 * Message: $c1 のパーティにパーティ同士での決闘を申し込みます。
	 */
	public static final SystemMessageId C1_PARTY_HAS_BEEN_CHALLENGED_TO_A_DUEL;
	
	/**
	 * ID: 1929<br>
	 * Message: $c1 が1対1での決闘を受け入れました。しばらくすると決闘が始まります。
	 */
	public static final SystemMessageId C1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1930<br>
	 * Message: $c1 からの1対1での決闘の申し込みを受け入れました。しばらくすると決闘が始まります。
	 */
	public static final SystemMessageId YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1931<br>
	 * Message: $c1 が1対1での決闘を拒否しました。
	 */
	public static final SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL;
	
	/**
	 * ID: 1932<br>
	 * Message: $c1 が決闘の申し込みを拒否しました。
	 */
	public static final SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL2;
	
	/**
	 * ID: 1933<br>
	 * Message: $c1 のパーティからのパーティ同士での決闘の申し込みを受け入れました。しばらくすると決闘が始まります。
	 */
	public static final SystemMessageId YOU_HAVE_ACCEPTED_C1_CHALLENGE_TO_A_PARTY_DUEL_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1934<br>
	 * Message: $c1 がパーティ同士での決闘を受け入れました。しばらくすると決闘が始まります。
	 */
	public static final SystemMessageId S1_HAS_ACCEPTED_YOUR_CHALLENGE_TO_DUEL_AGAINST_THEIR_PARTY_THE_DUEL_WILL_BEGIN_IN_A_FEW_MOMENTS;
	
	/**
	 * ID: 1935<br>
	 * Message: $c1 がパーティ同士での決闘を拒否しました。
	 */
	public static final SystemMessageId C1_HAS_DECLINED_YOUR_CHALLENGE_TO_A_PARTY_DUEL;
	
	/**
	 * ID: 1936<br>
	 * Message: 相手のパーティが決闘の申し込みを拒否しました。
	 */
	public static final SystemMessageId THE_OPPOSING_PARTY_HAS_DECLINED_YOUR_CHALLENGE_TO_A_DUEL;
	
	/**
	 * ID: 1937<br>
	 * Message: 相手がパーティ状態ではないため、パーティ同士での決闘の申し込みができません。
	 */
	public static final SystemMessageId SINCE_THE_PERSON_YOU_CHALLENGED_IS_NOT_CURRENTLY_IN_A_PARTY_THEY_CANNOT_DUEL_AGAINST_YOUR_PARTY;
	
	/**
	 * ID: 1938<br>
	 * Message: $c1 が1対1での決闘を申し込みました。
	 */
	public static final SystemMessageId C1_HAS_CHALLENGED_YOU_TO_A_DUEL;
	
	/**
	 * ID: 1939<br>
	 * Message: $c1 のパーティがパーティ同士での決闘を申し込みました。
	 */
	public static final SystemMessageId C1_PARTY_HAS_CHALLENGED_YOUR_PARTY_TO_A_DUEL;
	
	/**
	 * ID: 1940<br>
	 * Message: 条件が合わないため、決闘を申し込めません。
	 */
	public static final SystemMessageId YOU_ARE_UNABLE_TO_REQUEST_A_DUEL_AT_THIS_TIME;
	
	/**
	 * ID: 1941<br>
	 * Message: 決闘を行える場所ではありません。
	 */
	public static final SystemMessageId NO_PLACE_FOR_DUEL;
	
	/**
	 * ID: 1942<br>
	 * Message: 相手のパーティが決闘を行える状態ではありません。
	 */
	public static final SystemMessageId THE_OPPOSING_PARTY_IS_CURRENTLY_UNABLE_TO_ACCEPT_A_CHALLENGE_TO_A_DUEL;
	
	/**
	 * ID: 1943<br>
	 * Message: 相手のパーティが決闘を行える場所にいません。
	 */
	public static final SystemMessageId THE_OPPOSING_PARTY_IS_AT_BAD_LOCATION_FOR_A_DUEL;
	
	/**
	 * ID: 1944<br>
	 * Message: しばらくすると決闘場に移動します。
	 */
	public static final SystemMessageId IN_A_MOMENT_YOU_WILL_BE_TRANSPORTED_TO_THE_SITE_WHERE_THE_DUEL_WILL_TAKE_PLACE;
	
	/**
	 * ID: 1945<br>
	 * Message: $s1秒後に決闘が始まります。
	 */
	public static final SystemMessageId THE_DUEL_WILL_BEGIN_IN_S1_SECONDS;
	
	/**
	 * ID: 1946<br>
	 * Message: $c1 が1対1での決闘を申し込みました。受け入れますか。
	 */
	public static final SystemMessageId C1_CHALLENGED_YOU_TO_A_DUEL;
	
	/**
	 * ID: 1947<br>
	 * Message: $c1 のパーティがパーティ同士での決闘を申し込みました。受け入れますか。
	 */
	public static final SystemMessageId C1_CHALLENGED_YOU_TO_A_PARTY_DUEL;
	
	/**
	 * ID: 1948<br>
	 * Message: 決闘が $s1秒後に始まります。
	 */
	public static final SystemMessageId THE_DUEL_WILL_BEGIN_IN_S1_SECONDS2;
	
	/**
	 * ID: 1949<br>
	 * Message: 決闘開始！
	 */
	public static final SystemMessageId LET_THE_DUEL_BEGIN;
	
	/**
	 * ID: 1950<br>
	 * Message: $c1 が決闘に勝利しました。
	 */
	public static final SystemMessageId C1_HAS_WON_THE_DUEL;
	
	/**
	 * ID: 1951<br>
	 * Message: $c1 のパーティが決闘に勝利しました。
	 */
	public static final SystemMessageId C1_PARTY_HAS_WON_THE_DUEL;
	
	/**
	 * ID: 1952<br>
	 * Message: 引き分けです。
	 */
	public static final SystemMessageId THE_DUEL_HAS_ENDED_IN_A_TIE;
	
	/**
	 * ID: 1953<br>
	 * Message: $c1が失格となり$s2が勝利しました。
	 */
	public static final SystemMessageId SINCE_C1_WAS_DISQUALIFIED_S2_HAS_WON;
	
	/**
	 * ID: 1954<br>
	 * Message: $c1のパーティが失格となり$s2 のパーティが勝利しました。
	 */
	public static final SystemMessageId SINCE_C1_PARTY_WAS_DISQUALIFIED_S2_PARTY_HAS_WON;
	
	/**
	 * ID: 1955<br>
	 * Message: $c1が決闘を放棄したため$s2が勝利しました。
	 */
	public static final SystemMessageId SINCE_C1_WITHDREW_FROM_THE_DUEL_S2_HAS_WON;
	
	/**
	 * ID: 1956<br>
	 * Message: $c1のパーティが決闘を放棄したため$s2のパーティが勝利しました。
	 */
	public static final SystemMessageId SINCE_C1_PARTY_WITHDREW_FROM_THE_DUEL_S2_PARTY_HAS_WON;
	
	/**
	 * ID: 1957<br>
	 * Message: 精錬するアイテムを置いてください。
	 */
	public static final SystemMessageId SELECT_THE_ITEM_TO_BE_AUGMENTED;
	
	/**
	 * ID: 1958<br>
	 * Message: 精錬材を置いてください。
	 */
	public static final SystemMessageId SELECT_THE_CATALYST_FOR_AUGMENTATION;
	
	/**
	 * ID: 1959<br>
	 * Message: $s1 $s2個を置いてください。
	 */
	public static final SystemMessageId REQUIRES_S1_S2;
	
	/**
	 * ID: 1960<br>
	 * Message: アイテムが適していません。
	 */
	public static final SystemMessageId THIS_IS_NOT_A_SUITABLE_ITEM;
	
	/**
	 * ID: 1961<br>
	 * Message: ジェムストーンの数が合っていません。
	 */
	public static final SystemMessageId GEMSTONE_QUANTITY_IS_INCORRECT;
	
	/**
	 * ID: 1962<br>
	 * Message: アイテム精錬成功！
	 */
	public static final SystemMessageId THE_ITEM_WAS_SUCCESSFULLY_AUGMENTED;
	
	/**
	 * ID: 1963<br>
	 * Message: 精錬を解除するアイテムを置いてください。
	 */
	public static final SystemMessageId SELECT_THE_ITEM_FROM_WHICH_YOU_WISH_TO_REMOVE_AUGMENTATION;
	
	/**
	 * ID: 1964<br>
	 * Message: 精錬されていないアイテムは精錬解除を行うことができません。
	 */
	public static final SystemMessageId AUGMENTATION_REMOVAL_CAN_ONLY_BE_DONE_ON_AN_AUGMENTED_ITEM;
	
	/**
	 * ID: 1965<br>
	 * Message: $s1の精錬解除に成功しました。
	 */
	public static final SystemMessageId AUGMENTATION_HAS_BEEN_SUCCESSFULLY_REMOVED_FROM_YOUR_S1;
	
	/**
	 * ID: 1966<br>
	 * Message: 指揮コマンドは連合の指揮者だけが使用できます。
	 */
	public static final SystemMessageId ONLY_CLAN_LEADER_CAN_ISSUE_COMMANDS;
	
	/**
	 * ID: 1967<br>
	 * Message: 門が固く閉ざされています。しばらくしてからもう一度行ってください。
	 */
	public static final SystemMessageId GATE_LOCKED_TRY_AGAIN_LATER;
	
	/**
	 * ID: 1968<br>
	 * Message: $s1の所有者
	 */
	public static final SystemMessageId S1_OWNER;
	
	/**
	 * ID: 1968<br>
	 * Message: $s1の所有者
	 */
	public static final SystemMessageId AREA_S1_APPEARS;
	
	/**
	 * ID: 1970<br>
	 * Message: 精錬されたアイテムをまた精錬することはできません。
	 */
	public static final SystemMessageId ONCE_AN_ITEM_IS_AUGMENTED_IT_CANNOT_BE_AUGMENTED_AGAIN;
	
	/**
	 * ID: 1971<br>
	 * Message: 強化材のレベルが高過ぎるため、使用できません。
	 */
	public static final SystemMessageId HARDENER_LEVEL_TOO_HIGH;
	
	/**
	 * ID: 1972<br>
	 * Message: 個人商店や個人工房の途中では精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_A_PRIVATE_STORE_OR_PRIVATE_WORKSHOP_IS_IN_OPERATION;
	
	/**
	 * ID: 1973<br>
	 * Message: フリーズ状態では精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_FROZEN;
	
	/**
	 * ID: 1974<br>
	 * Message: 死亡した状態では精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_DEAD;
	
	/**
	 * ID: 1975<br>
	 * Message: トレード中は精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_TRADING;
	
	/**
	 * ID: 1976<br>
	 * Message: 石化状態では精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_PARALYZED;
	
	/**
	 * ID: 1977<br>
	 * Message: フィッシング中は精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_FISHING;
	
	/**
	 * ID: 1978<br>
	 * Message: 座っている状態では精錬できません。
	 */
	public static final SystemMessageId YOU_CANNOT_AUGMENT_ITEMS_WHILE_SITTING_DOWN;
	
	/**
	 * ID: 1979<br>
	 * Message: $s1の残存魔力は10になりました。
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_10;
	
	/**
	 * ID: 1980<br>
	 * Message: $s1の残存魔力は5になりました。
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_5;
	
	/**
	 * ID: 1981<br>
	 * Message: $s1の残存魔力は1になりました。しばらくすると消滅します。
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_1;
	
	/**
	 * ID: 1982<br>
	 * Message: $s1の残存魔力が0となり、アイテムが消滅しました。
	 */
	public static final SystemMessageId S1S_REMAINING_MANA_IS_NOW_0;
	
	/**
	 * ID: 1984<br>
	 * Message: 精錬ボタンを押すと精錬が始まります。
	 */
	public static final SystemMessageId PRESS_THE_AUGMENT_BUTTON_TO_BEGIN;
	
	/**
	 * ID: 1985<br>
	 * Message: $s1のドロップ地域($s2)
	 */
	public static final SystemMessageId S1_DROP_AREA_S2;
	
	/**
	 * ID: 1986<br>
	 * Message: $s1の所有者($s2)
	 */
	public static final SystemMessageId S1_OWNER_S2;
	
	/**
	 * ID: 1987<br>
	 * Message: $s1
	 */
	public static final SystemMessageId S1;
	
	/**
	 * ID: 1988<br>
	 * Message: 太古の島に到着しました。
	 */
	public static final SystemMessageId FERRY_ARRIVED_AT_PRIMEVAL;
	
	/**
	 * ID: 1989<br>
	 * Message: 3分間停泊後、ルウン港に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_RUNE_3_MINUTES;
	
	/**
	 * ID: 1990<br>
	 * Message: 太古の島からルウン港へ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_PRIMEVAL_FOR_RUNE_NOW;
	
	/**
	 * ID: 1991<br>
	 * Message: 3分間停泊後、太古の島に出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_FOR_PRIMEVAL_3_MINUTES;
	
	/**
	 * ID: 1992<br>
	 * Message: ルウン港から太古の島へ出発します。
	 */
	public static final SystemMessageId FERRY_LEAVING_RUNE_FOR_PRIMEVAL_NOW;
	
	/**
	 * ID: 1993<br>
	 * Message: 太古の島発 ルウン港行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_FROM_PRIMEVAL_TO_RUNE_DELAYED;
	
	/**
	 * ID: 1994<br>
	 * Message: ルウン港発 太古の島行きの定期船の運航に遅れが出ています。
	 */
	public static final SystemMessageId FERRY_FROM_RUNE_TO_PRIMEVAL_DELAYED;
	
	/**
	 * ID: 1995<br>
	 * Message: $s1ﾁｬﾝﾈﾙ ﾌｨﾙﾀﾘﾝｸﾞ ｵﾌﾟｼｮﾝ
	 */
	public static final SystemMessageId S1_CHANNEL_FILTER_OPTION;
	
	/**
	 * ID: 1996<br>
	 * Message: 相手が無敵状態です。
	 */
	public static final SystemMessageId ATTACK_WAS_BLOCKED;
	
	/**
	 * ID: 1997<br>
	 * Message: $c1がスキル攻撃に反撃します。
	 */
	public static final SystemMessageId C1_PERFORMING_COUNTERATTACK;
	
	/**
	 * ID: 1998<br>
	 * Message: $c1のスキル攻撃に反撃します。
	 */
	public static final SystemMessageId COUNTERED_C1_ATTACK;
	
	/**
	 * ID: 1999<br>
	 * Message: $c1がスキル攻撃を避けました。
	 */
	public static final SystemMessageId C1_DODGES_ATTACK;
	
	/**
	 * ID: 2000<br>
	 * Message: $c1のスキル攻撃を避けました。
	 */
	public static final SystemMessageId AVOIDED_C1_ATTACK2;
	
	/**
	 * ID: 2001<br>
	 * Message: 精錬が正常に行われず、精錬が失敗しました。
	 */
	public static final SystemMessageId AUGMENTATION_FAILED_DUE_TO_INAPPROPRIATE_CONDITIONS;
	
	/**
	 * ID: 2002<br>
	 * Message: 捕獲に失敗しました。
	 */
	public static final SystemMessageId TRAP_FAILED;
	
	/**
	 * ID: 2003<br>
	 * Message: 一般属性を得ました。
	 */
	public static final SystemMessageId OBTAINED_ORDINARY_MATERIAL;
	
	/**
	 * ID: 2004<br>
	 * Message: レア属性を得ました。
	 */
	public static final SystemMessageId OBTAINED_RATE_MATERIAL;
	
	/**
	 * ID: 2005<br>
	 * Message: ユニーク属性を得ました。
	 */
	public static final SystemMessageId OBTAINED_UNIQUE_MATERIAL;
	
	/**
	 * ID: 2006<br>
	 * Message: 唯一属性を得ました。
	 */
	public static final SystemMessageId OBTAINED_ONLY_MATERIAL;
	
	/**
	 * ID: 2007<br>
	 * Message: 受信先を入力してください。
	 */
	public static final SystemMessageId ENTER_RECIPIENTS_NAME;
	
	/**
	 * ID: 2008<br>
	 * Message: 本文を入力してください。
	 */
	public static final SystemMessageId ENTER_TEXT;
	
	/**
	 * ID: 2009<br>
	 * Message: 1500文字を過ぎています。
	 */
	public static final SystemMessageId CANT_EXCEED_1500_CHARACTERS;
	
	/**
	 * ID: 2009<br>
	 * Message: 1500文字を過ぎています。
	 */
	public static final SystemMessageId S2_S1;
	
	/**
	 * ID: 2011<br>
	 * Message: 精錬済みアイテムは捨てることができません。
	 */
	public static final SystemMessageId AUGMENTED_ITEM_CANNOT_BE_DISCARDED;
	
	/**
	 * ID: 2012<br>
	 * Message: $s1が発動されました。
	 */
	public static final SystemMessageId S1_HAS_BEEN_ACTIVATED;
	
	/**
	 * ID: 2013<br>
	 * Message: 種または購入の残りが不足しています。
	 */
	public static final SystemMessageId YOUR_SEED_OR_REMAINING_PURCHASE_AMOUNT_IS_INADEQUATE;
	
	/**
	 * ID: 2014<br>
	 * Message: 異常事態が発生して進行できません。
	 */
	public static final SystemMessageId MANOR_CANT_ACCEPT_MORE_CROPS;
	
	/**
	 * ID: 2015<br>
	 * Message: 卓越したスキルの効果が発動し、スキルの再使用が可能です。
	 */
	public static final SystemMessageId SKILL_READY_TO_USE_AGAIN;
	
	/**
	 * ID: 2016<br>
	 * Message: 卓越したスキルの効果が発動し、スキルの異常状態の効果時間が増加しました。
	 */
	public static final SystemMessageId SKILL_READY_TO_USE_AGAIN_BUT_TIME_INCREASED;
	
	/**
	 * ID: 2017<br>
	 * Message: $c1 は個人商店や個人工房を開いているため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_A_PRIVATE_STORE_OR_MANUFACTURE;
	
	/**
	 * ID: 2018<br>
	 * Message: $c1 はフィッシング中のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_FISHING;
	
	/**
	 * ID: 2019<br>
	 * Message: $c1 はHPまたはMPが50％以下のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_HP_OR_MP_IS_BELOW_50_PERCENT;
	
	/**
	 * ID: 2020<br>
	 * Message: $c1 は決闘不可能な地域（ピースゾーン、セブン サイン、水中、リスタート不可能な地域）にいるため決闘を申し込めません。
	 */
	public static final SystemMessageId C1_CANNOT_MAKE_A_CHALLANGE_TO_A_DUEL_BECAUSE_C1_IS_CURRENTLY_IN_A_DUEL_PROHIBITED_AREA;
	
	/**
	 * ID: 2021<br>
	 * Message: $c1 は戦闘中のため、決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_ENGAGED_IN_BATTLE;
	
	/**
	 * ID: 2022<br>
	 * Message: $c1 はすでに決闘中のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_ALREADY_ENGAGED_IN_A_DUEL;
	
	/**
	 * ID: 2023<br>
	 * Message: $c1 はカオティック性向のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_IN_A_CHAOTIC_STATE;
	
	/**
	 * ID: 2024<br>
	 * Message: $c1 はオリンピアードを行っているため、決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_THE_OLYMPIAD;
	
	/**
	 * ID: 2025<br>
	 * Message: $c1 はアジト戦遂行中のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_CLAN_HALL_WAR;
	
	/**
	 * ID: 2026<br>
	 * Message: $c1 は攻城戦に参加中のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_PARTICIPATING_IN_A_SIEGE_WAR;
	
	/**
	 * ID: 2027<br>
	 * Message: $c1 は船、ワイバーン、ストライダーなどに搭乗中のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_STEED_OR_STRIDER;
	
	/**
	 * ID: 2028<br>
	 * Message: $c1 は遠距離にいるため決闘を承諾できません。
	 */
	public static final SystemMessageId C1_CANNOT_RECEIVE_A_DUEL_CHALLENGE_BECAUSE_C1_IS_TOO_FAR_AWAY;
	
	/**
	 * ID: 2029<br>
	 * Message: $c1 はテレポート中であるため、オリンピアードに参加できません。
	 */
	public static final SystemMessageId C1_CANNOT_PARTICIPATE_IN_OLYMPIAD_DURING_TELEPORT;
	
	/**
	 * ID: 2030<br>
	 * Message: ログイン中です。
	 */
	public static final SystemMessageId CURRENTLY_LOGGING_IN;
	
	/**
	 * ID: 2031<br>
	 * Message: しばらくお待ちください。
	 */
	public static final SystemMessageId PLEASE_WAIT_A_MOMENT;
	
	/**
	 * ID: 2032<br>
	 * Message: アイテムの購入可能時間内ではありません。
	 */
	public static final SystemMessageId NOT_TIME_TO_PURCHASE_ITEM;
	
	/**
	 * ID: 2033<br>
	 * Message: インベントリの数量制限を超えたため、サブ クラスを作ることや変更ができません。
	 */
	public static final SystemMessageId NOT_SUBCLASS_WHILE_INVENTORY_FULL;
	
	/**
	 * ID: 2034<br>
	 * Message: アイテムの購入可能時間まで残り$s1時間 $s2分です。
	 */
	public static final SystemMessageId ITEM_PURCHASABLE_IN_S1_HOURS_S2_MINUTES;
	
	/**
	 * ID: 2035<br>
	 * Message: アイテムの購入可能時間まで残り $s1分です。
	 */
	public static final SystemMessageId ITEM_PURCHASABLE_IN_S1_MINUTES;
	
	/**
	 * ID: 2036<br>
	 * Message: パーティがロックされているため招待できません。
	 */
	public static final SystemMessageId NO_INVITE_PARTY_LOCKED;
	
	/**
	 * ID: 2037<br>
	 * Message: キャラクターを生成できません。該当サーバーは以前生成したキャラクターがいない場合は、新規キャラクターを生成できないように制限されています。他のサーバーをご利用ください。
	 */
	public static final SystemMessageId CANT_CREATE_CHARACTER_DURING_RESTRICTION;
	
	/**
	 * ID: 2038<br>
	 * Message: アイテムのドロップができないアカウントです。
	 */
	public static final SystemMessageId ACCOUNT_CANT_DROP_ITEMS;
	
	/**
	 * ID: 2039<br>
	 * Message: アイテムのトレードができないアカウントです。
	 */
	public static final SystemMessageId ACCOUNT_CANT_TRADE_ITEMS;
	
	/**
	 * ID: 2040<br>
	 * Message: 対象のユーザーとアイテムのトレードができません。
	 */
	public static final SystemMessageId CANT_TRADE_WITH_TARGET;
	
	/**
	 * ID: 2041<br>
	 * Message: 個人商店を開くことはできません。
	 */
	public static final SystemMessageId CANT_OPEN_PRIVATE_STORE;
	
	/**
	 * ID: 2042<br>
	 * Message: お客様のアカウントは、料金未納により一時的にご利用が制限されている状態です。\n詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE23;
	
	/**
	 * ID: 2043<br>
	 * Message: インベントリの重量または個数が制限を越えているため、クエストの精算ができません。インベントリにスペースを確保してからやり直してください。
	 */
	public static final SystemMessageId YOU_HAVE_EXCEEDED_YOUR_INVENTORY_VOLUME_LIMIT_AND_CANNOT_TAKE_THIS_QUESTITEM;
	
	/**
	 * ID: 2044<br>
	 * Message: 個人工房が開けないアカウントです。
	 */
	public static final SystemMessageId CANT_SETUP_PRIVATE_WORKSHOP;
	
	/**
	 * ID: 2045<br>
	 * Message: 個人工房がご利用できないアカウントです。
	 */
	public static final SystemMessageId CANT_USE_PRIVATE_WORKSHOP;
	
	/**
	 * ID: 2046<br>
	 * Message: 個人商店をご利用できないアカウントです。
	 */
	public static final SystemMessageId CANT_USE_PRIVATE_STORES;
	
	/**
	 * ID: 2047<br>
	 * Message: 血盟倉庫をご利用できないアカウントです。
	 */
	public static final SystemMessageId CANT_USE_CLAN_WH;
	
	/**
	 * ID: 2048<br>
	 * Message: $s1ですでに使用されているショートカット キーと重複しています。重複しているショートカット キーをリセットし、入力したショートカット キーを使いますか。
	 */
	public static final SystemMessageId CONFLICTING_SHORTCUT;
	
	/**
	 * ID: 2049<br>
	 * Message: ショートカットキーを適用してサーバーに保存します。よろしいですか。
	 */
	public static final SystemMessageId CONFIRM_SHORTCUT_WILL_SAVED_ON_SERVER;
	
	/**
	 * ID: 2050<br>
	 * Message: $s1血盟が旗の掲揚を行います。
	 */
	public static final SystemMessageId S1_TRYING_RAISE_FLAG;
	
	/**
	 * ID: 2051<br>
	 * Message: お客様は、まだリネージュIIのプレイに同意されていないため、リネージュIIのプレイはできません。公式サイト（http://lineage2.plaync.jp/）でゲームに同意してから、もう一度ログインしてください。
	 */
	public static final SystemMessageId MUST_ACCEPT_AGREEMENT;
	
	/**
	 * ID: 2052<br>
	 * Message: お客様のアカウントは保護者の同意を得ていません。\n保護者の同意を済ませた後で、もう一度ログインしてください。
	 */
	public static final SystemMessageId NEED_CONSENT_TO_PLAY_THIS_ACCOUNT;
	
	/**
	 * ID: 2053<br>
	 * Message: お客様のアカウントは、ゲームに同意していないか、解約申請中となっています。\nゲームに同意するか、解約申請をキャンセルした後で、もう一度ログインしてください。
	 */
	public static final SystemMessageId ACCOUNT_DECLINED_AGREEMENT_OR_PENDING;
	
	/**
	 * ID: 2054<br>
	 * Message: お客様のアカウントは、すべてのサービスのご利用が制限されている状態です。\n詳しくは公式サイト（http://lineage2.plaync.jp/）までお問い合わせください。
	 */
	public static final SystemMessageId ACCOUNT_SUSPENDED;
	
	/**
	 * ID: 2055<br>
	 * Message: お客様のアカウントは、すべてのゲーム サービスのご利用ができないように制限されています。\n詳しくは公式サイト（http://lineage2.plaync.jp/）までお問い合わせください。
	 */
	public static final SystemMessageId ACCOUNT_SUSPENDED_FROM_ALL_SERVICES;
	
	/**
	 * ID: 2056<br>
	 * Message: お客様のアカウントは統合アカウントに変更されたため、今後接続できません。\n変更後の統合アカウントでもう一度ログインしてください。
	 */
	public static final SystemMessageId ACCOUNT_CONVERTED;
	
	/**
	 * ID: 2057<br>
	 * Message: あなたは$c1を遮断しました。
	 */
	public static final SystemMessageId BLOCKED_C1;
	
	/**
	 * ID: 2058<br>
	 * Message: すでに変身状態であるため、変身ができません。
	 */
	public static final SystemMessageId YOU_ALREADY_POLYMORPHED_AND_CANNOT_POLYMORPH_AGAIN;
	
	/**
	 * ID: 2059<br>
	 * Message: 周りが狭いため、変身できません。別の場所に移動してからもう一度行ってください。
	 */
	public static final SystemMessageId AREA_UNSUITABLE_FOR_POLYMORPH;
	
	/**
	 * ID: 2060<br>
	 * Message: 水の中ではお望みの姿に変身できません。
	 */
	public static final SystemMessageId YOU_CANNOT_POLYMORPH_INTO_THE_DESIRED_FORM_IN_WATER;
	
	/**
	 * ID: 2061<br>
	 * Message: 変身後遺症が続いているため変身できません。
	 */
	public static final SystemMessageId CANT_MORPH_DUE_TO_MORPH_PENALTY;
	
	/**
	 * ID: 2062<br>
	 * Message: 召喚獣、ペットを召喚した状態では変身できません。
	 */
	public static final SystemMessageId YOU_CANNOT_POLYMORPH_WHEN_YOU_HAVE_SUMMONED_A_SERVITOR;
	
	/**
	 * ID: 2063<br>
	 * Message: ペットに乗っている状態では変身できません。
	 */
	public static final SystemMessageId YOU_CANNOT_POLYMORPH_WHILE_RIDING_A_PET;
	
	/**
	 * ID: 2064<br>
	 * Message: 特殊なスキルの影響を受けている間は変身できません。
	 */
	public static final SystemMessageId CANT_MORPH_WHILE_UNDER_SPECIAL_SKILL_EFFECT;
	
	/**
	 * ID: 2065<br>
	 * Message: 該当アイテムは解除できません。
	 */
	public static final SystemMessageId ITEM_CANNOT_BE_TAKEN_OFF;
	
	/**
	 * ID: 2066<br>
	 * Message: 該当の武器は攻撃ができません。
	 */
	public static final SystemMessageId THAT_WEAPON_CANT_ATTACK;
	
	/**
	 * ID: 2067<br>
	 * Message: 該当の武器は武器スキル以外のその他のスキルは使用できません。
	 */
	public static final SystemMessageId WEAPON_CAN_USE_ONLY_WEAPON_SKILL;
	
	/**
	 * ID: 2068<br>
	 * Message: エンチャント スキルのアントレインに必要なアイテムが不足しています。
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_UNTRAIN_SKILL_ENCHANT;
	
	/**
	 * ID: 2069<br>
	 * Message: エンチャント スキルのアントレインに成功しました。エンチャント スキル$s1のエンチャントLvが1低下しました。
	 */
	public static final SystemMessageId UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_DECREASED_BY_ONE;
	
	/**
	 * ID: 2070<br>
	 * Message: エンチャント スキルのアントレインに成功しました。エンチャント スキル$s1のエンチャントLvが0になりエンチャント スキルが初期化されます。
	 */
	public static final SystemMessageId UNTRAIN_SUCCESSFUL_SKILL_S1_ENCHANT_LEVEL_RESETED;
	
	/**
	 * ID: 2071<br>
	 * Message: エンチャント スキル ルート チェンジに必要なアイテムが足りません。
	 */
	public static final SystemMessageId YOU_DONT_HAVE_ALL_ITENS_NEEDED_TO_CHANGE_SKILL_ENCHANT_ROUTE;
	
	/**
	 * ID: 2072<br>
	 * Message: エンチャント スキル ルート チェンジに成功しました。エンチャント スキル$s1のLvが$s2低下しました。
	 */
	public static final SystemMessageId SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WAS_DECREASED_BY_S2;
	
	/**
	 * ID: 2073<br>
	 * Message: エンチャント スキル ルート チェンジに成功しました。エンチャントスキル$s1のLvが維持されます。
	 */
	public static final SystemMessageId SKILL_ENCHANT_CHANGE_SUCCESSFUL_S1_LEVEL_WILL_REMAIN;
	
	/**
	 * ID: 2074<br>
	 * Message: スキル エンチャントに失敗しました。エンチャント スキル$s1のエンチャントLvが維持されます。
	 */
	public static final SystemMessageId SKILL_ENCHANT_FAILED_S1_LEVEL_WILL_REMAIN;
	
	/**
	 * ID: 2075<br>
	 * Message: 競売期間ではありません。
	 */
	public static final SystemMessageId NO_AUCTION_PERIOD;
	
	/**
	 * ID: 2076<br>
	 * Message: 最高入札価格が1000億を超えるため、入札ができません。
	 */
	public static final SystemMessageId BID_CANT_EXCEED_100_BILLION;
	
	/**
	 * ID: 2077<br>
	 * Message: 最高入札価格より高い金額で入札してください。
	 */
	public static final SystemMessageId BID_MUST_BE_HIGHER_THAN_CURRENT_BID;
	
	/**
	 * ID: 2078<br>
	 * Message: 入札に必要なアデナが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_ADENA_FOR_THIS_BID;
	
	/**
	 * ID: 2079<br>
	 * Message: すでに最高額入札となったか、流札となった金額があります。
	 */
	public static final SystemMessageId HIGHEST_BID_BUT_RESERVE_NOT_MET;
	
	/**
	 * ID: 2080<br>
	 * Message: 上位入札者により入札がキャンセルされました。
	 */
	public static final SystemMessageId YOU_HAVE_BEEN_OUTBID;
	
	/**
	 * ID: 2081<br>
	 * Message: 流札となった金額がありません。
	 */
	public static final SystemMessageId NO_FUNDS_DUE;
	
	/**
	 * ID: 2082<br>
	 * Message: インベントリのアデナが許容値を超えます。
	 */
	public static final SystemMessageId EXCEEDED_MAX_ADENA_AMOUNT_IN_INVENTORY;
	
	/**
	 * ID: 2083<br>
	 * Message: 競売が始まります。
	 */
	public static final SystemMessageId AUCTION_BEGUN;
	
	/**
	 * ID: 2084<br>
	 * Message: 要塞に敵の血盟が侵入しました。
	 */
	public static final SystemMessageId ENEMIES_INTRUDED_FORTRESS;
	
	/**
	 * ID: 2085<br>
	 * Message: 呪われた武器を所有した状態では叫びとトレード チャットは利用できません。
	 */
	public static final SystemMessageId SHOUT_AND_TRADE_CHAT_CANNOT_BE_USED_WHILE_POSSESSING_CURSED_WEAPON;
	
	/**
	 * ID: 2086<br>
	 * Message: $s1分後にユーザー$c2についてのBOT検査が終了します。
	 */
	public static final SystemMessageId SEARCH_ON_S2_FOR_BOT_USE_COMPLETED_IN_S1_MINUTES;
	
	/**
	 * ID: 2087<br>
	 * Message: 要塞が攻撃されています！
	 */
	public static final SystemMessageId A_FORTRESS_IS_UNDER_ATTACK;
	
	/**
	 * ID: 2088<br>
	 * Message: 要塞戦開始まで残り$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_UNTIL_THE_FORTRESS_BATTLE_STARTS;
	
	/**
	 * ID: 2089<br>
	 * Message: 要塞戦開始まで残り$s1秒です。
	 */
	public static final SystemMessageId S1_SECONDS_UNTIL_THE_FORTRESS_BATTLE_STARTS;
	
	/**
	 * ID: 2090<br>
	 * Message: $s1の要塞戦が始まりました。
	 */
	public static final SystemMessageId THE_FORTRESS_BATTLE_S1_HAS_BEGUN;
	
	/**
	 * ID: 2091<br>
	 * Message: お客様のアカウントはパスワードの変更をしなければ接続ができません。\n公式サイト（http://lineage2.plaync.jp/）でパスワードを変更した後で、もう一度やり直してください。
	 */
	public static final SystemMessageId CHANGE_PASSWORT_FIRST;
	
	/**
	 * ID: 2092<br>
	 * Message: 流札となった金額があるため入札ができません。
	 */
	public static final SystemMessageId CANNOT_BID_DUE_TO_PASSED_IN_PRICE;
	
	/**
	 * ID: 2093<br>
	 * Message: 競売の流札となった金額は$s1アデナです。流札となった金額を返金しますか。
	 */
	public static final SystemMessageId PASSED_IN_PRICE_IS_S1_ADENA_WOULD_YOU_LIKE_TO_RETURN_IT;
	
	/**
	 * ID: 2094<br>
	 * Message: 他のユーザーが購入中です。しばらく経ってからもう一度お試しください。
	 */
	public static final SystemMessageId ANOTHER_USER_PURCHASING_TRY_AGAIN_LATER;
	
	/**
	 * ID: 2095<br>
	 * Message: 叫びができないアカウントです。
	 */
	public static final SystemMessageId ACCOUNT_CANNOT_SHOUT;
	
	/**
	 * ID: 2096<br>
	 * Message: $c1 が入場できない位置にいるため進行できません。
	 */
	public static final SystemMessageId C1_IS_IN_LOCATION_THAT_CANNOT_BE_ENTERED;
	
	/**
	 * ID: 2097<br>
	 * Message: $c1 のレベル条件が満たないため入場できません。
	 */
	public static final SystemMessageId C1_LEVEL_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2098<br>
	 * Message: $c1 のクエスト条件が満たないため入場できません。
	 */
	public static final SystemMessageId C1_QUEST_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2099<br>
	 * Message: $c1 のアイテム条件が満たないため入場できません。
	 */
	public static final SystemMessageId C1_ITEM_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2100<br>
	 * Message: $c1 が再入場可能時間にならないため、入場できません。
	 */
	public static final SystemMessageId C1_MAY_NOT_REENTER_YET;
	
	/**
	 * ID: 2101<br>
	 * Message: 現在パーティ状態ではないため入場できません。
	 */
	public static final SystemMessageId NOT_IN_PARTY_CANT_ENTER;
	
	/**
	 * ID: 2102<br>
	 * Message: 該当パーティが入場定員数を超えたため、入場できません。
	 */
	public static final SystemMessageId PARTY_EXCEEDED_THE_LIMIT_CANT_ENTER;
	
	/**
	 * ID: 2103<br>
	 * Message: 現在、連合チャンネルに所属していないため入場できません。
	 */
	public static final SystemMessageId NOT_IN_COMMAND_CHANNEL_CANT_ENTER;
	
	/**
	 * ID: 2104<br>
	 * Message: 該当インスタント ゾーンの最大生成数を超えたため、入場できません。
	 */
	public static final SystemMessageId MAXIMUM_INSTANCE_ZONE_NUMBER_EXCEEDED_CANT_ENTER;
	
	/**
	 * ID: 2105<br>
	 * Message: 他のインスタント ゾーンに入場した状態であるため、該当ダンジョンに入場できません。
	 */
	public static final SystemMessageId ALREADY_ENTERED_ANOTHER_INSTANCE_CANT_ENTER;
	
	/**
	 * ID: 2106<br>
	 * Message: 該当ダンジョンの利用時間終了まで残り$s1分です。時間が終了するとダンジョンの外へ強制移動します。
	 */
	public static final SystemMessageId DUNGEON_EXPIRES_IN_S1_MINUTES;
	
	/**
	 * ID: 2107<br>
	 * Message: インスタント ゾーンが$s1分後に削除され、該当時間終了後にダンジョンの外へ強制移動します。
	 */
	public static final SystemMessageId INSTANCE_ZONE_TERMINATES_IN_S1_MINUTES;
	
	/**
	 * ID: 2108<br>
	 * Message: お客様のアカウントは、不正ユーティリティの利用が認められたため10日間、ゲームサービスすべてのご利用が制限されています。今後同様の違反行為が確認された場合、永久に制限されますのでご注意ください。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE24;
	
	/**
	 * ID: 2109<br>
	 * Message: サーバーが統合された為、既存でご使用のキャラクター$s1は、他の名前と重複しています。変更するキャラクター名を入力してください。
	 */
	public static final SystemMessageId CHARACTER_NAME_OVERLAPPING_RENAME_CHARACTER;
	
	/**
	 * ID: 2110<br>
	 * Message: ご入力の名前は既に存在しているか、ご利用ができない名前です。変更するキャラクター名をもう一度ご入力ください。
	 */
	public static final SystemMessageId CHARACTER_NAME_INVALID_RENAME_CHARACTER;
	
	/**
	 * ID: 2111<br>
	 * Message: 指定のショートカットキーを入力します。
	 */
	public static final SystemMessageId ENTER_SHORTCUT_TO_ASSIGN;
	
	/**
	 * ID: 2112<br>
	 * Message: 補助キーはCTRL、ALT、SHIFTキーが使用でき、一度に二つの補助キーを入力できます。\n例) 「CTRL + ALT + A」
	 */
	public static final SystemMessageId SUBKEY_EXPLANATION1;
	
	/**
	 * ID: 2113<br>
	 * Message: 拡張 補助キー モードではCTRL、ALT、SHIFTキーを補助キーとして活用でき、一般ショートカットキー モードでは補助キーでALTのみ使用できます。
	 */
	public static final SystemMessageId SUBKEY_EXPLANATION2;
	
	/**
	 * ID: 2114<br>
	 * Message: 拡張 補助キー モードに設定すると、既存でCtrlとShiftに指定されていた強制攻撃とその場での攻撃がAlt + QとAlt + Eに変更され、CTRLキーとSHIFTキーを別のショートカットキーの指定キーで使用可能となるように変更されます。よろしいですか。
	 */
	public static final SystemMessageId SUBKEY_EXPLANATION3;
	
	/**
	 * ID: 2115<br>
	 * Message: お客様のアカウントはNCコインに関するバグを悪用したことが認められたため、ご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE25;
	
	/**
	 * ID: 2116<br>
	 * Message: お客様のアカウントは無料で支給されたコインを悪用したことが認められたためご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE26;
	
	/**
	 * ID: 2117<br>
	 * Message: お客様のアカウントは他人の名義を利用した加入などが認められたため、一時的にゲームサービスのご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE27;
	
	/**
	 * ID: 2118<br>
	 * Message: お客様のアカウントは決済の盗用が認められたため、ゲームサービスすべてのご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE28;
	
	/**
	 * ID: 2119<br>
	 * Message: お客様のアカウントは決済の盗用が認められため、ゲームサービスすべてのご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE29;
	
	/**
	 * ID: 2120<br>
	 * Message: お客様のアカウントは不正ユーティリティの利用が認められたため、10日間ゲームサービスの利用がすべて制限されています。今後同様の違反行為が確認された場合は永久に制限されますのでご注意ください。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE30;
	
	/**
	 * ID: 2121<br>
	 * Message: お客様のアカウントは不正ユーティリティの利用が認められたため、ゲームサービスすべての利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE31;
	
	/**
	 * ID: 2122<br>
	 * Message: お客様のアカウントは不正ユーティリティの利用が認められたため、ゲームサービスすべての利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE32;
	
	/**
	 * ID: 2123<br>
	 * Message: お客様のアカウントはお客様からの要請によりゲームサービスすべての利用ができないようになっています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE33;
	
	/**
	 * ID: 2124<br>
	 * Message: サーバーが統合され既存にご使用されていた血盟名である$s1は、他の名前と重複しています。変更する血盟名を入力してください。
	 */
	public static final SystemMessageId CLAN_NAME_OVERLAPPING_RENAME_CLAN;
	
	/**
	 * ID: 2125<br>
	 * Message: ご入力の名前は既に存在しているかご使用ができない名前です。変更する血盟名をもう一度入力してください。
	 */
	public static final SystemMessageId CLAN_NAME_INVALID_RENAME_CLAN;
	
	/**
	 * ID: 2126<br>
	 * Message: お客様のアカウントは公序良俗に反する内容の度重なるアップロードが認められたため、サービスのご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE34;
	
	/**
	 * ID: 2127<br>
	 * Message: お客様のアカウントは違反となる書き込みが認められたため、ご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE35;
	
	/**
	 * ID: 2128<br>
	 * Message: お客様のアカウントは商業目的のゲーム利用が認められたため、ゲームサービスすべてのご利用が制限されています。詳しくは公式サイト（http://lineage2.plaync.jp/）のサポートまでお問い合わせください。
	 */
	public static final SystemMessageId ILLEGAL_USE36;
	
	/**
	 * ID: 2129<br>
	 * Message: 精錬されたアイテムは変換できません。精錬を解除した後に変換してください。
	 */
	public static final SystemMessageId AUGMENTED_ITEM_CANT_CONVERTED;
	
	/**
	 * ID: 2130<br>
	 * Message: 変換できないアイテムです。
	 */
	public static final SystemMessageId CANT_CONVERT_THIS_ITEM;
	
	/**
	 * ID: 2131<br>
	 * Message: 最高金額をつけてアイテムが落札されました。落札されたアイテムは個人倉庫から受け取ることができます。
	 */
	public static final SystemMessageId WON_BID_ITEM_CAN_BE_FOUND_IN_WAREHOUSE;
	
	/**
	 * ID: 2132<br>
	 * Message: 一般サーバーに接続しました。
	 */
	public static final SystemMessageId ENTERED_COMMON_SERVER;
	
	/**
	 * ID: 2133<br>
	 * Message: 成人サーバーに接続しました。
	 */
	public static final SystemMessageId ENTERED_ADULTS_ONLY_SERVER;
	
	/**
	 * ID: 2134<br>
	 * Message: 青少年サーバーに接続しました。
	 */
	public static final SystemMessageId ENTERED_JUVENILES_SERVER;
	
	/**
	 * ID: 2135<br>
	 * Message: 疲労度のため行えません。
	 */
	public static final SystemMessageId NOT_ALLOWED_DUE_TO_FATIGUE_LEVEL;
	
	/**
	 * ID: 2136<br>
	 * Message: 血盟名変更の申請が受け付けられました。
	 */
	public static final SystemMessageId CLAN_NAME_CHANCE_PETITION_SUBMITTED;
	
	/**
	 * ID: 2137<br>
	 * Message: $s1アイテムを$s2アデナで入札しようとしています。よろしいですか。
	 */
	public static final SystemMessageId CONFIRM_BID_S2_ADENA_FOR_S1_ITEM;
	
	/**
	 * ID: 2138<br>
	 * Message: 入札価格を入力して下さい。
	 */
	public static final SystemMessageId ENTER_BID_PRICE;
	
	/**
	 * ID: 2139<br>
	 * Message: $c1のペット
	 */
	public static final SystemMessageId C1_PET;
	
	/**
	 * ID: 2140<br>
	 * Message: $c1の召喚獣
	 */
	public static final SystemMessageId C1_SERVITOR;
	
	/**
	 * ID: 2141<br>
	 * Message: $c1の魔法に弱く抵抗しました。
	 */
	public static final SystemMessageId SLIGHTLY_RESISTED_C1_MAGICC;
	
	/**
	 * ID: 2142<br>
	 * Message: $c1 はパーティ メンバーではないため追放できません。
	 */
	public static final SystemMessageId CANT_EXPEL_C1_NOT_A_PARTY_MEMBER;
	
	/**
	 * ID: 2143<br>
	 * Message: 個人商店や個人工房の途中では属性を付与できません。
	 */
	public static final SystemMessageId CANNOT_ADD_ELEMENTAL_POWER_WHILE_OPERATING_PRIVATE_STORE_OR_WORKSHOP;
	
	/**
	 * ID: 2144<br>
	 * Message: 属性を付与したいアイテムをお選びください。
	 */
	public static final SystemMessageId SELECT_ITEM_TO_ADD_ELEMENTAL_POWER;
	
	/**
	 * ID: 2145<br>
	 * Message: 属性強化材の使用が取り消されました。
	 */
	public static final SystemMessageId ELEMENTAL_ENHANCE_CANCELED;
	
	/**
	 * ID: 2146<br>
	 * Message: 属性強化材の使用条件に合いません。
	 */
	public static final SystemMessageId ELEMENTAL_ENHANCE_REQUIREMENT_NOT_SUFFICIENT;
	
	/**
	 * ID: 2147<br>
	 * Message: $s1に$s2の属性の付与に成功しました。
	 */
	public static final SystemMessageId ELEMENTAL_POWER_S2_SUCCESSFULLY_ADDED_TO_S1;
	
	/**
	 * ID: 2148<br>
	 * Message: +$s1$s2に$s3の属性を成功的に付与しました。
	 */
	public static final SystemMessageId ELEMENTAL_POWER_S3_SUCCESSFULLY_ADDED_TO_S1_S2;
	
	/**
	 * ID: 2149<br>
	 * Message: 属性の付与に失敗しました。
	 */
	public static final SystemMessageId FAILED_ADDING_ELEMENTAL_POWER;
	
	/**
	 * ID: 2150<br>
	 * Message: すでに別の属性が付与されているため、属性が付与できません。
	 */
	public static final SystemMessageId ANOTHER_ELEMENTAL_POWER_ALREADY_ADDED;
	
	/**
	 * ID: 2151<br>
	 * Message: 相手が魔法に抵抗したためダメージが低下しました。
	 */
	public static final SystemMessageId OPPONENT_HAS_RESISTANCE_MAGIC_DAMAGE_DECREASED;
	
	/**
	 * ID: 2152<br>
	 * Message: 指定のショートカットキーをすべて消去して、ショートカットキーを初期設定に戻します。よろしいですか。
	 */
	public static final SystemMessageId CONFIRM_SHORCUT_DELETE;
	
	/**
	 * ID: 2153<br>
	 * Message: 現在お持ちのアカウントの中の10個が同時に接続されているため、これ以上別のアカウントでの接続はできません。
	 */
	public static final SystemMessageId MAXIMUM_ACCOUNT_LOGINS_REACHED;
	
	/**
	 * ID: 2154<br>
	 * Message: ターゲットがフラッグ ポールではないため、旗の掲揚はできません。
	 */
	public static final SystemMessageId THE_TARGET_IS_NOT_A_FLAGPOLE_SO_A_FLAG_CANNOT_BE_DISPLAYED;
	
	/**
	 * ID: 2155<br>
	 * Message: すでに旗が掲揚されているため掲揚できません。
	 */
	public static final SystemMessageId A_FLAG_IS_ALREADY_BEING_DISPLAYED_ANOTHER_FLAG_CANNOT_BE_DISPLAYED;
	
	/**
	 * ID: 2156<br>
	 * Message: 必要なアイテムが足りないためスキルが使用できません。
	 */
	public static final SystemMessageId THERE_ARE_NOT_ENOUGH_NECESSARY_ITEMS_TO_USE_THE_SKILL;
	
	/**
	 * ID: 2157<br>
	 * Message: $s1アデナで入札します。
	 */
	public static final SystemMessageId BID_WILL_BE_ATTEMPTED_WITH_S1_ADENA;
	
	/**
	 * ID: 2158<br>
	 * Message: 攻城側の臨時連合員をターゲットには強制攻撃ができません。
	 */
	public static final SystemMessageId FORCED_ATTACK_IS_IMPOSSIBLE_AGAINST_SIEGE_SIDE_TEMPORARY_ALLIED_MEMBERS;
	
	/**
	 * ID: 2159<br>
	 * Message: 入札者がいるため競売時間が5分間延長されました。
	 */
	public static final SystemMessageId BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_5_MINUTES;
	
	/**
	 * ID: 2160<br>
	 * Message: 入札者がいるため競売時間が3分間延長されました。
	 */
	public static final SystemMessageId BIDDER_EXISTS_AUCTION_TIME_EXTENDED_BY_3_MINUTES;
	
	/**
	 * ID: 2161<br>
	 * Message: 移動スペースがないため、スキルが使用できません。
	 */
	public static final SystemMessageId NOT_ENOUGH_SPACE_FOR_SKILL;
	
	/**
	 * ID: 2162<br>
	 * Message: 魂が$s1個増えたため、合計$s2個になりました。
	 */
	public static final SystemMessageId YOUR_SOUL_HAS_INCREASED_BY_S1_SO_IT_IS_NOW_AT_S2;
	
	/**
	 * ID: 2163<br>
	 * Message: これ以上魂を増やすことはできません。
	 */
	public static final SystemMessageId SOUL_CANNOT_BE_INCREASED_ANYMORE;
	
	/**
	 * ID: 2164<br>
	 * Message: 幕舎を占領しました。
	 */
	public static final SystemMessageId SEIZED_BARRACKS;
	
	/**
	 * ID: 2165<br>
	 * Message: 幕舎機能が復旧されました。
	 */
	public static final SystemMessageId BARRACKS_FUNCTION_RESTORED;
	
	/**
	 * ID: 2166<br>
	 * Message: すべての幕舎が占領されました。
	 */
	public static final SystemMessageId ALL_BARRACKS_OCCUPIED;
	
	/**
	 * ID: 2167<br>
	 * Message: 悪意のあるスキルはピースゾーンで使用できません。
	 */
	public static final SystemMessageId A_MALICIOUS_SKILL_CANNOT_BE_USED_IN_PEACE_ZONE;
	
	/**
	 * ID: 2168<br>
	 * Message: $c1が旗を拾いました。
	 */
	public static final SystemMessageId C1_ACQUIRED_THE_FLAG;
	
	/**
	 * ID: 2169<br>
	 * Message: $s1の要塞戦に登録されました。
	 */
	public static final SystemMessageId REGISTERED_TO_S1_FORTRESS_BATTLE;
	
	/**
	 * ID: 2170<br>
	 * Message: 悪意のあるスキルは相手がピースゾーンにいる時は使用できません。
	 */
	public static final SystemMessageId CANT_USE_BAD_MAGIC_WHEN_OPPONENT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2171<br>
	 * Message: クリスタライズできないアイテムです。
	 */
	public static final SystemMessageId ITEM_CANNOT_CRYSTALLIZED;
	
	/**
	 * ID: 2172<br>
	 * Message: +$s1$s2の競売が終了しました。
	 */
	public static final SystemMessageId S1_S2_AUCTION_ENDED;
	
	/**
	 * ID: 2173<br>
	 * Message: $s1の競売が終了しました。
	 */
	public static final SystemMessageId S1_AUCTION_ENDED;
	
	/**
	 * ID: 2174<br>
	 * Message: $c1 が変身状態のため決闘できません。
	 */
	public static final SystemMessageId C1_CANNOT_DUEL_WHILE_POLYMORPHED;
	
	/**
	 * ID: 2175<br>
	 * Message: 変身状態のパーティ メンバーがいるためパーティ同士での決闘ができません。
	 */
	public static final SystemMessageId CANNOT_PARTY_DUEL_WHILE_A_MEMBER_IS_POLYMORPHED;
	
	/**
	 * ID: 2176<br>
	 * Message: $s1の$s2属性を解除しました。
	 */
	public static final SystemMessageId S1_ELEMENTAL_POWER_REMOVED;
	
	/**
	 * ID: 2177<br>
	 * Message: +$s1$s2の$s3の属性を解除しました。
	 */
	public static final SystemMessageId S1_S2_ELEMENTAL_POWER_REMOVED;
	
	/**
	 * ID: 2178<br>
	 * Message: 属性の解除に失敗しました。
	 */
	public static final SystemMessageId FAILED_TO_REMOVE_ELEMENTAL_POWER;
	
	/**
	 * ID: 2179<br>
	 * Message: ギラン城の競売に最高額を入札中です。
	 */
	public static final SystemMessageId HIGHEST_BID_FOR_GIRAN_CASTLE;
	
	/**
	 * ID: 2180<br>
	 * Message: アデン城の競売に最高額を入札中です。
	 */
	public static final SystemMessageId HIGHEST_BID_FOR_ADEN_CASTLE;
	
	/**
	 * ID: 2181<br>
	 * Message: ルウン城の競売に最高額を入札中です。
	 */
	public static final SystemMessageId HIGHEST_BID_FOR_RUNE_CASTLE;
	
	/**
	 * ID: 2182<br>
	 * Message: 船に乗った状態では変身できません。
	 */
	public static final SystemMessageId CANT_POLYMORPH_ON_BOAT;
	
	/**
	 * ID: 2183<br>
	 * Message: $s1の要塞戦が終了しました。
	 */
	public static final SystemMessageId THE_FORTRESS_BATTLE_OF_S1_HAS_FINISHED;
	
	/**
	 * ID: 2184<br>
	 * Message: $s1血盟が$s2の要塞戦で勝利しました。
	 */
	public static final SystemMessageId S1_CLAN_IS_VICTORIOUS_IN_THE_FORTRESS_BATTLE_OF_S2;
	
	/**
	 * ID: 2185<br>
	 * Message: パーティ リーダーのみが入場することができます。
	 */
	public static final SystemMessageId ONLY_PARTY_LEADER_CAN_ENTER;
	
	/**
	 * ID: 2186<br>
	 * Message: これ以上の魂は吸収できません。
	 */
	public static final SystemMessageId SOUL_CANNOT_BE_ABSORBED_ANYMORE;
	
	/**
	 * ID: 2187<br>
	 * Message: 相手が突撃できない位置にいます。
	 */
	public static final SystemMessageId CANT_REACH_TARGET_TO_CHARGE;
	
	/**
	 * ID: 2188<br>
	 * Message: 他のエンチャントが行われています。前の作業を終了された後また行ってください。
	 */
	public static final SystemMessageId ENCHANTMENT_ALREADY_IN_PROGRESS;
	
	/**
	 * ID: 2189<br>
	 * Message: 現在地： $s1、$s2、$s3 (カマエル村付近)
	 */
	public static final SystemMessageId LOC_KAMAEL_VILLAGE_S1_S2_S3;
	
	/**
	 * ID: 2190<br>
	 * Message: 現在地：$s1、$s2、$s3 (荒地南側のキャンプ付近)
	 */
	public static final SystemMessageId LOC_WASTELANDS_CAMP_S1_S2_S3;
	
	/**
	 * ID: 2191<br>
	 * Message: 選択したオプションを適用するには、再びゲームをロードする必要があります。今すぐ適用しない場合は、次回ゲームを実行する際に適用されます。今すぐ適用しますか。
	 */
	public static final SystemMessageId CONFIRM_APPLY_SELECTIONS;
	
	/**
	 * ID: 2192<br>
	 * Message: アイテム競売に入札しました。
	 */
	public static final SystemMessageId BID_ON_ITEM_AUCTION;
	
	/**
	 * ID: 2193<br>
	 * Message: NPCとの距離が遠いため作動しません。
	 */
	public static final SystemMessageId TOO_FAR_FROM_NPC;
	
	/**
	 * ID: 2194<br>
	 * Message: 現在変身中の変身体では該当の効果を適用できません。
	 */
	public static final SystemMessageId CANT_APPLY_CURRENT_POLYMORPH_WITH_CORRESPONDING_EFFECTS;
	
	/**
	 * ID: 2195<br>
	 * Message: 魂が足りません。
	 */
	public static final SystemMessageId THERE_IS_NOT_ENOUGH_SOUL;
	
	/**
	 * ID: 2196<br>
	 * Message: 所有血盟なし
	 */
	public static final SystemMessageId NO_OWNED_CLAN;
	
	/**
	 * ID: 2197<br>
	 * Message: $s1血盟所有中
	 */
	public static final SystemMessageId OWNED_S1_CLAN;
	
	/**
	 * ID: 2198<br>
	 * Message: アイテム競売に最高額を入札中です。
	 */
	public static final SystemMessageId HIGHEST_BID_IN_ITEM_AUCTION;
	
	/**
	 * ID: 2199<br>
	 * Message: NPCサーバーが作動中止状態のため、インスタント ゾーンに入場できません。
	 */
	public static final SystemMessageId CANT_ENTER_INSTANCE_ZONE_NPC_SERVER_OFFLINE;
	
	/**
	 * ID: 2200<br>
	 * Message: NPCサーバー作動中止により該当インスタント ゾーンが削除されるため、しばらくしてから強制退場されます。
	 */
	public static final SystemMessageId INSTANCE_ZONE_TERMINATED_NPC_SERVER_OFFLINE;
	
	/**
	 * ID: 2201<br>
	 * Message: $s1年$s2月$s3月
	 */
	public static final SystemMessageId S1_YEARS_S2_MONTHS_S3_DAYS;
	
	/**
	 * ID: 2202<br>
	 * Message: $s1時$s2分$s3秒
	 */
	public static final SystemMessageId S1_HOURS_S2_MINUTES_S3_SECONDS;
	
	/**
	 * ID: 2203<br>
	 * Message: $s1月$s2日
	 */
	public static final SystemMessageId S1_MONTHS_S2_DAYS;
	
	/**
	 * ID: 2204<br>
	 * Message: $s1時
	 */
	public static final SystemMessageId S1_HOURS;
	
	/**
	 * ID: 2205<br>
	 * Message: ミニマップが使えない地域に入りました。ミニマップを閉じます。
	 */
	public static final SystemMessageId AREA_FORBIDS_MINIMAP;
	
	/**
	 * ID: 2206<br>
	 * Message: ミニマップが使える地域に入りました。
	 */
	public static final SystemMessageId AREA_ALLOWS_MINIMAP;
	
	/**
	 * ID: 2207<br>
	 * Message: ミニマップが使えない地域のため、ミニマップが開けません。
	 */
	public static final SystemMessageId CANT_OPEN_MINIMAP;
	
	/**
	 * ID: 2208<br>
	 * Message: スキル習得に必要なスキル レベル条件が合いません。
	 */
	public static final SystemMessageId YOU_DONT_MEET_SKILL_LEVEL_REQUIREMENTS;
	
	/**
	 * ID: 2209<br>
	 * Message: レーダーが作動しない地域
	 */
	public static final SystemMessageId AREA_WHERE_RADAR_CANNOT_BE_USED;
	
	/**
	 * ID: 2210<br>
	 * Message: エンチャントされていない状態に戻します。
	 */
	public static final SystemMessageId RETURN_TO_UNENCHANTED_CONDITION;
	
	/**
	 * ID: 2211<br>
	 * Message: 先行スキルを習得していないため新しいスキルを習得できません。
	 */
	public static final SystemMessageId YOU_MUST_LEARN_ONYX_BEAST_SKILL;
	
	/**
	 * ID: 2212<br>
	 * Message: スキル習得に必要なクエストをまだ遂行していません。
	 */
	public static final SystemMessageId NOT_COMPLETED_QUEST_FOR_SKILL_ACQUISITION;
	
	/**
	 * ID: 2213<br>
	 * Message: 変身した状態では船に乗船できません。
	 */
	public static final SystemMessageId CANT_BOARD_SHIP_POLYMORPHED;
	
	/**
	 * ID: 2214<br>
	 * Message: 今設定したセッティングで新しいキャラクターを生成します。よろしいですか。
	 */
	public static final SystemMessageId CONFIRM_CHARACTER_CREATION;
	
	/**
	 * ID: 2215<br>
	 * Message: $s1防御力
	 */
	public static final SystemMessageId S1_PDEF;
	
	/**
	 * ID: 2216<br>
	 * Message: CPUドライバーが最新バージョンではありません。AMDデュアルコア以上のCPUをお使いの場合、AMDのウェブサイトからAMDデュアルコアオプティマイザーをダウンロード、インストールして下さい。
	 */
	public static final SystemMessageId PLEASE_UPDATE_CPU_DRIVER;
	
	/**
	 * ID: 2217<br>
	 * Message: バリスタの爆破に成功して、血盟名声値が上がります。
	 */
	public static final SystemMessageId BALLISTA_DESTROYED_CLAN_REPU_INCREASED;
	
	/**
	 * ID: 2218<br>
	 * Message: メイン クラスの時にだけ使えるスキルです。
	 */
	public static final SystemMessageId MAIN_CLASS_SKILL_ONLY;
	
	/**
	 * ID: 2219<br>
	 * Message: すでに習得した下位血盟のスキルです。
	 */
	public static final SystemMessageId SQUAD_SKILL_ALREADY_ACQUIRED;
	
	/**
	 * ID: 2220<br>
	 * Message: 前段階レベルのスキルを習っていません。
	 */
	public static final SystemMessageId PREVIOUS_LEVEL_SKILL_NOT_LEARNED;
	
	/**
	 * ID: 2221<br>
	 * Message: 選んだ機能をアクティブにしますか。
	 */
	public static final SystemMessageId ACTIVATE_SELECTED_FUNTIONS_CONFIRM;
	
	/**
	 * ID: 2222<br>
	 * Message: 偵察兵の配置には15万アデナがかかります。配置しますか。
	 */
	public static final SystemMessageId SCOUT_COSTS_150000_ADENA;
	
	/**
	 * ID: 2223<br>
	 * Message: 要塞門の強化には20万アデナがかかります。強化しますか。
	 */
	public static final SystemMessageId FORTRESS_GATE_COSTS_200000_ADENA;
	
	/**
	 * ID: 2224<br>
	 * Message: ボウガン発射準備中です。
	 */
	public static final SystemMessageId CROSSBOW_PREPARING_TO_FIRE;
	
	/**
	 * ID: 2225<br>
	 * Message: これ以上習うスキルはありません。$s1次転職をしてから来てください。
	 */
	public static final SystemMessageId NO_SKILLS_TO_LEARN_RETURN_AFTER_S1_CLASS_CHANGE;
	
	/**
	 * ID: 2226<br>
	 * Message: ボルトが足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_BOLTS;
	
	/**
	 * ID: 2227<br>
	 * Message: 契約している上位の城の攻城戦には攻城側に登録はできません。
	 */
	public static final SystemMessageId NOT_POSSIBLE_TO_REGISTER_TO_CASTLE_SIEGE;
	
	/**
	 * ID: 2228<br>
	 * Message: 利用時間制限状態のインスタント ゾーン：
	 */
	public static final SystemMessageId INSTANCE_ZONE_TIME_LIMIT;
	
	/**
	 * ID: 2229<br>
	 * Message: 利用時間制限状態のインスタント ゾーンはありません。
	 */
	public static final SystemMessageId NO_INSTANCEZONE_TIME_LIMIT;
	
	/**
	 * ID: 2230<br>
	 * Message: $s1 $s2時間 $s3分後にご利用できます。
	 */
	public static final SystemMessageId AVAILABLE_AFTER_S1_S2_HOURS_S3_MINUTES;
	
	/**
	 * ID: 2231<br>
	 * Message: 契約している上位の城の血盟名声値が足らないため、補給品が支給されませんでした。
	 */
	public static final SystemMessageId REPUTATION_SCORE_FOR_CONTRACT_NOT_ENOUGH;
	
	/**
	 * ID: 2232<br>
	 * Message: $s1を砕く前にクリスタライズします。続けますか。
	 */
	public static final SystemMessageId S1_CRYSTALLIZED_BEFORE_DESTRUCTION;
	
	/**
	 * ID: 2233<br>
	 * Message: 上位の城と契約しているため、攻城登録はできません。
	 */
	public static final SystemMessageId CANT_REGISTER_TO_SIEGE_DUE_TO_CONTRACT;
	
	/**
	 * ID: 2234<br>
	 * Message: 選択したカマエル専用の英雄武器を使いますか。
	 */
	public static final SystemMessageId CONFIRM_KAMAEL_HERO_WEAPON;
	
	/**
	 * ID: 2235<br>
	 * Message: ご利用中のインスタント ゾーンが消えたため、入場できません。
	 */
	public static final SystemMessageId INSTANCE_ZONE_DELETED_CANT_ACCESSED;
	
	/**
	 * ID: 2236<br>
	 * Message: ワイバーン搭乗時間は残り$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_LEFT_ON_WYVERN;
	
	/**
	 * ID: 2237<br>
	 * Message: ワイバーン搭乗時間は残り$s1秒です。
	 */
	public static final SystemMessageId S1_SECONDS_LEFT_ON_WYVERN;
	
	/**
	 * ID: 2238<br>
	 * Message: $s1の攻城戦に参戦しました。この攻城戦は2時間行われます。
	 */
	public static final SystemMessageId PARTICIPATING_IN_SIEGE_OF_S1;
	
	/**
	 * ID: 2239<br>
	 * Message: 参戦した$s1の攻城戦が終了しました。
	 */
	public static final SystemMessageId SIEGE_OF_S1_FINIHSED;
	
	/**
	 * ID: 2240<br>
	 * Message: 血盟主委譲の申し込み手続き中である血盟はチーム バトル アジト戦に登録できません。
	 */
	public static final SystemMessageId CANT_REGISTER_TO_TEAM_BATTLE_CLAN_HALL_WAR_WHILE_LORD_ON_TRANSACTION_WAITING_LIST;
	
	/**
	 * ID: 2241<br>
	 * Message: チーム バトル アジト戦に登録した血盟は血盟主委譲の申し込みはできません。
	 */
	public static final SystemMessageId CANT_APPLY_ON_LORD_TRANSACTION_WHILE_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR;
	
	/**
	 * ID: 2242<br>
	 * Message: チーム バトル アジト戦に登録した血盟員は脱退もしくは追放ができません。
	 */
	public static final SystemMessageId MEMBERS_CANT_LEAVE_WHEN_REGISTERED_TO_TEAM_BATTLE_CLAN_HALL_WAR;
	
	/**
	 * ID: 2243<br>
	 * Message: 山賊の巣窟、ビースト ファーム アジトを所有する血盟主が委任する場合、変更後の血盟主ではなく変更前の血盟主がアジト戦に参加します。
	 */
	public static final SystemMessageId WHEN_BANDITSTRONGHOLD_WILDBEASTRESERVRE_CLANLORD_IN_DANGER_PREVIOUS_LORD_PARTICIPATES_IN_BATTLE;
	
	/**
	 * ID: 2244<br>
	 * Message: 終了時間まで残り$s1分です。
	 */
	public static final SystemMessageId S1_MINUTES_REMAINING;
	
	/**
	 * ID: 2245<br>
	 * Message: 終了時間まで残り$s1秒です。
	 */
	public static final SystemMessageId S1_SECONDS_REMAINING;
	
	/**
	 * ID: 2246<br>
	 * Message: $s1分後に予選が始まります。
	 */
	public static final SystemMessageId CONTEST_BEGIN_IN_S1_MINUTES;
	
	/**
	 * ID: 2247<br>
	 * Message: 変身中は乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_TRANSFORMED;
	
	/**
	 * ID: 2248<br>
	 * Message: 石化状態では乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_PETRIFIED;
	
	/**
	 * ID: 2249<br>
	 * Message: 死亡状態では乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_DEAD;
	
	/**
	 * ID: 2250<br>
	 * Message: フィッシング中は乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_FISHING;
	
	/**
	 * ID: 2251<br>
	 * Message: 戦闘中は乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_BATTLE;
	
	/**
	 * ID: 2252<br>
	 * Message: 決闘中は乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_IN_A_DUEL;
	
	/**
	 * ID: 2253<br>
	 * Message: 座った状態では乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_SITTING;
	
	/**
	 * ID: 2254<br>
	 * Message: スキル詠唱中は乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_CASTING;
	
	/**
	 * ID: 2255<br>
	 * Message: 呪われた武器を装備した状態では乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_CURSED_WEAPON_IS_EQUIPPED;
	
	/**
	 * ID: 2256<br>
	 * Message: 旗を持った状態では乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_HOLDING_A_FLAG;
	
	/**
	 * ID: 2257<br>
	 * Message: ペットおよび召喚獣を召喚した状態では乗り物に搭乗できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_AN_AIRSHIP_WHILE_A_PET_OR_A_SERVITOR_IS_SUMMONED;
	
	/**
	 * ID: 2258<br>
	 * Message: すでに別の乗り物に搭乗している状態です。
	 */
	public static final SystemMessageId YOU_HAVE_ALREADY_BOARDED_ANOTHER_AIRSHIP;
	
	/**
	 * ID: 2259<br>
	 * Message: 現在地：$s1、$s2、$s3 (幻想の島付近)
	 */
	public static final SystemMessageId LOC_FANTASY_ISLAND_S1_S2_S3;
	
	/**
	 * ID: 2260<br>
	 * Message: 空腹感ゲージを10％以上に満たし続けなければ、ペットが逃げ出すことがあります。
	 */
	public static final SystemMessageId PET_CAN_RUN_AWAY_WHEN_HUNGER_BELOW_10_PERCENT;
	
	/**
	 * ID: 2261<br>
	 * Message: $c1が$c2に$s3のダメージを与えました。
	 */
	public static final SystemMessageId C1_GAVE_C2_DAMAGE_OF_S3;
	
	/**
	 * ID: 2262<br>
	 * Message: $c1が$c2から$s3のダメージを負いました。
	 */
	public static final SystemMessageId C1_RECEIVED_DAMAGE_OF_S3_FROM_C2;
	
	/**
	 * ID: 2263<br>
	 * Message: $c1が$c2により$s3のダメージを負いました。
	 */
	public static final SystemMessageId C1_RECEIVED_DAMAGE_OF_S3_THROUGH_C2;
	
	/**
	 * ID: 2264<br>
	 * Message: $c1が$c2の攻撃を避けました。
	 */
	public static final SystemMessageId C1_EVADED_C2_ATTACK;
	
	/**
	 * ID: 2265<br>
	 * Message: $c1の攻撃が外れました。
	 */
	public static final SystemMessageId C1_ATTACK_WENT_ASTRAY;
	
	/**
	 * ID: 2266<br>
	 * Message: $c1のクリティカル ヒット！
	 */
	public static final SystemMessageId C1_HAD_CRITICAL_HIT;
	
	/**
	 * ID: 2267<br>
	 * Message: $c1が$c2のドレインに抵抗しました。
	 */
	public static final SystemMessageId C1_RESISTED_C2_DRAIN;
	
	/**
	 * ID: 2268<br>
	 * Message: $c1が攻撃に失敗しました。
	 */
	public static final SystemMessageId C1_ATTACK_FAILED;
	
	/**
	 * ID: 2269<br>
	 * Message: $c1が$c2の魔法に抵抗しました。
	 */
	public static final SystemMessageId C1_RESISTED_C2_DRAIN2;
	
	/**
	 * ID: 2270<br>
	 * Message: 魔法の炎により$c1が$s2のダメージを負いました。
	 */
	public static final SystemMessageId C1_RECEIVED_DAMAGE_FROM_S2_THROUGH_FIRE_OF_MAGIC;
	
	/**
	 * ID: 2271<br>
	 * Message: $c1が$c2の魔法に若干抵抗しました。
	 */
	public static final SystemMessageId C1_WEAKLY_RESISTED_C2_MAGIC;
	
	/**
	 * ID: 2272<br>
	 * Message: 一般チャット モードでは指定したキーをショートカットキーに指定することはできません。
	 */
	public static final SystemMessageId USE_SHORTCUT_CONFIRM;
	
	/**
	 * ID: 2273<br>
	 * Message: サブ クラスの状態では習得できないスキルです。メイン クラスに変更してからもう一度行ってください。
	 */
	public static final SystemMessageId SKILL_NOT_FOR_SUBCLASS;
	
	/**
	 * ID: 2276<br>
	 * Message: レジスタンスの要塞を奪還しました。
	 */
	public static final SystemMessageId NPCS_RECAPTURED_FORTRESS;
	
	/**
	 * ID: 2291<br>
	 * Message: パーティに参加した状態でのみ機械操作が可能です。
	 */
	public static final SystemMessageId CAN_OPERATE_MACHINE_WHEN_IN_PARTY;
	
	/**
	 * ID: 2293<br>
	 * Message: 現在地：$s1、$s2、$s3 (鋼鉄の城内部)
	 */
	public static final SystemMessageId LOC_IN_STEEL_CITADEL_S1_S2_S3;
	
	/**
	 * ID: 2296<br>
	 * Message: バイタリティ ポイントを獲得しました。
	 */
	public static final SystemMessageId GAINED_VITALITY_POINTS;
	
	/**
	 * ID: 2301<br>
	 * Message: 現在地：鋼鉄の城内郭
	 */
	public static final SystemMessageId LOC_STEEL_CITADEL;
	
	/**
	 * ID: 2302<br>
	 * Message: トッピング アイテムが到着しました。各村の境界の商人からプレゼントを受け取れます。
	 */
	public static final SystemMessageId YOUR_VITAMIN_ITEM_HAS_ARRIVED;
	
	/**
	 * ID: 2303<br>
	 * Message: $s1の再使用時間は残り$s2秒です。
	 */
	public static final SystemMessageId S2_SECONDS_REMAINING_FOR_REUSE_S1;
	
	/**
	 * ID: 2304<br>
	 * Message: $s1の再使用時間は残り$s2分$s3秒です。
	 */
	public static final SystemMessageId S2_MINUTES_S3_SECONDS_REMAINING_FOR_REUSE_S1;
	
	/**
	 * ID: 2305<br>
	 * Message: $s1の再使用時間は残り$s2時間$s3分$s4秒です。
	 */
	public static final SystemMessageId S2_HOURS_S3_MINUTES_S4_SECONDS_REMAINING_FOR_REUSE_S1;
	
	/**
	 * ID: 2306<br>
	 * Message: 勇気のお守りの効果により復活できます。今すぐに復活しますか。\n
	 */
	public static final SystemMessageId RESURRECT_USING_CHARM_OF_COURAGE;
	
	/**
	 * ID: 2314<br>
	 * Message: バイタリティが満ちあふれます。
	 */
	public static final SystemMessageId VITALITY_IS_AT_MAXIMUM;
	
	/**
	 * ID: 2315<br>
	 * Message: バイタリティが増加しました。
	 */
	public static final SystemMessageId VITALITY_HAS_INCREASED;
	
	/**
	 * ID: 2316<br>
	 * Message: バイタリティが減少しました。
	 */
	public static final SystemMessageId VITALITY_HAS_DECREASED;
	
	/**
	 * ID: 2317<br>
	 * Message: バイタリティがすべてなくなりました。
	 */
	public static final SystemMessageId VITALITY_IS_EXHAUSTED;
	
	/**
	 * ID: 2319<br>
	 * Message: $s1の名声値を手に入れました。
	 */
	public static final SystemMessageId ACQUIRED_S1_REPUTATION_SCORE;
	
	/**
	 * ID: 2321<br>
	 * Message: 現在地：カマロカ内部
	 */
	public static final SystemMessageId LOC_KAMALOKA;
	
	/**
	 * ID: 2322<br>
	 * Message: 現在地：ニア カマロカ内部
	 */
	
	public static final SystemMessageId LOC_NIA_KAMALOKA;
	/**
	 * ID: 2323<br>
	 * Message: 現在地：リム カマロカ内部
	 */
	public static final SystemMessageId LOC_RIM_KAMALOKA;
	
	/**
	 * ID: 2326<br>
	 * Message: 血盟名声値 50点を獲得しました。
	 */
	public static final SystemMessageId ACQUIRED_50_CLAN_FAME_POINTS;
	
	/**
	 * ID: 2327<br>
	 * Message: 名声値が足りません。
	 */
	public static final SystemMessageId NOT_ENOUGH_FAME_POINTS;
	
	/**
	 * ID: 2333<br>
	 * Message: インベントリの重さ/個数制限を超えているため、トッピング アイテムが受取れません。
	 */
	public static final SystemMessageId YOU_CANNOT_RECEIVE_THE_VITAMIN_ITEM;
	
	/**
	 * ID: 2335<br>
	 * Message: 受け取れるトッピング アイテムはありません。
	 */
	public static final SystemMessageId THERE_ARE_NO_MORE_VITAMIN_ITEMS_TO_BE_FOUND;
	
	/**
	 * ID: 2336<br>
	 * Message: ハーフキル！
	 */
	public static final SystemMessageId HALF_KILL;
	
	/**
	 * ID: 2337<br>
	 * Message: ハーフ キルスキルに当たりCPが消えます。
	 */
	public static final SystemMessageId CP_DISAPPEARS_WHEN_HIT_WITH_A_HALF_KILL_SKILL;
	
	/**
	 * ID: 2348<br>
	 * Message: 戦闘中はフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_BATTLE;
	
	/**
	 * ID: 2349<br>
	 * Message: 攻城戦、要塞戦、アジト戦などの大規模戦闘への参加中はフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING;
	
	/**
	 * ID: 2350<br>
	 * Message: 決闘中はフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_DURING_A_DUEL;
	
	/**
	 * ID: 2351<br>
	 * Message: 飛行中はフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_FLYING;
	
	/**
	 * ID: 2352<br>
	 * Message: オリンピアード競技進行中はフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_PARTICIPATING_IN_AN_OLYMPIAD_MATCH;
	
	/**
	 * ID: 2353<br>
	 * Message: 石化、麻痺状態ではフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_PARALYZED;
	
	/**
	 * ID: 2354<br>
	 * Message: 死亡状態ではフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_WHILE_YOU_ARE_DEAD;
	
	/**
	 * ID: 2355<br>
	 * Message: この地域ではフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_IN_THIS_AREA;
	
	/**
	 * ID: 2356<br>
	 * Message: 水中ではフリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_UNDERWATER;
	
	/**
	 * ID: 2357<br>
	 * Message: インスタントゾーンでは フリーテレポート機能は使用できません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_IN_AN_INSTANT_ZONE;
	
	/**
	 * ID: 2358<br>
	 * Message: テレポート位置を保存するスペースがありません。
	 */
	public static final SystemMessageId YOU_HAVE_NO_SPACE_TO_SAVE_THE_TELEPORT_LOCATION;
	
	/**
	 * ID: 2359<br>
	 * Message: テレポートアイテムがないため、テレポートできません。
	 */
	public static final SystemMessageId YOU_CANNOT_TELEPORT_BECAUSE_YOU_DO_NOT_HAVE_A_TELEPORT_ITEM;
	
	/**
	 * ID: 2366<br>
	 * Message: 期間限定アイテムを削除しました。
	 */
	public static final SystemMessageId TIME_LIMITED_ITEM_DELETED;
	
	/**
	 * ID: 2372<br>
	 * Message: ハントサポートペットがまもなくいなくなります。
	 */
	public static final SystemMessageId THERE_NOT_MUCH_TIME_REMAINING_UNTIL_HELPER_LEAVES;
	
	/**
	 * ID: 2373<br>
	 * Message: ハントサポートペットがあなたのもとから去る時間となりました。
	 */
	public static final SystemMessageId THE_HELPER_PET_LEAVING;
	
	/**
	 * ID: 2375<br>
	 * Message: ハントサポートペットがまもなくいなくなります。今からは戻せません。
	 */
	public static final SystemMessageId THE_HELPER_PET_CANNOT_BE_RETURNED;
	
	/**
	 * ID: 2376<br>
	 * Message: 交換中にはトッピング アイテムを受け取れません。
	 */
	public static final SystemMessageId YOU_CANNOT_RECEIVE_A_VITAMIN_ITEM_DURING_AN_EXCHANGE;
	
	/**
	 * ID: 2390<br>
	 * Message: フリーテレポート スロットが最大限に拡張されています。これ以上の拡張はできません。
	 */
	public static final SystemMessageId YOUR_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_REACHED_ITS_MAXIMUM_LIMIT;
	
	/**
	 * ID: 2396<br>
	 * Message: ペット/召喚獣のこのスキルは再使用中であるため使えません。
	 */
	public static final SystemMessageId PET_SKILL_CANNOT_BE_USED_RECHARCHING;
	
	/**
	 * ID: 2398<br>
	 * Message: スロットが非アクティブになっています。
	 */
	public static final SystemMessageId YOU_HAVE_NO_OPEN_MY_TELEPORTS_SLOTS;
	
	/**
	 * ID: 2440<br>
	 * Message: $c1 はクラス無制限競技種目の待機者リストにすでに登録されています。
	 */
	public static final SystemMessageId C1_IS_ALREADY_REGISTERED_NON_CLASS_LIMITED_EVENT_TEAMS;
	
	/**
	 * ID: 2441<br>
	 * Message: チーム競技の申し込みはパーティリーダーしかできません。
	 */
	public static final SystemMessageId ONLY_PARTY_LEADER_CAN_REQUEST_TEAM_MATCH;
	
	/**
	 * ID: 2442<br>
	 * Message: 条件が合わないため、申し込みできません。チーム競技に参加するためには3人以上のパーティを作らなければなりません。
	 */
	public static final SystemMessageId PARTY_REQUIREMENTS_NOT_MET;
	
	/**
	 * ID: 2936<br>
	 * Message: 所属の領地とスクロールが一致しないため、偽装できません。
	 */
	public static final SystemMessageId THE_DISGUISE_SCROLL_MEANT_FOR_DIFFERENT_TERRITORY;
	
	/**
	 * ID: 2937<br>
	 * Message: 領地を持っている血盟の血盟員は偽装できません。
	 */
	public static final SystemMessageId TERRITORY_OWNING_CLAN_CANNOT_USE_DISGUISE_SCROLL;
	
	/**
	 * ID: 2955<br>
	 * Message: 領地戦専用の偽装、変身は領地戦開始20分前から終了10分後まで使えます。
	 */
	public static final SystemMessageId TERRITORY_WAR_SCROLL_CAN_NOT_USED_NOW;
	
	/**
	 * ID: 2400<br>
	 * Message: 現在使用中のインスタント ゾーン：$s1
	 */
	public static final SystemMessageId INSTANT_ZONE_CURRENTLY_INUSE_S1;
	
	/**
	 * ID: 2402<br>
	 * Message: 領地戦の申し込みは既に締め切られています。
	 */
	public static final SystemMessageId THE_TERRITORY_WAR_REGISTERING_PERIOD_ENDED;
	
	/**
	 * ID: 2403<br>
	 * Message: 領地戦開始まであと10分です。
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_10_MINUTES;
	
	/**
	 * ID: 2404<br>
	 * Message: 領地戦開始まであと5分です。
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_5_MINUTES;
	
	/**
	 * ID: 2405<br>
	 * Message: 領地戦開始まであと1分です。
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_1_MINUTE;
	
	/**
	 * ID: 2408<br>
	 * Message: クラス無制限競技種目の待機者リストに登録されました。
	 */
	public static final SystemMessageId YOU_HAVE_REGISTERED_IN_A_WAITING_LIST_OF_TEAM_GAMES;
	
	/**
	 * ID: 2409<br>
	 * Message: フリーテレポート スロットが拡張されました。
	 */
	public static final SystemMessageId THE_NUMBER_OF_MY_TELEPORTS_SLOTS_HAS_BEEN_INCREASED;
	
	/**
	 * ID: 2410<br>
	 * Message: フリーテレポートでは行けない地域です。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_MY_TELEPORTS_TO_REACH_THIS_AREA;
	
	/**
	 * ID: 2424<br>
	 * Message: 採集に失敗しました。
	 */
	public static final SystemMessageId THE_COLLECTION_HAS_FAILED;
	
	/**
	 * ID: 2448<br>
	 * Message: お誕生日おめでとうございます！アレグリアがバースデイ プレゼントをお届けします。
	 */
	public static final SystemMessageId YOUR_BIRTHDAY_GIFT_HAS_ARRIVED;
	
	/**
	 * ID: 2449<br>
	 * Message: 誕生日まであと$s1日です。誕生日になったら、アレグリアが心を込めて準備したプレゼントを郵便でお届けします。
	 */
	public static final SystemMessageId THERE_ARE_S1_DAYS_UNTIL_YOUR_CHARACTERS_BIRTHDAY;
	
	/**
	 * ID: 2450<br>
	 * Message: $c1 の誕生日は$s2年$s3月$s4日です。
	 */
	public static final SystemMessageId C1_BIRTHDAY_IS_S3_S4_S2;
	
	/**
	 * ID: 2451<br>
	 * Message: アーマー セットの装着を解除したため、クロークの装着も解除されます。
	 */
	public static final SystemMessageId CLOAK_REMOVED_BECAUSE_ARMOR_SET_REMOVED;
	
	/**
	 * ID: 2455<br>
	 * Message: 召喚された飛行船が存在しないため、搭乗できません。
	 */
	public static final SystemMessageId THE_AIRSHIP_MUST_BE_SUMMONED_TO_BOARD;
	
	/**
	 * ID: 2456<br>
	 * Message: 飛行船を獲得するには、血盟レベルが5以上でなければなりません。
	 */
	public static final SystemMessageId THE_AIRSHIP_NEED_CLANLVL_5_TO_SUMMON;
	
	/**
	 * ID: 2457<br>
	 * Message: 飛行船召喚許可書を入力していないか、血盟所有の飛行船が存在しないため、飛行船を召喚できません。
	 */
	public static final SystemMessageId THE_AIRSHIP_NEED_LICENSE_TO_SUMMON;
	
	/**
	 * ID: 2458<br>
	 * Message: 血盟所有の飛行船はすでに他の血盟員が使用しています。
	 */
	public static final SystemMessageId THE_AIRSHIP_ALREADY_USED;
	
	/**
	 * ID: 2459<br>
	 * Message: 飛行船召喚許可書をすでに獲得しています。
	 */
	public static final SystemMessageId THE_AIRSHIP_SUMMON_LICENSE_ALREADY_ACQUIRED;
	
	/**
	 * ID: 2460<br>
	 * Message: 血盟所有の飛行船はすでに存在しています。
	 */
	public static final SystemMessageId THE_AIRSHIP_IS_ALREADY_EXISTS;
	
	/**
	 * ID: 2461<br>
	 * Message: 血盟所有の飛行船は血盟主のみ買えます。
	 */
	public static final SystemMessageId THE_AIRSHIP_NO_PRIVILEGES;
	
	/**
	 * ID: 2462<br>
	 * Message: $s1が不足しているため、飛行船を召喚できません。
	 */
	public static final SystemMessageId THE_AIRSHIP_NEED_MORE_S1;
	
	/**
	 * ID: 2463<br>
	 * Message: 飛行船の燃料（EP）がなくなりそうです。
	 */
	public static final SystemMessageId THE_AIRSHIP_FUEL_SOON_RUN_OUT;
	
	/**
	 * ID: 2464<br>
	 * Message: 飛行船の燃料(EP)をすべて使い切りました。この状態では飛行船のスピードが大幅に減速します。
	 */
	public static final SystemMessageId THE_AIRSHIP_FUEL_RUN_OUT;
	
	/**
	 * ID: 2465<br>
	 * Message: クラス無制限チーム競技を選択しました。参加しますか。
	 */
	public static final SystemMessageId OLYMPIAD_3VS3_CONFIRM;
	
	/**
	 * ID: 2491<br>
	 * Message: 飛行船の燃料が足らないため、テレポートできません。
	 */
	public static final SystemMessageId THE_AIRSHIP_CANNOT_TELEPORT;
	
	/**
	 * ID: 2492<br>
	 * Message: 飛行船が召喚されました。%s分後に自動的に出発します。
	 */
	public static final SystemMessageId THE_AIRSHIP_SUMMONED;
	
	/**
	 * ID: 2500<br>
	 * Message: 採集に成功しました。
	 */
	public static final SystemMessageId THE_COLLECTION_HAS_SUCCEEDED;
	
	/**
	 * ID: 2701<br>
	 * Message: 競技準備中です。しばらくしてから再度お試しください。
	 */
	public static final SystemMessageId MATCH_BEING_PREPARED_TRY_LATER;
	
	/**
	 * ID: 2702<br>
	 * Message: 人数が合わないため、チームから除外されました。
	 */
	public static final SystemMessageId EXCLUDED_FROM_MATCH_DUE_INCORRECT_COUNT;
	
	/**
	 * ID: 2703<br>
	 * Message: 人員の比率が合わないため、チームが調整されました。
	 */
	public static final SystemMessageId TEAM_ADJUSTED_BECAUSE_WRONG_POPULATION_RATIO;
	
	/**
	 * ID: 2704<br>
	 * Message: 定員を超過したため、登録できません。
	 */
	public static final SystemMessageId CANNOT_REGISTER_CAUSE_QUEUE_FULL;
	
	/**
	 * ID: 2705<br>
	 * Message: 競技待機時間が1分間延長されました。
	 */
	public static final SystemMessageId MATCH_WAITING_TIME_EXTENDED;
	
	/**
	 * ID: 2706<br>
	 * Message: 条件が合わないため、入場できません。
	 */
	public static final SystemMessageId CANNOT_ENTER_CAUSE_DONT_MATCH_REQUIREMENTS;
	
	/**
	 * ID: 2707<br>
	 * Message: 競技登録キャンセル後10数秒間は再度の申し込みはできません。
	 */
	public static final SystemMessageId CANNOT_REQUEST_REGISTRATION_10_SECS_AFTER;
	
	/**
	 * ID: 2708<br>
	 * Message: 呪われた武器を持っているため、登録できません。
	 */
	public static final SystemMessageId CANNOT_REGISTER_PROCESSING_CURSED_WEAPON;
	
	/**
	 * ID: 2709<br>
	 * Message: オリンピアード、地下コロシアム、クラトのキューブに申し込み中は登録できません。
	 */
	public static final SystemMessageId COLISEUM_OLYMPIAD_KRATEIS_APPLICANTS_CANNOT_PARTICIPATE;
	
	/**
	 * ID: 2710<br>
	 * Message: 現在地： $s1, $s2, $s3（クセルス同盟の連合基地のそば）
	 */
	public static final SystemMessageId LOC_KEUCEREUS_S1_S2_S3;
	
	/**
	 * ID: 2711<br>
	 * Message: 現在地： $s1, $s2, $s3（不滅の種の内部）
	 */
	public static final SystemMessageId LOC_IN_SEED_INFINITY_S1_S2_S3;
	
	/**
	 * ID: 2712<br>
	 * Message: 現在地： $s1, $s2, $s3（破滅の種の内部）
	 */
	public static final SystemMessageId LOC_OUT_SEED_INFINITY_S1_S2_S3;
	
	/**
	 * ID: 2716<br>
	 * Message: 現在地： $s1, $s2, $s3（クラフトの内部）
	 */
	public static final SystemMessageId LOC_CLEFT_S1_S2_S3;
	
	/**
	 * ID: 2720<br>
	 * Message: 今からインスタント ゾーン：$s1への入場が制限されます。次の入場可能時間を調べるにはコマンド「/instantzone」を使います。
	 */
	public static final SystemMessageId INSTANT_ZONE_S1_RESTRICTED;
	
	/**
	 * ID: 2721<br>
	 * Message: ここは飛行体に搭乗、搭乗解除ができない地域です。
	 */
	public static final SystemMessageId BOARD_OR_CANCEL_NOT_POSSIBLE_HERE;
	
	/**
	 * ID: 2722<br>
	 * Message: 発着場には他の飛行船が召喚されています。また後ほどご利用ください。
	 */
	public static final SystemMessageId ANOTHER_AIRSHIP_ALREADY_SUMMONED;
	
	/**
	 * ID: 2727<br>
	 * Message: 条件が合わないため乗れません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOARD_NOT_MEET_REQUEIREMENTS;
	
	/**
	 * ID: 2729<br>
	 * Message: 変身中には対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_TRANSFORMED;
	
	/**
	 * ID: 2730<br>
	 * Message: 石化状態では対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_YOU_ARE_PETRIFIED;
	
	/**
	 * ID: 2731<br>
	 * Message: 死んだ状態では対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHEN_YOU_ARE_DEAD;
	
	/**
	 * ID: 2732<br>
	 * Message: 釣り中には対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_FISHING;
	
	/**
	 * ID: 2733<br>
	 * Message: 戦闘中には対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_BATTLE;
	
	/**
	 * ID: 2734<br>
	 * Message: 決闘中には対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_DUEL;
	/**
	 * ID: 2735<br>
	 * Message: 座った状態では対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_IN_A_SITTING_POSITION;
	
	/**
	 * ID: 2736<br>
	 * Message: スキル詠唱中には対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_USING_A_SKILL;
	
	/**
	 * ID: 2737<br>
	 * Message: 呪われた武器を装着した状態では対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_A_CURSED_WEAPON_IS_EQUIPPED;
	
	/**
	 * ID: 2738<br>
	 * Message: 旗を持った状態では対象を操縦できません。
	 */
	public static final SystemMessageId YOU_CANNOT_CONTROL_THE_HELM_WHILE_HOLDING_A_FLAG;
	
	/**
	 * ID: 2750<br>
	 * Message: $s1守護物、破壊！守護物を$c2が奪取しました。
	 */
	public static final SystemMessageId THE_S1_WARD_HAS_BEEN_DESTROYED_C2_HAS_THE_WARD;
	
	/**
	 * ID: 2751<br>
	 * Message: $s1守護物を拾得したキャラクターが死亡しました。
	 */
	public static final SystemMessageId THE_CHAR_THAT_ACQUIRED_S1_WARD_HAS_BEEN_KILLED;
	
	/**
	 * ID: 2762<br>
	 * Message: 距離が離れているため、対象を操縦できません。
	 */
	public static final SystemMessageId CANT_CONTROL_TOO_FAR;
	
	/**
	 * ID: 2764<br>
	 * Message: この連合チャンネルは人数超過のため入れません。
	 */
	public static final SystemMessageId YOU_CANNOT_ENTER_BECAUSE_MAXIMUM_ENTRANTS;
	
	/**
	 * ID: 2765<br>
	 * Message: 連合チャンネルリーダー以外は入れません。
	 */
	public static final SystemMessageId ONLY_ALLIANCE_CHANNEL_LEADER_CAN_ENTER;
	
	/**
	 * ID: 2766<br>
	 * Message: 不滅の種レベル1攻撃中
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_1_ATTACK_IN_PROGRESS;
	
	/**
	 * ID: 2767<br>
	 * Message: 不滅の種レベル2攻撃中
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_2_ATTACK_IN_PROGRESS;
	
	/**
	 * ID: 2768<br>
	 * Message: 不滅の種占領完了
	 */
	public static final SystemMessageId SEED_OF_INFINITY_CONQUEST_COMPLETE;
	
	/**
	 * ID: 2769<br>
	 * Message: 不滅の種レベル1防御中
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_1_DEFENSE_IN_PROGRESS;
	
	/**
	 * ID: 2770<br>
	 * Message: 不滅の種レベル2防御中
	 */
	public static final SystemMessageId SEED_OF_INFINITY_STAGE_2_DEFENSE_IN_PROGRESS;
	
	/**
	 * ID: 2771<br>
	 * Message: 破滅の種攻撃中
	 */
	public static final SystemMessageId SEED_OF_DESTRUCTION_ATTACK_IN_PROGRESS;
	
	/**
	 * ID: 2772<br>
	 * Message: 破滅の種占領完了
	 */
	public static final SystemMessageId SEED_OF_DESTRUCTION_CONQUEST_COMPLETE;
	
	/**
	 * ID: 2773<br>
	 * Message: 破滅の種防御中
	 */
	public static final SystemMessageId SEED_OF_DESTRUCTION_DEFENSE_IN_PROGRESS;
	
	/**
	 * ID: 2777<br>
	 * Message: 飛行船の召喚許可書が入力できました。貴血盟は今後、飛行船の召喚ができるようになります。
	 */
	public static final SystemMessageId THE_AIRSHIP_SUMMON_LICENSE_ENTERED;
	
	/**
	 * ID: 2778<br>
	 * Message: 守護物を持っている状態では瞬間移動効果が適用されません。
	 */
	public static final SystemMessageId YOU_CANNOT_TELEPORT_WHILE_IN_POSSESSION_OF_A_WARD;
	
	/**
	 * ID: 2793<br>
	 * Message: インスタント ゾーンの最少人数（$s1人）に達していないため、入れません。
	 */
	public static final SystemMessageId YOU_MUST_HAVE_MINIMUM_OF_S1_PEOPLE_TO_ENTER;
	
	/**
	 * ID: 2795<br>
	 * Message: 既に他の領地戦を申し込んでいる状態です。
	 */
	public static final SystemMessageId YOU_ALREADY_REQUESTED_TW_REGISTRATION;
	
	/**
	 * ID: 2796<br>
	 * Message: 領地を持っている血盟は領地戦に傭兵の資格では参戦できません。
	 */
	public static final SystemMessageId THE_TERRITORY_OWNER_CLAN_CANNOT_PARTICIPATE_AS_MERCENARIES;
	
	/**
	 * ID: 2797<br>
	 * Message: 今は領地戦申込期間ではありません。
	 */
	public static final SystemMessageId NOT_TERRITORY_REGISTRATION_PERIOD;
	
	/**
	 * ID: 2798<br>
	 * Message: 領地戦終了$s1時間前！
	 */
	public static final SystemMessageId THE_TERRITORY_WAR_WILL_END_IN_S1_HOURS;
	
	/**
	 * ID: 2799<br>
	 * Message: 領地戦終了$s1分前！
	 */
	public static final SystemMessageId THE_TERRITORY_WAR_WILL_END_IN_S1_MINUTES;
	
	/**
	 * ID: 2900<br>
	 * Message: 領地戦終了$s1秒前！
	 */
	public static final SystemMessageId S1_SECONDS_TO_THE_END_OF_TERRITORY_WAR;
	
	/**
	 * ID: 2901<br>
	 * Message: 対象が同じ領地所属のため、強制攻撃はできません。
	 */
	public static final SystemMessageId YOU_CANNOT_ATTACK_A_MEMBER_OF_THE_SAME_TERRITORY;
	
	/**
	 * ID: 2902<br>
	 * Message: 守護物を得ました。早急に味方の前哨基地に移動してください。
	 */
	public static final SystemMessageId YOU_VE_ACQUIRED_THE_WARD;
	
	/**
	 * ID: 2903<br>
	 * Message: 領地戦が始まりました。
	 */
	public static final SystemMessageId TERRITORY_WAR_HAS_BEGUN;
	
	/**
	 * ID: 2904<br>
	 * Message: 領地戦が終わりました。
	 */
	public static final SystemMessageId TERRITORY_WAR_HAS_ENDED;
	
	/**
	 * ID: 2911<br>
	 * Message: $c1 に友人登録を申し込みました。
	 */
	public static final SystemMessageId YOU_REQUESTED_C1_TO_BE_FRIEND;
	
	/**
	 * ID: 2913<br>
	 * Message: $s1血盟が$s2守護物の奪取に成功しました。
	 */
	public static final SystemMessageId CLAN_S1_HAS_SUCCEDED_IN_CAPTURING_S2_TERRITORY_WARD;
	
	/**
	 * ID: 2914<br>
	 * Message: 領地戦開始20分前です。ただいまから戦場チャンネルがアクティブになります。専用偽装、変装ができます。
	 */
	public static final SystemMessageId TERRITORY_WAR_BEGINS_IN_20_MINUTES;
	
	/**
	 * ID: 2922<br>
	 * Message: ブロックチェッカー終了5秒前！
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_5;
	
	/**
	 * ID: 2923<br>
	 * Message: ブロックチェッカー終了4秒前！
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_4;
	
	/**
	 * ID: 2924<br>
	 * Message: 飛行変体状態では種の内部には入れません。
	 */
	public static final SystemMessageId YOU_CANNOT_ENTER_SEED_IN_FLYING_TRANSFORM;
	
	/**
	 * ID: 2925<br>
	 * Message: ブロックチェッカー終了3秒前！
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_3;
	
	/**
	 * ID: 2926<br>
	 * Message: ブロックチェッカー終了2秒前！
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_2;
	
	/**
	 * ID: 2927<br>
	 * Message: ブロックチェッカー終了1秒前！
	 */
	public static final SystemMessageId BLOCK_CHECKER_ENDS_1;
	
	/**
	 * ID: 2928<br>
	 * Message: $c1のチームが競技で勝利しました。
	 */
	public static final SystemMessageId TEAM_C1_WON;
	
	/**
	 * ID: 2961<br>
	 * Message: アイテム$s1が$s2個必要です。
	 */
	public static final SystemMessageId S2_UNIT_OF_THE_ITEM_S1_REQUIRED;
	
	/**
	 * ID: 2964<br>
	 * Message: ノーブレスに任命される場合、遂行中の関連クエストが削除されます。よろしいですか。
	 */
	public static final SystemMessageId CANCEL_NOBLESSE_QUESTS;
	
	/**
	 * ID: 2966<br>
	 * Message: 代引郵便はアイテムを添付しなければ差出できません。アイテムを添付してください。
	 */
	public static final SystemMessageId PAYMENT_REQUEST_NO_ITEM;
	
	/**
	 * ID: 2968<br>
	 * Message: このキャラクターの郵便の限度数(240通)を超えたため、差出できません。
	 */
	public static final SystemMessageId CANT_FORWARD_MAIL_LIMIT_EXCEEDED;
	
	/**
	 * ID: 2969<br>
	 * Message: 前回郵便を差出してから10秒経っていないため、差出できません。
	 */
	public static final SystemMessageId CANT_FORWARD_LESS_THAN_MINUTE;
	
	/**
	 * ID: 2970<br>
	 * Message: ピースゾーンでないところには発送できません。
	 */
	public static final SystemMessageId CANT_FORWARD_NOT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2971<br>
	 * Message: トレード中には発送できません。
	 */
	public static final SystemMessageId CANT_FORWARD_DURING_EXCHANGE;
	
	/**
	 * ID: 2972<br>
	 * Message: 個人商店、または工房を使用中のため発送できません。
	 */
	public static final SystemMessageId CANT_FORWARD_PRIVATE_STORE;
	
	/**
	 * ID: 2973<br>
	 * Message: アイテム強化、属性強化中には発送できません。
	 */
	public static final SystemMessageId CANT_FORWARD_DURING_ENCHANT;
	
	/**
	 * ID: 2974<br>
	 * Message: 発送するアイテムが適切でないため発送できません。
	 */
	public static final SystemMessageId CANT_FORWARD_BAD_ITEM;
	
	/**
	 * ID: 2975<br>
	 * Message: アデナが不足しているため発送できません。
	 */
	public static final SystemMessageId CANT_FORWARD_NO_ADENA;
	
	/**
	 * ID: 2976<br>
	 * Message: ピースゾーンでないところでは受信できません。
	 */
	public static final SystemMessageId CANT_RECEIVE_NOT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2977<br>
	 * Message: トレード中には受信できません。
	 */
	public static final SystemMessageId CANT_RECEIVE_DURING_EXCHANGE;
	
	/**
	 * ID: 2978<br>
	 * Message: 個人商店、または工房を開いているため受信できません。
	 */
	public static final SystemMessageId CANT_RECEIVE_PRIVATE_STORE;
	
	/**
	 * ID: 2979<br>
	 * Message: アイテム強化、属性強化中には受信できません。
	 */
	public static final SystemMessageId CANT_RECEIVE_DURING_ENCHANT;
	
	/**
	 * ID: 2980<br>
	 * Message: アデナが不足しているため受信できません。
	 */
	public static final SystemMessageId CANT_RECEIVE_NO_ADENA;
	
	/**
	 * ID: 2981<br>
	 * Message: 受取に失敗しました。
	 */
	public static final SystemMessageId CANT_RECEIVE_INVENTORY_FULL;
	
	/**
	 * ID: 2982<br>
	 * Message: ピースゾーンでないところにはキャンセルできません。
	 */
	public static final SystemMessageId CANT_CANCEL_NOT_IN_PEACE_ZONE;
	
	/**
	 * ID: 2983<br>
	 * Message: トレード中にはキャンセルできません。
	 */
	public static final SystemMessageId CANT_CANCEL_DURING_EXCHANGE;
	
	/**
	 * ID: 2984<br>
	 * Message: 個人商店、または工房を開いているためキャンセルできません。
	 */
	public static final SystemMessageId CANT_CANCEL_PRIVATE_STORE;
	
	/**
	 * ID: 2985<br>
	 * Message: アイテム強化、属性強化中にはキャンセルできません。
	 */
	public static final SystemMessageId CANT_CANCEL_DURING_ENCHANT;
	
	/**
	 * ID: 2988<br>
	 * Message: インベントリ エラーのため、受取をキャンセルできませんでした。
	 */
	public static final SystemMessageId CANT_CANCEL_INVENTORY_FULL;
	
	/**
	 * ID: 3002<br>
	 * Message: 受信者が存在しないか、削除されたキャラクターには郵便発送ができません。
	 */
	public static final SystemMessageId RECIPIENT_NOT_EXIST;
	
	/**
	 * ID: 3008<br>
	 * Message: 郵便が届きました。
	 */
	public static final SystemMessageId MAIL_ARRIVED;
	
	/**
	 * ID: 3009<br>
	 * Message: 郵便の差出が成功しました。
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_SENT;
	
	/**
	 * ID: 3010<br>
	 * Message: 郵便の返送が成功しました。
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_RETURNED;
	
	/**
	 * ID: 3011<br>
	 * Message: 郵便のキャンセルが成功しました。
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_CANCELLED;
	
	/**
	 * ID: 3012<br>
	 * Message: 郵便の受取が成功しました。
	 */
	public static final SystemMessageId MAIL_SUCCESSFULLY_RECEIVED;
	
	/**
	 * ID: 3013<br>
	 * Message: $c1 が+$s2$s3エンチャントに成功しました。
	 */
	public static final SystemMessageId C1_SUCCESSFULY_ENCHANTED_A_S2_S3;
	
	/**
	 * ID: 3014<br>
	 * Message: 選択したメールを削除しますか。
	 */
	public static final SystemMessageId DO_YOU_WISH_TO_ERASE_MAIL;
	
	/**
	 * ID: 3015<br>
	 * Message: 削除するメールを選択してください。
	 */
	public static final SystemMessageId PLEASE_SELECT_MAIL_TO_BE_DELETED;
	
	/**
	 * ID: 3016<br>
	 * Message: アイテムの添付は最大8個までできます。
	 */
	public static final SystemMessageId ITEM_SELECTION_POSSIBLE_UP_TO_8;
	
	/**
	 * ID: 3019<br>
	 * Message: 自分自身には郵便を差出できません。
	 */
	public static final SystemMessageId YOU_CANT_SEND_MAIL_TO_YOURSELF;
	
	/**
	 * ID: 3020<br>
	 * Message: 代引郵便は受け取る金額を入力しなければ差出できません。
	 */
	public static final SystemMessageId PAYMENT_AMOUNT_NOT_ENTERED;
	
	/**
	 * ID: 3023<br>
	 * Message: カーシャの目から流れ出る気が急速に強まりつつあるのが感じられます。
	 */
	public static final SystemMessageId I_CAN_FEEL_ENERGY_KASHA_EYE_GETTING_STRONGER_RAPIDLY;
	
	/**
	 * ID: 3024<br>
	 * Message: カーシャの目が今にでも破裂せんばかりの勢いでもんどりうちます。
	 */
	public static final SystemMessageId KASHA_EYE_PITCHES_TOSSES_EXPLODE;
	
	/**
	 * ID: 3025<br>
	 * Message: $s2 が代金を支払いました。$s1アデナを獲得しました。
	 */
	public static final SystemMessageId PAYMENT_OF_S1_ADENA_COMPLETED_BY_S2;
	
	/**
	 * ID: 3026<br>
	 * Message: スキル エンチャント機能を使えるレベルではありません。この機能を使うには、レベル76以上でなければなりません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_SKILL_ENCHANT_ON_THIS_LEVEL;
	
	/**
	 * ID: 3027<br>
	 * Message: スキル エンチャント機能を使えるクラスではありません。この機能を使うには、3次転職を終えていなければなりません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_SKILL_ENCHANT_IN_THIS_CLASS;
	
	/**
	 * ID: 3028<br>
	 * Message: スキル エンチャント機能を使える状態ではありません。この機能は非戦闘状態で使えます。変身、戦闘、搭乗の各状態では使えません。
	 */
	public static final SystemMessageId YOU_CANNOT_USE_SKILL_ENCHANT_ATTACKING_TRANSFORMED_BOAT;
	
	/**
	 * ID: 3029<br>
	 * Message: $s1 が郵便を返送しました。
	 */
	public static final SystemMessageId S1_RETURNED_MAIL;
	
	/**
	 * ID: 3030<br>
	 * Message: 受取人がすでに受け取っているため、差出のキャンセルはできません。
	 */
	public static final SystemMessageId YOU_CANT_CANCEL_RECEIVED_MAIL;
	
	/**
	 * ID: 3059<br>
	 * Message: $s1 が受取待機期間中に受け取らなかったため、自動的に返送されました。
	 */
	public static final SystemMessageId S1_NOT_RECEIVE_DURING_WAITING_TIME_MAIL_RETURNED;
	
	/**
	 * ID: 3062<br>
	 * Message: $s1アデナで決済しますか。
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_PAY_S1_ADENA;
	
	/**
	 * ID: 3063<br>
	 * Message: 本当に返送しますか。
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_FORWARD;
	
	/**
	 * ID: 3064<br>
	 * Message: 未読の郵便があります。
	 */
	public static final SystemMessageId UNREAD_MAIL;
	
	/**
	 * ID: 3065<br>
	 * Message: 現在地：無限の結界内部
	 */
	public static final SystemMessageId LOC_DELUSION_CHAMBER;
	
	/**
	 * ID: 3066<br>
	 * Message: ピースゾーン以外の場所では、郵便に添付されたアイテムの受取や差出はできません。文面を見ることだけ可能です。
	 */
	public static final SystemMessageId CANT_USE_MAIL_OUTSIDE_PEACE_ZONE;
	
	/**
	 * ID: 3067<br>
	 * Message: $s1 が郵便の差出をキャンセルしました。
	 */
	public static final SystemMessageId S1_CANCELLED_MAIL;
	
	/**
	 * ID: 3068<br>
	 * Message: 受取待機期間が過ぎたため、郵便が返送されました。
	 */
	public static final SystemMessageId MAIL_RETURNED;
	
	/**
	 * ID: 3069<br>
	 * Message: 本当に取引をキャンセルしますか。
	 */
	public static final SystemMessageId DO_YOU_WANT_TO_CANCEL_TRANSACTION;
	
	/**
	 * ID: 3072<br>
	 * Message: $s1 が郵便に添付されたアイテムを獲得しました。
	 */
	public static final SystemMessageId S1_ACQUIRED_ATTACHED_ITEM;
	
	/**
	 * ID: 3073<br>
	 * Message: $s1アイテムを$s2個獲得しました。
	 */
	public static final SystemMessageId YOU_ACQUIRED_S2_S1;
	
	/**
	 * ID: 3074<br>
	 * Message: 規定の受取人名の長さを超えています。
	 */
	public static final SystemMessageId ALLOWED_LENGTH_FOR_RECIPIENT_EXCEEDED;
	
	/**
	 * ID: 3075<br>
	 * Message: 規定のタイトルの長さを超えています。
	 */
	public static final SystemMessageId ALLOWED_LENGTH_FOR_TITLE_EXCEEDED;
	
	/**
	 * ID: 3077<br>
	 * Message: 相手のキャラクターが郵便の限度数(240通)を超えたため、差出できません。
	 */
	public static final SystemMessageId MAIL_LIMIT_EXCEEDED;
	
	/**
	 * ID: 3078<br>
	 * Message: 代引郵便です。本当に差し出しますか。
	 */
	public static final SystemMessageId YOU_MAKING_PAYMENT_REQUEST;
	
	/**
	 * ID: 3079<br>
	 * Message: ペット インベントリにアイテムがあるため、ペットの販売、個人商店の販売登録、アイテムのドロップはできません。ペット インベントリを空にしてください。
	 */
	public static final SystemMessageId ITEMS_IN_PET_INVENTORY;
	
	/**
	 * ID: 3080<br>
	 * Message: アデナが不足しているためスキル リンクをリセットできません。
	 */
	public static final SystemMessageId CANNOT_RESET_SKILL_LINK_BECAUSE_NOT_ENOUGH_ADENA;
	
	/**
	 * ID: 3081<br>
	 * Message: 相手が決済アデナを獲得できない条件になっているため、受け取れません。
	 */
	public static final SystemMessageId YOU_CANNOT_RECEIVE_CONDITION_OPPONENT_CANT_ACQUIRE_ADENA;
	
	/**
	 * ID: 3082<br>
	 * Message: 遮断しているキャラクターには郵便を差し出せません。
	 */
	public static final SystemMessageId YOU_CANNOT_SEND_MAIL_TO_CHAR_BLOCK_YOU;
	
	/**
	 * ID: 3094<br>
	 * Message: オリンピアード参加中のユーザーはパーティ、友人の招待はできません。
	 */
	public static final SystemMessageId A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS;
	
	/**
	 * ID: 3108<br>
	 * Message: 今後、アクティブ モンスターを刺激するおそれがあります。
	 */
	public static final SystemMessageId YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS;
	
	/**
	 * ID: 3119<br>
	 * Message: カップル アクションの申し込みが拒否されました。
	 */
	public static final SystemMessageId COUPLE_ACTION_DENIED;
	
	/**
	 * ID: 3120<br>
	 * Message: 対象との位置条件が合わないため申し込みできません。
	 */
	public static final SystemMessageId TARGET_DO_NOT_MEET_LOC_REQUIREMENTS;
	
	/**
	 * ID: 3121<br>
	 * Message: カップル アクションがキャンセルされました。
	 */
	public static final SystemMessageId COUPLE_ACTION_CANCELED;
	
	/**
	 * ID: 3122<br>
	 * Message: アップロードしたエンブレムまたはエンサインは規格に合いません。
	 */
	public static final SystemMessageId WRONG_SIZE_UPLOADED_CREST;
	
	/**
	 * ID: 3123<br>
	 * Message: $c1 は個人商店や個人工房を開いているため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_IN_PRIVATE_SHOP_MODE_OR_IN_A_BATTLE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3124<br>
	 * Message: $c1 はフィッシング中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_FISHING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3125<br>
	 * Message: $c1 は戦闘中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_IN_A_BATTLE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3126<br>
	 * Message: $c1 はすでにカップル アクション中のため、新たにカップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_ALREADY_PARTICIPATING_IN_A_COUPLE_ACTION_AND_CANNOT_BE_REQUESTED_FOR_ANOTHER_COUPLE_ACTION;
	
	/**
	 * ID: 3127<br>
	 * Message: $c1 はカオティック性向のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_IN_A_CHAOTIC_STATE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3128<br>
	 * Message: $c1 はオリンピアード参加中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_PARTICIPATING_IN_THE_OLYMPIAD_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3129<br>
	 * Message: $c1 はアジト戦遂行中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_PARTICIPATING_IN_A_HIDEOUT_SIEGE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3130<br>
	 * Message: $c1 は攻城戦に参加中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_IN_A_CASTLE_SIEGE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3131<br>
	 * Message: $c1 は船、ワイバーン、ストライダーなどに搭乗中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_RIDING_A_SHIP_STEED_OR_STRIDER_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3132<br>
	 * Message: $c1 はテレポート中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_CURRENTLY_TELEPORTING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3133<br>
	 * Message: $c1 は変身中のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_CURRENTLY_TRANSFORMING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3135<br>
	 * Message: $s1への変更について、同意を求めています。
	 */
	public static final SystemMessageId REQUESTING_APPROVAL_CHANGE_PARTY_LOOT_S1;
	
	/**
	 * ID: 3137<br>
	 * Message: アイテム分配タイプの変更がキャンセルされました。
	 */
	public static final SystemMessageId PARTY_LOOT_CHANGE_CANCELLED;
	
	/**
	 * ID: 3138<br>
	 * Message: アイテム分配タイプが$s1に変更されました。
	 */
	public static final SystemMessageId PARTY_LOOT_CHANGED_S1;
	
	/**
	 * ID: 3139<br>
	 * Message: $c1 は現在死亡状態のため、カップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_CURRENTLY_DEAD_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION;
	
	/**
	 * ID: 3144<br>
	 * Message: $s1に$s2の属性を付与しました。$s3に対する耐性が向上しました。
	 */
	public static final SystemMessageId THE_S2_ATTRIBUTE_WAS_SUCCESSFULLY_BESTOWED_ON_S1_RES_TO_S3_INCREASED;
	
	/**
	 * ID: 3150<br>
	 * Message: $c1 にカップル アクションを申し込みました。
	 */
	public static final SystemMessageId YOU_HAVE_REQUESTED_COUPLE_ACTION_C1;
	
	/**
	 * ID: 3152<br>
	 * Message: $s1の$s2の属性を解除しました。$s3に対する耐性が低下しました。
	 */
	public static final SystemMessageId S1_S2_ATTRIBUTE_REMOVED_RESISTANCE_S3_DECREASED;
	
	/**
	 * ID: 3156<br>
	 * Message: 手数料が不足しているため、属性を解除できません。
	 */
	public static final SystemMessageId YOU_DO_NOT_HAVE_ENOUGH_FUNDS_TO_CANCEL_ATTRIBUTE;
	
	/**
	 * ID: 3160<br>
	 * Message: +$s1$s2の$s3の属性を解除しました。$s4に対する耐性が低下しました。
	 */
	public static final SystemMessageId S1_S2_S3_ATTRIBUTE_REMOVED_RESISTANCE_TO_S4_DECREASED;
	
	/**
	 * ID: 3163<br>
	 * Message: +$s1$s2に$s3の属性を付与しました。$s4に対する耐性が向上しました。
	 */
	public static final SystemMessageId THE_S3_ATTRIBUTE_BESTOWED_ON_S1_S2_RESISTANCE_TO_S4_INCREASED;
	
	/**
	 * ID: 3164<br>
	 * Message: $c1 はすでにカップル拒否状態のため、新たにカップル アクションの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_SET_TO_REFUSE_COUPLE_ACTIONS;
	
	/**
	 * ID: 3168<br>
	 * Message: $c1 はすでにカップル拒否状態のため、新たにパーティの申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_SET_TO_REFUSE_PARTY_REQUEST;
	
	/**
	 * ID: 3169<br>
	 * Message: $c1 はすでに決闘申込拒否状態のため、新たに決闘の申し込みはできません。
	 */
	public static final SystemMessageId C1_IS_SET_TO_REFUSE_DUEL_REQUEST;
	
	/**
	 * ID: 3206<br>
	 * Message: 現在、持っている推薦権はありません。
	 */
	public static final SystemMessageId YOU_CURRENTLY_DO_NOT_HAVE_ANY_RECOMMENDATIONS;
	
	/**
	 * ID: 3207<br>
	 * Message: 推薦権を$s1手に入れました。
	 */
	public static final SystemMessageId YOU_OBTAINED_S1_RECOMMENDATIONS;
	
	/**
	 * ID: 3214<br>
	 * Message: $s1 が受取人リストに登録されました。
	 */
	public static final SystemMessageId S1_SUCCESSFULLY_ADDED_TO_CONTACT_LIST;
	
	/**
	 * ID: 3215<br>
	 * Message: $s1は存在しないユーザーです。正しい名前を入力してください。
	 */
	public static final SystemMessageId NAME_S1_NOT_EXIST_TRY_ANOTHER_NAME;
	
	/**
	 * ID: 3216<br>
	 * Message: すでに受取人リストに登録されている名前です。
	 */
	public static final SystemMessageId NAME_ALREADY_EXIST_ON_CONTACT_LIST;
	
	/**
	 * ID: 3217<br>
	 * Message: 現在、名前をリストに登録できません。
	 */
	public static final SystemMessageId NAME_NOT_REGISTERED_ON_CONTACT_LIST;
	
	/**
	 * ID: 3219<br>
	 * Message: $s1 が受取人リストから削除されました。
	 */
	public static final SystemMessageId S1_SUCCESFULLY_DELETED_FROM_CONTACT_LIST;
	
	/**
	 * ID: 3221<br>
	 * Message: 自分の名前を登録することはできません。
	 */
	public static final SystemMessageId CANNOT_ADD_YOUR_NAME_ON_CONTACT_LIST;
	
	/**
	 * ID: 3222<br>
	 * Message: 名前の登録上限(100人)に達したため、登録できません。
	 */
	public static final SystemMessageId CONTACT_LIST_LIMIT_REACHED;
	
	/**
	 * ID: 3224<br>
	 * Message: 1週間に参加できる最大競技数は70です。
	 */
	public static final SystemMessageId MAX_OLY_WEEKLY_MATCHES_REACHED;
	
	/**
	 * ID: 3225<br>
	 * Message: 1週間に参加できる最大競技数は無制限競技60、クラス別競技30、チーム競技10です。
	 */
	public static final SystemMessageId MAX_OLY_WEEKLY_MATCHES_REACHED_60_NON_CLASSED_30_CLASSED_10_TEAM;
	
	/**
	 * ID: 3226<br>
	 * Message: チャット開始後すぐには移動できません。しばらくお待ちください。
	 */
	public static final SystemMessageId CANNOT_MOVE_WHILE_SPEAKING_TO_AN_NPC;
	
	/**
	 * ID: 3255<br>
	 * Message: アケイン シールドの影響で、HPの代わりにMPが$s1のダメージを受けました。
	 */
	public static final SystemMessageId ARCANE_SHIELD_DECREASED_YOUR_MP_BY_S1_INSTEAD_OF_HP;
	
	/**
	 * ID: 3259<br>
	 * Message: $s1の経験値(ボーナス：$s2)と$s3のSP(ボーナス：$s4)を得ました。
	 */
	public static final SystemMessageId YOU_EARNED_S1_EXP_BONUS_S2_AND_S3_SP_BONUS_S4;
	
	/**
	 * ID: 3256<br>
	 * Message: MPが0になって、アケイン シールドが消滅します。
	 */
	public static final SystemMessageId MP_BECAME_0_ARCANE_SHIELD_DISAPPEARING;
	
	/**
	 * ID: 3261<br>
	 * Message: 今週、参加できる競技の数は$s1です。(クラス別競技：$s2、無制限競技：$s3、チーム競技：$s4)
	 */
	public static final SystemMessageId YOU_HAVE_S1_MATCHES_REMAINING_THAT_YOU_CAN_PARTECIPATE_IN_THIS_WEEK_S2_CLASSED_S3_NON_CLASSED_S4_TEAM;
	
	/**
	 * ID: 6004<br>
	 * Message: エンチャントに失敗しました。当該アイテムのエンチャント値はそのままです。
	 */
	public static final SystemMessageId SAFE_ENCHANT_FAILED;
	
	/**
	 * ID: 6501<br>
	 * Message: フリーテレポートの旗がないため、ブックマークを保存できません。
	 */
	public static final SystemMessageId YOU_CANNOT_BOOKMARK_THIS_LOCATION_BECAUSE_YOU_DO_NOT_HAVE_A_MY_TELEPORT_FLAG;
	
	/**
	 * ID: 6503<br>
	 * Message: キャプテン ターキーが現れました。クリスマス サンタを救い出しましょう。
	 */
	public static final SystemMessageId THOMAS_D_TURKEY_APPEARED;
	
	/**
	 * ID: 6504<br>
	 * Message: キャプテン ターキーとの戦いに勝って、クリスマス サンタを救い出しました。
	 */
	public static final SystemMessageId THOMAS_D_TURKEY_DEFETED;
	
	/**
	 * ID: 6505<br>
	 * Message: クリスマス サンタを救い出せないまま、キャプテン ターキーが消えてしまいました。
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
		CANNOT_CHANGE_WEAPON_DURING_AN_ATTACK = new SystemMessageId(104);
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
		FAILED_TO_DELETE_CHAR = new SystemMessageId(306);
		CANNOT_TRADE_WAREHOUSE_KEEPER = new SystemMessageId(307);
		PLAYER_DECLINED_CLAN_INVITATION = new SystemMessageId(308);
		YOU_HAVE_SUCCEEDED_IN_EXPELLING_CLAN_MEMBER = new SystemMessageId(309);
		FAILED_TO_EXPEL_CLAN_MEMBER = new SystemMessageId(310);
		CLAN_WAR_DECLARATION_ACCEPTED = new SystemMessageId(311);
		CLAN_WAR_DECLARATION_REFUSED = new SystemMessageId(312);
		CEASE_WAR_REQUEST_ACCEPTED = new SystemMessageId(313);
		FAILED_TO_SURRENDER = new SystemMessageId(314);
		FAILED_TO_PERSONALLY_SURRENDER = new SystemMessageId(315);
		FAILED_TO_WITHDRAW_FROM_THE_PARTY = new SystemMessageId(316);
		FAILED_TO_EXPEL_THE_PARTY_MEMBER = new SystemMessageId(317);
		FAILED_TO_DISPERSE_THE_PARTY = new SystemMessageId(318);
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
		INCORRECT_ITEM = new SystemMessageId(352);
		CANNOT_PURCHASE = new SystemMessageId(353);
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
		SYSTEM_ERROR = new SystemMessageId(399);
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
		FAILED_TO_REFUSE_CASTLE_DEFENSE_AID = new SystemMessageId(640);
		FAILED_TO_APPROVE_CASTLE_DEFENSE_AID = new SystemMessageId(641);
		ALREADY_ATTACKER_NOT_CANCEL = new SystemMessageId(642);
		ALREADY_DEFENDER_NOT_CANCEL = new SystemMessageId(643);
		NOT_REGISTERED_FOR_SIEGE = new SystemMessageId(644);
		ONLY_CLAN_LEVEL_5_ABOVE_MAY_SIEGE = new SystemMessageId(645);
		DO_NOT_HAVE_AUTHORITY_TO_MODIFY_CASTLE_DEFENDER_LIST = new SystemMessageId(646);
		DO_NOT_HAVE_AUTHORITY_TO_MODIFY_SIEGE_TIME = new SystemMessageId(647);
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
		SIEGE_TIME_DECLARED_FOR_S1 = new SystemMessageId(663);
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
		SUBMITTED_A_BID_OF_S1 = new SystemMessageId(678);
		CANCELED_BID = new SystemMessageId(679);
		CANNOT_PARTICIPATE_IN_AN_AUCTION = new SystemMessageId(680);
		CLAN_HAS_NO_CLAN_HALL = new SystemMessageId(681);
		MOVING_TO_ANOTHER_VILLAGE = new SystemMessageId(681);
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
		LOC_NEUTRAL_ZONE_S1_S2_S3 = new SystemMessageId(913);
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
		SSQ_COMPETITION_UNDERWAY = new SystemMessageId(1176);
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
		DEPARTURE_FOR_RUNE_10_MINUTES = new SystemMessageId(1625);
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
		C1_CANNOT_DUEL_BECAUSE_C1_IS_CURRENTLY_RIDING_A_BOAT_STEED_OR_STRIDER = new SystemMessageId(2027);
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
		YOU_MUST_LEARN_ONYX_BEAST_SKILL = new SystemMessageId(2211);
		NOT_COMPLETED_QUEST_FOR_SKILL_ACQUISITION = new SystemMessageId(2212);
		CANT_BOARD_SHIP_POLYMORPHED = new SystemMessageId(2213);
		CONFIRM_CHARACTER_CREATION = new SystemMessageId(2214);
		S1_PDEF = new SystemMessageId(2215);
		PLEASE_UPDATE_CPU_DRIVER = new SystemMessageId(2216);
		BALLISTA_DESTROYED_CLAN_REPU_INCREASED = new SystemMessageId(2217);
		MAIN_CLASS_SKILL_ONLY = new SystemMessageId(2218);
		SQUAD_SKILL_ALREADY_ACQUIRED = new SystemMessageId(2219);
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
		CAN_OPERATE_MACHINE_WHEN_IN_PARTY = new SystemMessageId(2291);
		LOC_IN_STEEL_CITADEL_S1_S2_S3 = new SystemMessageId(2293);
		GAINED_VITALITY_POINTS = new SystemMessageId(2296);
		LOC_STEEL_CITADEL = new SystemMessageId(2301);
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
		HALF_KILL = new SystemMessageId(2336);
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
		THE_S1_WARD_HAS_BEEN_DESTROYED_C2_HAS_THE_WARD = new SystemMessageId(2750);
		THE_CHAR_THAT_ACQUIRED_S1_WARD_HAS_BEEN_KILLED = new SystemMessageId(2751);
		CANT_CONTROL_TOO_FAR = new SystemMessageId(2762);
		YOU_CANNOT_ENTER_BECAUSE_MAXIMUM_ENTRANTS = new SystemMessageId(2764);
		ONLY_ALLIANCE_CHANNEL_LEADER_CAN_ENTER = new SystemMessageId(2765);
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
		YOU_MUST_HAVE_MINIMUM_OF_S1_PEOPLE_TO_ENTER = new SystemMessageId(2793);
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
		A_USER_CURRENTLY_PARTICIPATING_IN_THE_OLYMPIAD_CANNOT_SEND_PARTY_AND_FRIEND_INVITATIONS = new SystemMessageId(3094);
		YOU_ARE_NO_LONGER_PROTECTED_FROM_AGGRESSIVE_MONSTERS = new SystemMessageId(3108);
		COUPLE_ACTION_DENIED = new SystemMessageId(3119);
		TARGET_DO_NOT_MEET_LOC_REQUIREMENTS = new SystemMessageId(3120);
		COUPLE_ACTION_CANCELED = new SystemMessageId(3121);
		WRONG_SIZE_UPLOADED_CREST = new SystemMessageId(3122);
		C1_IS_IN_PRIVATE_SHOP_MODE_OR_IN_A_BATTLE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3123);
		C1_IS_FISHING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3124);
		C1_IS_IN_A_BATTLE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3125);
		C1_IS_ALREADY_PARTICIPATING_IN_A_COUPLE_ACTION_AND_CANNOT_BE_REQUESTED_FOR_ANOTHER_COUPLE_ACTION = new SystemMessageId(3126);
		C1_IS_IN_A_CHAOTIC_STATE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3127);
		C1_IS_PARTICIPATING_IN_THE_OLYMPIAD_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3128);
		C1_IS_PARTICIPATING_IN_A_HIDEOUT_SIEGE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3129);
		C1_IS_IN_A_CASTLE_SIEGE_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3130);
		C1_IS_RIDING_A_SHIP_STEED_OR_STRIDER_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3131);
		C1_IS_CURRENTLY_TELEPORTING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3132);
		C1_IS_CURRENTLY_TRANSFORMING_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3133);
		REQUESTING_APPROVAL_CHANGE_PARTY_LOOT_S1 = new SystemMessageId(3135);
		PARTY_LOOT_CHANGE_CANCELLED = new SystemMessageId(3137);
		PARTY_LOOT_CHANGED_S1 = new SystemMessageId(3138);
		C1_IS_CURRENTLY_DEAD_AND_CANNOT_BE_REQUESTED_FOR_A_COUPLE_ACTION = new SystemMessageId(3139);
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
		S1_SUCCESSFULLY_ADDED_TO_CONTACT_LIST = new SystemMessageId(3214);
		NAME_S1_NOT_EXIST_TRY_ANOTHER_NAME = new SystemMessageId(3215);
		NAME_ALREADY_EXIST_ON_CONTACT_LIST = new SystemMessageId(3216);
		NAME_NOT_REGISTERED_ON_CONTACT_LIST = new SystemMessageId(3217);
		S1_SUCCESFULLY_DELETED_FROM_CONTACT_LIST = new SystemMessageId(3219);
		CANNOT_ADD_YOUR_NAME_ON_CONTACT_LIST = new SystemMessageId(3221);
		CONTACT_LIST_LIMIT_REACHED = new SystemMessageId(3222);
		MAX_OLY_WEEKLY_MATCHES_REACHED = new SystemMessageId(3224);
		MAX_OLY_WEEKLY_MATCHES_REACHED_60_NON_CLASSED_30_CLASSED_10_TEAM = new SystemMessageId(3225);
		CANNOT_MOVE_WHILE_SPEAKING_TO_AN_NPC = new SystemMessageId(3226);
		ARCANE_SHIELD_DECREASED_YOUR_MP_BY_S1_INSTEAD_OF_HP = new SystemMessageId(3255);
		YOU_EARNED_S1_EXP_BONUS_S2_AND_S3_SP_BONUS_S4 = new SystemMessageId(3259);
		MP_BECAME_0_ARCANE_SHIELD_DISAPPEARING = new SystemMessageId(3256);
		YOU_HAVE_S1_MATCHES_REMAINING_THAT_YOU_CAN_PARTECIPATE_IN_THIS_WEEK_S2_CLASSED_S3_NON_CLASSED_S4_TEAM = new SystemMessageId(3261);
		SAFE_ENCHANT_FAILED = new SystemMessageId(6004);
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
		for (int i = 0; i < (name.length() - 1); i++)
		{
			c1 = name.charAt(i);
			if ((c1 == 'C') || (c1 == 'S'))
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
		if ((id < 0) || (id >= VALUES.length))
		{
			return null;
		}
		
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
			{
				smId.removeAllLocalisations();
			}
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
			{
				continue;
			}
			
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
								if (text.isEmpty() || (text.length() > 255))
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
	
	protected static final Builder newBuilder(final String text)
	{
		final ArrayList<Builder> builders = new ArrayList<Builder>();
		
		int index1 = 0, index2 = 0, paramId, subTextLen;
		
		final char[] array = text.toCharArray();
		final int arrayLength = array.length;
		
		char c, c2, c3;
		LOOP:
		for (; index1 < arrayLength; index1++)
		{
			c = array[index1];
			if ((c == '$') && (index1 < (arrayLength - 2)))
			{
				c2 = array[index1 + 1];
				if ((c2 == 'c') || (c2 == 's') || (c2 == 'p') || (c2 == 'C') || (c2 == 'S') || (c2 == 'P'))
				{
					c3 = array[index1 + 2];
					if (Character.isDigit(c3))
					{
						paramId = Character.getNumericValue(c3);
						subTextLen = index1 - index2;
						if (subTextLen != 0)
						{
							builders.add(new BuilderText(new String(array, index2, subTextLen)));
						}
						
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
			{
				builders.add(new BuilderText(new String(array, index2, subTextLen)));
			}
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
	 * @param params
	 */
	public final void setParamCount(final int params)
	{
		if (params < 0)
		{
			throw new IllegalArgumentException("Invalid negative param count: " + params);
		}
		
		if (params > 10)
		{
			throw new IllegalArgumentException("Maximum param count exceeded: " + params);
		}
		
		if (params != 0)
		{
			_staticSystemMessage = null;
		}
		
		_params = (byte) params;
	}
	
	public final SMLocalisation getLocalisation(final String lang)
	{
		SMLocalisation sml;
		for (int i = _localisations.length; i-- > 0;)
		{
			sml = _localisations[i];
			if (sml.getLanguage().hashCode() == lang.hashCode())
			{
				return sml;
			}
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
	 * @author Forsaiken
	 */
	private static interface Builder
	{
		public String toString(final Object param);
		
		public String toString(final Object... params);
		
		public int getIndex();
	}
	
	/**
	 * @author Forsaiken
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
			return toString(new Object[]
			{
				param
			});
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
					build = (paramIndex != -1) && (paramIndex < paramsLength) ? builder.toString(params[paramIndex]) : builder.toString();
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
	 * @author Forsaiken
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
	 * @author Forsaiken
	 */
	private static final class BuilderObject implements Builder
	{
		private final int _index;
		
		public BuilderObject(final int id)
		{
			if ((id < 1) || (id > 9))
			{
				throw new RuntimeException("Illegal id " + id);
			}
			
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
			if ((params == null) || (params.length == 0))
			{
				return "null";
			}
			
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
	 * @author Forsaiken
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