import java.util.Scanner;

// 높은 수가 적힌 사람이 두 카드의 적힌 수의 합만큼 점수 획득
// 패배 시 점수 획득 X
// 총점이 같으면 무승부
// 1명 카드 순서 고정. 나머지는 9!의 경우의 수
class Solution
{
    // 고정된 카드 배열 생성
    static int [] fixedArr = new int[9];
    // 나머지 카드 배열 생성
    static int [] restArr = new int[9];
    // 이기는 경우의 수
    static int winCnt = 0;
    // 지는 경우의 수
    static int loseCnt = 0;

    // 순열을 통한 선택 배열 생성
    static int [] selectArr = new int[9];
    // 방문 체크
    static boolean [] visited = new boolean[9];

    // 순열을 이용한 경우의 수 계산
    static void permutation(int selectIdx){
        if(selectIdx == 9){
            // 점수 계산
            int score1 = 0;
            int score2 = 0;
            for(int idx=0;idx<9;idx++){
                if(fixedArr[idx] > selectArr[idx]) {
                    score1 += fixedArr[idx] + selectArr[idx];
                } else { // 같은 카드가 존재하지 않으므로 비기는 경우는 존재 X
                    score2 += fixedArr[idx] + selectArr[idx];
                }
            }

            if(score1 > score2) winCnt++;
            else if(score1 < score2) loseCnt++;
            return;
        }

        for(int idx=0;idx<9;idx++){
            if(!visited[idx]){
                visited[idx] = true;
                selectArr[selectIdx] = restArr[idx];
                permutation(selectIdx+1);
                visited[idx] = false;
            }
        }
    }

    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            // 값 초기화
            winCnt = 0;
            loseCnt = 0;

            // 나머지 카드를 넣기 편하게 배열 생성
            int [] tempArr = new int[19];

            for(int idx=0;idx<9;idx++){
                fixedArr[idx] = sc.nextInt();
                tempArr[fixedArr[idx]] = 1;
            }


            int idx = 0;
            for(int cnt=1;cnt<19;cnt++){
                if(tempArr[cnt] == 0){
                    restArr[idx++] = cnt;
                }
            }

            permutation(0);

            System.out.println("#"+test_case+" "+winCnt+" "+loseCnt);
        }
    }
}
