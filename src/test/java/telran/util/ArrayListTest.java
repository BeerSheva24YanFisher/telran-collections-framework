package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayListTest extends ListTest{    
    @BeforeEach
    @Override
    void setUp(){
        collection = new ArrayList<>(3);
        super.setUp();
    }

    @Test
    void addIndexTest() {
        list.add(2, 50);
        assertEquals(50, list.get(50));
        assertEquals(array.length + 1, list.size());
    }

    @Test
    void removeIndexTest() {
        int removedElement = list.remove(1);
        assertEquals(-10, removedElement);
        assertEquals(array.length - 1, list.size());
    }

    @Test
    void getTest() {
        assertEquals((Integer) 20, list.get(20));
        assertNull(list.get(200));
    }

    @Test
    void indexOfTest() {
        assertEquals(2, list.indexOf(20));
        assertEquals(-1, list.indexOf(200));
    }

    @Test
    void lastIndexTest() {
        list.add(10);
        assertEquals(8, list.lastIndex(10));
        assertEquals(-1, list.lastIndex(200));
    }

    @Override
    @Test
    void addTest() {
        assertTrue(list.add(200));
        assertTrue(list.add(17));
        assertEquals(array.length + 2, list.size());
    }

    @Test
    void removePatternTest() {
        assertTrue(list.remove((Integer) 20));
        assertEquals(array.length - 1, list.size());
        assertFalse(list.remove((Integer) 200));
    }

    @Override
    @Test
    void sizeTest() {
        assertEquals(array.length, list.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(list.isEmpty());
        assertTrue(!list.isEmpty());
    }

    @Test
    void containsTest() {
        assertTrue(list.contains(20));
        assertFalse(list.contains(300));
    }

    @Test
    void iteratorTest() {
        Iterator<Integer> it = list.iterator();
        int index = 0;
        while (it.hasNext()) {
            assertEquals(array[index++], it.next());
        }
    }

}
