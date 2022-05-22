import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.*;

public class LinkedListTest {
    @Test
    public void testAddElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testRemoveElement() {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(1);
        list.remove(1);

        Assertions.assertEquals(1, list.size());
        // TODO: 22.05.22 extract to another test method
        assertThatThrownBy(() -> list.remove(10));
    }


    @Test
    public void testGetElement() {

        LinkedList<Integer> list = new LinkedList<>();

        // TODO: 22.05.22 remove unnecessary million element adding
        list.add(23);
        list.add(43);


        Assertions.assertEquals(43, list.get(1));
        assertThatThrownBy(() -> list.get(4));

    }

    @Test
    public void testSetElement() {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(2);
        list.set(0, 1);

        Assertions.assertEquals(1, list.get(0));
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
        assertThatThrownBy(() -> list.removeFirst(5));
    }

    @Test
    public void testRemoveLastElement() {

        LinkedList<Integer> list = new LinkedList<>();


        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);


        list.removeLast(1);

        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals(2, list.get(2));
        assertThatThrownBy(() -> list.removeLast(3));
    }

    @Test
    public void testIndexOf() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);


        Assertions.assertEquals(2, list.indexOf(3));
        assertThatThrownBy(() -> list.indexOf(10));
    }

    @Test
    public void testContainsElement() {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Assertions.assertTrue(list.contains(2));
        assertThatThrownBy(() -> list.contains(5));
    }

    @Test
    public void testClearElements() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.clear();

        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testChangeAll() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);


        list.changeAll(t -> t + 1);

        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(3, list.get(1));
    }

    @Test
    public void testChangeIf() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        list.changeIf(
                e -> e == 1,
                e -> e + 1
        );
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testRemoveIf() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        list.removeIf(
                e -> e == 1
        );
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testForEach(){
        LinkedList<Integer> list = new LinkedList<>();
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
    public void testRemoveIfPresent() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(2);

        list.removeIfPresent(
                1
        );
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testAddAndProcess() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.addAndProcess(2, e -> Assertions.assertEquals(2, e));

        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testProcessIf() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(3);

        AtomicBoolean flag = new AtomicBoolean(false);

        list.processIf(e -> e == 3, e -> flag.set(true));

        Assertions.assertEquals(2, list.size());
        Assertions.assertTrue(flag.get());
    }

    @Test
    public void testTransform() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(3);

        var newList = list.transform(i -> i.doubleValue());

        Assertions.assertEquals(list.size(), newList.size());
        Assertions.assertInstanceOf(Double.class, newList.get(0));
        Assertions.assertEquals(3.0, newList.get(1));
    }

    @Test
    public void testReduce(){
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

}
