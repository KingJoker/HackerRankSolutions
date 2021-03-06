/*
You have an empty sequence, and you will be given  queries. Each query is one of these three types:
1 x  -Push the element x into the stack.
2    -Delete the element present at the top of the stack.
3    -Print the maximum element in the stack.
*Input Format
The first line of input contains an integer, N. The next N lines each contain an above mentioned query.
(It is guaranteed that each query is valid.)
*Constraints
1<=N<=10^5
1<=x<=10^9
1<=type<=3
*Output Format
For each type 3 query, print the maximum element in the stack on a new line.
*Sample Input
10
1 97
2
1 20
2
1 26
1 20
2
3
1 91
3
*Sample Output
26
91
 */

package Stacks.Easy;

import java.util.Scanner;
import java.util.Stack;

public class MaximumElement {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        int max = Integer.MIN_VALUE;
        int n = input.nextInt();
        for(int i = 0; i < n; i++){
            int queryType = input.nextInt();
            switch(queryType){
                case 1:
                    int value = input.nextInt();
                    if(value > max){
                        max = value;
                    }
                    stack.push(max);
                    break;
                case 2:
                    stack.pop();
                    if(stack.size()>0) {
                        max = stack.peek();
                    }
                    else{
                        max = Integer.MIN_VALUE;
                    }
                    break;
                case 3:
                    System.out.println(max);
            }
        }
    }
}
