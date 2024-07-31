package programmers;

import java.util.*;

/*
 * 1. 배열에 점수 저장
 * 	   단, 1사분면처럼 사용하기 위해 인덱스를 좌표값에 맞게 설정
 * 	  0, 0 비우면 메모리 낭비 -> 인덱스값을 좌표값으로 사용할 때 +1 >>> 별 연산이 그냥 덧셈이라 무관
 * 2. p, q 입력 받고 해당하는 인덱스 찾기
 * 3. 인덱스값 = 좌표값, 인덱스 값을 이용해 별 연산 진행
 * 4. 별 연산으로 찾은 좌표에 해당하는 값 찾기
 */

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		int[][] xyArr = new int[5][5]; //좌표계를 대신할 배열
		int col, row; //배열 인덱스
		int num = 1; //배열에 저장할 값
		
		//(좌표계 -> 배열 //90도 왼쪽 회전) 해당하는 인덱스에 대각선으로 값 저장
		for(int line = 0; line <= 6; line++) {
			//대각선 인덱스 계산
			col = line;
			row = 0;
			while(true) {
				if(col < 0) { //col이 0보다 작으면 탈출
					break;
				}
				
				if(col > 3 || row > 3) { //배열 범위보다 크면 저장x
					col--;
					row++;
					continue;
				}
				
				xyArr[row + 1][col + 1] = num; //실제 좌표계처럼 1,1부터 시작
				num++; //다음 저장할 값
				col--; //다음 대각선 인덱스를 위해 col, row 업데이트
				row++;
			
				if(num == 11) { //왠지 모르겠지만 문제에 11이 없습니다.
					num++;
				}
			}
		}
		
		
		for(int test_case = 1; test_case <= T; test_case++){
			int pRow = -1, pCol = -1, qRow = -1, qCol = -1;
			int p = sc.nextInt();
			int q = sc.nextInt();
			
			//입력 받은 p값, q값과 같은 값을 가진 인덱스 찾기
			for(row = 1; row < xyArr.length; row++) {
				for(col = 1; col < xyArr.length; col++) {
					if(p == xyArr[row][col]) {
						pRow = row;
						pCol = col;
					}
					
					if(q == xyArr[row][col]) {
						qRow = row;
						qCol = col;
					}
				}
				if(qRow >= 0 && pRow >= 0) { //p, q에 해당하는 좌표 다 찾았는지? > 쓸데없는 반복 줄이기
					break;
				}
			}
			
			//별 연산 진행 후 출력
			int answer = xyArr[pRow + qRow][pCol + qCol];
			System.out.printf("#%d %d", test_case, answer);
		}
	}
}
