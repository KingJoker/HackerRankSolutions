/*
Given an array of integers, find the subset of non-adjacent elements with the maximum sum. Calculate the sum of that subset.
For example, given an array arr=[-2,1,3,-4,5] we have the following possible subsets:
Subset      Sum
[-2, 3, 5]   6
[-2, 3]      1
[-2, -4]    -6
[-2, 5]      3
[1, -4]     -3
[1, 5]       6
[3, 5]       8
Our maximum subset sum is 8.
*Function Description
Complete the maxSubsetSum function in the editor below. It should return an integer representing the maximum subset sum for the given array.
*maxSubsetSum has the following parameter(s):
arr: an array of integers
*Input Format
The first line contains an integer, n.
The second line contains n space-separated integers arr[i].
*Constraints
a<=n<=10^5
-10^4<=arr[i]<=10^4
*Output Format
Return the maximum sum described in the statement.
*Sample Input 0
5
3 7 4 6 5
*Sample Output 0
13
*Explanation 0
Our possible subsets are [3,4,5],[3,4],[3,6],[3,5],[7,6],[7,5] and [4,5]. The largest subset sum is 13 from subset [7,6]
*Sample Input 1
5
2 1 5 8 4
*Sample Output 1
11
*Explanation 1
Our subsets are [2,5,4],[2,5],[2,8],[2,4],[1,8],[1,4] and [5,4]. The maximum subset sum is 11 from the first subset listed.
*Sample Input 2
5
3 5 -7 8 10
*Sample Output 2
15
*Explanation 2
Our subsets are [3,-7,10],[3,8],[3,10],[5,8],[5,10] and [-7,10]. The maximum subset sum is 15 from the fifth subset listed.
 */

package DynamicProgramming.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaxArraySum {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        int[] maxSums = new int[arr.length];
        int maxSum = Integer.MIN_VALUE;
        for(int i = arr.length - 1; i >= 0; i--){
            if(i + 2 > arr.length - 1){
                maxSums[i] = arr[i];
            }
            else{
                int maxSumLookahead = maxSums[i+2];
                if(i + 3 <= arr.length - 1){
                    maxSumLookahead = Math.max(maxSumLookahead, maxSums[i+3]);
                }
                maxSumLookahead = Math.max(maxSumLookahead, maxSumLookahead + arr[i]);
                maxSums[i] = Math.max(maxSumLookahead, arr[i]);
            }
            maxSum = Math.max(maxSum,maxSums[i]);
        }
        return maxSum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
