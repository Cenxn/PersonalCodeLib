import org.junit.Test;
import static org.junit.Assert.*;

public class ConcatContainTest {

    @Test
    public void testConcatContain1() {
        int expectedNumOfConcat = 2;
        assertEquals(expectedNumOfConcat, ConcatContain.concatContain("ab", "baba"));
    }

    @Test
    public void testConcatContain2() {
        assertEquals(-1, ConcatContain.concatContain("ab", "abcde"));
    }

    @Test
    public void testConcatContain3() {
        assertEquals(0, ConcatContain.concatContain("ab", "ab"));
    }

    @Test
    public void testConcatContain4() {
        // abca, bcab, cabc
        assertEquals(1,
                ConcatContain.concatContain("abc", "cabc"));
        assertEquals(1,
                ConcatContain.concatContain("abc", "abca"));
        assertEquals(1,
                ConcatContain.concatContain("abc", "bcab"));
        // abacad   ad abacad abacad ab
        assertEquals(3,
                ConcatContain.concatContain("abacad", "dabacadabacadab"));
        assertEquals(3,
                ConcatContain.concatContain("abacad", "adabacadabacadab"));

        assertEquals(-1,
                ConcatContain.concatContain("ab", "babqaba"));
        assertEquals(-1,
                ConcatContain.concatContain("ab", "baaabababa"));
        assertEquals(-1,
                ConcatContain.concatContain("ab", "babbbabababa"));
    }
}