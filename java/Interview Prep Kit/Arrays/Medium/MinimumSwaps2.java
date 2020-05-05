/*
You are given an unordered array consisting of consecutive integers ∈[1, 2, 3, ..., n] without any duplicates.
You are allowed to swap any two elements. You need to find the minimum number of swaps required to sort the array in ascending order.
For example, given the array arr=[7,1,3,2,4,5,6] we perform the following steps:
i   arr                     swap (indices)
0   [7, 1, 3, 2, 4, 5, 6]   swap (0,3)
1   [2, 1, 3, 7, 4, 5, 6]   swap (0,1)
2   [1, 2, 3, 7, 4, 5, 6]   swap (3,4)
3   [1, 2, 3, 4, 7, 5, 6]   swap (4,5)
4   [1, 2, 3, 4, 5, 7, 6]   swap (5,6)
5   [1, 2, 3, 4, 5, 6, 7]
It took 5 swaps to sort the array.
*Function Description
Complete the function minimumSwaps in the editor below.
It must return an integer representing the minimum number of swaps to sort the array.
*minimumSwaps has the following parameter(s):
arr: an unordered array of integers
*Input Format
The first line contains an integer, n, the size of arr.
The second line contains n space-separated integers arr[i].
*Constraints
1<=n<=10^5
1<=arr[i]<=n
*Output Format
Return the minimum number of swaps to sort the given array.
*Sample Input 0
4
4 3 1 2
*Sample Output 0
3
*Explanation 0

Given array arr: [4,3,1,2]
After swapping (0,2) we get arr: [1,3,4,2]
After swapping (1,2) we get arr: [1,4,3,2]
After swapping (1,3) we get arr: [1,2,3,4]
So, we need a minimum of 3 swaps to sort the array in ascending order.
*Sample Input 1
5
2 3 4 1 5
*Sample Output 1
3
*Explanation 1
Given array arr: [2,3,4,1,5]
After swapping (2,3) we get arr: [2,3,1,4,5]
After swapping (0,1) we get arr: [3,2,1,4,5]
After swapping (0,2) we get arr: [1,2,3,4,5]
So, we need a minimum of 3 swaps to sort the array in ascending order.
*Sample Input 2
7
1 3 5 2 4 6 7
*Sample Output 2
3
*Explanation 2
Given array arr: [1,3,5,2,4,6,7]
After swapping (1,3) we get arr: [2,3,1,4,5]
After swapping (2,3) we get arr: [3,2,1,4,5]
After swapping (3,4) we get arr: [1,2,3,4,5]
So, we need a minimum of  swaps to sort the array in ascending order.
 */

package Arrays.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class MinimumSwaps2 {

    // Complete the minimumSwaps function below.
    static int minimumSwaps(int[] arr) {
       int swaps = 0;
       for(int i = 0; i < arr.length; i++)
       {
           if(arr[i] != i+1){
               int swapIndex = 0;
               for(int j = i+1; j < arr.length; j++){
                   if(arr[j] == i + 1){
                       swapIndex = j;
                       break;
                   }
               }
               arr[swapIndex] = arr[i];
               arr[i] = i+1;
               swaps++;
           }
       }
       return swaps;
    }

    static int minimumSwapsCache(int[] arr){
        int swaps = 0;
        HashMap<Integer,Integer> cache = new HashMap<>();
        IntStream.range(0,arr.length).forEach(x -> cache.put(arr[x],x));
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != i+1){
                int swapIndex = cache.get(i + 1);
                cache.put(i + 1, i);
                cache.put(arr[i],swapIndex);
                arr[swapIndex] = arr[i];
                arr[i] = i + 1;
                swaps++;
            }
        }
        return swaps;
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

        int res = minimumSwapsCache(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
