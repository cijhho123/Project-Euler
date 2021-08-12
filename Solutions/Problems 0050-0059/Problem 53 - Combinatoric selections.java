/*	Problem 53 - Combinatoric selections: 

		
	There are exactly ten ways of selecting three from five, 12345:
	123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
	
	In combinatorics, we use the notation: 	( 5 ) 
						(   ) = 10
						( 3 )
											
	 		( n ) 		n!
	In general,	(   ) = ___________		where, r <= n	and 
			( r )	r! * (n-r)!		 n! = n * (n-1) * (n-2) ... 3 * 2 * 1, and 0! = 1
				
	
	it is not untill n = 23   			( 23 ) 
	that the value exceeds one-million:		(    ) = 1144066
							( 10 )
											
	
		 					( n ) 	
	How many, not necessarily distinct, values of 	(   )  for 1 <= n <= 100 are greater than one-million?
							( r )
	
*/

package euler;


public class projectEuler {
	
	public static final int SIZE = 101;
	
	public static void main (String [] args) {
		
		long time = System.nanoTime();
/*	
	Optimizations:
		1.	we can save a lot of calculations by using pascal's triangle to generate the binomial coefficients
		2.	we can also save half of the needed memory by using a jagged matrix
		3.	we can calculate only half of the triangle because its symmetric

		4.	we don't need to store all the value of each number, if we exceeded 1Million we can store -1 instead
			and take care of the lower levels 
*/
		//creating the pascal's triangle up to level SIZE (in this case 100)
		long [][] pascal = generatePascalTriangle(SIZE);
		
		/*
		//print the array
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col <= row; col++) {
				System.out.print(pascal[row][col]+"	");
			}
			System.out.println();
		}
		*/
		
		int count = 0;
		for(int row = 0; row < SIZE; row++) {
			for(int col = 0; col <= row; col++) {
				if(pascal[row][col] == -1)
					count++;
			}
		}
		
		System.out.println("there are "+count+" values above 1 million");
		
		
		
		time = System.nanoTime() - time;
		System.out.println("The program took "+(time/1000000)+" ms to execute\n");
	}

	private static long[][] generatePascalTriangle(int n) {
		
		long[][] matrix = new long [n][n];
		
		for(int row = 0; row < n; row++) {
			for(int col = 0; col <= row; col++) {
				//dynamic programming - build it from the ground up
				try {
					//fill the current cell based on the previous two
					if(matrix[row-1][col] == -1 || matrix[row-1][col-1] == -1)
						matrix[row][col] = -1;
					else
						matrix[row][col] = matrix[row-1][col] + matrix[row-1][col-1];
					
					//check if the current value is over 1M, and if so assign -1
					if(matrix[row][col] > 1000000)
						matrix[row][col] = -1;
					
				} catch (Exception e) {
					matrix[row][col] = 1;
				}
			}
		}
		
		return matrix;
	}
}
