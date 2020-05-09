/*
Alexa has two stacks of non-negative integers, stack A=[a[0],a[1],...,a[n-1] and stack B=[b[0],b[1],...,b[m-1]
where index 0 denotes the top of the stack. Alexa challenges Nick to play the following game:
In each move, Nick can remove one integer from the top of either stack A or stack B.
Nick keeps a running sum of the integers he removes from the two stacks.
Nick is disqualified from the game if, at any point, his running sum becomes greater than some integer x given at the beginning of the game.
Nick's final score is the total number of integers he has removed from the two stacks.
Given A, B, and x for g games, find the maximum possible score Nick can achieve
(i.e., the maximum number of integers he can remove without being disqualified) during each game and print it on a new line.
*Input Format
The first line contains an integer, g (the number of games). The 3*g subsequent lines describe each game in the following format:
1.The first line contains three space-separated integers describing the respective values of n (the number of integers in stack A),
m (the number of integers in stack B), and x (the number that the sum of the integers removed from the two stacks cannot exceed).
2.The second line contains n space-separated integers describing the respective values of a[0],a[1],...a[n-1].
3.The third line contains m space-separated integers describing the respective values of b[0],b[1],...b[m-1].
*Constraints
1<=g<=50
1<=n,m<=10^5
0<=a[i],b[j]<=10^6
1<=x<=10^9
*Subtasks
1<=n,m,<=100 for 50% of the maximum score.
*Output Format
For each of the g games, print an integer on a new line denoting the maximum possible score Nick can achieve without being disqualified.
*Sample Input 0
1
5 4 10
4 2 4 6 1
2 1 8 5
*Sample Output 0
4
*Explanation 0
The two stacks initially look like this:
4
2   2
4   1
6   8
1   5
A   B
The image below depicts the integers Nick should choose to remove from the stacks. We print 4 as our answer,
because that is the maximum number of integers that can be removed from the two stacks without the sum exceeding x=10.
move 1 - 4
            2 - move 2
move 4 - 2
            1 - move 3
         4
         6  8
         1  5
         A  B
(There can be multiple ways to remove the integers from the stack, the image shows just one of them.)
 */

package Stacks.Medium;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class GameOfTwoStacks {

    /*
     * Complete the twoStacks function below.
     */
    static int twoStacks(int x, int[] a, int[] b) {
        int sum = 0;
        int a1 = 0;
        int b1 = 0;
        int moves = Integer.MIN_VALUE;
        Stack<Integer> stackA = new Stack<>();
        for(; a1 < a.length; a1++){
            if(a[a1] + sum <= x){
                stackA.push(a[a1]);
                sum += a[a1];
            }
            else{
                break;
            }
        }
        while(b1 < b.length && sum + b[b1] <= x){
            sum += b[b1++];
        }
        moves = Math.max(moves,stackA.size() + b1);
        while(stackA.size() > 0 && b1 < b.length){
            sum -= stackA.pop();
            while(b1 < b.length && sum + b[b1] <= x){
                sum += b[b1++];
            }
            moves = Math.max(moves,stackA.size() + b1);
        }
        return moves;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int g = Integer.parseInt(scanner.nextLine().trim());

        for (int gItr = 0; gItr < g; gItr++) {
            String[] nmx = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmx[0].trim());

            int m = Integer.parseInt(nmx[1].trim());

            int x = Integer.parseInt(nmx[2].trim());

            int[] a = new int[n];

            String[] aItems = scanner.nextLine().split(" ");

            for (int aItr = 0; aItr < n; aItr++) {
                int aItem = Integer.parseInt(aItems[aItr].trim());
                a[aItr] = aItem;
            }

            int[] b = new int[m];

            String[] bItems = scanner.nextLine().split(" ");

            for (int bItr = 0; bItr < m; bItr++) {
                int bItem = Integer.parseInt(bItems[bItr].trim());
                b[bItr] = bItem;
            }

            int result = twoStacks(x, a, b);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}

