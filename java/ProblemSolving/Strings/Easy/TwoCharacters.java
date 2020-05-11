/*
In this challenge, you will be given a string.
You must remove characters until the string is made up of any two alternating characters.
When you choose a character to remove, all instances of that character must be removed.
Your goal is to create the longest string possible that contains just two alternating letters.
As an example, consider the string abaacdabd. If you delete the character a, you will be left with the string bcdbd.
Now, removing the character c leaves you with a valid string bdbd having a length of 4.
Removing either b or d at any point would not result in a valid string.
Given a string s, convert it to the longest possible string t made up only of alternating characters.
Print the length of string t on a new line. If no string t can be formed, print 0 instead.
*Function Description
Complete the alternate function in the editor below.
It should return an integer that denotes the longest string that can be formed, or 0 if it cannot be done.
*alternate has the following parameter(s):
s: a string
*Input Format
The first line contains a single integer denoting the length of s.
The second line contains string s.
*Constraints
1<= |s| <=1000
s[i]∈ascii[a-z]
*Output Format
Print a single integer denoting the maximum length of t for the given s;
if it is not possible to form string t, print 0 instead.
*Sample Input
10
beabeefeab
*Sample Output
5
*Explanation
The characters present in s are a, b, e, and f.
This means that t must consist of two of those characters and we must delete two others.
Our choices for characters to leave are [a,b], [a,e], [a, f], [b, e], [b, f] and [e, f].
If we delete e and f, the resulting string is babab.
This is a valid t as there are only two distinct characters (a and b), and they are alternating within the string.
If we delete a and f, the resulting string is bebeeeb. This is not a valid string t because there are consecutive e's present.
Removing them would leave consecutive b's, so this fails to produce a valid string t.
Other cases are solved similarly.
babab is the longest string we can create.
 */

package Strings.Easy;

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

public class TwoCharacters {

    // Complete the alternate function below.
    static int alternate(String s) {
        int max = 0;
        HashSet<Character> alreadyChecked = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            char current = s.charAt(i);
            if(alreadyChecked.contains(current)){
                continue;
            }
            alreadyChecked.add(current);

            HashSet<Character> alreadyChecked2 = new HashSet<>();
            for(int j = i + 1; j < s.length(); j++){
                char checking = s.charAt(j);
                if(current == checking){
                    break;
                }
                Stack<Character> stack = new Stack<>();
                stack.push(current);
                stack.push(checking);
                if(alreadyChecked.contains(checking) || alreadyChecked2.contains(checking)){
                    continue;
                }
                for(int k = j + 1; k < s.length(); k++){
                    char checking2 = s.charAt(k);
                    if(checking2 == current || checking2 == checking){
                        if(stack.peek() != checking2) {
                            stack.push(checking2);
                        }
                        else{
                            stack = null;
                            break;
                        }
                    }
                }
                if(stack!=null){
                    max = Math.max(max,stack.size());
                }
                alreadyChecked2.add(checking);
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = alternate(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
