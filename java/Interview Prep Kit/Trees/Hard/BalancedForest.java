/*
Greg has a tree of nodes containing integer data.
He wants to insert a node with some non-zero integer value somewhere into the tree.
His goal is to be able to cut two edges and have the values of each of the three new trees sum to the same amount.
This is called a balanced forest. Being frugal, the data value he inserts should be minimal.
Determine the minimal amount that a new node can have to allow creation of a balanced forest.
If it's not possible to create a balanced forest, return -1.
For example, you are given node values c=[15,12,8,14,13] and edges=[[1,2],[1,3],[1,4],[4,5]].
It is the following tree:
         1,15
       /  |   \
   2,12  3,8   4,14
                |
               5,13
The blue node is root, the first number in a node is node number and the second is its value.
Cuts can be made between nodes 1 and 3 and nodes 1 and 4 to have three trees with sums 27, 27 and 8.
Adding a new node w of c[w]=19 to the third tree completes the solution.
*Function Description
Complete the balancedForest function in the editor below.
It must return an integer representing the minimum value of c[w] that can be added to allow creation of a balanced forest,
or -1 if it is not possible.
*balancedForest has the following parameter(s):
c: an array of integers, the data values for each node
edges: an array of 2 element arrays, the node pairs per edge
*Input Format
The first line contains a single integer, q, the number of queries.
Each of the following q sets of lines is as follows:
The first line contains an integer, n, the number of nodes in the tree.
The second line contains n space-separated integers describing the respective
values of c[1],c[2]...,c[n], where each c[i] denotes the value at node i.
Each of the following n-1 lines contains two space-separated integers, x[j] and y[j], describing edge j connecting nodes x[j] and y[j].
*Constraints
1<=q<=5
1<=n<=5*10^4
1<=c[i]<=10^9
Each query forms a valid undirected tree.
*Subtasks
For 30% of the maximum score:
1<=n<=100
1<=c[i]<=100
For 50% of the maximum score:
1<=n<=2000
1<=c[i]<=10^9
*Output Format
For each query, return the minimum value of the integer c[w]. If no such value exists, return -1 instead.
*Sample Input
2
5
1 2 2 1 1
1 2
1 3
3 5
1 4
3
1 3 5
1 3
1 2
*Sample Output
2
-1
*Explanation
We perform the following two queries:
1.The tree initially looks like this:
         1,1
       /  |  \
    2,2  3,2  4,1
          |
         5,1
Greg can add a new node w=6 with c[w]=2 and create a new edge connecting nodes 4 and 6.
Then he cuts the edge connecting nodes 1 and 4 and the edge connecting nodes 1 and 3.
We now have a three-tree balanced forest where each tree has a sum of 3.
         1,1
       /
    2,2  3,2  4,1
          |    |
         5,1  w=6,2
2.In the second query, it's impossible to add a node in such a way that we can split the tree into a three-tree balanced forest so we return -1.
 */

package Trees.Hard;

import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class BalancedForest {

    private static class Node{
        int index;
        int value;
        long totalSum;
        Node parent;
        HashSet<Long> sumList;
        ArrayList<Node> children;

        public Node(int index, int value){
            this.index = index;
            this.value = value;
            children = new ArrayList<>();
        }
    }

    private static class Entry implements Comparable<Entry>{
        long sum;
        Node node;
        Node parent;
        public Entry(long sum, Node node, Node parent){
            this.sum = sum;
            this.node = node;
            this.parent = parent;
        }
        public Entry(long sum){
            this.sum = sum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Entry entry = (Entry) o;
            return sum == entry.sum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(sum);
        }

        @Override
        public int compareTo(Entry o) {
            return ((Long)sum).compareTo(o.sum);
        }
    }

    // Complete the balancedForest function below.
    static long balancedForest(int[] c, int[][] edges) {
        ArrayList<Node> nodes = new ArrayList<>(c.length + 1);
        nodes.add(new Node(-1,-1));
        for(int i = 0; i < c.length; i++){
            nodes.add(i+1,new Node(i+1,c[i]));
        }
        for(int i = 0; i < edges.length; i++){
            Node node1 = nodes.get(edges[i][0]);
            Node node2 = nodes.get(edges[i][1]);
            node1.children.add(node2);
            node2.children.add(node1);
        }
        //Node root = nodes.get(1);
        Node root = findRoot2(nodes);
        split(root,null);
        long total = root.value;

        //long total = findSumsUp(root);
        ArrayList<HashSet<Long>> targetSumLists = new ArrayList<>();
        for(int i = 0; i < root.children.size(); i++){
            targetSumLists.add(new HashSet<>());
        }
        HashMap<Node,Integer> childIndexes = new HashMap<>();
        for(int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            HashSet<Long> childSet = new HashSet<>();
            total += findSumsUp(child, i, childSet, childIndexes);
            for(int j = 0; j < root.children.size(); j++){
                if(j != i){
                    targetSumLists.get(j).addAll(childSet);
                }
            }
        }
        root.totalSum = total;
        //ArrayList<ArrayList<Entry>> sumLists = new ArrayList<>();

        //ArrayList<HashSet<Entry>> targetLists = new ArrayList<>();

        /*for(int i = 0; i < root.children.size(); i++){
            Node child = root.children.get(i);
            //sumLists.add(findSums(child,root,null));

        }*/

        /*for(int i = 0; i < sumLists.size(); i++){
            ArrayList<Entry> currentList = sumLists.get(i);
            total += currentList.get(currentList.size()-1).sum;
        }*/
        long min = Long.MAX_VALUE;
        //for(int i = 0; i < sumLists.size(); i++){
            //ArrayList<Entry> currentList = sumLists.get(i);
            //HashSet<Entry> currentList = sumLists.get(i);
            //HashSet<Entry> currentList = sumLists.get(sumLists.size()-1);
            /*int lowerIndex = 0;
            //Collections.binarySearch(currentList,new Entry(total/3));
            if(lowerIndex < 0){
                lowerIndex = -1 * (lowerIndex + 1);
            }
            int upperIndex = Collections.binarySearch(currentList,new Entry(total/2));
            if(upperIndex < 0){
                upperIndex = -1 * (upperIndex + 1);
            }
            if(upperIndex >= currentList.size()){
                upperIndex = currentList.size() - 1;
            }
            for(int j = upperIndex; j >= lowerIndex; j--){
                Entry current = currentList.get(j);*/
            //for(Long current : targetSumsUp){
            for(int i = 1; i < nodes.size(); i++){
                Node current = nodes.get(i);
                if(current == root)
                    continue;
                /*HashSet<Entry> updatedList = new HashSet<>();
                findSums(root.children.get(i),root, updatedList, current.node);*/
                HashSet<Long> complimentSums = new HashSet<>();
                findSumsCompliment(current, current.totalSum, complimentSums);
                for(Node sibling : current.parent.children){
                    if(sibling != current){
                        complimentSums.addAll(sibling.sumList);
                        complimentSums.add(sibling.totalSum);
                    }
                }
                HashSet<Long> currentSums = targetSumLists.get(childIndexes.get(current));
                HashSet<Long> childSums = current.sumList;
                //ArrayList<Entry> updatedList = findSums(root.children.get(i),root,current.node);
                //ArrayList<HashSet<Entry>> currentSearchLists = new ArrayList<>();
                long target1 = current.totalSum;
                long target2 = total - 2*current.totalSum;
                long target3 = (total-current.totalSum)/2;
                boolean target3Flag = (total-current.totalSum)%2 == 0;
                //for(int k = 0; k < sumLists.size() /*+ current.node.children.size()*/; k++){
                    /*HashSet<Entry> targetList;
                    if(k == i){
                        targetList = updatedList;
                    }*/
                    /*else if(k >= sumLists.size()){
                        targetList = current.node.children.get(k-sumLists.size()).entry.sums;
                    }*/
                    /*else{
                        targetList = sumLists.get(k);
                    }*/
                    /*int targetIndex1 = Collections.binarySearch(targetList,new Entry(target1));
                    if(targetIndex1 >= 0){*/
                    if(currentSums.contains(target1) || complimentSums.contains(target1) || childSums.contains(target1)){
                        //long remaining = total - current.sum - targetList.get(targetIndex1).sum;
                        long remaining = total - current.totalSum - target1;
                        if(remaining <= target1){
                            //return  (target1 - remaining);
                            min = Math.min(min,target1 - remaining);
                        }
                    }
                    /*int targetIndex2 = Collections.binarySearch(targetList,new Entry(target2));
                    if(targetIndex2 >= 0){*/
                    if(currentSums.contains(target2) || complimentSums.contains(target2) || childSums.contains(target2)){
                        if(target2 <= current.totalSum){
                            //return  (current.totalSum - remaining);
                            min = Math.min(min,current.totalSum - target2);
                        }
                    }
                    if(target3Flag){
                        /*int targetIndex3 = Collections.binarySearch(targetList,new Entry(target3));
                        if(targetIndex3 >= 0){*/
                        if(currentSums.contains(target3) || complimentSums.contains(target3) || childSums.contains(target3)){
                            if(current.totalSum < target3){
                                //return  (target3 - remaining);
                                min = Math.min(min,target3 - current.totalSum);
                            }
                        }
                    }
                    /*if(current.totalSum == total - current.totalSum){
                        //return current.totalSum;
                        min = Math.min(min, current.totalSum);
                    }*/
                }
            //}

        //}
        //Stack<Node> stack = new Stack<>();
        return min == Long.MAX_VALUE? -1 : (int)min;
    }

    public static Node findRoot2(ArrayList<Node> nodes){
        Node root = null;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < nodes.size(); i++){
            Node current = nodes.get(i);
            if(current.children.size() < min){
                min = current.children.size();
                root = current;
            }
        }
        return root;
    }

    public static Node findRoot(ArrayList<Node> nodes){
        LinkedList<Node> queue = new LinkedList<Node>();
        HashSet<Node> visited = new HashSet<>();
        for(Node node : nodes){
            if(node.children.size() <= 1){
                queue.offer(node);
                visited.add(node);
            }
        }
        while(queue.size() > 1){
            Node current = queue.poll();
            for(Node neighbor : current.children){
                if(! visited.contains(neighbor)){
                    queue.offer(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return queue.pop();
    }

    public static void split(Node node, Node from){
        if(from != null){
            node.children.remove(from);
            node.parent = from;
        }
        for(int i = 0; i < node.children.size(); i++){
            split(node.children.get(i),node);
        }
    }

    public static long findSumsUp(Node node, int childIndex, HashSet<Long> list, HashMap<Node,Integer> childMap){
        //ArrayList<Entry> entries = new ArrayList<>();
        /*if(node.equals(exclude)){
            //return entries;
            return 0;
        }*/
        long sum = node.value;
        if(childMap != null){
            childMap.put(node,childIndex);
        }
        HashSet<Long> childSums = new HashSet<>();
        for(Node child : node.children){
            //if(!child.equals(exclude)) {
                HashSet<Long> childSet = new HashSet<>();
                sum += findSumsUp(child, childIndex, childSet, childMap);
                childSums.addAll(childSet);
                list.addAll(childSet);
                //ArrayList<Entry> childEntries = findSums(child, node, exclude);
                /*sum += childEntries.get(childEntries.size()-1).sum;
                for (Entry entry : childEntries) {
                    int index = Collections.binarySearch(entries, entry);
                    if (index < 0) {
                        index = -1 * (index + 1);
                    }
                    entries.add(index, entry);

                }*/

            //}
        }
        /*Entry thisEntry = new Entry(sum,node,from);
        int index = Collections.binarySearch(entries,thisEntry);
        if(index < 0){
            index = -1*(index + 1);
        }
        entries.add(index,thisEntry);
        return entries;*/
        //Entry thisEntry = new Entry(sum,node,from);
        node.totalSum = sum;
        node.sumList = childSums;
        list.add(sum);
        return sum;
    }

    public static void findSumsCompliment(Node node, long childSum, HashSet<Long> list) {
        if(node == null){
            return;
        }
        findSumsCompliment(node.parent, childSum, list);
        long sum = node.totalSum - childSum;
        list.add(sum);
    }


    private static final FastReader scanner = new FastReader();

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();

            int[] c = new int[n];

            String[] cItems = scanner.nextLine().split(" ");

            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }

            int[][] edges = new int[n - 1][2];

            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            long result = balancedForest(c, edges);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}

