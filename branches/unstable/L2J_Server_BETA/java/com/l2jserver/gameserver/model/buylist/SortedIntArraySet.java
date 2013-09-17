package com.l2jserver.gameserver.model.buylist;

import java.util.Arrays;

/**
 * @author JOJO
 */
class SortedIntArraySet
{
	static int[] add(int[] array, int value)
	{
		if (array == null)
			return new int[]{ value };
		int index = Arrays.binarySearch(array, value);
		if (index >= 0)
			return array;
		index = -(index + 1);
		final int length = array.length;
		int[] copy = new int[length + 1];
		System.arraycopy(array, 0, copy, 0, length);
		int n;
		if ((n = length - index) > 0)
			System.arraycopy(copy, index, copy, index + 1, n);
        copy[index] = value;
		return copy;
	}
	
	static boolean contains(int[] array, int value)
	{
		return array != null && Arrays.binarySearch(array, value) >= 0;
	}
}
