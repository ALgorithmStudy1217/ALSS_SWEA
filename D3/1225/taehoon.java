import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.io.FileInputStream;

/*
 * testcase 10개 고정
 * 주어진 수 8개
 * -1, -2, -3, ... 순으로 감소
 * 0보다 작아지는 경우 그 순간의 8개 숫자들이 암호가 됨
 * 입력 : test_case 번호, 8개 숫자
 * */

class Solution
{
	public static void main(String args[]) throws Exception
	{
        
		Scanner sc = new Scanner(System.in);
		int T = 10;

		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 테스트케이스 번호 입력
			int testNum = sc.nextInt();
			// 큐를 통해 8개의 수 관리
			Queue<Integer> inpQ = new LinkedList<>();
			// 8개 수 입력
			for(int cnt=0;cnt<8;cnt++) {
				int inputNum = sc.nextInt();
				inpQ.add(inputNum);
			}
			
			int curCnt = 1;
			// 0이 될때까지 반복
			while(true) {
				// 큐의 헤드
				int cur = inpQ.remove();
				// 현재 수와 뺼셈 수행
				cur -= curCnt;
				
				// 0보다 작거나 같은 경우 종료
				if(cur<=0) {
					cur=0;
					inpQ.add(cur);
					break;
				}
				// 그 외에는 큐의 뒤에 삽입
				inpQ.add(cur);
                curCnt++;
                // 한 사이클 -> 1~5
                if(curCnt>5){
                	curCnt=1;
                }
			}
			
			// 순서대로 출력
			System.out.print("#"+ testNum + " ");
			while(!inpQ.isEmpty()) {
				System.out.print(inpQ.remove() + " ");
			}
			System.out.println();
		}
	}
}
