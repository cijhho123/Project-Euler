/*	Problem 63 - Powerful digit counts:			
		
		The 5-digit number, 16807=7^5, is also a fifth power. 
		Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
		
		How many n-digit positive integers exist which are also an nth power?
*/

package euler;

import java.math.BigInteger;

public class projectEuler {
		
		
		public static void main (String [] args){
			
			/*	
			 	First of all we'll have to find the bound:
			 	we know that:
			 		*	n-digit number N is in the range: 10^(n-1) <= N < 10^n
			 			and the amount of digits can be calculated by Math.floor(log10(N) + 1)
			 		
			 		*	and a max bound is: A^n < 10^N
			 			therefore  2<= A <= 9
			 */

			
			//method #1 - calculate powers 
			long time = System.nanoTime();
			
			int counter = calculatePowerLengthNumbers();
			System.out.println("There are "+counter+" numbers with the same length as power.");
			
			time = System.nanoTime() - time;
			System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");

		}
		
		private static int calculatePowerLengthNumbers() {
			int counter = 1;	// we start from 1 to cover the case of 1^1
			BigInteger currentNumber;
			
			for(int base = 2; base <= 9; base++) {
				for(int power = 1; ; power++) {					
					currentNumber = new BigInteger(Integer.toString(base)).pow(power);
							
					int length = currentNumber.toString().length();
					
					if(length == power) {
						counter++;
						
						//System.out.println(base+"^"+power+" = "+ currentNumber.toString() +"	counter:"+counter); 
					}
					
					//once the power is bigger than the length it will always stay that way 
					//because the base is smaller than 10 so we can go to the next base
					else if(length < power)
						break;
				}
			}
			return counter;
			
		}
		
		private static boolean isNthRoot(long i, int n, double precision) {
			//took from:
			//	https://stackoverflow.com/questions/32553108/calculating-nth-root-in-java-using-power-method
			
		    double a = Math.pow(i, 1.0 / n);
		    return Math.abs(a - Math.round(a)) < precision; 
		}
}
