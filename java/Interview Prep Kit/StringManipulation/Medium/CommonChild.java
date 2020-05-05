/*
A string is said to be a child of a another string if it can be formed by deleting 0 or more characters from the other string.
Given two strings of equal length, what's the longest string that can be constructed such that it is a child of both?
For example, ABCD and ABDC have two children with maximum length 3, ABC and ABD.
They can be formed by eliminating either the D or C from both strings.
Note that we will not consider ABCD as a common child because we can't rearrange characters and ABCD != ABDC.
*Function Description
Complete the commonChild function in the editor below.
It should return the longest string which is a common child of the input strings.
*commonChild has the following parameter(s):
s1, s2: two equal length strings
*Input Format
There is one line with two space-separated strings, s1 and s2.
*Constraints
1 <= |s1|,|s2| <= 5000
All characters are upper case in the range ascii[A-Z].
*Output Format
Print the length of the longest string s, such that s is a child of both s1 and s2.
*Sample Input
HARRY
SALLY
*Sample Output
2
*Explanation
The longest string that can be formed by deleting zero or more characters from HARRY and SALLY is AY, whose length is 2.
*Sample Input 1
AA
BB
*Sample Output 1
0
*Explanation 1
AA and BB have no characters in common and hence the output is 0.
*Sample Input 2
SHINCHAN
NOHARAAA
Sample Output 2
3
*Explanation 2
The longest string that can be formed between SINCHAN and NOHARAAA while maintaining the order is NHA.
*Sample Input 3
ABCDEF
FBDAMN
*Sample Output 3
2
*Explanation 3
BD is the longest child of the given strings.
 */

package StringManipulation.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CommonChild{

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        int[][] cache = new int[s1.length() + 1][s2.length() + 1];
        for(int i1 = 1; i1 <= s1.length(); i1++){
            for(int i2 = 1; i2 <= s2.length(); i2++){
                if(s1.charAt(i1 - 1) == s2.charAt(i2 - 1)){
                    cache[i1][i2] = cache[i1-1][i2-1] + 1;
                }
                else{
                    cache[i1][i2] = Math.max(cache[i1-1][i2], cache[i1][i2-1]);
                }
            }
        }
        return cache[s1.length()][s2.length()];
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

