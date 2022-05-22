package function;

@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);

    default Consumer<T> andThen(Consumer<T> another) {
        return (t) -> {
            accept(t);
            another.accept(t);
        };
    }

    default Consumer<T> compose(Consumer<T> another) {
        return (t) -> {
            another.accept(t);
            accept(t);
        };
    }


}
