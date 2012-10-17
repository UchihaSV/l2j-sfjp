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
	private static final int[] S18319 =	//釣れたカエル
	{
		1010400, //ケロケロッ！こんなところに$s1があるなんて！？
		1010401, //$s1、やったね！
		1010402, //釣り間違えたって言えよ、$s1！
		
		1010403, //カエルを甘く見ちゃいかんよ。
		1010404, //カエルの力を見せてやろう！
		1010405, //一口で飲み込んでやる！
		
		1010406, //ゴフッ．．．このオレ様が死ぬなんて！
		1010407, //ゲコゲコ！カエル殺し！
		1010408, //カエルは美味しくないですよ！あわわ。
	};
	private static final int[] S18320 =	//釣れたウンディーネ
	{
		1010409, //きゃあっ！$s1、何するのっ？
		1010410, //ふふっ、$s1精霊の体に傷をつけるなんて。覚悟はできているんでしょうね？
		1010411, //あら、$s1 あなただったのね。でもね、こんな荒っぽい招待に喜ぶ女なんていないわよ？
		
		1010412, //あたしを怒らせたわね！
		1010413, //この傷、どうしてくれるのよっ！？
		1010414, //あんたも同じ目にあわせてやる！えいっ！
		
		1010415, //きゃあっ！そうきたわね、覚えてらっしゃい！
		1010416, //あなたのエサは食べるなって、他の魚たちに言いふらしてやる！
		1010417, //あたしみたいなか弱い精霊をいじめるなんて．．．うっうっ。
	};
	private static final int[] S18321 =	//釣れたラクル
	{
		1010418, //ケケケ．．．$s1．．．食べるぞ．．．ケケッ．．．
		1010419, //グルル．．．う．．．$s1．．．うらめしや．．．魚ども．．．
		1010420, //$s1？ケホッ．．．グル．．．グルッ．．．
		
		1010421, //クケケケケ！ラクル！スピン！アタアアアアアック！
		1010422, //おおっ！パプリオン！うおっ！うおっ！
		1010423, //ラクル！ラクル！ラァァクゥゥルゥゥ！
		
		1010424, //クエェ．．．恨み．．．魚．．．
		1010425, //食われたくない．．．グアァ
		1010426, //グハッ！ラクル！ケホッケホッ！グエェ．．．
	};
	private static final int[] S18322 =	//釣れたシージャイアント
	{
		1010427, //パプリオンに栄光あれ！$s1には死を！
		1010428, //$s1、お前か！うちの可愛い魚たちをいじめてるのは！
		1010429, //水竜様！$s1に呪いを！
		
		1010430, //ジャイアント スペシャル アタック！
		1010431, //魚たちの恨みを思い知れ！
		1010432, //俺の槍を食らってみろ！
		
		1010433, //パプリオン様に栄光あれ！
		1010434, //ぐはああ！
		1010435, //老兵は死なず．．．ただ消え去るのみ．．．
	};
	private static final int[] S18323 =	//釣れたシーホース ソルジャー
	{
		1010436, //$s1、この水の騎士の挑戦を受けろ！
		1010437, //魚たちの報告にあった$s1発見！
		1010438, //$s1、私がくわえたのはあんたのエサだったのか！
		
		1010439, //龍宮城仕込みの槍術をご覧あれ！
		1010440, //あんたに贈る最期のプレゼントだぜ！
		1010441, //あんたのエサは旨かったよ！じゃあな！
		
		1010442, //悔しい！我が同胞の恨みを！
		1010443, //覚えてろ！この仇はきっと誰かが．．．
		1010444, //グフッ．．．しかしお前には捕まらないぞ！
	};
	private static final int[] S18324 =	//釣れたホムンクルス
	{
		1010445, //$s1、すげぇぞ．．．
		1010446, //$s1、深海にいる俺を釣り上げるなんて．．．
		1010447, //$s1、俺が魚に見えるか。
		
		1010448, //はらほろひれ～
		1010449, //クククッ、どれ、こんがり焼いてやろうか。
		1010450, //水から出たからって油断してたんだろ？
		
		1010451, //うひゃ．．．痛い．．．いててててっ！
		1010452, //チェッ、失敗か．．．せっかく見つけたのに。
		1010453, //生命活動．．．停止．．．ジジッ．．．
	};
	private static final int[] S18325 =	//釣れたフラヴァ
	{
		1010454, //クルルッ！$s1～クルッポー。
		1010455, //$s1の匂いがするぞぅ！
		1010456, //美味しそうだな、$s1！
		
		1010457, //食ってやるぞおおお！
		1010458, //うへへへ、久しぶりにありつくエサだな！
		1010459, //お前なんか一口さ！
		
		1010460, //ぐお．．．エサにやられるなんて！
		1010461, //お前は俺のエサなんだよ！捕って食ってやる！
		1010462, //エサに食われるなんて．．．クッ．．．
	};
	private static final int[] S18326 =	//釣れた大怪球
	{
		1010463, //俺を釣ったのは$s1？
		1010464, //俺に会えたことを光栄に思えよ、$s1。
		1010465, //$s1、生きて帰れるなんてこれっぽっちも思わないほうがいいぞ。
		
		1010466, //深淵の力をとくと見ろ．．．
		1010467, //その釣り竿をへし折ってやる．．．
		1010468, //貴様の屍は、この俺が美味しく頂こう．．．
		
		1010469, //ただの釣り師じゃなかったのか．．．
		1010470, //パプリオンが怖くないのか．．．
		1010471, //素晴らしい、褒めてやるぞ．．．
	};
	//-------------------------------------------------------
	
	public L2PenaltyMonsterInstance(int objectId, L2NpcTemplate template)
	{
		super(objectId, template);
		setInstanceType(InstanceType.L2PenaltyMonsterInstance);
		//[JOJO]-------------------------------------------------
		switch (getNpcId())
		{
			case 18319: _randomNpcStrings = S18319; break;	//釣れたカエル
			case 18320: _randomNpcStrings = S18320; break;	//釣れたウンディーネ
			case 18321: _randomNpcStrings = S18321; break;	//釣れたラクル
			case 18322: _randomNpcStrings = S18322; break;	//釣れたシージャイアント
			case 18323: _randomNpcStrings = S18323; break;	//釣れたシーホース ソルジャー
			case 18324: _randomNpcStrings = S18324; break;	//釣れたホムンクルス
			case 18325: _randomNpcStrings = S18325; break;	//釣れたフラヴァ
			default:
			case 18326: _randomNpcStrings = S18326; break;	//釣れた大怪球
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
