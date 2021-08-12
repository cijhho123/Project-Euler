/*	Problem 5 - Smallest multiple:
	2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

	What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
*/
package euler;

public class Main {
	
	public static void main (String [] args) {
		//Method #1 - by hand
		/*
		OK so in order to find the SMALLEST number that divide all the numbers between 1-20 we have to find the prime 
		factors of all those numbers, reduce the multiples and multiply them.
		
		-	Factor all the numbers:
		
		Number	|	Prime Factors
		--------+----------------
		2		|	2
		3		|	3
		4		|	2, 2
		5		|	5
		6		|	2, 3
		7		|	7
		8		|	2, 2, 2
		9		|	3, 3
		10		|	2, 5
		11		| 	11
		12		|	2, 2, 3
		13		| 	13
		14		|	2, 7
		15		|	3, 5
		16		|	2, 2, 2, 2
		17		|	17
		18,		|	2, 3, 3
		19		|	19
		20		|	2, 2, 5
		
		-	Write down the minimum required:
		Note: the max ammount of number any n is (int)(1/log20(n))
		(can be generalized to (int) (1 / logN(n)) 	N-all the numbers up to N)
		
		Prime	|	Minimum Required
		--------+----------------
		2		|	4
		3		|	2
		5		|	1
		7		|	1
		11		| 	1
		13		| 	1
		17		|	1
		19		|	1
		
		
		
		-	Multiply all the primes:
		number = 2^4 * 3^2 * 5 * 7 * 11 * 13 * 17 * 19 	=	232792560
		
		*/
		
		//Method #2 - Bruteforce (slow af, not practical)
		
		//can also use this fact:the max ammount of number any n is (int)(1/log20(n))
		//(can be generalized to (int) (1 / logN(n)) 	N-all the numbers up to N)
		
		int n = 0;
		boolean flag = true;
		
		while(flag) {
			n += 20;	//must be a multiple of 20
			
			for(int i=2; i<=20; i++) {
				if(n % i != 0) {
					flag = false;
					break;
				}
			}
			
			if(flag) { 
				System.out.println(n);
				break;
			}
			
			flag = true;
			System.out.println(n);
		}
	}
}
