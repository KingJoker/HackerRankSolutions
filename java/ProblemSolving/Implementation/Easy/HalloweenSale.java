/*
You wish to buy video games from the famous online video game store Mist.
Usually, all games are sold at the same price, p dollars.
However, they are planning to have the seasonal Halloween Sale next month in which you can buy games at a cheaper price.
Specifically, the first game you buy during the sale will be sold at p dollars,
but every subsequent game you buy will be sold at exactly d dollars less than the cost of the previous one you bought.
This will continue until the cost becomes less than or equal to m dollars, after which every game you buy will cost m dollars each.
For example, if p=20, d=3 and m=6, then the following are the costs of the first 11 games you buy, in order:
20,17,14,11,8,6,6,6,6,6,6
You have s dollars in your Mist wallet. How many games can you buy during the Halloween Sale?
*Input Format
The first and only line of input contains four space-separated integers p, d, m and s.
*Constraints
1<=m<=p<=100
1<=d<=100
1<=s<=10^4
*Output Format
Print a single line containing a single integer denoting the maximum number of games you can buy.
*Sample Input 0
20 3 6 80
*Sample Output 0
6
*Explanation 0
We have p=20, d=3 and m=6, the same as in the problem statement. We also have s=80 dollars.
We can buy 6 games since they cost 20+17+14+11+8+6=76 dollars. However, we cannot buy a 7th game.
Thus, the answer is 6.
*Sample Input 1
20 3 6 85
*Sample Output 1
7
*Explanation 1
This is the same as the previous case, except this time we have s=85 dollars.
This time, we can buy 7 games since they cost 20+17+14+11+8+6+6=82 dollars.
However, we cannot buy an 8th game. Thus, the answer is 7.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HalloweenSale {

    // Complete the howManyGames function below.
    static int howManyGames(int p, int d, int m, int s) {
        int games = 1;
        int currentSpend = p;
        int lastGameCost = p;
        while(currentSpend <= s){
            int nextGameCost = lastGameCost - d;
            if(nextGameCost < m){
                nextGameCost = m;
            }
            currentSpend += nextGameCost;
            lastGameCost = nextGameCost;
            games++;
        }
        return --games;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] pdms = scanner.nextLine().split(" ");

        int p = Integer.parseInt(pdms[0]);

        int d = Integer.parseInt(pdms[1]);

        int m = Integer.parseInt(pdms[2]);

        int s = Integer.parseInt(pdms[3]);

        int answer = howManyGames(p, d, m, s);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

