package Model.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHeap<K,V> implements IMyHeap<K ,V> {

    Map<K, V> dict = new HashMap<>();


    @Override
    public void add(K key, V value) {
        dict.put(key,value);
    }

    @Override
    public boolean contains(K key) {
        return dict.containsKey(key);
    }

    @Override
    public void update(K key, V value) {
        dict.put(key,value);
    }

    @Override
    public void remove(K key) {
        dict.remove(key);
    }

    @Override
    public V get(K key) {
        return dict.get(key);
    }

    @Override
    public Iterable<K> getAll() {
        return dict.keySet();
    }

    @Override
    public void setContent(Map<K, V> map) {
        dict = map;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dict.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Map.Entry<K,V> e : dict.entrySet())
            str.append("<").append(e.getKey()).append(", ").append(e.getValue()).append(">; ");
       return str.toString();
    }
}
