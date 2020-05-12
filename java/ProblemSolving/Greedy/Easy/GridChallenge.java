/*
Given a square grid of characters in the range ascii[a-z], rearrange elements of each row alphabetically, ascending.
Determine if the columns are also in ascending alphabetical order, top to bottom. Return YES if they are or NO if they are not.
For example, given:
a b c
a d e
e f g
The rows are already in alphabetical order.
The columns a a e, b d f and c e g are also in alphabetical order, so the answer would be YES.
Only elements within the same row can be rearranged. They cannot be moved to a different row.
*Function Description
Complete the gridChallenge function in the editor below. It should return a string, either YES or NO.
*gridChallenge has the following parameter(s):
grid: an array of strings
*Input Format
The first line contains t, the number of testcases.
Each of the next t sets of lines are described as follows:
- The first line contains n, the number of rows and columns in the grid.
- The next n lines contains a string of length n
*Constraints
1<=t<=100
1<=n<=100
Each string consists of lowercase letters in the range ascii[a-z]
*Output Format
For each test case, on a separate line print YES if it is possible to rearrange the grid
alphabetically ascending in both its rows and columns, or NO otherwise.
*Sample Input
1
5
ebacd
fghij
olmkn
trpqs
xywuv
*Sample Output
YES
*Explanation
The 5x5 grid in the 1 test case can be reordered to
abcde
fghij
klmno
pqrst
uvwxy
This fulfills the condition since the rows 1, 2, ..., 5 and the columns 1, 2, ..., 5 are all lexicographically sorted.
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class GridChallenge {

    // Complete the gridChallenge function below.
    static String gridChallenge(String[] grid) {
        char[][] charGrid = new char[grid.length][];
        for(int i = 0; i < grid.length; i++){
            char[] charLine = grid[i].toCharArray();
            Arrays.parallelSort(charLine);
            charGrid[i] = charLine;
        }
        for(int j = 0; j < charGrid[0].length; j++){
            for(int i = 1; i < charGrid.length; i++){
                if(charGrid[i][j] < charGrid[i-1][j]){
                    return "NO";
                }
            }
        }
        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String[] grid = new String[n];

            for (int i = 0; i < n; i++) {
                String gridItem = scanner.nextLine();
                grid[i] = gridItem;
            }

            String result = gridChallenge(grid);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

