/*	Problem 17 - Number letter counts:
	If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

	If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?


	NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
*/
package euler;

public class projectEuler {
	
	public static int [] singleDigits = new int [10];			//1-10
	public static int [] tens			= new int [9];			//11-19
	public static int [] dozens		= new int [8];				//20,30...90
	public static int hundreds	= "Hundred".length();			//100s
	public static int thousand 	= "thousand".length();			//1,000
	public static int and 		= "and".length();				//and
	
	public static void main(String [] args){
		initializeArrays();
		
		//count the letters
		int letters = 0;
		int temp = 0;	//can be removed, for debug's sake
		for(int i = 1; i <= 1000; i++) {
			System.out.print("number: "+i);
			temp = numberToLetters(i);	//in order to display each number name's length
			letters += temp;
			System.out.println(" letters: "+temp);
		}
		
		System.out.println(letters);
		//System.out.println(numberToLetters(115));
		
	}
	
	public static void initializeArrays () {
		//cell1[x] => refer to number x+1
		//1-10
		singleDigits[0] = "one".length();
		singleDigits[1] = "two".length();
		singleDigits[2] = "three".length();	
		singleDigits[3] = "four".length();
		singleDigits[4] = "five".length();
		singleDigits[5] = "six".length();
		singleDigits[6] = "seven".length();
		singleDigits[7] = "eight".length();
		singleDigits[8] = "nine".length();
		singleDigits[9] = "ten".length();
		
		//cell2[x] => refer to number x+11
		//11-19
		tens[0] = "eleven".length();
		tens[1] = "Twelve".length();
		tens[2] = "Thirteen".length();
		tens[3] = "Fourteen".length();
		tens[4] = "Fifteen".length();
		tens[5] = "Sixteen".length();
		tens[6] = "Seventeen".length();
		tens[7] = "Eighteen".length();
		tens[8] = "Nineteen".length();
		
		//cell3[x] => refer to number x*10+20
		//20-90
		dozens[0] = "Twenty".length();
		dozens[1] = "Thirty".length();
		dozens[2] = "Forty".length();
		dozens[3] = "Fifty".length();
		dozens[4] = "Sixty".length();
		dozens[5] = "Seventy".length();
		dozens[6] = "Eighty".length();
		dozens[7] = "Ninety".length();
		
		//number x => refer to cell1[(int)x/100]+Hundred + and
		//100s
		
		//number x => refer to cell1[(int)x/1000]+thousand 
		//1000s
		
		//the word "and" will be used in case of 3 digit number.
		//for example: 342 (three hundred and forty-two)
	}
	
	public static int numberToLetters (int n) {
		int letters = 0;
		//thousand's digit
		if(n >= 1000) {
			letters += singleDigits[(int)n/1000 - 1] + thousand;
			n %= 1000;
		}
		
		//hundred's digit
		if(n >= 100) {
			letters += singleDigits[(int)n/100 - 1] + hundreds;
			
			n %= 100;
			
			if(n != 0)
				letters += and;
			
		}
		
		//20-90 case
		if(n >= 20) {
			//tens digit
			letters += dozens[(n-20)/10];
			n %= 10;
			
			//single digit
			if(n != 0)
				letters += singleDigits[n-1];
			
		//1-20 case
		} else{
			//tens and single digit at once
			if(n > 10) 
				letters += tens[n-11];
			else if(n != 0)
				letters += singleDigits[n-1];

		}
		return letters;
	}
}
