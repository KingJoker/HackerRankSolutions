/*
You are given a pointer to the root of a binary search tree and values to be inserted into the tree.
Insert the values into their appropriate position in the binary search tree and return the root of the updated binary tree.
You just have to complete the function.
*Input Format
You are given a function,
Node * insert (Node * root ,int data) {

}
*Constraints
No. of nodes in the tree <= 500
*Output Format
Return the root of the binary search tree after inserting the value into the tree.
*Sample Input
        4
       / \
      2   7
     / \
    1   3
The value to be inserted is 6.
*Sample Output
         4
       /   \
      2     7
     / \   /
    1   3 6
 */

package Trees.Easy;

import java.util.*;
import java.io.*;



class Insertion {
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

    public static void preOrder( Node root ) {

        if( root == null)
            return;

        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);

    }

     /* Node is defined as :
     class Node
        int data;
        Node left;
        Node right;

        */

    public static Node insert(Node root,int data) {
        Node newNode = new Node(data);
        if(root == null){
            return newNode;
        }
        if(data < root.data){
            if(root.left == null){
                root.left = newNode;
            }
            else{
                insert(root.left,data);
            }
        }
        else if(data > root.data){
            if(root.right == null){
                root.right = newNode;
            }
            else{
                insert(root.right,data);
            }
        }
        return root;
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
        preOrder(root);
    }
}
