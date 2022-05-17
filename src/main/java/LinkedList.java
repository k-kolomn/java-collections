import lombok.Data;

public class LinkedList<E> implements List<E>{

    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(E element) {

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

    @Override
    public boolean changeAll(Operator<E> operator) {
        return false;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, Operator<E> operator) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<E> predicate) {
        return false;
    }

    @Override
    public void forEach(Consumer<E> consumer) {

    }

    @Override
    public boolean removeIfPresent(E elem) {
        return false;
    }

    @Override
    public boolean addAndProcess(E elem, Consumer<E> consumer) {
        return false;
    }

    @Override
    public boolean processIf(Predicate<E> predicate, Consumer<E> consumer) {
        return false;
    }

    @Override
    public <P> List<P> transform(Function<E, P> transformFunction) {
        return null;
    }

    @Override
    public <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction) {
        return null;
    }

    @Override
    public E reduce(BinaryOperator<E> reduceOperator) {
        return null;
    }

    @Data
    public static class Node<T> {
        private T data;

        private Node<T> next;
        private Node<T> previous;
    }
}
