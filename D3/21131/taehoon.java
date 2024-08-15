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
            // 배열 크기 N
            int N = sc.nextInt();
            // 배열 생성
            int [][] arr = new int[N][N];

            // 행렬 입력
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    arr[i][j]=sc.nextInt();
                }
            }

            // 행렬 전치가 발생했는지 여부
            boolean isChanged = false;
            // 전치 발생 횟수
            int ans = 0;
            // 정렬이 가능한 경우만 주기 때문에
            // 가장 큰 정사각형에서 가장 작은 사각형 순으로
            // 가운데 대각선을 기준으로 가장 큰 인덱스에서 좌, 상만 확인하면 됨.
            // ex) N-1 대각선 -> (N-1,N-2) > (N-2,N-1)인지 체크
            for(int i=N-1;i>0;i--){
                // 정렬이 되있지 않은 상태에서 전치도 발생하지 않은 경우 -> 전치 수행
                if(arr[i][i-1] < arr[i-1][i]&&!isChanged){
                    ans++;
                    isChanged = true;
                }
                // 정렬된 상태에서 전치가 이미 발생한 경우 -> 다시 전치 수행후 false
                else if(arr[i][i-1]>arr[i-1][i]&&isChanged){
                    ans++;
                    isChanged = false;
                }
            }
            System.out.println(ans);
        }
    }
}