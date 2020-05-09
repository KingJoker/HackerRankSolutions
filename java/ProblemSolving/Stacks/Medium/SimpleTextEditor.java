/*
In this challenge, you must implement a simple text editor.
Initially, your editor contains an empty string, S. You must perform Q operations of the following 4 types:
1. append(W) - Append string W to the end of S.
2. delete(k) - Delete the last k characters of S.
3. print(k) - Print the kth character of S.
4. undo() - Undo the last (not previously undone) operation of type 1 or 2, reverting S to the state it was in prior to that operation.
*Input Format
The first line contains an integer, Q, denoting the number of operations.
Each line i of the Q subsequent lines (where 0<=i<=Q) defines an operation to be performed.
Each operation starts with a single integer, t (where ts∈{1,2,3,4}), denoting a type of operation as defined in the Problem Statement above.
If the operation requires an argument, t is followed by its space-separated argument.
For example, if t=1 and W="abcd", line i will be 1 abcd.
*Constraints
1<=Q<=10^6
1<=k<= |s|
The sum of the lengths of all W in the input <=10^6.
The sum of k over all delete operations <=2*10^6.
All input characters are lowercase English letters.
It is guaranteed that the sequence of operations given as input is possible to perform.
*Output Format
Each operation of type 3 must print the kth character on a new line.
*Sample Input
8
1 abc
3 3
2 3
1 xy
3 2
4
4
3 1
*Sample Output
c
y
a
*Explanation
Initially, S is empty. The following sequence of 8 operations are described below:
1. S="". We append abc to S, so S="abc".
2. Print the 3rd character on a new line. Currently, the 3rd character is c.
3. Delete the last 3 characters in S (abc), so S="".
4. Append xy to S, so S="xy".
5. Print the 2nd character on a new line. Currently, the 2nd character is y.
6. Undo the last update to S, making S empty again (i.e., S="").
7. Undo the next to last update to S (the deletion of the last 3 characters), making S="abc".
8. Print the 1st character on a new line. Currently, the 1st character is a.
 */

package Stacks.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SimpleTextEditor {

    public static ArrayList<Character> parseQueries(String[][] queries){
        Stack<String> backup = new Stack<>();
        ArrayList<Character> output = new ArrayList<>();
        StringBuilder s = new StringBuilder();
        for(int i = 0; i < queries.length; i++){
            switch(queries[i][0]){
                case "1":
                    backup.push(s.toString());
                    s.append(queries[i][1]);
                    break;
                case "2":
                    backup.push(s.toString());
                    s.delete(s.length() - Integer.parseInt(queries[i][1]), s.length());
                    break;
                case "3":
                    output.add(s.charAt(Integer.parseInt(queries[i][1]) - 1));
                    break;
                case "4":
                    s.delete(0,s.length());
                    s.append(backup.pop());
            }
        }
        return output;
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int q = input.nextInt();
        Stack<String> backup = new Stack<>();
        StringBuilder s = new StringBuilder();
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < q; i++){
            int query = input.nextInt();
            switch(query){
                case 1:
                    backup.push(s.toString());
                    s.append(input.next());
                    break;
                case 2:
                    backup.push(s.toString());
                    s.delete(s.length() - input.nextInt(), s.length());
                    break;
                case 3:
                    output.append(s.charAt(input.nextInt() - 1)+"\n");
                    break;
                case 4:
                    s.delete(0,s.length());
                    s.append(backup.pop());
            }
        }
        System.out.println(output);
    }
}
