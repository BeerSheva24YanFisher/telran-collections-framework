package telran.util;

abstract public class ListTest extends CollectionTest{
    List<Integer> list;

    @Override
    void setUp(){
        super.setUp();
        list = (List<Integer>) collection;
    }

}
