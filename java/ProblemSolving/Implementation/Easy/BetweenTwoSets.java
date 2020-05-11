/*
You will be given two arrays of integers and asked to determine all integers that satisfy the following two conditions:
1. The elements of the first array are all factors of the integer being considered
2. The integer being considered is a factor of all elements of the second array
These numbers are referred to as being between the two arrays. You must determine how many such numbers exist.
For example, given the arrays a=[2,6] and b=[24,36], there are two numbers between them: 6 and 12.
6%6=0, 24%6=0 and 36%6=0 for the first value. Similarly, 12%2=0, 12%6=0 and 24%12=0, 36%12=0.
*Function Description
Complete the getTotalX function in the editor below.
It should return the number of integers that are betwen the sets.
*getTotalX has the following parameter(s):
a: an array of integers
b: an array of integers
*Input Format
The first line contains two space-separated integers, n and m, the number of elements in array a and the number of elements in array b.
The second line contains n distinct space-separated integers describing a[i] where 0<=i<n.
The third line contains m distinct space-separated integers describing b[j] where 0<=j<m.
*Constraints
1<=n,m<=10
1<=a[i]<=100
1<=b[j]<=100
*Output Format
Print the number of integers that are considered to be between a and b.
*Sample Input
2 3
2 4
16 32 96
*Sample Output
3
*Explanation
2 and 4 divide evenly into 4, 8, 12 and 16.
4, 8 and 16 divide evenly into 16, 32, 96.
4, 8 and 16 are the only three numbers for which each element of a is a factor and each is a factor of all elements of b.
 */

package Implementation.Easy;

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



public class BetweenTwoSets {

    private static class Result {

        /*
         * Complete the 'getTotalX' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts following parameters:
         *  1. INTEGER_ARRAY a
         *  2. INTEGER_ARRAY b
         */

        public static int getTotalX(List<Integer> a, List<Integer> b) {
            HashSet<Integer> commonFactorsB = null;
            HashSet<Integer> setA = new HashSet<>(a);
            setA.add(1);
            int count = 0;
            for(int num : b){
                HashSet<Integer> factors = factor(num);
                if(commonFactorsB == null){
                    commonFactorsB = factors;
                }
                else{
                    commonFactorsB.retainAll(factor(num));
                }
            }
            for(int num : commonFactorsB){
                HashSet<Integer> factors = factor(num);
                if(factors.containsAll(setA)){
                    count++;
                }
            }
            return count;
        }

        private static HashSet<Integer> factor(int num){
            HashSet<Integer> factors = new HashSet<>();
            for(int i = num; i > 0; i--){
                if(num % i == 0){
                    factors.add(i);
                }
            }
            return factors;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

