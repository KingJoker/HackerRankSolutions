/*
Alice is taking a cryptography class and finding anagrams to be very useful.
We consider two strings to be anagrams of each other if the first string's letters can be rearranged to form the second string. In other words, both strings must contain the same exact letters in the same exact frequency.
For example, bacdc and dcbac are anagrams, but bacdc and dcbad are not.
Alice decides on an encryption scheme involving two large strings where encryption is dependent on the minimum number of character deletions required to make the two strings anagrams.
Can you help her find this number?
Given two strings, a and b, that may or may not be of the same length, determine the minimum number of character deletions required to make a and b anagrams.
Any characters can be deleted from either of the strings.
For example, if a="cde" and b="dcf", we can delete e from string a and f from string b so that both remaining strings are "cd" and "dc" which are anagrams.
*Function Description
Complete the makeAnagram function in the editor below. It must return an integer representing the minimum total characters that must be deleted to make the strings anagrams.
*makeAnagram has the following parameter(s):
a: a string
b: a string
*Input Format
The first line contains a single string, a.
The second line contains a single string, b.
*Constraints
1<=|a|,|b|<=10^4
The strings  and  consist of lowercase English alphabetic letters ascii[a-z].
*Output Format
Print a single integer denoting the number of characters you must delete to make the two strings anagrams of each other.
*Sample Input
cde
abc
*Sample Output
4
*Explanation
We delete the following characters from our two strings to turn them into anagrams of each other:
Remove d and e from cde to get c.
Remove a and b from abc to get c.
We must delete 4 characters to make both strings anagrams, so we print 4 on a new line.
 */


package StringManipulation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakingAnagrams {

    // Complete the makeAnagram function below.
    static int makeAnagram(String a, String b) {
        HashMap<Character,Integer> aMap = new HashMap<>();
        HashMap<Character,Integer> bMap = new HashMap<>();
        a.chars().forEach(c ->aMap.merge((char)c,1,(oldValue, one) -> oldValue + one));
        b.chars().forEach(c ->bMap.merge((char)c,1,(oldValue, one) -> oldValue + one));
        int commonCharCount = 0;
        for(char c : aMap.keySet()){
            int countA = aMap.getOrDefault(c,0);
            int countB = bMap.getOrDefault(c, 0);
            if(countA != 0 && countB != 0){
                commonCharCount += Math.min(countA,countB);
            }
        }
        int deleteA = a.length() - commonCharCount;
        int deleteB = b.length() - commonCharCount;
        return deleteA + deleteB;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String a = scanner.nextLine();

        String b = scanner.nextLine();

        int res = makeAnagram(a, b);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

