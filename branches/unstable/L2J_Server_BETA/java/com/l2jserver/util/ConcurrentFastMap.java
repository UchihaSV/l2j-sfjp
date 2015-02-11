/**
 * FastMap java 8 support.
 * @auther: JOJO
 */
package com.l2jserver.util;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import javolution.util.FastMap;

@SuppressWarnings("serial")
public class ConcurrentFastMap<K, V> extends FastMap<K, V>
{
	public ConcurrentFastMap()
	{
		shared();
	}
	
	@Override
	public void forEach(BiConsumer<? super K, ? super V> action) {
		if (action == null) throw new NullPointerException();
		for (java.util.Map.Entry<K, V> entry : entrySet())
			if (entry != null)
				action.accept(entry.getKey(), entry.getValue());
	}
	
	@Override
	public V computeIfAbsent(K key, Function<? super K, ? extends V> function) {
		if (function == null) throw new NullPointerException();
		V oldValue;
		if ((oldValue = get(key)) != null) return oldValue;
		synchronized (this) {
			if ((oldValue = get(key)) != null) return oldValue;
			V newValue = function.apply(key);	// -> new V
			if (newValue != null)
		//TODO:	FastMap#put(key, newValue, _isDirectKeyComparator ? key.hashCode() : _keyComparator.hashCodeOf(key), false , false, false); private --> protected
				put(key, newValue);	// maybe synchronized double.
			return newValue;
		}
	}
	
	@Override
	public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> function) {
		if (function == null) throw new NullPointerException();
		javolution.util.FastMap.Entry<K, V> entry;
		if ((entry = getEntry(key)) == null) return null;
		synchronized (this) {
			if ((entry = getEntry(key)) == null) return null;
			V newValue = function.apply(key, entry.getValue());	// -> new V
			if (newValue != null)
				entry.setValue(newValue);
			else
		//TODO:	FastMap#remove(key, false); private --> protected
				remove(key);	// maybe synchronized double.
			return newValue;
		}
	}
}
