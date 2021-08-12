/*	Problem 8 - 10001st prime:
	By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

	What is the 10 001st prime number?
*/
public class Main {
	
	public static void main (String [] args) {
		//can use sieving, but its low enough so the tradeoff between speed and memory is good enough for n = 10001
		int count = 0;
		int number = 1;
		while (count < 10001) {
			number++;
			if(isPrime(number))
				count++;
		}
		System.out.println(number);
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
