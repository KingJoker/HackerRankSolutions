/*
You are given  queries. Each query is of the form two integers described below:
- 1 x: Insert x in your data structure.
- 2 y: Delete one occurence of y from your data structure, if present.
- 3 z: Check if any integer is present whose frequency is exactly z. If yes, print 1 else 0.
The queries are given in the form of a 2-D array queries of size q where queries[i][0] contains the operation, and queries[i][1] contains the data element.
For example, you are given array queries=[(1,1),(2,2),(3,2),(1,1),(1,1),(2,1),(3,2).
The results of each operation are:
Operation   Array   Output
(1,1)       [1]
(2,2)       [1]
(3,2)                   0
(1,1)       [1,1]
(1,1)       [1,1,1]
(2,1)       [1,1]
(3,2)                   1
Return an array with the output: [0,1].
*Function Description
Complete the freqQuery function in the editor below.
It must return an array of integers where each element is a 1 if there is at least one element value
with the queried number of occurrences in the current array, or 0 if there is not.
*freqQuery has the following parameter(s):
queries: a 2-d array of integers
*Input Format
The first line contains of an integer q, the number of queries.
Each of the next q lines contains two integers denoting the 2-d array queries.
*Constraints
1<=q<=10^5
1<x,y,z<=10^9
All queries[i][0]∈{1,2,3}
1<=queries[i][1]<=10^9
*Output Format
Return an integer array consisting of all the outputs of queries of type 3.
*Sample Input 0
8
1 5
1 6
3 2
1 10
1 10
1 6
2 5
3 2
*Sample Output 0
0
1
*Explanation 0
For the first query of type 3, there is no integer whose frequency is 2 (array=[5,6]). So answer is 0.
For the second query of type 3, there are two integers in array=[6,10,10,6] whose frequency is 2 (integers = 6 and 10). So, the answer is 1.
*Sample Input 1
4
3 4
2 1003
1 16
3 1
*Sample Output 1
0
1
*Explanation 1
For the first query of type 3, there is no integer of frequency 4. The answer is 0.
For the second query of type 4, there is one integer, 16 of frequency 1 so the answer is 1.
*Sample Input 2
10
1 3
2 3
3 2
1 4
1 5
1 5
1 4
3 2
2 4
3 2
*Sample Output 2
0
1
1
*Explanation 2
When the first output query is run, the array is empty.
We insert two 4's and two 5's before the second output query, arr=[4,5,5,4] so there are two instances of elements occurring twice.
We delete a 4 and run the same query. Now only the instances of 5 satisfy the query.
 */

package DictionairesAndHashmaps.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class FrequencyQueries {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<List<Integer>> queries) {
        ArrayList<Integer> output = new ArrayList<>();
        HashMap<Integer,Integer> valueToFreq = new HashMap<>();
        HashMap<Integer,HashSet<Integer>> freqToValue = new HashMap<>();
        for(List<Integer> query : queries){
            int queryType = query.get(0);
            int data = query.get(1);
            if(queryType == 1){
                int oldFrequency = valueToFreq.getOrDefault(data,0);
                int newFrequency = oldFrequency + 1;
                if(oldFrequency != 0){
                    HashSet<Integer> oldFreqValues = freqToValue.get(oldFrequency);
                    if(oldFreqValues.size() > 1){
                        oldFreqValues.remove(data);
                    }
                    else{
                        freqToValue.remove(oldFrequency);
                    }
                }

                HashSet<Integer> newValues = new HashSet<>();
                newValues.add(data);
                freqToValue.merge(newFrequency,newValues, (oldValues, newValue) -> {oldValues.addAll(newValue); return oldValues;});
                valueToFreq.put(data,newFrequency);
            }
            else if(queryType == 2){
                if(valueToFreq.containsKey(data)){
                    int oldFrequency = valueToFreq.get(data);
                    int newFrequency = oldFrequency - 1;
                    HashSet<Integer> oldFreqValues = freqToValue.get(oldFrequency);
                    if(oldFreqValues.size() > 1){
                        oldFreqValues.remove(data);
                    }
                    else{
                        freqToValue.remove(oldFrequency);
                    }
                    if(newFrequency != 0){
                        HashSet<Integer> newValues = new HashSet<>();
                        newValues.add(data);
                        freqToValue.merge(newFrequency,newValues, (oldValues, newValue) -> {oldValues.addAll(newValue); return oldValues;});
                        valueToFreq.put(data, newFrequency);
                    }
                    else{
                        valueToFreq.remove(data);
                    }
                }
            }
            else{
                 boolean present = freqToValue.containsKey(data);
                 output.add((present ? 1 : 0));
            }
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);

        bufferedWriter.write(
                ans.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
