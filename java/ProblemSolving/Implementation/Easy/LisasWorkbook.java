/*
Lisa just got a new math workbook. A workbook contains exercise problems, grouped into chapters.
Lisa believes a problem to be special if its index (within a chapter) is the same as the page number where it's located.
The format of Lisa's book is as follows:
There are n chapters in Lisa's workbook, numbered from 1 to n.
The ith chapter has arr[i] problems, numbered from 1 to arr[i].
Each page can hold up to k problems. Only a chapter's last page of exercises may contain fewer than k problems.
Each new chapter starts on a new page, so a page will never contain problems from more than one chapter.
The page number indexing starts at 1.
Given the details for Lisa's workbook, can you count its number of special problems?
For example, Lisa's workbook contains arr[1]=4 problems for chapter 1, and arr[2] problems for chapter 2.
Each page can hold k=3 problems. The first page will hold 3 problems for chapter 1.
Problem 1 is on page 1, so it is special. Page 2 contains only Chapter 1, Problem 4, so no special problem is on page 2.
Chapter 2 problems start on page 3 and there are 2 problems.
Since there is no problem 3 on page 3, there is no special problem on that page either.
There is 1 special problem in her workbook.
Note: See the diagram in the Explanation section for more details.
*Function Description
Complete the workbook function in the editor below.
It should return an integer that represents the number of special problems in the workbook.
*workbook has the following parameter(s):
n: an integer that denotes the number of chapters
k: an integer that denotes the maximum number of problems per page
arr: an array of integers that denote the number of problems in each chapter
*Input Format
The first line contains two integers n and k, the number of chapters and the maximum number of problems per page.
The second line contains n space-separated integers arr[i] where arr[i] denotes the number of problems in the ith chapter.
*Constraints
1<=n,k,arr[i]<=100
*Output Format
Print the number of special problems in Lisa's workbook.
*Sample Input
5 3
4 2 6 1 10
*Sample Output
4
*Explanation
The diagram below depicts Lisa's workbook with n=5 chapters and a maximum of k=3 problems per page.
Special problems are outlined in red, and page numbers are in yellow squares.
    Chapter 1    Chapter 1    Chapter 2     Chapter 3   Chapter 3
    page 1       page 2       page 3        page 4      page 5
    1*,2,3       4            1,2           1,2,3       4,5*,6

    Chapter 4    Chapter 5    Chapter 5     Chapter 5   Chapter 5
    page 6       page 7       page 8        page 9      page 10
    1            1,2,3        4,5,6         7,8,9*      10*
There are 4 special problems and thus we print the number 4 on a new line.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class LisasWorkbook {

    // Complete the workbook function below.
    static int workbook(int n, int k, int[] arr) {
        int page = 1;
        int problems = 0;
        int chapter = 0;
        int special = 0;
        while(chapter < n){
            int chapterProblems = arr[chapter];
            int remainingProblems = chapterProblems - problems;
            int pageProblems = remainingProblems - k > 0 ? k : remainingProblems;
            if(page >= problems + 1 && page <= problems + pageProblems){
                special++;
            }
            if(remainingProblems == pageProblems){
                chapter++;
                problems=0;
            }
            else {
                problems += pageProblems;
            }
            page++;
        }
        return special;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = workbook(n, k, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

