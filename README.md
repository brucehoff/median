# brucehoff.median

This is a solution to the problem of computing the median
of a list of values contained in two sorted arrays.

The solution should have time complexity logarithmic in
the length of the combined lists.

https://leetcode.com/problems/median-of-two-sorted-arrays/

In a nutshell, the solution is:

Let 'n' be the length of the shorter array and 'm' be
the length of the longer array.  To solve the problem in o(log(n+m))
time we have to eliminate o(n+m) (say, half the total values) 
in each iteration.

If the lengths of the arrays are similar (m-n is small), 
we can eliminate o(n) from the left of one array and right
of the other array. The choice of left/right is determined
simply by comparing the values at the centers of the two arrays.

If the lengths of the arrays are very different (m-n is o(m+n)),
then we can eliminate about m-n/2 values (which is o(n+m)) from
the left and right side of the longer array.

Throughout we have to be careful not to throw out the middle
values which will determine the median value.

