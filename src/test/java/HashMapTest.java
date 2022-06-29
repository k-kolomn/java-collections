import collections.ArraySet;
import collections.HashMap;
import collections.LinkedList;
import collections.Set;
import org.assertj.core.internal.bytebuddy.dynamic.scaffold.MethodGraph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
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

        assertEquals("Hollo", map.get(1));
    }

    @Test
    public void testGetCases(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");

        assertThatThrownBy(() -> map.get(3));

    }

    @Test
    public void testRemove(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");
        var s = map.remove(1);

        assertEquals(2, map.size());
        assertEquals("Hollo", s);
        System.out.println(" ");
    }

    @Test
    public void testRemoveCases(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");

        assertThatThrownBy(() -> map.remove(3));
    }

    @Test
    public void testReplace(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8,"Darova");

        map.replace(1, "Niger");
        assertEquals(3, map.size());
        assertEquals("Niger", map.get(1));
    }
    @Test
    public void testReplaceCases(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");

        assertThatThrownBy(() -> map.replace(3, "h1"));

    }
    @Test
    public void testReplaceAll() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1, "HI");
        map.put(2, "Poka");
        map.put(8, "Darova");

        map.replaceAll((k,v) -> "1");
        assertEquals("1", map.get(1));
        assertEquals("1", map.get(2));
        assertEquals("1", map.get(8));

    }

    @Test
    public void testKeySet(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8,"Darova");

        ArraySet<Integer> result = (ArraySet<Integer>) map.keySet();

        assertEquals(3, result.size());
        assertTrue(result.contains(1));
    }

    @Test
    public void testKeySetCases(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(8,"Hollo");


    }

    @Test
    public void testValues(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8,"Darova");

        LinkedList<String> result = (LinkedList<String>) map.values();

        assertTrue(result.contains("HI"));
        assertEquals(3, result.size());
    }

    @Test
    public void testGetOrDefault() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8,"Darova");

        var get1 = map.getOrDefault(1,"privet");
        var get2 = map.getOrDefault(3,"privet");

        assertEquals("HI", get1);
        assertEquals("privet", get2);
    }

    @Test
    public void testPutIfAbsent() {
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8, null);
        map.putIfAbsent(8,"yaloh");

        assertEquals("yaloh", map.get(8));
        assertEquals(3, map.size());
    }
}
