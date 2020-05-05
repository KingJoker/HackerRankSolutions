/*
In this challenge, there is a connected undirected graph where each of the nodes is a color.
Given a color, find the shortest path connecting any two nodes of that color.
Each edge has a weight of 1. If there is not a pair or if the color is not found, print -1.
For example, given graph_nodes=5, and 4 edges g_from=[1,2,2,3] and g_to=[2,3,4,5] and colors for each node are arr=[1,2,3,1,3] we can draw the following graph:
          3/3 - 5/3
         /
1/1 - 2/2
         \
          4/1
Each of the nodes is labeled [node]/[color] and is colored appropriately.
If we want the shortest path between color 3, blue, we see there is a direct path between nodes 3 and 5.
For green, color 1, we see the path length 2 from 1->2->4.
There is no pair for node 4 having color 2, red.
*Function Description
Complete the findShortest function in the editor below.
It should return an integer representing the length of the shortest path between two nodes of the same color, or -1 if it is not possible.
*findShortest has the following parameter(s):
g_nodes: an integer, the number of nodes
g_from: an array of integers, the start nodes for each edge
g_to: an array of integers, the end nodes for each edge
ids: an array of integers, the color id per node
val: an integer, the id of the color to match
*Input Format
The first line contains two space-separated integers n and m, the number of nodes and edges in the graph.
Each of the next m lines contains two space-separated integers g_from[i] and g_to[i], the nodes connected by an edge.
The next line contains n space-seperated integers, ids[i], representing the color id of each node from 1 to n.
The last line contains the id of the color to analyze.
Note: The nodes are indexed from 1 to n.
*Constraints
1<=n<=10^6
1<=m<=10^6
1<=ids[i]<=10^8
*Output Format
Print the single integer representing the smallest path length or -1.
*Sample Input 0
4 3
1 2
1 3
4 2
1 2 1 1
1
*Sample Output 0
1
*Explanation 0
    2/2 - 4/1
   /
1/1
   \
    3/1
In the above image the distance between the closest nodes having color label 1 is 1.
*Sample Input 1
4 3
1 2
1 3
4 2
1 2 3 4
2
*Sample Output 1
-1
*Explanation 1
    2/2 - 4/4
   /
1/1
   \
    3/3
*Sample Input 2
5 4
1 2
1 3
2 4
3 5
1 2 3 3 2
2
*Sample Output 2
3
*Explanation 2
    1/1
   /   \
2/2     3/3
 |       |
4/3     5/2
 */

package Graphs.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FindNearestClone {

    private static class Node{
        public ArrayList<Node> children;
        public long color;
        public int index;

        public Node(int index){
            this.index = index;
            children = new ArrayList<>();
        }
    }


    // Complete the findShortest function below.

    /*
     * For the unweighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] to <name>To[i].
     *
     */
    static int findShortest(int graphNodes, int[] graphFrom, int[] graphTo, long[] ids, int val) {
        //HashMap<Integer,Node> nodes = new HashMap<>(graphNodes);
        Node[] nodes = new Node[graphNodes + 1];
        Node coloredNode = null;
        for(int i = 1; i <= graphNodes; i++){
            Node newNode = new Node(i);
            newNode.color = ids[i-1];
            nodes[i] = newNode;
            if(ids[i-1] == val){
                coloredNode = newNode;
            }
        }
        for(int i = 0; i < graphFrom.length; i++){
            Node from = nodes[graphFrom[i]];
            Node to = nodes[graphTo[i]];
            from.children.add(to);
            to.children.add(from);
        }
        if(coloredNode == null){
            return -1;
        }
        int min = findDistance(coloredNode, val);
        return min == Integer.MAX_VALUE? -1 : min;
    }

    static int findDistance(Node start, int color){
        int min = Integer.MAX_VALUE;
        for(Node child : start.children){
            Object[] paths = findPaths(child, start, color, 1);
            min = Math.min(min, (Integer)paths[0]);
        }
        return min;
    }



    static Object[] findPaths(Node start, Node from, int color, int distance){
        ArrayList<Node> childrenToCheck = start.children;
        childrenToCheck.remove(from);
        int min = Integer.MAX_VALUE;
        if(childrenToCheck.size() == 0){
            if(start.color == color){
                min = distance;
            }
            return new Object[]{min, new ArrayList<>()};
        }
        ArrayList<Integer> pathDepths = new ArrayList<>();
        int newDistance = start.color == color ? 0 : distance;
        for(Node check : childrenToCheck){
            Object[] retValues = findPaths(check,start,color,newDistance+1);
            min = Math.min(min,(Integer)retValues[0]);
            for(int depth : (ArrayList<Integer>)retValues[1]){
                int index = Collections.binarySearch(pathDepths, depth);
                if(index < 0){
                    index = -1 * (index + 1);
                }
                pathDepths.add(index, depth);
            }
        }
        if(start.color == color){
            min = Math.min(min, distance);
            ArrayList<Integer> ret = new ArrayList<>();
            ret.add(distance);
            return new Object[]{min, ret};
        }
        else{
            for(int i = 0; i < pathDepths.size() - 1; i++){
                int tempDistance = pathDepths.get(i + 1) + pathDepths.get(i);
                min = Math.min(min, tempDistance);
            }
        }
        return new Object[]{min, pathDepths};
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] graphNodesEdges = scanner.nextLine().split(" ");
        int graphNodes = Integer.parseInt(graphNodesEdges[0].trim());
        int graphEdges = Integer.parseInt(graphNodesEdges[1].trim());

        int[] graphFrom = new int[graphEdges];
        int[] graphTo = new int[graphEdges];

        for (int i = 0; i < graphEdges; i++) {
            String[] graphFromTo = scanner.nextLine().split(" ");
            graphFrom[i] = Integer.parseInt(graphFromTo[0].trim());
            graphTo[i] = Integer.parseInt(graphFromTo[1].trim());
        }

        long[] ids = new long[graphNodes];

        String[] idsItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < graphNodes; i++) {
            long idsItem = Long.parseLong(idsItems[i]);
            ids[i] = idsItem;
        }

        int val = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int ans = findShortest(graphNodes, graphFrom, graphTo, ids, val);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

