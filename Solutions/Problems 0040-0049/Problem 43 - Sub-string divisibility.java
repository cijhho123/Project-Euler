/*	Problem 43 - Sub-string divisibility:
	The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9 in some order, but it also has a rather interesting sub-string divisibility property.

	Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
	
	d2d3d4 = 406 is divisible by 2
	d3d4d5 = 063 is divisible by 3
	d4d5d6 = 635 is divisible by 5
	d5d6d7 = 357 is divisible by 7
	d6d7d8 = 572 is divisible by 11
	d7d8d9 = 728 is divisible by 13
	d8d9d10= 289 is divisible by 17
	Find the sum of all 0 to 9 pandigital numbers with this property.
*/

package euler;

import java.util.ArrayList;
import java.util.List;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();
		
		char [] digits = {'0','1','2','3','4','5','6','7','8','9'};
		int  [] primes = {2,3,5,7,11,13,17};

		
		//generate all the pandigital permuttions and check from there
		
		List<String> list = new ArrayList<String>();
		generatePermutations(digits, list);
		
		long sum = 0;
		
		for(int i=0; i<list.size(); i++) {
			if(isDivisibleByPrimes(list.get(i), primes)) {
				sum += Long.parseLong(list.get(i));
			}
		}
			

		System.out.println("the sum is: "+sum);

		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}
	
	public static void generatePermutations (char[] digits, List<String>perms){
		//generate a list of all the permutation of s in sorted (lexicographic) order
		//I'm using this article as my algorithm:	
		//	https://www.quora.com/How-would-you-explain-an-algorithm-that-generates-permutations-using-lexicographic-ordering
		
		while(true) {
			
			//add the current permutation
			String temp = new String(digits);
			perms.add(temp);
	
			
			//Step 1: Find the largest x such that P[x]<P[x+1]. If there is no such x, P is the last permutation.)
			int largestX = -1;
			for(int index = 0; index < digits.length-1; index++) {
				if(digits[index] < digits[index+1])
					largestX = index;
			}
			
			if(largestX == -1) 
				return;	
			
			//Step 2: Find the largest y such that P[x]<P[y].
			int largestY = -1;
			for(int index = 0; index < digits.length; index++) {
				if(digits[largestX] < digits[index])
					largestY = index;
			}
			
			//Step 3: Swap P[x] and P[y].
			//System.out.println("x:"+largestX + " y:"+largestY);	--debug
			swap(digits, largestX, largestY);
			
			//Step 4: Reverse P[x+1 .. n].
			int i = 1;
			while(i < (digits.length - largestX+1)/2){
				swap(digits, i+largestX, digits.length - i);
				i++;
			}
		}
	}
	
	public static void swap(char [] a, int x1, int x2) {
		//a function that swaps 2 values in an array
		char temp = a[x1];
		a[x1] = a[x2];
		a[x2] = temp;
	}
	
	public static boolean isDivisibleByPrimes(String s, int[] primes) {
		//check if the leading digit is 0
		if(s.charAt(0) == '0')
			return false;
		
		
		//check if the number s satisfy all the conditions from the questions
		for(int i = 1; i <= primes.length; i++) {
			int currentNumber = Integer.parseInt(s.substring(i,i+3));
			if(currentNumber % primes[i-1] != 0)
				return false;
			
		}
		return true;
	}
}