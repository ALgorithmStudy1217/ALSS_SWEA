import java.util.*;
import java.io.*;

class Solution {
    static int T;
    static Map<Integer, int[]> points;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());


        points = new HashMap<>();
        int iterNum = 0, num = 0;

        while(num <= 10000) {
            int x = 1, y = 1;
            for (int i = 0; i <= iterNum; i++) {
                int nx, ny;

                if(i != iterNum / 2.0) {
                    nx = x + i;
                    ny = y + iterNum - i;

                } else {
                    nx = (iterNum + 2) / 2;
                    ny = (iterNum + 2) / 2;
                }
                points.put(++num, new int[]{nx, ny});
            }
            iterNum++;
        }


        for(int t = 1; t <= T; t ++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken().trim());
            int q = Integer.parseInt(st.nextToken().trim());
            int answer = calculate(p, q);
            System.out.println("#" + t + " " + answer);
        }

    }

    public static int calculate(int n1, int n2){
        int[] result1 = points.get(n1);
        int[] result2 = points.get(n2);

        int newP = result1[0] + result2[0];
        int newQ = result1[1] + result2[1];

        for(Map.Entry<Integer, int[]> entry: points.entrySet()){
            int[] values = entry.getValue();
            if(values[0] == newP & values[1] == newQ){
                System.out.println(newP + " " + newQ);
                return entry.getKey();
            }
        }

        return 0;
    }
}

