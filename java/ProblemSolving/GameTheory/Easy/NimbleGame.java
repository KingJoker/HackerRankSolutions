/*
Two people are playing Nimble! The rules of the game are:
The game is played on a line of n squares, indexed from 0 to n-1.
Each square i (where 0<=i<n) contains c[i] coins. For example:
 0 1 2 3 4
| |2|3| |6
The players move in alternating turns.
During each move, the current player must remove exactly 1 coin from square i and move it to square j if and only if 0<=j<i.
The game ends when all coins are in square 0 and nobody can make a move.
The first player to have no available move loses the game.
Given the value of n and the number of coins in each square, determine whether the person who
wins the game is the first or second person to move. Assume both players move optimally.
*Input Format
The first line contains an integer, T, denoting the number of test cases.
Each of the 2*T subsequent lines defines a test case. Each test case is described over the following two lines:
1. An integer, n, denoting the number of squares.
2. n space-separated integers, c[0],c[1],...,c[n-1], where each c[i] describes the number of coins at square i.
*Constraints
1<=T<=10^4
1<=n<=100
0<=c[i]<=10^9
*Output Format
For each test case, print the name of the winner on a new line (i.e., either First or Second).
*Sample Input
2
5
0 2 3 0 6
4
0 0 0 0
*Sample Output
First
Second
*Explanation
Explanation for 1st testcase:
The first player will shift one coin from squard[2] to square[0]. Hence, the second player is left with the squares [2,3,3,0,6].
Now whatever be his/her move is, the first player can always nullify
the change by shifting a coin to the same square where he/she shifted it.
Hence the last move is always played by the first player, so he wins.
Exlanation for 2nd testcase:
There are no coins in any of the squares so the first player cannot make any move, hence second player wins.
 */

package GameTheory.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class NimbleGame {

    // Complete the nimbleGame function below.
    static String nimbleGame(int[] s) {
        if(s.length < 2){
            return "Second";
        }
        return IntStream.range(1,s.length).filter(x -> s[x] % 2 == 1).reduce((x,y) -> x^y).orElse(0) == 0 ? "Second": "First";
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

            String result = nimbleGame(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

