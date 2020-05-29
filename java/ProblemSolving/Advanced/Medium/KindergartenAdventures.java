/*
Meera teaches a class of n students, and every day in her classroom is an adventure. Today is drawing day!
The students are sitting around a round table, and they are numbered from 1 to n in the clockwise direction.
This means that the students are numbered 1,2,3,...,n-1, and students 1 and n are sitting next to each other.
After letting the students draw for a certain period of time,
Meera starts collecting their work to ensure she has time to review all the drawings before the end of the day.
However, some of her students aren't finished drawing! Each student i needs t[i] extra minutes to complete their drawing.
Meera collects the drawings sequentially in the clockwise direction, starting with student ID x,
and it takes her exactly 1 minute to review each drawing.
This means that student x gets 0 extra minutes to complete their drawing, student x+1 gets 1 extra minute,
student x+2 gets 2 extra minutes, and so on.
Note that Meera will still spend 1 minute for each student even if the drawing isn't ready.
Given the values of t[1],t[2],...,t[n], help Meera choose the best possible x to start collecting drawings from,
such that the number of students able to complete their drawings is maximal.
Then print x on a new line. If there are multiple such IDs, select the smallest one.
*Input Format
The first line contains a single positive integer, n, denoting the number of students in the class.
The second line contains n space-separated integers describing the respective amounts of time that each student
needs to finish their drawings (i.e., t[1],t[2],...,t[n]).
*Constraints
1<=n<=10^5
*Subtasks
1<=n<=10^4 for 30% of the maximum score.
*Output Format
Print an integer denoting the ID number, x, where Meera should start collecting the drawings such that a
maximal number of students can complete their drawings. If there are multiple such IDs, select the smallest one.
*Sample Input 1
3
1 0 0
*Sample Output 1
2
*Explanation 1
Meera's class has n=3 students:
1. If x=1, then only two students will finish.
The first student needs t[1]=1 extra minute to complete their drawing.
If Meera starts collecting here, this student will never finish their drawing.
Students 2 and 3's drawings are already finished, so their drawings are ready when she collects them in the second and third minutes.
2. If x=2, then all three students will finish.
The second student needs t[2]=0 extra minutes, so the drawing is ready for Meera to collect.
The next (third) student's drawing is also ready one minute later, as t[1]=1.
Meera then proceeds to the next (first) student, who needed t[1]=1 extra minute.
Because she already spent two minutes collecting the work of the other two students,
the first student's drawing has already been completed for 2-1=1 minute.
3. If x=3, then all three students will finish.
The third student needs t[3]=0 extra minutes, so the drawing is ready for Meera to collect.
The next (first) student's drawing is also ready one minute later, as t[1]=1 and 1 minute passed while
Meera collected the third student's drawing.
She then proceeds to the next (second) student, whose drawing was already completed (as t[2]=0)
Starting with student IDs x=2 or x=3 will result in a maximum number of completed drawings (i.e., 3).
Because there are two equally valid answers, we choose the smaller ID, 2, and print it as our answer.
*Sample Input 2
3
0 1 2
*Sample Output 2
1
 */

package Advanced.Medium;

import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class KindergartenAdventures {

    /*
     * Complete the solve function below.
     */
    static int solve(int[] t) {
        int[] counts = new int[t.length];
        for(int i = 0; i < t.length; i++){
            if(t[i] == 0 || t[i] == t.length){
                continue;
            }
            int endPosition = i - t[i] + 1;
            if(endPosition < 0){
                endPosition = t.length + endPosition;
            }
            counts[endPosition]--;
            int startPosition = i+1;
            if(startPosition >= t.length){
                startPosition = 0;
            }
            counts[startPosition]++;
        }
        int person = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < counts.length; i++){
            sum += counts[i];
            if(sum > max){
                max = sum;
                person = i;
            }
        }
        return person + 1;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        int tCount = Integer.parseInt(scanner.nextLine().trim());

        int[] t = new int[tCount];

        String[] tItems = scanner.nextLine().split(" ");

        for (int tItr = 0; tItr < tCount; tItr++) {
            int tItem = Integer.parseInt(tItems[tItr].trim());
            t[tItr] = tItem;
        }

        int id = solve(t);

        bufferedWriter.write(String.valueOf(id));
        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}

