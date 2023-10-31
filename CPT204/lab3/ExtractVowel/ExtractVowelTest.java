import org.junit.Test;
import static org.junit.Assert.*;

public class ExtractVowelTest {
    @Test
    public void testExtractVowels() {
        String str = "i love you 3000";
        assertEquals("ioeou", ExtractVowel.extractVowel(str));
        assertEquals("aiueo", ExtractVowel.extractVowel("aiueo"));
        assertEquals("", ExtractVowel.extractVowel(""));
        assertEquals("", ExtractVowel.extractVowel("123456"));
        assertEquals("aaaa", ExtractVowel.extractVowel("abcabcabcabc"));
    }
}
