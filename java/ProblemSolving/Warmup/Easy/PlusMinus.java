/*
Given an array of integers, calculate the fractions of its elements that are positive, negative, and are zeros.
Print the decimal value of each fraction on a new line.
Note: This challenge introduces precision problems.
The test cases are scaled to six decimal places, though answers with absolute error of up to 10^-4 are acceptable.
For example, given the array arr=[1,1,0,-1,-1] there are 5 elements, two positive, two negative and one zero.
Their ratios would be 2/5=0.400000, 2/5=0.400000 and 1/5=0.200000. It should be printed as
0.400000
0.400000
0.200000
*Function Description
Complete the plusMinus function in the editor below.
It should print out the ratio of positive, negative and zero items in the array, each on a separate line rounded to six decimals.
*plusMinus has the following parameter(s):
arr: an array of integers
*Input Format
The first line contains an integer, n, denoting the size of the array.
The second line contains n space-separated integers describing an array of numbers .
*Constraints
0<n<=100
-100<=arr[i]<=100
*Output Format
You must print the following 3 lines:
1. A decimal representing of the fraction of positive numbers in the array compared to its size.
2. A decimal representing of the fraction of negative numbers in the array compared to its size.
3. A decimal representing of the fraction of zeros in the array compared to its size.
*Sample Input
6
-4 3 -9 0 4 1
*Sample Output
0.500000
0.333333
0.166667
Explanation
There are 3 positive numbers, 2 negative numbers, and 1 zero in the array.
The proportions of occurrence are positive: 3/6=.500000, negative: 2/6=0.33333 and zeros: 1/6=0.166667.
 */

package Warmup.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PlusMinus {

    // Complete the plusMinus function below.
    static void plusMinus(int[] arr) {
        double positive = 0;
        double negative = 0;
        double zero = 0;
        for(int element : arr){
            if(element < 0){
                negative++;
            }
            else if(element > 0){
                positive++;
            }
            else{
                zero++;
            }
        }
        BigDecimal out = new BigDecimal(positive/arr.length);
        out.setScale(6,RoundingMode.HALF_UP);
        System.out.println(String.format("%.6f",out.doubleValue()));

        out = new BigDecimal(negative/arr.length);
        out.setScale(6,RoundingMode.HALF_UP);
        System.out.println(String.format("%.6f",out.doubleValue()));

        out = new BigDecimal(zero/arr.length);
        out.setScale(6,RoundingMode.HALF_UP);
        System.out.println(String.format("%.6f",out.doubleValue()));
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

        plusMinus(arr);

        scanner.close();
    }
}

