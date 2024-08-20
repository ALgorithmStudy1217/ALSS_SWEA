import java.util.Scanner;

/*
 * 정사각형 N길이 지역에 디저트 카페가 모여있음
 * 각 지역의 숫자는 디저트의 종류
 * 대각선 방향으로 이동하고 사각형 모양을 그리며 출발한 카페로 돌아와야함
 * 카페 투어 중 같은 숫자의 디저트를 팔고 있는 카페가 존재하면 안됨
 * 하나의 카페에서 디저트를 먹는 것도 불가
 * 왔던 길 돌아오는 것 불가
 * 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수
 * 디저트를 먹을 수 없는 경우 -1
 * 
 * 1. 지역의 한 변 길이 N, 디저트 종류 입력
 * 2. 각 지역에서 카페 투어 시작
 * 	2-1. 오른쪽 아래 대각선으로 가지 못하는 경우 패스
 * 	2-2. 대각선을 오른쪽 아래로 생성
 * 	2-3. 더 갈 수 있는 경우와 왼쪽 아래로 가는 경우 분기
 * 	2-4. 가지 못하는 경우 종료
 * 	2-5. 더 갈 수 있는 경우와 왼쪽 위로 가는 경우 분기
 * 	2-6. 가지 못하는 경우 종료
 * 	2-7. 더 갈 수 있는 경우와 오른쪽 위로 가는 경우 분기
 * 	2-8. 가지 못하는 경우 종료
 * 	2-9. 원점으로 돌아오면 최대 값 갱신
 * 3. 최대값 출력
 * */

class Solution {
	static Scanner sc = new Scanner(System.in);

	static int[][] cafe;
	static int cafeSize;
	static boolean[] desert; // 디저트 종류 1~100
	static int[] dx = { 1, 1, -1, -1, 0 }; // 오른쪽 아래, 왼쪽 아래, 왼쪽 위, 오른쪽 위 순서
	static int[] dy = { 1, -1, -1, 1, 0 };
	static int maxDesert;
	static int standardRow; // 탐색을 시작한 row
	static int standardCol; // 탐색을 시작한 col

	// 경계 안에 있는지 체크
	static boolean inRange(int row, int col) {
		if (row >= 0 && row < cafeSize && col >= 0 && col < cafeSize)
			return true;
		return false;
	}

	// 1. 지역의 한 변 길이 N, 디저트 종류 입력
	static void input() {
		maxDesert = -1;
		cafeSize = sc.nextInt();
		cafe = new int[cafeSize][cafeSize];
		desert = new boolean[101];
		for (int row = 0; row < cafeSize; row++) {
			for (int col = 0; col < cafeSize; col++) {
				cafe[row][col] = sc.nextInt();
			}
		}
	}

	// 2. 각 지역에서 카페 투어 시작
	static void cafeTour() {
		for (int row = 0; row < cafeSize; row++) {
			for (int col = 1; col < cafeSize - 1; col++) {
				// 2-1. 오른쪽 아래 대각선으로 가지 못하는 경우 패스
				int nextRow = row + dx[0];
				int nextCol = col + dy[0];
				if (!inRange(nextRow, nextCol))
					continue;
				standardRow = row;
				standardCol = col;
				desert[cafe[row][col]] = true;
				move(nextRow, nextCol, 0, 1);
				desert[cafe[row][col]] = false;
			}
		}
	}

	static void move(int row, int col, int dir, int cnt) {
		if (dir == 5) // 더 이상 방향을 변경할 수 없는 경우 종료
			return;
		if (row == standardRow && col == standardCol) { // 원점으로 돌아온 경우 종료
			maxDesert = Math.max(maxDesert, cnt);
			return;
		}
		if (!inRange(row, col)) // 경계선 밖인 경우 종료
			return;
		if (desert[cafe[row][col]]) { // 이미 먹은 디저트인 경우 종료
			return;
		}
		desert[cafe[row][col]] = true;
		// 2-3. 더 갈 수 있는 경우와 대각선으로 가는 경우 분기
		move(row + dx[dir], col + dy[dir], dir, cnt + 1);
		move(row + dx[dir + 1], col + dy[dir + 1], dir + 1, cnt + 1);
		desert[cafe[row][col]] = false; // 초기화
	}

	public static void main(String args[]) throws Exception {

		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			input();
			cafeTour();
			System.out.println("#" + test_case + " " + maxDesert);
		}
	}
}
