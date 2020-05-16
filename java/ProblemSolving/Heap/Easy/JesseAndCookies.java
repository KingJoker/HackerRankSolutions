/*
Jesse loves cookies. He wants the sweetness of all his cookies to be greater than value K.
To do this, Jesse repeatedly mixes two cookies with the least sweetness. He creates a special combined cookie with:
sweetness=(1*Least sweet cookie + 2*2nd least sweet cookie).
He repeats this procedure until all the cookies in his collection have a sweetness >=K.
You are given Jesse's cookies. Print the number of operations required to give the cookies a sweetness >=K.
Print -1 if this isn't possible.
*Input Format
The first line consists of integers N, the number of cookies and K, the minimum required sweetness, separated by a space.
The next line contains N integers describing the array A where A[i] is the sweetness of the ith cookie in Jesse's collection.
*Constraints
1<=N<=10^6
0<=K<=10^9
0<=A[i]<=10^6
*Output Format
Output the number of operations that are needed to increase the cookie's sweetness >=K.
Output -1 if this isn't possible.
*Sample Input
6 7
1 2 3 9 10 12
*Sample Output
2
*Explanation
Combine the first two cookies to create a cookie with sweetness=1*1+2*2=5
After this operation, the cookies are 3,5,9,10,12.
Then, combine the cookies with sweetness 3 and sweetness 5, to create a cookie with resulting sweetness=1*3+2*5=13
Now, the cookies are 9,10,12,13.
All the cookies have a sweetness>=7.
Thus, 2 operations are required to increase the sweetness.
 */

package Heap.Easy;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class JesseAndCookies {

    /*
     * Complete the cookies function below.
     */
    static int cookies(int k, PriorityQueue<Integer> heap) {
        int ops = 0;
        while(heap.size() >= 2 && heap.peek() < k){
            int cookie1 = heap.poll();
            int cookie2 = heap.poll();
            int newCookie = cookie1 + 2*cookie2;
            heap.offer(newCookie);
            ops++;
        }
        return heap.peek() >= k?ops : -1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0].trim());

        int k = Integer.parseInt(nk[1].trim());

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            heap.offer(scanner.nextInt());
        }

        int result = cookies(k, heap);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

