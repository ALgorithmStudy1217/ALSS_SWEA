import java.util.Scanner;

// SWEA1210. Ladder1

/*
 * 어느 사다리를 고르면 X표시에 도착하게 되는지
 * 출발점 x=0 및 x=9 세로 방향의 두 막대 사이에 임의의 개수의 막대들이 랜덤 간격으로 추가
 * 가로 방향의 선들이 또한 랜덤하게 연결
 * 
 * 100x100 크기의 2차원 배열로 주어진 사다리에 대해서 
 * 지정된 도착점에 대응되는 출발점 X를 반환
 */

public class Solution {
	public static final int SIZE = 100;
	public static int startCol = 0;

	// 도착점에서 사다리를 위로 타고 올라가는 함수
	public static void findStart(int[][] ladder, int row, int col) {
		if (row == 1) {
			// row == 1이라면 출발지점에 도착한 것이다.
			startCol = col;
			return;
		}
		
		// 좌 우 가로 방향 길이 있는지 확인한다
		if (ladder[row][col - 1] == 1) {
			// 좌측으로 길이 있다면 길이 없을때가지 이동
			while (ladder[row][col - 1] == 1) {
				col--;
			}
		} else if (ladder[row][col + 1] == 1) {
			// 우측으로 길이 있다면 길이 없을때까지 이동
			while (ladder[row][col + 1] == 1) {
				col++;
			}
		} 
		// 위로 한칸 이동한다
		row--;
		
		findStart(ladder ,row, col);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int totalCase = 10;

		for (int cnt = 0; cnt < totalCase; ++cnt) {
			int testCase = scanner.nextInt();
			// 사다리 밖을 0으로 감싸게 +2만큼 크게
			int[][] ladder = new int[SIZE + 2][SIZE + 2];

			int goalRow = SIZE - 1;
			int goalCol = 0;

			for (int row = 1; row <= SIZE - 1; ++row) {
				for (int col = 1; col <= SIZE; ++col) {
					ladder[row][col] = scanner.nextInt();
				}
			}
			for (int col = 1; col <= SIZE; ++col) {
				ladder[SIZE][col] = scanner.nextInt();
				if (ladder[SIZE][col] == 2) {
					goalCol = col;
				}
			}
			
			// 출발점을 찾는다
			findStart(ladder, goalRow, goalCol);
			// 문제는 0~99 이고 1~100으로 구했으므로 1을 빼준다
			System.out.println("#"+testCase+" "+(startCol-1));
		}
	}
}
