import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
    public boolean add(E element) {
        checkValue(element);
        if (size >= data.length) {
            resize();
        }

        data[size] = element;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    private void checkIndex(int index) {
        if (size == 0) {
            throw new NoSuchElementException("List is empty!");
        }
        if (index >= data.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " is out of bounds!");
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
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
    public E set(int index, E newValue) {
        checkIndex(index);
        checkValue(newValue);

        var old = data[index];

        data[index] = newValue;
        return old;
    }

    @Override
    public void add(int index, E value) {

    }

    @Override
    public int size() {
        return this.size;
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
    public List<E> of(E e) {
        return null;
    }

    @Override
    public List<E> of(E e1, E e2) {
        return null;
    }

    @Override
    public List<E> of(E e1, E e2, E e3) {
        return null;
    }

    @Override
    public List<E> of(E e1, E e2, E e3, E e4) {
        return null;
    }

    @Override
    public List<E> of(E e1, E e2, E e3, E e4, E e5) {
        return null;
    }

    @Override
    public List<E> of(E... values) {
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
    public boolean contains(Object o) {
        var value = checkAndCastValue(o);
        for (E e : data) {
            if (e.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object toArray() {
        return null;
    }

    @Override
    public E toArray(E[] array) {
        return null;
    }

    @Override
    public void clear() {
        size = 0;
    }

    public void sort() {
        if (size == 0) return;

        Comparator<? super E> comp;

        if (comparator != null) {
            comp = comparator;
        } else if (data[0] instanceof Comparable) {
            comp = (o1, o2) -> ((Comparable<E>) o1).compareTo(o2);
        } else {
            throw new RuntimeException("Expected declared Comparator or implemented Comparable interface!");
        }

        sorting(comp);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        return false;
    }

    @Override
    public void replaceAll(Operator<E> operator) {

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
    @SuppressWarnings("unchecked")
    public boolean changeAll(Operator<E> operator) {
        ArrayList<E> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(operator.accept(data[i]));
        }

        clear();
        addAll(list);

        return true;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, Operator<E> operator) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(data[i])) {
                data[i] = operator.accept(data[i]);
            }
        }
        return true;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        int i = 0;
        while (i < size) {
            E e = data[i];

            if (predicate.test(e)) {
                remove(i);
            } else {
                i++;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
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
