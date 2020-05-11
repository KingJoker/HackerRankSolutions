/*
This is a simple challenge to get things started. Given a sorted array (arr) and a number (V),
can you print the index location of V in the array?
For example, if arr=[1,2,3] and V=3, you would print 2 for a zero-based index array.
If you are going to use the provided code for I/O, this next section is for you.
*Function Description
Complete the introTutorial function in the editor below. It must return an integer representing the zero-based index of V.
*introTutorial has the following parameter(s):
arr: a sorted array of integers
V: an integer to search for
The next section describes the input format. You can often skip it, if you are using included methods or code stubs.
*Input Format
The first line contains an integer, V, a value to search for.
The next line contains an integer, n, the size of arr.
The last line contains n space-separated integers, each a value of arr[i] where 0<=i<n.
*Output Format
Output the index of V in the array.
The next section describes the constraints and ranges of the input. You should check this section to know the range of the input.
*Constraints
1<=n<=1000
-1000<=V<=1000, V∈arr
It is guaranteed that V will occur in arr exactly once.
This "sample" shows the first input test case. It is often useful to go through the sample to understand a challenge.
*Sample Input 0
4
6
1 4 5 7 9 12
*Sample Output 0
1
Explanation 0
V=4. The value 4 is the 2nd element in the array, but its index is 1 since in this case,
array indices start from 0 (see array definition under Input Format).
 */

package Sorting.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class IntroToTutorialChallenges {

    // Complete the introTutorial function below.
    static int introTutorial(int V, int[] arr) {
        return Arrays.binarySearch(arr,V);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int V = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = introTutorial(V, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

