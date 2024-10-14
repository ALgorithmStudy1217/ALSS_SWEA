package Programmers;

public class Programmers_사라지는발판 {

	static class Solution {

		int minCnt;

		int[][] copyBoard(int[][] board) {
			int[][] newBoard = new int[board.length][board[0].length];
			for (int row = 0; row < board.length; ++row) {
				for (int col = 0; col < board[0].length; ++col) {
					newBoard[row][col] = board[row][col];
				}
			}
			return newBoard;
		}

		int[] ROW_DELTA = { -1, 1, 0, 0 };
		int[] COL_DELTA = { 0, 0, -1, 1 };

		int rowSize;
		int colSize;

		public boolean inRange(int row, int col) {
			return row >= 0 && row < rowSize && col >= 0 && col < colSize;
		}

		public void print(int[][] board, int aRow, int aCol, int bRow, int bCol) {
			for (int row = 0; row < rowSize; ++row) {
				for (int col = 0; col < colSize; ++col) {
					if (row == aRow && col == aCol) {
						System.out.print("A ");
					} else if (row == bRow && col == bCol) {
						System.out.print("B ");
					} else {
						System.out.print(board[row][col] + " ");
					}
				}
				System.out.println();
			}
			System.out.println("------------------------------");
		}

		public int APlay(int[][] board, int aRow, int aCol, int bRow, int bCol, int count) {

			// print(board, aRow, aCol, bRow, bCol);

			if (board[bRow][bCol] == 0) {
				return count;
			}
			if (board[aRow][aCol] == 0) {
				return -count;
			}

			int result = 0;
			int ret = 0;

			for (int dir = 0; dir < ROW_DELTA.length; ++dir) {
				int newRow = aRow + ROW_DELTA[dir];
				int newCol = aCol + COL_DELTA[dir];

				if (!inRange(newRow, newCol)) {
					continue;
				}
				if (board[newRow][newCol] == 0) {
					continue;
				}
				board[aRow][aCol] = 0;
				result = BPlay(board, newRow, newCol, bRow, bCol, count + 1);
				board[aRow][aCol] = 1;

				if (ret == 0) {
					ret = result;
				} else {
					
					// b가 필승일 때
					if (ret < 0 && result < 0) {
						// b가 이기는 경우 최대한 늦게 져야지
						ret = Math.min(ret, result);
					} else if (ret > 0 && result > 0){
						// a가 이기는 경우 최대한 빨리 이겨야지
						ret = Math.min(ret, result);
					} else {
						ret = Math.max(ret, result);
					}
				}
			}

			if (ret == 0) {
				return -count;
			}

			return ret;
		}

		public int BPlay(int[][] board, int aRow, int aCol, int bRow, int bCol, int count) {

			// print(board, aRow, aCol, bRow, bCol);
			if (board[aRow][aCol] == 0) {
				return -count;
			}

			if (board[bRow][bCol] == 0) {
				return count;
			}

			int result = 0;
			int ret = 0;
			for (int dir = 0; dir < ROW_DELTA.length; ++dir) {
				int newRow = bRow + ROW_DELTA[dir];
				int newCol = bCol + COL_DELTA[dir];

				if (!inRange(newRow, newCol)) {
					continue;
				}
				if (board[newRow][newCol] == 0) {
					continue;
				}
				board[bRow][bCol] = 0;
				result = APlay(board, aRow, aCol, newRow, newCol, count + 1);
				board[bRow][bCol] = 1;

				if (ret == 0) {
					ret = result;
				} else {
					// a가 필승인 경우 늦게 지기
					if (ret > 0 && result > 0) {
						ret = Math.max(ret, result);
						// b가 필승인 경우 최대한 빨리 이기기
					} else if (ret < 0 && result < 0) {
						ret = Math.max(ret, result);
					} else {
						ret = Math.min(ret, result);
					}
				}
			}
			if (ret == 0) {
				return count;
			}
			return ret;
		}

		public int solution(int[][] board, int[] aloc, int[] bloc) {
			int answer = -1;

			rowSize = board.length;
			colSize = board[0].length;

			answer = APlay(board, aloc[0], aloc[1], bloc[0], bloc[1], 0);

			return Math.abs(answer);
		}
	}

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(Math
				.abs(solution.solution(new int[][] { { 1, 1, 1, 0 }, { 1, 1, 0, 1 }, { 1, 0, 1, 1 }, { 0, 1, 1, 1 } },
						new int[] { 0, 0 }, new int[] { 3, 3 })));
	}

}
