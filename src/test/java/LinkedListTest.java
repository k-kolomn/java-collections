import collections.LinkedList;
import collections.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LinkedListTest {
    @Test
    public void testAddElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(list.contains(1));
        Assertions.assertTrue(list.contains(2));
    }

    @Test
    public void testGetElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        int listGet = list.get(0);
        Assertions.assertEquals(1, listGet);
    }

    @Test
    public void testGetElementThatDoesNotExist() {
        List<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.get(1));
    }

    @Test
    public void testSetElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.set(0, 2);
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(1, list.size());
        Assertions.assertFalse(list.contains(1));
        Assertions.assertTrue(list.contains(2));
    }

    @Test
    public void testSetElementThatDoesNotExist() {
        List<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.set(1, 1));
    }

    @Test
    public void testRemoveElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        List<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.remove(10));
    }

    @Test
    public void testRemoveFirstElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeFirst(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testRemoveLastElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeLast(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    public void testIndexOfElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int checkIndex = list.indexOf(2);
        Assertions.assertEquals(1, checkIndex);
    }

    @Test
    public void testContainsElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        boolean contains = list.contains(1);
        Assertions.assertTrue(contains);
    }

    @Test
    public void testClearElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testForEachElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        AtomicInteger counter = new AtomicInteger(0);

        list.forEach(
                e -> counter.incrementAndGet()
        );
        Assertions.assertEquals(list.size(), counter.get());
    }

    @Test
    public void testChangeAllElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        list.changeAll(i -> i + 1);

        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(3, list.get(1));
    }

    @Test
    public void testChangeIfElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.changeIf(
                i -> i == 1,
                i -> i + 1
        );
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(2, list.get(2));
    }

    @Test
    public void testRemoveIfElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIf(
                i -> i == 2
        );
        Assertions.assertFalse(list.contains(2));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testRemoveIfPresentElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIfPresent(1);
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddAndProcessElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        list.addAndProcess(2, e -> Assertions.assertEquals(2, e));
        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testProcessIf() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);

        AtomicBoolean flag = new AtomicBoolean(false);
        list.forEachIf(e -> e == 3, e -> flag.set(true));
        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(flag.get());
    }

    @Test
    public void testTransformElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        var newList = list.transform(Integer::doubleValue);
        Assertions.assertEquals(list.size(), newList.size());
        Assertions.assertInstanceOf(Double.class, newList.get(0));
        Assertions.assertEquals(2, newList.get(1));
    }

    @Test
    public void testReduceElement() {
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        var result = list.reduce(Integer::sum);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testReduceOverflowByte() {
        List<Byte> list = new LinkedList<>();
        list.add((byte) 120);
        list.add((byte) 7);

        var result = list.reduce((a, b) -> (byte) (a + b));
        Assertions.assertEquals(Byte.MAX_VALUE, result);
        list.add((byte) 1);
        result = list.reduce((a, b) -> (byte) (a + b));
        Assertions.assertEquals(Byte.MIN_VALUE, result);
    }

    @Test
    public void testSublist() {
        List<Integer> numbers = new LinkedList<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        var sublist = numbers.sublist(1, 3);

        Assertions.assertEquals(2, sublist.size());
        Assertions.assertTrue(sublist.contains(2));
        Assertions.assertTrue(sublist.contains(3));
    }


    @Test
    public void testAddAll() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        List<Integer> list2 = new LinkedList<>();

        list2.add(3);
        list2.add(4);
        list.addAll(list2);

        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(3, list.get(2));
    }

    @Test
    public void testAddAllByIndex() {
        List<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        List<Integer> list2 = new LinkedList<>();

        list2.add(3);
        list2.add(4);
        list.addAll(1, list2);

        Assertions.assertEquals(4, list.size());
        Assertions.assertEquals(1, list.get(0));
        Assertions.assertEquals(3, list.get(1));
        Assertions.assertEquals(4, list.get(2));
        Assertions.assertEquals(2, list.get(3));
    }

    @Test
    public void testCopy() {

        List<Integer> list = new LinkedList<>();

        list.add(3);
        list.add(4);
        list.copy();

        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testRetainAll() {
        List<Integer> list = new LinkedList<>();
        List<Integer> retainList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);

            if (i % 3 == 0) {
                retainList.add(i);
            }
        }

        list.retainAll(retainList);

        Assertions.assertTrue(list.contains(0));
        Assertions.assertTrue(list.contains(3));
        Assertions.assertTrue(list.contains(6));
        Assertions.assertTrue(list.contains(9));
    }

}
