package programmers;

import java.util.*;

/*
 * p, q의 최대 입력값 = 10000
 * p + q의 최댓값 = 20000
 * 142*142 배열에 다 넣을 수 있음
 * 
 * 1. 값 -> 좌표
 * 대각선 하단의 값은 1 + 2 + 3 + ... + n
 * 이 값이 찾으려는 수보다 커질 경우, 해당 대각선 라인 탐색
 * 해당 대각선 라인은 하단의 값보다 1씩 작아짐
 * -> 찾으려는 값과 같아지면 좌표 찾는 것
 * 
 * 2. 좌표 -> 값
 * (7,4)를 찾는다는 가정
 * (7,1)의 값 찾음 -> 1 + 2 + ... + n
 * y축으로 올라갈 때 -> x, x+1, x+2 ... 이런식으로 증가함
 */

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		int row, col;
		
		for(int test_case = 1; test_case <= T; test_case++){
			 //p, q값 입력 받기
			int p = sc.nextInt();
			int q = sc.nextInt();
			//p와 q 값을 가지는 각각의 x, y좌표값
			int px = -1, py = -1, qx = -1, qy = -1;
			int pn = -1, qn = -1;
			
			int num = 0; //계속 증가하는 하단값 계산
			int index = 1;
			while(true) {
				num += index;
				if(num >= p && px == -1) {
					pn = num;
					px = index;
				}
				
				if(num >= q && qy == -1) {
					qn = num;
					qx = index;
				}
				
				if(px != -1 && qx != -1)//둘 다 값을 찾았을 시 탈출
					break;
				index++;
			}
			
			//(px, 1)를 끝으로 하는 대각선 탐색 -> p찾기
			row = px; //해당 대각선만 확인하면 돼서 반복 필요X
			col = 1;
			num = pn;
			for(index = 1; index <= px; index++) { //대각선 n번째의 요소 수는 n이므로 px번 반복
				if(p == num) {
					px = row; //진짜 px, py
					py = col;
					break;
				}
				row--;
				col++;
				num--; //대각선 거슬러 올라갈수록 --
			}
			
			//(qx, 1)를 끝으로 하는 대각선 탐색 -> q찾기
			row = qx;
			col = 1;
			num = qn;
			for(index = 1; index <= qx; index++) { //대각선 n번째의 요소 수는 n이므로 qx번 반복
				if(q == num) {
					qx = row; //진짜 px, py
					qy = col;
					break;
				}
				row--;
				col++;
				num--; //대각선 거슬러 올라갈수록 --
			}
			
			//p값과 q값을 가지는 좌표 찾음
			//별 연산
			int starX = px + qx;
			int starY = py + qy;
			
			//별 연산으로 얻은 좌표에 해당하는 값
			//먼저 (starX, 1)의 값 찾기
			int starNumX = 0;
			for(index = 1; index <= starX; index++) {
				starNumX += index;
			}
			
			//(starX, 1)의 값을 이용해서(starX, starY) 좌표의 값 찾기
			//위로 올라갈수록 순차적으로 커지는 x좌표값을 (starX, 1)에더해주면 됨
			//starX = x좌표값
			//starNumX = (starX, 1)에 해당하는 값
			//index = 순차적으로 증가시켜주기 위한 값
			int starNum = starNumX; //별 연산으로 나온 좌표가 가리키는 곳의 값, 시작하는 곳(starX, 1)의 값 셋팅
			for(index = 0; index < starY - 1; index++) { //starY에 도달해서 멈추려면 -1 해줘야함
				starNum += (starX + index);
			}
			
			System.out.printf("#%d %d\n", test_case, starNum);
		}
	}
}
