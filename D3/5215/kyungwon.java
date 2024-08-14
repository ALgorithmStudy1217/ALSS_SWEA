import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
 
// 1. 재료의 수, 제한 칼로리를 입력받는다
// 2. 재료의 수만큼 점수와 칼로리를 입력받아 배열에 저장한다
// 3. 재료의 수보다 작거나 같은 조합을 구한다.
// 3-1. 반복문을 돌며 조합의 요소 개수를 설정한다
// 4. 
// 4-1. elementHam의 인덱스 값을 selectHam으로 넣는다
// 4-2. 조합이 만들어지는 도중에, 칼로리를 넘어가면 그냥 return 한다
// 4-3. 조합이 다 만들어지면 점수를 계산하여 max_score을 업데이트 한다
 
 
public class Solution {
    static BufferedReader bf;
    static StringTokenizer st;
    static StringBuilder sb;
    static int cnt;
    static int sum_kcal;
    static int elementHam[][];
    static int max_score;
    static int combi_size;
     
    public static void combination(int elementIdx, int selectIdx, int score,int kcal) {
     
         
        // 4-1. 조합이 만들어지는 도중에, 칼로리를 넘어가면 그냥 return 한다
        if(kcal > sum_kcal) return;
        // 4-2. 조합이 다 만들어지면 점수를 계산하여 max_score을 업데이트 한다
        if(selectIdx == combi_size) {
            if(max_score < score) max_score = score;
            return;
        }
        if(elementIdx == cnt) return;
         
        // 4-3. elementHam의 인덱스 값을 selectHam으로 넣는다
        // 원소를 선택해서 담아줌
         
        combination(elementIdx+1, selectIdx +1, score + elementHam[elementIdx][0], kcal + elementHam[elementIdx][1]);
         
        // 원소를 선택하지 않고 넘어가기
         
        combination(elementIdx+1, selectIdx, score, kcal);
         
    }
 
    public static void main(String[] args) throws NoSuchElementException, IOException {
        // TODO Auto-generated method stub
        bf = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        int Test = Integer.parseInt(bf.readLine().trim());
        for(int tcase = 1; tcase <= Test; tcase++) {
            max_score=0;
            // 1. 재료의 수, 제한 칼로리를 입력받는다
            st = new StringTokenizer(bf.readLine().trim());
            cnt = Integer.parseInt(st.nextToken());
            sum_kcal = Integer.parseInt(st.nextToken());
             
             
            elementHam = new int[cnt][2];
            // 2. 재료의 수만큼 점수와 칼로리를 입력받아 배열에 저장한다
            for(int idx = 0; idx<cnt; idx++) {
                st = new StringTokenizer(bf.readLine().trim());
                for(int input = 0; input<2; input++) {
                    elementHam[idx][input] = Integer.parseInt(st.nextToken());
                }
            }
             
            // 3. 재료의 수보다 작거나 같은 조합을 구한다.
            // 3-1. 반복문을 돌며 조합의 요소 개수를 설정한다
            for(combi_size = 1; combi_size <= cnt; combi_size++) {
                 
                combination(0, 0, 0, 0);
            }
             
            System.out.println("#" + tcase + " " + max_score);
             
        }
    }
}
