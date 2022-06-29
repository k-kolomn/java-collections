import collections.Collection;
import collections.HashMap;
import collections.Map;
import collections.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void testPutCases() {
        HashMap<Integer, String> map = new HashMap<>(10);

        map.put(1,"Hollo");
        map.put(2,"Hollo");
        map.put(3,"Hollo");
        map.put(4,"Hollo");
        map.put(5,"Hollo");

        assertNotNull(map.get(1));
        assertNotNull(map.get(2));
        assertNotNull(map.get(3));
        assertNotNull(map.get(4));
        assertNotNull(map.get(5));

    }

    @Test
    public void testGet(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"Hollo");
        assertEquals("Hollo", map.get(1));
    }

    @Test
    public void testGetCases(){
        HashMap<Integer, String> map = new HashMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void testRemove(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"Hollo");
        var s = map.remove(1);
        assertEquals(0, map.size());
        assertEquals("Hollo", s);
    }

    @Test
    public void testRemoveCases(){
        HashMap<Integer, String> map = new HashMap<>();
        assertNull(map.remove(1));
    }

    @Test
    public void testReplace(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"HI");
        map.replace(1, "Afro-american");
        assertEquals(1, map.size());
        assertEquals("Afro-american", map.get(1));
    }
    @Test
    public void testReplaceCases(){
        HashMap<Integer, String> map = new HashMap<>();
        assertNull(map.replace(1, "h1"));
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
        Map<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8,"Darova");

        Set<Integer> result = map.keySet();

        assertEquals(3, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(8));
    }

    @Test
    public void testKeySetCases(){
        HashMap<Integer, String> map = new HashMap<>();
        var result = map.keySet();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testValues(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"Poka");
        map.put(8,"Darova");

        Collection<String> result = map.values();

        assertTrue(result.contains("HI"));
        assertTrue(result.contains("Poka"));
        assertTrue(result.contains("Darova"));
        assertEquals(3, result.size());
    }

    @Test
    public void testValuesCases(){
        HashMap<Integer, String> map = new HashMap<>();

        map.put(1,"HI");
        map.put(2,"HI");
        map.put(8,"HI");

        Collection<String> result = map.values();

        assertTrue(result.contains("HI"));
        assertFalse(result.contains("Poka"));
        assertEquals(3, result.size());

        result.remove("HI");
        result.remove("HI");
        result.remove("HI");

        assertTrue(result.isEmpty());
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
        map.put(8, null);
        map.put(8, null);
        map.put(8, null);
        map.put(8, null);
        map.put(8, null);
        map.putIfAbsent(8,"yaloh");

        assertEquals("yaloh", map.get(8));
        assertEquals(3, map.size());
    }
}
