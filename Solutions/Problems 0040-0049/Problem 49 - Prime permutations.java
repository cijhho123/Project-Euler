/*	Problem 49 - Self powers:
	The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, 
	is unusual in two ways: 
	(i) each of the three terms are prime, and, 
	(ii) each of the 4-digit numbers are permutations of one another.

	There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, 
	exhibiting this property, but there is one other 4-digit increasing sequence.

	What 12-digit number do you form by concatenating the three terms in this sequence?
*/

package euler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();

		//generate an array of 4 digit primes
		char[][] primes = generatePrimeList(4);
		
		for(int a = 0; a < primes.length; a++) {
			for(int b = a+1; b < primes.length; b++) {
				if(isAnagram(primes[a], primes[b])) {
					//could be improved a bit:
					//storing the primes in Hashmap and checking if (b + (b-a)) is there
					//if it is we found our triplet
					//I couldn't be bother to do so atm, might later on
					for(int c = b+1; c < primes.length; c++) {
						if(isAnagram(primes[b], primes[c])) {
							int n1 = Integer.parseInt(String.copyValueOf(primes[a]));
							int n2 = Integer.parseInt(String.copyValueOf(primes[b]));
							int n3 = Integer.parseInt(String.copyValueOf(primes[c]));
							
							if(n2 - n1 == n3 - n2 ) {
								
								System.out.println(n1+""+n2+""+n3);

							}
						}
					}
				}
			}
			
		}
		
		time = System.nanoTime() - time;
		System.out.println("The program took "+(time/1000000)+" ms to execute\n");
		
	}
	
	public static char[][] generatePrimeList (int digits) {
		//create a hashmap of all the primes up to 10^digits
		HashMap<Integer, Boolean> primes = sieveOfEratosthenesSum((int) Math.pow(10, digits));
		
		List<String> primeList = new ArrayList<String>();
		
		//remove all the primes with less than 4 digits
		for (var entry : primes.entrySet()) 
			if(entry.getKey() >= 1000) 
				primeList.add(entry.getKey().toString());
		
		//sort the list 
		primeList.sort(Comparator.naturalOrder());
		
		//change the list into a char matrix (each prime in diffrent row)
		char[][] matrix = new char [primeList.size()][digits];	//we have only primes with fixed size (int digits)
		for(int i = 0; i < primeList.size(); i++) {
			matrix[i] = primeList.get(i).toCharArray();
		}
		
		/*
		//print the matrix
		for(int i = 0; i <matrix.length; i++) {
			for(int j = 0; j < digits; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();
		}
		*/
		
		return matrix;
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
	
	//method 1
	public static boolean isAnagram(char[] a, char[] b) {
		//because arrays are passed by reference and we want to keep them as is 
		//we need to create a defensive copy
		char [] a1 = Arrays.copyOf(a, a.length);
		char [] b1 = Arrays.copyOf(b, b.length);
		
		Arrays.sort(a1);
		Arrays.sort(b1);
		
		return (Arrays.equals(a1, b1));
	}
	
	//method 2
	public static boolean isAnagram2(char[] a, char[] b) {
		if(a.length != b.length)
			return false;
		
		char [] a1 = Arrays.copyOf(a, a.length);
		char [] b1 = Arrays.copyOf(b, b.length);
		
		Arrays.sort(a1);
		Arrays.sort(b1);
		
		for(int i = 0; i < a.length; i++)
			if(a1[i] != b1[i])
				return false;
		
		return true;
	}
}
 
