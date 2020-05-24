/*
HackerLand Enterprise is adopting a new viral advertising strategy.
When they launch a new product, they advertise it to exactly 5 people on social media.
On the first day, half of those 5 people (i.e., floor(5/2)=2) like the advertisement and each shares it with 3 of their friends.
At the beginning of the second day, floor(5/2)*3=2*3=6 people receive the advertisement.
Each day, floor(recipients/2) of the recipients like the advertisement and will share it with 3 friends on the following day.
Assuming nobody receives the advertisement twice, determine how many people have liked the ad by the end of a given day,
beginning with launch day as day 1.
For example, assume you want to know how many have liked the ad by the end of the 5th day.
Day Shared Liked Cumulative
1      5     2       2
2      6     3       5
3      9     4       9
4     12     6      15
5     18     9      24
The cumulative number of likes is 24.
*Function Description
Complete the viralAdvertising function in the editor below.
It should return the cumulative number of people who have liked the ad at a given time.
*viralAdvertising has the following parameter(s):
n: the integer number of days
*Input Format
A single integer, n, denoting a number of days.
*Constraints
1<=n<=50
*Output Format
Print the number of people who liked the advertisement during the first n days.
*Sample Input
3
*Sample Output
9
*Explanation
This example is depicted in the following diagram:
                       1*                      2*          3 4 5
               /       |        \            / | \
        6*             7*           8*      9  10 11
     /  |  \        /  |  \      /  |  \
    12* 13* 14*   15* 16   17   18  19  20

2 people liked the advertisement on the first day, 3 people liked the advertisement on the second day
and 4 people liked the advertisement on the third day, so the answer is 2+3+4=9.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ViralAdvertising {

    // Complete the viralAdvertising function below.
    static int viralAdvertising(int n) {
        int previousLikes = 2;
        int likes = 2;
        for(int i = 1; i < n; i++){
            int newLikes = (previousLikes * 3)/2;
            likes += newLikes;
            previousLikes = newLikes;
        }
        return likes;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = viralAdvertising(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

