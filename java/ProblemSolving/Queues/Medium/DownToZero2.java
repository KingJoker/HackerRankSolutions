/*
You are given Q queries.
Each query consists of a single number N. You can perform any of the 2 operations on N in each move:
1: If we take 2 integers a and b where N=a*b(a!=1,b!=1), then we can change N = max(a,b)
2: Decrease the value of N by 1.
Determine the minimum number of moves required to reduce the value of N to 0.
*Input Format
The first line contains the integer Q.
The next Q lines each contain an integer, N.
*Constraints
1<=Q<=10^3
0<=N<=10^6
*Output Format
Output Q lines. Each line containing the minimum number of moves required to reduce the value of N to 0.
*Sample Input
2
3
4
*Sample Output
3
3
*Explanation
For test case 1, We only have one option that gives the minimum number of moves.
Follow 3->2->1->0. Hence, 3 moves.
For the case 2, we can either go 4->3->2->1->  or 4->2->1->0. The 2nd option is more optimal. Hence, 3 moves.
 */

package Queues.Medium;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class DownToZero2 {

    private static class Node{
        int depth;
        int value;
        public Node(int value, int depth){
            this.value = value;
            this.depth = depth;
        }
    }

    static int downToZero(int n) {
        LinkedList<Node> queue = new LinkedList<>();
        Node start = new Node(n,0);
        queue.offer(start);

        while(queue.size() > 0){
            Node current = queue.poll();
            if(current.value == 4){
                return current.depth + 3;
            }
            if(current.value < 4){
                return current.depth + current.value;
            }
            queue.offer(new Node(current.value - 1, current.depth + 1));
            int sqrt = (int)Math.sqrt(current.value);
            for(int i = sqrt; i >= 2; i--){
                if(current.value % i == 0){
                    queue.offer(new Node(current.value / i, current.depth + 1));
                }
            }
        }
        return -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = Integer.parseInt(scanner.nextLine().trim());

        for (int qItr = 0; qItr < q; qItr++) {
            int n = Integer.parseInt(scanner.nextLine().trim());

            int result = downToZero(n);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}

