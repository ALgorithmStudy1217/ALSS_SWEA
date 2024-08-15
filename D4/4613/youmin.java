import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
   	 static int n,m;
	static char [][] flag;
	
	//새로 칠해야 하는 칸 개수 구하는 함수
	public static int calcPaint(int wb, int br){
		int change=0;
		for(int idxW=0; idxW<wb; idxW++) {	//흰색x 개수
			for(int col=0; col<m; col++) {
				if(flag[idxW][col]!='W') {
					change+=1;
				}
			}
		}
		for(int idxB=wb; idxB<br; idxB++) {	//파란색x 개수
			for(int col=0; col<m; col++) {
				if(flag[idxB][col]!='B') {
					change+=1;
				}
			}
		}
		for(int idxR=br; idxR<n; idxR++) {	//빨간색x 개수
			for(int col=0; col<m; col++) {
				if(flag[idxR][col]!='R') {
					change+=1;
				}
			}
		}
		return change;
	}
    
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
			n=sc.nextInt();
			m=sc.nextInt();
			flag=new char[n][m];
			for(int row=0; row<n; row++) {
				String s=sc.next();
				for(int idx=0; idx<m; idx++) {
					flag[row][idx]=s.charAt(idx);	
				}
			}
			int result=n*m;
			
			//wb:w와 b의 경계 br: b와 r의 경계
			for(int wb=1; wb<n-1; wb++) {	
				for(int br=wb+1; br<n; br++) {
					int temp=calcPaint(wb,br);
					if(result>temp) {
						result=temp;
					}
				}
			}
			System.out.println("#"+test_case+" "+result);
		}
	}
}