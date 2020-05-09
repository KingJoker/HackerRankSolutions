/*
The median of a set of integers is the midpoint value of the data set for which an equal number of
integers are less than and greater than the value.
To find the median, you must first sort your set of integers in non-decreasing order, then:
If your set contains an odd number of elements, the median is the middle element of the sorted sample.
In the sorted set {1,2,3}, 2 is the median.
If your set contains an even number of elements, the median is the average of the two middle elements of the sorted sample.
In the sorted set {1,2,3,4}, (2+3)/2=2.5 is the median.
Given an input stream of n integers, you must perform the following task for each ith integer:
1. Add the ith integer to a running list of integers.
2. Find the median of the updated list (i.e., for the first element through the ith element).
3. Print the list's updated median on a new line. The printed value must be a double-precision
number scaled to 1 decimal place (i.e., 12.3 format).
*Input Format
The first line contains a single integer, n, denoting the number of integers in the data stream.
Each line i of the n subsequent lines contains an integer, a[i], to be added to your list.
*Constraints
1<=n<=10^5
0<=a[i]<=10^5
*Output Format
After each new integer is added to the list, print the list's updated median on a new line
as a single double-precision number scaled to 1 decimal place (i.e., 12.3 format).
*Sample Input
6
12
4
5
3
8
7
*Sample Output
12.0
8.0
5.0
4.5
5.0
6.0
*Explanation
There are n=6 integers, so we must print the new median on a new line as each integer is added to the list:
1. list={12},median=12.0
2. list={12,4},median=(12+4)/2)=8.0
3. list={12,4,5},median=5.0
4. list={12,4,5,3},median=(4+5)/2=4.5
5. list={12,4,5,3,8},median=5.0
6. list={12,4,5,3,8,7},median=(5+7)/2=6.0
 */

package Heap.Hard;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class FindRunningMedian {
    //Heap
    static double[] runningMedian(int[] a) {
        PriorityQueue<Integer> upperHeap = new PriorityQueue<>();
        PriorityQueue<Integer> lowerHeap = new PriorityQueue<>(Comparator.reverseOrder());
        double[] ret = new double[a.length];
        double previousMedian = 0;
        for(int i = 0; i < a.length; i++){
            if(a[i] <= previousMedian){
                lowerHeap.offer(a[i]);
            }
            else{
                upperHeap.offer(a[i]);
            }
            while(lowerHeap.size() - upperHeap.size() > 1){
                upperHeap.offer(lowerHeap.poll());
            }
            while(upperHeap.size() - lowerHeap.size() > 1){
                lowerHeap.offer(upperHeap.poll());
            }
            if(lowerHeap.size() == upperHeap.size()){
                previousMedian = (lowerHeap.peek() + upperHeap.peek())/2.0;
                ret[i] = previousMedian;
            }
            else if(lowerHeap.size() > upperHeap.size()){
                previousMedian = lowerHeap.peek();
                ret[i] = previousMedian;
            }
            else{
                previousMedian = upperHeap.peek();
                ret[i] = previousMedian;
            }
        }
        return ret;
    }
    //Sorted List
    static double[] runningMedian2(int[] a) {
        double[] ret = new double[a.length];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < a.length; i++){
            int insertIndex = Collections.binarySearch(list,a[i]);
            if(insertIndex < 0){
                insertIndex = -1 * (insertIndex + 1);
            }
            list.add(insertIndex,a[i]);
            if(list.size() % 2 == 0){
                ret[i] = (list.get(list.size()/2) + list.get(list.size()/2 - 1))/2.0;
            }
            else{
                ret[i] = list.get(list.size()/2);
            }
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int aCount = Integer.parseInt(scanner.nextLine().trim());

        int[] a = new int[aCount];

        for (int aItr = 0; aItr < aCount; aItr++) {
            int aItem = Integer.parseInt(scanner.nextLine().trim());
            a[aItr] = aItem;
        }

        double[] result = runningMedian(a);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.format("%.1f",result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

