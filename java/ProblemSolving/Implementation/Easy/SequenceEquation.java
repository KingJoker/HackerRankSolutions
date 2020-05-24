/*
Given a sequence of n integers, p(1),p(2),...,p(n) where each element is distinct and satisfies 1<=p(x)<=n.
For each x where 1<=x<=n, find any integer y such that p(p(y)=x and print the value of y on a new line.
For example, assume the sequence p=[5,2,1,3,4]. Each value of x between 1 and 5,
the length of the sequence, is analyzed as follows:
1. x=1=p[3],p[4]=3, so p[p[4]]=1
2. x=2=p[2],p[2]=2, so p[p[4]]=2
3. x=3=p[4],p[5]=4, so p[p[4]]=3
4. x=4=p[5],p[1]=5, so p[p[4]]=4
5. x=5=p[1],p[3]=1, so p[p[4]]=5
The values for y are [4,2,5,1,3].
*Function Description
Complete the permutationEquation function in the editor below.
It should return an array of integers that represent the values of y.
*permutationEquation has the following parameter(s):
p: an array of integers
*Input Format
The first line contains an integer n, the number of elements in the sequence.
The second line contains n space-separated integers p[i] where 1<=i<=n.
Constraints
1<=n<=50
1<=p[i]<=50, where 1<=i<=n.
Each element in the sequence is distinct.
*Output Format
For each x from 1 to n, print an integer denoting any valid y satisfying the equation p(p(y))=x on a new line.
*Sample Input 0
3
2 3 1
*Sample Output 0
2
3
1
*Explanation 0
Given the values of p(1)=2, p(2)=3, and p(3)=1, we calculate and print the following values for each x from 1 to n:
1. x=1=p(3)=p(p(2))=p(p(y)), so we print the value of y=2 on a new line.
2. x=2=p(1)=p(p(3))=p(p(y)), so we print the value of y=3 on a new line.
3. x=3=p(2)=p(p(1))=p(p(y)), so we print the value of y=1 on a new line.
*Sample Input 1
5
4 3 5 1 2
*Sample Output 1
1
3
5
4
2
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.regex.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SequenceEquation {

    // Complete the permutationEquation function below.
    static int[] permutationEquation(int[] p) {
        int[] ret = new int[p.length];
        HashMap<Integer,Integer> values = new HashMap<>();
        for(int i = 0; i < p.length; i++){
            values.put(p[i],i+1);
        }
        for(int i = 1; i <=p.length; i++){
            int index1 = values.get(i);
            int index2 = values.get(index1);
            ret[i-1] = index2;
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

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int[] result = permutationEquation(p);

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

