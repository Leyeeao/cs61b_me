import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class AListTest {

    @Test
    public void testFor() {
        AList<Integer> a = new AList<>();
        a.addLast(1);
        a.addLast(2);
        a.addLast(3);
        int count = 0;
        //for (Integer i : a) {
        //    count += i;
        //}
        Iterator<Integer> iter = a.iterator();
        while(iter.hasNext()) {
            count += iter.next();
        }
        assertEquals(6, count);
    }
}
