/*
Dan is playing a video game in which his character competes in a hurdle race.
Hurdles are of varying heights, and Dan has a maximum height he can jump.
There is a magic potion he can take that will increase his maximum height by 1 unit for each dose.
How many doses of the potion must he take to be able to jump all of the hurdles.
Given an array of hurdle heights height, and an initial maximum height Dan can jump, k,
determine the minimum number of doses Dan must take to be able to clear all the hurdles in the race.
For example, if height=[1,2,3,3,2] and Dan can jump1 unit high naturally, he must take 3-1 doses of
potion to be able to jump all of the hurdles.
*Function Description
Complete the hurdleRace function in the editor below.
It should return the minimum units of potion Dan needs to drink to jump all of the hurdles.
*hurdleRace has the following parameter(s):
k: an integer denoting the height Dan can jump naturally
height: an array of integers denoting the heights of each hurdle
*Input Format
The first line contains two space-separated integers n and k, the number of hurdles and the maximum height Dan can jump naturally.
The second line contains n space-separated integers height[1] where 0<=i<n.
*Constraints
1<=n,k<=100
1<=height[i]<=100
*Output Format
Print an integer denoting the minimum doses of magic potion Dan must drink to complete the hurdle race.
*Sample Input 0
5 4
1 6 3 5 2
*Sample Output 0
2
*Explanation 0
Dan's character can jump a maximum of k=4 units, but the tallest hurdle has a height of h[1]=6:
To be able to jump all the hurdles, Dan must drink 6-4=2 doses.
*Sample Input 1
5 7
2 5 4 5 2
*Sample Output 1
0
*Explanation 1
Dan's character can jump a maximum of k=7 units, which is enough to cross all the hurdles:
Because he can already jump all the hurdles, Dan needs to drink 0 doses.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class HurdleRace {

    // Complete the hurdleRace function below.
    static int hurdleRace(int k, int[] height) {
        int max = Arrays.stream(height).reduce((x,y) -> Math.max(x,y)).getAsInt();
        return Math.max(max - k, 0);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] height = new int[n];

        String[] heightItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int heightItem = Integer.parseInt(heightItems[i]);
            height[i] = heightItem;
        }

        int result = hurdleRace(k, height);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

