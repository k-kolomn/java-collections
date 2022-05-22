package function;

public interface UnaryOperator<T> {
    T accept(T t);

    default UnaryOperator<T> andThen(UnaryOperator<T> another) {
        return (t) -> another.accept(accept(t));
    }

    default UnaryOperator<T> compose(UnaryOperator<T> another) {
        return (t) -> accept(another.accept(t));
    }

    default UnaryOperator<T> identity() {
        return (t) -> t;
    }
}
