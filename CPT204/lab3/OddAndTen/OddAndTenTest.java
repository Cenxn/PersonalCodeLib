import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class OddAndTenTest {
    
	@Test
    public void testOddAndTen(){
        assertTrue(OddAndTen.oddAndTen(Arrays.asList(5, 1, 5)));
        assertTrue(OddAndTen.oddAndTen(Arrays.asList(5, -1, -5)));
        assertTrue(OddAndTen.oddAndTen(Arrays.asList(5, -5, 1)));
        assertFalse(OddAndTen.oddAndTen(Arrays.asList()));
        assertFalse(OddAndTen.oddAndTen(Arrays.asList(5, 2, -5)));
        assertFalse(OddAndTen.oddAndTen(Arrays.asList(5, -5, 2)));
        assertFalse(OddAndTen.oddAndTen(Arrays.asList(5, 5, 4)));
        assertTrue(OddAndTen.oddAndTen(Arrays.asList(5, 5, 4, 1)));
        assertTrue(OddAndTen.oddAndTen(Arrays.asList(5, 5, 3)));
    }
}
