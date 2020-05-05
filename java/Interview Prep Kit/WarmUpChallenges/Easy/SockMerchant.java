/*
John works at a clothing store. He has a large pile of socks that he must pair by color for sale.
Given an array of integers representing the color of each sock, determine how many pairs of socks with matching colors there are.
For example, there are n=7 socks with colors ar = [1,2,1,2,1,3,2]. There is one pair of color 1 and one of color 2.
There are three odd socks left, one of each color. The number of pairs is 2.

*Function Description
Complete the sockMerchant function in the editor below.
It must return an integer representing the number of matching pairs of socks that are available.

*sockMerchant has the following parameter(s):
n: the number of socks in the pile
ar: the colors of each sock

*Input Format
The first line contains an integer , the number of socks represented in .
The second line contains  space-separated integers describing the colors  of the socks in the pile.

*Constraints
1<=n<= 100
1<=ar[i]<=100 where 0<=i<n

*Output Format
Return the total number of matching pairs of socks that John can sell.

*Sample Input
9
10 20 20 10 10 30 50 10 20

*Sample Output
3
*/
package WarmUpChallenges.Easy;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SockMerchant {

    // Complete the sockMerchant function below.
    static int sockMerchant(int n, int[] ar) {
        HashMap<Integer,Integer> sockCount = new HashMap<>();
        Arrays.stream(ar).forEach((sock) -> sockCount.merge(sock,1, (oldValue, value) -> oldValue + value));
        int pairCount = sockCount.keySet().stream().mapToInt((key) -> sockCount.get(key) / 2).reduce((x, y) -> x + y).getAsInt();
        return pairCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = sockMerchant(n, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
