package collections;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public abstract class AbstractList<E> implements List<E> {

    protected int size;

    protected Comparator<? super E> comparator;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object toArray() {
        Object[] result = new Object[size()];
        for (int i = 0; i < size(); i++) {
            result[i] = get(i);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] array) {
        int size = size();
        if (array.length < size) {
            E[] result = (E[]) Array.newInstance(array.getClass().componentType(), size);
            for (int i = 0; i < size; i++) {
                result[i] = get(i);
            }

            return result;
        } else {
            System.arraycopy(toArray(), 0, array, 0, size);
            return array;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean remove(Object o) {
        return removeFirst((E) o);
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        var iterator = iterator();

        while (iterator.hasNext()) {
            E current = iterator.next();

            if (predicate.test(current)) {
                iterator.remove();
            }
        }

        return true;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        Collections.sort(this, comparator);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort() {
        Comparator<? super E> comp;

        if (comparator != null) {
            comp = comparator;
        } else if (size() > 0 && get(0) instanceof Comparable<?>) {
                comp = (a, b) -> ((Comparable<E>) a).compareTo(b);
        } else {
            throw new IllegalArgumentException(
                    "No comparator found and items does not implement Comparable!"
            );
        }

        sort(comp);
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size(); i++) {
            if (get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            if (get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<E> sublist(int start, int end) {
        sublistIndexCheck(start, end);

        List<E> list = List.emptyList();
        if (size() != 0) {
            for (int i = start; i < end; i++) {
                list.add(get(i));
            }
        }
        return list;
    }

    @Override
    public List<E> copy() {
        List<E> list = List.emptyList();
        list.addAll(this);
        return list;
    }

    @Override
    public List<E> copyOf(Collection<? extends E> collection) {
        List<E> list = copy();
        list.addAll(collection);
        return list;
    }

    @Override
    public boolean removeFirst(E element) {
        if (size() == 0) return false;

        for (int i = 0; i < size; i++) {
            if (get(i).equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLast(E element) {
        if (size() == 0) return false;

        for (int i = size - 1; i > 0; i--) {
            if (get(i).equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            var current = get(i);
            if (predicate.test(current)) {
                E apply = operator.apply(current);
                set(i, apply);
            }
        }
        return true;
    }

    @Override
    public boolean removeIfPresent(E elem) {
        if (contains(elem)) {
            removeFirst(elem);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeAll(UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            E apply = operator.apply(get(i));
            set(i, apply);
        }

        return true;
    }

    @Override
    public boolean addAndProcess(E elem, Consumer<E> consumer) {
        add(elem);
        consumer.accept(elem);
        return true;
    }

    @Override
    public boolean forEachIf(Predicate<E> predicate, Consumer<E> consumer) {
        for (E next : this) {
            if (predicate.test(next)) {
                consumer.accept(next);
            }
        }

        return true;
    }

    @Override
    public <P> List<P> transform(Function<E, P> transformFunction) {
        List<P> temp = Collections.emptyList();
        for (E e : this) {
            temp.add(transformFunction.apply(e));
        }
        return temp;
    }

    @Override
    public <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction) {
        List<P> temp = Collections.emptyList();
        for (E e : this) {
            if (predicate.test(e)) {
                temp.add(transformFunction.apply(e));
            }
        }
        return temp;
    }

    @Override
    public E reduce(BinaryOperator<E> reduceOperator) {
        if (size() == 0) return null;
        E result = get(0);
        for (int i = 1; i < size(); i++) {
            result = reduceOperator.apply(result, get(i));
        }
        return result;
    }

    protected void sublistIndexCheck(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("Start index greater than end index!");
        }
        if (start < 0) {
            throw new IndexOutOfBoundsException("Start index lower than zero!");
        }
        if (end > size()) {
            throw new IndexOutOfBoundsException("End index greater than size!");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof List<?>)) return false;

        List<E> other;
        try {
            other = (List<E>) o;
        } catch (ClassCastException ignored) {
            return false;
        }

        if (this.size() != other.size()) return false;

        for (var e: other) {
            if (!this.contains(e)) return false;
        }
        return true;
    }
}
