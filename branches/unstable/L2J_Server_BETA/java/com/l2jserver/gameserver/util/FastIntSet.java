//@formatter:off
package com.l2jserver.gameserver.util;	//TODO: package jp.sf.l2j.troja;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import jp.sf.l2j.troja.FastIntObjectMap;
import jp.sf.l2j.troja.IntIterator;
import jp.sf.l2j.troja.IntObjectMap;

/**
 * @author JOJO
 */
public class FastIntSet /*implements Set*/
{
	private FastIntObjectMap<Object> map = new FastIntObjectMap<>();
	private static final Object VALUE = Boolean.TRUE;	// dummy
	
	public FastIntSet shared() { map.shared(); return this; }
	public boolean putIfAbsent(int key) { return map.putIfAbsent(key, VALUE) == null; }
	
	public boolean add(int key) { return map.put(key, VALUE) == null; }
	public boolean remove(int key) { return map.remove(key) != null; }
	public boolean contains(int key) { return map.containsKey(key); }
	
	public void clear() { map.clear(); }
	public int size() { return map.size(); }
	public boolean isEmpty() { return map.isEmpty(); }
	
	public IntIterator iterator() { return map.keySetIterator(); }
	public Set<IntObjectMap.Entry<Object>> entrySet() { return map.entrySet(); }
	
	public int[] toArray() { return map.keys(); }
	
	public int[] toArray(int[] a) {
		if (a.length < map.size())
			return Arrays.copyOf(map.keys(), map.size());
		System.arraycopy(map.keys(), 0, a, 0, map.size());
		return a;
	}
	
	public boolean containsAll(int[]               a) { for (int key : a) if (!map.containsKey(key)) return false; return true; }
	public boolean containsAll(Collection<Integer> c) { for (int key : c) if (!map.containsKey(key)) return false; return true; }
	public boolean addAll(int[]               a) { boolean changed = false; for (int key : a) changed |= map.put(key, VALUE) == null; return changed; }
	public boolean addAll(Collection<Integer> c) { boolean changed = false; for (int key : c) changed |= map.put(key, VALUE) == null; return changed; }
 //	public boolean retainAll(int[]               a) { throw new UnsupportedOperationException(); }
 //	public boolean retainAll(Collection<Integer> c) { throw new UnsupportedOperationException(); }
	public boolean removeAll(int[]               a) { boolean changed = false; for (int key : a) changed |= map.remove(key) != null; return changed; }
	public boolean removeAll(Collection<Integer> c) { boolean changed = false; for (int key : c) changed |= map.remove(key) != null; return changed; }

	@Override public boolean equals(Object o) { return map.equals(o); }
	@Override public int hashCode() { return map.hashCode(); }
}
