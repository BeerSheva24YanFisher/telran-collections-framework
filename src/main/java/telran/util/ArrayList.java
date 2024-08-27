package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;

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
        checkSize(index, true);
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
        checkSize(index, false);
        T removedElement = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size-index-1);
        array[--size] = null;
        return removedElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        checkSize(index, false);
        return (T) array[index];
    }

    @Override
    public int indexOf(T pattern) {
        int index = 0;
        while (index<size && !Objects.equals(pattern, array[index])) {
            index++;
        }
        return index>=size ? -1:index;
    }

    @Override
    public int lastIndexOf(T pattern) {
        int index = array.length-1;
        while (index>=0 && !Objects.equals(pattern, array[index])) {
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

    private void checkSize(int index, boolean sizeInclusive){
        int limit = sizeInclusive ? size : size - 1;
        if (index < 0 || index > limit) {
         throw new IndexOutOfBoundsException(index);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean removeIf(Predicate<T> predicate) {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (!predicate.test((T) array[i])) {
                array[j++] = array[i];
            }
        }
        Arrays.fill(array, j, size, null);
        boolean removed = size != j;
        size = j;
        return removed;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int current = 0;
        private boolean flNext = false;
    
        @Override
        public boolean hasNext() {
            flNext = true;
            return current < size;
        }
    
        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T)array[current++];
        }

        @Override
        public void remove() {
            if(!flNext) {
                throw new IllegalStateException();
            }
            ArrayList.this.remove(--current);
            flNext = false;
        }
    }
}


