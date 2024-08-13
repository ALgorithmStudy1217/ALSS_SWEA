import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;
    static int win;
    static int lose;
    static int[] input = new int[9];
    static int[] card = new int[19];
    static boolean[] used = new boolean[19];
     
     
    public static void game(int selectIdx, int score) {
         
        if(selectIdx == 9) {
             
            if(score > 85) {
                win++;
            } else lose++;
        }
         
        for(int elementIdx = 1; elementIdx < 19; elementIdx++) {
            if(card[elementIdx] == 1) continue;
            if(used[elementIdx]) continue;
             
            used[elementIdx] = true;
            if(input[selectIdx] > elementIdx) game(selectIdx+1, score+input[selectIdx] + elementIdx);
            else game(selectIdx+1, score);
            used[elementIdx] = false;
             
        }
         
    }
     
    public static void main(String[] args) throws NumberFormatException, IOException {
         
        bf = new BufferedReader(new InputStreamReader(System.in));
         
        int Test = Integer.parseInt(bf.readLine().trim());
         
        for(int tcase = 1; tcase<=Test; tcase++) {
             
            st = new StringTokenizer(bf.readLine().trim());
             
            win = 0; 
            lose = 0;
            for(int idx = 0; idx<19; idx++) {
                card[idx] = 0;
                used[idx] = false;
            }
             
            for(int card_idx = 0; card_idx<9; card_idx++) {
                int n = Integer.parseInt(st.nextToken());
                input[card_idx] = n;
                card[n] = 1;
                 
            }
             
             
             
            game(0, 0);
             
             
            System.out.println("#" + tcase + " " + win + " " + lose);
             
        }
     
     
    }
}
