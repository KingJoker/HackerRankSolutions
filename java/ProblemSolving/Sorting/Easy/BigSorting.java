/*
Consider an array of numeric strings where each string is a positive number with anywhere from 1 to 10^6 digits.
Sort the array's elements in non-decreasing, or ascending order of their integer values and
print each element of the sorted array on a new line.
*Function Description
Complete the bigSorting function in the editor below. It should return the sorted string array.
*bigSorting has the following parameter(s):
unsorted: an unsorted array of integers as strings
*Input Format
The first line contains an integer, n, denoting the number of strings in unsorted.
Each of the n subsequent lines contains an integer string unsorted[i].
*Constraints
1<=n<=2*10^5
Each string is guaranteed to represent a positive integer without leading zeros.
The total number of digits across all strings in unsorted is between 1 and 10^6 (inclusive).
*Output Format
Print each element of the sorted array on a new line.
*Sample Input 0
6
31415926535897932384626433832795
1
3
10
3
5
*Sample Output 0
1
3
3
5
10
31415926535897932384626433832795
*Explanation 0
The initial array of strings is unsorted=[31415926535897932384626433832795,1,3,10,3,5].
When we order each string by the real-world integer value it represents, we get:
1<=3<=3<=5<=10<=31415926535897932384626433832795
We then print each value on a new line, from smallest to largest.
*Sample Input 1
8
1
2
100
12303479849857341718340192371
3084193741082937
3084193741082938
111
200
*Sample Output 1
1
2
100
111
200
3084193741082937
3084193741082938
12303479849857341718340192371
 */

package Sorting.Easy;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BigSorting {

    // Complete the bigSorting function below.
    static String[] bigSorting(String[] unsorted) {
        Arrays.parallelSort(unsorted, (x, y) ->{
            int lenX = x.length();
            int lenY = y.length();
            if(lenX != lenY){
                return lenX - lenY;
            }
            for(int i = 0; i < lenX; i++){
                char charX = x.charAt(i);
                char charY = y.charAt(i);
                if(charX != charY){
                    return charX - charY;
                }
            }
            return 0;
        });
        return unsorted;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = Integer.parseInt(reader.readLine());
        //scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] unsorted = new String[n];

        for (int i = 0; i < n; i++) {
            String unsortedItem = reader.readLine();
            unsorted[i] = unsortedItem;
        }

        String[] result = bigSorting(unsorted);
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

