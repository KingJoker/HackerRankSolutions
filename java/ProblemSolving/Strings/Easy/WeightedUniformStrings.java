/*
A weighted string is a string of lowercase English letters where each letter has a weight.
Character weights are 1 to 26 from a to z as shown below:
a 1
b 2
c 3
d 4
e 5
f 6
g 7
h 8
i 9
j 10
k 11
l 12
m 13
n 14
o 15
p 16
q 17
r 18
s 19
t 20
u 21
v 22
w 23
x 24
y 25
z 26
We define the following terms:
The weight of a string is the sum of the weights of all the string's characters. For example:
apple  1+16+16+12+5=50
hack   8+1+3+11=23
watch  23+1+20+30+8=53
ccccc  3+3+3+3+3=15
aaa    1+1+1=3
zzzz   26+26+26+26
A uniform string consists of a single character repeated zero or more times.
For example, ccc and a are uniform strings, but bcb and cd are not.
Given a string, s, let U be the set of weights for all possible uniform contiguous substrings of string s.
You have to answer n queries, where each query i consists of a single integer, x[i].
For each query, print Yes on a new line if x[i]∈U; otherwise, print No instead.
Note: The ∈ symbol denotes that x[i] is an element of set U.
*Function Description
Complete the weightedUniformStrings function in the editor below.
It should return an array of strings, either Yes or No, one for each query.
*weightedUniformStrings has the following parameter(s):
s: a string
queries: an array of integers
*Input Format
The first line contains a string s, the original string.
The second line contains an integer n, the number of queries.
Each of the next n lines contains an integer x[i], the weight of a uniform subtring of s that may or may not exist.
*Constraints
1<= |s|,n <=10^5
1<=x[i]<=10^7
s will only contain lowercase English letters, ascii[a-z].
*Output Format
Print n lines. For each query, print Yes on a new line if x[i]∈U. Otherwise, print No.
*Sample Input 0
abccddde
6
1
3
12
5
9
10
*Sample Output 0
Yes
Yes
Yes
Yes
No
No
*Explanation 0
The weights of every possible uniform substring in the string abccddde are shown below:
a    1
b    2
c    3
cc   3+3=6
d    4
dd   4+4=8
ddd  4+4+4=12
e    5
We print Yes on the first four lines because the first four queries match weights of uniform substrings of s.
We print No for the last two queries because there are no uniform substrings in s that have those weights.
Note that while de is a substring of s that would have a weight of 9, it is not a uniform substring.
Note that we are only dealing with contiguous substrings. So ccc is not a substring of the string ccxxc.
*Sample Input 1
aaabbbbcccddd
5
9
7
8
12
5
*Sample Output 1
Yes
No
Yes
Yes
No
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class WeightedUniformStrings {

    // Complete the weightedUniformStrings function below.
    static String[] weightedUniformStrings(String s, int[] queries) {
        HashSet<Integer> weights = new HashSet<>();
        int weight = 0;
        int previous = ' ';
        for(int i = 0; i < s.length(); i++){
            char current = s.charAt(i);
            if(current == previous){
                weight += current - 96;
            }
            else{
                weight = current - 96;
            }
            previous = current;
            weights.add(weight);
        }
        String[] ret = new String[queries.length];
        for(int i = 0; i < queries.length; i++){
            if(weights.contains(queries[i])){
                ret[i] = "Yes";
            }
            else{
                ret[i] = "No";
            }
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String s = scanner.nextLine();

        int queriesCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[queriesCount];

        for (int i = 0; i < queriesCount; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        String[] result = weightedUniformStrings(s, queries);

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

