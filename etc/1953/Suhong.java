package baekjoon.study17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 터널에서 한 시간에 1만큼 이동하는 탈주범의 L시간 뒤에 있을 수 있는 위치의 개수를 구하는 문제
 * 1. 테스트 케이스와 배열의 사이즈, 시작 위치(맨홀 위치), 위치의 개수를 구하려는 시간을 입력 받는다.
 * 2. 시작 위치부터 BFS를 이용해 탐색한다
 * 		2-1. 현 위치의 파이프의 모양에 따라 다음에 방문할 위치를 선정한다.
 * 		2-2. 이미 방문한 곳이거나 파이프가 없거나 연결이 불가능한 파이프일 경우 패스
 * 		2-3. 방문처리 후 큐에 저장한다.(그 위치의 방문처리는 0으로 처리)
 * 		2-4. 이때 정답을 출력할 answer 변수를 하나씩 늘린다.
 * 3. 시간이 1부터 L이 될 때까지 반복하여 L이 됐을 때 answer를 출력한다.
 * 		3-1. L-1 시간일 때 L시간에 갈 수 있는 곳의 개수를 이미 체크함으로 L-1일 때 작업이 종료됐을 때 answer를 출력한다.
 */
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int answer, map[][];

    // 상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    static int maxRow, maxCol, startRow, startCol, lastTime;

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        maxRow = Integer.parseInt(st.nextToken());
        maxCol = Integer.parseInt(st.nextToken());
        startRow = Integer.parseInt(st.nextToken());
        startCol = Integer.parseInt(st.nextToken());
        lastTime = Integer.parseInt(st.nextToken());

        map = new int[maxRow][maxCol];
        answer = 0;

        for (int row = 0; row < maxRow; row++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int col = 0; col < maxCol; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            input();
            sb.append("#").append(tc).append(" ").append(bfs()).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        int result = 0;
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { startRow, startCol, map[startRow][startCol] });
        map[startRow][startCol] = 0; // 방문 처리
        result++; // 있을 수 있는 위치 추가
        int nowTime = 1;

        // 정해진 시간만큼만 반복
        while(nowTime < lastTime) {
            int size = queue.size();

            // 각 시간별로 탐색할 위치 탐색
            while (--size >= 0) {
                int[] now = queue.poll();

                int row = now[0];
                int col = now[1];
                int pipe = now[2];

                // 현 위치의 파이프 모양을 기준으로 탐색해야할 방향 결정
                int[] directions = directionArr(pipe);

                // 이동 가능한 방향으로 탐색 시작
                for (int direction : directions) {
                    int nr = row + dy[direction];
                    int nc = col + dx[direction];

                    // 범위 밖이면
                    if (nr < 0 || nr >= maxRow || nc < 0 || nc >= maxCol)
                        continue;

                    // 파이프가 없거나 방문한 곳이면
                    if (map[nr][nc] == 0)
                        continue;

                    // 현재 방향에서 연결될 수 없는 파이프인 경우
                    if (!isPossibleConnect(direction, map[nr][nc]))
                        continue;

                    // 큐에 추가
                    queue.add(new int[] { nr, nc, map[nr][nc] });
                    map[nr][nc] = 0; // 방문 처리
                    result++; // 있을 수 있는 위치 추가

                }
            }
            nowTime++;
        }

        return result;
    }

    // 다음 파이프가 현재 바라보고 있는 방향의 반대 방향으로 구멍이 나있는 경우 연결이 가능한 파이프이다.
    private static boolean isPossibleConnect(int direction, int nextPipeNum) {
        int[] nextPipe = directionArr(nextPipeNum);

        // direction이 짝수면 +1, 홀수면 -1
        // 상하좌우 순서이기 때문에 위 과정을 거치면 정확히 반대방향을 가리킴
        if (direction % 2 == 0)
            direction++;
        else
            direction--;

        // 다음 파이프가 연결이 되는 지 확인
        for (int pipeDirection : nextPipe) {
            if (direction == pipeDirection)
                return true;
        }
        return false;
    }

    private static int[] directionArr(int pipe) {
        switch (pipe) {
            case 1: // 상하좌우
                return new int[] { 0, 1, 2, 3 };
            case 2: // 상하
                return new int[] { 0, 1 };
            case 3: // 좌우
                return new int[] { 2, 3 };
            case 4: // 상우
                return new int[] { 0, 3 };

            case 5: // 하우
                return new int[] { 1, 3 };

            case 6: // 하좌
                return new int[] { 1, 2 };

            default:// 상좌
                return new int[] { 0, 2 };
        }
    }

}