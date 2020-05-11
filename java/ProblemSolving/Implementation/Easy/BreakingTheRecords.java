/*
Maria plays college basketball and wants to go pro.
Each season she maintains a record of her play.
She tabulates the number of times she breaks her season record for most points and least points in a game.
Points scored in the first game establish her record for the season, and she begins counting from there.
For example, assume her scores for the season are represented in the array scores=[12,24,10,24].
Scores are in the same order as the games played. She would tabulate her results as follows:
                                 Count
Game  Score  Minimum  Maximum   Min Max
 0      12     12       12       0   0
 1      24     12       24       0   1
 2      10     10       24       1   1
 3      24     10       24       1   1
Given Maria's scores for a season, find and print the number of times she breaks her records
for most and least points scored during the season.
*Function Description
Complete the breakingRecords function in the editor below.
It must return an integer array containing the numbers of times she broke her records.
Index 0 is for breaking most points records, and index 1 is for breaking least points records.
*breakingRecords has the following parameter(s):
scores: an array of integers
*Input Format
The first line contains an integer n, the number of games.
The second line contains n space-separated integers describing the respective values of score[0],score[1],...,score[n-1].
*Constraints
1<=n<=1000
0<=scores[i]<=10^8
*Output Format
Print two space-seperated integers describing the respective numbers of times her best (highest) score increased
and her worst (lowest) score decreased.
*Sample Input 0
9
10 5 20 20 4 5 2 25 1
*Sample Output 0
2 4
*Explanation 0
The diagram below depicts the number of times Maria broke her best and worst records throughout the season:
                                 Count
Game  Score  Minimum  Maximum   Min Max
 0      10     10       10       0   0
 1      5      5        10       1   0
 2      20     5        20       0   1
 3      20     5        20       0   0
 4      4      4        20       1   0
 5      5      4        20       0   0
 6      2      2        20       1   0
 7      25     2        25       0   1
 8      1      1        25       1   0
She broke her best record twice (after games 2 and 7) and her worst record four times (after games 1, 4, 6, and 8),
so we print 2 4 as our answer.
Note that she did not break her record for best score during game 3,
as her score during that game was not strictly greater than her best record at the time.
*Sample Input 1
10
3 4 21 36 10 28 35 5 24 42
*Sample Output 1
4 0
*Explanation 1
The diagram below depicts the number of times Maria broke her best and worst records throughout the season:
Game  Score  Minimum  Maximum   Min Max
 0      3      3        3        0   0
 1      4      3        4        0   1
 2      21     3        21       0   1
 3      36     3        36       0   1
 4      10     3        36       0   0
 5      28     3        36       0   0
 6      35     3        36       0   0
 7      5      3        36       0   0
 8      24     3        36       0   0
 9      42     3        42       0   1
She broke her best record four times (after games 1, 2, 3, and 9) and her worst record zero times
(no score during the season was lower than the one she earned during her first game), so we print 4 0 as our answer.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BreakingTheRecords {

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        int max = scores[0];
        int min = scores[0];
        int maxCount = 0;
        int minCount = 0;
        for(int score : scores){
            if(score > max){
                max = score;
                maxCount++;
            }
            else if(score < min){
                min = score;
                minCount++;
            }
        }
        return new int[]{maxCount,minCount};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[n];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int[] result = breakingRecords(scores);

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

