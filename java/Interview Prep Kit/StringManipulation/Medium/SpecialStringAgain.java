/*
A string is said to be a special string if either of two conditions is met:
All of the characters are the same, e.g. aaa.
All characters except the middle one are the same, e.g. aadaa.
A special substring is any substring of a string which meets one of those criteria.
Given a string, determine how many special substrings can be formed from it.
For example, given the string s=mnonopoo, we have the following special substrings:
{m,n,o,n,o,p,o,o,non,ono,opo,oo}.
*Function Description
Complete the substrCount function in the editor below.
It should return an integer representing the number of special substrings that can be formed from the given string.
*substrCount has the following parameter(s):
n: an integer, the length of string s
s: a string
*Input Format
The first line contains an integer, n, the length of s.
The second line contains the string s.
*Constraints
1<=n<=10^6
Each character of the string is a lowercase alphabet, ascii[a-z].
*Output Format
Print a single line containing the count of total special substrings.
*Sample Input 0
5
asasd
*Sample Output 0
7
*Explanation 0
The special palindromic substrings of s=asasd are {a,s,a,s,d,asa,sas}
*Sample Input 1
7
abcbaba
*Sample Output 1
10
*Explanation 1
*The special palindromic substrings of s=abcbaba are {a,b,c,b,a,b,a,bcb,bab,aba}
*Sample Input 2
4
aaaa
*Sample Output 2
10
*Explanation 2
The special palindromic substrings of s=aaaa are {a,a,a,a,aa,aa,aa,aaa,aaa,aaaa}
 */

package StringManipulation.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SpecialStringAgain {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        Stack<StringBuilder> stack = new Stack<>();
        long count = 0;
        for(char c : s.toCharArray()){
            count++;
            if(stack.size() == 0){
                stack.push(new StringBuilder(c + ""));
                continue;
            }
            StringBuilder curr = stack.pop();
            if(curr.charAt(0) == c){
                curr.append(c);
                count += curr.length() - 1;
            }
            else{
                stack.push(curr);
                curr = new StringBuilder(c + "");
            }
            if(stack.size() >= 2){
                StringBuilder prev = stack.pop();
                if(prev.length() == 1){
                    StringBuilder prev2 = stack.peek();
                    if(prev2.charAt(0) == c){
                        if(prev2.length() >= curr.length()){
                            count+= 1;
                        }
                    }
                }
                stack.push(prev);
            }
            stack.push(curr);
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
