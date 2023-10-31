import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class HAMapTest {

    @Test
    public void testHAMap_1() {
        HAMap<String, Integer> map = new HAMap<>();
        assertFalse(map.containsKey("a"));
        map.put("a", 1);
        assertTrue(map.containsKey("a"));
        assertEquals(1, (int) map.get("a"));
        assertEquals(1, map.size());
        map.put("b", 2);
        map.put("c", 3);
        map.remove("a", 1);
        for (String key : map) {
            System.out.println("(" + key + ", " + map.get(key) + ")");
        }
        map.clear();
        assertEquals(0, map.size());
        assertFalse(map.containsKey("b"));
        assertFalse(map.containsKey("c"));
    }

    @Test
    public void testHAMap_2() {
        HAMap<String, Integer> map = new HAMap<>();
        assertFalse(map.containsKey("a"));
        map.put("a", 1);
        assertTrue(map.containsKey("a"));
        assertEquals(1, (int) map.get("a"));
        assertEquals(1, map.size());
        map.put("b", 2);
        map.put("c", 3);
        for (String key : map) {
            System.out.println("(" + key + ", " + map.get(key) + ")");
        }
        map.clear();
        assertEquals(0, map.size());
        assertFalse(map.containsKey("b"));
        assertFalse(map.containsKey("c"));
    }

    @Test
    public void testHAMap_3() {

        HAMap<Integer, String> map = new HAMap<Integer, String>(32);
        System.out.println(map.size());
        System.out.println(map.get(0));
        System.out.println("setsize=" + map.keySet().size());
        map.put(0, "a");
        System.out.println(map.get(0));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.put(0, "b");
        System.out.println(map.get(0));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        for (int i = 1; i <= 46; i++) {
            map.put(i, "x" + i);
        }
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
        map.put(47, "x" + 47);
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
        map.put(48, "x" + 48);
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
        map.put(49, "x" + 49);
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        System.out.println(map.remove(10, "x"));
        System.out.println(map.get(10));
        System.out.println(map.containsKey(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        System.out.println(map.remove(10, new String("x10")));
        System.out.println(map.get(10));
        System.out.println(map.containsKey(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.put(10, "abc");
        System.out.println(map.get(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.put(10, new String("abc"));
        System.out.println(map.get(10));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());

        map.clear();
        System.out.println(map.get(0));
        System.out.println(map.get(49));
        System.out.println(map.containsKey(0));
        System.out.println("size=" + map.size());
        System.out.println("nbuckets=" + map.getNumBuckets());
        System.out.println("setsize=" + map.keySet().size());
    }

//        0
//        null
//        setsize=0
//        a
//        size=1
//        nbuckets=32
//        setsize=1
//        b
//                size=1
//        nbuckets=32
//        setsize=1
//        size=47
//        nbuckets=32
//        setsize=47
//        size=48
//        nbuckets=32
//        setsize=48
//        size=49
//        nbuckets=64
//        setsize=49
//        size=50
//        nbuckets=64
//        setsize=50
//        null
//        x10
//        true
//        size=50
//        nbuckets=64
//        setsize=50
//        x10
//        null
//        false
//        size=49
//        nbuckets=64
//        setsize=49
//        abc
//                size=50
//        nbuckets=64
//        setsize=50
//        abc
//                size=50
//        nbuckets=64
//        setsize=50
//        null
//        null
//        false
//        size=0
//        nbuckets=64
//        setsize=0
}
