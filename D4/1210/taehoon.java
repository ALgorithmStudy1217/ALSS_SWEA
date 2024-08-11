import java.util.Scanner;

/*
 * 출발점 x = 0~9
 * 도착점 2
 * 입력 : 100 * 100 배열
 * 출력 : #test_case 당첨되는 위치
 * */
public class Solution {

	// 경계선을 넘는지 체크
	static boolean inRange(int row, int col) {
		if (row >= 0 && row < 100 && col >= 0 && col < 100)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			// 테스트 케이스의 번호
			int Num = sc.nextInt();
			// 입력 배열
			int[][] inpArr = new int[100][100];
			// 이미 지나온 곳은 돌아가지 않음
			boolean[][] visited = new boolean[100][100];
			// 반대로 생각해서 목표 지점에서 출발점으로 가도록 구현
			int goalX = 0, goalY = 0;

			// 입력
			for (int row = 0; row < 100; row++) {
				for (int col = 0; col < 100; col++) {
					inpArr[row][col] = sc.nextInt();
					if (inpArr[row][col] == 2) { // 당첨 지역 저장
						goalX = row;
						goalY = col;
					}
				}
			}

			// 좌표가 0에 도착하면 해당 위치가 당첨된 위치
			while (goalX != 0) {
				// 방문
				visited[goalX][goalY] = true;
				// 좌, 우 먼저 확인 후 아래로 이동
				if (inRange(goalX, goalY - 1) && inpArr[goalX][goalY - 1] == 1 && !visited[goalX][goalY - 1]) {
					goalY -= 1;
				} else if (inRange(goalX, goalY + 1) && inpArr[goalX][goalY + 1] == 1 && !visited[goalX][goalY + 1]) {
					goalY += 1;
				} else {
					goalX -= 1;
				}
			}

			System.out.println("#" + Num + " " + goalY);
		}
	}
}
