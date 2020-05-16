/*
You’re given the pointer to the head nodes of two linked lists.
Compare the data in the nodes of the linked lists to check if they are equal.
The lists are equal only if they have the same number of nodes and corresponding nodes contain the same data.
Either head pointer given may be null meaning that the corresponding list is empty.
*Input Format
You have to complete the int CompareLists(Node* headA, Node* headB) method which takes
two arguments - the heads of the two linked lists to compare. You should NOT read any input from stdin/console.
The input is handled by the code in the editor and the format is as follows:
The first line contains t, the number of test cases. The format for each test case is as follows:
The first line contains an integer n, denoting the number of elements in the first linked list.
The next n lines contain an integer each, denoting the elements of the first linked list.
The next line contains an integer m, denoting the number of elements in the second linked list.
The next m lines contain an integer each, denoting the elements of the second linked list.
*Constraints
1<=t<=10
1<=n<=1000
1<=list[i]<=1000, where list[i] is the ith element in the list.
*Output Format
Compare the two linked lists and return 1 if the lists are equal.
Otherwise, return 0. Do NOT print anything to stdout/console.
The output is handled by the code in the editor and it is as follows:
For each test case, in a new line, print 1 if the two lists are equal, else print 0.
*Sample Input
2
2
1
2
1
1
2
1
2
2
1
2
*Sample Output
0
1
*Explanation
In the first case, linked lists are: 1 -> 2 -> NULL and 1 -> NULL
In the second case, linked lists are: 1 -> 2 -> NULL and 1 -> 2 -> NULL
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class CompareTwoLinkedLists {

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

    // Complete the compareLists function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if(head1 == null || head2 == null){
            if(head1 != null){
                return false;
            }
            else if(head2 != null){
                return false;
            }
            else{
                return true;
            }
        }
        if(head1.data != head2.data){
            return false;
        }
        return compareLists(head1.next, head2.next);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int tests = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int testsItr = 0; testsItr < tests; testsItr++) {
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

            boolean result = compareLists(llist1.head, llist2.head);

            bufferedWriter.write(String.valueOf(result ? 1 : 0));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

