import java.util.*;
import java.io.*;
 
public class Solution {
    /*
     * 1. test_case만큼 input
     * 2. row별 brk 개수 세기 + Color 객체 생성
     * 2-1. B=W+R, R=B+W, W=R+B 
     * 3. recursive(front, value)
     * 3-1. front) 이전 row의 색깔 저장, value) 이전 row까지 변경된 값 저장
     * 3-2. 종료조건) 빨간색 만났을때! 최솟값이면 저장 or row == M-1 인데 R가 아닌 경우 그냥 끝
     */
    static int N, M;
    static char[][] flag;
    static Color[] colors;
    static int answer;
 
    // 3. recursive(front, value)
    // 3-1. front) 이전 row의 색깔 저장, value) 이전 row까지 변경된 값 저장
    static void recursive(int prevRow, char color, int value) {
        int curRow = prevRow+1;
        if (curRow==N) {
            if (color!='R') return;
            answer = Math.min(answer, value);
            return;
        }
        if (color=='W') {
            recursive(curRow, 'W', value+colors[curRow].white);
            recursive(curRow, 'B', value+colors[curRow].blue);
        } else if (color=='B') {
            recursive(curRow, 'B', value+colors[curRow].blue);
            recursive(curRow, 'R', value+colors[curRow].red);
        } else if (color=='R') {
            recursive(curRow, 'R', value+colors[curRow].red);
        }
    }
    static class Color {
        int blue;
        int white;
        int red;
        Color(int blue, int white, int red) {
            this.blue = blue;
            this.white = white;
            this.red = red;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int T=1; T<=testCase; T++) {
            answer = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            flag = new char[N][M];
            colors = new Color[N];
            for (int row=0; row<N; row++) {
                String line = br.readLine();
                 
                // 2. row별 brk 개수 세기 + Color 객체 생성
                int blue=0, red=0, white=0;
                for (int col=0; col<M; col++) {
                    flag[row][col] = line.charAt(col);
                    if (flag[row][col]=='W') white++;
                    else if (flag[row][col]=='B') blue++;
                    else if (flag[row][col]=='R') red++;
                }
                // 2-1. B=W+R, R=B+W, W=R+B 
                colors[row] = new Color(white+red, blue+red, white+blue);
            }
            int value = colors[0].white;
            recursive(0, 'W', value);
            list.add(answer);
        }
        for (int test_case=1; test_case<=testCase; test_case++) {
            System.out.printf("#%d %d%n", test_case, list.get(test_case-1));
        }
    }
 
     
}
