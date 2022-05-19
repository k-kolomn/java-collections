import lombok.Data;
import lombok.NoArgsConstructor;

public class LinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;

    private int size;

    @Override
    public void add(E element) {
        var newNode = new Node<>(element);

        if (size == 0) {
            head = newNode;
            tail = newNode;

            linkNodes(head, tail);
        } else {
            linkNodes(tail, newNode);
            tail = newNode;
        }

        size++;
    }

    private void linkNodes(Node<E> first, Node<E> second) {
        first.setNext(second);
        second.setPrevious(first);
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if (index == 0) return head.getData();
        if (index == size - 1) return tail.getData();
        return getNode(index).getData();
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("index " + index + " is out of bounds");
    }

    @Override
    public boolean remove(int index) {
        checkIndex(index);
        // check if its last remaining node in the list
            // clear()
        // check if its head node
            // head -> head.next()
        // check if its tail node
            // tail -> tail.previous()

        var currentNode = getNode(index);


        linkNodes(currentNode.getPrevious(), currentNode.getNext());

        return true;
    }

    @Override
    public boolean set(int idx, E newValue) {
        checkIndex(idx);
        getNode(idx).setData(newValue);
        return true;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean removeFirst(E element) {
        if (size == 0) return false;

        if (head.getData().equals(element)) {
            return remove(0);
        }

        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            if (currentNode.getData().equals(element)) {
                return remove(i);
            }
        }

        return false;
    }

    @Override
    public boolean removeLast(E element) {
        if (size == 0) return false;

        if (tail.getData().equals(element)) {
            return remove(size - 1);
        }

        for (int i = size - 1; i >= 0; i--) {
            var currentNode = getNode(i);
            if (currentNode.getData().equals(element)) {
                return remove(i);
            }
        }

        return false;
    }

    @Override
    public int indexOf(E element) {
        if (size == 0) return -1;

        int counter = 0;
        var currentNode = head;
        while (currentNode != null) {
            if (currentNode.getData().equals(element)) {
                return counter;
            }

            counter++;
            currentNode = currentNode.getNext();
        }

        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public boolean changeAll(Operator<E> operator) {
        for (int i = 0; i < size; i++) {
            var currentNode = getNode(i);
            currentNode.setData(
                    operator.accept(
                            currentNode.getData()
                    )
            );
        }
        return true;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, Operator<E> operator) {
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
        return true;
    }

    @Override
    public boolean removeIf(Predicate<E> predicate) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(get(i))) {
                remove(i);
            }
        }

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
        if (contains(elem)) {
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
    public boolean processIf(Predicate<E> predicate, Consumer<E> consumer) {
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

        return null;
    }

    @Override
    public <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction) {

        return null;
    }

    @Override
    public E reduce(BinaryOperator<E> reduceOperator) {
        if (size == 0) return null;
        E result = head.getData();
        for (int i = 1; i < size; i++) {
            reduceOperator.apply(result, get(i));
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
