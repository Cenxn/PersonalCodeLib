import org.junit.Test;

import static java.util.Map.entry;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class MatchSwapTest {
	@Test
    public void testMatchSwap1(){
        List<String> list = Arrays.asList("apple", "avocado");
        List<String> expected = Arrays.asList("avocado", "apple");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testMatchSwap2(){
        List<String> list = Arrays.asList("ab", "ac", "ad", "ae", "af");
        List<String> expected = Arrays.asList("ac", "ab", "ae", "ad", "af");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testMatchSwap3(){
        List<String> list = Arrays.asList();
        List<String> expected = Arrays.asList();
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testMatchSwap4(){
        List<String> list = Arrays.asList("aa");
        List<String> expected = Arrays.asList("aa");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }

    @Test
    public void testMatchSwap5(){
        List<String> list = Arrays.asList("aa", "ab", "ab", "ab");
        List<String> expected = Arrays.asList("ab", "aa", "ab", "ab");
        assertEquals(expected, MatchSwap.matchSwap(list));
    }
	
}