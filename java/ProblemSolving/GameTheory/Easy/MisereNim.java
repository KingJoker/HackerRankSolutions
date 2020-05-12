/*
Two people are playing game of Misère Nim. The basic rules for this game are as follows:
The game starts with n piles of stones indexed from 0 to n-1. Each pile i (where 0<=i<n) has s[i] stones.
The players move in alternating turns. During each move, the current player must remove one or more stones from a single pile.
The player who removes the last stone loses the game.
Given the value of n and the number of stones in each pile, determine whether the person who
wins the game is the first or second person to move.
If the first player to move wins, print First on a new line; otherwise, print Second. Assume both players move optimally.
*Input Format
The first line contains an integer, T, denoting the number of test cases.
Each of the 2T subsequent lines defines a test case. Each test case is described over the following two lines:
An integer, n, denoting the number of piles.
n space-separated integers, s[0],s[1],...,s[n-1], where each s[i] describes the number of stones at pile i.
*Constraints
1<=T<=100
1<=n<=100
1<=s[i]<=10^9
*Output Format
For each test case, print the name of the winner on a new line (i.e., either First or Second).
*Sample Input
2
2
1 1
3
2 1 3
*Sample Output
First
Second
*Explanation
In the first testcase, the first player removes 1 stone from the first pile and then the
second player has no moves other than removing the only stone in the second pile. So first wins.
In the second testcase, in every possible move of first player we see that the last stone is picked by him, so second player wins.
 */

package GameTheory.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MisereNim {

    // Complete the misereNim function below.
    static String misereNim(int[] s) {
        int xor = Arrays.stream(s).reduce((x,y) -> x^y).getAsInt();
        boolean allOnes = Arrays.stream(s).reduce(Integer::sum).getAsInt() == s.length;
        boolean oneWins = xor != 0;
        if(allOnes){
            if(!oneWins && s.length % 2 == 0){
                oneWins = true;
            }
            else if(oneWins && s.length % 2 != 0){
                oneWins = false;
            }
        }
        return oneWins?"First":"Second";
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

            int[] s = new int[n];

            String[] sItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int sItem = Integer.parseInt(sItems[i]);
                s[i] = sItem;
            }

            String result = misereNim(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

