import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SkipSumTest {
    @Test
    public void testSkipSum(){
        assertTrue(SkipSum.skipSum(Arrays.asList(2, 1, 5), 7));
        assertTrue(SkipSum.skipSum(Arrays.asList(2, 1, 5), 1));
        assertTrue(SkipSum.skipSum(Arrays.asList(), 0));
        assertFalse(SkipSum.skipSum(Arrays.asList(), 1));
        assertTrue(SkipSum.skipSum(Arrays.asList(2, 5, 10, 6), 12));
        assertFalse(SkipSum.skipSum(Arrays.asList(2, 5, 10, 6), 7));
        assertFalse(SkipSum.skipSum(Arrays.asList(2, 5, 10, 6), 16));
    }
}
