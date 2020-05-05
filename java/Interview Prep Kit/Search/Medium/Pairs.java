/*
You will be given an array of integers and a target value. Determine the number of pairs of array elements that have a difference equal to a target value.
For example, given an array of [1, 2, 3, 4] and a target value of 1, we have three values meeting the condition: 2-1=1, 3-2=1, and 4-3=1.
*Function Description
Complete the pairs function below. It must return an integer representing the number of element pairs having the required difference.
*pairs has the following parameter(s):
k: an integer, the target difference
arr: an array of integers
*Input Format
The first line contains two space-separated integers n and k, the size of arr and the target value.
The second line contains n space-separated integers of the array .
*Constraints
2<=n<=10^5
0<k<10^9
0<arr[i]<2^31-1
each integer arr[i] will be unique
*Output Format
An integer representing the number of pairs of integers whose difference is k.
*Sample Input
5 2
1 5 3 4 2
*Sample Output
3
*Explanation
There are 3 pairs of integers in the set with a difference of 2: [5,3], [4,2] and [3,1] .
 */

package Search.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Pairs {

    // Complete the pairs function below.
    static int pairs(int k, int[] arr) {
        HashSet<Integer> values = new HashSet<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        int count = 0;
        for(int i : arr){
            int lookingFor = i - k;
            if(values.contains(lookingFor)){
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = pairs(k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

