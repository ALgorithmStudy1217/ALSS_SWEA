import java.util.*;
import java.io.*;
 
 
class Solution {
    static int T;
    static int answer;
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
    static void recur(int[] pends, int left, int right, int amount){
        if(amount == 0){
            answer ++;
            return;
        }
 
        for(int idx = 0; idx < pends.length; idx ++){
            if (pends[idx] != 0) {
                int weight = pends[idx];
                pends[idx] = 0;
                recur(pends, left + weight, right, amount - 1);
 
                if (right + weight <= left) {
                    recur(pends, left, right + weight, amount - 1);
                }
 
                pends[idx] = weight;
            }
        }
    }
 
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            answer = 0;
            int[] pends = new int[N];
 
            for (int i = 0; i < N; i++) {
                pends[i] = Integer.parseInt(st.nextToken());
            }
 
            recur(pends, 0, 0, N);
            System.out.println("#" + t + " " + answer);
        }
    }
}