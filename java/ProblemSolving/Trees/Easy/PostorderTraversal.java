/*
Complete the postOrder function in your editor below, which has1  parameter: a pointer to the root of a binary tree.
It must print the values in the tree's postorder traversal as a single line of space-separated values.
*Input Format
Our hidden tester code passes the root node of a binary tree to your postOrder function.
*Constraints
1<=Nodes in the tree<=500
*Output Format
Print the tree's postorder traversal as a single line of space-separated values.
*Sample Input
     1
      \
       2
        \
         5
        /  \
       3    6
        \
         4
*Sample Output
4 3 6 5 2 1
 */

package Trees.Easy;

import java.util.*;
import java.io.*;



public class PostorderTraversal {

    private static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    /* you only have to complete the function given below.
    Node is defined as

    class Node {
        int data;
        Node left;
        Node right;
    }

    */

    public static void postOrder(Node root) {
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        postOrder(root);
    }
}
