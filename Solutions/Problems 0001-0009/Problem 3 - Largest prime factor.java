/*
	Problem 3 - Largest prime factor:
	The prime factors of 13195 are 5, 7, 13 and 29.

	What is the largest prime factor of the number 600851475143 ?
*/
public class Main {
	
	public static void main (String [] args) {
		
		int biggest = 0;
		for (int i=999; i>100; i--) {
			for(int j=999; j>=i; j--) {
				if(i * j <= biggest)
					break;
				
				if (isPrime(i*j))
					biggest = i * j;
			}
		}
		System.out.println(biggest);
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
