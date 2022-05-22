package function;

@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(Predicate<T> another) {
        return (t) -> test(t) && another.test(t);
    }

    default Predicate<T> or(Predicate<T> another) {
        return (t) -> test(t) || another.test(t);
    }

    default Predicate<T> negate() {
        return (t) -> !test(t);
    }
}
