/*
Two words are anagrams of one another if their letters can be rearranged to form the other word.
In this challenge, you will be given a string.
You must split it into two contiguous substrings, then determine the minimum number of
characters to change to make the two substrings into anagrams of one another.
For example, given the string 'abccde', you would break it into two parts: 'abc' and 'cde'.
Note that all letters have been used, the substrings are contiguous and their lengths are equal.
Now you can change 'a' and 'b' in the first substring to 'd' and 'e' to have 'dec' and 'cde' which are anagrams.
Two changes were necessary.
*Function Description
Complete the anagram function in the editor below.
It should return the minimum number of characters to change to make the words anagrams, or -1 if it's not possible.
*anagram has the following parameter(s):
s: a string
*Input Format
The first line will contain an integer, q, the number of test cases.
Each test case will contain a string s which will be concatenation of both the strings described above in the problem.
The given string will contain only characters in the range ascii[a-z].
*Constraints
1<=q<=100
1<= |s| <=10^4
s consists only of characters in the range ascii[a-z].
*Output Format
For each test case, print an integer representing the minimum number of changes required to make an anagram.
Print -1 if it is not possible.
*Sample Input
6
aaabbb
ab
abc
mnop
xyyx
xaxbbbxx
*Sample Output
3
1
-1
2
0
1
*Explanation
Test Case #01: We split s into two strings S1='aaa' and S2='bbb'.
We have to replace all three characters from the first string with 'b' to make the strings anagrams.
Test Case #02: You have to replace 'a' with 'b', which will generate "bb".
Test Case #03: It is not possible for two strings of unequal length to be anagrams of one another.
Test Case #04: We have to replace both the characters of first string ("mn") to make it an anagram of the other one.
Test Case #05: S1 and S2 are already anagrams of one another.
Test Case #06: Here S1 = "xaxb" and S2 = "bbxx". You must replace 'a' from S1 with 'b' so that S1 = "xbxb".
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Anagram {

    // Complete the anagram function below.
    static int anagram(String s) {
        if(s.length() % 2 != 0){
            return -1;
        }
        HashMap<Character, Integer> left = new HashMap<>();
        HashMap<Character, Integer> right = new HashMap<>();
        String s1 = s.substring(0,s.length()/2);
        String s2 = s.substring(s.length()/2);
        for(int i = 0; i < s1.length(); i++){
            left.merge(s1.charAt(i),1,(oldValue,newValue) -> oldValue + newValue);
            right.merge(s2.charAt(i),1,(oldValue,newValue) -> oldValue + newValue);
        }
        int count = 0;
        for(Character c : left.keySet()){
            int countLeft = left.get(c);
            int countRight = right.getOrDefault(c,0);
            int diff = countLeft - countRight;
            if(diff > 0){
                count+= diff;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = anagram(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

