import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ArrayListTest {
    @Test
    public void testAddElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
//        assertThatThrownBy(() -> {
//            list.add(10);
//            list.add(11);
//        });
    }

    @Test
    public void testGetElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int listGet = list.get(0);
        Assertions.assertEquals(1, listGet);
        assertThatThrownBy(() -> {
            list.get(10);
            list.get(11);
        });
    }

    @Test
    public void testRemoveElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.remove(0);
        Assertions.assertEquals(1, list.size());
        assertThatThrownBy(() -> {
            list.remove(10);
            list.remove(11);
        });
    }

    @Test
    public void testSetElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.set(0, 2);
        Assertions.assertEquals(2, list.get(0));
        assertThatThrownBy(() -> {
            list.set(10, 10);
            list.set(11, 11);
        });
    }

    @Test
    public void testSizeElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        Assertions.assertEquals(2, list.size());
//        assertThatThrownBy(() -> {
//            list.size();
//            list.size();
//        });
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
//        assertThatThrownBy(() -> {
//            list.removeFirst(10);
//            list.removeFirst(11);
//        });
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
//        assertThatThrownBy(() -> {
//            list.removeLast(10);
//            list.removeLast(11);
//        });
    }

    @Test
    public void testIndexOfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int checkIndex = list.indexOf(2);
        Assertions.assertEquals(1, checkIndex);
        assertThatThrownBy(() -> {
            list.indexOf(10);
            list.indexOf(11);
        });
    }

    @Test
    public void testIsEmptyElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        boolean isEmpty = list.isEmpty();
        Assertions.assertEquals(false, isEmpty);
//        assertThatThrownBy(() -> {
//            list.isEmpty();
//            list.isEmpty();
//        });
    }

    @Test
    public void testContainsElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        boolean contains = list.contains(1);
        Assertions.assertEquals(true, contains);
        assertThatThrownBy(() -> {
            list.contains(2);
            list.contains(3);
        });
    }

    @Test
    public void testClearElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.clear();
        Assertions.assertEquals(0, list.size());
//        assertThatThrownBy(() -> {
//            list.clear();
//            list.clear();
//        });
    }

    @Test
    public void testChangeAllElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        boolean changeAll = list.changeAll();
//        Assertions.assertEquals(, changeAll);
//        assertThatThrownBy(() -> {
//
//        });
    }

    @Test
    public void testChangeIfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        boolean changeIf = list.changeIf();
//        Assertions.assertEquals(, changeIf);
//        assertThatThrownBy(() -> {
//
//        });
    }

    @Test
    public void testRemoveIfElement() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
//        boolean removeIf = list.removeIf();
//        Assertions.assertEquals(, removeIf);
//        assertThatThrownBy(() -> {
//
//        });
    }
}
