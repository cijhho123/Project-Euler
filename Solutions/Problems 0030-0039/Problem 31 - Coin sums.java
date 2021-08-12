/*	Problem 31 - Coin sums:
	In the United Kingdom the currency is made up of pound (£) and pence (p). There are eight coins in general circulation:

	1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p).
	It is possible to make £2 in the following way:

	1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
	How many different ways can £2 be made using any number of coins?
*/
package euler;

public class projectEuler {
	
	static final int SIZE = 200;
	
	//1p, 2p, 5p, 10p, 20p, 50p, £1 (100p), and £2 (200p)
	static final int [] coins = {1,2,5,10, 20, 50, 100, 200};
	
	public static void main (String [] args){
		long time = System.nanoTime();
		
		
		//initialize the array
		int[] ways = new int[SIZE+1];
        ways[0] = 1;
        
    	//a bottom-up "dynamic programming"
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= SIZE; j++) {
                ways[j] += ways[j - coins[i]];
            }               
        }
		
		
		System.out.println("method #1 -  answer: "+ways[SIZE]);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
}
