import collections.ArrayList;
import collections.LinkedList;
import collections.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.Inet4Address;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LinkedListTest {
    @Test
    public void testAddElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddNullElement() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.add(null));
    }

    @Test
    public void testGetElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        int listGet = list.get(0);
        Assertions.assertEquals(1, listGet);
    }

    @Test
    public void testGetElementThatDoesNotExist() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.get(1));
    }

    @Test
    public void testSetElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.set(0, 2);
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testSetElementThatDoesNotExist() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.set(1, 1));
    }

    @Test
    public void testRemoveElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.remove(10));
    }

    @Test
    public void testRemoveFirstElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeFirst(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testRemoveFirstElementParamEqNull() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.removeFirst(null));
    }

    @Test
    public void testRemoveLastElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeLast(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    public void testRemoveLastElementParamEqNull() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.removeLast(null));
    }

    @Test
    public void testIndexOfElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int checkIndex = list.indexOf(2);
        Assertions.assertEquals(1, checkIndex);
    }

    @Test
    public void testIndexOfElementThatDoesNotExist() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.indexOf(10));
    }

    @Test
    public void testContainsElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        boolean contains = list.contains(1);
        Assertions.assertTrue(contains);
    }

    @Test
    public void testContainsElementThatDoesNotExist() {
        LinkedList<Integer> list = new LinkedList<>();
        assertThatThrownBy(() -> list.contains(2));
    }

    @Test
    public void testClearElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testForEachElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

//        AtomicInteger counter = new AtomicInteger(0);
//
//        list.forEach(
//                e -> counter.incrementAndGet()
//        );
//        Assertions.assertEquals(list.size(), counter.get());
    }

    @Test
    public void testChangeAllElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        list.changeAll(i -> i + 1);

        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(3, list.get(1));
    }

    @Test
    public void testChangeIfElement() {
        LinkedList<Integer> list = new LinkedList<>();
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
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIf(
                i -> i == 2
        );
        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void testRemoveIfPresentElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIfPresent(1);
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddAndProcessElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        list.addAndProcess(2, e -> Assertions.assertEquals(2, e));
        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testProcessIf() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(3);

        AtomicBoolean flag = new AtomicBoolean(false);
        list.forEachIf(e -> e == 3, e -> flag.set(true));
        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(flag.get());
    }

    @Test
    public void testTransformElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        var newList = list.transform(i -> i.doubleValue());
        Assertions.assertEquals(list.size(), newList.size());
        Assertions.assertInstanceOf(Double.class, newList.get(0));
        Assertions.assertEquals(2, newList.get(1));
    }

    @Test
    public void testReduceElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        var result = list.reduce(Integer::sum);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testReduceOverflowByte() {
        LinkedList<Byte> list = new LinkedList<>();
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
        LinkedList<Integer> numbers = new LinkedList<>();

        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        numbers.sublist(1, 3);
        //###

        assertEquals(3, numbers.size());


    }


    @Test
    public void testAddAll() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        LinkedList<Integer> list2 = new LinkedList<>();

        list2.add(3);
        list2.add(4);
        list.addAll(list2);

        assertEquals(4, list.size());
        assertEquals(3, list.get(2));
    }

    @Test
    public void testAddAllByIndex() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        LinkedList<Integer> list2 = new LinkedList<>();

        list2.add(3);
        list2.add(4);
        list.addAll(1, list2);

        assertEquals(3, list.size());
        assertEquals(3, list.get(1));
    }

    @Test
    public void testCopy(){

        LinkedList<Integer> list = new LinkedList<>();

        list.add(3);
        list.add(4);
        list.copy();

        assertEquals(2, list.size());

    }

}
