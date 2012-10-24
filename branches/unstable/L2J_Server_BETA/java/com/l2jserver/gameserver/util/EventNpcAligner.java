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
 * 村の冒険者インストラクター、初心者案内人周辺のイベントNPCが混み合ってきたので
 * 他のNPCと重ならないように自動的に位置補正するロジック。
 * 冒険者インストラクター、初心者案内人を基点に位置補正します。
 * 
 * @author JOJO
 */
public class EventNpcAligner
{
	private/*public*/ static final Location[][] SPAWN_DATA =
	{
		{new Location(  82789,  149377, -3464, 49224 )                                              },	//ギラン城の村
		{new Location( 147049,   25939, -2008, 49151 )                                              },	//アデン城の村
		{new Location( 148077,  -55417, -2728, 32768 ),new Location( 148046,  -55328, -2728, 40960 )},	//ゴダード城の村
		{new Location(  43566,  -47657,  -792, 30616 ),new Location(  43546,  -47556,  -792, 36864 )},	//ルウン城の村
		{new Location(  17119,  144848, -3008, 26624 ),new Location(  17175,  144927, -3008, 21845 )},	//ディオン城の村
		{new Location(  82335,   53283, -1488, 16384 )                                              },	//オーレン城の村
		{new Location( -13946,  121932, -2984, 21849 ),new Location( -13880,  122006, -2984, 22248 )},	//グルーディオ城の村
		{new Location( -83123,  150918, -3120,     0 ),new Location( -83123,  150818, -3120,     0 )},	//グルーディン村
		{new Location(  87102, -141328, -1336, 49296 )                                              },	//シュチュッツガルト城の村
		{new Location( 111168,  221058, -3544,     0 ),new Location( 111168,  220958, -3544,     0 )},	//水上都市ハイネス
		{new Location( 116970,   77223, -2688, 40960 ),new Location( 116900,   77293, -2688, 40960 )},	//猟師の村
		{new Location( -84121,  243256, -3728,  9000 ),new Location( -84043,  243195, -3728,  9000 )},	//話せる島の村
		{new Location( 115642, -178046,  -896, 32768 ),new Location( 115622, -177946,  -896, 32768 )},	//ドワーフ村
		{new Location( -45042, -113648,  -192, 32768 ),new Location( -45042, -113548,  -192, 32768 )},	//オーク村
		{new Location(  12099,   16633, -4584, 63240 ),new Location(  12123,   16739, -4584, 63240 )},	//ダークエルフ村
		{new Location(  45525,   48363, -3056, 49152 ),new Location(  45425,   48355, -3056, 49152 )},	//エルフ村
		{new Location(-119692,   44454,   360, 33324 ),new Location(-119692,   44554,   360, 33324 )},	//カマエル村
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
					case 32327:	//冒険者 インストラクター
					case 30598:	//初心者案内人／話せる島の村
					case 30599:	//初心者案内人／エルフ村
					case 30600:	//初心者案内人／ダークエルフ村
					case 30601:	//初心者案内人／ドワーフ村
					case 30602:	//初心者案内人／オーク村
					case 31076:	//初心者案内人／グルーディン村
					case 31077:	//初心者案内人／グルーディオ城の村
					case 32135:	//初心者案内人／カマエル村
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
	 * 他のNPCと重なるときは右に移動するバージョン。
	 * 基準NPC(村の冒険者インストラクター、初心者案内人)が近くにいないとき用。
	 */
	public static L2Npc alignRight(L2Npc manager)
	{
		final int gx = manager.getX(), gy = manager.getY();
		int mX = manager.getX(), mY = manager.getY(), mZ = manager.getZ();
		int h = (manager.getHeading() + 16384) % 65536;							// 右+16384 左-16384
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
