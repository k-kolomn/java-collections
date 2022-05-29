import collections.ArrayList;
import collections.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {
    @Test
    public void testAddElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddByIndexElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0,1);
        list.add(1,2);
        Assertions.assertEquals(2,list.size());
    }

    @Test
    public void testAddNullElement() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.add(null));
    }

    @Test
    public void testIteratorElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.iterator();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testToArrayElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.toArray();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testCopyElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.copy();
        Assertions.assertEquals(2, list.size());
    }

//    @Test
//    public void testCopyOfElement() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.copyOf();
//    }

//    @Test
//    public void testRetainAllElement() {
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.retainAll();
//    }

    @Test
    public void testGetElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int listGet = list.get(0);
        Assertions.assertEquals(1, listGet);
    }

    @Test
    public void testGetElementThatDoesNotExist() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.get(1));
    }

    @Test
    public void testSetElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.set(0, 2);
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testSetElementThatDoesNotExist() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.set(1, 1));
    }

    @Test
    public void testRemoveElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.remove(10));
    }

    @Test
    public void testRemoveFirstElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeFirst(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testRemoveFirstElementParamEqNull() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.removeFirst(null));
    }

    @Test
    public void testRemoveLastElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeLast(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, list.get(0));
    }

    @Test
    public void testRemoveLastElementParamEqNull() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.removeLast(null));
    }

    @Test
    public void testIndexOfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int checkIndex = list.indexOf(2);
        Assertions.assertEquals(1, checkIndex);
    }

    @Test
    public void testIndexOfElementThatDoesNotExist() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.indexOf(10));
    }

    @Test
    public void testContainsElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        boolean contains = list.contains(1);
        Assertions.assertTrue(contains);
    }

    @Test
    public void testContainsElementThatDoesNotExist() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.contains(2));
    }

    @Test
    public void testClearElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testIsEmptyElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        boolean isEmpty = list.isEmpty();
        Assertions.assertFalse(isEmpty);
    }

    @Test
    public void testForEachElement() {
        ArrayList<Integer> list = new ArrayList<>();
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
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.changeAll(i -> i + 1);

        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(3, list.get(1));
    }

    @Test
    public void testChangeIfElement() {
        ArrayList<Integer> list = new ArrayList<>();
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
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIf(
                i -> i == 2
        );
        Assertions.assertEquals(3, list.get(1));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testRemoveIfPresentElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIfPresent(1);
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddAndProcessElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.addAndProcess(2, e -> Assertions.assertEquals(2, e));

        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testTransformElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        var newList = list.transform(i -> i.doubleValue());
        Assertions.assertEquals(list.size(), newList.size());
        Assertions.assertInstanceOf(Double.class, newList.get(0));
        Assertions.assertEquals(2, newList.get(1));
    }

    @Test
    public void testReduceElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        var result = list.reduce(Integer::sum);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testRetainAll() {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> retainList = new ArrayList<>();
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
