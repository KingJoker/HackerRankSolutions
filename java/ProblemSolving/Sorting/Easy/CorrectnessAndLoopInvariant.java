/*
In the InsertionSort code below, there is an error. Can you fix it? Print the array only once, when it is fully sorted.
*Input Format
There will be two lines of input:
s - the size of the array
arr - the list of numbers that makes up the array
*Constraints
1<=s<=1000
-1500<=V<=1500, V∈arr
*Output Format
Output the numbers in order, space-separated on one line.
*Sample Input
6
7 4 3 5 6 2
*Sample Output
2 3 4 5 6 7
*Explanation
The corrected code returns the sorted array.
 */

package Sorting.Easy;

import java.io.*;
import java.util.*;

public class CorrectnessAndLoopInvariant {

    /*
    //Original Code
    public static void insertionSort(int[] A){
        for(int i = 1; i < A.length; i++){
            int value = A[i];
            int j = i - 1;
            while(j > 0 && A[j] > value){
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = value;
        }

        printArray(A);
    }*/


    public static void insertionSort(int[] A){
        for(int i = 1; i < A.length; i++){
            int value = A[i];
            int j = i - 1;
            while(j >= 0 && A[j] > value){
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = value;
        }

        printArray(A);
    }


    static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] ar = new int[n];
        for(int i=0;i<n;i++){
            ar[i]=in.nextInt();
        }
        insertionSort(ar);
    }
}
