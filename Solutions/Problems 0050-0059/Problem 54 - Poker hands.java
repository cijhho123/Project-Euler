/*	Problem 54 - Poker hands: 

	In the card game poker, a hand consists of five cards and are ranked, 
	from lowest to highest, in the following way:
	
	High Card: Highest value card.
	One Pair: Two cards of the same value.
	Two Pairs: Two different pairs.
	Three of a Kind: Three cards of the same value.
	Straight: All cards are consecutive values.
	Flush: All cards of the same suit.
	Full House: Three of a kind and a pair.
	Four of a Kind: Four cards of the same value.
	Straight Flush: All cards are consecutive values of same suit.
	Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
	
		
	The cards are valued in the order:
	2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King, Ace.
		
	
	If two players have the same ranked hands then the rank made up of the highest value wins; 
	for example, a pair of eights beats a pair of fives (see example 1 below). 
	But if two ranks tie, for example, both players have a pair of queens, 
	then highest cards in each hand are compared (see example 4 below); 
	if the highest cards tie then the next highest cards are compared, and so on.
	
	Consider the following five hands dealt to two players:
	
			
		Hand	 	Player 1	 		Player 2	 	Winner
		1	 	5H 5C 6S 7S KD		2C 3S 8S 8D TD		Player 2
				Pair of Fives		Pair of Eights
				
		2	 	5D 8C 9S JS AC		2C 5C 7D 8S QH		Player 1
				Highest card Ace	Highest card Queen
		 	
		3	 	2D 9C AS AH AC		3D 6D 7D TD QD		Player 2
				Three Aces			Flush with Diamonds
		 	
		4	 	4D 6S 9H QH QC		3D 6D 7H QD QS		Player 1
				Pair of Queens		Pair of Queens
				Highest card Nine	Highest card Seven
		 	
		5	 	2H 2D 4C 4D 4S		3C 3D 3S 9S 9D	 	Player 1
				Full House			Full House
				With Three Fours	with Three Threes
			
		
		The file, poker.txt, contains one-thousand random hands dealt to two players. 
		Each line of the file contains ten cards (separated by a single space): 
		the first five are Player 1's cards and the last five are Player 2's cards. 
		You can assume that all hands are valid (no invalid characters or repeated cards), 
		each player's hand is in no specific order, and in each hand there is a clear winner.

		How many hands does Player 1 win?
*/

package euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class projectEuler {
	
	public static HashMap<Character, Integer> dict = new HashMap<>();
	
	public static void main (String [] args){
		
		long time = System.nanoTime();
		
		//add 2-9
		for(int i=2; i<=9; i++)
			dict.put((char) (i+'0'), i-1);
		//add special characters
		dict.put('T', 9);
		dict.put('J', 10);
		dict.put('Q', 11);
		dict.put('K', 12);
		dict.put('A', 13);
		
		//S - Spade		H -Heart		D - Diamond		C - CLUB
		
		int wins = 0;
		try {
		      File obj = new File("src/euler/p054_poker.txt");
		      Scanner myReader = new Scanner(obj);
		      
		      
		      while (myReader.hasNextLine()) {
			      String hands = myReader.nextLine();
			      
			      if(findWinner(hands) == 1)
			    	  wins++;
			      
		      }
		      
		      myReader.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred. File not found!");
		      e.printStackTrace();
		    }
		
		System.out.print("\n"+wins);
		
		time = System.nanoTime() - time;
		System.out.println("\nThe program took "+(time/1000000)+" ms to execute\n");
	}
	
	private static int findWinner(String hands) {
		String hand1 = hands.substring(0, 15);
		String hand2 = hands.substring(15, 29);
		
		/*	Table of values:									Hand Code
		 	High Card: Highest value card.							(1)
			One Pair: Two cards of the same value.					(2)
			Two Pairs: Two different pairs.							(3)
			Three of a Kind: Three cards of the same value.			(4)
			Straight: All cards are consecutive values.				(5)
			Flush: All cards of the same suit.						(6)
			Full House: Three of a kind and a pair.					(7)
			Four of a Kind: Four cards of the same value.			(8)
			Straight Flush: All cards are consecutive values of same suit.	(9)
			Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.			(10)
		*/
		
		//	NOTE: Remove the //  for console debug
		
		//check for the highest combination
		//[0] - hand code	[1] - comb#1 card value	[2] - comb#2 card value  [3] - comb#3 card value [4] - highest card	
		
		//System.out.print("\nhand 1: "+hand1);
		int [] player1 = getHighestCombination(hand1);
		//System.out.println("\nhand code: "+player1[0]+" comb #1: "+player1[1]+" comb #2: "+player1[2]+" comb#3: "+player1[3]+" comb#4: "+player1[4]);
		
		//System.out.print("\nhand 2: "+hand2);
		int [] player2 = getHighestCombination(hand2);
		//System.out.println("\nhand code: "+player2[0]+" comb #1: "+player2[1]+" comb #2: "+player2[2]+" comb#3: "+player2[3]+" comb#4: "+player2[4]);
		
		
		//check for the better hand score
		for(int i = 0; i < 5; i++) {
			if(player1[i] > player2[i])
				return 1;
			if(player1[i] < player2[i])
				return 2;
		}
		
		//draw
		return 0;
	}
	
	public static int [] getHighestCombination (String s) {
		int [] data = new int [5];
		
		int [] cards = new int [15];
		
		//count how many times each card accrue (regardless of color)
		for(int i=0; i < 5; i++) {
			//2,3,4,5,6,7,8,9,T,J,Q,K,A
			cards[dict.get(s.charAt(3*i))]++;
		}
		
		
		
	/*	Step 1:	check for combination where color do matter
		
		Flush: All cards of the same suit.	
		Straight Flush: All cards are consecutive values of same suit.
		Royal Flush: Ten, Jack, Queen, King, Ace, in same suit.
	
	*/
	
	//check for flush
	boolean isSuit = true;
	char suit = s.charAt(1);
	
	for(int i=0; i<5; i++)
		if(s.charAt(i*3 + 1) != suit)
			isSuit = false;
	
	if(isSuit) {
		//check for Royal Flush
		boolean isRoyalFlush = true;
		
		//check for T J Q K A
		for(int i = 9; i < 14; i++)
			if(cards[i] != 1)
				isRoyalFlush = false;
		
		if(isRoyalFlush) {
			data[0] = 10;	//royal flush
			return data; 
		}
		
		//check for Straight Flush
		for(int i = 0; i < 8; i++) {
			if(cards[i]==1 && cards[i+1]==1 && cards[i+2]==1 && cards[i+3]==1 && cards[i+4]==1) {
				data[0] = 9;//straight flush
				data[1] = i;
				return data;
			}
		}
		
		
		//if none, we will return with normal flush
		data[0] = 6;	//flush
		return data;
	}
	
	/*	Step 2:	check for combination where color does not matter
	
		One Pair: Two cards of the same value.
		Two Pairs: Two different pairs.
		Three of a Kind: Three cards of the same value.
		Straight: All cards are consecutive values.
		Full House: Three of a kind and a pair.
		Four of a Kind: Four cards of the same value.
	*/
	
	//check for Straight
	for(int i = 0; i < 9; i++) {
		if(cards[i]==1 && cards[i+1]==1 && cards[i+2]==1 && cards[i+3]==1 && cards[i+4]==1) {
			data[0] = 9;//straight flush
			data[1] = i;
			return data;
		}
	}
		
		int pairs = 0;
		int triplet = 0;
		int index = 1;
		
		//[0] - hand code	[1] - pair #1 card value	[2] - pair#2 card value 	[3] - triplet card value
		
		//count how many pairs and triplets
		for(int i=0; i<14; i++) {
			if(cards[i] == 2) {
				pairs ++;
				data[index++] = i+1 ;	//postfix 
			}
			else if(cards[i] == 3) {
				triplet ++;
				data[3] = i+1 ;	//postfix 
			}
		}
		
		//determine which combination the player got
		if(triplet == 1) {
			if(pairs == 1)
				data[0] = 7;	//full house
			else
				data[0] = 4;	//triplet
		} else {
			if(pairs == 2)
				data[0] = 3;	//two pairs
			else if(pairs == 1)
				data[0] = 2;	//pair
		}
		
		//add the highest card
		for(int i = 1; i < 14; i++) 
			if(cards[i] == 1)
				data[4] = i+1;
		
		
		//if we didnt found a combination we will set the hand code to 1 (highest card)
		if(data[0] == 0)
			data[0] = 1;
		
		return data;

	}
}