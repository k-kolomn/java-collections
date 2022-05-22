import java.util.Comparator;

public interface List <E> extends Collection<E> {

    boolean addAll(int index, Collection<? extends E> collection);

    void replaceAll(Operator<E> operator);

    void sort(Comparator<? super E> comparator);

    E get(int index);

    E set(int index, E value);

    void add(int index, E value);

    E remove(int index);

    int indexOf(Object o);

    int lastIndexOf(Object o);

    List<E> sublist(int start, int end);

    List<E> of(E e);
    List<E> of(E e1, E e2);
    List<E> of(E e1, E e2, E e3);
    List<E> of(E e1, E e2, E e3, E e4);
    List<E> of(E e1, E e2, E e3, E e4, E e5);

    List<E> of(E ... values);

    List<E> copy();

    List<E> copyOf(Collection<? extends E> collection);


    boolean removeFirst(E element);

    boolean removeLast(E element);


    boolean changeAll(Operator<E> operator);

    boolean changeIf(Predicate<E> predicate, Operator<E> operator);

    void forEach(Consumer<E> consumer);

    boolean removeIfPresent(E elem);

    boolean addAndProcess(E elem, Consumer<E> consumer);

    boolean forEachIf(Predicate<E> predicate, Consumer<E> consumer);

    <P> List<P> transform(Function<E, P> transformFunction);

    <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction);

    E reduce(BinaryOperator<E> reduceOperator);
}
