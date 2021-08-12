/*	Problem 16 - Power digit sum:

	215 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.

	What is the sum of the digits of the number 21000?

*/
package euler;

public class projectEuler {
	//problem 16
	public static void main(String [] args){
		final int POWER = 1000;
		final int ARRAYSIZE = 305;	//2^1000 is ~305 digits long
		
		int [] number = new int [ARRAYSIZE];
		
		number[ARRAYSIZE -1] = 1;	//initialize the number
		
		for(int i = 0; i < POWER; i++){
			//multiply each digit by 2
			for(int j = ARRAYSIZE - 1; j > 1; j--)
				number[j] *= 2;
			
			//take care of the overflow
			for(int j = ARRAYSIZE - 1; j > 1; j--){
				if(number[j] >= 10){
					number[j-1] += number[j] / 10;
					number[j] %= 10;
				}
			}
		}
		
		//sum the digits
		int sum = 0;
		for(int j = ARRAYSIZE - 1; j >= 0; j--)
			sum += number[j];
		
		System.out.println("the sum of the digits of 2^1000 is: "+sum);
			
	}
}
