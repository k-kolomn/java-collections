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
    public E get(int idx) {
        return null;
    }

    @Override
    public boolean remove(int idx) {
        return false;
    }

    @Override
    public boolean set(int idx, E newValue) {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean removeFirst(E element) {
        return false;
    }

    @Override
    public boolean removeLast(E element) {
        return false;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(E value) {
        return false;
    }
}
