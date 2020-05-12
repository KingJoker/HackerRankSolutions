/*
Nim is the most famous two-player algorithm game. The basic rules for this game are as follows:
The game starts with a number of piles of stones. The number of stones in each pile may not be equal.
The players alternately pick up 1 or more stones from 1 pile
The player to remove the last stone wins.
For example, there are n=3 piles of stones having pile=[3,2,4] stones in them. Play may proceed as follows:
Player  Takes           Leaving
                        pile=[3,2,4]
1       2 from pile[1]  pile=[3,4]
2       2 from pile[1]  pile=[3,2]
1       1 from pile[0]  pile=[2,2]
2       1 from pile[0]  pile=[1,2]
1       1 from pile[1]  pile=[1,1]
2       1 from pile[0]  pile=[0,1]
1       1 from pile[1]  WIN
Given the value of n and the number of stones in each pile, determine the game's winner if both players play optimally.
*Function Desctription
Complete the nimGame function in the editor below. It should return a string, either First or Second.
*nimGame has the following parameter(s):
pile: an integer array that represents the number of stones in each pile
*Input Format
The first line contains an integer, g, denoting the number of games they play.
Each of the next g pairs of lines is as follows:
The first line contains an integer n, the number of piles.
The next line contains n space-separated integers pile[i], the number of stones in each pile.
*Constraints
1<=g<=100
1<=n<=100
0<=s[i]<=100
Player 1 always goes first.
*Output Format
For each game, print the name of the winner on a new line (i.e., either First or Second).
*Sample Input
2
2
1 1
3
2 1 4
*Sample Output
Second
First
*Explanation
In the first case, there are n=2 piles of pile=[1,1] stones.
Player 1 has to remove one pile on the first move. Player 2 removes the second for a win.
In the second case, there are n=3 piles of pile=[2,1,4] stones.
If player 1 removes any one pile, player 2 can remove all but one of another pile and force a win.
If player 1 removes less than a pile, in any case, player 2 can force a win as well, given optimal play.
 */

package GameTheory.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class IntroductionToNimGame {

    static HashMap<ArrayList<Integer>, Boolean> cache = new HashMap<>();
    // Complete the nimGame function below.

    static String nimGame(int[] pile) {
        return Arrays.stream(pile).reduce((x,y) -> x^y).getAsInt() == 0 ? "Second": "First";
    }

    static String nimGameSlow(int[] pile) {
        ArrayList<Integer> piles = Arrays.stream(pile).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(piles);
        removeDuplicates(piles);
        return winner(piles)? "First":"Second";
    }

    static boolean winner(ArrayList<Integer> piles){
        if(piles.size() == 0){
            return false;
        }
        if(cache.containsKey(piles)){
            return cache.get(piles);
        }
        boolean winner = false;
        for(int i = 0; !winner && i < piles.size(); i++){
            for(int j = piles.get(i); !winner && j > 0; j--){
                ArrayList<Integer> clone = (ArrayList<Integer>)piles.clone();
                int currentPile = clone.remove(i);
                currentPile -= j;
                int index = 0;
                if(currentPile > 0){
                    index = Collections.binarySearch(clone,currentPile);
                    if(index < 0){
                        index = -1 * (index + 1);
                        clone.add(index,currentPile);
                    }
                    else{
                        clone.remove(index);
                    }
                }
                winner = winner || !winner(clone);
            }
        }
        cache.put(piles,winner);
        return winner;
    }

    static void removeDuplicates(ArrayList<Integer> list){
        for(int i = list.size()-2; i >=0; i--){
            if(list.get(i) == list.get(i+1)){
                list.remove(i+1);
                list.remove(i);
                i--;
            }
        }
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int g = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int gItr = 0; gItr < g; gItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] pile = new int[n];

            String[] pileItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int pileItem = Integer.parseInt(pileItems[i]);
                pile[i] = pileItem;
            }

            String result = nimGame(pile);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

