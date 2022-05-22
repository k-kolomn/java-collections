import java.util.Iterator;

public class LinkedSet<E> implements Set<E>{

    private final LinkedList<E> linkedList = new LinkedList<>();

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return linkedList.iterator();
    }

    @Override
    public boolean contains(Object o) {
        return linkedList.contains(o);
    }

    @Override
    public Object toArray() {
        return linkedList.toArray();
    }

    @Override
    public E toArray(E[] array) {
        return linkedList.toArray(array);
    }

    @Override
    public boolean add(E elem) {
        return linkedList.add(elem);
    }

    @Override
    public boolean remove(Object o) {
        return linkedList.remove(o);
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        return linkedList.removeIf(predicate);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        linkedList.clear();
    }

    @Override
    public Set<E> of() {
        return null;
    }

    @Override
    public Set<E> of(E e) {
        return null;
    }

    @Override
    public Set<E> of(E e1, E e2) {
        return null;
    }

    @Override
    public Set<E> of(E e1, E e2, E e3) {
        return null;
    }

    @Override
    public Set<E> of(E e1, E e2, E e3, E e4) {
        return null;
    }

    @Override
    public Set<E> of(E e1, E e2, E e3, E e4, E e5) {
        return null;
    }

    @Override
    public Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {
        return null;
    }

    @Override
    public Set<E> of(E... values) {
        return null;
    }

    @Override
    public Set<E> copy() {
        LinkedSet<E> linkedSet = new LinkedSet<>();
        linkedSet.addAll(linkedList);
        return linkedSet;
    }

    @Override
    public Set<E> copyOf(Collection<? extends E> collection) {
        LinkedSet<E> linkedSet = new LinkedSet<>();
        linkedSet.addAll(linkedList);
        linkedSet.addAll(collection);
        return linkedSet;
    }
}
