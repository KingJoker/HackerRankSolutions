/*
We define the distance between two array values as the number of indices between the two values.
Given a, find the minimum distance between any pair of equal elements in the array. If no such value exists, print -1.
For example, if a=[3,2,1,2,3], there are two matching pairs of values: 3 and 2.
The indices of the 3's are i=0 and j=4, so their distance is d[i,j]=|j-i| =4.
The indices of the 2's are i=1 and j=3, so their distance is d[i,j]=|j-i| =2.
*Function Description
Complete the minimumDistances function in the editor below.
It should return the minimum distance between any two matching elements.
*minimumDistances has the following parameter(s):
a: an array of integers
*Input Format
The first line contains an integer n, the size of array .
The second line contains n space-separated integers a[i].
*Constraints
1<=n<=10^3
1<=a[i]<=10^5
*Output Format
Print a single integer denoting the minimum d[i,j] in a. If no such value exists, print -1.
*Sample Input
6
7 1 3 4 1 7
*Sample Output
3
*Explanation
Here, we have two options:
a[1] and a[4] are both 1, so d[1,4]=|1-4| =3.
a[0] and a[5] are both 7, so d[0,5]=|0-5| =5.
The answer is min(3,5)=3.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MinimumDistances {

    // Complete the minimumDistances function below.
    static int minimumDistances(int[] a) {
        HashMap<Integer,TreeSet<Integer>> map = new HashMap<>();
        for(int i = 0; i < a.length; i++){
            TreeSet<Integer> currentIndex = new TreeSet<>();
            currentIndex.add(i);
            map.merge(a[i],currentIndex,(oldValue,newValue)->{oldValue.addAll(newValue);return oldValue;});
        }
        int min = Integer.MAX_VALUE;
        for(int key : map.keySet()){
            ArrayList<Integer> indeces = new ArrayList<>(map.get(key));
            for(int i = 0; i < indeces.size() - 1; i++){
                int currentMin = indeces.get(i+1) - indeces.get(i);
                min = Math.min(min,currentMin);
            }
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] a = new int[n];

        String[] aItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aItems[i]);
            a[i] = aItem;
        }

        int result = minimumDistances(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

