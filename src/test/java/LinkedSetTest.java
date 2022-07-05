import collections.LinkedSet;
import collections.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class LinkedSetTest {


    @Test
    public void testAdd(){
        Set<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(1);

     assertEquals(1, set.size());
    }

    @Test
    public void testContains(){
        Set<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(2);

        assertTrue(set.contains(2));
    }
    @Test
    public void testRemove(){
        Set<Integer> set = new LinkedSet<>();
        set.add(1);
        set.add(2);
        set.remove(1);
        Assertions.assertEquals(1, set.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        Set<Integer> set = new LinkedSet<>();
        assertFalse(set.remove(10));
    }

    @Test
    public void testRemoveIf(){
        Set<Integer> set = new LinkedSet<>();
        set.add(1);
        set.add(2);
        set.add(3);

        set.removeIf(
                i -> i == 3
        );
        Assertions.assertEquals(2, set.size());
    }

    @Test
    public void testClear(){
        Set<Integer> set = new LinkedSet<>();
        set.add(2);
        set.add(1);
        set.add(4);
        set.add(3);

        set.clear();
        assertEquals(0, set.size());

    }

    @Test
    public void testCopy(){
        Set<Integer> set = new LinkedSet<>();
        set.add(2);
        set.add(1);
        set.add(4);
        set.add(3);

        Set<Integer> setCopy = (LinkedSet<Integer>) set.copy();

        assertEquals(set.size(), setCopy.size());
    }
}
