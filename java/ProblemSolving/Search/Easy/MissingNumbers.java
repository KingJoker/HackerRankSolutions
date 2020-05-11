/*
Numeros the Artist had two lists that were permutations of one another. He was very proud.
Unfortunately, while transporting them from one exhibition to another, some numbers were lost out of the first list.
Can you find the missing numbers?
As an example, the array with some numbers missing, arr=[7,2,5,3,5,3].
The original array of numbers brr=[7,2,5,4,6,3,5,3]. The numbers missing are [4,6].
*Notes
If a number occurs multiple times in the lists, you must ensure that the frequency of that number in both lists is the same.
If that is not the case, then it is also a missing number.
You have to print all the missing numbers in ascending order.
Print each missing number once, even if it is missing multiple times.
The difference between maximum and minimum number in the second list is less than or equal to 100.
*Function Description
Complete the missingNumbers function in the editor below. It should return a sorted array of missing numbers.
*missingNumbers has the following parameter(s):
arr: the array with missing numbers
brr: the original array of numbers
*Input Format
There will be four lines of input:
n - the size of the first list, arr
The next line contains n space-separated integers arr[i]
m - the size of the second list, brr
The next line contains m space-separated integers brr[i]
*Constraints
1<=n,m<=2*10^5
n<=m
1<=brr[i]<=10^4
Xmax-Xmin<101
*Output Format
Output the missing numbers in ascending order.
*Sample Input
10
203 204 205 206 207 208 203 204 205 206
13
203 204 204 205 206 207 205 208 203 206 205 206 204
*Sample Output
204 205 206
*Explanation
204 is present in both arrays. Its frequency in arr is 2, while its frequency in brr is 3.
Similarly, 205 and 206 occur twice in arr, but three times in brr. The rest of the numbers have the same frequencies in both lists.
 */

package Search.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MissingNumbers {

    // Complete the missingNumbers function below.
    static int[] missingNumbers(int[] arr, int[] brr) {
        HashMap<Integer,Integer> mapA = new HashMap<>();
        HashMap<Integer,Integer> mapB = new HashMap<>();
        for(int elem : arr){
            mapA.merge(elem, 1, (oldValue, one) -> oldValue + one);
        }
        for(int elem : brr){
            mapB.merge(elem,1,(oldValue, one) -> oldValue + one);
        }
        TreeSet<Integer> missing = new TreeSet<>();
        for(int elem : mapB.keySet()){
            if(!mapA.containsKey(elem) || (mapA.get(elem) < mapB.get(elem))){
                missing.add(elem);
            }
        }
        return missing.stream().mapToInt(Integer::intValue).toArray();
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

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] brr = new int[m];

        String[] brrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int brrItem = Integer.parseInt(brrItems[i]);
            brr[i] = brrItem;
        }

        int[] result = missingNumbers(arr, brr);

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

