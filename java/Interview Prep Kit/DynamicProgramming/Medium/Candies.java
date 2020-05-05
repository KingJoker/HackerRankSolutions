/*
Alice is a kindergarten teacher. She wants to give some candies to the children in her class.
All the children sit in a line and each of them has a rating score according to his or her performance in the class.
Alice wants to give at least 1 candy to each child.
If two children sit next to each other, then the one with the higher rating must get more candies.
Alice wants to minimize the total number of candies she must buy.
For example, assume her students' ratings are [4, 6, 4, 5, 6, 2].
She gives the students candy in the following minimal amounts: [1, 2, 1, 2, 3, 1].
She must buy a minimum of 10 candies.
*Function Description
Complete the candies function in the editor below. It must return the minimum number of candies Alice must buy.
*candies has the following parameter(s):
n: an integer, the number of children in the class
arr: an array of integers representing the ratings of each student
*Input Format
The first line contains an integer, n, the size of arr.
Each of the next n lines contains an integer arr[i] indicating the rating of the student at position i.
*Constraints
1<=n<=10^5
1<=arr[i]<=10^5
*Output Format
Output a single line containing the minimum number of candies Alice must buy.
*Sample Input 0
3
1
2
2
*Sample Output 0
4
*Explanation 0
Here 1, 2, 2 is the rating.
Note that when two children have equal rating, they are allowed to have different number of candies.
Hence optimal distribution will be 1, 2, 1.
*Sample Input 1
10
2
4
2
6
1
7
8
9
2
1
*Sample Output 1
19
*Explanation 1
Optimal distribution will be 1,2,1,2,1,2,3,4,2,1
*Sample Input 2
8
2
4
3
5
2
6
4
5
*Sample Output 2
12
*Explanation 2
Optimal distribution will be 12121212
 */

package DynamicProgramming.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Candies {

    // Complete the candies function below.
    static long candies(int n, int[] arr) {
        long totalCandies = 0;
        HashMap<Integer,Long> cache = new HashMap<>(n);
        for(int i = 0; i < n; i++){
            totalCandies += 1 + gradesLowerThanIterative(arr,i,cache);
        }
        return totalCandies;
    }
    static long candiesRecursive(int n, int[] arr) {
        long totalCandies = 0;
        HashMap<Integer,Long> cache = new HashMap<>(n);
        for(int i = 0; i < n; i++){
            totalCandies += 1 + gradesLowerThanRecursive(arr,i,cache);
        }
        return totalCandies;
    }

    public static long gradesLowerThanIterative(int[] arr, int index, HashMap<Integer,Long> cache){
        if(cache.containsKey(index)){
            return cache.get(index);
        }
        long left = 0;
        long right = 0;
        if(index - 1 >= 0 && arr[index - 1] < arr[index]){
            int i = index - 1;
            long lowestValue = 0;
            for(; i >= 0; i--){
                if(cache.containsKey(i)){
                    lowestValue = cache.get(i);
                    break;
                }
                if(i == 0 || !(i - 1 >= 0 && arr[i-1] < arr[i])){
                    break;
                }
            }
            for(; i < index; i++){
                cache.put(i,lowestValue++);
            }
            left = lowestValue;
        }
        if(index + 1 < arr.length && arr[index + 1] < arr[index]){
            int i = index + 1;
            long lowestValue = 0;
            for(; i < arr.length; i++){
                if(cache.containsKey(i)){
                    lowestValue = cache.get(i);
                    break;
                }
                if(i == arr.length - 1 || !(i + 1 < arr.length && arr[i+1] < arr[i])){
                    break;
                }
            }
            for(; i > index; i--){
                cache.put(i,lowestValue++);
            }
            right = lowestValue;
        }
        long lowestValue = Math.max(left,right);
        cache.put(index,lowestValue);
        return lowestValue;
    }
    /* For worst-case scenarios with large value arr.length, will cause StackOverflowException from
        call stack being too large
     */
    public static long gradesLowerThanRecursive(int[] arr, int index, HashMap<Integer,Long> cache){
        if(cache.containsKey(index)){
            return cache.get(index);
        }
        long left = 0;
        long right = 0;
        if(index - 1 >= 0 && arr[index - 1] < arr[index]){
            left = 1 + gradesLowerThanRecursive(arr, index - 1, cache );
        }
        if(index + 1 < arr.length && arr[index + 1] < arr[index]){
            right = 1 + gradesLowerThanRecursive(arr, index + 1, cache);
        }
        long maxLower = Math.max(left, right);
        cache.put(index, maxLower);
        return maxLower;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int arrItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arr[i] = arrItem;
        }

        long result = candies(n, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

