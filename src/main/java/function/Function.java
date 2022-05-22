package function;

@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);

    default <U> Function<T, U> andThen(Function<R, U> another) {
        return (t) -> another.apply(apply(t));
    }

    default <U> Function<U, R> compose(Function<U, T> another) {
        return (t) -> apply(another.apply(t));
    }

    static <T> Function<T, T> identity() {
        return t -> t;
    }
}


