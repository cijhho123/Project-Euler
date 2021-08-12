/*	Problem 20 - Factorial digit sum:

	n! means n × (n − 1) × ... × 3 × 2 × 1

	For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
	and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.

	Find the sum of the digits in the number 100!
*/
package euler;

public class projectEuler {
	public static void main (String [] args) {
		//note - can be done with BigInteger, but i wanted to try someting of my own uwu
		final int FACTORIAL = 100;
		final int ARRAYSIZE  = 160; //100! is ~160 digits
		
		int[] number = new int[ARRAYSIZE];
		
		number[ARRAYSIZE - 1] = 1;	//initialize the number
		
		
		for(int i = 2; i < FACTORIAL; i++){
			//multiply each cell by i
			for(int j = ARRAYSIZE - 1; j > 0; j--)
				number[j] *= i;
			
			//take care of cell overflow
			for(int j = ARRAYSIZE - 1; j > 1; j--){
				if(number[j] >= 10){
					number[j-1] += number[j] / 10;
					number[j] %= 10;
				}
			}
		}
		
		//sum the digits
		int sum = 0;
		for(int i = ARRAYSIZE - 1; i >= 0; i--)
			sum += number[i];
		
		System.out.println("the sum of the digits of "+FACTORIAL+"! is: "+sum);
	}
	
	
	
	//functions that make my console look nice
	public static void newSection () {
		System.out.println("\n------------------------------\n");
	}
}
