package com.tarum.io.content;
import java.util.Map;
import java.util.HashMap;

// The class itself holds the generic type parameters
public class ContentMap<K, V> {
    private Map<K, V> internalMap = new HashMap<>();

    public void save(K key, V value) {
        internalMap.put(key, value);
    }

    public V find(K key) {
        return internalMap.get(key);
    }



}