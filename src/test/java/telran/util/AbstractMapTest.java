package telran.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        Integer[] expected = {10,20,30};
        Integer[] actual = {map.get(keys[0]),map.get(keys[1]),map.get(keys[2])};
        runTest(expected, actual);
        assertArrayEquals(expected, actual);
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
        assertTrue(map.containsKey(keys[1]));
        assertTrue(map.containsKey(keys[2]));
        assertFalse(map.containsKey(4));
    }

    @Test
    void testContainsValue() {
        assertTrue(map.containsValue(10));
        assertTrue(map.containsValue(20));
        assertFalse(map.containsValue(300));
    }

    @Test
    void testKeySet() {
        Integer[] expected = {1,2,3};
        Set<Integer> myKeys = map.keySet();
        assertEquals(3, myKeys.size());
        assertTrue(myKeys.contains(expected[0]));
        assertTrue(myKeys.contains(expected[1]));
        assertTrue(myKeys.contains(expected[2]));
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
        assertEquals(3, entries.size());
        assertTrue(entries.contains(expected[0]));
        assertTrue(entries.contains(expected[1]));
        assertTrue(entries.contains(expected[2]));
    }

    @Test
    void testValues() {
        Integer[] expected = {10,20,30};
        Collection<Integer> values = map.values();
        assertEquals(3, values.size());
        assertTrue(values.contains(expected[0]));
        assertTrue(values.contains(expected[1]));
        assertTrue(values.contains(expected[2]));
    }
    
}
