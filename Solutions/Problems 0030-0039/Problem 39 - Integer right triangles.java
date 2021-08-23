/*	Problem 39 - Integer right triangles:
	If p is the perimeter of a right angle triangle with integral length sides, {a,b,c}, there are exactly three solutions for p = 120.

	{20,48,52}, {24,45,51}, {30,40,50}

	For which value of p ≤ 1000, is the number of solutions maximised? 
*/
package euler;

import java.util.Arrays;

public class projectEuler {

	 public static void main(String[] args) {
		 long time = System.nanoTime();
		 
		 int [] perimeter = new int [1001];
		 
		 //the max value for c is 500, because a+b>c (triangle inequality) and a+b+c<1000
		 for(int a = 3;a < 500; a++) {
			 for(int b = a + 1; b < 500; b++) {
				 for(int c = b+1; c <= 500; c++) {	
					 if(a + b + c >= 1000)
						 break;
					 
					 if(a*a + b*b == c*c) {
						 perimeter[a+b+c] ++;
					 }
				 }
			 }
		 }
		 
		 int maxPerm = 0;
		 int maxCombinations = 0;
		 
		 for(int i = 0; i < 1001; i++) {
			 if(maxCombinations < perimeter[i]) {
				 maxCombinations = perimeter[i];
				 maxPerm = i;
			 }
		 }
		 
		 System.out.println("the perimeter with the max amount of pythagorean triples is: "+maxPerm);
			 
		 time = System.nanoTime() - time;
		 System.out.println("\nIt took "+(time/1000000)+"ms to execute");
	}

}
 
