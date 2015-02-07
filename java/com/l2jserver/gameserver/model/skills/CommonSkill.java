/*
 * Copyright (C) 2004-2014 L2J Server
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
package com.l2jserver.gameserver.model.skills;

import com.l2jserver.gameserver.datatables.SkillData;

/**
 * An Enum to hold some important references to commonly used skills
 * @author DrHouse
 */
public enum CommonSkill
{
	RAID_CURSE(4215, 1),	// レイドの呪い - レイド ガーディアンの呪いにかかり、一定時間、物理、魔法スキルのすべてが封じられた状態。自分よりレベルが9以上低いレイド モンスターを倒そうとする時にかかり、呪いを解くことはできません。
	RAID_CURSE2(4515, 1),	// レイドの呪い - レイド ガーディアンの呪い。石化にかかって、一時的に体が石のように固まっている状態。自分よりレベルが9以上低いレイド モンスターを倒そうとする時にかかり、解くことはできません。
	SEAL_OF_RULER(246, 1),	// シール オブ ルーラー - 城を守護する古代の聖物に対する支配者として刻印させます。
	BUILD_HEADQUARTERS(247, 1),	// 陣地構築 - 攻城戦の際、血盟員の回復と戦線投入を助ける陣地を構築します。ジェムストーン：Cグレード300個が消耗します。
	WYVERN_BREATH(4289, 1),	// ワイバーン ブレス - 炎を噴き、多くの敵を攻撃します。ターゲットから遠くなる程ダメージが減少します。
	STRIDER_SIEGE_ASSAULT(325, 1),	// ストライダー シージ アサルト - 15005の威力で城門を攻撃します。ストライダー搭乗時に使用できます。ルーンストーンを5個消耗します。
	FIREWORK(5965, 1),	// 花火
	LARGE_FIREWORK(2025, 1),	// 大花火
	BLESSING_OF_PROTECTION(5182, 1),	// 保護の祝福 - 10レベル以上差のあるカオティック性向のキャラクターの攻撃から安全に身を守ります。
	VOID_BURST(3630, 1),	// ボイド バースト - 呪われた気を放ち周りの敵を攻撃します。
	VOID_FLOW(3631, 1),	// ボイド フロー - 呪われた気を凝縮し遠距離の敵に打撃を与えます。
	THE_VICTOR_OF_WAR(5074, 1),	// 戦乱の勝者 - 最大CPが増加した状態。
	THE_VANQUISHED_OF_WAR(5075, 1),	// 戦乱の敗者 - 最大CPが減少した状態。
	SPECIAL_TREE_RECOVERY_BONUS(2139, 1),	// 高級ツリーの回復ボーナス - クリスマス イベントの高級ツリー効果。HP回復ボーナス、MP回復ボーナスが向上した状態。
	WEAPON_GRADE_PENALTY(6209, 1),	// Lv.1～4 武器グレード ペナルティ - 自分のレベルに合わないグレードの武器を装着したため、命中率が16、クリティカル確率が■%、クリティカル威力が■%、攻撃速度が10%、攻撃力が10%低下するペナルティを受けています。
	ARMOR_GRADE_PENALTY(6213, 1),	// Lv.1～4 防具グレード ペナルティ - 自分のレベルに合わないグレードの防具、アクセサリーを装着したため、回避率が■、攻撃速度、魔法詠唱速度、移動速度が■%低下するペナルティを受けています。
	CREATE_DWARVEN(172, 1),	// Lv.1～10 クリエイト アイテム - ■ステップのアイテムが製造できます。
	LUCKY(194, 1),	// ラッキー - LV9以下での死亡時に、経験値の減少と死がもたらす傷の影響を受けるのを防ぎ、バイタリティ ポイントが消費されません。
	EXPERTISE(239, 1),	// Lv.1～7 エキスパーティーズ ■ - ■グレード以下の装備の扱いに慣れます。
	CRYSTALLIZE(248, 1),	// Lv.1～5 クリスタライズ - ■グレードのクリスタライズを可能にします。
	CLAN_LUCK(390, 1),	// Lv.1～3 クラン デス フォーチュン - 血盟員がPK、一般モンスターに倒された場合の経験値の減少率と死がもたらす傷の影響を受ける確率が低くなります。従士以上から効力が適用されます。
	ONYX_BEAST_TRANSFORMATION(617, 1),	// トランスフォーム オニキス ビースト - オニキス ビーストに変身します。
	CREATE_COMMON(1320, 1),	// Lv.1～9 クリエイト コモン アイテム - ■ステップの一般アイテムが製造できます。
	DIVINE_INSPIRATION(1405, 1),	// Lv.1～4 インクリース オブ ディビニティ - 個人の受けられる強化魔法の数が■増加します。
	SERVITOR_SHARE(1557, 1),	// シェアリング アビリティ - 召喚師の能力が召喚獣に移ります。移るのは召喚師の攻撃力、防御力の50%、魔力、魔法抵抗の25%、最大HPとMPの10%、クリティカル確率の20%、攻撃速度の10%、魔法詠唱速度の3%です。
	CARAVANS_SECRET_MEDICINE(2341, 1),	// キャラバンの秘薬 - 砂漠の熱気に免疫がついている状態。
	;
	
	private final int _id, _skillHashCode;
	
	private CommonSkill(int id, int level)
	{
		_id = id;
		_skillHashCode = SkillData.getSkillHashCode(id, level);
	}
	
	public int getId()
	{
		return _id;
	}
	
	public int getLevel()
	{
		return SkillData.getSkillLevel(_skillHashCode);
	}
	
	public Skill getSkill()
	{
		return SkillData.getSkill(_skillHashCode);
	}
}