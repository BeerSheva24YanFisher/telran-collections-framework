package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        checkSize(index);
        if (size == array.length) {
            reallocate();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = obj;
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T remove(int index) {
        checkSize(index);
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size-index-1);
        array[--size] = null;
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkSize(index);
        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        int index = 0;
        while (index<size && !pattern.equals(array[index])) {
            index++;
        }
        return index>=size ? -1:index;
    }

    @Override
    public int lastIndex(T pattern) {
        int index = array.length-1;
        while (index>=0 && !pattern.equals(array[index])) {
            index--;
        }
        return index;
        
    }

    @Override
    public boolean add(T obj) {
        if (size == array.length) {
            reallocate();
        }
        array[size++]=  obj;
        return true;
    }

    @Override
    public boolean remove(T pattern) {
        int index = indexOf(pattern);
        if (index>=0) {
            remove(index);      
        }
        return index>=0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(T pattern) {
        return indexOf(pattern) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private void reallocate() {
        array = Arrays.copyOf(array, array.length*2);
    }

    private void checkSize(int index){
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private class ArrayListIterator implements Iterator<T> {
        private int current = 0;
    
        @Override
        public boolean hasNext() {
            return current < size;
        }
    
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T)array[current++];
        }
    }
}


