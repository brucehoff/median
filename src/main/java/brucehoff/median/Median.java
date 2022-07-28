package brucehoff.median;

import java.util.Arrays;

public class Median {
    
	/*
	 * return the median of a subrange of an ordered array
	 */
	public static double median(int[] in, int min, int max) {
		if (in.length==0) {
			throw new IllegalArgumentException();
		}
		if (min<0 || min>in.length-1) throw new IllegalArgumentException("invalid min");
		if (max<0 || max>in.length-1) throw new IllegalArgumentException("invalid max");
		if (min>max) throw new IllegalArgumentException("min cannot be greater than max");
		int i = (min+max)/2;
		if ((min+max) % 2 == 0) {
			// range min->max is odd
			return in[i];
		} else { // rand min->max is even
			return ((double)in[i] + (double)in[i+1])/2;
		}
	}
	
	// Although this uses methods like 'arraycopy' and 'sort'
	// it has O(1) time complexity since it only allows inputs
	// of a short maximum length
	public static double medianOfShortArrays(int[] x, int xstart, int xlength, int[] y, int ystart, int ylength) {
		if (xlength+ylength>6) throw new IllegalArgumentException("Input too long.");
		int[] combined = new int[xlength+ylength];
		System.arraycopy(x, xstart, combined, 0, xlength);
		System.arraycopy(y, ystart, combined, xlength, ylength);
		Arrays.sort(combined);
		return median(combined, 0, combined.length-1);
	}
	
	private static final int MAX_ITERS = 100;
	private static final int MINIMUM_ARRAY_LENGTH = 2;
	/*
	 * Find the median value in two ordered arrays
	 */
	public static double median(int[] x, int[] y) {
		
		if (x==null) throw new IllegalArgumentException("x can't be null.");
		if (y==null) throw new IllegalArgumentException("y can't be null.");
				
		int n = x.length;
		int m = y.length;
		
		if (n == 0 && m == 0) {
			throw new IllegalArgumentException("No values to find the median of.");
		}
		
		int xmin = 0; // the leftmost value in x that contributes to the median
		int xmax = n-1; // the rightmost value in x that contributes to the median
		int ymin = 0; // the leftmost value in y that contributes to the median
		int ymax = m-1; // the rightmost value in y that contributes to the median
		
		for (int c=0; c<MAX_ITERS; c++) {
			if (xmax<xmin) {
				// we've gotten rid of all the x values and just return the median of y
				return median(y, ymin, ymax);
			}
			
			if (ymax<ymin) {
				// we've gotten rid of all the y values and just return the median of x
				return median(x, xmin, xmax);
			}
			
			// if there are 6 or fewer values left, then we can wrap things up
			if ((xmax-xmin+1) + (ymax-ymin+1) <= 6) {
				return medianOfShortArrays(x, xmin, xmax-xmin+1, y, ymin, ymax-ymin+1);
			}

			// if the two arrays are of comparable length, we can eliminate O(n+m)
			// values from the left of one array and the right of the other
			int i = (xmax+xmin)/2;
			int j = (ymax+ymin)/2;
			if (x[i] >= y[j]) {
				// we know that x[i] and the values to its right are  >= than y[j] and the values to its left
				// so we can 'throw out' an equal number of x & y values from the 
				// top of the x range and the bottom of the y range
				int numToDiscard = Math.min(j-ymin+1, xmax-i+1);
				
				// mustn't encroach on the final two values in either array
				numToDiscard = Math.min(numToDiscard, xmax-xmin-MINIMUM_ARRAY_LENGTH+1);
				numToDiscard = Math.min(numToDiscard, ymax-ymin-MINIMUM_ARRAY_LENGTH+1);
				
				xmax -= numToDiscard;
				ymin += numToDiscard;
			} else { // x[i] < y[j]
				int numToDiscard = Math.min(i-xmin+1, ymax-j+1);
				// mustn't encroach on the final two values in either array
				numToDiscard = Math.min(numToDiscard, xmax-xmin-MINIMUM_ARRAY_LENGTH+1);
				numToDiscard = Math.min(numToDiscard, ymax-ymin-MINIMUM_ARRAY_LENGTH+1);
				
				xmin += numToDiscard;
				ymax -= numToDiscard;
			}
			
			
			// if one array is much longer than the other, we can
			// eliminate o(|n-m|) values from the left and right of the longer array
			// if the length difference is o(n+m) then the number eliminated
			// is o(n+m).
			if (xmax-xmin > ymax-ymin) {
				int halfToDiscardFromX = ((xmax-xmin)-(ymax-ymin)-1)/2;
				xmax -= halfToDiscardFromX;
				xmin += halfToDiscardFromX;
			} else {
				int halfToDiscardFromY = ((ymax-ymin)-(xmax-xmin)-1)/2;
				ymax -= halfToDiscardFromY;
				ymin += halfToDiscardFromY;
			}
		}
		
		throw new IllegalStateException("Failed to converge after "+MAX_ITERS+" iterations.");
	}
	
    public static void main(String[] args) {
    }
    
}
