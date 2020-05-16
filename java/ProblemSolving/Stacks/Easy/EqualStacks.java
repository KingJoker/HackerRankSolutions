/*
You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height.
You can change the height of a stack by removing and discarding its topmost cylinder any number of times.
Find the maximum possible height of the stacks such that all of the stacks are exactly the same height.
This means you must remove zero or more cylinders from the top of zero or more of the three stacks until
they're all the same height, then print the height.
The removals must be performed in such a way as to maximize the height.
Note: An empty stack is still a stack.
*Input Format
The first line contains three space-separated integers, n1, n2, and n3, describing the respective number of
cylinders in stacks 1, 2, and 3.
The subsequent lines describe the respective heights of each cylinder in a stack from top to bottom:
The second line contains n1 space-separated integers describing the cylinder heights in stack 1.
The first element is the top of the stack.
The third line contains n2 space-separated integers describing the cylinder heights in stack 2.
The first element is the top of the stack.
The fourth line contains n3 space-separated integers describing the cylinder heights in stack 3.
The first element is the top of the stack.
*Constraints
0<n1,n2,n3<=10^5
0<height of any cylinder<=100
*Output Format
Print a single integer denoting the maximum height at which all stacks will be of equal height.
*Sample Input
5 3 4
3 2 1 1 1
4 3 2
1 1 4 1
*Sample Output
5
*Explanation
Initially, the stacks look like this:
    -
-
        -
        1
3   4   1

2
1   3
1       4
1   2   1
Observe that the three stacks are not all the same height. To make all stacks of equal height,
we remove the first cylinder from stacks 1 and 2, and then remove the top two cylinders from stack 3 (shown below).
    -
-
        -
        1
3   4   1
-   -   -

2
1   3
1       4
1   2   1

As a result, the stacks undergo the following change in height:
1. 8-3=5
2. 9-4=5
3. 7-1-1=5
All three stacks now have height=5. Thus, we print 5 as our answer.
 */

package Stacks.Easy;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class EqualStacks {

    /*
     * Complete the equalStacks function below.
     */
    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        Stack<Integer> stack3 = new Stack<>();
        int height1 = fillStack(h1,stack1);
        int height2 = fillStack(h2,stack2);
        int height3 = fillStack(h3,stack3);

        while(!(height1==height2 && height2==height3)){
            while(height1 > height2){
                height1 -= stack1.pop();
            }
            while(height2 > height3){
                height2 -= stack2.pop();
            }
            while(height3 > height1){
                height3 -= stack3.pop();
            }
        }
        return height1;
    }

    static int fillStack(int[] h, Stack<Integer> stack){
        int sum = 0;
        for(int i = h.length - 1; i >= 0; i--){
            stack.push(h[i]);
            sum+= h[i];
        }
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] n1N2N3 = scanner.nextLine().split(" ");

        int n1 = Integer.parseInt(n1N2N3[0].trim());

        int n2 = Integer.parseInt(n1N2N3[1].trim());

        int n3 = Integer.parseInt(n1N2N3[2].trim());

        int[] h1 = new int[n1];

        String[] h1Items = scanner.nextLine().split(" ");

        for (int h1Itr = 0; h1Itr < n1; h1Itr++) {
            int h1Item = Integer.parseInt(h1Items[h1Itr].trim());
            h1[h1Itr] = h1Item;
        }

        int[] h2 = new int[n2];

        String[] h2Items = scanner.nextLine().split(" ");

        for (int h2Itr = 0; h2Itr < n2; h2Itr++) {
            int h2Item = Integer.parseInt(h2Items[h2Itr].trim());
            h2[h2Itr] = h2Item;
        }

        int[] h3 = new int[n3];

        String[] h3Items = scanner.nextLine().split(" ");

        for (int h3Itr = 0; h3Itr < n3; h3Itr++) {
            int h3Item = Integer.parseInt(h3Items[h3Itr].trim());
            h3[h3Itr] = h3Item;
        }

        int result = equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

