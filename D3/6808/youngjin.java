import java.util.*;
import java.io.*;

public class Solution {

    static int[] gyuyoung;
    static int[] inyoung;
    static boolean[] selected;
    // 규영이가 이긴 횟수
    static int count;
    static int entireCount = 362880;

    static void permuation(int value, int order) {
        // 재귀 종료 조건
        if (order == 9) {
            // 승리 | 실패 결정
            int gyu = 0;
            int in = 0;
            for (int idx=0; idx<9; idx++) {
                if (gyuyoung[idx]<inyoung[idx]) in += gyuyoung[idx]+inyoung[idx];
                else if (gyuyoung[idx]>inyoung[idx]) gyu += gyuyoung[idx]+inyoung[idx];
            }
            if (gyu > in) count++;
            else if (gyu==in) entireCount--;
            return;
        }
        // 방문처리
        for (int next=1; next<=18; next++) {
            // 이미 선택된 값
            if (selected[next]) continue;
            // 선택되지 않은 값 -> 재귀 돌리기
            selected[next] = true;
            // 다음 값 저장
            inyoung[order] = next;
            permuation(next, order+1);
            selected[next] = false;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int test_case=1; test_case<=testCase; test_case++) {
            // 인영이의 순서
            gyuyoung = new int[9];
            inyoung = new int[9];
            selected = new boolean[19];
            int order = 0;
            count = 0;
            entireCount = 362880;

            String[] cards = br.readLine().split(" ");
            for (int idx=0; idx<9; idx++) {
                gyuyoung[idx]=Integer.parseInt(cards[idx]);
                selected[gyuyoung[idx]] = true;
            }
            for (int value=1; value<=18; value++) {
                // 방문한 적이 없다면, 순열 시작!
                if (!selected[value]) {
                    inyoung[order] = value;
                    selected[value] = true;
                    permuation(value, order+1);
                    selected[value] = false;
                }
            }
            sb.append("#"+test_case+" "+count+" "+(entireCount-count)+"\n");
        }
        System.out.println(sb);
    }
}