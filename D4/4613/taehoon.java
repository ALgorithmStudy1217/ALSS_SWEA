
import java.util.Scanner;
import java.io.FileInputStream;

// 적어도 W,R,B가 한 줄씩 존재해야함.
// 가장 적게 색깔을 변하게 하는 횟수 출력
// 맨 윗줄 무조건 흰색, 그 후 파란색, 빨간색 순으로 무조건 1줄씩은 존재해야함.
class Solution
{

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 국기 배열 생성
            // 2차원 -> 0: W, 1: B, 2: R 색깔 수 저장
            // 최소값을 출력해야하므로 나올 수 있는 최대 수 + 1
            int ans = 2501;
            // 가로 입력
            int N = sc.nextInt();
            // 세로 입력
            int M = sc.nextInt();
            int [][] board = new int[N][3];
            // 한 줄 받고 배열에 해당하는 색깔의 수를 추가하는 방식
            for(int row = 0;row<N;row++){
                String info=sc.next();
                for(int col=0;col<M;col++){
                    if(info.charAt(col)=='W'){
                        board[row][0]++;
                    }
                    else if(info.charAt(col)=='B'){
                        board[row][1]++;
                    }
                    else if(info.charAt(col)=='R'){
                        board[row][2]++;
                    }
                }
            }
            // 누적 흰색으로 칠해야하는 수
            int whiteCnt = 0;
            // 맨 위는 흰색 줄, 최소 2줄은 파란, 빨간색에게 줘야하므로 N-3까지 체크
            for(int whiteIdx=0;whiteIdx<N-2;whiteIdx++){
                // 흰색 제외 모두 칠해야 하므로 M - 흰색 수
                whiteCnt+= (M-board[whiteIdx][0]);
                // 누적 파란색 수
                int blueCnt = 0;
                // 최소 1줄은 빨간색에게 줘야하므로 N-2까지 체크
                for(int blueIdx=whiteIdx+1;blueIdx<N-1;blueIdx++){
                    blueCnt+= (M-board[blueIdx][1]);
                    int redCnt=0;
                    for(int redIdx=blueIdx+1;redIdx<N;redIdx++){
                        redCnt+=(M-board[redIdx][2]);
                    }
                    // 모든 국기를 칠했으므로 값 갱신
                    ans = Math.min(ans, whiteCnt+redCnt+blueCnt);
                }
            }

            // 출력 : "#i ans"
            System.out.println("#"+test_case+" " + ans);
        }
    }
}