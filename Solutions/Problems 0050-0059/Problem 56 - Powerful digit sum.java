/*	Problem 56 - Powerful digit sum:
  	
  	
	A googol (10^100) is a massive number: one followed by one-hundred zeros; 
	100^100 is almost unimaginably large: one followed by two-hundred zeros. 
	Despite their size, the sum of the digits in each number is only 1.
	
	Considering natural numbers of the form, a^b, where a, b < 100, what is the maximum digital sum?
*/

package euler;

import java.math.BigInteger;

public class projectEuler {
	public static void main (String [] args){
		
		long time = System.nanoTime();
		
		long maxDigitalSum = 0;
		
		for(int a = 2; a <= 100; a++) {
			for(int b = 2; b <= 100; b++) {
				BigInteger a1 = new BigInteger(Integer.toString(a));
				
				maxDigitalSum = Math.max(maxDigitalSum, calculateDigitalSum(a1.pow(b)));
			}
		}
		System.out.println(maxDigitalSum);
		
		
		time = System.nanoTime() - time;
		System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
	}
	
	public static long calculateDigitalSum(BigInteger a) {
		long sum = 0;
		
		String n = a.toString();
		
		for(int i = 0; i < n.length(); i++)
			sum += n.charAt(i) - '0';
		
		return sum;
	}
}
