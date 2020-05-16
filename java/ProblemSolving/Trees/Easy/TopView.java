/*
You are given a pointer to the root of a binary tree. Print the top view of the binary tree.
Top view means when you look the tree from the top the nodes, what you will see will be called the top view of the tree.
See the example below.
You only have to complete the function.
For example :
   1
    \
     2
      \
       5
      /  \
     3    6
      \
       4
Top View : 1 -> 2 -> 5 -> 6
*Input Format
You are given a function,
void topView(node * root) {

}
*Constraints
1<=Nodes in the tree<=500
*Output Format
Print the values on a single line separated by space.
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
1 2 5 6
*Explanation
   1
    \
     2
      \
       5
      /  \
     3    6
      \
       4
From the top only nodes 1,2,5,6 will be visible.
 */

package Trees.Easy;

import java.util.*;
import java.io.*;



class TopView {
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
    public static void topView(Node root) {
        TreeMap<Integer,Integer> top = new TreeMap<>();
        LinkedList<Entry> queue = new LinkedList<>();
        queue.offer(new Entry(root,1));
        while(queue.size() > 0){
            Entry current = queue.poll();
            Node currentNode = current.node;
            top.merge(current.index,currentNode.data, (oldValue, newValue) -> oldValue);
            if(currentNode.left != null) {
                queue.offer(new Entry(currentNode.left,current.index - 1));
            }
            if(currentNode.right != null) {
                queue.offer(new Entry(currentNode.right,current.index + 1));
            }
        }
        for(int key : top.keySet()){
            System.out.print(top.get(key) + " ");
        }
    }
    private static class Entry{
        int index;
        Node node;
        public Entry(Node node, int index){
            this.node = node;
            this.index = index;
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
        topView(root);
    }
}
