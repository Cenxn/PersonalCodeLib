import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

public class EqualSumTest {
    @Test
    public void testTrueEqualSum() {
        List<Integer> list = Arrays.asList(0,0);
        assertEquals(true, EqualSum.equalSum(list));

        List<Integer> list2 = Arrays.asList(2,3,3,4,2,4);
        assertEquals(true, EqualSum.equalSum(list2));
    }
    @Test
    public void testFalseEqualSum() {
        List<Integer> list = Arrays.asList(2, 2, 5);
		assertEquals(false, EqualSum.equalSum(list));
	}
    @Test
    public void testEqualSum() {
        List<Integer> list = Arrays.asList();
        assertEquals(true, EqualSum.equalSum(list));
    }

    @Test
    public void testEqualSum2() {
        List<Integer> list = Arrays.asList(2);
        assertEquals(false, EqualSum.equalSum(list));
    }
	
	
	
}
