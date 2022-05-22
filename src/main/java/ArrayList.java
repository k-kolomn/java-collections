import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class ArrayList<E> implements List<E> {

    private E[] data;
    private int size;

    private Comparator<E> comparator;

    private static final int INIT_CAPACITY = 8;
    private static final double RESIZE_KOEF = 1.5;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        data = (E[]) Array.newInstance(Object.class, INIT_CAPACITY);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList(Comparator<E> comparator) {
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
    public void add(E element) {
        if (size >= data.length) {
            resize();
        }

        data[size] = element;
        size++;
    }

    private void checkIndex(int index) {
        if (index >= data.length || index < 0) throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean remove(int index) {
        if (size == 0){
            return false;
        }
        checkIndex(index);
        try {
            System.arraycopy(data, 0, data, 0, index);
            System.arraycopy(data, index + 1, data, index, data.length - index - 1);
            size--;
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public boolean set(int index, E newValue) {
        checkIndex(index);
        checkValue(newValue);
        data[index] = newValue;
        return true;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void checkValue(E element) {
        if (element == null) throw new RuntimeException("Null elements is not allowed!");
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
    public int indexOf(E element) {
        checkValue(element);
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E value) {
        checkValue(value);
        for (E e : data) {
            if (e.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        size = 0;
    }

    // class (o1, o2)
    // o1.compareTo(o2)

    public void sort() {
        if (size == 0) return;

        Comparator<E> comp;

        if (comparator != null) {
            comp = comparator;
        } else if (data[0] instanceof Comparable) {
            comp = (o1, o2) -> ((Comparable<E>) o1).compareTo(o2);
        } else {
            throw new RuntimeException("Expected declared Comparator or implemented Comparable interface!");
        }

        sorting(comp);
    }

    public void sort(Comparator<E> comparator) {
        if (comparator == null) throw new RuntimeException("Comparator cannot be null!");
        if (size == 0) return;

        sorting(comparator);
    }

    private void sorting(Comparator<E> comparator) {
        Arrays.sort(data, comparator);
    }


    @Override
    public boolean changeAll(Operator<E> operator) {
        for (int i = 0; i < size; i++) {
            data[i] = operator.accept(data[i]);
        }
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
    public boolean removeIf(Predicate<E> predicate) {
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
    public boolean processIf(Predicate<E> predicate, Consumer<E> consumer) {
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
