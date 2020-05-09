/*
There are 2N values to represent nodes in a graph.
They are divided into two sets G and B. Each set has exactly N values.
Set G is represent by {G[1],G[2],...,G[N]}. G can contain any value between 1 to N (inclusive).
Set B is represented by {B[1],B[2],...,B[N}. B can contain any value between N+1 to 2*N (inclusive).
Same value can be chosen any number of times.
Here (G[1],B[1]),(G[2],B[2]),...,(G[N],B[N]) represents the edges of the graph.
Your task is to print the number of vertices in the smallest and the largest connected components of the graph.
Note Single nodes should not be considered in the answer.
*Input Format
First line contains an integer N.
Each of the next N lines contain two space-separated integers, ith line contains G[i] and B[i].
*Constraints
1<=N<=15000
1<=G[i]<=N
N+1<=B[i]<=2*N
*Output Format
Print two space separated integers, the number of vertices in the smallest and the largest components.
*Sample Input
5
1 6
2 7
3 8
4 9
2 6
*Sample Output
2 4
*Explanation
The number of vertices in the smallest connected component in the graph is 2 i.e. either (3,8) or (4,9).
The number of vertices in the largest connected component in the graph is 4 i.e. 1-2-6-7.
*/

package DisjointSet.Medium;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class ComponentsInGraph {

    private static class Node{
        ArrayList<Node> neighbors;
        int value;

        public Node(int value){
            this.value = value;
            neighbors = new ArrayList<>();
        }
    }

    static int[] componentsInGraph(int[][] gb) {
        ArrayList<Node> nodes = new ArrayList<>();
        nodes.add(new Node(-1));
        for(int i = 1; i <= 2 * gb.length; i++){
            nodes.add(new Node(i));
        }
        for(int i = 0; i < gb.length; i++){
            Node node1 = nodes.get(gb[i][0]);
            Node node2 = nodes.get(gb[i][1]);
            node1.neighbors.add(node2);
            node2.neighbors.add(node1);
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        HashSet<Node> visited = new HashSet<>();
        for(int i = 1; i <= gb.length; i++){
            Node current = nodes.get(i);
            if(! visited.contains(current)) {
                int size = findSize(nodes.get(i), visited);
                if(size != 1){
                    min = Math.min(min,size);
                    max = Math.max(max,size);
                }
            }
        }
        return new int[]{min,max};
    }

    static int findSize(Node node, HashSet<Node> visited){
        int size = 0;
        visited.add(node);
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(queue.size() > 0){
            Node current = queue.poll();
            size++;
            for(Node neighbor : current.neighbors){
                if(!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return size;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = Integer.parseInt(scanner.nextLine().trim());

        int[][] gb = new int[n][2];

        for (int gbRowItr = 0; gbRowItr < n; gbRowItr++) {
            String[] gbRowItems = scanner.nextLine().split(" ");

            for (int gbColumnItr = 0; gbColumnItr < 2; gbColumnItr++) {
                int gbItem = Integer.parseInt(gbRowItems[gbColumnItr].trim());
                gb[gbRowItr][gbColumnItr] = gbItem;
            }
        }

        int[] result = componentsInGraph(gb);

        for (int SPACEItr = 0; SPACEItr < result.length; SPACEItr++) {
            bufferedWriter.write(String.valueOf(result[SPACEItr]));

            if (SPACEItr != result.length - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
