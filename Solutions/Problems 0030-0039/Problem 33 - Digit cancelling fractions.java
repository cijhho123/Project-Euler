/*	Problem 33 - Digit cancelling fractions:
	The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it may 
	incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.

	We shall consider fractions like, 30/50 = 3/5, to be trivial examples.

	There are exactly four non-trivial examples of this type of fraction, less than one in value, and containing two digits in the numerator and denominator.

	If the product of these four fractions is given in its lowest common terms, find the value of the denominator.
*/
package euler;

public class projectEuler {
	
	public static void main (String [] args){
		long time = System.nanoTime();

		int prodA = 1;
		int prodB = 1;
		
		for(int a=1; a<=9; a++){
			for(int b=1; b<=9; b++){
				for(int c=1; c<=9; c++){

					double f = 1.0 * a / c;
			
					if(f > 1.0)
						continue ;
			
					if(a == b || a == c || b == c)
						continue;
			
					boolean flag = false;
			
					if(f == (10.0*a+b)/(10.0*b+c))
					  flag = true; 
			
					if(f == (10.0*a+b)/(10.0*c+b))
					flag  = true;
			
					if(f == (10.0*b+a)/(10.0*b+c))
					flag = true;
			
					if(f == (10.0*b+a)/(10.0*c+b))
					flag = true;
			
					if(flag){
					  prodA *= a;
					  prodB *= c;
					  System.out.println(a+"/"+c);
					}
			

				}
			}
		}
		
		System.out.println("the denominator is: "+(prodB/prodA));

		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
}
