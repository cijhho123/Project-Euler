/*	Problem 30 - Digit fifth powers:
	Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:

	1634 = 1^4 + 6^4 + 3^4 + 4^4
	8208 = 8^4 + 2^4 + 0^4 + 8^4
	9474 = 9^4 + 4^4 + 7^4 + 4^4
	As 1 = 1^4 is not a sum it is not included.

	The sum of these numbers is 1634 + 8208 + 9474 = 19316.

	Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.

*/
package euler;

public class projectEuler {
	
	static final int POWER = 5;
	
	public static void main (String [] args){
		//method #1 - brute force
		long time = System.nanoTime();
		
		int sum = 0;
		
		for(int i = 2; i < 10000000; i++) {
			if(isDigitSum(i)) 
				sum += i;
		}
		
		System.out.println("\nThe sum in the first method is:"+sum);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");
		
		//--------------------------------------------------------
		//method #2 - pre-calculations
		
		time = System.nanoTime();
		
		//create an array of pre determined values, so we won't have to calculate digit^5 each time
		int [] digitsToFifthPower = new int[10];
		for (int i = 0; i < 10; i++)
			digitsToFifthPower[i] = (int) Math.pow(i, 5);
		
		sum = 0;
		for(int i = 2; i < 10000000; i++) {
			if(i == calculateDigitSum(i, digitsToFifthPower)) 
				sum += i;
			
		}
		
		System.out.println("\nThe sum in the sechond method is:"+sum);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}

	//1st method
	public static boolean isDigitSum (int n) {
		String number = Integer.toString(n);
		long sum = 0;
		int pointer = 0;
		
		while (pointer < number.length()) {
			
			sum += Math.pow((number.charAt(pointer++) - '0'), POWER);
		}
		return (n == sum);
	}
	
	//2st method
	public static int calculateDigitSum (int n, int[] a) {
		int digitSum = 0;
		while (n > 0) {
			digitSum += a[n % 10];
			n /= 10;
		}
		return digitSum;
	}
}
