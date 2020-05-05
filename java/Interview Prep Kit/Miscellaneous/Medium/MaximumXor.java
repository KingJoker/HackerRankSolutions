/*
You are given an array arr of n elements.
A list of integers, queries is given as an input, find the maximum value of queries[j]⊕each arr[i] for all 0<=i<n,
where ⊕ represents xor of two elements.
Note that there are multiple test cases in one input file.
For example:
arr=[3,7,15]
queries[j]=3
3⊕3=0, max=0
3⊕7=4, max=4
3⊕15=12, max=12
3⊕10=9, max=12
*Function Description
Complete the maxXor function in the editor below.
It must return an array of integers, each representing the maximum xor value for each element queries[j] against all elements of arr.
*maxXor has the following parameter(s):
arr: an array of integers
queries: an array of integers to query
*Input Format
The first line contains an integer n, the size of the array arr.
The second line contains n space-separated integers, arr[i] from 0<=i<=n.
The third line contain m, the size of the array queries.
Each of the next m lines contains an integer queries[j] where 0<=j<m.
*Constraints
1<=n,m<=10^5
0<=arr[i],queries[j]<=10^9
Output Format
The output should contain m lines with each line representing output for the corresponding input of the testcase.
*Sample Input 0
3
0 1 2
3
3
7
2
*Sample Output 0
3
7
3
Explanation 0
arr=[0,1,2]
queries[0]=3
3⊕0=3,max=3
3⊕1=2,max=3
3⊕2=1,max=3
queries[1]=7
7⊕0=7,max=7
7⊕1=6,max=7
7⊕2=5,max=7
queries[2]=2
2⊕0=2,max=2
2⊕1=3,max=3
2⊕2=0,max=3
*Sample Input 1
5
5 1 7 4 3
2
2
0
*Sample Output 1
7
7
*Explanation 1
arr=[5,1,7,4,3]
queries[0]=2
2⊕5=7,max=7
2⊕1=3,max=7
2⊕7=5,max=7
2⊕4=6,max=7
2⊕3=1,max=7
queries[1]=0
0⊕5=5,max=5
0⊕1=1,max=5
0⊕7=7,max=7
0⊕4=4,max=7
0⊕3=3,max=7
*Sample Input 2
4
1 3 5 7
2
17
6
*Sample Output 2
22
7
*Explanation 2
arr=[1,3,5,7]
queries[0]=17
17⊕1=16,max=16
17⊕3=18,max=18
17⊕5=20,max=20
17⊕7=22,max=22
queries[1]=6
6⊕1=7,max=7
6⊕3=5,max=7
6⊕5=3,max=7
6⊕7=1,max=7
 */

package Miscellaneous.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MaximumXor {
    private static class Node{
        Node zero;
        Node one;
        public Node(){

        }
    }

    static int[] maxXor(int[] arr, int[] queries) {
        int ret[] = new int[queries.length];
        Node root = new Node();
        for(int i = 0; i < arr.length; i++){
            int current = arr[i];
            String currentString = Integer.toBinaryString(current);
            Node currentNode = root;
            for(int j = 29; j >= 0; j--){
                if(j >= currentString.length()){
                    if(currentNode.zero == null){
                        currentNode.zero = new Node();
                    }
                    currentNode = currentNode.zero;
                }
                else if(currentString.charAt(currentString.length()-1-j) == '0'){
                    if(currentNode.zero == null){
                        currentNode.zero = new Node();
                    }
                    currentNode = currentNode.zero;
                }
                else{
                    if(currentNode.one == null){
                        currentNode.one = new Node();
                    }
                    currentNode = currentNode.one;
                }
            }
        }

        for(int i = 0; i < queries.length; i++) {
            int current = queries[i];
            String currentString = Integer.toBinaryString(current);
            String target = "";
            Node currentNode = root;
            for(int j = 29; j >= 0; j--) {
                if(currentNode.one == null){
                    target = target + '0';
                    currentNode = currentNode.zero;
                }
                else if(currentNode.zero == null){
                    target = target + '1';
                    currentNode = currentNode.one;
                }
                else {
                    int searchFor;
                    if (j >= currentString.length()) {
                        searchFor = '1';
                    } else {
                        if (currentString.charAt(currentString.length() - 1 - j) == '1') {
                            searchFor = '0';
                        } else {
                            searchFor = '1';
                        }
                    }
                    if(searchFor == '1'){
                        target = target + '1';
                        currentNode = currentNode.one;
                    }
                    else{
                        target = target + '0';
                        currentNode = currentNode.zero;
                    }
                }
            }
            int targetInt = Integer.parseUnsignedInt(target,2);
            ret[i] = current ^ targetInt;
        }
        return ret;
    }


    static int[] maxXorSlow(int[] arr, int[] queries) {
        int ret[] = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int max = 0;
            for(int j = 0; j < arr.length; j++){
                max = Math.max(max,queries[i]^arr[j]);
            }
            ret[i]=max;
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int m = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] queries = new int[m];

        for (int i = 0; i < m; i++) {
            int queriesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            queries[i] = queriesItem;
        }

        int[] result = maxXor(arr, queries);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
