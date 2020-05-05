/*
You are given an array and you need to find number of tripets of indices (i,j,k)
such that the elements at those indices are in geometric progression for a given common ratio r and i<j<k.
For example, arr=[1,4,16,64]. If r=4, we have [1,4,16] and [4,16,64] at indices (0,1,2) and (1,2,3).
*Function Description
Complete the countTriplets function in the editor below.
It should return the number of triplets forming a geometric progression for a given  as an integer.
*countTriplets has the following parameter(s):
arr: an array of integers
r: an integer, the common ratio
*Input Format
The first line contains two space-separated integers n and r, the size of arr and the common ratio.
The next line contains n space-seperated integers arr[i].
*Constraints
1<=n<=10^5
1<=r<=10^9
1<=arr[i]<=10^9
*Output Format
Return the count of triplets that form a geometric progression.
*Sample Input 0
4 2
1 2 2 4
*Sample Output 0
2
*Explanation 0
There are 2 triplets in satisfying our criteria, whose indices are (0,1,3) and (0,2,3)
*Sample Input 1
6 3
1 3 9 9 27 81
*Sample Output 1
6
*Explanation 1
The triplets satisfying are index (0,1,2), (0,1,3), (1,2,4), (1,3,4), (2,4,5) and (3,4,5).
*Sample Input 2
5 5
1 5 5 25 125
*Sample Output 2
4
*Explanation 2
The triplets satisfying are index (0,1,3), (0,2,3), (1,3,4), (2,3,4).
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

public class CountTriplets {

    // Complete the countTriplets function below.
    // O(n)
    static long countTriplets(List<Long> arr, long r) {
        HashMap<Long,Long> mapI = new HashMap<>();
        HashMap<Long,Long> mapJ = new HashMap<>();
        long count = 0;
        for(long i : arr){
            long previous = i / r;
            if(i % r == 0 && mapJ.containsKey(previous)){
                count += mapJ.get(previous);
            }
            if(i % r == 0 && mapI.containsKey(previous)){
                mapJ.merge(i, mapI.get(previous), (oldValue, newValue) -> oldValue + newValue);
            }
            mapI.merge(i, 1L, (oldValue, one) -> oldValue + one);
        }
        return count;
    }
    // O(n^2 * log(n))
    static long countTripletsSlow(List<Long> arr, long r) {
        HashMap<Long,ArrayList<Integer>> frequencyMap = new HashMap<>();
        long tripleCount = 0;
        for(int i = 0; i < arr.size(); i++){
            ArrayList<Integer> indeces = new ArrayList<>();
            indeces.add(i);
            frequencyMap.merge(arr.get(i),indeces,(oldList,newList) -> {
                int insertPoint = Collections.binarySearch(oldList,newList.get(0));
                if(insertPoint < 0){
                    insertPoint = -1 * (insertPoint + 1);
                }
                oldList.add(insertPoint,newList.get(0));
                return oldList;
            });
        }
        for(long value : frequencyMap.keySet()){
            if(frequencyMap.containsKey(value * r) && frequencyMap.containsKey(value * r * r)){
                for(int indexI : frequencyMap.get(value)){
                    ArrayList<Integer> indicesJ = frequencyMap.get(value * r);
                    int startJ = Collections.binarySearch(indicesJ,indexI);
                    if(startJ >= 0){
                        startJ++;
                    }
                    else{
                        startJ = -1 * (startJ + 1);
                    }
                    for(int j = startJ; j < indicesJ.size(); j++){
                        ArrayList<Integer> indicesK = frequencyMap.get(value * r * r);
                        int indexJ = indicesJ.get(j);
                        int startK = Collections.binarySearch(indicesK,indexJ);
                        if(startK >= 0){
                            startK++;
                        }
                        else{
                            startK = -1 * (startK + 1);
                        }
                        if(startK < indicesK.size()){
                            tripleCount += indicesK.size() - startK;
                        }
                    }
                }
            }
        }
        return tripleCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
