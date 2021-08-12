/*	Problem 9 - Special Pythagorean triplet:
	
	A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,

	a^2 + b^2 = c^2
	For example, 3^2 + 4^2 = 9 + 16 = 25 = 52.

	There exists exactly one Pythagorean triplet for which a + b + c = 1000.
	Find the product abc.

*/
public class Main {
	
	public static void main (String [] args) {
		//method #1 - Bruteforce
		int max = 0;
		for(int c=999; c>5; c--)		
			for(int b=c-1; b>5; b--)	
				for(int a=b-1; a+b+c>=1000; a--) 
					if((a+b+c == 1000) && (a*a + b*b == c*c))
						max = Math.max(max, (a*b*c));
		System.out.println(max);
		 	
		
		//method #2 - using the method that generate Pythagorean triples (credit to Pier from the question's thread)
		/*
		 More Info:		https://en.wikipedia.org/wiki/Formulas_for_generating_Pythagorean_triples
		 			https://www.chilimath.com/lessons/geometry-lessons/generating-pythagorean-triples/
		 
		 1. we can assign the parametric form
		 a = m^2 - n^2
		 b = 2 * m * n
		 c = m^2 + n^2
		 
		 2. Utilizing the sum
		       a     +   b   +      c      = 1000
		 (m^2 - n^2) + (2mn) + (m^2 + n^2) = 1000
		 
		 2*m^2 + 2mn = 1000
		 m^2 + n*m - 500 = 0
		 
		 3. finding the roots with the quadratic formula (ignoring the negative solution)
		 m1 = [-n + sqrt(n^2 -4 * 1 * (-500))]/2
		 m1 = [-n + sqrt(n^2 + 2000)]/2
		 
		 4. finding the right n
		 We know that m1 is have to be an integer, by plugging numbers into the mathematical expression 
		 until we find a find an integer.
		 */
		boolean found = false;
		int n = -1;
		double delta = 0;
		
		while (!found) {
			n++;
			delta = Math.sqrt(n*n + 2000);
			if(delta == (int)delta) {
				found = true;
			}
		}
		System.out.println("n = "+n+" m1 = "+((-1*n+delta))/2);
		
		/*
		 5. getting the values of a, b, c
		 after we've got n=5 m=20 we can get assign them into the parametric form of a,b,c: 
		 a = m^2 - n^2	= 20^2 - 5^2 = 375 
		 b = 2 * m * n 	= 2 * 20 * 5 = 200
		 c = m^2 + n^2	= 20^2 + 5^2 = 425
		 
		 And we can validate the solution:
		 a + b + c = 
		 375 + 200 + 425 = 1000
		 
		 yay :)
		 
		 */
		
	}
}
