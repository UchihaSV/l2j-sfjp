package com.l2jserver.util.troja.test;

import com.l2jserver.util.Rnd;
import com.l2jserver.util.troja.FastIntObjectMap;
import com.l2jserver.util.troja.IntObjectMap;

public class TestPutFastIntObjectMap
{
	private static IntObjectMap<String> map = new FastIntObjectMap<String>().shared();	//1,440,435,058
	
	static class Append implements Runnable
	{
		@Override
		public void run()
		{
			for (int i = 0; i < 100000; i++)
			{
				int key = Rnd.get(2000000000);
				String value = String.valueOf(key);
				map.put(key, value);
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException
	{
		System.out.print(map.getClass().getSimpleName() + ": ");
		Append a = new Append();
		Thread a1 = new Thread(a);
		Thread a2 = new Thread(a);
		Thread a3 = new Thread(a);
		Thread a4 = new Thread(a);
		Thread a5 = new Thread(a);
		Thread a6 = new Thread(a);
		Thread a7 = new Thread(a);
		Thread a8 = new Thread(a);
		
		long start = System.nanoTime();
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		a6.start();
		a7.start();
		a8.start();
		
		a1.join();
		a2.join();
		a3.join();
		a4.join();
		a5.join();
		a6.join();
		a7.join();
		a8.join();
		long time = System.nanoTime() - start;
		
		System.out.println(String.format("%1$,3d", time));
	}
}
