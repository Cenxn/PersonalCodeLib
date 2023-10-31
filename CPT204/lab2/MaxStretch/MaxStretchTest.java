import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaxStretchTest {

    @Test
    public void testSingleMaxStretch1() {
        List<Integer> list = Arrays.asList(8, 5, 1, 2, 3, 4, 5, 10);
        assertEquals(6, MaxStretch.maxStretch(list));
    }

    @Test
    public void testSingleMaxStretch2() {
        List<Integer> list = Arrays.asList(5, 2, 2, 5, 2);
        assertEquals(4, MaxStretch.maxStretch(list));
    }

    @Test
    public void testSingleMaxStretch3() {
        List<Integer> list = Arrays.asList(2, 7, 1, 2, 3, 7);
        assertEquals(5, MaxStretch.maxStretch(list));
    }

    @Test
    public void testSingleMaxStretch4() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        assertEquals(1, MaxStretch.maxStretch(list));
    }

    @Test
    public void testSingleMaxStretch5() {
        List<Integer> list = Arrays.asList();
        assertEquals(0, MaxStretch.maxStretch(list));
    }
	
    @Test
    public void testSingleOverlapMaxStretch() {
        List<Integer> list = Arrays.asList(2, 7, 1, 2, 3, 7);
        assertEquals(5, MaxStretch.maxStretch(list));
    }
    
	// add more test cases
	
	
	
}