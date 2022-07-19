import org.junit.Test;

import static org.junit.Assert.*;

public class ModNCounterTest {

    @Test
    public void increment() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        assertEquals(1, c.value());
        c.increment();
        assertEquals(2, c.value());
    }

    @Test
    public void reset() {
        ModNCounter c = new ModNCounter(2);
        c.increment();
        c.reset();
        assertEquals(0, c.value());
    }

    @Test
    public void testConstructor() {
        ModNCounter c = new ModNCounter(2);
        assertEquals(0, c.value());
    }

}