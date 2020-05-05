/*
Given a reference to the head of a doubly-linked list and an integer, data, create a new DoublyLinkedListNode object having data value data and insert it into a sorted linked list while maintaining the sort.
*Function Description
Complete the sortedInsert function in the editor below. It must return a reference to the head of your modified DoublyLinkedList.
*sortedInsert has two parameters:
head: A reference to the head of a doubly-linked list of DoublyLinkedListNode objects.
data: An integer denoting the value of the data field for the DoublyLinkedListNode you must insert into the list.
Note: Recall that an empty list (i.e., where head=null) and a list with one element are sorted lists.
*Input Format
The first line contains an integer t, the number of test cases.
Each of the test case is in the following format:
The first line contains an integer n, the number of elements in the linked list.
Each of the next n lines contains an integer, the data for each node of the linked list.
The last line contains an integer data which needs to be inserted into the sorted doubly-linked list.
*Constraints
1<=t<=10
1<=n<=1000
1<=DoublyLinkedListNode.data<=1000
*Output Format
Do not print anything to stdout. Your method must return a reference to the head of the same list that was passed to it as a parameter.
The output is handled by the code in the editor and is as follows:
For each test case, print the elements of the sorted doubly-linked list separated by spaces on a new line.
*Sample Input
1
4
1
3
4
10
5
*Sample Output
1 3 4 5 10
*Explanation
The initial doubly linked list is: 1<->3<->4<->10->NULL.
The doubly linked list after insertion is: 1<->3<->4<->5<->10->NULL.
 */
package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class InsertIntoSortedDoublyLinkedList {

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

    // Complete the sortedInsert function below.

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
    static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {
        DoublyLinkedListNode current = head;
        DoublyLinkedListNode previous = null;
        DoublyLinkedListNode insert = new DoublyLinkedListNode(data);
        while(current != null && current.data < data){
            previous = current;
            current = current.next;
        }
        if(previous == null){
            head = insert;
        }
        else{
            previous.next = insert;
        }
        insert.prev = previous;
        insert.next = current;
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

            int data = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            DoublyLinkedListNode llist1 = sortedInsert(llist.head, data);

            printDoublyLinkedList(llist1, " ", bufferedWriter);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

