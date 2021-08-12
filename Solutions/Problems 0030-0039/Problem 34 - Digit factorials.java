/*	Problem 34 - Digit factorials:
	145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.

	Find the sum of all numbers which are equal to the sum of the factorial of their digits.

	Note: As 1! = 1 and 2! = 2 are not sums they are not included.
*/
package euler;

public class projectEuler {
	
	 static int [] digits = new int[10];
	
	public static void main (String [] args){
		long time = System.nanoTime();

		//set up the factorial values
		  digits[0] = 1; 
		  for(int i=1; i<=9; i++){
		    digits[i] = digits[i-1] * i;
		  }
		  
		  long sum = 0;
		  long n = 3;
		  
		  while(n < digits[9]){		//an upper bound is 9! 
		    if(isFactorialDigits(n)){
		      sum += n;
		      System.out.println(n);
		    }
		    n++;
		  }
		  
		  System.out.println("sum: "+sum);
		  
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
		  
  public static boolean isFactorialDigits(long n){
	long temp = n;
	    
    while(n > 0){
      temp -= digits[(int)n%10];
      n /= 10;
    }
	    
    return (temp == 0);
  }
}
