/*	Problem 6 - Sum square difference:
	<p>The sum of the squares of the first ten natural numbers is,</p>
	$$1^2 + 2^2 + ... + 10^2 = 385$$
	<p>The square of the sum of the first ten natural numbers is,</p>
	$$(1 + 2 + ... + 10)^2 = 55^2 = 3025$$
	<p>Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is $3025 - 385 = 2640$.</p>
	<p>Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.</p>	
*/
public class Main {
	
	public static void main (String [] args) {
		//method #1 - bruteforce
		int s1=0, s2=0;
		for(int i=1; i<=100; i++) {
			s1 += i;
			s2 += i*i;
		}
		s1 *= s1;	//calculate (1+2+3+..100)^2
		System.out.println((s1-s2));
		
		//method #2 - finding a formula
		/*
		    The sum of the squares of the first N natural numbers:
		 	S(N) = [N*(N+1)*(2N+1)]/6
		 	More Info:	https://trans4mind.com/personal_development/mathematics/series/sumNaturalSquares.htm
		 	
		 	The square of the sum of the first N natural numbers is:
		 	S(N) = [N*(N+1)]/2
		 */
		
		s1 = 100 * (100+1) * (2 * 100 + 1) / 6;
		s2 = 100 * (100+1) / 2;
		s2 = (int) Math.pow(s2, 2);
		System.out.println((s2-s1));
	}
}
