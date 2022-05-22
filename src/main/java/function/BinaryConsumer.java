package function;

@FunctionalInterface
public interface BinaryConsumer<T, U> {
    void accept(T t, U u);
}
