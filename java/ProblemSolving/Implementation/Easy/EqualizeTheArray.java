/*
Karl has an array of integers. He wants to reduce the array until all remaining elements are equal.
Determine the minimum number of elements to delete to reach his goal.
For example, if his array is arr=[1,2,2,3], we see that he can delete the 2 elements 1 and 3 leaving arr=[2,2].
He could also delete both twos and either the 1 or the 3, but that would take 3 deletions.
The minimum number of deletions is 2.
*Function Description
Complete the equalizeArray function in the editor below.
It must return an integer that denotes the minimum number of deletions required.
*equalizeArray has the following parameter(s):
arr: an array of integers
*Input Format
The first line contains an integer n, the number of elements in arr.
The next line contains n space-separated integers arr[i].
*Constraints
1<=n<=100
1<=arr[i]<=100
*Output Format
Print a single integer that denotes the minimum number of elements Karl must delete for all elements in the array to be equal.
*Sample Input
5
3 3 2 1 3
*Sample Output
2
*Explanation
Array arr=[3,3,2,1,3]. If we delete arr[2]=2 and arr[3]=1, all of the elements in the resulting array, A'=[3,3,3], will be equal.
Deleting these 2 elements is minimal.
Our only other options would be to delete 4 elements to get an array of either [1] or [2].
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class EqualizeTheArray {

    // Complete the equalizeArray function below.
    static int equalizeArray(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap();
        int max = 0;
        for(int i = 0; i < arr.length; i++){
            map.merge(arr[i],1,(oldValue,newValue) -> oldValue + newValue);
            max = Math.max(max,map.get(arr[i]));
        }
        return arr.length - max;
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

        int result = equalizeArray(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

