
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;
    //이진수를 구하는 함수
    static String cal(int M) {
        String result = "";
        while(M > 0) {
            result = String.valueOf((M % 2)) + result;
            M /= 2;
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine().trim());

        for(int t = 1; t <= T; t++) {
           st = new StringTokenizer(bf.readLine().trim());
           int N = Integer.parseInt(st.nextToken());
           int M = Integer.parseInt(st.nextToken());
           String binary = cal(M);
           int size = binary.length();

          //이진수로 변환 후, 길이가 N보다 작으면 맞춰준다
           if(size < N) {
               for(int i = N; i > size; i--) {
                   binary = "0" + binary;
               }

           }
            //0이 하나라도 있으면 OFF 출력
           if(binary.substring(binary.length() - N).contains("0")) System.out.println("#" + t + " " + "OFF");
           else System.out.println("#" + t + " " + "ON");

        }
    }
}
