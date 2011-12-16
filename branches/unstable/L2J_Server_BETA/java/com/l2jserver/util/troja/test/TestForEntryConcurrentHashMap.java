package com.l2jserver.util.troja.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.l2jserver.util.Rnd;

public class TestForEntryConcurrentHashMap
{
//	private static Map<Integer, String> map = new Hashtable<>();						//ConcurrentModificationException
//	private static Map<Integer, String> map = java.util.Collections.synchronizedMap(new java.util.HashMap<Integer, String>());	//ConcurrentModificationException
	private static Map<Integer, String> map = new ConcurrentHashMap<>();				//8,543,373,680
//	private static Map<Integer, String> map = new FastMap<>();							//7,001,613,325
//	private static Map<Integer, String> map = new FastMap<Integer, String>().shared();	//6,839,550,927
	static
	{
		for (int i = 0; i < 800000; i++)
		{
			int key = Rnd.get(2000000000);
			String value = String.valueOf(key);
			map.put(key, value);
		}
	}
	
	static class Iterate implements Runnable
	{
		@Override
		public void run()
		{
			for (int i = 0; i < 20; i++)
			{
				int count = 0;
				for (java.util.Map.Entry<Integer, String> e : map.entrySet())
				{
					int key = e.getKey();
					if (key % 10 == 0)
						++count;
				}
				map.put(count, "*");
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		System.out.print(map.getClass().getSimpleName() + ": ");
		Iterate i = new Iterate();
		Thread i1 = new Thread(i);
		Thread i2 = new Thread(i);
		Thread i3 = new Thread(i);
		Thread i4 = new Thread(i);
		Thread i5 = new Thread(i);
		Thread i6 = new Thread(i);
		Thread i7 = new Thread(i);
		Thread i8 = new Thread(i);
		
		long start = System.nanoTime();
		i1.start();
		i2.start();
		i3.start();
		i4.start();
		i5.start();
		i6.start();
		i7.start();
		i8.start();
		
		i1.join();
		i2.join();
		i3.join();
		i4.join();
		i5.join();
		i6.join();
		i7.join();
		i8.join();
		long time = System.nanoTime() - start;
		
		System.out.println(String.format("%1$,3d", time));
	}
}
