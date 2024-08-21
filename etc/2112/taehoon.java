import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
 
/*
 * 필름 두께 D, 가로 크기 W의 보호 필름
 * 각 셀들은 특성 A 또는 특성 B를 가짐
 * 합격기준 K
 * 약품 투입 -> 가로 줄을 모두 특성 A 또는 B로 변경시킴
 * 성능 검사 -> 세로 줄에 같은 특성이 K개이상 연속 존재
 * 
 * 제약 사항
 * 3<=D<=13
 * 1<=W<=20
 * 1<=K<=D
 * 
 * 입력
 * 테스트 케이스 T
 * 보호 필름 두께 D, 가로 크기 W, 합격기준 K
 * D줄에 보호 필름 단면 정보 -> 특성 W개
 * 
 * 1. 보호 필름 두께 D, 가로 크기 W, 합격기준 K, D*W의 보호 필름 단면 정보 입력
 * 2. 약품을 사용하는 방법을 부분 조합을 통해 완전탐색
 *      2-1. 현재 사용한 약품보다 많은 경우 종료
 *      2-2. 한 줄이 성능 검사 실패 시 종료
 *      2-3. 약품을 사용안하는 경우
 *      2-4. 필름 정보 복사
 *      2-5. 0 또는 1의 약품 사용
 *      2-6. 복사했던 정보로 다시 초기화
 * 3. 최소 약품 사용 횟수 출력
 * */
 
class Solution {
    static Scanner sc = new Scanner(System.in);
 
    static int[][] filmInfo; // 필름의 특성 저장
    static int minCnt; // 최소 약품 사용 횟수
    static int ROW; // 보호필름 두께 D
    static int COL; // 셀들의 특성 W
    static int correctCondition; // 합격기준 K
 
    // 1. 보호 필름 두께 D, 가로 크기 W, 합격기준 K, D*W의 보호 필름 단면 정보 입력
    static void input() {
        ROW = sc.nextInt();
        COL = sc.nextInt();
        filmInfo = new int[ROW][COL];
        correctCondition = sc.nextInt();
        for (int row = 0; row < ROW; row++) {
            for (int col = 0; col < COL; col++) {
                filmInfo[row][col] = sc.nextInt();
            }
        }
    }
 
    // 2. 약품을 사용하는 방법을 부분 조합을 통해 완전탐색
    static void powerSet(int selectRow, int medicineCnt) {
        // 2-2. 한 줄이 성능 검사 실패 시 종료
        if (check()) {
            minCnt = Math.min(minCnt, medicineCnt);
            return;
        }
        if (selectRow == ROW) // 부분 조합 종료
            return;
        // 2-1. 현재 사용한 약품보다 많은 경우 종료
        if (medicineCnt >= minCnt)
            return;
 
        // 2-3. 약품을 사용안하는 경우
        powerSet(selectRow + 1, medicineCnt);
        // 2-4. 필름 정보 복사
        int[] copyFilmInfo = new int[COL]; // 약품을 사용했을 때 돌리기 위한 copy배열 생성
        for (int col = 0; col < COL; col++) {
            copyFilmInfo[col] = filmInfo[selectRow][col];
        }
        // 2-5. 0 또는 1의 약품 사용
        useMedicine(selectRow, 0);
        powerSet(selectRow + 1, medicineCnt + 1);
        useMedicine(selectRow, 1);
        powerSet(selectRow + 1, medicineCnt + 1);
 
        // 2-6. 복사했던 정보로 다시 초기화
        for (int col = 0; col < COL; col++) {
            filmInfo[selectRow][col] = copyFilmInfo[col];
        }
    }
 
    // 2-2. 한 줄이 성능 검사 실패 시 종료
    static boolean check() {
        for (int col = 0; col < COL; col++) {
            int cur = 0;
            boolean isPossible = false;
            // 특성 A가 연속으로 존재하면 infoSum==0일 시 true, 특성 B가 연속으로 존재하면 infoSum==K이면 true
            int infoSum = 0;
            for (int row = 0; row < ROW; row++) {
                infoSum += filmInfo[row][col];
                if (row - cur == correctCondition - 1) {
                    if (infoSum == 0 || infoSum == correctCondition) {
                        isPossible = true;
                        break;
                    }
                    infoSum -= filmInfo[cur][col];
                    cur += 1;
                }
            }
            if (!isPossible)
                return false;
        }
        return true;
    }
 
    // 2-4. 0 또는 1의 약품 사용
    static void useMedicine(int selectRow, int type) {
        for (int col = 0; col < COL; col++) {
            filmInfo[selectRow][col] = type;
        }
    }
 
    public static void main(String args[]) throws Exception {
 
        int T;
        T = sc.nextInt();
 
        for (int test_case = 1; test_case <= T; test_case++) {
            minCnt = 14; // 최소 약품 사용 횟수 초기화 (K<=13)
            input();
            if (check()) { // 약품을 사용하지 않아도 되는 경우
                minCnt = 0;
            } else {
                powerSet(0, 0);
            }
            System.out.println("#" + test_case + " " + minCnt);
        }
    }
}
