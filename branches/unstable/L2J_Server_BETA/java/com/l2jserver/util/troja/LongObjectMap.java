package com.l2jserver.util.troja;

import gnu.trove.map.TLongObjectMap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface LongObjectMap<V>
{
	int size();
	boolean isEmpty();
	boolean containsKey(long key);
	boolean containsValue(Object value);
	V get(long key);
	V put(long key, V value);
	V remove(long key);
	void putAll(LongObjectMap<? extends V> m);
	void putAll(TLongObjectMap<? extends V> m);
	void putAll(Map<Long, ? extends V> m);
	void clear();
	long[] keys(); //TODO: Set keySet();
	Collection<V> values();
	Set<LongObjectMap.Entry<V>> entrySet();
	
	boolean forEachKey(LongProcedure procedure);
	boolean forEachValue(ObjectProcedure<? super V> procedure);
	boolean forEachEntry(LongObjectProcedure<? super V> procedure);
	
	interface Entry<V>
	{
		Entry<V> getNext();
		Entry<V> getPrevious();
		long  getKey();
		int getKeyHash();
		V  getValue();
		V  setValue(V  value);
		
		@Override boolean equals(Object o);
		@Override int hashCode();
	}
	
	@Override boolean equals(Object o);
	@Override int hashCode();
}
