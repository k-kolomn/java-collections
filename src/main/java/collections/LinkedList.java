package collections;

import function.BinaryOperator;
import function.Consumer;
import function.Function;
import function.Predicate;
import function.UnaryOperator;

import java.util.Comparator;
import java.util.Iterator;

public class LinkedList<E> implements List<E> {

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Object toArray() {
        return null;
    }

    @Override
    public E toArray(E[] array) {
        return null;
    }

    @Override
    public boolean add(E elem) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

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
        return null;
    }

    @Override
    public E set(int index, E value) {
        return null;
    }

    @Override
    public void add(int index, E value) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
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
        return false;
    }

    @Override
    public boolean removeLast(E element) {
        return false;
    }

    @Override
    public boolean changeAll(UnaryOperator<E> operator) {
        return false;
    }

    @Override
    public boolean changeIf(Predicate<E> predicate, UnaryOperator<E> operator) {
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
    public boolean forEachIf(Predicate<E> predicate, Consumer<E> consumer) {
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
}
