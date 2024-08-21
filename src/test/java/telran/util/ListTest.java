package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

abstract public class ListTest extends CollectionTest{
    List<Integer> list;

    @Override
    void setUp(){
        super.setUp();
        list = (List<Integer>) collection;
    }

    @Test
    void addIndexTest() {
        list.add(2, 50);
        assertEquals(50, list.get(2));
        assertEquals(array.length + 1, collection.size());
    }

    @Test
    void removeIndexTest() {
        int removedElement = list.remove(1);
        assertEquals(-10, removedElement);
        assertEquals(array.length - 1, list.size());
    }

    @Test
    void getTest() {
        assertEquals((Integer) 1, list.get(3));
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

    @Test
    void removePatternTest() {
        assertTrue(list.remove((Integer) 20));
        assertEquals(array.length - 1, list.size());
        assertFalse(list.remove((Integer) 200));
    }

}
