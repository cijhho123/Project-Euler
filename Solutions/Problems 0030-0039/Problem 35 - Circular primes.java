/*	Problem 35 - Circular primes:
	The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719, are themselves prime.

	There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.

	How many circular primes are there below one million?
*/
package euler;

public class projectEuler {
	
	 static int [] digits = new int[10];
	
	public static void main (String [] args){
		long time = System.nanoTime();
		final int MAX_VALUE = 1000000 / 6;

		int count = 4;//2,3,5,7
		  
		for(int i = 2; i < MAX_VALUE; i++) {
			//primes must be in the form 6n+-1
			
			if(isCircularPrime(6 * i - 1))
				count++;
			
			if(isCircularPrime(6 * i + 1))
				count++;
		  }
		  
		  
		System.out.println("There are "+count+" Circular primes");
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
		  
	public static boolean isCircularPrime (int n){
	    String numString = Integer.toString(n);
	    int len = numString.length();
	    int itr = 0;
	       
	    while (len > itr){
	      if(!isPrime(Integer.parseInt(numString)))
	          return false;
	          
	      numString = numString.charAt(len-1) + numString.substring(0, len-1);
	      itr ++;
	    }
	    return true;
	  }  
	  
	  
	  public static boolean isPrime (int n) {
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
