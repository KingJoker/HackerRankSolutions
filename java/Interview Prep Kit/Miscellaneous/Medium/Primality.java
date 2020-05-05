/*
A prime is a natural number greater than 1 that has no positive divisors other than 1 and itself.
Given p integers, determine the primality of each integer and print whether it is Prime or Not prime on a new line.
Note: If possible, try to come up with an O(√n) primality algorithm,
or see what sort of optimizations you can come up with for an O(n) algorithm.
Be sure to check out the Editorial after submitting your code!
*Function Description
Complete the primality function in the editor below. It should return Prime if n is prime, or Not prime.
*primality has the following parameter(s):
n: an integer to test for primality
*Input Format
The first line contains an integer, p , denoting the number of integers to check for primality.
Each of the p subsequent lines contains an integer, n, the number you must test for primality.
*Constraints
1<=p<=30
*Output Format
For each integer, print whether n is Prime or Not prime on a new line.
*Sample Input
3
12
5
7
*Sample Output
Not prime
Prime
Prime
*Explanation
We check the following p=3 integers for primality:
n=12 is divisible by numbers other than 1 and itself (i.e.: 2, 3, 4, 6), so we print Not prime on a new line.
n=5 is only divisible 1 and itself, so we print Prime on a new line.
n=7 is only divisible 1 and itself, so we print Prime on a new line.
 */


package Miscellaneous.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Primality {

    // Complete the primality function below.
    static String primality(int n) {
        if(n <=1){
            return "Not prime";
        }
        for(int i = 2; i <= Math.sqrt(n); i++){
            if(n % i == 0){
                return "Not prime";
            }
        }
        return "Prime";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int p = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int pItr = 0; pItr < p; pItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            String result = primality(n);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

