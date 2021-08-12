/*	Problem 50 - Consecutive prime sum: 
 * 
	The prime 41, can be written as the sum of six consecutive primes:
	41 = 2 + 3 + 5 + 7 + 11 + 13
	This is the longest sum of consecutive primes that adds to a prime below one-hundred.
	
	The longest sum of consecutive primes below one-thousand that adds to a prime, 
	contains 21 terms, and is equal to 953.
	
	Which prime, below one-million, can be written as the sum of the most consecutive primes?
*/

package euler;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();
		
		boolean[] primes = sieveOfEratosthenes(1000000);
		
		int longestConsecutives = 0;
		int prime = 0;
		
		for(int start = 2; start < primes.length; start++) {
			for(int end = (start+longestConsecutives+1); end < primes.length-1; end += 2) {
				
				int [] data = calculateConsecutivePrimesSum(primes, start, end);
				
				int currentSum = data[0];
			
				try {
					if(primes[currentSum]) {	
						if(data[1] > longestConsecutives) {
							longestConsecutives = data[1];
							prime = currentSum;
						}
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					//if we overshoot
					break;
				}
			}
		}
		
		System.out.println("The prime with the longest chain is: "+prime+" and the chain is: "+longestConsecutives);
		
		time = System.nanoTime() - time;
		System.out.println("The program took "+(time/1000000)+" ms to execute\n");
		
	}
	
	public static int[] calculateConsecutivePrimesSum (boolean [] primes, int start, int end) {
		int len = 0;
		int sum = 0;
		
		while(start <= end) {
			if(primes[start]) {
				sum += start;
				len++;
			}
			start++;
		}
		
		int [] arr = new int []{sum, len};
		return arr;
	}


	public static boolean[] sieveOfEratosthenes (int limit) {
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
		 
		 return isPrime;
	 }
	
}