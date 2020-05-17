/*
Jim's Burgers has a line of hungry customers. Orders vary in the time it takes to prepare them.
Determine the order the customers receive their orders.
Start by numbering each of the customers from 1 to n, front of the line to the back.
You will then be given an order number and a preparation time for each customer.
The time of delivery is calculated as the sum of the order number and the preparation time.
If two orders are delivered at the same time, assume they are delivered in ascending customer number order.
For example, there are n=5 customers in line. They each receive an order number order[i] and a preparation time prep[i].:
Customer	1	2	3	4	5
Order #		8	5	6	2	4
Prep time	3	6	2	3	3
Calculate:
Serve time	11	11	8	5	7
We see that the orders are delivered to customers in the following order:
Order by:
Serve time	5	7	8	11	11
Customer	4	5	3	1	2
*Function Description
Complete the jimOrders function in the editor below.
It should return an array of integers that represent the order that customers' orders are delivered.
*jimOrders has the following parameter(s):
orders: a 2D integer array where each orders[i] is in the form [order[i],prep[i]].
*Input Format
The first line contains an integer n, the number of customers.
Each of the next n lines contains two space-separated integers, an order number and prep time for customer[i].
*Constraints
1<=n<=10^3
1<=i<n
1<=order[i],prep[i]<=10^6
*Output Format
Print a single line of n space-separated customer numbers (recall that customers are numbered from 1 to n)
that describes the sequence in which the customers receive their burgers.
If two or more customers receive their burgers at the same time, print their numbers in ascending order.
*Sample Input 0
3
1 3
2 3
3 3
*Sample Output 0
1 2 3
*Explanation 0
Jim has the following orders:
1. order[1]=1,prep[1]=3. This order is delivered at time t=1+3=4.
2. order[1]=2,prep[2]=3. This order is delivered at time t=2+3=5.
3. order[3]=1,prep[3]=3. This order is delivered at time t=3+3=6.
The orders were delivered in the same order as the customers stood in line.
The index in order[i] is the customer number and is what is printed.
In this case, the customer numbers match the order numbers.
*Sample Input 1
5
8 1
4 2
5 6
3 1
4 3
*Sample Output 1
4 2 5 1 3
*Explanation 1
Jim has the following orders:
1. order[1]=1,prep[1]=8. This order is delivered at time t=8+1=9.
2. order[2]=1,prep[1]=2. This order is delivered at time t=4+2=6.
3. order[3]=1,prep[1]=6. This order is delivered at time t=5+6=11.
4. order[4]=1,prep[1]=1. This order is delivered at time t=3+1=4.
5. order[5]=1,prep[1]=3. This order is delivered at time t=4+3=7.
When we order these by ascending fulfillment time, we get:

t=4: customer 4.
t=6: customer 2.
t=7: customer 5.
t=9: customer 1.
t=11. customer 3.
We print the ordered numbers in the bulleted listed above as 4 2 5 1 3.
Note: While not demonstrated in these sample cases, recall that any orders fulfilled at the same time
must be listed by ascending order number.
 */

package Greedy.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JimAndTheOrders {

    // Complete the jimOrders function below.
    static int[] jimOrders(int[][] orders) {
        TreeMap<Long,TreeSet<Integer>> map = new TreeMap<>();
        for(int i = 0; i < orders.length; i++){
            long time = (long)orders[i][0]+orders[i][1];
            TreeSet<Integer> newCustomer = new TreeSet<>();
            newCustomer.add(i+1);
            map.merge(time,newCustomer, (oldCustomers, newCustomers) -> {oldCustomers.addAll(newCustomers);return oldCustomers;});
        }
        int[] ret = new int[orders.length];
        int index = 0;
        for(Long time : map.keySet()){
            TreeSet<Integer> customers = map.get(time);
            for(int customer : customers){
                ret[index++] = customer;
            }
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

        int[][] orders = new int[n][2];

        for (int i = 0; i < n; i++) {
            String[] ordersRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int ordersItem = Integer.parseInt(ordersRowItems[j]);
                orders[i][j] = ordersItem;
            }
        }

        int[] result = jimOrders(orders);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

