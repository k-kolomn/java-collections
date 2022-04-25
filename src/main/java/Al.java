import java.lang.reflect.Array;

public class Al<E> implements List<E> {

    private E[] data;
    private int size;

    private static final int INIT_CAPACITY = 8;
    private static final double RESIZE_KOEF = 1.5;

    @SuppressWarnings("unchecked")
    public Al() {
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
        return data[idx];
    }

    @Override
    // zadanie so *
    public boolean remove(int idx) {
        if (idx < 0 || idx >= data.length) throw new ArrayIndexOutOfBoundsException();
        try {
                size--;
                data[idx] = null;
//            for (int i = idx; i < data.length; i++) {
//                get(i);
//                set(i, data[i + 1]);
//                data[i + 1] = null;
//                return true;
//            }

        } catch (Exception e) {
        return false;
        }
        return false;
    }

    @Override
    // kirill
    public boolean set(int idx, E newValue) {
        if (idx < 0 || idx >= data.length) throw new ArrayIndexOutOfBoundsException();
        data[idx] = newValue;
        size++;
        return true;
    }

    @Override
    // danya
    public int size() {
        return size;
    }

    @Override
    // kirill
    public boolean removeFirst(E element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element) {
                remove(i);
                return true;
            }
        }
        return false;
    }
    @Override
    // danya
    public boolean removeLast(E element) {
        for (int i = data.length - 1; i > 0; i--) {
            if (data[i] == element) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    // kirill
    public int indexOf(E element) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == element) {
                return i;
            }
        }
        return 0;
    }

    @Override
    // kirill
    public boolean isEmpty() {
        return size ==0;
    }

    @Override
    // danya
    public boolean contains(E value) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;

    }
}
