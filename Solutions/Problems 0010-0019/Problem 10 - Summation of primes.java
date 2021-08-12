/*	Problem 10 - Summation of primes:
	
	The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
	
	Find the sum of all the primes below two million.
*/
package euler;

public class projectEuler {

	 public static void main(String[] args) {
		 long time = System.nanoTime();
		 
		 long sum = sieveOfEratosthenesSum(2000000);
			 
		 System.out.println("the sum is: "+sum);
		 
		 time = System.nanoTime() - time;
		 System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}
	 
	 public static long sieveOfEratosthenesSum (int limit) {
		 //note: the max size of an array is 2^31 -1 (~2.14 mil) so array will work here, if it was something
		 //bigger than that hashmap will work as well.
		 
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
		 
		//cauclualte the sum
		 long sum = 0;
		 for(int i = 2; i <= limit; i++)
			 if(isPrime[i])
				 sum += i;
		 
		 return sum;
	 }
}
