/*
Given pointers to the head nodes of 2 linked lists that merge together at some point, find the Node where the two lists merge.
It is guaranteed that the two head Nodes will be different, and neither will be NULL.
In the diagram below, the two lists converge at Node x:
[List #1] a--->b--->c
                     \
                      x--->y--->z--->NULL
                     /
     [List #2] p--->q
Complete the int findMergeNode(SinglyLinkedListNode* head1, SinglyLinkedListNode* head2) method so that it finds and returns the data value of the Node where the two lists merge.
*Input Format
Do not read any input from stdin/console.
The findMergeNode(SinglyLinkedListNode,SinglyLinkedListNode) method has two parameters, head1 and head2, which are the non-null head Nodes of two separate linked lists that are guaranteed to converge.
*Constraints
The lists will merge.
head1,head2 != null.
head1 != head2.
*Output Format
Do not write any output to stdout/console.
Each Node has a data field containing an integer. Return the integer data for the Node where the two lists merge.
*Sample Input
The diagrams below are graphical representations of the lists that input Nodes headA and headB are connected to.
Recall that this is a method-only challenge; the method only has initial visibility to those 2 Nodes and must explore the rest of the Nodes using some algorithm of your own design.
*Test Case 0
 1
  \
   2--->3--->NULL
  /
 1
*Test Case 1

1--->2
      \
       3--->Null
      /
     1
*Sample Output
2
3
*Explanation
Test Case 0: As demonstrated in the diagram above, the merge Node's data field contains the integer 2.
Test Case 1: As demonstrated in the diagram above, the merge Node's data field contains the integer 3.
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MergePoint {

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

    public static void printSinglyLinkedList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
        while (node != null) {
            bufferedWriter.write(String.valueOf(node.data));

            node = node.next;

            if (node != null) {
                bufferedWriter.write(sep);
            }
        }
    }

    // Complete the findMergeNode function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        ArrayList<SinglyLinkedListNode> list1 = new ArrayList<>();
        HashSet<SinglyLinkedListNode> list2 = new HashSet<>();
        while(head1 != null){
            list1.add(head1);
            head1 = head1.next;
        }
        while(head2 != null){
            list2.add(head2);
            head2 = head2.next;
        }
        int merge = 0;
        for(SinglyLinkedListNode node : list1){
            if(list2.contains(node)){
                merge = node.data;
                break;
            }
        }
        return merge;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int tests = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int testsItr = 0; testsItr < tests; testsItr++) {
            int index = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            SinglyLinkedList llist1 = new SinglyLinkedList();

            int llist1Count = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llist1Count; i++) {
                int llist1Item = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist1.insertNode(llist1Item);
            }

            SinglyLinkedList llist2 = new SinglyLinkedList();

            int llist2Count = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < llist2Count; i++) {
                int llist2Item = scanner.nextInt();
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                llist2.insertNode(llist2Item);
            }

            SinglyLinkedListNode ptr1 = llist1.head;
            SinglyLinkedListNode ptr2 = llist2.head;

            for (int i = 0; i < llist1Count; i++) {
                if (i < index) {
                    ptr1 = ptr1.next;
                }
            }

            for (int i = 0; i < llist2Count; i++) {
                if (i != llist2Count-1) {
                    ptr2 = ptr2.next;
                }
            }

            ptr2.next = ptr1;

            int result = findMergeNode(llist1.head, llist2.head);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

