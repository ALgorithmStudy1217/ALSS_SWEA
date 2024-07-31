import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws IOException {
		
		final int MAX_SIZE = 1000 + 1;
		
		int[][] board = new int[MAX_SIZE][MAX_SIZE];
		
		int rowNum;
		
		board[MAX_SIZE-1][1] = 1;
		
		for (int row = 1; row < MAX_SIZE; row++) {
			board[MAX_SIZE-row-1][1] = board[MAX_SIZE-row][1] + row-1;
			
			rowNum = row+1;
			
			for (int col = 2; col < MAX_SIZE; col++) {
				board[MAX_SIZE-row-1][col] = board[MAX_SIZE-row-1][col-1] + rowNum;
				rowNum++;
			}
		}
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		int test_case = Integer.parseInt(st.nextToken());
		
		for (int tc = 1; tc < test_case+1; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
			boolean f1 = false, f2 = false;
			
			
			for (int row = 1; row < MAX_SIZE-1; row++) {

				for (int col = 1; col < MAX_SIZE; col++) {
					
					if (board[MAX_SIZE-row-1][col] == n1) {
						x1 = row;
						y1 = col;
						
						f1 = true;
					}
					
					if (board[MAX_SIZE-row-1][col] == n2) {
						x2 = row;
						y2 = col;
						
						f2 = true;
					}
					
					if (f1 && f2) break;
				}
			}
			
			//System.out.println(x1 + "," + y1 + "::" + x2 + "," + y2);
			
			int result = board[MAX_SIZE-(x1+x2)-1][y1+y2];
			
			System.out.println("#" + tc + " " + result);

			
		}


	}
	
	public static void print(int[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
	}
	

}
