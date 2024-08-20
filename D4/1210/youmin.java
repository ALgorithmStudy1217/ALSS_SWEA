package swea;

/*
 * 1. 100x100 배열에 숫자 입력받기
 * 	1-1. 도착지점 찾기
 * 2. 도착지점에서 출발하여 현재 row가 0이 될때까지 반복
 * 	2-1. 왼쪽, 오른쪽, 위 중 왼/오의 우선순위가 위보다 높으므로 방향 배열의 앞에 두고 방향을 찾음
 * 	2-2. 방향을 찾았으면 이미 지나온 곳은 -1로 바꾸고 현재 위치를 변경함
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T=10;
		int dx[]= {0,0,-1};	//왼쪽, 오른쪽, 위 순서대로 탐색
		int dy[]= {-1,1,0};
		
		for(int i=1; i<=T; i++) {
			int test_case=Integer.parseInt(br.readLine());
			int [][] ladder=new int[100][100];
			int currentRow = -1;
			int currentCol = -1;

			for(int row=0; row<100; row++) {
				String[] str=br.readLine().split(" ");
				for(int col=0; col<100; col++) {
					int inputNum=Integer.parseInt(str[col]);
					
					ladder[row][col]=inputNum;
					if(inputNum==2) {	//도착지점 구하기
						currentCol=col;
						currentRow=row;	//x:도착행, y:도착열
					}
				}
				
			}
			
			 //도착지점부터 거꾸로 올라가기
            while(currentRow!=0) {
                for(int idx=0; idx<3; idx++) {
                    int newRow=dx[idx]+currentRow;
                    int newCol=dy[idx]+currentCol;
                    if(newRow<100 && newRow>=0 && newCol<100 && newCol>=0 && ladder[newRow][newCol]==1 ) {
                        ladder[currentRow][currentCol]=-1;
                        currentRow=newRow;
                        currentCol=newCol;
                        break;
                    }
                }
                 
            }
            System.out.println("#"+test_case+" "+currentCol);
		}
	}

}

