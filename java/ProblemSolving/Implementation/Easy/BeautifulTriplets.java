/*
Given a sequence of integers a, a triplet (a[i],a[j],a[k] is beautiful if:
i<j<k
a[j]-a[i]=a[k]-a[j]=d
Given an increasing sequence of integers and the value of d, count the number of beautiful triplets in the sequence.
For example, the sequence arr=[2,2,3,4,5] and d=1.
There are three beautiful triplets, by index: [i,j,k]=[0,2,3],[1,2,3],[2,3,4].
To test the first triplet, arr[j]-arr[i]=3-2=1 and arr[k]-arr[j]=4-3=1.
*Function Description
Complete the beautifulTriplets function in the editor below.
It must return an integer that represents the number of beautiful triplets in the sequence.
*beautifulTriplets has the following parameters:
d: an integer
arr: an array of integers, sorted ascending
*Input Format
The first line contains 2 space-separated integers n and d, the length of the sequence and the beautiful difference.
The second line contains n space-separated integers arr[i].
*Constraints
1<=n<=10^4
1<=d<=20
0<=arr[i]<=2*10^4
arr[i]>arr[i-1]
*Output Format
Print a single line denoting the number of beautiful triplets in the sequence.
*Sample Input
7 3
1 2 4 5 7 8 10
*Sample Output
3
*Explanation
The input sequence is 1,2,3,4,5,7,8,10, and our beautiful difference d=3.
There are many possible triplets (arr[i],arr[j],arr[k]), but our only
beautiful triplets are (1,4,7), (4,7,10), and (2,5,8) by value not index.
Please see the equations below:
7-4=4-1=3=d
10-7=7-4=3=d
8-5=5-2=3=d
Recall that a beautiful triplet satisfies the following equivalence relation: arr[j]-arr[i]=arr[k]-arr[j]=d where i<j<k.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class BeautifulTriplets {

    // Complete the beautifulTriplets function below.
    static int beautifulTriplets(int d, int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int num : arr){
            map.merge(num,1,(oldValue,newValue) -> oldValue + newValue);
        }
        int count = 0;
        for(int num : arr){
            if(map.containsKey(num - d) && map.containsKey(num - 2*d)){
                count += map.get(num) * map.get(num - d) * map.get(num - 2*d);
            }
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nd = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nd[0]);

        int d = Integer.parseInt(nd[1]);

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int result = beautifulTriplets(d, arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

