import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
			int m = sc.nextInt();
			int isOn = 1; // on : 1, off : 0
			
			//10진수에서 2진수 변환은 0이 될 때까지 2로 나누어 나오는 나머지의 reverse
			//문제에서 마지막 n개 비트를 물어보니까 reverse 안하고 연산 시  0인지 바로 판단
			for(int cnt = 1; cnt <= n; cnt++) {
				if(m % 2 == 0) { //n 범위 내에 0이 있으면 OFF 판단
					isOn = 0;
					break;
				}
				m /= 2;
				
				//m을 이진수로 변환했을 때 자릿수가 n보다 작을 경우를 고려
				if(m <= 0) {
					if(cnt != n && cnt <= 4) { //최소 4bit는 있어야 10진수를 표현 -> 최소비트만 존재 시 다 채우지 못하면 0
						isOn = 0;
					}
					break;
				}
			}
			
			if(isOn == 1) {
				System.out.printf("#%d %s\n", test_case, "ON");
			}
			else {
				System.out.printf("#%d %s\n", test_case, "OFF");
			}
		}
	}
}