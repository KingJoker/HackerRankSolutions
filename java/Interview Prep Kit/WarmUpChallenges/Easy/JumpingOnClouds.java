/*
Emma is playing a new mobile game that starts with consecutively numbered clouds.
Some of the clouds are thunderheads and others are cumulus.
She can jump on any cumulus cloud having a number that is equal to the number of the current cloud plus 1 or 2.
She must avoid the thunderheads. Determine the minimum number of jumps it will take Emma to jump from her starting postion to the last cloud.
It is always possible to win the game.
For each game, Emma will get an array of clouds numbered 0 if they are safe or 1 if they must be avoided.
For example, c=[0,1,0,0,0,1,0] indexed from 0...6.
The number on each cloud is its index in the list so she must avoid the clouds at indexes 1 and 5.
She could follow the following two paths: 0->2->4->6 or 0->2->3->4->6.
The first path takes 3 jumps while the second takes 4.
*Function Description
Complete the jumpingOnClouds function in the editor below.
It should return the minimum number of jumps required, as an integer.
*jumpingOnClouds has the following parameter(s):
c: an array of binary integers
*Input Format
The first line contains an integer n, the total number of clouds.
The second line contains n space-separated binary integers describing clouds c[i] where 0<=i<n.
*Constraints
2<=n<=100
c[i]∈{0,1}
c[0]=c[n-1]=0
*Output Format
Print the minimum number of jumps needed to win the game.
*Sample Input 0
7
0 0 1 0 0 1 0
*Sample Output 0
4
*Explanation 0:
Emma must avoid c[2] and c[5]. She can win the game with a minimum of 4 jumps:
*Sample Input 1
6
0 0 0 0 1 0
*Sample Output 1
3
*Explanation 1:
The only thundercloud to avoid is c[4]. Emma can win the game in 3 jumps:
*/
package WarmUpChallenges.Easy;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JumpingOnClouds {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c) {
        return turn(0,c);
    }

    static int turn(int index, int[] c){
        if(index == c.length - 1){
            return 0;
        }
        if(index >= c.length || c[index] == 1){
            return Integer.MAX_VALUE;
        }
        int minFutureTurns = Math.min(turn(index + 1, c), turn(index + 2, c));
        return 1 + minFutureTurns;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
