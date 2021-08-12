/*	Problem 52 - Permuted multiples: 

	
	It can be seen that the number, 125874, and its double, 251748, 
	contain exactly the same digits, but in a different order.
	
	Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x, contain the same digits.
*/

package euler;

import java.util.Arrays;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();
		
		
		//Method #1 - Brute force
		int i = 1;
		
		while(true) {
			
			if(isPerMultiple(i)) {
				System.out.println(i);
				break;
			}
			i++;
		}
		
		time = System.nanoTime() - time;
		System.out.println("Method #1 took "+(time/1000000)+" ms to execute\n");
		
		//Method #2 - analysis
		/*We can utilize few facts:
		 	1.	in order for x and 6x to have the same amount of digits the leading digit must be 1
		 		so we can run with numbers and append leading 1
		 	2.	if x and 6x have the same digits, it means that x, 2x, 3x, 4x have the same digits
		 		so we need to check only 5x and 6x
		*/
		time = System.nanoTime();
		
		i = 1;
		
		while(true) {
			
			if(isPerMultiple2("1"+Integer.toString(i))) {
				System.out.println("1"+i);
				break;
			}
			i++;
		}
		
		
		time = System.nanoTime() - time;
		System.out.println("Method #2 took "+(time/1000000)+" ms to execute\n");
		
	}
	
	public static boolean isPerMultiple2 (String i) {
		int n = Integer.parseInt(i);
		
		char [] arr1 = i.toCharArray();
		
		Arrays.sort(arr1);
		
		for(int x = 5; x <= 6; x++) {
			char [] arr2 = Integer.toString(n*x).toCharArray();
			Arrays.sort(arr2);

			if(!Arrays.equals(arr1, arr2))
				return false;
		}
		return true;
	}
	
	public static boolean isPerMultiple (int i) {
		char [] arr1 = Integer.toString(i).toCharArray();
		
		Arrays.sort(arr1);
		
		for(int x = 2; x <= 6; x++) {
			char [] arr2 = Integer.toString(i*x).toCharArray();
			Arrays.sort(arr2);

			if(!Arrays.equals(arr1, arr2))
				return false;
		}
		return true;
	}
}