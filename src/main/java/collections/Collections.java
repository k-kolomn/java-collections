package collections;

import java.util.Comparator;

public class Collections {

    private static final List<?> EMPTY_LIST;

    private static final Set<?> EMPTY_SET;

    static {
        EMPTY_LIST = new ArrayList<>(0);
        EMPTY_SET = new ArraySet<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> emptyList() {
        return ((List<T>) EMPTY_LIST).copy();
    }

    @SuppressWarnings("unchecked")
    public static <T> Set<T> emptySet() {
        return ((Set<T>) EMPTY_SET).copy();
    }

    public static <E> void sort(List<E> list, Comparator<? super E> comparator) {
        List<E> tmp = sorting(list, comparator);
        list.clear();
        list.addAll(tmp);
    }

    private static <E> List<E> sorting(List<E> origin, Comparator<? super E> comparator) {
        if (origin.isEmpty() || origin.size() == 1) return origin;
        if (origin.size() == 2) {
            var first = origin.get(0);
            var second = origin.get(1);

            LinkedList<E> result = new LinkedList<>();

            if (comparator.compare(first, second) > 0) {
                result.add(second);
                result.add(first);
            } else {
                result.add(first);
                result.add(second);
            }

            return result;

        }

        int t = origin.size() - 1;
        var tV = origin.get(t);

        LinkedList<E> result = new LinkedList<>();
        LinkedList<E> left = new LinkedList<>();
        LinkedList<E> right = new LinkedList<>();


        for (int i = 0; i < origin.size(); i++) {
            if (i == t) continue;

            var current = origin.get(i);

            if (comparator.compare(current, tV) > 0) {
                right.add(current);
            } else {
                left.add(current);
            }
        }

        result.addAll(
                sorting(left, comparator)
        );
        result.add(tV);
        result.addAll(
                sorting(right, comparator)
        );

        return result;

    }

}
