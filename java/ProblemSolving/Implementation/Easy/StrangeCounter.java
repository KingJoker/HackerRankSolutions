/*
Bob has a strange counter. At the first second, it displays the number 3.
Each second, the number displayed by the counter decrements by 1 until it reaches 1.
The counter counts down in cycles. In next second, the timer resets to
2*the initial number for the prior cycle and continues counting down.
The diagram below shows the counter values for each time t in the first three cycles:
Time Value      Time Value      Time Value
1    3          4    6          10   12
2    2          5    5          11   11
3    1          6    4          12   10
                7    3          13   9
                8    2          14   8
                9    1          15   7
                                ...  ...
                                21   1
Find and print the value displayed by the counter at time t.
*Function Description
Complete the strangeCounter function in the editor below. It should return the integer value displayed by the counter at time t.
*strangeCounter has the following parameter(s):
t: an integer
*Input Format
A single integer denoting the value of t.
*Constraints
1<=t<=10^12
Subtask
1<=t<=10^5 for 60% of the maximum score.
*Output Format
Print the value displayed by the strange counter at the given time t.
*Sample Input
4
*Sample Output
6
*Explanation
Time t=4 marks the beginning of the second cycle.
It is double the number displayed at the beginning of the first cycle:2*3=6.
This is also shown in the diagram in the Problem Statement above.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class StrangeCounter {

    // Complete the strangeCounter function below.
    static long strangeCounter(long t) {
        long columnStart = 1;
        long valueStart = 3;
        while(columnStart + valueStart <= t){
            columnStart += valueStart;
            valueStart *= 2;
        }
        long target = valueStart - (t - columnStart);
        return target;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        long t = scanner.nextLong();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        long result = strangeCounter(t);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

