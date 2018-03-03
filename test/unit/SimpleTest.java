package unit;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * TODO(ei): document
 */
public class SimpleTest {
    @Test
    public void sum() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void string() {
        String str = "Hello world";
        assertFalse(str.isEmpty());
    }
}
