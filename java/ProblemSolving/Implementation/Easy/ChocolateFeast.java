/*
Little Bobby loves chocolate. He frequently goes to his favorite 5 & 10 store, Penny Auntie, to buy them.
They are having a promotion at Penny Auntie. If Bobby saves enough wrappers, he can turn them in for a free chocolate.
For example, Bobby has n=15 to spend on bars of chocolate that cost c=3 each.
He can turn in m=2 wrappers to receive another bar. Initially, he buys 5 bars and has 5 wrappers after eating them.
He turns in 4 of them, leaving him with 1, for 2 more bars.
After eating those two, he has 3 wrappers, turns in 2 leaving him with 1 wrapper and his new bar.
Once he eats that one, he has 2 wrappers and turns them in for another bar.
After eating that one, he only has 1 wrapper, and his feast ends.
Overall, he has eaten 5+2+1+1=9 bars.
*Function Description
Complete the chocolateFeast function in the editor below.
It must return the number of chocolates Bobby can eat after taking full advantage of the promotion.
*chocolateFeast has the following parameter(s):
n: an integer representing Bobby's initial amount of money
c: an integer representing the cost of a chocolate bar
m: an integer representing the number of wrappers he can turn in for a free bar
Note: Little Bobby will always turn in his wrappers if he has enough to get a free chocolate.
*Input Format
The first line contains an integer, t, denoting the number of test cases to analyze.
Each of the next t lines contains three space-separated integers: n, c, and m.
They represent money to spend, cost of a chocolate, and the number of wrappers he can turn in for a free chocolate.
*Constraints
1<=t<=1000
2<=n<=10^5
1<=c<=n
2<=m<=n
*Output Format
For each trip to Penny Auntie, print the total number of chocolates Bobby eats on a new line.
*Sample Input
3
10 2 5
12 4 4
6 2 2
*Sample Output
6
3
5
*Explanation
Bobby makes the following 3 trips to the store:
1. He spends his 10 dollars on 5 chocolates at 2 dollars apiece.
He then eats them and exchanges all 5 wrappers to get 1 more. He eats 6 chocolates.
2. He spends his 12 dollars on 3 chocolates at 4 dollars apiece.
He has 3 wrappers, but needs 4 to trade for his next chocolate. He eats 3 chocolates.
3. He spends 6 dollars on 3 chocolates at 2 dollars apiece.
He then exchanges 2 of the 3 wrappers for 1 additional piece.
Next, he uses his third leftover chocolate wrapper from his initial purchase with the
wrapper from his trade-in to do a second trade-in for  more piece.
At this point he has 1 wrapper left, which is not enough to perform another trade-in. He eats 5 chocolates.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ChocolateFeast {

    // Complete the chocolateFeast function below.
    static int chocolateFeast(int n, int c, int m) {
        int currentChocolates = n / c;
        int totalChocolates = currentChocolates;
        while(currentChocolates - m >= 0){
            int nextChocolates = currentChocolates / m;
            currentChocolates -= nextChocolates * m;
            currentChocolates += nextChocolates;
            totalChocolates += nextChocolates;
        }
        return totalChocolates;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] ncm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(ncm[0]);

            int c = Integer.parseInt(ncm[1]);

            int m = Integer.parseInt(ncm[2]);

            int result = chocolateFeast(n, c, m);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

