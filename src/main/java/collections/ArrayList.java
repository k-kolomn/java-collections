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

public class ArrayList<E> extends AbstractList<E> {

    private E[] data;

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

    @SuppressWarnings("unchecked")
    private void trimDataToSize() {
        E[] newData = (E[]) Array.newInstance(Object.class, (int) (size * RESIZE_KOEF));

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;
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
}
