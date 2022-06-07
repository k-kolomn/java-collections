package collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class ArrayList<E> implements List<E> {

    private E[] data;
    private int size;

    private Comparator<? super E> comparator;

    private static final int INIT_CAPACITY = 8;
    private static final double RESIZE_KOEF = 1.5;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        data = (E[]) Array.newInstance(Object.class, INIT_CAPACITY);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int initCapacity) {
        data = (E[]) Array.newInstance(Object.class, initCapacity);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList(Comparator<? super E> comparator) {
        data = (E[]) Array.newInstance(Object.class, INIT_CAPACITY);
        size = 0;

        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = (int) (size * RESIZE_KOEF);
        E[] newData = (E[]) Array.newInstance(Object.class, newSize);

        System.arraycopy(data, 0, newData, 0, data.length);

        data = newData;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int counter = 0;
            private int previous = -1;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public E next() {
                var current = data[counter];
                previous = counter++;
                return current;
            }

            @Override
            public void remove() {
                if (previous != -1) {
                    counter = previous;
                    ArrayList.this.remove(counter);
                    previous = -1;
                }
            }
        };
    }

    private E checkAndCastValue(Object o) {
        if (o == null) throw new NullPointerException("Null elements is not allowed!");
        return (E) o;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object toArray() {
        var array = new Object[size];
        System.arraycopy(data, 0, array, 0, size);
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E[] toArray(E[] array) {
        if (array.length < size) {
            return (E[]) Arrays.copyOf(data, size, array.getClass());
        } else {
            System.arraycopy(data, 0, array, 0, size);
            return array;
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index " + index + " is out of bounds for size " + size + "!"
            );
        }
    }

    @Override
    public boolean add(E elem) {
        if (size == data.length) {
            resize();
        }

        data[size] = elem;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index == -1) return false;

        System.arraycopy(data, 0, data, 0, index);
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        size--;

        return true;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        Iterator<E> iterator = iterator();

        while (iterator.hasNext()) {
            var e = iterator.next();
            if (predicate.test(e)) {
                iterator.remove();
            }
        }

        return true;
    }

    @Override
    public void clear() {
        data = (E[]) Array.newInstance(Object.class, INIT_CAPACITY);
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndex(index);
        if (collection.isEmpty()) return false;

        E[] array = (E[]) collection.toArray();
        E[] newData =(E[]) Array.newInstance(Object.class, size + array.length);

        System.arraycopy(data, 0, newData, 0, index);
        System.arraycopy(array, 0, newData, index, array.length);
        System.arraycopy(data, index, newData, index + array.length, size - index);

        data = newData;

        return true;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        sorting(comparator);
    }

    @Override
    public void sort() {
        Comparator<? super E> comp;

        if (comparator != null) {
            comp = comparator;
        } else if (size > 0 && data[0] instanceof Comparable<?>) {
            comp = (a, b) -> ((Comparable<E>) a).compareTo(b);
        } else {
            throw new IllegalArgumentException(
                    "No comparator found and items does not implement Comparable!"
            );
        }

        sorting(comp);
    }

    @SuppressWarnings("unchecked")
    private void trimDataToSize() {
        E[] newData = (E[]) Array.newInstance(Object.class, (int) (size * RESIZE_KOEF));

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;
    }

    private void sorting(Comparator<? super E> comparator) {
        trimDataToSize();
        Arrays.sort(data, comparator);
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public E set(int index, E value) {
        checkIndex(index);
        var old = data[index];
        data[index] = value;

        return old;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(int index, E value) {
        if (size == index) {
            add(value);
        } else {
            E[] newData = (E[]) Array.newInstance(Object.class, (int) ((size + 1) * RESIZE_KOEF));

            System.arraycopy(data, 0, newData, 0, index);
            newData[index] = value;
            System.arraycopy(data, index, newData, index + 1, size - index);

            data = newData;
            size++;
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        var value = data[index];
        System.arraycopy(data, 0, data, 0, index);
        System.arraycopy(data, index + 1, data, index, data.length - index - 1);
        size--;

        return value;
    }

    @Override
    public int indexOf(Object o) {
        var element = checkAndCastValue(o);

        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        var element = checkAndCastValue(o);

        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public List<E> sublist(int start, int end) {
        sublistIndexCheck(start, end);

        if (start == end) {
            return List.emptyList();
        }

        List<E> list = new ArrayList<>((int) ((end - start) * RESIZE_KOEF));

        for (int i = start; i < end; i++) {
            list.add(data[i]);
        }

        return list;
    }

    private void sublistIndexCheck(int start, int end) {
        if (start > end) {
            throw new  IllegalArgumentException("Start index greater than end index!");
        }
        if (start < 0) {
            throw new IndexOutOfBoundsException("Start index lower than zero!");
        }
        if (end > size) {
            throw new IndexOutOfBoundsException("End index greater than size!");
        }
    }

    @Override
    public List<E> copy() {
        List<E> list = new ArrayList<>((int) (size * RESIZE_KOEF));
        list.addAll(this);
        return list;
    }

    @Override
    public List<E> copyOf(Collection<? extends E> collection) {
        List<E> list = new ArrayList<>((int) ((size + collection.size()) * RESIZE_KOEF));
        list.addAll(this);
        list.addAll(collection);
        return list;
    }

    @Override
    public boolean removeFirst(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean removeLast(E element) {
        for (int i = size - 1; i > 0; i--) {
            if (data[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean changeAll(UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            data[i] = operator.apply(data[i]);
        }

        return true;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(data[i])) {
                data[i] = operator.apply(data[i]);
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
    public boolean addAndProcess(E elem, Consumer<E> consumer) {
        add(elem);
        consumer.accept(elem);
        return true;
    }

    @Override
    public boolean forEachIf(Predicate<E> predicate, Consumer<E> consumer) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(data[i])) {
                consumer.accept(data[i]);
            }

        }
        return true;
    }

    @Override
    public <P> List<P> transform(Function<E, P> transformFunction) {
        ArrayList<P> temp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            temp.add(transformFunction.apply(data[i]));
        }
        return temp;
    }

    @Override
    public <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction) {
        ArrayList<P> temp = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (predicate.test(data[i])) {
                temp.add(transformFunction.apply(data[i]));
            }
        }
        return temp;
    }

    @Override
    public E reduce(BinaryOperator<E> reduceOperator) {
        if (size == 0) return null;
        E result = data[0];
        for (int i = 1; i < size; i++) {
            result = reduceOperator.apply(result, data[i]);
        }
        return result;
    }
}
