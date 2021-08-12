/*   
     Problem 1 - Multiples of 3 and 5:
     If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
     Find the sum of all the multiples of 3 or 5 below 1000.
*/

public class Main{

     public static void main(String []args){
        
        //method #1 - bruteforce
        int sum = 0;
        for(int i=3; i<1000; i++)
            if(i%3 == 0 || i%5 == 0)
                sum += i;
        System.out.println(sum);
        
        
        //method #2 - jumping between numbers
        int sum2 = 0;
        //adding the multipules of 3 and 5
        int n = 3;
        while(n < 1000){
            sum2 += n;
            n += 3;
        }
        //adding the multipules of 5
        n = 5;
        while(n < 1000){
            sum2 += n;
            n += 5;
        }
        //subtractind the duplicates of 15 (3*5)
        n = 15;
        while (n < 1000){
            sum2 -= n;
            n +=15;
        }
        System.out.println(sum2);

        
        //method #3 - finding a formula 
        //using the formula of the sum of  s(n) = n[2*a1+(n-1)d]/2
        //and accounting for duplicates S = S3 + S5 - S15
        int s3 = 333 * (2*3 + (333-1)*3)/2;    // (int)999/3 = 333
        int s5 = 199 * (2*5 + (199-1)*5)/2;    // (int)999/5 = 199
        int s15 = 66 * (2*15 + (66-1)*15)/2;   // (int)999/15 = 66
        int sum3 = s3 + s5 - s15;
        System.out.println(sum3);
        
     }
} 
