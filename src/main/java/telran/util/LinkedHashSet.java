package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T>{
    private final LinkedList<T> list = new LinkedList<>();
    HashMap<T, Node<T>> map = new HashMap<>();

    private class LinkedHashSetIterator implements Iterator<T> {
		Iterator<T> it = list.iterator();
		T iterated;
		@Override
		public boolean hasNext() {
			return it.hasNext();
		}

		@Override
		public T next() {
			iterated = it.next();
			return iterated;
		}
		@Override
		public void remove() {
			it.remove();
			map.remove(iterated);
		}
		
	}

    @Override
    public T get(Object pattern) {
		Node<T> node = map.get(pattern);
		return node == null ? null : node.obj;
    }

    @Override
    public boolean add(T obj) {
        boolean res = false;
		if(!contains(obj)) {
			Node<T> node = new Node<>(obj);
			list.addNode(node, list.size());
            map.put(obj, node);
            res = true;
		}
		return res;
    }

    @Override
    public boolean remove(T pattern) {
		Node<T> node = map.get(pattern);
		boolean res = false;
		if (node != null) {
			res = true;
			list.removeNode(node);
			map.remove(pattern);
		}
		
		return res;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean contains(T pattern) {
        return map.get(pattern) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedHashSetIterator();
    }

}
