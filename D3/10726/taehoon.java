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

            // 정수 N, M
            int N, M; // 마지막 N개의 비트가 모두 켜져있으면 ON, 그 외 OFF
            String ans = "ON";
            N = sc.nextInt();
            M = sc.nextInt();
            // 2진수로 변환할 때, N번 동안 0이 나오면 OFF
            for(int cnt=1;cnt<=N;cnt++){
            	if(M%2==0)
                    ans="OFF";
                M/=2;
            }
            
            System.out.println("#"+test_case+" "+ans);
        }
    }
}
