package telran.util;

import java.util.Iterator;

public class ArrayList<T> implements List<T>{
    private static final int DEFAULT_CAPACITY = 16;
    Object[] array;
    private int size = 0;

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    public ArrayList(){
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void add(int index, T obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T get(T pattern) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int indexOf(T pattrn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int lastIndex(T pattern) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean add(T obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(T pattern) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(T pattern) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

}
