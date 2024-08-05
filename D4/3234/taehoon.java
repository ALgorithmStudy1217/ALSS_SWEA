import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    // 순열을 만든 뒤, 오른쪽 무게가 더 크면 종료
    // 순열 저장 배열
    static int [] permArr;
    // 입력 숫자 저장 배열
    static int [] inpArr;
    // 사용한 수 인지 체크
    static boolean [] isVisited;
    // 입력 수
    static int N;
    // 가능한 조합 수
    static int ans;
    // 순열 생성
    static void refunc(int cnt){
        // 순열 생성 후 검증
        if(cnt==N){
            check(0,0,0);
            return;
        }
        for(int idx=0;idx<N;idx++){
            // 이미 순열에 들어간 수는 제외
            if(isVisited[idx])
                continue;
            permArr[cnt] = inpArr[idx];
            isVisited[idx]=true;
            refunc(cnt+1);
            isVisited[idx]=false;
        }
    }
    static void check(int left, int right, int cnt){
        if(left<right) // 오른쪽 무게가 더 크면 종료
            return;
        if(cnt==N){
            ans++; // 왼쪽 무게가 더 크게 무게추를 두는 case인 경우 ++
            return;
        }
        check(left, right+permArr[cnt], cnt+1); // 오른쪽 추가
        check(left + permArr[cnt], right, cnt+1); // 왼쪽 추가
    }
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 조합 수 초기화
            ans = 0;
            // 입력 받을 수
            N = sc.nextInt();
            // 배열 초기화
            inpArr = new int[N];
            permArr = new int[N];
            isVisited = new boolean[N];
            // 무게 저장
            for(int idx=0;idx<N;idx++){
                inpArr[idx] = sc.nextInt();
            }
            refunc(0);
            System.out.println("#"+test_case + " " + ans);
        }
    }
}