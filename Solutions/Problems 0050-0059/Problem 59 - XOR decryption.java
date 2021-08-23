/*	Problem 59 - XOR decryption:
	
		
	Each character on a computer is assigned a unique code and the preferred standard is ASCII 
	(American Standard Code for Information Interchange). 
	For example, uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
	
	A modern encryption method is to take a text file, convert the bytes to ASCII, 
	then XOR each byte with a given value, taken from a secret key. 
	The advantage with the XOR function is that using the same encryption key on the cipher text, 
	restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
	
	For unbreakable encryption, the key is the same length as the plain text message, 
	and the key is made up of random bytes. 
	The user would keep the encrypted message and the encryption key in different locations, 
	and without both "halves", it is impossible to decrypt the message.
	
	Unfortunately, this method is impractical for most users, 
	so the modified method is to use a password as a key. 
	If the password is shorter than the message, which is likely, 
	the key is repeated cyclically throughout the message. 
	The balance for this method is using a sufficiently long password key for security, 
	but short enough to be memorable.
	
	Your task has been made easy, as the encryption key consists of three lower case characters. 
	Using p059_cipher.txt (right click and 'Save Link/Target As...'), 
	a file containing the encrypted ASCII codes, 
	and the knowledge that the plain text must contain common English words, 
	decrypt the message and find the sum of the ASCII values in the original text.
*/

package euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class projectEuler {
	public static void main (String [] args) throws FileNotFoundException{
		
		long time = System.nanoTime();
		
		//read the text file into an ArrayList
		ArrayList<String> chars = new ArrayList<>(Arrays.asList((readTextFile("src/euler/p059_cipher.txt")).split(",")));
		
		//check if we have special characters besides numbers, and if so, fix it
		checkForBadLetters(chars);
		
		//Find the max ascii value:
		int maxAscii = findMaxAscii(chars);
		
		//lets split the message into 3 piles:
		int [][] freq = new int [3][maxAscii + 1];
		
		//And count in each pile how much each ascii occur 
		for(int i = 0; i < chars.size(); i++) {
			freq[i%3][Integer.parseInt(chars.get(i))]++;
		}
		
		//We know that the most common character is a space bar (assuming it wasn't omitted)
		//so we can XOR the most common character in each pile with the ascii for space to get back the key
		//its like frequency analysis for dummies  :3
		int  space = (int)' ';
		
		int key [] = new int [3];
		
		for(int x = 0; x < 3; x++) {
			for(int i = 0; i < freq[x].length; i++) {
				if(key[x] < freq[x][i])
					key[x] = i;
			}
		}
		
		//XORing with space's ascii code
		for(int i = 0; i < 3; i++)
			key[i] = key[i] ^ space;
			
		
		int sum = 0;
		for(int i = 0; i < chars.size(); i++)
			sum += Integer.parseInt(chars.get(i)) ^ key[i%3];

		System.out.println("The sum of the ascii is: "+sum);
		
		time = System.nanoTime() - time;
		System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
	}
	

	//read txt fille 
	public static String readTextFile (String path) {
		String str = "";
		try {
			File obj = new File(path);
			Scanner myReader = new Scanner(obj);
	      
			while (myReader.hasNextLine()) 
				str +=  myReader.nextLine() + "\n";
	      
			myReader.close();
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred. File not found!");
	      e.printStackTrace();
	    }
		return str;
	}
	
	public static void checkForBadLetters(ArrayList<String> chars) {
		for(int i = 0; i < chars.size(); i++) {
			try {
				Integer.parseInt(chars.get(i));
			} catch (NumberFormatException e) {
				String s = "";
				for(int c = 0; c < chars.get(i).length(); c++)
					if(Character.isDigit(chars.get(i).charAt(c)))
						s += chars.get(i).charAt(c);
				
				chars.set(i, s);
			}
		}
	}
	
	private static int findMaxAscii(ArrayList<String> chars) {
		int maxAscii = 0;
		for(String i : chars) 
			maxAscii = Math.max(maxAscii, Integer.parseInt(i));
		
		return maxAscii;
	}
}

/*	Scribbles:
 	let's play with XOR a bit 
		 	a = 111
			b = 101
			c = 100
			
			-----------------
			a = a ^ c 
			a: 111
			c: 100
			=  011
			
			b = b^c
			b: 101
			c: 100
			=  001
			
			-------------------
			a ^ b after xor with c
			
			a: 011
			b: 001
			=  010
			
			-------------
			a ^ b before xor with c
			a: 111
			b: 101
			=  010
			
			ITS THE SAME	- we can do frequency analysis on the XORed ascii
*/