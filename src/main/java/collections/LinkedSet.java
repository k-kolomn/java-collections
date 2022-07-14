package collections;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.security.interfaces.ECKey;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedSet<E> extends AbstractSet<E> {

    private final LinkedList<E> linkedList = new LinkedList<>();

    private Node<E> head;
    private Node<E> tail;
    private int size;

    private Comparator<? super E> comparator;

    public LinkedSet() {
        size = 0;
        head = null;
        tail = null;
    }

    public LinkedSet(Comparator<? super E> comparator) {
        this.comparator = comparator;
        size = 0;
        head = null;
        tail = null;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> nodeCounter = head;
            private Node<E> previous = null;

            @Override
            public boolean hasNext() {
                return nodeCounter != null;
            }

            @Override
            public E next() {
                E data = nodeCounter.getData();
                previous = nodeCounter;
                nodeCounter = nodeCounter.getNext();
                return data;
            }

            @Override
            public void remove() {
                Iterator.super.remove();
            }
        };
    }

    @Override
    public boolean contains(Object o) {
        if (size < 2) return false;
        var elem = findElem(o);
        return elem;
    }

    @Override
    public Object toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (var x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }

        return result;
    }

    @Override
    public E[] toArray(E[] array) {
        if (array.length < size) {
            E[] result = (E[]) Array.newInstance(array.getClass().componentType(), size);
            int i = 0;
            for (var x = head; x != null; x = x.next) {
                result[i++] = x.data;
            }
            return result;
        } else {
            System.arraycopy(toArray(), 0, array, 0, size);
            return array;
        }
    }

    @Override
    public boolean add(E elem) {
        for (int i = 0; i < size(); i++) {
            if (contains(elem)) {
                return false;
            }
        }
        var node = new Node<>(elem);

        if (size == 0) {
            head = node;
        } else if (size == 1) {
            tail = node;
            linkNodes(head, tail);
        } else if (size > 1) {
            tail = node;
            linkNodes(tail, node);
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
        if (size == 1) clear();
        var currentNode = head;
        E old;

        while (currentNode != null) {
            if (currentNode.equals(o)) {
                if (currentNode.equals(head.getData())) {
                    old = head.getData();
                    head = head.getNext();
                } else if (currentNode.equals(tail.getData())) {
                    old = tail.getData();
                    tail = tail.getPrevious();
                } else {
                    old = currentNode.getData();
                    linkNodes(currentNode.getPrevious(), currentNode.getNext());
                }
            }
            currentNode = currentNode.getNext();
        }

        return true;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        var currentNode = head;
        while (currentNode != null) {
            if (predicate.test(currentNode.getData())) {
                remove(currentNode.getData());
                return true;
            }
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return linkedList.retainAll(collection);
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Set<E> copy() {
        LinkedSet<E> linkedSet = new LinkedSet<>();
        linkedSet.addAll(this);
        return linkedSet;
    }

    @Override
    public Set<E> copyOf(Collection<? extends E> collection) {
        LinkedSet<E> linkedSet = new LinkedSet<>();
        linkedSet.addAll(this);
        linkedSet.addAll(collection);
        return linkedSet;
    }

    private boolean findElem(Object o) {
        var currentNode = head;

        while (currentNode != null) {
            if (currentNode.getData().equals(o)) return true;
            currentNode = currentNode.getNext();
        }
        return false;
    }

    @Data
    @NoArgsConstructor
    public static class Node<T> {
        private T data;

        private LinkedSet.Node<T> next;
        private LinkedSet.Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }
}
