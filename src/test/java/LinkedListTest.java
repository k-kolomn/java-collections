import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

    }
}
