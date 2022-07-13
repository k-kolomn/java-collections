package collections;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

public class LinkedSet<E> implements Set<E>{

    private final LinkedList<E> linkedList = new LinkedList<>();

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return linkedList.iterator();
    }

    @Override
    public boolean contains(Object o) {
        return linkedList.contains(o);
    }

    @Override
    public Object toArray() {
        return linkedList.toArray();
    }

    @Override
    public E[] toArray(E[] array) {
        return linkedList.toArray(array);
    }

    @Override
    public boolean add(E elem) {
        for (int i =0; i < linkedList.size(); i++){
            if(contains(elem)){
                return false;
            }
        }
        return linkedList.add(elem);
    }


    @Override
    public boolean remove(Object o) {
        return linkedList.remove(o);
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        return linkedList.removeIf(predicate);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        linkedList.clear();
    }

    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Set<?>)) return false;

        Set<E> other;
        try {
            other = (Set<E>) o;
        } catch (ClassCastException ignored) {
            return false;
        }

        if (this.size() != other.size()) return false;

        for (var e: other) {
            if (!this.contains(e)) return false;
        }
        return true;
    }

    @Override
    public Set<E> copy() {
        LinkedSet<E> linkedSet = new LinkedSet<>();
        linkedSet.addAll(linkedList);
        return linkedSet;
    }

    @Override
    public Set<E> copyOf(Collection<? extends E> collection) {
        LinkedSet<E> linkedSet = new LinkedSet<>();
        linkedSet.addAll(linkedList);
        linkedSet.addAll(collection);
        return linkedSet;
    }
}
