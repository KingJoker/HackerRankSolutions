/*
You are given the pointer to the head node of a linked list and you need to print all its elements in reverse order
from tail to head, one element per line.
The head pointer may be null meaning that the list is empty - in that case, do not print anything!
*Input Format
You have to complete the void reversePrint(SinglyLinkedListNode* head) method which takes one argument - the head of the linked list.
You should NOT read any input from stdin/console.
The first line of input contains t, the number of test cases.
The input of each test case is as follows:
The first line contains an integer n, denoting the number of elements in the list.
The next n lines contain one element each, denoting the elements of the linked list in the order.
*Constraints
1<=n<=1000
1<=list[i], where list[i] is the ith element in the list.
*Output Format
Complete the reversePrint function in the editor below and print the elements of the linked list in the reverse order, each in a new line.
*Sample Input
3
5
16
12
4
2
5
3
7
3
9
5
5
1
18
3
13
*Sample Output
5
2
4
12
16
9
3
7
13
3
18
1
5
Explanation
There are three test cases.
The first linked list has  elements: 16 -> 12 -> 4 -> 2 -> 5. Printing this in reverse order will produce: 5 -> 2 -> 4 -> 12 -> 16.
The second linked list has  elements: 7 -> 3 -> 9. Printing this in reverse order will produce: 9 -> 3 -> 7.
The third linked list has  elements: 5 -> 1 -> 18 -> 3 -> 13. Printing this in reverse order will produce: 13 -> 3 -> 18 -> 1 -> 5.
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PrintInReverse {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    static class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep) {
        while (node != null) {
            System.out.print(node.data);

            node = node.next;

            if (node != null) {
                System.out.print(sep);
            }
        }
    }

    // Complete the reversePrint function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static void reversePrint(SinglyLinkedListNode head) {
        Stack<Integer> stack = new Stack<>();
        SinglyLinkedListNode current = head;
        while(current!=null){
            stack.push(current.data);
            current=current.next;
        }
        while(stack.size() != 0){
            System.out.println(stack.pop());
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int tests = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int testsItr = 0; testsItr < tests; testsItr++) {
            SinglyLinkedList llist = new SinglyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            reversePrint(llist.head);
        }

        scanner.close();
    }
}

