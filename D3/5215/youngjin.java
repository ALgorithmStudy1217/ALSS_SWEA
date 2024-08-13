import java.util.*;
import java.io.*;
 
/**
 * 
 * @author SSAFY
 * 1. testcase와 민기의 재료의 수, 제한 칼로리를 입력 받는다.
 * 2. 재료와 칼로리에 대한 정보를 입력받고 배열에 저장한다. int[][]
 * 3. 선택하거나 말거나 전략을 사용(부분집합)하여 최대 칼로리를 구한다.
 * 3-1. answer을 0으로 초기화하고, 생성되는 부분집합마다 answer의 값을 최댓값으로 갱신한다.
 * 3-2. 칼로리와 맛에 대한 점수를 가지고 간다.
 */
 
public class Solution {
     
    static int ingredientCount;
    static int maxCalorie;
    static int[][] ingredients;
    static int answer;
    static StringBuilder sb;
     
    static void PowerSet(int ingredientIndex, int sumCalorie, int sumTaste) {
        // 종료 조건
        if (sumCalorie > maxCalorie) {
            return;
        }
        answer = Math.max(answer, sumTaste);
        if (ingredientIndex == ingredientCount) {
            return;
        }
         
        int ingredientTaste = ingredients[ingredientIndex][0];
        int ingredientCalorie = ingredients[ingredientIndex][1];
         
        // 선택하기
        PowerSet(ingredientIndex+1, sumCalorie + ingredientCalorie, sumTaste+ingredientTaste);
        // 선택 안하기
        PowerSet(ingredientIndex+1, sumCalorie, sumTaste);
    }
 
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        // 1. testcase와 민기의 재료의 수, 제한 칼로리를 입력 받는다.
        int testCase = Integer.parseInt(br.readLine());
        for (int test_case=1; test_case<=testCase; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            ingredientCount = Integer.parseInt(st.nextToken());
            maxCalorie = Integer.parseInt(st.nextToken());
             
            // 2. 재료와 칼로리에 대한 정보를 입력받고 배열에 저장한다. int[][]
            ingredients = new int[ingredientCount][];
            for (int count=0; count<ingredientCount; count++) {
                st = new StringTokenizer(br.readLine().trim());
                ingredients[count] = new int[2];
                ingredients[count][0] = Integer.parseInt(st.nextToken());
                ingredients[count][1] = Integer.parseInt(st.nextToken());
            }
            answer = 0;
            PowerSet(0, 0, 0);
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
 
}
