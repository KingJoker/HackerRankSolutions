/*
You are given a string containing characters A and B only. Your task is to change it into a string such that there are no matching adjacent characters.
To do this, you are allowed to delete zero or more characters in the string.
Your task is to find the minimum number of required deletions.
For example, given the string s=AABAAB, remove an A at positions 0 and 3 to make s=ABAB in 2 deletions.
*Function Description
Complete the alternatingCharacters function in the editor below.
It must return an integer representing the minimum number of deletions to make the alternating string.
*alternatingCharacters has the following parameter(s):
s: a string
*Input Format
The first line contains an integer q, the number of queries.
The next q lines each contain a string s.
*Constraints
a<=q<=10
1<=|s|<=10^5
Each string  will consist only of characters A and B
*Output Format
For each query, print the minimum number of deletions required on a new line.
*Sample Input
5
AAAA
BBBBB
ABABABAB
BABABA
AAABBB
Sample Output
3
4
0
0
4
*Explanation
The characters marked red are the ones that can be deleted so that the string doesn't have matching consecutive characters.
AAAA -> A (3 deletions)
BBBBB -> B (4 deletions)
ABABABAB -> ABABABAB (0 deletions)
BABABA -> BABABA (0 deletions)
AAABBB -> AB (4 deletions)
 */

package StringManipulation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class AlternatingCharacters {

    // Complete the alternatingCharacters function below.
    static int alternatingCharacters(String s) {
        char previousChar = s.charAt(0);
        int deleteCount = 0;
        for(int i = 1; i < s.length(); i++){
            char currentChar = s.charAt(i);
            if(previousChar == currentChar){
                deleteCount++;
            }
            else{
                previousChar = currentChar;
            }
        }
        return deleteCount;
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

            int result = alternatingCharacters(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

