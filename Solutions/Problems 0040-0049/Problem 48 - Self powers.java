/*	Problem 48 - Self powers:
		
	The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.

	Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.


*/

package euler;

import java.math.BigInteger;

public class projectEuler {
	public static void main (String [] args) {
		
		long time = System.nanoTime();

		//because we only need the last 10 digits we can try working in arithmetic mod 10^10

		//method 1 - int array
		System.out.println("the sum = "+selfPowerMod(1000));
		
		time = System.nanoTime() - time;
		System.out.println("Method 1 took "+(time/1000000)+" ms to execute\n");
		
		time = System.nanoTime();
		
		//method 2 - bigIntetger
		final BigInteger modValue = new BigInteger("10000000000");
		System.out.println("the sum = "+selfPowerMod2(1000, modValue));
				
		time = System.nanoTime() - time;
		System.out.println("Method 2 took "+(time/1000000)+" ms to execute");
	}
	
	//method 1 - int array mod 10^10
	public static long selfPowerMod (int n) {
		//a function that get a number and return the last 10 digits of the sum (1^1 to n^n)
		
		int [] result = new int [10];	//hold the total sum
		
		int number = n;
		for(n = 1; n <= number; n++) {
			//initialize the number to 0000000001
			int [] numArray = new int [10];	
			numArray[9] = 1;
			
			for(int i = 0; i < n; i++) {
				//multitply each cell by n
				for(int x = 9; x >= 0; x--)
					numArray[x] *= n;
				
				//take care of the carry for all the cell but [0]
				for(int x = 9; x > 0; x--) {
					if(numArray[x] > 10) {
						numArray[x-1] += numArray[x]/10;
						numArray[x] %= 10;
					}
				}
				//take care of the [0] cell
				numArray[0] %= 10;
			}
			
			//add the current self power into the result
			for(int x = 9; x >= 0; x--) {
				result[x] += numArray[x];
			}
			
			//-------take care of the result array-------
			//take care of the carry for all the cell but [0]
			for(int x = 9; x > 0; x--) {
				if(result[x] > 10) {
					result[x-1] += result[x]/10;
					result[x] %= 10;
				}
			}
			//take care of the [0] cell
			result[0] %= 10;
			
			/*-------debug
			System.out.print("\nn: "+n+" self power: ");
			for(int i = 0; i <= 9; i++)
				System.out.print(numArray[i]);
			*/
		}
		
		//translate the int array into a long number
		long sum = 0;
		for(int i = 0; i <= 9; i++) {
			sum *= 10;
			sum += result[i];
		}
		
		return sum;
		
		
		
	}
	
	//method 2 - bigIntetger
	public static long selfPowerMod2 (int n, BigInteger modValue) {
		BigInteger sum = BigInteger.ZERO;
		
		for(int i = 1; i <= n; i++) {
			BigInteger number = new BigInteger(String.valueOf(i));
			number = number.modPow(number, modValue);
			
			sum = sum.add(number);
		}

		sum = sum.mod(modValue);
		return sum.longValue();
	}
}
