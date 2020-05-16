/*
Brie’s Drawing teacher asks her class to open their books to a page number.
Brie can either start turning pages from the front of the book or from the back of the book.
She always turns pages one at a time. When she opens the book, page 1 is always on the right side:
| |1|
When she flips page 1, she sees pages 2 and 3.
Each page except the last page will always be printed on both sides.
The last page may only be printed on the front, given the length of the book.
If the book is n pages long, and she wants to turn to page p, what is the minimum number of pages she will turn?
She can start at the beginning or the end of the book.
Given n and p, find and print the minimum number of pages Brie must turn in order to arrive at page p.
*Function Description
Complete the pageCount function in the editor below. It should return the minimum number of pages Brie must turn.
*pageCount has the following parameter(s):
n: the number of pages in the book
p: the page number to turn to
*Input Format
The first line contains an integer n, the number of pages in the book.
The second line contains an integer, p, the page that Brie's teacher wants her to turn to.
*Constraints
1<=n<=10^5
1<=p<=n
*Output Format
Print an integer denoting the minimum number of pages Brie must turn to get to page p.
*Sample Input 0
6
2
*Sample Output 0
1
*Explanation 0
If Brie starts turning from page 1, she only needs to turn 1 page:
| |1| -> |2|3|
If Brie starts turning from page 6, she needs to turn 2 pages:
|6| | -> |4|5| -> |2|3|
Because we want to print the minumum number of page turns, we print 1 as our answer.
*Sample Input 1
5
4
*Sample Output 1
0
*Explanation 1
If Brie starts turning from page 1, she needs to turn 2 pages:
| |1| -> |2|3| -> |4|5|
If Brie starts turning from page 5, she doesn't need to turn any pages:
|4|5|
Because we want to print the minimum number of page turns, we print 0 as our answer.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DrawingBook {

    /*
     * Complete the pageCount function below.
     */
    static int pageCount(int n, int p) {
        int page = p/2;
        int end = n/2;
        return Math.min(page,end-page);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])*");

        int result = pageCount(n, p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

