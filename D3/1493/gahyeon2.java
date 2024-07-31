package programmers;

import java.util.*;

/*
 * p, q의 최대 입력값 = 10000
 * p + q의 최댓값 = 20000
 * 142*142 배열에 다 넣을 수 있음
 * 
 * 1. int[142][142] 배열에 값 다 넣음
 *    값 저장 방법 : 배열의 [0][0]애 좌표 (1, 1)의 값을 맞춰서 넣음
 *    대각선 수 만큼 반복, n번째 대각선 연산 시 배열의 row + col < n
 *    col이 0보다 작으면 다음 대각선으로, 저장할 수가 20000 초과 시 break
 * 2. 값을 입력 받고 좌표 찾기
 *    대각선 아래의 값으로 찾으려는 값이 있는 대각선을 찾고 그 대각선 탐색
 */

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		int arrLen = 142; //배열의 크기
		int crossLine = arrLen * 2 - 1; //대각선 수
		int[][] xyArr = new int[arrLen][arrLen]; //xy좌표에 맞게 값을 저장할 배열
		int col, row; //배열의 인덱스 값
		int num = 1; //증가하며 배열에 저장될 값
		
		//(좌표계 -> 배열  //90도 왼쪽 회전) 해당하는 좌표 인덱스에 값 저장
		//배열은 0부터 시작하므로 좌표와 1차이 있음 (1부터 시작하는 메모리 낭비 줄이기)
		for(int line = 0; line <= crossLine; line++) {
			col = line;
			row = 0;
			while(true) {
				if(col < 0 || num > 20000) { //col이 1보다 작으면 (?, 1)에 도달했다는 뜻
					break;
				}
				
				if(col >= arrLen || row >= arrLen) { //배열 범위보다 크면 저장x
					col--;
					row++;
					continue;
				}
				
				xyArr[row][col] = num;
				num++;
				col--; //다음 대각선 인덱스를 위해 col, row 업데이트
				row++;
			}
			
			if(num > 20000) { //20000까지만 저장 -> 그 이후 낭비는 줄임
				break;
			}
		}
		
		
		for(int test_case = 1; test_case <= T; test_case++){
			 //p, q값 입력 받기
			int p = sc.nextInt();
			int q = sc.nextInt();
			
			//p와 q 값을 가지는 각각의 x, y좌표값 구하기
			int px = -1, py = -1, qx = -1, qy = -1;
			//순차 탐색으로 찾으면 O(n*n)
			//좌표로 (n, 1) 라인은 n번째 좌표의 x값이 1+2+...+n인 것을 알 수 있음
			//대각선에는 해당하는 대각선의 가장 하단부 값보다 작은 값들이 있음
			//찾으려는 값이 (n, 1)의 값보다 작을 경우 해당 대각선번째의 요소들만 검사하면 됨
			//=> (n, 1)을 찾는 시간 n, (n, 1)의 대각선 요소들을 찾는 시간 n -> O(n)으로 줄일 수 있음
			
			//p, q의 값이 있는 대각선의 끝인 (n, 1) 찾기
			int pn = -1, qn = -1;
			for(int index = 0; index < arrLen; index++) {
				if(xyArr[index][0] >= p && pn == -1)
					pn = index;
				
				if(xyArr[index][0] >= q && qn == -1)
					qn = index;
				
				if(pn != -1 && qn != -1) //둘 다 값을 찾았을 시 탈출
					break;
			}
			
			//(pn, 1)에 해당하는 대각선 탐색 -> p찾기
			row = pn; //해당 대각선만 확인하면 돼서 반복 필요X
			col = 0;
			for(int index = 0; index <= pn; index++) {
				if(xyArr[row][col] == p) {
					px = row;
					py = col;
					break;
				}
				row--;
				col++;
				
				//if(row < 0) //대각선 n번째의 요소 수는 n이므로 for에서 탈출조건과 동일
				//	break;
			}
			
			//(qn, 1)에 해당하는 대각선 탐색 -> q찾기
			row = qn;
			col = 0;
			for(int index = 0; index <= qn; index++) {
				if(xyArr[row][col] == q) {
					qx = row;
					qy = col;
					break;
				}
				row--;
				col++;
			}

			//배열의 인덱스와 좌표값의 차이 = 1
			//px, qx, py, qy 다 좌표값보다 1 작으므로 둘을 더하면 -2 차이남 -> +1해주기
			int answer = xyArr[px+qx+1][py+qy+1];
			System.out.printf("#%d %d", test_case, answer);
		}
	}
}
