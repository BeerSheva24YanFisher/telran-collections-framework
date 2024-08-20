package telran.util;

public interface List<T> extends Collection<T>{
    void add(int index, T obj);
    T remove(int index);
    T get(T pattern);
    int indexOf(T pattrn);
    int lastIndex(T pattern);

}
