/*
Your local library needs your help!
Given the expected and actual return dates for a library book, create a program that calculates the fine (if any).
The fee structure is as follows:
1. If the book is returned on or before the expected return date, no fine will be charged (i.e.: fine=0).
2. If the book is returned after the expected return day but still within the
same calendar month and year as the expected return date, fine=15 Hackos * (the number of days late).
3. If the book is returned after the expected return month but still within the
same calendar year as the expected return date, the fine=500 Hackos * (the number of months late).
4. If the book is returned after the calendar year in which it was expected, there is a fixed fine of 10000 Hackos.
Charges are based only on the least precise measure of lateness.
For example, whether a book is due January 1, 2017 or December 31, 2017, if it is returned January 1, 2018,
that is a year late and the fine would be 10,000 Hackos.
*Function Description
Complete the libraryFine function in the editor below. It must return an integer representing the fine due.
*libraryFine has the following parameter(s):
d1, m1, y1: returned date day, month and year
d2, m2, y2: due date day, month and year
*Input Format
The first line contains 3 space-separated integers, d1,m1,y1, denoting the
respective day, month, and year on which the book was returned.
The second line contains 3 space-separated integers, d2,m2,y2, denoting the
respective day, month, and year on which the book was due to be returned.
*Constraints
1<=d1,d2<=31
1<=m1,m2<=12
1<=y1,y2<=3000
It is guaranteed that the dates will be valid Gregorian calendar dates.
*Output Format
Print a single integer denoting the library fine for the book received as input.
*Sample Input
9 6 2015
6 6 2015
*Sample Output
45
*Explanation
Given the following dates:
Returned: d1=9,m1=6,y1=2015
Due: d2=6,m2=6,y2=2015
Because y2==y1, we know it is less than a year late.
Because m2==m1, we know it's less than a month late.
Because d2<d1, we know that it was returned late (but still within the same month and year).
Per the library's fee structure, we know that our fine will be 15 Hackos * (# of days late).
We then print the result of 15 * (d1-d2)=15*(9-6) as our output.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LibraryFine {

    // Complete the libraryFine function below.
    static int libraryFine(int d1, int m1, int y1, int d2, int m2, int y2) {
        int fine = 0;
        if(y1 == y2){
            if(m1==m2){
                if(d1 > d2){
                    fine = 15 * (d1 - d2);
                }
            }
            else if(m1 > m2){
                fine = 500 * (m1 - m2);
            }
        }
        else if(y1 > y2){
            fine = 10000;
        }
        return fine;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] d1M1Y1 = scanner.nextLine().split(" ");

        int d1 = Integer.parseInt(d1M1Y1[0]);

        int m1 = Integer.parseInt(d1M1Y1[1]);

        int y1 = Integer.parseInt(d1M1Y1[2]);

        String[] d2M2Y2 = scanner.nextLine().split(" ");

        int d2 = Integer.parseInt(d2M2Y2[0]);

        int m2 = Integer.parseInt(d2M2Y2[1]);

        int y2 = Integer.parseInt(d2M2Y2[2]);

        int result = libraryFine(d1, m1, y1, d2, m2, y2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

