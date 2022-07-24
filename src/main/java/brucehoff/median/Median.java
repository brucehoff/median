package brucehoff.median;

public class Median {
    
	
	/*
	 * return the median of an ordered array
	 */
	public static double median(int[] in) {
		if (in.length==0) {
			throw new IllegalArgumentException();
		}
		int i = in.length/2 - 1;
		if (in.length % 2 == 0) {
			// even
			return ((double)in[i] + (double)in[i+1])/2;
		} else {
			return in[i+1];
		}
	}
	
	/*
	 * Find the median value in two ordered arrays
	 */
	public static double median(int[] x, int[] y) {
		
		if (x==null) throw new IllegalArgumentException("x can't be null.");
		if (y==null) throw new IllegalArgumentException("y can't be null.");
		
		// the logic below is simpler if we know that x is never longer than y
		if (x.length>y.length) {
			int[] temp = x;
			x = y;
			y = temp;
		}
		
		int n = x.length;
		int m = y.length;
		
		if (n == 0 && m == 0) {
			throw new IllegalArgumentException("No values to find the median of.");
		}
		if (n == 0) {
			return median(y);
		}
		if (m == 0) {
			return median(x);
		}
		
		// now we know we have two non empty arrays
		int xmin = 0; // the leftmost value in x which can be the median
		int xmax = n-1; // the rightmost value in x which can be the median
		int ymin = 0; // the leftmost value in y which can be the median
		int ymax = m-1; // the rightmost value in y which can be the median
		
		
		if (((n+m) % 2) == 1) { // n+m is odd
			int j = (m-n-1)/2;
			// j is a valid index into y iff j>=0 and j<m
			// since m>n and m+n is odd, m>=n+1
			// so m-n>=1
			// or m-n-1>=0
			// or (m-n-1)/2>=0
			// so the lower bound is satisfied
			// Since n>0, clearly the upper bound is satisifed
			int k = (m+n-1)/2;
			// k is a valid index into y iff k>=0 and k<m
			// TODO Prove it
			if (x[xmax]<=y[j]) {
				// then all (n) of x and j of y are <= of y[j]
				// while m-1 - j of y are >= y[j]
				// but these two are equal:
				// n+j ?== m-1-j
				// n+(m-n-1)/2 ?== m-1-(m-n-1)/2
				// (2n+m-n-1)/2  ?== (2m-2-m+n+1)/2
				// (m+n-1)/2 == (m+n-1)/2
				// so y[j] is the median
				return y[j];
			} else if (x[0]>=y[k]) {
				// then all (n) of x and m-1-k of y are >= y[k]
				// TODO prove y[k] is the median
				return y[k];
			} else {
				// we now know that xmax will never "overrun" n-1
				// i.e. x[n-1] will 
			}
		} else { // n+m even
			int j = (m-n)/2-1;
			int k = (m+n)/2-1;
			if (x[xmax]<=y[j]) {
				return 0.5d * (y[j]+y[j+1]);
			} else if (x[0]>=y[k+1]) {
				return 0.5d * (y[k]+y[k+1]);
			} else {
				// TODO The remaining cases should help below when
				// we need to update the min and max values				
			}
		}
		
		
		// below, i is the index into x and j is in the index into y
		// the # of values left of x[i] = i
		// the # of values left of y[j] = j
		// the # of value left of x[i] and y[j] = i+j
		// the # of values right of x[i] = n-1-i
		// the # of values right of y[j] = m-1-j
		// the # of values right of x[i] and y[j] = n+m-2 - (i+j)
		
		int i = n / 2 - 1;
		int iplusj = (n+m)/2-1; // i+j always sum to this
		// if n+m is even then the # of values right of x[i] and y[j]: 
		// = n+m-2 - (i+j)
		// = n+m-2 - (n+m)/2+1
		// = (n+m)/2 - 1
		// = iplusj, i.e. the # of values left of x[i] and y[j]
		//
		// if n+m is odd then
		// iplusj = (n+m)/2 - 0.5 - 1, since integer division 'rounds down'
		// if n+m is odd then # values right of x[i] and y[j]:
		// = n+m-2 - (i+j)
		// = n+m-2 - [(n+m)/2 - 0.5 - 1]
		// = n+m-2 - (n+m)/2 + 0.5 + 1
		// = (n+m)/2 - 0.5
		// = iplusj + 1
		// i.e. the # of values right of x[i] and y[j] are one more than the number of values to the left
		
		int j = iplusj - i;
		
		while (true) {
			if (x[i] == y[j]) {
				// If n+m is even, the median is the average of x[i] & y[j]
				// which is just x[i], since they're the same.
				// If n+m is odd, then the median is also this value
				// since the number left is just one less than the number right, and
				// one of x[i] and y[j] can be 'added to the left tally', leaving 
				// the other in the center.
				return x[i];
			} else if (x[i] > y[j]) {
				// the number of values left of x[i] in an imaginary, combined array are:
				//	1 (for y[j]) plus
				//	i (all values left of i in the x array) plus
				//	j (all the values left of j in the y array)
				//	0 or more values right of j
				// >= 1+i+j
				// If n+m is even:
				// 	1+i+j = 1 + (n+m)/2-1 = (n+m)/2
				// if n+m is odd:
				// 	1+i+j = 1 + (n+m)/2 - 0.5 - 1 = (n+m)/2 - 0.5
				// 	So the number of values left of x[i] would be:
				// 	>=(n+m)/2 or >= (n+m)/2 - 0.5
				// Any value *right* of x[i] would have more than half of the
				// values to its left and so cannot be the median.
				//
				// By similar logic, no value to the *left* of y[j] can
				// be the median value of the combined arrays.
				
				xmax = i;
				ymin = j;
			} else { // x[i] < y[j]
				ymax = j;
				xmin = i;
			}
			
			// TODO This is right for even n+m.  What about odd?
			if (xmax <= xmin && ymax <= ymin) {
				return ((double)x[xmin]+(double)y[ymin])/2.0;
			}
			
			// we want to keep halving the xmin-xmax range until xmin=xmax
			int deltai = (xmax-xmin)/2;
			
			// TODO how do we update i?
			j = iplusj - i;  // we always do this, to guarantee that i+j is constant
			// TODO what if j<0 or j>m-1?  This can happen when n>m, so i 'wants' to make
			// a bigger adjustment than j can make
		}
	}
	
    public static void main(String[] args) {
    }
    
}
