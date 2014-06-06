/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */

package com.l2jserver.gameserver.instancemanager;

import java.util.concurrent.ScheduledFuture;
import java.util.logging.Logger;

import com.l2jserver.gameserver.Announcements;
import com.l2jserver.gameserver.ThreadPoolManager;
import com.l2jserver.gameserver.ai.L2ControllableMobAI;
import com.l2jserver.gameserver.datatables.NpcData;
import com.l2jserver.gameserver.model.MobGroup;
import com.l2jserver.gameserver.model.MobGroupTable;
import com.l2jserver.gameserver.model.actor.templates.L2NpcTemplate;
import com.l2jserver.util.Rnd;

/**
 * 
 * This class ...
 * control for Battle of GrandBoss vs GrandBoss.
 * @version $Revision: $ $Date: $
 * @author  L2J_JP SANDMAN
 *
 * @author  JOJO
 * �d�l�ύX�F
 * �@�o�g�����Ɍ���ăX�^�[�g���Ă��d�����Ȃ��悤�΍�B
 * �@���������{�X�́A���̂܂܋��c��B�������{�X�������ł���B
 * �@�ăX�^�[�g����΁A�V���ȃ{�X���N���o�g�����n�܂�B
 * �@�X�g�b�v����Η��҂Ƃ����ł���B
 * �@�������Ԃ́A�ŏ��̃X�^�[�g����Q���ԁB
 */

public class BvBManager
{
    protected static Logger _log = Logger.getLogger(BvBManager.class.getName());
    private static BvBManager _instance = new BvBManager();

	// �R���V�A�����̍��W       x       y       z    ����
    static final int _spawnLocation1[] = { 148733, 46721, -3438,     0 };	// group1
    static final int _spawnLocation2[] = { 150058, 46721, -3412, 32768 };	// group2

//	// Antharas(old),Baium,Valakas,Scarlet van Halisha(small),Scarlet van Halisha(lage),Antharas(weak),Antharas(normal),Antharas(strong)
//	static final int _bossIds[] = { 29019, 29020, 29028, 29046, 29047, 29066, 29067, 29068 };

	// Baium,Valakas,Scarlet van Halisha(small),Scarlet van Halisha(lage)
	static final int _bossIds[] = { 29020, 29028, 29046, 29047 };

    MobGroup _group1;
    MobGroup _group2;
    int _group1Id;
    int _group2Id;

    public static BvBManager getInstance()
    {
        if (_instance == null) _instance = new BvBManager();

    	return _instance;
    }

    public void start()
    {
    	// �d���h�~
    	if (_group1 != null && _group2 != null)
    		return;

    	if (_group1 == null) {
	        _group1Id = MobGroupTable.getInstance().getGroupCount() + 1;
			L2NpcTemplate template1 = NpcData.getInstance().getTemplate(_bossIds[Rnd.get(_bossIds.length)]);
			_group1 = new MobGroup(_group1Id, template1, 1);
			MobGroupTable.getInstance().addGroup(_group1Id, _group1);	//�y���Ӂz
			_group1.spawnGroup(_spawnLocation1[0], _spawnLocation1[1], _spawnLocation1[2]);
			_group1.getMobs().get(0).setHeading(_spawnLocation1[3]);
			Announcements.getInstance().announceToAll(_group1.getMobs().get(0).getName() + " ���o�����܂����B");
    	}

    	if (_group2 == null) {
	    	_group2Id = MobGroupTable.getInstance().getGroupCount() + 1;
			L2NpcTemplate template2 = NpcData.getInstance().getTemplate(_bossIds[Rnd.get(_bossIds.length)]);
			_group2 = new MobGroup(_group2Id, template2, 1);
			MobGroupTable.getInstance().addGroup(_group2Id, _group2);	//�y���Ӂz
			_group2.spawnGroup(_spawnLocation2[0], _spawnLocation2[1], _spawnLocation2[2]);
			_group2.getMobs().get(0).setHeading(_spawnLocation2[3]);
			Announcements.getInstance().announceToAll(_group2.getMobs().get(0).getName() + " ���o�����܂����B");
    	}

		// Battle time up after 120min.
		if (_TimeUpTask == null)
			_TimeUpTask = ThreadPoolManager.getInstance().scheduleGeneral(new TimeUp(), 7200000);

	    // Battle start after 10sec.
		if (_CheckWinnerTask == null)
			_CheckWinnerTask = ThreadPoolManager.getInstance().scheduleGeneral(new CheckWinner(), 10000);

		Announcements.getInstance().announceToAll(_group1.getMobs().get(0).getName() + " �� " + _group2.getMobs().get(0).getName() + " ���킢�܂��B");
    }

	public void end()
    {
		if (_group1 != null) {
	    	_group1.unspawnGroup();
	    	MobGroupTable.getInstance().removeGroup(_group1Id);
	    	_group1 = null;
		}

    	if (_group2 != null) {
			_group2.unspawnGroup();
	    	MobGroupTable.getInstance().removeGroup(_group2Id);
	    	_group2 = null;
    	}

    	if (_TimeUpTask != null) { _TimeUpTask.cancel(true); _TimeUpTask = null; }
    	if (_CheckWinnerTask != null) { _CheckWinnerTask.cancel(true); _CheckWinnerTask = null; }
    }

	/**
	 * �������ԏI��
	 */
    ScheduledFuture<?> _TimeUpTask;
    class TimeUp implements Runnable
    {
    	@Override
		public void run()
    	{
    		Announcements.getInstance().announceToAll("���Ԑ؂���������ł��B");
    		end();
    	}
    }


    /**
     * ���S�`�F�b�N�B�ǂ��炩�Е��������c��Ώ��ҁB���������܂ŌJ��Ԃ��B
     */
    ScheduledFuture<?> _CheckWinnerTask;
	class CheckWinner implements Runnable
	{
		@Override
		public void run()
		{
    		assert _group1 != null && _group2 != null;

    		if (_group1.getMobs().get(0).isDead())
    		{
    	    	_group1.unspawnGroup();
    	    	MobGroupTable.getInstance().removeGroup(_group1Id);
    	    	_group1 = null;
    		}
    		if (_group2.getMobs().get(0).isDead())
    		{
    			_group2.unspawnGroup();
    	    	MobGroupTable.getInstance().removeGroup(_group2Id);
    	    	_group2 = null;
    		}

			_CheckWinnerTask = null;
    		if (_group1 != null && _group2 != null) {
    			L2ControllableMobAI ai1 = (L2ControllableMobAI)_group1.getMobs().get(0).getAI();
    			ai1.setAlternateAI(L2ControllableMobAI.AI_FORCEATTACK);
    			ai1.forceAttack(_group2.getMobs().get(0));
    			
    			L2ControllableMobAI ai2 = (L2ControllableMobAI)_group2.getMobs().get(0).getAI();
    			ai2.setAlternateAI(L2ControllableMobAI.AI_FORCEATTACK);
    			ai2.forceAttack(_group1.getMobs().get(0));

    			_CheckWinnerTask = ThreadPoolManager.getInstance().scheduleGeneral(new CheckWinner(), 5000);
    		}
    		else if (_group1 != null)
    		{
   				Announcements.getInstance().announceToAll(_group1.getMobs().get(0).getName() + " ���������܂����B");
    			L2ControllableMobAI ai1 = (L2ControllableMobAI)_group1.getMobs().get(0).getAI();
    			ai1.move(_spawnLocation1[0], _spawnLocation1[1], _spawnLocation1[2]);
    		}
    		else if (_group2 != null)
    		{
    			Announcements.getInstance().announceToAll(_group2.getMobs().get(0).getName() + " ���������܂����B");
    			L2ControllableMobAI ai2 = (L2ControllableMobAI)_group1.getMobs().get(0).getAI();
    			ai2.move(_spawnLocation2[0], _spawnLocation2[1], _spawnLocation2[2]);
    		}
    		else
    		{
    			end();
    		}
    	}
    }
}
