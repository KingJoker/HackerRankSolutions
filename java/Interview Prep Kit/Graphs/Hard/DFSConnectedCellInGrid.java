/*
Consider a matrix where each cell contains either a 0 or a 1 and any cell containing a 1 is called a filled cell.
Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally.
In the diagram below, the two colored regions show cells connected to the filled cells. Black on white are not connected.
Cells adjacent to filled cells:
000|0|01
010|0|00
   |  --
000|0 00
If one or more filled cells are also connected, they form a region.
Note that each cell in a region is connected to at least one other cell in the region but is not necessarily
directly connected to all the other cells in the region.
Regions:
|1   1|0 0 0|1
 -|   |-     -
 0|  1 1|0 0 0
   -----|-
 0   0 0|1|0 0
Given an nxm matrix, find and print the number of cells in the largest region in the matrix.
*Function Description
Complete the function maxRegion in the editor below. It must return an integer value, the size of the largest region.
*maxRegion has the following parameter(s):
grid: a two dimensional array of integers
*Input Format
The first line contains an integer, n, the number of rows in the matrix, grid.
The second line contains an integer, m, the number of columns in the matrix.
Each of the following n lines contains a row of m space-separated integers, grid[i][j].
*Constraints
0<n,m<10
grid[i][j]∈{0,1}
*Output Format
Print the number of cells in the largest region in the given matrix.
*Sample Input
4
4
1 1 0 0
0 1 1 0
0 0 1 0
1 0 0 0
*Sample Output
5
*Explanation
The diagram below depicts two regions of the matrix:
|1   1| 0 0
 -|   |--
 0|  1  1|0
   ----| |
 0   0 |1|0
 -      -
 1|  0  0 0
The first region has five cells and the second region has one cell. We choose the larger region.
 */

package Graphs.Hard;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class DFSConnectedCellInGrid {

    private static class Node{
        int i;
        int j;
        int value;
        boolean visited;
        ArrayList<Node> neighbors;
        public Node(int i, int j, int value){
            this.i = i;
            this.j = j;
            this.value = value;
            visited = false;
            neighbors = new ArrayList<>();
        }
    }

    // Complete the maxRegion function below.
    static int maxRegion(int[][] grid) {
        Node[][] nodes = new Node[grid.length][grid[0].length];
        HashSet<Node> ones = new HashSet<Node>();
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                Node current = nodes[i][j];
                if(current == null){
                    current = new Node(i,j,grid[i][j]);
                    nodes[i][j] = current;
                }
                if(grid[i][j] == 1){
                    ones.add(current);
                }
                for(int i1 = i-1; i1 <= i+1; i1++){
                    for(int j1 = j-1; j1<=j+1; j1++){
                        if(i1 >= 0 && j1 >= 0 && i1<grid.length && j1 < grid[i].length &&
                                !(i1 == i && j1 == j)){
                            Node neighbor = nodes[i1][j1];
                            if(neighbor == null){
                                neighbor = new Node(i1,j1,grid[i1][j1]);
                                nodes[i1][j1] = neighbor;
                            }
                            current.neighbors.add(neighbor);
                        }
                    }
                }
            }
        }
        int max = 0;
        for(Node node : ones){
            max = Math.max(max,regionSize(node));
        }
        return max;
    }

    static int regionSize(Node root){
        if(root.visited == true){
            return 0;
        }
        root.visited = true;
        int size = 1;
        for(Node neighbor : root.neighbors){
            if(!neighbor.visited && neighbor.value == 1){
                size += regionSize(neighbor);
            }
        }
        return size;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            String[] gridRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < m; j++) {
                int gridItem = Integer.parseInt(gridRowItems[j]);
                grid[i][j] = gridItem;
            }
        }

        int res = maxRegion(grid);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

