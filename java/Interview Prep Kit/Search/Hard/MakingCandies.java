/*
Karl loves playing games on social networking sites. His current favorite is CandyMaker, where the goal is to make candies.
Karl just started a level in which he must accumulate n candies starting with m machines and w workers.
In a single pass, he can make m*w candies.
After each pass, he can decide whether to spend some of his candies to buy more machines or hire more workers.
Buying a machine or hiring a worker costs p units, and there is no limit to the number of machines he can own or workers he can employ.
Karl wants to minimize the number of passes to obtain the required number of candies at the end of a day. Determine that number of passes.
For example, Karl starts with m=1 machine and w=2 workers. The cost to purchase or hire, p=1 and he needs to accumulate 60 candies.
He executes the following strategy:
Make m*w=1*2=2 candies. Purchase two machines.
Make 3*2=6 candies. Purchase 3 machines and hire 3 workers.
Make 6*5 candies. Retain all 30 candies.
Make 6*5 candies. With yesterday's production, Karl has 60 candies.
It took 4 passes to make enough candies.
*Function Description
Complete the minimumPasses function in the editor below.
The function must return a long integer representing the minimum number of passes required.
*minimumPasses has the following parameter(s):
m: long integer, the starting number of machines
w: long integer, the starting number of workers
p: long integer, the cost of a new hire or a new machine
n: long integer, the number of candies to produce
*Input Format
A single line consisting of four space-separated integers describing the values of m, w, p, and n,
the starting number of machines and workers, the cost of a new machine or a new hire,
and the the number of candies Karl must accumulate to complete the level.
*Constraints
1<=m,w,p,n<=10^12
*Output Format
Return a long integer denoting the minimum number of passes required to accumulate at least n candies.
*Sample Input
3 1 2 12
*Sample Output
3
*Explanation
Karl makes three passes:
In the first pass, he makes m*w=3*1=3 candies. He then spends p=2 of them hiring another worker, so w=2 and he has one candy left over.
In the second pass, he makes 3*2=6 candies. He spends 2*p=4 of them on another machine and another worker,
so w=3 and m=4 and he has 3 candies left over.
In the third pass, Karl makes 4*3=12 candies.
Because this satisfies his goal of making at least n=12 candies, we print the number of passes (i.e., 3) as our answer.
 */

package Search.Hard;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class MakingCandies {

    // Complete the minimumPasses function below.
    static long minimumPasses(long m1, long w1, long p1, long n1) {
        BigInteger m = BigInteger.valueOf(m1);
        BigInteger w = BigInteger.valueOf(w1);
        BigInteger p = BigInteger.valueOf(p1);
        BigInteger n = BigInteger.valueOf(n1);
        BigInteger[] minDaysRet = n.divideAndRemainder(m.multiply(w));
        BigInteger minDays = minDaysRet[0];
        if(minDaysRet[1].compareTo(BigInteger.ZERO) > 0){
            minDays = minDays.add(BigInteger.ONE);
        }
        BigInteger numCandies = BigInteger.ZERO;
        BigInteger currentDay = BigInteger.ZERO;
        while(numCandies.compareTo(n) < 0){
            BigInteger[] numDaysTillCanBuyRet = p.subtract(numCandies).divideAndRemainder(m.multiply(w));
            BigInteger numDaysTillCanBuy = numDaysTillCanBuyRet[0];
            if(numDaysTillCanBuyRet[1].compareTo(BigInteger.ZERO) > 0){
                numDaysTillCanBuy = numDaysTillCanBuy.add(BigInteger.ONE);
            }
            if(numDaysTillCanBuy.compareTo(BigInteger.ZERO) > 0){
                currentDay = currentDay.add(numDaysTillCanBuy);
                numCandies = numCandies.add(numDaysTillCanBuy.multiply(m).multiply(w));
            }

            BigInteger toBuy = numCandies.divide(p);
            if(m.compareTo(w) < 0){
                BigInteger diff = w.subtract(m);
                if(diff.compareTo(BigInteger.ONE) > 0){
                    BigInteger remaining = toBuy.subtract(diff);
                    if(remaining.compareTo(BigInteger.ZERO) > 0){
                        m = m.add(diff);
                        toBuy = remaining;
                    }
                    else{
                        m = m.add(toBuy);
                        toBuy = BigInteger.ZERO;
                    }
                }
                BigInteger toBuyDivideTwo = toBuy.divide(BigInteger.valueOf(2));
                w = w.add(toBuyDivideTwo);
                m = m.add(toBuy.subtract(toBuyDivideTwo));
            }
            else{
                BigInteger diff = m.subtract(w);
                if(diff.compareTo(BigInteger.ONE) > 0){
                    BigInteger remaining = toBuy.subtract(diff);
                    if(remaining.compareTo(BigInteger.ZERO) > 0){
                        w = w.add(diff);
                        toBuy = remaining;
                    }
                    else{
                        w = w.add(toBuy);
                        toBuy = BigInteger.ZERO;
                    }
                }
                BigInteger toBuyDivideTwo = toBuy.divide(BigInteger.valueOf(2));
                m = m.add(toBuyDivideTwo);
                w = w.add(toBuy.subtract(toBuyDivideTwo));
            }
            numCandies = numCandies.subtract(numCandies.divide(p).multiply(p));
            numCandies = numCandies.add(m.multiply(w));
            currentDay = currentDay.add(BigInteger.ONE);
            if(numCandies.compareTo(n) > 0){
                minDays = minDays.min(currentDay);
            }
            else{
                BigInteger[] totalDaysRet = n.subtract(numCandies).divideAndRemainder(m.multiply(w));
                BigInteger totalDays = currentDay.add(totalDaysRet[0]);
                if(totalDaysRet[1].compareTo(BigInteger.ZERO) > 0){
                    totalDays = totalDays.add(BigInteger.ONE);
                }
                minDays = minDays.min(totalDays);
            }
        }
        return minDays.longValue();
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter(FileDescriptor.out);
        //FileWriter fileWriter = new FileWriter(System.getenv("OUTPUT_PATH"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        String[] mwpn = scanner.nextLine().split(" ");

        long m = Long.parseLong(mwpn[0]);

        long w = Long.parseLong(mwpn[1]);

        long p = Long.parseLong(mwpn[2]);

        long n = Long.parseLong(mwpn[3]);

        long result = minimumPasses(m, w, p, n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

