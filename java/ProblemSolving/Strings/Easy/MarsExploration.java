/*
Sami's spaceship crashed on Mars! She sends a series of SOS messages to Earth for help.
Letters in some of the SOS messages are altered by cosmic radiation during transmission.
Given the signal received by Earth as a string, s, determine how many letters of Sami's SOS have been changed by radiation.
For example, Earth receives SOSTOT. Sami's original message was SOSSOS. Two of the message characters were changed in transit.
*Function Description
Complete the marsExploration function in the editor below.
It should return an integer representing the number of letters changed during transmission.
*marsExploration has the following parameter(s):
s: the string as received on Earth
*Input Format
There is one line of input: a single string, s.
Note: As the original message is just SOS repeated n times, s's length will be a multiple of 3.
*Constraints
1<= |s| <=99
|s|%3=0
s will contain only uppercase English letters, ascii[A-Z].
*Output Format
Print the number of letters in Sami's message that were altered by cosmic radiation.
*Sample Input 0
SOSSPSSQSSOR
*Sample Output 0
3
*Explanation 0

s = SOSSPSSQSSOR, and signal length |s| =12. Sami sent  SOS messages (i.e.:12/3=4).
Expected signal: SOSSOSSOSSOS
Recieved signal: SOSSPSSQSSOR
Difference:          X  X   X
We print the number of changed letters.
*Sample Input 1
SOSSOT
*Sample Output 1
1
*Explanation 1
s = SOSSOT, and signal length |s| =6. Sami sent 2 SOS messages (i.e.:6/3=2).
Expected Signal: SOSSOS
Received Signal: SOSSOT
Difference:           X
We print the number of changed letters, which is 1.
*Sample Input 2
SOSSOSSOS
*Sample Output 2
0
*Explanation 2
Since no character is altered, we print 0.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MarsExploration {

    // Complete the marsExploration function below.
    static int marsExploration(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            char current = s.charAt(i);
            if(i%3==1){
                if(current != 'O'){
                    count++;
                }
            }
            else{
                if(current != 'S'){
                    count++;
                }
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String s = scanner.nextLine();

        int result = marsExploration(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

