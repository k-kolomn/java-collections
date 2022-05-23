package collections;

public class Collections {

    private static final List<?> EMPTY_LIST;

    static {
        EMPTY_LIST = new ArrayList<>(0);
    }

    public static List<?> emptyList() {
        return EMPTY_LIST;
    }

}
