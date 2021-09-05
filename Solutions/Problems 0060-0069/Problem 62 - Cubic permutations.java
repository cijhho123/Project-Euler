	/*	Problem 62 - Cubic permutations:	

		
		The cube, 41063625 (345^3), can be permuted to produce two other cubes: 
		56623104 (384^3) and 66430125 (405^3). 
		In fact, 41063625 is the smallest cube which has exactly 
		three permutations of its digits which are also cube.
		
		Find the smallest cube for which exactly five permutations of its digits are cube.
	*/

package euler;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;

public class projectEuler {
		
		
		public static void main (String [] args){
			long time = System.nanoTime();
			
			int result = findCubePermutation(3,5);
			
			System.out.println("the smallest cube for which exactly five permutations of its digits are "
					+ "cube is: "+result+"^3, which is: "+(new BigInteger (Integer.toString(result)).pow(3)));
			
				
			
			time = System.nanoTime() - time;
			System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
		}


		private static int findCubePermutation(int power, int amount) {
			//i'm sure there's a better data structure to use in this case, might revise it at some point
			HashMap<String, Integer> cubes = new HashMap <String, Integer>();
			HashMap<String, Integer> smallestPerm = new HashMap <String, Integer>();
			
			int i = 1;
			
			while(true) {
				//calculate the cube of i and sort the digits 
				char[] digits = new BigInteger(Integer.toString(i)).pow(power).toString().toCharArray();
				Arrays.sort(digits);
				String n = String.valueOf(digits);
				
				if(cubes.containsKey(n))
					cubes.put(n, cubes.get(n) + 1);
				else {
					cubes.put(n, 1);
					smallestPerm.put(n, i);	//save the smallest cube for each given permutation
				}
				
				//check if there are 5 permutation for a given value
				if(cubes.get(n) == amount)
					return smallestPerm.get(n);
								
				i++;
			}
		}
}