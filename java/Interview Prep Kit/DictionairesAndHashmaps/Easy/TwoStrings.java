/*
Given two strings, determine if they share a common substring. A substring may be as small as one character.
For example, the words "a", "and", "art" share the common substring . The words "be" and "cat" do not share a substring.
*Function Description
Complete the function twoStrings in the editor below. It should return a string, either YES or NO based on whether the strings share a common substring.
*twoStrings has the following parameter(s):
s1, s2: two strings to analyze .
*Input Format
The first line contains a single integer p, the number of test cases.
The following p pairs of lines are as follows:
The first line contains string s1.
The second line contains string s2.
*Constraints
s1 and s2 consist of characters in the range ascii[a-z].
*Output Format
For each pair of strings, return YES or NO.
*Sample Input
2
hello
world
hi
world
*Sample Output
YES
NO
*Explanation
We have  pairs to check:
s1="hello", s2="world". The substrings "o" and "l" are common to both strings.
, .  and  share no common substrings.
 */


package DictionairesAndHashmaps.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class TwoStrings {

    // Complete the twoStrings function below.
    static String twoStrings(String s1, String s2) {
         HashSet<Character> set1 = new HashSet<>();
         HashSet<Character> set2 = new HashSet<>();
         s1.chars().forEach( c -> set1.add((char) c));
         s2.chars().forEach( c -> set2.add((char) c));
         set1.retainAll(set2);
         return set1.size() > 0 ? "YES" : "NO";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s1 = scanner.nextLine();

            String s2 = scanner.nextLine();

            String result = twoStrings(s1, s2);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
