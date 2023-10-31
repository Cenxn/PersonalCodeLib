import org.junit.Test;
import static org.junit.Assert.*;

public class LLDequeTest {

    @Test
    public void testCopyConstructor() {
        LLDeque<String> deque = new LLDeque<>();
        deque.addFirst("a");
        LLDeque<String> copyDeque = new LLDeque<>(deque);
        deque.addFirst("x");
        copyDeque.addFirst("y");
        assertEquals("x", deque.iterGet(0));
        assertEquals("a", deque.iterGet(1));
        assertEquals("y", copyDeque.iterGet(0));
        assertEquals("a", copyDeque.iterGet(1));
    }

    @Test
    public void testCopyConstructor2() {
        LLDeque<String> deque = new LLDeque<>();
        LLDeque<String> copyDeque = new LLDeque<>(deque);
        deque.addFirst("x");
        deque.addFirst("y");
        copyDeque.addFirst("y");
        assertEquals("x", deque.iterGet(1));
        assertEquals("y", copyDeque.iterGet(0));
        assertEquals("y", deque.iterGet(0));
    }
	
    @Test
    public void testNullIllegalArgument() {
        LLDeque<String> deque = new LLDeque<>();
        deque.addFirst("c");
        deque.addLegalFirst("b", "x");
        deque.addLegalFirst(null, "a");
        assertEquals("a", deque.iterGet(0));
        assertEquals("b", deque.iterGet(1));
        assertEquals("c", deque.iterGet(2));
    }
	
	
}
