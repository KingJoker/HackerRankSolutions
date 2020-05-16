/*
You are given an array of n integers, ar=[ar[0],ar[1],...,ar[n-1]], and a positive integer, k.
Find and print the number of (i,j) pairs where i<j and ar[i]+ar[j] is divisible by k.
For example, ar=[1,2,3,4,5,6] and k=5. Our three pairs meeting the criteria are [1,4],[2,3] and [4,6].
*Function Description
Complete the divisibleSumPairs function in the editor below.
It should return the integer count of pairs meeting the criteria.
*divisibleSumPairs has the following parameter(s):
n: the integer length of array
ar: an array of integers
k: the integer to divide the pair sum by
*Input Format
The first line contains 2 space-separated integers, n and k.
The second line contains n space-separated integers describing the values of ar[ar[0],ar[1],...,ar[n-1]].
*Constraints
2<=n<=100
1<=k<=100
1<=ar[i]<=100
*Output Format
Print the number of (i,j) pairs where i<j and a[i] + a[j] is evenly divisible by k.
*Sample Input
6 3
1 3 2 6 1 2
*Sample Output
5
*Explanation
Here are the 5 valid pairs when k=3:
(0,2)->ar[0]+ar[2]=1+2=3
(0,5)->ar[0]+ar[5]=1+2=3
(1,3)->ar[1]+ar[3]=3+6=9
(2,4)->ar[2]+ar[4]=2+1=3
(4,5)->ar[4]+ar[5]=1+2=3
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class DivisibleSumPairs {

    // Complete the divisibleSumPairs function below.
    static int divisibleSumPairs(int n, int k, int[] ar) {
        int max = 0;
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>(n);
        HashSet<HashSet<Integer>> seen = new HashSet<>();
        for(int i = 0; i < ar.length; i++){
            ArrayList<Integer> newIndex = new ArrayList<>();
            newIndex.add(i);
            map.merge(ar[i],newIndex, (oldValue,newValue) -> {oldValue.addAll(newValue);return oldValue;});
            max = Math.max(max,ar[i]);
        }
        for(int key : map.keySet()){
            int current = key;
            int target = k - key % k;
            ArrayList<Integer> indexes1 = map.get(key);
            while(target <= max){
                if(map.containsKey(target)){
                    ArrayList<Integer> indexes2 = map.get(target);
                    for(int index1 : indexes1){
                        for(int index2 : indexes2){
                            HashSet<Integer> pair = new HashSet<>(2);
                            pair.add(index1);
                            pair.add(index2);
                            if(pair.size() == 2){
                                seen.add(pair);
                            }
                        }
                    }
                }
                target += k;
            }
        }
        return seen.size();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] ar = new int[n];

        String[] arItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arItem = Integer.parseInt(arItems[i]);
            ar[i] = arItem;
        }

        int result = divisibleSumPairs(n, k, ar);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

