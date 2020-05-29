/*
Flatland is a country with a number of cities, some of which have space stations.
Cities are numbered consecutively and each has a road of 1km length connecting it to the next city.
It is not a circular route, so the first city doesn't connect with the last city.
Determine the maximum distance from any city to it's nearest space station.
For example, there are n=3 cities and m=1 of them has a space station, city 1.
They occur consecutively along a route. City 2 is 2-1=1 unit away and city 3 is 3-1=2 units away.
City 1 is 0 units from its nearest space station as one is located there. The maximum distance is 2.
*Function Description
Complete the flatlandSpaceStations function in the editor below.
It should return an integer that represents the maximum distance any city is from a space station.
*flatlandSpaceStations has the following parameter(s):
n: the number of cities
c: an integer array that contains the indices of cities with a space station, 1-based indexing
*Input Format
The first line consists of two space-separated integers, n and m.
The second line contains m space-separated integers, the indices of each city having a space-station.
These values are unordered and unique.
*Constraints
1<=n<=10^5
1<=m<=n
There will be at least 1 city with a space station.
No city has more than one space station.
*Output Format
Print an integer denoting the maximum distance that an astronaut in a
Flatland city would need to travel to reach the nearest space station.
*Sample Input 0
5 2
0 4
*Sample Output 0
2
*Explanation 0
This sample corresponds to following graphic:
0*->1->2->3->4*
The distance to the nearest space station for each city is listed below:
c[0] has distance 0 km, as it contains a space station.
c[1] has distance 1 km to the space station in c[0].
c[2] has distance 2 km to the space stations in c[0] and c[4].
c[3] has distance 1 km to the space station in c[4].
c[4] has distance 0 km, as it contains a space station.
We then take max(0,1,2,1,0)=2.
*Sample Input 1
6 6
0 1 2 4 3 5
*Sample Output 1
0
*Explanation 1
In this sample, n=m so every city has space station and we print 0 as our answer.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class FlatlandSpaceStations {

    // Complete the flatlandSpaceStations function below.
    static int flatlandSpaceStations(int n, int[] c) {
        Arrays.parallelSort(c);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            int index = Arrays.binarySearch(c,i);
            int min = 0;
            if(index < 0){
                index = -1 * (index + 1);
                if(index == c.length){
                    min = i - c[index-1];
                }
                else if(index == 0){
                    min = c[index] - i;
                }
                else {
                    min = Math.min(Math.abs(c[index] - i), Math.abs(c[index - 1] - i));
                }
            }
            max = Math.max(max,min);
        }
        return max;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[] c = new int[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = flatlandSpaceStations(n, c);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

