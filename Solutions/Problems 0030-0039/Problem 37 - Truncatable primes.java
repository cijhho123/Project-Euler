/*	Problem 37 - Truncatable primes:
	The number 3797 has an interesting property. Being prime itself, it is possible to continuously remove digits from left to right, 
	and remain prime at each stage: 3797, 797, 97, and 7. Similarly we can work from right to left: 3797, 379, 37, and 3.

	Find the sum of the only eleven primes that are both truncatable from left to right and right to left.

	NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
*/
package euler;

public class projectEuler {
	
	 static int count = 0;
	 static int sum = 0;

	 public static void main(String[] args) {
	 long time = System.nanoTime();
	 
	 int n = 11;
	  
	 while(count < 11){
	    if(isTruncatablePrime(n)){
	      System.out.println(n);
	      count++;
	      sum += n;
	    }
	    n += 2;
	  }
	  
	  System.out.println("the sum is: "+sum);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
	   
	   public static boolean isTruncatablePrime(int n){
	     String temp = Integer.toString(n);
	     
	     //check for right to left primallity
	     while (n > 0){
	       //ABC -> AB
	       if(!isPrime(n)){
	         return false;
	       }
	       n /= 10;
	     }
	     
	     //check for left to right primallity
	     while (temp.length()  > 0){
	       //ABC -> BC
	       if(!isPrime(Integer.parseInt(temp)))
	           return false;
	           
	       temp = temp.substring(1);
	     }
	     
	     return true;
	   }
	   
	   public static boolean isPrime(int n){
		if(n < 2)
			return false;
		if(n < 4)
			return true;
		if((n % 2 == 0) || (n % 3 == 0))
			return false;
			
		for(int i = 5; i*i <= n; i += 6)
			if((n % i == 0) || (n % (i+2) == 0))
	    		return false;
				
			return true;
		}
}
