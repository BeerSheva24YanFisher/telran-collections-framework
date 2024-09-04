package telran.util;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends AbstractMapTest{

    @SuppressWarnings("unchecked")
    @Override
    <T> void runTest(T[] expected, T[] actual) {
        Arrays.sort(expected, (o1, o2) -> ((Comparable<T>) o1).compareTo(o2));
        //Don't work without sorting actual???
        Arrays.sort(actual, (o1, o2) -> ((Comparable<T>) o1).compareTo(o2));    
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }  
    }

    @Override
    @BeforeEach
    void setUp(){
        map = new TreeMap<>();
        super.setUp();
    }

}
