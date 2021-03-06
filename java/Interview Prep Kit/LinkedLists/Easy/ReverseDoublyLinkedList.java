/*
You’re given the pointer to the head node of a doubly linked list.
Reverse the order of the nodes in the list.
The head node might be NULL to indicate that the list is empty.
Change the next and prev pointers of all the nodes so that the direction of the list is reversed.
Return a reference to the head node of the reversed list.
*Function Description
Complete the reverse function in the editor below. It should return a reference to the head of your reversed list.
*reverse has the following parameter(s):
head: a reference to the head of a DoublyLinkedList
*Input Format
The first line contains an integer t, the number of test cases.
Each test case is of the following format:
The first line contains an integer n, the number of elements in the linked list.
The next n lines contain an integer each denoting an element of the linked list.
*Constraints
1<=t<=10
0<=n<=1000
0<=DoublyLinkedListNode.data<=1000
*Output Format
Return a reference to the head of your reversed list.
The provided code will print the reverse array as a one line of space-separated integers for each test case.
*Sample Input
1
4
1
2
3
4
*Sample Output
4 3 2 1
*Explanation
The initial doubly linked list is: 1<->2<->3<->4->NULL
The reversed doubly linked list is: 4<->3<->2<->1->NULL
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ReverseDoublyLinkedList {

    static class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    static class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
        }
    }

    public static void printDoublyLinkedList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the reverse function below.

    /*
     * For your reference:
     *
     * DoublyLinkedListNode {
     *     int data;
     *     DoublyLinkedListNode next;
     *     DoublyLinkedListNode prev;
     * }
     *
     */
    static DoublyLinkedListNode reverse(DoublyLinkedListNode head) {
        Stack<DoublyLinkedListNode> stack = new Stack<>();
        if(head == null){
            return null;
        }
        DoublyLinkedListNode current = head;
        while(current.next != null){
            stack.push(current);
            current = current.next;
        }
        head = current;
        current.prev = null;
        while(stack.size() > 0){
            DoublyLinkedListNode next = stack.pop();
            current.next = next;
            next.prev = current;
            current = next;
        }
        current.next = null;
        return head;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            DoublyLinkedList llist = new DoublyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            DoublyLinkedListNode llist1 = reverse(llist.head);

            printDoublyLinkedList(llist1, " ", bufferedWriter);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
