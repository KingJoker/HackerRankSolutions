/*
Create a list, seqList, of N empty sequences, where each sequence is indexed from 0 to N-1.
The elements within each of the  sequences also use 0-indexing.
Create an integer, lastAnswer, and initialize it to 0.
The 2 types of queries that can be performed on your list of sequences (seqList) are described below:
1. Query: 1 x y
    1. Find the sequence, seq, at index ((x⊕lastAnswer)%N) in seqList.
    2. Append integer y to sequence seq.
2. Query: 2 x y
    1. Find the sequence, seq, at index ((x⊕lastAnswer)%N) in seqList.
    2. Find the value of element y%size in seq (where size is the size of seq) and assign it to lastAnswer.
    3. Print the new value of  on a new line
*Task
Given N, Q, and Q queries, execute each query.
Note: x⊕ is the bitwise XOR operation, which corresponds to the ^ operator in most languages. Learn more about it on Wikipedia.
*Input Format
The first line contains two space-separated integers, N (the number of sequences) and Q (the number of queries), respectively.
Each of the Q subsequent lines contains a query in the format defined above.
Constraints
1<=N,Q<=10^5
0<=x<=10^9
0<=y<=10^9
It is guaranteed that query type 2 will never query an empty sequence or index.
*Output Format
For each type 2 query, print the updated value of lastAnswer on a new line.
*Sample Input
2 5
1 0 5
1 1 7
1 0 3
2 1 0
2 1 1
*Sample Output
7
3
*Explanation
Initial Values:
N=2
lastAnswer=0
S[0] = [ ]
S[1] = [ ]
Query 0: Append 5 to sequence ((0⊕0)%2)=0.
lastAnswer=0
S[0] = [5]
S[1] = [ ]
Query 1: Append 7 to sequence ((1⊕0)%2)=1.
S[0] = [5]
S[1] = [7]
Query 2: Append 3 to sequence ((0⊕0)%2)=0.
LastAnswer=0
S[0] = [5, 3]
S[1] = [7]
Query 3: Assign the value at index 0 of sequence ((1⊕0)%2)=1 to lastAnswer, print lastAnswer.
lastAnswer=7
S[0] = [5, 3]
S[1] = [7]
Output: 7
Query 4: Assign the value at index 0 of sequence ((1⊕7)%2)=0 to lastAnswer, print lastAnswer.
lastAnswer=3
S[0] = [5, 3]
S[1] = [7]
Output: 3
 */

package Arrays.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;



public class DynamicArray {

    private static class Result {

        /*
         * Complete the 'dynamicArray' function below.
         *
         * The function is expected to return an INTEGER_ARRAY.
         * The function accepts following parameters:
         *  1. INTEGER n
         *  2. 2D_INTEGER_ARRAY queries
         */

        public static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
            ArrayList<Integer> ret = new ArrayList<>();
            int lastAnswer = 0;
            ArrayList<ArrayList<Integer>> seqList = new ArrayList<>();
            for(int i = 0; i < n; i++){
                seqList.add(new ArrayList<Integer>());
            }
            for(List<Integer> query : queries){
                int type = query.get(0);
                int x = query.get(1);
                int y = query.get(2);
                int index = (x^lastAnswer) % n;
                ArrayList<Integer> seq = seqList.get(index);
                if(type == 1){
                    seq.add(y);
                }
                else if(type == 2){
                    lastAnswer = seq.get(y % seq.size());
                    ret.add(lastAnswer);
                }
            }
            return ret;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> queries = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            String[] queriesRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> queriesRowItems = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowTempItems[j]);
                queriesRowItems.add(queriesItem);
            }

            queries.add(queriesRowItems);
        }

        List<Integer> result = Result.dynamicArray(n, queries);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

