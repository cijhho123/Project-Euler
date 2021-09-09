	/*	Problem 63 - Powerful digit counts:			
		
		The 5-digit number, 16807=7^5, is also a fifth power. 
		Similarly, the 9-digit number, 134217728=8^9, is a ninth power.
		
		How many n-digit positive integers exist which are also an nth power?
	*/

package euler;


public class projectEuler {
		
		
		public static void main (String [] args){
			long time = System.nanoTime();
			
			/*	
			 	First of all we'll have to find the bound:
			 	we know that:
			 		*	n-digit number N is in the range: 10^(n-1) <= N < 10^n
			 			and the amount of digits can be calculated by Math.floor(log10(N) + 1)
			 		
			 		*	so we know that 10^(n-1) < A^n < 10^N
			 			therefore 2<= A <= 9
			 */
			final double precision = 8.0E-20;	//Empirical precision value

			

			
			//method #1 - check for fifth roots (bruteforce)
			
			System.out.println(calculatePowerfulDigitNumbers2(precision));
			
			
			time = System.nanoTime() - time;
			System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
		}
		
		private static boolean isNthRoot(long i, int n, double precision) {
			//took from:
			//	https://stackoverflow.com/questions/32553108/calculating-nth-root-in-java-using-power-method
			
		    double a = Math.pow(i, 1.0 / n);
		    return Math.abs(a - Math.round(a)) < precision; 
		}
		
		private static int calculatePowerfulDigitNumbers2(double precision) {
			int counter = 0;
			
			for(int len = 1; len < 11; len++) {
				int min = (int) Math.pow(10, len-1);
				int max = min * 10;
				
				
				for(int i = 1; ;i++ ) {
					
					if(i < min)
						continue;
					
					if(i >= max)
						break;
					
					if(isNthRoot(i, len, precision)) {
						counter++;
						System.out.println("number: "+i+" len: "+len+" counter: "+counter);
					}
				}
			}
			
			return counter;
		}

		private static int calculatePowerfulDigitNumbers(double precision) {
			int counter = 0;
			
			for(long i = 1; i < 1000000000 ; i++) {
				
				//System.out.println(i);
				
				//get the number's length
				int len = (int) Math.log10(i) + 1;
				
				//System.out.println("the "+len+"th root of "+i+" is "+x);
				
				if(isNthRoot(i,len, precision)) {
					System.out.println("number: "+i+"	root: "+len+" counter: "+counter);
					counter++;
				}
			}
			
			return counter;
		}
}