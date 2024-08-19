import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// SWEA 2105 디저트 카페

/*
 * 디저트 카페 투어 계획
 * 한 변의 길이가 N인 정사각형 모양을 가진 지역에 디저트 카페가 모여 있다.
 * 원 안의 숫자는 해당 디저트 카페에서 팔고 이쓴 디저트의 종류
 * 카페들 사이에는 대각선 방향으로 움직일 수 있는 길들이 있다.
 * 디저트 카페 투어는 어느 한 카페에서 출발하여
 * 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야한다.
 * 카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안 된다.
 * 하나의 카페에서 디저트를 먹는 것도 안된다.
 * 왔던 길을 다시 돌아가는 것도 안된다.
 * 되도록 많이 먹으려고 한다.
 * 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력
 * 만약, 디저트를 먹을 수 없는 경우 -1
 * 
 * 3초
 * 한변의 길이 4~20
 * 디저트 종류 1~100
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;

	static int size;
	static Cafe[][] cafes;

	static void init() throws IOException {
		maxCount = 0;
		
		size = Integer.parseInt(br.readLine().trim());
		cafes = new Cafe[size][size];

		int dessert = 0;
		for (int rowIndex = 0; rowIndex < size; ++rowIndex) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIndex = 0; colIndex < size; ++colIndex) {
				dessert = Integer.parseInt(st.nextToken());
				cafes[rowIndex][colIndex] = new Cafe(rowIndex, colIndex, dessert);
			}
		}
	}

	public static class Cafe {
		int row;
		int col;
		int dessert;

		public Cafe(int row, int col, int dessert) {
			this.row = row;
			this.col = col;
			this.dessert = dessert;
		}
		
		@Override
		public boolean equals(Object other) {
			return (this.dessert == ((Cafe)other).dessert);
		}
	}

	static final int[] ROW_DELTA = { 1, 1, -1, -1 };
	static final int[] COL_DELTA = { -1, 1, 1, -1 };

	static int startRow;
	static int startCol;

	public static void makeTour() {
		for (startRow = 0; startRow < size; ++startRow) {
			for (startCol = 0; startCol < size; ++startCol) {
				selectDesserts = new ArrayList<>();
				selectDesserts.add(cafes[startRow][startCol]);
				tour(startRow + ROW_DELTA[0], startCol + COL_DELTA[0], 0, 1);
			}
		}
	}

	static List<Cafe> selectDesserts;
	static int maxCount;

	public static void tour(int row, int col, int direction, int count) {
		// 시작위치로 돌아왔다면 계산
		if (row == startRow && col == startCol) {
			//System.out.println("count" + count);
			//System.out.println("start" + startRow + ",  " + startCol);
			//System.out.println();
			maxCount = Math.max(maxCount, count);
			return;
		}

		// 범위 밖으로 나가면 종료
		if (row < 0 || row >= size || col < 0 || col >= size) {
			return;
		}

		// 이미 해당 디저트 가게를 방문했다면 종료
		if (selectDesserts.contains(cafes[row][col])) {
			return;
		}

		selectDesserts.add(cafes[row][col]);
		
		// 한번 더 해당 방향으로 가기
		tour(row + ROW_DELTA[direction], col + COL_DELTA[direction], direction, count + 1);
		// 다른 방향으로 꺾기
		if (direction != 3) {
			direction = direction + 1;
			tour(row + ROW_DELTA[direction], col + COL_DELTA[direction], direction, count + 1);
		}
		
		selectDesserts.remove(cafes[row][col]);
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));

		int totalCase = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= totalCase; ++testCase) {
			init();

			makeTour();
			if (maxCount == 0) {
				maxCount = -1;
			}
			System.out.println("#"+testCase+" "+maxCount);
		}
	}
}
