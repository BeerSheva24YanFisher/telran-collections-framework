package telran.util;

import java.util.Arrays;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = {3, -10, 20, 1, 10, 9, 100, 17};
    void setUp(){
        Arrays.stream(array).forEach(collection::add);
    }

    @Test
    void addTest(){
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        assertEquals(array.length+2, collection.size());
    }

    @Test
    void sizeTest(){
        assertEquals(array.length, collection.size());
    }

    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
        assertTrue(!collection.isEmpty());
    }

    @Test
    void containsTest() {
        assertTrue(collection.contains(20));
        assertFalse(collection.contains(300));
    }
    
    @Test
    void iteratorTest() {
        Iterator<Integer> it = collection.iterator();
        int index = 0;
        while (it.hasNext()) {
            assertEquals(array[index++], it.next());
        }
    }

}
