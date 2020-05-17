/*
Priyanka works for an international toy company that ships by container.
Her task is to the determine the lowest cost way to combine her orders for shipping.
She has a list of item weights.
The shipping company has a requirement that all items loaded in a container must weigh
less than or equal to 4 units plus the weight of the minimum weight item.
All items meeting that requirement will be shipped in one container.
What is the smallest number of containers that can be contracted to ship the items based on the given list of weights?
For example, there are items with weights w=[1,2,3,4,5,10,11,12,13].
This can be broken into two containers: [1,2,3,4,5] and [10,11,12,13].
Each container will contain items weighing within 4 units of the minimum weight item.
*Function Description
Complete the toys function in the editor below. It should return the minimum number of containers required to ship.
*toys has the following parameter(s):
w: an array of integers that represent the weights of each order to ship
*Input Format
The first line contains an integer n, the number of orders to ship.
The next line contains n space-separated integers, w[1],w[2],...,2[n], representing the orders in a weight array.
*Constraints
1<=n<=10^5
0<=w[i]<=10^4
*Output Format
Return the integer value of the number of containers Priyanka must contract to ship all of the toys.
*Sample Input
8
1 2 3 21 7 12 14 21
*Sample Output
4
*Explanation

The first container holds items weighing 1,2 and 3. (weights in range 1...5)
The second container holds the items weighing 21 units. (21...25)
The third container holds the item weighing 7 units. (7...11)
The fourth container holds the items weighing 12 and 14 units. (12...14)
4 containers are required.
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PriyankaAndToys {

    // Complete the toys function below.
    static int toys(int[] w) {
        Arrays.parallelSort(w);
        int count = 0;
        int target = Integer.MIN_VALUE;
        for(int weight : w){
            if(weight > target){
                count++;
                target = weight + 4;
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] w = new int[n];

        String[] wItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int wItem = Integer.parseInt(wItems[i]);
            w[i] = wItem;
        }

        int result = toys(w);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

