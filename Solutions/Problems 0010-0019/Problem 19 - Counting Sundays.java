/*	Problem 19 - Counting Sundays:
	
	You are given the following information, but you may prefer to do some research for yourself.

	1 Jan 1900 was a Monday.
	Thirty days has September,
	April, June and November.
	All the rest have thirty-one,
	Saving February alone,
	Which has twenty-eight, rain or shine.
	And on leap years, twenty-nine.
	A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
	How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?

*/
package euler;

public class projectEuler {
	public static void main (String [] args) {
		
		 //set up the months' amount of days
		int [] months = new int [12];
		months[0] = 31;	//jan
		months[1] = 28;	//feb	- on a leap years it is 29
		months[2] = 31;	//mar
		months[3] = 30;	//apr
		months[4] = 31;	//may
		months[5] = 30;	//jun
		months[6] = 31;	//jul
		months[7] = 31; //aug
		months[8] = 30;	//sep
		months[9] = 31;	//oct
		months[10]= 30;	//nov
		months[11]= 31;	//dec 
		
		//need to calculate from 1 Jan 1901 to 31 Dec 2000
		int dd = 0;		//day
		int mm = 0;		//month
		int yy = 1901;	//year
		
		int day = 2;	//the current day, start on Tuesday
		int subdaysOnFirst = 0;
		
		for(yy = 1901; yy <= 2000; yy++) {
			for(mm = 0; mm < 12; mm++) {
				if(day == 0)
					subdaysOnFirst ++;
				
				day += months[mm];
						
				//check for feb leap year additional day
				if(isLeapYear(yy) && mm == 1) {
					day ++;
				}
				day %= 7;
			}
		}
		
		System.out.println(subdaysOnFirst);
		
	}
	
	public static boolean isLeapYear (int year) {
		//A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
		if(year % 400 == 0) 
			return true;
		
		if(year % 100 == 0)
			return false;
		
		if(year % 4 == 0)
			return true;
		
		return false;
		
	}
	
	
	
	//functions that make my console look nice
	public static void newSection () {
		System.out.println("\n------------------------------\n");
	}
}
