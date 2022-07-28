package brucehoff.median;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MedianTest {

    @Test
    public void testSingleArrayMedian() {
        assertEquals(2, Median.median(new int[] {1,2,10}, 0, 2));
        assertEquals(6, Median.median(new int[] {1,2,10,11}, 0, 3));
        assertEquals(6, Median.median(new int[] {-1, 0, 1,2,10,11, 100, 101}, 2, 5));
    }
    
    
    @Test
    public void testMedian1() {
    	assertEquals(3, Median.median(new int[] {1,2,3}, new int[] {3,4,5}));
    }

    @Test
    public void testMedian2() {
    	assertEquals(3.5d, Median.median(new int[] {1,2,3}, new int[] {4,5,6}));
    }

    @Test
    public void testMedian3() {
    	assertEquals(3, Median.median(new int[] {1,2,3}, new int[] {3,4,5,6}));
    }

    @Test
    public void testMedian4() {
    	assertEquals(4, Median.median(new int[] {1,2,3}, new int[] {4,5,6,7}));
    	assertEquals(4, Median.median(new int[] {4,5,6,7}, new int[] {1,2,3}));
    }

    @Test
    public void testMedian5() {
    	assertEquals(4, Median.median(new int[] {0,2,4,6,8}, new int[] {1,3,5,7}));
    }

}
