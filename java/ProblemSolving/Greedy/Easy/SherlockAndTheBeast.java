/*
Sherlock Holmes suspects his archenemy Professor Moriarty is once again plotting something diabolical.
Sherlock's companion, Dr. Watson, suggests Moriarty may be responsible for MI6's recent issues with their supercomputer, The Beast.
Shortly after resolving to investigate, Sherlock receives a note from Moriarty boasting about infecting The Beast with a virus.
He also gives him a clue: an integer.
Sherlock determines the key to removing the virus is to find the largest Decent Number having that number of digits.
*A Decent Number has the following properties:
1. Its digits can only be 3's and/or 5's.
2. The number of 3's it contains is divisible by 5.
3. The number of 5's it contains is divisible by 3.
4. It is the largest such number for its length.
Moriarty's virus shows a clock counting down to The Beast's destruction, and time is running out fast.
Your task is to help Sherlock find the key before The Beast is destroyed!
For example, the numbers 55533333 and 555555 are both decent numbers because there are 3 5's and 5 3's in the first, and 6 5's in the second.
They are the largest values for those length numbers that have proper divisibility of digit occurrences.
*Function Description
Complete the decentNumber function in the editor below.
It should print the decent number for the given length, or -1 if a decent number of that length cannot be formed.
*decentNumber has the following parameter(s):
n: the integer length of the decent number to create
*Input Format
The first line is an integer, t, denoting the number of test cases.
The next t lines each contain an integer n, the number of digits in the number.
*Constraints
1<=t<=20
1<=n<=100000
*Output Format
Print the Decent Number having n digits; if no such number exists, tell Sherlock by printing -1.
*Sample Input
4
1
3
5
11
*Sample Output
-1
555
33333
55555533333
*Explanation
For n=1, there is no Decent Number having 1 digit (so we print -1).
For n=3, 555 is the only possible number. (Decent Number Property 3).
For n=5, 33333 is the only possible number. (Decent Number Property 2).
For n=11, 55555533333 is the Decent Number. All other permutations of these digits are not decent (Decent Number Property 4).
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class SherlockAndTheBeast {

    // Complete the decentNumber function below.
    static void decentNumber(int n) {
        int fives = n;
        int threes = 0;
        while(fives >= 0){
            if(fives % 3 == 0 && threes % 5 == 0){
                for(int i = 0; i < n; i++){
                    if(fives > i){
                        System.out.print(5);
                    }
                    else{
                        System.out.print(3);
                    }
                }
                System.out.println();
                return;
            }
            if(threes % 5 != 0){
                int change = 5 - threes % 5;
                fives -= change;
                threes += change;
            }
            else if(fives % 3 != 0){
                int change = 3 - fives % 3;
                fives -= change;
                threes += change;
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                decentNumber(n);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

