/*	Problem 51 - Prime digit replacements: 
 
	By replacing the 1st digit of the 2-digit number *3, 
	it turns out that six of the nine possible values: 
	13, 23, 43, 53, 73, and 83, are all prime.

	By replacing the 3rd and 4th digits of 56**3 with the same digit, 
	this 5-digit number is the first example having seven primes among
	the ten generated numbers, yielding the family: 
	56003, 56113, 56333, 56443, 56663, 56773, and 56993. 
	Consequently 56003, being the first member of this family, is the smallest prime with this property.

	Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) 
	with the same digit, is part of an eight prime value family.
*/

package euler;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();
		
		boolean [] primes = sieveOfEratosthenes(1000000);
		
		int target = 8;
		int i = 2;
		while(true) {
			i++;
			
			int a = calculatePossiblePrimes(primes, i, target);
			
			if(a == target) 
				break;
		}
				

		time = System.nanoTime() - time;
		System.out.println("The program took "+(time/1000000)+" ms to execute\n");
		
	}
	
	public static int calculatePossiblePrimes (boolean [] primes, int n, int target) {
		//we can look at it as a binary decision:
		//either we replace a digit or not.
		//therefore we can "count in binary" up to the number

		int counter = 1;
		String s = Integer.toString(n);	
				
		while (counter < n) {
			char [] digits = s.toCharArray();
			int len = digits.length;
			
			int currentBit = 1;	// [bitwise]	0 - don't replace		1 - replace 
			
			
			while (len > 0) {
				
				if((currentBit & counter) != 0) {
					digits[digits.length - len] = 'x';
				}
				
				currentBit <<= 1;
				len--;
			}
			
			//check the current number with replaceable digits
			int primeValueFamily = 0;
			for(int currentDigit = 0; currentDigit <= 9; currentDigit++) {
				String str = String.valueOf(digits).replace('x', (char) (currentDigit + '0'));				
				
				int finalResult = Integer.parseInt(str);
				
				if(primes[finalResult] && (Integer.toString(finalResult).equals(str)))	//check for leading 0s
					primeValueFamily ++;
				
			}
			
			if(primeValueFamily == target) {
				System.out.println("An answer was found!\n");
				
				//the first prime we find it the smallest one
				for(int a = 0; a <= 9; a++) {
					String str = String.valueOf(digits).replace('x', (char) ((char)a + '0'));	
					
					if(primes[Integer.parseInt(str)]) {
						System.out.println("the answer is: "+str);
						return target;
					}
				}
			}
			
			
			
			//if we go all over the number we can quit
			boolean flag = true;
			for(int i = 0; i < digits.length; i++)
				if(digits[i] != 'x')
					flag = false;
			
			if(flag)
				return 1;
			
			
			//go to the next binary combination
			counter++;
		}
		
		return -1;
	}
	
	
	public static boolean[] sieveOfEratosthenes (int limit) {
		 boolean isPrime[] = new boolean[limit + 1];
		 
		 for(int i = 0; i <= limit;i++)
	        	isPrime[i] = true;
		 
		 isPrime[0] = false;
		 isPrime[1] = false;
		 
		 for(int p = 2; p*p <= limit; p++){
	            // If prime[p] is still true, then it is a prime
	            if(isPrime[p]){
	            	//all the numbers of the form n*p are not prime (n >= 2)
	                for(int i = p*p; i <= limit; i += p)
	                	isPrime[i] = false;
	            }
	        }
		 
		 return isPrime;
	 }
	
}