/*
We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string.
In other words, both strings must contain the same exact letters in the same exact frequency.
For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.
Alice is taking a cryptography class and finding anagrams to be very useful.
She decides on an encryption scheme involving two large strings where encryption is
dependent on the minimum number of character deletions required to make the two strings anagrams.
Can you help her find this number?
Given two strings, s1 and s2, that may not be of the same length, determine the minimum number of character
deletions required to make s1 and s2 anagrams. Any characters can be deleted from either of the strings.
For example, s1=abc and s2=amnop. The only characters that match are the a's so we have to
remove bc from 1 and mnop from s2 for a total of 6 deletions.
*Function Description
Complete the makingAnagrams function in the editor below.
It should return an integer representing the minimum number of deletions needed to make the strings anagrams.
*makingAnagrams has the following parameter(s):
s1: a string
s2: a string
*Input Format
The first line contains a single string, s1.
The second line contains a single string, s2.
Constraints
1<= |s1|,|s2| <=10^4
It is guaranteed that s1 and s2 consist of lowercase English letters, ascii[a-z].
*Output Format
Print a single integer denoting the minimum number of characters which must be
deleted to make the two strings anagrams of each other.
*Sample Input
cde
abc
*Sample Output
4
*Explanation
We delete the following characters from our two strings to turn them into anagrams of each other:
1. Remove d and e from cde to get c.
2. Remove a and b from abc to get c.
We had to delete  characters to make both strings anagrams.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakingAnagrams {

    // Complete the makingAnagrams function below.
    static int makingAnagrams(String s1, String s2) {
        HashMap<Character, Integer> left = new HashMap<>();
        HashMap<Character, Integer> right = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            left.merge(s1.charAt(i),1,(oldValue,newValue) -> oldValue + newValue);
        }
        for(int i = 0; i < s2.length(); i++){
            right.merge(s2.charAt(i),1,(oldValue,newValue) -> oldValue + newValue);
        }
        int charSimilar = 0;
        for(Character c : left.keySet()){
            int countLeft = left.get(c);
            int countRight = right.getOrDefault(c,0);
            if(countLeft <= countRight){
                charSimilar += countLeft;
            }
            else{
                charSimilar += countRight;
            }
        }
        int deleteLeft = s1.length() - charSimilar;
        int deleteRight = s2.length() - charSimilar;
        return deleteLeft + deleteRight;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = makingAnagrams(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

