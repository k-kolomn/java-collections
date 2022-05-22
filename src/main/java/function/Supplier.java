package function;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
