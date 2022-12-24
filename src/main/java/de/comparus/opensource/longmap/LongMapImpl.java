package de.comparus.opensource.longmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongMapImpl<V> implements LongMap<V> {

    /**
     * param@ bucket - the hash table data
     * param@ count - the total number of entries in the hash table
     * param@ initialCapacity - the number of buckets in the hash table
     * param@ threshold - when size of hash table exceed threshold value
     * param@ loadFactor -  is a measure of how full the hash table is allowed
     * to get before its capacity is automatically increased
     */
    private List<List<Pair<V>>> bucket;
    protected int count;
    static final int initialCapacity = 16;
    private final float loadFactor = 0.7f;
    protected int threshold;
    public LongMapImpl(int initialCapacity){

        bucket = new ArrayList<List<Pair<V>>>(initialCapacity);
        for(int i=0; i< initialCapacity; i++)
            bucket.add(i, new ArrayList<Pair<V>>());
    }

    /**
     * Puts the key and the value in to the exact bucket number
     *
     *@param key to locate value
     *@param value the value to be stored
     *@return the prior value of the specified key in hash table
     */

    public V put(long key, V value) {
        int index = hash(key);
        List<Pair<V>> list = bucket.get(index);

            for (Pair<V> p : list) {
                if (p.getKey() == key) {
                    p.setValue(value);
                    return p.getValue();
                }
            }
        list.add(new Pair<V>(key, value));
        count++;
        if(count >= (initialCapacity * loadFactor)){
            expandCapacity();
        }
        return null;
    }
    private void expandCapacity(){
        int newCapacity = bucket.size() * 2;
        threshold = (int) (initialCapacity * loadFactor);
        List<List<Pair<V>>> newBucket = new ArrayList<List<Pair<V>>>();
          for(int i=0; i< newCapacity; i++) {
              newBucket.add(i, new ArrayList<Pair<V>>());
          }
          newBucket.addAll(bucket);
          bucket = newBucket;
    }
    final int hash(long key){
        Long k = new Long(key);
        return key == 0 ? 0 : Math.abs(k.hashCode() % bucket.size());
    }

    /**
     *Search the bucket index by key and search in all Entries for key given
     *
     * @param key locate the value
     * @return value for specify key
     */

    public V get(long key) {
        int index = hash(key);
        List<Pair<V>> list = bucket.get(index);
        if(list == null) return null;
        for(Pair<V> p : list) {
            if (p.getKey() == key) {
                return p.getValue();
            }
        }
        return null;
    }
    /**
     * Search the bucket index by key and remove the key-value pair from bucket's
     * list, if present
     *
     * @param key to be removed
     * @return value for removed key
     */

    public V remove(long key) {
        int index = hash(key);
        List<Pair<V>> list = bucket.get(index);
        for(Pair<V> p : list) {
            for(int i = 0; i < list.size(); i++) {
                if (p.getKey() == key) {
                    list.remove(i);
                    p.setKey(0);
                    count--;
                    return p.getValue();
                }
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean containsKey(long key) {
        int index = Long.hashCode(key) % initialCapacity;
        List<Pair<V>> list = bucket.get(index);
        for(Pair<V> p : list) {
            if (p.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        List<List<Pair<V>>> list = bucket;
        for (List<Pair<V>> l : list) {
            for (Pair<V> p : l) {
                if(p.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public long[] keys() {
        long[] keys = new long[bucket.size()];
        List<List<Pair<V>>> list = bucket;
        for (List<Pair<V>> l : list) {
            if(l == null) continue;
            keys = l.stream().mapToLong(Pair::getKey).toArray();
            Arrays.stream(keys).forEach(e->System.out.print(e + " "));
        }
        return keys;
    }

    public V[] values() {
        V[] values = null;
        List<List<Pair<V>>> list = bucket;
        for (List<Pair<V>> l : list) {
            if(l == null) continue;
            values = (V[]) l.stream().map(pair -> pair.getValue()).toArray();
            Arrays.stream(values).forEach(e->System.out.print(e + " "));
        }
        return values;
    }

    public long size() {
        return count;
    }

    public void clear() {
        if(size() == 0) return;
        List<List<Pair<V>>> list = bucket;
        for (List<Pair<V>> l : list) {
            for (Pair<V> p : l) {
                p.setKey(0);
                p.setValue(null);
            }
            count = 0;
        }
    }

}
