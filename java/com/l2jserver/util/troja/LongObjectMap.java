///////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2001, Eric D. Friedman All Rights Reserved.
// Copyright (c) 2009, Rob Eden All Rights Reserved.
// Copyright (c) 2009, Jeff Randall All Rights Reserved.
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this program; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
///////////////////////////////////////////////////////////////////////////////

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
	long[] keys();
	LongIterator keySetIterator();
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
