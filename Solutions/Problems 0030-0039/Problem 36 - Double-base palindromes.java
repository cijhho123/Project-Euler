/*	Problem 36 - Double-base palindromes:
	The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.

	Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.

	(Please note that the palindromic number, in either base, may not include leading zeros.)
*/
package euler;

public class projectEuler {
	
	 static int [] digits = new int[10];
	
	public static void main (String [] args){
		long time = System.nanoTime();
		
		int sum = 25; //1,3,5,7,9
		  
		//can check only the odd numbers, because even numbers are in the form 1....0 in binary
		for(int i=11; i<1000000; i+=2){
			if(isPelBinary(i)){
				//System.out.println(i);
		        sum += i;
		     }
		  }

		System.out.println("the sum is: "+sum);
		
		time = System.nanoTime() - time;
		System.out.println("\nIt took "+(time/1000000)+"ms to execute");

	}
	
	 public static boolean isPelBinary (int n){    
		 if(isPel(Integer.toString(n)))
			 if(isPel(decToBinary(n)))
				 return true;
		            
		 return false;
	 }
		  
	  public static boolean isPel(String str){
	    int len = str.length();
	    int index = 0;
	    
	    while (index * 2 <= len){
	      if(str.charAt(index) != str.charAt(len-index-1))
	      return false;
	      
	      index++;
	    }
	    return true;
	  }
		  
	  public static String decToBinary(int n){
	    String bin = "";
	    
	    while(n > 0){
	    	bin += (n % 2);
	    	n /= 2;
	    }
	    
	    return bin;
	  }
}
