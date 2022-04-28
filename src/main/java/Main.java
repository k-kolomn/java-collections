import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Apple[] apples = {
                new Apple(1),
                new Apple(2),
                new Apple(3),
                new Apple(4)
        };

        Apple apple = new Apple(2);
        System.out.println(apple.compareTo(new Apple(3)));

        // Comparator -> Отдельный класс, который имеет метод compare(o1, o2)
        // Comparable -> Интерфейс, который как компаратор, только метод теперь есть у самого обьекта


    }

    public static void sort(Apple[] apples) {
        for (int i = 0; i < apples.length; i++) {
            for (int j = i; j < apples.length - 1; j++) {
                Apple current = apples[j];
                Apple next = apples[j + 1];

                if (current.compareTo(next) > 0) {
                    // swap
                }
            }
        }
    }
}

record Apple(int weight) implements Comparable<Apple> {

    // compare (o1, o2)
    @Override
    public int compareTo(Apple o) {
        // o1 -> this
        // o2 -> o
        return Integer.compare(this.weight, o.weight);
    }
}
