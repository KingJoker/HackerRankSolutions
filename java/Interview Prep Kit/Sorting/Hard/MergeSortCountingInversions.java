/*
In an array, arr, the elements at indices i and j (where i<j) form an inversion if arr[i]>arr[j].
In other words, inverted elements arr[i] and arr[j] are considered to be "out of order".
To correct an inversion, we can swap adjacent elements.
For example, consider the dataset arr=[2,4,1]. It has two inversions: (4,1) and (2,1).
To sort the array, we must perform the following two swaps to correct the inversions:
arr=[2,4,1]-->swap(arr[1],arr[2])-->swap(arr[0],arr[1])-->[1,2,4]
Given d datasets, print the number of inversions that must be swapped to sort each dataset on a new line.
*Function Description
Complete the function countInversions in the editor below.
It must return an integer representing the number of inversions required to sort the array.
*countInversions has the following parameter(s):
arr: an array of integers to sort .
*Input Format
The first line contains an integer, d, the number of datasets.
Each of the next d pairs of lines is as follows:
1.The first line contains an integer, n, the number of elements in arr.
2.The second line contains n space-separated integers, arr[i].
*Constraints
1<=d<=15
1<=n<=10^5
1<=arr[i]<=10^7
*Output Format
For each of the d datasets, return the number of inversions that must be swapped to sort the dataset.
*Sample Input
2
5
1 1 1 2 2
5
2 1 3 1 2
*Sample Output
0
4
*Explanation
We sort the following d=2 datasets:
1. arr=[1,1,1,2,2] is already sorted, so there are no inversions for us to correct. Thus, we print 0 on a new line.
2. arr=[2,1,3,1,2]--1 swap->[1,2,3,1,2]--2 swaps->[1,1,2,3,2]--1 swap->1,1,2,2,3]
We performed a total of 1+2+1=4 swaps to correct inversions.
 */

package Sorting.Hard;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MergeSortCountingInversions {

    // Complete the countInversions function below.
    static long countInversions(int[] arr) {
        return mergeSort(arr,0,arr.length);
    }

    static long mergeSort(int[] arr, int left, int right){
        if(right - left <= 1){
            return 0;
        }
        long sum = 0;
        int mid = (left + right)/2;
        sum+= mergeSort(arr, left, mid);
        sum+= mergeSort(arr,mid,right);
        int[] temp = new int[right-left];
        int leftIndex = left;
        int rightIndex = mid;
        for(int i = 0; i < temp.length; i++){
            if(leftIndex >= mid){
                temp[i] =  arr[rightIndex++];
            }
            else if(rightIndex >= right){
                temp[i] = arr[leftIndex++];
            }
            else{
                if(arr[leftIndex] <= arr[rightIndex]){
                    temp[i] = arr[leftIndex++];
                }
                else{
                    temp[i] = arr[rightIndex++];
                    sum += mid - leftIndex;
                }
            }
        }
        for(int i = 0; i < temp.length; i++){
            arr[left + i] = temp[i];
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            long result = countInversions(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
