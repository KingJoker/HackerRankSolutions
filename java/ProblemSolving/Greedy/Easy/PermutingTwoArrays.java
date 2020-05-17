/*
Consider two n-element arrays of integers, A=[A[0],A[1],...,A[n-1] and B=[B[0],B[1],...B[n-1]].
You want to permute them into some A' and B' such that the relation A'[i]+B'[i]>=k holds for all i where 0<=i<n.
For example, if A=[0,1], B=[0,2], and k=1, a valid A',B' satisfying our relation would be
A'=[1,0] and B'=[0,2], 1+0>=1 and 0+2>=1.
You are given q queries consisting of A, B, and K.
For each query, print YES on a new line if some permutation A', B' satisfying the relation above exists. Otherwise, print NO.
*Function Description
Complete the twoArrays function in the editor below. It should return a string, either YES or NO.
*twoArrays has the following parameter(s):
k: an integer
A: an array of integers
B: an array of integers
*Input Format
The first line contains an integer q, the number of queries.
The next q sets of 3 lines are as follows:
The first line contains two space-separated integers n and k, the size of both arrays A and B, and the relation variable.
The second line contains n space-separated integers A[i].
The third line contains n space-separated integers B[i].
*Constraints
1<=q<=10
1<=n<=1000
1<=k<=10^9
0<=A[i],B[i]<=10^9
*Output Format
For each query, print YES on a new line if valid permutations exist. Otherwise, print NO.
*Sample Input
2
3 10
2 1 3
7 8 9
4 5
1 2 2 1
3 3 3 4
*Sample Output
YES
NO
*Explanation
We perform the following two queries:
1. A=[2,1,3], B=[7,8,9], and k=10. We permute these into A'=[1,2,3] and B'=[9,8,7] so that the following statements are true:
A[0]+B[0]=1+9=10>=k
A[1]+B[1]=2+8=10>=k
A[2]+B[2]=3+7=10>=k
Thus, we print YES on a new line.
2. A=[1,2,2,1], B=[2,2,2,4], and k=5. To permute A and B into a valid A' and B',
we would need at least three numbers in A to be greater than 1; as this is not the case, we print NO on a new line.
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.Collectors;

public class PermutingTwoArrays {

    // Complete the twoArrays function below.
    static String twoArrays(int k, int[] A, int[] B) {
        ArrayList<Integer> A1 = Arrays.stream(A).boxed().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> B1 = Arrays.stream(B).boxed().collect(Collectors.toCollection(ArrayList::new));
        Collections.sort(A1);
        Collections.sort(B1, Comparator.reverseOrder());
        for(int i = 0; i < A1.size(); i++){
            if(((long)A1.get(i) + B1.get(i)) < k){
                return "NO";
            }
        }
        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] A = new int[n];

            String[] AItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int AItem = Integer.parseInt(AItems[i]);
                A[i] = AItem;
            }

            int[] B = new int[n];

            String[] BItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int BItem = Integer.parseInt(BItems[i]);
                B[i] = BItem;
            }

            String result = twoArrays(k, A, B);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

