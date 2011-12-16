package com.l2jserver.util.troja.test;

import com.l2jserver.util.Rnd;
import com.l2jserver.util.troja.FastIntObjectMap;
import com.l2jserver.util.troja.IntObjectMap;
import com.l2jserver.util.troja.IntObjectProcedure;

public class TestForEntryFastIntObjectMap2
{
	private static IntObjectMap<String> map = new FastIntObjectMap<String>().shared();	//2,427,303,580
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
		int count;
		@Override
		public void run()
		{
			for (int i = 0; i < 20; i++)
			{
				count = 0;
				map.forEachEntry(new IntObjectProcedure<String>() {
					@Override
					public boolean execute(int key, String value)
					{
						if (key % 10 == 0)
							++count;
						return true;
					}
				});
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
