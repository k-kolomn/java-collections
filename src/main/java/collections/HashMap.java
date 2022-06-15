package collections;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HashMap<K, V> implements Map<K, V> {

    private static final int INIT_CAPACITY = 4;
    private static final double RESIZE_K = 1.5;
    private double loadFactorK;

    private LinkedList<Node<K, V>>[] data;

    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        data = new LinkedList[INIT_CAPACITY];
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }

        loadFactorK = 2;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public HashMap(double loadFactorK) {
        data = new LinkedList[INIT_CAPACITY];
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }

        this.loadFactorK = loadFactorK;
        size = 0;
    }

    /*
           [[k1], [k2, k5, k6], [k3], [k4]]
           get(k2)



           [key1, key2, key3, key4]

           [[key3], [key1, key2, key5, key], [key, key, key], [key4]]

           9 > 4 -> resize()
           [[key, key], [key, key], [key, key], [key, key, key]]

           [[key, key], [key], [key], [key, key], [key, key], [key]]


           1 + 2 + 0 + 1 = 4 > size * loadFactorK

           [[], [key1, key2, key3, key4], [], []]

           key4 -> 3
           3 % 4 -> 3

           key1 -> 256
           257 % 4 = 1

           key2 -> 1
           1 % 4 = 1

           1 % 2 -> 1
           2 % 2 -> 0
           3 % 2 -> 1
           4 % 2 -> 0

           2 * 2 = 4

           1 % 4 -> 1
           2 % 4 -> 2
           3 % 4 -> 3
           4 % 5 -> 0

     */

    private int getIndex(int size, K key) {
        return key.hashCode() % size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        if (size == 0) return null;

        K k = (K) key;

        var index = getIndex(data.length, k);
        var list = data[index];

        if (list.isEmpty() || !list.contains(key)) return null;

        for (Node<K, V> node : list) {
            if (node.getKey().equals(k)) {
                return node.getValue();
            }
        }

        throw new RuntimeException("Error occurred!");
    }

    @Override
    public V put(K key, V value) {
        checkLoadFactor();

        int index = getIndex(data.length, key);

        var list = data[index];

        size++;

        Node<K, V> kvNode = new Node<>(key, value);

        if (list.size() == 0 || !list.contains(kvNode)) {
            list.add(kvNode);
            return null;
        }

        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                var tmp = node.getValue();
                node.setValue(value);
                return tmp;
            }
        }

        throw new RuntimeException("Error occurred!");
    }

    private void checkLoadFactor() {
        int sum = Arrays.stream(data)
                .map(LinkedList::size)
                .reduce(0, Integer::sum);

        if (sum + 1 > data.length * loadFactorK) {
            resize();
        }
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        var oldData = data;

        int newSize = (int) (data.length * RESIZE_K);
        data = new LinkedList[newSize];
        for (int i = 0; i < data.length; i++) {
            data[i] = new LinkedList<>();
        }

        size = 0;

        for (LinkedList<Node<K, V>> list : oldData) {
            if (list.isEmpty()) continue;

            for (Node<K, V> node : list) {
                int index = getIndex(newSize, node.getKey());
                data[index].add(node);
                size++;
            }
        }
    }

    @Override
    public V remove(Object key) {



        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @AllArgsConstructor
    private static class Node<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            var tmp = this.value;
            this.value = value;
            return tmp;
        }

        public int hashCode() {
            return key.hashCode() + value.hashCode() / 2;
        }

        // v1 != v2
        // hash(v1) != hash(v2)

        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node<K, V> other = (Node<K, V>) o;

            if (this.hashCode() != other.hashCode()) return false;

            boolean keysEq = this.key.equals(other.key);
            boolean valuesEq = this.value.equals(other.value);

            return keysEq && valuesEq;
        }
    }
}
