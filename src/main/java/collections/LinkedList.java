package collections;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> head;
    private Node<E> tail;

    public LinkedList() {
        size = 0;
        head = null;
        tail = null;
    }

    public LinkedList(Comparator<? super E> comparator) {
        this.comparator = comparator;
        size = 0;
        head = null;
        tail = null;

    }

    public Iterator<E> iterator() {
        return new Iterator<>() {
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
                if (previous != null) {
                    if (size == 1) {
                        clear();
                    } else if (previous == head) {
                        head = head.getNext();
                        head.setPrevious(null);
                    } else if (previous == tail) {
                        tail = tail.getPrevious();
                        tail.setNext(null);
                    } else {
                        var first = previous.getPrevious();
                        var second = nodeCounter;

                        if (first == null) {
                            head = second;
                            head.setPrevious(null);
                        } else if (second == null) {
                            tail = first;
                            tail.setNext(null);
                        } else {
                            linkNodes(first, second);
                        }
                    }

                    previous = null;
                    size--;
                }
            }
        };
    }

    @Override
    public boolean add(E elem) {
        var newNode = new Node<>(elem);

        if (size == 0) {
            head = newNode;
        } else if (size == 1) {
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
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        for (E e : collection) {
            add(index, e);
            index++;
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E e : collection) {
            add(e);
        }
        return true;
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
        if (index > size || index < 0) throw new IndexOutOfBoundsException("index " + index + " is out of bounds");

        Node<E> node = new Node<>(value);

        if (index == size) {
            add(value);
        } else {
            if (index == 0) {
                node.setNext(head);
                head.setPrevious(node);
                head = node;
            } else {
                Node<E> oldNode = getNode(index);
                linkNodes(oldNode.getPrevious(), node);
                linkNodes(node, oldNode);
            }

            size++;
        }
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
            return old;
        }
        if (index == 0) {
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


    private Node<E> getNode(int index) {
        checkIndex(index);

        int counter;
        Node<E> currentNode;
        UnaryOperator<Integer> counterOp;
        UnaryOperator<Node<E>> nodeOp;

        if (size == 1 && index == 0) {
            return head;
        }

        if (index < size / 2) {
            counter = 0;
            currentNode = head;
            counterOp = i -> ++i;
            nodeOp = Node::getNext;
        } else {
            counter = size - 1;
            currentNode = tail;
            counterOp = i -> --i;
            nodeOp = Node::getPrevious;
        }

        while (currentNode != null) {
            if (index == counter) {
                return currentNode;
            }
            currentNode = nodeOp.apply(currentNode);
            counter = counterOp.apply(counter);
        }

        throw new RuntimeException("Bad index!");
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
