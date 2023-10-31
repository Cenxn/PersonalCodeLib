import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class CountRunsTest {

    @Test
    public void testCountRuns(){
        List<Integer> test1 = Arrays.asList(1,2,2,2,3);
        assertEquals(1, CountRuns.countRuns(test1));

        List<Integer> test2 = Arrays.asList(1, 1, 2, 3, 4, 5, 5);
        assertEquals(2, CountRuns.countRuns(test2));

        List<Integer> test3 = Arrays.asList(1);
        assertEquals(0, CountRuns.countRuns(test3));

        List<Integer> test4 = Arrays.asList(1,2,3,4,5);
        assertEquals(0, CountRuns.countRuns(test4));

        List<Integer> test5 = Arrays.asList(1,1,1,2,2,3,5,3,5);
        assertEquals(2, CountRuns.countRuns(test5));

        List<Integer> test6 = Arrays.asList();
        assertEquals(0, CountRuns.countRuns(test6));

        List<Integer> test7 = Arrays.asList(-1,-1,2,3,4,5,1,1);
        assertEquals(2, CountRuns.countRuns(test7));

        List<Integer> test8 = Arrays.asList(-1,2,2,2,3,4,4,5,5);
        assertEquals(3, CountRuns.countRuns(test8));

        List<Integer> test9 = Arrays.asList(1,1);
        assertEquals(1, CountRuns.countRuns(test9));

        List<Integer> test10 = Arrays.asList(1,2);
        assertEquals(0, CountRuns.countRuns(test10));
    }
}