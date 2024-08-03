
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine().trim());

        for(int t = 1; t<= T; t++) {
            st = new StringTokenizer(bf.readLine().trim());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            int flag[][] = new int[row][3];
            for(int i = 0; i<row; i++) {
                String line = bf.readLine().trim();
                for(int j= 0; j<col; j++) {
                    if(line.charAt(j) == 'W') {
                        flag[i][0]++;
                    } else if(line.charAt(j) == 'B') {
                        flag[i][1]++;
                    } else {
                        flag[i][2]++;
                    }
                }
            }
            int min_val = 2501;
            int whiteCnt = 0;
            for(int white = 0; white<row-2; white++) {

                whiteCnt += col - flag[white][0];

                int blueCnt = 0;
                for(int blue = white +1; blue < row-1; blue++ ){
                    blueCnt += col - flag[blue][1];

                    int redCnt = 0;
                    for(int red = blue +1; red < row; red++){
                        redCnt += col - flag[red][2];
                    }

                    min_val = Math.min(min_val, whiteCnt+blueCnt+redCnt);
                }
            }

            System.out.println("#"+t+" " +min_val);
        }
    }
}
