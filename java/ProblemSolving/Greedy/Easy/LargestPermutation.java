/*
You are given an unordered array of unique integers incrementing from 1.
You can swap any two elements a limited number of times.
Determine the largest lexicographical value array that can be created by executing no more than the limited number of swaps.
For example, if arr=[1,2,3,4] and the maximum swaps k=1, the following arrays can be formed by swapping the 1 with the other elements:
[2,1,3,4]
[3,2,1,4]
[4,2,3,1]
The highest value of the four (including the original) is [4,2,3,1]. If k>=2, we can
swap to the highest possible value: [4,3,2,1].
*Function Description
Complete the largestPermutation function in the editor below.
It must return an array that represents the highest value permutation that can be formed.
largestPermutation has the following parameter(s):
k: an integer that represents the limit of swaps
arr: an array of integers
*Input Format
The first line contains two space-separated integers n and k, the length of arr and the maximum swaps that can be performed.
The second line contains n unique space-separated integers arr[i] where 1<=arr[i]<=n.
*Constraints
1<=n<=10^5
1<=k<=10^9
*Output Format
Print the lexicographically largest permutation you can make with at most k swaps.
*Sample Input 0
5 1
4 2 3 5 1
*Sample Output 0
5 2 3 4 1
*Explanation 0
You can swap any two numbers in [4,2,3,5,1] and see the largest permutation is [5,2,3,4,1]
*Sample Input 1
3 1
2 1 3
*Sample Output 1
3 1 2
*Explanation 1
With 1 swap we can get [1,2,3],[3,1,2] and [3,1,2]. Of these, [3,1,2] is the largest permutation.
*Sample Input 2
2 1
2 1
*Sample Output 2
2 1
*Explanation 2
We can see that [2,1] is already the largest permutation. We don't make any swaps.
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LargestPermutation {

    // Complete the largestPermutation function below.
    static int[] largestPermutation(int k, int[] arr) {
        TreeMap<Integer,Integer> map = new TreeMap<>(Comparator.reverseOrder());
        for(int i = 0; i < arr.length; i++){
            map.put(arr[i],i);
        }
        int index = 0;
        int count = 0;
        for(int num : map.keySet()){
            if(count >= k){
                break;
            }
            if(arr[index] != num){
                int index2 = map.get(num);
                map.put(arr[index],index2);
                arr[index2] = arr[index];
                arr[index] = num;
                count++;
            }
            index++;
        }
        return arr;
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

        int[] result = largestPermutation(k, arr);

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

