/*
Given 3 arrays a, b, c of different sizes, find the number of distinct triplets (p,q,r) where p is an element of a, written as p∈a, q∈b, and r∈c, satisfying the criteria: p<=q and q>=r.
For example, given a=[3,5,7], b=[3,6] and c=[4,6,9], we find four distinct triplets: (3,6,4),(3,6,6),(5,6,4),(5,6,6).
*Function Description
Complete the triplets function in the editor below. It must return the number of distinct triplets that can be formed from the given arrays.
*triplets has the following parameter(s):
a, b, c: three arrays of integers.
*Input Format
The first line contains 3 integers lena, lenb, and lenc, the sizes of the three arrays.
The next 3 lines contain space-separated integers numbering lena, lenb, and lenc respectively.
*Constraints
1<=lena,lenb,lenc<=10^5
1<=all elements in a,b,c<=10^8
*Output Format
Print an integer representing the number of distinct triplets.
*Sample Input 0
3 2 3
1 3 5
2 3
1 2 3
*Sample Output 0
8
*Explanation 0
The special triplets are (1,2,1),(1,2,2),(1,3,1),(1,3,2),(1,3,3),(3,3,1),(3,3,2),(3,3,3).
*Sample Input 1
3 3 3
1 4 5
2 3 3
1 2 3
*Sample Output 1
5
*Explanation 1
The special triplets are (1,2,1),(1,2,2),(1,3,1),(1,3,2),(1,3,3).
*Sample Input 2
4 3 4
1 3 5 7
5 7 9
7 9 11 13
*Sample Output 2
12
*Explanation 2
The special triplets are (1,7,7),(1,9,7),(1,9,9),(3,7,7),(3,9,7),(3,9,9),(5,7,7),(5,9,7),(5,9,9),(7,7,7),(7,9,7),(7,9,9).
 */

package Search.Medium;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class TripleSum {

    // Complete the triplets function below.
    static long triplets(int[] a, int[] b, int[] c) {
        List<Integer> listA = new ArrayList<>(Arrays.stream(a).boxed().collect(Collectors.toSet())).parallelStream().sorted().collect(Collectors.toList());
        List<Integer> listB = new ArrayList<>(Arrays.stream(b).boxed().collect(Collectors.toSet())).parallelStream().sorted().collect(Collectors.toList());
        List<Integer> listC = new ArrayList<>(Arrays.stream(c).boxed().collect(Collectors.toSet())).parallelStream().sorted().collect(Collectors.toList());
        //listA.sort(null);
        //listB.sort(null);
        //listC.sort(null);
        int loweriA = 0;
        long count = 0;
        HashMap<Integer,Integer> cCache = new HashMap<>();
        for(int iB = 0; iB<listB.size(); iB++){
            for(; loweriA < listA.size() && listA.get(loweriA) <= listB.get(iB); loweriA++);
            if(cCache.containsKey(iB)){
                count += cCache.get(iB);
                continue;
            }
            int iC = Collections.binarySearch(listC,listB.get(iB));
            if(iC < 0){
                iC = (iC + 1) * -1;
            }
            else{
                iC++;
            }
            count += ((long)iC) * ((long)loweriA);
            cCache.put(iB,iC);
        }
        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = triplets(arra, arrb, arrc);

        bufferedWriter.write(String.valueOf(ans));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
