/*
Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
Given a string, find the number of pairs of substrings of the string that are anagrams of each other.
For example s=mom, the list of all anagrammatic pairs is [m,m],[mo,om] at positions [[0],[2]],[[0,1],[1,2] respectively.
*Function Description
Complete the function sherlockAndAnagrams in the editor below.
It must return an integer that represents the number of anagrammatic pairs of substrings in s.
*sherlockAndAnagrams has the following parameter(s):
s: a string.
*Input Format
The first line contains an integer q, the number of queries.
Each of the next q lines contains a string s to analyze.
*Constraints
1<=q<=10
2<= |s| <=100
String s contains only lowercase letters ∈ ascii[a-z].
*Output Format
For each query, return the number of unordered anagrammatic pairs.
*Sample Input 0
2
abba
abcd
*Sample Output 0
4
0
*Explanation 0
The list of all anagrammatic pairs is [a,a],[ab,ba],[b,b] and [abb,bba] at positions [[0],[3]],[[0,1],[2,3]],[[1],[2]] and [[0,1,2],[1,2,3]] respectively.
No anagrammatic pairs exist in the second query as no character repeats.
*Sample Input 1
2
ifailuhkqq
kkkk
*Sample Output 1
3
10
*Explanation 1
For the first query, we have anagram pairs [i,i],[q,q] and [ifa,fai] at positions [[0,3],[8,9]] and [[0,1,2],[1,2,3] respectively.
For the second query:
There are 6 anagrams of the form [k,k] at positions [[0,1],[0,2],[[0],[3]],[[1],[2]],[[1],[3]] and [[2],[3]].
There are 3 anagrams of the form [kk,kk] at positions [[0,1],[1,2]],[[0,1],[2,3]] and [[1,2],[2,3]].
There is 1 anagram of the form [kkk,kkk] at position [[0,1,2].[1,2,3]].
*Sample Input 2
1
cdcd
*Sample Output 2
5
*Explanation 2
There are two anagrammatic pairs of length 1: [c,c] and [d,d].
There are three anagrammatic pairs of length 2:[cd,dc],[cd,cd],[dc,cd] at positions [[0,1],[1,2]],[[0,1],[2,3]],[[1,2],[2,3]] respectively.
 */

package DictionairesAndHashmaps.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class SherlockAndAnagrams {

    // Complete the sherlockAndAnagrams function below.
    static int sherlockAndAnagrams(String s) {
        HashMap<HashMap<Character,Integer>,Integer> anagramCount = new HashMap<>();
        for(int start = 0; start < s.length(); start++){
            for(int end = start + 1; end <= s.length(); end++){
                HashMap<Character,Integer> anagramMap = anagramMap(s.substring(start,end));
                anagramCount.merge(anagramMap,1,(oldValue,one) -> oldValue + one);
            }
        }
        int totalCount = 0;
        for(int count : anagramCount.values()){
            if(count > 1){
                totalCount += ((count-1) * count) / 2;
            }
        }
        return totalCount;
    }

    static HashMap<Character,Integer> anagramMap(String s){
        HashMap<Character,Integer> anagramMap = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            anagramMap.merge(s.charAt(i),1,(oldValue,one) -> oldValue + one);
        }
        return anagramMap;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String s = scanner.nextLine();

            int result = sherlockAndAnagrams(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
