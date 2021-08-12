/*	Problem 41 - Pandigital prime:
	We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once. 
	For example, 2143 is a 4-digit pandigital and is also prime.

	What is the largest n-digit pandigital prime that exists?
*/
package euler;

public class projectEuler {

	 public static void main(String[] args) {
		 long time = System.nanoTime();
		 
		 //using the fact that a number is divisible by 3 if the sum of its digits is a multiple of 3 we can deduce that
		 //there are only 4,7 pandigital primes(1+2+3+4 = 10, 1+2+3+4+5+6+7=28
		 //and because we are only looking for  the largest one we'll look only into 1-7 pandigitals
		 
		 char [] digits = {'1','2','3','4','5','6','7'};
		 long max = 0;
	 
		 while(calculateMaxPandigitalPrime(digits))
			 if(isPrime(Integer.parseInt(new String(digits))))
				 max = Integer.parseInt(new String(digits));
				 
		 
			 
		 System.out.println("max prime pandigital number is:"+max);

		 time = System.nanoTime() - time;
		 System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}
	 
	 public static boolean calculateMaxPandigitalPrime (char [] digits) {

	        int n = digits.length;

	        // find rightmost element a[k] that is smaller than element to its right
	        int k; 
	        for (k = n-2; k >= 0; k--)
	            if (digits[k] < digits[k+1]) 
	            	break;
	        
	        if (k == -1) 
	        	return false;

	        // find rightmost element a[j] that is larger than a[k]
	        int j = n-1;
	        while (digits[k] > digits[j])
	            j--;
	        
	        swap(digits, j, k);

	        for (int r = n-1, s = k+1; r > s; r--, s++)
	            swap(digits, r, s);
	        
	        return true;
	 }
	 
	 public static void swap(char[] digits, int i, int j) {
	        char temp = digits[i];
	        digits[i] = digits[j];
	        digits[j] = temp;
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
