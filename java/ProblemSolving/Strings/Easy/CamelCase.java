/*
Alice wrote a sequence of words in CamelCase as a string of letters, s, having the following properties:
It is a concatenation of one or more words consisting of English letters.
All letters in the first word are lowercase.
For each of the subsequent words, the first letter is uppercase and rest of the letters are lowercase.
Given s, print the number of words in s on a new line.
For example, s=oneTwoThree. There are 3 words in the string.
*Function Description
Complete the camelcase function in the editor below. It must return the integer number of words in the input string.
*camelcase has the following parameter(s):
s: the string to analyze
*Input Format
A single line containing string s.
*Constraints
1<= |s| <=10^5
*Output Format
Print the number of words in string s.
*Sample Input
saveChangesInTheEditor
*Sample Output
5
*Explanation
String s contains five words:
save
Changes
In
The
Editor
Thus, we print 5 on a new line.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CamelCase {

    // Complete the camelcase function below.
    static int camelcase(String s) {
        int count = 1;
        for(char c : s.toCharArray()){
            if(Character.isUpperCase(c)){
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String s = scanner.nextLine();

        int result = camelcase(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

