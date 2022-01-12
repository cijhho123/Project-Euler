	/*	Problem 60 - Prime pair sets:	
			  
		The primes 3, 7, 109, and 673, are quite remarkable. 
		By taking any two primes and concatenating them in any order the result will always be prime. 
		For example, taking 7 and 109, both 7109 and 1097 are prime. 
		The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.
		
		Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
	*/

package euler;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

	public class projectEuler {
		
		/*	Important note:
			This solution run in ~35 seconds. although it obeys the "1 minute rule" I want to revise this code later on and 
			optimize it.
			
			One significant optimization is as following:
			
			let P(n) be a list of all the prime-pairs of n
			
			-For each n do:
				+	create an ArrayList of intersection of n and the Xth element in P(n)
				+	plug the intersection into P(n) and repeat untill we either:
						* ran into an empty set
						* still left with set of 5
						
			it'll be exponentially faster, but I couldn't program it for some reason
		*/
		
		
		public static void main (String [] args){
			
			//NOTE: at the end of the programs there a comparison of different ways two connect to strings
			
			long time = System.nanoTime();
			
			final int SIZE = 10000;	//upper bound

			HashMap<Integer, String> primes = sieveOfEratosthenes(SIZE);
			
			//create an arraylist of arraylists, one for each prime
			ArrayList<ArrayList<String>> pairs = new ArrayList<ArrayList<String>>();
			for(int i = 0; i < SIZE; i++)
				pairs.add(new ArrayList<String>());
			
			//for each prime check for all the other prime-pairs and add it to its corresponding list
			for (var p1 : primes.entrySet()) {
				pairs.get(p1.getKey()).add(p1.getValue());	//so each list will include its own value as well
				for (var p2 : primes.entrySet()) {
					if(isPrimePair(p1.getValue(), p2.getValue()))
						pairs.get(p1.getKey()).add(p2.getValue());
				}
			}
			
			for(int i = 0; i < pairs.size(); i++) {
				//System.out.println(i+": "+pairs.get(i).toString());
			}
			
			//check for overlapping items in lists
			for(ArrayList<String> a : pairs) {
				if(a.size() < 5)
					continue;
				

				for(ArrayList<String> b : pairs) {
					if(b.size() < 5)
						continue;
					
					if(a == b)
						break;
					
					ArrayList<String>common = new ArrayList<String>(a);
					
					common.retainAll(b);
					if(common.size() < 5)
						continue;
						
					for(ArrayList<String> c : pairs) {
						if(c.size() < 5)
							continue;
						
						if(b == c)
							break;
						
						ArrayList<String>common2 = new ArrayList<String>(common);
						common2.retainAll(c);

						if(common2.size() < 5)
							continue;
						
						for(ArrayList<String> d : pairs) {
							if(d.size() < 5)
								continue;
						
							if(c == d)
								break;
							
							ArrayList<String>common3 = new ArrayList<String>(common2);
							common3.retainAll(d);
							
							if(common3.size() < 5)
								continue;
							
							for(ArrayList<String> e : pairs) {
								if(e.size() < 5)
									continue;
							
								if(d == e)
									break;
								
								ArrayList<String>common4 = new ArrayList<String>(common3);
								common4.retainAll(e);

						
								if(common4.size() == 5) {
									int sum = 0;
									for(String s : common)
										sum += Integer.parseInt(s);
									System.out.println("sum: "+sum+" prime set: "+common.toString());
									
								}	
							}	
						}	
					}
				}
			}
				
			
			
			
			
			time = System.nanoTime() - time;
			System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
		}

		public static boolean isPrimePair (String p1, String p2) {
			//NOTE: at the end of the programs there is a comparison of different ways to connect to strings
			if(isPrime(p1+p2))
				if(isPrime(p2+p1))
					return true;
				
			return false;
		}
		

		public static HashMap<Integer, String> sieveOfEratosthenes (int limit) {
			boolean isPrime[] = new boolean[limit + 1];
			 
			for(int i = 0; i <= limit;i++)
				isPrime[i] = true;
			 
			isPrime[0] = false;
			isPrime[1] = false;
			 
			for(int p = 2; p*p <= limit; p++){
				// If prime[p] is still true, then it is a prime
				if(isPrime[p]){
					//all the numbers of the form n*p are not prime (n >= 2)
					for(int i = p*p; i <= limit; i += p)
						isPrime[i] = false;
				}
			}
			
			
	        HashMap<Integer,String> primes = new HashMap<Integer,String>();

	        for(int i = 2; i < isPrime.length; i++)	
				if(isPrime[i])
					primes.put(i, Integer.toString(i));
	        
			return primes;
		 }
		

		public static boolean isPrime(String i) {
	        return new BigInteger(i).isProbablePrime(1);
		}	
}
	
/*
			comparison of different ways to connect to strings:
			~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
the setup:
	long time = System.nanoTime();

	final int SIZE = 10000000;	//upper bound

	ArrayList<String> primes = sieveOfEratosthenes(SIZE);	

	int index = 0;
	for(String i : primes) {
		if(isPrime(i))
			index++;
		System.out.println(i);
	}
	System.out.print("primes: "+primes.size()+" primes detected: "+index);

	time = System.nanoTime() - time;
	System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
_________________________________________________________________________________	
using the primality test function:
	public static boolean isPrime(String i) {
		return new BigInteger(i).isProbablePrime(5);
	}
	
for primes up to n
where, n = 10000000

_________________________________________________________________________________

metod #1: concat
String s1 = p1.concat(p2);
The program took 17819 ms to execute

---------------------------------------------
method #2: + operator
String s1 = p1 + p2;
The program took 13563 ms to execute

---------------------------------------------
method #3: StringBuilder
String s1 = new StringBuilder().append(p1).append(p2).toString();
The program took 16758 ms to execute

---------------------------------------------
method #4: StringBuffer
String s1 = new StringBuffer().append(p1).append(p2).toString();
The program took 13408 ms to execute

---------------------------------------------
method #5: String.format
String s1 = String.format("%s%s", p1, p2);
The program took 14611 ms to execute

---------------------------------------------
method #6: String.join
String s1 = String.join(p1,p2);
The program took 14488 ms to execute

_________________________________________________________________________________

Conclusion:
	the most efficient way of connection two strings in this scenario is StringBuffer
	However, the + operator is only marginally slower, 
	and this is a tradeoff I am willing to take for the increased readabillity
*/	
