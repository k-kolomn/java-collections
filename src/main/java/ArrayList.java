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
     public boolean remove (int index){
        checkIndex(index);
        try {
            System.arraycopy(data , 0,data, 0, index);
            System.arraycopy(data, index + 1,data, index, data.length - index - 1);
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
        for (int i = 0; i < data.length; i++) {
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
        for (int i = data.length - 1; i > 0; i--) {
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
}
