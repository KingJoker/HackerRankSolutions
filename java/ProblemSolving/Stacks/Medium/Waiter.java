/*
You are a waiter at a party. There are N stacked plates on pile A[0].
Each plate has a number written on it. Then there will be Q iterations.
In i-th iteration, you start picking up the plates in A[i-1] from the top one by one and check whether
the number written on the plate is divisible by the i-th prime.
If the number is divisible, you stack that plate on pile B[i].
Otherwise, you stack that plate on pile A[i]. After Q iterations, plates can only be on pile B[1],B[2],...,B[Q], A[q].
Output numbers on these plates from top to bottom of each piles in order of B[1],B[2],...,B[Q], A[Q].
*Input Format
The first line contains two space separated integers, N and Q.
The next line contains N space separated integers representing the initial pile of plates, i.e., A[0].
The leftmost value represents the bottom plate of the pile.
*Constraints
1<=N<=5*10^4
2<=number[i]<=10^4
1<=Q<=1200
*Output Format
Output N lines. Each line contains a number written on the plate. Printing should be done in the order defined above.
*Sample Input 0
5 1
3 4 7 6 5
*Sample Output 0
4
6
3
7
5
*Explanation 0
Initially:
A[0] = [3, 4, 7, 6, 5]<-TOP
After 1 iteration:
A[0] = []<-TOP
B[1] = [6, 4]<-TOP
A[1] = [5, 7, 3]<-TOP
We should output numbers in B[1] first from top to bottom, and then output numbers in A[1] from top to bottom.
*Sample Input 1
5 2
3 3 4 4 9
*Sample Output 1
4
4
9
3
3
*Explanation 1
Initially:
A[0] = [3, 3, 4, 4, 9]<-TOP
After 1st iteration:
A[0] = []<-TOP
B[1] = [4, 4]<-TOP
A[1] = [3, 3, 9]<-TOP
After 2nd iteration:
A[1] = []<-TOP
B[1] = [4, 4]<- TOP
B[2] = [3, 3, 9]<-TOP
We should output numbers in B[1] first from top to bottom, and then output numbers in B[2] from top to bottom.
 */

package Stacks.Medium;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Waiter {

    /*
     * Complete the waiter function below.
     */
    static int[] waiter(int[] number, int q) {
        ArrayList<Stack<Integer>> b = new ArrayList<>();
        Stack<Integer> a = new Stack<>();
        for(int i = 0; i < number.length; i++){
            a.push(number[i]);
        }
        BigInteger prime = BigInteger.ZERO;
        for(int i = 0; i < q; i++){
            Stack<Integer> newA = new Stack<>();
            Stack<Integer> newB = new Stack<>();
            prime = prime.nextProbablePrime();
            while(a.size() > 0){
                int currentA = a.pop();
                if(currentA % prime.intValue() == 0){
                    newB.push(currentA);
                }
                else{
                    newA.push(currentA);
                }
            }
            a = newA;
            b.add(newB);
        }
        int[] ret = new int[number.length];
        int retIndex = 0;
        for(int i = 0; i < b.size(); i++){
            Stack<Integer> currentB = b.get(i);
            while(currentB.size() > 0){
                ret[retIndex++] = currentB.pop();
            }
        }
        while(a.size() > 0){
            ret[retIndex++] = a.pop();
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nq = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nq[0].trim());

        int q = Integer.parseInt(nq[1].trim());

        int[] number = new int[n];

        String[] numberItems = scanner.nextLine().split(" ");

        for (int numberItr = 0; numberItr < n; numberItr++) {
            int numberItem = Integer.parseInt(numberItems[numberItr].trim());
            number[numberItr] = numberItem;
        }

        int[] result = waiter(number, q);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
