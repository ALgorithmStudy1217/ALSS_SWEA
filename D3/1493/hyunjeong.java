import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;

    static int[] findPosition(int num){
        int n = 1;

        while (n * (n + 1) / 2 < num) {
            n++;
        }
        return new int[] {n - (n * (n+1) / 2 - num), 1 + n * (n+1) / 2 - num};
    }

    static int findPoint(int x, int y){
        int yIntercept = x + y - 1;
        return yIntercept * (yIntercept + 1) / 2 - (yIntercept - x);
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int nowTest = 1; nowTest <= T; nowTest++){
            st = new StringTokenizer(br.readLine().trim());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            int[] pPosition = findPosition(p);
            int[] qPosition = findPosition(q);

            System.out.println("#" + nowTest + " " + findPoint(pPosition[0] + qPosition[0], pPosition[1] + qPosition[1]));


        }
    }


}
