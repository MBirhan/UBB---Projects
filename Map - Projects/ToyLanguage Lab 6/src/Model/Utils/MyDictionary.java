package Model.Utils;

import java.util.*;
import Exceptions.UndefinedVariable;

public class MyDictionary<K, V> implements IMyDictionary<K, V>{

        private Map<K, V> elements = new HashMap<>();

        @Override
        public void setValue(K key, V value) {
            elements.put(key, value);
        }

        @Override
        public void delete(K key) {
            elements.remove(key);
        }

        @Override
        public void updateValue(K key, V value) {
            elements.put(key, value);
        }

        @Override
        public V getValue(K key) {
            if(!exists(key))
                throw new UndefinedVariable("Variable not defined in dictionary!");
            return elements.get(key);
        }

        @Override
        public List<V> getAllValues() {
            List<V> list = new ArrayList<>();

            for(K key : this.getElements()){
                list.add(this.getValue(key));
            }

            return list;
        }

        @Override
        public boolean exists(K key) {
            return elements.containsKey(key);
        }

        @Override
        public Iterable<K> getElements() {
            return elements.keySet();
        }

        @Override
        public IMyDictionary<K, V> copy() {
            IMyDictionary<K, V> table = new MyDictionary<>();
            for(Map.Entry<K, V> e :  elements.entrySet()){
                table.setValue(e.getKey(), e.getValue());
            }
            return table;
        }

        @Override
        public String toString(){
            StringBuilder bld = new StringBuilder();

            for(Map.Entry<K, V> e : elements.entrySet()){
                bld.append("<").append(e.getKey()).append(", ").append(e.getValue()).append(">; ");
            }
            return bld.toString();
        }

    }
