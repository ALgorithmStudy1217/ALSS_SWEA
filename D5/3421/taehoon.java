import java.util.Scanner;
/*
 * 1번부터 N번까지 순서대로 번호가 매겨짐
 * N은 최대 20
 * 궁합이 맞지 않는 재료 존재
 * 만들 수 있는 버거의 종류
 * 부분 집합 이용
 * */

public class Solution {
	// 궁합이 맞지 않는 재료 저장
	static int[][] badComb = new int[21][21];
	// 재료를 사용했는지 여부
	static boolean[] visited = new boolean[21];
	// 재료 개수
	static int cnt;
	// 가짓 수
	static int combCnt;

	// 부분 집합 이용
	static void powerSet(int selectIdx) {
		// 선택이 완료된 경우
		if (selectIdx == cnt + 1) {
			for (int idx = 1; idx <= cnt; idx++) {
				if(!visited[idx]) // 사용하지 않은 경우 패스
					continue;
				for(int check=idx+1;check<=cnt;check++) {
					if(!visited[check]) // 사용하지 않은 경우 패스
						continue;
					if(badComb[idx][check]==1) // 궁합이 맞지 않은 경우
						return;
				}
			}
			// 조합이 완성된 경우
			combCnt++;
			return;
		}
		// 재료를 사용
		visited[selectIdx] = true;
		powerSet(selectIdx+1);
		
		// 재료를 사용하지 않은 경우
		visited[selectIdx] = false;
		powerSet(selectIdx+1);
	}

	public static void main(String args[]) throws Exception {

		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			// 초기화
			combCnt = 0; 
			for(int row=0;row<21;row++) {
				for(int col=0;col<21;col++) {
					badComb[row][col] = 0;
				}
			}
			
			// 재료 개수 입력
			cnt = sc.nextInt();
			// 궁합이 맞지 않는 재료 개수 입력
			int badCnt = sc.nextInt();
			for (int idx = 0; idx < badCnt; idx++) {
				// 궁합이 맞지 않는 번호 입력
				int num1 = sc.nextInt();
				int num2 = sc.nextInt();
				// 0일 때, 궁합 O / 1일 때, 궁합 X
				badComb[num1][num2] = 1;
				badComb[num2][num1] = 1;
			}
			// 부분 집합 생성
			powerSet(1);

			// 출력
			System.out.println("#" + test_case + " " + combCnt);
		}
	}

}
