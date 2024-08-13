
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Ladder1
/*  사다리의 구조와, 도착저짐이 입력된다
*   입력된 도착지점에서부터 역으로 올라가며 출발 지점을 찾는다
*   올라가는 도중,
*     - x-1 의 값이 1이면, x-1이 0이 나올때까지 왼쪽으로 이동한다
*     - x+1 의 값이 1이면, x+1이 0이 나올때까지 오른쪽으로 이동한다
* */

public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;



    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));

        //입력받는 사다리
        int ladder[][] = new int[100][100];

        for(int testCase = 1; testCase <= 10; testCase++) {
            int Test = Integer.parseInt(bf.readLine().trim());
            // 도착 지점의 x 좌표를 저장(y좌표는 무조건 99이다)
            int endPoint = -1;
            for(int row = 0; row<100; row++) {
                st = new StringTokenizer(bf.readLine().trim());
                for(int col = 0; col<100; col++) {
                    ladder[row][col] = Integer.parseInt(st.nextToken());
                    if(ladder[row][col] == 2) {
                        endPoint = col;
                    }
                }
            }

            for(int row = 99; row>-1; row--) {
                //col 값이 1이상일 때만 ladder의 왼쪽 값을 조사한다
                if (endPoint > 0 && ladder[row][endPoint - 1] == 1) {
                    while (endPoint > 0 && ladder[row][endPoint - 1] == 1) {
                        //왼쪽으로 이동
                        endPoint--;
                    }
                } else if (endPoint < 99 && ladder[row][endPoint + 1] == 1) {
                    //col 값이 99보다 작을 때만 ladder의 오른쪽 값을 조사한다
                    while (endPoint < 99 && ladder[row][endPoint + 1] == 1) {
                        //오른쪽으로 이동
                        endPoint++;
                    }
                }

            }


            System.out.println("#" + testCase + " " + endPoint);


        }
    }
}
