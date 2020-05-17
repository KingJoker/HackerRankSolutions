/*
Alice has a binary string. She thinks a binary string is beautiful if and only if it doesn't contain the substring "010".
In one step, Alice can change a 0 to a 1 or vice versa.
Count and print the minimum number of steps needed to make Alice see the string as beautiful.
For example, if Alice's string is b=010 she can change any one element and have a beautiful string.
*Function Description
Complete the beautifulBinaryString function in the editor below.
It should return an integer representing the minimum moves required.
*beautifulBinaryString has the following parameter(s):
b: a string of binary digits
*Input Format
The first line contains an integer n, the length of binary string.
The second line contains a single binary string b.
*Constraints
1<=n<=100
b[i]∈{0,1}.
*Output Format
Print the minimum number of steps needed to make the string beautiful.
*Sample Input 0
7
0101010
*Sample Output 0
2
*Explanation 0:
In this sample, b="0101010"
The figure below shows a way to get rid of each instance of "010":

index   123 4 567
        010|1|010    Input String

        0111|010     After changing index 2

        0111000      After changing index 5
Because we were able to make the string beautiful by changing 2 characters (b[2] and b[5]), we print 2.
*Sample Input 1
5
01100
*Sample Output 1
0
*Sample Case 1:
In this sample b="01100"
*Explanation 1
The substring "010" does not occur in b, so the string is already beautiful and we print 0.
*Sample Input 2
10
0100101010
*Sample Output 2
3
*Explanation 2
In this sample b="0100101010"
One solution is to change the values of b[2],b[5] and b[9] to form a beautiful string.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BeautifulBinaryString {

    // Complete the beautifulBinaryString function below.
    static int beautifulBinaryString(String b) {
        int target = 2;
        int count = 0;
        int chars = 0;
        int current = Integer.parseInt(b.charAt(0)+"") << 2 + Integer.parseInt(b.charAt(2)+"") << 1 + Integer.parseInt(b.charAt(2)+"");
        for(int i = 0; i < b.length(); i++){
            current = current & 0b11;
            current = current * 2;
            current += Integer.parseInt(b.charAt(i)+"");
            chars++;
            if(chars >= 3){
                if(current == target){
                    current = 0;
                    chars = 0;
                    count++;
                }
            }
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

        String b = scanner.nextLine();

        int result = beautifulBinaryString(b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

