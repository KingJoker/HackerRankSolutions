/*
Skyline Real Estate Developers is planning to demolish a number of old, unoccupied buildings and construct a shopping mall in their place.
Your task is to find the largest solid area in which the mall can be constructed.
There are a number of buildings in a certain two-dimensional landscape.
Each building has a height, given by h[i] where i∈[1,n]. If you join k adjacent buildings, they will form a solid rectangle of area k*min(h[i],h[i+1],...,h[i+k-1].
For example, the heights array h=[3,2,3]. A rectangle of height h=2 and length k=3 can be constructed within the boundaries.
The area formed is h*k=2*3=6.
*Function Description
Complete the function largestRectangle int the editor below.
It should return an integer representing the largest rectangle that can be formed within the bounds of consecutive buildings.
*largestRectangle has the following parameter(s):
h: an array of integers representing building heights
*Input Format
The first line contains n, the number of buildings.
The second line contains n space-separated integers, each representing the height of a building.
*Constraints
1<=n<=10^5
1<=h[i]<=10^6
*Output Format
Print a long integer representing the maximum area of rectangle formed.
Sample Input
5
1 2 3 4 5
*Sample Output
9
Explanation

An illustration of the test case follows.
             _
          _ | |
       _ | |  |
    _ |*| *| *|
 _ | | *| *| *|
| |  | *| *| *|
 1  2  3  4  5
 */

package StacksAndQueues.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LargestRectangle {

    // Complete the largestRectangle function below.
    static long largestRectangle(int[] h) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{h[0],0});
        long max = 0;
        for(int i = 1; i < h.length; i++){
            if(h[i-1] >= h[i]){
                int end = i - 1;
                if(i == h.length - 1){
                    end = h.length - 1;
                }
                int newLeft = i;
                while(stack.size() > 0 && stack.peek()[0] >= h[i]){
                    int[] current = stack.pop();
                    newLeft = current[1];
                    long currentArea = current[0] * (i - current[1]);
                    max = Math.max(max, currentArea);
                }
                stack.push(new int[]{h[i],newLeft});
            }
            else{
                stack.push(new int[]{h[i],i});
            }

        }
        while(stack.size() > 0){
            int[] current = stack.pop();
            long currentArea = current[0] * (h.length - current[1]);
            max = Math.max(max, currentArea);
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] h = new int[n];

        String[] hItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int hItem = Integer.parseInt(hItems[i]);
            h[i] = hItem;
        }

        long result = largestRectangle(h);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

