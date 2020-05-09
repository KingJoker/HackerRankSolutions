/*
Consider an undirected graph where each edge is the same weight. Each of the nodes is labeled consecutively.
You will be given a number of queries. For each query, you will be given a list of edges describing an undirected graph.
After you create a representation of the graph, you must determine and report the shortest
distance to each of the other nodes from a given starting position using the breadth-first search algorithm (BFS).
Distances are to be reported in node number order, ascending. If a node is unreachable, print -1 for that node.
Each of the edges weighs 6 units of distance.
For example, given a graph with 5 nodes and 3 edges, [1,2],[1,3],[3,4], a visual representation is:
    1     5
   / \
  2   3
      |
      4
The start node for the example is node 1. Outputs are calculated for distances to nodes 2 through 5: [6,6,12,-1].
Each edge is 6 units, and the unreachable node 5 has the required return distance of -1.
*Function Description
Complete the bfs function in the editor below.
It must return an array of integers representing distances from the start node to each other node in node ascending order.
If a node is unreachable, its distance is -1.
*bfs has the following parameter(s):
n: the integer number of nodes
m: the integer number of edges
edges: a 2D array of start and end nodes for edges
s: the node to start traversals from
*Input Format
The first line contains an integer q, the number of queries. Each of the following q sets of lines has the following format:
The first line contains two space-separated integers n and m, the number of nodes and edges in the graph.
Each line i of the m subsequent lines contains two space-separated integers, u and v, describing an edge connecting node u to node v.
The last line contains a single integer, s, denoting the index of the starting node.
*Constraints
1<=q<=10
2<=n<=1000
1<=m<=(n*(n-1))/2
1<=u,v,s<=n
*Output Format
For each of the q queries, print a single line of n-1 space-separated integers denoting the shortest distances
to each of the n-1 other nodes from starting position s.
These distances should be listed sequentially by node number (i.e.,1,2,...,n ), but should not include node s.
If some node is unreachable from s, print -1 as the distance to that node.
*Sample Input
2
4 2
1 2
1 3
1
3 1
2 3
2
*Sample Output
6 6 -1
-1 6
Explanation
We perform the following two queries:
1. The given graph can be represented as:
    1   4
   / \
  2   3
where our start node, s, is node 1. The shortest distances from s to the other nodes are one edge to node 2, one edge to node 3,
and an infinite distance to node 4 (which it's not connected to).
We then print node 1's distance to nodes 2, 3, and 4 (respectively) as a single line of space-separated integers: 6, 6, -1.
2. The given graph can be represented as:
    1   2
        |
        3
where our start node, s, is node 2.
There is only one edge here, so node 1 is unreachable from node 2 and node 3 has one edge connecting it to node 2.
We then print node 2's distance to nodes 1 and 3 (respectively) as a single line of space-separated integers: -1 6.
Note: Recall that the actual length of each edge is 6, and we print -1 as the distance to any node that's unreachable from .
 */

package GraphTheory.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BreadthFirstSearchShortestReach {

    private static class Node{
        int value;
        ArrayList<Node> children;
        int distance;
        boolean visited;

        public Node(int value){
            this.value = value;
            children = new ArrayList<>();
            distance = 0;
            visited = false;
        }
    }

    static int[] bfs(int n, int m, int[][] edges, int s) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(-1));
        for(int i = 1; i <= n; i++){
            nodes.add(new Node(i));
        }
        for(int i = 0; i < m; i++){
            Node node1 = nodes.get(edges[i][0]);
            Node node2 = nodes.get(edges[i][1]);
            node1.children.add(node2);
            node2.children.add(node1);
        }
        Node root = nodes.get(s);
        root.visited = true;
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root) ;
        while(queue.size() > 0){
            Node current = queue.poll();
            for(Node child : current.children){
                if(! child.visited){
                    child.distance = current.distance + 1;
                    child.visited = true;
                    queue.offer(child);
                }
            }
        }
        int[] ret = new int[n-1];
        int iRet = 0;
        for(int i = 1; i < nodes.size(); i++){
            if(i != s){
                Node current = nodes.get(i);
                int distance = 6 * current.distance;
                if(distance == 0){
                    distance = -1;
                }
                ret[iRet++] = distance;
            }
        }
        return ret;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
