public interface List <E> {
    void add(E element);

    default void addAll(List<E> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    E get(int idx);

    boolean remove(int idx);

    boolean set(int idx, E newValue);

    int size();

    boolean removeFirst(E element);

    boolean removeLast(E element);

    int indexOf(E element);

    boolean isEmpty();

    boolean contains(E value);

    void clear();

    boolean changeAll(Operator<E> operator);

    boolean changeIf(Predicate<E> predicate, Operator<E> operator);

    boolean removeIf(Predicate<E> predicate);

    void forEach(Consumer<E> consumer);

    boolean removeIfPresent(E elem);

    boolean addAndProcess(E elem, Consumer<E> consumer);

    boolean processIf(Predicate<E> predicate, Consumer<E> consumer);

    <P> List<P> transform(Function<E, P> transformFunction);

    <P> List<P> transform(Predicate<E> predicate, Function<E, P> transformFunction);

    E reduce(BinaryOperator<E> reduceOperator);
}
