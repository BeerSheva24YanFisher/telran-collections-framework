package telran.util;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class TreeSet<T> implements Set<T> {
	private static class  Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;
		Node(T obj) {
			this.obj = obj;
		}
	}
	Node<T> root;
	Comparator<T> comparator;
	int size;

	public TreeSet(Comparator<T> comparator ) {
		this.comparator = comparator;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>)Comparator.naturalOrder());
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = getLeastFrom(root);
		Node<T> nodeToDelete = null;
		
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			nodeToDelete = current;
			T res = current.obj;
			current = getCurrent(current);
			return res;
		}
		
		@Override
		public void remove() {
			if (nodeToDelete == null )
				throw new IllegalStateException();
			removeNode(nodeToDelete);
			size--;
			nodeToDelete = null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Object pattern) {
		Node<T> node = getNode((T)pattern);
		return node != null ? node.obj : null;
	}

	@Override
	public boolean add(T obj) {
		boolean res = false;
		if(obj == null) {
			throw new NullPointerException();
		}
		if ( !contains(obj)) {
			res = true;
			Node<T> node = new Node<>(obj);
			if (root == null) {
				addRoot(node);
			} else {
				addWithParent(node);
			}
			size++;
		}
		return res;
	}

	private void addWithParent(Node<T> node) {
		Node<T> parent = getParent(node.obj);
		if (comparator.compare(node.obj, parent.obj) > 0 ) {
			parent.right = node;
		} else {
			parent.left = node;
		}
		node.parent = parent;
	}

	private void addRoot(Node<T> node) {
		root = node;
	}




@Override
public boolean remove(T pattern) {
    Node<T> node = getNode(pattern);
    if (node == null) {
        return false;
    }
    removeNode(node);
    size--;
    return true;
}

private void removeNode(Node<T> node) {
    if (node.left == null || node.right == null) {
        deleteLeafOrSingleChildNode(node);
    } else {
        Node<T> replacementNode = getGreatestFrom(node.left);
        node.obj = replacementNode.obj;
        deleteLeafOrSingleChildNode(replacementNode);
    }
}

private void deleteLeafOrSingleChildNode(Node<T> node) {
    Node<T> child = (node.left != null) ? node.left : node.right;
    Node<T> parent = node.parent;
    if (parent != null) {
        if (parent.left == node) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    } else {
        root = child;
    }
    if (child != null) {
        child.parent = parent;
    }
    node.obj = null;
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
        return getNode(pattern) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }


	private Node<T> getParentOrNode(T pattern) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while(current != null && (compRes = comparator.compare(pattern, current.obj)) != 0 ) {
			parent = current;
			current = compRes>0 ? current.right : current.left;
		}
		return 	current==null ? parent : current;
	}

	private Node<T> getNode(T pattern) {
		Node<T> res = getParentOrNode(pattern);
		int compRes = 0;
		if (res!=null){
			compRes = comparator.compare(pattern, res.obj);
		}
		return compRes == 0 ? res : null;
	}

	private Node<T> getParent(T pattern) {
		Node<T> res = getParentOrNode(pattern);
		int compRes = comparator.compare(pattern, res.obj);
		return compRes == 0 ? null : res;
	}



	private Node<T> getLeastFrom(Node<T> node) {
		if ( node != null) {
			while( node.left != null) {
				node = node.left;
			}
		}
		return node;
	}

	private Node<T> getGreatestFrom(Node<T> node) {
		if ( node != null) {
			while( node.right != null) {
				node = node.right;
			}
		}
		return node;
	}

	private Node<T> getCurrent(Node<T> current) {
		return current.right != null ? getLeastFrom(current.right) : getFirstParentIfNot(current, node -> node.parent.right == node);
	}

	private Node<T> getFirstParentIfNot( Node<T> node, Predicate<Node<T>> predicate) {
		while ( node.parent != null && predicate.test(node) ) {
			node = node.parent;
		}
		return node.parent;
	}




	
}