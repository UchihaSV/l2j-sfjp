package com.l2jserver.gameserver.util;

import java.util.List;

import com.l2jserver.gameserver.model.L2Object;
import com.l2jserver.gameserver.model.L2Spawn;
import com.l2jserver.gameserver.model.L2World;
import com.l2jserver.gameserver.model.Location;
import com.l2jserver.gameserver.model.actor.L2Npc;
import com.l2jserver.gameserver.network.serverpackets.ValidateLocation;
import com.l2jserver.util.Rnd;

/**
 * ���̖`���҃C���X�g���N�^�[�A���S�҈ē��l���ӂ̃C�x���gNPC�����ݍ����Ă����̂�
 * ����NPC�Əd�Ȃ�Ȃ��悤�Ɏ����I�Ɉʒu�␳���郍�W�b�N�B
 * �`���҃C���X�g���N�^�[�A���S�҈ē��l����_�Ɉʒu�␳���܂��B
 * 
 * @author JOJO
 */
public class EventNpcAligner
{
	private/*public*/ static final Location[][] SPAWN_DATA =
	{
		{new Location(  82789,  149377, -3464, 49224 )                                              },	//�M������̑�
		{new Location( 147049,   25939, -2008, 49151 )                                              },	//�A�f����̑�
		{new Location( 148077,  -55417, -2728, 32768 ),new Location( 148046,  -55328, -2728, 40960 )},	//�S�_�[�h��̑�
		{new Location(  43566,  -47657,  -792, 30616 ),new Location(  43546,  -47556,  -792, 36864 )},	//���E����̑�
		{new Location(  17119,  144848, -3008, 26624 ),new Location(  17175,  144927, -3008, 21845 )},	//�f�B�I����̑�
		{new Location(  82335,   53283, -1488, 16384 )                                              },	//�I�[������̑�
		{new Location( -13946,  121932, -2984, 21849 ),new Location( -13880,  122006, -2984, 22248 )},	//�O���[�f�B�I��̑�
		{new Location( -83123,  150918, -3120,     0 ),new Location( -83123,  150818, -3120,     0 )},	//�O���[�f�B����
		{new Location(  87102, -141328, -1336, 49296 )                                              },	//�V���`���b�c�K���g��̑�
		{new Location( 111168,  221058, -3544,     0 ),new Location( 111168,  220958, -3544,     0 )},	//����s�s�n�C�l�X
		{new Location( 116970,   77223, -2688, 40960 ),new Location( 116900,   77293, -2688, 40960 )},	//�t�̑�
		{new Location( -84121,  243256, -3728,  9000 ),new Location( -84043,  243195, -3728,  9000 )},	//�b���铇�̑�
		{new Location( 115642, -178046,  -896, 32768 ),new Location( 115622, -177946,  -896, 32768 )},	//�h���[�t��
		{new Location( -45042, -113648,  -192, 32768 ),new Location( -45042, -113548,  -192, 32768 )},	//�I�[�N��
		{new Location(  12099,   16633, -4584, 63240 ),new Location(  12123,   16739, -4584, 63240 )},	//�_�[�N�G���t��
		{new Location(  45525,   48363, -3056, 49152 ),new Location(  45425,   48355, -3056, 49152 )},	//�G���t��
		{new Location(-119692,   44454,   360, 33324 ),new Location(-119692,   44554,   360, 33324 )},	//�J�}�G����
	};
	
	public static Location[] getSpawns()
	{
		Location[] spawns = new Location[SPAWN_DATA.length];
		for (int i = SPAWN_DATA.length; --i >= 0;)
			spawns[i] = SPAWN_DATA[i][Rnd.get(SPAWN_DATA[i].length)];
		return spawns;
	}
	
	public static L2Npc align(L2Npc manager)
	{
		int mX = manager.getX(), mY = manager.getY(), mZ = manager.getZ();
		L2Npc guide = null;
		
		List<L2Object> knownObjects = L2World.getInstance().getVisibleObjects(manager, 300);
		LOOP1: for (;;) {
			for (L2Object o : knownObjects)
			{
				if (o instanceof L2Npc)
				{
					L2Npc obj = (L2Npc)o;
					final double collisionRadius = Math.max(30, manager.getTemplate().getCollisionRadius() * 2 + obj.getTemplate().getCollisionRadius());
					boolean isMove = false;
					while (Util.calculateDistance(mX, mY, obj.getX(), obj.getY()) < collisionRadius)
					{
						if (guide == null)
						{
							guide = findGuide(manager, knownObjects);
							if (guide == null)
							{
								//System.out.println("__BASENAME__:__LINE__: getNearestGuide(" + manager.getNpcId() + manager.getName() + ") is null");
								return manager;
							}
						}
						final int gx = guide.getX(), gy = guide.getY();
						final double dx = mX - gx, dy = mY - gy;
						final double angle = Math.atan2(dy, dx);
						final double radius = Math.sqrt(dx * dx + dy * dy) + manager.getTemplate().getCollisionRadius() * 2;
						mX = gx + (int)Math.round(Math.cos(angle) * radius);
						mY = gy + (int)Math.round(Math.sin(angle) * radius);
						isMove = true;
					}
					if (isMove)
						continue LOOP1; /*goto LOOP1;*/
				}
			}
			break;
		}
		
		if (mX != manager.getX() || mY != manager.getY())
		{
			//System.out.println("__BASENAME__:__LINE__: " + manager.getNpcId() + manager.getName() + ".setXYZ(" + mX + "," + mY + "," + mZ + ")");
			manager.setXYZ(mX, mY, mZ);
			manager.broadcastPacket(new ValidateLocation(manager));
			L2Spawn spawn = manager.getSpawn();
			spawn.setXYZ(mX, mY, mZ);
		}
		return manager;
	}
	
	private static L2Npc findGuide(L2Npc npc, List<L2Object> knownObjects)
	{
		L2Npc nearestGuide = null;
		double distance = Double.MAX_VALUE;
		for (L2Object o : knownObjects)
		{
			if (o instanceof L2Npc)
			{
				L2Npc obj = (L2Npc)o;
				switch (obj.getNpcId())
				{
//					default:
//						if (obj.getInstanceType() != InstanceType.L2AdventurerInstance)
//							break;
					case 32327:	//�`���� �C���X�g���N�^�[
					case 30598:	//���S�҈ē��l�^�b���铇�̑�
					case 30599:	//���S�҈ē��l�^�G���t��
					case 30600:	//���S�҈ē��l�^�_�[�N�G���t��
					case 30601:	//���S�҈ē��l�^�h���[�t��
					case 30602:	//���S�҈ē��l�^�I�[�N��
					case 31076:	//���S�҈ē��l�^�O���[�f�B����
					case 31077:	//���S�҈ē��l�^�O���[�f�B�I��̑�
					case 32135:	//���S�҈ē��l�^�J�}�G����
						double d = Util.calculateDistance(npc, obj, true);
						if (distance > d)
						{
							distance = d;
							nearestGuide = obj;
						}
				}
			}
		}
		return nearestGuide;
	}
	
	/**
	 * ����NPC�Əd�Ȃ�Ƃ��͉E�Ɉړ�����o�[�W�����B
	 * �NPC(���̖`���҃C���X�g���N�^�[�A���S�҈ē��l)���߂��ɂ��Ȃ��Ƃ��p�B
	 */
	public static L2Npc alignRight(L2Npc manager)
	{
		final int gx = manager.getX(), gy = manager.getY();
		int mX = manager.getX(), mY = manager.getY(), mZ = manager.getZ();
		int h = (manager.getHeading() + 16384) % 65536;							// �E+16384 ��-16384
 		final double angle = h * (javolution.lang.MathLib.TWO_PI / 65536.0);
		double radius = 0;
		
		List<L2Object> knownObjects = L2World.getInstance().getVisibleObjects(manager, 300);
		LOOP1: for (;;) {
			for (L2Object o : knownObjects)
			{
				if (o instanceof L2Npc)
				{
					L2Npc obj = (L2Npc)o;
					final double collisionRadius = Math.max(30, manager.getTemplate().getCollisionRadius() * 2 + obj.getTemplate().getCollisionRadius());
					boolean isMove = false;
					while (Util.calculateDistance(mX, mY, obj.getX(), obj.getY()) < collisionRadius)
					{
						radius += manager.getTemplate().getCollisionRadius() * 2;
						mX = gx + (int)Math.round(Math.cos(angle) * radius);
						mY = gy + (int)Math.round(Math.sin(angle) * radius);
						isMove = true;
					}
					if (isMove)
						continue LOOP1; /*goto LOOP1;*/
				}
			}
			break;
		}
		
		if (mX != manager.getX() || mY != manager.getY())
		{
			manager.setXYZ(mX, mY, mZ);
			manager.broadcastPacket(new ValidateLocation(manager));
			L2Spawn spawn = manager.getSpawn();
			spawn.setXYZ(mX, mY, mZ);
		}
		return manager;
	}
}
