/*
A left rotation operation on an array shifts each of the array's elements 1 unit to the left.
For example, if 2 left rotations are performed on array [1,2,3,4,5], then the array would become [3,4,5,1,2].
Given an array a of n integers and a number, d, perform d left rotations on the array.
Return the updated array to be printed as a single line of space-separated integers.
*Function Description
Complete the function rotLeft in the editor below. It should return the resulting array of integers.
*rotLeft has the following parameter(s):
An array of integers a.
An integer d, the number of rotations.
*Input Format
The first line contains two space-separated integers n and d, the size of a and the number of left rotations you must perform.
The second line contains n space-separated integers a[i].
*Constraints
1<=n<=10^5
1<=d<=n
1<=a[i]<=10^6
*Output Format
Print a single line of n space-separated integers denoting the final state of the array after performing d left rotations.
*Sample Input
5 4
1 2 3 4 5
*Sample Output
5 1 2 3 4
*Explanation
When we perform d=4 left rotations, the array undergoes the following sequence of changes:
[1,2,3,4,5]->[2,3,4,5,1]->[3,4,5,1,2]->[4,5,1,2,3]->[5,1,2,3,4]
*/
package Arrays.Easy;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LeftRotation {

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        int numRotations = d % a.length;
        int[] ret = new int[a.length];
        for(int i = 0; i < numRotations; i++){
            ret[a.length - numRotations + i] = a[i];
        }
        int index = 0;
        for(int i = numRotations; i < a.length; i++){
            ret[index++] = a[i];
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int[] result = rotLeft(a, d);

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
