import org.junit.Test;
import static org.junit.Assert.*;

public class CountBabaMamaTest {
	
	@Test
    public void testCountBabaMama(){
        assertEquals(0, CountBabaMama.countBabaMama(""));
        assertEquals(4, CountBabaMama.countBabaMama("babababamabamama"));
        assertEquals(2, CountBabaMama.countBabaMama("mamamaaaama"));
        assertEquals(3, CountBabaMama.countBabaMama("bababbbababa"));
        assertEquals(2, CountBabaMama.countBabaMama("aba babaa amama ma"));
        assertEquals(4, CountBabaMama.countBabaMama("bababamamama"));
    }
	
}
