/*
You’re given the pointer to the head node of a linked list and a specific position.
Counting backwards from the tail node of the linked list, get the value of the node at the given position.
A position of 0 corresponds to the tail, 1 corresponds to the node before the tail and so on.
*Input Format
You have to complete the int getNode(SinglyLinkedListNode* head, int positionFromTail) method which
takes two arguments - the head of the linked list and the position of the node from the tail.
positionFromTail will be at least 0 and less than the number of nodes in the list.
You should NOT read any input from stdin/console.
The first line will contain an integer t, the number of test cases.
Each test case has the following format:
The first line contains an integer n, the number of elements in the linked list.
The next n lines contains, an element each denoting the element of the linked list.
The last line contains an integer positionFromTail denoting the position from the tail, whose value needs to be found out and returned.
*Constraints
1<=t<=10
1<=n<=1000
1<=list[i]<=1000, where list[i] is the ith element of the linked list.
0<=positionFromTail<n
*Output Format
Find the node at the given position counting backwards from the tail.
Then return the data contained in this node. Do NOT print anything to stdout/console.
The code in the editor handles output.
For each test case, print the value of the node, each in a new line.
*Sample Input
2
1
1
0
3
3
2
1
2
*Sample Output
1
3
*Explanation
In first case, there is one element in linked list with value 1. Hence, last element is 1.
In second case, there are 3 elements with values 3, 2 and 1 (3 -> 2 -> 1). Hence, element with position of 2 from tail is 3.
 */

package LinkedLists.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class GetNodeValue {

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

    // Complete the getNode function below.

    /*
     * For your reference:
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    static int getNode(SinglyLinkedListNode head, int positionFromTail) {
        Stack<Integer> stack = new Stack<>();
        SinglyLinkedListNode current = head;
        while(current != null){
            stack.push(current.data);
            current = current.next;
        }
        for(int i = 0; i < positionFromTail; i++){
            stack.pop();
        }
        return stack.peek();
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

            int position = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int result = getNode(llist.head, position);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

