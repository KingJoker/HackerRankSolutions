/*
The median of a list of numbers is essentially it's middle element after sorting.
The same number of elements occur after it as before.
Given a list of numbers with an odd number of elements, can you find the median?
For example, the median of arr=[1,2,3,4,5] is 3, the middle element in the sorted array.
*Function Description
Complete the findMedian function in the editor below. It must return an integer that represents the median of the array.
*findMedian has the following parameter(s):
arr: an unsorted array of integers
*Input Format
The first line contains the integer n, the size of arr.
The second line contains n space-separated integers arr[i]
*Constraints
1<=n<=1000001
n is odd
-10000<=arr[i]<=10000
*Output Format
Output one integer, the median.
*Sample Input 0
7
0 1 2 4 6 5 3
*Sample Output 0
3
*Explanation 0
The sorted arr=[0,1,2,3,4,5,6]. It's middle element is at arr[3]=3.
 */

package Sorting.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FindTheMedian {

    // Complete the findMedian function below.
    static int findMedian(int[] arr) {
        Arrays.parallelSort(arr);
        return arr[arr.length/2];
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

        int result = findMedian(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

