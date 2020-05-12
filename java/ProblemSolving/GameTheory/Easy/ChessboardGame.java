/*
Two players are playing a game on a 15x15 chessboard. The rules of the game are as follows:
The game starts with a single coin located at some x,y coordinates.
The coordinates of the upper left cell are (1,1), and of the lower right cell are (15,15).
In each move, a player must move the coin from cell (x,y) to one of the following locations:
1. (x-2,y+1)
2. (x-2,y-1)
3. (x+1,y-1)
4. (x-1,y-2)
Note: The coin must remain inside the confines of the board.
Beginning with player 1, the players alternate turns. The first player who is unable to make a move loses the game.
The figure below shows all four possible moves using an 8x8 board for illustration:
********  #=next move
********  S=start
**#*#***
*#******
***S****
*#******
********
********
Given the initial coordinates of the players' coins, assuming optimal play, determine which player will win the game.
*Function Description
Complete the chessboardGame function in the editor below. It should return a string, either First or Second.
*chessboardGame has the following parameter(s):
x: an integer that represents the starting column position
y: an integer that represents the starting row position
*Input Format
The first line contains an integer t, the number of test cases.
Each of the next t lines contains 2 space-separated integers x and y.
*Constraints
1<=t<=225
1<=x[i],y[i]<=15
*Output Format
On a new line for each test case, print First if the first player is the winner. Otherwise, print Second.
*Sample Input
3
5 2
5 3
8 8
*Sample Output
Second
First
First
*Explanation
In the first case, player1 starts at the red square and can move to any of the blue squares.
Regardless of which one is chosen, the player 2 can move to one of the green squares to win the game.
*G***
G****
B*B**
*****
*R***
In the second case, player 1 starts at the red square and can move to any of the blue squares or the purple one.
Moving to the purple one limits player 2 to the yellow square.
From the yellow square, player 1 moves to the green square and wins.
******
G*****
*B*B**
BY****
**R***
P*****
 */

package GameTheory.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ChessboardGame {
    static HashMap<ArrayList<Integer>,Boolean> cache = new HashMap<>();
    // Complete the chessboardGame function below.
    static String chessboardGame(int x, int y) {
        return winner(x-1,y-1)?"First":"Second";
    }

    static boolean winner(int x, int y){
        if(x < 0 || y < 0 || x >= 15 || y >= 15){
            return true;
        }
        ArrayList<Integer> position = new ArrayList<>();
        position.add(x);
        position.add(y);
        if(cache.containsKey(position)){
            return cache.get(position);
        }
        boolean one = winner(x-2,y+1);
        boolean two = winner(x-2,y-1);
        boolean three = winner(x+1,y-2);
        boolean four = winner(x-1,y-2);
        boolean winner = !(one&&two&&three&four);
        cache.put(position, winner);
        return winner;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] xy = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xy[0]);

            int y = Integer.parseInt(xy[1]);

            String result = chessboardGame(x, y);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

