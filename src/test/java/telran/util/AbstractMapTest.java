package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

import telran.util.Map.Entry;

public abstract class AbstractMapTest {
    Map<Integer, Integer> map;
    Integer[] keys = {1,2,3};
    
    void setUp(){
        for(Integer key: keys) {
            map.put(key, key * 10);
        }
    }

    abstract <T> void runTest(T[] expected, T[] actual);

    @Test
    void testGet() {
        Integer[] expected = {10, 20, 30};
        Integer[] actual = {map.get(keys[0]), map.get(keys[1]), map.get(keys[2])};
        runTest(expected, actual);
        assertNull(map.get(4));
    }

    @Test
    void testPut() {
        assertEquals(10, map.put(1, 100));
        assertEquals(100, map.get(1));
        assertNull(map.put(4, 40));
        assertEquals(40, map.get(4));
    }

    @Test
    void testPutIfAbsent() {
        assertNull(map.putIfAbsent(5, 50));
        assertEquals(50, map.get(5));
        assertEquals(50, map.putIfAbsent(5, 60));
    }

    @Test
    void testContainsKey() {
        Boolean[] expected = {true, true, false};
        Boolean[] actual = {map.containsKey(keys[1]), map.containsKey(keys[2]), map.containsKey(4)};
        runTest(expected, actual);
    }

    @Test
    void testContainsValue() {
        Boolean[] expected = {true, true, false};
        Boolean[] actual = {map.containsValue(10), map.containsValue(20), map.containsValue(300)};
        runTest(expected, actual);
    }

    @Test
    void testKeySet() {
        Integer[] expected = {1, 2, 3};
        Set<Integer> myKeys = map.keySet();
        Integer[] actual = new Integer[myKeys.size()];
        actual = collectionToArray(myKeys, actual);
        runTest(expected, actual);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testEntrySet() {
        Entry<Integer, Integer>[] expected = new Entry[]{
            new Entry<>(1, 10),
            new Entry<>(2, 20),
            new Entry<>(3, 30)
        };
        Set<Entry<Integer, Integer>> entries = map.entrySet();
        Entry<Integer, Integer>[] actual = new Entry[entries.size()];
        actual = collectionToArray(entries, actual);
        runTest(expected, actual);
    }

    @Test
    void testValues() {
        Integer[] expected = {10, 20, 30};
        Collection<Integer> values = map.values();
        Integer[] actual = new Integer[values.size()];
        actual = collectionToArray(values, actual);
        runTest(expected, actual);
    }

    private <T> T[] collectionToArray(Collection<T> collection, T[] array) {
        int index = 0;
        for (T element : collection) {
            array[index++] = element;
        }
        return array;
    }
}
