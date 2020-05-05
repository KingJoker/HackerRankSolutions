/*
Harold is a kidnapper who wrote a ransom note, but now he is worried it will be traced back to him through his handwriting.
He found a magazine and wants to know if he can cut out whole words from it and use them to create an untraceable replica of his ransom note.
The words in his note are case-sensitive and he must use only whole words available in the magazine.
He cannot use substrings or concatenation to create the words he needs.
Given the words in the magazine and the words in the ransom note, print Yes if he can replicate his ransom note exactly using whole words from the magazine; otherwise, print No.
For example, the note is "Attack at dawn". The magazine contains only "attack at dawn". The magazine has all the right words, but there's a case mismatch. The answer is No.
*Function Description
Complete the checkMagazine function in the editor below. It must print Yes if the note can be formed using the magazine, or No.
*checkMagazine has the following parameters:
magazine: an array of strings, each a word in the magazine
note: an array of strings, each a word in the ransom note
*Input Format
The first line contains two space-separated integers, m and n, the numbers of words in the magazine and the note..
The second line contains m space-separated strings, each magazine[i].
The third line contains n space-separated strings, each note[i].
*Constraints
1<=m,n<=30000
1<=|magazine[i]|,|note[i]|<=5
Each word consists of English alphabetic letters (i.e., a to z and A to Z).
*Output Format
Print Yes if he can use the magazine to create an untraceable replica of his ransom note. Otherwise, print No.
*Sample Input 0
6 4
give me one grand today night
give one grand today
*Sample Output 0
Yes
*Sample Input 1
6 5
two times three is not four
two times two is four
*Sample Output 1
No
*Explanation 1
'two' only occurs once in the magazine.
*Sample Input 2
7 4
ive got a lovely bunch of coconuts
ive got some coconuts
*Sample Output 2
No
*Explanation 2
Harold's magazine is missing the word some.
 */

package DictionairesAndHashmaps.Easy;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RansomNote {

    // Complete the checkMagazine function below.
    static void checkMagazine(String[] magazine, String[] note) {
        HashMap<String, Integer> magazineMap = new HashMap<>();
        HashMap<String, Integer> noteMap = new HashMap<>();
        for(String word : magazine){
            magazineMap.merge(word,1,(oldValue, one) -> oldValue + 1);
        }
        for(String word : note){
            noteMap.merge(word,1,(oldValue, one) -> oldValue + 1);
        }
        for(String word : noteMap.keySet()){
            int magazineCount = magazineMap.getOrDefault(word,0);
            int noteCount = noteMap.get(word);
            if(magazineCount < noteCount){
                System.out.println("No");
                return;
            }
        }
        System.out.println("Yes");
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] mn = scanner.nextLine().split(" ");

        int m = Integer.parseInt(mn[0]);

        int n = Integer.parseInt(mn[1]);

        String[] magazine = new String[m];

        String[] magazineItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            String magazineItem = magazineItems[i];
            magazine[i] = magazineItem;
        }

        String[] note = new String[n];

        String[] noteItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            String noteItem = noteItems[i];
            note[i] = noteItem;
        }

        checkMagazine(magazine, note);

        scanner.close();
    }
}

