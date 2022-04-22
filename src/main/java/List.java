public interface List <E> {
    void add(E element);

    E get(int idx);

    boolean remove(int idx);

    boolean set(int idx, E newValue);

    int size();

    boolean removeFirst(E element);

    boolean removeLast(E element);

    int indexOf(E element);

    boolean isEmpty();

    boolean contains(E value);
}
