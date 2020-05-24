/*
Two cats and a mouse are at various positions on a line.
You will be given their starting positions.
Your task is to determine which cat will reach the mouse first,
assuming the mouse doesn't move and the cats travel at equal speed.
If the cats arrive at the same time, the mouse will be allowed to move and it will escape while they fight.
You are given q queries in the form of x, y, and z representing the respective positions for cats A and B, and for mouse C.
Complete the function catAndMouse to return the appropriate answer to each query, which will be printed on a new line.
If cat A catches the mouse first, print Cat A.
If cat B catches the mouse first, print Cat B.
If both cats reach the mouse at the same time, print Mouse C as the two cats fight and mouse escapes.
For example, cat A is at position x=2 and cat B is at y=5. If mouse C is at position z=4,
it is 2 units from cat A and 1 unit from cat B. Cat B will catch the mouse.
*Function Description
Complete the catAndMouse function in the editor below. It should return one of the three strings as described.
catAndMouse has the following parameter(s):
x: an integer, Cat A's position
y: an integer, Cat B's position
z: an integer, Mouse C's position
*Input Format
The first line contains a single integer, q, denoting the number of queries.
Each of the q subsequent lines contains three space-separated integers describing the
respective values of x (cat A's location), y (cat B's location), and z (mouse C's location).
*Constraints
1<=q<=100
1<=x,y,z<=100
*Output Format
For each query, return Cat A if cat A catches the mouse first, Cat B if cat B catches the mouse first, or Mouse C if the mouse escapes.
*Sample Input 0
2
1 2 3
1 3 2
*Sample Output 0
Cat B
Mouse C
*Explanation 0
Query 0: The positions of the cats and mouse are shown below:
0--1--2--3--4-->
   A  B  C
Cat B will catch the mouse first, so we print Cat B on a new line.
Query 1: In this query, cats A and B reach mouse C at the exact same time:
0--1--2--3--4-->
   A  C  B
Because the mouse escapes, we print Mouse C on a new line.
 */

package Implementation.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class CatsAndAMouse {

    // Complete the catAndMouse function below.
    static String catAndMouse(int x, int y, int z) {
        int cat1 = Math.abs(x-z);
        int cat2 = Math.abs(y-z);
        if(cat1 == cat2){
            return "Mouse C";
        }
        else if(cat1 < cat2){
            return "Cat A";
        }
        else{
            return "Cat B";
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] xyz = scanner.nextLine().split(" ");

            int x = Integer.parseInt(xyz[0]);

            int y = Integer.parseInt(xyz[1]);

            int z = Integer.parseInt(xyz[2]);

            String result = catAndMouse(x, y, z);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

