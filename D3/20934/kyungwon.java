

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;



    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine().trim());


        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(bf.readLine().trim());
            String cup =st.nextToken();
            int k = Integer.parseInt(st.nextToken());


            int game = 1;
            if(cup.equals("o..")) game = 0;
            else if(cup.equals(".o.")) game = 1;
            else game = 2;

            if(k == 0) {
                System.out.println("#"+t + " " + game);
            } else {
                if(game == 0 || game == 2) {
                    if(k % 2 == 0) System.out.println("#"+t + " " + 0);
                    else System.out.println("#"+t + " " + 1);
                } else if(game == 1) {
                    if(k % 2 == 0) System.out.println("#"+t + " " + 1);
                    else System.out.println("#"+t + " " + 0);
                }    }
            

        }
    }
}
