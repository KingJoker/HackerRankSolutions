/*
There are a number of plants in a garden. Each of these plants has been treated with some amount of pesticide.
After each day, if any plant has more pesticide than the plant on its left, being weaker than the left one, it dies.
You are given the initial values of the pesticide in each of the plants.
Print the number of days after which no plant dies, i.e. the time after which there are no plants
with more pesticide content than the plant to their left.
For example, pesticide levels p=[3,6,2,7,5]. Using a 1-indexed array, day 1 plants 2 and 4 die leaving p=[3,2,5].
On day 2, plant 3 of the current array dies leaving p=[3,2].
As there is no plant with a higher concentration of pesticide than the one to its left, plants stop dying after day .
*Function Description
Complete the function poisonousPlants in the editor below.
It must return an integer representing the number of days until plants no longer die from pesticide.
*poisonousPlants has the following parameter(s):
p: an array of integers representing pesticide levels in each plant
*Input Format
The first line contains an integer n, the size of the array p.
The next line contains n space-separated integers p[i].
*Constraints
1<=n<=10^5
0<=p[i]<=10^9
*Output Format
Output an integer equal to the number of days after which no plants die.
*Sample Input
7
6 5 8 4 7 10 9
*Sample Output
2
*Explanation
Initially all plants are alive.
Plants = {(6,1), (5,2), (8,3), (4,4), (7,5), (10,6), (9,7)}
Plants[k] = (i,j) => jth plant has pesticide amount = i.
After the 1st day, 4 plants remain as plants 3, 5, and 6 die.
Plants = {(6,1), (5,2), (4,4), (9,7)}
After the 2nd day, 3 plants survive as plant 7 dies.
Plants = {(6,1), (5,2), (4,4)}
After the 2nd day the plants stop dying.
 */

package StacksAndQueues.Hard;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class PoisonousPlants {

    private static class MyLinkedList<T>{
        private static class Node<T>{
            Node previous;
            Node next;
            T data;
            public Node(T data){
                this.data = data;
            }
        }
        Node<T> head;
        Node<T> tail;
        int size;

        public MyLinkedList(){
            size = 0;
        }

        public void offer(T data){
            Node node = new Node(data);
            if(tail == null){
                head = tail = node;
            }
            else{
                node.previous = tail;
                tail.next = node;
                tail = node;
            }
            size++;
        }

        public T peekLast(){
            return tail.data;
        }

        public T peekFirst(){
            return head.data;
        }

        public T pop(){
            T data = head.data;
            head = head.next;
            size--;
            return data;
        }

        public int size(){
            return size;
        }

        public void concat(MyLinkedList<T> other){
            tail.next = other.head;
            tail = other.tail;
            size += other.size();
        }
    }

    // Complete the poisonousPlants function below.
    static int poisonousPlants(int[] p) {
        ArrayList<MyLinkedList<Integer>> stacks = new ArrayList<>();
        int maxDays = 0;
        MyLinkedList<Integer> currentList = new MyLinkedList<>();
        stacks.add(currentList);
        currentList.offer(p[0]);
        for(int i = 1; i < p.length; i++){
            if(p[i] <= currentList.peekLast()){
                currentList.offer(p[i]);
            }
            else{
                currentList = new MyLinkedList<>();
                stacks.add(currentList);
                currentList.offer(p[i]);
            }
        }
        while(stacks.size() > 1){
            maxDays++;
            for(int i = stacks.size()-1; i >= 1; i--){
                stacks.get(i).pop();
                if(stacks.get(i).size() == 0){
                    stacks.remove(i);
                }
            }
            for(int i = stacks.size() - 1; i>=1; i--){
                MyLinkedList<Integer> current = stacks.get(i);
                MyLinkedList<Integer> previous = stacks.get(i-1);
                if(current.peekFirst() <= previous.peekLast()){
                    previous.concat(current);
                    stacks.remove(i);
                }
            }
        }
        return maxDays;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] p = new int[n];

        String[] pItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int pItem = Integer.parseInt(pItems[i]);
            p[i] = pItem;
        }

        int result = poisonousPlants(p);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

