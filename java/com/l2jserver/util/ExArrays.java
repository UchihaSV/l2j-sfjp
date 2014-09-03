//@formatter:off
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
package com.l2jserver.util;	//TODO: package jp.sf.l2j.troja;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author JOJO
 */
public class ExArrays
{
	/*
	 * 
	 */
	public static boolean[] add(boolean[] array, boolean value)
	{
		return push(array, value);
	}
	
	public static short[] add(short[] array, short value)
	{
		return push(array, value);
	}
	
	public static int[] add(int[] array, int value)
	{
		return push(array, value);
	}
	
	public static long[] add(long[] array, long value)
	{
		return push(array, value);
	}
	
	public static float[] add(float[] array, float value)
	{
		return push(array, value);
	}
	
	public static double [] add(double [] array, double  value)
	{
		return push(array, value);
	}
	
	public static <E> E[] add(E[] array, E value)
	{
		return push(array, value);
	}
	
	public static boolean[] put(boolean[] array, int index, boolean value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static short[] put(short[] array, int index, short value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static int[] put(int[] array, int index, int value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static long[] put(long[] array, int index, long value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static float[] put(float[] array, int index, float value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static double[] put(double[] array, int index, double value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static <E> E[] put(E[] array, int index, E value) /* throws IndexOutOfBoundsException */
	{
		if (array == null || index == array.length) array = push(array, value);
		else array[index] = value;
		return array;
	}
	
	public static boolean get(boolean[] array, int index)
	{
		return get(array, index, false);
	}
	
	public static short get(short[] array, int index)
	{
		return get(array, index, (short) 0);
	}
	
	public static int get(int[] array, int index)
	{
		return get(array, index, 0);
	}
	
	public static long get(long[] array, int index)
	{
		return get(array, index, 0L);
	}
	
	public static float get(float[] array, int index)
	{
		return get(array, index, 0F);
	}
	
	public static double get(double[] array, int index)
	{
		return get(array, index, 0D);
	}
	
	public static <E> E get(E[] array, int index)
	{
		return get(array, index, null);
	}
	
	public static boolean get(boolean[] array, int index, boolean defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static short get(short[] array, int index, short defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static int get(int[] array, int index, int defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static long get(long[] array, int index, long defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static float get(float[] array, int index, float defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static double get(double[] array, int index, double defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static <E> E get(E[] array, int index, E defaultValue)
	{
		return index >= 0 && index < array.length ? array[index] : defaultValue;
	}
	
	public static boolean[] push(boolean[] array, boolean value)
	{
		if (array == null) {
			return new boolean[]{value};
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	public static short[] push(short[] array, short value)
	{
		if (array == null) {
			return new short[]{value};
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	public static int[] push(int[] array, int value)
	{
		if (array == null) {
			return new int[]{value};
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	public static long[] push(long[] array, long value)
	{
		if (array == null) {
			return new long[]{value};
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	public static float[] push(float[] array, float value)
	{
		if (array == null) {
			return new float[]{value};
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	public static double[] push(double[] array, double value)
	{
		if (array == null) {
			return new double[]{value};
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	public static <E> E[] push(E[] array, E value)
	{
		if (array == null) {
			@SuppressWarnings("unchecked") E[] copy = (E[]) Array.newInstance(value.getClass(), 1);
			copy[0] = value;
			return copy;
		} else {
			final int index = array.length;
			array = Arrays.copyOf(array, index + 1);
			array[index] = value;
			return array;
		}
	}
	
	/*
	 * 
	 */
	public static int[] toPrimitiveArray(Collection<Integer> list)
	{
		int index = 0;
		int[] array = new int[list.size()];
		for (int s : list)
			array[index++] = s;
		return array;
	}
	
	public static int[] toPrimitiveArray(Collection<Integer> list, int nullValue)
	{
		int index = 0;
		int[] array = new int[list.size()];
		for (java.util.Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
			Integer v = it.next();
			array[index++] = v == null ? nullValue : v.intValue();
		}
		return array;
	}
	
	public static int[] toPrimitiveArrayTrim(Collection<Integer> list)
	{
		int index = 0;
		int[] array = new int[list.size()];
		for (java.util.Iterator<Integer> it = list.iterator(); it.hasNext(); ) {
			Integer v = it.next();
			if (v != null)
				array[index++] = v.intValue();
		}
		if (index != array.length) {
			int[] tmp = new int[index];
			System.arraycopy(array, 0, tmp, 0, index);
			array = tmp;
		}
		return array;
	}
}
