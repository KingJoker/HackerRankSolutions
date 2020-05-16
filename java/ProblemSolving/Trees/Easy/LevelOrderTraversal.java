/*
You are given a pointer to the root of a binary tree. You need to print the level order traversal of this tree.
In level order traversal, we visit the nodes level by level from left to right.
You only have to complete the function. For example:
     1
      \
       2
        \
         5
        /  \
       3    6
        \
         4
For the above tree, the level order traversal is 1 -> 2 -> 5 -> 3 -> 6 -> 4.
*Input Format
You are given a function,
void levelOrder(Node * root) {

}
*Constraints
1<=Nodes in the tree<=500
*Output Format
Print the values in a single line separated by a space.
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
1 2 5 3 6 4
*Explanation
We need to print the nodes level by level. We process each level from left to right.
Level Order Traversal: 1 -> 2 -> 5 -> 3 -> 6 -> 4.
 */

package Trees.Easy;

import java.util.*;
import java.io.*;



class LevelOrderTraversal {
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

    /*

    class Node
        int data;
        Node left;
        Node right;
    */
    public static void levelOrder(Node root) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(queue.size() > 0){
            Node current = queue.poll();
            System.out.print(current.data + " ");
            if(current.left != null){
                queue.offer(current.left);
            }
            if(current.right != null){
                queue.offer(current.right);
            }
        }
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
        levelOrder(root);
    }
}
