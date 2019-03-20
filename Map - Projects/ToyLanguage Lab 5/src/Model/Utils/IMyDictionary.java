package Model.Utils;

import java.util.List;

public interface IMyDictionary<K, V> {
    void setValue(K key, V value);
    void delete(K key);
    V getValue(K key);
    List<V> getAllValues();
    boolean exists(K key);
    Iterable<K> getElements();
    IMyDictionary<K, V> copy();
}
