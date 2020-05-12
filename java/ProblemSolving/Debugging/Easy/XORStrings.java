/*
In this challenge, the task is to debug the existing code to successfully execute all provided test files.
Given two strings consisting of digits 0 and 1 only, find the XOR of the two strings.
Debug the given function strings_xor to find the XOR of the two given strings appropriately.
Note: You can modify at most three lines in the given code and you cannot add or remove lines to the code.
*Input Format
The input consists of two lines. The first line of the input
contains the first string, s, and the second line contains the second string, t.
*Constraints
1<= |s| <=10^4
|s| = |t|
*Output Format
Print the string obtained by the XOR of the two input strings in a single line.
*Sample Input
10101
00101
*Sample Output
10000
*Explanation
The XOR of the two strings 10101 and 00101 is 10000.
 */

package Debugging.Easy;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class XORStrings {
    //Original Code
    /*public static String stringsXOR(String s, String t) {
        String res = new String("");
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) = t.charAt(i))
                res = "0";
            else
                res = "1";
        }

        return res;
    }*/

    public static String stringsXOR(String s, String t) {
        String res = new String("");
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == t.charAt(i))
                res += "0";
            else
                res += "1";
        }

        return res;
    }

    public static void main(String[] args) {

        String s, t;
        Scanner in = new Scanner(System.in);
        s = in.nextLine();
        t = in.nextLine();
        System.out.println(stringsXOR(s, t));

    }

}
