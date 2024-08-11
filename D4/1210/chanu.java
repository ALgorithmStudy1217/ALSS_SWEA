import java.util.*;
import java.io.*;

class Solution {
    static int[][] map;
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int iterNum = 1; iterNum <= 10; iterNum++){
            int tc = Integer.parseInt(br.readLine().trim());
            map = new int[100][100];
            int ansX = 0;
            int ansY = 0;
            answer = 0;

            for(int row = 0; row < 100; row ++){
                st = new StringTokenizer(br.readLine());
                for(int col = 0; col < 100; col ++){
                    int token = Integer.parseInt(st.nextToken().trim());
                    map[row][col] = token;
                    // 2일 때 좌표를 저장
                    if(token == 2){
                        ansX = row;
                        ansY = col;
                    }
                }
            }

            // 시작점을 미리 넣어줌
            map[ansX][ansY] = 0;
            int result = dfs(ansX, ansY);
            sb.append("#").append(tc).append(' ').append(result);
            if(iterNum != 10){
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    // 맨 뒤에서부터 dfs를 수행해 정답을 역으로 계산
    static int dfs(int curX, int curY){
        // 정답 좌표를 구했으므로 return
        if((curX == 0)){
            return curY;
        }

        // 왼쪽 방문 가능하면 방문하고 map을 0으로 만들어 재방문 방지
        if(inRange(curX, curY - 1) && map[curX][curY - 1] == 1){
            map[curX][curY - 1] = 0;
            return dfs(curX, curY - 1);  // return으로 재귀를 호출해서 이 코드 아래로는 실행을 시키지 않음
        }

        // 오른쪽 탐색
        if(inRange(curX, curY + 1) && map[curX][curY + 1] == 1){
            map[curX][curY + 1] = 0;
            return dfs(curX, curY + 1);
        }

        // 왼쪽, 오른쪽 모두 방문 불가하면 위로 올라감
        map[curX - 1][curY] = 0;
        return dfs(curX - 1, curY);

    }

    static boolean inRange(int row, int col){
        return 0 <= row && row < 100 && 0 <= col && col < 100;
    }
}
