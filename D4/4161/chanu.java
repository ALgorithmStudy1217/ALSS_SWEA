import java.util.*;
import java.io.*;

/*
완전 탐색
1. 흰 색을 맨 위에서부터 한 칸씩 늘려본다.
2. 파란 색 또한 맨 위에서부터 한 칸씩 늘려본다.
3. 매번 구분된 영역에서 바뀌어야 할 영역의 개수를 구한다.
 */

class Solution {
    static int T, N, M;
    static char[][] flag;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int answer = 100000;
            N = Integer.parseInt(st.nextToken().trim());
            M = Integer.parseInt(st.nextToken().trim());
            flag = new char[N][M];

            for(int i = 0; i < N; i ++){
                flag[i] = br.readLine().toCharArray();
            }

            // 흰 색으로 덮되 맨 밑에 두 줄은 덮으면 안 됨
            for(int whiteRow = 0; whiteRow < N - 2; whiteRow++){
                for(int blueRow = whiteRow + 1; blueRow < N - 1; blueRow ++){
                    int changeWhite = checkArea('W', 0, whiteRow);
                    int changeBlue = checkArea('B', whiteRow + 1, blueRow);
                    int changeRed = checkArea('R', blueRow + 1, N - 1);

                    answer = Math.min(answer, changeWhite + changeBlue + changeRed);
                }
            }
            System.out.printf("#%d %d%n", t, answer);
        }
    }

    // 시작 행과 끝 행을 받아 바꿔야 할 문자가 몇 개인지 계산
    public static int checkArea(char target, int start, int end){
        int count = 0;

        for(int n = start; n <= end; n ++){
            for(int m = 0; m < M; m ++){
                if(flag[n][m] != target) {
                    count += 1;
                }
            }
        }
        return count;
    }
}
