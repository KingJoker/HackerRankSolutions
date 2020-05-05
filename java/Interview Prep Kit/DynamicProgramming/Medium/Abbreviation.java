/*
You can perform the following operations on the string, a:
Capitalize zero or more of a's lowercase letters.
Delete all of the remaining lowercase letters in a.
Given two strings, a and b, determine if it's possible to make a equal to b as described.
If so, print YES on a new line. Otherwise, print NO.
For example, given a=AbcDE and b=ABDE, in a we can convert b and delete c to match b.
If a=AbcDE and b=AFDE, matching is not possible because letters may only be capitalized or discarded, not changed.
*Function Description
Complete the function abbreviation in the editor below. It must return either YES or NO.
*abbreviation has the following parameter(s):
a: the string to modify
b: the string to match
*Input Format
The first line contains a single integer q, the number of queries.
Each of the next q pairs of lines is as follows:
- The first line of each query contains a single string, a.
- The second line of each query contains a single string, b.
*Constraints
1<=q<=10
1<= |a|,|b| <=1000
String a consists only of uppercase and lowercase English letters, ascii[A-Za-z].
String b consists only of uppercase English letters, ascii[A-Z].
*Output Format
For each query, print YES on a new line if it's possible to make string a equal to string b.
Otherwise, print NO.
*Sample Input
1
daBcd
ABC
*Sample Output
YES
*Explanation
daBcd->dABCd->ABC
We have a=daBcd and b=ABC. We perform the following operation:
Capitalize the letters a and c in a so that a=dABCd.
Delete all the remaining lowercase letters in  so that  ABC.
Because we were able to successfully convert a to b, we print YES on a new line.
 */

package DynamicProgramming.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import static java.util.concurrent.CompletableFuture.runAsync;

public class Abbreviation {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        return abbreviationHelper(a,b, Collections.synchronizedMap(new HashMap<>())) ? "YES" : "NO";
    }

    static boolean abbreviationHelper(String a, String b, Map<ArrayList<String>,Boolean> cache){
        ArrayList<String> pair = new ArrayList<>();
        pair.add(a);
        pair.add(b);
        if(cache.containsKey(pair)){
            return cache.get(pair);
        }
        if(b.length() == 0){
            for(char c : a.toCharArray()){
                if(Character.isUpperCase(c)){
                    return false;
                }
            }
            return true;
        }
        if(a.length() == 0){
            return false;
        }
        if(a.length() < b.length()){
            return false;
        }
        char charA = a.charAt(a.length() - 1);
        char charB = b.charAt(b.length() -1);
        boolean ret = false;
        if(charA == charB){
            ret = abbreviationHelper(a.substring(0,a.length()-1), b.substring(0,b.length() - 1),cache);
        }
        else if(Character.isLowerCase(charA)){
            if(Character.toUpperCase(charA) == charB){
                ret = abbreviationHelper(a.substring(0,a.length() - 1), b.substring(0,b.length() - 1),cache)
                        || abbreviationHelper(a.substring(0,a.length() -1), b,cache);
            }
            else{
                ret = abbreviationHelper(a.substring(0,a.length() -1),b,cache);
            }
        }
        cache.put(pair,ret);
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

