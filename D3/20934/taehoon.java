import java.util.Scanner;
import java.io.FileInputStream;

// 동전 앞뒤 확률이 1/2로 같음
// 컵을 교환할 때마다 방울 소리 -> K
// 현재 방울이 있을 확률이 가장 높은 컵의 위치 출력
// 단, 확률이 같은 컵이 여러 개 일 경우 가장 왼쪽
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 방울 위치
            String pos = sc.next();
            // 방울이 울린 수
            int K = sc.nextInt();
            int ans = 0;
            // 방울이 가장 왼쪽에 있는 경우
            if(pos.equals("o..")){
                // 동전의 앞뒤 확률이 같기 때문에 아래 조건이 가능
            	if(K%2==0){ // 확률이 같은 컵이 여러 개인 경우 가장 왼쪽 출력
                    // 따라서 여러 번 컵을 돌려도 왼쪽에 있을 확률이 가장 큼
                    ans = 0;
                }
                else{ // 홀수 번인 경우 가운데에 있을 확률이 가장 큼
                    ans = 1;
                }
            }
            else if(pos.equals(".o.")){
            	if(K%2==0){
                	ans = 1;
                }
                else{
                    ans = 0;
                }
            }
            else{
            	if(K==0){
                	ans = 2;
                }
                // 가장 왼쪽, 가장 오른쪽에 존재할 확률이 같으므로 가장 왼쪽 출력
                else if(K%2==0){
                	ans = 0;
                }
                else
                    ans=1;
            }
			System.out.println("#"+test_case+ " " + ans);
		}
	}
}
