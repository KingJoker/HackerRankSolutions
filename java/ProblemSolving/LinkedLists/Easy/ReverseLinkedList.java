/*
You’re given the pointer to the head node of a linked list.
Change the next pointers of the nodes so that their order is reversed.
The head pointer given may be null meaning that the initial list is empty.
*Input Format
You have to complete the SinglyLinkedListNode reverse(SinglyLinkedListNode head) method which
takes one argument - the head of the linked list. You should NOT read any input from stdin/console.
The input is handled by the code in the editor and the format is as follows:
The first line contains an integer t, denoting the number of test cases.
Each test case is of the following format:
The first line contains an integer n, denoting the number of elements in the linked list.
The next n lines contain an integer each, denoting the elements of the linked list.
*Constraints
1<=t<=10
1<=n<=1000
1<=list[i]<=1000, where list[i] is the ith element in the list.
*Output Format
Change the next pointers of the nodes that their order is reversed and return the head of the reversed linked list.
Do NOT print anything to stdout/console.
The output is handled by the code in the editor. The output format is as follows:
For each test case, print in a new line the elements of the linked list after reversing it, separated by spaces.
*Sample Input
1
5
1
2
3
4
5
*Sample Output
5 4 3 2 1
*Explanation
The initial linked list is: 1 -> 2 -> 3 -> 4 -> 5 -> NULL
The reversed linked list is: 5 -> 4 -> 3 -> 2 -> 1 -> NULL
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ReverseLinkedList {

    private static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

    private static class SinglyLinkedList {
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

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
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
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode reverse(SinglyLinkedListNode head) {
        if(head == null){
            return null;
        }
        Stack<SinglyLinkedListNode> stack = new Stack<>();
        SinglyLinkedListNode current = head;
        while(current != null){
            stack.push(current);
            current = current.next;
        }
        SinglyLinkedListNode newHead = stack.pop();
        current = newHead;
        while(stack.size() > 0){
            SinglyLinkedListNode next = stack.pop();
            current.next = next;
            current = next;
        }
        current.next = null;
        return newHead;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

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

            SinglyLinkedListNode llist1 = reverse(llist.head);

            printSinglyLinkedList(llist1, " ", bufferedWriter);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

