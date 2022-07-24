package brucehoff.median;

public class Median2 {
    
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
	
	private static final int MAX_ITERS = 1000000;
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
			System.out.println("xmin: "+xmin+ " xmax: "+xmax+ " ymin: "+ymin+" ymax: "+ymax);
			if (xmax<xmin) {
				// we've gotten rid of all the x values and just return the median of y
				return median(y, ymin, ymax);
			}
			
			if (ymax<ymin) {
				// we've gotten rid of all the y values and just return the median of x
				return median(x, xmin, xmax);
			}
			
			int i = (xmax+xmin)/2;
			int j = (ymax+ymin)/2;
			if (x[i] >= y[j]) {
				// we know that x[i] and the values to its right are  >= than y[j] and the values to its left
				// so we can 'throw out' an equal number of x & y values from the 
				// top of the x range and the bottom of the y range
				int numToDiscard = Math.min(j-ymin+1, xmax-i+1);
				System.out.println("x[i] >= y[j].  numToDiscard="+numToDiscard);
				
				xmax -= numToDiscard; // TODO this could be i-1 which could be <0
				ymin += numToDiscard; // TODO this could be j+1 which could be m
			} else { // x[i] < y[j]
				int numToDiscard = Math.min(i-xmin+1, ymax-j+1);
				System.out.println("x[i] < y[j].  numToDiscard="+numToDiscard);
				
				xmin += numToDiscard;
				ymax -= numToDiscard;
			}
		}
		
		throw new IllegalStateException("Failed to converge after "+MAX_ITERS+" iterations.");
	}
	
    public static void main(String[] args) {
    }
    
}
