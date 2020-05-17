/*
Poker Nim is another 2-player game that's a simple variation on a Nim game. The rules of the games are as follows:
The game starts with n piles of chips indexed from 0 to n-1. Each pile i (where 0<=i<n) has c[i] chips.
The players move in alternating turns. During each move, the current player must perform either of the following actions:
Remove one or more chips from a single pile.
Add one or more chips to a single pile.
At least 1 chip must be added or removed during each turn.
To ensure that the game ends in finite time, a player cannot add chips to any pile i more than k times.
The player who removes the last chip wins the game.
Given the values of n, k, and the numbers of chips in each of the n piles,
determine whether the person who wins the game is the first or second person to move. Assume both players move optimally.
*Input Format
The first line contains an integer, T, denoting the number of test cases.
Each of the 2*T subsequent lines defines a test case. Each test case is described over the following two lines:
1. Two space-separated integers, n (the number of piles) and
k (the maximum number of times an individual player can add chips to some pile i), respectively.
2. n space-separated integers, c[0],c[1],...,c[n-1], where each c[i] describes the number of chips at pile i.
*Constraints
1<=T<=100
1<=n,k<=100
1<=c[i]<=10^9
*Output Format
For each test case, print the name of the winner on a new line (i.e., either First or Second).
*Sample Input
2
2 5
1 2
3 5
2 1 3
*Sample Output
First
Second
 */

package GameTheory.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PokerNim {

    // Complete the pokerNim function below.
    static String pokerNim(int k, int[] c) {
        return Arrays.stream(c).reduce((x,y) -> x^y).getAsInt() == 0 ? "Second": "First";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            String result = pokerNim(k, c);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

