import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

//1. 8개의 수를 입력받는다
//2. 하나의 사이클
//2-1. 큐의 첫번째 원소를 꺼내고, pop한다
//2-2. cycle % 5 +1 만큼 감소시킨다 (cycle%5의 범위는 0-4이다)
//2-3. cycle을 하나 증가시킨다
//2-4. 감소시킨 값이 0이 되면 반복을 종료한다
//2-5. 큐의 맨 뒤로 감소시킨 값을 넣는다
//3. 암호 큐를 출력한다


public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;
    static StringBuilder sb;
    static ArrayDeque<Integer> pw;
    static int cycle;



    public static void main(String[] args) throws NoSuchElementException, IOException {
        // TODO Auto-generated method stub
        bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        for(int tcase = 1; tcase <= 10; tcase++) {
            int Test = Integer.parseInt(bf.readLine().trim());
            cycle = 0;
            pw = new ArrayDeque<>();

            st = new StringTokenizer(bf.readLine().trim());
            for(int idx = 0; idx < 8; idx++) {
                pw.addLast(Integer.parseInt(st.nextToken()));
            }

            while(true) {
                //2-1. 큐의 첫번째 원소를 꺼내고, pop한다
                //2-2. cycle % 5 +1 만큼 감소시킨다 (cycle%5의 범위는 0-4이다)
                //2-3. cycle을 하나 증가시킨다
                int cur = pw.removeFirst() - (cycle++) % 5 + 1;

                //2-4. 감소시킨 값이 0보다 작으면 반복을 종료한다
                if(cur <= 0) {
                    pw.addLast(0);
                    break;
                }

                //2-5. 큐의 맨 뒤로 감소시킨 값을 넣는다
                pw.addLast(cur);
            }

            sb.append("#" + tcase + " ");
            for(Integer password : pw) {
                sb.append(password + " ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}
