package collections;

public abstract class AbstractSet<E> implements Set<E>{

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Set<?>)) return false;

        Set<E> other;
        try {
            other = (Set<E>) o;
        } catch (ClassCastException ignored) {
            return false;
        }

        if (this.size() != other.size()) return false;

        for (var e : other) {
            if (!this.contains(e)) return false;
        }
        return true;
    }
}
