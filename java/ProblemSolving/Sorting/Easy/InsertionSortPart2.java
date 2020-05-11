/*
In Insertion Sort Part 1, you inserted one element into an array at its correct sorted position.
Using the same approach repeatedly, can you sort an entire array?
Guideline: You already can place an element into a sorted array.
How can you use that code to build up a sorted array, one element at a time?
Note that in the first step, when you consider an array with just the first element,
it is already sorted since there's nothing to compare it to.
In this challenge, print the array after each iteration of the insertion sort, i.e., whenever the next element
has been inserted at its correct position.
Since the array composed of just the first element is already sorted, begin printing after placing the second element.
For example, there are n=7 elements in arr=[3,4,7,5,6,2,1]. Working from left to right, we get the following output:
3 4 7 5 6 2 1
3 4 7 5 6 2 1
3 4 5 7 6 2 1
3 4 5 6 7 2 1
2 3 4 5 6 7 1
1 2 3 4 5 6 7
*Function Description
Complete the insertionSort2 function in the editor below.
At each iteration, it should print the array as space-separated integers on a separate line.
*insertionSort2 has the following parameter(s):
n: an integer representing the length of the array arr
arr: an array of integers
*Input Format
The first line contains an integer, n, the size of arr.
The next line contains n space-separated integers arr[i].
*Constraints
1<=n<=1000
-10000<=arr[i]<=10000,0<=i<n
*Output Format
On each line, output the entire array at every iteration.
*Sample Input
6
1 4 3 5 6 2
*Sample Output
1 4 3 5 6 2
1 3 4 5 6 2
1 3 4 5 6 2
1 3 4 5 6 2
1 2 3 4 5 6
*Explanation
Skip testing 1 against itself at position 0. It is sorted.
Test position 1 against position 0:4>1 , no more to check, no change.
Print arr
Test position 2 against positions 1 and 0:
3<4, new position may be 1. Keep checking.
3>1, so insert 3 at position 1 and move others to the right.
Print arr
Test position 3 against positions 2,1,0 (as necessary): no change.
Print arr
Test position 4 against positions 3,2,1,0: no change.
Print arr
Test position 5 against positions 4,3,2,1,0, insert 2 at position 1 and move others to the right.
Print arr
 */

package Sorting.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class InsertionSortPart2 {

    // Complete the insertionSort2 function below.
    static void insertionSort2(int n, int[] arr) {
        for(int i = 1; i < arr.length; i++){
            int store = arr[i];
            for(int j = i; j >= 0; j--){
                if(j == 0 || arr[j-1] < store){
                    arr[j] = store;
                    break;
                }
                else{
                    arr[j] = arr[j-1];
                }
            }
            printArray(arr);
        }
    }

    private static void printArray(int[] arr){
        for(int elem : arr){
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        insertionSort2(n, arr);

        scanner.close();
    }
}

