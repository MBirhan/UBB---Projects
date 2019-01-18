package Model.FileHandeler;

import java.io.BufferedReader;
import java.util.Collection;
import java.util.List;
import Model.*;

public interface IFileTable<K, V> {

    void add(K key, V val);
    void delete(K key);
    V get(K key);
    boolean contains(K key);
    Iterable<K> getAllKeys();
    Iterable<V> getAllValues();

}