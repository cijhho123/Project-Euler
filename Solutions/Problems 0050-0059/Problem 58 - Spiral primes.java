/*	Problem 58 - Spiral primes:

	
	Starting with 1 and spiralling anticlockwise in the following way, 
	a square spiral with side length 7 is formed.
	
				37 36 35 34 33 32 31
				38 17 16 15 14 13 30
				39 18  5  4  3 12 29
				40 19  6  1  2 11 28
				41 20  7  8  9 10 27
				42 21 22 23 24 25 26
				43 44 45 46 47 48 49
	
	It is interesting to note that the odd squares lie along the bottom right diagonal, 
	but what is more interesting is that 8 out of the 13 numbers lying along both diagonals are prime; 
	that is, a ratio of 8/13 ≈ 62%.
	
	If one complete new layer is wrapped around the spiral above, 
	a square spiral with side length 9 will be formed. If this process is continued, 
	what is the side length of the square spiral for which 
	the ratio of primes along both diagonals first falls below 10%?
*/

package euler;
public class projectEuler {
	public static void main (String [] args){
		
		long time = System.nanoTime();
		
		//observation: the steps from each diagonal to the next one is a,a,a+1,a+1,a+2,a+2 etc
		//			   so we can just jump from diagonal to diagonal and check for primallity
		//			   and stop when the (%primes < 10)
		
		
		long primes = 3;	
		long diagSize = 2;		
		long currentNumber = 9;
		
		while (primes * 10 > (2 * diagSize + 1)) {
			
			diagSize += 2;
			
			for(int i = 0; i < 3; i++) {
				currentNumber += diagSize;
				if(isPrime(currentNumber))
					primes++;
			}
			
			currentNumber += diagSize;
		}
		
		System.out.println("the diagonal size is: "+(diagSize+1));
		
		time = System.nanoTime() - time;
		System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
	}
	
	public static boolean isPrime (long n) {
		if(n < 2) 	
			return false;
		if(n < 4) 	
			return true;
		if((n % 2 == 0) || (n % 3 == 0))
			return false;
		for (int i=5; i<=Math.sqrt(n); i+=6) 
			if ((n % i == 0) || (n % (i + 2) == 0)) 
				return false;
		return true;
	}
}