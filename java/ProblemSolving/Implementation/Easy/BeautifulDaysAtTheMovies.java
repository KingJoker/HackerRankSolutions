/*
Lily likes to play games with integers.
She has created a new game where she determines the difference between a number and its reverse.
For instance, given the number 12, its reverse is 21. Their difference is 9.
The number 120 reversed is 21, and their difference is 99.
She decides to apply her game to decision making.
She will look at a numbered range of days and will only go to a movie on a beautiful day.
Given a range of numbered days, [i...j] and a number k, determine the number of days in the range that are beautiful.
Beautiful numbers are defined as numbers where |i-reverse(i)| is evenly divisible by k.
If a day's value is a beautiful number, it is a beautiful day. Print the number of beautiful days in the range.
*Function Description
Complete the beautifulDays function in the editor below. It must return the number of beautiful days in the range.
*beautifulDays has the following parameter(s):
i: the starting day number
j: the ending day number
k: the divisor
*Input Format
A single line of three space-separated integers describing the respective values of i, j, and k.
*Constraints
1<=i<=j<=2*10^6
1<=k<=2*10^9
*Output Format
Print the number of beautiful days in the inclusive range between i and j.
*Sample Input
20 23 6
*Sample Output
2
*Explanation
Lily may go to the movies on days 20, 21, 22, and 23. We perform the following calculations to determine which days are beautiful:
Day 20 is beautiful because the following evaluates to a whole number: (|20-02|)/6=18/6=3
Day 21 is not beautiful because the following doesn't evaluate to a whole number: (|21-12|)/6=9/6=1.5
Day 22 is beautiful because the following evaluates to a whole number: (|22-22|)/6=0/6=0
Day 23 is not beautiful because the following doesn't evaluate to a whole number: (|22-32|)/6=9/6=1.5
Only two days, 20 and 22, in this interval are beautiful. Thus, we print 2 as our answer.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BeautifulDaysAtTheMovies {

    // Complete the beautifulDays function below.
    static int beautifulDays(int i, int j, int k) {
        int count = 0;
        for(int num = i; num <=j; num++){
            int reverse = Integer.parseInt(new StringBuffer(Integer.toString(num)).reverse().toString());
            int diff = Math.abs(num - reverse);
            if(diff % k == 0){
                count++;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] ijk = scanner.nextLine().split(" ");

        int i = Integer.parseInt(ijk[0]);

        int j = Integer.parseInt(ijk[1]);

        int k = Integer.parseInt(ijk[2]);

        int result = beautifulDays(i, j, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

