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
        Producer<Double> producer = () -> Math.random();

        printDouble.accept(
                producer.get()
        );



        // Predicate -> Предсказание (Условие)
        Predicate<Integer> integerIsOdd = (i) -> i % 2 == 0;

        int a = 12;

        if (integerIsOdd.test(a)) {
            printInt.accept(a);
        }

    }
}

@FunctionalInterface
interface Function <T, R> {
    R apply(T t);
}

@FunctionalInterface
interface IntFunction <T> {
    T apply(int i);
}

@FunctionalInterface
interface Consumer<T> {
    void accept(T t);
}

@FunctionalInterface
interface Producer<T> {
    T get();
}

@FunctionalInterface
interface Predicate<T> {
    boolean test(T t);
}

@FunctionalInterface
interface Operator<T> {
    T operate(T t);
}

@FunctionalInterface
interface BiOperator<T> {
    T operate(T t1, T t2);
}