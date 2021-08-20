/*	Problem 57 - Square root convergents:

	It is possible to show that the square root of two can be expressed as an infinite continued fraction.
		sqrt(2) = 1 + 1 / ( 2 + 1 / (2 + 1 / ( 2 + 1 / .... 
		
	By expanding this for the first four iterations, we get:
		1 + 1 / 2 = 3/2 = 1.5
		1 + 1 / (2 + (1 / 2)) = 7/5 = 1.4
		1 + 1 / (2 + (1 / (2 + 1 / 2))) = 17/12 = 1.41666..
		1 + 1 / (2 + (1 / (2 + 1 / (2 + 1 / 2)))) = 41/29 = 1.41379....
	
	The next three expansions are 99/70, 239/169, and 577/408
	
	but the eighth expansion, 1393/985 , is the first example where 
	the number of digits in the numerator exceeds the number of digits in the denominator. 
	
	In the first one-thousand expansions, 
	how many fractions contain a numerator with more digits than the denominator?
*/

package euler;

import java.math.BigInteger;

public class projectEuler {
	public static void main (String [] args){
		
		long time = System.nanoTime();
		
		/*	My approach is purely mathematical:
		 	Instead of naively calculating nested fraction i will use the fact that
		 	where:
			the numerators are half of the Pell-Lucas numbers, Qn / 2
			the denominators are the Pell numbers Pn
			starting from (Q1 / 2) / P1
		 
		 	For More Info:
		 	https://proofwiki.org/wiki/Sequence_of_Best_Rational_Approximations_to_Square_Root_of_2
		 	
		 	How to calculate the Pell-Lucas numbers:
		 	https://proofwiki.org/wiki/Definition:Pell-Lucas_Numbers
		 	https://proofwiki.org/wiki/Definition:Pell_Numbers
		 */
		
		//pell lucas numbers
		BigInteger [] Qn = new BigInteger [3];
		Qn[0] = new BigInteger("2");
		Qn[1] = new BigInteger("2");
		Qn[2] = new BigInteger("0");
		
		//pell numbers
		BigInteger [] Pn = new BigInteger [3];
		Pn[0] = new BigInteger("0");
		Pn[1] = new BigInteger("1");
		Pn[2] = new BigInteger("0");
		calculateNextStep(Pn);
		int count = 0;
		
		
		for(int i = 1; i <= 1000; i++) {			
			
			calculateNextStep(Pn);
			calculateNextStep(Qn);
			
			if((Qn[0].divide(BigInteger.TWO)).toString().length() > Pn[0].toString().length())
				count++;
		}
		
		System.out.println(count);
		
		time = System.nanoTime() - time;
		System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
	}
	
	public static BigInteger[] calculateNextStep (BigInteger [] n) {
		n[2] = n[1];
		n[1] = n[0];
		n[0] = n[1].add(n[1].add(n[2]));
		
		return n;
	}
}