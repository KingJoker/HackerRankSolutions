/*
Sunny and Johnny like to pool their money and go to the ice cream parlor.
Johnny never buys the same flavor that Sunny does. The only other rule they have is that they spend all of their money.
Given a list of prices for the flavors of ice cream, select the two that will cost all of the money they have.
For example, they have m=6 to spend and there are flavors costing cost=[1,3,4,5,6].
The two flavors costing 1 and 5 meet the criteria. Using 1-based indexing, they are at indices 1 and 4.
*Function Description
Complete the icecreamParlor function in the editor below.
It should return an array containing the indices of the prices of the two flavors they buy, sorted ascending.
*icecreamParlor has the following parameter(s):
m: an integer denoting the amount of money they have to spend
cost: an integer array denoting the cost of each flavor of ice cream
*Input Format
The first line contains an integer, t, denoting the number of trips to the ice cream parlor.
The next t sets of lines each describe a visit. Each trip is described as follows:
The integer m, the amount of money they have pooled.
The integer n, the number of flavors offered at the time.
n space-separated integers denoting the cost of each flavor: cost[cost[1],cost[2],...,cost[n]].
Note: The index within the cost array represents the flavor of the ice cream purchased.
*Constraints
1<=t<=50
2<=m<=10^4
2<=n<=10^4
1<=cost[i]<=10^4, ∀ i∈[1,n]
There will always be a unique solution.
*Output Format
For each test case, print two space-separated integers denoting the indices of the two flavors purchased, in ascending order.
*Sample Input
2
4
5
1 4 5 3 2
4
4
2 2 4 3
*Sample Output
1 4
1 2
*Explanation
Sunny and Johnny make the following two trips to the parlor:
1. The first time, they pool together m=4 dollars. Of the five flavors available that day, flavors 1 and 4 have a total cost of 1+3=4.
2. The second time, they pool together m=4 dollars. Of the four flavors available that day, flavors 1 and 2 have a total cost of 2+2=4.
 */

package Search.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class IceCreamParlor {

    // Complete the icecreamParlor function below.
    static int[] icecreamParlor(int m, int[] arr) {
        HashMap<Integer,ArrayList<Integer>> cache = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int elem = arr[i];
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(i+1);
            cache.merge(elem, temp, (oldValue, newValue) ->{oldValue.addAll(newValue);return oldValue;});
        }
        for(int elem : cache.keySet()){
            int complement = m - elem;
            if(cache.containsKey(complement)){
                int[] ret;
                if(elem == complement){
                    if(cache.get(complement).size() >=2) {
                        ret = new int[]{cache.get(complement).get(0), cache.get(complement).get(1)};
                    }
                    else{
                        continue;
                    }
                }
                else{
                    ret = new int[]{cache.get(elem).get(0), cache.get(complement).get(0)};
                }
                Arrays.sort(ret);
                return ret;
            }
        }
        return new int[2];
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int m = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] result = icecreamParlor(m, arr);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

