/*
Louise joined a social networking site to stay in touch with her friends.
The signup page required her to input a name and a password. However, the password must be strong.
The website considers a password to be strong if it satisfies the following criteria:
Its length is at least 6.
It contains at least one digit.
It contains at least one lowercase English character.
It contains at least one uppercase English character.
It contains at least one special character. The special characters are: !@#$%^&*()-+
She typed a random string of length n in the password field but wasn't sure if it was strong.
Given the string she typed, can you find the minimum number of characters she must add to make her password strong?
Note: Here's the set of types of characters in a form you can paste in your solution:
numbers = "0123456789"
lower_case = "abcdefghijklmnopqrstuvwxyz"
upper_case = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
special_characters = "!@#$%^&*()-+"
*Input Format
The first line contains an integer n denoting the length of the string.
The second line contains a string consisting of n characters, the password typed by Louise.
Each character is either a lowercase/uppercase English alphabet, a digit, or a special character.
*Constraints
1<=n<=100
*Output Format
Print a single line containing a single integer denoting the answer to the problem.
*Sample Input 0
3
Ab1
*Sample Output 0
3
*Explanation 0
She can make the password strong by adding 3 characters, for example, $hk, turning the password into Ab1$hk which is strong.
2 characters aren't enough since the length must be at least 6.
*Sample Input 1
11
#HackerRank
*Sample Output 1
1
*Explanation 1
The password isn't strong, but she can make it strong by adding a single digit.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class StrongPassword {

    // Complete the minimumNumber function below.
    static int minimumNumber(int n, String password) {
        HashSet<Character> specialCharacters = "!@#$%^&*()-+".chars().mapToObj(c -> (char)c).collect(Collectors.toCollection(HashSet::new));
        boolean digit = false;
        boolean lower = false;
        boolean upper = false;
        boolean special = false;
        for(char c : password.toCharArray()){
            if(Character.isDigit(c)){
                digit = true;
            }
            else if(Character.isLowerCase(c)){
                lower = true;
            }
            else if(Character.isUpperCase(c)){
                upper = true;
            }
            else if(specialCharacters.contains(c)){
                special = true;
            }
        }

        int toAdd = 0;
        if(!digit){
            toAdd++;
        }
        if(!lower){
            toAdd++;
        }
        if(!upper){
            toAdd++;
        }
        if(!special){
            toAdd++;
        }

        if(password.length() + toAdd < 6){
            return 6 - password.length();
        }
        return toAdd;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String password = scanner.nextLine();

        int answer = minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

