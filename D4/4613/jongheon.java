/*
#SWEA 4613. 러시아 국기 같은 깃발
NxM 크기 깃발의 각 칸에 흰색, 파란색, 빨간색 중 하나가 칠해져 있다.
몇 개의 칸을 다시 칠해 러시아 국기처럼 만들려고 한다.
다음의 조건을 만족해야 한다
    위에서 한 줄 이상은 모두 흰색으로,
    다음 몇 줄(한 줄 이상)은 모두 파란색으로,
    나머지 줄(한 줄 이상)은 모두 빨간색으로 칠해져 있어야 한다.
이 조건을 만족하는 깃발 중 새로 칠해야 하는 칸의 개수의 최솟값을 구해라.

#입력
첫째 줄: 테스트 케이스의 수 T
각 테스트 케이스 첫번째 줄: 깃발의 가로, 세로 크기 N, M(3 <= N, M <= 50)
다음 N개의 줄: M개의 문자로 이루어진 문자열
    'W' = 흰색, 'B' = 파란색, 'R' = 빨간색
#출력
"#Ti 최솟값"

#로직
깃발의 각 줄을 칠해진 색의 수로 각각 보관한다.
dfs로직으로 러시아 같은 국기인지 확인
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
    static final char WHITE = 'W', BLUE = 'B', RED = 'R';
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int testCases;
    static int flagRow, flagCol;
    static Line[] flag;
    static int answer;

    public static class Line {
        int lineNo;
        int white;
        int blue;
        int red;

        public Line(int lineNo, String line) {
            this.lineNo = lineNo;
            for (int idx = 0; idx < line.length(); idx++) {
                char cur = line.charAt(idx);
                if (cur == WHITE) white++;
                if (cur == BLUE) blue++;
                if (cur == RED) red++;
            }
        }

        public int paintWhite() {
            return blue + red;
        }

        public int paintBlue() {
            return white + red;
        }

        public int paintRed() {
            return white + blue;
        }
    }

    public static void dfs(int lineNo, char color, int paintedCells) {
        //종료
        if (lineNo == flagRow) {
            answer = Math.min(answer, paintedCells);
            return;
        }

//        //로직
        switch (color) {
            case WHITE:
                paintedCells += flag[lineNo].paintWhite();
                break;
            case BLUE:
                paintedCells += flag[lineNo].paintBlue();
                break;
            case RED:
                paintedCells += flag[lineNo].paintRed();
                break;
        }

        //반복
        if (color == WHITE) {
            if (lineNo + 1 < flagRow - 2) //적어도 밑에서 두번째 줄은 파란색
                dfs(lineNo + 1, WHITE, paintedCells);
            dfs(lineNo + 1, BLUE, paintedCells);
        }
        else if (color == BLUE) {
            if (lineNo + 1 < flagRow - 1) //적어도 마지막 줄은 빨간색
                dfs(lineNo + 1, BLUE, paintedCells);
            dfs(lineNo + 1, RED, paintedCells);
        }
        else if (color == RED) {
            dfs(lineNo + 1, RED, paintedCells);
        }
    }

    public static void main(String args[]) throws IOException {
        testCases = Integer.parseInt(input.readLine());
        for(int test_case = 1; test_case <= testCases; test_case++) {
            //입력
            st = new StringTokenizer(input.readLine().trim());
            flagRow = Integer.parseInt(st.nextToken());
            flagCol = Integer.parseInt(st.nextToken());

            flag = new Line[flagRow];
            for (int idx = 0; idx < flagRow; idx++)
                flag[idx] = new Line(idx, input.readLine());

            //로직
            answer = Integer.MAX_VALUE;
            dfs(0, WHITE, 0);

            //출력
            System.out.printf("#%d %d\n", test_case, answer);
        }
    }
}
