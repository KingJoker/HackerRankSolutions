/*
A palindrome is a word, phrase, number, or other sequence of characters which reads the same backward or forward.(Wikipedia)
Given a string A, print Yes if it is a palindrome, print No otherwise.
*Constraints
A will consist at most 50 lower case english letters.
*Sample Input
madam
*Sample Output
Yes
 */

package Strings.Easy;

import java.util.Scanner;

public class JavaStringReverse {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String A=sc.next();
        /* Enter your code here. Print output to STDOUT. */
        for(int i = 0; i < A.length()/2; i++){
            if(A.charAt(i) != A.charAt(A.length() - 1 - i)){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

}
