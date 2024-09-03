package telran.util;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;

public class HashMapTest extends AbstractMapTest{

    @SuppressWarnings("unchecked")
    @Override
    <T> void runTest(T[] expected, T[] actual) {
        Arrays.sort(expected, (o1, o2) -> ((Comparable<T>) o1).compareTo(o2));
        Arrays.sort(actual, (o1, o2) -> ((Comparable<T>) o1).compareTo(o2));          
    }

    @Override
    @BeforeEach
    void setUp(){
        map = new HashMap<>();
        super.setUp();
    }

}
