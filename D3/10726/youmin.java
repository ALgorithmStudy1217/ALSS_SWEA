import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n,m;
			n=sc.nextInt();
			m=sc.nextInt();
			String bit="ON";
			
			for(int idx=0; idx<n; idx++) {	//n개 체크
				if(m%2==0) {
					bit="OFF";
					break;
				}else {
					m=m/2;
				}
			}
			
			System.out.println("#"+test_case+" "+bit);
		}
	}
}