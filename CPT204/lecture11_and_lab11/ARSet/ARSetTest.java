import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ARSetTest {

    @Test
    public void testARSetEquals_1() {
        ARSet<String> set1 = new ARSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        ARSet<String> set2 = new ARSet<>();
        set2.add("b");
        set2.add("c");
        set2.add("a");
        assertEquals(set1, set2);
        set2.add("d");
        assertNotEquals(set1, set2);
    }
	
	// Add your own test cases below
    @Test
    public void testARSetEquals_2() {
        ARSet<String> set1 = new ARSet<>();
        ARSet<String> set2 = new ARSet<>();
        assertEquals(set1, set2);
        set2.add("d");
        assertNotEquals(set1, set2);
    }

    @Test
    public void testARSetEquals_3() {
        ARSet<String> set1 = new ARSet<>();
        set1.add("d");
        ARSet<String> set2 = new ARSet<>();
        assertNotEquals(set1, set2);
        set2.add("d");
        assertEquals(set1, set2);
    }

    @Test
    public void testARSetEquals_4() {
        Set<String> set = new HashSet<>();
        set.add("d");
        ARSet<String> set2 = new ARSet<>();
        set2.add("d");
        assertNotEquals(set, set2);
    }
	
}
