/*	Problem 38 - Pandigital multiples:

	Take the number 192 and multiply it by each of 1, 2, and 3:

	192 × 1 = 192
	192 × 2 = 384
	192 × 3 = 576
	By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the concatenated product of 192 and (1,2,3)

	The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital, 918273645, 
	which is the concatenated product of 9 and (1,2,3,4,5).

	What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of an integer with (1,2, ... , n) where n > 1?
*/
package euler;

import java.util.Arrays;

public class projectEuler {

	 public static void main(String[] args) {
		 long time = System.nanoTime();
		 
		 final int limit = 2;
		 long max = 0;
		 
		 //we know that in order for the product to be 1-9 pandigital with a starting 9 we will have to
		 //have it in the form 9XXX * {1,2}
		 
		 long n = 9000;
		 
		 while(true) {
			 String prod = calcProduct(n, limit);
			 
			 //check if we surpassed the length
			 if(prod.length() > 9)
				 break;
			 
			 if(isPandigital(prod.toCharArray()) && Integer.parseInt(prod) > max) {
				 max = Integer.parseInt(prod);
				 System.out.println("There's a new maximum! it's: "+max+". the product is "+n+" x {1-"+limit+"}");
			 }
			 n++;
		 }
		 
		 System.out.println("\nthe highest 9-digit pandigital number is: "+max);
		 
		 time = System.nanoTime() - time;
		 System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}
	 
	 public static String calcProduct (long n, int limit) {
		 //calculate the product and add the strings
		 String prodString = "";
		 int index = 1;
		 
		 while(index <= limit) {
			 long currentProduct = n * index;	//must start the product with 1 and go to limit
			 prodString += Long.toString(currentProduct);
			 index ++;
		 }
		 
		 return prodString;
	 }
	 
	 public static boolean isPandigital (char [] n) {
		 //method #1 - native approach
		 int [] digits = new int [10];	//0, 1-9
		 
		 for(char c: n) {
			 digits [c - '0'] ++;
		 }
		 
		 //must have no 0s
		 if(digits[0] != 0)
			 return false;
		 
		 //must have only one of each digits 1-9
		 for(int i = 1; i <= 9; i++) {
			 if(digits[i] != 1)
				 return false;
		 }
		 
		 return true;
	 }
	 
	 public static boolean isPandigitalv2 (char [] n) {
		 //method #2 - sort the array (credit for the idea for some folk in the problem thread in project euler)
		 Arrays.sort(n); 
	     return (String.copyValueOf(n).equals("123456789"));    
		 //damn it is so elegant, although a bit slower than my implementation
	 }
}
