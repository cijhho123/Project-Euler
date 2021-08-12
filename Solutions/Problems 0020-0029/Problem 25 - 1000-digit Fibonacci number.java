/*	Problem 25 - 1000-digit Fibonacci number:
	 the recurrence relation:

	Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
	Hence the first 12 terms will be:

	F1 = 1
	F2 = 1
	F3 = 2
	F4 = 3
	F5 = 5
	F6 = 8
	F7 = 13
	F8 = 21
	F9 = 34
	F10 = 55
	F11 = 89
	F12 = 144
	The 12th term, F12, is the first term to contain three digits.

	What is the index of the first term in the Fibonacci sequence to contain 1000 digits?

*/
package euler;

public class projectEuler {
	public static void main (String [] args) {
		final int requiredDigits = 1000;
		final int NUMBERSIZE = requiredDigits + 2;
		int [] number1 = new int [NUMBERSIZE];
		int [] number2 = new int [NUMBERSIZE];
		
		//initialize the numbers
		number1[NUMBERSIZE - 1] = 1;
		number2[NUMBERSIZE - 1] = 1;
		int index = 2;
		
		//calculating fib numbers untill one of them have 1000 digits
		int digits = 0;
		while (true) {
			
			//calculate the first number
			for(int i = NUMBERSIZE - 1; i > 1; i--)
				number1[i] += number2[i];	//n1 = n1 + n2
				
			//take care of number1 overflow
			for(int j = NUMBERSIZE - 1; j > 1; j--)
				if(number1[j] >= 10) {
					number1[j-1] += number1[j] / 10;
					number1[j] %= 10;
				}
			
			//calculate the second number
			for(int i = NUMBERSIZE - 1; i > 1; i--)
				number2[i] = number1[i] - number2[i];	//n2 = n1 - n2
			
			//take care of number2 overflow
			for(int j = NUMBERSIZE - 1; j > 1; j--)
				if(number2[j] < 0) {
					number2[j-1] --;
					number2[j] += 10;
				}
				
			
			index++;
			digits = calculateNumberLength (number1);

			//check if the number is 1k digits and print it
			if(digits == requiredDigits) {
				newSection();
				System.out.print("index: "+index+" digits: "+digits+" number:\n");
				for(int j=NUMBERSIZE - digits; j<NUMBERSIZE; j++)
					System.out.print(number1[j]);
				System.out.println();
				break;
			}
		}
	}

	
	private static int calculateNumberLength (int[] n) {
		int digits = 0;
		while(n[digits] == 0)
			digits ++;
		
		digits = n.length - digits;
			
		return digits;
	}


	//functions that make my console look nice
	public static void newSection () {
		System.out.println("\n------------------------------\n");
	}
	
}
