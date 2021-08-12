/*	Problem 24 - Lexicographic permutations:
	A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4. 
	If all of the permutations are listed numerically or alphabetically, we call it lexicographic order. The lexicographic permutations of 0, 1 and 2 are:

	012   021   102   120   201   210

	What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?

*/
package euler;

public class projectEuler {
	public static void main (String [] args) {

		// mathematical approach
		/*
		 	There are 10! permutation.
		 	let's find the millionth lexicographic permutation:
		 	
		 	10! = 3,628,800
		 	
		 	part 1: finding the most significant digit
		 	dividing 3,628,800 into 10 sections we get 362,880 (9!)
		 	so there are 362,880 permutations starting with each digit (0-9)
		 	
		 	we are looking for the 1,000,000th permutation,
		 	so we'll do [1,000,000 / 362,880] = 3 		----> [] is the ceiling functions
		 	the third digit is 2 (0,1,2), so the number begin with 2.
		 	
		 	so the range is 725,760 - 1,088,640
		 	2,XXX,XXX,XXX
		 	
		 	
		 	part 2: finding the second most significant digit
		 	dividing the range into 9 sections (0-9 without 2)
		 	362,880 / 9 = 40,320 (8!)
		 	
		 	1,088,640 - 40,320 * x => 1,000,000 
		 	x = 7
		 	because we need the millionth permutation we are in the seventh section
		 	
		 	so the range is 967,680 - 1,008,000
		 	2,7XX,XXX
		 	
		 	part 3: finding the third digit
		 	dividing the range into 8 sections (0-9 without 2,7)
		 	40,320 / 8 = 5,040 (7!)
		 	
		 	1,008,000 - 5,040 * x => 1,000,000 
		 	x = 8
		 	
		 	so the range is 997,920 - 1,002,960
		 	2,78X,XXX
		 	
		 	continueing the pattern we get 2,783,915,460
		 	yay!
		 */
		
	}
}
