import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());
        for(int nowTest = 1; nowTest <=testCase; nowTest++){
            st = new StringTokenizer(br.readLine().trim());
            int[] arr = new int[10];
            for(int index = 0; index <10; index++){
                arr[index] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            int result = 0;
            for(int index = 1; index < 9; index++) {
                result += arr[index];
            }

            System.out.printf("#%d %d%n", nowTest, Math.round((double) result /8));
        }
    }
}
