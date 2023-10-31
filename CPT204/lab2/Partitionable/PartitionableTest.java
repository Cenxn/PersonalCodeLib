import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class PartitionableTest {

    @Test
    public void testPartitionable() {
        List<Integer> list1 = Arrays.asList(1, 1, 1, 2, 1);
        assertTrue(Partitionable.isPartitionable(list1));

        List<Integer> list2 = Arrays.asList(0);
        assertTrue(Partitionable.isPartitionable(list2));

        List<Integer> list3 = Arrays.asList();
        assertTrue(Partitionable.isPartitionable(list3));

        List<Integer> list4 = Arrays.asList(-1,1,0);
        assertTrue(Partitionable.isPartitionable(list4));

        List<Integer> list5 = Arrays.asList(2, -5, 3);
        assertTrue(Partitionable.isPartitionable(list5));

        List<Integer> list6 = Arrays.asList(-1, -2, 1, 2);
        assertTrue(Partitionable.isPartitionable(list6));

    }
	
    @Test
    public void testNotPartitionable() {
        List<Integer> list1 = Arrays.asList(2, 1, 1, 2, 1);
        assertFalse(Partitionable.isPartitionable(list1));

        List<Integer> list2 = Arrays.asList(-1, 1, 1, 2, 1);
        assertFalse(Partitionable.isPartitionable(list2));

        List<Integer> list3 = Arrays.asList(-1, -1, 2, 1);
        assertFalse(Partitionable.isPartitionable(list3));
    }
	
	// add more test cases
	
}