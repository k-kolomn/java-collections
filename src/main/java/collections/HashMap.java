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
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (LinkedList<Node<K, V>> list : data) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getValue().equals(value)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        if (size == 0) return null;

        K k = (K) key;

        var index = getIndex(data.length, k);
        var list = data[index];

        if (list.isEmpty()) return null;

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
    @SuppressWarnings("unchecked")
    public V remove(Object key) {

        int index = getIndex(data.length, (K) key);
        var list = data[index];

        if (list.size() == 0) return null;
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key)) {
                var old = node.getValue();
                list.remove(node);
                size--;
                return old;
            }
        }

        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        // TODO: 21.06.22
    }

    @Override
    public void clear() {
        for (LinkedList<Node<K, V>> list : data) {
            list.clear();
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> result = new ArraySet<>();
        for (LinkedList<Node<K, V>> list : data) {
            for (int i = 0; i < list.size(); i++) {
                result.add(list.get(i).getKey());
            }
        }
        return result;
    }

    @Override
    public Collection<V> values() {
        LinkedList<V> result = new LinkedList<>();
        for (LinkedList<Node<K, V>> list : data) {
            for (int i = 0; i < list.size(); i++) {
                result.add(list.get(i).getValue());
            }
        }
        return result;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> result = new ArraySet<>();
        for (LinkedList<Node<K, V>> list : data) {
            for (Node<K, V> node : list) {
                result.add(node);
            }
        }
        return result;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        var get = get(key);
        return get == null ? defaultValue : get;

    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (Entry<K, V> entry : entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {
        for (Entry<K, V> entry : entrySet()) {
            var newValue = function.apply(entry.getKey(), entry.getValue());
            put(entry.getKey(), newValue);
        }
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return containsKey(key) ? get(key) : put(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        return remove(key).equals(value);
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {

        int index = getIndex(data.length, (K) key);
        var list = data[index];

        if (list.size() == 0) return false;
        for (Node<K, V> node : list) {
            if (node.getKey().equals(key) && node.getValue().equals(oldValue)) {
                node.setValue(newValue);
                return true;
            }
        }
        return false;
    }

    @Override
    public V replace(K key, V value) {
        var old = get(key);
        for (LinkedList<Node<K, V>> list : data) {
            for (Node<K, V> node : list) {
                if (node.getKey().equals(key)) {
                    node.setValue(value);
                    return old;
                }
            }
        }
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        // TODO: 21.06.22 realize this
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        // TODO: 21.06.22
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        if (remappingFunction == null) return null;
        if (containsKey(key)) {
            for (LinkedList<Node<K, V>> list : data) {
                for (Node<K,V> node : list){
                    // TODO: 21.06.22 realize this
                }
            }
        }
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        // TODO: 21.06.22
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
