package org.example.javabot.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUSet<K> extends LinkedHashMap<K, Boolean> {
    private final int capacity;

    public LRUSet(int capacity) {
        super(capacity + 1, 1.1f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, Boolean> eldest) {
        return size() > capacity;
    }

    public synchronized boolean add(K key) {
        return super.put(key, Boolean.TRUE) == null;
    }

    public synchronized boolean contains(K key) {
        return super.containsKey(key);
    }
}
