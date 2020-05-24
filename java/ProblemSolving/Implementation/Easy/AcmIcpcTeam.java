/*
There are a number of people who will be attending ACM-ICPC World Finals.
Each of them may be well versed in a number of topics.
Given a list of topics known by each attendee, you must determine the maximum number of topics a 2-person team can know.
Also find out how many ways a team can be formed to know that many topics.
Lists will be in the form of bit strings, where each string represents an attendee and each position in that
string represents a field of knowledge, 1 if its a known field or 0 if not.
For example, given three attendees' data as follows:
10101
11110
00010
These are all possible teams that can be formed:
Members Subjects
(1,2)   [1,2,3,4,5]
(1,3)   [1,3,4,5]
(2,3)   [1,2,3,4]
In this case, the first team will know all 5 subjects.
They are the only team that can be created knowing that many subjects.
*Function Description
Complete the acmTeam function in the editor below.
It should return an integer array with two elements: the maximum number of topics any team can know and
the number of teams that can be formed that know that maximum number of topics.
*acmTeam has the following parameter(s):
topic: a string of binary digits
*Input Format
The first line contains two space-separated integers n and m, where n represents
the number of attendees and m represents the number of topics.
Each of the next n lines contains a binary string of length m.
If the ith line's jth character is 1, then the ith person knows the jth topic.
*Constraints
2<=n<=500
1<=m<=500
*Output Format
On the first line, print the maximum number of topics a 2-person team can know.
On the second line, print the number of ways to form a 2-person team that knows the maximum number of topics.
*Sample Input
4 5
10101
11100
11010
00101
*Sample Output
5
2
*Explanation
Calculating topics known for all permutations of 2 attendees we get:
(1,2)->4
(1,3)->5
(1,4)->3
(2,3)->4
(2,4)->4
(3,4)->5
The 2 teams (1, 3) and (3, 4) know all 5 topics which is maximal.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class AcmIcpcTeam {

    // Complete the acmTeam function below.
    static int[] acmTeam(String[] topic) {
        ArrayList<BigInteger> topics = Arrays.stream(topic).map(x -> new BigInteger(x,2)).collect(Collectors.toCollection(ArrayList::new));
        int max = 0;
        int count = 0;
        for(int i = 0; i < topics.size(); i++){
            for(int j = i + 1; j < topics.size(); j++){
                int topicCount = topics.get(i).or(topics.get(j)).bitCount();
                if(max == topicCount){
                    count++;
                }
                else if(topicCount > max){
                    count = 1;
                    max = topicCount;
                }
            }
        }
        return new int[]{max,count};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] topic = new String[n];

        for (int i = 0; i < n; i++) {
            String topicItem = scanner.nextLine();
            topic[i] = topicItem;
        }

        int[] result = acmTeam(topic);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

