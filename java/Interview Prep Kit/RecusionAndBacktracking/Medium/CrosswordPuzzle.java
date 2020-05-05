/*
A 10x10 Crossword grid is provided to you, along with a set of words (or names of places) which need to be filled into the grid.
Cells are marked either + or -. Cells marked with a - are to be filled with the word list.
The following shows an example crossword from the input crossword grid and the list of words to fit,
words=[POLAND,LHASA,SPAIN,INDIA]:
Input 	   		Output
++++++++++ 		++++++++++
+------+++ 		+POLAND+++
+++-++++++ 		+++H++++++
+++-++++++ 		+++A++++++
+++-----++ 		+++SPAIN++
+++-++-+++ 		+++A++N+++
++++++-+++ 		++++++D+++
++++++-+++ 		++++++I+++
++++++-+++ 		++++++A+++
++++++++++ 		++++++++++
POLAND;LHASA;SPAIN;INDIA
*Function Description
Complete the crosswordPuzzle function in the editor below. It should return an array of strings, each representing a row of the finished puzzle.
*crosswordPuzzle has the following parameter(s):
crossword: an array of 10 strings of length 10 representing the empty grid
words: a string consisting of semicolon delimited strings to fit into crossword
*Input Format
Each of the first 10 lines represents crosswords[i], each of which has 10 characters, crossword[i][j].
The last line contains a string consisting of semicolon delimited words[i] to fit.
*Constraints
1<= |words| <=10
crossword[i][j]∈{+,-}
words[i][j]∈ascii[A-Z]
*Output Format
Position the words appropriately in the 10x10 grid, then return your array of strings for printing.
*Sample Input 0
+-++++++++
+-++++++++
+-++++++++
+-----++++
+-+++-++++
+-+++-++++
+++++-++++
++------++
+++++-++++
+++++-++++
LONDON;DELHI;ICELAND;ANKARA
*Sample Output 0
+L++++++++
+O++++++++
+N++++++++
+DELHI++++
+O+++C++++
+N+++E++++
+++++L++++
++ANKARA++
+++++N++++
+++++D++++
*Sample Input 1
+-++++++++
+-++++++++
+-------++
+-++++++++
+-++++++++
+------+++
+-+++-++++
+++++-++++
+++++-++++
++++++++++
AGRA;NORWAY;ENGLAND;GWALIOR
*Sample Output 1
+E++++++++
+N++++++++
+GWALIOR++
+L++++++++
+A++++++++
+NORWAY+++
+D+++G++++
+++++R++++
+++++A++++
++++++++++
*Sample Input 2
XXXXXX-XXX
XX------XX
XXXXXX-XXX
XXXXXX-XXX
XXX------X
XXXXXX-X-X
XXXXXX-X-X
XXXXXXXX-X
XXXXXXXX-X
XXXXXXXX-X
ICELAND;MEXICO;PANAMA;ALMATY
*Sample Output 2
XXXXXXIXXX
XXMEXICOXX
XXXXXXEXXX
XXXXXXLXXX
XXXPANAMAX
XXXXXXNXLX
XXXXXXDXMX
XXXXXXXXAX
XXXXXXXXTX
XXXXXXXXYX
*/

package RecusionAndBacktracking.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CrosswordPuzzle {

    private static class Entry{
        int length;
        boolean across;
        int[] start;
        boolean filled;
        public Entry(int[] start, int length, boolean across){
            this.start = start;
            this.length = length;
            this.across = across;
            filled = false;
        }
    }

    // Complete the crosswordPuzzle function below.
    static String[] crosswordPuzzle(String[] crossword, String words) {
        String[] wordList = words.split(";");
        char[][] puzzle = new char[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                puzzle[i][j] = crossword[i].charAt(j);
            }
        }
        ArrayList<Entry> entries = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(puzzle[i][j] == '-'){
                    int countAcross = 0;
                    int k = j;
                    for(; k < puzzle[i].length && puzzle[i][k] == '-'; k++){
                        countAcross++;
                        if((i < puzzle.length - 1 && puzzle[i+1][k] == '-') && (i == 0 || (i >= 1 && puzzle[i-1][k] != '-'))){
                            int countDown = 0;
                            for(int l = i; l < puzzle.length && puzzle[l][k] == '-'; l++){
                                countDown++;
                            }
                            Entry down = new Entry(new int[]{i,k},countDown, false);
                            entries.add(down);
                        }

                    }
                    if(countAcross > 1) {
                        Entry across = new Entry(new int[]{i,j}, countAcross, true);
                        entries.add(across);
                    }
                    j = k - 1;
                }
            }
        }

        solve(puzzle,entries,wordList,0);

        String[] output = new String[10];
        for(int i = 0; i < puzzle.length; i++){
            String line = "";
            for(int j = 0; j < puzzle[i].length; j++){
                line += puzzle[i][j];
            }
            output[i] = line;
        }
        return output;
    }

    public static boolean solve(char[][] puzzle, ArrayList<Entry> entries, String[] words, int wordIndex){
        if(wordIndex >= words.length){
            return true;
        }
        for(Entry entry : entries) {
            if (!entry.filled) {
                if(entry.length == words[wordIndex].length()) {
                    HashSet<Integer> alreadyFilled = fill(puzzle, entry, words[wordIndex]);
                    if (alreadyFilled != null) {
                        entry.filled = true;
                        boolean successSolve = solve(puzzle, entries, words, wordIndex+1);
                        if (successSolve) {
                            return true;
                        }
                        entry.filled = false;
                        unfill(puzzle, entry, alreadyFilled, entry.length - 1);
                    }
                }
            }
        }
        return false;
    }

    public static void unfill(char[][] puzzle, Entry entry, HashSet<Integer> alreadyFilled, int end){
        for(int index = 0; index <= end; index++){
            if(!alreadyFilled.contains(index)){
                if(entry.across){
                    puzzle[entry.start[0]][entry.start[1] + index] = '-';
                }
                else{
                    puzzle[entry.start[0] + index][entry.start[1]] = '-';
                }
            }
        }
    }

    public static HashSet<Integer> fill(char[][] puzzle, Entry entry, String word){
        HashSet<Integer> alreadyFilled = new HashSet<>();
        if(entry.across){
            int i = entry.start[0];
            int jStart = entry.start[1];
            alreadyFilled = new HashSet<>();
            for(int j = 0;j < entry.length; j++){
                if(puzzle[i][jStart + j] == '-'){
                    puzzle[i][jStart + j] = word.charAt(j);
                }
                else if(puzzle[i][jStart + j] == word.charAt(j)){
                    alreadyFilled.add(j);
                }
                else{
                    unfill(puzzle,entry,alreadyFilled, j - 1);
                    return null;
                }
            }
        }
        else{
            int iStart = entry.start[0];
            int j = entry.start[1];
            alreadyFilled = new HashSet<>();
            for(int i = 0;i < entry.length; i++){
                if(puzzle[iStart + i][j] == '-'){
                    puzzle[iStart + i][j] = word.charAt(i);
                }
                else if(puzzle[iStart + i][j] == word.charAt(i)){
                    alreadyFilled.add(i);
                }
                else{
                    unfill(puzzle,entry,alreadyFilled, i - 1);
                    return null;
                }
            }
        }

        return alreadyFilled;
    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] crossword = new String[10];

        for (int i = 0; i < 10; i++) {
            String crosswordItem = scanner.nextLine();
            crossword[i] = crosswordItem;
        }

        String words = scanner.nextLine();

        String[] result = crosswordPuzzle(crossword, words);

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

