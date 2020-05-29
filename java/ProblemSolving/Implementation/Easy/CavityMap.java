/*
You are given a square map as a matrix of integer strings. Each cell of the map has a value denoting its depth.
We will call a cell of the map a cavity if and only if this cell is not on the border of the map and each
cell adjacent to it has strictly smaller depth. Two cells are adjacent if they have a common side, or edge.
Find all the cavities on the map and replace their depths with the uppercase character X.
For example, given a matrix:
989
191
111
You should return:
989
1X1
111
The center cell was deeper than those on its edges: [8,1,1,1].
The deep cells in the top two corners don't share an edge with the center cell.
*Function Description
Complete the cavityMap function in the editor below.
It should return an array of strings, each representing a line of the completed map.
*cavityMap has the following parameter(s):
grid: an array of strings, each representing a row of the grid
*Input Format
The first line contains an integer n, the number of rows and columns in the map.
Each of the following n lines (rows) contains n positive digits without spaces (columns) representing depth at .
*Constraints
1<=n<=100
*Output Format
Output n lines, denoting the resulting map. Each cavity should be replaced with the character X.
*Sample Input
4
1112
1912
1892
1234
*Sample Output
1112
1X12
18X2
1234
*Explanation
The two cells with the depth of 9 are not on the border and are surrounded on all sides by shallower cells.
Their values have been replaced by X.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CavityMap {

    // Complete the cavityMap function below.
    static String[] cavityMap(String[] grid) {
        char[][] charGrid = new char[grid.length][grid[0].length()];
        for(int i = 0; i < grid.length; i++){
            charGrid[i] = grid[i].toCharArray();
        }
        for(int i = 0; i < charGrid.length; i++){
            for(int j = 0; j < charGrid[i].length; j++){
                if(isCavity(charGrid,i,j)){
                    charGrid[i][j] = 'X';
                }
            }
        }
        for(int i = 0; i < grid.length; i++){
            grid[i] = new String(charGrid[i]);
        }
        return grid;
    }

    static boolean isCavity(char[][] grid, int i, int j){
        if(i == 0 || i == grid.length-1 || j == 0 || j == grid[i].length - 1){
            return false;
        }
        char target = grid[i][j];
        return (target > grid[i-1][j] && target > grid[i][j-1] && target > grid[i+1][j] && target > grid[i][j+1]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        String[] result = cavityMap(grid);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(result[i]);

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

