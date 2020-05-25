/*
Dothraki are planning an attack to usurp King Robert's throne.
King Robert learns of this conspiracy from Raven and plans to lock the single door through which the enemy can enter his kingdom.
But, to lock the door he needs a key that is an anagram of a palindrome.
He starts to go through his box of strings, checking to see if they can be rearranged into a palindrome.
For example, given the string s=[aabbccdd, one way it can be arranged into a palindrome is abcddcba.
*Function Description
Complete the gameOfThrones function below to determine whether a given string can be rearranged into a palindrome.
If it is possible, return YES, otherwise return NO.
*gameOfThrones has the following parameter(s):
s: a string to analyze
*Input Format
A single line which contains s, the input string.
*Constraints
1<= |s| <=10^5
s contains only lowercase letters in the range ascii[a..z]
*Output Format
A single line which contains YES or NO.
*Sample Input 0
aaabbbb
*Sample Output 0
YES
*Explanation 0
A palindromic permutation of the given string is bbaaabb.
*Sample Input 1
cdefghmnopqrstuvw
*Sample Output 1
NO
*Explanation 1
Palindromes longer than 1 character are made up of pairs of characters. There are none here.
*Sample Input 2
cdcdcdcdeeeef
*Sample Output 2
YES
*Explanation 2
An example palindrome from the string: ddcceefeeccdd.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class GameOfThrones1 {

    // Complete the gameOfThrones function below.
    static String gameOfThrones(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            map.merge(s.charAt(i),1,(oldValue,newValue) -> oldValue + newValue);
        }
        int oddCount = 0;
        for(Character c : map.keySet()){
            if(map.get(c) % 2 != 0){
                oddCount++;
                if(s.length() % 2 != 0){
                    if(oddCount > 2) {
                        return "NO";
                    }
                }
                else if(oddCount > 1){
                    return "NO";
                }
            }
        }
        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String s = scanner.nextLine();

        String result = gameOfThrones(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

