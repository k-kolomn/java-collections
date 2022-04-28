import java.lang.reflect.Array;

public class ArrayList<E> implements List<E>{

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
    public E get(int index) {
        if (index >= data.length || index < 0) throw new ArrayIndexOutOfBoundsException();
        return data[index];
    }

    @Override
    // zadanie so *
     public boolean remove (int index){
        if (index > data.length || index < 0) throw new ArrayIndexOutOfBoundsException();
        System.arraycopy(data , 0,data, 0, index);
        System.arraycopy(data, index + 1,data, index, data.length - index - 1);
        size--;
        return true;
    }


    @Override
    // kirill
    public boolean set(int index, E newValue) {
        if (index > data.length || index < 0) throw new ArrayIndexOutOfBoundsException();
        data[index] = newValue;
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
            if (data[i].equals(element)) {
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
            if (data[i].equals(element)) {
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
            if (data[i].equals(element)) {
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
            if (data[i].equals(value)) {
                return true;
            }
        }
        return false;

    }
}
