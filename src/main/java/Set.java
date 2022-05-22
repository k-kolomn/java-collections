public interface Set<E> extends Collection<E>{

    Set<E> of();

    Set<E> of(E e);

    Set<E> of(E e1, E e2);

    Set<E> of(E e1, E e2, E e3);

    Set<E> of(E e1, E e2, E e3, E e4);

    Set<E> of(E e1, E e2, E e3, E e4, E e5);

    Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6);

    Set<E> of(E... values);

    Set<E> copy();

    Set<E> copyOf(Collection<? extends E> collection);

}
