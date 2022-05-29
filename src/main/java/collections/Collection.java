package collections;

import function.Predicate;

import java.util.Iterator;

public interface Collection <E> extends Iterable<E> {

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    Iterator<E> iterator();

    boolean contains(Object o);

    Object toArray();

    E[] toArray(E[] array);

    boolean add(E elem);

    boolean remove(Object o);

    default boolean containsAll(Collection<?> collection) {
        var result = false;

        for (Object o : collection) {
            result = contains(o);
        }

        return result;
    }

    default boolean addAll(Collection<? extends E> collection) {
        var result = false;

        for (E e : collection) {
            result = add(e);
        }

        return result;
    }

    default boolean removeAll(Collection<?> collection) {
        var result = false;

        for (var e : collection) {
            result = remove(e);
        }

        return result;
    }

    boolean removeIf(Predicate<? super E> predicate);

    default boolean retainAll(Collection<?> collection) {
        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            if (!collection.contains(iterator.next())) {
                iterator.remove();
            }
        }

        return true;
    }

    void clear();
}
