import java.io.*;
import java.util.*;

public class Solution {
    static int[][] matrix;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        StringTokenizer st;


        for(int t = 1; t <= T; t++){
            int N = Integer.parseInt(br.readLine().trim());
            matrix = new int[N][N];
            int answer = 0;

            for(int row = 0; row < N; row ++){
                st = new StringTokenizer(br.readLine());
                for(int col = 0; col < N; col ++){
                    matrix[row][col] = Integer.parseInt(st.nextToken().trim());
                }
            }

            while (true){
                int result = check(N);
                if(result != -1){
                    transpose(result);
                    answer ++;
                } else{
                    break;
                }
            }
            System.out.println("#" + t + " " + answer);
        }
    }

    static int check(int N){
        int max_ = -1;
        int num = 1;
        for(int row = 0; row < N; row ++){
            for(int col = 0; col < N; col ++){
                if(matrix[row][col] != num){
                    max_ = Math.max(max_, Math.max(row, col));
                }
                num++;
            }
        }
        return max_;
    }

    static void transpose(int x){
        int[][] tempArr = new int[x + 1][x + 1];

        for(int row = 0; row <= x; row ++){
            for(int col = 0; col <= x; col ++){
                tempArr[row][col] = matrix[col][row];
            }
        }
        for(int row = 0; row <= x; row ++){
            for(int col = 0; col <= x; col ++){
                matrix[row][col] = tempArr[row][col];
            }
        }
    }
}
