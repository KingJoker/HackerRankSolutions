/*
Sorting is useful as the first step in many different tasks.
The most common task is to make finding things easier, but there are other uses as well.
In this case, it will make it easier to determine which pair or pairs of elements have the smallest absolute difference between them.
For example, if you've got the list [5,2,3,4,1], sort it as [1,2,3,4,5] to see that several
pairs have the minimum difference of 1: [(1,2),(2,3),(3,4),(4,5). The return array would be [1,2,2,3,3,4,4,5].
Given a list of unsorted integers, arr, find the pair of elements that have the smallest absolute difference between them.
If there are multiple pairs, find them all.
*Function Description
Complete the closestNumbers function in the editor below. It must return an array of integers as described.
*closestNumbers has the following parameter(s):
arr: an array of integers
*Input Format
The first line contains a single integer n, the length of arr.
The second line contains n space-separated integers, arr[i].
*Constraints
2<=n<=200000
-10^7<=arr[i]<=10^7
All a[i] are unique in arr.
*Output Format
Output the pairs of elements with the smallest difference.
If there are multiple pairs, output all of them in ascending order,
all on the same line with just a single space between each pair of numbers.
A number may be part of two pairs when paired with its predecessor and its successor.
*Sample Input 0
10
-20 -3916237 -357920 -3620601 7374819 -7330761 30 6246457 -6461594 266854
*Sample Output 0
-20 30
*Explanation 0
(30) - (-20) = 50, which is the smallest difference.
*Sample Input 1
12
-20 -3916237 -357920 -3620601 7374819 -7330761 30 6246457 -6461594 266854 -520 -470
*Sample Output 1
-520 -470 -20 30
*Explanation 1
(-470) - (-520) = 30 - (-20) = 50, which is the smallest difference.
*Sample Input 2
4
5 4 3 2
*Sample Output 2
2 3 3 4 4 5
Explanation 2
Here, the minimum difference will be 1.
Valid pairs are (2, 3), (3, 4), and (4, 5). We print the elements of each pair, space-separated on a single line.
 */

package Sorting.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.*;

public class ClosestNumbers {

    // Complete the closestNumbers function below.
    static int[] closestNumbers(int[] arr) {
        ArrayList<Integer> pairs = new ArrayList<>();
        Arrays.parallelSort(arr);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length-1; i++){
            int diff = Math.abs(arr[i] - arr[i+1]);
            if(diff < min){
                min=diff;
                pairs = new ArrayList<>();
                pairs.add(arr[i]);
                pairs.add(arr[i+1]);
            }
            else if(diff == min){
                pairs.add(arr[i]);
                pairs.add(arr[i+1]);
            }
        }
        return pairs.stream().mapToInt(x -> x).toArray();
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

        int[] result = closestNumbers(arr);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

