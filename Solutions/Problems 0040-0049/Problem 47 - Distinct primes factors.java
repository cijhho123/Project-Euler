/*	Problem 47 - Distinct primes factors:
		
	The first two consecutive numbers to have two distinct prime factors are:
	
	14 = 2 × 7
	15 = 3 × 5
	
	The first three consecutive numbers to have three distinct prime factors are:
	
	644 = 2² × 7 × 23
	645 = 3 × 5 × 43
	646 = 2 × 17 × 19.
	
	Find the first four consecutive integers to have four distinct prime factors each. 
	What is the first of these numbers?
*/

package euler;

import java.util.HashMap;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();
		//the amount of primes needed is empirical 					V
		HashMap<Integer, Boolean> primes = sieveOfEratosthenesSum(150000);
		
		final int AMOUNT_OF_PRIME_FACTORS = 4;
		int count = 0;
		int n = 2;
		
		while (count != AMOUNT_OF_PRIME_FACTORS) {
			n++;
			
			if(amountOfPrimeFactors(n, primes) == AMOUNT_OF_PRIME_FACTORS)
				count++;
			else
				count = 0;
		}
		
		System.out.println("the first number is: "+(n-AMOUNT_OF_PRIME_FACTORS+1));
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+" ms to execute");
	}
	
	public static int amountOfPrimeFactors(int number, HashMap<Integer, Boolean> primes) {
		int factors = 0;
		
		//check if the number is prime
		//because i am using a HashMap its O(1) operation so it is more efficient than actually calculating
		if(primes.containsKey(number))
			return 1;
		
		for (var entry : primes.entrySet()) {
			
			if(number % entry.getKey() == 0) {	
				//increment the counter only once no matter how many of the same prime 
				factors ++;
				do {
					number /= entry.getKey();
				}while((number % entry.getKey() == 0));
			}
			
			//It took me a solid 2 hours to understand why my code was running in ~50s
			//so basically even after all the prime factors were found, and the number was 1,
			//it kept looping trough all the keys in the "primes" HashMap.
			//after checking it and breaking mid-loop it saves fuckton of time!
			if(number == 1)
				break;
		}
		return factors;
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

/*
	Another and faster approach from the project euler problem's thread:
	by:		Hui  Germany   Delphi Tue, 3 Jan 2006, 17:39

	"My solution goes the other way around: 
	After filling an array with the primes below one thousend 
	I set a boolean array up to 1000000 false. 
	Every product of four primes and multiples like 2*2*2*3*3*5*7 below 1000000 are set true.
	Then I look for the first 4 trues in sequence. 

	Takes 60 ms in Delphi on a P4 2.6 GHz."

	----------
	which is super neat! I might implement it someday and update the git file! :)
*/