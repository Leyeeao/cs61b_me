import org.junit.Test;

import static org.junit.Assert.*;

public class MeasurementTest {

    @Test
    public void getFeet() {
        Measurement m = new Measurement(1,2);
        assertEquals(1, m.getFeet());
    }
    @Test
    public void getInches() {
        Measurement m = new Measurement(1,2);
        assertEquals(2, m.getInches());
    }

    @Test
    public void plus() {
        Measurement m1 = new Measurement(1,8);
        Measurement m2 = new Measurement(3,9);
        //assertEquals(5, m1.plus(m2).getFeet());
        assertEquals(5, m1.plus(m2).getInches());
    }

    @Test
    public void minus() {
        Measurement m1 = new Measurement(3,4);
        Measurement m2 = new Measurement(1,5);
        assertEquals(1, m1.minus(m2).getFeet());
        //assertEquals(11, m1.minus(m2).getInches());
    }

    @Test
    public void multiple() {
        Measurement m1 = new Measurement(1,5);
        Measurement m2 = new Measurement(4,3);
        assertEquals(4, m1.multiple(3).getFeet());
        //assertEquals(3, m1.multiple(3).getInches());
    }

/*    @Test
    public void toString() {
        Measurement m1 = new Measurement(1,5);
        String res = m1.toString();
        String exp = 1 + "\'" + 5 + "\"";
        assertEquals(exp, res);
    }*/

    // TODO: Add additional JUnit tests for Measurement.java here.

}