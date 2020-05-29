/*
Calvin is driving his favorite vehicle on the 101 freeway.
He notices that the check engine light of his vehicle is on, and he wants to service it immediately to avoid any risks.
Luckily, a service lane runs parallel to the highway. The service lane varies in width along its length.
|       | - |
|       | -- |
|       | --- |
|       | -- |
You will be given an array of widths at points along the road (indices), then a list of the indices of entry and exit points.
Considering each entry and exit point pair, calculate the maximum size vehicle that can travel that segment of the service lane safely.
For example, there are n=4 measurements yielding width[2,3,2,1].
If our entry index, i=1 and our exit, j=2, there are two segment widths of 2 and 3 respectively.
The widest vehicle that can fit through both is 2.
If i=2 and j=4, our widths are [3,2,1] which limits vehicle width to 1.
*Function Description
Complete the serviceLane function in the editor below.
It should return an array of integers representing the maximum width vehicle that can
pass through each segment of the highway described.
*serviceLane has the following parameter(s):
n: an integer denoting the size of the cases array
cases: a two dimensional array of integers where each element is an array of two integers representing
starting and ending indices for a segment to consider.
*Input Format
The first line of input contains two integers, n and t, where n denotes the number of
width measurements you will receive and t the number of test cases.
The next line has n space-separated integers which represent the array width[w[0],w[1],...,w[n-1]].
The next t lines contain two integers, i and j, where i is the start index and j is the end index of the segment being considered.
*Constraints
2<=n<=100000
1<=t<=1000
0<=i<j<n
2<=j-i+1<=min(n,1000)
1<=width[k]<=3, where 0<=k<n
*Output Format
For each test case, print the number that represents the largest vehicle type that can pass through the
entire segment of the service lane between indexes i and j inclusive.
*Sample Input
8 5
2 3 1 2 3 2 3 3
0 3
4 6
6 7
3 5
0 7
*Sample Output
1
2
3
2
1
*Explanation
Below is the representation of the lane:

   |HIGHWAY|Lane|    ->    Width

0: |       |--|            2
1: |       |---|           3
2: |       |-|             1
3: |       |--|            2
4: |       |---|           3
5: |       |--|            2
6: |       |---|           3
7: |       |---|           3
1. (0,3): From index 0 through 3 we have widths 2,3,1 and 2. Nothing wider than 1 can pass all segments.
2. (4,6): From index 4 through 6 we have width 3,2 and 3. Nothing wider than 2 can pass all segments.
3. (6,7): 3,3->3.
4. (3,5): 2,3,2->2
5. (0,7): 2,3,1,2,3,2,3,3->1.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ServiceLane {

    // Complete the serviceLane function below.
    static int[] serviceLane(int n, int[] width, int[][] cases) {
        int[] ret = new int[cases.length];
        for(int index = 0; index < cases.length; index++){
            int i = cases[index][0];
            int j = cases[index][1];
            int minWidth = 3;
            for(;i<=j; i++){
                minWidth = Math.min(minWidth,width[i]);
                if(minWidth == 1){
                    break;
                }
            }
            ret[index] = minWidth;
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nt = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nt[0]);

        int t = Integer.parseInt(nt[1]);

        int[] width = new int[n];

        String[] widthItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int widthItem = Integer.parseInt(widthItems[i]);
            width[i] = widthItem;
        }

        int[][] cases = new int[t][2];

        for (int i = 0; i < t; i++) {
            String[] casesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int casesItem = Integer.parseInt(casesRowItems[j]);
                cases[i][j] = casesItem;
            }
        }

        int[] result = serviceLane(n, width, cases);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

