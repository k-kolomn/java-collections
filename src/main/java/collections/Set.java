package collections;

public interface Set<E> extends Collection<E> {

    Set<E> copy();

    Set<E> copyOf(Collection<? extends E> collection);

    static <T> Set<T> emptySet() {
        return Collections.emptySet();
    }

    static <T> Set<T> of(T ... elems) {
        Set<T> set = emptySet();

        for (T elem : elems) {
            set.add(elem);
        }

        return set;
    }

}
