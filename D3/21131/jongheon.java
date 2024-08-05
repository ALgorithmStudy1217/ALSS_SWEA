/*
#SWEA 21131. 행렬정렬
정렬 가능한 N*N 행렬에 각 원소는 1~N*N 사이의 자연수로 모두 서로 다르다.
이 때, (1,1)부터 (x,x)까지의 부분행렬의 전치가 가능할 때,
정렬하기 위한 최소 연산 수를 구해라.

#입력
첫째 줄: 테스트 케이스의 수 T
    =각 테스트 케이스
    첫번째 줄: N (4 <= N <= 64)
    두번째 줄부터 N개의 줄: 각 행렬의 원소
#출력
"[정렬하는데 필요한 최소 연산 수]"

#로직
1행2열부터 1행N-1열까지의 값을 검사하며, 전치가 필요한지 결정한다.
다음 값과 연속하지 않으면 전치가 필요한 것이다.
    헤당 값이 원래 1행의 값이었다면 다음 값과 1의 차이가 나야 하며,
    해당 값이 원래 1열의 값이었다면 다음 값이 N의 차이가 나야 한다.
단, 1행N열은 N이 아니면 전치가 필요한 것이다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

class Solution {
    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static int testCases;
    static int matrixSize;
    static int[] firstRow;
    static int transposeCount;

    public static void main(String args[]) throws IOException {
        testCases = Integer.parseInt(input.readLine());
        for (int test_case = 1; test_case <= testCases; test_case++) {
            //입력 및 초기화
            transposeCount = 0;

            matrixSize = Integer.parseInt(input.readLine());
            firstRow = Stream.of(input.readLine().trim().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            for (int idx = 1; idx < matrixSize; idx++) {
                input.readLine(); //1행 외에는 필요 없으므로 저장 X
            }

            //로직
            //2열부터 N열까지 전치가 필요한지 검사
            for (int col = 1; col < matrixSize - 1; col++) {
                int gap = firstRow[col + 1] - firstRow[col];
                if (!(gap == 1 || gap == matrixSize))
                    transposeCount++;
            }
            //N열 전치가 필요한지 검사
            if (firstRow[matrixSize - 1] != matrixSize)
                transposeCount++;

            //출력
            System.out.println(transposeCount);
        }
    }
}
