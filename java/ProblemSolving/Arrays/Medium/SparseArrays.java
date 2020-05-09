/*
There is a collection of input strings and a collection of query strings.
For each query string, determine how many times it occurs in the list of input strings.
For example, given input strings=['ab','ab','abc'] and queries=['ab','abc','bc'], we find 2 instances of 'ab',
1 of 'abc' and 0 of 'bc'. For each query, we add an element to our return array, results[2,1,0].
*Function Description
Complete the function matchingStrings in the editor below.
The function must return an array of integers representing the frequency of occurrence of each query string in strings.
*matchingStrings has the following parameters:
strings - an array of strings to search
queries - an array of query strings
*Input Format
The first line contains and integer n, the size of strings.
Each of the next n lines contains a string strings[i].
The next line contains q, the size of queries.
Each of the next q lines contains a string q[i].
*Constraints
1<=n<=1000
1<=q<=1000
1<= |strings[i]|,|queries[i]|<=20.
*Output Format
Return an integer array of the results of all queries in order.
*Sample Input 1
4
aba
baba
aba
xzxb
3
aba
xzxb
ab
*Sample Output 1
2
1
0
Explanation 1
Here, "aba" occurs twice, in the first and third string. The string "xzxb" occurs once in the fourth string, and "ab" does not occur at all.
*Sample Input 2
3
def
de
fgh
3
de
lmn
fgh
*Sample Output 2
1
0
1
*Sample Input 3
13
abcde
sdaklfj
asdjf
na
basdn
sdaklfj
asdjf
na
asdjf
na
basdn
sdaklfj
asdjf
5
abcde
sdaklfj
asdjf
na
basdn
*Sample Output 3
1
3
4
3
2
*/

package Arrays.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SparseArrays {

    // Complete the matchingStrings function below.
    static int[] matchingStrings(String[] strings, String[] queries) {
        HashMap<String,Integer> map = new HashMap<>();
        for(String string : strings){
            map.merge(string,1,(oldValue, one) -> oldValue + one);
        }
        int[] ret = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            ret[i] = map.getOrDefault(queries[i],0);
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int stringsCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] strings = new String[stringsCount];

        for (int i = 0; i < stringsCount; i++) {
            String stringsItem = scanner.nextLine();
            strings[i] = stringsItem;
        }

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] queries = new String[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            String queriesItem = scanner.nextLine();
            queries[i] = queriesItem;
        }

        int[] res = matchingStrings(strings, queries);

        for (int i = 0; i < res.length; i++) {
            bufferedWriter.write(String.valueOf(res[i]));

            if (i != res.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

