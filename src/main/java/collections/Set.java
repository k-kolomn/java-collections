package collections;

public interface Set<E> extends Collection<E> {

    Set<E> copy();

    Set<E> copyOf(Collection<? extends E> collection);
}
