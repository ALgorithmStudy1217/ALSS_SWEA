/*
#SWEA 3234. 준환이의 양팔저울
N개의 서로 다른 무게 추를 양팔저울에 올린다.
양팔저울에 무게 추를 올리는 순서는 N!이며, 왼쪽과 오른쪽에 올릴지까지 해서 총 2^N * N!의 경우가 생긴다.
이 때, 무게 추를 올리는 과정에서 오른쪽 무게가 왼쪽 무게보다 커져서는 안된다.
이런 방법으로 무게추를 올리는 방법은 총 몇 가지인지 구해라.

#입력
첫째 줄: 테스트 케이스의 수 T
    =각 테스트 케이스
    첫번째 줄: N (1 <= N <= 9)
    두번째 줄: 무게추 N개, 공백으로 구분 (1이상 999이하)
#출력
"#Ti [가능한 경우의 수]"

#로직
백트래킹을 이용하여 모든 경우의 수를 계산한다.
이 때, 반환하는 조건은 다음과 같다.
    1. 오른쪽 무게가 왼쪽 무게보다 커졌을 때
    2. 모든 무게 추를 달았을 때 -> 정답도 증가
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int testCases;
    static int weightCount;
    static int[] weights;
    static boolean[] visited;
    static int answer;

    public static void main(String args[]) throws IOException {
        testCases = Integer.parseInt(input.readLine());

        for (int test_case = 1; test_case <= testCases; test_case++) {
            //입력 및 초기화
            weightCount = Integer.parseInt(input.readLine());
            weights = new int[weightCount];
            st = new StringTokenizer(input.readLine().trim());
            for (int idx = 0; idx < weightCount; idx++) {
                weights[idx] = Integer.parseInt(st.nextToken());
            }
            answer = 0;

            //로직
            for (int idx = 0; idx < weightCount; idx++) {
                visited = new boolean[weightCount];
                visited[idx] = true;
                permutation(0, weights[idx], 0, weights, visited);
                permutation(0, 0, weights[idx], weights, visited);
            }

            //출력
            output.write(String.format("#%d %d\n", test_case, answer));
        }

        output.flush();
        output.close();
    }

    private static void permutation(int depth, int leftWeight, int rightWeight, int[] weights, boolean[] visited) {
        //조건
        if (rightWeight > leftWeight)
            return;
        if (depth == weights.length - 1) {
            answer++;
            return;
        }

        //반복
        for (int nextIdx = 0; nextIdx < weights.length; nextIdx++) {
            if (visited[nextIdx])
                continue;

            visited[nextIdx] = true;
            permutation(depth + 1, leftWeight + weights[nextIdx], rightWeight, weights, visited);
            permutation(depth + 1, leftWeight, rightWeight + weights[nextIdx], weights, visited);
            visited[nextIdx] = false;
        }
    }
}
