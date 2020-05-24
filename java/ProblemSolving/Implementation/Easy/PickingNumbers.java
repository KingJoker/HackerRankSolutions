/*
Given an array of integers, find and print the maximum number of integers you can select from the
array such that the absolute difference between any two of the chosen integers is less than or equal to 1.
For example, if your array is a=[1,1,2,2,4,4,5,5,5], you can create two subarrays meeting the criterion: [1,1,2,2] and [4,4,5,5,5].
The maximum length subarray has 5 elements.
*Function Description
Complete the pickingNumbers function in the editor below.
It should return an integer that represents the length of the longest array that can be created.
*pickingNumbers has the following parameter(s):
a: an array of integers
*Input Format
The first line contains a single integer n, the size of the array a.
The second line contains n space-separated integers a[i].
*Constraints
2<=n<=100
0<a[i]<100
The answer will be >=2.
*Output Format
A single integer denoting the maximum number of integers you can choose from the
array such that the absolute difference between any two of the chosen integers is <=1.
*Sample Input 0
6
4 6 5 3 3 1
*Sample Output 0
3
*Explanation 0
We choose the following multiset of integers from the array: {4,3,3}.
Each pair in the multiset has an absolute difference <=1 (i.e., |4-3| =1 and |3-3| =0),
so we print the number of chosen integers, 3, as our answer.
*Sample Input 1
6
1 2 2 3 1 2
*Sample Output 1
5
*Explanation 1
We choose the following multiset of integers from the array: {1,2,2,1,2}.
Each pair in the multiset has an absolute difference <=1 (i.e., |1-2| =1, |1-1| = 0, and |2-2| =0),
so we print the number of chosen integers, 5, as our answer.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class PickingNumbers {

    private static class Result {

        /*
         * Complete the 'pickingNumbers' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY a as parameter.
         */

        public static int pickingNumbers(List<Integer> a) {
            HashMap<Integer,Long> map = a.stream().collect(Collectors.groupingBy(Function.identity(),HashMap::new, Collectors.counting()));
            long max = 0;
            for(int num : map.keySet()){
                long count1 = map.get(num);
                long count2 = map.getOrDefault(num-1,0L);
                long count3 = map.getOrDefault(num+1, 0L);
                max = Math.max(max,count1+count2);
                max = Math.max(max,count1+count3);
            }
            return (int)max;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = Result.pickingNumbers(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

