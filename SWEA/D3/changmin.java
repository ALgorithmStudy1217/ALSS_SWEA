import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
 
/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    static int arr[][];
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int n1_x, n1_y, n2_x, n2_y;
    static int size;
    static int num;
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
 
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase < N + 1; testCase++) {
            st = new StringTokenizer(br.readLine().trim());
                         
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
 
            // 배열 크기
            size = 301;
            arr = new int[size][size];
             
            // 대각선 시작 값
            num = 1;
             
//          1   3   6  10  15
//          2   5   9  14  19
//          4   8  13  18  22
//          7  12  17  21  24
//          11  16  20  23  25
             
            // 대각선 개수만큼 반복
            for (int i = 0; i < 2 * (size-1); i++) {
                 
                // 대각선 시작 좌표
                 
                int x = i < size ? i : size - 1;
                int y = i < size ? 0 : i - (size - 1);
                 
                // 시작점 좌표부터 값 채우기 
                while (x >= 0 && y < size) {
                    arr[x][y] = num++;
                    x--;
                    y++;
                }
            }        
 
            // 입력 값 좌표 찾기
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if(arr[i][j] == n1) {
                        n1_x = i;
                        n1_y = j;
                    }
                    if(arr[i][j] == n2) {
                        n2_x = i;
                        n2_y = j;
                    }
                }
            }
 
            bw.write("#" + testCase + " " + arr[n1_x + n2_x+1][n1_y + n2_y+1] + "\n");
 
        }
        bw.flush();
        bw.close();
 
    }
 
}



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
 

//class Solution
//{
//    static int arr[][];
//    static BufferedReader br;
//    static BufferedWriter bw;
//    static StringTokenizer st;
//    static int n1_x, n1_y, n2_x, n2_y;
//    public static void main(String args[]) throws Exception
//    {
//                br = new BufferedReader(new InputStreamReader(System.in));
//        bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        int N = Integer.parseInt(br.readLine());
//        for (int testCase = 1; testCase < N + 1; testCase++) {
//            st = new StringTokenizer(br.readLine().trim());
// 
//            int n1 = Integer.parseInt(st.nextToken());
//            int n2 = Integer.parseInt(st.nextToken());
// 
//            arr = new int[501][501];
// 
//            // 하단의 초기 값 넣어줌
//            arr[500][1] = 1;
//            arr[500][2] = 3;
//            arr[500][3] = 6;
// 
//            // 왼쪽의 초기 값 넣어줌
//            arr[499][1] = 2;
//            arr[498][1] = 4;
// 
//            // 하단 가로 값 생성
//            for (int i = 4; i < arr.length; i++) {
//                arr[500][i] = arr[500][i - 1] + i;
//            }
// 
//            // 왼쪽 세로 값 생성
//            for (int i = 497; i >= 0; i--) {
//                arr[i][1] = arr[i + 1][1] + (500 - i);
//            }
// 
//            // 안의 값 생성
//            for (int col = 499; col > 0; col--) {
//                for (int row = 2; row < arr.length; row++) {
//                    arr[col][row] = arr[col][row - 1] + (499 - col) + row + 1;
//                }
//            }
// 
//            for (int col = 500; col >= 0; col--) {
//                for (int row = 1; row < arr.length; row++) {
//                    if (arr[col][row] == n1) {
//                        n1_x = 501 - col;
//                        n1_y = row;
//                    }
//                    if (arr[col][row] == n2) {
//                        n2_x = 501 - col;
//                        n2_y = row;
//                    }
//                }
//            }
//            bw.write("#" + testCase + " " + arr[501 - (n1_x + n2_x)][n1_y + n2_y] + "\n");
// 
//        }
//        bw.flush();
//        bw.close();
// 
//    }
//}