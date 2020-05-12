/*
Given an array of stick lengths, use 3 of them to construct a non-degenerate triange with the maximum possible perimeter.
Print the lengths of its sides as 3 space-separated integers in non-decreasing order.
If there are several valid triangles having the maximum perimeter:
Choose the one with the longest maximum side.
1. If more than one has that maximum, choose from them the one with the longest minimum side.
2. If more than one has that maximum as well, print any one them.
3. If no non-degenerate triangle exists, print -1.
For example, assume there are stick lengths sticks=[1,2,3,4,5,10].
The triplet (1,2,3) will not form a triangle.
Neither will (4,5,10) or (2,3,5), so the problem is reduced to (2,3,4) and (3,4,5). The longer perimeter is 3+4+5=12.
*Function Description
Complete the maximumPerimeterTriangle function in the editor below.
It should return an array of 3 integers that represent the side lengths of the chosen triangle in non-decreasing order.
*maximumPerimeterTriangle has the following parameter(s):
sticks: an integer array that represents the lengths of sticks available
*Input Format
The first line contains single integer n, the size of array sticks.
The second line contains n space-separated integers sticks[i], each a stick length.
*Constraints
3<=n<=50
1<=sticks[i]<=10^9
*Output Format
Print the lengths of the 3 chosen sticks as space-separated integers in non-decreasing order.
If no non-degenerate triangle can be formed, print -1.
*Sample Input 0
5
1 1 1 3 3
*Sample Output 0
1 3 3
*Explanation 0
There are 2 possible unique triangles:
1. (1,1,1)
2. (1,3,3)
The second triangle has the largest perimeter, so we print its side lengths on a new line in non-decreasing order.
*Sample Input 1
3
1 2 3
*Sample Output 1
-1
*Explanation 1
The triangle (1,2,3) is degenerate and thus can't be constructed, so we print -1 on a new line.
*Sample Input 2
6
1 1 1 2 3 5
*Sample Output 2
1 1 1
*Explanation 2
The triangle (1,1,1) is the only valid triangle.
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaximumPerimeterTriangle {

    // Complete the maximumPerimeterTriangle function below.
    static int[] maximumPerimeterTriangle(int[] sticks) {
        Arrays.parallelSort(sticks);
        for(int i = sticks.length-3; i >=0; i--){
            for(int j = i + 1; j < sticks.length - 1; j++){
                int target = sticks[i] + sticks[j];
                int index = Arrays.binarySearch(sticks, target);
                if(index < 0){
                    index = -1 * (index + 1);
                }
                if(index == sticks.length){
                    index--;
                }
                for(int k = index; k >= 0; k--){
                    if(k == i || k == j){
                        continue;
                    }
                    if(sticks[i] + sticks[j] > sticks[k] && sticks[i] + sticks[k] > sticks[j] && sticks[j] + sticks[k] > sticks[i]){
                        int[] ret =  new int[]{sticks[i],sticks[j],sticks[k]};
                        Arrays.sort(ret);
                        return ret;
                    }
                }
            }
        }
        return new int[]{-1};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] sticks = new int[n];

        String[] sticksItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int sticksItem = Integer.parseInt(sticksItems[i]);
            sticks[i] = sticksItem;
        }

        int[] result = maximumPerimeterTriangle(sticks);

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

