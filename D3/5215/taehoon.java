import java.util.Scanner;

/*
 * 햄버거 조합
 * 정해진 칼로리 중 최대 점수
 * 같은 재료 여러 번 사용 불가
 * 입력 : 재료 수, 제한 칼로리
 * 점수와 칼로리
 * 출력 : 가장 높은 점수
 * */
public class Solution {

	// 재료 개수
	static int cnt;
	// 칼로리를 저장할 배열
	static int[] kalArr = new int[21];
	// 점수를 저장할 배열
	static int[] score = new int[21];
	// 최대 칼로리
	static int limitKal;
	// 최대 점수
	static int maxScore;

	// 조합을 통해 만들 수 있는 햄버거 생성
	static void combination(int selectIdx, int scoreSum, int kalSum) {
		// 제한 칼로리를 넘는 경우
		if (kalSum > limitKal)
			return;
		// 모두 고른 경우
		if (selectIdx == cnt) {
			if (kalSum <= limitKal) {
				// 최대 점수 저장
				maxScore = Math.max(maxScore, scoreSum);
			}
			return;
		}

		// 해당 인덱스 사용
		combination(selectIdx + 1, scoreSum + score[selectIdx], kalSum + kalArr[selectIdx]);

		// 사용 X
		combination(selectIdx + 1, scoreSum, kalSum);
	}

	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			// 초기화
			maxScore = -1;

			// 재료 개수 입력
			cnt = sc.nextInt();
			// 제한 칼로리 입력
			limitKal = sc.nextInt();

			for (int idx = 0; idx < cnt; idx++) {
				// 맛 점수 입력
				score[idx] = sc.nextInt();
				// 칼로리 입력
				kalArr[idx] = sc.nextInt();
			}
			// 입력받은 재료들을 통해 조합 생성
			combination(0, 0, 0);
			System.out.println("#" + test_case + " " + maxScore);
		}
	}

}
