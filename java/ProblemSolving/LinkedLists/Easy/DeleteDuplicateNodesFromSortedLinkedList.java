/*
You're given the pointer to the head node of a sorted linked list, where the data in the nodes is in ascending order.
Delete as few nodes as possible so that the list does not contain any value more than once.
The given head pointer may be null indicating that the list is empty.
*Input Format
You have to complete the SinglyLinkedListNode* removeDuplicates(SinglyLinkedListNode* head) method which
takes one argument - the head of the sorted linked list. You should NOT read any input from stdin/console.
The input is handled by the code in the editor and the format is as follows:
The first line contains an integer t, denoting the number of test cases. The format for each test case is as follows:
The first line contains an integer n, denoting the number of elements in the linked list.
The next n lines contain an integer each, denoting the elements of the linked list.
*Constraints
1<=t<=10
1<=n<=1000
1<=list[i]<=1000
*Output Format
Delete as few nodes as possible to ensure that no two nodes have the same data.
Adjust the next pointers to ensure that the remaining nodes form a single sorted linked list.
Then return the head of the sorted updated linked list. Do NOT print anything to stdout/console.
The output is handled by the code in the editor and the format is as follows:
For each test case, print in a new line, the data of the linked list after removing the duplicates separated by space.
*Sample Input
1
5
1
2
2
3
4
*Sample Output
1 2 3 4
*Explanation
The initial linked list is: 1 -> 2 -> 2 -> 3 -> 4 -> NULL
The final linked list is: 1 -> 2 -> 3 -> 4 -> NULL
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class DeleteDuplicateNodesFromSortedLinkedList {

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

    // Complete the removeDuplicates function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;
        while(current != null){
            while(current.next != null && current.data == current.next.data){
                current.next = current.next.next;
            }
            current = current.next;
        }
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
            SinglyLinkedList llist = new SinglyLinkedList();

            int llistCount = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llistCount; i++) {
                int llistItem = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist.insertNode(llistItem);
            }

            SinglyLinkedListNode llist1 = removeDuplicates(llist.head);

            printSinglyLinkedList(llist1, " ", bufferedWriter);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

