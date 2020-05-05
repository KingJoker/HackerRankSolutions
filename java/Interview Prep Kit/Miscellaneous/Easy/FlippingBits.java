/*
You will be given a list of 32 bit unsigned integers. Flip all the bits (1->0 and 0->1) and print the result as an unsigned integer.
For example, your decimal input n=9(base 10) = 1001 (base 2). We're working with 32 bits, so:
00000000000000000000000000001001 (base 2) = 9 (base 10)
11111111111111111111111111110110 (base 2) = 4294967286 (base 10)
*Function Description
Complete the flippingBits function in the editor below. It should return the unsigned decimal integer result.
*flippingBits has the following parameter(s):
n: an integer
*Input Format
The first line of the input contains q, the number of queries.
Each of the next q lines contain an integer, n, to process.
*Constraints
1<=q<=100
0<=n<=2^32
*Output Format
Output one line per element from the list with the decimal value of the resulting unsigned integer.
*Sample Input 0
3
2147483647
1
0
*Sample Output 0
2147483648
4294967294
4294967295
*Explanation 0
01111111111111111111111111111111 (base 2) = 2147483647 (base 10)
10000000000000000000000000000000 (base 2) = 2147483648 (base 10)
00000000000000000000000000000001 (base 2) = 1 (base 10)
11111111111111111111111111111110 (base 2) = 4294967294 (base 10)
00000000000000000000000000000000 (base 2) = 0 (base 10)
11111111111111111111111111111111 (base 2) = 4294967295 (base 10)
*Sample Input 1
2
4
123456
*Sample Output 1
4294967291
4294843839
Explanation 1
00000000000000000000000000000100 (base 2) = 4 (base 10)
11111111111111111111111111111011 (base 2) = 4294967291 (base 10)
00000000000000011110001001000000 (base 2) = 123456 (base 10)
11111111111111100001110110111111 (base 2) = 4294843839 (base 10)
*Sample Input 2
3
0
802743475
35601423
*Sample Output 2
4294967295
3492223820
4259365872
Explanation 2
00000000000000000000000000000000 (base 2) = 0 (base 10)
11111111111111111111111111111111 (base 2) = 4294967295 (base 10)
00101111110110001110010010110011 (base 2) = 802743475 (base 10)
11010000001001110001101101001100 (base 2) = 3492223820 (base 10)
00000010000111110011110000001111 (base 2) = 35601423 (base 10)
11111101111000001100001111110000 (base 2) = 4259365872 (base 10)
 */


package Miscellaneous.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FlippingBits {

    // Complete the flippingBits function below.
    static long flippingBits(long n) {
        return n ^ 0xFFFFFFFFL;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            long n = scanner.nextLong();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            long result = flippingBits(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
