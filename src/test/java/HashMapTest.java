import collections.HashMap;
import collections.LinkedList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapTest {

    @Test
    public void testPut() {
        HashMap<Integer, Integer> map = new HashMap<>();

        map.put(1,3);
        map.put(2,1);
        map.put(8,5);

       assertEquals(3, map.size());
    }

    @Test
    public void testGet(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");

      assertEquals(map.get(1), "Hollo");
    }

    @Test
    public void testRemove(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");

        assertEquals(2, map.size());
        System.out.println(" ");
    }
}
