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
            // 10개를 입력받아 최소, 최대값을 제외한 평균
            // 소수점 첫째자리에서 반올림
            // 최대값 최소값 저장
            int maxVal = -1;
            int minVal = 10001;
            int sum = 0;
            // 모든 값을 더한 후, 최대값과 최소값을 빼서 합을 구함
            for(int cnt=0;cnt<10;cnt++){
                int val = sc.nextInt();
                sum+=val;
                maxVal = Math.max(maxVal, val);
                minVal = Math.min(minVal, val);
            }
            sum -= maxVal;
            sum -= minVal;
            // 소수점 첫째자리에서 반올림하기 위해 형변환 적용
            double ans = Math.round((double)sum/ (double)8);
            // 소수점 첫째자리는 0이기 때문에 int로 다시 형변환 적용
            System.out.println("#" + test_case + " " + (int)ans);
        }
    }
}