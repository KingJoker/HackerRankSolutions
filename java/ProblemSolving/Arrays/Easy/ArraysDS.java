/*
An array is a type of data structure that stores elements of the same type in a contiguous block of memory.
In an array, A, of size N, each memory location has some unique index, i (where 0<=i<N),
that can be referenced as A[i].
Given an array, A, of N integers, print each element in reverse order as a single line of space-separated integers.
*Input Format
The first line contains an integer, N (the number of integers in A).
The second line contains N space-separated integers describing A.
*Constraints
1<=N<=10^3
1<=A[i]<=10^4, where A[i]is the ith integer in A
*Output Format
Print all N integers in A in reverse order as a single line of space-separated integers.
*Sample Input 1
4
1 4 3 2
*Sample Output 1
2 3 4 1
 */

package Arrays.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ArraysDS {

    // Complete the reverseArray function below.
    static int[] reverseArray(int[] a) {
        int[] ret = new int[a.length];
        for(int i = a.length - 1; i >= 0; i--){
            ret[(a.length-1) - i] = a[i];
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int arrCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[arrCount];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < arrCount; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int[] res = reverseArray(arr);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

