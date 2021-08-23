/*	Problem 15 - 	Lattice paths:
	
	Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
	<div class="center"><img src="project/images/p015.png" class="dark_img" alt="" /></div>
	How many such routes are there through a 20×20 grid?
*/
package euler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	
	final static int size = 21;	//the amount of nodes is the amount of cells + 1
	static long [][] pathValue = new long [size][size];	//will store how much paths from each note
	
	public static void main (String [] args) {	
		
		//method #1 - building a values tree
		System.out.println("start filling the matrix\nThe Matrix is in the form (0,0) (0,1) ... (0,x)\n"
				+ "So the top-left node (i.e. the start) is (0,0) and the down-right (i.e. the end) is (y,x)");
		
		for(int y = size-1; y >= 0; y --) {
			for(int x = size-1; x >= 0; x --) {
				pathValue[y][x] = calculatePaths(y, x);
			}
		}
		
		newSection();
		System.out.println("Here's the paths map");

		for(int y = 0; y < size; y ++) {
			for(int x = 0; x < size; x ++) {
				System.out.print(pathValue[y][x]+"	");
			}
			System.out.println();
		}
		
		newSection();
		System.out.println("for matrix with dimensions "+size+"x"+size+" The amount of paths is: "+pathValue[0][0]);
		
		//method #2 - Mathematical approach
		method2();
		
	}
	
	public static long calculatePaths (int y, int x) {
		//check if we've got out of bounds
		if(x >= size || y >= size)
			return 0;
		
		//check if we've reach the end
		if(x == size-1 && y == size-1)
			return 1;
		
		//check if the paths already have been calculates
		if(pathValue[y][x] != 0) 
			return pathValue[y][x];
		
		//if we haven't reach the end make a step in each direction and try again
		return calculatePaths(x+1, y) + calculatePaths(x, y+1);	
	}
	
	public static void method2 () {
		newSection();
		System.out.println("\nMethod #2 - Mathematical approach\r\n"
				+ "		Lets look at the matrix as a web of nodes\r\n"
				+ "		in order to go from the up-left corner (0,0) to the down-right corner (20,20)\r\n"
				+ "		we need to go 20 steps down and 20 steps right, no matter the order.\r\n"
				+ "		 \r\n"
				+ "		In each step we either have the option to go down or right. untill we run out of down/right moves.\r\n"
				+ "		 \r\n"
				+ "		We can utilize Binomial coefficients:\r\n"
				+ "		40 steps needed to take => S = 40\r\n"
				+ "		20 of them need to be down (or right) no matter the order => D = 20\r\n"
				+ "		\r\n"
				+ "		the amount of paths can be calculated using Binomial coefficient forumula:\r\n"
				+ "		(S)	       S!\r\n"
				+ "		( ) = ------------\r\n"
				+ "		(D)    (D!)*(S-D)!");
	}
		
	
	//functions that make my console look nice
	public static void newSection () {
		System.out.println("\n------------------------------\n");
	}
}
 
 
