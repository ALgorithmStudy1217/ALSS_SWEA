

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine().trim());

        for(int t = 1; t<= T; t++) {
            int N = Integer.parseInt(bf.readLine().trim());

            int[][] board = new int[N][N];
            for(int i = 0; i< N; i++)   {
                st = new StringTokenizer(bf.readLine().trim());
                for(int j = 0; j<N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int wrong = 0;
            boolean isChange = false;
            for(int i = N-1; i>0;i--) {
                //맞는 값일 때, 이미 전치 되어있으면 다시 전치해야함
                if(board[0][i] == i+1 && isChange) {
                    wrong++;
                    isChange = false;

                //틀린 값일 때, 전치 되어있지 않아야 전치를 해야 정렬됨
                } else if(board[0][i] != i+1 && !isChange) {
                    wrong++;
                    isChange = true;
                }

            }
            System.out.println(wrong);
        }
    }
}
