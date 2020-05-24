/*
Aerith is playing a cloud hopping game.
In this game, there are sequentially numbered clouds that can be thunderheads or cumulus clouds.
Her character must jump from cloud to cloud until it reaches the start again.
To play, Aerith is given an array of clouds, c and an energy level e=100.
She starts from c[0] and uses 1 unit of energy to make a jump of size k to cloud c[(i+k)%n].
If Aerith lands on a thundercloud, c[i]=1, her energy (e) decreases by 2 additional units.
The game ends when Aerith lands back on cloud 0.
Given the values of n, k, and the configuration of the clouds as an array c,
can you determine the final value of e after the game ends?
For example, give c=[0,0,1,0] and k=2, the indices of her path are 0->2->0.
Her energy level reduces by 1 for each jump to 98.
She landed on one thunderhead at an additional cost of 2 energy units. Her final energy level is 96.
Note: Recall that % refers to the modulo operation. In this case, it serves to make the route circular.
If Aerith is at c[n-1] and jumps 1, she will arrive at c[0].
*Function Description
Complete the jumpingOnClouds function in the editor below.
It should return an integer representing the energy level remaining after the game.
*jumpingOnClouds has the following parameter(s):
c: an array of integers representing cloud types
k: an integer representing the length of one jump
*Input Format
The first line contains two space-separated integers, n and k, the number of clouds and the jump distance.
The second line contains n space-separated integers c[i] where 0<=i<=n. Each cloud is described as follows:
If c[i]=0, then cloud i is a cumulus cloud.
If c[i]=1, then cloud i is a thunderhead.
*Constraints
2<=n<=25
1<=k<=n
n%k=0
c[i]∈{0,1}
*Output Format
Print the final value of e on a new line.
*Sample Input
8 2
0 0 1 0 0 1 1 0
*Sample Output
92
*Explanation
Observe that our thunderheads are the clouds numbered 2, 5, and 6. Aerith makes the following sequence of moves:
Move: 0->2, Energy: e=100-1-2=97.
Move: 2->4, Energy: e=97-1=96.
Move: 4->6, Energy: e=96-1-2=93.
Move: 6->0, Energy: e=93-1=92.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class JumpingOnTheCloudsRevisited {

    // Complete the jumpingOnClouds function below.
    static int jumpingOnClouds(int[] c, int k) {
        int cloud = 0;
        int energy = 100;
        do{
            energy--;
            if(c[cloud] == 1){
                energy -= 2;
            }
            cloud = (cloud + k) % c.length;
        }while(cloud != 0);
        return energy;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int result = jumpingOnClouds(c, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

