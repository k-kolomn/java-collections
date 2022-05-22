package function;

@FunctionalInterface
public interface BinaryPredicate<T, U> {
    boolean test(T t, U u);
}
