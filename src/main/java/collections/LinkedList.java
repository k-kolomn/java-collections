package collections;

import function.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E> implements List<E> {


    private Node<E> head;
    private Node<E> tail;

    private int size;


    @Override
    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Object toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = head; x != null; x = x.next)
            result[i++] = x.data;
        return result;
    }

    @Override
    public E toArray(E[] array) {

        return null;
    }

    @Override
    public boolean add(E elem) {
        var newNode = new Node<>(elem);

        if (size == 0) {
            head = newNode;
            tail = newNode;

            linkNodes(head, tail);
        } else {
            linkNodes(tail, newNode);
            tail = newNode;
        }

        size++;

        return true;
    }

    private void linkNodes(Node<E> first, Node<E> second) {
        first.setNext(second);
        second.setPrevious(first);
    }

    @Override
    public boolean remove(Object o) {
            return false;
    }

    private E checkAndCastValue(Object o) {
        if (o == null) throw new NullPointerException("Null elements is not allowed!");
        return (E) o;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            if (predicate.test(currentNode.getData())) {
                remove(currentNode.getData());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {

    }

    @Override
    public void sort(Comparator<? super E> comparator) {

    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if (index == 0) return head.getData();
        if (index == size - 1) return tail.getData();
        return getNode(index).getData();
    }

    @Override
    public E set(int index, E newValue) {
        checkIndex(index);

        var currentNode = getNode(index);
        var old = currentNode.getData();
        currentNode.setData(newValue);

        return old;
    }

    @Override
    public void add(int index, E value) {

    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E old;

        if (size == 1) {
            old = head.getData();
            clear();
        } else if (index == 0) {
            old = head.getData();
            head = head.getNext();
        } else if (index == size - 1) {
            old = tail.getData();
            tail = tail.getPrevious();
        } else {
            var currentNode = getNode(index);
            old = currentNode.getData();
            linkNodes(currentNode.getPrevious(), currentNode.getNext());
        }

        size--;

        return old;
    }

    @Override
    public int indexOf(Object o) {
        var element = checkAndCastValue(o);

        if (size == 0) return -1;

        int counter = 0;
        var currentNode = head;
        while (currentNode != null) {
            if (currentNode.getData().equals(o)) {
                return counter;
            }

            counter++;
            currentNode = currentNode.getNext();
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object o) {
        return 0;
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
        if (size == 0) return false;

        if (head.getData().equals(element)) {
            remove(0);
            return true;
        }

        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            if (currentNode.getData().equals(element)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeLast(E element) {
        if (size == 0) return false;

        if (tail.getData().equals(element)) {
            remove(size - 1);
            return true;
        }

        for (int i = size - 1; i >= 0; i--) {
            var currentNode = getNode(i);
            if (currentNode.getData().equals(element)) {
                remove(i);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean changeAll(UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            currentNode.setData(
                    operator.accept(
                            currentNode.getData()
                    )
            );
        }
        size++;
        return true;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, UnaryOperator<E> operator) {
        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            if (predicate.test(currentNode.getData())) {
                currentNode.setData(
                        operator.accept(
                                currentNode.getData()
                        )
                );
            }
        }
        size++;
        return true;
    }

    @Override
    public void forEach(Consumer<E> consumer) {
        for (int i = 0; i < size; i++) {
            consumer.accept(get(i));
        }
    }

    @Override
    public boolean removeIfPresent(E elem) {
        if (contains(elem)){
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
            var currentNode = getNode(i);

            if (predicate.test(currentNode.getData())) {
                consumer.accept(currentNode.getData());
            }
        }
        return true;
    }

    @Override
    public <P> List<P> transform(Function<E, P> transformFunction) {
        LinkedList<P> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(transformFunction.apply(get(i)));
        }
        return list;
    }

    @Override
    public <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction) {
        LinkedList<P> list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            if (predicate.test(currentNode.getData())) {
                list.add(transformFunction.apply(currentNode.getData()));
            }
        }

        return list;
    }

    @Override
    public E reduce(BinaryOperator<E> reduceOperator) {
        if (size == 0) return null;
        E result = head.getData();
        for (int i = 1; i < size; i++) {
            result = reduceOperator.apply(result, get(i));
        }
        return result;
    }



    private Node<E> getNode(int index) {
        int counter;
        if (index < size / 2) {
            counter = 0;
            var currentNode = head;
            while (currentNode != null) {
                if (index == counter) {
                    return currentNode;
                }
                currentNode = currentNode.getNext();
                counter++;
            }
        } else {
            counter = size - 1;
            var currentNode = tail;
            while (currentNode != null) {
                if (index == counter) {
                    return currentNode;
                }
                currentNode = currentNode.getPrevious();
                counter--;
            }
        }

        throw new RuntimeException("Node not found!");
    }

    @Data
    @NoArgsConstructor
    public static class Node<T> {
        private T data;

        private Node<T> next;
        private Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }
}
