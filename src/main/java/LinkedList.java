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
        var variable = getNode(index);
        if (variable == null){
            return false;
        } else{
            linkNodes(variable.getPrevious(), variable.getNext());
        }

        return false;
    }

    @Override
    public boolean set(int idx, E newValue) {
        getNode(idx).setData(newValue);
        return true;
    }

    @Override
    public int size() {
        return size;
    }



    @Override
    public boolean removeFirst(E element) {
        for (int i = 0; i < size; i++) {
            var getI = get(i);
            if (getI.equals(element)){
                if (getI.equals(head)){
                        linkNodes(head, head.getNext());
                        remove(i);
                } else if (getI.equals(tail)){
                    linkNodes(tail, tail.getPrevious());
                    remove(i);
                } else{
                    remove(i);
                }
            }
        }
        return true;
    }

    @Override
    public boolean removeLast(E element) {
        for (int i = size -1; i > 0; i--) {
            var getI = get(i);
            if (getI.equals(element)){
                if (getI.equals(head)){
                    linkNodes(head, head.getNext());
                    remove(i);
                } else if (getI.equals(tail)){
                    linkNodes(tail, tail.getPrevious());
                    remove(i);
                } else{
                    remove(i);
                }
            }
        }
        return true;
    }

    @Override
    public int indexOf(E element) {
        int counter = 0;
        while(getNode(counter).getData().equals(element)){
            return counter;
        }
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (get(i).equals(value)) return true;
        }

        return false;
    }

    @Override
    public boolean changeAll(Operator<E> operator) {
        for (int i = 0; i < size; i++) {
            getNode(i).setData(operator.accept(get(i)));
        }
        return true;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, Operator<E> operator) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(get(i))) {
                getNode(i).setData(operator.accept(get(i)));
            }
        }
        return true;
    }

    @Override
    public boolean removeIf(Predicate<E> predicate) {
        for (int i = 0; i <size ; i++) {
            if (predicate.test(get(i))){
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
    public boolean processIf(Predicate<E> predicate, Consumer<E> consumer) {
        for (int i = 0; i < size; i++) {
            if (predicate.test(get(i)))   {
                consumer.accept(get(i));
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
        if (size ==0) return null;
        E result = head.getData();
        for (int i = 1; i < size; i++) {
            reduceOperator.apply(result, get(i));
        }
        return null;
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
        return null;
    }

        public  E pop(){
        return removeFirst();
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
