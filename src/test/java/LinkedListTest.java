import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class LinkedListTest {
    @Test
    public void testAddElement() {
        // create or get instance of testing object
        ArrayList<Integer> list = new ArrayList<>();

        // action (do things that should be tested)
        list.add(1);

        // assertion
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testRemoveElement() {

        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);


        list.remove(4);
        list.remove(4);
        list.remove(2);
        list.remove(1);


        Assertions.assertEquals(2, list.size());
        assertThatThrownBy(() -> list.remove(10));
    }


    @Test
    public void testGetElement() {

        LinkedList<Integer> list = new LinkedList<>();

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

//    @Test
//    public void testForEach(){
//        LinkedList<Integer> list = new LinkedList<>();
//
//        list.add(1);
//        list.add(2);
//
//        list.forEach(
//             e -> e.intValue()
//        );
//        Assertions.assertEquals(1, 1);
//    }


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
    public void testAddAndProccess() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.addAndProcess(2, e -> e.doubleValue());

        Assertions.assertEquals(2, list.get(1));
    }

    @Test
    public void testProccesIf() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(3);

        Assertions.assertEquals(    list.processIf(e -> e == 3, Integer::intValue), true);
    }

    @Test
    public void testTransform() {
        LinkedList<Integer> list = new LinkedList<>();

        list.add(1);
        list.add(3);


            Assertions.assertEquals(1,   list.transform(e -> {
                for (int i = 0; i < list.size(); i++) {
                    e = list.get(i);
                }
                return e;
            }));

    }


//    public void testReduce(){
//        LinkedList<Integer> list = new LinkedList<>();
//
//        list.add(1);
//        list.add(3);
//        list.reduce((e,u) -> e.compareTo(u));
//
//        Assertions.assertEquals(1, list.size());
//    }

}
