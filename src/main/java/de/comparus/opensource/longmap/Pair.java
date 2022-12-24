package de.comparus.opensource.longmap;

import java.util.Objects;
/**
 * Private inner class that acts as a data structure to create a new key-value pair in the bucket
 */
public class Pair<V> {
    private long key;
    private V value;

    /**
     * Creates a new key-value pair with the given values
     *
     * @param key   The key used to enter this in the table
     * @param value The value for this key
     */
    public Pair(long key, V value){
        this.key = key;
        this.value = value;
    }
    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?> pair = (Pair<?>) o;
        return key == pair.key && value.equals(pair.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
