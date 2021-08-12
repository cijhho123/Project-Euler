/*	Problem 18 - Maximum path sum I:

	By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

	3
	7 4
	2 4 6
	8 5 9 3

	That is, 3 + 7 + 4 + 9 = 23.

	Find the maximum total from top to bottom of the triangle below:

	75
	95 64
	17 47 82
	18 35 87 10
	20 04 82 47 65
	19 01 23 75 03 34
	88 02 77 73 07 63 67
	99 65 04 28 06 16 70 92
	41 41 26 56 83 40 80 70 33
	41 48 72 33 47 32 37 16 94 29
	53 71 44 65 25 43 91 52 97 51 14
	70 11 33 28 77 73 17 78 39 68 17 57
	91 71 52 38 17 14 91 43 58 50 27 29 48
	63 66 04 68 89 53 67 30 73 16 69 87 40 31
	04 62 98 27 23 09 70 98 73 93 38 53 60 04 23

	NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)

*/
package euler;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class projectEuler {
	public static void main (String [] args) {
		
		//read the text file into a string
		String matrixText = readTextFile("src/euler/triangle.txt");
		System.out.println("The triangle is:\n"+matrixText);
		newSection();
		
		//fill the matrix
		int size = 15, index = 0;
		int [][] matrix = new int [size][size];
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j <= i; j++) {
				int nextNumber = (matrixText.charAt(index) - '0')*10 + (matrixText.charAt(++index) - '0');	//Used Prefix
				matrix [i][j] = nextNumber;
				index +=2;	//go to the next digit (2 because there are spaces)
			}
		}
		
		//print the matrix
		System.out.println("the matrix is: \n");
		for(int i = 0; i < size; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print("	"+matrix[i][j]);
			}
			System.out.println();
		}
		newSection();
		
		
		//-----note: going from bottom to top is the most efficient way
		
		//creating a value matrix
		int [][] maxValues = new int [size][size];
		for(int i = size -1; i >= 0; i--) {
			for(int j = i; j >= 0; j--) {
				try {
					if(maxValues[i+1][j] > maxValues[i+1][j+1]) {
						maxValues[i][j] = maxValues[i+1][j] + matrix [i][j];
					} else {
						maxValues[i][j] = maxValues[i+1][j+1] + matrix[i][j];	//can be written better
					}
				} catch (ArrayIndexOutOfBoundsException e) {
					maxValues [i][j] = matrix[i][j]; 
				}
			}
		}
		
		
		//print the value matrix
		System.out.println("the value matrix is: \n");
		for(int i = 0; i < size; i++) {
			for(int j = 0; j <= i; j++) {
				System.out.print("	"+maxValues[i][j]);
			}
			System.out.println();
		}
		newSection();
		
		//finding the highest path
		int [] maxPathPosition = new int [size];
		for(int i = 0; i < size; i++) {
			int maxInRow = 0;
			int highestPosition = -1;
			
			for(int j = 0; j <= i; j++) {
				if(maxValues[i][j] > maxInRow) {
					highestPosition = j;
					maxInRow = maxValues[i][j];
				}
			}
			maxPathPosition [i] = highestPosition;
		}
		
		//print the sum of the maximum path
		System.out.println("\nthe sum of the maximum path is: "+maxValues[0][0]);

	}
	
	
	
	//functions that make my console look nice
	public static void newSection () {
		System.out.println("\n------------------------------\n");
	}
		
	//read txt fille 
	public static String readTextFile (String path) {
		String str = "";
		try {
		      File obj = new File(path);
		      Scanner myReader = new Scanner(obj);
		      
		      while (myReader.hasNextLine()) 
		    	str +=  myReader.nextLine() + "\n";
		      
		      myReader.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred. File not found!");
		      e.printStackTrace();
		    }
		return str;
		}
}
