/*	
	Problem 4 - Largest palindrome product:
	A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

	Find the largest palindrome made from the product of two 3-digit numbers.
*/
public class Main {
	
	public static void main (String [] args) {
		
		int biggest = 0;
		for (int i=999; i>100; i--) {
			for(int j=999; j>=i; j--) {
				if(i * j <= biggest)
					break;
				
				if (isPal(i*j))
					biggest = i * j;
			}
		}
		System.out.println(biggest);
	}
	
		public static boolean isPal (int n) {
		//turn the int into string, reverse and back into int
		String str = Integer.toString(n);
		String strRev =  reverseString(str);  
	    if(str.equals(strRev))
	    	return true;
	    
		return false;
		
	}
}
