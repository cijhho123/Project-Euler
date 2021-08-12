/*	Problem 46 - Pentagon numbers:
	It was proposed by Christian Goldbach that every odd composite number can be written 
	as the sum of a prime and twice a square.
	
	9 = 7  + 2×1^2
	15 = 7 + 2×2^2
	21 = 3 + 2×3^2
	25 = 7 + 2×3^2
	27 = 19 + 2×2^2
	33 = 31 + 2×1^2
	
	It turns out that the conjecture was false.
	
	What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
*/

package euler;

import java.util.HashMap;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();
		
		HashMap<Integer, Boolean> primes = sieveOfEratosthenesSum(30000);
		
		int n = badComposite(primes);
		System.out.println("the smallest odd composite that cannot be written as the sum of a prime and twice a square is: "+n);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}
	
	public static int badComposite (HashMap<Integer, Boolean> primes) {
		int i = 9;
		
		while(true) {
			
			//skip primes
			if(!primes.containsKey(i))
				if(!isSumOfPrimeAndSquare(i, primes))
					return i;
			
			i += 2;
		}
		
	}
	
	public static boolean isSumOfPrimeAndSquare (int i, HashMap<Integer, Boolean> primes) {
		int square = 0;
		
		while(2*square*square <= i) {
			square ++;
			for (var entry : primes.entrySet()) {
				
				if(i == (entry.getKey() + 2*square*square))
					return true;
				
				/*	NOTE: the HashMap isn't sorted, if we were to sort it via different data structure
				 *  we could use the following optimization:
				 * 
				if(i < (entry.getKey() + 2*square*square)) {
					break;
				}
				*/

			}
		}
		
		return false;
	}
	
	public static HashMap<Integer, Boolean> sieveOfEratosthenesSum (int limit) {
		 boolean isPrime[] = new boolean[limit + 1];
		 
		 for(int i = 0; i <= limit;i++)
	        	isPrime[i] = true;
		 
		 for(int p = 2; p*p <= limit; p++){
	            // If prime[p] is still true, then it is a prime
	            if(isPrime[p]){
	            	//all the numbers of the form n*p are not prime (n >= 2)
	                for(int i = p*p; i <= limit; i += p)
	                	isPrime[i] = false;
	            }
	        }
		 
		 //add the primes to an list
		 HashMap<Integer, Boolean> primes = new HashMap<>();
		 for(int i = 2; i <= limit; i++)
			 if(isPrime[i])
				 primes.put(i, true);
		 
		 return primes;
	 }

}