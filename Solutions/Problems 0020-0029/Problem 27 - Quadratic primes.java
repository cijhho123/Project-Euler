/*	Problem 27 - Quadratic primes:
	<p>Euler discovered the remarkable quadratic formula:</p>
	<p class="center">$n^2 + n + 41$</p>
	<p>It turns out that the formula will produce 40 primes for the consecutive integer values $0 \le n \le 39$. However, when $n = 40, 40^2 + 40 + 41 = 40(40 + 1) + 41$ is divisible by 41, and certainly when $n = 41, 41^2 + 41 + 41$ is clearly divisible by 41.</p>
	<p>The incredible formula $n^2 - 79n + 1601$ was discovered, which produces 80 primes for the consecutive values $0 \le n \le 79$. The product of the coefficients, −79 and 1601, is −126479.</p>
	<p>Considering quadratics of the form:</p>
	<blockquote>
	$n^2 + an + b$, where $|a| &lt; 1000$ and $|b| \le 1000$<br /><br /><div>where $|n|$ is the modulus/absolute value of $n$<br />e.g. $|11| = 11$ and $|-4| = 4$</div>
	</blockquote>
	<p>Find the product of the coefficients, $a$ and $b$, for the quadratic expression that produces the maximum number of primes for consecutive values of $n$, starting with $n = 0$.</p>

*/
package euler;

import java.math.BigInteger;

public class projectEuler{
	public static void main (String [] args){
			long n = System.nanoTime();
			
			int maxPrimeCount = 0;
			int maxA = 0, maxB = 0;
			
			for(int a = -999; a < 999; a++){
				for(int b = -999; b < 999; b++){
					int currentPrimeCount = primeCount(a, b);
					if(currentPrimeCount > maxPrimeCount){
						maxPrimeCount = currentPrimeCount;
						maxA = a;
						maxB = b;
					}
				}
			}
			
			System.out.println(maxA * maxB);
			
			n = System.nanoTime() - n;
			System.out.println("the program took "+(n/1000000)+" ms to execute");
	}
	
	public static int primeCount (int a, int b){
		int n = 0;	//n^2 + a*n + b => primes
		int current = n*n + a*n + b;
		
		while(isPrime(current)){
			n++;
			current = n*n + a*n + b; 
		}
		
		return n;
	}
	
	public static boolean isPrime(int n){
		if(n < 2)
			return false;
		if(n < 4)
			return true;
		if((n % 2 == 0) || (n % 3 == 0))
			return false;
		
		for(int i = 5; i*i <= n; i += 6)
			if((n % i == 0) || (n % (i+2) == 0))
				return false;
			
		return true;
	}
}
