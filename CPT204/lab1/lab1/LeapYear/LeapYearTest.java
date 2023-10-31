import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class LeapYearTest {

    @Test
	public void testLeapYear() {
        assertEquals(true, LeapYear.isLeapYear(-4));
    }
    
}