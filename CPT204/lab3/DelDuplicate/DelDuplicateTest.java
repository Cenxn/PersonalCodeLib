import org.junit.Test;
import static org.junit.Assert.*;

public class DelDuplicateTest {
	
	@Test
    public void testDelDuplicate(){
        assertEquals("", DelDuplicate.delDuplicate(""));
        assertEquals("abc", DelDuplicate.delDuplicate("aabbcc"));
        assertEquals("a", DelDuplicate.delDuplicate("aaaaaaa"));
        assertEquals("abca", DelDuplicate.delDuplicate("aabbca"));
        assertEquals("abc", DelDuplicate.delDuplicate("aaabbc"));
    }
    
}
