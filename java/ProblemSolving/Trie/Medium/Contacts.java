/*
We're going to make our own Contacts application! The application must perform two types of operations:
1. add name, where name is a string denoting a contact name. This must store name as a new contact in the application.
2. find partial, where partial is a string denoting a partial name to search the application for.
It must count the number of contacts starting with partial and print the count on a new line.
Given n sequential add and find operations, perform each operation in order.
*Input Format
The first line contains a single integer, n, denoting the number of operations to perform.
Each line i of the n subsequent lines contains an operation in one of the two forms defined above.
*Constraints
1<=n<=10^5
1<= |name| <=21
1<= |partial| <=21
It is guaranteed that name and lowercase contain lowercase English letters only.
The input doesn't have any duplicate name for the add operation.
*Output Format
For each find partial operation, print the number of contact names starting with partial on a new line.
*Sample Input
4
add hack
add hackerrank
find hac
find hak
*Sample Output
2
0
*Explanation
We perform the following sequence of operations:
1. Add a contact named hack.
2. Add a contact named hackerrank.
3. Find and print the number of contact names beginning with hac.
There are currently two contact names in the application and both of them start with hac, so we print 2 on a new line.
4. Find and print the number of contact names beginning with hak.
There are currently two contact names in the application but neither of them start with hak, so we print 0 on a new line.
 */

package Trie.Medium;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Contacts {

    private static class Node{
        HashMap<Character,Node> children;
        int count;
        boolean terminate;

        public Node(){
            count = 0;
            terminate = false;
            children = new HashMap<>();
        }
    }

    static int[] contacts(String[][] queries) {
        ArrayList<Integer> ret = new ArrayList<>();
        Node root = new Node();
        for(int i = 0; i < queries.length; i++){
            switch(queries[i][0]){
                case "add":
                    add(queries[i][1],root);
                    break;
                case "find":
                     ret.add(find(queries[i][1],root));
                     break;
            }
        }
        return ret.stream().mapToInt(i -> i).toArray();
    }

    static void add(String str, Node root){
        root.count++;
        if(str.length() == 0){
            root.terminate = true;
            return;
        }
        char current = str.charAt(0);
        Node nextNode = root.children.merge(current, new Node(), (oldValue, newValue) -> oldValue);
        add(str.substring(1),nextNode);
    }

    static int find(String str, Node root){
        if(str.length() == 0){
            return root.count;
        }
        if(root.children.containsKey(str.charAt(0))){
            return find(str.substring(1),root.children.get(str.charAt(0)));
        }
        return 0;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

