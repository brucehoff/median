package brucehoff.median;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MedianTest {

    @Test
    public void testSingleArrayMedian() {
        assertEquals(2, Median2.median(new int[] {1,2,10}, 0, 2));
        assertEquals(6, Median2.median(new int[] {1,2,10,11}, 0, 3));
        assertEquals(6, Median2.median(new int[] {-1, 0, 1,2,10,11, 100, 101}, 2, 5));
    }
    
    
//    @Test
//    public void testMedian1() {
//    	assertEquals(3, Median2.median(new int[] {1,2,3}, new int[] {3,4,5}));
//    }
//
//    @Test
//    public void testMedian2() {
//    	assertEquals(3, Median2.median(new int[] {1,2,3}, new int[] {4,5,6}));
//    }
//
    @Test
    public void testMedian3() {
    	assertEquals(3, Median2.median(new int[] {1,2,3}, new int[] {3,4,5,6}));
    }

    @Test
    public void testMedian4() {
    	assertEquals(4, Median2.median(new int[] {1,2,3}, new int[] {4,5,6,7}));
    }

}
