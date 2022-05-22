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
    }

    @Test
    public void testAddNullElement() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.add(null));
    }

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
        assertThatThrownBy(() -> list.remove(1));
    }

    @Test
    public void testRemoveFirstElementParamEqNull() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.removeFirst(null));
    }

    @Test
    public void testRemoveLastElementParamEqNull() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThatThrownBy(() -> list.removeLast(null));
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
        Assertions.assertFalse(isEmpty);
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
        Assertions.assertTrue(contains);
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

        list.changeAll(i -> i + 1);

        Assertions.assertEquals(2, list.get(0));
        Assertions.assertEquals(3, list.get(1));
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
