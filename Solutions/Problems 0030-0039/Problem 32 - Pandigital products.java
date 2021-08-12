/*	Problem 32 - Pandigital products:
	We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once;
	for example, the 5-digit number, 15234, is 1 through 5 pandigital.

	The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and product is 1 through 9 pandigital.

	Find the sum of all products whose multiplicand/multiplier/product identity can be written as a 1 through 9 pandigital.

	HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
*/
package euler;

import java.util.ArrayList;
import java.util.List;

public class projectEuler {
	
	public static void main (String [] args){
		long time = System.nanoTime();
		
		List <Integer> numbers = new ArrayList <Integer>();
		int sum = 0;
		
		//check for case X * XXXX = XXXX (9 digits total)
		for(int a = 1; a < 10; a++) {
			for(int b = 1000; b < 2000; b++) {
				int c = a * b;
				if(isPandigitalProducts(a, b, c) && !numbers.contains(c)) {
					numbers.add(c);
					sum += c;
				}
			}
		}
		
		//check for case XX * XXX = XXXX (9 digits total)
		for(int a = 10; a < 100; a++) {
			for(int b = 100; b < 1000; b++) {
				int c = a * b;
				if(isPandigitalProducts(a, b, c) && !numbers.contains(c)) {
					numbers.add(c);
					sum += c;
				}
			}
		}
		
		System.out.println("sum: "+sum);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
	
	public static boolean isPandigitalProducts (int n1, int n2, int n3) {
		
		int [] digits = new int [10];	//1-9
		
		//set the digits
		checkDigits(n1, digits);
		checkDigits(n2, digits);
		checkDigits(n3, digits);
		
		//check if there's exactly 1 of each digit from 1 to 9, and no 0s
		if(digits[0] != 0)
			return false;
		
		for(int i = 1; i <= 9; i++)
			if(digits[i] != 1)
				return false;
		
		return true;
	}
	
	public static void checkDigits (int n, int [] digits) {
		while (n > 0) {
			digits[n % 10] ++;
			n /= 10;
		}
	}
}
