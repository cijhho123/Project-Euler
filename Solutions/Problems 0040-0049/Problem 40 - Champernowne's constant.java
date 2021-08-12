/*	Problem 40 - Champernowne's constant:
	An irrational decimal fraction is created by concatenating the positive integers:

	0.123456789101112131415161718192021...

	It can be seen that the 12th digit of the fractional part is 1.

	If dn represents the nth digit of the fractional part, find the value of the following expression.

	d1 × d10 × d100 × d1000 × d10000 × d100000 × d1000000
*/
package euler;

import java.util.HashMap;

public class projectEuler {

	 public static void main(String[] args) {
		 long time = System.nanoTime();
		 
		 //save all the powers of 10
		 HashMap <Long, Integer> powersOf10 = new HashMap();
		 long n = 1;
		 while (n <= 1000000) {
			 powersOf10.put(n, 1);
			 n *= 10;
		 }
		 
		 //count digits
		 long charCount = 0;
		 int number = 0;
		 long product = 1;
		 
		 while(charCount <= 1000000) {
			 number ++;
			 String currentNumber = Integer.toString(number);
			 
			 for(int i = 0; i < currentNumber.length(); i++) {
				 charCount++;
 
				 //check if the digit is a power of 10
				 if(powersOf10.containsKey(charCount)) {
					 product *= (currentNumber.charAt(i) - '0');
					 System.out.println("digit #"+charCount+"	digit: "+currentNumber.charAt(i));
				 }
			 }
		 }
		 
		 System.out.println("the product is: "+product);

		 time = System.nanoTime() - time;
		 System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}

}
