package telran.util;

public abstract class AbstractMap<K, V> implements Map<K, V>{
    protected Set<Entry<K, V>> set;
    abstract protected Set<K> getEmptyKeySet();

    @SuppressWarnings("unchecked")
    @Override
    public V get(Object key) {
        Entry<K, V> pattern = new Entry<>((K)key, null);
        Entry<K,V> entry = set.get(pattern);
		V res = null;
		if (entry != null) {
			res = entry.getValue();
		}
		return res;
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> pattern = new Entry<>(key, null);
		Entry<K, V> entry = set.get(pattern);
		V res = null;
		if (entry == null) {
			set.add(new Entry<>(key, value));
		} else {
			res = entry.getValue();
			entry.setValue(value);
		}
		return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsKey(Object key) {
        Set<K> keys = keySet();
        return keys.contains((K) key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean containsValue(Object value) {
        Collection<V> values = values();
        return values.contains((V) value);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = getEmptyKeySet();
        for (Entry<K, V> entry : set) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return set;
    }

    @Override
    public Collection<V> values() {
        Set<V> values = new HashSet<>();
        for (Entry<K, V> entry : set) {
            values.add(entry.getValue());
        }
        return values;
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }
}
