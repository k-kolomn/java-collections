import collections.ArrayList;
import collections.ArraySet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArraySetTest {
    @Test
    public void testAddElement() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testAddNullElement() {
        ArraySet<Integer> set = new ArraySet<>();
        assertThatThrownBy(() -> set.add(null));
    }

    @Test
    public void testIteratorElement() {
        ArraySet<Integer> list = new ArraySet<>();
        list.add(1);
        list.add(2);
        list.iterator();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testToArrayElement() {
        ArraySet<Integer> list = new ArraySet<>();
        list.add(1);
        list.add(2);
        list.toArray();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testCopyElement() {
        ArraySet<Integer> list = new ArraySet<>();
        list.add(1);
        list.add(2);
        list.copy();
        Assertions.assertEquals(2, list.size());
    }

//    @Test
//    public void testCopyOfElement() {
//        ArraySet<Integer> list = new ArraySet<>();
//        list.add(1);
//        list.add(2);
//        list.copyOf();
//    }

//    @Test
//    public void testRetainAllElement() {
//        ArraySet<Integer> list = new ArraySet<>();
//        list.add(1);
//        list.add(2);
//        list.retainAll();
//    }

    @Test
    public void testRemoveElement() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.remove(0);
        Assertions.assertEquals(1, set.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        ArraySet<Integer> set = new ArraySet<>();
        assertThatThrownBy(() -> set.remove(10));
    }

    @Test
    public void testRemoveIfElement() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.removeIf(
                i -> i == 2
        );
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testContainsElement() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        boolean contains = set.contains(1);
        Assertions.assertTrue(contains);
    }

    @Test
    public void testContainsElementThatDoesNotExist() {
        ArraySet<Integer> set = new ArraySet<>();
        Assertions.assertFalse(set.contains(12));
    }

    @Test
    public void testClearElement() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.clear();
        Assertions.assertEquals(0, set.size());
    }


}
