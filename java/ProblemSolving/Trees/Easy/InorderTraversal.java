/*
Complete the inOrder function in your editor below, which has 1 parameter: a pointer to the root of a binary tree.
It must print the values in the tree's inorder traversal as a single line of space-separated values.
*Input Format
Our hidden tester code passes the root node of a binary tree to your inOrder function.
*Constraints
1<=Nodes in the tree<=500
*Output Format
Print the tree's inorder traversal as a single line of space-separated values.
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
1 2 3 4 5 6
 */

package Trees.Easy;

import java.util.*;
import java.io.*;



class InorderTraversal{
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

    public static void inOrder(Node root) {
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
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
        inOrder(root);
    }
}
