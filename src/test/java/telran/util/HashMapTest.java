package telran.util;

import org.junit.jupiter.api.BeforeEach;

public class HashMapTest extends AbstractMapTest{

    @Override
    <T> void runTest(T[] expected) {}

    @Override
    @BeforeEach
    void setUp(){
        map = new HashMap<>();
        super.setUp();
    }

}
