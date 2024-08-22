import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// SWEA 1953 탈주범 검거

/*
 * 탈주범은 탈출한 지 한 시간 뒤, 맨홀 뚜껑을 통해 지하터널의 어느 한 지점으로 들어깠음
 * 터널끼리 연결이 되어 있는 경우 이동이 가능하므로 탈주범이 있을 수 있는 위치의 개수를 계산하여야 한다.
 * 탈주범은 시간당 1의 거리를 움직일 수 있다.
 * 
 * 1 상하좌우 터널
 * 2 상하터널
 * 3 좌우 터널
 * 4 상우터널
 * 5 하우터널
 * 6 하좌터널
 * 7 상좌 터널
 * 
 * 최대 50개 테케 1초 안에
 * 지하 터널 지도의 세로크기 가로크기 N M 각각 5~50
 * 뚜겅의 세로위치 R은 0이상 N-1 이하, 가로 C는 0이상 M-1d이하
 * 탈출 후 소요된 시간 L은 1이상 20이하
 * 반드시 1개 이상의 터널이 있음이 보장
 * 맨홀 뚜껑은 항상 터널이 있는 위치에 존재
 * 
 * 1. 총 테스트 케이스를 입력받고
 * 2. 각 테스트 케이스에 대해
 *  2-1. 지하터널의 세로, 가로, 맨홀뚜껑 세로, 가로위치, 소요시간
 *  2-2. 터널 정보 입력 0은 터널x 1~7 구조물
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize;
	static int colSize;
	static int startRow;
	static int startCol;
	static int elapsedTime;
	static int[][] map;

	static int fugitiveNum;

	public static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		startRow = Integer.parseInt(st.nextToken());
		startCol = Integer.parseInt(st.nextToken());
		elapsedTime = Integer.parseInt(st.nextToken());

		map = new int[rowSize][colSize];
		for (int rowIndex = 0; rowIndex < rowSize; ++rowIndex) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIndex = 0; colIndex < colSize; ++colIndex) {
				map[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}

		visited = new boolean[rowSize][colSize];
		fugitiveNum = 0;
	}

	public static final int[][] ROW_DELTA = { {}, { -1, 1, 0, 0 }, { -1, 1, 0, 0 }, { 0, 0, 0, 0 }, { -1, 0, 0, 0 },
			{ 0, 1, 0, 0 }, { 0, 1, 0, 0 }, { -1, 0, 0, 0 } };
	public static final int[][] COL_DELTA = { {}, { 0, 0, -1, 1 }, { 0, 0, 0, 0 }, { 0, 0, -1, 1 }, { 0, 0, 0, 1 },
			{ 0, 0, 0, 1 }, { 0, 0, -1, 0 }, { 0, 0, -1, 0 } };

	public static final int NO_TUNNEL = 0;
	public static final int UDLR_TUNNEL = 1;
	public static final int UD_TUNNEL = 2;
	public static final int LR_TUNNEL = 3;
	public static final int UR_TUNNEL = 4;
	public static final int DR_TUNNEL = 5;
	public static final int DL_TUNNEL = 6;
	public static final int UL_TUNNEL = 7;

	public static final int UP = 0;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = 3;

	static class Position {
		int row;
		int col;
		int elapsedTime;

		public Position(int row, int col, int elapsedTime) {
			this.row = row;
			this.col = col;
			this.elapsedTime = elapsedTime;
		}
	}

	static boolean[][] visited;

	/*
	 * 2 7은 위에서 만나면 연결되고 아래서 만나면 연결이 안되자나...
	 */

	public static void move() {
		Queue<Position> fugitive = new ArrayDeque<>();
		visited[startRow][startCol] = true;
		fugitiveNum = 1;
		fugitive.offer(new Position(startRow, startCol, 1));
		while (!fugitive.isEmpty()) {
			Position curFugitive = fugitive.poll();
			int tunnelType = map[curFugitive.row][curFugitive.col];
			// 탈출 소요시간이 모두 지났으면 더이상 탐색하지 않음
			if (curFugitive.elapsedTime == elapsedTime) {
				continue;
			}
			for (int deltaIndex = 0; deltaIndex < ROW_DELTA[tunnelType].length; ++deltaIndex) {
				int nextRow = curFugitive.row + ROW_DELTA[tunnelType][deltaIndex];
				int nextCol = curFugitive.col + COL_DELTA[tunnelType][deltaIndex];
				// 맵 밖으로 나가는 경우 더이상탐색하지 않음
				if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
					continue;
				}
				int nextTunnelType = map[nextRow][nextCol];
				// 터널이 없으면 이동하지 못함
				if (nextTunnelType == NO_TUNNEL) {
					continue;
				}
				// 이미 방문한 터널이면 이동하지 않는다.
				if (visited[nextRow][nextCol]) {
					continue;
				}
				// 연결되지 않는 터널이면 이동하지 못함
				if (!canMove(tunnelType, nextTunnelType, deltaIndex)) {
					continue;
				}
				visited[nextRow][nextCol] = true;
				fugitive.offer(new Position(nextRow, nextCol, curFugitive.elapsedTime + 1));
				fugitiveNum++;
			}
		}
	}

	public static boolean canMove(int tunnelType, int nextTunnelType, int deltaIndex) {
		switch (deltaIndex) {
		case UP:
			if ((canMoveFlag[tunnelType] & 1 << UP) == 0) {
				return false;
			}
			if ((canMoveFlag[nextTunnelType] & 1 << DOWN) == 0) {
				return false;
			}
			return true;
		case DOWN:
			if ((canMoveFlag[tunnelType] & 1 << DOWN) == 0) {
				return false;
			}
			if ((canMoveFlag[nextTunnelType] & 1 << UP) == 0) {
				return false;
			}
			return true;
		case LEFT:
			if ((canMoveFlag[tunnelType] & 1 << LEFT) == 0) {
				return false;
			}
			if ((canMoveFlag[nextTunnelType] & 1 << RIGHT) == 0) {
				return false;
			}
			return true;
		default:
			if ((canMoveFlag[tunnelType] & 1 << RIGHT) == 0) {
				return false;
			}
			if ((canMoveFlag[nextTunnelType] & 1 << LEFT) == 0) {
				return false;
			}
			return true;
		}
	}

	static byte[] canMoveFlag;

	static void initMoveFlag() {
		canMoveFlag = new byte[8];
		canMoveFlag[UDLR_TUNNEL] = 0b1111;
		canMoveFlag[UD_TUNNEL] = 0b0011;
		canMoveFlag[LR_TUNNEL] = 0b1100;
		canMoveFlag[UR_TUNNEL] = 0b1001;
		canMoveFlag[DR_TUNNEL] = 0b1010;
		canMoveFlag[DL_TUNNEL] = 0b0110;
		canMoveFlag[UL_TUNNEL] = 0b0101;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int totalCase = Integer.parseInt(br.readLine().trim());

		initMoveFlag();
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			init();
			move();
			System.out.println("#" + testCase + " " + fugitiveNum);
		}
	}

}
