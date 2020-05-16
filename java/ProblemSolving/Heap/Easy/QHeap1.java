/*
This question is designed to help you get a better understanding of basic heap operations.
You will be given queries of  types:
"1 v" - Add an element v to the heap.
"2 v" - Delete the element v from the heap.
"3" - Print the minimum of all the elements in the heap.
NOTE: It is guaranteed that the element to be deleted will be there in the heap.
Also, at any instant, only distinct elements will be in the heap.
*Input Format
The first line contains the number of queries, Q.
Each of the next Q lines contains a single query of any one of the 3 above mentioned types.
*Constraints
1<=Q<=10^5
-10^9<=v<=10^9
*Output Format
For each query of type 3, print the minimum value on a single line.
*Sample Input
5
1 4
1 9
3
2 4
3
*Sample Output
4
9
*Explanation
After the first 2 queries, the heap contains {4,9}.
Printing the minimum gives 4 as the output.
Then, the 4th query deletes  from the heap, and the 5th query gives 9 as the output.
 */

package Heap.Easy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class QHeap1 {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int q = input.nextInt();
        for(int i = 0; i < q; i++){
            int type = input.nextInt();
            switch(type){
                case 1:
                    heap.offer(input.nextInt());
                    break;
                case 2:
                    heap.remove(input.nextInt());
                    break;
                case 3:
                    System.out.println(heap.peek());
                    break;
            }
        }
    }

}
