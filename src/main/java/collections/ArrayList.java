package collections;

import function.BinaryOperator;
import function.Consumer;
import function.Function;
import function.Predicate;
import function.UnaryOperator;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

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
        return null;
    }

    private void checkValue(E element) {
        if (element == null) throw new NullPointerException("Null elements is not allowed!");
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
    public E toArray(E[] array) {
        return null;
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
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        data = (E[]) Array.newInstance(Object.class, INIT_CAPACITY);
        size = 0;
    }

    @Override
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
    public void replaceAll(UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            data[i] = operator.accept(data[i]);
        }
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        if (comparator == null) throw new RuntimeException("Comparator cannot be null!");
        if (size == 0) return;

        sorting(comparator);
    }

    private void sorting(Comparator<? super E> comparator) {
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
        checkValue(value);

        var old = data[index];

        data[index] = value;
        return old;
    }

    @Override
    public void add(int index, E value) {

    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        try {
            var value = data[index];
            System.arraycopy(data, 0, data, 0, index);
            System.arraycopy(data, index + 1, data, index, data.length - index - 1);
            size--;

            return value;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int indexOf(Object o) {
        var element = checkAndCastValue(o);

        for (int i = 0; i < data.length; i++) {
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
        return null;
    }

    @Override
    public List<E> copy() {
        return null;
    }

    @Override
    public List<E> copyOf(Collection<? extends E> collection) {
        return null;
    }

    @Override
    public boolean removeFirst(E element) {
        checkValue(element);
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
        checkValue(element);
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
        ArrayList<E> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(operator.accept(data[i]));
        }

        clear();
        addAll(list);

        return true;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(data[i])) {
                data[i] = operator.accept(data[i]);
            }
        }
        return true;
    }

    @Override
    public void forEach(Consumer<E> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(data[i]);
        }
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
