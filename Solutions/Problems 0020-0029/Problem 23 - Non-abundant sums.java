/*	Problem 23 - Non-abundant sums:
	A perfect number is a number for which the sum of its proper divisors is exactly equal to the number. 
	For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.

	A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant if this sum exceeds n.

	As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as the sum of two abundant numbers is 24. 
	By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers. 
	However, this upper limit cannot be reduced any further by analysis even though it is known that the greatest number that cannot be expressed 
	as the sum of two abundant numbers is less than this limit.

	Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.

*/
package euler;

public class projectEuler {
	public static void main (String [] args) {
		
		final int MAXVALUE = 28123;	
		final int SIZE = 6965;		
		int [] abundants = new int [SIZE];
		
		//add all the abundant numbers to a list
		int index = 0;
		for(int i = 1; i < MAXVALUE; i++) 
			if(isAbundant(i)) 
				abundants[index++] = i;	//used prefix
		
		//mark all the numbers that can be written as the sum of the abundant numbers
		boolean [] badNumbers = new boolean [MAXVALUE];
		for(int i = 0; i < SIZE; i++) {
			for(int j = 0; j < SIZE; j++) {
				try {
					badNumbers[abundants[i] + abundants[j]] = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					break;	//if [i]+[j] > x so [i]+[j+1] will also be > x
				}
			}
		}
		
		
		//sum of all the positive integers which cannot be written as the sum of two abundant numbers
		int sum = 0;
		for(int i = 0; i < MAXVALUE; i++) {
			if(!badNumbers[i])
				sum += i;
		}
		
		System.out.println(sum);
			
		
	}

	public static boolean isAbundant (int n) {
		return (n < sumDivisors(n));
	}
	
	public static int sumDivisors (int n) {
		int sum = 1;
		for (int i = 2; i < n; i++)
			if(n % i == 0)
				sum += i;
		return sum;
	}
	
	//functions that make my console look nice
	public static void newSection () {
		System.out.println("\n------------------------------\n");
	}
	
}
