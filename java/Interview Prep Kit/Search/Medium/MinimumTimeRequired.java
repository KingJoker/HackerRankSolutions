/*
You are planning production for an order. You have a number of machines that each have a fixed number of days to produce an item.
Given that all the machines operate simultaneously, determine the minimum number of days to produce the required order.
For example, you have to produce goal=10 items. You have three machines that take machines=[2,3,2] days to produce an item.
The following is a schedule of items produced:
Day Production  Count
2   2               2
3   1               3
4   2               5
6   3               8
8   2              10
It takes 8 days to produce 10 items using these machines.
*Function Description
Complete the minimumTime function in the editor below. It should return an integer representing the minimum number of days required to complete the order.
*minimumTime has the following parameter(s):
machines: an array of integers representing days to produce one item per machine
goal: an integer, the number of items required to complete the order
*Input Format
The first line consist of two integers n and goal, the size of machines and the target production.
The next line contains n space-separated integers, machines[i].
*Constraints
1<=n<=10^5
1<=goal<=10^9
1<=machines[i]<=10^9
*Output Format
Return the minimum time required to produce goal items considering all machines work simultaneously.
*Sample Input 0
2 5
2 3
*Sample Output 0
6
*Explanation 0
In 6 days machine[0] can produce 3 items and machine[1] can produce 2 items. This totals up to 5.
*Sample Input 1
3 10
1 3 4
*Sample Output 1
7
*Explanation 1
In 7 days, machine[0] can produce 7 items, machine[1] can produce 2 items and machine[2] can produce 1 item, which totals up to 10.
*Sample Input 2
3 12
4 5 6
*Sample Output 2
20
Explanation 2
In 20 days machine[0] can produce 5 items, machine[1] can produce 4, and machine[2] can produce 3.
 */

package Search.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumTimeRequired {

    // Complete the minTime function below.
    static long minTime(long[] machines, long goal) {
        Arrays.parallelSort(machines);
        long minDay = (long)((double)goal/machines.length) * machines[0];
        long maxDay = (long)(((double)goal/machines.length) * machines[machines.length-1]);
        long dayCount = 0;
        while(dayCount == 0){
            long targetDay = (minDay+maxDay)/2L;
            long itemCount = itemCount(machines, targetDay);
            if(itemCount >= goal && (targetDay == 0 || itemCount(machines, targetDay - 1) < goal)){
                dayCount = targetDay;
                break;
            }
            if(itemCount < goal && itemCount(machines, targetDay + 1) >= goal){
                dayCount = targetDay + 1;
                break;
            }
            if(itemCount >= goal){
                maxDay = targetDay;
            }
            else{
                minDay = targetDay;
            }
        }
        return dayCount;
    }

    static long itemCount(long[] machines, long day){
        long itemCount = 0;
        for(long machine : machines){
            itemCount += day/machine;
        }
        return itemCount;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nGoal = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nGoal[0]);

        long goal = Long.parseLong(nGoal[1]);

        long[] machines = new long[n];

        String[] machinesItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            long machinesItem = Long.parseLong(machinesItems[i]);
            machines[i] = machinesItem;
        }

        long ans = minTime(machines, goal);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
