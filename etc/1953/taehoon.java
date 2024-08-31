import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/*
 * 터널 구조물 타입에 따라 갈 수 있는 방향이 다름.
 * L시간 후 탈주범이 위치할 수 있는 장소의 개수 출력
 * 
 * 1. 터널지도 세로 크기 N, 가로크기 M, 맨홀 뚜껑이 위치한 장소 세로 R, 가로 C, 탈출 후  소요된 시간 L입력
 * 2. bfs를 통해 각 시간마다 해당 터널 구조물의 방향에 맞게 이동
 * 3. 방문한 위치의 수 출력
 * */

class Solution {
	static Scanner sc = new Scanner(System.in);

	static final int EMPTY = 0;
	static final int UDLR = 1;
	static final int UD = 2;
	static final int LR = 3;
	static final int UR = 4;
	static final int DR = 5;
	static final int DL = 6;
	static final int UL = 7;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int posCnt;

	static boolean inRange(int row, int col) {
		if (row >= 0 && row < rowSize && col >= 0 && col < colSize)
			return true;
		return false;
	}

	static class Pos {
		int row;
		int col;

		Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

	static int rowSize;
	static int colSize;
	static int holeRow;
	static int holeCol;
	static int runTime;
	static boolean[][] visited;
	static int[][] map;

	// 1. 터널지도 세로 크기 N, 가로크기 M, 맨홀 뚜껑이 위치한 장소 세로 R, 가로 C, 탈출 후 소요된 시간 L입력
	static void input() {
		posCnt = 1; // 맨홀 위치 포함
		rowSize = sc.nextInt();
		colSize = sc.nextInt();
		map = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		holeRow = sc.nextInt();
		holeCol = sc.nextInt();
		runTime = sc.nextInt();
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
				map[row][col] = sc.nextInt();
			}
		}
	}

	// 2. bfs를 통해 각 시간마다 해당 터널 구조물의 방향에 맞게 이동
	static void move() {
		Queue<Pos> que = new ArrayDeque<>();
		que.add(new Pos(holeRow, holeCol));
		visited[holeRow][holeCol] = true;

		for (int time = 1; time < runTime; time++) {
			int queLen = que.size();
			if (queLen == 0)
				break;
			for (int cnt = 0; cnt < queLen; cnt++) {
				Pos curPos = que.poll();
				int nextRow = 0;
				int nextCol = 0;
				switch (map[curPos.row][curPos.col]) {
				case UDLR:
					nextRow = curPos.row + dx[0];
					nextCol = curPos.col + dy[0];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && upCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[1];
					nextCol = curPos.col + dy[1];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && downCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[2];
					nextCol = curPos.col + dy[2];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && leftCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[3];
					nextCol = curPos.col + dy[3];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && rightCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				case UD:
					nextRow = curPos.row + dx[0];
					nextCol = curPos.col + dy[0];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && upCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[1];
					nextCol = curPos.col + dy[1];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && downCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				case LR:
					nextRow = curPos.row + dx[2];
					nextCol = curPos.col + dy[2];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && leftCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[3];
					nextCol = curPos.col + dy[3];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && rightCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				case UR:
					nextRow = curPos.row + dx[0];
					nextCol = curPos.col + dy[0];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && upCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[3];
					nextCol = curPos.col + dy[3];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && rightCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				case DR:
					nextRow = curPos.row + dx[3];
					nextCol = curPos.col + dy[3];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && rightCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[1];
					nextCol = curPos.col + dy[1];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && downCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				case DL:
					nextRow = curPos.row + dx[1];
					nextCol = curPos.col + dy[1];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && downCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[2];
					nextCol = curPos.col + dy[2];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && leftCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				case UL:
					nextRow = curPos.row + dx[2];
					nextCol = curPos.col + dy[2];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && leftCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					nextRow = curPos.row + dx[0];
					nextCol = curPos.col + dy[0];
					if (inRange(nextRow, nextCol) && !visited[nextRow][nextCol] && upCheck(nextRow, nextCol)) {
						visited[nextRow][nextCol] = true;
						posCnt++;
						que.add(new Pos(nextRow, nextCol));
					}
					break;
				}
			}
		}
	}

	// 윗방향 체크
	static boolean upCheck(int row, int col) {
		if (map[row][col] == 1 || map[row][col] == 2 || map[row][col] == 5 || map[row][col] == 6)
			return true;
		return false;
	}

	// 아래방향 체크
	static boolean downCheck(int row, int col) {
		if (map[row][col] == 1 || map[row][col] == 2 || map[row][col] == 4 || map[row][col] == 7)
			return true;
		return false;
	}

	// 왼쪽방향 체크
	static boolean leftCheck(int row, int col) {
		if (map[row][col] == 1 || map[row][col] == 3 || map[row][col] == 4 || map[row][col] == 5)
			return true;
		return false;
	}

	// 오른쪽방향 체크
	static boolean rightCheck(int row, int col) {
		if (map[row][col] == 1 || map[row][col] == 3 || map[row][col] == 6 || map[row][col] == 7)
			return true;
		return false;
	}

	public static void main(String args[]) throws Exception {
		int T;
		T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			input();
			move();
			System.out.println("#" + test_case + " " + posCnt);
		}
	}
}
