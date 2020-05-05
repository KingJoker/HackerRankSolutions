/*
A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
For example, in the following graph there is a cycle formed when node 5 points back to node 3.
        4
       / \
      ▲   ▼
1->2->3<--5
*Function Description
Complete the function has_cycle in the editor below. It must return a boolean true if the graph contains a cycle, or false.
*has_cycle has the following parameter(s):
head: a pointer to a Node object that points to the head of a linked list.
Note: If the list is empty, head will be null.
*Input Format
There is no input for this challenge. A random linked list is generated at runtime and passed to your function.
*Constraints
0<=list size <= 100
*Output Format
If the list contains a cycle, your function must return true. If the list does not contain a cycle, it must return false.
The binary integer corresponding to the boolean value returned by your function is printed to stdout by our hidden code checker.
*Sample Input
The following linked lists are passed as arguments to your function:
1->null
1->2<->3
*Sample Output
0
1
*Explanation
The first list has no cycle, so we return false and the hidden code checker prints 0 to stdout.
The second list has a cycle, so we return true and the hidden code checker prints 1 to stdout.
 */

package LinkedLists.Easy;

import java.util.HashSet;

public class DetectCylcle {

    private static class Node{
        int data;
        Node next;
    }

    /*A Node is defined as:
    class Node {
        int data;
        Node next;
    }
    */
    boolean hasCycle(Node head) {
        HashSet<Node> previous = new HashSet<>();
        while(head != null){
            if(previous.contains(head)){
                return true;
            }
            previous.add(head);
            head = head.next;
        }
        return false;
    }

}
