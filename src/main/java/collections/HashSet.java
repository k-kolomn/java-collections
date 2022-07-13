package collections;

import java.util.Iterator;
import java.util.function.Predicate;

public class HashSet<E> implements Set<E> {

    private final Object dummyValue = new Object();

    private HashMap<E, Object> map;

    public HashSet() {
        map = new HashMap<>();
    }


    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object toArray() {
        return null;
    }

    @Override
    public E[] toArray(E[] array) {
        return null;
    }

    @Override
    public boolean add(E elem) {
        // TODO: 13.07.22 key=elem, value=dummyValue
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public Set<E> copy() {
        return null;
    }

    @Override
    public Set<E> copyOf(Collection<? extends E> collection) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }
}
