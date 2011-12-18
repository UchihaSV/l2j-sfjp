/*
 * Troja: Trove + Javolution.
 * Version: Beta 1 based on Trove 3.0.2 and Javolution 5.5.1
 * Auther: tukune
 * 
 * DON'T CODE FORMAT!
 * DON'T CONVERT TAB <---> SPACE
 * DON'T STRIP TRAILING BLANKS FROM END OF LINE
 */
/*
 * Javolution - Java(TM) Solution for Real-Time and Embedded Systems
 * Copyright (C) 2006 - Javolution (http://javolution.org/)
 * All rights reserved.
 * 
 * Permission to use, copy, modify, and distribute this software is
 * freely granted, provided that this notice is preserved.
 */
package com.l2jserver.util.troja;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.procedure.TIntObjectProcedure;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import javax.realtime.MemoryArea;

import javolution.context.ArrayFactory;
import javolution.context.LogContext;
import javolution.context.ObjectFactory;
import javolution.lang.MathLib;
import javolution.lang.Realtime;
import javolution.lang.Reusable;
import javolution.text.Text;
import javolution.util.FastCollection;
import javolution.util.FastCollection.Record;
import javolution.util.FastComparator;
import javolution.util.FastMap;
import javolution.xml.XMLSerializable;


/**
 * <p> This class represents a hash map with real-time behavior; 
 *     smooth capacity increase and <i>thread-safe</i> without external 
 *     synchronization when {@link #shared shared}.</p>
 *     <img src="doc-files/map-put.png"/>
 *     
 * <p> {@link FastMap} has a predictable iteration order, which is the order in
 *     which keys are inserted into the map (similar to 
 *     <code>java.util.LinkedHashMap</code> collection class). If the map is 
 *     marked {@link #shared shared} then all operations are
 *     thread-safe including iterations over the map's collections. 
 *     Unlike <code>ConcurrentHashMap</code>, {@link #get(Object) access} never
 *     blocks; retrieval reflects the map state not older than the last time the
 *     accessing threads have been synchronized (for multi-processors systems
 *     synchronizing ensures that the CPU internal cache is not stale).
 *     In most application it is not a problem because thread synchronization
 *     is done at high level (e.g. scheduler) and threads run freely
 *     (and quickly) until the next synchronization point. In some cases the
 *     "happen before" guarantee is necessary (e.g. to ensure unicity) and
 *     threads have to be synchronized explicitly. Whenever possible such
 *     synchronization should be performed on the key object itself and 
 *     not the whole map. For example:[code]
 *     FastMap<Index, Object> sparseVector = new FastMap<Index, Object>().shared()
 *     ... // Put
 *     synchronized(index) { // javolution.util.Index instances are unique.
 *         sparseVector.put(index, value);
 *     }
 *     ... // Get
 *     synchronized(index) { // Blocking only when accessing the same index.
 *         value = sparseVector.get(index); // Latest value guaranteed.
 *     }[/code]</p>
 *     
 * <p> {@link FastMap} may use custom key comparators; the default comparator is
 *     either {@link FastComparator#DIRECT DIRECT} or 
 *     {@link FastComparator#REHASH REHASH} based upon the current <a href=
 *     "{@docRoot}/overview-summary.html#configuration">Javolution 
 *     Configuration</a>. Users may explicitly set the key comparator to 
 *     {@link FastComparator#DIRECT DIRECT} for optimum performance
 *     when the hash codes are well distributed for all run-time platforms
 *     (e.g. calculated hash codes).</p>
 *     
 * <p> Custom key comparators are extremely useful for value retrieval when
 *     map's keys and argument keys are not of the same class (such as 
 *     {@link String} and {@link javolution.text.Text Text} 
 *     ({@link FastComparator#LEXICAL LEXICAL})), to substitute more efficient
 *     hash code calculations ({@link FastComparator#STRING STRING}) 
 *     or for identity maps ({@link FastComparator#IDENTITY IDENTITY}):[code]
 *     FastMap identityMap = new FastMap().setKeyComparator(FastComparator.IDENTITY);
 *     [/code]</p>
 * 
 * <p> {@link FastMap.Entry} can quickly be iterated over (forward or backward)
 *     without using iterators. For example:[code]
 *     FastMap<String, Thread> map = ...;
 *     for (FastMap.Entry<String, Thread> e = map.head(), end = map.tail(); (e = e.getNext()) != end;) {
 *          String key = e.getKey(); // No typecast necessary.
 *          Thread value = e.getValue(); // No typecast necessary.
 *     }[/code]</p>
 *     
 * <p> Custom map implementations may override the {@link #newEntry} method 
 *     in order to return their own  {@link Entry} implementation (with 
 *     additional fields for example).</p>
 *     
 * <p> {@link FastMap} are {@link Reusable reusable}; they maintain an 
 *     internal pool of <code>Map.Entry</code> objects. When an entry is removed
 *     from a map, it is automatically restored to its pool. Any new entry is
 *     allocated in the same memory area as the map itself (RTSJ). If the map
 *     is shared, removed entries are not recycled but only dereferenced
 *     (to maintain thread-safety) </p>
 *     
 * <p> {@link #shared() Shared} maps do not use internal synchronization, except in case of
 *     concurrent modifications of the map structure (entries being added/deleted).
 *     Reads and iterations are never synchronized and never blocking.
 *     With regards to the memory model, shared maps are equivalent to shared 
 *     non-volatile variables (no "happen before" guarantee). They can be used 
 *     as very efficient lookup tables. For example:[code]
 *     public class Unit {
 *         static FastMap<Unit, String> labels = new FastMap().shared();
 *         ...
 *         public String toString() {
 *             String label = labels.get(this); // No synchronization.
 *             if (label != null) return label;
 *             label = makeLabel();
 *             labels.put(this, label);
 *             return label;
 *         }
 *    }[/code]</p>
 *            
 * <p> <b>Implementation Note:</b> To maintain time-determinism, rehash/resize
 *     is performed only when the map's size is small (see chart). For large 
 *     maps (size > 512), the map is divided recursively into (64)
 *     smaller sub-maps. The cost of the dispatching (based upon hashcode 
 *     value) has been measured to be at most 20% of the access time 
 *     (and most often way less).</p>
 *            
 * @author <a href="mailto:jean-marie@dautelle.com">Jean-Marie Dautelle </a>
 * @version 5.2, September 11, 2007
 */
@SuppressWarnings({ "unchecked", "rawtypes", "javadoc" })
public class FastIntObjectMap <V>  implements IntObjectMap<V>, Reusable,
        XMLSerializable, Realtime {

    private static final boolean REENTRANT_LOCK = true;	// true: ReentrantLock(), false: synchronized
    
    @SuppressWarnings("unused") private static final boolean DEBUG = true;
    private static final int KEY_NULL = 0;

    // We do a full resize (and rehash) only when the capacity is less than C1.
    // For large maps we dispatch to sub-maps.
    private static final int B0 = 4; // Initial capacity in bits.
    private static final int C0 = 1 << B0; // Initial capacity (16)
    private static final int B1 = 10; // Entries array resize limit in bits.
    private static final int C1 = 1 << B1; // Entries array resize limit (1024).
    private static final int B2 = B1 - B0; // Sub-maps array length in bits.
    private static final int C2 = 1 << B2; // Sub-maps array length (64).
    /**
     * Holds the head entry to which the first entry attaches.
     * The head entry never changes (entries always added last).
     */
    private transient Entry <V>  _head;
    /**
     * Holds the tail entry to which the last entry attaches.
     * The tail entry changes as entries are added/removed.
     */
    private transient Entry <V>  _tail;
    /**
     * Holds the map's entries. 
     */
    private transient Entry <V> [] _entries;
    /**
     * Holds the number of user entry in the entry table.
     */
    private transient int _entryCount;
    /**
     * Holds the number of NULL (when entry removed). The number has to be
     * taken into account to clean-up the table if too many NULL are present.
     */
    private transient int _nullCount;
    /**
     * Holds sub-maps (for large collection). 
     */
    private transient FastIntObjectMap[] _subMaps;
    /**
     * Indicates if sub-maps are active.
     */
    private transient boolean _useSubMaps;
    /**
     * The hash shift (for sub-maps to discard bits already taken into account).
     */
    private transient int _keyShift;
    /**
     * Holds the values view.
     */
    private transient Values _values;
//    /**
//     * Holds the key set view.
//     */
//    private transient KeySet _keySet;
    /**
     * Holds the entry set view.
     */
    private transient EntrySet _entrySet;
    /**
     * Holds the unmodifiable view.
     */
    private transient IntObjectMap <V>  _unmodifiable;
//    /**
//     * Holds the key comparator.
//     */
//    private transient FastComparator _keyComparator;
//    /**
//     * Indicates if key comparator is direct.
//     */
//    private transient boolean _isDirectKeyComparator;
    /**
     * Holds the value comparator.
     */
    private transient FastComparator _valueComparator;
    /**
     * Indicates if this map is shared (thread-safe).
     */
    private transient boolean _isShared;
    private ReentrantLock _shared;

    /**
     * Creates a map whose capacity increment smoothly without large resize 
     * operations.
     */
    public FastIntObjectMap() {
        this(4);
    }

    /**
     * Creates a persistent map associated to the specified unique identifier
     * (convenience method).
     * 
     * @param id the unique identifier for this map.
     * @throws IllegalArgumentException if the identifier is not unique.
     * @see javolution.context.PersistentContext.Reference
     */
    public FastIntObjectMap(String id) {
        throw new UnsupportedOperationException();
//        this();
//        new PersistentContext.Reference(id, this) {
//
//            protected void notifyChange() {
//                FastMap.this.clear();
//                FastMap.this.putAll((FastMap) this.get());
//            }
//        };
    }

    /**
     * Creates a map of specified maximum size (a full resize may occur if the 
     * specififed capacity is exceeded).
     * 
     * @param capacity the maximum capacity.
     */
    public FastIntObjectMap(int capacity) {
        setValueComparator(FastComparator.DEFAULT);
        setup(capacity);
    }

    private void setup(int capacity) {
        int tableLength = C0;
        while (tableLength < capacity) {
            tableLength <<= 1;
        }
        _entries = /*(Entry <V> [])*/ new Entry[tableLength << 1];
        _head = newEntry();
        _tail = newEntry();
        _head._next = _tail;
        _tail._previous = _head;
        Entry previous = _tail;
        for (int i = 0; i++ < capacity;) {
            Entry newEntry = newEntry();
            newEntry._previous = previous;
            previous._next = newEntry;
            previous = newEntry;
        }
    }

    /**
     * Creates a map containing the specified entries, in the order they
     * are returned by the map iterator.
     *
     * @param map the map whose entries are to be placed into this map.
     */
    public FastIntObjectMap(IntObjectMap <? extends V>  map) {
        this(map.size());
        putAll(map);
    }
    public FastIntObjectMap(TIntObjectMap  <? extends V>  map) {
        this(map.size());
        putAll(map);
    }
    public FastIntObjectMap(java.util.Map <Integer, ? extends V>  map) {
        this(map.size());
        putAll(map);
    }

    /**
     * Used solely for sub-maps (we don't need head or tail just the table).
     */
    private FastIntObjectMap(Entry[] entries) {
        _entries = entries;
    }

    /**
     * Returns a potentially {@link #recycle recycled} map instance.
     *
     * @return a new, preallocated or recycled map instance.
     */
    public static <V>  FastIntObjectMap <V>  newInstance() {
        return (FastIntObjectMap <V> ) FACTORY.object();
    }

    /**
     * Recycles the specified map instance.
     * 
     * @param instance the map instance to recycle.
     */
    public static void recycle(FastIntObjectMap instance) {
        FACTORY.recycle(instance);
    }

    /**
     * Returns the reverse mapping of this map (for which the keys are this 
     * map's values and the values are this map's keys).
     *
     * @return the map having for keys this map values and for values 
     *         this map's key.
     */
    public final Map <V,Integer>  reverse() {
        FastMap <V,Integer>  map = new FastMap<V,Integer>(FastIntObjectMap.this.size());	//TODO:
        for (Entry <V>  e = _head,  n = _tail; (e = e._next) != n;) {
            map.put(e._value, e._key);
        }
        return map;
    }

    /**
     * Returns the head entry of this map.
     *
     * @return the entry such as <code>head().getNext()</code> holds 
     *         the first map entry.
     */
    public final Entry <V>  head() {
        return _head;
    }

    /**
     * Returns the tail entry of this map.
     *
     * @return the entry such as <code>tail().getPrevious()</code>
     *         holds the last map entry.
     */
    public final Entry <V>  tail() {
        return _tail;
    }

    /**
     * Returns the number of key-value mappings in this {@link FastIntObjectMap}.
     * 
     * <p>Note: If concurrent updates are performed, application should not 
     *          rely upon the size during iterations.</p> 
     * 
     * @return this map's size.
     */
    public final int size() {
        if (!_useSubMaps) {
            return _entryCount;
        }
        int sum = 0;
        for (int i = 0; i < _subMaps.length;) {
            sum += _subMaps[i++].size();
        }
        return sum;
    }

    /**
     * Indicates if this map contains no key-value mappings.
     * 
     * @return <code>true</code> if this map contains no key-value mappings;
     *         <code>false</code> otherwise.
     */
    public final boolean isEmpty() {
        return _head._next == _tail;
    }

    /**
     * Indicates if this map contains a mapping for the specified key.
     * 
     * @param key the key whose presence in this map is to be tested.
     * @return <code>true</code> if this map contains a mapping for the
     *         specified key; <code>false</code> otherwise.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public final boolean containsKey(int key) {
        return getEntry(key) != null;
    }

    /**
     * Indicates if this map associates one or more keys to the specified value.
     * 
     * @param value the value whose presence in this map is to be tested.
     * @return <code>true</code> if this map maps one or more keys to the
     *         specified value.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public final boolean containsValue(Object value) {
        return values().contains(value);
    }

    /**
     * Returns the value to which this map associates the specified key.
     * This method is always thread-safe regardless whether or not the map 
     * is marked {@link #isShared() shared}.
     * 
     * @param key the key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or
     *         <code>null</code> if there is no mapping for the key.
     * @throws NullPointerException if key is <code>null</code>.
     */
    public final  V  get(int key) {
        Entry <V>  entry = getEntry(key);
        return (entry != null) ? entry._value : null;
    }

    /**
     * Returns the entry with the specified key.
     * This method is always thread-safe without synchronization.
     * 
     * @param key the key whose associated entry is to be returned.
     * @return the entry for the specified key or <code>null</code> if none.
     */
    public final Entry <V>  getEntry(int key) {
        return getEntry(key, key);
    }

    private final Entry getEntry(int key, int keyHash) {
        final FastIntObjectMap map = getSubMap(keyHash);
        final Entry[] entries = map._entries; // Atomic.
        final int mask = entries.length - 1;
        for (int i = keyHash >> map._keyShift;; i++) {
            Entry entry = entries[i & mask];
            if (entry == null) {
                return null;
            }
            if (key == entry._key) {
                return entry;
            }
        }
    }

    private final FastIntObjectMap getSubMap(int keyHash) {
        return _useSubMaps ? _subMaps[keyHash & (C2 - 1)].getSubMap(keyHash >> B2) : this;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If this map previously contained a mapping for this key, the old value
     * is replaced. For {@link #isShared() shared} map, internal synchronization
     * is performed only when new entries are created.
     * 
     * @param key the key with which the specified value is to be associated.
     * @param value the value to be associated with the specified key.
     * @return the previous value associated with specified key, or
     *         <code>null</code> if there was no mapping for key. A
     *         <code>null</code> return can also indicate that the map
     *         previously associated <code>null</code> with the specified key.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public final  V  put( int  key,  V  value) {
        return ( V ) put(key, value, key, _isShared, false,
                false);
    }

    private final Object put(int key, Object value, int keyHash,
            boolean concurrent, boolean noReplace/*=putIfAbsent*/, boolean returnEntry) {
        final FastIntObjectMap map = getSubMap(keyHash);
        final Entry[] entries = map._entries; // Atomic.
        final int mask = entries.length - 1;
        int slot = -1;
        for (int i = keyHash >> map._keyShift;; i++) {
            Entry entry = entries[i & mask];
            if (entry == null) {
                slot = slot < 0 ? i & mask : slot;
                break;
            } else if (entry == Entry.NULL) {
                slot = slot < 0 ? i & mask : slot;
            } else if (key == entry._key) {
                if (noReplace) {
                    return returnEntry ? entry : entry._value;
                }
                Object prevValue = entry._value;
                entry._value = value;
                return returnEntry ? entry : prevValue;
            }
        }

        // Add new entry (synchronize if concurrent).
        if (concurrent) {
if (REENTRANT_LOCK) {{
            _shared.lock();
            try {
                return put(key, value, keyHash, false, noReplace, returnEntry);
            }
            finally { _shared.unlock(); }
}} else {{
            synchronized (this) {
                return put(key, value, keyHash, false, noReplace, returnEntry);
            }
}}
        }

        // Setup entry.
        final Entry entry;
        if(!_isShared) {
            entry = _tail;
            entry._key = key;
            entry._value = value;
            entry._keyHash = keyHash;
            if (entry._next == null) {
                createNewEntries();
            }
            entries[slot] = entry;
            map._entryCount += ONE_VOLATILE; // Prevents reordering.
            _tail = _tail._next;
        } else {
        	// keep _tail as the same object
            // check entry caches
            if (_tail._next == null) {
                createNewEntries();
            }
            // assign entry
            entry = _tail._next;
            
            // step forward in the entry cache
            _tail._next = entry._next;
            
            // populate entry
            entry._key = key;
            entry._value = value;
            entry._keyHash = keyHash;
            entry._next = _tail;
            entry._previous = _tail._previous; // backwards
            
              // set the hash table slots
            entries[slot] = entry;
            map._entryCount += ONE_VOLATILE; // Prevents reordering.
            // insert into chain at correct location
            entry._next._previous = entry; // backwards
            entry._previous._next = entry; // forwards
        }
        
        if (map._entryCount + map._nullCount > (entries.length >> 1)) { // Table more than half empty.
        	map.resizeTable(_isShared);
        }
        return returnEntry ? entry : null;
    }

    private void createNewEntries() { // Increase the number of entries.
        MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {

            public void run() {
                Entry previous = _tail;
                for (int i = 0; i < 8; i++) { // Creates 8 entries at a time.
                    Entry <V>  newEntry = newEntry();
                    newEntry._previous = previous;
                    previous._next = newEntry;
                    previous = newEntry;
                }
            }
        });
    }

    // This method is called only on final sub-maps.
    private void resizeTable(final boolean isShared) {
        MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {

            public void run() {

                // Reset the NULL count (we don't copy Entry.NULL).
                final int nullCount = _nullCount;
                _nullCount = 0;

                // Check if we can just cleanup (remove NULL entries).
                if (nullCount > _entryCount) { // Yes.
                    if (isShared) { // Replaces with a new table.
                        Entry[] tmp = new Entry[_entries.length];
                        copyEntries(_entries, tmp, _entries.length);
                        _entries = tmp;
                    } else { // We need a temporary buffer.
                        Object[] tmp = ArrayFactory.OBJECTS_FACTORY.array(_entries.length);
                        System.arraycopy(_entries, 0, tmp, 0, _entries.length);
                        FastIntObjectMap.reset(_entries); // Ok not shared. 
                        copyEntries(tmp, _entries, _entries.length);
                        FastIntObjectMap.reset(tmp); // Clear references.
                        ArrayFactory.OBJECTS_FACTORY.recycle(tmp);
                    }
                    return;
                }

                // Resize if size is small.
                int tableLength = _entries.length << 1;
                if (tableLength <= C1) { // Ok to resize.
                    Entry[] tmp = new Entry[tableLength];
                    copyEntries(_entries, tmp, _entries.length);
                    _entries = tmp;
                    return;
                }

                // No choice but to use sub-maps.
                if (_subMaps == null) { // Creates sub-maps.
                    _subMaps = newSubMaps(tableLength >> (B2 - 1));
                }

                // Copies the current entries to sub-maps. 
                for (int i = 0; i < _entries.length;) {
                    Entry entry = _entries[i++];
                    if ((entry == null) || (entry == Entry.NULL)) {
                        continue;
                    }
                    FastIntObjectMap subMap = _subMaps[(entry._keyHash >> _keyShift) & (C2 - 1)];
                    subMap.mapEntry(entry);
                    if (((subMap._entryCount + subMap._nullCount) << 1) >= subMap._entries.length) {
                        // Serious problem submap already full, don't use submap just resize.
                        LogContext.warning("Unevenly distributed hash code - Degraded Performance");
                        Entry[] tmp = new Entry[tableLength];
                        copyEntries(_entries, tmp, _entries.length);
                        _entries = tmp;
                        _subMaps = null; // Discards sub-maps.
                        return;
                    }
                }
                
                if(isShared) {
                	// clear the entries which now are held by submaps
                    FastIntObjectMap.reset(_entries);
                    _nullCount = 0;
                    _entryCount = 0;
                }
                
                _useSubMaps = ONE_VOLATILE == 1 ? true : false; // Prevents reordering.   
            }
        });
    }

    private FastIntObjectMap[] newSubMaps(int capacity) {
        FastIntObjectMap[] subMaps = new FastIntObjectMap[C2];
        for (int i = 0; i < C2; i++) {
            FastIntObjectMap subMap = new FastIntObjectMap(new Entry[capacity]);
            subMap._keyShift = B2 + _keyShift;
            subMaps[i] = subMap;
        }
        return subMaps;
    }

    // Adds the specified entry to this map table.
    private void mapEntry(Entry entry) {
        final int mask = _entries.length - 1;
        for (int i = entry._keyHash >> _keyShift;; i++) {
            Entry e = _entries[i & mask];
            if (e == null) {
                _entries[i & mask] = entry;
                break;
            }
        }
        _entryCount++;
    }

    // The destination table must be empty.
    private void copyEntries(Object[] from, Entry[] to, int count) {
        final int mask = to.length - 1;
        for (int i = 0; i < count;) {
            Entry entry = (Entry) from[i++];
            if ((entry == null) || (entry == Entry.NULL)) {
                continue;
            }
            for (int j = entry._keyHash >> _keyShift;; j++) {
                Entry tmp = to[j & mask];
                if (tmp == null) {
                    to[j & mask] = entry;
                    break;
                }
            }
        }
    }

    /**
     * Associates the specified value with the specified key in this map and 
     * returns the corresponding entry.
     * 
     * @param key the key with which the specified value is to be associated.
     * @param value the value to be associated with the specified key.
     * @return the entry being added.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public final Entry <V>  putEntry( int  key,  V  value) {
        return (Entry <V> ) put(key, value, key, _isShared, false,
                true);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * 
     * @param map the mappings to be stored in this map.
     * @throws NullPointerException the specified map is <code>null</code>,
     *         or the specified map contains <code>null</code> keys.
     */
    public final void putAll(IntObjectMap <? extends V>  map) {
        for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
            IntObjectMap.Entry <V>  e = (IntObjectMap.Entry <V> ) i.next();
            put(e.getKey(), e.getValue());
        }
    }
    public final void putAll(TIntObjectMap <? extends V>  map) {
        map.forEachEntry(new TIntObjectProcedure<V>(){
            @Override
            public boolean execute(int key, V value) {
                FastIntObjectMap.this.put(key, value);
                return true;
            }
        });
    }
    public final void putAll(java.util.Map <Integer, ? extends V>  map) {
        for (Iterator i = map.entrySet().iterator(); i.hasNext();) {
            java.util.Map.Entry <Integer,V>  e = (java.util.Map.Entry <Integer,V> ) i.next();
            put(e.getKey().intValue(), e.getValue());
        }
    }

    /**
     * Associates the specified value only if the specified key is not already
     * associated. This is equivalent to:[code]
     *   if (!map.containsKey(key))
     *       return map.put(key, value);
     *   else
     *       return map.get(key);[/code]
     * except that for shared maps the action is performed atomically.
     * For shared maps, this method guarantees that if two threads try to 
     * put the same key concurrently only one of them will succeed.
     *
     * @param key the key with which the specified value is to be associated.
     * @param value the value to be associated with the specified key.
     * @return the previous value associated with specified key, or
     *         <code>null</code> if there was no mapping for key. A
     *         <code>null</code> return can also indicate that the map
     *         previously associated <code>null</code> with the specified key.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public final  V  putIfAbsent( int  key,
             V  value) {
        return ( V ) put(key, value, key, _isShared, true,
                false);
    }

    /**
     * Removes the entry for the specified key if present. The entry 
     * is recycled if the map is not marked as {@link #isShared shared};
     * otherwise the entry is candidate for garbage collection.
     * 
     * <p> Note: Shared maps in ImmortalMemory (e.g. static) should not remove
     *           their entries as it could cause a memory leak (ImmortalMemory
     *           is never garbage collected), instead they should set their 
     *           entry values to <code>null</code>.</p> 
     * 
     * @param key the key whose mapping is to be removed from the map.
     * @return previous value associated with specified key, or
     *         <code>null</code> if there was no mapping for key. A
     *         <code>null</code> return can also indicate that the map
     *         previously associated <code>null</code> with the specified key.
     * @throws NullPointerException if the key is <code>null</code>.
     */
    public final  V  remove(int key) {
        return ( V ) remove(key, key, _isShared);
    }

    private final Object remove(int key, int keyHash, boolean concurrent) {
        final FastIntObjectMap map = getSubMap(keyHash);
        final Entry[] entries = map._entries; // Atomic.
        final int mask = entries.length - 1;
        for (int i = keyHash >> map._keyShift;; i++) {
            Entry entry = entries[i & mask];
            if (entry == null) {
                return null;
            } // No entry.
            if (key == entry._key) {
                // Found the entry.
                if (concurrent) {
if (REENTRANT_LOCK) {{
                    _shared.lock();
                    try {
                    	return remove(key, keyHash, false);
                    }
                    finally { _shared.unlock(); }
}} else {{
                    synchronized (this) {
                        return remove(key, keyHash, false);
                    }
}}
                }

                // Detaches entry from list.
                entry._previous._next = entry._next;
                entry._next._previous = entry._previous;

                // Removes from table.
                entries[i & mask] = Entry.NULL;
                map._nullCount++;
                map._entryCount--;

                Object prevValue = entry._value;
                if (!_isShared) { // Clears key/value and recycle.
                    entry._key = KEY_NULL;
                    entry._value = null;

                    final Entry next = _tail._next;
                    entry._previous = _tail;
                    entry._next = next;
                    _tail._next = entry;
                    if (next != null) {
                        next._previous = entry;
                    }
                } else {
                    // do nothing, preserving the iterator-free iterations of other threads
                }                       
                return prevValue;
            }
        }
    }

    /**
     * <p> Sets the shared status of this map (whether the map is thread-safe 
     *     or not). Shared maps are typically used for lookup table (e.g. static 
     *     instances in ImmortalMemory). They support concurrent access 
     *     (e.g. iterations) without synchronization, the maps updates 
     *     themselves are synchronized internally.</p>
     * <p> Unlike <code>ConcurrentHashMap</code> access to a shared map never 
     *     blocks. Retrieval reflects the map state not older than the last 
     *     time the accessing thread has been synchronized (for multi-processors
     *     systems synchronizing ensures that the CPU internal cache is not 
     *     stale).</p>
     * 
     * @return <code>this</code>
     */
    public FastIntObjectMap <V>  shared() {
        if (REENTRANT_LOCK) if (_shared == null) _shared = new ReentrantLock();
        _isShared = true;
        return this;
    }

//    /**
//     * @deprecated Replaced by {@link #shared}
//     */
//    public FastIntObjectMap <V>  setShared(boolean isShared) {
//        if (REENTRANT_LOCK) if (isShared) if (_shared == null) _shared = new ReentrantLock();
//        _isShared = isShared;
//        return this;
//    }

    /**
     * Indicates if this map supports concurrent operations without 
     * synchronization (default unshared).
     * 
     * @return <code>true</code> if this map is thread-safe; <code>false</code> 
     *         otherwise.
     */
    public boolean isShared() {
        return _isShared;
    }

    /**
     * <pre>For example:
     * map.<b>lock()</b>;
     * try {
     *     if (!map.containsKey(9999))
     *         map.put(9999, "****");
     * }
     * finally { map.<b>unlock();</b> }</pre>
     */
    public void lock()
    {
if (REENTRANT_LOCK) {{
        _shared.lock();
}} else {{
        throw new UnsupportedOperationException();
}}
    }
    /**
     * {@link #lock()}
     */
    public void unlock()
    {
if (REENTRANT_LOCK) {{
        _shared.unlock();
}} else {{
        throw new UnsupportedOperationException();
}}
    }

//    /**
//     * Sets the key comparator for this fast map.
//     * 
//     * @param keyComparator the key comparator.
//     * @return <code>this</code>
//     */
//    public FastMap <K,V>  setKeyComparator(
//            FastComparator <? super K>  keyComparator) {
//        _keyComparator = keyComparator;
//        _isDirectKeyComparator = (keyComparator == FastComparator.DIRECT) || ((_keyComparator == FastComparator.DEFAULT) && !((Boolean) FastComparator.REHASH_SYSTEM_HASHCODE.get()).booleanValue());
//        return this;
//    }
//
//    /**
//     * Returns the key comparator for this fast map.
//     * 
//     * @return the key comparator.
//     */
//    public FastComparator <? super K>  getKeyComparator() {
//        return _keyComparator;
//    }

    /**
     * Sets the value comparator for this map.
     * 
     * @param valueComparator the value comparator.
     * @return <code>this</code>
     */
    public FastIntObjectMap <V>  setValueComparator(
            FastComparator <? super V>  valueComparator) {
        _valueComparator = valueComparator;
        return this;
    }

    /**
     * Returns the value comparator for this fast map.
     * 
     * @return the value comparator.
     */
    public FastComparator <? super V>  getValueComparator() {
        return _valueComparator;
    }

    /**
     * Removes all map's entries. The entries are removed and recycled; 
     * unless this map is {@link #isShared shared} in which case the entries 
     * are candidate for garbage collection.
     * 
     * <p> Note: Shared maps in ImmortalMemory (e.g. static) should not remove
     *           their entries as it could cause a memory leak (ImmortalMemory
     *           is never garbage collected), instead they should set their 
     *           entry values to <code>null</code>.</p> 
     */
    public final void clear() {
        if (_isShared) {
            clearShared();
            return;
        }
        // Clears keys, values and recycle entries.
        for (Entry e = _head,  end = _tail; (e = e._next) != end;) {
            e._key = KEY_NULL;
            e._value = null;
        }
        _tail = _head._next; // Reuse linked list of entries.       
        clearTables();
    }

    private void clearTables() {
        if (_useSubMaps) {
            for (int i = 0; i < C2;) {
                _subMaps[i++].clearTables();
            }
            _useSubMaps = false;
        }
        FastIntObjectMap.reset(_entries);
        _nullCount = 0;
        _entryCount = 0;
    }

    private synchronized void clearShared() {
        // We do not modify the linked list of entries (e.g. key, values) 
        // Concurrent iterations can still proceed unaffected.
        // The linked list fragment is detached from the map and will be 
        // garbage collected once all concurrent iterations are completed.
        _head._next = _tail;
        _tail._previous = _head;

        // We also detach the main entry table and sub-maps.
        MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {

            public void run() {
                _entries = new Entry[C0];
                if (_useSubMaps) {
                    _useSubMaps = false;
                    _subMaps = newSubMaps(C0);
                }
                _entryCount = 0;
                _nullCount = 0;
            }
        });
    }

    /**
     * Compares the specified object with this map for equality.
     * Returns <code>true</code> if the given object is also a map and the two
     * maps represent the same mappings (regardless of collection iteration
     * order).
     * 
     * @param obj the object to be compared for equality with this map.
     * @return <code>true</code> if the specified object is equal to this map;
     *         <code>false</code> otherwise.
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof IntObjectMap) {
            IntObjectMap <?>  that = (IntObjectMap) obj;
            return this.entrySet().equals(that.entrySet());
        } else {
            return false;
        }
    }

    /**
     * Returns the hash code value for this map.
     * 
     * @return the hash code value for this map.
     */
    public int hashCode() {
        int code = 0;
        for (Entry e = _head,  end = _tail; (e = e._next) != end;) {
            code += e.hashCode();
        }
        return code;
    }

    /**
     * Returns the textual representation of this map.
     * 
     * @return the textual representation of the entry set.
     */
    public Text toText() {
        return Text.valueOf(entrySet());
    }

    /**
     * Returns the <code>String</code> representation of this 
     * {@link FastIntObjectMap}.
     *
     * @return <code>toText().toString()</code>
     */
    public final String toString() {
        return toText().toString();
    }

    /**
     * Returns a new entry for this map; this method can be overriden by 
     * custom map implementations. 
     *
     * @return a new entry.
     */
    protected Entry <V>  newEntry() {
        return new Entry();
    }

    /**
     * Prints the current statistics on this map.
     * This method may help identify poorly defined hash functions.
     * The average distance should be less than 20% (most of the entries 
     * are in their slots or close). 
     *  
     * @param out the stream to use for output (e.g. <code>System.out</code>)
     */
    public void printStatistics(PrintStream out) {
        long sum = this.getSumDistance();
        int size = this.size();
        int avgDistancePercent = size != 0 ? (int) (100 * sum / size) : 0;
        synchronized (out) {
            out.print("SIZE: " + size);
            out.print(", ENTRIES: " + getCapacity());
            out.print(", SLOTS: " + getTableLength());
            out.print(", USE SUB-MAPS: " + _useSubMaps);
            out.print(", SUB-MAPS DEPTH: " + getSubMapDepth());
            out.print(", NULL COUNT: " + _nullCount);
            out.print(", IS SHARED: " + _isShared);
            out.print(", AVG DISTANCE: " + avgDistancePercent + "%");
            out.print(", MAX DISTANCE: " + getMaximumDistance());
            out.println();
        }
    }

    private int getTableLength() {
        if (_useSubMaps) {
            int sum = 0;
            for (int i = 0; i < C2; i++) {
                sum += _subMaps[i].getTableLength();
            }
            return sum;
        } else {
            return _entries.length;
        }
    }

    private int getCapacity() {
        int capacity = 0;
        for (Entry e = _head; (e = e._next) != null;) {
            capacity++;
        }
        return capacity - 1;
    }

    private int getMaximumDistance() {
        int max = 0;
        if (_useSubMaps) {
            for (int i = 0; i < C2; i++) {
                int subMapMax = _subMaps[i].getMaximumDistance();
                max = MathLib.max(max, subMapMax);
            }
            return max;
        }
        for (int i = 0; i < _entries.length; i++) {
            Entry entry = _entries[i];
            if ((entry != null) && (entry != Entry.NULL)) {
                int slot = (entry._keyHash >> _keyShift) & (_entries.length - 1);
                int distanceToSlot = i - slot;
                if (distanceToSlot < 0) {
                    distanceToSlot += _entries.length;
                }
                if (distanceToSlot > max) {
                    max = distanceToSlot;
                }
            }
        }
        return max;
    }

    private long getSumDistance() {
        long sum = 0;
        if (_useSubMaps) {
            for (int i = 0; i < C2; i++) {
                sum += _subMaps[i].getSumDistance();
            }
            return sum;
        }
        for (int i = 0; i < _entries.length; i++) {
            Entry entry = _entries[i];
            if ((entry != null) && (entry != Entry.NULL)) {
                int slot = (entry._keyHash >> _keyShift) & (_entries.length - 1);
                int distanceToSlot = i - slot;
                if (distanceToSlot < 0) {
                    distanceToSlot += _entries.length;
                }
                sum += distanceToSlot;
            }
        }
        return sum;
    }

    private int getSubMapDepth() {
        if (_useSubMaps) {
            int depth = 0;
            for (int i = 0; i < C2; i++) {
                int subMapDepth = _subMaps[i].getSubMapDepth();
                depth = MathLib.max(depth, subMapDepth);
            }
            return depth + 1;
        } else {
            return 0;
        }
    }

    /**
     * x - for (Integer key : map.keySet()) { ... }<BR>
     * o - for (int key : map.keys()) { ... }<BR>
     * o - for (IntObjectMap.Entry<Object> e : map.entrySet()) { int key = e.getKey(); }
     */
    public final int[] keys() {
        int[] array = new int[FastIntObjectMap.this.size()];
        int length = 0;
        for (Entry <V>  e = _head,  end = _tail; (e = e._next) != end;) {
            if (length >= array.length)
            {
                // ensureCapacity
                int[] temp = new int[length + 1];
                System.arraycopy(array, 0, temp, 0, length);
                array = temp;
            }
            array[length++] = e._key;
        }
        if (length < array.length) {
            // trimToSize
            int[] temp = new int[length];
            System.arraycopy(array, 0, temp, 0, length);
            array = temp;
        }
        return array;
    }

    /**
     * <b>Deprecated</b><br>
     * trove like.
     */
    public boolean forEachKey(IntProcedure procedure) {
        for (Entry <V>  e = _head,  end = _tail; (e = e._next) != end;)
            if (!procedure.execute(e._key))
                return false;
        return true;
    }
    /**
     * <b>Deprecated</b><br>
     * trove like.
     */
    public boolean forEachValue(ObjectProcedure<? super V> procedure) {
        for (Entry <V>  e = _head,  end = _tail; (e = e._next) != end;)
            if (!procedure.execute(e._value))
                return false;
        return true;
    }
    /**
     * <b>Deprecated</b><br>
     * trove like.
     */
    public boolean forEachEntry(IntObjectProcedure<? super V> procedure) {
        for (Entry <V>  e = _head,  end = _tail; (e = e._next) != end;)
            if (!procedure.execute(e._key, e._value))
                return false;
        return true;
    }

    /**
     * Returns a {@link FastCollection} view of the values contained in this
     * map. The collection is backed by the map, so changes to the
     * map are reflected in the collection, and vice-versa. The collection 
     * supports element removal, which removes the corresponding mapping from
     * this map, via the <code>Iterator.remove</code>, 
     * <code>Collection.remove</code>, <code>removeAll</code>,
     * <code>retainAll</code> and <code>clear</code> operations. 
     * It does not support the <code>add</code> or <code>addAll</code> 
     * operations.
     * 
     * @return a collection view of the values contained in this map 
     *         (instance of {@link FastCollection}).
     */
    public final Collection <V>  values() {
        if (_values == null) {
            MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {

                public void run() {
                    _values = new Values();
                }
            });
        }
        return _values;
    }

    private final class Values extends FastCollection {

        public int size() {
            return FastIntObjectMap.this.size();
        }

        public void clear() {
            FastIntObjectMap.this.clear();
        }

        public Record head() {
            return FastIntObjectMap.this._head;
        }

        public Record tail() {
            return FastIntObjectMap.this._tail;
        }

        public Object valueOf(Record record) {
            return ((Entry) record)._value;
        }

        public void delete(Record record) {
            FastIntObjectMap.this.remove(((Entry) record).getKey());
        }

        public FastComparator getValueComparator() {
            return _valueComparator;
        }

        public Iterator iterator() {
            return ValueIterator.valueOf(FastIntObjectMap.this);
        }
    }

    // Value iterator optimization.
    private static final class ValueIterator implements Iterator {

        private static final ObjectFactory FACTORY = new ObjectFactory() {

            protected Object create() {
                return new ValueIterator();
            }

            protected void cleanup(Object obj) {
                ValueIterator iterator = (ValueIterator) obj;
                iterator._map = null;
                iterator._current = null;
                iterator._next = null;
                iterator._tail = null;
            }
        };
        private FastIntObjectMap _map;
        private Entry _current;
        private Entry _next;
        private Entry _tail;

        public static ValueIterator valueOf(FastIntObjectMap map) {
            ValueIterator iterator = (ValueIterator) ValueIterator.FACTORY.object();
            iterator._map = map;
            iterator._next = map._head._next;
            iterator._tail = map._tail;
            return iterator;
        }

        private ValueIterator() {
        }

        public boolean hasNext() {
            return (_next != _tail);
        }

        public Object next() {
            if (_next == _tail) {
                throw new NoSuchElementException();
            }
            _current = _next;
            _next = _next._next;
            return _current._value;
        }

        public void remove() {
            if (_current != null) {
                _next = _current._next;
                _map.remove(_current._key);
                _current = null;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    /**
     * Returns a {@link FastCollection} view of the mappings contained in this
     * map. Each element in the returned collection is a 
     * <code>IntObjectMap.Entry</code>. The collection is backed by the map, so
     * changes to the map are reflected in the collection, and vice-versa. The
     * collection supports element removal, which removes the corresponding
     * mapping from this map, via the <code>Iterator.remove</code>,
     * <code>Collection.remove</code>,<code>removeAll</code>,
     * <code>retainAll</code>, and <code>clear</code> operations. It does
     * not support the <code>add</code> or <code>addAll</code> operations.
     * 
     * @return a collection view of the mappings contained in this map
     *         (instance of {@link FastCollection}).
     */
    public final Set <IntObjectMap.Entry<V>>  entrySet() {
        if (_entrySet == null) {
            MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {

                public void run() {
                    _entrySet = new EntrySet();
                }
            });
        }
        return _entrySet;
    }

    private final class EntrySet extends FastCollection implements Set {

        public int size() {
            return FastIntObjectMap.this.size();
        }

        public void clear() {
            FastIntObjectMap.this.clear();
        }

        public boolean contains(Object obj) { // Optimization.
            if (obj instanceof IntObjectMap.Entry) {
                IntObjectMap.Entry thatEntry = (IntObjectMap.Entry) obj;
                Entry thisEntry = getEntry(thatEntry.getKey());
                if (thisEntry == null) {
                    return false;
                }
                return _valueComparator.areEqual(thisEntry.getValue(),
                        thatEntry.getValue());
            } else {
                return false;
            }
        }

        public Record head() {
            return _head;
        }

        public Record tail() {
            return _tail;
        }

        public Object valueOf(Record record) {
            return (IntObjectMap.Entry) record;
        }

        public void delete(Record record) {
            FastIntObjectMap.this.remove(((Entry) record).getKey());
        }

        public FastComparator getValueComparator() {
            return _entryComparator;
        }
        private final FastComparator _entryComparator = new FastComparator() {

            public boolean areEqual(Object o1, Object o2) {
                if ((o1 instanceof IntObjectMap.Entry) && (o2 instanceof IntObjectMap.Entry)) {
                    IntObjectMap.Entry e1 = (IntObjectMap.Entry) o1;
                    IntObjectMap.Entry e2 = (IntObjectMap.Entry) o2;
                    return e1.getKey() == e2.getKey() && _valueComparator.areEqual(e1.getValue(), e2.getValue());
                }
                return (o1 == null) && (o2 == null);
            }

            public int compare(Object o1, Object o2) {
                if ((o1 instanceof IntObjectMap.Entry) && (o2 instanceof IntObjectMap.Entry)) {
                    IntObjectMap.Entry e1 = (IntObjectMap.Entry) o1;
                    IntObjectMap.Entry e2 = (IntObjectMap.Entry) o2;
                    return e1.getKey() - e2.getKey();
                }
                throw new UnsupportedOperationException("EntrySet#compare(" + o1 + "," + o2 + ")");	//TODO:
              /*return _keyComparator.compare(o1, o2);*/
            }

            public int hashCodeOf(Object obj) {
                IntObjectMap.Entry entry = (IntObjectMap.Entry) obj;
                return entry.getKeyHash() + _valueComparator.hashCodeOf(entry.getValue());
            }
        };

        public Iterator iterator() {
            return EntryIterator.valueOf(FastIntObjectMap.this);
        }
    }

    // Entry iterator optimization.
    private static final class EntryIterator implements Iterator {

        private static final ObjectFactory FACTORY = new ObjectFactory() {

            protected Object create() {
                return new EntryIterator();
            }

            protected void cleanup(Object obj) {
                EntryIterator iterator = (EntryIterator) obj;
                iterator._map = null;
                iterator._current = null;
                iterator._next = null;
                iterator._tail = null;
            }
        };
        private FastIntObjectMap _map;
        private Entry _current;
        private Entry _next;
        private Entry _tail;

        public static EntryIterator valueOf(FastIntObjectMap map) {
            EntryIterator iterator = (EntryIterator) EntryIterator.FACTORY.object();
            iterator._map = map;
            iterator._next = map._head._next;
            iterator._tail = map._tail;
            return iterator;
        }

        private EntryIterator() {
        }

        public boolean hasNext() {
            return (_next != _tail);
        }

        public Object next() {
            if (_next == _tail) {
                throw new NoSuchElementException();
            }
            _current = _next;
            _next = _next._next;
            return _current;
        }

        public void remove() {
            if (_current != null) {
                _next = _current._next;
                _map.remove(_current._key);
                _current = null;
            } else {
                throw new IllegalStateException();
            }
        }
    }

//    /**
//     * Returns a {@link FastCollection} view of the keys contained in this 
//     * map. The set is backed by the map, so changes to the map are reflected
//     * in the set, and vice-versa. The set supports element removal, which 
//     * removes the corresponding mapping from this map, via the 
//     * <code>Iterator.remove</code>,
//    <code>Collection.remove</code>,<code>removeAll<f/code>,
//     * <code>retainAll</code>, and <code>clear</code> operations. It does
//     * not support the <code>add</code> or <code>addAll</code> operations.
//     * 
//     * @return a set view of the keys contained in this map
//     *         (instance of {@link FastCollection}).
//     */
//    public final Set keySet() {
//        if (_keySet == null) {
//            MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {
//
//                public void run() {
//                    _keySet = new KeySet();
//                }
//            });
//        }
//        return _keySet;
//    }
//
//    private final class KeySet extends FastCollection implements Set {
//
//        public int size() {
//            return FastMap.this.size();
//        }
//
//        public void clear() {
//            FastMap.this.clear();
//        }
//
//        public boolean contains(Object obj) { // Optimization.
//            return FastMap.this.containsKey(obj);
//        }
//
//        public boolean remove(Object obj) { // Optimization.
//            return FastMap.this.remove(obj) != null;
//        }
//
//        public Record head() {
//            return FastMap.this._head;
//        }
//
//        public Record tail() {
//            return FastMap.this._tail;
//        }
//
//        public Object valueOf(Record record) {
//            return ((Entry) record)._key;
//        }
//
//        public void delete(Record record) {
//            FastMap.this.remove(((Entry) record).getKey());
//        }
//
//        public FastComparator getValueComparator() {
//            return _keyComparator;
//        }
//
//        public Iterator iterator() {
//            return KeyIterator.valueOf(FastMap.this);
//        }
//    }
//
//    // Entry iterator optimization.
//    private static final class KeyIterator implements Iterator {
//
//        private static final ObjectFactory FACTORY = new ObjectFactory() {
//
//            protected Object create() {
//                return new KeyIterator();
//            }
//
//            protected void cleanup(Object obj) {
//                KeyIterator iterator = (KeyIterator) obj;
//                iterator._map = null;
//                iterator._current = null;
//                iterator._next = null;
//                iterator._tail = null;
//            }
//        };
//        private FastMap _map;
//        private Entry _current;
//        private Entry _next;
//        private Entry _tail;
//
//        public static KeyIterator valueOf(FastMap map) {
//            KeyIterator iterator = (KeyIterator) KeyIterator.FACTORY.object();
//            iterator._map = map;
//            iterator._next = map._head._next;
//            iterator._tail = map._tail;
//            return iterator;
//        }
//
//        private KeyIterator() {
//        }
//
//        public boolean hasNext() {
//            return (_next != _tail);
//        }
//
//        public Object next() {
//            if (_next == _tail) {
//                throw new NoSuchElementException();
//            }
//            _current = _next;
//            _next = _next._next;
//            return _current._key;
//        }
//
//        public void remove() {
//            if (_current != null) {
//                _next = _current._next;
//                _map.remove(_current._key);
//                _current = null;
//            } else {
//                throw new IllegalStateException();
//            }
//        }
//    }

    /**
     * Returns the unmodifiable view associated to this map.
     * Attempts to modify the returned map or to directly access its  
     * (modifiable) map entries (e.g. <code>unmodifiable().entrySet()</code>)
     * result in an {@link UnsupportedOperationException} being thrown.
     * Unmodifiable {@link FastCollection} views of this map keys and values
     * are nonetheless obtainable (e.g. <code>unmodifiable().keySet(), 
     * <code>unmodifiable().values()</code>). 
     *  
     * @return an unmodifiable view of this map.
     */
    public final IntObjectMap <V>  unmodifiable() {
        if (_unmodifiable == null) {
            MemoryArea.getMemoryArea(this).executeInArea(new Runnable() {

                public void run() {
                    _unmodifiable = new Unmodifiable();
                }
            });
        }
        return _unmodifiable;
    }

    // Implements Reusable.
    public void reset() {
        _isShared = false; // A shared map can only be reset if no thread use it.
        clear(); // In which case, it is safe to recycle the entries.
        setValueComparator(FastComparator.DEFAULT);
    }

    /**
     * Requires special handling during de-serialization process.
     *
     * @param  stream the object input stream.
     * @throws IOException if an I/O error occurs.
     * @throws ClassNotFoundException if the class for the object de-serialized
     *         is not found.
     */
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        setValueComparator((FastComparator) stream.readObject());
        _isShared = stream.readBoolean();
        final int size = stream.readInt();
        setup(size);
        for (int i = 0; i < size; i++) {
             int  key = ( int ) stream.readObject();
             V  value = ( V ) stream.readObject();
            put(key, value);
        }
    }

    /**
     * Requires special handling during serialization process.
     *
     * @param  stream the object output stream.
     * @throws IOException if an I/O error occurs.
     */
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.writeObject(getValueComparator());
        stream.writeBoolean(_isShared);
        stream.writeInt(size());
        for (Entry e = _head,  end = _tail; (e = e._next) != end;) {
            stream.writeObject(e._key);
            stream.writeObject(e._value);
        }
    }

    /**
     * This class represents a {@link FastIntObjectMap} entry.
     * Custom {@link FastIntObjectMap} may use a derived implementation.
     * For example:[code]
     *    static class MyMap<K,V,X> extends IntObjectMap<V> {
     *        protected MyEntry newEntry() {
     *            return new MyEntry();
     *        }
     *        class MyEntry extends Entry<K,V> {
     *            X xxx; // Additional entry field (e.g. cross references).
     *        }        
     *    }[/code]
     */
    public static class Entry <V>  implements IntObjectMap.Entry <V> , Record, Realtime {

        /**
         * Holds NULL entries (to fill empty hole).
         */
        public static final Entry NULL = new Entry();
        /**
         * Holds the next node.
         */
        private Entry <V>  _next;
        /**
         * Holds the previous node.
         */
        private Entry <V>  _previous;
        /**
         * Holds the entry key.
         */
        private  int  _key;
        /**
         * Holds the entry value.
         */
        private  V  _value;
        /**
         * Holds the key hash code.
         */
        private int _keyHash;

        /**
         * Default constructor.
         */
        protected Entry() {
        }

        /**
         * Returns the entry after this one.
         * 
         * @return the next entry.
         */
        public final Entry<V> getNext() {
            return _next;
        }

        /**
         * Returns the entry before this one.
         * 
         * @return the previous entry.
         */
        public final Entry<V> getPrevious() {
            return _previous;
        }

        /**
         * Returns the key for this entry.
         * 
         * @return the entry key.
         */
        public final  int  getKey() {
            return _key;
        }

        public final int getKeyHash() {
            return _keyHash;
        }

        /**
         * Returns the value for this entry.
         * 
         * @return the entry value.
         */
        public final  V  getValue() {
            return _value;
        }

        /**
         * Sets the value for this entry.
         * 
         * @param value the new value.
         * @return the previous value.
         */
        public final  V  setValue( V  value) {
             V  old = _value;
            _value = value;
            return old;
        }

        /**
         * Indicates if this entry is considered equals to the specified entry
         * (using default value and key equality comparator to ensure symetry).
         * 
         * @param that the object to test for equality.
         * @return <code>true<code> if both entry have equal keys and values.
         *         <code>false<code> otherwise.
         */
        public boolean equals(Object that) {
            if (that instanceof IntObjectMap.Entry) {
                IntObjectMap.Entry entry = (IntObjectMap.Entry) that;
                return _key == entry.getKey() && FastComparator.DEFAULT.areEqual(_value, entry.getValue());
            } else {
                return false;
            }
        }

        /**
         * Returns the hash code for this entry.
         * 
         * @return this entry hash code.
         */
        public int hashCode() {
            return _key ^ ((_value != null) ? _value.hashCode() : 0);
            /*return ((_key != null) ? _key.hashCode() : 0) ^ ((_value != null) ? _value.hashCode() : 0);*/
        }

        // Implements abstract method.
        public Text toText() {
            // We use Text concatenation instead of TextBuilder here to avoid 
            // copying the text representation of the keys/values (unknown length)
            return Text.valueOf(_key).plus("=").plus(_value);
        }
    }

    /**
     * This class represents an read-only view over a {@link FastIntObjectMap}.
     */
    private final class Unmodifiable implements IntObjectMap, Serializable {

        public boolean equals(Object obj) {
            return FastIntObjectMap.this.equals(obj);
        }

        public int hashCode() {
            return FastIntObjectMap.this.hashCode();
        }

        public Text toText() {
            return FastIntObjectMap.this.toText();
        }

        public int size() {
            return FastIntObjectMap.this.size();
        }

        public boolean isEmpty() {
            return FastIntObjectMap.this.isEmpty();
        }

        public boolean containsKey(int key) {
            return FastIntObjectMap.this.containsKey(key);
        }

        public boolean containsValue(Object value) {
            return FastIntObjectMap.this.containsValue(value);
        }

        public Object get(int key) {
            return FastIntObjectMap.this.get(key);
        }

        public Object put(int key, Object value) {
            throw new UnsupportedOperationException("Unmodifiable map");
        }

        public Object remove(int key) {
            throw new UnsupportedOperationException("Unmodifiable map");
        }

        public void putAll(IntObjectMap map) {
            throw new UnsupportedOperationException("Unmodifiable map");
        }
        public void putAll(TIntObjectMap map) {
            throw new UnsupportedOperationException("Unmodifiable map");
        }
        public void putAll(java.util.Map map) {
            throw new UnsupportedOperationException("Unmodifiable map");
        }

        public void clear() {
            throw new UnsupportedOperationException("Unmodifiable map");
        }

        public int[] keys() {
            return FastIntObjectMap.this.keys();
        }

        public Collection values() {
            return ((Values) FastIntObjectMap.this.values()).unmodifiable();
        }

        public Set entrySet() {
            throw new UnsupportedOperationException(
                    "Direct view over unmodifiable map entries is not supported " + " (to prevent access to Entry.setValue(Object) method). " + "To iterate over unmodifiable map entries, applications may " + "use the keySet() and values() fast collection views " + "in conjonction.");
        }

        public boolean forEachKey(IntProcedure procedure) { throw new UnsupportedOperationException(); }
        public boolean forEachValue(ObjectProcedure procedure) { throw new UnsupportedOperationException(); }
        public boolean forEachEntry(IntObjectProcedure procedure) { throw new UnsupportedOperationException(); }
    }

    // Holds the map factory.
    private static final ObjectFactory FACTORY = new ObjectFactory() {

        public Object create() {
            return new FastIntObjectMap();
        }

    };

    // Reset the specified table.
    private static void reset(Object[] entries) {
        for (int i = 0; i < entries.length;) {
            int count = MathLib.min(entries.length - i, C1);
            System.arraycopy(NULL_ENTRIES, 0, entries, i, count);
            i += count;
        }
    }
    private static final Entry[] NULL_ENTRIES = new Entry[C1];
    static volatile int ONE_VOLATILE = 1; // To prevent reordering.
    private static final long serialVersionUID = 1L;
}
