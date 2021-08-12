/*	Problem 26 - Reciprocal cycles:
	A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with denominators 2 to 10 are given:

	1/2	= 	0.5
	1/3	= 	0.(3)
	1/4	= 	0.25
	1/5	= 	0.2
	1/6	= 	0.1(6)
	1/7	= 	0.(142857)
	1/8	= 	0.125
	1/9	= 	0.(1)
	1/10	= 	0.1
	Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit recurring cycle.

	Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.

*/
package euler;

import java.math.BigInteger;

public class projectEuler{
	public static void main (String [] args){
			
			long n = System.nanoTime();
			
			final BigInteger exp = new BigInteger(BigInteger.TEN.pow(2000).toString());
			
			int maxNumber = 0;
			int maxCycle = 0;
			
			for(int d = 1; d < 1000; d++){
				BigInteger decimal = exp.divide(new BigInteger(Integer.toString(d)));	//decimal 10 ^ 2000 / d
				
				int currentCycleLength = cycleLength (decimal.toString());
				
				if(maxCycle < currentCycleLength){
					maxCycle = currentCycleLength;
					maxNumber = d;
				}
				
			}
			
			n = System.nanoTime() - n;
			
			System.out.println("\n1 / "+maxNumber+" cycle length: "+maxCycle);
			System.out.println("the program took "+(n/1000000)+" ms to execute");
	}
	
	public static int cycleLength (String n){
		int cycleLength;
		int shift;	//in case the cycle doesnt start at the first digit (for example 1/6)
		
		for(shift = 0; shift < n.length(); shift++){
			for(cycleLength = 1; cycleLength < n.length()/2; cycleLength++){
				boolean flag = true;
				for(int index = 0; index <= cycleLength; index++){
					if(n.charAt(shift + index) != n.charAt(shift + cycleLength + index)){
						flag = false;
						break;	//go to the next loop
					}
				}
				
				if(flag)
					return cycleLength;
			}
		}
		return -1;	//in case there's no cycle at all (can't happen in a rational fraction its a good practice);
	}
}
