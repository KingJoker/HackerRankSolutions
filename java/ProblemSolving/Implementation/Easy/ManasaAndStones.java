/*
Manasa is out on a hike with friends. She finds a trail of stones with numbers on them.
She starts following the trail and notices that any two consecutive stones' numbers differ by one of two values.
Legend has it that there is a treasure trove at the end of the trail.
If Manasa can guess the value of the last stone, the treasure will be hers.
For example, assume she finds 2 stones and their differences are a=2 or b=3.
We know she starts with a 0 stone not included in her count.
The permutations of differences for the two stones would be [2,2],[2,3],[3,2] or [3,3].
Looking at each scenario, stones might have [2,4],[2,5],[3,5] or [3,6] on them.
The last stone might have any of 4,5, or 6 on its face.
Compute all possible numbers that might occur on the last stone given a starting stone with a 0 on it,
a number of additional stones found, and the possible differences between consecutive stones. Order the list ascending.
*Function Description
Complete the stones function in the editor below.
It should return an array of integers representing all possible values of the last stone, sorted ascending.
*stones has the following parameter(s):
n: an integer, the number of non-zero stones
a: one possible integer difference
b: another possible integer difference
*Input Format
The first line contains an integer T, the number of test cases.
Each test case contains 3 lines:
- The first line contains n, the number of non-zero stones found.
- The second line contains a, one possible difference
- The third line contains b, the other possible difference.
*Constraints
1<=T<=10
1<=n,a,b<=10^3
*Output Format
Space-separated list of numbers which are the possible values of the last stone in increasing order.
*Sample Input
2
3
1
2
4
10
100
*Sample Output
2 3 4
30 120 210 300
*Explanation
With differences 1 and 2, all possible series for the first test case are given below:
0,1,2
0,1,3
0,2,3
0,2,4
Hence the answer 2 3 4.
With differences 10 and 100, all possible series for the second test case are the following:
0, 10, 20, 30
0, 10, 20, 120
0, 10, 110, 120
0, 10, 110, 210
0, 100, 110, 120
0, 100, 110, 210
0, 100, 200, 210
0, 100, 200, 300
Hence the answer 30 120 210 300.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class ManasaAndStones {

    // Complete the stones function below.
    static int[] stones(int n, int a, int b) {
        if(a==b){
            return new int[]{a*(n-1)};
        }
        int a1 = Math.min(a,b);
        int b1 = Math.max(a,b);
        int diff = b1 - a1;
        ArrayList<Integer> answer = new ArrayList<>();
        for(int i = (n-1) * a1; i <= (n-1) * b1; i += diff){
            answer.add(i);
        }
        answer.sort(null);
        return answer.stream().mapToInt(x -> x).toArray();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int T = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int TItr = 0; TItr < T; TItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int a = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int b = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = stones(n, a, b);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

