import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

		for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int	N=sc.nextInt();
			int arr[][]=new int[N+1][N+1];
			for(int row=1; row<=N; row++) {
				for(int col=1; col<=N; col++) {
					arr[row][col]=sc.nextInt();
				}
			}
			
			int trans=0;
			int flag=0;	//이전 줄 체크 변수
			for(int idx=N; idx>1; idx--) {
				if(arr[1][idx]!=idx & flag==0) {	//이전 줄: trans X, 현재 줄: trans 필요
					trans+=1;
					flag=1;
				}
				if(arr[1][idx]==idx & flag==1) {	//이전 줄: trans O, 현재 줄: trans 필요 x -> 다시 trans 필요 
					trans+=1;	
					flag=0;
				}
			}
			System.out.println(trans);

		}
	}
}