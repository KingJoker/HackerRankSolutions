/*
Marc loves cupcakes, but he also likes to stay fit.
Each cupcake has a calorie count, and Marc can walk a distance to expend those calories.
If Marc has eaten j cupcakes so far, after eating a cupcake with c calories he must walk at least 2^j*c miles to maintain his weight.
For example, if he eats 3 cupcakes with calorie counts in the following order: [5,10,7],
the miles he will need to walk are (2^0*5)+(2^1*10)+(2^2*7)=5+20+28=53.
This is not the minimum, though, so we need to test other orders of consumption.
In this case, our minimum miles is calculated as (2^0*10)+(2^1*7)+(2^2*5)=10+14+20=44.
Given the individual calorie counts for each of the cupcakes, determine the minimum number of miles Marc must walk to maintain his weight.
Note that he can eat the cupcakes in any order.
*Function Description
Complete the marcsCakewalk function in the editor below. It should return a long integer that represents the minimum miles necessary.
*marcsCakewalk has the following parameter(s):
calorie: an integer array that represents calorie count for each cupcake
*Input Format
The first line contains an integer n, the number of cupcakes in calorie.
The second line contains n space-separated integers calorie[i].
*Constraints
1<=n<=40
1<=c[i]<=1000
*Output Format
Print a long integer denoting the minimum number of miles Marc must walk to maintain his weight.
*Sample Input 0
3
1 3 2
*Sample Output 0
11
*Explanation 0
Let's say the number of miles Marc must walk to maintain his weight is miles.
He can minimize miles by eating the n=3 cupcakes in the following order:
1. Eat the cupcake with c[1]=3 calories, so miles=0+(3*2^0)=3.
2. Eat the cupcake with c[2]=2 calories, so miles=3+(2*2^1)=7.
3. Eat the cupcake with c[0]=1 calories, so miles=7+(1*2^2)=11.
We then print the final value of miles, which is 11, as our answer.
*Sample Input 1
4
7 4 9 6
*Sample Output 1
79
*Explanation 1
(2^0*9)+(2^1*7)+(2^2*6)+(2^3*4)=9+14+24+32=79
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MarcsCakewalk {

    // Complete the marcsCakewalk function below.
    static long marcsCakewalk(int[] calorie) {
        Arrays.parallelSort(calorie);
        long miles = 0;
        for(int i = calorie.length - 1; i >= 0; i--){
            miles += Math.pow(2,calorie.length - 1 - i) * calorie[i];
        }
        return miles;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] calorie = new int[n];

        String[] calorieItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int calorieItem = Integer.parseInt(calorieItems[i]);
            calorie[i] = calorieItem;
        }

        long result = marcsCakewalk(calorie);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

