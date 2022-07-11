import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanSetTest {

    @Test
    public void testBasics() {
        BooleanSet aSet = new BooleanSet(100);
        assertEquals(0, aSet.size());
        for (int i = 0; i < 100; i += 2) {
            aSet.add(i);
            assertTrue(aSet.contains(i));
        }
        assertEquals(50, aSet.size());

        for (int i = 0; i < 100; i += 2) {
            aSet.remove(i);
            assertFalse(aSet.contains(i));
        }
        assertTrue(aSet.isEmpty());
        assertEquals(0, aSet.size());
    }

    @Test
    public void testByMe() {
        BooleanSet aSet = new BooleanSet(100);
        aSet.add(12);
        assertTrue(aSet.contains(12));
        assertEquals(1, aSet.size());
        aSet.add(12);
        assertEquals(1, aSet.size());
        aSet.remove(12);
        assertEquals(0, aSet.size());
        for (int i = 0; i < 10; i += 2) {
            aSet.add(i);
        }
        assertEquals(2, aSet.toIntArray()[1]);
        assertEquals(4, aSet.toIntArray()[2]);
    }
}