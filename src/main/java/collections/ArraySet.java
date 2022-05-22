package collections;

import function.Predicate;

import java.util.Iterator;

public class ArraySet<E> implements Set<E>{

    private final ArrayList<E> arrayList = new ArrayList<>();

    @Override
    public int size() {
        return arrayList.size();
    }

    @Override
    public Iterator<E> iterator() {
        return arrayList.iterator();
    }

    @Override
    public boolean contains(Object o) {
        return arrayList.contains(o);
    }

    @Override
    public Object toArray() {
        return arrayList.toArray();
    }

    @Override
    public E toArray(E[] array) {
        return arrayList.toArray(array);
    }

    @Override
    public boolean add(E elem) {
        if (arrayList.contains(elem)){
            return false;
        }
        return arrayList.add(elem);
    }
    @Override
    public boolean addAll(Collection<? extends E> collection){
            for (var e : collection){
                add(e);
            }
            return true;
    }

    @Override
    public boolean remove(Object o) {
        return arrayList.remove(o);
    }

    @Override
    public boolean removeIf(Predicate<? super E> predicate) {
        return arrayList.removeIf(predicate);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {
        arrayList.clear();
    }


    public Set<E> copy() {
        ArraySet<E> arraySet = new ArraySet<>();
        arraySet.addAll(arrayList);
        return arraySet;
    }


    @Override
    public Set<E> copyOf(Collection<? extends E> collection) {
        ArraySet<E> arraySet = new ArraySet<>();
        arraySet.addAll(arrayList);
        arraySet.addAll(collection);
        return arraySet;
    }
}