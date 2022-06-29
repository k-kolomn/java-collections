import collections.ArrayList;
import collections.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {
    @Test
    public void testAddElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddByIndexElement() {
        List<Integer> list = new ArrayList<>();
        list.add(0,1);
        list.add(1,2);
        list.add(1,3);
        Assertions.assertEquals(3,list.size());
    }


    @Test
    public void testIteratorElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.iterator();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testToArrayElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.toArray();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testCopyElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.copy();
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testGetElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        int listGet = list.get(0);
        Assertions.assertEquals(1, listGet);
    }

    @Test
    public void testGetElementThatDoesNotExist() {
        List<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.get(1));
    }

    @Test
    public void testSetElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.set(0, 2);
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testSetElementThatDoesNotExist() {
        List<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.set(1, 1));
    }

    @Test
    public void testRemoveElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        List<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.remove(10));
    }

    @Test
    public void testRemoveFirstElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeFirst(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(2, list.get(0));
    }


    @Test
    public void testRemoveLastElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.removeLast(1);
        Assertions.assertEquals(2, list.size());
        Assertions.assertEquals(1, list.get(0));
    }


    @Test
    public void testIndexOfElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int checkIndex = list.indexOf(2);
        Assertions.assertEquals(1, checkIndex);
    }


    @Test
    public void testContainsElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        boolean contains = list.contains(1);
        Assertions.assertTrue(contains);
    }


    @Test
    public void testClearElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.clear();
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testIsEmptyElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        boolean isEmpty = list.isEmpty();
        Assertions.assertFalse(isEmpty);
    }

    @Test
    public void testForEachElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        AtomicInteger counter = new AtomicInteger(0);

        list.forEach(
                e -> counter.incrementAndGet()
        );
        Assertions.assertEquals(counter.get(), list.size());
    }

    @Test
    public void testChangeAllElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.changeAll(i -> i + 1);

        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(3, list.get(1));
    }

    @Test
    public void testChangeIfElement() {
        List<Integer> list = new ArrayList<>();
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
        List<Integer> list = new ArrayList<>();
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
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        list.removeIfPresent(1);
        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void testAddAndProcessElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        list.addAndProcess(2, e -> Assertions.assertEquals(2, e));

        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testTransformElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        var newList = list.transform(i -> i.doubleValue());
        Assertions.assertEquals(list.size(), newList.size());
        Assertions.assertInstanceOf(Double.class, newList.get(0));
        Assertions.assertEquals(2, newList.get(1));
    }

    @Test
    public void testReduceElement() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        var result = list.reduce(Integer::sum);
        Assertions.assertEquals(10, result);
    }

    @Test
    public void testRetainAll() {
        List<Integer> list = new ArrayList<>();
        List<Integer> retainList = new ArrayList<>();
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
