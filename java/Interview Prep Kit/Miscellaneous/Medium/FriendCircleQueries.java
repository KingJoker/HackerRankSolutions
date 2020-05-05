/*
The population of HackerWorld is 10^9.
Initially, none of the people are friends with each other.
In order to start a friendship, two persons a and b have to shake hands, where 1<=a,b<=10^9.
The friendship relation is transitive, that is if a and b shake hands with each other,
a and friends of a become friends with b and friends of b.
You will be given q queries.
After each query, you need to report the size of the largest friend circle (the largest group of friends)
formed after considering that query.
*For example, your list of queries is:
1 2
3 4
2 3
*First, 1 and 2 shake hands, forming a circle of 2. Next, 3 and 4 do the same.
Now there are two groups of 2 friends.
When 2 and 3 become friends in the next query, both groups of friends are added together to make a circle of 4 friends.
We would print
2
2
4
*Function Description
Complete the function maxCircle in the editor below.
It must return an array of integers representing the size of the maximum circle of friends after each query.
*maxCircle has the following parameter(s):
queries: an array of integer arrays, each with two elements indicating a new friendship
*Input Format
The first line contains an integer, q, the number of queries to process.
Each of the next q lines consists of two space-separated integers denoting the 2-D array queries.
*Constraints
1<=q<=10^5
1<=queries[i][0],queries[i][1]<=10^9 for 0<=i<=q
queries[i][0] != queries[i][1]
*Output Format
Return an integer array of size q, whose value at index i is the size of largest group present after processing the ith query.
*Sample Input 0
2
1 2
1 3
*Sample Output 0
2
3
*Explanation 0
In the first query, 1 and 2 shake hands. So, the size of largest group of friends is 2 (as no other friendships exist).
After the second query, 1, 2 and 3 all become friends, as 1 shakes
hand with 3, 2 also become friends with 3 as he was already a friend of 1.
*Sample Input 1
4
1000000000 23
11 3778
7 47
11 1000000000
*Sample Output 1
2
2
2
4
*Explanation 1
After first query, person 1000000000 and person 23 become friends. So, the largest group size is 2.
After the second query, person 11 and person 3778 become friends. So, the largest group size is still 2.
After the third query, person 7 and person 47 become friends. Answer is still 2.
After the last query, person 11 and person 1000000000 become friends, which means 23, 11, 1000000000 and 3778 all become friends.
Hence, the answer now increased to 4.
*Sample Input 2
6
1 2
3 4
1 3
5 7
5 6
7 4
*Sample Output 2
2
2
4
4
4
7
*Explanation 2
Friend circles after each iteration:
1   [1,2]
2   [1,2],[3,4]
3   [1,2,3,4]
4   [1,2,3,4],[5,7]
5   [1,2,3,4],[5,7,6]
6   [1,2,3,4,5,6,7]
 */

package Miscellaneous.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class FriendCircleQueries {

    private static class Person{
        int personNumber;
        ArrayList<Person> friends;
        int circleSize;
        boolean modified;
        public Person(int personNumber){
            this.personNumber = personNumber;
            this.friends = new ArrayList<>();
            this.circleSize = 0;
            this.modified = false;
        }
    }

    // Most efficient
    static int[] maxCircle(int[][] queries) {
        int[] ret = new int[queries.length];
        int max = 0;
        HashMap<Integer,Integer> friends = new HashMap<>();
        HashMap<Integer,Integer> size = new HashMap<>();
        for(int i = 0; i < queries.length; i++){
            int friend1 = queries[i][0];
            int friend2 = queries[i][1];
            friends.merge(friend1, friend1, (oldValue, newValue) -> oldValue);
            friends.merge(friend2, friend2, (oldValue, newValue) -> oldValue);
            size.merge(friend1, 1, (oldValue, newValue) -> oldValue);
            size.merge(friend2, 1, (oldValue, newValue) -> oldValue);
            int currentSize = weightedUnion(friends, size, friend1, friend2);
            max = Math.max(max,currentSize);
            ret[i] = max;
        }
        return ret;
    }

    static int weightedUnion(HashMap<Integer,Integer> friends, HashMap<Integer,Integer> size, int friend1, int friend2){
        int rootFriend1 = root(friends, friend1);
        int rootFriend2 = root(friends, friend2);
        int sizeRootFriend1 = size.get(rootFriend1);
        int sizeRootFriend2 = size.get(rootFriend2);

        if(sizeRootFriend1 < sizeRootFriend2){
            friends.put(rootFriend1,friends.get(rootFriend2));
            int newSize = size.get(rootFriend2);
            if(rootFriend1 != rootFriend2){
                newSize += size.get(rootFriend1);
            }
            size.put(rootFriend2, newSize);
            return newSize;
        }
        else {
            friends.put(rootFriend2,friends.get(rootFriend1));
            int newSize = size.get(rootFriend1);
            if(rootFriend1 != rootFriend2){
                newSize +=size.get(rootFriend2);
            }
            size.put(rootFriend1, newSize);
            return newSize;
        }
    }

    static int root(HashMap<Integer,Integer> friends, int person){
        while(friends.get(person) != person){
            person = friends.get(friends.get(person));
        }
        return person;
    }

    // Complete the maxCircle function below.

    // less efficient
    static int[] maxCircle1(int[][] queries) {
        int[] ret = new int[queries.length];
        /*for(int i = 0; i < queries.length; i++){
            biggestNode = Math.max(biggestNode,queries[i][0]);
            biggestNode = Math.max(biggestNode,queries[i][1]);
        }*/
        HashMap<Integer,HashSet<Integer>> matrix = new HashMap<>();
        for(int i = 0; i < queries.length; i++){
            int person1 = queries[i][0];
            int person2 = queries[i][1];
            HashSet<Integer> person1Set = new HashSet<>();
            person1Set.add(person1);
            matrix.merge(person1, person1Set, (oldValue, newValue) -> {oldValue.add(person1); return oldValue;});
            HashSet<Integer> person2Set = new HashSet<>();
            person2Set.add(person2);
            matrix.merge(person2, person2Set, (oldValue, newValue) -> {oldValue.add(person2); return oldValue;});
            ret[i] = findFriendCircleSize(person1, matrix);
        }
        return ret;
    }

    static int findFriendCircleSize(int person, HashMap<Integer,HashSet<Integer>> matrix){
        HashSet<Integer> cache = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(person);
        while(stack.size() != 0){
            int currentPerson = stack.pop();
            cache.add(currentPerson);
            for(Integer friend : matrix.get(currentPerson)){
                if(! cache.contains(friend)){
                    stack.push(friend);
                }
            }
        }
        return cache.size();
    }

    //even less efficient
    static int[] maxCircle2(int[][] queries) {
        int[] output = new int[queries.length];
        HashMap<Integer,Person> people = new HashMap<>();
        int maxCircleSize = 0;
        for(int i = 0; i < queries.length; i++){
            Person person1 = people.getOrDefault(queries[i][0],null);
            if(person1 == null){
                person1 = new Person(queries[i][0]);
                people.put(queries[i][0],person1);
            }
            Person person2 = people.getOrDefault(queries[i][1],null);
            if(person2 == null){
                person2 = new Person(queries[i][1]);
                people.put(queries[i][1],person1);
            }
            person1.friends.add(person2);
            person2.friends.add(person1);
            maxCircleSize = Math.max(maxCircleSize, findMaxFriendCircle(person1,Collections.synchronizedSet(new HashSet<Person>())));
            //maxCircleSize = Math.max(maxCircleSize, addFriend(person1, person2));
            output[i] = maxCircleSize;
        }
        return output;
    }

    static int findMaxFriendCircle(Person person, Set<Person> friends){
        if(friends.contains(person)){
            return friends.size();
        }
        friends.add(person);
        CompletableFuture<Void> allFutures = null;
        for(Person friend : person.friends){
            if(! friends.contains(friend)){
                findMaxFriendCircle(friend, friends);
            }

        }
        return friends.size();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

