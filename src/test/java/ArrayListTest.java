import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrayListTest {
    @Test
    public void testAddElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        Assertions.assertEquals(1, list.size());
    }

    @Test
    public void testGetElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int listGet = list.get(0);
        Assertions.assertEquals(1, listGet);
    }

    @Test
    public void testRemoveElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.remove(0);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    public void testSetElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.set(0, 2);
        Assertions.assertEquals(2, list.get(0));
    }

    @Test
    public void testSizeElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
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
    public void testIndexOfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int checkIndex = list.indexOf(2);
        Assertions.assertEquals(1,checkIndex);
    }

}
