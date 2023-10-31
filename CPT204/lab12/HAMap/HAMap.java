import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  Hash-based Map
 */
public class HAMap<K, V> implements Iterable<K> {

    /**
     * Represents a key-value pair.
     */
    private class Entry {
        K key;
        V value;

        Entry(K k, V v) {
            key = k;
            value = v;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_LOAD_FACTOR = 1.5;

    private ArrayList<ArrayList<Entry>> buckets;
    private HashSet<K> keySet;
    private int numBuckets;
    private int numEntries;
    private final double loadFactor;

    /**
     * @return a set of the keys contained in this map.
     */
    public HashSet<K> keySet() {
        return keySet;
    }

    /**
     * @return the number of entries in this map.
     */
    public int size() {
        return numEntries;
    }

    /**
     * @return the number of buckets in this map.
     */
    public int getNumBuckets() {
        return numBuckets;
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */

    
	// INCLUDE your helper methods in EACH of your submissions that use them
    private int reduce(K key, int cap){
        return Math.floorMod(key.hashCode(), cap);
    }

    private void resize(){
        ArrayList<ArrayList<Entry>> newB = new ArrayList<>();
        int newCap = this.numBuckets * 2;
        for (int i = 0; i < newCap; i++) {
            newB.add(new ArrayList<>());
        }

        for(K key : this.keySet){
            int idx = reduce(key, newCap);
            V value = get(key);
            newB.get(idx).add(new Entry(key, value));
        }
        for (ArrayList<Entry> bucket: this.buckets)bucket.clear();
        this.buckets.clear();
        this.buckets = newB;
        this.numBuckets = newCap;
    }
    /*
     ***** HELPER METHODS END *****
     */


    // LAB EXERCISE 12.2 CONSTRUCTORS

    public HAMap(int initialCapacity, double loadFactor) {
		this.loadFactor = loadFactor;
        this.numBuckets = initialCapacity;
        this.buckets = new ArrayList<>();
        for (int i = 0; i < initialCapacity; i++) {
            this.buckets.add(new ArrayList<>());
        }
        this.numEntries = 0;
        this.keySet = new HashSet<>();
    }

    public HAMap() {
		this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HAMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }


    // LAB EXERCISE 12.3 CLEAR

    /**
     * Removes all of the entries from this map.
     */
    public void clear() {
		this.numEntries = 0;
        this.buckets = new ArrayList<>();
        this.keySet = new HashSet<>();
        for (int i = 0; i < this.numBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
    }


    // LAB EXERCISE 12.4 CONTAINS KEY and ITERATOR

    /**
     * @param key to be checked
     * @return true iff this map contains an entry with the specified key
     */
    public boolean containsKey(K key) {
		return this.keySet.contains(key);
    }

    /**
     * @return an Iterator that iterates over the stored keys
     */
    @Override
    public Iterator<K> iterator() {
		return this.keySet.iterator();
    }


    // CODING ASSIGNMENT 12.1 GET

    /**
     * @param key of the value to be returned
     * @return the value to which the specified key is mapped
     *         null if this map contains no entries of the key
     */
    public V get(K key) {
		if (!keySet.contains(key)) return null;
        int idx = reduce(key, numBuckets);
        V value = null;
        for (Entry e: buckets.get(idx)){
            if (e.key.equals(key)){
                value = e.value;
            }
        }
		return value;
    }


    // CODING ASSIGNMENT 12.2 PUT

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained an entry with that key, the old value is replaced.
     * The key is not null.
     * @param key of the entry to be added
     * @param value of the entry to be added
     */
    public void put(K key, V value) {
        double ratio = (double) numEntries / (double) numBuckets;
        if (ratio >= loadFactor)resize();
		int idx = reduce(key, numBuckets);
        if (containsKey(key)) {
            Entry e = new Entry(key, value);
            buckets.get(idx).set(0, e);
        } else {
            Entry e = new Entry(key, value);
            buckets.get(idx).add(e);
            keySet.add(key);
            numEntries++;
        }
    }
	
	
    // CODING ASSIGNMENT 12.3 REMOVE

    /**
     * Removes the entry for the specified key only if it is
     * currently mapped to the specified value.
     * @param key of the entry to be removed
     * @param value of the entry to be removed
     * @return the value if entry found,
     *         null otherwise
     */
    public V remove(K key, V value) {
        if (!containsKey(key)) return null;
        int idx = reduce(key, numBuckets);
        Entry e = null;
        for (Entry entry: this.buckets.get(idx)){
            if (entry.key.equals(key) && entry.value.equals(value))
                e = entry;
        }
        if (e != null) {
            this.buckets.get(idx).remove(e);
            this.numEntries--;
            this.keySet.remove(key);
            return e.value;
        }
        return null;
    }

}
