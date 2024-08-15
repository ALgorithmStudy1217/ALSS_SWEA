import java.util.Scanner;

// SWEA4613. 러시아 국기 같은 깃발

/*
 * 러시아 국기를 만들자
 * 오래된 깃발 N행 M열
 * 각 칸은 흰색 파란색 빨간색 중 하나로 칠해져 있다.
 * 몇 개의 칸에 있는 색을 다시 칠해서 이 깃발을 러시아 국기처럼 만들려고 한다.
 * 위에서 몇 줄(한줄이상)은 모두 흰색
 * 다음 몇 줄(한줄이상)은 모두 파란색
 * 나머지 줄(한 줄 이상)은 모두 빨간색으로 칠해져 있어야 함
 * 이렇게 러시아 국기 같은 깃발을 만들기 위해서 새로 칠해야 하는 칸의 개수의 최솟값을 구하여라.
 */

public class Solution {

	// 가장 적게 칠해진 칸을 확인할 변수
	static int minPaint = 2500;

	// 2번째 행부터 flagRow-1행까지 탐색할 DFS.
	// 깃발 모양, 현재 행, 전체 행 갯수, 전체 열 갯수, 현재 칠하고 있는 색, 지금 까지 칠한 칸의 갯수, 파랑이 들어갔는 지 여부
	static void DFS(char[][] flag, int currentRow, int flagRow, int flagCol, char currentColor, int paint,
			boolean isBluePrinted) {
		// 종료 조건. 마지막 칸은 무조건 빨강이니까 고려하지 않는다.
		if (currentRow == flagRow) {
			// 만약 파랑이 없다면 러시아 국기가 아님
			if (isBluePrinted == false) {
				return;
			}
			// 가장 적게 칠한 칸 수 갱신
			minPaint = Math.min(minPaint, paint);
			return;
		}

		// flag[currentRow].length를 하니까 에러가 뜬다..
		// col값도 가져오자
		for (int col = 1; col <= flagCol; ++col) {
			// 현재 색깔에 따라 계산이 달라진다
			if (flag[currentRow][col] != currentColor) {
				paint++;
			}
		}
		if (currentColor == 'W') {
			// 다음 행도 흰색
			DFS(flag, currentRow + 1, flagRow, flagCol, currentColor, paint, isBluePrinted);
			// 다음 행은 파랑색
			DFS(flag, currentRow + 1, flagRow, flagCol, 'B', paint, isBluePrinted);
		} else if (currentColor == 'B') {
			isBluePrinted = true;
			// 다음 행도 파랑색
			DFS(flag, currentRow + 1, flagRow, flagCol, currentColor, paint, isBluePrinted);
			// 다음 행은 빨강색
			DFS(flag, currentRow + 1, flagRow, flagCol, 'R', paint, isBluePrinted);
		} else {
			// 다음 행은 무조건 빨강색
			DFS(flag, currentRow + 1, flagRow, flagCol, currentColor, paint, isBluePrinted);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = scanner.nextInt();

		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			// 정답 초기화
			minPaint = 2500;

			int flagRow = scanner.nextInt();
			int flagCol = scanner.nextInt();

			// 1번 flagRow 행은 무조건 흰색 빨간색이어야 한다!
			// 2~flagRow-1 까지는 흰파빨이어야 한다.
			// 쭉 돌면서 흰색을 넣는다.
			// 파랑을 넣을지 말지 선택
			// 파랑을 넣고 나면 쭉 파랑을 넣는다
			// 빨강을 넣을지 말지 선택
			// 해서 최소값을 구한다.
			// 이때 파랑이 x면 안되니까 제외하자

			// 깃발을 생성한다. row col을 1부터 시작하기 위해 +1만큼 크게 생성
			char[][] flag = new char[flagRow + 1][flagCol + 1];
			for (int row = 1; row <= flagRow; ++row) {
				String currentRow = scanner.next();
				for (int col = 1; col <= flagCol; ++col) {
					flag[row][col] = currentRow.charAt(col - 1);
				}
			}

			// 깃발, 시작 행 2번, 전체 행 갯수, 시작 색깔 흰색, 0, false;
			DFS(flag, 2, flagRow, flagCol, 'W', 0, false);
			// 깃발, 시작 행 2번, 전체 행 갯수, 시작 색깔 파랑색, 0, true;
			DFS(flag, 2, flagRow, flagCol, 'B', 0, true);

			// 첫번째, 마지막 행까지 칠해주도록 하자
			for (int col = 1; col <= flagCol; ++col) {
				// 첫째 줄 확인
				if (flag[1][col] != 'W') {
					minPaint++;
				}
				// 마지막 줄 확인
				if (flag[flagRow][col] != 'R') {
					minPaint++;
				}
			}

			System.out.println("#" + testCase + " " + minPaint);
		}
	}
}
