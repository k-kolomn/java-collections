import collections.ArrayList;
import collections.ArraySet;
import collections.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArraySetTest {
    @Test
    public void testAddElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testAddNullElement() {
        Set<Integer> set = new ArraySet<>();
        Assertions.assertTrue(set.add(null));
    }

    @Test
    public void testIteratorElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.iterator();
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testToArrayElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.toArray();
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testCopyElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.copy();
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testCopyOfElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        var result = set.copyOf(set);
        Assertions.assertEquals(2, result.size());
    }


    @Test
    public void testRetainAllElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        Set<Integer> setToRetain = new ArraySet<>();
        setToRetain.add(2);
        setToRetain.add(3);
        var result = set.retainAll(setToRetain);
        Assertions.assertTrue(result);
        Assertions.assertEquals(setToRetain, set);
    }

    @Test
    public void testRemoveElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.remove(1);
        Assertions.assertEquals(1, set.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        Set<Integer> set = new ArraySet<>();
        Assertions.assertFalse(set.remove(10));
    }

    @Test
    public void testRemoveIfElement() {
        Set<Integer> set = new ArraySet<>();
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
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        boolean contains = set.contains(1);
        Assertions.assertTrue(contains);
    }

    @Test
    public void testContainsElementThatDoesNotExist() {
        Set<Integer> set = new ArraySet<>();
        Assertions.assertFalse(set.contains(12));
    }

    @Test
    public void testClearElement() {
        Set<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.clear();
        Assertions.assertEquals(0, set.size());
    }


}
