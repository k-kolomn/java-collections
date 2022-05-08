import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.security.auth.Subject;

public class Main2 {
    public static void main(String[] args) {
        // Function -> for one data type -> example:
        // a + 1; a * 2; a / 4
        // int -> double


        Function<Integer, Double> divBy2 = i -> ((double) i) / 2.0;

        IntFunction<Double> intToDouble = i -> i * 1.0;

        System.out.println(divBy2.apply(3));


        // Consumer -> Потребитель
        Consumer<Double> printDouble = d -> System.out.println(d);
        Consumer<Integer> printInt = (i) -> System.out.println(i);

        printInt.accept(12);


        // Producer -> Производитель
        Supplier<Double> producer = () -> Math.random();

        printDouble.accept(
                producer.get()
        );


        // Predicate -> Предсказание (Условие)
        Predicate<Integer> integerIsOdd = (i) -> i % 2 == 0;

        int a = 12;

        if (integerIsOdd.test(a)) {
            printInt.accept(a);
        }

        Function<Integer, String> func1 = i -> String.valueOf(i);   // 12 -> "12"
        Function<String, Integer> func2 = s -> s.length();          // "12" -> 2
        var func3 = func1.andThen(func2);      // func1 -> func2 -> res
        var func4 = func2.compose(func1);      // func1 -> func2 -> res

        // a.andThen(b) = b.compose(a)

//        java.util.function.Function

        // 1, 2 ... 30
        // key -> value

        Stream.iterate(0, i -> i < 30, i -> i++)
                .collect(Collectors.toMap(
                        i -> i,
                        i -> func3.apply(i)
                ));

        Stream.generate(() -> (int) (Math.random() * 100))
                .limit(100)
                .forEach(System.out::println);

        int aa = 12;
        var result1 = func3.apply(aa);
        var result2 = func4.apply(aa);

        System.out.println(result1); //
        System.out.println(result2); //

        // Function, Operator, Consumer, Supplier, Predicate

    }
}

@FunctionalInterface
interface Function<T, R> {
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

@FunctionalInterface
interface IntFunction<T> {
    T apply(int i);


}

@FunctionalInterface
interface Consumer<T> {
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

@FunctionalInterface
interface Supplier<T> {
    T get();
}

@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);

    default Predicate<T> and(Predicate<T> another) {
        return (t) -> test(t) && another.test(t);
    }

    default Predicate<T> or(Predicate<T> another) {
        return (t) -> test(t) || another.test(t);
    }

    default  Predicate<T> negate(){
        return (t) -> !test(t);
    }
}


/*
Operator, BiOperator, ThreeOperator
*/
@FunctionalInterface
interface BiFunction<T, U, R> {
    R apply(T t, U u);
}

@FunctionalInterface
interface ThreeFunction<T, E, U, R> {
    R apply(T t, E e, U u);
}

@FunctionalInterface
interface BiConsumer<T, U> {
    void accept(T t, U u);
}

@FunctionalInterface
interface ThreeConsumer<T, U, E> {
    void accept(T t, U u, E e);
}

@FunctionalInterface
interface BiPredicate<T, U> {
    boolean test(T t, U u);
}

@FunctionalInterface
interface ThreePredicate<T, U, E> {
    boolean test(T t, U u, E e);
}

interface Operator<T> {
    T accept(T t);

    default Operator<T> andThen(Operator<T> another) {
        return (t) -> another.accept(accept(t));
    }

    default Operator<T> compose(Operator<T> another) {
        return (t) -> accept(another.accept(t));
    }

    default Operator<T> identity() {
        return (t) -> t;
    }
}

@FunctionalInterface
interface UnaryOperator<T> {
    T apply(T t);
}

@FunctionalInterface
interface BinaryOperator<T> {
    T apply(T t1, T t2);
}

interface TernaryOperator<T> {
    T apply(T t1, T t2, T t3);
}
