import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    	public static int[] cal(int num) {	//점의 좌표 계산
		int tmp=0;
		int i=0;
		while(tmp<num) {
			i++;
			tmp+=i;
		}
		int px=i-(tmp-num);
		int py=1+tmp-num;
		int[] coord= {px,py};
		return coord;
	}
    
	public static void main(String args[]) throws Exception
	{
		
     		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
			for(int test_case = 1; test_case <= T; test_case++)
		{
		
			int p,q;
			p=sc.nextInt();
			q=sc.nextInt();
			int [] pCoord=cal(p);
			int [] qCoord=cal(q);
			
			int nx=pCoord[0]+qCoord[0];	//덧셈한 점의 x,y 좌표 계산
			int ny=pCoord[1]+qCoord[1];
			
			int sum=0;
			for(int idx=1; idx<nx+ny-1; idx++) {
				sum+=idx;
			}
			sum+=nx;
			
			System.out.println("#"+test_case+" "+sum);
		}
	}
}