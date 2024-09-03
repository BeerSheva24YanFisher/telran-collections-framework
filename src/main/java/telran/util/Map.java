package telran.util;

import java.util.Objects;

public interface Map<K, V> {
    public static class Entry<K, V> implements Comparable<Entry<K,V>> {
        private final K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public V getValue() {
            return value;
        }
        public K getKey() {
            return key;
        }
        public void setValue(V value) {
            this.value = value;
        }
     
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> other) {
           return ((Comparable<K>)key).compareTo(other.getKey());
        }
        @Override
        public int hashCode(){
            return key.hashCode();
        }
        
        
        //Our method didn't work. This method is work
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Entry<?, ?> entry = (Entry<?, ?>) obj;
            return Objects.equals(key, entry.key) && Objects.equals(value, entry.value);
        }

    }

    V get(Object key);

    default V getOrDefault(Object key, V defaultValue) {
        V res = get(key);
        if (res == null) {
            res = defaultValue;
        }
        return res;
    }

    V put(K key, V value);
    
    default V putIfAbsent(K key, V value) {
        V result = get(key);
        if (result == null) {
            put(key, value);
        }
        return result;
    }
    boolean containsKey(Object key);
    boolean containsValue(Object value);   
    Set<K> keySet();
    Set<Entry<K, V>> entrySet();
    Collection<V> values();
    int size();
    boolean isEmpty();
    
}