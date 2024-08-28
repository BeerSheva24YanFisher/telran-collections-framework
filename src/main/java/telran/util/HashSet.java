package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class HashSet<T> implements Set<T> {
    private static final int DEFAULT_HASH_TABLE_LENGTH = 16;
    private static final float DEFAULT_FACTOR = 0.75f;
    List<T>[] hashTable;
    float factor;
    int size;
    private class HashSetIterator implements Iterator<T> {
        private Iterator<T> currentIterator;
        private Iterator<T> prevIterator;
        private int indexIterator;
    
        public HashSetIterator() {
            indexIterator = 0;
            moveToNextNonEmpty();
        }
    
        @Override
        public boolean hasNext() {
            return currentIterator != null && currentIterator.hasNext();
        }
    
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
    
            prevIterator = currentIterator;
            T obj = currentIterator.next();
            if (!currentIterator.hasNext()) {
                moveToNextNonEmpty();
            }
            return obj;
        }
    
        @Override
        public void remove() {
            if (prevIterator == null) {
                throw new IllegalStateException();
            }
            prevIterator.remove();
            prevIterator = null;
            size--;
        }
    
        private void moveToNextNonEmpty() {
            while (indexIterator < hashTable.length && (hashTable[indexIterator] == null || !hashTable[indexIterator].iterator().hasNext())) {
                indexIterator++;
            }
            if (indexIterator < hashTable.length) {
                currentIterator = hashTable[indexIterator] != null ? hashTable[indexIterator].iterator() : null;
            } else {
                currentIterator = null;
            }
        }
    }

    public HashSet(int hashTableLength, float factor) {
        hashTable = new List[hashTableLength];
        this.factor = factor;
    }

    public HashSet() {
        this(DEFAULT_HASH_TABLE_LENGTH, DEFAULT_FACTOR);
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
        if (!contains(obj)) {
            res = true;
            if (size >= hashTable.length * factor) {
                hashTableReallocation();
            }

            addObjInHashTable(obj, hashTable);
            size++;
        }
        return res;

    }

    private void addObjInHashTable(T obj, List<T>[] table) {
        int index = getIndex(obj, table.length);
        List<T> list = table[index];
        if (list == null) {
            list = new ArrayList<>(3);
            table[index]=list;
        }
        list.add(obj);
    }

    private int getIndex(T obj, int length) {
        int hashCode = obj.hashCode();
        return Math.abs(hashCode % length);
    }

    private void hashTableReallocation() {
       List<T> []tempTable = new List[hashTable.length * 2];
       for(List<T> list: hashTable) {
        if(list != null) {
            list.forEach(obj -> addObjInHashTable(obj, tempTable));
            list.clear(); //??? for testing if it doesn't work remove this statement
        }
       }
       hashTable = tempTable;

    }

    @Override
    public boolean remove(T pattern) {
        boolean result = contains(pattern);
        if (result) {
            int index = getIndex(pattern, hashTable.length);
            hashTable[index].remove(pattern);
            size--;
        }

        return result;
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
        int index = getIndex(pattern, hashTable.length);
        List<T> list = hashTable[index];
        return list != null && list.contains(pattern);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashSetIterator();
    }

    @Override
    public T get(Object pattern) {
        int index = getIndex((T) pattern, hashTable.length);
        List<T> list = hashTable[index];
        T obj = null;
        if (list != null && pattern!=null) {
            Iterator<T> iterator = list.iterator();
            while (iterator.hasNext()&&!Objects.equals(obj, pattern)) {
                obj = iterator.next();
            }
        }
        return obj;
    }

}