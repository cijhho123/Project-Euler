/*	Problem 29 - Number spiral diagonals:
	Starting with the number 1 and moving to the right in a clockwise direction a 5 by 5 spiral is formed as follows:

			21 22 23 24 25
			20  7  8  9 10
			19  6  1  2 11
			18  5  4  3 12
			17 16 15 14 13

	It can be verified that the sum of the numbers on the diagonals is 101.

	What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed in the same way?

*/
package src;

public class Main {
	public static void main (String [] args){
		final int SIZE = 1001;
		int [] [] matrix = new int [SIZE][SIZE];
		
		int cycle = 1;
		int direction = 1;
		
		int currentNumber = 1;
		
		int x = SIZE / 2;
		int y = x;
		
		for(int i = 1; i < SIZE; i++){
			//fill the current row
			for(int j = 0; j < cycle; j++)
				matrix[y][x + j * direction] = currentNumber++;
			
			x += cycle * direction;
			
			//fill the current coloumn
			for(int j = 0; j < cycle; j++)
				matrix[y + j * direction][x] = currentNumber++;
			
			y += cycle * direction;
			
			cycle ++;
			direction *= -1;
		}
		
		//fill the last row
		for(x = 0; x < SIZE; x++)
			matrix[0][x] = currentNumber++;
		
		//calculate the diagonal's sum
		int sum = -1;	//because we count 1 twice
		for(int i = 0; i < SIZE; i++)
			sum += matrix[i][i] + matrix[i][SIZE - 1 - i];
		
		System.out.println("\nThe Sum is: "+sum);
	}
}
