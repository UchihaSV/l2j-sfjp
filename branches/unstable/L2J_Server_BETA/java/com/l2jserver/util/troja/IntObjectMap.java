package com.l2jserver.util.troja;

import gnu.trove.map.TIntObjectMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface IntObjectMap<V>
{
	int size();
	boolean isEmpty();
	boolean containsKey(int key);
	boolean containsValue(Object value);
	V get(int key);
	V put(int key, V value);
	V remove(int key);
	void putAll(IntObjectMap<? extends V> m);
	void putAll(TIntObjectMap<? extends V> m);
	void putAll(Map<Integer, ? extends V> m);
	void clear();
	int[] keys(); //TODO: Set keySet();
	Collection<V> values();
	Set<IntObjectMap.Entry<V>> entrySet();
	
	boolean forEachKey(IntProcedure procedure);
	boolean forEachValue(ObjectProcedure<? super V> procedure);
	boolean forEachEntry(IntObjectProcedure<? super V> procedure);
	
	interface Entry<V>
	{
		Entry<V> getNext();
		Entry<V> getPrevious();
		int  getKey();
		int getKeyHash();
		V  getValue();
		V  setValue(V  value);
		
		@Override boolean equals(Object o);
		@Override int hashCode();
	}
	
	@Override boolean equals(Object o);
	@Override int hashCode();
}
