import java.io.*;
import java.util.*;

public class Solution {

    static int[][] map;
    static boolean isArrived = false; 
    static int answer = -1;

    // 원래 가던 방향이 아니라면, 반드시 방향전환!
    static int move(int startPoint) {
        // 위(-1,0) , 왼(0,-1), 오(0,1)
        int[] dx = {-1,0,0};
        int[] dy = {0,-1,1};
        boolean[][] visited = new boolean[100][100];
        int col = startPoint;
        int row = 99;
        int new_col, new_row;
        int direction = 0;
        visited[row][col] = true;
        
        while (true) {
            // while 종료 조건
            if (row==0) {
                return col;
            }
            for (int plus=0; plus<3; plus++) {
                // direction과 같다면 continue;
                if (direction == plus) continue;
                // direction과 다른 갈 수 있는 길이 있다면 방향 전환
                new_row = row + dx[plus];
                new_col = col + dy[plus];
                // map 벗어나면 continue;
                if (new_row < 0 || new_col < 0 || new_col >= 100) 
                    continue;
                // 방문한 적이 있다면 continue;
                if (visited[new_row][new_col]) continue;
                // 갈 수 있는 길이라면 방향 전환
                if (map[new_row][new_col]==1) {
                    direction = plus;
                }
            }
            row = row + dx[direction];
            col = col + dy[direction];
            visited[row][col] = true;
        }
    }
    public static void main(String[] args) throws IOException{
        map = new int[100][100];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        for (int testCase=0; testCase<10; testCase++) {
            int T = Integer.parseInt(br.readLine());
            int startPoint = -1;
            for (int row=0; row<99; row++) {
                String[] values = br.readLine().split(" ");
                for (int col=0; col<100; col++) {
                    map[row][col] = Integer.parseInt(values[col]);
                }
            }
            String[] values = br.readLine().split(" ");
            for (int col=0; col<100; col++) {
                map[99][col] = Integer.parseInt(values[col]);
                if (map[99][col]==2) {
                    startPoint = col;
                }
            }
            sb.append("#"+T+" "+move(startPoint)+"\n");
        }
        System.out.println(sb);

    }
}