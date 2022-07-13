package collections;

import java.util.Iterator;
import java.util.function.Predicate;

public class HashSet<E> extends AbstractSet<E>{

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
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
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
        return map.put(elem, dummyValue) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        var iterator = iterator();
        while (iterator.hasNext()) {
            if (predicate.test(iterator.next())) {
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<E> copy() {
        return map.keySet().copy();
    }

    @Override
    public Set<E> copyOf(Collection<? extends E> collection) {
        return map.keySet().copyOf(collection);
    }
}
