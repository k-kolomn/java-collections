import java.lang.reflect.Array;

public class ArrayList<E> implements List<E> {

    private E[] data;
    private int size;

    private static final int INIT_CAPACITY = 8;
    private static final double RESIZE_KOEF = 1.5;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        data = (E[]) Array.newInstance(Object.class, INIT_CAPACITY);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newSize = (int) (size * RESIZE_KOEF);
        E[] newData = (E[]) Array.newInstance(Object.class, newSize);

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    private void shiftElements(int start, int end) {
        for (int i = start; i < end - 1; i++) {
            data[i] = data[i + 1];
        }
    }

    @Override
    public void add(E element) {
        if (size >= data.length) {
            resize();
        }

        data[size] = element;
        size++;
    }

    @Override
    // danya
    public E get(int idx) {
        if (idx < data.length) {

        }
        else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[idx];
    }

    @Override
    // zadanie so *
    public boolean remove(int idx) {
        return false;
    }

    @Override
    // kirill
    public boolean set(int idx, E newValue) {
        return false;
    }

    @Override
    // danya
    public int size() {
        return size;
    }

    @Override
    // kirill
    public boolean removeFirst(E element) {
        return false;
    }

    @Override
    // danya
    public boolean removeLast(E element) {
        for (int i = data.length - 1; i > 0; i--) {
            if (data[i] == element) {
                data[i] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    // kirill
    public int indexOf(E element) {
        return 0;
    }

    @Override
    // kirill
    public boolean isEmpty() {
        return false;
    }

    @Override
    // danya
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }
}
