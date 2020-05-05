/*
Lena is preparing for an important coding competition that is preceded by a number of sequential preliminary contests.
Initially, her luck balance is 0. She believes in "saving luck", and wants to check her theory. Each contest is described by two integers, L[i] and T[i]:
L[i] is the amount of luck associated with a contest. If Lena wins the contest, her luck balance will decrease by L[i]; if she loses it, her luck balance will increase by L[i].
T[i] denotes the contest's importance rating. It's equal to 1 if the contest is important, and it's equal to 0 if it's unimportant.
If Lena loses no more than k important contests, what is the maximum amount of luck she can have after competing in all the preliminary contests? This value may be negative.
For example, k=2 and:
Contest		L[i]	T[i]
1		    5	    1
2		    1	    1
3		    4	    0
If Lena loses all of the contests, her will be 5+1+4. Since she is allowed to lose 2 important contests, and there are only 2 important contests.
he can lose all three contests to maximize her luck at 10. If k=1, she has to win at least 1 of the 2 important contests.
She would choose to win the lowest value important contest worth 1. Her final luck will be 5+4-1=8.
*Function Description
Complete the luckBalance function in the editor below. It should return an integer that represents the maximum luck balance achievable.
*luckBalance has the following parameter(s):
k: the number of important contests Lena can lose
contests: a 2D array of integers where each contests[i] contains two integers that represent the luck balance and importance of the  contest.
*Input Format
The first line contains two space-separated integers n and k, the number of preliminary contests and the maximum number of important contests Lena can lose.
Each of the next n lines contains two space-separated integers, L[i] and T[i], the contest's luck balance and its importance rating.
*Constraints
1<=n<=100
0<=k<=N
1<=L[i]<=10^4
T[i]∈{0,1}
*Output Format
Print a single integer denoting the maximum amount of luck Lena can have after all the contests.
*Sample Input
6 3
5 1
2 1
1 1
8 1
10 0
5 0
*Sample Output
29
*Explanation
There are n=6 contests. Of these contests, 4 are important and she cannot lose more than k=3 of them.
Lena maximizes her luck if she wins the 3rd important contest (where L[i]==1) and loses all of the other five contests for a total luck balance of 5+2+8+10+5-1=29.
 */

package GreedyAlgorithms.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class LuckBalance {

    // Complete the luckBalance function below.
    static int luckBalance(int k, int[][] contests) {
        ArrayList<Integer> importantContests = new ArrayList<>();
        ArrayList<Integer> unimportantContests = new ArrayList<>();
        for(int[] contest : contests) {
            if (contest[1] == 1)
                importantContests.add(contest[0]);
            else
                unimportantContests.add(contest[0]);
        }
        importantContests.sort(Integer::compareTo);
        int positiveLuck = 0;
        int negativeLuck = 0;
        for(int luck : unimportantContests){
            positiveLuck += luck;
        }
        int contestsToLose = k > importantContests.size()? 0 : importantContests.size() - k;
        for (int i = contestsToLose; i < importantContests.size(); i++) {
            positiveLuck += importantContests.get(i);
        }
        for (int i = 0; i < contestsToLose; i++) {
            negativeLuck += importantContests.get(i);
        }
        return positiveLuck - negativeLuck;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] contests = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] contestsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int contestsItem = Integer.parseInt(contestsRowItems[j]);
                contests[i][j] = contestsItem;
            }
        }

        int result = luckBalance(k, contests);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

