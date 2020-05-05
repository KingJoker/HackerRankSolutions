/*
You are given a square grid with some cells open (.) and some blocked (X).
Your playing piece can move along any row or column until it reaches the edge of the grid or a blocked cell.
Given a grid, a start and an end position, determine the number of moves it will take to get to the end position.
For example, you are given a grid with sides n=3 described as follows:
...
.X.
...
Your starting position (startX,startY)=(0,0) so you start in the top left corner.
The ending position is (goalX,goalY)=(1,2).
The path is (0,0)->(0,2)->(1,2). It takes 2 moves to get to the goal.
*Function Description
Complete the minimumMoves function in the editor.
It must print an integer denoting the minimum moves required to get from the starting position to the goal.
*minimumMoves has the following parameter(s):
grid: an array of strings representing the rows of the grid
startX: an integer
startY: an integer
goalX: an integer
goalY: an integer
*Input Format
The first line contains an integer n, the size of the array grid.
Each of the next n lines contains a string of length .
The last line contains four space-separated integers, startX, startY, goalX, goalY
*Constraints
1<=n<=100
0<=startX,startY,goalX,goalY<n
*Output Format
Print an integer denoting the minimum number of steps required to move the castle to the goal position.
*Sample Input
3
.X.
.X.
...
0 0 0 2
*Sample Output
3
*Explanation
Here is a path that one could follow in order to reach the destination in 3 steps:
(0,0)->(2,0)->(2,2)->(0,2).
 */

package StacksAndQueues.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CastleOnTheGrid {

    private static class Cell{
        int x;
        int y;
        boolean visited;
        int numMoves;

        public Cell(int x, int y){
            this.x = x;
            this.y = y;
            numMoves = 0;
            visited = false;
        }
    }

    // Complete the minimumMoves function below.
    static int minimumMoves(String[] grid, int startX, int startY, int goalX, int goalY) {
        Cell[][] cells = new Cell[grid.length][grid.length];
        for(int x = 0; x < grid.length; x++){
            char[] line = grid[x].toCharArray();
            for(int y = 0; y < line.length; y++){
                if(line[y] == '.'){
                    cells[x][y] = new Cell(x,y);
                }
            }
        }
        LinkedList<Cell> queue = new LinkedList<>();
        Cell current = cells[startX][startY];
        while(!(current.x == goalX && current.y == goalY)){

            //up
            for(int x = current.x - 1; x >= 0 && cells[x][current.y] != null; x--){
                if(x == goalX && current.y == goalY){
                    return current.numMoves + 1;
                }
                Cell currentCell = cells[x][current.y];
                if(! currentCell.visited){
                    currentCell.visited = true;
                    currentCell.numMoves = current.numMoves + 1;
                    queue.offer(currentCell);
                }
            }
            //down
            for(int x = current.x + 1; x < cells.length && cells[x][current.y] != null; x++){
                if(x == goalX && current.y == goalY){
                    return current.numMoves + 1;
                }
                Cell currentCell = cells[x][current.y];
                if(! currentCell.visited){
                    currentCell.visited = true;
                    currentCell.numMoves = current.numMoves + 1;
                    queue.offer(currentCell);
                }
            }
            //left
            for(int y = current.y - 1; y >= 0 && cells[current.x][y] != null; y--){
                if(current.x == goalX && y == goalY){
                    return current.numMoves + 1;
                }
                Cell currentCell = cells[current.x][y];
                if(! currentCell.visited){
                    currentCell.visited = true;
                    currentCell.numMoves = current.numMoves + 1;
                    queue.offer(currentCell);
                }

            }
            //right
            for(int y = current.y + 1; y < cells.length && cells[current.x][y] != null; y++){
                if(current.x == goalX && y == goalY){
                    return current.numMoves + 1;
                }
                Cell currentCell = cells[current.x][y];
                if(! currentCell.visited){
                    currentCell.visited = true;
                    currentCell.numMoves = current.numMoves + 1;
                    queue.offer(currentCell);
                }
            }
            current = queue.poll();
        }
        return 0;
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

        String[] startXStartY = scanner.nextLine().split(" ");

        int startX = Integer.parseInt(startXStartY[0]);

        int startY = Integer.parseInt(startXStartY[1]);

        int goalX = Integer.parseInt(startXStartY[2]);

        int goalY = Integer.parseInt(startXStartY[3]);

        int result = minimumMoves(grid, startX, startY, goalX, goalY);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

