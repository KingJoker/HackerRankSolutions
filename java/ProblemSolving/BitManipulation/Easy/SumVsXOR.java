/*
Given an integer n, find each x such that:
0<=x<=n
n+x=n⊕x
where ⊕ denotes the bitwise XOR operator. Print the number of x's satisfying the criteria.
For example, if n=4, there are four values:
4+0=4⊕0=4
4+1=4⊕1=5
4+2=4⊕2=6
4+3=4⊕3=7.
*Function Description
Complete the sumXor function in the editor below. It should return the number of values determined, as an integer.
*sumXor has the following parameter(s):
- n: an integer
*Input Format
A single integer, n.
*Constraints
0<=n<=10^15
*Subtasks
0<=n<=100 for 60% of the maximum score.
*Output Format
Print the total number of integers x satisfying the criteria.
*Sample Input 0
5
*Sample Output 0
2
*Explanation 0
For n=5, the x values 0 and 2 satisfy the conditions:
5+0=5⊕0=5
5+2=5⊕2=7
*Sample Input 1
10
*Sample Output 1
4
*Explanation 1
For n=10, the x values 0, 1, 4, and 5 satisfy the conditions:
10+0=10⊕0=10
10+1=10⊕1=11
10+4=10⊕4=14
10+5=10⊕5=15
 */

package BitManipulation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SumVsXOR {

    // Complete the sumXor function below.
    static long sumXor(long n) {
        long numZeros = 64 - Long.numberOfLeadingZeros(n) - Long.bitCount(n);
        return (long)Math.pow(2,numZeros);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = sumXor(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

