import collections.ArrayList;
import collections.LinkedList;
import collections.LinkedSet;
import collections.List;
import org.assertj.core.internal.bytebuddy.dynamic.scaffold.MethodGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.net.Inet4Address;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class LinkedSetTest {


    @Test
    public void testAdd(){
        LinkedSet<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(1);

     assertEquals(1, set.size());
    }

    @Test
    public void testContains(){
        LinkedSet<Integer> set = new LinkedSet<>();

        set.add(1);
        set.add(2);

        assertTrue(set.contains(2));
    }
    @Test
    public void testRemove(){
        LinkedSet<Integer> set = new LinkedSet<>();
        set.add(1);
        set.add(2);
        set.remove(1);
        Assertions.assertEquals(1, set.size());
    }

    @Test
    public void testRemoveElementThatDoesNotExist() {
        LinkedSet<Integer> set = new LinkedSet<>();
        assertThatThrownBy(() -> set.remove(10));
    }

    @Test
    public void testRemoveIf(){
        LinkedSet<Integer> set = new LinkedSet<>();
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
        LinkedSet<Integer> set = new LinkedSet<>();
        set.add(2);
        set.add(1);
        set.add(4);
        set.add(3);

        set.clear();
        assertEquals(0, set.size());

    }

    @Test
    public void testCopy(){
        LinkedSet<Integer> set = new LinkedSet<>();
        set.add(2);
        set.add(1);
        set.add(4);
        set.add(3);

        LinkedSet<Integer> setCopy = (LinkedSet<Integer>) set.copy();

        assertEquals(set.size(), setCopy.size());
    }
}
