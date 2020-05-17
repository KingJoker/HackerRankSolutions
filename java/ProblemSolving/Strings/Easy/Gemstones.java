/*
John has collected various rocks. Each rock has various minerals embeded in it.
Each type of mineral is designated by a lowercase letter in the range ascii[a-z].
There may be multiple occurrences of a mineral in a rock.
A mineral is called a gemstone if it occurs at least once in each of the rocks in John's collection.
Given a list of minerals embedded in each of John's rocks, display the number of types of gemstones he has in his collection.
For example, the array of mineral composition strings arr=[abc,abc,bc].
The minerals b and c appear in each composite, so there are 2 gemstones.
*Function Description
Complete the gemstones function in the editor below.
It should return an integer representing the number of gemstones found in the list of rocks.
*gemstones has the following parameter(s):
arr: an array of strings
*Input Format
The first line consists of an integer n, the size of arr.
Each of the next n lines contains a string arr[i] where each letter represents an occurence of a mineral in the current rock.
*Constraints
1<=n<=100
1<= |arr[i]| <=100
Each composition arr[i] consists of only lower-case Latin letters ('a'-'z').
*Output Format
Print the number of types of gemstones in John's collection. If there are none, print 0.
*Sample Input
3
abcdde
baccd
eeabg
*Sample Output
2
*Explanation
Only a and b are gemstones because they are the only types that occur in every rock.
 */

package Strings.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class Gemstones {

    // Complete the gemstones function below.
    static int gemstones(String[] arr) {
        ArrayList<HashSet<Character>> gems = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            HashSet<Character> gem = arr[i].chars().mapToObj(x -> (char)x).collect(Collectors.toCollection(HashSet::new));
            gems.add(gem);
        }
        HashSet<Character> minerals = gems.get(0);
        for(int i = 1; i < gems.size(); i++){
            minerals.retainAll(gems.get(i));
        }
        return minerals.size();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            String arrItem = scanner.nextLine();
            arr[i] = arrItem;
        }

        int result = gemstones(arr);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

