/*
Given two integers, l and r, find the maximal value of a xor b, written a⊕b, where a and b satisfy the following condition:
l<=a<=b<=r
For example, if l=11 and r=12, then
11⊕11=0
11⊕12=7
12⊕12=0
Our maximum value is 7.
*Function Description
Complete the maximizingXor function in the editor below. It must return an integer representing the maximum value calculated.
*maximizingXor has the following parameter(s):
l: an integer, the lower bound, inclusive
r: an integer, the upper bound, inclusive
*Input Format
The first line contains the integer l.
The second line contains the integer r.
*Constraints
3
*Output Format
Return the maximal value of the xor operations for all permutations of the integers from l to r, inclusive.
*Sample Input 0
10
15
*Sample Output 0
7
*Explanation 0
The input tells us that l=10 and r=15. All the pairs which comply to above condition are the following:
10⊕10=0
10⊕11=1
10⊕12=6
10⊕13=7
10⊕14=4
10⊕15=5
11⊕11=0
11⊕12=7
11⊕13=6
11⊕14=5
11⊕15=4
12⊕12=0
12⊕13=1
12⊕14=2
12⊕15=3
13⊕13=0
13⊕14=3
13⊕15=2
14⊕14=0
14⊕15=1
15⊕15=0
Here two pairs (10, 13) and (11, 12) have maximum xor value 7, and this is the answer.
*Sample Input 1
11
100
*Sample Output 1
127
 */

package BitManipulation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaximizingXOR {

    // Complete the maximizingXor function below.
    static int maximizingXor(int l, int r) {
        int xor = l^r;
        int significantBit = 31 - Integer.numberOfLeadingZeros(xor);
        return (1 << (significantBit + 1)) - 1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int l = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int r = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = maximizingXor(l, r);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

